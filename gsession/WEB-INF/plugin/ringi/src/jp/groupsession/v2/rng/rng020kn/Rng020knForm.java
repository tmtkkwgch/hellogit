package jp.groupsession.v2.rng.rng020kn;

import java.util.List;

import jp.groupsession.v2.rng.model.RingiChannelDataModel;
import jp.groupsession.v2.rng.rng020.Rng020Form;

/**
 * <br>[機  能] 稟議作成確認画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng020knForm extends Rng020Form {

    /** 添付ファイルSID */
    private String rng020BinSid__ = "";

    /** 承認経路 ユーザ数 */
    private String rng020apprUserCnt__ = "";
    /** 最終確認 ユーザ数*/
    private String rng020confirmUserCnt__ = "";
    /** 経路情報一覧 */
    private List<RingiChannelDataModel> channelList__ = null;
    /** 経路情報一覧(確認) */
    private List<RingiChannelDataModel> confirmChannelList__ = null;
    /** 表示用内容 */
    private String rng020knContent__ = null;


    /**
     * <p>rng020knContent を取得します。
     * @return rng020knContent
     */
    public String getRng020knContent() {
        return rng020knContent__;
    }

    /**
     * <p>rng020knContent をセットします。
     * @param rng020knContent rng020knContent
     */
    public void setRng020knContent(String rng020knContent) {
        rng020knContent__ = rng020knContent;
    }

    /**
     * <p>rng020BinSid を取得します。
     * @return rng020BinSid
     */
    public String getRng020BinSid() {
        return rng020BinSid__;
    }

    /**
     * <p>rng020BinSid をセットします。
     * @param rng020BinSid rng020BinSid
     */
    public void setRng020BinSid(String rng020BinSid) {
        rng020BinSid__ = rng020BinSid;
    }

    /**
     * <p>rng020apprUserCnt を取得します。
     * @return rng020apprUserCnt
     */
    public String getRng020apprUserCnt() {
        return rng020apprUserCnt__;
    }

    /**
     * <p>rng020apprUserCnt をセットします。
     * @param rng020apprUserCnt rng020apprUserCnt
     */
    public void setRng020apprUserCnt(String rng020apprUserCnt) {
        rng020apprUserCnt__ = rng020apprUserCnt;
    }

    /**
     * <p>rng020confirmUserCnt を取得します。
     * @return rng020confirmUserCnt
     */
    public String getRng020confirmUserCnt() {
        return rng020confirmUserCnt__;
    }

    /**
     * <p>rng020confirmUserCnt をセットします。
     * @param rng020confirmUserCnt rng020confirmUserCnt
     */
    public void setRng020confirmUserCnt(String rng020confirmUserCnt) {
        rng020confirmUserCnt__ = rng020confirmUserCnt;
    }

    /**
     * <p>channelList を取得します。
     * @return channelList
     */
    public List<RingiChannelDataModel> getChannelList() {
        return channelList__;
    }

    /**
     * <p>channelList をセットします。
     * @param channelList channelList
     */
    public void setChannelList(List<RingiChannelDataModel> channelList) {
        channelList__ = channelList;
    }

    /**
     * <p>confirmChannelList を取得します。
     * @return confirmChannelList
     */
    public List<RingiChannelDataModel> getConfirmChannelList() {
        return confirmChannelList__;
    }

    /**
     * <p>confirmChannelList をセットします。
     * @param confirmChannelList confirmChannelList
     */
    public void setConfirmChannelList(List<RingiChannelDataModel> confirmChannelList) {
        confirmChannelList__ = confirmChannelList;
    }
}
