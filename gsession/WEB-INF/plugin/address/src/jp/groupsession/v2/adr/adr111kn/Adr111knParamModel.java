package jp.groupsession.v2.adr.adr111kn;

import jp.groupsession.v2.adr.GSConstAddress;
import jp.groupsession.v2.adr.adr111.Adr111ParamModel;

/**
 * <br>[機  能] アドレス帳 拠点登録確認画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Adr111knParamModel extends Adr111ParamModel {

    /** 都道府県名称 */
    String adr111knAbaTdfkName__ = null;
    /** 備考(表示用) */
    String adr111knViewBiko__ = null;

    /**
     * <p>adr111knAbaTdfkName を取得します。
     * @return adr111knAbaTdfkName
     */
    public String getAdr111knAbaTdfkName() {
        return adr111knAbaTdfkName__;
    }

    /**
     * <p>adr111knAbaTdfkName をセットします。
     * @param adr111knAbaTdfkName adr111knAbaTdfkName
     */
    public void setAdr111knAbaTdfkName(String adr111knAbaTdfkName) {
        adr111knAbaTdfkName__ = adr111knAbaTdfkName;
    }

    /**
     * <p>adr111knViewBiko を取得します。
     * @return adr111knViewBiko
     */
    public String getAdr111knViewBiko() {
        return adr111knViewBiko__;
    }

    /**
     * <p>adr111knViewBiko をセットします。
     * @param adr111knViewBiko adr111knViewBiko
     */
    public void setAdr111knViewBiko(String adr111knViewBiko) {
        adr111knViewBiko__ = adr111knViewBiko;
    }

    /**
     * <br>[機  能] 会社拠点 種別の名称を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @return 会社拠点種別名称
     */
    public String getAdr111knAbaTypeName() {
        String abaTypeName = null;
        switch (this.getAdr111abaType()) {
            case GSConstAddress.ABATYPE_HEADOFFICE :
                //本社
                abaTypeName = "address.122";
                break;
            case GSConstAddress.ABATYPE_BRANCH :
                //支店
                abaTypeName = "address.123";
                break;
            case GSConstAddress.ABATYPE_BUSINESSOFFICE :
                //営業所
                abaTypeName = "address.124";
                break;
            default :
        }

        return abaTypeName;
    }

}
