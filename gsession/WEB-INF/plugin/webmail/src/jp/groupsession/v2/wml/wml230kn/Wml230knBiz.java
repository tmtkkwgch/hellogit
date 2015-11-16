package jp.groupsession.v2.wml.wml230kn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.GSConstWebmail;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.wml.biz.WmlBiz;
import jp.groupsession.v2.wml.dao.WebmailDao;
import jp.groupsession.v2.wml.dao.base.WmlAccountDao;
import jp.groupsession.v2.wml.dao.base.WmlFilterConditionDao;
import jp.groupsession.v2.wml.dao.base.WmlFilterDao;
import jp.groupsession.v2.wml.dao.base.WmlFilterFwaddressDao;
import jp.groupsession.v2.wml.dao.base.WmlFilterSortDao;
import jp.groupsession.v2.wml.dao.base.WmlLabelDao;
import jp.groupsession.v2.wml.model.MailFilterConditionModel;
import jp.groupsession.v2.wml.model.MailFilterModel;
import jp.groupsession.v2.wml.model.base.WmlAccountModel;
import jp.groupsession.v2.wml.model.base.WmlFilterConditionModel;
import jp.groupsession.v2.wml.model.base.WmlFilterFwaddressModel;
import jp.groupsession.v2.wml.model.base.WmlFilterModel;
import jp.groupsession.v2.wml.model.base.WmlFilterSortModel;
import jp.groupsession.v2.wml.model.base.WmlLabelModel;
import jp.groupsession.v2.wml.wml230.Wml230Biz;
import jp.groupsession.v2.wml.wml230.Wml230Form;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] WEBメール 管理者設定 フィルタ登録確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml230knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Wml230knBiz.class);
    /** DBコネクション */
    private Connection con__ = null;

    /**
     * <p>デフォルトコンストラクター
     */
    public Wml230knBiz() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public Wml230knBiz(Connection con) {
        con__ = con;
    }

    /**
     * <br>[機  能] 初期表示設定を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @param paramMdl パラメータ情報
     * @param userSid ユーザSID
     * @throws SQLException SQL実行時例外
     */
    public void setInitData(Connection con, RequestModel reqMdl,
                            Wml230knParamModel paramMdl, int userSid) throws SQLException {
        //アカウント名取得
        WmlAccountDao wacDao = new WmlAccountDao(con);
        WmlAccountModel wacMdl = wacDao.select(paramMdl.getWmlAccountSid());
        paramMdl.setWml220accountName(wacMdl.getWacName());

        //フィルター条件
        GsMessage gsMsg = new GsMessage(reqMdl);
        if (paramMdl.getWml230filterType() == 0) {
            paramMdl.setWml230filterTypeView(gsMsg.getMessage(GSConstWebmail.ALL_CONDITION));
        } else {
            paramMdl.setWml230filterTypeView(gsMsg.getMessage(GSConstWebmail.ANY_CONDITION));
        }

        //条件１
        if (paramMdl.getWml230condition1() != null) {
            paramMdl.setWml230conditionType1View(
                    __getViewCondition(paramMdl.getWml230conditionType1(), gsMsg));

            paramMdl.setWml230conditionExs1View(
                    __getViewConditionExs(paramMdl.getWml230conditionExs1(), gsMsg));
        }

        //条件２
        if (paramMdl.getWml230condition2() != null) {
            paramMdl.setWml230conditionType2View(
                    __getViewCondition(paramMdl.getWml230conditionType2(), gsMsg));

            paramMdl.setWml230conditionExs2View(
                    __getViewConditionExs(paramMdl.getWml230conditionExs2(), gsMsg));
        }

        //条件３
        if (paramMdl.getWml230condition3() != null) {
            paramMdl.setWml230conditionType3View(
                    __getViewCondition(paramMdl.getWml230conditionType3(), gsMsg));

            paramMdl.setWml230conditionExs3View(
                    __getViewConditionExs(paramMdl.getWml230conditionExs3(), gsMsg));
        }

        //条件４
        if (paramMdl.getWml230condition4() != null) {
            paramMdl.setWml230conditionType4View(
                    __getViewCondition(paramMdl.getWml230conditionType4(), gsMsg));

            paramMdl.setWml230conditionExs4View(
                    __getViewConditionExs(paramMdl.getWml230conditionExs4(), gsMsg));
        }

        //条件５
        if (paramMdl.getWml230condition5() != null) {
            paramMdl.setWml230conditionType5View(
                    __getViewCondition(paramMdl.getWml230conditionType5(), gsMsg));

            paramMdl.setWml230conditionExs5View(
                    __getViewConditionExs(paramMdl.getWml230conditionExs5(), gsMsg));
        }

        //ラベル名設定
        if (!(paramMdl.getWml230actionLabel().equals("0"))) {
            int wlbSid = Integer.parseInt(paramMdl.getWml230actionLabelValue());
            WmlLabelDao labelDao = new WmlLabelDao(con);
            WmlLabelModel labelData = labelDao.select(wlbSid);
            paramMdl.setWml230LabelView(labelData.getWlbName());
        }

        //フィルタリング対象のメール一覧
        if (paramMdl.getWml230doFilter() == Wml230Form.WML230_DOFILTER_YES) {
            Wml230Biz biz140 = new Wml230Biz();
            biz140.setMailList(con, paramMdl, userSid);
        }
    }

    /**
     * <br>[機  能] 登録、または更新処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param userSid ログインユーザSID
     * @param cntCon 採番コントローラ
     * @throws SQLException SQL実行例外
     * @throws IOToolsException IOエラー
     */
    public void doAddEdit(
        Wml230knParamModel paramMdl,
        int userSid,
        MlCountMtController cntCon) throws SQLException, IOToolsException {

        boolean commitFlg = false;
        try {
            con__.setAutoCommit(false);

            WmlFilterModel wftMdl = null;
            //処理モード(新規登録）
            if (paramMdl.getWmlFilterCmdMode() == GSConstWebmail.CMDMODE_ADD) {
                //新規登録
                log__.debug("新規登録");
                wftMdl = doInsert(paramMdl, userSid, cntCon);

            //編集
            } else if (paramMdl.getWmlFilterCmdMode() == GSConstWebmail.CMDMODE_EDIT) {
                log__.debug("編集");
                wftMdl = doUpdate(paramMdl, userSid);
            }

            if (paramMdl.getWml230doFilter() == Wml230knForm.WML230_DOFILTER_YES) {

                //フィルター情報
                MailFilterModel filterMdl = new MailFilterModel();
                filterMdl.setWftSid(wftMdl.getWftSid());
                filterMdl.setTempFile(wftMdl.getWftTempfile()
                                        == GSConstWebmail.FILTER_TEMPFILE_YES);
                filterMdl.setLabel(wftMdl.getWftActionLabel()
                                        == GSConstWebmail.FILTER_LABEL_SETLABEL);
                filterMdl.setLabelSid(wftMdl.getWlbSid());
                filterMdl.setReaded(wftMdl.getWftActionRead()
                                        == GSConstWebmail.FILTER_READED_SETREADED);
                filterMdl.setDust(wftMdl.getWftActionDust()
                                        == GSConstWebmail.FILTER_DUST_MOVEDUST);
                filterMdl.setCondition(wftMdl.getWftCondition());

                //フィルター条件
                WmlFilterConditionDao conditionDao = new WmlFilterConditionDao(con__);
                List<WmlFilterConditionModel> wfcList = conditionDao.select(wftMdl.getWftSid());
                List<MailFilterConditionModel> conditionList
                    = new ArrayList<MailFilterConditionModel>();
                for (WmlFilterConditionModel wfcData : wfcList) {
                    MailFilterConditionModel conditionMdl = new MailFilterConditionModel();
                    conditionMdl.setType(wfcData.getWfcType());
                    conditionMdl.setExpression(wfcData.getWfcExpression());
                    conditionMdl.setText(wfcData.getWfcText());

                    conditionList.add(conditionMdl);
                }

                //フィルタリング対象のアカウント
                int[] wacSidList = new int[]{paramMdl.getWmlAccountSid()};

                //メールのフィルタリングを行う
                WmlBiz wmlBiz = new WmlBiz();
                WebmailDao webmailDao = new WebmailDao(con__);
                for (int wacSid : wacSidList) {
                    long dustWdrSid = -1;
                    if (filterMdl.isDust()) {
                        dustWdrSid = wmlBiz.getDirectorySid(con__, wacSid,
                                                            GSConstWebmail.DIR_TYPE_DUST);
                    }

                    webmailDao.updateFilterlingMail(filterMdl, conditionList,
                                                wacSid, dustWdrSid);
                }
            }

            con__.commit();
            commitFlg = true;

        } catch (SQLException e) {
            log__.error("SQLException", e);
            throw e;
        } finally {
            if (!commitFlg) {
                JDBCUtil.rollback(con__);
            }
        }
    }

    /**
     * <br>[機  能] 新規登録画面処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param userSid ログインユーザSID
     * @param cntCon 採番コントローラ
     * @return フィルター情報
     * @throws SQLException SQL実行例外
     */
    public WmlFilterModel doInsert(
        Wml230knParamModel paramMdl,
        int userSid,
        MlCountMtController cntCon) throws SQLException {

        //フィルターSID採番
        int wftSid = (int) cntCon.getSaibanNumber(
                GSConstWebmail.SBNSID_WEBMAIL,
                GSConstWebmail.SBNSID_SUB_FILTER,
                userSid);

        //フィルターを登録する
        WmlFilterModel wfMdl  = __getFilterInsertMdl(paramMdl, userSid, wftSid);
        WmlFilterDao wfDao = new WmlFilterDao(con__);
        wfDao.insert(wfMdl);

        //フィルター_転送指定アドレスを登録する
        __entryFwAddress(paramMdl, wfMdl.getWftSid());

        //フィルター条件を登録する
        WmlFilterConditionDao wfcDao = new WmlFilterConditionDao(con__);
        List<WmlFilterConditionModel> conditionList = __getFilterConditionList(paramMdl, wftSid);
        for (WmlFilterConditionModel conditionMdl : conditionList) {
            wfcDao.insert(conditionMdl);
        }

        //フィルター適用順を登録する
        WmlFilterSortDao wfsDao = new WmlFilterSortDao(con__);
        WmlFilterSortModel wfsMdl  = __getFilterSortInsertMdl(paramMdl, wftSid);
        wfsDao.insert(wfsMdl);

        return wfMdl;
    }

    /**
     * <br>[機  能] 編集画面登録処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param userSid ログインユーザSID
     * @return フィルター情報
     * @throws SQLException SQL実行例外
     */
    public WmlFilterModel doUpdate(
        Wml230knParamModel paramMdl,
        int userSid) throws SQLException {

        List<WmlFilterConditionModel> conditionList = null;

        int wftSid = paramMdl.getWmlEditFilterId();
        con__.setAutoCommit(false);

        WmlFilterDao wfdao = new WmlFilterDao(con__);

        //フィルター更新Model
        WmlFilterModel wfMdl  = __getFilterUpdateMdl(paramMdl, userSid);
        //フィルターを更新する
        wfdao.update(wfMdl);

        //フィルター_転送指定アドレスを登録する
        __entryFwAddress(paramMdl, wfMdl.getWftSid());

        //フィルター条件を削除する
        Wml230knDao dao = new Wml230knDao(con__);
        dao.deleteFilter(wftSid);

        //フィルター条件を登録する
        WmlFilterConditionDao wfcDao = new WmlFilterConditionDao(con__);
        conditionList = __getFilterConditionList(paramMdl, wftSid);
        for (WmlFilterConditionModel conditionMdl : conditionList) {
            wfcDao.insert(conditionMdl);
        }

        return wfMdl;
    }

    /**
     * <br>[機  能] 新規登録用のWmlFilterModelを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param userSid ユーザーSID
     * @param wftSid フィルターSID
     * @return WmlFilterModel
     * @throws SQLException SQL実行例外
     */
    private WmlFilterModel __getFilterInsertMdl(
        Wml230knParamModel paramMdl,
        int userSid,
        int wftSid) throws SQLException {

        WmlFilterModel wfMdl = new WmlFilterModel();

        wfMdl.setWftSid(wftSid);
        __setFilterEntryMdl(wfMdl, paramMdl, userSid);

        wfMdl.setWacSid(paramMdl.getWmlAccountSid());

        return wfMdl;
    }

    /**
     * <br>[機  能] 更新用のWmlFilterModelを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param userSid ユーザーSID
     * @return WmlFilterModel
     * @throws SQLException SQL実行例外
     */
    private WmlFilterModel __getFilterUpdateMdl(
        Wml230knParamModel paramMdl,
        int userSid) throws SQLException {

        WmlFilterModel wfMdl = new WmlFilterModel();

        wfMdl.setWftSid(paramMdl.getWmlEditFilterId());
        __setFilterEntryMdl(wfMdl, paramMdl, userSid);

        //アカウントSID
        wfMdl.setWacSid(paramMdl.getWmlAccountSid());

        return wfMdl;
    }

    /**
     * <br>[機  能] フィルター情報Modelへパラメータ情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param wfMdl フィルター情報Model
     * @param paramMdl パラメータ情報
     * @param userSid ユーザSID
     */
    private void __setFilterEntryMdl(WmlFilterModel wfMdl, Wml230knParamModel paramMdl,
                                                    int userSid) {
        wfMdl.setUsrSid(userSid);
        wfMdl.setWftName(paramMdl.getWml230FilterName());
        wfMdl.setWftType(GSConstWebmail.WFT_TYPE_ONE);
        wfMdl.setWftCondition(paramMdl.getWml230filterType());
        wfMdl.setWftTempfile(Integer.parseInt(paramMdl.getWml230tempFile()));
        wfMdl.setWftActionLabel(Integer.parseInt(paramMdl.getWml230actionLabel()));
        wfMdl.setWftActionRead(Integer.parseInt(paramMdl.getWml230actionRead()));
        wfMdl.setWftActionDust(Integer.parseInt(paramMdl.getWml230actionDust()));
        wfMdl.setWftActionForward(Integer.parseInt(paramMdl.getWml230actionSend()));

        //ラベルを設定する場合
        if (paramMdl.getWml230actionLabel().equals("1")) {
            wfMdl.setWlbSid(Integer.parseInt(paramMdl.getWml230actionLabelValue()));
        }
    }

    /**
     * <br>[機  能] 新規登録用のWmlFilterModel一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param wftSid フィルターSID
     * @return WmlFilterModel一覧
     * @throws SQLException SQL実行例外
     */
    private List<WmlFilterConditionModel> __getFilterConditionList(
        Wml230knParamModel paramMdl, int wftSid) throws SQLException {

        List<WmlFilterConditionModel> conditionList = new ArrayList<WmlFilterConditionModel>();

        if (paramMdl.getWml230condition1().equals(String.valueOf(GSConstWebmail.CONDITION_ONE))) {
            conditionList.add(__createFilterConditionModel(wftSid,
                            GSConstWebmail.CONDITION_ONE,
                            paramMdl.getWml230conditionType1(),
                            paramMdl.getWml230conditionExs1(),
                            paramMdl.getWml230conditionText1()));
        }

        if (paramMdl.getWml230condition2().equals(String.valueOf(GSConstWebmail.CONDITION_TWO))) {
            conditionList.add(__createFilterConditionModel(wftSid,
                            GSConstWebmail.CONDITION_TWO,
                            paramMdl.getWml230conditionType2(),
                            paramMdl.getWml230conditionExs2(),
                            paramMdl.getWml230conditionText2()));
        }

        if (paramMdl.getWml230condition3().equals(String.valueOf(GSConstWebmail.CONDITION_THREE))) {
            conditionList.add(__createFilterConditionModel(wftSid,
                            GSConstWebmail.CONDITION_THREE,
                            paramMdl.getWml230conditionType3(),
                            paramMdl.getWml230conditionExs3(),
                            paramMdl.getWml230conditionText3()));
        }

        if (paramMdl.getWml230condition4().equals(String.valueOf(GSConstWebmail.CONDITION_FOUR))) {
            conditionList.add(__createFilterConditionModel(wftSid,
                            GSConstWebmail.CONDITION_FOUR,
                            paramMdl.getWml230conditionType4(),
                            paramMdl.getWml230conditionExs4(),
                            paramMdl.getWml230conditionText4()));
        }

        if (paramMdl.getWml230condition5().equals(String.valueOf(GSConstWebmail.CONDITION_FIVE))) {
            conditionList.add(__createFilterConditionModel(wftSid,
                            GSConstWebmail.CONDITION_FIVE,
                            paramMdl.getWml230conditionType5(),
                            paramMdl.getWml230conditionExs5(),
                            paramMdl.getWml230conditionText5()));
        }

        return conditionList;
    }

    /**
     * <br>[機  能] フィルター条件Modelを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param wftSid フィルターSID
     * @param number 番号
     * @param type 種別
     * @param exs 条件式
     * @param text 条件
     * @return フィルター条件Model
     */
    private WmlFilterConditionModel __createFilterConditionModel(int wftSid, int number,
                                                                String type, String exs,
                                                                String text) {

        WmlFilterConditionModel wfcMdl = new WmlFilterConditionModel();
        wfcMdl.setWftSid(wftSid);
        wfcMdl.setWfcNum(number);
        wfcMdl.setWfcType(Integer.parseInt(type));
        wfcMdl.setWfcExpression(Integer.parseInt(exs));
        wfcMdl.setWfcText(text);

        return wfcMdl;
    }

    /**
     * <br>[機  能] 新規登録用のWmlFilterSortModelを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param fiSaiSid 採番SID
     * @return WmlFilterModel
     * @throws SQLException SQL実行例外
     */
    private WmlFilterSortModel __getFilterSortInsertMdl(
        Wml230knParamModel paramMdl,
        int fiSaiSid) throws SQLException {

        WmlFilterSortModel wfsMdl = new WmlFilterSortModel();
        WmlFilterSortDao wfsdao = new WmlFilterSortDao(con__);

        wfsMdl.setWacSid(paramMdl.getWmlAccountSid());
        wfsMdl.setWftSid(fiSaiSid);

        //アカウントSID
        wfsMdl.setWfsSort(wfsdao.maxSortNumber() + 1);

        return wfsMdl;
    }

    /**
     * <br>[機  能] フィルター条件(表示用)を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param conditionType フィルター条件
     * @param gsMsg GSMessage
     * @return フィルター条件(表示用)
     */
    private String __getViewCondition(String conditionType, GsMessage gsMsg) {
        String viewCondition = "";
        if (conditionType.equals(String.valueOf(GSConstWebmail.FILTER_TYPE_TITLE))) {
           viewCondition = gsMsg.getMessage(GSConstWebmail.FILTER_TITLE);

        } else if (conditionType.equals(String.valueOf(GSConstWebmail.FILTER_TYPE_ADDRESS))) {
            viewCondition = gsMsg.getMessage(GSConstWebmail.FILTER_ADDRESS);

        } else if (conditionType.equals(String.valueOf(GSConstWebmail.FILTER_TYPE_CC))) {
            viewCondition = "CC";

        } else if (conditionType.equals(String.valueOf(GSConstWebmail.FILTER_TYPE_SEND))) {
            viewCondition = gsMsg.getMessage(GSConstWebmail.FILTER_SEND);

        } else if (conditionType.equals(String.valueOf(GSConstWebmail.FILTER_TYPE_MAIN))) {
            viewCondition = gsMsg.getMessage(GSConstWebmail.FILTER_MAIN);
        }

        return viewCondition;
    }

    /**
     * <br>[機  能] フィルター条件 条件指定(表示用)を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param conditionExs  条件指定
     * @param gsMsg GSMessage
     * @return 条件指定(表示用)
     */
    private String __getViewConditionExs(String conditionExs, GsMessage gsMsg) {
        String viewConditionExs = "";
        if (conditionExs.equals(String.valueOf(GSConstWebmail.FILTER_TYPE_INCLUDE))) {
            viewConditionExs = gsMsg.getMessage(GSConstWebmail.FILTER_INCLUDE);

        } else if (conditionExs.equals(String.valueOf(GSConstWebmail.FILTER_TYPE_EXCLUDE))) {
            viewConditionExs = gsMsg.getMessage(GSConstWebmail.FILTER_EXCLUDE);
        }

        return viewConditionExs;
    }

    /**
     * <br>[機  能] フィルター_転送指定アドレスを登録する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param wftSid フィルターSID
     * @throws SQLException SQL実行時例外
     */
    private void __entryFwAddress(Wml230knParamModel paramMdl, int wftSid)
    throws SQLException {
        //
        WmlFilterFwaddressDao wfFwadrDao = new WmlFilterFwaddressDao(con__);
        wfFwadrDao.delete(wftSid);
        if (paramMdl.getWml230actionSend().equals("1")) {
            WmlFilterFwaddressModel wfFwadrMdl = new WmlFilterFwaddressModel();
            wfFwadrMdl.setWftSid(wftSid);

            int wfaNo = 1;
            for (String fwAddress : paramMdl.getWml230actionSendValue()) {
                wfFwadrMdl.setWfaNo(wfaNo++);
                wfFwadrMdl.setWfaAddress(fwAddress);
                wfFwadrDao.insert(wfFwadrMdl);
            }
        }
    }
}
