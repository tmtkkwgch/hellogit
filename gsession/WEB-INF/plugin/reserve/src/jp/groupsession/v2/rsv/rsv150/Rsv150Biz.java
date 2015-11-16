package jp.groupsession.v2.rsv.rsv150;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rsv.AbstractReserveBiz;
import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.rsv.dao.RsvUserDao;
import jp.groupsession.v2.rsv.model.RsvUserModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 施設予約 個人設定 表示設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv150Biz extends AbstractReserveBiz {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rsv150Biz.class);

    /** コネクション */
    protected Connection con_ = null;
    /** リクエスト情報 */
    protected RequestModel reqMdl_ = null;
    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param con コネクション
     * @param reqMdl リクエスト情報
     */
    public Rsv150Biz(Connection con, RequestModel reqMdl) {
        con_ = con;
        reqMdl_ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示処理を行います
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Rsv150ParamModel
     * @param userSid ログインユーザSID
     * @throws SQLException 例外
     */
    public void initDsp(
            Rsv150ParamModel paramMdl, int userSid) throws SQLException {
        //施設グループコンボを取得
        paramMdl.setRsv150sisetuLabelList(_getGroupComboListDef(con_, reqMdl_));

        //自動リロード時間のコンボを取得
        paramMdl.setRsv150TimeLabelList(__getTimeLabel());

        //もし、初期表示フラグがoffならば、
        if (paramMdl.getRsv150initDspFlg() == 0) {
            //チェックボックスをチェック
            paramMdl.setRsv150initDspFlg(1);
            paramMdl.setRsv150DispItem1(String.valueOf(GSConstReserve.KOJN_SETTEI_DSP_OK));
            paramMdl.setRsv150DispItem2(String.valueOf(GSConstReserve.KOJN_SETTEI_DSP_OK));
            paramMdl.setRsv150ReloadTime(String.valueOf(GSConstReserve.AUTO_RELOAD_10MIN));

            RsvUserDao dao = new RsvUserDao(con_);
            RsvUserModel model = dao.select(userSid);
            //もし、モデルがNULLじゃなければ、
            if (model != null) {
                log__.debug("モデルから表示項目値を取得します");
                //モデルから表示項目値を取得
                paramMdl.setRsv150SelectedGrpSid(model.getRsgSid());
                paramMdl.setRsv150DispItem1(String.valueOf(model.getRsuDit1()));
                paramMdl.setRsv150DispItem2(String.valueOf(model.getRsuDit2()));

                //自動リロード時間
                paramMdl.setRsv150ReloadTime(String.valueOf(model.getRsuReload()));

                //施設画像表示区分
                paramMdl.setRsv150ImgDspKbn(model.getRsuImgDsp());

                //初期表示画面
                paramMdl.setRsv150DefDsp(model.getRsuIniDsp());
            }
        }
    }

    /**
     * <br>[機  能] 自動リロード時間コンボの表示を設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @return labelList ラベルリスト
     */
    private List <LabelValueBean> __getTimeLabel() {
        List <LabelValueBean> labelList = new ArrayList <LabelValueBean>();
        GsMessage gsMsg = new GsMessage(reqMdl_);
        String strMinute = gsMsg.getMessage("cmn.minute");
        labelList.add(new LabelValueBean(gsMsg.getMessage("cmn.1minute"), "60000"));
        labelList.add(new LabelValueBean("3" + strMinute, "180000"));
        labelList.add(new LabelValueBean("5" + strMinute, "300000"));
        labelList.add(new LabelValueBean("10" + strMinute, "600000"));
        labelList.add(new LabelValueBean("20" + strMinute, "1200000"));
        labelList.add(new LabelValueBean("30" + strMinute, "1800000"));
        labelList.add(new LabelValueBean("40" + strMinute, "2400000"));
        labelList.add(new LabelValueBean("50" + strMinute, "3000000"));
        labelList.add(new LabelValueBean("60" + strMinute, "3600000"));
        labelList.add(new LabelValueBean(gsMsg.getMessage("cmn.without.reloading"), "0"));
        return labelList;
    }
}
