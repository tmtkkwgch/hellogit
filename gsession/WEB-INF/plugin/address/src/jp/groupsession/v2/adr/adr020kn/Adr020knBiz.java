package jp.groupsession.v2.adr.adr020kn;

import static jp.groupsession.v2.adr.GSConstAddress.EDITPERMIT_GROUP;
import static jp.groupsession.v2.adr.GSConstAddress.EDITPERMIT_USER;
import static jp.groupsession.v2.adr.GSConstAddress.PROCMODE_ADD;
import static jp.groupsession.v2.adr.GSConstAddress.SBNSID_SUB_ADDRESS;
import static jp.groupsession.v2.cmn.GSConst.ADR_VIEWPERMIT_GROUP;
import static jp.groupsession.v2.cmn.GSConst.ADR_VIEWPERMIT_USER;
import static jp.groupsession.v2.cmn.GSConst.SBNSID_ADDRESS;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.StringUtilKana;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.adr.adr020.Adr020Biz;
import jp.groupsession.v2.adr.biz.AddressBiz;
import jp.groupsession.v2.adr.dao.AdrAddressDao;
import jp.groupsession.v2.adr.dao.AdrBelongLabelDao;
import jp.groupsession.v2.adr.dao.AdrPermitEditDao;
import jp.groupsession.v2.adr.dao.AdrPermitViewDao;
import jp.groupsession.v2.adr.dao.AdrPersonchargeDao;
import jp.groupsession.v2.adr.dao.AdrPositionDao;
import jp.groupsession.v2.adr.model.AdrAddressModel;
import jp.groupsession.v2.adr.model.AdrBelongLabelModel;
import jp.groupsession.v2.adr.model.AdrPermitEditModel;
import jp.groupsession.v2.adr.model.AdrPermitViewModel;
import jp.groupsession.v2.adr.model.AdrPersonchargeModel;
import jp.groupsession.v2.adr.model.AdrPositionModel;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.base.CmnTdfkDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnTdfkModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] アドレス帳登録確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Adr020knBiz extends Adr020Biz {

    /** Loggingインスタンス */
    private static Log log__ = LogFactory.getLog(Adr020knBiz.class);

    /** リクエスト */
    protected RequestModel reqMdl_ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     */
    public Adr020knBiz(RequestModel reqMdl) {
        super(reqMdl);
        reqMdl_ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Adr020knParamModel
     * @param userMdl セッションユーザ情報
     * @throws SQLException SQL実行例外
     * @throws IOException 添付ファイルの操作に失敗
     * @throws IOToolsException 添付ファイルの操作に失敗
     */
    public void setInitData(Connection con, Adr020knParamModel paramMdl,
                            BaseUserModel userMdl)
    throws IOException, IOToolsException, SQLException {

        log__.debug("START");

        //アドレス帳確認フラグ = 1:確認のみ の場合、DBからアドレス帳情報を読み込む
        if (paramMdl.getAdr020viewFlg() == 1) {
            _setAddressData(con, paramMdl);
        }

        //役職名称を設定
        if (paramMdl.getAdr020position() > 0) {
            AdrPositionDao positionDao = new AdrPositionDao(con);
            AdrPositionModel positionModel = positionDao.select(paramMdl.getAdr020position());
            if (positionModel != null) {
                paramMdl.setAdr020knPositionName(positionModel.getApsName());
            } else {
                paramMdl.setAdr020position(0);
            }
        }

        //都道府県名称を設定
        if (paramMdl.getAdr020tdfk() > 0) {
            CmnTdfkDao tdfkDao = new CmnTdfkDao(con);
            CmnTdfkModel tdfkModel = tdfkDao.select(paramMdl.getAdr020tdfk());
            paramMdl.setAdr020knTdfkName(tdfkModel.getTdfName());
        }

        //備考(表示用)を設定
        if (!StringUtil.isNullZeroString(paramMdl.getAdr020biko())) {
            paramMdl.setAdr020knViewBiko(
                    StringUtilHtml.transToHTmlPlusAmparsant(
                            paramMdl.getAdr020biko()));
        }

        AddressBiz addressBiz = new AddressBiz(reqMdl_);

        //会社名を設定
        _setCompanyData(con, paramMdl);

        //担当者を設定
        UserBiz userBiz = new UserBiz();
        paramMdl.setAdr020knTantoNameList(
                    addressBiz.getUserNameList(con, paramMdl.getAdr020tantoList()));
        List<LabelValueBean> userLabelList =
                userBiz.getUserLabelList(con, paramMdl.getAdr020tantoList());
        ArrayList<String> sidList = new ArrayList<String>();
        for (LabelValueBean bean : userLabelList) {
            sidList.add(bean.getValue());
        }
        paramMdl.setAdr020tantoList(sidList.toArray(new String[0]));

        //ラベル情報一覧を設定
        _setLabelList(con, paramMdl);


        
        //閲覧グループを設定
        int permitView = paramMdl.getAdr020permitView();
        if (permitView == ADR_VIEWPERMIT_GROUP) {
            List<LabelValueBean> labelList = 
                    addressBiz.getGroupLabelList(con, paramMdl.getAdr020permitViewGroup());
            List<String> nameList = new ArrayList<String>();
            sidList = new ArrayList<String>();
            for (LabelValueBean bean : labelList) {
                nameList.add(bean.getLabel());
                sidList.add(bean.getValue());
            }
            paramMdl.setAdr020knPermitViewList(nameList);
            paramMdl.setAdr020permitViewGroup(sidList.toArray(new String[0]));
            
        }

        //閲覧ユーザを設定
        if (permitView == ADR_VIEWPERMIT_USER) {
            paramMdl.setAdr020knPermitViewList(
                    addressBiz.getUserNameList(con, paramMdl.getAdr020permitViewUser()));
            userLabelList =
                    userBiz.getUserLabelList(con, paramMdl.getAdr020permitViewUser());
            sidList = new ArrayList<String>();
            for (LabelValueBean bean : userLabelList) {
                sidList.add(bean.getValue());
            }
            paramMdl.setAdr020permitViewUser(sidList.toArray(new String[0]));
        }

        //編集グループを設定
        int permitEdit = paramMdl.getAdr020permitEdit();
        if (permitView == ADR_VIEWPERMIT_GROUP || permitEdit == EDITPERMIT_GROUP) {
            
            List<LabelValueBean> labelList = 
                    addressBiz.getGroupLabelList(con, paramMdl.getAdr020permitEditGroup());
            List<String> nameList = new ArrayList<String>();
            sidList = new ArrayList<String>();
            for (LabelValueBean bean : labelList) {
                nameList.add(bean.getLabel());
                sidList.add(bean.getValue());
            }
            paramMdl.setAdr020knPermitEditList(nameList);
            paramMdl.setAdr020permitEditGroup(sidList.toArray(new String[0]));
            
        }

        //編集ユーザを設定
        if (permitView == ADR_VIEWPERMIT_USER || permitEdit == EDITPERMIT_USER) {
            paramMdl.setAdr020knPermitEditList(
                    addressBiz.getUserNameList(con, paramMdl.getAdr020permitEditUser()));
            userLabelList =
                    userBiz.getUserLabelList(con, paramMdl.getAdr020permitEditUser());
            sidList = new ArrayList<String>();
            for (LabelValueBean bean : userLabelList) {
                sidList.add(bean.getValue());
            }
            paramMdl.setAdr020permitEditUser(sidList.toArray(new String[0]));
            
        }

        log__.debug("End");
    }


    /**
     * <br>[機  能] アドレス帳の登録を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Adr020knParamModel
     * @param mtCon 採番コントローラ
     * @param sessionUserSid セッションユーザSID
     * @throws SQLException SQL実行例外
     * @throws IOException 添付ファイルの操作に失敗
     * @throws IOToolsException 添付ファイルの操作に失敗
     * @return AdrAddressModel アドレス情報モデル
     */
    public AdrAddressModel entryAddressData(
            Connection con,
            Adr020knParamModel paramMdl,
            MlCountMtController mtCon,
            int sessionUserSid)
    throws IOException, IOToolsException, SQLException {

        log__.debug("START");

        UDate now = new UDate();

        AdrAddressModel addressMdl = new AdrAddressModel();

        addressMdl.setAdrSei(paramMdl.getAdr020unameSei());
        addressMdl.setAdrMei(paramMdl.getAdr020unameMei());
        addressMdl.setAdrSeiKn(paramMdl.getAdr020unameSeiKn());
        addressMdl.setAdrMeiKn(paramMdl.getAdr020unameMeiKn());
        addressMdl.setAdrSini(StringUtilKana.getInitKanaChar(paramMdl.getAdr020unameSeiKn()));
        addressMdl.setAcoSid(NullDefault.getInt(paramMdl.getAdr020selectCompany(), 0));
        addressMdl.setAbaSid(NullDefault.getInt(paramMdl.getAdr020selectCompanyBase(), 0));
        addressMdl.setAdrSyozoku(paramMdl.getAdr020syozoku());
        addressMdl.setApsSid(paramMdl.getAdr020position());
        addressMdl.setAdrMail1(paramMdl.getAdr020mail1());
        addressMdl.setAdrMailCmt1(paramMdl.getAdr020mail1Comment());
        addressMdl.setAdrMail2(paramMdl.getAdr020mail2());
        addressMdl.setAdrMailCmt2(paramMdl.getAdr020mail2Comment());
        addressMdl.setAdrMail3(paramMdl.getAdr020mail3());
        addressMdl.setAdrMailCmt3(paramMdl.getAdr020mail3Comment());
        addressMdl.setAdrPostno1(paramMdl.getAdr020postno1());
        addressMdl.setAdrPostno2(paramMdl.getAdr020postno2());
        addressMdl.setTdfSid(paramMdl.getAdr020tdfk());
        addressMdl.setAdrAddr1(paramMdl.getAdr020address1());
        addressMdl.setAdrAddr2(paramMdl.getAdr020address2());
        addressMdl.setAdrTel1(paramMdl.getAdr020tel1());
        addressMdl.setAdrTelNai1(paramMdl.getAdr020tel1Nai());
        addressMdl.setAdrTelCmt1(paramMdl.getAdr020tel1Comment());
        addressMdl.setAdrTel2(paramMdl.getAdr020tel2());
        addressMdl.setAdrTelNai2(paramMdl.getAdr020tel2Nai());
        addressMdl.setAdrTelCmt2(paramMdl.getAdr020tel2Comment());
        addressMdl.setAdrTel3(paramMdl.getAdr020tel3());
        addressMdl.setAdrTelNai3(paramMdl.getAdr020tel3Nai());
        addressMdl.setAdrTelCmt3(paramMdl.getAdr020tel3Comment());
        addressMdl.setAdrFax1(paramMdl.getAdr020fax1());
        addressMdl.setAdrFaxCmt1(paramMdl.getAdr020fax1Comment());
        addressMdl.setAdrFax2(paramMdl.getAdr020fax2());
        addressMdl.setAdrFaxCmt2(paramMdl.getAdr020fax2Comment());
        addressMdl.setAdrFax3(paramMdl.getAdr020fax3());
        addressMdl.setAdrFaxCmt3(paramMdl.getAdr020fax3Comment());
        addressMdl.setAdrBiko(paramMdl.getAdr020biko());

        addressMdl.setAdrPermitView(paramMdl.getAdr020permitView());
        addressMdl.setAdrPermitEdit(paramMdl.getAdr020permitEdit());
        addressMdl.setAdrAuid(sessionUserSid);
        addressMdl.setAdrAdate(now);
        addressMdl.setAdrEuid(sessionUserSid);
        addressMdl.setAdrEdate(now);

        AdrAddressDao addressDao = new AdrAddressDao(con);

        if (paramMdl.getAdr020ProcMode() == PROCMODE_ADD) {
            long adrSid = mtCon.getSaibanNumber(SBNSID_ADDRESS,
                                                SBNSID_SUB_ADDRESS, sessionUserSid);
            addressMdl.setAdrSid((int) adrSid);
            addressDao.insert(addressMdl);
        } else {
            addressMdl.setAdrSid(paramMdl.getAdr010EditAdrSid());
            addressDao.update(addressMdl);
        }
        int adrSid = addressMdl.getAdrSid();

        //担当者情報を登録する
        AdrPersonchargeDao tantoDao = new AdrPersonchargeDao(con);
        tantoDao.deleteToAddress(adrSid);

        if (paramMdl.getAdr020tantoList() != null) {
            AdrPersonchargeModel tantoModel = new AdrPersonchargeModel();
            tantoModel.setAdrSid(adrSid);
            tantoModel.setApcAuid(sessionUserSid);
            tantoModel.setApcAdate(now);
            tantoModel.setApcEuid(sessionUserSid);
            tantoModel.setApcEdate(now);

            for (String userSid : paramMdl.getAdr020tantoList()) {
                tantoModel.setUsrSid(Integer.parseInt(userSid));
                tantoDao.insert(tantoModel);
            }

        }

        //ラベル付与情報を登録する
        AdrBelongLabelDao belongLabelDao = new AdrBelongLabelDao(con);
        belongLabelDao.deleteToAddress(adrSid);

        if (paramMdl.getAdr020label() != null) {
            AdrBelongLabelModel belongLabelMdl = new AdrBelongLabelModel();
            belongLabelMdl.setAdrSid(adrSid);
            belongLabelMdl.setAblAuid(sessionUserSid);
            belongLabelMdl.setAblAdate(now);
            belongLabelMdl.setAblEuid(sessionUserSid);
            belongLabelMdl.setAblEdate(now);

            for (String albSid : paramMdl.getAdr020label()) {
                belongLabelMdl.setAlbSid(Integer.parseInt(albSid));
                belongLabelDao.insertMulti(belongLabelMdl);
            }
        }

        //閲覧権限情報を登録する
        AdrPermitViewDao permitViewDao = new AdrPermitViewDao(con);
        permitViewDao.deleteToAddress(adrSid);

        int permitView = paramMdl.getAdr020permitView();
        AdrPermitViewModel permitViewModel = new AdrPermitViewModel();
        permitViewModel.setAdrSid(adrSid);
        permitViewModel.setAdrPermitView(permitView);
        permitViewModel.setApvAuid(sessionUserSid);
        permitViewModel.setApvAdate(now);
        permitViewModel.setApvEuid(sessionUserSid);
        permitViewModel.setApvEdate(now);
        if (permitView == ADR_VIEWPERMIT_GROUP) {
            for (String grpSid : paramMdl.getAdr020permitViewGroup()) {
                permitViewModel.setGrpSid(Integer.parseInt(grpSid));
                permitViewModel.setUsrSid(-1);
                permitViewDao.insert(permitViewModel);
            }
        } else if (permitView == ADR_VIEWPERMIT_USER) {
            for (String userSid : paramMdl.getAdr020permitViewUser()) {
                permitViewModel.setGrpSid(-1);
                permitViewModel.setUsrSid(Integer.parseInt(userSid));
                permitViewDao.insert(permitViewModel);
            }
        }

        //編集権限情報を登録する
        AdrPermitEditDao permitEditDao = new AdrPermitEditDao(con);
        permitEditDao.deleteToAddress(adrSid);

        int permitEdit = paramMdl.getAdr020permitEdit();
        AdrPermitEditModel permitEditModel = new AdrPermitEditModel();
        permitEditModel.setAdrSid(adrSid);
        permitEditModel.setAdrPermitEdit(permitEdit);
        permitEditModel.setApeAuid(sessionUserSid);
        permitEditModel.setApeAdate(now);
        permitEditModel.setApeEuid(sessionUserSid);
        permitEditModel.setApeEdate(now);
        if (permitEdit == EDITPERMIT_GROUP) {
            for (String grpSid : paramMdl.getAdr020permitEditGroup()) {
                permitEditModel.setGrpSid(Integer.parseInt(grpSid));
                permitEditModel.setUsrSid(-1);
                permitEditDao.insert(permitEditModel);
            }
        } else if (permitEdit == EDITPERMIT_USER) {
            for (String userSid : paramMdl.getAdr020permitEditUser()) {
                permitEditModel.setGrpSid(-1);
                permitEditModel.setUsrSid(Integer.parseInt(userSid));
                permitEditDao.insert(permitEditModel);
            }
        }

        log__.debug("End");
        return addressMdl;

    }
}
