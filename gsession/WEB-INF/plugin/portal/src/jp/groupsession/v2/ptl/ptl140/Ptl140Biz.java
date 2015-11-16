package jp.groupsession.v2.ptl.ptl140;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.groupsession.v2.man.GSConstPortal;
import jp.groupsession.v2.man.dao.base.PtlAdmConfDao;
import jp.groupsession.v2.man.dao.base.PtlPortalDao;
import jp.groupsession.v2.man.dao.base.PtlPortalSortDao;
import jp.groupsession.v2.man.dao.base.PtlUconfDao;
import jp.groupsession.v2.man.model.base.PtlAdmConfModel;
import jp.groupsession.v2.man.model.base.PtlPortalModel;
import jp.groupsession.v2.man.model.base.PtlPortalSortModel;
import jp.groupsession.v2.man.model.base.PtlUconfModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] ポータル 個人設定 ポータル管理画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ptl140Biz {

    /** 順序変更処理区分 順序をあげる */
    public static final int SORT_UP = 0;
    /** 順序変更処理区分 順序を下げる */
    public static final int SORT_DOWN = 1;
    /** 並び替え対象のポータル セパレート文字列 */
    public static final String SORT_SEPARATE = ":";

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Ptl140Biz.class);

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     */
    public Ptl140Biz() {
    }

    /**
     * <br>[機  能] 初期表示処理を行います
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param usrSid ユーザSID
     * @throws SQLException SQL実行時例外
     */
    public void initDsp(Connection con, Ptl140ParamModel paramMdl, int usrSid)
    throws SQLException {
        PtlPortalDao dao = new PtlPortalDao(con);

        //ポータルを取得する
        ArrayList<PtlPortalModel> list = dao.selectForUser(usrSid);
        if (list == null) {
            list = new ArrayList<PtlPortalModel>();
        } else {
            //ポータル個人並び順のレコードがあるか判定
            PtlPortalSortDao sortDao = new PtlPortalSortDao(con);
            ArrayList<PtlPortalSortModel> sortList = new ArrayList<PtlPortalSortModel>();
            sortList = sortDao.select(usrSid);

            //ポータルの個人並び順新規作成
            if (sortList.isEmpty() || sortList.size() != list.size()) {
                log__.debug("個人並び順なし");
                int sort = 1;

                //一度並び順を削除してから登録
                sortDao.deleteSortForUser(usrSid);
                for (PtlPortalModel ptlMdl : list) {
                    boolean commit = false;
                    try {
                        PtlPortalSortModel mdl = new PtlPortalSortModel();
                        mdl.setPtlSid(ptlMdl.getPtlSid());
                        mdl.setPtsKbn(GSConstPortal.PTS_KBN_USER);
                        mdl.setPtsSort(sort);
                        mdl.setUsrSid(usrSid);
                        sortDao.insert(mdl);
                        ptlMdl.setPtsSort(sort);
                        sort++;
                        con.commit();
                        commit = true;
                    } catch (SQLException e) {
                        log__.error("ポータル登録処理エラー", e);
                        throw e;
                    } finally {
                        if (!commit) {
                            con.rollback();
                        }
                    }
                }
            }
        }

        for (PtlPortalModel model : list) {
            //html表示用に整形
            String dspSetumei = StringUtilHtml.transToHTml(model.getPtlDescription());
            //整形したものを入れなおす
            model.setPtlDescription(dspSetumei);
        }
        if (!list.isEmpty()) {
            //ラジオの文字列作成
            for (PtlPortalModel model : list) {
                int sid = model.getPtlSid();
                int sort = model.getPtsSort();
                String strSort = __getRadioValueStr(sid, sort);
                model.setStrPtsSort(strSort);
            }
        }
        paramMdl.setPtl140portalList(list);

        //チェックされているラジオがNULLの場合、初期値設定
        if (StringUtil.isNullZeroString(paramMdl.getPtl140sortPortal())
            && list.size() > 0) {

            PtlPortalModel model = list.get(0);
            paramMdl.setPtl140sortPortal(
                    __getRadioValueStr(model.getPtlSid(), model.getPtsSort()));
        }

        //ポータル初期表示権限
        PtlAdmConfDao aConfDao = new PtlAdmConfDao(con);
        PtlAdmConfModel aConfModel = aConfDao.select();
        if (aConfModel == null) {
            aConfModel = new PtlAdmConfModel();
        }
        int pow = aConfModel.getPacDefKbn();
        paramMdl.setPtlDefPow(pow);
        //ポータルの表示順変更権限
        paramMdl.setPtlSortPow(aConfModel.getPacPtlEditkbn());

        //ポータルの初期表示取得
        PtlUconfModel uconfMdl = new PtlUconfModel();
        PtlUconfDao uconfDao = new PtlUconfDao(con);
        uconfMdl = uconfDao.select(usrSid);
        if (uconfMdl == null) {
            uconfMdl = new PtlUconfModel();
        }
        paramMdl.setPtlType(uconfMdl.getPucDefType());
        

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
    public void updateSort(Connection con, Ptl140ParamModel paramMdl, int sortKbn,
                        int sessionUserSid)
        throws SQLException {

        //画面表示全キーリスト取得
        String[] keyList = paramMdl.getArrayPtl140sortPortal();
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
        String strSelectSort = paramMdl.getPtl140sortPortal();
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
        PtlPortalSortDao dao = new PtlPortalSortDao(con);
        dao.updateSort(motoSid, motoSort, sakiSid, sakiSort, sessionUserSid);

        //新しいキーを設定
        paramMdl.setPtl140sortPortal(__getRadioValueStr(motoSid, sakiSort));
    }

    /**
     * <br>[機  能] radioにセットする値(文字列)を取得する
     * <br>[解  説] 「ポータルSID:表示順」のフォーマットの文字列を返す
     * <br>[備  考]
     * @param ptlSid ポータルSID
     * @param ptlSort 表示順
     * @return String radioにセットする値
     */
    private String __getRadioValueStr(int ptlSid, int ptlSort) {

        String sort = ptlSid + SORT_SEPARATE
                    + ptlSort;
        return sort;
    }

    /**
     * <br>[機  能] ポータルデフォルト表示区分変更
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param sessionUserSid セッションユーザSID
     * @throws SQLException SQL実行時例外
     */
    public void changeKbn(Connection con, Ptl140ParamModel paramMdl, int sessionUserSid)
        throws SQLException {

        PtlUconfDao uconfDao = new PtlUconfDao(con);
        PtlUconfModel uconfMdl = new PtlUconfModel();
        uconfMdl.setUsrSid(sessionUserSid);
        uconfMdl.setPucDefType(paramMdl.getPtlType());

        //更新処理
        int count = uconfDao.update(uconfMdl);
        //更新件数が0なら登録処理
        if (count < 1) {
            uconfDao.insert(uconfMdl);
        }
    }
}
