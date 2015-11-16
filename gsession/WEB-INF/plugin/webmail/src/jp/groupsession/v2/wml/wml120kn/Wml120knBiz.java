package jp.groupsession.v2.wml.wml120kn;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.GSConstWebmail;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.wml.dao.base.WmlLabelDao;
import jp.groupsession.v2.wml.model.base.WmlLabelModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] WEBメール ラベル登録確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml120knBiz {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Wml120knBiz.class);
    /** DBコネクション */
    private Connection con__ = null;

    /**
     * <p>デフォルトコンストラクター
     */
    public Wml120knBiz() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public Wml120knBiz(Connection con) {
        con__ = con;
    }

    /**
     * <br>[機  能] 初期表示設定を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param paramMdl パラメータ情報
     * @throws SQLException SQL実行時例外
     */
    public void setInitData(RequestModel reqMdl,  Wml120knParamModel paramMdl) throws SQLException {
    }

    /**
     * <br>[機  能] 登録、または更新処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param userSid ログインユーザSID
     * @param cntCon 採番コントローラ
     * @throws SQLException SQL実行例外
     * @throws IOToolsException IOエラー
     */
    public void doAddEdit(
        Wml120knParamModel paramMdl,
        int userSid,
        MlCountMtController cntCon) throws SQLException, IOToolsException {

        //処理モード(新規登録）
        if (paramMdl.getWmlLabelCmdMode() == GSConstWebmail.CMDMODE_ADD) {
            //新規登録
            log__.debug("新規登録");
            doInsert(paramMdl, userSid, cntCon);

        //編集
        } else if (paramMdl.getWmlLabelCmdMode() == GSConstWebmail.CMDMODE_EDIT) {
            log__.debug("編集");
            doUpdate(paramMdl, userSid);
        }
    }

    /**
     * <br>[機  能] 新規登録画面処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param userSid ログインユーザSID
     * @param cntCon 採番コントローラ
     * @throws SQLException SQL実行例外
     */
    public void doInsert(
        Wml120knParamModel paramMdl,
        int userSid,
        MlCountMtController cntCon) throws SQLException {

        boolean commitFlg = false;

        try {

            con__.setAutoCommit(false);

            //ラベルSID採番
            int lbSaiSid = (int) cntCon.getSaibanNumber(
                    GSConstWebmail.SBNSID_WEBMAIL,
                    GSConstWebmail.SBNSID_SUB_LABEL,
                    userSid);

            //ラベル登録Model
            WmlLabelModel wlMdl  = __getLabelInsertMdl(paramMdl, userSid, lbSaiSid);

            //ラベルを登録する
            WmlLabelDao wlDao = new WmlLabelDao(con__);
            wlDao.insert(wlMdl);

            commitFlg = true;

        } catch (SQLException e) {
            log__.error("SQLException", e);
        throw e;
        } finally {
            if (commitFlg) {
                con__.commit();
            } else {
                JDBCUtil.rollback(con__);
            }
        }
    }

    /**
     * <br>[機  能] 編集画面登録処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    public void doUpdate(
        Wml120knParamModel paramMdl,
        int userSid) throws SQLException {

        boolean commitFlg = false;

        try {

            con__.setAutoCommit(false);

            //ラベル更新Model
            WmlLabelModel wlMdl  = __getLabelUpdateMdl(paramMdl, userSid);

            //ラベルを更新する
            Wml120knDao wldao = new Wml120knDao(con__);
            wldao.update(wlMdl);

            commitFlg = true;

        } catch (SQLException e) {
            log__.error("SQLException", e);
            throw e;
        } finally {
            if (commitFlg) {
                con__.commit();
            } else {
                JDBCUtil.rollback(con__);
            }
        }
    }

    /**
     * <br>[機  能] 新規登録用のWmlLabelModelを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param userSid ユーザーSID
     * @param lbSaiSid 採番SID
     * @return WmlLabelModel
     * @throws SQLException SQL実行例外
     */
    private WmlLabelModel __getLabelInsertMdl(
        Wml120knParamModel paramMdl,
        int userSid,
        int lbSaiSid) throws SQLException {

        WmlLabelModel wlMdl = new WmlLabelModel();
        WmlLabelDao wldao = new WmlLabelDao(con__);

        wlMdl.setWlbSid(lbSaiSid);
        wlMdl.setUsrSid(userSid);
        wlMdl.setWlbName(paramMdl.getWml120LabelName());
        wlMdl.setWlbType(GSConstWebmail.LABELTYPE_ONES);
        wlMdl.setWacSid(paramMdl.getWml110accountSid());

        //並び順
        wlMdl.setWlbOrder(wldao.maxSortNumber(wlMdl.getWacSid()) + 1);

        return wlMdl;
    }

    /**
     * <br>[機  能] 更新のWmlLabelModelを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param userSid ユーザーSID
     * @return WmlLabelModel
     * @throws SQLException SQL実行例外
     */
    private WmlLabelModel __getLabelUpdateMdl(
        Wml120knParamModel paramMdl,
        int userSid) throws SQLException {

        WmlLabelModel wlMdl = new WmlLabelModel();

        wlMdl.setWlbSid(paramMdl.getWmlEditLabelId());
        wlMdl.setUsrSid(userSid);
        wlMdl.setWlbName(paramMdl.getWml120LabelName());
        wlMdl.setWlbType(GSConstWebmail.LABELTYPE_ONES);
        wlMdl.setWacSid(paramMdl.getWml110accountSid());

        return wlMdl;
    }
}
