package jp.groupsession.v2.rsv.rsv060kn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.UsidSelectGrpNameDao;
import jp.groupsession.v2.cmn.dao.base.SaibanDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.SaibanModel;
import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.rsv.dao.RsvAccessConfDao;
import jp.groupsession.v2.rsv.dao.RsvSisAdmDao;
import jp.groupsession.v2.rsv.dao.RsvSisGrpDao;
import jp.groupsession.v2.rsv.dao.RsvSisKbnDao;
import jp.groupsession.v2.rsv.model.RsvAccessConfModel;
import jp.groupsession.v2.rsv.model.RsvSisAdmModel;
import jp.groupsession.v2.rsv.model.RsvSisGrpModel;
import jp.groupsession.v2.rsv.model.RsvSisKbnModel;
import jp.groupsession.v2.rsv.rsv060.Rsv060Biz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 施設予約 施設グループ登録確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv060knBiz extends Rsv060Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rsv060knBiz.class);

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param reqMdl リクエスト情報
     * @param con コネクション
     */
    public Rsv060knBiz(RequestModel reqMdl, Connection con) {
        super(reqMdl, con);
    }

    /**
     * <br>[機  能] 初期表示データセット
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Rsv060knParamModel
     * @param cntCon 採番コントローラ
     * @throws SQLException SQL実行時例外
     */
    public void setInitData(Rsv060knParamModel paramMdl,
            MlCountMtController cntCon) throws SQLException {

        String sKbnName = "";

        //施設区分名称取得
        RsvSisKbnDao kbnDao = new RsvSisKbnDao(con_);
        RsvSisKbnModel kbnParam = new RsvSisKbnModel();
        kbnParam.setRskSid(paramMdl.getRsv060SelectedSisetuKbn());
        RsvSisKbnModel ret = kbnDao.select(kbnParam);
        if (ret != null) {
            sKbnName = ret.getRskName();
        }
        paramMdl.setRsv060knSelectedSisetuName(sKbnName);

        boolean grpIdSetFlg = StringUtil.isNullZeroString(paramMdl.getRsv060GrpId());
        if (grpIdSetFlg) {
            RsvSisGrpDao rsgDao = new RsvSisGrpDao(con_);
            String strGrpId = "";

            //新規登録
            if (paramMdl.getRsv060ProcMode().equals(GSConstReserve.PROC_MODE_SINKI)) {

                //施設グループIDが未入力のとき、採番施設グループSIDの最大値+1をセット
                SaibanDao saibanDao = new SaibanDao(con_);
                SaibanModel saibanBean = new SaibanModel();
                saibanBean.setSbnSid(GSConstReserve.PLUGIN_ID_RESERVE);
                saibanBean.setSbnSidSub(GSConstReserve.SBNSID_SUB_GROUP);
                SaibanModel retBean = saibanDao.getSaibanData(saibanBean);
                if (retBean == null) {
                    retBean = new SaibanModel();
                }

                long maxGrpSid = retBean.getSbnNumber();

                int addCnt = 1;
                RsvSisGrpModel rsgMdl = null;
                while (true) {
                    strGrpId = String.valueOf(maxGrpSid + addCnt);

                    //自動セットしたIDが既に存在していないかチェック
                    rsgMdl = rsgDao.getExGrpIdData(String.valueOf(strGrpId));
                    if (rsgMdl == null) {
                        //自動セットしたIDが登録されていない場合
                        break;
                    }
                    addCnt++;
                }

            //編集登録
            } else if (paramMdl.getRsv060ProcMode().equals(GSConstReserve.PROC_MODE_EDIT)) {
                //登録しているIDをセットし直す
                strGrpId = rsgDao.select(paramMdl.getRsv060EditGrpSid()).getRsgId();
            }

            log__.debug("***自動で割り当てたグループID = " + strGrpId);
            paramMdl.setRsv060GrpId(String.valueOf(strGrpId));
        }

        //【権限設定を行う】の場合は管理者ユーザ一覧取得
        if (paramMdl.getRsv060GrpAdmKbn() == GSConstReserve.RSG_ADM_KBN_OK) {

            //管理者ユーザ一覧取得
            String[] saveUser = paramMdl.getSaveUser();
            if (saveUser != null && saveUser.length > 0) {
                paramMdl.setRsv060knKanriUser(__getGrpUserLabel(saveUser, con_));
            }
        }

        //予約可能ユーザ一覧取得
        String[] saveYoyakuUser = paramMdl.getRsv060memberSid();
        if (saveYoyakuUser != null && saveYoyakuUser.length > 0) {
            paramMdl.setRsv060knEditUser(__getGrpUserLabel(saveYoyakuUser, con_));
        }

        //閲覧ユーザ一覧取得
        String[] saveReadUser = paramMdl.getRsv060memberSidRead();
        if (saveReadUser != null && saveReadUser.length > 0) {
            paramMdl.setRsv060knReadUser(__getGrpUserLabel(saveReadUser, con_));
        }
    }

    /**
     * <br>[機  能] 入力内容をDBに反映する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv060knParamModel
     * @param ctrl 採番用コネクション
     * @throws SQLException SQL実行時例外
     */
    public void updateGrpData(Rsv060knParamModel paramMdl, MlCountMtController ctrl)
        throws SQLException {

        int grpSid = -1;
        UDate now = new UDate();
        BaseUserModel usrMdl = _getSessionUserModel(reqMdl_);
        int usrSid = usrMdl.getUsrsid();

        RsvSisGrpDao grpDao = new RsvSisGrpDao(con_);
        RsvSisAdmDao admDao = new RsvSisAdmDao(con_);
        RsvAccessConfDao accessDao = new RsvAccessConfDao(con_);

        String procMode = paramMdl.getRsv060ProcMode();

        if (procMode.equals(GSConstReserve.PROC_MODE_SINKI)) {

            log__.debug("新規モード");

            //施設グループSID採番
            grpSid =
                (int) ctrl.getSaibanNumber(
                        GSConstReserve.SBNSID_RESERVE,
                        GSConstReserve.SBNSID_SUB_GROUP,
                        usrSid);

            RsvSisGrpModel grpParam = new RsvSisGrpModel();
            grpParam.setRsgSid(grpSid);
            grpParam.setRskSid(paramMdl.getRsv060SelectedSisetuKbn());
            grpParam.setRsgId(paramMdl.getRsv060GrpId());
            grpParam.setRsgName(paramMdl.getRsv060GrpName());
            grpParam.setRsgAdmKbn(paramMdl.getRsv060GrpAdmKbn());
            grpParam.setRsgApprKbn(paramMdl.getRsv060apprKbn());
            grpParam.setRsgAuid(usrSid);
            grpParam.setRsgAdate(now);
            grpParam.setRsgEuid(usrSid);
            grpParam.setRsgEdate(now);
            if (paramMdl.isRsv060AccessDspFlg()) {
                grpParam.setRsgAcsLimitKbn(paramMdl.getRsv060AccessKbn());
            } else {
                grpParam.setRsgAcsLimitKbn(GSConstReserve.RSV_ACCESS_MODE_FREE);
            }

            //施設グループ登録
            grpDao.insertNewGrp(grpParam);

        } else if (procMode.equals(GSConstReserve.PROC_MODE_EDIT)) {

            log__.debug("編集モード");

            grpSid = paramMdl.getRsv060EditGrpSid();

            RsvSisGrpModel grpParam = new RsvSisGrpModel();
            grpParam.setRsgSid(grpSid);
            grpParam.setRskSid(paramMdl.getRsv060SelectedSisetuKbn());
            grpParam.setRsgId(paramMdl.getRsv060GrpId());
            grpParam.setRsgName(paramMdl.getRsv060GrpName());
            grpParam.setRsgAdmKbn(paramMdl.getRsv060GrpAdmKbn());
            grpParam.setRsgApprKbn(paramMdl.getRsv060apprKbn());
            grpParam.setRsgEuid(usrSid);
            grpParam.setRsgEdate(now);
            if (paramMdl.isRsv060AccessDspFlg()) {
                grpParam.setRsgAcsLimitKbn(paramMdl.getRsv060AccessKbn());
            } else {
                grpParam.setRsgAcsLimitKbn(GSConstReserve.RSV_ACCESS_MODE_FREE);
            }

            //施設グループ更新
            grpDao.updateGrp(grpParam);

            //施設グループ管理者削除
            admDao.delete(grpSid);

            //アクセス権限を削除
            accessDao.deleteRsvGrpConf(grpSid);
        }

        //施設グループ管理者登録
        String[] saveUser = paramMdl.getSaveUser();
        int grpAdmKbn = paramMdl.getRsv060GrpAdmKbn();
        if (saveUser != null && saveUser.length > 0
                && grpAdmKbn == GSConstReserve.RSG_ADM_KBN_OK) {
            for (String strUsrGrpSid : saveUser) {
                //登録
                RsvSisAdmModel admParam =
                    __getAdmBaseModel(grpSid, usrSid, now);

                //グループ
                if (strUsrGrpSid.startsWith(GSConstReserve.GROUP_HEADSTR)) {
                    admParam.setUsrSid(-1);
                    admParam.setGrpSid(Integer.parseInt(strUsrGrpSid.substring(1)));
                } else {
                    //ユーザ
                    admParam.setUsrSid(Integer.parseInt(strUsrGrpSid));
                    admParam.setGrpSid(-1);
                }

                admDao.insert(admParam);
            }
        }


        if (paramMdl.isRsv060AccessDspFlg()) {
            //予約可能ユーザ登録
            String[] saveYoyakuUser = paramMdl.getRsv060memberSid();
            if (saveYoyakuUser != null && saveYoyakuUser.length > 0
                    && paramMdl.isRsv060AccessDspFlg()) {
                RsvAccessConfModel accessConfModel = null;

                for (String strUsrSid : saveYoyakuUser) {
                    //登録

                    if (StringUtil.isNullZeroString(strUsrSid)) {
                        continue;
                    }

                    if (strUsrSid.startsWith(GSConstReserve.GROUP_HEADSTR)) {
                        //グループ
                        accessConfModel = __getAccessConfModel(
                                                           grpSid,
                                                           -1,
                                                           Integer.parseInt(strUsrSid.substring(1)),
                                                           -1,
                                                           GSConstReserve.RSV_ACCESS_KBN_WRITE);

                    } else {
                        //ユーザ
                        accessConfModel = __getAccessConfModel(
                                                           grpSid,
                                                           -1,
                                                           -1,
                                                           Integer.parseInt(strUsrSid),
                                                           GSConstReserve.RSV_ACCESS_KBN_WRITE);
                    }

                    accessDao.insert(accessConfModel);
                }
            }


            //閲覧ユーザ登録
            String[] saveReadUser = paramMdl.getRsv060memberSidRead();
            if (saveReadUser != null && saveReadUser.length > 0
                    && paramMdl.isRsv060AccessDspFlg()) {
                RsvAccessConfModel accessConfModel = null;

                for (String strUsrSid : saveReadUser) {
                    //登録

                    if (StringUtil.isNullZeroString(strUsrSid)) {
                        continue;
                    }

                    if (strUsrSid.startsWith(GSConstReserve.GROUP_HEADSTR)) {
                        //グループ
                        accessConfModel = __getAccessConfModel(
                                                           grpSid,
                                                           -1,
                                                           Integer.parseInt(strUsrSid.substring(1)),
                                                           -1,
                                                           GSConstReserve.RSV_ACCESS_KBN_READ);

                    } else {
                        //ユーザ
                        accessConfModel = __getAccessConfModel(
                                                           grpSid,
                                                           -1,
                                                           -1,
                                                           Integer.parseInt(strUsrSid),
                                                           GSConstReserve.RSV_ACCESS_KBN_READ);
                    }

                    accessDao.insert(accessConfModel);
                }
            }
        }

    }

    /**
     * <br>[機  能] 施設グループ管理者のベースを取得
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param grpSid 施設グループSID
     * @param usrSid セッションユーザSID
     * @param now システム日付
     * @return ret ベースモデル
     */
    private RsvSisAdmModel __getAdmBaseModel(int grpSid,
                                              int usrSid,
                                              UDate now) {

        RsvSisAdmModel ret = new RsvSisAdmModel();
        ret.setRsgSid(grpSid);
        ret.setRsaAuid(usrSid);
        ret.setRsaAdate(now);
        ret.setRsaEuid(usrSid);
        ret.setRsaEdate(now);
        return ret;
    }

    /**
     * <br>[機  能] 施設アクセス権限モデルを取得
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param rsgSid 施設グループSID
     * @param rsdSid 施設SID
     * @param grpSid グループSID
     * @param usrSid ユーザSID
     * @param racAuth 権限種別
     * @return ret ベースモデル
     */
    private RsvAccessConfModel __getAccessConfModel(int rsgSid,
                                              int rsdSid,
                                              int grpSid,
                                              int usrSid,
                                              int racAuth) {

        RsvAccessConfModel model = new RsvAccessConfModel();
        model.setRsgSid(rsgSid);
        model.setRsdSid(rsdSid);
        model.setGrpSid(grpSid);
        model.setUsrSid(usrSid);
        model.setRacAuth(racAuth);

        return model;
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