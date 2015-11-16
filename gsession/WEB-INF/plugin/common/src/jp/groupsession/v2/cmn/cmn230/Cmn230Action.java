package jp.groupsession.v2.cmn.cmn230;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.struts.AbstractGsAction;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] 全グループから選択ポップアップのアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cmn230Action extends AbstractGsAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Cmn230Action.class);

    /**
     * <br>[機  能] アクション実行
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws Exception 実行例外
     */
    public ActionForward executeAction(ActionMapping map,
                                         ActionForm form,
                                         HttpServletRequest req,
                                         HttpServletResponse res,
                                         Connection con)
        throws Exception {

        ActionForward forward = null;
        Cmn230Form thisForm = (Cmn230Form) form;
        //CMD
        String cmd = NullDefault.getString(req.getParameter(GSConst.P_CMD), "");
        cmd = cmd.trim();

        if (cmd.equals("searchUsr")) {
            //検索
            forward = __doSearch(map, thisForm, req, res, con);
        } else if (cmd.equals("230_belowarrow")) {
            //ユーザ削除処理
            log__.debug("「下矢印」処理（選択→非選択）");
            forward = __doRight(map, thisForm, req, res, con);
        } else if (cmd.equals("230_abovearrow")) {
            //ユーザ追加処理
            log__.debug("「上矢印」処理（選択←非選択）");
            forward = __doLeft(map, thisForm, req, res, con);
        } else if (cmd.equals("230_abovearrow_button")) {
            //ユーザ削除処理
            log__.debug("「下矢印」処理（選択→非選択）");
            forward = __doRightBtn(map, thisForm, req, res, con);
        } else if (cmd.equals("230_belowarrow_button")) {
            //ユーザ追加処理
            log__.debug("「上矢印」処理（選択←非選択）");
            forward = __doLeftBtn(map, thisForm, req, res, con);
        } else if (cmd.equals("230_belowarrow2")) {
            //ユーザ削除処理
            log__.debug("「下矢印」処理（選択→非選択）");
            forward = __doRight2(map, thisForm, req, res, con);
        } else if (cmd.equals("230_abovearrow2")) {
            //ユーザ追加処理
            log__.debug("「上矢印」処理（選択←非選択）");
            forward = __doLeft2(map, thisForm, req, res, con);
        } else if (cmd.equals("230_abovearrow_button2")) {
            //ユーザ削除処理
            log__.debug("「下矢印」処理（選択→非選択）");
            forward = __doRightBtn2(map, thisForm, req, res, con);
        } else if (cmd.equals("230_belowarrow_button2")) {
            //ユーザ追加処理
            log__.debug("「上矢印」処理（選択←非選択）");
            forward = __doLeftBtn2(map, thisForm, req, res, con);
        } else if (cmd.equals("230_belowarrowGp")) {
            //グループ削除処理
            log__.debug("「下矢印」処理（選択→非選択）");
            forward = __doRightGp(map, thisForm, req, res, con);
        } else if (cmd.equals("230_abovearrowGp")) {
            //グループ追加処理
            log__.debug("「上矢印」処理（選択←非選択）");
            forward = __doLeftGp(map, thisForm, req, res, con);
        } else if (cmd.equals("230_abovearrowGp_button")) {
            //グループ削除処理
            log__.debug("「下矢印」処理（選択→非選択）");
            forward = __doRightBtnGp(map, thisForm, req, res, con);
        } else if (cmd.equals("230_belowarrowGp_button")) {
            //グループ追加処理
            log__.debug("「上矢印」処理（選択←非選択）");
            forward = __doLeftBtnGp(map, thisForm, req, res, con);
        } else if (cmd.equals("230_belowarrowGp2")) {
            //グループ削除処理
            log__.debug("「下矢印」処理（選択→非選択）");
            forward = __doRightGp2(map, thisForm, req, res, con);
        } else if (cmd.equals("230_abovearrowGp2")) {
            //グループ追加処理
            log__.debug("「上矢印」処理（選択←非選択）");
            forward = __doLeftGp2(map, thisForm, req, res, con);
        } else if (cmd.equals("230_abovearrowGp_button2")) {
            //グループ削除処理
            log__.debug("「下矢印」処理（選択→非選択）");
            forward = __doRightBtnGp2(map, thisForm, req, res, con);
        } else if (cmd.equals("230_belowarrowGp_button2")) {
            //グループ追加処理
            log__.debug("「上矢印」処理（選択←非選択）");
            forward = __doLeftBtnGp2(map, thisForm, req, res, con);
        } else if (cmd.equals("230_tabUser")) {
            //ユーザタブクリック
            log__.debug("ユーザタブクリック");
            thisForm.setCmn230TabMode(0);
            forward = __doInit(map, thisForm, req, res, con);
        } else if (cmd.equals("230_tabGroup")) {
            //グループタブクリック
            log__.debug("グループタブクリック");
            thisForm.setCmn230TabMode(1);
            forward = __doInit(map, thisForm, req, res, con);
        } else {
            //初期表示
            forward = __doInit(map, thisForm, req, res, con);
        }
        return forward;
    }

    /**
     * <p>グループリスト取得
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行例外
     * @return アクションフォーム
     */
    public ActionForward __doInit(ActionMapping map, Cmn230Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {
        con.setAutoCommit(true);
        //パラメータを初期化
        form.validatePrm(req);

        ActionForward forward = null;

        //セッション情報を取得
        int sessionUsrSid = getSessionUserSid(req);

        Cmn230Biz biz = new Cmn230Biz();
        Cmn230ParamModel paramModel = new Cmn230ParamModel();
        paramModel.setParam(form);
        biz.setInitData(paramModel, con, sessionUsrSid, new GsMessage(req));
        paramModel.setFormData(form);

        con.setAutoCommit(false);

        forward = map.getInputForward();
        return forward;
    }

    /**
     * <br>[機  能] 「上矢印」処理
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
            Cmn230Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con
            )
            throws Exception {
        log__.debug("START : __doLeft");
        __delUser(req, form, con);
        log__.debug("END : __doLeft");
        return __doInit(map, form, req, res, con);

    }
    /**
     * <br>[機  能] 「上矢印」処理
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
    private ActionForward __doLeftBtn(ActionMapping map,
            Cmn230Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con
            )
            throws Exception {
        if (form.getMoveUsers() != null && !form.getMoveUsers().equals("")) {
            form.setMoveUserSid(form.getMoveUsers().split(","));
            log__.debug("START : __doLeft");
            __delUser(req, form, con);
            log__.debug("END : __doLeft");
            form.setMoveUsers("");
        }
        return __doInit(map, form, req, res, con);

    }
    /**
     * <br>[機  能] 「下矢印」処理
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
    private ActionForward __doRightBtn(ActionMapping map,
            Cmn230Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con
            )
            throws Exception {
        if (form.getMoveUsers() != null && !form.getMoveUsers().equals("")) {
            form.setMoveUserSid(form.getMoveUsers().split(","));
            log__.debug("START : __doRight");
            __addUser(req, form, con);
            log__.debug("END : __doRight");
            form.setMoveUsers("");
        }
        return __doInit(map, form, req, res, con);
    }
    /**
     * <br>[機  能] 「下矢印」処理
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
            Cmn230Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con
            )
            throws Exception {
        log__.debug("START : __doRight");
        __addUser(req, form, con);
        log__.debug("END : __doRight");
        return __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 「上矢印」処理
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
    private ActionForward __doLeft2(ActionMapping map,
            Cmn230Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con
            )
            throws Exception {
        log__.debug("START : __doLeft");
        __delUser2(req, form, con);
        log__.debug("END : __doLeft");
        return __doInit(map, form, req, res, con);

    }
    /**
     * <br>[機  能] 「上矢印」処理
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
    private ActionForward __doLeftBtn2(ActionMapping map,
            Cmn230Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con
            )
            throws Exception {
        if (form.getMoveUsers() != null && !form.getMoveUsers().equals("")) {
            form.setMoveUserSid(form.getMoveUsers().split(","));
            log__.debug("START : __doLeft");
            __delUser2(req, form, con);
            log__.debug("END : __doLeft");
            form.setMoveUsers("");
        }
        return __doInit(map, form, req, res, con);

    }
    /**
     * <br>[機  能] 「下矢印」処理
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
    private ActionForward __doRightBtn2(ActionMapping map,
            Cmn230Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con
            )
            throws Exception {
        if (form.getMoveUsers() != null && !form.getMoveUsers().equals("")) {
            form.setMoveUserSid(form.getMoveUsers().split(","));
            log__.debug("START : __doRight");
            __addUser2(req, form, con);
            log__.debug("END : __doRight");
            form.setMoveUsers("");
        }
        return __doInit(map, form, req, res, con);
    }
    /**
     * <br>[機  能] 「下矢印」処理
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
    private ActionForward __doRight2(ActionMapping map,
            Cmn230Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con
            )
            throws Exception {
        log__.debug("START : __doRight");
        __addUser2(req, form, con);
        log__.debug("END : __doRight");
        return __doInit(map, form, req, res, con);
    }

    /**
     * <br>ユーザを選択ユーザ一覧へ追加します。
     * @param req リクエスト
     * @param con コネクション
     * @param form フォーム
     * @throws Exception  実行例外
     */
    private void __addUser(HttpServletRequest req,
            Cmn230Form form,
            Connection con
            ) throws Exception {
        log__.debug("START : __addUser");
        //未所属利用者リスト（上）からパラメータを取得
        log__.debug("__addUser 未所属利用者リスト（上）からパラメータを取得");
        String[] r_users = form.getCmn230userSid();

        String[] l_users = form.getMoveUserSid();

        if (l_users == null) {
            l_users = new String [0];
        }
        //選択登録ユーザSIDを格納
        ArrayList<String> r_user_list = new ArrayList<String>();
        if (r_users != null && r_users.length > 0) {
            for (int i = 0; i < r_users.length; i++) {
                r_user_list.add(r_users[i]);
            }
        }

        if (l_users.length > 0) {
            //所属利用者リスト（下）へ追加
            for (int i = 0; i < l_users.length; i++) {
                int intLeftUser = -1;
                if (l_users[i] != null && !l_users[i].equals("")) {
                    intLeftUser = Integer.parseInt(l_users[i]);
                }
                if (intLeftUser < 0) {
                    continue;
                }
                //選択ユーザ有り
                boolean exist = false;
                if (r_users != null && r_users.length > 0) {
                    for (int j = 0; j < r_users.length; j++) {
                        //同一ユーザSIDか判定
                        if (!r_users[j].equals("")) {
                            if (Integer.parseInt(r_users[j]) == intLeftUser) {
                                exist = true;
                                break;
                            }
                        }
                    }
                }
                if (!exist) {
                    r_user_list.add(l_users[i]);
                }
            }

            form.setCmn230userSid((String[]) r_user_list.toArray(new String[r_user_list.size()]));
        }

        log__.debug("END : __addUser");
    }
    /**
     * <br>ユーザを選択ユーザ一覧へ追加します。
     * @param req リクエスト
     * @param con コネクション
     * @param form フォーム
     * @throws Exception  実行例外
     */
    private void __addUser2(HttpServletRequest req,
            Cmn230Form form,
            Connection con
            ) throws Exception {
        log__.debug("START : __addUser");
        //未所属利用者リスト（上）からパラメータを取得
        log__.debug("__addUser 未所属利用者リスト（上）からパラメータを取得");
        String[] r_users = form.getCmn230userSid2();

        String[] l_users = form.getMoveUserSid();

        if (l_users == null) {
            l_users = new String [0];
        }
        //選択登録ユーザSIDを格納
        ArrayList<String> r_user_list = new ArrayList<String>();
        if (r_users != null && r_users.length > 0) {
            for (int i = 0; i < r_users.length; i++) {
                r_user_list.add(r_users[i]);
            }
        }

        if (l_users.length > 0) {
            //所属利用者リスト（下）へ追加
            for (int i = 0; i < l_users.length; i++) {
                int intLeftUser = -1;
                if (l_users[i] != null && !l_users[i].equals("")) {
                    intLeftUser = Integer.parseInt(l_users[i]);
                }
                if (intLeftUser < 0) {
                    continue;
                }
                //選択ユーザ有り
                boolean exist = false;
                if (r_users != null && r_users.length > 0) {
                    for (int j = 0; j < r_users.length; j++) {
                        //同一ユーザSIDか判定
                        if (!r_users[j].equals("")) {
                            if (Integer.parseInt(r_users[j]) == intLeftUser) {
                                exist = true;
                                break;
                            }
                        }
                    }
                }
                if (!exist) {
                    r_user_list.add(l_users[i]);
                }
            }

            form.setCmn230userSid2((String[]) r_user_list.toArray(new String[r_user_list.size()]));
        }

        log__.debug("END : __addUser");
    }
    /**
     * <br>選択ユーザ一覧から選択ユーザを削除します。
     * @param req リクエスト
     * @param form フォーム
     * @param con コネクション
     * @throws Exception 例外
     */
    private void __delUser(
    HttpServletRequest req,
    Cmn230Form form,
    Connection con
    ) throws Exception {

        log__.debug("START : __delUser");
        //未所属利用者リスト（上）からパラメータを取得
        log__.debug("__addUser 未所属利用者リスト（右）からパラメータを取得");
        String[] r_users = form.getCmn230userSid();

        String[] l_users = form.getMoveUserSid();

        if (l_users == null) {
            l_users = new String [0];
        }
        //選択登録ユーザSIDを格納
        ArrayList<String> r_user_list = new ArrayList<String>();
        if (r_users != null && r_users.length > 0) {
            for (int i = 0; i < r_users.length; i++) {
                r_user_list.add(r_users[i]);
            }
        }

        if (l_users.length > 0) {
            //所属利用者リスト（下）から削除
            for (int i = 0; i < l_users.length; i++) {
                int intLeftUser = -1;
                if (l_users[i] != null && !l_users[i].equals("")) {
                    intLeftUser = Integer.parseInt(l_users[i]);
                }
                if (intLeftUser < 0) {
                    continue;
                }
                r_user_list.remove(l_users[i]);
            }

            form.setCmn230userSid((String[]) r_user_list.toArray(new String[r_user_list.size()]));
        }
        log__.debug("END : __delUser");
    }
    /**
     * <br>選択ユーザ一覧から選択ユーザを削除します。
     * @param req リクエスト
     * @param form フォーム
     * @param con コネクション
     * @throws Exception 例外
     */
    private void __delUser2(
    HttpServletRequest req,
    Cmn230Form form,
    Connection con
    ) throws Exception {

        log__.debug("START : __delUser");
        //未所属利用者リスト（上）からパラメータを取得
        log__.debug("__addUser 未所属利用者リスト（右）からパラメータを取得");
        String[] r_users = form.getCmn230userSid2();

        String[] l_users = form.getMoveUserSid();

        if (l_users == null) {
            l_users = new String [0];
        }
        //選択登録ユーザSIDを格納
        ArrayList<String> r_user_list = new ArrayList<String>();
        if (r_users != null && r_users.length > 0) {
            for (int i = 0; i < r_users.length; i++) {
                r_user_list.add(r_users[i]);
            }
        }

        if (l_users.length > 0) {
            //所属利用者リスト（下）から削除
            for (int i = 0; i < l_users.length; i++) {
                int intLeftUser = -1;
                if (l_users[i] != null && !l_users[i].equals("")) {
                    intLeftUser = Integer.parseInt(l_users[i]);
                }
                if (intLeftUser < 0) {
                    continue;
                }
                r_user_list.remove(l_users[i]);
            }

            form.setCmn230userSid2((String[]) r_user_list.toArray(new String[r_user_list.size()]));
        }
        log__.debug("END : __delUser");
    }


















    /**
     * <br>[機  能] 「上矢印」処理
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
    private ActionForward __doLeftGp(ActionMapping map,
            Cmn230Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con
            )
            throws Exception {
        log__.debug("START : __doLeft");
        __delGroup(req, form, con);
        log__.debug("END : __doLeft");
        return __doInit(map, form, req, res, con);

    }
    /**
     * <br>[機  能] 「上矢印」処理
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
    private ActionForward __doLeftBtnGp(ActionMapping map,
            Cmn230Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con
            )
            throws Exception {
        if (form.getMoveGroups() != null && !form.getMoveGroups().equals("")) {
            form.setMoveGroupSid(form.getMoveGroups().split(","));
            log__.debug("START : __doLeft");
            __delGroup(req, form, con);
            log__.debug("END : __doLeft");
            form.setMoveGroups("");
        }
        return __doInit(map, form, req, res, con);

    }
    /**
     * <br>[機  能] 「下矢印」処理
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
    private ActionForward __doRightBtnGp(ActionMapping map,
            Cmn230Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con
            )
            throws Exception {
        if (form.getMoveGroups() != null && !form.getMoveGroups().equals("")) {
            form.setMoveGroupSid(form.getMoveGroups().split(","));
            log__.debug("START : __doRight");
            __addGroup(req, form, con);
            log__.debug("END : __doRight");
            form.setMoveGroups("");
        }
        return __doInit(map, form, req, res, con);
    }
    /**
     * <br>[機  能] 「下矢印」処理
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
    private ActionForward __doRightGp(ActionMapping map,
            Cmn230Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con
            )
            throws Exception {
        log__.debug("START : __doRight");
        __addGroup(req, form, con);
        log__.debug("END : __doRight");
        return __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 「上矢印」処理
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
    private ActionForward __doLeftGp2(ActionMapping map,
            Cmn230Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con
            )
            throws Exception {
        log__.debug("START : __doLeft");
        __delGroup2(req, form, con);
        log__.debug("END : __doLeft");
        return __doInit(map, form, req, res, con);

    }
    /**
     * <br>[機  能] 「上矢印」処理
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
    private ActionForward __doLeftBtnGp2(ActionMapping map,
            Cmn230Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con
            )
            throws Exception {
        if (form.getMoveGroups() != null && !form.getMoveGroups().equals("")) {
            form.setMoveGroupSid(form.getMoveGroups().split(","));
            log__.debug("START : __doLeft");
            __delGroup2(req, form, con);
            log__.debug("END : __doLeft");
            form.setMoveGroups("");
        }
        return __doInit(map, form, req, res, con);

    }
    /**
     * <br>[機  能] 「下矢印」処理
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
    private ActionForward __doRightBtnGp2(ActionMapping map,
            Cmn230Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con
            )
            throws Exception {
        if (form.getMoveGroups() != null && !form.getMoveGroups().equals("")) {
            form.setMoveGroupSid(form.getMoveGroups().split(","));
            log__.debug("START : __doRight");
            __addGroup2(req, form, con);
            log__.debug("END : __doRight");
            form.setMoveGroups("");
        }
        return __doInit(map, form, req, res, con);
    }
    /**
     * <br>[機  能] 「下矢印」処理
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
    private ActionForward __doRightGp2(ActionMapping map,
            Cmn230Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con
            )
            throws Exception {
        log__.debug("START : __doRight");
        __addGroup2(req, form, con);
        log__.debug("END : __doRight");
        return __doInit(map, form, req, res, con);
    }

    /**
     * <br>ユーザを選択ユーザ一覧へ追加します。
     * @param req リクエスト
     * @param con コネクション
     * @param form フォーム
     * @throws Exception  実行例外
     */
    private void __addGroup(HttpServletRequest req,
            Cmn230Form form,
            Connection con
            ) throws Exception {
        log__.debug("START : __addGroup");
        //未所属利用者リスト（上）からパラメータを取得
        log__.debug("__addUser 未所属利用者リスト（上）からパラメータを取得");
        String[] r_groups = form.getCmn230groupSidadd();

        String[] l_groups = form.getMoveGroupSid();

        if (l_groups == null) {
            l_groups = new String [0];
        }
        //選択登録ユーザSIDを格納
        ArrayList<String> r_group_list = new ArrayList<String>();
        if (r_groups != null && r_groups.length > 0) {
            for (int i = 0; i < r_groups.length; i++) {
                r_group_list.add(r_groups[i]);
            }
        }

        if (l_groups.length > 0) {
            //所属利用者リスト（下）へ追加
            for (int i = 0; i < l_groups.length; i++) {
                int intLeftGroup = -1;
                if (l_groups[i] != null && !l_groups[i].equals("")) {
                    intLeftGroup = Integer.parseInt(l_groups[i]);
                }
                if (intLeftGroup < 0) {
                    continue;
                }
                //選択ユーザ有り
                boolean exist = false;
                if (r_groups != null && r_groups.length > 0) {
                    for (int j = 0; j < r_groups.length; j++) {
                        //同一ユーザSIDか判定
                        if (!r_groups[j].equals("")) {
                            if (Integer.parseInt(r_groups[j]) == intLeftGroup) {
                                exist = true;
                                break;
                            }
                        }
                    }
                }
                if (!exist) {
                    r_group_list.add(l_groups[i]);
                }
            }

            form.setCmn230groupSidadd(
                    (String[]) r_group_list.toArray(new String[r_group_list.size()]));
        }

        log__.debug("END : __addGroup");
    }
    /**
     * <br>ユーザを選択ユーザ一覧へ追加します。
     * @param req リクエスト
     * @param con コネクション
     * @param form フォーム
     * @throws Exception  実行例外
     */
    private void __addGroup2(HttpServletRequest req,
            Cmn230Form form,
            Connection con
            ) throws Exception {
        log__.debug("START : __addGroup");
        //未所属利用者リスト（上）からパラメータを取得
        log__.debug("__addUser 未所属利用者リスト（上）からパラメータを取得");
        String[] r_groups = form.getCmn230groupSidadd2();

        String[] l_groups = form.getMoveGroupSid();

        if (l_groups == null) {
            l_groups = new String [0];
        }
        //選択登録ユーザSIDを格納
        ArrayList<String> r_group_list = new ArrayList<String>();
        if (r_groups != null && r_groups.length > 0) {
            for (int i = 0; i < r_groups.length; i++) {
                r_group_list.add(r_groups[i]);
            }
        }

        if (l_groups.length > 0) {
            //所属利用者リスト（下）へ追加
            for (int i = 0; i < l_groups.length; i++) {
                int intLeftGroup = -1;
                if (l_groups[i] != null && !l_groups[i].equals("")) {
                    intLeftGroup = Integer.parseInt(l_groups[i]);
                }
                if (intLeftGroup < 0) {
                    continue;
                }
                //選択ユーザ有り
                boolean exist = false;
                if (r_groups != null && r_groups.length > 0) {
                    for (int j = 0; j < r_groups.length; j++) {
                        //同一ユーザSIDか判定
                        if (!r_groups[j].equals("")) {
                            if (Integer.parseInt(r_groups[j]) == intLeftGroup) {
                                exist = true;
                                break;
                            }
                        }
                    }
                }
                if (!exist) {
                    r_group_list.add(l_groups[i]);
                }
            }

            form.setCmn230groupSidadd2(
                    (String[]) r_group_list.toArray(new String[r_group_list.size()]));
        }

        log__.debug("END : __addUser");
    }
    /**
     * <br>選択ユーザ一覧から選択ユーザを削除します。
     * @param req リクエスト
     * @param form フォーム
     * @param con コネクション
     * @throws Exception 例外
     */
    private void __delGroup(
    HttpServletRequest req,
    Cmn230Form form,
    Connection con
    ) throws Exception {

        log__.debug("START : __delgroup");
        //未所属利用者リスト（上）からパラメータを取得
        log__.debug("__addgroup 未所属利用者リスト（右）からパラメータを取得");
        String[] r_groups = form.getCmn230groupSidadd();

        String[] l_groups = form.getMoveGroupSid();

        if (l_groups == null) {
            l_groups = new String [0];
        }
        //選択登録ユーザSIDを格納
        ArrayList<String> r_group_list = new ArrayList<String>();
        if (r_groups != null && r_groups.length > 0) {
            for (int i = 0; i < r_groups.length; i++) {
                r_group_list.add(r_groups[i]);
            }
        }

        if (l_groups.length > 0) {
            //所属利用者リスト（下）から削除
            for (int i = 0; i < l_groups.length; i++) {
                int intLeftgroup = -1;
                if (l_groups[i] != null && !l_groups[i].equals("")) {
                    intLeftgroup = Integer.parseInt(l_groups[i]);
                }
                if (intLeftgroup < 0) {
                    continue;
                }
                r_group_list.remove(l_groups[i]);
            }

            form.setCmn230groupSidadd(
                    (String[]) r_group_list.toArray(new String[r_group_list.size()]));
        }
        log__.debug("END : __delgroup");
    }
    /**
     * <br>選択ユーザ一覧から選択ユーザを削除します。
     * @param req リクエスト
     * @param form フォーム
     * @param con コネクション
     * @throws Exception 例外
     */
    private void __delGroup2(
    HttpServletRequest req,
    Cmn230Form form,
    Connection con
    ) throws Exception {

        log__.debug("START : __delgroup");
        //未所属利用者リスト（上）からパラメータを取得
        log__.debug("__addgroup 未所属利用者リスト（右）からパラメータを取得");
        String[] r_groups = form.getCmn230groupSidadd2();

        String[] l_groups = form.getMoveGroupSid();

        if (l_groups == null) {
            l_groups = new String [0];
        }
        //選択登録ユーザSIDを格納
        ArrayList<String> r_group_list = new ArrayList<String>();
        if (r_groups != null && r_groups.length > 0) {
            for (int i = 0; i < r_groups.length; i++) {
                r_group_list.add(r_groups[i]);
            }
        }

        if (l_groups.length > 0) {
            //所属利用者リスト（下）から削除
            for (int i = 0; i < l_groups.length; i++) {
                int intLeftgroup = -1;
                if (l_groups[i] != null && !l_groups[i].equals("")) {
                    intLeftgroup = Integer.parseInt(l_groups[i]);
                }
                if (intLeftgroup < 0) {
                    continue;
                }
                r_group_list.remove(l_groups[i]);
            }

            form.setCmn230groupSidadd2(
                    (String[]) r_group_list.toArray(new String[r_group_list.size()]));
        }
        log__.debug("END : __delgroup");
    }

    /**
     * <p>詳細検索
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行例外
     * @return アクションフォーワード
     */
    private ActionForward __doSearch(ActionMapping map, Cmn230Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        log__.debug("詳細検索");

        //検索済にする
        form.setCmn230SearchFlg(1);

        return __doInit(map, form, req, res, con);

    }
}