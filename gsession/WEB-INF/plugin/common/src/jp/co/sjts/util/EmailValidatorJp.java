package jp.co.sjts.util;

import java.util.regex.Pattern;

import org.apache.commons.validator.routines.EmailValidator;

/**
 * EmailValidatorを日本国内キャリア用にチェックを緩めたValidator
 * RFC違反である連続したドットを許容します。
 * @author JTS
 *
 */
public class EmailValidatorJp extends EmailValidator {

    /** RFC準拠 + キャリアメール対応 メールフォーマット 正規表現*/
    private static final String USER_REGEX = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]*$";
    /** RFC準拠 + キャリアメール対応 メールフォーマット 正規表現パターンクラス*/
    private static final Pattern USER_PATTERN = Pattern.compile(USER_REGEX);
    /** RFC準拠 + キャリアメール対応 メールフォーマット 正規表現*/
    private static final String QUOTED_USER_REGEX = "^([^\"]|(\\\"))*$";
    /** RFC準拠 + キャリアメール対応 メールフォーマット 正規表現パターンクラス*/
    private static final Pattern QUOTED_USER_PATTERN = Pattern.compile(QUOTED_USER_REGEX);

    /**
     * Singleton instance of this class, which
     *  doesn't consider local addresses as valid.
     */
    private static final EmailValidatorJp EMAIL_VALIDATOR = new EmailValidatorJp(false);

    /**
     * Returns the Singleton instance of this validator,
     *  with local validation as required.
     *
     * @return singleton instance of this validator
    */
    public static EmailValidatorJp getInstance() {
          return EMAIL_VALIDATOR;
    }
    /**
     *
     * @param allowLocal Should local addresses be considered valid?
     */
    protected EmailValidatorJp(boolean allowLocal) {
        super(allowLocal);
    }
    /**
     *
     * <br>[機  能] 文字列末がエスケープシーケンスか否かを判定
     * <br>[解  説]
     * <br>[備  考]
     * @param src 判定文字列
     * @return true:エスケープシーケンス
     */
    private boolean __endWithEscape(String src) {
        if (!src.endsWith("\\")) {
            return false;
        }
        src = src.substring(0, (src.length() - 1));
        int cnt = 1;
        while (src.endsWith("\\")) {
            cnt++;
            src = src.substring(0, (src.length() - 1));
        }
        return (cnt % 2 != 0);
    }

    /**
      * Returns true if the user component of an email address is valid.
      *
      * @param user being validated
      * @return true if the user name is valid.
      */
     protected boolean isValidUser(String user) {
         if (user.startsWith(".") || user.endsWith(".")) {
             return false;
         }
         //""で囲まれた部分とその他でチェックを切り替える。
         int st = 0;
         int ed = 0;
         StringBuilder escapedBuilder = new StringBuilder();
         StringBuilder quotedBuilder = new StringBuilder();
         boolean resModeQuoted = false;
         boolean modeQuoted = false;
         while (user.indexOf('"', st) != -1) {
             if (modeQuoted) {
                 ed = user.indexOf('"', st);
                 quotedBuilder.append(user.substring(st, ed));
                 if (__endWithEscape(quotedBuilder.toString())) {
                     quotedBuilder.append("\"");
                 } else {
                     if (!QUOTED_USER_PATTERN.matcher(quotedBuilder.toString()).matches()) {
                         return false;
                     }
                     quotedBuilder = new StringBuilder();
                     modeQuoted = false;
                 }
                 st = ed + 1;
             } else {
                 ed = user.indexOf('"', st);
                 escapedBuilder.append(user.substring(st, ed));
                 st = ed + 1;
                 modeQuoted = true;
                 resModeQuoted = true;
             }
         }
         if (!modeQuoted) {
             escapedBuilder.append(user.substring(st, user.length()));
         } else {
             escapedBuilder.append("\"");
             escapedBuilder.append(quotedBuilder);
             escapedBuilder.append(user.substring(st, user.length()));
             resModeQuoted = false;
         }
         if (!resModeQuoted) {
             return (USER_PATTERN.matcher(user).matches());
         }
         if (escapedBuilder.length() == 0) {
             return true;
         }
         return (USER_PATTERN.matcher(user).matches());
//         return USER_PATTERN.matcher(user).matches();
     }
}
