package jp.groupsession.v2.adr.adr210;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.groupsession.v2.adr.dao.AdrPositionDao;
import jp.groupsession.v2.adr.model.AdrPositionModel;
import jp.groupsession.v2.cmn.model.RequestModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] アドレス帳 役職一覧画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Adr210Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Adr210Biz.class);
    /** 並び順分割文字列 */
    private static final String SORT_SEPARATE = "-";
    /** 順序変更処理区分 順序をあげる */
    public static final int SORT_UP = 0;
    /** 順序変更処理区分 順序を下げる */
    public static final int SORT_DOWN = 1;
    /** リクエスト */
    protected RequestModel reqMdl_ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     */
    public Adr210Biz(RequestModel reqMdl) {
        reqMdl_ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Adr210ParamModel
     * @throws SQLException SQL実行例外
     */
    public void getInitData(Connection con, Adr210ParamModel paramMdl) throws SQLException {

        //役職一覧を取得する
        AdrPositionDao apDao = new AdrPositionDao(con);
        List<AdrPositionModel> apList = apDao.selectPositionList();

        //役職情報を画面表示用に加工する
        List<Adr210PositionModel> posList = new ArrayList<Adr210PositionModel>();
        int count = 0;
        for (AdrPositionModel apMdl : apList) {

            String biko = NullDefault.getString(
                    StringUtilHtml.transToHTmlPlusAmparsant(apMdl.getApsBiko()), "");

            Adr210PositionModel posMdl = new Adr210PositionModel();
            posMdl.setApsSid(apMdl.getApsSid());
            posMdl.setPosValue(
                    __getRadioValueStr(apMdl.getApsSid(), apMdl.getApsSort(), count));
            posMdl.setApsName(apMdl.getApsName());
            posMdl.setApsBiko(biko);
            posList.add(posMdl);
            count++;
        }
        paramMdl.setPosList(posList);

        //チェックされている並び順がNULLの場合、初期値設定
        if (StringUtil.isNullZeroString(paramMdl.getAdr210SortRadio())
            && apList.size() > 0) {

            AdrPositionModel apMdl = apList.get(0);
            paramMdl.setAdr210SortRadio(
                    __getRadioValueStr(apMdl.getApsSid(), apMdl.getApsSort(), 0));
        }
    }

    /**
     * <br>[機  能] 順序変更処理
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Adr210ParamModel
     * @param changeKbn 処理区分 0:順序をあげる 1:順序を下げる
     * @throws SQLException SQL実行時例外
     */
    public void updateSort(Connection con, Adr210ParamModel paramMdl, int changeKbn)
        throws SQLException {

        //画面表示全キーリスト取得
        String[] keyList = paramMdl.getAdr210KeyList();
        if (keyList == null || keyList.length < 1) {
            return;
        }

        //ラジオ選択値取得
        String selectedKey = paramMdl.getAdr210SortRadio();
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

        //選択された項目の役職SID + ソート順
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
        AdrPositionDao dao = new AdrPositionDao(con);
        dao.updateSort(motoSid, motoSort, sakiSid, sakiSort);

        //新しいキーを設定
        paramMdl.setAdr210SortRadio(__getRadioValueStr(motoSid, sakiSort, target));
    }

    /**
     * <br>[機  能] radioにセットする値(文字列)を取得する
     * <br>[解  説] 「役職SID-表示順-画面上の表示順」のフォーマットの文字列を返す
     * <br>[備  考]
     * @param apsSid 役職SID
     * @param apsSort 表示順
     * @param index 画面上の表示順
     * @return String radioにセットする値
     */
    private String __getRadioValueStr(int apsSid, int apsSort, int index) {

        String sort = apsSid + SORT_SEPARATE
                    + apsSort + SORT_SEPARATE
                    + index;
        return sort;
    }
}