package jp.groupsession.v2.bmk.bmk130;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.bmk.GSConstBookmark;
import jp.groupsession.v2.bmk.dao.BmkUconfDao;
import jp.groupsession.v2.bmk.model.BmkUconfModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 個人設定 表示件数設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bmk130Biz {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bmk130Biz.class);

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Bmk130ParamModel
     * @param sessionUserSid セッションユーザSID
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setInitData(
            Bmk130ParamModel paramMdl,
            int sessionUserSid,
            Connection con) throws SQLException {

        log__.debug("start");

        //表示件数コンボを設定
        paramMdl.setBmk130DspCountList(__getDspCount());

        //DBより現在の設定を取得する。(なければデフォルト)
        BmkUconfDao dao = new BmkUconfDao(con);
        BmkUconfModel model = dao.select(sessionUserSid);

        if (model == null) {
            paramMdl.setBmk130DspCount(GSConstBookmark.DEFAULT_BMKCOUNT);
        } else {
            paramMdl.setBmk130DspCount(
                    NullDefault.getString(
                            paramMdl.getBmk130DspCount(),
                            (String.valueOf(model.getBucCount()))));
        }
    }

    /**
     * <br>[機  能] 表示件数コンボの表示を設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @return labelList ラベルリスト
     */
    private List <LabelValueBean> __getDspCount() {

        List <LabelValueBean> labelList = new ArrayList <LabelValueBean>();

        for (int count = 10; count <= 50; count += 10) {
            String strCount = String.valueOf(count);
            labelList.add(new LabelValueBean(strCount, strCount));
        }

        return labelList;
    }

    /**
     * <br>[機  能] 表示件数設定をDBに登録する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Bmk130ParamModel
     * @param sessionUserSid ユーザSID
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     * @return addEditFlg 登録モード(0:登録 1:編集)
     */
    public int setBmkUconfSetting(
            Bmk130ParamModel paramMdl,
            int sessionUserSid,
            Connection con) throws SQLException {

        boolean commitFlg = false;

        int addEditFlg = 0;
        try {
            //DBの存在確認
            BmkUconfDao dao = new BmkUconfDao(con);
            BmkUconfModel model = dao.select(sessionUserSid);
            //画面値セット
            BmkUconfModel crtMdl = createBmkUconfData(paramMdl, sessionUserSid);

            if (model == null) {
                //登録処理
                crtMdl.setBucMainMy(GSConstBookmark.DSP_YES);
                crtMdl.setBucMainNew(GSConstBookmark.DSP_YES);
                dao.insert(crtMdl);
            } else {
                crtMdl.setBucMainMy(model.getBucMainMy());
                crtMdl.setBucMainNew(model.getBucMainNew());
                dao.update(crtMdl);
                addEditFlg = 1;
            }
            commitFlg = true;

        } catch (SQLException e) {
            log__.error("", e);
            throw e;
        } finally {
            if (commitFlg) {
                con.commit();
            }
        }
        return addEditFlg;
    }

    /**
     * <br>[機  能] 表示件数設定情報を作成
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Bmk130ParamModel
     * @param usrSid ユーザSID
     * @return KhmSyaikbnModel 社員区分情報
     */
    public BmkUconfModel createBmkUconfData(
            Bmk130ParamModel paramMdl, int usrSid) {

        UDate nowDate = new UDate();
        BmkUconfModel mdl = new BmkUconfModel();

        mdl.setUsrSid(usrSid);
        mdl.setBucCount(Integer.parseInt(paramMdl.getBmk130DspCount()));
        mdl.setBucAuid(usrSid);
        mdl.setBucAdate(nowDate);
        mdl.setBucEuid(usrSid);
        mdl.setBucEdate(nowDate);

        return mdl;
    }
}
