package jp.groupsession.v2.wml.wml010;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;
import javax.mail.internet.ParseException;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.PageUtil;
import jp.co.sjts.util.RandomPassword;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.archive.ZipUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.http.ContentType;
import jp.co.sjts.util.http.TempFileUtil;
import jp.co.sjts.util.io.CrlfTerminatedWriter;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.io.ObjectFile;
import jp.co.sjts.util.mail.MailUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.GSConstWebmail;
import jp.groupsession.v2.cmn.GSContext;
import jp.groupsession.v2.cmn.GSException;
import jp.groupsession.v2.cmn.GSValidateUtil;
import jp.groupsession.v2.cmn.GroupSession;
import jp.groupsession.v2.cmn.ITempFileUtil;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.MailBiz;
import jp.groupsession.v2.cmn.cmn110.Cmn110Biz;
import jp.groupsession.v2.cmn.cmn110.Cmn110FileModel;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.WmlDao;
import jp.groupsession.v2.cmn.dao.base.CmnThemeDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrLangDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmInfDao;
import jp.groupsession.v2.cmn.dao.base.WmlTempfileDao;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.cmn.model.base.CmnThemeModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrLangModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.cmn.model.base.WmlMailFileModel;
import jp.groupsession.v2.cmn.model.base.WmlTempfileModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.wml.batch.WmlReceiveBatch;
import jp.groupsession.v2.wml.biz.WmlBiz;
import jp.groupsession.v2.wml.dao.WebmailDao;
import jp.groupsession.v2.wml.dao.base.WmlAccountDao;
import jp.groupsession.v2.wml.dao.base.WmlAccountSignDao;
import jp.groupsession.v2.wml.dao.base.WmlAdmConfDao;
import jp.groupsession.v2.wml.dao.base.WmlAutodeleteDao;
import jp.groupsession.v2.wml.dao.base.WmlDestlistAddressDao;
import jp.groupsession.v2.wml.dao.base.WmlDirectoryDao;
import jp.groupsession.v2.wml.dao.base.WmlHeaderDataDao;
import jp.groupsession.v2.wml.dao.base.WmlLabelDao;
import jp.groupsession.v2.wml.dao.base.WmlLabelRelationDao;
import jp.groupsession.v2.wml.dao.base.WmlMailBodyDao;
import jp.groupsession.v2.wml.dao.base.WmlMailSendplanDao;
import jp.groupsession.v2.wml.dao.base.WmlMailTemplateDao;
import jp.groupsession.v2.wml.dao.base.WmlMailTemplateFileDao;
import jp.groupsession.v2.wml.dao.base.WmlMaildataDao;
import jp.groupsession.v2.wml.dao.base.WmlMaildataSortDao;
import jp.groupsession.v2.wml.dao.base.WmlMaildataSortSearchDao;
import jp.groupsession.v2.wml.dao.base.WmlSendaddressDao;
import jp.groupsession.v2.wml.model.MailTempFileModel;
import jp.groupsession.v2.wml.model.WmlMailDeleteModel;
import jp.groupsession.v2.wml.model.WmlMailModel;
import jp.groupsession.v2.wml.model.WmlSendResultModel;
import jp.groupsession.v2.wml.model.base.WmlAccountModel;
import jp.groupsession.v2.wml.model.base.WmlAccountSignModel;
import jp.groupsession.v2.wml.model.base.WmlAdmConfModel;
import jp.groupsession.v2.wml.model.base.WmlAutodeleteModel;
import jp.groupsession.v2.wml.model.base.WmlDestlistAddressModel;
import jp.groupsession.v2.wml.model.base.WmlDestlistModel;
import jp.groupsession.v2.wml.model.base.WmlDirectoryModel;
import jp.groupsession.v2.wml.model.base.WmlHeaderDataModel;
import jp.groupsession.v2.wml.model.base.WmlLabelModel;
import jp.groupsession.v2.wml.model.base.WmlLabelRelationModel;
import jp.groupsession.v2.wml.model.base.WmlMailBodyModel;
import jp.groupsession.v2.wml.model.base.WmlMailTemplateModel;
import jp.groupsession.v2.wml.model.base.WmlMaildataModel;
import jp.groupsession.v2.wml.model.base.WmlMaildataSortModel;
import jp.groupsession.v2.wml.model.base.WmlMaildataSortSearchModel;
import jp.groupsession.v2.wml.model.base.WmlSendaddressModel;
import jp.groupsession.v2.wml.pdf.WmlPdfModel;
import jp.groupsession.v2.wml.pdf.WmlPdfUtil;
import jp.groupsession.v2.wml.smtp.WmlSmtpSendBiz;
import jp.groupsession.v2.wml.smtp.model.SmtpModel;
import jp.groupsession.v2.wml.smtp.model.SmtpSendModel;
import jp.groupsession.v2.wml.wml010.model.Wml010AddressModel;
import jp.groupsession.v2.wml.wml010.model.Wml010DirectoryModel;
import jp.groupsession.v2.wml.wml010.model.Wml010ExportFileModel;
import jp.groupsession.v2.wml.wml010.model.Wml010LabelModel;
import jp.groupsession.v2.wml.wml010.model.Wml010MailModel;
import jp.groupsession.v2.wml.wml010.model.Wml010SearchModel;
import jp.groupsession.v2.wml.wml010.model.Wml010SendAddrModel;
import jp.groupsession.v2.wml.wml010.model.Wml010SendMailModel;
import jp.groupsession.v2.wml.wml280.Wml280AddressBookModel;
import jp.groupsession.v2.wml.wml280.Wml280Dao;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.util.MessageResources;

import com.sun.mail.util.QPEncoderStream;

