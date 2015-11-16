package jp.groupsession.v2.cmn.cmn120;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.io.ObjectFile;
import jp.co.sjts.util.json.JSONObject;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.struts.AbstractGsAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * <br>[機  能] ユーザ選択画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考] 各プラグインで使用されるユーザ選択機能です。
 *
 * @author JTS
 */
public class Cmn120Action extends AbstractGsAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Cmn120Action.class);
    /** 呼び出し元フォームパラメータ保存ファイル名 */
    private static final String FORM_FILENAME = "cmn120Modelfile";

    /**
     * <br>[機  能] アクション実行
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行例外
     * @return ActionForward
     */
    public ActionForward executeAction(
        ActionMapping map,
        ActionForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        log__.debug("START_Cmn120");
        ActionForward forward = null;

        Cmn120Form thisForm = (Cmn120Form) form;

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter(GSConst.P_CMD), "");
        cmd = cmd.trim();
        if (cmd.equals("setuser")) {
            log__.debug("OKボタン押下");
            forward = __doOk(map, thisForm, req, res, con);

        } else if (cmd.equals("batoTo")) {
            log__.debug("戻るボタン押下");
            forward = __doBack(map, thisForm, req, res);

        } else if (cmd.equals("changeGrp")) {
            log__.debug("グループコンボ変更");
            forward = __doInit(map, thisForm, req, res, con);

        } else if (cmd.equals("addMyGrpUser")) {
            log__.debug("～に追加↓ボタン押下");
            forward = __doAddMyGrpUser(map, thisForm, req, res, con);

        } else if (cmd.equals("removeUser")) {
            log__.debug("削除(左矢印)ボタン押下");
            forward = __doLeft(map, thisForm, req, res, con);

        } else if (cmd.equals("addUser")) {
            log__.debug("追加(右矢印)ボタン押下");
            forward = __doRight(map, thisForm, req, res, con);

        } else if (cmd.equals("movePage")) {
            log__.debug("呼び出し元画面へ遷移");
            String url = thisForm.getCmn120BackUrl();
            CommonBiz cmnBiz = new CommonBiz();
            if (!setFileEdit(getTempPath(req), thisForm, req)
                    || !cmnBiz.isCheckRedirectUrl(url)) {
                log__.info("不正なアクセス");
                return getSubmitErrorPage(map, req);
            }
            forward = new ActionForward(url);
        } else if (cmd.equals("getInitData")) {
            log__.debug("初期データ取得");
            con.setAutoCommit(true);
            GroupBiz grpBiz = new GroupBiz();
            thisForm.setCmn120groupSid(
                    String.valueOf(grpBiz.getDefaultGroupSid(
                            Cmn120Biz.getUserSid(getRequestModel(req)), con)));
            thisForm.setCmn120userSidOld(thisForm.getCmn120userSid());
            thisForm.setCmn120FunctionName(
                    NullDefault.getString(thisForm.getCmn120FunctionName(), ""));
            //saveFormData(getTempPath(req), thisForm, req);
            con.setAutoCommit(false);
            __getInitData(map, thisForm, req, res, con);
        } else if (cmd.equals("changeGrpData")) {
            log__.debug("グループコンボ変更");
            __getInitData(map, thisForm, req, res, con);
        } else if (cmd.equals("changeTabData")) {
            log__.debug("タブ変更");
            __getInitData(map, thisForm, req, res, con);
        } else if (cmd.equals("removeUserData")) {
            log__.debug("削除(左矢印)ボタン押下");
            __getLeftData(map, thisForm, req, res, con);

        } else if (cmd.equals("addUserData")) {
            log__.debug("追加(右矢印)ボタン押下");
            __getRightData(map, thisForm, req, res, con);
        } else if (cmd.equals("addMyGrpUserData")) {
            log__.debug("～に追加↓ボタン押下");
            __getAddMyGrpUserData(map, thisForm, req, res, con);
        } else {
            log__.debug("初期表示");

            con.setAutoCommit(true);
            GroupBiz grpBiz = new GroupBiz();
            thisForm.setCmn120groupSid(
                    String.valueOf(grpBiz.getDefaultGroupSid(
                            Cmn120Biz.getUserSid(getRequestModel(req)), con)));
            thisForm.setCmn120userSidOld(thisForm.getCmn120userSid());
            thisForm.setCmn120FunctionName(
                    NullDefault.getString(thisForm.getCmn120FunctionName(), ""));
            saveFormData(getTempPath(req), thisForm, req);
            con.setAutoCommit(false);

            forward = __doInit(map, thisForm, req, res, con);
        }

        log__.debug("END_Cmn120");
        return forward;
    }

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @return ActionForward
     */
    private ActionForward __doInit(
        ActionMapping map,
        Cmn120Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException {

        int userSid = Cmn120Biz.getUserSid(getRequestModel(req));

        con.setAutoCommit(true);
        Cmn120Biz biz = new Cmn120Biz();
        Cmn120ParamModel paramModel = new Cmn120ParamModel();
        paramModel.setParam(form);
        biz.setInitData(paramModel, con, getRequestModel(req), userSid);
        paramModel.setFormData(form);
        con.setAutoCommit(false);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] OKボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws SQLException SQL実行例外
     * @throws IOException 呼び出し元画面のフォームパラメータの保存に失敗
     * @throws IOToolsException 呼び出し元画面のフォームパラメータの保存に失敗
     */
    private ActionForward __doOk(
        ActionMapping map,
        Cmn120Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, IOException, IOToolsException {

        //追加済みユーザが選択されているかを確認する
        String[] userList = form.getCmn120userSid();
        if (userList == null || userList.length <= 0) {
            ActionErrors errors = new ActionErrors();
            ActionMessage msg
                = new ActionMessage("error.select.required.text", form.getCmn120FunctionName());
            StrutsUtil.addMessage(errors, msg, "error.select.required.text");
            this.addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }

        form.setCmn120MovePage(1);
        return map.getInputForward();
    }

    /**
     * <br>[機  能] 戻るボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @return ActionForward
     * @throws IOException 呼び出し元画面のフォームパラメータ復元に失敗
     * @throws IOToolsException 呼び出し元画面のフォームパラメータ復元に失敗
     */
    private ActionForward __doBack(
        ActionMapping map,
        Cmn120Form form,
        HttpServletRequest req,
        HttpServletResponse res) throws IOException, IOToolsException {

        form.setCmn120userSid(form.getCmn120userSidOld());
        req.setAttribute("cmn120Form", form);

        form.setCmn120MovePage(1);
        return map.getInputForward();
    }

    /**
     * <br>[機  能] 削除(左矢印)ボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @return ActionForward
     */
    private ActionForward __doLeft(
        ActionMapping map,
        Cmn120Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException {

        CommonBiz cmnBiz = new CommonBiz();
        form.setCmn120userSid(
                cmnBiz.getDeleteMember(form.getCmn120SelectRightUser(), form.getCmn120userSid()));

        return __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 追加(右矢印)ボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @return ActionForward
     */
    private ActionForward __doRight(
        ActionMapping map,
        Cmn120Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException {

        CommonBiz cmnBiz = new CommonBiz();
        form.setCmn120userSid(
                cmnBiz.getAddMember(form.getCmn120SelectLeftUser(), form.getCmn120userSid()));

        return __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] ～に追加↓ボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @return ActionForward
     */
    private ActionForward __doAddMyGrpUser(
        ActionMapping map,
        Cmn120Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException {

        con.setAutoCommit(true);
        Cmn120Biz biz = new Cmn120Biz();
        Cmn120ParamModel paramModel = new Cmn120ParamModel();
        paramModel.setParam(form);
        biz.setMyGroupUser(Cmn120Biz.getUserSid(getRequestModel(req)), paramModel, con);
        paramModel.setFormData(form);
        con.setAutoCommit(false);

        return __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param rootDir ルートディレクトリ
     * @param form フォーム
     * @param req リクエストモデル
     * @return boolean true=ファイル有り(正常に処理終了)、false=ファイルが存在しない
     * @throws IOException フォームパラメータの設定に失敗
     * @throws IOToolsException フォームパラメータの設定に失敗
     */
    public boolean setFileEdit(String rootDir, Cmn120Form form, HttpServletRequest req)
    throws IOException, IOToolsException {

        //セッションID
        HttpSession session = req.getSession();
        String sessionId = session.getId();

        String dirPath = __getTempDirPath(rootDir, form, sessionId);

        if (!IOTools.isFileCheck(dirPath, FORM_FILENAME, false)) {
            return false;
        }

        setFormData(rootDir, form, req);
        removeFormDataFile(rootDir, form, req);
        return true;
    }

    /**
     * <br>[機  能] 保存した呼び出し元画面のフォームパラメータをリクエストに設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param rootDir ルートディレクトリ
     * @param form フォーム
     * @param req リクエストモデル
     * @throws IOException フォームパラメータの設定に失敗
     * @throws IOToolsException フォームパラメータの設定に失敗
     */
    public void setFormData(String rootDir, Cmn120Form form, HttpServletRequest req)
    throws IOException, IOToolsException {

        File tempFile = __getTempFilePath(rootDir, form, req);

        ObjectFile objFile = new ObjectFile(tempFile.getParent(), tempFile.getName());
        Object formData = objFile.load();

        req.setAttribute(form.getCmn120FormKey(), formData);
    }

    /**
     * <br>[機  能] 呼び出し元画面のフォームパラメータをテンポラリディレクトリに保存する
     * <br>[解  説]
     * <br>[備  考]
     * @param rootDir ルートディレクトリ
     * @param form フォーム
     * @param req リクエストモデル
     * @throws IOException フォームパラメータの保存に失敗
     * @throws IOToolsException フォームパラメータの保存に失敗
     */
    public void saveFormData(String rootDir, Cmn120Form form, HttpServletRequest req)
    throws IOException, IOToolsException {

        Object cmn120ModelData = req.getAttribute(form.getCmn120FormKey());

        File tempFile = __getTempFilePath(rootDir, form, req);
        IOTools.isDirCheck(tempFile.getParent(), true);

        ObjectFile objFile = new ObjectFile(tempFile.getParent(), tempFile.getName());
        objFile.save(cmn120ModelData);
    }

    /**
     * <br>[機  能] 呼び出し元画面のフォームパラメータファイルを削除する
     * <br>[解  説]
     * <br>[備  考]
     * @param rootDir ルートディレクトリ
     * @param form フォーム
     * @param req リクエスト
     * @throws IOException フォームパラメータの設定に失敗
     * @throws IOToolsException フォームパラメータの設定に失敗
     */
    public void removeFormDataFile(String rootDir,
                                   Cmn120Form form,
                                   HttpServletRequest req)
                                   throws IOException, IOToolsException {

        File tempFile = __getTempFilePath(rootDir, form, req);
        IOTools.deleteFile(tempFile.getPath());
    }

    /**
     * <br>[機  能] 呼び出し元フォームパラメータ保存ファイルパスを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param rootDir ルートディレクトリ
     * @param form アクションフォーム
     * @param req リクエスト
     * @return テンポラリディレクトリパス
     */
    private File __getTempFilePath(String rootDir,
                                  Cmn120Form form,
                                  HttpServletRequest req) {
        HttpSession session = req.getSession();

        //セッションID
        String sessionId = session.getId();

        StringBuilder filePath = new StringBuilder("");
        filePath.append(__getTempDirPath(rootDir, form, sessionId));
        filePath.append(FORM_FILENAME);

        return new File(filePath.toString());
    }

    /**
     * <br>[機  能] テンポラリディレクトリのパスを取得
     * <br>[解  説]
     * <br>[備  考]
     * @param rootDir ルートディレクトリ
     * @param form アクションフォーム
     * @param sessionId セッションID
     * @return String テンポラリディレクトリのパス
     */
    private String __getTempDirPath(String rootDir,
                                    Cmn120Form form,
                                    String sessionId) {

        StringBuilder filePath = new StringBuilder("");
        filePath.append(rootDir);
        filePath.append("/");
        filePath.append(form.getCmn120FormKey());
        filePath.append("/");
        filePath.append(sessionId);
        filePath.append("/");
        return filePath.toString();
    }

    /**
     * <br>[機  能] 初期データを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行時例外
     */
    private void __getInitData(
        ActionMapping map,
        Cmn120Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        JSONObject jsonData = new JSONObject();

        int userSid = Cmn120Biz.getUserSid(getRequestModel(req));

        con.setAutoCommit(true);
        Cmn120Biz biz = new Cmn120Biz();
        Cmn120ParamModel paramModel = new Cmn120ParamModel();
        paramModel.setParam(form);
        biz.setInitData(paramModel, con, getRequestModel(req), userSid);
        paramModel.setFormData(form);
        con.setAutoCommit(false);

        jsonData = JSONObject.fromObject(paramModel);

        PrintWriter out = null;

        try {
            res.setHeader("Cache-Control", "no-cache");
            res.setContentType("application/json;charset=UTF-8");
            out = res.getWriter();
            out.print(jsonData);
            out.flush();
        } catch (Exception e) {
            log__.error("jsonデータ送信失敗(初期データ)");
            throw e;
        } finally {
            if (out != null) {
                out.close();
            }
        }

    }

    /**
     * <br>[機  能] 削除(左矢印)ボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行時例外
     */
    private void __getLeftData(
        ActionMapping map,
        Cmn120Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        form.setCmn120userSid(
                getDeleteMember(form.getCmn120SelectRightUser(), form.getCmn120userSid()));

        __getInitData(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 追加(右矢印)ボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行時例外
     */
    private void __getRightData(
        ActionMapping map,
        Cmn120Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        form.setCmn120userSid(
                getAddMember(form.getCmn120SelectLeftUser(), form.getCmn120userSid()));

        __getInitData(map, form, req, res, con);
    }

    /**
     * <br>[機  能] ～に追加↓ボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行例外
     */
    private void __getAddMyGrpUserData(
        ActionMapping map,
        Cmn120Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        con.setAutoCommit(true);
        Cmn120Biz biz = new Cmn120Biz();
        Cmn120ParamModel paramModel = new Cmn120ParamModel();
        paramModel.setParam(form);
        biz.setMyGroupUser(Cmn120Biz.getUserSid(getRequestModel(req)), paramModel, con);
        paramModel.setFormData(form);
        con.setAutoCommit(false);

        __getInitData(map, form, req, res, con);
    }


    /**
     * <br>[機  能] 登録に使用する側のコンボで選択中のメンバーをメンバーリストから削除する
     *
     * <br>[解  説] 登録に使用する側のコンボ表示に必要なSID(複数)をメンバーリスト(memberSid)で持つ。
     *              画面で削除矢印ボタンをクリックした場合、
     *              登録に使用する側のコンボで選択中の値(deleteSelectSid)をメンバーリストから削除する。
     *
     * <br>[備  考] 登録に使用する側のコンボで値が選択されていない場合はメンバーリストをそのまま返す
     *
     * @param deleteSelectSid 登録に使用する側のコンボで選択中の値
     * @param memberSid メンバーリスト
     * @return 削除済みのメンバーリスト
     */
    public String[] getDeleteMember(String[] deleteSelectSid, String[] memberSid) {

        if (deleteSelectSid == null) {
            return memberSid;
        }
        if (deleteSelectSid.length < 1) {
            return memberSid;
        }

        if (memberSid == null) {
            memberSid = new String[0];
        }

        //削除後に画面にセットするメンバーリストを作成
        ArrayList<String> list = new ArrayList<String>();

        for (int i = 0; i < memberSid.length; i++) {
            boolean setFlg = true;

            for (int j = 0; j < deleteSelectSid.length; j++) {
                if (memberSid[i].equals(deleteSelectSid[j])) {
                    setFlg = false;
                    break;
                }
            }

            if (setFlg) {
                list.add(memberSid[i]);
            }
        }

        log__.debug("削除後メンバーリストサイズ = " + list.size());
        return list.toArray(new String[list.size()]);
    }


    /**
     * <br>[機  能] 追加側のコンボで選択中のメンバーをメンバーリストに追加する
     *
     * <br>[解  説] 画面右側のコンボ表示に必要なSID(複数)をメンバーリスト(memberSid)で持つ。
     *              画面で追加矢印ボタンをクリックした場合、
     *              追加側のコンボで選択中の値(addSelectSid)をメンバーリストに追加する。
     *
     * <br>[備  考] 追加側のコンボで値が選択されていない場合はメンバーリストをそのまま返す
     *
     * @param addSelectSid 追加側のコンボで選択中の値
     * @param memberSid メンバーリスト
     * @return 追加済みのメンバーリスト
     */
    public String[] getAddMember(String[] addSelectSid, String[] memberSid) {

        if (addSelectSid == null) {
            return memberSid;
        }
        if (addSelectSid.length < 1) {
            return memberSid;
        }


        //追加後に画面にセットするメンバーリストを作成
        ArrayList<String> list = new ArrayList<String>();

        if (memberSid != null) {
            for (int j = 0; j < memberSid.length; j++) {
                if (ValidateUtil.isNumber(memberSid[j])) {
                    if (Integer.parseInt(memberSid[j]) >= 0) {
                        list.add(memberSid[j]);
                    }
                } else {
                    list.add(memberSid[j]);
                }
            }
        }

        for (int i = 0; i < addSelectSid.length; i++) {

            if (ValidateUtil.isNumber(addSelectSid[i])) {
                if (Integer.parseInt(addSelectSid[i]) >= 0) {
                    list.add(addSelectSid[i]);
                }
            } else {
                list.add(addSelectSid[i]);
            }
        }

        log__.debug("追加後メンバーリストサイズ = " + list.size());
        return list.toArray(new String[list.size()]);
    }

}
