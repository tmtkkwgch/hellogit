package jp.groupsession.v2.man;

/**
 * <br>[機  能] メイン 管理定数一覧
 * <br>[解  説]
 * <br>[備  考]
 * @author JTS
 */
public class GSConstMain {

    /** 個人情報編集区分 管理者 */
    public static final int PCONF_EDIT_ADM = 0;
    /** 個人情報編集区分 ユーザ */
    public static final int PCONF_EDIT_USER = 1;
    /** パスワード編集区分 管理者 */
    public static final int PASSWORD_EDIT_ADM = 0;
    /** パスワード編集区分 ユーザ */
    public static final int PASSWORD_EDIT_USER = 1;

    /** 休日設定 処理モード 追加 */
    public static final int CMDMODE_HOLIDAY_ADD = 1;
    /** 休日設定 処理モード 修正 */
    public static final int CMDMODE_HOLIDAY_EDIT = 2;

    /** 全件選択チェックボックス ON */
    public static final int SELECTION_OF_ALL_ON = 1;
    /** 全件選択チェックボックス OFF */
    public static final int SELECTION_OF_ALL_OFF = 0;

    /** バックアップファイル接頭句 v2 */
    public static final String BACKUPFILE_HEADSTR_V2 = "gs2_sys_backup_";
    /** バックアップファイル接頭句 v3 */
    public static final String BACKUPFILE_HEADSTR_V3 = "gs3_sys_backup_";
    /** バックアップファイル接頭句 */
    public static final String BACKUPFILE_HEADSTR = "gs_sys_backup_";

    /** メイン画面ID インフォメーション */
    public static final String MAINSCREENID_INFORMATION = "information";
    /** メイン画面ID 時計 */
    public static final String MAINSCREENID_DAYTIME = "daytime";
    /** メイン画面ID 最終ログイン時間 */
    public static final String MAINSCREENID_LASTLOGIN = "lastlogin";
    /** メイン画面ID 天気予報 */
    public static final String MAINSCREENID_WEATHER = "weather";
    /** メイン画面ID 今日は何の日？ */
    public static final String MAINSCREENID_ANNIVERSARY = "anni";
    /** メイン画面ID ニュース */
    public static final String MAINSCREENID_NEWS = "news";

    /** プラグイン使用する */
    public static final int PLUGIN_USE = 0;
    /** プラグイン使用しない */
    public static final int PLUGIN_NOT_USE = 1;

    /** メインのプラグインID */
    public static final String PLUGIN_ID_MAIN = "main";
    /** ショートメールのプラグインID */
    public static final String PLUGIN_ID_SMAIL = "smail";
    /** 回覧板のプラグインID */
    public static final String PLUGIN_ID_CIRCULAR = "circular";
    /** プロジェクト管理のプラグインID */
    public static final String PLUGIN_ID_PROJECT = "project";
    /** モバイルプラグインID */
    public static final String PLUGIN_ID_MOBILE = "mobile";
    /** ポータルプラグインID */
    public static final String PLUGIN_ID_PORTAL = "portal";

    /** 開始　時 */
    public static final int DAY_START_HOUR = 0;
    /** 開始　分 */
    public static final int DAY_START_MINUTES = 0;
    /** 開始　秒 */
    public static final int DAY_START_SECOND = 0;
    /** 開始　ミリ秒 */
    public static final int DAY_START_MILLISECOND
    = java.util.Calendar.getInstance().getMinimum(java.util.Calendar.MILLISECOND);
    /** 終了　時 */
    public static final int DAY_END_HOUR = 23;
    /** 終了　分 */
    public static final int DAY_END_MINUTES = 59;
    /** 終了　秒 */
    public static final int DAY_END_SECOND = 59;
    /** 終了　ミリ秒 */
    public static final int DAY_END_MILLISECOND
    = java.util.Calendar.getInstance().getMaximum(java.util.Calendar.MILLISECOND);

