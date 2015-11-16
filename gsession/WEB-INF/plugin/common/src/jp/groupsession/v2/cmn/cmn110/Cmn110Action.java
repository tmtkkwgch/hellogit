package jp.groupsession.v2.cmn.cmn110;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.http.TempFileUtil;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.io.ObjectFile;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.struts.AbstractGsAction;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.usr.usr031.Usr031Biz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestHandler;

/**
 * <br>[機  能] ファイル添付ポップアップのアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cmn110Action extends AbstractGsAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Cmn110Action.class);

    /**
     * <br>[機  能] アクション実行
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行例外
     * @return ActionForward
     */
    public ActionForward executeAction(
        ActionMapping map,
        ActionForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        log__.debug("START_Cmn110");
        ActionForward forward = null;

        Cmn110Form thisForm = (Cmn110Form) form;

        //ファイルサイズがMAXサイズを超えた場合、共通メッセージ画面でエラーメッセージを表示
        Object obj = req.getAttribute(MultipartRequestHandler.ATTRIBUTE_MAX_LENGTH_EXCEEDED);
        if (obj != null) {
            Boolean maxlength = (Boolean) obj;
            if (maxlength.booleanValue()) {
                return getFileSizeErrorPage(map, req, res);
            }
        }

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter(GSConst.P_CMD), "");
        cmd = cmd.trim();

        if (cmd.equals("fileUpload")) {
            log__.debug("確定ボタンクリック");
            Cmn110ValidateResultModel resultMdl = __doUpload(map, thisForm, req, res, con);
            if (thisForm.getCmn110uploadType() == Cmn110Form.UPLOADTYPE_DROP) {
                StringBuilder strBuild = new StringBuilder("{");
                List<String> errMsgList = resultMdl.getErrMessageList();
                if (errMsgList != null && !errMsgList.isEmpty()) {
                    strBuild.append("\"errors\" : \"1\",");
                    strBuild.append("\"errorMsg\" : [");
                    int errIdx = 0;
                    for (String errMsg : errMsgList) {
                        if (errIdx > 0) {
                            strBuild.append(",");
                        }

                        strBuild.append("\""
                                    + Cmn110Biz.escapeText(errMsg)
                                    + "\"");

                        errIdx++;
                    }
                    strBuild.append("]");
                } else {
                    strBuild.append("\"errors\" : \"0\",");
                    strBuild.append("\"tempName\" : [");
                    for (int idx = 0; idx < thisForm.getCmn110tempName().length; idx++) {
                        if (idx > 0) {
                            strBuild.append(",");
                        }

                        String tempName = thisForm.getCmn110tempName()[idx];
                        strBuild.append("\""
                                    + Cmn110Biz.escapeText(tempName)
                                    + "\"");
                    }
                    strBuild.append("],");
                    strBuild.append("\"tempSaveName\" : [");
                    for (int idx = 0; idx < thisForm.getCmn110tempSaveName().length; idx++) {
                        if (idx > 0) {
                            strBuild.append(",");
                        }

                        String saveTempName = thisForm.getCmn110tempSaveName()[idx];
                        strBuild.append("\""
                            + Cmn110Biz.escapeText(saveTempName)
                            + "\"");
                    }
                    strBuild.append("]");
                }
                strBuild.append("}");

                PrintWriter writer = null;
                try {
                    res.setContentType("text/json; charset=UTF-8");
                    writer = res.getWriter();
                    writer.write(strBuild.toString());
                    writer.flush();
                } finally {
                    if (writer != null) {
                        writer.close();
                    }
                }
                forward = null;
            } else {
                forward = __doInit(map, thisForm, req, res, con);
            }

        } else {
            log__.debug("初期表示");
            forward = __doInit(map, thisForm, req, res, con);
        }

        log__.debug("END_Cmn110");
        return forward;
    }

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @return ActionForward
     * @throws IOToolsException 添付ファイル操作時例外
     */
    private ActionForward __doInit(
        ActionMapping map,
        Cmn110Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, IOToolsException {

        Cmn110ParamModel paramModel = new Cmn110ParamModel();
        paramModel.setParam(form);

        String rootDir = getTempPath(req);
        String tempDir =
            IOTools.replaceFileSep(Cmn110Biz.getTempDir(rootDir, paramModel, getRequestModel(req)));

        //初期表示情報を画面にセットする
        con.setAutoCommit(true);
        Cmn110Biz biz = new Cmn110Biz();
        biz.setInitData(paramModel, con, tempDir);
        paramModel.setFormData(form);

        con.setAutoCommit(false);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 指定された添付ファイルをテンポラリファイルに保存する
     * <br>[解  説]
     * <br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con Connection
     * @return 入力チェックの結果
     * @throws SQLException SQL実行例外
     * @throws IOException ファイルアクセス時例外
     * @throws IOToolsException ファイルアクセス時例外
     */
    private Cmn110ValidateResultModel __doUpload(ActionMapping map,
        Cmn110Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con)
        throws SQLException, IOException, IOToolsException {

        GsMessage gsMsg = new GsMessage(getRequestModel(req));
        Cmn110ValidateResultModel resultMdl = null;
        try {

            //入力チェック
            con.setAutoCommit(true);
            resultMdl = form.validate110(con, req);
            con.setAutoCommit(false);
            if (!resultMdl.getErrors().isEmpty()) {
                addErrors(req, resultMdl.getErrors());
                return resultMdl;
            }

            List<FormFile> formFileList = form.getCmn110fileForm();

            Cmn110ParamModel paramModel = new Cmn110ParamModel();
            paramModel.setParam(form);

            //テンポラリディレクトリパス
            String rootDir = getTempPath(req);
            String tempDir =
                IOTools.replaceFileSep(
                        Cmn110Biz.getTempDir(rootDir, paramModel, getRequestModel(req)));

            List<String> tempNameList = new ArrayList<String>();
            List<String> saveTempNameList = new ArrayList<String>();

            int fileNo = 0;
            for (FormFile formFile : formFileList) {
                fileNo++;

                String cmn110Mode = NullDefault.getString(form.getCmn110Mode(), "");
                if (cmn110Mode.equals(String.valueOf(GSConstCommon.FILE_UPLOAD_MODE_TANITU))
                || cmn110Mode.equals(String.valueOf(GSConstCommon.FILE_UPLOAD_MODE_TANITU_USR031))
                || cmn110Mode.equals(String.valueOf(GSConstCommon.FILE_UPLOAD_MODE_TANITU_FIL030))
                ) {
                    //単一の画像アップロードモード

                    //拡張子チェック
                    if (cmn110Mode.equals(String.valueOf(
                            GSConstCommon.FILE_UPLOAD_MODE_TANITU_USR031))) {
                        //ユーザ情報の写真のアップロード時
                        if (!Cmn110Biz.isExtensionForPhotoOk(formFile.getFileName())) {
                            //JPG,JPEG,GIF,PNG以外のファイルならばエラー
                            String[] strPhoto = {gsMsg.getMessage("cmn.photo")};
                            resultMdl.addErrors(req, "error.select2.required.extent2",
                                                "cmn.cmn110.error.select2.required.extent2",
                                                fileNo, strPhoto);
                            addErrors(req, resultMdl.getErrors());
                            return resultMdl;
                        }

                    } else {
                        //通常の添付ファイルのアップロード時
                        if (!Cmn110Biz.isExtensionOk(formFile.getFileName())) {
                            //BMP,JPG,JPEG,GIF,PNG以外のファイルならばエラー
                            resultMdl.addErrors(req, "error.select2.required.extent",
                                                "cmn.cmn110.error.select2.required.extent",
                                                fileNo);
                            addErrors(req, resultMdl.getErrors());
                            return resultMdl;
                        }
                    }

                    //添付ファイル名
                    String fileName = (new File(formFile.getFileName())).getName();
                    long fileSize = formFile.getFileSize();

                    //テンポラリディレクトリのファイル削除を行う
                    Usr031Biz biz = new Usr031Biz(getRequestModel(req));
                    biz.doDeleteFile(tempDir);

                    //現在日付の文字列(YYYYMMDD)を取得
                    String dateStr = (new UDate()).getDateString();

                    //添付ファイル(本体)のパスを取得
                    File saveFilePath = Cmn110Biz.getSaveFilePath(tempDir, dateStr, 0);

                    //添付ファイルアップロード
                    TempFileUtil.upload(formFile, tempDir, saveFilePath.getName());

                    //オブジェクトファイルを設定
                    File objFilePath = Cmn110Biz.getObjFilePath(tempDir, dateStr, 0);
                    Cmn110FileModel fileMdl = new Cmn110FileModel();
                    fileMdl.setFileName(fileName);
                    fileMdl.setSaveFileName(saveFilePath.getName());
                    fileMdl.setUpdateKbn(0);

                    ObjectFile objFile =
                            new ObjectFile(objFilePath.getParent(), objFilePath.getName());
                    objFile.save(fileMdl);

                    String saveFileName = saveFilePath.getName();

                    log__.debug(">>>サイズ :" + fileSize);
                    tempNameList.add(CommonBiz.addAtattiSizeForName(fileName, fileSize));
                    saveTempNameList.add(saveFileName.substring(0, saveFileName.length() - 4));
                    form.setCmn110Decision(1);

                } else if (cmn110Mode.equals(
                        String.valueOf(GSConstCommon.FILE_UPLOAD_MODE_FILE))) {
                    //ファイル管理 ファイルアップロード

                    //テンポラリディレクトリにあるファイル名称を取得
                    List<String> fileList = IOTools.getFileNames(tempDir);
                    int updateKbn = 1;
                    int pdrSid = -1;
                    Long binSid = new Long(-1);
                    String oldObjName = "";

                    if (fileList != null && fileList.size() > 0) {

                        if (form.getCmn110fileLimit() == GSConstCommon.FILE_LIMIT_FILENUM_ONE) {
                            log__.debug("*** 添付可能ファイル数 = 1");

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
                                binSid = fMdl.getBinSid();
                                pdrSid = fMdl.getPdrSid();
                                updateKbn = 3;

                                oldObjName = fMdl.getSplitObjName();

                                //既存オブジェクト削除
                                __deleteObject(tempDir, oldObjName);
                            }

                        } else {

                            log__.debug("*** 添付可能ファイル数 > 1");
                            log__.debug("*** 既存ファイルをチェック");

                            HashMap<String, Cmn110FileModel> fileMap =
                                new HashMap<String, Cmn110FileModel>();

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

                                log__.debug("*** fileMap.put = " + fMdl.getFileName());

                                fileMap.put(fMdl.getFileName(), fMdl);
                            }

                            if (!fileMap.isEmpty()) {

                                String updFileName = formFile.getFileName();
                                if (fileMap.containsKey(updFileName)) {

                                    log__.debug("*** 既存ファイルに同名ファイルが存在");
                                    Cmn110FileModel oldMdl = fileMap.get(updFileName);
                                    binSid = oldMdl.getBinSid();
                                    pdrSid = oldMdl.getPdrSid();

                                    oldObjName = oldMdl.getSplitObjName();

                                    //既存オブジェクト削除
                                    __deleteObject(tempDir, oldObjName);

                                    if (oldMdl.getUpdateKbn() == 1) {
                                        updateKbn = 1;
                                    } else if (oldMdl.getUpdateKbn() == 2
                                            || oldMdl.getUpdateKbn() == 3) {
                                        updateKbn = 3;
                                    }
                                }
                            }
                        }
                    }

                    //現在日付の文字列(YYYYMMDD)を取得
                    String dateStr = (new UDate()).getDateString();

                    //ファイルの連番を取得する
                    int fileNum = 1;
                    if (form.getCmn110fileLimit() != GSConstCommon.FILE_LIMIT_FILENUM_ONE) {
                        //ファイル数 = 無制限の場合はTEMPディレクトリ内のファイル数から
                        //連番を取得する
                        fileNum = Cmn110Biz.getFileNumber(tempDir, dateStr);
                        fileNum++;
                    }

                    //添付ファイル名
                    String fileName = (new File(formFile.getFileName())).getName();
                    long fileSize = formFile.getFileSize();
                    //添付ファイル(本体)のパスを取得
                    File saveFilePath = Cmn110Biz.getSaveFilePath(tempDir, dateStr, fileNum);

                    //添付ファイルアップロード
                    TempFileUtil.upload(formFile, tempDir, saveFilePath.getName());

                    //オブジェクトファイルを設定
                    File objFilePath = Cmn110Biz.getObjFilePath(tempDir, dateStr, fileNum);
                    Cmn110FileModel fileMdl = new Cmn110FileModel();
                    fileMdl.setBinSid(binSid);
                    fileMdl.setPdrSid(pdrSid);
                    fileMdl.setFileName(fileName);
                    fileMdl.setSaveFileName(saveFilePath.getName());
                    fileMdl.setAtattiSize(fileSize);

                    String[] fileVal
                        = fileMdl.getSaveFileName().split(GSConstCommon.ENDSTR_SAVEFILE);
                    log__.debug("*** セーブファイル = " + fileVal[0]);
                    fileMdl.setSplitObjName(fileVal[0]);

                    log__.debug("*** updateKbn = " + updateKbn);
                    fileMdl.setUpdateKbn(updateKbn);

                    ObjectFile objFile
                        = new ObjectFile(objFilePath.getParent(), objFilePath.getName());
                    objFile.save(fileMdl);

                    String saveFileName = saveFilePath.getName();

                    log__.debug(">>>サイズ :" + fileSize);
                    tempNameList.add(CommonBiz.addAtattiSizeForName(fileName, fileSize));
                    saveTempNameList.add(saveFileName.substring(0, saveFileName.length() - 4));
                    form.setCmn110Decision(1);

                //複数ファイルアップロード
                } else {

                    //施設予約:施設画像設定に使用する
                    if (cmn110Mode.equals(
                            String.valueOf(GSConstCommon.FILE_UPLOAD_MODE_RETO_RSV090))) {
                        if (!Cmn110Biz.isExtensionOk(formFile.getFileName())) {
                            //BMP,JPG,JPEG,GIF,PNG以外のファイルならばエラー
                            resultMdl.addErrors(req, "error.select2.required.extent",
                                                "error.select2.required.extent",
                                                fileNo);
                            addErrors(req, resultMdl.getErrors());
                            return resultMdl;
                        }
                    }

                    //現在日付の文字列(YYYYMMDD)を取得
                    String dateStr = (new UDate()).getDateString();

                    //ファイルの連番を取得する
                    int fileNum = 1;
                    if (form.getCmn110fileLimit() != GSConstCommon.FILE_LIMIT_FILENUM_ONE) {
                        //ファイル数 = 無制限の場合はTEMPディレクトリ内のファイル数から
                        //連番を取得する
                        fileNum = Cmn110Biz.getFileNumber(tempDir, dateStr);
                        fileNum++;
                    }

                    //添付ファイル名
                    String fileName = (new File(formFile.getFileName())).getName();
                    long fileSize = formFile.getFileSize();
                    //添付ファイル(本体)のパスを取得
                    File saveFilePath = Cmn110Biz.getSaveFilePath(tempDir, dateStr, fileNum);

                    //添付ファイルアップロード
                    TempFileUtil.upload(formFile, tempDir, saveFilePath.getName());

                    //オブジェクトファイルを設定
                    File objFilePath = Cmn110Biz.getObjFilePath(tempDir, dateStr, fileNum);
                    Cmn110FileModel fileMdl = new Cmn110FileModel();
                    fileMdl.setFileName(fileName);
                    fileMdl.setSaveFileName(saveFilePath.getName());
                    fileMdl.setUpdateKbn(0);

                    ObjectFile objFile
                        = new ObjectFile(objFilePath.getParent(), objFilePath.getName());
                    objFile.save(fileMdl);

                    String saveFileName = saveFilePath.getName();

                    log__.debug(">>>サイズ :" + fileSize);
                    tempNameList.add(CommonBiz.addAtattiSizeForName(fileName, fileSize));
                    saveTempNameList.add(saveFileName.substring(0, saveFileName.length() - 4));
                    form.setCmn110Decision(1);
                }
            }
            form.setCmn110tempName(
                    (String[]) tempNameList.toArray(new String[tempNameList.size()]));
            form.setCmn110tempSaveName(
                    (String[]) saveTempNameList.toArray(new String[saveTempNameList.size()]));
        } catch (IOException e) {
            log__.error("IOException", e);
            throw e;
        } catch (IOToolsException e) {
            log__.error("IOToolsException", e);
            throw e;
        } catch (Exception e) {
            log__.error("Exception", e);
            throw new IOException();
        }

        return resultMdl;
    }

    /**
     * <br>[機  能] 指定されたファイルを削除する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param tempDir テンポラリディレクトリ
     * @param oldObjName 削除オブジェクト
     * @throws IOToolsException ファイルアクセス時例外
     */
    private void __deleteObject(String tempDir, String oldObjName) throws IOToolsException {

        log__.debug("*** 既存ファイル オブジェクト = " + oldObjName);

        //テンポラリファイルのフルパス(オブジェクト)
        String delPathObj =
            tempDir + oldObjName + GSConstCommon.ENDSTR_OBJFILE;
        delPathObj = IOTools.replaceFileSep(delPathObj);
        log__.debug("削除するファイルのフルパス(オブジェクト) = " + delPathObj);

        //テンポラリファイルのフルパス(本体)
        String delPathFile =
            tempDir + oldObjName + GSConstCommon.ENDSTR_SAVEFILE;
        delPathFile = IOTools.replaceFileSep(delPathFile);
        log__.debug("削除するファイルのフルパス(本体) = " + delPathFile);

        //ファイルを削除
        IOTools.deleteFile(delPathObj);
        IOTools.deleteFile(delPathFile);
    }

    /**
     * <p>ファイル容量エラーの場合に遷移する画面を取得する。
     * @param map マップ
     * @param req リクエスト
     * @param res レスポンス
     * @return ActionForward フォワード
     */
    public ActionForward getFileSizeErrorPage(ActionMapping map,
                                                                HttpServletRequest req,
                                                                HttpServletResponse res) {

        String uploadType = NullDefault.getString(req.getParameter("cmn110uploadType"), "");
        if (!uploadType.equals(Integer.toString(Cmn110Form.UPLOADTYPE_DROP))) {
            return super.getFileSizeErrorPage(map, req, res);
        }

        GsMessage gsMsg = new GsMessage(getRequestModel(req));
        StringBuilder strBuild = new StringBuilder("{");
        strBuild.append("\"errors\" : \"1\",");
        strBuild.append("\"errorMsg\" : [");
        strBuild.append("\""
                + gsMsg.getMessage("cmn.upload.totalcapacity.over",
                                            new String[]{GSConstCommon.TEXT_FILE_MAX_SIZE})
                + "\"");
        strBuild.append("]");
        strBuild.append("}");

        PrintWriter writer = null;
        try {
            res.setContentType("text/json; charset=UTF-8");
            writer = res.getWriter();
            writer.write(strBuild.toString());
            writer.flush();
        } catch (IOException e) {
            log__.error("ファイル容量エラーの処理に失敗", e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
        return null;
    }
}