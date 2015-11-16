package jp.groupsession.v2.enq.enq320;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.PageUtil;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.io.IOTools;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.enq.GSConstEnquete;
import jp.groupsession.v2.enq.biz.EnqCommonBiz;
import jp.groupsession.v2.enq.dao.EnqMainDao;
import jp.groupsession.v2.enq.enq310.Enq310Biz;
import jp.groupsession.v2.enq.model.EnqMainModel;
import jp.groupsession.v2.enq.pdf.EnqListPdfUtil;
import jp.groupsession.v2.enq.pdf.EnqPdfModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] アンケート 結果確認（一覧）画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Enq320Biz {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Enq320Biz.class);

    /**
     * <br>[機  能] 初期表示情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータモデル
     * @param reqMdl リクエストモデル
     * @param con コネクション
     * @throws SQLException SQL実行例外
     */
    public void setInitData(Enq320ParamModel paramMdl,
                            RequestModel reqMdl,
                            Connection con)
    throws SQLException {

        //アンケート情報を設定する
        Enq310Biz biz310 = new Enq310Biz();
        biz310.setEnqueteData(reqMdl, con, paramMdl);

        //グループコンボ、ユーザコンボを設定
        paramMdl.setGroupCombo(getGroupCombo(con, reqMdl));
        paramMdl.setUserCombo(getUserCombo(con, reqMdl, paramMdl.getEnq320group()));

        //回答情報
        long emnSid = paramMdl.getAnsEnqSid();
        Enq320SearchModel searchMdl = new Enq320SearchModel();
        searchMdl.setEmnSid(emnSid);
        if (paramMdl.getEnq310anony() == GSConstEnquete.EMN_ANONNY_NON) {
            searchMdl.setGroup(paramMdl.getEnq320svGroup());
            searchMdl.setUser(paramMdl.getEnq320svUser());
        }
        searchMdl.setStsAns(paramMdl.getEnq320svStsAns());
        searchMdl.setStsNon(paramMdl.getEnq320svStsNon());
        searchMdl.setOrder(paramMdl.getEnq320order());
        if (paramMdl.getEnq310anony() == GSConstEnquete.ANONYMUS_ON) {
            searchMdl.setSortKey(Enq320Const.SORTKEY_ANSDATE);
        } else {
            searchMdl.setSortKey(paramMdl.getEnq320sortKey());
        }

        // 対象者情報件数を取得
        Enq320Dao dao320 = new Enq320Dao(con);
        int searchCnt = dao320.getAnswerCount(searchMdl, reqMdl);

        //ページ調整
        EnqCommonBiz enqBiz = new EnqCommonBiz();
        int pageMaxCnt = enqBiz.getMaxListCnt(con, reqMdl.getSmodel().getUsrsid());

        searchMdl.setMaxCount(pageMaxCnt);
        int maxPage = searchCnt / pageMaxCnt;
        if ((searchCnt % pageMaxCnt) > 0) {
            maxPage++;
        }
        int page = paramMdl.getEnq320pageTop();
        if (page < 1) {
            page = 1;
        } else if (page > maxPage) {
            page = maxPage;
        }
        paramMdl.setEnq320pageTop(page);
        paramMdl.setEnq320pageBottom(page);

        //ページコンボ設定
        if (maxPage > 1) {
            paramMdl.setPageList(PageUtil.createPageOptions(searchCnt, pageMaxCnt));
        }
        searchMdl.setPage(paramMdl.getEnq320pageTop());

        List<Enq320AnswerModel> ansList = dao320.getAnswerList(searchMdl, reqMdl);
        paramMdl.setAnsList(ansList);
    }

    /**
     * <br>[機  能] グループコンボを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @return グループコンボ
     * @throws SQLException SQL実行時例外
     */
    public List<LabelValueBean> getGroupCombo(Connection con, RequestModel reqMdl)
    throws SQLException {
        GsMessage gsMsg = new GsMessage(reqMdl);
        GroupBiz grpBiz = new GroupBiz();
        List<LabelValueBean> grpCombo
            = grpBiz.getGroupCombLabelList(con, false, gsMsg);
        grpCombo.add(0, new LabelValueBean(gsMsg.getMessage("cmn.all"), "-1"));
        return grpCombo;
    }

    /**
     * <br>[機  能] ユーザコンボを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @param grpSid グループSID
     * @return ユーザコンボ
     * @throws SQLException SQL実行時例外
     */
    public List<LabelValueBean> getUserCombo(Connection con, RequestModel reqMdl, int grpSid)
    throws SQLException {
        GsMessage gsMsg = new GsMessage(reqMdl);
        UserBiz userBiz = new UserBiz();
        List<LabelValueBean> userCombo
            = userBiz.getNormalUserLabelList(con, grpSid, null, false, gsMsg);
        userCombo.add(0, new LabelValueBean(gsMsg.getMessage("cmn.all"), "-1"));
        return userCombo;
    }

    /**
     * <br>[機  能] アンケート結果確認画面をPDF出力します。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータモデル
     * @param con コネクション
     * @param userSid セッションユーザSID
     * @param appRootPath アプリケーションルートパス
     * @param outTempDir テンポラリディレクトパス
     * @param pconfig プラグイン情報
     * @param reqMdl リクエスト情報
     * @return アンケート結果確認PDFモデル
     * @throws SQLException SQL実行時例外
     * @throws IOException 入出力時例外
     */
    public EnqPdfModel createEnqListPdf(
            Enq320ParamModel paramMdl,
            Connection con,
            int userSid,
            String appRootPath,
            String outTempDir,
            PluginConfig pconfig,
            RequestModel reqMdl) throws SQLException, IOException {

        //アンケート結果確認PDF用モデルに値をセット
        EnqPdfModel pdfModel = new EnqPdfModel();
        pdfModel = __getEnqPdfDataList(paramMdl, con, reqMdl);

        OutputStream oStream = null;
        GsMessage gsMsg = new GsMessage(reqMdl);

        //ファイル名
        String fileName = gsMsg.getMessage("enq.plugin") + gsMsg.getMessage("enq.50")
                + "_" + NullDefault.getString(pdfModel.getEnq310enqTitle(), "");

        String encFileName = __fileNameCheck(fileName) + ".pdf";
        pdfModel.setFileName(encFileName);

        //セーブ用ファイル名
        String saveFileName = "enq" + pdfModel.getEnqPdfSid() + ".pdf";
        pdfModel.setSvFileName(saveFileName);

        //PDF生成
        try {
            IOTools.isDirCheck(outTempDir, true);
            oStream = new FileOutputStream(outTempDir + saveFileName);

            EnqListPdfUtil pdfUtil = new EnqListPdfUtil();
            pdfUtil.createEnqListPdf(pdfModel, appRootPath, oStream);

        } catch (Exception e) {
            log__.error("アンケート結果確認PDF出力に失敗しました。", e);
        } finally {
            if (oStream != null) {
                oStream.flush();
                oStream.close();
            }
        }

        return pdfModel;
    }

    /**
    *
    * <br>[機  能] アンケート結果のPDFモデルを取得します。
    * <br>[解  説]
    * <br>[備  考]
    * @param paramMdl パラメータモデル
    * @param con コネクション
    * @param reqMdl request情報
    * @return pdfModel
    * @throws SQLException SQL実行時例外
    */
   public EnqPdfModel __getEnqPdfDataList(
           Enq320ParamModel paramMdl, Connection con, RequestModel reqMdl) throws SQLException {

       EnqPdfModel pdfMdl = new EnqPdfModel();
       long emnSid = paramMdl.getAnsEnqSid();

       /**  アンケート基本情報 */
       EnqMainDao enqMainDao = new EnqMainDao(con);
       EnqMainModel enqMainMdl = enqMainDao.select(emnSid);
       Enq310Biz biz310 = new Enq310Biz();
       biz310.getEnqPdfData(con, reqMdl, pdfMdl, emnSid, enqMainMdl);

       /** アンケート検索情報 */
       //グループコンボ、ユーザコンボ
       pdfMdl.setGroupCombo(getGroupCombo(con, reqMdl));
       pdfMdl.setUserCombo(getUserCombo(con, reqMdl, paramMdl.getEnq320group()));

       Enq320SearchModel searchMdl = new Enq320SearchModel();
       searchMdl.setEmnSid(emnSid);
       //検索対象 グループ
       searchMdl.setGroup(paramMdl.getEnq320svGroup());
       //検索対象 ユーザ
       searchMdl.setUser(paramMdl.getEnq320svUser());
       //検索状態 回答
       searchMdl.setStsAns(paramMdl.getEnq320svStsAns());
       //検索状態 未回答
       searchMdl.setStsNon(paramMdl.getEnq320svStsNon());

       //状態
       GsMessage gsMsg = new GsMessage(reqMdl);
       if (paramMdl.getEnq320svStsAns() == 1 && paramMdl.getEnq320svStsNon() != 1) {
           pdfMdl.setStsAnswer(gsMsg.getMessage("enq.22"));

       } else if (paramMdl.getEnq320svStsAns() != 1 && paramMdl.getEnq320svStsNon() == 1) {
           pdfMdl.setStsAnswer(gsMsg.getMessage("enq.21"));

       } else if (paramMdl.getEnq320svStsAns() == 1 && paramMdl.getEnq320svStsNon() == 1) {
           pdfMdl.setStsAnswer(gsMsg.getMessage("enq.22") + "・" + gsMsg.getMessage("enq.21"));

       } else if (paramMdl.getEnq320svStsAns() != 1 && paramMdl.getEnq320svStsNon() != 1)  {
           pdfMdl.setStsAnswer("未指定");
       }

       /** アンケート回答情報 */
       Enq320Dao dao320 = new Enq320Dao(con);
       List<Enq320AnswerModel> ansList = dao320.getAnswerList(searchMdl, reqMdl);
       pdfMdl.setAnsList(ansList);

       return pdfMdl;
   }

   /**
    * <br>[機  能] ファイル名として使用できるか文字列チェックする。
    * <br>[解  説] /\?*:|"<>. を除去
    *                    255文字超える場合は以降を除去
    * <br>[備  考]
    * @param fileName テンポラリディレクトリ
    * @return 出力したファイルのパス
    */
   private String __fileNameCheck(String fileName) {
       String escName = fileName;

       escName = StringUtilHtml.replaceString(escName, "/", "");
       escName = StringUtilHtml.replaceString(escName, "\\", ""); //\
       escName = StringUtilHtml.replaceString(escName, "?", "");
       escName = StringUtilHtml.replaceString(escName, "*", "");
       escName = StringUtilHtml.replaceString(escName, ":", "");
       escName = StringUtilHtml.replaceString(escName, "|", "");
       escName = StringUtilHtml.replaceString(escName, "\"", ""); //"
       escName = StringUtilHtml.replaceString(escName, "<", "");
       escName = StringUtilHtml.replaceString(escName, ">", "");
       escName = StringUtilHtml.replaceString(escName, ".", "");
       escName = StringUtil.trimRengeString(escName, 251); //ファイル名＋拡張子=255文字以内

       return escName;
   }
}
