package jp.groupsession.v2.rng.rng090;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.base.CmnBinfDao;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.rng.biz.RngBiz;
import jp.groupsession.v2.rng.biz.RngTemplateBiz;
import jp.groupsession.v2.rng.dao.RngTemplateBinDao;
import jp.groupsession.v2.rng.dao.RngTemplateCategoryDao;
import jp.groupsession.v2.rng.dao.RngTemplateDao;
import jp.groupsession.v2.rng.dao.RngTemplateUserDao;
import jp.groupsession.v2.rng.model.RngTemplateCategoryModel;
import jp.groupsession.v2.rng.model.RngTemplateModel;
import jp.groupsession.v2.rng.model.RngTemplateUserModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 稟議 テンプレート登録画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng090Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rng090Biz.class);
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
    Rng090Biz(Connection con, RequestModel reqMdl) {
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
        log__.debug("テンポラリディレクトリのファイル削除");
    }


    /**
     * <br>[機  能] 初期表示処理を行います
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param appRoot アプリケーションルート
     * @param tempDir 添付ファイルディレクトリ
     * @param cmd コマンド
     * @param userSid ユーザSID
     * @throws IOToolsException ファイル生成実行例外
     * @throws SQLException SQL実行時例外
     * @throws IOException 生成実行例外
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public void initDsp(
            Rng090ParamModel paramMdl,
            String appRoot,
            String tempDir,
            String cmd,
            int userSid)
    throws SQLException, IOException, IOToolsException, TempFileException {

        //編集モード時、テンプレート情報を取得する
        if (paramMdl.getRngTplCmdMode() == RngConst.RNG_CMDMODE_EDIT
                && cmd.equals("060title")) {

            RngTemplateBiz rtBiz = new RngTemplateBiz();
            RngBiz rBiz = new RngBiz(con__);
            int rstSid = paramMdl.getRngSelectTplSid();

            RngTemplateModel rtModel = rtBiz.getRtpModel(rstSid, con__);
            if (rtModel != null) {
                log__.debug("テンプレート情報をセットします。");
                paramMdl.setRng090title(rtModel.getRtpTitle());
                paramMdl.setRng090rngTitle(rtModel.getRtpRngTitle());
                paramMdl.setRng090content(rtModel.getRtpContent());
                paramMdl.setRng090CatSid(rtModel.getRtcSid());
            } else {
                log__.debug("テンプレート情報をセットしません。");
            }

            //添付ファイル生成
            rBiz.setRingiTemplateTempFileData(con__, rstSid, appRoot, tempDir,
                                            reqMdl__);

        } else {
            log__.debug("追加モードです。");
        }

        //経路情報の所為表示を行う。
        __setInitKeiro(paramMdl, userSid, cmd);

        //カテゴリ情報の取得。
        __setCategoryLabelList(paramMdl, userSid);

        setFileLabelList(paramMdl, tempDir);
    }

    /**
     * <br>[機  能] 添付ファイル一覧を生成
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param tempDir テンポラリディレクトリ
     * @throws IOToolsException 例外
     */
    public void setFileLabelList(Rng090ParamModel paramMdl, String tempDir)
    throws IOToolsException {
        log__.debug("添付ファイル一覧を設定");
        CommonBiz cmnBiz = new CommonBiz();
        paramMdl.setRng090FileLabelList(cmnBiz.getTempFileLabelList(tempDir));
    }

    /**
     * <br>[機  能] 稟議テンプレートの削除を行います
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param userSid userSid
     * @param rtpSid 稟議テンプレートSID
     * @throws Exception 例外
     */
    public void deleteTpl(Rng090ParamModel paramMdl, int userSid, int rtpSid) throws Exception {

        RngTemplateBinDao rtbdao = new RngTemplateBinDao(con__);
        RngTemplateDao rtdao = new RngTemplateDao(con__);
        RngTemplateUserDao rtudao = new RngTemplateUserDao(con__);
        //指定した稟議テンプレートSIDと関連するバイナリSIDを取得
        ArrayList<String> binlist = rtbdao.selectBinList(rtpSid);
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

        int sort = rtdao.select(rtpSid).getRtpSort();
        int catSid = rtdao.select(rtpSid).getRtcSid();

        //ソート順の更新を行う
        rtdao.updateSortAll2(paramMdl.getRngTemplateMode(), userSid, now, sort, catSid);

        //指定した稟議テンプレートSIDのテンプレートを削除
        rtdao.deleteTpl(rtpSid);
        //指定した稟議テンプレートSIDのバイナリ情報を全て削除
        rtbdao.deleteTpl(rtpSid);
        //指定した稟議テンプレートSIDの経路情報を全て削除
        rtudao.delete(rtpSid);
    }

    /**
     * <br>[機  能] 経路の初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param userSid ユーザSID
     * @param cmd コマンド
     * @throws SQLException SQL実行例外
     */
    private void __setInitKeiro(Rng090ParamModel paramMdl, int userSid, String cmd)
    throws SQLException {

        int mode = paramMdl.getRngTplCmdMode();
        if (mode == RngConst.RNG_CMDMODE_EDIT && cmd.equals("060title")) {

            int rstSid = paramMdl.getRngSelectTplSid();

            //経路情報を設定
            RngTemplateUserDao rtuDao = new RngTemplateUserDao(con__);
            List<RngTemplateUserModel> rtuList = rtuDao.getRtuList(rstSid);
            List<String> apprUser = new ArrayList<String>();
            List<String> confirmUser = new ArrayList<String>();

            for (RngTemplateUserModel rtuMdl : rtuList) {
                if (rtuMdl.getRtuType() == RngConst.RNG_RNCTYPE_APPR) {
                    apprUser.add(String.valueOf(rtuMdl.getUsrSid()));
                } else if (rtuMdl.getRtuType() == RngConst.RNG_RNCTYPE_CONFIRM) {
                    confirmUser.add(String.valueOf(rtuMdl.getUsrSid()));
                }
            }
            paramMdl.setRng090apprUser((String[]) apprUser.toArray(new String[apprUser.size()]));
            paramMdl.setRng090confirmUser(
                    (String[]) confirmUser.toArray(new String[confirmUser.size()]));

        }

        //グループが未選択の場合、デフォルトグループを設定
        if (paramMdl.getRng090group() == -1) {
            GroupBiz grpBz = new GroupBiz();
            int defGrp = grpBz.getDefaultGroupSid(userSid, con__);
            paramMdl.setRng090group(defGrp);
        }

        GsMessage gsMsg = new GsMessage(reqMdl__);

        //グループコンボを設定する。
        GroupBiz gpBiz = new GroupBiz();
        List<LabelValueBean> groupLabel = gpBiz.getGroupCombLabelList(con__, true, gsMsg);
        groupLabel.remove(0);
        paramMdl.setRng090groupList(groupLabel);

        //ユーザ一覧、承認経路、最終確認を設定する。
        String[] apprUser = paramMdl.getRng090apprUser();
        String[] confirmUser = paramMdl.getRng090confirmUser();
        List<String> notUser = new ArrayList<String>();
        if (apprUser != null) {
            notUser.addAll((List<String>) Arrays.asList(apprUser));
        }
        if (confirmUser != null) {
            notUser.addAll((List<String>) Arrays.asList(confirmUser));
        }

        //共有テンプレートのみ承認経路に本人を追加可能
        if (paramMdl.getRngTemplateMode() == RngConst.RNG_TEMPLATE_PRIVATE) {
            notUser.add(String.valueOf(userSid));
        }

        UserBiz userBiz = new UserBiz();
        paramMdl.setRng090userList(
                userBiz.getNormalUserLabelList(con__, paramMdl.getRng090group(),
                                        (String[]) notUser.toArray(new String[notUser.size()]),
                                        false, gsMsg));

        paramMdl.setRng090apprUserList(
                __sortLabelList(userBiz.getUserLabelList(con__, apprUser), apprUser));
        paramMdl.setRng090confirmUserList(
                __sortLabelList(userBiz.getUserLabelList(con__, confirmUser), confirmUser));

        log__.debug("End");
    }

    /**
     * <br>[機  能] ラベルリストを指定した値順に並び替える
     * <br>[解  説]
     * <br>[備  考]
     * @param labelList ラベルリスト
     * @param values 並び順
     * @return 並び替え後のラベルリスト
     */
    private List<LabelValueBean> __sortLabelList(List<LabelValueBean> labelList, String[] values) {

        List<LabelValueBean> sortLabelList = new ArrayList<LabelValueBean>();

        if (values == null || values.length <= 0) {
            return labelList;
        }

        for (String value : values) {
            int intValue = Integer.parseInt(value);
            for (LabelValueBean label : labelList) {
                if (intValue == Integer.parseInt(label.getValue())) {
                    sortLabelList.add(label);
                }
            }
        }

        return sortLabelList;
    }

    /**
     * <br>[機  能] カテゴリ一覧を生成
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param usrSid ユーザSID
     * @throws SQLException SQL実行例外
     */
    public void __setCategoryLabelList(Rng090ParamModel paramMdl, int usrSid) throws SQLException {
        log__.debug("カテゴリ一覧を設定");
        RngTemplateCategoryDao dao = new RngTemplateCategoryDao(con__);
        ArrayList<RngTemplateCategoryModel> catList = new ArrayList<RngTemplateCategoryModel>();

        //共有
        if (paramMdl.getRngTemplateMode() == RngConst.RNG_TEMPLATE_SHARE) {
            catList = dao.selectAdmin();
        //個人
        } else if (paramMdl.getRngTemplateMode() == RngConst.RNG_TEMPLATE_PRIVATE) {
            catList = dao.selectUser(usrSid);
        }

        GsMessage gsMsg = new GsMessage(reqMdl__);
        String msg = gsMsg.getMessage("cmn.notset");

        //コンボ設定
        ArrayList<LabelValueBean> catLabel = new ArrayList<LabelValueBean>();
        catLabel.add(new LabelValueBean(msg, "0"));
        for (RngTemplateCategoryModel model : catList) {
            String catName = model.getRtcName();
            String catSid = Integer.toString(model.getRtcSid());
            catLabel.add(new LabelValueBean(catName, catSid));
        }

        paramMdl.setRng090CategoryList(catLabel);
    }

}
