package jp.groupsession.v2.bbs.bbs170;

import jp.groupsession.v2.bbs.bbs060.Bbs060ParamModel;

/**
 * <br>[機  能] 掲示板 スレッド一覧画面の情報を保持するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bbs170ParamModel extends Bbs060ParamModel {

    /** ページコンボ上 */
    private int bbs170page1__ = 0;
    /** ページコンボ下 */
    private int bbs170page2__ = 0;
    /** 投稿SID */
    private int bbs170writeSid__ = 0;
    /** フォーラム名 */
    private String bbs170forumName__ = null;
    /** バイナリSID */
    private Long bbs170BinSid__ = new Long(0);
    /** オーダーキー */
    private String bbs170orderKey__ = "1";
    /** ソートキー */
    private String bbs170sortKey__ = "7";
    /** フォーラム ディスク容量警告 */
    private int bbs170forumWarnDisk__ = 0;

    /**
     * <p>bbs170page1 を取得します。
     * @return bbs170page1
     */
    public int getBbs170page1() {
        return bbs170page1__;
    }
    /**
     * <p>bbs170page1 をセットします。
     * @param bbs170page1 bbs170page1
     */
    public void setBbs170page1(int bbs170page1) {
        bbs170page1__ = bbs170page1;
    }
    /**
     * <p>bbs170page2 を取得します。
     * @return bbs170page2
     */
    public int getBbs170page2() {
        return bbs170page2__;
    }
    /**
     * <p>bbs170page2 をセットします。
     * @param bbs170page2 bbs170page2
     */
    public void setBbs170page2(int bbs170page2) {
        bbs170page2__ = bbs170page2;
    }
    /**
     * <p>bbs170forumName を取得します。
     * @return bbs170forumName
     */
    public String getBbs170forumName() {
        return bbs170forumName__;
    }
    /**
     * <p>bbs170forumName をセットします。
     * @param bbs170forumName bbs170forumName
     */
    public void setBbs170forumName(String bbs170forumName) {
        bbs170forumName__ = bbs170forumName;
    }
    /**
     * <p>bbs170BinSid を取得します。
     * @return bbs170BinSid
     */
    public Long getBbs170BinSid() {
        return bbs170BinSid__;
    }
    /**
     * <p>bbs170BinSid をセットします。
     * @param bbs170BinSid bbs170BinSid
     */
    public void setBbs170BinSid(Long bbs170BinSid) {
        bbs170BinSid__ = bbs170BinSid;
    }
    /**
     * <p>bbs170orderKey を取得します。
     * @return bbs170orderKey
     */
    public String getBbs170orderKey() {
        return bbs170orderKey__;
    }
    /**
     * <p>bbs170orderKey をセットします。
     * @param bbs170orderKey bbs170orderKey
     */
    public void setBbs170orderKey(String bbs170orderKey) {
        bbs170orderKey__ = bbs170orderKey;
    }
    /**
     * <p>bbs170sortKey を取得します。
     * @return bbs170sortKey
     */
    public String getBbs170sortKey() {
        return bbs170sortKey__;
    }
    /**
     * <p>bbs170sortKey をセットします。
     * @param bbs170sortKey bbs170sortKey
     */
    public void setBbs170sortKey(String bbs170sortKey) {
        bbs170sortKey__ = bbs170sortKey;
    }
    /**
     * <p>bbs170writeSid を取得します。
     * @return bbs170writeSid
     */
    public int getBbs170writeSid() {
        return bbs170writeSid__;
    }
    /**
     * <p>bbs170writeSid をセットします。
     * @param bbs170writeSid bbs170writeSid
     */
    public void setBbs170writeSid(int bbs170writeSid) {
        bbs170writeSid__ = bbs170writeSid;
    }
    /**
     * <p>bbs170forumWarnDisk を取得します。
     * @return bbs170forumWarnDisk
     */
    public int getBbs170forumWarnDisk() {
        return bbs170forumWarnDisk__;
    }
    /**
     * <p>bbs170forumWarnDisk をセットします。
     * @param bbs170forumWarnDisk bbs170forumWarnDisk
     */
    public void setBbs170forumWarnDisk(int bbs170forumWarnDisk) {
        bbs170forumWarnDisk__ = bbs170forumWarnDisk;
    }
}