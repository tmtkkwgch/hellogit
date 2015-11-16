package jp.groupsession.v2.sch.sch041;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.SchDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.rsv.model.RsvSisDataModel;
import jp.groupsession.v2.sch.AbstractScheduleAction;
import jp.groupsession.v2.sch.sch040.model.Sch040AddressModel;
import jp.groupsession.v2.sch.sch040.model.Sch040CompanyModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] スケジュール繰り返し登録画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sch041Action extends AbstractScheduleAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sch041Action.class);

    /**
     * <br>アクション実行
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行例外
     * @return アクションフォーム
     */
    public ActionForward executeAction(ActionMapping map, ActionForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        log__.debug("START_SCH041");
        //更新処理が無いのでAutoCommitはtrue
        con.setAutoCommit(true);

        ActionForward forward = null;
        Sch041Form uform = (Sch041Form) form;

        //アクセス不可グループ、またはユーザに対してのスケジュール登録を許可しない
        int selectUserSid = NullDefault.getInt(uform.getSch010SelectUsrSid(), -1);
        if (selectUserSid >= 0) {
            int sessionUserSid = getSessionUserSid(req);
            String selectUsrKbn = NullDefault.getString(uform.getSch010SelectUsrKbn(), "");
            SchDao schDao = new SchDao(con);
            if (selectUsrKbn.equals(String.valueOf(GSConstSchedule.USER_KBN_GROUP))) {
                //グループスケジュール登録権限チェック
                if (!schDao.canRegistGroupSchedule(selectUserSid, sessionUserSid)) {
                    return getSubmitErrorPage(map, req);
                }
            } else {
                //ユーザスケジュール登録権限チェック
                if (!schDao.canRegistUserSchedule(selectUserSid, sessionUserSid)) {
                    return getSubmitErrorPage(map, req);
                }
            }
        }

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        cmd = cmd.trim();
        uform.setSch040ScrollFlg("0");
        Sch041Biz biz = new Sch041Biz(getRequestModel(req));
        int schSid = NullDefault.getInt(uform.getSch010SchSid(), -1);
        log__.debug("ーーーーーーーーーーーーー");

        String cm = NullDefault.getString(uform.getCmd(), GSConstSchedule.CMD_ADD);
        if (cm.equals(GSConstSchedule.CMD_EDIT)
                && !biz.isExistData(schSid, con)) {
            log__.debug("データが存在しない");
            //データが存在しない
            return __doNoneDataError(map, uform, req, res, con);
        }
        log__.debug("CMD==>" + cmd);
        if (cmd.equals("041_week")) {
            //週間スケジュール
            forward = map.findForward("041_week");
        } else if (cmd.equals("041_month")) {
            //月間スケジュール
            forward = map.findForward("041_month");
        } else if (cmd.equals("041_day")) {
            //日間スケジュール
            forward = map.findForward("041_day");
        } else if (cmd.equals("041_ok")) {
            //スケジュール拡張登録確認
            forward = __doOk(map, uform, req, res, con);
        } else if (cmd.equals("041_del")) {
            //削除確認画面
            forward = __doDelete(map, uform, req, res, con);
        } else if (cmd.equals("041_back")) {
            //戻る
            forward = __doBack(map, uform, req, res, con);
        } else if (cmd.equals("041_group")) {
            //グループコンボ変更
            __doInit(map, uform, req, res, con);
            uform.setSch040ScrollFlg("1");
            forward = map.getInputForward();
        } else if (cmd.equals("041_leftarrow")) {
            //「左矢印」処理
            log__.debug("「左矢印」処理（所属←未所属）");
            forward = __doLeft(map, uform, req, res, con);
        } else if (cmd.equals("041_rightarrow")) {
            //「右矢印」処理
            log__.debug("「右矢印」処理（所属→未所属）");
            forward = __doRight(map, uform, req, res, con);
        } else if (cmd.equals("041_res_leftarrow")) {
            //「左矢印」処理
            log__.debug("「左矢印」処理（予約する施設を追加）");
            forward = __doResLeft(map, uform, req, res, con);
        } else if (cmd.equals("041_res_rightarrow")) {
            //「右矢印」処理
            log__.debug("「右矢印」処理（予約する施設を削除）");
            forward = __doResRight(map, uform, req, res, con);
        } else if (cmd.equals("041_default")) {
            //「通常登録画面」
            log__.debug("「通常登録画面」");
            forward = __doDefault(map, uform, req, res, con);
        } else if (cmd.equals("040_copy")) {
            //「複写して登録」
            log__.debug("「複写して登録」");
            forward = __doCopy(map, uform, req, res, con);
        } else if (cmd.equals("selectedCompany")) {
            //会社選択
            log__.debug("会社を選択");
            forward = __doSelectCompany(map, uform, req, res, con);
        } else if (cmd.equals("deleteCompany")) {
            //会社削除
            log__.debug("会社削除");
            forward = __doDeleteCompany(map, uform, req, res, con);
        } else {
            //スケジュール登録
            log__.debug("初期表示");
            __doInit(map, uform, req, res, con);
            forward = map.getInputForward();
        }
        log__.debug("END_SCH041");
        return forward;
    }

    /**
     * <br>初期表処理
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    private void __doInit(ActionMapping map, Sch041Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws SQLException {

        //管理者設定を反映したプラグイン設定情報を取得
        PluginConfig pconfig
            = getPluginConfigForMain(getPluginConfig(req), con, getSessionUserSid(req));

        RequestModel reqMdl = getRequestModel(req);
        Sch041Biz biz = new Sch041Biz(reqMdl);
        Sch041ParamModel paramMdl = new Sch041ParamModel();
        paramMdl.setParam(form);
        biz.getInitData(paramMdl, pconfig, con);
        biz.setCompanyData(paramMdl, con, getSessionUserModel(req).getUsrsid(), reqMdl);
        paramMdl.setFormData(form);

        // トランザクショントークン設定
        saveToken(req);
    }

    /**
     * 通常登録へ遷移します。
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     */
    private ActionForward __doDefault(ActionMapping map, Sch041Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con) {

        ActionForward forward = null;
        forward = map.findForward("041_default");

        return forward;
    }

    /**
     * <br>登録ボタンクリック時処理
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward 画面遷移先
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doOk(ActionMapping map, Sch041Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws SQLException {

        ActionForward forward = null;

        ActionErrors errors = form.validateCheck(getRequestModel(req), con);
        if (errors.size() > 0) {
            addErrors(req, errors);
            __doInit(map, form, req, res, con);
            log__.debug("入力エラー");
            return map.getInputForward();
        }

        log__.debug("入力エラーなし");

        forward = __doKakunin(map, form, req, res, con);
        return forward;
    }

    /**
     * <br>スケジュール拡張登録確認画面へ遷移
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward 画面遷移
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doKakunin(ActionMapping map, Sch041Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws SQLException {
        ActionForward forward = null;
        forward = map.findForward("041_ok");
        return forward;
    }

    /**
     * <br>削除ボタンクリック時処理
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward 画面遷移先
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doDelete(ActionMapping map, Sch041Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws SQLException {

        ActionForward forward = null;

        //編集権限チェック
        ActionErrors errors = form.validateExPowerCheck(getRequestModel(req), con);

        if (errors.size() > 0) {
            log__.debug("権限エラー");
            addErrors(req, errors);
            form.setCmd("edit");
            __doInit(map, form, req, res, con);

            return map.getInputForward();
        }

        // トランザクショントークン設定
        this.saveToken(req);
        forward = map.findForward("041_ok");
        return forward;
    }

    /**
     * <br>リクエストを解析し画面遷移先を取得する
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward 画面遷移
     */
    private ActionForward __doBack(ActionMapping map, Sch041Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con) {

        ActionForward forward = null;

//        String cmn = form.getCmd();
        String dspMod = form.getDspMod();
        String listMod = form.getListMod();
        if (listMod.equals(GSConstSchedule.DSP_MOD_LIST)) {
            forward = map.findForward("041_list");
            return forward;
        }
        if (dspMod.equals(GSConstSchedule.DSP_MOD_WEEK)) {
            forward = map.findForward("041_week");
        } else if (dspMod.equals(GSConstSchedule.DSP_MOD_MONTH)) {
            forward = map.findForward("041_month");
        } else if (dspMod.equals(GSConstSchedule.DSP_MOD_DAY)) {
            forward = map.findForward("041_day");
        } else if (dspMod.equals(GSConstSchedule.DSP_MOD_MAIN)) {
            forward = map.findForward("041_main");
        } else if (form.getDspMod().equals(GSConstSchedule.DSP_MOD_KOJIN_WEEK)) {
            forward = map.findForward("041_kojin");
        }
        return forward;
    }

    /**
     * <br>[機  能] 「左矢印」処理
     * <br>[解  説] ・選択した同時登録ユーザを同時登録ユーザ一覧から除外する
     * <br>         ・画面を再表示
     * <br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws Exception 実行例外
     */
    private ActionForward __doLeft(ActionMapping map,
            Sch041Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con
            )
            throws Exception {
        log__.debug("START : __doLeft");
        __delUser(req, form.getSch040SelectUsrLabel(), form, con);
        __doInit(map, form, req, res, con);
        form.setSch040ScrollFlg("1");
        log__.debug("END : __doLeft");
        // 自画面再表示
        return map.getInputForward();
    }

    /**
     * <br>[機  能] 「右矢印」処理
     * <br>[解  説] ・所属エリアから同時登録ユーザ一覧へ追加
     * <br>         ・画面を再表示
     * <br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws Exception 実行例外
     */
    private ActionForward __doRight(ActionMapping map,
            Sch041Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con
            )
            throws Exception {
        log__.debug("START : __doRight");
        __addUser(req, form.getSch040SelectUsrLabel(), form, con);
        __doInit(map, form, req, res, con);
        form.setSch040ScrollFlg("1");
        log__.debug("END : __doRight");
        // 自画面再表示
        return map.getInputForward();

    }

    /**
     * <br>[機  能] 「左矢印」処理
     * <br>[解  説] ・選択した同時登録施設を同時登録ユーザ一覧から除外する
     * <br>         ・画面を再表示
     * <br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws Exception 実行例外
     */
    private ActionForward __doResLeft(ActionMapping map,
            Sch041Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con
            )
            throws Exception {

        __delSisetu(req, form.getSch040ReserveSelectLabel(), form, con);
        __doInit(map, form, req, res, con);
        form.setSch040ScrollFlg("1");
        // 自画面再表示
        return map.getInputForward();
    }

    /**
     * <br>[機  能] 「右矢印」処理
     * <br>[解  説] ・所属エリアから同時登録ユーザ一覧へ追加
     * <br>         ・画面を再表示
     * <br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws Exception 実行例外
     */
    private ActionForward __doResRight(ActionMapping map,
            Sch041Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con
            )
            throws Exception {

        __addSisetu(req, form.getSch040ReserveSelectLabel(), form, con);
        __doInit(map, form, req, res, con);
        form.setSch040ScrollFlg("1");
        // 自画面再表示
        return map.getInputForward();

    }

    /**
     * <br>ユーザを同時登録ユーザ一覧へ追加します。
     * @param req リクエスト
     * @param uList 所属利用者SIDの配列
     * @param con コネクション
     * @param form フォーム
     * @throws Exception  実行例外
     */
    private void __addUser(HttpServletRequest req,
            ArrayList<CmnUsrmInfModel> uList,
            Sch041Form form,
            Connection con
            ) throws Exception {
        log__.debug("START : __addUser");
        //未所属利用者リスト（左）からパラメータを取得
        log__.debug("__addUser 未所属利用者リスト（右）からパラメータを取得");
        String[] r_users = form.getSch041SvUsers();
        if (r_users == null) {
            r_users = new String [0];
        }
        String[] l_users = req.getParameterValues("sch041users_l");

        if (l_users == null) {
            l_users = new String [0];
        }
        //同時登録ユーザSIDを格納
        ArrayList<String> r_user_list = new ArrayList<String>();
        for (int i = 0; i < r_users.length; i++) {
            r_user_list.add(r_users[i]);
        }

        if (uList == null) {
            uList = new ArrayList<CmnUsrmInfModel>();
        }

        if (l_users.length > 0) {
            //所属利用者リスト（左）へ追加
            for (int i = 0; i < l_users.length; i++) {
                int intLeftUser = Integer.parseInt(l_users[i]);
                if (intLeftUser < 0) {
                    continue;
                }
                //選択ユーザ有り
                boolean exist = false;
                for (int j = 0; j < r_users.length; j++) {
                    //同一ユーザSIDか判定
                    if (Integer.parseInt(r_users[j]) == intLeftUser) {
                        exist = true;
                        break;
                    }
                }

                if (!exist) {
                    r_user_list.add(l_users[i]);
                }
            }

            form.setSch041SvUsers((String[]) r_user_list.toArray(new String[r_user_list.size()]));
        }

        log__.debug("END : __addUser");
    }

    /**
     * <br>同時登録ユーザ一覧から選択ユーザを削除します。
     * @param req リクエスト
     * @param uList 同時登録ユーザSIDの配列
     * @param form フォーム
     * @param con コネクション
     * @throws Exception 例外
     */
    private void __delUser(
    HttpServletRequest req,
    ArrayList<CmnUsrmInfModel> uList,
    Sch041Form form,
    Connection con
    ) throws Exception {

        log__.debug("START : __delUser");
        //同時登録ユーザリスト（右）からパラメータを取得
        String[] sv_Users = form.getSch041SvUsers();
        String[] usr_r = form.getSch041users_r();
        if (sv_Users == null) {
            sv_Users = new String [0];
        }

        if (usr_r == null) {
            usr_r = new String [0];
        }
        ArrayList<String> sv_user_list = new ArrayList<String>();

        for (String svUser : sv_Users) {
            int intSvUser = Integer.parseInt(svUser);

            boolean exists = false;
            for (String rightUser : usr_r) {
                if (intSvUser == Integer.parseInt(rightUser)) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                sv_user_list.add(svUser);
            }
        }

        form.setSch041SvUsers((String[]) sv_user_list.toArray(new String[sv_user_list.size()]));
        log__.debug("END : __delUser");
    }

    /**
     * <br>施設を同時登録一覧へ追加します。
     * @param req リクエスト
     * @param uList 所属利用者SIDの配列
     * @param con コネクション
     * @param form フォーム
     * @throws Exception  実行例外
     */
    private void __addSisetu(HttpServletRequest req,
            ArrayList<RsvSisDataModel> uList,
            Sch041Form form,
            Connection con
            ) throws Exception {

        //未登録施設リストからパラメータを取得
        String[] r_users = form.getSch041SvReserve();
        if (r_users == null) {
            r_users = new String [0];
        }
        String[] l_users = req.getParameterValues("sch041Reserve_l");

        if (l_users == null) {
            l_users = new String [0];
        }
        //同時登録施設SIDを格納
        ArrayList<String> r_user_list = new ArrayList<String>();
        for (int i = 0; i < r_users.length; i++) {
            r_user_list.add(r_users[i]);
        }

        if (uList == null) {
            uList = new ArrayList<RsvSisDataModel>();
        }

        if (l_users.length > 0) {
            //所属施設リストへ追加
            for (int i = 0; i < l_users.length; i++) {
                int intLeftUser = Integer.parseInt(l_users[i]);
                if (intLeftUser < 0) {
                    continue;
                }
                //選択ユーザ有り
                boolean exist = false;
                for (int j = 0; j < r_users.length; j++) {
                    //同一ユーザSIDか判定
                    if (Integer.parseInt(r_users[j]) == intLeftUser) {
                        exist = true;
                        break;
                    }
                }

                if (!exist) {
                    r_user_list.add(l_users[i]);
                }
            }

            form.setSch041SvReserve((String[]) r_user_list.toArray(new String[r_user_list.size()]));
        }

    }

    /**
     * <br>同時登録施設一覧から選択ユーザを削除します。
     * @param req リクエスト
     * @param uList 同時登録ユーザSIDの配列
     * @param form フォーム
     * @param con コネクション
     * @throws Exception 例外
     */
    private void __delSisetu(
    HttpServletRequest req,
    ArrayList<RsvSisDataModel> uList,
    Sch041Form form,
    Connection con
    ) throws Exception {

        //同時登録施設リストからパラメータを取得
        String[] sv_Users = form.getSch041SvReserve();
        String[] usr_r = form.getSch041Reserve_r();
        if (sv_Users == null) {
            sv_Users = new String [0];
        }

        if (usr_r == null) {
            usr_r = new String [0];
        }
        ArrayList<String> sv_user_list = new ArrayList<String>();

        for (String svUser : sv_Users) {
            int intSvUser = Integer.parseInt(svUser);

            boolean exists = false;
            for (String rightUser : usr_r) {
                if (intSvUser == Integer.parseInt(rightUser)) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                sv_user_list.add(svUser);
            }
        }

        form.setSch041SvReserve((String[]) sv_user_list.toArray(new String[sv_user_list.size()]));

    }
    /**
     * <br>登録・更新完了画面設定
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     */
    private ActionForward __doNoneDataError(ActionMapping map, Sch041Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con) {
        ActionForward forward = null;

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;
        GsMessage gsMsg = new GsMessage();

        //スケジュール登録完了画面パラメータの設定
        MessageResources msgRes = getResources(req);
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        String listMod = NullDefault.getString(form.getListMod(), "");
        String dspMod = form.getDspMod();
        if (listMod.equals(GSConstSchedule.DSP_MOD_LIST)) {
            urlForward = map.findForward("041_list");
        } else {
            if (dspMod.equals(GSConstSchedule.DSP_MOD_WEEK)) {
                urlForward = map.findForward("041_week");
            } else if (dspMod.equals(GSConstSchedule.DSP_MOD_MONTH)) {
                urlForward = map.findForward("041_month");
            } else if (dspMod.equals(GSConstSchedule.DSP_MOD_DAY)) {
                urlForward = map.findForward("041_day");
            } else if (dspMod.equals(GSConstSchedule.DSP_MOD_MAIN)) {
                urlForward = map.findForward("041_main");
            }
        }
        //スケジュール
        String textSchedule = gsMsg.getMessage(req, "schedule.108");
        //変更
        String textChange = gsMsg.getMessage(req, "cmn.change");
        cmn999Form.setUrlOK(urlForward.getPath());
        cmn999Form.setMessage(msgRes.getMessage("error.none.edit.data",
                textSchedule, textChange));
        cmn999Form.addHiddenParam("sch010DspDate", form.getSch010DspDate());
        cmn999Form.addHiddenParam("changeDateFlg", form.getChangeDateFlg());
        cmn999Form.addHiddenParam("sch010DspGpSid", form.getSch010DspGpSid());
        cmn999Form.addHiddenParam("sch010SelectUsrSid", form.getSch010SelectUsrSid());
        cmn999Form.addHiddenParam("sch010SelectUsrKbn", form.getSch010SelectUsrKbn());
        cmn999Form.addHiddenParam("sch010SelectDate", form.getSch010SelectDate());
        cmn999Form.addHiddenParam("schWeekDate", form.getSchWeekDate());
        cmn999Form.addHiddenParam("schDailyDate", form.getSchDailyDate());
        cmn999Form.addHiddenParam("sch100SelectUsrSid", form.getSch100SelectUsrSid());
        cmn999Form.addHiddenParam("sch100PageNum", form.getSch100PageNum());
        cmn999Form.addHiddenParam("sch100Slt_page1", form.getSch100Slt_page1());
        cmn999Form.addHiddenParam("sch100Slt_page2", form.getSch100Slt_page2());
        cmn999Form.addHiddenParam("sch100OrderKey1", form.getSch100OrderKey1());
        cmn999Form.addHiddenParam("sch100SortKey1", form.getSch100SortKey1());
        cmn999Form.addHiddenParam("sch100OrderKey2", form.getSch100OrderKey2());
        cmn999Form.addHiddenParam("sch100SortKey2", form.getSch100SortKey2());
        //save
        cmn999Form.addHiddenParam("sch100SvSltGroup", form.getSch100SvSltGroup());
        cmn999Form.addHiddenParam("sch100SvSltUser", form.getSch100SvSltUser());
        cmn999Form.addHiddenParam("sch100SvSltStartYearFr", form.getSch100SvSltStartYearFr());
        cmn999Form.addHiddenParam("sch100SvSltStartMonthFr", form.getSch100SvSltStartMonthFr());
        cmn999Form.addHiddenParam("sch100SvSltStartDayFr", form.getSch100SvSltStartDayFr());
        cmn999Form.addHiddenParam("sch100SvSltStartYearTo", form.getSch100SvSltStartYearTo());
        cmn999Form.addHiddenParam("sch100SvSltStartMonthTo", form.getSch100SvSltStartMonthTo());
        cmn999Form.addHiddenParam("sch100SvSltStartDayTo", form.getSch100SvSltStartDayTo());
        cmn999Form.addHiddenParam("sch100SvSltEndYearFr", form.getSch100SvSltEndYearFr());
        cmn999Form.addHiddenParam("sch100SvSltEndMonthFr", form.getSch100SvSltEndMonthFr());
        cmn999Form.addHiddenParam("sch100SvSltEndDayFr", form.getSch100SvSltEndDayFr());
        cmn999Form.addHiddenParam("sch100SvSltEndYearTo", form.getSch100SvSltEndYearTo());
        cmn999Form.addHiddenParam("sch100SvSltEndMonthTo", form.getSch100SvSltEndMonthTo());
        cmn999Form.addHiddenParam("sch100SvSltEndDayTo", form.getSch100SvSltEndDayTo());
        cmn999Form.addHiddenParam("sch100SvKeyWordkbn", form.getSch100SvKeyWordkbn());
        cmn999Form.addHiddenParam("sch100SvKeyValue", form.getSch100SvKeyValue());
        cmn999Form.addHiddenParam("sch100SvOrderKey1", form.getSch100SvOrderKey1());
        cmn999Form.addHiddenParam("sch100SvSortKey1", form.getSch100SvSortKey1());
        cmn999Form.addHiddenParam("sch100SvOrderKey2", form.getSch100SvOrderKey2());
        cmn999Form.addHiddenParam("sch100SvSortKey2", form.getSch100SvSortKey2());
        cmn999Form.addHiddenParam("sch100SltGroup", form.getSch100SltGroup());
        cmn999Form.addHiddenParam("sch100SltUser", form.getSch100SltUser());
        cmn999Form.addHiddenParam("sch100SltStartYearFr", form.getSch100SltStartYearFr());
        cmn999Form.addHiddenParam("sch100SltStartMonthFr", form.getSch100SltStartMonthFr());
        cmn999Form.addHiddenParam("sch100SltStartDayFr", form.getSch100SltStartDayFr());
        cmn999Form.addHiddenParam("sch100SltStartYearTo", form.getSch100SltStartYearTo());
        cmn999Form.addHiddenParam("sch100SltStartMonthTo", form.getSch100SltStartMonthTo());
        cmn999Form.addHiddenParam("sch100SltStartDayTo", form.getSch100SltStartDayTo());
        cmn999Form.addHiddenParam("sch100SltEndYearFr", form.getSch100SltEndYearFr());
        cmn999Form.addHiddenParam("sch100SltEndMonthFr", form.getSch100SltEndMonthFr());
        cmn999Form.addHiddenParam("sch100SltEndDayFr", form.getSch100SltEndDayFr());
        cmn999Form.addHiddenParam("sch100SltEndYearTo", form.getSch100SltEndYearTo());
        cmn999Form.addHiddenParam("sch100SltEndMonthTo", form.getSch100SltEndMonthTo());
        cmn999Form.addHiddenParam("sch100SltEndDayTo", form.getSch100SltEndDayTo());
        cmn999Form.addHiddenParam("sch100KeyWordkbn", form.getSch100KeyWordkbn());
        cmn999Form.addHiddenParam("sch100SvBgcolor", form.getSch100SvBgcolor());
        cmn999Form.addHiddenParam("sch100Bgcolor", form.getSch100Bgcolor());
        cmn999Form.addHiddenParam("sch100CsvOutField", form.getSch100CsvOutField());

        //検索対象
        String[] searchTarget = form.getSch100SearchTarget();
        if (searchTarget != null) {
            for (String target : searchTarget) {
                cmn999Form.addHiddenParam("sch100SearchTarget", target);
            }
        }
        //検索対象
        String[] svSearchTarget = form.getSch100SvSearchTarget();
        if (svSearchTarget != null) {
            for (String target : svSearchTarget) {
                cmn999Form.addHiddenParam("sch100SvSearchTarget", target);
            }
        }
        req.setAttribute("cmn999Form", cmn999Form);

        forward = map.findForward("gf_msg");
        return forward;
    }

    /**
     * <br>[機  能] 「複写して登録」処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws Exception 実行例外
     */
    private ActionForward __doCopy(ActionMapping map,
            Sch041Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con
            )
            throws Exception {

        __doInit(map, form, req, res, con);
        return map.getInputForward();
    }

    /**
     * <br>[機  能] 会社を選択処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws Exception 実行例外
     */
    private ActionForward __doSelectCompany(ActionMapping map,
            Sch041Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con
            )
            throws Exception {

        //重複したパラメータを除外する
        String[] acoSidArray = form.getSch041CompanySid();
        if (acoSidArray != null) {
            String[] abaSidArray = form.getSch041CompanyBaseSid();
            List<String> companyIdList = new ArrayList<String>();

            for (int index = 0; index < acoSidArray.length; index++) {
                String companyId = acoSidArray[index] + ":" + abaSidArray[index];
                if (companyIdList.indexOf(companyId) < 0) {
                    companyIdList.add(companyId);
                }
            }

            acoSidArray = new String[companyIdList.size()];
            abaSidArray = new String[companyIdList.size()];
            for (int index = 0; index < companyIdList.size(); index++) {
                String companyId = companyIdList.get(index);
                acoSidArray[index] = companyId.split(":")[0];
                abaSidArray[index] = companyId.split(":")[1];
            }

            form.setSch041CompanySid(acoSidArray);
            form.setSch041CompanyBaseSid(abaSidArray);
        }

        String[] addressIdArray = form.getSch041AddressId();
        if (addressIdArray != null) {
            List<String> addressIdList = new ArrayList<String>();

            for (int index = 0; index < addressIdArray.length; index++) {
                if (addressIdList.indexOf(addressIdArray[index]) < 0) {
                    addressIdList.add(addressIdArray[index]);
                }
            }

            form.setSch041AddressId(
                    addressIdList.toArray(new String[addressIdList.size()]));
        }
        //初期表示処理
        __doInit(map, form, req, res, con);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 会社を削除処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws Exception 実行例外
     */
    private ActionForward __doDeleteCompany(ActionMapping map,
            Sch041Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con)
            throws Exception {

        int delCompanyId = NullDefault.getInt(form.getSch041delCompanyId(), -1);
        int delCompanyBaseId = NullDefault.getInt(form.getSch041delCompanyBaseId(), -1);

        if (delCompanyId != -1 && delCompanyBaseId != -1) {

            //会社情報を設定
            RequestModel reqMdl = getRequestModel(req);
            Sch041Biz biz = new Sch041Biz(reqMdl);

            Sch041ParamModel paramMdl = new Sch041ParamModel();
            paramMdl.setParam(form);
            biz.setCompanyData(paramMdl, con, getSessionUserModel(req).getUsrsid(), reqMdl);
            paramMdl.setFormData(form);

            List<Sch040CompanyModel> companyList = form.getSch041CompanyList();
            if (companyList != null && !companyList.isEmpty()) {
                List<String> companyIdList = new ArrayList<String>();
                List<String> companyBaseIdList = new ArrayList<String>();
                List<String> addressIdList = new ArrayList<String>();
                List<Sch040CompanyModel> newCompanyList = new ArrayList<Sch040CompanyModel>();

                for (Sch040CompanyModel companyData : companyList) {
                    if (companyData.getCompanySid() != delCompanyId
                    || companyData.getCompanyBaseSid() != delCompanyBaseId) {
                        companyIdList.add(String.valueOf(companyData.getCompanySid()));
                        companyBaseIdList.add(String.valueOf(companyData.getCompanyBaseSid()));
                        for (Sch040AddressModel addressMdl : companyData.getAddressDataList()) {
                            addressIdList.add(String.valueOf(addressMdl.getAdrSid()));
                        }
                        newCompanyList.add(companyData);
                    }
                }

                form.setSch041CompanySid(companyIdList.toArray(new String[companyIdList.size()]));
                form.setSch041CompanyBaseSid(
                        companyBaseIdList.toArray(new String[companyBaseIdList.size()]));
                form.setSch041AddressId(addressIdList.toArray(new String[addressIdList.size()]));
                form.setSch041CompanyList(newCompanyList);
            }
        }

        //初期表示処理
        __doInit(map, form, req, res, con);

        return map.getInputForward();
    }
}