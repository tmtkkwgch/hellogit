package jp.groupsession.v2.adr.adr100;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.csv.AbstractCSVWriter;
import jp.co.sjts.util.csv.CSVException;
import jp.co.sjts.util.io.IOTools;
import jp.groupsession.v2.adr.GSConstAddress;
import jp.groupsession.v2.adr.adr100.model.Adr100SearchModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 会社情報一覧(CSV)の出力を行うクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class CompanyCsvWriter extends AbstractCSVWriter {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(CompanyCsvWriter.class);

    /** グループ情報一覧ファイル名 */
    public static final String FILE_NAME = "companyList.csv";

    /** コネクション */
    private Connection con__ = null;


    /** サーチモデル */
    private Adr100SearchModel mdl__ = null;

    /**
     * <p>コンストラクタ
     * @param mdl Ntp130SearchModel
     */
    public CompanyCsvWriter(Adr100SearchModel mdl) {
        mdl__ = mdl;
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

        setCon(con);

        //ディレクトリの作成
        File tmpDir = new File(csvPath);
        tmpDir.mkdirs();

        //セットファイル名とフルパス
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
        String strHeader = GSConstAddress.TEXT_COMP_CODE
                         + ","
                         + GSConstAddress.TEXT_COMP_NAME
                         + ","
                         + GSConstAddress.TEXT_COMP_NAME_KN
                         + ","
                         + GSConstAddress.TEXT_COMP_URL
                         + ","
                         + GSConstAddress.TEXT_COMP_BIKO
                         + ","
                         + GSConstAddress.TEXT_COMP_BASE_TYPE
                         + ","
                         + GSConstAddress.TEXT_COMP_BASE_NAME
                         + ","
                         + GSConstAddress.TEXT_COMP_BASE_POST_NO
                         + ","
                         + GSConstAddress.TEXT_COMP_BASE_TDFK
                         + ","
                         + GSConstAddress.TEXT_COMP_BASE_ADDRESS1
                         + ","
                         + GSConstAddress.TEXT_COMP_BASE_ADDRESS2
                         + ","
                         + GSConstAddress.TEXT_COMP_BASE_BIKO;

        pw.println(strHeader);
    }

    /**
     * <p>明細部分を生成します。
     * @param pw PrintWriter
     * @throws CSVException CSV出力時例外
     */
    private void __writeItem(PrintWriter pw) throws CSVException {

        CompanyCsvRecordListenerImpl rl = new CompanyCsvRecordListenerImpl(pw);
        Connection con = getCon();
        try {
            Adr100CompanyDao companyDao = new Adr100CompanyDao(con);
            companyDao.selectCompanyInfoForCsv(rl, mdl__);
        } catch (SQLException e) {
            log__.error("SQLの実行に失敗", e);
            throw new CSVException("SQLの実行に失敗", e);
        }
    }

    /**
     * <p>con を取得します。
     * @return con
     */
    public Connection getCon() {
        return con__;
    }
    /**
     * <p>con をセットします。
     * @param con con
     */
    public void setCon(Connection con) {
        con__ = con;
    }
}