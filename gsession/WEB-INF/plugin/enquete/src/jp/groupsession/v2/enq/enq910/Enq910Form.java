package jp.groupsession.v2.enq.enq910;

import java.util.ArrayList;

import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.enq.GSConstEnquete;
import jp.groupsession.v2.enq.enq900.Enq900Form;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 管理者設定 アンケート発信対象者設定画面のアクションフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Enq910Form extends Enq900Form {

    /** 初期表示フラグ */
    private int enq910initFlg__ = 0;
    /** アンケート作成対象者区分 */
    private int enq910TaisyoKbn__ = 0;

    /** アンケート作成可能ユーザリスト */
    private String[] enq910MakeSenderList__ = null;

    /** グループSID */
    private String enq910GroupSid__ = null;
    /** グループ一覧 */
    private ArrayList<LabelValueBean> enq910GroupLabel__ = null;

    /** アンケート発信対象者リスト（左） */
    private ArrayList<LabelValueBean> enq910AddSenderLabel__ = null;
    /** アンケート発信対象者リスト（右） */
    private ArrayList<LabelValueBean> enq910BelongSenderLabel__ = null;
    /** アンケート発信対象者 選択SID （左） */
    private String[] enq910SelectAddSenderSid__ = null;
    /** アンケート非発信対象者 選択SID （右） */
    private String[] enq910SelectBelongSenderSid__ = null;

    /**
     * <p>enq910initFlg を取得します。
     * @return enq910initFlg
     */
    public int getEnq910initFlg() {
        return enq910initFlg__;
    }
    /**
     * <p>enq910initFlg をセットします。
     * @param enq910initFlg enq910initFlg
     */
    public void setEnq910initFlg(int enq910initFlg) {
        enq910initFlg__ = enq910initFlg;
    }
    /**
     * <p>enq910TaisyoKbn を取得します。
     * @return enq910TaisyoKbn
     */
    public int getEnq910TaisyoKbn() {
        return enq910TaisyoKbn__;
    }
    /**
     * <p>enq910TaisyoKbn をセットします。
     * @param enq910TaisyoKbn enq910TaisyoKbn
     */
    public void setEnq910TaisyoKbn(int enq910TaisyoKbn) {
        enq910TaisyoKbn__ = enq910TaisyoKbn;
    }
    /**
     * <p>アンケート作成可能ユーザリスト を取得します。
     * @return enq910MakeSenderList
     */
    public String[] getEnq910MakeSenderList() {
        return enq910MakeSenderList__;
    }
    /**
     * <p>アンケート作成可能ユーザリスト をセットします。
     * @param enq910MakeSenderList アンケート作成可能ユーザリスト
     */
    public void setEnq910MakeSenderList(String[] enq910MakeSenderList) {
        enq910MakeSenderList__ = enq910MakeSenderList;
    }
    /**
     * <p>グループSID を取得します。
     * @return enq910GroupSid
     */
    public String getEnq910GroupSid() {
        return enq910GroupSid__;
    }
    /**
     * <p>グループSID をセットします。
     * @param enq910GroupSid グループSID
     */
    public void setEnq910GroupSid(String enq910GroupSid) {
        enq910GroupSid__ = enq910GroupSid;
    }
    /**
     * <p>グループ一覧 を取得します。
     * @return enq910GroupLabel
     */
    public ArrayList<LabelValueBean> getEnq910GroupLabel() {
        return enq910GroupLabel__;
    }
    /**
     * <p>グループ一覧 をセットします。
     * @param enq910GroupLabel グループ一覧
     */
    public void setEnq910GroupLabel(ArrayList<LabelValueBean> enq910GroupLabel) {
        enq910GroupLabel__ = enq910GroupLabel;
    }
    /**
     * <p>アンケート発信対象者リスト（左） を取得します。
     * @return アンケート発信対象者リスト（左）
     */
    public ArrayList<LabelValueBean> getEnq910AddSenderLabel() {
        return enq910AddSenderLabel__;
    }
    /**
     * <p>アンケート発信対象者リスト（左） をセットします。
     * @param enq910AddSenderLabel アンケート発信対象者リスト（左）
     */
    public void setEnq910AddSenderLabel(
            ArrayList<LabelValueBean> enq910AddSenderLabel) {
        enq910AddSenderLabel__ = enq910AddSenderLabel;
    }
    /**
     * <p>アンケート発信対象者リスト（右） を取得します。
     * @return アンケート発信対象者リスト（右）
     */
    public ArrayList<LabelValueBean> getEnq910BelongSenderLabel() {
        return enq910BelongSenderLabel__;
    }
    /**
     * <p>アンケート発信対象者リスト（右） をセットします。
     * @param enq910BelongSenderLabel アンケート発信対象者リスト（右）
     */
    public void setEnq910BelongSenderLabel(
            ArrayList<LabelValueBean> enq910BelongSenderLabel) {
        enq910BelongSenderLabel__ = enq910BelongSenderLabel;
    }
    /**
     * <p>アンケート発信対象者 選択SID （左） を取得します。
     * @return アンケート発信対象者 選択SID （左）
     */
    public String[] getEnq910SelectAddSenderSid() {
        return enq910SelectAddSenderSid__;
    }
    /**
     * <p>アンケート発信対象者 選択SID （左） をセットします。
     * @param enq910SelectAddSenderSid アンケート発信対象者 選択SID （左）
     */
    public void setEnq910SelectAddSenderSid(String[] enq910SelectAddSenderSid) {
        enq910SelectAddSenderSid__ = enq910SelectAddSenderSid;
    }
    /**
     * <p>アンケート非発信対象者 選択SID （右） を取得します。
     * @return アンケート非発信対象者 選択SID （右）
     */
    public String[] getEnq910SelectBelongSenderSid() {
        return enq910SelectBelongSenderSid__;
    }
    /**
     * <p>アンケート非発信対象者 選択SID （右） をセットします。
     * @param enq910SelectBelongSenderSid アンケート非発信対象者 選択SID （右）
     */
    public void setEnq910SelectBelongSenderSid(String[] enq910SelectBelongSenderSid) {
        enq910SelectBelongSenderSid__ = enq910SelectBelongSenderSid;
    }

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエストモデル
     * @return エラー
     */
    public ActionErrors validateEnq910(RequestModel reqMdl) {

        ActionErrors errors = new ActionErrors();

        // アンケート発信対象者
        validateMakeSender(errors, reqMdl);

        return errors;
    }

    /**
     * <br>[機  能] アンケート発信対象者の入力チェック
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param reqMdl リクエストモデル
     * @return 入力チェック結果 true:正常、false:不正
     */
    public boolean validateMakeSender(ActionErrors errors, RequestModel reqMdl) {

        GsMessage gsMsg = new GsMessage();

        if (enq910TaisyoKbn__ == 0) {
            if ((enq910MakeSenderList__ == null || enq910MakeSenderList__.length < 1)) {
                String msgKey = "error.select.required.text";
                ActionMessage msg = new ActionMessage(msgKey, gsMsg.getMessage("enq.make.user"));
                StrutsUtil.addMessage(errors, msg, "enq910MakeSenderList." + msgKey);
                return false;
            }
        }
        return true;
    }

    /**
     * <br>[機  能] アンケート作成対象者更新時のオペレーションログ内容を取得
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエストモデル
     * @return [対象者区分]制限あり or 制限なし
     */
    public String getTargetLog(RequestModel reqMdl) {

        GsMessage gsMsg = new GsMessage(reqMdl);
        String ret = "[" + gsMsg.getMessage("enq.enq910.01") + "]";
        if (enq910TaisyoKbn__ == GSConstEnquete.CONF_TAISYO_LIMIT) {
            ret += gsMsg.getMessage("enq.enq910.02");
        } else {
            ret += gsMsg.getMessage("enq.enq910.03");
        }

        return ret;
    }
}
