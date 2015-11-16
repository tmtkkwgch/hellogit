package jp.groupsession.v2.rng.rng030;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rng.IRingiListener;
import jp.groupsession.v2.rng.RingiListenerModel;
import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.rng.biz.RngBiz;
import jp.groupsession.v2.rng.dao.RingiDao;
import jp.groupsession.v2.rng.dao.RngBinDao;
import jp.groupsession.v2.rng.dao.RngChannelDao;
import jp.groupsession.v2.rng.dao.RngRndataDao;
import jp.groupsession.v2.rng.model.RingiChannelDataModel;
import jp.groupsession.v2.rng.model.RingiDataModel;
import jp.groupsession.v2.rng.model.RngAconfModel;
import jp.groupsession.v2.rng.model.RngBinModel;
import jp.groupsession.v2.rng.model.RngChannelModel;
import jp.groupsession.v2.rng.model.RngRndataModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] 稟議内容確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng030Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rng030Biz.class);

    /** コネクション */
    private Connection con__ = null;
    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl リクエスト情報
     */
    public Rng030Biz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 表示情報の設定を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param appRoot アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリパス
     * @param userMdl セッションユーザ情報
     * @param req リクエスト
     * @throws SQLException SQL実行例外
     * @throws IOException 添付ファイルの操作に失敗
     * @throws IOToolsException 添付ファイルの操作に失敗
     * @throws TempFileException 添付ファイルUtil内での例外
     * @return 稟議が存在するか true:存在する false:存在しない
     */
    public boolean setDspData(RequestModel reqMdl, Rng030ParamModel paramMdl, Connection con,
                            String appRoot, String tempDir, BaseUserModel userMdl,
                            HttpServletRequest req)
    throws IOException, IOToolsException, SQLException, TempFileException {

        int apprMode = paramMdl.getRngApprMode();
        int rngSid = paramMdl.getRngSid();
        int userSid = userMdl.getUsrsid();

        //稟議情報を設定
        RingiDao ringiDao = new RingiDao(con);
        RingiDataModel model = ringiDao.getRingiData(rngSid, userSid);

        //タイトル、内容がnullであれば存在しない
        if (model.getRngTitle() == null || model.getRngContent() == null) {
            return false;
        }

        paramMdl.setRng030Status(model.getRngStatus());
        paramMdl.setRng030apprUser(model.getApprUser());
        paramMdl.setRng030makeDate(model.getStrMakeDate());
        paramMdl.setRng030ViewTitle(model.getRngTitle());
        String content = NullDefault.getString(model.getRngContent(), "");
        content = StringUtilHtml.transToHTmlForTextArea(content);
        content = StringUtil.transToLink(content, StringUtil.OTHER_WIN, true);
        content = StringUtilHtml.returntoBR(content);
        paramMdl.setRng030ViewContent(content);
        paramMdl.setRng030completeFlg(model.getRngCompflg());

        //処理モード設定
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");

        RngChannelDao channelDao = new RngChannelDao(con);
        switch(apprMode) {
            case RngConst.RNG_APPRMODE_DISCUSSING :
                //申請中案件管理画面からの遷移の場合は管理者承認モード
                paramMdl.setRng030CmdMode(Rng030Form.CMDMODE_ADMINAPPR);

                //現在確認中のユーザが最後の承認者ではない場合、スキップボタンを表示する

                //現在確認中のユーザが申請者、最後の承認者以外の場合、スキップボタンを表示する
                RngChannelModel channelData = channelDao.select(rngSid, model.getRngApplicate());
                if (channelData.getRncStatus() != RngConst.RNG_RNCSTATUS_CONFIRM
                && !isLastApproval(con, rngSid)) {
                    paramMdl.setRng030skipBtnFlg(1);
                }
                break;

            case RngConst.RNG_APPRMODE_COMPLETE :
                //完了案件管理画面からの遷移の場合は閲覧モード
                paramMdl.setRng030CmdMode(Rng030Form.CMDMODE_VIEW);
                break;

            case RngConst.RNG_APPRMODE_APPL :
                //処理モード=再申請の場合は閲覧モード
                paramMdl.setRng030CmdMode(Rng030Form.CMDMODE_VIEW);

                if (cmd.equals("rng030")
                || paramMdl.getRngDspMode() == RngConst.RNG_MODE_MAIN) {
                    //稟議一覧からの遷移
                    //またはメインからの遷移の場合はタイトル、内容、添付ファイルを設定
                    paramMdl.setRng030Title(model.getRngTitle());
                    paramMdl.setRng030Content(model.getRngContent());

                    //添付ファイルをテンポラリディレクトリへ移動する
                    RngBiz rngBiz = new RngBiz(con);
                    rngBiz.setRingiTempFileData(con, rngSid, 0, appRoot, tempDir,
                                                new UDate(), reqMdl__);
                }

                break;

            default :
                int rncStatus = model.getRncStatus();

                //稟議が完了している場合
                if (model.getRngCompflg() == RngConst.RNG_COMPFLG_COMPLETE) {

                    if (model.getRncType() == RngConst.RNG_RNCTYPE_APPR) {
                        //承認者の場合は閲覧モード
                        paramMdl.setRng030CmdMode(Rng030Form.CMDMODE_VIEW);
                    } else {
                        //最終確認者
                        if (model.getRncType() == RngConst.RNG_RNCTYPE_CONFIRM
                        && rncStatus == RngConst.RNG_RNCSTATUS_NOSET) {
                            //未確認の場合は確認モード
                            paramMdl.setRng030CmdMode(Rng030Form.CMDMODE_CONFIRM);
                        } else {
                            //確認済みの場合は閲覧モード
                            paramMdl.setRng030CmdMode(Rng030Form.CMDMODE_VIEW);
                        }
                    }

                    //申請者 = ログインユーザの場合、複写して申請ボタンを表示する
                    paramMdl.setRng030copyApplBtn(model.getRngApplicate() == userSid);
                } else {

                    //申請者 != ログインユーザ
                    // かつ稟議一覧またはメインからの遷移の場合はコメントを設定
                    if ((cmd.equals("rng030")
                    || paramMdl.getRngDspMode() == RngConst.RNG_MODE_MAIN)
                    && model.getRngApplicate() != userSid) {
                        RngChannelModel channelMdl = channelDao.select(rngSid, userSid);
                        if (channelMdl != null && paramMdl.getRng030Comment() == null) {
                            paramMdl.setRng030Comment(channelMdl.getRncComment());
                        }
                    }

                    //稟議の承認順がログインユーザ かつ 申請者がログインユーザではない場合
                    //承認モードに設定する
                    if (rncStatus == RngConst.RNG_RNCSTATUS_CONFIRM
                    && model.getRngApplicate() != userSid) {

                        paramMdl.setRng030CmdMode(Rng030Form.CMDMODE_APPR);
                        paramMdl.setRng030rftBtnFlg(1);
                    } else {
                        paramMdl.setRng030CmdMode(Rng030Form.CMDMODE_VIEW);
                    }

                    //稟議の状態 = 否認の時
                    //承認者 = ログインユーザ
                    //またはログインユーザが却下した承認者の前に稟議を確認していた場合
                    //完了ボタンを表示する
                    if (model.getRngStatus() == RngConst.RNG_STATUS_REJECT) {

                        if (model.getRngApplicate() == userSid) {
                            paramMdl.setRng030compBtnFlg(1);
                        } else {
                            if (channelDao.isBeforeApproval(rngSid, userSid)) {
                                paramMdl.setRng030compBtnFlg(1);
                            }
                        }
                    }
            }
        }

        //処理モード=再申請以外の場合は添付情報を設定
        if (apprMode != RngConst.RNG_APPRMODE_APPL) {
            paramMdl.setTmpFileList(ringiDao.getRingiTmpFileList(rngSid, 0));
        }

        //添付ファイル一覧を設定
        CommonBiz cmnBiz = new CommonBiz();
        paramMdl.setRng030fileList(cmnBiz.getTempFileLabelList(tempDir));

        //稟議経路情報を設定
        List<RingiChannelDataModel> channelList = ringiDao.getChannelList(rngSid);
        List<RingiChannelDataModel> apprUser = new ArrayList<RingiChannelDataModel>();
        List<RingiChannelDataModel> confirmUser = new ArrayList<RingiChannelDataModel>();

        for (RingiChannelDataModel channelMdl : channelList) {
            if (channelMdl.getRncType() == RngConst.RNG_RNCTYPE_APPR) {
                apprUser.add(channelMdl);
            } else if (channelMdl.getRncType() == RngConst.RNG_RNCTYPE_CONFIRM) {
                confirmUser.add(channelMdl);
            } else if (channelMdl.getRncType() == RngConst.RNG_RNCTYPE_APPL) {
                //申請者の経路情報は一番上に設定
                apprUser.add(0, channelMdl);
            }
        }
        paramMdl.setChannelList(apprUser);
        paramMdl.setConfirmChannelList(confirmUser);
        paramMdl.setChannelListCount(String.valueOf(apprUser.size()));
        paramMdl.setConfirmChannelListCount(String.valueOf(confirmUser.size()));

        log__.debug("End");
        return true;
    }

    /**
     * <br>[機  能] 稟議情報の添付ファイルをテンポラリディレクトリに移動する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param rngSid 稟議SID
     * @param userSid ユーザSID
     * @param appRoot アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリパス
     * @throws SQLException 実行例外
     * @throws IOException 添付ファイルの操作に失敗
     * @throws IOToolsException 添付ファイルの操作に失敗
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public void readRngBinData(Connection con, int rngSid, int userSid,
                            String appRoot, String tempDir)
    throws IOException, IOToolsException, SQLException, TempFileException {

        RngBiz rngBiz = new RngBiz(con);
        rngBiz.setRingiTempFileData(con, rngSid, userSid, appRoot, tempDir,
                                    new UDate(), reqMdl__);
    }

    /**
     * <br>[機  能] 稟議の承認を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param cntCon MlCountMtController
     * @param userSid セッションユーザSID
     * @param appRootPath アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリ
     * @param pluginConfig プラグイン情報
     * @param smailPluginUseFlg ショートメールプラグイン有効フラグ
     * @param reqMdl リクエスト情報
     * @throws Exception 実行例外
     */
    public void approvalRingi(Rng030ParamModel paramMdl,
                                MlCountMtController cntCon,
                                int userSid,
                                String appRootPath,
                                String tempDir,
                                PluginConfig pluginConfig,
                                boolean smailPluginUseFlg,
                                RequestModel reqMdl)
    throws Exception {
        log__.debug("START");

        //現在時刻
        UDate now = new UDate();

        int rngSid = paramMdl.getRngSid();

        //稟議経路情報の更新
        RngChannelDao channelDao = new RngChannelDao(con__);
        RngChannelModel channelMdl = __createChannelModel(paramMdl, userSid, now);
        channelMdl.setRncComment(paramMdl.getRng030Comment());
        channelMdl.setRncStatus(RngConst.RNG_RNCSTATUS_APPR);
        channelMdl.setRncChkdate(now);
        channelDao.updateChannel(channelMdl);

        //稟議添付情報の更新
        __updateRngBin(con__, cntCon, rngSid, userSid, appRootPath, tempDir, now);

        //通知リスナーの情報を取得
        RngBiz rngBiz = new RngBiz(con__);
        RingiListenerModel listenerMdl = rngBiz.createListenerModel(
                con__, cntCon, rngSid, appRootPath, pluginConfig, smailPluginUseFlg);

        IRingiListener[] listenerList = rngBiz.getRingiListeners(pluginConfig);

        int nextUserSid = channelDao.getNextApproval(channelMdl, 1);
        if (nextUserSid < 0) {
            //ログインユーザが最終承認者の場合、稟議を完了する
            RngRndataDao rngDao = new RngRndataDao(con__);
            RngRndataModel rngData = __createRndataModel(paramMdl, userSid, now);
            rngData.setRngStatus(RngConst.RNG_STATUS_SETTLED);
            rngData.setRngAdmcomment(null);
            rngDao.completeRingi(rngData, true);
            String url = rngBiz.createThreadUrl(reqMdl, rngData.getRngSid());
            //URLを設定
            listenerMdl.setRngUrl(url);

            //最終確認者の受信日を更新する
            channelDao.updateRcvdateForConfirmUser(rngSid, userSid, now);

            if (!rngBiz.isCheckRngAconfSmlNot(con__)) {
                RngAconfModel rngAdmMdl = rngBiz.getRngAconf(con__);
                listenerMdl.setSmlNtf(rngAdmMdl.getRarSmlNtf());

                //稟議承認(完了)通知を行う
                for (IRingiListener listener : listenerList) {
                    listener.doApprovalCompleteRingi(listenerMdl, reqMdl);
                }
            }

        } else {
            //ログインユーザが最終承認者ではない場合、次の承認者の稟議経路情報を更新する
            channelMdl.setRncStatus(RngConst.RNG_RNCSTATUS_CONFIRM);
            channelMdl.setRncRcvdate(now);
            channelMdl.setRncChkdate(null);
            channelMdl.setUsrSid(nextUserSid);
            channelDao.updateApprovalChannel(channelMdl);

            //稟議を申請中状態にする
            RngRndataDao rngDao = new RngRndataDao(con__);
            RngRndataModel rngData = __createRndataModel(paramMdl, userSid, now);
            rngData.setRngStatus(RngConst.RNG_STATUS_REQUEST);
            rngDao.updateRingiStatus(rngData);
            String url = rngBiz.createThreadUrl(reqMdl, rngData.getRngSid());
            //URLを設定
            listenerMdl.setRngUrl(url);

            if (!rngBiz.isCheckRngAconfSmlNot(con__)) {
                RngAconfModel rngAdmMdl = rngBiz.getRngAconf(con__);
                listenerMdl.setSmlNtf(rngAdmMdl.getRarSmlNtf());

                //稟議承認通知を行う
                for (IRingiListener listener : listenerList) {
                    listener.doApprovalRingi(listenerMdl, reqMdl);
                }
            }
        }

        log__.debug("End");
    }

    /**
     * <br>[機  能] 稟議の却下を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param cntCon MlCountMtController
     * @param userSid セッションユーザSID
     * @param appRootPath アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリ
     * @param pluginConfig プラグイン情報
     * @param smailPluginUseFlg ショートメールプラグイン有効フラグ
     * @param reqMdl リクエスト情報
     * @throws Exception 実行例外
     */
    public void denialRingi(Rng030ParamModel paramMdl,
                            MlCountMtController cntCon,
                            int userSid,
                            String appRootPath,
                            String tempDir,
                            PluginConfig pluginConfig,
                            boolean smailPluginUseFlg,
                            RequestModel reqMdl)
    throws Exception {
        log__.debug("START");

        //現在時刻
        UDate now = new UDate();

        int rngSid = paramMdl.getRngSid();

        //稟議経路情報の更新
        RngChannelDao channelDao = new RngChannelDao(con__);
        RngChannelModel channelMdl = __createChannelModel(paramMdl, userSid, now);
        channelMdl.setRncComment(paramMdl.getRng030Comment());
        channelMdl.setRncStatus(RngConst.RNG_RNCSTATUS_DENIAL);
        channelMdl.setRncChkdate(now);
        channelDao.updateChannel(channelMdl);

        //稟議添付情報の更新
        __updateRngBin(con__, cntCon, rngSid, userSid, appRootPath, tempDir, now);

        RngBiz rngBiz = new RngBiz(con__);
        rngBiz.getRingiListeners(pluginConfig);

        //稟議を却下状態で完了する
        RngRndataDao rngDao = new RngRndataDao(con__);
        RngRndataModel rngData = __createRndataModel(paramMdl, userSid, now);
        rngData.setRngStatus(RngConst.RNG_STATUS_REJECT);
        rngData.setRngAdmcomment(null);
        rngDao.completeRingi(rngData, true);

        //最終確認者の受信日を更新する
        channelDao.updateRcvdateForConfirmUser(rngSid, userSid, now);

        IRingiListener[] listenerList = rngBiz.getRingiListeners(pluginConfig);

        //リスナーに定義された稟議完了時の処理を行う
        RingiListenerModel listenerMdl =
            rngBiz.createListenerModel(con__, cntCon, rngSid, appRootPath,
                    pluginConfig, smailPluginUseFlg);
        String url = rngBiz.createThreadUrl(reqMdl, rngData.getRngSid());
        //URLを設定
        listenerMdl.setRngUrl(url);

        if (!rngBiz.isCheckRngAconfSmlNot(con__)) {
            RngAconfModel rngAdmMdl = rngBiz.getRngAconf(con__);
            listenerMdl.setSmlNtf(rngAdmMdl.getRarSmlNtf());

            for (IRingiListener listener : listenerList) {
                listener.doRejectRingi(listenerMdl, reqMdl);
            }
        }

        log__.debug("End");
    }

    /**
     * <br>[機  能] 稟議の差し戻しを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param cntCon MlCountMtController
     * @param userSid セッションユーザSID
     * @param appRootPath アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリ
     * @param pluginConfig プラグイン情報
     * @param smailPluginUseFlg ショートメールプラグイン有効フラグ
     * @param reqMdl リクエスト情報
     * @throws Exception 実行例外
     */
    public void reflectionRingi(Rng030ParamModel paramMdl,
                                MlCountMtController cntCon,
                                int userSid,
                                String appRootPath,
                                String tempDir,
                                PluginConfig pluginConfig,
                                boolean smailPluginUseFlg,
                                RequestModel reqMdl)
    throws Exception {
        log__.debug("START");

        log__.debug("START");

        //現在時刻
        UDate now = new UDate();

        int rngSid = paramMdl.getRngSid();

        //稟議経路情報の更新
        RngChannelDao channelDao = new RngChannelDao(con__);
        RngChannelModel channelMdl = __createChannelModel(paramMdl, userSid, now);
        channelMdl.setRncComment(paramMdl.getRng030Comment());
        channelMdl.setRncStatus(RngConst.RNG_RNCSTATUS_DENIAL);
        channelMdl.setRncChkdate(now);
        channelDao.updateChannel(channelMdl);

        //稟議添付情報の更新
        __updateRngBin(con__, cntCon, rngSid, userSid, appRootPath, tempDir, now);

        //稟議を却下状態にする
        RngRndataDao rngDao = new RngRndataDao(con__);
        RngRndataModel rngData = __createRndataModel(paramMdl, userSid, now);
        rngData.setRngStatus(RngConst.RNG_STATUS_REJECT);
        rngDao.updateRingiStatus(rngData);

        //次に確認するユーザの経路情報を更新
        int beforeUserSid = channelDao.getNextApproval(channelMdl, 0);
        channelMdl.setRncStatus(RngConst.RNG_RNCSTATUS_CONFIRM);
        channelMdl.setRncRcvdate(now);
        channelMdl.setRncChkdate(null);
        if (beforeUserSid > 0) {
            //最初の承認者ではない場合、前の承認者の稟議経路情報を更新する
            channelMdl.setUsrSid(beforeUserSid);
            channelDao.updateApprovalChannel(channelMdl);
        } else {
            //最初の承認者の場合、申請者の経路情報を更新する
            channelDao.updateApplicateChannel(channelMdl);
        }

        //リスナーに定義された稟議差し戻し時の処理を行う
        RngBiz rngBiz = new RngBiz(con__);
        RingiListenerModel listenerMdl
            = rngBiz.createListenerModel(con__, cntCon, rngSid,
                                        appRootPath, pluginConfig, smailPluginUseFlg);
        String url = rngBiz.createThreadUrlRef(reqMdl, rngData.getRngSid());
        //URLを設定
        listenerMdl.setRngUrl(url);

        if (!rngBiz.isCheckRngAconfSmlNot(con__)) {
            RngAconfModel rngAdmMdl = rngBiz.getRngAconf(con__);
            listenerMdl.setSmlNtf(rngAdmMdl.getRarSmlNtf());

            IRingiListener[] listenerList = rngBiz.getRingiListeners(pluginConfig);
            for (IRingiListener listener : listenerList) {
                listener.doReflectionRingi(listenerMdl, reqMdl);
            }
        }

        log__.debug("End");
    }

    /**
     * <br>[機  能] 稟議の完了を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param appRootPath アプリケーションのルートパス
     * @param userSid セッションユーザSID
     * @param pluginConfig プラグイン情報
     * @throws Exception 実行例外
     */
    public void completeRingi(Rng030ParamModel paramMdl, Connection con, int userSid,
                            MlCountMtController cntCon,
                            String appRootPath,
                            PluginConfig pluginConfig)
    throws Exception {
        log__.debug("START");

        //現在時刻
        UDate now = new UDate();

        //稟議を完了状態にする
        RngRndataDao rngDao = new RngRndataDao(con);
        RngRndataModel rngData = __createRndataModel(paramMdl, userSid, now);
        rngDao.completeRingi(rngData, false);

        //最終確認者の受信日を更新する
        RngChannelDao channelDao = new RngChannelDao(con);
        channelDao.updateRcvdateForConfirmUser(rngData.getRngSid(), userSid, now);

        log__.debug("End");
    }


    /**
     * <br>[機  能] 稟議の確認を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param userSid セッションユーザSID
     * @param appRootPath アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリ
     * @throws Exception 実行例外
     */
    public void confirmationRingi(Rng030ParamModel paramMdl,
                                Connection con,
                                MlCountMtController cntCon,
                                int userSid,
                                String appRootPath,
                                String tempDir)
    throws Exception {
        log__.debug("START");

        //現在時刻
        UDate now = new UDate();

        //稟議経路情報の更新
        RngChannelDao channelDao = new RngChannelDao(con);
        RngChannelModel channelMdl = __createChannelModel(paramMdl, userSid, now);
        channelMdl.setRncComment(paramMdl.getRng030Comment());
        channelMdl.setRncStatus(RngConst.RNG_RNCSTATUS_CONFIRMATION);
        channelMdl.setRncChkdate(now);
        channelDao.updateChannel(channelMdl);

        //最終確認者の受信日を更新する
        channelDao.updateRcvdateForConfirmUser(channelMdl.getRngSid(), userSid, now);

        //稟議添付情報の更新
        __updateRngBin(con, cntCon, paramMdl.getRngSid(), userSid, appRootPath, tempDir, now);

        log__.debug("End");
    }

    /**
     * <br>[機  能] 稟議の強制完了を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param appRootPath アプリケーションのルートパス
     * @param userSid セッションユーザSID
     * @param pluginConfig プラグイン情報
     * @throws Exception 実行例外
     */
    public void compelCompleteRingi(Rng030ParamModel paramMdl, Connection con, int userSid,
                                    MlCountMtController cntCon, String appRootPath,
                                    PluginConfig pluginConfig)
    throws Exception {
        log__.debug("START");

        //現在時刻
        UDate now = new UDate();

        //稟議を完了状態にする
        RngRndataDao rngDao = new RngRndataDao(con);
        RngRndataModel rngData = __createRndataModel(paramMdl, userSid, now);
        rngData.setRngAdmcomment(paramMdl.getRng030Comment());
        rngDao.completeRingi(rngData, false);

        //最終確認者の受信日を更新する
        RngChannelDao channelDao = new RngChannelDao(con);
        channelDao.updateRcvdateForConfirmUser(rngData.getRngSid(), userSid, now);

        log__.debug("End");
    }

    /**
     * <br>[機  能] 稟議の強制削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param userSid セッションユーザSID
     * @throws Exception 実行例外
     */
    public void compelDeleteRingi(Rng030ParamModel paramMdl, Connection con, int userSid)
    throws Exception {
        log__.debug("START");

        int rngSid = paramMdl.getRngSid();
        UDate now = new UDate();

        //稟議情報の削除
        RngRndataDao rngDao = new RngRndataDao(con);
        rngDao.delete(rngSid);

        //稟議経路情報の削除
        RngChannelDao channelDao = new RngChannelDao(con);
        channelDao.delete(rngSid);

        //バイナリー情報の論理削除
        RingiDao ringiDao = new RingiDao(con);
        ringiDao.removeRngBinData(rngSid, userSid, now);

        //稟議添付情報の削除
        RngBinDao binDao = new RngBinDao(con);
        binDao.delete(rngSid);

        log__.debug("End");
    }

    /**
     * <br>[機  能] 稟議のスキップを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param userSid セッションユーザSID
     * @throws Exception 実行例外
     */
    public void skipRingi(Rng030ParamModel paramMdl, Connection con, int userSid)
    throws Exception {
        log__.debug("START");

        int rngSid = paramMdl.getRngSid();
        UDate now = new UDate();

        RngChannelDao channelDao = new RngChannelDao(con);
        //現在確認中のユーザを取得する
        int confirmUserSid = channelDao.getApprovalUser(rngSid);

        //稟議経路情報の更新
        RngChannelModel channelMdl = __createChannelModel(paramMdl, userSid, now);
        channelMdl.setUsrSid(confirmUserSid);
        channelMdl.setRncComment(paramMdl.getRng030Comment());
        channelMdl.setRncStatus(RngConst.RNG_RNCSTATUS_NOSET);
        channelMdl.setRncChkdate(null);
        channelDao.updateChannel(channelMdl);


        //次の承認者の稟議経路情報を更新する
        int nextUserSid = channelDao.getNextApproval(channelMdl, 1);
        if (nextUserSid > 0) {
            channelMdl.setUsrSid(nextUserSid);
            channelMdl.setRncStatus(RngConst.RNG_RNCSTATUS_CONFIRM);
            channelMdl.setRncRcvdate(now);
            channelMdl.setRncChkdate(null);
            channelDao.updateApprovalChannel(channelMdl);
        } else {
            //次の承認者が存在しない場合、稟議を完了する
            RngRndataDao rngDao = new RngRndataDao(con);
            RngRndataModel rngData = new RngRndataModel();

            rngData.setRngSid(rngSid);
            rngData.setRngAuid(userSid);
            rngData.setRngAdate(now);
            rngData.setRngEuid(userSid);
            rngData.setRngEdate(now);
            rngDao.completeRingi(rngData, false);

            //最終確認者の受信日を更新する
            channelDao.updateRcvdateForConfirmUser(rngData.getRngSid(), userSid, now);
        }

        log__.debug("End");
    }


    /**
     * <br>[機  能] 稟議の再申請を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param cntCon MlCountMtController
     * @param userSid セッションユーザSID
     * @param appRootPath アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリ
     * @param pluginConfig プラグイン情報
     * @param smailPluginUseFlg ショートメールプラグイン有効フラグ
     * @throws Exception 実行例外
     */
    public void applicateRingi(Rng030ParamModel paramMdl,
                                MlCountMtController cntCon,
                                int userSid,
                                String appRootPath,
                                String tempDir,
                                PluginConfig pluginConfig,
                                boolean smailPluginUseFlg)
    throws Exception {

        log__.debug("START");

        int rngSid = paramMdl.getRngSid();
        UDate now = new UDate();

        //稟議情報の更新
        RngRndataModel rngMdl = new RngRndataModel();
        rngMdl.setRngSid(rngSid);
        rngMdl.setRngTitle(paramMdl.getRng030Title());
        rngMdl.setRngContent(paramMdl.getRng030Content());
        rngMdl.setRngMakedate(now);
        rngMdl.setRngApplicate(userSid);
        rngMdl.setRngAppldate(now);
        rngMdl.setRngStatus(RngConst.RNG_STATUS_REQUEST);
        rngMdl.setRngCompflg(0);
        rngMdl.setRngAdmcomment(null);
        rngMdl.setRngEuid(userSid);
        rngMdl.setRngEdate(now);

        RngRndataDao rngDao = new RngRndataDao(con__);
        rngDao.update(rngMdl);


        //稟議経路情報の更新
        RngChannelDao channelDao = new RngChannelDao(con__);
        RngChannelModel channelMdl = __createChannelModel(paramMdl, userSid, now);
        channelMdl.setUsrSid(userSid);
        channelMdl.setRncComment(null);
        channelMdl.setRncStatus(RngConst.RNG_RNCSTATUS_NOSET);
        channelMdl.setRncChkdate(null);
        channelDao.updateChannel(channelMdl);

        //削除されたユーザを除外する
        int delCnt = channelDao.deleteChannelForDelUser(rngSid);
        if (delCnt > 0) {
            List<Integer> apprUserList = channelDao.getApprUserList(rngSid);
            int sort = 1;
            for (int apprUserSid : apprUserList) {
                channelDao.updateChannelSort(rngSid, apprUserSid, sort);
                sort++;
            }
        }
        //最初の承認者の稟議経路情報を更新する
        channelMdl.setRncStatus(RngConst.RNG_RNCSTATUS_CONFIRM);
        channelMdl.setRncRcvdate(now);
        channelMdl.setRncChkdate(null);
        channelDao.updateFirstChannel(channelMdl);

        //稟議添付情報の登録を行う
        RngBiz biz = new RngBiz(con__, cntCon);
        biz.insertRngBin(rngSid, userSid, now,
                        appRootPath, tempDir, RngConst.RNG_CMDMODE_EDIT, RngBiz.APPLMODE_REAPPL);

        log__.debug("End");
    }

    /**
     * <br>[機  能] 指定したユーザが最初の承認者かを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param rngSid 稟議SID
     * @param userSid ユーザSID
     * @return 判定結果 true:最初の承認者 false:最初の承認者ではない
     * @throws SQLException SQL実行時例外
     */
    public boolean isFirstApproval(Connection con, int rngSid, int userSid) throws SQLException {
        RngChannelDao channelDao = new RngChannelDao(con);
        return channelDao.isFirstApprUser(rngSid, userSid);
    }

    /**
     * <br>[機  能] 現在確認中のユーザが最終承認者かを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param rngSid 稟議SID
     * @return 判定結果 true:最終承認者 false:最終承認者ではない
     * @throws SQLException SQL実行時例外
     */
    public boolean isLastApproval(Connection con, int rngSid) throws SQLException {
        RngChannelDao channelDao = new RngChannelDao(con);
        return channelDao.isLastApproval(rngSid);
    }

    /**
     * <br>[機  能] 稟議添付情報の更新を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param rngSid 稟議SID
     * @param userSid セッションユーザSID
     * @param appRootPath アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリ
     * @param now 現在日時
     * @throws SQLException SQL実行時例外
     * @throws IOException 添付ファイルの保存に失敗
     * @throws IOToolsException 添付ファイルの保存に失敗
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    private void __updateRngBin(Connection con, MlCountMtController cntCon,
                                int rngSid, int userSid,
                                String appRootPath, String tempDir,
                                UDate now)
    throws SQLException, IOException, IOToolsException, TempFileException {

        //稟議添付情報の登録
        CommonBiz cmnBiz = new CommonBiz();
        RngBinDao binDao = new RngBinDao(con);

        //更新の場合はバイナリー情報の論理削除、稟議添付情報の削除を行う
        RingiDao ringiDao = new RingiDao(con);
        ringiDao.removeRngBinData(rngSid, userSid, userSid, now);
        binDao.delete(rngSid, userSid);

        //バイナリー情報の登録
        List < String > binSidList = cmnBiz.insertBinInfo(con, tempDir, appRootPath,
                                                        cntCon, userSid, now);

        //稟議添付情報の登録
        if (binSidList != null && !binSidList.isEmpty()) {
            RngBinModel binMdl = new RngBinModel();
            binMdl.setRngSid(rngSid);
            binMdl.setUsrSid(userSid);

            for (String binSid : binSidList) {
                binMdl.setBinSid(Long.parseLong(binSid));
                binDao.insert(binMdl);
            }
        }
    }

    /**
     * <br>[機  能] 稟議情報Modelを作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param userSid ユーザSID
     * @param now 現在日時
     * @return 稟議情報Model
     */
    private RngRndataModel __createRndataModel(Rng030ParamModel paramMdl, int userSid, UDate now) {
        RngRndataModel rngMdl = new RngRndataModel();
        rngMdl.setRngSid(paramMdl.getRngSid());

        rngMdl.setRngAuid(userSid);
        rngMdl.setRngAdate(now);
        rngMdl.setRngEuid(userSid);
        rngMdl.setRngEdate(now);

        return rngMdl;
    }

    /**
     * <br>[機  能] 稟議経路情報Modelを作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param userSid ユーザSID
     * @param now 現在日時
     * @return 稟議経路情報Model
     */
    private RngChannelModel __createChannelModel(Rng030ParamModel paramMdl,
                                                int userSid, UDate now) {
        RngChannelModel channelMdl = new RngChannelModel();
        channelMdl.setRngSid(paramMdl.getRngSid());
        channelMdl.setUsrSid(userSid);
        channelMdl.setRncAuid(userSid);
        channelMdl.setRncAdate(now);
        channelMdl.setRncEuid(userSid);
        channelMdl.setRncEdate(now);

        return channelMdl;
    }

    /**
     * <br>[機  能] 処理モードにより遷移先を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param paramMdl パラメータ情報
     * @return ActionForward
     */
    public ActionForward getForward(ActionMapping map, Rng030ParamModel paramMdl) {

        ActionForward forward = null;

        if (paramMdl.getRng130searchFlg() == 1) {
            return map.findForward("search");
        }

        if (paramMdl.getRngApprMode() == RngConst.RNG_APPRMODE_DISCUSSING) {
            //申請中案件管理画面へ遷移
            forward = map.findForward("rng050");
        } else if (paramMdl.getRngApprMode() == RngConst.RNG_APPRMODE_COMPLETE) {
            //完了案件管理画面へ遷移
            forward = map.findForward("rng070");
        } else {
            if (paramMdl.getRngDspMode() == RngConst.RNG_MODE_MAIN) {
                //メインへ遷移
                forward = map.findForward("gf_main");
            } else {
                //稟議一覧画面へ遷移
                forward = map.findForward("rng010");
            }
        }

        return forward;
    }

}
