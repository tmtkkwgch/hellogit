package jp.groupsession.v2.cir.cir170;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cir.GSConstCircular;
import jp.groupsession.v2.cir.GSValidateCircular;
import jp.groupsession.v2.cir.biz.CirCommonBiz;
import jp.groupsession.v2.cir.cir150.Cir150Form;
import jp.groupsession.v2.cmn.cmn110.Cmn110FileModel;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 回覧板 アカウントインポート画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cir170Form extends Cir150Form {

    /** プラグインID */
    private String cir170pluginId__ = GSConstCircular.PLUGIN_ID_CIRCULAR;

    /** 添付ファイル(コンボで選択中) */
    private String[] cir170selectFiles__ = null;
    /** ファイルコンボ */
    private  List<LabelValueBean> cir170FileLabelList__ = null;

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param req リクエスト
     * @param tempDir テンポラリディレクトリ
     * @return エラー
     * @throws Exception 実行時例外
     */
    public ActionErrors validateCheck(Connection con, HttpServletRequest req, String tempDir)
    throws Exception {
        ActionErrors errors = new ActionErrors();

        String eprefix = "inputFile.";

        CirCommonBiz sBiz = new CirCommonBiz();
        List<Cmn110FileModel> fileDataList = sBiz.getFileData(tempDir);

        //ファイルが存在しない場合
        if (fileDataList == null || fileDataList.isEmpty()) {
            ActionMessage msg =
                new ActionMessage("error.select.required.text",
                                getInterMessage(req, "cmn.capture.file"));
            StrutsUtil.addMessage(errors, msg, eprefix + "error.select.required.text");
        } else {

            boolean csvError = false;
            String fileName = null;
            //複数選択エラー
            if (fileDataList.size() > 2) {
                ActionMessage msg =
                    new ActionMessage("error.input.notfound.file",
                                    getInterMessage(req, "cmn.capture.file"));
                StrutsUtil.addMessage(errors, msg, eprefix + "error.input.notfound.file");
                csvError = true;
            } else {
                //拡張子チェック
                fileName = fileDataList.get(0).getFileName();
                String strExt = StringUtil.getExtension(fileName);
                if (strExt == null || !strExt.toUpperCase().equals(".CSV")) {
                    ActionMessage msg =
                        new ActionMessage("error.select.required.text",
                                        getInterMessage(req, "cmn.csv.file.format"));
                    StrutsUtil.addMessage(errors, msg, eprefix + "error.select.required.text");
                    csvError = true;
                }
            }

            //入力チェック
            if (!csvError) {
                String fullPath = tempDir + fileDataList.get(0).getSaveFileName();
                CircularCsvReader csvReader = new CircularCsvReader();
                csvReader.readCsvFile(fullPath);
                List<CircularCsvModel> accountList = csvReader.getCircularList();

                int accountCount = 0;
                for (CircularCsvModel accountData : accountList) {
                    int errorCnt = errors.size();
                    String rowNum = String.valueOf(accountData.getRowNum());
                    String rowStr = getInterMessage(req, "cmn.line2", rowNum);
                    //アカウント名入力チェック
                    GSValidateCircular.validateTextBoxInput(errors, accountData.getAccountName(),
                            "acntName" + rowNum,
                            rowStr + getInterMessage(req, GSConstCircular.TEXT_ACCOUNT),
                            GSConstCircular.MAXLEN_ACCOUNT, true);


                    int errSize = errors.size();


                    //使用ユーザ
                    boolean user1 = StringUtil.isNullZeroString(accountData.getUser1());
                    boolean user2 = StringUtil.isNullZeroString(accountData.getUser2());
                    boolean user3 = StringUtil.isNullZeroString(accountData.getUser3());
                    boolean user4 = StringUtil.isNullZeroString(accountData.getUser4());
                    boolean user5 = StringUtil.isNullZeroString(accountData.getUser5());

                    String text_useUser = getInterMessage(req, GSConstCircular.TEXT_USE_USER);
                    if (user1 && user2 && user3 && user4 && user5) {
                        //未入力チェック
                        String chkFlgName = "acntUseUser" + rowNum;
                        eprefix = chkFlgName + ".";
                        String targetJp = rowStr + text_useUser;
                        ActionMessage msg = new ActionMessage("error.input.required.text",
                                targetJp);
                        StrutsUtil.addMessage(errors, msg, eprefix + "error.input.required.text");

                    } else {
                        errSize = errors.size();
                        //ユーザ1
                        GSValidateCircular.validateCsvUserId(errors, accountData.getUser1(),
                                                           "acntUser1" + rowNum,
                                                            rowStr + text_useUser,
                                                            1);
                        if (errSize == errors.size()) {
                            GSValidateCircular.validateCsvUserIdExist(
                                    errors, accountData.getUser1(),
                                   "acntExUser1" + rowNum,
                                    rowStr + text_useUser,
                                    con, 1);
                        }
                        errSize = errors.size();

                        //ユーザ2
                        GSValidateCircular.validateCsvUserId(errors, accountData.getUser2(),
                                                           "acntUser2" + rowNum,
                                                            rowStr + text_useUser,
                                                            2);
                        if (errSize == errors.size()) {
                            GSValidateCircular.validateCsvUserIdExist(
                                    errors, accountData.getUser2(),
                                    "acntExUser2" + rowNum,
                                     rowStr + text_useUser,
                                     con, 2);
                        }
                        errSize = errors.size();

                        //ユーザ3
                        GSValidateCircular.validateCsvUserId(errors, accountData.getUser3(),
                                                           "acntUser3" + rowNum,
                                                            rowStr + text_useUser,
                                                            3);
                        if (errSize == errors.size()) {
                            GSValidateCircular.validateCsvUserIdExist(
                                    errors, accountData.getUser3(),
                                    "acntExUser3" + rowNum,
                                    rowStr + text_useUser,
                                    con, 3);
                        }
                        errSize = errors.size();

                        //ユーザ4
                        GSValidateCircular.validateCsvUserId(errors, accountData.getUser4(),
                                                           "acntUser4" + rowNum,
                                                            rowStr + text_useUser,
                                                            4);
                        if (errSize == errors.size()) {
                            GSValidateCircular.validateCsvUserIdExist(
                                    errors, accountData.getUser4(),
                                    "acntExUser4" + rowNum,
                                    rowStr + text_useUser,
                                    con, 4);
                        }
                        errSize = errors.size();

                        //ユーザ5
                        GSValidateCircular.validateCsvUserId(errors, accountData.getUser5(),
                                                           "acntUser5" + rowNum,
                                                            rowStr + text_useUser,
                                                            5);
                        if (errSize == errors.size()) {
                            GSValidateCircular.validateCsvUserIdExist(
                                    errors, accountData.getUser5(),
                                    "acntExUser5" + rowNum,
                                    rowStr + text_useUser,
                                    con, 5);
                        }
                    }
                    if (errorCnt == errors.size()) {
                        accountCount++;
                    }
                }

                if (accountCount == 0) {
                    String msgText = getInterMessage(req, "cmn.capture.file");
                    ActionMessage msg =
                        new ActionMessage("search.notfound.data", msgText);
                    StrutsUtil.addMessage(errors, msg, "inputFile.search.notfound.data");
                }
            }
        }
        return errors;
    }

    /**
     * <p>cir170FileLabelList を取得します。
     * @return cir170FileLabelList
     */
    public List<LabelValueBean> getCir170FileLabelList() {
        return cir170FileLabelList__;
    }
    /**
     * <p>cir170FileLabelList をセットします。
     * @param cir170FileLabelList cir170FileLabelList
     */
    public void setCir170FileLabelList(List<LabelValueBean> cir170FileLabelList) {
        cir170FileLabelList__ = cir170FileLabelList;
    }
    /**
     * <p>cir170pluginId を取得します。
     * @return cir170pluginId
     */
    public String getCir170pluginId() {
        return cir170pluginId__;
    }
    /**
     * <p>cir170pluginId をセットします。
     * @param cir170pluginId cir170pluginId
     */
    public void setCir170pluginId(String cir170pluginId) {
        cir170pluginId__ = cir170pluginId;
    }
    /**
     * <p>cir170selectFiles を取得します。
     * @return cir170selectFiles
     */
    public String[] getCir170selectFiles() {
        return cir170selectFiles__;
    }
    /**
     * <p>cir170selectFiles をセットします。
     * @param cir170selectFiles cir170selectFiles
     */
    public void setCir170selectFiles(String[] cir170selectFiles) {
        cir170selectFiles__ = cir170selectFiles;
    }
}
