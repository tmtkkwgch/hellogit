package jp.groupsession.v2.wml.wml011;

import java.util.List;

import jp.groupsession.v2.wml.model.base.WmlHeaderDataModel;
import jp.groupsession.v2.wml.wml010.Wml010ParamModel;

/**
 * <br>[機  能] WEBメール メールヘッダ情報(ポップアップ)画面のパラメータ情報を保持するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml011ParamModel extends Wml010ParamModel {

    /** ヘッダ情報 */
    private List<WmlHeaderDataModel> wml011MailHeaderData__ = null;

    /**
     * <p>wml011MailHeaderData を取得します。
     * @return wml011MailHeaderData
     */
    public List<WmlHeaderDataModel> getWml011MailHeaderData() {
        return wml011MailHeaderData__;
    }

    /**
     * <p>wml011MailHeaderData をセットします。
     * @param wml011MailHeaderData wml011MailHeaderData
     */
    public void setWml011MailHeaderData(
            List<WmlHeaderDataModel> wml011MailHeaderData) {
        wml011MailHeaderData__ = wml011MailHeaderData;
    }
}