package jp.groupsession.v2.fil.fil250;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.fil.FilCommonBiz;
import jp.groupsession.v2.fil.FilTreeBiz;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.dao.FileCallConfDao;
import jp.groupsession.v2.fil.dao.FileDirectoryDao;
import jp.groupsession.v2.fil.model.FileCallConfModel;
import jp.groupsession.v2.fil.model.FileDirectoryModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 管理者設定 更新通知一括設定画面のビジネスロジック
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Fil250Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Fil250Biz.class);

    /** DBコネクション */
    private Connection con__ = null;
    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <p>Set Connection
     * @param con Connection
     * @param reqMdl RequestModel
     */
    public Fil250Biz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示を設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil250ParamModel
     * @throws SQLException SQL実行例外
     */
    public void setInitData(Fil250ParamModel paramMdl) throws SQLException {

        log__.debug("fil250Biz Start");

        //画面表示を設定する。
        setDsp(paramMdl);

        //ディレクトリツリーデータを設定する。
        __setTreeData(paramMdl);
        //フォルダパスを設定する。
        _setDirPath(con__, paramMdl);

    }

    /**
     * <br>[機  能] 画面表示を設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil250ParamModel
     * @throws SQLException SQL実行例外
     */
    public void setDsp(Fil250ParamModel paramMdl) throws SQLException {

        //キャビネットコンボを設定する。
        FilCommonBiz filCmnBiz = new FilCommonBiz(con__, reqMdl__);
        paramMdl.setFil250cabinetList(filCmnBiz.getCabinetLabel(con__, true));

        //更新通知対象者 ユーザリスト
        paramMdl.setFil250callUserCombo(_getCallUserCombo(con__, paramMdl));
        //更新通知対象者 グループコンボ
        paramMdl.setFil250callUserGroupCombo(__getGroupCombo());
        //更新通知対象者 候補リスト
        paramMdl.setFil250callUserRightCombo(__getCallUserRightCombo(paramMdl));
    }

    /**
     * <br>[機  能] 選択フォルダに更新通知設定しているユーザの一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil250ParamModel
     * @return 更新通知設定しているユーザの一覧
     * @throws SQLException SQL実行時例外
     */
    public String[] getCallUser(Fil250ParamModel paramMdl) throws SQLException {
        List<String> callUser = new ArrayList<String>();

        //選択フォルダに更新通知設定しているユーザ取得
        FileCallConfDao confDao = new FileCallConfDao(con__);
        String dirSid = paramMdl.getFil250DirSid();
        ArrayList<FileCallConfModel> confModelList = confDao.select(Integer.parseInt(dirSid));
        for (FileCallConfModel confMdl : confModelList) {
            callUser.add(String.valueOf(confMdl.getUsrSid()));
        }

        return (String[]) callUser.toArray(new String[callUser.size()]);
    }

    /**
     * <br>[機  能] フォルダパスを設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Fil250ParamModel
     * @throws SQLException SQL実行例外
     */
    protected void _setDirPath(Connection con, Fil250ParamModel paramMdl) throws SQLException {

        if (StringUtil.isNullZeroString(paramMdl.getFil250DirSid())) {
            return;
        }

        int dirPath = NullDefault.getInt(paramMdl.getFil250DirSid(), 0);

        FilCommonBiz filCmnBiz = new FilCommonBiz(con__, reqMdl__);

        String delPath = filCmnBiz.getDirctoryPath(dirPath, con);
        paramMdl.setFil250DirPath(delPath);

    }

    /**
     * <br>[機  能] フォルダ選択のディレクトリツリーを設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil250ParamModel
     * @throws SQLException SQL実行例外
     */
    private void __setTreeData(Fil250ParamModel paramMdl) throws SQLException {

        int cabiSid = NullDefault.getInt(paramMdl.getFil250SltCabinetSid(), 0);
        paramMdl.setTreeFormLv1(null);
        paramMdl.setTreeFormLv2(null);
        paramMdl.setTreeFormLv3(null);
        paramMdl.setTreeFormLv4(null);
        paramMdl.setTreeFormLv5(null);
        paramMdl.setTreeFormLv6(null);
        paramMdl.setTreeFormLv7(null);
        paramMdl.setTreeFormLv8(null);
        paramMdl.setTreeFormLv9(null);
        paramMdl.setTreeFormLv10(null);

        //ルートディレクトリSIDを設定する。
        FileDirectoryDao dirdao = new FileDirectoryDao(con__);
        FileDirectoryModel rootModel = dirdao.getRootDirectory(cabiSid);
        if (rootModel == null) {
            return;
        }
        paramMdl.setFil250RootDirSid(String.valueOf(rootModel.getFdrSid()));
        paramMdl.setFil250RootDirName(rootModel.getFdrName());

        //ツリー情報を取得する
        FilTreeBiz treeBiz = new FilTreeBiz(con__);
        paramMdl.setTreeFormLv1(treeBiz.getFileTree(cabiSid, GSConstFile.DIRECTORY_LEVEL_1));
        paramMdl.setTreeFormLv2(treeBiz.getFileTree(cabiSid, GSConstFile.DIRECTORY_LEVEL_2));
        paramMdl.setTreeFormLv3(treeBiz.getFileTree(cabiSid, GSConstFile.DIRECTORY_LEVEL_3));
        paramMdl.setTreeFormLv4(treeBiz.getFileTree(cabiSid, GSConstFile.DIRECTORY_LEVEL_4));
        paramMdl.setTreeFormLv5(treeBiz.getFileTree(cabiSid, GSConstFile.DIRECTORY_LEVEL_5));
        paramMdl.setTreeFormLv6(treeBiz.getFileTree(cabiSid, GSConstFile.DIRECTORY_LEVEL_6));
        paramMdl.setTreeFormLv7(treeBiz.getFileTree(cabiSid, GSConstFile.DIRECTORY_LEVEL_7));
        paramMdl.setTreeFormLv8(treeBiz.getFileTree(cabiSid, GSConstFile.DIRECTORY_LEVEL_8));
        paramMdl.setTreeFormLv9(treeBiz.getFileTree(cabiSid, GSConstFile.DIRECTORY_LEVEL_9));
        paramMdl.setTreeFormLv10(treeBiz.getFileTree(cabiSid, GSConstFile.DIRECTORY_LEVEL_10));
    }

    /**
     * <br>[機  能] 更新通知対象者 ユーザコンボを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Fil250ParamModel
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    protected ArrayList<LabelValueBean> _getCallUserCombo(Connection con, Fil250ParamModel paramMdl)
    throws SQLException {

        String[] usrSids = paramMdl.getFil250SvCallUser();
        UserBiz userBiz = new UserBiz();
        ArrayList<BaseUserModel> ulist
                = userBiz.getBaseUserList(con, usrSids);

        ArrayList<LabelValueBean> ret = new ArrayList<LabelValueBean>();
        for (BaseUserModel umodel : ulist) {
            LabelValueBean label = new LabelValueBean();
            label.setLabel(umodel.getUsisei() + " " + umodel.getUsimei());
            label.setValue(String.valueOf(umodel.getUsrsid()));
            ret.add(label);
        }

        return ret;
    }

    /**
     * <br>[機  能] グループコンボを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getGroupCombo()
    throws SQLException {
        ArrayList<LabelValueBean> ret = new ArrayList<LabelValueBean>();
        GroupBiz groupBiz = new GroupBiz();
        ArrayList<GroupModel> gpList = groupBiz.getGroupCombList(con__);
        LabelValueBean label = new LabelValueBean();

        GsMessage gsMsg = new GsMessage(reqMdl__);
        String textSelect = gsMsg.getMessage("cmn.select.plz");
        label.setLabel(textSelect);
        label.setValue("-1");
        ret.add(label);

        for (GroupModel gmodel : gpList) {
            label = new LabelValueBean();
            label.setLabel(gmodel.getGroupName());
            label.setValue(String.valueOf(gmodel.getGroupSid()));
            ret.add(label);
        }
        return ret;
    }

    /**
     * <br>[機  能] 更新通知対象者(候補) ユーザコンボを取得する
     * @param paramMdl Fil250ParamModel
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getCallUserRightCombo(Fil250ParamModel paramMdl)
    throws SQLException {

        ArrayList<LabelValueBean> ret = new ArrayList<LabelValueBean>();
        //アクセス権限 グループコンボ選択値
        int sltGrp = NullDefault.getInt(
                paramMdl.getFil250callUserSltGroup(), -1);

        //除外するユーザSID
        String[] leftAdm = paramMdl.getFil250SvCallUser();


        if (sltGrp > -1) {

            //除外するユーザSID
            ArrayList<Integer> usrSids = new ArrayList<Integer>();
            if (leftAdm != null) {
                for (int i = 0; i < leftAdm.length; i++) {
                    usrSids.add(new Integer(NullDefault.getInt(leftAdm[i], -1)));
                }
            }

            UserBiz userBiz = new UserBiz();
            List<CmnUsrmInfModel> usList
                = userBiz.getBelongUserList(con__, sltGrp, usrSids);

            for (int i = 0; i < usList.size(); i++) {
                CmnUsrmInfModel cuiMdl = usList.get(i);
                LabelValueBean label = new LabelValueBean();
                label.setLabel(cuiMdl.getUsiSei() + " " + cuiMdl.getUsiMei());
                label.setValue(String.valueOf(cuiMdl.getUsrSid()));
                ret.add(label);
            }
        }

        return ret;
    }
}