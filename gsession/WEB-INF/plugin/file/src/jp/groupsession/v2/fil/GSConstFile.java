package jp.groupsession.v2.fil;

import java.math.BigDecimal;

/**
 * <p>ファイル管理定数一覧
 * @author JTS
 */
public class GSConstFile {

    /** プラグインID */
    public static final String PLUGIN_ID_FILE = "file";

    /** 採番区分 キャビネット */
    public static final String SBNSID_SUB_CABINET = "cabinet";
    /** 採番区分 ディレクトリ */
    public static final String SBNSID_SUB_DIRECTORY = "directory";

    /** ファイル管理 全文検索使用設定 */
    public static final String FIL_ALL_SEARCH_USE = "FIL_ALL_SEARCH_USE";
    /** ファイル管理 全文検索使用設定 使用しない(デフォルト値) */
    public static final int FIL_ALL_SEARCH_USE_NO = 0;
    /** ファイル管理 全文検索使用設定 使用する */
    public static final int FIL_ALL_SEARCH_USE_YES = 1;

    /** メイン画面　ショートカット　表示しない */
    public static final int MAIN_OKINI_DSP_OFF = 0;
    /** メイン画面　ショートカット 表示する　*/
    public static final int MAIN_OKINI_DSP_ON = 1;

    /** メイン画面　更新通知　表示件数デフォルト */
    public static final int MAIN_CALL_DSP_CNT = 10;

    /** 履歴表示件数　初期値 */
    public static final int RIREKI_COUNT_DEFAULT = 10;
    /** 更新通知　表示件数デフォルト */
    public static final int CALL_DSP_CNT = 10;

    /** 管理者設定 ショートメール通知設定区分 各ユーザが設定 */
    public static final int FAC_SMAIL_SEND_KBN_USER = 0;
    /** 管理者設定 ショートメール通知設定区分  管理者が設定 */
    public static final int FAC_SMAIL_SEND_KBN_ADMIN = 1;
    /** 管理者設定 ショートメール通知設定 通知する */
    public static final int FAC_SMAIL_SEND_YES = 0;
    /** 管理者設定 ショートメール通知設定 通知しない */
    public static final int FAC_SMAIL_SEND_NO = 1;

    /** ショートメール通知　通知しない */
    public static final int SMAIL_SEND_OFF = 0;
    /** ショートメール通知　通知する */
    public static final int SMAIL_SEND_ON = 1;

    /** アクセス制御のグループコンボに設定するテキスト グループ一覧のVALUE */
    public static final String GROUP_COMBO_VALUE = "-9";

    /** キャビネット作成権限　管理者のみ */
    public static final int CREATE_CABINET_PERMIT_ADMIN = 0;
    /** キャビネット作成権限　グループ指定 */
    public static final int CREATE_CABINET_PERMIT_GROUP = 1;
    /** キャビネット作成権限　ユーザ指定 */
    public static final int CREATE_CABINET_PERMIT_USER = 2;
    /** キャビネット作成権限　制限しない */
    public static final int CREATE_CABINET_PERMIT_NO = 3;

    /** ファイルサイズ制限　初期値 */
    public static final int FILE_SIZE_DEFAULT = 10;

    /** ファイル保存期間　保存しない */
    public static final int FILE_SAVE_DAYS_NO = 0;

    /** ロック機能有無　無効 */
    public static final int LOCK_KBN_OFF = 0;
    /** ロック機能有無　有効 */
    public static final int LOCK_KBN_ON = 1;

    /** バージョン管理有無　無効 */
    public static final int VERSION_KBN_OFF = 0;
    /** バージョン管理有無　有効 */
    public static final int VERSION_KBN_ON = 1;

    /** キャビネット作成権限　ユーザ区分　ユーザ */
    public static final int USER_KBN_USER = 0;
    /** キャビネット作成権限 ユーザ区分　グループ */
    public static final int USER_KBN_GROUP = 1;

    /** アクセス権限　制限しない */
    public static final int ACCESS_KBN_OFF = 0;
    /** アクセス権限　制限する */
    public static final int ACCESS_KBN_ON = 1;

