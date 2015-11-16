package jp.groupsession.v2.wml.wml030;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.PageUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.groupsession.v2.cmn.GSConstWebmail;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.wml.dao.base.WmlAccountDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] WEBメール アカウントマネージャー画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml030Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Wml030Biz.class);

    /**
     * <br>[機  能] 初期表示設定を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @throws SQLException SQL実行時例外
     */
    public void setInitData(Connection con, Wml030ParamModel paramMdl,
                            RequestModel reqMdl) throws SQLException {
        //検索結果を取得する
        //1ページ最大30件
        int limit = GSConstWebmail.LIMIT_DSP_ACCOUNT;
        Wml030SearchModel searchMdl = __createSearchModel(paramMdl);
        searchMdl.setMaxCount(limit);

        //件数カウント
        Wml030Dao dao = new Wml030Dao(con);
        long maxCount = dao.recordCount(searchMdl);
        log__.debug("表示件数 = " + maxCount);

        //現在ページ（ページコンボのvalueを設定）
        int nowPage = paramMdl.getWml030pageTop();
        //結果取得開始カーソル位置
        int start = PageUtil.getRowNumber(nowPage, limit);

        //ページあふれ制御
        int maxPageNum = PageUtil.getPageCount(maxCount, limit);
        if (maxPageNum > 1) {
            paramMdl.setWml030pageDspFlg(true);
        }
        int maxPageStartRow = PageUtil.getRowNumber(maxPageNum, limit);
        if (maxPageStartRow < start) {
            nowPage = maxPageNum;
            start = maxPageStartRow;
        }
        searchMdl.setPage(start);

        //ページング
        paramMdl.setWml030pageTop(nowPage);
        paramMdl.setWml030pageBottom(nowPage);
        paramMdl.setPageCombo(PageUtil.createPageOptions(maxCount, limit));

        List<Wml030AccountModel> accountList = dao.getAccountList(searchMdl, reqMdl);
        paramMdl.setAccountList(accountList);

        //グループコンボを設定
        GsMessage gsMsg = new GsMessage(reqMdl);
        GroupBiz grpBiz = new GroupBiz();
        List<LabelValueBean> groupCombo = new ArrayList<LabelValueBean>();
        groupCombo.add(new LabelValueBean(gsMsg.getMessage("cmn.select.plz"), "-1"));

        ArrayList<GroupModel> grpList = grpBiz.getGroupCombList(con);
        for (GroupModel grpMdl : grpList) {
            LabelValueBean label = new LabelValueBean(grpMdl.getGroupName(),
                                                    String.valueOf(grpMdl.getGroupSid()));
            groupCombo.add(label);
        }
        paramMdl.setGroupCombo(groupCombo);

        //ユーザコンボを設定
        List<LabelValueBean> userCombo = new ArrayList<LabelValueBean>();

        UserBiz userBiz = new UserBiz();
        userCombo.addAll(userBiz.getUserLabelList(con, gsMsg, paramMdl.getWml030group()));
        paramMdl.setUserCombo(userCombo);
    }

    /**
     * <br>[機  能] メッセージに表示するアカウント名を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param accountSidList アカウントSID
     * @return アカウント名
     * @throws SQLException SQL実行時例外
     */
    public String getMsgAccountTitle(Connection con, String[] accountSidList)
    throws SQLException {

        Wml030Dao wml030Dao = new Wml030Dao(con);
        List<String> titleList = wml030Dao.getAccountNameList(accountSidList);

        String msgTitle = "";
        for (int idx = 0; idx < titleList.size(); idx++) {

            //最初の要素以外は改行を挿入
            if (idx > 0) {
                msgTitle += "<br>";
            }

            msgTitle += "・ " + StringUtilHtml.transToHTmlPlusAmparsant(
                            NullDefault.getString(titleList.get(idx), ""));
        }

        return msgTitle;
    }

    /**
     * <br>[機  能] アカウントの削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param userSid ユーザSID
     * @throws SQLException SQL実行時例外
     */
    public void deleteAccount(Connection con, Wml030ParamModel paramMdl, int userSid)
    throws SQLException {

        log__.debug("アカウント削除開始");

        boolean commit = false;
        try {
            WmlAccountDao accountDao = new WmlAccountDao(con);
            accountDao.updateJkbn(paramMdl.getWml030selectAcount(), GSConstWebmail.WAC_JKBN_DELETE);
            con.commit();
            commit = true;
        } catch (SQLException e) {
            log__.error("アカウントの削除に失敗", e);
            throw e;
        } finally {
            if (!commit) {
                con.rollback();
            }
        }

        log__.debug("アカウント削除終了");
    }

    /**
     * <br>[機  能] エクスポート情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @return エクスポート情報
     * @throws SQLException SQL実行時例外
     */
    public List<Wml030ExportModel> getExportData(Connection con, Wml030ParamModel paramMdl)
    throws SQLException {
        Wml030SearchModel searchMdl = __createSearchModel(paramMdl);

        Wml030Dao dao = new Wml030Dao(con);
        return dao.getExportData(searchMdl);
    }

    /**
     * <br>[機  能] 検索条件を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl 検索条件
     * @return 検索条件
     */
    private Wml030SearchModel __createSearchModel(Wml030ParamModel paramMdl) {
        //検索結果を取得する
        Wml030SearchModel searchMdl = new Wml030SearchModel();
        searchMdl.setKeyword(paramMdl.getWml030svKeyword());
        searchMdl.setGrpSid(paramMdl.getWml030svGroup());
        searchMdl.setUserSid(paramMdl.getWml030svUser());
        searchMdl.setSortKey(paramMdl.getWml030sortKey());
        searchMdl.setOrder(paramMdl.getWml030order());

        return searchMdl;
    }
}
