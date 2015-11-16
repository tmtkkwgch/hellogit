package jp.groupsession.v2.api.ntp.anken.edit;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.api.AbstractApiForm;
import jp.groupsession.v2.api.GSValidateApi;
import jp.groupsession.v2.cmn.GSValidateCommon;
import jp.groupsession.v2.cmn.GSValidateUtil;
import jp.groupsession.v2.ntp.GSConstNippou;
import jp.groupsession.v2.ntp.GSValidateNippou;
import jp.groupsession.v2.ntp.dao.NtpAnkenDao;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
/**
 * <br>[機  能] 日報 案件編集をするWEBAPIフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiAnkenEditForm extends AbstractApiForm {
    /** 案件SID */
    private String nanSid__ = null;
    /** 案件コード */
    private String nanCode__ = null;
    /** 案件名 */
    private String nanName__ = null;
    /** 案件詳細 */
    private String nanDetail__ = "";
    /** 会社SID */
    private String acoSid__ = "-1";
    /** 会社拠点SID */
    private String abaSid__ = "-1";
    /** プロセスSid */
    private String ngpSid__ = "-1";
    /** 見込み度 */
    private String nanMikomi__ = Integer.toString(GSConstNippou.DF_MIKOMIDO);
    /** 見積金額 */
    private String nanKinMitumori__ = "0";
    /** 受注金額 */
    private String nanKinJutyu__ = "0";
    /** 商談結果 */
    private String nanSyodan__ = "0";
    /** コンタクト区分 */
    private String mcnSid__ = "-1";
    /** 商品SID */
    private String[] nanShohin__ = null;
    /** date*/
    private String date__ = UDateUtil.getSlashYYMD(new UDate());

    /**
     * <p>nanSid を取得します。
     * @return nanSid
     */
    public String getNanSid() {
        return nanSid__;
    }
    /**
     * <p>nanSid をセットします。
     * @param nanSid nanSid
     */
    public void setNanSid(String nanSid) {
        nanSid__ = nanSid;
    }
    /**
     * <p>nanCode を取得します。
     * @return nanCode
     */
    public String getNanCode() {
        return nanCode__;
    }
    /**
     * <p>nanCode をセットします。
     * @param nanCode nanCode
     */
    public void setNanCode(String nanCode) {
        nanCode__ = nanCode;
    }
    /**
     * <p>nanName を取得します。
     * @return nanName
     */
    public String getNanName() {
        return nanName__;
    }
    /**
     * <p>nanName をセットします。
     * @param nanName nanName
     */
    public void setNanName(String nanName) {
        nanName__ = nanName;
    }
    /**
     * <p>nanDetial を取得します。
     * @return nanDetial
     */
    public String getNanDetail() {
        return nanDetail__;
    }
    /**
     * <p>nanDetial をセットします。
     * @param nanDetail nanDetial
     */
    public void setNanDetail(String nanDetail) {
        nanDetail__ = nanDetail;
    }
    /**
     * <p>acoSid を取得します。
     * @return acoSid
     */
    public String getAcoSid() {
        return acoSid__;
    }
    /**
     * <p>acoSid をセットします。
     * @param acoSid acoSid
     */
    public void setAcoSid(String acoSid) {
        acoSid__ = acoSid;
    }
    /**
     * <p>abaSid を取得します。
     * @return abaSid
     */
    public String getAbaSid() {
        return abaSid__;
    }
    /**
     * <p>abaSid をセットします。
     * @param abaSid abaSid
     */
    public void setAbaSid(String abaSid) {
        abaSid__ = abaSid;
    }
    /**
     * <p>ngpSid を取得します。
     * @return ngpSid
     */
    public String getNgpSid() {
        return ngpSid__;
    }
    /**
     * <p>ngpSid をセットします。
     * @param ngpSid ngpSid
     */
    public void setNgpSid(String ngpSid) {
        ngpSid__ = ngpSid;
    }
    /**
     * <p>nanMikomi を取得します。
     * @return nanMikomi
     */
    public String getNanMikomi() {
        return nanMikomi__;
    }
    /**
     * <p>nanMikomi をセットします。
     * @param nanMikomi nanMikomi
     */
    public void setNanMikomi(String nanMikomi) {
        nanMikomi__ = nanMikomi;
    }
    /**
     * <p>nanKinMitumori を取得します。
     * @return nanKinMitumori
     */
    public String  getNanKinMitumori() {
        return nanKinMitumori__;
    }
    /**
     * <p>nanKinMitumori をセットします。
     * @param nanKinMitumori nanKinMitumori
     */
    public void setNanKinMitumori(String  nanKinMitumori) {
        nanKinMitumori__ = nanKinMitumori;
    }
    /**
     * <p>nanKinJutyu を取得します。
     * @return nanKinJutyu
     */
    public String  getNanKinJutyu() {
        return nanKinJutyu__;
    }
    /**
     * <p>nanKinJutyu をセットします。
     * @param nanKinJutyu nanKinJutyu
     */
    public void setNanKinJutyu(String nanKinJutyu) {
        nanKinJutyu__ = nanKinJutyu;
    }
    /**
     * <p>nanSyodan を取得します。
     * @return nanSyodan
     */
    public String getNanSyodan() {
        return nanSyodan__;
    }
    /**
     * <p>nanSyodan をセットします。
     * @param nanSyodan nanSyodan
     */
    public void setNanSyodan(String nanSyodan) {
        nanSyodan__ = nanSyodan;
    }
    /**
     * <p>mcnSid を取得します。
     * @return mcnSid
     */
    public String getMcnSid() {
        return mcnSid__;
    }
    /**
     * <p>mcnSid をセットします。
     * @param mcnSid mcnSid
     */
    public void setMcnSid(String mcnSid) {
        mcnSid__ = mcnSid;
    }
    /**
     * <p>nanShohin を取得します。
     * @return nanShohin
     */
    public String[] getNanShohin() {
        return nanShohin__;
    }
    /**
     * <p>nanShohin をセットします。
     * @param nanShohin nanShohin
     */
    public void setNanShohin(String[] nanShohin) {
        nanShohin__ = nanShohin;
    }
    /**
     * <p>date を取得します。
     * @return date
     */
    public String getDate() {
        return date__;
    }
    /**
     * <p>date をセットします。
     * @param date date
     */
    public void setDate(String date) {
        date__ = date;
    }
    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param con コネクション
     * @return errors エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateCheck(Connection con) throws SQLException {
        ActionErrors errors = new ActionErrors();


        ActionMessage msg = null;

        /** SID*/
        GSValidateApi.validateSid(errors, nanSid__
                , "nanSid", GSConstNippou.TEXT_ANKEN_SID, true, true);

        /** コード*/
        GSValidateNippou.validateCmnFieldText(errors,
                GSConstNippou.TEXT_ANKEN_CODE,
                nanCode__,
               "nanCode",
                GSConstNippou.MAX_LENGTH_ANKEN_CODE,
                true);

        if (errors.isEmpty()) {
            //案件コードの重複チェック
            int ncnSid = Integer.parseInt(getNanSid());
            NtpAnkenDao dao = new NtpAnkenDao(con);
            if (dao.existAnken(ncnSid, nanCode__)) {
                String eprefix = "nanCode";
                String fieldfix = GSConstNippou.TEXT_ANKEN_CODE + ".";
                msg = new ActionMessage("error.select.dup.list", GSConstNippou.TEXT_ANKEN_CODE);
                StrutsUtil.addMessage(errors, msg, eprefix + fieldfix + "nanCode");
                return errors;
            }
        }
        /** 件名*/
        GSValidateNippou.validateCmnFieldText(errors,
                GSConstNippou.TEXT_ANKEN_NAME,
                nanName__,
               "nanName",
                GSConstNippou.MAX_LENGTH_ANKEN_NAME,
                true);

        /** 詳細*/
        GSValidateNippou.validateFieldTextArea(errors,
                GSConstNippou.TEXT_ANKEN_SYOSAI,
                nanDetail__,
               "nanDetail",
                GSConstNippou.MAX_LENGTH_ANKEN_SYOSAI,
                false);

        /** 日付*/
        GSValidateCommon.validateDateFieldText(errors
                , date__
                , "date"
                , GSConstNippou.TEXT_ANKEN_DATE
                , false);

        /** 会社SID*/
        if (!GSValidateUtil.isNumberHaifun(acoSid__)) {
            msg = new ActionMessage(
                    "error.input.number.hankaku", GSConstNippou.TEXT_COMPANY_SID);
            StrutsUtil.addMessage(errors, msg, "acoSid");
            return errors;

        }
        /** 会社拠点SID */
        if (!GSValidateUtil.isNumberHaifun(abaSid__)) {
            msg = new ActionMessage(
                    "error.input.number.hankaku", "会社拠点SID");
            StrutsUtil.addMessage(errors, msg, "abaSid");
            return errors;

        }
        /** プロセスSid */
        if (!GSValidateUtil.isNumberHaifun(ngpSid__)) {
            msg = new ActionMessage(
                    "error.input.number.hankaku", GSConstNippou.TEXT_COMPANY_SID);
            StrutsUtil.addMessage(errors, msg, "ngpSid");
            return errors;

        }
        /** 見込み度 */
        if (!GSValidateUtil.isNumber(nanMikomi__)) {
            msg = new ActionMessage(
                    "error.input.number.hankaku", "見込度");
            StrutsUtil.addMessage(errors, msg, "nanMikomi");
            return errors;
        }
        /** 見積金額 */
        if (!GSValidateUtil.isNumber(nanKinMitumori__)) {
            msg = new ActionMessage(
                    "error.input.number.hankaku", GSConstNippou.TEXT_ANKEN_MITUMORI);
            StrutsUtil.addMessage(errors, msg, "nanKinMitumori");
            return errors;
        }
        if (nanKinMitumori__.length() > GSConstNippou.MAX_LENGTH_ANKEN_MITUMORI) {
            msg = new ActionMessage("error.input.length.text"
                    , GSConstNippou.TEXT_ANKEN_MITUMORI
                    , GSConstNippou.MAX_LENGTH_ANKEN_MITUMORI);
            StrutsUtil.addMessage(errors, msg, "nanKinMitumori");
            return errors;
        }

        /** 受注金額 */
        if (!GSValidateUtil.isNumber(nanKinJutyu__)) {
            msg = new ActionMessage(
                    "error.input.number.hankaku", GSConstNippou.TEXT_ANKEN_JUCYU);
            StrutsUtil.addMessage(errors, msg, "nanKinJutyu");
            return errors;

        }
        if (nanKinJutyu__.length() > GSConstNippou.MAX_LENGTH_ANKEN_JUCYU) {
            msg = new ActionMessage("error.input.length.text"
                    , GSConstNippou.TEXT_ANKEN_JUCYU
                    , GSConstNippou.MAX_LENGTH_ANKEN_JUCYU);
            StrutsUtil.addMessage(errors, msg, "nanKinJutyu");
            return errors;
        }
        /** 商談結果 */
        if (!GSValidateUtil.isNumber(nanSyodan__)) {
            msg = new ActionMessage(
                    "error.input.number.hankaku", "商談結果");
            StrutsUtil.addMessage(errors, msg, "nanSyodan");
            return errors;
        }
        /** コンタクト区分 */
        if (!GSValidateUtil.isNumberHaifun(mcnSid__)) {
            msg = new ActionMessage(
                    "error.input.number.hankaku", "コンタクト");
            StrutsUtil.addMessage(errors, msg, "mcnSid");
            return errors;

        }
        /** 商品SID */
        if (nanShohin__ != null) {
            for (String shohinSid : nanShohin__) {
                if (!GSValidateUtil.isNumberHaifun(shohinSid)) {
                    msg = new ActionMessage(
                            "error.input.number.hankaku", "商品SID");
                    StrutsUtil.addMessage(errors, msg, "mcnSid");
                    return errors;
                }
            }
        }
        return errors;

    }

}