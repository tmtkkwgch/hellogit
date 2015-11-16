package jp.groupsession.v2.cir.cir040kn;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cir.GSConstCircular;
import jp.groupsession.v2.cir.biz.CirCommonBiz;
import jp.groupsession.v2.cir.dao.CirAccountDao;
import jp.groupsession.v2.cir.dao.CirBinDao;
import jp.groupsession.v2.cir.dao.CirInfDao;
import jp.groupsession.v2.cir.dao.CircularDao;
import jp.groupsession.v2.cir.model.CirAccountModel;
import jp.groupsession.v2.cir.model.CirAconfModel;
import jp.groupsession.v2.cir.model.CirBinModel;
import jp.groupsession.v2.cir.model.CirInfModel;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.sml.GSConstSmail;
import jp.groupsession.v2.sml.SmlSender;
import jp.groupsession.v2.sml.model.SmlSenderModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 回覧板 新規作成確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cir040knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Cir040knBiz.class);
    /** DBコネクション */
    public Connection con__ = null;

    /**
     * <p>コンストラクタ
     * @param con Connection
     */
    public Cir040knBiz(Connection con) {
        con__ = con;
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
     * <br>[機  能] 初期表示情報を画面にセットする
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param tempDir テンポラリディレクトリパス
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイルアクセス時例外
     */
    public void setInitData(
        Cir040knParamModel paramMdl,
        Connection con,
        String tempDir) throws SQLException, IOToolsException {

        CirAccountDao cacDao = new CirAccountDao(con);
        CirAccountModel cacMdl = null;

        cacMdl = cacDao.select(paramMdl.getCirViewAccount());

        if (cacMdl != null) {
            //アカウント
            paramMdl.setCirViewAccount(cacMdl.getCacSid());
            //アカウント名
            paramMdl.setCirViewAccountName(cacMdl.getCacName());
            //アカウントテーマ
            paramMdl.setCir010AccountTheme(cacMdl.getCacTheme());
        }

        /** 回覧先をセット ******************************************************/
        if (paramMdl.getCir040userSid() != null
                && paramMdl.getCir040userSid().length > 0) {
            CirCommonBiz cirBiz = new CirCommonBiz();
            String[] accountSids = cirBiz.getAccountSidFromUsr(con, paramMdl.getCir040userSid());
            paramMdl.setCir040MemberList(cacDao.getAccountList(accountSids));
        }


        //表示用内容
        String tmpBody =
            StringUtilHtml.transToHTmlPlusAmparsant(
                    NullDefault.getString(paramMdl.getCir040value(), ""));
        tmpBody = StringUtil.transToLink(tmpBody, StringUtil.OTHER_WIN, true);

        paramMdl.setCir040knBody(tmpBody);
    }

    /**
     * <br>[機  能] 回覧板の登録を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @param tempDir テンポラリディレクトリパス
     * @param appRootPath アプリケーションのルートパス
     * @return int 回覧板SID
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイルアクセス時例外
     * @throws IOException 入出力時例外
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public int doInsert(
            Cir040knParamModel paramMdl,
            MlCountMtController cntCon,
            int userSid,
            String tempDir,
            String appRootPath)
    throws SQLException, IOToolsException, IOException, TempFileException {

        int cirSid = 0;

        boolean commit = false;
        try {
            con__.setAutoCommit(false);

            //システム日付
            UDate now = new UDate();

            /** 添付ファイルを登録 **********************************************/
            //テンポラリディレクトリパスにある添付ファイルを全て登録
            CommonBiz biz = new CommonBiz();
            List < String > binList =
                biz.insertBinInfo(con__, tempDir, appRootPath, cntCon, userSid, now);


            /** 回覧板情報を登録 **********************************************/
            //回覧板SID採番
            cirSid = (int) cntCon.getSaibanNumber(GSConstCircular.SBNSID_CIRCULAR,
                                                       GSConstCircular.SBNSID_SUB_CIRCULAR,
                                                       userSid);

            log__.debug("shoukbn ===> " + paramMdl.getCir040show());

            /** メモ欄修正期限を設定 *******************************************/
            UDate memoLimit = new UDate();
            int year = paramMdl.getCir040memoPeriodYear();
            int month = paramMdl.getCir040memoPeriodMonth();
            int day = paramMdl.getCir040memoPeriodDay();

            if (year == -1 && month == -1 && day == -1) {
                memoLimit = null;
            } else {
                memoLimit.setDate(year, month, day);
            }

            CirInfModel ciMdl = new CirInfModel();
            ciMdl.setCifSid(cirSid);
            ciMdl.setCifTitle(NullDefault.getString(paramMdl.getCir040title(), ""));
            ciMdl.setGrpSid(NullDefault.getInt(paramMdl.getCir040groupSid(), -1));
            ciMdl.setCifValue(NullDefault.getString(paramMdl.getCir040value(), ""));
            ciMdl.setCifAuid(paramMdl.getCirViewAccount());
            ciMdl.setCifAdate(now);
            ciMdl.setCifEuid(paramMdl.getCirViewAccount());
            ciMdl.setCifEdate(now);
            ciMdl.setCifJkbn(GSConstCircular.DSPKBN_DSP_OK);
            ciMdl.setCifEkbn(GSConstCircular.CIR_NO_EDIT);
            ciMdl.setCifShow(paramMdl.getCir040show());
            ciMdl.setCifMemoFlg(paramMdl.getCir040memoKbn());
            ciMdl.setCifMemoDate(memoLimit);
            ciMdl.setCifEditDate(now);

            //回覧板情報を登録する
            CircularDao cDao = new CircularDao(con__);
            cDao.insertCir(
               ciMdl, binList, paramMdl.getCir040userSid());

            con__.commit();
            commit = true;
        } catch (SQLException e) {
            log__.warn("回覧板登録に失敗", e);
            JDBCUtil.rollback(con__);
            throw e;
        } catch (TempFileException e) {
            log__.warn("回覧板添付ファイル登録に失敗", e);
            JDBCUtil.rollback(con__);
            throw e;
        } finally {
            if (!commit) {
                con__.rollback();
            }
        }

        //テンポラリディレクトリのファイルを削除する
        IOTools.deleteDir(tempDir);
        log__.debug("テンポラリディレクトリのファイル削除");

        return cirSid;
    }

    /**
     * <br>[機  能] 回覧板の登録を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param userSid ログインユーザSID
     * @param tempDir テンポラリディレクトリパス
     * @param cntCon MlCountMtController
     * @param appRootPath アプリケーションのルートパス
     * @return int 回覧板SID
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイルアクセス時例外
     * @throws IOException 入出力時例外
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public int doEdit(
            Cir040knParamModel paramMdl,
            int userSid,
            String tempDir,
            MlCountMtController cntCon,
            String appRootPath)
    throws SQLException, IOToolsException, IOException, TempFileException {

        int cirSid = 0;

        if (ValidateUtil.isNumber(paramMdl.getCirEditInfSid())) {
            cirSid = Integer.valueOf(paramMdl.getCirEditInfSid());

            boolean commit = false;
            try {
                con__.setAutoCommit(false);

                //システム日付
                UDate now = new UDate();

                CircularDao cDao = new CircularDao(con__);

                /** 添付ファイルを登録 **********************************************/
                CirBinDao bdao = new CirBinDao(con__);
                List < CirBinModel > cbList
                           = bdao.getBinList(new String[] {String.valueOf(cirSid)});
                List<Long> cbSidList = new ArrayList<Long>();
                for (CirBinModel cbMdl : cbList) {
                    cbSidList.add(cbMdl.getBinSid());
                }
                //バイナリ情報を論理削除
                CmnBinfModel cbMdl = new CmnBinfModel();
                cbMdl.setBinJkbn(GSConst.JTKBN_DELETE);
                cbMdl.setBinUpuser(userSid);
                cbMdl.setBinUpdate(now);
                cDao.updateJKbn(cbMdl, cbSidList);

                //回覧板添付情報(CIR_BIN)を物理削除
                bdao.deleteCriBin(new String[] {paramMdl.getCirEditInfSid()});

                //テンポラリディレクトリパスにある添付ファイルを全て登録
                CommonBiz biz = new CommonBiz();
                List < String > binList =
                    biz.insertBinInfo(con__, tempDir, appRootPath, cntCon, userSid, now);


                log__.debug("shoukbn ===> " + paramMdl.getCir040show());

                /** メモ欄修正期限を設定 *******************************************/
                UDate memoLimit = new UDate();
                int year = paramMdl.getCir040memoPeriodYear();
                int month = paramMdl.getCir040memoPeriodMonth();
                int day = paramMdl.getCir040memoPeriodDay();

                if (year == -1 && month == -1 && day == -1) {
                    memoLimit = null;
                } else {
                    memoLimit.setDate(year, month, day);
                }

                CirInfModel ciMdl = new CirInfModel();
                ciMdl.setCifSid(cirSid);
                ciMdl.setCifTitle(NullDefault.getString(paramMdl.getCir040title(), ""));
                ciMdl.setGrpSid(NullDefault.getInt(paramMdl.getCir040groupSid(), -1));
                ciMdl.setCifValue(NullDefault.getString(paramMdl.getCir040value(), ""));
                ciMdl.setCifAuid(paramMdl.getCirViewAccount());
                ciMdl.setCifAdate(now);
                ciMdl.setCifEuid(paramMdl.getCirViewAccount());
                ciMdl.setCifEdate(now);
                ciMdl.setCifJkbn(GSConstCircular.DSPKBN_DSP_OK);
                ciMdl.setCifEkbn(GSConstCircular.CIR_EDIT);
                ciMdl.setCifShow(paramMdl.getCir040show());
                ciMdl.setCifMemoFlg(paramMdl.getCir040memoKbn());
                ciMdl.setCifMemoDate(memoLimit);
                ciMdl.setCifEditDate(now);

                //回覧板情報を登録する
                cDao.updateCir(
                   ciMdl, binList, paramMdl.getCir040userSid());

                con__.commit();
                commit = true;
            } catch (SQLException e) {
                log__.warn("回覧板登録に失敗", e);
                JDBCUtil.rollback(con__);
                throw e;
            } catch (TempFileException e) {
                log__.warn("回覧板添付ファイル登録に失敗", e);
                JDBCUtil.rollback(con__);
                throw e;
            } finally {
                if (!commit) {
                    con__.rollback();
                }
            }
        }

        //テンポラリディレクトリのファイルを削除する
        IOTools.deleteDir(tempDir);
        log__.debug("テンポラリディレクトリのファイル削除");

        return cirSid;
    }

    /**
     * <br>[機  能] 受信者にショートメールで通知を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param buMdl ユーザ情報モデル
     * @param paramMdl パラメータ情報
     * @param cirSid 回覧板SID
     * @param appRootPath アプリケーションのルートパス
     * @param cntCon MlCountMtController
     * @param pluginConfig PluginConfig
     * @param reqMdl リクエスト情報
     * @throws Exception 実行例外
     */
    public void doNotifies(
        BaseUserModel buMdl,
        Cir040knParamModel paramMdl,
        int cirSid,
        String appRootPath,
        MlCountMtController cntCon,
        PluginConfig pluginConfig,
        RequestModel reqMdl) throws Exception {

        //回覧先SID
        CirCommonBiz cirBiz = new CirCommonBiz();
        String[] userSid = paramMdl.getCir040userSid();


        CirCommonBiz biz = new CirCommonBiz();
        CirAconfModel admData = biz.getCirAdminData(con__, reqMdl.getSmodel().getUsrsid());
        boolean smlFlg = false;
        //管理者設定でショートメール通知が「管理者が設定する」の場合
        if (admData.getCafSmailSendKbn() == GSConstCircular.CAF_SML_NTF_ADMIN) {
            //管理者設定で「通知する」の場合
            if (admData.getCafSmailSend() == GSConstCircular.CAF_SML_NTF_KBN_YES) {
                smlFlg = false;

            //管理者設定で「通知しない」の場合、送信処理終了
            } else {
                return;
            }

        //管理者設定でショートメール通知が「各ユーザが設定する」の場合
        } else {
            smlFlg = true;
        }

        //指定されたユーザSIDリストの中からショートメール通知対象の受信者情報を取得
        CirAccountDao cacDao = new CirAccountDao(con__);
        List<CirAccountModel> cacList =
                cacDao.getMailSendUser(cirBiz.getAccountSidFromUsr(con__, userSid), smlFlg);

        log__.debug("cuList.size() = " + cacList.size());
        if (cacList.size() < 0) {
            //送信対象ユーザがいない場合、処理終了
            return;
        }

        //TOリストを作成
        List<String>  sidList = new ArrayList<String>();
        for (CirAccountModel cacMdl : cacList) {
            sidList.add(GSConstCircular.CIR_ACCOUNT_STR + String.valueOf(cacMdl.getCacSid()));
        }

        //回覧板情報を取得する
        CirInfDao ciDao = new CirInfDao(con__);
        CirInfModel ciMdl = ciDao.getCirInfo(cirSid);
        if (ciMdl == null) {
            return;
        }

        log__.debug("回覧板受信通知メールを送信する");
        __doMailSend(buMdl, sidList, ciMdl, appRootPath, cntCon, pluginConfig, reqMdl);
    }

    /**
     * <br>[機  能] 回覧板受信通知メールを送信する
     * <br>[解  説]
     * <br>[備  考]
     * @param buMdl ユーザ情報モデル
     * @param sidList メール送信ユーザ
     * @param ciMdl CirInfModel
     * @param appRootPath アプリケーションのルートパス
     * @param cntCon MlCountMtController
     * @param pluginConfig PluginConfig
     * @param reqMdl リクエスト情報
     * @throws Exception 実行例外
     */
    private void __doMailSend(
        BaseUserModel buMdl,
        List<String>  sidList,
        CirInfModel ciMdl,
        String appRootPath,
        MlCountMtController cntCon,
        PluginConfig pluginConfig,
        RequestModel reqMdl) throws Exception {

        //発信日時作成
        String tdate = UDateUtil.getSlashYYMD(ciMdl.getCifAdate()) + " "
        + UDateUtil.getSeparateHMS(ciMdl.getCifAdate());

        //ファイルリスト作成
        CircularDao cirDao = new CircularDao(con__);
        List<CmnBinfModel> cbList = cirDao.getFileInfo(ciMdl.getCifSid());
        StringBuilder fileLine = new StringBuilder();
        for (CmnBinfModel cbMdl : cbList) {
            String fileName = cbMdl.getBinFileName();
            if (fileLine.length() != 0) {
                fileLine.append(" , ");
            }
            fileLine.append(fileName);
        }

        //URL
        CirCommonBiz biz = new CirCommonBiz();
        int infSid = ciMdl.getCifSid();
        int hantei = GSConstCircular.SMAIL_DSP_KAKUNIN;
        String url = biz.createThreadUrl(reqMdl, infSid, hantei);

        //メール本文作成
        Map<String, String> map = new HashMap<String, String>();
        map.put("TITLE", ciMdl.getCifTitle());
        map.put("DATE", tdate);
        String sendName = "";
//        //ログインユーザSIDを取得
//        if (buMdl != null) {
//            sendName = buMdl.getUsiseimei();
//        }
        CirAccountDao cacDao = new CirAccountDao(con__);
        CirAccountModel cacMdl = new CirAccountModel();
        cacMdl = cacDao.select(ciMdl.getCifAuid());
        if (cacMdl != null) {
            sendName = cacMdl.getCacName();
        }

        map.put("NAME", sendName);
        map.put("FILES", fileLine.toString());
        map.put("BODY", ciMdl.getCifValue());
        map.put("URL", url);

        //テンプレートファイルパス取得
        String tmpPath = __getSmlTemplateFilePath(appRootPath);
        String tmpBody = IOTools.readText(tmpPath, Encoding.UTF_8);
        String bodyml = StringUtil.merge(tmpBody, map);

        GsMessage gsMsg = new GsMessage(reqMdl);
        String message = gsMsg.getMessage("cmn.mail.omit");

        if (bodyml.length() > GSConstCommon.MAX_LENGTH_SMLBODY) {
            bodyml = message + "\r\n\r\n" + bodyml;
            bodyml = bodyml.substring(0, GSConstCommon.MAX_LENGTH_SMLBODY);
        }

        //ショートメール送信用モデルを作成する。
        SmlSenderModel smlModel = new SmlSenderModel();
        //送信者(システムメールを指定)
        smlModel.setSendUsid(GSConst.SYSTEM_USER_MAIL);

        //TO アカウントSIDから使用ユーザを取得
        String[] usrSids = biz.getUsrSidFromSelSid(
                con__, (String[]) sidList.toArray(new String[sidList.size()]));

        List<Integer> usrSidList = new ArrayList<Integer>();
        if (usrSids != null && usrSids.length > 0) {
            for (String usId : usrSids) {
                if (ValidateUtil.isNumber(usId) && Integer.valueOf(usId) > 0) {
                    usrSidList.add(Integer.valueOf(usId));
                }
            }
        }
        smlModel.setSendToUsrSidArray(usrSidList);

        //タイトル
        String title = gsMsg.getMessage("cir.52");
        String cirTitle = ciMdl.getCifTitle();

        //30文字まで表示
        if (cirTitle.length() > 30) {
            cirTitle = cirTitle.substring(0, 30);
        }

        smlModel.setSendTitle(title + "　" + cirTitle);
        //本文
        smlModel.setSendBody(bodyml);
        //メール形式
        smlModel.setSendType(GSConstSmail.SAC_SEND_MAILTYPE_NORMAL);
        //マーク
        smlModel.setSendMark(GSConstSmail.MARK_KBN_NONE);

        boolean commit = false;
        try {

            //メール送信処理開始
            SmlSender sender = new SmlSender(con__, cntCon, smlModel,
                                             pluginConfig, appRootPath, reqMdl);
            sender.execute();

            commit = true;
        } catch (Exception e) {
            log__.error("スレッド作成処理エラー", e);
            throw e;
        } finally {
            if (commit) {
                con__.commit();
            } else {
                con__.rollback();
            }
        }
    }

    /**
     * <br>[機  能] アプリケーションのルートパスから受信通知メールのテンプレートパスを返す。
     * <br>[解  説]
     * <br>[備  考]
     * @param appRootPath アプリケーションのルートパス
     * @return テンプレートファイルのパス文字列
     */
    private String __getSmlTemplateFilePath(String appRootPath) {
        //WEBアプリケーションのパス
        appRootPath = IOTools.setEndPathChar(appRootPath);
        String ret = IOTools.replaceSlashFileSep(appRootPath
                + "/WEB-INF/plugin/circular/smail/jusin_tsuuchi.txt");
        return ret;
    }

    /**
     * <br>[機  能] 添付ファイル情報をセット
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param tempDir テンポラリディレクトリ
     * @throws IOToolsException ファイルアクセス時例外
     */
    @SuppressWarnings("unchecked")
    public void setTempFiles(Cir040knParamModel paramMdl, String tempDir)
        throws IOToolsException {

        /** 画面に表示するファイルのリストを作成、セット **********************/
        CommonBiz commonBiz = new CommonBiz();
        List<LabelValueBean> sortList = commonBiz.getTempFileLabelList(tempDir);
        Collections.sort(sortList);
        paramMdl.setCir040FileLabelList(sortList);
    }

}
