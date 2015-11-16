package jp.groupsession.v2.bbs.bbs050;

import java.sql.Connection;

import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.bbs.BbsBiz;
import jp.groupsession.v2.bbs.dao.BbsUserDao;
import jp.groupsession.v2.bbs.model.BbsUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 掲示板 表示設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bbs050Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bbs050Biz.class);

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param userSid ユーザSID
     * @param reqMdl リクエスト情報
     * @throws Exception 実行例外
     */
    public void setInitData(Bbs050ParamModel paramMdl, Connection con, int userSid,
                            RequestModel reqMdl)
    throws Exception {
        log__.debug("START");

        //掲示板個人情報を取得
        BbsBiz bbsBiz = new BbsBiz();
        BbsUserModel bUserMdl = bbsBiz.getBbsUserData(con, userSid);

        paramMdl.setBbs050forumCnt(bUserMdl.getBurForCnt());
        paramMdl.setBbs050threCnt(bUserMdl.getBurThreCnt());
        paramMdl.setBbs050writeCnt(bUserMdl.getBurWrtCnt());
        paramMdl.setBbs050newCnt(bUserMdl.getBurNewCnt());
        paramMdl.setBbs050wrtOrder(bUserMdl.getBurWrtlistOrder());
        paramMdl.setBbs050threImage(bUserMdl.getBurThreImage());
        paramMdl.setBbs050tempImage(bUserMdl.getBurTempImage());
        paramMdl.setBbs050threadFlg(bUserMdl.getBurSubNewThre());
        paramMdl.setBbs050forumFlg(bUserMdl.getBurSubForum());
        paramMdl.setBbs050midokuTrdFlg(bUserMdl.getBurSubUnchkThre());

        paramMdl.setNewCntLabel(reqMdl);
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
    public void updateBbsUserConf(Bbs050ParamModel paramMdl, Connection con, int userSid)
    throws Exception {
        log__.debug("START");

        UDate now = new UDate();

        //掲示板個人情報を取得
        BbsBiz bbsBiz = new BbsBiz();
        BbsUserModel bUserMdl = bbsBiz.getBbsUserData(con, userSid);

        int forumCnt = __getInputValue(paramMdl.getBbs050forumCnt(),
                                    Bbs050Form.FORUMCNTVALUE,
                                    10);
        int threCnt = __getInputValue(paramMdl.getBbs050threCnt(),
                                    Bbs050Form.THRECNTVALUE,
                                    10);
        int writeCnt = __getInputValue(paramMdl.getBbs050writeCnt(),
                                    Bbs050Form.WRITECNTVALUE,
                                    10);
        int newCnt = __getInputValue(paramMdl.getBbs050newCnt(),
                                    Bbs050Form.NEWCNTVALUE,
                                    1);
        int smlNtf = bUserMdl.getBurSmlNtf();
        int threMainCnt = bUserMdl.getBurThreMainCnt();
        int wrtListOrder = paramMdl.getBbs050wrtOrder();
        int imageDsp = paramMdl.getBbs050threImage();
        int imageTempDsp = paramMdl.getBbs050tempImage();
        int chkedDsp = bUserMdl.getBurMainChkedDsp();

        int newThredFlg = paramMdl.getBbs050threadFlg();
        int forumIchiranFlg = paramMdl.getBbs050forumFlg();
        int uncheckThredFlg = paramMdl.getBbs050midokuTrdFlg();


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
        bbsUserMdl.setBurTempImage(imageTempDsp);
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
