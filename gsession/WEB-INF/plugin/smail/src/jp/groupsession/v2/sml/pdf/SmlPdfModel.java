package jp.groupsession.v2.sml.pdf;

/**
 * <br>[機  能] PDF出力(ショートメール)で使用する情報を格納するModelクラスです。
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class SmlPdfModel {

    /** ファイル名 */
    private String fileName__ = null;
    /** 保存先ファイル名 */
    private String saveFileName__ = null;
    /** アカウント名 */
    private String accName__ = null;
    /** 件名 */
    private String title__ = null;
    /** 送信者 */
    private String sender__ = null;
    /** 日時 */
    private String date__ = null;
    /** 宛先 */
    private String atesaki__ = null;
    /** CC */
    private String atesakiCC__ = null;
    /** BCC */
    private String atesakiBCC__ = null;
    /** マーク */
    private Integer mark__ = null;
    /** 添付 */
    private String tempFile__ = null;
    /** 本文 */
    private String main__ = null;
    /**
     * <p>accName を取得します。
     * @return accName
     */
    public String getAccName() {
        return accName__;
    }
    /**
     * <p>accName をセットします。
     * @param accName accName
     */
    public void setAccName(String accName) {
        accName__ = accName;
    }
    /**
     * <p>title を取得します。
     * @return title
     */
    public String getTitle() {
        return title__;
    }
    /**
     * <p>title をセットします。
     * @param title title
     */
    public void setTitle(String title) {
        title__ = title;
    }
    /**
     * <p>sender を取得します。
     * @return sender
     */
    public String getSender() {
        return sender__;
    }
    /**
     * <p>sender をセットします。
     * @param sender sender
     */
    public void setSender(String sender) {
        sender__ = sender;
    }
    /**
     * <p>date を取得します。
     * @return date
     */
    public String getDate() {
        return date__;
    }
    /**
     * <p>date をセットします。
     * @param date date
     */
    public void setDate(String date) {
        date__ = date;
    }
    /**
     * <p>atesaki を取得します。
     * @return atesaki
     */
    public String getAtesaki() {
        return atesaki__;
    }
    /**
     * <p>atesaki をセットします。
     * @param atesaki atesaki
     */
    public void setAtesaki(String atesaki) {
        atesaki__ = atesaki;
    }
    /**
     * <p>atesakiCC を取得します。
     * @return atesakiCC
     */
    public String getAtesakiCC() {
        return atesakiCC__;
    }
    /**
     * <p>atesakiCC をセットします。
     * @param atesakiCC atesakiCC
     */
    public void setAtesakiCC(String atesakiCC) {
        atesakiCC__ = atesakiCC;
    }
    /**
     * <p>mark を取得します。
     * @return mark
     */
    public Integer getMark() {
        return mark__;
    }
    /**
     * <p>mark をセットします。
     * @param mark mark
     */
    public void setMark(Integer mark) {
        mark__ = mark;
    }
    /**
     * <p>tempFile を取得します。
     * @return tempFile
     */
    public String getTempFile() {
        return tempFile__;
    }
    /**
     * <p>tempFile をセットします。
     * @param tempFile tempFile
     */
    public void setTempFile(String tempFile) {
        tempFile__ = tempFile;
    }
    /**
     * <p>main を取得します。
     * @return main
     */
    public String getMain() {
        return main__;
    }
    /**
     * <p>main をセットします。
     * @param main main
     */
    public void setMain(String main) {
        main__ = main;
    }
    /**
     * <p>fileName を取得します。
     * @return fileName
     */
    public String getFileName() {
        return fileName__;
    }
    /**
     * <p>fileName をセットします。
     * @param fileName fileName
     */
    public void setFileName(String fileName) {
        fileName__ = fileName;
    }
    /**
     * <p>saveFileName を取得します。
     * @return saveFileName
     */
    public String getSaveFileName() {
        return saveFileName__;
    }
    /**
     * <p>saveFileName をセットします。
     * @param saveFileName saveFileName
     */
    public void setSaveFileName(String saveFileName) {
        saveFileName__ = saveFileName;
    }
    /**
     * <p>atesakiBCC を取得します。
     * @return atesakiBCC
     */
    public String getAtesakiBCC() {
        return atesakiBCC__;
    }
    /**
     * <p>atesakiBCC をセットします。
     * @param atesakiBCC atesakiBCC
     */
    public void setAtesakiBCC(String atesakiBCC) {
        atesakiBCC__ = atesakiBCC;
    }
}
