package jp.groupsession.v2.enq.enq110;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.enq.EnqValidateUtil;
import jp.groupsession.v2.enq.GSConstEnquete;
import jp.groupsession.v2.enq.enq210.Enq210Form;
import jp.groupsession.v2.enq.model.EnqMainListModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] アンケート 回答画面のフォームクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Enq110Form extends Enq210Form {

    /** 回答画面表示モード */
    private int enq110InitMode__ = Enq110Const.ANS_KBN_UNANS_CURRENT;

    /** アンケートSID */
    private long enq110Sid__ = -1;
    /** アンケート種類名 */
    private String enq110TypeName__ = null;
    /** タイトル */
    private String enq110Title__ = null;
    /** 重要度 */
    private int enq110PriKbn__ = 0;
    /** 説明 */
    private String enq110Desc__ = null;
    /** 添付区分 */
    private int enq110AttachKbn__ = 0;
    /** 添付ID */
    private String enq110AttachId__ = null;
    /** URL */
    private String enq110Url__ = null;
    /** 表示名 */
    private String enq110DspName__ = null;
    /** 添付名*/
    private String enq110AttachName__ = null;
    /** 添付位置 */
    private int enq110AttachPos__ = 0;
    /** 添付ファイルサイズ */
    private String enq110AttachSize__ = null;
    /** 公開期間_開始日 */
    private String enq110OpenStr__ = null;
    /** 公開期間_終了日 */
    private String enq110OpenEnd__ = null;
    /** 公開期限日 */
    private String enq110ResEnd__ = null;
    /** 結果公開開始日 */
    private String enq110AnsPubStr__ = null;
    /** 匿名フラグ */
    private int enq110Anony__ = 0;
    /** 回答公開フラグ */
    private int enq110AnsOpen__ = 0;
    /** 発信者SID */
    private long enq110SendSid__ = -1;
    /** 発信者_名称 */
    private String enq110SendName__ = null;
    /** 発信者区分 */
    private int enq110SendKbn__ = Enq110Const.SENDER_KBN_USER;
    /** 発信者_削除フラグ */
    private boolean enq110SendNameDelFlg__ = false;
    /** アンケート 設問作成日 */
    private String enq110queDate__ = null;

    /** バイナリSID（ダウンロード用） */
    private String enq110BinSid__ = null;
    /** 添付ファイルのコンボデータ */
    private LabelValueBean enq110TempFileLabel__ = null;

    /** 遷移元画面 */
    private int enq110DspMode__ = 0;
    /** プレビュー時に使用するテンポラリディレクトリ区分 */
    private int enq110PreTempDirKbn__ = 0;
    /** テンプラリディレクトリ（ダウンロード用） */
    private String enq110TempDir__ = null;

    /** 回答ユーザSID */
    private String enq110answer__ = null;

    /** 年リスト */
    private ArrayList <LabelValueBean> enq110YearLabel__ = null;
    /** 月リスト */
    private ArrayList <LabelValueBean> enq110MonthLabel__ = null;
    /** 日リスト */
    private ArrayList <LabelValueBean> enq110DayLabel__ = null;

    /** 設問一覧リスト */
    private List<EnqMainListModel> enq110QueList__ = null;

    /**
     * <p>回答画面表示モード を取得します。
     * @return 回答画面表示モード
     */
    public int getEnq110InitMode() {
        return enq110InitMode__;
    }
    /**
     * <p>回答画面表示モード をセットします。
     * @param enq110InitMode 回答画面表示モード
     */
    public void setEnq110InitMode(int enq110InitMode) {
        enq110InitMode__ = enq110InitMode;
    }
    /**
     * <p>アンケートSID を取得します。
     * @return アンケートSID
     */
    public long getEnq110Sid() {
        return enq110Sid__;
    }
    /**
     * <p>アンケートSID をセットします。
     * @param enq110Sid アンケートSID
     */
    public void setEnq110Sid(long enq110Sid) {
        enq110Sid__ = enq110Sid;
    }
    /**
     * <p>アンケート種類名 を取得します。
     * @return アンケート種類名
     */
    public String getEnq110TypeName() {
        return enq110TypeName__;
    }
    /**
     * <p>アンケート種類名 をセットします。
     * @param enq110TypeName アンケート種類名
     */
    public void setEnq110TypeName(String enq110TypeName) {
        enq110TypeName__ = enq110TypeName;
    }
    /**
     * <p>タイトル を取得します。
     * @return タイトル
     */
    public String getEnq110Title() {
        return enq110Title__;
    }
    /**
     * <p>タイトル をセットします。
     * @param enq110Title タイトル
     */
    public void setEnq110Title(String enq110Title) {
        enq110Title__ = enq110Title;
    }
    /**
     * <p>重要度 を取得します。
     * @return 重要度
     */
    public int getEnq110PriKbn() {
        return enq110PriKbn__;
    }
    /**
     * <p>重要度 をセットします。
     * @param enq110PriKbn 重要度
     */
    public void setEnq110PriKbn(int enq110PriKbn) {
        enq110PriKbn__ = enq110PriKbn;
    }
    /**
     * <p>説明 を取得します。
     * @return 説明
     */
    public String getEnq110Desc() {
        return enq110Desc__;
    }
    /**
     * <p>説明 をセットします。
     * @param enq110Desc 説明
     */
    public void setEnq110Desc(String enq110Desc) {
        enq110Desc__ = enq110Desc;
    }
    /**
     * <p>添付区分 を取得します。
     * @return 添付区分
     */
    public int getEnq110AttachKbn() {
        return enq110AttachKbn__;
    }
    /**
     * <p>添付区分 をセットします。
     * @param enq110AttachKbn 添付区分
     */
    public void setEnq110AttachKbn(int enq110AttachKbn) {
        enq110AttachKbn__ = enq110AttachKbn;
    }
    /**
     * <p>添付ID を取得します。
     * @return 添付ID
     */
    public String getEnq110AttachId() {
        return enq110AttachId__;
    }
    /**
     * <p>添付ID をセットします。
     * @param enq110AttachId 添付ID
     */
    public void setEnq110AttachId(String enq110AttachId) {
        enq110AttachId__ = enq110AttachId;
    }
    /**
     * <p>URL を取得します。
     * @return URL
     */
    public String getEnq110Url() {
        return enq110Url__;
    }
    /**
     * <p>URL をセットします。
     * @param enq110Url URL
     */
    public void setEnq110Url(String enq110Url) {
        enq110Url__ = enq110Url;
    }
    /**
     * <p>表示名 を取得します。
     * @return 表示名
     */
    public String getEnq110DspName() {
        return enq110DspName__;
    }
    /**
     * <p>表示名 をセットします。
     * @param enq110DspName 表示名
     */
    public void setEnq110DspName(String enq110DspName) {
        enq110DspName__ = enq110DspName;
    }
    /**
     * <p>添付名 を取得します。
     * @return 添付名
     */
    public String getEnq110AttachName() {
        return enq110AttachName__;
    }
    /**
     * <p>添付名 をセットします。
     * @param enq110AttachName 添付名
     */
    public void setEnq110AttachName(String enq110AttachName) {
        enq110AttachName__ = enq110AttachName;
    }
    /**
     * <p>添付位置 を取得します。
     * @return 添付位置
     */
    public int getEnq110AttachPos() {
        return enq110AttachPos__;
    }
    /**
     * <p>添付位置 をセットします。
     * @param enq110AttachPos 添付位置
     */
    public void setEnq110AttachPos(int enq110AttachPos) {
        enq110AttachPos__ = enq110AttachPos;
    }
    /**
     * <p>enq110AttachSize を取得します。
     * @return enq110AttachSize
     */
    public String getEnq110AttachSize() {
        return enq110AttachSize__;
    }
    /**
     * <p>enq110AttachSize をセットします。
     * @param enq110AttachSize enq110AttachSize
     */
    public void setEnq110AttachSize(String enq110AttachSize) {
        enq110AttachSize__ = enq110AttachSize;
    }
    /**
     * <p>公開期間_開始日 を取得します。
     * @return 公開期間_開始日
     */
    public String getEnq110OpenStr() {
        return enq110OpenStr__;
    }
    /**
     * <p>公開期間_開始日 をセットします。
     * @param enq110OpenStr 公開期間_開始日
     */
    public void setEnq110OpenStr(String enq110OpenStr) {
        enq110OpenStr__ = enq110OpenStr;
    }
    /**
     * <p>公開期間_終了日 を取得します。
     * @return 公開期間_終了日
     */
    public String getEnq110OpenEnd() {
        return enq110OpenEnd__;
    }
    /**
     * <p>公開期間_終了日 をセットします。
     * @param enq110OpenEnd 公開期間_終了日
     */
    public void setEnq110OpenEnd(String enq110OpenEnd) {
        enq110OpenEnd__ = enq110OpenEnd;
    }
    /**
     * <p>公開期限日 を取得します。
     * @return 公開期限日
     */
    public String getEnq110ResEnd() {
        return enq110ResEnd__;
    }
    /**
     * <p>公開期限日 をセットします。
     * @param enq110ResEnd 公開期限日
     */
    public void setEnq110ResEnd(String enq110ResEnd) {
        enq110ResEnd__ = enq110ResEnd;
    }
    /**
     * <p>enq110AnsPubStr を取得します。
     * @return enq110AnsPubStr
     */
    public String getEnq110AnsPubStr() {
        return enq110AnsPubStr__;
    }
    /**
     * <p>enq110AnsPubStr をセットします。
     * @param enq110AnsPubStr enq110AnsPubStr
     */
    public void setEnq110AnsPubStr(String enq110AnsPubStr) {
        enq110AnsPubStr__ = enq110AnsPubStr;
    }
    /**
     * <p>回答公開フラグ を取得します。
     * @return 回答公開フラグ
     */
    public int getEnq110Anony() {
        return enq110Anony__;
    }
    /**
     * <p>回答公開フラグ をセットします。
     * @param enq110Anony 回答公開フラグ
     */
    public void setEnq110Anony(int enq110Anony) {
        enq110Anony__ = enq110Anony;
    }
    /**
     * <p>回答公開フラグ を取得します。
     * @return 回答公開フラグ
     */
    public int getEnq110AnsOpen() {
        return enq110AnsOpen__;
    }
    /**
     * <p>回答公開フラグ をセットします。
     * @param enq110AnsOpen 回答公開フラグ
     */
    public void setEnq110AnsOpen(int enq110AnsOpen) {
        enq110AnsOpen__ = enq110AnsOpen;
    }
    /**
     * <p>発信者SID を取得します。
     * @return 発信者SID
     */
    public long getEnq110SendSid() {
        return enq110SendSid__;
    }
    /**
     * <p>発信者SID をセットします。
     * @param enq110SendSid 発信者SID
     */
    public void setEnq110SendSid(long enq110SendSid) {
        enq110SendSid__ = enq110SendSid;
    }
    /**
     * <p>発信者_名称 を取得します。
     * @return 発信者_名称
     */
    public String getEnq110SendName() {
        return enq110SendName__;
    }
    /**
     * <p>発信者_名称 をセットします。
     * @param enq110SendName 発信者_名称
     */
    public void setEnq110SendName(String enq110SendName) {
        enq110SendName__ = enq110SendName;
    }
    /**
     * <p>enq110SendKbn を取得します。
     * @return enq110SendKbn
     */
    public int getEnq110SendKbn() {
        return enq110SendKbn__;
    }
    /**
     * <p>enq110SendKbn をセットします。
     * @param enq110SendKbn enq110SendKbn
     */
    public void setEnq110SendKbn(int enq110SendKbn) {
        enq110SendKbn__ = enq110SendKbn;
    }
    /**
     * <p>enq110SendNameDelFlg を取得します。
     * @return enq110SendNameDelFlg
     */
    public boolean isEnq110SendNameDelFlg() {
        return enq110SendNameDelFlg__;
    }
    /**
     * <p>enq110SendNameDelFlg をセットします。
     * @param enq110SendNameDelFlg enq110SendNameDelFlg
     */
    public void setEnq110SendNameDelFlg(boolean enq110SendNameDelFlg) {
        enq110SendNameDelFlg__ = enq110SendNameDelFlg;
    }
    /**
     * <p>バイナリSID（ダウンロード用） を取得します。
     * @return バイナリSID（ダウンロード用）
     */
    public String getEnq110BinSid() {
        return enq110BinSid__;
    }
    /**
     * <p>バイナリSID（ダウンロード用） をセットします。
     * @param enq110BinSid バイナリSID（ダウンロード用）
     */
    public void setEnq110BinSid(String enq110BinSid) {
        enq110BinSid__ = enq110BinSid;
    }
    /**
     * <p>enq110TempFileLabel を取得します。
     * @return enq110TempFileLabel
     */
    public LabelValueBean getEnq110TempFileLabel() {
        return enq110TempFileLabel__;
    }
    /**
     * <p>enq110TempFileLabel をセットします。
     * @param enq110TempFileLabel enq110TempFileLabel
     */
    public void setEnq110TempFileLabel(LabelValueBean enq110TempFileLabel) {
        enq110TempFileLabel__ = enq110TempFileLabel;
    }
    /**
     * <p>enq110DspMode を取得します。
     * @return enq110DspMode
     */
    public int getEnq110DspMode() {
        return enq110DspMode__;
    }
    /**
     * <p>enq110DspMode をセットします。
     * @param enq110DspMode enq110DspMode
     */
    public void setEnq110DspMode(int enq110DspMode) {
        enq110DspMode__ = enq110DspMode;
    }
    /**
     * <p>enq110PreTempDirKbn を取得します。
     * @return enq110PreTempDirKbn
     */
    public int getEnq110PreTempDirKbn() {
        return enq110PreTempDirKbn__;
    }
    /**
     * <p>enq110PreTempDirKbn をセットします。
     * @param enq110PreTempDirKbn enq110PreTempDirKbn
     */
    public void setEnq110PreTempDirKbn(int enq110PreTempDirKbn) {
        this.enq110PreTempDirKbn__ = enq110PreTempDirKbn;
    }
    /**
     * <p>enq110TempDir を取得します。
     * @return enq110TempDir
     */
    public String getEnq110TempDir() {
        return enq110TempDir__;
    }
    /**
     * <p>enq110TempDir をセットします。
     * @param enq110TempDir enq110TempDir
     */
    public void setEnq110TempDir(String enq110TempDir) {
        enq110TempDir__ = enq110TempDir;
    }
    /**
     * <p>enq110answer を取得します。
     * @return enq110answer
     */
    public String getEnq110answer() {
        return enq110answer__;
    }
    /**
     * <p>enq110answer をセットします。
     * @param enq110answer enq110answer
     */
    public void setEnq110answer(String enq110answer) {
        enq110answer__ = enq110answer;
    }
    /**
     * <p>enq110queDate を取得します。
     * @return enq110queDate
     */
    public String getEnq110queDate() {
        return enq110queDate__;
    }
    /**
     * <p>enq110queDate をセットします。
     * @param enq110queDate enq110queDate
     */
    public void setEnq110queDate(String enq110queDate) {
        enq110queDate__ = enq110queDate;
    }
    /**
     * <p>年リスト を取得します。
     * @return 年リスト
     */
    public ArrayList<LabelValueBean> getEnq110YearLabel() {
        return enq110YearLabel__;
    }
    /**
     * <p>年リスト をセットします。
     * @param enq110YearLabel 年リスト
     */
    public void setEnq110YearLabel(ArrayList<LabelValueBean> enq110YearLabel) {
        enq110YearLabel__ = enq110YearLabel;
    }
    /**
     * <p>月リスト を取得します。
     * @return 月リスト
     */
    public ArrayList<LabelValueBean> getEnq110MonthLabel() {
        return enq110MonthLabel__;
    }
    /**
     * <p>月リスト をセットします。
     * @param enq110MonthLabel 月リスト
     */
    public void setEnq110MonthLabel(ArrayList<LabelValueBean> enq110MonthLabel) {
        enq110MonthLabel__ = enq110MonthLabel;
    }
    /**
     * <p>日リスト を取得します。
     * @return 日リスト
     */
    public ArrayList<LabelValueBean> getEnq110DayLabel() {
        return enq110DayLabel__;
    }
    /**
     * <p>日リスト をセットします。
     * @param enq110DayLabel 日リスト
     */
    public void setEnq110DayLabel(ArrayList<LabelValueBean> enq110DayLabel) {
        enq110DayLabel__ = enq110DayLabel;
    }
    /**
     * <p>設問一覧リスト を取得します。
     * @return 設問一覧リスト
     */
    public List<EnqMainListModel> getEnq110QueListToList() {
        return enq110QueList__;
    }
    /**
     * <p>設問一覧リスト をセットします。
     * @param enq110QueList 設問一覧リスト
     */
    public void setEnq110QueListForm(List<EnqMainListModel> enq110QueList) {
        enq110QueList__ = enq110QueList;
    }

    /**
     * <br>[機  能] アンケート設問リストを取得します
     * <br>[解  説]
     * <br>[備  考]
     * @param index インデックス番号
     * @return 設問リスト を戻す
     */
    public EnqMainListModel getEnq110QueList(int index) {
        while (enq110QueList__.size() <= index) {
            enq110QueList__.add(new EnqMainListModel());
        }
        return enq110QueList__.get(index);
    }

    /**
     * <br>[機  能] アンケート設問リストの配列を取得します
     * <br>[解  説]
     * <br>[備  考]
     * @return アンケート設問[]
     */
    public EnqMainListModel[] getEnq110QueList() {
        int size = 0;
        if (enq110QueList__ != null) {
            size = enq110QueList__.size();
        }
        return (EnqMainListModel[]) enq110QueList__.toArray(new EnqMainListModel[size]);
    }

    /**
     * <br>[機  能] アンケート設問リストの件数を返します
     * <br>[解  説]
     * <br>[備  考]
     * @return 件数
     */
    public int getEnq110QueListSize() {
        return enq110QueList__.size();
    }

    /**
     * コンストラクタ
     */
    public Enq110Form() {
        enq110QueList__ = new ArrayList<EnqMainListModel>();
    }

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @return エラー
     */
    public ActionErrors validateEnq110(RequestModel reqMdl) {

        ActionErrors errors = new ActionErrors();
        GsMessage gsMsg = new GsMessage(reqMdl);
        String targetJp = null;

        // 一覧
        for (EnqMainListModel bean : enq110QueList__) {

            // 回答必須フラグ
            boolean require = false;
            if (bean.getEqmRequire().equals(String.valueOf(GSConstEnquete.REQUIRE_ON))) {
                require = true;
            }
            targetJp = gsMsg.getMessage("enq.37") + StringUtilHtml.transToHTml(bean.getEqmQueSec());

            // コメント
            if (bean.getEqmQueKbn() == GSConstEnquete.SYURUI_COMMENT) {
                continue;
            }
            // 単一
            if (bean.getEqmQueKbn() == GSConstEnquete.SYURUI_SINGLE) {
                EnqValidateUtil.validateRadioBtn(
                        errors, bean.getEqmSelRbn(), targetJp, targetJp, require);

                // その他選択時、テキストボックスの入力チェック
                if (bean.getEqmSelRbn() != null
                 && bean.getEqmSelRbn().equals(String.valueOf(GSConstEnquete.CHOICE_KBN_OTHER))) {
                    targetJp += "(" + gsMsg.getMessage("cmn.other") + ")";

                    // その他（1行）
                    if (bean.getEqmOther() == GSConstEnquete.OTHER_TEXT) {
                        EnqValidateUtil.validateTextBoxInput(errors,
                                                             bean.getEqmSelOther(),
                                                             targetJp,
                                                             targetJp,
                                                             GSConstEnquete.MAX_LEN_EAS_ANS_TEXT,
                                                             require);
                    // その他（複数行）
                    } else if (bean.getEqmOther() == GSConstEnquete.OTHER_TEXTAREA) {
                        EnqValidateUtil.validateTextarea(errors,
                                                         bean.getEqmSelOther(),
                                                         targetJp,
                                                         targetJp,
                                                         GSConstEnquete.MAX_LEN_EAS_ANS_TEXTAREA,
                                                         require);
                    }
                }

            // 複数
            } else if (bean.getEqmQueKbn() == GSConstEnquete.SYURUI_MULTIPLE) {
                EnqValidateUtil.validateCheckBox(
                        errors, bean.getEqmSelChk(), targetJp, targetJp, require);

                // その他選択時、テキストボックスの入力チェック
                if (bean.getEqmSelChk() != null
                 && Arrays.asList(bean.getEqmSelChk()).contains(
                         String.valueOf(GSConstEnquete.CHOICE_KBN_OTHER))) {
                    targetJp += "(" + gsMsg.getMessage("cmn.other") + ")";

                    // その他（1行）
                    if (bean.getEqmOther() == GSConstEnquete.OTHER_TEXT) {
                        EnqValidateUtil.validateTextBoxInput(errors,
                                                             bean.getEqmSelOther(),
                                                             targetJp,
                                                             targetJp,
                                                             GSConstEnquete.MAX_LEN_EAS_ANS_TEXT,
                                                             require);
                    // その他（複数行）
                    } else if (bean.getEqmOther() == GSConstEnquete.OTHER_TEXTAREA) {
                        EnqValidateUtil.validateTextarea(errors,
                                                         bean.getEqmSelOther(),
                                                         targetJp,
                                                         targetJp,
                                                         GSConstEnquete.MAX_LEN_EAS_ANS_TEXTAREA,
                                                         require);
                    }
                }

            // テキスト
            } else if (bean.getEqmQueKbn() == GSConstEnquete.SYURUI_TEXT) {
                EnqValidateUtil.validateTextBoxInput(errors,
                                                     bean.getEqmAnsText(),
                                                     targetJp,
                                                     targetJp,
                                                     GSConstEnquete.MAX_LEN_EAS_ANS_TEXT,
                                                     require);

            // テキストエリア
            } else if (bean.getEqmQueKbn() == GSConstEnquete.SYURUI_TEXTAREA) {
                EnqValidateUtil.validateTextarea(errors,
                                                 bean.getEqmAnsTextarea(),
                                                 targetJp,
                                                 targetJp,
                                                 GSConstEnquete.MAX_LEN_EAS_ANS_TEXTAREA,
                                                 require);

            // 数値
            } else if (bean.getEqmQueKbn() == GSConstEnquete.SYURUI_INTEGER) {
                EnqValidateUtil.validateIntTextBox(errors,
                                                   bean.getEqmAnsNum(),
                                                   targetJp,
                                                   targetJp,
                                                   bean.getEqmRngStrNum(),
                                                   bean.getEqmRngEndNum(),
                                                   GSConstEnquete.MAX_LEN_EAS_ANS_INT,
                                                   require);

            // 日付
            } else {
                // 論理チェック
                boolean flg = EnqValidateUtil.validateDate(reqMdl,
                                                           errors,
                                                           targetJp,
                                                           targetJp,
                                                           bean.getEqmSelectAnsYear(),
                                                           bean.getEqmSelectAnsMonth(),
                                                           bean.getEqmSelectAnsDay(),
                                                           require);

                // 範囲チェック
                if (!flg) {
                    String date = StringUtil.getStrYyyyMmDd(bean.getEqmSelectAnsYear(),
                                                           bean.getEqmSelectAnsMonth(),
                                                           bean.getEqmSelectAnsDay());
                    EnqValidateUtil.validateDateRenge(errors,
                                                      date,
                                                      targetJp,
                                                      targetJp,
                                                      bean.getEqmRngStrDat(),
                                                      bean.getEqmRngEndDat(),
                                                      require);
                }
            }

        }

        return errors;
    }

    /**
     * <br>[機  能] 添付ファイルダウンロード時のオペレーションログ内容を取得
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエストモデル
     * @return [回答アンケート]XXXXX \r\n
     */
    public String getTargetLog(RequestModel reqMdl) {

        GsMessage gsMsg = new GsMessage(reqMdl);
        String ret = null;

        // 回答アンケート名
        ret = "[" + gsMsg.getMessage("enq.36") + "]" + enq110Title__;

       return ret;
    }

    /**
     * <br>[機  能] yyyy年mm月dd日を、8桁の日付文字列に変換する。
     * <br>[解  説]
     * <br>[備  考]
     * @param date yyyy年mm月dd日
     * @return yyyymmdd
     */
    public String getTrimDate(String date) {

        String ret = "";
        if (date.length() > 9) {
            ret = date.substring(0, 4) + date.substring(6, 7) + date.substring(8, 9);
        }
        return ret;
    }
}
