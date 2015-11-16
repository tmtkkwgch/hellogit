package jp.groupsession.v2.api.schedule.detail;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.api.ApiDataTypeUtil;
import jp.groupsession.v2.api.schedule.AbstractApiSchAction;
import jp.groupsession.v2.api.schedule.search.ApiSchSearchBiz;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.SchDao;
import jp.groupsession.v2.cmn.dao.base.CmnBelongmDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rsv.model.RsvSisDataModel;
import jp.groupsession.v2.sch.biz.SchCommonBiz;
import jp.groupsession.v2.sch.dao.SchAddressDao;
import jp.groupsession.v2.sch.dao.ScheduleSearchDao;
import jp.groupsession.v2.sch.model.SchAddressModel;
import jp.groupsession.v2.sch.model.SchAdmConfModel;
import jp.groupsession.v2.sch.model.SchDataModel;
import jp.groupsession.v2.sch.model.ScheduleSearchModel;
import jp.groupsession.v2.sch.sch010.Sch010Biz;
import jp.groupsession.v2.sch.sch040.Sch040Biz;
import jp.groupsession.v2.sch.sch040.model.Sch040AddressModel;
import jp.groupsession.v2.sch.sch040.model.Sch040CompanyModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;
import org.jdom.Document;
import org.jdom.Element;
/**
 * <br>[機  能]スケジュール詳細情報取得WEBAPIアクション
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiSchDetailAction extends AbstractApiSchAction {

    /** ログ */
    private static Log log__ =
            LogFactory.getLog(new Throwable().getStackTrace()[0].getClassName());

    @Override
    public Document createXml(ActionForm form, HttpServletRequest req,
            HttpServletResponse res, Connection con, BaseUserModel umodel)
            throws Exception {
        log__.debug("createXml start");

        ApiSchDetailForm thisForm = (ApiSchDetailForm) form;
        RequestModel reqMdl = getRequestModel(req);
        GsMessage gsMsg = new GsMessage(reqMdl);

        ActionErrors err = thisForm.validateCheck(gsMsg);
        if (!err.isEmpty()) {
            addErrors(req, err);
            return null;
        }

        int scdSid = NullDefault.getInt(thisForm.getSchSid(), -1);
        int sessionUsrSid = getSessionUserSid(req);
        //管理者設定を反映したプラグイン設定情報を取得
        PluginConfig pconfig
            = getPluginConfigForMain(getPluginConfig(req), con, sessionUsrSid);

        //スケジュール情報取得
        ScheduleSearchDao schSearchDao = new ScheduleSearchDao(con);
        SchCommonBiz schCmnBiz = new SchCommonBiz(reqMdl);
        SchAdmConfModel adminConf = schCmnBiz.getAdmConfModel(con);

        Sch040Biz sch040biz = new Sch040Biz(con, reqMdl);
        ScheduleSearchModel scData = sch040biz.getSchData(scdSid,
                adminConf,
                GSConstSchedule.SSP_AUTHFILTER_VIEW,
                con);
        if (scData == null) {
            ActionErrors errors = __makeNodataError(gsMsg);
            addErrors(req, errors);
            return null;
        } else {
            //スケジュール閲覧権限チェック
            if (!schCmnBiz.canAccessSchedule(con, scData, sessionUsrSid)) {
                ActionErrors errors = new ActionErrors();
                ActionMessage msg = new ActionMessage("error.notaccess.scd");
                StrutsUtil.addMessage(errors, msg, "detail");
                addErrors(req, errors);
                return null;
            }
        }

        //同時登録スケジュール
        SchDao schDao = new SchDao(con);
        List<Integer> notAccessUserList
            = schDao.getNotAccessUserList(sessionUsrSid);
        List<Integer> removeUsrsList = new ArrayList<Integer>(notAccessUserList);
        removeUsrsList.add(scData.getScdUsrSid());
        ArrayList<SchDataModel> sameInputSchedules =
                schSearchDao.getSameScheduleList(scData.getScdGrpSid(),
                        removeUsrsList);


        //
        int usrSid = scData.getScdUsrSid();
        int usrKbn = scData.getScdUsrKbn();

        boolean secret = false;
        //セッションユーザの所属グループを格納
        CmnBelongmDao bdao = new CmnBelongmDao(con);
        List<Integer> belongGpSidList = bdao.selectUserBelongGroupSid(sessionUsrSid);
        //表示グループに所属しているか判定
        GroupBiz gpBiz = new GroupBiz();
        boolean grpBelongHnt;

        if (usrKbn == GSConstSchedule.USER_KBN_USER) {
            //ユーザスケジュールの場合は表示スケジュールユーザと同じグループに所属しているか判定
            boolean belongFlg = false;
            ArrayList<Integer> belongSids = scData.getScdUserBlongGpList();
            if (belongSids != null && !belongSids.isEmpty()) {
                for (int gpSid : belongSids) {
                    if (belongGpSidList != null) {
                        int index = belongGpSidList.indexOf(gpSid);
                        if (index > -1) {
                            belongFlg = true;
                        }
                    }
                }
            }
            grpBelongHnt = belongFlg;
        } else {
            grpBelongHnt = gpBiz.isBelongGroup(sessionUsrSid, usrSid, con);
        }

        if (usrKbn == GSConstSchedule.USER_KBN_USER
         && usrSid == sessionUsrSid) {
            //本人
        } else if (usrKbn == GSConstSchedule.USER_KBN_USER
                && usrSid != sessionUsrSid) {
            //他ユーザ
            if (scData.getScdPublic() == GSConstSchedule.DSP_YOTEIARI) {
                //予定あり
                String textYoteiari = gsMsg.getMessage("schedule.src.9");

                scData.setScdTitle(textYoteiari);
                scData.setScdBiko("");
                scData.setScdValue("");

                secret = true;
            } else if (scData.getScdPublic() == GSConstSchedule.DSP_NOT_PUBLIC) {
                //非公開
                ActionErrors errors = __makeNodataError(gsMsg);
                addErrors(req, errors);
                return null;
            }

            if (scData.getScdPublic() == GSConstSchedule.DSP_YOTEIARI
                    || (scData.getScdPublic() == GSConstSchedule.DSP_BELONG_GROUP
                    && !grpBelongHnt)) {
                //予定あり
                String textYoteiari = gsMsg.getMessage("schedule.src.9");

                scData.setScdTitle(textYoteiari);
                scData.setScdBiko("");
                scData.setScdValue("");
                scData.setScdBgcolor(GSConstSchedule.BGCOLOR_BLACK);
                secret = true;
            }

            if (scData.getScdPublic() == GSConstSchedule.DSP_NOT_PUBLIC) {
                //非公開
                ActionErrors errors = __makeNodataError(gsMsg);
                addErrors(req, errors);
                return null;
            }

        }

        ApiSchSearchBiz searchBiz = new ApiSchSearchBiz(con, reqMdl);
        scData.setScdUserName(
                searchBiz.getUserName(scData.getScdUsrSid()
                        , scData.getScdUsrKbn(), con)
                );


        ArrayList<RsvSisDataModel> selectResList = new ArrayList<RsvSisDataModel>();
        Map<String, Sch040CompanyModel> companMap = new HashMap<String, Sch040CompanyModel>();
        if (!secret) {
            CommonBiz cmnBiz = new CommonBiz();
            //施設予約の管理者
            boolean rsvAdmin = cmnBiz.isPluginAdmin(con, umodel, GSConstSchedule.PLUGIN_ID_RESERVE);

            selectResList =
                    searchBiz.getSelectResList(con, scdSid, sessionUsrSid, rsvAdmin);
            if (selectResList == null) {
                selectResList = new ArrayList<RsvSisDataModel>();
            }

            companMap =
                    searchBiz.getCompanyMap(scdSid, sessionUsrSid);
            if (companMap == null) {
                companMap = new HashMap<String, Sch040CompanyModel>();
            }
        }
        SchAddressDao addressDao = new SchAddressDao(con);
        List<SchAddressModel> addressList = null;
        if (pconfig.getPlugin("address") != null) {
           addressList = addressDao.select(scdSid);
        }
        Sch010Biz sch010Biz = new Sch010Biz(reqMdl);
        boolean ableEdit = (sch010Biz.isEditOk(scdSid, reqMdl, con) && !secret);

        return __createDoc(scData, sameInputSchedules,
                selectResList, addressList,
                companMap, ableEdit,
                pconfig);
    }
    /**
     *
     * <br>[機  能] Document生成
     * <br>[解  説]
     * <br>[備  考]
     * @param scData スケジュールモデル
     * @param sameScheduleList 同時登録スケジュール
     * @param selectResList 同時登録施設予約
     * @param addressList アドレス情報
     * @param companMap 会社情報Map
     * @param ableEdit 編集権限
     * @param pconfig プラグインコンフィグ
     * @return Documentオブジェクト
     */
    private Document __createDoc(ScheduleSearchModel scData,
            List<SchDataModel> sameScheduleList,
            ArrayList<RsvSisDataModel> selectResList,
            List<SchAddressModel> addressList,
            Map<String, Sch040CompanyModel> companMap,
            boolean ableEdit,
            PluginConfig pconfig) {
        Element resultSet = new Element("ResultSet");
        Document doc = new Document(resultSet);

        Element result = new Element("Result");
        resultSet.addContent(result);

        //Schsid スケジュールSID
        result.addContent(_createElement("Schsid", scData.getScdSid()));
        //SchKf スケジュール公開フラグ
        result.addContent(_createElement("SchKf",
                scData.getScdPublic()));
        //SchEf スケジュール編集フラグ
        result.addContent(_createElement("SchEf",
                scData.getScdEdit()));
        //Title
        result.addContent(_createElement("Title", scData.getScdTitle()));
        //Naiyo
        result.addContent(_createElement("Naiyo", scData.getScdValue()));
        //StartDateTime
        Element startDateTime = new Element("StartDateTime");
        startDateTime.addContent(ApiDataTypeUtil.getDateTime(scData.getScdFrDate()));
        result.addContent(startDateTime);
        //EndDateTime
        Element endDateTime = new Element("EndDateTime");
        endDateTime.addContent(ApiDataTypeUtil.getDateTime(scData.getScdToDate()));
        result.addContent(endDateTime);
        //TimeKbn
        result.addContent(_createElement("TimeKbn", scData.getScdDaily()));
        //UserKbn
        result.addContent(_createElement("UserKbn", scData.getScdUsrKbn()));
        //UserSid
        result.addContent(_createElement("UserSid", scData.getScdUsrSid()));
        //UserName
        result.addContent(_createElement("UserName",
                scData.getScdUserName()));
        //ColorKbn
        result.addContent(_createElement("ColorKbn", scData.getScdBgcolor()));
        //Biko
        result.addContent(_createElement("Biko", scData.getScdBiko()));
        //AttendKbn
        result.addContent(_createElement("AttendKbn", scData.getScdAttendKbn()));
        //AttendAns
        result.addContent(_createElement("AttendAns", scData.getScdAttendAns()));
        //AttendAuKbn
        result.addContent(_createElement("AttendAuKbn", scData.getScdAttendAuKbn()));

        //AbleEdit
        if (ableEdit) {
            result.addContent(_createElement("AbleEdit", "1"));
        } else {
            result.addContent(_createElement("AbleEdit", "0"));
        }

        Element sameScheduleUserSet = new Element("SameScheduleUserSet");
        result.addContent(sameScheduleUserSet);
        if (sameScheduleList != null && sameScheduleList.size() > 0) {
            sameScheduleUserSet.setAttribute("Count", String.valueOf(sameScheduleList.size() - 1));
            for (SchDataModel sameScData : sameScheduleList) {
                if (sameScData.getScdSid() == scData.getScdSid()) {
                    continue;
                }
                Element user = new Element("User");
                sameScheduleUserSet.addContent(user);
                user.addContent(_createElement("Name", sameScData.getScdUserName()));
                user.addContent(_createElement("UsrSid", sameScData.getScdUsrSid()));
                user.addContent(_createElement("AttendAns", sameScData.getScdAttendAns()));
                user.addContent(_createElement("AttendAuKbn", sameScData.getScdAttendAuKbn()));
                if (sameScData.getScdAttendKbn() == GSConstSchedule.ATTEND_KBN_YES
                        && sameScData.getScdAttendAns() != GSConstSchedule.ATTEND_ANS_NONE) {
                    user.addContent(_createElement("AttendAnsDateTime",
                            ApiDataTypeUtil.getDateTime(scData.getScdEdate())));
                } else {
                    user.addContent(_createElement("AttendAnsDateTime",
                            "-"));
                }
            }
        } else {
            sameScheduleUserSet.setAttribute("Count", String.valueOf(0));
        }
        //施設予約使用有無
        if (pconfig.getPlugin("reserve") != null) {
            Element reserveSet = new Element("ReserveSet");
            result.addContent(reserveSet);
            reserveSet.setAttribute("Count", String.valueOf(selectResList.size()));
            for (RsvSisDataModel rsvSisDataModel : selectResList) {
                Element reserve = new Element("Reserve");
                reserveSet.addContent(reserve);

                reserve.addContent(_createElement("RsdSid", rsvSisDataModel.getRsdSid()));
                reserve.addContent(_createElement("Name", rsvSisDataModel.getRsdName()));

            }
        }
        //コンタクト保存フラグ
        boolean saveContact = false;
        //アドレス帳使用有無
        if (pconfig.getPlugin("address") != null) {

            if (addressList != null && addressList.size() > 0) {
                saveContact = (addressList.get(0).getAdcSid() > 0);
            }

            Element companySet = new Element("CompanySet");
            result.addContent(companySet);
            Set<String> comKeys = companMap.keySet();
            if (comKeys != null) {
                companySet.setAttribute("Count", String.valueOf(comKeys.size()));
                for (String key : comKeys) {
                    Element company = new Element("Company");
                    companySet.addContent(company);

                    Sch040CompanyModel comModel = companMap.get(key);

                    company.addContent(_createElement("AcoSid", comModel.getCompanySid()));
                    company.addContent(_createElement("AbaSid", comModel.getCompanyBaseSid()));
                    company.addContent(_createElement("Name", comModel.getCompanyName()));

                    List<Sch040AddressModel> adressList = comModel.getAddressDataList();
                    Element adressSet = new Element("AdressSet");
                    company.addContent(adressSet);
                    if (adressList != null) {
                        adressSet.setAttribute("Count", String.valueOf(adressList.size()));
                        for (Sch040AddressModel sch040AddressModel : adressList) {
                            Element adress = new Element("Adress");
                            adressSet.addContent(adress);

                            adress.addContent(_createElement("AdrSid"
                                    , sch040AddressModel.getAdrSid()));
                            adress.addContent(_createElement("Name"
                                    , sch040AddressModel.getAdrName()));
                        }
                    } else {
                        adressSet.setAttribute("Count", String.valueOf(0));
                    }
                }
            } else {
                companySet.setAttribute("Count", String.valueOf(0));
            }
        }
        result.addContent(_createElement("SaveContact", (saveContact ? "1" : "0")));
        return doc;
    }
    /**
     *
     * <br>[機  能]データ削除済みエラー作成
     * <br>[解  説]
     * <br>[備  考]
     * @param gsMsg GsMessage
     * @return エラーメッセージ
     */
    private ActionErrors __makeNodataError(GsMessage gsMsg) {
        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;
        //変更

        //スケジュール
        String textSchedule = gsMsg.getMessage("schedule.108");
        //変更
        String textChange = gsMsg.getMessage("api.cmn.view");
        msg = new ActionMessage("error.none.edit.data",
                textSchedule, textChange);
        StrutsUtil.addMessage(errors, msg, "detail");
        return errors;
    }

}
