package jp.groupsession.v2.cmn.cmn130;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.dao.MyGroupDao;
import jp.groupsession.v2.cmn.dao.base.CmnCmbsortConfDao;
import jp.groupsession.v2.cmn.dao.base.CmnMyGroupDao;
import jp.groupsession.v2.cmn.model.MyGroupModel;
import jp.groupsession.v2.cmn.model.base.CmnCmbsortConfModel;
import jp.groupsession.v2.cmn.model.base.CmnMyGroupModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] メイン 個人設定 マイグループ設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cmn130Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Cmn130Biz.class);

    /**
     * <br>[機  能] 初期表示情報を画面にセットする
     * <br>[解  説]
     * <br>[備  考]
     * @param cmn130Model パラメータ格納モデル
     * @param con コネクション
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    public void setInitData(Cmn130ParamModel cmn130Model,
                                          Connection con,
                                             int userSid
                                   ) throws SQLException {

        //ユーザSIDからマイグループ情報を取得する

        MyGroupDao mgDao = new MyGroupDao(con);

        CmnCmbsortConfDao sortDao = new CmnCmbsortConfDao(con);
        CmnCmbsortConfModel sortMdl = sortDao.getCmbSortData();

        List<MyGroupModel> cmgList = mgDao.getMyGroupList(userSid, sortMdl, true, false);

        //マイグループリストをセット
        cmn130Model.setCmn130GroupList(cmgList);

        List<MyGroupModel> smgList = mgDao.getMyGroupList(userSid, sortMdl, false, true);
        cmn130Model.setCmn130SharedGroupList(smgList);
    }

    /**
     * <br>[機  能] 削除するマイグループ名を取得する
     * <br>[解  説] 複数存在する場合は改行を挿入する
     * <br>[備  考]
     * @param cmn130Model パラメータ格納モデル
     * @param con コネクション
     * @return String 削除するマイグループ名
     * @throws SQLException SQL実行例外
     */
    public String getDeleteGroupName(Cmn130ParamModel cmn130Model,
                                                   Connection con)
                                             throws SQLException {

        //マイグループSIDを取得
        String[] delGroupSid = cmn130Model.getCmn130delGroupSid();

        //マイグループSID(複数)からマイグループ情報を取得する
        CmnMyGroupDao cmgDao = new CmnMyGroupDao(con);
        List<CmnMyGroupModel> cmgList = cmgDao.getMyGroupListFromGroupSid(delGroupSid);

        //マイグループ名取得
        StringBuilder deleteGroup = new StringBuilder();
        for (int i = 0; i < cmgList.size(); i++) {
            CmnMyGroupModel cmgMdl = cmgList.get(i);

            deleteGroup.append("・" + NullDefault.getString(cmgMdl.getMgpName(), ""));

            //改行を挿入
            if (i < cmgList.size() - 1) {
                deleteGroup.append(GSConst.NEW_LINE_STR);
            }
        }
        return deleteGroup.toString();
    }

    /**
     * <br>[機  能] 選択されたマイグループを削除する
     * <br>[解  説]
     * <br>[備  考]
     * @param cmn130Model パラメータ格納モデル
     * @param con コネクション
     * @throws SQLException SQL実行例外
     */
    public void deleteGroup(Cmn130ParamModel cmn130Model, Connection con) throws SQLException {

        //マイグループSIDを取得
        String[] delGroupSid = cmn130Model.getCmn130delGroupSid();

        boolean commit = false;
        try {
            con.setAutoCommit(false);

            //マイグループSID(複数)を指定してマイグループを削除する
            MyGroupDao mgDao = new MyGroupDao(con);
            mgDao.deleteMyGroup(delGroupSid);

            con.commit();
            commit = true;
        } catch (SQLException e) {
            log__.warn("マイグループ削除に失敗", e);
            throw e;
        } finally {
            if (!commit) {
                con.rollback();
            }
        }
    }

}
