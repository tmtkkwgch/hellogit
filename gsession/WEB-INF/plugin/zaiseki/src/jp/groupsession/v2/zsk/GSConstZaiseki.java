package jp.groupsession.v2.zsk;

import jp.groupsession.v2.usr.GSConstUser;

/**
 * <br>在席管理 定数一覧
 * @author JTS
 */
public class GSConstZaiseki {
    /** プラグインID */
    public static final String PLUGIN_ID_ZAISEKI = "zaiseki";
    /** 採番ID 在席管理 */
    public static final String SBNSID_ZAISEKI = "zaiseki";
    /** 採番IDサブ 座席表情報SID */
    public static final String SBNSID_SUB_ZAISEKIINFO = "info";

    /** エレメントKEY文字 */
    public static final String ELEMENT_KEY = "elKey";
    /** エレメントKEY区切り文字 */
    public static final String ELEMENT_SEPARATOR = "-";
    /** エレメント区分(ユーザ) */
    public static final int ELEMENT_KBN_USR = 0;
    /** エレメント区分(施設) */
    public static final int ELEMENT_KBN_RSV = 1;
    /** エレメント区分(その他) */
    public static final int ELEMENT_KBN_ETC = 2;

    /** 施設予約状態(未使用) */
    public static final int RSV_NOT_USED = 0;
    /** 施設予約状態(使用中) */
    public static final int RSV_USED = 1;

    /** オブジェクトファイル名 エレメント詳細情報 */
    public static final String FNAME_ELEMENT_INFO = "zaisekiElementobjfile";
    /** JSONファイル名 エレメント詳細情報 */
    public static final String FNAME_ELEMENT_JSON = "zaisekiElementjsonfile";

    /** 初期表示フラグ 初期表示 */
    public static final String INIT_FLG_ON = "0";
    /** 初期表示フラグ 初期表示以外 */
    public static final String INIT_FLG_OFF = "1";

    /** 座席表SID 未設定 */
    public static final int ZASEKI_NOT_SELECT = -1;
    /** 自動リロード時間 10分 */
    public static final int AUTO_RELOAD_10MIN = 600000;

    /** 表タイトル最大桁数 */
    public static final int MAX_LENGTH_MAPTITLE = 20;
    /** 表示順最大桁数 */
    public static final int MAX_LENGTH_SORTNUM = 3;
    /** 未選択値 */
    public static final int NONE_SELECT = -1;

    /** メイン画面表示のグループ区分(通常グループ) */
    public static final int DSP_GROUP = 0;
    /** メイン画面表示のグループ区分(マイグループ) */
    public static final int DSP_MYGROUP = 1;

    /** メイン画面:リロードを行わない */
    public static final int MAIN_RELOAD_OFF = 0;
    /** メイン画面:リロードを行う */
    public static final int MAIN_RELOAD_ON = 1;

    /** 投稿者画像(表示する) */
    public static final int USR_IMAGE_DSP = 1;
    /** 投稿者画像(表示しない) */
    public static final int USR_IMAGE_NOT_DSP = 0;

    /** 画面遷移モード 在席管理 */
    public static final int MODE_ZAISEKI = 0;
    /** 画面遷移モード メイン */
    public static final int MODE_MAIN = 1;

    /** 遷移先:ショートメール作成へ */
    public static final String SCR_SML_NEW = "gf_smlNew";

    /** ソート 名前 */
    public static final int SORT_KEY_NAME = GSConstUser.USER_SORT_NAME;
    /** ソート 社員/職員番号 */
    public static final int SORT_KEY_SNO = GSConstUser.USER_SORT_SNO;
    /** ソート 役職 */
    public static final int SORT_KEY_YKSK = GSConstUser.USER_SORT_YKSK;
    /** ソート 生年月日 */
    public static final int SORT_KEY_BDATE = GSConstUser.USER_SORT_BDATE;
    /** ソート 在席状況 */
    public static final int SORT_KEY_ZAKSTS = GSConstUser.USER_SORT_UIO;
    /** ソート 在席コメント */
    public static final int SORT_KEY_COMM = GSConstUser.USER_SORT_COMM;
    /** ソート ソートキー1 */
    public static final int SORT_KEY_SORTKEY1 = GSConstUser.USER_SORT_SORTKEY1;
    /** ソート ソートキー2 */
    public static final int SORT_KEY_SORTKEY2 = GSConstUser.USER_SORT_SORTKEY2;

    /** ソートキーALL */
    public static final int[] SORT_KEY_ALL = new int[] { SORT_KEY_NAME,
            SORT_KEY_SNO, SORT_KEY_YKSK, SORT_KEY_BDATE,
            SORT_KEY_SORTKEY1, SORT_KEY_SORTKEY2 };

    /** ソートキー 在席表 */
    public static final int[] SORT_KEY_ZSK =
        new int[] {SORT_KEY_NAME, SORT_KEY_SNO, SORT_KEY_YKSK,
        SORT_KEY_BDATE, SORT_KEY_ZAKSTS, SORT_KEY_COMM,
        SORT_KEY_SORTKEY1, SORT_KEY_SORTKEY2};

    /** メイン画面スケジュール表示区分 表示する */
    public static final int MAIN_SCH_DSP = 0;
    /** メイン画面スケジュール表示区分 表示しない */
    public static final int MAIN_SCH_NOT_DSP = 1;

    /** メイン画面在席グループ表示区分 表示する */
    public static final int MAINGRP_DSP = 0;
    /** メイン画面在席グループ表示区分 表示しない */
    public static final int MAINGRP_NOT_DSP = 1;

    /** 定時一括更新 設定する */
    public static final int FIXED_UPDATE_ON = 0;
    /** 定時一括更新 設定しない */
    public static final int FIXED_UPDATE_OFF = 1;

    /** 座席表表示画像DIR */
    public static final String DSP_IMAGE_DIR = "dspimage";

    /** 管理者設定 在席管理表示区分 0:ユーザ単位で設定 */
    public static final int ADM_SORTKBN_PRI = 0;
    /** 管理者設定 在席管理表示区分 1:管理者強制 */
    public static final int ADM_SORTKBN_ADM = 1;

    /** 在席管理ポートレット グループメンバー選択画面ID */
    public static final String SCREENID_ZSKPTL020 = "zskptl020";

    /** ログ出力種別判別フラグ なし */
    public static final int ZSK_LOG_FLG_NONE = -1;
    /** ログ出力種別判別フラグ 添付ファイル */
    public static final int ZSK_LOG_FLG_DOWNLOAD = 0;
}