package jp.groupsession.v2.bbs.bbs070;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.bbs.BbsBiz;
import jp.groupsession.v2.bbs.GSConstBulletin;
import jp.groupsession.v2.bbs.GSValidateBulletin;
import jp.groupsession.v2.bbs.bbs060.Bbs060Form;
import jp.groupsession.v2.bbs.model.BulletinDspModel;
import jp.groupsession.v2.bbs.model.BulletinForumDiskModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.usr.GSConstUser;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 掲示板 スレッド登録画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bbs070Form extends Bbs060Form {

    /** 処理モード */
    private int bbs070cmdMode__ = 0;
    /** ページコンボ上 */
    private int bbs080page1__ = 0;
    /** 投稿SID */
    private int bbs080writeSid__ = 0;
    /** 並び順 */
    private int bbs080orderKey__ = -1;

    /** タイトル */
    private String bbs070title__ = null;
    /** 内容 */
    private String bbs070value__ = null;
    /** 添付ファイル */
    private String[] bbs070files__ = null;
    /** 掲載期限 無効初期値 */
    private int bbs070limitDisable__ = GSConstBulletin.THREAD_DISABLE;
    /** 掲載期限 例外(無効+掲載期限有り) */
    private int bbs070limitException__ = GSConstBulletin.THREAD_NOEXCEPTION;
    /** 掲載期限 */
    private int bbs070limit__ = GSConstBulletin.THREAD_LIMIT_NO;
    /** 掲載期限 年 */
    private int bbs070limitFrYear__ = -1;
    /** 掲載期限 月 */
    private int bbs070limitFrMonth__ = 1;
    /** 掲載期限 日 */
    private int bbs070limitFrDay__ = 1;
    /** 掲載期限 年 */
    private int bbs070limitYear__ = -1;
    /** 掲載期限 月 */
    private int bbs070limitMonth__ = 1;
    /** 掲載期限 日 */
    private int bbs070limitDay__ = 1;

    /** 投稿者 */
    private int bbs070contributor__ = 0;
    /** 投稿者コンボ */
    private List <LabelValueBean> bbs070contributorList__ = null;
    /** 投稿者変更権限 */
    private int bbs070contributorEditKbn__ = 1;
    /** 投稿者削除区分 */
    private int bbs070contributorJKbn__ = GSConstUser.USER_JTKBN_ACTIVE;

    /** フォーラム名 */
    private String bbs070forumName__ = null;
    /** ファイルコンボ */
    private List <LabelValueBean> bbs070FileLabelList__ = null;

    /** 年コンボ */
    private List <LabelValueBean> bbs070FrYearList__ = null;
    /** 月コンボ */
    private List <LabelValueBean> bbs070FrMonthList__ = null;
    /** 日コンボ */
    private List <LabelValueBean> bbs070FrDayList__ = null;
    /** 年コンボ */
    private List <LabelValueBean> bbs070yearList__ = null;
    /** 月コンボ */
    private List <LabelValueBean> bbs070monthList__ = null;
    /** 日コンボ */
    private List <LabelValueBean> bbs070dayList__ = null;

    /** 掲示予定遷移フラグ */
    private int bbs070BackFlg__ = 0;

    /**
     * @return bbs070cmdMode を戻します。
     */
    public int getBbs070cmdMode() {
        return bbs070cmdMode__;
    }
    /**
     * @param bbs070cmdMode 設定する bbs070cmdMode。
     */
    public void setBbs070cmdMode(int bbs070cmdMode) {
        bbs070cmdMode__ = bbs070cmdMode;
    }
    /**
     * @return bbs070files を戻します。
     */
    public String[] getBbs070files() {
        return bbs070files__;
    }
    /**
     * @param bbs070files 設定する bbs070files。
     */
    public void setBbs070files(String[] bbs070files) {
        bbs070files__ = bbs070files;
    }
    /**
     * <p>bbs070limitDisable を取得します。
     * @return bbs070limitDisable
     */
    public int getBbs070limitDisable() {
        return bbs070limitDisable__;
    }
    /**
     * <p>bbs070limitDisable をセットします。
     * @param bbs070limitDisable bbs070limitDisable
     */
    public void setBbs070limitDisable(int bbs070limitDisable) {
        bbs070limitDisable__ = bbs070limitDisable;
    }
    /**
     * <p>bbs070limitException を取得します。
     * @return bbs070limitException
     */
    public int getBbs070limitException() {
        return bbs070limitException__;
    }
    /**
     * <p>bbs070limitException をセットします。
     * @param bbs070limitException bbs070limitException
     */
    public void setBbs070limitException(int bbs070limitException) {
        bbs070limitException__ = bbs070limitException;
    }
    /**
     * @return bbs070title を戻します。
     */
    public String getBbs070title() {
        return bbs070title__;
    }
    /**
     * @param bbs070title 設定する bbs070title。
     */
    public void setBbs070title(String bbs070title) {
        bbs070title__ = bbs070title;
    }
    /**
     * @return bbs070value を戻します。
     */
    public String getBbs070value() {
        return bbs070value__;
    }
    /**
     * @param bbs070value 設定する bbs070value。
     */
    public void setBbs070value(String bbs070value) {
        bbs070value__ = bbs070value;
    }
    /**
     * @return bbs070forumName を戻します。
     */
    public String getBbs070forumName() {
        return bbs070forumName__;
    }
    /**
     * @param bbs070forumName 設定する bbs070forumName。
     */
    public void setBbs070forumName(String bbs070forumName) {
        bbs070forumName__ = bbs070forumName;
    }
    /**
     * <p>bbs070limit を取得します。
     * @return bbs070limit
     */
    public int getBbs070limit() {
        return bbs070limit__;
    }
    /**
     * <p>bbs070limit をセットします。
     * @param bbs070limit bbs070limit
     */
    public void setBbs070limit(int bbs070limit) {
        bbs070limit__ = bbs070limit;
    }
    /**
     * <p>bbs070limitDay を取得します。
     * @return bbs070limitDay
     */
    public int getBbs070limitDay() {
        return bbs070limitDay__;
    }
    /**
     * <p>bbs070limitDay をセットします。
     * @param bbs070limitDay bbs070limitDay
     */
    public void setBbs070limitDay(int bbs070limitDay) {
        bbs070limitDay__ = bbs070limitDay;
    }
    /**
     * <p>bbs070limitMonth を取得します。
     * @return bbs070limitMonth
     */
    public int getBbs070limitMonth() {
        return bbs070limitMonth__;
    }
    /**
     * <p>bbs070limitMonth をセットします。
     * @param bbs070limitMonth bbs070limitMonth
     */
    public void setBbs070limitMonth(int bbs070limitMonth) {
        bbs070limitMonth__ = bbs070limitMonth;
    }
    /**
     * <p>bbs070limitYear を取得します。
     * @return bbs070limitYear
     */
    public int getBbs070limitYear() {
        return bbs070limitYear__;
    }
    /**
     * <p>bbs070limitYear をセットします。
     * @param bbs070limitYear bbs070limitYear
     */
    public void setBbs070limitYear(int bbs070limitYear) {
        bbs070limitYear__ = bbs070limitYear;
    }
    /**
     * @return bbs070FileLabelList を戻します。
     */
    public List < LabelValueBean > getBbs070FileLabelList() {
        return bbs070FileLabelList__;
    }
    /**
     * @param bbs070FileLabelList 設定する bbs070FileLabelList。
     */
    public void setBbs070FileLabelList(List < LabelValueBean > bbs070FileLabelList) {
        bbs070FileLabelList__ = bbs070FileLabelList;
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
     * <p>bbs070dayList を取得します。
     * @return bbs070dayList
     */
    public List<LabelValueBean> getBbs070dayList() {
        return bbs070dayList__;
    }
    /**
     * <p>bbs070dayList をセットします。
     * @param bbs070dayList bbs070dayList
     */
    public void setBbs070dayList(List<LabelValueBean> bbs070dayList) {
        bbs070dayList__ = bbs070dayList;
    }
    /**
     * <p>bbs070monthList を取得します。
     * @return bbs070monthList
     */
    public List<LabelValueBean> getBbs070monthList() {
        return bbs070monthList__;
    }
    /**
     * <p>bbs070monthList をセットします。
     * @param bbs070monthList bbs070monthList
     */
    public void setBbs070monthList(List<LabelValueBean> bbs070monthList) {
        bbs070monthList__ = bbs070monthList;
    }
    /**
     * <p>bbs070yearList を取得します。
     * @return bbs070yearList
     */
    public List<LabelValueBean> getBbs070yearList() {
        return bbs070yearList__;
    }
    /**
     * <p>bbs070yearList をセットします。
     * @param bbs070yearList bbs070yearList
     */
    public void setBbs070yearList(List<LabelValueBean> bbs070yearList) {
        bbs070yearList__ = bbs070yearList;
    }
    /**
     * <p>bbs070contributor を取得します。
     * @return bbs070contributor
     */
    public int getBbs070contributor() {
        return bbs070contributor__;
    }
    /**
     * <p>bbs070contributor をセットします。
     * @param bbs070contributor bbs070contributor
     */
    public void setBbs070contributor(int bbs070contributor) {
        bbs070contributor__ = bbs070contributor;
    }
    /**
     * <p>bbs070contributorList を取得します。
     * @return bbs070contributorList
     */
    public List<LabelValueBean> getBbs070contributorList() {
        return bbs070contributorList__;
    }
    /**
     * <p>bbs070contributorList をセットします。
     * @param bbs070contributorList bbs070contributorList
     */
    public void setBbs070contributorList(List<LabelValueBean> bbs070contributorList) {
        bbs070contributorList__ = bbs070contributorList;
    }
    /**
     * <p>bbs070contributorEditKbn を取得します。
     * @return bbs070contributorEditKbn
     */
    public int getBbs070contributorEditKbn() {
        return bbs070contributorEditKbn__;
    }
    /**
     * <p>bbs070contributorEditKbn をセットします。
     * @param bbs070contributorEditKbn bbs070contributorEditKbn
     */
    public void setBbs070contributorEditKbn(int bbs070contributorEditKbn) {
        bbs070contributorEditKbn__ = bbs070contributorEditKbn;
    }
    /**
     * <p>bbs070contributorJKbn を取得します。
     * @return bbs070contributorJKbn
     */
    public int getBbs070contributorJKbn() {
        return bbs070contributorJKbn__;
    }
    /**
     * <p>bbs070contributorJKbn をセットします。
     * @param bbs070contributorJKbn bbs070contributorJKbn
     */
    public void setBbs070contributorJKbn(int bbs070contributorJKbn) {
        bbs070contributorJKbn__ = bbs070contributorJKbn;
    }
    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @param tempDir テンポラリディレクトリ
     * @return エラー
     * @throws SQLException SQL実行時例外
     * @throws UnsupportedEncodingException スレッド情報のエンコードに失敗
     * @throws IOToolsException 添付ファイルの読み込みに失敗
     */
    public ActionErrors validateCheck(Connection con, RequestModel reqMdl, String tempDir)
    throws SQLException, UnsupportedEncodingException, IOToolsException {
        ActionErrors errors = new ActionErrors();

        GsMessage gsMsg = new GsMessage(reqMdl);
        String textTitle = gsMsg.getMessage("cmn.title");
        String textContent = gsMsg.getMessage("cmn.content");
        String textFrKigen = gsMsg.getMessage("bbs.bbs070.5");
        String textToKigen = gsMsg.getMessage("bbs.bbs070.6");

        //タイトル
        GSValidateBulletin.validateTitle(errors,
                                         bbs070title__,
                                         textTitle,
                                         GSConstBulletin.MAX_LENGTH_THRETITLE);
        //内容
        GSValidateBulletin.validateValue(errors,
                                         bbs070value__,
                                         textContent,
                                         GSConstBulletin.MAX_LENGTH_THREVALUE);

        //掲示期限
        if (bbs070limit__ == GSConstBulletin.THREAD_LIMIT_YES) {

            UDate limitFrDate = new UDate();
            limitFrDate.setTime(0);
            limitFrDate.setDate(bbs070limitFrYear__, bbs070limitFrMonth__, bbs070limitFrDay__);
            UDate limitToDate = new UDate();
            limitToDate.setTime(0);
            limitToDate.setDate(bbs070limitYear__, bbs070limitMonth__, bbs070limitDay__);

            boolean fromOk = true;
            boolean toOk = true;

            //From単体チェック
            if (limitFrDate.getYear() != bbs070limitFrYear__
            || limitFrDate.getMonth() != bbs070limitFrMonth__
            || limitFrDate.getIntDay() != bbs070limitFrDay__) {
                //存在チェック
                ActionMessage msg = new ActionMessage("error.input.notfound.date", textFrKigen);
                StrutsUtil.addMessage(errors, msg, "bbs070limitFrYear");
                fromOk = false;
            }

            //To単体チェック
            if (limitToDate.getYear() != bbs070limitYear__
            || limitToDate.getMonth() != bbs070limitMonth__
            || limitToDate.getIntDay() != bbs070limitDay__) {
                //存在チェック
                ActionMessage msg = new ActionMessage("error.input.notfound.date", textToKigen);
                StrutsUtil.addMessage(errors, msg, "bbs070limitYear");
                toOk = false;
            } else if (limitToDate.compareDateYMD(new UDate()) == UDate.LARGE) {
                //過去日付チェック
                ActionMessage msg = new ActionMessage("error.input.past.date", textToKigen);
                StrutsUtil.addMessage(errors, msg, "bbs070limitYear");
                toOk = false;
            }

            if (fromOk && toOk) {
                //From~Toチェック
                if (limitToDate.compareDateYMD(limitFrDate) == UDate.LARGE) {
                    ActionMessage msg = new ActionMessage("error.input.comp.text",
                            gsMsg.getMessage("bbs.12"),
                            textFrKigen + " < " + textToKigen);
                    StrutsUtil.addMessage(errors, msg, "bbs070limit");
                }
            }


        }

        //ディスク容量チェック
        if (errors.isEmpty()) {
            BbsBiz bbsBiz = new BbsBiz();
            BulletinForumDiskModel diskData = bbsBiz.getForumDiskData(con, getBbs010forumSid());
            if (diskData.getBfiDisk() == GSConstBulletin.BFI_DISK_LIMITED) {
                long limitSize = diskData.getBfiDiskSize() * 1024 * 1024;
                long diskSize = diskData.getBfsSize();
                diskSize += bbsBiz.getThreadSize(getBbs070title());
                diskSize += bbsBiz.getWriteSize(getBbs070value());
                diskSize += bbsBiz.getWriteTempFileSize(tempDir);
                if (getBbs070cmdMode() == GSConstBulletin.BBSCMDMODE_EDIT) {
                    //変更前の投稿サイズを差し引く
                    BulletinDspModel bbsMdl = bbsBiz.getThreadData(con, getThreadSid());
                    diskSize -= bbsBiz.getThreadSize(bbsMdl.getBtiTitle());
                    diskSize -= bbsBiz.getWriteSize(con, getBbs080writeSid());
                }

                if (diskSize > limitSize) {
                    ActionMessage msg
                        = new ActionMessage("error.over.limitsize.bbsdata",
                                                        gsMsg.getMessage("bbs.2"));
                    StrutsUtil.addMessage(errors, msg, "bbs070limitSize");
                }
            }
        }
        return errors;
    }
    /**
     * <p>bbs070limitFrYear を取得します。
     * @return bbs070limitFrYear
     */
    public int getBbs070limitFrYear() {
        return bbs070limitFrYear__;
    }
    /**
     * <p>bbs070limitFrYear をセットします。
     * @param bbs070limitFrYear bbs070limitFrYear
     */
    public void setBbs070limitFrYear(int bbs070limitFrYear) {
        bbs070limitFrYear__ = bbs070limitFrYear;
    }
    /**
     * <p>bbs070limitFrMonth を取得します。
     * @return bbs070limitFrMonth
     */
    public int getBbs070limitFrMonth() {
        return bbs070limitFrMonth__;
    }
    /**
     * <p>bbs070limitFrMonth をセットします。
     * @param bbs070limitFrMonth bbs070limitFrMonth
     */
    public void setBbs070limitFrMonth(int bbs070limitFrMonth) {
        bbs070limitFrMonth__ = bbs070limitFrMonth;
    }
    /**
     * <p>bbs070limitFrDay を取得します。
     * @return bbs070limitFrDay
     */
    public int getBbs070limitFrDay() {
        return bbs070limitFrDay__;
    }
    /**
     * <p>bbs070limitFrDay をセットします。
     * @param bbs070limitFrDay bbs070limitFrDay
     */
    public void setBbs070limitFrDay(int bbs070limitFrDay) {
        bbs070limitFrDay__ = bbs070limitFrDay;
    }
    /**
     * <p>bbs070FrYearList を取得します。
     * @return bbs070FrYearList
     */
    public List<LabelValueBean> getBbs070FrYearList() {
        return bbs070FrYearList__;
    }
    /**
     * <p>bbs070FrYearList をセットします。
     * @param bbs070FrYearList bbs070FrYearList
     */
    public void setBbs070FrYearList(List<LabelValueBean> bbs070FrYearList) {
        bbs070FrYearList__ = bbs070FrYearList;
    }
    /**
     * <p>bbs070FrMonthList を取得します。
     * @return bbs070FrMonthList
     */
    public List<LabelValueBean> getBbs070FrMonthList() {
        return bbs070FrMonthList__;
    }
    /**
     * <p>bbs070FrMonthList をセットします。
     * @param bbs070FrMonthList bbs070FrMonthList
     */
    public void setBbs070FrMonthList(List<LabelValueBean> bbs070FrMonthList) {
        bbs070FrMonthList__ = bbs070FrMonthList;
    }
    /**
     * <p>bbs070FrDayList を取得します。
     * @return bbs070FrDayList
     */
    public List<LabelValueBean> getBbs070FrDayList() {
        return bbs070FrDayList__;
    }
    /**
     * <p>bbs070FrDayList をセットします。
     * @param bbs070FrDayList bbs070FrDayList
     */
    public void setBbs070FrDayList(List<LabelValueBean> bbs070FrDayList) {
        bbs070FrDayList__ = bbs070FrDayList;
    }
    /**
     * <p>bbs070BackFlg を取得します。
     * @return bbs070BackFlg
     */
    public int getBbs070BackFlg() {
        return bbs070BackFlg__;
    }
    /**
     * <p>bbs070BackFlg をセットします。
     * @param bbs070BackFlg bbs070BackFlg
     */
    public void setBbs070BackFlg(int bbs070BackFlg) {
        bbs070BackFlg__ = bbs070BackFlg;
    }
}
