package jp.groupsession.v2.sml.sml020kn;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.base.CmnBinfDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmDao;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.TempFileModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.cmn.model.base.SmlMailFileModel;
import jp.groupsession.v2.sml.GSConstSmail;
import jp.groupsession.v2.sml.biz.SmlCommonBiz;
import jp.groupsession.v2.sml.dao.SmailDao;
import jp.groupsession.v2.sml.dao.SmlAccountDao;
import jp.groupsession.v2.sml.dao.SmlAsakDao;
import jp.groupsession.v2.sml.dao.SmlBinDao;
import jp.groupsession.v2.sml.dao.SmlHinaDao;
import jp.groupsession.v2.sml.dao.SmlJmeisDao;
import jp.groupsession.v2.sml.dao.SmlSmeisDao;
import jp.groupsession.v2.sml.dao.SmlUserSearchDao;
import jp.groupsession.v2.sml.dao.SmlWmeisDao;
import jp.groupsession.v2.sml.model.AtesakiModel;
import jp.groupsession.v2.sml.model.SmailDetailModel;
import jp.groupsession.v2.sml.model.SmailModel;
import jp.groupsession.v2.sml.model.SmailSendModel;
import jp.groupsession.v2.sml.model.SmlAccountModel;
import jp.groupsession.v2.sml.model.SmlAdminModel;
import jp.groupsession.v2.sml.model.SmlAsakModel;
import jp.groupsession.v2.sml.model.SmlBinModel;
import jp.groupsession.v2.sml.model.SmlHinaModel;
import jp.groupsession.v2.sml.model.SmlJmeisModel;
import jp.groupsession.v2.sml.model.SmlSmeisModel;
import jp.groupsession.v2.sml.model.SmlWmeisModel;
import jp.groupsession.v2.sml.sml010.Sml010Biz;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;


