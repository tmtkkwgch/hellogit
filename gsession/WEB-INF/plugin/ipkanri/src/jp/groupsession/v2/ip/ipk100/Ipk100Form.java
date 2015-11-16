package jp.groupsession.v2.ip.ipk100;

import java.util.ArrayList;

import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.ip.IpkConst;
import jp.groupsession.v2.ip.IpkValidate;
import jp.groupsession.v2.ip.ipk090.Ipk090Form;
import jp.groupsession.v2.ip.model.IpkSpecMstModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;

/**
 * <br>[機  能] IP管理 スペックマスタメンテナンス 登録画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 * @author JTS
 */
public class Ipk100Form extends Ipk090Form {

    /** 名前 */
    private String ipk100name__;
    /** 備考 */
    private String ipk100biko__;
    /** 表示順ラジオ */
    private String ipk100specLevel__;
    /** スペック一覧モデル */
    private ArrayList<IpkSpecMstModel> ipk100specMstModelList__;
    /** スクロール位置 */
    private String ipk100scroll__ = "1";
    /** 表示件数存在フラグ */
    private boolean existFlg__ = false;
    /** 表示順ラジオ(保持用) */
    private String ipk100svSpecLevel__ = "syokiti";
    /** ヘルプモード */
    private String ipk100helpMode__;

    /**
     * <p>ipk100specLevel を取得します。
     * @return ipk100specLevel
     */
    public String getIpk100specLevel() {
        return ipk100specLevel__;
    }

    /**
     * <p>ipk100specLevel をセットします。
     * @param ipk100specLevel ipk100specLevel
     */
    public void setIpk100specLevel(String ipk100specLevel) {
        ipk100specLevel__ = ipk100specLevel;
    }

    /**
     * <p>ipk100biko を取得します。
     * @return ipk100biko
     */
    public String getIpk100biko() {
        return ipk100biko__;
    }

    /**
     * <p>ipk100biko をセットします。
     * @param ipk100biko ipk100biko
     */
    public void setIpk100biko(String ipk100biko) {
        ipk100biko__ = ipk100biko;
    }

    /**
     * <p>ipk100name を取得します。
     * @return ipk100name
     */
    public String getIpk100name() {
        return ipk100name__;
    }

    /**
     * <p>ipk100name をセットします。
     * @param ipk100name ipk100name
     */
    public void setIpk100name(String ipk100name) {
        ipk100name__ = ipk100name;
    }

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @return エラー
     */
    public ActionErrors validateCheck(RequestModel reqMdl) {

        GsMessage gsMsg = new GsMessage(reqMdl);
        String textName = gsMsg.getMessage("cmn.name4");
        String textLevel = gsMsg.getMessage("cmn.sort");
        String textBiko = gsMsg.getMessage("cmn.memo");

        ActionErrors errors = new ActionErrors();
        //-- 名前 --
        errors = IpkValidate.validateCmnFieldText(
                                                errors,
                                                textName,
                                                ipk100name__,
                                                "ipk100name",
                                                IpkConst.MAX_LENGTH_SPECM_NAME,
                                                true);

        if (existFlg__ == true) {
            //-- 表示順 --
            errors = IpkValidate.validateCmnintFieldText(
                                                errors,
                                                textLevel,
                                                ipk100specLevel__,
                                                "ipk100specLevel",
                                                IpkConst.MAX_LENGTH_SPECM_LEVEL,
                                                true);
        }

        //-- 備考 --
        errors = IpkValidate.validateFieldTextArea(
                                                errors,
                                                textBiko,
                                                ipk100biko__,
                                                "ipk100biko",
                                                IpkConst.MAX_LENGTH_SPECM_BIKO,
                                                true);
        return errors;
    }

    /**
     * <p>ipk100specMstModelList を取得します。
     * @return ipk100specMstModelList
     */
    public ArrayList<IpkSpecMstModel> getIpk100specMstModelList() {
        return ipk100specMstModelList__;
    }

    /**
     * <p>ipk100specMstModelList をセットします。
     * @param ipk100specMstModelList ipk100specMstModelList
     */
    public void setIpk100specMstModelList(
            ArrayList<IpkSpecMstModel> ipk100specMstModelList) {
        ipk100specMstModelList__ = ipk100specMstModelList;
    }

    /**
     * <p>ipk100scroll を取得します。
     * @return ipk100scroll
     */
    public String getIpk100scroll() {
        return ipk100scroll__;
    }

    /**
     * <p>ipk100scroll をセットします。
     * @param ipk100scroll ipk100scroll
     */
    public void setIpk100scroll(String ipk100scroll) {
        ipk100scroll__ = ipk100scroll;
    }

    /**
     * <p>existFlg を取得します。
     * @return existFlg
     */
    public boolean isExistFlg() {
        return existFlg__;
    }

    /**
     * <p>existFlg をセットします。
     * @param existFlg existFlg
     */
    public void setExistFlg(boolean existFlg) {
        existFlg__ = existFlg;
    }

    /**
     * <p>ipk100svSpecLevel を取得します。
     * @return ipk100svSpecLevel
     */
    public String getIpk100svSpecLevel() {
        return ipk100svSpecLevel__;
    }

    /**
     * <p>ipk100svSpecLevel をセットします。
     * @param ipk100svSpecLevel ipk100svSpecLevel
     */
    public void setIpk100svSpecLevel(String ipk100svSpecLevel) {
        ipk100svSpecLevel__ = ipk100svSpecLevel;
    }

    /**
     * <p>ipk100helpMode を取得します。
     * @return ipk100helpMode
     */
    public String getIpk100helpMode() {
        return ipk100helpMode__;
    }

    /**
     * <p>ipk100helpMode をセットします。
     * @param ipk100helpMode ipk100helpMode
     */
    public void setIpk100helpMode(String ipk100helpMode) {
        ipk100helpMode__ = ipk100helpMode;
    }
}