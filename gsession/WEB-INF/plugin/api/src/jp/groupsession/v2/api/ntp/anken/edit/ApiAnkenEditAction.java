package jp.groupsession.v2.api.ntp.anken.edit;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.jdom.Document;
import org.jdom.Element;

import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.api.AbstractApiAction;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.ntp.GSConstNippou;
import jp.groupsession.v2.ntp.dao.NtpAnShohinDao;
import jp.groupsession.v2.ntp.dao.NtpAnkenDao;
import jp.groupsession.v2.ntp.model.NtpAnShohinModel;
import jp.groupsession.v2.ntp.model.NtpAnkenModel;
/**
 *
 * <br>[機  能] 日報 案件編集をするWEBAPIアクション
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiAnkenEditAction extends AbstractApiAction {
    /**ロガー クラス*/
    private static Log log__ = LogFactory.getLog(new Throwable().getStackTrace()[0].getClassName());
    @Override
    public Document createXml(ActionForm form, HttpServletRequest req,
            HttpServletResponse res, Connection con, BaseUserModel umodel)
            throws Exception {
        log__.debug("createXml start");

        //日報プラグインアクセス権限確認
        if (!canAccsessSelectPlugin(GSConst.PLUGIN_ID_NIPPOU)) {
            addErrors(req, addCantAccsessPluginError(req, null, GSConst.PLUGIN_ID_NIPPOU));
            return null;
        }
        ApiAnkenEditForm thisForm = (ApiAnkenEditForm) form;
        ActionErrors err = thisForm.validateCheck(con);
        if (!err.isEmpty()) {
            addErrors(req, err);
            return null;
        }
        boolean commitFlg = false;
        con.setAutoCommit(false);

        try {
            int usrSid = this.getSessionUserModel(req).getUsrsid();
            NtpAnkenDao ankenDao = new NtpAnkenDao(con);
            NtpAnkenModel ankenMdl = __createNtpAnken(usrSid);



            UDate date = new UDate();
            date.setZeroHhMmSs();
            date.setDate(Integer.parseInt(thisForm.getDate().substring(0, 4)),
                    Integer.parseInt(thisForm.getDate().substring(5, 7)),
                    Integer.parseInt(thisForm.getDate().substring(8, 10)));
            ankenMdl.setNanDate(date);

            ankenMdl.setNanCode(thisForm.getNanCode());
            ankenMdl.setNanName(thisForm.getNanName());
            ankenMdl.setNanDetial(thisForm.getNanDetail());
            ankenMdl.setAcoSid(Integer.parseInt(thisForm.getAcoSid()));
            ankenMdl.setAbaSid(Integer.parseInt(thisForm.getAbaSid()));
            ankenMdl.setNgpSid(Integer.parseInt(thisForm.getNgpSid()));
            ankenMdl.setNanMikomi(Integer.parseInt(thisForm.getNanMikomi()));
            ankenMdl.setNanKinMitumori(Integer.parseInt(thisForm.getNanKinMitumori()));
            ankenMdl.setNanKinJutyu(Integer.parseInt(thisForm.getNanKinJutyu()));
            ankenMdl.setNanSyodan(Integer.parseInt(thisForm.getNanSyodan()));
            ankenMdl.setNcnSid(Integer.parseInt(thisForm.getMcnSid()));

            int nanSid = Integer.parseInt(thisForm.getNanSid());
            boolean editMode = true;
            if (nanSid == -1) {
                editMode = false;
                //追加モード
                MlCountMtController cntCon = getCountMtController(req);
                //SID採番
                nanSid = (int) cntCon.getSaibanNumber(GSConstNippou.SBNSID_NIPPOU,
                    GSConstNippou.SBNSID_SUB_ANKEN, usrSid);
                ankenMdl.setNanSid(nanSid);
                ankenDao.insert(ankenMdl);
            } else {
                //変更モード
                ankenMdl.setNanSid(nanSid);
                ankenDao.update(ankenMdl);
            }

            //案件商品情報の登録
            NtpAnShohinDao anShohinDao = new NtpAnShohinDao(con);
            if (editMode) {
                //変更モード
                anShohinDao.delete(nanSid);
            }

            if (thisForm.getNanShohin() != null
                && thisForm.getNanShohin().length > 0) {
                for (String shohinSid : thisForm.getNanShohin()) {
                    NtpAnShohinModel anShohinMdl = __createNtpAnShohin(usrSid);
                    anShohinMdl.setNhnSid(Integer.parseInt(shohinSid));
                    anShohinMdl.setNanSid(nanSid);
                    anShohinDao.insert(anShohinMdl);
                }
            }
            commitFlg = true;


        } catch (SQLException e) {
            log__.error("SQLException", e);
            throw e;
        } finally {
            if (commitFlg) {
                con.commit();
            } else {
                JDBCUtil.rollback(con);
            }
        }
        Element result = new Element("Result");
        Document doc = new Document(result);

        result.addContent("OK");

        return doc;
    }
    /**
     * <br>[機  能] 案件情報を作成
     * <br>[解  説]
     * <br>[備  考]
     * @param usrSid ユーザSID
     * @return NtpAnkenModel
     */
    private NtpAnkenModel __createNtpAnken(int usrSid) {

        UDate nowDate = new UDate();
        NtpAnkenModel mdl = new NtpAnkenModel();
        mdl.setNanAuid(usrSid);
        mdl.setNanAdate(nowDate);
        mdl.setNanEuid(usrSid);
        mdl.setNanEdate(nowDate);
        return mdl;
    }
    /**
     * <br>[機  能] 案件商品情報を作成
     * <br>[解  説]
     * <br>[備  考]
     * @param usrSid ユーザSID
     * @return NtpAnkenModel
     */
    private NtpAnShohinModel __createNtpAnShohin(int usrSid) {

        UDate nowDate = new UDate();
        NtpAnShohinModel mdl = new NtpAnShohinModel();
        mdl.setNasAuid(usrSid);
        mdl.setNasAdate(nowDate);
        mdl.setNasEuid(usrSid);
        mdl.setNasEdate(nowDate);
        return mdl;
    }
}
