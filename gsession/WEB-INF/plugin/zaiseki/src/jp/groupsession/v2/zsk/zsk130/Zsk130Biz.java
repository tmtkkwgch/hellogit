package jp.groupsession.v2.zsk.zsk130;

import java.sql.Connection;
import java.util.ArrayList;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.zsk.GSConstZaiseki;
import jp.groupsession.v2.zsk.biz.ZsjCommonBiz;
import jp.groupsession.v2.zsk.model.ZskSortModel;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 在席管理 個人設定 座席表メンバー表示設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Zsk130Biz {

    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param reqMdl RequestModel
     */
    public Zsk130Biz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Zsk130ParamModel
     * @param umodel ユーザ基本情報モデル
     * @param con コネクション
     * @throws Exception SQL実行エラー
     */
    public void setInitData(Zsk130ParamModel paramMdl,
                             BaseUserModel umodel,
                             Connection con) throws Exception {


        //表示順設定
        ZsjCommonBiz cmnBiz = new ZsjCommonBiz(reqMdl__);
        ZskSortModel sortMdl = cmnBiz.getSortData(con, umodel.getUsrsid());
        paramMdl.setZsk130SortKey1(sortMdl.getSort1());
        paramMdl.setZsk130SortKey2(sortMdl.getSort2());
        paramMdl.setZsk130SortOrder1(sortMdl.getOrder1());
        paramMdl.setZsk130SortOrder2(sortMdl.getOrder2());

        setLabelData(paramMdl);

    }

    /**
     * <br>[機  能] コンボ設定を行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Zsk130ParamModel
     * @throws Exception SQL実行エラー
     */
    public void setLabelData(Zsk130ParamModel paramMdl) throws Exception {

        ArrayList<LabelValueBean> sortLabel = new ArrayList<LabelValueBean>();
        GsMessage gsMsg = new GsMessage(reqMdl__);
        String name = gsMsg.getMessage("cmn.name");
        String number = gsMsg.getMessage("cmn.employee.staff.number");
        String post = gsMsg.getMessage("cmn.post");
        String birth = gsMsg.getMessage("cmn.birthday");
        String zaiseki = gsMsg.getMessage("zsk.20");
        String comment = gsMsg.getMessage("zsk.23");
        String sortkey1 = gsMsg.getMessage("cmn.sortkey") + "1";
        String sortkey2 = gsMsg.getMessage("cmn.sortkey") + "2";
        String[] sortZskText = {name, number, post, birth, zaiseki, comment,
                sortkey1, sortkey2};

        for (int i = 0; i < sortZskText.length; i++) {
            String label = sortZskText[i];
            String value = Integer.toString(GSConstZaiseki.SORT_KEY_ZSK[i]);
            sortLabel.add(new LabelValueBean(label, value));
        }
        paramMdl.setZsk130SortKeyLabel(sortLabel);

    }

}