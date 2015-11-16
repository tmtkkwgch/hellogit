package jp.groupsession.v2.ntp.ntp160;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.ntp.dao.NtpGyomuDao;
import jp.groupsession.v2.ntp.dao.NtpProcessDao;
import jp.groupsession.v2.ntp.model.NtpGyomuModel;
import jp.groupsession.v2.ntp.model.NtpProcessModel;
import jp.groupsession.v2.ntp.ntp150.Ntp150ProcessDao;
import jp.groupsession.v2.ntp.ntp150.Ntp150ProcessModel;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 日報 プロセス順位一覧画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ntp160Biz {
    /** 順序変更処理区分 順序をあげる */
    public static final int SORT_UP = 0;
    /** 順序変更処理区分 順序を下げる */
    public static final int SORT_DOWN = 1;
    /** リクエスモデル */
    public RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     */
    public Ntp160Biz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Ntp160ParamModel
     * @param sessionUserSid セッションユーザSID
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setInitData(
            Ntp160ParamModel paramMdl,
            int sessionUserSid,
            Connection con) throws SQLException {

        //業務コンボ設定
        paramMdl.setNtp160GyomuList(__getGyomuList(con));

        if (paramMdl.getNtp160NgySid() == -1) {
            return;
        }
        //プロセス一覧の取得・設定
        Ntp150ProcessDao processDao = new Ntp150ProcessDao(con);

        @SuppressWarnings({"unchecked", "all" })
        ArrayList<Ntp150ProcessModel> labelList
            = (ArrayList) processDao.select(paramMdl.getNtp160NgySid());

        paramMdl.setNtp160ProcessList(labelList);

        //ソートキーの設定
        ArrayList<String> sortLabelList = new ArrayList<String>();

        for (Ntp150ProcessModel mdl : labelList) {
            sortLabelList.add(String.valueOf(mdl.getNgpSid()));
        }
        paramMdl.setNtp160SortKey(sortLabelList.toArray(new String[0]));

        //ラジオ初期値設定
        if (paramMdl.getNtp160SortRadio() == null && labelList.size() > 0) {
            String sid = Integer.toString(labelList.get(0).getNgpSid());
            paramMdl.setNtp160SortRadio(sid);
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
    private List<LabelValueBean> __getGyomuList(Connection con) throws SQLException {

        List<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        labelList.add(new LabelValueBean("選択してください", String.valueOf(-1)));

        // 業務一覧を取得
        NtpGyomuDao processDao = new NtpGyomuDao(con);
        List<NtpGyomuModel> gyomuMdl = processDao.select();

        for (NtpGyomuModel mdl : gyomuMdl) {
            labelList.add(
                    new LabelValueBean(mdl.getNgyName(), String.valueOf(mdl.getNgySid())));
        }
        return labelList;
    }

    /**
     * <br>[機  能] プロセスの並び順の更新を行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param con コネクション
     * @param sidList 表示プロセスSID一覧
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    public void updateProcessSort(Connection con, List<String> sidList
            , int userSid)
    throws SQLException {

        boolean commit = false;
        try {

            NtpProcessDao processDao = new NtpProcessDao(con);
            NtpProcessModel processMdl = __createNtpProcess(userSid);
            int order = 1;
            for (String sid : sidList) {
                processMdl.setNgpSid(Integer.parseInt(sid));
                processMdl.setNgpSort(order++);
                processDao.updateSort(processMdl);
            }

            con.commit();
            commit = true;
        } catch (SQLException e) {
            throw e;
        } finally {
            if (!commit) {
                con.rollback();
            }
        }
    }
    /**
     * <br>[機  能] プロセス情報を作成
     * <br>[解  説]
     * <br>[備  考]
     * @param usrSid ユーザSID
     * @return NtpProcessModel
     */
    private NtpProcessModel __createNtpProcess(int usrSid) {

        UDate nowDate = new UDate();
        NtpProcessModel mdl = new NtpProcessModel();
        mdl.setNgpAuid(usrSid);
        mdl.setNgpAdate(nowDate);
        mdl.setNgpEuid(usrSid);
        mdl.setNgpEdate(nowDate);
        return mdl;
    }
}