/**
 * <br>[機  能] WEBメール メール一覧画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml010Biz {

    /** メール一覧の最大表示件数 */
    public static final int MAILLIST_MAXCOUNT = 30;
    /** メール一覧 宛先の最大文字数 */
    public static final int MAXLEN_LISTTO = 100;

    /** オペレーションログ ゴミ箱へ移動 */
    protected static final int LOGTYPE_MAILDELETE_ = 0;
    /** オペレーションログ ゴミ箱を空にする */
    protected static final int LOGTYPE_EMPTYTRASH_ = 1;
    /** オペレーションログ メールの移動 */
    protected static final int LOGTYPE_MOVEMAIL_ = 2;

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Wml010Biz.class);

    /**
     * <br>[機  能] メール一覧画面を表示できるかを判定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param userMdl セッションユーザモデル
     * @return 判定結果
     * @throws SQLException SQL実行時例外
     */
    public int checkViewMailList(Connection con, BaseUserModel userMdl)
    throws SQLException {
        int result = Wml010Const.MAILLIST_VIEW_MOVEMAIN;
        WmlAccountDao accountDao = new WmlAccountDao(con);
        if (accountDao.getAccountCount(userMdl.getUsrsid()) == 0) {
            CommonBiz cmnBiz = new CommonBiz();
            boolean adminUser
                = cmnBiz.isPluginAdmin(con, userMdl, GSConstWebmail.PLUGIN_ID_WEBMAIL);

            if (adminUser) {
                result = Wml010Const.MAILLIST_VIEW_MAKEACCOUT;
            } else {
                WmlAdmConfDao admConfDao = new WmlAdmConfDao(con);
                WmlAdmConfModel admConfMdl = admConfDao.selectAdmData();

                if (admConfMdl.getWadAcntMake() == GSConstWebmail.KANRI_USER_NO) {
                    result = Wml010Const.MAILLIST_VIEW_MAKEACCOUT;
                }
            }
        } else {
            result = Wml010Const.MAILLIST_VIEW_OK;
        }

        return result;
    }

    /**
     * <br>[機  能] デフォルトアカウントを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param userSid ユーザSID
     * @return デフォルトアカウントのアカウントSID
     * @throws SQLException SQL実行時例外
     */
    public int getDefaultAccount(Connection con, int userSid)
    throws SQLException {
        WmlAccountDao accountDao = new WmlAccountDao(con);
        return accountDao.getDefaultAccountSid(userSid);
    }

    /**
     * <br>[機  能] アカウントが使用可能かを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl リクエスト情報
     * @param userSid ユーザSID
     * @return true:使用可能 false:使用不可
     * @throws SQLException SQL実行時例外
     */
    public boolean canUseAccount(Connection con, Wml010ParamModel paramMdl, int userSid)
    throws SQLException {
        int wacSid = 0;
        if (!StringUtil.isNullZeroString(paramMdl.getWmlViewAccount())) {
            wacSid = _getViewAccountSid(paramMdl);
        }
        if (wacSid <= 0) {
            return false;
        }

        WmlDao wmlDao = new WmlDao(con);
        return wmlDao.canUseAccount(wacSid, userSid);
    }

    /**
     * <br>[機  能] 初期表示設定
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl リクエスト情報
     * @param appRootPath アプリケーションルートパス
     * @param userSid セッションユーザSID
     * @param reqMdl リクエスト情報
     * @throws SQLException SQL実行時例外
     */
    public void setInitData(Connection con, Wml010ParamModel paramMdl,
                            String appRootPath, int userSid,
                            RequestModel reqMdl)
    throws SQLException {

        GsMessage gsMsg = new GsMessage(reqMdl);
        Wml010Dao dao010 = new Wml010Dao(con);

        //アカウント情報を取得する
        WmlAccountDao accountDao = new WmlAccountDao(con);
        List<WmlAccountModel> accountList = accountDao.getAccountList(userSid);

        List<LabelValueBean> accountCombo = new ArrayList<LabelValueBean>();
        for (WmlAccountModel accountData : accountList) {
            LabelValueBean accountLabel
                    = new LabelValueBean(accountData.getWacName(),
                                        String.valueOf(accountData.getWacSid()));
            accountCombo.add(accountLabel);
        }
        if (accountCombo.isEmpty()) {
            accountCombo.add(
                    new LabelValueBean(gsMsg.getMessage("wml.132"), "-1"));

        } else {
            if (StringUtil.isNullZeroString(paramMdl.getWmlViewAccount())) {
                paramMdl.setWmlViewAccount(accountCombo.get(0).getValue());
            }

            if (paramMdl.getWml010viewDirectory() <= 0) {
                WmlDirectoryDao directoryDao = new WmlDirectoryDao(con);
                paramMdl.setWml010viewDirectory(
                        directoryDao.getReceiveDirSid(_getViewAccountSid(paramMdl)));
            }

            paramMdl.setAccountLinkList(
                    dao010.getNotSelectAccountList(_getViewAccountSid(paramMdl), userSid));
        }

        paramMdl.setAccountCombo(accountCombo);

        //キーワードコンボ、ラベルコンボ、フォルダコンボを設定する
        List<LabelValueBean> keywordCombo = new ArrayList<LabelValueBean>();
        keywordCombo.add(new LabelValueBean(gsMsg.getMessage("cmn.all"), "-1"));

        int wacSid = NullDefault.getInt(paramMdl.getWmlViewAccount(), 0);
        WmlDirectoryDao directoryDao = new WmlDirectoryDao(con);
        List<WmlDirectoryModel> directoryList
                = directoryDao.getDirectoryList(wacSid);
        List<LabelValueBean> folderCombo = new ArrayList<LabelValueBean>();
        for (WmlDirectoryModel dirData : directoryList) {
            keywordCombo.add(new LabelValueBean(dirData.getWdrName(),
                                                "dir:" + String.valueOf(dirData.getWdrSid())));

            //未送信、ゴミ箱フォルダはフォルダコンボから除外する
            if (dirData.getWdrType() != GSConstWebmail.DIR_TYPE_NOSEND
            && dirData.getWdrType() != GSConstWebmail.DIR_TYPE_DUST) {
                folderCombo.add(new LabelValueBean(dirData.getWdrName(),
                                String.valueOf(dirData.getWdrSid())));
            }
        }

        WmlLabelDao labelDao = new WmlLabelDao(con);
        List<WmlLabelModel> labelList = labelDao.getLabelList(wacSid);
        List<LabelValueBean> labelCombo = new ArrayList<LabelValueBean>();
        for (WmlLabelModel labelData : labelList) {
            keywordCombo.add(new LabelValueBean(labelData.getWlbName(),
                                                "label:" + String.valueOf(labelData.getWlbSid())));
            labelCombo.add(new LabelValueBean(labelData.getWlbName(),
                        String.valueOf(labelData.getWlbSid())));
        }

        paramMdl.setKeywordCombo(keywordCombo);
        paramMdl.setLabelCombo(labelCombo);
        paramMdl.setFolderCombo(folderCombo);

        List<LabelValueBean> destlistCombo = new ArrayList<LabelValueBean>();
        WebmailDao webmailDao = new WebmailDao(con);
        List<WmlDestlistModel> destList = webmailDao.getDestList(userSid);
        for (WmlDestlistModel destlistData : destList) {
            LabelValueBean destLabel
                    = new LabelValueBean(destlistData.getWdlName(),
                                                    String.valueOf(destlistData.getWdlSid()));
            destlistCombo.add(destLabel);
        }
        paramMdl.setDestlistCombo(destlistCombo);

        //年、月、日コンボを設定する。
        //TODO
//        UDate[] mailDate = dao010.getMailDate(Integer.parseInt(paramMdl.getWmlViewAccount()));
//        if (mailDate != null) {
//            paramMdl.setYearCombo(
//                    __createDateCombo("", mailDate[0].getYear(), mailDate[1].getYear()));
//        } else {
//            UDate now = new UDate();
//            paramMdl.setYearCombo(__createDateCombo("", now.getYear() - 5, now.getYear()));
//        }
        UDate now = new UDate();
        paramMdl.setYearCombo(__createDateCombo("", now.getYear() - 10, now.getYear() + 10));

        paramMdl.setMonthCombo(__createDateCombo("", 1, 12));
        paramMdl.setDayCombo(__createDateCombo("", 1, 31));

        UDate date = new UDate();
        if (StringUtil.isNullZeroString(paramMdl.getWml010searchDateYearFr())
        && StringUtil.isNullZeroString(paramMdl.getWml010searchDateMonthFr())
        && StringUtil.isNullZeroString(paramMdl.getWml010searchDateDayFr())) {
            paramMdl.setWml010searchDateYearFr(String.valueOf(date.getYear()));
            paramMdl.setWml010searchDateMonthFr(String.valueOf(date.getMonth()));
            paramMdl.setWml010searchDateDayFr(String.valueOf(date.getIntDay()));
        }
        if (StringUtil.isNullZeroString(paramMdl.getWml010searchDateYearTo())
        && StringUtil.isNullZeroString(paramMdl.getWml010searchDateMonthTo())
        && StringUtil.isNullZeroString(paramMdl.getWml010searchDateDayTo())) {
            paramMdl.setWml010searchDateYearTo(String.valueOf(date.getYear()));
            paramMdl.setWml010searchDateMonthTo(String.valueOf(date.getMonth()));
            paramMdl.setWml010searchDateDayTo(String.valueOf(date.getIntDay()));
        }

        //グループコンボ、デフォルトグループを設定
        GroupBiz grpBiz = new GroupBiz();
        paramMdl.setWml010shainGroup(grpBiz.getDefaultGroupSid(userSid, con));
        paramMdl.setShainGroupCombo(grpBiz.getGroupCombLabelList(con, false, gsMsg));

        //メール本文の最大文字数を設定
        paramMdl.setWml010maxBodySize(WmlBiz.getBodyLimitLength(appRootPath));

        //テーマを設定
        setAccountTheme(con, paramMdl, wacSid);

        //[宛先、添付ファイルの確認]を設定
        setAccountSendConf(con, paramMdl, wacSid);

        //検索条件 送信先 宛先 をデフォルトで選択する
        paramMdl.setWml010searchToKbnTo(Wml010ParamModel.DESTINATION_SELECT);
    }

    /**
     * <br>[機  能] 新着メールの読み込みを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl リクエスト情報
     * @param gsContext GroupSession共通情報
     * @param msgResource MessageResources
     * @param reqMdl リクエスト情報
     * @throws Exception メールの受信に失敗
     */
    public void readNewMail(Connection con, Wml010ParamModel paramMdl, GSContext gsContext,
                            MessageResources msgResource, RequestModel reqMdl)
    throws Exception {

        String domain = reqMdl.getDomain();

        if (!StringUtil.isNullZeroString(paramMdl.getWmlViewAccount())) {
            int accountSid = _getViewAccountSid(paramMdl);
            //受信フォルダを表示
            WmlBiz wmlBiz = new WmlBiz();
            paramMdl.setWml010viewDirectory(
                    wmlBiz.getDirectorySid(con, accountSid, GSConstWebmail.DIR_TYPE_RECEIVE));
            paramMdl.setWml010viewDirectoryType(GSConstWebmail.DIR_TYPE_RECEIVE);
            paramMdl.setWml010sortKey(0);
            paramMdl.setWml010order(0);

            WmlReceiveBatch receiveBatch
                = new WmlReceiveBatch(gsContext, accountSid, msgResource, domain);
            Thread thread = new Thread(receiveBatch);
            receiveBatch.setStatus(WmlReceiveBatch.STATUS_RECEIVE);
            thread.start();
            while (receiveBatch.getStatus() == WmlReceiveBatch.STATUS_RECEIVE) {
                Thread.sleep(1000);
            }
        }
    }

    /**
     * <br>[機  能] フォームパラメータから検索条件Modelを生成する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl リクエスト情報
     * @return 検索条件Model
     * @throws SQLException SQL実行時例外
     */
    public Wml010SearchModel createSearchModel(Connection con, Wml010ParamModel paramMdl)
    throws SQLException {

        Wml010SearchModel searchMdl = new Wml010SearchModel();
        int wacSid = _getViewAccountSid(paramMdl);
        searchMdl.setAccountSid(wacSid);

        WmlDirectoryDao dirDao = new WmlDirectoryDao(con);
        long wdrSid = paramMdl.getWml010viewDirectory();
        if (wdrSid > 0) {
            if (!dirDao.isViewDirecty(wacSid, wdrSid)) {
                wdrSid = dirDao.getReceiveDirSid(wacSid);
                paramMdl.setWml010viewDirectory(wdrSid);
            }
        }

        if (paramMdl.getWml010searchFlg() == 1) {
            searchMdl.setFrom(paramMdl.getWml010svSearchFrom());
            searchMdl.setDestination(paramMdl.getWml010svSearchTo());
            searchMdl.setDestinationTo(paramMdl.getWml010svSearchToKbnTo()
                                                    == Wml010ParamModel.DESTINATION_SELECT);
            searchMdl.setDestinationCc(paramMdl.getWml010svSearchToKbnCc()
                                                    == Wml010ParamModel.DESTINATION_SELECT);
            searchMdl.setDestinationBcc(paramMdl.getWml010svSearchToKbnBcc()
                                                    == Wml010ParamModel.DESTINATION_SELECT);
            searchMdl.setSortKey(paramMdl.getWml010searchSortKey());
            searchMdl.setOrder(paramMdl.getWml010searchOrder());

            if (paramMdl.getWml010searchType() == Wml010Const.SEARCHTYPE_DETAIL) {
                searchMdl.setKeyword(paramMdl.getWml010svSearchKeyword());

                String keywordType
                    = NullDefault.getString(paramMdl.getWml010svSearchKeywordKbn(), "");
                if (keywordType.startsWith("dir:")) {
                    searchMdl.setDirectorySid(Long.parseLong(keywordType.substring(4)));
                } else if (keywordType.startsWith("label:")) {
                    searchMdl.setLabelSid(Integer.parseInt(keywordType.substring(6)));
                }

                if (NullDefault.getString(paramMdl.getWml010svSearchDateType(), "").equals(
                        Wml010Const.SEARCH_DATE_SET)) {

                    searchMdl.setResvDateFrom(
                                __createUDate(paramMdl.getWml010svSearchDateYearFr(),
                                            paramMdl.getWml010svSearchDateMonthFr(),
                                            paramMdl.getWml010svSearchDateDayFr()));
                    searchMdl.setResvDateTo(
                            __createUDate(paramMdl.getWml010svSearchDateYearTo(),
                                        paramMdl.getWml010svSearchDateMonthTo(),
                                        paramMdl.getWml010svSearchDateDayTo()));
                }
                searchMdl.setTempFile(paramMdl.getWml010svSearchTempFile() == 1);
                searchMdl.setReadKbn(paramMdl.getWml010svSearchReadKbn());

            } else {
                searchMdl.setDirectorySid(paramMdl.getWml010viewDirectory());
                searchMdl.setKeyword(paramMdl.getWml010svSearchKeywordNml());
            }
        } else {
            searchMdl.setDirectorySid(paramMdl.getWml010viewDirectory());
            searchMdl.setDirectoryType(dirDao.getDirType(searchMdl.getDirectorySid()));

            searchMdl.setLabelSid(paramMdl.getWml010viewLabel());
            searchMdl.setSortKey(paramMdl.getWml010sortKey());
            searchMdl.setOrder(paramMdl.getWml010order());
        }

        return searchMdl;
    }

    /**
     * <br>[機  能] メール一覧情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl リクエスト情報
     * @param reqMdl リクエスト情報
     * @param res HttpServletResponse
     * @param con DB Connection
     * @param userSid ユーザSID
     * @param appRootPath アプリケーションルートパス
     * @throws Exception 実行時例外
     */
    public void setMailData(Wml010ParamModel paramMdl, RequestModel reqMdl, HttpServletResponse res,
                            Connection con, int userSid, String appRootPath)
    throws Exception {
        StringBuilder strBuild = new StringBuilder("");
        boolean writeSuccess = false;
        GsMessage gsMsg = new GsMessage(reqMdl);
        try {
            int page = paramMdl.getWml010selectPage();
            if (page <= 0) {
                page = 1;
            }
            int start = PageUtil.getRowNumber(page, MAILLIST_MAXCOUNT);

            //表示順の初期設定および更新を行う
            __setMailListSort(con, paramMdl, userSid);

            con.setAutoCommit(true);
            //検索条件およびページの設定を行う
            Wml010SearchModel searchMdl = createSearchModel(con, paramMdl);
            Wml010Dao dao010 = new Wml010Dao(con);
            long maxCount = dao010.getMailCount(searchMdl);
            int maxPageNum = PageUtil.getPageCount(maxCount, MAILLIST_MAXCOUNT);
            int maxPageStartRow = PageUtil.getRowNumber(maxPageNum, MAILLIST_MAXCOUNT);
            if (maxPageStartRow < start) {
                page = maxPageNum;
                start = maxPageStartRow;
            }

            strBuild.append("{");

            //現在のページ、最大ページ、メール本文最大文字数、自動削除設定
            strBuild.append("\"errors\" : \"0\",");
            strBuild.append("\"page\" : " + page + ",");
            strBuild.append("\"maxPage\" : " + maxPageNum + ",");
            if (paramMdl.getWml010searchFlg() == 1) {
                strBuild.append("\"sortKey\" : " + paramMdl.getWml010searchSortKey() + ",");
                strBuild.append("\"order\" : " + paramMdl.getWml010searchOrder() + ",");
            } else {
                strBuild.append("\"sortKey\" : " + paramMdl.getWml010sortKey() + ",");
                strBuild.append("\"order\" : " + paramMdl.getWml010order() + ",");

                //メール本文最大文字数、自動削除設定
                String dirName = "";
                String autoDelDate = "";
                int autoDelKbn = 0;

                WmlAutodeleteDao autoDelDao = new WmlAutodeleteDao(con);
                WmlAutodeleteModel autoDelMdl = autoDelDao.getAutoDelSetUp();
                if (autoDelMdl != null) {
                    WmlDirectoryDao dirDao = new WmlDirectoryDao(con);
                    WmlDirectoryModel dirMdl = dirDao.select(searchMdl.getDirectorySid());
                    if (dirMdl != null) {
                        int autoDelYear = 0;
                        int autoDelMonth = 0;
                        int autoDelDay = 0;

                        switch (dirMdl.getWdrType()) {
                            case GSConstWebmail.DIR_TYPE_RECEIVE:
                                dirName = gsMsg.getMessage("cmn.receive");
                                autoDelKbn = autoDelMdl.getWadResvKbn();
                                autoDelYear = autoDelMdl.getWadResvYear();
                                autoDelMonth = autoDelMdl.getWadResvMonth();
                                autoDelDay = autoDelMdl.getWadResvDay();
                                break;
                            case GSConstWebmail.DIR_TYPE_SENDED:
                                dirName = gsMsg.getMessage("wml.19");
                                autoDelKbn = autoDelMdl.getWadSendKbn();
                                autoDelYear = autoDelMdl.getWadSendYear();
                                autoDelMonth = autoDelMdl.getWadSendMonth();
                                autoDelDay = autoDelMdl.getWadSendDay();
                                break;
                            case GSConstWebmail.DIR_TYPE_DRAFT:
                                dirName = gsMsg.getMessage("cmn.draft");
                                autoDelKbn = autoDelMdl.getWadDraftKbn();
                                autoDelYear = autoDelMdl.getWadDraftYear();
                                autoDelMonth = autoDelMdl.getWadDraftMonth();
                                autoDelDay = autoDelMdl.getWadDraftDay();
                                break;
                            case GSConstWebmail.DIR_TYPE_DUST:
                                dirName = gsMsg.getMessage("cmn.trash");
                                autoDelKbn = autoDelMdl.getWadDustKbn();
                                autoDelYear = autoDelMdl.getWadDustYear();
                                autoDelMonth = autoDelMdl.getWadDustMonth();
                                autoDelDay = autoDelMdl.getWadDustDay();
                                break;
                            case GSConstWebmail.DIR_TYPE_STORAGE:
                                dirName = gsMsg.getMessage("cmn.strage");
                                autoDelKbn = autoDelMdl.getWadKeepKbn();
                                autoDelYear = autoDelMdl.getWadKeepYear();
                                autoDelMonth = autoDelMdl.getWadKeepMonth();
                                autoDelDay = autoDelMdl.getWadKeepDay();
                                break;
                            default:
                        }

                        if (autoDelKbn == GSConstWebmail.WAC_DELKBN_AUTO) {
                            if (autoDelYear > 0) {
                                autoDelDate = gsMsg.getMessage(
                                        "cmn.year", new String[] {String.valueOf(autoDelYear)});
                            }
                            if (autoDelMonth > 0) {
                                autoDelDate += gsMsg.getMessage(
                                        "cmn.months", new String[] {String.valueOf(autoDelMonth)});
                            }
                            if (autoDelDay > 0 || autoDelDate.length() == 0) {
                                autoDelDate += autoDelDay + gsMsg.getMessage("cmn.day");
                            }
                        }
                    }
                }

                strBuild.append("\"dirName\" : \"" + dirName + "\",");
                strBuild.append("\"autoDelKbn\" : " + autoDelKbn + ",");
                strBuild.append("\"autoDelDate\" : \"" + autoDelDate + "\",");
            }

            //アカウントディスク使用量
            int wacSid = searchMdl.getAccountSid();
            WmlBiz wmlBiz = new WmlBiz();
            long accountDiskSize = wmlBiz.getUseDiskSize(con, wacSid);
            BigDecimal useDiskSizeMB = new BigDecimal(accountDiskSize);
            useDiskSizeMB = useDiskSizeMB.divide(new BigDecimal(1024 * 1024), 1,
                                                                    BigDecimal.ROUND_DOWN);
            strBuild.append("\"useDiskSize\" : \"" + useDiskSizeMB + "\",");

            //ディスク容量上限
            int limitDiskSize = -1;
            WmlAdmConfDao wacAdmDao = new WmlAdmConfDao(con);
            WmlAdmConfModel admConfMdl = wacAdmDao.selectAdmData();

            limitDiskSize = wmlBiz.getDiskLimitSize(con, wacSid, admConfMdl);
            strBuild.append("\"limitDiskSize\" : " + limitDiskSize + ",");

            //ディスク使用割合、ディスク容量警告
            BigDecimal useDiskRatio = BigDecimal.ZERO;
            String warnDiskRatio = "0";
            if (limitDiskSize > 0) {
                useDiskRatio = useDiskSizeMB.multiply(new BigDecimal(100));
                useDiskRatio = useDiskRatio.divide(new BigDecimal(limitDiskSize),
                                                            1, BigDecimal.ROUND_DOWN);

                //管理者設定 ディスク容量
                if (admConfMdl.getWadWarnDisk() == GSConstWebmail.WAD_WARN_DISK_YES) {
                    int diskWarnTh = admConfMdl.getWadWarnDiskTh();
                    BigDecimal useDiskSize = new BigDecimal(accountDiskSize);
                    BigDecimal limitSize = new BigDecimal(diskWarnTh * 1024 * 1024);
                    limitSize = limitSize.divide(new BigDecimal(100), 2,
                                                            BigDecimal.ROUND_HALF_UP);
                    limitSize = limitSize.multiply(new BigDecimal(limitDiskSize));
                    if (useDiskSize.compareTo(limitSize) >= 0) {
                        warnDiskRatio = Integer.toString(diskWarnTh);
                    }
                }
            }
            strBuild.append("\"useDiskRatio\" : \"" + useDiskRatio.toString() + "\",");
            strBuild.append("\"warnDiskRatio\" : \"" + warnDiskRatio + "\",");

            //表示アカウント Fromアドレス
            String wacAddress = "";
            WmlAccountDao wacDao = new WmlAccountDao(con);
            WmlAccountModel wacMdl = wacDao.select(wacSid);
            if (wacMdl != null) {
                wacAddress = NullDefault.getString(wacMdl.getWacAddress(), "");
                InternetAddress[] wacAddressList
                        = MailBiz.parseAddress(wacAddress);
                if (wacAddressList != null && wacAddressList.length > 0) {
                    wacAddress = wacAddressList[0].getAddress();
                }
            }
            strBuild.append("\"viewAccountFrom\" : \""
                                    + __URLEncode(_escapeText(wacAddress)) + "\",");

            //メッセージ一覧
            searchMdl.setStart(start);
            searchMdl.setMaxCount(MAILLIST_MAXCOUNT);
            List<Wml010MailModel> mailList
                = dao010.getMailList(searchMdl, WmlBiz.getBodyLimitLength(appRootPath));
            int maxIndex = mailList.size() - 1;
            strBuild.append("\"messages\" : [");

            CommonBiz cmnBiz = new CommonBiz();
            for (int index = 0; index <= maxIndex; index++) {
                Wml010MailModel mailData = mailList.get(index);
                strBuild.append("{");

                strBuild.append("\"XID\":\"").append(mailData.getMailNum());
                strBuild.append("\",");
                strBuild.append("\"dirId\":\"").append(mailData.getDirSid());
                strBuild.append("\",");

//                String body = _escapeText(mailData.getBody(), true, false, true);
                String body = NullDefault.getString(mailData.getBody(), "");
                body = formatBody(body, true);
                if (!StringUtil.isNullZeroString(body)) {
                    body = __URLEncode(body);
                }
                strBuild.append("\"Body\":\"").append(body);
                strBuild.append("\",");
                strBuild.append("\"Date\":\"")
                            .append(WmlBiz.getWmlViewDate(mailData.getDate())).append("\",");

                //To
                StringBuilder sendAddress = new StringBuilder("");
                List<String> addrList = mailData.getSendAddress().getToList();
                StringBuilder listTo = new StringBuilder("");
                for (int addrIndex = 0; addrIndex < addrList.size(); addrIndex++) {
                    if (addrIndex > 0) {
                        sendAddress.append(", ");
                        if (listTo.length() < MAXLEN_LISTTO - 1) {
                            listTo.append(",");
                        }
                    }
//                    sendAddress.append(_escapeText(addrList.get(addrIndex)));
                    sendAddress.append(_escapeTextAddress(addrList.get(addrIndex)));

                    if (listTo.length() < MAXLEN_LISTTO) {
                        String addTo = addrList.get(addrIndex);

                        if (addTo.length() + listTo.length() > MAXLEN_LISTTO) {
                            addTo.subSequence(0, MAXLEN_LISTTO - listTo.length());
                        }

                        listTo.append(addTo);
                    }

                }
                strBuild.append("\"To\":\"").append(sendAddress.toString());
                strBuild.append("\",");

                strBuild.append("\"ListTo\":\"");
                StringTokenizer listToToken = new StringTokenizer(listTo.toString(), ",");
                while (listToToken.hasMoreTokens()) {
//                    strBuild.append(_escapeText(listToToken.nextToken()));
                    strBuild.append(_escapeTextAddress(listToToken.nextToken()));
                    if (listToToken.countTokens() > 0) {
                        strBuild.append(",");
                    }
                }
                strBuild.append("\",");

                //Cc
                sendAddress = new StringBuilder("");
                addrList = mailData.getSendAddress().getCcList();
                for (int addrIndex = 0; addrIndex < addrList.size(); addrIndex++) {
                    if (addrIndex > 0) {
                        sendAddress.append(", ");
                    }

//                    sendAddress.append(_escapeText(addrList.get(addrIndex)));
                    sendAddress.append(_escapeTextAddress(addrList.get(addrIndex)));
                }
                strBuild.append("\"Cc\":\"").append(sendAddress.toString());
                strBuild.append("\",");

                //Bcc
                sendAddress = new StringBuilder("");
                addrList = mailData.getSendAddress().getBccList();
                for (int addrIndex = 0; addrIndex < addrList.size(); addrIndex++) {
                    if (addrIndex > 0) {
                        sendAddress.append(", ");
                    }

//                    sendAddress.append(_escapeText(addrList.get(addrIndex)));
                    sendAddress.append(_escapeTextAddress(addrList.get(addrIndex)));
                }
                strBuild.append("\"Bcc\":\"").append(sendAddress.toString());
                strBuild.append("\",");

                String from = mailData.getFrom();
                if (from != null) {
                    InternetAddress[] address = null;
                    try {
                        address = WmlBiz.parseAddress(from);
                    } catch (Exception e) {
                        address = new InternetAddress[] {new InternetAddress()};
                        address[0].setAddress(from);
                    }

                    if (address != null && address.length > 0) {
                        if (!StringUtil.isNullZeroString(address[0].getPersonal())) {
                            from = address[0].getPersonal();
                        } else if (!StringUtil.isNullZeroString(address[0].getAddress())) {
                            from = address[0].getAddress();
                        }
                    }
                }

                strBuild.append("\"From\":\"").append(__URLEncode(_escapeText(mailData.getFrom())));
                strBuild.append("\",");
                strBuild.append("\"ListFrom\":\"").append(__URLEncode(_escapeText(from)));
                strBuild.append("\",");

                String subject = _escapeText(mailData.getSubject());
                subject = __URLEncode(subject);
                strBuild.append("\"Subject\":\"").append(subject);
                strBuild.append("\",");

                strBuild.append("\"Attach\":").append(mailData.isAttach());
                strBuild.append(",");
                strBuild.append("\"Readed\":").append(mailData.isReaded());
                strBuild.append(",");
                strBuild.append("\"Reply\":").append(mailData.isReply());
                strBuild.append(",");
                strBuild.append("\"Forward\":").append(mailData.isForward());
                strBuild.append(",");
                strBuild.append("\"EditMail\":").append(mailData.isCanEditMail());
                strBuild.append(",");
                strBuild.append("\"SendWait\":").append(mailData.isSendWaitMail());
                strBuild.append(",");

                String sendPlanDate = "";
                if (mailData.isSendWaitMail()) {
                    UDate mailSendDate = mailData.getSendPlanDate();
                    if (mailSendDate != null) {
                        sendPlanDate
                            = gsMsg.getMessage("cmn.view.date", new String[] {
                                Integer.toString(mailSendDate.getYear()),
                                Integer.toString(mailSendDate.getMonth()),
                                Integer.toString(mailSendDate.getIntDay()),
                                Integer.toString(mailSendDate.getIntHour()),
                                Integer.toString(mailSendDate.getIntMinute())
                        });
                    }
                }
                strBuild.append("\"SendPlanDate\":\"").append(sendPlanDate);
                strBuild.append("\",");

                long mailSize = mailData.getMailSize();
                strBuild.append("\"MailSize\":\"").append(mailSize);
                strBuild.append("\",");
                strBuild.append("\"viewMailSize\":\"").append(cmnBiz.convertDiskSize(mailSize));
                strBuild.append("\",");

                //ラベル情報を設定する
                __setJsonLabel(strBuild, mailData);
                strBuild.append(",");

                //添付ファイル情報を設定
                __setJsonTempFile(strBuild, mailData);

                strBuild.append("}");
                if (index < maxIndex) {
                    strBuild.append(",");
                }
            }

            strBuild.append("]");
            strBuild.append("}");

            writeSuccess = true;
        } catch (Exception e) {
            log__.error("メール一覧の取得に失敗", e);
        } finally {

            PrintWriter writer = null;
            try {
                res.setContentType("text/json; charset=UTF-8");
                writer = res.getWriter();
                if (writeSuccess) {
                    writer.write(strBuild.toString());
                } else {
                    writer.write("{\"errors\" : \"1\"}");
                }
                writer.flush();
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }
        }
    }

    /**
     * <br>[機  能] ディレクトリ、ユーザ情報、ラベル情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl リクエスト情報
     * @param reqMdl リクエスト情報
     * @param res HttpServletResponse
     * @param con DB Connection
     * @param userSid セッションユーザSID
     * @param useUserKbn ユーザ情報プラグイン使用可否区分
     * @param useAddressKbn アドレス帳プラグイン使用可否区分
     * @throws Exception 実行時例外
     */
    public void setTreeData(Wml010ParamModel paramMdl, RequestModel reqMdl,
                            HttpServletResponse res, Connection con,
                            int userSid, int useUserKbn, int useAddressKbn)
    throws Exception {
        res.setContentType("text/json; charset=UTF-8");
        PrintWriter writer = null;

        try {
            writer = res.getWriter();
            writer.write("{");

            //ディレクトリ一覧
            int wacSid = NullDefault.getInt(paramMdl.getWmlViewAccount(), 0);
            Wml010Dao dao010 = new Wml010Dao(con);
            List<Wml010DirectoryModel> directoryList
                    = dao010.getDirectoryList(reqMdl, wacSid);
            int maxIndex = directoryList.size() - 1;

            writer.write("\"directory\" : [");
            for (int index = 0; index <= maxIndex; index++) {

                Wml010DirectoryModel directoryData = directoryList.get(index);
                writer.write("{");
                writer.write("\"ID\":\"" + directoryData.getId() + "\",");
                writer.write("\"TYPE\":\"" + directoryData.getType() + "\",");
                writer.write("\"NAME\":\"" + _escapeText(directoryData.getName()) + "\",");
                if (directoryData.getType() == GSConstWebmail.DIR_TYPE_DRAFT
                || directoryData.getType() == GSConstWebmail.DIR_TYPE_NOSEND) {
                    //予約送信 or 草稿の場合、ディレクトリ内のメール件数を設定する
                    WmlMaildataDao mailDataDao = new WmlMaildataDao(con);
                    writer.write("\"NRCNT\":"
                                    + mailDataDao.selectMailCntInDir(directoryData.getId()));
                } else {
                    writer.write("\"NRCNT\":" + directoryData.getNoReadCount());
                }
                writer.write("}");
                if (index < maxIndex) {
                    writer.write(",");
                }
            }
            writer.write("],");

            //ラベル一覧
            List<Wml010LabelModel> labelList = dao010.getLabelList(wacSid);
            int labelMaxIndex = labelList.size() - 1;

            writer.write("\"label\" : [");
            for (int index = 0; index <= labelMaxIndex; index++) {

                Wml010LabelModel labalData = labelList.get(index);
                writer.write("{");
                writer.write("\"ID\":\"" + labalData.getId() + "\",");
                writer.write("\"NAME\":\"" + _escapeText(labalData.getName()) + "\",");
                writer.write("\"NRCNT\":" + labalData.getNoReadCount());
                writer.write("}");
                if (index < labelMaxIndex) {
                    writer.write(",");
                }
            }
            writer.write("]");

            //ユーザ情報
            if (useUserKbn == GSConst.PLUGIN_USE) {
                int grpSid = paramMdl.getWml010shainGroup();
                if (grpSid < 0) {
                    GroupBiz grpBiz = new GroupBiz();
                    grpSid = grpBiz.getDefaultGroupSid(userSid, con);
                }

                List<Wml010AddressModel> addressList
                    = dao010.getShainList(grpSid);

                writer.write(",\"shain\" : [");
                writer.write(__createAddressString(addressList));
                writer.write("]");
            }

            //アドレス帳情報
            if (useAddressKbn == GSConst.PLUGIN_USE) {
                List<Wml010AddressModel> addressList
                    = dao010.getAddressList(userSid,
                            paramMdl.getWml010addressType() == Wml010Form.ADDRESS_TYPE_TANTO);

                writer.write(",\"address\" : [");
                writer.write(__createAddressString(addressList));
                writer.write("]");
            }

            //送信先リスト
            if (useUserKbn == GSConst.PLUGIN_USE || useAddressKbn == GSConst.PLUGIN_USE) {
                WebmailDao webmailDao = new WebmailDao(con);
                List<WmlDestlistModel> destList = webmailDao.getDestList(userSid);

                writer.write(",\"destlist\" : [");
                writer.write(__createDestlistString(destList));
                writer.write("]");
            }

            writer.write("}");
            writer.flush();

        } catch (Exception e) {
            log__.error("ディレクトリ、ラベル情報、ユーザ情報、アドレス帳、送信先リストの取得に失敗", e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

    }

    /**
     * <br>[機  能] ユーザ情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl リクエスト情報
     * @param res HttpServletResponse
     * @param con DB Connection
     * @param userSid ユーザSID
     * @param useUserKbn ユーザ情報プラグイン使用可否区分
     * @throws Exception 実行時例外
     */
    public void setShainTreeData(Wml010ParamModel paramMdl, HttpServletResponse res,
                                Connection con, int userSid, int useUserKbn)
    throws Exception {

        res.setContentType("text/json; charset=UTF-8");
        PrintWriter writer = null;

        try {
            writer = res.getWriter();
            writer.write("{");

            //ユーザ情報
            if (useUserKbn == GSConst.PLUGIN_USE) {
                int grpSid = paramMdl.getWml010shainGroup();
                if (grpSid < 0) {
                    GroupBiz grpBiz = new GroupBiz();
                    grpSid = grpBiz.getDefaultGroupSid(userSid, con);
                }

                Wml010Dao dao010 = new Wml010Dao(con);
                List<Wml010AddressModel> addressList
                    = dao010.getShainList(grpSid);

                writer.write("\"shain\" : [");
                writer.write(__createAddressString(addressList));
                writer.write("]");
            }

            writer.write("}");

            writer.flush();

        } catch (Exception e) {
            log__.error("ユーザ情報の取得に失敗", e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

    }

    /**
     * <br>[機  能] アドレス帳情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl リクエスト情報
     * @param res HttpServletResponse
     * @param con DB Connection
     * @param userSid ユーザSID
     * @param useAddressKbn アドレス帳プラグイン使用可否区分
     * @throws Exception 実行時例外
     */
    public void setAddressTreeData(Wml010ParamModel paramMdl, HttpServletResponse res,
                                    Connection con, int userSid, int useAddressKbn)
    throws Exception {

        res.setContentType("text/json; charset=UTF-8");
        PrintWriter writer = null;

        try {
            writer = res.getWriter();
            writer.write("{");

            //アドレス帳情報
            if (useAddressKbn == GSConst.PLUGIN_USE) {
                Wml010Dao dao010 = new Wml010Dao(con);
                List<Wml010AddressModel> addressList
                    = dao010.getAddressList(userSid,
                                    paramMdl.getWml010addressType()
                                        == Wml010Form.ADDRESS_TYPE_TANTO);

                writer.write("\"address\" : [");
                writer.write(__createAddressString(addressList));
                writer.write("]");
            }

            writer.write("}");
            writer.flush();

        } catch (Exception e) {
            log__.error("アドレス帳の取得に失敗", e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

    }

    /**
     * <br>[機  能] 送信先リスト情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl リクエスト情報
     * @param res HttpServletResponse
     * @param con DB Connection
     * @param userSid ユーザSID
     * @param useUserKbn ユーザ情報プラグイン使用可否区分
     * @param useAddressKbn アドレス帳プラグイン使用可否区分
     * @throws Exception 実行時例外
     */
    public void setDestlistTreeData(Wml010ParamModel paramMdl, HttpServletResponse res,
                                    Connection con, int userSid, int useUserKbn, int useAddressKbn)
    throws Exception {

        res.setContentType("text/json; charset=UTF-8");
        PrintWriter writer = null;

        try {
            writer = res.getWriter();
            writer.write("{");

            //アドレス帳情報
            if (useUserKbn == GSConst.PLUGIN_USE || useAddressKbn == GSConst.PLUGIN_USE) {
                WebmailDao webmailDao = new WebmailDao(con);
                List<WmlDestlistModel> destList = webmailDao.getDestList(userSid);

                writer.write("\"destlist\" : [");
                writer.write(__createDestlistString(destList));
                writer.write("]");
            }

            writer.write("}");
            writer.flush();

        } catch (Exception e) {
            log__.error("送信先リストの取得に失敗", e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

    }

    /**
     * <br>[機  能] 送信先リスト メールアドレスを設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl リクエスト情報
     * @param res HttpServletResponse
     * @param con DB Connection
     * @param userSid ユーザSID
     * @param useUserKbn ユーザ情報プラグイン使用可否区分
     * @param useAddressKbn アドレス帳プラグイン使用可否区分
     * @throws Exception 実行時例外
     */
    public void setDestlistAddress(Wml010ParamModel paramMdl, HttpServletResponse res,
                                    Connection con, int userSid, int useUserKbn, int useAddressKbn)
    throws Exception {

        res.setContentType("text/json; charset=UTF-8");
        PrintWriter writer = null;

        try {
            writer = res.getWriter();
            writer.write("{");

            List<Wml010AddressModel> addressList = new ArrayList<Wml010AddressModel>();
            WmlDestlistAddressDao destAddressDao = new WmlDestlistAddressDao(con);
            List<WmlDestlistAddressModel> destAddressList = null;

            //ユーザ情報
            if (useUserKbn == GSConst.PLUGIN_USE) {
                CmnUsrmInfDao userInfDao = new CmnUsrmInfDao(con);
                List<CmnUsrmInfModel> userDataList = null;

                destAddressList
                    = destAddressDao.getDestlistAddress(paramMdl.getWml010destlistId(),
                                                                GSConstWebmail.WDA_TYPE_USER);
                for (WmlDestlistAddressModel destAddressMdl : destAddressList) {
                    userDataList = userInfDao.getUsersDataList(
                            new String[] {Integer.toString(destAddressMdl.getWdaSid())});
                    if (!userDataList.isEmpty()) {
                        Wml010AddressModel addressData = new Wml010AddressModel();
                        CmnUsrmInfModel userData = userDataList.get(0);
                        addressData.setUserName(userData.getUsiSei() + " " + userData.getUsiMei());
                        switch (destAddressMdl.getWdaAdrno()) {
                            case 1:
                                addressData.setMail1(userData.getUsiMail1());
                                break;
                            case 2:
                                addressData.setMail1(userData.getUsiMail2());
                                break;
                            case 3:
                                addressData.setMail1(userData.getUsiMail3());
                                break;
                            default:
                        }

                        if (!StringUtil.isNullZeroString(addressData.getMail1())) {
                            addressList.add(addressData);
                        }
                    }
                }
            }

            //アドレス帳情報
            if (useAddressKbn == GSConst.PLUGIN_USE) {
                destAddressList
                    = destAddressDao.getDestlistAddress(paramMdl.getWml010destlistId(),
                                                                GSConstWebmail.WDA_TYPE_ADDRESS);
                Wml280Dao dao280 = new Wml280Dao(con);
                for (WmlDestlistAddressModel destAddressMdl : destAddressList) {
                    Wml010AddressModel addressData = new Wml010AddressModel();
                    Wml280AddressBookModel addressBookData
                        = dao280.getAddressBookData(destAddressMdl.getWdaSid());
                    if (addressBookData != null) {
                        addressData.setUserName(addressBookData.getAdrSei()
                                                        + " " + addressBookData.getAdrMei());

                        switch (destAddressMdl.getWdaAdrno()) {
                            case 1:
                                addressData.setMail1(addressBookData.getAdrMail1());
                                break;
                            case 2:
                                addressData.setMail1(addressBookData.getAdrMail2());
                                break;
                            case 3:
                                addressData.setMail1(addressBookData.getAdrMail3());
                                break;
                            default:
                        }

                        if (!StringUtil.isNullZeroString(addressData.getMail1())) {
                            addressList.add(addressData);
                        }
                    }
                }
            }

            writer.write("\"destlistAddress\" : [");

            StringBuilder sb = new StringBuilder("");

            for (Wml010AddressModel addressData : addressList) {
                if (!StringUtil.isNullZeroString(addressData.getMail1())) {
                    if (sb.length() > 0) {
                        sb.append(",");
                    }

                    sb.append("{");
                    sb.append("\"NAME\":\"" + _escapeText(addressData.getUserName()) + "\",");
                    sb.append("\"NAMEPARAM\":\""
                                + _escapeText(addressData.getUserName(), false, true, true)
                                + "\",");
                    sb.append("\"MAIL\":\"" + _escapeText(addressData.getMail1()) + "\"");
                    sb.append("}");
                }
            }

            writer.write(sb.toString());
            writer.write("]");
            writer.write("}");
            writer.flush();

        } catch (Exception e) {
            log__.error("送信先リストの取得に失敗", e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

    }

    /**
     * <br>[機  能] 指定した年月日からUDateのインスタンスを生成する
     * <br>[解  説]
     * <br>[備  考]
     * @param year 年
     * @param month 月
     * @param day 日
     * @return UDate
     */
    private UDate __createUDate(String year, String month, String day) {
        UDate date = null;
        if (!StringUtil.isNullZeroString(year)
        && !StringUtil.isNullZeroString(month)
        && !StringUtil.isNullZeroString(day)) {

            date = new UDate();
            date.setYear(Integer.parseInt(year));
            date.setMonth(Integer.parseInt(month));
            date.setDay(Integer.parseInt(day));
        }

        return date;
    }

    /**
     * <br>[機  能] JSON形式で使用できる文字列へ変換する
     * <br>[解  説]
     * <br>[備  考]
     * @param text 文字列
     * @return 文字列
     */
    protected static String _escapeText(String text) {
        return _escapeText(text, true, true, true);
    }

    /**
     * <br>[機  能] JSON形式で使用できる文字列へ変換する
     * <br>[解  説] 画面に表示する文字列を変換するために使用する
     * <br>[備  考]
     * @param text 文字列
     * @return 文字列
     */
    protected static String _escapeTextView(String text) {
        return _escapeText(text, true, true, true, true);
    }

    /**
     * <br>[機  能] JSON形式で使用できる文字列へ変換する
     * <br>[解  説]
     * <br>[備  考]
     * @param text 文字列
     * @return 文字列
     */
    protected static String _escapeTextInput(String text) {
        return _escapeText(text, false, true, true);
    }

    /**
     * <br>[機  能] JSON形式で使用できる文字列へ変換する
     * <br>[解  説] テキストエリアに設定する文字列を対象とする
     * <br>[備  考]
     * @param text 文字列
     * @return 文字列
     */
    protected static String _escapeTextAreaInput(String text) {
        return _escapeText(text, false, true, false);
    }

    /**
     * <br>[機  能] JSON形式で使用できる文字列へ変換する
     * <br>[解  説] テキストボックスに設定する文字列を対象とする
     * <br>[備  考]
     * @param text 文字列
     * @return 文字列
     */
    protected static String _escapeTextBox(String text) {
        String mailText = NullDefault.getString(text, "");
        mailText = StringUtilHtml.transToHTmlForTextArea(mailText);
        return _escapeTextInput(mailText);
    }

    /**
     * <br>[機  能] JSON形式で使用できる文字列へ変換する
     * <br>[解  説]
     * <br>[備  考]
     * @param text 文字列
     * @return 文字列
     */
    protected static String _escapeTextAddress(String text) {
        return _escapeText(text, true, true, true, true, true);
    }

    /**
     * <br>[機  能] JSON形式で使用できる文字列へ変換する
     * <br>[解  説]
     * <br>[備  考] '\b','\t','\f','\r' についてはサーバ側のJavascriptにてエスケープを行うので
     * <br>         エスケープの対象とはしない。
     * @param text 文字列
     * @param htmlEncode true:HTMLエスケープを行う false:HTMLエスケープを行わない
     * @param input 入力項目(textbox or textarea)か
     * @param newline '"'、改行文字のエスケープ true:する false:しない
     * @return 文字列
     */
    protected static String _escapeText(String text, boolean htmlEncode, boolean input,
                                        boolean newline) {
        return _escapeText(text, htmlEncode, input, newline, false);
    }

    /**
     * <br>[機  能] JSON形式で使用できる文字列へ変換する
     * <br>[解  説]
     * <br>[備  考] '\b','\t','\f','\r' についてはサーバ側のJavascriptにてエスケープを行うので
     * <br>         エスケープの対象とはしない。
     * @param text 文字列
     * @param htmlEncode true:HTMLエスケープを行う false:HTMLエスケープを行わない
     * @param input 入力項目(textbox or textarea)か
     * @param newline '"'、改行文字のエスケープ true:する false:しない
     * @param htmlAmparsant true:文字実体参照のHTMLエスケープを行う false:文字実体参照のHTMLエスケープを行わない
     * @return 文字列
     */
    protected static String _escapeText(String text, boolean htmlEncode, boolean input,
                                        boolean newline, boolean htmlAmparsant) {
        return _escapeText(text, htmlEncode, input, newline, htmlAmparsant, false);
    }

    /**
     * <br>[機  能] JSON形式で使用できる文字列へ変換する
     * <br>[解  説]
     * <br>[備  考]
     * @param text 文字列
     * @param htmlEncode true:HTMLエスケープを行う false:HTMLエスケープを行わない
     * @param input 入力項目(textbox or textarea)か
     * @param newline '"'、改行文字のエスケープ true:する false:しない
     * @param htmlAmparsant true:文字実体参照のHTMLエスケープを行う false:文字実体参照のHTMLエスケープを行わない
     * @param jsonParse '\b','\t','\f','\r'文字列のエスケープ
     * @return 文字列
     */
    protected static String _escapeText(String text, boolean htmlEncode, boolean input,
                                        boolean newline, boolean htmlAmparsant, boolean jsonParse) {
        String mailText = NullDefault.getString(text, "");
        if (htmlEncode) {
            if (htmlAmparsant) {
                mailText = StringUtilHtml.transToHTmlPlusAmparsant(mailText);
            } else {
                mailText = StringUtilHtml.transToHTml(mailText);
            }
        }
        if (input && newline) {
            mailText = StringUtilHtml.replaceString(mailText, "\\", "\\\\");
        }

        mailText = StringUtilHtml.replaceString(mailText, "\r\n", "\n");

        if (newline) {
            mailText = StringUtilHtml.replaceString(mailText, "\"", "\\\"");
            mailText = StringUtilHtml.replaceString(mailText, "\n", "\\n");
        }

        if (jsonParse) {
            mailText = StringUtilHtml.replaceString(mailText, "\b", "\\b");
            mailText = StringUtilHtml.replaceString(mailText, "/", "\\/");
            mailText = StringUtilHtml.replaceString(mailText, "\r", "\\r");
            mailText = StringUtilHtml.replaceString(mailText, "\t", "\\t");

            //ESCを除去
            while (mailText.indexOf('\u001b') >= 0) {
                mailText  = mailText.replace('\u001b', '\u0020');
            }
        }
        return mailText;
    }

    /**
     * <br>[機  能] ラベル情報をJSON形式に変換する
     * <br>[解  説]
     * <br>[備  考]
     * @param strBuild StringBuilder
     * @param mailData メール情報
     */
    private void __setJsonLabel(StringBuilder strBuild, Wml010MailModel mailData) {

        //ラベル情報を設定
        StringBuilder label = new StringBuilder("\"Label\":\"");
        StringBuilder labelId = new StringBuilder("\"LabelId\":[");
        StringBuilder labelName = new StringBuilder("\"LabelName\":[");
        List<Wml010LabelModel> labelList = mailData.getLabelList();
        for (int index = 0; index < labelList.size(); index++) {
            if (index > 0) {
                label.append(",");
                labelId.append(",");
                labelName.append(",");
            }

            Wml010LabelModel labelData = labelList.get(index);
            label.append(_escapeTextView(labelData.getName()));
            labelId.append(labelData.getId());
            labelName.append("\"").append(_escapeText(labelData.getName()));
            labelName.append("\"");
        }

        strBuild.append(label.toString()).append("\"");
        strBuild.append(",");
        strBuild.append(labelId.toString()).append("]");
        strBuild.append(",");
        strBuild.append(labelName.toString()).append("]");
    }

    /**
     * <br>[機  能] メールの削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl リクエスト情報
     * @param userSid ユーザSID
     * @param reqMdl リクエスト情報
     * @return 結果メッセージ
     * @throws SQLException SQL実行時例外
     */
    public String dustMail(Connection con, Wml010ParamModel paramMdl,
                        int userSid, RequestModel reqMdl)
    throws SQLException {
        GsMessage gsMsg = new GsMessage(reqMdl);
        long[] messageNum = getSelectMessageNum(paramMdl);
        if (messageNum == null || messageNum.length == 0) {
            return gsMsg.getMessage("wml.plz.select.mail");
        }
        int[] messageDirId = getSelectMessageDirId(paramMdl);
        if (messageDirId == null || messageDirId.length != messageNum.length) {
            return gsMsg.getMessage("wml.plz.select.mail");
        }

        String message = gsMsg.getMessage("wml.failed.deletemail");
        try {
            String accountSid = paramMdl.getWmlViewAccount();
            if (StringUtil.isNullZeroString(accountSid)
            || !ValidateUtil.isNumber(accountSid)) {
                return gsMsg.getMessage("wml.191");
            }

            //アカウントのゴミ箱ディレクトリを取得する
            WebmailDao webmailDao = new WebmailDao(con);
            long wdrSid = webmailDao.getWdrSid(Integer.parseInt(accountSid),
                                            GSConstWebmail.DIR_TYPE_DUST);
            if (wdrSid <= 0) {
                return gsMsg.getMessage("wml.191");
            }

            WmlDirectoryDao directoryDao = new WmlDirectoryDao(con);
            List<Long> dustMailNumList = new ArrayList<Long>();
            List<Long> deleteMailNumList = new ArrayList<Long>();
            for (int idx = 0; idx < messageDirId.length; idx++) {
                if (directoryDao.getDirType(messageDirId[idx]) == GSConstWebmail.DIR_TYPE_DUST) {
                    deleteMailNumList.add(messageNum[idx]);
                } else {
                    dustMailNumList.add(messageNum[idx]);
                }
            }

            if (deleteMailNumList.isEmpty()) {
                //ゴミ箱へ移動する
                webmailDao.moveMail(messageNum, wdrSid);

            } else {
                //ゴミ箱ディレクトリのメールは物理削除する
                long[] deleteMailNumArray = new long[deleteMailNumList.size()];
                for (int delIdx = 0; delIdx < deleteMailNumList.size(); delIdx++) {
                    deleteMailNumArray[delIdx] = deleteMailNumList.get(delIdx);
                }
                __deleteMailData(con, accountSid, deleteMailNumArray, userSid);

                long[] dustMailNum = new long[messageNum.length - deleteMailNumList.size()];
                if (dustMailNum.length > 0) {

                    int dustIdx = 0;
                    for (long mailNum : messageNum) {
                        if (deleteMailNumList.indexOf(mailNum) < 0) {
                            dustMailNum[dustIdx++] = mailNum;
                        }
                    }

                    //ゴミ箱へ移動する
                    webmailDao.moveMail(dustMailNum, wdrSid);
                }
            }

            message = "success";
        } catch (Exception e) {
            log__.error("メールの削除に失敗しました。", e);
        }

        return _escapeText(message);
    }

    /**
     * <br>[機  能] 「ゴミ箱」ディレクトリ内のメール情報を削除する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl リクエスト情報
     * @param userSid ユーザSID
     * @param reqMdl リクエスト情報
     * @return 結果メッセージ
     */
    public String emptyTrash(Connection con, Wml010ParamModel paramMdl, int userSid,
                            RequestModel reqMdl) {
        GsMessage gsMsg = new GsMessage(reqMdl);
        int wacSid = _getViewAccountSid(paramMdl);
        if (wacSid <= 0) {
            return gsMsg.getMessage("wml.131");
        }

        WmlMailDeleteModel delMdl = new WmlMailDeleteModel();
        delMdl.setManuDelDir(GSConstWebmail.DIR_TYPE_DUST);
        delMdl.setWacSid(wacSid);
        delMdl.setUseDate(false);

        try {
            WmlBiz biz = new WmlBiz();
            biz.deleteMailData(con, delMdl, userSid);

        } catch (Throwable e) {
            log__.error("「ゴミ箱を空にする」処理に失敗しました。: アカウントSID = " + wacSid, e);
            return gsMsg.getMessage("wml.failed.deletemail");
        }

        return "success";
    }

    /**
     * <br>[機  能] メッセージにラベルを付加する
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param reqMdl リクエスト情報
     * @param res レスポンス
     * @param con コネクション
     * @param paramMdl リクエスト情報
     * @param mtCon 採番コントローラ
     * @param sessionUserSid セッションユーザSID
     * @return 結果メッセージ
     * @throws SQLException SQL実行時例外
     */
    public String setLabelForMessage(ActionMapping map,
                                    RequestModel reqMdl,
                                    HttpServletResponse res,
                                    Connection con, Wml010ParamModel paramMdl,
                                    MlCountMtController mtCon, int sessionUserSid)
    throws SQLException {

        GsMessage gsMsg = new GsMessage(reqMdl);
        String message = null;
        String labelValue = null;
        int type = paramMdl.getWml010addLabelType();

        int wacSid = _getViewAccountSid(paramMdl);
        if (wacSid <= 0) {
            return __createResultMessage(gsMsg.getMessage("wml.131"));
        }

        try {
            long[] messageNum = getSelectMessageNum(paramMdl);

            if (messageNum == null || messageNum.length == 0) {
                return __createResultMessage(gsMsg.getMessage("wml.plz.select.mail"));
            }

            int labelSid = paramMdl.getWml010addLabel();
            if (type == Wml010Const.ADDLABEL_NEW) {
                String labelName = paramMdl.getWml010addLabelName();

                if (StringUtil.isNullZeroString(labelName)) {
                    return __createResultMessage(gsMsg.getMessage("wml.171"));
                }

                //JIS第2水準チェック
                if (!GSValidateUtil.isGsJapaneaseString(labelName)) {
                    //利用不可能な文字を入力した場合
                    String nstr = GSValidateUtil.getNotGsJapaneaseString(labelName);
                    return __createResultMessage(gsMsg.getMessage("wml.168")
                            + gsMsg.getMessage("wml.118")
                            + gsMsg.getMessage("wml.213") + nstr);
                }

                if (labelName.length() > GSConstWebmail.MAXLEN_SEARCH_KEYWORD) {
                    //MAX桁チェック
                    return __createResultMessage(
                            gsMsg.getMessage("wml.170",
                                            new String[] {String.valueOf(
                                                        GSConstWebmail.MAXLEN_SEARCH_KEYWORD)}));
                }

                if (ValidateUtil.isSpace(labelName)) {
                    //スペースのみ
                    return __createResultMessage(gsMsg.getMessage("wml.167"));
                }

                if (ValidateUtil.isSpaceStart(labelName)) {
                    //先頭スペース
                    return __createResultMessage(gsMsg.getMessage("wml.169"));
                }

                if (ValidateUtil.isTab(labelName)) {
                    //タブ文字チェック
                    String[] msgLabelName = new String[] {gsMsg.getMessage("cmn.label.name")};
                    return __createResultMessage(
                                    gsMsg.getMessage("wml.261", msgLabelName));
                }

                WmlLabelDao labelDao = new WmlLabelDao(con);
                boolean commit = false;
                try {
                    labelSid = (int) mtCon.getSaibanNumber(GSConstWebmail.SBNSID_WEBMAIL,
                                                        GSConstWebmail.SBNSID_SUB_LABEL,
                                                        sessionUserSid);

                    int viewWacSid = _getViewAccountSid(paramMdl);
                    WmlLabelModel labelMdl = new WmlLabelModel();
                    labelMdl.setWlbSid(labelSid);
                    labelMdl.setUsrSid(sessionUserSid);
                    labelMdl.setWlbName(labelName);
                    labelMdl.setWlbType(GSConstWebmail.LABELTYPE_ONES);
                    labelMdl.setWlbOrder(labelDao.maxSortNumber(viewWacSid) + 1);
                    labelMdl.setWacSid(viewWacSid);

                    labelValue = "{\"id\":" + labelSid;
                    labelValue += ",\"name\":\"" + _escapeText(labelName);
                    labelValue += "\"}";

                    labelDao.insert(labelMdl);
                    con.commit();
                    commit = true;

                } catch (Exception e) {
                    log__.error("ラベルの登録に失敗", e);
                    return __createResultMessage(gsMsg.getMessage("wml.161"));
                } finally {
                    if (!commit) {
                        con.rollback();
                    }
                }

                //ログ出力
                WmlBiz wmlBiz = new WmlBiz();
                wmlBiz.outPutLog(map, reqMdl, con,
                                 gsMsg.getMessage("cmn.entry"), GSConstLog.LEVEL_INFO,
                                "[name]" + labelName);

            } else {
                if (labelSid <= 0) {
                    return __createResultMessage(gsMsg.getMessage("cmn.select.a.label"));
                }

                WebmailDao webmailDao = new WebmailDao(con);
                if (!webmailDao.existLabel(labelSid)) {
                    return __createResultMessage(gsMsg.getMessage("wml.192"));
                }
            }

            WmlLabelRelationModel wlbRelationModel = new WmlLabelRelationModel();
            WmlLabelRelationDao wlbRelationDao = new WmlLabelRelationDao(con);

            for (long msgNum : messageNum) {
                wlbRelationModel.setWlbSid(labelSid);
                wlbRelationModel.setWmdMailnum(msgNum);
                wlbRelationModel.setWacSid(wacSid);

                if (wlbRelationDao.select(msgNum, labelSid) == null) {
                    wlbRelationDao.insert(wlbRelationModel);
                }
            }
            message = "success";

        } catch (Exception e) {
            log__.error("メールへのラベル追加に失敗しました。", e);
        } finally {
            if (message == null) {
                message = gsMsg.getMessage("wml.failed.addlabel");
            }

            message = "{\"message\":\"" +  _escapeText(message) + "\"";
            if (labelValue != null) {
                message += ",\"addLabelValue\":" + labelValue;
            }
            message += "}";
        }

        return message;
    }

    /**
     * <br>[機  能] メッセージからラベルを削除する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl リクエスト情報
     * @param reqMdl リクエスト情報
     * @return 結果メッセージ
     * @throws SQLException SQL実行時例外
     */
    public String deleteLabelForMessage(Connection con, Wml010ParamModel paramMdl,
                                        RequestModel reqMdl) throws SQLException {
        GsMessage gsMsg = new GsMessage(reqMdl);
        String message = null;
        try {
            long[] messageNum = getSelectMessageNum(paramMdl);
            int labelSid = paramMdl.getWml010delLabel();

            if (messageNum == null || messageNum.length == 0) {
                return __createResultMessage(gsMsg.getMessage("wml.plz.select.mail"));

            } else if (labelSid <= 0) {
                return __createResultMessage(gsMsg.getMessage("cmn.select.a.label"));
            }

            WebmailDao webmailDao = new WebmailDao(con);
            if (!webmailDao.existLabel(labelSid)) {
                return __createResultMessage(gsMsg.getMessage("wml.192"));
            }

            WmlLabelRelationDao wlbRelationDao = new WmlLabelRelationDao(con);
            for (long msgNum : messageNum) {
                wlbRelationDao.delete(msgNum, labelSid);
            }
            message = "success";

        } catch (Exception e) {
            log__.error("メールのラベル削除に失敗しました。", e);
        } finally {
            if (message == null) {
                message = gsMsg.getMessage("failed.deletelabel");
            }
            message = __createResultMessage(message);
        }

        return message;
    }

    /**
     * <br>[機  能] ラベル追加/削除、メールの移動 の結果メッセージを生成する
     * <br>[解  説]
     * <br>[備  考]
     * @param message メッセージ
     * @return 結果メッセージ
     */
    private String __createResultMessage(String message) {
        return "{\"message\":\"" +  _escapeText(message) + "\"}";
    }

    /**
     * <br>[機  能] メールの保管(保管ディレクトリへ移動)を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl リクエスト情報
     * @param reqMdl リクエスト情報
     * @return 結果メッセージ
     * @throws SQLException SQL実行時例外
     */
    public String keepMail(Connection con, Wml010ParamModel paramMdl, RequestModel reqMdl)
    throws SQLException {
        GsMessage gsMsg = new GsMessage(reqMdl);
        long[] messageNum = getSelectMessageNum(paramMdl);
        if (messageNum == null || messageNum.length == 0) {
            return gsMsg.getMessage("wml.plz.select.mail");
        }

        String message = gsMsg.getMessage("wml.failed.storemail");
        try {
            String accountSid = paramMdl.getWmlViewAccount();
            if (StringUtil.isNullZeroString(accountSid)
            || !ValidateUtil.isNumber(accountSid)) {
                return gsMsg.getMessage("wml.191");
            }

            //アカウントの保管ディレクトリを取得する
            WebmailDao webmailDao = new WebmailDao(con);
            long wdrSid = webmailDao.getWdrSid(Integer.parseInt(accountSid),
                                            GSConstWebmail.DIR_TYPE_STORAGE);
            if (wdrSid <= 0) {
                return gsMsg.getMessage("wml.209");
            }

            webmailDao.moveMail(messageNum, wdrSid);
            message = "success";
        } catch (Exception e) {
            log__.error("メールの保管に失敗しました。", e);
        }

        return _escapeText(message);
    }

    /**
     * <br>[機  能] 送信メール情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl リクエスト情報
     * @param reqMdl リクエスト情報
     * @param res HttpServletResponse
     * @param con DB Connection
     * @param type 送信種別
     * @param userSid ユーザSID
     * @param appRootPath アプリケーションルートパス
     * @param tempRootDir テンポラリディレクトリ
     * @throws Exception 実行時例外
     */
    public void setSendMailData(Wml010ParamModel paramMdl, RequestModel reqMdl,
                                HttpServletResponse res, Connection con,
                                int type, int userSid, String appRootPath,
                                String tempRootDir)
    throws Exception {
        res.setContentType("text/json; charset=UTF-8");
        PrintWriter writer = null;

        try {
            WmlAccountDao accountDao = new WmlAccountDao(con);

            writer = res.getWriter();
            writer.write("{");

            //送信メール形式
            int sendFormat = GSConstWebmail.ACNT_SENDFORMAT_NOSET;
            int sendFormatChange = 1;
            int accountSid = _getViewAccountSid(paramMdl);
            WmlAdmConfDao admConfDao = new WmlAdmConfDao(con);
            WmlAdmConfModel admConfMdl = admConfDao.selectAdmData();
            WmlAccountModel accountData = accountDao.select(accountSid);
            if (admConfMdl != null
            && admConfMdl.getWadAcctSendformat() != GSConstWebmail.ACNT_SENDFORMAT_NOSET) {
                sendFormat = admConfMdl.getWadAcctSendformat();
                sendFormatChange = 0;
            } else {
                if (accountData.getWacSendMailtype() == GSConstWebmail.WAC_SEND_MAILTYPE_HTML) {
                    sendFormat = GSConstWebmail.ACNT_SENDFORMAT_HTML;
                }
            }

            writer.write("\"sendFormat\" : " + sendFormat + ",");
            writer.write("\"sendFormatChange\" : " + sendFormatChange + ",");

            //アカウント情報
            writer.write("\"sendAccount\" : [");
            List<WmlAccountModel> accountList = accountDao.getAccountList(userSid);
            for (int index = 0; index < accountList.size(); index++) {
                WmlAccountModel accountModel = accountList.get(index);
                if (index > 0) {
                    writer.write(",");
                }
                writer.write("{");
                writer.write("\"ID\" : " + accountModel.getWacSid() + ",");
                writer.write("\"NAME\" : \""
//                            + _escapeTextInput(accountModel.getWacAddress())
                            + _escapeText(accountModel.getWacAddress(),
                                                false, true, true, false, true)
                            + "　　"
//                            + _escapeTextInput(
//                                    StringUtilHtml.transToHTml(accountModel.getWacName()))
                            + _escapeText(StringUtilHtml.transToHTml(accountModel.getWacName()),
                                                false, true, true, false, true)
                            + "\"");
                writer.write("}");
            }
            writer.write("],");

            //アカウント 署名
            writer.write("\"sendSign\" : [");
            writer.write(createAccountSignText(con, accountSid));
            writer.write("],");

            //アカウント メールテンプレート
            writer.write("\"mailTemplate\" : [");
            writer.write(createMailTemplateText(con, accountSid));
            writer.write("],");

            long time = 0;
            List<Wml010MailModel> mailList = null;
            if (paramMdl.getWml010sendMessageNum() > 0) {
                Wml010SearchModel searchMdl = new Wml010SearchModel();
                searchMdl.setAccountSid(accountSid);
                searchMdl.setMailNum(paramMdl.getWml010sendMessageNum());
                searchMdl.setStart(1);
                searchMdl.setMaxCount(1);
                //転送 or 返信元メールを取得
                time = System.currentTimeMillis();
                Wml010Dao dao010 = new Wml010Dao(con);
                mailList = dao010.getMailList(searchMdl, WmlBiz.getBodyLimitLength(appRootPath));
                log__.debug("転送 or 返信元メールの取得時間(1)" + (System.currentTimeMillis() - time));
            }

            time = System.currentTimeMillis();
            log__.debug("転送時のアカウント情報取得時間" + (System.currentTimeMillis() - time));

            time = System.currentTimeMillis();
            Wml010SendMailModel sendMailData = getSendMailData(con, accountData, type, mailList,
                                                            reqMdl, appRootPath, tempRootDir,
                                                            true);
            log__.debug("転送 or 返信元メールの取得時間(2)" + (System.currentTimeMillis() - time));
            log__.debug("送信データ宛先！！！＝" + sendMailData.getTo());

            //TODO
//            writer.write("\"to\" : \""
//                    + sendMailData.getTo().replaceAll("\n", "").replaceAll("\t", " ") + "\",");
//            writer.write("\"cc\" : \""
//                    + sendMailData.getCc().replaceAll("\n", "").replaceAll("\t", " ") + "\",");
//            writer.write("\"bcc\" : \""
//                    + sendMailData.getBcc().replaceAll("\n", "").replaceAll("\t", " ") + "\",");
            writer.write("\"to\" : \"" + __formatAddress(sendMailData.getTo()) + "\",");
            writer.write("\"cc\" : \"" + __formatAddress(sendMailData.getCc()) + "\",");
            writer.write("\"bcc\" : \"" + __formatAddress(sendMailData.getBcc()) + "\",");

            writer.write("\"subject\" : \"" + __URLEncode(sendMailData.getSubject()) + "\",");
            writer.write("\"content\" : \"" + __URLEncode(sendMailData.getContent()) + "\",");
            writer.write("\"fileList\" : [" + sendMailData.getFileList() + "]");

            writer.write(",\"timeSent\" : \"" + sendMailData.isTimeSent() + "\"");
            UDate sendPlanDate = null;
            if (sendMailData.isTimeSent() && sendMailData.getSendPlanDate() != null) {
                sendPlanDate = sendMailData.getSendPlanDate();
            } else {
                sendPlanDate = new UDate();
                sendPlanDate.addHour(1);
            }
            writer.write(",\"timeSentYear\" : \"" + sendPlanDate.getYear() + "\"");
            writer.write(",\"timeSentMonth\" : \"" + sendPlanDate.getMonth() + "\"");
            writer.write(",\"timeSentDay\" : \"" + sendPlanDate.getIntDay() + "\"");
            writer.write(",\"timeSentHour\" : \"" + sendPlanDate.getIntHour() + "\"");

            //5分単位
            BigDecimal sendPlanMinute = new BigDecimal(sendPlanDate.getIntMinute());
            sendPlanMinute = sendPlanMinute.subtract(sendPlanMinute.remainder(new BigDecimal(5)));
            sendPlanMinute = sendPlanMinute.setScale(0);
            writer.write(",\"timeSentMinute\" : \"" + sendPlanMinute.toString() + "\"");

            boolean admConfFlg
                = (admConfMdl != null
                    && admConfMdl.getWadPermitKbn() == GSConstWebmail.PERMIT_ADMIN);

            //予約送信 デフォルト
            int timeSentType = 0;
            int timeSentDef = 0;
            if (admConfFlg) {
                if (admConfMdl.getWadTimesent() == GSConstWebmail.WAC_TIMESENT_INPUT) {
                    timeSentType = 1;
                    timeSentDef = admConfMdl.getWadTimesentDef();
                }
            } else {
                if (accountData.getWacTimesent() == GSConstWebmail.WAC_TIMESENT_INPUT) {
                    timeSentType = 1;
                    timeSentDef = accountData.getWacTimesentDef();
                }
            }
            writer.write(",\"timeSentType\" : \"" + timeSentType + "\"");
            writer.write(",\"timeSentDef\" : \"" + timeSentDef + "\"");

            //ファイル圧縮 デフォルト
            int compressFileType = 0;
            int compressFileDef = 0;
            if (admConfFlg) {
                if (admConfMdl.getWadCompressFile() == GSConstWebmail.WAD_COMPRESS_FILE_INPUT) {
                    compressFileType = 1;
                    compressFileDef = admConfMdl.getWadCompressFileDef();
                }
            } else {
                if (accountData.getWacCompressFile() == GSConstWebmail.WAD_COMPRESS_FILE_INPUT) {
                    compressFileType = 1;
                    compressFileDef = accountData.getWacCompressFileDef();
                }
            }
            writer.write(",\"compressFileType\" : \"" + compressFileType + "\"");
            writer.write(",\"compressFileDef\" : \"" + compressFileDef + "\"");

            //ファイル圧縮(予約送信)
            writer.write(",\"compressFilePlan\" : \"" + sendMailData.getCompressFileType() + "\"");

            writer.write("}");
            writer.flush();

        } catch (Exception e) {
            log__.error("送信メール情報の取得に失敗", e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * <br>[機  能] 返信 or 転送時、本文に表示するメールアドレスのフォーマット
     * <br>[解  説]
     * <br>[備  考]
     * @param address メールアドレス
     * @return フォーマット後のメールアドレス
     */
    public String __formatAddress(String address) {
        if (StringUtil.isNullZeroString(address)) {
            return address;
        }
        String formatAddress = address.replaceAll("\n", "").replaceAll("\t", " ");

        //ESCを除去
        while (formatAddress.indexOf('\u001b') >= 0) {
            formatAddress  = formatAddress.replace('\u001b', '\u0020');
        }
        return formatAddress;
    }

    /**
     * <br>[機  能] 返信時の宛先を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param mailData メール情報
     * @return 宛先
     * @throws SQLException SQL実行時例外
     * @throws ParseException Reply-Toのparseに失敗
     */
    private String __getReplyToAdderss(Connection con, Wml010MailModel mailData)
    throws SQLException, ParseException {
        String to = "";

        try {
            Wml010Dao dao010 = new Wml010Dao(con);
            List<String> replyList = dao010.getHeaderValue(mailData.getMailNum(), "Reply-To");
            if (!replyList.isEmpty()) {
                for (String address : replyList) {
                    StringTokenizer st = new StringTokenizer(address, ",");
                    while (st.hasMoreTokens()) {
                        String toAddress = st.nextToken().trim();
                        if (toAddress.length() > 0) {
                            if (to.length() > 0) {
                                to += ",";
                            }
                            to += MailUtil.decodeText(toAddress);
                        }
                    }
                }
            }
        } finally {
            if (to.length() == 0) {
                to = mailData.getFrom();
            }
        }

        return _escapeTextBox(to);
    }

    /**
     * <br>[機  能] メールの送信を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl リクエスト情報
     * @param gsContext GroupSession共通情報
     * @param userSid ユーザSID
     * @param appRootPath アプリケーションルートパス
     * @param tempRootDir テンポラリルートディレクトリ
     * @param reqMdl リクエスト情報
     * @throws Exception メールの受信に失敗
     * @return SmtpSendModel
     */
    public WmlSendResultModel sendMail(Connection con, Wml010ParamModel paramMdl,
                        GSContext gsContext, int userSid,
                        String appRootPath,
                        String tempRootDir, RequestModel reqMdl)
    throws Exception {

        return sendMail(con, createSendMailData(con, paramMdl), gsContext, userSid,
                        appRootPath, tempRootDir, reqMdl);
    }


    /**
     * <br>[機  能] メールの送信を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param mailData 送信メール情報
     * @param gsContext GroupSession共通情報
     * @param userSid ユーザSID
     * @param appRootPath アプリケーションルートパス
     * @param tempRootDir テンポラリルートディレクトリ
     * @param reqMdl リクエスト情報
     * @throws Exception メールの受信に失敗
     * @return SmtpSendModel
     */
    public WmlSendResultModel sendMail(Connection con, Wml010SendMailModel mailData,
                                GSContext gsContext, int userSid,
                                String appRootPath, String tempRootDir, RequestModel reqMdl)
    throws Exception {
        return sendMail(con, mailData,
                gsContext, userSid,
                appRootPath, tempRootDir,
                reqMdl.getSession().getId(), reqMdl.getDomain(), reqMdl.getLocale());
    }

    /**
     * <br>[機  能] メールの送信を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param mailData 送信メール情報
     * @param gsContext GroupSession共通情報
     * @param userSid ユーザSID
     * @param appRootPath アプリケーションルートパス
     * @param tempRootDir テンポラリルートディレクトリ
     * @param tempDirId テンポラリディレクトリID
     * @param domain ドメイン
     * @param locale ロケール
     * @throws Exception メールの受信に失敗
     * @return SmtpSendModel
     */
    public WmlSendResultModel sendMail(Connection con,
                                Wml010SendMailModel mailData,
                                GSContext gsContext, int userSid,
                                String appRootPath, String tempRootDir,
                                String tempDirId, String domain, Locale locale)
    throws Exception {
        WmlSendResultModel sendResult = new WmlSendResultModel();

        int wacSid = mailData.getWacSid();
        WmlAccountDao accountDao = new WmlAccountDao(con);
        WmlAccountModel accountData = accountDao.select(wacSid);

        SmtpModel smtpData = new SmtpModel();

        smtpData.setSendServer(accountData.getWacSendHost());
        smtpData.setSendPort(accountData.getWacSendPort());
        smtpData.setSmtpAuth(accountData.getWacSmtpAuth() == GSConstWebmail.WAC_SMTPAUTH_YES);
        smtpData.setSendUser(accountData.getWacSendUser());
        smtpData.setSendPassword(accountData.getWacSendPass());
        smtpData.setEncode(WmlBiz.getSendEncode(accountData));

        smtpData.setPopBeforeSmtp(accountData.getWacPopbsmtp() == GSConstWebmail.WAC_POPBSMTP_YES);
        smtpData.setSsl(accountData.getWacSendSsl() == GSConstWebmail.WAC_SEND_SSL_USE);
        smtpData.setPopServer(accountData.getWacReceiveHost());
        smtpData.setPopServerPort(accountData.getWacReceivePort());
        smtpData.setPopServerUser(accountData.getWacReceiveUser());
        smtpData.setPopServerPassword(accountData.getWacReceivePass());
        smtpData.setPopServerSsl(accountData.getWacReceiveSsl() == GSConstWebmail.WAC_SEND_SSL_USE);
        smtpData.setTempFileList(getTempFileList(tempRootDir, tempDirId));

        WmlBiz wmlBiz = new WmlBiz();
        WmlSmtpSendBiz sendBiz = new WmlSmtpSendBiz();

        //送信メール情報を作成する
        Wml010SendMailModel wml010SendData
                                    = createSendMailData(con,
                                            mailData,
                                            smtpData.getEncode(),
                                            gsContext, userSid,
                                            appRootPath, tempRootDir,
                                            tempDirId,
                                            accountData);
        SmtpSendModel sendData = wml010SendData.getSendData();
        long mailNum = wml010SendData.getSendMessageNum();

        //送信メールの上限チェック
        if (!wmlBiz.checkSendmailSize(con, wacSid, sendData, smtpData.getEncode())) {
            String archivePath = wml010SendData.getArchiveFilePath();
            if (archivePath != null) {
                IOTools.deleteFile(archivePath);
            }

            sendResult.setResult(WmlSendResultModel.RESULT_SIZEOVER);
            sendResult.setMailMaxSize(wmlBiz.getSendMailLimitSize(con, wacSid));
            sendResult.setMailSize(wmlBiz.getSendMailSize(sendData, smtpData.getEncode()));

            return sendResult;
        }

        long messageNum = sendBiz.sendMail(wacSid, smtpData, sendData, domain);
        //自動で圧縮を行った場合、パスワードを送信する
        if (wml010SendData.isArchiveMail()) {
            SmtpSendModel archiveSendData = new SmtpSendModel();
            archiveSendData.setCon(con);
            archiveSendData.setWdrSid(sendData.getWdrSid());
            archiveSendData.setGsContext(sendData.getGsContext());
            archiveSendData.setUserSid(sendData.getUserSid());
            archiveSendData.setHtmlMail(false);

            //パスワード通知メールの件名を設定
            GsMessage gsMsg = new GsMessage(locale);
            String passwdSubject = NullDefault.getString(sendData.getSubject(), "");
            passwdSubject += " " + gsMsg.getMessage("cmn.password.notification");
            if (passwdSubject.length() > GSConstWebmail.MAXLEN_MAIL_SUBJECT) {
                passwdSubject = passwdSubject.substring(0, GSConstWebmail.MAXLEN_MAIL_SUBJECT);
            }
            archiveSendData.setSubject(passwdSubject);

            archiveSendData.setFrom(sendData.getFrom());
            archiveSendData.setTo(sendData.getTo());
            archiveSendData.setCc(sendData.getCc());
            archiveSendData.setBcc(sendData.getBcc());

            String templatePath = IOTools.setEndPathChar(appRootPath);
            templatePath
                = IOTools.replaceSlashFileSep(templatePath
                                                        + "/WEB-INF/plugin/webmail/mail/");

            //添付ファイルパスワードメールのテンプレートを取得
            String lang = "JP";
            CmnUsrLangDao langDao = new CmnUsrLangDao(con);
            CmnUsrLangModel langMdl = langDao.select(userSid);
            if (langMdl != null) {
                lang = NullDefault.getString(langMdl.getCulCountry(), "JP");
            }
            if (lang.equals("JP")) {
                templatePath += "tempfile_compress_tsuuchi_ja.txt";
            } else {
                templatePath += "tempfile_compress_tsuuchi_en.txt";
            }

            String tmpBody = IOTools.readText(templatePath, Encoding.UTF_8);
            Map<String, String> map = new HashMap<String, String>();
            map.put("PASSWD", wml010SendData.getArchivePassword());
            map.put("FROM", NullDefault.getString(archiveSendData.getFrom(), " "));
            map.put("TO", NullDefault.getString(archiveSendData.getTo(), " "));
            map.put("CC", NullDefault.getString(archiveSendData.getCc(), " "));
            map.put("SUBJECT", NullDefault.getString(archiveSendData.getSubject(), " "));

            WmlMaildataDao mailDataDao = new WmlMaildataDao(con);
            UDate sendDate = mailDataDao.getSdate(messageNum);
            if (sendDate != null) {
                map.put("DATE", UDateUtil.getSlashYYMD(sendDate)
                                        + " " + UDateUtil.getSeparateHMS(sendDate));
            } else {
                map.put("DATE", " ");
            }

            String archiveMailBody = StringUtil.merge(tmpBody, map);
            archiveMailBody = _escapeTextAreaInput(archiveMailBody);
            archiveSendData.setBody(archiveMailBody);

            sendBiz.sendMail(wacSid, smtpData, archiveSendData, domain);
        }

        Wml010Dao dao010 = new Wml010Dao(con);
        switch (mailData.getSendMailType()) {
            case Wml010Const.SEND_TYPE_REPLY :
            case Wml010Const.SEND_TYPE_REPLY_ALL :
                dao010.setReply(mailNum);
                break;

            case Wml010Const.SEND_TYPE_FORWARD :
                dao010.setForward(mailNum);
                break;

            case Wml010Const.SEND_TYPE_EDIT :
                if (dao010.getDirType(mailNum) == GSConstWebmail.DIR_TYPE_DRAFT
                || dao010.getDirType(mailNum) == GSConstWebmail.DIR_TYPE_NOSEND) {
                    //編集したメールのディレクトリ種別 = 草稿 or 未送信の場合、メールを削除する
                    __deleteMailData(con, String.valueOf(wacSid), new long[]{mailNum}, userSid);
                    sendData.setSendToDraft(true);
                }
                break;

            default:
        }

        String tempDir = getSendTempDir(tempRootDir, tempDirId);
        IOTools.deleteDir(tempDir);

        sendResult.setSendMailData(sendData);
        return sendResult;
    }

    /**
     * <br>[機  能] メールの草稿保存を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl リクエスト情報
     * @param gsContext GroupSession共通情報
     * @param userSid ユーザSID
     * @param tempRootDir テンポラリルートディレクトリ
     * @param reqMdl リクエスト情報
     * @throws Exception メールの受信に失敗
     * @return WmlMailModel
     */
    public WmlMailModel saveDraftMail(Connection con, Wml010ParamModel paramMdl,
            GSContext gsContext,
            int userSid, String tempRootDir, RequestModel reqMdl)
    throws Exception {

        int sendAccount = _getSendAccountSid(paramMdl);
        if (sendAccount <= 0) {
            throw new Exception("アカウントが選択されていません");
        }
        return saveMailData(con,
                            sendAccount,
                            createSendMailData(con, paramMdl), gsContext,
                            userSid, tempRootDir, reqMdl,
                            GSConstWebmail.DIR_TYPE_DRAFT,
                            paramMdl.getWml010sendMailType());
    }

    /**
     * <br>[機  能] 送信待ちメールの保存を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl リクエスト情報
     * @param gsContext GroupSession共通情報
     * @param userSid ユーザSID
     * @param tempRootDir テンポラリルートディレクトリ
     * @param reqMdl リクエスト情報
     * @throws Exception メールの受信に失敗
     * @return WmlMailModel
     */
    public WmlMailModel saveSendPlanMail(Connection con, Wml010ParamModel paramMdl,
            GSContext gsContext,
            int userSid, String tempRootDir, RequestModel reqMdl)
    throws Exception {

        int sendAccount = _getSendAccountSid(paramMdl);
        if (sendAccount <= 0) {
            throw new Exception("アカウントが選択されていません");
        }
        return saveMailData(con,
                            sendAccount,
                            createSendMailData(con, paramMdl), gsContext,
                            userSid, tempRootDir, reqMdl,
                            GSConstWebmail.DIR_TYPE_NOSEND,
                            paramMdl.getWml010sendMailType());
    }

    /**
     * <br>[機  能] メールの保存を行う
     * <br>[解  説]
     * <br>[備  考] 草稿、予約送信メールの保存を行う
     * @param con コネクション
     * @param wacSid アカウントSID
     * @param mailData 送信メール情報
     * @param gsContext GroupSession共通情報
     * @param userSid ユーザSID
     * @param tempRootDir テンポラリルートディレクトリ
     * @param reqMdl リクエスト情報
     * @param dirType ディレクトリ種別
     * @param sendType 送信メール表示時の操作(新規作成、返信 など)
     * @throws Exception メールの受信に失敗
     * @return WmlMailModel
     */
    public WmlMailModel saveMailData(Connection con, int wacSid,
                                    Wml010SendMailModel mailData,
                                    GSContext gsContext, int userSid,
                                    String tempRootDir, RequestModel reqMdl,
                                    int dirType,
                                    int sendType)
    throws Exception {

        int sendAccount = mailData.getWacSid();
        WmlBiz wmlBiz = new WmlBiz();
        long wdrSid = wmlBiz.getDirectorySid(con, sendAccount, dirType);
        long beforeMailNum = mailData.getSendMessageNum();

        WmlMailModel sendData = new WmlMailModel();
        sendData.setSendDate(null);
        sendData.setSubject(mailData.getSubject());
        sendData.setContent(mailData.getContent());

        sendData.addFrom(null);
        sendData.setTo(parseSendAddress(mailData.getTo()));
        sendData.setCc(parseSendAddress(mailData.getCc()));
        sendData.setBcc(parseSendAddress(mailData.getBcc()));
        sendData.setTempFileList(getTempFileList(tempRootDir, reqMdl));

        SmtpSendModel sendModel = new SmtpSendModel();
        sendModel.setCon(con);
        sendModel.setWdrSid(wdrSid);
        sendModel.setUserSid(userSid);
        sendModel.setGsContext(gsContext);
        sendModel.setHtmlMail(mailData.isHtmlMail());
        sendModel.setTimeSent(mailData.isTimeSent());
        sendModel.setSendPlanDate(mailData.getSendPlanDate());
        sendModel.setSendPlanCompressFileType(mailData.getCompressFileType());

        WmlAccountDao accountDao = new WmlAccountDao(con);
        WmlAccountModel accountMdl = accountDao.select(sendAccount);
        String encode = Encoding.ISO_2022_JP;
        if (accountMdl.getWacEncodeSend() == GSConstWebmail.WAC_ENCODE_SEND_UTF8) {
            encode = Encoding.UTF_8;
        }
        WmlSmtpSendBiz sendBiz = new WmlSmtpSendBiz();
        sendBiz.entrySendMailData(wacSid, sendModel, sendData,
                                                WmlSmtpSendBiz.ENTRY_TYPE_INSERT,
                                                dirType == GSConstWebmail.DIR_TYPE_DRAFT,
                                                encode, reqMdl.getDomain());

        if (beforeMailNum > 0) {
            Wml010Dao dao010 = new Wml010Dao(con);
            int beforeDirType = dao010.getDirType(beforeMailNum);

            //編集元のメール情報が存在する、かつ編集元メールが予約送信 or 草稿の場合、編集元メールを削除する
            if (beforeDirType == GSConstWebmail.DIR_TYPE_NOSEND
            || beforeDirType == GSConstWebmail.DIR_TYPE_DRAFT) {
                if (sendType != Wml010Const.SEND_TYPE_REPLY
                && sendType != Wml010Const.SEND_TYPE_REPLY_ALL
                && sendType != Wml010Const.SEND_TYPE_FORWARD) {
                    __deleteMailData(con, String.valueOf(wacSid),
                                            new long[]{beforeMailNum}, userSid);
                }
            }

            //予約送信、かつ返信 or 転送の場合、編集元メールの返信フラグ or 転送フラグを更新する
            if (dirType == GSConstWebmail.DIR_TYPE_NOSEND) {
                switch (sendType) {
                    case Wml010Const.SEND_TYPE_REPLY :
                    case Wml010Const.SEND_TYPE_REPLY_ALL :
                        dao010.setReply(beforeMailNum);
                        break;

                    case Wml010Const.SEND_TYPE_FORWARD :
                        dao010.setForward(beforeMailNum);
                        break;
                    default:
                }
            }
        }

        String tempDir = getSendTempDir(tempRootDir, reqMdl);
        IOTools.deleteDir(tempDir);

        List<String> fromList = new ArrayList<String>();
        fromList.add(accountMdl.getWacAddress());
        sendData.setFrom(fromList);
        return sendData;
    }

    /**
     * <br>[機  能] 送信アカウントのSIDを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl リクエスト情報
     * @return 送信アカウントSID
     * @throws SQLException SQL実行時例外
     */
    protected int _getSendAccountSid(Wml010ParamModel paramMdl) throws SQLException {
        return paramMdl.getWml010sendAccount();
    }

    /**
     * <br>[機  能] 送信メール情報を生成する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl リクエスト情報
     * @return 送信メール情報
     * @throws SQLException SQL実行時例外
     */
    public Wml010SendMailModel createSendMailData(Connection con, Wml010ParamModel paramMdl)
    throws SQLException {
        int sendWacSid = _getSendAccountSid(paramMdl);
        Wml010SendMailModel mailData = new Wml010SendMailModel();
        mailData.setWacSid(sendWacSid);
        mailData.setHtmlMail(paramMdl.getWml010sendMailHtml() == Wml010Const.SEND_HTMLMAIL_HTML);
        mailData.setSubject(paramMdl.getWml010sendSubject());
        mailData.setTo(paramMdl.getWml010sendAddressTo());
        mailData.setCc(paramMdl.getWml010sendAddressCc());
        mailData.setBcc(paramMdl.getWml010sendAddressBcc());
        mailData.setContent(paramMdl.getWml010svSendContent());

        mailData.setSendMessageNum(paramMdl.getWml010sendMessageNum());
        mailData.setSendMailType(paramMdl.getWml010sendMailType());

        boolean timeSent = paramMdl.getSendMailPlanType() == Wml010Const.TIMESENT_AFTER;
        mailData.setTimeSent(timeSent);
        if (mailData.isTimeSent()) {
            UDate sendPlanDate = new UDate();
            sendPlanDate.setTime(0);
            sendPlanDate.setYear(Integer.parseInt(paramMdl.getWml010sendMailPlanDateYear()));
            sendPlanDate.setMonth(Integer.parseInt(paramMdl.getWml010sendMailPlanDateMonth()));
            sendPlanDate.setDay(Integer.parseInt(paramMdl.getWml010sendMailPlanDateDay()));
            sendPlanDate.setHour(Integer.parseInt(paramMdl.getWml010sendMailPlanDateHour()));
            sendPlanDate.setMinute(Integer.parseInt(paramMdl.getWml010sendMailPlanDateMinute()));
            mailData.setSendPlanDate(sendPlanDate);
        } else {

            WmlBiz wmlBiz = new WmlBiz();
            boolean timeSentInput = wmlBiz.isTimeSentInput(con, sendWacSid);
            if (timeSentInput) {
                mailData.setTimeSent(
                        paramMdl.getWml010sendMailPlanImm() != Wml010Const.SENDPLAN_IMM);
            } else {
                mailData.setTimeSent(wmlBiz.isTimeSent(con, sendWacSid));
            }

            if (mailData.isTimeSent()) {
                //現在日時よりX分後に送信する
                UDate sendDate = new UDate();
                if (timeSentInput) {
                    sendDate.addMinute(5);
                } else {
                    sendDate.addMinute(wmlBiz.getTimeSentMinute(con, sendWacSid));
                }
                mailData.setSendPlanDate(sendDate);
            }
        }

        //添付ファイル自動圧縮
        boolean compressFileInput = false;
        WmlAdmConfDao wacAdmConfDao = new WmlAdmConfDao(con);
        WmlAdmConfModel admConfMdl = wacAdmConfDao.selectAdmData();
        boolean admConfFlg
            = (admConfMdl != null
                && admConfMdl.getWadPermitKbn() == GSConstWebmail.PERMIT_ADMIN);
        if (admConfFlg) {
            compressFileInput
                = admConfMdl.getWadCompressFile() == GSConstWebmail.WAD_COMPRESS_FILE_INPUT;
        } else {
            WmlAccountDao accountDao = new WmlAccountDao(con);
            WmlAccountModel accountData = accountDao.select(sendWacSid);
            compressFileInput
                = accountData.getWacCompressFile() == GSConstWebmail.WAD_COMPRESS_FILE_INPUT;
        }

        if (compressFileInput) {
            //画面からの指定
            if (paramMdl.getWml010sendTempfileCompress() == 1) {
                mailData.setCompressFileType(GSConstWebmail.WSP_COMPRESS_FILE_COMPRESS);
            } else {
                mailData.setCompressFileType(GSConstWebmail.WSP_COMPRESS_FILE_NOTCOMPRESS);
            }
        } else {
            mailData.setCompressFileType(GSConstWebmail.WSP_COMPRESS_FILE_NOSET);
        }

        return mailData;
    }

    /**
     * <br>[機  能] 送信メールのアドレス(宛先, CC, BCC)をparseする
     * <br>[解  説]
     * <br>[備  考]
     * @param address メールアドレス
     * @return メールアドレス
     */
    public List<String> parseSendAddress(String address) {
        List<String> ret = new ArrayList<String>();
        if (!StringUtil.isNullZeroString(address)) {
            try {
                WmlSmtpSendBiz biz = new WmlSmtpSendBiz();
                ret = biz.getAddressList(address);
            } catch (AddressException e) {
                ret = new ArrayList<String>();
            }
        }
        return ret;
    }

    /**
     * <br>[機  能] 指定されたメールの未読/既読を変更する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param readType メール未読/既読
     * @return エラーメッセージ
     * @throws SQLException SQL実行時例外
     */
    public String changeMailReaded(Connection con, Wml010ParamModel paramMdl,
                                                RequestModel reqMdl, int readType)
    throws SQLException {
        GsMessage gsMsg = new GsMessage(reqMdl);
        long[] messageNum = getSelectMessageNum(paramMdl);
        if (messageNum == null || messageNum.length == 0) {
            return gsMsg.getMessage("wml.plz.select.mail");
        }

        Wml010Dao dao010 = new Wml010Dao(con);
        dao010.changeMailReaded(messageNum, readType);
        return "success";
    }

    /**
     * <br>[機  能] 指定されたディレクトリのメール未読/既読を変更する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl リクエスト情報
     * @param readType メール未読/既読
     * @throws SQLException SQL実行時例外
     */
    public void changeMailReadedInDirectory(Connection con, Wml010ParamModel paramMdl, int readType)
    throws SQLException {
        //一度に更新するメールの最大件数
        final int maxNum = 100;

        long wdrSid = paramMdl.getWml010viewDirectory();
        Wml010Dao dao010 = new Wml010Dao(con);
        List<Long> mailNumList = dao010.getMailNum(wdrSid, readType);
        if (!mailNumList.isEmpty()) {
            for (int idx = 0; idx < mailNumList.size(); idx += maxNum) {
                boolean commit = false;
                try {
                    int toIndex = idx + maxNum;
                    if (toIndex > mailNumList.size()) {
                        toIndex = mailNumList.size();
                    }

                    dao010.changeMailReadedToMail(mailNumList.subList(idx, toIndex), readType);
                    con.commit();
                    commit = true;
                } catch (Exception e) {
                    log__.error("メールの未読/既読変更処理で例外発生", e);
                    return;
                } finally {
                    if (!commit) {
                        con.rollback();
                    }
                }
            }
        }
    }

    /**
     * <br>[機  能] 添付ファイルのアップロードを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdlFile 添付ファイル
     * @param tempRootDir テンポラリルートディレクトリ
     * @param reqMdl リクエスト情報
     * @return 保存ファイル名
     * @throws Exception 添付ファイルのアップロードに失敗
     */
    public String uploadTempFile(FormFile paramMdlFile, String tempRootDir, RequestModel reqMdl)
    throws Exception {

        String saveFileName = null;
        try {

            //テンポラリディレクトリパス
            String tempDir =
                IOTools.replaceFileSep(getSendTempDir(tempRootDir, reqMdl));
            //現在日付の文字列(YYYYMMDD)を取得
            String dateStr = (new UDate()).getDateString();

            //ファイルの連番を取得する
            int fileNum = Cmn110Biz.getFileNumber(tempDir, dateStr);
            fileNum++;

            //添付ファイル名
            String fileName = (new File(paramMdlFile.getFileName())).getName();
            long fileSize = paramMdlFile.getFileSize();
            //添付ファイル(本体)のパスを取得
            File saveFilePath = Cmn110Biz.getSaveFilePath(tempDir, dateStr, fileNum);

            //添付ファイルアップロード
            TempFileUtil.upload(paramMdlFile, tempDir, saveFilePath.getName());

            //オブジェクトファイルを設定
            File objFilePath = Cmn110Biz.getObjFilePath(tempDir, dateStr, fileNum);
            Cmn110FileModel fileMdl = new Cmn110FileModel();
            fileMdl.setFileName(fileName);
            fileMdl.setSaveFileName(saveFilePath.getName());
            fileMdl.setAtattiSize(fileSize);

            ObjectFile objFile = new ObjectFile(objFilePath.getParent(), objFilePath.getName());
            objFile.save(fileMdl);

            saveFileName = saveFilePath.getName();

        } catch (Exception e) {
            log__.error("添付ファイルのアップロードに失敗", e);
            throw new IOException();
        }

        return saveFileName;
    }

    /**
     * <br>[機  能] 添付ファイルの削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param tempRootDir テンポラリルートディレクトリ
     * @param saveFileName 保存ファイル名
     * @param reqMdl リクエスト情報
     * @throws Exception 添付ファイルの削除に失敗
     */
    public void deleteTempFile(String tempRootDir, String saveFileName, RequestModel reqMdl)
    throws Exception {

        String tempDir = getSendTempDir(tempRootDir, reqMdl);

        //オブジェクトファイルを取得
        ObjectFile objFile = new ObjectFile(tempDir, saveFileName);
        Object fObj = objFile.load();
        Cmn110FileModel fMdl = (Cmn110FileModel) fObj;
        String oldObjName = fMdl.getSplitObjName();

        //テンポラリファイルのフルパス(オブジェクト)
        String delPathObj =
            tempDir + oldObjName + GSConstCommon.ENDSTR_OBJFILE;
        delPathObj = IOTools.replaceFileSep(delPathObj);
        log__.debug("削除するファイルのフルパス(オブジェクト) = " + delPathObj);

        //テンポラリファイルのフルパス(本体)
        String delPathFile =
            tempDir + oldObjName + GSConstCommon.ENDSTR_SAVEFILE;
        delPathFile = IOTools.replaceFileSep(delPathFile);
        log__.debug("削除するファイルのフルパス(本体) = " + delPathFile);

        //ファイルを削除
        IOTools.deleteFile(delPathObj);
        IOTools.deleteFile(delPathFile);
    }

    /**
     * <br>[機  能] 指定されたメールアドレス一覧の結合を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param addressList メールアドレス一覧
     * @return 結合したメールアドレス
     */
    public String joinAddress(List<String> addressList) {
        if (addressList == null || addressList.isEmpty()) {
            return null;
        }

        String joinAddress = addressList.get(0);
        for (int idx = 1; idx < addressList.size(); idx++) {
            joinAddress += ", " + addressList.get(idx);
        }

        return joinAddress;
    }

    /**
     * <br>[機  能] 添付ファイル情報をJSON形式に変換する
     * <br>[解  説]
     * <br>[備  考]
     * @param strBuild StringBuilder
     * @param mailData メール情報
     * @throws UnsupportedEncodingException URLエンコードに失敗
     */
    private void __setJsonTempFile(StringBuilder strBuild, Wml010MailModel mailData)
    throws UnsupportedEncodingException {
        StringBuilder fileId = new StringBuilder("\"fileId\":[");
        StringBuilder fileName = new StringBuilder("\"fileName\":[");
        StringBuilder fileSize = new StringBuilder("\"fileSize\":[");

        List<MailTempFileModel> tempFileList = mailData.getTempFileList();
        if (tempFileList != null && !tempFileList.isEmpty()) {
            for (int index = 0; index < tempFileList.size(); index++) {
                if (index > 0) {
                    fileId.append(",");
                    fileName.append(",");
                    fileSize.append(",");
                }

                MailTempFileModel tempFile = tempFileList.get(index);
                long lngFileSize = tempFile.getFileSize();
                //String strSize = "";
                if (lngFileSize > 1048576) {
                    BigDecimal decFileSize = new BigDecimal(lngFileSize);
                    decFileSize = decFileSize.divide(new BigDecimal(1048576), 1,
                                                    BigDecimal.ROUND_HALF_UP);
                    //strSize = "\"" + decFileSize.toString() + " MByte\"";
                    fileSize.append("\"").append(decFileSize.toString()).append(" MByte\"");
                } else {
                    BigDecimal decFileSize = new BigDecimal(lngFileSize);
                    decFileSize = decFileSize.divide(new BigDecimal(1024), 1,
                                                    BigDecimal.ROUND_HALF_UP);
                    //strSize = "\"" + decFileSize.toString() + " KByte\"";
                    fileSize.append("\"").append(decFileSize.toString()).append(" KByte\"");
                }
                fileId.append(tempFile.getBinSid());
                fileName.append("\"");
//                fileName.append(__URLEncode(_escapeText(tempFile.getFileName())));
                fileName.append(__URLEncode(_escapeTextView(tempFile.getFileName())));
                fileName.append("\"");
                //fileSize.append(strSize);
            }
        }
        strBuild.append(fileId.toString() + "]");
        strBuild.append(",");
        strBuild.append(fileName.toString() + "]");
        strBuild.append(",");
        strBuild.append(fileSize.toString() + "]");
    }

    /**
     * <br>[機  能] 表示アカウントのアカウントSIDを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl リクエスト情報
     * @return アカウントSID
     */
    protected int _getViewAccountSid(Wml010ParamModel paramMdl) {
        return Integer.parseInt(paramMdl.getWmlViewAccount());
    }

    /**
     * <br>[機  能] 選択されたメールのメッセージ番号を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl リクエスト情報
     * @return メッセージ番号
     */
    public long[] getSelectMessageNum(Wml010ParamModel paramMdl) {
        return paramMdl.getWml010selectMessageNum();
    }

    /**
     * <br>[機  能] 選択されたメールのディレクトリSIDを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl リクエスト情報
     * @return ディレクトリSID
     */
    public int[] getSelectMessageDirId(Wml010ParamModel paramMdl) {
        return paramMdl.getWml010selectMessageDirId();
    }

    /**
     * <br>[機  能] 日付(年 or 月 or 日)コンボを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param dateStr 日付文字列
     * @param start 開始
     * @param end 終了
     * @return 日付コンボ
     */
    private List<LabelValueBean> __createDateCombo(String dateStr, int start, int end) {
        List<LabelValueBean> combo = new ArrayList<LabelValueBean>();
        for (; start <= end; start++) {
            combo.add(new LabelValueBean(start + dateStr, String.valueOf(start)));
        }

        return combo;
    }

    /**
     * <br>[機  能] 送信メール添付ファイルのテンポラリディレクトリパスを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param rootDir ルートディレクトリ
     * @param reqMdl リクエスト情報
     * @return テンポラリディレクトリパス
     */
    public String getSendTempDir(String rootDir, RequestModel reqMdl) {
        return getSendTempDir(rootDir, reqMdl.getSession().getId());
    }

    /**
     * <br>[機  能] 送信メール添付ファイルのテンポラリディレクトリパスを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param rootDir ルートディレクトリ
     * @param tempDirId テンポラリディレクトリID
     * @return テンポラリディレクトリパス
     */
    public String getSendTempDir(String rootDir, String tempDirId) {
        StringBuilder tempDir = new StringBuilder("");
        tempDir.append(rootDir);
        tempDir.append("/");
        tempDir.append(GSConstWebmail.PLUGIN_ID_WEBMAIL);
        tempDir.append("/");
        tempDir.append(tempDirId);
        tempDir.append("/sendmail/");

        return tempDir.toString();
    }

    /**
     * <br>[機  能] 送信メール添付ファイルのテンポラリパス(ファイル名を含む)を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param tempDir テンポラリディレクトリ
     * @param date 日付
     * @param fileNum ファイル番号
     * @return 送信メール添付ファイルのテンポラリパス
     */
    public File getSendTempPath(RequestModel reqMdl, String tempDir, UDate date, int fileNum) {
        return Cmn110Biz.getSaveFilePath(getSendTempDir(tempDir, reqMdl),
                                        date.getDateString(), fileNum);
    }

    /**
     * <br>[機  能] 送信メールの添付ファイル情報一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param tempRootDir テンポラリルートディレクトリ
     * @param reqMdl リクエスト情報
     * @return 添付ファイル情報一覧
     * @throws Exception 添付ファイル情報取得時に例外発生
     */
    public List<WmlMailFileModel> getTempFileList(String tempRootDir, RequestModel reqMdl)
    throws Exception {
        return getTempFileList(tempRootDir, reqMdl.getSession().getId());
    }

    /**
     * <br>[機  能] 送信メールの添付ファイル情報一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param tempRootDir テンポラリルートディレクトリ
     * @param tempDirId テンポラリディレクトリID
     * @return 添付ファイル情報一覧
     * @throws Exception 添付ファイル情報取得時に例外発生
     */
    public List<WmlMailFileModel> getTempFileList(String tempRootDir, String tempDirId)
    throws Exception {

        List<WmlMailFileModel> tempFileList = new ArrayList<WmlMailFileModel>();

        String tempDir = getSendTempDir(tempRootDir, tempDirId);

        List<String> fileList = IOTools.getFileNames(tempDir);
        if (fileList != null && !fileList.isEmpty()) {

            for (String fileName : fileList) {
                if (!fileName.endsWith(GSConstCommon.ENDSTR_OBJFILE)) {
                    continue;
                }

                //オブジェクトファイルを取得
                ObjectFile objFile = new ObjectFile(tempDir, fileName);
                Object fObj = objFile.load();
                if (fObj == null) {
                    continue;
                }

                Cmn110FileModel fMdl = (Cmn110FileModel) fObj;

                WmlMailFileModel fileMdl = new WmlMailFileModel();
                fileMdl.setFileName(fMdl.getFileName());
                fileMdl.setFilePath(IOTools.replaceFileSep(tempDir + fMdl.getSaveFileName()));
                tempFileList.add(fileMdl);
            }
        }

        return tempFileList;
    }

    /**
     * <br>[機  能] 送信メールの添付ファイル情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param tempRootDir テンポラリルートディレクトリ
     * @param sendFileName ファイル名
     * @param reqMdl リクエスト情報
     * @return 添付ファイル情報
     * @throws Exception 添付ファイル情報取得時に例外発生
     */
    public Cmn110FileModel getSendFileData(String tempRootDir, String sendFileName,
                                            RequestModel reqMdl)
    throws Exception {

        Cmn110FileModel tempFileModel = null;
        String tempDir = getSendTempDir(tempRootDir, reqMdl);

        List<String> fileList = IOTools.getFileNames(tempDir);
        if (fileList != null && !fileList.isEmpty()) {

            for (String fileName : fileList) {
                if (!fileName.endsWith(GSConstCommon.ENDSTR_OBJFILE)) {
                    continue;
                }

                //オブジェクトファイルを取得
                ObjectFile objFile = new ObjectFile(tempDir, fileName);
                Object fObj = objFile.load();
                if (fObj == null) {
                    continue;
                }

                if (((Cmn110FileModel) fObj).getSaveFileName().equals(sendFileName)) {
                    tempFileModel = (Cmn110FileModel) fObj;
                    tempFileModel.setBinFilePath(
                            IOTools.replaceFileSep(tempDir + tempFileModel.getSaveFileName()));
                    break;
                }
            }
        }

        return tempFileModel;
    }

    /**
     * <br>[機  能] メール情報をeml形式のファイルへ出力する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @param paramMdl リクエスト情報
     * @param appRootPath アプリケーションルートパス
     * @param tempDir テンポラリディレクトリ
     * @return 出力したメールの情報
     * @throws SQLException SQL実行時例外
     * @throws IOException ファイルの書き出しに失敗
     * @throws IOToolsException テンポラリディレクトリの削除に失敗
     * @throws TempFileException 添付ファイル情報の取得に失敗
     */
    public Wml010ExportFileModel createExportFile(Connection con, RequestModel reqMdl,
                                                Wml010ParamModel paramMdl,
                                                String appRootPath, String tempDir)
    throws SQLException, IOException, IOToolsException, TempFileException {
        long messageNum = getSelectMessageNum(paramMdl)[0];
        String exportDir = tempDir + "/webmail/"
                                    + reqMdl.getSession().getId() + "/export/";
        File exportFilePath = new File(exportDir + messageNum + ".eml");

        return __createExportFile(con, reqMdl, paramMdl,
                                            appRootPath, exportFilePath, messageNum);
    }

    /**
     * <br>[機  能] メール情報をeml形式のファイルへ出力し、まとめてZIP圧縮する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @param paramMdl リクエスト情報
     * @param appRootPath アプリケーションルートパス
     * @param tempDir テンポラリディレクトリ
     * @return 出力したファイルのパス
     * @throws SQLException SQL実行時例外
     * @throws IOException ファイルの書き出しに失敗
     * @throws IOToolsException テンポラリディレクトリの削除に失敗
     * @throws TempFileException 添付ファイル情報の取得に失敗
     */
    public Wml010ExportFileModel createExportFileList(Connection con, RequestModel reqMdl,
                                                Wml010ParamModel paramMdl,
                                                String appRootPath, String tempDir)
    throws SQLException, IOException, IOToolsException, TempFileException {
        long[] messageNumList = getSelectMessageNum(paramMdl);

        String exportDir = tempDir + "/webmail/"
                                    + reqMdl.getSession().getId() + "/export/";
        String emlExportDir = exportDir + "eml/";

        if (IOTools.isDirCheck(emlExportDir, true)) {
            IOTools.deleteInDir(emlExportDir);
        }

        WmlBiz wmlBiz = new WmlBiz();
        File exportFilePath = null;
        File mailFilePath = null;

        for (long messageNum : messageNumList) {

            exportFilePath = new File(emlExportDir + messageNum + ".eml");
            Wml010ExportFileModel exportFileData
                = __createExportFile(con, reqMdl, paramMdl,
                                                appRootPath, exportFilePath, messageNum);
            if (exportFileData == null) {
                log__.error("メールのエクスポートに失敗");
                return null;
            }

            //ファイル名用の送信時間を設定
            String mailDate = UDateUtil.getYYMD(exportFileData.getSdate()) + "_"
                    + UDateUtil.getSeparateHMS(exportFileData.getSdate());
            String fileName = mailDate + "_";
            String subject = exportFileData.getSubject();
            if (StringUtil.isNullZeroString(subject)) {
                subject = "message";
            }
            fileName += subject;
            //使用可能なファイル名かチェック
            fileName = wmlBiz.fileNameCheck(fileName);

            mailFilePath = new File(emlExportDir + fileName + ".eml");
            int mailIdx = 1;
            while (mailFilePath.exists()) {
                mailFilePath = new File(emlExportDir + fileName + "_" + mailIdx + ".eml");
                mailIdx++;
            }

            exportFilePath.renameTo(mailFilePath);
        }

        Wml010ExportFileModel exportFileData = new Wml010ExportFileModel();

        UDate now = new UDate();
        String saveFilePath = exportDir
                                    + now.getDateString() + "_"
                                    + now.getStrHour()
                                    + now.getStrMinute()
                                    + now.getStrSecond()
                                    + "_webmailEml.zip";
        ZipUtil.zipDir("Windows-31J", emlExportDir, saveFilePath);

        exportFileData.setFilePath(new File(saveFilePath));
        exportFileData.setMessageNumList(messageNumList);

        IOTools.deleteInDir(emlExportDir);
        return exportFileData;
    }

    /**
     * <br>[機  能] メール情報をeml形式のファイルへ出力する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @param paramMdl リクエスト情報
     * @param appRootPath アプリケーションルートパス
     * @param exportFilePath エクスポートファイルパス
     * @param messageNum メッセージ番号
     * @return 出力したファイルのパス
     * @throws SQLException SQL実行時例外
     * @throws IOException ファイルの書き出しに失敗
     * @throws IOToolsException テンポラリディレクトリの削除に失敗
     * @throws TempFileException 添付ファイル情報の取得に失敗
     */
    private Wml010ExportFileModel __createExportFile(Connection con, RequestModel reqMdl,
                                                                        Wml010ParamModel paramMdl,
                                                                        String appRootPath,
                                                                        File exportFilePath,
                                                                        long messageNum)
    throws SQLException, IOException, IOToolsException, TempFileException {
        Wml010ExportFileModel resultMdl = null;

        CrlfTerminatedWriter pw = null;
        FileOutputStream fos = null;

        resultMdl = new Wml010ExportFileModel();
        resultMdl.setMessageNum(messageNum);

        try {
            //メール情報を取得
            WmlMaildataDao mailDataDao = new WmlMaildataDao(con);
            WmlMaildataModel mailData = mailDataDao.select(messageNum);

            //メールヘッダ情報を取得
            WmlHeaderDataDao headerDao = new WmlHeaderDataDao(con);
            List<WmlHeaderDataModel> headerList = headerDao.select(messageNum);
            List<String> headerTypeList = new ArrayList<String>();
            Map<String, List<String>> headerMap = new HashMap<String, List<String>>();

            String headerType = null;
            String contentType = null;
            String contentTransferEncoding = null;
            boolean existHeader = (headerList != null && !headerList.isEmpty());
            if (existHeader) {
                for (WmlHeaderDataModel headerData : headerList) {
                    headerType = headerData.getWmhType();
                    if (!headerMap.containsKey(headerType)) {
                        headerTypeList.add(headerType);
                        headerMap.put(headerType, new ArrayList<String>());
                    }

                    headerMap.get(headerType).add(headerData.getWmhContent());
                    if (headerType.toLowerCase().equals("content-type")) {
                        contentType = headerData.getWmhContent();
                    } else if (headerType.toLowerCase().equals("content-transfer-encoding")) {
                        contentTransferEncoding = headerData.getWmhContent();
                    }
                }
            }
            headerList = null;

            //本文の情報を取得
            WmlMailBodyDao bodyDao = new WmlMailBodyDao(con);
            WmlMailBodyModel bodyMdl = bodyDao.select(messageNum);

            //添付ファイル情報を取得
            List<MailTempFileModel> tempFileList = new ArrayList<MailTempFileModel>();
            if (mailData.getWmdTempflg() == GSConstWebmail.TEMPFLG_EXIST) {
                WebmailDao wmlDao = new WebmailDao(con);
                tempFileList = wmlDao.getTempFileList(messageNum);
            }

            //ヘッダー情報が存在しない場合、追加する。
            if (!existHeader) {
                try {
                    String encode = Encoding.ISO_2022_JP;
                    WmlAccountDao accountDao = new WmlAccountDao(con);
                    if (accountDao.select(mailData.getWacSid()).getWacEncodeSend()
                            == GSConstWebmail.WAC_ENCODE_SEND_UTF8) {
                        encode = Encoding.UTF_8;
                    }

                    if (!StringUtil.isNullZeroString(mailData.getWmdFrom())) {
                        String from = mailData.getWmdFrom();
                        InternetAddress[] fromAdddress = WmlBiz.parseAddressPlus(from, encode);
                        headerMap.put("From", new ArrayList<String>());
                        headerMap.get("From").add(fromAdddress[0].getAddress());
                        headerTypeList.add("From");
                    }

                    //送信先
                    MailBiz mailBiz = new MailBiz();
                    Wml010Dao dao010 = new Wml010Dao(con);
                    Wml010SendAddrModel sendAddrData
                        = dao010.getSendAddress(mailData.getWacSid(), mailData.getWmdMailnum());
                    if (sendAddrData.getToList() != null && !sendAddrData.getToList().isEmpty()) {
                        headerMap.put("To", new ArrayList<String>());

                        String sendAddress = "";
                        InternetAddress[] addressList
                            = MailBiz.parseAddressPlus(__getDispAddrs(sendAddrData.getToList()),
                                                                    encode);

                        for (int idx = 0; idx < addressList.length; idx++) {
                            if (idx > 0) {
                                sendAddress += ",";
                            }
                            sendAddress += addressList[idx].getAddress();
                        }
                        headerMap.get("To").add(sendAddress);
                        headerTypeList.add("To");
                    }
                    //CC
                    if (sendAddrData.getCcList() != null && !sendAddrData.getCcList().isEmpty()) {
                        headerMap.put("Cc", new ArrayList<String>());

                        String sendAddress = "";
                        InternetAddress[] addressList
                            = MailBiz.parseAddressPlus(__getDispAddrs(sendAddrData.getCcList()),
                                                                    encode);

                        for (int idx = 0; idx < addressList.length; idx++) {
                            if (idx > 0) {
                                sendAddress += ",";
                            }
                            sendAddress += addressList[idx].getAddress();
                        }
                        headerMap.get("Cc").add(sendAddress);
                        headerTypeList.add("Cc");
                    }
                    //BCC
                    if (sendAddrData.getBccList() != null && !sendAddrData.getBccList().isEmpty()) {
                        headerMap.put("Bcc", new ArrayList<String>());

                        String sendAddress = "";
                        InternetAddress[] addressList
                            = MailBiz.parseAddressPlus(__getDispAddrs(sendAddrData.getBccList()),
                                                                    encode);

                        for (int idx = 0; idx < addressList.length; idx++) {
                            if (idx > 0) {
                                sendAddress += ",";
                            }
                            sendAddress += addressList[idx].getAddress();
                        }
                        headerMap.get("Bcc").add(sendAddress);
                        headerTypeList.add("Bcc");
                    }

                    //件名
                    if (!StringUtil.isNullZeroString(mailData.getWmdTitle())) {
                        String title = mailData.getWmdTitle();
                        headerMap.put("Subject", new ArrayList<String>());
                        headerMap.get("Subject").add(mailBiz.mimeEncode(title, encode));
                        headerTypeList.add("Subject");
                    }

                    headerMap.put("Content-Type", new ArrayList<String>());
                    contentType = "text/plain; " + encode;
                    if (tempFileList != null && !tempFileList.isEmpty()) {
                        contentType = "multipart/mixed; boundary=\"--83513777964215396\"";
                    }
                    headerMap.get("Content-Type").add(contentType);
                    headerTypeList.add("Content-Type");
                } catch (Exception e) {
                    log__.error("ヘッダー部の作成に失敗");
                }
            }

            //Content-Typeに設定されている文字コード、MimeTypeを取得
            String charset = null;
            String boundary = null;
            boolean multiPart = false;
            boolean mimeTypeHtml = false;
            if (!StringUtil.isNullZeroString(contentType)) {
                charset = CommonBiz.getHeaderCharset(contentType);
                if (StringUtil.isNullZeroString(charset)) {
                    charset = bodyMdl.getWmbCharset();

                    if (!StringUtil.isNullZeroString(charset)) {
                        String[] separatorList = {";", " ", "\r", "\n"};
                        int sepIdx = 0;
                        for (String separator : separatorList) {
                            sepIdx = charset.indexOf(separator);
                            if (sepIdx > 0) {
                                charset = charset.substring(0, sepIdx);
                                break;
                            }
                        }
                    }
                }
                if (!StringUtil.isNullZeroString(charset)) {
                    charset = charset.replace("\"", "");
                    charset = charset.trim();
                }

                multiPart = contentType.toLowerCase().indexOf("multipart") >= 0;
                if (multiPart) {
                    int bdIndex = contentType.toLowerCase().indexOf("boundary=");
                    if (bdIndex >= 0) {
                        boundary = contentType.substring(bdIndex + 9);
                        boundary = boundary.replace("\"", "");
                        int isoIndex = boundary.toLowerCase().indexOf(";iso-2022-jp");
                        if (isoIndex > 0) {
                            boundary = boundary.substring(0, isoIndex);
                        }
                        boundary = "--" + boundary;
                    }
                } else {
                    mimeTypeHtml = contentType.toLowerCase().indexOf("text/html") >= 0;
                }
            }

            if (StringUtil.isNullZeroString(charset)) {
                charset = Encoding.ISO_2022_JP;
            } else {
                charset = charset.trim();
            }

            IOTools.isDirCheck(exportFilePath.getParent(), true);
            fos = new FileOutputStream(exportFilePath);
            if (charset.toUpperCase().equals(Encoding.CP932)) {
                pw = new CrlfTerminatedWriter(new OutputStreamWriter(fos, Encoding.WINDOWS_31J));
            } else {
                pw = new CrlfTerminatedWriter(new OutputStreamWriter(fos, charset));
            }

            //メールヘッダ情報をファイルに書き込み
            for (String headerKey : headerTypeList) {
                List<String> headerValueList = headerMap.get(headerKey);
                for (String headerValue : headerValueList) {
                    pw.println(headerKey + ": " + headerValue);
                }
            }
            headerTypeList = null;
            headerMap = null;

            //メール本文を書き込み
            if (!mimeTypeHtml) {
                pw.println("");
                if (multiPart) {
                    pw.println(boundary);
                    pw.println("Content-Type: text/plain; charset=" + charset);
                    pw.println("Content-Transfer-Encoding: 7bit");
                    pw.println("");
                    pw.println(bodyMdl.getWmbBody());
                } else {
                    if (contentTransferEncoding != null
                    && contentTransferEncoding.toLowerCase().equals("base64")) {
                        MailBiz mailBiz = new MailBiz();
                        if (StringUtil.isNullZeroString(charset)) {
                            charset = Encoding.ISO_2022_JP;
                        } else {
                            charset = charset.trim();
                        }
                        String mimeBody = mailBiz.createMime(bodyMdl.getWmbBody(), charset);
                        pw.println(mimeBody);
                    } else if (contentTransferEncoding != null
                    && contentTransferEncoding.toLowerCase().equals("quoted-printable")) {
                        ByteArrayOutputStream bos = null;
                        OutputStream os = null;
                        try {
                            bos = new ByteArrayOutputStream();
                            byte[] bodyBytes = bodyMdl.getWmbBody().getBytes(charset);
                            os = MimeUtility.encode(bos, "quoted-printable");
                            ((QPEncoderStream) os).write(bodyBytes);
                            os.flush();
                            pw.println(bos.toString());
                        } catch (Exception e) {
                            pw.println(bodyMdl.getWmbBody());
                        } finally {
                            if (os != null) {
                                os.close();
                            }
                            if (bos != null) {
                                bos.close();
                            }
                        }
                    } else {
                        pw.println(bodyMdl.getWmbBody());
                    }
                }
                bodyMdl = null;
            }

            //添付ファイル情報を書き込み
            if (!tempFileList.isEmpty()) {
                WmlBiz wmlBiz = new WmlBiz();
                ITempFileUtil tempUtil
                    = (ITempFileUtil) GroupSession.getContext().get(GSContext.TEMP_FILE_UTIL);
                String filePath = null;
                List<WmlTempfileModel> wmlTmpFileList = new ArrayList<WmlTempfileModel>();

                int fileIdx = 1;
                for (MailTempFileModel fileData : tempFileList) {

                    WmlTempfileModel wmlTmpFileMdl
                        = wmlBiz.getTempFileData(con, messageNum, fileData.getBinSid(), reqMdl);
                    wmlTmpFileList.add(wmlTmpFileMdl);
                    filePath
                        = tempUtil.getDownloadFileForWebmail(wmlTmpFileMdl, appRootPath).getPath();

                    pw.println("");

                    if (fileData.isHtmlMail() && fileIdx == 1) {
                        if (!mimeTypeHtml) {
                            pw.println(boundary);
                            pw.println("Content-Type: text/html; charset=" + fileData.getCharset());
                            pw.println("Content-Transfer-Encoding: 7bit");
                            pw.println("");
                        }
                        pw.println(IOTools.readText(filePath, charset));
                    } else {
                        pw.println(boundary);
                        String fileName = fileData.getFileName();
                        pw.println("Content-Type: "
                                + ContentType.getContentType(fileName) + ";");
                        pw.println(" name=\"" + MimeUtility.encodeText(fileName) + "\"");
                        pw.println("Content-Transfer-Encoding: base64");
                        pw.println("Content-Disposition: attachment;");
                        __writeTempFileName(pw, fileData);

                        pw.println("");

                        FileInputStream fis = null;
                        try {
                            fis = new FileInputStream(filePath);
                            byte[] buff = new byte[54];
                            int len = 0;
                            while ((len = fis.read(buff, 0, buff.length)) != -1) {
                                if (buff.length > len) {
                                    byte[] newBuff = new byte[len];
                                    System.arraycopy(buff, 0, newBuff, 0, len);
                                    buff = newBuff;
                                    newBuff = null;
                                }
                                pw.println(
                                    new String(Base64.encodeBase64(buff)));
                            }

                        } finally {
                            if (fis != null) {
                                fis.close();
                            }
                        }
                    }
                    fileIdx++;
                }

                if (multiPart && !StringUtil.isNullZeroString(boundary)) {
                    pw.println(boundary + "--");
                }

                for (WmlTempfileModel wmlTmpFileMdl : wmlTmpFileList) {
                    wmlTmpFileMdl.removeTempFile();
                }
                wmlTmpFileList = null;
            }

            pw.flush();

            WmlAccountDao accountDao = new WmlAccountDao(con);
            resultMdl.setAccountName(accountDao.getAccountName(mailData.getWacSid()));

            List<String> toList = new ArrayList<String>();
            List<String> ccList = new ArrayList<String>();
            List<String> bccList = new ArrayList<String>();
            WmlSendaddressDao sendAddressDao = new WmlSendaddressDao(con);
            List<WmlSendaddressModel> sendAddressList
                = sendAddressDao.select(resultMdl.getMessageNum());
            for (WmlSendaddressModel sendAddress : sendAddressList) {
                switch (sendAddress.getWsaType()) {
                    case GSConstWebmail.WSA_TYPE_TO:
                        toList.add(sendAddress.getWsaAddress());
                        break;
                    case GSConstWebmail.WSA_TYPE_CC:
                        ccList.add(sendAddress.getWsaAddress());
                        break;
                    case GSConstWebmail.WSA_TYPE_BCC:
                        bccList.add(sendAddress.getWsaAddress());
                        break;
                    default:
                }
            }
            resultMdl.setToList(toList);
            resultMdl.setCcList(ccList);
            resultMdl.setBccList(bccList);

            resultMdl.setFrom(mailData.getWmdFrom());
            resultMdl.setSubject(mailData.getWmdTitle());
            resultMdl.setSdate(mailData.getWmdSdate());
            resultMdl.setFilePath(exportFilePath);

        } finally {
            if (pw != null) {
                pw.close();
            }
            if (fos != null)  {
                fos.close();
            }
        }

        return resultMdl;
    }

    /**
     * <br>[機  能] メッセージの保存先フォルダを変更する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl リクエスト情報
     * @param reqMdl リクエスト情報
     * @return 結果メッセージ
     * @throws SQLException SQL実行時例外
     */
    public String changeFolderInMail(Connection con, Wml010ParamModel paramMdl, RequestModel reqMdl)
    throws SQLException {

        GsMessage gsMsg = new GsMessage(reqMdl);
        String message = null;

        try {
            long[] messageNum = getSelectMessageNum(paramMdl);

            if (messageNum == null || messageNum.length == 0) {
                return __createResultMessage(gsMsg.getMessage("wml.plz.select.mail"));
            }

            //アカウントの存在チェック
            String accountSid = paramMdl.getWmlViewAccount();
            if (StringUtil.isNullZeroString(accountSid)
            || !ValidateUtil.isNumber(accountSid)) {
                return __createResultMessage(gsMsg.getMessage("wml.191"));
            }

            //移動先ディレクトリの存在チェック
            long wdrSid = paramMdl.getWml010moveFolder();
            if (wdrSid <= 0) {
                return __createResultMessage(gsMsg.getMessage("wml.146"));
            }

            WebmailDao webmailDao = new WebmailDao(con);
            int wacSid = Integer.parseInt(accountSid);
            if (wdrSid <= 0 || !webmailDao.existDirectory(wacSid, wdrSid)) {
                return __createResultMessage(gsMsg.getMessage("wml.191"));
            }

            webmailDao.moveMail(messageNum, wdrSid);
            message = "success";

        } catch (Exception e) {
            log__.error("メールの移動に失敗しました。", e);
        } finally {
            if (message == null) {
                message = __createResultMessage(gsMsg.getMessage("wml.failed.movemail"));
            }
        }

        return message;
    }

    /**
     * <br>[機  能] 指定したメールの物理削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param wacSid アカウントSID
     * @param mailNumList 削除するメールのメッセージ番号
     * @param userSid セッションユーザSID
     * @throws SQLException SQL実行時例外
     */
    private void __deleteMailData(Connection con, String wacSid, long[] mailNumList, int userSid)
    throws SQLException {
        WmlSendaddressDao sendDao = new WmlSendaddressDao(con);
        WmlTempfileDao tempFileDao = new WmlTempfileDao(con);
        WmlLabelRelationDao labelRelationDao = new WmlLabelRelationDao(con);
        WmlHeaderDataDao headerDao = new WmlHeaderDataDao(con);
        WmlMailBodyDao bodyDao = new WmlMailBodyDao(con);
        WmlMailSendplanDao sendplanDao = new WmlMailSendplanDao(con);
        WmlMaildataDao mailDataDao = new WmlMaildataDao(con);
        UDate now = new UDate();

        for (long deleteMailNum : mailNumList) {
            sendDao.delete(deleteMailNum);
            tempFileDao.deleteTempFile(deleteMailNum, userSid, now);
            labelRelationDao.deleteToMailNum(deleteMailNum);
            headerDao.delete(deleteMailNum);
            bodyDao.delete(deleteMailNum);
            sendplanDao.delete(deleteMailNum);
            mailDataDao.delete(deleteMailNum);
        }

        if (mailNumList.length > 0) {
            log__.debug("物理削除したメールの件数 : " + mailNumList.length);

            //削除したメールが1件以上存在する場合、アカウントディスク容量の集計を行う
            WmlBiz wmlBiz = new WmlBiz();
            wmlBiz.updateAccountDiskSize(con, Integer.parseInt(wacSid));
        }
    }

    /**
     * <br>[機  能] メール表示順の初期設定および更新を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl リクエスト情報
     * @param userSid ユーザSID
     * @throws SQLException SQL実行時例外
     */
    private void __setMailListSort(Connection con, Wml010ParamModel paramMdl, int userSid)
    throws SQLException {

        int wacSid = _getViewAccountSid(paramMdl);

        if (paramMdl.getWml010searchFlg() != 1) {
            long wdrSid = paramMdl.getWml010viewDirectory();
            int wlbSid = paramMdl.getWml010viewLabel();
            if (wdrSid <= 0) {
                wdrSid = -1;
            }
            if (wlbSid <= 0) {
                wlbSid = -1;
            }

            WmlMaildataSortDao sortDao = new WmlMaildataSortDao(con);
            WmlMaildataSortModel sortMdl = sortDao.select(wacSid, userSid, wdrSid, wlbSid);
            if (paramMdl.getWml010sortKey() <= 0) {
                if (sortMdl == null) {
                    paramMdl.setWml010sortKey(Wml010Const.SORTKEY_SDATE);
                    paramMdl.setWml010order(GSConstWebmail.ORDER_ASC);
                } else {
                    switch (sortMdl.getWmsSortkey()) {
                        case GSConstWebmail.WMS_SKEY_FILE :
                            paramMdl.setWml010sortKey(Wml010Const.SORTKEY_TEMPFILE);
                            break;
                        case GSConstWebmail.WMS_SKEY_SUBJECT :
                            paramMdl.setWml010sortKey(Wml010Const.SORTKEY_TITLE);
                            break;
                        case GSConstWebmail.WMS_SKEY_FROM :
                            paramMdl.setWml010sortKey(Wml010Const.SORTKEY_MAILADDRESS);
                            break;
                        case GSConstWebmail.WMS_SKEY_DATE :
                            paramMdl.setWml010sortKey(Wml010Const.SORTKEY_SDATE);
                            break;
                        case GSConstWebmail.WMS_SKEY_READED :
                            paramMdl.setWml010sortKey(Wml010Const.SORTKEY_READED);
                            break;
                        case GSConstWebmail.WMS_SKEY_SIZE :
                            paramMdl.setWml010sortKey(Wml010Const.SORTKEY_SIZE);
                            break;
                        default:
                            paramMdl.setWml010sortKey(Wml010Const.SORTKEY_SDATE);
                    }

                    paramMdl.setWml010order(sortMdl.getWmsOrder());
                }
            } else {
                int wmsSkey = GSConstWebmail.WMS_SKEY_DATE;
                switch (paramMdl.getWml010sortKey()) {
                    case Wml010Const.SORTKEY_TEMPFILE :
                        wmsSkey = GSConstWebmail.WMS_SKEY_FILE;
                        break;
                    case Wml010Const.SORTKEY_TITLE :
                        wmsSkey = GSConstWebmail.WMS_SKEY_SUBJECT;
                        break;
                    case Wml010Const.SORTKEY_MAILADDRESS :
                        wmsSkey = GSConstWebmail.WMS_SKEY_FROM;
                        break;
                    case Wml010Const.SORTKEY_SDATE :
                        wmsSkey = GSConstWebmail.WMS_SKEY_DATE;
                        break;
                    case Wml010Const.SORTKEY_READED :
                        wmsSkey = GSConstWebmail.WMS_SKEY_READED;
                        break;
                    case Wml010Const.SORTKEY_SIZE :
                        wmsSkey = GSConstWebmail.WMS_SKEY_SIZE;
                        break;
                    default:
                }

                int wmsOrder = GSConstWebmail.ORDER_ASC;
                if (paramMdl.getWml010order() == GSConstWebmail.ORDER_DESC) {
                    wmsOrder = GSConstWebmail.ORDER_DESC;
                }

                boolean commit = false;
                try {
                    if (sortMdl == null) {
                        sortMdl = new WmlMaildataSortModel();
                        sortMdl.setWacSid(wacSid);
                        sortMdl.setUsrSid(userSid);
                        sortMdl.setWdrSid(wdrSid);
                        sortMdl.setWlbSid(wlbSid);
                        sortMdl.setWmsSortkey(wmsSkey);
                        sortMdl.setWmsOrder(wmsOrder);
                        sortDao.insert(sortMdl);
                    } else {
                        sortMdl.setWmsSortkey(wmsSkey);
                        sortMdl.setWmsOrder(wmsOrder);
                        sortDao.update(sortMdl);
                    }
                    con.commit();
                    commit = true;
                } catch (Exception e) {
                    log__.error("メール情報表示順の登録に失敗", e);
                } finally {
                    if (!commit) {
                        con.rollback();
                    }
                }
            }
        } else {
            WmlMaildataSortSearchDao sortDao = new WmlMaildataSortSearchDao(con);
            WmlMaildataSortSearchModel sortMdl = sortDao.select(wacSid, userSid);

            int sortKey = paramMdl.getWml010searchSortKey();
            int order = paramMdl.getWml010searchOrder();
            if (sortKey <= 0) {
                if (sortMdl == null) {
                    paramMdl.setWml010searchSortKey(Wml010Const.SORTKEY_SDATE);
                    paramMdl.setWml010searchOrder(GSConstWebmail.ORDER_ASC);
                } else {
                    switch (sortMdl.getWssSortkey()) {
                        case GSConstWebmail.WMS_SKEY_FILE :
                            paramMdl.setWml010searchSortKey(Wml010Const.SORTKEY_TEMPFILE);
                            break;
                        case GSConstWebmail.WMS_SKEY_SUBJECT :
                            paramMdl.setWml010searchSortKey(Wml010Const.SORTKEY_TITLE);
                            break;
                        case GSConstWebmail.WMS_SKEY_FROM :
                            paramMdl.setWml010searchSortKey(Wml010Const.SORTKEY_MAILADDRESS);
                            break;
                        case GSConstWebmail.WMS_SKEY_DATE :
                            paramMdl.setWml010searchSortKey(Wml010Const.SORTKEY_SDATE);
                            break;
                        case GSConstWebmail.WMS_SKEY_READED :
                            paramMdl.setWml010searchSortKey(Wml010Const.SORTKEY_READED);
                            break;
                        case GSConstWebmail.WMS_SKEY_SIZE :
                            paramMdl.setWml010searchSortKey(Wml010Const.SORTKEY_SIZE);
                            break;
                        default:
                            paramMdl.setWml010searchSortKey(Wml010Const.SORTKEY_SDATE);
                    }

                    paramMdl.setWml010searchOrder(sortMdl.getWssOrder());
                }
            } else {
                int wssSkey = GSConstWebmail.WMS_SKEY_DATE;
                switch (sortKey) {
                    case Wml010Const.SORTKEY_TEMPFILE :
                        wssSkey = GSConstWebmail.WMS_SKEY_FILE;
                        break;
                    case Wml010Const.SORTKEY_TITLE :
                        wssSkey = GSConstWebmail.WMS_SKEY_SUBJECT;
                        break;
                    case Wml010Const.SORTKEY_MAILADDRESS :
                        wssSkey = GSConstWebmail.WMS_SKEY_FROM;
                        break;
                    case Wml010Const.SORTKEY_SDATE :
                        wssSkey = GSConstWebmail.WMS_SKEY_DATE;
                        break;
                    case Wml010Const.SORTKEY_READED :
                        wssSkey = GSConstWebmail.WMS_SKEY_READED;
                        break;
                    case Wml010Const.SORTKEY_SIZE :
                        wssSkey = GSConstWebmail.WMS_SKEY_SIZE;
                        break;
                    default:
                }

                int wssOrder = GSConstWebmail.ORDER_ASC;
                if (order == GSConstWebmail.ORDER_DESC) {
                    wssOrder = GSConstWebmail.ORDER_DESC;
                }

                boolean commit = false;
                try {
                    if (sortMdl == null) {
                        sortMdl = new WmlMaildataSortSearchModel();
                        sortMdl.setWacSid(wacSid);
                        sortMdl.setUsrSid(userSid);
                        sortMdl.setWssSortkey(wssSkey);
                        sortMdl.setWssOrder(wssOrder);
                        sortDao.insert(sortMdl);
                    } else {
                        sortMdl.setWssSortkey(wssSkey);
                        sortMdl.setWssOrder(wssOrder);
                        sortDao.update(sortMdl);
                    }
                    con.commit();
                    commit = true;
                } catch (Exception e) {
                    log__.error("メール情報表示順の登録に失敗", e);
                } finally {
                    if (!commit) {
                        con.rollback();
                    }
                }
            }
        }
    }

    /**
     * <br>[機  能] アドレス情報をJSON形式の文字列に変換する
     * <br>[解  説]
     * <br>[備  考]
     * @param addressList アドレス情報一覧
     * @return アドレス情報文字列
     */
    private String __createAddressString(List<Wml010AddressModel> addressList) {

        StringBuilder adrString = new StringBuilder("");
        if (!addressList.isEmpty()) {
            int addressMaxIndex = addressList.size() - 1;
            for (int index = 0; index <= addressMaxIndex; index++) {
                Wml010AddressModel addrData = addressList.get(index);
                __setAddressString(adrString, addrData, index, 1);
                __setAddressString(adrString, addrData, index, 2);
                __setAddressString(adrString, addrData, index, 3);
            }
        }

        return adrString.toString();
    }

    /**
     * <br>[機  能] アドレス情報をJSON形式の文字列に変換し、指定されたStringBuilderに設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param sb StringBuilder
     * @param addrData アドレス情報
     * @param addrDataIdx アドレス情報のインデックス
     * @param adrNum メールアドレスの番号(1～3)
     */
    private void __setAddressString(StringBuilder sb, Wml010AddressModel addrData,
                                    int addrDataIdx, int adrNum) {

        String address = addrData.getMail1();
        if (adrNum == 2) {
            address = addrData.getMail2();
        } else if (adrNum == 3) {
            address = addrData.getMail3();
        }

        if (!StringUtil.isNullZeroString(address)) {
            if (sb.length() > 0) {
                sb.append(",");
            }

            sb.append("{");
            sb.append("\"NAME\":\"" + _escapeText(addrData.getUserName()) + "\",");
            sb.append("\"MAIL\":\"" + _escapeText(address) + "\"");
            sb.append("}");
        }
    }

    /**
     * <br>[機  能] 送信先リスト情報をJSON形式の文字列に変換する
     * <br>[解  説]
     * <br>[備  考]
     * @param destList 送信先リスト情報一覧
     * @return アドレス情報文字列
     */
    private String __createDestlistString(List<WmlDestlistModel> destList) {

        StringBuilder destlistString = new StringBuilder("");
        if (!destList.isEmpty()) {
            for (WmlDestlistModel destlistData : destList) {
                if (destlistString.length() > 0) {
                    destlistString.append(",");
                }

                destlistString.append("{");
                destlistString.append("\"SID\":\"" + destlistData.getWdlSid() + "\",");
                destlistString.append("\"NAME\":\""
                                                + _escapeTextView(destlistData.getWdlName())
                                                + "\"");
                destlistString.append("}");
            }
        }

        return destlistString.toString();
    }

    /**
     * <br>[機  能] URLEncodeを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param text 文字列
     * @return URLEncode後の文字列
     * @throws UnsupportedEncodingException 文字コードの指定が不正
     */
    private String __URLEncode(String text) throws UnsupportedEncodingException {
        String encodeText = null;
        if (text.indexOf(" ") >= 0) {
            StringTokenizer st = new StringTokenizer(text, " ");
            encodeText = URLEncoder.encode(st.nextToken(), "UTF-8");
            while (st.hasMoreTokens()) {
                encodeText += "%20" + URLEncoder.encode(st.nextToken(), "UTF-8");
            }
        } else {
            encodeText = URLEncoder.encode(text, "UTF-8");
        }

        return encodeText;
    }

    /**
     * <br>[機  能] エクスポート時のfilenameを設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param pw PrintWriter
     * @param fileData 添付ファイル情報
     * @throws UnsupportedEncodingException charsetが不正
     */
    private void __writeTempFileName(PrintWriter pw, MailTempFileModel fileData)
    throws UnsupportedEncodingException {

        String fileName = fileData.getFileName();
        char[] charArray = fileName.toCharArray();
        boolean ascii = true;
        for (char c : charArray) {
            ascii = StringUtil.isAscii(c);
            if (!ascii) {
                break;
            }
        }

        if (ascii) {
            pw.println(" filename=\"" + fileName + "\"");
        } else {
            pw.println(" fileName=\""
                    + MimeUtility.encodeText(fileName, Encoding.ISO_2022_JP, "B") + "\"");
        }

        pw.println("");
    }

    /**
     * <br>[機  能] メール本文のフォーマットを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param body メール本文
     * @param mailFlg メールフラグ
     * @return メール本文
     */
    public String formatBody(String body, boolean mailFlg) {
        if (!StringUtil.isNullZeroString(body)) {
            //URL、メールアドレスをリンクに変換する
            body = escapeBodyWithTransToLink(body);

            StringBuilder bodyBuild = new StringBuilder();
            Pattern pattern = Pattern.compile(
                            "[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+",
                            Pattern.CASE_INSENSITIVE);
            Matcher match = pattern.matcher(body);
            while (match.find()) {
                String address = match.group();
                int adrIndex = body.indexOf(address);
                bodyBuild.append(body.substring(0, adrIndex));

                if (mailFlg) {
                    bodyBuild.append("<a href=\"javascript:setAdrListAddress('', '"
                                    + address + "');\">");
                    bodyBuild.append(address + "</a>");
                } else {
                    bodyBuild.append("<a href=\"mailto:"
                                    + address + "\">");
                    bodyBuild.append(address + "</a>");
                }
                body = body.substring(adrIndex + address.length());
            }
            bodyBuild.append(body);
            body = bodyBuild.toString();
        }

        return body;
    }

    /**
     * <br>[機  能] 本文のURLをリンクに変換する
     * <br>[解  説]
     * <br>[備  考] escapeも同時に行う
     * @param body 本文
     * @return 変換後の本文
     */
    public String escapeBodyWithTransToLink(String body) {
        if (!StringUtil.isNullZeroString(body)) {
            Pattern pattern = Pattern.compile(StringUtil.URL_PATTERN,
                                            Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(body);
            String replaceStr = "";
            String bufReq = body.toString();

            while (matcher.find()) {
                String url = matcher.group();

                int urlIndex = bufReq.indexOf(url) + url.length();
                String str
                    = _escapeText(bufReq.substring(0, bufReq.indexOf(url)), true, false, true);
                String linkStr = "<A HREF=\"" + url + "\"";
                linkStr += " target=\"_blank\"";

                int cornIndex = url.indexOf("://") + 3;
                String urlBf = url.substring(0, cornIndex);
                String urlAf = url.substring(cornIndex);
                String viewUrl = urlAf.replace("/", "/<wbr>");
                viewUrl = viewUrl.replace("%", "%<wbr>");
                linkStr += ">" + urlBf + viewUrl + "</A>";

                replaceStr += str + linkStr;
                bufReq = bufReq.substring(urlIndex);
            }

            if (!StringUtil.isNullZeroString(bufReq)) {
                replaceStr += _escapeText(bufReq, true, false, true);
            }

            if (replaceStr.length() > 0) {
                body = replaceStr;
            }
        }

        return body;
    }

    /**
     * <br>[機  能] 送信メール情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param accountData アカウント情報
     * @param type 送信メール種別(返信など)
     * @param mailList メール情報
     * @param reqMdl リクエスト情報
     * @param appRootPath アプリケーションルートパス
     * @param tempRootDir テンポラリディレクトリ
     * @param readTempFile true:添付ファイル情報を取得 false:添付ファイル情報を取得しない
     * @return 送信メール情報
     * @throws SQLException SQL実行時例外
     * @throws ParseException メールアドレスのparseに失敗
     * @throws IOException 添付ファイルの作成に失敗
     * @throws IOToolsException 添付ファイルの作成に失敗
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public Wml010SendMailModel getSendMailData(Connection con, WmlAccountModel accountData,
                                                int type, List<Wml010MailModel> mailList,
                                                RequestModel reqMdl,
                                                String appRootPath, String tempRootDir,
                                                boolean readTempFile)
    throws SQLException, ParseException, IOException, IOToolsException, TempFileException {
        return getSendMailData(con, accountData,
                                        type, mailList,
                                        appRootPath, tempRootDir,
                                        reqMdl.getSession().getId(), reqMdl.getDomain(),
                                        readTempFile);
    }

    /**
     * <br>[機  能] 送信メール情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param accountData アカウント情報
     * @param type 送信メール種別(返信など)
     * @param mailList メール情報
     * @param appRootPath アプリケーションルートパス
     * @param tempRootDir テンポラリディレクトリ
     * @param tempDirId テンポラリディレクトリID
     * @param domain ドメイン
     * @param readTempFile true:添付ファイル情報を取得 false:添付ファイル情報を取得しない
     * @return 送信メール情報
     * @throws SQLException SQL実行時例外
     * @throws ParseException メールアドレスのparseに失敗
     * @throws IOException 添付ファイルの作成に失敗
     * @throws IOToolsException 添付ファイルの作成に失敗
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public Wml010SendMailModel getSendMailData(Connection con, WmlAccountModel accountData,
                                                int type, List<Wml010MailModel> mailList,
                                                String appRootPath, String tempRootDir,
                                                String tempDirId, String domain,
                                                boolean readTempFile)
    throws SQLException, ParseException, IOException, IOToolsException, TempFileException {
        WmlBiz wmlBiz = new WmlBiz();
        String wacSign = wmlBiz.getAccountSign(con, accountData.getWacSid());
        String sign = "";
        String reTopSign = "";
        if (type != Wml010Const.SEND_TYPE_EDIT
        && !StringUtil.isNullZeroString(wacSign)) {
                sign = "\r\n\r\n" + wacSign;
                sign = _escapeTextAreaInput(sign);
        }

        //返信時署名表示位置区分
        int signPointKbn = accountData.getWacSignPointKbn();
        //返信時署名表示区分
        int signDspKbn = accountData.getWacSignDspKbn();
        //署名セットフラグ
        boolean setSignFlg = false;
        //返信時署名表示フラグ
        boolean reSetSignFlg = true;
        if (signPointKbn == GSConstWebmail.SIGN_POINT_TOP) {
            //返信時署名、上にセット
            reTopSign = wacSign + "\r\n\r\n";
            reTopSign = _escapeTextAreaInput(reTopSign);
        }

        String to = "";
        String cc = "";
        String bcc = "";
        String subject = "";
        String content = "";
        String fileList = "";
        boolean timeSent = false;
        UDate timeSentDate = null;
        int compressFileType = 0;

        if (type == Wml010Const.SEND_TYPE_REPLY
            || type == Wml010Const.SEND_TYPE_REPLY_ALL
            || type == Wml010Const.SEND_TYPE_FORWARD
            || type == Wml010Const.SEND_TYPE_EDIT) {

            if (mailList.isEmpty()) {
                content = sign;
            } else {
                Wml010MailModel mailData = mailList.get(0);
                WmlSendaddressDao sendAddressDao = new WmlSendaddressDao(con);

                Wml010Dao dao010 = new Wml010Dao(con);
                int dirType = dao010.getDirType(mailData.getMailNum());
                boolean sendDir = dirType == GSConstWebmail.DIR_TYPE_SENDED
                                            || dirType == GSConstWebmail.DIR_TYPE_DRAFT
                                            || dirType == GSConstWebmail.DIR_TYPE_NOSEND;
                switch (type) {
                    case Wml010Const.SEND_TYPE_REPLY: //返信

                        if (signPointKbn == GSConstWebmail.SIGN_POINT_TOP
                                && signDspKbn == GSConstWebmail.SIGN_RECEIVE_DSP) {
                            //署名を先頭に追加
                            content = reTopSign;
                            setSignFlg = true;
                        }
                        reSetSignFlg = signDspKbn == GSConstWebmail.SIGN_RECEIVE_DSP;

                        to = __getReplyToAdderss(con, mailData);
                        cc = _escapeTextBox(accountData.getWacAutocc());
                        bcc = _escapeTextBox(accountData.getWacAutobcc());
                        subject = "Re: " + _escapeTextBox(mailData.getSubject());

//                        content += "\n";
//                        content += UDateUtil.getSlashYYMD(mailData.getDate()) + " "
//                                + UDateUtil.getSeparateHMS(mailData.getDate());
//                        content += ", " + mailData.getFrom();
//                        content += " wrote\n\n";
                        content += __createBodyUpperPart(mailData);
                        content = _escapeTextAreaInput(content);

                        break;
                    case Wml010Const.SEND_TYPE_REPLY_ALL: //全員に返信
                        List<WmlSendaddressModel> addressList
                            = sendAddressDao.select(mailData.getMailNum());
                        if (sendDir) {
                            for (WmlSendaddressModel addressModel : addressList) {
                                switch (addressModel.getWsaType()) {
                                    case GSConstWebmail.WSA_TYPE_TO:
                                        if (to.length() > 0) {
                                            to += ",";
                                        }
                                        to += _escapeTextBox(addressModel.getWsaAddress());
                                        break;
                                    case GSConstWebmail.WSA_TYPE_CC:
                                        if (cc.length() > 0) {
                                            cc += ",";
                                        }
                                        cc += _escapeTextBox(addressModel.getWsaAddress());
                                        break;
                                    case GSConstWebmail.WSA_TYPE_BCC:
                                        if (bcc.length() > 0) {
                                            bcc += ",";
                                        }
                                        bcc += _escapeTextBox(addressModel.getWsaAddress());
                                        break;
                                    default:
                                }
                            }
                        } else {
                            to = __getReplyToAdderss(con, mailData);
                            bcc = _escapeTextBox(accountData.getWacAutobcc());

                            String autoCc = accountData.getWacAutocc();
                            for (WmlSendaddressModel addressModel : addressList) {
                                if (addressModel.getWsaType() == GSConstWebmail.WSA_TYPE_TO
                                || addressModel.getWsaType() == GSConstWebmail.WSA_TYPE_CC) {
                                    if (!addressModel.getWsaAddress().equals(autoCc)) {
                                        if (cc.length() > 0) {
                                            cc += ",";
                                        }
                                        cc += _escapeTextBox(addressModel.getWsaAddress());
                                    }
                                }
                            }

                            if (cc.length() > 0
                            && !StringUtil.isNullZeroString(autoCc)) {
                                cc += ",";
                            }
                            cc += _escapeTextBox(autoCc);
                        }

                        if (signPointKbn == GSConstWebmail.SIGN_POINT_TOP
                                && signDspKbn == GSConstWebmail.SIGN_RECEIVE_DSP) {
                            //署名を先頭に追加
                            content = reTopSign;
                            setSignFlg = true;
                        }
                        reSetSignFlg = signDspKbn == GSConstWebmail.SIGN_RECEIVE_DSP;

                        subject = "Re: " + _escapeTextBox(mailData.getSubject());
//                        content += "\n";
//                        content += UDateUtil.getSlashYYMD(mailData.getDate()) + " "
//                                + UDateUtil.getSeparateHMS(mailData.getDate());
//                        content += ", " + mailData.getFrom();
//                        content += " wrote\n\n";
                        content += __createBodyUpperPart(mailData);
                        content = _escapeTextAreaInput(content);

                        break;

                    case Wml010Const.SEND_TYPE_FORWARD: //転送
                        subject = "Fw: " + _escapeTextBox(mailData.getSubject());

                        content += __createBodyUpperPart(mailData);
                        content = _escapeTextAreaInput(content);

                        break;

                    case Wml010Const.SEND_TYPE_EDIT: //編集

                        List<WmlSendaddressModel> editAddressList
                            = sendAddressDao.select(mailData.getMailNum());

                        for (WmlSendaddressModel addressModel : editAddressList) {
                            switch (addressModel.getWsaType()) {
                                case GSConstWebmail.WSA_TYPE_TO :
                                    if (to.length() > 0) {
                                        to += ",";
                                    }
                                    to += _escapeTextBox(addressModel.getWsaAddress());
                                    break;
                                case GSConstWebmail.WSA_TYPE_CC :
                                    if (cc.length() > 0) {
                                        cc += ",";
                                    }
                                    cc += _escapeTextBox(addressModel.getWsaAddress());
                                    break;
                                case GSConstWebmail.WSA_TYPE_BCC :
                                    if (bcc.length() > 0) {
                                        bcc += ",";
                                    }
                                    bcc += _escapeTextBox(addressModel.getWsaAddress());
                                    break;
                                default:
                            }
                        }

                        subject = _escapeTextBox(mailData.getSubject());
                        break;

                    default:
                }

                if (!StringUtil.isNullZeroString(mailData.getBody())) {
                    boolean sendTypeHtml
                        = accountData.getWacSendMailtype() == GSConstWebmail.WAC_SEND_MAILTYPE_HTML;
                    String body = mailData.getBody();

                    if (type != Wml010Const.SEND_TYPE_EDIT) {

                        String mailQuotes = WmlBiz.getMailQuotes(accountData.getWacQuotes());
                        if (!StringUtil.isNullZeroString(mailQuotes)) {
                            mailQuotes = mailQuotes + " ";
                            if (sendTypeHtml) {
                                body = _escapeText(body, true, true, false);
                                body = StringUtil.substitute(body, "<BR>",
                                                                        "<BR>".concat(mailQuotes));
                            } else {
                                body = _escapeTextAreaInput(
                                        StringUtil.substitute(body, "\n", "\n".concat(mailQuotes)));
                            }

                            body = mailQuotes + body;
                        }
                    } else {
                        if (sendTypeHtml) {
                            body = _escapeText(body, true, true, false);
                        } else {
                            body =  _escapeTextAreaInput(body);
                        }
                    }

                    content += body;
                }
                if (!setSignFlg && reSetSignFlg) {
                    //文末に署名セット
                    content += sign;
                }

                //添付ファイル情報
                if ((type == Wml010Const.SEND_TYPE_FORWARD
                    || type == Wml010Const.SEND_TYPE_EDIT)
                && !mailData.getTempFileList().isEmpty()) {

                    //テンポラリディレクトリパス
                    String tempDir =
                        IOTools.replaceFileSep(getSendTempDir(tempRootDir, tempDirId));
                    UDate date = new UDate();
                    String dateStr = date.getDateString();

                    for (MailTempFileModel fileData : mailData.getTempFileList()) {

                        //ファイルの連番を取得する
                        int fileNum = Cmn110Biz.getFileNumber(tempDir, dateStr);
                        fileNum++;

                        //添付ファイル名
                        String fileName = fileData.getFileName();
                        long fileSize = fileData.getFileSize();

                        StringBuilder filePath = new StringBuilder("");
                        filePath.append(dateStr);
                        filePath.append(StringUtil.toDecFormat(fileNum, "000"));
                        filePath.append(GSConstCommon.ENDSTR_SAVEFILE);
                        String saveFilePath = filePath.toString();

                        CommonBiz cmnBiz = new CommonBiz();

                        //添付ファイル情報を取得する。
                        long time = System.currentTimeMillis();
                        WmlTempfileModel wtfMdl = cmnBiz.getBinInfoForWebmail(
                                con, mailData.getMailNum(), fileData.getBinSid(),
                                domain);
                        log__.debug("WebMail添付ファイル情報取得時間" + (System.currentTimeMillis() - time));

                        //添付ファイルをテンポラリディレクトリにコピーする。
                        time = System.currentTimeMillis();
                        cmnBiz.saveTempFileForWebmail(
                                dateStr, wtfMdl, appRootPath, tempDir, fileNum);
                        log__.debug("WebMail添付ファイルテンポラリコピー時間"
                                    + (System.currentTimeMillis() - time));

                        if (fileList.length() > 0) {
                            fileList += ",";
                        }

                        fileList += "{";
                        fileList += "\"fileName\" : \""
//                                + Wml010Biz._escapeText(fileName) + "\",";
                                + _escapeTextView(fileName) + "\",";
                        fileList += "\"fileSize\" : \"";
                        if (fileSize < 1024) {
                            fileList += fileSize + " Byte\",";
                        } else {
                            BigDecimal decFileSize = new BigDecimal(fileSize);
                            decFileSize = decFileSize.divide(new BigDecimal(1024), 1,
                                                            BigDecimal.ROUND_HALF_UP);
                            fileList += decFileSize.toString() + " KByte\",";
                        }
                        fileList += "\"saveFileName\" : \""
                                + saveFilePath + "\"";
                        fileList += "}";

                    }
                }

                //送信予定を設定
                timeSent = mailData.getSendPlanKbn() == GSConstWebmail.WAC_TIMESENT_LATER;
                timeSentDate = mailData.getSendPlanDate();
                compressFileType = mailData.getSendPlanCompressFile();
            }

        } else {
            to = _escapeTextBox(accountData.getWacAutoto());
            cc = _escapeTextBox(accountData.getWacAutocc());
            bcc = _escapeTextBox(accountData.getWacAutobcc());
            content = sign;
        }

        Wml010SendMailModel sendData = new Wml010SendMailModel();
        sendData.setTo(to);
        sendData.setCc(cc);
        sendData.setBcc(bcc);
        sendData.setSubject(subject);
        sendData.setContent(content);
        sendData.setFileList(fileList);
        sendData.setTimeSent(timeSent);
        sendData.setSendPlanDate(timeSentDate);
        sendData.setCompressFileType(compressFileType);
        return sendData;
    }

    /**
     * <br>[機  能] 返信/転送時にメール本文へ追加する情報を作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param mailData メール情報
     * @return メール本文へ追加する情報(文字列)
     */
    private String __createBodyUpperPart(Wml010MailModel mailData) {
        String upperPart = "\n-------- Original Message --------";
        upperPart += "\nSubject: " + NullDefault.getString(mailData.getSubject(), "");
        upperPart += "\nDate: "
                + UDateUtil.getSlashYYMD(mailData.getDate()) + " "
                + UDateUtil.getSeparateHMS(mailData.getDate());
        upperPart += "\nFrom: " + NullDefault.getString(mailData.getFrom(), "");
        upperPart += "\nTo: ";
        if (!mailData.getSendAddress().getToList().isEmpty()) {
            upperPart += mailData.getSendAddress().getToList().get(0);
        }
        upperPart += "\n\n\n";

        return upperPart;
    }

    /**
     * <br>[機  能] エクスポート対象メールのチェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @param paramMdl リクエスト情報
     * @return 出力したファイルのパス
     * @throws SQLException SQL実行時例外
     */
    public boolean checkExport(
            Connection con,
            RequestModel reqMdl,
            Wml010ParamModel paramMdl)
    throws SQLException {

        boolean result = false;

        try {
            long messageNum = getSelectMessageNum(paramMdl)[0];
            //メール情報を取得
            WmlMaildataDao mailDataDao = new WmlMaildataDao(con);
            WmlMaildataModel mailData = mailDataDao.select(messageNum);
            if (mailData != null) {
                result = true;
            }
        } catch (SQLException e) {
        }

        return result;
    }

    /**
     * <br>[機  能] メール情報をpdf形式のファイルへ出力する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @param paramMdl リクエスト情報
     * @param appRootPath アプリケーションルートパス
     * @param outTempDir テンポラリディレクトリ
     * @return 出力したファイルのパス
     * @throws SQLException SQL実行時例外
     * @throws IOException ファイルの書き出しに失敗
     * @throws IOToolsException テンポラリディレクトリの削除に失敗
     * @throws TempFileException 添付ファイル情報の取得に失敗
     */
    public WmlPdfModel createExportPdfFile(
            Connection con,
            RequestModel reqMdl,
            Wml010ParamModel paramMdl,
            String appRootPath,
            String outTempDir)
    throws SQLException, IOException, IOToolsException, TempFileException {
        WmlPdfModel pdfMdl = new WmlPdfModel();

            long messageNum = getSelectMessageNum(paramMdl)[0];

            //メール情報を取得
            WmlMaildataDao mailDataDao = new WmlMaildataDao(con);
            WmlMaildataModel mailData = mailDataDao.select(messageNum);

            //本文の情報を取得
            WmlMailBodyDao bodyDao = new WmlMailBodyDao(con);
            WmlMailBodyModel bodyMdl = bodyDao.select(messageNum);

            //添付ファイル情報を取得
            List<MailTempFileModel> tempFileList = new ArrayList<MailTempFileModel>();
            if (mailData.getWmdTempflg() == GSConstWebmail.TEMPFLG_EXIST) {
                WebmailDao wmlDao = new WebmailDao(con);
                tempFileList = wmlDao.getTempFileList(messageNum);
            }

            Wml010Dao wmlDao = new Wml010Dao(con);
            String accName = wmlDao.getAccName(_getViewAccountSid(paramMdl));
            //アカウント名
            pdfMdl.setAccName(accName);
            //タイトル
            pdfMdl.setTitle(mailData.getWmdTitle());
            //送信者
            pdfMdl.setSender(mailData.getWmdFrom());
            //送信日時
            String strSdate =
                    UDateUtil.getSlashYYMD(mailData.getWmdSdate())
                    + "  "
                    + UDateUtil.getSeparateHMS(mailData.getWmdSdate());
            pdfMdl.setDate(strSdate);
            //添付ファイルフラグ
            pdfMdl.setTempFlag(mailData.getWmdTempflg());

            //送信アドレス一覧を取得する
            Wml010SendAddrModel addrMdl =
                    wmlDao.getSendAddress(_getViewAccountSid(paramMdl), messageNum);
            //宛先
            pdfMdl.setAtesaki(__getDispAddrs(addrMdl.getToList()));
            //CC
            pdfMdl.setAtesakiCC(__getDispAddrs(addrMdl.getCcList()));
            //BCC
            pdfMdl.setAtesakiBCC(__getDispAddrs(addrMdl.getBccList()));
            //本文
            pdfMdl.setMain(bodyMdl.getWmbBody());

            //添付ファイル
            if (mailData.getWmdTempflg() == 1) {
                String strFileList = new String();
                for (int i = 0; i < tempFileList.size(); i++) {
                    strFileList += tempFileList.get(i).getFileName()
                            + tempFileList.get(i).getFileSizeDsp();
                    if (i != tempFileList.size() - 1) {
                        strFileList += " , ";
                    }
                }
                pdfMdl.setTempFile(strFileList);
            }

            String labelName = wmlDao.getLabelName(messageNum);
            //ラベル
            pdfMdl.setLabel(labelName);

            OutputStream oStream = null;

            //ファイル名用の送信時間を設定
            String mailDate = UDateUtil.getYYMD(mailData.getWmdSdate()) + "_"
                    + UDateUtil.getSeparateHMS(mailData.getWmdSdate());
            String mailName = mailDate + "_" + mailData.getWmdTitle();
            //使用可能なファイル名かチェック
            WmlBiz wmlBiz = new WmlBiz();
            mailName = wmlBiz.fileNameCheck(mailName);

            String outBookName = mailName + ".pdf"; // 送信日時_件名.pdf
            pdfMdl.setFileName(outBookName);

            String saveFileName = mailData.getWmdSdate().getTimeMillis() + ".pdf";
            pdfMdl.setSaveFileName(saveFileName);
            try {
                IOTools.isDirCheck(outTempDir, true);
                oStream = new FileOutputStream(outTempDir + saveFileName);
                WmlPdfUtil util = new WmlPdfUtil();
                util.createSmalPdf(pdfMdl, appRootPath, oStream);
            } catch (Exception e) {
                log__.error("メール内容PDF出力に失敗しました。", e);
            } finally {
                if (oStream != null) {
                    oStream.flush();
                    oStream.close();
                }
            }

        return pdfMdl;
    }

    /**
     * <br>[機  能] アカウントのテーマを設定します
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param wacSid アカウントSID
     * @throws SQLException SQL実行時例外
     */
    public void setAccountTheme(Connection con, Wml010ParamModel paramMdl, int wacSid)
    throws SQLException {
        //テーマを設定
        WmlAccountDao accountDao = new WmlAccountDao(con);
        WmlAccountModel accountData = accountDao.select(wacSid);
        int theme = accountData.getWacTheme();
        if (theme != GSConstWebmail.WAC_THEME_NOSET) {
            CmnThemeDao themeDao = new CmnThemeDao(con);
            CmnThemeModel themeData = themeDao.select(theme);
            paramMdl.setWml010theme(themeData.getCtmId());
        }
    }

    /**
     * <br>[機  能] アカウントの送信時制限を設定します
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param wacSid アカウントSID
     * @throws SQLException SQL実行時例外
     */
    public void setAccountSendConf(Connection con, Wml010ParamModel paramMdl, int wacSid)
    throws SQLException {
        //[宛先、添付ファイルの確認]を設定
        WmlAdmConfDao wacDao = new WmlAdmConfDao(con);
        WmlAdmConfModel admConfMdl = wacDao.selectAdmData();

        if (admConfMdl != null
        && admConfMdl.getWadPermitKbn() == GSConstWebmail.PERMIT_ADMIN) {
            paramMdl.setWml010checkAddress(admConfMdl.getWadCheckAddress());
            paramMdl.setWml010checkFile(admConfMdl.getWadCheckFile());
        } else {
            WmlAccountDao accountDao = new WmlAccountDao(con);
            WmlAccountModel accountData = accountDao.select(wacSid);
            paramMdl.setWml010checkAddress(accountData.getWacCheckAddress());
            paramMdl.setWml010checkFile(accountData.getWacCheckFile());
        }
    }

    /**
     * <br>[機  能] アドレスのリストから表示用にコンマ区切りの一行表示に変換する
     * <br>[解  説]
     * <br>[備  考]
     * @param addrList アドレスリスト
     * @return 変換したアドレス
     */
    private String __getDispAddrs(List<String> addrList) {
        String ret = null;

        for (int i = 0; i < addrList.size(); i++) {
            if (i == 0) {
                ret = addrList.get(i).trim();
            } else {
                ret += ",";
                ret += addrList.get(i).trim();
            }
        }
        return NullDefault.getString(ret, "");
    }

    /**
     * <br>[機  能] 送信メール情報(Wml010SendMailModel)を生成する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param mailData 送信メール情報
     * @param sendMailEncode 送信メールの文字コード
     * @param gsContext GroupSession共通情報
     * @param userSid ユーザSID
     * @param appRootPath アプリケーションルートパス
     * @param tempRootDir テンポラリルートディレクトリ
     * @param tempDirId テンポラリディレクトリID
     * @param accountData アカウント情報
     * @return 送信メール情報(SmtpSendModel)
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException 添付ファイルの操作に失敗
     * @throws Exception 送信メール情報(SmtpSendModel)の作成に失敗
     */
    public Wml010SendMailModel createSendMailData(Connection con,
                                                    Wml010SendMailModel mailData,
                                                    String sendMailEncode,
                                                    GSContext gsContext, int userSid,
                                                    String appRootPath, String tempRootDir,
                                                    String tempDirId,
                                                    WmlAccountModel accountData)
    throws SQLException, IOToolsException, Exception {

        WmlBiz wmlBiz = new WmlBiz();
        Wml010SendMailModel resultMdl  = new Wml010SendMailModel();
        String zipFilePath = null;
        SmtpSendModel sendData = new SmtpSendModel();
        sendData.setCon(con);
        sendData.setWdrSid(
                wmlBiz.getDirectorySid(con, accountData.getWacSid(),
                                                    GSConstWebmail.DIR_TYPE_SENDED));
        sendData.setGsContext(gsContext);
        sendData.setUserSid(userSid);
        sendData.setHtmlMail(mailData.isHtmlMail());
        sendData.setSubject(mailData.getSubject());
        sendData.setFrom(accountData.getWacAddress());

        //送信先(宛先, CC, BCC)
        WmlAdmConfDao admConfDao = new WmlAdmConfDao(con);
        WmlAdmConfModel admConfMdl = admConfDao.selectAdmData();
        __bccConvert(admConfMdl, mailData, sendData);

        sendData.setTimeSent(mailData.isTimeSent());
        sendData.setSendPlanDate(mailData.getSendPlanDate());

        String body = mailData.getContent();

        if (sendData.isHtmlMail()) {
            body = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">"
                + "<html>"
                + "<head>"
                + "<meta content=\"text/html; charset=" + sendMailEncode
                + "\" http-equiv=\"Content-Type\">"
                + "</head>"
                + "<body>"
                + body
                + "</body>"
                + "</html>";
        }
        sendData.setBody(body);

        Wml010Dao dao010 = new Wml010Dao(con);
        long mailNum = mailData.getSendMessageNum();
        if (mailData.getSendMailType() == Wml010Const.SEND_TYPE_REPLY
        || mailData.getSendMailType() == Wml010Const.SEND_TYPE_REPLY_ALL) {

            List<String> refMsgKey = dao010.getHeaderValue(mailNum, "Message-ID");
            if (refMsgKey != null && !refMsgKey.isEmpty()) {
                String reference = "";
                List<String> referenceList = dao010.getHeaderValue(mailNum, "References");
                if (referenceList != null && !referenceList.isEmpty()) {
                    for (String beforeReference : referenceList) {
                        if (reference.length() > 0) {
                            reference += " ";
                        }
                        reference += beforeReference;
                    }
                }

                if (reference.length() > 0) {
                    reference += " ";
                }
                reference += refMsgKey.get(0);
                sendData.addHeaderData("References", reference);
            }
        }

        List<WmlMailFileModel> tempFileList = getTempFileList(tempRootDir, tempDirId);

        //添付ファイル有り、かつ [添付ファイル自動圧縮 = 圧縮する]、かつ "自動圧縮しない"が未選択 の場合
        //ZIP圧縮したメールを送信する
        boolean archiveMail = false;
        String archivePassword = null;

        if (tempFileList != null && !tempFileList.isEmpty()) {
            if (mailData.getCompressFileType() != GSConstWebmail.WSP_COMPRESS_FILE_NOSET) {
                archiveMail
                    = mailData.getCompressFileType() == GSConstWebmail.WSP_COMPRESS_FILE_COMPRESS;
            } else {
                boolean admConfFlg
                    = (admConfMdl != null
                        && admConfMdl.getWadPermitKbn() == GSConstWebmail.PERMIT_ADMIN);
                if (admConfFlg) {
                    archiveMail
                        = admConfMdl.getWadCompressFile()
                                == GSConstWebmail.WAD_COMPRESS_FILE_COMPRESS;
                } else {
                    archiveMail
                        = accountData.getWacCompressFile()
                                == GSConstWebmail.WAD_COMPRESS_FILE_COMPRESS;
                }
            }

            if (archiveMail) {
                String tempDir = getSendTempDir(tempRootDir, tempDirId);
                String compressDir = tempDir + "/compress/";
                IOTools.isDirCheck(compressDir, true);

                String archiveDir = compressDir + "/archive/";
                IOTools.isDirCheck(archiveDir, true);

                ArrayList<File> sourceFileList = new ArrayList<File>();
                for (WmlMailFileModel fileData : tempFileList) {
                    File sourceFile = new File(archiveDir + fileData.getFileName());
                    IOTools.copyBinFile(fileData.getFilePath(), sourceFile.getPath());
                    sourceFileList.add(sourceFile);
                }

                try {
                    archivePassword = RandomPassword.createPassword();
                    ZipParameters zipParam = new ZipParameters();
                    zipParam.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
                    zipParam.setEncryptFiles(true);
                    zipParam.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
//AES
//                    zipParam.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
//                    zipParam.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
//                    zipParam.setEncryptFiles(true);
//                    zipParam.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
//                    zipParam.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
                    zipParam.setPassword(archivePassword);

                    UDate now = new UDate();
                    String zipFileName = now.getTimeStamp() + "_attach.zip";
                    zipFilePath = compressDir + zipFileName;
                    if (IOTools.isFileCheck(compressDir, zipFileName, false)) {
                        IOTools.deleteFile(zipFilePath);
                    }

                    ZipFile zipFile = new ZipFile(zipFilePath);
                    zipFile.setFileNameCharset("Windows-31J");
                    zipFile.createZipFile(sourceFileList, zipParam);

                    IOTools.deleteDir(archiveDir);
                    WmlMailFileModel archiveFileData = new WmlMailFileModel();
                    archiveFileData.setFileName(zipFileName);
                    archiveFileData.setFilePath(zipFilePath);
                    tempFileList.clear();
                    tempFileList.add(archiveFileData);
                } catch (ZipException e) {
                    log__.error("送信メール 添付ファイル圧縮に失敗", e);
                    try {
                        if (zipFilePath != null) {
                            IOTools.deleteFile(zipFilePath);
                        }

                        if (compressDir != null) {
                            IOTools.deleteDir(compressDir);
                        }
                    } catch (Exception zipe) {
                    }
                    throw e;
                }
            }
        }

        sendData.setTempFileList(tempFileList);

        resultMdl.setSendMessageNum(mailNum);
        resultMdl.setSendData(sendData);
        resultMdl.setArchiveMail(archiveMail);
        resultMdl.setArchivePassword(archivePassword);
        resultMdl.setArchiveFilePath(zipFilePath);

        return resultMdl;
    }

    /**
     * <br>[機  能] 送信メールのメールサイズチェックを生成する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param gsContext GroupSession共通情報
     * @param userSid ユーザSID
     * @param appRootPath アプリケーションルートパス
     * @param tempRootDir テンポラリルートディレクトリ
     * @param reqMdl リクエスト情報
     * @param mailData 送信メール情報
     * @return エラーメッセージ
     * @throws Exception 送信メール情報(SmtpSendModel)の作成に失敗
     */
    public boolean checkSendMailSize(Connection con,
                                                    GSContext gsContext, int userSid,
                                                    String appRootPath, String tempRootDir,
                                                    RequestModel reqMdl,
                                                    Wml010SendMailModel mailData)
    throws Exception {
        boolean result = true;

        int sendWacSid = mailData.getWacSid();
        WmlAccountDao accountDao = new WmlAccountDao(con);
        WmlAccountModel accountData = accountDao.select(sendWacSid);
        String sendMailEncode = WmlBiz.getSendEncode(accountData);

        String archiveFilePath = null;
        try {
            Wml010SendMailModel wml010SendData
                = createSendMailData(con, mailData,
                                                sendMailEncode,
                                                gsContext, userSid,
                                                appRootPath, tempRootDir,
                                                reqMdl.getSession().getId(),
                                                accountData);
            archiveFilePath = wml010SendData.getArchiveFilePath();

            WmlBiz wmlBiz = new WmlBiz();
            result = wmlBiz.checkSendmailSize(con,
                                                                sendWacSid,
                                                                wml010SendData.getSendData(),
                                                                sendMailEncode);
        } finally {
            if (archiveFilePath != null) {
                IOTools.deleteFile(archiveFilePath);
            }
        }

        return result;
    }

    /**
     * <br>[機  能] 指定されたアカウントの署名情報(文字列)を作成します。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param wacSid アカウントSID
     * @return 署名情報(文字列)
     * @throws SQLException SQL実行時例外
     */
    public String createAccountSignText(Connection con, int wacSid) throws SQLException {
        StringBuilder sb = new StringBuilder("");

        WmlAccountSignDao signDao = new WmlAccountSignDao(con);
        List<WmlAccountSignModel> signList = signDao.getSignList(wacSid);
        for (int index = 0; index < signList.size(); index++) {
            WmlAccountSignModel signModel = signList.get(index);
            if (index > 0) {
                sb.append(",");
            }
            sb.append("{");
            sb.append("\"ID\" : " + signModel.getWsiNo() + ",");
            sb.append("\"NAME\" : \""
                        + _escapeTextInput(
                                StringUtilHtml.transToHTml(signModel.getWsiTitle()))
                        + "\",");
            sb.append("\"DEF\" : " + signModel.getWsiDef());
            sb.append("}");
        }

        return sb.toString();
    }

    /**
     * <br>[機  能] 送信メール本文の署名を選択された署名へ変更します。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @return 送信メール本文
     * @throws SQLException SQL実行時例外
     * @throws UnsupportedEncodingException 送信メール本文の変換に失敗
     */
    public String replaceSendBodySign(Connection con, Wml010ParamModel paramMdl)
    throws SQLException, UnsupportedEncodingException {
        String body = NullDefault.getString(paramMdl.getWml010sendContent(), "");
        body = body.replaceAll("\r\n", "\n");

        int wacSid = paramMdl.getWml010sendAccount();
        WmlAccountDao accountDao = new WmlAccountDao(con);
        WmlAccountModel accountMdl = accountDao.select(wacSid);

        boolean signTopFlg = false;
        int sendType = paramMdl.getWml010sendMailType();
        if (sendType == Wml010Const.SEND_TYPE_REPLY
        || sendType == Wml010Const.SEND_TYPE_REPLY_ALL) {
            signTopFlg = accountMdl.getWacSignPointKbn() == GSConstWebmail.SIGN_POINT_TOP;
        }

        WmlAccountSignDao signDao = new WmlAccountSignDao(con);
        WmlAccountSignModel signMdl = signDao.select(wacSid, paramMdl.getWml010sendSignOld());
        String oldSign = __getSign(signMdl, paramMdl);
        signMdl = signDao.select(wacSid, paramMdl.getWml010sendSign());
        if (signMdl != null) {
            String newSign = __getSign(signMdl, paramMdl);
            boolean htmlMail = paramMdl.getWml010sendMailHtml() == Wml010Const.SEND_HTMLMAIL_HTML;
            if (signTopFlg) {
                if (oldSign != null && body.indexOf(oldSign) >= 0) {
//                        body =  body.replaceFirst(oldSign + "\n\n",
//                                                            signMdl.getWsiSign() + "\r\n\r\n");
                    body =  body.replaceFirst(oldSign , newSign);
                } else {
                    if (htmlMail && body.replaceAll("\n", "").startsWith("<p>")) {
                        body = "<p>"
                                    + newSign + "\r\n\r\n"
                                    + body.substring(3, body.length());
                    } else {
                        body =  newSign + "\r\n\r\n" + body;
                    }
                }
            } else {
                int signIndex = NullDefault.getString(body, "").lastIndexOf(oldSign);
                if (signIndex > 0) {
                    body = body.substring(0,  signIndex)
                            + newSign
                            + body.substring(signIndex + oldSign.length());
                } else {
                    if (htmlMail && body.replaceAll("\n", "").endsWith("</p>")) {
                        int lastIndex = body.lastIndexOf("</p>");
                        body = body.substring(0, lastIndex)
                                    + "\n" + newSign
                                    + body.substring(lastIndex, body.length());
                    } else {
                        body += "\n" + newSign;
                    }
                }
            }
        }

        body =  _escapeTextAreaInput(body);
        body = __URLEncode(body);
        return body;
    }

    /**
     * <br>[機  能] 署名を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param signMdl 署名情報
     * @param paramMdl パラメータ情報
     * @return 署名
     */
    private String __getSign(WmlAccountSignModel signMdl, Wml010ParamModel paramMdl) {
        if (signMdl == null) {
            return null;
        }

        String sign = signMdl.getWsiSign();
        sign = sign.replaceAll("\r\n", "\n");
        if (paramMdl.getWml010sendMailHtml() == Wml010Const.SEND_HTMLMAIL_HTML) {
            String htmlSign = "";
            StringTokenizer st = new StringTokenizer(sign, "\n", true);
            String signLine = null;
            while (st.hasMoreElements()) {
                signLine = st.nextToken().replace("\n", "");
                if (signLine.length() == 0 && st.hasMoreElements()) {
                    htmlSign += "<br />";
                    continue;
                }
                signLine = __transToHtml(signLine);
                htmlSign += signLine;
            }
            sign = htmlSign;
        }

        return sign;
    }

    /**
     * <br>[機  能] form から受け取ったメッセージをhtmlで正常に
     * <br>         表示できる文字列に変換します。(TEXT AREA専用)
     * <br>[解  説]
     * <br>[備  考] transToHTmlとの違いは改行コードを処理しない点です。
     *
     * @param  str     変換元の文字列
     * @return         変換済みの文字列
     */
    private String __transToHtml(String str) {
        if (str == null) {
            return null;
        }

        StringBuilder retSB = new StringBuilder();
        StringCharacterIterator stit = new StringCharacterIterator(str);

        for (
            char c = stit.first();
            c != CharacterIterator.DONE;
            c = stit.next()
            ) {

            switch(c) {
            case '<':
                retSB.append("&lt;");
                break;
            case '>':
                retSB.append("&gt;");
                break;
            case '&':
                retSB.append("&amp;");
                break;
            default :
                retSB.append(c);
                break;
            }
        }
        return retSB.toString();
    }

    /**
     * <br>[機  能] 指定されたアカウントのメールテンプレート情報(文字列)を作成します。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param wacSid アカウントSID
     * @return 署名情報(文字列)
     * @throws SQLException SQL実行時例外
     */
    public String createMailTemplateText(Connection con, int wacSid) throws SQLException {
        StringBuilder sb = new StringBuilder("");

        WmlMailTemplateDao templateDao = new WmlMailTemplateDao(con);
        List<WmlMailTemplateModel> templateList = templateDao.getMailTemplateList(wacSid);
        for (int index = 0; index < templateList.size(); index++) {
            WmlMailTemplateModel templateModel = templateList.get(index);
            if (index > 0) {
                sb.append(",");
            }
            sb.append("{");
            sb.append("\"ID\" : " + templateModel.getWtpSid() + ",");
            sb.append("\"NAME\" : \""
                        + _escapeTextInput(
                            StringUtilHtml.transToHTmlPlusAmparsant(templateModel.getWtpName()))
                        + "\",");
            sb.append("\"TITLE\" : \""
                    + _escapeTextInput(
                            StringUtilHtml.transToHTmlPlusAmparsant(templateModel.getWtpTitle()))
                    + "\",");

            String body = templateModel.getWtpBody();
            body = StringUtilHtml.replaceString(body, "\\", "\\\\");
            body = StringUtilHtml.replaceString(body, "\b", "\\b");
            body = StringUtilHtml.replaceString(body, "\t", "\\t");
            sb.append("\"BODY\" : \""
                    + _escapeTextAreaInput(
                            StringUtilHtml.transToHTmlPlusAmparsant(body))
                    + "\"");
            sb.append("}");
        }

        return sb.toString();
    }

    /**
     * <br>[機  能] 送信メール情報にメールテンプレートの各情報を追加します。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param res HttpServletResponse
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param appRootPath アプリケーションルートパス
     * @param tempRootDir テンポラリルートディレクトリ
     * @throws SQLException SQL実行時例外
     * @throws UnsupportedEncodingException 送信メール本文の変換に失敗
     * @throws TempFileException 添付ファイルの読み込みに失敗
     * @throws IOException 添付ファイルの設定に失敗
     * @throws IOToolsException 添付ファイルの設定に失敗
     */
    public void setMailTemplate(Connection con, HttpServletResponse res,
                                                Wml010ParamModel paramMdl,
                                                RequestModel reqMdl,
                                                String appRootPath,
                                                String tempRootDir)
    throws SQLException, UnsupportedEncodingException,
    TempFileException, IOException, IOToolsException {
        res.setContentType("text/json; charset=UTF-8");
        PrintWriter writer = null;

        try {
            writer = res.getWriter();
            writer.write("{");

            int wtpSid = paramMdl.getWml010sendTemplate();
            WmlMailTemplateDao templateDao = new WmlMailTemplateDao(con);
            WmlMailTemplateModel templateMdl = templateDao.select(wtpSid);

            //件名
            String subject = NullDefault.getString(paramMdl.getWml010sendSubject(), "");
            if (StringUtil.isNullZeroString(subject)) {
                subject = templateMdl.getWtpTitle();
            }
            writer.write("\"subject\" : \"" + __URLEncode(subject) + "\",");

            //本文
            String body = NullDefault.getString(paramMdl.getWml010sendContent(), "");
            body = body.replaceAll("\r\n", "\n");
            if (!StringUtil.isNullZeroString(templateMdl.getWtpBody())) {
                body = templateMdl.getWtpBody() + "\r\n\r\n" + body;
            }
            body =  _escapeTextAreaInput(body);
            body = __URLEncode(body);
            writer.write("\"content\" : \"" + body + "\",");

            //添付ファイル
            String tempDir = getSendTempDir(tempRootDir, reqMdl);
            List<String> fileList = IOTools.getFileNames(tempDir);
            if (fileList == null) {
                fileList = new ArrayList<String>();
            }

            WmlMailTemplateFileDao templateFileDao = new WmlMailTemplateFileDao(con);
            String[] binSid = templateFileDao.getBinSid(wtpSid);
            String templateFileList = "";
            if (binSid != null && binSid.length > 0) {
                String dateStr = (new UDate()).getDateString(); //現在日付の文字列(YYYYMMDD)

                //添付ファイル情報を取得する
                CommonBiz cmnBiz = new CommonBiz();
                List<CmnBinfModel> cmList = cmnBiz.getBinInfo(con, binSid, reqMdl.getDomain());

                int fileNum = fileList.size() + 1;
                for (CmnBinfModel cbMdl : cmList) {
                    if (cbMdl.getBinJkbn() == GSConst.JTKBN_DELETE) {
                        continue;
                    }

                    //添付ファイルをテンポラリディレクトリにコピーする。
                    cmnBiz.saveTempFile(dateStr, cbMdl, appRootPath, tempDir, fileNum);

                    String fileName = cbMdl.getBinFileName();
                    long fileSize = cbMdl.getBinFileSize();
                    if (templateFileList.length() > 0) {
                        templateFileList += ",";
                    }
                    templateFileList += "{";
                    templateFileList += "\"fileName\" : \""
                            + _escapeTextView(fileName) + "\",";
                    templateFileList += "\"fileSize\" : \"";
                    if (fileSize < 1024) {
                        templateFileList += fileSize + " Byte\",";
                    } else {
                        BigDecimal decFileSize = new BigDecimal(fileSize);
                        decFileSize = decFileSize.divide(new BigDecimal(1024), 1,
                                                        BigDecimal.ROUND_HALF_UP);
                        templateFileList += decFileSize.toString() + " KByte\",";
                    }
                    File saveFilePath = Cmn110Biz.getSaveFilePath(tempDir, dateStr, fileNum);
                    templateFileList += "\"saveFileName\" : \""
                            + saveFilePath.getName() + "\"";
                    templateFileList += "}";

                    fileNum++;
                }
            }
            writer.write("\"fileList\" : [" + templateFileList + "]");

            writer.write("}");
            writer.flush();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * <br>[機  能] 送信予定日時を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @return 送信予定日時
     */
    public UDate getSendPlanDate(Wml010ParamModel paramMdl) {
        UDate sendPlanDate = new UDate();
        sendPlanDate.setTime(0);
        sendPlanDate.setYear(Integer.parseInt(paramMdl.getWml010sendMailPlanDateYear()));
        sendPlanDate.setMonth(Integer.parseInt(paramMdl.getWml010sendMailPlanDateMonth()));
        sendPlanDate.setDay(Integer.parseInt(paramMdl.getWml010sendMailPlanDateDay()));
        sendPlanDate.setHour(Integer.parseInt(paramMdl.getWml010sendMailPlanDateHour()));
        sendPlanDate.setMinute(Integer.parseInt(paramMdl.getWml010sendMailPlanDateMinute()));

        return sendPlanDate;
    }

    /**
     * <br>[機  能] 指定したメールが存在するかを確認する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param mailNum メッセージ番号
     * @return true:存在する false:存在しない
     * @throws SQLException SQL実行時例外
     */
    public boolean existsMailData(Connection con, long mailNum) throws SQLException {
        Wml010Dao dao010 = new Wml010Dao(con);
        return dao010.existsMailData(mailNum);
    }

    /**
     * <br>[機  能] メール削除(ゴミ箱へ移動 or ゴミ箱を空にする)時のオペレーションログを出力する
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @param paramMdl パラメータ情報
     * @param type 操作(0: ゴミ箱へ移動, 1:ゴミ箱を空にする)
     * @throws SQLException SQL実行時例外
     * @throws GSException オペレーションログの出力に失敗
     */
    protected void _outputOpLog(ActionMapping map, Connection con,
                                            RequestModel reqMdl, Wml010ParamModel paramMdl,
                                            int type) throws SQLException, GSException {

        GsMessage gsMsg = new GsMessage(reqMdl);

        String opCode = "";
        String message = null;
        if (type == LOGTYPE_EMPTYTRASH_) {
            //ゴミ箱を空にする
            opCode = gsMsg.getMessage("cmn.delete");
            message = gsMsg.getMessage(GSConstWebmail.LOG_VALUE_EMPTYTRASH);
        } else if (type == LOGTYPE_MAILDELETE_) {
            //ゴミ箱へ移動
            opCode = gsMsg.getMessage("cmn.delete");
            message = gsMsg.getMessage(GSConstWebmail.LOG_VALUE_DUSTMAIL);
        } else if (type == LOGTYPE_MOVEMAIL_) {
            //メールの移動
            opCode = "";
            message = gsMsg.getMessage(GSConstWebmail.LOG_VALUE_MOVEMAIL);
        }

        //アカウント名を出力する
        String accountName = "[" + gsMsg.getMessage("wml.102") + "] ";
        WmlAccountDao accountDao = new WmlAccountDao(con);
        accountName += accountDao.getAccountName(_getViewAccountSid(paramMdl));
        message += "\r\n" + accountName;

        if (type == LOGTYPE_MOVEMAIL_) {
            //移動先のディレクトリ名を出力する
            String dirName = "[" + gsMsg.getMessage("project.52") + "] ";
            WmlDirectoryDao dirDao = new WmlDirectoryDao(con);
            dirName += dirDao.getDirName(paramMdl.getWml010moveFolder());
            message += "\r\n" + dirName;
        }

        //移動したメールの件名を出力する
        if (type == LOGTYPE_MAILDELETE_
        || type == LOGTYPE_MOVEMAIL_) {
            message += "\r\n[" + gsMsg.getMessage("cmn.subject") + "] ";

            long[] messageNum = getSelectMessageNum(paramMdl);
            if (messageNum != null && messageNum.length > 0) {
                WmlMaildataDao mailDao = new WmlMaildataDao(con);
                List<String> mailTitleList = mailDao.getMailSubject(messageNum);

                for (int idx = 0; idx < mailTitleList.size(); idx++) {
                    if (idx > 0) {
                        message += ",";
                    }
                    message += mailTitleList.get(idx);
                }
            }
        }

        message = StringUtil.trimRengeString(message, GSConstCommon.MAX_LENGTH_LOG_OP_VALUE);
        WmlBiz wmlBiz = new WmlBiz();
        wmlBiz.outPutLog(map, reqMdl, con,
                opCode, GSConstLog.LEVEL_TRACE,
                message);
    }

    /**
     * <br>[機  能] 指定したアドレスのBCC強制変換を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param admConfMdl WEBメール管理者設定
     * @param mailData 送信メール情報(パラメータ)
     * @param sendData 送信メール情報
     */
    private void __bccConvert(WmlAdmConfModel admConfMdl,
                                            Wml010SendMailModel mailData,
                                            SmtpSendModel sendData) {

        String toAddress = mailData.getTo();
        String ccAddress = mailData.getCc();
        String bccAddress = mailData.getBcc();

        int bccTh = admConfMdl.getWadBccTh();
        if (admConfMdl.getWadBcc() == GSConstWebmail.WAD_BCC_CONVERSION
        && parseSendAddress(toAddress).size() + parseSendAddress(ccAddress).size() > bccTh) {
            bccAddress = __joinAddress(toAddress, bccAddress);
            bccAddress = __joinAddress(ccAddress, bccAddress);

            toAddress = sendData.getFrom();
            ccAddress = "";
        }

        sendData.setTo(toAddress);
        sendData.setCc(ccAddress);
        sendData.setBcc(bccAddress);
    }

    /**
     * <br>[機  能] 指定したメールアドレスを結合する
     * <br>[解  説]
     * <br>[備  考]
     * @param sendAddress メールアドレス(宛先 or CC)
     * @param bccAddress メールアドレス(BCC)
     * @return 結合したメールアドレス
     */
    private String __joinAddress(String sendAddress, String bccAddress) {
        if (!StringUtil.isNullZeroString(sendAddress)) {
            if (bccAddress.length() > 0) {
                bccAddress += ",";
            }
            bccAddress += sendAddress;
        }
        return bccAddress;
    }
}
