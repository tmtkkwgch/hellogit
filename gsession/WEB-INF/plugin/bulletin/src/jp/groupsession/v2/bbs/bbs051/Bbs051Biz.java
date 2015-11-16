package jp.groupsession.v2.bbs.bbs051;

import java.sql.Connection;

import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.bbs.BbsBiz;
import jp.groupsession.v2.bbs.dao.BbsUserDao;
import jp.groupsession.v2.bbs.model.BbsUserModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 掲示板 メイン表示設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bbs051Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bbs051Biz.class);

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param userSid ユーザSID
     * @throws Exception 実行例外
     */
    public void setInitData(Bbs051ParamModel paramMdl, Connection con, int userSid)
    throws Exception {
        log__.debug("START");

        //掲示板個人情報を取得
        BbsBiz bbsBiz = new BbsBiz();
        BbsUserModel bUserMdl = bbsBiz.getBbsUserData(con, userSid);

        paramMdl.setBbs051threMainCnt(bUserMdl.getBurThreMainCnt());
        paramMdl.setBbs051mainChkedDsp(bUserMdl.getBurMainChkedDsp());

        log__.debug("End");
    }

    /**
     * <br>[機  能] 掲示板個人設定情報を更新する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param userSid ユーザSID
     * @throws Exception 実行例外
     */
    public void updateBbsUserConf(Bbs051ParamModel paramMdl, Connection con, int userSid)
    throws Exception {
        log__.debug("START");

        UDate now = new UDate();
        BbsBiz bbsBiz = new BbsBiz();
        BbsUserModel bUserMdl = bbsBiz.getBbsUserData(con, userSid);


        int forumCnt = bUserMdl.getBurForCnt();
        int threCnt = bUserMdl.getBurThreCnt();
        int writeCnt = bUserMdl.getBurWrtCnt();
        int newCnt = bUserMdl.getBurNewCnt();
        int smlNtf = bUserMdl.getBurSmlNtf();
        int threMainCnt = __getInputValue(paramMdl.getBbs051threMainCnt(),
                                    Bbs051Form.THREMAINCNTVALUE,
                                    10);
        int wrtListOrder = bUserMdl.getBurWrtlistOrder();
        int imageDsp = bUserMdl.getBurThreImage();
        int chkedDsp = paramMdl.getBbs051mainChkedDsp();

        int newThredFlg = bUserMdl.getBurSubNewThre();
        int forumIchiranFlg = bUserMdl.getBurSubForum();
        int uncheckThredFlg = bUserMdl.getBurSubUnchkThre();

        BbsUserModel bbsUserMdl = new BbsUserModel();
        bbsUserMdl.setUsrSid(userSid);
        bbsUserMdl.setBurForCnt(forumCnt);
        bbsUserMdl.setBurThreCnt(threCnt);
        bbsUserMdl.setBurWrtCnt(writeCnt);
        bbsUserMdl.setBurNewCnt(newCnt);
        bbsUserMdl.setBurSmlNtf(smlNtf);
        bbsUserMdl.setBurThreMainCnt(threMainCnt);
        bbsUserMdl.setBurWrtlistOrder(wrtListOrder);
        bbsUserMdl.setBurThreImage(imageDsp);
        bbsUserMdl.setBurMainChkedDsp(chkedDsp);
        bbsUserMdl.setBurEuid(userSid);
        bbsUserMdl.setBurEdate(now);

        bbsUserMdl.setBurSubNewThre(newThredFlg);
        bbsUserMdl.setBurSubForum(forumIchiranFlg);
        bbsUserMdl.setBurSubUnchkThre(uncheckThredFlg);

        BbsUserDao bbsUserDao = new BbsUserDao(con);
        if (bbsUserDao.update(bbsUserMdl) <= 0) {
            bbsUserMdl.setBurAuid(userSid);
            bbsUserMdl.setBurAdate(now);

            bbsUserDao.insert(bbsUserMdl);
        }

        log__.debug("End");
    }

    /**
     * <br>[機  能] 入力値のチェックを行う
     * <br>[解  説] 入力値が選択項目一覧に含まれない値の場合、初期値を返す
     * <br>[備  考]
     * @param value 入力値
     * @param labels 選択項目一覧
     * @param initValue 初期値
     * @return 値
     */
    private int __getInputValue(int value, String[] labels, int initValue) {
        int ret = initValue;

        for (String label : labels) {
            if (Integer.parseInt(label) == value) {
                ret = value;
                break;
            }
        }

        return ret;
    }

}
