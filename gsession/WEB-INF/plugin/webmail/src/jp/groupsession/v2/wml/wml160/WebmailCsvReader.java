package jp.groupsession.v2.wml.wml160;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.csv.AbstractCsvRecordReader;
import jp.co.sjts.util.csv.CsvTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] WEBメール アカウントインポート 取込みファイルのチェックを行うクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 *
 */
public class WebmailCsvReader extends AbstractCsvRecordReader {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(WebmailCsvReader.class);

    /** アカウント情報一覧 */
    private List<WebmailCsvModel> webmailList__ = null;

    /**
     * <p>webmailList を取得します。
     * @return webmailList
     */
    public List<WebmailCsvModel> getWebmailList() {
        return webmailList__;
    }

    /**
     * <p>webmailList をセットします。
     * @param webmailList webmailList
     */
    public void setWebmailList(List<WebmailCsvModel> webmailList) {
        webmailList__ = webmailList;
    }

    /**
     * コンストラクタ
     */
    public WebmailCsvReader() {
        setWebmailList(new ArrayList<WebmailCsvModel>());
    }

    /**
     * <br>[機　能] 指定したCSVファイルからアカウント情報を読み込む
     * <br>[解　説]
     * <br>[備  考]
     * @param csvFile 入力ファイル名
     * @throws Exception 実行時例外
     */
     public void readCsvFile(String csvFile)
     throws Exception {

         //ファイル読込み
         readFile(new File(csvFile), Encoding.WINDOWS_31J);
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
    protected void processedLine(long num, String lineStr) throws Exception {

        //ヘッダ文字列読み飛ばし
        if (num == 1) {
            return;
        }

        try {

            CsvTokenizer csvTokenizer = new CsvTokenizer(lineStr, ",");

            WebmailCsvModel model = new WebmailCsvModel();
            model.setRowNum(num);
            model.setElementCount(csvTokenizer.length());

            for (int index = 0; csvTokenizer.hasMoreElements(); index++) {
                String value = csvTokenizer.nextToken();

                switch (index) {
                    case 0 :
                        //アカウント名
                        model.setAccountName(value);
                        break;
                    case 1 :
                        //メールアドレス
                        model.setMail(value);
                        break;
                    case 2 :
                        //メール受信サーバ名
                        model.setMailRsvServer(value);
                        break;
                    case 3 :
                        //受信サーバ ポート番号
                        model.setMailRsvPort(value);
                        break;
                    case 4 :
                        //受信サーバ SSL
                        model.setMailRsvSsl(value);
                        break;
                    case 5 :
                        //受信サーバ ユーザ名
                        model.setMailRsvUser(value);
                        break;
                    case 6 :
                        //受信サーバ パスワード
                        model.setMailRsvPass(value);
                        break;
                    case 7 :
                        //メール自動受信
                        model.setMailAutoRsv(value);
                        break;
                    case 8 :
                        //メール自動受信間隔
                        model.setMailAutoRsvTime(value);
                        break;
                    case 9 :
                        //メール送信サーバ
                        model.setMailSndServer(value);
                        break;
                    case 10 :
                        //メール送信サーバ ポート番号
                        model.setMailSndPort(value);
                        break;
                    case 11 :
                        //メール送信サーバ SSL
                        model.setMailSndSsl(value);
                        break;
                    case 12 :
                        //SMTP認証ON/OFF
                        model.setSmtpNinsyo(value);
                        break;
                    case 13 :
                        //メール送信サーバ ユーザ名
                        model.setMailSndUser(value);
                        break;
                    case 14 :
                        //メール送信サーバ パスワード
                        model.setMailSndPass(value);
                        break;
                    case 15 :
                        //ディスク容量
                        model.setDiskCapa(value);
                        break;
                    case 16 :
                        //ディスク容量 サイズ
                        model.setDiskCapaSize(value);
                        break;
                    case 17 :
                        //ディスク容量 特例修正
                        model.setDiskCapaSps(value);
                        break;
                    case 18 :
                        //備考
                        model.setBiko(value);
                        break;
                    case 19 :
                        //組織名
                        model.setOrganization(value);
                        break;
                    case 20 :
                        //署名
                        model.setSign(value);
                        break;
                    case 21 :
                        //返信時署名位置
                        model.setSignPoint(value);
                        break;
                    case 22 :
                        //返信時署名表示
                        model.setSignDsp(value);
                        break;
                    case 23 :
                        //自動TO
                        model.setAutoTo(value);
                        break;
                    case 24 :
                        //自動CC
                        model.setAutoCc(value);
                        break;
                    case 25 :
                        //自動BCC
                        model.setAutoBcc(value);
                        break;
                    case 26 :
                        //受信時削除
                        model.setRsvDelete(value);
                        break;
                    case 27 :
                        //受信済みでも受信
                        model.setRsvOk(value);
                        break;
                    case 28 :
                        //APOPのON/OFF
                        model.setApop(value);
                        break;
                    case 29 :
                        //送信前POP認証
                        model.setBeSndPopNinsyo(value);
                        break;
                    case 30 :
                        //送信文字コード
                        model.setSndWordCode(value);
                        break;
                    case 31 :
                        //送信メール形式
                        model.setSndMailType(value);
                        break;
                    case 32 :
                        //宛先の確認
                        model.setCheckAddress(value);
                        break;
                    case 33 :
                        //添付ファイルの確認
                        model.setCheckFile(value);
                        break;
                    case 34 :
                        //添付ファイル自動圧縮
                        model.setCompressFile(value);
                        break;
                    case 35 :
                        //添付ファイル自動圧縮 初期値
                        model.setCompressFileDef(value);
                        break;
                    case 36 :
                        //時間差送信
                        model.setTimeSent(value);
                        break;
                    case 37 :
                        //時間差送信 初期値
                        model.setTimeSentDef(value);
                        break;
                    case 38 :
                        //テーマ
                        model.setTheme(value);
                        break;
                    case 39 :
                        //引用符
                        model.setQuotes(value);
                        break;
                    case 40 :
                        //使用ユーザ/グループ区分
                        model.setUserKbn(value);
                        break;
                    case 41 :
                        //使用ユーザ/グループ1
                        model.setUser1(value);
                        break;
                    case 42 :
                        //使用ユーザ/グループ2
                        model.setUser2(value);
                        break;
                    case 43 :
                        //使用ユーザ/グループ3
                        model.setUser3(value);
                        break;
                    case 44 :
                        //使用ユーザ/グループ4
                        model.setUser4(value);
                        break;
                    case 45 :
                        //使用ユーザ/グループ5
                        model.setUser5(value);
                        break;
                    case 46 :
                        //代理人1
                        model.setProxyUser1(value);
                        break;
                    case 47 :
                        //代理人2
                        model.setProxyUser2(value);
                        break;
                    case 48 :
                        //代理人3
                        model.setProxyUser3(value);
                        break;
                    case 49 :
                        //代理人4
                        model.setProxyUser4(value);
                        break;
                    case 50 :
                        //代理人5
                        model.setProxyUser5(value);
                        break;
                    default :
                        break;
                }
            }

            getWebmailList().add(model);
       } catch (Exception e) {
            log__.error("CSVファイル読込み時例外");
            throw e;
        }
    }
}