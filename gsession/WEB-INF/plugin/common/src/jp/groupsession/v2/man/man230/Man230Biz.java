package jp.groupsession.v2.man.man230;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.DBUtilFactory;
import jp.groupsession.v2.cmn.GroupSession;
import jp.groupsession.v2.cmn.IDbUtil;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.convert.dao.VersionDao;
import jp.groupsession.v2.convert.model.VersionModel;

import org.apache.commons.io.FileUtils;


/**
 * <br>[機  能] メイン 管理者設定 システム情報画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man230Biz {

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param context コンテキスト
     * @param realPath ディスクのパス
     * @param reqMdl リクエスト情報
     * @throws Exception 実行エラー
     */
    public void setInitData(Connection con, Man230ParamModel paramMdl,
                            ServletContext context, String realPath,
                            RequestModel reqMdl)
    throws Exception {

        // 現在のシステムプロパティを取得します
        Properties props = System.getProperties();

        //バージョン(DB)
        VersionDao verDao = new VersionDao(con);
        VersionModel verMdl = verDao.select();
        paramMdl.setMan230GSVersionDB(verMdl.getVerVersion());
        JDBCUtil.closeConnection(con);

        //OS
        StringBuilder osbuf = new StringBuilder();
        osbuf.append(props.getProperty("os.name"));
        osbuf.append(" " + props.getProperty("os.arch"));
        osbuf.append(" " + props.getProperty("os.version"));
        paramMdl.setMan230Os(osbuf.toString());

        //サーブレットコンテナバージョン
        String servletInfo = context.getServerInfo();
        paramMdl.setMan230J2ee(servletInfo);

        //java
        StringBuilder jbuf = new StringBuilder();
        jbuf.append(props.getProperty("java.version"));
        jbuf.append(" " + props.getProperty("java.vendor"));
        jbuf.append(" (");
        jbuf.append(props.getProperty("java.vm.name"));
        jbuf.append(")");
        paramMdl.setMan230Java(jbuf.toString());

        //メモリ情報
        getMemoryInfo(paramMdl);

        //DB区分
        IDbUtil dbUtil = DBUtilFactory.getInstance();
        paramMdl.setMan230DbKbn(String.valueOf(dbUtil.getDbType()));

        //ディスクの空き容量
        BigDecimal freeSpace = new BigDecimal(new File(realPath).getFreeSpace() / 1024);
        StringBuilder strFreeSpace = new StringBuilder("");
        strFreeSpace.append(freeSpace.divide(new BigDecimal(1024), 1).toString());
        strFreeSpace.append("MB");

        if (freeSpace.longValue() > FileUtils.ONE_GB) {
            strFreeSpace.append(" (");
            strFreeSpace.append(freeSpace.divide(new BigDecimal(1024 * 1024), 1).toString());
            strFreeSpace.append("GB)");
        }
        paramMdl.setMan230FreeDSpace(strFreeSpace.toString());

        //開発者用
        //DS
        DataSource ds = GroupSession.getResourceManager().getDataSource(reqMdl);
        if (ds instanceof org.apache.commons.dbcp.BasicDataSource) {
            //
            org.apache.commons.dbcp.BasicDataSource bds
                = (org.apache.commons.dbcp.BasicDataSource) ds;
            int aconcnt = bds.getNumActive();
            int iconcnt = bds.getNumIdle();
            String connectionCount = "ACTIVE=" + aconcnt + " IDLE=" + iconcnt;
            paramMdl.setMan230ConnectionCount(connectionCount);
        }
    }

    /**
     * [機  能] 使用量、使用を試みる最大メモリ容量の情報を返します。<br>
     * [解  説] <br>
     * [備  考] <br>
     * @param paramMdl パラメータ情報
     */
    public void getMemoryInfo(Man230ParamModel paramMdl) {
        DecimalFormat format1 = new DecimalFormat("#,###KB");
        DecimalFormat format2 = new DecimalFormat("##.#");
        long free = Runtime.getRuntime().freeMemory() / 1024;
        long max = Runtime.getRuntime().maxMemory() / 1024;
        long used = max - free;
        double ratio = (used * 100 / (double) max);
        //使用
        paramMdl.setMan230MemoryUse(format1.format(used));
        //使用(割合)
        paramMdl.setMan230MemoryUsePer(format2.format(ratio));
        //最大値
        paramMdl.setMan230MemoryMax(format1.format(max));
    }
}
