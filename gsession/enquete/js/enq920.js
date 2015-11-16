/**
 * 選択行削除
 * @param arg "インデックス番号:種類名SID"
 * @returns {}
 */
function delRow(arg) {

    var subSeq = -1;
    var sid = -1;

    // 引数をINDEXとSIDに分割
    var wk = arg.split(':');
    if (wk.length == 2) {
        subSeq = $.trim(wk[0]);
        sid = $.trim(wk[1]);
    }

    // 選択行削除
    $('#typeRow_' + subSeq).remove();
    changeDspSec();

    // 削除SID保持
    if (sid != -1) {
        saveDelTypeList(sid);
    }
}

/**
 * 新規行追加
 * @returns {}
 */
function addRow() {
    var val = 0;
    var ary = 0;
    var dspSec = 0;

    // IDの最大値を取得
    ary = document.getElementsByName("rowIdx");
    for (i = 0; i < ary.length; i++) {
        if (val <= parseInt(ary[i].value)) {
            val = ary[i].value;
        }
    }
    val++;
    dspSec = val + 1;

    // 行を追加する<tr>
    var trId = 'typeRow_' + val;
    $('#typeTbl').append("<tr id=\"" + trId + "\">"
                       + "<input type=\"hidden\" name=\"rowIdx\" value=\"" + val + "\">"
                       + "<input type=\"hidden\" name=\"enq920TypeList[" + val + "].etpSid\" value=\"-1\">"
                       + "<input type=\"hidden\" name=\"enq920TypeList[" + val + "].etpDspSeq\" value=\"" + dspSec + "\" class=\"etpDspSec\">"
                       + "<td class=\"td_type1 text_mod_10pt rbnChk\" align=\"center\" valign=\"middle\" nowrap>"
                       + "  <input type=\"radio\" name=\"nowRow\" id=\"radioNo_" + val + "\" value=\"" + val + "\" >"
                       + "</td>"
                       + "<td class=\"td_type1 text_mod_10pt\" align=\"left\" valign=\"middle\" nowrap>"
                       + "  <input type=\"text\" name=\"enq920TypeList[" + val + "].etpName\" style=\"width:100%;\" maxlength=\"100\" value=\"\">"
                       + "</td>"
                       + "<td class=\"td_type1 text_mod_10pt\" align=\"left\" valign=\"middle\" nowrap>"
                       + "  &nbsp;"
                       + "</td>"
                       + "<td class=\"td_type1 text_mod_10pt\" align=\"left\" valign=\"middle\" nowrap>"
                       + "  &nbsp;"
                       + "</td>"
                       + "<td class=\"td_type1 text_mod_10pt\" align=\"left\" valign=\"middle\" nowrap>"
                       + "  &nbsp;"
                       + "</td>"
                       + "<td class=\"td_type1 text_mod_10pt\" align=\"center\" valign=\"middle\" nowrap>"
                       + "  <input type=\"button\" class=\"btn_dell_n3\" name=\"enqTypeDelBtn\" value=\"削除\" title=\"削除\" id=\"" + val + ":-1\">"
                       + "</td></tr>");

    // 表示順の再設定
    changeDspSec();
}

$(function(){

    // 上移動
    $('#upList').click(function(){
        // ラジオボタンの選択行を取得
        var chkRow = $("input:radio[name='nowRow']:checked").val();
        // クリックした行の<tr>を取得
        var tr = document.getElementById("typeRow_" + chkRow);
        // 要素を入れ替え、表示順を振り直す
        if($(tr).prev("tr")) {
            $(tr).insertBefore($(tr).prev("tr")[0]);
            changeDspSec();
        }
    });

    // 下移動
    $('#downList').click(function(){
        // ラジオボタンの選択行を取得
        var chkRow = $("input:radio[name='nowRow']:checked").val();
        // クリックした行の<tr>を取得
        var tr = document.getElementById("typeRow_" + chkRow);
        // 要素を入れ替え、表示順を振り直す
        if ($(tr).next("tr")) {
            $(tr).insertAfter($(tr).next("tr")[0]);
            changeDspSec();
        }
    });

    // 削除ボタン
    $("input[name=enqTypeDelBtn]").live("click", function(){

        var thisObj = $(this);
        var rowNum = $(this).attr("id");
        var dialogName = "dialogDeleteOk";

        $('#' + dialogName).dialog({
            autoOpen: true,
            bgiframe: true,
            resizable: false,
            height: 160,
            width: 320,
            modal: true,
            closeOnEscape: false,
            overlay: {
                backgroundColor: '#000000',
                opacity: 0.5
            },
            buttons: {
                はい: function() {
                    $(this).dialog('close');
                    delRow(rowNum);
                },
                いいえ: function() {
                    $(this).dialog('close');
                }
            }
        });
    });

});

/**
 * hiddenパラメータの表示順を再設定する
 * @returns {}
 */
function changeDspSec() {

    // 表示順の再設定
    var hid = $('.etpDspSec');
    for (i = 0; i < $('.etpDspSec').length; i++) {
        hid[i].value = i + 1;
    }
}


/**
 * 削除した種類名のSIDを保持する
 * @param sid アンケート種類SID
 * @returns {}
 */
function saveDelTypeList(sid) {

    // 削除SIDの要素数を取得する
    var val = $('.save').length;

    // 行を追加する
    var element = document.createElement("div");
    element.className = "save";
    element.innerHTML = "<input type=\"hidden\" name=\"enq920DelList[" + val + "].etpSid\" value=\"" + sid + "\">";

    var obj = document.getElementById("saveTbl");
    obj.appendChild(element);
}

