package jp.groupsession.v2.bmk.bmk060;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.sjts.util.StringUtilHtml;
import jp.groupsession.v2.bmk.GSConstBookmark;
import jp.groupsession.v2.bmk.dao.BmkLabelDao;
import jp.groupsession.v2.bmk.dao.BmkLabelDataDao;
import jp.groupsession.v2.bmk.model.BmkLabelDataModel;
import jp.groupsession.v2.bmk.model.BmkLabelModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.model.base.CmnGroupmModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] ラベル登録画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 * @author JTS
 */
public class Bmk060Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bmk060Biz.class);

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Bmk060ParamModel
     * @param userSid ユーザSID
     * @param con コネクション
     * @throws SQLException 実行例外
     */
    public void setInitData(Bmk060ParamModel paramMdl, int userSid, Connection con)
    throws SQLException {
        log__.debug("START");

        if (paramMdl.getBmk010mode() == GSConstBookmark.BMK_KBN_GROUP) {
            //グループブックマーク
            //グループ名の設定
            GroupDao grpDao = new GroupDao(con);
            CmnGroupmModel grpMdl = grpDao.getGroup(paramMdl.getBmk010groupSid());
            paramMdl.setBmk050GrpName(
                    StringUtilHtml.transToHTmlPlusAmparsant(grpMdl.getGrpName()));
        }

        if (paramMdl.getBmk060LblName() == null
            && paramMdl.getBmk050ProcMode() == GSConstBookmark.LABEL_MODE_EDIT) {
            //処理モード：編集モード
            BmkLabelDao dao = new BmkLabelDao(con);
            BmkLabelModel model = dao.select(paramMdl.getBmk050LblSid());
            //ラベル名の設定
            paramMdl.setBmk060LblName(model.getBlbName());
        }

        if (paramMdl.getBmk060LblKbn() == GSConstBookmark.LABEL_TOGO_YES) {
            //ラベル統合

            //指定ラベル情報取得
            BmkLabelDataDao dao = new BmkLabelDataDao(con);
            ArrayList<BmkLabelDataModel> model
                = dao.select(paramMdl.getBmk010mode(), userSid, paramMdl.getBmk010groupSid());

            List<LabelValueBean> leftList = new ArrayList <LabelValueBean>();
            List<LabelValueBean> rightList = new ArrayList <LabelValueBean>();

            List<String> selectList = new ArrayList<String>();
            if (paramMdl.getBmk060LabelList() != null) {
                selectList = Arrays.asList(paramMdl.getBmk060LabelList());
            }

            StringBuilder labelName = null;
            for (BmkLabelDataModel mdl : model) {

                if (!selectList.contains(Integer.toString(mdl.getBlbSid()))
                    && mdl.getBlbSid() != paramMdl.getBmk050LblSid()) {
                    //選択リスト用ラベル名設定
                    labelName = new StringBuilder("");
                    labelName.append(mdl.getBlbName());
                    labelName.append(" ");
                    labelName.append("(" + mdl.getBlbCnt() + ")");
                    rightList.add(
                            new LabelValueBean(labelName.toString()
                                , Integer.toString(mdl.getBlbSid())));
                }
            }
            paramMdl.setBmk060RightLabelList(rightList);
            if (paramMdl.getBmk060LabelList() != null) {
                //選択済リスト用ラベル名設定
                model = dao.select(paramMdl.getBmk060LabelList());

                for (BmkLabelDataModel mdl : model) {
                    labelName = new StringBuilder("");
                    labelName.append(mdl.getBlbName());
                    labelName.append(" ");
                    labelName.append("(" + mdl.getBlbCnt() + ")");
                    leftList.add(
                            new LabelValueBean(labelName.toString()
                                , Integer.toString(mdl.getBlbSid())));
                }
                paramMdl.setBmk060LeftLabelList(leftList);
            }
        }
        log__.debug("End");
    }
}
