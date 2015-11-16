package jp.groupsession.v2.rng.model;

import java.util.List;

import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.model.AbstractModel;
import jp.groupsession.v2.rng.RngConst;


/**
 * <br>[機  能] 稟議の検索条件を格納するのModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class RingiSearchModel extends AbstractModel {

    /** キーワード */
    private List<String> keyword__ = null;
    /** グループSID */
    private int groupSid__ = -1;
    /** ユーザSID */
    private int userSid__ = -1;
    /** タイトル・内容検索条件 */
    private int keywordType__ = RngConst.RNG_SEARCHTYPE_AND;
    /** タイトル検索フラグ */
    private boolean titleSearchFlg__ = true;
    /** 内容検索フラグ */
    private boolean contentSearchFlg__ = true;

    /** ソート対象 */
    private int sortKey__ = -1;
    /** 並び順 */
    private int orderKey__ = -1;
    /** ソート対象2 */
    private int sortKey2__ = -1;
    /** 並び順2 */
    private int orderKey2__ = -1;

    /** ページ */
    private int page__ = 0;
    /** ページ毎の最大表示件数 */
    private int maxCnt__ = 0;
    /** 申請日時From */
    private UDate applDateFr__ = null;
    /** 申請日時To */
    private UDate applDateTo__ = null;
    /** 最終処理日時From */
    private UDate lastMagageDateFr__ = null;
    /** 最終処理日時To */
    private UDate lastMagageDateTo__ = null;
    /** 管理画面フラグ true:管理画面 false:通常画面 */
    private boolean adminFlg__ = false;
    /**
     * <p>adminFlg を取得します。
     * @return adminFlg
     */
    public boolean isAdminFlg() {
        return adminFlg__;
    }
    /**
     * <p>adminFlg をセットします。
     * @param adminFlg adminFlg
     */
    public void setAdminFlg(boolean adminFlg) {
        adminFlg__ = adminFlg;
    }
    /**
     * <p>applDateFr を取得します。
     * @return applDateFr
     */
    public UDate getApplDateFr() {
        return applDateFr__;
    }
    /**
     * <p>applDateFr をセットします。
     * @param applDateFr applDateFr
     */
    public void setApplDateFr(UDate applDateFr) {
        applDateFr__ = applDateFr;
    }
    /**
     * <p>applDateTo を取得します。
     * @return applDateTo
     */
    public UDate getApplDateTo() {
        return applDateTo__;
    }
    /**
     * <p>applDateTo をセットします。
     * @param applDateTo applDateTo
     */
    public void setApplDateTo(UDate applDateTo) {
        applDateTo__ = applDateTo;
    }
    /**
     * <p>contentSearchFlg を取得します。
     * @return contentSearchFlg
     */
    public boolean isContentSearchFlg() {
        return contentSearchFlg__;
    }
    /**
     * <p>contentSearchFlg をセットします。
     * @param contentSearchFlg contentSearchFlg
     */
    public void setContentSearchFlg(boolean contentSearchFlg) {
        contentSearchFlg__ = contentSearchFlg;
    }
    /**
     * <p>groupSid を取得します。
     * @return groupSid
     */
    public int getGroupSid() {
        return groupSid__;
    }
    /**
     * <p>groupSid をセットします。
     * @param groupSid groupSid
     */
    public void setGroupSid(int groupSid) {
        groupSid__ = groupSid;
    }
    /**
     * <p>keyword を取得します。
     * @return keyword
     */
    public List<String> getKeyword() {
        return keyword__;
    }
    /**
     * <p>keyword をセットします。
     * @param keyword keyword
     */
    public void setKeyword(List<String> keyword) {
        keyword__ = keyword;
    }
    /**
     * <p>keywordType を取得します。
     * @return keywordType
     */
    public int getKeywordType() {
        return keywordType__;
    }
    /**
     * <p>keywordType をセットします。
     * @param keywordType keywordType
     */
    public void setKeywordType(int keywordType) {
        keywordType__ = keywordType;
    }
    /**
     * <p>lastMagageDateFr を取得します。
     * @return lastMagageDateFr
     */
    public UDate getLastMagageDateFr() {
        return lastMagageDateFr__;
    }
    /**
     * <p>lastMagageDateFr をセットします。
     * @param lastMagageDateFr lastMagageDateFr
     */
    public void setLastMagageDateFr(UDate lastMagageDateFr) {
        lastMagageDateFr__ = lastMagageDateFr;
    }
    /**
     * <p>lastMagageDateTo を取得します。
     * @return lastMagageDateTo
     */
    public UDate getLastMagageDateTo() {
        return lastMagageDateTo__;
    }
    /**
     * <p>lastMagageDateTo をセットします。
     * @param lastMagageDateTo lastMagageDateTo
     */
    public void setLastMagageDateTo(UDate lastMagageDateTo) {
        lastMagageDateTo__ = lastMagageDateTo;
    }
    /**
     * <p>maxCnt を取得します。
     * @return maxCnt
     */
    public int getMaxCnt() {
        return maxCnt__;
    }
    /**
     * <p>maxCnt をセットします。
     * @param maxCnt maxCnt
     */
    public void setMaxCnt(int maxCnt) {
        maxCnt__ = maxCnt;
    }
    /**
     * <p>orderKey を取得します。
     * @return orderKey
     */
    public int getOrderKey() {
        return orderKey__;
    }
    /**
     * <p>orderKey をセットします。
     * @param orderKey orderKey
     */
    public void setOrderKey(int orderKey) {
        orderKey__ = orderKey;
    }
    /**
     * <p>orderKey2 を取得します。
     * @return orderKey2
     */
    public int getOrderKey2() {
        return orderKey2__;
    }
    /**
     * <p>orderKey2 をセットします。
     * @param orderKey2 orderKey2
     */
    public void setOrderKey2(int orderKey2) {
        orderKey2__ = orderKey2;
    }
    /**
     * <p>page を取得します。
     * @return page
     */
    public int getPage() {
        return page__;
    }
    /**
     * <p>page をセットします。
     * @param page page
     */
    public void setPage(int page) {
        page__ = page;
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
     * <p>sortKey2 を取得します。
     * @return sortKey2
     */
    public int getSortKey2() {
        return sortKey2__;
    }
    /**
     * <p>sortKey2 をセットします。
     * @param sortKey2 sortKey2
     */
    public void setSortKey2(int sortKey2) {
        sortKey2__ = sortKey2;
    }
    /**
     * <p>titleSearchFlg を取得します。
     * @return titleSearchFlg
     */
    public boolean isTitleSearchFlg() {
        return titleSearchFlg__;
    }
    /**
     * <p>titleSearchFlg をセットします。
     * @param titleSearchFlg titleSearchFlg
     */
    public void setTitleSearchFlg(boolean titleSearchFlg) {
        titleSearchFlg__ = titleSearchFlg;
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

}
