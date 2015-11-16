package jp.groupsession.v2.sch.sch093;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.sch.model.SchLabelValueModel;
import jp.groupsession.v2.sch.sch100.Sch100ParamModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] スケジュール 個人設定 グループメンバー表示設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sch093ParamModel extends Sch100ParamModel {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sch093ParamModel.class);

    /** ソートキー1 */
    private int sch093SortKey1__ = -1;
    /** ソートキー1オーダー */
    private int sch093SortOrder1__ = -1;
    /** ソートキー2 */
    private int sch093SortKey2__ = -1;
    /** ソートキー2オーダー */
    private int sch093SortOrder2__ = -1;
    /** ソートキー ラベル */
    private List<LabelValueBean> sch093SortKeyLabel__ = null;

    /** デフォルト表示グループ */
    private String sch093DefGroup__ = null;
    /** デフォルト表示グループ ラベル */
    private List<SchLabelValueModel> sch093GroupLabel__ = null;
    /** デフォルト表示グループ 個別設定フラグ */
    private int sch093DefGroupFlg__ = 0;

    /** メンバー表示順設定フラグ */
    private int sch093MemDspConfFlg__ = -1;
    /** 表示用ソートキー1 名前 */
    private String sch093DspSortKey1__ = null;
    /** 表示用ソートキー1 オーダー */
    private String sch093DspSortOrder1__ = null;
    /** 表示用ソートキー2 名前 */
    private String sch093DspSortKey2__ = null;
    /** 表示用ソートキー2 オーダー */
    private String sch093DspSortOrder2__ = null;

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     */
    public Sch093ParamModel() {
    }

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param usrSid ユーザSID
     * @param con コネクション
     * @param req リクエスト
     * @return エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateCheck(
                        Connection con,
                        int usrSid,
                        HttpServletRequest req) throws SQLException {

        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;
        GsMessage gsMsg = new GsMessage();
        //メンバー表示順：第1キー
        boolean key1Flg = false;
        for (int key : GSConstSchedule.SORT_KEY_ALL) {
            if (sch093SortKey1__ == key) {
                key1Flg = true;
                break;
            }
        }
        if (!key1Flg) {
            //メンバー表示順：第1キー
            String textMemKey1 = gsMsg.getMessage(req, "schedule.src.61");
            msg = new ActionMessage("error.select3.required.text", textMemKey1);
            errors.add("sch093SortKey1" + "error.select3.required.text", msg);
        }

        log__.debug("sch093SortOrder1__ = " + sch093SortOrder1__);
        //メンバー表示順：第1キー 昇順・降順
        if (sch093SortOrder1__ != GSConst.ORDER_KEY_ASC
        && sch093SortOrder1__ != GSConst.ORDER_KEY_DESC) {
            //メンバー表示順：第1キー 昇順・降順
            String textMemKeyAscDsc = gsMsg.getMessage(req, "schedule.src.62");
            msg = new ActionMessage(
                    "error.select3.required.text", textMemKeyAscDsc);
            errors.add("sch093SortOrder1" + "error.select3.required.text", msg);
        }

        //メンバー表示順：第2キー
        boolean key2Flg = false;
        for (int key : GSConstSchedule.SORT_KEY_ALL) {
            if (sch093SortKey2__ == key) {
                key2Flg = true;
                break;
            }
        }
        if (!key2Flg) {
            //メンバー表示順：第2キー
            String textMemKey2 = gsMsg.getMessage(req, "schedule.src.63");
            msg = new ActionMessage("error.select3.required.text", textMemKey2);
            errors.add("sch093SortKey2" + "error.select3.required.text", msg);
        }

        log__.debug("sch093SortOrder2__ = " + sch093SortOrder2__);

        //メンバー表示順：第2キー 昇順・降順
        if (sch093SortOrder2__ != GSConst.ORDER_KEY_ASC
        && sch093SortOrder2__ != GSConst.ORDER_KEY_DESC) {
//          メンバー表示順：第2キー 昇順・降順
            String textMemKey2AscDsc = gsMsg.getMessage(req, "schedule.src.64");
            msg = new ActionMessage(
                    "error.select3.required.text", textMemKey2AscDsc);
            errors.add("sch093SortOrder2" + "error.select3.required.text", msg);
        }

        return errors;
    }

    /**
     * <p>sch093DefGroup を取得します。
     * @return sch093DefGroup
     */
    public String getSch093DefGroup() {
        return sch093DefGroup__;
    }

    /**
     * <p>sch093DefGroup をセットします。
     * @param sch093DefGroup sch093DefGroup
     */
    public void setSch093DefGroup(String sch093DefGroup) {
        sch093DefGroup__ = sch093DefGroup;
    }

    /**
     * <p>sch093DefGroupFlg を取得します。
     * @return sch093DefGroupFlg
     */
    public int getSch093DefGroupFlg() {
        return sch093DefGroupFlg__;
    }

    /**
     * <p>sch093DefGroupFlg をセットします。
     * @param sch093DefGroupFlg sch093DefGroupFlg
     */
    public void setSch093DefGroupFlg(int sch093DefGroupFlg) {
        sch093DefGroupFlg__ = sch093DefGroupFlg;
    }

    /**
     * <p>sch093GroupLabel を取得します。
     * @return sch093GroupLabel
     */
    public List<SchLabelValueModel> getSch093GroupLabel() {
        return sch093GroupLabel__;
    }

    /**
     * <p>sch093GroupLabel をセットします。
     * @param sch093GroupLabel sch093GroupLabel
     */
    public void setSch093GroupLabel(List<SchLabelValueModel> sch093GroupLabel) {
        sch093GroupLabel__ = sch093GroupLabel;
    }

    /**
     * <p>sch093SortKey1 を取得します。
     * @return sch093SortKey1
     */
    public int getSch093SortKey1() {
        return sch093SortKey1__;
    }

    /**
     * <p>sch093SortKey1 をセットします。
     * @param sch093SortKey1 sch093SortKey1
     */
    public void setSch093SortKey1(int sch093SortKey1) {
        sch093SortKey1__ = sch093SortKey1;
    }

    /**
     * <p>sch093SortKey2 を取得します。
     * @return sch093SortKey2
     */
    public int getSch093SortKey2() {
        return sch093SortKey2__;
    }

    /**
     * <p>sch093SortKey2 をセットします。
     * @param sch093SortKey2 sch093SortKey2
     */
    public void setSch093SortKey2(int sch093SortKey2) {
        sch093SortKey2__ = sch093SortKey2;
    }

    /**
     * <p>sch093SortKeyLabel を取得します。
     * @return sch093SortKeyLabel
     */
    public List<LabelValueBean> getSch093SortKeyLabel() {
        return sch093SortKeyLabel__;
    }

    /**
     * <p>sch093SortKeyLabel をセットします。
     * @param sch093SortKeyLabel sch093SortKeyLabel
     */
    public void setSch093SortKeyLabel(List<LabelValueBean> sch093SortKeyLabel) {
        sch093SortKeyLabel__ = sch093SortKeyLabel;
    }

    /**
     * <p>sch093SortOrder1 を取得します。
     * @return sch093SortOrder1
     */
    public int getSch093SortOrder1() {
        return sch093SortOrder1__;
    }

    /**
     * <p>sch093SortOrder1 をセットします。
     * @param sch093SortOrder1 sch093SortOrder1
     */
    public void setSch093SortOrder1(int sch093SortOrder1) {
        sch093SortOrder1__ = sch093SortOrder1;
    }

    /**
     * <p>sch093SortOrder2 を取得します。
     * @return sch093SortOrder2
     */
    public int getSch093SortOrder2() {
        return sch093SortOrder2__;
    }

    /**
     * <p>sch093SortOrder2 をセットします。
     * @param sch093SortOrder2 sch093SortOrder2
     */
    public void setSch093SortOrder2(int sch093SortOrder2) {
        sch093SortOrder2__ = sch093SortOrder2;
    }

    /**
     * @return sch093DspSortKey1
     */
    public String getSch093DspSortKey1() {
        return sch093DspSortKey1__;
    }

    /**
     * @param sch093DspSortKey1 設定する sch093DspSortKey1
     */
    public void setSch093DspSortKey1(String sch093DspSortKey1) {
        sch093DspSortKey1__ = sch093DspSortKey1;
    }

    /**
     * @return sch093DspSortKey2
     */
    public String getSch093DspSortKey2() {
        return sch093DspSortKey2__;
    }

    /**
     * @param sch093DspSortKey2 設定する sch093DspSortKey2
     */
    public void setSch093DspSortKey2(String sch093DspSortKey2) {
        sch093DspSortKey2__ = sch093DspSortKey2;
    }

    /**
     * @return sch093DspSortOrder1
     */
    public String getSch093DspSortOrder1() {
        return sch093DspSortOrder1__;
    }

    /**
     * @param sch093DspSortOrder1 設定する sch093DspSortOrder1
     */
    public void setSch093DspSortOrder1(String sch093DspSortOrder1) {
        sch093DspSortOrder1__ = sch093DspSortOrder1;
    }

    /**
     * @return sch093DspSortOrder2
     */
    public String getSch093DspSortOrder2() {
        return sch093DspSortOrder2__;
    }

    /**
     * @param sch093DspSortOrder2 設定する sch093DspSortOrder2
     */
    public void setSch093DspSortOrder2(String sch093DspSortOrder2) {
        sch093DspSortOrder2__ = sch093DspSortOrder2;
    }

    /**
     * @return sch093MemDspConfFlg
     */
    public int getSch093MemDspConfFlg() {
        return sch093MemDspConfFlg__;
    }

    /**
     * @param sch093MemDspConfFlg 設定する sch093MemDspConfFlg
     */
    public void setSch093MemDspConfFlg(int sch093MemDspConfFlg) {
        sch093MemDspConfFlg__ = sch093MemDspConfFlg;
    }
}
