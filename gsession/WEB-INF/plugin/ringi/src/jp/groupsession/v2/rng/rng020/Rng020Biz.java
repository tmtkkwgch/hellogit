package jp.groupsession.v2.rng.rng020;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.rng.biz.RngBiz;
import jp.groupsession.v2.rng.dao.RngChannelDao;
import jp.groupsession.v2.rng.dao.RngChannelTemplateDao;
import jp.groupsession.v2.rng.dao.RngChannelTemplateUserDao;
import jp.groupsession.v2.rng.dao.RngRndataDao;
import jp.groupsession.v2.rng.model.RingiRequestModel;
import jp.groupsession.v2.rng.model.RngChannelModel;
import jp.groupsession.v2.rng.model.RngChannelTemplateModel;
import jp.groupsession.v2.rng.model.RngChannelTemplateUserModel;
import jp.groupsession.v2.rng.model.RngRndataModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 稟議作成画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng020Biz {

    /** Loggingインスタンス */
    private static Log log__ = LogFactory.getLog(Rng020Biz.class);

    /** コネクション */
    private Connection con__ = null;
    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;
    /** セッションユーザSID */
    private int usrSid__;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     */
    public Rng020Biz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl リクエスト情報
     */
    public Rng020Biz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @param usrSid ユーザSID
     */
    public Rng020Biz(Connection con, RequestModel reqMdl, int usrSid) {
        con__ = con;
        reqMdl__ = reqMdl;
        usrSid__ = usrSid;
    }

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説] 処理モード = 編集の場合、稟議情報を設定する
     * <br>[備  考]
     * @param req リクエスト
     * @param paramMdl パラメータ情報
     * @param appRoot アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリパス
     * @param userMdl セッションユーザ情報
     * @throws SQLException SQL実行例外
     * @throws IOException 添付ファイルの操作に失敗
     * @throws IOToolsException 添付ファイルの操作に失敗
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public void setInitData(HttpServletRequest req, Rng020ParamModel paramMdl,
                            String appRoot, String tempDir, BaseUserModel userMdl)
    throws IOException, IOToolsException, SQLException, TempFileException {

        UDate now = new UDate();

        //申請者を設定する
        paramMdl.setRng020requestUser(userMdl.getUsiseimei());
        paramMdl.setRng020requestUserId(String.valueOf(userMdl.getUsrsid()));
        //作成日を設定する
        paramMdl.setRng020createDate(UDateUtil.getSlashYYMD(now)
                                + " "
                                + UDateUtil.getSeparateHMS(now));

        //稟議一覧からの遷移、かつ処理モード = 編集 の場合
        //または複写して申請する場合
        //稟議情報を取得する
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        if ((cmd.equals("rngEdit") && paramMdl.getRngCmdMode() == RngConst.RNG_CMDMODE_EDIT)
         || (cmd.equals("copyApply") && paramMdl.isRng020copyApply())) {

            int rngSid = paramMdl.getRngSid();
            //稟議情報を設定
            RngRndataDao rngDao = new RngRndataDao(con__);
            RngRndataModel rngData = rngDao.select(rngSid);
            paramMdl.setRng020Title(rngData.getRngTitle());
            paramMdl.setRng020Content(rngData.getRngContent());

            //稟議経路情報を設定
            RngChannelDao channelDao = new RngChannelDao(con__);
            List<RngChannelModel> channelList = channelDao.getChannelListWithoutDelUser(rngSid);
            List<String> apprUser = new ArrayList<String>();
            List<String> confirmUser = new ArrayList<String>();
            for (RngChannelModel channelMdl : channelList) {
                if (channelMdl.getRncType() == RngConst.RNG_RNCTYPE_APPR) {
                    apprUser.add(String.valueOf(channelMdl.getUsrSid()));
                } else if (channelMdl.getRncType() == RngConst.RNG_RNCTYPE_CONFIRM) {
                    confirmUser.add(String.valueOf(channelMdl.getUsrSid()));
                }
            }
            paramMdl.setRng020apprUser((String[]) apprUser.toArray(new String[apprUser.size()]));
            paramMdl.setRng020confirmUser(
                    (String[]) confirmUser.toArray(new String[confirmUser.size()]));

            //添付ファイルをテンポラリディレクトリへ移動する
            RngBiz rngBiz = new RngBiz(con__);
            rngBiz.setRingiTempFileData(con__, rngSid, appRoot, tempDir, now, reqMdl__);
        }

        //グループが未選択の場合、デフォルトグループを設定
        if (paramMdl.getRng020group() == -1) {
            GroupBiz grpBz = new GroupBiz();
            int defGrp = grpBz.getDefaultGroupSid(userMdl.getUsrsid(), con__);
            paramMdl.setRng020group(defGrp);
        }

        GsMessage gsMsg = new GsMessage(reqMdl__);

        //グループコンボを設定する。
        GroupBiz gpBiz = new GroupBiz();
        List<LabelValueBean> groupLabel = gpBiz.getGroupCombLabelList(con__, true, gsMsg);
        groupLabel.remove(0);
        paramMdl.setRng020groupList(groupLabel);

        //ユーザ一覧、承認経路、最終確認を設定する。
        String[] apprUser = __getCanUsePluginUser(con__, paramMdl.getRng020apprUser());
        String[] confirmUser = __getCanUsePluginUser(con__, paramMdl.getRng020confirmUser());
        paramMdl.setRng020apprUser(apprUser);
        paramMdl.setRng020confirmUser(confirmUser);

        List<String> notUser = new ArrayList<String>();
        if (apprUser != null) {
            notUser.addAll((List<String>) Arrays.asList(apprUser));
        }
        if (confirmUser != null) {
            notUser.addAll((List<String>) Arrays.asList(confirmUser));
        }
        notUser.add(String.valueOf(userMdl.getUsrsid()));

        UserBiz userBiz = new UserBiz();
        List<LabelValueBean> addUserList
            = userBiz.getNormalUserLabelList(con__, paramMdl.getRng020group(),
                                            (String[]) notUser.toArray(new String[notUser.size()]),
                                            false, gsMsg);
        String[] userSidList = new String[addUserList.size()];
        for (int idx = 0; idx < addUserList.size(); idx++) {
            userSidList[idx] = addUserList.get(idx).getValue();
        }

        List<String> canUseAddUserSid =
            (List<String>) Arrays.asList(__getCanUsePluginUser(con__, userSidList));
        List<LabelValueBean> canUseAddUserList = new ArrayList<LabelValueBean>(userSidList.length);
        for (LabelValueBean addLabel : addUserList) {
            if (canUseAddUserSid.contains(addLabel.getValue())) {
                canUseAddUserList.add(addLabel);
            }
        }
        paramMdl.setRng020userList(canUseAddUserList);

        RngBiz rngBiz = new RngBiz(con__);
        paramMdl.setRng020apprUserList(
                rngBiz.sortLabelList(userBiz.getUserLabelList(con__, apprUser), apprUser));
        paramMdl.setRng020confirmUserList(
                rngBiz.sortLabelList(userBiz.getUserLabelList(con__, confirmUser), confirmUser));

        //添付ファイル一覧を設定
        CommonBiz cmnBiz = new CommonBiz();
        paramMdl.setRng020fileList(cmnBiz.getTempFileLabelList(tempDir));

        //経路テンプレート一覧を設定
        RngChannelTemplateDao rctDao = new RngChannelTemplateDao(con__);
        List<RngChannelTemplateModel> rctList = rctDao.getChannelTemplateList(userMdl.getUsrsid());

        String msg = gsMsg.getMessage(req, "cmn.select.plz");

        List<LabelValueBean> rctLabelList = new ArrayList<LabelValueBean>();
        rctLabelList.add(new LabelValueBean(msg, "-1"));
        for (RngChannelTemplateModel rctData : rctList) {
            rctLabelList.add(new LabelValueBean(rctData.getRctName(),
                            String.valueOf(rctData.getRctSid())));
        }
        paramMdl.setRng020rctList(rctLabelList);

        //添付ファイル一覧(テンプレートから取得)を設定
        String templateFileDir = getTemplateFileDir(tempDir);
        paramMdl.setRng020templateFileList(
                cmnBiz.getTempFileLabelList(templateFileDir));

        log__.debug("End");
    }

    /**
     * <br>[機  能] 経路情報に経路テンプレートの内容を反映する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param userSid ユーザSID
     * @throws SQLException SQL実行例外
     */
    public void setChannelForTemplate(Rng020ParamModel paramMdl, int userSid)
    throws SQLException {
        RngChannelTemplateUserDao rctUserDao = new RngChannelTemplateUserDao(con__);
        List<RngChannelTemplateUserModel> rctUserList
                    = rctUserDao.getRctUserListWithoutDelUser(paramMdl.getRng020rctSid());

        List<String> apprUser = new ArrayList<String>();
        List<String> confirmUser = new ArrayList<String>();
        if (paramMdl.getRng020apprUser() != null) {
            apprUser.addAll(Arrays.asList(paramMdl.getRng020apprUser()));
        }
        if (paramMdl.getRng020confirmUser() != null) {
            confirmUser.addAll(Arrays.asList(paramMdl.getRng020confirmUser()));
        }

        for (RngChannelTemplateUserModel rctUserMdl : rctUserList) {
            String strUserSid = String.valueOf(rctUserMdl.getUsrSid());
            if (rctUserMdl.getRcuType() == RngConst.RNG_RNCTYPE_APPR
            && !apprUser.contains(strUserSid)
            && !confirmUser.contains(strUserSid)) {
                apprUser.add(strUserSid);
            } else if (rctUserMdl.getRcuType() == RngConst.RNG_RNCTYPE_CONFIRM
            && !apprUser.contains(strUserSid)
            && !confirmUser.contains(strUserSid)) {
                confirmUser.add(strUserSid);
            }
        }

        paramMdl.setRng020apprUser((String[]) apprUser.toArray(new String[apprUser.size()]));
        paramMdl.setRng020confirmUser(
                (String[]) confirmUser.toArray(new String[confirmUser.size()]));
    }

    /**
     * <br>[機  能] 稟議情報の登録処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param cntCon MlCountMtController
     * @param appRootPath アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリ
     * @param mode 登録モード 0:申請 1:草稿
     * @param pluginConfig プラグイン情報
     * @param smailPluginUseFlg ショートメールプラグイン有効フラグ
     * @param reqMdl リクエスト情報
     * @throws Exception 実行例外
     */
    public void entryRingiData(Rng020ParamModel paramMdl,
                                MlCountMtController cntCon,
                                String appRootPath,
                                String tempDir,
                                int mode,
                                PluginConfig pluginConfig,
                                boolean smailPluginUseFlg,
                                RequestModel reqMdl)
    throws Exception {
        log__.debug("START");

        RingiRequestModel model = new RingiRequestModel();
        model.setRngSid(paramMdl.getRngSid());
        model.setRngTitle(paramMdl.getRng020Title());
        model.setRngContent(paramMdl.getRng020Content());
        model.setRngapprUser(paramMdl.getRng020apprUser());
        model.setRngconfirmUser(paramMdl.getRng020confirmUser());
        model.setAppRootPath(appRootPath);
        model.setTempDir(tempDir);
        model.setUserSid(usrSid__);
        model.setDate(new UDate());

        RngBiz rngBiz = new RngBiz(con__, cntCon);
        rngBiz.entryRingiData(model, mode,
                paramMdl.getRngCmdMode(), pluginConfig, smailPluginUseFlg, reqMdl);

        log__.debug("End");
    }

    /**
     * <br>[機  能] 稟議テンプレート反映の際、添付ファイルの保存先として使用するディレクトリのパスを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param tempDir テンポラリディレクトリ
     * @return 添付ファイルの保存先として使用するディレクトリのパス
     */
    public String getTemplateFileDir(String tempDir) {
        String templateDir = IOTools.replaceSlashFileSep(tempDir).toString();
        if (!templateDir.endsWith("/")) {
            templateDir += "/";
        }
        return templateDir + "template" + "/";
    }

    /**
     * <br>[機  能] 指定されたユーザから対象プラグインを使用可能なユーザを選択して返す
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param userSidList ユーザSID
     * @return ユーザSID
     * @throws SQLException SQL実行時例外
     */
    private String[] __getCanUsePluginUser(Connection con, String[] userSidList)
    throws SQLException {
        if (userSidList == null || userSidList.length == 0) {
            return userSidList;
        }

        List<Integer> iUserSidList = new ArrayList<Integer>(userSidList.length);
        for (String userSid : userSidList) {
            iUserSidList.add(new Integer(userSid));
        }

        CommonBiz cmnBiz = new CommonBiz();
        iUserSidList = cmnBiz.getCanUsePluginUser(con, RngConst.PLUGIN_ID_RINGI, iUserSidList);

        //ユーザSIDの順序
        List<String> canUseUserList = new ArrayList<String>();
        for (String userSid : userSidList) {
            if (iUserSidList.contains(new Integer(userSid))) {
                canUseUserList.add(userSid);
            }
        }

        return canUseUserList.toArray(new String[canUseUserList.size()]);
    }

}
