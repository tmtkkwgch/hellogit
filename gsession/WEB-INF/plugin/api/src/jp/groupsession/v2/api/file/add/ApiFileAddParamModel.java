package jp.groupsession.v2.api.file.add;

import jp.groupsession.v2.cmn.model.AbstractParamModel;

import org.apache.struts.upload.FormFile;

/**
 * <br>[機  能] /file/addのフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiFileAddParamModel extends AbstractParamModel {

    /** 親ディレクトリSID */
    private String fdrParentSid__ = null;
    /** ファイル */
    private FormFile uploadFile__ = null;

    /**
     * @return fdrParentSid
     */
    public String getFdrParentSid() {
        return fdrParentSid__;
    }

    /**
     * @param fdrParentSid 設定する fdrParentSid
     */
    public void setFdrParentSid(String fdrParentSid) {
        fdrParentSid__ = fdrParentSid;
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
