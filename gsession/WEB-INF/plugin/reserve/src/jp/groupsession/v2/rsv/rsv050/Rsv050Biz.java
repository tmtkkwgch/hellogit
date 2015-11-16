package jp.groupsession.v2.rsv.rsv050;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.sjts.util.StringUtil;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.base.CmnBelongmDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rsv.AbstractReserveBiz;
import jp.groupsession.v2.rsv.dao.RsvSisGrpDao;
import jp.groupsession.v2.rsv.model.RsvSisGrpModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 施設予約 施設グループ情報設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv050Biz extends AbstractReserveBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rsv050Biz.class);
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
    public Rsv050Biz(RequestModel reqMdl, Connection con) {
        reqMdl_ = reqMdl;
        con_ = con;
    }

    /**
     * <br>[機  能] 管理者フラグを設定する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv050ParamModel
     * @throws SQLException SQL実行時例外
     */
    public void setAdmFlg(Rsv050ParamModel paramMdl) throws SQLException {

        paramMdl.setRsvAdmFlg(_isAdmin(reqMdl_, con_));

    }

    /**
     * <br>[機  能] 処理権限判定
     * <br>[解  説]
     * <br>[備  考]
     *
     * @return true:処理実行可 false:処理実行負荷
     * @throws SQLException SQL実行時例外
     */
    public boolean isPossibleToProcess()
        throws SQLException {

       /***********************************************
         *
         * 下記の条件を満たす施設グループが1つでも
         * 存在する場合は処理可能
         *
         * 1:施設グループ管理者に設定されている
         * 2:施設グループに権限設定を行わない
         *
         ***********************************************/
        return _isAllGroupEditAuthority(reqMdl_, con_);

    }

    /**
     * <br>[機  能] 施設グループ一覧をセットする
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv050ParamModel
     * @throws SQLException SQL実行時例外
     */
    public void setGroupList(Rsv050ParamModel paramMdl) throws SQLException {

        RsvSisGrpDao dao = new RsvSisGrpDao(con_);
        ArrayList<RsvSisGrpModel> grpList = new ArrayList<RsvSisGrpModel>();
        boolean admFlg = paramMdl.isRsvAdmFlg();

        //管理者
        if (admFlg) {
            log__.debug("管理者なので全データ取得");
            grpList = dao.selectAllGroupData();
        //管理者以外
        } else {
            log__.debug("管理者ではないので修正可能なデータのみ取得");
            BaseUserModel usrMdl = _getSessionUserModel(reqMdl_);
            //セッションユーザの所属グループを格納
            CmnBelongmDao bdao = new CmnBelongmDao(con_);
            ArrayList<Integer> belongGpSidList = bdao.selectUserBelongGroupSid(usrMdl.getUsrsid());
            grpList = dao.selectGrpListNotAdmin(usrMdl.getUsrsid(), belongGpSidList);
        }

        int index = 1;
        String[] sortKey = new String[grpList.size()];
        ArrayList<Rsv050Model> retList = new ArrayList<Rsv050Model>();
        String firstRecordKey = null;

        for (RsvSisGrpModel mdl : grpList) {
            Rsv050Model retMdl = new Rsv050Model();
            retMdl.setRsgSid(mdl.getRsgSid());
            retMdl.setRsgName(mdl.getRsgName());

            String paramMdlatSid =
                StringUtil.toDecFormat(
                        mdl.getRsgSid(), "0000000000");

            String paramMdlatSort =
                StringUtil.toDecFormat(
                        mdl.getRsgSort(), "0000000000");

            String radioKey = paramMdlatSid + paramMdlatSort + String.valueOf(index);
            sortKey[index - 1] = radioKey;
            retMdl.setRadioKey(radioKey);
            retList.add(retMdl);

            if (index == 1) {
                firstRecordKey = radioKey;
            }
            index += 1;
        }

        //ソート順ラジオが未選択の場合は1レコード目を選択済みにする
        if (StringUtil.isNullZeroString(paramMdl.getRsv050SortRadio())) {
            paramMdl.setRsv050SortRadio(firstRecordKey);
        } else {
            //ソート順ラジオが選択済みの場合はキー値が存在するか精査
            if (sortKey != null && sortKey.length > 0) {
                boolean exists = false;
                String selectKey = paramMdl.getRsv050SortRadio();
                for (String key : sortKey) {
                    if (key.equals(selectKey)) {
                        exists = true;
                        break;
                    }
                }
                //キー値が見つからない(他のユーザにより削除された等)の場合
                if (!exists) {
                    paramMdl.setRsv050SortRadio(firstRecordKey);
                }
            }
        }

        paramMdl.setRsv050KeyList(sortKey);
        paramMdl.setRsv050GroupList(retList);
    }

    /**
     * <br>[機  能] 順序変更処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv050ParamModel
     * @param changeKbn 処理区分 0:順序をあげる 1:順序を下げる
     * @throws SQLException SQL実行時例外
     */
    public void updateSort(Rsv050ParamModel paramMdl, int changeKbn)
        throws SQLException {

        //画面表示全キーリスト取得
        String[] keyList = paramMdl.getRsv050KeyList();
        if (keyList == null || keyList.length < 1) {
            return;
        }

        //ラジオ選択値取得
        String selectedKey = paramMdl.getRsv050SortRadio();

        //キーから画面表示順(ソート順ではない)を取得
        int selectedKeyDispNum =
            Integer.parseInt(
                    selectedKey.substring(20));
        log__.debug("画面表示順 = " + selectedKeyDispNum);

        //画面の最初に表示されている項目 + 順位を上げる
        if (selectedKeyDispNum == 0 && changeKbn == 0) {
            return;
        //画面の最後に表示されている項目 + 順位を下げる
        } else if (selectedKeyDispNum == keyList.length
                && changeKbn == 1) {
            return;
        }

        //選択された項目の施設グループSID + ソート順
        int motoSid = Integer.parseInt(selectedKey.substring(0, 10));
        int motoSort = Integer.parseInt(selectedKey.substring(10, 20));

        int sakiSid = -1;
        int sakiSort = -1;
        int target = selectedKeyDispNum;

        if (changeKbn == 0) {
            target -= 1;
        } else if (changeKbn == 1) {
            target += 1;
        }

        //画面表示全キーから入れ替え先のデータを探す
        for (String allKey : keyList) {

            int allKeyDispNum =
                Integer.parseInt(
                        allKey.substring(20));

            if (allKeyDispNum == target) {
                sakiSid = Integer.parseInt(allKey.substring(0, 10));
                sakiSort = Integer.parseInt(allKey.substring(10, 20));
                break;
            }
        }

        if (sakiSid == -1 || sakiSort == -1) {
            return;
        }

        //順序入れ替え
        RsvSisGrpDao dao = new RsvSisGrpDao(con_);
        dao.updateSort(motoSid, motoSort, sakiSid, sakiSort);

        //新しいキーを設定
        String newSid = StringUtil.toDecFormat(motoSid, "0000000000");
        String newSort = StringUtil.toDecFormat(sakiSort, "0000000000");
        String newDispNum = String.valueOf(target);
        String newKey = newSid + newSort + newDispNum;
        paramMdl.setRsv050SortRadio(newKey);
    }
}