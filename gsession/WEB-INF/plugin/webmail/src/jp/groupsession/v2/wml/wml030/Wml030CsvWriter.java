package jp.groupsession.v2.wml.wml030;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import jp.co.sjts.util.csv.AbstractCSVWriter;
import jp.co.sjts.util.csv.CSVException;
import jp.co.sjts.util.io.IOTools;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] アカウント情報(CSV)を出力する
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml030CsvWriter extends AbstractCSVWriter {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Wml030CsvWriter.class);

    /** コネクション */
    private Connection con__ = null;
    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;
    /** エクスポート情報 */
    private List<Wml030ExportModel> exportDataList__ = null;

    /** ユーザ情報一覧ファイル名 */
    public static final String FILE_NAME = "accountList.csv";

    /**
     * <p>コンストラクタ
     */
    private Wml030CsvWriter() {
    }

    /**
     * <p>コンストラクタ
     * @param con Connection
     * @param reqMdl RequestModel
     * @param exportDataList エクスポート情報
     */
    public Wml030CsvWriter(Connection con, RequestModel reqMdl,
                                    List<Wml030ExportModel> exportDataList) {
        this();
        con__ = con;
        reqMdl__ = reqMdl;
        exportDataList__ = exportDataList;
    }

    /**
     * <br>[機  能] CSVファイルの作成
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param csvPath 出力先
     * @throws CSVException CSV出力時例外
     */
    public void outputCsv(Connection con, String csvPath)
    throws CSVException {

        //ディレクトリの作成
        File tmpDir = new File(csvPath);
        tmpDir.mkdirs();

        //セットファイル名とフルパス
//        UDate date = new UDate();
        String fileName = FILE_NAME;
        String fileFullPath = IOTools.replaceFileSep(csvPath + File.separator + fileName);
        log__.debug("CSVファイルのパス = " + fileFullPath);

        //出力初期セット
        setCsvPath(fileFullPath);

        log__.debug("開始");
        write();
        log__.debug("終了");
    }

    /**
     * <br>[機  能] CSV生成 値をセットする
     * <br>[解  説]
     * <br>[備  考]
     * @param pw PrintWriter
     * @throws CSVException CSV出力時例外
     */
    public void create(PrintWriter pw) throws CSVException {

        //ヘッダ
        __writeHeader(pw);

        //明細
        __writeItem(pw);
    }

    /**
     * <p>ヘッダ部分を生成します。
     * @param pw PrintWriter
     */
    private void __writeHeader(PrintWriter pw) {
        GsMessage gsMsg = new GsMessage(reqMdl__);
        String header =
                gsMsg.getMessage("wml.96") + ","
                + gsMsg.getMessage("cmn.mailaddress") + ","
                + gsMsg.getMessage("wml.154") + ","
                + gsMsg.getMessage("wml.267") + ","
                + gsMsg.getMessage("wml.268") + ","
                + gsMsg.getMessage("wml.269") + ","
                + gsMsg.getMessage("wml.270") + ","
                + gsMsg.getMessage("wml.152") + ","
                + gsMsg.getMessage("wml.271") + ","
                + gsMsg.getMessage("wml.80") + ","
                + gsMsg.getMessage("wml.272") + ","
                + gsMsg.getMessage("wml.273") + ","
                + gsMsg.getMessage("wml.266") + ","
                + gsMsg.getMessage("wml.78") + ","
                + gsMsg.getMessage("wml.79") + ","
                + gsMsg.getMessage("wml.87") + ","
                + gsMsg.getMessage("wml.87") + "　" + gsMsg.getMessage("cmn.size") + ","
                + gsMsg.getMessage("wml.87") + "　" + gsMsg.getMessage("cmn.fixed.special") + ","
                + gsMsg.getMessage("cmn.memo") + ","
                + gsMsg.getMessage("wml.25") + ","
                + gsMsg.getMessage("wml.34") + ","
                + gsMsg.getMessage("wml.sign.point") + ","
                + gsMsg.getMessage("wml.receive.sign") + ","
                + gsMsg.getMessage("wml.52") + ","
                + gsMsg.getMessage("wml.53") + ","
                + gsMsg.getMessage("wml.54") + ","
                + gsMsg.getMessage("wml.36") + ","
                + gsMsg.getMessage("wml.39") + ","
                + gsMsg.getMessage("wml.111") + ","
                + gsMsg.getMessage("wml.17") + ","
                + gsMsg.getMessage("wml.wml040kn.01") + ","
                + gsMsg.getMessage("cmn.format.") + ","
                + gsMsg.getMessage("wml.238") + ","
                + gsMsg.getMessage("wml.239") + ","
                + gsMsg.getMessage("wml.240") + ","
                + gsMsg.getMessage("wml.240") + "　" + gsMsg.getMessage("ntp.10") + ","
                + gsMsg.getMessage("wml.241") + ","
                + gsMsg.getMessage("wml.241") + "　" + gsMsg.getMessage("ntp.10") + ","
                + gsMsg.getMessage("cmn.theme") + ","
                + gsMsg.getMessage("cmn.quotes") + ","
                + gsMsg.getMessage("cmn.use.user.group.kbn") + ","
                + gsMsg.getMessage("cmn.use.user.group") + "1" + ","
                + gsMsg.getMessage("cmn.use.user.group") + "2" + ","
                + gsMsg.getMessage("cmn.use.user.group") + "3" + ","
                + gsMsg.getMessage("cmn.use.user.group") + "4" + ","
                + gsMsg.getMessage("cmn.use.user.group") + "5" + ","
                + gsMsg.getMessage("cmn.proxyuser") + "1" + ","
                + gsMsg.getMessage("cmn.proxyuser") + "2" + ","
                + gsMsg.getMessage("cmn.proxyuser") + "3" + ","
                + gsMsg.getMessage("cmn.proxyuser") + "4" + ","
                + gsMsg.getMessage("cmn.proxyuser") + "5";

        pw.println(header);
    }

    /**
     * <p>明細部分を生成します。
     * @param pw PrintWriter
     * @throws CSVException CSV出力時例外
     */
    private void __writeItem(PrintWriter pw) throws CSVException {
        Wml030CsvRecordListenerImpl rl = new Wml030CsvRecordListenerImpl(pw, con__);
        for (Wml030ExportModel exportData : exportDataList__) {
            rl.setRecord(exportData);
        }
    }
}