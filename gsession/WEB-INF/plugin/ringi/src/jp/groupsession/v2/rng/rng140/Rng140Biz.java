package jp.groupsession.v2.rng.rng140;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.rng.dao.RngTemplateCategoryDao;
import jp.groupsession.v2.rng.dao.RngTemplateDao;
import jp.groupsession.v2.rng.model.RngTemplateCategoryModel;
import jp.groupsession.v2.rng.model.RngTemplateModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] 稟議 テンプレートカテゴリ登録画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng140Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rng140Biz.class);

    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <p>コンストラクタ
     * @param reqMdl リクエスト情報
     */
    public Rng140Biz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] テンポラリディレクトリのファイル削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param tempDir テンポラリディレクトリパス
     * @throws IOToolsException ファイルアクセス時例外
     */
    public void doDeleteFile(String tempDir) throws IOToolsException {
        //テンポラリディレクトリのファイルを削除する
        IOTools.deleteDir(tempDir);
        log__.debug("テンポラリディレクトリのファイル削除 : " + tempDir);
    }

    /**
     * <br>[機  能] 初期表示処理を行います
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param sessionUserSid セッションユーザSID
     * @param cmd コマンド
     * @throws Exception 例外
     */
    public void init(Rng140ParamModel paramMdl,
                     Connection con,
                     int sessionUserSid,
                     String cmd)
                     throws Exception {

        //編集の場合、データ取得
        if (paramMdl.getRng140ProcMode() == RngConst.RNG_CMDMODE_EDIT
        && cmd.equals("addeditcategory")) {
            RngTemplateCategoryDao dao = new RngTemplateCategoryDao(con);
            RngTemplateCategoryModel model = dao.select(paramMdl.getRng140CatSid());
            paramMdl.setRng140CatName(model.getRtcName());
        }
    }

    /**
     * <br>[機  能] カテゴリSIDからテンプレート情報一覧を取得し、一覧のlistを返す
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param sessionUserSid セッションユーザSID
     * @return ArrayList テンプレートリスト
     * @throws Exception 例外
     */
    public ArrayList<String> getDeleteTmpList(Rng140ParamModel paramMdl,
                                 Connection con,
                                 int sessionUserSid)
                                 throws Exception {

        //テンプレート一覧取得
        RngTemplateDao dao = new RngTemplateDao(con);
        int kbn = paramMdl.getRngTemplateMode();
        int catSid = 0;
        if (kbn == RngConst.RNG_TEMPLATE_SHARE) {
            catSid = paramMdl.getRng140CatSid();
        } else if (kbn == RngConst.RNG_TEMPLATE_PRIVATE) {
            catSid = paramMdl.getRng140CatSid();
        }
        ArrayList<RngTemplateModel> list = dao.selectTplList(kbn, sessionUserSid, catSid, reqMdl__);

        ArrayList<String> tmpList = new ArrayList<String>();
        for (RngTemplateModel model : list) {
            tmpList.add(model.getRtpTitle());
        }
        return tmpList;
    }

    /**
     * <br>[機  能] カテゴリSIDからカテゴリ名を取得し、テンプレート一覧を追加したメッセージを返す
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param catSid ラベルSID
     * @param msgRes MessageResources
     * @param delMsg html表示用ラベル一覧
     * @return String 削除確認メッセージ
     * @throws SQLException SQL実行例外
     */
    public String getDeleteTmpAndCatMsg(Connection con,
                                        int catSid,
                                        MessageResources msgRes,
                                        String delMsg)
                                        throws SQLException {

        String msg = "";

        //カテゴリ名取得
        RngTemplateCategoryDao catDao = new RngTemplateCategoryDao(con);
        RngTemplateCategoryModel catMdl = catDao.select(catSid);
        String catName = NullDefault.getString(catMdl.getRtcName(), "");

        msg = msgRes.getMessage("error.ringitmp.category", catName, delMsg);

        return msg;
    }

    /**
     * <br>[機  能] カテゴリSIDからカテゴリを取得し、メッセージを返す
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param catSid ラベルSID
     * @param msgRes MessageResources
     * @return String 削除確認メッセージ
     * @throws SQLException SQL実行例外
     */
    public String getDeletePosMsg(Connection con, int catSid, MessageResources msgRes)
    throws SQLException {

        String msg = "";

        GsMessage gsMsg = new GsMessage(reqMdl__);
        String category = gsMsg.getMessage("cmn.category");

        //カテゴリ名取得
        RngTemplateCategoryDao catDao = new RngTemplateCategoryDao(con);
        RngTemplateCategoryModel catMdl = catDao.select(catSid);
        String catName = NullDefault.getString(catMdl.getRtcName(), "");

        msg = msgRes.getMessage("sakujo.kakunin.list", category,
                StringUtilHtml.transToHTmlPlusAmparsant(catName));

        return msg;
    }

    /**
     * <br>[機  能] 稟議テンプレートカテゴリの削除を行います
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param rtcSid 稟議テンプレートカテゴリSID
     * @param usrSid ユーザSID
     * @param con Connection
     * @throws Exception 例外
     */
    public void deleteTplCat(Rng140ParamModel paramMdl,
                             int rtcSid, int usrSid, Connection con) throws Exception {

        RngTemplateCategoryDao rtcDao = new RngTemplateCategoryDao(con);
        UDate now = new UDate();

        int sort = rtcDao.getSort(rtcSid);

        //ソート順の更新を行う
        rtcDao.updateSortAll(paramMdl.getRngTemplateMode(), usrSid, now, sort);
        //カテゴリの削除
        rtcDao.delete(rtcSid);
    }

}
