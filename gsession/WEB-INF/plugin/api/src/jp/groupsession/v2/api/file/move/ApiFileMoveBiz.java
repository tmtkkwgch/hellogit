package jp.groupsession.v2.api.file.move;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.fil.fil090kn.Fil090knBiz;

/**
 * <br>[機  能] フォルダ・ファイルの移動を行うWEBAPIビジネスロジック
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiFileMoveBiz {


    /**
     * <p>Set Connection
     */
    public ApiFileMoveBiz() {
    }

    /**
     * <br>[機  能] フォルダ・ファイルを移動する。
     * <br>[解  説]
     * <br>[備  考]
     * @param param ParamModel
     * @param reqMdl リクエストモデル
     * @param con コネクション
     * @throws SQLException 実行例外
     */
    public void registerData(ApiFileMoveParamModel param,
            RequestModel reqMdl,
            Connection con) throws SQLException {

        Fil090knBiz moveBiz = new Fil090knBiz(con, reqMdl);
        //移動元ディレクトリ
        int dirSid = NullDefault.getInt(param.getFdrSid(), 0);

        //移動先ディレクトリ
        int sltDirSid = NullDefault.getInt(param.getFdrParentSid(), 0);

        int usrSid = reqMdl.getSmodel().getUsrsid();


        //編集
        moveBiz.moveDir(dirSid, sltDirSid, 0, usrSid);


    }
}