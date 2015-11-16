package jp.groupsession.v2.sch.sch081;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.GSValidateUtil;
import jp.groupsession.v2.sch.model.SchColMsgModel;
import jp.groupsession.v2.sch.sch100.Sch100Form;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * <br>[機  能] スケジュール基本設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sch081Form extends Sch100Form {

    /** 共有範囲設定 */
    private String sch081Crange__ = null;
    /** スケジュール単位分設定 */
    private String sch081HourDiv__ = null;
    /** スケジュールタイトルカラー情報 */
    private ArrayList<SchColMsgModel> sch081ColorCommentList__ = null;
    /** スケジュールタイトルカラー*/
    private String sch081ColorComment1__ = null;
    /** スケジュールタイトルカラー*/
    private String sch081ColorComment2__ = null;
    /** スケジュールタイトルカラー*/
    private String sch081ColorComment3__ = null;
    /** スケジュールタイトルカラー*/
    private String sch081ColorComment4__ = null;
    /** スケジュールタイトルカラー*/
    private String sch081ColorComment5__ = null;

    /** タイトルカラー設定 */
    private int sch081colorKbn__ = GSConstSchedule.SAD_MSG_NO_ADD;

    /** スケジュールタイトルカラー*/
    private String sch081ColorComment6__ = null;
    /** スケジュールタイトルカラー*/
    private String sch081ColorComment7__ = null;
    /** スケジュールタイトルカラー*/
    private String sch081ColorComment8__ = null;
    /** スケジュールタイトルカラー*/
    private String sch081ColorComment9__ = null;
    /** スケジュールタイトルカラー*/
    private String sch081ColorComment10__ = null;

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     */
    public Sch081Form() {
    }

    /**
     * <p>sch081ColorComment1 を取得します。
     * @return sch081ColorComment1
     */
    public String getSch081ColorComment1() {
        return sch081ColorComment1__;
    }

    /**
     * <p>sch081ColorComment1 をセットします。
     * @param sch081ColorComment1 sch081ColorComment1
     */
    public void setSch081ColorComment1(String sch081ColorComment1) {
        sch081ColorComment1__ = sch081ColorComment1;
    }

    /**
     * <p>sch081ColorComment2 を取得します。
     * @return sch081ColorComment2
     */
    public String getSch081ColorComment2() {
        return sch081ColorComment2__;
    }

    /**
     * <p>sch081ColorComment2 をセットします。
     * @param sch081ColorComment2 sch081ColorComment2
     */
    public void setSch081ColorComment2(String sch081ColorComment2) {
        sch081ColorComment2__ = sch081ColorComment2;
    }

    /**
     * <p>sch081ColorComment3 を取得します。
     * @return sch081ColorComment3
     */
    public String getSch081ColorComment3() {
        return sch081ColorComment3__;
    }

    /**
     * <p>sch081ColorComment3 をセットします。
     * @param sch081ColorComment3 sch081ColorComment3
     */
    public void setSch081ColorComment3(String sch081ColorComment3) {
        sch081ColorComment3__ = sch081ColorComment3;
    }

    /**
     * <p>sch081ColorComment4 を取得します。
     * @return sch081ColorComment4
     */
    public String getSch081ColorComment4() {
        return sch081ColorComment4__;
    }

    /**
     * <p>sch081ColorComment4 をセットします。
     * @param sch081ColorComment4 sch081ColorComment4
     */
    public void setSch081ColorComment4(String sch081ColorComment4) {
        sch081ColorComment4__ = sch081ColorComment4;
    }

    /**
     * <p>sch081ColorComment5 を取得します。
     * @return sch081ColorComment5
     */
    public String getSch081ColorComment5() {
        return sch081ColorComment5__;
    }

    /**
     * <p>sch081ColorComment5 をセットします。
     * @param sch081ColorComment5 sch081ColorComment5
     */
    public void setSch081ColorComment5(String sch081ColorComment5) {
        sch081ColorComment5__ = sch081ColorComment5;
    }

    /**
     * <p>sch081ColorCommentList を取得します。
     * @return sch081ColorCommentList
     */
    public ArrayList<SchColMsgModel> getSch081ColorCommentList() {
        return sch081ColorCommentList__;
    }

    /**
     * <p>sch081ColorCommentList をセットします。
     * @param sch081ColorCommentList sch081ColorCommentList
     */
    public void setSch081ColorCommentList(
            ArrayList<SchColMsgModel> sch081ColorCommentList) {
        sch081ColorCommentList__ = sch081ColorCommentList;
    }

    /**
     * <p>sch081Crange を取得します。
     * @return sch081Crange
     */
    public String getSch081Crange() {
        return sch081Crange__;
    }

    /**
     * <p>sch081Crange をセットします。
     * @param sch081Crange sch081Crange
     */
    public void setSch081Crange(String sch081Crange) {
        sch081Crange__ = sch081Crange;
    }

    /**
     * <p>sch081HourDiv を取得します。
     * @return sch081HourDiv
     */
    public String getSch081HourDiv() {
        return sch081HourDiv__;
    }

    /**
     * <p>sch081HourDiv をセットします。
     * @param sch081HourDiv sch081HourDiv
     */
    public void setSch081HourDiv(String sch081HourDiv) {
        sch081HourDiv__ = sch081HourDiv;
    }

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param req リクエスト
     * @param con コネクション
     * @return エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateCheck(
            ActionMapping map,
            HttpServletRequest req,
            Connection con) throws SQLException {

        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;
        GsMessage gsMsg = new GsMessage();
        //カラーコメント
        String textColorComment = gsMsg.getMessage(req, "schedule.src.16");
        //カラーコメントのチェック
        if (NullDefault.getString(sch081ColorComment1__, "").length() > 0) {
            //青
            String textBlue = gsMsg.getMessage(req, "schedule.src.49");
            if (__checkRange(
                    errors,
                    sch081ColorComment1__,
                    "sch081ColorComment1",
                    textColorComment + textBlue,
                    GSConstSchedule.MAX_LENGTH_TITLE)) {
                //先頭スペースチェック
                if (ValidateUtil.isSpaceStart(sch081ColorComment1__)) {
                    msg = new ActionMessage("error.input.spase.start",
                                            textColorComment);
                    StrutsUtil.addMessage(errors, msg, "sch081ColorComment1");
                } else {
                    __checkJisString(
                            errors,
                            sch081ColorComment1__,
                            "sch081ColorComment1",
                            textColorComment + textBlue);
                }
            }
        }
        if (NullDefault.getString(sch081ColorComment2__, "").length() > 0) {
            //赤
            String textRed = gsMsg.getMessage(req, "schedule.src.50");
            if (__checkRange(
                    errors,
                    sch081ColorComment2__,
                    "sch081ColorComment2",
                    textColorComment + textRed,
                    GSConstSchedule.MAX_LENGTH_TITLE)) {
                //先頭スペースチェック
                if (ValidateUtil.isSpaceStart(sch081ColorComment2__)) {
                    msg = new ActionMessage("error.input.spase.start",
                                            textColorComment);
                    StrutsUtil.addMessage(errors, msg, "sch081ColorComment2");
                } else {
                    __checkJisString(
                            errors,
                            sch081ColorComment2__,
                            "sch081ColorComment2",
                            textColorComment + textRed);
                }
            }
        }
        if (NullDefault.getString(sch081ColorComment3__, "").length() > 0) {
            //緑
            String textGreen = gsMsg.getMessage(req, "schedule.src.50");
            if (__checkRange(
                    errors,
                    sch081ColorComment3__,
                    "sch081ColorComment3",
                    textColorComment + textGreen,
                    GSConstSchedule.MAX_LENGTH_TITLE)) {
                //先頭スペースチェック
                if (ValidateUtil.isSpaceStart(sch081ColorComment3__)) {
                    msg = new ActionMessage("error.input.spase.start",
                                            textColorComment);
                    StrutsUtil.addMessage(errors, msg, "sch081ColorComment3");
                } else {
                    __checkJisString(
                            errors,
                            sch081ColorComment3__,
                            "sch081ColorComment3",
                            textColorComment + textGreen);
                }
            }
        }
        if (NullDefault.getString(sch081ColorComment4__, "").length() > 0) {
            //黄
            String textYellow = gsMsg.getMessage(req, "schedule.src.47");
            if (__checkRange(
                    errors,
                    sch081ColorComment4__,
                    "sch081ColorComment4",
                    textColorComment + textYellow,
                    GSConstSchedule.MAX_LENGTH_TITLE)) {
                //先頭スペースチェック
                if (ValidateUtil.isSpaceStart(sch081ColorComment4__)) {
                    msg = new ActionMessage("error.input.spase.start",
                                            textColorComment);
                    StrutsUtil.addMessage(errors, msg, "sch081ColorComment4");
                } else {
                    __checkJisString(
                            errors,
                            sch081ColorComment4__,
                            "sch081ColorComment4",
                            textColorComment + textYellow);
                }
            }
        }
        if (NullDefault.getString(sch081ColorComment5__, "").length() > 0) {
            //黒
            String textBlack = gsMsg.getMessage(req, "schedule.src.48");
            if (__checkRange(
                    errors,
                    sch081ColorComment5__,
                    "sch081ColorComment5",
                    textColorComment + textBlack,
                    GSConstSchedule.MAX_LENGTH_TITLE)) {
                //先頭スペースチェック
                if (ValidateUtil.isSpaceStart(sch081ColorComment5__)) {
                    msg = new ActionMessage("error.input.spase.start",
                                            textColorComment);
                    StrutsUtil.addMessage(errors, msg, "sch081ColorComment5");
                } else {
                    __checkJisString(
                            errors,
                            sch081ColorComment5__,
                            "sch081ColorComment5",
                            textColorComment + textBlack);
                }
            }
        }
        return errors;
    }
    /**
     * <br>[機  能] 指定された項目の桁数チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors アクションエラー
     * @param value 項目の値
     * @param element 項目名
     * @param elementName 項目名(日本語)
     * @param range 桁数
     * @return チェック結果 true : 正常, false : 異常
     */
    private boolean __checkRange(ActionErrors errors,
                                String value,
                                String element,
                                String elementName,
                                int range) {
        boolean result = true;
        ActionMessage msg = null;

        //MAX値を超えていないか
        if (value.length() > range) {
            msg = new ActionMessage("error.input.length.text", elementName,
                    String.valueOf(range));
            errors.add(element + "error.input.length.text", msg);
            result = false;
        }
        return result;
    }
    /**
     * <br>[機  能] 指定された項目がJIS第2水準文字かチェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors アクションエラー
     * @param value 項目の値
     * @param element 項目名
     * @param elementName 項目名(日本語)
     * @return チェック結果 true : 正常, false : 異常
     */
    private boolean __checkJisString(ActionErrors errors,
                                String value,
                                String element,
                                String elementName) {
        boolean result = true;
        ActionMessage msg = null;
        //JIS第2水準文字か
        if (!GSValidateUtil.isGsJapaneaseStringTextArea(value)) {
            //利用不可能な文字を入力した場合
            String nstr = GSValidateUtil.getNotGsJapaneaseStringTextArea(value);
            msg = new ActionMessage("error.input.njapan.text", elementName, nstr);
            errors.add(element + "error.input.njapan.text", msg);
            result = false;
        }
        return result;
    }

    /**
     * <p>sch081colorKbn を取得します。
     * @return sch081colorKbn
     */
    public int getSch081colorKbn() {
        return sch081colorKbn__;
    }

    /**
     * <p>sch081colorKbn をセットします。
     * @param sch081colorKbn sch081colorKbn
     */
    public void setSch081colorKbn(int sch081colorKbn) {
        sch081colorKbn__ = sch081colorKbn;
    }

    /**
     * <p>sch081ColorComment6 を取得します。
     * @return sch081ColorComment6
     */
    public String getSch081ColorComment6() {
        return sch081ColorComment6__;
    }

    /**
     * <p>sch081ColorComment6 をセットします。
     * @param sch081ColorComment6 sch081ColorComment6
     */
    public void setSch081ColorComment6(String sch081ColorComment6) {
        sch081ColorComment6__ = sch081ColorComment6;
    }

    /**
     * <p>sch081ColorComment7 を取得します。
     * @return sch081ColorComment7
     */
    public String getSch081ColorComment7() {
        return sch081ColorComment7__;
    }

    /**
     * <p>sch081ColorComment7 をセットします。
     * @param sch081ColorComment7 sch081ColorComment7
     */
    public void setSch081ColorComment7(String sch081ColorComment7) {
        sch081ColorComment7__ = sch081ColorComment7;
    }

    /**
     * <p>sch081ColorComment8 を取得します。
     * @return sch081ColorComment8
     */
    public String getSch081ColorComment8() {
        return sch081ColorComment8__;
    }

    /**
     * <p>sch081ColorComment8 をセットします。
     * @param sch081ColorComment8 sch081ColorComment8
     */
    public void setSch081ColorComment8(String sch081ColorComment8) {
        sch081ColorComment8__ = sch081ColorComment8;
    }

    /**
     * <p>sch081ColorComment9 を取得します。
     * @return sch081ColorComment9
     */
    public String getSch081ColorComment9() {
        return sch081ColorComment9__;
    }

    /**
     * <p>sch081ColorComment9 をセットします。
     * @param sch081ColorComment9 sch081ColorComment9
     */
    public void setSch081ColorComment9(String sch081ColorComment9) {
        sch081ColorComment9__ = sch081ColorComment9;
    }

    /**
     * <p>sch081ColorComment10 を取得します。
     * @return sch081ColorComment10
     */
    public String getSch081ColorComment10() {
        return sch081ColorComment10__;
    }

    /**
     * <p>sch081ColorComment10 をセットします。
     * @param sch081ColorComment10 sch081ColorComment10
     */
    public void setSch081ColorComment10(String sch081ColorComment10) {
        sch081ColorComment10__ = sch081ColorComment10;
    }
}