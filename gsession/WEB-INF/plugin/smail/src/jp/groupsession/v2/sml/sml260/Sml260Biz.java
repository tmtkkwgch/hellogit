package jp.groupsession.v2.sml.sml260;

import jp.groupsession.v2.sml.biz.SmlCommonBiz;



/**
 * <br>[機  能] ショートメール アカウントインポート画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sml260Biz {

    /**
     * <br>[機  能] 初期表示情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param tempDir テンポラリディレクトリパス
     * @throws Exception 実行例外
     */
    public void getInitData(Sml260ParamModel paramMdl, String tempDir)
    throws Exception {

        SmlCommonBiz sBiz = new SmlCommonBiz();

        //取込みファイルコンボを設定
        paramMdl.setSml260FileLabelList(sBiz.getFileCombo(tempDir));
    }
}
