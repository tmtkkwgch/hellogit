package jp.groupsession.v2.zsk.zsk100;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.sch.biz.SchCommonBiz;
import jp.groupsession.v2.sch.model.SchLabelValueModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.zsk.GSConstZaiseki;
import jp.groupsession.v2.zsk.dao.ZaiGpriConfDao;
import jp.groupsession.v2.zsk.maingrp.ZskMaingrpCommonBiz;
import jp.groupsession.v2.zsk.model.ZaiGpriConfModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 在席管理 個人設定 メイン画面メンバー表示設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Zsk100Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Zsk100Biz.class);
    /** リクエスモデル */
    public RequestModel reqMdl__ = null;

    /**
     * <p>コンストラクタ
     * @param reqMdl RequestModel
     */
    public Zsk100Biz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Zsk100ParamModel
     * @param umodel ユーザ基本情報モデル
     * @param con コネクション
     * @throws Exception SQL実行エラー
     */
    public void setInitData(Zsk100ParamModel paramMdl,
            BaseUserModel umodel, Connection con) throws Exception {

        log__.debug("setInitData START");
        GsMessage gsMsg = new GsMessage(reqMdl__);
        String name = gsMsg.getMessage("cmn.name");
        String number = gsMsg.getMessage("cmn.employee.staff.number");
        String post = gsMsg.getMessage("cmn.post");
        String birth = gsMsg.getMessage("cmn.birthday");
        String sortkey1 = gsMsg.getMessage("cmn.sortkey") + "1";
        String sortkey2 = gsMsg.getMessage("cmn.sortkey") + "2";

        String[] sortAllText = {name, number, post, birth,
                sortkey1, sortkey2};

        //ソートキーラベル
        ArrayList<LabelValueBean> sortLabel = new ArrayList<LabelValueBean>();
        for (int i = 0; i < sortAllText.length; i++) {
            String label = sortAllText[i];
            String value = Integer.toString(GSConstZaiseki.SORT_KEY_ALL[i]);
            sortLabel.add(new LabelValueBean(label, value));
        }
        paramMdl.setZsk100SortKeyLabel(sortLabel);

        //コンボ選択値取得
        ZskMaingrpCommonBiz biz = new ZskMaingrpCommonBiz();
        String groupCodeStr = biz.selectGroupSid(umodel.getUsrsid(), con);

        //コンボ選択値セット
        paramMdl.setZsk100DspGpSid(
                NullDefault.getString(paramMdl.getZsk100DspGpSid(), groupCodeStr));

        //コンボセット グループ
        SchCommonBiz scBiz = new SchCommonBiz(reqMdl__);
        List<SchLabelValueModel> groupLabel = scBiz.getGroupLabelForSchedule(
                umodel.getUsrsid(), con, false);
        paramMdl.setZsk100GpLabelList(groupLabel);

        //表示順・スケジュールデフォルト設定
        __setGpriInf(paramMdl, con, umodel.getUsrsid());
    }

    /**
     * <br>[機  能] 表示順の設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Zsk100ParamModel
     * @param con コネクション
     * @param usrSid ユーザSID
     * @throws Exception SQL実行エラー
     */
    public void __setGpriInf(
            Zsk100ParamModel paramMdl, Connection con, int usrSid) throws Exception {

        ZaiGpriConfDao dao = new ZaiGpriConfDao(con);
        ZaiGpriConfModel model = dao.select(usrSid);

        if (paramMdl.getZsk100maingrpDspFlg() == -1 && model != null) {
            paramMdl.setZsk100SortKey1(NullDefault.getInt(
                    String.valueOf(model.getZgcSortKey1()), GSConstZaiseki.SORT_KEY_NAME));
            paramMdl.setZsk100SortKey2(NullDefault.getInt(
                    String.valueOf(model.getZgcSortKey2()), GSConstZaiseki.SORT_KEY_NAME));
            paramMdl.setZsk100SortOrder1(NullDefault.getInt(
                    String.valueOf(model.getZgcSortOrder1()), GSConst.ORDER_KEY_ASC));
            paramMdl.setZsk100SortOrder2(NullDefault.getInt(
                    String.valueOf(model.getZgcSortOrder2()), GSConst.ORDER_KEY_ASC));
            paramMdl.setZsk100SchViewDf(NullDefault.getInt(
                    String.valueOf(model.getZgcSchViewDf()), GSConstZaiseki.MAIN_SCH_DSP));
            paramMdl.setZsk100maingrpDspFlg(NullDefault.getInt(
                    String.valueOf(model.getZgcViewKbn()), GSConstZaiseki.MAINGRP_DSP));

        } else if (paramMdl.getZsk100maingrpDspFlg() == -1 && model == null) {
            paramMdl.setZsk100SortKey1(GSConstZaiseki.SORT_KEY_NAME);
            paramMdl.setZsk100SortKey2(GSConstZaiseki.SORT_KEY_NAME);
            paramMdl.setZsk100SortOrder1(GSConst.ORDER_KEY_ASC);
            paramMdl.setZsk100SortOrder2(GSConst.ORDER_KEY_ASC);
            paramMdl.setZsk100SchViewDf(GSConstZaiseki.MAIN_SCH_DSP);
            paramMdl.setZsk100maingrpDspFlg(GSConstZaiseki.MAINGRP_DSP);
        }
    }
}
