package jp.groupsession.v2.ntp.ntp093;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.ntp.GSConstNippou;
import jp.groupsession.v2.ntp.biz.NtpCommonBiz;
import jp.groupsession.v2.ntp.dao.NtpPriConfDao;
import jp.groupsession.v2.ntp.model.NtpLabelValueModel;
import jp.groupsession.v2.ntp.model.NtpPriConfModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 日報 グループメンバー表示設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ntp093Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Ntp093Biz.class);
    /** DBコネクション */
    public Connection con__ = null;
    /** リクエスモデル */
    public RequestModel reqMdl__ = null;
    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param con Connection
     * @param reqMdl RequestModel
     */
    public Ntp093Biz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Ntp093ParamModel
     * @param umodel ユーザ基本情報モデル
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setInitData(Ntp093ParamModel paramMdl,
            BaseUserModel umodel, Connection con) throws SQLException {
        //DBより設定情報を取得。なければデフォルト値とする。
        NtpCommonBiz biz = new NtpCommonBiz(con__, reqMdl__);
        NtpPriConfModel pconf = biz.getNtpPriConfModel(con, umodel.getUsrsid());

        log__.debug("paramMdl = " + paramMdl);
        //ソート1
        paramMdl.setNtp093SortKey1(pconf.getNprSortKey1());
        paramMdl.setNtp093SortOrder1(pconf.getNprSortOrder1());
        //ソート2
        paramMdl.setNtp093SortKey2(pconf.getNprSortKey2());
        paramMdl.setNtp093SortOrder2(pconf.getNprSortOrder2());

        //デフォルトグループ
        if (pconf.getNprDspMygroup() != 0) {
            paramMdl.setNtp093DefGroup(GSConstNippou.MY_GROUP_STRING
                    + String.valueOf(pconf.getNprDspMygroup()));
        } else {
            paramMdl.setNtp093DefGroup(String.valueOf(pconf.getNprDspGroup()));
        }

        //表示情報をFormにセットする
        setDspData(paramMdl, con, umodel.getUsrsid());
    }

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Ntp093ParamModel
     * @param umodel ユーザ基本情報モデル
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setReloadData(Ntp093ParamModel paramMdl,
            BaseUserModel umodel, Connection con) throws SQLException {
        //表示情報をFormにセットする
        setDspData(paramMdl, con, umodel.getUsrsid());
    }

    /**
     * <br>[機  能] 表示情報をFormにセットする
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Ntp093ParamModel
     * @param con コネクション
     * @param userSid ユーザSID
     * @throws SQLException SQL実行エラー
     */
    public void setDspData(
            Ntp093ParamModel paramMdl, Connection con, int userSid) throws SQLException {

        //グループラベルの作成
        NtpCommonBiz cBiz = new NtpCommonBiz(con__, reqMdl__);
        List<NtpLabelValueModel> glabel = cBiz.getGroupLabelForNippou(userSid, con, false);
        paramMdl.setNtp093GroupLabel(glabel);

        //グループ存在チェック
        GroupBiz gpBiz = new GroupBiz();
        int gsid = gpBiz.getDefaultGroupSid(userSid, con);
        boolean gpFlg = false;
        for (NtpLabelValueModel lbvMdl : glabel) {
            if (lbvMdl.getValue().equals(paramMdl.getNtp093DefGroup())) {
                gpFlg = true;
            }
        }

        if (!gpFlg) {
            paramMdl.setNtp093DefGroup(String.valueOf(gsid));
        }
    }

    /**
     * <br>[機  能] 設定された個人設定情報をDBに保存する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Ntp093ParamModel
     * @param umodel ユーザ基本情報モデル
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setPconfSetting(Ntp093ParamModel paramMdl,
            BaseUserModel umodel, Connection con) throws SQLException {

        //DBより設定情報を取得。なければデフォルト値とする。
        NtpCommonBiz biz = new NtpCommonBiz(con__, reqMdl__);
        NtpPriConfModel pconf = biz.getNtpPriConfModel(con, umodel.getUsrsid());

        //ソート1
        pconf.setNprSortKey1(paramMdl.getNtp093SortKey1());
        pconf.setNprSortOrder1(paramMdl.getNtp093SortOrder1());
        //ソート2
        pconf.setNprSortKey2(paramMdl.getNtp093SortKey2());
        pconf.setNprSortOrder2(paramMdl.getNtp093SortOrder2());
        //デフォルトグループ

        if (NtpCommonBiz.isMyGroupSid(paramMdl.getNtp093DefGroup())) {
            //マイグループをデフォルト表示とした場合
            GroupBiz gpBiz = new GroupBiz();
            int gsid = gpBiz.getDefaultGroupSid(umodel.getUsrsid(), con);
            pconf.setNprDspGroup(gsid);
            pconf.setNprDspMygroup(NtpCommonBiz.getDspGroupSid(paramMdl.getNtp093DefGroup()));
        } else {
            pconf.setNprDspGroup(Integer.parseInt(paramMdl.getNtp093DefGroup()));
            pconf.setNprDspMygroup(0);
        }

        pconf.setNprEuid(umodel.getUsrsid());
        pconf.setNprEdate(new UDate());

        boolean commitFlg = false;
        try {
            NtpPriConfDao dao = new NtpPriConfDao(con);
            int count = dao.updateGroupDisp(pconf);
            if (count <= 0) {
                //レコードがない場合は作成
                dao.insert(pconf);
            }
            commitFlg = true;
        } catch (SQLException e) {
            log__.error("", e);
            throw e;
        } finally {
            if (commitFlg) {
                con.commit();
            }
        }
    }
}
