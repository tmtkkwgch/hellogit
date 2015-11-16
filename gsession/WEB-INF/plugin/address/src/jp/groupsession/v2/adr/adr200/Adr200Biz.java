package jp.groupsession.v2.adr.adr200;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.adr.GSConstAddress;
import jp.groupsession.v2.adr.adr010.Adr010Biz;
import jp.groupsession.v2.adr.dao.AdrLabelCategoryDao;
import jp.groupsession.v2.adr.dao.AdrLabelDao;
import jp.groupsession.v2.adr.model.AdrLabelCategoryModel;
import jp.groupsession.v2.adr.model.AdrLabelModel;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.model.RequestModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] アドレス帳 ラベル登録ポップアップのビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Adr200Biz extends Adr010Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Adr200Biz.class);
    /** リクエスト情報 */
    protected RequestModel reqMdl_ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     */
    public Adr200Biz(RequestModel reqMdl) {
        super(reqMdl);
        reqMdl_ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Adr200ParamModel
     * @throws SQLException SQL実行時例外
     */
    public void setInitData(Connection con, Adr200ParamModel paramMdl) throws SQLException {

        if (paramMdl.getAdr200selectCategory() <= 0) {
            paramMdl.setAdr200selectCategory(GSConstAddress.LABEL_CATEGORY_NOSET);
        }

        //カテゴリコンボを設定
        ArrayList<LabelValueBean> categoryList = new ArrayList<LabelValueBean>();
        AdrLabelCategoryDao categoryDao = new AdrLabelCategoryDao(con);
        List<AdrLabelCategoryModel> modelList = categoryDao.select();
        for (AdrLabelCategoryModel model : modelList) {
            String category = model.getAlcName();
            String value = String.valueOf(model.getAlcSid());
            categoryList.add(new LabelValueBean(category, value));
        }
        paramMdl.setAdr200category(categoryList);
    }

    /**
     * <br>[機  能] ラベル情報の登録を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Adr200ParamModel
     * @param mtCon 採番コントローラ
     * @param sessionUserSid セッションユーザSID
     * @throws SQLException SQL実行例外
     * @return labelCnt ラベル件数
     */
    public int entryLabelData(Connection con, Adr200ParamModel paramMdl, MlCountMtController mtCon,
                                int sessionUserSid)
    throws SQLException {

        log__.debug("START");


        UDate now = new UDate();

        AdrLabelModel labelMdl = new AdrLabelModel();
        labelMdl.setAlbAuid(sessionUserSid);
        labelMdl.setAlbAdate(now);
        labelMdl.setAlbEuid(sessionUserSid);
        labelMdl.setAlbEdate(now);
        labelMdl.setAlcSid(paramMdl.getAdr200selectCategory());

        AdrLabelDao labelDao = new AdrLabelDao(con);
        StringTokenizer labelToken = new StringTokenizer(paramMdl.getAdr200labelName(), ",");

        //登録ラベル件数
        int labelCnt = 0;

        while (labelToken.hasMoreTokens()) {
            String labelName = labelToken.nextToken().trim();

            if (labelName.length() > 0) {
                long albSid = mtCon.getSaibanNumber(GSConst.SBNSID_ADDRESS,
                                                    GSConstAddress.SBNSID_SUB_LABEL,
                                                    sessionUserSid);
                labelMdl.setAlbSid((int) albSid);
                labelMdl.setAlbName(labelName);
                labelDao.insertNewLabel(labelMdl);
                labelCnt++;
            }
        }

        log__.debug("End");
        return labelCnt;

    }
}
