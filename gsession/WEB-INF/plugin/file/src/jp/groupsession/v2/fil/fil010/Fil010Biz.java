package jp.groupsession.v2.fil.fil010;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.fil.FilCommonBiz;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.dao.FileCabinetDao;
import jp.groupsession.v2.fil.dao.FileCallConfDao;
import jp.groupsession.v2.fil.dao.FileCallDataDao;
import jp.groupsession.v2.fil.dao.FileDirectoryDao;
import jp.groupsession.v2.fil.dao.FileShortcutConfDao;
import jp.groupsession.v2.fil.dao.FileUconfDao;
import jp.groupsession.v2.fil.model.FileDirectoryModel;
import jp.groupsession.v2.fil.model.FileUconfModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * キャビネット一覧で使用するビジネスロジッククラス
 * @author JTS
 */
public class Fil010Biz {
    /** 備考改行区分 改行なし */
    public static final int FIL_CMT_BR_NO = 0;
    /** 備考改行区分 改行あり */
    public static final int FIL_CMT_BR_YES = 1;

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Fil010Biz.class);
    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <p>Set Connection
     * @param reqMdl RequestModel
     */
    public Fil010Biz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * <br>初期表示画面情報を取得します
     * @param paramMdl Fil010ParamModel
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void setInitData(Fil010ParamModel paramMdl, Connection con) throws SQLException {

        //セッション情報を取得
        HttpSession session = reqMdl__.getSession();
        BaseUserModel usModel =
            (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID
        FilCommonBiz cmnBiz = new FilCommonBiz(con, reqMdl__);

        //画面制御設定
        CommonBiz  commonBiz = new CommonBiz();
        boolean adminUser = commonBiz.isPluginAdmin(con, usModel, GSConstFile.PLUGIN_ID_FILE);

        if (adminUser) {
            paramMdl.setFil010DspAdminConfBtn(GSConstFile.DSP_KBN_ON);
            paramMdl.setFil010DspCabinetAddBtn(GSConstFile.DSP_KBN_ON);
        } else if (cmnBiz.isCanCreateCabinetUser(usModel, con)) {
            paramMdl.setFil010DspCabinetAddBtn(GSConstFile.DSP_KBN_ON);
        }

        //アクセス可能なキャビネット一覧を取得
        paramMdl.setCabinetList(getAccessCabinetList(usModel, con, true));
        //ショートカット一覧を取得
        paramMdl.setShortcutList(getShortcutInfoList(sessionUsrSid, con));
        //更新通知情報を取得
        paramMdl.setCallList(getCallInfoList(sessionUsrSid, con));

        if (paramMdl.getShortcutList().size() == 0 && paramMdl.getCallList().size() == 0) {
            paramMdl.setShortcutCallListFlg(1);
        } else {
            paramMdl.setShortcutCallListFlg(0);
        }
    }

    /**
     * ユーザを指定しアクセス可能なキャビネット一覧を取得します。
     * @param umodel セッションユーザ情報
     * @param con コネクション
     * @param exInfo true:負荷情報を設定 false:キャビネット一覧情報のみを取得
     * @return ret キャビネット一覧
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<FileCabinetDspModel> getAccessCabinetList(BaseUserModel umodel, Connection con,
                                                                boolean exInfo)
    throws SQLException {
        return getCabinetList(umodel, con, exInfo, false);
    }

    /**
     * ユーザを指定し利用可能なキャビネット一覧を取得します。
     * @param umodel セッションユーザ情報
     * @param con コネクション
     * @param exInfo true:負荷情報を設定 false:キャビネット一覧情報のみを取得
     * @param writeOnly true: 編集可能なキャビネットのみ false: 利用可能なキャビネット
     * @return ret キャビネット一覧
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<FileCabinetDspModel> getCabinetList(BaseUserModel umodel, Connection con,
                                                                boolean exInfo, boolean writeOnly)
    throws SQLException {

        int userSid = umodel.getUsrsid();
        FileCabinetDao cabDao = new FileCabinetDao(con);
        CommonBiz  commonBiz = new CommonBiz();
        boolean isAdmin = commonBiz.isPluginAdmin(con, umodel, GSConstFile.PLUGIN_ID_FILE);

        ArrayList<FileCabinetDspModel> ret = null;
        //システム管理者権限有の場合、全てのキャビネットOK
        if (isAdmin) {
            //全てのキャビネット
            ret = cabDao.getFileCabinetDspModelsAll();
        } else {
            HashMap<Integer, FileCabinetDspModel> map = new HashMap<Integer, FileCabinetDspModel>();

            //キャビネット情報のアクセス権限を制限しないキャビネット情報を取得
            ArrayList<FileCabinetDspModel> freeList = cabDao.getFreeAccessCabinet();
            __setMapForCabinetList(map, freeList);

            //セッション情報のユーザSIDがキャビネット管理者情報になっているキャビネット
            ArrayList<FileCabinetDspModel> admList = cabDao.getAdminCabinet(userSid);
            __setMapForCabinetList(map, admList);

            //ユーザSIDがキャビネットアクセス設定に登録(追加・変更・削除)
            ArrayList<FileCabinetDspModel> usrCanWriteList = cabDao.getCanAccessCabinet(
                    userSid, Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE));
            __setMapForCabinetList(map, usrCanWriteList);

            //所属しているグループSIDがキャビネットアクセス設定に登録(追加・変更・削除)
            ArrayList<FileCabinetDspModel> grpCanWriteList = cabDao.getCanAccessGpCabinet(
                    userSid, Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE));
            __setMapForCabinetList(map, grpCanWriteList);

            if (!writeOnly) {
                //ユーザSIDがキャビネットアクセス設定に登録(閲覧)
                ArrayList<FileCabinetDspModel> usrCanReadList = cabDao.getCanAccessCabinet(
                        userSid, Integer.parseInt(GSConstFile.ACCESS_KBN_READ));
                __setMapForCabinetList(map, usrCanReadList);

                //所属しているグループSIDがキャビネットアクセス設定に登録(閲覧)
                ArrayList<FileCabinetDspModel> grpCanReadList = cabDao.getCanAccessGpCabinet(
                        userSid, Integer.parseInt(GSConstFile.ACCESS_KBN_READ));
                __setMapForCabinetList(map, grpCanReadList);
            }

            //ソート順に並べ替え
            ret = __doCabinetSort(map, cabDao.getFileCabinetDspModels(userSid));
        }

        //キャビネット情報に付加情報を追加
        if (exInfo) {
            __setCabinetExInfo(userSid, ret, con);
        }

        return ret;
    }

    /**
     * キャビネットlistをallの順で並び替えたリストを生成します。
     * @param map 並び変え対象配列
     * @param all 並び変え順配列
     * @return 並び変え済み配列
     */
    private ArrayList<FileCabinetDspModel> __doCabinetSort(
            HashMap<Integer, FileCabinetDspModel> map, ArrayList<FileCabinetDspModel> all) {

        ArrayList<FileCabinetDspModel> ret = new ArrayList<FileCabinetDspModel>();
        for (FileCabinetDspModel bean : all) {
            if (map.containsKey(new Integer(bean.getFcbSid()))) {

                ret.add(map.get(new Integer(bean.getFcbSid())));
            }
        }
        return ret;
    }

    /**
     * ディスク使用率やアクセス権限、通知設定情報を付加する
     * @param userSid ユーザSID
     * @param list 表示用キャビネット一覧情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    private void __setCabinetExInfo(
            int userSid, ArrayList<FileCabinetDspModel> list, Connection con)
    throws SQLException {

        for (FileCabinetDspModel bean : list) {
            __setUsedDiskSizeString(bean, con);
            __setCallIconInfo(userSid, bean, con);
            setRootDirSid(bean, con);
            bean.setDspfcbName(StringUtilHtml.transToHTml(bean.getFcbName()));
            bean.setDspBikoString(StringUtilHtml.transToHTmlPlusAmparsant(bean.getFcbBiko()));

            //コメント改行区分
            if (bean.getDspBikoString().indexOf("<BR>") >= 0
                    || bean.getDspBikoString().indexOf("<br>") >= 0) {
                bean.setDspBikoBrKbn(FIL_CMT_BR_YES);
            } else {
                bean.setDspBikoBrKbn(FIL_CMT_BR_NO);
            }
        }
    }

    /**
     * キャビネット毎の通知設定アイコン表示情報を設定します
     * @param userSid ユーザSID
     * @param bean FileCabinetDspModel
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    private void __setCallIconInfo(int userSid, FileCabinetDspModel bean, Connection con)
    throws SQLException {

        String callIconKbn = GSConstFile.DSP_KBN_OFF;

        FileCallConfDao dao = new FileCallConfDao(con);
        if (dao.isCabinetCallSetting(bean.getFcbSid(), userSid)) {
            callIconKbn = GSConstFile.DSP_KBN_ON;
        }
        bean.setCallIconKbn(callIconKbn);

    }

    /**
     * キャビネットのRootディレクトリSIDを取得し設定する
     * @param bean FileCabinetDspModel
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void setRootDirSid(FileCabinetDspModel bean, Connection con)
    throws SQLException {
        FileDirectoryDao dao = new FileDirectoryDao(con);
        FileDirectoryModel mdl = dao.getRootDirectory(bean.getFcbSid());
        bean.setRootDirSid(mdl.getFdrSid());
    }
    /**
     * キャビネット毎のファイルサイズ合計や警告を設定します
     * @param bean FileCabinetDspModel
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    private void __setUsedDiskSizeString(FileCabinetDspModel bean, Connection con)
    throws SQLException {

        if (bean == null) {
            return;
        }
        StringBuilder ret = new StringBuilder();
        String warnKbn = GSConstFile.DSP_KBN_OFF;

        int fcbSid = bean.getFcbSid();
        FileCabinetDao dao = new FileCabinetDao(con);
        BigDecimal sum = dao.getCabinetUsedSize(fcbSid);
        BigDecimal convSum = null;

        //集計値が1MB以上か判定
        if (sum.compareTo(GSConstFile.B_TO_MB) == -1) {
            //BからKBへ変換
            convSum = sum.divide(GSConstFile.KB_TO_MB, 1, RoundingMode.HALF_UP);
            ret.append(StringUtil.toCommaFromBigDecimal(convSum));

            if (bean.getFcbCapaKbn() == GSConstFile.CAPA_KBN_ON) {
                BigDecimal capaSize = new BigDecimal(bean.getFcbCapaSize());
                //MB→KBへ変換
                capaSize = capaSize.multiply(GSConstFile.KB_TO_MB);
                //キャパ制限有り
                ret.append("/" + StringUtil.toCommaFromBigDecimal(capaSize));
                ret.append("KB<br>(");
                if (capaSize.intValue() == 0) {
                    ret.append("-");
                } else {
                    BigDecimal par =  convSum.divide(capaSize, 3, RoundingMode.HALF_UP);
                    BigDecimal persent = par.multiply(new BigDecimal(100));
                    persent = persent.setScale(1, RoundingMode.HALF_UP);
                    ret.append(StringUtil.toCommaFromBigDecimal(persent));
                    if (bean.getFcbCapaWarn() > 0) {
                        if (persent.compareTo(new BigDecimal(bean.getFcbCapaWarn())) > 0) {
                            warnKbn = GSConstFile.DSP_KBN_ON;
                        }
                    }

                }
                ret.append("%)");
            } else {
                //キャパ制限無し
                ret.append("KB");
            }
        } else {
            //BからMBへ変換
            convSum = sum.divide(GSConstFile.B_TO_MB, 1, RoundingMode.HALF_UP);
            ret.append(StringUtil.toCommaFromBigDecimal(convSum));

            if (bean.getFcbCapaKbn() == GSConstFile.CAPA_KBN_ON) {
                BigDecimal capaSize = new BigDecimal(bean.getFcbCapaSize());
                //キャパ制限有り
                ret.append("/" + StringUtil.toCommaFromBigDecimal(capaSize));
                ret.append("MB<br>(");
                if (capaSize.intValue() == 0) {
                    ret.append("-");
                } else {
                    BigDecimal par =  convSum.divide(capaSize, 3, RoundingMode.HALF_UP);
                    BigDecimal persent = par.multiply(new BigDecimal(100));
                    persent = persent.setScale(1, RoundingMode.HALF_UP);
                    ret.append(StringUtil.toCommaFromBigDecimal(persent));
                    if (bean.getFcbCapaWarn() > 0) {
                        if (persent.compareTo(new BigDecimal(bean.getFcbCapaWarn())) > 0) {
                            warnKbn = GSConstFile.DSP_KBN_ON;
                        }
                    }

                }
                ret.append("%)");
            } else {
                //キャパ制限無し
                ret.append("MB");
            }
        }

        bean.setDiskUsedString(ret.toString());
        bean.setDiskUsedWarning(warnKbn);
    }

    /**
     * 配列に格納されたFileCabinetDspModelをHashMapに設定する
     * @param map HashMap
     * @param list FileCabinetDspModelの配列
     */
    private void __setMapForCabinetList(
            HashMap<Integer, FileCabinetDspModel> map, ArrayList<FileCabinetDspModel> list) {

        for (FileCabinetDspModel bean : list) {
            if (map.containsKey(new Integer(bean.getFcbSid()))) {
                if (bean.getAccessIconKbn().equals(GSConstFile.ACCESS_KBN_WRITE)) {
                    map.put(new Integer(bean.getFcbSid()), bean);
                }
            } else {
                map.put(new Integer(bean.getFcbSid()), bean);
            }
        }
    }

    /**
     * ショートカット一覧を取得します
     * @param userSid ユーザSID
     * @param con コネクション
     * @return ショートカット一覧
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<FileLinkSimpleModel> getShortcutInfoList(int userSid, Connection con)
    throws SQLException {
        ArrayList<FileLinkSimpleModel> ret = new ArrayList<FileLinkSimpleModel>();
        FileShortcutConfDao dao = new FileShortcutConfDao(con);
        //ショートカット情報を取得
        ArrayList<FileDirectoryModel> shortCutList = dao.getShortCutInfo(userSid);
        FilCommonBiz filCmnBiz = new FilCommonBiz(con, reqMdl__);
        FileLinkSimpleModel dspModel = null;
        String path = "";
        for (FileDirectoryModel bean : shortCutList) {
            path = filCmnBiz.getDirctoryPath(bean.getFdrSid(), con);
            dspModel = new FileLinkSimpleModel();
            dspModel.setDirectoryFullPathName(path);
            dspModel.setDirectoryKbn(bean.getFdrKbn());
            dspModel.setDirectoryName(bean.getFdrName());
            dspModel.setCabinetSid(bean.getFcbSid());
            dspModel.setDirectorySid(bean.getFdrSid());
            dspModel.setBinSid(bean.getBinSid());
            dspModel.setDirectoryUpdateStr(
                    UDateUtil.getSlashYYMD(bean.getFdrEdate())
                    + " "
                    + UDateUtil.getSeparateHMS(bean.getFdrEdate()));
            ret.add(dspModel);
        }
        return ret;
    }

    /**
     * 更新通知一覧を取得します
     * @param userSid ユーザSID
     * @param con コネクション
     * @return 更新通知一覧
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<FileLinkSimpleModel> getCallInfoList(int userSid, Connection con)
    throws SQLException {
        ArrayList<FileLinkSimpleModel> ret = new ArrayList<FileLinkSimpleModel>();
        FileCallDataDao dao = new FileCallDataDao(con);

        //個人設定を取得
        FileUconfDao uconfDao = new FileUconfDao(con);
        FileUconfModel uconfModel = uconfDao.select(userSid);
        int limit = uconfModel.getFucCall();
        int offset = 1;

        //更新通知情報を取得
        ArrayList<FileDirectoryModel> updateList = dao.getUpdateCallData(userSid, offset, limit);

        FilCommonBiz filCmnBiz = new FilCommonBiz(con, reqMdl__);
        FileLinkSimpleModel dspModel = null;
        String path = "";
        for (FileDirectoryModel bean : updateList) {
            path = filCmnBiz.getDirctoryPath(bean.getFdrSid(), con);
            dspModel = new FileLinkSimpleModel();
            dspModel.setDirectoryFullPathName(path);
            dspModel.setDirectoryKbn(bean.getFdrKbn());
            dspModel.setDirectoryName(bean.getFdrName());
            dspModel.setCabinetSid(bean.getFcbSid());
            dspModel.setDirectorySid(bean.getFdrSid());
            dspModel.setDirectoryUpdateStr(
                    UDateUtil.getSlashYYMD(bean.getFdrEdate())
                    + " "
                    + UDateUtil.getSeparateHMS(bean.getFdrEdate()));
            dspModel.setFcbMark(bean.getFcbMark());
            ret.add(dspModel);
        }
        return ret;
    }

    /**
     * <br>[機  能] 削除するショートカットのタイトルを取得する
     * <br>[解  説] 複数存在する場合は改行を挿入する
     * <br>[備  考]
     * @param paramMdl Fil010ParamModel
     * @param userSid ユーザSID
     * @param con コネクション
     * @return String 削除するショートカット名称
     * @throws SQLException SQL実行例外
     */
    public String getDeleteShortName(Fil010ParamModel paramMdl, int userSid, Connection con)
    throws SQLException {

        //ショートカット名称取得
        StringBuilder deleteShort = new StringBuilder();
        List <FileDirectoryModel> fdList = __getConfList(paramMdl, userSid, con);

        FilCommonBiz filCmnBiz = new FilCommonBiz(con, reqMdl__);

        for (int i = 0; i < fdList.size(); i++) {
            FileDirectoryModel model = fdList.get(i);
            //フルパスで名称を取得
            deleteShort.append(filCmnBiz.getDirctoryPath(model.getFdrSid(), con));

            //改行を挿入
            if (i < fdList.size() - 1) {
                deleteShort.append(GSConst.NEW_LINE_STR);
            }
        }
        return deleteShort.toString();
    }

    /**
     * <br>[機  能] 選択したディレクトリSID(複数)からショートカット情報を取得
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil010ParamModel
     * @param userSid ユーザSID
     * @param con コネクション
     * @return ショートカット情報
     * @throws SQLException SQL実行例外
     */
    private List<FileDirectoryModel> __getConfList(
            Fil010ParamModel paramMdl, int userSid, Connection con)
    throws SQLException {

        String[] delInfSid = paramMdl.getFil010SelectDelLink();
        List<FileDirectoryModel> ret = new ArrayList<FileDirectoryModel>();

        for (int i = 0; i < delInfSid.length; i++) {
            //SID
            int shortSid = Integer.parseInt(delInfSid[i]);
            FileShortcutConfDao dao = new FileShortcutConfDao(con);
            ret.add(dao.getShortCutInfo(userSid, shortSid));
        }
        return ret;
    }

    /**
     * <br>[機  能] 削除実行
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil010ParamModel
     * @param userSid ユーザSID
     * @param con コネクション
     * @throws SQLException SQL実行例外
     */
    public void deleteShort(Fil010ParamModel paramMdl, int userSid, Connection con)
    throws SQLException {

        String[] delInfSid = paramMdl.getFil010SelectDelLink();

        try {
            con.setAutoCommit(false);
            FileShortcutConfDao dao = new FileShortcutConfDao(con);

            for (int i = 0; i < delInfSid.length; i++) {
                //SID
                int shortSid = Integer.parseInt(delInfSid[i]);
                //削除処理
                dao.delete(shortSid, userSid);
                //削除チェックボックスクリア
                paramMdl.setFil010SelectDelLink(null);
            }
            con.commit();

        } catch (SQLException e) {
            log__.warn("ショートカット削除に失敗", e);
            JDBCUtil.rollback(con);
            throw e;
        }
    }
}
