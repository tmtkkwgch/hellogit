package jp.groupsession.v2.ntp.ntp140;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jp.co.sjts.util.StringUtil;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.ntp.GSConstNippou;
import jp.groupsession.v2.ntp.dao.NtpGyomuSortDao;
import jp.groupsession.v2.ntp.model.NtpGyomuModel;
import jp.groupsession.v2.ntp.model.NtpGyomuSortModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 日報 業種一覧画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ntp140Biz {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Ntp140Biz.class);
    /** 並び順分割文字列 */
    private static final String SORT_SEPARATE = ":";

    /** リクエスモデル */
    public RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     */
    public Ntp140Biz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl アクションフォーム
     * @param sessionUserSid セッションユーザSID
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setInitData(
            Ntp140ParamModel paramMdl,
            int sessionUserSid,
            Connection con) throws SQLException {

//        //業務一覧の取得・設定
//        NtpGyomuDao gyomuDao = new NtpGyomuDao(con);
//        paramMdl.setNtp140GyomuList((ArrayList)gyomuDao.select());
        //活動方法情報を取得する
        Ntp140Dao gyomuDao = new Ntp140Dao(con);
        List<NtpGyomuModel> kthouhouList = gyomuDao.getGyoumuList();
        List<Ntp140GyomuDspModel> kthDspList = new ArrayList<Ntp140GyomuDspModel>();
        Ntp140GyomuDspModel kthDspMdl = null;
        int count = 0;

        for (NtpGyomuModel kthMdl : kthouhouList) {
            kthDspMdl = new Ntp140GyomuDspModel();

            kthDspMdl.setGymSid(kthMdl.getNgySid());
            kthDspMdl.setGymCode(kthMdl.getNgyCode());
            kthDspMdl.setGymName(kthMdl.getNgyName());
            kthDspMdl.setGymSort(kthMdl.getNgsSort());
            kthDspMdl.setGymValue(__getRadioValueStr(kthMdl.getNgySid(), count));
            count++;
            kthDspList.add(kthDspMdl);
        }
        paramMdl.setNtp140GyomuList(kthDspList);

        if (StringUtil.isNullZeroString(paramMdl.getNtp140sortGyomu())
                && kthouhouList.size() > 0) {

            Ntp140GyomuDspModel appspMdl = kthDspList.get(0);
            paramMdl.setNtp140sortGyomu(
                    __getRadioValueStr(appspMdl.getGymSid(), 0));
        }

    }

    /**
     * <br>[機  能] 順序変更処理
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Ntp140ParamModel
     * @param userSid ユーザSID
     * @param changeKbn 処理区分 0:順序をあげる 1:順序を下げる
     * @throws SQLException SQL実行時例外
     */
    public void updateSort(Connection con,
                           Ntp140ParamModel paramMdl,
                           int userSid,
                           int changeKbn)
                           throws SQLException {

        //画面表示全キーリスト取得
        String[] keyList = paramMdl.getNtp140sortLabel();
        if (keyList == null || keyList.length < 1) {
            return;
        }

        //ラジオ選択地の取得
        String selectedKey = paramMdl.getNtp140sortGyomu();

        String[] selectKeyList = selectedKey.split(SORT_SEPARATE);

        //画面表示順
        int selectedKeyDispNum = Integer.parseInt(selectKeyList[1]);
        log__.debug("画面表示順  = " + selectedKeyDispNum);

        //画面の最初に表示されている項目 + 順位を上げる
        if (selectedKeyDispNum == 0 && changeKbn == GSConstNippou.SORT_UP) {
            return;
            //画面の最後に表示されている項目 + 順位を下げる
        } else if (selectedKeyDispNum == keyList.length - 1
                && changeKbn == GSConstNippou.SORT_DOWN) {
            return;
        }

        //選択された項目のSID + ソート順
        int sid = Integer.parseInt(selectKeyList[0]);
        int sort = Integer.parseInt(selectKeyList[1]);
        int target = selectedKeyDispNum;

        if (changeKbn == GSConstNippou.SORT_UP) {
            target -= 1;
        } else if (changeKbn == GSConstNippou.SORT_DOWN) {
            target += 1;
        }

        //画面表示全キーで新しいソート順を設定する。
        ArrayList<NtpGyomuSortModel> sortModelList
            = new ArrayList<NtpGyomuSortModel>();

        for (String allKey : keyList) {

            String[] allKeyList = allKey.split(SORT_SEPARATE);
            int allKeyDispNum = Integer.parseInt(allKeyList[1]);

            if (sid != Integer.parseInt(allKeyList[0])) {
                if (allKeyDispNum == target) {
                    if (changeKbn == GSConstNippou.SORT_UP) {
                        sort = Integer.parseInt(allKeyList[1]) + 1;
                    } else if (changeKbn == GSConstNippou.SORT_DOWN) {
                        sort = Integer.parseInt(allKeyList[1]) - 1;
                    }
                } else {
                    sort = Integer.parseInt(allKeyList[1]);
                }
            } else {
                sort = target;
            }

            int sortSid = Integer.parseInt(allKeyList[0]);
            NtpGyomuSortModel nkhSortMdl = new NtpGyomuSortModel();
            nkhSortMdl.setNgySid(sortSid);
            nkhSortMdl.setNgsSort(sort);
            sortModelList.add(nkhSortMdl);
        }

        //並び順のデータの削除
        NtpGyomuSortDao sortDao = new NtpGyomuSortDao(con);
        sortDao.delete();

        //新しい並び順を設定
        sortDao.insert(sortModelList);

        //新しいキーを設定
        paramMdl.setNtp140sortGyomu(__getRadioValueStr(sid, target));
    }

    /**
     * <br>[機  能] radioにセットする値(文字列)を取得する
     * <br>[解  説] 「活動分類SID-表示順-画面上の表示順」のフォーマットの文字列を返す
     * <br>[備  考]
     * @param lbSid 活動分類SID
     * @param index 画面上の表示順
     * @return String radioにセットする値
     */
    private String __getRadioValueStr(int lbSid, int index) {

        String sort = lbSid + SORT_SEPARATE
                    + index;
        return sort;
    }
}
