package jp.groupsession.v2.rng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.io.IOTools;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmInfDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.rng.dao.RingiDao;
import jp.groupsession.v2.rng.dao.RngChannelDao;
import jp.groupsession.v2.rng.dao.RngRndataDao;
import jp.groupsession.v2.rng.model.RngChannelModel;
import jp.groupsession.v2.rng.model.RngRndataModel;
import jp.groupsession.v2.sml.GSConstSmail;
import jp.groupsession.v2.sml.SmlSender;
import jp.groupsession.v2.sml.model.SmlSenderModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 稟議の申請、承認などが行われた場合に呼ばれるリスナーを実装
 * <br>[解  説] 稟議に関する処理を行う
 * <br>[備  考]
 *
 * @author JTS
 */
public class RngListenerImpl implements IRingiListener {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(RngListenerImpl.class);

    /** 稟議ステータス 申請 */
    private static final int STATUS_APPLY__ = 0;
    /** 稟議ステータス 承認 */
    private static final int STATUS_APPROVAL__ = 1;
    /** 稟議ステータス 承認(完了) */
    private static final int STATUS_APPRCOMP__ = 2;
    /** 稟議ステータス 却下 */
    private static final int STATUS_REJECT__ = 3;
    /** 稟議ステータス 差し戻し */
    private static final int STATUS_REFLECTION__ = 4;

    /**
     * <br>[機  能] 稟議申請時に実行される
     * <br>[解  説]
     * <br>[備  考]
     * @param model 稟議リスナーパラメータ
     * @param reqMdl リクエスト情報
     * @throws Exception 実行時例外
     */
    public void doApplyRingi(RingiListenerModel model, RequestModel reqMdl) throws Exception {

        RngChannelDao channelDao = new RngChannelDao(model.getCon());
        int userSid = 0;
        //管理者設定ショートメール通知区分 「各ユーザが設定」 の場合
        if (model.getSmlNtf() == RngConst.RAR_SML_NTF_USER) {
            userSid = channelDao.getApprovalSmUser(model.getRngSid(), true);
        } else {
            //管理者設定ショートメール通知区分 「管理者が設定」 の場合
            userSid = channelDao.getApprovalSmUser(model.getRngSid(), false);
        }


        if (userSid <= 0) {
            log__.debug("申請メールの送信を行わない。");
            return;
        }

        List<Integer> userList = new ArrayList<Integer>();
        userList.add(new Integer(userSid));
        __sendSmail(model, userList, STATUS_APPLY__, false, reqMdl);
    }

    /**
     * <br>[機  能] 稟議承認時に実行される
     * <br>[解  説]
     * <br>[備  考]
     * @param model 稟議リスナーパラメータ
     * @param reqMdl リクエスト情報
     * @throws Exception 実行時例外
     */
    public void doApprovalRingi(RingiListenerModel model, RequestModel reqMdl) throws Exception {
        RngChannelDao channelDao = new RngChannelDao(model.getCon());
        int userSid = 0;

        //管理者設定ショートメール通知区分「各ユーザが設定」の場合
        if (model.getSmlNtf() == RngConst.RAR_SML_NTF_USER) {
            userSid = channelDao.getApprovalSmUser(model.getRngSid(), true);
        } else {
            //管理者設定ショートメール通知区分「管理者が設定」の場合
            userSid = channelDao.getApprovalSmUser(model.getRngSid(), false);
        }

        if (userSid <= 0) {
            log__.debug("承認メールの送信を行わない。");
            return;
        }

        List<Integer> userList = new ArrayList<Integer>();
        userList.add(new Integer(userSid));
        __sendSmail(model, userList, STATUS_APPROVAL__, false, reqMdl);
    }

