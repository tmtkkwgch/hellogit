package jp.groupsession.v2.wml.wml012;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.io.ObjectFile;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.GSConstWebmail;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn110.Cmn110FileModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.wml.biz.WmlBiz;
import jp.groupsession.v2.wml.dao.base.WmlAccountDao;
import jp.groupsession.v2.wml.dao.base.WmlAdmConfDao;
import jp.groupsession.v2.wml.model.MailTempFileModel;
import jp.groupsession.v2.wml.model.base.WmlAccountModel;
import jp.groupsession.v2.wml.model.base.WmlAdmConfModel;
import jp.groupsession.v2.wml.wml010.Wml010Biz;
import jp.groupsession.v2.wml.wml010.Wml010Const;
import jp.groupsession.v2.wml.wml010.Wml010Form;

/**
 * <br>[機  能] WEBメール 送信メール確認(ポップアップ)画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml012Biz extends Wml010Biz {

    /** ドメイン種別 */
    private int domainType__  =  0;

    /**
     * <br>[機  能] 送信メール情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @param tempRootDir テンポラリルートディレクトリ
     * @return パラメータ情報
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException 送信メール情報の読み込みに失敗
     */
    public Wml012ParamModel getInitData(Connection con, RequestModel reqMdl, String tempRootDir)
        throws SQLException, IOToolsException {
        File sendMailDataPath = __getSaveFilePath(reqMdl, tempRootDir);
        Wml012ParamModel paramMdl  = null;
        if (sendMailDataPath.exists()) {
            ObjectFile objFile
                = new ObjectFile(sendMailDataPath.getParent(), sendMailDataPath.getName());

            paramMdl = (Wml012ParamModel) objFile.load();
            Map<String, String> domainMap = new HashMap<String, String>();
            domainType__ = 0;
            paramMdl.setWml012ToList(
                    __createAddressList(paramMdl.getWml010sendAddressTo(), domainMap));
            paramMdl.setWml012CcList(
                    __createAddressList(paramMdl.getWml010sendAddressCc(), domainMap));
            paramMdl.setWml012BccList(
                    __createAddressList(paramMdl.getWml010sendAddressBcc(), domainMap));

            paramMdl.setWml012ViewBody(paramMdl.getWml010svSendContent());
            if (paramMdl.getWml010sendMailHtml() != Wml010Const.SEND_HTMLMAIL_HTML) {
                paramMdl.setWml012ViewBody(
                        StringUtilHtml.transToHTmlForTextArea(paramMdl.getWml012ViewBody()));
            }

            GsMessage gsMsg = new GsMessage(reqMdl);
            String sendPlanDate = null;

            int sendAccountSid = paramMdl.getWml010sendAccount();
            if (paramMdl.getSendMailPlanType() == Wml010Form.SENDMAILPLAN_TYPE_LATER) {
                    sendPlanDate = gsMsg.getMessage("cmn.view.date", new String[] {
                        paramMdl.getWml010sendMailPlanDateYear(),
                        paramMdl.getWml010sendMailPlanDateMonth(),
                        paramMdl.getWml010sendMailPlanDateDay(),
                        paramMdl.getWml010sendMailPlanDateHour(),
                        paramMdl.getWml010sendMailPlanDateMinute()
                });
            } else {
                int timeSendMinute = 0;

                boolean timsentImm
                    = paramMdl.getWml010sendMailPlanImm() == Wml010Const.SENDPLAN_IMM;
                WmlAdmConfDao admConfDao = new WmlAdmConfDao(con);
                WmlAdmConfModel wmlAdmConf = admConfDao.selectAdmData();

                if (wmlAdmConf.getWadPermitKbn() == GSConstWebmail.PERMIT_ADMIN) {
                    //管理者設定 時間差送信 = 有効 に設定されている
                    //かつ、画面上で"即時送信"が選択されていない場合
                    if (wmlAdmConf.getWadTimesent() == GSConstWebmail.WAC_TIMESENT_LATER
                    || (wmlAdmConf.getWadTimesent() == GSConstWebmail.WAC_TIMESENT_INPUT
                        && !timsentImm)) {
                        timeSendMinute = 5;
                    }
                } else {
                    WmlAccountDao wacDao = new WmlAccountDao(con);
                    WmlAccountModel wacMdl = wacDao.select(sendAccountSid);
                    if (wacMdl.getWacTimesent() == GSConstWebmail.WAC_TIMESENT_INPUT) {
                        if (!timsentImm) {
                            timeSendMinute = 5;
                        }
                    } else {
                        WmlBiz wmlBiz = new WmlBiz();
                        timeSendMinute
                            = wmlBiz.getTimeSentMinute(con, sendAccountSid);
                    }
                }

                if (timeSendMinute > 0) {
                    sendPlanDate
                        = gsMsg.getMessage("wml.242",
                                                new String[] {Integer.toString(timeSendMinute)});
                } else {
                    sendPlanDate = gsMsg.getMessage("wml.wml012.01");
                }
            }
            paramMdl.setWml012SendPlanDate(sendPlanDate);

            paramMdl.setWml012TempfileList(__getSendMailFileData(tempRootDir, reqMdl));

            //[宛先、添付ファイルの確認]を設定
            setAccountSendConf(con, paramMdl, sendAccountSid);
        }

        return paramMdl;
    }

    /**
     * <br>[機  能] 送信メール情報を保存する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param tempRootDir テンポラリルートディレクトリ
     * @throws IOToolsException 送信メール情報の読み込みに失敗
     */
    public void saveSendMailData(Wml012ParamModel paramMdl,
                                    RequestModel reqMdl, String tempRootDir)
        throws IOToolsException {
        File sendMailDataPath = __getSaveFilePath(reqMdl, tempRootDir);
        ObjectFile objFile
            = new ObjectFile(sendMailDataPath.getParent(), sendMailDataPath.getName());
        objFile.save(paramMdl);
    }

    /**
     * <br>[機  能] JSON形式で使用できる文字列へ変換する
     * <br>[解  説]
     * <br>[備  考]
     * @param text 文字列
     * @return 文字列
     */
    public static String escapeText(String text) {
        return _escapeText(text, true, true, true);
    }

    /**
     * <br>[機  能] 送信メール情報ファイルのファイルパスを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param tempRootDir テンポラリルートディレクトリ
     * @return ファイルパス
     * @throws IOToolsException 送信メール情報の読み込みに失敗
     */
    private File __getSaveFilePath(RequestModel reqMdl, String tempRootDir)
    throws IOToolsException {
        String sendTempPath = getSendTempDir(tempRootDir, reqMdl);
        sendTempPath = IOTools.setEndPathChar(sendTempPath);
        sendTempPath += "confirm/sendMailData";
        File sendMailDataPath = new File(sendTempPath);
        IOTools.isDirCheck(sendMailDataPath.getParent(), true);

        return sendMailDataPath;
    }


    /**
     * <br>[機  能] 送信メール情報の添付ファイル一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param tempRootDir テンポラリルートディレクトリ
     * @param reqMdl リクエスト情報
     * @return 添付ファイル情報
     * @throws IOToolsException 添付ファイル情報取得時に例外発生
     */
    private List<MailTempFileModel> __getSendMailFileData(String tempRootDir, RequestModel reqMdl)
    throws IOToolsException {

        List<MailTempFileModel> fileDataList = new ArrayList<MailTempFileModel>();

        String tempDir = getSendTempDir(tempRootDir, reqMdl);
        List<String> fileList = IOTools.getFileNames(tempDir);
        if (fileList != null && !fileList.isEmpty()) {

            Cmn110FileModel tempFileModel = null;
            CommonBiz cmnBiz = new CommonBiz();
            for (String fileName : fileList) {
                if (!fileName.endsWith(GSConstCommon.ENDSTR_OBJFILE)) {
                    continue;
                }

                //オブジェクトファイルを取得
                ObjectFile objFile = new ObjectFile(tempDir, fileName);
                Object fObj = objFile.load();
                if (fObj == null) {
                    continue;
                }

                tempFileModel = (Cmn110FileModel) fObj;
                MailTempFileModel sendFileData = new MailTempFileModel();
                sendFileData.setFileName(tempFileModel.getFileName());
                sendFileData.setFileSizeDsp(
                        cmnBiz.getByteSizeString(
                                new File(tempDir + tempFileModel.getSaveFileName()).length()));
                fileDataList.add(sendFileData);
            }
        }

        return fileDataList;
    }
    /**
     * <br>[機  能] 送信先一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param address 送信先メールアドレス(宛先 or CC or BCC)
     * @param domainMap ドメインとドメイン種別のMapping
     * @return 送信先一覧
     */
    private List<Wml012AddressModel> __createAddressList(String address,
                                                                    Map<String, String>domainMap) {
        List<Wml012AddressModel> addressList = new ArrayList<Wml012AddressModel>();
        if (StringUtil.isNullZeroString(address)) {
            return addressList;
        }
        InternetAddress[] sendAddressList = null;
        try {
            sendAddressList = WmlBiz.parseAddress(address);
        } catch (AddressException ae) {
        }
        if (sendAddressList == null) {
            return addressList;
        }
        for (InternetAddress iAdress : sendAddressList) {
            Wml012AddressModel addressData = new Wml012AddressModel();
            String sendAddress = WmlBiz.convertIAdress2String(iAdress);

            int atIndex = sendAddress.lastIndexOf("@");
            if (atIndex <= 0) {
                addressData.setAddress(sendAddress);
            } else {
                addressData.setUser(sendAddress.substring(0, atIndex));
                String domain = sendAddress.substring(atIndex + 1, sendAddress.length()).trim();

                if (domain.endsWith(">")) {
                    domain = domain.substring(0, domain.length() - 1);
                    addressData.setDomainEnd(">");
                }
                addressData.setDomain(domain);

                String type = domainMap.get(domain);
                if (type != null) {
                    addressData.setDomainType(type);
                } else {
                    domainType__++;
                    if (domainType__ > Wml012AddressModel.MAX_DOMAINTYPE) {
                        domainType__ = Wml012AddressModel.MIN_DOMAINTYPE;
                    }
                    addressData.setDomainType(Integer.toString(domainType__));
                    domainMap.put(domain, addressData.getDomainType());
                }
            }
            addressList.add(addressData);
        }

        return addressList;
    }
}