/**
 * <br>[機  能] ショートメール作成確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sml020knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sml020knBiz.class);

    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     *
     *@param reqMdl リクエスト情報
     */
    public Sml020knBiz(RequestModel reqMdl) {
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
        log__.debug("テンポラリディレクトリのファイル削除");
    }

    /**
     * <br>[機  能] セッションユーザSIDを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param reqMdl リクエスト情報
     * @return sessionUsrSid セッションユーザSID
     */
    private int __getSessionUserSid(RequestModel reqMdl) {

        log__.debug("セッションユーザSID取得");

        int sessionUsrSid = -1;

        //セッション情報を取得
        BaseUserModel usModel = reqMdl.getSmodel();
        if (usModel != null) {
            sessionUsrSid = usModel.getUsrsid();
        }

        return sessionUsrSid;
    }

    /**
     * <br>[機  能] 宛先名称一覧を設定
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void setAtesaki(Sml020knParamModel paramMdl,
                        Connection con)
        throws SQLException {

        log__.debug("宛先名称設定");

        String[] userSid = paramMdl.getSml020userSid();
        if (userSid == null || userSid.length < 1) {
            return;
        }

        ArrayList<AtesakiModel> ret = new ArrayList<AtesakiModel>();

        List<String> newUserSid = new ArrayList<String>();
        List<String> accountUserSid = new ArrayList<String>();

        for (String usid : userSid) {
            if (usid.indexOf(GSConstSmail.SML_ACCOUNT_STR) != -1) {
                //作成アカウント
                accountUserSid.add(usid.substring(GSConstSmail.SML_ACCOUNT_STR.length()));
            } else {
                //GSユーザ
                newUserSid.add(usid);
            }
        }

        if (!newUserSid.isEmpty()) {
            SmlUserSearchDao udao = new SmlUserSearchDao(con);
            ret.addAll(udao.getUserDataFromSidList(
                    (String[]) newUserSid.toArray(new String[newUserSid.size()])));
        }

        if (!accountUserSid.isEmpty()) {
            SmlUserSearchDao udao = new SmlUserSearchDao(con);
            ret.addAll(udao.getAccountDataFromSidList(
                    (String[]) accountUserSid.toArray(new String[accountUserSid.size()])));
        }


        SmailModel sMdl = new SmailModel();
        sMdl.setAtesakiList(ret);
        sMdl.setListSize(ret.size() - 1);

        paramMdl.setSml020Atesaki(sMdl);
    }

    /**
     * <br>[機  能] CC名称一覧を設定
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void setAtesakiCc(Sml020knParamModel paramMdl, Connection con)
        throws SQLException {

        log__.debug("CC名称設定");

        String[] userSid = paramMdl.getSml020userSidCc();

        if (userSid == null || userSid.length < 1) {
            return;
        }
        ArrayList<AtesakiModel> ret = new ArrayList<AtesakiModel>();

        List<String> newUserSid = new ArrayList<String>();
        List<String> accountUserSid = new ArrayList<String>();

        for (String usid : userSid) {
            if (usid.indexOf(GSConstSmail.SML_ACCOUNT_STR) != -1) {
                //作成アカウント
                accountUserSid.add(usid.substring(GSConstSmail.SML_ACCOUNT_STR.length()));
            } else {
                //GSユーザ
                newUserSid.add(usid);
            }
        }

        if (!newUserSid.isEmpty()) {
            SmlUserSearchDao udao = new SmlUserSearchDao(con);
            ret.addAll(udao.getUserDataFromSidList(
                    (String[]) newUserSid.toArray(new String[newUserSid.size()])));
        }

        if (!accountUserSid.isEmpty()) {
            SmlUserSearchDao udao = new SmlUserSearchDao(con);
            ret.addAll(udao.getAccountDataFromSidList(
                    (String[]) accountUserSid.toArray(new String[accountUserSid.size()])));
        }

        SmailModel sMdl = new SmailModel();
        sMdl.setAtesakiList(ret);
        sMdl.setListSize(ret.size() - 1);

        paramMdl.setSml020AtesakiCc(sMdl);
    }

    /**
     * <br>[機  能] BCC名称一覧を設定
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void setAtesakiBcc(Sml020knParamModel paramMdl, Connection con)
        throws SQLException {

        log__.debug("BCC名称設定");

        String[] userSid = paramMdl.getSml020userSidBcc();

        if (userSid == null || userSid.length < 1) {
            return;
        }
        ArrayList<AtesakiModel> ret = new ArrayList<AtesakiModel>();

        List<String> newUserSid = new ArrayList<String>();
        List<String> accountUserSid = new ArrayList<String>();

        for (String usid : userSid) {
            if (usid.indexOf(GSConstSmail.SML_ACCOUNT_STR) != -1) {
                //作成アカウント
                accountUserSid.add(usid.substring(GSConstSmail.SML_ACCOUNT_STR.length()));
            } else {
                //GSユーザ
                newUserSid.add(usid);
            }
        }

        if (!newUserSid.isEmpty()) {
            SmlUserSearchDao udao = new SmlUserSearchDao(con);
            ret.addAll(udao.getUserDataFromSidList(
                    (String[]) newUserSid.toArray(new String[newUserSid.size()])));
        }

        if (!accountUserSid.isEmpty()) {
            SmlUserSearchDao udao = new SmlUserSearchDao(con);
            ret.addAll(udao.getAccountDataFromSidList(
                    (String[]) accountUserSid.toArray(new String[accountUserSid.size()])));
        }

        SmailModel sMdl = new SmailModel();
        sMdl.setAtesakiList(ret);
        sMdl.setListSize(ret.size() - 1);

        paramMdl.setSml020AtesakiBcc(sMdl);
    }

    /**
     * <br>[機  能] ひな形SIDからひな形データを取得し設定
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void setHinagataData(Sml020knParamModel paramMdl,
                                 Connection con)
        throws SQLException {

        log__.debug("ひな形データ設定");

        SmlHinaModel param = new SmlHinaModel();
        param.setShnSid(paramMdl.getSml020SelectHinaId());

        SmlHinaDao hdao = new SmlHinaDao(con);
        SmlHinaModel ret = hdao.select(param);

        if (ret != null) {
            //件名
            paramMdl.setSml020Title(NullDefault.getString(ret.getShnTitle(), ""));
            //マーク
            paramMdl.setSml020Mark(ret.getShnMark());
            //本文
            paramMdl.setSml020Body(NullDefault.getString(ret.getShnBody(), ""));
        }
    }

    /**
     * <br>[機  能] ひな形リストを作成する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void setHinagataList(Sml020knParamModel paramMdl,
                                 RequestModel reqMdl,
                                 Connection con)
        throws SQLException {

        log__.debug("ひな形リスト設定");

        SmlHinaDao hdao = new SmlHinaDao(con);
        List<SmlHinaModel> ret = hdao.select(paramMdl.getSmlViewAccount());
        paramMdl.setSml020HinaList(ret);
    }

    /**
     * <br>[機  能] 添付ファイル情報をセット
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param tempDir テンポラリディレクトリ
     * @param con コネクション
     * @throws IOToolsException
     * @throws IOToolsException ファイルアクセス時例外
     */
    @SuppressWarnings("unchecked")
    public void setTempFiles(Sml020knParamModel paramMdl, String tempDir, Connection con)
        throws IOToolsException {

        /** 画面に表示するファイルのリストを作成、セット **********************/
        CommonBiz commonBiz = new CommonBiz();
        List<LabelValueBean> sortList = commonBiz.getTempFileLabelList(tempDir);
        Collections.sort(sortList);
        paramMdl.setSml020FileLabelList(sortList);

    }
