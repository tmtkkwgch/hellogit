package jp.groupsession.v2.man.man002;

import java.util.ArrayList;

import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.lic.LicenseModel;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.man.model.ManAdmSettingInfoModel;
import jp.groupsession.v2.struts.AbstractGsForm;

/**
 * <br>[機  能] メイン 管理者設定メニュー画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man002Form extends AbstractGsForm {

    /** ライセンス購入画面URL */
    public String licensePageUrl__;
    /** ライセンスID */
    public String licenseId__;
    /** GS UID */
    public String gsUid__;

    /** ライセンス ユーザ数 */
    public String licenseCount__;
    /** 会社名 */
    public String licenseCom__;
    /** プラグイン情報 */
    public ArrayList<LicenseModel> pluginList__;
    /** 各プラグイン管理者設定情報 */
    public ArrayList<ManAdmSettingInfoModel> pluginMenuList__;
    /** 現在登録済みユーザ数 */
    public String userCount__;
    /** 登録可能ユーザ数 */
    private String limitUserCount__;
    /** 添付ファイル保存先区分 */
    private String tempFileHozonKbn__;
    /** DB区分 */
    private String dbKbn__;
    /** システム区分 */
    private int sysKbn__ = 0;
    /** DB使用可能容量 */
    private String dbCanUse__;
    /** DB使用量 */
    private String dbUse__;
    /** ドメイン */
    private String domain__ = GSConst.GS_DOMAIN;

    /**
     * <p>sysKbn を取得します。
     * @return sysKbn
     */
    public int getSysKbn() {
        return sysKbn__;
    }
    /**
     * <p>sysKbn をセットします。
     * @param sysKbn sysKbn
     */
    public void setSysKbn(int sysKbn) {
        sysKbn__ = sysKbn;
    }
    /** パスワード変更の有効・無効 */
    public int changePassword__ = GSConst.CHANGEPASSWORD_PARMIT;
    /** ポータルプラグイン利用可:0・不可:1*/
    private int portalUseOk__ = GSConstMain.PLUGIN_USE;

    /**
     * @return the gsUid
     */
    public String getGsUid() {
        return gsUid__;
    }
    /**
     * @param gsUid the gsUid to set
     */
    public void setGsUid(String gsUid) {
        gsUid__ = gsUid;
    }
    /**
     * @return tempFileHozonKbn
     */
    public String getTempFileHozonKbn() {
        return tempFileHozonKbn__;
    }
    /**
     * @param tempFileHozonKbn セットする tempFileHozonKbn
     */
    public void setTempFileHozonKbn(String tempFileHozonKbn) {
        tempFileHozonKbn__ = tempFileHozonKbn;
    }
    /**
     * @return limitUserCount
     */
    public String getLimitUserCount() {
        return limitUserCount__;
    }
    /**
     * @param limitUserCount セットする limitUserCount
     */
    public void setLimitUserCount(String limitUserCount) {
        limitUserCount__ = limitUserCount;
    }
    /**
     * <p>licenseCount を取得します。
     * @return licenseCount
     */
    public String getLicenseCount() {
        return licenseCount__;
    }
    /**
     * <p>licenseCount をセットします。
     * @param licenseCount licenseCount
     */
    public void setLicenseCount(String licenseCount) {
        licenseCount__ = licenseCount;
    }
    /**
     * <p>userCount を取得します。
     * @return userCount
     */
    public String getUserCount() {
        return userCount__;
    }
    /**
     * <p>userCount をセットします。
     * @param userCount userCount
     */
    public void setUserCount(String userCount) {
        userCount__ = userCount;
    }
    /**
     * <p>pluginList を取得します。
     * @return pluginList
     */
    public ArrayList<LicenseModel> getPluginList() {
        return pluginList__;
    }
    /**
     * <p>pluginList をセットします。
     * @param pluginList pluginList
     */
    public void setPluginList(ArrayList<LicenseModel> pluginList) {
        pluginList__ = pluginList;
    }
    /**
     * <p>licenseId を取得します。
     * @return licenseId
     */
    public String getLicenseId() {
        return licenseId__;
    }
    /**
     * <p>licenseId をセットします。
     * @param licenseId licenseId
     */
    public void setLicenseId(String licenseId) {
        licenseId__ = licenseId;
    }
    /**
     * <p>licenseCom を取得します。
     * @return licenseCom
     */
    public String getLicenseCom() {
        return licenseCom__;
    }
    /**
     * <p>licenseCom をセットします。
     * @param licenseCom licenseCom
     */
    public void setLicenseCom(String licenseCom) {
        licenseCom__ = licenseCom;
    }
    /**
     * <p>pluginMenuList を取得します。
     * @return pluginMenuList
     */
    public ArrayList<ManAdmSettingInfoModel> getPluginMenuList() {
        return pluginMenuList__;
    }
    /**
     * <p>pluginMenuList をセットします。
     * @param pluginMenuList pluginMenuList
     */
    public void setPluginMenuList(ArrayList<ManAdmSettingInfoModel> pluginMenuList) {
        pluginMenuList__ = pluginMenuList;
    }
    /**
     * <p>licensePageUrl を取得します。
     * @return licensePageUrl
     */
    public String getLicensePageUrl() {
        return licensePageUrl__;
    }
    /**
     * <p>licensePageUrl をセットします。
     * @param licensePageUrl licensePageUrl
     */
    public void setLicensePageUrl(String licensePageUrl) {
        licensePageUrl__ = licensePageUrl;
    }
    /**
     * <p>changePassword を取得します。
     * @return changePassword
     */
    public int getChangePassword() {
        return changePassword__;
    }
    /**
     * <p>changePassword をセットします。
     * @param changePassword changePassword
     */
    public void setChangePassword(int changePassword) {
        changePassword__ = changePassword;
    }
    /**
     * <p>portalUseOk を取得します。
     * @return portalUseOk
     */
    public int getPortalUseOk() {
        return portalUseOk__;
    }
    /**
     * <p>portalUseOk をセットします。
     * @param portalUseOk portalUseOk
     */
    public void setPortalUseOk(int portalUseOk) {
        portalUseOk__ = portalUseOk;
    }
    /**
     * <p>dbKbn を取得します。
     * @return dbKbn
     */
    public String getDbKbn() {
        return dbKbn__;
    }
    /**
     * <p>dbKbn をセットします。
     * @param dbKbn dbKbn
     */
    public void setDbKbn(String dbKbn) {
        dbKbn__ = dbKbn;
    }
    /**
     * <p>dbCanUse を取得します。
     * @return dbCanUse
     */
    public String getDbCanUse() {
        return dbCanUse__;
    }
    /**
     * <p>dbCanUse をセットします。
     * @param dbCanUse dbCanUse
     */
    public void setDbCanUse(String dbCanUse) {
        dbCanUse__ = dbCanUse;
    }
    /**
     * <p>dbUse を取得します。
     * @return dbUse
     */
    public String getDbUse() {
        return dbUse__;
    }
    /**
     * <p>dbUse をセットします。
     * @param dbUse dbUse
     */
    public void setDbUse(String dbUse) {
        dbUse__ = dbUse;
    }
    /**
     * <p>domain を取得します。
     * @return domain
     */
    public String getDomain() {
        return domain__;
    }
    /**
     * <p>domain をセットします。
     * @param domain domain
     */
    public void setDomain(String domain) {
        domain__ = domain;
    }
}