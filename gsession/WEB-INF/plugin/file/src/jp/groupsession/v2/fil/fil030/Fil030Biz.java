package jp.groupsession.v2.fil.fil030;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.UsidSelectGrpNameDao;
import jp.groupsession.v2.cmn.dao.base.CmnBinfDao;
import jp.groupsession.v2.cmn.dao.base.CmnCmbsortConfDao;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.cmn.model.base.CmnCmbsortConfModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.dao.FileAccessConfDao;
import jp.groupsession.v2.fil.dao.FileAconfDao;
import jp.groupsession.v2.fil.dao.FileCabinetAdminDao;
import jp.groupsession.v2.fil.dao.FileCabinetDao;
import jp.groupsession.v2.fil.model.FileAconfModel;
import jp.groupsession.v2.fil.model.FileCabinetModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * キャビネット登録・編集画面で使用するビジネスロジッククラス
 * @author JTS
 */
public class Fil030Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Fil030Biz.class);
    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;
    /** リクエスト情報 */
    private Connection con__ = null;

    /**
     * <p>コンストラクタ
     * @param con Connection
     * @param reqMdl RequestModel
     */
    public Fil030Biz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }

    /**
     * <br>初期表示画面情報を取得します
     * @param paramMdl Fil030ParamModel
     * @param tempDir テンポラリディレクトリパス
     * @param appRoot アプリケーションルートパス
     * @param cmd コマンド
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException ファイル操作時例外
     * @throws IOException ファイル操作時例外
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public void setInitData(Fil030ParamModel paramMdl,
            String tempDir, String appRoot,
            String cmd)
    throws SQLException, IOToolsException, IOException, TempFileException {

        log__.debug("キャビネット登録初期表示");

        if (paramMdl.getCmnMode().equals(GSConstFile.CMN_MODE_ADD)) {
            //新規
            setInitDataNew(paramMdl, tempDir, appRoot);

        } else if (paramMdl.getCmnMode().equals(GSConstFile.CMN_MODE_EDT)) {
            //編集
            setInitDataEdit(paramMdl, tempDir, appRoot, cmd);

        } else if (paramMdl.getCmnMode().equals(GSConstFile.CMN_MODE_MLT)) {
            //一括編集
            if (paramMdl.getFil220sltCheck().length == 1) {

                //単一SIDなら編集モードに移行
                //編集
                setInitDataEdit(paramMdl, tempDir, appRoot, cmd);

            } else if (paramMdl.getFil220sltCheck().length > 1) {
                //SIDが2つ以上なら一括編集モード
                setInitDataNew(paramMdl, tempDir, appRoot);

                FileCabinetDao cabDao = new FileCabinetDao(con__);

                String[] sidlist = paramMdl.getFil220sltCheck();
                ArrayList<FileCabinetModel> ret = new ArrayList<FileCabinetModel>();

                //複数SIDからキャビネットリストを作成
                for (String sid : sidlist) {
                    ret.add(cabDao.select(Integer.parseInt(sid)));
                }

                StringBuilder cabinetsName = new StringBuilder();

                for (FileCabinetModel mdl : ret) {
                    if (cabinetsName.length() > 0) {
                        cabinetsName.append(", ");
                    }
                    cabinetsName.append(mdl.getFcbName());
                }

                paramMdl.setFil030CabinetName(cabinetsName.toString());
            }


        }

        //アクセス権限 グループコンボ
        paramMdl.setFil030AcGroupLavel(__getAccessGroupLavle(con__, true));
        //アクセス権限 候補一覧
        paramMdl.setFil030AcRightLavel(__getAccessRightLavle(paramMdl, con__));
        //アクセス権限 フルアクセス一覧
        paramMdl.setFil030AcFullLavel(__getAccessFullLavle(paramMdl, con__));
        //アクセス権限 閲覧アクセス一覧
        paramMdl.setFil030AcReadLavel(__getAccessReadLavle(paramMdl, con__));

        //管理者権限 グループコンボ
        paramMdl.setFil030AdmGroupLavel(__getAccessGroupLavle(con__, false));
        //管理者権限 候補一覧
        paramMdl.setFil030AdmRightLavel(__getAdmRightLavle(paramMdl, con__));
        //管理者権限 管理者一覧
        paramMdl.setFil030AdmLavel(__getAdmLavle(paramMdl, con__));

        //使用率ラベル
        __setCapaWarnLavle(paramMdl);

        //管理者設定バージョン管理区分
        int verKbnAdm = __setVerKbnAdmin(paramMdl, con__);

        if (verKbnAdm == GSConstFile.VERSION_KBN_ON) {
            //バージョン世代ラベル設定
            __setVersionLavle(paramMdl);
        }
    }
    /**
     * <br>新規モード時の初期表示を設定します
     * @param paramMdl Fil030ParamModel
     * @param tempDir テンポラリディレクトリパス
     * @param appRoot アプリケーションルートパス
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException ファイル操作時例外
     * @throws IOException ファイル操作時例外
     */
    public void setInitDataNew(Fil030ParamModel paramMdl,
            String tempDir, String appRoot)
    throws SQLException, IOToolsException, IOException {

        //新規
        paramMdl.setFil030AccessKbn(
                NullDefault.getString(
                        paramMdl.getFil030AccessKbn(),
                        String.valueOf(GSConstFile.ACCESS_KBN_OFF)));
        paramMdl.setFil030CapaKbn(
                NullDefault.getString(
                        paramMdl.getFil030CapaKbn(),
                        String.valueOf(GSConstFile.CAPA_KBN_OFF)));
    }

    /**
     * <br>編集モード時の初期表示を設定します
     * @param paramMdl Fil030ParamModel
     * @param tempDir テンポラリディレクトリパス
     * @param appRoot アプリケーションルートパス
     * @param cmd コマンド
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException ファイル操作時例外
     * @throws IOException ファイル操作時例外
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public void setInitDataEdit(Fil030ParamModel paramMdl,
            String tempDir, String appRoot, String cmd)
    throws SQLException, IOToolsException, IOException, TempFileException {

//        FilCommonBiz filBiz = new FilCommonBiz(con__, reqMdl__);
        CommonBiz cmnBiz = new CommonBiz();

        //編集
        int cabSid = 0;
        if (paramMdl.getCmnMode().equals(GSConstFile.CMN_MODE_MLT)) {
            cabSid = NullDefault.getInt(paramMdl.getFil220sltCheck()[0], -1);
            paramMdl.setFil030SelectCabinet(paramMdl.getFil220sltCheck()[0]);
            paramMdl.setCmnMode(GSConstFile.CMN_MODE_EDT);
        } else {
            cabSid = NullDefault.getInt(paramMdl.getFil030SelectCabinet(), -1);
        }

        FileCabinetDao cabDao = new FileCabinetDao(con__);
        FileCabinetModel cabModel = cabDao.select(cabSid);
        if (cabModel != null) {
            paramMdl.setFil030CabinetName(
                    NullDefault.getString(paramMdl.getFil030CabinetName(), cabModel.getFcbName()));
            paramMdl.setFil030AccessKbn(
                    NullDefault.getString(
                            paramMdl.getFil030AccessKbn(),
                            String.valueOf(cabModel.getFcbAccessKbn())));
            //アクセス制御情報
            FileAccessConfDao acDao = new FileAccessConfDao(con__);
            if (paramMdl.getFil030Biko() == null) {
                paramMdl.setFil030SvAcFull(
                        acDao.getAccessUser(cabModel.getFcbSid(),
                                Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE)));

                paramMdl.setFil030SvAcRead(
                        acDao.getAccessUser(cabModel.getFcbSid(),
                                Integer.parseInt(GSConstFile.ACCESS_KBN_READ)));

                FileCabinetAdminDao admDao = new FileCabinetAdminDao(con__);
                paramMdl.setFil030SvAdm(admDao.getAdminUserSid(cabModel.getFcbSid()));
            }

            paramMdl.setFil030CapaKbn(
                    NullDefault.getString(
                            paramMdl.getFil030CapaKbn(),
                            String.valueOf(cabModel.getFcbCapaKbn())));
            paramMdl.setFil030CapaSize(
                    NullDefault.getString(
                            paramMdl.getFil030CapaSize(),
                            String.valueOf(cabModel.getFcbCapaSize())));
            paramMdl.setFil030CapaWarn(
                    NullDefault.getString(
                            paramMdl.getFil030CapaWarn(),
                            String.valueOf(cabModel.getFcbCapaWarn())));
            paramMdl.setFil030VerKbn(
                    NullDefault.getString(
                            paramMdl.getFil030VerKbn(),
                            String.valueOf(cabModel.getFcbVerKbn())));
            paramMdl.setFil030VerAllKbn(
                    NullDefault.getString(
                            paramMdl.getFil030VerAllKbn(),
                            String.valueOf(cabModel.getFcbVerallKbn())));
            paramMdl.setFil030Biko(
                    NullDefault.getString(paramMdl.getFil030Biko(), cabModel.getFcbBiko()));

            if (paramMdl.getFil030VerAllKbn().equals(
                    String.valueOf(GSConstFile.VERSION_ALL_KBN_OFF))) {
                paramMdl.setFil030VerKbn(String.valueOf(GSConstFile.VERSION_KBN_OFF));
            }
            //初期表示のみ
            //編集時、キャビネット管理からの一括編集時、キャビネット管理からの変更時
//            if (cmd.equals("fil020edit")
//             || cmd.equals("fil220togetherEdit")
//             || cmd.equals("fil220editCabinet")) {
//                //添付ファイルをテンポラリディレクトリへ設定する
//                filBiz.setCabinetTempFile(appRoot,
//                        tempDir + GSConstFile.TEMP_DIR_NORMAL + File.separator, cabSid, con__);
//            }

            if (paramMdl.getCmnMode().equals(GSConstFile.CMN_MODE_EDT)
                    && cabModel.getFcbMark() > 0 && !paramMdl.getFil030InitFlg().equals("1")) {
                if (cabModel.getFcbMark() > 0) {
                    CmnBinfModel binMdl = cmnBiz.getBinInfo(con__, cabModel.getFcbMark(),
                            reqMdl__.getDomain());
                    if (binMdl != null) {

                        //アイコンのテンポラリディレクトリパス
                        String markTempDir =
                                IOTools.replaceFileSep(tempDir + GSConstFile.TEMP_DIR_MARK + "/");

                        //添付ファイルをテンポラリディレクトリに格納する。
                        String imageSaveName =
                                cmnBiz.saveSingleTempFile(binMdl, appRoot, markTempDir);
                        paramMdl.setFil030ImageName(binMdl.getBinFileName());
                        paramMdl.setFil030ImageSaveName(imageSaveName);
                    }
                }
            }

            //添付ファイルのラベルを設定する。
            paramMdl.setFil030SelectTempFiles(null);
            //添付ファイルのラベルを設定する。
            paramMdl.setFil030SelectTempFilesMark(null);
        }
    }

    /**
     * アクセス権限の候補抽出用グループ一覧を取得する
     * @param con コネクション
     * @param comboflg true:コンボ用 false:候補用
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getAccessGroupLavle(
            Connection con, boolean comboflg)
    throws SQLException {
        ArrayList<LabelValueBean> ret = new ArrayList<LabelValueBean>();

//        GroupDao dao = new GroupDao(con);
//        ArrayList<GroupModel> gpList = dao.getGroupTree();
        GroupBiz groupBiz = new GroupBiz();
        ArrayList<GroupModel> gpList = groupBiz.getGroupCombList(con);
        LabelValueBean lavelBean = new LabelValueBean();

        GsMessage gsMsg = new GsMessage(reqMdl__);

        if (comboflg) {
            String textGroup = gsMsg.getMessage("cmn.grouplist");
            lavelBean.setLabel(textGroup);
            lavelBean.setValue(GSConstFile.GROUP_COMBO_VALUE);
            ret.add(lavelBean);
        } else {
            String textSelect = gsMsg.getMessage("cmn.select.plz");
            lavelBean.setLabel(textSelect);
            lavelBean.setValue("-1");
            ret.add(lavelBean);
        }

        for (GroupModel gmodel : gpList) {
            lavelBean = new LabelValueBean();
            lavelBean.setLabel(gmodel.getGroupName());
            lavelBean.setValue(String.valueOf(gmodel.getGroupSid()));
            ret.add(lavelBean);
        }
        return ret;
    }

    /**
     * アクセス権限の候補用一覧を取得する
     * @param paramMdl Fil030ParamModel
     * @param con コネクション
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getAccessRightLavle(
            Fil030ParamModel paramMdl, Connection con)
    throws SQLException {

        ArrayList<LabelValueBean> ret = new ArrayList<LabelValueBean>();
        //アクセス権限 グループコンボ選択値
        int acSltGp = NullDefault.getInt(
                paramMdl.getFil030AcSltGroup(), Integer.parseInt(GSConstFile.GROUP_COMBO_VALUE));
        //除外するグループSID
        String[] leftFull = paramMdl.getFil030SvAcFull();
        String[] leftRead = paramMdl.getFil030SvAcRead();

        if (acSltGp == Integer.parseInt(GSConstFile.GROUP_COMBO_VALUE)) {
            //グループを全て取得
            GroupDao dao = new GroupDao(con);
            CmnCmbsortConfDao sortDao = new CmnCmbsortConfDao(con);
            CmnCmbsortConfModel sortMdl = sortDao.getCmbSortData();
            ArrayList<GroupModel> allGpList = dao.getGroupTree(sortMdl);

            //除外するグループSID(フルアクセス選択済み)
            List<String> fullGrepList = new ArrayList<String>();
            if (paramMdl.getFil030SvAcFull() != null) {
                fullGrepList = Arrays.asList(paramMdl.getFil030SvAcFull());
            }
            //除外するグループSID(閲覧アクセス選択済み)
            List<String> readGrepList = new ArrayList<String>();
            if (paramMdl.getFil030SvAcRead() != null) {
                readGrepList = Arrays.asList(paramMdl.getFil030SvAcRead());
            }

            for (GroupModel bean : allGpList) {
                if (!fullGrepList.contains(String.valueOf("G" + bean.getGroupSid()))
                &&  !readGrepList.contains(String.valueOf("G" + bean.getGroupSid()))) {
                    ret.add(new LabelValueBean(
                            bean.getGroupName(), String.valueOf("G" + bean.getGroupSid())));
                }
            }

        } else {

            //除外するユーザSID
            ArrayList<Integer> usrSids = new ArrayList<Integer>();
            if (leftFull != null) {
                for (int i = 0; i < leftFull.length; i++) {
                    usrSids.add(new Integer(NullDefault.getInt(leftFull[i], -1)));
                }
            }
            if (leftRead != null) {
                for (int i = 0; i < leftRead.length; i++) {
                    usrSids.add(new Integer(NullDefault.getInt(leftRead[i], -1)));
                }
            }
//            UserSearchDao usDao = new UserSearchDao(con);
//            List<CmnUsrmInfModel>usList = usDao.getBelongUserList(acSltGp, usrSids);
            UserBiz userBiz = new UserBiz();
            List<CmnUsrmInfModel> usList
                = userBiz.getBelongUserList(con, acSltGp, usrSids);

            LabelValueBean lavelBean = null;
            for (int i = 0; i < usList.size(); i++) {
                CmnUsrmInfModel cuiMdl = usList.get(i);
                lavelBean = new LabelValueBean();
                lavelBean.setLabel(cuiMdl.getUsiSei() + " " + cuiMdl.getUsiMei());
                lavelBean.setValue(String.valueOf(cuiMdl.getUsrSid()));
                ret.add(lavelBean);
            }
        }

        return ret;
    }

    /**
     * アクセス権限のフルアクセス用一覧を取得する
     * @param paramMdl Fil030ParamModel
     * @param con コネクション
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getAccessFullLavle(
            Fil030ParamModel paramMdl, Connection con)
    throws SQLException {

        //取得するユーザSID・グループSID
        String[] leftFull = paramMdl.getFil030SvAcFull();
        return __getAccessLavle(leftFull, con);
    }

    /**
     * アクセス権限のフルアクセス用一覧を取得する
     * @param paramMdl Fil030ParamModel
     * @param con コネクション
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getAccessReadLavle(
            Fil030ParamModel paramMdl, Connection con)
    throws SQLException {

        //取得するユーザSID・グループSID
        String[] leftRead = paramMdl.getFil030SvAcRead();
        return __getAccessLavle(leftRead, con);

    }
    /**
     * アクセス権限の一覧を取得する
     * @param left 取得するユーザSID・グループSID
     * @param con コネクション
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getAccessLavle(String[] left, Connection con)
    throws SQLException {

        ArrayList<LabelValueBean> ret = new ArrayList<LabelValueBean>();

        //
        ArrayList<Integer> grpSids = new ArrayList<Integer>();
        ArrayList<String> usrSids = new ArrayList<String>();

        //ユーザSIDとグループSIDを分離
        if (left != null) {
            for (int i = 0; i < left.length; i++) {
                String str = NullDefault.getString(left[i], "-1");
                log__.debug("str==>" + str);
                log__.debug("G.index==>" + str.indexOf("G"));
                if (str.contains(new String("G").subSequence(0, 1))) {
                    //グループ
                    grpSids.add(new Integer(str.substring(1, str.length())));
                } else {
                    //ユーザ
                    usrSids.add(str);
                }
            }
        }
        //グループ情報取得
        UsidSelectGrpNameDao gdao = new UsidSelectGrpNameDao(con);
        ArrayList<GroupModel> glist = gdao.selectGroupNmListOrderbyClass(grpSids);
        LabelValueBean lavelBean = null;
        for (GroupModel gmodel : glist) {
            lavelBean = new LabelValueBean();
            lavelBean.setLabel(gmodel.getGroupName());
            lavelBean.setValue("G" + String.valueOf(gmodel.getGroupSid()));
            ret.add(lavelBean);
        }

        //ユーザ情報取得
//        UserSearchDao usDao = new UserSearchDao(con);
//        ArrayList<BaseUserModel> ulist = usDao.getSelectedUserList(
//                (String[]) usrSids.toArray(new String[usrSids.size()]));
        UserBiz userBiz = new UserBiz();
        ArrayList<BaseUserModel> ulist
                = userBiz.getBaseUserList(con,
                                        (String[]) usrSids.toArray(new String[usrSids.size()]));
        for (BaseUserModel umodel : ulist) {
            lavelBean = new LabelValueBean();
            lavelBean.setLabel(umodel.getUsisei() + " " + umodel.getUsimei());
            lavelBean.setValue(String.valueOf(umodel.getUsrsid()));
            ret.add(lavelBean);
        }

        return ret;
    }
    /**
     * 管理者権限の候補用一覧を取得する
     * @param paramMdl Fil030ParamModel
     * @param con コネクション
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getAdmRightLavle(Fil030ParamModel paramMdl, Connection con)
    throws SQLException {

        ArrayList<LabelValueBean> ret = new ArrayList<LabelValueBean>();
        //アクセス権限 グループコンボ選択値
        int admSltGp = NullDefault.getInt(
                paramMdl.getFil030AdmSltGroup(), -1);
        //除外するユーザSID
        String[] leftAdm = paramMdl.getFil030SvAdm();


        if (admSltGp > -1) {

            //除外するユーザSID
            ArrayList<Integer> usrSids = new ArrayList<Integer>();
            if (leftAdm != null) {
                for (int i = 0; i < leftAdm.length; i++) {
                    usrSids.add(new Integer(NullDefault.getInt(leftAdm[i], -1)));
                }
            }
//            UserSearchDao usDao = new UserSearchDao(con);
//            List<CmnUsrmInfModel>usList = usDao.getBelongUserList(admSltGp, usrSids);
            UserBiz userBiz = new UserBiz();
            List<CmnUsrmInfModel> usList
                = userBiz.getBelongUserList(con, admSltGp, usrSids);

            LabelValueBean lavelBean = null;
            for (int i = 0; i < usList.size(); i++) {
                CmnUsrmInfModel cuiMdl = usList.get(i);
                lavelBean = new LabelValueBean();
                lavelBean.setLabel(cuiMdl.getUsiSei() + " " + cuiMdl.getUsiMei());
                lavelBean.setValue(String.valueOf(cuiMdl.getUsrSid()));
                ret.add(lavelBean);
            }
        }

        return ret;
    }
    /**
     * 管理者権限の管理者一覧を取得する
     * @param paramMdl Fil030ParamModel
     * @param con コネクション
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getAdmLavle(Fil030ParamModel paramMdl, Connection con)
    throws SQLException {

        //取得するユーザSID・グループSID
        String[] leftAdm = paramMdl.getFil030SvAdm();
        return __getAccessLavle(leftAdm, con);
    }
    /**
     * 容量警告コンボのラベルを設定する
     * @param paramMdl Fil030ParamModel
     */
    private void __setCapaWarnLavle(Fil030ParamModel paramMdl) {

        //警告コンボ
        ArrayList<LabelValueBean> capaWarnLavel = new ArrayList<LabelValueBean>();

        GsMessage gsMsg = new GsMessage(reqMdl__);
        String textSiteiNasi = gsMsg.getMessage("cmn.specified.no");

        LabelValueBean lavelBean = null;
        lavelBean = new LabelValueBean();
        lavelBean.setLabel(textSiteiNasi);
        lavelBean.setValue("0");
        capaWarnLavel.add(lavelBean);
        for (int i = 50; i < 100; i += 5) {
            lavelBean = new LabelValueBean();
            lavelBean.setLabel(i + "%");
            lavelBean.setValue(String.valueOf(i));
            capaWarnLavel.add(lavelBean);
        }
        paramMdl.setFil030CapaWarnLavel(capaWarnLavel);
    }

    /**
     * 固定コンボのラベルを設定する
     * @param paramMdl Fil030ParamModel
     */
    private void __setVersionLavle(Fil030ParamModel paramMdl) {

        //バージョンコンボ
        ArrayList<LabelValueBean> versionLavel = new ArrayList<LabelValueBean>();

        GsMessage gsMsg = new GsMessage(reqMdl__);

        LabelValueBean lavelBean = null;
        for (int i = 0; i < 11; i++) {
            lavelBean = new LabelValueBean();
            lavelBean.setLabel(gsMsg.getMessage("fil.generations",
                    new String[] {String.valueOf(i)}));
            lavelBean.setValue(String.valueOf(i));
            versionLavel.add(lavelBean);
        }
        paramMdl.setFil030VerKbnLavel(versionLavel);
    }

    /**
     * <br>管理者設定のバージョン管理区分を取得します。
     * @param paramMdl Fil030ParamModel
     * @param con コネクション
     * @return verKbn バージョン管理区分（管理者設定）
     * @throws SQLException SQL実行時例外
     */
    private int __setVerKbnAdmin(Fil030ParamModel paramMdl, Connection con)
    throws SQLException {

        FileAconfDao aconfDao = new FileAconfDao(con);
        FileAconfModel aconfMdl = aconfDao.select();

        if (aconfMdl == null) {
            return GSConstFile.VERSION_KBN_ON;
        }
        paramMdl.setAdmVerKbn(aconfMdl.getFacVerKbn());

        return aconfMdl.getFacVerKbn();
    }

    /**
     * <br>[機  能] 添付ファイルの削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param binList 削除するBIN_SID
     * @param appRootPath アプリケーションルートパス
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException ファイルアクセス時例外
     */
    public void doDeleteFile(ArrayList<String> binList, String appRootPath, Connection con)
    throws SQLException, IOToolsException {

        if (binList == null || binList.size() < 1) {
            return;
        }
        CmnBinfDao binDao = new CmnBinfDao(con);
        CommonBiz cmnBiz = new CommonBiz();

        //バイナリ情報を取得する。
        List<CmnBinfModel> cmnBinList = binDao.select(binList.toArray(new String[binList.size()]));

        for (CmnBinfModel binMdl : cmnBinList) {

            //ファイルシステムより添付ファイルを削除する。
            cmnBiz.deleteFile(binMdl, appRootPath);

        }
        cmnBiz.deleteBinInf(con, binList.toArray(new String[binList.size()]));
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

}