    /** 自動バックアップ設定 間隔 設定なし */
    public static final int BUCCONF_INTERVAL_NOSET = 0;
    /** 自動バックアップ設定 間隔 毎日 */
    public static final int BUCCONF_INTERVAL_DAILY = 1;
    /** 自動バックアップ設定 間隔 毎週 */
    public static final int BUCCONF_INTERVAL_WEEKLY = 2;
    /** 自動バックアップ設定 間隔 毎月 */
    public static final int BUCCONF_INTERVAL_MONTHLY = 3;

    /** ディスク容量警告値種類 割合(%) */
    public static final int DISKWARN_TYPE_RATE = 1;
    /** ディスク容量警告値種類 容量 */
    public static final int DISKWARN_TYPE_CAPACITY = 2;

    /** プロキシサーバ区分 0:使用しない */
    public static final int PROXY_SERVER_NOT_USE = 0;
    /** プロキシサーバ区分 1:使用する */
    public static final int PROXY_SERVER_USE = 1;
    /** プロキシサーバアドレス MAX文字数 */
    public static final int MAX_LENGTH_PROXY_ADDRESS = 200;
    /** プロキシサーバポート番号 MAX文字数 */
    public static final int MAX_LENGTH_PROXY_PORTNUM = 5;
    /** プロキシサーバポート番号 最大値 */
    public static final int MAX_NUMBER_PROXY_PORTNUM = 65535;
    /** プロキシサーバ ユーザ認証 0:未設定 */
    public static final int PROXY_SERVER_USERAUTH_NOSET = 0;
    /** プロキシサーバ ユーザ認証 1:認証する */
    public static final int PROXY_SERVER_USERAUTH_AUTH = 1;
    /** プロキシサーバ ユーザ認証 ユーザ MAX文字数 */
    public static final int MAX_LENGTH_PROXY_USERAUTH_USER = 256;
    /** プロキシサーバ ユーザ認証 パスワード MAX文字数 */
    public static final int MAX_LENGTH_PROXY_USERAUTH_PASSWORD = 256;
    /** プロキシサーバ アドレス区分 0:未設定 */
    public static final int PROXY_SERVER_ADRKBN_NOSET = 0;
    /** プロキシサーバ アドレス区分 1:プロキシサーバを使用しないアドレスを参照 */
    public static final int PROXY_SERVER_ADRKBN_EXISTADDRESS = 1;

    /** メニュー固定区分 0:設定しない */
    public static final int MENU_STATIC_NOT_USE = 0;
    /** メニュー固定区分 1:固定 */
    public static final int MENU_STATIC_USE = 1;

    /** 休日名MAX文字数 */
    public static final int MAX_LENGTH_HOL_NAME = 20;
    /** 役職コードMAX文字数 */
    public static final int MAX_LENGTH_POS_CODE = 15;
    /** 役職名MAX文字数 */
    public static final int MAX_LENGTH_POS = 30;
    /** 役職備考MAX文字数 */
    public static final int MAX_LENGTH_POS_CMT = 300;
    /** 役職表示順MAX文字数 */
    public static final int MAX_LENGTH_POS_SORT = 4;

