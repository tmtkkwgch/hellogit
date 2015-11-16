package jp.groupsession.v2.bmk.bmk090kn;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.bmk.GSConstBookmark;
import jp.groupsession.v2.bmk.biz.BookmarkBiz;
import jp.groupsession.v2.bmk.dao.BmkGconfDao;
import jp.groupsession.v2.bmk.dao.BmkGroupEditDao;
import jp.groupsession.v2.bmk.model.BmkGconfModel;
import jp.groupsession.v2.bmk.model.BmkGroupEditModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnGroupmModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 権限設定(グループブックマーク)確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bmk090knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bmk090knBiz.class);
    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     */
    public Bmk090knBiz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 権限設定をDBに保存する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Bmk090knParamModel
     * @param usrSid ユーザSID
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setBmkGconf(Bmk090knParamModel paramMdl,
            int usrSid, Connection con) throws SQLException {

      boolean commitFlg = false;

      try {
          //DBの存在確認
          BmkGconfDao gConDao = new BmkGconfDao(con);
          BmkGconfModel gConModel = gConDao.selectGConf(paramMdl.getBmk010groupSid());
          //画面値セット
          BmkGconfModel gConMdl = createBmkGconfData(paramMdl, usrSid);

          if (gConModel == null) {
              gConDao.insert(gConMdl);
          } else {
              gConDao.update(gConMdl);
          }
          BmkGroupEditDao grpDao = new BmkGroupEditDao(con);
          //フィールド削除
          grpDao.delete(paramMdl.getBmk010groupSid());

          if (paramMdl.getBmk090GrpEditKbn() == GSConstBookmark.EDIT_POW_GROUP) {

              //グループブックマーク編集権限：グループ指定
              BmkGroupEditModel grpMdl = createBmkGroupEditData(paramMdl, usrSid);

              if (paramMdl.getBmk090GrpSid() != null) {

                  for (int i = 0; i < paramMdl.getBmk090GrpSid().length; i++) {
                      grpMdl.setBgeGrpSid(Integer.parseInt(paramMdl.getBmk090GrpSid()[i]));
                      grpDao.insert(grpMdl);
                  }
              }
          } else if (paramMdl.getBmk090GrpEditKbn() == GSConstBookmark.EDIT_POW_USER) {

              //グループブックマーク編集権限：ユーザ指定
              BmkGroupEditModel grpMdl = createBmkGroupEditData(paramMdl, usrSid);

              if (paramMdl.getBmk090UserSid() != null) {

                  for (int i = 0; i < paramMdl.getBmk090UserSid().length; i++) {
                      grpMdl.setBgeUsrSid(Integer.parseInt(paramMdl.getBmk090UserSid()[i]));
                      grpDao.insert(grpMdl);
                  }
              }
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
    }

    /**
     * <br>[機  能] 管理者設定情報を作成
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Bmk090knParamModel
     * @param usrSid ユーザSID
     * @return BmkGconfModel
     */
    public BmkGconfModel createBmkGconfData(
            Bmk090knParamModel paramMdl, int usrSid) {

        UDate nowDate = new UDate();
        BmkGconfModel mdl = new BmkGconfModel();

        mdl.setGrpSid(paramMdl.getBmk010groupSid());
        mdl.setBgcEdit(paramMdl.getBmk090GrpEditKbn());
        mdl.setBgcAuid(usrSid);
        mdl.setBgcAdate(nowDate);
        mdl.setBgcEuid(usrSid);
        mdl.setBgcEdate(nowDate);

        return mdl;
    }
    /**
     * <br>[機  能] グループブックマーク編集権限情報を作成
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Bmk090knParamModel
     * @param usrSid ユーザSID
     * @return BmkGroupEditModel
     */
    public BmkGroupEditModel createBmkGroupEditData(
            Bmk090knParamModel paramMdl, int usrSid) {

        UDate nowDate = new UDate();
        BmkGroupEditModel mdl = new BmkGroupEditModel();

        mdl.setGrpSid(paramMdl.getBmk010groupSid());
        mdl.setBgeGrpSid(-1);
        mdl.setBgeUsrSid(-1);
        mdl.setBgeAuid(usrSid);
        mdl.setBgeAdate(nowDate);
        mdl.setBgeEuid(usrSid);
        mdl.setBgeEdate(nowDate);

        return mdl;
    }

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Bmk090knParamModel
     * @param sessionUserSid セッションユーザSID
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setInitData(
            Bmk090knParamModel paramMdl,
            int sessionUserSid,
            Connection con) throws SQLException {
        //グループ名の設定
        GroupDao grpDao = new GroupDao(con);
        CmnGroupmModel grpMdl = grpDao.getGroup(paramMdl.getBmk010groupSid());
        paramMdl.setBmk090GrpName(
                StringUtilHtml.transToHTmlPlusAmparsant(grpMdl.getGrpName()));

        if (paramMdl.getBmk090GrpEditKbn() == GSConstBookmark.EDIT_POW_USER) {
            //共有ブックマーク編集権限：ユーザ指定
            BookmarkBiz bookmarkBiz = new BookmarkBiz(con, reqMdl__);
            paramMdl.setBmk090knUserList(
                    bookmarkBiz.getUserNameList(con, paramMdl.getBmk090UserSid()));

        } else if (paramMdl.getBmk090GrpEditKbn() == GSConstBookmark.EDIT_POW_GROUP) {
            //共有ブックマーク編集権限：グループ指定
            BookmarkBiz bookmarkBiz = new BookmarkBiz(con, reqMdl__);
            paramMdl.setBmk090knGroupList(
                    bookmarkBiz.getGroupNameList(con, paramMdl.getBmk090GrpSid()));
        }
    }
}
