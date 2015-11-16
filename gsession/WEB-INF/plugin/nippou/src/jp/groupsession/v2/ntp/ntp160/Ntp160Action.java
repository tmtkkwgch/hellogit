package jp.groupsession.v2.ntp.ntp160;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.ntp.AbstractNippouAdminAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] 日報 プロセス順位一覧画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ntp160Action extends AbstractNippouAdminAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Ntp160Action.class);

    /**
     * <br>[機  能] アクションを実行する
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form ActionForm
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     * @see jp.co.sjts.util.struts.AbstractAction
     * @see #executeAction(org.apache.struts.action.ActionMapping,
     *                      org.apache.struts.action.ActionForm,
     *                      javax.servlet.http.HttpServletRequest,
     *                      javax.servlet.http.HttpServletResponse,
     *                      java.sql.Connection)
     */
    public ActionForward executeAction(ActionMapping map,
                                        ActionForm form,
                                        HttpServletRequest req,
                                        HttpServletResponse res,
                                        Connection con)
        throws Exception {

        ActionForward forward = null;

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        log__.debug("CMD = " + cmd);

        Ntp160Form thisForm = (Ntp160Form) form;

        if (cmd.equals("backNtp160")) {
            forward = map.findForward("ntp120");
        } else if (cmd.equals("up")) {
            forward = __doSortChange(map, thisForm, req, res, con, Ntp160Biz.SORT_UP);
        } else if (cmd.equals("down")) {
            forward = __doSortChange(map, thisForm, req, res, con, Ntp160Biz.SORT_DOWN);
        } else if (cmd.equals("changeGyomu")) {
            thisForm.setNtp160SortRadio(null);
            forward = __doInit(map, thisForm, req, res, con);
        } else {
            log__.debug("初期表示");
            forward = __doInit(map, thisForm, req, res, con);
        }

        return forward;
    }

    /**
     * <br>[機  能] 初期表示処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws Exception 実行時例外
     */
    private ActionForward __doInit(ActionMapping map,
                                    Ntp160Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con) throws Exception {
        int sessionUserSid = this.getSessionUserModel(req).getUsrsid();
        Ntp160Biz biz = new Ntp160Biz(getRequestModel(req));

        Ntp160ParamModel paramMdl = new Ntp160ParamModel();
        paramMdl.setParam(form);
        biz.setInitData(paramMdl, sessionUserSid, con);
        paramMdl.setFormData(form);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 上へ/下へボタンクリック時の処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @param changeKbn 処理区分 0:順序をあげる 1:順序を下げる
     * @throws Exception 実行例外
     * @throws SQLException SQL実行例外
     * @return ActionForward
     */
    private ActionForward __doSortChange(
        ActionMapping map,
        Ntp160Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con,
        int changeKbn) throws SQLException, Exception {

        String selectedKey = form.getNtp160SortRadio();
        if (form.getNtp160NgySid() != -1 && selectedKey != null) {
            log__.debug("*****プロセス並び替え");

            log__.debug("*****選択ラジオ = " + selectedKey);
            ArrayList<String> sortList = new ArrayList<String>();
            for (String sort : form.getNtp160SortKey()) {
                sortList.add(sort);
            }

            int index = sortList.indexOf(selectedKey);
            log__.debug("*****選択ラジオインデックス = " + index);

            if (changeKbn == Ntp160Biz.SORT_UP) {
                //選択されたブックマークの表示順を上げる
                if (index > 0) {
                    sortList.remove(selectedKey);
                    sortList.add(index - 1, selectedKey);
                }
            } else {
                //選択されたブックマークの表示順を下げる
                if (index < sortList.size() - 1) {
                    sortList.remove(selectedKey);
                    sortList.add(index + 1, selectedKey);
                }
            }

            int sessionUserSid = this.getSessionUserModel(req).getUsrsid();
            Ntp160Biz biz = new Ntp160Biz(getRequestModel(req));
            biz.updateProcessSort(con, sortList, sessionUserSid);
        }
        return __doInit(map, form, req, res, con);
    }
}
