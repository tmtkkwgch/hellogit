function changeCmdMode(cmdMode) {
    document.forms[0].adr010cmdMode.value = cmdMode;
    document.forms[0].adr010searchFlg.value = 0;
    document.forms[0].adr010InitDspContactFlg.value = 0;
    buttonPush('init');
}

function changeCmdModeForContact(cmdMode, sortKey, order) {

    if (document.forms[0].adr010cmdMode.value != cmdMode) {
        document.forms[0].adr010sortKey.value = sortKey;
        document.forms[0].adr010orderKey.value = order;
    }
    changeCmdMode(cmdMode);
}

function searchToKana(kana) {
    document.forms[0].adr010SearchKana.value = kana;

    buttonPush('search');
}

function searchToComKana(kana) {
    document.forms[0].adr010SearchComKana.value = kana;
    buttonPush('searchCompanyInit');
}

function sort(inputSortKey) {

    var sortKey = $("input:hidden[name='adr010sortKey']").val();
    var orderKey = $("input:hidden[name='adr010orderKey']").val();
    if (sortKey == inputSortKey) {
        if (orderKey == 1) {
            $("input:hidden[name='adr010orderKey']").val(0);
        } else {
            $("input:hidden[name='adr010orderKey']").val(1);
        }
    } else {
        $("input:hidden[name='adr010orderKey']").val(0);
    }

    $("input:hidden[name='adr010sortKey']").val(inputSortKey);
    buttonPush('init');
}

function editAddress(procMode, adrSid) {
    document.forms[0].adr020ProcMode.value = procMode;
    document.forms[0].adr010EditAdrSid.value = adrSid;

    buttonPush('editAdrData');
}

function viewCompany(acoSid) {
    document.forms[0].adr100backFlg.value = 2;
    document.forms[0].adr110editAcoSid.value = acoSid;

    buttonPush('viewCompany');
}

function viewContact(adrSid) {
    document.forms[0].adr010EditAdrSid.value = adrSid;

    buttonPush('contact');
}

function changePage(pageCmbName) {
    setPageParam('adr010page', pageCmbName);

    buttonPush('init');
}

function labelCheck(id) {

    var chkObj = document.forms[0].adr010searchLabel;
    if (typeof chkObj.length == "undefined") {
        chkObj.checked = "true";
    } else {
        for (i = 0; i < chkObj.length; i++) {
            if (id == chkObj[i].value) {
                if (chkObj[i].checked) {
                    chkObj[i].checked = false;
                } else {
                    chkObj[i].checked = true;
                }
                break;
            }
        }
    }

    var dspMode = $("input:hidden[name='adr010cmdMode']").val();
    if (dspMode == 0) {
        buttonPush('searchCompanyInit');
    } else {
        buttonPush('search');
    }

}

function changeChk(){
   var chkFlg;
   if (document.forms[0].allCheck.checked) {
       chkFlg = true;
   } else {
       chkFlg = false;
   }
   delAry = document.getElementsByName("adr010selectSid");
   for(i = 0; i < delAry.length; i++) {
       delAry[i].checked = chkFlg;
   }
}

function changCcategoryCombo(){
    document.forms[0].CMD.value='init';
    document.forms[0].submit();
    return false;
}


YAHOO.namespace("labelbox");

function init() {
  YAHOO.labelbox.labelPanel = new YAHOO.widget.Panel("labelPanel", { width:"400px", fixedcenter:true, visible:false, constraintoviewport:true, close:false } );
  YAHOO.labelbox.labelPanel.render();
}

YAHOO.util.Event.addListener(window, "load", init);

function openlabel(){
  checkAdd = document.getElementsByName("adr010selectSid");
  var chkCount = 0;
  for(i = 0; i < checkAdd.length; i++) {
    if (checkAdd[i].checked == false) {
      chkCount = chkCount + 1;
    }
  }
  if (checkAdd.length == chkCount) {
    alert("アドレスを選択してください。");
  } else {
    var labLoc = '../address/adr260.do?adr260parentLabelName=adr010selectLabelSid&adrSid=';
    for (n = 0; n < checkAdd.length; n++ ) {
      labLoc += checkAdd[n] + ',';
    }
    lab.location = labLoc
    YAHOO.labelbox.labelPanel.show();
  }

  return false;
}

function adr010DateKbn() {
    if (document.forms[0].adr010dateNoKbn.checked == true) {
        document.forms[0].adr010SltYearFrContact.disabled=true;
        document.forms[0].adr010SltMonthFrContact.disabled=true;
        document.forms[0].adr010SltDayFrContact.disabled=true;
        document.forms[0].adr010FrCalBtn.disabled=true;
        document.forms[0].adr010SltYearToContact.disabled=true;
        document.forms[0].adr010SltMonthToContact.disabled=true;
        document.forms[0].adr010SltDayToContact.disabled=true;
        document.forms[0].adr010ToCalBtn.disabled=true;
    } else {
        document.forms[0].adr010SltYearFrContact.disabled=false;
        document.forms[0].adr010SltMonthFrContact.disabled=false;
        document.forms[0].adr010SltDayFrContact.disabled=false;
        document.forms[0].adr010FrCalBtn.disabled=false;
        document.forms[0].adr010SltYearToContact.disabled=false;
        document.forms[0].adr010SltMonthToContact.disabled=false;
        document.forms[0].adr010SltDayToContact.disabled=false;
        document.forms[0].adr010ToCalBtn.disabled=false;
    }
}

