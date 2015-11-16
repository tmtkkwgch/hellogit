package jp.groupsession.v2.wml.wml160;

import jp.co.sjts.util.io.IOTools;
import jp.groupsession.v2.wml.biz.WmlBiz;

/**
 * <br>[機  能] WEBメール アカウントインポート画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml160Biz {

    /**
     * <br>[機  能] 初期表示情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param tempDir テンポラリディレクトリパス
     * @throws Exception 実行例外
     */
    public void getInitData(Wml160ParamModel paramMdl, String tempDir)
    throws Exception {

        //初期表示の場合、テンポラリディレクトリを削除する
        if (paramMdl.getWml160initFlg() != 1) {
            IOTools.deleteDir(tempDir);
            paramMdl.setWml160initFlg(1);
        }

        //取込みファイルコンボを設定
        WmlBiz wmlBiz = new WmlBiz();
        paramMdl.setWml160FileLabelList(wmlBiz.getFileCombo(tempDir));
    }
}
