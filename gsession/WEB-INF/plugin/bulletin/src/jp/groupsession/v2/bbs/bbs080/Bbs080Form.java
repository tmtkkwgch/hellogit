package jp.groupsession.v2.bbs.bbs080;

import java.util.List;

import jp.groupsession.v2.bbs.GSConstBulletin;
import jp.groupsession.v2.bbs.bbs060.Bbs060Form;
import jp.groupsession.v2.bbs.model.BulletinDspModel;
import jp.groupsession.v2.cmn.GSConst;

/**
 * <br>[機  能] 掲示板 投稿一覧画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bbs080Form extends Bbs060Form {

    /** ページコンボ上 */
    private int bbs080page1__ = 0;
    /** ページコンボ下 */
    private int bbs080page2__ = 0;
    /** 投稿SID */
    private int bbs080writeSid__ = 0;
    /** 添付ファイルSID */
    private Long bbs080binSid__ = new Long(0);

    /** フォーラム名 */
    private String bbs080forumName__ = null;
    /** スレッドタイトル */
    private String bbs080threadTitle__ = null;
    /** 投稿一覧 */
    private List < BulletinDspModel > writeList__ = null;
    /** スレッド削除ボタン表示フラグ */
    private int bbs080ShowThreBtn__ = 0;

    /** 写真ファイル名 */
    private String photoFileName__ = null;
    /** 写真表示有無 */
    private int photoFileDsp__ = GSConstBulletin.BBS_IMAGE_DSP;

    /** 添付ファイル画像表示有無 */
    private int tempImageFileDsp__ = GSConstBulletin.BBS_IMAGE_TEMP_DSP;


    /** スレッドURL */
    private String threadUrl__ = null;

    /** 並び順 */
    private int bbs080orderKey__ = -1;

    /** 返信可能区分 */
    private String bbs080reply__ = null;

    /** 掲示期限 */
    private String bbs080limitDate__ = null;

    /** ボタン項目表示フラグ true:表示 false:非表示 */
    private boolean bbs080btnDspFlg__ = BulletinDspModel.SHOWALLBTNFLG_YES;

    /** 既読件数 */
    private int readedCnt__ = 0;

    /** 削除スレッドタイトル */
    private String bbs080delTitle__ = null;

    /** WEB検索プラグイン使用可否 0=使用 1=未使用 */
    private int bbs080searchUse__ = GSConst.PLUGIN_USE;

    //スケジュール用
    /** 処理モード */
    private String cmd__ = null;
    /** ユーザSID */
    private String sch010SelectUsrSid__;
    /** ユーザ区分 */
    private String sch010SelectUsrKbn__;

    /**
     * <p>photoFileDsp を取得します。
     * @return photoFileDsp
     */
    public int getPhotoFileDsp() {
        return photoFileDsp__;
    }
    /**
     * <p>photoFileDsp をセットします。
     * @param photoFileDsp photoFileDsp
     */
    public void setPhotoFileDsp(int photoFileDsp) {
        photoFileDsp__ = photoFileDsp;
    }
    /**
     * <p>tempImageFileDsp を取得します。
     * @return tempImageFileDsp
     */
    public int getTempImageFileDsp() {
        return tempImageFileDsp__;
    }
    /**
     * <p>tempImageFileDsp をセットします。
     * @param tempImageFileDsp tempImageFileDsp
     */
    public void setTempImageFileDsp(int tempImageFileDsp) {
        tempImageFileDsp__ = tempImageFileDsp;
    }
    /**
     * @return bbs080binSid を戻します。
     */
    public Long getBbs080binSid() {
        return bbs080binSid__;
    }
    /**
     * @param bbs080binSid 設定する bbs080binSid。
     */
    public void setBbs080binSid(Long bbs080binSid) {
        bbs080binSid__ = bbs080binSid;
    }
    /**
     * @return bbs080page1 を戻します。
     */
    public int getBbs080page1() {
        return bbs080page1__;
    }
    /**
     * @param bbs080page1 設定する bbs080page1。
     */
    public void setBbs080page1(int bbs080page1) {
        bbs080page1__ = bbs080page1;
    }
    /**
     * @return bbs080page2 を戻します。
     */
    public int getBbs080page2() {
        return bbs080page2__;
    }
    /**
     * @param bbs080page2 設定する bbs080page2。
     */
    public void setBbs080page2(int bbs080page2) {
        bbs080page2__ = bbs080page2;
    }
    /**
     * @return bbs080writeSid を戻します。
     */
    public int getBbs080writeSid() {
        return bbs080writeSid__;
    }
    /**
     * @param bbs080writeSid 設定する bbs080writeSid。
     */
    public void setBbs080writeSid(int bbs080writeSid) {
        bbs080writeSid__ = bbs080writeSid;
    }
    /**
     * @return bbs080forumName を戻します。
     */
    public String getBbs080forumName() {
        return bbs080forumName__;
    }
    /**
     * @param bbs080forumName 設定する bbs080forumName。
     */
    public void setBbs080forumName(String bbs080forumName) {
        bbs080forumName__ = bbs080forumName;
    }
    /**
     * @return bbs080threadTitle を戻します。
     */
    public String getBbs080threadTitle() {
        return bbs080threadTitle__;
    }
    /**
     * @param bbs080threadTitle 設定する bbs080threadTitle。
     */
    public void setBbs080threadTitle(String bbs080threadTitle) {
        bbs080threadTitle__ = bbs080threadTitle;
    }
    /**
     * @return writeList を戻します。
     */
    public List<BulletinDspModel> getWriteList() {
        return writeList__;
    }
    /**
     * @param writeList 設定する writeList。
     */
    public void setWriteList(List<BulletinDspModel> writeList) {
        writeList__ = writeList;
    }
    /**
     * @return photoFileName を戻します。
     */
    public String getPhotoFileName() {
        return photoFileName__;
    }
    /**
     * @param photoFileName 設定する photoFileName。
     */
    public void setPhotoFileName(String photoFileName) {
        photoFileName__ = photoFileName;
    }
    /**
     * <p>bbs080ShowThreBtn を取得します。
     * @return bbs080ShowThreBtn
     */
    public int getBbs080ShowThreBtn() {
        return bbs080ShowThreBtn__;
    }
    /**
     * <p>bbs080ShowThreBtn をセットします。
     * @param bbs080ShowThreBtn bbs080ShowThreBtn
     */
    public void setBbs080ShowThreBtn(int bbs080ShowThreBtn) {
        bbs080ShowThreBtn__ = bbs080ShowThreBtn;
    }
    /**
     * <p>threadUrl を取得します。
     * @return threadUrl
     */
    public String getThreadUrl() {
        return threadUrl__;
    }
    /**
     * <p>threadUrl をセットします。
     * @param threadUrl threadUrl
     */
    public void setThreadUrl(String threadUrl) {
        threadUrl__ = threadUrl;
    }
    /**
     * <p>bbs080orderKey を取得します。
     * @return bbs080orderKey
     */
    public int getBbs080orderKey() {
        return bbs080orderKey__;
    }
    /**
     * <p>bbs080orderKey をセットします。
     * @param bbs080orderKey bbs080orderKey
     */
    public void setBbs080orderKey(int bbs080orderKey) {
        bbs080orderKey__ = bbs080orderKey;
    }
    /**
     * <p>bbs080reply を取得します。
     * @return bbs080reply
     */
    public String getBbs080reply() {
        return bbs080reply__;
    }
    /**
     * <p>bbs080reply をセットします。
     * @param bbs080reply bbs080reply
     */
    public void setBbs080reply(String bbs080reply) {
        bbs080reply__ = bbs080reply;
    }
    /**
     * <p>bbs080btnDspFlg を取得します。
     * @return bbs080btnDspFlg
     */
    public boolean isBbs080btnDspFlg() {
        return bbs080btnDspFlg__;
    }
    /**
     * <p>bbs080btnDspFlg をセットします。
     * @param bbs080btnDspFlg bbs080btnDspFlg
     */
    public void setBbs080btnDspFlg(boolean bbs080btnDspFlg) {
        bbs080btnDspFlg__ = bbs080btnDspFlg;
    }
    /**
     * <p>bbs080limitDate を取得します。
     * @return bbs080limitDate
     */
    public String getBbs080limitDate() {
        return bbs080limitDate__;
    }
    /**
     * <p>bbs080limitDate をセットします。
     * @param bbs080limitDate bbs080limitDate
     */
    public void setBbs080limitDate(String bbs080limitDate) {
        bbs080limitDate__ = bbs080limitDate;
    }
    /**
     * <p>readedCnt を取得します。
     * @return readedCnt
     */
    public int getReadedCnt() {
        return readedCnt__;
    }
    /**
     * <p>readedCnt をセットします。
     * @param readedCnt readedCnt
     */
    public void setReadedCnt(int readedCnt) {
        readedCnt__ = readedCnt;
    }
    /**
     * <p>bbs080delTitle を取得します。
     * @return bbs080delTitle
     */
    public String getBbs080delTitle() {
        return bbs080delTitle__;
    }
    /**
     * <p>bbs080delTitle をセットします。
     * @param bbs080delTitle bbs080delTitle
     */
    public void setBbs080delTitle(String bbs080delTitle) {
        bbs080delTitle__ = bbs080delTitle;
    }
    /**
     * <p>bbs080searchUse を取得します。
     * @return bbs080searchUse
     */
    public int getBbs080searchUse() {
        return bbs080searchUse__;
    }
    /**
     * <p>bbs080searchUse をセットします。
     * @param bbs080searchUse bbs080searchUse
     */
    public void setBbs080searchUse(int bbs080searchUse) {
        bbs080searchUse__ = bbs080searchUse;
    }
    /**
     * <p>cmd を取得します。
     * @return cmd
     */
    public String getCmd() {
        return cmd__;
    }
    /**
     * <p>cmd をセットします。
     * @param cmd cmd
     */
    public void setCmd(String cmd) {
        cmd__ = cmd;
    }
    /**
     * <p>sch010SelectUsrSid を取得します。
     * @return sch010SelectUsrSid
     */
    public String getSch010SelectUsrSid() {
        return sch010SelectUsrSid__;
    }
    /**
     * <p>sch010SelectUsrSid をセットします。
     * @param sch010SelectUsrSid sch010SelectUsrSid
     */
    public void setSch010SelectUsrSid(String sch010SelectUsrSid) {
        sch010SelectUsrSid__ = sch010SelectUsrSid;
    }
    /**
     * <p>sch010SelectUsrKbn を取得します。
     * @return sch010SelectUsrKbn
     */
    public String getSch010SelectUsrKbn() {
        return sch010SelectUsrKbn__;
    }
    /**
     * <p>sch010SelectUsrKbn をセットします。
     * @param sch010SelectUsrKbn sch010SelectUsrKbn
     */
    public void setSch010SelectUsrKbn(String sch010SelectUsrKbn) {
        sch010SelectUsrKbn__ = sch010SelectUsrKbn;
    }
}