    /** エラーメッセージに表示するテキスト 休日 日付 */
    public static final String TEXT_HOLIDAY_DATE = "cmn.date2";
    /** エラーメッセージに表示するテキスト 休日 日付 月 */
    public static final String TEXT_HOLIDAY_DATE_MONTH = "main.date.month";
    /** エラーメッセージに表示するテキスト 休日 日付 日 */
    public static final String TEXT_HOLIDAY_DATE_DAY = "main.src.2";
    /** エラーメッセージに表示するテキスト 休日名 */
    public static final String TEXT_HOLIDAY_NAME = "cmn.holiday.name";
    /** エラーメッセージに表示するテキスト 表示選択 */
    public static final String TEXT_PRINT_OPTION = "main.man031.2";
    /** エラーメッセージに表示するテキスト 拡張 日付 月 */
    public static final String TEXT_HOLIDAY_TEMPLATE_DATE_MONTH = "main.date.month";
    /** エラーメッセージに表示するテキスト 拡張 日付 週 */
    public static final String TEXT_HOLIDAY_TEMPLATE_DATE_WEEK = "main.src.5";
    /** エラーメッセージに表示するテキスト 拡張 日付 曜日 */
    public static final String TEXT_HOLIDAY_TEMPLATE_DATE_DAY_OF_WEEK = "main.src.6";
    /** エラーメッセージに表示するテキスト 振替 */
    public static final String TEXT_HOLIDAY_TEMPLATE_FURIKAE = "main.src.7";
    /** エラーメッセージに表示するテキスト プロキシサーバ */
    public static final String TEXT_PROXY_SERVER = "main.src.8";
    /** エラーメッセージに表示するテキスト プロキシサーバ使用区分 */
    public static final String TEXT_PROXY_USE_KBN = "main.src.9";
    /** エラーメッセージに表示するテキスト プロキシサーバアドレス */
    public static final String TEXT_PROXY_ADDRESS = "cmn.address.2";
    /** エラーメッセージに表示するテキスト プロキシサーバポート番号 */
    public static final String TEXT_PROXY_PORTNUM = "cmn.port.number";
    /** エラーメッセージに表示するテキスト 役職コード */
    public static final String TEXT_POS_CODE = "user.src.50";
    /** エラーメッセージに表示するテキスト 役職名 */
    public static final String TEXT_POS_NAME = "cmn.job.title";
    /** エラーメッセージに表示するテキスト 役職備考 */
    public static final String TEXT_POS_CMT = "cmn.memo";
    /** エラーメッセージに表示するテキスト 役職表示順 */
    public static final String TEXT_POS_SORT = "cmn.sort";
    /** エラーメッセージに表示するテキスト メインプラグイン */
    public static final String TEXT_MAIN_PLUGIN = "main.src.10";
    /** エラーメッセージに表示するテキスト システム内使用プラグイン設定 */
    public static final String TEXT_SYSCONF_PLUGIN = "main.src.11";
    /** メッセージに表示するテキスト プラグイン表示設定 */
    public static final String TEXT_SYSCONF_PLUGIN_DSP = "main.src.12";
    /** メッセージに表示するテキスト 添付ファイル */
    public static final String TEXT_FILE_CONF = "cmn.attach.file";
    /** メッセージに表示するテキスト セッション保持時間 */
    public static final String TEXT_SESSION_TIME = "main.man140.1";
    /** メッセージに表示するテキスト オペレーションログ */
    public static final String TEXT_SORT = "cmn.sort.order";
    /** エラーメッセージに表示するテキスト インフォメーション */
    public static final String TEXT_INFO = "cmn.information";
    /** エラーメッセージに表示するテキスト メッセージ */
    public static final String TEXT_MSG = "cmn.message";
    /** エラーメッセージに表示するテキスト 内容 */
    public static final String TEXT_VALUE = "cmn.content";
    /** エラーメッセージに表示するテキスト インフォメーション管理者 */
    public static final String TEXT_INFO_ADMIN = "cmn.information.admin";
    /** エラーメッセージに表示するテキスト 使用制限ユーザ */
    public static final String TEXT_LIMIT_USER = "man.restricted.use.user";
    /** エラーメッセージに表示するテキスト 使用制限ユーザ */
    public static final String TEXT_PERMIT_USER = "main.can.use.user";

