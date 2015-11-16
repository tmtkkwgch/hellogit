package jp.groupsession.v2.sml.sml020;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.WmlDao;
import jp.groupsession.v2.cmn.dao.base.CmnBelongmDao;
import jp.groupsession.v2.cmn.dao.base.CmnBinfDao;
import jp.groupsession.v2.cmn.dao.base.CmnMyGroupDao;
import jp.groupsession.v2.cmn.dao.base.CmnMyGroupMsDao;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.cmn.model.CmnLabelValueModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.WmlMailDataModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.cmn.model.base.CmnMyGroupModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.cmn.model.base.WmlTempfileModel;
import jp.groupsession.v2.sml.GSConstSmail;
import jp.groupsession.v2.sml.biz.SmlCommonBiz;
import jp.groupsession.v2.sml.dao.SmailDao;
import jp.groupsession.v2.sml.dao.SmlAccountDao;
import jp.groupsession.v2.sml.dao.SmlAsakDao;
import jp.groupsession.v2.sml.dao.SmlBanDestDao;
import jp.groupsession.v2.sml.dao.SmlBinDao;
import jp.groupsession.v2.sml.dao.SmlHinaDao;
import jp.groupsession.v2.sml.dao.SmlJmeisDao;
import jp.groupsession.v2.sml.dao.SmlSmeisDao;
import jp.groupsession.v2.sml.dao.SmlUserSearchDao;
import jp.groupsession.v2.sml.dao.SmlWmeisDao;
import jp.groupsession.v2.sml.model.AtesakiModel;
import jp.groupsession.v2.sml.model.SmailDetailModel;
import jp.groupsession.v2.sml.model.SmailModel;
import jp.groupsession.v2.sml.model.SmlAccountModel;
import jp.groupsession.v2.sml.model.SmlAsakModel;
import jp.groupsession.v2.sml.model.SmlBinModel;
import jp.groupsession.v2.sml.model.SmlHinaModel;
import jp.groupsession.v2.sml.model.SmlSmeisModel;
import jp.groupsession.v2.sml.model.SmlWmeisModel;
import jp.groupsession.v2.sml.sml010.Sml010Action;
import jp.groupsession.v2.sml.sml010.Sml010Biz;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.usr.GSConstUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;


