package jp.groupsession.v2.usr.usr011;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.SltUserPerGroupModel;
import jp.groupsession.v2.cmn.dao.base.CmnGroupmDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmInfDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnGroupClassModel;
import jp.groupsession.v2.cmn.model.base.CmnGroupmModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] メイン 管理者設定 グループ登録画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Usr011Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Usr011Biz.class);
    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     */
    public Usr011Biz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] グループ、ユーザセット処理
     * <br>[解  説] グループセレクトボックスと、所属ユーザーを設定します。
     * <br>[備  考]
     * @param paramMdl Usr011ParamModel
     * @param con コネクション
     * @param grpSid グループSID
     * @throws Exception 実行例外
     */
    public void setSltUserPerGroupInfo(Usr011ParamModel paramMdl, Connection con, int grpSid)
            throws Exception {
        log__.debug("START");

        log__.debug("grpSid : " + grpSid);
        // グループセレクトボックス
        CmnGroupmDao gdao = new CmnGroupmDao(con);
        ArrayList<CmnGroupmModel> suvivers = (ArrayList<CmnGroupmModel>) gdao.selectSuvivers();
        paramMdl.setUsr011cbxgroup(suvivers);
        // 未所属ユーザーリストを作成します。
        UserBiz userBiz = new UserBiz();
        paramMdl.setUsr011tarUnbelongingUser(
                (ArrayList<SltUserPerGroupModel>) userBiz.getUserPerGroupList(
                        con, paramMdl.getSlt_group(), null, true));

        String[] svList = paramMdl.getSv_users();
        if (svList == null) {
            svList = new String[0];
        }
        ArrayList<SltUserPerGroupModel> rightList = paramMdl.getUsr011tarUnbelongingUser();
        for (String svUser : svList) {
            int index = 0;
            boolean exists = false;
            int svUserSid = Integer.parseInt(svUser);
            for (; index < rightList.size(); index++) {
                SltUserPerGroupModel belongModel = (SltUserPerGroupModel) rightList.get(index);
                if (belongModel.getUsrsid() == svUserSid) {
                    exists = true;
                    break;
                }
            }

            if (exists) {
                rightList.remove(index);
            }
        }
        paramMdl.setUsr011tarUnbelongingUser(rightList);

        List<SltUserPerGroupModel> uList = new ArrayList<SltUserPerGroupModel>();
        String[] l_user_list = paramMdl.getSv_users();

        if (l_user_list == null) {
            l_user_list = new String[0];
        }

        for (String lUsrId : l_user_list) {
            // 所属へユーザーの移動
            SltUserPerGroupModel bean = new SltUserPerGroupModel();
            bean.setUsrsid(Integer.parseInt(lUsrId));
            CmnUsrmInfDao ufdao = new CmnUsrmInfDao(con);
            CmnUsrmInfModel ufmdl = new CmnUsrmInfModel();
            ufmdl.setUsrSid(Integer.parseInt(lUsrId));

            bean.setFullName(ufdao.select(ufmdl).getUsiSei()
                    + "  " + ufdao.select(ufmdl).getUsiMei());
            uList.add(bean);
        }
        paramMdl.setUsr011tarBelongingUser(uList);

        log__.debug("END");
    }

    /**
     * 選択可能レベルを取得します。
     * @param gSid グループSID
     * @param con コネクション
     * @return String
     * @throws SQLException SQL実行時例外
     */
    public String getSelectLevel(int gSid, Connection con) throws SQLException {
        String ret = null;
        if (gSid == 0) {
            ret = "0";
            return ret;
        }

        try {
            GroupDao dao = new GroupDao(con);
            CmnGroupClassModel model = null;
            int uLv = -1;
            int myLv = -1;
            ArrayList<CmnGroupClassModel> list = dao.getUnderGroupClassList(gSid);
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    model = (CmnGroupClassModel) list.get(i);
                    if (i == 0) {
                        myLv = model.getClassLevel(gSid);
                    }
                    if (uLv < model.getUnderGroupLevel()) {
                        uLv = model.getUnderGroupLevel();
                    }
                }
            }
            ret = (new Integer(10 - (uLv - myLv + 1))).toString();
        } catch (SQLException e) {
            log__.error("SQLエラー", e);
        }
        return ret;
    }

    /**
     * 選択不可能グループSIDリストを取得します。
     * @param gSid グループSID
     * @param con コネクション
     * @return ArrayList 選択不可能グループSIDリスト
     */
    public ArrayList<Integer> getDisabledGroups(int gSid, Connection con) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        if (gSid == 0) {
            ret.add(new Integer(-1));
        }

        try {
            GroupDao dao = new GroupDao(con);
            ArrayList<CmnGroupClassModel> list = dao.getUnderGroupClassList(gSid);
            CmnGroupClassModel model = null;
            for (int i = 0; i < list.size(); i++) {
                model = (CmnGroupClassModel) list.get(i);
                ret.add(new Integer(model.getUnderGroup()));
                log__.debug("選択不可能グループSID = " + model.getUnderGroup());
            }
            //管理者グループへは階層追加不可
            ret.add(new Integer(0));
            log__.debug("選択不可能グループSID = " + new Integer(0));
        } catch (SQLException e) {
            log__.error("SQLエラー", e);
        }
        return ret;
    }

    /**
     * グループコンボを作成します。
     * @param paramMdl Usr011ParamModel
     * @param con コネクション
     * @throws SQLException SQL実行例外
     */
    public void getGroupComb(Usr011ParamModel paramMdl,
                              Connection con)
    throws SQLException {

        //グループコンボを設定
        GsMessage gsMsg = new GsMessage(reqMdl__);
        GroupBiz grpBiz = new GroupBiz();
        List<LabelValueBean> groupCombo = new ArrayList<LabelValueBean>();
        groupCombo.add(new LabelValueBean(gsMsg.getMessage("cmn.select.plz"), "-1"));

        ArrayList<GroupModel> grpList = grpBiz.getGroupCombList(con);
        for (GroupModel grpMdl : grpList) {
            LabelValueBean label = new LabelValueBean(grpMdl.getGroupName(),
                                                    String.valueOf(grpMdl.getGroupSid()));
            groupCombo.add(label);
        }
        //フォームにセット
        paramMdl.setGroupCombo(groupCombo);

    }
}