    /** 役職 完了メッセージ */
    public static final String POS_MSG = "cmn.post";
    /** 休日 完了メッセージ */
    public static final String HOLIDAY_MSG = "main.src.man020.1";
    /** テンプレート 完了メッセージ */
    public static final String HOLIDAY_TEMPLATE_MSG = "main.holiday.template";
    /** バッチジョブ起動時間設定 完了メッセージ */
    public static final String BATCH_JOB_START_TIME_MSG = "main.src.18";
    /** バッチジョブ */
    public static final String BATCH_JOB_MSG = "main.src.19";
    /** ディスク容量管理 完了メッセージ */
    public static final String DISKSPACE_CONF_MSG = "main.management.disksize";
    /** 自動バックアップ設定 完了メッセージ */
    public static final String BACKUP_CONF_MSG = "cmn.autobackup.setting";
    /** 手動バックアップ設定 完了メッセージ */
    public static final String BACKUP_MANUAL_MSG = "cmn.manual.backup";
    /** バックアップファイル メッセージ */
    public static final String BACKUP_FILE_MSG = "cmn.backupfile";
    /** プラグインマネージャー 確認メッセージ１ */
    public static final String PLUGIN_MNG_CONF_MSG1 = "main.src.24";
    /** プラグインマネージャー 確認メッセージ２ */
    public static final String PLUGIN_MNG_CONF_MSG2 = "main.src.25";

    /** 表示テキスト ライセンスファイル */
    public static final String TEXT_LICENSE_FILE = "main.src.26";
    /** 表示テキスト モバイルID・パスワード保存区分 */
    public static final String TEXT_MBL_IDPASS = "main.src.27";
    /** 表示テキスト モバイル使用一括設定 */
    public static final String TEXT_MBL_CONF = "cmn.mobile.use.massconfig";

    /** 添付ファイル容量 10MB */
    public static final int FILE_SIZE_10MB = 10;
    /** 添付ファイル最大容量 最大値 */
    public static final int FILE_SIZE_MAX = 100;
    /** 写真ファイル最大容量 最大値 */
    public static final int PHOTO_SIZE_MAX = 10;

    /** ログイン履歴自動削除区分 設定しない */
    public static final int LHIS_DELKBN_OFF = 0;
    /** ログイン履歴自動削除区分 自動で削除する */
    public static final int LHIS_DELKBN_ON = 1;

    /** 表示テキスト ログイン履歴自動削除 */
    public static final String TEXT_LHIS_AUTODEL = "main.autodelete.login.history";
    /** 表示テキスト ログイン履歴手動削除 */
    public static final String TEXT_LHIS_SYUDODEL = "main.manualdelete.login.history";

    /** 最終ログイン一覧 */
    public static final String MODE_LIST = "0";
    /** ログイン検索 */
    public static final String MODE_SEARCH = "1";

    /** 同一パスワード利用期限 MAX文字数 */
    public static final int MAX_LENGTH_PSWD_LIMIT_DAY = 3;

    /** パスワード桁数設定区分 設定しない */
    public static final int PWC_DIGITKBN_OFF = 0;
    /** パスワード桁数設定区分 設定する */
    public static final int PWC_DIGITKBN_ON = 1;
    /** デフォルトパスワード桁数 */
    public static final int DEFAULT_DIGIT = 2;

    /** パスワード英数混在設定区分 必須としない */
    public static final int PWC_COEKBN_OFF = 0;
    /** パスワード英数混在設定区分 英数混在を必須とする  */
    public static final int PWC_COEKBN_ON_EN = 1;
    /** パスワード英数混在設定区分 英数記号混在を必須とする  */
    public static final int PWC_COEKBN_ON_ENS = 2;


    /** 同一パスワード利用期限設定区分 設定しない */
    public static final int PWC_LIMITKBN_OFF = 0;
    /** 同一パスワード利用期限設定区分 設定する */
    public static final int PWC_LIMITKBN_ON = 1;

    /** ユーザIDと同一パスワード設定区分 許可する */
    public static final int PWC_UIDPSWDKBN_OFF = 0;
    /** ユーザIDと同一パスワード設定区分 設定しない */
    public static final int PWC_UIDPSWDKBN_ON = 1;

