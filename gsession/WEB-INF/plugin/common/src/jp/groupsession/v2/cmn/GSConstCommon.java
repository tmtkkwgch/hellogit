package jp.groupsession.v2.cmn;

/**
 * <br>[機  能] 共通画面で使用する定数一覧
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class GSConstCommon {

    /** 採番区分 SID 共通 */
    public static final String SBNSID_COMMON = "common";
    /** 採番区分 SID SUB */
    public static final String SBNSID_SUB_COMMON = "cmn";
    /** 採番区分 SID SUB マイグループ */
    public static final String SBNSID_SUB_MYGROUP = "mygroup";

    /** プラグインID スケジュール */
    public static final String PLUGIN_ID_SCHEDULE = "schedule";
    /** プラグインID 施設予約 */
    public static final String PLUGIN_ID_RESERVE = "reserve";
    /** プラグインID プロジェクト */
    public static final String PLUGIN_ID_PROJECT = "project";
    /** プラグインID 日報 */
    public static final String PLUGIN_ID_NIPPOU = "nippou";

    /** マイグループ名MAX文字数 */
    public static final int MAX_LENGTH_MYGROUPNAME = 20;
    /** メモMAX文字数 */
    public static final int MAX_LENGTH_MEMO = 1000;

    /** メッセージに表示するテキスト URL */
    public static final String TEXT_ENT_URL = "URL";
    /** 雛形名称MAX文字数 */
    public static final int MAX_LENGTH_HINANAME = 50;
    /** 件名MAX文字数 */
    public static final int MAX_LENGTH_SMLTITLE = 70;
    /** 本文MAX文字数 */
    public static final int MAX_LENGTH_SMLBODY = 2000;
    /** 企業情報 会社名MAX文字数 */
    public static final int MAX_LENGTH_ENT_NAME = 50;
    /** 企業情報 会社カナMAX文字数 */
    public static final int MAX_LENGTH_ENT_NAMEKN = 100;
    /** 企業情報 URL文字数MAX文字数 */
    public static final int MAX_LENGTH_ENT_URL = 100;
    /** 企業情報 備考文字数MAX文字数 */
    public static final int MAX_LENGTH_ENT_BIKO = 1000;

    /** オペレーションログ 操作内容補足 MAX文字数 */
    public static final int MAX_LENGTH_LOG_OP_VALUE = 3000;

    //汎用
    /** 有効状態を表す */
    public static final String ENABLE = "enable";
    /** 無効状態を表す */
    public static final String DISABLE = "disable";

    /** 添付ファイル(本体)の接尾文字列 */
    public static final String ENDSTR_SAVEFILE = "file";
    /** オブジェクトファイルの接尾文字列 */
    public static final String ENDSTR_OBJFILE = "obj";

    /** ファイルサイズ 1MB */
    public static final int FILE_SIZE_1MB = 1048576;
    /** 添付ファイル容量の最大値 */
    public static final int FILE_MAX_SIZE = 10485760;
    /** 添付ファイル容量の最大値(MB) */
    public static final String TEXT_FILE_MAX_SIZE = "100MB";
    /** 添付ファイルの登録数制限 無制限 */
    public static final int FILE_LIMIT_FILENUM_UNLIMITED = 0;
    /** 添付ファイルの登録数制限 1件のみ */
    public static final int FILE_LIMIT_FILENUM_ONE = 1;

    /** 添付ファイル取り扱い後に元ファイルを削除しない */
    public static final int TEMPFILE_FINALIZE_NOTDEL = 0;
    /** 添付ファイル取り扱い後に元ファイルを削除する */
    public static final int TEMPFILE_FINALIZE_DEL = 1;


    /** 添付ファイル処理モード 0(通常：複数ファイル連続登録) */
    public static final int FILE_UPLOAD_MODE_RETO = 0;
    /** 添付ファイル処理モード 1(画像：単一画像ファイル登録) */
    public static final int FILE_UPLOAD_MODE_TANITU = 1;
    /** 添付ファイル処理モード 2(プロジェクト管理のファイル管理) */
    public static final int FILE_UPLOAD_MODE_FILE = 2;
    /** 添付ファイル処理モード 3(ファイル管理) */
    public static final int FILE_UPLOAD_MODE_FILEKANRI = 3;
    /** 添付ファイル処理モード 4(画像：単一画像ファイル登録ユーザ登録) */
    public static final int FILE_UPLOAD_MODE_TANITU_USR031 = 4;
    /** 添付ファイル処理モード 5(画像：単一画像ファイル登録キャビネット登録) */
    public static final int FILE_UPLOAD_MODE_TANITU_FIL030 = 5;
    /** 添付ファイル処理モード 6(画像：複数ファイル連続登録施設画像登録) */
    public static final int FILE_UPLOAD_MODE_RETO_RSV090 = 6;

    /** 数値フィールド初期値 */
    public static final int NUM_INIT = -1;

    /** メイン画面表示区分(表示) */
    public static final int MAIN_DSP = 1;
    /** メイン画面表示区分(非表示) */
    public static final int MAIN_NOT_DSP = 0;
    /** メイン画面表示区分(自動リロード時間設定時) */
    public static final int MAIN_RELOAD = 2;

    /** メイン画面自動リロード時間 */
    public static final int MAIN_DSPRELOAD = 600000;

    /** メイン画面項目 時計 */
    public static final String MAIN_DSPVALUE_CLOCK = "1";
    /** メイン画面項目 最終ログイン時間 */
    public static final String MAIN_DSPVALUE_LOGINHISTORY = "2";
    /** メイン画面項目 自動リロード時間 */
    public static final String MAIN_DSPVALUE_AUTORELOAD = "3";
    /** メイン画面項目 天気予報 */
    public static final String MAIN_DSPVALUE_WEATHER = "4";
    /** メイン画面項目 今日は何の日？ */
    public static final String MAIN_DSPVALUE_ANNIVERSARY = "5";
    /** メイン画面項目 今日は何の日？ */
    public static final String MAIN_DSPVALUE_NEWS = "6";
    /** メイン画面項目(時計),(最終ログイン時間),(自動リロード時間),(天気予報) */
    public static final String[] MAIN_DSPVALUE = {MAIN_DSPVALUE_CLOCK,
                                                    MAIN_DSPVALUE_LOGINHISTORY,
                                                    MAIN_DSPVALUE_AUTORELOAD,
                                                    MAIN_DSPVALUE_WEATHER,
                                                    MAIN_DSPVALUE_ANNIVERSARY,
                                                    MAIN_DSPVALUE_NEWS};

    /** 天気予報 初期表示地域(東京) */
    public static final String MAIN_DSP_WEATHER_INIT_AREA = "18";

    /** ユーザ情報popup リンクタイプ 無効 */
    public static final int APPENDINFO_LINK_TYPE_NONE = 0;
    /** ユーザ情報popup リンクタイプ スケジュール */
    public static final int APPENDINFO_LINK_TYPE_SCH = 1;

    /** 端末区分 0=全て */
    public static final int TERMINAL_KBN_ALL = 0;
    /** 端末区分 1=PC */
    public static final int TERMINAL_KBN_PC = 1;
    /** 端末区分 2=モバイル */
    public static final int TERMINAL_KBN_MOBILE = 2;
    /** 端末区分 テキスト 1=PC */
    public static final String TERMINAL_KBN_PC_TEXT = "PC";

    /** キャリア 0=全て */
    public static final int CAR_KBN_PC_ALL = 0;
    /** キャリア 1=PC */
    public static final int CAR_KBN_PC = 1;
    /** キャリア 2=DoCoMo */
    public static final int CAR_KBN_DOCOMO = 2;
    /** キャリア 3=KDDI */
    public static final int CAR_KBN_KDDI = 3;
    /** キャリア 4=SoftBank */
    public static final int CAR_KBN_SOFTBANK = 4;

    /** キャリア テキスト 1=PC */
    public static final String CAR_KBN_PC_TEXT = "PC";
    /** キャリア テキスト 2=DoCoMo */
    public static final String CAR_KBN_DOCOMO_TEXT = "docomo";
    /** キャリア テキスト 3=KDDI */
    public static final String CAR_KBN_KDDI_TEXT = "au";
    /** キャリア テキスト 4=SoftBank */
    public static final String CAR_KBN_SOFTBANK_TEXT = "SoftBank";

    /** ID/パスワード保存区分  0=保存する */
    public static final int IDPASS_SAVE_OK = 0;
    /** ID/パスワード保存区分  1=保存しない */
    public static final int IDPASS_SAVE_NG = 1;

    /** 企業情報 期首月 デフォルト */
    public static final int DEFAULT_KISYU = 4;


    /** ロックアウト ログイン失敗回数 */
    public static final String LOCKOUT_FAILCOUNT = "LOGIN_FAILCOUNT";

    /** ログイン設定 ロックアウト区分 ロックアウトしない */
    public static final int LOGIN_LOCKKBN_NOSET = 0;
    /** ログイン設定 ロックアウト区分 ロックアウトする */
    public static final int LOGIN_LOCKKBN_LOCKOUT = 1;
    /** ログイン設定 ログイン失敗回数 最大値 */
    public static final int LOGIN_FAILCOUNT_MAX = 20;
    /** ログイン設定 失敗カウント期間 */
    public static final int[] LOGIN_FAILTIME_LIST = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                                                        20, 30, 60, 120};
    /** ログイン設定 ロック期間 */
    public static final int[] LOGIN_LOCKTIME_LIST = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                                                        20, 30, 60, 120};

    /** OutOｆMemory */
    public static final String OUT_OF_MEMORY = "OutOfMemory";

    /** モード:新規登録 */
    public static final int MODE_ADD = 0;
    /** モード:編集 */
    public static final int MODE_EDIT = 1;

    /** 日付 末日 */
    public static final int LAST_DAY_OF_MONTH = 99;

    /** グループ選択コンボボックスでのグループ一覧のグループSID */
    public static final int GRP_SID_GRPLIST = -9;

    /** 年 0年 */
    public static final int YEAR_ZERO = 0;
    /** 月 0月*/
    public static final int MONTH_ZERO = 0;
    /** 日 0日*/
    public static final int DAY_ZERO = 0;
}