package jp.groupsession.v2.bmk.bmk140;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.bmk.GSConstBookmark;
import jp.groupsession.v2.bmk.dao.BmkBookmarkDao;
import jp.groupsession.v2.bmk.dao.BmkUconfDao;
import jp.groupsession.v2.bmk.model.BmkBookmarkModel;
import jp.groupsession.v2.bmk.model.BmkUconfModel;
import jp.groupsession.v2.cmn.model.RequestModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] メイン画面表示設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bmk140Biz {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bmk140Biz.class);
    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     */
    public Bmk140Biz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Bmk140ParamModel
     * @param sessionUserSid セッションユーザSID
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setInitData(
            Bmk140ParamModel paramMdl,
            int sessionUserSid,
            Connection con) throws SQLException {

        log__.debug("start");

        //DBより現在の設定を取得する。(なければデフォルト)
        BmkUconfDao uConfdao = new BmkUconfDao(con);
        BmkUconfModel uConfmodel = uConfdao.select(sessionUserSid);

        if (paramMdl.getBmk140MyKbn() == -1) {
            //個人ブックマーク　メイン表示区分初期化
            if (uConfmodel == null) {
                paramMdl.setBmk140MyKbn(GSConstBookmark.DSP_YES);
            } else {
                paramMdl.setBmk140MyKbn(uConfmodel.getBucMainMy());
            }
        }
        if (paramMdl.getBmk140NewKbn() == -1) {
            //新着ブックマーク　メイン表示区分初期化
            if (uConfmodel == null) {
                paramMdl.setBmk140NewKbn(GSConstBookmark.DSP_YES);
            } else {
                paramMdl.setBmk140NewKbn(uConfmodel.getBucMainNew());
            }
        }

        //新着ブックマーク表示日数初期化
        if (paramMdl.getBmk140newCnt() == 0) {
            if (uConfmodel == null) {
                paramMdl.setBmk140newCnt(GSConstBookmark.NEW_DEFO_DSP_COUNT);
            } else {
                paramMdl.setBmk140newCnt(uConfmodel.getBucNewCnt());
            }
        }

        if (paramMdl.getBmk140MyKbn() == GSConstBookmark.DSP_YES) {
            //個人ブックマークメイン表示区分：表示

            List<LabelValueBean> viewBmkList = new ArrayList<LabelValueBean>();
            List<LabelValueBean> notViewBmkList = new ArrayList<LabelValueBean>();

            //DBより現在の設定を取得する。
            BmkBookmarkDao dao = new BmkBookmarkDao(con);
            List<BmkBookmarkModel> dispList = dao.getUsrBookmark(sessionUserSid);

            if (!(dispList == null || dispList.isEmpty())) {
                int order = 1;
                for (BmkBookmarkModel dbDispMdl : dispList) {
                    //表示ブックマークリストの設定
                    if (dbDispMdl.getBmkMain() == GSConstBookmark.DSP_YES) {
                        viewBmkList.add(__createBmkOption(order++,
                                                            Integer.toString(dbDispMdl.getBmkSid()),
                                                            dbDispMdl.getBmkTitle()));
                    }
                }
                for (BmkBookmarkModel dbDispMdl : dispList) {
                    //非表示ブックマークリストの設定
                    if (dbDispMdl.getBmkMain() == GSConstBookmark.DSP_NO) {
                        notViewBmkList.add(__createBmkOption(order++,
                                Integer.toString(dbDispMdl.getBmkSid()),
                                dbDispMdl.getBmkTitle()));
                    }
                }
            }
            paramMdl.setBmk140ViewBmkLabel(viewBmkList);
            paramMdl.setBmk140NotViewBmkLabel(notViewBmkList);
        }

        paramMdl.setNewCntLabel(reqMdl__);
    }

    /**
     * <br>[機  能] ブックマークコンボの情報を作成する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param order 並び順
     * @param bmkSid ブックマークSID
     * @param title タイトル
     * @return ブックマークコンボの情報
     */
    private LabelValueBean __createBmkOption(int order, String bmkSid, String title) {
        StringBuilder labelValue = new StringBuilder();
        labelValue.append(order++);
        labelValue.append(":");
        labelValue.append(title);

        return new LabelValueBean(labelValue.toString(), bmkSid);
    }
    /**
     * <br>[機  能] メイン表示設定をDBに登録する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Bmk140ParamModel
     * @param sessionUserSid ユーザSID
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     * @return addEditFlg 登録モード(0:登録 1:編集)
     */
    public int setBmkUconfSetting(
            Bmk140ParamModel paramMdl,
            int sessionUserSid,
            Connection con) throws SQLException {

        boolean commitFlg = false;
        int addEditFlg = 0;

        try {
            //DBの存在確認
            BmkUconfDao dao = new BmkUconfDao(con);
            BmkUconfModel model = dao.select(sessionUserSid);
            //画面値セット
            BmkUconfModel crtMdl = createBmkUconfData(paramMdl, sessionUserSid);

            if (model == null) {
                crtMdl.setBucCount(Integer.parseInt(GSConstBookmark.DEFAULT_BMKCOUNT));
                dao.insert(crtMdl);
            } else {
                crtMdl.setBucCount(model.getBucCount());
                dao.update(crtMdl);
                addEditFlg = 1;
            }
            commitFlg = true;

        } catch (SQLException e) {
            log__.error("", e);
            throw e;
        } finally {
            if (commitFlg) {
                con.commit();
            }
        }
        return addEditFlg;
    }

    /**
     * <br>[機  能] メイン表示設定情報を作成
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Bmk140ParamModel
     * @param usrSid ユーザSID
     * @return BmkUconfModel 個人設定情報
     */
    public BmkUconfModel createBmkUconfData(
            Bmk140ParamModel paramMdl, int usrSid) {

        UDate nowDate = new UDate();
        BmkUconfModel mdl = new BmkUconfModel();
        mdl.setUsrSid(usrSid);
        mdl.setBucMainMy(paramMdl.getBmk140MyKbn());
        mdl.setBucMainNew(paramMdl.getBmk140NewKbn());
        mdl.setBucNewCnt(paramMdl.getBmk140newCnt());
        mdl.setBucAuid(usrSid);
        mdl.setBucAdate(nowDate);
        mdl.setBucEuid(usrSid);
        mdl.setBucEdate(nowDate);

        return mdl;
    }
    /**
     * <br>[機  能] 指定表示区分のブックマークリストを取得
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param userSid ログインユーザSID
     * @param kbn 表示区分
     * @throws SQLException SQL実行例外
     * @return 指定表示区分のブックマークリスト
     */
    private ArrayList<String> __getMainList(Connection con, int userSid, int kbn)
    throws SQLException {
        ArrayList<String> list = new ArrayList<String>();
        BmkBookmarkDao dao = new BmkBookmarkDao(con);
        List<BmkBookmarkModel> dispList = dao.getUsrBookmark(userSid);

        for (BmkBookmarkModel dbDispMdl : dispList) {
            if (dbDispMdl.getBmkMain() == kbn) {
                list.add(Integer.toString(dbDispMdl.getBmkSid()));
            }
        }
        return list;
    }
    /**
     * <br>[機  能] 表示ブックマークの削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Bmk140ParamModel
     * @param con コネクション
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    public void deleteViewBmk(Bmk140ParamModel paramMdl, Connection con, int userSid)
    throws SQLException {

        if (paramMdl.getBmk140SelectViewBmk() != null) {

            ArrayList<String> notViewList
                = __getMainList(con, userSid, GSConstBookmark.DSP_NO);

            ArrayList<String> viewList = new ArrayList<String>();

            if (paramMdl.getBmk140ViewBmkList() != null) {
                for (String bookmarkId : paramMdl.getBmk140ViewBmkList()) {
                    log__.debug("１：bookmarkId : " + bookmarkId);
                    viewList.add(bookmarkId);
                }
            }
            //選択されたメニューを表示メニュー一覧から削除
            for (String selectBookmark : paramMdl.getBmk140SelectViewBmk()) {
                log__.debug("２：bookmarkId : " + selectBookmark);

                //表示ブックマークから選択ブックマーク削除
                viewList.remove(selectBookmark);

                //非表示ブックマークに選択ブックマーク追加
                notViewList.add(selectBookmark);
            }
            __updateMaindisp(con, viewList, notViewList, userSid);
        }
    }

    /**
     * <br>[機  能] 表示ブックマークの追加を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Bmk140ParamModel
     * @param con コネクション
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    public void addViewBmk(Bmk140ParamModel paramMdl, Connection con, int userSid)
    throws SQLException {

        if (paramMdl.getBmk140SelectNotViewBmk() != null) {

            ArrayList<String> notViewList
                = __getMainList(con, userSid, GSConstBookmark.DSP_NO);

            ArrayList<String> viewList = new ArrayList<String>();

            if (paramMdl.getBmk140ViewBmkList() != null) {
                for (String bookmarkId : paramMdl.getBmk140ViewBmkList()) {
                    viewList.add(bookmarkId);
                }
            }

            //選択されたメニューを表示メニュー一覧に追加
            for (String addBookmark : paramMdl.getBmk140SelectNotViewBmk()) {
                log__.debug("２：bookmarkId : " + addBookmark);

                //選択ブックマークを表示ブックマークに追加
                viewList.add(addBookmark);

                //非表示リストから選択ブックマークを削除
                notViewList.remove(addBookmark);
            }

            __updateMaindisp(con, viewList, notViewList, userSid);
        }
    }

    /**
     * <br>[機  能] 表示ブックマークの表示順を変更する
     * <br>[解  説] 指定された表示ブックマークの表示順を上げる
     * <br>[備  考]
     * @param paramMdl Bmk140ParamModel
     * @param con コネクション
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    public void upOrderViewBmk(Bmk140ParamModel paramMdl, Connection con, int userSid)
    throws SQLException {

        if (paramMdl.getBmk140SelectViewBmk() != null
        && paramMdl.getBmk140ViewBmkList() != null) {

            ArrayList<String> notViewList
                = __getMainList(con, userSid, GSConstBookmark.DSP_NO);

            ArrayList<String> viewList = new ArrayList<String>();
            for (String bookmarkId : paramMdl.getBmk140ViewBmkList()) {
                viewList.add(bookmarkId);
            }

            //選択されたブックマークの表示順を上げる
            for (String upBookmark : paramMdl.getBmk140SelectViewBmk()) {
                int index = viewList.indexOf(upBookmark);
                if (index > 0) {
                    viewList.remove(upBookmark);
                    viewList.add(index - 1, upBookmark);
                }
            }

            __updateMaindisp(con, viewList, notViewList, userSid);
        }
    }

    /**
     * <br>[機  能] 表示ブックマークの表示順を変更する
     * <br>[解  説] 指定された表示ブックマークの表示順を下げる
     * <br>[備  考]
     * @param paramMdl Bmk140ParamModel
     * @param con コネクション
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    public void downOrderViewBmk(Bmk140ParamModel paramMdl, Connection con, int userSid)
    throws SQLException {

        if (paramMdl.getBmk140SelectViewBmk() != null
        && paramMdl.getBmk140ViewBmkList() != null) {

            ArrayList<String> notViewList
                = __getMainList(con, userSid, GSConstBookmark.DSP_NO);

            ArrayList<String> viewList = new ArrayList<String>();

            for (String bookmarkId : paramMdl.getBmk140ViewBmkList()) {
                viewList.add(bookmarkId);
            }

            //選択されたブックマークの表示順を上げる
            for (String downBookmark : paramMdl.getBmk140SelectViewBmk()) {
                int index = viewList.indexOf(downBookmark);
                if (index < viewList.size() - 1) {
                    viewList.remove(downBookmark);
                    viewList.add(index + 1, downBookmark);
                }
            }

            __updateMaindisp(con, viewList, notViewList, userSid);
        }
    }

    /**
     * <br>[機  能] 個人ブックマークのメイン表示区分の更新を行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param con コネクション
     * @param viewList 表示ブックマーク一覧
     * @param notViewList 非表示ブックマーク一覧
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    private void __updateMaindisp(Connection con, List<String> viewList
            , List<String> notViewList, int userSid)
    throws SQLException {

        boolean commit = false;
        try {

            BmkBookmarkModel model = createBmkData(userSid);
            BmkBookmarkDao dao = new BmkBookmarkDao(con);
            int order = 1;
            //表示ブックマーク
            for (String addId : viewList) {
                model.setBmkSid(Integer.parseInt(addId));
                model.setBmkMain(GSConstBookmark.DSP_YES);
                model.setBmkSort(order++);
                dao.updateMainKbn(model, userSid);
            }
            //非表示ブックマーク
            for (String deleteId : notViewList) {
                model.setBmkSid(Integer.parseInt(deleteId));
                model.setBmkMain(GSConstBookmark.DSP_NO);
                model.setBmkSort(order++);
                dao.updateMainKbn(model, userSid);
            }

            con.commit();
            commit = true;
        } catch (SQLException e) {
            log__.error("メイン表示設定の登録", e);
            throw e;
        } finally {
            if (!commit) {
                con.rollback();
            }
        }
    }
    /**
     * <br>[機  能] ブックマーク情報を作成
     * <br>[解  説]
     * <br>[備  考]
     * @param usrSid ユーザSID
     * @return BmkBookmarkModel ブックマーク情報
     */
    public BmkBookmarkModel createBmkData(int usrSid) {

        UDate nowDate = new UDate();
        BmkBookmarkModel mdl = new BmkBookmarkModel();
        mdl.setUsrSid(usrSid);
        mdl.setBmkAuid(usrSid);
        mdl.setBmkAdate(nowDate);
        mdl.setBmkEuid(usrSid);
        mdl.setBmkEdate(nowDate);

        return mdl;
    }

}
