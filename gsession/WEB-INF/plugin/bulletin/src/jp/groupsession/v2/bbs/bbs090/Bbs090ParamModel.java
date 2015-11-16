package jp.groupsession.v2.bbs.bbs090;

import java.util.List;

import jp.groupsession.v2.bbs.bbs080.Bbs080ParamModel;
import jp.groupsession.v2.usr.GSConstUser;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 掲示板 投稿登録画面の情報を保持するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bbs090ParamModel extends Bbs080ParamModel {
    /** 処理モード */
    private int bbs090cmdMode__ = 0;

    /** フォーラム名 */
    private String bbs090forumName__ = null;
    /** スレッドタイトル */
    private String bbs090threTitle__ = null;
    /** 内容 */
    private String bbs090value__ = null;
    /** 添付ファイル */
    private String[] bbs090files__ = null;

    /** 投稿者 */
    private int bbs090contributor__ = 0;
    /** 投稿者コンボ */
    private List <LabelValueBean> bbs090contributorList__ = null;
    /** 投稿者変更権限 */
    private int bbs090contributorEditKbn__ = 1;
    /** 投稿者削除区分 */
    private int bbs090contributorJKbn__ = GSConstUser.USER_JTKBN_ACTIVE;

    /** ファイルコンボ */
    private List < LabelValueBean > bbs090FileLabelList__ = null;

    /**
     * @return bbs090forumName を戻します。
     */
    public String getBbs090forumName() {
        return bbs090forumName__;
    }
    /**
     * @param bbs090forumName 設定する bbs090forumName。
     */
    public void setBbs090forumName(String bbs090forumName) {
        bbs090forumName__ = bbs090forumName;
    }
    /**
     * @return bbs090threTitle を戻します。
     */
    public String getBbs090threTitle() {
        return bbs090threTitle__;
    }
    /**
     * @param bbs090threTitle 設定する bbs090threTitle。
     */
    public void setBbs090threTitle(String bbs090threTitle) {
        bbs090threTitle__ = bbs090threTitle;
    }
    /**
     * @return bbs090cmdMode を戻します。
     */
    public int getBbs090cmdMode() {
        return bbs090cmdMode__;
    }
    /**
     * @param bbs090cmdMode 設定する bbs090cmdMode。
     */
    public void setBbs090cmdMode(int bbs090cmdMode) {
        bbs090cmdMode__ = bbs090cmdMode;
    }
    /**
     * @return bbs090files を戻します。
     */
    public String[] getBbs090files() {
        return bbs090files__;
    }
    /**
     * @param bbs090files 設定する bbs090files。
     */
    public void setBbs090files(String[] bbs090files) {
        bbs090files__ = bbs090files;
    }
    /**
     * @return bbs090value を戻します。
     */
    public String getBbs090value() {
        return bbs090value__;
    }
    /**
     * @param bbs090value 設定する bbs090value。
     */
    public void setBbs090value(String bbs090value) {
        bbs090value__ = bbs090value;
    }
    /**
     * @return bbs090FileLabelList を戻します。
     */
    public List<LabelValueBean> getBbs090FileLabelList() {
        return bbs090FileLabelList__;
    }
    /**
     * @param bbs090FileLabelList 設定する bbs090FileLabelList。
     */
    public void setBbs090FileLabelList(List<LabelValueBean> bbs090FileLabelList) {
        bbs090FileLabelList__ = bbs090FileLabelList;
    }
    /**
     * <p>bbs090contributor を取得します。
     * @return bbs090contributor
     */
    public int getBbs090contributor() {
        return bbs090contributor__;
    }
    /**
     * <p>bbs090contributor をセットします。
     * @param bbs090contributor bbs090contributor
     */
    public void setBbs090contributor(int bbs090contributor) {
        bbs090contributor__ = bbs090contributor;
    }
    /**
     * <p>bbs090contributorList を取得します。
     * @return bbs090contributorList
     */
    public List<LabelValueBean> getBbs090contributorList() {
        return bbs090contributorList__;
    }
    /**
     * <p>bbs090contributorList をセットします。
     * @param bbs090contributorList bbs090contributorList
     */
    public void setBbs090contributorList(List<LabelValueBean> bbs090contributorList) {
        bbs090contributorList__ = bbs090contributorList;
    }
    /**
     * <p>bbs090contributorEditKbn を取得します。
     * @return bbs090contributorEditKbn
     */
    public int getBbs090contributorEditKbn() {
        return bbs090contributorEditKbn__;
    }
    /**
     * <p>bbs090contributorEditKbn をセットします。
     * @param bbs090contributorEditKbn bbs090contributorEditKbn
     */
    public void setBbs090contributorEditKbn(int bbs090contributorEditKbn) {
        bbs090contributorEditKbn__ = bbs090contributorEditKbn;
    }
    /**
     * <p>bbs090contributorJKbn を取得します。
     * @return bbs090contributorJKbn
     */
    public int getBbs090contributorJKbn() {
        return bbs090contributorJKbn__;
    }
    /**
     * <p>bbs090contributorJKbn をセットします。
     * @param bbs090contributorJKbn bbs090contributorJKbn
     */
    public void setBbs090contributorJKbn(int bbs090contributorJKbn) {
        bbs090contributorJKbn__ = bbs090contributorJKbn;
    }
}