    /** 容量設定区分　制限しない */
    public static final int CAPA_KBN_OFF = 0;
    /** 容量設定区分　制限する */
    public static final int CAPA_KBN_ON = 1;

    /** 容量設定 無制限 */
    public static final int CAPA_SIZE_INFINITY = 0;

    /** キャビネットマーク区分デフォルト */
    public static final int CABINET_MARK_DEFAULT = 0;

    /** バージョン管理一括設定区分　一括設定しない */
    public static final int VERSION_ALL_KBN_OFF = 0;
    /** バージョン管理一括設定区分　一括設定する */
    public static final int VERSION_ALL_KBN_ON = 1;

    /** バージョン 最新バージョン */
    public static final int VERSION_NEW = -1;

    /** ディレクトリ区分 0=フォルダ */
    public static final int DIRECTORY_FOLDER = 0;
    /** ディレクトリ区分 1=ファイル */
    public static final int DIRECTORY_FILE = 1;

    /** ディレクトリ階層 0=ルート */
    public static final int DIRECTORY_LEVEL_0 = 0;
    /** ディレクトリ階層 1=1階層目 */
    public static final int DIRECTORY_LEVEL_1 = 1;
    /** ディレクトリ階層 2=2階層目 */
    public static final int DIRECTORY_LEVEL_2 = 2;
    /** ディレクトリ階層 3=3階層目 */
    public static final int DIRECTORY_LEVEL_3 = 3;
    /** ディレクトリ階層 4=4階層目 */
    public static final int DIRECTORY_LEVEL_4 = 4;
    /** ディレクトリ階層 5=5階層目 */
    public static final int DIRECTORY_LEVEL_5 = 5;
    /** ディレクトリ階層 6=6階層目 */
    public static final int DIRECTORY_LEVEL_6 = 6;
    /** ディレクトリ階層 7=7階層目 */
    public static final int DIRECTORY_LEVEL_7 = 7;
    /** ディレクトリ階層 8=8階層目 */
    public static final int DIRECTORY_LEVEL_8 = 8;
    /** ディレクトリ階層 9=9階層目 */
    public static final int DIRECTORY_LEVEL_9 = 9;
    /** ディレクトリ階層 10=10階層目 */
    public static final int DIRECTORY_LEVEL_10 = 10;

    /** ディレクトリ未選択時 */
    public static final String DIRECTORY_NOT_SELECT = "-1";
    /** ルートディレクトリSID */
    public static final int DIRECTORY_ROOT = 0;
    /** ルートディレクトリ名称 */
    public static final String DIRECTORY_ROOT_NAME = "ROOT";

    /** 状態区分　通常 */
    public static final int JTKBN_NORMAL = 0;
    /** 状態区分 削除 */
    public static final int JTKBN_DELETE = 9;

    /** ファイル一括削除オプション ファイルのみを削除する */
    public static final int DELETE_OPTION_FILE = 0;
    /** ファイル一括削除オプション 下位のフォルダとファイルを削除する */
    public static final int DELETE_OPTION_FOLDER_FILE = 1;

    /** ショートカット 未登録 */
    public static final int SHORTCUT_OFF = 0;
    /** ショートカット 登録済 */
    public static final int SHORTCUT_ON = 1;

    /** 更新通知 無効 */
    public static final int CALL_OFF = 0;
    /** 更新通知 有効 */
    public static final int CALL_ON = 1;

    /** 更新通知 子階層反映区分 無効 */
    public static final int CALL_LEVEL_OFF = 0;
    /** 更新通知 子階層反映区分 有効 */
    public static final int CALL_LEVEL_ON = 1;
    /** 更新通知 子階層反映区分 有効_設定フォルダ */
    public static final int CALL_LEVEL_ON_SET = 2;

    /** 履歴区分 新規 */
    public static final int REKI_KBN_NEW = 0;
    /** 履歴区分 更新 */
    public static final int REKI_KBN_UPDATE = 1;
    /** 履歴区分 復旧 */
    public static final int REKI_KBN_REPAIR = 2;
    /** 履歴区分 削除 */
    public static final int REKI_KBN_DELETE = 3;

