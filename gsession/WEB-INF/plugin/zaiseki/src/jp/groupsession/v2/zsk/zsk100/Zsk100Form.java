package jp.groupsession.v2.zsk.zsk100;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.sch.model.SchLabelValueModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.zsk.GSConstZaiseki;
import jp.groupsession.v2.zsk.zsk070.Zsk070Form;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 在席管理 個人設定 メイン画面メンバー表示設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Zsk100Form extends Zsk070Form {

    /** メイン画面表示フラグ */
    private int zsk100maingrpDspFlg__ = -1;

    /** 表示グループSID */
    private String zsk100DspGpSid__;
    /** グループコンボ */
    private List<SchLabelValueModel> zsk100GpLabelList__ = null;
    /** 画面遷移モード */
    private int zsk100Mode__ = GSConstZaiseki.MODE_ZAISEKI;

    /** ソートキー1 */
    private int zsk100SortKey1__ = GSConstZaiseki.SORT_KEY_NAME;
    /** ソートキー1オーダー */
    private int zsk100SortOrder1__ = GSConst.ORDER_KEY_ASC;
    /** ソートキー2 */
    private int zsk100SortKey2__ = GSConstZaiseki.SORT_KEY_NAME;
    /** ソートキー2オーダー */
    private int zsk100SortOrder2__ = GSConst.ORDER_KEY_ASC;
    /** ソートキー ラベル */
    private List<LabelValueBean> zsk100SortKeyLabel__ = null;
    /** ソートキー1 表示用 */
    private String zsk100SortKey1Name__;
    /** ソートキー2 表示用 */
    private String zsk100SortKey2Name__;

    /** メイン画面の在席管理メンバー スケジュール表示区分 デフォルト値 */
    private int zsk100SchViewDf__ = GSConstZaiseki.MAIN_SCH_DSP;

    /**
     * <p>zsk100SchViewDf を取得します。
     * @return zsk100SchViewDf
     */
    public int getZsk100SchViewDf() {
        return zsk100SchViewDf__;
    }

    /**
     * <p>zsk100SchViewDf をセットします。
     * @param zsk100SchViewDf zsk100SchViewDf
     */
    public void setZsk100SchViewDf(int zsk100SchViewDf) {
        zsk100SchViewDf__ = zsk100SchViewDf;
    }

    /**
     * <p>zsk100SortKey1Name を取得します。
     * @return zsk100SortKey1Name
     */
    public String getZsk100SortKey1Name() {
        return zsk100SortKey1Name__;
    }

    /**
     * <p>zsk100SortKey1Name をセットします。
     * @param zsk100SortKey1Name zsk100SortKey1Name
     */
    public void setZsk100SortKey1Name(String zsk100SortKey1Name) {
        zsk100SortKey1Name__ = zsk100SortKey1Name;
    }

    /**
     * <p>zsk100SortKey2Name を取得します。
     * @return zsk100SortKey2Name
     */
    public String getZsk100SortKey2Name() {
        return zsk100SortKey2Name__;
    }

    /**
     * <p>zsk100SortKey2Name をセットします。
     * @param zsk100SortKey2Name zsk100SortKey2Name
     */
    public void setZsk100SortKey2Name(String zsk100SortKey2Name) {
        zsk100SortKey2Name__ = zsk100SortKey2Name;
    }

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     */
    public Zsk100Form(HttpServletRequest req) {
        GsMessage gsMsg = new GsMessage();
        String name = gsMsg.getMessage(req, "cmn.name");
        String number = gsMsg.getMessage(req, "cmn.employee.staff.number");
        String post = gsMsg.getMessage(req, "cmn.post");
        String birth = gsMsg.getMessage(req, "cmn.birthday");
        String sortkey1 = gsMsg.getMessage("cmn.sortkey") + "1";
        String sortkey2 = gsMsg.getMessage("cmn.sortkey") + "2";

       String[] sortAllText = {name, number, post, birth,
               sortkey1, sortkey2};

        //ソートキーラベル
        ArrayList<LabelValueBean> sortLabel = new ArrayList<LabelValueBean>();
        for (int i = 0; i < sortAllText.length; i++) {
            String label = sortAllText[i];
            String value = Integer.toString(GSConstZaiseki.SORT_KEY_ALL[i]);
            sortLabel.add(new LabelValueBean(label, value));
        }
        zsk100SortKeyLabel__ = sortLabel;
    }

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     */
    public Zsk100Form() {
    }

    /**
     * <p>zsk100DspGpSid を取得します。
     * @return zsk100DspGpSid
     */
    public String getZsk100DspGpSid() {
        return zsk100DspGpSid__;
    }

    /**
     * <p>zsk100DspGpSid をセットします。
     * @param zsk100DspGpSid zsk100DspGpSid
     */
    public void setZsk100DspGpSid(String zsk100DspGpSid) {
        zsk100DspGpSid__ = zsk100DspGpSid;
    }

    /**
     * <p>zsk100GpLabelList を取得します。
     * @return zsk100GpLabelList
     */
    public List<SchLabelValueModel> getZsk100GpLabelList() {
        return zsk100GpLabelList__;
    }

    /**
     * <p>zsk100GpLabelList をセットします。
     * @param zsk100GpLabelList zsk100GpLabelList
     */
    public void setZsk100GpLabelList(List<SchLabelValueModel> zsk100GpLabelList) {
        zsk100GpLabelList__ = zsk100GpLabelList;
    }

    /**
     * <p>zsk100Mode を取得します。
     * @return zsk100Mode
     */
    public int getZsk100Mode() {
        return zsk100Mode__;
    }

    /**
     * <p>zsk100Mode をセットします。
     * @param zsk100Mode zsk100Mode
     */
    public void setZsk100Mode(int zsk100Mode) {
        zsk100Mode__ = zsk100Mode;
    }

    /**
     * <p>zsk100SortKey1 を取得します。
     * @return zsk100SortKey1
     */
    public int getZsk100SortKey1() {
        return zsk100SortKey1__;
    }

    /**
     * <p>zsk100SortKey1 をセットします。
     * @param zsk100SortKey1 zsk100SortKey1
     */
    public void setZsk100SortKey1(int zsk100SortKey1) {
        zsk100SortKey1__ = zsk100SortKey1;
    }

    /**
     * <p>zsk100SortKey2 を取得します。
     * @return zsk100SortKey2
     */
    public int getZsk100SortKey2() {
        return zsk100SortKey2__;
    }

    /**
     * <p>zsk100SortKey2 をセットします。
     * @param zsk100SortKey2 zsk100SortKey2
     */
    public void setZsk100SortKey2(int zsk100SortKey2) {
        zsk100SortKey2__ = zsk100SortKey2;
    }

    /**
     * <p>zsk100SortKeyLabel を取得します。
     * @return zsk100SortKeyLabel
     */
    public List<LabelValueBean> getZsk100SortKeyLabel() {
        return zsk100SortKeyLabel__;
    }

    /**
     * <p>zsk100SortKeyLabel をセットします。
     * @param zsk100SortKeyLabel zsk100SortKeyLabel
     */
    public void setZsk100SortKeyLabel(List<LabelValueBean> zsk100SortKeyLabel) {
        zsk100SortKeyLabel__ = zsk100SortKeyLabel;
    }

    /**
     * <p>zsk100SortOrder1 を取得します。
     * @return zsk100SortOrder1
     */
    public int getZsk100SortOrder1() {
        return zsk100SortOrder1__;
    }

    /**
     * <p>zsk100SortOrder1 をセットします。
     * @param zsk100SortOrder1 zsk100SortOrder1
     */
    public void setZsk100SortOrder1(int zsk100SortOrder1) {
        zsk100SortOrder1__ = zsk100SortOrder1;
    }

    /**
     * <p>zsk100SortOrder2 を取得します。
     * @return zsk100SortOrder2
     */
    public int getZsk100SortOrder2() {
        return zsk100SortOrder2__;
    }

    /**
     * <p>zsk100SortOrder2 をセットします。
     * @param zsk100SortOrder2 zsk100SortOrder2
     */
    public void setZsk100SortOrder2(int zsk100SortOrder2) {
        zsk100SortOrder2__ = zsk100SortOrder2;
    }

    /**
     * <p>zsk100maingrpDspFlg を取得します。
     * @return zsk100maingrpDspFlg
     */
    public int getZsk100maingrpDspFlg() {
        return zsk100maingrpDspFlg__;
    }

    /**
     * <p>zsk100maingrpDspFlg をセットします。
     * @param zsk100maingrpDspFlg zsk100maingrpDspFlg
     */
    public void setZsk100maingrpDspFlg(int zsk100maingrpDspFlg) {
        zsk100maingrpDspFlg__ = zsk100maingrpDspFlg;
    }
}
