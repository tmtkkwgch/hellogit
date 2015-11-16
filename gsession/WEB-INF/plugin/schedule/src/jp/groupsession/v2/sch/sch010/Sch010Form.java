package jp.groupsession.v2.sch.sch010;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.sch.model.SchLabelValueModel;
import jp.groupsession.v2.sch.model.SimpleCalenderModel;
import jp.groupsession.v2.struts.AbstractGsForm;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] スケジュール 週間画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sch010Form extends AbstractGsForm {

    //共通・モード
    /** 処理モード */
    private String cmd__ = null;
    /** 画面モード */
    private String dspMod__ = null;
    /** 画面モード(一覧用) */
    private String listMod__ = null;

    /** スケジュール共有範囲  0=共有範囲制限なし 1=所属グループのみ*/
    private int sch010CrangeKbn__;

    /** 検索キーワード */
    private String sch010searchWord__;

    //表示条件
    /** 表示開始日付 */
    private String sch010DspDate__ = null;
    /** 表示グループSID */
    private String sch010DspGpSid__;
    /** ユーザSID */
    private String sch010SelectUsrSid__;
    /** ユーザ区分 */
    private String sch010SelectUsrKbn__;
    /** スケジュール登録日付 */
    private String sch010SelectDate__ = null;
    /** スケジュールSID */
    private String sch010SchSid__;

    /** 自動リロード時間 */
    private int sch010Reload__ = GSConstSchedule.AUTO_RELOAD_10MIN;

    //表示内容
    /** ヘッダー表示用年月 */
    private String sch010StrDspDate__ = null;
    /** 週間カレンダー */
    private ArrayList<SimpleCalenderModel> sch010CalendarList__ = null;
    /** グループコンボ */
    private List<SchLabelValueModel> sch010GpLabelList__ = null;
    /** スケジュール上段リスト */
    private ArrayList<Sch010WeekOfModel> sch010TopList__ = null;
    /** スケジュール下段リスト */
    private ArrayList<Sch010WeekOfModel> sch010BottomList__ = null;

    /** 管理者権限有無*/
    private int adminKbn__;
    /** グループ所属有無*/
    private int belongKbn__;
    /** セッションユーザのデフォルトグループSID */
    private String sysDfGroupSid__;
    /** 表示グループ閲覧権限 */
    private boolean schAccessGroup__ = true;
    /** 閲覧を許可しないグループの一覧 */
    private List<Integer> schNotAccessGroupList__;
    /** 閲覧を許可しないユーザの一覧 */
    private List<Integer> schNotAccessUserList__;

    //その他プラグインの利用可能状況
    /** 在席管理プラグイン利用可:0・不可:1*/
    private int zaisekiUseOk__ = GSConstSchedule.PLUGIN_USE;
    /** ショートメールプラグイン利用可:0・不可:1*/
    private int smailUseOk__ = GSConstSchedule.PLUGIN_USE;

    /** 遷移元 メイン個人設定:1 メイン管理者設定:1 その他:0*/
    private int backScreen__ = GSConstMain.BACK_PLUGIN;

    /** 表示区分 0:初期表示 1:初期表示済 2:ショートメールURLリンク先から遷移*/
    private int dspKbn__ = 0;
    /** 表示日付の移動を行ったかどうかの区分 0:未実行 1:実行 */
    private int changeDateFlg__ = 0;

    /**
     * <p>sysDfGroupSid を取得します。
     * @return sysDfGroupSid
     */
    public String getSysDfGroupSid() {
        return sysDfGroupSid__;
    }

    /**
     * <p>sysDfGroupSid をセットします。
     * @param sysDfGroupSid sysDfGroupSid
     */
    public void setSysDfGroupSid(String sysDfGroupSid) {
        sysDfGroupSid__ = sysDfGroupSid;
    }

    /**
     * <p>backScreen を取得します。
     * @return backScreen
     */
    public int getBackScreen() {
        return backScreen__;
    }

    /**
     * <p>backScreen をセットします。
     * @param backScreen backScreen
     */
    public void setBackScreen(int backScreen) {
        backScreen__ = backScreen;
    }

    /**
     * <p>smailUseOk を取得します。
     * @return smailUseOk
     */
    public int getSmailUseOk() {
        return smailUseOk__;
    }

    /**
     * <p>smailUseOk をセットします。
     * @param smailUseOk smailUseOk
     */
    public void setSmailUseOk(int smailUseOk) {
        smailUseOk__ = smailUseOk;
    }

    /**
     * <p>zaisekiUseOk を取得します。
     * @return zaisekiUseOk
     */
    public int getZaisekiUseOk() {
        return zaisekiUseOk__;
    }

    /**
     * <p>zaisekiUseOk をセットします。
     * @param zaisekiUseOk zaisekiUseOk
     */
    public void setZaisekiUseOk(int zaisekiUseOk) {
        zaisekiUseOk__ = zaisekiUseOk;
    }

    /**
     * <p>sch010searchWord を取得します。
     * @return sch010searchWord
     */
    public String getSch010searchWord() {
        return sch010searchWord__;
    }



    /**
     * <p>sch010searchWord をセットします。
     * @param sch010searchWord sch010searchWord
     */
    public void setSch010searchWord(String sch010searchWord) {
        sch010searchWord__ = sch010searchWord;
    }



    /**
     * <p>belongKbn を取得します。
     * @return belongKbn
     */
    public int getBelongKbn() {
        return belongKbn__;
    }



    /**
     * <p>belongKbn をセットします。
     * @param belongKbn belongKbn
     */
    public void setBelongKbn(int belongKbn) {
        belongKbn__ = belongKbn;
    }



    /**
     * <p>listMod を取得します。
     * @return listMod
     */
    public String getListMod() {
        return listMod__;
    }



    /**
     * <p>listMod をセットします。
     * @param listMod listMod
     */
    public void setListMod(String listMod) {
        listMod__ = listMod;
    }



    /**
     * @return adminKbn を戻します。
     */
    public int getAdminKbn() {
        return adminKbn__;
    }



    /**
     * @param adminKbn 設定する adminKbn。
     */
    public void setAdminKbn(int adminKbn) {
        adminKbn__ = adminKbn;
    }



    /**
     * @return cmd を戻します。
     */
    public String getCmd() {
        return cmd__;
    }



    /**
     * @param cmd 設定する cmd。
     */
    public void setCmd(String cmd) {
        cmd__ = cmd;
    }



    /**
     * @return dspMod を戻します。
     */
    public String getDspMod() {
        return dspMod__;
    }



    /**
     * @param dspMod 設定する dspMod。
     */
    public void setDspMod(String dspMod) {
        dspMod__ = dspMod;
    }



    /**
     * @return sch010BottomList を戻します。
     */
    public ArrayList<Sch010WeekOfModel> getSch010BottomList() {
        return sch010BottomList__;
    }



    /**
     * @param sch010BottomList 設定する sch010BottomList。
     */
    public void setSch010BottomList(ArrayList<Sch010WeekOfModel> sch010BottomList) {
        sch010BottomList__ = sch010BottomList;
    }



    /**
     * @return sch010CalendarList を戻します。
     */
    public ArrayList<SimpleCalenderModel> getSch010CalendarList() {
        return sch010CalendarList__;
    }



    /**
     * @param sch010CalendarList 設定する sch010CalendarList。
     */
    public void setSch010CalendarList(ArrayList<SimpleCalenderModel> sch010CalendarList) {
        sch010CalendarList__ = sch010CalendarList;
    }



    /**
     * @return sch010DspDate を戻します。
     */
    public String getSch010DspDate() {
        return sch010DspDate__;
    }



    /**
     * @param sch010DspDate 設定する sch010DspDate。
     */
    public void setSch010DspDate(String sch010DspDate) {
        sch010DspDate__ = sch010DspDate;
    }



    /**
     * @return sch010DspGpSid を戻します。
     */
    public String getSch010DspGpSid() {
        return sch010DspGpSid__;
    }



    /**
     * @param sch010DspGpSid 設定する sch010DspGpSid。
     */
    public void setSch010DspGpSid(String sch010DspGpSid) {
        sch010DspGpSid__ = sch010DspGpSid;
    }



    /**
     * @return sch010GpLabelList を戻します。
     */
    public List <SchLabelValueModel> getSch010GpLabelList() {
        return sch010GpLabelList__;
    }



    /**
     * @param sch010GpLabelList 設定する sch010GpLabelList。
     */
    public void setSch010GpLabelList(List<SchLabelValueModel> sch010GpLabelList) {
        sch010GpLabelList__ = sch010GpLabelList;
    }



    /**
     * @return sch010SchSid を戻します。
     */
    public String getSch010SchSid() {
        return sch010SchSid__;
    }



    /**
     * @param sch010SchSid 設定する sch010SchSid。
     */
    public void setSch010SchSid(String sch010SchSid) {
        sch010SchSid__ = sch010SchSid;
    }



    /**
     * @return sch010SelectDate を戻します。
     */
    public String getSch010SelectDate() {
        return sch010SelectDate__;
    }



    /**
     * @param sch010SelectDate 設定する sch010SelectDate。
     */
    public void setSch010SelectDate(String sch010SelectDate) {
        sch010SelectDate__ = sch010SelectDate;
    }



    /**
     * @return sch010SelectUsrKbn を戻します。
     */
    public String getSch010SelectUsrKbn() {
        return sch010SelectUsrKbn__;
    }



    /**
     * @param sch010SelectUsrKbn 設定する sch010SelectUsrKbn。
     */
    public void setSch010SelectUsrKbn(String sch010SelectUsrKbn) {
        sch010SelectUsrKbn__ = sch010SelectUsrKbn;
    }



    /**
     * @return sch010SelectUsrSid を戻します。
     */
    public String getSch010SelectUsrSid() {
        return sch010SelectUsrSid__;
    }



    /**
     * @param sch010SelectUsrSid 設定する sch010SelectUsrSid。
     */
    public void setSch010SelectUsrSid(String sch010SelectUsrSid) {
        sch010SelectUsrSid__ = sch010SelectUsrSid;
    }



    /**
     * @return sch010StrDspDate を戻します。
     */
    public String getSch010StrDspDate() {
        return sch010StrDspDate__;
    }



    /**
     * @param sch010StrDspDate 設定する sch010StrDspDate。
     */
    public void setSch010StrDspDate(String sch010StrDspDate) {
        sch010StrDspDate__ = sch010StrDspDate;
    }



    /**
     * @return sch010TopList を戻します。
     */
    public ArrayList<Sch010WeekOfModel> getSch010TopList() {
        return sch010TopList__;
    }



    /**
     * @param sch010TopList 設定する sch010TopList。
     */
    public void setSch010TopList(ArrayList<Sch010WeekOfModel> sch010TopList) {
        sch010TopList__ = sch010TopList;
    }



    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param req リクエスト
     * @return エラー
     */
    public ActionErrors validateCheck(ActionMapping map, HttpServletRequest req) {
        ActionErrors errors = new ActionErrors();
        return errors;
    }

    /**
     * <p>sch010Reload を取得します。
     * @return sch010Reload
     */
    public int getSch010Reload() {
        return sch010Reload__;
    }

    /**
     * <p>sch010Reload をセットします。
     * @param sch010Reload sch010Reload
     */
    public void setSch010Reload(int sch010Reload) {
        sch010Reload__ = sch010Reload;
    }

    /**
     * <p>dspKbn を取得します。
     * @return dspKbn
     */
    public int getDspKbn() {
        return dspKbn__;
    }

    /**
     * <p>dspKbn をセットします。
     * @param dspKbn dspKbn
     */
    public void setDspKbn(int dspKbn) {
        dspKbn__ = dspKbn;
    }

    /**
     * @return changeDateFlg を取得します。
     */
    public int getChangeDateFlg() {
        return changeDateFlg__;
    }

    /**
     * @param changeDateFlg をセットします。
     */
    public void setChangeDateFlg(int changeDateFlg) {
        changeDateFlg__ = changeDateFlg;
    }

    /**
     * <p>sch010CrangeKbn を取得します。
     * @return sch010CrangeKbn
     */
    public int getSch010CrangeKbn() {
        return sch010CrangeKbn__;
    }

    /**
     * <p>sch010CrangeKbn をセットします。
     * @param sch010CrangeKbn sch010CrangeKbn
     */
    public void setSch010CrangeKbn(int sch010CrangeKbn) {
        sch010CrangeKbn__ = sch010CrangeKbn;
    }

    /**
     * <p>schAccessGroup を取得します。
     * @return schAccessGroup
     */
    public boolean isSchAccessGroup() {
        return schAccessGroup__;
    }

    /**
     * <p>schAccessGroup をセットします。
     * @param schAccessGroup schAccessGroup
     */
    public void setSchAccessGroup(boolean schAccessGroup) {
        schAccessGroup__ = schAccessGroup;
    }

    /**
     * <p>schNotAccessGroupList を取得します。
     * @return schNotAccessGroupList
     */
    public List<Integer> getSchNotAccessGroupList() {
        return schNotAccessGroupList__;
    }

    /**
     * <p>schNotAccessGroupList をセットします。
     * @param schNotAccessGroupList schNotAccessGroupList
     */
    public void setSchNotAccessGroupList(List<Integer> schNotAccessGroupList) {
        schNotAccessGroupList__ = schNotAccessGroupList;
    }

    /**
     * <p>schNotAccessUserList を取得します。
     * @return schNotAccessUserList
     */
    public List<Integer> getSchNotAccessUserList() {
        return schNotAccessUserList__;
    }

    /**
     * <p>schNotAccessUserList をセットします。
     * @param schNotAccessUserList schNotAccessUserList
     */
    public void setSchNotAccessUserList(List<Integer> schNotAccessUserList) {
        schNotAccessUserList__ = schNotAccessUserList;
    }
}
