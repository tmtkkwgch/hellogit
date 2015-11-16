package jp.groupsession.v2.cmn.cmn997;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.encryption.Blowfish;
import jp.co.sjts.util.encryption.EncryptionException;
import jp.groupsession.v2.cmn.ConfigBundle;
import jp.groupsession.v2.cmn.GroupSession;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.lic.GSConstLicese;
import jp.groupsession.v2.lic.LicenseModel;
import jp.groupsession.v2.lic.LicenseOperation;
import jp.groupsession.v2.man.man150kn.Man150knBiz;
import jp.groupsession.v2.struts.AbstractGsAction;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] ライセンス情報の再読み込み画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考
 *]
 * @author JTS
 */
public class Cmn997Action extends AbstractGsAction {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Man150knBiz.class);

    /**
     * <p>セッションが確立されていない状態でのアクセスを許可するのか判定を行う。
     * @param req リクエスト
     * @param form アクションフォーム
     * @return true:許可する,false:許可しない
     */
    public boolean canNoSessionAccess(HttpServletRequest req, ActionForm form) {
        return true;
    }

    /**
     * <p>アクセス可能なドメインかを判定
     * @param req リクエスト
     * @return true:許可する,false:許可しない
     * @throws Exception ドメイン判定時に例外発生
     */
    public boolean canAccessDomain(HttpServletRequest req) throws Exception {
        return true;
    }

    /**
     * <br>[機  能] アクションを実行する
     * <br>[解  説]
     * <br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws Exception 実行例外
     * @see #executeAction(org.apache.struts.action.ActionMapping,
     *                     org.apache.struts.action.ActionForm,
     *                     javax.servlet.http.HttpServletRequest,
     *                     javax.servlet.http.HttpServletResponse,
     *                     java.sql.Connection)
    */
    public ActionForward executeAction(
        ActionMapping map,
        ActionForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con)
        throws Exception {

        Cmn997Form thisForm = (Cmn997Form) form;

        if (!StringUtil.isNullZeroStringSpace(thisForm.getDomain())
                && !StringUtil.isNullZeroStringSpace(thisForm.getCmn997ValueGsAuthParam())) {

            if (GroupSession.getResourceManager().getDomain(req)
                    .equals(ConfigBundle.getValue("MULTI_MAINT_DOMAIN"))
                    && thisForm.getCmn997ValueGsAuthParam()
                    .equals("zAEf6yaN8iFNbKR5TEKLfhmiNBNW8PbmjBKAB3dK3N3g2AQMKPuWaJQsyENNkCd56")) {

                    GroupSession.getResourceManager().doDomain(
                            thisForm.getDomain(), thisForm.getChangeKbn());

                    __updateGSContext(thisForm.getDomain());
                }

        }

        return null;
    }
    /**
     * <br>[機  能] ライセンスファイルの読み込み
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param domain ドメイン
     * @throws IOException ファイル入出力時例外
     * @throws EncryptionException 文字列複合化時例外
     */
   private void __updateGSContext(String domain)
        throws IOException, EncryptionException {

        CommonBiz cmnBiz = new CommonBiz();
        String appRootPath = getAppRootPath();
        String filePath = cmnBiz.getSaveLicensePath(appRootPath, domain);
        String fullPath = filePath + GSConstLicese.LICENSE_NAME;
        File impFile = new File(fullPath);

        if (impFile != null) {

            BufferedReader in = null;
            FileInputStream fis = null;

            try {

                in =
                    new BufferedReader(
                            new InputStreamReader(
                                new FileInputStream(impFile), Encoding.UTF_8));

                String line = in.readLine();
                String fileString = "";
                while (line != null) {
                    fileString = fileString + line;
                    line = in.readLine();
                }

                if (!StringUtil.isNullZeroStringSpace(fileString)) {

                    String decFileString = getDecryString(fileString);
                    String[] splitString = decFileString.split("\r\n");

                    if (splitString != null && splitString.length > 0) {

                        String idStr = "";
                        String uidStr = "";
                        String numberStr = "";
                        String diskStr = "";
                        String comStr = "";
                        String cdateStr = "";
                        String ldate_support = "";
                        String ldate_mobile = "";
                        String ldate_crossRide = "";

                        String idKey = GSConstLicese.KEY_ID + "=";
                        String uidKey = GSConstLicese.KEY_UID + "=";
                        String numberKey = GSConstLicese.KEY_NUMBER + "=";
                        String diskKey = GSConstLicese.KEY_DISK + "=";
                        String comKey = GSConstLicese.KEY_COM + "=";
                        String cdateKey = GSConstLicese.KEY_CDATE + "=";
                        String ldateSupportKey = GSConstLicese.KEY_LIMIT_SUP + "=";
                        String ldateMobileKey = GSConstLicese.KEY_LIMIT_MBL + "=";
                        String ldateCrossRideKey = GSConstLicese.KEY_LIMIT_CROSSRIDE + "=";

                        for (int i = 0; i < splitString.length; i++) {
                            //キー = ライセンスID
                            if (splitString[i].startsWith(idKey)) {
                                idStr = splitString[i].substring(idKey.length());
                            //キー = GS UID
                            } else if (splitString[i].startsWith(uidKey)) {
                                    uidStr = splitString[i].substring(uidKey.length());
                            //キー = 人数
                            } else if (splitString[i].startsWith(numberKey)) {
                                numberStr = splitString[i].substring(numberKey.length());
                            //キー = 会社名
                            } else if (splitString[i].startsWith(comKey)) {
                                comStr = splitString[i].substring(comKey.length());
                            //キー = ライセンス発効日
                            } else if (splitString[i].startsWith(cdateKey)) {
                                cdateStr = splitString[i].substring(cdateKey.length());
                            //キー = サポート期限
                            } else if (splitString[i].startsWith(ldateSupportKey)) {
                                ldate_support = splitString[i].substring(ldateSupportKey.length());
                            //キー = モバイル期限
                            } else if (splitString[i].startsWith(ldateMobileKey)) {
                                ldate_mobile = splitString[i].substring(ldateMobileKey.length());
                            //キー = CrossRide契約
                            } else if (splitString[i].startsWith(ldateCrossRideKey)) {
                                ldate_crossRide =
                                    splitString[i].substring(ldateCrossRideKey.length());
                            //キー = ディスク容量
                            } else if (splitString[i].startsWith(diskKey)) {
                                diskStr =
                                    splitString[i].substring(diskKey.length());
                            }
                        }

                        LicenseModel lmdl = new LicenseModel();
                        lmdl.setLicenseId(idStr);
                        lmdl.setLicenseNumber(numberStr);
                        lmdl.setLicenseDisk(diskStr);
                        lmdl.setLicenseCom(comStr);
                        lmdl.setLicenseCdate(cdateStr);
                        lmdl.setGsUid(uidStr);

                        //サポート期限
                        lmdl.setLicenseLimitSupport(LicenseOperation.getPluginLimit(ldate_support));
                        //モバイル使用期限
                        lmdl.setLicenseLimitMobile(LicenseOperation.getPluginLimit(ldate_mobile));
                        //CrossRide
                        lmdl.setLicenseLimitCrossRide(
                                LicenseOperation.getPluginLimit(ldate_crossRide));

                        GroupSession.getResourceManager().updateLicense(domain, lmdl);
                    }
                }

            } catch (FileNotFoundException e) {
                log__.error("FileNotFoundException", e);
                throw e;
            } catch (IOException e) {
                log__.error("IOException", e);
                throw e;
            } catch (EncryptionException e) {
                log__.error("EncryptionException", e);
                throw e;
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                    if (fis != null) {
                        fis.close();
                    }
                } catch (IOException e) {
                    log__.error("IOException", e);
                }
            }
        }
    }
    /**
     * <br>[機  能] 暗号化された文字を復号する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param target 暗号化された文字
     * @return 復号化した文字
     * @throws EncryptionException 文字の復号に失敗
     */
    public static String getDecryString(String target) throws EncryptionException {

        String decStr = null;

        if (target != null) {
            try {
                byte[] decBytes = Base64.decodeBase64(target.getBytes(Encoding.UTF_8));
                decStr = Blowfish.decrypt(GSConstLicese.LICENSE_PHRASE, decBytes);
            } catch (Exception e) {
                log__.error("復号化に失敗", e);
                throw new EncryptionException("復号化に失敗", e);
            }
        }

        return decStr;
    }
}