    /** 旧パスワードと新パスワード設定区分 許可する */
    public static final int PWC_OLDPSWDKBN_OFF = 0;
    /** 旧パスワードと新パスワード設定区分 設定しない */
    public static final int PWC_OLDPSWDKBN_ON = 1;

    /** オペレーションログ自動削除設定区分 設定しない */
    public static final int OPE_LOG_NOTCONF = 0;
    /** オペレーションログ自動削除設定区分 自動で削除する */
    public static final int OPE_LOG_CONF = 1;
    /** メッセージに表示するテキスト オペレーションログ自動削除設定 */
    public static final String TEXT_AUTODEL = "man.autodelete.opelog";
    /** 表示テキスト オペレーションログ */
    public static final String TEXT_OPLOG_SYUDODEL = "cmn.operations.log";


    /** 表示テキスト パスワードルール設定 */
    public static final String TEXT_PWC_RULE = "main.src.32";

    /** 管理者・個人設定遷移元 各プラグインより遷移 */
    public static final int BACK_PLUGIN = 0;
    /** 管理者・個人設定遷移元 メイン管理者設定より遷移 */
    public static final int BACK_MAIN_ADM_SETTING = 1;
    /** 管理者・個人設定遷移元 メイン個人設定より遷移 */
    public static final int BACK_MAIN_PRI_SETTING = 2;

    /** プラグイン使用制限 使用制限区分 0:全てのユーザが使用可能 */
    public static final int PCT_KBN_ALLOK = 0;
    /** プラグイン使用制限 使用制限区分 1:グループ/ユーザ毎に設定 */
    public static final int PCT_KBN_MEMBER = 1;

    /** プラグイン制限方法 制限(許可ユーザを指定する。) */
    public static final int PCT_TYPE_LIMIT = 0;
    /** プラグイン制限方法 許可(制限ユーザを指定する。) */
    public static final int PCT_TYPE_PERMIT = 1;

    /** プラグイン使用制限 グループSID・ユーザSID 未設定 */
    public static final int PCM_MEMSID_NOSET = -1;
    /** インフォメーション公開区分 (毎日) */
    public static final int INFO_KBN_DAY = 1;
    /** インフォメーション公開区分 (毎週) */
    public static final int INFO_KBN_WEEK = 2;
    /** インフォメーション公開区分 (毎月) */
    public static final int INFO_KBN_MONTH = 3;
    /** インフォメーション公開区分 (毎日) */
    public static final String INFO_KBN_DAYSTRING = "cmn.everyday";
    /** インフォメーション公開区分 (毎週) */
    public static final String INFO_KBN_WEEKSTRING = "cmn.weekly2";
    /** インフォメーション公開区分 (毎月) */
    public static final String INFO_KBN_MONTHSTRING = "cmn.monthly.2";

    /** タイトルMAX文字数 */
    public static final int MAX_LENGTH_TITLE = 10;
    /** メッセージMAX文字数 */
    public static final int MAX_LENGTH_MSG = 150;
    /** 内容MAX文字数 */
    public static final int MAX_LENGTH_VALUE = 1000;

    /** デフォルト時間指定：from時 */
    public static final int DF_FROM_HOUR = 9;
    /** デフォルト時間指定：from分 */
    public static final int DF_FROM_MINUTES = 0;

    /** デフォルト時間指定：to時 */
    public static final int DF_TO_HOUR = 18;
    /** デフォルト時間指定：to分 */
    public static final int DF_TO_MINUTES = 0;

    /** フォーラムメンバー グループの頭文字 */
    public static final String FORUM_MEMBER_GROUP_HEADSTR = "G";
    /** フォーラムメンバー登録のグループコンボに設定するテキスト グループ一覧 */
    public static final String TEXT_GROUP_COMBO = "cmn.grouplist";
    /** フォーラムメンバー登録のグループコンボに設定するテキスト グループ一覧のVALUE */
    public static final String GROUP_COMBO_VALUE = "-9";

