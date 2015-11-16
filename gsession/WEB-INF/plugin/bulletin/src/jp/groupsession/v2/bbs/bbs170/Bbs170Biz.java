package jp.groupsession.v2.bbs.bbs170;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.PageUtil;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.bbs.BbsBiz;
import jp.groupsession.v2.bbs.bbs080.Bbs080Biz;
import jp.groupsession.v2.bbs.dao.BbsForInfDao;
import jp.groupsession.v2.bbs.dao.BbsWriteInfDao;
import jp.groupsession.v2.bbs.dao.BulletinDao;
import jp.groupsession.v2.bbs.model.BbsUserModel;
import jp.groupsession.v2.bbs.model.BulletinDspModel;
import jp.groupsession.v2.bbs.model.BulletinForumDiskModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 掲示板 掲示予定一覧画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bbs170Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bbs170Biz.class);

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param userSid ユーザSID
     * @param adminFlg システム管理者・プラグイン管理者フラグ
     * @throws Exception 実行例外
     */
    public void setInitData(Bbs170ParamModel paramMdl, Connection con,
                            int userSid, boolean adminFlg)
    throws Exception {
        log__.debug("START");

        //フォーラム名を設定
        BbsBiz bbsBiz = new BbsBiz();
        int bfiSid = paramMdl.getBbs010forumSid();
        BulletinDspModel btDspMdl = bbsBiz.getForumData(con, bfiSid);
        paramMdl.setBbs170forumName(btDspMdl.getBfiName());

        //フォーラムのディスク容量警告を設定
        BulletinForumDiskModel diskData = bbsBiz.getForumDiskData(con, bfiSid);
        if (bbsBiz.checkForumWarnDisk(diskData)) {
            paramMdl.setBbs170forumWarnDisk(diskData.getBfiWarnDiskTh());
        } else {
            paramMdl.setBbs170forumWarnDisk(0);
        }

        //掲示板個人情報を取得
        BbsUserModel bUserMdl = bbsBiz.getBbsUserData(con, userSid);

        UDate now = new UDate();

        //最大件数
        int threCnt = __getThreadCount(con, bfiSid, userSid, now, adminFlg);

        log__.debug("getThreadCount==>" + threCnt);
        int maxThreCnt = bUserMdl.getBurThreCnt();

        //ページ調整
        int maxPage = threCnt / maxThreCnt;
        if ((threCnt % maxThreCnt) > 0) {
            maxPage++;
        }
        int page = paramMdl.getBbs170page1();
        if (page < 1) {
            page = 1;
        } else if (page > maxPage) {
            page = maxPage;
        }
        paramMdl.setBbs170page1(page);
        paramMdl.setBbs170page2(page);
        //ページコンボ設定
        paramMdl.setBbsPageLabel(PageUtil.createPageOptions(threCnt, maxThreCnt));

        //スレッド一覧を取得する
        BulletinDao btDao = new BulletinDao(con);
        int start = (page - 1) * maxThreCnt + 1;
        int end = start + maxThreCnt - 1;

        paramMdl.setThreadList(
                btDao.getRsvThreadList(bfiSid, userSid, now,
                        bUserMdl.getBurNewCnt(), start, end,
                        Integer.parseInt(paramMdl.getBbs170sortKey()),
                        Integer.parseInt(paramMdl.getBbs170orderKey()), adminFlg));


        //フォーラムのメンバー数を取得する
        paramMdl.setForumMemberCount(String.valueOf(btDao.getForumMemberCount(bfiSid)));


        //フォーラムSIDからアイコンバイナリSIDを取得する
        Bbs170Biz bbs170Biz = new Bbs170Biz();
        Long binSid = bbs170Biz.getIcoBinSid(paramMdl, con);
        paramMdl.setBbs170BinSid(binSid);

        log__.debug("End");
    }

    /**
     * <br>[機  能] スレッドデータを削除する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータモデル
     * @param userSid ユーザSID
     * @param admin 管理者権限の有無
     * @param con コネクション
     * @return 削除後の掲示予定スレッド件数
     * @throws Exception 実行時例外
     */
    public int deleteThreadData(
            Bbs170ParamModel paramMdl, int userSid, boolean admin, Connection con)
            throws Exception {

        int rsvThrNum = 0;

        //スレッド情報の削除
        boolean commit = false;
        try {
            Bbs080Biz biz = new Bbs080Biz();
            biz.deleteThreadData(
                    paramMdl.getBbs010forumSid(), paramMdl.getThreadSid(), con, userSid);
            commit = true;
        } catch (Exception e) {
            log__.error("スレッドの削除に失敗", e);
            throw e;
        } finally {
            if (commit) {
                con.commit();
            } else {
                con.rollback();
            }
        }

        //削除後、掲示予定スレッド件数を取得する
        BulletinDao btDao = new BulletinDao(con);
        boolean allFlg = false;
        if (admin) {
            allFlg = true;
        }

        rsvThrNum = btDao.countRsvThreData(
                paramMdl.getBbs010forumSid(), userSid, allFlg, new UDate());


        return rsvThrNum;
    }


    /**
     * <br>[機  能] 掲示予定のスレッドの件数を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param bfiSid フォーラムSID
     * @param usrSid ユーザSID
     * @param now 現在日時
     * @param adminFlg 管理者権限フラグ
     * @return スレッド件数
     * @throws SQLException SQL実行例外
     */
    private int __getThreadCount(
            Connection con, int bfiSid, int usrSid, UDate now, boolean adminFlg)
    throws SQLException {

        BulletinDao btDao = new BulletinDao(con);
        return btDao.countRsvThreData(bfiSid, usrSid, adminFlg, now);
    }

    /**
     * <br>[機  能] 閲覧フォーラムのアイコンバイナリSIDを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @return アイコンバイナリSID
     * @throws SQLException SQL実行例外
     */
    public Long getIcoBinSid(Bbs170ParamModel paramMdl, Connection con)
    throws SQLException {

        //閲覧フォーラムのアイコンバイナリSIDを取得する
        BbsForInfDao bfiDao = new BbsForInfDao(con);
        Long icoBinSid = bfiDao.selectIcoBinSid(paramMdl.getBbs010forumSid());
        paramMdl.setBbs170BinSid(icoBinSid);

        return icoBinSid;
    }

    /**
     * <br>[機  能] フォーラムSIDとアイコンバイナリSIDを照合する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param bfiSid フォーラムSID
     * @param imgBinSid 画像バイナリSID
     * @return true:照合OK false:照合NG
     * @throws SQLException SQL実行例外
     */
    public boolean cheIcoHnt(Connection con, int bfiSid, Long imgBinSid)
    throws SQLException {

        boolean icoCheckFlg = false;

        //フォーラムSIDとアイコンバイナリSIDの組み合わせチェック
        BbsForInfDao bfiDao = new BbsForInfDao(con);
        boolean existForIcoFlg = bfiDao.existBbsForIco(bfiSid, imgBinSid);

        if (existForIcoFlg) {
            icoCheckFlg = true;
        }

        return icoCheckFlg;
    }

    /**
     * <br>[機  能] 最初の投稿SIDを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param bfiSid フォーラムSID
     * @param btiSid スレッドSID
     * @return 投稿SID
     * @throws SQLException SQL実行例外
     */
    public int getFirstBwiSid(Connection con, int bfiSid, int btiSid)
    throws SQLException {

        BbsWriteInfDao dao = new BbsWriteInfDao(con);
        return dao.getFirstBwiSid(bfiSid, btiSid);
    }
}
