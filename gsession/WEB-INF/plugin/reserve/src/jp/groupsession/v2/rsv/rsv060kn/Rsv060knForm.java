package jp.groupsession.v2.rsv.rsv060kn;

import java.util.ArrayList;

import jp.groupsession.v2.rsv.rsv060.Rsv060Form;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 施設予約 施設グループ登録確認画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv060knForm extends Rsv060Form {

    /** 施設区分名称 */
    private String rsv060knSelectedSisetuName__ = null;
    /** 管理者ユーザリスト */
    private ArrayList<LabelValueBean> rsv060knKanriUser__ = null;
    /** 予約可能ユーザリスト */
    private ArrayList<LabelValueBean> rsv060knEditUser__ = null;
    /** 閲覧ユーザリスト */
    private ArrayList<LabelValueBean> rsv060knReadUser__ = null;


    /**
     * <p>rsv060knSelectedSisetuName__ を取得します。
     * @return rsv060knSelectedSisetuName
     */
    public String getRsv060knSelectedSisetuName() {
        return rsv060knSelectedSisetuName__;
    }
    /**
     * <p>rsv060knSelectedSisetuName__ をセットします。
     * @param rsv060knSelectedSisetuName rsv060knSelectedSisetuName__
     */
    public void setRsv060knSelectedSisetuName(String rsv060knSelectedSisetuName) {
        rsv060knSelectedSisetuName__ = rsv060knSelectedSisetuName;
    }
    /**
     * <p>rsv060knKanriUser__ を取得します。
     * @return rsv060knKanriUser
     */
    public ArrayList<LabelValueBean> getRsv060knKanriUser() {
        return rsv060knKanriUser__;
    }
    /**
     * <p>rsv060knKanriUser__ をセットします。
     * @param rsv060knKanriUser rsv060knKanriUser__
     */
    public void setRsv060knKanriUser(ArrayList<LabelValueBean> rsv060knKanriUser) {
        rsv060knKanriUser__ = rsv060knKanriUser;
    }
    /**
     * <p>rsv060knEditUser を取得します。
     * @return rsv060knEditUser
     */
    public ArrayList<LabelValueBean> getRsv060knEditUser() {
        return rsv060knEditUser__;
    }
    /**
     * <p>rsv060knEditUser をセットします。
     * @param rsv060knEditUser rsv060knEditUser
     */
    public void setRsv060knEditUser(ArrayList<LabelValueBean> rsv060knEditUser) {
        rsv060knEditUser__ = rsv060knEditUser;
    }
    /**
     * <p>rsv060knReadUser を取得します。
     * @return rsv060knReadUser
     */
    public ArrayList<LabelValueBean> getRsv060knReadUser() {
        return rsv060knReadUser__;
    }
    /**
     * <p>rsv060knReadUser をセットします。
     * @param rsv060knReadUser rsv060knReadUser
     */
    public void setRsv060knReadUser(ArrayList<LabelValueBean> rsv060knReadUser) {
        rsv060knReadUser__ = rsv060knReadUser;
    }
}