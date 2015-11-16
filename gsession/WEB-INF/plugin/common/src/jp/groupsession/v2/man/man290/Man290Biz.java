package jp.groupsession.v2.man.man290;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.UsidSelectGrpNameDao;
import jp.groupsession.v2.cmn.dao.base.CmnCmbsortConfDao;
import jp.groupsession.v2.cmn.dao.base.CmnInfoBinDao;
import jp.groupsession.v2.cmn.dao.base.CmnInfoMsgDao;
import jp.groupsession.v2.cmn.dao.base.CmnInfoTagDao;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.cmn.model.base.CmnCmbsortConfModel;
import jp.groupsession.v2.cmn.model.base.CmnInfoMsgModel;
import jp.groupsession.v2.cmn.model.base.CmnInfoTagModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.man.MaintenanceUtil;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] メイン インフォメーション登録画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man290Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Man290Biz.class);

    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     */
    public Man290Biz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示画面情報を設定します
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param appRoot ROOTパス
     * @param tempDir テンポラリDIR
     * @param con コネクション
     * @param domain ドメイン
     * @param cmd 操作種別パラメータ
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException 添付ファイル処理例外
     * @throws TempFileException 添付ファイル処理例外
     * @throws IOException  添付ファイル処理例外
     */
    public void setInitData(Man290ParamModel paramMdl,
                           String appRoot, String tempDir, Connection con, String domain,
                           String cmd)
    throws SQLException, IOToolsException, TempFileException, IOException {

        CommonBiz cmnBiz = new CommonBiz();
        int imsSid = NullDefault.getInt(paramMdl.getMan320SelectedSid(), -1);
        CmnInfoMsgDao infoDao = new CmnInfoMsgDao(con);
        CmnInfoMsgModel infoMdl = infoDao.select(imsSid);

        UDate dspDate = new UDate();
        if (infoMdl != null) {
            dspDate = infoMdl.getImsFrDate();
        }

        //新規モード
        UDate frDate = null;
        UDate toDate = null;
        frDate = new UDate();
        toDate = new UDate();
        frDate.setHour(GSConstMain.DF_FROM_HOUR);
        frDate.setMinute(GSConstMain.DF_FROM_MINUTES);
        toDate.setHour(GSConstMain.DF_TO_HOUR);
        toDate.setMinute(GSConstMain.DF_TO_MINUTES);

        if (cmd.equals("man320edit") && infoMdl != null) { //編集モード
            int man290FrYear = infoMdl.getImsFrDate().getYear();
            int man290FrMonth = infoMdl.getImsFrDate().getMonth();
            int man290FrDay = infoMdl.getImsFrDate().getIntDay();
            int man290ToYear = infoMdl.getImsToDate().getYear();
            int man290ToMonth = infoMdl.getImsToDate().getMonth();
            int man290ToDay = infoMdl.getImsToDate().getIntDay();

            paramMdl.setMan290FrYear(
                    NullDefault.getString(String.valueOf(man290FrYear),
                            String.valueOf(frDate.getYear())));
            paramMdl.setMan290FrMonth(
                    NullDefault.getString(String.valueOf(man290FrMonth),
                            String.valueOf(frDate.getMonth())));
            paramMdl.setMan290FrDay(
                    NullDefault.getString(String.valueOf(man290FrDay),
                            String.valueOf(frDate.getIntDay())));

            paramMdl.setMan290ToYear(
                    NullDefault.getString(String.valueOf(man290ToYear),
                            String.valueOf(toDate.getYear())));
            paramMdl.setMan290ToMonth(
                    NullDefault.getString(String.valueOf(man290ToMonth),
                            String.valueOf(toDate.getMonth())));
            paramMdl.setMan290ToDay(
                    NullDefault.getString(String.valueOf(man290ToDay),
                            String.valueOf(toDate.getIntDay())));

            //時間
            int man290FrHour = infoMdl.getImsFrDate().getIntHour();
            int man290FrMinute = infoMdl.getImsFrDate().getIntMinute();
            int man290ToHour = infoMdl.getImsToDate().getIntHour();
            int man290ToMinute = infoMdl.getImsToDate().getIntMinute();

            paramMdl.setMan290FrHour(
                    NullDefault.getString(String.valueOf(man290FrHour),
                    String.valueOf(frDate.getIntHour())));
            paramMdl.setMan290FrMin(
                    NullDefault.getString(String.valueOf(man290FrMinute),
                            String.valueOf(frDate.getIntMinute())));
            paramMdl.setMan290ToHour(
                    NullDefault.getString(String.valueOf(man290ToHour),
                            String.valueOf(toDate.getIntHour())));
            paramMdl.setMan290ToMin(
                    NullDefault.getString(String.valueOf(man290ToMinute),
                            String.valueOf(toDate.getIntMinute())));

            //メッセージ
            paramMdl.setMan290Msg(infoMdl.getImsMsg());
            //内容
            paramMdl.setMan290Value(infoMdl.getImsValue());

            CmnInfoTagDao tagDao = new CmnInfoTagDao(con);
            ArrayList<CmnInfoTagModel> tagMdlList = null;
            ArrayList<String> list = new ArrayList<String>();
            tagMdlList = tagDao.select(imsSid);

            for (int i = 0; i < tagMdlList.size(); i++) {
                if (tagMdlList.get(i).getUsrSid() != -1) {
                    list.add(String.valueOf(tagMdlList.get(i).getUsrSid()));
                }

                if (tagMdlList.get(i).getGrpSid() != -1) {
                    list.add(String.valueOf("G"
                                            + tagMdlList.get(i).getGrpSid()));
                }

            }
            String[] usrGrpSids = (String[]) list.toArray(new String[list.size()]);
            paramMdl.setMan290memberSid(usrGrpSids);

            //拡張区分
            paramMdl.setMan290ExtKbn(
                    NullDefault.getString(paramMdl.getMan290ExtKbn(),
                            String.valueOf(infoMdl.getImsKbn())));
            if (infoMdl.getImsKbn() > 1) {
                paramMdl.setMan290elementKbn(1);
            }
            paramMdl.setMan290HolKbn(infoMdl.getImsHolkbn());

            //週
            paramMdl.setMan290Week(
                    NullDefault.getString(paramMdl.getMan290Week(),
                            String.valueOf(infoMdl.getImsWeek())));
            //日
            paramMdl.setMan290Day(
                    NullDefault.getString(paramMdl.getMan290Day(),
                            String.valueOf(infoMdl.getImsDay())));
            //曜日
            if (paramMdl.getMan290Dweek() == null
            || paramMdl.getMan290Dweek().length <= 0) {
                __setDayWeekToForm(paramMdl, infoMdl);
            }

            CmnInfoBinDao binDao = new CmnInfoBinDao(con);
            ArrayList<CmnBinfModel> tmpFileList = binDao.getBinList(infoMdl.getImsSid());

            //添付ファイルをテンポラリディレクトリへ移動する
            IOTools.deleteDir(tempDir);
            String dateStr = (new UDate()).getDateString(); //現在日付の文字列(YYYYMMDD)
            if (tmpFileList != null && tmpFileList.size() > 0) {
                String[] binSids = new String[tmpFileList.size()];

                //バイナリSIDの取得
                for (int i = 0; i < tmpFileList.size(); i++) {
                    binSids[i] = String.valueOf(tmpFileList.get(i).getBinSid());
                }

                //取得したバイナリSIDからバイナリ情報を取得
                List<CmnBinfModel> binList = cmnBiz.getBinInfo(con, binSids, domain);

                int fileNum = 1;
                for (CmnBinfModel binData : binList) {
                    cmnBiz.saveTempFile(dateStr, binData, appRoot, tempDir, fileNum);
                    fileNum++;
                }
            }

        } else { //新規
            //日付
            paramMdl.setMan290FrYear(
                    NullDefault.getString(paramMdl.getMan290FrYear(),
                            String.valueOf(frDate.getYear())));
            paramMdl.setMan290FrMonth(
                    NullDefault.getString(paramMdl.getMan290FrMonth(),
                            String.valueOf(frDate.getMonth())));
            paramMdl.setMan290FrDay(
                    NullDefault.getString(paramMdl.getMan290FrDay(),
                            String.valueOf(frDate.getIntDay())));

            paramMdl.setMan290ToYear(
                    NullDefault.getString(paramMdl.getMan290ToYear(),
                            String.valueOf(toDate.getYear())));
            paramMdl.setMan290ToMonth(
                    NullDefault.getString(paramMdl.getMan290ToMonth(),
                            String.valueOf(toDate.getMonth())));
            paramMdl.setMan290ToDay(
                    NullDefault.getString(paramMdl.getMan290ToDay(),
                            String.valueOf(toDate.getIntDay())));
            //時間
            paramMdl.setMan290FrHour(
                    NullDefault.getString(paramMdl.getMan290FrHour(),
                    String.valueOf(frDate.getIntHour())));
            paramMdl.setMan290FrMin(
                    NullDefault.getString(paramMdl.getMan290FrMin(),
                            String.valueOf(frDate.getIntMinute())));
            paramMdl.setMan290ToHour(
                    NullDefault.getString(paramMdl.getMan290ToHour(),
                            String.valueOf(toDate.getIntHour())));
            paramMdl.setMan290ToMin(
                    NullDefault.getString(paramMdl.getMan290ToMin(),
                            String.valueOf(toDate.getIntMinute())));

            //拡張区分
            paramMdl.setMan290ExtKbn(
                    NullDefault.getString(paramMdl.getMan290ExtKbn(),
                            String.valueOf(GSConstMain.INFO_KBN_DAY)));
            //週
            paramMdl.setMan290Week(
                    NullDefault.getString(paramMdl.getMan290Week(),
                            String.valueOf(-1)));
            //日
            paramMdl.setMan290Day(
                    NullDefault.getString(paramMdl.getMan290Day(),
                            String.valueOf(-1)));

            if (cmd.equals("man320add")) {
                IOTools.deleteDir(tempDir);
            }
        }

        //添付ファイル一覧を設定
        paramMdl.setMan290FileLabelList(cmnBiz.getTempFileLabelList(tempDir));
        //グループラベル
        ArrayList<LabelValueBean> gpLabelList = __getGroupLabelList(con);
        paramMdl.setMan290GroupList(gpLabelList);

        //グループコンボ選択値
        int infoSltGp = paramMdl.getMan290groupSid();
        List<LabelValueBean> labelListAdd = new ArrayList<LabelValueBean>();

        if (infoSltGp == Integer.parseInt(GSConstMain.GROUP_COMBO_VALUE)) {
            //グループを全て取得
            GroupDao dao = new GroupDao(con);
            CmnCmbsortConfDao sortDao = new CmnCmbsortConfDao(con);
            CmnCmbsortConfModel sortMdl = sortDao.getCmbSortData();
            ArrayList<GroupModel> allGpList = dao.getGroupTree(sortMdl);
            //除外するグループSID
            List<String> fullGrepList = new ArrayList<String>();
            if (paramMdl.getMan290memberSid() != null) {
                fullGrepList = Arrays.asList(paramMdl.getMan290memberSid());
            }
            for (GroupModel bean : allGpList) {
                if (!fullGrepList.contains(String.valueOf("G" + bean.getGroupSid()))) {
                    labelListAdd.add(new LabelValueBean(
                            bean.getGroupName(), String.valueOf("G" + bean.getGroupSid())));
                }
            }

        } else {
            //追加用ユーザを取得する
            ArrayList<Integer> usrSids = new ArrayList<Integer>();
            for (String str : paramMdl.getMan290memberSid()) {
                usrSids.add(new Integer(NullDefault.getInt(str, -1)));
            }
            UserBiz userBiz = new UserBiz();
            List<CmnUsrmInfModel> usList
                = userBiz.getBelongUserList(con, paramMdl.getMan290groupSid(), usrSids);
            for (int i = 0; i < usList.size(); i++) {
                CmnUsrmInfModel cuiMdl = usList.get(i);
                labelListAdd.add(new LabelValueBean(cuiMdl.getUsiSei() + cuiMdl.getUsiMei(),
                                 String.valueOf(cuiMdl.getUsrSid())));
            }
        }

        //候補
        paramMdl.setMan290RightUserList(labelListAdd);
        //メンバ追加一覧
        paramMdl.setMan290LeftUserList(__getInfoFullLabel(paramMdl, con));

        //共通項目
        //拡張設定 日コンボを作成
        paramMdl.setMan290ExDayLabel(getDayLabel(true));
        //拡張設定 週コンボを作成
        paramMdl.setMan290WeekLabel(getWeekLabel());

        //年コンボを作成
        paramMdl.setMan290YearLabel(getYearLabel(dspDate.getYear()));
        //月コンボを作成
        paramMdl.setMan290MonthLabel(getMonthLabel());
        //日コンボを作成
        paramMdl.setMan290DayLabel(getDayLabel(false));
       //時コンボを作成
        paramMdl.setMan290HourLabel(getHourLabel());
        //分コンボを作成
        paramMdl.setMan290MinuteLabel(getMinuteLabel(con));
    }

    /**
     * <br>[機  能] 表示開始日から年コンボを生成します
     * <br>[解  説]
     * <br>[備  考]
     * @param year 基準年
     * @return ArrayList (in LabelValueBean)  年コンボ
     */
    public ArrayList<LabelValueBean> getYearLabel(int year) {
        year--;
        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        GsMessage gsMsg = new GsMessage(reqMdl__);
        for (int i = 0; i < GSConstMain.YEAR_LIST_CNT; i++) {
            String strYear = String.valueOf(year);
            labelList.add(
                    new LabelValueBean(
                            gsMsg.getMessage("cmn.year", new String[] {strYear}), strYear));
            year++;
        }
        return labelList;
    }

    /**
     * <br>[機  能] 月コンボを生成します
     * <br>[解  説]
     * <br>[備  考]
     * @return ArrayList (in LabelValueBean)  月コンボ
     */
    public ArrayList<LabelValueBean> getMonthLabel() {
        int month = 1;
        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        GsMessage gsMsg = new GsMessage(reqMdl__);
        for (int i = 0; i < 12; i++) {
            labelList.add(
                    new LabelValueBean(
                            month + gsMsg.getMessage("cmn.month"), String.valueOf(month)));
            month++;
        }
        return labelList;
    }

    /**
     * <br>[機  能] 日コンボを生成します
     * <br>[解  説]
     * <br>[備  考]
     * @param exFlg 拡張フラグ
     * @return ArrayList (in LabelValueBean)  日コンボ
     */
    public ArrayList<LabelValueBean> getDayLabel(boolean exFlg) {
        int day = 1;
        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        GsMessage gsMsg = new GsMessage(reqMdl__);
        if (exFlg) {
            labelList.add(
                    new LabelValueBean(gsMsg.getMessage("cmn.noset2"), String.valueOf(0)));
        }
        for (int i = 0; i < 31; i++) {
            labelList.add(
                    new LabelValueBean(day + gsMsg.getMessage("cmn.day"),
                            String.valueOf(day)));
            day++;
        }

        if (exFlg) {
            labelList.add(
                    new LabelValueBean(gsMsg.getMessage("tcd.tcd050kn.01"),
                                    Integer.toString(GSConstCommon.LAST_DAY_OF_MONTH)));
        }

        return labelList;
    }

    /**
     * <br>[機  能] 時コンボを生成します
     * <br>[解  説]
     * <br>[備  考]
     * @return ArrayList (in LabelValueBean)  時コンボ
     */
    public ArrayList<LabelValueBean> getHourLabel() {
        int hour = 0;
        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        GsMessage gsMsg = new GsMessage(reqMdl__);
        labelList.add(
                new LabelValueBean(gsMsg.getMessage("cmn.notset"), "-1"));
        for (int i = 0; i < 24; i++) {
            labelList.add(
                    new LabelValueBean(String.valueOf(hour),
                            String.valueOf(hour)));
            hour++;
        }
        return labelList;
    }

    /**
     * <br>[機  能] 分コンボを生成します
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @return ArrayList (in LabelValueBean)  分コンボ
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<LabelValueBean> getMinuteLabel(Connection con) throws SQLException {
        int hourDivCount = 6;
        int min = 0;
        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        GsMessage gsMsg = new GsMessage(reqMdl__);
        labelList.add(
                new LabelValueBean(gsMsg.getMessage("cmn.notset"), "-1"));
        int hourMemCount = 60 / hourDivCount;
        for (int i = 0; i < hourDivCount; i++) {
            labelList.add(
                    new LabelValueBean(
                            StringUtil.toDecFormat(min, "00"),
                            String.valueOf(min)));
            min = min + hourMemCount;
        }
        return labelList;
    }

    /**
     * <br>[機  能] 週コンボを生成します
     * <br>[解  説]
     * <br>[備  考]
     * @return ArrayList (in LabelValueBean)  週コンボ
     */
    public ArrayList<LabelValueBean> getWeekLabel() {
        int week = 1;
        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        GsMessage gsMsg = new GsMessage(reqMdl__);
        labelList.add(new LabelValueBean(gsMsg.getMessage("cmn.noset2"), String.valueOf(0)));
        for (int i = 0; i < 5; i++) {
            labelList.add(
                    new LabelValueBean(
                            MaintenanceUtil.getWeek(week, reqMdl__),
                            String.valueOf(week)));
            week++;
        }
        return labelList;
    }

    /**
     * <br>[機  能] ユーザコンボを生成します
     * <br>[解  説]
     * <br>[備  考]
     * @param uList (in CmnUsrmInfModel) ユーザ情報リスト
     * @return ArrayList (in LabelValueBean)  年コンボ
     */
    public ArrayList<LabelValueBean> getUserLabel(ArrayList < CmnUsrmInfModel > uList) {

        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        if (uList != null) {
            CmnUsrmInfModel uMdl = null;
            for (int i = 0; i < uList.size(); i++) {
                uMdl = uList.get(i);
                String name = uMdl.getUsiSei() + " " + uMdl.getUsiMei();
                labelList.add(
                        new LabelValueBean(name, String.valueOf(uMdl.getUsrSid())));
            }
        }

        return labelList;
    }

    /**
     *
     * <br>[機  能] 曜日指定パラメータを設定します
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param model ScheduleExSearchModel
     */
    private void __setDayWeekToForm(Man290ParamModel paramMdl, CmnInfoMsgModel model) {

        ArrayList<String> dWeekList = new ArrayList<String>();
        if (model.getImsDweek1() == 1) {
            dWeekList.add("1");
        }
        if (model.getImsDweek2() == 1) {
            dWeekList.add("2");
        }
        if (model.getImsDweek3() == 1) {
            dWeekList.add("3");
        }
        if (model.getImsDweek4() == 1) {
            dWeekList.add("4");
        }
        if (model.getImsDweek5() == 1) {
            dWeekList.add("5");
        }
        if (model.getImsDweek6() == 1) {
            dWeekList.add("6");
        }
        if (model.getImsDweek7() == 1) {
            dWeekList.add("7");
        }
        paramMdl.setMan290Dweek((String[]) dWeekList.toArray(new String[dWeekList.size()]));
    }

    /**
     * <br>[機  能] 追加側のコンボで選択中のメンバーをメンバーリストに追加する
     *
     * <br>[解  説] 画面右側のコンボ表示に必要なSID(複数)をメンバーリスト(memberSid)で持つ。
     *              画面で追加矢印ボタンをクリックした場合、
     *              追加側のコンボで選択中の値(addSelectSid)をメンバーリストに追加する。
     *
     * <br>[備  考] 追加側のコンボで値が選択されていない場合はメンバーリストをそのまま返す
     *
     * @param addSelectSid 追加側のコンボで選択中の値
     * @param memberSid メンバーリスト
     * @return 追加済みのメンバーリスト
     */
    public String[] getAddMember(String[] addSelectSid, String[] memberSid) {

        if (addSelectSid == null) {
            return memberSid;
        }
        if (addSelectSid.length < 1) {
            return memberSid;
        }


        //追加後に画面にセットするメンバーリストを作成
        ArrayList<String> list = new ArrayList<String>();

        if (memberSid != null) {
            for (int j = 0; j < memberSid.length; j++) {
                if (!memberSid[j].equals("-1")) {
                    list.add(memberSid[j]);
                }
            }
        }

        for (int i = 0; i < addSelectSid.length; i++) {
            if (!addSelectSid[i].equals("-1")) {
                list.add(addSelectSid[i]);
            }
        }

        log__.debug("追加後メンバーリストサイズ = " + list.size());
        return list.toArray(new String[list.size()]);
    }

    /**
     * <br>[機  能] 登録に使用する側のコンボで選択中のメンバーをメンバーリストから削除する
     *
     * <br>[解  説] 登録に使用する側のコンボ表示に必要なSID(複数)をメンバーリスト(memberSid)で持つ。
     *              画面で削除矢印ボタンをクリックした場合、
     *              登録に使用する側のコンボで選択中の値(deleteSelectSid)をメンバーリストから削除する。
     *
     * <br>[備  考] 登録に使用する側のコンボで値が選択されていない場合はメンバーリストをそのまま返す
     *
     * @param deleteSelectSid 登録に使用する側のコンボで選択中の値
     * @param memberSid メンバーリスト
     * @return 削除済みのメンバーリスト
     */
    public String[] getDeleteMember(String[] deleteSelectSid, String[] memberSid) {

        if (deleteSelectSid == null) {
            return memberSid;
        }
        if (deleteSelectSid.length < 1) {
            return memberSid;
        }

        if (memberSid == null) {
            memberSid = new String[0];
        }

        //削除後に画面にセットするメンバーリストを作成
        ArrayList<String> list = new ArrayList<String>();

        for (int i = 0; i < memberSid.length; i++) {
            boolean setFlg = true;

            for (int j = 0; j < deleteSelectSid.length; j++) {
                if (memberSid[i].equals(deleteSelectSid[j])) {
                    setFlg = false;
                    break;
                }
            }

            if (setFlg) {
                list.add(memberSid[i]);
            }
        }

        log__.debug("削除後メンバーリストサイズ = " + list.size());
        return list.toArray(new String[list.size()]);
    }

    /**
     * <br>[機  能] 表示グループ用のグループリストを取得する(全て)
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @return ArrayList
     * @throws SQLException SQL実行時例外
     */
    private ArrayList <LabelValueBean> __getGroupLabelList(Connection con)
    throws SQLException {

        GsMessage gsMsg = new GsMessage(reqMdl__);
        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        LabelValueBean labelBean = new LabelValueBean();
        labelBean.setLabel(gsMsg.getMessage(GSConstMain.TEXT_GROUP_COMBO));
        labelBean.setValue(GSConstMain.GROUP_COMBO_VALUE);
        labelList.add(labelBean);

        //グループリスト取得
        GroupBiz gBiz = new GroupBiz();
        ArrayList <GroupModel> gpList = gBiz.getGroupCombList(con);

        GroupModel gpMdl = null;
        for (int i = 0; i < gpList.size(); i++) {
            gpMdl = gpList.get(i);
            labelList.add(
                    new LabelValueBean(gpMdl.getGroupName(), String.valueOf(gpMdl.getGroupSid())));
        }
        log__.debug("labelList.size()=>" + labelList.size());
        return labelList;
    }

    /**
     * <br>[機  能] インフォメーション公開対象一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getInfoFullLabel(Man290ParamModel paramMdl, Connection con)
    throws SQLException {

        //取得するユーザSID・グループSID
        String[] leftFull = paramMdl.getMan290memberSid();
        return __getInfoLabel(leftFull, con);
    }

    /**
     * <br>[機  能] インフォメーションメンバー一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param left 取得するユーザSID・グループSID
     * @param con コネクション
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getInfoLabel(String[] left, Connection con)
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
        LabelValueBean labelBean = null;
        for (GroupModel gmodel : glist) {
            labelBean = new LabelValueBean();
            labelBean.setLabel(gmodel.getGroupName());
            labelBean.setValue("G" + String.valueOf(gmodel.getGroupSid()));
            ret.add(labelBean);
        }
        UserBiz userBiz = new UserBiz();
        ArrayList<BaseUserModel> ulist
                = userBiz.getBaseUserList(con,
                                        (String[]) usrSids.toArray(new String[usrSids.size()]));
        for (BaseUserModel umodel : ulist) {
            labelBean = new LabelValueBean();
            labelBean.setLabel(umodel.getUsisei() + " " + umodel.getUsimei());
            labelBean.setValue(String.valueOf(umodel.getUsrsid()));
            ret.add(labelBean);
        }
        return ret;
    }
}
