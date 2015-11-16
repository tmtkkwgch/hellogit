package jp.groupsession.v2.wml.wml040;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.io.ObjectFile;
import jp.groupsession.v2.cmn.GSConstWebmail;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.base.CmnThemeDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnThemeModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.wml.biz.WmlBiz;
import jp.groupsession.v2.wml.dao.base.WmlAccountDao;
import jp.groupsession.v2.wml.dao.base.WmlAccountSignDao;
import jp.groupsession.v2.wml.dao.base.WmlAccountUserDao;
import jp.groupsession.v2.wml.dao.base.WmlAccountUserProxyDao;
import jp.groupsession.v2.wml.dao.base.WmlAdmConfDao;
import jp.groupsession.v2.wml.model.base.WmlAccountModel;
import jp.groupsession.v2.wml.model.base.WmlAccountSignModel;
import jp.groupsession.v2.wml.model.base.WmlAccountUserModel;
import jp.groupsession.v2.wml.model.base.WmlAdmConfModel;
import jp.groupsession.v2.wml.wml040kn.Wml040knForm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] WEBメール アカウント登録画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml040Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Wml040Biz.class);

    /**
     * <br>[機  能] 初期表示設定を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param tempRootPath テンポラリルートパス
     * @throws Exception 実行時例外
     */
    public void setInitData(Connection con, Wml040ParamModel paramMdl, RequestModel reqMdl,
                                    String tempRootPath)
    throws Exception {

        //管理者設定情報を取得
        WmlAdmConfDao admConfDao = new WmlAdmConfDao(con);
        WmlAdmConfModel admConfMdl = admConfDao.selectAdmData();
        paramMdl.setWml040PermitKbn(admConfMdl.getWadPermitKbn());
        paramMdl.setWml040admDisk(admConfMdl.getWadDisk());
        paramMdl.setWml040admDiskSize(admConfMdl.getWadDiskSize());

        //代理人 使用許可
        WmlBiz wmlBiz = new WmlBiz();
        paramMdl.setWml040proxyUserFlg(wmlBiz.isProxyUserAllowed(con));

        //新規登録 初期表示
        if (paramMdl.getWml040initFlg() == GSConstWebmail.DSP_FIRST
                && paramMdl.getWmlCmdMode() == GSConstWebmail.CMDMODE_ADD) {
            paramMdl.setWml040receiveServerPort(String.valueOf(GSConstWebmail.SERVER_DEFO_RESV));
            paramMdl.setWml040sendServerPort(String.valueOf(GSConstWebmail.SERVER_DEFO_SEND));
            paramMdl.setWml040initFlg(GSConstWebmail.DSP_ALREADY);

            //詳細設定
            if (paramMdl.getWml040PermitKbn() == GSConstWebmail.PERMIT_ADMIN) {
                //管理者一括
                //受信時間間隔(未設定の0をセット)
                paramMdl.setWml040AutoReceiveTime(GSConstWebmail.AUTO_RECEIVE_FIRST);
                //ディスク容量
                paramMdl.setWml040diskSize(GSConstWebmail.WAC_DISK_UNLIMITED);
                //受信時削除
                paramMdl.setWml040delReceive(GSConstWebmail.WAC_DELRECEIVE_NO);
                //自動受信
                paramMdl.setWml040autoResv(GSConstWebmail.MAIL_AUTO_RSV_ON);

                //宛先の確認
                paramMdl.setWml040checkAddress(GSConstWebmail.WAC_CHECK_ADDRESS_NOCHECK);
                //添付ファイルの確認
                paramMdl.setWml040checkFile(GSConstWebmail.WAC_CHECK_FILE_NOCHECK);
                //添付ファイル自動圧縮
                paramMdl.setWml040compressFile(GSConstWebmail.WAC_COMPRESS_FILE_NOTCOMPRESS);
                //時間差送信
                paramMdl.setWml040timeSent(GSConstWebmail.WAC_TIMESENT_NOSET);

            } else {
                //アカウント単位
                //受信時間間隔
                paramMdl.setWml040AutoReceiveTime(admConfMdl.getWadAutoReceiveTime());
                //ディスク容量
                paramMdl.setWml040diskSize(admConfMdl.getWadDisk());
                if (paramMdl.getWml040diskSize() == GSConstWebmail.WAC_DISK_LIMIT) {
                    //制限あり
                    paramMdl.setWml040diskSizeLimit(String.valueOf(admConfMdl.getWadDiskSize()));
                }
                //受信時削除
                paramMdl.setWml040delReceive(admConfMdl.getWadDelreceive());
                //自動受信
                paramMdl.setWml040autoResv(admConfMdl.getWadAutoreceive());

                //宛先の確認
                paramMdl.setWml040checkAddress(admConfMdl.getWadCheckAddress());
                //添付ファイルの確認
                paramMdl.setWml040checkFile(admConfMdl.getWadCheckFile());
                //添付ファイル自動圧縮
                paramMdl.setWml040compressFile(admConfMdl.getWadCompressFile());
                //添付ファイル自動圧縮 初期値
                if (admConfMdl.getWadCompressFileDef()
                == GSConstWebmail.WAD_COMPRESS_FILE_DEF_COMPRESS) {
                    paramMdl.setWml040compressFileDef(Wml040Form.COMPRESS_FILE_DEF_YES);
                } else {
                    paramMdl.setWml040compressFileDef(Wml040Form.COMPRESS_FILE_DEF_NO);
                }
                //時間差送信
                paramMdl.setWml040timeSent(admConfMdl.getWadTimesent());
                //時間差送信 初期値
                if (admConfMdl.getWadTimesentDef() == GSConstWebmail.WAD_TIMESENT_DEF_LATER) {
                    paramMdl.setWml040timeSentDef(Wml040Form.TIMESENT_DEF_AFTER);
                } else {
                    paramMdl.setWml040timeSentDef(Wml040Form.TIMESENT_DEF_IMM);
                }
            }

            //受信サーバ
            paramMdl.setWml040receiveServer(
                    NullDefault.getString(admConfMdl.getWadReceiveHost(), ""));
            //受信ポート
            paramMdl.setWml040receiveServerPort(String.valueOf(admConfMdl.getWadReceivePort()));
            //受信SSL
            paramMdl.setWml040receiveServerSsl(admConfMdl.getWadReceiveSsl());
            //送信サーバ
            paramMdl.setWml040sendServer(NullDefault.getString(admConfMdl.getWadSendHost(), ""));
            //送信ポート
            paramMdl.setWml040sendServerPort(String.valueOf(admConfMdl.getWadSendPort()));
            //送信SSL
            paramMdl.setWml040sendServerSsl(admConfMdl.getWadSendSsl());

        //編集 初期表示
        } else if (paramMdl.getWml040initFlg() == GSConstWebmail.DSP_FIRST
                && paramMdl.getWmlCmdMode() == GSConstWebmail.CMDMODE_EDIT) {

            int wacSid = paramMdl.getWmlAccountSid();

            //アカウント情報を設定する
            WmlAccountDao accountDao = new WmlAccountDao(con);
            WmlAccountModel accountMdl = accountDao.select(wacSid);

            paramMdl.setWml040name(accountMdl.getWacName());
            paramMdl.setWml040mailAddress(accountMdl.getWacAddress());

            paramMdl.setWml040receiveServer(accountMdl.getWacReceiveHost());
            paramMdl.setWml040receiveServerPort(String.valueOf(accountMdl.getWacReceivePort()));

            paramMdl.setWml040receiveServerSsl(Wml040Form.RECEIVE_SSL_NOTUSE);
            if (accountMdl.getWacReceiveSsl() == GSConstWebmail.WAC_RECEIVE_SSL_USE) {
                paramMdl.setWml040receiveServerSsl(Wml040Form.RECEIVE_SSL_USE);
            }

            paramMdl.setWml040receiveServerType(Wml040Form.RSERVERTYPE_POP);
            if (accountMdl.getWacReceiveType() == GSConstWebmail.WAC_RECEIVE_TYPE_IMAP) {
                paramMdl.setWml040receiveServerType(Wml040Form.RSERVERTYPE_IMAP);
            }

            paramMdl.setWml040receiveServerUser(accountMdl.getWacReceiveUser());
//            paramMdl.setWml040receiveServerPassword(
//                    WmlUtil.decryptMailPassword(accountMdl.getWacReceivePass()));
            paramMdl.setWml040receiveServerPassword(accountMdl.getWacReceivePass());
            paramMdl.setWml040sendServer(accountMdl.getWacSendHost());
            paramMdl.setWml040sendServerPort(String.valueOf(accountMdl.getWacSendPort()));

            paramMdl.setWml040sendServerSsl(Wml040Form.RECEIVE_SSL_NOTUSE);
            if (accountMdl.getWacSendSsl() == GSConstWebmail.WAC_SEND_SSL_USE) {
                paramMdl.setWml040sendServerSsl(Wml040Form.SEND_SSL_USE);
            }

            paramMdl.setWml040sendServerUser(accountMdl.getWacSendUser());
            paramMdl.setWml040sendServerPassword(accountMdl.getWacSendPass());

            if (admConfMdl.getWadDiskComp() == GSConstWebmail.WAC_DISK_ADM_COMP) {
                //ディスク容量 強制制限あり
                paramMdl.setWml040diskSizeComp(GSConstWebmail.WAC_DISK_ADM_COMP);
            }

            paramMdl.setWml040diskSize(Wml040Form.DISKSIZE_UNLIMITED);

            if (accountMdl.getWacDisk() == GSConstWebmail.WAC_DISK_LIMIT) {
                paramMdl.setWml040diskSize(Wml040Form.DISKSIZE_LIMIT);
                paramMdl.setWml040diskSizeLimit(String.valueOf(accountMdl.getWacDiskSize()));
            }

            paramMdl.setWml040biko(accountMdl.getWacBiko());
            paramMdl.setWml040organization(accountMdl.getWacOrganization());
//            paramMdl.setWml040sign(accountMdl.getWacSign());

            //返信時署名位置区分
            paramMdl.setWml040signPoint(accountMdl.getWacSignPointKbn());
            //返信時署名表示区分
            paramMdl.setWml040receiveDsp(accountMdl.getWacSignDspKbn());

            paramMdl.setWml040autoTo(accountMdl.getWacAutoto());
            paramMdl.setWml040autoCc(accountMdl.getWacAutocc());
            paramMdl.setWml040autoBcc(accountMdl.getWacAutobcc());

            paramMdl.setWml040delReceive(Wml040Form.DELRECEIVE_NO);
            if (accountMdl.getWacDelreceive() == GSConstWebmail.WAC_DELRECEIVE_YES) {
                paramMdl.setWml040delReceive(Wml040Form.DELRECEIVE_YES);
            }

            paramMdl.setWml040reReceive(Wml040Form.RERECEIVE_NO);
            if (accountMdl.getWacRereceive() == GSConstWebmail.WAC_DELRECEIVE_YES) {
                paramMdl.setWml040reReceive(Wml040Form.RERECEIVE_YES);
            }

            paramMdl.setWml040apop(Wml040Form.APOP_OFF);
            if (accountMdl.getWacApop() == GSConstWebmail.WAC_APOP_USE) {
                paramMdl.setWml040apop(Wml040Form.APOP_ON);
            }

            paramMdl.setWml040smtpAuth(Wml040Form.SMTPAUTH_OFF);
            if (accountMdl.getWacSmtpAuth() == GSConstWebmail.WAC_SMTPAUTH_YES) {
                paramMdl.setWml040smtpAuth(Wml040Form.SMTPAUTH_ON);
            }

            paramMdl.setWml040popBSmtp(Wml040Form.POPBSMTP_OFF);
            if (accountMdl.getWacPopbsmtp() == GSConstWebmail.WAC_POPBSMTP_YES) {
                paramMdl.setWml040popBSmtp(Wml040Form.POPBSMTP_ON);
            }

            paramMdl.setWml040encodeSend(Wml040Form.ENCODE_SEND_2022JP);
            if (accountMdl.getWacEncodeSend() == GSConstWebmail.WAC_ENCODE_SEND_UTF8) {
                paramMdl.setWml040encodeSend(Wml040Form.ENCODE_SEND_UNICODE);
            }

            paramMdl.setWml040autoResv(GSConstWebmail.MAIL_AUTO_RSV_OFF);
            if (accountMdl.getWacAutoreceive() == Wml040knForm.AUTORESV_ON) {
                paramMdl.setWml040autoResv(GSConstWebmail.MAIL_AUTO_RSV_ON);
            }

            paramMdl.setWml040sendType(GSConstWebmail.WAC_SEND_MAILTYPE_NORMAL);
            if (accountMdl.getWacSendMailtype() == Wml040knForm.SEND_MAIL_HTML) {
                paramMdl.setWml040sendType(GSConstWebmail.WAC_SEND_MAILTYPE_HTML);
            }

            //アカウント処理モード 共通
            if (paramMdl.getWmlAccountMode() == GSConstWebmail.ACCOUNTMODE_COMMON) {
                //使用者を設定
                if (accountMdl.getWacType() != GSConstWebmail.WAC_TYPE_NORMAL) {

                    paramMdl.setWml040userKbn(Wml040Form.USERKBN_USER);
                    if (accountMdl.getWacType() == GSConstWebmail.WAC_TYPE_GROUP) {
                        paramMdl.setWml040userKbn(Wml040Form.USERKBN_GROUP);
                    }

                    WmlAccountUserDao accountUserDao = new WmlAccountUserDao(con);
                    List<WmlAccountUserModel> accountUserList = accountUserDao.select(wacSid);

                    if (accountMdl.getWacType() == GSConstWebmail.WAC_TYPE_GROUP) {
                        String[] id = new String[accountUserList.size()];
                        for (int index = 0; index < id.length; index++) {
                            id[index] = String.valueOf(accountUserList.get(index).getGrpSid());
                        }
                        paramMdl.setWml040userKbnGroup(id);

                    } else if (accountMdl.getWacType() == GSConstWebmail.WAC_TYPE_USER) {
                        String[] id = new String[accountUserList.size()];
                        for (int index = 0; index < id.length; index++) {
                            id[index] = String.valueOf(accountUserList.get(index).getUsrSid());
                        }
                        paramMdl.setWml040userKbnUser(id);
                    }
                }
            }

            //代理人
            if (paramMdl.isWml040proxyUserFlg()) {
                WmlAccountUserProxyDao proxyUserDao = new WmlAccountUserProxyDao(con);
                List<Integer> proxyUserList = proxyUserDao.getProxyUserList(wacSid);
                String[] id = new String[proxyUserList.size()];
                for (int index = 0; index < id.length; index++) {
                    id[index] = String.valueOf(proxyUserList.get(index));
                }
                paramMdl.setWml040proxyUser(id);
            }

            //自動受信時間設定
            if (accountMdl.getWacAutoReceiveTime() == GSConstWebmail.AUTO_RECEIVE_FIRST) {
                //未設定時は管理者のデフォルト値
                paramMdl.setWml040AutoReceiveTime(admConfMdl.getWadAutoReceiveTime());
            } else {
                //設定済の場合
                paramMdl.setWml040AutoReceiveTime(accountMdl.getWacAutoReceiveTime());
            }

            //テーマ
            paramMdl.setWml040theme(accountMdl.getWacTheme());
            //宛先の確認
            paramMdl.setWml040checkAddress(accountMdl.getWacCheckAddress());
            //添付ファイルの確認
            paramMdl.setWml040checkFile(accountMdl.getWacCheckFile());
            //添付ファイル自動圧縮
            paramMdl.setWml040compressFile(accountMdl.getWacCompressFile());
            //添付ファイル自動圧縮 初期値
            if (accountMdl.getWacCompressFileDef()
                    == GSConstWebmail.WAC_COMPRESS_FILE_DEF_COMPRESS) {
                paramMdl.setWml040compressFileDef(Wml040Form.COMPRESS_FILE_DEF_YES);
            } else {
                paramMdl.setWml040compressFileDef(Wml040Form.COMPRESS_FILE_DEF_NO);
            }
            //時間差送信
            paramMdl.setWml040timeSent(accountMdl.getWacTimesent());
            //時間差送信 初期値
            if (accountMdl.getWacTimesentDef() == GSConstWebmail.WAC_TIMESENT_DEF_LATER) {
                paramMdl.setWml040timeSentDef(Wml040Form.TIMESENT_DEF_AFTER);
            } else {
                paramMdl.setWml040timeSentDef(Wml040Form.TIMESENT_DEF_IMM);
            }
            //引用符
            paramMdl.setWml040quotes(accountMdl.getWacQuotes());

            paramMdl.setWml040initFlg(GSConstWebmail.DSP_ALREADY);

            //署名
            WmlAccountSignDao signDao = new WmlAccountSignDao(con);
            List<WmlAccountSignModel> signList = signDao.getSignList(wacSid);
            for (WmlAccountSignModel signDetailData : signList) {
                addSignModel(reqMdl, tempRootPath, signDetailData);
                if (signDetailData.getWsiDef() == GSConstWebmail.WSI_DEF_DEFAULT) {
                    paramMdl.setWml040signNo(signDetailData.getWsiNo());
                }
            }

            //ディスク容量制限 特例設定
            paramMdl.setWml040diskSps(accountMdl.getWacDiskSps());
        }

        //グループコンボを設定
        paramMdl.setUserKbnGroupCombo(createGroupCombo(con, reqMdl));

        //使用者 グループコンボ、ユーザコンボを設定
        _setGroupCombo(con, paramMdl);
        _setUserCombo(con, paramMdl, reqMdl);

        //代理人 ユーザコンボを設定
        if (paramMdl.isWml040proxyUserFlg()) {
            _setProxyUserCombo(con, paramMdl, reqMdl);
        }

        //自動受信時間設定コンボを設定
        if (paramMdl.getWml040PermitKbn()
                == GSConstWebmail.PERMIT_ACCOUNT) {
            //アカウント単位で設定可能時のみ
            setCombData(paramMdl, reqMdl);
        }

        //署名を設定
        paramMdl.setWml040signList(createSignCombo(reqMdl, tempRootPath));

        //テーマコンボを設定
        paramMdl.setWml040themeList(createThemeCombo(con, reqMdl));

        //引用符コンボを設定
        paramMdl.setWml040quotesList(createQuotesCombo(reqMdl));

        //セッションユーザ情報を設定
        paramMdl.setWml040sessionUserData(reqMdl.getSmodel());

        //WEBメール管理者フラグを設定
        CommonBiz cmnBiz = new CommonBiz();
        paramMdl.setWml040webmailAdmin(
                cmnBiz.isPluginAdmin(con, reqMdl.getSmodel(), GSConstWebmail.PLUGIN_ID_WEBMAIL));
    }

    /**
     * <br>[機  能] アカウントの削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param userSid ユーザSID
     * @throws SQLException SQL実行時例外
     */
    public void deleteAccount(Connection con, Wml040ParamModel paramMdl, int userSid)
    throws SQLException {

        log__.info("アカウント削除開始");

        boolean commit = false;
        try {
            WmlAccountDao accountDao = new WmlAccountDao(con);
            accountDao.updateJkbn(paramMdl.getWmlAccountSid(), GSConstWebmail.WAC_JKBN_DELETE);
            con.commit();
            commit = true;
        } catch (SQLException e) {
            log__.info("アカウントの削除に失敗", e);
            throw e;
        } finally {
            if (!commit) {
                con.rollback();
            }
        }

        log__.info("アカウント削除終了");
    }

    /**
     * <br>[機  能] グループコンボを設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @throws SQLException SQL実行時例外
     */
    protected void _setGroupCombo(Connection con, Wml040ParamModel paramMdl)
    throws SQLException {
        List<LabelValueBean> groupCombo = new ArrayList<LabelValueBean>();
        List<LabelValueBean> selectGroupCombo = new ArrayList<LabelValueBean>();

        String[] selectGrpSid = paramMdl.getWml040userKbnGroup();
        if (selectGrpSid == null) {
            selectGrpSid = new String[0];
        }
        Arrays.sort(selectGrpSid);

        GroupBiz grpBiz = new GroupBiz();
        ArrayList<GroupModel> gpList = grpBiz.getGroupCombList(con);
        for (GroupModel grpMdl : gpList) {
            LabelValueBean label = new LabelValueBean(grpMdl.getGroupName(),
                                                String.valueOf(grpMdl.getGroupSid()));
            if (Arrays.binarySearch(selectGrpSid, String.valueOf(grpMdl.getGroupSid())) < 0) {
                groupCombo.add(label);
            } else {
                selectGroupCombo.add(label);
            }
        }

        paramMdl.setUserKbnGroupSelectCombo(selectGroupCombo);
        paramMdl.setUserKbnGroupNoSelectCombo(groupCombo);
    }

    /**
     * <br>[機  能] ユーザコンボを設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @throws SQLException SQL実行時例外
     */
    protected void _setUserCombo(Connection con, Wml040ParamModel paramMdl, RequestModel reqMdl)
    throws SQLException {

        String[] selectUserSid = paramMdl.getWml040userKbnUser();
        if (selectUserSid == null) {
            selectUserSid = new String[0];
        }
        Arrays.sort(selectUserSid);

        GsMessage gsMsg = new GsMessage(reqMdl);
        paramMdl.setUserKbnUserSelectCombo(createUserCombo(con, selectUserSid, gsMsg));

        int grpSid = NullDefault.getInt(paramMdl.getWml040userKbnUserGroup(), -1);
        if (grpSid >= 0) {
            UserBiz userBiz = new UserBiz();
            paramMdl.setUserKbnUserNoSelectCombo(
                    userBiz.getNormalUserLabelList(con, grpSid, selectUserSid, false, gsMsg));
        }
    }

    /**
     * <br>[機  能] 代理人 ユーザコンボを設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @throws SQLException SQL実行時例外
     */
    protected void _setProxyUserCombo(Connection con, Wml040ParamModel paramMdl,
                                                            RequestModel reqMdl)
    throws SQLException {

        String[] selectUserSid = paramMdl.getWml040proxyUser();

        paramMdl.setProxyUserSelectCombo(createProxySelectUserCombo(con, selectUserSid, reqMdl));
        paramMdl.setProxyUserNoSelectCombo(
                createProxyNoSelectUserCombo(con, paramMdl.getWml040proxyUserGroup(),
                                                                selectUserSid, reqMdl));
    }

    /**
     * <br>[機  能]  ユーザコンボを作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param selectUserSid ユーザSID
     * @param gsMsg GsMessage
     * @return ユーザコンボ
     * @throws SQLException SQL実行時例外
     */
    protected List<LabelValueBean> createUserCombo(Connection con, String[] selectUserSid,
                                                                                    GsMessage gsMsg)
    throws SQLException {

        UserBiz userBiz = new UserBiz();
        ArrayList<BaseUserModel> ulist
                = userBiz.getBaseUserList(con, selectUserSid);
        LabelValueBean labelBean = null;
        List <LabelValueBean> selectUserList = new ArrayList<LabelValueBean>();
        for (BaseUserModel umodel : ulist) {
            labelBean = new LabelValueBean();
            labelBean.setLabel(umodel.getUsisei() + " " + umodel.getUsimei());
            labelBean.setValue(String.valueOf(umodel.getUsrsid()));
            selectUserList.add(labelBean);
        }

        return selectUserList;
    }

    /**
     * <br>[機  能] 自動時間区分設定コンボを設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     */
    public void setCombData(Wml040ParamModel paramMdl, RequestModel reqMdl) {
        //自動受信時間ラベル
        paramMdl.setWml040AutoRsvKeyLabel(createAutoRsvTimeCombo(reqMdl));
    }

    /**
     * <br>[機  能] 自動受信時間コンボを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @return 年コンボ
     */
    public static List<LabelValueBean> createAutoRsvTimeCombo(RequestModel reqMdl) {
        GsMessage gsMsg = new GsMessage(reqMdl);
        //自動受信時間ラベル
        ArrayList<LabelValueBean> autoRsvTimeCombo = new ArrayList<LabelValueBean>();

        String minute = gsMsg.getMessage("cmn.minute");
        String time5 = "5" + minute;
        String time10 = "10" + minute;
        String time30 = "30" + minute;
        String time60 = "60" + minute;

        String[] timeKeyAllText = new String[] { time5,
                time10, time30, time60 };

        for (int i = 0; i < GSConstWebmail.LIST_AUTO_REV_KEY_ALL.length; i++) {
            String label = timeKeyAllText[i];
            String value = String.valueOf(GSConstWebmail.LIST_AUTO_REV_KEY_ALL[i]);
            autoRsvTimeCombo.add(new LabelValueBean(label, value));
        }

        return autoRsvTimeCombo;
    }

    /**
     * <br>[機  能] 署名情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param tempRootDir テンポラリルートディレクトリ
     * @return 署名情報
     * @throws IOToolsException 署名情報の取得に失敗
     */
    public Wml040SignModel loadSignModel(RequestModel reqMdl, String tempRootDir)
    throws IOToolsException {
        Wml040SignModel signData = null;
        File signFilePath = __getSignFilePath(reqMdl, tempRootDir);
        IOTools.isDirCheck(signFilePath.getParent(), true);
        if (IOTools.isFileCheck(signFilePath.getParent(), signFilePath.getName(), false)) {
            ObjectFile objFile
                = new ObjectFile(signFilePath.getParent(), signFilePath.getName());
            signData = (Wml040SignModel) objFile.load();
        } else {
            signData = new Wml040SignModel();
        }

        return signData;
    }

    /**
     * <br>[機  能] 署名情報を保存する
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param tempRootDir テンポラリルートディレクトリ
     * @param signData 署名情報
     * @throws IOToolsException 署名情報の取得に失敗
     */
    public void saveSignModel(RequestModel reqMdl, String tempRootDir, Wml040SignModel signData)
    throws IOToolsException {
        File signFilePath = __getSignFilePath(reqMdl, tempRootDir);
       IOTools.isDirCheck(signFilePath.getParent(), true);

        ObjectFile objFile
            = new ObjectFile(signFilePath.getParent(), signFilePath.getName());
        objFile.save(signData);
    }

    /**
     * <br>[機  能] 署名情報を追加する
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param tempRootDir テンポラリルートディレクトリ
     * @param signDetailData 署名情報
     * @throws IOToolsException 署名情報の取得に失敗
     */
    public void addSignModel(RequestModel reqMdl, String tempRootDir,
                                        WmlAccountSignModel signDetailData)
    throws IOToolsException {
        Wml040SignModel signData = loadSignModel(reqMdl, tempRootDir);
        signDetailData.setWsiNo(signData.getSignList().size() + 1);
        signData.getSignList().add(signDetailData);
        saveSignModel(reqMdl, tempRootDir, signData);
    }

    /**
     * <br>[機  能] 署名情報を編集する
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param tempRootDir テンポラリルートディレクトリ
     * @param signDetailData 署名情報
     * @param no No.
     * @throws IOToolsException 署名情報の取得に失敗
     */
    public void editSignModel(RequestModel reqMdl, String tempRootDir,
                                        WmlAccountSignModel signDetailData,
                                        int no)
    throws IOToolsException {
        Wml040SignModel signData = loadSignModel(reqMdl, tempRootDir);
        signData.getSignList().set(no - 1, signDetailData);
        saveSignModel(reqMdl, tempRootDir, signData);
    }

    /**
     * <br>[機  能] 署名情報を削除する
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param tempRootDir テンポラリルートディレクトリ
     * @param no No.
     * @throws IOToolsException 署名情報の取得に失敗
     */
    public void deleteSignModel(RequestModel reqMdl, String tempRootDir,
                                        int no)
    throws IOToolsException {
        Wml040SignModel signData = loadSignModel(reqMdl, tempRootDir);
        int delIdx = no - 1;
        signData.getSignList().remove(delIdx);
        for (; delIdx < signData.getSignList().size(); delIdx++) {
            signData.getSignList().get(delIdx).setWsiNo(
                    signData.getSignList().get(delIdx).getWsiNo() - 1);
        }
        saveSignModel(reqMdl, tempRootDir, signData);
    }

    /**
     * <br>[機  能] 署名の並び替えを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param tempRootDir テンポラリルートディレクトリ
     * @param selectNo 並び替え対象の署名No.
     * @param changeKbn 0:上へ 1:下へ
     * @return 並び替え後の署名No.
     * @throws IOToolsException 署名情報の取得に失敗
     */
    public int sortSignModel(RequestModel reqMdl, String tempRootDir, int selectNo, int changeKbn)
    throws IOToolsException {

        if (selectNo > 0) {
            Wml040Biz biz = new Wml040Biz();
            Wml040SignModel signData = biz.loadSignModel(reqMdl, tempRootDir);
            if (signData != null && !signData.getSignList().isEmpty()) {
                List<WmlAccountSignModel> signList = signData.getSignList();
                int selectIdx = selectNo - 1;
                int changeIdx = 0;
                WmlAccountSignModel selectSign = signList.get(selectIdx);
                WmlAccountSignModel changeSign = null;
                if (changeKbn == GSConstWebmail.SORT_UP) {
                    changeIdx = selectIdx - 1;
                    if (changeIdx >= 0) {
                        selectSign.setWsiNo(selectSign.getWsiNo() - 1);
                        changeSign = signList.get(changeIdx);
                        changeSign.setWsiNo(changeSign.getWsiNo() + 1);
                    }
                } else {
                    changeIdx = selectIdx + 1;
                    if (changeIdx < signList.size()) {
                        selectSign.setWsiNo(selectSign.getWsiNo() + 1);
                        changeSign = signList.get(changeIdx);
                        changeSign.setWsiNo(changeSign.getWsiNo() - 1);
                    }
                }

                if (changeSign != null) {
                    signList.set(selectIdx, changeSign);
                    signList.set(changeIdx, selectSign);
                    signData.setSignList(signList);
                    biz.saveSignModel(reqMdl, tempRootDir, signData);
                    selectNo = selectSign.getWsiNo();
                }
            }
        }

        return selectNo;
    }

    /**
     * <br>[機  能] 署名の一覧を取得するの並び替えを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param tempRootDir テンポラリルートディレクトリ
     * @return 署名一覧
     * @throws IOToolsException 署名情報の取得に失敗
     */
    public List<LabelValueBean> createSignCombo(RequestModel reqMdl, String tempRootDir)
    throws IOToolsException {

        //署名を設定
        List<LabelValueBean> signCombo = new ArrayList<LabelValueBean>();
        Wml040SignModel signData = loadSignModel(reqMdl, tempRootDir);
        if (signData != null) {
            for (WmlAccountSignModel signDetail : signData.getSignList()) {
                LabelValueBean label
                    = new LabelValueBean(signDetail.getWsiTitle(),
                                                    String.valueOf(signDetail.getWsiNo()));
                signCombo.add(label);
            }
        }
        return signCombo;
    }

    /**
     * <br>[機  能] テーマコンボを作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @return テーマコンボ
     * @throws SQLException SQL実行時例外
     */
    public List<LabelValueBean> createThemeCombo(Connection con, RequestModel reqMdl)
    throws SQLException {
        GsMessage gsMsg = new GsMessage(reqMdl);
        List<LabelValueBean> themeCombo = new ArrayList<LabelValueBean>();
        themeCombo.add(
                new LabelValueBean(gsMsg.getMessage("cmn.notset"),
                                            String.valueOf(GSConstWebmail.WAC_THEME_NOSET)));

        CmnThemeDao themeDao = new CmnThemeDao(con);
        List<CmnThemeModel> themeList = themeDao.select();
        for (CmnThemeModel themeData : themeList) {
            themeCombo.add(
                    new LabelValueBean(themeData.getCtmName(),
                                                    String.valueOf(themeData.getCtmSid())));
        }
        return themeCombo;
    }

    /**
     * <br>[機  能] 引用符コンボを作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @return 引用符コンボ
     */
    public List<LabelValueBean> createQuotesCombo(RequestModel reqMdl) {
        //引用符コンボを設定
        List<LabelValueBean> quotesCombo = new ArrayList<LabelValueBean>();
        int[] quotesList = {GSConstWebmail.WAC_QUOTES_DEF,
                                    GSConstWebmail.WAC_QUOTES_NONE,
                                    GSConstWebmail.WAC_QUOTES_2,
                                    GSConstWebmail.WAC_QUOTES_3,
                                    GSConstWebmail.WAC_QUOTES_4,
                                    GSConstWebmail.WAC_QUOTES_5};
        for (int quotes : quotesList) {
            quotesCombo.add(new LabelValueBean(WmlBiz.getViewMailQuotes(quotes, reqMdl),
                                                                    Integer.toString(quotes)));
        }
        return quotesCombo;
    }

    /**
     * <br>[機  能] グループコンボを生成する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @return グループコンボ
     * @throws SQLException SQL実行時例外
     */
    public List<LabelValueBean> createGroupCombo(Connection con,  RequestModel reqMdl)
    throws SQLException {
        GsMessage gsMsg = new GsMessage(reqMdl);
        GroupBiz grpBiz = new GroupBiz();
        List<LabelValueBean> groupCombo = new ArrayList<LabelValueBean>();
        groupCombo.add(new LabelValueBean(gsMsg.getMessage("cmn.select.plz"), "-1"));

        ArrayList<GroupModel> grpList = grpBiz.getGroupCombList(con);
        for (GroupModel grpMdl : grpList) {
            LabelValueBean label = new LabelValueBean(grpMdl.getGroupName(),
                                                    String.valueOf(grpMdl.getGroupSid()));
            groupCombo.add(label);
        }

        return groupCombo;
    }

    /**
     * <br>[機  能] 選択済みの代理人ユーザコンボを生成する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param selectUserSid 選択済みの代理人
     * @param reqMdl リクエスト情報
     * @return 代理人ユーザコンボ
     * @throws SQLException SQL実行時例外
     */
    public List<LabelValueBean> createProxySelectUserCombo(Connection con, String[] selectUserSid,
                                                                                RequestModel reqMdl)
    throws SQLException {

        if (selectUserSid == null) {
            selectUserSid = new String[0];
        }
        Arrays.sort(selectUserSid);

        GsMessage gsMsg = new GsMessage(reqMdl);
        return createUserCombo(con, selectUserSid, gsMsg);
    }

    /**
     * <br>[機  能] 未選択の代理人ユーザコンボを生成する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param grpSid グループSID
     * @param selectUserSid 選択済みの代理人
     * @param reqMdl リクエスト情報
     * @return 代理人ユーザコンボ
     * @throws SQLException SQL実行時例外
     */
    public List<LabelValueBean> createProxyNoSelectUserCombo(Connection con,
                                                                            String grpSid,
                                                                            String[] selectUserSid,
                                                                            RequestModel reqMdl)
    throws SQLException {

        if (selectUserSid == null) {
            selectUserSid = new String[0];
        }
        Arrays.sort(selectUserSid);

        GsMessage gsMsg = new GsMessage(reqMdl);

        int intGrpSid = NullDefault.getInt(grpSid, -1);
        List<LabelValueBean> userCombo = new ArrayList<LabelValueBean>();
        if (intGrpSid >= 0) {
            UserBiz userBiz = new UserBiz();
            userCombo = userBiz.getNormalUserLabelList(con, intGrpSid, selectUserSid, false, gsMsg);
        }

        return userCombo;
    }

    /**
     * <br>[機  能] 署名情報ファイルのファイルパスを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param tempRootDir テンポラリルートディレクトリ
     * @return 署名情報ファイルのファイルパス
     */
    private File __getSignFilePath(RequestModel reqMdl, String tempRootDir) {
        StringBuilder tempDir = new StringBuilder("");
        tempDir.append(tempRootDir);
        tempDir.append("/");
        tempDir.append(GSConstWebmail.PLUGIN_ID_WEBMAIL);
        tempDir.append("/");
        tempDir.append(reqMdl.getSession().getId());
        tempDir.append("/accountSign/");
        tempDir.append("signData");

        return new File(tempDir.toString());
    }
}
