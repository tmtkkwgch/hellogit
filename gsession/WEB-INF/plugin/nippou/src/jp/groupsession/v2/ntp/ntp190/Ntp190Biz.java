package jp.groupsession.v2.ntp.ntp190;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.StringUtil;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.ntp.GSConstNippou;
import jp.groupsession.v2.ntp.dao.NtpContactSortDao;
import jp.groupsession.v2.ntp.model.NtpContactModel;
import jp.groupsession.v2.ntp.model.NtpContactSortModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 日報 顧客源泉一覧画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ntp190Biz {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Ntp190Biz.class);
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
    public Ntp190Biz(RequestModel reqMdl) {
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
            Ntp190ParamModel paramMdl,
            int sessionUserSid,
            Connection con) throws SQLException {

//        //コンタクト一覧の取得・設定
//        NtpContactDao contactDao = new NtpContactDao(con);
//        paramMdl.setNtp190ContactList((ArrayList)contactDao.select());

        //活動方法情報を取得する
        Ntp190Dao contactDao = new Ntp190Dao(con);
        List<NtpContactModel> contactList = contactDao.getKthouhouList();
        List<Ntp190ContactDspModel> contDspList = new ArrayList<Ntp190ContactDspModel>();
        Ntp190ContactDspModel contDspMdl = null;
        int count = 0;

        for (NtpContactModel kthMdl : contactList) {
            contDspMdl = new Ntp190ContactDspModel();

            contDspMdl.setContSid(kthMdl.getNcnSid());
            contDspMdl.setContCode(kthMdl.getNcnCode());
            contDspMdl.setContName(kthMdl.getNcnName());
            contDspMdl.setContSort(kthMdl.getNcsSort());
            contDspMdl.setContValue(__getRadioValueStr(kthMdl.getNcnSid(), count));
            count++;
            contDspList.add(contDspMdl);
        }
        paramMdl.setNtp190ContactList(contDspList);

        if (StringUtil.isNullZeroString(paramMdl.getNtp190sortContact())
                && contactList.size() > 0) {

            Ntp190ContactDspModel appspMdl = contDspList.get(0);
            paramMdl.setNtp190sortContact(
                    __getRadioValueStr(appspMdl.getContSid(), 0));
        }
    }

    /**
     * <br>[機  能] 順序変更処理
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl フォーム
     * @param userSid ユーザSID
     * @param changeKbn 処理区分 0:順序をあげる 1:順序を下げる
     * @throws SQLException SQL実行時例外
     */
    public void updateSort(Connection con, Ntp190ParamModel paramMdl, int userSid, int changeKbn)
            throws SQLException {

        //画面表示全キーリスト取得
        String[] keyList = paramMdl.getNtp190sortLabel();
        if (keyList == null || keyList.length < 1) {
            return;
        }

        //ラジオ選択地の取得
        String selectedKey = paramMdl.getNtp190sortContact();

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
        ArrayList<NtpContactSortModel> sortModelList
            = new ArrayList<NtpContactSortModel>();

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
            NtpContactSortModel nkhSortMdl = new NtpContactSortModel();
            nkhSortMdl.setNcnSid(sortSid);
            nkhSortMdl.setNcsSort(sort);
            sortModelList.add(nkhSortMdl);
        }

        //並び順のデータの削除
        NtpContactSortDao sortDao = new NtpContactSortDao(con);
        sortDao.delete();

        //新しい並び順を設定
        sortDao.insert(sortModelList);

        //新しいキーを設定
        paramMdl.setNtp190sortContact(__getRadioValueStr(sid, target));
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
