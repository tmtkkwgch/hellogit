package jp.groupsession.v2.rsv.rsv160kn;

import java.sql.Connection;
import java.sql.SQLException;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rsv.AbstractReserveBiz;
import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.rsv.dao.RsvUserDao;
import jp.groupsession.v2.rsv.model.RsvUserModel;
import jp.groupsession.v2.rsv.rsv150.Rsv150Biz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 施設予約 個人設定 日間表示時間帯設定確認画面のビジネスロジックくらす
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv160knBiz extends AbstractReserveBiz {
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
     * @param reqMdl RequestModel
     */
    public Rsv160knBiz(Connection con, RequestModel reqMdl) {
        con_ = con;
        reqMdl_ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示処理
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Rsv160knParamModel
     */
    public void initDsp(Rsv160knParamModel paramMdl) {
        //From
        String from = NullDefault.getString(paramMdl.getRsv160SelectedFromSid(), "9");
        paramMdl.setRsv160knDspFrom(StringUtil.toDecFormat(from, "00"));
        //To
        String to = NullDefault.getString(paramMdl.getRsv160SelectedToSid(), "18");
        paramMdl.setRsv160knDspTo(StringUtil.toDecFormat(to, "00"));
    }

    /**
     * <br>[機  能] DB登録処理を行います
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Rsv160knParamModel
     * @param userSid ログインユーザSID
     * @throws SQLException 例外
     */
    public void registDate(Rsv160knParamModel paramMdl, int userSid) throws SQLException {
        RsvUserModel model = new RsvUserModel();

        //モデルを取得
        model = getModel(model, paramMdl, userSid);
        //更新処理
        int count = updatePrivate(model, paramMdl, userSid);
        //更新件数が0なら新規登録を行う
        if (count <= 0) {
            log__.debug("新規登録を行います。count = " + count);
            insertPrivate(model, paramMdl, userSid);
        }
    }
    /**
     * <br>[機  能] 個人設定モデルを取得します
     * <br>[解  説]
     * <br>[備  考]
     * @param model RsvUserModel model, Rsv150knForm paramMdl, int userSid
     * @param paramMdl Rsv160knParamModel
     * @param userSid ログインユーザSID
     * @return model
     */
    private RsvUserModel getModel(RsvUserModel model, Rsv160knParamModel paramMdl, int userSid) {
        UDate now = new UDate();
        model.setUsrSid(userSid);
        model.setRsgSid(0);
        model.setRsuDit1(GSConstReserve.KOJN_SETTEI_DSP_OK);
        model.setRsuDit2(GSConstReserve.KOJN_SETTEI_DSP_OK);
        model.setRsuMaxDsp(GSConstReserve.RSV_DEFAULT_DSP);
        model.setRsuDtmFr(Integer.parseInt(paramMdl.getRsv160SelectedFromSid()));
        model.setRsuDtmTo(Integer.parseInt(paramMdl.getRsv160SelectedToSid()));
        UDate frDate = new UDate();
        frDate.setZeroHhMmSs();
        frDate.setHour(GSConstReserve.YRK_DEFAULT_START_HOUR);
        frDate.setMinute(GSConstReserve.YRK_DEFAULT_START_MINUTE);
        model.setRsuIniFrDate(frDate);
        UDate toDate = new UDate();
        toDate.setZeroHhMmSs();
        toDate.setHour(GSConstReserve.YRK_DEFAULT_END_HOUR);
        toDate.setMinute(GSConstReserve.YRK_DEFAULT_END_MINUTE);
        model.setRsuIniToDate(toDate);
        model.setRsuIniEdit(GSConstReserve.EDIT_AUTH_NONE);
        model.setRsuAuid(userSid);
        model.setRsuAdate(now);
        model.setRsuEuid(userSid);
        model.setRsuEdate(now);
        return model;
    }

    /**
     * <br>[機  能] 個人設定を更新します
     * <br>[解  説]
     * <br>[備  考]
     * @param model RsvUserModel
     * @param paramMdl Rsv160knParamModel
     * @param userSid ログインユーザSID
     * @return int 更新件数
     * @throws SQLException 例外
     */
    private int updatePrivate(
            RsvUserModel model, Rsv160knParamModel paramMdl, int userSid) throws SQLException {
        RsvUserDao dao = new RsvUserDao(con_);
        return dao.update(model, true);
    }
    /**
     * <br>[機  能] 個人設定を追加します
     * <br>[解  説]
     * <br>[備  考]
     * @param model RsvUserModel
     * @param paramMdl Rsv160knParamModel
     * @param userSid ログインユーザSID
     * @throws SQLException 例外
     */
    private void insertPrivate(
            RsvUserModel model, Rsv160knParamModel paramMdl, int userSid) throws SQLException {
        RsvUserDao dao = new RsvUserDao(con_);
        dao.insert(model);
    }
}
