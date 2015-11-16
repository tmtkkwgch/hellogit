package jp.groupsession.v2.wml.wml280;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.groupsession.v2.cmn.GSConstWebmail;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.MailBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.UsidSelectGrpNameDao;
import jp.groupsession.v2.cmn.dao.base.CmnCmbsortConfDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmInfDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnCmbsortConfModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.wml.dao.base.WmlDestlistAccessConfDao;
import jp.groupsession.v2.wml.dao.base.WmlDestlistAddressDao;
import jp.groupsession.v2.wml.dao.base.WmlDestlistDao;
import jp.groupsession.v2.wml.model.base.WmlDestlistAccessConfModel;
import jp.groupsession.v2.wml.model.base.WmlDestlistAddressModel;
import jp.groupsession.v2.wml.model.base.WmlDestlistModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] WEBメール 送信先リスト登録画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml280Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Wml280Biz.class);

    /**
     * <br>[機  能] 初期表示設定を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @throws Exception 実行時例外
     */
    public void setInitData(Connection con, Wml280ParamModel paramMdl, RequestModel reqMdl)
    throws Exception {

        //初期表示
        if (paramMdl.getWml280initFlg() == GSConstWebmail.DSP_FIRST) {
            //新規登録
            if (paramMdl.getWmlCmdMode() == GSConstWebmail.CMDMODE_ADD) {
                if (paramMdl.getWmlAccountMode() == 1) {
                    //個人設定からの遷移の場合、アクセス権限 編集ユーザの初期値としてセッションユーザを設定する
                    String[] accessFull
                        = new String[] {String.valueOf(reqMdl.getSmodel().getUsrsid())};
                    paramMdl.setWml280accessFull(accessFull);
                }

            } else if (paramMdl.getWmlCmdMode() == GSConstWebmail.CMDMODE_EDIT) {
                //編集
                _setDestlistData(con, paramMdl);
            }

            paramMdl.setWml280initFlg(GSConstWebmail.DSP_ALREADY);
        }

        //グループが未選択の場合、デフォルトグループを設定
        if (paramMdl.getWml280accessGroup() == -1) {
            GroupBiz grpBz = new GroupBiz();
            int defGrp = grpBz.getDefaultGroupSid(reqMdl.getSmodel().getUsrsid(), con);
            paramMdl.setWml280accessGroup(defGrp);
        }

        //グループコンボを設定
        GsMessage gsMsg = new GsMessage(reqMdl);
        GroupBiz grpBiz = new GroupBiz();
        List<LabelValueBean> groupCombo = new ArrayList<LabelValueBean>();
        groupCombo.add(
                new LabelValueBean(gsMsg.getMessage("cmn.grouplist"),
                                                String.valueOf(Wml280Form.GROUP_COMBO_VALUE)));

        ArrayList<GroupModel> grpList = grpBiz.getGroupCombList(con);
        for (GroupModel grpMdl : grpList) {
            LabelValueBean label = new LabelValueBean(grpMdl.getGroupName(),
                                                    String.valueOf(grpMdl.getGroupSid()));
            groupCombo.add(label);
        }
        paramMdl.setGroupCombo(groupCombo);

        //送信先を設定
        _setSelectAddressCombo(con, paramMdl);

        //アクセス権限を設定
        _setSelectAccessCombo(con, paramMdl);
        paramMdl.setWml280accessNoSelectCombo(__getAccessRightLabel(con, paramMdl));
    }

    /**
     * <br>[機  能] 送信先リスト情報をパラメータ情報へ設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @throws SQLException SQL実行時例外
     */
    protected void _setDestlistData(Connection con, Wml280ParamModel paramMdl) throws SQLException {
        int wdlSid = paramMdl.getWmlEditDestList();

        //送信先リスト情報を設定する
        WmlDestlistDao destlistDao = new WmlDestlistDao(con);
        WmlDestlistModel destlistMdl = destlistDao.select(wdlSid);

        paramMdl.setWml280name(destlistMdl.getWdlName());
        paramMdl.setWml280biko(destlistMdl.getWdlBiko());

        //送信先を設定する
        WmlDestlistAddressDao destlistAddressDao = new WmlDestlistAddressDao(con);
        List<WmlDestlistAddressModel> destUserList
            = destlistAddressDao.getDestlistAddress(wdlSid,
                                                        GSConstWebmail.WDA_TYPE_USER);
        paramMdl.setWml280destUser(__createDestParamArray(destUserList));
        List<WmlDestlistAddressModel> destAddressList
            = destlistAddressDao.getDestlistAddress(wdlSid,
                                                        GSConstWebmail.WDA_TYPE_ADDRESS);
        paramMdl.setWml280destAddress(__createDestParamArray(destAddressList));

        //アクセス権限を設定する
        WmlDestlistAccessConfDao destlistAccessConfDao = new WmlDestlistAccessConfDao(con);
        List<WmlDestlistAccessConfModel> destlistFullAccessList
            = destlistAccessConfDao.getAccessConfList(wdlSid, GSConstWebmail.WLA_AUTH_ALL);
        paramMdl.setWml280accessFull(__createAccessUserArray(destlistFullAccessList));

        List<WmlDestlistAccessConfModel> destlistReadAccessList
            = destlistAccessConfDao.getAccessConfList(wdlSid, GSConstWebmail.WLA_AUTH_READ);
        paramMdl.setWml280accessRead(__createAccessUserArray(destlistReadAccessList));
    }

    /**
     * <br>[機  能] 送信先リスト 送信先情報から送信先IDを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param type 種別
     * @param sid SID(ユーザSID or アドレス帳SID)
     * @param mailNo メール番号
     * @return 送信先ID
     */
    protected String _createDestAddressId(int type, int sid, int mailNo) {
        String destAddressId = type + "-" + sid;
        if (type == GSConstWebmail.WDA_TYPE_USER) {
            destAddressId += "-" + mailNo;
        } else if (type == GSConstWebmail.WDA_TYPE_ADDRESS) {
            destAddressId += "_" + mailNo;
        }

        return destAddressId;
    }

    /**
     * <br>[機  能] 取得した送信先情報をパラメータに変換する
     * <br>[解  説]
     * <br>[備  考]
     * @param addressList 送信先
     * @return 送信先(ユーザ情報 or アドレス帳)パラメータ
     */
    private String[] __createDestParamArray(List<WmlDestlistAddressModel> addressList) {
        String[] userArray = new String[addressList.size()];
        WmlDestlistAddressModel addressData = null;
        for (int idx = 0; idx < addressList.size(); idx++) {
            addressData = addressList.get(idx);
            userArray[idx] = _createDestAddressId(addressData.getWdaType(),
                                                                    addressData.getWdaSid(),
                                                                    addressData.getWdaAdrno());
        }

        return userArray;
    }

    /**
     * <br>[機  能] 取得したアクセス設定からパラメータ用ユーザ一覧を作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param accessUserList アクセス設定
     * @return パラメータ用ユーザ一覧
     */
    private String[] __createAccessUserArray(List<WmlDestlistAccessConfModel> accessUserList) {
        String[] userArray = new String[accessUserList.size()];
        WmlDestlistAccessConfModel accessUser = null;
        for (int idx = 0; idx < accessUserList.size(); idx++) {
            accessUser = accessUserList.get(idx);
            if (accessUser.getWlaKbn() == GSConstWebmail.WLA_KBN_GROUP) {
                userArray[idx] = "G" + accessUser.getWlaUsrSid();
            } else {
                userArray[idx] = String.valueOf(accessUser.getWlaUsrSid());
            }
        }

        return userArray;
    }

    /**
     * <br>[機  能] 送信先一覧を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @throws SQLException SQL実行時例外
     */
    protected void _setSelectAddressCombo(Connection con, Wml280ParamModel paramMdl)
            throws SQLException {

        //ユーザ情報
        CmnUsrmInfDao userInfDao = new CmnUsrmInfDao(con);
        List<CmnUsrmInfModel> userDataList = null;

        StringTokenizer st = null;

        List<Wml280AddressParamModel> addressParamList = new ArrayList<Wml280AddressParamModel>();
        List<String> destIdList = new ArrayList<String>();
        List<Wml280AddressParamModel> userList = new ArrayList<Wml280AddressParamModel>();

        String mailAddress = null;
        for (String destId : paramMdl.getWml280destUser()) {
            //重複チェック
            if (destIdList.indexOf(destId) >= 0) {
                continue;
            }
            Wml280AddressParamModel addressModel = new Wml280AddressParamModel();
            addressModel.setDestId(destId);
            destIdList.add(destId);

            st = new StringTokenizer(destId, "-");
            addressModel.setType(Integer.parseInt(st.nextToken()));
            addressModel.setSid(Integer.parseInt(st.nextToken()));
            addressModel.setMailNo(Integer.parseInt(st.nextToken()));
            userList.add(addressModel);
            String [] usids = new String[] {String.valueOf(addressModel.getSid())};
            userDataList = userInfDao.getUsersInfList(usids);
            if (userDataList.isEmpty()) {
                continue;
            }
            CmnUsrmInfModel  userData = userDataList.get(0);
            addressModel.setName(userData.getUsiSei() + " " + userData.getUsiMei());
            switch (addressModel.getMailNo()) {
            case 1:
                addressModel.setMailAddress(userData.getUsiMail1());
                break;
            case 2:
                addressModel.setMailAddress(userData.getUsiMail2());
                break;
            case 3:
                addressModel.setMailAddress(userData.getUsiMail3());
                break;
            default:
            }
            addressModel.setYakusyoku(userData.getUsiYakusyoku());
            if (!StringUtil.isNullZeroString(addressModel.getMailAddress())) {
                mailAddress = " <" + addressModel.getMailAddress() + ">";
                addressModel.setMailAddress(addressModel.getName()
                        + mailAddress);
                addressModel.setSendMailAddress(
                        MailBiz.formatPersonal(addressModel.getName())
                        + mailAddress);
                addressParamList.add(addressModel);
            }
        }

        //アドレス帳
        Wml280Dao dao280 = new Wml280Dao(con);
        List<Wml280AddressParamModel> addressList = new ArrayList<Wml280AddressParamModel>();
        for (String destId : paramMdl.getWml280destAddress()) {
            //重複チェック
            if (destIdList.indexOf(destId) >= 0) {
                continue;
            }

            Wml280AddressParamModel addressModel = new Wml280AddressParamModel();
            addressModel.setDestId(destId);
            destIdList.add(destId);

            addressModel.setType(Integer.parseInt(destId.substring(0, destId.indexOf("-"))));
            destId = destId.substring(destId.indexOf("-") + 1);
            if (destId.indexOf("_") > 0) {
                addressModel.setSid(Integer.parseInt(destId.substring(0, destId.indexOf("_"))));
                addressModel.setMailNo(Integer.parseInt(destId.substring(destId.indexOf("_") + 1)));
            } else {
                addressModel.setSid(Integer.parseInt(destId));
                addressModel.setMailNo(1);
            }
            addressList.add(addressModel);
            Wml280AddressBookModel addressBookData =
                    dao280.getAddressBookData(addressModel.getSid());
            if (addressBookData != null) {
                addressModel.setName(addressBookData.getAdrSei()
                        + " " + addressBookData.getAdrMei());
                addressModel.setAcoName(addressBookData.getAcoName());
                addressModel.setAbaName(addressBookData.getAbaName());
                addressModel.setYakusyoku(addressBookData.getYakusyoku());

                switch (addressModel.getMailNo()) {
                case 1:
                    addressModel.setMailAddress(addressBookData.getAdrMail1());
                    break;
                case 2:
                    addressModel.setMailAddress(addressBookData.getAdrMail2());
                    break;
                case 3:
                    addressModel.setMailAddress(addressBookData.getAdrMail3());
                    break;
                default:
                }
            }

            mailAddress = null;
            if (!StringUtil.isNullZeroString(addressModel.getMailAddress())) {
                mailAddress = " <" + addressModel.getMailAddress() + ">";
                addressModel.setMailAddress(addressModel.getName()
                        + mailAddress);
                addressModel.setSendMailAddress(
                        MailBiz.formatPersonal(addressModel.getName())
                        + mailAddress);
                addressParamList.add(addressModel);
            }
        }
        paramMdl.setDestUserList(addressParamList);
    }

    /**
     * <br>[機  能] ユーザコンボを設定する
     * <br>[解  説] アクセス権限を設定する
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @throws SQLException SQL実行時例外
     */
    protected void _setSelectAccessCombo(Connection con, Wml280ParamModel paramMdl)
    throws SQLException {

        //アクセス権限を設定
        paramMdl.setWml280accessFullSelectCombo(
                __getUserLabelList(con, paramMdl.getWml280accessFull()));
        paramMdl.setWml280accessReadSelectCombo(
                __getUserLabelList(con, paramMdl.getWml280accessRead()));
    }

    /**
     * <br>[機  能]  ユーザコンボを作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param selectUserSid ユーザSID
     * @param gsMsg GsMessage
     * @return ユーザコンボ
     * @throws SQLException SQL実行時例外
     */
    protected List<LabelValueBean> __createUserCombo(Connection con, String[] selectUserSid,
                                                                                    GsMessage gsMsg)
    throws SQLException {

        UserBiz userBiz = new UserBiz();
        ArrayList<BaseUserModel> ulist
                = userBiz.getBaseUserList(con, selectUserSid);
        LabelValueBean labelBean = null;
        List <LabelValueBean> selectUserList = new ArrayList<LabelValueBean>();
        for (BaseUserModel umodel : ulist) {
            labelBean = new LabelValueBean();
            labelBean.setLabel(umodel.getUsisei() + " " + umodel.getUsimei());
            labelBean.setValue(String.valueOf(umodel.getUsrsid()));
            selectUserList.add(labelBean);
        }

        return selectUserList;
    }

    /**
     * <br>[機  能] ユーザ情報の一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param userList 取得するユーザSID・グループSID
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getUserLabelList(Connection con, String[] userList)
    throws SQLException {

        ArrayList<LabelValueBean> ret = new ArrayList<LabelValueBean>();

        //
        ArrayList<Integer> grpSids = new ArrayList<Integer>();
        ArrayList<String> usrSids = new ArrayList<String>();

        //ユーザSIDとグループSIDを分離
        if (userList != null) {
            for (String userId : userList) {
                String str = NullDefault.getString(userId, "-1");
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
        LabelValueBean label = null;
        for (GroupModel gmodel : glist) {
            label = new LabelValueBean();
            label.setLabel(gmodel.getGroupName());
            label.setValue("G" + String.valueOf(gmodel.getGroupSid()));
            ret.add(label);
        }

        //ユーザ情報取得
        UserBiz userBiz = new UserBiz();
        ArrayList<BaseUserModel> ulist
                = userBiz.getBaseUserList(con,
                                        (String[]) usrSids.toArray(new String[usrSids.size()]));
        for (BaseUserModel umodel : ulist) {
            label = new LabelValueBean();
            label.setLabel(umodel.getUsisei() + " " + umodel.getUsimei());
            label.setValue(String.valueOf(umodel.getUsrsid()));
            ret.add(label);
        }

        return ret;
    }

    /**
     * <br>[機  能] アクセス権限の候補一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getAccessRightLabel(
            Connection con, Wml280ParamModel paramMdl)
    throws SQLException {

        //アクセス権限 グループコンボ選択値
        int accessGrpSid = paramMdl.getWml280accessGroup();
        //除外するSID(ユーザ or グループ)
        String[] leftFull = paramMdl.getWml280accessFull();
        String[] leftRead = paramMdl.getWml280accessRead();

        return __getRightLabel(con, leftFull, leftRead, accessGrpSid);
    }

    /**
     * <br>[機  能] ユーザ情報、アクセス権限の候補一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param left1 除外するSID1
     * @param left2 除外するSID2
     * @param groupSid グループコンボの選択値
     * @return 候補一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getRightLabel(
            Connection con, String[] left1, String[] left2, int groupSid)
    throws SQLException {

        ArrayList<LabelValueBean> ret = new ArrayList<LabelValueBean>();
        if (groupSid == Wml280Form.GROUP_COMBO_VALUE) {
            //グループを全て取得
            GroupDao dao = new GroupDao(con);
            CmnCmbsortConfDao sortDao = new CmnCmbsortConfDao(con);
            CmnCmbsortConfModel sortMdl = sortDao.getCmbSortData();
            ArrayList<GroupModel> allGpList = dao.getGroupTree(sortMdl);

            //選択済みのSIDを除外する
            List<String> excludeList = new ArrayList<String>();
            if (left1 != null && left1.length > 0) {
                excludeList.addAll(Arrays.asList(left1));
            }
            if (left2 != null && left2.length > 0) {
                excludeList.addAll(Arrays.asList(left2));
            }

            for (GroupModel bean : allGpList) {
                if (!excludeList.contains(String.valueOf("G" + bean.getGroupSid()))) {
                    ret.add(new LabelValueBean(
                            bean.getGroupName(), String.valueOf("G" + bean.getGroupSid())));
                }
            }

        } else {

            //除外するユーザSID
            ArrayList<Integer> excludeList = new ArrayList<Integer>();
            if (left1 != null) {
                for (String usrSid : left1) {
                    excludeList.add(new Integer(NullDefault.getInt(usrSid, -1)));
                }
            }
            if (left2 != null) {
                for (String usrSid : left2) {
                    excludeList.add(new Integer(NullDefault.getInt(usrSid, -1)));
                }
            }
            UserBiz userBiz = new UserBiz();
            List<CmnUsrmInfModel> userList
                = userBiz.getBelongUserList(con, groupSid, excludeList);

            LabelValueBean label = null;
            for (CmnUsrmInfModel userData : userList) {
                label = new LabelValueBean();
                label.setLabel(userData.getUsiSei() + " " + userData.getUsiMei());
                label.setValue(String.valueOf(userData.getUsrSid()));
                ret.add(label);
            }
        }

        return ret;
    }

    /**
     * <br>[機  能] 送信先の削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     */
    public void deleteDestUser(Wml280ParamModel paramMdl) {

        CommonBiz cmnBiz = new CommonBiz();

        paramMdl.setWml280destUser(
                cmnBiz.getDeleteMember(paramMdl.getWml280destUserSelect(),
                        paramMdl.getWml280destUser()));
        paramMdl.setWml280destAddress(
                cmnBiz.getDeleteMember(paramMdl.getWml280destUserSelect(),
                        paramMdl.getWml280destAddress()));
    }

    /**
     * <br>[機  能] 送信先リストの削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @throws SQLException SQL実行時例外
     */
    public void deleteDestList(Connection con, Wml280ParamModel paramMdl)
    throws SQLException {

        WmlDestlistDao destlistDao = new WmlDestlistDao(con);
        WmlDestlistAddressDao destAddressDao = new WmlDestlistAddressDao(con);
        WmlDestlistAccessConfDao destAccessDao = new WmlDestlistAccessConfDao(con);

        int wdlSid = paramMdl.getWmlEditDestList();
        destAddressDao.delete(wdlSid);
        destAccessDao.delete(wdlSid);
        destlistDao.delete(wdlSid);
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

}
