package jp.groupsession.v2.rng.rng110;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.rng.dao.RngChannelTemplateDao;
import jp.groupsession.v2.rng.dao.RngChannelTemplateUserDao;
import jp.groupsession.v2.rng.model.RngChannelTemplateModel;
import jp.groupsession.v2.rng.model.RngChannelTemplateUserModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 稟議 経路テンプレート登録画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng110Biz {

    /** Loggingインスタンス */
    private static Log log__ = LogFactory.getLog(Rng110Biz.class);

    /** コネクション */
    private Connection con__ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     */
    public Rng110Biz() {
    }

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     */
    public Rng110Biz(Connection con) {
        con__ = con;
    }

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説] 処理モード = 編集の場合、経路テンプレート情報を設定する
     * <br>[備  考]
     * @param req リクエスト
     * @param paramMdl パラメータ情報
     * @param userSid ユーザSID
     * @throws SQLException SQL実行例外
     * @throws IOException 添付ファイルの操作に失敗
     * @throws IOToolsException 添付ファイルの操作に失敗
     */
    public void setInitData(HttpServletRequest req, Rng110ParamModel paramMdl, int userSid)
    throws IOException, IOToolsException, SQLException {

        //経路テンプレート一覧からの遷移、かつ処理モード = 編集 の場合
        //経路テンプレート情報を取得する
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        if (cmd.equals("editkeiro") && paramMdl.getRngRctCmdMode() == RngConst.RNG_CMDMODE_EDIT) {

            int rctSid = paramMdl.getRctSid();

            //経路テンプレート情報を設定
            RngChannelTemplateDao rctDao = new RngChannelTemplateDao(con__);
            RngChannelTemplateModel rctData = rctDao.select(rctSid, userSid);
            paramMdl.setRng110name(rctData.getRctName());

            //経路テンプレートユーザ情報を設定
            RngChannelTemplateUserDao rctUserDao = new RngChannelTemplateUserDao(con__);
            List<RngChannelTemplateUserModel> rctUserList = rctUserDao.getRctUserList(rctSid);
            List<String> apprUser = new ArrayList<String>();
            List<String> confirmUser = new ArrayList<String>();

            for (RngChannelTemplateUserModel rctUserMdl : rctUserList) {
                if (rctUserMdl.getRcuType() == RngConst.RNG_RNCTYPE_APPR) {
                    apprUser.add(String.valueOf(rctUserMdl.getUsrSid()));
                } else if (rctUserMdl.getRcuType() == RngConst.RNG_RNCTYPE_CONFIRM) {
                    confirmUser.add(String.valueOf(rctUserMdl.getUsrSid()));
                }
            }
            paramMdl.setRng110apprUser((String[]) apprUser.toArray(new String[apprUser.size()]));
            paramMdl.setRng110confirmUser(
                    (String[]) confirmUser.toArray(new String[confirmUser.size()]));

        }

        //グループが未選択の場合、デフォルトグループを設定
        if (paramMdl.getRng110group() == -1) {
            GroupBiz grpBz = new GroupBiz();
            int defGrp = grpBz.getDefaultGroupSid(userSid, con__);
            paramMdl.setRng110group(defGrp);
        }


        GsMessage gsMsg = new GsMessage(req);

        //グループコンボを設定する。
        GroupBiz gpBiz = new GroupBiz();
        List<LabelValueBean> groupLabel = gpBiz.getGroupCombLabelList(con__, true, gsMsg);
        groupLabel.remove(0);
        paramMdl.setRng110groupList(groupLabel);

        //ユーザ一覧、承認経路、最終確認を設定する。
        String[] apprUser = paramMdl.getRng110apprUser();
        String[] confirmUser = paramMdl.getRng110confirmUser();
        List<String> notUser = new ArrayList<String>();
        if (apprUser != null) {
            notUser.addAll((List<String>) Arrays.asList(apprUser));
        }
        if (confirmUser != null) {
            notUser.addAll((List<String>) Arrays.asList(confirmUser));
        }
        notUser.add(String.valueOf(userSid));
        UserBiz userBiz = new UserBiz();
        paramMdl.setRng110userList(
                userBiz.getNormalUserLabelList(con__, paramMdl.getRng110group(),
                                        (String[]) notUser.toArray(new String[notUser.size()]),
                                        false, gsMsg));

        paramMdl.setRng110apprUserList(
                __sortLabelList(userBiz.getUserLabelList(con__, apprUser), apprUser));
        paramMdl.setRng110confirmUserList(
                __sortLabelList(userBiz.getUserLabelList(con__, confirmUser), confirmUser));

        paramMdl.setRng110UserSid(userSid);

        log__.debug("End");
    }

    /**
     * <br>[機  能] 稟議情報の登録処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param cntCon MlCountMtController
     * @param userSid セッションユーザSID
     * @throws Exception 実行例外
     */
    public void entryKeiroTemplate(Rng110ParamModel paramMdl,
                                    MlCountMtController cntCon,
                                    int userSid)
    throws Exception {
        log__.debug("START");

        int rctCmdMode = paramMdl.getRngRctCmdMode();
        UDate now = new UDate();

        int rctSid = paramMdl.getRctSid();
        if (rctCmdMode == RngConst.RNG_CMDMODE_ADD) {
            //新規登録の場合は経路テンプレートSIDを採番する
            rctSid = (int) cntCon.getSaibanNumber(RngConst.SBNSID_RINGI,
                                                RngConst.SBNSID_SUB_RINGI_CHANNEL_TEMPLATE,
                                                userSid);
        }

        //経路テンプレート情報の登録
        RngChannelTemplateModel model = new RngChannelTemplateModel();
        model.setRctSid(rctSid);
        model.setUsrSid(userSid);
        model.setRctName(paramMdl.getRng110name());
        model.setRctAuid(userSid);
        model.setRctEdate(now);
        RngChannelTemplateDao rctDao = new RngChannelTemplateDao(con__);
        if (rctCmdMode == RngConst.RNG_CMDMODE_ADD) {
            model.setRctAdate(now);
            model.setRctEuid(userSid);
            rctDao.insert(model);
        } else if (rctCmdMode == RngConst.RNG_CMDMODE_EDIT) {
            rctDao.update(model);
        }

        //経路テンプレートユーザ情報の登録
        RngChannelTemplateUserDao rctUserDao = new RngChannelTemplateUserDao(con__);

        if (rctCmdMode == RngConst.RNG_CMDMODE_EDIT) {
            //処理モード = 編集の場合、経路テンプレートユーザ情報の削除を行う
            rctUserDao.delete(rctSid);
        }

        RngChannelTemplateUserModel rctUserModel = new RngChannelTemplateUserModel();
        rctUserModel.setRctSid(rctSid);
        rctUserModel.setRcuAuid(userSid);
        rctUserModel.setRcuAdate(now);
        rctUserModel.setRcuEuid(userSid);
        rctUserModel.setRcuEdate(now);

        int rcuSort = 1;

        //承認経路の登録
        rcuSort = __entryRctUser(rctUserModel, rctCmdMode, paramMdl.getRng110apprUser(),
                                RngConst.RNG_RNCTYPE_APPR, rcuSort);
        //最終確認の登録
        __entryRctUser(rctUserModel, rctCmdMode, paramMdl.getRng110confirmUser(),
                    RngConst.RNG_RNCTYPE_CONFIRM, rcuSort);

        log__.debug("End");
    }

    /**
     * <br>[機  能] 稟議情報の削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param userSid セッションユーザSID
     * @throws Exception 実行例外
     */
    public void deleteKeiroTemplate(Rng110ParamModel paramMdl,
                                    int userSid)
    throws Exception {
        log__.debug("START");

        int rctSid = paramMdl.getRctSid();

        //経路テンプレート情報の削除
        RngChannelTemplateDao rctDao = new RngChannelTemplateDao(con__);
        rctDao.delete(rctSid);

        //経路テンプレートユーザ情報の削除
        RngChannelTemplateUserDao rctUserDao = new RngChannelTemplateUserDao(con__);
        rctUserDao.delete(rctSid);

        log__.debug("End");
    }

    /**
     * <br>[機  能] 経路テンプレート経路ユーザ情報の登録を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param rctUserModel 経路テンプレート経路ユーザ情報
     * @param cmdMode 処理モード
     * @param userList ユーザ一覧
     * @param rcuType 承認者種別
     * @param sort 経路順
     * @return 経路順
     * @throws SQLException SQL実行時例外
     */
    private int __entryRctUser(RngChannelTemplateUserModel rctUserModel,
                                int cmdMode, String[] userList, int rcuType, int sort)
    throws SQLException {

        if (userList == null || userList.length < 1) {
            return sort;
        }

        RngChannelTemplateUserDao rctUserDao = new RngChannelTemplateUserDao(con__);

        rctUserModel.setRcuType(rcuType);
        for (String user : userList) {
            rctUserModel.setUsrSid(Integer.parseInt(user));
            rctUserModel.setRcuSort(sort++);
            rctUserDao.insert(rctUserModel);
        }

        return sort;
    }

    /**
     * <br>[機  能] ラベルリストを指定した値順に並び替える
     * <br>[解  説]
     * <br>[備  考]
     * @param labelList ラベルリスト
     * @param values 並び順
     * @return 並び替え後のラベルリスト
     */
    private List<LabelValueBean> __sortLabelList(List<LabelValueBean> labelList, String[] values) {

        List<LabelValueBean> sortLabelList = new ArrayList<LabelValueBean>();

        if (values == null || values.length <= 0) {
            return labelList;
        }

        for (String value : values) {
            int intValue = Integer.parseInt(value);
            for (LabelValueBean label : labelList) {
                if (intValue == Integer.parseInt(label.getValue())) {
                    sortLabelList.add(label);
                }
            }
        }

        return sortLabelList;
    }
}
