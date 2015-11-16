package jp.groupsession.v2.enq.enq910;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.UsidSelectGrpNameDao;
import jp.groupsession.v2.cmn.dao.base.CmnCmbsortConfDao;
import jp.groupsession.v2.cmn.dao.base.CmnGroupmDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnCmbsortConfModel;
import jp.groupsession.v2.cmn.model.base.CmnGroupmModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.enq.GSConstEnquete;
import jp.groupsession.v2.enq.biz.EnqCommonBiz;
import jp.groupsession.v2.enq.dao.EnqCrtUserDao;
import jp.groupsession.v2.enq.enq210.Enq210Form;
import jp.groupsession.v2.enq.model.EnqAdmConfModel;
import jp.groupsession.v2.enq.model.EnqCrtUserModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 管理者設定 アンケート発信対象者設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Enq910Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Enq910Biz.class);
    /** グループ一覧の接頭辞 */
    protected static final String GROUP_PREFIX = "G";

    /**
     * <p>デフォルトコンストラクタ
     */
    public Enq910Biz() {
    }

    /**
     * <br>[機  能] アンケート発信対象者を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param enq910Model パラメータモデル
     * @param reqMdl リクエストモデル
     * @param con コネクション
     * @throws SQLException SQL実行例外
     */
    public void setConfData(Enq910ParamModel enq910Model,
                            RequestModel reqMdl,
                            Connection con) throws SQLException {

        log__.debug("アンケート発信対象者設定処理");

        if (enq910Model.getEnq910initFlg() != 1) {
            enq910Model.setEnq910initFlg(1);

            // セッション情報を取得
            BaseUserModel usModel = reqMdl.getSmodel();
            int sessionUsrSid = usModel.getUsrsid();

            // 管理者設定を取得
            EnqCommonBiz enqBiz = new EnqCommonBiz();
            EnqAdmConfModel admConf = enqBiz.getAdmConfData(con, sessionUsrSid);
            enq910Model.setEnq910TaisyoKbn(admConf.getEacKbnTaisyo());

            // アンケート発信対象者を取得
            EnqCrtUserDao dao = new EnqCrtUserDao(con);
            List<EnqCrtUserModel> getList = dao.selectList();
            if (getList == null || getList.isEmpty()) {
                return;
            }

            // アンケート発信対象者の一覧を作成
            String[] makeUser = null;
            List<String> senderList = new ArrayList<String>();
            for (EnqCrtUserModel crtBean : getList) {
                if (crtBean.getEcuKbn() == GSConstEnquete.TAISYO_KBN_USER
                 && __checkUser(con, crtBean.getEcuSid())) {
                    senderList.add(String.valueOf(crtBean.getEcuSid()));
                } else if (crtBean.getEcuKbn() == GSConstEnquete.TAISYO_KBN_GROUP
                        && __checkGroup(con, crtBean.getEcuSid())) {
                    senderList.add(GROUP_PREFIX + String.valueOf(crtBean.getEcuSid()));
                }
            }
            makeUser = (String[]) senderList.toArray(new String[senderList.size()]);

            enq910Model.setEnq910MakeSenderList(makeUser);
        }
    }

    /**
     * <br>[機  能] 初期表示情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param enq910Model パラメータモデル
     * @param reqMdl リクエストモデル
     * @param con コネクション
     * @throws SQLException SQL実行例外
     */
    public void setInitData(Enq910ParamModel enq910Model,
                            RequestModel reqMdl,
                            Connection con) throws SQLException {

        log__.debug("初期表示情報取得処理");

        // グループラベルをセット
        enq910Model.setEnq910GroupLabel(__createGroupLabel(enq910Model, reqMdl, con));

        // アンケート発信対象者をセット
        enq910Model.setEnq910AddSenderLabel(_createSenderLabel(enq910Model, con));
        // アンケート非発信対象者をセット
        enq910Model.setEnq910BelongSenderLabel(__createBelongSenderLabel(enq910Model, con));
    }

    /**
     * <br>[機  能] リスト中で選択されている発信対象者を追加する
     * <br>[解  説]
     * <br>[備  考]
     * @param list 元のグループリスト
     * @param selectSids 選択グループリスト
     * @return 選択グループを追加したグループリスト
     * @throws Exception 実行例外
     */
    public String[] getListToAdd(String[] list, String[] selectSids) throws Exception {

        log__.debug("リスト中で選択されている発信対象者の追加処理");

        String[] retList = null;
        List<String> newList = new ArrayList<String>();

        // 選択中リストの件数チェック
        if (selectSids == null || selectSids.length < 1) {
            return list;
        }

        // 元のリストに、選択されている発信対象者を追加したリストを作成する
        if (list != null && list.length > 0) {
            for (String str : list) {
                newList.add(str);
            }
        }
        for (String str : selectSids) {
            newList.add(str);
        }
        if (newList.size() > 0) {
            retList = (String[]) newList.toArray(new String[newList.size()]);
        }

        return retList;
    }

    /**
     * <br>[機  能] リスト中で選択されている発信対象者を削除する
     * <br>[解  説]
     * <br>[備  考]
     * @param list 元のリスト
     * @param selectSids 選択中のリスト
     * @return 選択中のリストを削除したリスト
     * @throws Exception 実行例外
     */
    public String[] getListToRemove(String[] list, String[] selectSids) throws Exception {

        log__.debug("リスト中で選択されている発信対象者の削除処理");

        String[] retList = null;
        String[] select = null;
        List<String> newList = new ArrayList<String>();

        // 元のリスト、選択中リストの件数チェック
        if (list == null || list.length < 1) {
            return list;
        }
        if (selectSids == null || selectSids.length < 1) {
            return list;
        }

        // 元のリストから、選択されている発信対象者を除外したリストを作成する
        select = Arrays.copyOf(selectSids, selectSids.length);
        Arrays.sort(select);
        for (String str : list) {
            if (Arrays.binarySearch(select, str) < 0) {
                newList.add(str);
            }
        }
        if (newList.size() > 0) {
            retList = (String[]) newList.toArray(new String[newList.size()]);
        }

        return retList;
    }

    /**
     * <br>[機  能] グループラベルを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param enq910Model パラメータモデル
     * @param reqMdl リクエストモデル
     * @param con コネクション
     * @return グループラベルリスト
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __createGroupLabel(Enq910ParamModel enq910Model,
                                                         RequestModel reqMdl,
                                                         Connection con) throws SQLException {

        log__.debug("グループラベル取得処理");

        ArrayList<LabelValueBean> groupLabel = new ArrayList<LabelValueBean>();
        GsMessage gsMsg = new GsMessage(reqMdl);

        // グループリスト取得
        GroupBiz grpBiz = new GroupBiz();
        ArrayList<GroupModel> grpList = grpBiz.getGroupCombList(con);

        // グループラベル作成
        groupLabel.add(new LabelValueBean(gsMsg.getMessage("cmn.grouplist"),
                                          String.valueOf(GSConstEnquete.ANSWER_GROUP_GRPLIST)));
        for (GroupModel grpBean : grpList) {
            groupLabel.add(new LabelValueBean(grpBean.getGroupName(),
                                             String.valueOf(grpBean.getGroupSid())));
        }

        return groupLabel;
    }

    /**
     * <br>[機  能] 対象者一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param enq910Model パラメータモデル
     * @param con コネクション
     * @return 対象者一覧
     * @throws SQLException SQL実行時例外
     */
    protected ArrayList<LabelValueBean> _createSenderLabel(Enq910ParamModel enq910Model,
                                                          Connection con) throws SQLException {

        ArrayList<LabelValueBean> ansCombo = new ArrayList<LabelValueBean>();

        ArrayList<Integer> grpSids = new ArrayList<Integer>();
        ArrayList<String> usrSids = new ArrayList<String>();

        //ユーザSIDとグループSIDを分離
        String[] selectAnsList = enq910Model.getEnq910MakeSenderList();
        if (selectAnsList == null) {
            return ansCombo;
        }
        for (String ansSid : selectAnsList) {
            String str = NullDefault.getString(ansSid, "-1");
            if (str.contains(new String(GROUP_PREFIX).subSequence(0, 1))) {
                //グループ
                grpSids.add(new Integer(str.substring(1, str.length())));
            } else {
                //ユーザ
                usrSids.add(str);
            }
        }

        //グループ情報取得
        UsidSelectGrpNameDao gdao = new UsidSelectGrpNameDao(con);
        ArrayList<GroupModel> grpList = gdao.selectGroupNmListOrderbyClass(grpSids);
        for (GroupModel grpMdl : grpList) {
            ansCombo.add(new LabelValueBean(
                    grpMdl.getGroupName(), GROUP_PREFIX + grpMdl.getGroupSid()));
        }

        //ユーザ情報取得
        UserBiz userBiz = new UserBiz();
        ArrayList<BaseUserModel> userList
                = userBiz.getBaseUserList(con,
                                        (String[]) usrSids.toArray(new String[usrSids.size()]));
        for (BaseUserModel usrMdl : userList) {
            ansCombo.add(
                    new LabelValueBean(usrMdl.getUsisei() + " " + usrMdl.getUsimei(),
                                                String.valueOf(usrMdl.getUsrsid())));
        }

        return ansCombo;
    }

    /**
     * <br>[機  能] 対象者一覧(未選択)を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param enq910Model パラメータ情報
     * @param con コネクション
     * @return 対象者一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __createBelongSenderLabel(
            Enq910ParamModel enq910Model, Connection con)
    throws SQLException {

     // 選択しているグループ一覧のSID
        int ansGrpSid = NullDefault.getInt(
                enq910Model.getEnq910GroupSid(), GSConstEnquete.ANSWER_GROUP_GRPLIST);

        ArrayList<LabelValueBean> ansCombo = new ArrayList<LabelValueBean>();

        String[] selectList = enq910Model.getEnq910MakeSenderList();
        if (ansGrpSid == Enq210Form.ANSER_GROUP_GRPLIST) {
            //グループを全て取得
            GroupDao dao = new GroupDao(con);
            CmnCmbsortConfDao sortDao = new CmnCmbsortConfDao(con);
            CmnCmbsortConfModel sortMdl = sortDao.getCmbSortData();
            ArrayList<GroupModel> allGpList = dao.getGroupTree(sortMdl);

            //選択済みのグループを除外
            List<String> selectGrpList = new ArrayList<String>();
            if (selectList != null) {
                selectGrpList = Arrays.asList(selectList);
            }

            for (GroupModel bean : allGpList) {
                if (!selectGrpList.contains(GROUP_PREFIX + bean.getGroupSid())) {
                    ansCombo.add(new LabelValueBean(
                            bean.getGroupName(),
                            String.valueOf(GROUP_PREFIX + bean.getGroupSid())));
                }
            }
        } else {

            //除外するユーザSID
            ArrayList<Integer> usrSids = new ArrayList<Integer>();
            if (selectList != null) {
                for (String selectSid : selectList) {
                    usrSids.add(new Integer(NullDefault.getInt(selectSid, -1)));
                }
            }

            UserBiz userBiz = new UserBiz();
            List<CmnUsrmInfModel> userList
                = userBiz.getBelongUserList(con, ansGrpSid, usrSids);

            for (CmnUsrmInfModel userMdl : userList) {
                ansCombo.add(
                        new LabelValueBean(userMdl.getUsiSei() + " " + userMdl.getUsiMei(),
                                String.valueOf(userMdl.getUsrSid())));
            }
        }

        return ansCombo;
    }

    /**
     * <br>[機  能] 指定したグループが、削除されていないかチェック
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param grpSid グループSID
     * @return true:存在する、false:存在しない
     * @throws SQLException SQL実行例外
     */
    private boolean __checkGroup(Connection con, long grpSid) throws SQLException {

        boolean ret = false;
        CmnGroupmModel mdl = new CmnGroupmModel();
        CmnGroupmDao dao = new CmnGroupmDao(con);
        mdl = dao.select((int) grpSid);
        if (mdl != null && mdl.getGrpJkbn() == GSConst.JTKBN_TOROKU) {
            ret = true;
        }
        return ret;
    }

    /**
     * <br>[機  能] 指定したユーザが、削除されていないかチェック
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param usrSid ユーザSID
     * @return true:存在する、false:存在しない
     * @throws SQLException SQL実行例外
     */
    private boolean __checkUser(Connection con, long usrSid) throws SQLException {

        boolean ret = false;
        CmnUsrmDao dao = new CmnUsrmDao(con);
        int kbn = dao.getUserJkbn((int) usrSid);
        if (kbn == GSConst.JTKBN_TOROKU) {
            ret = true;
        }
        return ret;
    }
}
