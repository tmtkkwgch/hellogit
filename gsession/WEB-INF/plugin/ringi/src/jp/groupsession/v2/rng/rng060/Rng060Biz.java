package jp.groupsession.v2.rng.rng060;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.rng.dao.RngTemplateCategoryDao;
import jp.groupsession.v2.rng.dao.RngTemplateDao;
import jp.groupsession.v2.rng.model.RngTemplateCategoryModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 稟議 内容テンプレート選択画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng060Biz {
    /** テンプレート種別 共有 */
    public static final int TEMPLATETYPE_COMMON = 0;
    /** テンプレート種別 個人 */
    public static final int TEMPLATETYPE_PERSONAL = 1;
    /** 順序変更処理区分 順序をあげる */
    public static final int SORT_UP = 0;
    /** 順序変更処理区分 順序を下げる */
    public static final int SORT_DOWN = 1;

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rng060Biz.class);
    /** Connection */
    private Connection con__ = null;
    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * @param con Connection
     * @param reqMdl リクエスト情報
     */
    Rng060Biz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示処理を行います
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param userSid ユーザSID
     * @throws SQLException SQL実行時例外
     */
    public void initDsp(Rng060ParamModel paramMdl, int userSid) throws SQLException {
        RngTemplateDao dao = new RngTemplateDao(con__);

        int tplMode = paramMdl.getRngTemplateMode();
        //もし、tplModeが「全て」であるならば、
        if (tplMode == RngConst.RNG_TEMPLATE_ALL) {
            //共有・個人のテンプレートを取得する
            paramMdl.setRng060tplListShare(dao.selectTplList(RngConst.RNG_TEMPLATE_SHARE,
                                                            userSid,
                                                            paramMdl.getRng060SelectCat(),
                                                            reqMdl__));
            paramMdl.setRng060tplListPrivate(
                    dao.selectTplList(RngConst.RNG_TEMPLATE_PRIVATE,
                            userSid,
                            paramMdl.getRng060SelectCatUsr(), reqMdl__));

            //共有のカテゴリを取得する
            createCategoryComb(con__, paramMdl, userSid);

            //個人のカテゴリを取得する
            createCategoryComb(con__, paramMdl, userSid);

        //ではなく、tplModeが「共有」であるならば、
        } else if (tplMode == RngConst.RNG_TEMPLATE_SHARE) {
            //共有のテンプレートを取得する
            paramMdl.setRng060tplListShare(
                    dao.selectTplList(RngConst.RNG_TEMPLATE_SHARE,
                                      userSid,
                                      paramMdl.getRng060SelectCat(), reqMdl__));

            //共有のカテゴリを取得する
            createCategoryComb(con__, paramMdl, userSid);

        //ではなく、tplModeが「個人」であるならば、
        } else if (tplMode == RngConst.RNG_TEMPLATE_PRIVATE) {
            //個人のテンプレートを取得する
            paramMdl.setRng060tplListPrivate(
                    dao.selectTplList(RngConst.RNG_TEMPLATE_PRIVATE,
                                      userSid,
                                      paramMdl.getRng060SelectCatUsr(),
                                      reqMdl__));

            //個人のカテゴリを取得する
            createCategoryComb(con__, paramMdl, userSid);

        } else {
            log__.debug("テンプレート情報はありません。");
        }

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
    public void updateSort(Connection con, Rng060ParamModel paramMdl,
                            int sortKbn, int sessionUserSid)
        throws SQLException {

        UDate now = new UDate();

        //ラジオ選択値取得
        int tplMode = paramMdl.getRng060TemplateMode();
        String sortRtpSid = paramMdl.getRng060SortRadio();
        if (tplMode == RngConst.RNG_TEMPLATE_PRIVATE) {
            sortRtpSid = paramMdl.getRng060SortRadioPrivate();
        }
        if (StringUtil.isNullZeroString(sortRtpSid)) {
            return;
        }

        RngTemplateDao dao = new RngTemplateDao(con__);

        //画面表示順
        int rtpSid = Integer.parseInt(sortRtpSid);
        int selectSort = dao.getSort(rtpSid);

        int catSid = dao.select(rtpSid).getRtcSid();
        if (tplMode == RngConst.RNG_TEMPLATE_PRIVATE) {
            catSid = paramMdl.getRng060SelectCatUsr();
        }
        //ソート順が設定されていない場合は全てのソート順を登録日順に更新する
        if (selectSort == 0) {
            dao.updateSortAll(tplMode, sessionUserSid, now, catSid);
            selectSort = dao.getSort(rtpSid);
        }
        //同じソート順のテンプレートが存在した場合は全てのソート順を登録日順に更新する
        int cnt = dao.getSortCount(catSid, selectSort, tplMode);
        if (cnt > 1) {
            dao.updateSortAll(tplMode, sessionUserSid, now, catSid);
            selectSort = dao.getSort(rtpSid);
        }
        Map<Integer, Integer> tplSortMap = dao.getTplSortMap(tplMode, sessionUserSid, catSid);
        int min = dao.getMinSort(tplMode, sessionUserSid, catSid);
        int max = dao.getMaxSort(tplMode, sessionUserSid, catSid);
        if ((selectSort == min && sortKbn == SORT_UP)
        || (selectSort == max && sortKbn == SORT_DOWN)) {
            return;
        }

        int newSort = selectSort;
        if (sortKbn == SORT_UP) {
            newSort--;
        } else if (sortKbn == SORT_DOWN) {
            newSort++;
        }


        //ソート順
        int editRtpSid = getUpdateSid(newSort, tplSortMap, sortKbn);

        //順序の入れ替えを行う
        dao.updateSort(editRtpSid, selectSort, sessionUserSid, now);
        dao.updateSort(rtpSid, newSort, sessionUserSid, now);
    }

    /**
     * <br>[機  能] カテゴリのコンボ作成
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param sessionUserSid セッションユーザSID
     * @throws SQLException SQL実行時例外
     */
    public void createCategoryComb(Connection con, Rng060ParamModel paramMdl, int sessionUserSid)
        throws SQLException {

        RngTemplateCategoryDao dao = new RngTemplateCategoryDao(con__);
        ArrayList<LabelValueBean> list = new ArrayList<LabelValueBean>();
        ArrayList<LabelValueBean> list2 = new ArrayList<LabelValueBean>();
        ArrayList<RngTemplateCategoryModel> categoryList =
            new ArrayList<RngTemplateCategoryModel>();
        ArrayList<RngTemplateCategoryModel> categoryListUsr =
            new ArrayList<RngTemplateCategoryModel>();
        int tplMode = paramMdl.getRngTemplateMode();

        GsMessage gsMsg = new GsMessage(reqMdl__);
        String all = gsMsg.getMessage("cmn.all");
        String noCategory = gsMsg.getMessage("cmn.category.no");

        //個人のカテゴリ
        if (tplMode == RngConst.RNG_TEMPLATE_PRIVATE) {
            categoryListUsr = dao.selectUser(sessionUserSid);

            //初期値 全て
            list.add(new LabelValueBean(all, "-1"));
            list.add(new LabelValueBean(noCategory, "0"));
            for (RngTemplateCategoryModel model : categoryListUsr) {
                String strName = model.getRtcName();
                String strSid = Integer.toString(model.getRtcSid());
                list.add(new LabelValueBean(strName, strSid));
            }
            paramMdl.setRng060CategoryListPrivate(list);

        //共有のカテゴリ
        } else if (tplMode == RngConst.RNG_TEMPLATE_SHARE) {
            categoryList = dao.selectAdmin();

            //初期値 全て
            list2.add(new LabelValueBean(all, "-1"));
            list2.add(new LabelValueBean(noCategory, "0"));
            for (RngTemplateCategoryModel model : categoryList) {
                String strName = model.getRtcName();
                String strSid = Integer.toString(model.getRtcSid());
                list2.add(new LabelValueBean(strName, strSid));
            }
            paramMdl.setRng060CategoryList(list2);

        //全て
        } else if (tplMode == RngConst.RNG_TEMPLATE_ALL) {
            categoryListUsr = dao.selectUser(sessionUserSid);
            categoryList = dao.selectAdmin();

            //初期値 全て
            list.add(new LabelValueBean(all, "-1"));
            list.add(new LabelValueBean(noCategory, "0"));
            for (RngTemplateCategoryModel model : categoryListUsr) {
                String strName = model.getRtcName();
                String strSid = Integer.toString(model.getRtcSid());
                list.add(new LabelValueBean(strName, strSid));
            }
            //初期値 全て
            list2.add(new LabelValueBean(all, "-1"));
            list2.add(new LabelValueBean(noCategory, "0"));
            for (RngTemplateCategoryModel model : categoryList) {
                String strName = model.getRtcName();
                String strSid = Integer.toString(model.getRtcSid());
                list2.add(new LabelValueBean(strName, strSid));
            }
            paramMdl.setRng060CategoryListPrivate(list);
            paramMdl.setRng060CategoryList(list2);
        }
    }

    /**
     * <br>[機  能] MapのvalueのテンプレートSIDを取得
     * <br>[解  説]
     * <br>[備  考]
     * @param newSort 指定ソート順
     * @param tplSortMap テンプレートSIDとソート順のMapping
     * @param sortKbn 上げるか下げるか
     * @return int SID
     */
    public int getUpdateSid(int newSort, Map<Integer, Integer> tplSortMap, int sortKbn) {
        int sid = 0;
        while (tplSortMap.get(newSort) == null) {
            if (sortKbn == SORT_UP) {
                newSort--;
            } else if (sortKbn == SORT_DOWN) {
                newSort++;
            }
        }
        sid = tplSortMap.get(newSort);
        return sid;
    }
}