    /** 処理モード　追加 */
    public static final String CMD_ADD = "add";
    /** 処理モード　編集 */
    public static final String CMD_EDIT = "edit";
    /** 処理モード　削除 */
    public static final String CMD_DEL = "del";
    /** 処理モード　追加 */
    public static final int CMD_MAIN_ADD = 0;
    /** 処理モード　編集 */
    public static final int CMD_MAIN_EDIT = 1;
    /** 処理モード　削除 */
    public static final int CMD_MAIN_DEL = 2;

    /** ヘルプモード 登録 */
    public static final String MAN_HELP_TOUROKU = "0";
    /** ヘルプモード 編集 */
    public static final String MAN_HELP_HENSYUU = "1";

    /** 年コンボの表示件数 */
    public static final int YEAR_LIST_CNT = 5;

    /** インフォメーション 休日表示区分 0=そのまま表示する */
    public static final int INFO_HOL_KBN_DEFO = 0;
    /** インフォメーション 休日表示区分 1=表示しない */
    public static final int INFO_HOL_KBN_NO = 1;
    /** インフォメーション 休日表示区分 2=前営業日 */
    public static final int INFO_HOL_KBN_BEFORE = 2;
    /** インフォメーション 休日表示区分 3=翌営業日 */
    public static final int INFO_HOL_KBN_AFTER = 3;

    /** 休日設定インポート用サンプルCSVファイル名 */
    public static final String SAMPLE_CSV_HOLIDAY = "sample_holiday.xls";
    /** 休日テンプレートインポート用サンプルCSVファイル名 */
    public static final String SAMPLE_CSV_HOLIDAY_TEMPLATE = "sample_holiday_template.xls";
    /** エラーメッセージに表示するテキスト 取込みファイル */
    public static final String TEXT_SELECT_FILE = "cmn.capture.file";
    /** エラーメッセージに表示するテキスト CSVファイル */
    public static final String TEXT_CSV_FILE = "cmn.csv.file.format";
    /** 休日設定インポート項目数 */
    public static final int IMP_VALUE_SIZE_ADM = 4;
    /** 休日テンプレートインポート項目数 */
    public static final int IMP_VALUE_SIZE_TEMPLATE = 7;
    /** 表示テキスト CSVファイル */
    public static final String TEXT_CSV_VALUE_COUNT = "cmn.csv.number.items";

    /** ZIPバックアップフラグ OFF */
    public static final int ZIP_BACKUP_FLG_OFF = 0;
    /** ZIPバックアップフラグ ON */
    public static final int ZIP_BACKUP_FLG_ON = 1;

    /** 所属情報一括設定 エクスポート */
    public static final String MODE_EXPORT = "0";
    /** 所属情報一括設定 インスポート */
    public static final String MODE_IMPORT = "1";

    /** 所属情報インポートサンプルCSVファイル名 */
    public static final String SAMPLE_MAN330_CSV_NAME = "sample_user_group_import.xls";

    /** CSV出力対象 ユーザID */
    public static final String CSV_OUT_USER_ID = "1";
    /** CSV出力対象 氏名 */
    public static final String CSV_OUT_NAME = "2";
    /** CSV出力対象 氏名カナ */
    public static final String CSV_OUT_NAME_KN = "3";
    /** CSV出力対象 グループID */
    public static final String CSV_OUT_GROUP_ID = "4";
    /** CSV出力対象 グループ名 */
    public static final String CSV_OUT_GROUP_NAME = "5";
    /** CSV出力対象 グループ名カナ */
    public static final String CSV_OUT_GROUP_NAME_KN = "6";

