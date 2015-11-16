package jp.groupsession.v2.rsv.rsv091;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.rsv.rsv090.Rsv090Form;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 施設予約 施設・設備画像設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv091Form extends Rsv090Form {

    /** 添付ファイル(コンボで選択中) */
    private String[] rsv091selectFiles__ = null;
    /** ファイルコンボ */
    private List<LabelValueBean> rsv091FileLabelList__ = null;

    /**
     * <p>rsv091FileLabelList を取得します。
     * @return rsv091FileLabelList
     */
    public List<LabelValueBean> getRsv091FileLabelList() {
        return rsv091FileLabelList__;
    }
    /**
     * <p>rsv091FileLabelList をセットします。
     * @param rsv091FileLabelList rsv091FileLabelList
     */
    public void setRsv091FileLabelList(List<LabelValueBean> rsv091FileLabelList) {
        rsv091FileLabelList__ = rsv091FileLabelList;
    }
    /**
     * <p>rsv091selectFiles を取得します。
     * @return rsv091selectFiles
     */
    public String[] getRsv091selectFiles() {
        return rsv091selectFiles__;
    }
    /**
     * <p>rsv091selectFiles をセットします。
     * @param rsv091selectFiles rsv091selectFiles
     */
    public void setRsv091selectFiles(String[] rsv091selectFiles) {
        rsv091selectFiles__ = rsv091selectFiles;
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
        List<LabelValueBean> rsv091FileLabelList = commonBiz.getTempFileLabelList(tempDir);

        if (rsv091FileLabelList == null || rsv091FileLabelList.size() == 0) {
            return errors;
        }

        if (rsv091FileLabelList.size() > 10) {
            msg =
                new ActionMessage("error.overfile.number.sisetu",
                        gsMsg.getMessage(req, "reserve.src.13"),
                        GSConstReserve.MAX_NUMBER_SISETU_FILE);
            StrutsUtil.addMessage(errors, msg, "rsv091selectFiles");
        }

        return errors;
    }
}