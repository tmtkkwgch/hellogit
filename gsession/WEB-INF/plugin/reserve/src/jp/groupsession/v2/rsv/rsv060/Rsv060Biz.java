package jp.groupsession.v2.rsv.rsv060;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.UsidSelectGrpNameDao;
import jp.groupsession.v2.cmn.dao.base.CmnCmbsortConfDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnCmbsortConfModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.rsv.AbstractReserveBiz;
import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.rsv.dao.RsvAccessConfDao;
import jp.groupsession.v2.rsv.dao.RsvBinDao;
import jp.groupsession.v2.rsv.dao.RsvSisAdmDao;
import jp.groupsession.v2.rsv.dao.RsvSisDataDao;
import jp.groupsession.v2.rsv.dao.RsvSisGrpDao;
import jp.groupsession.v2.rsv.dao.RsvSisKyrkDao;
import jp.groupsession.v2.rsv.dao.RsvSisYrkDao;
import jp.groupsession.v2.rsv.model.RsvAccessConfModel;
import jp.groupsession.v2.rsv.model.RsvSisDataModel;
import jp.groupsession.v2.rsv.model.RsvSisGrpModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 施設予約 施設グループ登録画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv060Biz extends AbstractReserveBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rsv060Biz.class);
    /** リクエスト情報 */
    protected RequestModel reqMdl_ = null;
    /** コネクション */
    protected Connection con_ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param reqMdl リクエスト情報
     * @param con コネクション
     */
    public Rsv060Biz(RequestModel reqMdl, Connection con) {
        reqMdl_ = reqMdl;
        con_ = con;
    }

    /**
     * <br>[機  能] 処理権限判定
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv060ParamModel
     * @return true:処理実行可 false:処理実行負荷
     * @throws SQLException SQL実行時例外
     */
    public boolean isPossibleToProcess(Rsv060ParamModel paramMdl)
        throws SQLException {

        //管理者である or この施設グループの管理者である
        return _isGroupEditAuthority(reqMdl_,
                                      con_,
                                      paramMdl.getRsv060ProcMode(),
                                      paramMdl.getRsv060EditGrpSid());
    }

    /**
     * <br>[機  能] 施設グループ編集可否フラグを取得する
     * <br>[解  説]
     * <br>[備  考] 施設グループの編集が可能か判定しフラグをセットする。
     *              (※下記のいずれかの条件を満たすか)
     *              1:管理者グループに所属している。
     *              2:いずれかの施設グループの管理者として設定されている。
     *              3:「権限設定をしない」となっている施設グループ1つでも存在する。
     *
     * @return true:編集可 false:編集不可
     * @throws SQLException SQL実行時例外
     */
    public boolean getGroupEditFlg() throws SQLException {

        return _isAllGroupEditAuthority(reqMdl_, con_);

    }

    /**
     * <br>[機  能] 画面表示処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv060ParamModel
     * @throws SQLException SQL実行時例外
     */
    public void setGroupData(Rsv060ParamModel paramMdl) throws SQLException {

        boolean initFlg = paramMdl.isRsv060InitFlg();
        String procMode = paramMdl.getRsv060ProcMode();
        log__.debug("***Rsv060procMode = " + procMode);

        //初期表示 & 編集モード
        if (initFlg && procMode.equals(GSConstReserve.PROC_MODE_EDIT)) {

            //施設グループデータ取得
            RsvSisGrpDao grpDao = new RsvSisGrpDao(con_);
            RsvSisGrpModel grpMdl = grpDao.select(paramMdl.getRsv060EditGrpSid());
            if (grpMdl != null) {
                paramMdl.setRsv060GrpId(grpMdl.getRsgId());
                paramMdl.setRsv060GrpName(grpMdl.getRsgName());
                paramMdl.setRsv060SelectedSisetuKbn(grpMdl.getRskSid());
                paramMdl.setRsv060GrpAdmKbn(grpMdl.getRsgAdmKbn());
                paramMdl.setRsv060apprKbn(grpMdl.getRsgApprKbn());
                paramMdl.setRsv060DataExists(true);

                //【権限設定を行う】に設定されている
                if (grpMdl.getRsgAdmKbn() == GSConstReserve.RSG_ADM_KBN_OK) {

                    //管理者ユーザ一覧取得
                    ArrayList<String> selectUser =
                        grpDao.getDefaultAdmUser(paramMdl.getRsv060EditGrpSid());

                    if (!selectUser.isEmpty()) {
                        String[] saveUserWork = new String[selectUser.size()];
                        for (int i = 0; i < selectUser.size(); i++) {
                            saveUserWork[i] = selectUser.get(i);
                        }
                        paramMdl.setSaveUser(saveUserWork);
                    }
                }


                if (grpMdl.getRsgAcsLimitKbn() == GSConstReserve.RSV_ACCESS_MODE_FREE) {
                    //アクセス権限を設定いない
                    paramMdl.setRsv060AccessDspFlg(false);

                } else {
                    //アクセス権限を設定している
                    paramMdl.setRsv060AccessDspFlg(true);
                    paramMdl.setRsv060AccessKbn(grpMdl.getRsgAcsLimitKbn());
                    RsvAccessConfDao accessDao = new RsvAccessConfDao(con_);
                    ArrayList<String> editList = new ArrayList<String>();
                    ArrayList<String> readList = new ArrayList<String>();
                    List<RsvAccessConfModel> acsConfList
                            = accessDao.getUsrData(paramMdl.getRsv060EditGrpSid(), -1);

                    for (int i = 0; i < acsConfList.size(); i++) {
                        if (acsConfList.get(i).getRacAuth()
                                == GSConstReserve.RSV_ACCESS_KBN_WRITE) {
                            if (acsConfList.get(i).getUsrSid() != -1) {
                                editList.add(String.valueOf(acsConfList.get(i).getUsrSid()));
                            }
                            if (acsConfList.get(i).getGrpSid() != -1) {
                                editList.add(String.valueOf(GSConstReserve.GROUP_HEADSTR
                                                        + acsConfList.get(i).getGrpSid()));
                            }

                        } else {
                            if (acsConfList.get(i).getUsrSid() != -1) {
                                readList.add(String.valueOf(acsConfList.get(i).getUsrSid()));
                            }
                            if (acsConfList.get(i).getGrpSid() != -1) {
                                readList.add(String.valueOf(GSConstReserve.GROUP_HEADSTR
                                                        + acsConfList.get(i).getGrpSid()));
                            }

                        }

                    }
                    String[] usrGrpSids = (String[]) editList.toArray(new String[editList.size()]);
                    String[] usrGrpSidsRead
                        = (String[]) readList.toArray(new String[readList.size()]);
                    paramMdl.setRsv060memberSid(usrGrpSids);
                    paramMdl.setRsv060memberSidRead(usrGrpSidsRead);

                }


            } else {
                paramMdl.setRsv060DataExists(false);
            }
        }

        if (paramMdl.getRsv060ProcMode().equals(GSConstReserve.PROC_MODE_EDIT)
            && !initFlg) {

            RsvSisGrpDao grpDao = new RsvSisGrpDao(con_);
            RsvSisGrpModel grpMdl = grpDao.select(paramMdl.getRsv060EditGrpSid());
            if (grpMdl != null) {
                paramMdl.setRsv060DataExists(true);
            } else {
                paramMdl.setRsv060DataExists(false);
            }
        }

        //施設区分コンボ生成
        ArrayList<LabelValueBean> grpKbnList = _getGroupKbnComboList(con_);
        paramMdl.setRsv060SisetuLabelList(grpKbnList);
        if (paramMdl.getRsv060SelectedSisetuKbn() < 0) {
            LabelValueBean lbl = grpKbnList.get(0);
            paramMdl.setRsv060SelectedSisetuKbn(Integer.parseInt(lbl.getValue()));
        }


        //グループコンボ生成
        ArrayList<LabelValueBean> grpList = __getGroupComboList(true);
        paramMdl.setRsv060GrpLabelList(grpList);

        //管理者リスト作成
        String[] saveUser = paramMdl.getSaveUser();
        UserBiz userBiz = new UserBiz();
        if (saveUser != null && saveUser.length > 0) {
            paramMdl.setRsv060AdmUser(__getGrpUserLabel(saveUser, con_));
        }

        //追加用管理者リスト（右）を作成する
        //グループコンボで選択されているグループのユーザ取得
        int grpComboSid = paramMdl.getRsv060SelectedGrpComboSid();
        ArrayList<LabelValueBean> notAdminLabelList = new ArrayList<LabelValueBean>();
        //グループ一覧の場合
        if (grpComboSid == Integer.valueOf(GSConstReserve.GROUP_COMBO_VALUE)) {

            ArrayList<String> fullGrepList = new ArrayList<String>();
            if (saveUser != null && saveUser.length > 0) {
                for  (int i = 0; i < saveUser.length; i++) {
                    String memSid = saveUser[i];
                    if (!fullGrepList.contains(memSid)) {
                        fullGrepList.add(memSid);
                    }
                }
            }

            //グループを全て取得
            GroupDao dao = new GroupDao(con_);
            CmnCmbsortConfDao sortDao = new CmnCmbsortConfDao(con_);
            CmnCmbsortConfModel sortMdl = sortDao.getCmbSortData();
            ArrayList<GroupModel> allGpList = dao.getGroupTree(sortMdl);


            for (GroupModel bean : allGpList) {
                if (!fullGrepList.contains(String.valueOf("G" + bean.getGroupSid()))) {
                    notAdminLabelList.add(new LabelValueBean(
                            bean.getGroupName(), String.valueOf("G" + bean.getGroupSid())));
                }
            }

        } else if (grpComboSid >= 0) {
            //各グループを選択した場合

            //追加用ユーザ一覧から除外するユーザ一覧作成する（Gがあった場合は-1）
            ArrayList<Integer> usrSids = new ArrayList<Integer>();
            for (String str : saveUser) {
                usrSids.add(new Integer(NullDefault.getInt(str, -1)));
            }

            //追加用ユーザ一覧の取得
            List<CmnUsrmInfModel> usList
            = userBiz.getBelongUserList(con_, grpComboSid, usrSids);

            for (int i = 0; i < usList.size(); i++) {
                CmnUsrmInfModel cuiMdl = usList.get(i);
                notAdminLabelList.add(new LabelValueBean(cuiMdl.getUsiSei() + cuiMdl.getUsiMei(),
                                 String.valueOf(cuiMdl.getUsrSid())));
            }

        }
        paramMdl.setRsv060NotAdmUser(notAdminLabelList);



        //アクセス権限 グループコンボを設定
        paramMdl.setRsv060GroupList(__getGroupComboList(true));

        //アクセス権限 グループコンボ選択値
        int forumSltGp = paramMdl.getRsv060groupSid();
        List<LabelValueBean> labelListAdd = new ArrayList<LabelValueBean>();

        ArrayList<String> fullGrepList = new ArrayList<String>();
        //除外するグループSID
        String[] memberSid = paramMdl.getRsv060memberSid();
        if (memberSid != null && memberSid.length > 0) {
            for  (int i = 0; i < memberSid.length; i++) {
                String memSid = memberSid[i];
                if (!fullGrepList.contains(memSid)) {
                    fullGrepList.add(memSid);
                }
            }
        }

        //閲覧メンバーを除外リストに追加
        String[] memberSidRead = paramMdl.getRsv060memberSidRead();
        if (memberSidRead != null && memberSidRead.length > 0) {
            for  (int i = 0; i < memberSidRead.length; i++) {
                String readMemSid = memberSidRead[i];
                if (!fullGrepList.contains(readMemSid)) {
                    fullGrepList.add(readMemSid);
                }
            }
        }

        if (forumSltGp == Integer.parseInt(GSConstReserve.GROUP_COMBO_VALUE)) {
            //グループを全て取得
            GroupDao dao = new GroupDao(con_);
            CmnCmbsortConfDao sortDao = new CmnCmbsortConfDao(con_);
            CmnCmbsortConfModel sortMdl = sortDao.getCmbSortData();
            ArrayList<GroupModel> allGpList = dao.getGroupTree(sortMdl);


            for (GroupModel bean : allGpList) {
                if (!fullGrepList.contains(String.valueOf("G" + bean.getGroupSid()))) {
                    labelListAdd.add(new LabelValueBean(
                            bean.getGroupName(), String.valueOf("G" + bean.getGroupSid())));
                }
            }

        } else {
            //追加用ユーザを取得する
            ArrayList<Integer> usrSids = new ArrayList<Integer>();
            for (String str : paramMdl.getRsv060memberSid()) {
                usrSids.add(new Integer(NullDefault.getInt(str, -1)));
            }

            for (String str : paramMdl.getRsv060memberSidRead()) {
                usrSids.add(new Integer(NullDefault.getInt(str, -1)));
            }

            List<CmnUsrmInfModel> usList
                = userBiz.getBelongUserList(con_, paramMdl.getRsv060groupSid(), usrSids);

            for (int i = 0; i < usList.size(); i++) {
                CmnUsrmInfModel cuiMdl = usList.get(i);
                labelListAdd.add(new LabelValueBean(cuiMdl.getUsiSei() + cuiMdl.getUsiMei(),
                                 String.valueOf(cuiMdl.getUsrSid())));
            }
        }

        //追加用メンバー一覧
        paramMdl.setRsv060RightUserList(labelListAdd);


        //編集メンバ追加一覧
        paramMdl.setRsv060LeftUserList(__getGrpUserLabel(paramMdl.getRsv060memberSid(), con_));

        //閲覧メンバ追加一覧
        paramMdl.setRsv060LeftUserListRead(
                __getGrpUserLabel(paramMdl.getRsv060memberSidRead(), con_));


        //編集モード時は所属施設リストを取得
        if (procMode.equals(GSConstReserve.PROC_MODE_EDIT)) {
            RsvSisDataDao dataDao = new RsvSisDataDao(con_);
            ArrayList<RsvSisDataModel> rsv060SyozokuList =
                dataDao.selectGrpSisetuList(paramMdl.getRsv060EditGrpSid());
            paramMdl.setRsv060SyozokuList(rsv060SyozokuList);
        }

        if (paramMdl.getRsv060GrpAdmKbn() < 0) {
            //権限設定デフォルト値
            paramMdl.setRsv060GrpAdmKbn(GSConstReserve.RSG_ADM_KBN_OK);
        }
        //初期表示フラグOFF
        paramMdl.setRsv060InitFlg(false);

    }

    /**
     * <br>[機  能] 施設グループ削除処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv060ParamModel
     * @throws SQLException SQL実行時例外
     */
    public void doGrpDelete(Rsv060ParamModel paramMdl) throws SQLException {

        int grpSid = paramMdl.getRsv060EditGrpSid();

        //施設情報から削除対象の施設SIDを取得
        RsvSisDataDao dataDao = new RsvSisDataDao(con_);
        ArrayList<Integer> delRsdSidList = dataDao.getDeleteGrpSisetuList(grpSid);

        //削除対象の施設に予約チェックがあれば除外
        ArrayList<String> convKeyArray = new ArrayList<String>();
        String[] ikkatuKey = paramMdl.getRsvIkkatuTorokuKey();

        if (ikkatuKey != null && ikkatuKey.length > 0) {
            for (String key : ikkatuKey) {
                String keySid = key.substring(key.indexOf("-") + 1);
                for (int delKey : delRsdSidList) {
                    if (Integer.parseInt(keySid) != delKey) {
                        convKeyArray.add(key);
                    }
                }
            }
            String[] convKeyStr = null;
            if (convKeyArray.isEmpty()) {
                convKeyStr = new String[0];
            } else {
                convKeyStr =
                    (String[]) convKeyArray.toArray(
                            new String[convKeyArray.size()]);
            }
            paramMdl.setRsvIkkatuTorokuKey(convKeyStr);
        }

        //施設グループを削除
        RsvSisGrpDao grpDao = new RsvSisGrpDao(con_);
        RsvSisGrpModel grpParam = new RsvSisGrpModel();
        grpParam.setRsgSid(grpSid);
        grpDao.delete(grpParam);

        //施設グループ管理者を削除
        RsvSisAdmDao admDao = new RsvSisAdmDao(con_);
        admDao.delete(grpSid);

        //施設情報を削除
        dataDao.delete(grpSid);

        if (!delRsdSidList.isEmpty()) {
            //施設予約情報を削除
            RsvSisYrkDao yrkDao = new RsvSisYrkDao(con_);
            ArrayList<Integer> rsySidList = yrkDao.getRsySidListSetSisSids(delRsdSidList);
            yrkDao.delete(delRsdSidList);
            //施設予約拡張別情報削除
            if (rsySidList != null && rsySidList.size() > 0) {
                RsvSisKyrkDao kyrkDao = new RsvSisKyrkDao(con_);
                kyrkDao.delete(rsySidList);
            }

            for (int i = 0; i < delRsdSidList.size(); i++) {

                //バイナリ情報を削除
                //施設・設備
                __delSisetuTempData(GSConstReserve.TEMP_IMG_KBN_SISETU,
                                    delRsdSidList.get(i));

                //場所・地図
                __delSisetuTempData(GSConstReserve.TEMP_IMG_KBN_PLACE,
                                    delRsdSidList.get(i));
            }
        }
    }

    /**
     * <br>[機  能] 施設添付情報を編集する
     * <br>[解  説]
     * <br>[備  考]
     * @param imgKbn 画像区分
     * @param sisetuSid 施設SID
     * @throws SQLException SQL実行例外
     */
    private void __delSisetuTempData(int imgKbn,
                                      int sisetuSid) throws SQLException {


        //バイナリー情報の論理削除
        RsvBinDao rsvBinDao = new RsvBinDao(con_);
        rsvBinDao.deleteBinfForSisetu(sisetuSid, imgKbn);

        //施設添付情報の削除
        rsvBinDao.deleteSisetuBin(sisetuSid, imgKbn);
    }

    /**
     * <br>[機  能] 処理対象の施設グループ名称を取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv060ParamModel
     * @return grpName 施設グループ名称
     * @throws SQLException SQL実行時例外
     */
    public String getGroupName(Rsv060ParamModel paramMdl) throws SQLException {

        String grpName = "";

        RsvSisGrpDao grpDao = new RsvSisGrpDao(con_);
        RsvSisGrpModel grpMdl = grpDao.select(paramMdl.getRsv060EditGrpSid());
        if (grpMdl != null) {
            grpName = grpMdl.getRsgName();
        }

        return grpName;
    }

    /**
     * <br>[機  能] グループコンボリストを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param addGgrpListFlg trueの場合グループ一覧を含める。
     * @return ret グループコンボリスト
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getGroupComboList(boolean addGgrpListFlg)
        throws SQLException {

        log__.debug("グループコンボリストを取得");
        GsMessage gsMsg = new GsMessage(reqMdl_);
        GroupBiz groupBiz = new GroupBiz();
        List <GroupModel> groupList = groupBiz.getGroupCombList(con_);

        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        labelList.add(new LabelValueBean(gsMsg.getMessage("cmn.select.plz"), "-1"));

        /** メッセージ グループ一覧 **/
        String strGroupList = gsMsg.getMessage("cmn.grouplist");
        if (addGgrpListFlg) {
            labelList.add(
                    new LabelValueBean(strGroupList,
                            GSConstReserve.GROUP_COMBO_VALUE));
        }
        for (GroupModel mdl : groupList) {
            labelList.add(
                    new LabelValueBean(mdl.getGroupName(),
                            String.valueOf(mdl.getGroupSid())));
        }

        return labelList;
    }

    /**
     * <br>[機  能] ユーザ追加処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv060ParamModel
     * @throws SQLException SQL実行時例外
     */
    public void doUserAdd(Rsv060ParamModel paramMdl) throws SQLException {

        paramMdl.setSaveUser(
                getAddMember(paramMdl.getRsv060SelectedRight(), paramMdl.getSaveUser()));
    }

    /**
     * <br>[機  能] ユーザ削除処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv060ParamModel
     * @throws SQLException SQL実行時例外
     */
    public void doUserDel(Rsv060ParamModel paramMdl) throws SQLException {

        CommonBiz cmnBiz = new CommonBiz();
        paramMdl.setSaveUser(
                cmnBiz.getDeleteMember(paramMdl.getRsv060SelectedLeft(), paramMdl.getSaveUser()));
    }

    /**
     * <br>[機  能] メンバー一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param left 取得するユーザSID・グループSID
     * @param con コネクション
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getGrpUserLabel(String[] left, Connection con)
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

        LabelValueBean lavelBean = null;
        if (grpSids.size() > 0) {
            //グループ情報取得
            UsidSelectGrpNameDao gdao = new UsidSelectGrpNameDao(con);
            ArrayList<GroupModel> glist = gdao.selectGroupNmListOrderbyClass(grpSids);
            for (GroupModel gmodel : glist) {
                lavelBean = new LabelValueBean();
                lavelBean.setLabel(gmodel.getGroupName());
                lavelBean.setValue("G" + String.valueOf(gmodel.getGroupSid()));
                ret.add(lavelBean);
            }

        }
        //ユーザ情報取得
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