package jp.groupsession.v2.rsv.rsv260;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.io.ObjectFile;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.cmn110.Cmn110FileModel;
import jp.groupsession.v2.rsv.rsv050.Rsv050ParamModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 施設予約 グループ・施設一括登録画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv260ParamModel extends Rsv050ParamModel {

    /** 施設区分選択値 */
    private int rsv260SelectedSisetuKbn__ = -1;
    /** 施設区分コンボリスト */
    private ArrayList<LabelValueBean> rsv260SisetuLabelList__ = null;
    /** 管理者ユーザセーブ */
    private String[] saveUser__ = null;
    /** 選択済ユーザ(左) */
    private String[] rsv260SelectedLeft__ = null;
    /** セレクトエリア(左) */
    private ArrayList<LabelValueBean> rsv260AdmUser__ = null;
    /** 選択済ユーザ(右) */
    private String[] rsv260SelectedRight__ = null;
    /** セレクトエリア(右) */
    private ArrayList<LabelValueBean> rsv260NotAdmUser__ = null;
    /** グループコンボリスト */
    private ArrayList<LabelValueBean> rsv260GrpLabelList__ = null;
    /** グループコンボ選択値 */
    private int rsv260SelectedGrpComboSid__ = -1;
    /** 権限設定 */
    private int rsv260GrpAdmKbn__ = -1;
    /** 既存の施設情報更新フラグ */
    private String rsv260updateFlg__ = "0";
    /** グループ作成フラグ */
    private String rsv260createGrpFlg__ = "0";
    /** ファイルコンボ */
    private ArrayList<LabelValueBean> rsv260FileLabelList__ = null;
    /** 添付ファイル(コンボで選択中) */
    private String[] rsv260SelectFiles__ = null;
    /** 施設区分SID */
    private int rsv260RskSid__ = -1;
    /** 有効データ件数 */
    private int impDataCnt__ = 0;

    /**
     * <p>rsv260updateFlg を取得します。
     * @return rsv260updateFlg
     */
    public String getRsv260updateFlg() {
        return rsv260updateFlg__;
    }

    /**
     * <p>rsv260updateFlg をセットします。
     * @param rsv260updateFlg rsv260updateFlg
     */
    public void setRsv260updateFlg(String rsv260updateFlg) {
        rsv260updateFlg__ = rsv260updateFlg;
    }

    /**
     * <p>rsv260AdmUser を取得します。
     * @return rsv260AdmUser
     */
    public ArrayList<LabelValueBean> getRsv260AdmUser() {
        return rsv260AdmUser__;
    }

    /**
     * <p>rsv260AdmUser をセットします。
     * @param rsv260AdmUser rsv260AdmUser
     */
    public void setRsv260AdmUser(ArrayList<LabelValueBean> rsv260AdmUser) {
        rsv260AdmUser__ = rsv260AdmUser;
    }

    /**
     * <p>rsv260GrpAdmKbn を取得します。
     * @return rsv260GrpAdmKbn
     */
    public int getRsv260GrpAdmKbn() {
        return rsv260GrpAdmKbn__;
    }

    /**
     * <p>rsv260GrpAdmKbn をセットします。
     * @param rsv260GrpAdmKbn rsv260GrpAdmKbn
     */
    public void setRsv260GrpAdmKbn(int rsv260GrpAdmKbn) {
        rsv260GrpAdmKbn__ = rsv260GrpAdmKbn;
    }

    /**
     * <p>rsv260GrpLabelList を取得します。
     * @return rsv260GrpLabelList
     */
    public ArrayList<LabelValueBean> getRsv260GrpLabelList() {
        return rsv260GrpLabelList__;
    }

    /**
     * <p>rsv260GrpLabelList をセットします。
     * @param rsv260GrpLabelList rsv260GrpLabelList
     */
    public void setRsv260GrpLabelList(ArrayList<LabelValueBean> rsv260GrpLabelList) {
        rsv260GrpLabelList__ = rsv260GrpLabelList;
    }

    /**
     * <p>rsv260NotAdmUser を取得します。
     * @return rsv260NotAdmUser
     */
    public ArrayList<LabelValueBean> getRsv260NotAdmUser() {
        return rsv260NotAdmUser__;
    }

    /**
     * <p>rsv260NotAdmUser をセットします。
     * @param rsv260NotAdmUser rsv260NotAdmUser
     */
    public void setRsv260NotAdmUser(ArrayList<LabelValueBean> rsv260NotAdmUser) {
        rsv260NotAdmUser__ = rsv260NotAdmUser;
    }

    /**
     * <p>rsv260SelectedGrpComboSid を取得します。
     * @return rsv260SelectedGrpComboSid
     */
    public int getRsv260SelectedGrpComboSid() {
        return rsv260SelectedGrpComboSid__;
    }

    /**
     * <p>rsv260SelectedGrpComboSid をセットします。
     * @param rsv260SelectedGrpComboSid rsv260SelectedGrpComboSid
     */
    public void setRsv260SelectedGrpComboSid(int rsv260SelectedGrpComboSid) {
        rsv260SelectedGrpComboSid__ = rsv260SelectedGrpComboSid;
    }

    /**
     * <p>rsv260SelectedLeft を取得します。
     * @return rsv260SelectedLeft
     */
    public String[] getRsv260SelectedLeft() {
        return rsv260SelectedLeft__;
    }

    /**
     * <p>rsv260SelectedLeft をセットします。
     * @param rsv260SelectedLeft rsv260SelectedLeft
     */
    public void setRsv260SelectedLeft(String[] rsv260SelectedLeft) {
        rsv260SelectedLeft__ = rsv260SelectedLeft;
    }

    /**
     * <p>rsv260SelectedRight を取得します。
     * @return rsv260SelectedRight
     */
    public String[] getRsv260SelectedRight() {
        return rsv260SelectedRight__;
    }

    /**
     * <p>rsv260SelectedRight をセットします。
     * @param rsv260SelectedRight rsv260SelectedRight
     */
    public void setRsv260SelectedRight(String[] rsv260SelectedRight) {
        rsv260SelectedRight__ = rsv260SelectedRight;
    }

    /**
     * <p>rsv260SelectedSisetuKbn を取得します。
     * @return rsv260SelectedSisetuKbn
     */
    public int getRsv260SelectedSisetuKbn() {
        return rsv260SelectedSisetuKbn__;
    }

    /**
     * <p>rsv260SelectedSisetuKbn をセットします。
     * @param rsv260SelectedSisetuKbn rsv260SelectedSisetuKbn
     */
    public void setRsv260SelectedSisetuKbn(int rsv260SelectedSisetuKbn) {
        rsv260SelectedSisetuKbn__ = rsv260SelectedSisetuKbn;
    }

    /**
     * <p>rsv260SisetuLabelList を取得します。
     * @return rsv260SisetuLabelList
     */
    public ArrayList<LabelValueBean> getRsv260SisetuLabelList() {
        return rsv260SisetuLabelList__;
    }

    /**
     * <p>rsv260SisetuLabelList をセットします。
     * @param rsv260SisetuLabelList rsv260SisetuLabelList
     */
    public void setRsv260SisetuLabelList(
            ArrayList<LabelValueBean> rsv260SisetuLabelList) {
        rsv260SisetuLabelList__ = rsv260SisetuLabelList;
    }

    /**
     * <p>saveUser を取得します。
     * @return saveUser
     */
    public String[] getSaveUser() {
        return saveUser__;
    }

    /**
     * <p>saveUser をセットします。
     * @param saveUser saveUser
     */
    public void setSaveUser(String[] saveUser) {
        saveUser__ = saveUser;
    }

    /**
     * <p>rsv260createGrpFlg を取得します。
     * @return rsv260createGrpFlg
     */
    public String getRsv260createGrpFlg() {
        return rsv260createGrpFlg__;
    }

    /**
     * <p>rsv260createGrpFlg をセットします。
     * @param rsv260createGrpFlg rsv260createGrpFlg
     */
    public void setRsv260createGrpFlg(String rsv260createGrpFlg) {
        rsv260createGrpFlg__ = rsv260createGrpFlg;
    }

    /**
     * <p>rsv260FileLabelList を取得します。
     * @return rsv260FileLabelList
     */
    public ArrayList<LabelValueBean> getRsv260FileLabelList() {
        return rsv260FileLabelList__;
    }

    /**
     * <p>rsv260FileLabelList をセットします。
     * @param rsv260FileLabelList rsv260FileLabelList
     */
    public void setRsv260FileLabelList(ArrayList<LabelValueBean> rsv260FileLabelList) {
        rsv260FileLabelList__ = rsv260FileLabelList;
    }

    /**
     * <p>rsv260SelectFiles を取得します。
     * @return rsv260SelectFiles
     */
    public String[] getRsv260SelectFiles() {
        return rsv260SelectFiles__;
    }

    /**
     * <p>rsv260SelectFiles をセットします。
     * @param rsv260SelectFiles rsv260SelectFiles
     */
    public void setRsv260SelectFiles(String[] rsv260SelectFiles) {
        rsv260SelectFiles__ = rsv260SelectFiles;
    }

    /**
     * <p>rsv260RskSid を取得します。
     * @return rsv260RskSid
     */
    public int getRsv260RskSid() {
        return rsv260RskSid__;
    }

    /**
     * <p>rsv260RskSid をセットします。
     * @param rsv260RskSid rsv260RskSid
     */
    public void setRsv260RskSid(int rsv260RskSid) {
        rsv260RskSid__ = rsv260RskSid;
    }

    /**
     * <p>impDataCnt を取得します。
     * @return impDataCnt
     */
    public int getImpDataCnt() {
        return impDataCnt__;
    }

    /**
     * <p>impDataCnt をセットします。
     * @param impDataCnt impDataCnt
     */
    public void setImpDataCnt(int impDataCnt) {
        impDataCnt__ = impDataCnt;
    }

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param req リクエスト
     * @param tempDir 添付DIR
     * @param con DBコネクション
     * @return エラー
     * @throws SQLException SQL実行例外
     * @throws IOToolsException SQL実行例外
     * @throws Exception 実行例外
     */
    public ActionErrors validateCheck(ActionMapping map,
                                       HttpServletRequest req,
                                       String tempDir,
                                       Connection con)
        throws IOToolsException, SQLException, Exception {

        ActionErrors errors = new ActionErrors();

        //テンポラリディレクトリにあるファイル名称を取得
        List<String> fileList = IOTools.getFileNames(tempDir);

        String saveFileName = "";
        String baseFileName = "";
        String eprefix = "inputFile.";
        GsMessage gsMsg = new GsMessage();

        if (fileList == null) {
            ActionMessage msg =
                new ActionMessage(
                        "error.select.required.text",
                        gsMsg.getMessage(req, "cmn.capture.file"));
            StrutsUtil.addMessage(errors, msg, eprefix + "error.select.required.text");
        } else {
            for (int i = 0; i < fileList.size(); i++) {
                //ファイル名を取得
                String fileName = fileList.get(i);
                if (!fileName.endsWith(GSConstCommon.ENDSTR_OBJFILE)) {
                    continue;
                }

                //オブジェクトファイルを取得
                ObjectFile objFile = new ObjectFile(tempDir, fileName);
                Object fObj = objFile.load();
                if (fObj == null) {
                    continue;
                }
                Cmn110FileModel fMdl = (Cmn110FileModel) fObj;
                saveFileName = fMdl.getSaveFileName();
                baseFileName = fMdl.getFileName();
            }

            boolean csvError = false;
            //複数選択エラー
            if (fileList.size() > 2) {
                ActionMessage msg =
                    new ActionMessage(
                            "error.input.notfound.file",
                            gsMsg.getMessage(req, "cmn.capture.file"));
                StrutsUtil.addMessage(errors, msg, eprefix + "error.input.notfound.file");
                csvError = true;
            } else {
                //拡張子チェック
                String strExt = StringUtil.getExtension(baseFileName);
                if (strExt == null || !strExt.toUpperCase().equals(".CSV")) {
                    ActionMessage msg =
                        new ActionMessage(
                                "error.select.required.text",
                                gsMsg.getMessage(req, "cmn.csv.file.format"));
                    StrutsUtil.addMessage(errors, msg, eprefix + "error.select.required.text");
                    csvError = true;
                }
            }

            if (!csvError) {
                String fullPath = tempDir + saveFileName;
                RsvImportCheck csvCheck = new RsvImportCheck(
                        errors, con, rsv260SelectedSisetuKbn__,
                        rsv260updateFlg__, rsv260createGrpFlg__, req);

                //CSVチェック
                if (errors.isEmpty() && csvCheck.isCsvDataOk(fullPath)) {
                    ActionMessage msg =
                        new ActionMessage("error.format.impfile");
                    StrutsUtil.addMessage(errors, msg, eprefix + "error.format.impfile");
                    csvError = true;

                }

                //有効データ数
                setImpDataCnt(csvCheck.getCount());
                if (!csvError && getImpDataCnt() <= 0) {
                    ActionMessage msg =
                        new ActionMessage("error.nodata.impfile");
                    StrutsUtil.addMessage(errors, msg, eprefix + "error.nodata.impfile");
                }
            }
        }

        return errors;
    }
}