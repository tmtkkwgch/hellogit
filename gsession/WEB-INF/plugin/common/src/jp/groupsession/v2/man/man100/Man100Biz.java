package jp.groupsession.v2.man.man100;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.groupsession.v2.cmn.dao.base.CmnPositionDao;
import jp.groupsession.v2.cmn.model.base.CmnPositionModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] メイン 管理者設定 役職マネージャー画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man100Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Man100Biz.class);

    /** 並び順分割文字列 */
    private static final String SORT_SEPARATE = "-";

    /** 順序変更処理区分 順序をあげる */
    public static final int SORT_UP = 0;
    /** 順序変更処理区分 順序を下げる */
    public static final int SORT_DOWN = 1;

    /** 処理区分 登録 */
    public static final int MODE_ADD = 0;
    /** 処理区分 編集 */
    public static final int MODE_EDIT = 1;


    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @throws SQLException SQL実行例外
     */
    public void setInitData(Connection con, Man100ParamModel paramMdl) throws SQLException {

        //役職一覧を取得する
        CmnPositionDao cpDao = new CmnPositionDao(con);
        List<CmnPositionModel> cpList = cpDao.getPosList(false);

        //役職情報を画面表示用に加工する
        List<Man100PositionModel> posList = new ArrayList<Man100PositionModel>();
        int count = 0;
        for (CmnPositionModel cpMdl : cpList) {

            String biko = NullDefault.getString(
                    StringUtilHtml.transToHTmlPlusAmparsant(cpMdl.getPosBiko()), "");

            Man100PositionModel posMdl = new Man100PositionModel();
            posMdl.setPosSid(cpMdl.getPosSid());
            posMdl.setPosCode(cpMdl.getPosCode());
            posMdl.setPosValue(
                    __getRadioValueStr(cpMdl.getPosSid(), cpMdl.getPosSort(), count));
            posMdl.setPosName(cpMdl.getPosName());
            posMdl.setPosBiko(biko);
            posList.add(posMdl);
            count++;
        }
        paramMdl.setPosList(posList);


        //チェックされている並び順がNULLの場合、初期値設定
        if (StringUtil.isNullZeroString(paramMdl.getMan100SortRadio())
        && cpList.size() > 0) {
            CmnPositionModel cpMdl = cpList.get(0);
            paramMdl.setMan100SortRadio(
                    __getRadioValueStr(cpMdl.getPosSid(), cpMdl.getPosSort(), 0));
        }
    }

    /**
     * <br>[機  能] 順序変更処理
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param changeKbn 処理区分 0:順序をあげる 1:順序を下げる
     * @throws SQLException SQL実行時例外
     */
    public void updateSort(Connection con, Man100ParamModel paramMdl, int changeKbn)
        throws SQLException {

        //画面表示全キーリスト取得
        String[] keyList = paramMdl.getMan100KeyList();
        if (keyList == null || keyList.length < 1) {
            return;
        }

        //ラジオ選択値取得
        String selectedKey = paramMdl.getMan100SortRadio();
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

        //選択された項目の施設グループSID + ソート順
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
        CmnPositionDao dao = new CmnPositionDao(con);
        dao.updateSort(motoSid, motoSort, sakiSid, sakiSort);

        //新しいキーを設定
        paramMdl.setMan100SortRadio(__getRadioValueStr(motoSid, sakiSort, target));
    }

    /**
     * <br>[機  能] radioにセットする値(文字列)を取得する
     * <br>[解  説] 「役職SID-表示順-画面上の表示順」のフォーマットの文字列を返す
     * <br>[備  考]
     * @param posSid 役職SID
     * @param posSort 表示順
     * @param index 画面上の表示順
     * @return String radioにセットする値
     */
    private String __getRadioValueStr(int posSid, int posSort, int index) {

        String sort = posSid + SORT_SEPARATE
                    + posSort + SORT_SEPARATE
                    + index;
        return sort;
    }

}
