package jp.groupsession.v2.rng.rng020kn;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmInfDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.rng.biz.RngBiz;
import jp.groupsession.v2.rng.model.RingiChannelDataModel;
import jp.groupsession.v2.rng.model.RingiRequestModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 稟議作成確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng020knBiz {

    /** Loggingインスタンス */
    private static Log log__ = LogFactory.getLog(Rng020knBiz.class);

    /** コネクション */
    private Connection con__ = null;
    /** セッションユーザSID */
    private int usrSid__;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     */
    public Rng020knBiz(Connection con) {
        con__ = con;
    }

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param usrSid ユーザSID
     */
    public Rng020knBiz(Connection con, int usrSid) {
        con__ = con;
        usrSid__ = usrSid;
    }

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param paramMdl パラメータ情報
     * @param appRoot アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリパス
     * @param userMdl セッションユーザ情報
     * @throws SQLException SQL実行例外
     * @throws IOException 添付ファイルの操作に失敗
     * @throws IOToolsException 添付ファイルの操作に失敗
     */
    public void setInitData(RequestModel reqMdl, Rng020knParamModel paramMdl,
                            String appRoot, String tempDir, BaseUserModel userMdl)
    throws IOException, IOToolsException, SQLException {

        UDate now = new UDate();

        //申請者を設定する
        paramMdl.setRng020requestUser(userMdl.getUsiseimei());
        //作成日を設定する
        paramMdl.setRng020createDate(UDateUtil.getSlashYYMD(now)
                                + " "
                                + UDateUtil.getSeparateHMS(now));

        //内容をHTML表示用に変換する。
        String content =
            StringUtilHtml.transToHTmlPlusAmparsant(
                    NullDefault.getString(paramMdl.getRng020Content(), ""));
        paramMdl.setRng020knContent(content);

        String[] apprUser = paramMdl.getRng020apprUser();
        String[] confirmUser = paramMdl.getRng020confirmUser();
        //承認経路情報を設定する。
        if (apprUser != null) {
            paramMdl.setChannelList(__getUserLabelList(apprUser));
            paramMdl.setRng020apprUserCnt(String.valueOf(apprUser.length));
        }
        //最終確認情報を設定する。
        if (confirmUser != null) {
            paramMdl.setConfirmChannelList(__getUserLabelList(confirmUser));
            paramMdl.setRng020confirmUserCnt(String.valueOf(confirmUser.length));
        }
        //添付ファイル一覧を設定
        CommonBiz cmnBiz = new CommonBiz();
        paramMdl.setRng020fileList(cmnBiz.getTempFileLabelList(tempDir));

        log__.debug("End");
    }

    /**
     * <br>[機  能] 表示用のユーザリスト一覧を取得する
     * <br>[解  説] 指定したユーザの表示用ユーザリスト一覧を取得する
     * <br>[備  考]
     * @param userSidList ユーザSID一覧
     * @return 表示用のユーザリスト一覧
     * @throws SQLException SQL実行時例外
     */
    public List < RingiChannelDataModel > __getUserLabelList(String[] userSidList)
    throws SQLException {
        List<RingiChannelDataModel> userLabelList = new ArrayList<RingiChannelDataModel>();

        CmnUsrmInfDao uinfDao = new CmnUsrmInfDao(con__);
        List < CmnUsrmInfModel > userInfList
            = uinfDao.getUsersInfList(userSidList);
        RingiChannelDataModel model = new RingiChannelDataModel();

        //承認順のソートを行い、一覧モデルに設定する。
        for (String userSid : userSidList) {
            int intValue = Integer.parseInt(userSid);
            for (CmnUsrmInfModel usrMdl : userInfList) {
                if (intValue == usrMdl.getUsrSid()) {
                    model = new RingiChannelDataModel();
                    model.setUserName(usrMdl.getUsiSei() + " " + usrMdl.getUsiMei());
                    model.setYakusyoku(usrMdl.getUsiYakusyoku());
                    userLabelList.add(model);
                }
            }
        }
        return userLabelList;
    }

//    /**
//     * <br>[機  能] 表示用のユーザリストの項目数を取得する
//     * <br>[解  説]
//     * <br>[備  考]
//     * @param userSidList ユーザSID一覧
//     * @return 表示用のユーザリスト一覧
//     * @throws SQLException SQL実行時例外
//     */
//    public String __getUserLabelListCnt(String[] userSidList)
//    throws SQLException {
//        List<LabelValueBean> userLabelList = new ArrayList<LabelValueBean>();
//
//        RingiDao rngDao = new RingiDao(con__);
//        String cnt = rngDao.getUsrInfCount(userSidList);
//
//        return cnt;
//    }

    /**
     * <br>[機  能] 稟議情報の登録処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param cntCon MlCountMtController
     * @param appRootPath アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリ
     * @param mode 登録モード 0:申請 1:草稿
     * @param pluginConfig プラグイン情報
     * @param smlPluginUseFlg ショートメールプラグイン有効フラグ
     * @param reqMdl リクエスト情報
     * @throws Exception 実行例外
     */
    public void entryRingiData(Rng020knParamModel paramMdl,
                                MlCountMtController cntCon,
                                String appRootPath,
                                String tempDir,
                                int mode,
                                PluginConfig pluginConfig,
                                boolean smlPluginUseFlg,
                                RequestModel reqMdl)
    throws Exception {
        log__.debug("START");

        RingiRequestModel model = new RingiRequestModel();
        UserBiz userBiz = new UserBiz();
        RngBiz rngBiz = new RngBiz(con__, cntCon);
        String[] apprUsers = paramMdl.getRng020apprUser();
        String[] confirmUsers = paramMdl.getRng020confirmUser();
        if (apprUsers != null) {
            paramMdl.setRng020apprUserList(
                    rngBiz.sortLabelList(userBiz.getUserLabelList(
                            con__, paramMdl.getRng020apprUser()), apprUsers));
        }

        if (confirmUsers != null) {
            paramMdl.setRng020confirmUserList(
                    rngBiz.sortLabelList(userBiz.getUserLabelList(
                            con__, paramMdl.getRng020confirmUser()), confirmUsers));
        }

        model.setRngSid(paramMdl.getRngSid());
        model.setRngTitle(paramMdl.getRng020Title());
        model.setRngContent(paramMdl.getRng020Content());
        model.setRngapprUser(paramMdl.getRng020apprUser());
        model.setRngconfirmUser(paramMdl.getRng020confirmUser());
        model.setAppRootPath(appRootPath);
        model.setTempDir(tempDir);
        model.setUserSid(usrSid__);
        model.setDate(new UDate());

        rngBiz.entryRingiData(model, mode, paramMdl.getRngCmdMode(),
                pluginConfig, smlPluginUseFlg, reqMdl);

        log__.debug("End");
    }
}
