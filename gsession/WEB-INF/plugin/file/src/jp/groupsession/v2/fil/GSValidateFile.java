package jp.groupsession.v2.fil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.GSValidateUtil;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.fil.dao.FileCabinetDao;
import jp.groupsession.v2.fil.model.FileAconfModel;
import jp.groupsession.v2.fil.model.FileCabinetModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

/**
 * <br>[機  能] ファイル管理の入力チェックを行うクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class GSValidateFile {
    /**
     * <br>[機  能] テキストボックス（オールタイプ）の入力チェックを行う（汎用）
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param target チェック対象
     * @param targetName チェック対象名称
     * @param maxLength 最大入力文字数
     * @param checkNoInput 未入力チェック true:チェックする false:チェックしない
     * @return チェック結果 true :エラー有り false :エラー無し
     */
    public static boolean validateTextBoxInput(
            ActionErrors errors,
            String target,
            String targetName,
            int maxLength,
            boolean checkNoInput) {
        ActionMessage msg = null;

        String fieldFix = targetName + ".";

        if (StringUtil.isNullZeroString(target)) {
            if (!checkNoInput) {
                return false;
            }

            //未入力チェック
            msg = new ActionMessage("error.input.required.text", targetName);
            StrutsUtil.addMessage(
                    errors, msg, fieldFix + "error.input.required.text");
            return true;
        }

        //スペースのみチェック
        if (ValidateUtil.isSpace(target)) {
            msg = new ActionMessage("error.input.spase.only", targetName);
            StrutsUtil.addMessage(errors, msg, fieldFix + "error.input.spase.only");
            return true;
        }
        //先頭スペースチェック
        if (ValidateUtil.isSpaceStart(target)) {
            msg = new ActionMessage("error.input.spase.start", targetName);
            StrutsUtil.addMessage(errors, msg, fieldFix + "error.input.spase.start");
            return true;
        }
        //JIS第2水準チェック
        if (!GSValidateUtil.isGsJapaneaseString(target)) {
            //利用不可能な文字を入力した場合
            String nstr = GSValidateUtil.getNotGsJapaneaseString(target);
            msg = new ActionMessage("error.input.njapan.text", targetName, nstr);
            StrutsUtil.addMessage(errors, msg, fieldFix + "error.input.njapan.text");
            return true;
        }

        if (target.length() > maxLength) {
            //MAX桁チェック
            msg = new ActionMessage("error.input.length.text", targetName, maxLength);
            StrutsUtil.addMessage(
                    errors, msg, fieldFix + "error.input.length.text");
            return true;
        }

        //入力エラー無し
        return false;
    }

    /**
     * <br>[機  能] テキストボックス（数字タイプ）の入力チェックを行う（汎用）
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param target チェック対象
     * @param targetName チェック対象名称
     * @param maxLength 最大入力文字数
     * @param checkNoInput 未入力チェック true:チェックする false:チェックしない
     * @return チェック結果 true :エラー有り false :エラー無し
     */
    public static boolean validateTextBoxInputNum(
            ActionErrors errors,
            String target,
            String targetName,
            int maxLength,
            boolean checkNoInput) {
        ActionMessage msg = null;

        String fieldFix = targetName + ".";

        if (StringUtil.isNullZeroString(target)) {
            if (!checkNoInput) {
                return false;
            }

            //未入力チェック
            msg = new ActionMessage("error.input.required.text", targetName);
            StrutsUtil.addMessage(
                    errors, msg, fieldFix + "error.input.required.text");
            return true;
        }

        if (!ValidateUtil.isNumber(target)) {
            //半角数字チェック
            msg = new ActionMessage("error.input.number.text", targetName);
            StrutsUtil.addMessage(errors, msg, fieldFix + "error.input.number.text");
            return true;
        }

        if (target.length() > maxLength) {
            //MAX桁チェック
            msg = new ActionMessage("error.input.length.text", targetName, maxLength);
            StrutsUtil.addMessage(errors, msg, fieldFix + "error.input.length.text");
            return true;
        }

        //入力エラー無し
        return false;
    }

    /**
     * <br>[機  能] テキストボックス（数字タイプ）の入力チェックを行う（汎用）
     * <br>[解  説] 最大値、最小値の判定も行う
     * <br>[備  考]
     * @param errors ActionErrors
     * @param target チェック対象
     * @param targetName チェック対象名称
     * @param min 最小値
     * @param max 最大値
     * @param checkNoInput 未入力チェック true:チェックする false:チェックしない
     * @return チェック結果 true :エラー有り false :エラー無し
     */
    public static boolean validateTextBoxInputNumLenge(
            ActionErrors errors,
            String target,
            String targetName,
            int min,
            int max,
            boolean checkNoInput) {
        ActionMessage msg = null;

        String fieldFix = targetName + ".";

        if (StringUtil.isNullZeroString(target)) {
            if (!checkNoInput) {
                return false;
            }

            //未入力チェック
            msg = new ActionMessage("error.input.required.text", targetName);
            StrutsUtil.addMessage(
                    errors, msg, fieldFix + "error.input.required.text");
            return true;
        }

        if (!ValidateUtil.isNumber(target)) {
            //半角数字チェック
            msg = new ActionMessage("error.input.number.text", targetName);
            StrutsUtil.addMessage(errors, msg, fieldFix + "error.input.number.text");
            return true;
        }

        if (NullDefault.getInt(target, -1) < min || NullDefault.getInt(target, -1) > max) {
            //入力範囲チェック
            msg = new ActionMessage("error.input.lenge", targetName, min, max);
            StrutsUtil.addMessage(errors, msg, fieldFix + "error.input.lenge");
            return true;
        }

        //入力エラー無し
        return false;
    }

    /**
     * <br>[機  能] テキストボックス（英数字タイプ）の入力チェックを行う（汎用）
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param target チェック対象
     * @param targetName チェック対象名称
     * @param maxLength 最大入力文字数
     * @param checkNoInput 未入力チェック true:チェックする false:チェックしない
     * @param req リクエスト
     * @return チェック結果 true :エラー有り false :エラー無し
     */
    public static boolean validateTextBoxInputArphaNum(
            ActionErrors errors,
            String target,
            String targetName,
            int maxLength,
            boolean checkNoInput,
            HttpServletRequest req) {
        ActionMessage msg = null;

        String fieldFix = targetName + ".";
        GsMessage gsMsg = new GsMessage();
        String textHankakuEiji = gsMsg.getMessage(req, "cmn.english");

        if (StringUtil.isNullZeroString(target)) {
            if (!checkNoInput) {
                return false;
            }

            //未入力チェック
            msg = new ActionMessage("error.input.required.text", targetName);
            StrutsUtil.addMessage(
                    errors, msg, fieldFix + "error.input.required.text");
            return true;
        }

        if (!ValidateUtil.isAlpha(target)) {
            //半角英字チェック
            msg = new ActionMessage("error.input.comp.text", targetName, textHankakuEiji);
            StrutsUtil.addMessage(errors, msg, fieldFix + "error.input.comp.text");
            return true;
        }

        if (target.length() > maxLength) {
            //MAX桁チェック
            msg = new ActionMessage("error.input.length.text", targetName, maxLength);
            StrutsUtil.addMessage(errors, msg, fieldFix + "error.input.length.text");
            return true;
        }

        //入力エラー無し
        return false;
    }

    /**
     * <br>[機  能] テキストエリアの入力チェックを行う（汎用）
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param target チェック対象
     * @param targetName チェック対象名称
     * @param maxLength 最大入力文字数
     * @param checkNoInput 未入力チェック true:チェックする false:チェックしない
     * @return チェック結果 true :エラー有り false :エラー無し
     */
    public static boolean validateTextarea(
            ActionErrors errors,
            String target,
            String targetName,
            int maxLength,
            boolean checkNoInput) {
        ActionMessage msg = null;

        String fieldFix = targetName + ".";

        if (StringUtil.isNullZeroString(target)) {
            if (!checkNoInput) {
                return false;
            }

            //未入力チェック
            msg = new ActionMessage("error.input.required.text", targetName);
            StrutsUtil.addMessage(
                    errors, msg, fieldFix + "error.input.required.text");
            return true;
        }
        //スペース・改行のみチェック
        if (ValidateUtil.isSpaceOrKaigyou(target)) {
            msg = new ActionMessage("error.input.spase.cl.only", targetName);
            StrutsUtil.addMessage(errors, msg, fieldFix + "error.input.spase.cl.only");
            return true;
        }
        //JIS第2水準チェック
        if (!GSValidateUtil.isGsJapaneaseStringTextArea(target)) {
            //利用不可能な文字を入力した場合
            String nstr = GSValidateUtil.getNotGsJapaneaseStringTextArea(target);
            msg = new ActionMessage("error.input.njapan.text", targetName, nstr);
            StrutsUtil.addMessage(errors, msg, fieldFix + "error.input.njapan.text");
            return true;
        }

        if (target.length() > maxLength) {
            //MAX桁チェック
            msg = new ActionMessage("error.input.length.text", targetName, maxLength);
            StrutsUtil.addMessage(
                    errors, msg, fieldFix + "error.input.length.text");
            return true;
        }

        //入力エラー無し
        return false;
    }

    /**
     * <br>[機  能] 検索ソート順の入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param sortKey1 検査ソートKey1
     * @param sortKey2 検査ソートKey2
     * @param sortKeyName ソートキー名称
     * @return true: エラーあり false: エラーなし
     */
    public static boolean validateSearchSortOrder(
            ActionErrors errors,
            String sortKey1,
            String sortKey2,
            String sortKeyName) {
        ActionMessage msg = null;

        String fieldFix = sortKeyName + ".";

        if (sortKey1.equals(sortKey2)) {
            //同一キー指定チェック
            msg = new ActionMessage("error.select.dup.list", sortKeyName);
            StrutsUtil.addMessage(errors, msg, fieldFix + "error.select.dup.list");
            return true;
        }

        return false;
    }

    /**
     * <br>[機  能] 対象の入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param req リクエスト
     * @param searchTarget1 対象
     * @param searchTarget2 対象
     * @return true: エラーあり false: エラーなし
     */
    public static boolean validateTarget(
        ActionErrors errors,
        HttpServletRequest req,
        String searchTarget1,
        String searchTarget2) {

        ActionMessage msg = null;

        if (!(searchTarget1.equals(String.valueOf(GSConstFile.GET_TARGET_FOLDER)))
                && !(searchTarget2.equals(String.valueOf(GSConstFile.GET_TARGET_FILE)))) {
            //未選択の場合エラー
            GsMessage gsMsg = new GsMessage();
            String textTaisyou = gsMsg.getMessage(req, "cmn.target");
            msg = new ActionMessage(
                    "error.select.required.text", textTaisyou);
            StrutsUtil.addMessage(errors, msg, "target1");
            return true;
        }

        return false;
    }

    /**
     * <br>[機  能] 検索対象の入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param searchTarget1 検索対象
     * @param searchTarget2 検索対象
     * @param searchTarget3 検索対象
     * @param req リクエスト
     * @return true: エラーあり false: エラーなし
     */
    public static boolean validateSearchTarget(
        ActionErrors errors,
        HttpServletRequest req,
        String searchTarget1,
        String searchTarget2,
        String searchTarget3) {

        ActionMessage msg = null;

        if (!(searchTarget1.equals(String.valueOf(GSConstFile.KEYWORD_TARGET_NAME)))
                && !(searchTarget2.equals(String.valueOf(GSConstFile.KEYWORD_TARGET_BIKO)))
                && !(searchTarget3.equals(String.valueOf(GSConstFile.KEYWORD_TARGET_TEXT)))) {
            //未選択の場合エラー

            GsMessage gsMsg = new GsMessage();
            String textSerachTarget = gsMsg.getMessage(req, "cmn.search2");

            msg = new ActionMessage(
                    "error.select.required.text", textSerachTarget);
            StrutsUtil.addMessage(errors, msg, "target2");
            return true;
        }

        return false;
    }

   /**
    *
    *<br>[機  能]チェックボックスが一つ以上選択されているか
    *<br>[解  説]
    *<br>[備  考]
    * @param errors ActionErrors
    * @param searchTarget 検索対象
    * @param req リクエスト
    * @return true: エラーあり false: エラーなし
    */
   public static boolean validateCheckSelected(ActionErrors errors,
           HttpServletRequest req, String[] searchTarget) {
       ActionMessage msg = null;

       if ((searchTarget != null)) {

          if (!(searchTarget.length > 0)) {
              //未選択の場合エラー

              GsMessage gsMsg = new GsMessage();
              String textDelShortcut = gsMsg.getMessage(req, "fil.76");

              msg = new ActionMessage(
                      "error.select.required.text", textDelShortcut);
              StrutsUtil.addMessage(errors, msg, "target2");
              return true;
          }
      }
      return false;
  }

   /**
    * <br>[機  能] キャビネットの最大容量設定を超えていないか判定する。
    * <br>[解  説]
    * <br>[備  考]
    * @param errors ActionErrors
    * @param fcbSid キャビネットSID
    * @param con コネクション
    * @param tempDir テンポラリディレクトリ
    * @param reqMdl RequestModel
    * @return チェック結果 true :エラー有り false :エラー無し
    * @throws SQLException SQL実行時例外
    * @throws IOToolsException ファイル操作時例外
    */
   public static boolean validateUsedDiskSizeOver(
           ActionErrors errors,
           int fcbSid,
           Connection con,
           String tempDir,
           RequestModel reqMdl) throws SQLException, IOToolsException {

       ActionMessage msg = null;
       FilCommonBiz filBiz = new FilCommonBiz(con, reqMdl);

       //最大ディスク使用容量を取得する。
       FileCabinetDao cabDao = new FileCabinetDao(con);
       FileCabinetModel cabMdl = cabDao .select(fcbSid);
       if (cabMdl.getFcbCapaKbn() == GSConstFile.CAPA_KBN_OFF) {
           return false;
       }
       BigDecimal capaSizeMB = BigDecimal.valueOf(cabMdl.getFcbCapaSize());
       BigDecimal capaSize = capaSizeMB.multiply(new BigDecimal(1000000));

       //現在の使用容量を取得する。
       FileCabinetDao dao = new FileCabinetDao(con);
       BigDecimal usedSize = dao.getCabinetUsedSize(fcbSid);

       //添付ファイルサイズを取得する。
       BigDecimal tempFileSize = filBiz.getSumTempFileSize(tempDir, con);

       //登録後の合計サイズ
       BigDecimal useSize = usedSize.add(tempFileSize);

       if (useSize.compareTo(capaSize) == 1) {
         msg = new ActionMessage("error.input.cabinet.capacity.over", capaSizeMB + "MB");
         StrutsUtil.addMessage(errors, msg, "error.input.cabinet.capacity.over");
       }

       //入力エラー無し
       return false;
   }

   /**
    * <br>[機  能] キャビネットの最大容量設定を超えていないか判定する。
    * <br>[解  説]
    * <br>[備  考]
    * @param errors ActionErrors
    * @param fcbSid キャビネットSID
    * @param con コネクション
    * @param tempFileSize 添付ファイルサイズ
    * @return チェック結果 true :エラー有り false :エラー無し
    * @throws SQLException SQL実行時例外
    * @throws IOToolsException ファイル操作時例外
    */
   public static boolean validateUsedDiskSizeOverValue(
           ActionErrors errors,
           int fcbSid,
           Connection con,
           BigDecimal tempFileSize) throws SQLException, IOToolsException {

       ActionMessage msg = null;

       //最大ディスク使用容量を取得する。
       FileCabinetDao cabDao = new FileCabinetDao(con);
       FileCabinetModel cabMdl = cabDao .select(fcbSid);
       if (cabMdl.getFcbCapaKbn() == GSConstFile.CAPA_KBN_OFF) {
           return false;
       }
       BigDecimal capaSizeMB = BigDecimal.valueOf(cabMdl.getFcbCapaSize());
       BigDecimal capaSize = capaSizeMB.multiply(new BigDecimal(1000000));

       //現在の使用容量を取得する。
       FileCabinetDao dao = new FileCabinetDao(con);
       BigDecimal usedSize = dao.getCabinetUsedSize(fcbSid);

       //登録後の合計サイズ
       BigDecimal useSize = usedSize.add(tempFileSize);

       if (useSize.compareTo(capaSize) == 1) {
           msg = new ActionMessage("error.input.cabinet.capacity.over", capaSizeMB + "MB");
           StrutsUtil.addMessage(errors, msg, "error.input.cabinet.capacity.over");
       }

       //入力エラー無し
       return false;
   }

   /**
    * <br>[機  能] 添付ファイルの最大容量設定を超えていないか判定する。
    * <br>[解  説]
    * <br>[備  考]
    * @param errors ActionErrors
    * @param con コネクション
    * @param tempFileSize 添付ファイルサイズ
    * @param reqMdl RequestModel
    * @return チェック結果 true :エラー有り false :エラー無し
    * @throws SQLException SQL実行時例外
    * @throws IOToolsException ファイル操作時例外
    */
   public static boolean validateFileSizeOver(
           ActionErrors errors,
           Connection con,
           BigDecimal tempFileSize,
           RequestModel reqMdl) throws SQLException, IOToolsException {

       ActionMessage msg = null;
       FilCommonBiz fileBiz = new FilCommonBiz(con, reqMdl);

       //最大添付ファイルサイズを取得する。
       FileAconfModel aconfModel = fileBiz.getFileAconfModel(con);
       BigDecimal capaSizeMB = new BigDecimal(aconfModel.getFacFileSize());
       BigDecimal capaSize = capaSizeMB.multiply(new BigDecimal(1000000));

       if (tempFileSize.compareTo(capaSize) == 1) {
           msg = new ActionMessage("error.input.capacity.over", capaSizeMB + "MB");
           StrutsUtil.addMessage(errors, msg, "error.input.capacity.over");
           //入力エラー無し
           return true;
       }

       //入力エラー無し
       return false;
   }

}
