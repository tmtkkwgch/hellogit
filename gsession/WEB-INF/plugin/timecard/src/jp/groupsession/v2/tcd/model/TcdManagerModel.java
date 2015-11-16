package jp.groupsession.v2.tcd.model;

import java.math.BigDecimal;

import jp.groupsession.v2.cmn.GSConstTimecard;

/**
 * <br>[機  能] タイムカードマネージャー検索結果情報を格納するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class TcdManagerModel extends TcdTotalValueModel
implements Comparable<TcdManagerModel> {

    /** compareTo(TcdManagerModel o)メソッド実施時、oがthisより大きい場合に返されます */
    public static final int LARGE = 1;
    /** compareTo(TcdManagerModel o)メソッド実施時、oが等しい場合に返されます */
    public static final int EQUAL = 0;
    /** compareTo(TcdManagerModel o)メソッド実施時、oがthisより小さい場合に返されます */
    public static final int SMALL = -1;

    /** ソートKEY(ソートするターゲットを指定) */
    private int sortKey__;

    /** ユーザSID */
    private int userSid__;
    /** ユーザ氏名 */
    private String userName__;
    /** 職員番号 */
    private String userSyainNo__;

    /** ソートKEYをもとに比較します。
     * @param o 比較対照
     * @return int
     */
    public int compareTo(TcdManagerModel o) {
        int res = EQUAL;
        switch (this.sortKey__) {
            case GSConstTimecard.SORT_KADODAYS:
                res = compareToBigDecimalValue(
                        getKadoDays(), o.getKadoDays());
                break;
            case GSConstTimecard.SORT_KADOHOURS:
                res = compareToBigDecimalValue(
                        getKadoHours(), o.getKadoHours());
                break;
            case GSConstTimecard.SORT_ZANDAYS:
                res = compareToBigDecimalValue(
                        getZangyoDays(), o.getZangyoDays());
                break;
            case GSConstTimecard.SORT_ZANHOURS:
                res = compareToBigDecimalValue(
                        getZangyoHours(), o.getZangyoHours());
                break;
            case GSConstTimecard.SORT_SINYADAYS:
                res = compareToBigDecimalValue(
                        getSinyaDays(), o.getSinyaDays());
                break;
            case GSConstTimecard.SORT_SINYAHOURS:
                res = compareToBigDecimalValue(
                        getSinyaHours(), o.getSinyaHours());
                break;
            case GSConstTimecard.SORT_KYUDEDAYS:
                res = compareToBigDecimalValue(
                        getKyusyutuDays(), o.getKyusyutuDays());
                break;
            case GSConstTimecard.SORT_KYUDEHOURS:
                res = compareToBigDecimalValue(
                        getKyusyutuHours(), o.getKyusyutuHours());
                break;
            case GSConstTimecard.SORT_CHIKOKU:
                res = compareToBigDecimalValue(
                        getChikokuTimes(), o.getChikokuTimes());
                break;
            case GSConstTimecard.SORT_SOUTAI:
                res = compareToBigDecimalValue(
                        getSoutaiTimes(), o.getSoutaiTimes());
                break;
            case GSConstTimecard.SORT_KEKKIN:
                res = compareToBigDecimalValue(
                        getKekkinDays(), o.getKekkinDays());
                break;
            case GSConstTimecard.SORT_KEITYO:
                res = compareToBigDecimalValue(
                        getKeityoDays(), o.getKeityoDays());
                break;
            case GSConstTimecard.SORT_YUUKYU:
                res = compareToBigDecimalValue(
                        getYuukyuDays(), o.getYuukyuDays());
                break;
            case GSConstTimecard.SORT_DAIKYU:
                res = compareToBigDecimalValue(
                        getDaikyuDays(), o.getDaikyuDays());
                break;
            case GSConstTimecard.SORT_SONOTA:
                res = compareToBigDecimalValue(
                        getSonotaDays(), o.getSonotaDays());
                break;
            default:
                break;
        }


        return res;
    }

    /**
     * BigDecimalの数値を比較します
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param val1 比較対象1
     * @param val2 比較対象2
     * @return int
     */
    private int compareToBigDecimalValue(BigDecimal val1, BigDecimal val2) {
        int res = val1.compareTo(val2);
        return res;
    }


    /**
     * タイムカード集計用モデルを元にインスタンスを生成
     * @param valueMdl タイムカード集計用
     */
    public TcdManagerModel(TcdTotalValueModel valueMdl) {
        /** 集計開始日付 */
        this.setStartDate(valueMdl.getStartDate());
        /** 集計終了日付 */
        this.setEndDate(valueMdl.getEndDate());
        //集計用
        /** 稼動基準日数 */
        this.setKadoBaseDays(valueMdl.getKadoBaseDays());
        /** 稼動実績日数 */
        this.setKadoDays(valueMdl.getKadoDays());
        /** 稼動基準時間数 */
        this.setKadoBaseHours(valueMdl.getKadoBaseHours());
        /** 稼動実績時間数 */
        this.setKadoHours(valueMdl.getKadoHours());
        /** 残業日数 */
        this.setZangyoDays(valueMdl.getZangyoDays());
        /** 残業時間数 */
        this.setZangyoHours(valueMdl.getZangyoHours());
        /** 深夜日数 */
        this.setSinyaDays(valueMdl.getSinyaDays());
        /** 深夜時間数 */
        this.setSinyaHours(valueMdl.getSinyaHours());
        /** 休日出勤日数 */
        this.setKyusyutuDays(valueMdl.getKyusyutuDays());
        /** 休日出勤時間数 */
        this.setKyusyutuHours(valueMdl.getKyusyutuHours());
        /** 遅刻日数 */
        this.setChikokuDays(valueMdl.getChikokuDays());
        /** 遅刻時間 */
        this.setChikokuTimes(valueMdl.getChikokuTimes());
        /** 早退日数 */
        this.setSoutaiDays(valueMdl.getSoutaiDays());
        /** 早退時間 */
        this.setSoutaiTimes(valueMdl.getSoutaiTimes());
        /** 欠勤日数 */
        this.setKekkinDays(valueMdl.getKekkinDays());
        /** 慶弔休暇日数 */
        this.setKeityoDays(valueMdl.getKeityoDays());
        /** 有給休暇日数 */
        this.setYuukyuDays(valueMdl.getYuukyuDays());
        /** 代休日数 */
        this.setDaikyuDays(valueMdl.getDaikyuDays());
        /** その他休暇日数 */
        this.setSonotaDays(valueMdl.getSonotaDays());
        //画面表示用
        /** 稼動基準日数 */
        this.setKadoBaseDaysStr(valueMdl.getKadoBaseDaysStr());
        /** 稼動実績日数 */
        this.setKadoDaysStr(valueMdl.getKadoDaysStr());
        /** 稼動基準時間数 */
        this.setKadoBaseHoursStr(valueMdl.getKadoBaseHoursStr());
        /** 稼動実績時間数 */
        this.setKadoHoursStr(valueMdl.getKadoHoursStr());
        /** 残業日数 */
        this.setZangyoDaysStr(valueMdl.getZangyoDaysStr());
        /** 残業時間数 */
        this.setZangyoHoursStr(valueMdl.getZangyoHoursStr());
        /** 深夜日数 */
        this.setSinyaDaysStr(valueMdl.getSinyaDaysStr());
        /** 深夜時間数 */
        this.setSinyaHoursStr(valueMdl.getSinyaHoursStr());
        /** 休日出勤日数 */
        this.setKyusyutuDaysStr(valueMdl.getKyusyutuDaysStr());
        /** 休日出勤時間数 */
        this.setKyusyutuHoursStr(valueMdl.getKyusyutuHoursStr());
        /** 遅刻日数 */
        this.setChikokuDaysStr(valueMdl.getChikokuDaysStr());
        /** 遅刻時間 */
        this.setChikokuTimesStr(valueMdl.getChikokuTimesStr());
        /** 早退日数 */
        this.setSoutaiDaysStr(valueMdl.getSoutaiDaysStr());
        /** 早退時間 */
        this.setSoutaiTimesStr(valueMdl.getSoutaiTimesStr());
        /** 欠勤日数 */
        this.setKekkinDaysStr(valueMdl.getKekkinDaysStr());
        /** 慶弔休暇日数 */
        this.setKeityoDaysStr(valueMdl.getKeityoDaysStr());
        /** 有給休暇日数 */
        this.setYuukyuDaysStr(valueMdl.getYuukyuDaysStr());
        /** 代休日数 */
        this.setDaikyuDaysStr(valueMdl.getDaikyuDaysStr());
        /** その他休暇日数 */
        this.setSonotaDaysStr(valueMdl.getSonotaDaysStr());

    }

    /**
     * <p>sortKey を取得します。
     * @return sortKey
     */
    public int getSortKey() {
        return sortKey__;
    }
    /**
     * <p>sortKey をセットします。
     * @param sortKey sortKey
     */
    public void setSortKey(int sortKey) {
        sortKey__ = sortKey;
    }
    /**
     * <p>userName を取得します。
     * @return userName
     */
    public String getUserName() {
        return userName__;
    }
    /**
     * <p>userName をセットします。
     * @param userName userName
     */
    public void setUserName(String userName) {
        userName__ = userName;
    }
    /**
     * <p>userSid を取得します。
     * @return userSid
     */
    public int getUserSid() {
        return userSid__;
    }
    /**
     * <p>userSid をセットします。
     * @param userSid userSid
     */
    public void setUserSid(int userSid) {
        userSid__ = userSid;
    }

    /**
     * <p>userSyainNo を取得します。
     * @return userSyainNo
     */
    public String getUserSyainNo() {
        return userSyainNo__;
    }

    /**
     * <p>userSyainNo をセットします。
     * @param userSyainNo userSyainNo
     */
    public void setUserSyainNo(String userSyainNo) {
        userSyainNo__ = userSyainNo;
    }


}
