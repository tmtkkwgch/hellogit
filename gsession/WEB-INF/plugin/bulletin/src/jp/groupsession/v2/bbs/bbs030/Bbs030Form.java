package jp.groupsession.v2.bbs.bbs030;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.bbs.GSConstBulletin;
import jp.groupsession.v2.bbs.bbs020.Bbs020Form;
import jp.groupsession.v2.cmn.GSValidateUtil;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;


/**
 * <br>[機  能] 掲示板 フォーラム登録画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bbs030Form extends Bbs020Form {

    /** 経過年ラベルの選択値 */
    public static final String[] YEAR_VALUE
        = new String[] {"0", "1", "2", "3", "4", "5", "10"};
    /** 経過月ラベルの選択値 */
    public static final String[] MONTH_VALUE
        = new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};


    /** プラグインID */
    private String bbs030pluginId__ = GSConstBulletin.PLUGIN_ID_BULLETIN;

    /** 処理モード */
    private int bbs030cmdMode__ = 0;

    /** フォーラム名 */
    private String bbs030forumName__ = null;
    /** コメント */
    private String bbs030comment__ = null;
    /** グループSID */
    private int bbs030groupSid__ = Integer.parseInt(GSConstBulletin.GROUP_COMBO_VALUE);
    /** メンバーSID 追加・編集・削除 */
    private String[] bbs030memberSid__ = new String[0];
    /** メンバーSID 閲覧 */
    private String[] bbs030memberSidRead__ = new String[0];


    /** グループ一覧 */
    private List < LabelValueBean > bbs030GroupList__ = null;
    /** 追加済みユーザ一覧 */
    private List < LabelValueBean > bbs030LeftUserList__ = null;
    /** 追加済みユーザ一覧 */
    private List < LabelValueBean > bbs030LeftUserListRead__ = null;
    /** 追加用ユーザ一覧 */
    private List < LabelValueBean > bbs030RightUserList__ = null;

    /** 追加済みメンバー(選択) 追加・編集・削除 */
    private String[] bbs030SelectLeftUser__ = new String[0];
    /** 追加済みメンバー(選択) 閲覧 */
    private String[] bbs030SelectLeftUserRead__ = new String[0];
    /** 追加用メンバー(選択) */
    private String[] bbs030SelectRightUser__ = new String[0];


    /** グループSID 管理者 */
    private int bbs030groupSidAdm__ = Integer.parseInt(GSConstBulletin.GROUP_COMBO_VALUE_USER);
    /** メンバーSID 管理者 */
    private String[] bbs030memberSidAdm__ = new String[0];
    /** グループ一覧 管理者 */
    private List < LabelValueBean > bbs030GroupListAdm__ = null;
    /** 追加済みユーザ一覧 管理者 */
    private List < LabelValueBean > bbs030LeftUserListAdm__ = null;
    /** 追加用ユーザ一覧 管理者 */
    private List < LabelValueBean > bbs030RightUserListAdm__ = null;
    /** 追加済みメンバー(選択) 管理者 */
    private String[] bbs030SelectLeftUserAdm__ = new String[0];
    /** 追加用メンバー(選択) 管理者 */
    private String[] bbs030SelectRightUserAdm__ = new String[0];


    /** 投稿許可 */
    private String bbs030reply__ = String.valueOf(GSConstBulletin.BBS_THRE_REPLY_YES);
    /** 新規ユーザスレッド閲覧状態 */
    private String bbs030read__ = String.valueOf(GSConstBulletin.NEWUSER_THRE_VIEW_NO);
    /** 投稿許可 */
    private String bbs030mread__ = String.valueOf(GSConstBulletin.BBS_FORUM_MREAD_NO);
    /** スレッドテンプレート区分 */
    private int bbs030templateKbn__ = GSConstBulletin.BBS_THRE_TEMPLATE_NO;
    /** スレッドテンプレート */
    private String bbs030template__ = null;
    /** スレッドテンプレート 投稿時使用区分 */
    private int bbs030templateWriteKbn__ = GSConstBulletin.BBS_THRE_TEMPLATE_WRITE_NO;
    /** ディスク容量 */
    private int bbs030diskSize__ = GSConstBulletin.BFI_DISK_NOLIMIT;
    /** ディスク容量 最大値 */
    private String bbs030diskSizeLimit__ = null;
    /** ディスク容量警告 閾値 */
    private int bbs030warnDisk__ = 0;
    /** ディスク容量警告 閾値 */
    private int bbs030warnDiskThreshold__ = 0;

    /** 掲示期間 有効初期値 */
    private int bbs030LimitDisable__ = GSConstBulletin.THREAD_ENABLE;
    /** 掲示期間 初期値 */
    private int bbs030Limit__ = GSConstBulletin.THREAD_LIMIT_NO;
    /** 掲示期間日数 初期値 */
    private String bbs030LimitDate__ = null;
    /** スレッド保存期間設定 */
    private int bbs030Keep__ = GSConstBulletin.THREAD_KEEP_NO;
    /** スレッド保存期間 年 */
    private int bbs030KeepDateY__ = -1;
    /** スレッド保存期間 月 */
    private int bbs030KeepDateM__ = -1;
    /** スレッド保存期間 自動削除設定内容表示フラグ */
    private int bbs030DspAtdelFlg__ = GSConstBulletin.AUTO_DELETE_OFF;
    /** スレッド保存期間 表示用 自動削除設定内容 経過年 */
    private int bbs030DspAtdelYear__ = -1;
    /** スレッド保存期間 表示用 自動削除設定内容  経過月 */
    private int bbs030DspAtdelMonth__ = -1;

    /** 経過年ラベル */
    private List < LabelValueBean > bbs030KeepDateYLabel__ = null;
    /** 経過月ラベル */
    private List < LabelValueBean > bbs030KeepDateMLabel__ = null;

    /** 写真ファイル名 */
    private String bbs030ImageName__;
    /** 写真ファイル保存名 */
    private String bbs030ImageSaveName__;
    /** バイナリSID */
    private Long bbs030BinSid__ = new Long(0);

    /** ディスク容量警告 閾値 選択値 */
    private List<LabelValueBean> warnDiskThresholdList__ = null;

    /**
     * <p>bbs030Read を取得します。
     * @return bbs030Read
     */
    public String getBbs030read() {
        return bbs030read__;
    }

    /**
     * <p>bbs030Read をセットします。
     * @param bbs030read bbs030read
     */
    public void setBbs030read(String bbs030read) {
        bbs030read__ = bbs030read;
    }

    /**
     * <p>bbs030Reply を取得します。
     * @return bbs030Reply
     */
    public String getBbs030reply() {
        return bbs030reply__;
    }

    /**
     * <p>bbs030Reply をセットします。
     * @param bbs030reply bbs030reply
     */
    public void setBbs030reply(String bbs030reply) {
        bbs030reply__ = bbs030reply;
    }

    /**
     * @return bbs030cmdMode を戻します。
     */
    public int getBbs030cmdMode() {
        return bbs030cmdMode__;
    }

    /**
     * @param bbs030cmdMode 設定する bbs030cmdMode。
     */
    public void setBbs030cmdMode(int bbs030cmdMode) {
        bbs030cmdMode__ = bbs030cmdMode;
    }

    /**
     * @return bbs030comment を戻します。
     */
    public String getBbs030comment() {
        return bbs030comment__;
    }

    /**
     * @param bbs030comment 設定する bbs030comment。
     */
    public void setBbs030comment(String bbs030comment) {
        bbs030comment__ = bbs030comment;
    }

    /**
     * @return bbs030forumName を戻します。
     */
    public String getBbs030forumName() {
        return bbs030forumName__;
    }

    /**
     * @param bbs030forumName 設定する bbs030forumName。
     */
    public void setBbs030forumName(String bbs030forumName) {
        bbs030forumName__ = bbs030forumName;
    }

    /**
     * @return bbs030GroupList を戻します。
     */
    public List < LabelValueBean > getBbs030GroupList() {
        return bbs030GroupList__;
    }

    /**
     * @param bbs030GroupList 設定する bbs030GroupList。
     */
    public void setBbs030GroupList(List < LabelValueBean > bbs030GroupList) {
        bbs030GroupList__ = bbs030GroupList;
    }

    /**
     * @return bbs030groupSid を戻します。
     */
    public int getBbs030groupSid() {
        return bbs030groupSid__;
    }

    /**
     * @param bbs030groupSid 設定する bbs030groupSid。
     */
    public void setBbs030groupSid(int bbs030groupSid) {
        bbs030groupSid__ = bbs030groupSid;
    }

    /**
     * @return bbs030LeftUserList を戻します。
     */
    public List < LabelValueBean > getBbs030LeftUserList() {
        return bbs030LeftUserList__;
    }

    /**
     * @param bbs030LeftUserList 設定する bbs030LeftUserList。
     */
    public void setBbs030LeftUserList(List < LabelValueBean > bbs030LeftUserList) {
        bbs030LeftUserList__ = bbs030LeftUserList;
    }

    /**
     * @return bbs030memberSid を戻します。
     */
    public String[] getBbs030memberSid() {
        return bbs030memberSid__;
    }

    /**
     * @param bbs030memberSid 設定する bbs030memberSid。
     */
    public void setBbs030memberSid(String[] bbs030memberSid) {
        bbs030memberSid__ = bbs030memberSid;
    }

    /**
     * @return bbs030RightUserList を戻します。
     */
    public List < LabelValueBean > getBbs030RightUserList() {
        return bbs030RightUserList__;
    }

    /**
     * @param bbs030RightUserList 設定する bbs030RightUserList。
     */
    public void setBbs030RightUserList(List < LabelValueBean > bbs030RightUserList) {
        bbs030RightUserList__ = bbs030RightUserList;
    }

    /**
     * @return bbs030SelectLeftUser を戻します。
     */
    public String[] getBbs030SelectLeftUser() {
        return bbs030SelectLeftUser__;
    }

    /**
     * @param bbs030SelectLeftUser 設定する bbs030SelectLeftUser。
     */
    public void setBbs030SelectLeftUser(String[] bbs030SelectLeftUser) {
        bbs030SelectLeftUser__ = bbs030SelectLeftUser;
    }

    /**
     * @return bbs030SelectRightUser を戻します。
     */
    public String[] getBbs030SelectRightUser() {
        return bbs030SelectRightUser__;
    }

    /**
     * @param bbs030SelectRightUser 設定する bbs030SelectRightUser。
     */
    public void setBbs030SelectRightUser(String[] bbs030SelectRightUser) {
        bbs030SelectRightUser__ = bbs030SelectRightUser;
    }

    /**
     * <p>bbs030pluginId を取得します。
     * @return bbs030pluginId
     */
    public String getBbs030pluginId() {
        return bbs030pluginId__;
    }

    /**
     * <p>bbs030pluginId をセットします。
     * @param bbs030pluginId bbs030pluginId
     */
    public void setBbs030pluginId(String bbs030pluginId) {
        bbs030pluginId__ = bbs030pluginId;
    }

    /**
     * <p>bbs030ImageName を取得します。
     * @return bbs030ImageName
     */
    public String getBbs030ImageName() {
        return bbs030ImageName__;
    }

    /**
     * <p>bbs030ImageName をセットします。
     * @param bbs030ImageName bbs030ImageName
     */
    public void setBbs030ImageName(String bbs030ImageName) {
        bbs030ImageName__ = bbs030ImageName;
    }

    /**
     * <p>bbs030ImageSaveName を取得します。
     * @return bbs030ImageSaveName
     */
    public String getBbs030ImageSaveName() {
        return bbs030ImageSaveName__;
    }

    /**
     * <p>bbs030ImageSaveName をセットします。
     * @param bbs030ImageSaveName bbs030ImageSaveName
     */
    public void setBbs030ImageSaveName(String bbs030ImageSaveName) {
        bbs030ImageSaveName__ = bbs030ImageSaveName;
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

        //-- フォーラム名チェック --
        GsMessage gsMsg = new GsMessage();
        String textForumName = gsMsg.getMessage(req, "bbs.4");
        String textComment = gsMsg.getMessage(req, "cmn.comment");
        String textTemplate = gsMsg.getMessage(req, "bbs.bbs030.6");

        //未入力チェック
        if (StringUtil.isNullZeroString(bbs030forumName__)) {
            msg = new ActionMessage("error.input.required.text",
                    textForumName);
            StrutsUtil.addMessage(errors, msg, "bbs030forumName");

        //桁数チェック
        } else if (bbs030forumName__.length() > GSConstBulletin.MAX_LENGTH_FORUMNAME) {
            msg = new ActionMessage("error.input.length.text",
                                textForumName,
                                String.valueOf(GSConstBulletin.MAX_LENGTH_FORUMNAME));
            StrutsUtil.addMessage(errors, msg, "bbs030forumName");

        //スペースのみチェック
        } else {
            if (ValidateUtil.isSpace(bbs030forumName__)) {
                msg = new ActionMessage("error.input.spase.only", textForumName);
                StrutsUtil.addMessage(errors, msg, "bbs030forumName");
            }
            //先頭スペースチェック
            if (ValidateUtil.isSpaceStart(bbs030forumName__)) {
                msg = new ActionMessage("error.input.spase.start",
                                        textForumName);
                StrutsUtil.addMessage(errors, msg, "bbs030forumName");
            }

            //タブ文字が含まれている
            if (ValidateUtil.isTab(bbs030forumName__)) {
                String msgKey = "error.input.tab.text";
                msg = new ActionMessage(msgKey, textForumName);
                StrutsUtil.addMessage(
                        errors, msg, "bbs030forumName");
            }

            //JIS第2水準チェック
            if (!GSValidateUtil.isGsJapaneaseString(bbs030forumName__)) {
                //利用不可能な文字を入力した場合
                String nstr = GSValidateUtil.getNotGsJapaneaseString(bbs030forumName__);
                msg = new ActionMessage("error.input.njapan.text",
                                        textForumName,
                                        nstr);
                StrutsUtil.addMessage(errors, msg, "bbs030forumName");
            }
        }

        //-- コメントチェック --
        if (!StringUtil.isNullZeroString(bbs030comment__)) {
            //桁数チェック
            if (bbs030comment__.length() > GSConstBulletin.MAX_LENGTH_FORUMCOMMENT) {
                msg = new ActionMessage("error.input.length.textarea",
                                    textComment,
                                    String.valueOf(GSConstBulletin.MAX_LENGTH_FORUMCOMMENT));
                StrutsUtil.addMessage(errors, msg, "bbs030comment");
                return errors;
            }
            //スペース、改行のみチェック
            if (ValidateUtil.isSpaceOrKaigyou(bbs030comment__)) {
                msg = new ActionMessage("error.input.spase.cl.only", textComment);
                StrutsUtil.addMessage(errors, msg, "bbs030comment");
            }

            //JIS第2水準チェック
            if (!GSValidateUtil.isGsJapaneaseStringTextArea(bbs030comment__)) {
                //利用不可能な文字を入力した場合
                String nstr = GSValidateUtil.getNotGsJapaneaseStringTextArea(bbs030comment__);
                msg = new ActionMessage("error.input.njapan.text", textComment, nstr);
                StrutsUtil.addMessage(errors, msg, "bbs030comment");
            }
        }

        //ディスク容量最大値入力チェック
        //制限ありのときエラーチェック
        if (bbs030diskSize__ == 1) {
            String fieldFix = "bbs030diskSizeLimit.";
            String paramNameJpn = gsMsg.getMessage("wml.87");

            if (StringUtil.isNullZeroString(bbs030diskSizeLimit__)) {
                //未入力チェック
                msg = new ActionMessage("error.input.required.text", paramNameJpn);
                StrutsUtil.addMessage(
                        errors, msg, fieldFix + "error.input.required.text");
            } else {

                if (bbs030diskSizeLimit__.length() > GSConstBulletin.MAX_LENGTH_FORUM_DISK) {
                    //MAX桁チェック
                    msg = new ActionMessage("error.input.length.text",
                            paramNameJpn, GSConstBulletin.MAX_LENGTH_FORUM_DISK);
                    StrutsUtil.addMessage(
                            errors, msg, fieldFix + "error.input.length.text");
                }

                if (!GSValidateUtil.isNumber(bbs030diskSizeLimit__)) {
                    //半角数字チェック
                    String msgKey = "error.input.number.hankaku";
                    msg = new ActionMessage(msgKey, paramNameJpn);
                    StrutsUtil.addMessage(
                            errors, msg, fieldFix + msgKey);
                }
            }

        }

        //-- テンプレートチェック --
        if (bbs030templateKbn__ == GSConstBulletin.BBS_THRE_TEMPLATE_YES
                && !StringUtil.isNullZeroString(bbs030template__)) {
            //桁数チェック
            if (bbs030template__.length() > GSConstBulletin.MAX_LENGTH_THREVALUE) {
                msg = new ActionMessage("error.input.length.textarea",
                                    textTemplate,
                                    String.valueOf(GSConstBulletin.MAX_LENGTH_THREVALUE));
                StrutsUtil.addMessage(errors, msg, "bbs030template");
                return errors;
            }
            //スペース、改行のみチェック
            if (ValidateUtil.isSpaceOrKaigyou(bbs030template__)) {
                msg = new ActionMessage("error.input.spase.cl.only", textTemplate);
                StrutsUtil.addMessage(errors, msg, "bbs030template");
            }

            //JIS第2水準チェック
            if (!GSValidateUtil.isGsJapaneaseStringTextArea(bbs030template__)) {
                //利用不可能な文字を入力した場合
                String nstr = GSValidateUtil.getNotGsJapaneaseStringTextArea(bbs030template__);
                msg = new ActionMessage("error.input.njapan.text", textTemplate, nstr);
                StrutsUtil.addMessage(errors, msg, "bbs030template");
            }
        }

        //掲示期間 有効
        if (bbs030LimitDisable__ == GSConstBulletin.THREAD_ENABLE) {
            //掲示期間日数 初期値
            if (bbs030Limit__ == GSConstBulletin.THREAD_LIMIT_YES) {

                String fieldFix = "bbs030LimitDate.";
                String paramNameJpn = gsMsg.getMessage("bbs.12");

                if (StringUtil.isNullZeroString(bbs030LimitDate__)) {
                    //未入力チェック
                    msg = new ActionMessage("error.input.required.text", paramNameJpn);
                    StrutsUtil.addMessage(
                            errors, msg, fieldFix + "error.input.required.text");
                } else {

                    if (bbs030LimitDate__.length() > 3) {
                        //MAX桁チェック
                        msg = new ActionMessage("error.input.length.text",
                                paramNameJpn, 3);
                        StrutsUtil.addMessage(
                                errors, msg, fieldFix + "error.input.length.text");
                    }

                    if (!GSValidateUtil.isNumber(bbs030LimitDate__)) {
                        //半角数字チェック
                        String msgKey = "error.input.number.hankaku";
                        msg = new ActionMessage(msgKey, paramNameJpn);
                        StrutsUtil.addMessage(
                                errors, msg, fieldFix + msgKey);
                    }
                }
            }

            //スレッド保存期間
            if (bbs030Keep__ == GSConstBulletin.THREAD_KEEP_YES) {
                //保存気
                boolean yFlg = false;
                for (String sy : YEAR_VALUE) {
                    int iy = Integer.parseInt(sy);
                    if (bbs030KeepDateY__ == iy) {
                        yFlg = true;
                        break;
                    }
                }
                if (yFlg == false) {
                    String year = gsMsg.getMessage(req, "bbs.bbs030.14")
                            + " (" + gsMsg.getMessage(req, "cmn.year2") + ")";
                    String prefix = "bbs030KeepDateY";
                    msg = new ActionMessage("error.input.notvalidate.data", year);
                    errors.add(prefix + "error.input.notvalidate.data", msg);
                }



                //経過月
                boolean mFlg = false;
                for (String sm : MONTH_VALUE) {
                    int im = Integer.parseInt(sm);
                    if (bbs030KeepDateM__ == im) {
                        mFlg = true;
                        break;
                    }
                }

                if (mFlg == false) {
                    String month = gsMsg.getMessage(req, "bbs.bbs030.14")
                            + " (" + gsMsg.getMessage(req, "cmn.month") + ")";

                    String prefix = "bbs030KeepDateM";
                    msg = new ActionMessage("error.input.notvalidate.data", month);
                    errors.add(prefix + "error.input.notvalidate.data", msg);
                }

                //経過年、月
                if (yFlg && mFlg) {
                    //
                    if (bbs030KeepDateY__ == 0 && bbs030KeepDateM__ == 0) {
                        //
                        String yearMonth = gsMsg.getMessage(req, "bbs.bbs030.14");
                        String months = gsMsg.getMessage(req, "cmn.months", "1");
                        String prefix = "bbs030KeepDateYM";
                        msg = new ActionMessage("error.input.range0over.data", yearMonth, months);
                        errors.add(prefix + "error.input.range0over.data", msg);
                    }
                }
            }
        }
//        /** スレッド保存期間設定 */
//        private int bbs030Keep__ = GSConstBulletin.THREAD_KEEP_NO;
//        /** スレッド保存期間 経過年 */
//        private int bbs030KeepDateY__ = -1;
//        /** スレッド保存期間 経過月 */
//        private int bbs030KeepDateM__ = -1;

        return errors;
    }

    /**
     * <p>bbs030BinSid を取得します。
     * @return bbs030BinSid
     */
    public Long getBbs030BinSid() {
        return bbs030BinSid__;
    }

    /**
     * <p>bbs030BinSid をセットします。
     * @param bbs030BinSid bbs030BinSid
     */
    public void setBbs030BinSid(Long bbs030BinSid) {
        bbs030BinSid__ = bbs030BinSid;
    }

    /**
     * <p>bbs030memberSidRead を取得します。
     * @return bbs030memberSidRead
     */
    public String[] getBbs030memberSidRead() {
        return bbs030memberSidRead__;
    }

    /**
     * <p>bbs030memberSidRead をセットします。
     * @param bbs030memberSidRead bbs030memberSidRead
     */
    public void setBbs030memberSidRead(String[] bbs030memberSidRead) {
        bbs030memberSidRead__ = bbs030memberSidRead;
    }

    /**
     * <p>bbs030SelectLeftUserRead を取得します。
     * @return bbs030SelectLeftUserRead
     */
    public String[] getBbs030SelectLeftUserRead() {
        return bbs030SelectLeftUserRead__;
    }

    /**
     * <p>bbs030SelectLeftUserRead をセットします。
     * @param bbs030SelectLeftUserRead bbs030SelectLeftUserRead
     */
    public void setBbs030SelectLeftUserRead(String[] bbs030SelectLeftUserRead) {
        bbs030SelectLeftUserRead__ = bbs030SelectLeftUserRead;
    }

    /**
     * <p>bbs030groupSidAdm を取得します。
     * @return bbs030groupSidAdm
     */
    public int getBbs030groupSidAdm() {
        return bbs030groupSidAdm__;
    }

    /**
     * <p>bbs030groupSidAdm をセットします。
     * @param bbs030groupSidAdm bbs030groupSidAdm
     */
    public void setBbs030groupSidAdm(int bbs030groupSidAdm) {
        bbs030groupSidAdm__ = bbs030groupSidAdm;
    }

    /**
     * <p>bbs030memberSidAdm を取得します。
     * @return bbs030memberSidAdm
     */
    public String[] getBbs030memberSidAdm() {
        return bbs030memberSidAdm__;
    }

    /**
     * <p>bbs030memberSidAdm をセットします。
     * @param bbs030memberSidAdm bbs030memberSidAdm
     */
    public void setBbs030memberSidAdm(String[] bbs030memberSidAdm) {
        bbs030memberSidAdm__ = bbs030memberSidAdm;
    }

    /**
     * <p>bbs030GroupListAdm を取得します。
     * @return bbs030GroupListAdm
     */
    public List<LabelValueBean> getBbs030GroupListAdm() {
        return bbs030GroupListAdm__;
    }

    /**
     * <p>bbs030GroupListAdm をセットします。
     * @param bbs030GroupListAdm bbs030GroupListAdm
     */
    public void setBbs030GroupListAdm(List<LabelValueBean> bbs030GroupListAdm) {
        bbs030GroupListAdm__ = bbs030GroupListAdm;
    }

    /**
     * <p>bbs030LeftUserListAdm を取得します。
     * @return bbs030LeftUserListAdm
     */
    public List<LabelValueBean> getBbs030LeftUserListAdm() {
        return bbs030LeftUserListAdm__;
    }

    /**
     * <p>bbs030LeftUserListAdm をセットします。
     * @param bbs030LeftUserListAdm bbs030LeftUserListAdm
     */
    public void setBbs030LeftUserListAdm(List<LabelValueBean> bbs030LeftUserListAdm) {
        bbs030LeftUserListAdm__ = bbs030LeftUserListAdm;
    }

    /**
     * <p>bbs030RightUserListAdm を取得します。
     * @return bbs030RightUserListAdm
     */
    public List<LabelValueBean> getBbs030RightUserListAdm() {
        return bbs030RightUserListAdm__;
    }

    /**
     * <p>bbs030RightUserListAdm をセットします。
     * @param bbs030RightUserListAdm bbs030RightUserListAdm
     */
    public void setBbs030RightUserListAdm(
            List<LabelValueBean> bbs030RightUserListAdm) {
        bbs030RightUserListAdm__ = bbs030RightUserListAdm;
    }

    /**
     * <p>bbs030LeftUserListRead を取得します。
     * @return bbs030LeftUserListRead
     */
    public List<LabelValueBean> getBbs030LeftUserListRead() {
        return bbs030LeftUserListRead__;
    }

    /**
     * <p>bbs030LeftUserListRead をセットします。
     * @param bbs030LeftUserListRead bbs030LeftUserListRead
     */
    public void setBbs030LeftUserListRead(
            List<LabelValueBean> bbs030LeftUserListRead) {
        bbs030LeftUserListRead__ = bbs030LeftUserListRead;
    }

    /**
     * <p>bbs030SelectRightUserAdm を取得します。
     * @return bbs030SelectRightUserAdm
     */
    public String[] getBbs030SelectRightUserAdm() {
        return bbs030SelectRightUserAdm__;
    }

    /**
     * <p>bbs030SelectRightUserAdm をセットします。
     * @param bbs030SelectRightUserAdm bbs030SelectRightUserAdm
     */
    public void setBbs030SelectRightUserAdm(String[] bbs030SelectRightUserAdm) {
        bbs030SelectRightUserAdm__ = bbs030SelectRightUserAdm;
    }

    /**
     * <p>bbs030SelectLeftUserAdm を取得します。
     * @return bbs030SelectLeftUserAdm
     */
    public String[] getBbs030SelectLeftUserAdm() {
        return bbs030SelectLeftUserAdm__;
    }

    /**
     * <p>bbs030SelectLeftUserAdm をセットします。
     * @param bbs030SelectLeftUserAdm bbs030SelectLeftUserAdm
     */
    public void setBbs030SelectLeftUserAdm(String[] bbs030SelectLeftUserAdm) {
        bbs030SelectLeftUserAdm__ = bbs030SelectLeftUserAdm;
    }

    /**
     * <p>bbs030mread を取得します。
     * @return bbs030mread
     */
    public String getBbs030mread() {
        return bbs030mread__;
    }

    /**
     * <p>bbs030mread をセットします。
     * @param bbs030mread bbs030mread
     */
    public void setBbs030mread(String bbs030mread) {
        bbs030mread__ = bbs030mread;
    }

    /**
     * <p>bbs030template を取得します。
     * @return bbs030template
     */
    public String getBbs030template() {
        return bbs030template__;
    }

    /**
     * <p>bbs030template をセットします。
     * @param bbs030template bbs030template
     */
    public void setBbs030template(String bbs030template) {
        bbs030template__ = bbs030template;
    }

    /**
     * <p>bbs030templateWriteKbn を取得します。
     * @return bbs030templateWriteKbn
     */
    public int getBbs030templateWriteKbn() {
        return bbs030templateWriteKbn__;
    }

    /**
     * <p>bbs030templateWriteKbn をセットします。
     * @param bbs030templateWriteKbn bbs030templateWriteKbn
     */
    public void setBbs030templateWriteKbn(int bbs030templateWriteKbn) {
        bbs030templateWriteKbn__ = bbs030templateWriteKbn;
    }

    /**
     * <p>bbs030templateKbn を取得します。
     * @return bbs030templateKbn
     */
    public int getBbs030templateKbn() {
        return bbs030templateKbn__;
    }

    /**
     * <p>bbs030templateKbn をセットします。
     * @param bbs030templateKbn bbs030templateKbn
     */
    public void setBbs030templateKbn(int bbs030templateKbn) {
        bbs030templateKbn__ = bbs030templateKbn;
    }

    /**
     * <p>bbs030diskSize__ を取得します。
     * @return bbs030diskSize
     */
   public int getBbs030diskSize() {
        return bbs030diskSize__;
    }

   /**
    * <p>bbs030diskSize をセットします。
    * @param bbs030diskSize bbs030diskSize
    */
    public void setBbs030diskSize(int bbs030diskSize) {
        bbs030diskSize__ = bbs030diskSize;
    }

    /**
     * <p>bbs030diskSizeLimit を取得します。
     * @return bbs030diskSizeLimit
     */
    public String getBbs030diskSizeLimit() {
        return bbs030diskSizeLimit__;
    }

    /**
     * <p>bbs030diskSizeLimit をセットします。
     * @param bbs030diskSizeLimit bbs030diskSizeLimit
     */
    public void setBbs030diskSizeLimit(String bbs030diskSizeLimit) {
        bbs030diskSizeLimit__ = bbs030diskSizeLimit;
    }

    /**
     * <p>bbs030warnDisk を取得します。
     * @return bbs030warnDisk
     */
    public int getBbs030warnDisk() {
        return bbs030warnDisk__;
    }

    /**
     * <p>bbs030warnDisk をセットします。
     * @param bbs030warnDisk bbs030warnDisk
     */
    public void setBbs030warnDisk(int bbs030warnDisk) {
        bbs030warnDisk__ = bbs030warnDisk;
    }

    /**
     * <p>bbs030warnDiskThreshold を取得します。
     * @return bbs030warnDiskThreshold
     */
    public int getBbs030warnDiskThreshold() {
        return bbs030warnDiskThreshold__;
    }

    /**
     * <p>bbs030warnDiskThreshold をセットします。
     * @param bbs030warnDiskThreshold bbs030warnDiskThreshold
     */
    public void setBbs030warnDiskThreshold(int bbs030warnDiskThreshold) {
        bbs030warnDiskThreshold__ = bbs030warnDiskThreshold;
    }

    /**
     * <p>warnDiskThresholdList を取得します。
     * @return warnDiskThresholdList
     */
    public List<LabelValueBean> getWarnDiskThresholdList() {
        return warnDiskThresholdList__;
    }

    /**
     * <p>warnDiskThresholdList をセットします。
     * @param warnDiskThresholdList warnDiskThresholdList
     */
    public void setWarnDiskThresholdList(List<LabelValueBean> warnDiskThresholdList) {
        warnDiskThresholdList__ = warnDiskThresholdList;
    }

    /**
     * <p>bbs030LimitDisable を取得します。
     * @return bbs030LimitDisable
     */
    public int getBbs030LimitDisable() {
        return bbs030LimitDisable__;
    }

    /**
     * <p>bbs030LimitDisable をセットします。
     * @param bbs030LimitDisable bbs030LimitDisable
     */
    public void setBbs030LimitDisable(int bbs030LimitDisable) {
        bbs030LimitDisable__ = bbs030LimitDisable;
    }

    /**
     * <p>bbs030Limit を取得します。
     * @return bbs030Limit
     */
    public int getBbs030Limit() {
        return bbs030Limit__;
    }

    /**
     * <p>bbs030Limit をセットします。
     * @param bbs030Limit bbs030Limit
     */
    public void setBbs030Limit(int bbs030Limit) {
        bbs030Limit__ = bbs030Limit;
    }

    /**
     * <p>bbs030LimitDate を取得します。
     * @return bbs030LimitDate
     */
    public String getBbs030LimitDate() {
        return bbs030LimitDate__;
    }

    /**
     * <p>bbs030LimitDate をセットします。
     * @param bbs030LimitDate bbs030LimitDate
     */
    public void setBbs030LimitDate(String bbs030LimitDate) {
        bbs030LimitDate__ = bbs030LimitDate;
    }

    /**
     * <p>bbs030Keep を取得します。
     * @return bbs030Keep
     */
    public int getBbs030Keep() {
        return bbs030Keep__;
    }

    /**
     * <p>bbs030Keep をセットします。
     * @param bbs030Keep bbs030Keep
     */
    public void setBbs030Keep(int bbs030Keep) {
        bbs030Keep__ = bbs030Keep;
    }

    /**
     * <p>bbs030KeepDateY を取得します。
     * @return bbs030KeepDateY
     */
    public int getBbs030KeepDateY() {
        return bbs030KeepDateY__;
    }

    /**
     * <p>bbs030KeepDateY をセットします。
     * @param bbs030KeepDateY bbs030KeepDateY
     */
    public void setBbs030KeepDateY(int bbs030KeepDateY) {
        bbs030KeepDateY__ = bbs030KeepDateY;
    }

    /**
     * <p>bbs030KeepDateM を取得します。
     * @return bbs030KeepDateM
     */
    public int getBbs030KeepDateM() {
        return bbs030KeepDateM__;
    }

    /**
     * <p>bbs030KeepDateM をセットします。
     * @param bbs030KeepDateM bbs030KeepDateM
     */
    public void setBbs030KeepDateM(int bbs030KeepDateM) {
        bbs030KeepDateM__ = bbs030KeepDateM;
    }

    /**
     * <p>bbs030KeepDateYLabel を取得します。
     * @return bbs030KeepDateYLabel
     */
    public List<LabelValueBean> getBbs030KeepDateYLabel() {
        return bbs030KeepDateYLabel__;
    }

    /**
     * <p>bbs030KeepDateYLabel をセットします。
     * @param bbs030KeepDateYLabel bbs030KeepDateYLabel
     */
    public void setBbs030KeepDateYLabel(List<LabelValueBean> bbs030KeepDateYLabel) {
        bbs030KeepDateYLabel__ = bbs030KeepDateYLabel;
    }

    /**
     * <p>bbs030KeepDateMLabel を取得します。
     * @return bbs030KeepDateMLabel
     */
    public List<LabelValueBean> getBbs030KeepDateMLabel() {
        return bbs030KeepDateMLabel__;
    }

    /**
     * <p>bbs030KeepDateMLabel をセットします。
     * @param bbs030KeepDateMLabel bbs030KeepDateMLabel
     */
    public void setBbs030KeepDateMLabel(List<LabelValueBean> bbs030KeepDateMLabel) {
        bbs030KeepDateMLabel__ = bbs030KeepDateMLabel;
    }

    /**
     * <p>bbs030DspAtdelFlg を取得します。
     * @return bbs030DspAtdelFlg
     */
    public int getBbs030DspAtdelFlg() {
        return bbs030DspAtdelFlg__;
    }

    /**
     * <p>bbs030DspAtdelFlg をセットします。
     * @param bbs030DspAtdelFlg bbs030DspAtdelFlg
     */
    public void setBbs030DspAtdelFlg(int bbs030DspAtdelFlg) {
        bbs030DspAtdelFlg__ = bbs030DspAtdelFlg;
    }

    /**
     * <p>bbs030DspAtdelYear を取得します。
     * @return bbs030DspAtdelYear
     */
    public int getBbs030DspAtdelYear() {
        return bbs030DspAtdelYear__;
    }

    /**
     * <p>bbs030DspAtdelYear をセットします。
     * @param bbs030DspAtdelYear bbs030DspAtdelYear
     */
    public void setBbs030DspAtdelYear(int bbs030DspAtdelYear) {
        bbs030DspAtdelYear__ = bbs030DspAtdelYear;
    }

    /**
     * <p>bbs030DspAtdelMonth を取得します。
     * @return bbs030DspAtdelMonth
     */
    public int getBbs030DspAtdelMonth() {
        return bbs030DspAtdelMonth__;
    }

    /**
     * <p>bbs030DspAtdelMonth をセットします。
     * @param bbs030DspAtdelMonth bbs030DspAtdelMonth
     */
    public void setBbs030DspAtdelMonth(int bbs030DspAtdelMonth) {
        bbs030DspAtdelMonth__ = bbs030DspAtdelMonth;
    }
}
