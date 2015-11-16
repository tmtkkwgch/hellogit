package jp.groupsession.v2.sml.sml190;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.sml.GSConstSmail;
import jp.groupsession.v2.sml.biz.SmlCommonBiz;
import jp.groupsession.v2.sml.dao.SmlUserDao;
import jp.groupsession.v2.sml.model.SmlUserModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] ショートメール 個人設定 メイン表示設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sml190Biz {

    /**
     * <br>[機  能] 初期表示情報を画面にセットする
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @throws SQLException SQL実行例外
     */
    public void setInitData(RequestModel reqMdl, Sml190ParamModel paramMdl, Connection con)
        throws SQLException {

        //セッション情報を取得
        BaseUserModel usModel = reqMdl.getSmodel();
        int sessionUsrSid = usModel.getUsrsid();

        //メイン表示設定を取得
        SmlCommonBiz smlCmnBiz = new SmlCommonBiz(reqMdl);
        SmlUserModel result = smlCmnBiz.getSmailUserConf(sessionUsrSid, con);

        paramMdl.setSml190kidokuKbn(Integer.toString(result.getSmlMainKbn()));
        paramMdl.setSml190mainDsp(Integer.toString(result.getSmlMainCnt()));
        paramMdl.setSml190mainSort(Integer.toString(result.getSmlMainSort()));

        //コンボ作成
        ArrayList<LabelValueBean> dspList = new ArrayList<LabelValueBean>();
        dspList.add(new LabelValueBean("5", "5"));
        for (int i = 10; i <= 50; i = i + 10) {
            dspList.add(new LabelValueBean(Integer.toString(i),
                                           Integer.toString(i)));
        }

        paramMdl.setSml190mainDspList(dspList);
    }

    /**
     * <br>[機  能] メイン表示設定を更新する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @throws SQLException SQL実行例外
     */
    public void updateMainDsp(Sml190ParamModel paramMdl, Connection con, RequestModel reqMdl)
        throws SQLException {

        //セッション情報を取得
        BaseUserModel usModel = reqMdl.getSmodel();
        int sessionUsrSid = usModel.getUsrsid();

        //入力項目をセット
        SmlUserModel result = new SmlUserModel();

        result.setSmlMainKbn(NullDefault.getInt(
                paramMdl.getSml190kidokuKbn(), GSConstSmail.SML_MAIN_KBN_MIDOKU));
        result.setSmlMainCnt(NullDefault.getInt(
                paramMdl.getSml190mainDsp(), GSConstSmail.SML_MAIN_CNT_10));
        result.setSmlMainSort(NullDefault.getInt(
                paramMdl.getSml190mainSort(), GSConstSmail.SML_MAIN_SORT_KOUJYUN));

        SmlUserDao dao = new SmlUserDao(con);
        SmlUserModel smlMdl = new SmlUserModel();
        smlMdl = dao.getSmlUserInfo(sessionUsrSid);
        if (smlMdl == null) {
            UDate nowDate = new UDate();
            SmlUserModel insertMdl = new SmlUserModel();
            insertMdl.setUsrSid(sessionUsrSid);
            insertMdl.setSmlMaxDsp(GSConstSmail.MAX_RECORD_COUNT);
            insertMdl.setSmlReload(GSConstSmail.MAIL_RELOAD_10MIN);
            insertMdl.setSmlAuid(sessionUsrSid);
            insertMdl.setSmlAdate(nowDate);
            insertMdl.setSmlEuid(sessionUsrSid);
            insertMdl.setSmlEdate(nowDate);
            insertMdl.setSmlMainKbn(0);
            insertMdl.setSmlMainCnt(10);
            insertMdl.setSmlMainSort(0);
            dao.insertSmlUser(insertMdl);
        } else {
            dao.updateMainDsp(result, sessionUsrSid);
        }
    }

    /**
     * <br>[機  能]オペレーションログ出力用設定内容を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param paramMdl パラメータ格納モデル
     * @return オペレーションログ表示内容
     *
     */
    public String getOpLog(RequestModel reqMdl, Sml190ParamModel paramMdl) {

        GsMessage gsMsg = new GsMessage(reqMdl);
        StringBuilder opLog = new StringBuilder();
        //表示
        String display = gsMsg.getMessage("api.cmn.view");
        //非表示
        String nodisplay = gsMsg.getMessage("cmn.hide");
        //*メイン表示件数*//
        opLog.append(__opLogValue(gsMsg.getMessage("sml.sml190.02")));
        opLog.append(paramMdl.getSml190mainDsp());
        opLog.append(gsMsg.getMessage("cmn.number"));
        opLog.append("\n");
        //*既読メール*//
        int picture = NullDefault.getInt(paramMdl.getSml190kidokuKbn(), 0);
        opLog.append(__opLogValue(gsMsg.getMessage("sml.sml190.03")));
        if (picture == GSConstCommon.MAIN_NOT_DSP) {
            opLog.append(nodisplay);
        } else if (picture == GSConstCommon.MAIN_DSP) {
            opLog.append(display);
        }
        opLog.append("\n");
        //*表示順*//
        int value = NullDefault.getInt(paramMdl.getSml190mainSort(), 0);
        opLog.append(__opLogValue(gsMsg.getMessage("cmn.sort")));
        if (value == GSConstSmail.ORDER_ASC) {
            opLog.append(gsMsg.getMessage("cmn.order.desc"));
        } else if (value == GSConstSmail.ORDER_DESC) {
            opLog.append(gsMsg.getMessage("cmn.order.asc"));
        }
        return opLog.toString();
    }
    /**
     * <br>[機  能]オペレーションログ出力項目まとめロジック
     * <br>[解  説]
     * <br>[備  考]
     * @param value 設定項目
     * @return [設定項目名]
     */
    private String __opLogValue(String value) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(value);
        sb.append("] ");
        return sb.toString();
    }
}