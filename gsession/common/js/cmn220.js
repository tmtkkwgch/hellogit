function searchUser(cmd){
    if ($('#field_id').val() != null && $('#field_id').val() != "") {
        document.forms[0].CMD.value=cmd;
        document.forms[0].submit();
    } else {
        var dwidth2 = 180;
          $('#dialog-error3').dialog({
              autoOpen: true,
              height: dwidth2,
              width: 350,
              modal: true,
              buttons: {
                  "OK": function() {
                      $( this ).dialog( "close" );
                  }
              }
          });
      }
    return false;
}

function changeGroup(gpSid){
    document.forms[0].CMD.value = 'changeGroup';
    document.forms[0].cmn220groupSid.value=gpSid;
    document.forms[0].cmn220SearchFlg.value=0;
    document.forms[0].cmn220SearchStr.value="";
    document.forms[0].submit();
    return false;
}

function moveUserButton(cmd, moveSid){
    var moveUsers=[];
    if (moveSid != null && moveSid != "" ) {
        moveUsers.push(moveSid);
    }

    document.forms[0].CMD.value=cmd;
    document.forms[0].moveUserSid.value=moveUsers;
    document.forms[0].submit();
    return false;
}

function moveUserAddButton(cmd){
    var idList = [];
    $('.td_type_selected').each(function() {
        idList.push($(this).attr('id'));
    });
    $('.td_type_selected2').each(function() {
        idList.push($(this).attr('id'));
    });
    if (idList != null  && idList.length > 0) {
        document.forms[0].CMD.value=cmd;
        document.forms[0].moveUsers.value=idList;
        document.forms[0].submit();
    } else {
      var dwidth2 = 180;
        $('#dialog-error').dialog({
            autoOpen: true,
            height: dwidth2,
            width: 350,
            modal: true,
            buttons: {
                "OK": function() {
                    $( this ).dialog( "close" );
                }
            }
        });
    }

    return false;
}

function moveUserDelButton(cmd){
    var idList = [];
    $('.td_type_selected3').each(function() {
        idList.push($(this).attr('id'));
    });
    $('.td_type_selected4').each(function() {
        idList.push($(this).attr('id'));
    });

    if (idList != null  && idList.length > 0) {
        document.forms[0].CMD.value=cmd;
        document.forms[0].moveUsers.value=idList;
        document.forms[0].submit();
    } else {
      var dwidth2 = 180;
        $('#dialog-error2').dialog({
            autoOpen: true,
            height: dwidth2,
            width: 350,
            modal: true,
            buttons: {
                "OK": function() {
                    $( this ).dialog( "close" );
                }
            }
        });
    }
    return false;
}

function moveGroupButton(cmd, moveSid){
    var moveGroups=[];
    if (moveSid != null && moveSid != "" ) {
        moveGroups.push(moveSid.split("G").join(""));
    }

    document.forms[0].CMD.value=cmd;
    document.forms[0].moveGroupSid.value=moveGroups;
    document.forms[0].submit();
    return false;
}

function moveGroupAddButton(cmd){
    var idList = [];
    $('.td_type_selected').each(function() {
        idList.push($(this).attr('id').split("G").join(""));
    });
    $('.td_type_selected2').each(function() {
        idList.push($(this).attr('id').split("G").join(""));
    });
    if (idList != null  && idList.length > 0) {
        document.forms[0].CMD.value=cmd;
        document.forms[0].moveGroups.value=idList;
        document.forms[0].submit();
    } else {
      var dwidth2 = 180;
        $('#dialog-error4').dialog({
            autoOpen: true,
            height: dwidth2,
            width: 350,
            modal: true,
            buttons: {
                "OK": function() {
                    $( this ).dialog( "close" );
                }
            }
        });
    }
    return false;
}

function moveGroupDelButton(cmd){
    var idList = [];
    $('.td_type_selected3').each(function() {
        idList.push($(this).attr('id').split("G").join(""));
    });
    $('.td_type_selected4').each(function() {
        idList.push($(this).attr('id').split("G").join(""));
    });

    if (idList != null  && idList.length > 0) {
        document.forms[0].CMD.value=cmd;
        document.forms[0].moveGroups.value=idList;
        document.forms[0].submit();
    } else {
      var dwidth2 = 180;
        $('#dialog-error5').dialog({
            autoOpen: true,
            height: dwidth2,
            width: 350,
            modal: true,
            buttons: {
                "OK": function() {
                    $( this ).dialog( "close" );
                }
            }
        });
    }
    return false;
}

function moveUser(cmd){
    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}

function application(svDomName, cmd, selBoxName, groupSid, splitFlg, cmdKbn) {

    //親画面の選択ユーザを削除
    window.opener.$("[name='" + svDomName + "']").remove();
    //次画面の選択ユーザをセット
    var svName = "cmn220userSid";
    $("[name='" + svName + "']").each(function(){
        if (this.value != null && this.value != "") {
            if (splitFlg == 1) {
                this.value = this.value + "d41d8cd98f00b204e9800998ecf8427e";
            }
            window.opener.$('<input type="hidden" name="' + svDomName + '" value="' + this.value + '">').appendTo("form");
        }
    });
    //保持してたグループをセット
    var svName2 = "cmn220groupSidadd";
    $("[name='" + svName2 + "']").each(function(){
        if (this.value != null && this.value != "") {
            if (splitFlg == 1) {
                this.value = this.value + "d41d8cd98f00b204e9800998ecf8427e";
            }
            window.opener.$('<input type="hidden" name="' + svDomName + '" value="' + "G" + this.value + '">').appendTo("form");
        }
    });

    var cmdStr = "";
    if (cmdKbn == 0) {
        cmdStr = "CMD";
    } else {
        cmdStr = "cmd";
    }
    window.opener.$("[name='" + cmdStr + "']").val(cmd);
    window.opener.$("[name='" + selBoxName + "']").val(groupSid);
    window.opener.document.forms[0].submit();
    window.close();
}