//
//    /**
//     * <br>[機  能] メールSIDから引用するメールデータを設定する
//     * <br>[解  説]
//     * <br>[備  考] 返信、全返信、転送モードの処理
//     *
//     * @param paramMdl パラメータ情報
//     * @param reqMdl リクエスト情報
//     * @param con コネクション
//     * @param appRootPath アプリケーションルート
//     * @param tempDir テンポラリディレクトリパス
//     * @param domain ドメイン
//     * @throws SQLException SQL実行時例外
//     * @throws IOToolsException ファイルアクセス時例外
//     * @throws IOException 入出力時例外
//     * @throws TempFileException 添付ファイルUtil内での例外
//     */
//    public void setMailData(Sml020knParamModel paramMdl,
//                             RequestModel reqMdl,
//                             Connection con,
//                             String appRootPath,
//                             String tempDir,
//                             String domain)
//        throws SQLException, IOToolsException, IOException, TempFileException {
//
//        log__.error("引用するメールデータ設定(返信、全返信、転送モード)");
//
//        SmailDao sdao = new SmailDao(con);
//        ArrayList<SmailDetailModel> ret =
//            sdao.selectSmeisDetailFromSid(
//                    __getSessionUserSid(reqMdl),
//                    paramMdl.getSml010SelectedSid());
//
//        if (!ret.isEmpty()) {
//            SmailDetailModel sMdl = ret.get(0);
//
//            //件名
//            paramMdl.setSml020Title(
//                    __convertTitle(
//                            paramMdl.getSml020ProcMode(),
//                            NullDefault.getString(sMdl.getSmsTitle(), "")));
//            //マーク
//            paramMdl.setSml020Mark(sMdl.getSmsMark());
//            //本文
//            paramMdl.setSml020Body(
//                    __convertBody(
//                            paramMdl.getSml020ProcMode(), ret));
//
//            if (paramMdl.getSml020ProcMode().equals(GSConstSmail.MSG_CREATE_MODE_TENSO)) {
//
//                //添付ファイル情報
//                SmlBinDao sbinDao = new SmlBinDao(con);
//                List<SmlBinModel> binList = sbinDao.getBinList(sMdl.getSmlSid());
//
//                //添付ファイルがあるなるならばテンポラリにコピー
//                if (!binList.isEmpty()) {
//                    __tempFileCopy(binList, appRootPath, tempDir, con, domain);
//                }
//            }
//        }
//    }

    /**
     * <br>[機  能] メールSIDから引用するメールデータを設定する
     * <br>[解  説]
     * <br>[備  考] 下書きから作成モードの処理
     *
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @param appRootPath アプリケーションルート
     * @param tempDir テンポラリディレクトリ
     * @param domain ドメイン
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException ファイルアクセス時例外
     * @throws IOException 入出力時例外
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public void setMailDataFromSitagaki(Sml020knParamModel paramMdl,
                                         RequestModel reqMdl,
                                         Connection con,
                                         String appRootPath,
                                         String tempDir,
                                         String domain)
        throws SQLException, IOToolsException, IOException, TempFileException {

        log__.error("引用するメールデータ設定(草稿から作成モード)");

        SmailDao sdao = new SmailDao(con);
        ArrayList<SmailDetailModel> ret =
            sdao.selectWmeisDetail(
                    __getSessionUserSid(reqMdl),
                    paramMdl.getSml010SelectedSid(),
                    GSConst.JTKBN_TOROKU);

        if (!ret.isEmpty()) {
            SmailDetailModel sMdl = ret.get(0);
            //件名
            paramMdl.setSml020Title(NullDefault.getString(sMdl.getSmsTitle(), ""));
            //マーク
            paramMdl.setSml020Mark(sMdl.getSmsMark());
            //本文
            paramMdl.setSml020Body(NullDefault.getString(sMdl.getSmsBody(), ""));
            //メール形式
            paramMdl.setSml020MailType((NullDefault.getInt(String.valueOf(sMdl.getSmsType()),
                    GSConstSmail.SAC_SEND_MAILTYPE_NORMAL)));

            //添付ファイル情報
            SmlBinDao sbinDao = new SmlBinDao(con);
            List<SmlBinModel> binList = sbinDao.getBinList(sMdl.getSmlSid());

            //添付ファイルがあるなるならばテンポラリにコピー
            if (!binList.isEmpty()) {
                __tempFileCopy(binList, appRootPath, tempDir, con, domain);
            }
        }
    }

    /**
     * <br>[機  能] 添付ファイルをテンポラリディレクトリにコピーする
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param binList 添付ファイルリスト
     * @param appRootPath アプリケーションルート
     * @param tempDir テンポラリディレクトリ
     * @param con コネクション
     * @param domain ドメイン
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException ファイルアクセス時例外
     * @throws IOException 入出力時例外
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    private void __tempFileCopy(List<SmlBinModel> binList,
                                 String appRootPath,
                                 String tempDir,
                                 Connection con,
                                 String domain)
        throws SQLException, IOToolsException, IOException, TempFileException {

        CommonBiz cmnBiz = new CommonBiz();
        UDate now = new UDate();
        String dateStr = now.getDateString();
        int i = 1;
        for (SmlBinModel retBinMdl : binList) {
            CmnBinfModel binMdl = cmnBiz.getBinInfo(con, retBinMdl.getBinSid(), domain);
            if (binMdl != null) {
                cmnBiz.saveTempFile(dateStr, binMdl, appRootPath, tempDir, i);
                i++;
            }
        }
    }
//
//    /**
//     * <br>[機  能] 処理モードに応じ、件名を変換する
//     * <br>[解  説]
//     * <br>[備  考]
//     *
//     * @param mode 処理モード
//     * @param title 件名
//     * @return cnvTitle 変換後タイトル
//     */
//    private String __convertTitle(String mode, String title) {
//
//        String cnvTitle = "";
//
//        GsMessage gsMsg = new GsMessage(reqMdl__);
//        String colon = gsMsg.getMessage("wml.215");
//
//        //返信モード
//        if (mode.equals(GSConstSmail.MSG_CREATE_MODE_HENSIN)) {
//            cnvTitle = "Re" + colon + title;
//        //全返信モード
//        } else if (mode.equals(GSConstSmail.MSG_CREATE_MODE_ZENHENSIN)) {
//            cnvTitle = "Re" + colon + title;
//        //転送モード
//        } else if (mode.equals(GSConstSmail.MSG_CREATE_MODE_TENSO)) {
//            cnvTitle = "Fw" + colon + title;
//        }
//
//        return cnvTitle;
//    }
//
//    /**
//     * <br>[機  能] 区分値に応じ、マークの文字列表現へ変換する
//     * <br>[解  説]
//     * <br>[備  考]
//     *
//     * @param mark マーク区分
//     * @return cnvMark 変換後マーク
//     */
//    private String __convertMark(int mark) {
//
//        String cnvMark = "";
//
//        GsMessage gsMsg = new GsMessage(reqMdl__);
//        String phone = gsMsg.getMessage("cmn.phone") + gsMsg.getMessage("wml.215");;
//        String important = gsMsg.getMessage("sml.61") + gsMsg.getMessage("wml.215");;
//
//        if (mark == GSConstSmail.MARK_KBN_NONE) {
//            //変換無し
//        } else if (mark == GSConstSmail.MARK_KBN_TEL) {
//            cnvMark = phone;
//        } else if (mark == GSConstSmail.MARK_KBN_INP) {
//            cnvMark = important;
//        }
//
//        return cnvMark;
//    }
//
//    /**
//     * <br>[機  能] 処理モードに応じ、本文を変換する
//     * <br>[解  説]
//     * <br>[備  考]
//     *
//     * @param mode 処理モード
//     * @param bean 検索結果
//     * @return cnvBody 変換後本文
//     */
//    private String __convertBody(String mode, ArrayList<SmailDetailModel> bean) {
//
//        String newLine = "\r\n";
//        int atesakiLimit = 4;
//
//        String cnvBody = "";
//        SmailDetailModel sMdl = bean.get(0);
//
//        GsMessage gsMsg = new GsMessage(reqMdl__);
//        String henshin = gsMsg.getMessage("sml.98");
//        String tensou = gsMsg.getMessage("sml.97");
//        String colon = gsMsg.getMessage("wml.215");
//        String sender = gsMsg.getMessage("cmn.sender") + colon;
//        String sendTo = gsMsg.getMessage("cmn.from") + colon;
//        String sendDate = gsMsg.getMessage("sml.154");
//        String subject = gsMsg.getMessage("cmn.subject") + colon;
//
//        /******************** 見出し設定 ********************/
//        //返信モード
//        if (mode.equals(GSConstSmail.MSG_CREATE_MODE_HENSIN)) {
//            cnvBody = henshin;
//        //全返信モード
//        } else if (mode.equals(GSConstSmail.MSG_CREATE_MODE_ZENHENSIN)) {
//            cnvBody = henshin;
//        //転送モード
//        } else if (mode.equals(GSConstSmail.MSG_CREATE_MODE_TENSO)) {
//            cnvBody = tensou;
//        }
//        cnvBody += newLine;
//
//        /******************** 送信者設定 ********************/
//        cnvBody += sender;
//        String sousinsya = sMdl.getUsiSei() + "　" + sMdl.getUsiMei();
//        cnvBody += sousinsya;
//        cnvBody += newLine;
//
//        /******************** 宛先設定 ********************/
//        cnvBody += "　" + sendTo;
//        ArrayList<AtesakiModel> atsk = sMdl.getAtesakiList();
//        int limCount = 0;
//        boolean newLineFlg = false;
//        for (int i = 0; i < atsk.size(); i++) {
//            AtesakiModel aMdl = (AtesakiModel) atsk.get(i);
//            String atesakiName = aMdl.getUsiSei() + "　" + aMdl.getUsiMei();
//
//            if (newLineFlg) {
//                cnvBody += "　　　　";
//                newLineFlg = false;
//            }
//            cnvBody += atesakiName;
//
//            if (i < atsk.size() - 1) {
//                cnvBody += "; ";
//            }
//
//            limCount += 1;
//            if (limCount == atesakiLimit
//                && i != atsk.size() - 1) {
//                limCount = 0;
//                newLineFlg = true;
//                cnvBody += newLine;
//            }
//        }
//        cnvBody += newLine;
//
//        /******************** 送信日設定 ********************/
//        cnvBody += sendDate;
//        UDate sDate = sMdl.getSmsSdate();
//        if (sDate != null) {
//            String strSdate =
//                UDateUtil.getSlashYYMD(sDate)
//                + " "
//                + UDateUtil.getSeparateHMS(sDate);
//            cnvBody += strSdate;
//        }
//        cnvBody += newLine;
//
//        /******************** 件名設定 ********************/
//        cnvBody += "　" + subject;
//        String markStr = __convertMark(sMdl.getSmsMark());
//        cnvBody += markStr;
//        cnvBody += sMdl.getSmsTitle();
//        cnvBody += newLine;
//        cnvBody += newLine;
//
//        /******************** 本文設定 ********************/
//        String[] splStr = sMdl.getSmsBody().split(newLine);
//        if (splStr != null && splStr.length > 0) {
//            for (int j = 0; j < splStr.length; j++) {
//                cnvBody += ">";
//                cnvBody += splStr[j];
//                cnvBody += newLine;
//            }
//        }
//
//        return cnvBody;
//    }

    /**
     * <br>[機  能] 作成されたメールデータを登録する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @param ctrl 採番用コネクション
     * @param appRootPath アプリケーションルート
     * @param tempDir テンポラリディレクトリ
     * @param pluginConfig PluginConfig
     * @throws ClassNotFoundException 指定されたリスナークラスが存在しない
     * @throws IllegalAccessException リスナー実装クラスのインスタンス生成に失敗
     * @throws InstantiationException リスナー実装クラスのインスタンス生成に失敗
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException ファイルアクセス時例外
     * @throws IOException 入出力時例外
     * @throws TempFileException 添付ファイルUtil内での例外
     * @return sendMdl 送信情報
     */
    public SmailSendModel insertMailData(
        Sml020knParamModel paramMdl,
        RequestModel reqMdl,
        Connection con,
        MlCountMtController ctrl,
        String appRootPath,
        String tempDir,
        PluginConfig pluginConfig)
        throws
        ClassNotFoundException,
        IllegalAccessException,
        InstantiationException,
        SQLException,
        IOToolsException,
        IOException,
        TempFileException {

        SmailSendModel sendMdl = new SmailSendModel();


        log__.debug("DBに登録");

        int usrSid = __getSessionUserSid(reqMdl);
        UDate now = new UDate();
        SmlCommonBiz smlCmnBiz = new SmlCommonBiz(reqMdl__);
        SmlAdminModel adminConf = smlCmnBiz.getSmailAdminConf(usrSid, con);

        //添付ファイルを登録
        CommonBiz biz = new CommonBiz();
        List<String> binList =
            biz.insertBinInfo(con, tempDir, appRootPath, ctrl, usrSid, now);

        //SID採番
        int mailSid =
            (int) ctrl.getSaibanNumber(
                    GSConstSmail.SAIBAN_SML_SID,
                    GSConstSmail.SAIBAN_SUB_MAIL_SID,
                    usrSid);

        //メールサイズ取得
        Long titile_byte = new Long(0);
        Long body_byte = new Long(0);
        Long file_byte = new Long(0);

        String bodyStr = "";
        String bodyPlainStr = "";

        if (paramMdl.getSml020MailType() == GSConstSmail.SAC_SEND_MAILTYPE_NORMAL) {
            bodyStr = paramMdl.getSml020Body();
        } else {
            bodyStr = paramMdl.getSml020BodyHtml();
            bodyPlainStr = StringUtilHtml.deleteHtmlTag(bodyStr);
        }

        try {
            if (paramMdl.getSml020Title().getBytes("UTF-8").length != 0) {
                titile_byte = Long.valueOf(
                        paramMdl.getSml020Title().getBytes("UTF-8").length);
            }
        } catch (UnsupportedEncodingException e) {
            log__.error("文字のバイト数取得に失敗");
            titile_byte = Long.valueOf(
                    paramMdl.getSml020Title().getBytes().length);
        }

        try {
            if (bodyStr.getBytes("UTF-8").length != 0) {
                body_byte = Long.valueOf(
                        bodyStr.getBytes("UTF-8").length);
            }
        } catch (UnsupportedEncodingException e) {
            log__.error("文字のバイト数取得に失敗");
            body_byte = Long.valueOf(
                    bodyStr.getBytes().length);
        }

        file_byte = biz.getTempFileSize(tempDir);


        //送信テーブルにデータ作成
        SmlSmeisModel sparam = new SmlSmeisModel();
        sparam.setSacSid(paramMdl.getSmlViewAccount());
        sparam.setSmsSid(mailSid);
        sparam.setSmsSdate(now);
        sparam.setSmsTitle(paramMdl.getSml020Title());
        sparam.setSmsMark(paramMdl.getSml020Mark());
        sparam.setSmsBody(bodyStr);
        sparam.setSmsBodyPlain(bodyPlainStr);
        sparam.setSmsType(paramMdl.getSml020MailType());
        sparam.setSmsSize(titile_byte + body_byte + file_byte);
        sparam.setSmsType(paramMdl.getSml020MailType());
        sparam.setSmsJkbn(GSConst.JTKBN_TOROKU);
        sparam.setSmsAuid(usrSid);
        sparam.setSmsAdate(now);
        sparam.setSmsEuid(usrSid);
        sparam.setSmsEdate(now);
        SmlSmeisDao sdao = new SmlSmeisDao(con);
        sdao.insert(sparam);

        SmlJmeisDao jdao = new SmlJmeisDao(con);

        /** 返信・全返信時には受信メールにわかるようにフィールドデータ変更  **/
        int editMailSid = paramMdl.getSml010EditSid();
        if (paramMdl.getSml020ProcMode().equals(GSConstSmail.MSG_CREATE_MODE_HENSIN)
            || paramMdl.getSml020ProcMode().equals(GSConstSmail.MSG_CREATE_MODE_ZENHENSIN)) {
            int kbn = Integer.parseInt(paramMdl.getSml020ProcMode());
//            int jMailSid = paramMdl.getSml010SelectedSid();
//            jdao.updateHenshin(kbn, paramMdl.getSmlViewAccount(), jMailSid);
            jdao.updateHenshin(kbn, paramMdl.getSmlViewAccount(), editMailSid);
        }

        /** 転送時には受信メールにわかるようにフィールドデータ変更  **/
        if (paramMdl.getSml020ProcMode().equals(GSConstSmail.MSG_CREATE_MODE_TENSO)) {
            int kbn = GSConstSmail.SML_FW;
//            int jMailSid = paramMdl.getSml010SelectedSid();
//            jdao.updateFw(kbn, paramMdl.getSmlViewAccount(), jMailSid);
            jdao.updateFw(kbn, paramMdl.getSmlViewAccount(), editMailSid);
        }

//        //ショートメールリスナー取得
//        ISMailListener[] lis = SMailListenerUtil.getSMailListeners(pluginConfig);

        //受信テーブルにデータ作成
        String[] accountSidAtesaki = smlCmnBiz.getAccountSidFromUsr(
                                                con, paramMdl.getSml020userSid());
        String[] accountSidCc = smlCmnBiz.getAccountSidFromUsr(
                                                con, paramMdl.getSml020userSidCc());
        String[] accountSidBcc = smlCmnBiz.getAccountSidFromUsr(
                                                con, paramMdl.getSml020userSidBcc());
        ArrayList<String[]> accountSidList = new ArrayList<String[]>();
        ArrayList<Integer> sendKbnList = new ArrayList<Integer>();
        accountSidList.add(accountSidAtesaki);
        sendKbnList.add(GSConstSmail.SML_SEND_KBN_ATESAKI);

        if (accountSidCc != null && accountSidCc.length > 0) {
            accountSidList.add(accountSidCc);
            sendKbnList.add(GSConstSmail.SML_SEND_KBN_CC);
        }
        if (accountSidBcc != null && accountSidBcc.length > 0) {
            accountSidList.add(accountSidBcc);
            sendKbnList.add(GSConstSmail.SML_SEND_KBN_BCC);
        }

        //受信メール登録前に送信メールの集計データを登録する
        String[] cntAccountSid = null;
        ArrayList<String> cntAllAccountSidList = new ArrayList<String>();
        int cntAtesaki = 0;
        int cntCc = 0;
        int cntBcc = 0;
        for (int n = 0; n < accountSidList.size(); n++) {
            cntAccountSid = accountSidList.get(n);
            for (int i = 0; i < cntAccountSid.length; i++) {
                if (cntAllAccountSidList.contains(cntAccountSid[i])) {
                    continue;
                }
                cntAllAccountSidList.add(cntAccountSid[i]);
                if (sendKbnList.get(n) == GSConstSmail.SML_SEND_KBN_ATESAKI) {
                    cntAtesaki++;
                } else if (sendKbnList.get(n) == GSConstSmail.SML_SEND_KBN_CC) {
                    cntCc++;
                } else if (sendKbnList.get(n) == GSConstSmail.SML_SEND_KBN_BCC) {
                    cntBcc++;
                }
            }
        }

        SmlCommonBiz smlBiz = new SmlCommonBiz();
        smlBiz.regSmeisLogCnt(con, paramMdl.getSmlViewAccount(), cntAtesaki, cntCc, cntBcc, now);

//        SmlAccountForwardModel safMdl = null;
        String[] accountSid = null;
        ArrayList<String> allAccountSidList = new ArrayList<String>();
        List<Integer> sendAccountList = new ArrayList<Integer>();
        for (int n = 0; n < accountSidList.size(); n++) {

            accountSid = accountSidList.get(n);

            for (int i = 0; i < accountSid.length; i++) {
                if (allAccountSidList.contains(accountSid[i])) {
                    //一度送信したユーザを除く
                    continue;
                }
                allAccountSidList.add(accountSid[i]);

                SmlJmeisModel jparam = new SmlJmeisModel();
                jparam.setSacSid(Integer.parseInt(accountSid[i]));
                jparam.setSmjSid(mailSid);
                //userConf = smlCmnBiz.getSmailUserConf(Integer.parseInt(accountSid[i]), con);
//              safMdl = smlCmnBiz.getSmailAccountForward(Integer.parseInt(accountSid[i]), -1, con);
//                if (safMdl != null && smlCmnBiz.isSmailForwardOk(
//                        Integer.parseInt(accountSid[i]), safMdl, adminConf, con)) {
//                    jparam.setSmjOpkbn(safMdl.getSafSmailOp());
//                } else {
//                    jparam.setSmjOpkbn(GSConstSmail.OPKBN_UNOPENED);
//                }
                jparam.setSmjOpkbn(GSConstSmail.OPKBN_UNOPENED);
                jparam.setSmjFwkbn(GSConstSmail.FWKBN_NO);
                jparam.setSmjOpdate(null);
                jparam.setSmjJkbn(GSConst.JTKBN_TOROKU);
                jparam.setSmjSendkbn(sendKbnList.get(n));
                jparam.setSmjAuid(usrSid);
                jparam.setSmjAdate(now);
                jparam.setSmjEuid(usrSid);
                jparam.setSmjEdate(now);
                jdao.insert(jparam);

                sendAccountList.add(Integer.parseInt(accountSid[i]));
//                log__.debug("ショートメールリスナー doSmailUnOpen()開始");
//                //各プラグインリスナーを呼び出し
//                for (int j = 0; j < lis.length; j++) {
//                    lis[j].doSmailUnOpen(
//                            con, mailSid, Integer.parseInt(accountSid[i]));
//                }
//                log__.debug("ショートメールリスナー doSmailUnOpen()終了");

                //アカウントディスク使用量の再計算を行う
                smlBiz.updateAccountDiskSize(con, Integer.parseInt(accountSid[i]));

                //受信メールの集計データを登録する
                smlBiz.regJmeisLogCnt(
                        con, Integer.parseInt(accountSid[i]), sendKbnList.get(n), now);

            }
        }

        sendMdl.setSmjSid(mailSid);
        sendMdl.setAccountSidList(sendAccountList);

        //転送設定を取得し必要に応じてE-mailにて転送
        List<TempFileModel> fileList = biz.getTempFiles(tempDir);
        SmailDao smaildao = new SmailDao(con);
        ArrayList<SmailDetailModel> sdList =
            smaildao.selectSmeisDetailFromSid(sparam.getSmsSid());
        int fwkbn = 0;
        SmlJmeisModel jparam = null;

        if (sparam.getSmsType() == GSConstSmail.SAC_SEND_MAILTYPE_HTML) {
            //HTML形式の場合はattach.htmlを作成
            SmlMailFileModel fileMdl = new SmlMailFileModel();
            fileMdl.setFileName(GSConstSmail.HTMLMAIL_FILENAME);
            fileMdl.setContentType(
                    "Content-Type: text/html; charset=" + GSConstSmail.ATTACH_ENCODE);
            fileMdl.setFilePath(tempDir + "/" + GSConstSmail.HTMLMAIL_FILENAME);
            fileMdl.setHtmlMail(true);

            PrintWriter pw = null;
            try {
                IOTools.isDirCheck(tempDir, true);
                pw = new PrintWriter(new BufferedOutputStream(
                            new FileOutputStream(fileMdl.getFilePath())));
                pw.print(sparam.getSmsBody());
                pw.flush();
            } catch (Exception e) {
                log__.error("HTMLメールの保存に失敗", e);
                throw new IOToolsException("HTMLメールの保存に失敗", e);
            } finally {
                if (pw != null) {
                    pw.close();
                }
            }

            if (fileList == null || fileList.isEmpty()) {
                fileList = new ArrayList<TempFileModel>();
            }

            TempFileModel tempFileMdl = new TempFileModel();
            File file = new File(tempDir, GSConstSmail.HTMLMAIL_FILENAME);
            tempFileMdl.setFile(file);
            tempFileMdl.setFileName(GSConstSmail.HTMLMAIL_FILENAME);
            fileList.add(tempFileMdl);

            //メール送信用のテキストに変換
            bodyStr = StringUtilHtml.transToText(
                    StringUtilHtml.delHTMLTag(StringUtilHtml.transBRtoCRLF(bodyStr)));
            if (sdList != null && !sdList.isEmpty()) {
                sdList.get(0).setSmsBody(bodyStr);
            }
        }



        for (int i = 0; i < allAccountSidList.size(); i++) {

            fwkbn = smlCmnBiz.sendSmailForward(
                    sparam,
                    sdList,
                    Integer.parseInt(allAccountSidList.get(i)),
                    fileList,
                    adminConf,
                    pluginConfig,
                    con);

            if (fwkbn == GSConstSmail.ERROR_KBN) {
                continue;

            }

            if (fwkbn > 0) {
                jparam = new SmlJmeisModel();
                jparam.setSacSid(Integer.parseInt(allAccountSidList.get(i)));
                jparam.setSmjSid(mailSid);
                jparam.setSmjFwkbn(GSConstSmail.FWKBN_OK);
                jdao.updateFwkbn(jparam);
            }

        }

        SmlBinDao sbinDao = new SmlBinDao(con);

        //草稿モードの場合、草稿データと添付情報を削除(物理)
        if (paramMdl.getSml020ProcMode().equals(GSConstSmail.MSG_CREATE_MODE_SOKO)
        && editMailSid > 0) {

            //下書きを削除
            SmlWmeisModel wparam = new SmlWmeisModel();
            wparam.setSmwSid(editMailSid);
            SmlWmeisDao wdao = new SmlWmeisDao(con);
            wdao.delete(wparam);

            //下書き宛先を削除
            SmlAsakModel aparam = new SmlAsakModel();
            aparam.setSmsSid(editMailSid);
            SmlAsakDao adao = new SmlAsakDao(con);
            adao.deleteFromMailSid(aparam);

            //ショートメールに送付されているバイナリSID一覧取得
            String[] mailSidList = new String[1];
            mailSidList[0] = String.valueOf(editMailSid);
            List<Long> binSidList =
                sbinDao.selectBinSidList(mailSidList);

            //バイナリ情報を論理削除
            CmnBinfDao binDao = new CmnBinfDao(con);
            CmnBinfModel cbMdl = new CmnBinfModel();
            cbMdl.setBinJkbn(GSConst.JTKBN_DELETE);
            cbMdl.setBinUpuser(usrSid);
            cbMdl.setBinUpdate(new UDate());
            binDao.updateJKbn(cbMdl, binSidList);

            //添付情報を削除
            sbinDao.deleteSmlBin(editMailSid);
        }

        //ショートメール送付情報を登録
        sbinDao.insertSmlBin(sparam, binList);
        //ディスク容量を更新
        smlCmnBiz.updateAccountDiskSize(con, paramMdl.getSmlViewAccount());

        //テンポラリディレクトリのファイルを削除する
        IOTools.deleteDir(tempDir);
        log__.debug("テンポラリディレクトリのファイル削除");

        return sendMdl;
    }

    /**
     * <br>[機  能] 写真表示フラグを設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void setPhotoDsp(
            Sml020knParamModel paramMdl,
            RequestModel reqMdl,
            Connection con)
                    throws SQLException {
        //写真表示フラグ
        Sml010Biz sml010Biz = new Sml010Biz();
        int photoDspFlg = sml010Biz.getPhotoDspFlg(reqMdl, con);
        paramMdl.setPhotoDspFlg(photoDspFlg);
    }

    /**
     * <br>[機  能] オペレーションログ 操作内容を作成します
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエストモデル
     * @param paramMdl Sml020knParamModel
     * @param con コネクション
     * @return オペレーションログの操作内容
     * @throws SQLException SQL実行時例外
     */
    public String createSendLogValue(
            HttpServletRequest req,
            Sml020knParamModel paramMdl,
            Connection con) throws SQLException {
        StringBuilder opValue = new StringBuilder();

        GsMessage gsMsg = new GsMessage();
        SmlAccountModel sacMdl = new SmlAccountModel();
        SmlAccountDao sacDao = new SmlAccountDao(con);
        sacMdl = sacDao.select(paramMdl.getSmlViewAccount());

        opValue.append(gsMsg.getMessage(req, "cmn.mail.send"));
        opValue.append(":");
        opValue.append("\n[Subject]" + paramMdl.getSml020Title());
        opValue.append("\n[From]" + sacMdl.getSacName());

        //To
        String userNameTo = __getNamesForLog(con, paramMdl.getSml020userSid());
        opValue.append("\n[To]");
        if (userNameTo.length() <= GSConstSmail.MAXLEN_OPLOG_SENDERTO) {
            opValue.append(userNameTo);
        } else {
            //最大文字数を超える場合、人数と「...」を表示する
            opValue.append("(");
            opValue.append(paramMdl.getSml020userSid().length);
            opValue.append(gsMsg.getMessage(req, "anp.count.people"));
            opValue.append(")");
            opValue.append(StringUtil.trimRengeString(
                    userNameTo, GSConstSmail.MAXLEN_OPLOG_SENDERTO));
            opValue.append("...");
        }

        //Cc
        if (paramMdl.getSml020userSidCc() != null && paramMdl.getSml020userSidCc().length > 0) {
            opValue.append("\n[Cc]");
            String userNameCc = __getNamesForLog(con, paramMdl.getSml020userSidCc());
            if (userNameCc.length() <= GSConstSmail.MAXLEN_OPLOG_SENDERCC) {
                opValue.append(userNameCc);
            } else {
                //最大文字数を超える場合、人数と「...」を表示する
                opValue.append("(");
                opValue.append(paramMdl.getSml020userSidCc().length);
                opValue.append(gsMsg.getMessage(req, "anp.count.people"));
                opValue.append(")");
                opValue.append(StringUtil.trimRengeString(
                        userNameCc, GSConstSmail.MAXLEN_OPLOG_SENDERCC));
                opValue.append("...");
            }
        }

        //Bcc
        if (paramMdl.getSml020userSidBcc() != null && paramMdl.getSml020userSidBcc().length > 0) {
            opValue.append("\n[Bcc]");
            String userNameBcc = __getNamesForLog(con, paramMdl.getSml020userSidBcc());
            if (userNameBcc.length() <= GSConstSmail.MAXLEN_OPLOG_SENDERCC) {
                opValue.append(userNameBcc);
            } else {
                //最大文字数を超える場合、人数と「...」を表示する
                opValue.append("(");
                opValue.append(paramMdl.getSml020userSidBcc().length);
                opValue.append(gsMsg.getMessage(req, "anp.count.people"));
                opValue.append(")");
                opValue.append(StringUtil.trimRengeString(
                        userNameBcc, GSConstSmail.MAXLEN_OPLOG_SENDERCC));
                opValue.append("...");
            }
        }

        opValue.append("\n[Body]" + StringUtil.trimRengeString(
                paramMdl.getSml020Body(), GSConstSmail.MAXLEN_OPLOG_BODY));
        if (paramMdl.getSml020Body().length() > GSConstSmail.MAXLEN_OPLOG_BODY) {
            opValue.append("...");
        }

        return opValue.toString();
    }

    /**
     * <br>[機  能] ログ用の名前一覧を返します
     * <br>[解  説] カンマで区切られた名前一覧を返します
     * <br>[備  考] オペレーションログ用
     * @param con コネクション
     * @param sids ユーザSID
     * @return カンマで区切られた名前一覧
     * @throws SQLException SQL実行時例外
     */
    private String __getNamesForLog(
            Connection con,
            String[] sids)
                    throws SQLException {
        StringBuilder userNames = new StringBuilder();
        CmnUsrmDao usrmDao = new CmnUsrmDao(con);

        ArrayList<BaseUserModel> userModelList = usrmDao.getSelectedUserList(sids);
        boolean firstFlg = true;

        for (BaseUserModel userMdl : userModelList) {
            if (firstFlg) {
                firstFlg = false;
            } else {
                userNames.append(",");
            }
            userNames.append(userMdl.getUsisei() + " " + userMdl.getUsimei());
        }

        return userNames.toString();
    }
}