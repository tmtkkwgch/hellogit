package jp.groupsession.v2.rng.rng010;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jp.co.sjts.util.PageUtil;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.rng.biz.RngBiz;
import jp.groupsession.v2.rng.dao.RingiDao;
import jp.groupsession.v2.rng.model.RingiDataModel;
import jp.groupsession.v2.rng.model.RingiSearchModel;
import jp.groupsession.v2.rng.model.RngAconfModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 稟議一覧画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng010Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rng010Biz.class);

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @param admin 管理者か否か true:管理者, false:一般ユーザ
     * @throws Exception 実行例外
     */
    public void setInitData(Rng010ParamModel paramMdl, RequestModel reqMdl,
                            Connection con, boolean admin)
    throws Exception {
        log__.debug("START");

        int userSid = reqMdl.getSmodel().getUsrsid();

        if (admin) {
            paramMdl.setRng010adminFlg(1);
        } else {
            paramMdl.setRng010adminFlg(0);
        }

        RingiDao dao = new RingiDao(con);

        //最大件数
        int ringiCnt = dao.getRingiDataCount(userSid, paramMdl.getRngProcMode());

        //１ページの最大表示件数
        RngBiz rngBiz = new RngBiz(con);
        int viewCount = rngBiz.getViewCount(con, userSid);

        //ページ調整
        int maxPage = ringiCnt / viewCount;
        if ((ringiCnt % viewCount) > 0) {
            maxPage++;
        }
        int page = paramMdl.getRng010pageTop();
        if (page < 1) {
            page = 1;
        } else if (page > maxPage) {
            page = maxPage;
        }
        paramMdl.setRng010pageTop(page);
        paramMdl.setRng010pageBottom(page);

        //ページコンボ設定
        if (ringiCnt > viewCount) {
            paramMdl.setPageList(PageUtil.createPageOptions(ringiCnt, viewCount));
        }

        //稟議情報一覧設定
        List <RingiDataModel> rngList = dao.getRingiDataList(userSid, paramMdl.getRngProcMode(),
                                            paramMdl.getRng010sortKey(),
                                            paramMdl.getRng010orderKey(),
                                            paramMdl.getRng010pageTop(), viewCount);

        if (paramMdl.getRngProcMode() == RngConst.RNG_MODE_KANRYO) {
            for (int index = 0; index < rngList.size(); index++) {
                if (rngList.get(index).getRngApplicate() == userSid) {
                    rngList.get(index).setDelFlg(1);
                }
            }
        }

        paramMdl.setRngDataList(rngList);

        //基本設定 削除権限
        RngAconfModel aconfMdl = rngBiz.getRngAconf(con);
        paramMdl.setRng010delAuth(aconfMdl.getRarDelAuth());

        log__.debug("End");
    }

    /**
     * <br>[機  能] 稟議の削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param userSid セッションユーザSID
     * @throws Exception 実行例外
     */
    public void deleteRingi(Rng010ParamModel paramMdl, Connection con, int userSid)
    throws Exception {
        log__.debug("START");

        RngBiz rngBiz = new RngBiz(con);
        for (String strRngSid : paramMdl.getRng010DelSidList()) {

            int rngSid = Integer.parseInt(strRngSid);
            rngBiz.deleteRngData(con, rngSid, userSid);
        }

        log__.debug("End");
    }

    /**
     * <br>[機  能] 検索結果の件数を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param procMode 処理モード
     * @param keyword キーワード
     * @param userSid ユーザSID
     * @return 検索結果の件数
     * @throws SQLException SQL実行時例外
     */
    public int getSearchCount(Connection con, int procMode, String keyword, int userSid)
    throws SQLException {

        RingiSearchModel model = new RingiSearchModel();
        model.setKeyword(RngBiz.createKeywordList(keyword));
        model.setKeywordType(RngConst.RNG_SEARCHTYPE_AND);
        model.setTitleSearchFlg(true);
        model.setContentSearchFlg(true);
        model.setUserSid(userSid);

        RingiDao ringiDao = new RingiDao(con);
        return ringiDao.getRingiDataCount(model, procMode);
    }

}
