package jp.groupsession.v2.ptl.ptl110;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jp.co.sjts.util.StringUtil;
import jp.groupsession.v2.ptl.dao.PtlPortletCategoryDao;
import jp.groupsession.v2.ptl.dao.PtlPortletCategorySortDao;
import jp.groupsession.v2.ptl.model.PtlPortletCategoryModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] ポータル ポートレットカテゴリ一覧画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ptl110Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Ptl110Biz.class);
    /** 順序変更処理区分 順序をあげる */
    public static final int SORT_UP = 0;
    /** 順序変更処理区分 順序を下げる */
    public static final int SORT_DOWN = 1;
    /** 並び替え対象のポートレットカテゴリ セパレート文字列 */
    public static final String SORT_SEPARATE = ":";

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
    public void init(Ptl110ParamModel paramMdl,
                     Connection con,
                     int sessionUserSid,
                     String cmd)
                     throws Exception {

        PtlPortletCategoryDao dao = new PtlPortletCategoryDao(con);
        List<PtlPortletCategoryModel> catList =  new ArrayList<PtlPortletCategoryModel>();
        catList = dao.selectSortAll();
        if (!catList.isEmpty()) {
            //ラジオの文字列作成
            for (PtlPortletCategoryModel model : catList) {
                int sort = model.getPlcSort();
                int sid = model.getPlcSid();
                String strSort = __getRadioValueStr(sid, sort);
                model.setStrPlcSort(strSort);
            }
        }
        paramMdl.setPtl110categoryList(catList);

        //チェックされているラジオがNULLの場合、初期値設定
        if (StringUtil.isNullZeroString(paramMdl.getPtl110sortPltCategory())
            && catList.size() > 0) {
            log__.debug("ラジオチェックなし");

            PtlPortletCategoryModel model = catList.get(0);
            paramMdl.setPtl110sortPltCategory(
                    __getRadioValueStr(model.getPlcSid(), model.getPlcSort()));
        }

    }

    /**
     * <br>[機  能] radioにセットする値(文字列)を取得する
     * <br>[解  説] 「カテゴリSID:表示順」のフォーマットの文字列を返す
     * <br>[備  考]
     * @param catSid カテゴリSID
     * @param catSort 表示順
     * @return String radioにセットする値
     */
    private String __getRadioValueStr(int catSid, int catSort) {

        String sort = catSid + SORT_SEPARATE
                    + catSort;
        return sort;
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
    public void updateSort(Connection con, Ptl110ParamModel paramMdl,
                            int sortKbn, int sessionUserSid)
        throws SQLException {

        //画面表示全キーリスト取得
        String[] keyList = paramMdl.getPtl110categorySort();
        if (keyList == null || keyList.length < 1) {
            return;
        }

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        //画面表示全ラジオの値をマップに格納
        int index = 0;
        for (String strRadio : keyList) {
            String[] sidSort = strRadio.split(SORT_SEPARATE);
            int sid = Integer.parseInt(sidSort[0]);
            map.put(sid, index);
            index++;
        }

        //ラジオ選択値取得
        String strSelectSort = paramMdl.getPtl110sortPltCategory();
        if (StringUtil.isNullZeroString(strSelectSort)) {
            return;
        }

        String[] selectKeyList = strSelectSort.split(SORT_SEPARATE);
        //選択されたポートレットSID
        int selectSid = Integer.parseInt(selectKeyList[0]);
        //選択された画面上の並び順
        int selectSort = map.get(selectSid);

        //画面の最初に表示されている項目 + 順位を上げる
        if (selectSort == 0 && sortKbn == SORT_UP) {
            return;
        //画面の最後に表示されている項目 + 順位を下げる
        } else if (selectSort == keyList.length - 1 && sortKbn == SORT_DOWN) {
            return;
        }

        //選択された項目のカテゴリSID + DBソート順
        int motoSid = Integer.parseInt(selectKeyList[0]);
        int motoSort = Integer.parseInt(selectKeyList[1]);

        int sakiSid = -1;
        int sakiSort = -1;
        int target = selectSort;

        if (sortKbn == SORT_UP) {
            target -= 1;
        } else if (sortKbn == SORT_DOWN) {
            target += 1;
        }

        //画面表示全キーから入れ替え先のデータを探す
        for (String allKey : keyList) {

            String[] allKeyList = allKey.split(SORT_SEPARATE);
            int allKeyDispSid = Integer.parseInt(allKeyList[0]);
            int dspSort = map.get(allKeyDispSid);

            if (dspSort == target) {
                sakiSid = Integer.parseInt(allKeyList[0]);
                sakiSort = Integer.parseInt(allKeyList[1]);
                break;
            }
        }

        if (sakiSid == -1 || sakiSort == -1) {
            return;
        }

        //順序入れ替え
        PtlPortletCategorySortDao dao = new PtlPortletCategorySortDao(con);
        dao.updateSort(motoSid, motoSort, sakiSid, sakiSort, con);

        //新しいキーを設定
        paramMdl.setPtl110sortPltCategory(__getRadioValueStr(motoSid, sakiSort));
    }

}