function addAddress(mode, adrSid, cmd) {
    return addAddressExt(mode, adrSid, cmd, 0);
}

function addAddressExt(mode, adrSid, cmd, type) {
    document.forms[0].adr010SendMailMode.value = mode;
    if (adrSid > 0) {
        document.forms[0].adr010AdrSid.value = adrSid;
        document.forms[0].adr010AdrType.value = type;
    }

    buttonPush(cmd);
}

function deleteSend(mode, usrSid, kbn) {
    document.forms[0].CMD.value='deleteSend';
    document.forms[0].adr010SendMailMode.value=mode;
    document.forms[0].adr010DelAdrSid.value=usrSid;
    document.forms[0].adr010AdrType.value = kbn;
    document.forms[0].submit();
    return false;
}

$(function() {

    /* 表示モード */
    var dspMode = $("input:hidden[name='adr010cmdMode']").val();
    if (dspMode == 0) {
        //グループ
        $("#0").addClass("sel_menu_title_select");
    } else if (dspMode == 1) {
        //詳細
        $("#1").addClass("sel_menu_title_select");
    } else if (dspMode == 2) {
        //詳細
        $("#2").addClass("sel_menu_title_select");
    } else if (dspMode == 3) {
        //詳細
        $("#3").addClass("sel_menu_title_select");
    } else if (dspMode == 4) {
        //詳細
        $("#4").addClass("sel_menu_title_select");
    } else {
        //詳細
        $("#5").addClass("sel_menu_title_select");
    }


    /* メニュー  hover*/
    $('.sel_menu_title').hover(
        function () {
            $(this).addClass("sel_menu_title_on");
        },
        function () {
            $(this).removeClass("sel_menu_title_on");
        }
    );

    /* 画面切り替え  hover*/
    $('.td_change_pop_not_sel').hover(
        function () {
            $(this).addClass("td_change_pop_not_sel_on");
        },
        function () {
            $(this).removeClass("td_change_pop_not_sel_on");
        }
    );

    /* メニュークリック */
    $('.sel_menu_title').live('click', function() {
        var selectMenu = $(this).attr('id');
        /* submitするかチェックする */
        if (submitCheck(selectMenu)) {
            if (selectMenu == "5") {
                changeCmdModeForContact(selectMenu, 5, 1);
            } else {
                changeCmdMode(selectMenu);
            }
        }
    });

    var openFlgs =  $("input:hidden[name='adr010CategoryOpenFlg']");
    for(var i=0, l=openFlgs.length; i<l; i++) {
        var dataId = "#" + "category_data_" + i;
        var headId = "#" + "category_head_" + i;
        if (openFlgs[i].value == 1) {
            $(dataId).show();
            $(headId).removeClass("menu_head_not_sel");
            $(headId).addClass("menu_head_sel");
        } else {
            $(dataId).hide();
            $(headId).removeClass("menu_head_sel");
            $(headId).addClass("menu_head_not_sel");
        }
    }
})

function submitCheck(selectMenu) {
    //表示モード
    var dspMode = $("input:hidden[name='adr010cmdMode']").val();

    if ((dspMode == 0 && selectMenu == "0")
            || (dspMode == 1 && selectMenu == "1")
            || (dspMode == 2 && selectMenu == "2")
            || (dspMode == 3 && selectMenu == "3")
            || (dspMode == 4 && selectMenu == "4")
            || (dspMode == 5 && selectMenu == "5")) {
        return false;
    } else {
        return true;
    }
}

function changeDspCategory(catId) {

    var dataId = "#" + "category_data_" + catId;
    var headId = "#" + "category_head_" + catId;

    $(dataId).animate( { height: 'toggle'}, 'middle' );
    changeSelImg($(headId));


    var now = $("input:hidden[name='adr010CategoryOpenFlg']");

    if (now[catId].value == 1) {
        now[catId].value = 0;
    } else {
        now[catId].value = 1;
    }

}

function changeSelImg(id) {
    if (id.hasClass("menu_head_not_sel")) {
        id.removeClass("menu_head_not_sel");
        id.addClass("menu_head_sel");
    } else {
        id.removeClass("menu_head_sel");
        id.addClass("menu_head_not_sel");
    }
}

function changePopup() {
    var url = "../user/usr040.do?usr040webmail=1";
    url += "&usr040webmailAddress=" + document.getElementsByName('adr010webmailAddress')[0].value;
    url += "&usr040webmailType=" + document.getElementsByName('adr010webmailType')[0].value;
    location.href = url;
    return true;
}