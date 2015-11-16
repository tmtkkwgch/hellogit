package jp.groupsession.v2.prj.prj140;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmDao;
import jp.groupsession.v2.prj.GSConstProject;
import jp.groupsession.v2.prj.GSValidateProject;
import jp.groupsession.v2.prj.prj130.Prj130Form;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

/**
 * <br>[機  能] プロジェクト管理 プロジェクトテンプレート登録画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Prj140Form extends Prj130Form {

    /** 処理モード */
    private String prj140cmdMode__ = null;
    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param con コネクション
     * @param buMdl セッションユーザModel
     * @param req リクエスト
     * @return エラー
     * @throws SQLException SQL実行例外
     */
    public ActionErrors validate140(Connection con, BaseUserModel buMdl, HttpServletRequest req)
        throws SQLException {
        GSValidateProject gsValidatePrj = new GSValidateProject(req);
        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;
        GsMessage gsMsg = new GsMessage();
        //所属メンバー
        String textSquad = gsMsg.getMessage(req, "cmn.squad");
        //プロジェクト管理者
        String textProjectAdmin = gsMsg.getMessage(req, "project.src.32");
        //テンプレート名称
        String textProjectTmpName = gsMsg.getMessage(req, "project.prj140.6");
        //テンプレート名称
        GSValidateProject.validateTextBoxInput(
                errors,
                getPrj140prtTmpName(),
                textProjectTmpName,
                GSConstProject.MAX_LENGTH_TMP_NAME,
                true);

        int errSize = 0;
        //プロジェクトID
        String textProjectId = gsMsg.getMessage(req, "project.31");
        //プロジェクトID
        GSValidateProject.validateTextBoxInput(
                errors,
                getPrj140prtId(),
                textProjectId,
                GSConstProject.MAX_LENGTH_PRJ_ID,
                false);

        //プロジェクトIDの入力形式が正しい時は更に重複チェックを行う
        if (errSize == errors.size()) {

//            int usrSid = -1;
//
//            //処理モード
//            String cmdMode = getPrj020cmdMode();
//
//            //登録モード
//            if (cmdMode.equals(GSConstProject.CMD_MODE_ADD)) {
//                usrSid = buMdl.getUsrsid();
//            //更新モード
//            } else if (cmdMode.equals(GSConstProject.CMD_MODE_EDIT)) {
//                //マイプロジェクトの場合ユーザSIDを取得(通常プロジェクトならば-1)
//                PrjPrjdataDao ppDao = new PrjPrjdataDao(con);
//                usrSid = ppDao.getEditMyProjectUsrSid(getPrj020prjSid());
//            }
//
//            PrjPrjdataDao prjDataDao = new PrjPrjdataDao(con);
//            if (prjDataDao.isExistSameProjectId(
//                    getPrj020prjSid(), prj020prjId__, usrSid)) {
//                msg =
//                    new ActionMessage(
//                            "error.input.double.timezone",
//                            GSConstProject.MSG_PRJ_ID,
//                            GSConstProject.MSG_PRJ_ID);
//                StrutsUtil.addMessage(
//                        errors, msg, "prj020prjId.error.input.double.timezone");
//            }
        }
        //プロジェクト名称
        String textProjectName = gsMsg.getMessage(req, "project.40");
        //プロジェクト名称
        GSValidateProject.validateTextBoxInput(
                errors,
                getPrj140prtName(),
                textProjectName,
                GSConstProject.MAX_LENGTH_PRJ_NAME,
                false);
        //プロジェクト略称
        String textProjectShortName = gsMsg.getMessage(req, "project.41");
        //プロジェクト略称
        GSValidateProject.validateTextBoxInput(
                errors,
                getPrj140prtNameS(),
                textProjectShortName,
                GSConstProject.MAX_LENGTH_PRJ_SHORT_NAME,
                false);
        //予算
        String textProjectYosan = gsMsg.getMessage(req, "project.10");
        //予算
        GSValidateProject.validateTextBoxInputNum(
                errors,
                getPrj140yosan(),
                textProjectYosan,
                GSConstProject.MAX_LENGTH_YOSAN,
                false);

        errSize = errors.size();
        //開始
        String textProjectStart = gsMsg.getMessage(req, "cmn.start");
        //開始
        gsValidatePrj.validateYMD(
                errors,
                textProjectStart,
                getPrj140startYear(),
                getPrj140startMonth(),
                getPrj140startDay(),
                false);
        //終了
        String textProjectEnd = gsMsg.getMessage(req, "main.src.man250.30");
        //終了
        gsValidatePrj.validateYMD(
                errors,
                textProjectEnd,
                getPrj140endYear(),
                getPrj140endMonth(),
                getPrj140endDay(),
                false);

        if (errSize == errors.size()
            && !NullDefault.getString(getPrj140startYear(), "").equals("")
            && !NullDefault.getString(getPrj140startMonth(), "").equals("")
            && !NullDefault.getString(getPrj140startDay(), "").equals("")
            && !NullDefault.getString(getPrj140endYear(), "").equals("")
            && !NullDefault.getString(getPrj140endMonth(), "").equals("")
            && !NullDefault.getString(getPrj140endDay(), "").equals("")) {

            //大小チェック
            UDate dateStart = new UDate();
            dateStart.setDate(NullDefault.getInt(getPrj140startYear(), -1),
                              NullDefault.getInt(getPrj140startMonth(), -1),
                              NullDefault.getInt(getPrj140startDay(), -1));

            UDate dateEnd = new UDate();
            dateEnd.setDate(NullDefault.getInt(getPrj140endYear(), -1),
                            NullDefault.getInt(getPrj140endMonth(), -1),
                            NullDefault.getInt(getPrj140endDay(), -1));

            GSValidateProject.validateDataRange(
                    errors,
                    textProjectStart,
                    textProjectEnd,
                    dateStart,
                    dateEnd);
        }
        //状態
        String textStatus = gsMsg.getMessage(req, "cmn.status");
        //状態
        if (getPrj140status() <= 0) {
            msg = new ActionMessage("error.select.required.text", textStatus);
            StrutsUtil.addMessage(errors, msg, "prj140status.error.select.required.text");
        }
        //目標・目的
        String textTarget = gsMsg.getMessage(req, "project.21");
        //目標・目的
        GSValidateProject.validateTextarea(
                errors,
                getPrj140mokuhyou(),
                textTarget,
                GSConstProject.MAX_LENGTH_TARGET,
                false);
        //内容
        String textContent = gsMsg.getMessage(req, "cmn.content");
        //内容
        GSValidateProject.validateTextarea(
                errors,
                getPrj140naiyou(),
                textContent,
                GSConstProject.MAX_LENGTH_CONTENT,
                false);

        CmnUsrmDao udao = new CmnUsrmDao(con);

//        //所属メンバー
//        String[] hdnMember = getPrj140hdnMember();
//        if (hdnMember != null && hdnMember.length > 0) {
//            ArrayList<String> memList = new ArrayList<String>();
//            for (String member : hdnMember) {
//                memList.add(member);
//            }
//            int count = udao.getCountDeleteUser(memList);
//            if (count > 0) {
//                msg = new ActionMessage("error.select.user.delete", GSConstProject.MSG_MEMBER);
//                StrutsUtil.addMessage(errors, msg, "prj140hdnMember.error.select.user.delete");
//            }
//        }

        //所属メンバー
        String[] hdnMember = getPrj140hdnMember();
        if (hdnMember != null && hdnMember.length > 0) {
            ArrayList<String> memList = new ArrayList<String>();
            for (String member : hdnMember) {
                memList.add(member);
            }

            ArrayList<String> spUsrSidList = new ArrayList<String>();

            if (memList != null && memList.size() > 0) {

                for (String hdn : memList) {

                    String[] splitStr = hdn.split(GSConst.GSESSION2_ID);
                    spUsrSidList.add(String.valueOf(splitStr[0]));
                }
            }

            int count = udao.getCountDeleteUser(spUsrSidList);
            if (count > 0) {
                msg = new ActionMessage("error.select.user.delete", textSquad);
                StrutsUtil.addMessage(errors, msg, "prj140hdnMember.error.select.user.delete");
            }
        }

        //プロジェクト管理者
        String[] hdnAdmin = getPrj140hdnAdmin();
        if (hdnAdmin != null && hdnAdmin.length > 0) {
            ArrayList<String> memList = new ArrayList<String>();
            for (String member : hdnAdmin) {
                memList.add(member);
            }
            int count = udao.getCountDeleteUser(memList);
            if (count > 0) {
                msg = new ActionMessage(
                        "error.select.user.delete", textProjectAdmin);
                StrutsUtil.addMessage(errors, msg, "prj140hdnAdmin.error.select.user.delete");
            }
        }

        return errors;
    }

    /**
     * <br>[機  能] Cmn999Formに画面パラメータをセットする
     * <br>[解  説]
     * <br>[備  考]
     * @param cmn999Form Cmn999Form
     */
    public void setcmn999FormParam(Cmn999Form cmn999Form) {

        super.setcmn999FormParam(cmn999Form);

        cmn999Form.addHiddenParam("prjTmpMode", getPrjTmpMode());
        cmn999Form.addHiddenParam("prtSid", getPrtSid());
        cmn999Form.addHiddenParam("prj140prtTmpName", getPrj140prtTmpName());
        cmn999Form.addHiddenParam("prj140prtId", getPrj140prtId());
        cmn999Form.addHiddenParam("prj140prtName", getPrj140prtName());
        cmn999Form.addHiddenParam("prj140prtNameS", getPrj140prtNameS());
        cmn999Form.addHiddenParam("prj140yosan", getPrj140yosan());
        cmn999Form.addHiddenParam("prj140koukai", getPrj140koukai());
        cmn999Form.addHiddenParam("prj140startYear", getPrj140startYear());
        cmn999Form.addHiddenParam("prj140startMonth", getPrj140startMonth());
        cmn999Form.addHiddenParam("prj140startDay", getPrj140startDay());
        cmn999Form.addHiddenParam("prj140endYear", getPrj140endYear());
        cmn999Form.addHiddenParam("prj140endMonth", getPrj140endMonth());
        cmn999Form.addHiddenParam("prj140endDay", getPrj140endDay());
        cmn999Form.addHiddenParam("prj140status", getPrj140status());
        cmn999Form.addHiddenParam("prj140mokuhyou", getPrj140mokuhyou());
        cmn999Form.addHiddenParam("prj140naiyou", getPrj140naiyou());
        cmn999Form.addHiddenParam("prj140group", getPrj140group());
        cmn999Form.addHiddenParam("prj140kengen", getPrj140kengen());
        cmn999Form.addHiddenParam("prj140smailKbn", getPrj140smailKbn());
        cmn999Form.addHiddenParam("prj140syozokuMember", getPrj140syozokuMember());
        cmn999Form.addHiddenParam("prj140user", getPrj140user());
        cmn999Form.addHiddenParam("prj140hdnMember", getPrj140hdnMember());
        cmn999Form.addHiddenParam("prj140adminMember", getPrj140adminMember());
        cmn999Form.addHiddenParam("prj140prjMember", getPrj140prjMember());
        cmn999Form.addHiddenParam("prj140hdnAdmin", getPrj140hdnAdmin());

    }

    /**
     * <p>prj140cmdMode を取得します。
     * @return prj140cmdMode
     */
    public String getPrj140cmdMode() {
        return prj140cmdMode__;
    }

    /**
     * <p>prj140cmdMode をセットします。
     * @param prj140cmdMode prj140cmdMode
     */
    public void setPrj140cmdMode(String prj140cmdMode) {
        prj140cmdMode__ = prj140cmdMode;
    }
}