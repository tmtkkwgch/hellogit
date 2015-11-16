package jp.groupsession.v2.rng;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rng.dao.RingiDao;
import jp.groupsession.v2.rng.dao.RngChannelDao;
import jp.groupsession.v2.rng.dao.RngRndataDao;
import jp.groupsession.v2.rng.model.RingiDataModel;
import jp.groupsession.v2.rng.model.RngChannelModel;
import jp.groupsession.v2.rng.model.RngRndataModel;
import jp.groupsession.v2.usr.IUserGroupListener;

/**
 * <br>[機  能] ユーザ・グループに変更があった場合に実行されるリスナーを実装
 * <br>[解  説] 稟議についての処理を行う
 * <br>[備  考]
 *
 * @author JTS
 */
public class RngIUserGroupListenerImpl implements IUserGroupListener {

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

        RingiDao dao = new RingiDao(con);
        List<RingiDataModel> proRngSidList = dao.getProgressRingiSidList(usid);
        int rngSid = 0;
        UDate now = new UDate();
        RngChannelDao channelDao = new RngChannelDao(con);
        boolean isLastApprovalHnt = false;

        for (int i = 0; i < proRngSidList.size(); i++) {
            rngSid = proRngSidList.get(i).getRngSid();

            //削除ユーザが最終承認者かどうか
            isLastApprovalHnt = channelDao.isLastApproval(rngSid);

            if (!isLastApprovalHnt) {
                //現在確認中のユーザを取得する
                int confirmUserSid = channelDao.getApprovalUser(rngSid);

                //稟議経路情報の更新
                RngChannelModel channelMdl = new RngChannelModel();
                channelMdl.setRngSid(rngSid);
                channelMdl.setUsrSid(usid);
                channelMdl.setRncAuid(usid);
                channelMdl.setRncAdate(now);
                channelMdl.setRncEuid(usid);
                channelMdl.setRncEdate(now);
                channelMdl.setUsrSid(confirmUserSid);
                channelMdl.setRncComment(null);
                channelMdl.setRncStatus(RngConst.RNG_RNCSTATUS_NOSET);
                channelMdl.setRncChkdate(null);
                channelDao.updateChannel(channelMdl);

                //次の承認者の稟議経路情報を更新する
                int nextUserSid = channelDao.getNextApproval(channelMdl, 1);
                isLastApprovalHnt = nextUserSid <= 0;
                if (!isLastApprovalHnt) {
                    channelMdl.setUsrSid(nextUserSid);
                    channelMdl.setRncStatus(RngConst.RNG_RNCSTATUS_CONFIRM);
                    channelMdl.setRncRcvdate(now);
                    channelMdl.setRncChkdate(null);
                    channelDao.updateApprovalChannel(channelMdl);
                }
            }

            if (isLastApprovalHnt) {

                //ログインユーザが最終承認者の場合、稟議を完了する
                RngRndataDao rngDao = new RngRndataDao(con);
                RngRndataModel rngData = new RngRndataModel();

                rngData.setRngSid(rngSid);
                rngData.setRngAuid(usid);
                rngData.setRngAdate(now);
                rngData.setRngEuid(usid);
                rngData.setRngEdate(now);

                rngDao.completeRingi(rngData, false);

                //最終確認者の受信日を更新する
                channelDao.updateRcvdateForConfirmUser(rngData.getRngSid(), usid, now);
            }
        }
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
     * @param con DBコネクション
     * @param usid ユーザSID
     * @param pastGsids 変更前のグループSID配列
     * @param gsids 変更後のグループSID配列
     * @param eusid 更新者ユーザSID
     * @throws SQLException SQL実行例外
     */
    public void changeBelong(Connection con, int usid, int[] pastGsids, int[] gsids, int eusid)
    throws SQLException {
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
