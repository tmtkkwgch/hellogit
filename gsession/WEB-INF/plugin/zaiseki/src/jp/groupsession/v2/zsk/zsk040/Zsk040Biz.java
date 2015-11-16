package jp.groupsession.v2.zsk.zsk040;

import java.io.IOException;
import java.sql.Connection;

import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.cmn.biz.CommonBiz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 在席管理 座席表登録画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Zsk040Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Zsk040Biz.class);

    /**
     * <br>[機  能] テンポラリディレクトリのファイル削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param tempDir テンポラリディレクトリパス
     * @throws IOToolsException ファイルアクセス時例外
     */
    public void doDeleteFile(String tempDir) throws IOToolsException {

        //テンポラリディレクトリのファイルを削除する
        IOTools.deleteDir(tempDir);
        log__.debug("テンポラリディレクトリのファイル削除");
    }

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説] 処理モード = 編集の場合、スレッド情報を設定する
     * <br>[備  考]
     * @param paramMdl Zsk040ParamModel
     * @param con コネクション
     * @param appRoot アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリパス
     * @throws IOException 添付ファイルの操作に失敗
     * @throws IOToolsException 添付ファイルの操作に失敗
     */
    public void setInitData(Zsk040ParamModel paramMdl, Connection con,
                            String appRoot, String tempDir)
    throws IOException, IOToolsException {
        log__.debug("START");

        //添付ファイル一覧を設定
        CommonBiz cmnBiz = new CommonBiz();
        paramMdl.setZsk040FileLabelList(cmnBiz.getTempFileLabelList(tempDir));

        log__.debug("End");
    }
}