function application_no_submit(svDomName, cmd, selBoxName, groupSid, splitFlg, cmdKbn,
                               formName, paramBtn) {

    //親画面の選択ユーザを削除
    window.opener.$("[name='" + svDomName + "']").remove();
    //次画面の選択ユーザをセット
    var svName = "cmn220userSid";
    $("[name='" + svName + "']").each(function(){
        if (this.value != null && this.value != "") {
            if (splitFlg == 1) {
                this.value = this.value + "d41d8cd98f00b204e9800998ecf8427e";
            }
            window.opener.$('<input type="hidden" name="' + svDomName + '" value="' + this.value + '">').appendTo(window.opener.$("#" + formName));
        }
    });
    //保持してたグループをセット
    var svName2 = "cmn220groupSidadd";
    $("[name='" + svName2 + "']").each(function(){
        if (this.value != null && this.value != "") {
            if (splitFlg == 1) {
                this.value = this.value + "d41d8cd98f00b204e9800998ecf8427e";
            }
            window.opener.$('<input type="hidden" name="' + svDomName + '" value="' + "G" + this.value + '">').appendTo(window.opener.$("#" + formName));
        }
    });

    window.opener.$("#" + paramBtn).click();
    window.close();

}

function backGroundSetting(key, typeNo) {
    if (key.checked) {
        if (typeNo == 1) {
          document.getElementById(key.value).className='td_type_selected';
        } else {
          document.getElementById(key.value).className='td_type_selected2';
        }
    } else {
        var cssName = 'td_line_color' + typeNo;
        document.getElementById(key.value).className=cssName;
    }
}

function backGroundSetting2(key, typeNo) {
    if (key.checked) {
        if (typeNo == 1) {
          document.getElementById(key.value).className='td_type_selected3';
        } else {
          document.getElementById(key.value).className='td_type_selected4';
        }
    } else {
        var cssName = 'td_line_color' + typeNo;
        document.getElementById(key.value).className=cssName;
    }
}

function chgCheckAllChange(allChkName, chkName) {
    if (document.getElementsByName(allChkName)[0].checked) {
        $(".td_line_color1").addClass("td_type_selected");
        $(".td_line_color2").addClass("td_type_selected2");
    } else {
        $(".td_type_selected").addClass("td_line_color1");
        $(".td_type_selected2").addClass("td_line_color2");
        $(".td_line_color1").removeClass("td_type_selected");
        $(".td_line_color2").removeClass("td_type_selected2");
    }
}

function chgCheckAllChange2(allChkName, chkName) {
    if (document.getElementsByName(allChkName)[0].checked) {
        $(".td_line_color3").addClass("td_type_selected3");
        $(".td_line_color4").addClass("td_type_selected4");
    } else {
        $(".td_type_selected3").addClass("td_line_color3");
        $(".td_type_selected4").addClass("td_line_color4");
        $(".td_line_color3").removeClass("td_type_selected3");
        $(".td_line_color4").removeClass("td_type_selected4");
    }
}

function clickSortTitle(sortValue) {

    if (document.forms[0].cmn220SortTopKey.value == sortValue) {
      var kbnInt = 1;
      if (document.forms[0].cmn220SortTopKbn.value == kbnInt) {
          document.forms[0].cmn220SortTopKbn.value = -1;
      } else {
          document.forms[0].cmn220SortTopKbn.value = 1;
      }
    } else {
        document.forms[0].cmn220SortTopKey.value = sortValue;
        document.forms[0].cmn220SortTopKbn.value = 1;
    }

    document.forms[0].submit();
    return false;
}

function clickSortTitle2(sortValue) {

    if (document.forms[0].cmn220SortBottomKey.value == sortValue) {
      var kbnInt = 1;
      if (document.forms[0].cmn220SortBottomKbn.value == kbnInt) {
          document.forms[0].cmn220SortBottomKbn.value = -1;
      } else {
          document.forms[0].cmn220SortBottomKbn.value = 1;
      }
    } else {
        document.forms[0].cmn220SortBottomKey.value = sortValue;
        document.forms[0].cmn220SortBottomKbn.value = 1;
    }

    document.forms[0].submit();
    return false;
}

function clickSortTitle3(sortValue) {

    if (document.forms[0].cmn220SortTopKeyGp.value == sortValue) {
      var kbnInt = 1;
      if (document.forms[0].cmn220SortTopKbnGp.value == kbnInt) {
          document.forms[0].cmn220SortTopKbnGp.value = -1;
      } else {
          document.forms[0].cmn220SortTopKbnGp.value = 1;
      }
    } else {
        document.forms[0].cmn220SortTopKeyGp.value = sortValue;
        document.forms[0].cmn220SortTopKbnGp.value = 1;
    }

    document.forms[0].submit();
    return false;
}

function clickSortTitle4(sortValue) {

    if (document.forms[0].cmn220SortBottomKeyGp.value == sortValue) {
      var kbnInt = 1;
      if (document.forms[0].cmn220SortBottomKbnGp.value == kbnInt) {
          document.forms[0].cmn220SortBottomKbnGp.value = -1;
      } else {
          document.forms[0].cmn220SortBottomKbnGp.value = 1;
      }
    } else {
        document.forms[0].cmn220SortBottomKeyGp.value = sortValue;
        document.forms[0].cmn220SortBottomKbnGp.value = 1;
    }

    document.forms[0].submit();
    return false;
}

function changeTab(cmd) {
    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}