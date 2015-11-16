package jp.groupsession.v2.wml.wml041;

import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.wml.model.base.WmlAccountSignModel;
import jp.groupsession.v2.wml.wml040.Wml040Biz;
import jp.groupsession.v2.wml.wml040.Wml040SignModel;

/**
 * <br>[機  能] WEBメール アカウント 署名登録(ポップアップ)画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml041Biz {

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param tempRootDir テンポラリルートディレクトリ
     * @param paramMdl パラメータ情報
     * @throws IOToolsException 署名情報の読み込みに失敗
     */
    public  void setInitData(RequestModel reqMdl, String tempRootDir,
                                    Wml041ParamModel paramMdl)
    throws IOToolsException {

        if (paramMdl.getWml041initFlg() != 1) {
            paramMdl.setWml041initFlg(1);

            Wml040Biz biz040 = new Wml040Biz();
            Wml040SignModel signData = biz040.loadSignModel(reqMdl, tempRootDir);
            if (paramMdl.getWml041mode() == Wml041Form.MODE_EDIT) {
                int signNo = paramMdl.getWml041No();
                WmlAccountSignModel signDetailData = signData.getSignList().get(signNo - 1);
                paramMdl.setWml041title(signDetailData.getWsiTitle());
                paramMdl.setWml041sign(signDetailData.getWsiSign());
            } else {
                paramMdl.setWml041No(signData.getSignList().size() + 1);
            }
        }
    }


    /**
     * <br>[機  能] 署名情報を登録する
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param tempRootDir テンポラリルートディレクトリ
     * @param paramMdl パラメータ情報
     * @throws IOToolsException 署名情報の保存に失敗
     */
    public  void saveSignData(RequestModel reqMdl, String tempRootDir,
                                            Wml041ParamModel paramMdl)
    throws IOToolsException {

        int signNo = paramMdl.getWml041No();
        WmlAccountSignModel signDetailData = new WmlAccountSignModel();
        signDetailData.setWsiNo(signNo);
        signDetailData.setWsiTitle(paramMdl.getWml041title());
        signDetailData.setWsiSign(paramMdl.getWml041sign());

        Wml040Biz biz040 = new Wml040Biz();
        if (paramMdl.getWml041mode() == Wml041Form.MODE_EDIT) {
            biz040.editSignModel(reqMdl, tempRootDir, signDetailData, signNo);
        } else {
            biz040.addSignModel(reqMdl, tempRootDir, signDetailData);
        }
    }
}
