package jp.groupsession.v2.bmk.bmk030kn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.bmk.GSConstBookmark;
import jp.groupsession.v2.bmk.biz.BookmarkBiz;
import jp.groupsession.v2.bmk.bmk010.Bmk010Biz;
import jp.groupsession.v2.bmk.dao.BmkBelongLabelDao;
import jp.groupsession.v2.bmk.dao.BmkBookmarkDao;
import jp.groupsession.v2.bmk.dao.BmkLabelDao;
import jp.groupsession.v2.bmk.dao.BmkUrlDao;
import jp.groupsession.v2.bmk.model.BmkBelongLabelModel;
import jp.groupsession.v2.bmk.model.BmkBookmarkModel;
import jp.groupsession.v2.bmk.model.BmkLabelModel;
import jp.groupsession.v2.bmk.model.BmkUrlModel;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.model.RequestModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] ブックマーク登録確認画面のビジネスロジック
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bmk030knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bmk030knBiz.class);

    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;
    /** コネクション */
    private Connection con__ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl RequestModel
     */
    public Bmk030knBiz(Connection con, RequestModel reqMdl) {
        reqMdl__ = reqMdl;
        con__ = con;
    }

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Bmk030knParamModel
     * @param userMdl セッションユーザ情報
     * @throws Exception 実行例外
     */
    public void setInitData(Bmk030knParamModel paramMdl, BaseUserModel userMdl)
    throws Exception {
        log__.debug("初期表示処理");

        //タイトル(表示用)をセット
        BookmarkBiz bBiz = new BookmarkBiz(con__, reqMdl__);
        paramMdl.setBmk030knTitleDsp(bBiz.getStringCutList(60, paramMdl.getBmk030title()));
        //ＵＲＬ(表示用)をセット
        paramMdl.setBmk030knUrlDsp(bBiz.getStringCutList(60, paramMdl.getBmk020url()));
        //コメント(表示用)をセット
        paramMdl.setBmk030knCmtDsp(NullDefault.getString(
                StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getBmk030cmt()), ""));
    }

    /**
     * <br>[機  能] 登録・更新処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Bmk030knParamModel
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    public void doAddEdit(
        Bmk030knParamModel paramMdl,
        Connection con,
        MlCountMtController cntCon,
        int userSid) throws SQLException {

        boolean commitFlg = false;

        try {
            con.setAutoCommit(false);

            //固定値をセット
            if (paramMdl.getBmk030mode() == GSConstBookmark.BMK_KBN_KYOYU) {
                paramMdl.setBmk030public(GSConstBookmark.KOKAI_YES);
            }
            if (paramMdl.getBmk030mode() != GSConstBookmark.BMK_KBN_KOJIN) {
                paramMdl.setBmk030main(GSConstBookmark.DSP_NO);
            }

            //URLマスタの登録・更新、URLSID取得
            int urlSid = __doAddEditUrl(paramMdl, con, cntCon, userSid);
            //ブックマーク情報の登録・更新、ブックマークSID取得
            int bmkSid = __doAddEditBookmark(paramMdl, con, cntCon, userSid, urlSid);
            //ラベル情報の登録、ラベルSID取得
            ArrayList<Integer> labelList = __doAddEditLabel(paramMdl, con, cntCon, userSid);
            //ラベル付与情報の登録
            __doAddEditBelongLabel(con, userSid, bmkSid, labelList);

            //URLマスタのタイトル、公開日を更新
            Bmk010Biz biz = new Bmk010Biz(reqMdl__);
            biz.updateUrlPub(urlSid, userSid, con);

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

    /**
     * <br>[機  能] URLマスタの登録・更新処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Bmk030knParamModel
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @return URLSID
     * @throws SQLException SQL実行例外
     */
    private int __doAddEditUrl(
        Bmk030knParamModel paramMdl,
        Connection con,
        MlCountMtController cntCon,
        int userSid) throws SQLException {
        log__.debug("URLマスタの登録・更新処理");

        int urlSid = 0;
        UDate now = new UDate();

        //URLSIDを取得
        BmkUrlDao uDao = new BmkUrlDao(con);
        BmkUrlModel uModel = uDao.select(paramMdl.getBmk020url());

        if (uModel != null) {
            //URLSID
            urlSid =  uModel.getBmuSid();
            //更新用Model作成
            if (paramMdl.getBmk030public() == GSConstBookmark.KOKAI_YES) {
                uModel.setBmuTitle(paramMdl.getBmk030title());
            }
            if (uModel.getBmuPubDate() == null
                    && paramMdl.getBmk030public() == GSConstBookmark.KOKAI_YES) {
                uModel.setBmuPubDate(now);
            }
            uModel.setBmuEuid(userSid);
            uModel.setBmuEdate(now);

            //更新処理
            uDao.update(uModel);

        } else {
            //URLSID採番
            urlSid = (int) cntCon.getSaibanNumber(GSConstBookmark.SBNSID_BOOKMARK,
                                                  GSConstBookmark.SBNSID_SUB_URL,
                                                  userSid);
            //登録用Model作成
            uModel = new BmkUrlModel();
            uModel.setBmuSid(urlSid);
            uModel.setBmuUrl(paramMdl.getBmk020url());
            uModel.setBmuTitle(paramMdl.getBmk030title());
            if (paramMdl.getBmk030public() == GSConstBookmark.KOKAI_YES) {
                uModel.setBmuPubDate(now);
            } else {
                uModel.setBmuPubDate(null);
            }
            uModel.setBmuAuid(userSid);
            uModel.setBmuAdate(now);
            uModel.setBmuEuid(userSid);
            uModel.setBmuEdate(now);

            //登録処理
            uDao.insert(uModel);
        }

        return urlSid;
    }

    /**
     * <br>[機  能] ブックマーク情報の登録・更新処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Bmk030knParamModel
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @param urlSid URLSID
     * @return ブックマークSID
     * @throws SQLException SQL実行例外
     */
    private int __doAddEditBookmark(
        Bmk030knParamModel paramMdl,
        Connection con,
        MlCountMtController cntCon,
        int userSid,
        int urlSid) throws SQLException {
        log__.debug("ブックマーク情報の登録・更新処理");

        UDate now = new UDate();
        int bmkSid = 0;

        if (paramMdl.getProcMode() == GSConstBookmark.BMK_MODE_TOUROKU) {
            //ブックマークSID採番
            bmkSid = (int) cntCon.getSaibanNumber(GSConstBookmark.SBNSID_BOOKMARK,
                                                  GSConstBookmark.SBNSID_SUB_BMK,
                                                  userSid);
        } else if (paramMdl.getProcMode() == GSConstBookmark.BMK_MODE_EDIT) {
            bmkSid = paramMdl.getEditBmkSid();
        }

        //登録用Model作成
        BmkBookmarkModel bMdl = new BmkBookmarkModel();
        bMdl.setBmkSid(bmkSid);
        bMdl.setBmkKbn(paramMdl.getBmk030mode());
        if (paramMdl.getBmk030mode() == GSConstBookmark.BMK_KBN_KOJIN) {
            bMdl.setUsrSid(userSid);
        } else {
            bMdl.setUsrSid(-1);
        }
        if (paramMdl.getBmk030mode() == GSConstBookmark.BMK_KBN_GROUP) {
            bMdl.setGrpSid(paramMdl.getBmk030groupSid());
        } else {
            bMdl.setGrpSid(-1);
        }
        bMdl.setBmuSid(urlSid);
        bMdl.setBmkTitle(paramMdl.getBmk030title());
        bMdl.setBmkCmt(paramMdl.getBmk030cmt());
        bMdl.setBmkScore(paramMdl.getBmk030score());
        bMdl.setBmkPublic(paramMdl.getBmk030public());
        bMdl.setBmkMain(paramMdl.getBmk030main());
        bMdl.setBmkSort(0);
        bMdl.setBmkAuid(userSid);
        bMdl.setBmkAdate(now);
        bMdl.setBmkEuid(userSid);
        bMdl.setBmkEdate(now);

        //登録・更新処理
        BmkBookmarkDao bDao = new BmkBookmarkDao(con);
        if (paramMdl.getProcMode() == GSConstBookmark.BMK_MODE_TOUROKU) {
            bDao.insert(bMdl);
        } else if (paramMdl.getProcMode() == GSConstBookmark.BMK_MODE_EDIT) {
            bDao.update(bMdl);
        }

        return bmkSid;
    }


    /**
     * <br>[機  能] ラベル情報の登録処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Bmk030knParamModel
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @return ラベルSIDリスト
     * @throws SQLException SQL実行例外
     */
    private ArrayList<Integer> __doAddEditLabel(
        Bmk030knParamModel paramMdl,
        Connection con,
        MlCountMtController cntCon,
        int userSid) throws SQLException {
        log__.debug("ラベル情報の登録処理");

        ArrayList<Integer> labelSidList = new ArrayList<Integer>();
        UDate now = new UDate();

        //ラベル名を半角スペースで分割
        String labelName = new String(paramMdl.getBmk030label());
        String[] labelNameList = labelName.split(" ");

        //ラベル名を取得
        for (int i = 0; i < labelNameList.length; i++) {

            //ラベル名
            String blbName = labelNameList[i];
            if (blbName.equals("")) {
                continue;
            }
            //ラベルSID取得
            BmkLabelDao lDao = new BmkLabelDao(con);
            BmkLabelModel lMdl = lDao.select(paramMdl.getBmk030mode(), userSid,
                                             paramMdl.getBmk030groupSid(), blbName);
            int blbSid = 0;
            if (lMdl == null) {
                //ラベルSID採番
                blbSid = (int) cntCon.getSaibanNumber(GSConstBookmark.SBNSID_BOOKMARK,
                                                      GSConstBookmark.SBNSID_SUB_LABEL,
                                                      userSid);
                //登録処理
                lMdl = new BmkLabelModel();
                lMdl.setBlbSid(blbSid);
                lMdl.setBlbKbn(paramMdl.getBmk030mode());
                if (paramMdl.getBmk030mode() == GSConstBookmark.BMK_KBN_KOJIN) {
                    lMdl.setUsrSid(userSid);
                } else {
                    lMdl.setUsrSid(-1);
                }
                if (paramMdl.getBmk030mode() == GSConstBookmark.BMK_KBN_GROUP) {
                    lMdl.setGrpSid(paramMdl.getBmk030groupSid());
                } else {
                    lMdl.setGrpSid(-1);
                }
                lMdl.setBlbName(blbName);
                lMdl.setBlbAuid(userSid);
                lMdl.setBlbAdate(now);
                lMdl.setBlbEuid(userSid);
                lMdl.setBlbEdate(now);
                lDao.insert(lMdl);

            } else {
                blbSid = lMdl.getBlbSid();

            }

            //ラベルSIDリスト作成
            labelSidList.add(blbSid);
        }

        return labelSidList;
    }

    /**
     * <br>[機  能] ラベル付与情報の登録処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param userSid ログインユーザSID
     * @param bmkSid ブックマークSID
     * @param labelList ラベルSIDリスト
     * @throws SQLException SQL実行例外
     */
    private void __doAddEditBelongLabel(
        Connection con,
        int userSid,
        int bmkSid,
        ArrayList<Integer> labelList) throws SQLException {
        log__.debug("ラベル付与情報の登録処理");

        //削除処理
        BmkBelongLabelDao blDao = new BmkBelongLabelDao(con);
        blDao.deleteBmkSid(bmkSid);

        UDate now = new UDate();

        //登録処理
        for (Integer blbSid : labelList) {
            BmkBelongLabelModel blMdl = new BmkBelongLabelModel();
            blMdl.setBmkSid(bmkSid);
            blMdl.setBlbSid(blbSid);
            blMdl.setBblAuid(userSid);
            blMdl.setBblAdate(now);
            blMdl.setBblEuid(userSid);
            blMdl.setBblEdate(now);
            blDao.insert(blMdl);
        }
    }
}