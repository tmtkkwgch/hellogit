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
    document.forms[0].cmn230groupSid.value=gpSid;
    document.forms[0].cmn230SearchFlg.value=0;
    document.forms[0].cmn230SearchStr.value="";
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

function moveUserDelButton2(cmd){
    var idList = [];
    $('.td_type_selected5').each(function() {
        idList.push($(this).attr('id'));
    });
    $('.td_type_selected6').each(function() {
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

function moveUser(cmd){
    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}

function moveGroupButton(cmd, moveSid){
    var moveGroups=[];
    if (moveSid != null && moveSid != "" ) {
        moveGroups.push(moveSid);
    }

    document.forms[0].CMD.value=cmd;
    document.forms[0].moveGroupSid.value=moveGroups;
    document.forms[0].submit();
    return false;
}


function moveGroupAddButton(cmd){
    var idList = [];
    $('.td_type_selected').each(function() {
        idList.push($(this).attr('id'));
    });
    $('.td_type_selected2').each(function() {
        idList.push($(this).attr('id'));
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
        idList.push($(this).attr('id'));
    });
    $('.td_type_selected4').each(function() {
        idList.push($(this).attr('id'));
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

function moveGroupDelButton2(cmd){
    var idList = [];
    $('.td_type_selected5').each(function() {
        idList.push($(this).attr('id'));
    });
    $('.td_type_selected6').each(function() {
        idList.push($(this).attr('id'));
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

function moveGroup(cmd){
    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}

function application(svDomName, svDomName2, cmd, selBoxName, groupSid, splitFlg, cmdKbn) {

    //親画面の選択(上)ユーザを削除
    window.opener.$("[name='" + svDomName + "']").remove();
    //親画面の選択(下)ユーザを削除
    window.opener.$("[name='" + svDomName2 + "']").remove();

    //次画面の選択ユーザ(上)をセット
    var svName = "cmn230userSid";
    $("[name='" + svName + "']").each(function(){
        if (this.value != null && this.value != "") {
            if (splitFlg == 1) {
                this.value = this.value + "d41d8cd98f00b204e9800998ecf8427e";
            }
            window.opener.$('<input type="hidden" name="' + svDomName + '" value="' + this.value + '">').appendTo("form");
        }
    });
    //保持してたグループをセット(上)
    var svName2 = "cmn230groupSidadd";
    $("[name='" + svName2 + "']").each(function(){
        if (this.value != null && this.value != "") {
            if (splitFlg == 1) {
                this.value = this.value + "d41d8cd98f00b204e9800998ecf8427e";
            }
            window.opener.$('<input type="hidden" name="' + svDomName + '" value="' + "G" + this.value + '">').appendTo("form");
        }
    });

    //次画面の選択ユーザ(下)をセット
    var svName3 = "cmn230userSid2";
    $("[name='" + svName3 + "']").each(function(){
        if (this.value != null && this.value != "") {
            if (splitFlg == 1) {
                this.value = this.value + "d41d8cd98f00b204e9800998ecf8427e";
            }
            window.opener.$('<input type="hidden" name="' + svDomName2 + '" value="' + this.value + '">').appendTo("form");
        }
    });
    //保持してたグループをセット(下)
    var svName4 = "cmn230groupSidadd2";
    $("[name='" + svName4 + "']").each(function(){
        if (this.value != null && this.value != "") {
            if (splitFlg == 1) {
                this.value = this.value + "d41d8cd98f00b204e9800998ecf8427e";
            }
            window.opener.$('<input type="hidden" name="' + svDomName2 + '" value="' + "G" + this.value + '">').appendTo("form");
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

function backGroundSetting3(key, typeNo) {
    if (key.checked) {
        if (typeNo == 1) {
          document.getElementById(key.value).className='td_type_selected5';
        } else {
          document.getElementById(key.value).className='td_type_selected6';
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

function chgCheckAllChange3(allChkName, chkName) {
    if (document.getElementsByName(allChkName)[0].checked) {
        $(".td_line_color5").addClass("td_type_selected5");
        $(".td_line_color6").addClass("td_type_selected6");
    } else {
        $(".td_type_selected5").addClass("td_line_color5");
        $(".td_type_selected6").addClass("td_line_color6");
        $(".td_line_color5").removeClass("td_type_selected5");
        $(".td_line_color6").removeClass("td_type_selected6");
    }
}

function clickSortTitle(sortValue) {

    if (document.forms[0].cmn230SortTopKey.value == sortValue) {
      var kbnInt = 1;
      if (document.forms[0].cmn230SortTopKbn.value == kbnInt) {
          document.forms[0].cmn230SortTopKbn.value = -1;
      } else {
          document.forms[0].cmn230SortTopKbn.value = 1;
      }
    } else {
        document.forms[0].cmn230SortTopKey.value = sortValue;
        document.forms[0].cmn230SortTopKbn.value = 1;
    }

    document.forms[0].submit();
    return false;
}

function clickSortTitle2(sortValue) {

    if (document.forms[0].cmn230SortBottomKey.value == sortValue) {
      var kbnInt = 1;
      if (document.forms[0].cmn230SortBottomKbn.value == kbnInt) {
          document.forms[0].cmn230SortBottomKbn.value = -1;
      } else {
          document.forms[0].cmn230SortBottomKbn.value = 1;
      }
    } else {
        document.forms[0].cmn230SortBottomKey.value = sortValue;
        document.forms[0].cmn230SortBottomKbn.value = 1;
    }

    document.forms[0].submit();
    return false;
}

function clickSortTitle3(sortValue) {

    if (document.forms[0].cmn230SortBottomKey2.value == sortValue) {
      var kbnInt = 1;
      if (document.forms[0].cmn230SortBottomKbn2.value == kbnInt) {
          document.forms[0].cmn230SortBottomKbn2.value = -1;
      } else {
          document.forms[0].cmn230SortBottomKbn2.value = 1;
      }
    } else {
        document.forms[0].cmn230SortBottomKey2.value = sortValue;
        document.forms[0].cmn230SortBottomKbn2.value = 1;
    }

    document.forms[0].submit();
    return false;
}

function clickSortTitle4(sortValue) {

    if (document.forms[0].cmn230SortTopKeyGp.value == sortValue) {
      var kbnInt = 1;
      if (document.forms[0].cmn230SortTopKbnGp.value == kbnInt) {
          document.forms[0].cmn230SortTopKbnGp.value = -1;
      } else {
          document.forms[0].cmn230SortTopKbnGp.value = 1;
      }
    } else {
        document.forms[0].cmn230SortTopKeyGp.value = sortValue;
        document.forms[0].cmn230SortTopKbnGp.value = 1;
    }

    document.forms[0].submit();
    return false;
}

function clickSortTitle5(sortValue) {

    if (document.forms[0].cmn230SortBottomKeyGp.value == sortValue) {
      var kbnInt = 1;
      if (document.forms[0].cmn230SortBottomKbnGp.value == kbnInt) {
          document.forms[0].cmn230SortBottomKbnGp.value = -1;
      } else {
          document.forms[0].cmn230SortBottomKbnGp.value = 1;
      }
    } else {
        document.forms[0].cmn230SortBottomKeyGp.value = sortValue;
        document.forms[0].cmn230SortBottomKbnGp.value = 1;
    }

    document.forms[0].submit();
    return false;
}

function clickSortTitle6(sortValue) {

    if (document.forms[0].cmn230SortBottomKeyGp2.value == sortValue) {
      var kbnInt = 1;
      if (document.forms[0].cmn230SortBottomKbnGp2.value == kbnInt) {
          document.forms[0].cmn230SortBottomKbnGp2.value = -1;
      } else {
          document.forms[0].cmn230SortBottomKbnGp2.value = 1;
      }
    } else {
        document.forms[0].cmn230SortBottomKeyGp2.value = sortValue;
        document.forms[0].cmn230SortBottomKbnGp2.value = 1;
    }

    document.forms[0].submit();
    return false;
}

function changeTab(cmd) {
    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}