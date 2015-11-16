package jp.groupsession.v2.wml.wml010;

/**
 * <br>[機  能] WEBメール メール一覧画面で使用する定数一覧
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml010Const {

    /** 初期表示判定 表示可能 */
    public static final int MAILLIST_VIEW_OK = 1;
    /** 初期表示判定 表示不可(アカウント作成画面へ) */
    public static final int MAILLIST_VIEW_MAKEACCOUT = 2;
    /** 初期表示判定 表示不可(メイン画面へ) */
    public static final int MAILLIST_VIEW_MOVEMAIN = 3;

    /** ソートキー 添付ファイル */
    public static final int SORTKEY_TEMPFILE = 1;
    /** ソートキー タイトル */
    public static final int SORTKEY_TITLE = 2;
    /** ソートキー 送信先 */
    public static final int SORTKEY_MAILADDRESS = 3;
    /** ソートキー 送信日 */
    public static final int SORTKEY_SDATE = 4;
    /** ソートキー 未読/既読 */
    public static final int SORTKEY_READED = 5;
    /** ソートキー サイズ */
    public static final int SORTKEY_SIZE = 6;

    /** 検索条件 日付 指定しない */
    public static final String SEARCH_DATE_NOSET = "0";
    /** 検索条件 日付 指定する */
    public static final String SEARCH_DATE_SET = "1";

    /** ラベル追加種別 既存のラベルを追加 */
    public static final int ADDLABEL_NORMAL = 0;
    /** ラベル追加種別 新規登録したラベルを追加 */
    public static final int ADDLABEL_NEW = 1;

    /** メール一覧 種別 通常 */
    public static final int MAILLIST_TYPE_NORMAL = 0;
    /** メール一覧 種別 検索 */
    public static final int MAILLIST_TYPE_SEARCH = 1;

    /** 検索種別 キーワード検索 */
    public static final int SEARCHTYPE_KEYWORD = 0;
    /** 検索種別 詳細検索 */
    public static final int SEARCHTYPE_DETAIL = 1;

    /** 検索条件 未読/既読 指定しない */
    public static final int SEARCH_READKBN_NOSET = 0;
    /** 検索条件 未読/既読 未読 */
    public static final int SEARCH_READKBN_NOREAD = 1;
    /** 検索条件 未読/既読 未読 */
    public static final int SEARCH_READKBN_READED = 2;

    /** 送信メール 種別 新規作成 */
    public static final int SEND_TYPE_NORMAL = 0;
    /** 送信メール 種別 返信 */
    public static final int SEND_TYPE_REPLY = 1;
    /** 送信メール 種別 全員に返信 */
    public static final int SEND_TYPE_REPLY_ALL = 2;
    /** 送信メール 種別 転送 */
    public static final int SEND_TYPE_FORWARD = 3;
    /** 送信メール 種別 編集 */
    public static final int SEND_TYPE_EDIT = 4;

    /** 送信メール HTMLメール テキストメール */
    public static final int SEND_HTMLMAIL_TEXT = 0;
    /** 送信メール HTMLメール HTMLメール */
    public static final int SEND_HTMLMAIL_HTML = 1;

    /** メール未読/既読 既読にする */
    public static final int MAIL_READTYPE_READED = 0;
    /** メール未読/既読 未読にする */
    public static final int MAIL_READTYPE_NOREAD = 1;

    /** 後で送信する 通常 */
    public static final int TIMESENT_NORMAL = 0;
    /** 後で送信する 後で送信 */
    public static final int TIMESENT_AFTER = 1;

    /** 予約送信 即時送信 */
    public static final int SENDPLAN_IMM = 1;
    /** 添付ファイル 自動圧縮を行わない */
    public static final int SEND_TEMPFILE_COMPRESS_NO = 1;
}
