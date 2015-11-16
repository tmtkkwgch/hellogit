package jp.groupsession.v2.zsk.zsk130kn;

import java.sql.Connection;
import java.sql.SQLException;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.zsk.GSConstZaiseki;
import jp.groupsession.v2.zsk.dao.ZaiPriConfDao;
import jp.groupsession.v2.zsk.model.ZaiPriConfModel;

/**
 * <br>[機  能] 在席管理 個人設定 座席表メンバー表示設定確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Zsk130knBiz {

    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param reqMdl RequestModel
     */
    public Zsk130knBiz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
   }

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Zsk130knParamModel
     */
    public void setInitData(Zsk130knParamModel paramMdl) {

        GsMessage gsMsg = new GsMessage(reqMdl__);
        String name = gsMsg.getMessage("cmn.name");
        String number = gsMsg.getMessage("cmn.employee.staff.number");
        String post = gsMsg.getMessage("cmn.post");
        String birth = gsMsg.getMessage("cmn.birthday");
        String zaiseki = gsMsg.getMessage("zsk.20");
        String comment = gsMsg.getMessage("zsk.23");
        String sortkey1 = gsMsg.getMessage("cmn.sortkey") + "1";
        String sortkey2 = gsMsg.getMessage("cmn.sortkey") + "2";

        String[] sortZskText = {name, number, post, birth, zaiseki, comment,
                sortkey1, sortkey2};

        int sort1 = paramMdl.getZsk130SortKey1();
        int sort2 = paramMdl.getZsk130SortKey2();
        int idx = 0;
        for (int key : GSConstZaiseki.SORT_KEY_ZSK) {
            if (key == sort1) {
                sort1 = idx;
            }
            if (key == sort2) {
                sort2 = idx;
            }
            idx++;
        }

        paramMdl.setZsk130knSortKey1Name(
                sortZskText[sort1]);

        paramMdl.setZsk130knSortKey2Name(
                sortZskText[sort2]);
    }

    /**
     * <br>[機  能] 個人設定更新処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Zsk130knParamModel
     * @param con コネクション
     * @param usrMdl セッションユーザModel
     * @throws SQLException SQL実行時例外
     */
    public void updatePriConf(Zsk130knParamModel paramMdl,
                               Connection con,
                               BaseUserModel usrMdl) throws SQLException {

        ZaiPriConfModel param = new ZaiPriConfModel();
        UDate now = new UDate();
        int usrSid = usrMdl.getUsrsid();
        param.setUsrSid(usrSid);
        param.setZpcEid(usrSid);
        param.setZpcEdate(now);
        param.setZpcSortKey1(paramMdl.getZsk130SortKey1());
        param.setZpcSortOrder1(paramMdl.getZsk130SortOrder1());
        param.setZpcSortKey2(paramMdl.getZsk130SortKey2());
        param.setZpcSortOrder2(paramMdl.getZsk130SortOrder2());

        ZaiPriConfDao dao = new ZaiPriConfDao(con);
        int count = dao.updateZskMemberSetting(param);
        if (count < 1) {
            param.setZifSid(GSConstZaiseki.ZASEKI_NOT_SELECT);
            param.setZpcReload(GSConstZaiseki.AUTO_RELOAD_10MIN);
            param.setZpcAid(usrSid);
            param.setZpcAdate(now);
            dao.insert(param);
        }
    }
}