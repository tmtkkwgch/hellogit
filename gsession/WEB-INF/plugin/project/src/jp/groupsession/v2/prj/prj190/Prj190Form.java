package jp.groupsession.v2.prj.prj190;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.prj.GSConstProject;
import jp.groupsession.v2.prj.dao.PrjTodostatusDao;
import jp.groupsession.v2.prj.dao.ProjectSearchDao;
import jp.groupsession.v2.prj.model.PrjTodostatusModel;
import jp.groupsession.v2.prj.model.ProjectItemModel;
import jp.groupsession.v2.prj.model.ProjectSearchModel;
import jp.groupsession.v2.prj.prj080.Prj080Form;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] プロジェクト管理 個人設定 ダッシュボード初期値設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Prj190Form extends Prj080Form {

    /**ダッシュボード初期表示画面 */
    private int prj190DefDsp__ = 0;
    /** ＴＯＤＯ[ダッシュボード] 日付コンボ選択値 */
    private String prj190TodoDay__ = null;
    /** ＴＯＤＯ[ダッシュボード] プロジェクトコンボ選択値 */
    private String prj190TodoPrj__ = null;
    /** ＴＯＤＯ[ダッシュボード] 状態コンボ選択値 */
    private String prj190TodoSts__ = null;
    /** ＴＯＤＯ[ダッシュボード] 日付コンボリスト */
    private List<LabelValueBean> prj190TodoDayList__;
    /** ＴＯＤＯ[ダッシュボード] プロジェクトコンボリスト */
    private List<LabelValueBean> prj190TodoProjectList__;
    /** ＴＯＤＯ[ダッシュボード] 状態コンボリスト */
    private List<LabelValueBean> prj190TodoStsList__;

    /** プロジェクト[ダッシュボード] プロジェクトコンボ選択値 */
    private String prj190Project__ = null;
    /**
     * <p>prj190DefDsp を取得します。
     * @return prj190DefDsp
     */
    public int getPrj190DefDsp() {
        return prj190DefDsp__;
    }
    /**
     * <p>prj190DefDsp をセットします。
     * @param prj190DefDsp prj190DefDsp
     */
    public void setPrj190DefDsp(int prj190DefDsp) {
        prj190DefDsp__ = prj190DefDsp;
    }

    /** プロジェクト[ダッシュボード] プロジェクトコンボリスト */
    private List<LabelValueBean> prj190ProjectList__;

    /**
     * <p>prj190TodoDay を取得します。
     * @return prj190TodoDay
     */
    public String getPrj190TodoDay() {
        return prj190TodoDay__;
    }
    /**
     * <p>prj190TodoDay をセットします。
     * @param prj190TodoDay prj190TodoDay
     */
    public void setPrj190TodoDay(String prj190TodoDay) {
        prj190TodoDay__ = prj190TodoDay;
    }
    /**
     * <p>prj190TodoPrj を取得します。
     * @return prj190TodoPrj
     */
    public String getPrj190TodoPrj() {
        return prj190TodoPrj__;
    }
    /**
     * <p>prj190TodoPrj をセットします。
     * @param prj190TodoPrj prj190TodoPrj
     */
    public void setPrj190TodoPrj(String prj190TodoPrj) {
        prj190TodoPrj__ = prj190TodoPrj;
    }
    /**
     * <p>prj190TodoSts を取得します。
     * @return prj190TodoSts
     */
    public String getPrj190TodoSts() {
        return prj190TodoSts__;
    }
    /**
     * <p>prj190TodoSts をセットします。
     * @param prj190TodoSts prj190TodoSts
     */
    public void setPrj190TodoSts(String prj190TodoSts) {
        prj190TodoSts__ = prj190TodoSts;
    }
    /**
     * <p>prj190TodoDayList を取得します。
     * @return prj190TodoDayList
     */
    public List<LabelValueBean> getPrj190TodoDayList() {
        return prj190TodoDayList__;
    }
    /**
     * <p>prj190TodoDayList をセットします。
     * @param prj190TodoDayList prj190TodoDayList
     */
    public void setPrj190TodoDayList(List<LabelValueBean> prj190TodoDayList) {
        prj190TodoDayList__ = prj190TodoDayList;
    }
    /**
     * <p>prj190TodoProjectList を取得します。
     * @return prj190TodoProjectList
     */
    public List<LabelValueBean> getPrj190TodoProjectList() {
        return prj190TodoProjectList__;
    }
    /**
     * <p>prj190TodoProjectList をセットします。
     * @param prj190TodoProjectList prj190TodoProjectList
     */
    public void setPrj190TodoProjectList(List<LabelValueBean> prj190TodoProjectList) {
        prj190TodoProjectList__ = prj190TodoProjectList;
    }
    /**
     * <p>prj190TodoStsList を取得します。
     * @return prj190TodoStsList
     */
    public List<LabelValueBean> getPrj190TodoStsList() {
        return prj190TodoStsList__;
    }
    /**
     * <p>prj190TodoStsList をセットします。
     * @param prj190TodoStsList prj190TodoStsList
     */
    public void setPrj190TodoStsList(List<LabelValueBean> prj190TodoStsList) {
        prj190TodoStsList__ = prj190TodoStsList;
    }
    /**
     * <p>prj190Project を取得します。
     * @return prj190Project
     */
    public String getPrj190Project() {
        return prj190Project__;
    }
    /**
     * <p>prj190Project をセットします。
     * @param prj190Project prj190Project
     */
    public void setPrj190Project(String prj190Project) {
        prj190Project__ = prj190Project;
    }
    /**
     * <p>prj190ProjectList を取得します。
     * @return prj190ProjectList
     */
    public List<LabelValueBean> getPrj190ProjectList() {
        return prj190ProjectList__;
    }
    /**
     * <p>prj190ProjectList をセットします。
     * @param prj190ProjectList prj190ProjectList
     */
    public void setPrj190ProjectList(List<LabelValueBean> prj190ProjectList) {
        prj190ProjectList__ = prj190ProjectList;
    }

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param con コネクション
     * @param buMdl セッションユーザモデル
     * @param req リクエスト
     * @throws SQLException SQL実行時例外
     * @return エラー
     */
    public ActionErrors validatePrj190(Connection con, BaseUserModel buMdl, HttpServletRequest req)
        throws SQLException {

        ActionErrors errors = new ActionErrors();

       /******************************************************************
        *
        * 選択されているプロジェクトが削除されている
        * or
        * 所属メンバから外された などの場合はエラー
        *
        ******************************************************************/

        ProjectSearchModel bean = new ProjectSearchModel();
        bean.setUserSid(buMdl.getUsrsid());
        bean.setOrder(GSConst.ORDER_KEY_ASC);
        bean.setSort(GSConstProject.SORT_PRJECT_START);
        bean.setEndPrjFlg(true);
        bean.setGetKbn(ProjectSearchModel.GET_BELONG);
        bean.setProjectSid(Integer.parseInt(prj190TodoPrj__));

        GsMessage gsMsg = new GsMessage(req);
        ProjectSearchDao psDao = new ProjectSearchDao(con, gsMsg);
        List<ProjectItemModel> prjlist = psDao.getAllProjectList(bean);
        if (prjlist.isEmpty()) {
            ActionMessage msg =
                new ActionMessage("error.select.project.not.eff");
            StrutsUtil.addMessage(errors, msg, "prj190TodoPrj");
            return errors;
        }

       /******************************************************************
        *
        * 選択されている状態区分が削除されている場合はエラー
        *
        ******************************************************************/

        int todoPrjSid = Integer.parseInt(prj190TodoPrj__);
        int todoStatus = Integer.parseInt(prj190TodoSts__);

        if (todoStatus != GSConstProject.STATUS_ALL
                && todoStatus != GSConstProject.STATUS_YOTEI_AND_MIKAN) {

            if (todoPrjSid > 0) {
                PrjTodostatusDao todoStsDao = new PrjTodostatusDao(con);
                PrjTodostatusModel todoStsMdl =
                    todoStsDao.select(todoPrjSid, todoStatus);

                if (todoStsMdl == null) {
                    ActionMessage msg =
                        new ActionMessage("error.select.status.not.eff");
                    StrutsUtil.addMessage(errors, msg, "prj190TodoSts");
                    return errors;
                }
            } else {
                if (todoStatus != GSConstProject.STATUS_MIKAN
                        && todoStatus == GSConstProject.STATUS_SINKO
                        && todoStatus == GSConstProject.STATUS_KANRYO) {
                    ActionMessage msg =
                        new ActionMessage("error.select.status.not.eff");
                    StrutsUtil.addMessage(errors, msg, "prj190TodoSts");
                    return errors;
                }
            }
        }

       /******************************************************************
        *
        * システム管理者しか選択できないプロジェクト区分のチェック
        *
        ******************************************************************/

        CommonBiz cmnBiz = new CommonBiz();
        boolean adminUser = cmnBiz.isPluginAdmin(con, buMdl, GSConstProject.PLUGIN_ID_PROJECT);

        int prjKbn = Integer.parseInt(prj190Project__);
        if ((prjKbn == GSConstProject.PRJ_NOT_END_ALL
                || prjKbn == GSConstProject.PRJ_END_ALL
                || prjKbn == GSConstProject.PRJ_ALL)
                && !adminUser) {
            ActionMessage msg =
                new ActionMessage("error.select.prjkbn.not.eff");
            StrutsUtil.addMessage(errors, msg, "prj190Project");
        }

        return errors;
    }
}