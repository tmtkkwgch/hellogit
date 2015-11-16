package jp.groupsession.v2.api.schedule.delete;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.api.schedule.AbstractApiSchAction;
import jp.groupsession.v2.api.schedule.edit.ApiSchEditBiz;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rsv.dao.RsvSisKryrkDao;
import jp.groupsession.v2.rsv.dao.RsvSisRyrkDao;
import jp.groupsession.v2.rsv.dao.RsvSisYrkDao;
import jp.groupsession.v2.rsv.model.RsvSisRyrkModel;
import jp.groupsession.v2.sch.biz.SchCommonBiz;
import jp.groupsession.v2.sch.dao.ScheduleSearchDao;
import jp.groupsession.v2.sch.model.SchAdmConfModel;
import jp.groupsession.v2.sch.model.ScheduleExSearchModel;
import jp.groupsession.v2.sch.sch040.Sch040Biz;
import jp.groupsession.v2.sch.sch041kn.Sch041knBiz;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.jdom.Document;

/**
 * <br>[機  能] スケジュール削除するWEBAPIアクション
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiSchDeleteAction extends AbstractApiSchAction {

    /** ログ */
    private static Log log__ =
            LogFactory.getLog(new Throwable().getStackTrace()[0].getClassName());
    @Override
    public Document createXml(ActionForm aForm, HttpServletRequest req,
            HttpServletResponse res, Connection con, BaseUserModel umodel)
            throws Exception {
        log__.debug("createXml start");

        ApiSchDeleteForm form = (ApiSchDeleteForm) aForm;


        int sessionUsrSid = umodel.getUsrsid(); //セッションユーザSID
        RequestModel reqMdl = getRequestModel(req);
        GsMessage gsMsg = new GsMessage(reqMdl);

        ActionErrors err = form.validateCheck(gsMsg);
        if (!err.isEmpty()) {
            addErrors(req, err);
            return null;
        }
        ApiSchEditBiz eBiz = new ApiSchEditBiz(con, reqMdl, null);
        int scdSid = Integer.parseInt(form.getSchSid());
        err = eBiz.validateExistData(err, scdSid, gsMsg.getMessage("cmn.delete"));
        if (!err.isEmpty()) {
            addErrors(req, err);
            return null;
        }


        //編集権減確認
        err = form.validatePowerCheck(req, reqMdl, con);
        if (!err.isEmpty()) {
            addErrors(req, err);
            return null;
        }

        Sch040Biz biz = new Sch040Biz(con, reqMdl);
        Sch041knBiz sch041knBiz = new Sch041knBiz(con, getRequestModel(req));

        //管理者設定を取得
        SchCommonBiz cbiz = new SchCommonBiz(reqMdl);
        SchAdmConfModel adminConf = cbiz.getAdmConfModel(con);

        boolean commitFlg = false;
        con.setAutoCommit(false);
        try {
            ScheduleSearchDao ssDao = new ScheduleSearchDao(con);
            ArrayList<Integer> scds = ssDao.getScheduleUsrs(
                    Integer.parseInt(form.getSchSid()),
                    sessionUsrSid,
                    adminConf.getSadCrange(),
                    GSConstSchedule.SSP_AUTHFILTER_EDIT
                    );
            ScheduleExSearchModel sceMdl = biz.getSchExData(scdSid, adminConf, con);
            log__.debug("削除処理実行");

            //施設予約を削除
            if (NullDefault.getInt(form.getBatchResRef(), 1) == 1) {
                //同時登録施設予約があれば削除
                int cnt = biz.deleteReserve(scdSid, con);
                log__.debug("施設予約削除件数=>" + cnt);
            }

            //ひも付いている施設予約情報が無くなった場合、予約拡張データを削除
            RsvSisRyrkDao ryrkDao = new RsvSisRyrkDao(con);
            RsvSisRyrkModel ryrkMdl = ryrkDao.selectFromScdSid(scdSid);
            if (ryrkMdl != null) {
                int rsrRsid = ryrkMdl.getRsrRsid();
                RsvSisYrkDao yrkDao = new RsvSisYrkDao(con);
                if (rsrRsid > -1 && yrkDao.getYrkDataCnt(rsrRsid) < 1) {
                    //件数取得し0件の場合
                    ryrkDao.delete(rsrRsid);
                    //施設予約拡張区分別情報削除
                    RsvSisKryrkDao kryrkDao = new RsvSisKryrkDao(con);
                    kryrkDao.delete(rsrRsid);
                }
            }

            if (NullDefault.getInt(form.getBatchRef(), 1) == 0 || scds.size() < 1) {
                //同時登録反映無しの場合
                biz.deleteSchedule(scdSid, con);
            } else if (NullDefault.getInt(form.getBatchRef(), 1) == 1) {

                //同時登録ユーザへ反映更新
                if (scds.size() > 1) {
                    biz.deleteSchedule(scds, con);
                } else {
                    biz.deleteSchedule(scdSid, con);
                }
            }
            //ひも付いているスケジュール情報が無い拡張データを削除
            if (sceMdl != null) {
                int sceSid = sceMdl.getSceSid();
                if (ssDao.getExCount(sceSid) == 0) {
                    sch041knBiz.deleteScheduleEx(sceSid, con, getSessionUserSid(req));
                }
            }

            commitFlg = true;
        } catch (SQLException e) {
            log__.error("スケジュール登録に失敗しました" + e);
            throw e;
        } finally {
            if (commitFlg) {
                con.commit();
            } else {
                con.rollback();
            }
        }

        String delete = gsMsg.getMessage("cmn.delete");

        //ログ出力処理
        SchCommonBiz schBiz = new SchCommonBiz(con, getRequestModel(req));
        schBiz.outPutApiLog(reqMdl, con, sessionUsrSid, ApiSchDeleteAction.class.getName(),
                delete, GSConstLog.LEVEL_TRACE, "[scdSid]" + form.getSchSid());
        Document doc = new Document();
        doc.addContent(_createElement("Result", "OK"));
        return doc;
    }
}
