package jp.groupsession.v2.zsk.maingrp;

import jp.groupsession.v2.sch.model.SchDataModel;

/**
 * <br>[機  能] 在席管理(メイン画面表示用 メンバー) スケジュール情報を格納するクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ZskMaingrpModel extends SchDataModel {

    /** スケジュール開始～終了(表示用) */
    private String scdFrToDateDsp__;

    /**
     * <p>scdFrToDateDsp を取得します。
     * @return scdFrToDateDsp
     */
    public String getScdFrToDateDsp() {
        return scdFrToDateDsp__;
    }

    /**
     * <p>scdFrToDateDsp をセットします。
     * @param scdFrToDateDsp scdFrToDateDsp
     */
    public void setScdFrToDateDsp(String scdFrToDateDsp) {
        scdFrToDateDsp__ = scdFrToDateDsp;
    }
}
