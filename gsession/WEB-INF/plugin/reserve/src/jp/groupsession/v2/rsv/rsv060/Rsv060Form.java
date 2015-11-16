package jp.groupsession.v2.rsv.rsv060;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.GSValidateUtil;
import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.rsv.dao.RsvSisGrpDao;
import jp.groupsession.v2.rsv.model.RsvSisDataModel;
import jp.groupsession.v2.rsv.model.RsvSisGrpModel;
import jp.groupsession.v2.rsv.rsv050.Rsv050Form;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 施設予約 施設グループ登録画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv060Form extends Rsv050Form {

    /** 初期表示フラグ */
    private boolean rsv060InitFlg__ = true;
    /** 処理モード */
    private String rsv060ProcMode__ = getRsv060ProcMode();
    /** 修正対象の施設グループSID */
    private int rsv060EditGrpSid__ = -1;
    /** 施設グループ名 */
    private String rsv060GrpName__ = null;
    /** 施設グループID */
    private String rsv060GrpId__ = null;
    /** 施設区分選択値 */
    private int rsv060SelectedSisetuKbn__ = -1;
    /** 施設区分コンボリスト */
    private ArrayList<LabelValueBean> rsv060SisetuLabelList__ = null;
    /** 管理者ユーザセーブ */
    private String[] saveUser__ = null;
    /** 選択済ユーザ(左) */
    private String[] rsv060SelectedLeft__ = null;
    /** セレクトエリア(左) */
    private ArrayList<LabelValueBean> rsv060AdmUser__ = null;
    /** 選択済ユーザ(右) */
    private String[] rsv060SelectedRight__ = null;
    /** セレクトエリア(右) */
    private ArrayList<LabelValueBean> rsv060NotAdmUser__ = null;
    /** グループコンボリスト */
    private ArrayList<LabelValueBean> rsv060GrpLabelList__ = null;
    /** グループコンボ選択値 */
    private int rsv060SelectedGrpComboSid__ = -1;
    /** 権限設定 */
    private int rsv060GrpAdmKbn__ = -1;
    /** 所属施設リスト */
    private ArrayList<RsvSisDataModel> rsv060SyozokuList__ = null;
    /** データ存在フラグ */
    private boolean rsv060DataExists__ = true;
    /** 施設予約の承認 */
    private int rsv060apprKbn__ = 0;

    /** グループSID */
    private int rsv060groupSid__ = Integer.parseInt(GSConstReserve.GROUP_COMBO_VALUE);
    /** メンバーSID 予約可能 */
    private String[] rsv060memberSid__ = new String[0];
    /** メンバーSID 閲覧 */
    private String[] rsv060memberSidRead__ = new String[0];
    /** グループ一覧 */
    private List < LabelValueBean > rsv060GroupList__ = null;
    /** 追加済みユーザ一覧 */
    private List < LabelValueBean > rsv060LeftUserList__ = null;
    /** 追加済みユーザ一覧 */
    private List < LabelValueBean > rsv060LeftUserListRead__ = null;
    /** 追加用ユーザ一覧 */
    private List < LabelValueBean > rsv060RightUserList__ = null;

    /** 追加済みメンバー(選択) 予約可能 */
    private String[] rsv060SelectLeftUser__ = new String[0];
    /** 追加済みメンバー(選択) 閲覧 */
    private String[] rsv060SelectLeftUserRead__ = new String[0];
    /** 追加用メンバー(選択) */
    private String[] rsv060SelectRightUser__ = new String[0];

    /** 制限方法 */
    private int rsv060AccessKbn__ = GSConstReserve.RSV_ACCESS_MODE_PERMIT;
    /** アクセス制限表示フラグ */
    private boolean rsv060AccessDspFlg__ = false;


    /**
     * <p>rsv060AdmUser を取得します。
     * @return rsv060AdmUser
     */
    public ArrayList<LabelValueBean> getRsv060AdmUser() {
        return rsv060AdmUser__;
    }
    /**
     * <p>rsv060AdmUser をセットします。
     * @param rsv060AdmUser rsv060AdmUser
     */
    public void setRsv060AdmUser(ArrayList<LabelValueBean> rsv060AdmUser) {
        rsv060AdmUser__ = rsv060AdmUser;
    }
    /**
     * <p>rsv060EditGrpSid__ を取得します。
     * @return rsv060EditGrpSid
     */
    public int getRsv060EditGrpSid() {
        return rsv060EditGrpSid__;
    }
    /**
     * <p>rsv060EditGrpSid__ をセットします。
     * @param rsv060EditGrpSid rsv060EditGrpSid__
     */
    public void setRsv060EditGrpSid(int rsv060EditGrpSid) {
        rsv060EditGrpSid__ = rsv060EditGrpSid;
    }
    /**
     * <p>rsv060GrpAdmKbn__ を取得します。
     * @return rsv060GrpAdmKbn
     */
    public int getRsv060GrpAdmKbn() {
        return rsv060GrpAdmKbn__;
    }
    /**
     * <p>rsv060GrpAdmKbn__ をセットします。
     * @param rsv060GrpAdmKbn rsv060GrpAdmKbn__
     */
    public void setRsv060GrpAdmKbn(int rsv060GrpAdmKbn) {
        rsv060GrpAdmKbn__ = rsv060GrpAdmKbn;
    }
    /**
     * <p>rsv060GrpLabelList__ を取得します。
     * @return rsv060GrpLabelList
     */
    public ArrayList<LabelValueBean> getRsv060GrpLabelList() {
        return rsv060GrpLabelList__;
    }
    /**
     * <p>rsv060GrpLabelList__ をセットします。
     * @param rsv060GrpLabelList rsv060GrpLabelList__
     */
    public void setRsv060GrpLabelList(ArrayList<LabelValueBean> rsv060GrpLabelList) {
        rsv060GrpLabelList__ = rsv060GrpLabelList;
    }
    /**
     * <p>rsv060GrpName__ を取得します。
     * @return rsv060GrpName
     */
    public String getRsv060GrpName() {
        return rsv060GrpName__;
    }
    /**
     * <p>rsv060GrpName__ をセットします。
     * @param rsv060GrpName rsv060GrpName__
     */
    public void setRsv060GrpName(String rsv060GrpName) {
        rsv060GrpName__ = rsv060GrpName;
    }
    /**
     * <p>rsv060InitFlg__ を取得します。
     * @return rsv060InitFlg
     */
    public boolean isRsv060InitFlg() {
        return rsv060InitFlg__;
    }
    /**
     * <p>rsv060InitFlg__ をセットします。
     * @param rsv060InitFlg rsv060InitFlg__
     */
    public void setRsv060InitFlg(boolean rsv060InitFlg) {
        rsv060InitFlg__ = rsv060InitFlg;
    }
    /**
     * <p>rsv060NotAdmUser__ を取得します。
     * @return rsv060NotAdmUser
     */
    public ArrayList<LabelValueBean> getRsv060NotAdmUser() {
        return rsv060NotAdmUser__;
    }
    /**
     * <p>rsv060NotAdmUser__ をセットします。
     * @param rsv060NotAdmUser rsv060NotAdmUser__
     */
    public void setRsv060NotAdmUser(ArrayList<LabelValueBean> rsv060NotAdmUser) {
        rsv060NotAdmUser__ = rsv060NotAdmUser;
    }
    /**
     * <p>rsv060ProcMode__ を取得します。
     * @return rsv060ProcMode
     */
    public String getRsv060ProcMode() {
        return rsv060ProcMode__;
    }
    /**
     * <p>rsv060ProcMode__ をセットします。
     * @param rsv060ProcMode rsv060ProcMode__
     */
    public void setRsv060ProcMode(String rsv060ProcMode) {
        rsv060ProcMode__ = rsv060ProcMode;
    }
    /**
     * <p>rsv060SelectedGrpComboSid__ を取得します。
     * @return rsv060SelectedGrpComboSid
     */
    public int getRsv060SelectedGrpComboSid() {
        return rsv060SelectedGrpComboSid__;
    }
    /**
     * <p>rsv060SelectedGrpComboSid__ をセットします。
     * @param rsv060SelectedGrpComboSid rsv060SelectedGrpComboSid__
     */
    public void setRsv060SelectedGrpComboSid(int rsv060SelectedGrpComboSid) {
        rsv060SelectedGrpComboSid__ = rsv060SelectedGrpComboSid;
    }
    /**
     * <p>rsv060SelectedLeft__ を取得します。
     * @return rsv060SelectedLeft
     */
    public String[] getRsv060SelectedLeft() {
        return rsv060SelectedLeft__;
    }
    /**
     * <p>rsv060SelectedLeft__ をセットします。
     * @param rsv060SelectedLeft rsv060SelectedLeft__
     */
    public void setRsv060SelectedLeft(String[] rsv060SelectedLeft) {
        rsv060SelectedLeft__ = rsv060SelectedLeft;
    }
    /**
     * <p>rsv060SelectedRight__ を取得します。
     * @return rsv060SelectedRight
     */
    public String[] getRsv060SelectedRight() {
        return rsv060SelectedRight__;
    }
    /**
     * <p>rsv060SelectedRight__ をセットします。
     * @param rsv060SelectedRight rsv060SelectedRight__
     */
    public void setRsv060SelectedRight(String[] rsv060SelectedRight) {
        rsv060SelectedRight__ = rsv060SelectedRight;
    }
    /**
     * <p>rsv060SelectedSisetuKbn__ を取得します。
     * @return rsv060SelectedSisetuKbn
     */
    public int getRsv060SelectedSisetuKbn() {
        return rsv060SelectedSisetuKbn__;
    }
    /**
     * <p>rsv060SelectedSisetuKbn__ をセットします。
     * @param rsv060SelectedSisetuKbn rsv060SelectedSisetuKbn__
     */
    public void setRsv060SelectedSisetuKbn(int rsv060SelectedSisetuKbn) {
        rsv060SelectedSisetuKbn__ = rsv060SelectedSisetuKbn;
    }
    /**
     * <p>rsv060SisetuLabelList__ を取得します。
     * @return rsv060SisetuLabelList
     */
    public ArrayList<LabelValueBean> getRsv060SisetuLabelList() {
        return rsv060SisetuLabelList__;
    }
    /**
     * <p>rsv060SisetuLabelList__ をセットします。
     * @param rsv060SisetuLabelList rsv060SisetuLabelList__
     */
    public void setRsv060SisetuLabelList(
            ArrayList<LabelValueBean> rsv060SisetuLabelList) {
        rsv060SisetuLabelList__ = rsv060SisetuLabelList;
    }
    /**
     * <p>rsv060SyozokuList__ を取得します。
     * @return rsv060SyozokuList
     */
    public ArrayList<RsvSisDataModel> getRsv060SyozokuList() {
        return rsv060SyozokuList__;
    }
    /**
     * <p>rsv060SyozokuList__ をセットします。
     * @param rsv060SyozokuList rsv060SyozokuList__
     */
    public void setRsv060SyozokuList(ArrayList<RsvSisDataModel> rsv060SyozokuList) {
        rsv060SyozokuList__ = rsv060SyozokuList;
    }
    /**
     * <p>saveUser__ を取得します。
     * @return saveUser
     */
    public String[] getSaveUser() {
        return saveUser__;
    }
    /**
     * <p>saveUser__ をセットします。
     * @param saveUser saveUser__
     */
    public void setSaveUser(String[] saveUser) {
        saveUser__ = saveUser;
    }
    /**
     * <p>rsv060DataExists__ を取得します。
     * @return rsv060DataExists
     */
    public boolean isRsv060DataExists() {
        return rsv060DataExists__;
    }
    /**
     * <p>rsv060DataExists__ をセットします。
     * @param rsv060DataExists rsv060DataExists__
     */
    public void setRsv060DataExists(boolean rsv060DataExists) {
        rsv060DataExists__ = rsv060DataExists;
    }
    /**
     * <p>rsv060GrpId を取得します。
     * @return rsv060GrpId
     */
    public String getRsv060GrpId() {
        return rsv060GrpId__;
    }
    /**
     * <p>rsv060GrpId をセットします。
     * @param rsv060GrpId rsv060GrpId
     */
    public void setRsv060GrpId(String rsv060GrpId) {
        rsv060GrpId__ = rsv060GrpId;
    }

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param req リクエスト
     * @return errors エラー
     * @throws SQLException SQL実行例外
     */
    public ActionErrors validateCheck(Connection con, HttpServletRequest req) throws SQLException {

        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;
        GsMessage gsMsg = new GsMessage();
        //施設グループ名 未入力チェック
        if (StringUtil.isNullZeroString(rsv060GrpName__)) {
            msg =
                new ActionMessage("error.input.required.text",
                        gsMsg.getMessage(req, "cmn.group.name"));
            StrutsUtil.addMessage(errors, msg, "rsv060GrpName");
        } else {

            //施設グループ名 桁数チェック
            if (rsv060GrpName__.length() > GSConstReserve.MAX_LENGTH_GROUP_NAME
                    && errors.size() == 0) {
                msg =
                    new ActionMessage("error.input.length.text",
                            gsMsg.getMessage(req, "cmn.group.name"),
                                    String.valueOf(GSConstReserve.MAX_LENGTH_GROUP_NAME));
                StrutsUtil.addMessage(errors, msg, "rsv060GrpName");
            }

            //施設グループ名 スペースのみチェック
            if (ValidateUtil.isSpace(rsv060GrpName__) && errors.size() == 0) {
                msg = new ActionMessage("error.input.spase.only",
                        gsMsg.getMessage(req, "cmn.group.name"));
                StrutsUtil.addMessage(errors, msg, "rsv060GrpName__");
            }

            //施設グループ名 先頭スペースチェック
            if (ValidateUtil.isSpaceStart(rsv060GrpName__) && errors.size() == 0) {
                msg = new ActionMessage("error.input.spase.start",
                        gsMsg.getMessage(req, "cmn.group.name"));
                StrutsUtil.addMessage(errors, msg, "rsv060GrpName");
            }

            if (ValidateUtil.isTab(rsv060GrpName__)) {
                //タブ文字が含まれている
                String msgKey = "error.input.tab.text";
                msg = new ActionMessage(msgKey, 
                        gsMsg.getMessage(req, "cmn.group.name"));
                StrutsUtil.addMessage(errors, msg, "rsv060GrpName" + msgKey);
            }
            
            //施設グループ名 JIS第2水準チェック
            if (!GSValidateUtil.isGsJapaneaseString(rsv060GrpName__) && errors.size() == 0) {
                //利用不可能な文字を入力した場合
                String nstr = GSValidateUtil.getNotGsJapaneaseString(rsv060GrpName__);
                msg =
                    new ActionMessage("error.input.njapan.text",
                            gsMsg.getMessage(req, "cmn.group.name"),
                            nstr);
                StrutsUtil.addMessage(errors, msg, "bbs030forumName");
            }
        }

        //施設グループID
        if (!StringUtil.isNullZeroString(rsv060GrpId__)) {
            RsvSisGrpDao rsgDao = new RsvSisGrpDao(con);
            RsvSisGrpModel rsgMdl = rsgDao.getGrpIdData(rsv060GrpId__, rsv060EditGrpSid__);

            //重複チェック
            if (rsgMdl != null) {
                msg =
                    new ActionMessage("error.input.double.timezone",
                            gsMsg.getMessage(req, "cmn.group.id"),
                            gsMsg.getMessage(req, "cmn.group.id"));
                StrutsUtil.addMessage(errors, msg, "rsv060GrpId");
                return errors;
            }

            //施設グループID 桁数チェック
            if (rsv060GrpId__.length() > GSConstReserve.MAX_LENGTH_GROUP_ID) {
                msg =
                    new ActionMessage("error.input.length.text",
                            gsMsg.getMessage(req, "cmn.group.id"),
                                    String.valueOf(GSConstReserve.MAX_LENGTH_GROUP_ID));
                StrutsUtil.addMessage(errors, msg, "rsv060GrpId");
                return errors;
            }
            //施設グループID スペースのみチェック
            if (ValidateUtil.isSpace(rsv060GrpId__)) {
                msg = new ActionMessage("error.input.spase.only",
                        gsMsg.getMessage(req, "cmn.group.id"));
                StrutsUtil.addMessage(errors, msg, "rsv060GrpId__");
                return errors;
            }
            //施設グループID 先頭スペースチェック
            if (ValidateUtil.isSpaceStart(rsv060GrpId__)) {
                msg = new ActionMessage("error.input.spase.start",
                        gsMsg.getMessage(req, "cmn.group.id"));
                StrutsUtil.addMessage(errors, msg, "rsv060GrpId");
                return errors;
            }
            if (ValidateUtil.isTab(rsv060GrpId__)) {
                //タブ文字が含まれている
                String msgKey = "error.input.tab.text";
                msg = new ActionMessage(msgKey, 
                        gsMsg.getMessage(req, "cmn.group.id"));
                StrutsUtil.addMessage(errors, msg, "rsv060GrpId" + msgKey);
            }
            
            //施設グループID JIS第2水準チェック
            if (!GSValidateUtil.isGsJapaneaseString(rsv060GrpId__)) {
                //利用不可能な文字を入力した場合
                String nstr = GSValidateUtil.getNotGsJapaneaseString(rsv060GrpId__);
                msg =
                    new ActionMessage("error.input.njapan.text",
                            gsMsg.getMessage(req, "cmn.group.id"),
                            nstr);
                StrutsUtil.addMessage(errors, msg, "rsv060GrpId");
            }
        }

        return errors;
    }
    /**
     * <p>rsv060memberSid を取得します。
     * @return rsv060memberSid
     */
    public String[] getRsv060memberSid() {
        return rsv060memberSid__;
    }
    /**
     * <p>rsv060memberSid をセットします。
     * @param rsv060memberSid rsv060memberSid
     */
    public void setRsv060memberSid(String[] rsv060memberSid) {
        rsv060memberSid__ = rsv060memberSid;
    }
    /**
     * <p>rsv060memberSidRead を取得します。
     * @return rsv060memberSidRead
     */
    public String[] getRsv060memberSidRead() {
        return rsv060memberSidRead__;
    }
    /**
     * <p>rsv060memberSidRead をセットします。
     * @param rsv060memberSidRead rsv060memberSidRead
     */
    public void setRsv060memberSidRead(String[] rsv060memberSidRead) {
        rsv060memberSidRead__ = rsv060memberSidRead;
    }
    /**
     * <p>rsv060GroupList を取得します。
     * @return rsv060GroupList
     */
    public List<LabelValueBean> getRsv060GroupList() {
        return rsv060GroupList__;
    }
    /**
     * <p>rsv060GroupList をセットします。
     * @param rsv060GroupList rsv060GroupList
     */
    public void setRsv060GroupList(List<LabelValueBean> rsv060GroupList) {
        rsv060GroupList__ = rsv060GroupList;
    }
    /**
     * <p>rsv060LeftUserList を取得します。
     * @return rsv060LeftUserList
     */
    public List<LabelValueBean> getRsv060LeftUserList() {
        return rsv060LeftUserList__;
    }
    /**
     * <p>rsv060LeftUserList をセットします。
     * @param rsv060LeftUserList rsv060LeftUserList
     */
    public void setRsv060LeftUserList(List<LabelValueBean> rsv060LeftUserList) {
        rsv060LeftUserList__ = rsv060LeftUserList;
    }
    /**
     * <p>rsv060LeftUserListRead を取得します。
     * @return rsv060LeftUserListRead
     */
    public List<LabelValueBean> getRsv060LeftUserListRead() {
        return rsv060LeftUserListRead__;
    }
    /**
     * <p>rsv060LeftUserListRead をセットします。
     * @param rsv060LeftUserListRead rsv060LeftUserListRead
     */
    public void setRsv060LeftUserListRead(
            List<LabelValueBean> rsv060LeftUserListRead) {
        rsv060LeftUserListRead__ = rsv060LeftUserListRead;
    }
    /**
     * <p>rsv060RightUserList を取得します。
     * @return rsv060RightUserList
     */
    public List<LabelValueBean> getRsv060RightUserList() {
        return rsv060RightUserList__;
    }
    /**
     * <p>rsv060RightUserList をセットします。
     * @param rsv060RightUserList rsv060RightUserList
     */
    public void setRsv060RightUserList(List<LabelValueBean> rsv060RightUserList) {
        rsv060RightUserList__ = rsv060RightUserList;
    }
    /**
     * <p>rsv060SelectLeftUser を取得します。
     * @return rsv060SelectLeftUser
     */
    public String[] getRsv060SelectLeftUser() {
        return rsv060SelectLeftUser__;
    }
    /**
     * <p>rsv060SelectLeftUser をセットします。
     * @param rsv060SelectLeftUser rsv060SelectLeftUser
     */
    public void setRsv060SelectLeftUser(String[] rsv060SelectLeftUser) {
        rsv060SelectLeftUser__ = rsv060SelectLeftUser;
    }
    /**
     * <p>rsv060SelectLeftUserRead を取得します。
     * @return rsv060SelectLeftUserRead
     */
    public String[] getRsv060SelectLeftUserRead() {
        return rsv060SelectLeftUserRead__;
    }
    /**
     * <p>rsv060SelectLeftUserRead をセットします。
     * @param rsv060SelectLeftUserRead rsv060SelectLeftUserRead
     */
    public void setRsv060SelectLeftUserRead(String[] rsv060SelectLeftUserRead) {
        rsv060SelectLeftUserRead__ = rsv060SelectLeftUserRead;
    }
    /**
     * <p>rsv060SelectRightUser を取得します。
     * @return rsv060SelectRightUser
     */
    public String[] getRsv060SelectRightUser() {
        return rsv060SelectRightUser__;
    }
    /**
     * <p>rsv060SelectRightUser をセットします。
     * @param rsv060SelectRightUser rsv060SelectRightUser
     */
    public void setRsv060SelectRightUser(String[] rsv060SelectRightUser) {
        rsv060SelectRightUser__ = rsv060SelectRightUser;
    }
    /**
     * <p>rsv060AccessKbn を取得します。
     * @return rsv060AccessKbn
     */
    public int getRsv060AccessKbn() {
        return rsv060AccessKbn__;
    }
    /**
     * <p>rsv060AccessKbn をセットします。
     * @param rsv060AccessKbn rsv060AccessKbn
     */
    public void setRsv060AccessKbn(int rsv060AccessKbn) {
        rsv060AccessKbn__ = rsv060AccessKbn;
    }
    /**
     * <p>rsv060groupSid を取得します。
     * @return rsv060groupSid
     */
    public int getRsv060groupSid() {
        return rsv060groupSid__;
    }
    /**
     * <p>rsv060groupSid をセットします。
     * @param rsv060groupSid rsv060groupSid
     */
    public void setRsv060groupSid(int rsv060groupSid) {
        rsv060groupSid__ = rsv060groupSid;
    }
    /**
     * <p>rsv060AccessDspFlg を取得します。
     * @return rsv060AccessDspFlg
     */
    public boolean isRsv060AccessDspFlg() {
        return rsv060AccessDspFlg__;
    }
    /**
     * <p>rsv060AccessDspFlg をセットします。
     * @param rsv060AccessDspFlg rsv060AccessDspFlg
     */
    public void setRsv060AccessDspFlg(boolean rsv060AccessDspFlg) {
        rsv060AccessDspFlg__ = rsv060AccessDspFlg;
    }
    /**
     * @return rsv060apprKbn
     */
    public int getRsv060apprKbn() {
        return rsv060apprKbn__;
    }
    /**
     * @param rsv060apprKbn 設定する rsv060apprKbn
     */
    public void setRsv060apprKbn(int rsv060apprKbn) {
        rsv060apprKbn__ = rsv060apprKbn;
    }

}