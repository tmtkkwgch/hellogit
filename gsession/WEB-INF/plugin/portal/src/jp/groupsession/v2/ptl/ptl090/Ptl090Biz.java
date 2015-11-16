package jp.groupsession.v2.ptl.ptl090;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jp.co.sjts.util.StringUtil;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.ptl.dao.PtlPortletCategoryDao;
import jp.groupsession.v2.ptl.dao.PtlPortletDao;
import jp.groupsession.v2.ptl.dao.PtlPortletSortDao;
import jp.groupsession.v2.ptl.model.PtlPortletCategoryModel;
import jp.groupsession.v2.ptl.model.PtlPortletModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] ポータル ポートレット管理画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ptl090Biz {
    /** 順序変更処理区分 順序をあげる */
    public static final int SORT_UP = 0;
    /** 順序変更処理区分 順序を下げる */
    public static final int SORT_DOWN = 1;
    /** 並び替え対象のポートレットカテゴリ セパレート文字列 */
    public static final String SORT_SEPARATE = ":";

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Ptl090Biz.class);
    /** Connection */
    private Connection con__ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param con Connection
     */
    Ptl090Biz(Connection con) {
        con__ = con;
    }

    /**
     * <br>[機  能] 初期表示処理を行います
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @throws SQLException SQL実行時例外
     */
    public void initDsp(Ptl090ParamModel paramMdl, RequestModel reqMdl) throws SQLException {
        PtlPortletDao dao = new PtlPortletDao(con__);

        //ポートレットを取得する

        ArrayList<PtlPortletModel> list
            = dao.selectInCategory(paramMdl.getPtl090svCategory(), reqMdl);
        if (list == null) {
            log__.debug("ポートレットなし");
            list = new ArrayList<PtlPortletModel>();
        }

        if (!list.isEmpty()) {
            //ラジオの文字列作成
            for (PtlPortletModel model : list) {
                int sid = model.getPltSid();
                int sort = model.getPltSort();
                String strSort = __getRadioValueStr(sid, sort);
                model.setStrPltSort(strSort);
            }
        }
        paramMdl.setPtl090portletlist(list);

        //チェックされているラジオがNULLの場合、初期値設定
        if (StringUtil.isNullZeroString(paramMdl.getPtl090sortPortlet())
            && list.size() > 0) {

            PtlPortletModel model = list.get(0);
            paramMdl.setPtl090sortPortlet(
                    __getRadioValueStr(model.getPltSid(), model.getPltSort()));
        }

        //カテゴリコンボを作成する
        createCategoryComb(paramMdl, reqMdl);
    }

    /**
     * <br>[機  能] 順序変更処理
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param sortKbn 処理区分 0:順序をあげる 1:順序を下げる
     * @param sessionUserSid セッションユーザSID
     * @throws SQLException SQL実行時例外
     */
    public void updateSort(Ptl090ParamModel paramMdl, int sortKbn, int sessionUserSid)
    throws SQLException {

        //画面表示全キーリスト取得
        String[] keyList = paramMdl.getArrayPtl090sortPortlet();
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
        String strSelectSort = paramMdl.getPtl090sortPortlet();
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

        //選択された項目のポートレットSID + DBソート順
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
        PtlPortletSortDao dao = new PtlPortletSortDao(con__);
        dao.updateSort(motoSid, motoSort, sakiSid, sakiSort, con__);

        //新しいキーを設定
        paramMdl.setPtl090sortPortlet(__getRadioValueStr(motoSid, sakiSort));
    }


    /**
     * <br>[機  能] カテゴリのコンボ作成
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @throws SQLException SQL実行時例外
     */
    public void createCategoryComb(Ptl090ParamModel paramMdl, RequestModel reqMdl)
        throws SQLException {

        PtlPortletCategoryDao dao = new PtlPortletCategoryDao(con__);
        ArrayList<LabelValueBean> list = new ArrayList<LabelValueBean>();

        GsMessage gsMsg = new GsMessage(reqMdl);
        String all = gsMsg.getMessage("cmn.all");
        String noCategory = gsMsg.getMessage("cmn.category.no");

        List<PtlPortletCategoryModel> mdlList = dao.selectSortAll();
        //初期値 全て
        list.add(new LabelValueBean(all, "-1"));
        list.add(new LabelValueBean(noCategory, "0"));
        for (PtlPortletCategoryModel model : mdlList) {
            String strName = model.getPlcName();
            String strSid = Integer.toString(model.getPlcSid());
            list.add(new LabelValueBean(strName, strSid));
        }
        paramMdl.setPtl090CategoryList(list);

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

}
