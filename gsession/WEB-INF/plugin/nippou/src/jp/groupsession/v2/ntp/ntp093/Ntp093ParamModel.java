package jp.groupsession.v2.ntp.ntp093;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.ntp.GSConstNippou;
import jp.groupsession.v2.ntp.model.NtpLabelValueModel;
import jp.groupsession.v2.ntp.ntp090.Ntp090ParamModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 日報 グループメンバー表示設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ntp093ParamModel extends Ntp090ParamModel {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Ntp093ParamModel.class);

    /** ソートキー1 */
    private int ntp093SortKey1__ = -1;
    /** ソートキー1オーダー */
    private int ntp093SortOrder1__ = -1;
    /** ソートキー2 */
    private int ntp093SortKey2__ = -1;
    /** ソートキー2オーダー */
    private int ntp093SortOrder2__ = -1;
    /** ソートキー ラベル */
    private List<LabelValueBean> ntp093SortKeyLabel__ = null;

    /** デフォルト表示グループ */
    private String ntp093DefGroup__ = null;
    /** デフォルト表示グループ ラベル */
    private List<NtpLabelValueModel> ntp093GroupLabel__ = null;

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     */
    public Ntp093ParamModel() {
        //ソートキーラベル
        ArrayList<LabelValueBean> sortLabel = new ArrayList<LabelValueBean>();
        for (int i = 0; i < GSConstNippou.SORT_KEY_ALL.length; i++) {
            String label = GSConstNippou.SORT_KEY_ALL_TEXT[i];
            String value = Integer.toString(GSConstNippou.SORT_KEY_ALL[i]);
            sortLabel.add(new LabelValueBean(label, value));
        }
        ntp093SortKeyLabel__ = sortLabel;
    }

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param usrSid ユーザSID
     * @param con コネクション
     * @return エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateCheck(Connection con, int usrSid) throws SQLException {

        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;

        //メンバー表示順：第1キー
        boolean key1Flg = false;
        for (int key : GSConstNippou.SORT_KEY_ALL) {
            if (ntp093SortKey1__ == key) {
                key1Flg = true;
                break;
            }
        }
        if (!key1Flg) {
            msg = new ActionMessage("error.select3.required.text", "メンバー表示順：第1キー");
            errors.add("ntp093SortKey1" + "error.select3.required.text", msg);
        }

        log__.debug("ntp093SortOrder1__ = " + ntp093SortOrder1__);
        //メンバー表示順：第1キー 昇順・降順
        if (ntp093SortOrder1__ != GSConst.ORDER_KEY_ASC
        && ntp093SortOrder1__ != GSConst.ORDER_KEY_DESC) {
            msg = new ActionMessage(
                    "error.select3.required.text", "メンバー表示順：第1キー 昇順・降順");
            errors.add("ntp093SortOrder1" + "error.select3.required.text", msg);
        }

        //メンバー表示順：第2キー
        boolean key2Flg = false;
        for (int key : GSConstNippou.SORT_KEY_ALL) {
            if (ntp093SortKey2__ == key) {
                key2Flg = true;
                break;
            }
        }
        if (!key2Flg) {
            msg = new ActionMessage("error.select3.required.text", "メンバー表示順：第2キー");
            errors.add("ntp093SortKey2" + "error.select3.required.text", msg);
        }

        log__.debug("ntp093SortOrder2__ = " + ntp093SortOrder2__);

        //メンバー表示順：第1キー 昇順・降順
        if (ntp093SortOrder2__ != GSConst.ORDER_KEY_ASC
        && ntp093SortOrder2__ != GSConst.ORDER_KEY_DESC) {
            msg = new ActionMessage(
                    "error.select3.required.text", "メンバー表示順：第2キー 昇順・降順");
            errors.add("ntp093SortOrder2" + "error.select3.required.text", msg);
        }

//        //デフォルト表示グループ
//        boolean groupFlg = false;
//        UsidSelectGrpNameDao gpDao = new UsidSelectGrpNameDao(con);
//        ArrayList<GroupModel> gpList = gpDao.selectGroupNmListOrderbyClass(usrSid);
//        for (GroupModel gmodel : gpList) {
//            if (ntp093DefGroup__ == gmodel.getGroupSid()) {
//                groupFlg = true;
//                break;
//            }
//        }
//        if (!groupFlg) {
//            msg = new ActionMessage("error.select3.required.text", "デフォルト表示グループ");
//            errors.add("ntp093DefGroup" + "error.select3.required.text", msg);
//        }

        return errors;
    }

    /**
     * <p>ntp093DefGroup を取得します。
     * @return ntp093DefGroup
     */
    public String getNtp093DefGroup() {
        return ntp093DefGroup__;
    }

    /**
     * <p>ntp093DefGroup をセットします。
     * @param ntp093DefGroup ntp093DefGroup
     */
    public void setNtp093DefGroup(String ntp093DefGroup) {
        ntp093DefGroup__ = ntp093DefGroup;
    }

    /**
     * <p>ntp093GroupLabel を取得します。
     * @return ntp093GroupLabel
     */
    public List<NtpLabelValueModel> getNtp093GroupLabel() {
        return ntp093GroupLabel__;
    }

    /**
     * <p>ntp093GroupLabel をセットします。
     * @param ntp093GroupLabel ntp093GroupLabel
     */
    public void setNtp093GroupLabel(List<NtpLabelValueModel> ntp093GroupLabel) {
        ntp093GroupLabel__ = ntp093GroupLabel;
    }

    /**
     * <p>ntp093SortKey1 を取得します。
     * @return ntp093SortKey1
     */
    public int getNtp093SortKey1() {
        return ntp093SortKey1__;
    }

    /**
     * <p>ntp093SortKey1 をセットします。
     * @param ntp093SortKey1 ntp093SortKey1
     */
    public void setNtp093SortKey1(int ntp093SortKey1) {
        ntp093SortKey1__ = ntp093SortKey1;
    }

    /**
     * <p>ntp093SortKey2 を取得します。
     * @return ntp093SortKey2
     */
    public int getNtp093SortKey2() {
        return ntp093SortKey2__;
    }

    /**
     * <p>ntp093SortKey2 をセットします。
     * @param ntp093SortKey2 ntp093SortKey2
     */
    public void setNtp093SortKey2(int ntp093SortKey2) {
        ntp093SortKey2__ = ntp093SortKey2;
    }

    /**
     * <p>ntp093SortKeyLabel を取得します。
     * @return ntp093SortKeyLabel
     */
    public List<LabelValueBean> getNtp093SortKeyLabel() {
        return ntp093SortKeyLabel__;
    }

    /**
     * <p>ntp093SortKeyLabel をセットします。
     * @param ntp093SortKeyLabel ntp093SortKeyLabel
     */
    public void setNtp093SortKeyLabel(List<LabelValueBean> ntp093SortKeyLabel) {
        ntp093SortKeyLabel__ = ntp093SortKeyLabel;
    }

    /**
     * <p>ntp093SortOrder1 を取得します。
     * @return ntp093SortOrder1
     */
    public int getNtp093SortOrder1() {
        return ntp093SortOrder1__;
    }

    /**
     * <p>ntp093SortOrder1 をセットします。
     * @param ntp093SortOrder1 ntp093SortOrder1
     */
    public void setNtp093SortOrder1(int ntp093SortOrder1) {
        ntp093SortOrder1__ = ntp093SortOrder1;
    }

    /**
     * <p>ntp093SortOrder2 を取得します。
     * @return ntp093SortOrder2
     */
    public int getNtp093SortOrder2() {
        return ntp093SortOrder2__;
    }

    /**
     * <p>ntp093SortOrder2 をセットします。
     * @param ntp093SortOrder2 ntp093SortOrder2
     */
    public void setNtp093SortOrder2(int ntp093SortOrder2) {
        ntp093SortOrder2__ = ntp093SortOrder2;
    }

}