    /**
     * <br>[機  能] 稟議承認(完了)時に実行される
     * <br>[解  説]
     * <br>[備  考]
     * @param model 稟議リスナーパラメータ
     * @param reqMdl リクエスト情報
     * @throws Exception 実行時例外
     */
    public void doApprovalCompleteRingi(RingiListenerModel model,
            RequestModel reqMdl) throws Exception {

        RngChannelDao channelDao = new RngChannelDao(model.getCon());
        List<Integer> userList = null;
        boolean sendApplUser;
        //管理者設定ショートメール通知区分「各ユーザが設定」の場合
        if (model.getSmlNtf() == RngConst.RAR_SML_NTF_USER) {
            userList = channelDao.getConfirmSmUserList(model.getRngSid(), true);
            RingiDao ringiDao = new RingiDao(model.getCon());
            sendApplUser = ringiDao.isApplicateSmUser(model.getRngSid());
        } else {
            //管理者設定ショートメール通知区分「管理者が設定」の場合
            userList = channelDao.getConfirmSmUserList(model.getRngSid(), false);
            sendApplUser = true;
        }


        if (userList.isEmpty() && !sendApplUser) {
            log__.debug("承認(完了)メールの送信を行わない。");
            return;
        }

        __sendSmail(model, userList, STATUS_APPRCOMP__, sendApplUser, reqMdl);
    }

    /**
     * <br>[機  能] 稟議却下時に実行される
     * <br>[解  説]
     * <br>[備  考]
     * @param model 稟議リスナーパラメータ
     * @param reqMdl リクエスト情報
     * @throws Exception 実行時例外
     */
    public void doRejectRingi(RingiListenerModel model, RequestModel reqMdl) throws Exception {
        RngChannelDao channelDao = new RngChannelDao(model.getCon());
        List<Integer> userList = null;

        //管理者設定ショートメール通知区分「各ユーザが設定」の場合
        boolean sendApplUser;
        if (model.getSmlNtf() == RngConst.RAR_SML_NTF_USER) {
            userList = channelDao.getBeforeApprovalSmUserList(model.getRngSid(), true);
            RingiDao ringiDao = new RingiDao(model.getCon());
            sendApplUser = ringiDao.isApplicateSmUser(model.getRngSid());
        } else {
            //管理者設定ショートメール通知区分「管理者が設定」の場合
            userList = channelDao.getBeforeApprovalSmUserList(model.getRngSid(), false);
            sendApplUser = true;
        }


        if (userList.isEmpty() && !sendApplUser) {
            log__.debug("却下メールの送信を行わない。");
            return;
        }

        __sendSmail(model, userList, STATUS_REJECT__, sendApplUser, reqMdl);
    }

    /**
     * <br>[機  能] 稟議差し戻し時に実行される
     * <br>[解  説]
     * <br>[備  考]
     * @param model 稟議リスナーパラメータ
     * @param reqMdl リクエスト情報
     * @throws Exception 実行時例外
     */
    public void doReflectionRingi(RingiListenerModel model,
            RequestModel reqMdl) throws Exception {
        RngChannelDao channelDao = new RngChannelDao(model.getCon());

        List<Integer> userList = new ArrayList<Integer>();
        int userSid = 0;
        boolean sendApplUser;

        //管理者設定ショートメール通知区分「各ユーザが設定」の場合
        if (model.getSmlNtf() == RngConst.RAR_SML_NTF_USER) {
            userSid = channelDao.getApprovalSmUser(model.getRngSid(), true);
            RingiDao ringiDao = new RingiDao(model.getCon());
            sendApplUser = ringiDao.isApplicateSmUser(model.getRngSid());
        } else {
            //管理者設定ショートメール通知区分「管理者が設定」の場合
            userSid = channelDao.getApprovalSmUser(model.getRngSid(), false);
            sendApplUser = true;
        }


        if (userSid <= 0 && !sendApplUser) {
            log__.debug("差し戻しメールの送信を行わない。");
            return;
        }

        userList.add(new Integer(userSid));
        __sendSmail(model, userList, STATUS_REFLECTION__, sendApplUser, reqMdl);
    }

