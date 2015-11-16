package jp.groupsession.v2.prj.prj200;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.prj.GSConstProject;
import jp.groupsession.v2.prj.PrjCommonBiz;
import jp.groupsession.v2.prj.dao.PrjUserConfDao;
import jp.groupsession.v2.prj.model.PrjUserConfModel;
import jp.groupsession.v2.struts.msg.GsMessage;

/**
 * <br>[機  能] プロジェクト管理 個人設定 プロジェクトメイン初期値設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Prj200Biz {

    /** コネクション */
    private Connection con__ = null;
    /** リクエストモデル */
    private RequestModel reqMdl__ = null;
    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl RequestModel
     */
    public Prj200Biz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期値セット
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Prj200ParamModel
     * @param buMdl ベースユーザモデル
     * @throws SQLException SQL実行時例外
     */
    public void setInitData(Prj200ParamModel paramMdl, BaseUserModel buMdl) throws SQLException {

        PrjUserConfDao confDao = new PrjUserConfDao(con__);
        PrjUserConfModel confMdl = confDao.select(buMdl.getUsrsid());

        if (confMdl != null) {
            paramMdl.setPrj200Date(String.valueOf(confMdl.getPucMainDate()));
            paramMdl.setPrj200Status(String.valueOf(confMdl.getPucMainStatus()));
            paramMdl.setPrj200Member(String.valueOf(confMdl.getPucMainMember()));
        }
    }

    /**
     * <br>[機  能] リストと選択値を設定
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Prj200ParamModel
     * @param buMdl ベースユーザモデル
     * @throws SQLException SQL実行時例外
     */
    public void setList(Prj200ParamModel paramMdl, BaseUserModel buMdl) throws SQLException {
        GsMessage gsMsg = new GsMessage(reqMdl__);
        PrjCommonBiz pcBiz = new PrjCommonBiz(con__, gsMsg, reqMdl__);

        //日付コンボリスト
        paramMdl.setPrj200DateList(pcBiz.getTargetDateLabel());

        //日付選択値
        paramMdl.setPrj200Date(
                NullDefault.getStringZeroLength(
                        paramMdl.getPrj200Date(),
                        String.valueOf(GSConstProject.DATE_THE_PAST)));

        //状態リスト
        paramMdl.setPrj200StatusList(pcBiz.getStatusLabel());

        //状態選択値
        paramMdl.setPrj200Status(
                NullDefault.getStringZeroLength(
                        paramMdl.getPrj200Status(),
                        String.valueOf(GSConstProject.STATUS_YOTEI_AND_MIKAN)));

        //メンバリスト
        paramMdl.setPrj200MemberList(pcBiz.getMemberLabel(buMdl.getUsrsid()));

        //メンバ選択値
        paramMdl.setPrj200Member(
                NullDefault.getStringZeroLength(
                        paramMdl.getPrj200Member(),
                        String.valueOf(buMdl.getUsrsid())));
    }
}