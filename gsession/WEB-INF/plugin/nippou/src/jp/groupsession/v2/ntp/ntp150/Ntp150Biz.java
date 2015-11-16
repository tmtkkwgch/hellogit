package jp.groupsession.v2.ntp.ntp150;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jp.co.sjts.util.StringUtil;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.ntp.GSConstNippou;
import jp.groupsession.v2.ntp.dao.NtpGyomuDao;
import jp.groupsession.v2.ntp.dao.NtpProcessSortDao;
import jp.groupsession.v2.ntp.model.NtpGyomuModel;
import jp.groupsession.v2.ntp.model.NtpProcessModel;
import jp.groupsession.v2.ntp.model.NtpProcessSortModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 日報 プロセス一覧画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ntp150Biz {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Ntp150Biz.class);
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
    public Ntp150Biz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Ntp150ParamModel
     * @param sessionUserSid セッションユーザSID
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setInitData(
            Ntp150ParamModel paramMdl,
            int sessionUserSid,
            Connection con) throws SQLException {

        //業務コンボ設定
        paramMdl.setNtp150GyomuList(getGyomuList(con));

        //活動分類情報を取得する
        Ntp150ProcessDao processDao = new Ntp150ProcessDao(con);
        List<NtpProcessModel> processList;
        if (paramMdl.getNtp150DispNgySid().equals("all")
                || paramMdl.getNtp150DispNgySid().equals("-1")) {
            paramMdl.setNtp150DispNgySid("all");
            processList = processDao.select();
        } else {
            processList = processDao.select(Integer.valueOf(paramMdl.getNtp150DispNgySid()));
        }
        List<Ntp150ProcessModel> proDspList = new ArrayList<Ntp150ProcessModel>();
        Ntp150ProcessModel proDspMdl = null;
        int count = 0;

        for (NtpProcessModel proMdl : processList) {
            proDspMdl = new Ntp150ProcessModel();

            proDspMdl.setNgpSid(proMdl.getNgpSid());
            proDspMdl.setNgySid(proMdl.getNgySid());
            proDspMdl.setNgpCode(proMdl.getNgpCode());
            proDspMdl.setNgpName(proMdl.getNgpName());
            proDspMdl.setNgyName(proMdl.getNgyName());
            proDspMdl.setNgpSort(proMdl.getNgpSort());
            proDspMdl.setNgpValue(
                    __getRadioValueStr(proMdl.getNgpSid(),
                                       count));
            count++;
            proDspList.add(proDspMdl);
        }
        paramMdl.setNtp150ProcessList(proDspList);

        //チェックされている並び順がNULLの場合、初期値設定
        if (StringUtil.isNullZeroString(paramMdl.getNtp150SortProcess())
            && processList.size() > 0) {

            Ntp150ProcessModel addspMdl = proDspList.get(0);
            paramMdl.setNtp150SortProcess(
                    __getRadioValueStr(addspMdl.getNgpSid(), 0));
        }
    }

    /**
     * <br>[機  能] 業務一覧リストを取得
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     * @return 業務一覧リスト
     */
    public List<LabelValueBean> getGyomuList(Connection con) throws SQLException {

        List<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        labelList.add(new LabelValueBean("全件", "all"));

        // 業務一覧を取得
        NtpGyomuDao gyomuDao = new NtpGyomuDao(con);
        List<NtpGyomuModel> gyomuMdl = gyomuDao.select();

        for (NtpGyomuModel mdl : gyomuMdl) {
            labelList.add(
                    new LabelValueBean(mdl.getNgyName(), String.valueOf(mdl.getNgySid())));
        }
        return labelList;
    }

    /**
     * <br>[機  能] 順序変更処理
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Ntp150ParamModel
     * @param userSid ユーザSID
     * @param changeKbn 処理区分 0:順序をあげる 1:順序を下げる
     * @throws SQLException SQL実行時例外
     */
    public void updateSort(Connection con, Ntp150ParamModel paramMdl, int userSid, int changeKbn)
        throws SQLException {

        //画面表示全キーリスト取得
        String[] keyList = paramMdl.getNtp150SortLabel();
        if (keyList == null || keyList.length < 1) {
            return;
        }

        //ラジオ選択値取得
        String selectedKey = paramMdl.getNtp150SortProcess();
        if (StringUtil.isNullZeroString(selectedKey)) {
            return;
        }

        String[] selectKeyList = selectedKey.split(SORT_SEPARATE);

        //画面表示順
        int selectedKeyDispNum = Integer.parseInt(selectKeyList[1]);
        log__.debug("画面表示順 = " + selectedKeyDispNum);

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

        //画面表示全キーで新しいソート順を設定する
        ArrayList<NtpProcessSortModel> sortModelList
                              = new ArrayList<NtpProcessSortModel>();

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
            NtpProcessSortModel proSortMdl = new NtpProcessSortModel();
            proSortMdl.setNgpSid(sortSid);
            proSortMdl.setNgySid(Integer.valueOf(paramMdl.getNtp150DispNgySid()));
            proSortMdl.setNpsSort(sort);
            sortModelList.add(proSortMdl);
        }


        //並び順のデータを削除
        NtpProcessSortDao sortDao = new NtpProcessSortDao(con);
        sortDao.delete(Integer.valueOf(paramMdl.getNtp150DispNgySid()));


        //新しい並び順を設定
        sortDao.insert(sortModelList);


        //新しいキーを設定
        paramMdl.setNtp150SortProcess(__getRadioValueStr(sid, target));
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
