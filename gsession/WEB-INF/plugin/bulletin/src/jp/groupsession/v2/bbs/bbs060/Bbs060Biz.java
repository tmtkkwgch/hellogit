package jp.groupsession.v2.bbs.bbs060;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.PageUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.bbs.BbsBiz;
import jp.groupsession.v2.bbs.GSConstBulletin;
import jp.groupsession.v2.bbs.bbs010.Bbs010Biz;
import jp.groupsession.v2.bbs.dao.BbsForInfDao;
import jp.groupsession.v2.bbs.dao.BbsThreViewDao;
import jp.groupsession.v2.bbs.dao.BulletinDao;
import jp.groupsession.v2.bbs.model.BbsUserModel;
import jp.groupsession.v2.bbs.model.BulletinDspModel;
import jp.groupsession.v2.bbs.model.BulletinForumDiskModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 掲示板 スレッド一覧画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bbs060Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bbs060Biz.class);

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
    public void setInitData(Bbs060ParamModel paramMdl, Connection con,
                            int userSid, boolean adminFlg)
    throws Exception {
        log__.debug("START");

        //フォーラム名を設定
        BbsBiz bbsBiz = new BbsBiz();
        int bfiSid = paramMdl.getBbs010forumSid();
        BulletinDspModel btDspMdl = bbsBiz.getForumData(con, bfiSid);
        paramMdl.setBbs060forumName(btDspMdl.getBfiName());

        //フォーラムのディスク容量警告を設定
        BulletinForumDiskModel diskData = bbsBiz.getForumDiskData(con, bfiSid);
        if (bbsBiz.checkForumWarnDisk(diskData)) {
            paramMdl.setBbs060forumWarnDisk(diskData.getBfiWarnDiskTh());
        } else {
            paramMdl.setBbs060forumWarnDisk(0);
        }

        //掲示板個人情報を取得
        BbsUserModel bUserMdl = bbsBiz.getBbsUserData(con, userSid);

        UDate now = new UDate();

        //最大件数
        int threCnt = __getThreadCount(con, bfiSid, now);
        int maxThreCnt = bUserMdl.getBurThreCnt();

        //ページ調整
        int maxPage = threCnt / maxThreCnt;
        if ((threCnt % maxThreCnt) > 0) {
            maxPage++;
        }
        int page = paramMdl.getBbs060page1();
        if (page < 1) {
            page = 1;
        } else if (page > maxPage) {
            page = maxPage;
        }
        paramMdl.setBbs060page1(page);
        paramMdl.setBbs060page2(page);
        //ページコンボ設定
        paramMdl.setBbsPageLabel(PageUtil.createPageOptions(threCnt, maxThreCnt));

        //スレッド一覧を取得する
        BulletinDao btDao = new BulletinDao(con);
        int start = (page - 1) * maxThreCnt + 1;
        int end = start + maxThreCnt - 1;
        paramMdl.setThreadList(
                btDao.getThreadList(bfiSid, userSid, now,
                        bUserMdl.getBurNewCnt(), start, end,
                        Integer.parseInt(paramMdl.getBbs060sortKey()),
                        Integer.parseInt(paramMdl.getBbs060orderKey())));


        //フォーラムのメンバー数を取得する
        paramMdl.setForumMemberCount(String.valueOf(btDao.getForumMemberCount(bfiSid)));

        List<BulletinDspModel> bullList = new ArrayList<BulletinDspModel>();

        //[サブコンテンツ]フォーラム一覧表示フラグ判定
        if (bUserMdl.getBurSubForum() == GSConstBulletin.BBS_MIDOKU_TRD_DSP) {
            //フォーラム一覧を取得
            Bbs010Biz biz010 = new Bbs010Biz();
            bUserMdl.setBurForCnt(-1);
            List<BulletinDspModel> list = biz010.getForumDataList(con, userSid, false, 1, bUserMdl);
            bullList = new ArrayList<BulletinDspModel>();
            for (BulletinDspModel mdl : list) {

                String forumName = StringUtilHtml.transToHTmlWithWbr(
                        StringUtilHtml.deleteHtmlTag(
                                StringUtilHtml.transToText(mdl.getBfiName())), 20);
                mdl.setBfiName(forumName);
                bullList.add(mdl);
            }
        }
        paramMdl.setForumList(bullList);

        List<BulletinDspModel> midokuList =
                                         new ArrayList<BulletinDspModel>();

        //[サブコンテンツ]未読スレッド表示フラグ判定
        if (bUserMdl.getBurSubUnchkThre() == GSConstBulletin.BBS_FORUM_DSP) {
            //未読スレッド一覧listを取得する
            midokuList = btDao.getThreadList2(userSid, bUserMdl.getBurThreMainCnt());
        }

        paramMdl.setNotReadThreadList(midokuList);

        //フォーラムSIDからアイコンバイナリSIDを取得する
        Bbs060Biz bbs060Biz = new Bbs060Biz();
        Long binSid = bbs060Biz.getIcoBinSid(paramMdl, con);
        paramMdl.setBbs060BinSid(binSid);


        //新規スレッドボタン表示フラグ
        if (adminFlg) {
            //システム管理者・プラグイン管理者ならば表示する。
            paramMdl.setBbs060createDspFlg(true);
        } else {
            //フォーラム管理者か編集メンバーならば表示する
            paramMdl.setBbs060createDspFlg(bbsBiz.isForumEditAuth(con, bfiSid, userSid));
        }

        //「全て既読にする」機能が使用可能かを判定する
        paramMdl.setBbs060mreadFlg(btDspMdl.getBfiMread() == GSConstBulletin.BBS_FORUM_MREAD_YES);
        if (paramMdl.isBbs060mreadFlg()) {
            //表示フォーラム内の未読スレッド件数を取得する
            paramMdl.setBbs060unreadCnt(btDao.getUnreadThreadCount(bfiSid, userSid));
        }

        log__.debug("End");
    }

    /**
     * <br>[機  能] スレッドの件数を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param bfiSid ユーザSID
     * @param now 現在日時
     * @return スレッド件数
     * @throws SQLException SQL実行例外
     */
    private int __getThreadCount(Connection con, int bfiSid, UDate now)
    throws SQLException {

        BulletinDao btDao = new BulletinDao(con);
        return btDao.getThreadCount(bfiSid, now);

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
    public Long getIcoBinSid(Bbs060ParamModel paramMdl, Connection con)
    throws SQLException {

        //閲覧フォーラムのアイコンバイナリSIDを取得する
        BbsForInfDao bfiDao = new BbsForInfDao(con);
        Long icoBinSid = bfiDao.selectIcoBinSid(paramMdl.getBbs010forumSid());
        paramMdl.setBbs060BinSid(icoBinSid);

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
     * <br>[機  能] 表示フォーラム内のスレッドを全て既読にする(掲示予定のものは除く)
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param userSid ユーザSID
     * @throws SQLException SQL実行例外
     */
    public void allReadThread(Connection con, Bbs060ParamModel paramMdl, int userSid)
    throws SQLException {

        int bfiSid = paramMdl.getBbs010forumSid();
        BbsThreViewDao dao = new BbsThreViewDao(con);
        dao.updateAllReadThreadInForum(bfiSid, userSid, userSid, new UDate());
        dao.insertAllReadThreadInForum(bfiSid, userSid, userSid, new UDate());
    }
}
