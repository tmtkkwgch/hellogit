package jp.groupsession.v2.man.man150kn;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.encryption.EncryptionException;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.io.ObjectFile;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.GSContext;
import jp.groupsession.v2.cmn.cmn110.Cmn110FileModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.lic.GSConstLicese;
import jp.groupsession.v2.lic.LicenseModel;
import jp.groupsession.v2.lic.LicenseOperation;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] メイン 管理者設定 ライセンスファイル登録・更新確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man150knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Man150knBiz.class);


    /**
     * <br>[機  能] 初期表示情報を画面にセットする
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl リクエスト情報
     * @param reqMdl リクエスト情報
     * @param tempDir テンポラリディレクトリ
     * @throws IOToolsException 入出力時例外
     * @throws IOException  ファイル操作時例外
     * @throws EncryptionException 文字列複合化時例外
     */
    public void setInitData(Man150knParamModel paramMdl, RequestModel reqMdl, String tempDir)
        throws IOToolsException, EncryptionException, IOException {
        log__.debug("setInitData Start");

        String saveFileName = "";

        //テンポラリディレクトリにあるファイル名称を取得
        List<String> fileList = IOTools.getFileNames(tempDir);

        if (fileList != null) {

            for (int i = 0; i < fileList.size(); i++) {

                //ファイル名を取得
                String fileName = fileList.get(i);
                if (!fileName.endsWith(GSConstCommon.ENDSTR_OBJFILE)) {
                    continue;
                }

                //オブジェクトファイルを取得
                ObjectFile objFile = new ObjectFile(tempDir, fileName);
                Object fObj = objFile.load();
                if (fObj == null) {
                    continue;
                }
                Cmn110FileModel fMdl = (Cmn110FileModel) fObj;
                saveFileName = fMdl.getSaveFileName();
            }

            String fullPath = tempDir + saveFileName;
            File impFile = new File(fullPath);

            String licenseId = "";
            String licenseCom = "";
            ArrayList<LicenseModel> pluginList = new ArrayList<LicenseModel>();

            LicenseOperation lop = new LicenseOperation();
            LicenseModel lmdl = lop.getLicenseFileData(impFile);
            GsMessage gsMsg = new GsMessage(reqMdl);

            if (lmdl != null) {

                if (!StringUtil.isNullZeroStringSpace(lmdl.getLicenseId())) {
                    licenseId = String.valueOf(lmdl.getLicenseId());
                }

                if (!StringUtil.isNullZeroStringSpace(lmdl.getLicenseCom())) {
                    licenseCom = String.valueOf(lmdl.getLicenseCom());
                }

                LicenseModel pmdl = null;
                //プラグイン情報 サポート
                if (!StringUtil.isNullZeroStringSpace(lmdl.getLicenseLimitSupport())) {
                    pmdl = new LicenseModel();
                    pmdl.setPluginName(gsMsg.getMessage("cmn.support"));
                    pmdl.setLicenseLimit(lmdl.getLicenseLimitSupport());
                    pluginList.add(pmdl);
                }

                //プラグイン情報 モバイル
                if (!StringUtil.isNullZeroStringSpace(lmdl.getLicenseLimitMobile())) {
                    pmdl = new LicenseModel();
                    pmdl.setPluginName(gsMsg.getMessage("mobile.4"));
                    pmdl.setLicenseLimit(lmdl.getLicenseLimitMobile());
                    pluginList.add(pmdl);
                }

                //プラグイン情報 CrossRide
                if (!StringUtil.isNullZeroStringSpace(lmdl.getLicenseLimitCrossRide())) {
                    pmdl = new LicenseModel();
                    pmdl.setPluginName(GSConstLicese.PLUGIN_NAME_CROSSRIDE);
                    pmdl.setLicenseLimit(lmdl.getLicenseLimitCrossRide());
                    pluginList.add(pmdl);
                }
            }

            paramMdl.setMan150LicenseId(licenseId);
            paramMdl.setMan150LicenseCom(licenseCom);
            paramMdl.setMan150PluginList(pluginList);
        }
    }

    /**
     * <br>[機  能] ライセンスファイルを取り込む
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param appRoot アプリケーションルート
     * @param tempDir テンポラリディレクトリ
     * @param gs GSコンテキスト
     * @param domain ドメイン
     * @throws IOToolsException 入出力時例外
     * @throws IOException  入出力時例外
     * @throws EncryptionException 文字列複合化時例外
     */
    public void importLicenseFile(String appRoot,
                                  String tempDir,
                                  GSContext gs,
                                  String domain)
        throws IOToolsException, IOException, EncryptionException {

        String saveFileName = "";

        //テンポラリディレクトリにあるファイル名称を取得
        List<String> fileList = IOTools.getFileNames(tempDir);

        if (fileList != null) {

            for (int i = 0; i < fileList.size(); i++) {

                //ファイル名を取得
                String fileName = fileList.get(i);
                if (!fileName.endsWith(GSConstCommon.ENDSTR_OBJFILE)) {
                    continue;
                }

                //オブジェクトファイルを取得
                ObjectFile objFile = new ObjectFile(tempDir, fileName);
                Object fObj = objFile.load();
                if (fObj == null) {
                    continue;
                }
                Cmn110FileModel fMdl = (Cmn110FileModel) fObj;
                saveFileName = fMdl.getSaveFileName();
            }

            String fullPath = tempDir + saveFileName;
            File impFile = new File(fullPath);

            LicenseOperation lop = new LicenseOperation();
            lop.updateLicenseFile(
                    appRoot, impFile, domain);
            lop.updateGSContext(impFile, domain);
        }
    }
}