/**
 * <br>[機  能] ショートメール作成画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sml020Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sml020Biz.class);

    /** ユーザ選択モード ユーザ名クリック */
    public static final int SELECT_USR_MODE_USRNAME = 1;
    /** ユーザ選択モード 宛先追加クリック */
    public static final int SELECT_USR_MODE_ATESAKI = 2;
    /** ユーザ選択モード CC追加クリック */
    public static final int SELECT_USR_MODE_CC = 3;
    /** ユーザ選択モード BCC追加クリック */
    public static final int SELECT_USR_MODE_BCC = 4;

    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param reqMdl リクエスト情報
     */
    public Sml020Biz(RequestModel reqMdl) {
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
     *
     * <br>[機  能] 自動送信先設定を反映
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void setAutoDest(Sml020ParamModel paramMdl,
            RequestModel reqMdl,
            Connection con)
        throws SQLException {
        ArrayList<String> askList = new ArrayList<String>();
        ArrayList<String> ccList = new ArrayList<String>();
        ArrayList<String> bccList = new ArrayList<String>();
        SmlCommonBiz smlCmnBiz = new SmlCommonBiz(con, reqMdl);
        SmlBanDestDao sbdDao = new SmlBanDestDao(con);
        List<Integer> banUsrList = sbdDao.getBanDestUsrSidList(
                reqMdl__.getSmodel().getUsrsid());
        List<Integer> banAccList = sbdDao.getBanDestAccSidList(
                reqMdl__.getSmodel().getUsrsid());
        List<AtesakiModel> banAtesaki = new ArrayList<AtesakiModel>();

        List<AtesakiModel> atsk = smlCmnBiz.getAutoDestList(con,
                paramMdl.getSmlViewAccount(),
                GSConstSmail.SML_SEND_KBN_ATESAKI);
        for (int i = 0; i < atsk.size(); i++) {
            AtesakiModel aMdl = (AtesakiModel) atsk.get(i);
            if (aMdl.getUsrSid() > 0) {
                if (!banUsrList.contains(aMdl.getUsrSid())) {
                    askList.add(String.valueOf(aMdl.getUsrSid()));
                }
            } else {
                if (!banAccList.contains(aMdl.getAccountSid())) {
                    askList.add(GSConstSmail.SML_ACCOUNT_STR
                                        + String.valueOf(aMdl.getAccountSid()));
                }
            }
        }
        if (!askList.isEmpty()) {
            paramMdl.setSml020userSid(askList.toArray(new String[askList.size()]));
        }

        atsk = smlCmnBiz.getAutoDestList(con,
                paramMdl.getSmlViewAccount(),
                GSConstSmail.SML_SEND_KBN_CC);
        for (int i = 0; i < atsk.size(); i++) {
            AtesakiModel aMdl = (AtesakiModel) atsk.get(i);
            if (aMdl.getUsrSid() > 0) {
                if (!banUsrList.contains(aMdl.getUsrSid())) {
                    ccList.add(String.valueOf(aMdl.getUsrSid()));
                }
            } else {
                if (!banAccList.contains(aMdl.getAccountSid())) {
                    ccList.add(GSConstSmail.SML_ACCOUNT_STR
                                        + String.valueOf(aMdl.getAccountSid()));
                }
            }
        }
        if (!ccList.isEmpty()) {
            paramMdl.setSml020userSidCc(ccList.toArray(new String[ccList.size()]));
        }
        atsk = smlCmnBiz.getAutoDestList(con,
                paramMdl.getSmlViewAccount(),
                GSConstSmail.SML_SEND_KBN_BCC);
        for (int i = 0; i < atsk.size(); i++) {
            AtesakiModel aMdl = (AtesakiModel) atsk.get(i);
            if (aMdl.getUsrSid() > 0) {
                if (!banUsrList.contains(aMdl.getUsrSid())) {
                    bccList.add(String.valueOf(aMdl.getUsrSid()));
                }
            } else {
                if (!banAccList.contains(aMdl.getAccountSid())) {
                    bccList.add(GSConstSmail.SML_ACCOUNT_STR
                                        + String.valueOf(aMdl.getAccountSid()));
                }
            }
        }
        if (!bccList.isEmpty()) {
            paramMdl.setSml020userSidBcc(bccList.toArray(new String[bccList.size()]));
        }
        paramMdl.setSml020AtesakiDeletedMessage(
                __getMessageForDeleteAtesaki(banAtesaki,
                        new GsMessage(reqMdl__)));

    }
    /**
     * <br>[機  能] 宛先名称一覧を設定
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void setAtesaki(Sml020ParamModel paramMdl,
                            RequestModel reqMdl,
                            Connection con)
        throws SQLException {

        log__.debug("宛先名称設定");

        String[] userSid = paramMdl.getSml020userSid();

        if (userSid == null || userSid.length < 1) {
            return;
        }
        SmlUserSearchDao udao = new SmlUserSearchDao(con);
        ArrayList<AtesakiModel> ret = new ArrayList<AtesakiModel>();


            //全返信時以外
            List<String> gsUsers = new ArrayList<String>();
            List<String> smlUsers = new ArrayList<String>();

            for (String usid : userSid) {
                if (usid.indexOf(GSConstSmail.SML_ACCOUNT_STR) != -1) {
                    smlUsers.add(usid.substring(GSConstSmail.SML_ACCOUNT_STR.length()));
                } else {
                    gsUsers.add(usid);
                }
            }

            if (!gsUsers.isEmpty()) {
                ret.addAll(udao.getUserDataFromSidList(
                        (String[]) gsUsers.toArray(new String[gsUsers.size()])));
            }

            if (!smlUsers.isEmpty()) {
                ret.addAll(udao.getAccountDataFromSidList(
                        (String[]) smlUsers.toArray(new String[smlUsers.size()])));
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
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void setAtesakiCc(Sml020ParamModel paramMdl, RequestModel reqMdl, Connection con)
        throws SQLException {

        log__.debug("CC名称設定");

        String[] userSid = paramMdl.getSml020userSidCc();

        if (userSid == null || userSid.length < 1) {
            return;
        }
        SmlUserSearchDao udao = new SmlUserSearchDao(con);
        ArrayList<AtesakiModel> ret = new ArrayList<AtesakiModel>();

            //全返信時以外
            List<String> gsUsers = new ArrayList<String>();
            List<String> smlUsers = new ArrayList<String>();

            for (String usid : userSid) {
                if (usid.indexOf(GSConstSmail.SML_ACCOUNT_STR) != -1) {
                    smlUsers.add(usid.substring(GSConstSmail.SML_ACCOUNT_STR.length()));
                } else {
                    gsUsers.add(usid);
                }
            }

            if (!gsUsers.isEmpty()) {
                ret.addAll(udao.getUserDataFromSidList(
                        (String[]) gsUsers.toArray(new String[gsUsers.size()])));
            }

            if (!smlUsers.isEmpty()) {
                ret.addAll(udao.getAccountDataFromSidList(
                        (String[]) smlUsers.toArray(new String[smlUsers.size()])));
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
    public void setAtesakiBcc(Sml020ParamModel paramMdl, Connection con)
        throws SQLException {

        log__.debug("BCC名称設定");

        String[] userSid = paramMdl.getSml020userSidBcc();

        if (userSid == null || userSid.length < 1) {
            return;
        }
        SmlUserSearchDao udao = new SmlUserSearchDao(con);
        ArrayList<AtesakiModel> ret = new ArrayList<AtesakiModel>();

        List<String> gsUsers = new ArrayList<String>();
        List<String> smlUsers = new ArrayList<String>();

        for (String usid : userSid) {
            if (usid.indexOf(GSConstSmail.SML_ACCOUNT_STR) != -1) {
                smlUsers.add(usid.substring(GSConstSmail.SML_ACCOUNT_STR.length()));
            } else {
                gsUsers.add(usid);
            }
        }

        if (!gsUsers.isEmpty()) {
            ret.addAll(udao.getUserDataFromSidList(
                    (String[]) gsUsers.toArray(new String[gsUsers.size()])));
        }

        if (!smlUsers.isEmpty()) {
            ret.addAll(udao.getAccountDataFromSidList(
                    (String[]) smlUsers.toArray(new String[smlUsers.size()])));
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
    public void setHinagataData(Sml020ParamModel paramMdl,
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
    public void setHinagataList(Sml020ParamModel paramMdl,
                                 RequestModel reqMdl,
                                 Connection con)
        throws SQLException {

        log__.debug("ひな形リスト設定");

        SmlHinaDao hdao = new SmlHinaDao(con);
        List<SmlHinaModel> ret = hdao.getHinaList(paramMdl.getSmlViewAccount());
        List<SmlHinaModel> cmnList = new ArrayList<SmlHinaModel>();
        List<SmlHinaModel> kojinList = new ArrayList<SmlHinaModel>();
        if (ret != null && ret.size() > 0) {
            for (SmlHinaModel model : ret) {
                if (model.getShnCkbn() == GSConstSmail.HINA_KBN_CMN) {
                    cmnList.add(model);
                } else if (model.getShnCkbn() == GSConstSmail.HINA_KBN_PRI) {
                    kojinList.add(model);
                }
            }
        }

        paramMdl.setSml020HinaList(cmnList);
        paramMdl.setSml020HinaListKjn(kojinList);
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
    public void setTempFiles(Sml020ParamModel paramMdl, String tempDir, Connection con)
        throws IOToolsException {

        /** 画面に表示するファイルのリストを作成、セット **********************/
        CommonBiz commonBiz = new CommonBiz();
        log__.debug("添付ファイルのディレクトリ！＝" + tempDir);
        paramMdl.setSml020FileLabelList(commonBiz.getTempFileLabelList(tempDir));
    }

    /**
     * <br>[機  能] メールSIDから引用するメールデータを設定する
     * <br>[解  説]
     * <br>[備  考] 返信、全返信、転送モードの処理
     *
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @param appRootPath アプリケーションルート
     * @param tempDir テンポラリディレクトリパス
     * @param domain ドメイン
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException ファイルアクセス時例外
     * @throws IOException 入出力時例外
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public void setMailData(Sml020ParamModel paramMdl,
                             RequestModel reqMdl,
                             Connection con,
                             String appRootPath,
                             String tempDir,
                             String domain)
        throws SQLException, IOToolsException, IOException, TempFileException {

        log__.debug("引用するメールデータ設定(返信、全返信、転送モード)");

        SmlAccountDao sacDao = new SmlAccountDao(con);
        SmlAccountModel sacMdl = new SmlAccountModel();
        sacMdl = sacDao.select(paramMdl.getSmlViewAccount());

        SmailDao sdao = new SmailDao(con);
        ArrayList<SmailDetailModel> ret =
            sdao.selectSmeisDetailFromSid2(
                    paramMdl.getSmlViewAccount(),
                    paramMdl.getSml010SelectedSid(),
                    paramMdl.getSml020ProcMode() == GSConstSmail.MSG_CREATE_MODE_TENSO);

        if (!ret.isEmpty()) {
            SmailDetailModel sMdl = ret.get(0);

            //件名
            paramMdl.setSml020Title(
                    __convertTitle(
                            paramMdl.getSml020ProcMode(),
                            NullDefault.getString(sMdl.getSmsTitle(), "")));
            //マーク
            paramMdl.setSml020Mark(sMdl.getSmsMark());
            //本文
            paramMdl.setSml020Body(
                    __convertBody(
                            paramMdl.getSml020ProcMode(), ret, reqMdl, sacMdl));

            //メール形式
            paramMdl.setSml020MailType(sMdl.getSmsType());

            if (paramMdl.getSml020ProcMode().equals(GSConstSmail.MSG_CREATE_MODE_TENSO)
                    || paramMdl.getSml020ProcMode().equals(GSConstSmail.MSG_CREATE_MODE_COPY)) {

                //添付ファイル情報
                SmlBinDao sbinDao = new SmlBinDao(con);
                List<SmlBinModel> binList = sbinDao.getBinList(sMdl.getSmlSid());

                //添付ファイルがあるなるならばテンポラリにコピー
                if (!binList.isEmpty()) {
                    __tempFileCopy(binList, appRootPath, tempDir, con, domain);
                }
            }
            //宛先
            __setAtesaki(paramMdl.getSml020ProcMode(), ret, paramMdl, con);
        }
    }

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
    public void setMailDataFromSitagaki(Sml020ParamModel paramMdl,
                                         RequestModel reqMdl,
                                         Connection con,
                                         String appRootPath,
                                         String tempDir,
                                         String domain)
        throws SQLException, IOToolsException, IOException, TempFileException {
        log__.debug("引用するメールデータ設定(草稿から作成モード)");

        //下書きモード時は宛先を生成する
        Sml010Biz sml010biz = new Sml010Biz();
        ArrayList<AtesakiModel> retTo =
            sml010biz.getAtesaki2(paramMdl.getSml010SelectedSid(),
                            GSConstSmail.SML_SEND_KBN_ATESAKI, con);
        ArrayList<AtesakiModel> retCc =
            sml010biz.getAtesaki2(
                    paramMdl.getSml010SelectedSid(), GSConstSmail.SML_SEND_KBN_CC, con);
        ArrayList<AtesakiModel> retBcc =
            sml010biz.getAtesaki2(
                    paramMdl.getSml010SelectedSid(), GSConstSmail.SML_SEND_KBN_BCC, con);

        String[] sml020userSid = null;
        String[] sml020userSidCc = null;
        String[] sml020userSidBcc = null;
        SmlBanDestDao sbdDao = new SmlBanDestDao(con);
        List<Integer> banUsrList = sbdDao.getBanDestUsrSidList(reqMdl.getSmodel().getUsrsid());
        List<Integer> banAccList = sbdDao.getBanDestAccSidList(reqMdl.getSmodel().getUsrsid());
        List<AtesakiModel> banAtesaki = new ArrayList<AtesakiModel>();

        if (retTo.isEmpty()) {
            sml020userSid = new String[0];
        } else {
            List<String> toList = new ArrayList<String>();
            for (int i = 0; i < retTo.size(); i++) {
                AtesakiModel retMdl = (AtesakiModel) retTo.get(i);
                if (retMdl.getUsrSid() > 0) {
                    if (!banUsrList.contains(retMdl.getUsrSid())) {
                        toList.add(String.valueOf(retMdl.getUsrSid()));
                    } else {
                        banAtesaki.add(retMdl);
                    }
                } else {
                    if (!banAccList.contains(retMdl.getAccountSid())) {
                        toList.add("sac" + String.valueOf(retMdl.getAccountSid()));
                    } else {
                        banAtesaki.add(retMdl);
                    }
                }
            }
            sml020userSid = toList.toArray(new String[toList.size()]);
        }

        if (retCc.isEmpty()) {
            sml020userSidCc = new String[0];
        } else {
            List<String> toList = new ArrayList<String>();
            for (int i = 0; i < retCc.size(); i++) {
                AtesakiModel retMdl = (AtesakiModel) retCc.get(i);
                if (retMdl.getUsrSid() > 0) {
                    if (!banUsrList.contains(retMdl.getUsrSid())) {
                        toList.add(String.valueOf(retMdl.getUsrSid()));
                    } else {
                        banAtesaki.add(retMdl);
                    }
                } else {
                    if (!banAccList.contains(retMdl.getAccountSid())) {
                        toList.add("sac" + String.valueOf(retMdl.getAccountSid()));
                    } else {
                        banAtesaki.add(retMdl);
                    }
                }
            }
            sml020userSidCc = toList.toArray(new String[toList.size()]);
        }
        if (retBcc.isEmpty()) {
            sml020userSidBcc = new String[0];
        } else {
            List<String> toList = new ArrayList<String>();
            for (int i = 0; i < retBcc.size(); i++) {
                AtesakiModel retMdl = (AtesakiModel) retBcc.get(i);
                if (retMdl.getUsrSid() > 0) {
                    if (!banUsrList.contains(retMdl.getUsrSid())) {
                        toList.add(String.valueOf(retMdl.getUsrSid()));
                    } else {
                        banAtesaki.add(retMdl);
                    }
                } else {
                    if (!banAccList.contains(retMdl.getAccountSid())) {
                        toList.add("sac" + String.valueOf(retMdl.getAccountSid()));
                    } else {
                        banAtesaki.add(retMdl);
                    }
                }
            }
            sml020userSidBcc = toList.toArray(new String[toList.size()]);
        }
        paramMdl.setSml020userSid(sml020userSid);
        paramMdl.setSml020userSidCc(sml020userSidCc);
        paramMdl.setSml020userSidBcc(sml020userSidBcc);
        paramMdl.setSml020AtesakiDeletedMessage(
                __getMessageForDeleteAtesaki(banAtesaki,
                        new GsMessage(reqMdl__)));

        SmailDao sdao = new SmailDao(con);
        ArrayList<SmailDetailModel> ret =
            sdao.selectWmeisDetail(
                    paramMdl.getSmlViewAccount(),
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

                //添付ファイルをテンポラリディレクトリにコピーする。
                cmnBiz.saveTempFile(dateStr, binMdl, appRootPath, tempDir, i);
                i++;
            }
        }
    }

    /**
     * <br>[機  能] 処理モードに応じ、件名を変換する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param mode 処理モード
     * @param title 件名
     * @return cnvTitle 変換後タイトル
     */
    private String __convertTitle(String mode, String title) {

        String cnvTitle = "";

        GsMessage gsMsg = new GsMessage(reqMdl__);
        String msg = gsMsg.getMessage("wml.215");

        //返信モード
        if (mode.equals(GSConstSmail.MSG_CREATE_MODE_HENSIN)) {
            cnvTitle = "Re" + msg + title;
        //全返信モード
        } else if (mode.equals(GSConstSmail.MSG_CREATE_MODE_ZENHENSIN)) {
            cnvTitle = "Re" + msg + title;
        //転送モード
        } else if (mode.equals(GSConstSmail.MSG_CREATE_MODE_TENSO)) {
            cnvTitle = "Fw" + msg + title;
        //複写して新規作成
        } else if (mode.equals(GSConstSmail.MSG_CREATE_MODE_COPY)) {
            cnvTitle = title;
        }

        return cnvTitle;
    }

    /**
     * <br>[機  能] 処理モードに応じ、本文を変換する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param mode 処理モード
     * @param bean 検索結果
     * @param reqMdl リクエスト情報
     * @param sacMdl アカウントモデル
     * @return cnvBody 変換後本文
     */
    private String __convertBody(String mode, ArrayList<SmailDetailModel> bean,
                                RequestModel reqMdl, SmlAccountModel sacMdl) {

        String newLine = "\r\n";
        int atesakiLimit = 4;

        String cnvBody = "";
        SmailDetailModel sMdl = bean.get(0);

        //複写して新規作成モード
        if (mode.equals(GSConstSmail.MSG_CREATE_MODE_COPY)) {
            return sMdl.getSmsBody();
        }

        GsMessage gsMsg = new GsMessage(reqMdl);
        String henshin = gsMsg.getMessage("sml.98");
        String tensou = gsMsg.getMessage("sml.97");
        String colon = gsMsg.getMessage("wml.215");
        String sender = gsMsg.getMessage("cmn.sender") + colon;
        String sendTo = gsMsg.getMessage("cmn.from") + colon;
        String sendDate = gsMsg.getMessage("sml.154");
        String subject = gsMsg.getMessage("cmn.subject") + colon;
        String mark = gsMsg.getMessage("cmn.mark") + colon;

        /******************** 見出し設定 ********************/
        //返信モード
        if (mode.equals(GSConstSmail.MSG_CREATE_MODE_HENSIN)) {
            cnvBody = henshin;
        //全返信モード
        } else if (mode.equals(GSConstSmail.MSG_CREATE_MODE_ZENHENSIN)) {
            cnvBody = henshin;
        //転送モード
        } else if (mode.equals(GSConstSmail.MSG_CREATE_MODE_TENSO)) {
            cnvBody = tensou;
        }
        cnvBody += newLine;

        /******************** 送信者設定 ********************/
        cnvBody += sender;
        String sousinsya = "";

        if (sMdl.getUsrSid() > 0) {
            sousinsya = sMdl.getUsiSei() + "　" + sMdl.getUsiMei();
        } else {
            sousinsya = sMdl.getAccountName();
        }

        cnvBody += sousinsya;
        cnvBody += newLine;

        /******************** 宛先設定 ********************/
        cnvBody += sendTo;
        ArrayList<AtesakiModel> atsk = sMdl.getAtesakiList();
        int limCount = 0;
        boolean newLineFlg = false;
        for (int i = 0; i < atsk.size(); i++) {
            AtesakiModel aMdl = (AtesakiModel) atsk.get(i);

            if (aMdl.getSmjSendkbn() == GSConstSmail.SML_SEND_KBN_ATESAKI) {
                String atesakiName = "";

                if (aMdl.getUsrSid() > 0) {
                    atesakiName = aMdl.getUsiSei() + "　" + aMdl.getUsiMei();
                } else {
                    atesakiName = aMdl.getAccountName();
                }


                if (newLineFlg) {
                    cnvBody += "　　　　";
                    newLineFlg = false;
                }
                cnvBody += atesakiName;

                if (i < atsk.size() - 1) {
                    cnvBody += "; ";
                }

                limCount += 1;
                if (limCount == atesakiLimit
                    && i != atsk.size() - 1) {
                    limCount = 0;
                    newLineFlg = true;
                    cnvBody += newLine;
                }
            }
        }

        /******************** CC設定 ********************/
        //CCの件数を取得
        int cCcnt = 0;
        for (int i = 0; i < atsk.size(); i++) {
            AtesakiModel aMdl = (AtesakiModel) atsk.get(i);
            if (aMdl.getSmjSendkbn() == GSConstSmail.SML_SEND_KBN_CC) {
                cCcnt++;
            }
        }
        //CCを取得
        if (cCcnt > 0) {
            cnvBody += newLine;
            cnvBody += "CC" + colon;
            limCount = 0;
            newLineFlg = false;
            for (int i = 0; i < atsk.size(); i++) {
                AtesakiModel aMdl = (AtesakiModel) atsk.get(i);

                if (aMdl.getSmjSendkbn() == GSConstSmail.SML_SEND_KBN_CC) {
                    String atesakiName = "";

                    if (aMdl.getUsrSid() > 0) {
                        atesakiName = aMdl.getUsiSei() + "　" + aMdl.getUsiMei();
                    } else {
                        atesakiName = aMdl.getAccountName();
                    }

                    if (newLineFlg) {
                        cnvBody += "　　　　";
                        newLineFlg = false;
                    }
                    cnvBody += atesakiName;

                    if (i < atsk.size() - 1) {
                        cnvBody += "; ";
                    }

                    limCount += 1;
                    if (limCount == atesakiLimit
                        && i != atsk.size() - 1) {
                        limCount = 0;
                        newLineFlg = true;
                        cnvBody += newLine;
                    }
                }
            }
        }

        /******************** BCC設定 ********************/
        limCount = 0;
        newLineFlg = false;
        for (int i = 0; i < atsk.size(); i++) {
            AtesakiModel aMdl = (AtesakiModel) atsk.get(i);
            if (getSessionUserSid(reqMdl) == aMdl.getUsrSid()) {
                if (aMdl.getSmjSendkbn() == GSConstSmail.SML_SEND_KBN_BCC) {
                    String atesakiName = "";

                    if (aMdl.getUsrSid() > 0) {
                        atesakiName = aMdl.getUsiSei() + "　" + aMdl.getUsiMei();
                    } else {
                        atesakiName = aMdl.getAccountName();
                    }

                    if (newLineFlg) {
                        cnvBody += "　　　　";
                        newLineFlg = false;
                    }
                    cnvBody += newLine;
                    cnvBody += "BCC" + colon;
                    cnvBody += atesakiName;

                    if (i < atsk.size() - 1) {
                        cnvBody += "; ";
                    }

                    limCount += 1;
                    if (limCount == atesakiLimit
                        && i != atsk.size() - 1) {
                        limCount = 0;
                        newLineFlg = true;
                        cnvBody += newLine;
                    }
                }
            }
        }

        cnvBody += newLine;
        /******************** 送信日設定 ********************/
        cnvBody += sendDate;
        UDate sDate = sMdl.getSmsSdate();
        if (sDate != null) {
            String strSdate =
                UDateUtil.getSlashYYMD(sDate)
                + " "
                + UDateUtil.getSeparateHMS(sDate);
            cnvBody += strSdate;
        }
        cnvBody += newLine;

        /******************** 件名設定 ********************/
        cnvBody += " " + subject;
        cnvBody += sMdl.getSmsTitle();
        cnvBody += newLine;

        /******************** マーク設定 ********************/
        cnvBody += " " + mark;
        SmlCommonBiz schCmnBiz = new SmlCommonBiz(reqMdl);
        String markStr = schCmnBiz.convertMark2(sMdl.getSmsMark());
        cnvBody += markStr;
        cnvBody += newLine;
        cnvBody += newLine;

        /******************** 本文設定 ********************/


        String bodyStr = NullDefault.getString(sMdl.getSmsBody(), "");
        if (sMdl.getSmsType() != GSConstSmail.SAC_SEND_MAILTYPE_NORMAL) {
            bodyStr = StringUtilHtml.transToText(
                    StringUtilHtml.delHTMLTag(StringUtilHtml.transBRtoCRLF(bodyStr)));

            //HTML特殊文字を変換
            bodyStr = StringUtilHtml.replaceString(bodyStr, "&copy;", "©");
            bodyStr = StringUtilHtml.replaceString(bodyStr, "&yen;", "¥");
            bodyStr = StringUtilHtml.replaceString(bodyStr, "&rdquo;", "”");
        }

        String[] splStr = bodyStr.split(newLine);
        if (splStr != null && splStr.length > 0) {

            String quotes = ">";

            if (sacMdl != null) {
                if (sacMdl.getSacQuotes() != GSConstSmail.SAC_QUOTES_NONE) {
                    quotes = SmlCommonBiz.getViewMailQuotes(sacMdl.getSacQuotes(), reqMdl);
                } else {
                    quotes = "";
                }
            }

            for (int j = 0; j < splStr.length; j++) {
                cnvBody += quotes;
                cnvBody += splStr[j];
                cnvBody += newLine;
            }
        }
        return cnvBody;
    }

    /**
     * <br>[機  能] 作成されたメールデータを下書きとして登録する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @param ctrl 採番用コネクション
     * @param appRootPath アプリケーションルート
     * @param tempDir テンポラリディレクトリ
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException ファイルアクセス時例外
     * @throws IOException 入出力時例外
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public void insertSitagakiData(Sml020ParamModel paramMdl,
                                    RequestModel reqMdl,
                                    Connection con,
                                    MlCountMtController ctrl,
                                    String appRootPath,
                                    String tempDir)
        throws SQLException, IOToolsException, IOException, TempFileException {

        log__.debug("DBに下書き登録");

        int mailSid = -1;
        int usrSid = getSessionUserSid(reqMdl);
        UDate now = new UDate();

        //添付ファイルを登録
        CommonBiz biz = new CommonBiz();
        List<String> binList =
            biz.insertBinInfo(con, tempDir, appRootPath, ctrl, usrSid, now);

        //下書きから作成 → 再度下書き保存 の場合(つまり下書きデータの更新)
        if (paramMdl.getSml020ProcMode().equals(GSConstSmail.MSG_CREATE_MODE_SOKO)) {

//            mailSid = paramMdl.getSml010SelectedSid();
            mailSid = paramMdl.getSml010EditSid();

            //下書きを削除
            SmlWmeisModel wparam = new SmlWmeisModel();
            wparam.setSmwSid(mailSid);
            SmlWmeisDao wdao = new SmlWmeisDao(con);
            wdao.delete(wparam);

            //下書き宛先を削除
            SmlAsakModel aparam = new SmlAsakModel();
            aparam.setSmsSid(mailSid);
            SmlAsakDao adao = new SmlAsakDao(con);
            adao.deleteFromMailSid(aparam);

            //ショートメールに送付されているバイナリSID一覧取得
            SmlBinDao sbinDao = new SmlBinDao(con);
            String[] mailSidList = new String[1];
            mailSidList[0] = String.valueOf(mailSid);
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
            sbinDao.deleteSmlBin(mailSid);
        }

        //SID採番
        mailSid =
            (int) ctrl.getSaibanNumber(
                    GSConstSmail.SAIBAN_SML_SID,
                    GSConstSmail.SAIBAN_SUB_MAIL_SID,
                    usrSid);

        //メールサイズ取得
        Long titile_byte = new Long(0);
        Long body_byte = new Long(0);
        Long file_byte = new Long(0);

        try {
            if (NullDefault.getString(
                    paramMdl.getSml020Title(), "").getBytes("UTF-8").length != 0) {
                titile_byte = Long.valueOf(
                        NullDefault.getString(
                                paramMdl.getSml020Title(), "").getBytes("UTF-8").length);
            }
        } catch (UnsupportedEncodingException e) {
            log__.error("文字のバイト数取得に失敗");
            titile_byte = Long.valueOf(
                    NullDefault.getString(
                            paramMdl.getSml020Title(), "").getBytes().length);
        }

        try {
            if (NullDefault.getString(
                    paramMdl.getSml020Body(), "").getBytes("UTF-8").length != 0) {
                body_byte = Long.valueOf(
                        NullDefault.getString(
                                paramMdl.getSml020Body(), "").getBytes("UTF-8").length);
            }
        } catch (UnsupportedEncodingException e) {
            log__.error("文字のバイト数取得に失敗");
            body_byte = Long.valueOf(
                    NullDefault.getString(
                            paramMdl.getSml020Body(), "").getBytes().length);
        }

        file_byte = biz.getTempFileSize(tempDir);


        //下書きテーブルにデータ作成
        SmlWmeisModel wparam = new SmlWmeisModel();
        wparam.setSacSid(paramMdl.getSmlViewAccount());
        wparam.setSmwSid(mailSid);
        wparam.setSmwTitle(NullDefault.getString(paramMdl.getSml020Title(), ""));
        wparam.setSmwMark(paramMdl.getSml020Mark());
        if (paramMdl.getSml020MailType() == GSConstSmail.SAC_SEND_MAILTYPE_NORMAL) {
            wparam.setSmwBody(NullDefault.getString(paramMdl.getSml020Body(), ""));
            wparam.setSmwBodyPlain("");
        } else {
            wparam.setSmwBody(NullDefault.getString(paramMdl.getSml020BodyHtml(), ""));
            wparam.setSmwBodyPlain(StringUtilHtml.deleteHtmlTag(
                    NullDefault.getString(paramMdl.getSml020Body(), "")));
        }
        wparam.setSmwType(paramMdl.getSml020MailType());
        wparam.setSmwSize(titile_byte + body_byte + file_byte);
        wparam.setSmwJkbn(GSConst.JTKBN_TOROKU);
        wparam.setSmwAuid(usrSid);
        wparam.setSmwAdate(now);
        wparam.setSmwEuid(usrSid);
        wparam.setSmwEdate(now);
        SmlWmeisDao wdao = new SmlWmeisDao(con);
        wdao.insert(wparam);

        /** 返信・全返信時には受信メールにわかるようにフィールドデータ変更  **/
        if (paramMdl.getSml020ProcMode().equals(GSConstSmail.MSG_CREATE_MODE_HENSIN)
            || paramMdl.getSml020ProcMode().equals(GSConstSmail.MSG_CREATE_MODE_ZENHENSIN)) {

            SmlJmeisDao jdao = new SmlJmeisDao(con);
            int kbn = GSConstSmail.SML_REPLY;
            int jMailSid = paramMdl.getSml010SelectedSid();
            jdao.updateHenshin(kbn, paramMdl.getSmlViewAccount(), jMailSid);
        }

        /** 転送時には受信メールにわかるようにフィールドデータ変更  **/
        if (paramMdl.getSml020ProcMode().equals(GSConstSmail.MSG_CREATE_MODE_TENSO)) {

            SmlJmeisDao jdao = new SmlJmeisDao(con);
            int kbn = GSConstSmail.SML_FW;
            int jMailSid = paramMdl.getSml010SelectedSid();
            jdao.updateFw(kbn, paramMdl.getSmlViewAccount(), jMailSid);
        }

        //ショートメール送付情報を登録
        SmlBinDao sbinDao = new SmlBinDao(con);
        SmlSmeisModel sparam = new SmlSmeisModel();
        sparam.setSmsSid(mailSid);
        sbinDao.insertSmlBin(sparam, binList);

        SmlCommonBiz smlBiz = new SmlCommonBiz();

        //宛先テーブルにデータ作成
        String[] userSids = paramMdl.getSml020userSid();
        //ユーザSIDからアカウントのSIDを取得
        String[] accountSids = null;
        accountSids = smlBiz.getAccountSidFromUsr(con, userSids);

        SmlAsakDao adao = new SmlAsakDao(con);
        SmlAsakModel aparam = null;
        if (accountSids != null && accountSids.length > 0) {
            for (int i = 0; i < accountSids.length; i++) {
                aparam = new SmlAsakModel();
                aparam.setSacSid(Integer.parseInt(accountSids[i]));
                aparam.setSmsSid(mailSid);
                aparam.setSmjSendkbn(GSConstSmail.SML_SEND_KBN_ATESAKI);
                aparam.setSmsAuid(usrSid);
                aparam.setSmsAdate(now);
                aparam.setSmsEuid(usrSid);
                aparam.setSmsEdate(now);
                adao.insert(aparam);
            }
        }

        //CC
        String[] userSidsCc = paramMdl.getSml020userSidCc();
        //ユーザSIDからアカウントのSIDを取得
        String[] accountSidsCc = null;
        accountSidsCc = smlBiz.getAccountSidFromUsr(con, userSidsCc);

        if (accountSidsCc != null && accountSidsCc.length > 0) {
            for (int i = 0; i < accountSidsCc.length; i++) {
                aparam = new SmlAsakModel();
                aparam.setSacSid(Integer.parseInt(accountSidsCc[i]));
                aparam.setSmsSid(mailSid);
                aparam.setSmjSendkbn(GSConstSmail.SML_SEND_KBN_CC);
                aparam.setSmsAuid(usrSid);
                aparam.setSmsAdate(now);
                aparam.setSmsEuid(usrSid);
                aparam.setSmsEdate(now);
                adao.insert(aparam);
            }
        }

        //BCC
        String[] userSidBcc = paramMdl.getSml020userSidBcc();
        //ユーザSIDからアカウントのSIDを取得
        String[] accountSidsBcc = null;
        accountSidsBcc = smlBiz.getAccountSidFromUsr(con, userSidBcc);

        if (accountSidsBcc != null && accountSidsBcc.length > 0) {
            for (int i = 0; i < accountSidsBcc.length; i++) {
                aparam = new SmlAsakModel();
                aparam.setSacSid(Integer.parseInt(accountSidsBcc[i]));
                aparam.setSmsSid(mailSid);
                aparam.setSmjSendkbn(GSConstSmail.SML_SEND_KBN_BCC);
                aparam.setSmsAuid(usrSid);
                aparam.setSmsAdate(now);
                aparam.setSmsEuid(usrSid);
                aparam.setSmsEdate(now);
                adao.insert(aparam);
            }
        }

        //ディスク容量を更新
        SmlCommonBiz smlCmnBiz = new SmlCommonBiz();
        smlCmnBiz.updateAccountDiskSize(con, paramMdl.getSmlViewAccount());


        //テンポラリディレクトリのファイルを削除する
        IOTools.deleteDir(tempDir);
        log__.debug("テンポラリディレクトリのファイル削除");
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
    public String getDelMsgTitle(Sml020ParamModel paramMdl, Connection con)
        throws SQLException {

        String retTitle = "";

        SmlSmeisDao sdao = new SmlSmeisDao(con);
        SmlSmeisModel param = new SmlSmeisModel();
        param.setSmsSid(paramMdl.getSml010SelectedSid());
        SmlSmeisModel sret = sdao.select(param);
        if (sret != null) {
            retTitle = sret.getSmsTitle();
        }

        return retTitle;
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
    public void deleteMessage(Sml020ParamModel paramMdl, RequestModel reqMdl, Connection con)
        throws SQLException {

        int selectedSid = paramMdl.getSml010SelectedSid();
        int sessionUsrSid = getSessionUserSid(reqMdl);

        log__.debug("草稿メッセージ削除(ゴミ箱へ移動)");
        SmlWmeisDao wdao = new SmlWmeisDao(con);
        wdao.moveWmeis(
                sessionUsrSid,
                paramMdl.getSmlViewAccount(),
                GSConstSmail.SML_JTKBN_GOMIBAKO,
                new UDate(),
                selectedSid);
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
     * <br>[機  能] 削除・復旧の対象メールタイトル取得
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @return ret 削除対象メッセージリスト
     * @throws SQLException SQL実行時例外
     */
    public String getMailTitle(Sml020ParamModel paramMdl, Connection con)
        throws SQLException {

        String smwTitle = "";

        SmlWmeisModel bean = new SmlWmeisModel();
        bean.setSmwSid(paramMdl.getSml010SelectedSid());

        SmlWmeisDao wDao = new SmlWmeisDao(con);
        SmlWmeisModel wmlModel = wDao.select(bean);

        if (wmlModel != null) {
            smwTitle = wmlModel.getSmwTitle();
        }
        return smwTitle;
    }

    /**
     * <br>[機  能] ユーザ一覧を設定する。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @throws SQLException SQL実行例外
     */
    public void setLeftMenu(
            Sml020ParamModel paramMdl, Connection con, RequestModel reqMdl) throws SQLException {

        String groupSid = paramMdl.getSml010groupSid();
        int sessionUsrSid = getSessionUserSid(reqMdl);

        if (StringUtil.isNullZeroString(groupSid)) {
            GroupBiz grpBiz = new GroupBiz();
            groupSid = String.valueOf(grpBiz.getDefaultGroupSid(sessionUsrSid, con));
            paramMdl.setSml010groupSid(groupSid);
        }

        //グループ一覧を取得する
        GroupBiz gpBiz = new GroupBiz();

        //ユーザSIDからマイグループ情報を取得する
        CmnMyGroupDao cmgDao = new CmnMyGroupDao(con);
        List<CmnMyGroupModel> cmgList = cmgDao.getMyGroupList(sessionUsrSid);

        //マイグループリストをセット
        List<CmnLabelValueModel> dspGrpList = new ArrayList<CmnLabelValueModel>();
        for (CmnMyGroupModel cmgMdl : cmgList) {
            dspGrpList.add(
                    new CmnLabelValueModel(
                            cmgMdl.getMgpName(), "M" + String.valueOf(cmgMdl.getMgpSid()), "1"));
        }

        GsMessage gsMsg = new GsMessage(reqMdl);

        List<LabelValueBean> grpLabelList = gpBiz.getGroupCombLabelList(con, true, gsMsg);
        for (LabelValueBean bean : grpLabelList) {
            dspGrpList.add(
                    new CmnLabelValueModel(bean.getLabel(), bean.getValue(), "0"));

        }
        paramMdl.setSml020groupList(dspGrpList);


        //グループSIDから所属ユーザ一覧を作成
        int grpSid = getDspGroupSid(groupSid);
        ArrayList<Integer> users = new ArrayList<Integer>();

        if (isMyGroupSid(groupSid)) {
            //マイグループから作成
            CmnMyGroupMsDao mgmsDao = new CmnMyGroupMsDao(con);
            users = mgmsDao.selectMyGroupUsers(sessionUsrSid, grpSid);

        } else {
            //通常グループから作成
            CmnBelongmDao cmnbDao = new CmnBelongmDao(con);
            users = cmnbDao.selectBelongUserSid(getDspGroupSid(groupSid));
        }

        //ショートメールプラグインを使用していないユーザを除外する。
        CommonBiz cmnBiz = new CommonBiz();
        ArrayList<Integer> usrList = (ArrayList<Integer>) cmnBiz.getCanUseSmailUser(con, users);

        //システムメールとGS管理者を除外する
        ArrayList<Integer> usrDspList = new ArrayList<Integer>();
        for (Integer usid : usrList) {
            if (usid != GSConstUser.SID_ADMIN && usid != GSConstUser.SID_SYSTEM_MAIL) {
                usrDspList.add(usid);
            }

        }

        //ユーザ情報を取得
        List<CmnUsrmInfModel> uList = null;
        if (users != null && users.size() > 0) {
            //ユーザ情報一覧を作成
            UserBiz usrBiz = new UserBiz();
            uList = usrBiz.getUserList(con, usrDspList);

        }

        paramMdl.setSml020userList(uList);
    }

    /**
     * パラメータ.グループコンボ値からグループSID又はマイグループSIDを取得する
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param gpSid グループSID
     * @return int グループSID又はマイグループSID
     */
    public static int getDspGroupSid(String gpSid) {
        int ret = 0;
        if (gpSid == null) {
            return ret;
        }

        if (isMyGroupSid(gpSid)) {
            return Integer.parseInt(gpSid.substring(1));
        } else {
            return Integer.parseInt(gpSid);
        }
    }

    /**
     * パラメータ.グループコンボ値がグループSIDかマイグループSIDかを判定する
     * <br>[機  能]先頭文字に"M"が有る場合、マイグループSID
     * <br>[解  説]
     * <br>[備  考]
     * @param gpSid グループSID
     * @return boolean true:マイグループ false=通常のグループ
     */
    public static boolean isMyGroupSid(String gpSid) {
        boolean ret = false;
        if (gpSid == null) {
            return ret;
        }
        // 置換対象文字列が存在する場所を取得
        int index = gpSid.indexOf("M");

        // 先頭文字に"M"が有る場合はマイグループ
        if (index == 0) {
            return true;
        } else {
            return ret;
        }
    }

    /**
     * <br>[機  能] ユーザ一覧を設定する。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param mode モード
     * @throws SQLException SQL実行例外
     */
    public void addUserAtesaki(Sml020ParamModel paramMdl, int mode)
    throws SQLException {

        ArrayList<String> addList = new ArrayList<String>();

        if (mode == Sml010Action.SELECT_USR_MODE_USRNAME) {
            addList.add(String.valueOf(paramMdl.getSml010usrSid()));

        } else {
            String[] addUser = paramMdl.getSml010usrSids();
            if (addUser != null && addUser.length > 0) {
                for (int i = 0; i < addUser.length; i++) {
                    addList.add(addUser[i]);
                }
            }
        }


        ArrayList<String> selectList = new ArrayList<String>();

        if (mode == Sml010Action.SELECT_USR_MODE_USRNAME) {
            String[] sltUser = paramMdl.getSml020userSid();
            if (sltUser != null && sltUser.length > 0) {
                for (int i = 0; i < sltUser.length; i++) {
                    selectList.add(sltUser[i]);
                }
            }

        } else if (mode == Sml010Action.SELECT_USR_MODE_ATESAKI) {
            String[] sltUser = paramMdl.getSml020userSid();
            if (sltUser != null && sltUser.length > 0) {
                for (int i = 0; i < sltUser.length; i++) {
                    selectList.add(sltUser[i]);
                }
            }

        } else if (mode == Sml010Action.SELECT_USR_MODE_CC) {
            String[] sltUser = paramMdl.getSml020userSidCc();
            if (sltUser != null && sltUser.length > 0) {
                for (int i = 0; i < sltUser.length; i++) {
                    selectList.add(sltUser[i]);
                }
            }

        } else if (mode == Sml010Action.SELECT_USR_MODE_BCC) {
            String[] sltUser = paramMdl.getSml020userSidBcc();
            if (sltUser != null && sltUser.length > 0) {
                for (int i = 0; i < sltUser.length; i++) {
                    selectList.add(sltUser[i]);
                }
            }

        }

        if (addList.size() > 0) {
            for (String usrSid : addList) {
                if (!selectList.contains(usrSid)) {
                    selectList.add(usrSid);
                }
            }
        }
        if (selectList.size() == 0) {
            return;
        }
        String[] retSid = new String[selectList.size()];
        for (int n = 0; n < selectList.size(); n++) {
            retSid[n] = selectList.get(n);
        }

        if (mode == Sml010Action.SELECT_USR_MODE_USRNAME
                || mode == Sml010Action.SELECT_USR_MODE_ATESAKI) {
            paramMdl.setSml020userSid(retSid);

        } else if (mode == Sml010Action.SELECT_USR_MODE_CC) {
            paramMdl.setSml020userSidCc(retSid);

        } else if (mode == Sml010Action.SELECT_USR_MODE_BCC) {
            paramMdl.setSml020userSidBcc(retSid);

        }
    }

    /**
     * <br>[機  能] ユーザ一覧を設定する。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @throws SQLException SQL実行例外
     */
    public void deleteUserAtesaki(Sml020ParamModel paramMdl)
    throws SQLException {

        int sendKbn = paramMdl.getSml020DelSendKbn();
        String[] userList = null;
        if (sendKbn == GSConstSmail.SML_SEND_KBN_ATESAKI) {
            userList = paramMdl.getSml020userSid();
        } else if (sendKbn == GSConstSmail.SML_SEND_KBN_CC) {
            userList = paramMdl.getSml020userSidCc();
        } else if (sendKbn == GSConstSmail.SML_SEND_KBN_BCC) {
            userList = paramMdl.getSml020userSidBcc();
        }

        String[] delList = new String[1];
        delList[0] = String.valueOf(paramMdl.getSml020DelUsrSid());

        CommonBiz biz = new CommonBiz();
        String[] retList = biz.getDeleteMember(delList, userList);

        if (sendKbn == GSConstSmail.SML_SEND_KBN_ATESAKI) {
            paramMdl.setSml020userSid(retList);
        } else if (sendKbn == GSConstSmail.SML_SEND_KBN_CC) {
            paramMdl.setSml020userSidCc(retList);
        } else if (sendKbn == GSConstSmail.SML_SEND_KBN_BCC) {
            paramMdl.setSml020userSidBcc(retList);
        }
    }

    /**
     * <br>[機  能] 宛先を設定
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param mode 処理モード
     * @param bean 検索結果
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    private void __setAtesaki(String mode, ArrayList<SmailDetailModel> bean,
                            Sml020ParamModel paramMdl, Connection con) throws SQLException {

        SmailDetailModel sMdl = bean.get(0);
        ArrayList<AtesakiModel> atsk = sMdl.getAtesakiList();
        ArrayList<String> askList = new ArrayList<String>();
        ArrayList<String> ccList = new ArrayList<String>();
        ArrayList<String> bccList = new ArrayList<String>();
        SmlBanDestDao sbdDao = new SmlBanDestDao(con);
        List<Integer> banUsrList = sbdDao.getBanDestUsrSidList(
                reqMdl__.getSmodel().getUsrsid());
        List<Integer> banAccList = sbdDao.getBanDestAccSidList(
                reqMdl__.getSmodel().getUsrsid());
        List<AtesakiModel> banAtesaki = new ArrayList<AtesakiModel>();


        //返信モード
        if (mode.equals(GSConstSmail.MSG_CREATE_MODE_HENSIN)) {

            if (sMdl.getUsrSid() > 0) {
                if (!banUsrList.contains(sMdl.getUsrSid())) {
                    askList.add(String.valueOf(sMdl.getUsrSid()));
                } else {
                    SmlUserSearchDao udao = new SmlUserSearchDao(con);
                    List<AtesakiModel> atList
                    = udao.getUserDataFromSidList(
                            new String[] { String.valueOf(sMdl.getUsrSid()) });
                    if (atList.size() > 0) {
                        banAtesaki.add(atList.get(0));
                    }
                }
            } else {
                if (!banAccList.contains(sMdl.getAccountSid())) {
                    askList.add(GSConstSmail.SML_ACCOUNT_STR
                                    + String.valueOf(sMdl.getAccountSid()));
                } else {
                    SmlUserSearchDao udao = new SmlUserSearchDao(con);
                    List<AtesakiModel> atList
                    = udao.getAccountDataFromSidList(
                            new String[] { String.valueOf(sMdl.getAccountSid()) });
                    if (atList.size() > 0) {
                        banAtesaki.add(atList.get(0));
                    }
                }
            }
        //全返信モード
        } else if (mode.equals(GSConstSmail.MSG_CREATE_MODE_ZENHENSIN)) {
            //自身は返信対象に加えない
            SmlAccountDao sacDao = new SmlAccountDao(con);
            SmlAccountModel sacMdl = sacDao.select(paramMdl.getSmlViewAccount());
            String mySid = null;
            if (sacMdl != null) {
                mySid = String.valueOf(sacMdl.getUsrSid());
            }


            if (sMdl.getUsrSid() > 0 && !String.valueOf(sMdl.getUsrSid()).equals(mySid)) {
                if (!banUsrList.contains(sMdl.getUsrSid())) {
                    askList.add(String.valueOf(sMdl.getUsrSid()));
                } else {
                    SmlUserSearchDao udao = new SmlUserSearchDao(con);
                    List<AtesakiModel> atList
                    = udao.getUserDataFromSidList(
                            new String[] { String.valueOf(sMdl.getUsrSid()) });
                    if (atList.size() > 0) {
                        banAtesaki.add(atList.get(0));
                    }
                }
            } else if (sMdl.getAccountSid() != paramMdl.getSmlViewAccount()) {
                if (!banAccList.contains(sMdl.getAccountSid())) {
                    askList.add(GSConstSmail.SML_ACCOUNT_STR
                                    + String.valueOf(sMdl.getAccountSid()));
                } else {
                    SmlUserSearchDao udao = new SmlUserSearchDao(con);
                    List<AtesakiModel> atList
                    = udao.getAccountDataFromSidList(
                            new String[] { String.valueOf(sMdl.getAccountSid()) });
                    if (atList.size() > 0) {
                        banAtesaki.add(atList.get(0));
                    }
                }
            }
            //宛先
            for (int i = 0; i < atsk.size(); i++) {
                AtesakiModel aMdl = (AtesakiModel) atsk.get(i);
                if (aMdl.getSmjSendkbn() == GSConstSmail.SML_SEND_KBN_ATESAKI) {
                    if (aMdl.getUsrSid() > 0 && !String.valueOf(aMdl.getUsrSid()).equals(mySid)) {
                        if (!banUsrList.contains(aMdl.getUsrSid())) {
                            askList.add(String.valueOf(aMdl.getUsrSid()));
                        } else {
                            banAtesaki.add(aMdl);
                        }
                    } else if (aMdl.getAccountSid() != paramMdl.getSmlViewAccount()) {
                        if (!banAccList.contains(aMdl.getAccountSid())) {
                            askList.add(GSConstSmail.SML_ACCOUNT_STR
                                                + String.valueOf(aMdl.getAccountSid()));
                        } else {
                            banAtesaki.add(aMdl);
                        }
                    }
                }
            }

            //CC
            for (int i = 0; i < atsk.size(); i++) {
                AtesakiModel aMdl = (AtesakiModel) atsk.get(i);
                if (aMdl.getSmjSendkbn() == GSConstSmail.SML_SEND_KBN_CC) {
                    if (aMdl.getUsrSid() > 0 && !String.valueOf(aMdl.getUsrSid()).equals(mySid)) {
                        if (!banUsrList.contains(aMdl.getUsrSid())) {
                            ccList.add(String.valueOf(aMdl.getUsrSid()));
                        } else {
                            banAtesaki.add(aMdl);
                        }
                    } else if (aMdl.getAccountSid() != paramMdl.getSmlViewAccount()) {
                        if (!banAccList.contains(aMdl.getAccountSid())) {
                            ccList.add(GSConstSmail.SML_ACCOUNT_STR
                                    + String.valueOf(aMdl.getAccountSid()));
                        } else {
                            banAtesaki.add(aMdl);
                        }
                    }
                }
            }
        }


        //自動送信先を反映
        SmlCommonBiz smlCommonBiz = new SmlCommonBiz(con, reqMdl__);

        if (mode.equals(GSConstSmail.MSG_CREATE_MODE_HENSIN)
                || mode.equals(GSConstSmail.MSG_CREATE_MODE_ZENHENSIN)) {
            //自動CC
            List<AtesakiModel> ccMdlList = smlCommonBiz.getAutoDestList(con,
                            paramMdl.getSmlViewAccount(),
                            GSConstSmail.SML_SEND_KBN_CC);
            for (int i = 0; i < ccMdlList.size(); i++) {
                AtesakiModel aMdl = (AtesakiModel) ccMdlList.get(i);
                if (aMdl.getUsrSid() > 0) {
                    if (!banUsrList.contains(aMdl.getUsrSid())) {
                        ccList.add(String.valueOf(aMdl.getUsrSid()));
                    }
                } else {
                    if (!banAccList.contains(aMdl.getAccountSid())) {
                        ccList.add(GSConstSmail.SML_ACCOUNT_STR
                                + String.valueOf(aMdl.getAccountSid()));
                    }
                }
            }
            //自動BCC
            List<AtesakiModel> bccMdlList = smlCommonBiz.getAutoDestList(con,
                    paramMdl.getSmlViewAccount(),
                    GSConstSmail.SML_SEND_KBN_BCC);
            for (int i = 0; i < bccMdlList.size(); i++) {
                AtesakiModel aMdl = (AtesakiModel) bccMdlList.get(i);
                if (aMdl.getUsrSid() > 0) {
                    if (!banUsrList.contains(aMdl.getUsrSid())) {
                        bccList.add(String.valueOf(aMdl.getUsrSid()));
                    }
                } else {
                    if (!banAccList.contains(aMdl.getAccountSid())) {
                        bccList.add(GSConstSmail.SML_ACCOUNT_STR
                                + String.valueOf(aMdl.getAccountSid()));
                    }
                }
            }
        }
//        if (!askList.isEmpty()) {
            paramMdl.setSml020userSid(askList.toArray(new String[askList.size()]));
//        }
//        if (!ccList.isEmpty()) {
            paramMdl.setSml020userSidCc(ccList.toArray(new String[ccList.size()]));
//        }
//        if (!bccList.isEmpty()) {
            paramMdl.setSml020userSidBcc(bccList.toArray(new String[bccList.size()]));
//        }

        paramMdl.setSml020AtesakiDeletedMessage(
                __getMessageForDeleteAtesaki(banAtesaki,
                        new GsMessage(reqMdl__)));

    }

    /**
     * <br>[機  能] WEBメール メール情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param appRootPath アプリケーションルートパス
     * @param tempDir テンポラリディレクトリパス
     * @throws Exception 実行例外
     */
    public void setWebmailData(
        Sml020ParamModel paramMdl,
        Connection con,
        String appRootPath,
        String tempDir) throws Exception {

        long mailNum = paramMdl.getSml020webmailId();
        WmlDao wmlDao = new WmlDao(con);
        WmlMailDataModel mailData = wmlDao.getMailData(mailNum, reqMdl__.getDomain());
        paramMdl.setSml020Title(mailData.getSubject());
        paramMdl.setSml020Body(mailData.getBody());

        setAutoDest(paramMdl, reqMdl__, con);

        if (mailData.getTempFileList() != null) {
            UDate now = new UDate();
            String dateStr = now.getDateString();
            CommonBiz cmnBiz = new CommonBiz();
            int fileNum = 1;
            for (WmlTempfileModel fileMdl : mailData.getTempFileList()) {
                cmnBiz.saveTempFileForWebmail(dateStr, fileMdl, appRootPath, tempDir, fileNum);
                fileNum++;
            }
            CommonBiz commonBiz = new CommonBiz();
            log__.debug("添付ファイルのディレクトリ！＝" + tempDir);
            paramMdl.setSml020FileLabelList(commonBiz.getTempFileLabelList(tempDir));
        }
    }
    /**
     *
     * <br>[機  能] 送信先が制限されている場合の削除メッセージを作成
     * <br>[解  説]
     * <br>[備  考]
     * @param atkList 制限された宛先一覧
     * @param gsMsg メッセージクラス
     * @return エラーメッセージ文言
     */
    private String __getMessageForDeleteAtesaki(List<AtesakiModel> atkList, GsMessage gsMsg) {
        if (atkList == null || atkList.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(gsMsg.getMessage("sml.sml020.08"));
        List<Integer> useSidList = new ArrayList<Integer>();
        for (AtesakiModel atk : atkList) {
            if (useSidList.contains(atk.getAccountSid())) {
                continue;
            }

            sb.append("<br />・");
            if (atk.getUsrSid() > 0) {
                sb.append(atk.getUsiSei());
                sb.append(" ");
                sb.append(atk.getUsiMei());
            } else {
                sb.append(atk.getAccountName());
            }
            useSidList.add(atk.getAccountSid());
        }
        return sb.toString();
    }
}