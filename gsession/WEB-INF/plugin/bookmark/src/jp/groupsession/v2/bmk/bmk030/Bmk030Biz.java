package jp.groupsession.v2.bmk.bmk030;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.bmk.GSConstBookmark;
import jp.groupsession.v2.bmk.biz.BmkHtmlDataBiz;
import jp.groupsession.v2.bmk.biz.BookmarkBiz;
import jp.groupsession.v2.bmk.bmk010.Bmk010Biz;
import jp.groupsession.v2.bmk.bmk010.dao.Bmk010Dao;
import jp.groupsession.v2.bmk.dao.BmkBookmarkDao;
import jp.groupsession.v2.bmk.dao.BmkLabelDataDao;
import jp.groupsession.v2.bmk.dao.BmkUrlDao;
import jp.groupsession.v2.bmk.model.BmkBookmarkModel;
import jp.groupsession.v2.bmk.model.BmkHtmlDataModel;
import jp.groupsession.v2.bmk.model.BmkLabelDataModel;
import jp.groupsession.v2.bmk.model.BmkLabelModel;
import jp.groupsession.v2.bmk.model.BmkUrlModel;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.base.CmnGroupmDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnGroupmModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;



/**
 * <br>[機  能] ブックマーク登録画面のビジネスロジック
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bmk030Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bmk030Biz.class);
    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     */
    public Bmk030Biz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Bmk030ParamModel
     * @param con コネクション
     * @param userMdl セッションユーザ情報
     * @param userSid セッションユーザSID
     * @throws Exception 実行例外
     */
    public void setInitData(
            Bmk030ParamModel paramMdl, Connection con, BaseUserModel userMdl, int userSid)
    throws Exception {
        log__.debug("初期表示処理");

        //編集モードのとき
        if (paramMdl.getProcMode() == GSConstBookmark.BMK_MODE_EDIT) {
            //ブックマーク情報を取得
            BmkBookmarkDao bDao = new BmkBookmarkDao(con);
            BmkBookmarkModel bModel = bDao.select(paramMdl.getEditBmkSid());

            //登録先ブックマーク区分セット
            if (paramMdl.getBmk030mode() == -1) {
                paramMdl.setBmk030mode(bModel.getBmkKbn());
            }

            //グループSIDセット
            if (paramMdl.getBmk030groupSid() == -1) {
                paramMdl.setBmk030groupSid(bModel.getGrpSid());
            }

            //ＵＲＬセット
            BmkUrlDao uDao = new BmkUrlDao(con);
            BmkUrlModel uModel = uDao.select(bModel.getBmuSid());
            paramMdl.setBmk020url(NullDefault.getString(
                    paramMdl.getBmk020url(), uModel.getBmuUrl()));
            //タイトルセット
            paramMdl.setBmk030title(NullDefault.getString(
                    paramMdl.getBmk030title(), bModel.getBmkTitle()));
            //ラベルセット
            paramMdl.setBmk030label(NullDefault.getString(
                    paramMdl.getBmk030label(), __getLabelString(con, paramMdl.getEditBmkSid())));
            //コメントセット
            paramMdl.setBmk030cmt(NullDefault.getString(
                    paramMdl.getBmk030cmt(), bModel.getBmkCmt()));

            //評価セット
            if (paramMdl.getBmk030score() == -1) {
                paramMdl.setBmk030score(bModel.getBmkScore());
            }

            //公開区分セット
            if (paramMdl.getBmk030public() == -1) {
                paramMdl.setBmk030public(bModel.getBmkPublic());
            }

            //メイン表示区分セット
            if (paramMdl.getBmk030main() == -1) {
                paramMdl.setBmk030main(bModel.getBmkMain());
            }
        }

        //初期値セット 登録先ブックマーク区分
        if (paramMdl.getBmk030mode() == -1) {
            if (paramMdl.getEditBmuSid() == -1) {
                //初期値セット 登録先
                paramMdl.setBmk030mode(paramMdl.getBmk010mode());
                //初期値セット グループSID
                if (paramMdl.getBmk010mode() == GSConstBookmark.BMK_KBN_GROUP) {
                    paramMdl.setBmk030groupSid(paramMdl.getBmk010groupSid());
                }
            } else {
                //初期値セット 登録先（追加ボタンから遷移してきたとき）
                paramMdl.setBmk030mode(GSConstBookmark.BMK_KBN_KOJIN);
            }
        }

        //ＵＲＬ(表示用)セット
        BookmarkBiz bBiz = new BookmarkBiz(con, reqMdl__);
        paramMdl.setBmk030UrlDsp(bBiz.getStringCutList(60, paramMdl.getBmk020url()));

        //初期値セット タイトル、コメント
        //ファイルパスはセットしない
        if (paramMdl.getBmk030title() == null
                && !paramMdl.getBmk020url().startsWith("\\")) {

            String title = "";
            String comment = "";

            //URLマスタからタイトル取得
            title = __getTitleFromDb(con, paramMdl.getBmk020url());

            //URLからタイトル、コメント取得
            BmkHtmlDataBiz biz = new BmkHtmlDataBiz();
            BmkHtmlDataModel model = biz.getHtmlData(con, paramMdl.getBmk020url());
            if (title.equals("")) {
                title = model.getTitle();
                BookmarkBiz bmkBiz = new BookmarkBiz(con, reqMdl__);
                title = bmkBiz.formatBookmarkTitle(title);
            }

            comment = NullDefault.getString(model.getDescription(), "");

            if (comment.length() > GSConstBookmark.MAX_LENGTH_CMT) {
                //取得コメント1000文字以上はカットする
                comment = comment.substring(0, GSConstBookmark.MAX_LENGTH_CMT);
            }

            //タイトル
            paramMdl.setBmk030title(__transToDsp(title));
            //コメント
            paramMdl.setBmk030cmt(
                    NullDefault.getString(paramMdl.getBmk030cmt(), __transToDsp(comment)));
        }

        //初期値セット 評価
        if (paramMdl.getBmk030score() == -1) {
            paramMdl.setBmk030score(3);
        }

        //初期値セット 公開区分
        if (paramMdl.getBmk030public() == -1) {
            paramMdl.setBmk030public(GSConstBookmark.KOKAI_YES);
        }

        //初期値セット メイン表示区分
        if (paramMdl.getBmk030main() == -1) {
            paramMdl.setBmk030main(GSConstBookmark.DSP_NO);
        }

        //グループコンボを設定
        BookmarkBiz bmkBiz = new BookmarkBiz(con, reqMdl__);
        paramMdl.setBmk030groupCmbList(bmkBiz.getEditGroupList(con, userMdl));

        //グループSIDがグループコンボに存在しないとき、グループSID＝"選択してください"をセット
        boolean usrFlg = false;
        for (LabelValueBean labelValue : paramMdl.getBmk030groupCmbList()) {
            if (Integer.parseInt(labelValue.getValue()) == paramMdl.getBmk030groupSid()) {
                usrFlg = true;
                break;
            }
        }
        if (!usrFlg) {
            paramMdl.setBmk030groupSid(-1);
        }

        //評価コンボを設定
        paramMdl.setBmk030scoreCmbList(__getScoreList());

        GsMessage gsMsg = new GsMessage(reqMdl__);
        String msg = "";
        String colon = gsMsg.getMessage("wml.215");

        //登録先ブックマーク区分名セット
        if (paramMdl.getBmk030mode() == GSConstBookmark.BMK_KBN_KOJIN) {
            msg = gsMsg.getMessage("bmk.30");
            paramMdl.setBmk030modeName(msg);

        } else if (paramMdl.getBmk030mode() == GSConstBookmark.BMK_KBN_GROUP) {
            msg = gsMsg.getMessage("bmk.51");
            paramMdl.setBmk030modeName(msg + " " + colon);
            //グループ名を取得
            CmnGroupmDao gDao = new CmnGroupmDao(con);
            CmnGroupmModel gModel = gDao.select(paramMdl.getBmk030groupSid());
            if (gModel != null) {
                paramMdl.setBmk030modeName(paramMdl.getBmk030modeName() + gModel.getGrpName());
            }

        } else if (paramMdl.getBmk030mode() == GSConstBookmark.BMK_KBN_KYOYU) {
            msg = gsMsg.getMessage("bmk.34");
            paramMdl.setBmk030modeName(msg);
        }

        //ラベル選択POPUPで選択したラベル名セット
        if (paramMdl.getBmk040label() != null && !paramMdl.getBmk040label().equals("")) {
            String label = paramMdl.getBmk030label().trim();
            if (!label.equals("")) {
                label += " ";
            }
            paramMdl.setBmk030label(label + paramMdl.getBmk040label());
        }

        log__.debug("フラグの中身！" + paramMdl.isBmk030InitFlg());

        //ラベルを設定
        if (paramMdl.isBmk030InitFlg()) {
            BmkLabelDataDao dao = new BmkLabelDataDao(con);
            ArrayList<BmkLabelDataModel> lblMdl
                = dao.select(paramMdl.getBmk010mode(), userSid, paramMdl.getBmk010groupSid());

            String title = NullDefault.getString(paramMdl.getBmk030title(), "").toLowerCase();
            String comment = NullDefault.getString(paramMdl.getBmk030cmt(), "").toLowerCase();

            for (BmkLabelDataModel bldMdl : lblMdl) {
                String label = paramMdl.getBmk030label();
                String labelname = bldMdl.getBlbName();
                if (title.indexOf(labelname.toLowerCase()) != -1
                    || comment.indexOf(labelname.toLowerCase()) != -1) {
                    if (label == null) {
                        paramMdl.setBmk030label(labelname);
                    } else {
                        paramMdl.setBmk030label(label + " " + labelname);
                    }
                }
            }
            paramMdl.setBmk030InitFlg(false);
        }
    }

    /**
     * <br>[機  能] 評価コンボを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @return ArrayList
     */
    private ArrayList<LabelValueBean> __getScoreList() {

        GsMessage gsMsg = new GsMessage(reqMdl__);
        String star1 = gsMsg.getMessage("tcd.148");
        String star2 = gsMsg.getMessage("tcd.149");
        String star3 = gsMsg.getMessage("tcd.150");
        String star4 = gsMsg.getMessage("tcd.151");
        String star5 = gsMsg.getMessage("tcd.152");
        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        labelList.add(new LabelValueBean(star1 + " (1)", String.valueOf(1)));
        labelList.add(new LabelValueBean(star2 + " (2)", String.valueOf(2)));
        labelList.add(new LabelValueBean(star3 + " (3)", String.valueOf(3)));
        labelList.add(new LabelValueBean(star4 + " (4)", String.valueOf(4)));
        labelList.add(new LabelValueBean(star5 + " (5)", String.valueOf(5)));

        return labelList;
    }

    /**
     * <br>[機  能] ラベル一覧を半角スペースで区切って取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param bmkSid ブックマークSID
     * @return ラベル
     * @throws SQLException SQL実行例外
     */
    private String __getLabelString(Connection con, int bmkSid) throws SQLException {

        String ret = "";

        Bmk010Dao bDao = new Bmk010Dao(con, reqMdl__);
        List<BmkLabelModel> labelList = bDao.getBmkLabelList(bmkSid);

        for (BmkLabelModel model : labelList) {
            if (model.getBlbSid() == Bmk010Biz.NO_LABEL_SID) {
                continue;
            }
            ret += model.getBlbName() + " ";
        }

        return ret;
    }

    /**
     * <br>[機  能] 削除するブックマークのタイトルを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Bmk030ParamModel
     * @return タイトル
     * @throws SQLException SQL実行例外
     */
    public String getDeleteBmkName(Connection con, Bmk030ParamModel paramMdl) throws SQLException {
        BmkBookmarkDao bDao = new BmkBookmarkDao(con);
        return bDao.select(paramMdl.getEditBmkSid()).getBmkTitle();
    }

    /**
     * <br>[機  能] 削除処理
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Bmk030ParamModel
     * @param userSid ユーザSID
     * @param con コネクション
     * @throws SQLException SQL実行例外
     */
    public void deleteBmk(Bmk030ParamModel paramMdl, int userSid, Connection con)
    throws SQLException {

        try {
            con.setAutoCommit(false);

            //ブックマークSID
            int bmkSid = paramMdl.getEditBmkSid();
            //削除実行
            Bmk010Biz bBiz = new Bmk010Biz(reqMdl__);
            bBiz.deleteBookmark(bmkSid, userSid, con);

            con.commit();

        } catch (SQLException e) {
            log__.warn("ブックマーク削除に失敗", e);
            JDBCUtil.rollback(con);
            throw e;
        }
    }

    /**
     * <br>[機  能] URLマスタからタイトル取得
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param strUrl URL
     * @return タイトル
     * @throws Exception 実行例外
     * @throws SQLException SQL実行例外
     */
    private String __getTitleFromDb(Connection con, String strUrl)
    throws Exception, SQLException {
        log__.debug("URLマスタからタイトル取得");

        BmkUrlDao uDao = new BmkUrlDao(con);
        BmkUrlModel uModel = uDao.select(strUrl);
        if (uModel != null) {
            return uModel.getBmuTitle();
        }
        return "";
    }

     /**
     * <br>[機  能] エスケープ文字を通常の文字に変換、前後のブランクを除去します。
     * <br>[解  説]
     * <br>[備  考]
     * @param title 変換元の文字列
     * @return 変換済みの文字列
     */
    private String __transToDsp(String title) {
        if (title == null) {
                return null;
        }

        //エスケープ文字の変換
        title = StringUtilHtml.transToText(title);
        //前後のブランクを除去
        title = title.replaceAll("^[\\s　]*", "").replaceAll("[\\s　]*$", "");

        return title;
    }
}