    /** プラグイン区分  既定プラグイン*/
    public static final int PLUGIN_GS = 0;
    /** プラグイン区分  ユーザプラグイン*/
    public static final int PLUGIN_USER = 1;
    /** 別ウィンドウパラメータ blank */
    public static final int TARGET_BLANK = 0;
    /** 別ウィンドウパラメータ  body*/
    public static final int TARGET_BODY = 1;
    /** 別ウィンドウパラメータ blank */
    public static final String TARGET_BLANK_STR = "_blank";
    /** 別ウィンドウパラメータ  body*/
    public static final String TARGET_BODY_STR = "body";
    /** プラグインパス */
    public static final String PLUGIN_PATH = "/WEB-INF/plugin/";
    /** プラグイン */
    public static final String PLUGIN_MSG = "cmn.plugin";
    /** ユーザ追加プラグイン */
    public static final String USER_PLUGIN_HEAD = "pl";

    /** メイン画面レイアウト設定区分 管理者が設定する。 */
    public static final int MANSCREEN_LAYOUTKBN_ADMIN = 0;
    /** メイン画面レイアウト設定区分 ユーザ毎に設定する。 */
    public static final int MANSCREEN_LAYOUTKBN_USER = 1;

    /** メイン画面レイアウトデフォルト区分 デフォルト */
    public static final int MANSCREEN_LAYOUT_DEFAULT = 0;
    /** メイン画面レイアウトデフォルト区分 カスタマイズ */
    public static final int MANSCREEN_LAYOUT_CUSTOM = 1;

    /** レイアウト表示区分 表示 */
    public static final int LAYOUT_VIEW_ON = 0;
    /** レイアウト表示区分 非表示 */
    public static final int LAYOUT_VIEW_OFF = 1;

    /** 処理区分 初期表示 */
    public static final int DSP_FIRST = 0;
    /** 処理区分 初期表示完了 */
    public static final int DSP_ALREADY = 1;
    /** 自動削除区分 設定しない */
    public static final int LAD_DELKBN_NO = 0;
    /** 自動削除区分 自動削除する */
    public static final int LAD_DELKBN_AUTO = 1;
    /** 手動削除区分 削除しない */
    public static final int LMD_DELKBN_NO = 0;
    /** 手動削除区分 削除する */
    public static final int LMD_DELKBN_OK = 1;

    /** 統計情報日付表示単位 日毎 */
    public static final int STATS_DATE_UNIT_DAY = 0;
    /** 統計情報日付表示単位 週毎 */
    public static final int STATS_DATE_UNIT_WEEK = 1;
    /** 統計情報日付表示単位 月毎 */
    public static final int STATS_DATE_UNIT_MONTH = 2;

    /** URLパラメータ設定区分 設定しない */
    public static final int PARAM_KBN_NO = 0;
    /** URLパラメータ設定区分 設定する */
    public static final int PARAM_KBN_YES = 1;

    /** 送信方法区分 POST */
    public static final int SEND_KBN_POST = 0;
    /** 送信方法区分 GET */
    public static final int SEND_KBN_GET = 1;

    /** URLパラメータ名 MAX文字数 */
    public static final int MAX_LENGTH_PARAM_NAME = 100;
    /** URLパラメータ値 MAX文字数 */
    public static final int MAX_LENGTH_PARAM_VALUE = 1000;

    /** URLパラメータ 設定可能件数 */
    public static final int MAX_SET_PARAM_NUM = 10;

    /** 検索キーワード 最大文字数 */
    public static final int MAX_KEYWORD_LENGTH_TITLE = 50;
    /** キーワード検索区分 (and) */
    public static final int KEY_WORD_KBN_AND = 0;
    /** キーワード検索区分 (or) */
    public static final int KEY_WORD_KBN_OR = 1;
    /** 検索対象 画面・機能名 */
    public static final int SEARCH_TARGET_FUNC = 1;
    /** 検索対象 操作 */
    public static final int SEARCH_TARGET_OPERATION = 2;
    /** 検索対象 内容 */
    public static final int SEARCH_TARGET_CONTENT = 3;
    /** 検索対象 IPアドレス */
    public static final int SEARCH_TARGET_IPADDRESS = 4;

}