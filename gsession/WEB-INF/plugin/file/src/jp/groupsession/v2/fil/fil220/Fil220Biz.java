package jp.groupsession.v2.fil.fil220;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.fil.dao.FileCabinetDao;
import jp.groupsession.v2.fil.model.FileCabinetModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 管理者設定 キャビネット管理設定画面のビジネスロジック
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Fil220Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Fil220Biz.class);

    /** DBコネクション */
    private Connection con__ = null;

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public Fil220Biz(Connection con) {
        con__ = con;
    }

    /**
     * <br>[機  能] 初期表示を設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil220ParamModel
     * @throws SQLException SQL実行例外
     */
    public void setInitData(Fil220ParamModel paramMdl) throws SQLException {

        log__.debug("fil020Biz Start");

        FileCabinetDao cabiDao = new FileCabinetDao(con__);
        List<FileCabinetModel> cabiList = cabiDao.select();

        paramMdl.setFil220cabinetList(cabiList);


    }

    /**
     * <br>[機  能] 表示順を上へ移動する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil220ParamModel
     * @param buMdl BaseUserModel
     * @throws SQLException SQL実行例外
     */
    public void updateSortUp(Fil220ParamModel paramMdl, BaseUserModel buMdl) throws SQLException {

        FileCabinetDao cabiDao = new FileCabinetDao(con__);
        List<FileCabinetModel> cabiList = null;

        //キャビネット登録数を取得する。
        int cabiCount = cabiDao.countCabinet();

        if (cabiCount < 1) {
            return;
        }

        //表示順の降順でキャビネット情報を取得する。
        cabiList = cabiDao.selectSortDesc();

        boolean editFlg = false;
        int sort = cabiCount;
        int sltCabiSid = NullDefault.getInt(paramMdl.getFil220sltRadio(), -1);
        UDate now = new UDate();

        //表示順の更新を行う。
        for (FileCabinetModel model : cabiList) {
            model.setFcbEuid(buMdl.getUsrsid());
            model.setFcbEdate(now);

            if (sltCabiSid == model.getFcbSid()) {

                if (sort == 1) {
                    //表示順最初のキャビネット選択した場合
                    model.setFcbSort(sort);
                } else {
                    model.setFcbSort(sort - 1);
                }
                cabiDao.updateSort(model);
                editFlg = true;

            } else if (editFlg) {

                model.setFcbSort(sort);
                cabiDao.updateSort(model);
                editFlg = false;
                sort = sort - 2;

            } else {
                model.setFcbSort(sort);
                cabiDao.updateSort(model);
                sort--;

            }
        }

    }

    /**
     * <br>[機  能] 表示順を下へ移動する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil220ParamModel
     * @param buMdl BaseUserModel
     * @throws SQLException SQL実行例外
     */
    public void updateSortDown(Fil220ParamModel paramMdl, BaseUserModel buMdl) throws SQLException {


        FileCabinetDao cabiDao = new FileCabinetDao(con__);
        List<FileCabinetModel> cabiList = null;

        //キャビネット登録数を取得する。
        int cabiCount = cabiDao.countCabinet();

        if (cabiCount < 1) {
            return;
        }

        //キャビネット情報を取得する。
        cabiList = cabiDao.select();

        boolean editFlg = false;
        int sort = 1;
        int sltCabiSid = NullDefault.getInt(paramMdl.getFil220sltRadio(), -1);
        UDate now = new UDate();

        //表示順の更新を行う。
        for (FileCabinetModel model : cabiList) {
            model.setFcbEuid(buMdl.getUsrsid());
            model.setFcbEdate(now);

            if (sltCabiSid == model.getFcbSid()) {

                if (cabiCount == sort) {
                    //表示順が最後の場合
                    model.setFcbSort(sort);
                } else {
                    model.setFcbSort(sort + 1);
                }

                cabiDao.updateSort(model);
                editFlg = true;

            } else if (editFlg) {

                model.setFcbSort(sort);
                cabiDao.updateSort(model);
                editFlg = false;
                sort = sort + 2;

            } else {

                model.setFcbSort(sort);
                cabiDao.updateSort(model);
                sort++;
            }
        }
    }
}
