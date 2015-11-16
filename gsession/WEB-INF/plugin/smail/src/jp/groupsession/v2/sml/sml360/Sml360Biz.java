package jp.groupsession.v2.sml.sml360;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.PageUtil;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.sml.GSConstSmail;
import jp.groupsession.v2.sml.biz.SmlCommonBiz;
import jp.groupsession.v2.sml.dao.SmlAccountDao;
import jp.groupsession.v2.sml.dao.SmlFilterDao;
import jp.groupsession.v2.sml.model.LabelDataModel;
import jp.groupsession.v2.sml.model.SmlAccountModel;
import jp.groupsession.v2.sml.model.SmlFilterConditionModel;
import jp.groupsession.v2.sml.model.SmlFilterModel;
import jp.groupsession.v2.sml.sml340.Sml340Dao;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] ショートメール 個人設定 フィルタ登録画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sml360Biz {


    /** メール一覧の最大表示件数 */
    public static final int MAILLIST_MAXCOUNT = 30;
    /** メール一覧 宛先の最大文字数 */
    public static final int MAXLEN_LISTTO = 100;

    /** オペレーションログ ゴミ箱へ移動 */
    protected static final int LOGTYPE_MAILDELETE_ = 0;
    /** オペレーションログ ゴミ箱を空にする */
    protected static final int LOGTYPE_EMPTYTRASH_ = 1;
    /** オペレーションログ メールの移動 */
    protected static final int LOGTYPE_MOVEMAIL_ = 2;

    /**
     * <br>[機  能] 初期表示設定を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param userSid ユーザSID
     * @param reqMdl リクエスト情報
     * @throws SQLException SQL実行時例外
     */
    public void setInitData(Connection con, Sml360ParamModel paramMdl, int userSid,
                            RequestModel reqMdl)
    throws SQLException {

        //アカウント名取得
        SmlAccountDao sacDao = new SmlAccountDao(con);
        SmlAccountModel sacMdl = sacDao.select(paramMdl.getSmlAccountSid());
        paramMdl.setSml350accountName(sacMdl.getSacName());

        SmlCommonBiz comonBiz = new SmlCommonBiz(con, reqMdl);

        //ラベルリスト取得
        Sml340Dao dao = new Sml340Dao(con);
        List<LabelDataModel> sldMdlList = dao.getLabelList(paramMdl.getSmlAccountSid());
        paramMdl.setLbList(comonBiz.getLbCombo(reqMdl, sldMdlList));

        //条件コンボをセットする
        paramMdl.setConditionList1(getConditionComb1(reqMdl));
        paramMdl.setConditionList2(getConditionComb2(reqMdl));

        //初期表示　編集
        if (paramMdl.getSml360initFlg() == GSConstSmail.DSP_FIRST
                && paramMdl.getSmlFilterCmdMode() == GSConstSmail.CMDMODE_EDIT) {

            //フィルター情報設定
            int sfiSid = paramMdl.getSmlEditFilterId();
            SmlFilterDao filterDao = new SmlFilterDao(con);
            SmlFilterModel filterData = filterDao.select(sfiSid);

            paramMdl.setSml360tempFile(String.valueOf(filterData.getSftTempfile()));
            paramMdl.setSml360actionLabel(String.valueOf(filterData.getSftActionLabel()));

            if (filterData.getSftActionLabel() == 1) {
                paramMdl.setSml360actionLabelValue(String.valueOf(filterData.getSlbSid()));
            }

            paramMdl.setSml360actionRead(String.valueOf(filterData.getSftActionRead()));
            paramMdl.setSml360actionDust(String.valueOf(filterData.getSftActionDust()));

            paramMdl.setSml360FilterName(filterData.getSftName());
            paramMdl.setSml360filterType(filterData.getSftCondition());

            //フィルター条件設定
            List<SmlFilterConditionModel> sfcMdlList = new ArrayList<SmlFilterConditionModel>();
            sfcMdlList = dao.selectFilCon(sfiSid);


            for (int i = 0; i < sfcMdlList.size(); i++) {

                //条件１
                if (sfcMdlList.get(i).getSfcNum() == GSConstSmail.CONDITION_ONE) {
                    paramMdl.setSml360condition1(String.valueOf(GSConstSmail.CONDITION_ONE));
                    paramMdl.setSml360conditionType1(
                            String.valueOf(sfcMdlList.get(i).getSfcType()));
                    paramMdl.setSml360conditionExs1(String.valueOf(
                            sfcMdlList.get(i).getSfcExpression()));
                    paramMdl.setSml360conditionText1(
                            String.valueOf(sfcMdlList.get(i).getSfcText()));
                }

                //条件２
                if (sfcMdlList.get(i).getSfcNum() == GSConstSmail.CONDITION_TWO) {
                    paramMdl.setSml360condition2(String.valueOf(GSConstSmail.CONDITION_TWO));
                    paramMdl.setSml360conditionType2(
                            String.valueOf(sfcMdlList.get(i).getSfcType()));
                    paramMdl.setSml360conditionExs2(String.valueOf(
                            sfcMdlList.get(i).getSfcExpression()));
                    paramMdl.setSml360conditionText2(
                            String.valueOf(sfcMdlList.get(i).getSfcText()));
                }

                //条件３
                if (sfcMdlList.get(i).getSfcNum() == GSConstSmail.CONDITION_THREE) {
                    paramMdl.setSml360condition3(String.valueOf(GSConstSmail.CONDITION_THREE));
                    paramMdl.setSml360conditionType3(
                            String.valueOf(sfcMdlList.get(i).getSfcType()));
                    paramMdl.setSml360conditionExs3(String.valueOf(
                            sfcMdlList.get(i).getSfcExpression()));
                    paramMdl.setSml360conditionText3(
                            String.valueOf(sfcMdlList.get(i).getSfcText()));
                }

                //条件４
                if (sfcMdlList.get(i).getSfcNum() == GSConstSmail.CONDITION_FOUR) {
                    paramMdl.setSml360condition4(String.valueOf(GSConstSmail.CONDITION_FOUR));
                    paramMdl.setSml360conditionType4(
                            String.valueOf(sfcMdlList.get(i).getSfcType()));
                    paramMdl.setSml360conditionExs4(String.valueOf(
                            sfcMdlList.get(i).getSfcExpression()));
                    paramMdl.setSml360conditionText4(
                            String.valueOf(sfcMdlList.get(i).getSfcText()));
                }

                //条件５
                if (sfcMdlList.get(i).getSfcNum() == GSConstSmail.CONDITION_FIVE) {
                    paramMdl.setSml360condition5(String.valueOf(GSConstSmail.CONDITION_FIVE));
                    paramMdl.setSml360conditionType5(
                            String.valueOf(sfcMdlList.get(i).getSfcType()));
                    paramMdl.setSml360conditionExs5(String.valueOf(
                            sfcMdlList.get(i).getSfcExpression()));
                    paramMdl.setSml360conditionText5(
                            String.valueOf(sfcMdlList.get(i).getSfcText()));
                }
            }

            //初期表示完了
            paramMdl.setSml360initFlg(GSConstSmail.DSP_ALREADY);
        }

        if (paramMdl.getSml360viewMailList() == 1) {
            setMailList(con, paramMdl, userSid);
        }
    }

    /**
     * <br>[機  能] 条件コンボを生成します
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @return ArrayList (in LabelValueBean)  条件コンボ
     */
    public List<LabelValueBean> getConditionComb1(RequestModel reqMdl) {
        List<LabelValueBean> labelList = new ArrayList<LabelValueBean>();

        GsMessage gsMsg = new GsMessage(reqMdl);
        labelList.add(new LabelValueBean(gsMsg.getMessage("cmn.subject"),
                                String.valueOf(GSConstSmail.FILTER_TYPE_TITLE)));
        labelList.add(new LabelValueBean(gsMsg.getMessage("cmn.from"),
                                String.valueOf(GSConstSmail.FILTER_TYPE_ADDRESS)));
        labelList.add(new LabelValueBean("CC",
                                String.valueOf(GSConstSmail.FILTER_TYPE_CC)));
        labelList.add(new LabelValueBean(gsMsg.getMessage("cmn.sender"),
                                String.valueOf(GSConstSmail.FILTER_TYPE_SEND)));
        labelList.add(new LabelValueBean(gsMsg.getMessage("cmn.body"),
                                String.valueOf(GSConstSmail.FILTER_TYPE_MAIN)));

        return labelList;
    }

    /**
     * <br>[機  能] 条件コンボを生成します
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @return ArrayList (in LabelValueBean)  条件コンボ
     */
    public List<LabelValueBean> getConditionComb2(RequestModel reqMdl) {
        List<LabelValueBean> labelList = new ArrayList<LabelValueBean>();

        GsMessage gsMsg = new GsMessage(reqMdl);
        labelList.add(new LabelValueBean(gsMsg.getMessage("wml.141"), "0"));
        labelList.add(new LabelValueBean(gsMsg.getMessage("wml.140"), "1"));
        return labelList;
    }

    /**
     * <br>[機  能] フィルタリング対象のメール一覧に関する情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param userSid ユーザSID
     * @throws SQLException SQL実行時例外
     */
    public void setMailList(Connection con, Sml360ParamModel paramMdl, int userSid)
    throws SQLException {
        int page = paramMdl.getSml360mailListPageTop();

        if (page <= 0) {
            page = 1;
        }
        int start = PageUtil.getRowNumber(page, MAILLIST_MAXCOUNT);
        Sml360Dao dao160 = new Sml360Dao(con);
        long maxCount = dao160.getMailCount(paramMdl);
        int maxPageNum = PageUtil.getPageCount(maxCount, MAILLIST_MAXCOUNT);
        int maxPageStartRow = PageUtil.getRowNumber(maxPageNum, MAILLIST_MAXCOUNT);
        if (maxPageStartRow < start) {
            page = maxPageNum;
            start = maxPageStartRow;
        }

        if (maxPageNum > 1) {
            paramMdl.setSml360mailListPageCombo(
                        PageUtil.createPageOptions(maxCount, MAILLIST_MAXCOUNT));
        }

        paramMdl.setSml360mailList(
                dao160.getMailList(paramMdl, start, MAILLIST_MAXCOUNT));
    }

}
