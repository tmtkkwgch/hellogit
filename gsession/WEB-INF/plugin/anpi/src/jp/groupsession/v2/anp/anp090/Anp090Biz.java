package jp.groupsession.v2.anp.anp090;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.StringUtil;
import jp.groupsession.v2.anp.GSConstAnpi;
import jp.groupsession.v2.anp.dao.AnpMtempSortDao;
import jp.groupsession.v2.anp.model.AnpMtempSortModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * <br>[機  能] 管理者設定・メールテンプレート選択画面ビジネスロジック
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Anp090Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Anp090Biz.class);

    /** 並び順分割文字列 */
    private static final String SORT_SEPARATE = ":";

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param anp090Model パラメータモデル
     * @param con DBコネクション
     * @throws Exception 実行例外
     */
    public void setInitData(Anp090ParamModel anp090Model, Connection con)
                throws Exception {
        log__.debug("初期データ取得");


        Anp090Dao dao = new Anp090Dao(con);
        List<Anp090TemplateDspModel> tmpList = dao.getTemplateList();
        List<Anp090TemplateDspModel> escTmpDspList = new ArrayList<Anp090TemplateDspModel>();
        Anp090TemplateDspModel escTmpMdl = null;
        int count = 0;

        //取得したデータより表示順を設定する
        for (Anp090TemplateDspModel mdl : tmpList) {
            escTmpMdl = new Anp090TemplateDspModel();
            escTmpMdl.setTemplateSid(mdl.getTemplateSid());
            escTmpMdl.setTemplateName(mdl.getTemplateName());
            escTmpMdl.setTemplateSort(mdl.getTemplateSort());
            escTmpMdl.setTemplateValue(__getRadioValueStr(mdl.getTemplateSid(), count));
            count++;
            escTmpDspList.add(escTmpMdl);
        }
        anp090Model.setAnp090TempList(escTmpDspList);

        if (StringUtil.isNullZeroString(anp090Model.getAnp090SortTemplate())
                && tmpList.size() > 0) {

            Anp090TemplateDspModel appspMdl = escTmpDspList.get(0);
            anp090Model.setAnp090SortTemplate(
                    __getRadioValueStr(appspMdl.getTemplateSid(), 0));
        }
    }


    /**
     * <br>[機  能] 順序変更処理
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param anp090Model パラメータモデル
     * @param userSid ユーザSID
     * @param changeKbn 処理区分 0:順序をあげる 1:順序を下げる
     * @throws SQLException SQL実行時例外
     */
    public void updateSort(Connection con, Anp090ParamModel anp090Model, int userSid, int changeKbn)
            throws SQLException {

        //画面表示全キーリスト取得
        String[] keyList = anp090Model.getAnp090SortLabel();
        if (keyList == null || keyList.length < 1) {
            return;
        }

        //ラジオ選択値の取得
        String selectedKey = anp090Model.getAnp090SortTemplate();

        String[] selectKeyList = selectedKey.split(SORT_SEPARATE);

        //画面表示順
        int selectedKeyDispNum = Integer.parseInt(selectKeyList[1]);
        log__.debug("画面表示順  = " + selectedKeyDispNum);

        //画面の最初に表示されている項目 + 順位を上げる
        if (selectedKeyDispNum == 0 && changeKbn == GSConstAnpi.SORT_UP) {
            return;
            //画面の最後に表示されている項目 + 順位を下げる
        } else if (selectedKeyDispNum == keyList.length - 1
                && changeKbn == GSConstAnpi.SORT_DOWN) {
            return;
        }

        //選択された項目のSID + ソート順
        int sid = Integer.parseInt(selectKeyList[0]);
        int sort = Integer.parseInt(selectKeyList[1]);
        int target = selectedKeyDispNum;

        if (changeKbn == GSConstAnpi.SORT_UP) {
            target -= 1;
        } else if (changeKbn == GSConstAnpi.SORT_DOWN) {
            target += 1;
        }

        //画面表示全キーで新しいソート順を設定する。
        ArrayList<AnpMtempSortModel> sortModelList = new ArrayList<AnpMtempSortModel>();

        for (String allKey : keyList) {

            String[] allKeyList = allKey.split(SORT_SEPARATE);
            int allKeyDispNum = Integer.parseInt(allKeyList[1]);

            if (sid != Integer.parseInt(allKeyList[0])) {
                if (allKeyDispNum == target) {
                    if (changeKbn == GSConstAnpi.SORT_UP) {
                        sort = Integer.parseInt(allKeyList[1]) + 1;
                    } else if (changeKbn == GSConstAnpi.SORT_DOWN) {
                        sort = Integer.parseInt(allKeyList[1]) - 1;
                    }
                } else {
                    sort = Integer.parseInt(allKeyList[1]);
                }
            } else {
                sort = target;
            }

            int sortSid = Integer.parseInt(allKeyList[0]);
            AnpMtempSortModel anpSortMdl = new AnpMtempSortModel();
            anpSortMdl.setApmSid(sortSid);
            anpSortMdl.setAmsSort(sort);
            sortModelList.add(anpSortMdl);
        }

        //並び順のデータの削除
        AnpMtempSortDao sortDao = new AnpMtempSortDao(con);
        sortDao.delete();

        //新しい並び順を設定
        sortDao.insert(sortModelList);

        //新しいキーを設定
        anp090Model.setAnp090SortTemplate(__getRadioValueStr(sid, target));
    }

    /**
     * <br>[機  能] radioにセットする値(文字列)を取得する
     * <br>[解  説] 「テンプレートSID-表示順-画面上の表示順」のフォーマットの文字列を返す
     * <br>[備  考]
     * @param lbSid テンプレートSID
     * @param index 画面上の表示順
     * @return String radioにセットする値
     */
    private String __getRadioValueStr(int lbSid, int index) {

        String sort = lbSid + SORT_SEPARATE + index;
        return sort;
    }
}