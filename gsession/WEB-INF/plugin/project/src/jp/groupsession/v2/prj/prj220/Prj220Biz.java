package jp.groupsession.v2.prj.prj220;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.prj.GSConstProject;
import jp.groupsession.v2.prj.dao.PrjUserConfDao;
import jp.groupsession.v2.prj.model.PrjUserConfModel;


/**
 * <br>[機  能] プロジェクト管理 個人設定 スケジュール表示設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Prj220Biz {
    /** コネクション */
    private Connection con__ = null;
    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     */
    public Prj220Biz(Connection con) {
        con__ = con;
    }

    /**
     * <br>[機  能] 初期値セット
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Prj220ParamModel
     * @param buMdl ベースユーザモデル
     * @throws SQLException SQL実行時例外
     */
    public void setInitData(Prj220ParamModel paramMdl, BaseUserModel buMdl) throws SQLException {

        PrjUserConfDao confDao = new PrjUserConfDao(con__);
        PrjUserConfModel confMdl = confDao.select(buMdl.getUsrsid());
        if (confMdl != null) {
            paramMdl.setPrj220SchDsp(confMdl.getPucSchKbn());
        }
    }
    /**
     * <br>[機  能] 個人設定更新
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Prj220ParamModel
     * @param buMdl セッションユーザモデル
     * @throws SQLException SQL実行時例外
     * @return addEditFlg 登録モード(0:登録 1:編集)
     */
    public int updateUserConf(Prj220ParamModel paramMdl, BaseUserModel buMdl)
        throws SQLException {

        int usrSid = buMdl.getUsrsid();
        UDate now = new UDate();
        int addEditFlg = 1;

        PrjUserConfModel pucMdl = new PrjUserConfModel();
        pucMdl.setUsrSid(usrSid);
        pucMdl.setPucEuid(usrSid);
        pucMdl.setPucEdate(now);
        pucMdl.setPucSchKbn(paramMdl.getPrj220SchDsp());

        PrjUserConfDao pucDao = new PrjUserConfDao(con__);
        int updateCnt = pucDao.updatePrjSch(pucMdl);

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
            pucDao.insert(pucMdl);
            addEditFlg = 0;
        }
        return addEditFlg;
    }
}