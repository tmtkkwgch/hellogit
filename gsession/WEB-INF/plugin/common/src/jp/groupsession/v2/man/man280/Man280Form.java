package jp.groupsession.v2.man.man280;

import java.util.List;

import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.man.man120.Man120Form;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;


/**
 * <br>[機  能] メイン 管理者設定 プラグイン使用制限画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man280Form extends Man120Form {

    /** グループSID: グループ一覧 */
    public static final int GRP_SID_GRPLIST = -9;

    /** プラグイン名称 */
    private String man280pluginName__ = null;
    /** プラグイン区分 */
    private int man280pluginKbn__ = GSConstMain.PLUGIN_GS;
    /** バイナリSID */
    private Long man280BinSid__ = new Long(0);

    /** 初期表示フラグ */
    private int man280initFlg__ = 0;
    /** プラグイン使用制限区分 */
    private int man280useKbn__ = GSConstMain.PCT_KBN_ALLOK;
    /** 制限方法 */
    private int man280limitType__ = GSConstMain.PCT_TYPE_LIMIT;

    /** グループSID */
    private int man280groupSid__ = GRP_SID_GRPLIST;
    /** メンバーSID */
    private String[] man280memberSid__ = new String[0];

    /** グループ一覧 */
    private List <LabelValueBean> man280GroupList__ = null;
    /** 追加済みメンバー一覧 */
    private List <LabelValueBean> man280LeftUserList__ = null;
    /** 追加用メンバー一覧 */
    private List < LabelValueBean > man280RightUserList__ = null;

    /** 追加済みメンバー(選択) */
    private String[] man280SelectLeftUser__ = new String[0];
    /** 追加用メンバー(選択) */
    private String[] man280SelectRightUser__ = new String[0];

    /** グループSID（管理者） */
    private int man280groupSidAdmin__ = GRP_SID_GRPLIST;
    /** 管理者SID */
    private String[] man280AdminSid__ = new String[0];

    /** グループ一覧 (管理者) */
    private List <LabelValueBean> man280GroupListAdmin__ = null;
    /** 追加済み管理者一覧 */
    private List <LabelValueBean> man280LeftAdminList__ = null;
    /** 追加用管理者一覧 */
    private List < LabelValueBean > man280RightAdminList__ = null;

    /** 追加済み管理者(選択) */
    private String[] man280SelectLeftAdmin__ = new String[0];
    /** 追加用管理者(選択) */
    private String[] man280SelectRightAdmin__ = new String[0];

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @return エラー
     */
    public ActionErrors validateCheck(RequestModel reqMdl) {
        ActionErrors errors = new ActionErrors();

        if (man280useKbn__ == GSConstMain.PCT_KBN_MEMBER) {
            if (man280memberSid__ == null || man280memberSid__.length == 0) {
                GsMessage gsMsg = new GsMessage(reqMdl);
                String msgStr = "";
                if (man280limitType__ == GSConstMain.PCT_TYPE_LIMIT) {
                    msgStr = gsMsg.getMessage(GSConstMain.TEXT_PERMIT_USER);
                } else {
                    msgStr = gsMsg.getMessage(GSConstMain.TEXT_LIMIT_USER);
                }
                ActionMessage msg =
                    new ActionMessage("error.select.required.text", msgStr);
                StrutsUtil.addMessage(errors, msg, "man280memberSid.error.select.required.text");
            }
        }

        return errors;
    }

    /**
     * <p>man280pluginName を取得します。
     * @return man280pluginName
     */
    public String getMan280pluginName() {
        return man280pluginName__;
    }
    /**
     * <p>man280pluginName をセットします。
     * @param man280pluginName man280pluginName
     */
    public void setMan280pluginName(String man280pluginName) {
        man280pluginName__ = man280pluginName;
    }
    /**
     * <p>man280initFlg を取得します。
     * @return man280initFlg
     */
    public int getMan280initFlg() {
        return man280initFlg__;
    }
    /**
     * <p>man280initFlg をセットします。
     * @param man280initFlg man280initFlg
     */
    public void setMan280initFlg(int man280initFlg) {
        man280initFlg__ = man280initFlg;
    }
    /**
     * <p>man280GroupList を取得します。
     * @return man280GroupList
     */
    public List<LabelValueBean> getMan280GroupList() {
        return man280GroupList__;
    }
    /**
     * <p>man280GroupList をセットします。
     * @param man280GroupList man280GroupList
     */
    public void setMan280GroupList(List<LabelValueBean> man280GroupList) {
        man280GroupList__ = man280GroupList;
    }
    /**
     * <p>man280groupSid を取得します。
     * @return man280groupSid
     */
    public int getMan280groupSid() {
        return man280groupSid__;
    }
    /**
     * <p>man280groupSid をセットします。
     * @param man280groupSid man280groupSid
     */
    public void setMan280groupSid(int man280groupSid) {
        man280groupSid__ = man280groupSid;
    }
    /**
     * <p>man280LeftUserList を取得します。
     * @return man280LeftUserList
     */
    public List<LabelValueBean> getMan280LeftUserList() {
        return man280LeftUserList__;
    }
    /**
     * <p>man280LeftUserList をセットします。
     * @param man280LeftUserList man280LeftUserList
     */
    public void setMan280LeftUserList(List<LabelValueBean> man280LeftUserList) {
        man280LeftUserList__ = man280LeftUserList;
    }
    /**
     * <p>man280memberSid を取得します。
     * @return man280memberSid
     */
    public String[] getMan280memberSid() {
        return man280memberSid__;
    }
    /**
     * <p>man280memberSid をセットします。
     * @param man280memberSid man280memberSid
     */
    public void setMan280memberSid(String[] man280memberSid) {
        man280memberSid__ = man280memberSid;
    }
    /**
     * <p>man280RightUserList を取得します。
     * @return man280RightUserList
     */
    public List<LabelValueBean> getMan280RightUserList() {
        return man280RightUserList__;
    }
    /**
     * <p>man280RightUserList をセットします。
     * @param man280RightUserList man280RightUserList
     */
    public void setMan280RightUserList(List<LabelValueBean> man280RightUserList) {
        man280RightUserList__ = man280RightUserList;
    }
    /**
     * <p>man280SelectLeftUser を取得します。
     * @return man280SelectLeftUser
     */
    public String[] getMan280SelectLeftUser() {
        return man280SelectLeftUser__;
    }
    /**
     * <p>man280SelectLeftUser をセットします。
     * @param man280SelectLeftUser man280SelectLeftUser
     */
    public void setMan280SelectLeftUser(String[] man280SelectLeftUser) {
        man280SelectLeftUser__ = man280SelectLeftUser;
    }
    /**
     * <p>man280SelectRightUser を取得します。
     * @return man280SelectRightUser
     */
    public String[] getMan280SelectRightUser() {
        return man280SelectRightUser__;
    }
    /**
     * <p>man280SelectRightUser をセットします。
     * @param man280SelectRightUser man280SelectRightUser
     */
    public void setMan280SelectRightUser(String[] man280SelectRightUser) {
        man280SelectRightUser__ = man280SelectRightUser;
    }
    /**
     * <p>man280useKbn を取得します。
     * @return man280useKbn
     */
    public int getMan280useKbn() {
        return man280useKbn__;
    }
    /**
     * <p>man280useKbn をセットします。
     * @param man280useKbn man280useKbn
     */
    public void setMan280useKbn(int man280useKbn) {
        man280useKbn__ = man280useKbn;
    }
    /**
     * <p>man280groupSidAdmin を取得します。
     * @return man280groupSidAdmin
     */
    public int getMan280groupSidAdmin() {
        return man280groupSidAdmin__;
    }
    /**
     * <p>man280groupSidAdmin をセットします。
     * @param man280groupSidAdmin man280groupSidAdmin
     */
    public void setMan280groupSidAdmin(int man280groupSidAdmin) {
        man280groupSidAdmin__ = man280groupSidAdmin;
    }
    /**
     * <p>man280AdminSid を取得します。
     * @return man280AdminSid
     */
    public String[] getMan280AdminSid() {
        return man280AdminSid__;
    }
    /**
     * <p>man280AdminSid をセットします。
     * @param man280AdminSid man280AdminSid
     */
    public void setMan280AdminSid(String[] man280AdminSid) {
        man280AdminSid__ = man280AdminSid;
    }
    /**
     * <p>man280LeftAdminList を取得します。
     * @return man280LeftAdminList
     */
    public List<LabelValueBean> getMan280LeftAdminList() {
        return man280LeftAdminList__;
    }
    /**
     * <p>man280LeftAdminList をセットします。
     * @param man280LeftAdminList man280LeftAdminList
     */
    public void setMan280LeftAdminList(List<LabelValueBean> man280LeftAdminList) {
        man280LeftAdminList__ = man280LeftAdminList;
    }
    /**
     * <p>man280RightAdminList を取得します。
     * @return man280RightAdminList
     */
    public List<LabelValueBean> getMan280RightAdminList() {
        return man280RightAdminList__;
    }
    /**
     * <p>man280RightAdminList をセットします。
     * @param man280RightAdminList man280RightAdminList
     */
    public void setMan280RightAdminList(List<LabelValueBean> man280RightAdminList) {
        man280RightAdminList__ = man280RightAdminList;
    }
    /**
     * <p>man280SelectLeftAdmin を取得します。
     * @return man280SelectLeftAdmin
     */
    public String[] getMan280SelectLeftAdmin() {
        return man280SelectLeftAdmin__;
    }
    /**
     * <p>man280SelectLeftAdmin をセットします。
     * @param man280SelectLeftAdmin man280SelectLeftAdmin
     */
    public void setMan280SelectLeftAdmin(String[] man280SelectLeftAdmin) {
        man280SelectLeftAdmin__ = man280SelectLeftAdmin;
    }
    /**
     * <p>man280SelectRightAdmin を取得します。
     * @return man280SelectRightAdmin
     */
    public String[] getMan280SelectRightAdmin() {
        return man280SelectRightAdmin__;
    }
    /**
     * <p>man280SelectRightAdmin をセットします。
     * @param man280SelectRightAdmin man280SelectRightAdmin
     */
    public void setMan280SelectRightAdmin(String[] man280SelectRightAdmin) {
        man280SelectRightAdmin__ = man280SelectRightAdmin;
    }
    /**
     * <p>man280limitType を取得します。
     * @return man280limitType
     */
    public int getMan280limitType() {
        return man280limitType__;
    }
    /**
     * <p>man280limitType をセットします。
     * @param man280limitType man280limitType
     */
    public void setMan280limitType(int man280limitType) {
        man280limitType__ = man280limitType;
    }
    /**
     * <p>man280GroupListAdmin を取得します。
     * @return man280GroupListAdmin
     */
    public List<LabelValueBean> getMan280GroupListAdmin() {
        return man280GroupListAdmin__;
    }
    /**
     * <p>man280GroupListAdmin をセットします。
     * @param man280GroupListAdmin man280GroupListAdmin
     */
    public void setMan280GroupListAdmin(List<LabelValueBean> man280GroupListAdmin) {
        man280GroupListAdmin__ = man280GroupListAdmin;
    }
    /**
     * <p>man280pluginKbn を取得します。
     * @return man280pluginKbn
     */
    public int getMan280pluginKbn() {
        return man280pluginKbn__;
    }
    /**
     * <p>man280pluginKbn をセットします。
     * @param man280pluginKbn man280pluginKbn
     */
    public void setMan280pluginKbn(int man280pluginKbn) {
        man280pluginKbn__ = man280pluginKbn;
    }
    /**
     * <p>man280BinSid を取得します。
     * @return man280BinSid
     */
    public Long getMan280BinSid() {
        return man280BinSid__;
    }
    /**
     * <p>man280BinSid をセットします。
     * @param man280BinSid man280BinSid
     */
    public void setMan280BinSid(Long man280BinSid) {
        man280BinSid__ = man280BinSid;
    }
}
