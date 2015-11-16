package jp.groupsession.v2.bbs;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.bbs.dao.BbsForInfDao;
import jp.groupsession.v2.bbs.dao.BbsForMemDao;
import jp.groupsession.v2.bbs.dao.BbsThreViewDao;
import jp.groupsession.v2.bbs.dao.BbsUserDao;
import jp.groupsession.v2.bbs.model.BbsUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.usr.IUserGroupListener;

/**
 * <br>[機  能] ユーザ・グループに変更があった場合に実行されるリスナーを実装
 * <br>[解  説] 掲示板についての処理を行う
 * <br>[備  考]
 *
 * @author JTS
 */
public class BbsIUserGroupListenerImpl implements IUserGroupListener {

    /**
     * <br>[機  能] ユーザ追加時に実行される
     * <br>[解  説]
     * <br>[備  考]
     * @param usid 追加されるユーザSID
     * @param con DBコネクション
     * @param cntCon MlCountMtController
     * @param eusid 更新者ユーザSID
     * @param reqMdl リクエスト情報
     * @throws SQLException SQL実行例外
     */
    public void addUser(MlCountMtController cntCon,
            Connection con, int usid, int eusid, RequestModel reqMdl)
    throws SQLException {

        //追加ユーザがメンバーに含まれる かつ
        //新規ユーザのスレッド閲覧状態 = 「既読」のフォーラムを取得する
        BbsForInfDao forumDao = new BbsForInfDao(con);
        List<Integer> bfiSidList = forumDao.getInitForumSidList(usid);

        //スレッド閲覧情報の登録を行う
        if (!bfiSidList.isEmpty()) {
            UDate now = new UDate();
            BbsThreViewDao threViewDao = new BbsThreViewDao(con);

            for (Integer bfiSid : bfiSidList) {
                threViewDao.insert(bfiSid, usid, eusid, now);
            }
        }
    }

    /**
     * <br>[機  能] ユーザ削除時に実行される
     * <br>[解  説]
     * <br>[備  考]
     * @param usid ユーザSID
     * @param con DBコネクション
     * @param eusid 更新者ユーザSID
     * @param reqMdl リクエスト情報
     * @throws SQLException SQL実行例外
     */
    public void deleteUser(Connection con, int usid, int eusid, RequestModel reqMdl)
    throws SQLException {

        //フォーラムメンバーの削除を行う。
        BbsForMemDao forMemDao = new BbsForMemDao(con);
        forMemDao.delete(usid);

        //掲示板個人設定の削除
        BbsUserDao userDao = new BbsUserDao(con);
        BbsUserModel userModel = new BbsUserModel();
        userModel.setUsrSid(usid);
        userDao.delete(userModel);

        //スレッド閲覧情報の削除
        BbsThreViewDao threViewDao = new BbsThreViewDao(con);
        threViewDao.delete(userModel);
    }

    /**
     * <br>[機  能] グループ追加時に実行される
     * <br>[解  説]
     * <br>[備  考]
     * @param gsid グループSID
     * @param con DBコネクション
     * @param eusid 更新者ユーザSID
     * @throws SQLException SQL実行例外
     */
    public void addGroup(Connection con, int gsid, int eusid) throws SQLException {
    }

    /**
     * <br>[機  能] グループ削除時に実行される
     * <br>[解  説]
     * <br>[備  考]
     * @param gsid グループSID
     * @param con DBコネクション
     * @param eusid 更新者ユーザSID
     * @param reqMdl RequestModel
     * @throws SQLException SQL実行例外
     */
    public void deleteGroup(
            Connection con, int gsid, int eusid, RequestModel reqMdl) throws SQLException {
    }

    /**
     * <br>[機  能] ユーザの所属グループが変更になった場合に実行される
     * <br>[解  説]
     * <br>[備  考]
     * @param usid ユーザSID
     * @param pastGsids 変更前のグループSID配列
     * @param gsids 変更後のグループSID配列
     * @param con DBコネクション
     * @param eusid 更新者ユーザSID
     * @throws SQLException SQL実行例外
     */
    public void changeBelong(Connection con, int usid, int[] pastGsids, int[] gsids, int eusid)
    throws SQLException {

        if (gsids == null || gsids.length == 0) {
            return;
        }

        //所属グループに追加されたグループを取得する
        List<Integer> addGroupList = new ArrayList<Integer>();
        List<Integer> excGroupList = new ArrayList<Integer>();

        if (pastGsids == null || pastGsids.length == 0) {
            for (int gsid : gsids) {
                addGroupList.add(gsid);
            }
        } else {
            Arrays.sort(pastGsids);
            for (int gsid : gsids) {
                if (Arrays.binarySearch(pastGsids, gsid) < 0) {
                    addGroupList.add(gsid);
                } else {
                    excGroupList.add(gsid);
                }
            }
        }

        //追加されたグループが存在しない場合、処理を終了する
        if (addGroupList.isEmpty()) {
            return;
        }

        //追加グループがメンバーに含まれる かつ
        //新規ユーザのスレッド閲覧状態 = 「既読」のフォーラムを取得する
        BbsForInfDao forumDao = new BbsForInfDao(con);
        List<Integer> bfiSidList = forumDao.getInitForumSidList(addGroupList);

        //取得したフォーラムのうち、変更前のグループまたは対象ユーザがメンバーとして登録されているものを除外する
        bfiSidList = forumDao.excludeForumSidList(bfiSidList, excGroupList, usid);

        //スレッド閲覧情報の登録を行う
        if (!bfiSidList.isEmpty()) {
            UDate now = new UDate();
            BbsThreViewDao threViewDao = new BbsThreViewDao(con);
            for (Integer bfiSid : bfiSidList) {
                threViewDao.insertAllReadThreadInForum(bfiSid, usid, eusid, now);
            }
        }
    }

    /**
     * <br>[機  能] ユーザのデフォルトグループが変更になった場合に実行される
     * <br>[解  説]
     * <br>[備  考]
     * @param usid ユーザSID
     * @param gsid 変更後のデフォルトグループ
     * @param con DBコネクション
     * @param eusid 更新者ユーザSID
     * @throws SQLException SQL実行例外
     */
    public void changeDefaultBelong(Connection con, int usid, int gsid, int eusid)
    throws SQLException {
    }
}