    /** KBからMBへ変換する数値*/
    public static final BigDecimal KB_TO_MB = new BigDecimal(1024);
    /** BからMBへ変換する数値*/
    public static final BigDecimal B_TO_MB = new BigDecimal(1024 * 1024);
    /** アクセス権限区分 閲覧 */
    public static final String ACCESS_KBN_READ = "0";
    /** アクセス権限区分 追加・編集・削除 */
    public static final String ACCESS_KBN_WRITE = "1";

    /** 表示区分 非表示 */
    public static final String DSP_KBN_OFF = "0";
    /** 表示区分 表示 */
    public static final String DSP_KBN_ON = "1";

    /** 処理モード　新規 */
    public static final String CMN_MODE_ADD = "0";
    /** 処理モード　編集 */
    public static final String CMN_MODE_EDT = "1";
    /** 処理モード　一括編集 */
    public static final String CMN_MODE_MLT = "2";

    /** ディレクトリ名称　最大文字数 */
    public static final int MAX_LENGTH_NAME = 70;
    /** 備考　最大文字数 */
    public static final int MAX_LENGTH_BIKO = 1000;
    /** 容量設定　最大桁数 */
    public static final int MAX_LENGTH_CAPA = 8;

    /** 容量設定　最大階層数 */
    public static final int MAX_LEVEL = 10;

    /** キャビネット一覧ID */
    public static final String MOVE_TO_FIL010 = "fil010";
    /** キャビネット詳細ID */
    public static final String MOVE_TO_FIL020 = "fil020";
    /** キャビネット登録ID */
    public static final String MOVE_TO_FIL030 = "fil030";
    /** フォルダ一覧ID */
    public static final String MOVE_TO_FIL040 = "fil040";
    /** フォルダ詳細ID */
    public static final String MOVE_TO_FIL050 = "fil050";
    /** フォルダ登録ID */
    public static final String MOVE_TO_FIL060 = "fil060";
    /** ファイル詳細ID */
    public static final String MOVE_TO_FIL070 = "fil070";
    /** ファイル登録ID */
    public static final String MOVE_TO_FIL080 = "fil080";
    /** フォルダ・ファイル移動ID */
    public static final String MOVE_TO_FIL090 = "fil090";
    /** 詳細検索ID */
    public static final String MOVE_TO_FIL100 = "fil100";
    /** キャビネット管理設定ID */
    public static final String MOVE_TO_FIL220 = "fil220";
    /** 更新通知一覧ID */
    public static final String MOVE_TO_FIL240 = "fil240";
    /** GSメイン画面 */
    public static final String MOVE_TO_MAIN = "main";

    /** KB単位*/
    public static final String TEXT_KB = "KB";
    /** MB単位*/
    public static final String TEXT_MB = "MB";

    /** MAX文字数　フォルダ名 */
    public static final int MAX_LENGTH_FOLDER_NAME = 70;
    /** MAX文字数　フォルダ備考 */
    public static final int MAX_LENGTH_FOLDER_BIKO = 1000;
    /** MAX文字数　ファイル備考 */
    public static final int MAX_LENGTH_FILE_BIKO = 1000;
    /** MAX文字数　ファイル備考 */
    public static final int MAX_LENGTH_FILE_UP_CMT = 1000;
    /** MAX文字数　キーワード */
    public static final int MAX_LENGTH_KEYWORD = 50;

    /** フォルダ登録・ファイル登録モード　新規 */
    public static final int MODE_ADD = 0;
    /** フォルダ登録・ファイル登録モード　編集 */
    public static final int MODE_EDIT = 1;

    /** フォルダ　バージョン */
    public static final int FOLDER_VERSION = 0;

    /** キャビネットアクセス権限　編集不可 */
    public static final int EDIT_AUTH_NG = 0;
    /** キャビネットアクセス権限　編集可能 */
    public static final int EDIT_AUTH_OK = 1;

    /** 詳細表示モード　更新履歴 */
    public static final String DSP_MODE_HIST = "0";
    /** 詳細表示モード　アクセスユーザ一覧 */
    public static final String DSP_MODE_USER = "1";

    /** ファイルロック解除権限 無し */
    public static final String UNLOCK_AUTH_OFF = "0";
    /** ファイルロック解除権限 有り */
    public static final String UNLOCK_AUTH_ON = "1";

