package jp.groupsession.v2.usr.usr032kn;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilKana;
import jp.co.sjts.util.csv.AbstractCsvRecordReader;
import jp.co.sjts.util.csv.CsvTokenizer;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.ObjectFile;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.biz.PosBiz;
import jp.groupsession.v2.cmn.cmn110.Cmn110FileModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.base.CmnBelongmDao;
import jp.groupsession.v2.cmn.dao.base.CmnGroupClassDao;
import jp.groupsession.v2.cmn.dao.base.CmnGroupmDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmInfDao;
import jp.groupsession.v2.cmn.login.ILogin;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBelongmModel;
import jp.groupsession.v2.cmn.model.base.CmnGroupClassModel;
import jp.groupsession.v2.cmn.model.base.CmnGroupmModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmModel;
import jp.groupsession.v2.cmn.model.base.SaibanModel;
import jp.groupsession.v2.usr.GSConstUser;
import jp.groupsession.v2.usr.GSPassword;
import jp.groupsession.v2.usr.IUserGroupListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] ユーザインポート CSVファイル取り込み処理
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class UserCsvImport extends AbstractCsvRecordReader {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(UserCsvImport.class);

    /** 実行モード*/
    private int mode__;
    /** 取り込みモード*/
    private int torikomiMode__;
    /** コネクション */
    private Connection con__ = null;
    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;
    /** 採番コントローラ */
    private MlCountMtController cntCon__ = null;
    /** 所属グループ */
    private List<CmnGroupmModel> glist__ = null;
    /** デフォルトグループ */
    private CmnGroupmModel dfGroup__ = null;
    /** セッションユーザ */
    private int sessionUser__;
    /** システム日付 */
    private UDate sysUd__;
    /** 取込みユーザ情報リスト*/
    private ArrayList<CmnUsrmInfModel> userInfList__ = null;
    /** 未登録役職 */
    private HashMap<String, String> posMap__;
    /** ユーザリスナー */
    private IUserGroupListener[] lis__ = null;
    /** ログイン処理実行Biz */
    private ILogin loginBiz__ = null;
    /** 既存のユーザ情報更新フラグ */
    private int updateFlg__ = 0;
    /** パスワード更新フラグ */
    private int updatePassFlg__;
    /** グループ作成フラグ */
    private int insertFlg__ = 0;
    /** 初回ログイン時、パスワード変更区分 */
    private int pswdKbn__ = GSConstUser.PSWD_UPDATE_OFF;
    /** グループID,名称重複チェック用MAP */
    private HashMap<String, String> groupIdnmMap__;
    /** グループId格納変数） */
    private String groupId__;
    /** グループ名称格納変数 */
    private String groupNm__;

    /**
     * @return userInfList を戻します。
     */
    public ArrayList<CmnUsrmInfModel> getUserInfList() {
        return userInfList__;
    }
    /**
     * @param userInfList 設定する userInfList。
     */
    public void setUserInfList(ArrayList<CmnUsrmInfModel> userInfList) {
        userInfList__ = userInfList;
    }
    /**
     * @return mode を戻します。
     */
    public int getMode() {
        return mode__;
    }
    /**
     * @param mode 設定する mode。
     */
    public void setMode(int mode) {
        mode__ = mode;
    }
    /**
     * @return mode を戻します。
     */
    public int getTmode() {
        return torikomiMode__;
    }
    /**
     * @param torikomiMode 設定する mode。
     */
    public void setTmode(int torikomiMode) {
        torikomiMode__ = torikomiMode;
    }
    /**
     * @return cntCon を戻します。
     */
    public MlCountMtController getCntCon() {
        return cntCon__;
    }
    /**
     * @param cntCon 設定する cntCon。
     */
    public void setCntCon(MlCountMtController cntCon) {
        cntCon__ = cntCon;
    }
    /**
     * @return dfGroup を戻します。
     */
    public CmnGroupmModel getDfGroup() {
        return dfGroup__;
    }
    /**
     * @param dfGroup 設定する dfGroup。
     */
    public void setDfGroup(CmnGroupmModel dfGroup) {
        dfGroup__ = dfGroup;
    }
    /**
     * @return glist を戻します。
     */
    public List<CmnGroupmModel> getGlist() {
        return glist__;
    }
    /**
     * @param glist 設定する glist。
     */
    public void setGlist(List<CmnGroupmModel> glist) {
        glist__ = glist;
    }
    /**
     * @return con__ を戻す。
     */
    public Connection getCon() {
        return con__;
    }
    /**
     * @param con con__ をセット。
     */
    public void setCon(Connection con) {
        con__ = con;
    }
    /**
     * <p>reqMdl を取得します。
     * @return reqMdl
     */
    public RequestModel getReqMdl() {
        return reqMdl__;
    }
    /**
     * <p>reqMdl をセットします。
     * @param reqMdl reqMdl
     */
    public void setReqMdl(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }
    /**
     * @return sessionUser__ を戻す。
     */
    public int getSessionUser() {
        return sessionUser__;
    }
    /**
     * @param sessionUser sessionUser__ をセット。
     */
    public void setSessionUser(int sessionUser) {
        sessionUser__ = sessionUser;
    }
    /**
     * @return sysUd__ を戻す。
     */
    public UDate getSysUd() {
        return sysUd__;
    }
    /**
     * @param sysUd sysUd__ をセット。
     */
    public void setSysUd(UDate sysUd) {
        sysUd__ = sysUd;
    }
    /**
     * <p>posMap を取得します。
     * @return posMap
     */
    public HashMap<String, String> getPosMap() {
        return posMap__;
    }
    /**
     * <p>posMap をセットします。
     * @param posMap posMap
     */
    public void setPosMap(HashMap<String, String> posMap) {
        posMap__ = posMap;
    }
    /**
     * <p>groupIdMap を取得します。
     * @return groupIdMap
     */
    public HashMap<String, String> getGroupIdnmMap() {
        return groupIdnmMap__;
    }

    /**
     * <p>groupIdMap をセットします。
     * @param groupIdnmMap groupIdnmMap
     */
    public void setGroupIdnmMap(HashMap<String, String> groupIdnmMap) {
        groupIdnmMap__ = groupIdnmMap;
    }

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param cntCon 採番コントローラ
     * @param con コネクション
     * @param model UserCsvImportModel
     * @param torikomiMode 取り込みモード
     */
    public UserCsvImport(RequestModel reqMdl,
                          MlCountMtController cntCon,
                          Connection con,
                          UserCsvImportModel model,
                          int torikomiMode) {

        setReqMdl(reqMdl);
        setCntCon(cntCon);
        setGlist(model.getGlist());
        setDfGroup(model.getDfGroup());
        setSessionUser(model.getUserSid());
        setMode(model.getMode());
        setTmode(torikomiMode);
        setCon(con);
        setSysUd(new UDate());
        setUserInfList(new ArrayList<CmnUsrmInfModel>());
        setPosMap(new HashMap<String, String>());
        setGroupIdnmMap(new HashMap<String, String>());
        setUpdateFlg(model.getUpdateFlg());
        setUpdatePassFlg(model.getUpdatePassFlg());
        setInsertFlg(model.getInsertFlg());
        setPswdKbn(model.getPswdKbn());
    }

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param mode 実行モード 0=インポート 1=インポート情報を取得
     * @param con コネクション
     * @param torikomiMode 取り込みモード
     */
    public UserCsvImport(RequestModel reqMdl,
                          int mode,
                          int torikomiMode,
                          Connection con) {

        setReqMdl(reqMdl);
        setMode(mode);
        setCon(con);
        setTmode(torikomiMode);
        setSysUd(new UDate());
        setUserInfList(new ArrayList<CmnUsrmInfModel>());
        setPosMap(new HashMap<String, String>());
        setGroupIdnmMap(new HashMap<String, String>());
    }

    /**
     * <br>[機　能] CSVファイルを取り込む
     * <br>[解　説]
     * <br>[備  考]
     * @param tmpFileDir テンポラリディレクトリ
     * @param loginBiz ログイン処理実行Biz
     * @return userInfList__
     * @throws  Exception   実行時例外
     */
    public ArrayList<CmnUsrmInfModel> importCsv(String tmpFileDir, ILogin loginBiz)
        throws Exception {

        setLoginBiz(loginBiz);

        //テンポラリディレクトリにあるファイル名称を取得
        String saveFileName = "";
        List<String> fileList = IOTools.getFileNames(tmpFileDir);
        for (int i = 0; i < fileList.size(); i++) {
            //ファイル名を取得
            String fileName = fileList.get(i);
            if (!fileName.endsWith(GSConstCommon.ENDSTR_OBJFILE)) {
                continue;
            }

            //オブジェクトファイルを取得
            ObjectFile objFile = new ObjectFile(tmpFileDir, fileName);
            Object fObj = objFile.load();
            if (fObj == null) {
                continue;
            }
            Cmn110FileModel fMdl = (Cmn110FileModel) fObj;
            saveFileName = fMdl.getSaveFileName();
        }
        String csvFile = tmpFileDir + saveFileName;

        //ファイル取込
        readFile(new File(csvFile), Encoding.WINDOWS_31J);

        //登録・登録予定内容を設定
        return userInfList__;
    }

    /**
     * <br>[機  能] csvファイル一行の処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param num 行番号
     * @param lineStr 行データ
     * @throws Exception csv読込時例外
     * @see jp.co.sjts.util.csv.AbstractCsvRecordReader#processedLine(long, java.lang.String)
     */
    protected void processedLine(long num, String lineStr) throws Exception {

        //ヘッダ文字列読み飛ばし
        if (num == 1) {
            return;
        }

        //処理モード
        if (mode__ == 0) {
            __import(num, lineStr);
        } else {
            //登録処理無し
            __getData(num, lineStr);
        }
    }

    /**
     * <br>[機  能] csvファイル一行の処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param num 行番号
     * @param lineStr 行データ
     * @throws Exception csv読込時例外
     */
    private void __import(long num, String lineStr) throws Exception {

        int j = 0;
        String buff;
        CsvTokenizer stringTokenizer = new CsvTokenizer(lineStr, ",");

        CmnGroupmModel grpmModel = new CmnGroupmModel();
        CmnUsrmModel umodel = new CmnUsrmModel();
        CmnUsrmDao udao = new CmnUsrmDao(con__);
        CmnUsrmInfModel uifmodel = new CmnUsrmInfModel();
        CmnUsrmInfDao uifdao = new CmnUsrmInfDao(con__);
        CmnGroupmDao grpDao = new CmnGroupmDao(con__);
        boolean existorikomiFlg = false;

        //取り込みモード
        Boolean torikomiFlg = null;
        if (torikomiMode__ == GSConstUser.MODE_NORMAL) {
            //通常モード
            torikomiFlg = true;
        } else {
            //グループ一括モード
            torikomiFlg = false;
        }

        while (stringTokenizer.hasMoreTokens()) {
            j++;
            buff = stringTokenizer.nextToken();

            if (torikomiFlg == true) {
                //通常モード
                //ユーザID
                if (j == 1) {
                    if (updateFlg__ == GSConstUser.IMPORT_MODE_UPDATE) {
                        existorikomiFlg = udao.existLoginidEdit(-1, buff);
                    }
                    umodel.setUsrLgid(buff);
                }
                if (j == 2) {
                    String password = getLoginBiz().formatPassword(buff);
                    umodel.setUsrPswd(GSPassword.getEncryPassword(password)); //暗号化
                }
                //ユーザ情報をセットする。
                __setUsrInfModel(j, uifmodel, buff);
            } else {
                //グループ一括モード
                //ユーザID
                if (j == 3) {
                    if (updateFlg__ == GSConstUser.IMPORT_MODE_UPDATE) {
                        existorikomiFlg = udao.existLoginidEdit(-1, buff);
                    }
                    umodel.setUsrLgid(buff);
                }
                if (j == 4) {
                    String password = getLoginBiz().formatPassword(buff);
                    umodel.setUsrPswd(GSPassword.getEncryPassword(password)); //暗号化
                }
                //ユーザ情報をセットする。
                __setUsrInfModel2(j, uifmodel, grpmModel, buff, grpDao);
            }
        }
        int usid = 0;

        //ユーザマスタ
        umodel.setUsrJkbn(GSConstUser.USER_JTKBN_ACTIVE);
        umodel.setUsrAuid(sessionUser__);
        umodel.setUsrAdate(sysUd__);
        umodel.setUsrEuid(sessionUser__);
        umodel.setUsrEdate(sysUd__);
        umodel.setUsrPswdEdate(sysUd__);
        umodel.setUsrPswdKbn(pswdKbn__);

        //ユーザ情報
        uifmodel.setUsiEuid(sessionUser__);
        uifmodel.setUsiEdate(sysUd__);
        uifmodel.setUsiLtlgin(sysUd__);
        uifmodel.setUsiAuid(sessionUser__);
        uifmodel.setUsiAdate(sysUd__);

        if (updateFlg__ == GSConstUser.IMPORT_MODE_UPDATE && existorikomiFlg) {
            CmnUsrmModel usrmModel = udao.getUserSid(umodel.getUsrLgid());
            if (usrmModel != null) {
                usid = usrmModel.getUsrSid();
                umodel.setUsrSid(usid);
                uifmodel.setUsrSid(usid);
                //ユーザ登録処理
                udao.updateUserData(umodel, updatePassFlg__);
                //バイナリSID、写真公開フラグは更新対象から除外する
                uifdao.updateCmnUserInfNoBinSid(uifmodel);
            }

        } else {
            usid = (int) cntCon__.getSaibanNumber(SaibanModel.SBNSID_USER,
                    SaibanModel.SBNSID_SUB_USER,
                    sessionUser__);
            umodel.setUsrSid(usid);
            uifmodel.setUsrSid(usid);

            //ユーザ登録処理
            udao.insert(umodel);
            uifdao.insert(uifmodel);
        }


        //ユーザ所属
        CmnBelongmDao bdao = new CmnBelongmDao(con__);
        List<Integer> pastGsidList = new ArrayList<Integer>();

        if (torikomiFlg) {
            //通常モード
            //デフォルトグループ
            int dgsid = dfGroup__.getGrpSid();
            log__.debug(">>>デフォルトグループSID: " + dgsid);
            //所属グループ
            int gsid = -1;

            if (updateFlg__ == GSConstUser.IMPORT_MODE_UPDATE && existorikomiFlg) {
                //変更前の所属グループを取得する
                pastGsidList = bdao.selectUserBelongGroupSid(usid);
                //所属グループ情報を削除する。
                bdao.deleteUserBelongGroup(usid);
            }

            for (int i = 0; i < glist__.size(); i++) {
                gsid = glist__.get(i).getGrpSid();
                CmnBelongmModel bmodel = __createCmnBelongmModel(dgsid,
                                                                  gsid,
                                                                  usid,
                                                         sessionUser__,
                                                               sysUd__);
                bdao.insert(bmodel);
            }

        } else {
            int gsid = 0;
            int dgsid = 0;
        //グループ一括モード
            //グループが存在する場合
            //groupsid取得
            CmnGroupmModel groupModel = grpDao.getGroupInf(grpmModel.getGrpId());

            if (groupModel != null) {
                gsid = groupModel.getGrpSid();
                dgsid = gsid;

                if (updateFlg__ == GSConstUser.IMPORT_MODE_UPDATE && existorikomiFlg) {
                    //変更前の所属グループを取得する
                    pastGsidList = bdao.selectUserBelongGroupSid(usid);
                    //所属グループ情報を削除する。
                    bdao.deleteUserBelongGroup(usid);
                }
                glist__ = new ArrayList<CmnGroupmModel>();
                glist__.add(groupModel);

                for (int i = 0; i < glist__.size(); i++) {
                    gsid = glist__.get(i).getGrpSid();
                    CmnBelongmModel bmodel = __createCmnBelongmModel(dgsid, gsid,
                            usid, sessionUser__, sysUd__);
                    bdao.insert(bmodel);
                }
            } else {
                CmnGroupmModel groupModel2 = new CmnGroupmModel();

                //グループが存在しない場合は作成してから登録
                gsid = (int) cntCon__.getSaibanNumber(SaibanModel.SBNSID_USER,
                        SaibanModel.SBNSID_SUB_GROUP,
                        sessionUser__);

                //グループ登録処理
                groupModel2.setGrpSid(gsid);
                dgsid = gsid;
                grpmModel.setGrpSid(gsid);
                grpmModel.setGrpAuid(sessionUser__);
                grpmModel.setGrpAdate(sysUd__);
                grpmModel.setGrpEuid(sessionUser__);
                grpmModel.setGrpEdate(sysUd__);
                grpDao.insert(grpmModel);

                //グループ階層情報を登録する。
                CmnGroupClassDao gclsDao = new CmnGroupClassDao(con__);
                CmnGroupClassModel classModel = new CmnGroupClassModel();
                classModel.addUnder(gsid);
                classModel.setGclAuid(sessionUser__);
                classModel.setGclAdate(sysUd__);
                classModel.setGclEuid(sessionUser__);
                classModel.setGclEdate(sysUd__);
                gclsDao.insert(classModel);

                glist__ = new ArrayList<CmnGroupmModel>();
                glist__.add(groupModel2);

                for (int i = 0; i < glist__.size(); i++) {
                    gsid = glist__.get(i).getGrpSid();
                    CmnBelongmModel bmodel = __createCmnBelongmModel(dgsid, gsid,
                            usid, sessionUser__, sysUd__);
                    bdao.insert(bmodel);
                }
            }
        }

        //各プラグインリスナーを呼び出し
        if (updateFlg__ != GSConstUser.IMPORT_MODE_UPDATE || !existorikomiFlg) {
            for (int i = 0; i < lis__.length; i++) {
                lis__[i].addUser(cntCon__, con__, usid, sessionUser__, reqMdl__);
            }
        } else {
            //更新前所属グループ
            int[] pastGsids = new int[pastGsidList.size()];
            for (int gidIndex = 0; gidIndex < pastGsidList.size(); gidIndex++) {
                pastGsids[gidIndex] = pastGsidList.get(gidIndex);
            }
            //更新後所属グループ
            List<Integer> belongGsidList = bdao.selectUserBelongGroupSid(usid);
            int[] belongGsids = new int[belongGsidList.size()];
            for (int gidIndex = 0; gidIndex < belongGsidList.size(); gidIndex++) {
                belongGsids[gidIndex] = belongGsidList.get(gidIndex);
            }

            for (int i = 0; i < lis__.length; i++) {
                lis__[i].changeBelong(con__, usid, pastGsids, belongGsids, sessionUser__);
            }
        }

        userInfList__.add(uifmodel);
    }

    /**
     * <br>[機  能] csvファイル一行の処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param num 行番号
     * @param lineStr 行データ
     * @throws Exception csv読込時例外
     */
    private void __getData(long num, String lineStr) throws Exception {

        int j = 0;
        String buff;
        CsvTokenizer stringTokenizer = new CsvTokenizer(lineStr, ",");
//        boolean noInputorikomiFlg = false;
        //取り込みモード
        Boolean torikomiFlg = null;
        if (torikomiMode__ == GSConstUser.MODE_NORMAL) {
            //通常モード
            torikomiFlg = true;
        } else {
            //グループ一括モード
            torikomiFlg = false;
        }

        //ユーザ情報
        CmnUsrmInfModel uifmodel = new CmnUsrmInfModel();
        CmnGroupmModel grpmModel = new CmnGroupmModel();
        CmnGroupmDao grpDao = new CmnGroupmDao(con__);
//        CmnUsrmInfDao uifdao = new CmnUsrmInfDao(con__);
        uifmodel.setUsiEuid(sessionUser__);
        uifmodel.setUsiEdate(sysUd__);
        uifmodel.setUsiLtlgin(sysUd__);
        uifmodel.setUsiAuid(sessionUser__);
        uifmodel.setUsiAdate(sysUd__);

        while (stringTokenizer.hasMoreTokens()) {
            j++;
            buff = stringTokenizer.nextToken();

            //ユーザID
            if (j == 1) {
            }
            //パスワード
            if (j == 2) {
//                String password = buff;
            }

            //ユーザ情報をセットする
            if (torikomiFlg == true) {
                __setUsrInfModel(j, uifmodel, buff);
            } else {
                __setUsrInfModel2(j, uifmodel, grpmModel, buff, grpDao);
            }
        }
        userInfList__.add(uifmodel);
    }

    /**
     * <br>[機  能] ユーザ情報をセットする(通常モード)
     * <br>[解  説]
     * <br>[備  考]
     * @param index 項目index
     * @param uifmodel CmnUsrmInfModel
     * @param buff 読込文字列
     */
    private void __setUsrInfModel(int index, CmnUsrmInfModel uifmodel, String buff) {

        //社員・職員番号
        if (index == 3) {
            uifmodel.setUsiSyainNo(NullDefault.getStringZeroLength(buff, ""));
        }
        //姓
        if (index == 4) {
            uifmodel.setUsiSei(NullDefault.getString(buff, ""));
        }
        //名
        if (index == 5) {
            uifmodel.setUsiMei(NullDefault.getString(buff, ""));
        }
        //姓カナ
        if (index == 6) {
            uifmodel.setUsiSeiKn(NullDefault.getString(buff, ""));
            uifmodel.setUsiSini(StringUtilKana.getInitKanaChar(buff));
        }
        //名カナ
        if (index == 7) {
            uifmodel.setUsiMeiKn(NullDefault.getString(buff, ""));
        }
        //所属
        if (index == 8) {
            uifmodel.setUsiSyozoku(NullDefault.getStringZeroLength(buff, ""));
        }
        //役職
        if (index == 9) {
            uifmodel.setUsiYakusyoku(NullDefault.getStringZeroLength(buff, ""));

            try {
                PosBiz pBiz = new PosBiz();
                if (!NullDefault.getStringZeroLength(buff, "").equals("")
                && !pBiz.existPosName(con__, buff)) {
                    //役職未登録の場合、役職名をMapに追加
                    if (!posMap__.containsKey(buff)) {
                        posMap__.put(buff, buff);
                    }
                }
            } catch (SQLException e) {
            }
        }
        //ソートキー1
        if (index == 10) {
            uifmodel.setUsiSortkey1(NullDefault.getStringZeroLength(buff, ""));
        }
        //ソートキー2
        if (index == 11) {
            uifmodel.setUsiSortkey2(NullDefault.getStringZeroLength(buff, ""));
        }
        //性別
        if (index == 12) {
            uifmodel.setUsiSeibetu(NullDefault.getInt(buff, 0));
        }
        //入社年月日
        if (index == 13) {
            if (buff != null && buff.length() == 8) {
                UDate entranceDate = new UDate();
                entranceDate.setDate(buff);
                entranceDate.setHour(0);
                entranceDate.setMinute(0);
                entranceDate.setSecond(0);
                entranceDate.setMilliSecond(0);
                uifmodel.setUsiEntranceDate(entranceDate);
            }
        }
        //生年月日
        if (index == 14) {
            if (buff != null && buff.length() == 8) {
                UDate bDate = new UDate();
                bDate.setDate(buff);
                bDate.setHour(0);
                bDate.setMinute(0);
                bDate.setSecond(0);
                bDate.setMilliSecond(0);
                uifmodel.setUsiBdate(bDate);
            }
        }
        //生年月日公開フラグ
        if (index == 15) {
            uifmodel.setUsiBdateKf(NullDefault.getInt(buff, 0));
        }
        //メールアドレス１
        if (index == 16) {
            uifmodel.setUsiMail1(NullDefault.getStringZeroLength(buff, ""));
        }
        //メールアドレスコメント１
        if (index == 17) {
            uifmodel.setUsiMailCmt1(NullDefault.getStringZeroLength(buff, ""));
        }
        //メールアドレス１公開フラグ
        if (index == 18) {
            uifmodel.setUsiMail1Kf(NullDefault.getInt(buff, 0));
        }
        //メールアドレス２
        if (index == 19) {
            uifmodel.setUsiMail2(NullDefault.getStringZeroLength(buff, ""));
        }
        //メールアドレスコメント２
        if (index == 20) {
            uifmodel.setUsiMailCmt2(NullDefault.getStringZeroLength(buff, ""));
        }
        //メールアドレス２公開フラグ
        if (index == 21) {
            uifmodel.setUsiMail2Kf(NullDefault.getInt(buff, 0));
        }
        //メールアドレス３
        if (index == 22) {
            uifmodel.setUsiMail3(NullDefault.getStringZeroLength(buff, ""));
        }
        //メールアドレスコメント３
        if (index == 23) {
            uifmodel.setUsiMailCmt3(NullDefault.getStringZeroLength(buff, ""));
        }
        //メールアドレス３公開フラグ
        if (index == 24) {
            uifmodel.setUsiMail3Kf(NullDefault.getInt(buff, 0));
        }
        //郵便番号
        if (index == 25) {
            if (buff != null && buff.length() == 8) {
                uifmodel.setUsiZip1(buff.substring(0, 3));
                uifmodel.setUsiZip2(buff.substring(4, 8));
            }
        }
        //郵便番号公開フラグ
        if (index == 26) {
            uifmodel.setUsiZipKf(NullDefault.getInt(buff, 0));
        }
        //都道府県コード
        if (index == 27) {
            uifmodel.setTdfSid(NullDefault.getInt(buff,  -1));
        }
        //都道府県公開フラグ
        if (index == 28) {
            uifmodel.setUsiTdfKf(NullDefault.getInt(buff, 0));
        }
        //住所１
        if (index == 29) {
            uifmodel.setUsiAddr1(NullDefault.getStringZeroLength(buff, ""));
        }
        //住所１公開フラグ
        if (index == 30) {
            uifmodel.setUsiAddr1Kf(NullDefault.getInt(buff, 0));
        }
        //住所２
        if (index == 31) {
            uifmodel.setUsiAddr2(NullDefault.getStringZeroLength(buff, ""));
        }
        //住所２公開フラグ
        if (index == 32) {
            uifmodel.setUsiAddr2Kf(NullDefault.getInt(buff, 0));
        }
        //電話番号１
        if (index == 33) {
            uifmodel.setUsiTel1(NullDefault.getStringZeroLength(buff, ""));
        }
        //電話番号内線１
        if (index == 34) {
            uifmodel.setUsiTelNai1(NullDefault.getStringZeroLength(buff, ""));
        }
        //電話番号コメント１
        if (index == 35) {
            uifmodel.setUsiTelCmt1(NullDefault.getStringZeroLength(buff, ""));
        }
        //電話番号１公開フラグ
        if (index == 36) {
            uifmodel.setUsiTel1Kf(NullDefault.getInt(buff, 0));
        }
        //電話番号２
        if (index == 37) {
            uifmodel.setUsiTel2(NullDefault.getStringZeroLength(buff, ""));
        }
        //電話番号内線２
        if (index == 38) {
            uifmodel.setUsiTelNai2(NullDefault.getStringZeroLength(buff, ""));
        }
        //電話番号コメント２
        if (index == 39) {
            uifmodel.setUsiTelCmt2(NullDefault.getStringZeroLength(buff, ""));
        }
        //電話番号２公開フラグ
        if (index == 40) {
            uifmodel.setUsiTel2Kf(NullDefault.getInt(buff, 0));
        }
        //電話番号３
        if (index == 41) {
            uifmodel.setUsiTel3(NullDefault.getStringZeroLength(buff, ""));
        }
        //電話番号内線３
        if (index == 42) {
            uifmodel.setUsiTelNai3(NullDefault.getStringZeroLength(buff, ""));
        }
        //電話番号コメント３
        if (index == 43) {
            uifmodel.setUsiTelCmt3(NullDefault.getStringZeroLength(buff, ""));
        }
        //電話番号３公開フラグ
        if (index == 44) {
            uifmodel.setUsiTel3Kf(NullDefault.getInt(buff, 0));
        }
        //FAX１
        if (index == 45) {
            uifmodel.setUsiFax1(NullDefault.getStringZeroLength(buff, ""));
        }
        //ＦＡＸコメント１
        if (index == 46) {
            uifmodel.setUsiFaxCmt1(NullDefault.getStringZeroLength(buff, ""));
        }
        //FAX１公開フラグ
        if (index == 47) {
            uifmodel.setUsiFax1Kf(NullDefault.getInt(buff, 0));
        }
        //FAX２
        if (index == 48) {
            uifmodel.setUsiFax2(NullDefault.getStringZeroLength(buff, ""));
        }
        //ＦＡＸコメント２
        if (index == 49) {
            uifmodel.setUsiFaxCmt2(NullDefault.getStringZeroLength(buff, ""));
        }
        //FAX２公開フラグ
        if (index == 50) {
            uifmodel.setUsiFax2Kf(NullDefault.getInt(buff, 0));
        }
        //FAX３
        if (index == 51) {
            uifmodel.setUsiFax3(NullDefault.getStringZeroLength(buff, ""));
        }
        //ＦＡＸコメント３
        if (index == 52) {
            uifmodel.setUsiFaxCmt3(NullDefault.getStringZeroLength(buff, ""));
        }
        //FAX３公開フラグ
        if (index == 53) {
            uifmodel.setUsiFax3Kf(NullDefault.getInt(buff, 0));
        }
        //備考
        if (index == 54) {
            uifmodel.setUsiBiko(NullDefault.getStringZeroLength(buff, ""));
        }
    }

    /**
     * <br>[機  能] ユーザ情報をセットする(グループ一括モード)
     * <br>[解  説]
     * <br>[備  考]
     * @param index 項目index
     * @param uifmodel CmnUsrmInfModel
     * @param buff 読込文字列
     * @param grpmModel CmnGroupmModel
     * @param grpDao CmnGroupmDao
     * @throws SQLException SQL実行時例外
     */
    private void __setUsrInfModel2(int index,
                    CmnUsrmInfModel uifmodel,
                    CmnGroupmModel grpmModel,
                                 String buff,
                         CmnGroupmDao grpDao) throws SQLException {

        //グループID
        if (index == 1) {
            String groupNm = null;
            uifmodel.setUsigpSid(NullDefault.getString(buff, ""));
            grpmModel.setGrpId(NullDefault.getString(buff, ""));
            boolean ret = grpDao.existGroupEdit(0, buff);
            if (ret) {
                groupNm = grpDao.getGrpNm(buff);
                groupIdnmMap__.put(buff, groupNm);
            }
            groupId__ = buff;
        }
        //グループ名
        if (index == 2) {
            // グループIDが同じで名称が異なるデータがあった場合は、それより前のデータの名称に変更する
            if (groupIdnmMap__.containsKey(groupId__)) {
                groupNm__ = groupIdnmMap__.get(groupId__);
                if (!groupNm__.equals(buff)) {
                    buff = groupNm__;
                    uifmodel.setUsiHenkou(GSConstUser.GROUP_NAME_CHANGE);
                }
            }
            groupIdnmMap__.put(groupId__, buff);
            uifmodel.setUsigpNm(NullDefault.getString(buff, ""));
            grpmModel.setGrpName(NullDefault.getString(buff, ""));
        }
        //社員・職員番号
        if (index == 5) {
            uifmodel.setUsiSyainNo(NullDefault.getStringZeroLength(buff, ""));
        }
        //姓
        if (index == 6) {
            uifmodel.setUsiSei(NullDefault.getString(buff, ""));
        }
        //名
        if (index == 7) {
            uifmodel.setUsiMei(NullDefault.getString(buff, ""));
        }
        //姓カナ
        if (index == 8) {
            uifmodel.setUsiSeiKn(NullDefault.getString(buff, ""));
            uifmodel.setUsiSini(StringUtilKana.getInitKanaChar(buff));
        }
        //名カナ
        if (index == 9) {
            uifmodel.setUsiMeiKn(NullDefault.getString(buff, ""));
        }
        //所属
        if (index == 10) {
            uifmodel.setUsiSyozoku(NullDefault.getStringZeroLength(buff, ""));
        }
        //役職
        if (index == 11) {
            uifmodel.setUsiYakusyoku(NullDefault.getStringZeroLength(buff, ""));

            try {
                PosBiz pBiz = new PosBiz();
                if (!NullDefault.getStringZeroLength(buff, "").equals("")
                && !pBiz.existPosName(con__, buff)) {
                    //役職未登録の場合、役職名をMapに追加
                    if (!posMap__.containsKey(buff)) {
                        posMap__.put(buff, buff);
                    }
                }
            } catch (SQLException e) {
            }
        }
      //ソートキー1
        if (index == 12) {
            uifmodel.setUsiSortkey1(NullDefault.getStringZeroLength(buff, ""));
        }
        //ソートキー2
        if (index == 13) {
            uifmodel.setUsiSortkey2(NullDefault.getStringZeroLength(buff, ""));
        }
        //性別
        if (index == 14) {
            uifmodel.setUsiSeibetu(NullDefault.getInt(buff, 0));
        }
        //入社年月日
        if (index == 15) {
            if (buff != null && buff.length() == 8) {
                UDate entranceDate = new UDate();
                entranceDate.setDate(buff);
                entranceDate.setHour(0);
                entranceDate.setMinute(0);
                entranceDate.setSecond(0);
                entranceDate.setMilliSecond(0);
                uifmodel.setUsiEntranceDate(entranceDate);
            }
        }
        //生年月日
        if (index == 16) {
            if (buff != null && buff.length() == 8) {
                UDate bDate = new UDate();
                bDate.setDate(buff);
                bDate.setHour(0);
                bDate.setMinute(0);
                bDate.setSecond(0);
                bDate.setMilliSecond(0);
                uifmodel.setUsiBdate(bDate);
            }
        }
        //生年月日公開フラグ
        if (index == 17) {
            uifmodel.setUsiBdateKf(NullDefault.getInt(buff, 0));
        }
        //メールアドレス１
        if (index == 18) {
            uifmodel.setUsiMail1(NullDefault.getStringZeroLength(buff, ""));
        }
        //メールアドレスコメント１
        if (index == 19) {
            uifmodel.setUsiMailCmt1(NullDefault.getStringZeroLength(buff, ""));
        }
        //メールアドレス１公開フラグ
        if (index == 20) {
            uifmodel.setUsiMail1Kf(NullDefault.getInt(buff, 0));
        }
        //メールアドレス２
        if (index == 21) {
            uifmodel.setUsiMail2(NullDefault.getStringZeroLength(buff, ""));
        }
        //メールアドレスコメント２
        if (index == 22) {
            uifmodel.setUsiMailCmt2(NullDefault.getStringZeroLength(buff, ""));
        }
        //メールアドレス２公開フラグ
        if (index == 23) {
            uifmodel.setUsiMail2Kf(NullDefault.getInt(buff, 0));
        }
        //メールアドレス３
        if (index == 24) {
            uifmodel.setUsiMail3(NullDefault.getStringZeroLength(buff, ""));
        }
        //メールアドレスコメント３
        if (index == 25) {
            uifmodel.setUsiMailCmt3(NullDefault.getStringZeroLength(buff, ""));
        }
        //メールアドレス３公開フラグ
        if (index == 26) {
            uifmodel.setUsiMail3Kf(NullDefault.getInt(buff, 0));
        }
        //郵便番号
        if (index == 27) {
            if (buff != null && buff.length() == 8) {
                uifmodel.setUsiZip1(buff.substring(0, 3));
                uifmodel.setUsiZip2(buff.substring(4, 8));
            }
        }
        //郵便番号公開フラグ
        if (index == 28) {
            uifmodel.setUsiZipKf(NullDefault.getInt(buff, 0));
        }
        //都道府県コード
        if (index == 29) {
            uifmodel.setTdfSid(NullDefault.getInt(buff,  -1));
        }
        //都道府県公開フラグ
        if (index == 30) {
            uifmodel.setUsiTdfKf(NullDefault.getInt(buff, 0));
        }
        //住所１
        if (index == 31) {
            uifmodel.setUsiAddr1(NullDefault.getStringZeroLength(buff, ""));
        }
        //住所１公開フラグ
        if (index == 32) {
            uifmodel.setUsiAddr1Kf(NullDefault.getInt(buff, 0));
        }
        //住所２
        if (index == 33) {
            uifmodel.setUsiAddr2(NullDefault.getStringZeroLength(buff, ""));
        }
        //住所２公開フラグ
        if (index == 34) {
            uifmodel.setUsiAddr2Kf(NullDefault.getInt(buff, 0));
        }
        //電話番号１
        if (index == 35) {
            uifmodel.setUsiTel1(NullDefault.getStringZeroLength(buff, ""));
        }
        //電話番号内線１
        if (index == 36) {
            uifmodel.setUsiTelNai1(NullDefault.getStringZeroLength(buff, ""));
        }
        //電話番号コメント１
        if (index == 37) {
            uifmodel.setUsiTelCmt1(NullDefault.getStringZeroLength(buff, ""));
        }
        //電話番号１公開フラグ
        if (index == 38) {
            uifmodel.setUsiTel1Kf(NullDefault.getInt(buff, 0));
        }
        //電話番号２
        if (index == 39) {
            uifmodel.setUsiTel2(NullDefault.getStringZeroLength(buff, ""));
        }
        //電話番号内線２
        if (index == 40) {
            uifmodel.setUsiTelNai2(NullDefault.getStringZeroLength(buff, ""));
        }
        //電話番号コメント２
        if (index == 41) {
            uifmodel.setUsiTelCmt2(NullDefault.getStringZeroLength(buff, ""));
        }
        //電話番号２公開フラグ
        if (index == 42) {
            uifmodel.setUsiTel2Kf(NullDefault.getInt(buff, 0));
        }
        //電話番号３
        if (index == 43) {
            uifmodel.setUsiTel3(NullDefault.getStringZeroLength(buff, ""));
        }
        //電話番号内線３
        if (index == 44) {
            uifmodel.setUsiTelNai3(NullDefault.getStringZeroLength(buff, ""));
        }
        //電話番号コメント３
        if (index == 45) {
            uifmodel.setUsiTelCmt3(NullDefault.getStringZeroLength(buff, ""));
        }
        //電話番号３公開フラグ
        if (index == 46) {
            uifmodel.setUsiTel3Kf(NullDefault.getInt(buff, 0));
        }
        //FAX１
        if (index == 47) {
            uifmodel.setUsiFax1(NullDefault.getStringZeroLength(buff, ""));
        }
        //ＦＡＸコメント１
        if (index == 48) {
            uifmodel.setUsiFaxCmt1(NullDefault.getStringZeroLength(buff, ""));
        }
        //FAX１公開フラグ
        if (index == 49) {
            uifmodel.setUsiFax1Kf(NullDefault.getInt(buff, 0));
        }
        //FAX２
        if (index == 50) {
            uifmodel.setUsiFax2(NullDefault.getStringZeroLength(buff, ""));
        }
        //ＦＡＸコメント２
        if (index == 51) {
            uifmodel.setUsiFaxCmt2(NullDefault.getStringZeroLength(buff, ""));
        }
        //FAX２公開フラグ
        if (index == 52) {
            uifmodel.setUsiFax2Kf(NullDefault.getInt(buff, 0));
        }
        //FAX３
        if (index == 53) {
            uifmodel.setUsiFax3(NullDefault.getStringZeroLength(buff, ""));
        }
        //ＦＡＸコメント３
        if (index == 54) {
            uifmodel.setUsiFaxCmt3(NullDefault.getStringZeroLength(buff, ""));
        }
        //FAX３公開フラグ
        if (index == 55) {
            uifmodel.setUsiFax3Kf(NullDefault.getInt(buff, 0));
        }
        //備考
        if (index == 56) {
            uifmodel.setUsiBiko(NullDefault.getStringZeroLength(buff, ""));
        }
    }

    /**
     * ユーザ所属マスタの項目の共通セットを行います
     * @param dgSid デフォルトグループSID
     * @param gSid グループSID
     * @param uSid ユーザSID
     * @param sessionUser セッションユーザID
     * @param now タイムスタンプ
     * @return CmnBelongmModel
     */
    private CmnBelongmModel __createCmnBelongmModel(int dgSid,
                                                     int gSid,
                                                     int uSid,
                                                     int sessionUser,
                                                     UDate now) {

        CmnBelongmModel bmodel = new CmnBelongmModel();

        bmodel.setGrpSid(gSid);
        bmodel.setUsrSid(uSid);
        //システム項目
        bmodel.setBegAuid(sessionUser);
        bmodel.setBegAdate(now);
        bmodel.setBegEuid(sessionUser);
        bmodel.setBegEdate(now);
        if (gSid == dgSid) {
            log__.debug(">>>デフォルトグループ");
            bmodel.setBegDefgrp(CmnBelongmModel.DEFGRP_FLG_DEFAULT);
        } else {
            log__.debug(">>>所属グループ");
            bmodel.setBegDefgrp(CmnBelongmModel.DEFGRP_FLG_NORMAL);
        }

        return bmodel;
    }

    /**
     * <br>[機  能]ユーザリスナーを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @return ユーザリスナー
     */
    public IUserGroupListener[] getLis() {
        return lis__;
    }

    /**
     * <br>[機  能]ユーザリスナーをセットする。
     * <br>[解  説]
     * <br>[備  考]
     * @param lis ユーザリスナー
     */
    public void setLis(IUserGroupListener[] lis) {
        this.lis__ = lis;
    }
    /**
     * <p>updateFlg を取得します。
     * @return updateFlg
     */
    public int getUpdateFlg() {
        return updateFlg__;
    }
    /**
     * <p>updateFlg をセットします。
     * @param updateFlg updateFlg
     */
    public void setUpdateFlg(int updateFlg) {
        updateFlg__ = updateFlg;
    }
    /**
     * <p>insertFlg を取得します。
     * @return insertFlg
     */
    public int getInsertFlg() {
        return insertFlg__;
    }
    /**
     * <p>insertFlg をセットします。
     * @param insertFlg insertFlg
     */
    public void setInsertFlg(int insertFlg) {
        insertFlg__ = insertFlg;
    }
    /**
     * <p>pswdKbn を取得します。
     * @return pswdKbn
     */
    public int getPswdKbn() {
        return pswdKbn__;
    }
    /**
     * <p>pswdKbn をセットします。
     * @param pswdKbn pswdKbn
     */
    public void setPswdKbn(int pswdKbn) {
        pswdKbn__ = pswdKbn;
    }
    /**
     * <p>loginBiz を取得します。
     * @return loginBiz
     */
    public ILogin getLoginBiz() {
        return loginBiz__;
    }
    /**
     * <p>loginBiz をセットします。
     * @param loginBiz loginBiz
     */
    public void setLoginBiz(ILogin loginBiz) {
        loginBiz__ = loginBiz;
    }
    /**
     * <p>updatePassFlg を取得します。
     * @return updatePassFlg
     */
    public int getUpdatePassFlg() {
        return updatePassFlg__;
    }
    /**
     * <p>updatePassFlg をセットします。
     * @param updatePassFlg updatePassFlg
     */
    public void setUpdatePassFlg(int updatePassFlg) {
        updatePassFlg__ = updatePassFlg;
    }
}