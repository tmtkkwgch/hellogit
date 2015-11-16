$(function() {

    //目標名セット
    initTargetName();

    //目標選択ボタン
    $("#targetPopBtn").live("click", function(){

        /* 目標ポップアップ */
        $('#targetPop').dialog({
            autoOpen: true,  // hide dialog
            bgiframe: true,   // for IE6
            resizable: false,
            height: 400,
            width: 600,
            modal: true,
            overlay: {
              backgroundColor: '#000000',
              opacity: 0.5
            },
            buttons: {
              選択: function() {

                  //選択値取得
                  var targetList   = $("input:checkbox[name='poptarget']:checked");
                  $('#targetArea').children().remove();
                  $('#targetArea').html("<div></div>");

                  //画面表示
                  for (var j = 0; j < targetList.length; j++) {
                      var targetName = $('#td_name_' + targetList[j].value).html();
                      if (targetName != null) {
                          $('#targetArea').children().append("<div id=\"targetArea_" + targetList[j].value + "\">"
                                +  "<span class=\"text_target\">"
                                +  "<a id=\""  + targetList[j].value +  "\" class=\"sc_link target_click\">"
                                +  $('#td_name_' + targetList[j].value).html()
                                +  "</a>"
                                +  "</span>"
                                +  "<input type=\"hidden\" name=\"ntp087DspTarget\" value=\"" + targetList[j].value + "\">"
                                +  "&nbsp;<span class=\"targetDel\" id=\"" + targetList[j].value + "\"><img src=\"../nippou/images/delete_icon.gif\" alt=\"削除\">削除</span><br>"
                                +  "</div>");
                      }

                  }
                  $(this).dialog('close');
              },
              ｷｬﾝｾﾙ: function() {
                  //表示項目をリセット
                  resetTargetObj();
                  $(this).dialog('close');
              }
            }
        });
    });


    /* 目標削除リンク */
    $(".targetDel").live("click", function(){
        var targetId = $(this).attr("id");
        $('#targetArea_' + targetId).remove();
        clickTargetName(1, targetId);
    });

    //目標名クリック
    $(".target_click").live("click", function(){
        var targetSid = $(this).attr("id");
        openSubWindow("../nippou/ntp250.do?ntp250NtgSid=" + targetSid);
    });

});


function selectGroup() {
    document.forms[0].CMD.value = 'changeGrp';
    document.forms[0].submit();
    return false;
}


var checkBoxClickFlg = 0;

function clickMulti() {
    checkBoxClickFlg = 1;
    return false;
}

function clickTargetName(typeNo, itmSid) {

    if (checkBoxClickFlg == 0) {
        //tr押下時
        if (!$('#tr_' + itmSid).children().children().attr('checked')) {
            $('#tr_' + itmSid).children().children().attr('checked','checked');
            if (typeNo == 1) {
                $('#tr_' + itmSid)[0].className = 'td_type_selected';
            } else {
                $('#tr_' + itmSid)[0].className = 'td_type_selected2';
            }
        } else {
            $('#tr_' + itmSid).children().children().attr('checked','');
            var cssName = 'td_line_color' + typeNo;
            $('#tr_' + itmSid)[0].className = cssName;
        }
    } else {
        //checkBox押下時
        if ($('#tr_' + itmSid).children().children().attr('checked')) {
            $('#tr_' + itmSid).children().children().attr('checked','checked');
            if (typeNo == 1) {
                $('#tr_' + itmSid)[0].className = 'td_type_selected';
            } else {
                $('#tr_' + itmSid)[0].className = 'td_type_selected2';
            }
        } else {
            $('#tr_' + itmSid).children().children().attr('checked','');
            var cssName = 'td_line_color' + typeNo;
            $('#tr_' + itmSid)[0].className = cssName;
        }
        checkBoxClickFlg = 0;
    }
    return false;
}

function resetTargetObj() {
    //チェックをすべてはずす
    var targetList   = $("input:checkbox[name='poptarget']");
    targetList.attr('checked', false);
    for (var n = 0; n < targetList.length; n++) {
        //画面表示
        resetTargetName(targetList[n].value);
    }

    //現在画面で選択されている値を取得
    var dspTargetList   = $("input:hidden[name='ntp087DspTarget']");

    for (var i = 0; i < dspTargetList.length; i++) {
      //一致する値にチェック,色変更する
      clickTargetName(1, dspTargetList[i].value);
    }
    return false;
}

function resetTargetName(itmSid) {

    $('#tr_' + itmSid).children().children().attr('checked','');
    var cssName = 'td_line_color1';
    $('#tr_' + itmSid)[0].className = cssName;

    return false;
}


function initTargetName() {
    //選択値取得
    var targetList   = $("input:hidden[name='ntp087DspTarget']");
    $('#targetArea').children().remove();
    $('#targetArea').html("<div></div>");


    //画面表示
    if (document.forms[0].ntp087initDspFlg.value == 0) {
        for (var j = targetList.length - 1; j >= 0; j--) {

            var targetName = $('#td_name_' + targetList[j].value).html();
            if (targetName != null) {
                $('#targetArea').children().append("<div id=\"targetArea_" + targetList[j].value + "\">"
                        +  "<span class=\"text_target\">"
                        +  "<a id=\""  + targetList[j].value +  "\" class=\"sc_link target_click\">"
                        +  $('#td_name_' + targetList[j].value).html()
                        +  "</a>"
                        +  "</span>"
                        +  "<input type=\"hidden\" name=\"ntp087DspTarget\" value=\"" + targetList[j].value + "\">"
                        +  "&nbsp;<span class=\"targetDel\" id=\"" + targetList[j].value + "\"><img src=\"../nippou/images/delete_icon.gif\" alt=\"削除\">削除</span><br>"
                        +  "</div>");
            }

        }
    } else {
        for (var k = 0; k < targetList.length; k++) {

            var targetName = $('#td_name_' + targetList[k].value).html();
            if (targetName != null) {
                $('#targetArea').children().append("<div id=\"targetArea_" + targetList[k].value + "\">"
                        +  "<span class=\"text_target\">"
                        +  "<a id=\""  + targetList[k].value +  "\" class=\"sc_link target_click\">"
                        +  $('#td_name_' + targetList[k].value).html()
                        +  "</a>"
                        +  "</span>"
                        +  "<input type=\"hidden\" name=\"ntp087DspTarget\" value=\"" + targetList[k].value + "\">"
                        +  "&nbsp;<span class=\"targetDel\" id=\"" + targetList[k].value + "\"><img src=\"../nippou/images/delete_icon.gif\" alt=\"削除\">削除</span><br>"
                        +  "</div>");
            }

        }
    }





    for (var m = 0; m < targetList.length; m++) {
        //一致する値にチェック,色変更する
        clickTargetName(1, targetList[m].value);
    }

    document.forms[0].ntp087initDspFlg.value = 1;
}