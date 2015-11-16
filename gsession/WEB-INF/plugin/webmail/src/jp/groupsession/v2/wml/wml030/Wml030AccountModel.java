package jp.groupsession.v2.wml.wml030;

import java.math.BigDecimal;

import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.groupsession.v2.cmn.GSConstWebmail;
import jp.groupsession.v2.cmn.model.AbstractModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.struts.msg.GsMessage;


/**
 * <br>[機  能] WEBメール アカウント情報を格納するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml030AccountModel extends AbstractModel {

    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     */
   public Wml030AccountModel(RequestModel reqMdl) {
       reqMdl__ = reqMdl;
   }
    /** アカウントSID */
    private int accountSid__ = 0;
    /** アカウント名 */
    private String accountName__ = null;
    /** メールアドレス */
    private String mailAddress__ = null;
    /** 使用者 区分 */
    private int accountUserKbn__ = GSConstWebmail.WAC_TYPE_NORMAL;
    /** 使用者 件数 */
    private int accountUserCount__ = 0;
    /** ディスク使用区分 */
    private int diskType__ = 0;
    /** ディスク使用量 */
    private BigDecimal diskSizeUse__ = null;
    /** ディスク使用量 最大値 */
    private BigDecimal diskSizeLimit__ = null;
    /** 備考 */
    private String biko__ = null;
    /** 受信日時 */
    private UDate receiveDate__ = null;

    /**
     * <p>accountName を取得します。
     * @return accountName
     */
    public String getAccountName() {
        return accountName__;
    }
    /**
     * <p>accountName をセットします。
     * @param accountName accountName
     */
    public void setAccountName(String accountName) {
        accountName__ = accountName;
    }
    /**
     * <p>accountSid を取得します。
     * @return accountSid
     */
    public int getAccountSid() {
        return accountSid__;
    }
    /**
     * <p>accountSid をセットします。
     * @param accountSid accountSid
     */
    public void setAccountSid(int accountSid) {
        accountSid__ = accountSid;
    }
    /**
     * <p>accountUserCount を取得します。
     * @return accountUserCount
     */
    public int getAccountUserCount() {
        return accountUserCount__;
    }
    /**
     * <p>accountUserCount をセットします。
     * @param accountUserCount accountUserCount
     */
    public void setAccountUserCount(int accountUserCount) {
        accountUserCount__ = accountUserCount;
    }
    /**
     * <p>accountUserKbn を取得します。
     * @return accountUserKbn
     */
    public int getAccountUserKbn() {
        return accountUserKbn__;
    }
    /**
     * <p>accountUserKbn をセットします。
     * @param accountUserKbn accountUserKbn
     */
    public void setAccountUserKbn(int accountUserKbn) {
        accountUserKbn__ = accountUserKbn;
    }
    /**
     * <p>biko を取得します。
     * @return biko
     */
    public String getBiko() {
        return biko__;
    }
    /**
     * <p>biko をセットします。
     * @param biko biko
     */
    public void setBiko(String biko) {
        biko__ = biko;
    }
    /**
     * <p>diskType を取得します。
     * @return diskType
     */
    public int getDiskType() {
        return diskType__;
    }
    /**
     * <p>diskType をセットします。
     * @param diskType diskType
     */
    public void setDiskType(int diskType) {
        diskType__ = diskType;
    }
    /**
     * <p>diskSizeLimit を取得します。
     * @return diskSizeLimit
     */
    public BigDecimal getDiskSizeLimit() {
        return diskSizeLimit__;
    }
    /**
     * <p>diskSizeLimit をセットします。
     * @param diskSizeLimit diskSizeLimit
     */
    public void setDiskSizeLimit(BigDecimal diskSizeLimit) {
        diskSizeLimit__ = diskSizeLimit;
    }
    /**
     * <p>diskSizeUse を取得します。
     * @return diskSizeUse
     */
    public BigDecimal getDiskSizeUse() {
        return diskSizeUse__;
    }
    /**
     * <p>diskSizeUse をセットします。
     * @param diskSizeUse diskSizeUse
     */
    public void setDiskSizeUse(BigDecimal diskSizeUse) {
        diskSizeUse__ = diskSizeUse;
    }
    /**
     * <p>mailAddress を取得します。
     * @return mailAddress
     */
    public String getMailAddress() {
        return mailAddress__;
    }
    /**
     * <p>mailAddress をセットします。
     * @param mailAddress mailAddress
     */
    public void setMailAddress(String mailAddress) {
        mailAddress__ = mailAddress;
    }
    /**
     * <p>receiveDate を取得します。
     * @return receiveDate
     */
    public UDate getReceiveDate() {
        return receiveDate__;
    }
    /**
     * <p>receiveDate をセットします。
     * @param receiveDate receiveDate
     */
    public void setReceiveDate(UDate receiveDate) {
        receiveDate__ = receiveDate;
    }

    /**
     * <br>[機  能] 使用者 区分 の文字列表現を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @return 使用者 区分 の文字列表現
     */
    public String getAccountUserKbnString() {
        GsMessage gsMsg = new GsMessage(reqMdl__);
        if (accountUserKbn__ == GSConstWebmail.WAC_TYPE_USER) {
            return gsMsg.getMessage("cmn.user");
        } else if (accountUserKbn__ == GSConstWebmail.WAC_TYPE_GROUP) {
            return gsMsg.getMessage("cmn.group");
        }

        return "";
    }

    /**
     * <br>[機  能] 備考(表示用)を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @return 備考(表示用)
     */
    public String getViewBiko() {
        return StringUtilHtml.transToHTmlPlusAmparsant(biko__);
    }

    /**
     * <br>[機  能] 受信日時(表示用)を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @return 受信日時(表示用)
     */
    public String getViewReceiveDate() {
        if (receiveDate__ == null) {
            return null;
        }

        String dateString = UDateUtil.getSlashYYMD(receiveDate__);
        dateString += " " + UDateUtil.getSeparateHMS(receiveDate__);
        return dateString;
    }
}
