package jp.groupsession.v2.fil.ptl010;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.biz.PortletBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.fil.FilCommonBiz;
import jp.groupsession.v2.fil.FilTreeBiz;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.ptl020.FilPtl020Biz;

/**
 * ポータル ファイル管理メインツリー画面で使用するビジネスロジッククラス
 * @author JTS
 */
public class FilPtl010Biz implements PortletBiz {

    /** DBコネクション */
    private Connection con__ = null;
    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * デフォルトコンストラクタ
     */
    public FilPtl010Biz() {
    }

    /**
     * デフォルトコンストラクタ
     * @param con Connection
     * @param reqMdl RequestModel
     */
    public FilPtl010Biz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }

    /**
     * <br>初期表示画面情報を取得します
     * @param paramMdl FilPtl010ParamModel
     * @param umodel BaseUserModel
     * @throws SQLException SQL実行時例外
     */
    public void setInitData(FilPtl010ParamModel paramMdl,
            BaseUserModel umodel) throws SQLException {

        FilCommonBiz filBiz = new FilCommonBiz(con__, reqMdl__);

        //表示キャビネットSIDを取得
        int cabinetSid = paramMdl.getDspFcbSid();
        if (cabinetSid < 1) {
            paramMdl.setDspFcbSid(0);
            return;
        }

        //キャビネットへのアクセス権限があるか判定する。
        boolean errorFlg = filBiz.isAccessAuthUser(cabinetSid, con__, umodel);
        if (!errorFlg) {
            //アクセス権限なし
            paramMdl.setDspFcbSid(0);
            return;
        }

        //特権ユーザ判定
        boolean superUser = filBiz.isEditCabinetUser(cabinetSid, con__);
        //ユーザSID
        int sessionUsrSid = umodel.getUsrsid();

        //Tree情報取得
        FilTreeBiz treeBiz = new FilTreeBiz(con__);
        paramMdl.setTreeFormLv0(
                treeBiz.getFileTree(cabinetSid,
                                    GSConstFile.DIRECTORY_LEVEL_0,
                                    sessionUsrSid,
                                    -1,
                                    superUser));
        paramMdl.setTreeFormLv1(
                treeBiz.getFileTree(cabinetSid,
                                    GSConstFile.DIRECTORY_LEVEL_1,
                                    sessionUsrSid,
                                    -1,
                                    superUser));
        paramMdl.setTreeFormLv2(
                treeBiz.getFileTree(cabinetSid,
                                    GSConstFile.DIRECTORY_LEVEL_2,
                                    sessionUsrSid,
                                    -1,
                                    superUser));
        paramMdl.setTreeFormLv3(
                treeBiz.getFileTree(cabinetSid,
                                    GSConstFile.DIRECTORY_LEVEL_3,
                                    sessionUsrSid,
                                    -1,
                                    superUser));
        paramMdl.setTreeFormLv4(
                treeBiz.getFileTree(cabinetSid,
                                    GSConstFile.DIRECTORY_LEVEL_4,
                                    sessionUsrSid,
                                    -1,
                                    superUser));
        paramMdl.setTreeFormLv5(
                treeBiz.getFileTree(cabinetSid,
                                    GSConstFile.DIRECTORY_LEVEL_5,
                                    sessionUsrSid,
                                    -1,
                                    superUser));
        paramMdl.setTreeFormLv6(
                treeBiz.getFileTree(cabinetSid,
                                    GSConstFile.DIRECTORY_LEVEL_6,
                                    sessionUsrSid,
                                    -1,
                                    superUser));
        paramMdl.setTreeFormLv7(
                treeBiz.getFileTree(cabinetSid,
                                    GSConstFile.DIRECTORY_LEVEL_7,
                                    sessionUsrSid,
                                    -1,
                                    superUser));
        paramMdl.setTreeFormLv8(
                treeBiz.getFileTree(cabinetSid,
                                    GSConstFile.DIRECTORY_LEVEL_8,
                                    sessionUsrSid,
                                    -1,
                                    superUser));
        paramMdl.setTreeFormLv9(
                treeBiz.getFileTree(cabinetSid,
                                    GSConstFile.DIRECTORY_LEVEL_9,
                                    sessionUsrSid,
                                    -1,
                                    superUser));
        paramMdl.setTreeFormLv10(
                treeBiz.getFileTree(cabinetSid,
                                    GSConstFile.DIRECTORY_LEVEL_10,
                                    sessionUsrSid,
                                    -1,
                                    superUser));

        //TREE OPEN設定
        paramMdl.setSelectDir("-1");

        //キャビネット名
        String cabinetName = filBiz.getCabinetName(cabinetSid, con__);
        paramMdl.setFilPtl010FcbName(cabinetName);

    }

    /**
     * <br>プラグインポートレットタイトルを取得する。
     * @param con コネクション
     * @param paramMap パラメータマップ
     * @return title ポートレットプラグインタイトル
     * @throws Exception 実行時例外
     */
    public String getPortletTitle(Connection con, HashMap<String, String> paramMap)
    throws Exception {

        String title = "";

        if (paramMap == null) {
            return title;
        }

        FilCommonBiz filBiz = new FilCommonBiz(con);

        //マップからパラメータを取得
        String paramFcbSid = paramMap.get(FilPtl020Biz.FILE_PORTLET_PARAM1);

        //キャビネット名
        title = filBiz.getCabinetName(NullDefault.getInt(paramFcbSid, 0), con);

        return title;
    }

}
