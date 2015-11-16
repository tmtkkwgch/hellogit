package jp.groupsession.v2.rng.rng090kn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.base.CmnBinfDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.rng.biz.RngBiz;
import jp.groupsession.v2.rng.dao.RngTemplateBinDao;
import jp.groupsession.v2.rng.dao.RngTemplateCategoryDao;
import jp.groupsession.v2.rng.dao.RngTemplateDao;
import jp.groupsession.v2.rng.dao.RngTemplateUserDao;
import jp.groupsession.v2.rng.model.RngTemplateBinModel;
import jp.groupsession.v2.rng.model.RngTemplateModel;
import jp.groupsession.v2.rng.model.RngTemplateUserModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 稟議 テンプレート登録確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng090knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rng090knBiz.class);
    /** Connection */
    private Connection con__ = null;

    /**
     * @param con Connection
     */
    Rng090knBiz(Connection con) {
        con__ = con;
    }

    /**
     * <br>[機  能] 初期表示処理を行います
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param tempDir 添付ファイルディレクトリ
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @throws Exception 実行時例外
     */
    public void initDsp(
            Rng090knParamModel paramMdl,
            String tempDir,
            Connection con,
            RequestModel reqMdl) throws Exception {

        //内容の改行を反映し、設定
        paramMdl.setRng090knViewContent(StringUtilHtml.transToHTml(
                        NullDefault.getString(paramMdl.getRng090content(), "")));

        //添付ファイル一覧を設定
        CommonBiz cmnBiz = new CommonBiz();
        paramMdl.setRng090FileLabelList(cmnBiz.getTempFileLabelList(tempDir));

        String[] apprUserList = paramMdl.getRng090apprUser();
        String[] confirmUserList = paramMdl.getRng090confirmUser();
        //経路のユーザ情報を取得する。
        UserBiz userBiz = new UserBiz();
        List<CmnUsrmInfModel> apprList = userBiz.getUserList(con, apprUserList);
        List<CmnUsrmInfModel> confirmList = userBiz.getUserList(con, confirmUserList);

        GsMessage gsMsg = new GsMessage(reqMdl);
        String msg = gsMsg.getMessage("cmn.category.no");

        //カテゴリ名を設定
        RngTemplateCategoryDao dao = new RngTemplateCategoryDao(con);
        paramMdl.setRng090knCatName(NullDefault.getString(
                dao.select(paramMdl.getRng090CatSid()).getRtcName(), msg));

        RngBiz rngBiz = new RngBiz(con);
        paramMdl.setRng090knApprUserList(rngBiz.sortUserList(apprList, apprUserList));
        paramMdl.setRng090knConfirmUserList(rngBiz.sortUserList(confirmList, confirmUserList));
    }

    /**
     * <br>[機  能] 稟議テンプレートの登録処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param controller MlCountMtController
     * @param userSid ログインユーザSID
     * @param appRootPath アプリケーションルートパス
     * @param tempDir テンポラリディレクトリパス
     * @throws Exception 実行時例外
     */
    public void registRngTpl(
            Rng090knParamModel paramMdl,
            MlCountMtController controller,
            int userSid,
            String appRootPath,
            String tempDir) throws Exception {
        int procMode = paramMdl.getRngTplCmdMode();
        int tplMode = paramMdl.getRngTemplateMode();

        //稟議テンプレートモデルを作成
        RngTemplateModel rtMdl = __createRTModel(
                paramMdl, controller, userSid, appRootPath, tempDir, procMode);

        RngTemplateDao rtDao = new RngTemplateDao(con__);
        //稟議テンプレートを登録or更新
        if (procMode == RngConst.RNG_CMDMODE_ADD) {
            log__.debug("// 稟議テンプレートを登録しました。");
            //登録
            rtDao.insert(rtMdl, tplMode, paramMdl.getRng090CatSid());
        }
        if (procMode == RngConst.RNG_CMDMODE_EDIT) {
            log__.debug("// 稟議テンプレートを更新しました。");
            int tmpSid = paramMdl.getRngSelectTplSid();
            //添付ファイル情報の削除
            __delTplTmpFile(paramMdl, userSid, tmpSid);

            //カテゴリ変更があった場合はソート値の更新も行う
            if (__moveCategory(paramMdl, rtMdl.getRtpSid())) {
                rtDao.updateChangeCategory(rtMdl);
            } else {
                //カテゴリ変更がなかった場合はソート値は一切変更しない
                rtDao.updateNotChangeCategory(rtMdl);
            }
        }

        //経路情報の登録を行う。
        __entryKeiro(paramMdl, rtMdl.getRtpSid(), userSid);

        UDate now = new UDate();
        //バイナリー情報の登録
        CommonBiz cmnBiz = new CommonBiz();
        List < String > binSidList = cmnBiz.insertBinInfo(
                con__, tempDir, appRootPath, controller, userSid, now);

        //稟議添付情報の登録
        RngTemplateBinDao binDao = new RngTemplateBinDao(con__);
        //もし、バイナリSIDがnullじゃなければ、、
        if (binSidList != null) {
            //バイナリSIDの数だけinsertを行う
            RngTemplateBinModel binMdl = new RngTemplateBinModel();
            for (String binSid : binSidList) {
                binMdl.setRtpSid(rtMdl.getRtpSid());
                binMdl.setBinSid(Long.parseLong(binSid));
                binDao.insert(binMdl);
            }
        } else {
            log__.debug("// 添付情報は更新しませんでした。");
        }
    }

    /**
     * <br>[機  能] 稟議テンプレートモデルを作成
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param controller MlCountMtController
     * @param userSid ログインユーザSID
     * @param appRootPath アプリケーションルートパス
     * @param tempDir テンポラリディレクトリパス
     * @param mode 処理モード
     * @return RngTemplateModel
     * @throws SQLException SQL実行時例外
     */
    private RngTemplateModel __createRTModel(
            Rng090knParamModel paramMdl,
            MlCountMtController controller,
            int userSid,
            String appRootPath,
            String tempDir,
            int mode) throws SQLException {

        log__.debug("稟議テンプレートモデルを作成します。");
        RngTemplateModel mdl = new RngTemplateModel();
        RngTemplateDao dao = new RngTemplateDao(con__);
        int rngTemplateMode = paramMdl.getRngTemplateMode();

        int rngSid;
        //もし処理モードが追加なら、
        if (mode == RngConst.RNG_CMDMODE_ADD) {
            log__.debug("//採番マスタから稟議SIDを取得。");
            rngSid = (int) controller.getSaibanNumber(RngConst.SBNSID_RINGI,
                                                RngConst.SBNSID_SUB_RINGI_TEMPLATE,
                                                userSid);
            int maxSort = dao.getMaxSort(rngTemplateMode, userSid, paramMdl.getRng090CatSid());
            mdl.setRtpSort(maxSort + 1);
        //そうでないなら、
        } else {
            log__.debug("//現在選択されている稟議テンプレートのSIDを取得。");
            rngSid = paramMdl.getRngSelectTplSid();
            //カテゴリ間の移動がある場合、移動先のソート最大値+1をセット
            if (__moveCategory(paramMdl, rngSid)) {
                int maxSort = dao.getMaxSort(rngTemplateMode, userSid, paramMdl.getRng090CatSid());
                mdl.setRtpSort(maxSort + 1);
            }
        }

        UDate now = new UDate();
        mdl.setRtpSid(rngSid);
        mdl.setRtpTitle(paramMdl.getRng090title());
        mdl.setRtpRngTitle(paramMdl.getRng090rngTitle());
        mdl.setRtpContent(paramMdl.getRng090content());
        if (rngTemplateMode != RngConst.RNG_TEMPLATE_SHARE) {
            mdl.setUsrSid(userSid);
        }
        mdl.setRtpType(rngTemplateMode);
        mdl.setRtpAuid(userSid);
        mdl.setRtpAdate(now);
        mdl.setRtpEuid(userSid);
        mdl.setRtpEdate(now);
        mdl.setRtcSid(paramMdl.getRng090CatSid());
        return mdl;
    }

    /**
     * <br>[機  能] 指定したテンプレートSIDのバイナリ情報を削除します
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param userSid ユーザSID
     * @param rtpSid テンプレートSID
     * @throws Exception 例外
     */
    private void __delTplTmpFile(Rng090knParamModel paramMdl, int userSid, int rtpSid)
    throws Exception {

        RngTemplateBinDao rtdao = new RngTemplateBinDao(con__);
        //指定した稟議テンプレートSIDと関連するバイナリSIDを取得
        ArrayList<String> binlist = rtdao.selectBinList(rtpSid);
        //現在日時
        UDate now = new UDate();

        //もし、バイナリSIDリストが空でなければ、
        if (binlist != null || !binlist.isEmpty()) {
            //添付ファイルの論理削除を行う。
            log__.debug("// 添付ファイルの論理削除を行います = " + binlist.size());
            for (String bin : binlist) {
                CmnBinfDao dao = new CmnBinfDao(con__);
                CmnBinfModel mdl = new CmnBinfModel();
                mdl.setBinUpuser(userSid);
                mdl.setBinUpdate(now);
                mdl.setBinJkbn(GSConst.JTKBN_DELETE);
                mdl.setBinSid(Long.parseLong(bin));
                //論理削除実行
                dao.removeBinData(mdl);
            }

        } else {
            //そうでなければ論理削除を行わない。
            log__.debug("// 添付ファイルの論理削除を行いませんでした。");
        }

        RngTemplateBinDao rtbdao = new RngTemplateBinDao(con__);
        //指定した稟議テンプレートSIDのバイナリ情報を全て削除
        rtbdao.deleteTpl(rtpSid);
    }

    /**
     * <br>[機  能] 経路情報の登録処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param rtpSid 稟議テンプレートSID
     * @param userSid セッションユーザSID
     * @throws Exception 実行例外
     */
    private void __entryKeiro(Rng090knParamModel paramMdl,
                                    int rtpSid,
                                    int userSid)
    throws Exception {

        int mode = paramMdl.getRngTplCmdMode();

        UDate now = new UDate();

        //経路登録
        RngTemplateUserModel model = new RngTemplateUserModel();
        model.setRtpSid(rtpSid);
        model.setUsrSid(userSid);
        model.setRtuAuid(userSid);
        model.setRtuAdate(now);
        model.setRtuEuid(userSid);
        model.setRtuEdate(now);

        //経路テンプレートユーザ情報の登録
        RngTemplateUserDao tempUserDao = new RngTemplateUserDao(con__);

        if (mode == RngConst.RNG_CMDMODE_EDIT) {
            //処理モード = 編集の場合、経路情報の削除を行う
            tempUserDao.delete(rtpSid);
        }

        RngTemplateUserModel tempUserModel = new RngTemplateUserModel();
        tempUserModel.setRtpSid(rtpSid);
        tempUserModel.setRtuAuid(userSid);
        tempUserModel.setRtuAdate(now);
        tempUserModel.setRtuEuid(userSid);
        tempUserModel.setRtuEdate(now);

        int rcuSort = 1;

        //承認経路の登録
        rcuSort = __entryRtpUser(tempUserModel, mode, paramMdl.getRng090apprUser(),
                                RngConst.RNG_RNCTYPE_APPR, rcuSort);
        //最終確認の登録
        __entryRtpUser(tempUserModel, mode, paramMdl.getRng090confirmUser(),
                    RngConst.RNG_RNCTYPE_CONFIRM, rcuSort);

    }

    /**
     * <br>[機  能] 経路情報の登録を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param rtuModel 経路情報モデル
     * @param mode 処理モード
     * @param userList ユーザ一覧
     * @param rtuType 承認者種別
     * @param sort 経路順
     * @return 経路順
     * @throws SQLException SQL実行時例外
     */
    private int __entryRtpUser(RngTemplateUserModel rtuModel,
                                int mode, String[] userList, int rtuType, int sort)
    throws SQLException {

        if (userList == null || userList.length < 1) {
            return sort;
        }

        RngTemplateUserDao rtuDao = new RngTemplateUserDao(con__);

        rtuModel.setRtuType(rtuType);
        for (String user : userList) {
            rtuModel.setUsrSid(Integer.parseInt(user));
            rtuModel.setRtuSort(sort++);
            rtuDao.insert(rtuModel);
        }

        return sort;
    }

    /**
     * <br>[機  能] カテゴリ間の移動があるか判断
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param tplSid テンプレートSID
     * @return カテゴリ間の移動があるか true=あり false=なし
     * @throws SQLException SQL実行時例外
     */
    private boolean __moveCategory(Rng090knParamModel paramMdl, int tplSid)
    throws SQLException {

        RngTemplateDao dao = new RngTemplateDao(con__);
        //編集mode以外に、カテゴリ間の移動はありえない
        if (paramMdl.getRngTplCmdMode() == RngConst.RNG_CMDMODE_EDIT) {
            if (paramMdl.getRng090CatSid() != dao.select(tplSid).getRtcSid()) {
                return true;
            }
        }
        return false;
    }
}
