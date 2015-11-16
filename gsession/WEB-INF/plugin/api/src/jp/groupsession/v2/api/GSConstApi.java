package jp.groupsession.v2.api;

/**
 * <p>GroupSession WEB API定数一覧
 * @author JTS
 */
public class GSConstApi {
    /** ユーザSID 項目名 */
    public static final String USER_SID_STRING = "cmn.user.sid";
    /** グループSID 項目名 */
    public static final String GROUP_SID_STRING = "cmn.group.sid";
    /** キャビネットSID 項目名 */
    public static final String TEXT_CABINET_SID = "cmn.cabinet.sid";
    /** バイナリSID 項目名 */
    public static final String TEXT_BIN_SID = "cmn.binary.sid";
    /** 添付ファイル 項目名 */
    public static final String TEXT_TEMP_FILE = "cmn.attach.file";
    /** ショートメールSID 項目名 */
    public static final String TEXT_SML_SID = "cmn.smail.sid";
    /** ディレクトリSID 項目名 */
    public static final String TEXT_DIRECTORY_SID = "fil.111";
    /** 親ディレクトリSID 項目名 */
    public static final String TEXT_PARENT_DIRECTORY_SID = "cmn.parantdirectory.sid";
    /** 選択したファイル 項目名 */
    public static final String TEXT_SELECT_FILE = "fil.92";
    /** 選択したフォルダ 項目名 */
    public static final String TEXT_SELECT_FOLDER = "cmn.selected.folder";
    /** モード 項目名 */
    public static final String TEXT_MODE = "cmn.mode";

    /** ショートメールリスト最大表示件数 */
    public static final int SMAIL_MAX_RECORD_COUNT = 20;

    /** ディレクトリ区分 0=フォルダ */
    public static final int DIRECTORY_FOLDER = 0;
    /** ディレクトリ区分 1=ファイル */
    public static final int DIRECTORY_FILE = 1;
}
