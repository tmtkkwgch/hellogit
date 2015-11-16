package jp.groupsession.v2.fil.fil250kn;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.fil.dao.FileCallConfDao;
import jp.groupsession.v2.fil.fil250.Fil250Biz;
import jp.groupsession.v2.fil.model.FileCallConfModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 管理者設定 更新通知一括設定確認画面のビジネスロジック
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Fil250knBiz extends Fil250Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Fil250knBiz.class);

    /** DBコネクション */
    private Connection con__ = null;

    /**
     * <p>Set Connection
     * @param con Connection
     * @param reqMdl RequestModel
     */
    public Fil250knBiz(Connection con, RequestModel reqMdl) {
        super(con, reqMdl);
        con__ = con;
    }

    /**
     * <br>[機  能] 初期表示を設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil250knParamModel
     * @throws SQLException SQL実行例外
     */
    public void setInitData(Fil250knParamModel paramMdl) throws SQLException {

        log__.debug("fil250knBiz Start");

        //フォルダパスを設定する。
        _setDirPath(con__, paramMdl);

        //更新通知対象者 ユーザリスト
        paramMdl.setFil250callUserCombo(_getCallUserCombo(con__, paramMdl));
    }

    /**
     * <br>[機  能] 更新通知設定を行う。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil250knParamModel
     * @throws Exception 実行例外
     */
    public void entryCallUser(Fil250knParamModel paramMdl) throws Exception {

        int fdrSid = NullDefault.getInt(paramMdl.getFil250DirSid(), -1);

        //指定されたディレクトリの更新通知設定を削除
        FileCallConfDao confDao = new FileCallConfDao(con__);
        confDao.delete(fdrSid);

        //選択されたユーザの更新通知設定を行う
        String[] callUser = paramMdl.getFil250SvCallUser();
        if (callUser != null && callUser.length > 0) {
            FileCallConfModel confMdl = new FileCallConfModel();
            confMdl.setFdrSid(fdrSid);

            for (String userSid : callUser) {
                confMdl.setUsrSid(Integer.parseInt(userSid));
                confDao.insert(confMdl);
            }
        }

    }
}