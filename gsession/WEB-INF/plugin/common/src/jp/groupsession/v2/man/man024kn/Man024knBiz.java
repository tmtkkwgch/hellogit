package jp.groupsession.v2.man.man024kn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.dao.base.CmnHolidayDao;
import jp.groupsession.v2.cmn.dao.base.CmnHolidayTemplateDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnHolidayModel;
import jp.groupsession.v2.cmn.model.base.CmnHolidayTemplateModel;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.man.MaintenanceUtil;
import jp.groupsession.v2.man.man023.Man023HolidayTemplateModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] メイン 管理者設定 テンプレートから追加確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 * @author JTS
 */
public class Man024knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Man024knBiz.class);

    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     */
    public Man024knBiz() { }


    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     */
    public Man024knBiz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示画面情報を設定します
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void getInitData(Man024knParamModel paramMdl, Connection con)
    throws SQLException {
        log__.debug("START");

        CmnHolidayTemplateDao htDao = new CmnHolidayTemplateDao(con);
        List<Man023HolidayTemplateModel> viewList
            = new ArrayList<Man023HolidayTemplateModel>();

        int countReflect = 0;
        List<CmnHolidayTemplateModel> holidayList
            = htDao.selectSortedHoliday(paramMdl.getMan023hltSid());
        GsMessage gsMsg = new GsMessage(reqMdl__);

        for (CmnHolidayTemplateModel model : holidayList) {
            Man023HolidayTemplateModel viewModel = new Man023HolidayTemplateModel();
            Man023HolidayTemplateModel hurikaeViewModel = null;

            boolean bolNGflg = true;
            boolean flghuriExsist = false;

            String dspFurikaeString = "";
            //「テンプレートSID」作成
            int tmpSid = model.getHltSid();
            viewModel.setHltSid(tmpSid);

            UDate date = new UDate();
            UDate huriDate = new UDate();
            //「休日文字列」作成
            int hltExflg = model.getHltExflg();
            String dspString = "";
            int intSetMon = -1;
            int intSetDay = -1;

            viewModel.setHltExflg(model.getHltExflg());
            if (hltExflg == CmnHolidayTemplateModel.HLT_EXFLG_NORMAL) {
                dspString = model.getHltDateMonth()
                            + gsMsg.getMessage("cmn.month")
                            + "　"
                            + model.getHltDateDay() + gsMsg.getMessage("cmn.day");
                intSetMon = model.getHltDateMonth();
                intSetDay = model.getHltDateDay();
                date.setDate(Integer.parseInt(paramMdl.getMan020DspYear()) ,
                        model.getHltDateMonth(), intSetDay);

                viewModel.setHltDateMonth(model.getHltDateMonth());

                //振替用
                huriDate.setDate(Integer.parseInt(paramMdl.getMan020DspYear()) ,
                        model.getHltDateMonth(), intSetDay);

                int intCompMon = model.getHltDateMonth();
                bolNGflg = false;

                //閏年判定
                if (date.getMonth() != intCompMon) {
                    log__.debug("date.getMonth()" + date.getMonth());
                    log__.debug("intCompMon" + intCompMon);
                    bolNGflg = true;

                    //打消し線セット
                    __setViewParts(dspString, viewModel, model, date,
                            paramMdl, bolNGflg, viewList);
                    continue;
                }

            } else {
                String strMon = Integer.toString(model.getHltExMonth());
                String strSyu = Integer.toString(model.getHltExWeekMonth());
                String strYoubi =
                    MaintenanceUtil.getStrYoubiForCalendar(model.getHltExDayWeek(), reqMdl__);

                date.setYear(Integer.parseInt(paramMdl.getMan020DspYear()));

                //31日が存在しない月対策用
                date.setDay(1);

                date.setMonth(model.getHltExMonth());
                boolean result = MaintenanceUtil.isAccurateWeekOfMonth(date,
                              MaintenanceUtil.getIntYoubiForCalendar(model.getHltExDayWeek()));
                if (result) {
                    date.setWeek(model.getHltExWeekMonth());
                } else {
                    date.setWeek(model.getHltExWeekMonth() + 1);
                }
                date.setDayOfWeek(MaintenanceUtil.getIntYoubiForCalendar(model.getHltExDayWeek()));

                log__.debug("form.getMan020DspYear()" + paramMdl.getMan020DspYear());
                log__.debug("model.getHltExMonth()" + model.getHltExMonth());
                log__.debug("model.getHltExWeekMonth()" + model.getHltExWeekMonth());
                log__.debug("model.getHltExDayWeek()" + model.getHltExDayWeek());
                log__.debug("生成日付" + date.getDateString());

                viewModel.setHltExMonth(model.getHltExMonth());

                //振替用
                huriDate.setDate(date.getDateString());

                intSetMon = date.getMonth();
                intSetDay = date.getIntDay();
                dspString = "（" + strMon
                                 + gsMsg.getMessage("cmn.month")
                                 + MaintenanceUtil.getWeek(Integer.parseInt(strSyu), reqMdl__)
                                 + strYoubi + "）";
                if (intSetMon == Integer.parseInt(strMon)) {
                    dspString = intSetMon
                                + gsMsg.getMessage("cmn.month")
                                + "　"
                                + intSetDay + gsMsg.getMessage("cmn.day") + dspString;
                    bolNGflg = false;
                } else {
                    bolNGflg = true;
                    __setViewParts(dspString, viewModel, model, date,
                            paramMdl, bolNGflg, viewList);
                    continue;
                }
            }

            //ノーマルと拡張で重複日付の存在チェック
            if (!bolNGflg && model.getHltExflg() == 1) {
                bolNGflg = __isDuplicateDateNormalKakucho(date, holidayList);
                if (bolNGflg) {
                    __setViewParts(dspString, viewModel, model, date,
                            paramMdl, bolNGflg, viewList);
                    continue;
                }
            }

            //振替休日処理
            if ((date.getWeek() == Calendar.SUNDAY) && model.getHltExFurikae() == 0) {
                huriDate.setDay(intSetDay + 1);
                CmnHolidayTemplateModel holiHuriModel = new CmnHolidayTemplateModel();
                holiHuriModel.setHltDateMonth(huriDate.getMonth());
                holiHuriModel.setHltExMonth(huriDate.getMonth());
                holiHuriModel.setHltDateDay(huriDate.getIntDay());
                holiHuriModel.setHltExDayWeek(
                        huriDate.getWeek());

                log__.debug("曜日カレンダー :" + huriDate.getWeek());
                holiHuriModel.setHltExWeekMonth(huriDate.getWeekOfMonth());
                log__.debug("月" + holiHuriModel.getHltDateMonth());
                log__.debug("日" + holiHuriModel.getHltDateDay());
                log__.debug("月" + holiHuriModel.getHltExMonth());
                log__.debug("曜" + holiHuriModel.getHltExDayWeek());
                log__.debug("週" + holiHuriModel.getHltExWeekMonth());

                if (hltExflg == CmnHolidayTemplateModel.HLT_EXFLG_NORMAL) {
                    dspFurikaeString = holiHuriModel.getHltDateMonth()
                                        + gsMsg.getMessage("cmn.month")
                                        + "　"
                                        + holiHuriModel.getHltDateDay()
                                        + gsMsg.getMessage("cmn.day");
                    flghuriExsist = true;
                    //振替時の画面セットモデル設定
                    hurikaeViewModel = __addHurikaeViewSet(hurikaeViewModel, dspFurikaeString,
                            huriDate, bolNGflg, htDao, holiHuriModel);

                    hurikaeViewModel.setHltDateMonth(huriDate.getMonth());
                } else {
                    String strMon = Integer.toString(model.getHltExMonth());
                    String strYoubi =
                        MaintenanceUtil.getStrYoubiFromCalendar(
                                holiHuriModel.getHltExDayWeek(), reqMdl__);

//                    hurikaeViewModel.setHltExMonth(huriDate.getMonth());
                    dspFurikaeString = "（" + strMon
                                            + gsMsg.getMessage("cmn.month")
                                            + MaintenanceUtil.getWeek(
                                                    model.getHltExWeekMonth(), reqMdl__)
                                            + strYoubi + "）";
                    if (intSetMon == Integer.parseInt(strMon)) {
                        log__.debug("intSetMon" + intSetMon);
                        log__.debug("strMon" + strMon);
                        intSetDay = huriDate.getIntDay();
                        dspFurikaeString = intSetMon
                                           + gsMsg.getMessage("cmn.month")
                                           + "　"
                                           + intSetDay
                                           + gsMsg.getMessage("cmn.day") + dspFurikaeString;
                        flghuriExsist = true;
                        //振替時の画面セットモデル設定
                        hurikaeViewModel = __addHurikaeViewSet(hurikaeViewModel, dspFurikaeString,
                                huriDate, bolNGflg, htDao, holiHuriModel);
                    } else {
                        bolNGflg = true;
                        flghuriExsist = false;
                        hurikaeViewModel = null;
                    }
                }

              //振替休日の重複チェック
              if (hurikaeViewModel != null && flghuriExsist) {
                  boolean result = __isDuplicateDateHurikaeHoliday(
                          hurikaeViewModel, holidayList, paramMdl);
                  if (result) {
                      log__.debug("重複時振替処理飛ばし");
                      hurikaeViewModel = null;
                      flghuriExsist = false;
                      bolNGflg = false;
                  }
              }

                //既存データチェック「※」表示制御
                if (hurikaeViewModel != null && flghuriExsist) {
                    __setAsterisk(hurikaeViewModel, con, huriDate);
                }
            }

            //既存データチェック「※」表示制御
            __setAsterisk(viewModel, con, date);

            //表示用部品セット
            __setViewParts(dspString, viewModel, model, date,
                    paramMdl, bolNGflg, viewList);
            if (hurikaeViewModel != null && flghuriExsist) {
                viewList.add(hurikaeViewModel);
            }

            //反映可能件数カウント
            if (!bolNGflg) {
                countReflect++;
            }

        }
        log__.debug("viewList.size()" + viewList.size());
        Man023HolidayTemplateModel[] viewArray = (Man023HolidayTemplateModel[])
                    viewList.toArray(new Man023HolidayTemplateModel[viewList.size()]);

        Arrays.sort(viewArray);
        paramMdl.setMan023TemplateList(viewArray);
        paramMdl.setCountReflect(countReflect);     //反映可能件数

        log__.debug("END");
    }

    /**
     * <br>[機  能] 休日テンプレートより休日の登録を行います
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param sessionSid セッションユーザ
     * @return true:登録成功 false:登録失敗
     * @throws SQLException SQL実行時例外
     */
    public boolean execDataTran(Man024knParamModel paramMdl,
                                Connection con, int sessionSid)
    throws SQLException {

        boolean commitFlg = false;
        CmnHolidayDao holiDao = new CmnHolidayDao(con);
        CmnHolidayTemplateDao tmpDao = new CmnHolidayTemplateDao(con);

        UDate now = new UDate();
        int[] sids = paramMdl.getMan024knOKSid();
        String[] asterisks = paramMdl.getMan024knAsterisks();

        try {
            for (int i = 0; i < sids.length; i++) {
                CmnHolidayTemplateModel tmpModel = new CmnHolidayTemplateModel();
                tmpModel.setHltSid(sids[i]);
                tmpModel = tmpDao.select(tmpModel);
                CmnHolidayModel holiModel = new CmnHolidayModel();

                int year = Integer.parseInt(paramMdl.getMan020DspYear());
                UDate hltDate = new UDate();
                hltDate.setDate(paramMdl.getMan024knDateValue()[i]);
                holiModel.setHolDate(hltDate);
                holiModel.setHolYear(year);
                holiModel.setHolName(paramMdl.getMan024knNameValue()[i]);
                holiModel.setHolUpuser(sessionSid);
                holiModel.setHolUpdate(now);

                if (asterisks[i].equals("1")) {
                    holiDao.updateHoliday(holiModel, hltDate);
                } else {
                    holiModel.setHolAduser(sessionSid);
                    holiModel.setHolAddate(now);
                    holiDao.insertHoliday(holiModel);
                }

            }
            commitFlg = true;
        } catch (SQLException e) {
            GsMessage gsMsg = new GsMessage(reqMdl__);
            log__.error(gsMsg.getMessage(GSConstMain.HOLIDAY_MSG) + "の登録に失敗", e);
            throw e;
        } finally {
            if (commitFlg) {
                con.commit();
            } else {
                JDBCUtil.rollback(con);
            }
        }

        return commitFlg;
    }

    /**
     * <br>[機  能] 振替時の各種画面設定を行います
     * <br>[解  説]
     * <br>[備  考]
     * @param hurikaeViewModel Man023HolidayTemplateModel
     * @param dspFurikaeString 表示用文字列
     * @param huriDate 振替日用UDate
     * @param bolNGflg 打消し表示フラグ
     * @param htDao CmnHolidayTemplateDao
     * @param model CmnHolidayTemplateModel
     * @return Man023HolidayTemplateModel
     * @throws SQLException SQL実行時例外
     */
    private Man023HolidayTemplateModel __addHurikaeViewSet(
            Man023HolidayTemplateModel hurikaeViewModel,
            String dspFurikaeString,
            UDate huriDate,
            boolean bolNGflg,
            CmnHolidayTemplateDao htDao,
            CmnHolidayTemplateModel model
            ) throws SQLException {
        log__.debug("START");

        hurikaeViewModel = new Man023HolidayTemplateModel();
        GsMessage gsMsg = new GsMessage(reqMdl__);
        hurikaeViewModel.setHltSid(-1);
        hurikaeViewModel.setHltName(gsMsg.getMessage("main.src.man024kn.4"));
        hurikaeViewModel.setViewDate(dspFurikaeString);
        hurikaeViewModel.setDate(Integer.parseInt(huriDate.getDateString()));
        hurikaeViewModel.setStrDate(huriDate.getDateString()); //８桁日付保持
        hurikaeViewModel.setFlgNGDate(bolNGflg);
        hurikaeViewModel.setHltExflg(model.getHltExflg());
        log__.debug("END");
        return hurikaeViewModel;
    }

    /**
     * <br>[機  能] ノーマル日付と拡張日付での重複日付チェックをします
     * <br>[解  説]
     * <br>[備  考]
     * @param date UDate
     * @param hltList テンプレートモデルリスト
     * @return boolean true: 重複  false: 単一
     * @throws SQLException SQL実行時例外
     */
    private boolean __isDuplicateDateNormalKakucho(UDate date,
            List < CmnHolidayTemplateModel > hltList) throws SQLException {
        log__.debug("START");
        boolean bolNGflg = false;

        for (CmnHolidayTemplateModel hltMdl : hltList) {
            log__.debug("Month = " + date.getMonth());
            log__.debug("Date = " + date.getIntDay());
            log__.debug("Month2 = " + hltMdl.getHltDateMonth());
            log__.debug("Date2 = " + hltMdl.getHltDateDay());
            log__.debug("exFlg = " + hltMdl.getHltExflg());
            if (hltMdl.getHltExflg() == 1) {
                continue;
            }

            if (date.getMonth() == hltMdl.getHltDateMonth()
                    && date.getIntDay() == hltMdl.getHltDateDay()) {
                log__.debug("重複チェックOUT");
                return true;
            }
        }
        log__.debug("END");
        return bolNGflg;
    }

    /**
     * <br>[機  能] 振替休日同士の重複日付チェックをします
     * <br>[解  説]
     * <br>[備  考]
     * @param furikaeMdl CmnHolidayTemplateModel
     * @param hltList テンプレートリストモデル
     * @param paramMdl パラメータ情報
     * @return boolean true: 重複  false: 単一
     * @throws SQLException SQL実行時例外
     */
    private boolean __isDuplicateDateHurikaeHoliday(Man023HolidayTemplateModel furikaeMdl,
                                                    List < CmnHolidayTemplateModel > hltList,
                                                    Man024knParamModel paramMdl)
    throws SQLException {

        log__.debug("START");
        boolean bolNGflg = false;

        UDate date = new UDate();
        date.setTime(0);
        date.setDate(String.valueOf(furikaeMdl.getDate()));
        for (CmnHolidayTemplateModel hltMdl : hltList) {
            log__.debug("---------------------------");
            log__.debug("Month = " + furikaeMdl.getMonth());
            log__.debug("Day = " + date.getIntDay());
            log__.debug("date1 = " + date.getDateString());

            log__.debug("Month2 = " + hltMdl.getHltDateMonth());
            log__.debug("Day2 = " + hltMdl.getHltDateDay());
            log__.debug("exFlg = " + hltMdl.getHltExflg());

            if (hltMdl.getHltExflg() == 0) {
                if (date.getMonth() == hltMdl.getHltDateMonth()
                        && date.getIntDay() == hltMdl.getHltDateDay()) {
                    log__.debug("Exit check! error （拡張区分0）");
                    return true;
                }
            } else if (hltMdl.getHltExflg() == 1) {
                UDate dateEx = new UDate();
                dateEx.setYear(Integer.parseInt(paramMdl.getMan020DspYear()));
                dateEx.setMonth(hltMdl.getHltExMonth());
                dateEx.setWeek(hltMdl.getHltExWeekMonth());
                dateEx.setDayOfWeek(
                        MaintenanceUtil.getIntYoubiForCalendar(hltMdl.getHltExDayWeek()));
                if (date.getMonth() == dateEx.getMonth()
                        && date.getIntDay() == dateEx.getIntDay()) {
                    log__.debug("Exit check! error （拡張区分1）");
                    return true;
                }
            }
        }
        log__.debug("END");
        return bolNGflg;
    }

    /**
     * <br>[機  能] 既存データチェックを行い「※」表示制御する
     * <br>[解  説]
     * <br>[備  考]
     * @param viewModel Man023HolidayTemplateModel
     * @param con Connection
     * @param date UDate
     * @throws SQLException SQL実行時例外
     */
    private void __setAsterisk(Man023HolidayTemplateModel viewModel,
            Connection con,
            UDate date) throws SQLException {
        log__.debug("START");

        //既存データチェック「※」表示制御
        CmnHolidayDao holiDao = new CmnHolidayDao(con);
        CmnHolidayModel holiModel = new CmnHolidayModel();
        holiModel.setHolDate(date);
        holiModel = holiDao.select(holiModel);
        if (holiModel != null) {
            viewModel.setAsterisk("1");
        }
        log__.debug("END");
    }

    /**
     * <br>[機  能] 画面表示に必要な項目をセットします
     * <br>[解  説]
     * <br>[備  考]
     * @param dspString 表示文字列
     * @param viewModel 表示モデル
     * @param model CmnHolidayTemplateModel
     * @param date UDate
     * @param paramMdl パラメータ情報
     * @param bolNGflg 打消し線表示可否
     * @param viewList 表示モデルリスト
     */
    private void __setViewParts(String dspString,
            Man023HolidayTemplateModel viewModel,
            CmnHolidayTemplateModel model,
            UDate date,
            Man024knParamModel paramMdl,
            boolean bolNGflg,
            List < Man023HolidayTemplateModel > viewList) {
        viewModel.setViewDate(dspString);

        log__.debug("START");

        //「休日名文字列」作成
        String holiday = "";
        holiday = model.getHltName();
        viewModel.setHltName(holiday);

        //リストへセット
        date = new UDate();
        date.setTime(0);
        if (model.getHltExflg() == 0) {
            date.setYear(Integer.parseInt(paramMdl.getMan020DspYear()));
            date.setMonth(model.getHltDateMonth());
            date.setDay(model.getHltDateDay());
        } else {
            date.setYear(Integer.parseInt(paramMdl.getMan020DspYear()));
            date.setMonth(model.getHltExMonth());
            boolean result = MaintenanceUtil.isAccurateWeekOfMonth(date,
                    MaintenanceUtil.getIntYoubiForCalendar(model.getHltExDayWeek()));
            if (result) {
                date.setWeek(model.getHltExWeekMonth());
            } else {
              date.setWeek(model.getHltExWeekMonth() + 1);
            }
            //date.setWeek(model.getHltExWeekMonth());
            date.setDayOfWeek(MaintenanceUtil
                    .getIntYoubiForCalendar(model.getHltExDayWeek()));
        }
        viewModel.setDate(Integer.parseInt(date.getDateString()));

        viewModel.setFlgNGDate(bolNGflg);
        viewModel.setStrDate(date.getDateString()); //８桁日付保持
        log__.debug("NGflg :" + viewModel.getFlgNGDate());
        log__.debug("日付 :" + viewModel.getViewDate());
        viewList.add(viewModel);

        log__.debug("END");
    }
}
