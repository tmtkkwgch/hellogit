package jp.groupsession.v2.api.file.update;

import org.apache.struts.upload.FormFile;

import jp.groupsession.v2.cmn.model.AbstractParamModel;
/**
 *
 * <br>[機  能] /file/updateのparamModel
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiFileUpdateParamModel extends AbstractParamModel {
    /** ディレクトリSID */
    private String fdrSid__ = null;
    /** ファイル */
    private FormFile uploadFile__ = null;

//    /** キャビネットSID(入力チェック用) */
//    private int fcbSid__ = 0;
    /**
     * @return fdrSid
     */
    public String getFdrSid() {
        return fdrSid__;
    }

    /**
     * @param fdrSid 設定する fdrSid
     */
    public void setFdrSid(String fdrSid) {
        fdrSid__ = fdrSid;
    }

    /**
     * @return uploadFile
     */
    public FormFile getUploadFile() {
        return uploadFile__;
    }

    /**
     * @param uploadFile 設定する uploadFile
     */
    public void setUploadFile(FormFile uploadFile) {
        uploadFile__ = uploadFile;
    }

}
