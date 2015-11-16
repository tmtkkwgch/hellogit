package jp.groupsession.v2.rsv.rsv090kn;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.io.ObjectFile;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.rsv.biz.RsvCommonBiz;
import jp.groupsession.v2.rsv.dao.RsvBinDao;
import jp.groupsession.v2.rsv.dao.RsvSisDataDao;
import jp.groupsession.v2.rsv.dao.RsvSisGrpDao;
import jp.groupsession.v2.rsv.model.RsvSisDataModel;
import jp.groupsession.v2.rsv.rsv080.Rsv080Model;
import jp.groupsession.v2.rsv.rsv090.Rsv090Biz;
import jp.groupsession.v2.rsv.rsv090.Rsv090PlaceForm;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 施設予約 [施設登録確認]画面のビジネスロジック
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv090knBiz extends Rsv090Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rsv090knBiz.class);

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param reqMdl リクエスト情報
     * @param con コネクション
     */
    public Rsv090knBiz(RequestModel reqMdl, Connection con) {
        super(reqMdl, con);
    }

    /**
     * <br>[機  能] 画面表示処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv090knParamModel
     * @throws SQLException SQL実行時例外
     */
    public void setInitData(Rsv090knParamModel paramMdl) throws SQLException {

        //施設グループデータセット
        int rsgSid = paramMdl.getRsv090EditGrpSid();
        RsvSisGrpDao grpDao = new RsvSisGrpDao(con_);
        Rsv080Model grpMdl = grpDao.getGrpBaseData(rsgSid);
        paramMdl.setRsv090GrpName(grpMdl.getRsgName());
        paramMdl.setRsv090SisetuKbnName(grpMdl.getRskName());
        int rskSid = grpMdl.getRskSid();

        //施設区分毎に入力可能な項目を設定
        __setSisetuHeader(paramMdl, rskSid);

        paramMdl.setRsv090knBiko(StringUtilHtml.transToHTmlPlusAmparsant(
                NullDefault.getString(paramMdl.getRsv090Biko(), "")));

        //予約可能期限を数字変換する
        String yoyakuKigen = NullDefault.getStringZeroLength(paramMdl.getRsv090Prop6Value(), "");
        if (yoyakuKigen.length() > 0) {
            int yoyakuNum = Integer.parseInt(paramMdl.getRsv090Prop6Value());
            paramMdl.setRsv090Prop6Value(String.valueOf(yoyakuNum));
        }

        //施設グループに管理者が設定されているかを判定
        ArrayList<String> grpAdmUsers = grpDao.getDefaultAdmUser(rsgSid);
        paramMdl.setRsv090sisGrpAdmFlg((grpAdmUsers != null && !grpAdmUsers.isEmpty()));

    }

    /**
     * <br>[機  能] 入力内容をDBに反映する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Rsv090knParamModel
     * @param ctrl 採番用コネクション
     * @param appRoot アプリケーションルートパス
     * @param tempDirSisetu テンポラリディレクトリパス(施設画像用)
     * @param weekDayDsptempDir テンポラリディレクトリパス(施設週間・日間画像用)
     * @param tempDirPlace  テンポラリディレクトリパス(場所画像用)
     * @param plaTempDataDir テンポラリディレクトリパス(場所画像データ用)
     * @throws SQLException SQL実行時例外
     * @throws TempFileException 添付ファイルUtil内での例外
     * @throws IOToolsException ファイルアクセス時例外
     */
    public void updateSisetuData(Rsv090knParamModel paramMdl,
                                  MlCountMtController ctrl,
                                  String appRoot,
                                  String tempDirSisetu,
                                  String weekDayDsptempDir,
                                  String tempDirPlace,
                                  String plaTempDataDir)
        throws SQLException, TempFileException, IOToolsException {

        int sisetuSid = -1;
        UDate now = new UDate();
        BaseUserModel usrMdl = _getSessionUserModel(reqMdl_);
        int usrSid = usrMdl.getUsrsid();

        RsvSisDataDao dataDao = new RsvSisDataDao(con_);

        //新規
        if (paramMdl.getRsv090ProcMode().equals(GSConstReserve.PROC_MODE_SINKI)) {

            log__.debug("新規モード");

            //施設SID採番
            sisetuSid =
                (int) ctrl.getSaibanNumber(
                        GSConstReserve.SBNSID_RESERVE,
                        GSConstReserve.SBNSID_SUB_SISETU,
                        usrSid);

            RsvSisDataModel dataParam = new RsvSisDataModel();
            dataParam.setRsgSid(paramMdl.getRsv090EditGrpSid());
            dataParam.setRsdSid(sisetuSid);
            dataParam.setRsdId(NullDefault.getString(paramMdl.getRsv090SisetuId(), ""));
            dataParam.setRsdName(NullDefault.getString(paramMdl.getRsv090SisetuName(), ""));
            dataParam.setRsdSnum(NullDefault.getString(paramMdl.getRsv090SisanKanri(), ""));
            dataParam.setRsdProp1(NullDefault.getString(paramMdl.getRsv090Prop1Value(), ""));
            dataParam.setRsdProp2(NullDefault.getString(paramMdl.getRsv090Prop2Value(), ""));
            dataParam.setRsdProp3(NullDefault.getString(paramMdl.getRsv090Prop3Value(), ""));
            dataParam.setRsdProp4(NullDefault.getString(paramMdl.getRsv090Prop4Value(), ""));
            dataParam.setRsdProp5(NullDefault.getString(paramMdl.getRsv090Prop5Value(), ""));
            dataParam.setRsdProp6(NullDefault.getString(paramMdl.getRsv090Prop6Value(), ""));
            dataParam.setRsdProp7(NullDefault.getString(paramMdl.getRsv090Prop7Value(), ""));
            dataParam.setRsdProp8Df(GSConstReserve.SISETU_DATA_DSP_OFF);
            dataParam.setRsdProp9Df(GSConstReserve.SISETU_DATA_DSP_OFF);
            dataParam.setRsdProp10Df(GSConstReserve.SISETU_DATA_DSP_OFF);

            //場所コメント
            dataParam.setRsdPlaCmt(NullDefault.getString(paramMdl.getRsv090PlaceComment(), ""));

            //場所画像コメント
            dataParam.setRsdImgCmt1(
                    NullDefault.getString(
                            getPlaceImgData(plaTempDataDir, 1).getRsv090PlaceFileComment(), ""));
            dataParam.setRsdImgCmt2(
                    NullDefault.getString(
                            getPlaceImgData(plaTempDataDir, 2).getRsv090PlaceFileComment(), ""));
            dataParam.setRsdImgCmt3(
                    NullDefault.getString(
                            getPlaceImgData(plaTempDataDir, 3).getRsv090PlaceFileComment(), ""));
            dataParam.setRsdImgCmt4(
                    NullDefault.getString(
                            getPlaceImgData(plaTempDataDir, 4).getRsv090PlaceFileComment(), ""));
            dataParam.setRsdImgCmt5(
                    NullDefault.getString(
                            getPlaceImgData(plaTempDataDir, 5).getRsv090PlaceFileComment(), ""));
            dataParam.setRsdImgCmt6(
                    NullDefault.getString(
                            getPlaceImgData(plaTempDataDir, 6).getRsv090PlaceFileComment(), ""));
            dataParam.setRsdImgCmt7(
                    NullDefault.getString(
                            getPlaceImgData(plaTempDataDir, 7).getRsv090PlaceFileComment(), ""));
            dataParam.setRsdImgCmt8(
                    NullDefault.getString(
                            getPlaceImgData(plaTempDataDir, 8).getRsv090PlaceFileComment(), ""));
            dataParam.setRsdImgCmt9(
                    NullDefault.getString(
                            getPlaceImgData(plaTempDataDir, 9).getRsv090PlaceFileComment(), ""));
            dataParam.setRsdImgCmt10(
                    NullDefault.getString(
                            getPlaceImgData(plaTempDataDir, 10).getRsv090PlaceFileComment(), ""));

            dataParam.setRsdBiko(NullDefault.getString(paramMdl.getRsv090Biko(), ""));
            dataParam.setRsdAuid(usrSid);
            dataParam.setRsdAdate(now);
            dataParam.setRsdEuid(usrSid);
            dataParam.setRsdEdate(now);


            //日間・週間施設情報表示区分設定
            dataParam.setRsdIdDf(Integer.parseInt(paramMdl.getRsv090SisetuIdDspKbn()));
            dataParam.setRsdSnumDf(Integer.parseInt(paramMdl.getRsv090SisanKanriDspKbn()));
            dataParam.setRsdProp1Df(Integer.parseInt(paramMdl.getRsv090Prop1ValueDspKbn()));
            dataParam.setRsdProp2Df(Integer.parseInt(paramMdl.getRsv090Prop2ValueDspKbn()));
            dataParam.setRsdProp3Df(Integer.parseInt(paramMdl.getRsv090Prop3ValueDspKbn()));
            dataParam.setRsdProp4Df(Integer.parseInt(paramMdl.getRsv090Prop4ValueDspKbn()));
            dataParam.setRsdProp5Df(Integer.parseInt(paramMdl.getRsv090Prop5ValueDspKbn()));
            dataParam.setRsdProp6Df(Integer.parseInt(paramMdl.getRsv090Prop6ValueDspKbn()));
            dataParam.setRsdProp7Df(Integer.parseInt(paramMdl.getRsv090Prop7ValueDspKbn()));
            dataParam.setRsdProp8Df(GSConstReserve.SISETU_DATA_DSP_OFF);
            dataParam.setRsdProp9Df(GSConstReserve.SISETU_DATA_DSP_OFF);
            dataParam.setRsdProp10Df(GSConstReserve.SISETU_DATA_DSP_OFF);
            dataParam.setRsdImgCmt1Df(
                    NullDefault.getInt(
                            getPlaceImgData(plaTempDataDir, 1).getRsv090PlaceFileCommentDspKbn(),
                            GSConstReserve.SISETU_DATA_DSP_OFF));
            dataParam.setRsdImgCmt2Df(
                    NullDefault.getInt(
                            getPlaceImgData(plaTempDataDir, 2).getRsv090PlaceFileCommentDspKbn(),
                            GSConstReserve.SISETU_DATA_DSP_OFF));
            dataParam.setRsdImgCmt3Df(
                    NullDefault.getInt(
                            getPlaceImgData(plaTempDataDir, 3).getRsv090PlaceFileCommentDspKbn(),
                            GSConstReserve.SISETU_DATA_DSP_OFF));
            dataParam.setRsdImgCmt4Df(
                    NullDefault.getInt(
                            getPlaceImgData(plaTempDataDir, 4).getRsv090PlaceFileCommentDspKbn(),
                            GSConstReserve.SISETU_DATA_DSP_OFF));
            dataParam.setRsdImgCmt5Df(
                    NullDefault.getInt(
                            getPlaceImgData(plaTempDataDir, 5).getRsv090PlaceFileCommentDspKbn(),
                            GSConstReserve.SISETU_DATA_DSP_OFF));
            dataParam.setRsdImgCmt6Df(
                    NullDefault.getInt(
                            getPlaceImgData(plaTempDataDir, 6).getRsv090PlaceFileCommentDspKbn(),
                            GSConstReserve.SISETU_DATA_DSP_OFF));
            dataParam.setRsdImgCmt7Df(
                    NullDefault.getInt(
                            getPlaceImgData(plaTempDataDir, 7).getRsv090PlaceFileCommentDspKbn(),
                            GSConstReserve.SISETU_DATA_DSP_OFF));
            dataParam.setRsdImgCmt8Df(
                    NullDefault.getInt(
                            getPlaceImgData(plaTempDataDir, 8).getRsv090PlaceFileCommentDspKbn(),
                            GSConstReserve.SISETU_DATA_DSP_OFF));
            dataParam.setRsdImgCmt9Df(
                    NullDefault.getInt(
                            getPlaceImgData(plaTempDataDir, 9).getRsv090PlaceFileCommentDspKbn(),
                            GSConstReserve.SISETU_DATA_DSP_OFF));
            dataParam.setRsdImgCmt10Df(
                    NullDefault.getInt(
                            getPlaceImgData(plaTempDataDir, 10).getRsv090PlaceFileCommentDspKbn(),
                            GSConstReserve.SISETU_DATA_DSP_OFF));

            dataParam.setRsdBikoDf(Integer.parseInt(paramMdl.getRsv090BikoDspKbn()));
            dataParam.setRsdPlaCmtDf(Integer.parseInt(paramMdl.getRsv090PlaceCommentDspKbn()));
            dataParam.setRsdImgDf(Integer.parseInt(paramMdl.getRsv090SisetuImgDefoDspKbn()));

            if (__canEditApprKbn(paramMdl)) {
                dataParam.setRsdApprKbn(paramMdl.getRsv090apprKbn());
            } else {
                dataParam.setRsdApprKbn(GSConstReserve.RSD_APPR_KBN_NOSET);
            }
            dataParam.setRsdApprKbnDf(Integer.parseInt(paramMdl.getRsv090apprKbnDspKbn()));


            //情報を登録
            dataDao.insertNewSisetu(dataParam);

            paramMdl.setRsv090knUsrSid(usrSid);
            paramMdl.setRsv090knSisetuSid(sisetuSid);

            //週間・日間表示用画像
            __setWeekDayDspSisetuTempData(weekDayDsptempDir, appRoot, ctrl,
                                          now, GSConstReserve.TEMP_IMG_KBN_SISETU, paramMdl);

            //施設用画像
            __setSisetuTempData(tempDirSisetu, appRoot, ctrl,
                                now, GSConstReserve.TEMP_IMG_KBN_SISETU, paramMdl);

            //場所用画像
            __setSisetuTempData(tempDirPlace, appRoot, ctrl,
                                now, GSConstReserve.TEMP_IMG_KBN_PLACE, paramMdl);

        //編集
        } else if (paramMdl.getRsv090ProcMode().equals(GSConstReserve.PROC_MODE_EDIT)) {

            log__.debug("編集モード");

            sisetuSid = paramMdl.getRsv090EditSisetuSid();

            RsvSisDataModel dataParam = new RsvSisDataModel();
            dataParam.setRsgSid(paramMdl.getRsv090EditGrpSid());
            dataParam.setRsdSid(sisetuSid);
            dataParam.setRsdId(NullDefault.getString(paramMdl.getRsv090SisetuId(), ""));
            dataParam.setRsdName(NullDefault.getString(paramMdl.getRsv090SisetuName(), ""));
            dataParam.setRsdSnum(NullDefault.getString(paramMdl.getRsv090SisanKanri(), ""));
            dataParam.setRsdProp1(NullDefault.getString(paramMdl.getRsv090Prop1Value(), ""));
            dataParam.setRsdProp2(NullDefault.getString(paramMdl.getRsv090Prop2Value(), ""));
            dataParam.setRsdProp3(NullDefault.getString(paramMdl.getRsv090Prop3Value(), ""));
            dataParam.setRsdProp4(NullDefault.getString(paramMdl.getRsv090Prop4Value(), ""));
            dataParam.setRsdProp5(NullDefault.getString(paramMdl.getRsv090Prop5Value(), ""));
            dataParam.setRsdProp6(NullDefault.getString(paramMdl.getRsv090Prop6Value(), ""));
            dataParam.setRsdProp7(NullDefault.getString(paramMdl.getRsv090Prop7Value(), ""));
            dataParam.setRsdProp8Df(GSConstReserve.SISETU_DATA_DSP_OFF);
            dataParam.setRsdProp9Df(GSConstReserve.SISETU_DATA_DSP_OFF);
            dataParam.setRsdProp10Df(GSConstReserve.SISETU_DATA_DSP_OFF);
            //場所コメント
            dataParam.setRsdPlaCmt(NullDefault.getString(paramMdl.getRsv090PlaceComment(), ""));

            //場所画像コメント
            dataParam.setRsdImgCmt1(
                    NullDefault.getString(
                            getPlaceImgData(plaTempDataDir, 1).getRsv090PlaceFileComment(), ""));
            dataParam.setRsdImgCmt2(
                    NullDefault.getString(
                            getPlaceImgData(plaTempDataDir, 2).getRsv090PlaceFileComment(), ""));
            dataParam.setRsdImgCmt3(
                    NullDefault.getString(
                            getPlaceImgData(plaTempDataDir, 3).getRsv090PlaceFileComment(), ""));
            dataParam.setRsdImgCmt4(
                    NullDefault.getString(
                            getPlaceImgData(plaTempDataDir, 4).getRsv090PlaceFileComment(), ""));
            dataParam.setRsdImgCmt5(
                    NullDefault.getString(
                            getPlaceImgData(plaTempDataDir, 5).getRsv090PlaceFileComment(), ""));
            dataParam.setRsdImgCmt6(
                    NullDefault.getString(
                            getPlaceImgData(plaTempDataDir, 6).getRsv090PlaceFileComment(), ""));
            dataParam.setRsdImgCmt7(
                    NullDefault.getString(
                            getPlaceImgData(plaTempDataDir, 7).getRsv090PlaceFileComment(), ""));
            dataParam.setRsdImgCmt8(
                    NullDefault.getString(
                            getPlaceImgData(plaTempDataDir, 8).getRsv090PlaceFileComment(), ""));
            dataParam.setRsdImgCmt9(
                    NullDefault.getString(
                            getPlaceImgData(plaTempDataDir, 9).getRsv090PlaceFileComment(), ""));
            dataParam.setRsdImgCmt10(
                    NullDefault.getString(
                            getPlaceImgData(plaTempDataDir, 10).getRsv090PlaceFileComment(), ""));
            dataParam.setRsdBiko(NullDefault.getString(paramMdl.getRsv090Biko(), ""));

            if (__canEditApprKbn(paramMdl)) {
                dataParam.setRsdApprKbn(paramMdl.getRsv090apprKbn());
            } else {
                dataParam.setRsdApprKbn(-1);
            }
            dataParam.setRsdApprKbnDf(Integer.parseInt(paramMdl.getRsv090apprKbnDspKbn()));
            dataParam.setRsdEuid(usrSid);
            dataParam.setRsdEdate(now);

            //日間・週間施設情報表示区分設定
            dataParam.setRsdIdDf(Integer.parseInt(paramMdl.getRsv090SisetuIdDspKbn()));
            dataParam.setRsdSnumDf(Integer.parseInt(paramMdl.getRsv090SisanKanriDspKbn()));
            dataParam.setRsdProp1Df(Integer.parseInt(paramMdl.getRsv090Prop1ValueDspKbn()));
            dataParam.setRsdProp2Df(Integer.parseInt(paramMdl.getRsv090Prop2ValueDspKbn()));
            dataParam.setRsdProp3Df(Integer.parseInt(paramMdl.getRsv090Prop3ValueDspKbn()));
            dataParam.setRsdProp4Df(Integer.parseInt(paramMdl.getRsv090Prop4ValueDspKbn()));
            dataParam.setRsdProp5Df(Integer.parseInt(paramMdl.getRsv090Prop5ValueDspKbn()));
            dataParam.setRsdProp6Df(Integer.parseInt(paramMdl.getRsv090Prop6ValueDspKbn()));
            dataParam.setRsdProp7Df(Integer.parseInt(paramMdl.getRsv090Prop7ValueDspKbn()));
            dataParam.setRsdProp8Df(GSConstReserve.SISETU_DATA_DSP_OFF);
            dataParam.setRsdProp9Df(GSConstReserve.SISETU_DATA_DSP_OFF);
            dataParam.setRsdProp10Df(GSConstReserve.SISETU_DATA_DSP_OFF);
            dataParam.setRsdImgCmt1Df(
                    NullDefault.getInt(
                            getPlaceImgData(plaTempDataDir, 1).getRsv090PlaceFileCommentDspKbn(),
                            GSConstReserve.SISETU_DATA_DSP_OFF));
            dataParam.setRsdImgCmt2Df(
                    NullDefault.getInt(
                            getPlaceImgData(plaTempDataDir, 2).getRsv090PlaceFileCommentDspKbn(),
                            GSConstReserve.SISETU_DATA_DSP_OFF));
            dataParam.setRsdImgCmt3Df(
                    NullDefault.getInt(
                            getPlaceImgData(plaTempDataDir, 3).getRsv090PlaceFileCommentDspKbn(),
                            GSConstReserve.SISETU_DATA_DSP_OFF));
            dataParam.setRsdImgCmt4Df(
                    NullDefault.getInt(
                            getPlaceImgData(plaTempDataDir, 4).getRsv090PlaceFileCommentDspKbn(),
                            GSConstReserve.SISETU_DATA_DSP_OFF));
            dataParam.setRsdImgCmt5Df(
                    NullDefault.getInt(
                            getPlaceImgData(plaTempDataDir, 5).getRsv090PlaceFileCommentDspKbn(),
                            GSConstReserve.SISETU_DATA_DSP_OFF));
            dataParam.setRsdImgCmt6Df(
                    NullDefault.getInt(
                            getPlaceImgData(plaTempDataDir, 6).getRsv090PlaceFileCommentDspKbn(),
                            GSConstReserve.SISETU_DATA_DSP_OFF));
            dataParam.setRsdImgCmt7Df(
                    NullDefault.getInt(
                            getPlaceImgData(plaTempDataDir, 7).getRsv090PlaceFileCommentDspKbn(),
                            GSConstReserve.SISETU_DATA_DSP_OFF));
            dataParam.setRsdImgCmt8Df(
                    NullDefault.getInt(
                            getPlaceImgData(plaTempDataDir, 8).getRsv090PlaceFileCommentDspKbn(),
                            GSConstReserve.SISETU_DATA_DSP_OFF));
            dataParam.setRsdImgCmt9Df(
                    NullDefault.getInt(
                            getPlaceImgData(plaTempDataDir, 9).getRsv090PlaceFileCommentDspKbn(),
                            GSConstReserve.SISETU_DATA_DSP_OFF));
            dataParam.setRsdImgCmt10Df(
                    NullDefault.getInt(
                            getPlaceImgData(plaTempDataDir, 10).getRsv090PlaceFileCommentDspKbn(),
                            GSConstReserve.SISETU_DATA_DSP_OFF));

            dataParam.setRsdBikoDf(Integer.parseInt(paramMdl.getRsv090BikoDspKbn()));
            dataParam.setRsdPlaCmtDf(Integer.parseInt(paramMdl.getRsv090PlaceCommentDspKbn()));
            dataParam.setRsdImgDf(Integer.parseInt(paramMdl.getRsv090SisetuImgDefoDspKbn()));

            //情報を更新
            dataDao.updateSisetuData(dataParam);

            paramMdl.setRsv090knUsrSid(usrSid);
            paramMdl.setRsv090knSisetuSid(sisetuSid);

            RsvBinDao rsvBinDao = new RsvBinDao(con_);
            //バイナリー情報の削除更新
            rsvBinDao.deleteBinfForSisetu(paramMdl.getRsv090knSisetuSid(),
                    GSConstReserve.TEMP_IMG_KBN_SISETU);
            //RSV_BINテーブルの添付データを削除(施設/設備)
            rsvBinDao.deleteSisetuBin(paramMdl.getRsv090knSisetuSid(),
                    GSConstReserve.TEMP_IMG_KBN_SISETU);

            //週間・日間表示用画像
            __editWeekDaySisetuTempData(weekDayDsptempDir, appRoot, ctrl,
                                          now, GSConstReserve.TEMP_IMG_KBN_SISETU, paramMdl);

            //施設画像
            __editSisetuTempData(tempDirSisetu, appRoot, ctrl,
                    now, GSConstReserve.TEMP_IMG_KBN_SISETU, paramMdl);

            //場所画像
            //バイナリー情報の削除更新
            rsvBinDao.deleteBinfForSisetu(paramMdl.getRsv090knSisetuSid(),
                                          GSConstReserve.TEMP_IMG_KBN_PLACE);
            //RSV_BINテーブルの添付データを削除(場所/地図)
            rsvBinDao.deleteSisetuBin(paramMdl.getRsv090knSisetuSid(),
                                      GSConstReserve.TEMP_IMG_KBN_PLACE);
            __editSisetuTempData(tempDirPlace, appRoot, ctrl,
                    now, GSConstReserve.TEMP_IMG_KBN_PLACE, paramMdl);

        }
    }

    /**
     * <br>[機  能] 施設添付情報を登録する
     * <br>[解  説]
     * <br>[備  考]
     * @param tempDir テンポラリディレクトリパス
     * @param appRoot アプリケーションルートパス
     * @param ctrl 採番コントローラ
     * @param now 現在日付
     * @param imgKbn 画像区分
     * @param paramMdl Rsv090knParamModel
     * @throws SQLException SQL実行例外
     * @throws TempFileException 添付ファイルUtil内での例外
     * @throws IOToolsException ファイルアクセス時例外
     */
    private void __setSisetuTempData(String tempDir,
                                      String appRoot,
                                      MlCountMtController ctrl,
                                      UDate now,
                                      int imgKbn,
                                      Rsv090knParamModel paramMdl)
    throws SQLException, TempFileException, IOToolsException {

        //バイナリー情報の登録
        CommonBiz cmnBiz = new CommonBiz();
        List<String> binSids = cmnBiz.insertBinInfo(con_, tempDir, appRoot,
                ctrl, paramMdl.getRsv090knUsrSid(), now);

        //施設添付情報の登録
        RsvBinDao rsvBinDao = new RsvBinDao(con_);
        rsvBinDao.insertRsvBinData(paramMdl.getRsv090knSisetuSid(),
                                   binSids,
                                   imgKbn,
                                   GSConstReserve.IMG_NOT_DSP_KBN);
    }

    /**
     * <br>[機  能] 週間・日間に表示する施設添付情報を登録する
     * <br>[解  説]
     * <br>[備  考]
     * @param tempDir テンポラリディレクトリパス
     * @param appRoot アプリケーションルートパス
     * @param ctrl 採番コントローラ
     * @param now 現在日付
     * @param imgKbn 画像区分
     * @param paramMdl Rsv090knParamModel
     * @throws SQLException SQL実行例外
     * @throws TempFileException 添付ファイルUtil内での例外
     * @throws IOToolsException ファイルアクセス時例外
     */
    private void __setWeekDayDspSisetuTempData(String tempDir,
                                      String appRoot,
                                      MlCountMtController ctrl,
                                      UDate now,
                                      int imgKbn,
                                      Rsv090knParamModel paramMdl)
    throws SQLException, TempFileException, IOToolsException {

        //バイナリー情報の登録
        CommonBiz cmnBiz = new CommonBiz();
        List <String> binSid = cmnBiz.insertBinInfo(con_, tempDir, appRoot,
                ctrl, paramMdl.getRsv090knUsrSid(), now);

        //施設添付情報の登録
        RsvBinDao rsvBinDao = new RsvBinDao(con_);
        rsvBinDao.insertRsvBinData(paramMdl.getRsv090knSisetuSid(),
                                   binSid,
                                   imgKbn,
                                   GSConstReserve.IMG_DSP_KBN);
    }

    /**
     * <br>[機  能] 施設添付情報を編集する
     * <br>[解  説]
     * <br>[備  考]
     * @param tempDir テンポラリディレクトリパス
     * @param appRoot アプリケーションルートパス
     * @param ctrl 採番コントローラ
     * @param now 現在日付
     * @param imgKbn 画像区分
     * @param paramMdl Rsv090knParamModel
     * @throws SQLException SQL実行例外
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    private void __editSisetuTempData(String tempDir,
                                      String appRoot,
                                      MlCountMtController ctrl,
                                      UDate now,
                                      int imgKbn,
                                      Rsv090knParamModel paramMdl)
                                      throws SQLException, TempFileException {


        //バイナリー情報の登録
        CommonBiz cmnBiz = new CommonBiz();
        List <String> binSid = cmnBiz.insertBinInfo(con_, tempDir, appRoot,
                ctrl, paramMdl.getRsv090knUsrSid(), now);

        //施設添付情報の登録
        RsvBinDao rsvBinDao = new RsvBinDao(con_);
        rsvBinDao.insertRsvBinData(paramMdl.getRsv090knSisetuSid(),
                                   binSid,
                                   imgKbn,
                                   GSConstReserve.IMG_NOT_DSP_KBN);
    }

    /**
     * <br>[機  能] 週間・日間に表示する施設添付情報を編集する
     * <br>[解  説]
     * <br>[備  考]
     * @param tempDir テンポラリディレクトリパス
     * @param appRoot アプリケーションルートパス
     * @param ctrl 採番コントローラ
     * @param now 現在日付
     * @param imgKbn 画像区分
     * @param paramMdl Rsv090knParamModel
     * @throws SQLException SQL実行例外
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    private void __editWeekDaySisetuTempData(String tempDir,
                                      String appRoot,
                                      MlCountMtController ctrl,
                                      UDate now,
                                      int imgKbn,
                                      Rsv090knParamModel paramMdl)
                                      throws SQLException, TempFileException {


        //バイナリー情報の登録
        CommonBiz cmnBiz = new CommonBiz();
        List <String> binSid = cmnBiz.insertBinInfo(con_, tempDir, appRoot,
                ctrl, paramMdl.getRsv090knUsrSid(), now);

        //施設添付情報の登録
        RsvBinDao rsvBinDao = new RsvBinDao(con_);
        rsvBinDao.insertRsvBinData(paramMdl.getRsv090knSisetuSid(),
                                   binSid,
                                   imgKbn,
                                   GSConstReserve.IMG_DSP_KBN);


    }

    /**
     * <br>[機  能] 施設区分に応じて施設のヘッダ文字列をセットする
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv090knParamModel
     * @param rskSid 施設区分SID
     */
    private void __setSisetuHeader(Rsv090knParamModel paramMdl, int rskSid) {
        GsMessage gsMsg = new GsMessage(reqMdl_);
        switch (rskSid) {
            //部屋
            case GSConstReserve.RSK_KBN_HEYA:
                paramMdl.setRsv090PropHeaderName1(gsMsg.getMessage("reserve.128"));
                paramMdl.setRsv090PropHeaderName2(gsMsg.getMessage("reserve.132"));
                paramMdl.setRsv090PropHeaderName6(gsMsg.getMessage("reserve.135"));
                paramMdl.setRsv090PropHeaderName7(gsMsg.getMessage("reserve.136"));
                break;
            //物品
            case GSConstReserve.RSK_KBN_BUPPIN:
                paramMdl.setRsv090PropHeaderName1(gsMsg.getMessage("reserve.130"));
                paramMdl.setRsv090PropHeaderName3(gsMsg.getMessage("reserve.133"));
                paramMdl.setRsv090PropHeaderName6(gsMsg.getMessage("reserve.135"));
                paramMdl.setRsv090PropHeaderName7(gsMsg.getMessage("reserve.136"));
                break;
            //車
            case GSConstReserve.RSK_KBN_CAR:
                paramMdl.setRsv090PropHeaderName1(gsMsg.getMessage("reserve.129"));
                paramMdl.setRsv090PropHeaderName2(gsMsg.getMessage("reserve.132"));
                paramMdl.setRsv090PropHeaderName4(gsMsg.getMessage("reserve.134"));
                paramMdl.setRsv090PropHeaderName6(gsMsg.getMessage("reserve.135"));
                paramMdl.setRsv090PropHeaderName7(gsMsg.getMessage("reserve.136"));
                break;
            //書籍
            case GSConstReserve.RSK_KBN_BOOK:
                paramMdl.setRsv090PropHeaderName1(gsMsg.getMessage("reserve.131"));
                paramMdl.setRsv090PropHeaderName3(gsMsg.getMessage("reserve.133"));
                paramMdl.setRsv090PropHeaderName5(GSConstReserve.RSK_TEXT_ISBN);
                paramMdl.setRsv090PropHeaderName6(gsMsg.getMessage("reserve.135"));
                paramMdl.setRsv090PropHeaderName7(gsMsg.getMessage("reserve.136"));
                break;
            //その他
            case GSConstReserve.RSK_KBN_OTHER:
                paramMdl.setRsv090PropHeaderName6(gsMsg.getMessage("reserve.135"));
                paramMdl.setRsv090PropHeaderName7(gsMsg.getMessage("reserve.136"));
                break;
            default:
                break;
        }
    }

    /**
     * <br>[機  能] 週間・日間で表示する画像を別のテンポラリディレクトリに移動させる
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv090knParamModel
     * @param sisetuTempDir 施設画像テンポラリディレクトリ
     * @param weekDayDsptempDir 週間・日間表示用画像テンポラリディレクトリ
     * @throws IOToolsException ファイルアクセス時例外
     * @throws IOException 添付ファイルの操作に失敗
     */
    public void setWeekDayImgOthTemp(Rsv090knParamModel paramMdl,
                                      String sisetuTempDir,
                                      String weekDayDsptempDir)
    throws IOToolsException, IOException {

        //週間・日間表示用画像ファイルのフルパス(オブジェクト)
        String dspPathObj = sisetuTempDir + "/"
                          + paramMdl.getRsv090SisetuImgDefoValue() + GSConstCommon.ENDSTR_OBJFILE;
        dspPathObj = IOTools.replaceFileSep(dspPathObj);
        log__.debug("週間・日間表示用画像ファイルのフルパス(オブジェクト) = " + dspPathObj);

        //週間・日間表示用画像ファイルのフルパス(本体)
        String dspPathFile = sisetuTempDir
                           + "/" + paramMdl.getRsv090SisetuImgDefoValue()
                           + GSConstCommon.ENDSTR_SAVEFILE;
        dspPathFile = IOTools.replaceFileSep(dspPathFile);
        log__.debug("週間・日間表示用画像ファイルのフルパス(本体) = " + dspPathFile);

        //ディレクトリ存在チェック(なければ作成)
        IOTools.isDirCheck(weekDayDsptempDir, true);

        //保存元のファイル
        File exObjFile = new File(dspPathObj);
        File exRealFile = new File(dspPathFile);

        //保存先のファイル
        File dspObjFile = new File(weekDayDsptempDir
                                  + paramMdl.getRsv090SisetuImgDefoValue()
                                  + GSConstCommon.ENDSTR_OBJFILE);
        File dspRealFile = new File(weekDayDsptempDir
                                  + paramMdl.getRsv090SisetuImgDefoValue()
                                  + GSConstCommon.ENDSTR_SAVEFILE);

        //添付ファイルをコピー
        IOTools.copyBinFile(exObjFile, dspObjFile);
        IOTools.copyBinFile(exRealFile, dspRealFile);

        //保存元ファイルを削除
        IOTools.deleteFile(exObjFile);
        IOTools.deleteFile(exRealFile);
    }

    /**
     * <br>[機  能] 添付ファイル情報をセット
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv090knParamModel
     * @param sisetuTempDir 施設画像テンポラリディレクトリ
     * @param placeTempDir 場所画像テンポラリディレクトリ
     * @param placeImgDataDir 場所画像データテンポラリディレクトリ
     * @param con コネクション
     * @throws IOToolsException
     * @throws IOToolsException ファイルアクセス時例外
     */
    public void setTempFiles(Rsv090knParamModel paramMdl,
                              String sisetuTempDir,
                              String placeTempDir,
                              String placeImgDataDir,
                              Connection con)

    throws IOToolsException {

        /** 画面に表示するファイルのリストを作成、セット **********************/

        CommonBiz commonBiz = new CommonBiz();
        paramMdl.setRsv090knSisetuFileLabelList(commonBiz.getTempFileLabelList(sisetuTempDir));

        if (paramMdl.getRsv090knSisetuFileLabelList() != null
                && paramMdl.getRsv090knSisetuFileLabelList().size() > 0) {
            //施設画像が登録されている場合
            paramMdl.setRsv090knSetImgFlg("1");
        }
        //場所・地図画像ファイル
        paramMdl.setRsv090knPlaceFileLabelList(commonBiz.getTempFileLabelList(placeTempDir));

        if (paramMdl.getRsv090knPlaceFileLabelList().size() <= 0) {
            return;
        }

        //場所・地図画像ファイルコメントと表示区分を取得
        for (int i = 1; i <= paramMdl.getRsv090knPlaceFileLabelList().size(); i++) {

            if (i == 1) {
                paramMdl.setRsv090knPlaceFileComment1(
                        getPlaceImgData(placeImgDataDir, 1).getRsv090PlaceFileComment());
                paramMdl.setRsv090knPlaceFileComment1DspKbn(
                        getPlaceImgData(placeImgDataDir, 1).getRsv090PlaceFileCommentDspKbn());
            } else if (i == 2) {
                paramMdl.setRsv090knPlaceFileComment2(
                        getPlaceImgData(placeImgDataDir, 2).getRsv090PlaceFileComment());
                paramMdl.setRsv090knPlaceFileComment2DspKbn(
                        getPlaceImgData(placeImgDataDir, 2).getRsv090PlaceFileCommentDspKbn());
            } else if (i == 3) {
                paramMdl.setRsv090knPlaceFileComment3(
                        getPlaceImgData(placeImgDataDir, 3).getRsv090PlaceFileComment());
                paramMdl.setRsv090knPlaceFileComment3DspKbn(
                        getPlaceImgData(placeImgDataDir, 3).getRsv090PlaceFileCommentDspKbn());
            } else if (i == 4) {
                paramMdl.setRsv090knPlaceFileComment4(
                        getPlaceImgData(placeImgDataDir, 4).getRsv090PlaceFileComment());
                paramMdl.setRsv090knPlaceFileComment4DspKbn(
                        getPlaceImgData(placeImgDataDir, 4).getRsv090PlaceFileCommentDspKbn());
            } else if (i == 5) {
                paramMdl.setRsv090knPlaceFileComment5(
                        getPlaceImgData(placeImgDataDir, 5).getRsv090PlaceFileComment());
                paramMdl.setRsv090knPlaceFileComment5DspKbn(
                        getPlaceImgData(placeImgDataDir, 5).getRsv090PlaceFileCommentDspKbn());
            } else if (i == 6) {
                paramMdl.setRsv090knPlaceFileComment6(
                        getPlaceImgData(placeImgDataDir, 6).getRsv090PlaceFileComment());
                paramMdl.setRsv090knPlaceFileComment6DspKbn(
                        getPlaceImgData(placeImgDataDir, 6).getRsv090PlaceFileCommentDspKbn());
            } else if (i == 7) {
                paramMdl.setRsv090knPlaceFileComment7(
                        getPlaceImgData(placeImgDataDir, 7).getRsv090PlaceFileComment());
                paramMdl.setRsv090knPlaceFileComment7DspKbn(
                        getPlaceImgData(placeImgDataDir, 7).getRsv090PlaceFileCommentDspKbn());
            } else if (i == 8) {
                paramMdl.setRsv090knPlaceFileComment8(
                        getPlaceImgData(placeImgDataDir, 8).getRsv090PlaceFileComment());
                paramMdl.setRsv090knPlaceFileComment8DspKbn(
                        getPlaceImgData(placeImgDataDir, 8).getRsv090PlaceFileCommentDspKbn());
            } else if (i == 9) {
                paramMdl.setRsv090knPlaceFileComment9(
                        getPlaceImgData(placeImgDataDir, 9).getRsv090PlaceFileComment());
                paramMdl.setRsv090knPlaceFileComment9DspKbn(
                        getPlaceImgData(placeImgDataDir, 9).getRsv090PlaceFileCommentDspKbn());
            } else if (i == 10) {
                paramMdl.setRsv090knPlaceFileComment10(
                        getPlaceImgData(placeImgDataDir, 10).getRsv090PlaceFileComment());
                paramMdl.setRsv090knPlaceFileComment10DspKbn(
                        getPlaceImgData(placeImgDataDir, 10).getRsv090PlaceFileCommentDspKbn());
            }
        }
    }

    /**
     * <br>[機  能] オブジェクトファイルから場所画像データを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param filePath ファイルパス
     * @param index インデックス
     * @return Rsv090PlaceForm
     * @throws IOToolsException IOエラー
     */
    public Rsv090PlaceForm getPlaceImgData(
        String filePath, int index) throws IOToolsException {

        Rsv090PlaceForm bean = new Rsv090PlaceForm();

        //オブジェクトファイルを取得
        if (!IOTools.isFileCheck(filePath, GSConstReserve.SAVE_FILENAME + index, false)) {
            return bean;
        }

        ObjectFile objFile = new ObjectFile(filePath, GSConstReserve.SAVE_FILENAME + index);
        Object paramMdlData = objFile.load();
        if (paramMdlData == null) {
            return bean;
        }

        //場所画像データ
        return (Rsv090PlaceForm) paramMdlData;
    }

    /**
     * <br>[機  能] [施設予約の承認]を設定可能かを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Rsv090knParamModel
     * @return true:設定可能 false:設定不可
     * @throws SQLException SQL実行時例外
     */
    private boolean __canEditApprKbn(Rsv090knParamModel paramMdl) throws SQLException {
        boolean result = false;

        int rsgSid = paramMdl.getRsv090EditGrpSid();
        RsvSisGrpDao grpDao = new RsvSisGrpDao(con_);

        //施設グループに管理者が設定されているかを判定
        ArrayList<String> grpAdmUsers = grpDao.getDefaultAdmUser(rsgSid);
        if (grpAdmUsers != null && !grpAdmUsers.isEmpty()) {
            //施設ごとに施設予約の承認設定が可能かを判定
            RsvCommonBiz rsvCmnBiz = new RsvCommonBiz();
            result = !rsvCmnBiz.isApprSisGroup(con_, rsgSid);
        }

        return result;
    }
}