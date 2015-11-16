package jp.groupsession.v2.fil.fil050;

import java.util.ArrayList;
import java.util.List;

import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.fil040.Fil040ParamModel;
import jp.groupsession.v2.fil.fil050.model.Fil050Model;
import jp.groupsession.v2.fil.model.FileDAccessUserModel;

import org.apache.struts.util.LabelValueBean;


/**
 * <br>[機  能] フォルダ詳細画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Fil050ParamModel extends Fil040ParamModel {

    /** ソートする項目 */
    private String fil050SortKey__ = "0";
    /** 昇順・降順 */
    private String fil050OrderKey__ = String.valueOf(GSConst.ORDER_KEY_DESC);
    /** GS管理者権限 有無 */
    private String fil050AdminFlg__ = String.valueOf(GSConst.USER_NOT_ADMIN);

    /** 表示切替用 更新履歴・アクセス制御*/
    private String fil050DspMode__ = GSConstFile.DSP_MODE_HIST;

    /** ディレクトリSID */
    private String fil050DirSid__ = null;
    /** 親ディレクトリSID */
    private String fil050ParentDirSid__ = null;
    /** フォルダパス */
    private String fil050FolderPath__ = null;
    /** 備考 */
    private String fil050Biko__ = null;
    /** ショートカット有無 */
    private String fil050ShortcutKbn__ = null;
    /** ショートカットパス */
    private String fil050ShortcutPath__ = null;
    /** 更新通知有無 */
    private String fil050CallKbn__ = null;
    /** 更新通知 一括反映区分 */
    private int fil050CallLevelKbn__ = GSConstFile.CALL_LEVEL_OFF;
    /** ファイルコンボ */
    private List<LabelValueBean> fil050FileLabelList__ = null;
    /** 履歴　選択したディレクトリSID */
    private String fil050SltDirSid__ = null;
    /** 履歴　選択したディレクトリバージョン */
    private String fil050SltDirVer__ = null;
    /** ログインユーザ編集権限区分 */
    private String fil050EditAuthKbn__ = null;
    /** 復旧表示区分 */
    private String fil050RepairDspFlg__ = null;

    /** 更新履歴一覧 */
    private List<Fil050Model> fil050RekiList__ = null;
    /** アクセス制御一覧 */
    private List<FileDAccessUserModel> fil050AccessList__ = null;
    /** ページ1 */
    private int fil050PageNum1__ = 1;
    /** ページ2 */
    private int fil050PageNum2__ = 1;
    /** ページラベル */
    ArrayList < LabelValueBean > fil050PageLabel__;

    /** 履歴　選択したディレクトリバージョン */
    private String fil050FolderUrl__ = null;

    //詳細検索画面のパラメータ

    /** 抽出対象　キャビネット */
    private String fil100SltCabinetSid__ = null;
    /** 抽出対象　フォルダ */
    private String fil100ChkTrgFolder__ = "0";
    /** 抽出対象　ファイル */
    private String fil100ChkTrgFile__ = "0";
    /** キーワード AND OR */
    private String fil100SearchMode__ = String.valueOf(GSConstFile.KEY_WORD_KBN_AND);
    /** キーワード検索対象 名前 */
    private String fil100ChkWdTrgName__ = "0";
    /** キーワード検索対象 備考 */
    private String fil100ChkWdTrgBiko__ = "0";
    /** キーワード検索対象 ファイル内容 */
    private String fil100ChkWdTrgText__ = "0";
    /** 更新日　日時指定区分 */
    private String fil100ChkOnOff__ = String.valueOf(GSConstFile.UPDATE_KBN_OK);
    /** 検索実行フラグ */
    private int searchFlg__ = GSConstFile.SEARCH_EXECUTE_TRUE;
    /** ページ1 */
    private int fil100pageNum1__ = 1;
    /** ページ2 */
    private int fil100pageNum2__ = 1;
    /** 検索更新開始日付 年 */
    private int fileSearchfromYear__ = 0;
    /** 検索更新開始日付 月 */
    private int fileSearchfromMonth__ = 0;
    /** 検索更新開始日付 日 */
    private int fileSearchfromDay__ = 0;
    /** 検索更新終了日付 年 */
    private int fileSearchtoYear__ = 0;
    /** 検索更新終了日付 月 */
    private int fileSearchtoMonth__ = 0;
    /** 検索更新終了日付 日 */
    private int fileSearchtoDay__ = 0;
    /** ソートキー */
    private int fil100sortKey__ = GSConstFile.SORT_NAME;
    /** オーダーキー */
    private int fil100orderKey__ = GSConstFile.ORDER_KEY_ASC;

    /** キャビネットSID */
    private String fil100SvSltCabinetSid__ = null;
    /** 抽出対象　フォルダ */
    private String fil100SvChkTrgFolder__ = String.valueOf(GSConstFile.GET_TARGET_FOLDER);
    /** 抽出対象　ファイル */
    private String fil100SvChkTrgFile__ = String.valueOf(GSConstFile.GET_TARGET_FILE);
    /** キーワード AND OR */
    private String fil100SvSearchMode__ = String.valueOf(GSConstFile.KEY_WORD_KBN_AND);
    /** キーワード検索対象 名前 */
    private String fil100SvChkWdTrgName__ = String.valueOf(GSConstFile.KEYWORD_TARGET_NAME);
    /** キーワード検索対象 備考 */
    private String fil100SvChkWdTrgBiko__ = String.valueOf(GSConstFile.KEYWORD_TARGET_BIKO);
    /** キーワード検索対象 ファイル内容 */
    private String fil100SvChkWdTrgText__ = String.valueOf(GSConstFile.KEYWORD_TARGET_TEXT);
    /** キーワード */
    private String fil100SvChkWdKeyWord__ = null;
    /** from更新日　年 */
    private int fileSvSearchfromYear__ = 0;
    /** from更新日　月 */
    private int fileSvSearchfromMonth__ = 0;
    /** from更新日　日 */
    private int fileSvSearchfromDay__ = 0;
    /** to  更新日　年 */
    private int fileSvSearchtoYear__ = 0;
    /** to  更新日　月 */
    private int fileSvSearchtoMonth__ = 0;
    /** to  更新日　日 */
    private int fileSvSearchtoDay__ = 0;
    /** 更新日　指定区分 */
    private String fil100SvChkOnOff__ = String.valueOf(GSConstFile.UPDATE_KBN_NO);

    /**
     * <p>fil050PageLabel を取得します。
     * @return fil050PageLabel
     */
    public ArrayList<LabelValueBean> getFil050PageLabel() {
        return fil050PageLabel__;
    }
    /**
     * <p>fil050PageLabel をセットします。
     * @param fil050PageLabel fil050PageLabel
     */
    public void setFil050PageLabel(ArrayList<LabelValueBean> fil050PageLabel) {
        fil050PageLabel__ = fil050PageLabel;
    }
    /**
     * <p>fil050PageNum1 を取得します。
     * @return fil050PageNum1
     */
    public int getFil050PageNum1() {
        return fil050PageNum1__;
    }
    /**
     * <p>fil050PageNum1 をセットします。
     * @param fil050PageNum1 fil050PageNum1
     */
    public void setFil050PageNum1(int fil050PageNum1) {
        fil050PageNum1__ = fil050PageNum1;
    }
    /**
     * <p>fil050PageNum2 を取得します。
     * @return fil050PageNum2
     */
    public int getFil050PageNum2() {
        return fil050PageNum2__;
    }
    /**
     * <p>fil050PageNum2 をセットします。
     * @param fil050PageNum2 fil050PageNum2
     */
    public void setFil050PageNum2(int fil050PageNum2) {
        fil050PageNum2__ = fil050PageNum2;
    }
    /**
     * <p>fil050Biko を取得します。
     * @return fil050Biko
     */
    public String getFil050Biko() {
        return fil050Biko__;
    }
    /**
     * <p>fil050Biko をセットします。
     * @param fil050Biko fil050Biko
     */
    public void setFil050Biko(String fil050Biko) {
        fil050Biko__ = fil050Biko;
    }
    /**
     * <p>fil050FolderPath を取得します。
     * @return fil050FolderPath
     */
    public String getFil050FolderPath() {
        return fil050FolderPath__;
    }
    /**
     * <p>fil050FolderPath をセットします。
     * @param fil050FolderPath fil050FolderPath
     */
    public void setFil050FolderPath(String fil050FolderPath) {
        fil050FolderPath__ = fil050FolderPath;
    }
    /**
     * <p>fil050AdminFlg を取得します。
     * @return fil050AdminFlg
     */
    public String getFil050AdminFlg() {
        return fil050AdminFlg__;
    }
    /**
     * <p>fil050AdminFlg をセットします。
     * @param fil050AdminFlg fil050AdminFlg
     */
    public void setFil050AdminFlg(String fil050AdminFlg) {
        fil050AdminFlg__ = fil050AdminFlg;
    }
    /**
     * <p>fil050DspMode を取得します。
     * @return fil050DspMode
     */
    public String getFil050DspMode() {
        return fil050DspMode__;
    }
    /**
     * <p>fil050DspMode をセットします。
     * @param fil050DspMode fil050DspMode
     */
    public void setFil050DspMode(String fil050DspMode) {
        this.fil050DspMode__ = fil050DspMode;
    }
    /**
     * <p>fil050OrderKey を取得します。
     * @return fil050OrderKey
     */
    public String getFil050OrderKey() {
        return fil050OrderKey__;
    }
    /**
     * <p>fil050OrderKey をセットします。
     * @param fil050OrderKey fil050OrderKey
     */
    public void setFil050OrderKey(String fil050OrderKey) {
        fil050OrderKey__ = fil050OrderKey;
    }
    /**
     * <p>fil050SortKey を取得します。
     * @return fil050SortKey
     */
    public String getFil050SortKey() {
        return fil050SortKey__;
    }
    /**
     * <p>fil050SortKey をセットします。
     * @param fil050SortKey fil050SortKey
     */
    public void setFil050SortKey(String fil050SortKey) {
        fil050SortKey__ = fil050SortKey;
    }

    /**
     * <p>fil050CallKbn を取得します。
     * @return fil050CallKbn
     */
    public String getFil050CallKbn() {
        return fil050CallKbn__;
    }
    /**
     * <p>fil050CallKbn をセットします。
     * @param fil050CallKbn fil050CallKbn
     */
    public void setFil050CallKbn(String fil050CallKbn) {
        fil050CallKbn__ = fil050CallKbn;
    }
    /**
     * <p>fil050ShortcutPath を取得します。
     * @return fil050ShortcutPath
     */
    public String getFil050ShortcutPath() {
        return fil050ShortcutPath__;
    }
    /**
     * <p>fil050ShortcutPath をセットします。
     * @param fil050ShortcutPath fil050ShortcutPath
     */
    public void setFil050ShortcutPath(String fil050ShortcutPath) {
        fil050ShortcutPath__ = fil050ShortcutPath;
    }
    /**
     * <p>fil050ShortcutKbn を取得します。
     * @return fil050ShortcutKbn
     */
    public String getFil050ShortcutKbn() {
        return fil050ShortcutKbn__;
    }
    /**
     * <p>fil050ShortcutKbn をセットします。
     * @param fil050ShortcutKbn fil050ShortcutKbn
     */
    public void setFil050ShortcutKbn(String fil050ShortcutKbn) {
        fil050ShortcutKbn__ = fil050ShortcutKbn;
    }
    /**
     * <p>fil050RekiList を取得します。
     * @return fil050RekiList
     */
    public List<Fil050Model> getFil050RekiList() {
        return fil050RekiList__;
    }
    /**
     * <p>fil050RekiList をセットします。
     * @param fil050RekiList fil050RekiList
     */
    public void setFil050RekiList(List<Fil050Model> fil050RekiList) {
        fil050RekiList__ = fil050RekiList;
    }
    /**
     * <p>fil050AccessList を取得します。
     * @return fil050AccessList
     */
    public List<FileDAccessUserModel> getFil050AccessList() {
        return fil050AccessList__;
    }
    /**
     * <p>fil050AccessList をセットします。
     * @param fil050AccessList fil050AccessList
     */
    public void setFil050AccessList(List<FileDAccessUserModel> fil050AccessList) {
        this.fil050AccessList__ = fil050AccessList;
    }
    /**
     * <p>fil050SltDirSid を取得します。
     * @return fil050SltDirSid
     */
    public String getFil050SltDirSid() {
        return fil050SltDirSid__;
    }
    /**
     * <p>fil050SltDirSid をセットします。
     * @param fil050SltDirSid fil050SltDirSid
     */
    public void setFil050SltDirSid(String fil050SltDirSid) {
        fil050SltDirSid__ = fil050SltDirSid;
    }
    /**
     * <p>fil050SltDirVer を取得します。
     * @return fil050SltDirVer
     */
    public String getFil050SltDirVer() {
        return fil050SltDirVer__;
    }
    /**
     * <p>fil050SltDirVer をセットします。
     * @param fil050SltDirVer fil050SltDirVer
     */
    public void setFil050SltDirVer(String fil050SltDirVer) {
        fil050SltDirVer__ = fil050SltDirVer;
    }
    /**
     * <p>fil050FileLabelList を取得します。
     * @return fil050FileLabelList
     */
    public List<LabelValueBean> getFil050FileLabelList() {
        return fil050FileLabelList__;
    }
    /**
     * <p>fil050FileLabelList をセットします。
     * @param fil050FileLabelList fil050FileLabelList
     */
    public void setFil050FileLabelList(List<LabelValueBean> fil050FileLabelList) {
        fil050FileLabelList__ = fil050FileLabelList;
    }
    /**
     * <p>fil050DirSid を取得します。
     * @return fil050DirSid
     */
    public String getFil050DirSid() {
        return fil050DirSid__;
    }
    /**
     * <p>fil050DirSid をセットします。
     * @param fil050DirSid fil050DirSid
     */
    public void setFil050DirSid(String fil050DirSid) {
        fil050DirSid__ = fil050DirSid;
    }
    /**
     * <p>fil050FolderUrl を取得します。
     * @return fil050FolderUrl
     */
    public String getFil050FolderUrl() {
        return fil050FolderUrl__;
    }
    /**
     * <p>fil050FolderUrl をセットします。
     * @param fil050FolderUrl fil050FolderUrl
     */
    public void setFil050FolderUrl(String fil050FolderUrl) {
        fil050FolderUrl__ = fil050FolderUrl;
    }
    /**
     * <p>fil100ChkOnOff を取得します。
     * @return fil100ChkOnOff
     */
    public String getFil100ChkOnOff() {
        return fil100ChkOnOff__;
    }
    /**
     * <p>fil100ChkOnOff をセットします。
     * @param fil100ChkOnOff fil100ChkOnOff
     */
    public void setFil100ChkOnOff(String fil100ChkOnOff) {
        fil100ChkOnOff__ = fil100ChkOnOff;
    }
    /**
     * <p>fil100ChkTrgFile を取得します。
     * @return fil100ChkTrgFile
     */
    public String getFil100ChkTrgFile() {
        return fil100ChkTrgFile__;
    }
    /**
     * <p>fil100ChkTrgFile をセットします。
     * @param fil100ChkTrgFile fil100ChkTrgFile
     */
    public void setFil100ChkTrgFile(String fil100ChkTrgFile) {
        fil100ChkTrgFile__ = fil100ChkTrgFile;
    }
    /**
     * <p>fil100ChkTrgFolder を取得します。
     * @return fil100ChkTrgFolder
     */
    public String getFil100ChkTrgFolder() {
        return fil100ChkTrgFolder__;
    }
    /**
     * <p>fil100ChkTrgFolder をセットします。
     * @param fil100ChkTrgFolder fil100ChkTrgFolder
     */
    public void setFil100ChkTrgFolder(String fil100ChkTrgFolder) {
        fil100ChkTrgFolder__ = fil100ChkTrgFolder;
    }
    /**
     * <p>fil100ChkWdTrgBiko を取得します。
     * @return fil100ChkWdTrgBiko
     */
    public String getFil100ChkWdTrgBiko() {
        return fil100ChkWdTrgBiko__;
    }
    /**
     * <p>fil100ChkWdTrgBiko をセットします。
     * @param fil100ChkWdTrgBiko fil100ChkWdTrgBiko
     */
    public void setFil100ChkWdTrgBiko(String fil100ChkWdTrgBiko) {
        fil100ChkWdTrgBiko__ = fil100ChkWdTrgBiko;
    }
    /**
     * <p>fil100ChkWdTrgName を取得します。
     * @return fil100ChkWdTrgName
     */
    public String getFil100ChkWdTrgName() {
        return fil100ChkWdTrgName__;
    }
    /**
     * <p>fil100ChkWdTrgName をセットします。
     * @param fil100ChkWdTrgName fil100ChkWdTrgName
     */
    public void setFil100ChkWdTrgName(String fil100ChkWdTrgName) {
        fil100ChkWdTrgName__ = fil100ChkWdTrgName;
    }
    /**
     * <p>fil100orderKey を取得します。
     * @return fil100orderKey
     */
    public int getFil100orderKey() {
        return fil100orderKey__;
    }
    /**
     * <p>fil100orderKey をセットします。
     * @param fil100orderKey fil100orderKey
     */
    public void setFil100orderKey(int fil100orderKey) {
        fil100orderKey__ = fil100orderKey;
    }
    /**
     * <p>fil100pageNum1 を取得します。
     * @return fil100pageNum1
     */
    public int getFil100pageNum1() {
        return fil100pageNum1__;
    }
    /**
     * <p>fil100pageNum1 をセットします。
     * @param fil100pageNum1 fil100pageNum1
     */
    public void setFil100pageNum1(int fil100pageNum1) {
        fil100pageNum1__ = fil100pageNum1;
    }
    /**
     * <p>fil100pageNum2 を取得します。
     * @return fil100pageNum2
     */
    public int getFil100pageNum2() {
        return fil100pageNum2__;
    }
    /**
     * <p>fil100pageNum2 をセットします。
     * @param fil100pageNum2 fil100pageNum2
     */
    public void setFil100pageNum2(int fil100pageNum2) {
        fil100pageNum2__ = fil100pageNum2;
    }
    /**
     * <p>fil100SearchMode を取得します。
     * @return fil100SearchMode
     */
    public String getFil100SearchMode() {
        return fil100SearchMode__;
    }
    /**
     * <p>fil100SearchMode をセットします。
     * @param fil100SearchMode fil100SearchMode
     */
    public void setFil100SearchMode(String fil100SearchMode) {
        fil100SearchMode__ = fil100SearchMode;
    }
    /**
     * <p>fil100sortKey を取得します。
     * @return fil100sortKey
     */
    public int getFil100sortKey() {
        return fil100sortKey__;
    }
    /**
     * <p>fil100sortKey をセットします。
     * @param fil100sortKey fil100sortKey
     */
    public void setFil100sortKey(int fil100sortKey) {
        fil100sortKey__ = fil100sortKey;
    }
    /**
     * <p>fil100SvChkOnOff を取得します。
     * @return fil100SvChkOnOff
     */
    public String getFil100SvChkOnOff() {
        return fil100SvChkOnOff__;
    }
    /**
     * <p>fil100SvChkOnOff をセットします。
     * @param fil100SvChkOnOff fil100SvChkOnOff
     */
    public void setFil100SvChkOnOff(String fil100SvChkOnOff) {
        fil100SvChkOnOff__ = fil100SvChkOnOff;
    }
    /**
     * <p>fil100SvChkTrgFile を取得します。
     * @return fil100SvChkTrgFile
     */
    public String getFil100SvChkTrgFile() {
        return fil100SvChkTrgFile__;
    }
    /**
     * <p>fil100SvChkTrgFile をセットします。
     * @param fil100SvChkTrgFile fil100SvChkTrgFile
     */
    public void setFil100SvChkTrgFile(String fil100SvChkTrgFile) {
        fil100SvChkTrgFile__ = fil100SvChkTrgFile;
    }
    /**
     * <p>fil100SvChkTrgFolder を取得します。
     * @return fil100SvChkTrgFolder
     */
    public String getFil100SvChkTrgFolder() {
        return fil100SvChkTrgFolder__;
    }
    /**
     * <p>fil100SvChkTrgFolder をセットします。
     * @param fil100SvChkTrgFolder fil100SvChkTrgFolder
     */
    public void setFil100SvChkTrgFolder(String fil100SvChkTrgFolder) {
        fil100SvChkTrgFolder__ = fil100SvChkTrgFolder;
    }
    /**
     * <p>fil100SvChkWdKeyWord を取得します。
     * @return fil100SvChkWdKeyWord
     */
    public String getFil100SvChkWdKeyWord() {
        return fil100SvChkWdKeyWord__;
    }
    /**
     * <p>fil100SvChkWdKeyWord をセットします。
     * @param fil100SvChkWdKeyWord fil100SvChkWdKeyWord
     */
    public void setFil100SvChkWdKeyWord(String fil100SvChkWdKeyWord) {
        fil100SvChkWdKeyWord__ = fil100SvChkWdKeyWord;
    }
    /**
     * <p>fil100SvChkWdTrgBiko を取得します。
     * @return fil100SvChkWdTrgBiko
     */
    public String getFil100SvChkWdTrgBiko() {
        return fil100SvChkWdTrgBiko__;
    }
    /**
     * <p>fil100SvChkWdTrgBiko をセットします。
     * @param fil100SvChkWdTrgBiko fil100SvChkWdTrgBiko
     */
    public void setFil100SvChkWdTrgBiko(String fil100SvChkWdTrgBiko) {
        fil100SvChkWdTrgBiko__ = fil100SvChkWdTrgBiko;
    }
    /**
     * <p>fil100SvChkWdTrgName を取得します。
     * @return fil100SvChkWdTrgName
     */
    public String getFil100SvChkWdTrgName() {
        return fil100SvChkWdTrgName__;
    }
    /**
     * <p>fil100SvChkWdTrgName をセットします。
     * @param fil100SvChkWdTrgName fil100SvChkWdTrgName
     */
    public void setFil100SvChkWdTrgName(String fil100SvChkWdTrgName) {
        fil100SvChkWdTrgName__ = fil100SvChkWdTrgName;
    }
    /**
     * <p>fil100SvSearchMode を取得します。
     * @return fil100SvSearchMode
     */
    public String getFil100SvSearchMode() {
        return fil100SvSearchMode__;
    }
    /**
     * <p>fil100SvSearchMode をセットします。
     * @param fil100SvSearchMode fil100SvSearchMode
     */
    public void setFil100SvSearchMode(String fil100SvSearchMode) {
        fil100SvSearchMode__ = fil100SvSearchMode;
    }
    /**
     * <p>fil100SvSltCabinetSid を取得します。
     * @return fil100SvSltCabinetSid
     */
    public String getFil100SvSltCabinetSid() {
        return fil100SvSltCabinetSid__;
    }
    /**
     * <p>fil100SvSltCabinetSid をセットします。
     * @param fil100SvSltCabinetSid fil100SvSltCabinetSid
     */
    public void setFil100SvSltCabinetSid(String fil100SvSltCabinetSid) {
        fil100SvSltCabinetSid__ = fil100SvSltCabinetSid;
    }
    /**
     * <p>fileSearchfromDay を取得します。
     * @return fileSearchfromDay
     */
    public int getFileSearchfromDay() {
        return fileSearchfromDay__;
    }
    /**
     * <p>fileSearchfromDay をセットします。
     * @param fileSearchfromDay fileSearchfromDay
     */
    public void setFileSearchfromDay(int fileSearchfromDay) {
        fileSearchfromDay__ = fileSearchfromDay;
    }
    /**
     * <p>fileSearchfromMonth を取得します。
     * @return fileSearchfromMonth
     */
    public int getFileSearchfromMonth() {
        return fileSearchfromMonth__;
    }
    /**
     * <p>fileSearchfromMonth をセットします。
     * @param fileSearchfromMonth fileSearchfromMonth
     */
    public void setFileSearchfromMonth(int fileSearchfromMonth) {
        fileSearchfromMonth__ = fileSearchfromMonth;
    }
    /**
     * <p>fileSearchfromYear を取得します。
     * @return fileSearchfromYear
     */
    public int getFileSearchfromYear() {
        return fileSearchfromYear__;
    }
    /**
     * <p>fileSearchfromYear をセットします。
     * @param fileSearchfromYear fileSearchfromYear
     */
    public void setFileSearchfromYear(int fileSearchfromYear) {
        fileSearchfromYear__ = fileSearchfromYear;
    }
    /**
     * <p>fileSearchtoDay を取得します。
     * @return fileSearchtoDay
     */
    public int getFileSearchtoDay() {
        return fileSearchtoDay__;
    }
    /**
     * <p>fileSearchtoDay をセットします。
     * @param fileSearchtoDay fileSearchtoDay
     */
    public void setFileSearchtoDay(int fileSearchtoDay) {
        fileSearchtoDay__ = fileSearchtoDay;
    }
    /**
     * <p>fileSearchtoMonth を取得します。
     * @return fileSearchtoMonth
     */
    public int getFileSearchtoMonth() {
        return fileSearchtoMonth__;
    }
    /**
     * <p>fileSearchtoMonth をセットします。
     * @param fileSearchtoMonth fileSearchtoMonth
     */
    public void setFileSearchtoMonth(int fileSearchtoMonth) {
        fileSearchtoMonth__ = fileSearchtoMonth;
    }
    /**
     * <p>fileSearchtoYear を取得します。
     * @return fileSearchtoYear
     */
    public int getFileSearchtoYear() {
        return fileSearchtoYear__;
    }
    /**
     * <p>fileSearchtoYear をセットします。
     * @param fileSearchtoYear fileSearchtoYear
     */
    public void setFileSearchtoYear(int fileSearchtoYear) {
        fileSearchtoYear__ = fileSearchtoYear;
    }
    /**
     * <p>fileSvSearchfromDay を取得します。
     * @return fileSvSearchfromDay
     */
    public int getFileSvSearchfromDay() {
        return fileSvSearchfromDay__;
    }
    /**
     * <p>fileSvSearchfromDay をセットします。
     * @param fileSvSearchfromDay fileSvSearchfromDay
     */
    public void setFileSvSearchfromDay(int fileSvSearchfromDay) {
        fileSvSearchfromDay__ = fileSvSearchfromDay;
    }
    /**
     * <p>fileSvSearchfromMonth を取得します。
     * @return fileSvSearchfromMonth
     */
    public int getFileSvSearchfromMonth() {
        return fileSvSearchfromMonth__;
    }
    /**
     * <p>fileSvSearchfromMonth をセットします。
     * @param fileSvSearchfromMonth fileSvSearchfromMonth
     */
    public void setFileSvSearchfromMonth(int fileSvSearchfromMonth) {
        fileSvSearchfromMonth__ = fileSvSearchfromMonth;
    }
    /**
     * <p>fileSvSearchfromYear を取得します。
     * @return fileSvSearchfromYear
     */
    public int getFileSvSearchfromYear() {
        return fileSvSearchfromYear__;
    }
    /**
     * <p>fileSvSearchfromYear をセットします。
     * @param fileSvSearchfromYear fileSvSearchfromYear
     */
    public void setFileSvSearchfromYear(int fileSvSearchfromYear) {
        fileSvSearchfromYear__ = fileSvSearchfromYear;
    }
    /**
     * <p>fileSvSearchtoDay を取得します。
     * @return fileSvSearchtoDay
     */
    public int getFileSvSearchtoDay() {
        return fileSvSearchtoDay__;
    }
    /**
     * <p>fileSvSearchtoDay をセットします。
     * @param fileSvSearchtoDay fileSvSearchtoDay
     */
    public void setFileSvSearchtoDay(int fileSvSearchtoDay) {
        fileSvSearchtoDay__ = fileSvSearchtoDay;
    }
    /**
     * <p>fileSvSearchtoMonth を取得します。
     * @return fileSvSearchtoMonth
     */
    public int getFileSvSearchtoMonth() {
        return fileSvSearchtoMonth__;
    }
    /**
     * <p>fileSvSearchtoMonth をセットします。
     * @param fileSvSearchtoMonth fileSvSearchtoMonth
     */
    public void setFileSvSearchtoMonth(int fileSvSearchtoMonth) {
        fileSvSearchtoMonth__ = fileSvSearchtoMonth;
    }
    /**
     * <p>fileSvSearchtoYear を取得します。
     * @return fileSvSearchtoYear
     */
    public int getFileSvSearchtoYear() {
        return fileSvSearchtoYear__;
    }
    /**
     * <p>fileSvSearchtoYear をセットします。
     * @param fileSvSearchtoYear fileSvSearchtoYear
     */
    public void setFileSvSearchtoYear(int fileSvSearchtoYear) {
        fileSvSearchtoYear__ = fileSvSearchtoYear;
    }
    /**
     * <p>searchFlg を取得します。
     * @return searchFlg
     */
    public int getSearchFlg() {
        return searchFlg__;
    }
    /**
     * <p>searchFlg をセットします。
     * @param searchFlg searchFlg
     */
    public void setSearchFlg(int searchFlg) {
        searchFlg__ = searchFlg;
    }
    /**
     * <p>fil050ParentDirSid を取得します。
     * @return fil050ParentDirSid
     */
    public String getFil050ParentDirSid() {
        return fil050ParentDirSid__;
    }
    /**
     * <p>fil050ParentDirSid をセットします。
     * @param fil050ParentDirSid fil050ParentDirSid
     */
    public void setFil050ParentDirSid(String fil050ParentDirSid) {
        fil050ParentDirSid__ = fil050ParentDirSid;
    }
    /**
     * <p>fil050EditAuthKbn を取得します。
     * @return fil050EditAuthKbn
     */
    public String getFil050EditAuthKbn() {
        return fil050EditAuthKbn__;
    }
    /**
     * <p>fil050EditAuthKbn をセットします。
     * @param fil050EditAuthKbn fil050EditAuthKbn
     */
    public void setFil050EditAuthKbn(String fil050EditAuthKbn) {
        fil050EditAuthKbn__ = fil050EditAuthKbn;
    }
    /**
     * <p>fil050RepairDspFlg を取得します。
     * @return fil050RepairDspFlg
     */
    public String getFil050RepairDspFlg() {
        return fil050RepairDspFlg__;
    }
    /**
     * <p>fil050RepairDspFlg をセットします。
     * @param fil050RepairDspFlg fil050RepairDspFlg
     */
    public void setFil050RepairDspFlg(String fil050RepairDspFlg) {
        this.fil050RepairDspFlg__ = fil050RepairDspFlg;
    }
    /**
     * <p>fil100SltCabinetSid を取得します。
     * @return fil100SltCabinetSid
     */
    public String getFil100SltCabinetSid() {
        return fil100SltCabinetSid__;
    }
    /**
     * <p>fil100SltCabinetSid をセットします。
     * @param fil100SltCabinetSid fil100SltCabinetSid
     */
    public void setFil100SltCabinetSid(String fil100SltCabinetSid) {
        fil100SltCabinetSid__ = fil100SltCabinetSid;
    }
    /**
     * <p>fil050CallLevelKbn を取得します。
     * @return fil050CallLevelKbn
     */
    public int getFil050CallLevelKbn() {
        return fil050CallLevelKbn__;
    }
    /**
     * <p>fil050CallLevelKbn をセットします。
     * @param fil050CallLevelKbn fil050CallLevelKbn
     */
    public void setFil050CallLevelKbn(int fil050CallLevelKbn) {
        fil050CallLevelKbn__ = fil050CallLevelKbn;
    }
    /**
     * @return fil100ChkWdTrgText
     */
    public String getFil100ChkWdTrgText() {
        return fil100ChkWdTrgText__;
    }
    /**
     * @param fil100ChkWdTrgText セットする fil100ChkWdTrgText
     */
    public void setFil100ChkWdTrgText(String fil100ChkWdTrgText) {
        fil100ChkWdTrgText__ = fil100ChkWdTrgText;
    }
    /**
     * @return fil100SvChkWdTrgText
     */
    public String getFil100SvChkWdTrgText() {
        return fil100SvChkWdTrgText__;
    }
    /**
     * @param fil100SvChkWdTrgText セットする fil100SvChkWdTrgText
     */
    public void setFil100SvChkWdTrgText(String fil100SvChkWdTrgText) {
        fil100SvChkWdTrgText__ = fil100SvChkWdTrgText;
    }
}