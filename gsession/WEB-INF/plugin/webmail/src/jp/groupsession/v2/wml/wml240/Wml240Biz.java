package jp.groupsession.v2.wml.wml240;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.GSConstWebmail;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.wml.dao.base.WmlAccountDao;
import jp.groupsession.v2.wml.dao.base.WmlMailTemplateDao;
import jp.groupsession.v2.wml.dao.base.WmlMailTemplateFileDao;
import jp.groupsession.v2.wml.model.base.WmlAccountModel;
import jp.groupsession.v2.wml.model.base.WmlMailTemplateModel;

/**
 * <br>[機  能] WEBメール メールテンプレート管理画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml240Biz {

    /**
     * <br>[機  能] メールテンプレートの削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param sessionUserSid セッションユーザSID
     * @throws SQLException SQL実行時例外
     */
    public void deleteTemplate(Connection con, Wml240ParamModel paramMdl, int sessionUserSid)
    throws SQLException {

        int wtpSid = paramMdl.getWmlEditTemplateId();
        //メールテンプレートを削除する
        WmlMailTemplateDao wlDao = new WmlMailTemplateDao(con);
        wlDao.delete(wtpSid);

        //メールテンプレートに関連するバイナリ―情報を削除する
        WmlMailTemplateFileDao tempFileDao = new WmlMailTemplateFileDao(con);
        tempFileDao.removeTemplateBinData(wtpSid, sessionUserSid, new UDate());

        //メールテンプレート_ファイルを削除する
        tempFileDao.delete(wtpSid);

    }

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param userSid ユーザSID
     * @param reqMdl リクエスト情報
     * @throws SQLException SQL実行時例外
     */
    public void setInitData(Connection con,
                             Wml240ParamModel paramMdl,
                             int userSid,
                             RequestModel reqMdl) throws SQLException {

        int templateType = GSConstWebmail.WTP_TYPE_COMMON;
        int selectWacSid = paramMdl.getWmlAccountSid();
        if (paramMdl.getWmlMailTemplateKbn() != GSConstWebmail.MAILTEMPLATE_COMMON) {
            templateType = GSConstWebmail.WTP_TYPE_ACCOUNT;

            //アカウント名取得
            WmlAccountDao wacDao = new WmlAccountDao(con);
            WmlAccountModel wacMdl = wacDao.select(selectWacSid);
            paramMdl.setWml240accountName(wacMdl.getWacName());
        }

        WmlMailTemplateDao templateDao = new WmlMailTemplateDao(con);
        List<WmlMailTemplateModel> templateList
            = templateDao.getTemplateList(templateType, selectWacSid);
        paramMdl.setTemplateList(templateList);
    }

    /**
     * <br>[機  能] 順序変更処理
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param changeKbn 処理区分 0:順序をあげる 1:順序を下げる
     * @param sessionUserSid セッションユーザSID
     * @throws SQLException SQL実行時例外
     */
    public void updateSort(Connection con, Wml240ParamModel paramMdl,
                                    int changeKbn, int sessionUserSid)
    throws SQLException {

        //ラジオ選択値取得
        int wtpSid = paramMdl.getWml240SortRadio();
        if (wtpSid <= 0) {
            return;
        }

        int wacSid = paramMdl.getWmlAccountSid();
        int wtpType = GSConstWebmail.WTP_TYPE_COMMON;
        if (paramMdl.getWmlMailTemplateKbn() != GSConstWebmail.MAILTEMPLATE_COMMON) {
            wtpType = GSConstWebmail.WTP_TYPE_ACCOUNT;
        }

        WmlMailTemplateDao templateDao = new WmlMailTemplateDao(con);
        List<WmlMailTemplateModel> tempList = templateDao.getAllOrder(wtpType, wacSid);
        WmlMailTemplateModel tempMdl = null;
        boolean updateFlg = false;

        if (tempList != null && !tempList.isEmpty()) {

            for (int i = 0; i < tempList.size(); i++) {
                if (tempList.get(i) != null) {
                    tempList.get(i).setWtpOrder(i + 1);
                }
            }


            for (int i = 0; i < tempList.size(); i++) {
                tempMdl = tempList.get(i);
                if (tempMdl.getWtpSid() == wtpSid) {

                    if (changeKbn == GSConstWebmail.SORT_UP) {
                        if (i != 0) {
                            int order = tempMdl.getWtpOrder();
                            if (order > 1) {
                                tempList.get(i).setWtpOrder(order - 1);
                                if (tempList.get(i - 1) != null) {
                                    tempList.get(i - 1).setWtpOrder(order);
                                }
                                updateFlg = true;
                            }
                        }
                    } else if (changeKbn == GSConstWebmail.SORT_DOWN) {

                        if (i != tempList.size()) {
                            int order = tempMdl.getWtpOrder();
                            if (order < tempList.size()) {
                                tempList.get(i).setWtpOrder(order + 1);
                                if (tempList.get(i + 1) != null) {
                                    tempList.get(i + 1).setWtpOrder(order);
                                }
                                updateFlg = true;
                            }
                        }
                    }
                }
            }


            if (updateFlg) {
                UDate now = new UDate();
                for (WmlMailTemplateModel tMdl : tempList) {
                    templateDao.updateOrder(
                            tMdl.getWtpSid(), tMdl.getWtpOrder(), sessionUserSid, now);
                }
            }

        }



//        int order = templateDao.getOrder(wtpSid);
//        int nextOrder = 0;
//        if (changeKbn == GSConstWebmail.SORT_UP) {
//            nextOrder = order - 1;
//            if (nextOrder <= 0) {
//                return;
//            }
//        } else if (changeKbn == GSConstWebmail.SORT_DOWN) {
//            nextOrder = order + 1;
//            int maxOrder = templateDao.getMaxOrder(wtpType, wacSid);
//            if (nextOrder > maxOrder) {
//                return;
//            }
//        }
//
//
//        WmlMailTemplateModel nextModel = templateDao.selectByOrder(wtpType, wacSid, nextOrder);
//        if (nextModel != null) {
//            UDate now = new UDate();
//            templateDao.updateOrder(wtpSid, nextOrder, sessionUserSid, now);
//            templateDao.updateOrder(nextModel.getWtpSid(), order, sessionUserSid, now);
//        }
    }
}
