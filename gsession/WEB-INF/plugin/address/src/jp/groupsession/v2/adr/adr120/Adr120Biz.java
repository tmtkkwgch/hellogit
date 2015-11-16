package jp.groupsession.v2.adr.adr120;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.groupsession.v2.adr.biz.AddressBiz;
import jp.groupsession.v2.cmn.model.RequestModel;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] アドレス帳 会社インポート画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Adr120Biz {

    /** リクエスト情報 */
    protected RequestModel reqMdl_ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     */
    public Adr120Biz(RequestModel reqMdl) {
        reqMdl_ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Adr120ParamModel
     * @param tempDir テンポラリディレクトリパス
     * @throws Exception 実行例外
     */
    public void getInitData(Connection con, Adr120ParamModel paramMdl, String tempDir)
    throws Exception {

        AddressBiz addressBiz = new AddressBiz(reqMdl_);

        //取込みファイルコンボを設定
        paramMdl.setAdr120fileCombo(addressBiz.getFileCombo(tempDir));

        //業種コンボを設定する
        _setGyosyuCombo(con, paramMdl);

    }

    /**
     * <br>[機  能] 業種コンボを設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Adr120ParamModel
     * @throws SQLException SQL実行時例外
     */
    protected void _setGyosyuCombo(Connection con, Adr120ParamModel paramMdl) throws SQLException {

        AddressBiz addressBiz = new AddressBiz(reqMdl_);
        List<LabelValueBean> allGyosyuCombo = addressBiz.getGyosyuLabelList(con, false);

        String[] selectAtiArray = paramMdl.getAdr120atiSid();
        if (selectAtiArray == null || selectAtiArray.length <= 0) {
            paramMdl.setAdr120NoSelectAtiSidCombo(allGyosyuCombo);
            return;
        }

        List<LabelValueBean> selectGyosyuCombo = new ArrayList<LabelValueBean>();
        List<LabelValueBean> noSelectGyosyuCombo = new ArrayList<LabelValueBean>();
        Arrays.sort(selectAtiArray);
        for (LabelValueBean gyosyuLabel : allGyosyuCombo) {
            if (Arrays.binarySearch(selectAtiArray, gyosyuLabel.getValue()) >= 0) {
                selectGyosyuCombo.add(gyosyuLabel);
            } else {
                noSelectGyosyuCombo.add(gyosyuLabel);
            }
        }

        paramMdl.setAdr120selectAtiSidCombo(selectGyosyuCombo);
        paramMdl.setAdr120NoSelectAtiSidCombo(noSelectGyosyuCombo);
    }
}
