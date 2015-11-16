package jp.groupsession.v2.usr.usr044;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.groupsession.v2.cmn.dao.base.CmnLabelUsrCategoryDao;
import jp.groupsession.v2.cmn.dao.base.CmnLabelUsrDao;
import jp.groupsession.v2.cmn.model.base.CmnLabelUsrCategoryModel;
import jp.groupsession.v2.cmn.model.base.CmnLabelUsrModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] ユーザ情報 ラベル一覧画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Usr044Biz {

    /** 並び順分割文字列 */
    private static final String SORT_SEPARATE = "-";
    /** 順序変更処理区分 順序をあげる */
    public static final int SORT_UP = 0;
    /** 順序変更処理区分 順序を下げる */
    public static final int SORT_DOWN = 1;

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Usr044Biz.class);

    /**
     * <br>[機  能] 初期表示情報を画面にセットする
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Usr044ParamModel
     * @param con コネクション
     * @throws SQLException SQL実行例外
     */
    public void setInitData(Usr044ParamModel paramMdl,
                            Connection con) throws SQLException {
        //ラベル一覧を取得
        CmnLabelUsrDao dao = new CmnLabelUsrDao(con);
        ArrayList<CmnLabelUsrModel> labelList = dao.select(paramMdl.getUsr043EditSid());

        ArrayList<CmnLabelUsrModel> list = new ArrayList<CmnLabelUsrModel>();
        int count = 0;
        //ラベル情報をセット
        for (CmnLabelUsrModel model : labelList) {
            String biko = NullDefault.getString(
                    StringUtilHtml.transToHTmlPlusAmparsant(model.getLabBiko()), "");
            CmnLabelUsrModel usrLabMdl = new CmnLabelUsrModel();

            usrLabMdl.setLabSid(model.getLabSid());
            usrLabMdl.setLabName(model.getLabName());
            usrLabMdl.setLabBiko(biko);
            usrLabMdl.setLucSid(model.getLucSid());
            usrLabMdl.setLauValue(
                    __getRadioValueStr(model.getLabSid(), model.getLabSort(), count));

            list.add(usrLabMdl);
            count++;
        }

        //カテゴリ情報取得
        int editSid = paramMdl.getUsr043EditSid();
        CmnLabelUsrCategoryDao catDao = new CmnLabelUsrCategoryDao(con);
        CmnLabelUsrCategoryModel catMdl = catDao.select(editSid);
        if (catMdl == null) {
            return;
        }
        //カテゴリ情報を画面にセット
        paramMdl.setUsr044CatName(catMdl.getLucName());

        paramMdl.setLabelList(list);

        //チェックされているラジオがNULLの場合、初期値設定
        if (StringUtil.isNullZeroString(paramMdl.getUsr044SortRadio())
        && labelList.size() > 0) {
            CmnLabelUsrModel model = labelList.get(0);
            paramMdl.setUsr044SortRadio(
                    __getRadioValueStr(model.getLabSid(), model.getLabSort(), 0));
        }

    }

    /**
     * <br>[機  能] 順序変更処理
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Usr044ParamModel
     * @param changeKbn 処理区分 0:順序をあげる 1:順序を下げる
     * @throws SQLException SQL実行時例外
     */
    public void updateSort(Connection con, Usr044ParamModel paramMdl, int changeKbn)
        throws SQLException {

        //画面表示全キーリスト取得
        String[] keyList = paramMdl.getUsr044KeyList();
        if (keyList == null || keyList.length < 1) {
            return;
        }

        //ラジオ選択値取得
        String selectedKey = paramMdl.getUsr044SortRadio();
        if (StringUtil.isNullZeroString(selectedKey)) {
            return;
        }

        String[] selectKeyList = selectedKey.split(SORT_SEPARATE);

        //画面表示順
        int selectedKeyDispNum = Integer.parseInt(selectKeyList[2]);
        log__.debug("画面表示順 = " + selectedKeyDispNum);

        //画面の最初に表示されている項目 + 順位を上げる
        if (selectedKeyDispNum == 0 && changeKbn == SORT_UP) {
            return;
        //画面の最後に表示されている項目 + 順位を下げる
        } else if (selectedKeyDispNum == keyList.length - 1
                && changeKbn == SORT_DOWN) {
            return;
        }

        //選択された項目のSID + ソート順
        int motoSid = Integer.parseInt(selectKeyList[0]);
        int motoSort = Integer.parseInt(selectKeyList[1]);

        int sakiSid = -1;
        int sakiSort = -1;
        int target = selectedKeyDispNum;

        if (changeKbn == SORT_UP) {
            target -= 1;
        } else if (changeKbn == SORT_DOWN) {
            target += 1;
        }

        //画面表示全キーから入れ替え先のデータを探す
        for (String allKey : keyList) {

            String[] allKeyList = allKey.split(SORT_SEPARATE);
            int allKeyDispNum = Integer.parseInt(allKeyList[2]);

            if (allKeyDispNum == target) {
                sakiSid = Integer.parseInt(allKeyList[0]);
                sakiSort = Integer.parseInt(allKeyList[1]);
                break;
            }
        }

        if (sakiSid == -1 || sakiSort == -1) {
            return;
        }


        //順序入れ替え
        CmnLabelUsrDao dao = new CmnLabelUsrDao();
        dao.updateSort(motoSid, motoSort, sakiSid, sakiSort, con);

        //新しいキーを設定
        paramMdl.setUsr044SortRadio(__getRadioValueStr(motoSid, sakiSort, target));
    }

    /**
     * <br>[機  能] radioにセットする値(文字列)を取得する
     * <br>[解  説] 「ラベルSID-表示順-画面上の表示順」のフォーマットの文字列を返す
     * <br>[備  考]
     * @param labSid カテゴリSID
     * @param labSort 表示順
     * @param index 画面上の表示順
     * @return String radioにセットする値
     */
    private String __getRadioValueStr(int labSid, int labSort, int index) {

        String sort = labSid + SORT_SEPARATE
                    + labSort + SORT_SEPARATE
                    + index;
        return sort;
    }


}
