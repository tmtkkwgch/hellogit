package jp.groupsession.v2.tcd.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.io.IOTools;
import jp.groupsession.v2.cmn.GSConstTimecard;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.TcdAdmConfModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * <br>[機  能] 勤務表Excel出力に関するユーティリティークラスです。
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ExcelUtil {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(ExcelUtil.class);

    /** BOOK */
    private static Workbook workbook = null;
    /** Sheet */
    private static Sheet sheet = null;

    /** 勤務表フォーマットファイル */
    private static final String FORMATFILE = "workReportFormat.xls";
    /** 勤務表フォーマットファイル ６０進数版 */
    private static final String FORMATFILE60 = "workReportFormat60.xls";

    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;
    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param reqMdl リクエスト情報
     */
    public ExcelUtil(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     *
     */
    public ExcelUtil() {
    }

    /**
     * 指定されたストリームに勤務表データを設定する
     * @param appRootPath タイムカードデータディレクトリパス
     * @param oStream 勤務表データの出力先となるストリーム
     * @param workReportData 勤務表データ
     * @param admMdl 管理者設定
     * @throws Exception 例外
     */
    public synchronized void createWorkReport(
            String appRootPath,
            OutputStream oStream,
            WorkReportData workReportData,
            TcdAdmConfModel admMdl) throws Exception {
        log__.debug("start");

        FileInputStream in = null;

        List<TimecardLineData> reportDataList = workReportData.getLineDataList();

        try {

            String path = __getWorkTemplateFilePath(appRootPath, admMdl);
            log__.debug(">>>path :" + path);
            in = new FileInputStream(path);
//            POIFSFileSystem fs = new POIFSFileSystem(in);
//            workbook = new HSSFWorkbook(fs);
            workbook = WorkbookFactory.create(in);

            sheet = workbook.getSheetAt(1);

            //氏名
            Cell clName = getCell2(0, 1);
            setCellString(clName, workReportData.getUserName());

            //作業場所
            Cell clPlace = getCell2(0, 2);
            setCellString(clPlace, workReportData.getPlace());

            //基準稼働時間
            Cell clHour = getCell2(0, 7);
            setCellString(clHour, workReportData.getBaseHour());

            //基準稼働時間
            Cell clBday = getCell2(0, 8);
            setCellString(clBday, workReportData.getBaseDay());

            GsMessage gsMsg = new GsMessage(reqMdl__);
            String msgYear = gsMsg.getMessage(
                    "cmn.year", new String[] {String.valueOf(workReportData.getYear())});
            String msgMonth = gsMsg.getMessage("cmn.month");

            //該当月
            sheet = workbook.getSheetAt(0);
            Cell clMon = getCell2(1, 1);
            setCellString(clMon,
                          msgYear
                          + workReportData.getMonth()
                          + msgMonth);

            String msgMintyo = gsMsg.getMessage("tcd.126");

            CellStyle style = clMon.getCellStyle();
            Font font = workbook.createFont();
            font.setFontName(msgMintyo);
            font.setItalic(true);
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);
            font.setFontHeightInPoints((short) 14);
            style.setFont(font);

            sheet = workbook.getSheetAt(1);

            Iterator<TimecardLineData> it = reportDataList.iterator();

            TimecardLineData reportData = null;

            for (int i = 1; i < 32; i++) {

                if (it.hasNext()) {
                    reportData = (TimecardLineData) it.next();
                } else {
                    reportData = new TimecardLineData();
                }


                Row row = getRow(i);
                //日付
                Cell clDay = getCell(row, 1);
                if (reportData.getDate() != null) {
                    this.setCellNumeric(
                            clDay, Double.parseDouble(reportData.getDate().getStrDay()));
                } else {
                    setCellString(clDay, "");
                }

                //曜日
                Cell clYou = getCell(row, 2);
                if (reportData.getDate() != null) {
                    int wk = reportData.getDate().getWeek();
                    String jwk = UDateUtil.getStrWeek(wk, reqMdl__);
                    setCellString(clYou, jwk);
                } else {
                    setCellString(clYou, " ");
                }

                //休日フラグ 0:平日,1:休日
                int holFlg = reportData.getHoliday();

                //休日(土日、祝祭日)
                Cell clHol = getCell(row, 0);
                if (reportData.getDate() != null) {
                    setCellNumeric(clHol, holFlg);
                } else {
                    setCellString(clHol, "");
                }

                //打刻開始時間
                Cell strikeStime = getCell(row, 3);
                if (reportData.getStrikeStartTime() != null) {
                    setCellString(strikeStime, reportData.getStrikeStartTime());
                } else {
                    setCellString(strikeStime, "");
                }

                //打刻終了時間
                Cell strikeEtime = getCell(row, 4);
                if (reportData.getStrikeEndTime() != null) {
                    setCellString(strikeEtime, reportData.getStrikeEndTime());
                } else {
                    setCellString(strikeEtime, " ");
                }

                //開始時間
                Cell stime = getCell(row, 5);
                if (reportData.getStartTime() != null) {
                    setCellString(stime, reportData.getStartTime());
                } else {
                    setCellString(stime, "");
                }

                //終了時間
                Cell etime = getCell(row, 6);
                if (reportData.getEndTime() != null) {
                    setCellString(etime, reportData.getEndTime());
                } else {
                    setCellString(etime, " ");
                }

                //稼動時間
                Cell ktime = getCell(row, 7);
                String utilTime = reportData.getUtilTime();
                if (utilTime != null && utilTime.length() > 0) {
                    double utilTimeVal = Double.parseDouble(utilTime);
                    if (utilTimeVal > 0) {
                        setCellNumeric(ktime, utilTimeVal);
                    } else {
                        setCellString(ktime, " ");
                    }
                } else {
                    setCellString(ktime, " ");
                }

                //残業
                Cell clZan = getCell(row, 8);
                String overTime = reportData.getOverTime();
                if (overTime != null && overTime.length() > 0) {
                    double overTimeVal = Double.parseDouble(overTime);
                    if (overTimeVal > 0) {
                        setCellNumeric(clZan, overTimeVal);
                    } else {
                        setCellString(clZan, " ");
                    }
                } else {
                    setCellString(clZan, " ");
                }

                //深夜
                Cell clShin = getCell(row, 9);
                String nightOverTime = reportData.getNightOverTime();
                if (nightOverTime != null && nightOverTime.length() > 0) {
                    double nightOverTimeVal = Double.parseDouble(nightOverTime);
                    if (nightOverTimeVal > 0) {
                        setCellNumeric(clShin, nightOverTimeVal);
                    } else {
                        setCellString(clShin, " ");
                    }
                } else {
                    setCellString(clShin, " ");
                }

                //遅刻
                Cell clChiko = getCell(row, 10);
                __emptyFormatForCell(clChiko, reportData.getTikoku());

                //早退
                Cell clSou = getCell(row, 11);
                __emptyFormatForCell(clSou, reportData.getSoutai());

                //代休
                Cell clDai = getCell(row, 12);
                __emptyFormatForCell(clDai, reportData.getDaikyu());

                //有休
                Cell clYuu = getCell(row, 13);
                __emptyFormatForCell(clYuu, reportData.getYuukyu());

                //欠勤
                Cell clKek = getCell(row, 14);
                __emptyFormatForCell(clKek, reportData.getKekkin());

                //残業理由
                Cell clRiyu = getCell(row, 15);
                setCellString(clRiyu, "");

                //備 考
                Cell clBikou = getCell(row, 16);
                String bikou = reportData.getBikou();
                if (bikou != null) {
                    setCellString(clBikou, bikou);
                } else {
                    setCellString(clBikou, "");
                }

            }

            //ファイルopen時に数式を実行する
            ((HSSFSheet) workbook.getSheetAt(0)).setForceFormulaRecalculation(true);
            if (admMdl.getTacKansan() == GSConstTimecard.TIMECARD_SINSU[1]) {
                ((HSSFSheet) workbook.getSheetAt(1)).setForceFormulaRecalculation(true);
                ((HSSFSheet) workbook.getSheetAt(0)).setForceFormulaRecalculation(true);
            }
            workbook.write(oStream);

        } catch (IOException e) {
            throw new Exception("WorkReport Set Error", e);
        } finally {
            try {
                in.close();
            } catch (Exception e) { }
        }

        log__.debug("end");
    }

    /**
     * <br>[機  能] アプリケーションのルートパスから勤務表テンプレートパスを返す。
     * <br>[解  説]
     * <br>[備  考]
     * @param appRootPath アプリケーションのルートパス
     * @param admMdl 基本設定
     * @return テンプレートファイルのパス文字列
     */
    private static String __getWorkTemplateFilePath(String appRootPath, TcdAdmConfModel admMdl) {
        //WEBアプリケーションのパス
        appRootPath = IOTools.setEndPathChar(appRootPath);
        String filename = FORMATFILE;
        if (admMdl.getTacKansan() == GSConstTimecard.TIMECARD_SINSU[0]) {
            //10進数
            filename = FORMATFILE;
        } else {
            //60進数
            filename = FORMATFILE60;
        }
        String ret = IOTools.replaceFileSep(appRootPath
                + "WEB-INF/plugin/timecard/template/" + filename);
        return ret;
    }

    /**
     * <p>セルにStringをセットする
     * @param cell 対象のセル
     * @param str セットする文字列
     * @return セル
     */
    private Cell setCellString(Cell cell, String str) {
//        cell.setEncoding(Cell.ENCODING_UTF_16);
        cell.setCellType(Cell.CELL_TYPE_STRING);
        cell.setCellValue(str);
        return cell;
    }


    /**
     * <p>セルにStringをセットするNUMERIC
     * @param cell 対象のセル
     * @param i セットする数値
     * @return セル
     */
    private Cell setCellNumeric(Cell cell, double i) {
        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
        cell.setCellValue(i);
        return cell;
    }

    /**
     * <p>行を返す
     * @param index 行数
     * @return 行
     */
    private Row getRow(int index) {
        Row row = sheet.getRow(index);
        if (row == null) {
            row = sheet.createRow(index);
        }
        return row;
    }

    /**
     * <p>セルを返す
     * @param row 行
     * @param index 取得するセルのインデックス
     * @return 取得したセル
     */
    private Cell getCell(Row row, int index) {
        Cell cell = row.createCell(index);
        return cell;
    }

    /**
     * <p>セルを返す
     * @param rowIdx 行数
     * @param colIdx 列数
     * @return 行
     */
    private Cell getCell2(int rowIdx, int colIdx) {
        Row row = getRow(rowIdx);
        Cell cell = row.createCell((short) colIdx);
        return cell;
    }

    /**
     * <br>[機  能] Excel出力用の数値項目の空文字対応表現を出力する
     * <br>[解  説]
     * <br>[備  考]
     * @param cell Excelセル
     * @param value フォーマット対象文字列
     */
    private void __emptyFormatForCell(Cell cell, String value) {
        if (StringUtil.isNullZeroString(value) || Double.parseDouble(value) == 0) {
            setCellString(cell, " ");
            return;
        }
        setCellNumeric(cell, Double.parseDouble(value));
    }

}
