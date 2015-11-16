package jp.groupsession.v2.ntp.ntp170;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jp.co.sjts.util.StringUtil;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.ntp.GSConstNippou;
import jp.groupsession.v2.ntp.dao.NtpkatudouBunruiSortDao;
import jp.groupsession.v2.ntp.model.NtpKatudouBunruiSortModel;
import jp.groupsession.v2.ntp.model.NtpKtbunruiModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 日報 活動分類一覧画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ntp170Biz {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Ntp170Biz.class);
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
    public Ntp170Biz(RequestModel reqMdl) {
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
            Ntp170ParamModel paramMdl,
            int sessionUserSid,
            Connection con) throws SQLException {
//        //活動分類一覧の取得・設定
//        Ntp170Dao bunruiDao = new Ntp170Dao(con);
//        paramMdl.setNtp170KtbunruiList(bunruiDao.getKtBunruiList());


        //活動分類情報を取得する
        Ntp170Dao bunruiDao = new Ntp170Dao(con);
        List<NtpKtbunruiModel> ktbunruiList = bunruiDao.getKtBunruiList();
        List<Ntp170KtBunruiDspModel> ktbDspList = new ArrayList<Ntp170KtBunruiDspModel>();
        Ntp170KtBunruiDspModel ktbDspMdl = null;
        int count = 0;

        for (NtpKtbunruiModel ktbMdl : ktbunruiList) {
            ktbDspMdl = new Ntp170KtBunruiDspModel();

            ktbDspMdl.setKtbSid(ktbMdl.getNkbSid());
            ktbDspMdl.setKtbCode(ktbMdl.getNkbCode());
            ktbDspMdl.setKtbName(ktbMdl.getNkbName());
            ktbDspMdl.setKtbSort(ktbMdl.getNksSort());
            ktbDspMdl.setKtbValue(
                    __getRadioValueStr(ktbMdl.getNkbSid(),
                                       count));
            count++;
            ktbDspList.add(ktbDspMdl);
        }
        paramMdl.setNtp170KtbunruiList(ktbDspList);

        //チェックされている並び順がNULLの場合、初期値設定
        if (StringUtil.isNullZeroString(paramMdl.getNtp170sortBunrui())
            && ktbunruiList.size() > 0) {

            Ntp170KtBunruiDspModel addspMdl = ktbDspList.get(0);
            paramMdl.setNtp170sortBunrui(
                    __getRadioValueStr(addspMdl.getKtbSid(), 0));
        }

    }



    /**
     * <br>[機  能] 順序変更処理
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Ntp170ParamModel
     * @param userSid ユーザSID
     * @param changeKbn 処理区分 0:順序をあげる 1:順序を下げる
     * @throws SQLException SQL実行時例外
     */
    public void updateSort(Connection con, Ntp170ParamModel paramMdl, int userSid, int changeKbn)
        throws SQLException {

        //画面表示全キーリスト取得
        String[] keyList = paramMdl.getNtp170sortLabel();
        if (keyList == null || keyList.length < 1) {
            return;
        }

        //ラジオ選択値取得
        String selectedKey = paramMdl.getNtp170sortBunrui();
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
        ArrayList<NtpKatudouBunruiSortModel> sortModelList
                              = new ArrayList<NtpKatudouBunruiSortModel>();

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
            NtpKatudouBunruiSortModel nkbSortMdl = new NtpKatudouBunruiSortModel();
            nkbSortMdl.setNkbSid(sortSid);
            nkbSortMdl.setNksSort(sort);
            sortModelList.add(nkbSortMdl);
        }


        //並び順のデータを削除
        NtpkatudouBunruiSortDao sortDao = new NtpkatudouBunruiSortDao(con);
        sortDao.delete();


        //新しい並び順を設定
        sortDao.insert(sortModelList);


        //新しいキーを設定
        paramMdl.setNtp170sortBunrui(__getRadioValueStr(sid, target));
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
