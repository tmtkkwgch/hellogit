package jp.groupsession.v2.adr.adr070;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.adr.GSConstAddress;
import jp.groupsession.v2.adr.GSValidateAdr;
import jp.groupsession.v2.adr.GSValidateAdrCsv;
import jp.groupsession.v2.adr.adr010.Adr010ParamModel;
import jp.groupsession.v2.adr.biz.AddressBiz;
import jp.groupsession.v2.adr.dao.AdrAconfDao;
import jp.groupsession.v2.adr.dao.AdrCompanyDao;
import jp.groupsession.v2.adr.dao.AdrPositionDao;
import jp.groupsession.v2.adr.model.AdrAconfModel;
import jp.groupsession.v2.adr.util.AdrValidateUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn110.Cmn110FileModel;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.base.CmnTdfkDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] アドレス帳 アドレスインポート画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Adr070ParamModel extends Adr010ParamModel {

    /** 取込みファイル */
    private String[] adr070file__ = null;
    /** 会社 */
    private int adr070selectCompany__ = 0;
    /** 支店・営業所 */
    private int adr070selectCompanyBase__ = 0;
    /** 閲覧権限 */
    private int adr070permitView__ = GSConst.ADR_VIEWPERMIT_OWN;
    /** 閲覧グループ */
    private String[] adr070permitViewGroup__ = null;
    /** 閲覧ユーザ */
    private String[] adr070permitViewUser__ = null;
    /** 閲覧ユーザグループ */
    private int adr070permitViewUserGroup__ = 0;
    /** 編集権限 */
    private int adr070permitEdit__ = GSConstAddress.EDITPERMIT_OWN;
    /** 編集グループ */
    private String[] adr070permitEditGroup__ = null;
    /** 編集ユーザ */
    private String[] adr070permitEditUser__ = null;
    /** 編集ユーザグループ */
    private int adr070permitEditUserGroup__ = 0;

    /** 取り込みファイルコンボ */
    private List<LabelValueBean> adr070fileCombo__ = null;
    /** 会社コンボ */
    private List<LabelValueBean> adr070CompanyCombo__ = null;
    /** 支店・営業所コンボ */
    private List<LabelValueBean> adr070CompanyBaseCombo__ = null;
    /** 閲覧グループ(選択用) */
    private String[] adr070selectPermitViewGroup__  = null;
    /** 閲覧グループ(未選択 選択用) */
    private String[] adr070NoSelectPermitViewGroup__ = null;
    /** 閲覧グループコンボ */
    private List<LabelValueBean> selectPermitViewGroup__ = null;
    /** 閲覧グループ(未選択)コンボ */
    private List<LabelValueBean> noSelectPermitViewGroup__ = null;
    /** 閲覧ユーザ(選択用) */
    private String[] adr070selectPermitViewUser__  = null;
    /** 閲覧ユーザ(未選択 選択用) */
    private String[] adr070NoSelectPermitViewUser__ = null;
    /** 閲覧ユーザコンボ */
    private List<LabelValueBean> selectPermitViewUser__ = null;
    /** 閲覧ユーザ(未選択)コンボ */
    private List<LabelValueBean> noSelectPermitViewUser__ = null;
    /** 編集グループ(選択用) */
    private String[] adr070selectPermitEditGroup__  = null;
    /** 編集グループ(未選択 選択用) */
    private String[] adr070NoSelectPermitEditGroup__ = null;
    /** 編集グループコンボ */
    private List<LabelValueBean> selectPermitEditGroup__ = null;
    /** 編集グループ(未選択)コンボ */
    private List<LabelValueBean> noSelectPermitEditGroup__ = null;
    /** 編集ユーザ(選択用) */
    private String[] adr070selectPermitEditUser__  = null;
    /** 編集ユーザ(未選択 選択用) */
    private String[] adr070NoSelectPermitEditUser__ = null;
    /** 編集ユーザコンボ */
    private List<LabelValueBean> selectPermitEditUser__ = null;
    /** 編集ユーザ(未選択)コンボ */
    private List<LabelValueBean> noSelectPermitEditUser__ = null;

    /** 担当者(選択用) */
    private String[] adr070selectTantoList__ = null;
    /** 担当者(未選択 選択用) */
    private String[] adr070NoSelectTantoList__ = null;
    /** 担当者グループ */
    private int adr070tantoGroup__ = -2;
    /** 担当者コンボ */
    private List<LabelValueBean> selectTantoCombo__ = null;
    /** 担当者(未選択)コンボ */
    private List<LabelValueBean> noSelectTantoCombo__ = null;
    /** 担当者 */
    private String[] adr070tantoList__ = null;

    /** 画面モード 0:通常, 1:会社同時登録 **/
    private int adr070cmdMode__ = 1;
    /** 既存のユーザ情報更新フラグ */
    private int adr070updateFlg__ = 0;


    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param tempDir テンポラリディレクトリ
     * @param reqMdl RequestModel
     * @param buMdl ユーザ情報Model
     * @return エラー
     * @throws Exception 実行時例外
     */
    public ActionErrors validateCheck(
            Connection con, String tempDir, RequestModel reqMdl, BaseUserModel buMdl)
                    throws Exception {
        ActionErrors errors = new ActionErrors();
        GsMessage gsMsg = new GsMessage(reqMdl);
        String eprefix = "inputFile.";

        AddressBiz addressBiz = new AddressBiz(reqMdl);
        List<Cmn110FileModel> fileDataList = addressBiz.getFileData(tempDir);

        if (fileDataList == null || fileDataList.isEmpty()) {
            ActionMessage msg =
                new ActionMessage("error.select.required.text",
                                   gsMsg.getMessage("cmn.capture.file"));
            StrutsUtil.addMessage(errors, msg, eprefix + "error.select.required.text");
        } else {

            boolean csvError = false;
            String fileName = null;
            //複数選択エラー
            if (fileDataList.size() > 2) {
                ActionMessage msg =
                    new ActionMessage("error.input.notfound.file",
                            gsMsg.getMessage("cmn.capture.file"));
                StrutsUtil.addMessage(errors, msg, eprefix + "error.input.notfound.file");
                csvError = true;
            } else {
                //拡張子チェック
                fileName = fileDataList.get(0).getFileName();
                String strExt = StringUtil.getExtension(fileName);
                if (strExt == null || !strExt.toUpperCase().equals(".CSV")) {
                    ActionMessage msg =
                        new ActionMessage("error.select.required.text",
                                gsMsg.getMessage("cmn.csv.file.format"));
                    StrutsUtil.addMessage(errors, msg, eprefix + "error.select.required.text");
                    csvError = true;
                }
            }

            if (!csvError) {
                String fullPath = tempDir + fileDataList.get(0).getSaveFileName();
                AddressCsvReader csvReader = new AddressCsvReader(getAdr070cmdMode());
                csvReader.readCsvFile(fullPath);
                List<AddressCsvModel> addressList = csvReader.getAddressList();

                //氏名 姓
                String nameSei = gsMsg.getMessage("cmn.name") + " "
                                 + gsMsg.getMessage("cmn.lastname");
                //氏名 名
                String nameMei = gsMsg.getMessage("cmn.name") + " "
                                 + gsMsg.getMessage("cmn.name3");
                //氏名カナ 姓
                String nameSeiKn = gsMsg.getMessage("cmn.name.kana") + " "
                                   + gsMsg.getMessage("cmn.lastname");
                //氏名カナ 名
                String nameMeiKn = gsMsg.getMessage("cmn.name.kana") + " "
                                   + gsMsg.getMessage("cmn.name3");
                //所属
                String syozoku = gsMsg.getMessage("cmn.affiliation");
                //役職
                String yakusyoku = gsMsg.getMessage("cmn.post");
                //メールアドレス
                String mailAddress = gsMsg.getMessage("cmn.mailaddress");
                //コメント
                String msgComment = gsMsg.getMessage("cmn.comment");
                //住所
                String msgJyusyo = gsMsg.getMessage("cmn.address");
                //電話番号
                String msgTelNum = gsMsg.getMessage("cmn.tel");
                //内線
                String msgNai = gsMsg.getMessage("address.58");
                //備考
                String msgBiko = gsMsg.getMessage("cmn.memo");

                AdrCompanyDao companyDao = new AdrCompanyDao(con);

                for (AddressCsvModel addressData : addressList) {
                    String rowNum = String.valueOf(addressData.getRowNum());
                    String rowStr = gsMsg.getMessage("cmn.line2", new String[] {rowNum});

                    //氏名 姓
                    AdrValidateUtil.validateTextField(errors, addressData.getNameSei(),
                                                    "nameSei" + rowNum,
                                                    rowStr + nameSei,
                                                    GSConstAddress.MAX_LENGTH_NAME_SEI, true);
                    //氏名 名
                    AdrValidateUtil.validateTextField(errors, addressData.getNameMei(),
                                                    "nameMei" + rowNum,
                                                    rowStr + nameMei,
                                                    GSConstAddress.MAX_LENGTH_NAME_MEI, true);
                    //氏名カナ 姓
                    AdrValidateUtil.validateTextFieldKana(errors, addressData.getNameSeiKn(),
                                                    "nameSeiKn" + rowNum,
                                                    rowStr + nameSeiKn,
                                                    GSConstAddress.MAX_LENGTH_NAME_SEI_KN, true);
                    //氏名カナ 名
                    AdrValidateUtil.validateTextFieldKana(errors, addressData.getNameMeiKn(),
                                                    "nameMeiKn" + rowNum,
                                                    rowStr + nameMeiKn,
                                                    GSConstAddress.MAX_LENGTH_NAME_MEI_KN, true);
                    //所属
                    AdrValidateUtil.validateTextField(errors, addressData.getSyozoku(),
                                                    "syozoku" + rowNum,
                                                    rowStr + syozoku,
                                                    GSConstAddress.MAX_LENGTH_SYOZOKU, false);
                    //役職
                    int errorCount = errors.size();
                    AdrValidateUtil.validateTextField(errors, addressData.getYakusyoku(),
                                                    "yakusyoku" + rowNum,
                                                    rowStr + yakusyoku,
                                                    GSConstAddress.MAX_LENGTH_YAKUSYOKU, false);
                    if (errorCount == errors.size()) {
                        //役職の入力チェックが通った場合、役職の存在チェックと編集権限のチェック
                        __validatePositionEditPower(errors, addressData.getYakusyoku(),
                                "yakusyoku" + rowNum,
                                rowStr + yakusyoku,
                                gsMsg, buMdl, con);
                    }

                    //メールアドレス１
                    AdrValidateUtil.validateTextField(errors, addressData.getMail1(),
                                                    "mail1" + rowNum,
                                                    rowStr + mailAddress + "１",
                                                    GSConstAddress.MAX_LENGTH_MAIL, false);
                    //メールアドレス１コメント
                    AdrValidateUtil.validateTextField(errors, addressData.getMail1Comment(),
                                                    "mail1Comment" + rowNum,
                                                    rowStr + mailAddress + "１ " + msgComment ,
                                                    GSConstAddress.MAX_LENGTH_MAIL_COMMENT, false);
                    //メールアドレス２
                    AdrValidateUtil.validateTextField(errors, addressData.getMail2(),
                                                    "mail2",
                                                    rowStr + mailAddress + "２",
                                                    GSConstAddress.MAX_LENGTH_MAIL, false);
                    //メールアドレス２コメント
                    AdrValidateUtil.validateTextField(errors, addressData.getMail2Comment(),
                                                    "mail2Comment" + rowNum,
                                                    rowStr + mailAddress + "２ " + msgComment ,
                                                    GSConstAddress.MAX_LENGTH_MAIL_COMMENT, false);
                    //メールアドレス３
                    AdrValidateUtil.validateTextField(errors, addressData.getMail3(),
                                                    "mail3" + rowNum,
                                                    rowStr + mailAddress + "３",
                                                    GSConstAddress.MAX_LENGTH_MAIL, false);
                    //メールアドレス３コメント
                    AdrValidateUtil.validateTextField(errors, addressData.getMail3Comment(),
                                                    "mail3Comment" + rowNum,
                                                    rowStr + mailAddress + "３ " + msgComment ,
                                                    GSConstAddress.MAX_LENGTH_MAIL_COMMENT, false);
                    GSValidateAdr gsValidate = new GSValidateAdr(reqMdl);
                    GSValidateAdrCsv gsValidateCvs = new GSValidateAdrCsv(reqMdl);
                    //郵便番号
                    gsValidate.validateCsvPostNum(errors, addressData.getPostNo(),
                                                        Integer.parseInt(rowNum), reqMdl);
                    //都道府県
                    String tdfk = addressData.getTdfk();
                    gsValidateCvs.validateCsvTdfkName(errors, tdfk,
                                                    addressData.getRowNum(), con, "");
                    //住所１
                    AdrValidateUtil.validateTextField(errors, addressData.getAddress1(),
                                                    "address1" + rowNum,
                                                    rowStr + msgJyusyo + "１",
                                                    GSConstAddress.MAX_LENGTH_ADDRESS, false);
                    //住所２
                    AdrValidateUtil.validateTextField(errors, addressData.getAddress2(),
                                                    "address2" + rowNum,
                                                    rowStr + msgJyusyo + "２",
                                                    GSConstAddress.MAX_LENGTH_ADDRESS, false);

                    //電話番号１
                    AdrValidateUtil.validateTel(errors, addressData.getTel1(),
                                                "tel1" + rowNum,
                                                rowStr + msgTelNum + "１",
                                                GSConstAddress.MAX_LENGTH_TEL, false);
                    //電話番号１ 内線
                    AdrValidateUtil.validateTextField(errors, addressData.getNai1(),
                                                "tel1Nai" + rowNum,
                                                rowStr + msgTelNum + "１ " + msgNai,
                                                GSConstAddress.MAX_LENGTH_TEL_NAI, false);
                    //電話番号１ コメント
                    AdrValidateUtil.validateTextField(errors, addressData.getTel1Comment(),
                                                "tel1Comment" + rowNum,
                                                rowStr + msgTelNum + "１ " + msgComment,
                                                GSConstAddress.MAX_LENGTH_TEL_COMMENT, false);
                    //電話番号２
                    AdrValidateUtil.validateTel(errors, addressData.getTel2(),
                                                "tel2" + rowNum,
                                                rowStr + msgTelNum + "２",
                                                GSConstAddress.MAX_LENGTH_TEL, false);
                    //電話番号２ 内線
                    AdrValidateUtil.validateTextField(errors, addressData.getNai2(),
                                                "tel2Nai" + rowNum,
                                                rowStr + msgTelNum + "２ " + msgNai,
                                                GSConstAddress.MAX_LENGTH_TEL_NAI, false);
                    //電話番号２ コメント
                    AdrValidateUtil.validateTextField(errors, addressData.getTel2Comment(),
                                                "tel2Comment" + rowNum,
                                                rowStr + msgTelNum + "２ " + msgComment,
                                                GSConstAddress.MAX_LENGTH_TEL_COMMENT, false);
                    //電話番号３
                    AdrValidateUtil.validateTel(errors, addressData.getTel3(),
                                                "tel3" + rowNum,
                                                rowStr + msgTelNum + "３",
                                                GSConstAddress.MAX_LENGTH_TEL, false);
                    //電話番号３ 内線
                    AdrValidateUtil.validateTextField(errors, addressData.getNai3(),
                                                "tel3Nai" + rowNum,
                                                rowStr + msgTelNum + "３ " + msgNai,
                                                GSConstAddress.MAX_LENGTH_TEL_NAI, false);
                    //電話番号３ コメント
                    AdrValidateUtil.validateTextField(errors, addressData.getTel3Comment(),
                                                "tel3Comment" + rowNum,
                                                rowStr + msgTelNum + "３ " + msgComment,
                                                GSConstAddress.MAX_LENGTH_TEL_COMMENT, false);

                    //ＦＡＸ１
                    AdrValidateUtil.validateTel(errors, addressData.getFax1(),
                                                "fax1" + rowNum,
                                                rowStr + GSConstAddress.TEXT_FAX + "１",
                                                GSConstAddress.MAX_LENGTH_FAX, false);
                    //ＦＡＸ１コメント
                    AdrValidateUtil.validateTextField(errors, addressData.getFax1Comment(),
                                            "fax1Comment" + rowNum,
                                            rowStr + GSConstAddress.TEXT_FAX + "１ " + msgComment,
                                            GSConstAddress.MAX_LENGTH_FAX_COMMENT, false);
                    //ＦＡＸ２
                    AdrValidateUtil.validateTel(errors, addressData.getFax2(),
                                                 "fax1" + rowNum,
                                                rowStr + GSConstAddress.TEXT_FAX + "２",
                                                GSConstAddress.MAX_LENGTH_FAX, false);
                    //ＦＡＸ２コメント
                    AdrValidateUtil.validateTextField(errors, addressData.getFax2Comment(),
                                            "fax2Comment" + rowNum,
                                            rowStr + GSConstAddress.TEXT_FAX + "２ " + msgComment,
                                            GSConstAddress.MAX_LENGTH_FAX_COMMENT, false);
                    //ＦＡＸ３
                    AdrValidateUtil.validateTel(errors, addressData.getFax3(),
                                                 "fax3" + rowNum,
                                                rowStr + GSConstAddress.TEXT_FAX + "３",
                                                GSConstAddress.MAX_LENGTH_FAX, false);
                    //ＦＡＸ３コメント
                    AdrValidateUtil.validateTextField(errors, addressData.getFax3Comment(),
                                            "fax3Comment" + rowNum,
                                            rowStr + GSConstAddress.TEXT_FAX + "３ " + msgComment,
                                            GSConstAddress.MAX_LENGTH_FAX_COMMENT, false);

                    //備考
                    AdrValidateUtil.validateTextAreaField(errors, addressData.getBiko(),
                                                    "biko" + rowNum,
                                                    rowStr + msgBiko,
                                                    GSConstAddress.MAX_LENGTH_ADR_BIKO, false);

                    if (getAdr070cmdMode() != 1) {
                        //会社指定の場合、会社情報をチェックしない。
                        continue;
                    }


                    //以下会社情報チェック

                    //企業コード
                    String companyCode = addressData.getCompanyCode();
                    boolean comCodeError = AdrValidateUtil.validateTextField(errors, companyCode,
                                                    "companyCode" + rowNum,
                                                    rowStr + gsMsg.getMessage("address.7"),
                                                    GSConstAddress.MAX_LENGTH_COMPANY_CODE,
                                                    false);

                    if (!comCodeError) {
                        continue;
                    }
                    if (StringUtil.isNullZeroString(companyCode)) {
                        continue;
                    }

                    boolean exist = companyDao.existCompany(0, companyCode);

                    if (exist && getAdr070updateFlg() == 0) {
                        //会社情報の存在チェック
                        ActionMessage msg = new ActionMessage("error.input.timecard.exist",
                                rowStr + gsMsg.getMessage("address.118"));
                        StrutsUtil.addMessage(errors, msg,
                                "companyExist" + rowNum + "error.input.timecard.exist");
                        continue;
                    }

                    //会社名
                    AdrValidateUtil.validateTextField(errors, addressData.getCompanyName(),
                                                "companyName" + rowNum,
                                                rowStr + gsMsg.getMessage("cmn.company.name"),
                                                GSConstAddress.MAX_LENGTH_COMPANY_NAME,
                                                true);
                    //会社名(カナ)
                    AdrValidateUtil.validateTextFieldKana(errors, addressData.getCompanyNameKn(),
                                                "companyNameKn" + rowNum,
                                                rowStr
                                                + gsMsg.getMessage("cmn.company.name")
                                                + "(" + gsMsg.getMessage("cmn.kana") + ")",
                                                GSConstAddress.MAX_LENGTH_COMPANY_NAME_KN,
                                                true);
                    //URL
                    AdrValidateUtil.validateTextField(errors, addressData.getCompanyUrl(),
                                                "companyUrl" + rowNum,
                                                rowStr + "URL",
                                                GSConst.MAX_LENGTH_URL,
                                                false);
                    //備考(会社情報)
                    AdrValidateUtil.validateTextAreaField(errors, addressData.getCompanyBiko(),
                                                "companyBiko" + rowNum,
                                                rowStr + gsMsg.getMessage("cmn.memo")
                                                + "(" + gsMsg.getMessage("address.118") + ")",
                                                GSConstAddress.MAX_LENGTH_ADR_BIKO,
                                                false);
                    //企業拠点種別
                    boolean indispensabilityFlg = false;
                    CmnTdfkDao tdfkDao = new CmnTdfkDao(con);
                    Adr070Biz adr070Biz = new Adr070Biz(reqMdl);
                    String companyBaseType
                        = NullDefault.getString(addressData.getCompanyBaseType(), "");
                    if (companyBaseType.length() > 0) {
                        if (!companyBaseType.equals(GSConstAddress.ABATYPE_HEADOFFICE_STR)
                        && !companyBaseType.equals(GSConstAddress.ABATYPE_BRANCH_STR)
                        && !companyBaseType.equals(GSConstAddress.ABATYPE_BUSINESSOFFICE_STR)) {
                            String msgKey = "error.input.notvalidate.data";

                            ActionMessage msg = new ActionMessage(
                                    msgKey, rowStr + gsMsg.getMessage("address.src.29"));

                            StrutsUtil.addMessage(
                                    errors, msg,
                                    "companyBaseType" + rowNum + "." + msgKey);
                        } else {
                            indispensabilityFlg = true;
                        }
                    } else if (!StringUtil.isNullZeroString(addressData.getCompanyBasePostNo())
                            || adr070Biz.getTdfkSid(tdfkDao, addressData.getCompanyBaseTdfk()) > 0
                            || !StringUtil.isNullZeroString(addressData.getCompanyBaseAddress1())
                            || !StringUtil.isNullZeroString(addressData.getCompanyBaseAddress2())
                            || !StringUtil.isNullZeroString(addressData.getCompanyBaseBiko())) {

                        indispensabilityFlg = true;
                    }

                    //企業拠点名
                    AdrValidateUtil.validateTextField(errors, addressData.getCompanyBaseName(),
                                                  "companyBaseName" + rowNum,
                                                  rowStr + gsMsg.getMessage("address.src.30"),
                                                  GSConstAddress.MAX_LENGTH_COBASE_NAME,
                                                  indispensabilityFlg);
                    //郵便番号(会社情報)
                    String companyBasePostNo = addressData.getCompanyBasePostNo();
                    gsValidate.validateCsvPostNum(errors, companyBasePostNo,
                                                  addressData.getRowNum(), reqMdl,
                                                  "(" + gsMsg.getMessage("address.118") + ")");

                    //拠点都道府県
                    String companyBaseTdfk = addressData.getCompanyBaseTdfk();
                    gsValidateCvs.validateCsvTdfkName(errors, companyBaseTdfk,
                                                  addressData.getRowNum(), con,
                                                  gsMsg.getMessage("address.10"));

                    //住所１(会社情報)
                    AdrValidateUtil.validateTextField(errors, addressData.getCompanyBaseAddress1(),
                                                 "companyBaseAddress1" + rowNum,
                                                 rowStr + gsMsg.getMessage("cmn.address1")
                                                 + "(" + gsMsg.getMessage("address.118") + ")",
                                                 GSConstAddress.MAX_LENGTH_ADDRESS,
                                                 false);
                    //住所２(会社情報)
                    AdrValidateUtil.validateTextField(errors, addressData.getCompanyBaseAddress2(),
                                                 "companyBaseAddress2" + rowNum,
                                                 rowStr + gsMsg.getMessage("cmn.address2")
                                                 + "(" + gsMsg.getMessage("address.118") + ")",
                                                 GSConstAddress.MAX_LENGTH_ADDRESS,
                                                 false);
                    //企業拠点備考
                    AdrValidateUtil.validateTextAreaField(errors, addressData.getCompanyBaseBiko(),
                                                  "companyBaseBiko" + rowNum,
                                                  rowStr + gsMsg.getMessage("address.src.31"),
                                                  GSConstAddress.MAX_LENGTH_ADR_BIKO,
                                                  false);

                }
            }
        }

        //担当者
        String msgTanto = gsMsg.getMessage("cmn.staff");
        //閲覧権限担当者のみ
        if (adr070permitView__ == GSConst.ADR_VIEWPERMIT_OWN) {
            if (adr070permitEdit__ == GSConstAddress.EDITPERMIT_OWN
                    && (adr070tantoList__ == null || adr070tantoList__.length == 0)) {
                String msgKey = "error.select.required.text";
                ActionMessage msg = new ActionMessage(msgKey, msgTanto);
                StrutsUtil.addMessage(
                        errors, msg, "adr070tantoListOwn." + msgKey);
            }
        }

        //閲覧権限設定無し
        if (adr070permitView__ == GSConst.ADR_VIEWPERMIT_NORESTRICTION) {
            if (adr070permitEdit__ == GSConstAddress.EDITPERMIT_OWN
                    && (adr070tantoList__ == null || adr070tantoList__.length == 0)) {
                String msgKey = "error.select.required.text";
                ActionMessage msg = new ActionMessage(msgKey, msgTanto);
                StrutsUtil.addMessage(
                        errors, msg, "adr070tantoListNo." + msgKey);
            }
        }

        //閲覧権限グループ
        if (adr070permitView__ == GSConst.ADR_VIEWPERMIT_GROUP) {
            if (adr070permitViewGroup__ == null || adr070permitViewGroup__.length == 0) {
                String msgKey = "error.select.required.text";
                ActionMessage msg = new ActionMessage(msgKey, gsMsg.getMessage("address.66"));
                StrutsUtil.addMessage(
                        errors, msg, "adr070permitViewGroup." + msgKey);
            }
        }

        //閲覧権限ユーザ
        if (adr070permitView__ == GSConst.ADR_VIEWPERMIT_USER) {
            if (adr070permitViewUser__ == null || adr070permitViewUser__.length == 0) {
                String msgKey = "error.select.required.text";
                ActionMessage msg = new ActionMessage(msgKey, gsMsg.getMessage("address.68"));
                StrutsUtil.addMessage(
                        errors, msg, "adr070permitViewUser." + msgKey);
            }
        }

        //編集権限グループ
        if (adr070permitView__ == GSConst.ADR_VIEWPERMIT_GROUP
        || adr070permitEdit__ == GSConstAddress.EDITPERMIT_GROUP) {
            if (adr070permitEditGroup__ == null || adr070permitEditGroup__.length == 0) {
                String msgKey = "error.select.required.text";
                ActionMessage msg = new ActionMessage(msgKey,
                        gsMsg.getMessage("cmn.editgroup"));
                StrutsUtil.addMessage(
                        errors, msg, "adr070permitEditGroup." + msgKey);
            }
        }

        //編集権限ユーザ
        if (adr070permitView__ == GSConst.ADR_VIEWPERMIT_USER
        || adr070permitEdit__ == GSConstAddress.EDITPERMIT_USER) {
            if (adr070permitEditUser__ == null || adr070permitEditUser__.length == 0) {
                String msgKey = "error.select.required.text";
                ActionMessage msg = new ActionMessage(msgKey,
                        gsMsg.getMessage("cmn.edituser"));
                StrutsUtil.addMessage(
                        errors, msg, "adr070permitEditUser." + msgKey);
            }
        }

        return errors;
    }

    /**
     * <br>[機  能] 役職編集権限を検証する
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param positionName 役職名
     * @param gsMsg GsMessage
     * @param paramName パラメータ名 (xxxDate, wtbxxxText 等)
     * @param paramNameJpn パラメータの日本語名 (日付, 時刻 等)
     * @param buMdl ユーザ情報Model
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     * @return errors ActionErrors
     */
    private ActionErrors __validatePositionEditPower(
            ActionErrors errors, String positionName,
            String paramName, String paramNameJpn,
            GsMessage gsMsg, BaseUserModel buMdl, Connection con)
                    throws SQLException {
        AdrAconfDao aconfDao = new AdrAconfDao(con);
        AdrAconfModel aconfMdl = aconfDao.selectAconf();
        int yksEdit = 0;
        if (aconfMdl != null) {
            yksEdit = aconfMdl.getAacYksEdit();
        }
        CommonBiz cmnBiz = new CommonBiz();
        AdrPositionDao positionDao = new AdrPositionDao(con);

        if (yksEdit == 1
                && !cmnBiz.isPluginAdmin(con, buMdl, GSConst.PLUGINID_ADDRESS)
                && !StringUtil.isNullZeroString(positionName)
                && !positionDao.isExistPositionName(positionName, 0)) {
            //「管理者のみ役職を編集可能」状態で一般ユーザでログインし、未登録の役職名をインポートした場合
            String msgKey = "error.not.exist.userid";
            ActionMessage msg = new ActionMessage(msgKey, paramNameJpn);
            StrutsUtil.addMessage(errors, msg, paramName + "." + msgKey);
        }

        return errors;
    }

    /**
     * <p>adr070NoSelectPermitEditGroup を取得します。
     * @return adr070NoSelectPermitEditGroup
     */
    public String[] getAdr070NoSelectPermitEditGroup() {
        return adr070NoSelectPermitEditGroup__;
    }

    /**
     * <p>adr070NoSelectPermitEditGroup をセットします。
     * @param adr070NoSelectPermitEditGroup adr070NoSelectPermitEditGroup
     */
    public void setAdr070NoSelectPermitEditGroup(
            String[] adr070NoSelectPermitEditGroup) {
        adr070NoSelectPermitEditGroup__ = adr070NoSelectPermitEditGroup;
    }

    /**
     * <p>adr070NoSelectPermitEditUser を取得します。
     * @return adr070NoSelectPermitEditUser
     */
    public String[] getAdr070NoSelectPermitEditUser() {
        return adr070NoSelectPermitEditUser__;
    }

    /**
     * <p>adr070NoSelectPermitEditUser をセットします。
     * @param adr070NoSelectPermitEditUser adr070NoSelectPermitEditUser
     */
    public void setAdr070NoSelectPermitEditUser(
            String[] adr070NoSelectPermitEditUser) {
        adr070NoSelectPermitEditUser__ = adr070NoSelectPermitEditUser;
    }

    /**
     * <p>adr070NoSelectPermitViewGroup を取得します。
     * @return adr070NoSelectPermitViewGroup
     */
    public String[] getAdr070NoSelectPermitViewGroup() {
        return adr070NoSelectPermitViewGroup__;
    }

    /**
     * <p>adr070NoSelectPermitViewGroup をセットします。
     * @param adr070NoSelectPermitViewGroup adr070NoSelectPermitViewGroup
     */
    public void setAdr070NoSelectPermitViewGroup(
            String[] adr070NoSelectPermitViewGroup) {
        adr070NoSelectPermitViewGroup__ = adr070NoSelectPermitViewGroup;
    }

    /**
     * <p>adr070NoSelectPermitViewUser を取得します。
     * @return adr070NoSelectPermitViewUser
     */
    public String[] getAdr070NoSelectPermitViewUser() {
        return adr070NoSelectPermitViewUser__;
    }

    /**
     * <p>adr070NoSelectPermitViewUser をセットします。
     * @param adr070NoSelectPermitViewUser adr070NoSelectPermitViewUser
     */
    public void setAdr070NoSelectPermitViewUser(
            String[] adr070NoSelectPermitViewUser) {
        adr070NoSelectPermitViewUser__ = adr070NoSelectPermitViewUser;
    }

    /**
     * <p>adr070permitEdit を取得します。
     * @return adr070permitEdit
     */
    public int getAdr070permitEdit() {
        return adr070permitEdit__;
    }

    /**
     * <p>adr070permitEdit をセットします。
     * @param adr070permitEdit adr070permitEdit
     */
    public void setAdr070permitEdit(int adr070permitEdit) {
        adr070permitEdit__ = adr070permitEdit;
    }

    /**
     * <p>adr070permitEditGroup を取得します。
     * @return adr070permitEditGroup
     */
    public String[] getAdr070permitEditGroup() {
        return adr070permitEditGroup__;
    }

    /**
     * <p>adr070permitEditGroup をセットします。
     * @param adr070permitEditGroup adr070permitEditGroup
     */
    public void setAdr070permitEditGroup(String[] adr070permitEditGroup) {
        adr070permitEditGroup__ = adr070permitEditGroup;
    }

    /**
     * <p>adr070permitEditUser を取得します。
     * @return adr070permitEditUser
     */
    public String[] getAdr070permitEditUser() {
        return adr070permitEditUser__;
    }

    /**
     * <p>adr070permitEditUser をセットします。
     * @param adr070permitEditUser adr070permitEditUser
     */
    public void setAdr070permitEditUser(String[] adr070permitEditUser) {
        adr070permitEditUser__ = adr070permitEditUser;
    }

    /**
     * <p>adr070permitEditUserGroup を取得します。
     * @return adr070permitEditUserGroup
     */
    public int getAdr070permitEditUserGroup() {
        return adr070permitEditUserGroup__;
    }

    /**
     * <p>adr070permitEditUserGroup をセットします。
     * @param adr070permitEditUserGroup adr070permitEditUserGroup
     */
    public void setAdr070permitEditUserGroup(int adr070permitEditUserGroup) {
        adr070permitEditUserGroup__ = adr070permitEditUserGroup;
    }

    /**
     * <p>adr070permitView を取得します。
     * @return adr070permitView
     */
    public int getAdr070permitView() {
        return adr070permitView__;
    }

    /**
     * <p>adr070permitView をセットします。
     * @param adr070permitView adr070permitView
     */
    public void setAdr070permitView(int adr070permitView) {
        adr070permitView__ = adr070permitView;
    }

    /**
     * <p>adr070permitViewGroup を取得します。
     * @return adr070permitViewGroup
     */
    public String[] getAdr070permitViewGroup() {
        return adr070permitViewGroup__;
    }

    /**
     * <p>adr070permitViewGroup をセットします。
     * @param adr070permitViewGroup adr070permitViewGroup
     */
    public void setAdr070permitViewGroup(String[] adr070permitViewGroup) {
        adr070permitViewGroup__ = adr070permitViewGroup;
    }

    /**
     * <p>adr070permitViewUser を取得します。
     * @return adr070permitViewUser
     */
    public String[] getAdr070permitViewUser() {
        return adr070permitViewUser__;
    }

    /**
     * <p>adr070permitViewUser をセットします。
     * @param adr070permitViewUser adr070permitViewUser
     */
    public void setAdr070permitViewUser(String[] adr070permitViewUser) {
        adr070permitViewUser__ = adr070permitViewUser;
    }

    /**
     * <p>adr070permitViewUserGroup を取得します。
     * @return adr070permitViewUserGroup
     */
    public int getAdr070permitViewUserGroup() {
        return adr070permitViewUserGroup__;
    }

    /**
     * <p>adr070permitViewUserGroup をセットします。
     * @param adr070permitViewUserGroup adr070permitViewUserGroup
     */
    public void setAdr070permitViewUserGroup(int adr070permitViewUserGroup) {
        adr070permitViewUserGroup__ = adr070permitViewUserGroup;
    }

    /**
     * <p>adr070selectCompany を取得します。
     * @return adr070selectCompany
     */
    public int getAdr070selectCompany() {
        return adr070selectCompany__;
    }

    /**
     * <p>adr070selectCompany をセットします。
     * @param adr070selectCompany adr070selectCompany
     */
    public void setAdr070selectCompany(int adr070selectCompany) {
        adr070selectCompany__ = adr070selectCompany;
    }

    /**
     * <p>adr070selectCompanyBase を取得します。
     * @return adr070selectCompanyBase
     */
    public int getAdr070selectCompanyBase() {
        return adr070selectCompanyBase__;
    }

    /**
     * <p>adr070selectCompanyBase をセットします。
     * @param adr070selectCompanyBase adr070selectCompanyBase
     */
    public void setAdr070selectCompanyBase(int adr070selectCompanyBase) {
        adr070selectCompanyBase__ = adr070selectCompanyBase;
    }

    /**
     * <p>adr070selectPermitEditGroup を取得します。
     * @return adr070selectPermitEditGroup
     */
    public String[] getAdr070selectPermitEditGroup() {
        return adr070selectPermitEditGroup__;
    }

    /**
     * <p>adr070selectPermitEditGroup をセットします。
     * @param adr070selectPermitEditGroup adr070selectPermitEditGroup
     */
    public void setAdr070selectPermitEditGroup(String[] adr070selectPermitEditGroup) {
        adr070selectPermitEditGroup__ = adr070selectPermitEditGroup;
    }

    /**
     * <p>adr070selectPermitEditUser を取得します。
     * @return adr070selectPermitEditUser
     */
    public String[] getAdr070selectPermitEditUser() {
        return adr070selectPermitEditUser__;
    }

    /**
     * <p>adr070selectPermitEditUser をセットします。
     * @param adr070selectPermitEditUser adr070selectPermitEditUser
     */
    public void setAdr070selectPermitEditUser(String[] adr070selectPermitEditUser) {
        adr070selectPermitEditUser__ = adr070selectPermitEditUser;
    }

    /**
     * <p>adr070selectPermitViewGroup を取得します。
     * @return adr070selectPermitViewGroup
     */
    public String[] getAdr070selectPermitViewGroup() {
        return adr070selectPermitViewGroup__;
    }

    /**
     * <p>adr070selectPermitViewGroup をセットします。
     * @param adr070selectPermitViewGroup adr070selectPermitViewGroup
     */
    public void setAdr070selectPermitViewGroup(String[] adr070selectPermitViewGroup) {
        adr070selectPermitViewGroup__ = adr070selectPermitViewGroup;
    }

    /**
     * <p>adr070selectPermitViewUser を取得します。
     * @return adr070selectPermitViewUser
     */
    public String[] getAdr070selectPermitViewUser() {
        return adr070selectPermitViewUser__;
    }

    /**
     * <p>adr070selectPermitViewUser をセットします。
     * @param adr070selectPermitViewUser adr070selectPermitViewUser
     */
    public void setAdr070selectPermitViewUser(String[] adr070selectPermitViewUser) {
        adr070selectPermitViewUser__ = adr070selectPermitViewUser;
    }

    /**
     * <p>noSelectPermitEditGroup を取得します。
     * @return noSelectPermitEditGroup
     */
    public List<LabelValueBean> getNoSelectPermitEditGroup() {
        return noSelectPermitEditGroup__;
    }

    /**
     * <p>noSelectPermitEditGroup をセットします。
     * @param noSelectPermitEditGroup noSelectPermitEditGroup
     */
    public void setNoSelectPermitEditGroup(
            List<LabelValueBean> noSelectPermitEditGroup) {
        noSelectPermitEditGroup__ = noSelectPermitEditGroup;
    }

    /**
     * <p>noSelectPermitEditUser を取得します。
     * @return noSelectPermitEditUser
     */
    public List<LabelValueBean> getNoSelectPermitEditUser() {
        return noSelectPermitEditUser__;
    }

    /**
     * <p>noSelectPermitEditUser をセットします。
     * @param noSelectPermitEditUser noSelectPermitEditUser
     */
    public void setNoSelectPermitEditUser(
            List<LabelValueBean> noSelectPermitEditUser) {
        noSelectPermitEditUser__ = noSelectPermitEditUser;
    }

    /**
     * <p>noSelectPermitViewGroup を取得します。
     * @return noSelectPermitViewGroup
     */
    public List<LabelValueBean> getNoSelectPermitViewGroup() {
        return noSelectPermitViewGroup__;
    }

    /**
     * <p>noSelectPermitViewGroup をセットします。
     * @param noSelectPermitViewGroup noSelectPermitViewGroup
     */
    public void setNoSelectPermitViewGroup(
            List<LabelValueBean> noSelectPermitViewGroup) {
        noSelectPermitViewGroup__ = noSelectPermitViewGroup;
    }

    /**
     * <p>noSelectPermitViewUser を取得します。
     * @return noSelectPermitViewUser
     */
    public List<LabelValueBean> getNoSelectPermitViewUser() {
        return noSelectPermitViewUser__;
    }

    /**
     * <p>noSelectPermitViewUser をセットします。
     * @param noSelectPermitViewUser noSelectPermitViewUser
     */
    public void setNoSelectPermitViewUser(
            List<LabelValueBean> noSelectPermitViewUser) {
        noSelectPermitViewUser__ = noSelectPermitViewUser;
    }

    /**
     * <p>selectPermitEditGroup を取得します。
     * @return selectPermitEditGroup
     */
    public List<LabelValueBean> getSelectPermitEditGroup() {
        return selectPermitEditGroup__;
    }

    /**
     * <p>selectPermitEditGroup をセットします。
     * @param selectPermitEditGroup selectPermitEditGroup
     */
    public void setSelectPermitEditGroup(List<LabelValueBean> selectPermitEditGroup) {
        selectPermitEditGroup__ = selectPermitEditGroup;
    }

    /**
     * <p>selectPermitEditUser を取得します。
     * @return selectPermitEditUser
     */
    public List<LabelValueBean> getSelectPermitEditUser() {
        return selectPermitEditUser__;
    }

    /**
     * <p>selectPermitEditUser をセットします。
     * @param selectPermitEditUser selectPermitEditUser
     */
    public void setSelectPermitEditUser(List<LabelValueBean> selectPermitEditUser) {
        selectPermitEditUser__ = selectPermitEditUser;
    }

    /**
     * <p>selectPermitViewGroup を取得します。
     * @return selectPermitViewGroup
     */
    public List<LabelValueBean> getSelectPermitViewGroup() {
        return selectPermitViewGroup__;
    }

    /**
     * <p>selectPermitViewGroup をセットします。
     * @param selectPermitViewGroup selectPermitViewGroup
     */
    public void setSelectPermitViewGroup(List<LabelValueBean> selectPermitViewGroup) {
        selectPermitViewGroup__ = selectPermitViewGroup;
    }

    /**
     * <p>selectPermitViewUser を取得します。
     * @return selectPermitViewUser
     */
    public List<LabelValueBean> getSelectPermitViewUser() {
        return selectPermitViewUser__;
    }

    /**
     * <p>selectPermitViewUser をセットします。
     * @param selectPermitViewUser selectPermitViewUser
     */
    public void setSelectPermitViewUser(List<LabelValueBean> selectPermitViewUser) {
        selectPermitViewUser__ = selectPermitViewUser;
    }

    /**
     * <p>adr070file を取得します。
     * @return adr070file
     */
    public String[] getAdr070file() {
        return adr070file__;
    }

    /**
     * <p>adr070file をセットします。
     * @param adr070file adr070file
     */
    public void setAdr070file(String[] adr070file) {
        adr070file__ = adr070file;
    }

    /**
     * <p>adr070fileCombo を取得します。
     * @return adr070fileCombo
     */
    public List<LabelValueBean> getAdr070fileCombo() {
        return adr070fileCombo__;
    }

    /**
     * <p>adr070fileCombo をセットします。
     * @param adr070fileCombo adr070fileCombo
     */
    public void setAdr070fileCombo(List<LabelValueBean> adr070fileCombo) {
        adr070fileCombo__ = adr070fileCombo;
    }

    /**
     * <p>adr070CompanyCombo を取得します。
     * @return adr070CompanyCombo
     */
    public List<LabelValueBean> getAdr070CompanyCombo() {
        return adr070CompanyCombo__;
    }

    /**
     * <p>adr070CompanyCombo をセットします。
     * @param adr070CompanyCombo adr070CompanyCombo
     */
    public void setAdr070CompanyCombo(List<LabelValueBean> adr070CompanyCombo) {
        adr070CompanyCombo__ = adr070CompanyCombo;
    }

    /**
     * <p>adr070CompanyBaseCombo を取得します。
     * @return adr070CompanyBaseCombo
     */
    public List<LabelValueBean> getAdr070CompanyBaseCombo() {
        return adr070CompanyBaseCombo__;
    }

    /**
     * <p>adr070CompanyBaseCombo をセットします。
     * @param adr070CompanyBaseCombo adr070CompanyBaseCombo
     */
    public void setAdr070CompanyBaseCombo(
            List<LabelValueBean> adr070CompanyBaseCombo) {
        adr070CompanyBaseCombo__ = adr070CompanyBaseCombo;
    }

    /**
     * <p>adr070selectTantoList を取得します。
     * @return adr070selectTantoList
     */
    public String[] getAdr070selectTantoList() {
        return adr070selectTantoList__;
    }

    /**
     * <p>adr070selectTantoList をセットします。
     * @param adr070selectTantoList adr070selectTantoList
     */
    public void setAdr070selectTantoList(String[] adr070selectTantoList) {
        adr070selectTantoList__ = adr070selectTantoList;
    }

    /**
     * <p>adr070NoSelectTantoList を取得します。
     * @return adr070NoSelectTantoList
     */
    public String[] getAdr070NoSelectTantoList() {
        return adr070NoSelectTantoList__;
    }

    /**
     * <p>adr070NoSelectTantoList をセットします。
     * @param adr070NoSelectTantoList adr070NoSelectTantoList
     */
    public void setAdr070NoSelectTantoList(String[] adr070NoSelectTantoList) {
        adr070NoSelectTantoList__ = adr070NoSelectTantoList;
    }

    /**
     * <p>adr070tantoGroup を取得します。
     * @return adr070tantoGroup
     */
    public int getAdr070tantoGroup() {
        return adr070tantoGroup__;
    }

    /**
     * <p>adr070tantoGroup をセットします。
     * @param adr070tantoGroup adr070tantoGroup
     */
    public void setAdr070tantoGroup(int adr070tantoGroup) {
        adr070tantoGroup__ = adr070tantoGroup;
    }

    /**
     * <p>selectTantoCombo を取得します。
     * @return selectTantoCombo
     */
    public List<LabelValueBean> getSelectTantoCombo() {
        return selectTantoCombo__;
    }

    /**
     * <p>selectTantoCombo をセットします。
     * @param selectTantoCombo selectTantoCombo
     */
    public void setSelectTantoCombo(List<LabelValueBean> selectTantoCombo) {
        selectTantoCombo__ = selectTantoCombo;
    }

    /**
     * <p>noSelectTantoCombo を取得します。
     * @return noSelectTantoCombo
     */
    public List<LabelValueBean> getNoSelectTantoCombo() {
        return noSelectTantoCombo__;
    }

    /**
     * <p>noSelectTantoCombo をセットします。
     * @param noSelectTantoCombo noSelectTantoCombo
     */
    public void setNoSelectTantoCombo(List<LabelValueBean> noSelectTantoCombo) {
        noSelectTantoCombo__ = noSelectTantoCombo;
    }

    /**
     * <p>adr070tantoList を取得します。
     * @return adr070tantoList
     */
    public String[] getAdr070tantoList() {
        return adr070tantoList__;
    }

    /**
     * <p>adr070tantoList をセットします。
     * @param adr070tantoList adr070tantoList
     */
    public void setAdr070tantoList(String[] adr070tantoList) {
        adr070tantoList__ = adr070tantoList;
    }

    /**
     * <p>adr070cmdMode を取得します。
     * @return adr070cmdMode
     */
    public int getAdr070cmdMode() {
        return adr070cmdMode__;
    }

    /**
     * <p>adr070cmdMode をセットします。
     * @param adr070cmdMode adr070cmdMode
     */
    public void setAdr070cmdMode(int adr070cmdMode) {
        adr070cmdMode__ = adr070cmdMode;
    }

    /**
     * <p>adr070updateFlg を取得します。
     * @return adr070updateFlg
     */
    public int getAdr070updateFlg() {
        return adr070updateFlg__;
    }

    /**
     * <p>adr070updateFlg をセットします。
     * @param adr070updateFlg adr070updateFlg
     */
    public void setAdr070updateFlg(int adr070updateFlg) {
        adr070updateFlg__ = adr070updateFlg;
    }
}