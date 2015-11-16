package jp.groupsession.v2.bbs.bbs010;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.PageUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.bbs.BbsBiz;
import jp.groupsession.v2.bbs.GSConstBulletin;
import jp.groupsession.v2.bbs.dao.BbsForInfDao;
import jp.groupsession.v2.bbs.dao.BulletinDao;
import jp.groupsession.v2.bbs.model.BbsUserModel;
import jp.groupsession.v2.bbs.model.BulletinDspModel;
import jp.groupsession.v2.bbs.model.BulletinNewBbsModel;
import jp.groupsession.v2.bbs.model.BulletinSearchModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 掲示板 フォーラム一覧画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bbs010Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bbs010Biz.class);

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
    public void setInitData(Bbs010ParamModel paramMdl, Connection con, int userSid, boolean admin)
    throws Exception {
        log__.debug("START");

        //掲示板個人情報を取得
        BbsBiz bbsBiz = new BbsBiz();
        BbsUserModel bUserMdl = bbsBiz.getBbsUserData(con, userSid);
        if (admin) {
            paramMdl.setBbs010AdminFlg(1);
        } else {
            paramMdl.setBbs010AdminFlg(0);
        }

        //最大件数
        int forumCnt = __getForumCount(con, userSid, false);
        //ページ調整
        int maxPage = forumCnt / bUserMdl.getBurForCnt();
        if ((forumCnt % bUserMdl.getBurForCnt()) > 0) {
            maxPage++;
        }
        int page = paramMdl.getBbs010page1();
        if (page < 1) {
            page = 1;
        } else if (page > maxPage) {
            page = maxPage;
        }
        paramMdl.setBbs010page1(page);
        paramMdl.setBbs010page2(page);

        //ページコンボ設定
        paramMdl.setBbsPageLabel(PageUtil.createPageOptions(forumCnt, bUserMdl.getBurForCnt()));

        //フォーラム一覧設定
        paramMdl.setForumList(getForumDataList(con, userSid, admin, page, bUserMdl));

        ArrayList<BulletinNewBbsModel> shinThredList =
                                       new ArrayList<BulletinNewBbsModel>();
        if (bUserMdl.getBurSubNewThre() == GSConstBulletin.BBS_THRED_DSP) {
            //新着スレッド一覧を取得する
            BulletinDao btDao = new BulletinDao(con);
            List < BulletinDspModel > thredList =
                btDao.getThreadList(userSid, false, 10, Integer.MAX_VALUE, null, 0);

            if (!thredList.isEmpty()) {
                //表示用Modelに格納
                for (BulletinDspModel mdl : thredList) {
                    BulletinNewBbsModel newThredMdl = new BulletinNewBbsModel();
                    newThredMdl.setBfiSid(mdl.getBfiSid());
                    //半角文字対応
                    String name = mdl.getBfiName();
                    name = StringUtilHtml.transToHTmlWithWbr(
                            StringUtilHtml.deleteHtmlTag(StringUtilHtml.transToText(name)), 20);
                    newThredMdl.setBfiName(name);
                    newThredMdl.setBtiSid(mdl.getBtiSid());
                    //半角文字対応
                    String title = mdl.getBtiTitle();
                    title = StringUtilHtml.transToHTmlWithWbr(
                            StringUtilHtml.deleteHtmlTag(StringUtilHtml.transToText(title)), 20);
                    newThredMdl.setBtiTitle(title);
                    newThredMdl.setBfiSid(mdl.getBfiSid());
                    newThredMdl.setUserSid(mdl.getUserSid());
                    newThredMdl.setUserName(mdl.getUserName());
                    newThredMdl.setUserJkbn(mdl.getUserJkbn());
                    newThredMdl.setGrpSid(mdl.getGrpSid());
                    newThredMdl.setGrpName(mdl.getGrpName());
                    newThredMdl.setGrpJkbn(mdl.getGrpJkbn());

                    //フォーラムのアイコンバイナリSID取得
                    BulletinDspModel forMdl = btDao.getForumData(mdl.getBfiSid());
                    newThredMdl.setImgBinSid(forMdl.getBinSid());

                    String writeDate = mdl.getStrWriteDate();
                    //表示用に日付を整形
                    String strWriteDate = writeDate.substring(5, 21);
                    newThredMdl.setStrWriteDate(strWriteDate);
                    //スレッド未読フラグ
                    newThredMdl.setT_ReadFlg(mdl.getReadFlg());
                    //フォーラム未読フラグ
                    //フォーラム一覧からSIDの一致するフォーラムの状態を取得
                    for (BulletinDspModel forMdl2 : paramMdl.getForumList()) {
                        if (forMdl2.getBfiSid() == mdl.getBfiSid()) {
                            newThredMdl.setF_ReadFlg(forMdl2.getReadFlg());
                            break;
                        }
                    }
                    shinThredList.add(newThredMdl);
                }
            }
        }
        paramMdl.setShinThredList(shinThredList);

        log__.debug("End");
    }

    /**
     * <br>[機  能] フォーラム情報一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param userSid ユーザSID
     * @param admin 管理者フラグ true:管理者 false:管理者以外
     * @param page ページ
     * @param bUserMdl 掲示板個人情報
     * @return フォーラム情報一覧
     * @throws SQLException SQL実行時例外
     */
    public List<BulletinDspModel> getForumDataList(Connection con, int userSid, boolean admin,
                                                    int page, BbsUserModel bUserMdl)
    throws SQLException {


        BulletinDao btDao = new BulletinDao(con);
        int start = (page - 1) * bUserMdl.getBurForCnt();
        int end = 0;
        if (bUserMdl.getBurForCnt() > 0) {
            end = start + bUserMdl.getBurForCnt() - 1;
        }
        return btDao.getForumList(
                userSid, false, new UDate(), bUserMdl.getBurNewCnt(), start, end, admin);
    }

    /**
     * <br>[機  能] 検索結果件数を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param userSid ユーザSID
     * @param admin 管理者か否か true:管理者, false:一般ユーザ
     * @return 検索結果件数
     * @throws Exception 実行例外
     */
    public int getSearchCount(Bbs010ParamModel paramMdl, Connection con, int userSid, boolean admin)
    throws Exception {

        //掲示板個人情報を取得
        BulletinSearchModel searchMdl = new BulletinSearchModel();
        searchMdl.setUserSid(userSid);
        searchMdl.setAdmin(admin);

        searchMdl.setKeyword(paramMdl.getS_key());

        BulletinDao btDao = new BulletinDao(con);
        return btDao.getForumSearchCount(searchMdl);
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
     * <br>[機  能] フォーラムSIDとアイコンバイナリSIDを照合する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param userSid ユーザSID
     * @param bfiSid フォーラムSID
     * @param imgBinSid 画像バイナリSID
     * @return true:照合OK false:照合NG
     * @throws SQLException SQL実行例外
     */
    public boolean cheIcoHnt(Connection con, int userSid, int bfiSid, Long imgBinSid)
    throws SQLException {

        boolean icoCheckFlg = false;

        //フォーラム閲覧権限のチェック
        BbsBiz bbsBiz = new BbsBiz();
        boolean existForUsrFlg = bbsBiz.checkForumAuth(con, bfiSid, userSid);
        if (!existForUsrFlg) {
            return icoCheckFlg;
        }

        //フォーラムSIDとアイコンバイナリSIDの組み合わせチェック
        BbsForInfDao bfiDao = new BbsForInfDao(con);
        boolean existForIcoFlg = bfiDao.existBbsForIco(bfiSid, imgBinSid);

        if (existForIcoFlg) {
            icoCheckFlg = true;
        }

        return icoCheckFlg;
    }
}
