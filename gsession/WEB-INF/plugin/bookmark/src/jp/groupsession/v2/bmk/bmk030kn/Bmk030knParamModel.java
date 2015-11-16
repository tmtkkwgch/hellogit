package jp.groupsession.v2.bmk.bmk030kn;

import java.util.List;

import jp.groupsession.v2.bmk.bmk030.Bmk030ParamModel;


/**
 * <br>[機  能] ブックマーク登録確認画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bmk030knParamModel extends Bmk030ParamModel {

    /** タイトル（表示用） */
    private List<String> bmk030knTitleDsp__;
    /** ＵＲＬ（表示用） */
    private List<String> bmk030knUrlDsp__;
    /** コメント（表示用） */
    private String bmk030knCmtDsp__;

    /**
     * <p>bmk030knCmtDsp を取得します。
     * @return bmk030knCmtDsp
     */
    public String getBmk030knCmtDsp() {
        return bmk030knCmtDsp__;
    }

    /**
     * <p>bmk030knCmtDsp をセットします。
     * @param bmk030knCmtDsp bmk030knCmtDsp
     */
    public void setBmk030knCmtDsp(String bmk030knCmtDsp) {
        bmk030knCmtDsp__ = bmk030knCmtDsp;
    }

    /**
     * <p>bmk030knTitleDsp を取得します。
     * @return bmk030knTitleDsp
     */
    public List<String> getBmk030knTitleDsp() {
        return bmk030knTitleDsp__;
    }

    /**
     * <p>bmk030knTitleDsp をセットします。
     * @param bmk030knTitleDsp bmk030knTitleDsp
     */
    public void setBmk030knTitleDsp(List<String> bmk030knTitleDsp) {
        bmk030knTitleDsp__ = bmk030knTitleDsp;
    }

    /**
     * <p>bmk030knUrlDsp を取得します。
     * @return bmk030knUrlDsp
     */
    public List<String> getBmk030knUrlDsp() {
        return bmk030knUrlDsp__;
    }

    /**
     * <p>bmk030knUrlDsp をセットします。
     * @param bmk030knUrlDsp bmk030knUrlDsp
     */
    public void setBmk030knUrlDsp(List<String> bmk030knUrlDsp) {
        bmk030knUrlDsp__ = bmk030knUrlDsp;
    }


}