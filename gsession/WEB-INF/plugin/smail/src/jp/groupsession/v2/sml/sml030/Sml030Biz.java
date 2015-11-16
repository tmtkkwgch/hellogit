package jp.groupsession.v2.sml.sml030;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeUtility;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.http.ContentType;
import jp.co.sjts.util.io.CrlfTerminatedWriter;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.GSContext;
import jp.groupsession.v2.cmn.GroupSession;
import jp.groupsession.v2.cmn.ITempFileUtil;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.sml.GSConstSmail;
import jp.groupsession.v2.sml.ISMailListener;
import jp.groupsession.v2.sml.SMailListenerUtil;
import jp.groupsession.v2.sml.biz.SmlCommonBiz;
import jp.groupsession.v2.sml.dao.SmailDao;
import jp.groupsession.v2.sml.dao.SmailSearchDao;
import jp.groupsession.v2.sml.dao.SmlAsakDao;
import jp.groupsession.v2.sml.dao.SmlBinDao;
import jp.groupsession.v2.sml.dao.SmlJmeisDao;
import jp.groupsession.v2.sml.dao.SmlSmeisDao;
import jp.groupsession.v2.sml.dao.SmlWmeisDao;
import jp.groupsession.v2.sml.model.AtesakiModel;
import jp.groupsession.v2.sml.model.SmailDetailModel;
import jp.groupsession.v2.sml.model.SmlAdminModel;
import jp.groupsession.v2.sml.model.SmlJmeisModel;
import jp.groupsession.v2.sml.model.SmlSmeisModel;
import jp.groupsession.v2.sml.model.SmlUserModel;
import jp.groupsession.v2.sml.model.SmlWmeisModel;
import jp.groupsession.v2.sml.pdf.SmlPdfModel;
import jp.groupsession.v2.sml.pdf.SmlPdfUtil;
import jp.groupsession.v2.sml.sml010.Sml010Biz;
import jp.groupsession.v2.sml.sml010.Sml010ExportFileModel;
import jp.groupsession.v2.sml.sml090.Sml090SearchParameterModel;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] ショートメール 内容確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sml030Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sml030Biz.class);
    /** 返信・全返信ボタン表示フラグ */
    private boolean hensinDspFlg__ = true;

    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param reqMdl リクエスト情報
     */
    public Sml030Biz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     *
     */
    public Sml030Biz() {
    }

    /**
     * <br>[機  能] セッションユーザSIDを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param reqMdl リクエスト情報
     * @return sessionUsrSid セッションユーザSID
     */
    public int getSessionUserSid(RequestModel reqMdl) {

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
     * <br>[機  能] メール詳細設定(受信モード)
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void setInitDataJusin(Sml030ParamModel paramMdl,
                                  RequestModel reqMdl,
                                  Connection con)
        throws SQLException {

        log__.debug("初期表示データ(受信モード)取得");

        int sessionUsrSid = getSessionUserSid(reqMdl);
        SmailDao sDao = new SmailDao(con);

        //データ取得
        ArrayList<SmailDetailModel> resultList =
            sDao.selectJmeisDetail(
                paramMdl.getSmlViewAccount(),
                paramMdl.getSml010SelectedSid(),
                GSConstSmail.SML_JTKBN_TOROKU);

        if (!resultList.isEmpty()) {
            //取得データを表示形式に変換
            ArrayList<SmailDetailModel> ret =
                                      __convertMeisData(resultList, sessionUsrSid, false, con);
            paramMdl.setSml030SmlList(ret);
            paramMdl.setSml030HensinDspFlg(hensinDspFlg__);
            //送付ファイル情報を取得
            SmailDetailModel retMl = resultList.get(0);
            SmlBinDao binDao = new SmlBinDao(con);
            ArrayList<CmnBinfModel> retBin = binDao.getFileList(retMl.getSmlSid());
            paramMdl.setSml010SelectedMailKbn(retMl.getMailKbn());
            paramMdl.setSml030FileList(retBin);
        }
        paramMdl.setSml030SosinFlg(false);

        //写真表示フラグ
        Sml010Biz sml010Biz = new Sml010Biz();
        int photoDspFlg = sml010Biz.getPhotoDspFlg(reqMdl, con);
        paramMdl.setPhotoDspFlg(photoDspFlg);
    }

    /**
     * <br>[機  能] メール詳細設定(送信モード)
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void setInitDataSosin(Sml030ParamModel paramMdl,
                                RequestModel reqMdl,
                                Connection con)
        throws SQLException {

        log__.debug("初期表示データ(送信モード)取得");

        int sessionUsrSid = getSessionUserSid(reqMdl);
        SmailDao sDao = new SmailDao(con);

        //データ取得
        ArrayList<SmailDetailModel> resultList =
            sDao.selectSmeisDetail(
                paramMdl.getSmlViewAccount(),
                paramMdl.getSml010SelectedSid(),
                GSConstSmail.SML_JTKBN_TOROKU);

        if (!resultList.isEmpty()) {
            //取得データを表示形式に変換
            ArrayList<SmailDetailModel> ret =
                              __convertMeisData(resultList, sessionUsrSid, true, con);
            paramMdl.setSml030SmlList(ret);

            //送付ファイル情報を取得
            SmailDetailModel retMl = resultList.get(0);
            paramMdl.setSml010SelectedMailKbn(retMl.getMailKbn());
            SmlBinDao binDao = new SmlBinDao(con);
            ArrayList<CmnBinfModel> retBin = binDao.getFileList(retMl.getSmlSid());
            paramMdl.setSml030FileList(retBin);
        }

        if (isDeleteAll(paramMdl, con)) {
            paramMdl.setSml030DeleteAllOk(true);
        } else {
            paramMdl.setSml030DeleteAllOk(false);
        }
        paramMdl.setSml030SosinFlg(true);

        //写真表示フラグ
        Sml010Biz sml010Biz = new Sml010Biz();
        int photoDspFlg = sml010Biz.getPhotoDspFlg(reqMdl, con);
        paramMdl.setPhotoDspFlg(photoDspFlg);
    }

    /**
     * 開封済みのショートメールが存在するか判定する
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @return boolean true:全て未開封 false:開封済み有り
     * @throws SQLException SQL実行時例外
     */
    public boolean isDeleteAll(Sml030ParamModel paramMdl,
            Connection con) throws SQLException {

        String mailKbn =
            NullDefault.getString(
                    paramMdl.getSml010SelectedMailKbn(), "");
        if (mailKbn.equals(GSConstSmail.TAB_DSP_MODE_SOKO)) {
            return false;
        }

        SmailDao sDao = new SmailDao(con);
        //データ取得
        ArrayList<AtesakiModel> atesakiList = sDao.getAtesakiList(paramMdl.getSml010SelectedSid());
        if (!atesakiList.isEmpty()) {
            //取得データを表示形式に変換
            for (AtesakiModel atesakiMdl : atesakiList) {
                //開封日付が登録されている場合は開封済み有りと判定
                if (atesakiMdl.getSmjOpdate() != null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * <br>[機  能] メール詳細設定(ゴミ箱モード)
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void setInitDataGomi(Sml030ParamModel paramMdl,
                                RequestModel reqMdl,
                                Connection con)
        throws SQLException {

        log__.debug("初期表示データ(ゴミ箱モード)取得");

        int sessionUsrSid = getSessionUserSid(reqMdl);
        SmailDao sDao = new SmailDao(con);
        ArrayList<SmailDetailModel> resultList = new ArrayList<SmailDetailModel>();
        String mailKbn = paramMdl.getSml010SelectedMailKbn();
        boolean sosinFlg = false;

        int jtkbn = GSConstSmail.SML_JTKBN_GOMIBAKO;

        if (paramMdl.getSml010ProcMode().equals(GSConstSmail.TAB_DSP_MODE_LABEL)) {
            jtkbn = GSConstSmail.SML_JTKBN_TOROKU;
        }

        //受信メール
        if (mailKbn.equals(GSConstSmail.TAB_DSP_MODE_JUSIN)) {
            //データ取得
            resultList =
                sDao.selectJmeisDetail(
                    paramMdl.getSmlViewAccount(),
                    paramMdl.getSml010SelectedSid(),
                    jtkbn);
        //送信メール
        } else if (mailKbn.equals(GSConstSmail.TAB_DSP_MODE_SOSIN)) {
            //データ取得
            resultList =
                sDao.selectSmeisDetail(
                    paramMdl.getSmlViewAccount(),
                    paramMdl.getSml010SelectedSid(),
                    jtkbn);
            sosinFlg = true;
        //草稿
        } else if (mailKbn.equals(GSConstSmail.TAB_DSP_MODE_SOKO)) {
            resultList =
                sDao.selectWmeisDetail(
                        paramMdl.getSmlViewAccount(),
                        paramMdl.getSml010SelectedSid(),
                        jtkbn);
            sosinFlg = true;
        }
        paramMdl.setSml030SosinFlg(sosinFlg);

        if (!resultList.isEmpty()) {
            //取得データを表示形式に変換
            ArrayList<SmailDetailModel> ret
                = __convertMeisData(resultList, sessionUsrSid, sosinFlg, con);
            paramMdl.setSml030SmlList(ret);

            paramMdl.setSml030HensinDspFlg(hensinDspFlg__);

            //送付ファイル情報を取得
            SmailDetailModel retMl = resultList.get(0);
            SmlBinDao binDao = new SmlBinDao(con);
            ArrayList<CmnBinfModel> retBin = binDao.getFileList(retMl.getSmlSid());
            paramMdl.setSml030FileList(retBin);
        }
        if (isDeleteAll(paramMdl, con)) {
            paramMdl.setSml030DeleteAllOk(true);
        } else {
            paramMdl.setSml030DeleteAllOk(false);
        }

        //写真表示フラグ
        Sml010Biz sml010Biz = new Sml010Biz();
        int photoDspFlg = sml010Biz.getPhotoDspFlg(reqMdl, con);
        paramMdl.setPhotoDspFlg(photoDspFlg);
    }

    /**
     * <br>[機  能] メールの名称を取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @return mailName メール名称
     * @throws SQLException SQL実行時例外
     */
    public String getMailName(Sml030ParamModel paramMdl,
                            RequestModel reqMdl,
                            Connection con)
        throws SQLException {

        String mailName = "";

        //int sessionUsrSid = getSessionUserSid(reqMdl);
        SmailDao sDao = new SmailDao(con);
        ArrayList<SmailDetailModel> resultList = null;
        String mailKbn = paramMdl.getSml010SelectedMailKbn();

        //受信メール
        if (mailKbn.equals(GSConstSmail.TAB_DSP_MODE_JUSIN)) {
            //データ取得
            resultList =
                sDao.selectJmeisDetail(
                    paramMdl.getSmlViewAccount(),
                    paramMdl.getSml010SelectedSid(),
                    GSConstSmail.SML_JTKBN_GOMIBAKO);
        //送信メール
        } else if (mailKbn.equals(GSConstSmail.TAB_DSP_MODE_SOSIN)) {
            //データ取得
            resultList =
                sDao.selectSmeisDetail(
                    paramMdl.getSmlViewAccount(),
                    paramMdl.getSml010SelectedSid(),
                    GSConstSmail.SML_JTKBN_GOMIBAKO);
        //草稿
        } else if (mailKbn.equals(GSConstSmail.TAB_DSP_MODE_SOKO)) {
            resultList =
                sDao.selectWmeisDetail(
                        paramMdl.getSmlViewAccount(),
                        paramMdl.getSml010SelectedSid(),
                        GSConstSmail.SML_JTKBN_GOMIBAKO);
        }

        if (!resultList.isEmpty()) {
            SmailDetailModel retMdl = resultList.get(0);
            mailName = retMdl.getSmsTitle();
        }
        return mailName;
    }

    /**
     * <br>[機  能] 取得結果を変換する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramList 取得結果リスト
     * @param sessionUsrSid セッションユーザSID
     * @param sosinFlg 送信モードか、草稿モードならばtrue
     * @param con コネクション
     * @return 変換後リスト
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<SmailDetailModel> __convertMeisData(
            ArrayList<SmailDetailModel> paramList,
            int sessionUsrSid, boolean sosinFlg, Connection con)
            throws SQLException {

        ArrayList<SmailDetailModel> ret = new ArrayList<SmailDetailModel>();

        for (SmailDetailModel paramMdl : paramList) {
            SmailDetailModel retMdl = new SmailDetailModel();
            retMdl.setMailKbn(paramMdl.getMailKbn());
            retMdl.setSmlSid(paramMdl.getSmlSid());
            retMdl.setSmjOpkbn(paramMdl.getSmjOpkbn());
            retMdl.setSmjOpDate(paramMdl.getSmjOpDate());
            retMdl.setSmsMark(paramMdl.getSmsMark());
            retMdl.setSmsTitle(
                    StringUtilHtml.transToHTmlPlusAmparsant(
                            NullDefault.getString(paramMdl.getSmsTitle(), "")));
            retMdl.setSmsSdate(paramMdl.getSmsSdate());
            if (paramMdl.getSmsSdate() != null) {
                String strSdate =
                    UDateUtil.getSlashYYMD(paramMdl.getSmsSdate())
                    + "  "
                    + UDateUtil.getSeparateHMS(paramMdl.getSmsSdate());
                retMdl.setSmsSdateStr(strSdate);
            }
            String tmpBody = NullDefault.getString(paramMdl.getSmsBody(), "");
            if (paramMdl.getSmsType() == GSConstSmail.SAC_SEND_MAILTYPE_NORMAL) {
                tmpBody = StringUtil.transToLink(StringUtilHtml.transToHTmlPlusAmparsant(
                        tmpBody), StringUtil.OTHER_WIN, true);
            } else {
                tmpBody = StringUtil.transToLink(tmpBody, StringUtil.OTHER_WIN, true);
            }
            log__.debug("実際に書かれるurlです。" + tmpBody);
            retMdl.setSmsBody(tmpBody);
            retMdl.setSmsType(paramMdl.getSmsType());

            retMdl.setAccountSid(paramMdl.getAccountSid());
            retMdl.setAccountName(paramMdl.getAccountName());
            retMdl.setAccountJkbn(paramMdl.getAccountJkbn());

            retMdl.setUsrSid(paramMdl.getUsrSid());
            if (!StringUtil.isNullZeroStringSpace(paramMdl.getAccountName())) {
                retMdl.setUsrJkbn(paramMdl.getAccountJkbn());
                retMdl.setUsiSei(NullDefault.getString(
                        paramMdl.getUsiSei(), paramMdl.getAccountName()));
                retMdl.setUsiMei(NullDefault.getString(paramMdl.getUsiMei(), ""));
            } else {
                retMdl.setUsrJkbn(paramMdl.getUsrJkbn());
                retMdl.setUsiSei(NullDefault.getString(paramMdl.getUsiSei(), ""));
                retMdl.setUsiMei(NullDefault.getString(paramMdl.getUsiMei(), ""));
            }
            retMdl.setSmsEdate(paramMdl.getSmsEdate());
            if (paramMdl.getUsrSid() == GSConst.SYSTEM_USER_MAIL) {
                hensinDspFlg__ = false;
            } else {
                hensinDspFlg__ = true;
            }

            ArrayList<AtesakiModel> atskList = paramMdl.getAtesakiList();
            ArrayList<AtesakiModel> retAtskList = new ArrayList<AtesakiModel>();
            ArrayList<AtesakiModel> retCcList = new ArrayList<AtesakiModel>();
            ArrayList<AtesakiModel> retBccList = new ArrayList<AtesakiModel>();
            if (!atskList.isEmpty()) {
                for (AtesakiModel atskMdl : atskList) {
                    AtesakiModel dbatskMdl = new AtesakiModel();
                    if (atskMdl.getSmjOpdate() != null) {
                        String strOpdate =
                            UDateUtil.getSlashYYMD(atskMdl.getSmjOpdate())
                        + "  "
                        + UDateUtil.getSeparateHMS(atskMdl.getSmjOpdate());
                        dbatskMdl.setSmlOpdateStr(strOpdate);
                    }
                    dbatskMdl.setUsrSid(atskMdl.getUsrSid());


                    if (atskMdl.getUsrSid() > 0) {
                        dbatskMdl.setUsrJkbn(atskMdl.getUsrJkbn());
                        dbatskMdl.setUsiSei(NullDefault.getString(atskMdl.getUsiSei(), ""));
                        dbatskMdl.setUsiMei(NullDefault.getString(atskMdl.getUsiMei(), ""));
                    } else {
                        dbatskMdl.setUsrJkbn(atskMdl.getAccountJkbn());
                        dbatskMdl.setUsiSei(NullDefault.getString(
                                atskMdl.getUsiSei(), atskMdl.getAccountName()));
                        dbatskMdl.setUsiMei(NullDefault.getString(atskMdl.getUsiMei(), ""));

                    }


                    dbatskMdl.setSmjFwkbn(atskMdl.getSmjFwkbn());
                    dbatskMdl.setBinFileSid(atskMdl.getBinFileSid());
                    dbatskMdl.setPhotoFileDsp(atskMdl.getPhotoFileDsp());

                    dbatskMdl.setAccountSid(atskMdl.getAccountSid());
                    dbatskMdl.setAccountName(atskMdl.getAccountName());
                    dbatskMdl.setAccountJkbn(atskMdl.getAccountJkbn());

                    if (atskMdl.getSmjSendkbn() == GSConstSmail.SML_SEND_KBN_ATESAKI) {
                        retAtskList.add(dbatskMdl);
                    } else if (atskMdl.getSmjSendkbn() == GSConstSmail.SML_SEND_KBN_CC) {
                        retCcList.add(dbatskMdl);
                    } else if (atskMdl.getSmjSendkbn() == GSConstSmail.SML_SEND_KBN_BCC) {
                        if (sosinFlg || sessionUsrSid == atskMdl.getUsrSid()) {
                            retBccList.add(dbatskMdl);
                        }
                    }
                }
            }

            retMdl.setAtesakiList(retAtskList);
            retMdl.setCcList(retCcList);
            retMdl.setBccList(retBccList);
            if (!retAtskList.isEmpty()) {
                retMdl.setListSize(retAtskList.size() - 1);
            }
            if (!retCcList.isEmpty()) {
                retMdl.setCcListSize(retCcList.size() - 1);
            }
            if (!retBccList.isEmpty()) {
                retMdl.setBccListSize(retBccList.size() - 1);
            }

            retMdl.setBinFileSid(paramMdl.getBinFileSid());
            retMdl.setPhotoFileDsp(paramMdl.getPhotoFileDsp());
            retMdl.setReturnKbn(paramMdl.getReturnKbn());
            retMdl.setFwKbn(paramMdl.getFwKbn());

            ret.add(retMdl);
        }

        return ret;
    }

    /**
     * <br>[機  能] 未読メッセージを既読に更新し、戻り値として更新後の未読メッセージ数を返す。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @param pluginConfig PluginConfig
     * @return midokuCnt 未読メッセージ残りカウント
     * @throws ClassNotFoundException 指定されたリスナークラスが存在しない
     * @throws IllegalAccessException リスナー実装クラスのインスタンス生成に失敗
     * @throws InstantiationException リスナー実装クラスのインスタンス生成に失敗
     * @throws SQLException SQL実行時例外
     */
    public int updateMidokuMsg(
        Sml030ParamModel paramMdl,
        RequestModel reqMdl,
        Connection con,
        PluginConfig pluginConfig)
        throws
        ClassNotFoundException,
        IllegalAccessException,
        InstantiationException,
        SQLException {

        int opKbn = GSConstSmail.OPKBN_UNOPENED;
        ArrayList<SmailDetailModel> ret = paramMdl.getSml030SmlList();
        if (ret != null && !ret.isEmpty()) {
            for (SmailDetailModel retMdl : ret) {
                if (retMdl.getSmjOpkbn() != GSConstSmail.OPKBN_UNOPENED
                        && retMdl.getSmjOpDate() != null) {
                    opKbn = retMdl.getSmjOpkbn();
                }
            }
        }

        int midokuCnt = -1;

        //未開封
        if (opKbn == GSConstSmail.OPKBN_UNOPENED) {
            //開封済に更新
            int usrSid = getSessionUserSid(reqMdl);
            int smjsid = paramMdl.getSml010SelectedSid();
            updateMidokuMsg(con, paramMdl.getSmlViewAccount(), usrSid, smjsid);

            //ショートメールリスナー取得
            log__.debug("ショートメールリスナー doSmailOpen()開始");
            ISMailListener[] lis =
                SMailListenerUtil.getSMailListeners(pluginConfig);
            //各プラグインリスナーを呼び出し
            for (int i = 0; i < lis.length; i++) {
                lis[i].doSmailOpen(con, smjsid, usrSid, paramMdl.getSmlViewAccount());
            }
            log__.debug("ショートメールリスナー doSmailOpen()終了");

            //残り未読件数を取得
            SmlCommonBiz cmnBiz = new SmlCommonBiz(reqMdl);
            midokuCnt = cmnBiz.getUnopenedMsgCnt(paramMdl.getSmlViewAccount(), con);
            log__.debug("未読メッセージ件数残り = " + midokuCnt + "件");
        }

        return midokuCnt;
    }

    /**
     * <br>[機  能] 指定したユーザの、指定したメールを既読にする。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param sacSid アカウントSID
     * @param usid ユーザSID
     * @param smjsid メールSID
     * @throws SQLException SQL実行時例外
     */
    public void updateMidokuMsg(
            Connection con, int sacSid, int usid, int smjsid) throws SQLException {
        UDate sysUd = new UDate();
        SmlJmeisModel param = new SmlJmeisModel();
        param.setSacSid(sacSid);
        param.setSmjSid(smjsid);
        param.setSmjOpdate(sysUd);
        param.setSmjEuid(usid);
        param.setSmjEdate(sysUd);

        SmlJmeisDao jdao = new SmlJmeisDao(con);

        //すでに開封していた場合は開封日時は更新しない
        if (!jdao.selOpKbnDate(param)) {
            jdao.updateOpKbn(usid, param);
        } else {
            param.setSmjOpkbn(GSConstSmail.OPKBN_OPENED);
            jdao.updateOpKbnOnly(usid, param);
        }

    }

    /**
     * <br>[機  能] 全データ中の位置を把握するハッシュを作成
     * <br>[解  説]
     * <br>[備  考] 送信モード時
     *
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @return ret ハッシュModel
     * @throws SQLException SQL実行時例外
     */
    public HashControlModel getAllDataHash(Sml030ParamModel paramMdl,
                                        RequestModel reqMdl,
                                        Connection con)
        throws SQLException {

        HashControlModel ret = new HashControlModel();
        String procMode = paramMdl.getSml010ProcMode();
        int sessionUserSid = getSessionUserSid(reqMdl);

        //表示最大件数を取得する
        int limit = 0;
        SmlCommonBiz smlCmnBiz = new SmlCommonBiz(reqMdl);
        SmlAdminModel smlAdmMdl = smlCmnBiz.getSmailAdminConf(sessionUserSid, con);
        if (smlAdmMdl.getSmaMaxDspStype() == GSConstSmail.DISP_CONF_ADMIN) {
            //管理者設定の表示設定を反映する
            limit = smlAdmMdl.getSmaMaxDsp();
        } else {
            //個人設定の表示設定を反映する
            SmlUserModel smlUsrMdl = smlCmnBiz.getSmailUserConf(sessionUserSid, con);
            limit = smlUsrMdl.getSmlMaxDsp();
        }

        //受信モード
        if (procMode.equals(GSConstSmail.TAB_DSP_MODE_JUSIN)
            || procMode.equals(GSConstSmail.TAB_DSP_MODE_JUSIN_FROM_TOP)) {
            if (!GSConstSmail.SEARCH_BACK_ON.equals(paramMdl.getSml090BackParm())) {

                SmailDao sdao = new SmailDao(con);
                ret =
                    sdao.createJAllDataHash(paramMdl.getSmlViewAccount(),
                                            limit,
                                            paramMdl.getSml010SelectedSid(),
                                            paramMdl.getSml010Sort_key(),
                                            paramMdl.getSml010Order_key());
            } else {
                log__.debug("詳細検索より遷移：受信モード");
                SmailSearchDao srhDao = new SmailSearchDao(con);
                Sml090SearchParameterModel prmModel =
                    __getSearchParameter(paramMdl.getSmlViewAccount(), paramMdl, limit);
                ret = srhDao.createJAllDataHash(paramMdl.getSml010SelectedSid(), prmModel);
            }
        //送信モード
        } else if (procMode.equals(GSConstSmail.TAB_DSP_MODE_SOSIN)) {
            if (!GSConstSmail.SEARCH_BACK_ON.equals(paramMdl.getSml090BackParm())) {
                SmailDao sdao = new SmailDao(con);
                ret =
                    sdao.createSAllDataHash(paramMdl.getSmlViewAccount(),
                                            limit,
                                            paramMdl.getSml010SelectedSid(),
                                            paramMdl.getSml010Sort_key(),
                                            paramMdl.getSml010Order_key());
            } else {
                log__.debug("詳細検索より遷移：送信モード");
                SmailSearchDao srhDao = new SmailSearchDao(con);
                Sml090SearchParameterModel prmModel =
                    __getSearchParameter(paramMdl.getSmlViewAccount(), paramMdl, limit);
                ret = srhDao.createSAllDataHash(paramMdl.getSml010SelectedSid(), prmModel);
            }
        //ゴミ箱モード
        } else if (procMode.equals(GSConstSmail.TAB_DSP_MODE_GOMIBAKO)) {
            if (!GSConstSmail.SEARCH_BACK_ON.equals(paramMdl.getSml090BackParm())
                    || procMode.equals(GSConstSmail.TAB_DSP_MODE_LABEL)) {
                SmailDao sdao = new SmailDao(con);
                ret =
                    sdao.createGAllDataHash(paramMdl.getSmlViewAccount(),
                                            paramMdl.getSml010SelectedMailKbn(),
                                            limit,
                                            paramMdl.getSml010SelectedSid(),
                                            paramMdl.getSml010Sort_key(),
                                            paramMdl.getSml010Order_key());
            } else {
                log__.debug("詳細検索より遷移：ゴミ箱モード");
                SmailSearchDao srhDao = new SmailSearchDao(con);
                Sml090SearchParameterModel prmModel =
                    __getSearchParameter(paramMdl.getSmlViewAccount(), paramMdl, limit);
                ret = srhDao.createGAllDataHash(paramMdl.getSml010SelectedMailKbn(),
                                            paramMdl.getSml010SelectedSid(),
                                            prmModel);
            }
        //ラベルモード
        } else if (procMode.equals(GSConstSmail.TAB_DSP_MODE_LABEL)) {

                SmailDao sdao = new SmailDao(con);
                ret =
                    sdao.createLAllDataHash(paramMdl.getSmlViewAccount(),
                                            paramMdl.getSml010SelectLabelSid(),
                                            paramMdl.getSml010SelectedMailKbn(),
                                            limit,
                                            paramMdl.getSml010SelectedSid(),
                                            paramMdl.getSml010Sort_key(),
                                            paramMdl.getSml010Order_key());

        }
        return ret;
    }

    /**
     * <br>[機  能] 検索用パラメータモデルを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param sacSid アカウントSID
     * @param paramMdl パラメータ情報
     * @param limit リミット
     * @return Sml090SearchParameterModel
     */
    private Sml090SearchParameterModel __getSearchParameter(
            int sacSid, Sml030ParamModel paramMdl, int limit) {

        Sml090SearchParameterModel prmModel = new Sml090SearchParameterModel();
        prmModel.setMySid(sacSid);
        prmModel.setMailMode(paramMdl.getSml010ProcMode());
        prmModel.setAtesaki(paramMdl.getSml090SvAtesaki());
        prmModel.setKeyword(NullDefault.getString(paramMdl.getSml090SvKeyWord(), ""));
        prmModel.setKeyWordkbn(Integer.parseInt(paramMdl.getSml090SvKeyWordkbn()));
        prmModel.setMailMark(Integer.parseInt(paramMdl.getSml090SvMailMark()));
        prmModel.setMailSyubetsu(paramMdl.getSml090SvMailSyubetsu());

        prmModel.setSearchOrderKey1(Integer.parseInt(paramMdl.getSml090SvSearchOrderKey1()));
        prmModel.setSearchOrderKey2(Integer.parseInt(paramMdl.getSml090SvSearchOrderKey2()));
        prmModel.setSearchSortKey1(Integer.parseInt(paramMdl.getSml090SvSearchSortKey1()));
        prmModel.setSearchSortKey2(Integer.parseInt(paramMdl.getSml090SvSearchSortKey2()));
        prmModel.setSearchTarget(paramMdl.getSml090SvSearchTarget());
        prmModel.setSltGroup(String.valueOf(
                NullDefault.getInt(paramMdl.getSml090SvSltGroup(), GSConstCommon.NUM_INIT)));
        prmModel.setSltUser(String.valueOf(
                NullDefault.getInt(paramMdl.getSml090SvSltUser(), GSConstCommon.NUM_INIT)));
        prmModel.setUserSid(paramMdl.getCmn120SvuserSid());

        prmModel.setLimit(limit);

        return prmModel;
    }

    /**
     * <br>[機  能] 削除処理実行
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void deleteMessage(Sml030ParamModel paramMdl, RequestModel reqMdl, Connection con)
        throws SQLException {

        String procMode = paramMdl.getSml010ProcMode();
        int selectedSid = paramMdl.getSml010SelectedSid();
        int sessionUsrSid = getSessionUserSid(reqMdl);

        //処理モード = 受信モード
        if (procMode.equals(GSConstSmail.TAB_DSP_MODE_JUSIN)
                || procMode.equals(GSConstSmail.TAB_DSP_MODE_JUSIN_FROM_TOP)) {
            log__.debug("受信メッセージ削除(ゴミ箱へ移動)");
            SmlJmeisDao jdao = new SmlJmeisDao(con);
            jdao.moveJmeis(
                    sessionUsrSid,
                    paramMdl.getSmlViewAccount(),
                    GSConstSmail.SML_JTKBN_GOMIBAKO,
                    new UDate(),
                    selectedSid);
        //処理モード = 送信モード
        } else if (procMode.equals(GSConstSmail.TAB_DSP_MODE_SOSIN)) {
            log__.debug("送信メッセージ削除(ゴミ箱へ移動)");
            SmlSmeisDao sdao = new SmlSmeisDao(con);
            sdao.moveSmeis(
                    sessionUsrSid,
                    paramMdl.getSmlViewAccount(),
                    GSConstSmail.SML_JTKBN_GOMIBAKO,
                    new UDate(),
                    selectedSid);
        //処理モード = 下書きモード
        } else if (procMode.equals(GSConstSmail.TAB_DSP_MODE_SOKO)) {
            log__.debug("草稿メッセージ削除(ゴミ箱へ移動)");
            SmlWmeisDao wdao = new SmlWmeisDao(con);
            wdao.moveWmeis(
                    sessionUsrSid,
                    paramMdl.getSmlViewAccount(),
                    GSConstSmail.SML_JTKBN_GOMIBAKO,
                    new UDate(),
                    selectedSid);
        //処理モード = ゴミ箱
        } else if (procMode.equals(GSConstSmail.TAB_DSP_MODE_GOMIBAKO)
                || procMode.equals(GSConstSmail.TAB_DSP_MODE_LABEL)) {
            log__.debug("ゴミ箱メッセージを削除(論理削除)");

            String mailKbn = paramMdl.getSml010SelectedMailKbn();
            int mailSid = paramMdl.getSml010SelectedSid();

           /************************************************************************
            *
            * 受信、送信の場合は他のユーザのデータと参照しあうため論理削除とする。
            * 草稿に関しては自分のみのデータなので物理削除とする。
            *
            ************************************************************************/

            if (procMode.equals(GSConstSmail.TAB_DSP_MODE_GOMIBAKO)) {
                //受信メッセージ(論理削除)
                if (mailKbn.equals(GSConstSmail.TAB_DSP_MODE_JUSIN)) {
                    SmlJmeisDao jdao = new SmlJmeisDao(con);
                    jdao.moveJmeis(
                            sessionUsrSid,
                            paramMdl.getSmlViewAccount(),
                            mailSid,
                            GSConstSmail.SML_JTKBN_DELETE,
                            new UDate());
                }
                //送信メッセージ(論理削除)
                if (mailKbn.equals(GSConstSmail.TAB_DSP_MODE_SOSIN)) {
                    SmlSmeisDao sdao = new SmlSmeisDao(con);
                    sdao.moveSmeis(
                            sessionUsrSid,
                            paramMdl.getSmlViewAccount(),
                            mailSid,
                            GSConstSmail.SML_JTKBN_DELETE,
                            new UDate());
                }
                //草稿メッセージ(物理削除)
                if (mailKbn.equals(GSConstSmail.TAB_DSP_MODE_SOKO)) {
                    //ラベル
                    SmailDao smlDao = new SmailDao(con);
                    smlDao.deleteLabel(mailSid);
                    //添付情報(論理削除)
                    SmlWmeisDao wdao = new SmlWmeisDao(con);
                    wdao.deleteBin(paramMdl.getSmlViewAccount(), mailSid);
                    //草稿
                    wdao.deleteMsgButuri(paramMdl.getSmlViewAccount(), mailSid);
                    SmlAsakDao adao = new SmlAsakDao(con);
                    adao.deleteMsgButuri(paramMdl.getSmlViewAccount(), mailSid);

                }
            } else if (procMode.equals(GSConstSmail.TAB_DSP_MODE_LABEL)) {
                //受信メッセージ(論理削除)
                if (mailKbn.equals(GSConstSmail.TAB_DSP_MODE_JUSIN)) {
                    SmlJmeisDao jdao = new SmlJmeisDao(con);
                    jdao.moveJmeis(
                            sessionUsrSid,
                            paramMdl.getSmlViewAccount(),
                            GSConstSmail.SML_JTKBN_GOMIBAKO,
                            new UDate(),
                            selectedSid);
                }
                //送信メッセージ(論理削除)
                if (mailKbn.equals(GSConstSmail.TAB_DSP_MODE_SOSIN)) {
                    SmlSmeisDao sdao = new SmlSmeisDao(con);
                    sdao.moveSmeis(
                            sessionUsrSid,
                            paramMdl.getSmlViewAccount(),
                            mailSid,
                            GSConstSmail.SML_JTKBN_GOMIBAKO,
                            new UDate());
                }
                //草稿メッセージ(物理削除)
                if (mailKbn.equals(GSConstSmail.TAB_DSP_MODE_SOKO)) {
                    SmlWmeisDao wdao = new SmlWmeisDao(con);
                    wdao.moveWmeis(
                            sessionUsrSid,
                            paramMdl.getSmlViewAccount(),
                            GSConstSmail.SML_JTKBN_GOMIBAKO,
                            new UDate(),
                            selectedSid);

                }
            }

            //アカウントディスク使用量の再計算を行う
            SmlCommonBiz smlBiz = new SmlCommonBiz();
            smlBiz.updateAccountDiskSize(con, paramMdl.getSmlViewAccount());
        }
    }

    /**
     * <br>[機  能] 完全にショートメールを削除します。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void allDeleteMessage(Sml030ParamModel paramMdl, RequestModel reqMdl, Connection con)
        throws SQLException {

        int mailSid = paramMdl.getSml010SelectedSid();

        //受信メッセージ(論理削除)
        SmlJmeisDao jdao = new SmlJmeisDao(con);
        jdao.deleteMail(mailSid);
        //送信メッセージ(論理削除)
        SmlSmeisDao sdao = new SmlSmeisDao(con);
        sdao.deleteMail(mailSid);


    }
    /**
     * <br>[機  能] 復旧処理実行
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void revivedMessage(Sml030ParamModel paramMdl, RequestModel reqMdl, Connection con)
        throws SQLException {

        log__.debug("ゴミ箱メッセージ復旧");

        int sessionUsrSid = getSessionUserSid(reqMdl);

        int mailSid = paramMdl.getSml010SelectedSid();
        String mailKbn = paramMdl.getSml010SelectedMailKbn();

        String[] sidList = new String[1];
        sidList[0] = mailKbn + String.valueOf(mailSid);

       /************************************************************************
        *
        * 選択されたメールを種類毎(受信、送信、草稿)に復旧していく。
        *
        ************************************************************************/

        //受信メッセージ
        if (mailKbn.equals(GSConstSmail.TAB_DSP_MODE_JUSIN)) {
            SmlJmeisDao jdao = new SmlJmeisDao(con);
            jdao.moveJmeis(
                    sessionUsrSid,
                    paramMdl.getSmlViewAccount(),
                    GSConstSmail.SML_JTKBN_TOROKU,
                    new UDate(),
                    sidList);
        }
        //送信メッセージ
        if (mailKbn.equals(GSConstSmail.TAB_DSP_MODE_SOSIN)) {
            SmlSmeisDao sdao = new SmlSmeisDao(con);
            sdao.moveSmeis(
                    sessionUsrSid,
                    paramMdl.getSmlViewAccount(),
                    GSConstSmail.SML_JTKBN_TOROKU,
                    new UDate(),
                    sidList);
        }
        //草稿メッセージ
        if (mailKbn.equals(GSConstSmail.TAB_DSP_MODE_SOKO)) {
            SmlWmeisDao wdao = new SmlWmeisDao(con);
            wdao.moveWmeis(
                    sessionUsrSid,
                    paramMdl.getSmlViewAccount(),
                    GSConstSmail.SML_JTKBN_TOROKU,
                    new UDate(),
                    sidList);
        }
    }

    /**
     * <br>[機  能] メール詳細設定(受信モード)
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @return resultList 取得結果
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<SmailDetailModel> getAtesakiZyusin(Sml030ParamModel paramMdl,
                                                        RequestModel reqMdl,
                                                        Connection con)
        throws SQLException {

        log__.debug("宛先(受信時)取得");

        int sessionUsrSid = getSessionUserSid(reqMdl);
        SmailDao sDao = new SmailDao(con);

        //データ取得
        ArrayList<SmailDetailModel> resultList =
            sDao.selectJmeisDetail(
                sessionUsrSid,
                paramMdl.getSml010SelectedSid(),
                GSConstSmail.SML_JTKBN_TOROKU);

        return resultList;
    }

    /**
     * <br>[機  能] メール詳細設定(受信モード,全返信押下時)
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @return resultList 取得結果
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<SmailDetailModel> getAtesakiZyusin2(Sml030ParamModel paramMdl,
                                                        RequestModel reqMdl,
                                                        Connection con)
        throws SQLException {

        log__.debug("宛先(受信時)取得");

        int sessionUsrSid = getSessionUserSid(reqMdl);
        SmailDao sDao = new SmailDao(con);

        //データ取得
        ArrayList<SmailDetailModel> resultList =
            sDao.selectJmeisDetail2(
                sessionUsrSid,
                paramMdl.getSml010SelectedSid(),
                GSConstSmail.SML_JTKBN_TOROKU);

        return resultList;
    }

    /**
     * <br>[機  能] メール詳細設定(送信モード)
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @return resultList 取得結果
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<SmailDetailModel> getAtesakiSosin(Sml030ParamModel paramMdl,
                                                    RequestModel reqMdl,
                                                    Connection con)
        throws SQLException {

        log__.debug("宛先(受信時)取得");

        int sessionUsrSid = getSessionUserSid(reqMdl);
        SmailDao sDao = new SmailDao(con);

        //データ取得
        ArrayList<SmailDetailModel> resultList =
            sDao.selectSmeisDetail(
                sessionUsrSid,
                paramMdl.getSml010SelectedSid(),
                GSConstSmail.SML_JTKBN_TOROKU);

        return resultList;
    }

    /**
     * <br>[機  能] メール詳細設定(送信モード、全返信押下時)
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @return resultList 取得結果
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<SmailDetailModel> getAtesakiSosin2(Sml030ParamModel paramMdl,
                                                        RequestModel reqMdl,
                                                        Connection con)
        throws SQLException {

        log__.debug("宛先(受信時)取得");

        int sessionUsrSid = getSessionUserSid(reqMdl);
        SmailDao sDao = new SmailDao(con);

        //データ取得
        ArrayList<SmailDetailModel> resultList =
            sDao.selectSmeisDetail2(
                sessionUsrSid,
                paramMdl.getSml010SelectedSid(),
                GSConstSmail.SML_JTKBN_TOROKU);

        return resultList;
    }

    /**
     * <br>[機  能] メッセージ件名取得
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @return retTitle 件名
     * @throws SQLException SQL実行時例外
     */
    public String getDelMsgTitle(Sml030ParamModel paramMdl, Connection con)
        throws SQLException {

        String retTitle = "";
        String procMode = paramMdl.getSml010ProcMode();
        String mailKbn = NullDefault.getString(
                paramMdl.getSml010SelectedMailKbn(), "");

        if (procMode.equals(GSConstSmail.TAB_DSP_MODE_GOMIBAKO)
            && mailKbn.equals(GSConstSmail.TAB_DSP_MODE_SOKO)) {
            SmlWmeisDao wdao = new SmlWmeisDao(con);
            SmlWmeisModel param = new SmlWmeisModel();
            param.setSmwSid(paramMdl.getSml010SelectedSid());
            SmlWmeisModel wret = wdao.select(param);
            if (wret != null) {
                retTitle = wret.getSmwTitle();
            }
        } else {
            SmlSmeisDao sdao = new SmlSmeisDao(con);
            SmlSmeisModel param = new SmlSmeisModel();
            param.setSmsSid(paramMdl.getSml010SelectedSid());
            SmlSmeisModel sret = sdao.select(param);
            if (sret != null) {
                retTitle = sret.getSmsTitle();
            }
        }

        return retTitle;
    }

    /**
     * <br>[機  能] メール内容をPDF出力します。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param appRootPath アプリケーションルートパス
     * @param outTempDir テンポラリディレクトパス
     * @return pdfModel SmlPdfModel
     * @throws IOException IO実行時例外
     * @throws SQLException IO実行時例外
     */
    public SmlPdfModel createSmlPdf(
            Sml030ParamModel paramMdl,
            Connection con,
            String appRootPath,
            String outTempDir)
        throws IOException, SQLException {
        OutputStream oStream = null;

        BaseUserModel usModel = reqMdl__.getSmodel();

        //アカウント名
        String accName = usModel.getUsiseimei();
        //件名
        String title = StringUtilHtml.transToText(
                             paramMdl.getSml030SmlList().get(0).getSmsTitle());
        //送信者
        String sender = paramMdl.getSml030SmlList().get(0).getUsiSei()
                + " " + paramMdl.getSml030SmlList().get(0).getUsiMei();
        //日時
        String date = paramMdl.getSml030SmlList().get(0).getSmsSdateStr();
        if (paramMdl.getSml030SmlList().get(0).getSmsSdate() == null
        && paramMdl.getSml030SmlList().get(0).getSmsEdate() != null) {
            date = UDateUtil.getSlashYYMD(paramMdl.getSml030SmlList().get(0).getSmsEdate())
            + "  "
            + UDateUtil.getSeparateHMS(paramMdl.getSml030SmlList().get(0).getSmsEdate());
        }
        //宛先
        String atesaki = new String();
        for (int i = 0; i < paramMdl.getSml030SmlList().get(0).getAtesakiList().size(); i++) {
            atesaki += paramMdl.getSml030SmlList().get(0).getAtesakiList().get(i).getUsiSei()
                    + " " + paramMdl.getSml030SmlList().get(0).getAtesakiList().get(i).getUsiMei();
            if (i != paramMdl.getSml030SmlList().get(0).getAtesakiList().size() - 1) {
                atesaki += " , ";
            }
        }
        //CC
        String atesakiCC = new String();
        for (int i = 0; i < paramMdl.getSml030SmlList().get(0).getCcList().size(); i++) {
            atesakiCC += paramMdl.getSml030SmlList().get(0).getCcList().get(i).getUsiSei()
                    + " " + paramMdl.getSml030SmlList().get(0).getCcList().get(i).getUsiMei();
            if (i != paramMdl.getSml030SmlList().get(0).getCcList().size() - 1) {
                atesakiCC += " , ";
            }
        }

        String mailKbn = paramMdl.getSml030SmlList().get(0).getMailKbn();
        String atesakiBCC = null;
        //送信区分のみ
        if (mailKbn.equals(GSConstSmail.TAB_DSP_MODE_SOSIN)) {
            //BCC
            atesakiBCC = new String();
            for (int i = 0; i < paramMdl.getSml030SmlList().get(0).getBccList().size(); i++) {
                atesakiBCC += paramMdl.getSml030SmlList().get(0).getBccList().get(i).getUsiSei()
                        + " " + paramMdl.getSml030SmlList().get(0).getBccList().get(i).getUsiMei();
                if (i != paramMdl.getSml030SmlList().get(0).getBccList().size() - 1) {
                    atesakiBCC += " , ";
                }
            }
        }

        //草稿の場合、CCとBCCを設定
        if (mailKbn.equals(GSConstSmail.TAB_DSP_MODE_SOKO)) {
            SmailDao smailDao = new SmailDao(con);
            ArrayList<AtesakiModel> ccList
                = smailDao.getSitagakiAtesakiList(paramMdl.getSml030SmlList().get(0).getSmlSid(),
                                                    GSConstSmail.SML_SEND_KBN_CC);
            ArrayList<AtesakiModel> bccList
                = smailDao.getSitagakiAtesakiList(paramMdl.getSml030SmlList().get(0).getSmlSid(),
                                                    GSConstSmail.SML_SEND_KBN_BCC);
            //CC
            atesakiCC = "";
            for (int i = 0; i < ccList.size(); i++) {
                atesakiCC += ccList.get(i).getUsiSei()
                        + " " + ccList.get(i).getUsiMei();
                if (i != ccList.size() - 1) {
                    atesakiCC += " , ";
                }
            }
            //BCC
            atesakiBCC = "";
            for (int i = 0; i < bccList.size(); i++) {
                atesakiBCC += bccList.get(i).getUsiSei()
                        + " " + bccList.get(i).getUsiMei();
                if (i != bccList.size() - 1) {
                    atesakiBCC += " , ";
                }
            }
        }

        //マーク
        int mark = paramMdl.getSml030SmlList().get(0).getSmsMark();
        //添付
        String tempFile = new String();
        for (int i = 0; i < paramMdl.getSml030FileList().size(); i++) {
            tempFile += paramMdl.getSml030FileList().get(i).getBinFileName()
                    + paramMdl.getSml030FileList().get(i).getBinFileSizeDsp();
            if (i != paramMdl.getSml030FileList().size() - 1) {
                tempFile += " , ";
            }
        }
        //本文
        String main = paramMdl.getSml030SmlList().get(0).getSmsBody();
//        StringUtilHtml.transToText(main);
//        StringUtilHtml.deleteHtmlTag(main);
//        String convertMain = paramMdl.getSml030SmlList().get(0).getSmsBody();

        main = StringUtilHtml.transToText(
                StringUtilHtml.delHTMLTag(StringUtilHtml.transBRtoCRLF(main)));


        //PDF用モデルにデータセット
        SmlPdfModel pdfModel = new SmlPdfModel();
        pdfModel.setAccName(accName);
        pdfModel.setTitle(title);
        pdfModel.setSender(sender);
        pdfModel.setDate(date);
        pdfModel.setAtesaki(atesaki);
        pdfModel.setAtesakiCC(atesakiCC);
        pdfModel.setAtesakiBCC(atesakiBCC);
        pdfModel.setMark(mark);
        pdfModel.setTempFile(tempFile);
        pdfModel.setMain(main);

        UDate bookDate = paramMdl.getSml030SmlList().get(0).getSmsSdate();
        if (bookDate == null) {
            bookDate = paramMdl.getSml030SmlList().get(0).getSmsEdate();
        }
        String bookName = UDateUtil.getYYMD(bookDate)
                + "_" + UDateUtil.getSeparateHMS(bookDate)
                + "_" + pdfModel.getTitle();

        //使用可能なファイル名かチェック
        bookName = fileNameCheck(bookName);

        String outBookName = bookName + ".pdf";
        pdfModel.setFileName(outBookName);

        //ダウンロード時のファイル名
        String saveFileName = String.valueOf(
                paramMdl.getSml030SmlList().get(0).getSmlSid()) + ".pdf";
        pdfModel.setSaveFileName(saveFileName);

        try {
            IOTools.isDirCheck(outTempDir, true);
            oStream = new FileOutputStream(outTempDir + saveFileName);
            SmlPdfUtil util = new SmlPdfUtil();
            util.createSmalPdf(pdfModel, appRootPath, oStream);
        } catch (Exception e) {
            log__.error("メール内容PDF出力に失敗しました。", e);
        } finally {
            if (oStream != null) {
                oStream.flush();
                oStream.close();
            }
        }
        log__.debug("メール内容PDF出力を終了します。");

        return pdfModel;
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
     * @return 出力したファイルのパス
     * @throws SQLException SQL実行時例外
     * @throws IOException ファイルの書き出しに失敗
     * @throws IOToolsException テンポラリディレクトリの削除に失敗
     * @throws TempFileException 添付ファイル情報の取得に失敗
     */
    public Sml010ExportFileModel createSmlEml(Connection con, RequestModel reqMdl,
                                                Sml030ParamModel paramMdl,
                                                String appRootPath, String tempDir)
    throws SQLException, IOException, IOToolsException, TempFileException {
        Sml010ExportFileModel resultMdl = null;

        CrlfTerminatedWriter pw = null;
        FileOutputStream fos = null;

        try {

//            //アカウント名
//            String accName = usModel.getUsiseimei();
            //メールSID
            int smlSid = paramMdl.getSml030SmlList().get(0).getSmlSid();
            //件名
            String title = StringUtilHtml.transToText(
                                 paramMdl.getSml030SmlList().get(0).getSmsTitle());
            //送信者
            String sender = paramMdl.getSml030SmlList().get(0).getUsiSei()
                    + " " + paramMdl.getSml030SmlList().get(0).getUsiMei();
            //日時
            UDate date = paramMdl.getSml030SmlList().get(0).getSmsSdate();
            if (date == null) {
                date = paramMdl.getSml030SmlList().get(0).getSmsEdate();
            }
            //宛先
            String atesaki = new String();
            for (int i = 0; i < paramMdl.getSml030SmlList().get(0).getAtesakiList().size(); i++) {
                atesaki += paramMdl.getSml030SmlList().get(0).getAtesakiList().get(i).getUsiSei()
                     + " " + paramMdl.getSml030SmlList().get(0).getAtesakiList().get(i).getUsiMei();
                if (i != paramMdl.getSml030SmlList().get(0).getAtesakiList().size() - 1) {
                    atesaki += " , ";
                }
            }
            //CC
            String atesakiCC = new String();
            for (int i = 0; i < paramMdl.getSml030SmlList().get(0).getCcList().size(); i++) {
                atesakiCC += paramMdl.getSml030SmlList().get(0).getCcList().get(i).getUsiSei()
                        + " " + paramMdl.getSml030SmlList().get(0).getCcList().get(i).getUsiMei();
                if (i != paramMdl.getSml030SmlList().get(0).getCcList().size() - 1) {
                    atesakiCC += " , ";
                }
            }
            //BCC
            String atesakiBCC = new String();
            for (int i = 0; i < paramMdl.getSml030SmlList().get(0).getBccList().size(); i++) {
                atesakiBCC += paramMdl.getSml030SmlList().get(0).getBccList().get(i).getUsiSei()
                        + " " + paramMdl.getSml030SmlList().get(0).getBccList().get(i).getUsiMei();
                if (i != paramMdl.getSml030SmlList().get(0).getBccList().size() - 1) {
                    atesakiBCC += " , ";
                }
            }

            //草稿の場合、CCとBCCを設定
            String mailKbn = NullDefault.getString(
                                        paramMdl.getSml030SmlList().get(0).getMailKbn(), "");
            if (mailKbn.equals(GSConstSmail.TAB_DSP_MODE_SOKO)) {
                SmailDao smailDao = new SmailDao(con);
                ArrayList<AtesakiModel> ccList
                    = smailDao.getSitagakiAtesakiList(
                                                    paramMdl.getSml030SmlList().get(0).getSmlSid(),
                                                    GSConstSmail.SML_SEND_KBN_CC);
                ArrayList<AtesakiModel> bccList
                    = smailDao.getSitagakiAtesakiList(
                                                    paramMdl.getSml030SmlList().get(0).getSmlSid(),
                                                    GSConstSmail.SML_SEND_KBN_BCC);
                //CC
                atesakiCC = "";
                for (int i = 0; i < ccList.size(); i++) {
                    atesakiCC += ccList.get(i).getUsiSei()
                            + " " + ccList.get(i).getUsiMei();
                    if (i != ccList.size() - 1) {
                        atesakiCC += " , ";
                    }
                }
                //BCC
                atesakiBCC = "";
                for (int i = 0; i < bccList.size(); i++) {
                    atesakiBCC += bccList.get(i).getUsiSei()
                            + " " + bccList.get(i).getUsiMei();
                    if (i != bccList.size() - 1) {
                        atesakiBCC += " , ";
                    }
                }
            }

            //本文
            String main = paramMdl.getSml030SmlList().get(0).getSmsBody();
//            StringUtilHtml.transToText(main);
//            StringUtilHtml.deleteHtmlTag(main);
//            String convertMain = paramMdl.getSml030SmlList().get(0).getSmsBody();

            File exportFilePath = new File(tempDir + "/smail/"
                                        + reqMdl.getSession().getId() + "/export/"
                                        + smlSid + ".eml");


            String charset = Encoding.ISO_2022_JP;
            boolean multiPart = paramMdl.getSml030FileList().size() > 0;
            boolean mimeTypeHtml =
                paramMdl.getSml030SmlList().get(0).getSmsType()
                                               == GSConstSmail.SAC_SEND_MAILTYPE_HTML;

            IOTools.isDirCheck(exportFilePath.getParent(), true);
            fos = new FileOutputStream(exportFilePath);
            pw = new CrlfTerminatedWriter(new OutputStreamWriter(fos, charset));

            //メールヘッダ情報をファイルに書き込み
            pw.println("Date: " + date.getIntDay() + " " + getMonthStr(date.getMonth()) + " "
                        + date.getYear() + " " + date.getIntHour() + ":" + date.getIntMinute());
            pw.println("From: " + mimeEncode(sender, "UTF-8") + " @");
            pw.println("To: " + mimeEncode(atesaki, "UTF-8"));
            pw.println("Cc: " + mimeEncode(atesakiCC, "UTF-8"));

            if (mailKbn.equals(GSConstSmail.TAB_DSP_MODE_SOSIN)
            || mailKbn.equals(GSConstSmail.TAB_DSP_MODE_SOKO)) {
                if (!StringUtil.isNullZeroString(atesakiBCC)) {
                    pw.println("Bcc: " + mimeEncode(atesakiBCC, "UTF-8"));
                }
            }

            pw.println("Subject: " + mimeEncode(title, charset));
            pw.println("MIME-Version: 1.0 ");

            //メール本文を書き込み
            if (!mimeTypeHtml && !multiPart) {
                main = StringUtilHtml.transToText(
                        StringUtilHtml.delHTMLTag(StringUtilHtml.transBRtoCRLF(main)));
                pw.println("Content-Type: text/plain; charset=ISO-2022-JP");
                pw.println("Content-Transfer-Encoding: 7bit");
                pw.println("");
                pw.println(main);
            } else {
                //main = StringUtilHtml.transToText(main);
                pw.println("Content-Type: multipart/mixed; ");
                pw.println("    boundary=\"----=_Part_1\"");
                pw.println("Content-Transfer-Encoding: 7bit");
                pw.println("");

                if (mimeTypeHtml) {
                    if (!multiPart) {
                        pw.println("------=_Part_1");
                        pw.println("Content-Type: text/plain; charset=ISO-2022-JP");
                        pw.println("Content-Transfer-Encoding: 7bit");
                        pw.println("");
                        pw.println(StringUtilHtml.transToTextAndDeleteTag(main));
                    }
                    pw.println("");
                    pw.println("------=_Part_1");
                    pw.println("Content-Type: text/html; charset=ISO-2022-JP");
                    pw.println("Content-Transfer-Encoding: 7bit");
                    pw.println("");
                    pw.println(main);
                    pw.println("");
                } else {
                    pw.println("------=_Part_1");
                    pw.println("Content-Type: text/plain; charset=ISO-2022-JP");
                    pw.println("Content-Transfer-Encoding: 7bit");
                    pw.println("");
                    pw.println(StringUtilHtml.transToTextAndDeleteTag(main));
                    pw.println("");
                }

                if (!multiPart) {
                    pw.println("------=_Part_1--");
                }
            }

            List<CmnBinfModel> tempFileList = new ArrayList<CmnBinfModel>();
            tempFileList = paramMdl.getSml030FileList();


            //添付ファイル情報を書き込み
            if (!tempFileList.isEmpty()) {
                ITempFileUtil tempUtil
                    = (ITempFileUtil) GroupSession.getContext().get(GSContext.TEMP_FILE_UTIL);
                String filePath = null;
                List<CmnBinfModel> smlTmpFileList = new ArrayList<CmnBinfModel>();

                for (CmnBinfModel fileData : tempFileList) {

                    CommonBiz cmnBiz = new CommonBiz();
                    CmnBinfModel smlTmpFileMdl
                        = cmnBiz.getBinInfo(con, fileData.getBinSid(),
                                reqMdl.getDomain());
                    smlTmpFileList.add(smlTmpFileMdl);

                    filePath
                        = tempUtil.getDownloadFile(smlTmpFileMdl, appRootPath).getPath();

                    pw.println("");


                    pw.println("------=_Part_1");
                    String fileName = fileData.getBinFileName();
                    pw.println("Content-Type: "
                            + ContentType.getContentType(fileName) + ";");
                    pw.println(" name=\"" + MimeUtility.encodeText(fileName) + "\"");
                    pw.println("Content-Transfer-Encoding: base64");
                    pw.println("Content-Disposition: attachment;");
                    writeTempFileName(pw, fileData);

                    pw.println("");

                    FileInputStream fis = null;
//                    try {
//                        fis = new FileInputStream(filePath);
//                        byte[] buff = new byte[54];
//                        int len = 0;
//                        do {
//                            len = fis.read(buff, 0, buff.length);
//                            pw.println(
//                                new String(Base64.encodeBase64(buff)));
//                        } while (len > 0);
//
//                    } finally {
//                        if (fis != null) {
//                            fis.close();
//                        }
//                    }
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

            if (multiPart) {
                pw.println("------=_Part_1--");
            }

                for (CmnBinfModel smlTmpFileMdl : smlTmpFileList) {
                    smlTmpFileMdl.removeTempFile();
                }
                smlTmpFileList = null;
            }

            pw.flush();

            resultMdl = new Sml010ExportFileModel();
            resultMdl.setMessageNum(smlSid);
            resultMdl.setSubject(title);
            resultMdl.setSdate(paramMdl.getSml030SmlList().get(0).getSmsSdate());
            if (resultMdl.getSdate() == null
            && paramMdl.getSml030SmlList().get(0).getSmsEdate() != null) {
                resultMdl.setSdate(paramMdl.getSml030SmlList().get(0).getSmsEdate());
            }
            resultMdl.setFilePath(exportFilePath);
            resultMdl.setSender(sender);
            resultMdl.setAtesaki(atesaki);
            resultMdl.setAtesakiCC(atesakiCC);
            resultMdl.setAtesakiBCC(atesakiBCC);

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
     * <br>[機  能] ファイル名として使用できるか文字列チェックする。
     * <br>[解  説] /\?*:|"<>. を除去
     *                    255文字超える場合は以降を除去
     * <br>[備  考] OSチェック未実装
     * @param fileName テンポラリディレクトリ
     * @return 出力したファイルのパス
     */
    private String fileNameCheck(String fileName) {
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

    /**
     * <br>[機  能] 月を取得
     * <br>[解  説]
     * @param month 月
     * @return 月の先頭3文字
     */
    public String getMonthStr(int month) {
        String ret = "";
        switch (month) {
            case UDate.JANUARY : //1
                ret = "Jan";
                break;
            case UDate.FEBRUARY : //2
                ret = "Feb";
                break;
            case UDate.MARCH : //3
                ret = "Mar";
                break;
            case UDate.APRIL : //4
                ret = "Apr";
                break;
            case UDate.MAY : //5
                ret = "May";
                break;
            case UDate.JUNE : //6
                ret = "Jun";
                break;
            case UDate.JULY : //7
                ret = "Jul";
                break;
            case UDate.AUGUST : //8
                ret = "Aug";
                break;
            case UDate.SEPTEMBER : //9
                ret = "Sep";
                break;
            case UDate.OCTOBER : //10
                ret = "Oct";
                break;
            case UDate.NOVEMBER : //11
                ret = "Nov";
                break;
            case UDate.DECEMBER : //12
            default:
                ret = "Dec";
                break;
        }
        return ret;
    }

    /**
     * <br>[機  能] 指定した文字列をMIMEエンコードして返す
     * <br>[解  説]
     * <br>[備  考]
     * @param value 文字列
     * @param encode 文字コード
     * @return MIMEエンコードした文字列
     * @throws UnsupportedEncodingException 不正な文字コードを指定
     */
    public String mimeEncode(String value, String encode)
    throws UnsupportedEncodingException {
        if (StringUtil.isNullZeroString(encode)
        || StringUtil.isNullZeroString(value)) {
            return value;
        }
        String mimeEncode = "Q";
        if (encode.equals(Encoding.UTF_8)) {
            mimeEncode = "B";
        }

        return MimeUtility.encodeText(value,
                                                    encode,
                                                    mimeEncode);
    }


    /**
     * <br>[機  能] 指定された文字列をBase64変換する
     * <br>[解  説]
     * <br>[備  考] 76文字ごとに改行を行なう
     * @param value 文字列
     * @param encode 文字コード
     * @return 変換後の文字列
     * @throws UnsupportedEncodingException 文字コードが不正
     */
    public String createMime(String value, String encode)
    throws UnsupportedEncodingException {

        int mimeLineLen = 76;

        String mimeStr = value;
        if (!StringUtil.isNullZeroString(mimeStr)) {
            mimeStr = new String(
                    Base64.encodeBase64(value.getBytes("UTF-8")), "UTF-8");

            if (mimeStr.length() > mimeLineLen) {
                String lines = "";
                for (int idx = 0; idx < mimeStr.length(); idx += mimeLineLen) {
                    int end = idx + mimeLineLen;
                    if (end > mimeStr.length()) {
                        end = mimeStr.length();
                    }
                    String substr = mimeStr.substring(idx, end);
                    if (lines.length() > 0) {
                        lines += "\r\n";
                    }
                    lines += substr;
                }
                mimeStr = lines;
            }
        }

        return mimeStr;
    }

    /**
     * <br>[機  能] エクスポート時のfilenameを設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param pw PrintWriter
     * @param fileData 添付ファイル情報
     * @throws UnsupportedEncodingException charsetが不正
     */
    public void writeTempFileName(PrintWriter pw, CmnBinfModel fileData)
    throws UnsupportedEncodingException {

        String fileName = fileData.getBinFileName();
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
}