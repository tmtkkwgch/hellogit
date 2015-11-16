package jp.groupsession.v2.sch.sch093;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.sch.biz.SchCommonBiz;
import jp.groupsession.v2.sch.dao.SchAdmConfDao;
import jp.groupsession.v2.sch.dao.SchPriConfDao;
import jp.groupsession.v2.sch.model.SchAdmConfModel;
import jp.groupsession.v2.sch.model.SchLabelValueModel;
import jp.groupsession.v2.sch.model.SchPriConfModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] スケジュール 個人設定 グループメンバー表示設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sch093Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sch093Biz.class);
    /** リクエスモデル */
    public RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     */
    public Sch093Biz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Sch093ParamModel
     * @param umodel ユーザ基本情報モデル
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setInitData(Sch093ParamModel paramMdl,
            BaseUserModel umodel, Connection con) throws SQLException {

        //DBより設定情報を取得。なければデフォルト値とする。
        SchCommonBiz biz = new SchCommonBiz(reqMdl__);
        SchPriConfModel pconf = biz.getSchPriConfModel(con, umodel.getUsrsid());
        SchAdmConfModel aconf = biz.getAdmConfModel(con);

        log__.debug("paramMdl = " + paramMdl);

        //デフォルトグループ
        if (pconf.getSccDspMygroup() != 0) {
            paramMdl.setSch093DefGroup(GSConstSchedule.MY_GROUP_STRING
                    + String.valueOf(pconf.getSccDspMygroup()));
        } else {
            paramMdl.setSch093DefGroup(String.valueOf(pconf.getSccDspGroup()));
        }

        //各ユーザが設定したメンバー表示設定を反映
        if (pconf.getSccSortEdit() == GSConstSchedule.MEM_EDIT_EXECUTE) {
            log__.debug("***ユーザが設定したソート順で表示します***");
            //ソート1
            paramMdl.setSch093SortKey1(pconf.getSccSortKey1());
            paramMdl.setSch093SortOrder1(pconf.getSccSortOrder1());
            //ソート2
            paramMdl.setSch093SortKey2(pconf.getSccSortKey2());
            paramMdl.setSch093SortOrder2(pconf.getSccSortOrder2());

        //管理者が設定したデフォルトメンバー表示設定を反映
        } else {
            log__.debug("***管理者が設定したデフォルトのソート順で表示します***");
            pconf = new SchPriConfModel();
            pconf = biz.getSchPriConfModel(con, 0);

            //ソート1
            paramMdl.setSch093SortKey1(pconf.getSccSortKey1());
            paramMdl.setSch093SortOrder1(pconf.getSccSortOrder1());
            //ソート2
            paramMdl.setSch093SortKey2(pconf.getSccSortKey2());
            paramMdl.setSch093SortOrder2(pconf.getSccSortOrder2());
        }

        //ユーザごとにメンバ表示順変更可能か
        paramMdl.setSch093MemDspConfFlg(aconf.getSadSortKbn());

        //表示情報をFormにセットする
        setDspData(paramMdl, con, umodel.getUsrsid());

        GsMessage gsMsg = new GsMessage(reqMdl__);
        /** 氏名 **/
        String textName = gsMsg.getMessage("cmn.name");
        /** 社員/職員番号 **/
        String textStaffNumber = gsMsg.getMessage("cmn.employee.staff.number");
        /** 役職 **/
        String textPost = gsMsg.getMessage("cmn.post");
        /** 生年月日 **/
        String textBirthDay = gsMsg.getMessage("cmn.birthday");
        /**ソートキー1 **/
        String textSortkey1 = gsMsg.getMessage("cmn.sortkey") + 1;
        /**ソートキー2 **/
        String textSortkey2 = gsMsg.getMessage("cmn.sortkey") + 2;

        String[] arrayLabel = {textName, textStaffNumber, textPost,
                                     textBirthDay, textSortkey1, textSortkey2};
        //ソートキーラベル
        ArrayList<LabelValueBean> sortLabel = new ArrayList<LabelValueBean>();
        for (int i = 0; i < GSConstSchedule.SORT_KEY_ALL.length; i++) {
            String label = arrayLabel[i];
            String value = Integer.toString(GSConstSchedule.SORT_KEY_ALL[i]);
            sortLabel.add(new LabelValueBean(label, value));
        }
        paramMdl.setSch093SortKeyLabel(sortLabel);
    }

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Sch093ParamModel
     * @param umodel ユーザ基本情報モデル
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setReloadData(Sch093ParamModel paramMdl,
            BaseUserModel umodel, Connection con) throws SQLException {
        //表示情報をFormにセットする
        setDspData(paramMdl, con, umodel.getUsrsid());
    }

    /**
     * <br>[機  能] 表示情報をFormにセットする
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Sch093ParamModel
     * @param con コネクション
     * @param userSid ユーザSID
     * @throws SQLException SQL実行エラー
     */
    public void setDspData(Sch093ParamModel paramMdl, Connection con, int userSid)
    throws SQLException {

        GsMessage gsMsg = new GsMessage(reqMdl__);

        //グループラベルの作成
        SchCommonBiz cBiz = new SchCommonBiz(reqMdl__);
        List<SchLabelValueModel> glabel
                            = cBiz.getGroupLabelForScheduleMygrpExist(userSid, con, false);

        //グループラベルに"デフォルトグループ" を追加
        SchLabelValueModel defGroupLabel
            = new SchLabelValueModel(gsMsg.getMessage("user.35"),
                                                Integer.toString(GSConstSchedule.SCC_DSP_GROUP_DEF),
                                                "0");
        glabel.add(0, defGroupLabel);
        paramMdl.setSch093GroupLabel(glabel);

        //デフォルト表示グループフラグ
        SchAdmConfModel aconf = cBiz.getAdmConfModel(con);
        paramMdl.setSch093DefGroupFlg(aconf.getSadDspGroup());

        SchPriConfDao dao = new SchPriConfDao(con);
        SchPriConfModel conf = dao.select(userSid);
        //昇順
        String textOrderAsc = gsMsg.getMessage("cmn.order.asc");
        String textOrderDesc = gsMsg.getMessage("cmn.order.desc");

        /** メッセージ ソートキーALLテキスト **/
        String[] sortText = {gsMsg.getMessage("cmn.name4"),
                             gsMsg.getMessage("schedule.sch100.11"),
                             gsMsg.getMessage("schedule.sch100.16"),
                             gsMsg.getMessage("schedule.sch100.7"),
                             gsMsg.getMessage("cmn.sortkey") + 1,
                             gsMsg.getMessage("cmn.sortkey") + 2};

        //メンバー表示順を設定(各ユーザが設定したデータ)
        if (conf.getSccSortEdit() == GSConstSchedule.MEM_EDIT_EXECUTE) {

            //第一キー
            //氏名
            if (conf.getSccSortKey1() == GSConstSchedule.SORT_KEY_NAME) {
                paramMdl.setSch093DspSortKey1(sortText[0]);
            //社員
            } else if (conf.getSccSortKey1() == GSConstSchedule.SORT_KEY_SNO) {
                paramMdl.setSch093DspSortKey1(sortText[1]);
            //役職
            } else if (conf.getSccSortKey1() == GSConstSchedule.SORT_KEY_YKSK) {
                paramMdl.setSch093DspSortKey1(sortText[2]);
            //生年月日
            } else if (conf.getSccSortKey1() == GSConstSchedule.SORT_KEY_BDATE) {
                paramMdl.setSch093DspSortKey1(sortText[3]);
            //ソートキー1
            } else if (conf.getSccSortKey1() == GSConstSchedule.SORT_KEY_SORTKEY1) {
                paramMdl.setSch093DspSortKey1(sortText[4]);
            //ソートキー2
            } else if (conf.getSccSortKey1() == GSConstSchedule.SORT_KEY_SORTKEY2) {
                paramMdl.setSch093DspSortKey1(sortText[5]);
            }

            if (conf.getSccSortOrder1() == GSConstSchedule.ORDER_KEY_ASC) {
                paramMdl.setSch093DspSortOrder1(textOrderAsc);
            } else if (conf.getSccSortOrder1() == GSConstSchedule.ORDER_KEY_DESC) {
                paramMdl.setSch093DspSortOrder1(textOrderDesc);
            }

            //第二キー
            //氏名
            if (conf.getSccSortKey2() == GSConstSchedule.SORT_KEY_NAME) {
                paramMdl.setSch093DspSortKey2(sortText[0]);
            //社員
            } else if (conf.getSccSortKey2() == GSConstSchedule.SORT_KEY_SNO) {
                paramMdl.setSch093DspSortKey2(sortText[1]);
            //役職
            } else if (conf.getSccSortKey2() == GSConstSchedule.SORT_KEY_YKSK) {
                paramMdl.setSch093DspSortKey2(sortText[2]);
            //生年月日
            } else if (conf.getSccSortKey2() == GSConstSchedule.SORT_KEY_BDATE) {
                paramMdl.setSch093DspSortKey2(sortText[3]);
            //ソートキー1
            } else if (conf.getSccSortKey2() == GSConstSchedule.SORT_KEY_SORTKEY1) {
                paramMdl.setSch093DspSortKey2(sortText[4]);
            //ソートキー2
            } else if (conf.getSccSortKey2() == GSConstSchedule.SORT_KEY_SORTKEY2) {
                paramMdl.setSch093DspSortKey2(sortText[5]);
            }

            if (conf.getSccSortOrder2() == GSConstSchedule.ORDER_KEY_ASC) {
                paramMdl.setSch093DspSortOrder2(textOrderAsc);
            } else if (conf.getSccSortOrder2() == GSConstSchedule.ORDER_KEY_DESC) {
                paramMdl.setSch093DspSortOrder2(textOrderDesc);
            }

        //管理者設定で設定したデフォルト値
        } else {

            //第一キー
            //氏名
            if (aconf.getSadSortKey1() == GSConstSchedule.SORT_KEY_NAME) {
                paramMdl.setSch093DspSortKey1(sortText[0]);
            //社員
            } else if (aconf.getSadSortKey1() == GSConstSchedule.SORT_KEY_SNO) {
                paramMdl.setSch093DspSortKey1(sortText[1]);
            //役職
            } else if (aconf.getSadSortKey1() == GSConstSchedule.SORT_KEY_YKSK) {
                paramMdl.setSch093DspSortKey1(sortText[2]);
            //生年月日
            } else if (aconf.getSadSortKey1() == GSConstSchedule.SORT_KEY_BDATE) {
                paramMdl.setSch093DspSortKey1(sortText[3]);
            //ソートキー1
            } else if (aconf.getSadSortKey1() == GSConstSchedule.SORT_KEY_SORTKEY1) {
                paramMdl.setSch093DspSortKey1(sortText[4]);
            //ソートキー2
            } else if (aconf.getSadSortKey1() == GSConstSchedule.SORT_KEY_SORTKEY2) {
                paramMdl.setSch093DspSortKey1(sortText[5]);
            }

            if (aconf.getSadSortOrder1() == GSConstSchedule.ORDER_KEY_ASC) {
                paramMdl.setSch093DspSortOrder1(textOrderAsc);
            } else if (aconf.getSadSortOrder1() == GSConstSchedule.ORDER_KEY_DESC) {
                paramMdl.setSch093DspSortOrder1(textOrderDesc);
            }

            //第二キー
            //氏名
            if (aconf.getSadSortKey2() == GSConstSchedule.SORT_KEY_NAME) {
                paramMdl.setSch093DspSortKey2(sortText[0]);
            //社員
            } else if (aconf.getSadSortKey2() == GSConstSchedule.SORT_KEY_SNO) {
                paramMdl.setSch093DspSortKey2(sortText[1]);
            //役職
            } else if (aconf.getSadSortKey2() == GSConstSchedule.SORT_KEY_YKSK) {
                paramMdl.setSch093DspSortKey2(sortText[2]);
            //生年月日
            } else if (aconf.getSadSortKey2() == GSConstSchedule.SORT_KEY_BDATE) {
                paramMdl.setSch093DspSortKey2(sortText[3]);
            //ソートキー1
            } else if (aconf.getSadSortKey2() == GSConstSchedule.SORT_KEY_SORTKEY1) {
                paramMdl.setSch093DspSortKey2(sortText[4]);
            //ソートキー2
            } else if (aconf.getSadSortKey2() == GSConstSchedule.SORT_KEY_SORTKEY2) {
                paramMdl.setSch093DspSortKey2(sortText[5]);
            }

            if (aconf.getSadSortOrder2() == GSConstSchedule.ORDER_KEY_ASC) {
                paramMdl.setSch093DspSortOrder2(textOrderAsc);
            } else if (aconf.getSadSortOrder2() == GSConstSchedule.ORDER_KEY_DESC) {
                paramMdl.setSch093DspSortOrder2(textOrderDesc);
            }
        }
    }

    /**
     * <br>[機  能] 設定された個人設定情報をDBに保存する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Sch093ParamModel
     * @param umodel ユーザ基本情報モデル
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setPconfSetting(Sch093ParamModel paramMdl,
            BaseUserModel umodel, Connection con) throws SQLException {

        //DBより設定情報を取得。なければデフォルト値とする。
        SchCommonBiz biz = new SchCommonBiz(reqMdl__);
        SchPriConfModel pconf = biz.getSchPriConfModel(con, umodel.getUsrsid());

        boolean memberDspFlg = isMemberDspConf(con);

        //各ユーザ設定可
        if (memberDspFlg) {
            //ソート1
            pconf.setSccSortKey1(paramMdl.getSch093SortKey1());
            pconf.setSccSortOrder1(paramMdl.getSch093SortOrder1());
            //ソート2
            pconf.setSccSortKey2(paramMdl.getSch093SortKey2());
            pconf.setSccSortOrder2(paramMdl.getSch093SortOrder2());

            //ユーザ毎にメンバー表示順の変更を行った
            pconf.setSccSortEdit(GSConstSchedule.MEM_EDIT_EXECUTE);
        }

        //デフォルトグループ
        if (SchCommonBiz.isMyGroupSid(paramMdl.getSch093DefGroup())) {
            //マイグループをデフォルト表示とした場合
            GroupBiz gpBiz = new GroupBiz();
            int gsid = gpBiz.getDefaultGroupSid(umodel.getUsrsid(), con);
            pconf.setSccDspGroup(gsid);
            pconf.setSccDspMygroup(SchCommonBiz.getDspGroupSid(paramMdl.getSch093DefGroup()));
        } else {
            pconf.setSccDspGroup(Integer.parseInt(paramMdl.getSch093DefGroup()));
            pconf.setSccDspMygroup(0);
        }

        pconf.setSccEuid(umodel.getUsrsid());
        pconf.setSccEdate(new UDate());

        boolean commitFlg = false;
        try {
            SchPriConfDao dao = new SchPriConfDao(con);
            int count = dao.updateGroupDisp(pconf);
            if (count <= 0) {
                //レコードがない場合は作成
                dao.insert(pconf);
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
    }

    /**
     * <br>[機  能] 個人設定でメンバー表示設定が可能か
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     * @return true:メンバー表示設定可能,false:メンバー表示設定不可
     */
    public boolean isMemberDspConf(Connection con)
    throws SQLException {
        SchAdmConfDao sacDao = new SchAdmConfDao(con);
        boolean memberDspFlg = true;

        if (sacDao.select() != null) {
            memberDspFlg = (sacDao.select().getSadSortKbn() == GSConstSchedule.MEM_DSP_USR);
        }
        return memberDspFlg;
    }
}
