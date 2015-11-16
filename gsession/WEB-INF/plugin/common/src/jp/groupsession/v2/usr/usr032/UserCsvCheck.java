package jp.groupsession.v2.usr.usr032;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.csv.AbstractCsvRecordReader;
import jp.co.sjts.util.csv.CsvTokenizer;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.dao.base.CmnPswdConfDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnPswdConfModel;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.usr.GSConstUser;
import jp.groupsession.v2.usr.GSValidateUser;
import jp.groupsession.v2.usr.GSValidateUserCsv;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

/**
 * <br>[機  能] ユーザインポート 取込みファイル(CSV)のチェックを行うクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class UserCsvCheck extends AbstractCsvRecordReader {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(UserCsvCheck.class);

    /** エラー行存在フラグ */
    private boolean errorFlg__ = false;
    /** コネクション */
    private Connection con__ = null;
    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;
    /** アクションエラー */
    private ActionErrors errors__ = null;
    /** 有効データカウント */
    private int mode__ = 0;
    /** 有効データカウント */
    private int count__ = 0;
    /** グループID重複チェック用MAP */
    private HashMap<String, String> groupIdMap__;
    /** ログインID重複チェック用MAP */
    private HashMap<String, String> loginIdMap__;
    /** 既存のユーザ情報更新フラグ */
    private int updateFlg__ = 0;
    /** グループ作成フラグ */
    private int insertFlg__ = 0;

    /** 重複したユーザ数 */
    private int overlabCount__ = 0;

    /** パスワード変更区分 */
    private boolean canChangePassword__ = true;

    /**
     * @return errors を戻します。
     */
    public ActionErrors getErrors() {
        return errors__;
    }

    /**
     * @param errors 設定する errors。
     */
    public void setErrors(ActionErrors errors) {
        errors__ = errors;
    }

    /**
     * @return con を戻します。
     */
    public Connection getCon() {
        return con__;
    }

    /**
     * @param con 設定する con。
     */
    public void setCon(Connection con) {
        con__ = con;
    }

    /**
     * @return errorFlg を戻します。
     */
    public boolean isErrorFlg() {
        return errorFlg__;
    }

    /**
     * @param errorFlg 設定する errorFlg。
     */
    public void setErrorFlg(boolean errorFlg) {
        errorFlg__ = errorFlg;
    }

    /**
     * @return mode を戻します。
     */
    public int getMode() {
        return mode__;
    }

    /**
     * @param mode 設定する mode。
     */
    public void setMode(int mode) {
        mode__ = mode;
    }
    /**
     * @return count を戻します。
     */
    public int getCount() {
        return count__;
    }

    /**
     * @param count 設定する count。
     */
    public void setCount(int count) {
        count__ = count;
    }

    /**
     * <p>groupIdMap を取得します。
     * @return groupIdMap
     */
    public HashMap<String, String> getGroupIdMap() {
        return groupIdMap__;
    }

    /**
     * <p>groupIdMap をセットします。
     * @param groupIdMap groupIdMap
     */
    public void setGroupIdMap(HashMap<String, String> groupIdMap) {
        groupIdMap__ = groupIdMap;
    }

    /**
     * <p>loginIdMap を取得します。
     * @return loginIdMap
     */
    public HashMap<String, String> getLoginIdMap() {
        return loginIdMap__;
    }

    /**
     * <p>loginIdMap をセットします。
     * @param loginIdMap loginIdMap
     */
    public void setLoginIdMap(HashMap<String, String> loginIdMap) {
        loginIdMap__ = loginIdMap;
    }

    /**
     * コンストラクタ
     * @param error アクションエラー
     * @param con コネクション
     * @param updateFlg 上書きモード
     * @param mode 取り込みモード
     * @param insertFlg 作成モード
     * @param reqMdl RequestModel
     * @param canChangePassword パスワード変更区分
     */
     public UserCsvCheck(ActionErrors error,
                         int mode,
                         Connection con,
                         int updateFlg,
                         int insertFlg,
                         RequestModel reqMdl,
                         boolean canChangePassword
                         ) {
        setErrors(error);
        setMode(mode);
        setCon(con);
        setGroupIdMap(new HashMap<String, String>());
        setLoginIdMap(new HashMap<String, String>());
        setUpdateFlg(updateFlg);
        setinsertFlg(insertFlg);
        setReqMdl(reqMdl);
        canChangePassword__ = canChangePassword;
    }

    /**
     * <br>[機　能] CSVファイルのチェックを行なう
     * <br>[解　説]
     * <br>[備  考]
     *
     * @param csvFile 入力ファイル名
     * @return ture:エラー有 false:エラー無し
     * @throws Exception 実行時例外
     */
     public boolean isCsvDataOk(String csvFile) throws Exception {

         boolean ret = false;
         GsMessage gsMsg = new GsMessage(reqMdl__);
         //取込みファイル
         String textCaptureFile = gsMsg.getMessage("cmn.capture.file");
         //ファイル読込み
         readFile(new File(csvFile), Encoding.WINDOWS_31J);
         log__.debug("有効データ件数==" + getCount());

         ret = isErrorFlg();

         //有効データ無し
         if (getCount() == 0) {

             String eprefix = "inputFile.";
             ActionMessage msg =
                 new ActionMessage("search.notfound.data", textCaptureFile);
             StrutsUtil.addMessage(errors__, msg, eprefix + "search.notfound.data");
             ret = true;
         }
         return ret;
     }

   /**
    * <br>[機  能] csvファイル一行の処理
    * <br>[解  説]
    * <br>[備  考]
    *
    * @param num 行番号
    * @param lineStr 行データ
    * @throws Exception csv読込時例外
    * @see jp.co.sjts.util.csv.AbstractCsvRecordReader#processedLine(long, java.lang.String)
    */
    protected void processedLine(
                     long num,
                     String lineStr) throws Exception {
        GSValidateUser gsValidateUser = new GSValidateUser(reqMdl__);
        GsMessage gsMsg = new GsMessage(reqMdl__);

        //ユーザID
        String textUserId = gsMsg.getMessage("cmn.user.id");
        //取込みファイル
        String textCaptureFile = gsMsg.getMessage("cmn.capture.file");
        //CSV項目数
        String textCsvitems = gsMsg.getMessage("cmn.csv.number.items");
        //メールアドレスコメント１
        String textMailAdrComment1 = gsMsg.getMessage("cmn.mailaddress1.comment");
        //メールアドレスコメント2
        String textMailAdrComment2 = gsMsg.getMessage("mailaddress2.comment");
        //メールアドレスコメント3
        String textMailAdrComment3 = gsMsg.getMessage("mailaddress3.comment");
        //メールアドレス１公開フラグ
        String textMailPublicFlg1 = gsMsg.getMessage("user.src.21");
        //メールアドレス２公開フラグ
        String textMailPublicFlg2 = gsMsg.getMessage("user.src.22");
        //メールアドレス３公開フラグ
        String textMailPublicFlg3 = gsMsg.getMessage("user.src.23");
        //電話番号内線１
        String textTelNai1 = gsMsg.getMessage("user.src.39");
        //電話番号内線２
        String textTelNai2 = gsMsg.getMessage("user.src.40");
        //電話番号内線３
        String textTelNai3 = gsMsg.getMessage("user.src.41");
        //電話番号１公開フラグ
        String textTelPubFlg1 = gsMsg.getMessage("user.src.42");
        //電話番号２公開フラグ
        String textTelPubFlg2 = gsMsg.getMessage("user.src.43");
        //電話番号３公開フラグ
        String textTelPubFlg3 = gsMsg.getMessage("user.src.44");
        //電話番号コメント１
        String textTelComment1 = gsMsg.getMessage("user.src.36");
        //電話番号コメント２
        String textTelComment2 = gsMsg.getMessage("user.src.37");
        //電話番号コメント３
        String textTelComment3 = gsMsg.getMessage("user.src.38");
        //ＦＡＸ１公開フラグ
        String textFaxPubFlg1 = gsMsg.getMessage("user.src.13");
        //ＦＡＸ２公開フラグ
        String textFaxPubFlg2 = gsMsg.getMessage("user.src.14");
        //ＦＡＸ３公開フラグ
        String textFaxPubFlg3 = gsMsg.getMessage("user.src.15");
        //ＦＡＸコメント１
        String textFaxComment1 = gsMsg.getMessage("cmn.fax.comment1");
        //ＦＡＸコメント２
        String textFaxComment2 = gsMsg.getMessage("cmn.fax.comment2");
        //ＦＡＸコメント３
        String textFaxComment3 = gsMsg.getMessage("cmn.fax.comment3");
        //郵便番号公開フラグ
        String textPostPubFlg = gsMsg.getMessage("user.src.32");
        //都道府県公開フラグ
        String textTdfkPubFlg = gsMsg.getMessage("user.src.35");
        //住所１公開フラグ
        String textAddressPubFlg = gsMsg.getMessage("user.src.6");
        //住所２公開フラグ
        String textAddress2Open = gsMsg.getMessage("user.src.7");
        //生年月日公開フラグ
        String textBirthDdayFlg = gsMsg.getMessage("user.src.8");
        //行目
        String textLine = gsMsg.getMessage("cmn.line", new String[] {String.valueOf(num)});
        //行目の
        String textLine2 = gsMsg.getMessage("cmn.line2", new String[] {String.valueOf(num)});

        //ヘッダ文字列読み飛ばし
        if (num == 1) {
            return;
        }

        try {

            int j = 0;
            String buff;
            String eprefix = "inputFile.";
            int ecnt = errors__.size();
            CsvTokenizer stringTokenizer = new CsvTokenizer(lineStr, ",");

            log__.debug("項目数=" + stringTokenizer.length());
            //モードで処理を分岐
            if (mode__ == GSConstUser.MODE_NORMAL) {
                //通常モードの場合は50項目か49項目(備考はなくても良い)である必要がある
                if (stringTokenizer.length() != 54 && stringTokenizer.length() != 53) {
                        ActionMessage msg =
                        new ActionMessage(
                                "error.input.format.file",
                                textCaptureFile,
                                textCsvitems + "(" + textLine + ")");
                    StrutsUtil.addMessage(errors__, msg, eprefix + num + "error.input.format.file");
                } else {

                    // パスワードルール設定取得
                    int coe = GSConstMain.PWC_COEKBN_OFF;
                    int digit = GSConstMain.DEFAULT_DIGIT;
                    int uidPswdKbn = GSConstMain.PWC_UIDPSWDKBN_OFF;

                    CmnPswdConfDao dao = new CmnPswdConfDao(con__);
                    CmnPswdConfModel model = dao.select();

                    if (model != null) {
                        coe = model.getPwcCoe();
                        digit = model.getPwcDigit();
                        uidPswdKbn = model.getPwcUidPswd();
                    }
                    // ユーザログインID
                    String usrLgid = "";

                    CmnUsrmDao usrmDao = new CmnUsrmDao(con__);
                    GSValidateUserCsv gsValidateUserCsv = new GSValidateUserCsv(reqMdl__);

                    while (stringTokenizer.hasMoreTokens()) {
                        j++;
                        buff = stringTokenizer.nextToken();

                        //ユーザID
                        if (j == 1) {
                            gsValidateUserCsv.validateCsvUserId(errors__, buff, num);

                            if (getUpdateFlg() != GSConstUser.IMPORT_MODE_UPDATE) {
                                gsValidateUserCsv.validateCsvUserIdDouble(
                                         errors__, -1, buff, num, con__);
                            } else {
                                if (usrmDao.existLoginidEdit(-1, buff)) {
                                     overlabCount__++;
                                }
                            }

                            //CSVファイル内重複チェック
                            if (!StringUtil.isNullZeroString(buff)) {
                                if (loginIdMap__.containsKey(buff)) {
                                    String dupIndex = (String) loginIdMap__.get(buff);
                                    //行目の
                                    String dupLine = gsMsg.getMessage("cmn.line2",
                                            new String[] {dupIndex});

                                    ActionMessage msg = new ActionMessage(
                                        "error.select.dup.list2",
                                        textLine2 + textUserId,
                                        dupLine + textUserId);
                                    StrutsUtil.addMessage(
                                        errors__,
                                        msg,
                                        "userid." + num + "error.select.dup.list2");
                                } else {
                                    loginIdMap__.put(buff, String.valueOf(num));
                                }
                            }
                            usrLgid = buff;
                        }
                        //パスワード
                        if (j == 2) {
                            if (canChangePassword__) {
                                gsValidateUserCsv.validatePassword(errors__, buff, usrLgid,
                                        num, coe, digit, uidPswdKbn);
                            }
                        }
                        //社員・職員番号
                        if (j == 3) {
                            gsValidateUserCsv.validateCsvShainNo(errors__, buff, num);
                        }
                        //姓
                        if (j == 4) {
                            gsValidateUserCsv.validateCsvUserNameSei(errors__, buff, num);
                        }
                        //名
                        if (j == 5) {
                            gsValidateUserCsv.validateCsvUserNameMei(errors__, buff, num);
                        }
                        //姓カナ
                        if (j == 6) {
                            gsValidateUserCsv.validateCsvUserNameSeiKana(
                                                                    errors__, buff, num);
                        }
                        //名カナ
                        if (j == 7) {
                            gsValidateUserCsv.validateCsvUserNameMeiKana(errors__, buff, num);
                        }
                        //所属
                        if (j == 8) {
                            gsValidateUserCsv.validateCsvSyozoku(errors__, buff, num);
                        }
                        //役職
                        if (j == 9) {
                            //役職
                            gsValidateUserCsv.validateCsvYakushoku(errors__, buff, num, getCon());
                        }
                        //ソートキー1
                        if (j == 10) {
                            //ソートキー1
                            gsValidateUserCsv.validateCsvSortKey1(errors__, buff, num);
                        }

                        //ソートキー2
                        if (j == 11) {
                            //ソートキー2
                            gsValidateUserCsv.validateCsvSortKey2(errors__, buff, num);
                        }

                        //性別
                        if (j == 12) {
                            //性別
                            gsValidateUserCsv.validateCsvSeibetu(errors__, buff, num);
                        }

                        //入社年月日
                        if (j == 13) {
                            //入社年月日
                            gsValidateUserCsv.validateCsvEntranceDate(
                                                                    errors__, buff, num);
                        }

                        //生年月日
                        if (j == 14) {
                            gsValidateUserCsv.validateCsvBirthDate(errors__, buff, num);
                        }
                        //生年月日公開フラグ
                        if (j == 15) {
                            gsValidateUserCsv.validateCsvKoukaiFlg(
                                    errors__, String.valueOf(NullDefault.getInt(buff, 0)),
                                    textBirthDdayFlg, num);
                        }
                        //メールアドレス１
                        if (j == 16) {
                            gsValidateUserCsv.validateCsvMail(errors__, buff, 1, num);
                        }
                        //メールアドレスコメント１
                        if (j == 17) {
                            gsValidateUserCsv.validateCmt(
                                    errors__, buff, textMailAdrComment1, num);
                        }
                        //メールアドレス１公開フラグ
                        if (j == 18) {
                            gsValidateUserCsv.validateCsvKoukaiFlg(
                                    errors__, String.valueOf(NullDefault.getInt(buff, 0)),
                                    textMailPublicFlg1, num);
                        }
                        //メールアドレス２
                        if (j == 19) {
                            gsValidateUserCsv.validateCsvMail(errors__, buff, 2, num);
                        }
                        //メールアドレスコメント２
                        if (j == 20) {
                            gsValidateUserCsv.validateCmt(
                                    errors__, buff, textMailAdrComment2, num);
                        }
                        //メールアドレス２公開フラグ
                        if (j == 21) {
                            gsValidateUserCsv.validateCsvKoukaiFlg(
                                    errors__, String.valueOf(NullDefault.getInt(buff, 0)),
                                    textMailPublicFlg2, num);
                        }
                        //メールアドレス３
                        if (j == 22) {
                            gsValidateUserCsv.validateCsvMail(errors__, buff, 3, num);
                        }
                        //メールアドレスコメント３
                        if (j == 23) {
                            gsValidateUserCsv.validateCmt(
                                    errors__, buff, textMailAdrComment3, num);
                        }
                        //メールアドレス３公開フラグ
                        if (j == 24) {
                            gsValidateUserCsv.validateCsvKoukaiFlg(
                                    errors__, String.valueOf(NullDefault.getInt(buff, 0)),
                                    textMailPublicFlg3, num);
                        }
                        //郵便番号
                        if (j == 25) {
                            gsValidateUser.validateCsvPostNum(errors__, buff, num);
                        }
                        //郵便番号公開フラグ
                        if (j == 26) {
                            gsValidateUserCsv.validateCsvKoukaiFlg(
                                errors__, String.valueOf(NullDefault.getInt(buff, 0)),
                                textPostPubFlg, num);
                        }
                        //都道府県コード
                        if (j == 27) {
                            gsValidateUserCsv.validateCsvTdfk(errors__, buff, num, con__);
                        }
                        //都道府県公開フラグ
                        if (j == 28) {
                            gsValidateUserCsv.validateCsvKoukaiFlg(
                                errors__, String.valueOf(NullDefault.getInt(buff, 0)),
                                textTdfkPubFlg, num);
                        }
                        //住所１
                        if (j == 29) {
                            gsValidateUserCsv.validateCsvAddress(errors__, buff, 1, num);
                        }
                        //住所１公開フラグ
                        if (j == 30) {
                            gsValidateUserCsv.validateCsvKoukaiFlg(
                                errors__, String.valueOf(NullDefault.getInt(buff, 0)),
                                textAddressPubFlg, num);
                        }
                        //住所２
                        if (j == 31) {
                            gsValidateUserCsv.validateCsvAddress(errors__, buff, 2, num);
                        }
                        //住所２公開フラグ
                        if (j == 32) {
                            gsValidateUserCsv.validateCsvKoukaiFlg(
                                errors__, String.valueOf(NullDefault.getInt(buff, 0)),
                                textAddress2Open, num);
                        }
                        //電話番号１
                        if (j == 33) {
                            gsValidateUserCsv.validateCsvTel(errors__, buff, 1, num);
                        }
                        //電話番号内線１
                        if (j == 34) {
                            gsValidateUserCsv.validateNaisen(
                                errors__, buff, textTelNai1, num);
                        }
                        //電話番号コメント１
                        if (j == 35) {
                            gsValidateUserCsv.validateCmt(
                                errors__, buff, textTelComment1, num);
                        }
                        //電話番号１公開フラグ
                        if (j == 36) {
                            gsValidateUserCsv.validateCsvKoukaiFlg(
                                errors__, String.valueOf(NullDefault.getInt(buff, 0)),
                                textTelPubFlg1, num);
                        }
                        //電話番号２
                        if (j == 37) {
                            gsValidateUserCsv.validateCsvTel(errors__, buff, 2, num);
                        }
                        //電話番号内線２
                        if (j == 38) {
                            gsValidateUserCsv.validateNaisen(
                                errors__, buff, textTelNai2, num);
                        }
                        //電話番号コメント２
                        if (j == 39) {
                            gsValidateUserCsv.validateCmt(
                                errors__, buff, textTelComment2, num);
                        }
                        //電話番号２公開フラグ
                        if (j == 40) {
                            gsValidateUserCsv.validateCsvKoukaiFlg(
                                errors__, String.valueOf(NullDefault.getInt(buff, 0)),
                                textTelPubFlg2, num);
                        }
                        //電話番号３
                        if (j == 41) {
                            gsValidateUserCsv.validateCsvTel(errors__, buff, 3, num);
                        }
                        //電話番号内線３
                        if (j == 42) {
                            gsValidateUserCsv.validateNaisen(
                                errors__, buff, textTelNai3, num);
                        }
                        //電話番号コメント３
                        if (j == 43) {
                            gsValidateUserCsv.validateCmt(
                                errors__, buff, textTelComment3, num);
                        }
                        //電話番号３公開フラグ
                        if (j == 44) {
                            gsValidateUserCsv.validateCsvKoukaiFlg(
                                errors__, String.valueOf(NullDefault.getInt(buff, 0)),
                                textTelPubFlg3, num);
                        }
                        //FAX１
                        if (j == 45) {
                            gsValidateUserCsv.validateCsvTel(errors__, buff, 4, num);
                        }
                        //ＦＡＸコメント１
                        if (j == 46) {
                            gsValidateUserCsv.validateCmt(
                                errors__, buff, textFaxComment1, num);
                        }
                        //FAX１公開フラグ
                        if (j == 47) {
                            gsValidateUserCsv.validateCsvKoukaiFlg(
                                errors__, String.valueOf(NullDefault.getInt(buff, 0)),
                                textFaxPubFlg1, num);
                        }
                        //FAX２
                        if (j == 48) {
                            gsValidateUserCsv.validateCsvTel(errors__, buff, 5, num);
                        }
                        //ＦＡＸコメント２
                        if (j == 49) {
                            gsValidateUserCsv.validateCmt(
                                errors__, buff, textFaxComment2, num);
                        }
                        //FAX２公開フラグ
                        if (j == 50) {
                            gsValidateUserCsv.validateCsvKoukaiFlg(
                                errors__, String.valueOf(NullDefault.getInt(buff, 0)),
                                textFaxPubFlg2, num);
                        }
                        //FAX３
                        if (j == 51) {
                            gsValidateUserCsv.validateCsvTel(errors__, buff, 6, num);
                        }
                        //ＦＡＸコメント３
                        if (j == 52) {
                            gsValidateUserCsv.validateCmt(
                                errors__, buff, textFaxComment3, num);
                        }
                        //FAX３公開フラグ
                        if (j == 53) {
                            gsValidateUserCsv.validateCsvKoukaiFlg(
                                errors__, String.valueOf(NullDefault.getInt(buff, 0)),
                                textFaxPubFlg3, num);
                        }
                        //備考
                        if (j == 54) {
                            //備考
                            gsValidateUserCsv.validateCsvUserComment(errors__, buff, num);
                        }
                    }
                }
            } else {
            //グループ一括モードの場合は56項目か55項目(備考はなくても良い)である必要がある
                if (stringTokenizer.length() != 56 && stringTokenizer.length() != 55) {
                    ActionMessage msg =
                        new ActionMessage(
                                "error.input.format.file",
                                textCaptureFile,
                                textCsvitems + "(" + textLine + ")");
                    StrutsUtil.addMessage(errors__, msg, eprefix + num + "error.input.format.file");
                } else {

                    // パスワードルール設定取得
                    int coe = GSConstMain.PWC_COEKBN_OFF;
                    int digit = GSConstMain.DEFAULT_DIGIT;
                    int uidPswdKbn = GSConstMain.PWC_UIDPSWDKBN_OFF;

                    CmnPswdConfDao dao = new CmnPswdConfDao(con__);
                    CmnPswdConfModel model = dao.select();

                    if (model != null) {
                        coe = model.getPwcCoe();
                        digit = model.getPwcDigit();
                        uidPswdKbn = model.getPwcUidPswd();
                        }
                    // ユーザログインID
                    String usrLgid = "";

                    CmnUsrmDao usrmDao = new CmnUsrmDao(con__);
                    GSValidateUserCsv gsValidateUserCsv = new GSValidateUserCsv(reqMdl__);
                    while (stringTokenizer.hasMoreTokens()) {
                        j++;
                        buff = stringTokenizer.nextToken();
                        //グループID
                        if (j == 1) {
                            int errorSize = errors__.size();
                            gsValidateUserCsv.validateCsvGroupId(errors__, buff, num);
                            if (errorSize == errors__.size()) {
                                if (getinsertFlg() != GSConstUser.IMPORT_MODE_CREATE
                                   || buff.equals(GSConstUser.USER_KANRI_ID)) {
                                    gsValidateUserCsv.validateCsvGroupExist(errors__,
                                                                                buff,
                                                                                 num,
                                                                               con__);
                                }
                            }
                        }
                        //グループ名
                        if (j == 2) {
                            gsValidateUserCsv.validateCsvGroupName(errors__, buff, num);
                        }
                        //ユーザID
                        if (j == 3) {
                            gsValidateUserCsv.validateCsvUserId(errors__, buff, num);

                            if (getUpdateFlg() != GSConstUser.IMPORT_MODE_UPDATE) {
                                gsValidateUserCsv.validateCsvUserIdDouble(
                                        errors__, -1, buff, num, con__);
                            } else {
                                if (usrmDao.existLoginidEdit(-1, buff)) {
                                    overlabCount__++;
                                }
                            }

                            //CSVファイル内重複チェック
                            if (!StringUtil.isNullZeroString(buff)) {
                                if (loginIdMap__.containsKey(buff)) {
                                    String dupIndex = (String) loginIdMap__.get(buff);

                                    ActionMessage msg = new ActionMessage(
                                        "error.select.dup.list2",
                                        num + textLine2 + textUserId,
                                        dupIndex + textLine2 + textUserId);
                                    StrutsUtil.addMessage(
                                        errors__,
                                        msg,
                                        "userid." + num + "error.select.dup.list2");
                                } else {
                                    loginIdMap__.put(buff, String.valueOf(num));
                                }
                            }
                            usrLgid = buff;
                        }
                        //パスワード
                        if (j == 4) {
                            if (canChangePassword__) {
                                gsValidateUserCsv.validatePassword(errors__, buff, usrLgid,
                                        num, coe, digit, uidPswdKbn);
                            }
                        }
                        //社員・職員番号
                        if (j == 5) {
                            gsValidateUserCsv.validateCsvShainNo(errors__, buff, num);
                        }
                        //姓
                        if (j == 6) {
                            gsValidateUserCsv.validateCsvUserNameSei(errors__, buff, num);
                        }
                        //名
                        if (j == 7) {
                            gsValidateUserCsv.validateCsvUserNameMei(errors__, buff, num);
                        }
                        //姓カナ
                        if (j == 8) {
                            gsValidateUserCsv.validateCsvUserNameSeiKana(
                                                                errors__, buff, num);
                        }
                        //名カナ
                        if (j == 9) {
                            gsValidateUserCsv.validateCsvUserNameMeiKana(errors__, buff, num);
                        }
                        //所属
                        if (j == 10) {
                            gsValidateUserCsv.validateCsvSyozoku(errors__, buff, num);
                        }
                        //役職
                        if (j == 11) {
                            //役職
                            gsValidateUserCsv.validateCsvYakushoku(errors__, buff, num, getCon());
                        }
                        //ソートキー1
                        if (j == 12) {
                            //ソートキー1
                            gsValidateUserCsv.validateCsvSortKey1(errors__, buff, num);
                        }

                        //ソートキー2
                        if (j == 13) {
                            //ソートキー2
                            gsValidateUserCsv.validateCsvSortKey2(errors__, buff, num);
                        }

                        //性別
                        if (j == 14) {
                            //性別
                            gsValidateUserCsv.validateCsvSeibetu(errors__, buff, num);
                        }

                        //入社年月日
                        if (j == 15) {
                            //入社年月日
                            gsValidateUserCsv.validateCsvEntranceDate(
                                                                    errors__, buff, num);
                        }
                        //生年月日
                        if (j == 16) {
                            gsValidateUserCsv.validateCsvBirthDate(errors__, buff, num);
                        }
                        //生年月日公開フラグ
                        if (j == 17) {
                            gsValidateUserCsv.validateCsvKoukaiFlg(
                                    errors__, String.valueOf(NullDefault.getInt(buff, 0)),
                                    textBirthDdayFlg, num);
                        }
                        //メールアドレス１
                        if (j == 18) {
                            gsValidateUserCsv.validateCsvMail(errors__, buff, 1, num);
                        }
                        //メールアドレスコメント１
                        if (j == 19) {
                            gsValidateUserCsv.validateCmt(
                                    errors__, buff, textMailAdrComment1, num);
                        }
                        //メールアドレス１公開フラグ
                        if (j == 20) {
                            gsValidateUserCsv.validateCsvKoukaiFlg(
                                    errors__, String.valueOf(NullDefault.getInt(buff, 0)),
                                    textMailPublicFlg1, num);
                        }
                        //メールアドレス２
                        if (j == 21) {
                            gsValidateUserCsv.validateCsvMail(errors__, buff, 2, num);
                        }
                        //メールアドレスコメント２
                        if (j == 22) {
                            gsValidateUserCsv.validateCmt(
                                    errors__, buff, textMailAdrComment2, num);
                        }
                        //メールアドレス２公開フラグ
                        if (j == 23) {
                            gsValidateUserCsv.validateCsvKoukaiFlg(
                                    errors__, String.valueOf(NullDefault.getInt(buff, 0)),
                                    textMailPublicFlg2, num);
                        }
                        //メールアドレス３
                        if (j == 24) {
                            gsValidateUserCsv.validateCsvMail(errors__, buff, 3, num);
                        }
                        //メールアドレスコメント３
                        if (j == 25) {
                            gsValidateUserCsv.validateCmt(
                                    errors__, buff, textMailAdrComment3, num);
                        }
                        //メールアドレス３公開フラグ
                        if (j == 26) {
                            gsValidateUserCsv.validateCsvKoukaiFlg(
                                    errors__, String.valueOf(NullDefault.getInt(buff, 0)),
                                    textMailPublicFlg3, num);
                        }
                        //郵便番号
                        if (j == 27) {
                            gsValidateUser.validateCsvPostNum(errors__, buff, num);
                        }
                        //郵便番号公開フラグ
                        if (j == 28) {
                            gsValidateUserCsv.validateCsvKoukaiFlg(
                                    errors__, String.valueOf(NullDefault.getInt(buff, 0)),
                                    textPostPubFlg, num);
                        }
                        //都道府県コード
                        if (j == 29) {
                            gsValidateUserCsv.validateCsvTdfk(errors__, buff, num, con__);
                        }
                        //都道府県公開フラグ
                        if (j == 30) {
                            gsValidateUserCsv.validateCsvKoukaiFlg(
                                    errors__, String.valueOf(NullDefault.getInt(buff, 0)),
                                    textTdfkPubFlg, num);
                        }
                        //住所１
                        if (j == 31) {
                            gsValidateUserCsv.validateCsvAddress(errors__, buff, 1, num);
                        }
                        //住所１公開フラグ
                        if (j == 32) {
                            gsValidateUserCsv.validateCsvKoukaiFlg(
                                    errors__, String.valueOf(NullDefault.getInt(buff, 0)),
                                    textAddressPubFlg, num);
                        }
                        //住所２
                        if (j == 33) {
                            gsValidateUserCsv.validateCsvAddress(errors__, buff, 2, num);
                        }
                        //住所２公開フラグ
                        if (j == 34) {
                            gsValidateUserCsv.validateCsvKoukaiFlg(
                                    errors__, String.valueOf(NullDefault.getInt(buff, 0)),
                                    textAddress2Open, num);
                        }
                        //電話番号１
                        if (j == 35) {
                            gsValidateUserCsv.validateCsvTel(errors__, buff, 1, num);
                        }
                        //電話番号内線１
                        if (j == 36) {
                            gsValidateUserCsv.validateNaisen(
                                    errors__, buff, textTelNai1, num);
                        }
                        //電話番号コメント１
                        if (j == 37) {
                            gsValidateUserCsv.validateCmt(
                                    errors__, buff, textTelComment1, num);
                        }
                        //電話番号１公開フラグ
                        if (j == 38) {
                            gsValidateUserCsv.validateCsvKoukaiFlg(
                                    errors__, String.valueOf(NullDefault.getInt(buff, 0)),
                                    textTelPubFlg1, num);
                        }
                        //電話番号２
                        if (j == 39) {
                            gsValidateUserCsv.validateCsvTel(errors__, buff, 2, num);
                        }
                        //電話番号内線２
                        if (j == 40) {
                            gsValidateUserCsv.validateNaisen(
                                    errors__, buff, textTelNai2, num);
                        }
                        //電話番号コメント２
                        if (j == 41) {
                            gsValidateUserCsv.validateCmt(
                                    errors__, buff, textTelComment2, num);
                        }
                        //電話番号２公開フラグ
                        if (j == 42) {
                            gsValidateUserCsv.validateCsvKoukaiFlg(
                                    errors__, String.valueOf(NullDefault.getInt(buff, 0)),
                                    textTelPubFlg2, num);
                        }
                        //電話番号３
                        if (j == 43) {
                            gsValidateUserCsv.validateCsvTel(errors__, buff, 3, num);
                        }
                        //電話番号内線３
                        if (j == 44) {
                            gsValidateUserCsv.validateNaisen(
                                    errors__, buff, textTelNai3, num);
                        }
                        //電話番号コメント３
                        if (j == 45) {
                            gsValidateUserCsv.validateCmt(
                                    errors__, buff, textTelComment3, num);
                        }
                        //電話番号３公開フラグ
                        if (j == 46) {
                            gsValidateUserCsv.validateCsvKoukaiFlg(
                                    errors__, String.valueOf(NullDefault.getInt(buff, 0)),
                                    textTelPubFlg3, num);
                        }
                        //FAX１
                        if (j == 47) {
                            gsValidateUserCsv.validateCsvTel(errors__, buff, 4, num);
                        }
                        //ＦＡＸコメント１
                        if (j == 48) {
                            gsValidateUserCsv.validateCmt(
                                    errors__, buff, textFaxComment1, num);
                        }
                        //FAX１公開フラグ
                        if (j == 49) {
                            gsValidateUserCsv.validateCsvKoukaiFlg(
                                    errors__, String.valueOf(NullDefault.getInt(buff, 0)),
                                    textFaxPubFlg1, num);
                        }
                        //FAX２
                        if (j == 50) {
                            gsValidateUserCsv.validateCsvTel(errors__, buff, 5, num);
                        }
                        //ＦＡＸコメント２
                        if (j == 51) {
                            gsValidateUserCsv.validateCmt(
                                    errors__, buff, textFaxComment2, num);
                        }
                        //FAX２公開フラグ
                        if (j == 52) {
                            gsValidateUserCsv.validateCsvKoukaiFlg(
                                    errors__, String.valueOf(NullDefault.getInt(buff, 0)),
                                    textFaxPubFlg2, num);
                        }
                        //FAX３
                        if (j == 53) {
                            gsValidateUserCsv.validateCsvTel(errors__, buff, 6, num);
                        }
                        //ＦＡＸコメント３
                        if (j == 54) {
                            gsValidateUserCsv.validateCmt(
                                    errors__, buff, textFaxComment3, num);
                        }
                        //FAX３公開フラグ
                        if (j == 55) {
                            gsValidateUserCsv.validateCsvKoukaiFlg(
                                    errors__, String.valueOf(NullDefault.getInt(buff, 0)),
                                    textFaxPubFlg3, num);
                        }
                        //備考
                        if (j == 56) {
                            //備考
                            gsValidateUserCsv.validateCsvUserComment(errors__, buff, num);
                        }
                    }
                }
            }

            //エラー有り
            if (ecnt < errors__.size()) {
                //エラー存在フラグON
                setErrorFlg(true);
            } else {
                //明細データ以降
                if (num >= 2) {
                    //有効データ件数カウントアップ
                    int cnt = getCount();
                    cnt += 1;
                    setCount(cnt);
                }
            }

        } catch (Exception e) {
            log__.error("CSVファイル読込み時例外");
            throw e;
        }
    }

    /**
     * <p>updateFlg を取得します。
     * @return updateFlg
     */
    public int getUpdateFlg() {
        return updateFlg__;
    }

    /**
     * <p>updateFlg をセットします。
     * @param updateFlg updateFlg
     */
    public void setUpdateFlg(int updateFlg) {
        updateFlg__ = updateFlg;
    }
    /**
     * <p>insertFlg を取得します。
     * @return insertFlg
     */
    public int getinsertFlg() {
        return insertFlg__;
    }

    /**
     * <p>insertFlg をセットします。
     * @param insertFlg insertFlg
     */
    public void setinsertFlg(int insertFlg) {
        insertFlg__ = insertFlg;
    }

    /**
     * @return overlabCount
     */
    public int getOverlabCount() {
        return overlabCount__;
    }

    /**
     * @param overlabCount 設定する overlabCount
     */
    public void setOverlabCount(int overlabCount) {
        overlabCount__ = overlabCount;
    }

    /**
     * <p>reqMdl を取得します。
     * @return reqMdl
     */
    public RequestModel getReqMdl() {
        return reqMdl__;
    }

    /**
     * <p>reqMdl をセットします。
     * @param reqMdl reqMdl
     */
    public void setReqMdl(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }
}