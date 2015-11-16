package jp.groupsession.v2.rsv.rsv092;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.rsv.rsv090.Rsv090ParamModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 施設予約 場所・地図画像設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv092ParamModel extends Rsv090ParamModel {

    /** 添付ファイル(コンボで選択中) */
    private String[] rsv092selectFiles__ = null;
    /** ファイルコンボ */
    private List<LabelValueBean> rsv092FileLabelList__ = null;
    /** 削除実行フラグ 0:未実行 1:実行済 */
    private int rsv092DelExeFlg__;

    /**
     * <p>rsv092DelExeFlg を取得します。
     * @return rsv092DelExeFlg
     */
    public int getRsv092DelExeFlg() {
        return rsv092DelExeFlg__;
    }
    /**
     * <p>rsv092DelExeFlg をセットします。
     * @param rsv092DelExeFlg rsv092DelExeFlg
     */
    public void setRsv092DelExeFlg(int rsv092DelExeFlg) {
        rsv092DelExeFlg__ = rsv092DelExeFlg;
    }
    /**
     * <p>rsv092FileLabelList を取得します。
     * @return rsv092FileLabelList
     */
    public List<LabelValueBean> getRsv092FileLabelList() {
        return rsv092FileLabelList__;
    }
    /**
     * <p>rsv092FileLabelList をセットします。
     * @param rsv092FileLabelList rsv092FileLabelList
     */
    public void setRsv092FileLabelList(List<LabelValueBean> rsv092FileLabelList) {
        rsv092FileLabelList__ = rsv092FileLabelList;
    }
    /**
     * <p>rsv092selectFiles を取得します。
     * @return rsv092selectFiles
     */
    public String[] getRsv092selectFiles() {
        return rsv092selectFiles__;
    }
    /**
     * <p>rsv092selectFiles をセットします。
     * @param rsv092selectFiles rsv092selectFiles
     */
    public void setRsv092selectFiles(String[] rsv092selectFiles) {
        rsv092selectFiles__ = rsv092selectFiles;
    }

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param tempDir テンポラリディレクトリパス
     * @param req リクエスト
     * @return errors エラー
     * @throws Exception 実行例外
     */
    public ActionErrors validateCheck(String tempDir,
                                      HttpServletRequest req) throws Exception {

        ActionErrors errors = new ActionErrors();
        GsMessage gsMsg = new GsMessage();
        ActionMessage msg = null;

        CommonBiz commonBiz = new CommonBiz();
        List<LabelValueBean> rsv092FileLabelList = commonBiz.getTempFileLabelList(tempDir);

        if (rsv092FileLabelList == null && rsv092FileLabelList.size() == 0) {
            return errors;
        }

        if (rsv092FileLabelList.size() > 10) {
            msg =
                new ActionMessage("error.overfile.number.sisetu",
                        gsMsg.getMessage(req, "reserve.src.14"),
                        GSConstReserve.MAX_NUMBER_SISETU_FILE);
            StrutsUtil.addMessage(errors, msg, "rsv092selectFiles");
        }

        return errors;
    }
}