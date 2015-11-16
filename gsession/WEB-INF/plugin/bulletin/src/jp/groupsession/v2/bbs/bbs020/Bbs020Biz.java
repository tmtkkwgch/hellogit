package jp.groupsession.v2.bbs.bbs020;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.PageUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.bbs.BbsBiz;
import jp.groupsession.v2.bbs.GSConstBulletin;
import jp.groupsession.v2.bbs.dao.BbsForInfDao;
import jp.groupsession.v2.bbs.dao.BulletinDao;
import jp.groupsession.v2.bbs.model.BbsForInfModel;
import jp.groupsession.v2.bbs.model.BbsUserModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 掲示板 フォーラム管理画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bbs020Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bbs020Biz.class);

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param userSid ユーザSID
     * @param admin 管理者か否か true:管理者, false:一般ユーザ
     * @throws Exception 実行例外
     */
    public void setInitData(Bbs020ParamModel paramMdl, Connection con, int userSid, boolean admin)
    throws Exception {
        log__.debug("START");

        //表示順が設定されていない場合、表示順を設定する。
        BbsForInfDao dao = new BbsForInfDao(con);
        if (dao.count() > 0) {
            log__.debug("--- 表示順設定 ---");
            __updateSortAll(con);
        }
        //掲示板個人情報を取得
        BbsBiz bbsBiz = new BbsBiz();
        BbsUserModel bUserMdl = bbsBiz.getBbsUserData(con, userSid);

        //最大件数
        int forumCnt = __getForumCount(con, userSid, true);
        //ページ調整
        int maxPage = forumCnt / bUserMdl.getBurForCnt();
        if ((forumCnt % bUserMdl.getBurForCnt()) > 0) {
            maxPage++;
        }
        int page = paramMdl.getBbs020page1();
        if (page < 1) {
            page = 1;
        } else if (page > maxPage) {
            page = maxPage;
        }
        paramMdl.setBbs020page1(page);
        paramMdl.setBbs020page2(page);

        //ページコンボ設定
        paramMdl.setBbsPageLabel(PageUtil.createPageOptions(forumCnt, bUserMdl.getBurForCnt()));

        BulletinDao btDao = new BulletinDao(con);
        int start = (page - 1) * bUserMdl.getBurForCnt();
        int end = start + bUserMdl.getBurForCnt() - 1;
        paramMdl.setForumList(btDao.getForumList(userSid, admin, new UDate(),
                                            bUserMdl.getBurNewCnt(), start, end, admin));

        log__.debug("End");
    }

    /**
     * <br>[機  能] フォーラム情報を削除する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @throws Exception 実行例外
     */
    public void deleteForumData(Bbs020ParamModel paramMdl, Connection con)
    throws Exception {

        //ラジオボタンの値を設定する。
        int indexRadio = paramMdl.getBbs020indexRadio();
        BbsForInfDao forDao = new BbsForInfDao(con);
        BbsForInfModel bean = new BbsForInfModel();
        bean.setBfiSid(paramMdl.getBbs020forumSid());
        BbsForInfModel model = forDao.select(bean);
        int delIndex = model.getBfiSort();
        if (indexRadio > delIndex) {
            paramMdl.setBbs020indexRadio(indexRadio - 1);
        } else if (indexRadio == delIndex) {
            paramMdl.setBbs020indexRadio(1);
        }

        //削除処理
        BulletinDao btDao = new BulletinDao(con);
        btDao.deleteForumData(paramMdl.getBbs020forumSid());

        //並び順の変更を行う。
        List<BbsForInfModel> forList = forDao.select(delIndex);
        if (forList != null) {
            for (BbsForInfModel mdl : forList) {
                mdl.setBfiSort(mdl.getBfiSort() - 1);
                forDao.updateBBSSort(mdl);
            }
        }
    }

    /**
     * <br>[機  能] フォーラムの件数を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param userSid ユーザSID
     * @param admin 管理者か否か true:管理者, false:一般ユーザ
     * @return フォーラム件数
     * @throws SQLException SQL実行例外
     */
    private int __getForumCount(Connection con, int userSid, boolean admin)
    throws SQLException {

        BulletinDao btDao = new BulletinDao(con);
        return btDao.getForumCount(userSid, admin);

    }

    /**
     * <br>[機  能] フォーラムの並び順が変更可能か判定し、可能であれば変更する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param mode モード
     * @param userSid ユーザSID
     * @param admin 管理者権限 true：管理者ユーザ、false：一般ユーザ
     * @throws Exception 実行例外
     */
    public void updateSort(Bbs020ParamModel paramMdl, Connection con, String mode,
                        int userSid, boolean admin)
    throws Exception {

        int index = paramMdl.getBbs020indexRadio();
        int forCnt = __getForumCount(con, userSid, admin);

        if (forCnt > index && mode.equals(GSConstBulletin.FORUM_SORT_DOWN)) {
            __updateSort(paramMdl, con, mode);
        } else if (index > 1 && mode.equals(GSConstBulletin.FORUM_SORT_UP)) {
            __updateSort(paramMdl, con, mode);
        }
    }

    /**
     * <br>[機  能] フォーラムの並び順を変更する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param mode モード
     * @throws Exception 実行例外
     */
    private void __updateSort(Bbs020ParamModel paramMdl, Connection con, String mode)
    throws Exception {

        int index = paramMdl.getBbs020indexRadio();
        BbsForInfDao dao = new BbsForInfDao(con);
        List < BbsForInfModel > forList = new ArrayList < BbsForInfModel >();
        forList = dao.select();
        if (mode.equals(GSConstBulletin.FORUM_SORT_UP)) {
            paramMdl.setBbs020indexRadio(paramMdl.getBbs020indexRadio() - 1);
        } else if (mode.equals(GSConstBulletin.FORUM_SORT_DOWN)) {
            paramMdl.setBbs020indexRadio(paramMdl.getBbs020indexRadio() + 1);
        }


        for (BbsForInfModel model : forList) {

            if (mode.equals(GSConstBulletin.FORUM_SORT_UP)) {
                if (index == model.getBfiSort()) {
                    model.setBfiSort(model.getBfiSort() - 1);
                    dao.updateBBSSort(model);
                } else if (model.getBfiSort() == index - 1) {
                    model.setBfiSort(model.getBfiSort() + 1);
                    dao.updateBBSSort(model);
                }

            } else if (mode.equals(GSConstBulletin.FORUM_SORT_DOWN)) {
                if (index == model.getBfiSort()) {
                    model.setBfiSort(model.getBfiSort() + 1);
                    dao.updateBBSSort(model);
                } else if (model.getBfiSort() == index + 1) {
                    model.setBfiSort(model.getBfiSort() - 1);
                    dao.updateBBSSort(model);
                }
            }
        }
    }

    /**
     * <br>[機  能] 全フォーラムの並び順を設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @throws Exception 実行例外
     */
    private void __updateSortAll(Connection con)
    throws Exception {

        con.setAutoCommit(false);
        boolean commitFlg = false;

        try {

            int index = 1;
            BbsForInfDao dao = new BbsForInfDao(con);
            List < BbsForInfModel > forList = new ArrayList < BbsForInfModel >();
            forList = dao.select();

            for (BbsForInfModel model : forList) {

                model.setBfiSort(index);
                dao.updateBBSSort(model);
                index++;
            }
            commitFlg = true;

        } catch (SQLException e) {
            log__.error("SQLException", e);
            throw e;
        } finally {
            if (commitFlg) {
                con.commit();
            } else {
                JDBCUtil.rollback(con);
            }
        }
    }
}
