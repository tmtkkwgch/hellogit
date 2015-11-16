package jp.groupsession.v2.usr.usr011;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.groupsession.v2.cmn.dao.SltUserPerGroupModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnGroupmModel;
import jp.groupsession.v2.usr.GSValidateUser;
import jp.groupsession.v2.usr.usr010.Usr010Form;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] メイン 管理者設定 グループ登録画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
@SuppressWarnings("all")
public class Usr011Form extends Usr010Form {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Usr011Form.class);

    /** グループID */
    private String usr011gpId__ = null;
    /** グループ名 */
    private String usr011gpname__ = null;
    /** グループ名カナ */
    private String usr011gpnameKana__ = null;
    /** コメント */
    private String usr011com__ = null;
    /** グループID */
    private String usr011gpIdDel__ = null;
    /** グループ名 */
    private String usr011gpnameDel__ = null;
    /** グループ名カナ */
    private String usr011gpnameKanaDel__ = null;
    /** コメント */
    private String usr011comDel__ = null;
    /** コメント(html表示形式) */
    private String usr011comHtml__ = null;

    /** グループSID */
    private int usr011grpsid__ = -1;

    /** 未所属利用者セレクトボックス・グループ */
    private ArrayList<CmnGroupmModel> usr011cbxgroup__ = null;
    /** 未所属利用者セレクトエリア・利用者 */
    private ArrayList<SltUserPerGroupModel> usr011tarUnbelongingUser__ = null;
    /** 所属利用者セレクトエリア・利用者 */
    private List<SltUserPerGroupModel> usr011tarBelongingUser__ = null;
    /** グループコンボ*/
    private int slt_group__ = -1;
    /** 所属利用者ModelのList*/
    private ArrayList leftUserList__ = null;
    /** 未所属利用者用利用者リスト情報 */
    private ArrayList rightUserList__ = null;

    /** セーブユーザーリスト */
    private String[] sv_users__ = null;
    /** 変更前セーブユーザーリスト */
    private String[] sv_Bfusers__ = null;
    /** ユーザーリスト（所属）*/
    private String[] users_l__ = null;
    /** ユーザーリスト（未所属）*/
    private String[] users_r__ = null;

    /** 削除ボタン表示制御フラグ */
    private String usr011DelButton__ = null;
    /** 初期表示フラグ */
    private String usr011grpOnce__ = null;

    /** 選択グループSID*/
    private int selectgroup__ = -1;
    /** 選択可能階層レベル */
    private String selectLevel__ = null;
    /** 選択不可なグループSIDのリスト */
    private String disabledGroups__ = null;

    /** 処理削除区分 */
    private String usr011DelKbn__ = null;

    /** 管理者(管理者) */
    private ArrayList<SltUserPerGroupModel> usr011BelongingUserKr__ = null;
    /** 所属利用者(管理者) */
    private ArrayList<SltUserPerGroupModel> usr011UnBelongingUserKr__ = null;
    /** ユーザーリスト(管理者 管理者)*/
    private String[] usersKr_l__ = null;
    /** ユーザーリスト(管理者 所属利用者)*/
    private String[] usersKr_r__ = null;
    /** セーブユーザーリスト */
    private String[] sv_usersKr__ = null;

    /** ヘルプモード */
    private int usr011hrpPrm__ = 0;

    /** グループコンボ */
    private List<LabelValueBean> groupCombo__ = null;

    /**
     * <p>usr011hrpPrm を取得します。
     * @return usr011hrpPrm
     */
    public int getUsr011hrpPrm() {
        return usr011hrpPrm__;
    }
    /**
     * <p>usr011hrpPrm をセットします。
     * @param usr011hrpPrm usr011hrpPrm
     */
    public void setUsr011hrpPrm(int usr011hrpPrm) {
        usr011hrpPrm__ = usr011hrpPrm;
    }
    /**
     * @return usr011comDel__ を戻します。
     */
    public String getUsr011comDel() {
        return usr011comDel__;
    }
    /**
     * @param usr011comDel 設定する usr011comDel__。
     */
    public void setUsr011comDel(String usr011comDel) {
        usr011comDel__ = usr011comDel;
    }
    /**
     * @return usr011gpnameDel__ を戻します。
     */
    public String getUsr011gpnameDel() {
        return usr011gpnameDel__;
    }
    /**
     * @param usr011gpnameDel 設定する usr011gpnameDel__。
     */
    public void setUsr011gpnameDel(String usr011gpnameDel) {
        usr011gpnameDel__ = usr011gpnameDel;
    }
    /**
     * @return usr011gpnameKanaDel__ を戻します。
     */
    public String getUsr011gpnameKanaDel() {
        return usr011gpnameKanaDel__;
    }
    /**
     * @param usr011gpnameKanaDel 設定する usr011gpnameKanaDel__。
     */
    public void setUsr011gpnameKanaDel(String usr011gpnameKanaDel) {
        usr011gpnameKanaDel__ = usr011gpnameKanaDel;
    }
    /**
     * @return usr011comHtml__ を戻します。
     */
    public String getUsr011comHtml() {
        return usr011comHtml__;
    }
    /**
     * @param usr011comHtml 設定する usr011comHtml__。
     */
    public void setUsr011comHtml(String usr011comHtml) {
        usr011comHtml__ = usr011comHtml;
    }
    /**
     * @return sv_usersKr__ を戻します。
     */
    public String[] getSv_usersKr() {
        return sv_usersKr__;
    }
    /**
     * @param svUusersKr 設定する sv_usersKr__。
     */
    public void setSv_usersKr(String[] svUusersKr) {
        sv_usersKr__ = svUusersKr;
    }
    /**
     * @return usersKr_l__ を戻します。
     */
    public String[] getUsersKr_l() {
        return usersKr_l__;
    }
    /**
     * @param usersKrL 設定する usersKr_l__。
     */
    public void setUsersKr_l(String[] usersKrL) {
        usersKr_l__ = usersKrL;
    }
    /**
     * @return usersKr_r__ を戻します。
     */
    public String[] getUsersKr_r() {
        return usersKr_r__;
    }
    /**
     * @param usersKrR 設定する usersKr_r__。
     */
    public void setUsersKr_r(String[] usersKrR) {
        usersKr_r__ = usersKrR;
    }
    /**
     * @return usr011BelongingUserKr__ を戻します。
     */
    public List<SltUserPerGroupModel> getUsr011BelongingUserKr() {
        return usr011BelongingUserKr__;
    }
    /**
     * @param usr011BelongingUserKr 設定する usr011BelongingUserKr__。
     */
    public void setUsr011BelongingUserKr(
            ArrayList<SltUserPerGroupModel> usr011BelongingUserKr) {
        usr011BelongingUserKr__ = usr011BelongingUserKr;
    }
    /**
     * @return usr011UnBelongingUserKr__ を戻します。
     */
    public List<SltUserPerGroupModel> getUsr011UnBelongingUserKr() {
        return usr011UnBelongingUserKr__;
    }
    /**
     * @param usr011UnBelongingUserKr 設定する usr011UnBelongingUserKr__。
     */
    public void setUsr011UnBelongingUserKr(
            ArrayList<SltUserPerGroupModel> usr011UnBelongingUserKr) {
        usr011UnBelongingUserKr__ = usr011UnBelongingUserKr;
    }
    /**
     * @return usr011DelKbn を戻します。
     */
    public String getUsr011DelKbn() {
        return usr011DelKbn__;
    }
    /**
     * @param usr011DelKbn 設定する usr011DelKbn。
     */
    public void setUsr011DelKbn(String usr011DelKbn) {
        usr011DelKbn__ = usr011DelKbn;
    }
    /**
     * @return disabledGroups を戻します。
     */
    public String getDisabledGroups() {
        return disabledGroups__;
    }
    /**
     * @param disabledGroups 設定する disabledGroups。
     */
    public void setDisabledGroups(String disabledGroups) {
        disabledGroups__ = disabledGroups;
    }
    /**
     * @return select_group を戻します。
     */
    public int getSelectgroup() {
        return selectgroup__;
    }
    /**
     * @param selectgroup 設定する selectgroup。
     */
    public void setSelectgroup(int selectgroup) {
        selectgroup__ = selectgroup;
    }
    /**
     * @return selectLevel を戻します。
     */
    public String getSelectLevel() {
        return selectLevel__;
    }
    /**
     * @param selectLevel 設定する selectLevel。
     */
    public void setSelectLevel(String selectLevel) {
        selectLevel__ = selectLevel;
    }
    /**
     * <p>コメントを取得します。
     * @return コメント
     */
    public String getUsr011com() {
        return usr011com__;
    }
    /**
     * <p>コメントをセットします。
     * @param usr011com コメント
     */
    public void setUsr011com(String usr011com) {
        usr011com__ = usr011com;
    }

    /**
     * <p>グループ名を取得します。
     * @return グループ名
     */
    public String getUsr011gpname() {
        return usr011gpname__;
    }

    /**
     * <p>グループ名をセットします。
     * @param usr011gpname グループ名
     */
    public void setUsr011gpname(String usr011gpname) {
        usr011gpname__ = usr011gpname;
    }
    /**
     * <p>グループ名カナを取得します。
     * @return グループ名カナ
     */
    public String getUsr011gpnameKana() {
        return usr011gpnameKana__;
    }
    /**
     * <p>グループ名カナをセットします。
     * @param usr011gpnameKana グループ名カナ
     */
    public void setUsr011gpnameKana(String usr011gpnameKana) {
        usr011gpnameKana__ = usr011gpnameKana;
    }

    /**
     * <p>グループSIDを取得します。
     * @return グループ名カナ
     */
    public int getUsr011grpsid() {
        return usr011grpsid__;
    }
    /**
     * <p>グループSIDをセットします。
     * @param usr011grpsid グループ名カナ
     */
    public void setUsr011grpsid(int usr011grpsid) {
        usr011grpsid__ = usr011grpsid;
    }

    /**
     * <p>グループコンボボックスの値を取得します。
     * @return グループコンボボックス値
     */
    public ArrayList<CmnGroupmModel> getUsr011cbxgroup() {
        return usr011cbxgroup__;
    }

    /**
     * <p>グループコンボボックスで使用する値をセットします。
     * @param usr011cbxgroup グループコンボボックス値
     */
    public void setUsr011cbxgroup(ArrayList<CmnGroupmModel> usr011cbxgroup) {
        usr011cbxgroup__ = usr011cbxgroup;
    }

    /**
     * <p>ユーザーテキストエリアの値を取得します。
     * @return ユーザーテキストエリア値
     */
    public ArrayList<SltUserPerGroupModel> getUsr011tarUnbelongingUser() {
        return usr011tarUnbelongingUser__;
    }

    /**
     * <p>ユーザーテキストエリアで使用する値をセットします。
     * @param usr011tarUnbelongingUser ユーザーテキストエリア値
     */
    public void setUsr011tarUnbelongingUser(
            ArrayList<SltUserPerGroupModel> usr011tarUnbelongingUser) {
        usr011tarUnbelongingUser__ = usr011tarUnbelongingUser;
    }

    /**
     * <p>ユーザーテキストエリアの値を取得します。
     * @return ユーザーテキストエリア値
     */
    public List<SltUserPerGroupModel> getUsr011tarBelongingUser() {
        return usr011tarBelongingUser__;
    }

    /**
     * <p>ユーザーテキストエリアで使用する値をセットします。
     * @param usr011tarBelongingUser ユーザーテキストエリア値
     */
    public void setUsr011tarBelongingUser(
            List<SltUserPerGroupModel> usr011tarBelongingUser) {
        usr011tarBelongingUser__ = usr011tarBelongingUser;
    }

    /**
     * グループコンボの値を取得します。
     * @return int
     */
    public int getSlt_group() {
        return slt_group__;
    }

    /**
     * グループコンボの値を設定します。
     * @param i グループSID
     */
    public void setSlt_group(int i) {
        slt_group__ = i;
    }

    /**
     * 所属利用者情報を取得します。
     * @return ArrayList
     */
    public ArrayList getLeftUserList() {
        return leftUserList__;
    }
    /**
     * 所属利用者情報を設定します。
     * @param list 所属利用者情報
     */
    public void setLeftUserList(ArrayList list) {
        leftUserList__ = list;
    }

    /**
     * 未所属利用者情報を取得します。
     * @return ArrayList
     */
    public ArrayList getRightUserList() {
        return rightUserList__;
    }

    /**
     * 未所属利用者情報を設定します。
     * @param list 未所属利用者情報
     */
    public void setRightUserList(ArrayList list) {
        rightUserList__ = list;
    }

    /**
     * @return sv_users__ を戻します。
     */
    public String[] getSv_users() {
        return sv_users__;
    }
    /**
     * @param svUsers 設定する sv_users__。
     */
    public void setSv_users(String[] svUsers) {
        this.sv_users__ = svUsers;
    }

    /**
     * @return users_l__ を戻します。
     */
    public String[] getUsers_l() {
        return users_l__;
    }
    /**
     * @param usersL 設定する svusers_l__。
     */
    public void setUsers_l(String[] usersL) {
        this.users_l__ = usersL;
    }

    /**
     * @return users_r__ を戻します。
     */
    public String[] getUsers_r() {
        return users_r__;
    }
    /**
     * @param usersR 設定する users_r__。
     */
    public void setUsers_r(String[] usersR) {
        users_r__ = usersR;
    }
    /**
     * @return usr011grpOnce__ を戻します。
     */
    public String getUsr011grpOnce() {
        return usr011grpOnce__;
    }
    /**
     * @param usr011grpOnce 設定する usr011grpOnce__。
     */
    public void setUsr011grpOnce(String usr011grpOnce) {
        this.usr011grpOnce__ = usr011grpOnce;
    }

    /**
     * @return usr011DelButton__ を戻します。
     */
    public String getUsr011DelButton() {
        return usr011DelButton__;
    }
    /**
     * @param usr011DelButton 設定する usr011DelButton__。
     */
    public void setUsr011DelButton(String usr011DelButton) {
        this.usr011DelButton__ = usr011DelButton;
    }
    /**
     * <p>usr011gpId を取得します。
     * @return usr011gpId
     */
    public String getUsr011gpId() {
        return usr011gpId__;
    }
    /**
     * <p>usr011gpId をセットします。
     * @param usr011gpId usr011gpId
     */
    public void setUsr011gpId(String usr011gpId) {
        usr011gpId__ = usr011gpId;
    }

    /**
     * <br>[機  能] ＯＫボタン押下時(追加)の入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param reqMdl RequestModel
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     * @return エラー
     */
    public ActionErrors validateAdd(ActionMapping map, RequestModel reqMdl, Connection con)
    throws SQLException {

        ActionErrors errors = new ActionErrors();
        GSValidateUser gsValidate = new GSValidateUser(reqMdl);
        //共通チェック
        int svErrorSize = errors.size();
        //グループID
        gsValidate.validateGroupId(errors, usr011gpId__);
        if (svErrorSize == errors.size()) {
            GSValidateUser.validateGroupIdDouble(errors, usr011grpsid__, usr011gpId__, con, reqMdl);
        }
        //グループ名
        gsValidate.validateGroupName(errors, usr011gpname__);
        //グループ名カナ
        gsValidate.validateGroupNameKana(errors, usr011gpnameKana__);
        //コメント
        gsValidate.validateGroupComment(errors, usr011com__);

        return errors;
    }

    /**
     * <br>[機  能] ＯＫボタン押下時(修正)の入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param reqMdl RequestModel
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     * @return エラー
     */
    public ActionErrors validateEdit(ActionMapping map, RequestModel reqMdl, Connection con)
    throws SQLException {
        return validateAdd(map, reqMdl, con);
    }

    /**
     * <br>[機  能] ＯＫボタン押下時(削除)の入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param reqMdl RequestModel
     * @param con コネクション
     * @return エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateDell(ActionMapping map,
                                      RequestModel reqMdl,
                                      Connection con)
        throws SQLException {

        ActionErrors errors = new ActionErrors();
        GSValidateUser gsValidate = new GSValidateUser(reqMdl);
        //管理者
        GSValidateUser.validateDeleteAdmin(errors, usr011grpsid__, reqMdl);
        //階層の末端か
        GSValidateUser.validateIsLastGroup(errors, con, getUsr010grpSid());

        if (errors.isEmpty()) {
            //所属ユーザ
            gsValidate.validateBelongUser(errors, sv_users__);
        }

        return errors;
    }

    /**
     * <br>[機  能] 「＞」押下時の入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param reqMdl RequestModel
     * @param con コネクション
     * @param usersL ユーザ所属リスト
     * @return エラー
     * @throws Exception 実行時例外
     */
    public ActionErrors validateArrowRight(
            ActionMapping map,
            RequestModel reqMdl,
            Connection con,
            String[] usersL) throws Exception {
        log__.debug("START");

        ActionErrors errors = new ActionErrors();

        log__.debug("選択ユーザがデフォルトグループかのチェック");
        GSValidateUser.validateShozokuDefaultUser(errors, con, usersL, usr011grpsid__, reqMdl);

        log__.debug("END");
        return errors;
    }

    /**
     * <br>[機  能] 「＜」押下時の入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param req リクエスト
     * @return エラー
     */
    public ActionErrors validateArrowLeft(ActionMapping map, HttpServletRequest req) {
        log__.debug("START");

        ActionErrors errors = new ActionErrors();

        log__.debug("END");
        return errors;
    }
    /**
     * <p>usr011gpIdDel を取得します。
     * @return usr011gpIdDel
     */
    public String getUsr011gpIdDel() {
        return usr011gpIdDel__;
    }
    /**
     * <p>usr011gpIdDel をセットします。
     * @param usr011gpIdDel usr011gpIdDel
     */
    public void setUsr011gpIdDel(String usr011gpIdDel) {
        usr011gpIdDel__ = usr011gpIdDel;
    }
    /**
     * <p>groupCombo を取得します。
     * @return groupCombo
     */
    public List<LabelValueBean> getGroupCombo() {
        return groupCombo__;
    }
    /**
     * <p>groupCombo をセットします。
     * @param groupCombo groupCombo
     */
    public void setGroupCombo(List<LabelValueBean> groupCombo) {
        groupCombo__ = groupCombo;
    }
    /**
     * <p>sv_Bfusers を取得します。
     * @return sv_Bfusers
     */
    public String[] getSv_Bfusers() {
        return sv_Bfusers__;
    }
    /**
     * <p>sv_Bfusers をセットします。
     * @param svBfusers sv_Bfusers
     */
    public void setSv_Bfusers(String[] svBfusers) {
        sv_Bfusers__ = svBfusers;
    }
}