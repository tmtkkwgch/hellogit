package jp.groupsession.v2.ntp.ntp091;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.groupsession.v2.ntp.GSConstNippou;
import jp.groupsession.v2.ntp.ntp090.Ntp090Form;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 日報 初期値設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ntp091Form extends Ntp090Form {

    /** 開始 時 */
    private int ntp091DefFrH__ = -1;
    /** 開始 分 */
    private int ntp091DefFrM__ = -1;
    /** 終了 時 */
    private int ntp091DefToH__ = -1;
    /** 終了 分 */
    private int ntp091DefToM__ = -1;
    /** ラベル 時 */
    private List < LabelValueBean > ntp091HourLabel__ = null;
    /** ラベル 分 */
    private List < LabelValueBean > ntp091MinuteLabel__ = null;

    /** 公開フラグ */
    private int ntp091PubFlg__ = GSConstNippou.DSP_PUBLIC;

    /** タイトル色 */
    private String ntp091Fcolor__ = null;

    /** 編集権限 */
    private int ntp091Edit__ = GSConstNippou.EDIT_CONF_NONE;

    /** カラーコメントリスト */
    private ArrayList < String > ntp091ColorMsgList__ = null;

    /** 業務内容 */
    private int ntp091DefGyomu__ = -1;
    /** ラベル 業務内容 */
    private List < LabelValueBean > ntp091GyomuLabel__ = null;

    /** グループSID */
    private int ntp091groupSid__ = -1;
    /** ラベル グループ一覧 */
    private List<LabelValueBean> ntp091GroupList__ = null;

    /** 追加用ユーザ一覧 */
    private List<LabelValueBean> ntp091LeftUserList__ = null;
    /** 追加済みユーザ一覧 */
    private List<LabelValueBean> ntp091RightUserList__ = null;

    /** 追加用ユーザ(選択) */
    private String[] ntp091SelectLeftUser__ = null;
    /** 追加済みユーザ(選択) */
    private String[] ntp091SelectRightUser__ = null;
    /** パラメータ保持用　追加済みユーザSID */
    private String[] ntp091userSid__ = null;

    /**
     * <p>ntp091ColorMsgList を取得します。
     * @return ntp091ColorMsgList
     */
    public ArrayList<String> getNtp091ColorMsgList() {
        return ntp091ColorMsgList__;
    }

    /**
     * <p>ntp091ColorMsgList をセットします。
     * @param ntp091ColorMsgList ntp091ColorMsgList
     */
    public void setNtp091ColorMsgList(ArrayList<String> ntp091ColorMsgList) {
        ntp091ColorMsgList__ = ntp091ColorMsgList;
    }

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     */
    public Ntp091Form() {

//        //ラベル(時)
//        ntp091HourLabel__ = new ArrayList<LabelValueBean>();
//        for (int i = 0; i < 24; i++) {
//            ntp091HourLabel__.add(new LabelValueBean(i + "時", Integer.toString(i)));
//        }
//
//        //ラベル(分)
//        ntp091MinuteLabel__ = new ArrayList<LabelValueBean>();
//        for (int i = 0; i < 60; i += GSConstNippou.HOUR_MEMORI_COUNT) {
//            ntp091MinuteLabel__.add(new LabelValueBean(i + "分", Integer.toString(i)));
//        }
    }

    /**
     * <p>ntp091Edit を取得します。
     * @return ntp091Edit
     */
    public int getNtp091Edit() {
        return ntp091Edit__;
    }

    /**
     * <p>ntp091Edit をセットします。
     * @param ntp091Edit ntp091Edit
     */
    public void setNtp091Edit(int ntp091Edit) {
        ntp091Edit__ = ntp091Edit;
    }

    /**
     * <p>ntp091Fcolor を取得します。
     * @return ntp091Fcolor
     */
    public String getNtp091Fcolor() {
        return ntp091Fcolor__;
    }

    /**
     * <p>ntp091Fcolor をセットします。
     * @param ntp091Fcolor ntp091Fcolor
     */
    public void setNtp091Fcolor(String ntp091Fcolor) {
        ntp091Fcolor__ = ntp091Fcolor;
    }

    /**
     * <p>ntp091DefFrH を取得します。
     * @return ntp091DefFrH
     */
    public int getNtp091DefFrH() {
        return ntp091DefFrH__;
    }

    /**
     * <p>ntp091DefFrH をセットします。
     * @param ntp091DefFrH ntp091DefFrH
     */
    public void setNtp091DefFrH(int ntp091DefFrH) {
        ntp091DefFrH__ = ntp091DefFrH;
    }

    /**
     * <p>ntp091DefFrM を取得します。
     * @return ntp091DefFrM
     */
    public int getNtp091DefFrM() {
        return ntp091DefFrM__;
    }

    /**
     * <p>ntp091DefFrM をセットします。
     * @param ntp091DefFrM ntp091DefFrM
     */
    public void setNtp091DefFrM(int ntp091DefFrM) {
        ntp091DefFrM__ = ntp091DefFrM;
    }

    /**
     * <p>ntp091DefToH を取得します。
     * @return ntp091DefToH
     */
    public int getNtp091DefToH() {
        return ntp091DefToH__;
    }

    /**
     * <p>ntp091DefToH をセットします。
     * @param ntp091DefToH ntp091DefToH
     */
    public void setNtp091DefToH(int ntp091DefToH) {
        ntp091DefToH__ = ntp091DefToH;
    }

    /**
     * <p>ntp091DefToM を取得します。
     * @return ntp091DefToM
     */
    public int getNtp091DefToM() {
        return ntp091DefToM__;
    }

    /**
     * <p>ntp091DefToM をセットします。
     * @param ntp091DefToM ntp091DefToM
     */
    public void setNtp091DefToM(int ntp091DefToM) {
        ntp091DefToM__ = ntp091DefToM;
    }

    /**
     * <p>ntp091HourLabel を取得します。
     * @return ntp091HourLabel
     */
    public List<LabelValueBean> getNtp091HourLabel() {
        return ntp091HourLabel__;
    }

    /**
     * <p>ntp091HourLabel をセットします。
     * @param ntp091HourLabel ntp091HourLabel
     */
    public void setNtp091HourLabel(List<LabelValueBean> ntp091HourLabel) {
        ntp091HourLabel__ = ntp091HourLabel;
    }

    /**
     * <p>ntp091MinuteLabel を取得します。
     * @return ntp091MinuteLabel
     */
    public List<LabelValueBean> getNtp091MinuteLabel() {
        return ntp091MinuteLabel__;
    }

    /**
     * <p>ntp091MinuteLabel をセットします。
     * @param ntp091MinuteLabel ntp091MinuteLabel
     */
    public void setNtp091MinuteLabel(List<LabelValueBean> ntp091MinuteLabel) {
        ntp091MinuteLabel__ = ntp091MinuteLabel;
    }

    /**
     * <p>ntp091PubFlg を取得します。
     * @return ntp091PubFlg
     */
    public int getNtp091PubFlg() {
        return ntp091PubFlg__;
    }

    /**
     * <p>ntp091PubFlg をセットします。
     * @param ntp091PubFlg ntp091PubFlg
     */
    public void setNtp091PubFlg(int ntp091PubFlg) {
        ntp091PubFlg__ = ntp091PubFlg;
    }

    /**
     * <p>ntp091DefGyomu を取得します。
     * @return ntp091DefGyomu
     */
    public int getNtp091DefGyomu() {
        return ntp091DefGyomu__;
    }

    /**
     * <p>ntp091DefGyomu をセットします。
     * @param ntp091DefGyomu ntp091DefGyomu
     */
    public void setNtp091DefGyomu(int ntp091DefGyomu) {
        ntp091DefGyomu__ = ntp091DefGyomu;
    }

    /**
     * <p>ntp091GyomuLabel を取得します。
     * @return ntp091GyomuLabel
     */
    public List<LabelValueBean> getNtp091GyomuLabel() {
        return ntp091GyomuLabel__;
    }

    /**
     * <p>ntp091GyomuLabel をセットします。
     * @param ntp091GyomuLabel ntp091GyomuLabel
     */
    public void setNtp091GyomuLabel(List<LabelValueBean> ntp091GyomuLabel) {
        ntp091GyomuLabel__ = ntp091GyomuLabel;
    }

    /**
     * <p>ntp091GroupList を取得します。
     * @return ntp091GroupList
     */
    public List<LabelValueBean> getNtp091GroupList() {
        return ntp091GroupList__;
    }

    /**
     * <p>ntp091GroupList をセットします。
     * @param ntp091GroupList ntp091GroupList
     */
    public void setNtp091GroupList(List<LabelValueBean> ntp091GroupList) {
        ntp091GroupList__ = ntp091GroupList;
    }

    /**
     * <p>ntp091groupSid を取得します。
     * @return ntp091groupSid
     */
    public int getNtp091groupSid() {
        return ntp091groupSid__;
    }

    /**
     * <p>ntp091groupSid をセットします。
     * @param ntp091groupSid ntp091groupSid
     */
    public void setNtp091groupSid(int ntp091groupSid) {
        ntp091groupSid__ = ntp091groupSid;
    }

    /**
     * <p>ntp091LeftUserList を取得します。
     * @return ntp091LeftUserList
     */
    public List<LabelValueBean> getNtp091LeftUserList() {
        return ntp091LeftUserList__;
    }

    /**
     * <p>ntp091LeftUserList をセットします。
     * @param ntp091LeftUserList ntp091LeftUserList
     */
    public void setNtp091LeftUserList(List<LabelValueBean> ntp091LeftUserList) {
        ntp091LeftUserList__ = ntp091LeftUserList;
    }

    /**
     * <p>ntp091RightUserList を取得します。
     * @return ntp091RightUserList
     */
    public List<LabelValueBean> getNtp091RightUserList() {
        return ntp091RightUserList__;
    }

    /**
     * <p>ntp091RightUserList をセットします。
     * @param ntp091RightUserList ntp091RightUserList
     */
    public void setNtp091RightUserList(List<LabelValueBean> ntp091RightUserList) {
        ntp091RightUserList__ = ntp091RightUserList;
    }

    /**
     * <p>ntp091SelectLeftUser を取得します。
     * @return ntp091SelectLeftUser
     */
    public String[] getNtp091SelectLeftUser() {
        return ntp091SelectLeftUser__;
    }

    /**
     * <p>ntp091SelectLeftUser をセットします。
     * @param ntp091SelectLeftUser ntp091SelectLeftUser
     */
    public void setNtp091SelectLeftUser(String[] ntp091SelectLeftUser) {
        ntp091SelectLeftUser__ = ntp091SelectLeftUser;
    }

    /**
     * <p>ntp091SelectRightUser を取得します。
     * @return ntp091SelectRightUser
     */
    public String[] getNtp091SelectRightUser() {
        return ntp091SelectRightUser__;
    }

    /**
     * <p>ntp091SelectRightUser をセットします。
     * @param ntp091SelectRightUser ntp091SelectRightUser
     */
    public void setNtp091SelectRightUser(String[] ntp091SelectRightUser) {
        ntp091SelectRightUser__ = ntp091SelectRightUser;
    }

    /**
     * <p>ntp091userSid を取得します。
     * @return ntp091userSid
     */
    public String[] getNtp091userSid() {
        return ntp091userSid__;
    }

    /**
     * <p>ntp091userSid をセットします。
     * @param ntp091userSid ntp091userSid
     */
    public void setNtp091userSid(String[] ntp091userSid) {
        ntp091userSid__ = ntp091userSid;
    }

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param req リクエスト
     * @return エラー
     */
    public ActionErrors validateCheckNtp091(ActionMapping map, HttpServletRequest req) {
        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;

        //時間
        if (ntp091DefFrH__ > ntp091DefToH__
                || (ntp091DefFrH__ == ntp091DefToH__ && ntp091DefFrM__ >= ntp091DefToM__)) {
            String prefix = "ntp091DefFrH__";
            msg = new ActionMessage("error.input.comp.text", "時間", "開始＜終了");
            errors.add(prefix + "error.input.comp.text", msg);
        }

//        //公開フラグ
//        if (ntp091PubFlg__ != GSConstNippou.DSP_PUBLIC
//                && ntp091PubFlg__ != GSConstNippou.DSP_NOT_PUBLIC
//                && ntp091PubFlg__ != GSConstNippou.DSP_YOTEIARI) {
//            String prefix = "ntp091PubFlg__";
//            msg = new ActionMessage("error.input.notvalidate.data", "公開");
//            errors.add(prefix + "error.input.comp.text", msg);
//        }
//
//        //業務内容
//        if (ntp091DefGyomu__ < 0) {
//            String prefix = "ntp091PubFlg__";
//            msg = new ActionMessage("error.select.required.text", "業務内容");
//            errors.add(prefix + "error.input.comp.text", msg);
//        }

        return errors;
    }
}
