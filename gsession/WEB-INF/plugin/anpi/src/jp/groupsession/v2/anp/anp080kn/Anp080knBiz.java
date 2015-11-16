package jp.groupsession.v2.anp.anp080kn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.encryption.EncryptionException;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.anp.AnpiCommonBiz;
import jp.groupsession.v2.anp.GSConstAnpi;
import jp.groupsession.v2.anp.dao.AnpAdmConfDao;
import jp.groupsession.v2.anp.dao.AnpCmnConfDao;
import jp.groupsession.v2.anp.model.AnpAdmConfModel;
import jp.groupsession.v2.anp.model.AnpCmnConfModel;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.UsidSelectGrpNameDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.usr.GSPassword;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;


/**
 * <br>[機  能] 管理者設定・基本設定確認画面ビジネスロジック
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Anp080knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Anp080knBiz.class);

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param anp080knModel パラメータモデル
     * @param reqMdl リクエストモデル
     * @param con DBコネクション
     * @throws Exception 実行例外
     */
    public void setInitData(Anp080knParamModel anp080knModel, RequestModel reqMdl, Connection con)
                        throws Exception {
        log__.debug("初期表示");

        //安否確認管理者ユーザ情報を取得
        String[] adms = anp080knModel.getAnp080AdmUserList();
        if (adms != null && adms.length > 0) {
            anp080knModel.setAnp080AdmUserLabel(__getKoukaiLavle(adms, con));
        }

        //SMTP認証パスワード
        anp080knModel.setAnp080knDspSendPass(
                GSPassword.createDamyPassword(anp080knModel.getAnp080SendPass()));
    }

    /**
     * <br>[機  能] 送信サーバへの接続テストを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param anp080knModel パラメータモデル
     * @param reqMdl リクエストモデル
     * @return エラー
     * @throws Exception 実行例外
     */
    public ActionErrors connectTest(Anp080knParamModel anp080knModel, RequestModel reqMdl)
                        throws Exception {

        ActionErrors errors = new ActionErrors();
        GsMessage gsMsg = new GsMessage(reqMdl);

        //送信サーバに接続可能かチェック
        AnpiCommonBiz anpibiz = new AnpiCommonBiz();
        AnpCmnConfModel cmnConf = __getUpdateCmnConf(anp080knModel, 0, null);

        String msgKey;
        ActionMessage msg;

        if (anpibiz.connectSendServer(cmnConf) != GSConstAnpi.SENDMSG_SUCCESS) {
            msgKey = "error.connect.failed.mailserver";
            msg = new ActionMessage(
                    msgKey, gsMsg.getMessage("anp.smtp.server"),
                    gsMsg.getMessage("anp.smtp.server"));
            StrutsUtil.addMessage(errors, msg, "servercheck." + msgKey);
        }

        return errors;
    }

    /**
     * <br>[機  能] 管理者設定基本設定 登録・更新処理
     * <br>[解  説]
     * <br>[備  考]
     * @param anp080knModel パラメータモデル
     * @param reqMdl リクエストモデル
     * @param con DBコネクション
     * @throws Exception 実行例外
     * @return true:初回登録 false:更新処理
     */
    public boolean doUpdate(Anp080knParamModel anp080knModel, RequestModel reqMdl, Connection con)
                        throws Exception {

        //登録区分
        boolean ret = false;

        boolean commitFlg = false;
        UDate now = new UDate();

        try {
            con.setAutoCommit(false);

            //セッション情報を取得
            BaseUserModel usModel = reqMdl.getSmodel();
            int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID

            //共通設定更新
            AnpCmnConfDao cDao = new AnpCmnConfDao(con);
            AnpCmnConfModel cbean = __getUpdateCmnConf(anp080knModel, sessionUsrSid, now);
            int count = cDao.update(cbean);
            if (count == 0) {
                cDao.insert(cbean);
                ret = true;
            }

            //管理者設定更新
            AnpAdmConfDao aDao = new AnpAdmConfDao(con);
            aDao.delete();

            String[] admList = anp080knModel.getAnp080AdmUserList();
            ArrayList<Integer> grpSids = new ArrayList<Integer>();
            ArrayList<Integer> usrSids = new ArrayList<Integer>();

            //ユーザSIDとグループSIDを分離
            if (admList != null) {

                for (String sid : admList) {
                    sid = NullDefault.getString(sid, "-1");
                    if (sid.contains(new String("G").subSequence(0, 1))) {
                        //グループ
                        grpSids.add(new Integer(sid.substring(1, sid.length())));
                    } else {
                        //ユーザ
                        usrSids.add(new Integer(sid));
                    }
                }

                AnpAdmConfModel mdl = null;
                //ユーザを登録
                if (usrSids != null) {
                    for (Integer uSid : usrSids) {
                        mdl = __getUpdateAdmConf(uSid, -1, sessionUsrSid, now);
                        aDao.insert(mdl);
                    }
                }
                //グループを登録
                if (grpSids != null) {
                    for (Integer gSid : grpSids) {
                        mdl = __getUpdateAdmConf(-1, gSid, sessionUsrSid, now);
                        aDao.insert(mdl);
                    }
                }
            }

            commitFlg = true;

        } catch (SQLException e) {
            log__.error("SQLException", e);
            throw e;
        } finally {
            if (commitFlg) {
                con.commit();
            } else {
                JDBCUtil.rollback(con);
            }
        }

        return ret;
    }

    /**
     * <br>[機  能] 共通設定の更新情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param anp080knModel パラメータモデル
     * @param upUser 更新ユーザSID
     * @param upDate 更新日時
     * @return AnpCmnConfModel 共通設定MODEL
     * @throws EncryptionException パスワードの復号に失敗
     */
    private AnpCmnConfModel __getUpdateCmnConf(
            Anp080knParamModel anp080knModel, int upUser, UDate upDate)
                    throws EncryptionException {

        AnpCmnConfModel ret = new AnpCmnConfModel();

        if (anp080knModel.getAnp080UrlSetKbn() == GSConstAnpi.URL_SETTING_AUTO) {
            ret.setApcUrlBase(anp080knModel.getAnp080SvBaseUrlAuto());
            ret.setApcUrlKbn(GSConstAnpi.URL_SETTING_AUTO);
        } else {
            ret.setApcUrlBase(anp080knModel.getAnp080BaseUrl());
            ret.setApcUrlKbn(GSConstAnpi.URL_SETTING_MANUAL);
        }
        ret.setApcAddress(anp080knModel.getAnp080SendMail());
        ret.setApcSendHost(anp080knModel.getAnp080SendHost());
        ret.setApcSendPort(Integer.valueOf(anp080knModel.getAnp080SendPort()));
        if (anp080knModel.getAnp080SendSsl() == GSConstAnpi.SEND_SSL_NOUSE) {
            ret.setApcSendSsl(GSConstAnpi.SEND_SSL_NOUSE);
        } else {
            ret.setApcSendSsl(GSConstAnpi.SEND_SSL_USE);
        }
        if (StringUtil.isNullZeroString(anp080knModel.getAnp080SmtpAuth())) {
            ret.setApcSmtpAuth(GSConstAnpi.SMTP_AUTH_NOT);
        } else {
            ret.setApcSmtpAuth(Integer.valueOf(anp080knModel.getAnp080SmtpAuth()));
        }
        if (ret.getApcSmtpAuth() == GSConstAnpi.SMTP_AUTH_YES) {
            ret.setApcSendUser(anp080knModel.getAnp080SendUser());
            ret.setApcSendPass(GSPassword.getEncryPassword(anp080knModel.getAnp080SendPass()));
        }
        ret.setApcAuid(upUser);
        ret.setApcAdate(upDate);
        ret.setApcEuid(upUser);
        ret.setApcEdate(upDate);

        return ret;
    }

    /**
     * <br>[機  能] 管理者設定の更新モデルを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param usrSid ユーザSID
     * @param grpSid グループSID
     * @param upUser 更新ユーザSID
     * @param upDate 更新日時
     * @return AnpCmnConfModel 共通設定MODEL
     */
    private AnpAdmConfModel __getUpdateAdmConf(int usrSid, int grpSid, int upUser, UDate upDate) {

        AnpAdmConfModel ret = new AnpAdmConfModel();
        ret.setUsrSid(usrSid);
        ret.setGrpSid(grpSid);
        ret.setApaAuid(upUser);
        ret.setApaAdate(upDate);
        ret.setApaEuid(upUser);
        ret.setApaEdate(upDate);

        return ret;
    }


    /**
     * <br>[機  能] 安否確認管理者一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param left 取得するユーザSID・グループSID
     * @param con コネクション
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getKoukaiLavle(String[] left, Connection con)
    throws SQLException {

        ArrayList<LabelValueBean> ret = new ArrayList<LabelValueBean>();

        ArrayList<Integer> grpSids = new ArrayList<Integer>();
        ArrayList<String> usrSids = new ArrayList<String>();

        //ユーザSIDとグループSIDを分離
        if (left != null) {
            for (int i = 0; i < left.length; i++) {
                String str = NullDefault.getString(left[i], "-1");
                if (str.contains(new String("G").subSequence(0, 1))) {
                    //グループ
                    grpSids.add(new Integer(str.substring(1, str.length())));
                } else {
                    //ユーザ
                    usrSids.add(str);
                }
            }
        }
        //グループ情報取得
        UsidSelectGrpNameDao gdao = new UsidSelectGrpNameDao(con);
        ArrayList<GroupModel> glist = gdao.selectGroupNmListOrderbyClass(grpSids);
        LabelValueBean lavelBean = null;
        for (GroupModel gmodel : glist) {
            lavelBean = new LabelValueBean();
            lavelBean.setLabel(gmodel.getGroupName());
            lavelBean.setValue("G" + String.valueOf(gmodel.getGroupSid()));
            ret.add(lavelBean);
        }

        //ユーザ情報取得
        UserBiz userBiz = new UserBiz();
        ArrayList<BaseUserModel> ulist
                = userBiz.getBaseUserList(con,
                                        (String[]) usrSids.toArray(new String[usrSids.size()]));
        for (BaseUserModel umodel : ulist) {
            lavelBean = new LabelValueBean();
            lavelBean.setLabel(umodel.getUsisei() + " " + umodel.getUsimei());
            lavelBean.setValue(String.valueOf(umodel.getUsrsid()));
            ret.add(lavelBean);
        }

        return ret;
    }
}