package jp.groupsession.v2.prj.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * <br>[機  能] ツリー階層(ディレクトリ、ファイル)情報を格納するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class PrjChildTreeModel implements Serializable {

    /** 階層(ディレクトリ) */
    private ArrayList<PrjDirectoryModel> listOfDir__;
    /** 階層(ファイル) */
    private ArrayList<PrjDirectoryModel> listOfFile__;

    /**
     * <p>listOfDir を取得します。
     * @return listOfDir
     */
    public ArrayList<PrjDirectoryModel> getListOfDir() {
        return listOfDir__;
    }
    /**
     * <p>listOfDir をセットします。
     * @param listOfDir listOfDir
     */
    public void setListOfDir(ArrayList<PrjDirectoryModel> listOfDir) {
        listOfDir__ = listOfDir;
    }
    /**
     * <p>listOfFile を取得します。
     * @return listOfFile
     */
    public ArrayList<PrjDirectoryModel> getListOfFile() {
        return listOfFile__;
    }
    /**
     * <p>listOfFile をセットします。
     * @param listOfFile listOfFile
     */
    public void setListOfFile(ArrayList<PrjDirectoryModel> listOfFile) {
        listOfFile__ = listOfFile;
    }
}