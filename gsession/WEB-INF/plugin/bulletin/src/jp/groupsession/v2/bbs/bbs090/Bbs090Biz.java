package jp.groupsession.v2.bbs.bbs090;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.bbs.BbsBiz;
import jp.groupsession.v2.bbs.GSConstBulletin;
import jp.groupsession.v2.bbs.dao.BbsWriteInfDao;
import jp.groupsession.v2.bbs.model.BulletinDspModel;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.base.CmnGroupmDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmInfDao;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.cmn.model.base.CmnGroupmModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.usr.GSConstUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 掲示板 投稿登録画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bbs090Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bbs090Biz.class);

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説] 処理モード = 編集の場合、フォーラム情報を設定する
     * <br>[備  考]
     * @param cmd CMDパラメータ
     * @param reqMdl リクエスト情報
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param appRoot アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリパス
     * @throws SQLException 実行例外
     * @throws IOException 添付ファイルの操作に失敗
     * @throws IOToolsException 添付ファイルの操作に失敗
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    @SuppressWarnings("unchecked")
    public void setInitData(String cmd, RequestModel reqMdl, Bbs090ParamModel paramMdl,
                            Connection con, String appRoot, String tempDir)
    throws SQLException, IOException, IOToolsException, TempFileException {
        log__.debug("START");

        //フォーラム名、スレッド名称を設定
        BbsBiz bbsBiz = new BbsBiz();
        BulletinDspModel bbsMdl = bbsBiz.getThreadData(con, paramMdl.getThreadSid());
        paramMdl.setBbs090forumName(bbsMdl.getBfiName());
        paramMdl.setBbs090threTitle(bbsMdl.getBtiTitle());

        //投稿一覧からの遷移、かつ処理モード = 編集 または
        //投稿一覧からの遷移、かつ処理モード = 引用投稿 の場合
        //投稿情報を取得する
        int cmdMode = paramMdl.getBbs090cmdMode();
        if ((cmd.equals("editWrite") && cmdMode == GSConstBulletin.BBSCMDMODE_EDIT)
        || (cmd.equals("inyouWrite") && cmdMode == GSConstBulletin.BBSCMDMODE_INYOU)) {

            //投稿情報を設定
            BulletinDspModel bbsWriteMdl = bbsBiz.getWriteData(con, paramMdl.getBbs080writeSid());
            paramMdl.setBbs090value(bbsWriteMdl.getBwiValue());
            if (paramMdl.getBbs090cmdMode() == GSConstBulletin.BBSCMDMODE_INYOU) {
                if (!StringUtil.isNullZeroString(paramMdl.getBbs090value())) {
                    //引用投稿の場合は内容の各行に「>」を付与する
                    String separator = "\r\n";
                    String writeValue = StringUtil.substitute(paramMdl.getBbs090value(),
                                                            separator,
                                                            separator.concat("> "));
                    paramMdl.setBbs090value("> ".concat(writeValue));
                }
            }
            if (bbsWriteMdl.getGrpSid() > 0) {
                paramMdl.setBbs090contributor(bbsWriteMdl.getGrpSid());
            }

            //処理モード = 編集の場合は添付ファイルを設定する
            if (cmdMode == GSConstBulletin.BBSCMDMODE_EDIT) {
                getTempData(bbsWriteMdl, paramMdl, con, appRoot, tempDir, reqMdl);
            }

        }

        if (cmd.equals("addWrite") && cmdMode == GSConstBulletin.BBSCMDMODE_ADD) {
            BulletinDspModel bbsModel = bbsBiz.getForumData(con, paramMdl.getBbs010forumSid());
            if (bbsModel != null) {

                if (bbsModel.getBfiTemplateKbn() == GSConstBulletin.BBS_THRE_TEMPLATE_YES
                && bbsModel.getBfiTemplateWrite() == GSConstBulletin.BBS_THRE_TEMPLATE_WRITE_YES) {
                    //スレッドテンプレートを適応する。
                    paramMdl.setBbs090value(NullDefault.getString(bbsModel.getBfiTemplate(), ""));
                }
            }
        }

        //添付ファイル一覧を設定
        CommonBiz cmnBiz = new CommonBiz();

        List<LabelValueBean> sortList = cmnBiz.getTempFileLabelList(tempDir);
        Collections.sort(sortList);
        paramMdl.setBbs090FileLabelList(sortList);

        //投稿者コンボを設定
        int registUser = reqMdl.getSmodel().getUsrsid();
        if (paramMdl.getBbs090cmdMode() == GSConstBulletin.BBSCMDMODE_EDIT) {
            //編集の場合、新規登録時の登録ユーザを投稿者の基準とする
            BbsWriteInfDao writeDao = new BbsWriteInfDao(con);
            registUser = writeDao.getWriteAuid(paramMdl.getBbs080writeSid());
        }
        CmnUsrmInfDao usiDao = new CmnUsrmInfDao(con);
        CmnUsrmInfModel usiMdl = usiDao.selectUserNameAndJtkbn(registUser);

        int contributor = paramMdl.getBbs090contributor();
        if (contributor > 0) {
            GroupDao grpDao = new GroupDao(con);
            if (!grpDao.isBelong(registUser, contributor)) {
                //投稿者が投稿グループに所属していない場合には投稿者を編集不可にする。
                paramMdl.setBbs090contributorEditKbn(0);
                CmnGroupmDao cmnGrpDao = new CmnGroupmDao(con);
                CmnGroupmModel grpMdl = cmnGrpDao.selectGroup(contributor);
                if (grpMdl != null) {
                    paramMdl.setBbs090contributorJKbn(grpMdl.getGrpJkbn());
                    LabelValueBean label;
                    List<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
                    label = new LabelValueBean(grpMdl.getGrpName(), String
                            .valueOf(contributor));
                    labelList.add(label);
                    paramMdl.setBbs090contributorList(labelList);
                }
            }
        } else if (usiMdl.getUsrJkbn() == GSConstUser.USER_JTKBN_DELETE) {
            //投稿者が削除済みユーザの場合には投稿者を編集不可にする。
            paramMdl.setBbs090contributorEditKbn(0);

            LabelValueBean label;
            List<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
            String name = NullDefault.getString(usiMdl.getUsiSei(), "")
                    + "　" + NullDefault.getString(usiMdl.getUsiMei(), "");
            paramMdl.setBbs090contributorJKbn(usiMdl.getUsrJkbn());

            label = new LabelValueBean(name, String
                    .valueOf(contributor));
            labelList.add(label);
            paramMdl.setBbs090contributorList(labelList);
        }


        if (paramMdl.getBbs090contributorEditKbn() == 1) {
                //投稿者コンボを設定
            paramMdl.setBbs090contributorList(
                    cmnBiz.getMARegistrantCombo(con, registUser, reqMdl));
        }
        log__.debug("End");
    }

    /**
     * <br>[機  能] 添付ファイルを設定する
     * <br>[解  説] 処理モード = 編集の場合、添付ファイルを設定する
     * <br>[備  考]
     * @param bbsWriteMdl 添付ファイルデータ
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param appRoot アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリパス
     * @param reqMdl リクエスト情報
     * @throws SQLException 実行例外
     * @throws IOException 添付ファイルの操作に失敗
     * @throws IOToolsException 添付ファイルの操作に失敗
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public void getTempData(BulletinDspModel bbsWriteMdl, Bbs090ParamModel paramMdl,
                            Connection con, String appRoot, String tempDir,
                            RequestModel reqMdl)
    throws SQLException, IOException, IOToolsException, TempFileException {

        CommonBiz cmnBiz = new CommonBiz();
        String dateStr = (new UDate()).getDateString(); //現在日付の文字列(YYYYMMDD)

        if (bbsWriteMdl.getTmpFileList() != null
                      && bbsWriteMdl.getTmpFileList().size() > 0) {

            List < CmnBinfModel > tmpFileList = bbsWriteMdl.getTmpFileList();
            String[] binSids = new String[tmpFileList.size()];

            //バイナリSIDの取得
            for (int i = 0; i < tmpFileList.size(); i++) {
                binSids[i] = String.valueOf(tmpFileList.get(i).getBinSid());
            }

            //取得したバイナリSIDからバイナリ情報を取得
            List<CmnBinfModel> binList = cmnBiz.getBinInfo(con, binSids, reqMdl.getDomain());

            int fileNum = 1;

            for (CmnBinfModel cbMdl : binList) {

                //添付ファイルをテンポラリディレクトリへ移動する
                cmnBiz.saveTempFile(dateStr, cbMdl,
                       appRoot, tempDir, fileNum);
                fileNum++;
            }
        }
    }
}
