package jp.groupsession.v2.fil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.groupsession.v2.fil.dao.FileDirectoryDao;
import jp.groupsession.v2.fil.model.FilChildTreeModel;
import jp.groupsession.v2.fil.model.FileDirectoryModel;

/**
 * <br>[機  能] ファイルツリーBusinessLogic
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class FilTreeBiz {

    /** DBコネクション */
    private Connection con__ = null;
    /** OPENディレクトリ */
    private String openDirSid__ = "";

    /**
     * <p>openDirSid を取得します。
     * @return openDirSid
     */
    public String getOpenDirSid() {
        return openDirSid__;
    }
    /**
     * <p>openDirSid をセットします。
     * @param openDirSid openDirSid
     */
    public void setOpenDirSid(String openDirSid) {
        openDirSid__ = openDirSid;
    }

    /**
     * <p>デフォルトコンストラクタ
     * @param con Connection
     */
    public FilTreeBiz(Connection con) {
        con__ = con;
    }

    /**
     * <br>[機  能] ツリーを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param cabiSid キャビネットSID
     * @param level 階層LV
     * @param usrSid ユーザSID
     * @param auth 権限区分
     * @param superUser 特権ユーザ
     * @return ret ツリー配列
     * @throws SQLException SQL実行時例外
     */
     public String[] getFileTree(int cabiSid, int level, int usrSid, int auth, boolean superUser)
         throws SQLException {

         FileDirectoryDao dao = new FileDirectoryDao(con__);

         //階層取得
         String[] ret = null;
         if (superUser) {
             ret = dao.getTreeList(cabiSid, level);
         } else {
             ret = dao.getTreeListAccessLimit(cabiSid, level, usrSid, auth);
         }

         return ret;
     }

   /**
    * <br>[機  能] ツリーを取得する
    * <br>[解  説]
    * <br>[備  考]
    *
    * @param cabiSid キャビネットSID
    * @param level 階層LV
    * @return ret ツリー配列
    * @throws SQLException SQL実行時例外
    */
    public String[] getFileTree(int cabiSid, int level)
        throws SQLException {

        FileDirectoryDao dao = new FileDirectoryDao(con__);

        //階層取得
        String[] ret = dao.getTreeList(cabiSid, level);

        return ret;
    }

    /**
     * <br>[機  能] ツリーを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param cabiSid キャビネットSID
     * @param level 階層LV
     * @param fdrSid 移動元のディレクトリSID
     * @return ret ツリー配列
     * @throws SQLException SQL実行時例外
     */
     public String[] getFileTreeForMove(int cabiSid, int level, int fdrSid)
         throws SQLException {

         FileDirectoryDao dao = new FileDirectoryDao(con__);
         List<Integer> fdrSidList = new ArrayList<Integer>();
         fdrSidList.add(fdrSid);

         //階層取得
         String[] ret = dao.getTreeListForMove(cabiSid, level, fdrSidList);

         return ret;
     }

     /**
      * <br>[機  能] ツリーを取得する
      * <br>[解  説]
      * <br>[備  考]
      *
      * @param cabiSid キャビネットSID
      * @param level 階層LV
      * @param fdrSidList 移動元のディレクトリSIDリスト
      * @param usrSid ユーザSID
      * @param superUser 特権ユーザ
      * @return ret ツリー配列
      * @throws SQLException SQL実行時例外
      */
      public String[] getFileTreeForMove(
              int cabiSid, int level, List<Integer> fdrSidList, int usrSid, boolean superUser)
                      throws SQLException {

          FileDirectoryDao dao = new FileDirectoryDao(con__);

          //階層取得
          String[] ret = null;
          if (superUser) {
              ret = dao.getTreeListForMove(cabiSid, level, fdrSidList);
          } else {
              ret = dao.getTreeListForMoveAccessLimit(cabiSid, level, fdrSidList, usrSid);
          }

          return ret;
      }

      /**
       * <br>[機  能] ツリーを取得する
       * <br>[解  説]
       * <br>[備  考]
       *
       * @param cabiSid キャビネットSID
       * @param level 階層LV
       * @param fdrSidList 移動元のディレクトリSIDリスト
       * @return ret ツリー配列
       * @throws SQLException SQL実行時例外
       */
       public String[] getFileTreeForMove(int cabiSid, int level, List<Integer> fdrSidList)
           throws SQLException {

           FileDirectoryDao dao = new FileDirectoryDao(con__);

           //階層取得
           String[] ret = dao.getTreeListForMove(cabiSid, level, fdrSidList);

           return ret;
       }

    /**
    * <br>[機  能] 指定されたディレクトリ以下を全て取得する
    * <br>[解  説] 自ディレクトリを結果セットに含める
    * <br>[備  考]
    *
    * @param bean 自ディレクトリModel
    * @return ret 指定されたディレクトリ以下全てのディレクトリ情報
    * @throws SQLException SQL実行時例外
    */
    public FilChildTreeModel getChildOfTarget(FileDirectoryModel bean)
        throws SQLException {

        FilChildTreeModel ret = new FilChildTreeModel();

        //階層(ディレクトリ)
        ArrayList<FileDirectoryModel> listOfDir = new ArrayList<FileDirectoryModel>();

        //階層(ファイル)
        ArrayList<FileDirectoryModel> listOfFile = new ArrayList<FileDirectoryModel>();

        //階層取得
        getChildOfLevelX(bean, listOfDir, listOfFile);

        ret.setListOfDir(listOfDir);
        ret.setListOfFile(listOfFile);

        return ret;
    }

   /**
    * <br>[機  能] 指定された子階層を取得する
    * <br>[解  説]
    * <br>[備  考]
    *
    * @param bean 自ディレクトリModel
    * @param listOfDir ディレクトリ(フォルダ)リスト
    * @param listOfFile ディレクトリ(ファイル)リスト
    * @throws SQLException SQL実行時例外
    */
    public void getChildOfLevelX(FileDirectoryModel bean,
                                  ArrayList<FileDirectoryModel> listOfDir,
                                  ArrayList<FileDirectoryModel> listOfFile)
        throws SQLException {

        //自ディレクトリ追加
        if (bean.getFdrParentSid() != GSConstFile.DIRECTORY_ROOT) {
            listOfDir.add(bean);
        }

        ArrayList<FileDirectoryModel> listOfParent = new ArrayList<FileDirectoryModel>();
        listOfParent.add(bean);

        int level = bean.getFdrLevel();

        FileDirectoryDao dao = new FileDirectoryDao(con__);
        for (int i = level + 1; i <= GSConstFile.DIRECTORY_LEVEL_10 + 1; i++) {
            if (!listOfParent.isEmpty()) {
                listOfParent =
                    dao.setChildDirList(listOfParent, listOfDir, listOfFile);
            }
        }
    }
}