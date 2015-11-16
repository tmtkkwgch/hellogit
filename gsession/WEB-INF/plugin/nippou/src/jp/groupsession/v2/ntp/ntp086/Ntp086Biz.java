package jp.groupsession.v2.ntp.ntp086;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jp.co.sjts.util.PageUtil;
import jp.co.sjts.util.StringUtil;
import jp.groupsession.v2.cmn.dao.UserSearchDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmInfDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnCmbsortConfModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.ntp.GSConstNippou;
import jp.groupsession.v2.ntp.dao.NtpTemplateSortDao;
import jp.groupsession.v2.ntp.dao.NtpTmpMemberDao;
import jp.groupsession.v2.ntp.model.NtpTemplateModel;
import jp.groupsession.v2.ntp.model.NtpTemplateSortModel;
import jp.groupsession.v2.ntp.model.NtpTmpMemberModel;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 日報 日報テンプレート一覧画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ntp086Biz {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Ntp086Biz.class);
    /** DBコネクション */
    public Connection con__ = null;
    /** リクエスモデル */
    public RequestModel reqMdl__ = null;
    /** 並び順分割文字列 */
    private static final String SORT_SEPARATE = ":";
    /** ユーザポップアップ最大件数 */
    private static final int USR_POP_SIZE = 10;

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     * @param con コネクション
     */
    public Ntp086Biz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Ntp086ParamModel
     * @param con コネクション
     * @throws Exception SQL実行エラー
     */
    public void setInitData(Ntp086ParamModel paramMdl,
            Connection con) throws Exception {

        //目標情報を取得する
        Ntp086Dao templateDao = new Ntp086Dao(con);
        List<NtpTemplateModel> templateList = templateDao.getTemplateList();
        List<Ntp086TemplateDspModel> templateDspList = new ArrayList<Ntp086TemplateDspModel>();
        Ntp086TemplateDspModel templateDspMdl = null;
        int count = 0;

        for (NtpTemplateModel trgMdl : templateList) {
            templateDspMdl = new Ntp086TemplateDspModel();

            templateDspMdl.setTemplateSid(trgMdl.getNttSid());
            templateDspMdl.setTemplateName(trgMdl.getNttName());
            templateDspMdl.setTemplateSort(trgMdl.getNttSort());
            templateDspMdl.setTemplateValue(__getRadioValueStr(trgMdl.getNttSid(), count));
            count++;
            templateDspList.add(templateDspMdl);
        }
        paramMdl.setNtp086TemplateList(templateDspList);

        if (StringUtil.isNullZeroString(paramMdl.getNtp086sortTemplate())
                && templateList.size() > 0) {

            Ntp086TemplateDspModel appspMdl = templateDspList.get(0);
            paramMdl.setNtp086sortTemplate(
                    __getRadioValueStr(appspMdl.getTemplateSid(), 0));
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
    public void updateSort(Connection con, Ntp086ParamModel paramMdl, int userSid, int changeKbn)
            throws SQLException {

        //画面表示全キーリスト取得
        String[] keyList = paramMdl.getNtp086sortLabel();
        if (keyList == null || keyList.length < 1) {
            return;
        }

        //ラジオ選択値の取得
        String selectedKey = paramMdl.getNtp086sortTemplate();

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
        ArrayList<NtpTemplateSortModel> sortModelList
            = new ArrayList<NtpTemplateSortModel>();

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
            NtpTemplateSortModel ntsSortMdl = new NtpTemplateSortModel();
            ntsSortMdl.setNttSid(sortSid);
            ntsSortMdl.setNpsSort(sort);
            sortModelList.add(ntsSortMdl);
        }

        //並び順のデータの削除
        NtpTemplateSortDao sortDao = new NtpTemplateSortDao(con);
        sortDao.delete();

        //新しい並び順を設定
        sortDao.insert(sortModelList);

        //新しいキーを設定
        paramMdl.setNtp086sortTemplate(__getRadioValueStr(sid, target));
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

        String sort = lbSid + SORT_SEPARATE + index;
        return sort;
    }

    /**
     * <br>[機  能] ユーザ一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param nttSid テンプレートSID
     * @param pageNum ページ番号
     * @throws Exception SQL実行時例外
     * @return jsonData jsonコメントリスト
     */
    public JSONObject getJsonUsrList(Connection con,
                            int nttSid, int pageNum) throws Exception {


        NtpTmpMemberDao npmDao = new NtpTmpMemberDao(con);
        CmnUsrmInfDao cmnUsrInfDao = new CmnUsrmInfDao(con);

        JSONObject jsonData = new JSONObject();
        ArrayList<NtpTmpMemberModel> npmList = null;
        ArrayList<Integer> gpSidList = new ArrayList<Integer>();
        ArrayList<Integer> usrSidIntList = new ArrayList<Integer>();
        ArrayList<Integer> gpUsrSidIntList = new ArrayList<Integer>();
        ArrayList<String> usrSidStrList = new ArrayList<String>();
        List<CmnUsrmInfModel> usrInfList = new ArrayList<CmnUsrmInfModel>();
        List<CmnUsrmInfModel> usrInfDataList = new ArrayList<CmnUsrmInfModel>();

        npmList = npmDao.select(nttSid);

        if (pageNum == 0) {
            pageNum = 1;
        }

        if (!npmList.isEmpty()) {
            for (NtpTmpMemberModel nsmMdl : npmList) {

                if (nsmMdl.getGrpSid() != -1) {
                    gpSidList.add(nsmMdl.getGrpSid());
                } else if (nsmMdl.getUsrSid() != -1) {
                    usrSidStrList.add(String.valueOf(nsmMdl.getUsrSid()));
                    usrSidIntList.add(nsmMdl.getUsrSid());
                }
            }

            //ユーザSIDからユーザ情報を取得
            if (!usrSidStrList.isEmpty()) {
                usrInfList = cmnUsrInfDao.select(
                 usrSidStrList.toArray(
                         new String[usrSidStrList.size()]), new CmnCmbsortConfModel());

            }

            //グループSIDからユーザ情報を取得
            if (!gpSidList.isEmpty()) {
                UserSearchDao usearchDao = new UserSearchDao(con);
                gpUsrSidIntList = usearchDao.getBelongUserSids(gpSidList, usrSidIntList);
                if (!gpUsrSidIntList.isEmpty()) {
                    ArrayList<String> usrSidList = new ArrayList<String>();
                    for (int usrSid : gpUsrSidIntList) {
                        usrSidList.add(String.valueOf(usrSid));
                    }
                    usrInfList.addAll(cmnUsrInfDao.select(
                            usrSidList.toArray(
                             new String[usrSidList.size()]), new CmnCmbsortConfModel()));
                }
            }


            //Jsonデータ成形
            if (!usrInfList.isEmpty()) {

                int maxPageSize = PageUtil.getPageCount(usrInfList.size(), USR_POP_SIZE);

                if (pageNum > maxPageSize) {
                    pageNum = maxPageSize;
                }

                int maxSize = pageNum * USR_POP_SIZE;

                Ntp086UsrInfModel ntp086UsrInfMdl = new Ntp086UsrInfModel();
                CmnUsrmInfModel usrInfMdl = null;

                ntp086UsrInfMdl.setMaxPageSize(maxPageSize);
                ntp086UsrInfMdl.setPageNum(pageNum);

                for (int i = (pageNum - 1) * USR_POP_SIZE; i < usrInfList.size(); i++) {
                    if (i < maxSize) {
                        usrInfMdl = usrInfList.get(i);
                        usrInfMdl.setUsiAdate(null);
                        usrInfMdl.setUsiBdate(null);
                        usrInfMdl.setUsiEdate(null);
                        usrInfMdl.setUsiLtlgin(null);
                        usrInfMdl.setUsiEntranceDate(null);
                        usrInfDataList.add(usrInfMdl);
                    } else {
                        break;
                    }
                }

                ntp086UsrInfMdl.setUsrInfDataList(usrInfDataList);

                jsonData = JSONObject.fromObject(ntp086UsrInfMdl);

            }
        }

        return jsonData;

    }
}