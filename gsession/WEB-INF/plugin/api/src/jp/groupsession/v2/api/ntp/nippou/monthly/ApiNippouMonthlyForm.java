package jp.groupsession.v2.api.ntp.nippou.monthly;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.api.AbstractApiForm;
import jp.groupsession.v2.cmn.GSValidateCommon;
import jp.groupsession.v2.cmn.GSValidateUtil;
import jp.groupsession.v2.ntp.GSConstNippou;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
/**
 * <br>[機  能] WEBAPI 日報月次取得フォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiNippouMonthlyForm extends AbstractApiForm {
    /** ユーザSID*/
    private String usrSid__;
    /** 表示月*/
    private String month__;
    /** 表示開始日*/
    private String start__;
    /** 表示終了日*/
    private String end__;
    /**
     * <p>usrSid を取得します。
     * @return usrSid
     */
    public String getUsrSid() {
        return usrSid__;
    }
    /**
     * <p>usrSid をセットします。
     * @param usrSid usrSid
     */
    public void setUsrSid(String usrSid) {
        this.usrSid__ = usrSid;
    }
    /**
     * <p>startMonth を取得します。
     * @return startMonth
     */
    public String getMonth() {
        return month__;
    }
    /**
     * <p>startMonth をセットします。
     * @param startMonth startMonth
     */
    public void setMonth(String startMonth) {
        this.month__ = startMonth;
    }
    /**
     * <p>start を取得します。
     * @return start
     */
    public String getStart() {
        return start__;
    }
    /**
     * <p>start をセットします。
     * @param start start
     */
    public void setStart(String start) {
        start__ = start;
    }
    /**
     * <p>end を取得します。
     * @return end
     */
    public String getEnd() {
        return end__;
    }
    /**
     * <p>end をセットします。
     * @param end end
     */
    public void setEnd(String end) {
        end__ = end;
    }
    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param con コネクション
     * @param req リクエスト
     * @return errors エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateCheck(Connection con, HttpServletRequest req) throws SQLException {
        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;

        //未入力チェック
        if (StringUtil.isNullZeroString(usrSid__)) {
            msg = new ActionMessage("error.input.required.text", GSConstNippou.TEXT_USER_SID);
            StrutsUtil.addMessage(errors, msg, "usrSid");
            return errors;
        }

        if (!GSValidateUtil.isNumber(usrSid__)) {
            msg = new ActionMessage(
                    "error.input.number.hankaku", GSConstNippou.TEXT_USER_SID);
            StrutsUtil.addMessage(errors, msg, "usrSid");
            return errors;
        }
//        startMonth =
        if (StringUtil.isNullZeroString(month__)) {
            UDate date = new UDate();
            String dateF = date.toString();
            month__ = dateF.substring(0, 4) + "/" + dateF.substring(4, 6);
        }
        //日付フォーマットチェック
        if (month__.length() < 7 || !GSValidateUtil.isNumber(month__.substring(0, 4))
                || !GSValidateUtil.isNumber(month__.substring(5, 7))
                || !month__.substring(4, 5).equals("/")) {
            msg = new ActionMessage("error.input.format.text", "取得月");
            StrutsUtil.addMessage(
                    errors, msg, "取得月.month");
        } else {
            //日付存在チェック
            UDate date = new UDate();
            int month = Integer.parseInt(month__.substring(5, 7));
            date.setDate(Integer.parseInt(month__.substring(0, 4)),
                        month,
                        1);

            if (date.getMonth() != month) {
                msg = new ActionMessage("error.input.notfound.date", "取得月");
                StrutsUtil.addMessage(
                        errors, msg, "取得月.month");
            }
        }
        //取得開始日
        GSValidateCommon.validateDateFieldText(errors, start__, "start", "取得開始日", false);
        //取得終了日
        GSValidateCommon.validateDateFieldText(errors, end__, "end", "取得開始日", false);

        return errors;
    }
}
