package jp.groupsession.v2.rsv.rsv091;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rsv.AbstractReserveBiz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 施設予約 施設・設備画像設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv091Biz extends AbstractReserveBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rsv091Biz.class);
    /** リクエスト情報 */
    protected RequestModel reqMdl_ = null;
    /** コネクション */
    protected Connection con_ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param reqMdl リクエスト情報
     * @param con コネクション
     */
    public Rsv091Biz(RequestModel reqMdl, Connection con) {
        reqMdl_ = reqMdl;
        con_ = con;
    }

    /**
     * <br>[機  能] 処理権限判定
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv091ParamModel
     * @return ret true:処理実行可 false:処理実行負荷
     * @throws SQLException SQL実行時例外
     */
    public boolean isPossibleToProcess(Rsv091ParamModel paramMdl)
        throws SQLException {

       /***********************************************
         *
         * 施設の編集が可能か判定する
         *
         * 1:システム管理者である
         * 2:処理対象の施設グループが【権限設定をしない】に
         *   設定されている
         * 3:施設グループの管理者に設定されている
         *
         ***********************************************/
        boolean ret = _isSisetuEditAuthority(reqMdl_, con_, paramMdl.getRsv090EditGrpSid());
        log__.debug("処理権限 = " + ret);

        return ret;

    }

    /**
     * <br>[機  能] 添付ファイル情報をセット
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv091ParamModel
     * @param tempDir テンポラリディレクトリ
     * @param con コネクション
     * @throws IOToolsException
     * @throws IOToolsException ファイルアクセス時例外
     */
    public void setTempFiles(Rsv091ParamModel paramMdl, String tempDir, Connection con)
        throws IOToolsException {

        /** 画面に表示するファイルのリストを作成、セット **********************/
        //テンポラリディレクトリにあるファイル名称を取得
//        List<String> fileList = IOTools.getFileNames(tempDir);

        CommonBiz commonBiz = new CommonBiz();
        paramMdl.setRsv091FileLabelList(commonBiz.getTempFileLabelList(tempDir));
    }

    /**
     * <br>[機  能] 画像を別のテンポラリディレクトリに移動させる
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param oldDir 保存元ディレクトリ
     * @param newDir 保存先ディレクトリ
     * @throws IOToolsException ファイルアクセス時例外
     * @throws IOException 添付ファイルの操作に失敗
     */
    public void copyImgOthTemp(String oldDir,
                                String newDir)
    throws IOToolsException, IOException {

        //保存元のファイル
        log__.debug("******保存元ディレクトリパス:" + oldDir);
        log__.debug("******保存先ディレクトリパス:" + newDir);

        File oldFileDir = new File(oldDir);
        File newFileDir = new File(newDir);

        if (IOTools.isDirCheck(newDir, false)) {
            //ディレクトリが存在する場合
            //削除
            IOTools.deleteDir(newDir);
        }

        //ディレクトリ存在チェック(なければ作成)
        IOTools.isDirCheck(newDir, true);

        //添付ファイルをコピー
        IOTools.copyDir(oldFileDir, newFileDir);

        //ディレクトリを削除
        IOTools.deleteDir(oldDir);
    }

    /**
     * <br>[機  能] ディレクトリを削除する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param deleteDir 削除ディレクトリパス
     * @throws IOToolsException ファイルアクセス時例外
     */
    public void deleteDir(String deleteDir)
    throws IOToolsException {

        File delDir = new File(deleteDir);

        //添付ファイルを削除
        IOTools.deleteDir(delDir);
    }
}