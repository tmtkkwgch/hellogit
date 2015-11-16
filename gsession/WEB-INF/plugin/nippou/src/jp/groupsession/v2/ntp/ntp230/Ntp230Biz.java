package jp.groupsession.v2.ntp.ntp230;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.StringUtil;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.ntp.GSConstNippou;
import jp.groupsession.v2.ntp.dao.NtpTargetSortDao;
import jp.groupsession.v2.ntp.model.NtpTargetModel;
import jp.groupsession.v2.ntp.model.NtpTargetSortModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 日報 目標一覧画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ntp230Biz {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Ntp230Biz.class);
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
    public Ntp230Biz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Ntp230ParamModel
     * @param sessionUserSid セッションユーザSID
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setInitData(
            Ntp230ParamModel paramMdl,
            int sessionUserSid,
            Connection con) throws SQLException {

        //目標一覧の取得・設定
//        NtpTargetDao targetDao = new NtpTargetDao(con);
//        paramMdl.setNtp230TargetList((ArrayList)targetDao.select());

        //目標情報を取得する
        Ntp230Dao targetDao = new Ntp230Dao(con);
        List<NtpTargetModel> targetList = targetDao.getTargetList();
        List<Ntp230TargetDspModel> targetDspList = new ArrayList<Ntp230TargetDspModel>();
        Ntp230TargetDspModel targetDspMdl = null;
        int count = 0;

        for (NtpTargetModel trgMdl : targetList) {
            targetDspMdl = new Ntp230TargetDspModel();

            targetDspMdl.setTargetSid(trgMdl.getNtgSid());
            targetDspMdl.setTargetName(trgMdl.getNtgName());
            targetDspMdl.setTargetSort(trgMdl.getNtgSort());
            targetDspMdl.setTargetValue(__getRadioValueStr(trgMdl.getNtgSid(), count));
            count++;
            targetDspList.add(targetDspMdl);
        }
        paramMdl.setNtp230TargetList(targetDspList);

        if (StringUtil.isNullZeroString(paramMdl.getNtp230sortTarget())
                && targetList.size() > 0) {

            Ntp230TargetDspModel appspMdl = targetDspList.get(0);
            paramMdl.setNtp230sortTarget(
                    __getRadioValueStr(appspMdl.getTargetSid(), 0));
        }
    }

    /**
     * <br>[機  能] 順序変更処理
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Ntp230ParamModel
     * @param userSid ユーザSID
     * @param changeKbn 処理区分 0:順序をあげる 1:順序を下げる
     * @throws SQLException SQL実行時例外
     */
    public void updateSort(Connection con, Ntp230ParamModel paramMdl, int userSid, int changeKbn)
            throws SQLException {

        //画面表示全キーリスト取得
        String[] keyList = paramMdl.getNtp230sortLabel();
        if (keyList == null || keyList.length < 1) {
            return;
        }

        //ラジオ選択値の取得
        String selectedKey = paramMdl.getNtp230sortTarget();

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
        ArrayList<NtpTargetSortModel> sortModelList
            = new ArrayList<NtpTargetSortModel>();

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
            NtpTargetSortModel ntsSortMdl = new NtpTargetSortModel();
            ntsSortMdl.setNtgSid(sortSid);
            ntsSortMdl.setNtsSort(sort);
            sortModelList.add(ntsSortMdl);
        }

        //並び順のデータの削除
        NtpTargetSortDao sortDao = new NtpTargetSortDao(con);
        sortDao.delete();

        //新しい並び順を設定
        sortDao.insert(sortModelList);

        //新しいキーを設定
        paramMdl.setNtp230sortTarget(__getRadioValueStr(sid, target));
    }

    /**
     * <br>[機  能] radioにセットする値(文字列)を取得する
     * <br>[解  説] 「目標SID-表示順-画面上の表示順」のフォーマットの文字列を返す
     * <br>[備  考]
     * @param lbSid 目標SID
     * @param index 画面上の表示順
     * @return String radioにセットする値
     */
    private String __getRadioValueStr(int lbSid, int index) {

        String sort = lbSid + SORT_SEPARATE
                    + index;
        return sort;
    }
}
