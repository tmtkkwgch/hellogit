<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<div id="footdiv"></div>

<div id="dialog" title="削除確認" style="display:none">
    <p>
      <span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>削除してもよろしいですか？</b>
    </p>
</div>

<div id="dialogNtpDel" title="削除確認" style="display:none">
    <p>
      <span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>削除してもよろしいですか？</b>
    </p>
</div>

<div id="dialogCommentDel" title="削除確認" style="display:none">
    <p>
      <span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>削除してもよろしいですか？</b>
    </p>
</div>

<div id="dialogEditOk" title="編集確認" style="display:none">
    <p>
      <span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>編集してもよろしいですか？</b>
    </p>
</div>

<div id="dspMoveOk" title="移動確認" style="display:none">
    <p>
      <span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>報告日付が変更されました。<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;変更した日付へ移動しますか？</b>
    </p>
</div>

<div id="commentError" title="入力エラー" style="display:none">
    <p>
      <span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>コメントを入力してください。</b><br><br>
    </p>
</div>

<div id="commentLengthError" title="入力エラー" style="display:none">
    <p>
      <span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span><b>コメントは1000文字以内で入力してください。</b><br><br>
    </p>
</div>

<div id="goodError" title="" style="display:none">
    <p>
      <span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>この日報には「いいね!」しています。</b><br><br>
    </p>
</div>

<div id="goodDialog" title="" style="display:none">
    <p>
      <span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>この日報に「いいね!」しますか?</b><br><br>
    </p>
</div>

<div id="goodDialogComp" title="" style="display:none">
    <p>
      <span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>「いいね!」しました。</b><br><br>
    </p>
</div>

<div id="goodZero" title="" style="display:none">
    <p>
      <span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>この日報に「いいね!」しているユーザはいません。</b><br><br>
    </p>
</div>

<div id="dialog_error" class="error_dialog" title="" style="display:none">
    <p>
      <table>
        <tr>
          <td class="ui-icon ui-icon-alert" valign="middle"></td>
          <td id="error_msg" valign="middle" style="padding-left:20px;" class="TXT02"></td>
        </tr>
      </table>
    </p>
</div>

<div id="goodUsrInfArea" class="goodAddUsrArea">
  <table style="width:100%;">
    <tr>
      <td style="width:100%;" align="right">
        <input value="閉じる" class="btn_close_n1" id="goodAddUsrClose" type="button">
      </td>
    </tr>
    <tr>
      <td style="width:100%;">
        <div id="goodUsrInfArea2" style="height:270px;overflow-y: scroll;">
        </div>
      </td>
    </tr>
  </table>
</div>
<div id="dialogEditOk" title="変更確認" style="display:none">
    <p>
      <span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>変更してもよろしいですか？</b>
    </p>
</div>
<div id="dialogAddOk" title="登録確認" style="display:none">
    <p>
      <span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>登録してもよろしいですか？</b>
    </p>
</div>
<div id="notAddOk" title="" style="display:none">
    <p>
      <span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>未登録の日報を破棄してもよろしいですか？</b>
    </p>
</div>
<div id="notEditOk" title="" style="display:none">
    <p>
      <span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>編集中のデータを破棄してもよろしいですか？</b>
    </p>
</div>
<div id="adrHistoryPop" title="選択アドレス履歴" style="display:none;">
  <p>
    <div id="adrHistoryArea">
    </div>
  </p>
</div>
<div id="ankenHistoryPop" title="選択案件履歴" style="display:none;">
  <p>
    <div id="ankenHistoryArea">
    </div>
  </p>
</div>


