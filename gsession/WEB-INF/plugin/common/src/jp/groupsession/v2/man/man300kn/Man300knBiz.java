package jp.groupsession.v2.man.man300kn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.UsidSelectGrpNameDao;
import jp.groupsession.v2.cmn.dao.base.CmnInfoUserDao;
import jp.groupsession.v2.cmn.model.base.CmnInfoUserModel;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] メイン インフォメーション 管理者設定確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man300knBiz {

    /**
     * <br>[機  能] 初期表示画面情報を設定します
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void setInitData(Man300knParamModel paramMdl, Connection con)
    throws SQLException {

        //公開対象生成
        String[] leftFull = paramMdl.getMan300memberSid();
        paramMdl.setMan300knKoukaiList(__getKoukaiLabel(leftFull, con));

        //シングルコーテーションをエスケープする
        paramMdl.setCmd(StringUtil.toSingleCortationEscape(paramMdl.getCmd()));
    }

    /**
     * <br>[機  能] 管理者設定を更新します
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void update(Man300knParamModel paramMdl, Connection con) throws SQLException {
        CmnInfoUserDao usrDao = new CmnInfoUserDao(con);
        CmnInfoUserModel usrMdl = null;
        String[] left = paramMdl.getMan300memberSid();
        ArrayList<Integer> grpSids = new ArrayList<Integer>();
        ArrayList<Integer> usrSids = new ArrayList<Integer>();

        //ユーザSIDとグループSIDを分離
        if (left != null) {
            for (int i = 0; i < left.length; i++) {
                String str = NullDefault.getString(left[i], "-1");
                if (str.contains(new String("G").subSequence(0, 1))) {
                    //グループ
                    grpSids.add(new Integer(str.substring(1, str.length())));
                } else {
                    //ユーザ
                    usrSids.add(new Integer(str));
                }
            }
        }
        //事前削除
        usrDao.delete();
        //公開グループを登録
        for (Integer gSid : grpSids) {
            usrMdl = new CmnInfoUserModel();
            usrMdl.setUsrSid(-1);
            usrMdl.setGrpSid(gSid);
            usrDao.insert(usrMdl);
        }
        //公開ユーザを登録
        for (Integer uSid : usrSids) {
            usrMdl = new CmnInfoUserModel();
            usrMdl.setUsrSid(uSid);
            usrMdl.setGrpSid(-1);
            usrDao.insert(usrMdl);
        }

    }
    /**
     * <br>[機  能] 公開対象一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param left 取得するユーザSID・グループSID
     * @param con コネクション
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getKoukaiLabel(String[] left, Connection con)
    throws SQLException {

        ArrayList<LabelValueBean> ret = new ArrayList<LabelValueBean>();

        //
        ArrayList<Integer> grpSids = new ArrayList<Integer>();
        ArrayList<String> usrSids = new ArrayList<String>();

        //ユーザSIDとグループSIDを分離
        if (left != null) {
            for (int i = 0; i < left.length; i++) {
                String str = NullDefault.getString(left[i], "-1");
                if (str.contains(new String("G").subSequence(0, 1))) {
                    //グループ
                    grpSids.add(new Integer(str.substring(1, str.length())));
                } else {
                    //ユーザ
                    usrSids.add(str);
                }
            }
        }
        //グループ情報取得
        UsidSelectGrpNameDao gdao = new UsidSelectGrpNameDao(con);
        ArrayList<GroupModel> glist = gdao.selectGroupNmListOrderbyClass(grpSids);
        LabelValueBean labelBean = null;
        for (GroupModel gmodel : glist) {
            labelBean = new LabelValueBean();
            labelBean.setLabel(gmodel.getGroupName());
            labelBean.setValue("G" + String.valueOf(gmodel.getGroupSid()));
            ret.add(labelBean);
        }

        //ユーザ情報取得
        UserBiz userBiz = new UserBiz();
        ArrayList<BaseUserModel> ulist
                = userBiz.getBaseUserList(con,
                                        (String[]) usrSids.toArray(new String[usrSids.size()]));
        for (BaseUserModel umodel : ulist) {
            labelBean = new LabelValueBean();
            labelBean.setLabel(umodel.getUsisei() + " " + umodel.getUsimei());
            labelBean.setValue(String.valueOf(umodel.getUsrsid()));
            ret.add(labelBean);
        }

        return ret;
    }
}
