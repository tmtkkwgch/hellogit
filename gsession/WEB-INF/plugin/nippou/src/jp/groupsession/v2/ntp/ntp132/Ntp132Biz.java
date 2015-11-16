package jp.groupsession.v2.ntp.ntp132;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.io.ObjectFile;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.cmn110.Cmn110FileModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.ntp.dao.NtpShohinCategoryDao;
import jp.groupsession.v2.ntp.model.NtpShohinCategoryModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 日報 商品インポート画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ntp132Biz {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Ntp132Biz.class);

    /** リクエスモデル */
    public RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     */
    public Ntp132Biz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] テンポラリディレクトリのファイル削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param tempDir テンポラリディレクトリパス
     * @throws IOToolsException ファイルアクセス時例外
     */
    public void doDeleteFile(String tempDir) throws IOToolsException {

        //テンポラリディレクトリのファイルを削除する
        IOTools.deleteDir(tempDir);
        log__.debug("テンポラリディレクトリのファイル削除");
    }

    /**
     * <br>[機  能] 初期表示情報を画面にセットする
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Ntp132ParamModel
     * @param tempDir テンポラリディレクトリパス
     * @param con コネクション
     * @throws IOToolsException ファイルアクセス時例外
     * @throws SQLException sql実行例外
     */
    public void setInitData(
        Ntp132ParamModel paramMdl,
        Connection con,
        String tempDir) throws IOToolsException, SQLException {

        //カテゴリコンボ作成
        ArrayList<LabelValueBean> ntpShohinCatList = new ArrayList<LabelValueBean>();
        NtpShohinCategoryDao catDao = new NtpShohinCategoryDao(con);
        List<NtpShohinCategoryModel> catMdlList = catDao.select();
        for (NtpShohinCategoryModel mdl : catMdlList) {
            String catName = mdl.getNscName();
            String catSid = String.valueOf(mdl.getNscSid());
            ntpShohinCatList.add(new LabelValueBean(catName, catSid));
        }

        paramMdl.setNtp132CategoryList(ntpShohinCatList);
        paramMdl.setNtp132CatSid(paramMdl.getNtp132CatSid());

        /** 画面に表示するファイルのリストを作成、セット **********************/
        //テンポラリディレクトリにあるファイル名称を取得
        List<String> fileList = IOTools.getFileNames(tempDir);

        //画面に表示するファイルのリストを作成
        ArrayList<LabelValueBean> fileLblList = new ArrayList<LabelValueBean>();

        if (fileList != null) {

            log__.debug("ファイルの数×２(オブジェクトと本体) = " + fileList.size());

            for (int i = 0; i < fileList.size(); i++) {

                //ファイル名を取得
                String fileName = fileList.get(i);
                if (!fileName.endsWith(GSConstCommon.ENDSTR_OBJFILE)) {
                    continue;
                }

                //オブジェクトファイルを取得
                ObjectFile objFile = new ObjectFile(tempDir, fileName);
                Object fObj = objFile.load();
                if (fObj == null) {
                    continue;
                }

                String[] value = fileName.split(GSConstCommon.ENDSTR_OBJFILE);

                //表示用リストへ追加
                Cmn110FileModel fMdl = (Cmn110FileModel) fObj;
                fileLblList.add(new LabelValueBean(fMdl.getFileName(), value[0]));
                log__.debug("ファイル名 = " + fMdl.getFileName());
                log__.debug("保存ファイル名 = " + fMdl.getSaveFileName());
            }
        }
        paramMdl.setNtp132FileLabelList(fileLblList);
    }
}
