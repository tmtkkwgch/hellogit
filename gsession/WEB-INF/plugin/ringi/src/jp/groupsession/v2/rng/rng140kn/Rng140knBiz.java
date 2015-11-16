package jp.groupsession.v2.rng.rng140kn;

import java.sql.Connection;

import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.rng.dao.RngTemplateCategoryDao;
import jp.groupsession.v2.rng.model.RngTemplateCategoryModel;

/**
 * <br>[機  能] 稟議 テンプレートカテゴリ登録確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng140knBiz {

    /**
     * <br>[機  能] 登録処理を行います
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param usrSid ユーザSID
     * @param cntCon MlCountMtController
     * @throws Exception 例外
     */
    public void insert(Rng140knParamModel paramMdl,
                       Connection con,
                       int usrSid,
                       MlCountMtController cntCon)
                       throws Exception {

        RngTemplateCategoryDao dao = new RngTemplateCategoryDao(con);

        RngTemplateCategoryModel insertMdl = createModel(paramMdl, con, usrSid, cntCon);
        dao.insert(insertMdl);
    }

    /**
     * <br>[機  能] 更新処理を行います
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param usrSid ユーザSID
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @throws Exception 例外
     */
    public void update(Rng140knParamModel paramMdl,
                       Connection con,
                       int usrSid,
                       MlCountMtController cntCon)
                       throws Exception {

        RngTemplateCategoryDao dao = new RngTemplateCategoryDao(con);

        RngTemplateCategoryModel updateMdl = createModel(paramMdl, con, usrSid, cntCon);
        dao.update(updateMdl);


    }

    /**
     * <br>[機  能] 登録・更新用のmodel作成を行います
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param usrSid ユーザSID
     * @param cntCon MlCountMtController
     * @return RngTemplateCategoryModel 登録model
     * @throws Exception 例外
     */
    public RngTemplateCategoryModel createModel(Rng140knParamModel paramMdl,
                                                Connection con,
                                                int usrSid,
                                                MlCountMtController cntCon)
                                                throws Exception {

        RngTemplateCategoryDao dao = new RngTemplateCategoryDao(con);
        RngTemplateCategoryModel model = new RngTemplateCategoryModel();
        UDate date = new UDate();

        //登録
        if (paramMdl.getRng140ProcMode() == RngConst.RNG_CMDMODE_ADD) {
            //カテゴリSID採番
            int catSid = (int) cntCon.getSaibanNumber(RngConst.SBNSID_RINGI,
                                                       RngConst.SBNSID_SUB_RINGI_TEMPLATE_CATEGORY,
                                                       usrSid);
            model.setRtcSid(catSid);

            //ソートの最大値+1
            if (paramMdl.getRngTemplateMode() == RngConst.RNG_TEMPLATE_SHARE) {
                model.setRtcSort(dao.getMaxSortShare() + 1);
            } else if (paramMdl.getRngTemplateMode() == RngConst.RNG_TEMPLATE_PRIVATE) {
                model.setRtcSort(dao.getMaxSortPrivate(usrSid) + 1);
            }
        //更新
        } else if (paramMdl.getRng140ProcMode() == RngConst.RNG_CMDMODE_EDIT) {
            model.setRtcSid(paramMdl.getRng140CatSid());
        }

        model.setRtcName(paramMdl.getRng140CatName());
        model.setRtcType(paramMdl.getRngTemplateMode());
        model.setRtcAdate(date);
        model.setRtcAuid(usrSid);
        model.setRtcEdate(date);
        model.setRtcEuid(usrSid);
        if (paramMdl.getRngTemplateMode() == RngConst.RNG_TEMPLATE_PRIVATE) {
            model.setUsrSid(usrSid);
        }

        return model;
    }

}
