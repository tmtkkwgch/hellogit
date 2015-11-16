package jp.groupsession.v2.zsk.zsk100kn;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.base.CmnGroupmDao;
import jp.groupsession.v2.cmn.dao.base.CmnMyGroupDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnGroupmModel;
import jp.groupsession.v2.cmn.model.base.CmnMyGroupModel;
import jp.groupsession.v2.sch.biz.SchCommonBiz;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.zsk.GSConstZaiseki;
import jp.groupsession.v2.zsk.dao.ZaiGpriConfDao;
import jp.groupsession.v2.zsk.model.ZaiGpriConfModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 在席管理 個人設定 メイン画面メンバー表示設定確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Zsk100knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Zsk100knBiz.class);
    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param reqMdl RequestModel
     */
    public Zsk100knBiz(RequestModel reqMdl) {
        super();
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Zsk100knParamModel
     * @param umodel ユーザ基本情報モデル
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setInitData(Zsk100knParamModel paramMdl,
            BaseUserModel umodel, Connection con) throws SQLException {

        if (paramMdl.getZsk100maingrpDspFlg() == GSConstZaiseki.MAINGRP_NOT_DSP) {
            return;
        }

        if (SchCommonBiz.isMyGroupSid(paramMdl.getZsk100DspGpSid())) {
            //マイグループ名の取得
            CmnMyGroupDao mygroupDao = new CmnMyGroupDao(con);
            CmnMyGroupModel mygroupName = mygroupDao.getMyGroupInfo(
                    SchCommonBiz.getDspGroupSid(paramMdl.getZsk100DspGpSid()));
            paramMdl.setZsk100GrpName(mygroupName.getMgpName());
        } else {
            //通常グループ名の取得
            CmnGroupmDao groupDao = new CmnGroupmDao(con);
            CmnGroupmModel groupName
                        = groupDao.select(Integer.parseInt(paramMdl.getZsk100DspGpSid()));
            paramMdl.setZsk100GrpName(groupName.getGrpName());
        }

        //ソート順を設定する。
        paramMdl.setZsk100SortKey1Name(__getSortName(paramMdl.getZsk100SortKey1()));
        paramMdl.setZsk100SortKey2Name(__getSortName(paramMdl.getZsk100SortKey2()));
    }

    /**
     * <br>[機  能] ソートキー名の取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param sortKey int  ソートキー
     * @return sortName ソート名
     * @throws SQLException SQL実行エラー
     */
    public String __getSortName(int sortKey) throws SQLException {

        GsMessage gsMsg = new GsMessage(reqMdl__);
        String msg = "";

        String sortName = "";
        if (sortKey == GSConstZaiseki.SORT_KEY_NAME) {
            msg = gsMsg.getMessage("cmn.name");
            sortName = msg;
        } else if (sortKey == GSConstZaiseki.SORT_KEY_SNO) {
            msg = gsMsg.getMessage("cmn.employee.staff.number");
            sortName = msg;
        } else if (sortKey == GSConstZaiseki.SORT_KEY_YKSK) {
            msg = gsMsg.getMessage("cmn.post");
            sortName = msg;
        } else if (sortKey == GSConstZaiseki.SORT_KEY_BDATE) {
            msg = gsMsg.getMessage("cmn.birthday");
            sortName = msg;
        }
        return sortName;
    }

    /**
     * <br>[機  能] 在席個人設定をDBに保存する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Zsk100knParamModel
     * @param umodel ユーザ基本情報モデル
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setPconfSetting(Zsk100knParamModel paramMdl,
            BaseUserModel umodel, Connection con) throws SQLException {

      boolean commitFlg = false;

      try {
          //DBの存在確認
          ZaiGpriConfDao dao = new ZaiGpriConfDao(con);
          ZaiGpriConfModel model = dao.select(umodel.getUsrsid());
          //画面値セット
          ZaiGpriConfModel iocMdl = createZaiGpriConfData(paramMdl, umodel);

          if (model == null) {
              dao.insert(iocMdl);
          } else {
              dao.update(iocMdl);
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
     * <br>[機  能] 在席個人設定情報を作成
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Zsk100knParamModel
     * @param umodel ユーザ基本情報モデル
     * @return KhmSyaikbnModel 社員区分情報
     */
    public ZaiGpriConfModel createZaiGpriConfData(
            Zsk100knParamModel paramMdl, BaseUserModel umodel) {

        UDate nowDate = new UDate();
        ZaiGpriConfModel mdl = new ZaiGpriConfModel();

        if (paramMdl.getZsk100maingrpDspFlg() == GSConstZaiseki.MAINGRP_DSP) {
            mdl.setZgcGrp(SchCommonBiz.getDspGroupSid(paramMdl.getZsk100DspGpSid()));
        } else {
            mdl.setZgcGrp(-1);
        }
        mdl.setUsrSid(umodel.getUsrsid());
        mdl.setZgcSortKey1(paramMdl.getZsk100SortKey1());
        mdl.setZgcSortKey2(paramMdl.getZsk100SortKey2());
        mdl.setZgcSortOrder1(paramMdl.getZsk100SortOrder1());
        mdl.setZgcSortOrder2(paramMdl.getZsk100SortOrder2());
        mdl.setZgcSchViewDf(paramMdl.getZsk100SchViewDf());
        mdl.setZgcViewKbn(paramMdl.getZsk100maingrpDspFlg());

        if (SchCommonBiz.isMyGroupSid(paramMdl.getZsk100DspGpSid())) {
            mdl.setZgcGkbn(GSConstZaiseki.DSP_MYGROUP);
        } else {
            mdl.setZgcGkbn(GSConstZaiseki.DSP_GROUP);
        }

        mdl.setZgcAuid(umodel.getUsrsid());
        mdl.setZgcAdate(nowDate);
        mdl.setZgcEuid(umodel.getUsrsid());
        mdl.setZgcEdate(nowDate);

        return mdl;
    }

}
