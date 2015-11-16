package jp.groupsession.v2.rsv.rsv092;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.io.ObjectFile;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn110.Cmn110FileModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rsv.AbstractReserveBiz;
import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.rsv.rsv090.Rsv090Biz;
import jp.groupsession.v2.rsv.rsv090.Rsv090PlaceForm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 施設予約 場所・地図画像設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv092Biz extends AbstractReserveBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rsv092Biz.class);
    /** リクエスト情報 */
    protected RequestModel reqMdl_ = null;
    /** コネクション */
    protected Connection con_ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param reqMdl リクエスト情報
     * @param con コネクション
     */
    public Rsv092Biz(RequestModel reqMdl, Connection con) {
        reqMdl_ = reqMdl;
        con_ = con;
    }

    /**
     * <br>[機  能] 処理権限判定
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv092ParamModel
     * @return ret true:処理実行可 false:処理実行負荷
     * @throws SQLException SQL実行時例外
     */
    public boolean isPossibleToProcess(Rsv092ParamModel paramMdl)
        throws SQLException {

       /***********************************************
         *
         * 施設の編集が可能か判定する
         *
         * 1:システム管理者である
         * 2:処理対象の施設グループが【権限設定をしない】に
         *   設定されている
         * 3:施設グループの管理者に設定されている
         *
         ***********************************************/
        boolean ret = _isSisetuEditAuthority(reqMdl_, con_, paramMdl.getRsv090EditGrpSid());
        log__.debug("処理権限 = " + ret);

        return ret;

    }

    /**
     * <br>[機  能] 添付ファイル情報をセット
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv092ParamModel
     * @param tempDir テンポラリディレクトリ
     * @param con コネクション
     * @throws IOToolsException
     * @throws IOToolsException ファイルアクセス時例外
     */
    public void setTempFiles(Rsv092ParamModel paramMdl, String tempDir, Connection con)
        throws IOToolsException {

        /** 画面に表示するファイルのリストを作成、セット **********************/
        //テンポラリディレクトリにあるファイル名称を取得
//        List<String> fileList = IOTools.getFileNames(tempDir);

        CommonBiz commonBiz = new CommonBiz();
        paramMdl.setRsv092FileLabelList(commonBiz.getTempFileLabelList(tempDir));
    }

    /**
     * <br>[機  能] 画像を別のテンポラリディレクトリに移動させる
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param oldDir 保存元ディレクトリ
     * @param newDir 保存先ディレクトリ
     * @throws IOToolsException ファイルアクセス時例外
     * @throws IOException 添付ファイルの操作に失敗
     */
    public void copyImgOthTemp(String oldDir,
                                String newDir)
    throws IOToolsException, IOException {

        //保存元のファイル
        log__.debug("******保存元ディレクトリパス:" + oldDir);
        log__.debug("******保存先ディレクトリパス:" + newDir);

        File oldFileDir = new File(oldDir);
        File newFileDir = new File(newDir);

        if (IOTools.isDirCheck(newDir, false)) {
            //ディレクトリが存在する場合
            //削除
            IOTools.deleteDir(newDir);
        }

        //ディレクトリ存在チェック(なければ作成)
        IOTools.isDirCheck(newDir, true);

        //添付ファイルをコピー
        IOTools.copyDir(oldFileDir, newFileDir);

        //ディレクトリを削除
        IOTools.deleteDir(oldDir);
    }

    /**
     * <br>[機  能] 場所・地図データを別のディレクトリに保存する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Rsv092ParamModel
     * @param rsv092PlaDataDirPath RSV092画面場所画像データディレクトリ
     * @param rsv090PlaDataDirPath RSV090画面場所画像データディレクトリ
     * @param plaImgDir 場所・地図画像ディレクトリ
     * @throws IOToolsException ファイルアクセス時例外
     */
    public void copyPlaceImgDataOthTemp(Rsv092ParamModel paramMdl,
                                String rsv092PlaDataDirPath,
                                String rsv090PlaDataDirPath,
                                String plaImgDir)
    throws IOToolsException {

        String oldDir = rsv092PlaDataDirPath;
        String newDir = rsv090PlaDataDirPath;

        //保存元のファイル
        log__.debug("******保存元ディレクトリパス:" + oldDir);
        log__.debug("******保存先ディレクトリパス:" + newDir);

        if (IOTools.isDirCheck(newDir, false)) {
            //ディレクトリが存在する場合
            //ディレクトリ削除
            IOTools.deleteDir(newDir);
        }

        //ディレクトリ作成
        IOTools.isDirCheck(newDir, true);

        //場所画像データ保存
        setSavePlaImgData(oldDir, newDir, plaImgDir);

    }

    /**
     * <br>[機  能] 場所画像データを設定し保存する
     * <br>[解  説]
     * <br>[備  考]
     * @param oldDir 保存元ディレクトリパス
     * @param newDir 保存先ディレクトリパス
     * @param plaImgDir 場所・地図画像ディレクトリ
     * @throws IOToolsException ファイルアクセス時例外
     */
    public void setSavePlaImgData(String oldDir, String newDir, String plaImgDir)
    throws IOToolsException {

        //テンポラリディレクトリにあるファイル名称を取得
        List < String > plaImgList = IOTools.getFileNames(plaImgDir);

        //画面に表示するファイルのリストを作成
        Rsv090PlaceForm rsv090PlaceBean = null;

        if (plaImgList != null) {

            int cmtCnt = 1;

            for (int i = 0; i < plaImgList.size(); i++) {
                rsv090PlaceBean = new Rsv090PlaceForm();

                //ファイル名を取得
                String fileName = plaImgList.get(i);

                if (!fileName.endsWith(GSConstCommon.ENDSTR_OBJFILE)) {
                    continue;
                }

                String name = fileName.replaceFirst(
                        GSConstCommon.ENDSTR_OBJFILE, GSConstCommon.ENDSTR_SAVEFILE);
                long atattiSize = new File(plaImgDir, name).length();
                //オブジェクトファイルを取得
                ObjectFile objFile = new ObjectFile(plaImgDir, fileName);
                Object fObj = objFile.load();
                if (fObj == null) {
                    continue;
                }

                String[] value = fileName.split(GSConstCommon.ENDSTR_OBJFILE);

                //表示用リストへ追加
                Cmn110FileModel fMdl = (Cmn110FileModel) fObj;

                if (getPlaceImgData(oldDir, cmtCnt).getRsv090PlaceFileLabel() == null
                        || getPlaceImgData(oldDir, cmtCnt).getRsv090PlaceFileValue() == null) {

                    //ラベル
                    rsv090PlaceBean.setRsv090PlaceFileLabel(
                            CommonBiz.addAtattiSizeForName(fMdl.getFileName(), atattiSize));

                    //バリュー
                    rsv090PlaceBean.setRsv090PlaceFileValue(value[0]);

                    //コメント
                    rsv090PlaceBean.setRsv090PlaceFileComment("");

                    //コメント表示区分
                    rsv090PlaceBean.setRsv090PlaceFileCommentDspKbn(
                            String.valueOf(GSConstReserve.SISETU_DATA_DSP_OFF));

                } else {

                    //場所画像のデータと添付した場所画像データが一致するかチェック
                    if (getPlaceImgData(oldDir, cmtCnt).getRsv090PlaceFileLabel().equals(
                        CommonBiz.addAtattiSizeForName(fMdl.getFileName(), atattiSize))
                     && getPlaceImgData(oldDir, cmtCnt).getRsv090PlaceFileValue().equals(
                        value[0])) {

                        //ラベル
                        rsv090PlaceBean.setRsv090PlaceFileLabel(
                                getPlaceImgData(oldDir, cmtCnt).getRsv090PlaceFileLabel());

                        //バリュー
                        rsv090PlaceBean.setRsv090PlaceFileValue(
                                getPlaceImgData(oldDir, cmtCnt).getRsv090PlaceFileValue());

                        //コメント
                        rsv090PlaceBean.setRsv090PlaceFileComment(
                                getPlaceImgData(oldDir, cmtCnt).getRsv090PlaceFileComment());

                        //コメント表示区分
                        rsv090PlaceBean.setRsv090PlaceFileCommentDspKbn(
                                getPlaceImgData(oldDir, cmtCnt).getRsv090PlaceFileCommentDspKbn());

                    } else {
                        //ラベル
                        rsv090PlaceBean.setRsv090PlaceFileLabel(
                                CommonBiz.addAtattiSizeForName(fMdl.getFileName(), atattiSize));

                        //バリュー
                        rsv090PlaceBean.setRsv090PlaceFileValue(value[0]);

                        //コメント
                        rsv090PlaceBean.setRsv090PlaceFileComment("");

                        //コメント表示区分
                        rsv090PlaceBean.setRsv090PlaceFileCommentDspKbn(
                                String.valueOf(GSConstReserve.SISETU_DATA_DSP_OFF));

                    }
                }

                //場所画像データ保存
                Rsv090Biz.saveObjFile(rsv090PlaceBean, newDir, cmtCnt);
                cmtCnt++;

                log__.debug("ファイル名 = " + fMdl.getFileName());
                log__.debug("保存ファイル名 = " + fMdl.getSaveFileName());
                log__.debug("ファイルサイズ(byte) =" + fMdl.getAtattiSize());

            }
        }

        //添付ファイルを削除
        IOTools.deleteDir(oldDir);
    }

    /**
     * <br>[機  能] ディレクトリを削除する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param deleteDir 削除ディレクトリパス
     * @throws IOToolsException ファイルアクセス時例外
     */
    public void deleteDir(String deleteDir)
    throws IOToolsException {

        File delDir = new File(deleteDir);

        //添付ファイルを削除
        IOTools.deleteDir(delDir);
    }

    /**
     * <br>[機  能] 選択した場所画像データを削除し、
     *              削除しない場所画像データをテンポラリに保存する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Rsv092ParamModel
     * @param rsv092TempPlaDataDir 場所画像用データ保存用ディレクトリ
     * @param tempPlaDataDir 場所画像用データ参照用ディレクトリ
     * @throws IOToolsException 添付ファイルの読み込みに失敗
     */
    public void detPlaceData(Rsv092ParamModel paramMdl,
                              String rsv092TempPlaDataDir,
                              String tempPlaDataDir)

    throws IOToolsException {

        //テンポラリディレクトリにあるファイル名称を取得
        List <String> fileList = null;

        //削除実行によって参照するディレクトリを分ける
        if (paramMdl.getRsv092DelExeFlg() == 0) {
            fileList = IOTools.getFileNames(tempPlaDataDir);
            paramMdl.setRsv092DelExeFlg(1);
        } else {
            fileList = IOTools.getFileNames(rsv092TempPlaDataDir);
        }

        if (fileList == null) {
            return;
        }

        if (paramMdl.getRsv092selectFiles() == null) {
            log__.debug("削除するファイルのフルパス(オブジェクト) = 戻る1");
            return;
        }
        if (paramMdl.getRsv092selectFiles().length < 1) {
            log__.debug("削除するファイルのフルパス(オブジェクト) = 戻る2");
            return;
        }

        //削除するファイルナンバーを取得
        List<Integer> delNumList = new ArrayList<Integer>();
        String strDelNum = "-1";
        int delNum = -1;

        for (int i = 0; i < paramMdl.getRsv092selectFiles().length; i++) {
            strDelNum = paramMdl.getRsv092selectFiles() [i].substring(8, 11);
            log__.debug("****削除ファイルナンバー : " + strDelNum);
            delNum = Integer.parseInt(strDelNum);
            delNumList.add(delNum);
        }

        //ディレクトリを削除する
        IOTools.deleteDir(rsv092TempPlaDataDir);

        //ディレクトリを作成する
        IOTools.isDirCheck(rsv092TempPlaDataDir, true);

        //ファイルを削除後、場所画像データをテンポラリに保存
        Rsv090PlaceForm nowPlaceImgData = null;
        int count = 1;

        for (int m = 1; m <= fileList.size(); m++) {

            if (delNumList.contains(m)) {
                continue;
            }

            nowPlaceImgData = new Rsv090PlaceForm();

            //ラベル
            nowPlaceImgData.setRsv090PlaceFileLabel(
                    getPlaceImgData(tempPlaDataDir, m).getRsv090PlaceFileLabel());
            //バリュー
            nowPlaceImgData.setRsv090PlaceFileValue(
                    getPlaceImgData(tempPlaDataDir, m).getRsv090PlaceFileValue());
            //コメント
            nowPlaceImgData.setRsv090PlaceFileComment(
                    getPlaceImgData(tempPlaDataDir, m).getRsv090PlaceFileComment());
            //コメント表示区分
            nowPlaceImgData.setRsv090PlaceFileCommentDspKbn(
                    getPlaceImgData(tempPlaDataDir, m).getRsv090PlaceFileCommentDspKbn());

            //保存
            Rsv090Biz.saveObjFile(nowPlaceImgData, rsv092TempPlaDataDir, count);

            count++;
        }
    }

    /**
     * <br>[機  能] オブジェクトファイルから場所画像データを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param filePath ファイルパス
     * @param index インデックス
     * @return Rsv090PlaceForm
     * @throws IOToolsException IOエラー
     */
    public Rsv090PlaceForm getPlaceImgData(
        String filePath, int index) throws IOToolsException {

        Rsv090PlaceForm bean = new Rsv090PlaceForm();

        //オブジェクトファイルを取得
        if (!IOTools.isFileCheck(filePath, GSConstReserve.SAVE_FILENAME + index, false)) {
            return bean;
        }

        ObjectFile objFile = new ObjectFile(filePath, GSConstReserve.SAVE_FILENAME + index);
        Object paramMdlData = objFile.load();
        if (paramMdlData == null) {
            return bean;
        }

        //場所画像データ
        return (Rsv090PlaceForm) paramMdlData;
    }
}