package jp.groupsession.v2.anp.anp060;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.ValidateUtil;
import jp.groupsession.v2.anp.AnpiCommonBiz;
import jp.groupsession.v2.anp.GSConstAnpi;
import jp.groupsession.v2.anp.dao.AnpHdataDao;
import jp.groupsession.v2.anp.dao.AnpMtempDao;
import jp.groupsession.v2.anp.dao.AnpSdataDao;
import jp.groupsession.v2.anp.model.AnpCmnConfModel;
import jp.groupsession.v2.anp.model.AnpHdataModel;
import jp.groupsession.v2.anp.model.AnpLabelValueModel;
import jp.groupsession.v2.anp.model.AnpMtempModel;
import jp.groupsession.v2.anp.model.AnpSdataModel;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.UsidSelectGrpNameDao;
import jp.groupsession.v2.cmn.dao.base.CmnCmbsortConfDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnCmbsortConfModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;


/**
 * <br>[機  能] 安否確認メッセージ配信画面ビジネスロジック
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Anp060Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Anp060Biz.class);

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param anp060Model パラメータモデル
     * @param reqMdl リクエストモデル
     * @param con DBコネクション
     * @throws Exception 実行例外
     */
    public void setInitData(Anp060ParamModel anp060Model, RequestModel reqMdl, Connection con)
                throws Exception {

        GsMessage gsMsg = new GsMessage(reqMdl);

        //セッション情報を取得
        BaseUserModel usModel = reqMdl.getSmodel();
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID

        AnpiCommonBiz anpiBiz = new AnpiCommonBiz();

        //定型メッセージコンボボックスリストを設定
        List<LabelValueBean> mtempLabel = __getMTemplateLabel(con);
        anp060Model.setAnp060MtempLabel(mtempLabel);

        //全て表示区分(コンボボックス)
        boolean allGroupUserFlg = false;

        //送信先グループリストを取得
        List<AnpLabelValueModel> gpLabel =
                anpiBiz.getGroupLabel(reqMdl, con, sessionUsrSid, allGroupUserFlg);

        //"グループ一覧"を最上位に追加
        gpLabel.add(0, new AnpLabelValueModel(gsMsg.getMessage("cmn.grouplist"),
                String.valueOf(GSConstAnpi.GRP_SID_GRPLIST), "0"));
        anp060Model.setAnp060GroupLabel(gpLabel);

        //グループリストに初期表示するデフォルトグループを取得
        String dspGpSidStr = anp060Model.getAnp060SelectGroupSid();
        if (StringUtil.isNullZeroString(dspGpSidStr)) {
            dspGpSidStr = anpiBiz.getDefaultGroupSid(con, sessionUsrSid, allGroupUserFlg);
            anp060Model.setAnp060SelectGroupSid(dspGpSidStr);
        }
        dspGpSidStr = anpiBiz.getEnableSelectGroup(gpLabel, dspGpSidStr,
                anpiBiz.getDefaultGroupSid(con, sessionUsrSid, allGroupUserFlg));
        anp060Model.setAnp060SelectGroupSid(dspGpSidStr);


        int dspGpSid = AnpiCommonBiz.getGroupSidfromDisp(dspGpSidStr);
        boolean isMyGroup = AnpiCommonBiz.isMyGroupSidforDisp(dspGpSidStr);

        List<LabelValueBean> labelListAdd = new ArrayList<LabelValueBean>();
        if (dspGpSid == GSConstAnpi.GRP_SID_GRPLIST) {
            GroupDao dao = new GroupDao(con);
            //グループを全て取得
            CmnCmbsortConfDao sortDao = new CmnCmbsortConfDao(con);
            CmnCmbsortConfModel sortMdl = sortDao.getCmbSortData();
            ArrayList<GroupModel> allGpList = dao.getGroupTree(sortMdl);
            //除外するグループSID
            List<String> fullGrepList = new ArrayList<String>();
            if (anp060Model.getAnp060SenderList() != null) {
                fullGrepList = Arrays.asList(anp060Model.getAnp060SenderList());
            }
            for (GroupModel bean : allGpList) {
                if (!fullGrepList.contains(String.valueOf("G" + bean.getGroupSid()))) {
                    labelListAdd.add(new LabelValueBean(
                            bean.getGroupName(), String.valueOf("G" + bean.getGroupSid())));
                }
            }
        } else {
            //除外するユーザSIDを設定
            ArrayList<Integer> usrSids = new ArrayList<Integer>();
            String[] senders = anp060Model.getAnp060SenderList();
            if (senders != null && senders.length > 0) {
                for (String sid : senders) {
                    usrSids.add(new Integer(NullDefault.getInt(sid, -1)));
                }
            }

            //グループ所属ユーザラベルの設定
            List<CmnUsrmInfModel> belongList =
                    anpiBiz.getBelongUserList(con, dspGpSid, usrSids, sessionUsrSid, isMyGroup);
            for (CmnUsrmInfModel cuiMdl : belongList) {
                labelListAdd.add(new LabelValueBean(cuiMdl.getUsiSei() + "　" + cuiMdl.getUsiMei(),
                        String.valueOf(cuiMdl.getUsrSid())));
            }
        }

        //送信先グループ所属ユーザリスト 右
        anp060Model.setAnp060BelongLabel(labelListAdd);
        //送信先ユーザリスト 左
        anp060Model.setAnp060SenderLabel(__getSenderFullLavle(anp060Model, con));

        //ユーザ返信URLの設定
        anp060Model.setAnp060MessageBody(
                anpiBiz.getHaisinMessageBodyFixd(reqMdl, con, null, null, true));

        //配信者の設定
        anp060Model.setAnp060RegistName(NullDefault.getString(usModel.getUsisei(), "") + " "
                               + NullDefault.getString(usModel.getUsimei(), ""));
    }

    /**
     * <br>[機  能] 過去データより引用しデータを設定
     * <br>[解  説]
     * <br>[備  考]
     * @param anp060Model パラメータモデル
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void setDatafromCopy(Anp060ParamModel anp060Model, Connection con)
                throws SQLException {

        if (!ValidateUtil.isNumber(anp060Model.getAnp060CopyAnpiSid())) {
            return;
        }

        int anpiSid = new Integer(anp060Model.getAnp060CopyAnpiSid());

        //配信情報
        AnpHdataDao hdao = new AnpHdataDao(con);
        AnpHdataModel hdata = hdao.select(anpiSid);

        if (hdata != null) {
            anp060Model.setAnp060Subject(hdata.getAphSubject());
            anp060Model.setAnp060Text1(hdata.getAphText1());
            anp060Model.setAnp060Text2(hdata.getAphText2());
            anp060Model.setAnp010KnrenFlg(hdata.getAphKnrenFlg());
        }

        //安否状況
        String[] senders = null;
        AnpSdataDao sdao = new AnpSdataDao(con);
        List<AnpSdataModel> sdataList = sdao.select(anpiSid);
        if (sdataList != null && !sdataList.isEmpty()) {
            List<String> sids = new ArrayList<String>();
            for (AnpSdataModel smdl : sdataList) {
                if (smdl.getApsType() == GSConstAnpi.SEND_TYPE_USER) {
                    sids.add(String.valueOf(smdl.getUsrSid()));
                } else {
                    sids.add(0, "G" + String.valueOf(smdl.getGrpSid()));
                }
            }
            senders = (String[]) sids.toArray(new String[sids.size()]);
        }

        anp060Model.setAnp060SenderList(senders);
    }

    /**
     * <br>[機  能] ユーザリストに選択されているユーザリストを追加して戻します。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param userList   元のユーザリスト
     * @param selectSids 選択ユーザリスト
     * @return 選択ユーザを追加したユーザリスト
     * @throws Exception 実行例外
     */
    public String[] getUserListAdd(String[] userList, String[] selectSids)
                        throws Exception {

        if (selectSids == null) {
            return userList;
        }
        if (selectSids.length < 1) {
            return userList;
        }

        log__.debug("追加後ユーザを取得");

        //元のユーザリストから、戻り配列を作成
        List<String> newList = new ArrayList<String>();
        if (userList != null) {
            for (String sid: userList) {
                newList.add(sid);
            }
        }

        //選択されているユーザSIDを追加
        if (selectSids != null) {
            for (String sid: selectSids) {
                newList.add(sid);
            }
        }

        String[] ret = null;
        if (newList.size() > 0) {
            ret = (String[]) newList.toArray(new String[newList.size()]);
        }
        return ret;
    }

    /**
     * <br>[機  能] ユーザリストから選択されているユーザを削除して戻します。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param userList   元のユーザリスト
     * @param selectSids 選択ユーザリスト
     * @return 選択ユーザを削除したユーザリスト
     * @throws Exception 実行例外
     */
    public String[] getUserListDel(String[] userList, String[] selectSids)
                        throws Exception {

        log__.debug("削除後ユーザを取得");

        if (userList == null || userList.length == 0) {
            return null;
        }

        //元のリストから選択されているユーザ以外を追加
        List<String> newList = new ArrayList<String>();
        String[] selects = new String[0];
        if (selectSids != null) {
            selects = Arrays.copyOf(selectSids, selectSids.length);
        }
        Arrays.sort(selects);

        for (String sid: userList) {
            if (Arrays.binarySearch(selects, sid) < 0) {
                newList.add(sid);
            }
        }

        String[] ret = null;
        if (newList.size() > 0) {
            ret = (String[]) newList.toArray(new String[newList.size()]);
        }
        return ret;
    }

    /**
     * <br>[機  能] メールテンプレートラベルを生成します。
     * <br>[解  説]
     * <br>[備  考]
     * @param anp060Model パラメータモデル
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void setMailTemplate(Anp060ParamModel anp060Model, Connection con)
                throws SQLException {

        String mtempSid = NullDefault.getString(anp060Model.getAnp060SelectMtempSid(), "");
        if (mtempSid.equals("") || mtempSid.equals("-1")) {
            return;
        }

        //メールテンプレート情報を取得する
        AnpMtempDao dao = new AnpMtempDao(con);
        AnpMtempModel bean = dao.select(new Integer(mtempSid));

        if (bean == null) {
            bean = new AnpMtempModel();
        }

        anp060Model.setAnp060Subject(bean.getApmSubject());
        anp060Model.setAnp060Text1(bean.getApmText1());
        anp060Model.setAnp060Text2(bean.getApmText2());
    }

    /**
     * <br>[機  能] テストメールを送信する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param anp060Model パラメータモデル
     * @param reqMdl リクエストモデル
     * @param con コネクション
     * @return 送信結果
     * @throws Exception 実行時例外
     */
    public int sendTestMail(Anp060ParamModel anp060Model, RequestModel reqMdl, Connection con)
                throws Exception {

        //セッション情報を取得
        BaseUserModel usModel = reqMdl.getSmodel();
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID

        //基本共通設定情報取得
        AnpiCommonBiz anpiBiz = new AnpiCommonBiz();
        AnpCmnConfModel cmnConf = anpiBiz.getAnpCmnConfModel(con);

        //配信データをセット
        AnpHdataModel hdata = new AnpHdataModel();
        hdata.setAphSubject(anp060Model.getAnp060Subject());
        hdata.setAphText1(anp060Model.getAnp060Text1());
        hdata.setAphText2(anp060Model.getAnp060Text2());
        hdata.setAphKnrenFlg(anp060Model.getAnp010KnrenFlg());

        //メール送信（緊急連絡先が設定されているユーザのみ）
        int sendResult =
                anpiBiz.sendMail(
                        reqMdl, cmnConf, hdata,
                        anp060Model.getAnp060TestAdr(), String.valueOf(sessionUsrSid));
        return sendResult;
    }

    /**
     * <br>[機  能] 配信中データを破棄する。
     * <br>[解  説]
     * <br>[備  考]
     * @param anp060Model パラメータモデル
     * @param reqMdl リクエストモデル
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     * @return 配信中の安否確認タイトル
     */
    public String finishHaisin(Anp060ParamModel anp060Model, RequestModel reqMdl, Connection con)
                throws SQLException {

        //セッション情報を取得
        BaseUserModel usModel = reqMdl.getSmodel();
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID

        GsMessage gsMsg = new GsMessage(reqMdl);
        AnpHdataDao dao = new AnpHdataDao(con);
        AnpHdataModel anpMdl = dao.selectInHaisin();
        String ret;
        if (anpMdl.getAphKnrenFlg() == GSConstAnpi.KNREN_MODE_ON) {
            ret = "【 " + gsMsg.getMessage("anp.knmode") + " 】" + anpMdl.getAphSubject();
        } else {
            ret = anpMdl.getAphSubject();
        }
        dao.updateFinish(sessionUsrSid);

        return ret;
    }

    /**
     * <br>[機  能] メールテンプレートラベルを生成します。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @return List
     * @throws SQLException SQL実行時例外
     */
    private List<LabelValueBean> __getMTemplateLabel(Connection con)
                throws SQLException {

        //ソート済みのメールテンプレート情報を取得する
        Anp060Dao dao = new Anp060Dao(con);
        List<LabelValueBean> ret = dao.getTemplateList();

        return ret;
    }

    /**
     * <br>[機  能] 送信先 送信者一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param anp060Model パラメータモデル
     * @param con コネクション
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getSenderFullLavle(
            Anp060ParamModel anp060Model, Connection con)
    throws SQLException {

        //取得するユーザSID・グループSID
        String[] leftFull = anp060Model.getAnp060SenderList();
        return __getSenderLavle(leftFull, con);
    }

    /**
     * <br>[機  能] 送信先 送信者一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param left 取得するユーザSID・グループSID
     * @param con コネクション
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getSenderLavle(String[] left, Connection con)
    throws SQLException {

        ArrayList<LabelValueBean> ret = new ArrayList<LabelValueBean>();

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
}