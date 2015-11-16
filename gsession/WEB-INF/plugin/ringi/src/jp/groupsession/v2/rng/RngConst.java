package jp.groupsession.v2.rng;

/**
 * <br>[機  能] 稟議 定数一覧
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class RngConst {

    /** プラグインID 稟議 */
    public static final String PLUGIN_ID_RINGI = "ringi";
    /** プラグインID 稟議テンプレート */
    public static final String PLUGIN_ID_RINGI_TEMPLATE = "ringitemplate";

    /** 稟議リスナークラスの定義名 */
    public static final String RNG_LISTENER_ID = "rnglistener";

    /** 採番ID 稟議 */
    public static final String SBNSID_RINGI = "ringi";
    /** 採番IDサブ 稟議SID */
    public static final String SBNSID_SUB_RINGI_ID = "rngid";
    /** 採番IDサブ 稟議テンプレートSID */
    public static final String SBNSID_SUB_RINGI_TEMPLATE = "rngtplid";
    /** 採番IDサブ 経路テンプレートSID */
    public static final String SBNSID_SUB_RINGI_CHANNEL_TEMPLATE = "rngcntplid";
    /** 採番IDサブ テンプレートカテゴリSID */
    public static final String SBNSID_SUB_RINGI_TEMPLATE_CATEGORY = "category";

    /** オーダーキー 昇順 */
    public static final int RNG_ORDER_ASC = 0;
    /** オーダーキー 降順 */
    public static final int RNG_ORDER_DESC = 1;
    /** 検索条件 AND */
    public static final int RNG_SEARCHTYPE_AND = 0;
    /** 検索条件 OR */
    public static final int RNG_SEARCHTYPE_OR = 1;

    /** ソートキー タイトル */
    public static final int RNG_SORT_TITLE = 0;
    /** ソートキー 姓名 */
    public static final int RNG_SORT_NAME = 1;
    /** ソートキー 申請日時 */
    public static final int RNG_SORT_DATE = 2;
    /** ソートキー 受信日 */
    public static final int RNG_SORT_JYUSIN = 3;
    /** ソートキー 確認日 */
    public static final int RNG_SORT_KAKUNIN = 4;
    /** ソートキー 登録日時 */
    public static final int RNG_SORT_TOUROKU = 5;
    /** ソートキー 結果 */
    public static final int RNG_SORT_KEKKA = 6;

    /** モード 受信 */
    public static final int RNG_MODE_JYUSIN = 0;
    /** モード 申請中 */
    public static final int RNG_MODE_SINSEI = 1;
    /** モード 完了 */
    public static final int RNG_MODE_KANRYO = 2;
    /** モード 草稿 */
    public static final int RNG_MODE_SOUKOU = 3;

    /** 画面モード メイン以外 */
    public static final int RNG_MODE_NOT_MAIN = 0;
    /** 画面モード メイン */
    public static final int RNG_MODE_MAIN = 1;

    /** テンプレートモード 全て */
    public static final int RNG_TEMPLATE_ALL = 0;
    /** テンプレートモード 共有 */
    public static final int RNG_TEMPLATE_SHARE = 1;
    /** テンプレートモード 個人 */
    public static final int RNG_TEMPLATE_PRIVATE = 2;

    /** 承認者種別 承認者 */
    public static final int RNG_RNCTYPE_APPR = 0;
    /** 承認者種別 最終確認者 */
    public static final int RNG_RNCTYPE_CONFIRM = 1;
    /** 承認者種別 申請者 */
    public static final int RNG_RNCTYPE_APPL = 2;

    /** 稟議 状態 草稿 */
    public static final int RNG_STATUS_DRAFT = 0;
    /** 稟議 状態 申請中 */
    public static final int RNG_STATUS_REQUEST = 1;
    /** 稟議 状態 決裁 */
    public static final int RNG_STATUS_SETTLED = 2;
    /** 稟議 状態 却下 */
    public static final int RNG_STATUS_REJECT = 3;

    /** 稟議経路情報 状態 未設定 */
    public static final int RNG_RNCSTATUS_NOSET = 0;
    /** 稟議経路情報 状態 確認中 */
    public static final int RNG_RNCSTATUS_CONFIRM = 1;
    /** 稟議経路情報 状態 承認 */
    public static final int RNG_RNCSTATUS_APPR = 2;
    /** 稟議経路情報 状態 否認 */
    public static final int RNG_RNCSTATUS_DENIAL = 3;
    /** 稟議経路情報 状態 確認 */
    public static final int RNG_RNCSTATUS_CONFIRMATION = 4;

    /** 完了フラグ 未定 */
    public static final int RNG_COMPFLG_UNDECIDED = 0;
    /** 完了フラグ 完了 */
    public static final int RNG_COMPFLG_COMPLETE = 1;

    /** 処理モード 登録 */
    public static final int RNG_CMDMODE_ADD = 0;
    /** 処理モード 更新 */
    public static final int RNG_CMDMODE_EDIT = 1;

    /** 承認モード 稟議承認 */
    public static final int RNG_APPRMODE_APPR = 0;
    /** 承認モード 審議中承認 */
    public static final int RNG_APPRMODE_DISCUSSING  = 1;
    /** 承認モード 承認完了 */
    public static final int RNG_APPRMODE_COMPLETE  = 2;
    /** 承認モード 再申請 */
    public static final int RNG_APPRMODE_APPL = 3;

    /** 稟議一覧 1ページの最大表示件数 */
    public static final int RNG_PAGE_VIEWCNT = 30;
    /** タイトルMAX文字数 */
    public static final int MAX_LENGTH_TITLE = 100;
    /** 内容MAX文字数 */
    public static final int MAX_LENGTH_CONTENT = 1000;
    /** フォーマットMAX文字数 */
    public static final int MAX_LENGTH_FORMAT = 1000;
    /** コメントMAX文字数 */
    public static final int MAX_LENGTH_COMMENT = 300;
    /** 経路テンプレート名称MAX文字数 */
    public static final int MAX_LENGTH_KEIRONAME = 20;

    /** 管理者設定 ショートメール通知設定 各ユーザが設定 */
    public static final int RAR_SML_NTF_USER = 0;
    /** 管理者設定 ショートメール通知設定  管理者が設定 */
    public static final int RAR_SML_NTF_ADMIN = 1;
    /** 管理者設定 ショートメール通知設定区分 通知する */
    public static final int RAR_SML_NTF_KBN_YES = 0;
    /** 管理者設定 ショートメール通知設定区分 通知しない */
    public static final int RAR_SML_NTF_KBN_NO = 1;

    /** 個人設定 ショートメール通知(通知する) */
    public static final int RNG_SMAIL_TSUUCHI = 0;
    /** 個人設定 ショートメール通知(通知しない) */
    public static final int RNG_SMAIL_NOT_TSUUCHI = 1;

    /** 処理区分 初期表示 */
    public static final int DSP_FIRST = 0;
    /** 処理区分 初期表示完了 */
    public static final int DSP_ALREADY = 1;

    /** 自動削除区分 削除しない */
    public static final int RAD_KBN_NO = 0;
    /** 自動削除区分 削除する */
    public static final int RAD_KBN_DELETE = 1;
    /** 手動削除区分 削除しない */
    public static final int MANU_DEL_NO = 0;
    /** 手動削除区分 削除する */
    public static final int MANU_DEL_OK = 1;

    /** 年 0年 */
    public static final int YEAR_ZERO = 0;
    /** 年 1年 */
    public static final int YEAR_ONE = 1;
    /** 年 2年 */
    public static final int YEAR_TWO = 2;
    /** 年 3年 */
    public static final int YEAR_THREE = 3;
    /** 年 4年 */
    public static final int YEAR_FOUR = 4;
    /** 年 5年 */
    public static final int YEAR_FIVE = 5;
    /** 年 10年 */
    public static final int YEAR_TEN = 10;

    /** 削除 月 開始月 */
    public static final int DEL_MONTH_START = 0;
    /** 削除 月 終了月 */
    public static final int DEL_MONTH_END = 11;
    /** 削除 日 開始日 */
    public static final int DEL_DAY_START = 0;
    /** 削除 日 終了日 */
    public static final int DEL_DAY_END = 30;

    /** 年キーALL */
    public static final int[] LIST_YEAR_KEY_ALL = new int[] { YEAR_ZERO,
        YEAR_ONE, YEAR_TWO, YEAR_THREE, YEAR_FOUR, YEAR_FIVE, YEAR_TEN };

    /** 稟議 管理者設定 稟議削除権限 管理者のみ */
    public static final int RAR_DEL_AUTH_ADM = 0;
    /** 稟議 管理者設定 稟議削除権限 制限なし */
    public static final int RAR_DEL_AUTH_UNRESTRICTED = 1;
}