    /** ソート　キャビネット管理者 */
    public static final int USER_SORT_ADMIN = 4;
    /** ソート アクセス区分 */
    public static final int USER_SORT_ACCESS = 5;

    /** キャビネット管理者　権限有り */
    public static final int CABINET_ADM_ON = 0;
    /** キャビネット管理者　権限無し */
    public static final int CABINET_ADM_OFF = 1;

    /** 検索未実行 */
    public static final int SEARCH_EXECUTE_FALSE = 0;
    /** 検索実行 */
    public static final int SEARCH_EXECUTE_TRUE = 1;

    /** 更新日時　指定有り */
    public static final int UPDATE_KBN_OK = 0;
    /** 更新日時　指定無し */
    public static final int UPDATE_KBN_NO = 1;

    /** オーダーキー (昇順) */
    public static final int ORDER_KEY_ASC = 0;
    /** オーダーキー (降順) */
    public static final int ORDER_KEY_DESC = 1;

    /** キーワード検索区分 (and) */
    public static final int KEY_WORD_KBN_AND = 0;
    /** キーワード検索区分 (or) */
    public static final int KEY_WORD_KBN_OR = 1;

    /** 抽出対象　フォルダ */
    public static final int GET_TARGET_FOLDER = 1;
    /** 抽出対象　ファイル */
    public static final int GET_TARGET_FILE = 1;

    /** キーワード検索対象　名前 */
    public static final int KEYWORD_TARGET_NAME = 1;
    /** キーワード検索対象　備考 */
    public static final int KEYWORD_TARGET_BIKO = 1;
    /** キーワード検索対象　ファイル内容 */
    public static final int KEYWORD_TARGET_TEXT = 1;
    /** ソート　名前 */
    public static final int SORT_NAME = 1;
    /** ソート サイズ */
    public static final int SORT_SIZE = 2;
    /** ソート 更新通知 */
    public static final int SORT_CALL = 3;
    /** ソート 更新日付 */
    public static final int SORT_EDATE = 4;
    /** ソート 更新者名 */
    public static final int SORT_EUSR = 5;

    /** 更新通知一覧表示件数 */
    public static final int CALL_LIST_DSP_CNT = 30;

    /** マーク添付ファイル フォルダ名 */
    public static final String TEMP_DIR_MARK = "mark";
    /** 添付ファイル フォルダ名 */
    public static final String TEMP_DIR_NORMAL = "temp";

    /** ファイル管理ポートレット フォルダ階層選択画面ID */
    public static final String SCREENID_FILPTL020 = "filptl020";

    /** 親ディレクトリアクセス権限最大表示件数 */
    public static final String MAXCOUNT_PARENT_ACCESS = "5";

    /** 下位フォルダに適応 しない */
    public static final String ADAPT_FILE_INC_KBN_OFF = "0";
    /** 下位フォルダに適応 する */
    public static final String ADAPT_FILE_INC_KBN_ON = "1";

    /** 集計データ登録区分 新規 */
    public static final int LOG_COUNT_KBN_NEW = 0;
    /** 集計データ登録区分 更新 */
    public static final int LOG_COUNT_KBN_EDIT = 1;

    /** ファイル管理集計データ_集計結果 ログ区分 アップロード */
    public static final int FLS_KBN_UPLOAD = 0;
    /** ファイル管理集計データ_集計結果 ログ区分 ダウンロード */
    public static final int FLS_KBN_DOWNLOAD = 1;

    /** 統計グラフ ダウンロード件数 */
    public static final String FIL_LOG_GRAPH_DOWNLOAD = "fil_graph_download";
    /** 統計グラフ 登録件数 */
    public static final String FIL_LOG_GRAPH_UPLOAD = "fil_graph_upload";

    /** ファイルテキスト読込区分 読込み成功 */
    public static final int READ_KBN_SUCCESSFUL = 0;
    /** ファイルテキスト読込区分 パスワード保護による読込みエラー */
    public static final int READ_KBN_ERROR_PASSWORD = 8;
    /** ファイルテキスト読込区分 読込みエラー or 対象外 */
    public static final int READ_KBN_ERROR = 9;
}