    /**
     * <br>[機  能] ショートメールで更新通知を行う。
     * <br>[解  説]
     * <br>[備  考]
     * @param model 稟議リスナーパラメータ
     * @param sendUsers 送信先ユーザ一覧
     * @param status 稟議ステータス 承認 or 却下
     * @param sendApplUser 申請者を通知対象に含めるか true:含める false:含めない
     * @param reqMdl リクエスト情報
     * @throws Exception 実行例外
     */
    private void __sendSmail(RingiListenerModel model, List<Integer> sendUsers,
                            int status, boolean sendApplUser, RequestModel reqMdl)
    throws Exception {

        if (!model.isSmailPluginFlg()) {
            //ショートメールプラグインが無効の場合は送信を行わない。
            return;
        }

        //稟議SID
        int rngSid = model.getRngSid();

        //稟議情報を取得
        RngRndataDao rngDao = new RngRndataDao(model.getCon());
        RngRndataModel rngData = rngDao.select(rngSid);

        if (sendApplUser) {
            //メール送信対象者に申請者を含める
            sendUsers.add(new Integer(rngData.getRngApplicate()));
        }

        String msgTitle = "";
        String msgStatus = "";

        //ショートメールタイトル、状態、日時
        String smTitle = "";
        String rngStatus = "";
        String smDate = "";
        RngChannelDao channelDao = new RngChannelDao(model.getCon());
        GsMessage gsMsg = new GsMessage(reqMdl);
        if (status == STATUS_APPLY__) {
            msgTitle = gsMsg.getMessage("rng.100");
            msgStatus = gsMsg.getMessage("rng.46");
            smTitle = msgTitle;
            rngStatus = msgStatus;
            RngChannelModel chlMdl = channelDao.select(model.getRngSid(),
                                                    sendUsers.get(0).intValue());
            smDate = __getDateString(chlMdl.getRncRcvdate());
        } else if (status == STATUS_APPROVAL__) {
            msgTitle = gsMsg.getMessage("rng.100");
            msgStatus = gsMsg.getMessage("rng.46");
            smTitle = msgTitle;
            rngStatus = msgStatus;
            RngChannelModel chlMdl = channelDao.select(model.getRngSid(),
                                                    sendUsers.get(0).intValue());
            smDate = __getDateString(chlMdl.getRncRcvdate());
        } else if (status == STATUS_APPRCOMP__) {
            msgTitle = gsMsg.getMessage("rng.96");
            msgStatus = gsMsg.getMessage("rng.29");
            smTitle = msgTitle;
            rngStatus = msgStatus;
            smDate = __getDateString(rngData.getRngEdate());
        } else if (status == STATUS_REJECT__) {
            msgTitle = gsMsg.getMessage("rng.95");
            msgStatus = gsMsg.getMessage("rng.22");
            smTitle = msgTitle;
            rngStatus = msgStatus;
            smDate = __getDateString(rngData.getRngEdate());
        } else if (status == STATUS_REFLECTION__) {
            msgTitle = gsMsg.getMessage("rng.98");
            msgStatus = gsMsg.getMessage("rng.rng030.08");
            smTitle = msgTitle;
            rngStatus = msgStatus;
            smDate = __getDateString(rngData.getRngEdate());
        }
        //タイトル
        String rngTitle = rngData.getRngTitle();
        //申請者名
        CmnUsrmInfDao udao = new CmnUsrmInfDao(model.getCon());
        CmnUsrmInfModel umodel = udao.select(rngData.getRngApplicate());
        String tname = umodel.getUsiSei() + " " + umodel.getUsiMei();
        //申請日時
        UDate ud = rngData.getRngAppldate();
        String tdate = UDateUtil.getSlashYYMD(ud) + " "
        + UDateUtil.getSeparateHMS(ud);
        //添付ファイル
        RingiDao ringiDao = new RingiDao(model.getCon());
        List <CmnBinfModel> fileList = ringiDao.getRingiTmpFileList(rngSid);
        StringBuilder fileLine = new StringBuilder();
        for (CmnBinfModel file : fileList) {
            String fileName = file.getBinFileName();
            if (fileLine.length() != 0) {
                fileLine.append(" , ");
            }
            fileLine.append(fileName);
        }

        //内容
        String body = rngData.getRngContent();

        //宛先(通知を受けるに設定されたユーザSIDを取得する。)
        //本文
        String tmpPath = __getSmlTemplateFilePath(model.getAppRootPath()); //テンプレートファイルパス取得
        String tmpBody = IOTools.readText(tmpPath, Encoding.UTF_8);
        Map<String, String> map = new HashMap<String, String>();
        map.put("SMTITLE", smTitle);
        map.put("STATUS", rngStatus);
        map.put("SMDATE", smDate);
        map.put("TITLE", rngTitle);
        map.put("UNAME", tname);
        map.put("DATE", tdate);
        map.put("FILES", fileLine.toString());
        map.put("BODY", body);
        map.put("URL", model.getRngUrl());
        String bodyml = StringUtil.merge(tmpBody, map);

        String errorMsg = gsMsg.getMessage("cmn.mail.omit");

        if (bodyml.length() > GSConstCommon.MAX_LENGTH_SMLBODY) {
            bodyml = errorMsg + "\r\n\r\n" + bodyml;
            bodyml = bodyml.substring(0, GSConstCommon.MAX_LENGTH_SMLBODY);
        }

        //ショートメール送信用モデルを作成する。
        SmlSenderModel smlModel = new SmlSenderModel();
        //送信者(システムメールを指定)
        smlModel.setSendUsid(GSConst.SYSTEM_USER_MAIL);
        //TO
        smlModel.setSendToUsrSidArray(sendUsers);
        String msg = gsMsg.getMessage("rng.71");
        String msg2 = gsMsg.getMessage("rng.88");

        //タイトル
        String title = msg + " " + rngStatus + msg2;
        if (!StringUtil.isNullZeroString(rngData.getRngTitle())) {
            title += " " + rngData.getRngTitle();
            title = StringUtil.trimRengeString(title,
                                                GSConstCommon.MAX_LENGTH_SMLTITLE);
        }
        smlModel.setSendTitle(title);

        //本文
        smlModel.setSendBody(bodyml);
        //メール形式
        smlModel.setSendType(GSConstSmail.SAC_SEND_MAILTYPE_NORMAL);
        //マーク
        smlModel.setSendMark(GSConstSmail.MARK_KBN_NONE);

        //メール送信処理開始
        PluginConfig pluginConfig = model.getPluginConfig();
        SmlSender sender =
            new SmlSender(
                    model.getCon(),
                    model.getCntCon(),
                    smlModel, pluginConfig,
                    model.getAppRootPath(),
                    reqMdl);
        sender.execute();
    }

    /**
     * <br>[機  能] 承認/否認通知メールのテンプレートパスを返す。
     * <br>[解  説]
     * <br>[備  考]
     * @param appRootPath アプリケーションのルートパス
     * @return テンプレートファイルのパス文字列
     */
    private String __getSmlTemplateFilePath(String appRootPath) {
        //WEBアプリケーションのパス
        appRootPath = IOTools.setEndPathChar(appRootPath);
        String ret = IOTools.replaceSlashFileSep(appRootPath
                + "/WEB-INF/plugin/ringi/smail/koushin_tsuuchi.txt");
        return ret;
    }

    /**
     * <br>[機  能] 日時文字列を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param date 日時
     * @return 日時文字列
     */
    private String __getDateString(UDate date) {
        StringBuilder dateStr = new StringBuilder("");
        dateStr.append(UDateUtil.getSlashYYMD(date));
        dateStr.append(" ");
        dateStr.append(UDateUtil.getSeparateHMS(date));
        return dateStr.toString();
    }

}