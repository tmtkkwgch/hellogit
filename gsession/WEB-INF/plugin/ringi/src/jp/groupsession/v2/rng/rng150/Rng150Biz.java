package jp.groupsession.v2.rng.rng150;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.rng.dao.RngTemplateCategoryDao;
import jp.groupsession.v2.rng.model.RngTemplateCategoryModel;

/**
 * <br>[機  能] 稟議 テンプレートカテゴリ選択画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng150Biz {
    /** 順序変更処理区分 順序をあげる */
    public static final int SORT_UP = 0;
    /** 順序変更処理区分 順序を下げる */
    public static final int SORT_DOWN = 1;

    /**
     * <br>[機  能] 初期表示処理を行います
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param sessionUserSid セッションユーザSID
     * @param cmd コマンド
     * @throws Exception 例外
     */
    public void init(Rng150ParamModel paramMdl,
                     Connection con,
                     int sessionUserSid,
                     String cmd)
                     throws Exception {

        RngTemplateCategoryDao dao = new RngTemplateCategoryDao(con);
        ArrayList<RngTemplateCategoryModel> catList =  new ArrayList<RngTemplateCategoryModel>();
        int tplMode = paramMdl.getRngTemplateMode();
        if (tplMode == RngConst.RNG_TEMPLATE_SHARE) {
            catList = dao.selectAdmin();
        } else if (tplMode == RngConst.RNG_TEMPLATE_PRIVATE) {
            catList = dao.selectUser(sessionUserSid);
        }
        paramMdl.setRng150CatList(catList);

    }

    /**
     * <br>[機  能] 順序変更処理
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param sortKbn 処理区分 0:順序をあげる 1:順序を下げる
     * @param sessionUserSid セッションユーザSID
     * @throws SQLException SQL実行時例外
     */
    public void updateSort(Connection con, Rng150ParamModel paramMdl,
                            int sortKbn, int sessionUserSid)
        throws SQLException {

        UDate now = new UDate();

        //ラジオ選択値取得
        int tplMode = paramMdl.getRngTemplateMode();
        String sortRtpSid = paramMdl.getRng150SortRadio();
        if (StringUtil.isNullZeroString(sortRtpSid)) {
            return;
        }

        RngTemplateCategoryDao dao = new RngTemplateCategoryDao(con);

        //画面表示順
        int rtcSid = Integer.parseInt(sortRtpSid);
        int selectSort = dao.getSort(rtcSid);

        Map<Integer, Integer> tplSortMap = dao.getTplSortMap(tplMode, sessionUserSid);
        if ((selectSort == 1 && sortKbn == SORT_UP)
        || (selectSort == tplSortMap.size() && sortKbn == SORT_DOWN)) {
            return;
        }

        int newSort = selectSort;
        if (sortKbn == SORT_UP) {
            newSort--;
        } else if (sortKbn == SORT_DOWN) {
            newSort++;
        }
        int editRtcSid = tplSortMap.get(new Integer(newSort)).intValue();

        //順序の入れ替えを行う
        dao.updateSort(editRtcSid, selectSort, sessionUserSid, now);
        dao.updateSort(rtcSid, newSort, sessionUserSid, now);
    }

}
