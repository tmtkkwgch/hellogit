package jp.groupsession.v2.man.man210;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] メイン 管理者設定 モバイル使用一括設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man210Biz {

    /**
     * <br>[機  能] 常に表示する値を画面にセットする
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @throws SQLException SQL実行時例外
     */
    public void setDspData(Connection con, Man210ParamModel paramMdl, RequestModel reqMdl)
    throws SQLException {

        GsMessage gsMsg = new GsMessage(reqMdl);

        /** グループコンボセット **************************************************/
        GroupBiz biz = new GroupBiz();
        paramMdl.setMan210GpLabelList(biz.getGroupCombLabelList(con, true, gsMsg));


        /** 現在選択中のメンバーコンボセット **************************************/
        UserBiz userBiz = new UserBiz();
        paramMdl.setMan210MbLabelList(
                (ArrayList<LabelValueBean>) userBiz.getUserLabelList(
                        con, paramMdl.getMan210userSid()));

        /** 追加用メンバーコンボセット ********************************************/
        //グループSID
        int gpSid = NullDefault.getInt(paramMdl.getMan210groupSid(), -1);

        //除外するユーザSID
        ArrayList<Integer> usrSids = new ArrayList<Integer>();
        String[] userSids = paramMdl.getMan210userSid();
        if (userSids != null) {
            for (int i = 0; i < userSids.length; i++) {
                usrSids.add(new Integer(NullDefault.getInt(userSids[i], -1)));
            }
        }

        List<CmnUsrmInfModel> usList
            = userBiz.getBelongUserList(con, gpSid, usrSids);

        ArrayList<LabelValueBean> labelListAdd = new ArrayList<LabelValueBean>();
        for (int i = 0; i < usList.size(); i++) {
            CmnUsrmInfModel cuiMdl = usList.get(i);
            labelListAdd.add(new LabelValueBean(cuiMdl.getUsiSei() + cuiMdl.getUsiMei(),
                             String.valueOf(cuiMdl.getUsrSid())));
        }
        paramMdl.setMan210AdLabelList(labelListAdd);
    }

    /**
     * <br>[機  能] コンボで選択中のメンバーをメンバーリストから削除する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     */
    public void deleteMnb(Man210ParamModel paramMdl) {

        //コンボで選択中
        String[] selectUserSid = paramMdl.getMan210selectUserSid();
        //メンバーリスト(コンボ表示に使用する値)
        String[] userSid = paramMdl.getMan210userSid();

        CommonBiz biz = new CommonBiz();
        paramMdl.setMan210userSid(biz.getDeleteMember(selectUserSid, userSid));
    }

    /**
     * <br>[機  能] 追加用メンバーコンボで選択中のメンバーをメンバーリストに追加する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     */
    public void addMnb(Man210ParamModel paramMdl) {

        //追加用メンバー(選択中)
        String[] addUserSid = paramMdl.getMan210addUserSid();
        //メンバーリスト(コンボ表示に使用する値)
        String[] userSid = paramMdl.getMan210userSid();

        CommonBiz biz = new CommonBiz();
        paramMdl.setMan210userSid(biz.getAddMember(addUserSid, userSid));
    }
}