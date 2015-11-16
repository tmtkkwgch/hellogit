package jp.groupsession.v2.sml.sml110;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.encryption.EncryptionException;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.sml.GSConstSmail;
import jp.groupsession.v2.sml.biz.SmlCommonBiz;
import jp.groupsession.v2.sml.dao.SmailDao;
import jp.groupsession.v2.sml.dao.SmlAdminDao;
import jp.groupsession.v2.sml.dao.SmlFwlmtDao;
import jp.groupsession.v2.sml.model.SmlAdminModel;
import jp.groupsession.v2.sml.model.SmlFwlmtModel;
import jp.groupsession.v2.usr.GSPassword;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] ショートメール 管理者設定 転送設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sml110Biz {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sml110Biz.class);

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     * @throws EncryptionException パスワードデコードに失敗
     */
    public void setInitData(Sml110ParamModel paramMdl,
            RequestModel reqMdl, Connection con)
    throws SQLException, EncryptionException {

        //セッション情報を取得
        int sessionUsrSid = -1;
        BaseUserModel usModel = reqMdl.getSmodel();
        if (usModel != null) {
            sessionUsrSid = usModel.getUsrsid();
        }

        //DBより現在の設定を取得する。(なければデフォルト)
        SmlCommonBiz cmnBiz = new SmlCommonBiz(reqMdl);
        SmlAdminModel admConf = cmnBiz.getSmailAdminConf(sessionUsrSid, con);

        //転送設定
        paramMdl.setSml110MailForward(
                NullDefault.getString(
                        paramMdl.getSml110MailForward(),
                        String.valueOf(admConf.getSmaMailfw())));
        //SMTP URL
        paramMdl.setSml110SmtpUrl(
                NullDefault.getString(
                        paramMdl.getSml110SmtpUrl(),
                        String.valueOf(admConf.getSmaSmtpurl())));
        //SMTP ポート
        paramMdl.setSml110SmtpPort(
                NullDefault.getString(
                        paramMdl.getSml110SmtpPort(),
                        String.valueOf(admConf.getSmaSmtpPort())));
        //認証ユーザID
        paramMdl.setSml110SmtpUser(
                NullDefault.getString(
                        paramMdl.getSml110SmtpUser(),
                        String.valueOf(admConf.getSmaSmtpUser())));
        //認証パスワード
        paramMdl.setSml110SmtpPass(
                NullDefault.getString(
                        paramMdl.getSml110SmtpPass(),
                        GSPassword.getDecryPassword(admConf.getSmaSmtpPass())));
        //fromアドレス
        paramMdl.setSml110FromAddress(
                NullDefault.getString(
                        paramMdl.getSml110FromAddress(),
                        String.valueOf(admConf.getSmaFromAdd())));
        //転送先制限区分
        paramMdl.setSml110FwLmtKbn(
                NullDefault.getString(
                        paramMdl.getSml110FwLmtKbn(),
                        String.valueOf(admConf.getSmaFwlmtKbn())));

        //転送先制限 文字列
        __setFwTextArea(paramMdl, con);

        //SSL使用フラグ
        paramMdl.setSml110SslFlg(admConf.getSmaSsl());
    }

    /**
     * <br>[機  能] メールの転送先を制限する文字列を設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    private void __setFwTextArea(Sml110ParamModel paramMdl, Connection con)
    throws SQLException {

        if (paramMdl.getSml110FwLmtKbn().equals(GSConstSmail.MAIL_FORWARD_LIMIT)
                && paramMdl.getSml110FwlmtTextArea() == null) {

            SmlFwlmtDao dao = new SmlFwlmtDao(con);
            List<SmlFwlmtModel> fwlmtModelList = dao.select();

            String lmtTxt = "";
            for (SmlFwlmtModel model : fwlmtModelList) {
                lmtTxt = lmtTxt + model.getSflText() + "\n";
            }

            paramMdl.setSml110FwlmtTextArea(lmtTxt);
        }
    }

    /**
     * <br>[機  能] メール転送先制限情報をDBに登録する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param sessionUsrSid ユーザSID
     * @throws SQLException SQL実行エラー
     */
    private void __setSmlFwLmt(Sml110ParamModel paramMdl, Connection con, int sessionUsrSid)
    throws SQLException {

        //転先制限情報を削除
        SmlFwlmtDao dao = new SmlFwlmtDao(con);
        dao.delete();

        if (paramMdl.getSml110FwLmtKbn().equals(GSConstSmail.MAIL_FORWARD_LIMIT)) {
            String fwlmtTextArea = paramMdl.getSml110FwlmtTextArea();
            String[] fwlmtText = null;
            if (fwlmtTextArea != null) {
                fwlmtText = fwlmtTextArea.split("\n");
            }

            UDate now = new UDate();
            SmlFwlmtModel model = new SmlFwlmtModel();

            model.setSflAuid(sessionUsrSid);
            model.setSflAdate(now);
            model.setSflEuid(sessionUsrSid);
            model.setSflEdate(now);

            //転先制限情報の登録
            for (int i = 0; fwlmtText.length > i; i++) {
                model.setSflText(StringUtilHtml.deleteHtmlTag(fwlmtText[i]));
                dao.insert(model);
            }
        }
    }

    /**
     * <br>[機  能] DBへの登録を行う。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param umodel ユーザモデル
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     * @throws EncryptionException パスワードエンコードに失敗
     */
    public void setSmlConfData(Sml110ParamModel paramMdl,
            BaseUserModel umodel, Connection con)
    throws SQLException, EncryptionException {

        //DB更新
        boolean commitFlg = false;
        try {

            //メール転送設定をDBに登録する。
            __setAdminConf(paramMdl, umodel, con);

            //メール転送先制限情報をDBに登録する。
            __setSmlFwLmt(paramMdl, con, umodel.getUsrsid());

            commitFlg = true;
        } catch (SQLException e) {
            log__.error("メール転送設定の更新に失敗", e);
            throw e;
        } finally {
            if (commitFlg) {
                con.commit();
            }
        }
    }

    /**
     * <br>[機  能] メール転送設定をDBに登録する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param umodel ユーザモデル
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     * @throws EncryptionException パスワードエンコードに失敗
     */
    private void __setAdminConf(Sml110ParamModel paramMdl,
            BaseUserModel umodel, Connection con) throws SQLException, EncryptionException {

        UDate now = new UDate();
        //既存のデータを取得
        //データを設定
        SmlAdminModel conf = new SmlAdminModel();
        conf.setSmaMailfw(
                NullDefault.getInt(
                        paramMdl.getSml110MailForward(), GSConstSmail.MAIL_FORWARD_NG));
        conf.setSmaSmtpurl(NullDefault.getString(paramMdl.getSml110SmtpUrl(), ""));
        conf.setSmaSmtpPort(NullDefault.getString(paramMdl.getSml110SmtpPort(), ""));
        conf.setSmaSmtpUser(NullDefault.getString(paramMdl.getSml110SmtpUser(), ""));
        conf.setSmaSmtpPass(GSPassword.getEncryPassword(
                NullDefault.getString(paramMdl.getSml110SmtpPass(), "")));
        conf.setSmaFromAdd(NullDefault.getString(paramMdl.getSml110FromAddress(), ""));
        conf.setSmaFwlmtKbn(
                NullDefault.getInt(
                        paramMdl.getSml110FwLmtKbn(),
                        Integer.parseInt(GSConstSmail.MAIL_FORWARD_NO_LIMIT)));
        conf.setSmaSsl(paramMdl.getSml110SslFlg());

        conf.setSmaEuid(umodel.getUsrsid());
        conf.setSmaEdate(now);

        //管理者設定を更新
        SmlAdminDao dao = new SmlAdminDao(con);
        int count = dao.update(conf);
        if (count <= 0) {
            conf.setSmaAuid(umodel.getUsrsid());
            conf.setSmaAdate(now);
            conf.setSmaAcntMake(GSConstSmail.KANRI_USER_ONLY);
            conf.setSmaAutoDelKbn(GSConstSmail.AUTO_DEL_ADM);
            dao.insert(conf);
        }
    }

    /**
     * <br>[機  能] メール転送先不正リストを設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setCheckList(Sml110ParamModel paramMdl, Connection con)
    throws SQLException {

        String fwlmtTextArea = paramMdl.getSml110FwlmtTextArea();
        String[] fwlmtText = null;
        if (fwlmtTextArea != null) {
            fwlmtText = fwlmtTextArea.split("\n");
        }
        String[] lmtTxt = new String[fwlmtText.length];

        int n = 0;
        for (int i = 0; fwlmtText.length > i; i++) {
            if (fwlmtText[i] != null && !fwlmtText[i].equals("")) {
                lmtTxt[n] = StringUtilHtml.deleteHtmlTag(fwlmtText[i]);
                n++;
            }
        }

        SmailDao dao = new SmailDao(con);
        List<Sml110FwCheckModel> fwCheckList = dao.getFwErrorList(lmtTxt);

          paramMdl.setSml110FwCheckList(fwCheckList);
          if (fwCheckList == null || fwCheckList.size() == 0) {
              paramMdl.setSml110CheckFlg(GSConstSmail.FW_CHECK_ON);
          }
    }
}
