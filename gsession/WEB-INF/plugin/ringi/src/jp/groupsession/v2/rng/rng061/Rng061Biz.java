package jp.groupsession.v2.rng.rng061;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.rng.biz.RngBiz;
import jp.groupsession.v2.rng.biz.RngTemplateBiz;
import jp.groupsession.v2.rng.dao.RingiDao;
import jp.groupsession.v2.rng.dao.RngTemplateUserDao;
import jp.groupsession.v2.rng.model.RngTemplateModel;
import jp.groupsession.v2.rng.model.RngTemplateUserModel;
import jp.groupsession.v2.rng.rng020.Rng020Biz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 稟議 内容テンプレート選択確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng061Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rng061Biz.class);
    /** Connection */
    private Connection con__ = null;
    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param con Connection
     * @param reqMdl リクエスト情報
     */
    Rng061Biz(Connection con, RequestModel reqMdl) {
        con__ = con;
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
        log__.debug("テンポラリディレクトリのファイル削除 : " + tempDir);
    }

    /**
     * <br>[機  能] 初期表示処理を行います
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param appRoot アプリケーションルート
     * @param tempDir 添付ファイルディレクトリ
     * @param con コネクション
     * @param sessionUserSid セッションユーザSID
     * @throws IOToolsException ファイル生成実行例外
     * @throws SQLException SQL実行時例外
     * @throws IOException 生成実行例外
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public void initDsp(
            Rng061ParamModel paramMdl,
            String appRoot,
            String tempDir,
            Connection con,
            int sessionUserSid)
    throws SQLException, IOException, IOToolsException, TempFileException {


        RngTemplateBiz rtBiz = new RngTemplateBiz();
        RingiDao ringiDao = new RingiDao(con);
        RngBiz rBiz = new RngBiz(con);
        int rstSid = paramMdl.getRngSelectTplSid();
        log__.debug("form.getRngSelectTplSid() = " + paramMdl.getRngSelectTplSid());

        RngTemplateModel rtModel = rtBiz.getRtpModel(rstSid, con__);
        if (rtModel != null) {
            log__.debug("テンプレート情報をセットします。");
            paramMdl.setRng061title(rtModel.getRtpTitle());
            paramMdl.setRng061rngTitle(rtModel.getRtpRngTitle());
            paramMdl.setRng061content(rtModel.getRtpContent());
            //内容の改行を反映し、設定
            paramMdl.setRng061viewContent(
                    StringUtilHtml.transToHTmlForTextArea(
                            NullDefault.getString(rtModel.getRtpContent(), "")
                    ));
            paramMdl.setRng061templateType(rtModel.getRtpType());
        } else {
            log__.debug("テンプレート情報をセットしません。");
        }

        //添付ファイル生成
        rBiz.setRingiTemplateTempFileData(con__, rstSid, appRoot, tempDir, reqMdl__);

        log__.debug("添付ファイル一覧を設定");
        CommonBiz cmnBiz = new CommonBiz();
        paramMdl.setRng061FileLabelList(cmnBiz.getTempFileLabelList(tempDir));
        //表示用(ファイル名にサイズ追加)
        paramMdl.setTmpFileList(ringiDao.getRingiTemplateTmpFileList(rstSid));

        //経路情報設定
        __setKeiro(paramMdl, con, sessionUserSid);
    }

    /**
     * <br>[機  能] 選択ボタンクリック時の処理を行います
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param appRoot アプリケーションルート
     * @param tempDir 稟議添付ファイルディレクトリ
     * @param tempTplDir 稟議テンプレート添付ファイルディレクトリ
     * @throws IOToolsException ファイル生成実行例外
     * @throws SQLException SQL実行時例外
     * @throws IOException 生成実行例外
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public void initOpt(
            Rng061ParamModel paramMdl,
            String appRoot,
            String tempDir,
            String tempTplDir)
    throws SQLException, IOException, IOToolsException, TempFileException {

        int rstSid = paramMdl.getRngSelectTplSid();

        doDeleteFile(tempDir);

        //添付ファイル生成
        Rng020Biz biz020 = new Rng020Biz(reqMdl__);
        RngBiz rBiz = new RngBiz(con__);
        rBiz.setRingiTemplateTempFileData(con__, rstSid, appRoot,
                                        biz020.getTemplateFileDir(tempDir),
                                        reqMdl__);

        doDeleteFile(tempTplDir);

    }

    /**
     * <br>[機  能] 経路情報を設定します。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param sessionUserSid セッションユーザSID
     * @throws SQLException SQL実行時例外
     */
    private void __setKeiro(Rng061ParamModel paramMdl, Connection con, int sessionUserSid)
    throws SQLException {

        int rstSid = paramMdl.getRngSelectTplSid();

        //経路情報を設定
        RngTemplateUserDao rtuDao = new RngTemplateUserDao(con__);
        List<RngTemplateUserModel> rtuList = rtuDao.getRtuList(rstSid);
        List<String> apprUser = new ArrayList<String>();
        List<String> confirmUser = new ArrayList<String>();

        for (RngTemplateUserModel rtuMdl : rtuList) {
            int userSid = rtuMdl.getUsrSid();

            //セッションユーザは経路から除外する
            if (userSid != sessionUserSid) {
                if (rtuMdl.getRtuType() == RngConst.RNG_RNCTYPE_APPR) {
                    apprUser.add(String.valueOf(userSid));
                } else if (rtuMdl.getRtuType() == RngConst.RNG_RNCTYPE_CONFIRM) {
                    confirmUser.add(String.valueOf(userSid));
                }
            }
        }

        String[] apprUserList = (String[]) apprUser.toArray(new String[apprUser.size()]);
        String[] confirmUserList = (String[]) confirmUser.toArray(new String[confirmUser.size()]);

        //削除されたユーザは除外する
        UserBiz userBiz = new UserBiz();
        apprUserList = userBiz.getNormalUser(con, apprUserList);
        confirmUserList = userBiz.getNormalUser(con, confirmUserList);

        paramMdl.setRng061apprUser(apprUserList);
        paramMdl.setRng061confirmUser(confirmUserList);

        //経路のユーザ情報を取得する。
        List<CmnUsrmInfModel> apprList = userBiz.getUserList(con, apprUserList);
        List<CmnUsrmInfModel> confirmList = userBiz.getUserList(con, confirmUserList);
        RngBiz rngBiz = new RngBiz(con);
        paramMdl.setRng061ApprUserList(rngBiz.sortUserList(apprList, apprUserList));
        paramMdl.setRng061ConfirmUserList(rngBiz.sortUserList(confirmList, confirmUserList));
    }
}
