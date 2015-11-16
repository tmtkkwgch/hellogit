package jp.groupsession.v2.convert;

import jp.co.sjts.util.io.IOTools;

/**
 * <br>[機  能] コンバートで共通的に使用するビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ConvertBiz {

    /**
     * <br>[機  能] コンバート用バックアップディレクトリPATHを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param appRootPath アプリケーションROOT
     * @return String コンバート用バックアップディレクトリPATH
     */
    public static String getBackupDirPath(String appRootPath) {

        String backupDir = appRootPath.concat("WEB-INF/convert/backup/");
        backupDir = IOTools.replaceFileSep(backupDir);
        return backupDir;
    }

}
