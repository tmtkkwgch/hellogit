package jp.groupsession.v2.enq.enq210;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.io.ObjectFile;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.cmn110.Cmn110Biz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.UsidSelectGrpNameDao;
import jp.groupsession.v2.cmn.dao.base.CmnBelongmDao;
import jp.groupsession.v2.cmn.dao.base.CmnBinfDao;
import jp.groupsession.v2.cmn.dao.base.CmnCmbsortConfDao;
import jp.groupsession.v2.cmn.dao.base.CmnGroupmDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmInfDao;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.cmn.model.base.CmnCmbsortConfModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.enq.GSConstEnquete;
import jp.groupsession.v2.enq.biz.EnqCommonBiz;
import jp.groupsession.v2.enq.dao.EnqAnsMainDao;
import jp.groupsession.v2.enq.dao.EnqAnsSubDao;
import jp.groupsession.v2.enq.dao.EnqMainDao;
import jp.groupsession.v2.enq.dao.EnqQueMainDao;
import jp.groupsession.v2.enq.dao.EnqQueSubDao;
import jp.groupsession.v2.enq.dao.EnqSubjectDao;
import jp.groupsession.v2.enq.dao.EnqTypeDao;
import jp.groupsession.v2.enq.enq210kn.Enq210knEnqDataModel;
import jp.groupsession.v2.enq.enq210kn.Enq210knForm;
import jp.groupsession.v2.enq.enq220.Enq220Biz;
import jp.groupsession.v2.enq.model.EnqAnsMainModel;
import jp.groupsession.v2.enq.model.EnqMainModel;
import jp.groupsession.v2.enq.model.EnqQueMainModel;
import jp.groupsession.v2.enq.model.EnqQueSubModel;
import jp.groupsession.v2.enq.model.EnqSubjectModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] アンケート 設問登録画面のビジネスロジック
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Enq210Biz {

    /** 並び替え 上へ */
    protected static final int SORTTYPE_UP_ = 0;
    /** 並び替え 下へ */
    protected static final int SORTTYPE_DOWN_ = 1;

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Enq210Biz.class);

    /**
     * <br>[機  能] 初期表示情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータモデル
     * @param reqMdl リクエストモデル
     * @param con コネクション
     * @param appRoot アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリパス
     * @throws SQLException 実行例外
     * @throws IOException 添付ファイルの操作に失敗
     * @throws IOToolsException 添付ファイルの操作に失敗
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public void setInitData(Enq210ParamModel paramMdl,
                            RequestModel reqMdl,
                            Connection con, String appRoot, String tempDir)
    throws SQLException, IOException, IOToolsException, TempFileException {

        long emnSid = paramMdl.getEditEnqSid();

        //回答件数が1件以上ある時は警告フラグに値をセットする
        Enq210Dao dao210 = new Enq210Dao(con);
        if (dao210.getAnsCount(emnSid) > 0) {
            paramMdl.setEnq210Alert(1);
        }

        EnqCommonBiz enqBiz = new EnqCommonBiz();
        UDate now = new UDate();
        int registUser = reqMdl.getSmodel().getUsrsid();

        if (paramMdl.getEnq210initFlg() != 1) {
            IOTools.deleteDir(tempDir);
            paramMdl.setEnq210queType(GSConstEnquete.SYURUI_SINGLE);
            paramMdl.setEnq210smailInfo(Enq210Form.SML_INFO_SEND);

            if (paramMdl.getEnqEditMode() == GSConstEnquete.EDITMODE_EDIT) {
                setEnqueteData(emnSid, paramMdl, reqMdl, con, appRoot, tempDir);

            } else {
                paramMdl.setEnq210Juuyou(GSConstEnquete.JUUYOU_1);
                paramMdl.setEnq210queSeqType(Enq210Form.QUE_SEQTYPE_AUTO);
                paramMdl.setEnq210FrYear(now.getYear());
                paramMdl.setEnq210FrMonth(now.getMonth());
                paramMdl.setEnq210FrDay(now.getIntDay());
                UDate toDate = now.cloneUDate();
                toDate.addDay(7);
                paramMdl.setEnq210ToYear(toDate.getYear());
                paramMdl.setEnq210ToMonth(toDate.getMonth());
                paramMdl.setEnq210ToDay(toDate.getIntDay());
                paramMdl.setEnq210AnsYear(toDate.getYear());
                paramMdl.setEnq210AnsMonth(toDate.getMonth());
                paramMdl.setEnq210AnsDay(toDate.getIntDay());
                paramMdl.setEnq210AnsPubFrYear(now.getYear());
                paramMdl.setEnq210AnsPubFrMonth(now.getMonth());
                paramMdl.setEnq210AnsPubFrDay(now.getIntDay());
                paramMdl.setEnq210AnsOpen(GSConstEnquete.EMN_ANS_OPEN_PUBLIC);
            }

            paramMdl.setEnq210initFlg(1);
        }
        //日付を設定
        paramMdl.setEnq210YearLabel(enqBiz.getYearLabels(reqMdl));
        paramMdl.setEnq210MonthLabel(enqBiz.getMonthLabels(reqMdl));
        paramMdl.setEnq210DayLabel(enqBiz.getDayLabels(reqMdl));

        if (paramMdl.getEnq210ToYear() == 0
                && paramMdl.getEnq210ToMonth() == 0
                && paramMdl.getEnq210ToDay() == 0) {


            paramMdl.setEnq210ToYear(paramMdl.getEnq210AnsYear());
            paramMdl.setEnq210ToMonth(paramMdl.getEnq210AnsMonth());
            paramMdl.setEnq210ToDay(paramMdl.getEnq210AnsDay());

        }
        if (paramMdl.getEnq210AnsPubFrYear() == 0
                && paramMdl.getEnq210AnsPubFrMonth() == 0
                && paramMdl.getEnq210AnsPubFrDay() == 0) {
            paramMdl.setEnq210AnsPubFrYear(paramMdl.getEnq210FrYear());
            paramMdl.setEnq210AnsPubFrMonth(paramMdl.getEnq210FrMonth());
            paramMdl.setEnq210AnsPubFrDay(paramMdl.getEnq210FrDay());
        }
        //アンケート種類を設定
        GsMessage gsMsg = new GsMessage(reqMdl);
        EnqTypeDao enqTypeDao = new EnqTypeDao(con);
        List<LabelValueBean> enqTypeList = new ArrayList<LabelValueBean>();
        enqTypeList.add(
                new LabelValueBean(gsMsg.getMessage("cmn.notset"),
                                            String.valueOf(GSConstEnquete.ETP_SID_NOTSET)));
        enqTypeList.addAll(enqTypeDao.getEnqTypeList());
        paramMdl.setEnqTypeList(enqTypeList);

        //発信者リストを設定
        GroupBiz grpBiz = new GroupBiz();
        List<LabelValueBean> senderList =
                grpBiz.getBelongGroupLabelList(registUser, con, false, gsMsg);
        for (int idx = 0; idx < senderList.size(); idx++) {
            senderList.get(idx).setValue(
                    "G" + senderList.get(idx).getValue());
        }
        senderList.add(0,
                            new LabelValueBean(reqMdl.getSmodel().getUsiseimei(),
                                                String.valueOf(reqMdl.getSmodel().getUsrsid())));
        paramMdl.setEnqSenderList(senderList);

        //添付ファイル保存先ディレクトリ
        //$GSTEMPDIR/enquete/セッションID/queData/file/セッションID/
        paramMdl.setEnq210fileDir(
                GSConstEnquete.PLUGIN_ID_ENQUETE
                + "/" + reqMdl.getSession().getId()
                + "/queData/file");

        //添付ファイル名の設定
        setTempFileName(paramMdl, reqMdl, tempDir);

        //対象者を設定
        ArrayList<LabelValueBean> answerGroupList = new ArrayList<LabelValueBean>();
        answerGroupList.add(
                new LabelValueBean(gsMsg.getMessage("cmn.grouplist"),
                                            String.valueOf(Enq210Form.ANSER_GROUP_GRPLIST)));
        GroupBiz groupBiz = new GroupBiz();
        ArrayList<GroupModel> grpList = groupBiz.getGroupCombList(con);
        for (GroupModel grpMdl : grpList) {
            answerGroupList.add(
                    new LabelValueBean(grpMdl.getGroupName(),
                                                String.valueOf(grpMdl.getGroupSid())));
        }
        paramMdl.setSelectAnswerGroup(answerGroupList);

        paramMdl.setSelectAnswerCombo(_createAnswerCombo(paramMdl, con));
        paramMdl.setNoSelectAnswerCombo(__createAnswerRightCombo(paramMdl, con));

        //設問一覧を設定
        paramMdl.setEbaList(readQueList(tempDir));

        //テンプレート一覧を設定
        paramMdl.setEnq210TemplatelList(dao210.getTemplateList());
    }

    /**
     * <br>[機  能] 添付ファイル名を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param tempDir テンポラリディレクトリパス
     * @throws IOToolsException 添付ファイル情報の取得に失敗
     */
    public void setTempFileName(Enq210ParamModel paramMdl, RequestModel reqMdl, String tempDir)
    throws IOToolsException {
        paramMdl.setEnq210fileName(getTempFileName(reqMdl, tempDir));
        paramMdl.setEnq210AttachKbn(getEnqAttachKbn(paramMdl.getEnq210fileName()));
    }

    /**
     * <br>[機  能] 添付ファイル名を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param tempDir テンポラリディレクトリパス
     * @return 添付ファイル名
     * @throws IOToolsException 添付ファイル情報の取得に失敗
     */
    public String getTempFileName(RequestModel reqMdl, String tempDir)
    throws IOToolsException {
        String fileName = null;

        String queTempDir = getEnqTempDir(reqMdl, tempDir);
        CommonBiz cmnBiz = new CommonBiz();
        List<LabelValueBean> fileList = cmnBiz.getTempFileLabelList(queTempDir);
        if (fileList != null && !fileList.isEmpty()) {
            fileName = fileList.get(0).getLabel();
        }
        return fileName;
    }

    /** 設問情報一覧の保存先ファイル名 */
    private static final String QUELIST_FILENAME__ = "enqQueDataList";

    /**
     * <br>[機  能] 設問情報一覧の読み込みを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param tempDir テンポラリディレクトリパス
     * @return 設問情報一覧
     * @throws IOToolsException 設問情報一覧の読み込みに失敗
     */
    @SuppressWarnings("all")
    public List<Enq210QueModel> readQueList(String tempDir)
    throws IOToolsException {
        List<Enq210QueModel> queList = new ArrayList<Enq210QueModel>();

        String saveDir = getQueSaveDir(tempDir);
        File objFilePath = new File(saveDir + QUELIST_FILENAME__);
        if (objFilePath.exists()) {
            ObjectFile objFile = new ObjectFile(objFilePath.getParent(),
                                                                objFilePath.getName());
            Object saveObj = objFile.load();
            if (saveObj != null) {
                queList = (List<Enq210QueModel>) saveObj;
            }
        }
        return queList;
    }

    /**
     * <br>[機  能] 設問情報一覧の保存を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param tempDir テンポラリディレクトリパス
     * @param queList 設問情報一覧
     * @throws IOToolsException 設問情報一覧の読み込みに失敗
     */
    public void saveQueList(String tempDir, List<Enq210QueModel> queList)
    throws IOToolsException {
        String saveDir = getQueSaveDir(tempDir);
        ObjectFile objFile = new ObjectFile(saveDir, QUELIST_FILENAME__);
        IOTools.isDirCheck(saveDir, true);

        //保存前に設問番号(自動)を再設定
        if (queList != null && !queList.isEmpty()) {
            int autoQueNo = 1;
            for (int idx = 0; idx < queList.size(); idx++) {
                if (queList.get(idx).getEnq210QueKbn() != GSConstEnquete.SYURUI_COMMENT) {
                    queList.get(idx).setEnq210AutoQueNo(autoQueNo++);
                } else {
                    queList.get(idx).setEnq210AutoQueNo(0);
                }
            }
        }

        objFile.save(queList);
    }

    /**
     * <br>[機  能] 設問情報一覧の保存先ディレクトリパスを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param tempDir テンポラリディレクトリパス
     * @return 設問情報一覧の保存先ディレクトリパス
     */
    public String getQueSaveDir(String tempDir) {
        //$GSTEMPDIR/enquete/セッションID/queData/
        //オブジェクトファイル "enqQueDataList” に設問情報を保存する
        String saveDir = IOTools.replaceSlashFileSep(tempDir);
        if (!saveDir.endsWith("/")) {
            saveDir += "/";
        }
        saveDir += "queData/";

        return saveDir;
    }

    /**
     * <br>[機  能] アンケート情報の添付ファイル保存先ディレクトリパスを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param tempDir テンポラリディレクトリパス
     * @return アンケート情報の添付ファイル保存先ディレクトリパス
     */
    public String getEnqTempDir(RequestModel reqMdl, String tempDir) {
        //$GSTEMPDIR/enquete/セッションID/queData/file/セッションID/
        String saveDir = IOTools.replaceSlashFileSep(tempDir);
        if (!saveDir.endsWith("/")) {
            saveDir += "/";
        }
        saveDir += "queData/file/";
        saveDir += reqMdl.getSession().getId() + "/";

        return saveDir;
    }

    /**
     * <br>[機  能] アンケート設問情報の削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータモデル
     * @param reqMdl リクエストモデル
     * @param con コネクション
     * @return 削除したアンケートの名称
     * @throws SQLException SQL実行例外
     */
    public String deleteEnqQuestion(Enq210ParamModel paramMdl,
                                              RequestModel reqMdl,
                                              Connection con) throws SQLException {

        long emnSid = paramMdl.getEditEnqSid();
        int userSid = reqMdl.getSmodel().getUsrsid();

        EnqCommonBiz enqBiz = new EnqCommonBiz();
        return enqBiz.deleteEnquete(emnSid, userSid, con);
    }

    /**
     * <br>[機  能] 指定したアンケート情報をパラメータへ設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param emnSid アンケートSID
     * @param paramMdl パラメータモデル
     * @param reqMdl リクエストモデル
     * @param con コネクション
     * @param appRoot アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリパス
     * @throws SQLException 実行例外
     * @throws IOException 添付ファイルの操作に失敗
     * @throws IOToolsException 添付ファイルの操作に失敗
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public void setEnqueteData(long emnSid,
                Enq210ParamModel paramMdl,
                RequestModel reqMdl,
                Connection con, String appRoot, String tempDir)
    throws SQLException, IOException, IOToolsException, TempFileException {
        setEnqueteData(emnSid, paramMdl, reqMdl, con, appRoot, tempDir, false);
    }

    /**
     * <br>[機  能] 指定したアンケート情報をパラメータへ設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param emnSid アンケートSID
     * @param paramMdl パラメータモデル
     * @param reqMdl リクエストモデル
     * @param con コネクション
     * @param appRoot アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリパス
     * @param templateFlg テンプレート読み込みか否か
     * @throws SQLException 実行例外
     * @throws IOException 添付ファイルの操作に失敗
     * @throws IOToolsException 添付ファイルの操作に失敗
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public void setEnqueteData(long emnSid,
                Enq210ParamModel paramMdl,
                RequestModel reqMdl,
                Connection con, String appRoot, String tempDir, boolean templateFlg)
    throws SQLException, IOException, IOToolsException, TempFileException {
        IOTools.deleteDir(tempDir);

        // 設問一覧を取得
        EnqMainDao enqMainDao = new EnqMainDao(con);
        EnqMainModel enqMainMdl = enqMainDao.select(emnSid);

        if (enqMainMdl.getEmnDataKbn() == GSConstEnquete.EMN_DATA_KBN_DRAFT) {
            paramMdl.setEnq210editMode(Enq210Form.EDITMODE_DRAFT);
        }

        paramMdl.setEnq210Title(enqMainMdl.getEmnTitle());
        paramMdl.setEnq210Syurui(enqMainMdl.getEtpSid());
        paramMdl.setEnq210Juuyou(enqMainMdl.getEmnPriKbn());
        paramMdl.setEnq210Desc(enqMainMdl.getEmnDesc());

        //添付区分
        paramMdl.setEnq210AttachKbn(enqMainMdl.getEmnAttachKbn());
        //添付ID
        paramMdl.setEnq210Url(enqMainMdl.getEmnAttachId());
        //添付名
        paramMdl.setEnq210TempDspName(enqMainMdl.getEmnAttachName());
        //添付位置
        paramMdl.setEnq210AttachPos(enqMainMdl.getEmnAttachPos());

        if (!templateFlg) {
            //公開期間_開始
            if (enqMainMdl.getEmnOpenStr() != null) {
                paramMdl.setEnq210FrYear(enqMainMdl.getEmnOpenStr().getYear());
                paramMdl.setEnq210FrMonth(enqMainMdl.getEmnOpenStr().getMonth());
                paramMdl.setEnq210FrDay(enqMainMdl.getEmnOpenStr().getIntDay());
            }
            //公開期間_終了
            if (enqMainMdl.getEmnOpenEndKbn() == GSConstEnquete.EMN_OPEN_END_KBN_NON) {
                paramMdl.setEnq210ToKbn(Enq210Form.TO_DATE_NOLIMIT);
            } else {
                if (enqMainMdl.getEmnOpenEnd() != null) {
                    paramMdl.setEnq210ToYear(enqMainMdl.getEmnOpenEnd().getYear());
                    paramMdl.setEnq210ToMonth(enqMainMdl.getEmnOpenEnd().getMonth());
                    paramMdl.setEnq210ToDay(enqMainMdl.getEmnOpenEnd().getIntDay());
                }
                paramMdl.setEnq210ToKbn(0);
            }
            //回答期限
            if (enqMainMdl.getEmnResEnd() != null) {
                paramMdl.setEnq210AnsYear(enqMainMdl.getEmnResEnd().getYear());
                paramMdl.setEnq210AnsMonth(enqMainMdl.getEmnResEnd().getMonth());
                paramMdl.setEnq210AnsDay(enqMainMdl.getEmnResEnd().getIntDay());
            }
            //回答公開期間_開始
            if (enqMainMdl.getEmnAnsPubStr() != null) {
                paramMdl.setEnq210AnsPubFrYear(enqMainMdl.getEmnAnsPubStr().getYear());
                paramMdl.setEnq210AnsPubFrMonth(enqMainMdl.getEmnAnsPubStr().getMonth());
                paramMdl.setEnq210AnsPubFrDay(enqMainMdl.getEmnAnsPubStr().getIntDay());
            }
        } else {
            UDate now = new UDate();
            //公開期間_開始
            paramMdl.setEnq210FrYear(now.getYear());
            paramMdl.setEnq210FrMonth(now.getMonth());
            paramMdl.setEnq210FrDay(now.getIntDay());

            UDate toDate = now.cloneUDate();
            toDate.addDay(7);
            //公開期間_終了
            paramMdl.setEnq210ToYear(toDate.getYear());
            paramMdl.setEnq210ToMonth(toDate.getMonth());
            paramMdl.setEnq210ToDay(toDate.getIntDay());
            //回答期限
            paramMdl.setEnq210AnsYear(toDate.getYear());
            paramMdl.setEnq210AnsMonth(toDate.getMonth());
            paramMdl.setEnq210AnsDay(toDate.getIntDay());
            //回答公開期間_開始
            paramMdl.setEnq210AnsPubFrYear(now.getYear());
            paramMdl.setEnq210AnsPubFrMonth(now.getMonth());
            paramMdl.setEnq210AnsPubFrDay(now.getIntDay());
        }

        //匿名フラグ
        paramMdl.setEnq210Anony(enqMainMdl.getEmnAnony());
        //回答公開フラグ
        paramMdl.setEnq210AnsOpen(enqMainMdl.getEmnAnsOpen());

        //設問番号_種類
        if (enqMainMdl.getEmnQuesecType() == GSConstEnquete.EMN_QUESEC_TYPE_MANUAL) {
            paramMdl.setEnq210queSeqType(Enq210Form.QUE_SEQTYPE_MANUAL);
        } else {
            paramMdl.setEnq210queSeqType(Enq210Form.QUE_SEQTYPE_AUTO);
        }

        //発信者
        if (enqMainMdl.getEmnSendGrp() > 0) {
            paramMdl.setEnq210Send("G" + enqMainMdl.getEmnSendGrp());
        } else {
            paramMdl.setEnq210Send(String.valueOf(enqMainMdl.getEmnSendUsr()));
        }

        //アンケート_基本情報 添付ファイル情報を設定
        if (enqMainMdl.getEmnAttachKbn() == GSConstEnquete.EMN_ATTACH_KBN_IMAGE
        || enqMainMdl.getEmnAttachKbn() == GSConstEnquete.EMN_ATTACH_KBN_FILE) {
            __saveTempFileData(con, reqMdl,
                    appRoot, getEnqTempDir(reqMdl, tempDir),
                    enqMainMdl.getEmnAttachId());
        }

        ArrayList<Enq210QueModel> queList = __getQueList(emnSid, reqMdl, con,
                appRoot, tempDir);

        saveQueList(tempDir, queList);
        paramMdl.setEbaList(queList);

        //対象者を設定
        EnqSubjectDao enqSubjectDao = new EnqSubjectDao(con);
        List<EnqSubjectModel> subjectList = enqSubjectDao.getSubjectList(emnSid);
        List<String> answerList = new ArrayList<String>();
        for (EnqSubjectModel subjectMdl : subjectList) {
            if (subjectMdl.getGrpSid() != GSConstEnquete.ENQ_SUBJECT_NOTSET) {
                answerList.add("G" + subjectMdl.getGrpSid());
            } else {
                answerList.add(String.valueOf(subjectMdl.getUsrSid()));
            }
        }

        paramMdl.setEnq210answerList(
                (String[]) answerList.toArray(new String[answerList.size()]));

    }

    /**
     * <br>[機  能] 設問情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param emnSid アンケートSID
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @param appRoot アプリケーションルートパス
     * @param tempDir テンポラリディレクトリ
     * @return 設問情報
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException 設問情報の読み込みに失敗
     * @throws TempFileException 設問情報の読み込みに失敗
     * @throws IOException 設問情報の読み込みに失敗
     */
    private ArrayList<Enq210QueModel> __getQueList(long emnSid,
            RequestModel reqMdl, Connection con, String appRoot, String tempDir)
            throws SQLException, IOToolsException, TempFileException,
            IOException {
        //設問情報を設定
        EnqQueMainDao queMainDao = new EnqQueMainDao(con);
        ArrayList<EnqQueMainModel> eqmList = queMainDao.select(emnSid);
        ArrayList<Enq210QueModel> queList = __createDspList(reqMdl, eqmList, tempDir, con);

        //設問_サブ情報を設定
        if (queList != null && !queList.isEmpty()) {
            int queKbn = 0;
            for (int queIdx = 0; queIdx < queList.size(); queIdx++) {
                List<Enq210QueSubModel> queSubList = new ArrayList<Enq210QueSubModel>();
                queKbn = queList.get(queIdx).getEnq210QueKbn();
                if (queKbn == GSConstEnquete.SYURUI_SINGLE
                || queKbn == GSConstEnquete.SYURUI_MULTIPLE) {
                    //設問 選択肢を設定
                    int subIndex = 1;
                    EnqQueSubDao queSubDao = new EnqQueSubDao(con);
                    List<EnqQueSubModel> subList
                        = queSubDao.select(emnSid, queList.get(queIdx).getEnq210Seq());
                    for (EnqQueSubModel subMdl : subList) {
                        if (subMdl.getEqsSeq() != GSConstEnquete.EQS_SEQ_OTHER) {
                            Enq210QueSubModel queSubMdl = new Enq210QueSubModel();
                            queSubMdl.setEnqIndex(subIndex++);
                            queSubMdl.setEnqDspName(subMdl.getEqsDspName());
                            queSubMdl.setEnqDefFlg(subMdl.getEqsDefNum());
                            queSubList.add(queSubMdl);
                        }
                    }
                    queList.get(queIdx).setQueSubList(queSubList);

                } else {
                    //初期値、範囲情報を設定
                    Enq210Dao dao210 = new Enq210Dao(con);
                    queList.set(queIdx, dao210.setEnqDefRngValue(queList.get(queIdx)));
                    //初期値、範囲情報(表示用)を設定
                    EnqCommonBiz enqBiz = new EnqCommonBiz();
                    for (int idx = 0; idx < queList.size(); idx++) {
                        //初期値
                        queList.get(idx).setEnq210initDspDate(
                                enqBiz.getStrDate(reqMdl, queList.get(idx).getEnq210initDate()));
                        //範囲From
                        queList.get(idx).setEnq210rangeTxtFrDsp(
                                enqBiz.getStrDate(reqMdl, queList.get(idx).getEnq210rangeDateFr()));
                        //範囲To
                        queList.get(idx).setEnq210rangeTxtToDsp(
                                enqBiz.getStrDate(reqMdl, queList.get(idx).getEnq210rangeDateTo()));
                    }
                }
            }
        }

        //設問_基本情報 添付ファイル情報を設定
        Enq220Biz biz220 = new Enq220Biz();
        for (Enq210QueModel queMainMdl :  queList) {
            if (queMainMdl.getEnq210AttachKbn() == GSConstEnquete.EQM_ATTACH_KBN_IMAGE
            || queMainMdl.getEnq210AttachKbn() == GSConstEnquete.EQM_ATTACH_KBN_FILE) {
                __saveTempFileData(con, reqMdl,
                        appRoot,
                        biz220.getQueTempDir(queMainMdl.getEnq210Id(), reqMdl, tempDir),
                        queMainMdl.getEnq210AttachSid());
            }
        }
        return queList;
    }

    /**
     * <br>[機  能] 設問情報の並び替えを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータモデル
     * @param reqMdl リクエストモデル
     * @param tempDir テンポラリディレクトリパス
     * @param sortType 上へ or 下へ
     * @throws IOToolsException 設問情報の並び替えに失敗
     */
    public void sortQuestion(Enq210ParamModel paramMdl,
                            RequestModel reqMdl, String tempDir,
                            int sortType)
    throws IOToolsException {

        int sortQueIdx = paramMdl.getEnq210queIndex();
        List<Enq210QueModel> queList = readQueList(tempDir);

        if ((sortType == SORTTYPE_UP_ && sortQueIdx <= 0)
        || (sortType == SORTTYPE_DOWN_ && sortQueIdx + 1 >= queList.size())) {
            return;
        }

        List<Enq210QueModel> newQueList = new ArrayList<Enq210QueModel>();
        int oldAutoNo = 0;
        for (int idx = 0; idx < queList.size(); idx++) {
            if (queList.get(idx).getEnq210queIndex() == sortQueIdx) {
                if (sortType == SORTTYPE_UP_) {
                    newQueList.add(idx - 1, queList.get(idx));
                    newQueList.get(idx - 1).setEnq210queIndex(
                            newQueList.get(idx).getEnq210queIndex());
                    newQueList.get(idx).setEnq210queIndex(
                            newQueList.get(idx).getEnq210queIndex() + 1);

                    oldAutoNo = newQueList.get(idx - 1).getEnq210AutoQueNo();
                    newQueList.get(idx - 1).setEnq210AutoQueNo(
                            newQueList.get(idx).getEnq210AutoQueNo());
                    newQueList.get(idx).setEnq210AutoQueNo(oldAutoNo);

                    paramMdl.setEnq210queIndex(sortQueIdx - 1);
                } else if (sortType == SORTTYPE_DOWN_) {
                    newQueList.add(queList.get(idx + 1));
                    newQueList.add(queList.get(idx));
                    newQueList.get(idx).setEnq210queIndex(
                            newQueList.get(idx).getEnq210queIndex() - 1);
                    newQueList.get(idx + 1).setEnq210queIndex(
                            newQueList.get(idx).getEnq210queIndex() + 1);

                    oldAutoNo = newQueList.get(idx + 1).getEnq210AutoQueNo();
                    newQueList.get(idx).setEnq210AutoQueNo(
                            newQueList.get(idx + 1).getEnq210AutoQueNo());
                    newQueList.get(idx + 1).setEnq210AutoQueNo(oldAutoNo);

                    paramMdl.setEnq210queIndex(sortQueIdx + 1);
                    idx++;
                }
            } else {
                newQueList.add(queList.get(idx));
            }
        }

        saveQueList(tempDir, newQueList);
    }

    /**
     * <br>[機  能] 設問情報の削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータモデル
     * @param reqMdl リクエストモデル
     * @param tempDir テンポラリディレクトリパス
     * @throws IOToolsException 設問情報の削除に失敗
     */
    public void deleteQuestion(Enq210ParamModel paramMdl,
                            RequestModel reqMdl, String tempDir)
    throws IOToolsException {

        List<Enq210QueModel> queList = readQueList(tempDir);

        boolean deleteFlg = false;
        int deleteQueIdx = paramMdl.getEnq210editQueIndex();
        for (int idx = 0; idx < queList.size(); idx++) {
            if (queList.get(idx).getEnq210queIndex() == deleteQueIdx) {
                queList.remove(idx);
                deleteFlg = true;
                idx--;
            } else if (deleteFlg) {
                queList.get(idx).setEnq210queIndex(
                        queList.get(idx).getEnq210queIndex() - 1);
            }
        }

        saveQueList(tempDir, queList);
    }

    /**
     * <br>[機  能] 設問情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param resultList DB取得リスト
     * @param tempDir 天ぷら理ディレクトリ
     * @param con コネクション
     * @return 表示用リスト
     * @throws IOToolsException 添付ファイルの操作に失敗
     * @throws TempFileException 添付ファイルUtil内での例外
     *
     */
    private ArrayList<Enq210QueModel> __createDspList(RequestModel reqMdl,
                                                    ArrayList<EnqQueMainModel> resultList,
                                                    String tempDir,
                                                    Connection con)
        throws IOToolsException, TempFileException {

        ArrayList<Enq210QueModel> eqmList = new ArrayList<Enq210QueModel>();
        Enq210QueModel dspBean = null;
        int index = 0;
        String queId = (new UDate()).getTimeStamp();
        int queKbn = 0;
        int autoQueNo = 1;
        for (EnqQueMainModel eMdl : resultList) {
            dspBean = new Enq210QueModel();
            dspBean.setEnq210Sid(eMdl.getEmnSid());
            dspBean.setEnq210Seq(eMdl.getEqmSeq());
            dspBean.setEnq210DspOrder(eMdl.getEqmDspSec());
            dspBean.setEnq210QueNo(eMdl.getEqmQueSec());
            if (eMdl.getEqmQueKbn() != GSConstEnquete.SYURUI_COMMENT) {
                dspBean.setEnq210AutoQueNo(autoQueNo++);
            } else {
                dspBean.setEnq210AutoQueNo(0);
            }
            dspBean.setEnq210Question(eMdl.getEqmQuestion());
            queKbn = eMdl.getEqmQueKbn();
            dspBean.setEnq210QueKbn(queKbn);
            dspBean.setEnq210Require(
                    NullDefault.getString(String.valueOf(eMdl.getEqmRequire()), null));
            dspBean.setEnq210OtherFlg(eMdl.getEqmOther());
            dspBean.setEnq210QueDesc(eMdl.getEqmDesc());
            dspBean.setEnq210QueDescText(eMdl.getEqmDescPlain());
            dspBean.setEnq210AttachKbn(eMdl.getEqmAttachKbn());
            dspBean.setEnq210AttachPos(eMdl.getEqmAttachPos());
            dspBean.setEnq210AttachSid(eMdl.getEqmAttachId());
            dspBean.setEnq210AttachName(__getTempFileName(con, eMdl.getEqmAttachId()));
            dspBean.setEnq210SyuruiName(getDspQueType(reqMdl, eMdl.getEqmQueKbn()));
            dspBean.setEnq210LinePos(eMdl.getEqmLineKbn());

            dspBean.setEnq210Id(queId + index);
            dspBean.setEnq210queIndex(index++);

            eqmList.add(dspBean);
        }
        return eqmList;
    }

    /**
     * <br>[機  能] 設問種類名を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param queKbn 設問種類区分
     * @return 設問種類名
     */
    public static String getDspQueType(RequestModel reqMdl, int queKbn) {

        String ret = "";

        GsMessage gsMsg = new GsMessage(reqMdl);
        switch (queKbn) {

        // 単一選択
        case(GSConstEnquete.SYURUI_SINGLE):
            ret = gsMsg.getMessage("enq.enq210.03");
            break;

        // 複数選択
        case(GSConstEnquete.SYURUI_MULTIPLE):
            ret = gsMsg.getMessage("enq.enq210.04");
            break;

        // テキスト入力
        case(GSConstEnquete.SYURUI_TEXT):
            ret = gsMsg.getMessage("enq.enq210.05");
            break;

        // テキスト入力（複数行）
        case(GSConstEnquete.SYURUI_TEXTAREA):
            ret = gsMsg.getMessage("enq.enq210.06");
            break;

        // 数値入力
        case(GSConstEnquete.SYURUI_INTEGER):
            ret = gsMsg.getMessage("enq.enq210.07");
            break;

        // 日付入力
        case(GSConstEnquete.SYURUI_DAY):
            ret = gsMsg.getMessage("enq.enq210.08");
            break;

        // コメント
        default:
            ret = gsMsg.getMessage("cmn.comment");
        }
        return ret;
    }

    /**
     * <br>[機  能] 登録に使用する側のコンボで選択中のメンバーをメンバーリストから削除する
     *
     * <br>[解  説] 登録に使用する側のコンボ表示に必要なSID(複数)をメンバーリスト(memberSid)で持つ。
     *              画面で削除矢印ボタンをクリックした場合、
     *              登録に使用する側のコンボで選択中の値(deleteSelectSid)をメンバーリストから削除する。
     *
     * <br>[備  考] 登録に使用する側のコンボで値が選択されていない場合はメンバーリストをそのまま返す
     *
     * @param deleteSelectSid 登録に使用する側のコンボで選択中の値
     * @param memberSid メンバーリスト
     * @return 削除済みのメンバーリスト
     */
    public String[] getDeleteMember(String[] deleteSelectSid, String[] memberSid) {

        if (deleteSelectSid == null) {
            return memberSid;
        }
        if (deleteSelectSid.length < 1) {
            return memberSid;
        }

        if (memberSid == null) {
            memberSid = new String[0];
        }

        //削除後に画面にセットするメンバーリストを作成
        ArrayList<String> list = new ArrayList<String>();

        for (int i = 0; i < memberSid.length; i++) {
            boolean setFlg = true;

            for (int j = 0; j < deleteSelectSid.length; j++) {
                if (memberSid[i].equals(deleteSelectSid[j])) {
                    setFlg = false;
                    break;
                }
            }

            if (setFlg) {
                list.add(memberSid[i]);
            }
        }

        log__.debug("削除後メンバーリストサイズ = " + list.size());
        return list.toArray(new String[list.size()]);
    }

    /**
     * <br>[機  能] 追加側のコンボで選択中のメンバーをメンバーリストに追加する
     *
     * <br>[解  説] 画面右側のコンボ表示に必要なSID(複数)をメンバーリスト(memberSid)で持つ。
     *              画面で追加矢印ボタンをクリックした場合、
     *              追加側のコンボで選択中の値(addSelectSid)をメンバーリストに追加する。
     *
     * <br>[備  考] 追加側のコンボで値が選択されていない場合はメンバーリストをそのまま返す
     *
     * @param addSelectSid 追加側のコンボで選択中の値
     * @param memberSid メンバーリスト
     * @return 追加済みのメンバーリスト
     */
    public String[] getAddMember(String[] addSelectSid, String[] memberSid) {

        if (addSelectSid == null) {
            return memberSid;
        }
        if (addSelectSid.length < 1) {
            return memberSid;
        }


        //追加後に画面にセットするメンバーリストを作成
        ArrayList<String> list = new ArrayList<String>();

        if (memberSid != null) {
            for (int j = 0; j < memberSid.length; j++) {
                if (!memberSid[j].equals("-1")) {
                    list.add(memberSid[j]);
                }
            }
        }

        for (int i = 0; i < addSelectSid.length; i++) {
            if (!addSelectSid[i].equals("-1")) {
                list.add(addSelectSid[i]);
            }
        }

        log__.debug("追加後メンバーリストサイズ = " + list.size());
        return list.toArray(new String[list.size()]);
    }

    /**
     * <br>[機  能] バイナリ―情報を指定したディレクトリへ保存する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @param appRootPath アプリケーションルートパス
     * @param saveDir 保存先ディレクトリパス
     * @param binSid バイナリ―SID
     * @throws SQLException SQL実行時例外
     * @throws IOException バイナリ―情報の保存に失敗
     * @throws IOToolsException バイナリ―情報の保存に失敗
     * @throws TempFileException バイナリ―情報の保存に失敗
     */
    private void __saveTempFileData(Connection con, RequestModel reqMdl,
                                                String appRootPath, String saveDir, String binSid)
    throws SQLException, IOException, IOToolsException, TempFileException {
        String[] binSids = {binSid};
        CommonBiz cmnBiz = new CommonBiz();
        List<CmnBinfModel> binList = cmnBiz.getBinInfo(con, binSids, reqMdl.getDomain());
        cmnBiz.saveTempFile((new UDate()).getDateString(), binList.get(0), appRootPath, saveDir, 1);
    }

    /**
     * <br>[機  能] アンケート情報を登録
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータモデル
     * @param reqMdl リクエストモデル
     * @param con コネクション
     * @param mtCon 採番コントローラ
     * @param appRootPath アプリケーションルートパス
     * @param tempDir テンポラリディレクトリパス
     * @return アンケートSID
     * @throws SQLException SQL実行例外
     * @throws IOToolsException 設問情報の取得に失敗
     * @throws TempFileException 添付ファイル情報の登録に失敗
     */
    public long entryEnqueteData(Enq210ParamModel paramMdl,
                                                RequestModel reqMdl,
                                                Connection con,
                                                MlCountMtController mtCon,
                                                String appRootPath,
                                                String tempDir)
    throws SQLException, IOToolsException, TempFileException {

        EnqMainDao enqMainDao = new EnqMainDao(con);
        EnqSubjectDao enqSubjectDao = new EnqSubjectDao(con);
        EnqAnsMainDao ansMainDao = new EnqAnsMainDao(con);
        EnqAnsSubDao ansSubDao = new EnqAnsSubDao(con);
        EnqCommonBiz enqBiz = new EnqCommonBiz();

        //発信者
        int sessionUserSid = reqMdl.getSmodel().getUsrsid();
        UDate now = new UDate();
        long emnSid = 0;

        //アンケート編集時
        boolean notDelAns = false;
        if (paramMdl.getEnqEditMode() == GSConstEnquete.EDITMODE_EDIT) {

            //アンケートSID
            emnSid = paramMdl.getEditEnqSid();

            //編集前のアンケート基本情報に関連するバイナリ―情報を削除する
            enqMainDao.removeBinData(emnSid, sessionUserSid, now);

            //編集前の設問、回答情報を削除する
            boolean delAns = paramMdl.isEnq210DelAnsFlg();
            enqBiz.deleteEnqQuestion(emnSid, sessionUserSid, con, delAns);
            if (!delAns) {
                //回答情報の削除を行わない場合、[対象者]に含まれないユーザの回答情報を削除する
                ansMainDao.deleteNonSubject(emnSid);
                ansSubDao.deleteNonSubject(emnSid);
                notDelAns = true;
            }

            //編集前のアンケート_対象者を削除する
            enqSubjectDao.delete(emnSid);

        } else {
            emnSid = mtCon.getSaibanNumber(GSConstEnquete.SBNSID_ENQUETE,
                    GSConstEnquete.SBNSID_SUB_ENQUETE_ID,
                    sessionUserSid);
        }

        //アンケート基本情報モデルを取得する
        EnqMainModel enqMainMdl = __getEnqMainModel(
                paramMdl, reqMdl, tempDir,
                sessionUserSid, now);

        //アンケートSIDをセットする
        enqMainMdl.setEmnSid(emnSid);

        //添付ファイル情報の登録
        if (enqMainMdl.getEmnAttachKbn() == GSConstEnquete.EMN_ATTACH_KBN_IMAGE
        || enqMainMdl.getEmnAttachKbn() == GSConstEnquete.EMN_ATTACH_KBN_FILE) {
            String enqTempDir = getEnqTempDir(reqMdl, tempDir);
            CommonBiz cmnBiz = new CommonBiz();
            List<String> binSid = cmnBiz.insertBinInfo(con, enqTempDir, appRootPath,
                                                mtCon, sessionUserSid, now);
            enqMainMdl.setEmnAttachId(binSid.get(0));
        }

        //設問情報の登録
        paramMdl.setEbaList(readQueList(tempDir));
        __entryQuestionData(paramMdl, reqMdl, con, emnSid, mtCon, appRootPath, tempDir, now);

        //回答情報の登録
        int emnTarget = 0;
        String[] answerList = paramMdl.getEnq210answerList();
        if (answerList != null && answerList.length > 0) {

            CmnBelongmDao belongDao = new CmnBelongmDao(con);
            List<String> subjectUserList = new ArrayList<String>();

            CmnGroupmDao grpDao = new CmnGroupmDao(con);
            CmnUsrmDao usrDao = new CmnUsrmDao(con);

            ArrayList<EnqSubjectModel> subjectList = new ArrayList<EnqSubjectModel>();
            //アンケート_対象者の登録
            for (String answer : answerList) {
                EnqSubjectModel subjectMdl = new EnqSubjectModel();
                subjectMdl.setEmnSid(emnSid);
                if (answer.startsWith("G")) {
                    int ansGrpSid = Integer.parseInt(answer.substring(1));
                    //削除グループは除外する
                    if (grpDao.isDeleteGroup(ansGrpSid)) {
                        continue;
                    }
                    subjectMdl.setGrpSid(ansGrpSid);
                    subjectMdl.setUsrSid(GSConstEnquete.ENQ_SUBJECT_NOTSET);

                    List<Integer> belongUserList = belongDao.selectBelongUserSid(ansGrpSid);
                    if (belongUserList != null && !belongUserList.isEmpty()) {
                        for (Integer belongUser : belongUserList) {
                            if (canAnsUser(String.valueOf(belongUser))) {
                                if (!subjectUserList.contains(String.valueOf(belongUser))) {
                                    subjectUserList.add(String.valueOf(belongUser));
                                }
                            }
                        }
                    }
                } else {
                    //システムユーザ、または削除ユーザは除外する
                    if (!canAnsUser(answer) || usrDao.isDeleteUserHnt(Integer.parseInt(answer))) {
                        continue;
                    }
                    subjectMdl.setGrpSid(GSConstEnquete.ENQ_SUBJECT_NOTSET);
                    subjectMdl.setUsrSid(Integer.parseInt(answer));
                    if (!subjectUserList.contains(answer)) {
                        subjectUserList.add(answer);
                    }
                }
                subjectList.add(subjectMdl);
            }

            enqSubjectDao.insert(subjectList);

            emnTarget = subjectUserList.size();
            //回答済みユーザを回答情報登録対象から除外する
            if (notDelAns) {
                List<String> ansUserList = ansMainDao.getAnswerUserList(emnSid);
                if (ansUserList != null && !ansUserList.isEmpty()) {
                    subjectUserList.removeAll(ansUserList);
                }
            }

            //回答_基本情報の登録（リセットの場合は実行しない）
            EnqAnsMainModel ansMainMdl = new EnqAnsMainModel();
            if (paramMdl.getEnq210editMode() != Enq210Form.EDITMODE_DRAFT
                    && paramMdl.getEnq210editMode() != Enq210Form.EDITMODE_TEMPLATE) {
                ansMainMdl.setEmnSid(emnSid);
                ansMainMdl.setEamStsKbn(GSConstEnquete.EAM_STS_KBN_NO);
                ansMainMdl.setEamAuid(sessionUserSid);
                ansMainMdl.setEamAdate(now);
                ansMainMdl.setEamEuid(sessionUserSid);
                ansMainMdl.setEamEdate(now);

                ansMainDao.insert(subjectUserList, ansMainMdl);
            }
        }

        //アンケート基本情報の登録
        enqMainMdl.setEmnTarget(emnTarget);
        if (paramMdl.getEnqEditMode() == GSConstEnquete.EDITMODE_EDIT) {
            enqMainDao.update(enqMainMdl);
        } else {
            enqMainMdl.setEmnAuid(sessionUserSid);
            enqMainMdl.setEmnAdate(now);
            enqMainDao.insert(enqMainMdl);
        }

        return emnSid;
    }

    /**
     * <br>[機  能] アンケート基本情報モデルを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータモデル
     * @param reqMdl リクエストモデル
     * @param tempDir テンポラリディレクトリパス
     * @param sessionUserSid セッションユーザSID
     * @param now 登録、更新日時
     * @return アンケートSID
     * @throws IOToolsException 設問情報の取得に失敗
     */
    private EnqMainModel __getEnqMainModel(Enq210ParamModel paramMdl,
            RequestModel reqMdl, String tempDir, int sessionUserSid, UDate now)
            throws IOToolsException {
        //アンケート基本情報の登録
        EnqMainModel enqMainMdl = new EnqMainModel();
        if (paramMdl.getEnq210editMode() == Enq210Form.EDITMODE_DRAFT) {
            enqMainMdl.setEmnDataKbn(GSConstEnquete.EMN_DATA_KBN_DRAFT);
        } else if (paramMdl.getEnq210editMode() == Enq210Form.EDITMODE_TEMPLATE) {
            enqMainMdl.setEmnDataKbn(GSConstEnquete.EMN_DATA_KBN_TEMPLATE);
        } else {
            enqMainMdl.setEmnDataKbn(GSConstEnquete.EMN_DATA_KBN_SEND);
        }

        enqMainMdl.setEtpSid(paramMdl.getEnq210Syurui());
        enqMainMdl.setEmnTitle(paramMdl.getEnq210Title());
        enqMainMdl.setEmnPriKbn(paramMdl.getEnq210Juuyou());
        enqMainMdl.setEmnDesc(paramMdl.getEnq210Desc());
        enqMainMdl.setEmnDescPlain(paramMdl.getEnq210DescText());

        //添付区分
        String fileName = getTempFileName(reqMdl, tempDir);
        int attachKbn = getEnqAttachKbn(fileName);
        enqMainMdl.setEmnAttachKbn(attachKbn);

        //添付ID
        //添付名
        //添付位置
        if (attachKbn == GSConstEnquete.EMN_ATTACH_KBN_IMAGE
        || attachKbn == GSConstEnquete.EMN_ATTACH_KBN_FILE) {
            enqMainMdl.setEmnAttachId(paramMdl.getEnq210Url());
            enqMainMdl.setEmnAttachName(paramMdl.getEnq210TempDspName());
            enqMainMdl.setEmnAttachPos(paramMdl.getEnq210AttachPos());
        } else {
            enqMainMdl.setEmnAttachId(null);
            enqMainMdl.setEmnAttachName(null);
            enqMainMdl.setEmnAttachPos(0);
        }

        if (paramMdl.getEnq210editMode() == Enq210Form.EDITMODE_TEMPLATE) {
            // 公開期間_開始
            enqMainMdl.setEmnOpenStr(null);
            // 公開期間_終了
            enqMainMdl.setEmnOpenEnd(null);
            // 回答期限
            enqMainMdl.setEmnResEnd(null);
        } else {
            //公開期間_開始
            enqMainMdl.setEmnOpenStr(__createDate(paramMdl.getEnq210FrYear(),
                                                  paramMdl.getEnq210FrMonth(),
                                                  paramMdl.getEnq210FrDay()));

            //回答期限
            enqMainMdl.setEmnResEnd(__createDate(paramMdl.getEnq210AnsYear(),
                                                 paramMdl.getEnq210AnsMonth(),
                                                 paramMdl.getEnq210AnsDay()));
            //公開期間_終了
            enqMainMdl.setEmnOpenEnd(null);
            enqMainMdl.setEmnOpenEndKbn(GSConstEnquete.EMN_OPEN_END_KBN_NON);

            //結果公開期限 開始
            if (paramMdl.getEnq210AnsOpen() == Enq210Form.ANS_OPEN) {
                enqMainMdl.setEmnAnsPubStr(__createDate(paramMdl.getEnq210AnsPubFrYear(),
                                                     paramMdl.getEnq210AnsPubFrMonth(),
                                                     paramMdl.getEnq210AnsPubFrDay()));
                //公開期間_終了
                if (paramMdl.getEnq210ToKbn() != Enq210Form.TO_DATE_NOLIMIT) {
                    enqMainMdl.setEmnOpenEnd(__createDate(paramMdl.getEnq210ToYear(),
                            paramMdl.getEnq210ToMonth(),
                            paramMdl.getEnq210ToDay()));
                    enqMainMdl.setEmnOpenEndKbn(GSConstEnquete.EMN_OPEN_END_KBN_SPECIFIED);
                }
            } else {
                enqMainMdl.setEmnAnsPubStr(enqMainMdl.getEmnOpenStr());
            }
        }

        //匿名フラグ
        if (paramMdl.getEnq210Anony() == GSConstEnquete.EMN_ANONNY_ANONNY) {
            enqMainMdl.setEmnAnony(GSConstEnquete.EMN_ANONNY_ANONNY);
        } else {
            enqMainMdl.setEmnAnony(GSConstEnquete.EMN_ANONNY_NON);
        }
        //回答公開フラグ
        if (paramMdl.getEnq210AnsOpen() == GSConstEnquete.EMN_ANS_OPEN_PUBLIC) {
            enqMainMdl.setEmnAnsOpen(GSConstEnquete.EMN_ANS_OPEN_PUBLIC);
        } else {
            enqMainMdl.setEmnAnsOpen(GSConstEnquete.EMN_ANS_OPEN_PRIVATE);
        }
        //設問番号_種類
        if (paramMdl.getEnq210queSeqType() == Enq210Form.QUE_SEQTYPE_MANUAL) {
            enqMainMdl.setEmnQuesecType(GSConstEnquete.EMN_QUESEC_TYPE_MANUAL);
        } else {
            enqMainMdl.setEmnQuesecType(GSConstEnquete.EMN_QUESEC_TYPE_AUTO);
        }

        if (paramMdl.getEnq210editMode() == Enq210Form.EDITMODE_TEMPLATE) {
            enqMainMdl.setEmnSendGrp(GSConstEnquete.ENQ_SUBJECT_NOTSET);
            enqMainMdl.setEmnSendUsr(GSConstEnquete.ENQ_SUBJECT_NOTSET);
        } else {
            if (NullDefault.getString(paramMdl.getEnq210Send(), "").startsWith("G")) {
                String grpSid = paramMdl.getEnq210Send();
                grpSid = grpSid.substring(1);
                enqMainMdl.setEmnSendGrp(Integer.parseInt(grpSid));
                enqMainMdl.setEmnSendUsr(GSConstEnquete.ENQ_SUBJECT_NOTSET);
            } else {
                enqMainMdl.setEmnSendUsr(sessionUserSid);
                enqMainMdl.setEmnSendGrp(GSConstEnquete.ENQ_SUBJECT_NOTSET);
            }
        }

        enqMainMdl.setEmnEuid(sessionUserSid);
        enqMainMdl.setEmnEdate(now);
        return enqMainMdl;
    }


    /**
     * <br>[機  能] 設問情報の登録を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータモデル
     * @param reqMdl リクエストモデル
     * @param con コネクション
     * @param emnSid アンケートSID
     * @param mtCon 採番コントローラ
     * @param appRootPath アプリケーションルートパス
     * @param tempDir テンポラリディレクトリパス
     * @param now 登録日時
     * @throws SQLException SQL実行例外
     * @throws IOToolsException 添付ファイル情報の取得に失敗
     * @throws TempFileException 添付ファイル情報の登録に失敗
     */
    private void __entryQuestionData(Enq210ParamModel paramMdl,
                                     RequestModel reqMdl,
                                     Connection con,
                                     long emnSid,
                                     MlCountMtController mtCon,
                                     String appRootPath,
                                     String tempDir,
                                     UDate now)
     throws SQLException, IOToolsException, TempFileException {
        int sessionUserSid = reqMdl.getSmodel().getUsrsid();

        EnqQueMainDao dao = new EnqQueMainDao(con);

        EnqQueMainModel queMainMdl = new EnqQueMainModel();
        queMainMdl.setEmnSid(emnSid);
        queMainMdl.setEqmAuid(sessionUserSid);
        queMainMdl.setEqmAdate(now);
        queMainMdl.setEqmEuid(sessionUserSid);
        queMainMdl.setEqmEdate(now);

        int seq = 0;
        Enq220Biz biz220 = new Enq220Biz();
        String queTempDir = null;
        CommonBiz cmnBiz = new CommonBiz();
        List <String> binSid = null;
        String queTempFileName = null;
        int queAttackKbn = 0;
        int dspOrder = 0;

        for (Enq210QueModel baseForm : paramMdl.getEbaList()) {

            queMainMdl.setEqmSeq(++seq);
            queMainMdl.setEqmDspSec(++dspOrder);

            if (baseForm.getEnq210QueKbn() != GSConstEnquete.SYURUI_COMMENT) {
                if (paramMdl.getEnq210queSeqType() == Enq210knForm.QUE_SEQTYPE_MANUAL) {
                    queMainMdl.setEqmQueSec(baseForm.getEnq210QueNo());
                } else {
                    queMainMdl.setEqmQueSec(String.valueOf(baseForm.getEnq210AutoQueNo()));
                }
            } else {
                queMainMdl.setEqmQueSec(null);
            }

            queMainMdl.setEqmQuestion(baseForm.getEnq210Question());
            queMainMdl.setEqmQueKbn(baseForm.getEnq210QueKbn());

            String require = NullDefault.getString(baseForm.getEnq210Require(), "");
            if (require.equals(String.valueOf(GSConstEnquete.REQUIRE_ON))) {
                queMainMdl.setEqmRequire(GSConstEnquete.REQUIRE_ON);
            } else {
                queMainMdl.setEqmRequire(GSConstEnquete.REQUIRE_OFF);
            }

            queMainMdl.setEqmOther(baseForm.getEnq210OtherFlg());
            queMainMdl.setEqmDesc(baseForm.getEnq210QueDesc());
            queMainMdl.setEqmDescPlain(baseForm.getEnq210QueDescText());

            queTempFileName = biz220.getQueTempFileName(baseForm.getEnq210Id(), reqMdl, tempDir);
            queAttackKbn = biz220.getQueAttachKbn(queTempFileName);
            queMainMdl.setEqmAttachKbn(queAttackKbn);
            if (queAttackKbn == GSConstEnquete.EQM_ATTACH_KBN_IMAGE
            || queAttackKbn == GSConstEnquete.EQM_ATTACH_KBN_FILE) {
                queMainMdl.setEqmAttachId(baseForm.getEnq210AttachSid());
                queMainMdl.setEqmAttachName(baseForm.getEnq210AttachName());
                queMainMdl.setEqmAttachPos(baseForm.getEnq210AttachPos());
            } else {
                queMainMdl.setEqmAttachId(null);
                queMainMdl.setEqmAttachName(null);
                queMainMdl.setEqmAttachPos(0);
            }

            queMainMdl.setEqmLineKbn(baseForm.getEnq210LinePos());

            //添付ファイル情報の登録
            if (queAttackKbn == GSConstEnquete.EQM_ATTACH_KBN_IMAGE
            || queAttackKbn == GSConstEnquete.EQM_ATTACH_KBN_FILE) {
                queTempDir = biz220.getQueTempDir(baseForm.getEnq210Id(), reqMdl, tempDir);
                binSid = cmnBiz.insertBinInfo(con, queTempDir, appRootPath,
                                                        mtCon, sessionUserSid, now);
                queMainMdl.setEqmAttachId(binSid.get(0));
            }

            dao.insert(queMainMdl);

            //設問_サブ情報の登録
            __entryQuestionSubData(paramMdl, reqMdl, con, baseForm, queMainMdl);
        }
    }

    /**
     * <br>[機  能] 設問_サブ情報の登録を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータモデル
     * @param reqMdl リクエストモデル
     * @param con コネクション
     * @param baseForm パラメータ_設問情報
     * @param queMainMdl 設問_基本情報
     * @throws SQLException SQL実行例外
     */
    private void __entryQuestionSubData(Enq210ParamModel paramMdl,
                                     RequestModel reqMdl,
                                     Connection con,
                                     Enq210QueModel baseForm,
                                     EnqQueMainModel queMainMdl) throws SQLException {

        long emnSid = queMainMdl.getEmnSid();

        EnqQueSubModel queSubMdl =  new EnqQueSubModel();
        queSubMdl.setEmnSid(emnSid);
        queSubMdl.setEqmSeq(queMainMdl.getEqmSeq());
        queSubMdl.setEqsAuid(queMainMdl.getEqmAuid());
        queSubMdl.setEqsAdate(queMainMdl.getEqmAdate());
        queSubMdl.setEqsEuid(queMainMdl.getEqmEuid());
        queSubMdl.setEqsEdate(queMainMdl.getEqmEdate());

        EnqQueSubDao dao = new EnqQueSubDao(con);
        int queKbn = queMainMdl.getEqmQueKbn();
        if (queKbn != GSConstEnquete.SYURUI_SINGLE
        && queKbn != GSConstEnquete.SYURUI_MULTIPLE) {
            queSubMdl.setEqsSeq(0);
            queSubMdl.setEqsDspSec(0);
            queSubMdl.setEqsDspName(null);

            if (baseForm.getEnq210initFlg() == Enq210QueModel.INITFLG_SELECT) {
                String eqsDef = null;
                if (queKbn == GSConstEnquete.SYURUI_TEXT
                || queKbn == GSConstEnquete.SYURUI_TEXTAREA) {
                    //初期値_文字
                    queSubMdl.setEqsDefTxt(baseForm.getEnq210initTxt());
                    eqsDef = baseForm.getEnq210initTxt();
                } else if (queKbn == GSConstEnquete.SYURUI_INTEGER) {
                    //初期値_数値
                    queSubMdl.setEqsDefNum(
                            Long.parseLong(baseForm.getEnq210initTxt()));
                    eqsDef = baseForm.getEnq210initTxt();
                } else if (queKbn == GSConstEnquete.SYURUI_DAY) {
                    //初期値_日付
                    queSubMdl.setEqsDefDat(baseForm.getEnq210initDate());
                    eqsDef = UDateUtil.getSlashYYMD(baseForm.getEnq210initDate());
                }
                queSubMdl.setEqsDef(eqsDef);

            } else {
                queSubMdl.setEqsDefTxt(null);
                queSubMdl.setEqsDefNum(0);
                queSubMdl.setEqsDefDat(null);
                queSubMdl.setEqsDef(null);
            }

            //範囲フラグ
            if (baseForm.getEnq210rangeFlg() == Enq210QueModel.RANGEFLG_SELECT) {
                if (queKbn == GSConstEnquete.SYURUI_INTEGER) {
                    //範囲 数値
                    if (!StringUtil.isNullZeroString(baseForm.getEnq210rangeTxtFr())) {
                        queSubMdl.setEqsRngStrNum(Long.parseLong(baseForm.getEnq210rangeTxtFr()));
                    }
                    if (!StringUtil.isNullZeroString(baseForm.getEnq210rangeTxtTo())) {
                        queSubMdl.setEqsRngEndNum(Long.parseLong(baseForm.getEnq210rangeTxtTo()));
                    }
                } else if (queKbn == GSConstEnquete.SYURUI_DAY) {
                    //範囲 日付
                    UDate frDate = baseForm.getEnq210rangeDateFr();
                    if (frDate != null) {
                        frDate.setZeroHhMmSs();
                        queSubMdl.setEqsRngStrDat(frDate);
                    }
                    UDate toDate = baseForm.getEnq210rangeDateTo();
                    if (toDate != null) {
                        toDate.setZeroHhMmSs();
                        queSubMdl.setEqsRngEndDat(toDate);
                    }
                }
            } else {
                queSubMdl.setEqsRngStrNum(0);
                queSubMdl.setEqsRngEndNum(0);
                queSubMdl.setEqsRngStrDat(null);
                queSubMdl.setEqsRngEndDat(null);
            }

            //単位
            if (queKbn == GSConstEnquete.SYURUI_INTEGER) {
                queSubMdl.setEqsUnitNum(baseForm.getEnq210unitNum());
            } else {
                queSubMdl.setEqsUnitNum(null);
            }

            dao.insert(queSubMdl, baseForm);

        } else {

            List<Enq210QueSubModel> subList = baseForm.getQueSubList();
            if (subList == null || subList.isEmpty()) {
                return;
            }

            int eqsSeq = 0;
            for (Enq210QueSubModel subForm : subList) {
                queSubMdl.setEqsSeq(++eqsSeq);
                queSubMdl.setEqsDspSec(eqsSeq);
                queSubMdl.setEqsDspName(subForm.getEnqDspName());
                queSubMdl.setEqsDefTxt(null);
                queSubMdl.setEqsDefNum(subForm.getEnqDefFlg());
                queSubMdl.setEqsDefDat(null);
                queSubMdl.setEqsDef(null);
                queSubMdl.setEqsRngStrNum(0);
                queSubMdl.setEqsRngEndNum(0);
                queSubMdl.setEqsRngStrDat(null);
                queSubMdl.setEqsRngEndDat(null);
                queSubMdl.setEqsUnitNum(null);

                dao.insert(queSubMdl);
            }

            //その他
            if (baseForm.getEnq210OtherFlg() == 1 || baseForm.getEnq210OtherFlg() == 2) {
                queSubMdl.setEqsSeq(GSConstEnquete.EQS_SEQ_OTHER);
                queSubMdl.setEqsDspSec(eqsSeq + 1);
                queSubMdl.setEqsDspName(null);
                queSubMdl.setEqsDefTxt(null);
                queSubMdl.setEqsDefNum(0);
                queSubMdl.setEqsDefDat(null);
                queSubMdl.setEqsDef(null);
                queSubMdl.setEqsRngStrNum(0);
                queSubMdl.setEqsRngEndNum(0);
                queSubMdl.setEqsRngStrDat(null);
                queSubMdl.setEqsRngEndDat(null);
                queSubMdl.setEqsUnitNum(null);

                dao.insert(queSubMdl);
            }
        }
    }

    /**
     * <br>[機  能] 対象者一覧(未選択)を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @return 対象者一覧
     * @throws SQLException SQL実行時例外
     */
    private List<LabelValueBean> __createAnswerRightCombo(
            Enq210ParamModel paramMdl, Connection con)
    throws SQLException {

        int ansGrpSid = paramMdl.getEnq210answerGroup();

        List<LabelValueBean> ansCombo = new ArrayList<LabelValueBean>();

        String[] selectList = paramMdl.getEnq210answerList();
        if (ansGrpSid == Enq210Form.ANSER_GROUP_GRPLIST) {
            //グループを全て取得
            GroupDao dao = new GroupDao(con);
            CmnCmbsortConfDao sortDao = new CmnCmbsortConfDao(con);
            CmnCmbsortConfModel sortMdl = sortDao.getCmbSortData();
            ArrayList<GroupModel> allGpList = dao.getGroupTree(sortMdl);

            //選択済みのグループを除外
            List<String> selectGrpList = new ArrayList<String>();
            if (selectList != null) {
                selectGrpList = Arrays.asList(selectList);
            }

            for (GroupModel bean : allGpList) {
                if (!selectGrpList.contains("G" + bean.getGroupSid())) {
                    ansCombo.add(new LabelValueBean(
                            bean.getGroupName(), String.valueOf("G" + bean.getGroupSid())));
                }
            }
        } else {

            //除外するユーザSID
            ArrayList<Integer> usrSids = new ArrayList<Integer>();
            if (selectList != null) {
                for (String selectSid : selectList) {
                    usrSids.add(new Integer(NullDefault.getInt(selectSid, -1)));
                }
            }

            UserBiz userBiz = new UserBiz();
            List<CmnUsrmInfModel> userList
                = userBiz.getBelongUserList(con, ansGrpSid, usrSids);

            for (CmnUsrmInfModel userMdl : userList) {
                ansCombo.add(
                        new LabelValueBean(userMdl.getUsiSei() + " " + userMdl.getUsiMei(),
                                String.valueOf(userMdl.getUsrSid())));
            }
        }

        return ansCombo;
    }


    /**
     * <br>[機  能] 対象者一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @return 対象者一覧
     * @throws SQLException SQL実行時例外
     */
    protected List<LabelValueBean> _createAnswerCombo(
            Enq210ParamModel paramMdl, Connection con)
    throws SQLException {

        List<LabelValueBean> ansCombo = new ArrayList<LabelValueBean>();

        ArrayList<Integer> grpSids = new ArrayList<Integer>();
        ArrayList<String> usrSids = new ArrayList<String>();

        //ユーザSIDとグループSIDを分離
        String[] selectAnsList = paramMdl.getEnq210answerList();
        if (selectAnsList != null) {
            for (String ansSid : selectAnsList) {
                String str = NullDefault.getString(ansSid, "-1");
                if (str.contains(new String("G").subSequence(0, 1))) {
                    //グループ
                    grpSids.add(new Integer(str.substring(1, str.length())));
                } else {
                    //ユーザ
                    if (canAnsUser(str)) {
                        usrSids.add(str);
                    }
                }
            }
        }

        //グループ情報取得
        UsidSelectGrpNameDao gdao = new UsidSelectGrpNameDao(con);
        ArrayList<GroupModel> grpList = gdao.selectGroupNmListOrderbyClass(grpSids);
        for (GroupModel grpMdl : grpList) {
            ansCombo.add(new LabelValueBean(grpMdl.getGroupName(), "G" + grpMdl.getGroupSid()));
        }

        //ユーザ情報取得
        UserBiz userBiz = new UserBiz();
        ArrayList<BaseUserModel> userList
                = userBiz.getBaseUserList(con,
                                        (String[]) usrSids.toArray(new String[usrSids.size()]));
        for (BaseUserModel usrMdl : userList) {
            ansCombo.add(
                    new LabelValueBean(usrMdl.getUsisei() + " " + usrMdl.getUsimei(),
                                                String.valueOf(usrMdl.getUsrsid())));
        }

        return ansCombo;
    }

    /**
     * <br>[機  能] アンケートを編集可能かを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @return true: 編集可能 false: 編集不可
     * @throws SQLException SQL実行時例外
     */
    public boolean canEditEnquete(Connection con, Enq210ParamModel paramMdl)
    throws SQLException {
        if (paramMdl.getEnqEditMode() != GSConstEnquete.EDITMODE_EDIT) {
            return true;
        }

        EnqMainDao enqMainDao = new EnqMainDao(con);
        return enqMainDao.select(paramMdl.getEditEnqSid()) != null;
    }

    /**
     * <br>[機  能] 添付ファイル名取得
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param binSid バイナリSID
     * @return 添付ファイル名
     * @throws TempFileException 添付ファイル操作時に発生する例外
     */
    private String __getTempFileName(Connection con, String binSid) throws TempFileException {

        log__.debug("添付ファイル名取得処理");

        if (binSid == null || binSid.equals("")) {
            return "";
        }

        CmnBinfModel model = new CmnBinfModel();
        CmnBinfDao dao = new CmnBinfDao(con);
        model = dao.getBinInfo(NullDefault.getLong(binSid, -1));
        return model.getBinFileName();
    }

    /**
     * <br>[機  能] 指定したファイル名から添付区分を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param fileName ファイル名
     * @return 添付区分
     */
    public int getEnqAttachKbn(String fileName) {
        if (StringUtil.isNullZeroString(fileName)) {
            return GSConstEnquete.EMN_ATTACH_KBN_NONE;
        }

        //指定したファイルが画像ファイルかを判定
        if (Cmn110Biz.isExtensionOk(fileName)) {
            return GSConstEnquete.EMN_ATTACH_KBN_IMAGE;
        }

        return GSConstEnquete.EMN_ATTACH_KBN_FILE;
    }

    /**
     * <br>[機  能] 発信者名称を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @param senderId 発信者ID
     * @return 発信者名称
     * @throws SQLException SQL実行時例外
     */
    public String getSenderName(Connection con, RequestModel reqMdl, String senderId)
    throws SQLException {
        return getSenderName(con, reqMdl, senderId, 0);
    }

    /**
     * <br>[機  能] 発信者名称を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @param senderId 発信者ID
     * @param userSid ユーザSID
     * @return 発信者名称
     * @throws SQLException SQL実行時例外
     */
    public String getSenderName(Connection con, RequestModel reqMdl, String senderId, int userSid)
    throws SQLException {
        String senderName = null;
        if (NullDefault.getString(senderId, "").startsWith("G")) {
            String grpSid = senderId.substring(1);
            CmnGroupmDao grpDao = new CmnGroupmDao(con);
            senderName = grpDao.selectGroup(Integer.parseInt(grpSid)).getGrpName();
        } else if (userSid > 0) {
            CmnUsrmInfDao usrmInfDao = new CmnUsrmInfDao(con);
            CmnUsrmInfModel usrInfMdl = usrmInfDao.select(userSid);
            if (usrInfMdl != null) {
                senderName = usrInfMdl.getUsiSei() + " " + usrInfMdl.getUsiMei();
            }
        } else {
            senderName = reqMdl.getSmodel().getUsiseimei();
        }

        return senderName;
    }


    /**
     * <br>[機  能] アンケート回答者に指定可能なユーザかを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param userSid ユーザSID
     * @return 判定結果 true:指定可能 false:指定不可
     */
    public boolean canAnsUser(String userSid) {
        int intUserSid = Integer.parseInt(userSid);
        if (intUserSid < 0) {
            return false;
        }

        return intUserSid != GSConst.SYSTEM_USER_ADMIN
                && intUserSid != GSConst.SYSTEM_USER_MAIL;
    }

    /**
     * <br>[機  能] 指定された年月日を設定したUDateを生成する
     * <br>[解  説]
     * <br>[備  考]
     * @param year 年
     * @param month 月
     * @param day 日
     * @return UDate
     */
    private UDate __createDate(int year, int month, int day) {
        UDate date = new UDate();
        date.setDate(year, month, day);
        date.setZeroHhMmSs();
        return date;
    }


    /**
     * <br>[機  能] 設問情報変更チェック
     * <br>[解  説]
     * <br>[備  考]
     * @param emnSid アンケートSID
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエストモデル
     * @param con コネクション
     * @param appRoot ルートパス
     * @param tempDir テンポラリディレクトリパス
     * @throws SQLException SQL実行例外
     * @return jsonData jsonコメントリスト
     * @throws IOToolsException 設問情報の読み込みに失敗
     * @throws TempFileException 添付ファイル情報の取得に失敗
     * @throws IOException 設問情報の読み込みに失敗
     */
    public boolean __checkSameQue(long emnSid,
            Enq210ParamModel paramMdl, RequestModel reqMdl, Connection con,
            String appRoot, String tempDir)
            throws SQLException, IOToolsException, TempFileException,
            IOException {

        //添付ファイル 添付ボタン、削除ボタンクリックチェック
        if (GSConstEnquete.ENQ_QUS_CHG_YES == paramMdl.getTempClickBtn()) {
            return false;
        }

        //データベースから取得した設問情報リスト
        ArrayList<Enq210QueModel> queList = __getQueList(emnSid, reqMdl, con,
                appRoot, tempDir);

        //編集した設問情報リスト
        List<Enq210QueModel> editQueList = readQueList(tempDir);

        //取得設問情報リスト、編集設問情報リストのサイズチェック
        if (queList.size() != editQueList.size()) {
            return false;
        }

        //取得設問情報リスト、編集設問情報リストの要素を一つずつ比較
        for (int i = 0; i < queList.size(); i++) {

            Enq210QueModel que = queList.get(i);
            Enq210QueModel editQue = editQueList.get(i);

            //アンケートSID一致チェック
            if (que.getEnq210Sid() != editQue.getEnq210Sid()) {
                return false;
            }

            //設問連番一致チェック
//            if (que.getEnq210Seq() != editQue.getEnq210Seq()) {
//                return false;
//            }

            //表示順
            if (que.getEnq210DspOrder() != editQue.getEnq210DspOrder()) {
                return false;
            }

            //設問番号
            if (que.getEnq210QueKbn() != GSConstEnquete.SYURUI_COMMENT) {
                if (paramMdl.getEnq210queSeqType() == Enq210knForm.QUE_SEQTYPE_MANUAL) {
                    if (!__checkSameQueValue(que.getEnq210QueNo(), editQue.getEnq210QueNo())) {
                        return false;
                    }
                } else {
                    if (!__checkSameQueValue(que.getEnq210QueNo(),
                            String.valueOf(editQue.getEnq210AutoQueNo()))) {
                        return false;
                    }
                }
            }

            //設問番号(自動)
            if (que.getEnq210AutoQueNo() != editQue.getEnq210AutoQueNo()) {
                return false;
            }

            //設問
            if (!__checkSameQueValue(que.getEnq210Question(), editQue.getEnq210Question())) {
                return false;
            }

            //設問種類区分
            if (que.getEnq210QueKbn() != editQue.getEnq210QueKbn()) {
                return false;
            }

            //必須フラグ
            if (!__checkSameQueValue(que.getEnq210Require()
                    , NullDefault.getString(editQue.getEnq210Require(),
                    String.valueOf(GSConstEnquete.REQUIRE_OFF)))) {
                return false;
            }

            //その他入力有無
            if (que.getEnq210OtherFlg() != editQue.getEnq210OtherFlg()) {
                return false;
            }

            //説明
            if (!__checkSameQueValue(que.getEnq210QueDesc(), editQue.getEnq210QueDesc())) {
                return false;
            }

            //説明(テキストのみ)
            if (!__checkSameQueValue(que.getEnq210QueDescText(), editQue.getEnq210QueDescText())) {
                return false;
            }

            //添付区分
            if (que.getEnq210AttachKbn() != editQue.getEnq210AttachKbn()) {
                return false;
            }

            //添付ファイルSID
            if (!__checkSameQueValue(que.getEnq210AttachSid(), editQue.getEnq210AttachSid())) {
                return false;
            }

            //添付名
            if (!__checkSameQueValue(que.getEnq210AttachName(), editQue.getEnq210AttachName())) {
                return false;
            }

            //添付位置
            if (que.getEnq210AttachPos() != editQue.getEnq210AttachPos()) {
                return false;
            }

            //横線位置
            if (que.getEnq210LinePos() != editQue.getEnq210LinePos()) {
                return false;
            }

            //URL
            if (!__checkSameQueValue(que.getEnq210Url(), editQue.getEnq210Url())) {
                return false;
            }

            //表示名
            if (!__checkSameQueValue(que.getEnq210DspName(), editQue.getEnq210DspName())) {
                return false;
            }

            //初期値フラグ
            if (que.getEnq210initFlg() != editQue.getEnq210initFlg()) {
                return false;
            }

            //初期値 テキスト
            if (!__checkSameQueValue(que.getEnq210initTxt(), editQue.getEnq210initTxt())) {
                return false;
            }

            // 初期値 日付
            if (!__checkSameQueDate(que.getEnq210initDate(), editQue.getEnq210initDate())) {
                return false;
            }

            //初期値 日付 表示用
            if (!__checkSameQueValue(que.getEnq210initDspDate(), editQue.getEnq210initDspDate())) {
                return false;
            }

            //範囲フラグ
            if (que.getEnq210rangeFlg() != editQue.getEnq210rangeFlg()) {
                return false;
            }

            //範囲From テキスト
            if (!__checkSameQueValue(que.getEnq210rangeTxtFr(), editQue.getEnq210rangeTxtFr())) {
                return false;
            }

            //範囲To テキスト
            if (!__checkSameQueValue(que.getEnq210rangeTxtTo(), editQue.getEnq210rangeTxtTo())) {
                return false;
            }

            //範囲From 日付
            if (!__checkSameQueDate(que.getEnq210rangeDateFr(), editQue.getEnq210rangeDateFr())) {
                return false;
            }

            //範囲To 日付
            if (!__checkSameQueDate(que.getEnq210rangeDateTo(), editQue.getEnq210rangeDateTo())) {
                return false;
            }

            //範囲From 日付 表示用
            if (!__checkSameQueValue(que.getEnq210rangeTxtFrDsp(),
                                                    editQue.getEnq210rangeTxtFrDsp())) {
                return false;
            }

            //範囲To 日付 表示用
            if (!__checkSameQueValue(que.getEnq210rangeTxtToDsp(),
                                                    editQue.getEnq210rangeTxtToDsp())) {
                return false;
            }

            //単位
            if (!__checkSameQueValue(que.getEnq210unitNum(), editQue.getEnq210unitNum())) {
                return false;
            }

            //設問Index
            if (que.getEnq210queIndex() != editQue.getEnq210queIndex()) {
                return false;
            }

            //設問種類名称
            if (!__checkSameQueValue(que.getEnq210SyuruiName(), editQue.getEnq210SyuruiName())) {
                return false;
            }

            /** 設問情報(選択肢)一覧変更チェック     ここから*/
            //取得設問情報(選択肢)リスト
            List<Enq210QueSubModel> queSubList = que.getQueSubList();
            //編集設問情報(選択肢)リスト
            List<Enq210QueSubModel> editQueSubList = editQue.getQueSubList();

            if (queSubList == null) {
                queSubList = new ArrayList<Enq210QueSubModel>();
            }
            if (editQueSubList == null) {
                editQueSubList = new ArrayList<Enq210QueSubModel>();
            }

            //取得設問情報(選択肢)リストと編集設問情報(選択肢)リストのサイズチェック
            if (queSubList.size() != editQueSubList.size()) {
                return false;
            }

            //取得設問情報(選択肢)リスト、編集設問情報(選択肢)リストの要素を一つずつ比較
            for (int j = 0; j < queSubList.size(); j++) {
                Enq210QueSubModel queSub = queSubList.get(j);
                Enq210QueSubModel editQueSub = editQueSubList.get(j);

                //インデックス番号
//                if (queSub.getEnqIndex() != editQueSub.getEnqIndex()) {
//                    return false;
//                }

                //アンケートSID
               if (queSub.getEnqSid() != editQueSub.getEnqSid()) {
                   return false;
               }

               //設問連番
//               if (queSub.getEnqSeq() != editQueSub.getEnqSeq()) {
//                   return false;
//               }

               //設問サブ連番
//               if (queSub.getEnqSubSeq() != editQueSub.getEnqSubSeq()) {
//                   return false;
//               }

               //表示順
               if (queSub.getEnqDspSec() != editQueSub.getEnqDspSec()) {
                   return false;
               }

               //表示名
                if (!__checkSameQueValue(queSub.getEnqDspName(), editQueSub.getEnqDspName())) {
                    return false;
                }

                //初期値フラグ
                if (queSub.getEnqDefFlg() != editQueSub.getEnqDefFlg()) {
                    return false;
                }

            }
        }

        return true;
    }

    /**
     * <br>[機  能] 設問情報の変更前、変更後が同一かを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param before 変更前設問情報
     * @param after 変更後設問情報
     * @return true: 同一 false: 変更あり
     */
    private boolean __checkSameQueValue(String before, String after) {
        return NullDefault.getString(before, "").equals(NullDefault.getString(after, ""));
    }

    /**
     * <br>[機  能] 設問情報(日付)の変更前、変更後が同一かを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param before 変更前設問情報
     * @param after 変更後設問情報
     * @return true: 同一 false: 変更あり
     */
    private boolean __checkSameQueDate(UDate before, UDate after) {
        if (before == null) {
            return after == null;
        }

        if (after == null) {
            return before == null;
        }

        return before.compareDateYMD(after) == 0;
    }

    /**
     * アンケートの設問が変更されたかの判定結果を取得します
     * @param emnSid アンケートSID
     * @param con コネクション
     * @param paramMdl パラメータモデル
     * @param tempDir テンポラリディレクトリパス
     * @param appRoot アプリケーションルートパス
     * @param reqMdl リクエスト情報
     * @throws SQLException SQL実行例外
     * @return jsonData jsonコメントリスト
     * @throws IOToolsException 設問情報の読み込みに失敗
     * @throws TempFileException 設問情報の読み込みに失敗
     * @throws IOException 設問情報の読み込みに失敗
     */
    public JSONObject getChangeEnqQuection(long emnSid,
            Connection con,
            Enq210ParamModel paramMdl,
            String tempDir,
            String appRoot,
            RequestModel reqMdl)
            throws SQLException, IOToolsException, TempFileException, IOException {

        //回答件数が1件以上ある時は回答済みフラグに1をセットする
        int answerFlg = 0;
        Enq210Dao dao210 = new Enq210Dao(con);
        if (dao210.getAnsCount(emnSid) > 0) {
            answerFlg = GSConstEnquete.ENQ_QUS_CHG_YES;
        } else {
            answerFlg = GSConstEnquete.ENQ_QUS_CHG_NO;
        }

        //設問情報ファイル中身チェック
        int queChangeFlg = 0;
        boolean flg = false;
        boolean queData = __checkSameQue(emnSid, paramMdl, reqMdl, con, appRoot, tempDir);
        if (flg != queData) {
            queChangeFlg = GSConstEnquete.ENQ_QUS_CHG_NO;
        } else {
            queChangeFlg = GSConstEnquete.ENQ_QUS_CHG_YES;
        }

        JSONObject jsonData = new JSONObject();

        //Jsonデータ成形
        Enq210knEnqDataModel enq210knEnqDataMdl = new Enq210knEnqDataModel();

        enq210knEnqDataMdl.setEnqAnswerFlg(answerFlg);
        enq210knEnqDataMdl.setEnqQchangeFlg(queChangeFlg);

        jsonData = JSONObject.fromObject(enq210knEnqDataMdl);

        return jsonData;
    }
}
