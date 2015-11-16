package jp.groupsession.v2.cmn.cmn100;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.lang.ClassUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.biz.PosBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.base.CmnBelongmDao;
import jp.groupsession.v2.cmn.dao.base.CmnCmbsortConfDao;
import jp.groupsession.v2.cmn.dao.base.CmnLabelUsrDao;
import jp.groupsession.v2.cmn.dao.base.CmnTdfkDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmInfDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmLabelDao;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnCmbsortConfModel;
import jp.groupsession.v2.cmn.model.base.CmnLabelUsrModel;
import jp.groupsession.v2.cmn.model.base.CmnTdfkModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.usr.GSConstUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] ユーザ情報ポップアップのビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cmn100Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Cmn100Biz.class);
    /** GSメッセージ */
    public GsMessage gsMsg__ = null;

    /**
     * <p>コンストラクタ
     * @param gsMsg GSメッセージ
     */
    public Cmn100Biz(GsMessage gsMsg) {
        gsMsg__ = gsMsg;
    }

    /**
     * <br>[機  能] テンポラリディレクトリパスを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param rootDir ルートディレクトリ
     * @param cmn100Model パラメータ情報
     * @param reqMdl リクエストモデル
     * @return テンポラリディレクトリパス
     */
    public static String getTempDir(String rootDir,
                                    Cmn100ParamModel cmn100Model,
                                    RequestModel reqMdl) {
        //セッションID
        String sessionId = reqMdl.getSession().getId();

        StringBuilder tempDir = new StringBuilder("");
        tempDir.append(rootDir);
        tempDir.append("/");
        tempDir.append(cmn100Model.getCmn100pluginId());
        tempDir.append("/");
        tempDir.append(sessionId);
        tempDir.append("/");

        return tempDir.toString();
    }

    /**
     * <br>[機  能] ユーザ情報をセットします
     * <br>[解  説]
     * <br>[備  考]
     * @param cmn100Model パラメータ情報
     * @param appRoot アプリケーションルート
     * @param rootDir ルートディレクトリ
     * @param reqMdl リクエストモデル
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException 写真データ操作時例外
     * @throws IOException  ファイル操作時例外
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public void setUsrmInf(Cmn100ParamModel cmn100Model,
                             String appRoot,
                             String rootDir,
                             RequestModel reqMdl,
                             Connection con)
        throws SQLException, IOToolsException, IOException, TempFileException {

        //月
        String textMonth = gsMsg__.getMessage("cmn.month");
        //日
        String textDay = gsMsg__.getMessage("cmn.day");


        //インスタンス生成
        CmnUsrmInfDao dao = new CmnUsrmInfDao(con);
        CmnUsrmLabelDao labelDao = new CmnUsrmLabelDao(con);
        CmnUsrmInfModel model = new CmnUsrmInfModel();

        //ユーザSIDをセット
        model.setUsrSid(
                Integer.parseInt(
                        NullDefault.getString(cmn100Model.getCmn100usid(), "-1")));

        model = dao.select(model);

        //写真公開フラグ
        cmn100Model.setCmn100usiPictKf(model.getUsiPictKf());
        //社員・職員番号
        cmn100Model.setCmn100usiSyainNo(NullDefault.getString(model.getUsiSyainNo(), ""));
        //姓
        cmn100Model.setCmn100usiSei(NullDefault.getString(model.getUsiSei(), ""));
        //名
        cmn100Model.setCmn100usiMei(NullDefault.getString(model.getUsiMei(), ""));
        //姓カナ
        cmn100Model.setCmn100usiSeiKn(NullDefault.getString(model.getUsiSeiKn(), ""));
        //名カナ
        cmn100Model.setCmn100usiMeiKn(NullDefault.getString(model.getUsiMeiKn(), ""));
        //所属
        cmn100Model.setCmn100usiSyozoku(NullDefault.getString(model.getUsiSyozoku(), ""));
        //役職
        //役職名称を取得
        String posName = "";
        if (model.getPosSid() > GSConst.POS_DEFAULT) {
            PosBiz pBiz = new PosBiz();
            posName = NullDefault.getString(pBiz.getPosName(con, model.getPosSid()), "");
        }
        cmn100Model.setCmn100usiYakusyoku(posName);

        //生年月日
        UDate bDate = model.getUsiBdate();
        if (bDate != null) {
            String birthDay =
                  gsMsg__.getMessage("cmn.year", new String[] {bDate.getStrYear()})
                + bDate.getStrMonth()
                + textMonth
                + bDate.getStrDay()
                + textDay;
            cmn100Model.setCmn100usiBdate(birthDay);
        }

        //入社年月日
        UDate entranceDate = model.getUsiEntranceDate();
        if (entranceDate != null) {
            String entranceDateStr =
                  gsMsg__.getMessage("cmn.year", new String[] {entranceDate.getStrYear()})
                + entranceDate.getStrMonth()
                + textMonth
                + entranceDate.getStrDay()
                + textDay;
            cmn100Model.setCmn100entranceDate(entranceDateStr);
        }

        //性別取得
        GsMessage gsMsg = new GsMessage(reqMdl);
        String seibetu = "";
        if (model.getUsiSeibetu() == GSConstUser.SEIBETU_MAN) {
            seibetu = gsMsg.getMessage("user.124");

        } else if (model.getUsiSeibetu() ==  GSConstUser.SEIBETU_WOMAN) {
            seibetu = gsMsg.getMessage("user.125");
        }
        cmn100Model.setCmn100usiSeibetu(seibetu);

        //年齢
        cmn100Model.setCmn100usiAge(UDateUtil.getAge(bDate));
        //生年月日公開フラグ
        cmn100Model.setCmn100usiBdateKf(model.getUsiBdateKf());
        //メールアドレス１
        cmn100Model.setCmn100usiMail1(NullDefault.getString(model.getUsiMail1(), ""));
        //メールアドレスコメント１
        cmn100Model.setCmn100usiMailCmt1(NullDefault.getString(model.getUsiMailCmt1(), ""));
        //メールアドレス１公開フラグ
        cmn100Model.setCmn100usiMail1Kf(model.getUsiMail1Kf());
        //メールアドレス２
        cmn100Model.setCmn100usiMail2(NullDefault.getString(model.getUsiMail2(), ""));
        //メールアドレスコメント２
        cmn100Model.setCmn100usiMailCmt2(NullDefault.getString(model.getUsiMailCmt2(), ""));
        //メールアドレス２公開フラグ
        cmn100Model.setCmn100usiMail2Kf(model.getUsiMail2Kf());
        //メールアドレス３
        cmn100Model.setCmn100usiMail3(NullDefault.getString(model.getUsiMail3(), ""));
        //メールアドレスコメント３
        cmn100Model.setCmn100usiMailCmt3(NullDefault.getString(model.getUsiMailCmt3(), ""));
        //メールアドレス３公開フラグ
        cmn100Model.setCmn100usiMail3Kf(model.getUsiMail3Kf());

        //郵便番号
        String postNo = "";
        String post3 = NullDefault.getString(model.getUsiZip1(), "");
        String post4 = NullDefault.getString(model.getUsiZip2(), "");
        if (!post3.equals("") && !post4.equals("")) {
            postNo = post3 + "-" + post4;
        }
        cmn100Model.setCmn100usiZip(postNo);

        //郵便番号公開フラグセット
        cmn100Model.setCmn100usiZipKf(model.getUsiZipKf());

        int tdfkCode = model.getTdfSid();
        String tdfkName = "";
        if (tdfkCode > 0) {
            CmnTdfkDao tdfkDao = new CmnTdfkDao(con);
            CmnTdfkModel param = new CmnTdfkModel();
            param.setTdfSid(tdfkCode);
            CmnTdfkModel ret = tdfkDao.select(param);
            if (ret != null) {
                tdfkName = ret.getTdfName();
            }
        }
        cmn100Model.setCmn100TdfkName(tdfkName);

        //都道府県公開フラグ
        cmn100Model.setCmn100usiTdfkKf(model.getUsiTdfKf());
        //住所１
        cmn100Model.setCmn100usiAddr1(NullDefault.getString(model.getUsiAddr1(), ""));
        //住所１公開フラグ
        cmn100Model.setCmn100usiAddr1Kf(model.getUsiAddr1Kf());
        //住所２
        cmn100Model.setCmn100usiAddr2(NullDefault.getString(model.getUsiAddr2(), ""));
        //住所２公開フラグ
        cmn100Model.setCmn100usiAddr2Kf(model.getUsiAddr2Kf());
        //電話番号１
        cmn100Model.setCmn100usiTel1(NullDefault.getString(model.getUsiTel1(), ""));
        //電話番号内線１
        cmn100Model.setCmn100usiTelNai1(NullDefault.getString(model.getUsiTelNai1(), ""));
        //電話番号コメント１
        cmn100Model.setCmn100usiTelCmt1(NullDefault.getString(model.getUsiTelCmt1(), ""));
        //電話番号１公開フラグ
        cmn100Model.setCmn100usiTel1Kf(model.getUsiTel1Kf());
        //電話番号２
        cmn100Model.setCmn100usiTel2(NullDefault.getString(model.getUsiTel2(), ""));
        //電話番号内線２
        cmn100Model.setCmn100usiTelNai2(NullDefault.getString(model.getUsiTelNai2(), ""));
        //電話番号コメント２
        cmn100Model.setCmn100usiTelCmt2(NullDefault.getString(model.getUsiTelCmt2(), ""));
        //電話番号２公開フラグ
        cmn100Model.setCmn100usiTel2Kf(model.getUsiTel2Kf());
        //電話番号３
        cmn100Model.setCmn100usiTel3(NullDefault.getString(model.getUsiTel3(), ""));
        //電話番号内線３
        cmn100Model.setCmn100usiTelNai3(NullDefault.getString(model.getUsiTelNai3(), ""));
        //電話番号コメント３
        cmn100Model.setCmn100usiTelCmt3(NullDefault.getString(model.getUsiTelCmt3(), ""));
        //電話番号３公開フラグ
        cmn100Model.setCmn100usiTel3Kf(model.getUsiTel3Kf());
        //ＦＡＸ１
        cmn100Model.setCmn100usiFax1(NullDefault.getString(model.getUsiFax1(), ""));
        //ＦＡＸコメント１
        cmn100Model.setCmn100usiFaxCmt1(NullDefault.getString(model.getUsiFaxCmt1(), ""));
        //ＦＡＸ１公開フラグ
        cmn100Model.setCmn100usiFax1Kf(model.getUsiFax1Kf());
        //ＦＡＸ２
        cmn100Model.setCmn100usiFax2(NullDefault.getString(model.getUsiFax2(), ""));
        //ＦＡＸコメント２
        cmn100Model.setCmn100usiFaxCmt2(NullDefault.getString(model.getUsiFaxCmt2(), ""));
        //ＦＡＸ２公開フラグ
        cmn100Model.setCmn100usiFax2Kf(model.getUsiFax2Kf());
        //ＦＡＸ３
        cmn100Model.setCmn100usiFax3(NullDefault.getString(model.getUsiFax3(), ""));
        //ＦＡＸコメント３
        cmn100Model.setCmn100usiFaxCmt3(NullDefault.getString(model.getUsiFaxCmt3(), ""));
        //ＦＡＸ３公開フラグ
        cmn100Model.setCmn100usiFax3Kf(model.getUsiFax3Kf());
        //モバイル使用
        cmn100Model.setCmn100UsiMblUseKbn(model.getUsiMblUse());
        //備考
        cmn100Model.setCmn100usiBiko(
                StringUtilHtml.transToHTml(
                        NullDefault.getString(model.getUsiBiko(), "")));
        cmn100Model.setCmn100usiBiko(
                StringUtil.transToLink(cmn100Model.getCmn100usiBiko(),
                        StringUtil.OTHER_WIN));

        //写真
        cmn100Model.setCmn100binSid(model.getBinSid());

        String[] labelArray = labelDao.getLabListBelongUsr(model.getUsrSid());
        //ラベル
        setLabelList(cmn100Model, con, labelArray);

        //WEB検索のキーワードを設定する。
        String address = "";
        if (cmn100Model.getCmn100searchUse() == GSConst.PLUGIN_USE) {
            if (cmn100Model.getCmn100usiAddr1Kf() == GSConstUser.INDIVIDUAL_INFO_OPEN) {
                address = cmn100Model.getCmn100usiAddr1();
            }
            if (cmn100Model.getCmn100usiAddr2Kf() == GSConstUser.INDIVIDUAL_INFO_OPEN) {
                address += cmn100Model.getCmn100usiAddr2();
            }
        }
        address = StringUtil.toSingleCortationEscape(address);
        cmn100Model.setCmn100WebSearchWord(address);
    }

    /**
     * <br>[機  能] ユーザSIDからグループ名を取得、セットします
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param cmn100Model パラメータ情報
     * @throws NumberFormatException 例外
     * @throws SQLException SQL実行時例外
     */
    public void setGrpName(Cmn100ParamModel cmn100Model, Connection con)
                throws NumberFormatException, SQLException {

        log__.debug("START setGrpName");

        //検索結果格納用ArrayList
        ArrayList<GroupModel> ret = new ArrayList<GroupModel>();

        CmnCmbsortConfDao sortDao = new CmnCmbsortConfDao(con);
        CmnCmbsortConfModel sortMdl = sortDao.getCmbSortData();

        GroupDao dao = new GroupDao(con);
        ArrayList<GroupModel> gpList = null;
        if (sortMdl.getCscGroupSkbn() == GSConst.GROUPCMB_SKBN_SET) {
            gpList = dao.getGroupTree(sortMdl);
        } else {
            gpList = dao.getGroupList(sortMdl);
        }
        CmnBelongmDao belongDao = new CmnBelongmDao(con);
        List<Integer> belongList = belongDao.selectUserBelongGroupSid(
                Integer.parseInt(
                NullDefault.getString(cmn100Model.getCmn100usid(), "-1")));

        for (GroupModel gpMdl : gpList) {
            if (belongList.indexOf(new Integer(gpMdl.getGroupSid())) >= 0) {
                ret.add(gpMdl);
            }

        }


        //グループ情報をフォームにセット
        cmn100Model.setCmn100grpNmlist(ret);
    }

    /**
     * <br>[機  能] 追加情報をセットする。
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエストモデル
     * @param con DBコネクション
     * @param pconfig プラグイン設定情報
     * @param cmn100Model パラメータ情報
     * @param context コンテキスト
     * @throws Exception インフォーメーション取得クラスの設定ミスの場合にスロー
     */
    public void setAppendInfo(RequestModel reqMdl,
                                           Connection con,
                                           PluginConfig pconfig,
                                           Cmn100ParamModel cmn100Model,
                                           ServletContext context) throws Exception {
        log__.debug("START setAppendInfo");
        //セッション情報を取得
        BaseUserModel usModel = reqMdl.getSmodel();
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID

        CmnUsrmInfModel model = new CmnUsrmInfModel();
        //ユーザSIDをセット
        model.setUsrSid(
                Integer.parseInt(
                        NullDefault.getString(cmn100Model.getCmn100usid(), "-1")));
        CmnUsrmInfDao dao = new CmnUsrmInfDao(con);
        model = dao.select(model);

        String [] pifclss = pconfig.getCmn100AppendInfoImpl();
        Cmn100AppendInfo[] info = null;
        try {
            info = __getAppendInfo(pifclss);
        } catch (ClassNotFoundException e) {
            log__.error("クラスが見つかりません。設定を見直してください。", e);
            throw e;
        } catch (IllegalAccessException e) {
            log__.error("クラスの設定が間違っています。設定を見直してください。", e);
            throw e;
        } catch (InstantiationException e) {
            log__.error("クラスの設定が間違っています。設定を見直してください。", e);
            throw e;
        }

        //
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("realPath", context.getRealPath("/"));

        ArrayList<Cmn100AppendInfoModel> msgs = new ArrayList<Cmn100AppendInfoModel>();
        for (Cmn100AppendInfo imsgCls : info) {
            List<Cmn100AppendInfoModel> plist
                    = imsgCls.getAppendInfo(paramMap,
                            model.getUsrSid(), sessionUsrSid, con, gsMsg__);
            if (plist != null) {
                msgs.addAll(plist);
            }
        }
//      表示順を元に並び替えを行う
        Collections.sort(msgs);
        cmn100Model.setAppendInfoList(msgs);
    }

    /**
     * <br>[機  能] ユーザラベルを設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param cmn100Model パラメータ情報
     * @param con コネクション
     * @param labelArray ラベルSID配列
     * @throws SQLException SQL実行時例外
     */
    public void setLabelList(Cmn100ParamModel cmn100Model, Connection con, String[] labelArray)
        throws SQLException {

        if (labelArray != null) {

            //ユーザラベル
            CmnLabelUsrDao dao = new CmnLabelUsrDao(con);
            ArrayList<CmnLabelUsrModel> list = new ArrayList<CmnLabelUsrModel>();
            for (String str : labelArray) {
                CmnLabelUsrModel model = dao.selectOneLabel(Integer.parseInt(str));
                list.add(model);
            }
            cmn100Model.setLabelList(list);
        }
    }

    /**
     * <p>追加情報のリストを取得する
     * @param classNames プラグインクラス名
     * @throws ClassNotFoundException 指定されたクラスが存在しない
     * @throws IllegalAccessException 実装クラスのインスタンス生成に失敗
     * @throws InstantiationException 実装クラスのインスタンス生成に失敗
     * @return リスナー
     */
    private Cmn100AppendInfo[] __getAppendInfo(String[] classNames)
    throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        Cmn100AppendInfo[] iclasses = new Cmn100AppendInfo[classNames.length];
        for (int i = 0; i < classNames.length; i++) {
            Object obj = ClassUtil.getObject(classNames[i]);
            iclasses[i] = (Cmn100AppendInfo) obj;
        }
        return iclasses;
    }
}