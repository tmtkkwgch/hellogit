package jp.groupsession.v2.fil.fil100;

import java.util.List;

import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.fil.GSConstFile;

/**
 * <br>[機  能] ファイル詳細検索パラメータモデル
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Fil100SearchParameterModel {

    /** キャビネット */
    private int cabinet__ = GSConstCommon.NUM_INIT;
    /** キーワード */
    private List<String> keyWord__;
    /** キーワード検索区分(AND OR) */
    private int wordKbn__ = GSConstCommon.NUM_INIT;
    /** 更新日 from */
    private UDate upFrDate__ = null;
    /** 更新日 to */
    private UDate upToDate__ = null;
    /** 更新日区分 */
    private int updateKbn__ = GSConstCommon.NUM_INIT;
    /** 対象(フォルダ) */
    private String targetFolder__ = null;
    /** 対象(ファイル) */
    private String targetFile__ = null;
    /** 検索対象(フォルダ・ファイル名) */
    private boolean srchTargetName__ = false;
    /** 検索対象(備考) */
    private boolean srchTargetBiko__ = false;
    /** 検索対象(備考) */
    private boolean srchTargetText__ = false;
    /** 1ページの件数 */
    private int limit__ = 0;
    /** カーソルスタート位置 */
    private int start__ = 0;
    /** ユーザSID */
    private int usrSid__ = 0;

    /** ソートキー */
    private int searchSortKey__ = GSConstFile.SORT_NAME;
    /** オーダーキー */
    private int searchOrderKey__ = GSConstFile.ORDER_KEY_ASC;

    /** 全文検索使用フラグ */
    private boolean searchFlg__ = false;


    /**
     * <br>[機  能] フォルダ・ファイル名キーワードを設定する
     * <br>[解  説] スペース区切りで複数のキーワードを設定する
     * <br>[備  考]
     * @param textWordName フォルダ・ファイル名キーワード
     */
    public void setTextWordName(String textWordName) {

        CommonBiz cBiz = new CommonBiz();
        setKeyWord(cBiz.setKeyword(textWordName));
    }
    /**
     * <br>[機  能] 備考キーワードを設定する
     * <br>[解  説] スペース区切りで複数のキーワードを設定する
     * <br>[備  考]
     * @param textWordBiko 備考キーワード
     */
    public void setTextWordBiko(String textWordBiko) {

        CommonBiz cBiz = new CommonBiz();
        setKeyWord(cBiz.setKeyword(textWordBiko));
    }
    /**
     * <br>[機  能] ファイル内容キーワードを設定する
     * <br>[解  説] スペース区切りで複数のキーワードを設定する
     * <br>[備  考]
     * @param textWordText ファイル内容キーワード
     */
    public void setTextWordText(String textWordText) {

        CommonBiz cBiz = new CommonBiz();
        setKeyWord(cBiz.setKeyword(textWordText));
    }

    /**
     * <p>searchOrderKey を取得します。
     * @return searchOrderKey
     */
    public int getSearchOrderKey() {
        return searchOrderKey__;
    }

    /**
     * <p>searchOrderKey をセットします。
     * @param searchOrderKey searchOrderKey
     */
    public void setSearchOrderKey(int searchOrderKey) {
        searchOrderKey__ = searchOrderKey;
    }

    /**
     * <p>searchSortKey を取得します。
     * @return searchSortKey
     */
    public int getSearchSortKey() {
        return searchSortKey__;
    }

    /**
     * <p>searchSortKey をセットします。
     * @param searchSortKey searchSortKey
     */
    public void setSearchSortKey(int searchSortKey) {
        searchSortKey__ = searchSortKey;
    }
    /**
     * @return cabinet
     */
    public int getCabinet() {
        return cabinet__;
    }

    /**
     * @param cabinet 設定する cabinet
     */
    public void setCabinet(int cabinet) {
        cabinet__ = cabinet;
    }

    /**
     * @return keyWord
     */
    public List<String> getKeyWord() {
        return keyWord__;
    }

    /**
     * @param keyWord 設定する keyWord
     */
    public void setKeyWord(List<String> keyWord) {
        keyWord__ = keyWord;
    }

    /**
     * @return wordKbn
     */
    public int getWordKbn() {
        return wordKbn__;
    }

    /**
     * @param wordKbn 設定する wordKbn
     */
    public void setWordKbn(int wordKbn) {
        wordKbn__ = wordKbn;
    }

    /**
     * @return upFrDate
     */
    public UDate getUpFrDate() {
        return upFrDate__;
    }

    /**
     * @param upFrDate 設定する upFrDate
     */
    public void setUpFrDate(UDate upFrDate) {
        upFrDate__ = upFrDate;
    }

    /**
     * @return upToDate
     */
    public UDate getUpToDate() {
        return upToDate__;
    }

    /**
     * @param upToDate 設定する upToDate
     */
    public void setUpToDate(UDate upToDate) {
        upToDate__ = upToDate;
    }

    /**
     * @return updateKbn
     */
    public int getUpdateKbn() {
        return updateKbn__;
    }

    /**
     * @param updateKbn 設定する updateKbn
     */
    public void setUpdateKbn(int updateKbn) {
        updateKbn__ = updateKbn;
    }

    /**
     * @return targetFolder
     */
    public String getTargetFolder() {
        return targetFolder__;
    }

    /**
     * @param targetFolder 設定する targetFolder
     */
    public void setTargetFolder(String targetFolder) {
        targetFolder__ = targetFolder;
    }

    /**
     * @return targetFile
     */
    public String getTargetFile() {
        return targetFile__;
    }

    /**
     * @param targetFile 設定する targetFile
     */
    public void setTargetFile(String targetFile) {
        targetFile__ = targetFile;
    }

    /**
     * @return srchTargetName
     */
    public boolean isSrchTargetName() {
        return srchTargetName__;
    }

    /**
     * @param srchTargetName 設定する srchTargetName
     */
    public void setSrchTargetName(boolean srchTargetName) {
        srchTargetName__ = srchTargetName;
    }

    /**
     * @return srchTargetBiko
     */
    public boolean isSrchTargetBiko() {
        return srchTargetBiko__;
    }

    /**
     * @param srchTargetBiko 設定する srchTargetBiko
     */
    public void setSrchTargetBiko(boolean srchTargetBiko) {
        srchTargetBiko__ = srchTargetBiko;
    }
    /**
     * <p>limit を取得します。
     * @return limit
     */
    public int getLimit() {
        return limit__;
    }

    /**
     * <p>limit をセットします。
     * @param limit limit
     */
    public void setLimit(int limit) {
        limit__ = limit;
    }

    /**
     * <p>start を取得します。
     * @return start
     */
    public int getStart() {
        return start__;
    }

    /**
     * <p>start をセットします。
     * @param start start
     */
    public void setStart(int start) {
        start__ = start;
    }

    /**
     * <p>usrSid を取得します。
     * @return usrSid
     */
    public int getUsrSid() {
        return usrSid__;
    }

    /**
     * <p>usrSid をセットします。
     * @param usrSid usrSid
     */
    public void setUsrSid(int usrSid) {
        usrSid__ = usrSid;
    }
    /**
     * @return srchTargetText
     */
    public boolean isSrchTargetText() {
        return srchTargetText__;
    }
    /**
     * @param srchTargetText セットする srchTargetText
     */
    public void setSrchTargetText(boolean srchTargetText) {
        srchTargetText__ = srchTargetText;
    }

    /**
     * <p>searchFlg を取得します。
     * @return searchFlg
     */
    public boolean getSearchFlg() {
        return searchFlg__;
    }

    /**
     * <p>searchFlg をセットします。
     * @param searchFlg searchFlg
     */
    public void setSearchFlg(boolean searchFlg) {
        searchFlg__ = searchFlg;
    }
}
