package jp.groupsession.v2.bbs.bbs060;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.bbs.bbs041.Bbs041Form;
import jp.groupsession.v2.bbs.model.BulletinDspModel;
import jp.groupsession.v2.cmn.GSValidateUtil;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

/**
 * <br>[機  能] 掲示板 スレッド一覧画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bbs060Form extends Bbs041Form {

    /** ページコンボ下 */
    private int bbs060page2__ = 0;
    /** スレッドSID */
    private int threadSid__ = 0;
    /** メインページ遷移フラグ */
    private int bbsmainFlg__ = 0;

    /** フォーラム名 */
    private String bbs060forumName__ = null;
    /** スレッド一覧 */
    private List < BulletinDspModel > threadList__ = null;
    /** フォーラム一覧 */
    private List < BulletinDspModel > forumList__ = null;
    /** 未読スレッド一覧 */
    private List<BulletinDspModel> notReadThreadList__ = null;
    /** フォーラムメンバー数 */
    private String forumMemberCount__ = null;
    /** バイナリSID */
    private Long bbs060BinSid__ = new Long(0);
    /** オーダーキー */
    private String bbs060orderKey__ = "0";
    /** ソートキー */
    private String bbs060sortKey__ = "5";

    /** 新規スレッドボタン表示フラグ */
    private boolean bbs060createDspFlg__ = false;

    /** フォーラム一覧表示フラグ */
    private int bbs060forumDspFlg__ = 0;

    /** 「全て既読にする」機能の使用可/不可 */
    private boolean bbs060mreadFlg__ = false;
    /** 表示しているフォーラム内の未読スレッド一覧 */
    private int bbs060unreadCnt__ = 0;

    /** フォーラム ディスク容量警告 */
    private int bbs060forumWarnDisk__ = 0;

    /**
     * @return bbs060forumName を戻します。
     */
    public String getBbs060forumName() {
        return bbs060forumName__;
    }
    /**
     * @param bbs060forumName 設定する bbs060forumName。
     */
    public void setBbs060forumName(String bbs060forumName) {
        bbs060forumName__ = bbs060forumName;
    }
    /**
     * @return bbs060page2 を戻します。
     */
    public int getBbs060page2() {
        return bbs060page2__;
    }
    /**
     * @param bbs060page2 設定する bbs060page2。
     */
    public void setBbs060page2(int bbs060page2) {
        bbs060page2__ = bbs060page2;
    }
    /**
     * @return threadSid を戻します。
     */
    public int getThreadSid() {
        return threadSid__;
    }
    /**
     * @param threadSid 設定する threadSid。
     */
    public void setThreadSid(int threadSid) {
        threadSid__ = threadSid;
    }
    /**
     * @return threadList を戻します。
     */
    public List < BulletinDspModel > getThreadList() {
        return threadList__;
    }
    /**
     * @param threadList 設定する threadList。
     */
    public void setThreadList(List < BulletinDspModel > threadList) {
        threadList__ = threadList;
    }
    /**
     * <p>bbsmainFlg を取得します。
     * @return bbsmainFlg
     */
    public int getBbsmainFlg() {
        return bbsmainFlg__;
    }
    /**
     * <p>bbsmainFlg をセットします。
     * @param bbsmainFlg bbsmainFlg
     */
    public void setBbsmainFlg(int bbsmainFlg) {
        bbsmainFlg__ = bbsmainFlg;
    }
    /**
     * <p>forumList を取得します。
     * @return forumList
     */
    public List<BulletinDspModel> getForumList() {
        return forumList__;
    }
    /**
     * <p>forumList をセットします。
     * @param forumList forumList
     */
    public void setForumList(List<BulletinDspModel> forumList) {
        forumList__ = forumList;
    }
    /**
     * <p>forumMemberCount を取得します。
     * @return forumMemberCount
     */
    public String getForumMemberCount() {
        return forumMemberCount__;
    }
    /**
     * <p>forumMemberCount をセットします。
     * @param forumMemberCount forumMemberCount
     */
    public void setForumMemberCount(String forumMemberCount) {
        forumMemberCount__ = forumMemberCount;
    }

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     * @return エラー
     */
    public ActionErrors validateCheck(HttpServletRequest req) {
        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;

        GsMessage gsMsg = new GsMessage();
        String textKeyWord = gsMsg.getMessage(req, "cmn.search.keyword");

        //未入力チェック
        if (StringUtil.isNullZeroString(getS_key())) {
            msg = new ActionMessage("error.input.required.text", textKeyWord);
            StrutsUtil.addMessage(errors, msg, "s_key");
            return errors;
        }

        //スペースのみチェック
        if (ValidateUtil.isSpace(getS_key())) {
            msg = new ActionMessage("error.input.spase.only", textKeyWord);
            StrutsUtil.addMessage(errors, msg, "s_key");
        }
        //先頭スペースチェック
        if (ValidateUtil.isSpaceStart(getS_key())) {
            msg = new ActionMessage("error.input.spase.start", textKeyWord);
            StrutsUtil.addMessage(errors, msg, "s_key");
        }
        //JIS第2水準チェック
        if (!GSValidateUtil.isGsJapaneaseString(getS_key())) {
            //利用不可能な文字を入力した場合
            String nstr = GSValidateUtil.getNotGsJapaneaseString(getS_key());
            msg = new ActionMessage("error.input.njapan.text",
                                    textKeyWord,
                                    nstr);
            StrutsUtil.addMessage(errors, msg, "s_key");
        }

        return errors;
    }
    /**
     * <p>bbs060BinSid を取得します。
     * @return bbs060BinSid
     */
    public Long getBbs060BinSid() {
        return bbs060BinSid__;
    }
    /**
     * <p>bbs060BinSid をセットします。
     * @param bbs060BinSid bbs060BinSid
     */
    public void setBbs060BinSid(Long bbs060BinSid) {
        bbs060BinSid__ = bbs060BinSid;
    }
    /**
     * @return bbs060orderKey
     */
    public String getBbs060orderKey() {
        return bbs060orderKey__;
    }
    /**
     * @param bbs060orderKey セットする bbs060orderKey
     */
    public void setBbs060orderKey(String bbs060orderKey) {
        bbs060orderKey__ = bbs060orderKey;
    }
    /**
     * @return bbs060sortKey
     */
    public String getBbs060sortKey() {
        return bbs060sortKey__;
    }
    /**
     * @param bbs060sortKey セットする bbs060sortKey
     */
    public void setBbs060sortKey(String bbs060sortKey) {
        bbs060sortKey__ = bbs060sortKey;
    }
    /**
     * <p>bbs060createDspFlg を取得します。
     * @return bbs060createDspFlg
     */
    public boolean isBbs060createDspFlg() {
        return bbs060createDspFlg__;
    }
    /**
     * <p>bbs060createDspFlg をセットします。
     * @param bbs060createDspFlg bbs060createDspFlg
     */
    public void setBbs060createDspFlg(boolean bbs060createDspFlg) {
        bbs060createDspFlg__ = bbs060createDspFlg;
    }
    /**
     * @return bbs060forumDspFlg
     */
    public int getBbs060forumDspFlg() {
        return bbs060forumDspFlg__;
    }
    /**
     * @param bbs060forumDspFlg セットする bbs060forumDspFlg
     */
    public void setBbs060forumDspFlg(int bbs060forumDspFlg) {
        bbs060forumDspFlg__ = bbs060forumDspFlg;
    }
    /**
     * <p>bbs060mreadFlg を取得します。
     * @return bbs060mreadFlg
     */
    public boolean isBbs060mreadFlg() {
        return bbs060mreadFlg__;
    }
    /**
     * <p>bbs060mreadFlg をセットします。
     * @param bbs060mreadFlg bbs060mreadFlg
     */
    public void setBbs060mreadFlg(boolean bbs060mreadFlg) {
        bbs060mreadFlg__ = bbs060mreadFlg;
    }
    /**
     * <p>bbs060unreadCnt を取得します。
     * @return bbs060unreadCnt
     */
    public int getBbs060unreadCnt() {
        return bbs060unreadCnt__;
    }
    /**
     * <p>bbs060unreadCnt をセットします。
     * @param bbs060unreadCnt bbs060unreadCnt
     */
    public void setBbs060unreadCnt(int bbs060unreadCnt) {
        bbs060unreadCnt__ = bbs060unreadCnt;
    }
    /**
     * <p>bbs060forumWarnDisk を取得します。
     * @return bbs060forumWarnDisk
     */
    public int getBbs060forumWarnDisk() {
        return bbs060forumWarnDisk__;
    }
    /**
     * <p>bbs060forumWarnDisk をセットします。
     * @param bbs060forumWarnDisk bbs060forumWarnDisk
     */
    public void setBbs060forumWarnDisk(int bbs060forumWarnDisk) {
        bbs060forumWarnDisk__ = bbs060forumWarnDisk;
    }
    /**
     * @return notReadThreadList
     */
    public List<BulletinDspModel> getNotReadThreadList() {
        return notReadThreadList__;
    }
    /**
     * @param notReadThreadList セットする notReadThreadList
     */
    public void setNotReadThreadList(List<BulletinDspModel> notReadThreadList) {
        notReadThreadList__ = notReadThreadList;
    }

}
