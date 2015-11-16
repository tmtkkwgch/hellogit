package jp.groupsession.v2.prj.prj200kn;

import java.sql.Connection;
import java.sql.SQLException;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.prj.GSConstProject;
import jp.groupsession.v2.prj.PrjCommonBiz;
import jp.groupsession.v2.prj.dao.PrjUserConfDao;
import jp.groupsession.v2.prj.model.PrjUserConfModel;
import jp.groupsession.v2.struts.msg.GsMessage;

/**
 * <br>[機  能] プロジェクト管理 個人設定 プロジェクトメイン初期値設定確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Prj200knBiz {

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
    public Prj200knBiz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示値セット
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Prj200knParamModel
     * @param buMdl セッションユーザモデル
     * @throws SQLException SQL実行時例外
     */
    public void setInitData(Prj200knParamModel paramMdl, BaseUserModel buMdl)
        throws SQLException {

        GsMessage gsMsg = new GsMessage(reqMdl__);
        PrjCommonBiz biz = new PrjCommonBiz(con__, gsMsg, reqMdl__);

        //日付
        paramMdl.setPrj200knDateStr(
                biz.getTargetDateString(
                        Integer.parseInt(paramMdl.getPrj200Date())));

        //状態
        paramMdl.setPrj200knStatusStr(
                biz.getStatusString(
                        Integer.parseInt(paramMdl.getPrj200Status())));

        //メンバ
        paramMdl.setPrj200knMemberStr(
                biz.getMemberString(
                        Integer.parseInt(paramMdl.getPrj200Member())));
    }

    /**
     * <br>[機  能] 個人設定更新
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Prj200knParamModel
     * @param buMdl セッションユーザモデル
     * @throws SQLException SQL実行時例外
     * @return addEditFlg 登録モード(0:登録 1:編集)
     */
    public int updateUserConf(Prj200knParamModel paramMdl, BaseUserModel buMdl)
        throws SQLException {

        int usrSid = buMdl.getUsrsid();
        UDate now = new UDate();
        int addEditFlg = 1;

        PrjUserConfModel pucMdl = new PrjUserConfModel();
        pucMdl.setUsrSid(usrSid);
        pucMdl.setPucEuid(usrSid);
        pucMdl.setPucEdate(now);
        pucMdl.setPucMainDate(Integer.parseInt(paramMdl.getPrj200Date()));
        pucMdl.setPucMainStatus(Integer.parseInt(paramMdl.getPrj200Status()));
        pucMdl.setPucMainMember(Integer.parseInt(paramMdl.getPrj200Member()));

        PrjUserConfDao pucDao = new PrjUserConfDao(con__);
        int updateCnt = pucDao.updatePrjMain(pucMdl);

        //更新件数が0件の場合、プロジェクト個人設定の新規登録を行う
        if (updateCnt == 0) {
            pucMdl.setPucPrjCnt(10);
            pucMdl.setPucTodoCnt(10);
            pucMdl.setPucAuid(usrSid);
            pucMdl.setPucAdate(now);
            pucMdl.setPucTodoDate(GSConstProject.DATE_THE_PAST);
            pucMdl.setPucTodoProject(GSConstProject.PROJECT_ALL);
            pucMdl.setPucTodoStatus(GSConstProject.STATUS_YOTEI_AND_MIKAN);
            pucMdl.setPucPrjProject(GSConstProject.PRJ_MEMBER_NOT_END);
            pucMdl.setPucDefDsp(GSConstProject.DSP_TODO);
            pucMdl.setPucSchKbn(GSConstProject.DSP_TODO_SHOW);
            pucMdl.setPucTodoDsp(GSConstProject.DSP_TODO_EASY);
            pucDao.insert(pucMdl);
            addEditFlg = 0;
        }
        return addEditFlg;
    }
}