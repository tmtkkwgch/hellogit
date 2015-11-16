package jp.groupsession.v2.man.biz;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.encryption.Sha;
import jp.groupsession.v2.cmn.dao.base.CmnPositionDao;
import jp.groupsession.v2.cmn.dao.base.CmnPswdConfDao;
import jp.groupsession.v2.cmn.dao.base.SaibanDao;
import jp.groupsession.v2.cmn.model.base.CmnPswdConfModel;
import jp.groupsession.v2.cmn.model.base.SaibanModel;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.man.dao.base.CmnPconfEditDao;
import jp.groupsession.v2.man.model.base.CmnPconfEditModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] メイン 共通ビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class MainCommonBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(MainCommonBiz.class);

    /**
     * <p>パスフレーズ定数
     * <p>echo -n "GS backup_auto" | sha256sum の結果
     * <p>[d9dd047e392a218ddcfff577373e00a5c843fed97986688586864f8a075a8036]
     * <p>パスフレーズは最大16桁までなので先頭から16文字
     */
    public static final String BACKUP_AUTO_PHRASE = "d9dd047e392a218d";

    /**
     * <p>パスフレーズ定数
     * <p>echo -n "GS backup_self" | sha256sum の結果
     * <p>[4aef2d11b117f6ea59dc86b5db7bb912dfc41bf593018ae388f5607f98250bf8]
     * <p>パスフレーズは最大16桁までなので先頭から16文字
     */
    public static final String BACKUP_SELF_PHRASE = "4aef2d11b117f6ea";

    /**
     * <br>[機  能] 自動バックアップファイル名のハッシュ値を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param text ファイル名
     * @return ハッシュ値
     */
    public static String getAutoBackUpHashName(String text) {

        log__.debug("ファイル名よりハッシュ値を取得（自動バックアップ）");

        String fileName = Sha.encryptSha256(BACKUP_AUTO_PHRASE, text);
        fileName = NullDefault.getString(fileName, "");

        if (fileName.length() < 1) {
            log__.debug("ハッシュ値を取得失敗（自動バックアップ）");
        }

        return fileName;
    }

    /**
     * <br>[機  能] 手動バックアップファイル名のハッシュ値を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param text ファイル名
     * @return ハッシュ値
     */
    public static String getSelfBackUpHashName(String text) {

        log__.debug("ファイル名よりハッシュ値を取得（手動バックアップ）");

        String fileName = Sha.encryptSha256(BACKUP_SELF_PHRASE, text);
        fileName = NullDefault.getString(fileName, "");

        if (fileName.length() < 1) {
            log__.debug("ハッシュ値を取得失敗（手動バックアップ）");
        }

        return fileName;
    }

    /**
     * <br>[機  能] 個人情報編集権限の設定値を取得します。
     * <br>[解  説]
     * <br>[備  考]
     * @param userSid セッションユーザSID
     * @param con コネクション
     * @return CmnPconfEditModel 管理者設定
     * @throws SQLException SQL実行時例外
     */
    public CmnPconfEditModel getCpeConf(int userSid, Connection con) throws SQLException {

        CmnPconfEditDao dao = new CmnPconfEditDao(con);
        CmnPconfEditModel ret = dao.selectConf();

        //未設定の場合、初期値を設定する
        if (ret == null) {
            UDate now = new UDate();

            ret = new CmnPconfEditModel();
            ret.setCpePconfKbn(GSConstMain.PCONF_EDIT_USER);
            ret.setCpePasswordKbn(GSConstMain.PASSWORD_EDIT_USER);
            ret.setCpeAuid(userSid);
            ret.setCpeAdate(now);
            ret.setCpeEuid(userSid);
            ret.setCpeEdate(now);
        }

        return ret;
    }

    /**
     * <br>[機  能] パスワード有効期限設定値を取得します。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @return パスワード有効期限設定値（0：無制限、1：有効期限あり）
     * @throws SQLException SQL実行例外
     */
    public int checkPassLimit(Connection con) throws SQLException {

        int limit  = -1;
        CmnPswdConfDao dao = new CmnPswdConfDao(con);
        CmnPswdConfModel model = dao.select();

        if (model != null) {
            //設定
            if (model.getPwcLimitDay() == -1) {
                limit = GSConstMain.PWC_LIMITKBN_OFF;
            } else {
                limit = GSConstMain.PWC_LIMITKBN_ON;
            }
        } else {
            //初期
            limit = GSConstMain.PWC_LIMITKBN_OFF;
        }
        return limit;
    }


    /**
     * <br>[機  能] 役職コードを自動生成し取得する
     * <br>[解  説] 役職コード生成ルール
     *                     1. 役職SIDと同じ値を返す
     *                     2. 1で発行したコードが既に使用されていた場合は役職SIDの最大＋１を返す
     *                     3. 2で取得したコードが既に使用されていた場合は重複しないまで＋１を繰り替えす
     * <br>[備  考]
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @return 役職コード
     */
    public String getAutoPosCode(Connection con) throws SQLException {

        SaibanDao sbDao = new SaibanDao(con);

        //采番マスタ検索モデル
        SaibanModel searchMdl = new SaibanModel();
        searchMdl.setSbnSid(SaibanModel.SBNSID_USER);
        searchMdl.setSbnSidSub(SaibanModel.SBNSID_SUB_POS);

        //采番マスタより役職SIDの現在のMAX採番値を取得する
        SaibanModel mdl = sbDao.getSaibanData(searchMdl);

        //采番値として出力される予定の役職SID
        long sbPosSid = 1;
        if (mdl != null) {
            sbPosSid = mdl.getSbnNumber() + 1;
        }

        return getAutoPosCode(con, (int) sbPosSid);
    }

    /**
     * <br>[機  能] 役職コードを自動生成し取得する
     * <br>[解  説] 役職コード生成ルール
     *                     1. 役職SIDと同じ値を返す
     *                     2. 1で発行したコードが既に使用されていた場合は役職SIDの最大＋１を返す
     *                     3. 2で取得したコードが既に使用されていた場合は重複しないまで＋１を繰り替えす
     * <br>[備  考]
     * @param con コネクション
     * @param sbPosSid 采番値として出力される予定の役職SID
     * @throws SQLException SQL実行例外
     * @return 役職コード
     */
    public String getAutoPosCode(Connection con, int sbPosSid) throws SQLException {

        CmnPositionDao cpDao = new CmnPositionDao(con);
        String code = String.valueOf(sbPosSid);

        //重複チェック
        while (cpDao.isExistPositionCode(code, -1)) {
            sbPosSid += 1;
            code = String.valueOf(sbPosSid);
        }

        return code;
    }
}
