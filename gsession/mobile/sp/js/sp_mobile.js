function buttonPush(cmd){
    document.forms[0].CMD.value=cmd
    document.forms[0].submit();
    return false;
}

function buttonPush2(cmd){
    document.forms[0].BCMD.value=cmd
    document.forms[0].submit();
    return false;
}

function buttonPush3(cmd, wmlNum){
    document.forms[0].CMD.value=cmd
    document.forms[0].wmlMailNum.value=wmlNum
    document.forms[0].submit();
    return false;
}

function changeGroupCombo(){
    document.forms[0].cmd.value='';
    document.forms[0].CMD.value='';
    document.forms[0].submit();
    return false;
}

function changeCombo(){
    document.forms[0].submit();
    return false;
}

function changeCombo2(cmd){
    document.forms[0].CMD.value=cmd
    document.forms[0].submit();
    return false;
}

function changeDate(cmd){
    document.forms[0].CMD.value=cmd
    document.forms[0].submit();
    return false;
}

function addUser(id){
    document.forms[0].id.value=id;
    document.forms[0].submit();
    return false;
}

function addUser2(id){
    document.forms[0].adId.value=id;
    document.forms[0].submit();
    return false;
}

function addRsv(id){
    document.forms[0].rsvId.value=id;
    document.forms[0].submit();
    return false;
}

function addAnken(id){
    document.forms[0].ankenId.value=id;
    document.forms[0].submit();
    return false;
}

function clickGj(id){
    document.forms[0].gjId.value=id;
    document.forms[0].submit();
    return false;
}

function clickComp(id){
    document.forms[0].companyId.value=id;
    document.forms[0].submit();
    return false;
}

function kanaTopBtn(cmd, selectInd, searchInd, searchTopFlg){
    document.forms[0].CMD.value=cmd;
    document.forms[0].adr010SelectInd.value=selectInd;
    document.forms[0].adr010SearchInd.value=searchInd;
    document.forms[0].adr010SearchTopFlg.value=searchTopFlg;
    document.forms[0].submit();
    return false;
}

function kanaBtn(cmd, searchInd){
    document.forms[0].CMD.value=cmd;
    document.forms[0].adr010SearchInd.value=searchInd;
    document.forms[0].submit();
    return false;
}

function kanaBtn2(cmd, searchInd, searchKanaFlg){
    document.forms[0].CMD.value=cmd;
    document.forms[0].adr010SearchInd.value=searchInd;
    document.forms[0].adr010SearchTopFlg.value=0;
    document.forms[0].adr010SearchKanaFlg.value=searchKanaFlg;
    document.forms[0].submit();
    return false;
}

function popupMenu(){
    if($('#popupmenu')[0].style.display == 'none'){
      $('#popupmenu')[0].style.display="block";
    } else {
      $('#popupmenu')[0].style.display="none";
    }
}

function rsvSchUsrChange(theme) {
    var radioFont = "#7d7d7d";
    var radioFontActive = "#ffffff";
    if (theme == 'a') {
        radioFont = "#75a0bf";
        radioFontActive = "#ffffff";
    } else if (theme == 'b') {
        radioFont = "#7d7d7d";
        radioFontActive = "#ffffff";
    } else if (theme == 'c') {
        radioFont = "#d3c5c5";
        radioFontActive = "#000000";
    } else if (theme == 'd') {
        radioFont = "#d3c5c5";
        radioFontActive = "#000000";
    } else if (theme == 'e') {
        radioFont = "#cacc8e";
        radioFontActive = "#000000";
    }
    $('#rsvSchUsrBtn').css('color', radioFontActive);
    $('#rsvSchGrpBtn').css('color', radioFont);
    $('#schUsrAddArea')[0].style.display="block";
    $('#schGrpAddArea')[0].style.display="none";
    $('#schUsrText')[0].style.display="block";
    $('#schGrpText')[0].style.display="none";
}

function rsvSchGrpChange(theme) {
    var radioFont = "#7d7d7d";
    var radioFontActive = "#ffffff";
    if (theme == 'a') {
        radioFont = "#75a0bf";
        radioFontActive = "#ffffff";
    } else if (theme == 'b') {
        radioClass = "ui-btn-active_black";
        radioFont = "#7d7d7d";
        radioFontActive = "#ffffff";
    } else if (theme == 'c') {
        radioClass = "ui-btn-active_gray";
        radioFont = "#d3c5c5";
        radioFontActive = "#000000";
    } else if (theme == 'd') {
        radioClass = "ui-btn-active_white";
        radioFont = "#d3c5c5";
        radioFontActive = "#000000";
    } else if (theme == 'e') {
        radioClass = "ui-btn-active_orange";
        radioFont = "#cacc8e";
        radioFontActive = "#000000";
    }
    $('#rsvSchUsrBtn').css('color', radioFont);
    $('#rsvSchGrpBtn').css('color', radioFontActive);
    $('#schUsrAddArea')[0].style.display="none";
    $('#schGrpAddArea')[0].style.display="block";
    $('#schUsrText')[0].style.display="none";
    $('#schGrpText')[0].style.display="block";
}

function popupRsvData(id){
    if($('#' + id)[0].style.display == 'none'){
      $('#' + id)[0].style.display="block";
    } else {
      $('#' + id)[0].style.display="none";
    }
}

function timeShiftRight(toEnd){

   var cnt = eval($("#startNum").text());
   var end = cnt + 8;
   if (cnt < (toEnd - 7) ) {
     $("." + cnt).css("display","none");
     $("." + end).css("display","block");
     $("#startNum").text(cnt + 1);
   }
}

function timeShiftLeft(start){

   var cnt = eval($("#startNum").text());
   var end = cnt + 7;
   var str = cnt - 1;
   if (cnt > start) {
     $("." + str).css("display","block");
     $("." + end).css("display","none");
     $("#startNum").text(cnt - 1);
   }
}

function moveSisetuAdd(ymd, sisetuSid) {

    document.forms[0].CMD.value='sisetu_add';
    document.forms[0].rsvSelectedDate.value=ymd;
    document.forms[0].rsvSelectedSisetuSid.value=sisetuSid;
    document.forms[0].submit();
    return false;
}

function moveSisetuAdd2(ymd, sisetuSid, frTime) {

    document.forms[0].CMD.value='sisetu_add';
    document.forms[0].rsvSelectedDate.value=ymd;
    document.forms[0].rsvSelectedSisetuSid.value=sisetuSid;
    document.forms[0].rsvSelectedHourFr.value = parseInt(frTime);
    document.forms[0].rsvSelectedHourTo.value = parseInt(frTime) + 1;
    document.forms[0].submit();
    return false;
}

function moveSisetuEdit(yoyakuSid) {

    document.forms[0].CMD.value='sisetu_edit';
    document.forms[0].rsvSelectedYoyakuSid.value=yoyakuSid;
    document.forms[0].submit();
    return false;
}

function chacord(){
  if (document.forms[0].acordParam.value == 'true' ) {
    document.forms[0].acordParam.value = 'false';
  } else {
    document.forms[0].acordParam.value = 'true';
  }
}

function chRsvYes(theme){
    var radioFont = "#7d7d7d";
    var radioFontActive = "#ffffff";
    if (theme == 'a') {
        radioFont = "#75a0bf";
        radioFontActive = "#ffffff";
    } else if (theme == 'b') {
        radioClass = "ui-btn-active_black";
        radioFont = "#7d7d7d";
        radioFontActive = "#ffffff";
    } else if (theme == 'c') {
        radioClass = "ui-btn-active_gray";
        radioFont = "#d3c5c5";
        radioFontActive = "#000000";
    } else if (theme == 'd') {
        radioClass = "ui-btn-active_white";
        radioFont = "#d3c5c5";
        radioFontActive = "#000000";
    } else if (theme == 'e') {
        radioClass = "ui-btn-active_orange";
        radioFont = "#cacc8e";
        radioFontActive = "#000000";
    }

    $('#rsvYes').css('color', radioFont);
    $('#rsvNo').css('color', radioFontActive);
}

function chRsvNo(theme){
    var radioFont = "#7d7d7d";
    var radioFontActive = "#ffffff";
    if (theme == 'a') {
        radioFont = "#75a0bf";
        radioFontActive = "#ffffff";
    } else if (theme == 'b') {
        radioClass = "ui-btn-active_black";
        radioFont = "#7d7d7d";
        radioFontActive = "#ffffff";
    } else if (theme == 'c') {
        radioClass = "ui-btn-active_gray";
        radioFont = "#d3c5c5";
        radioFontActive = "#000000";
    } else if (theme == 'd') {
        radioClass = "ui-btn-active_white";
        radioFont = "#d3c5c5";
        radioFontActive = "#000000";
    } else if (theme == 'e') {
        radioClass = "ui-btn-active_orange";
        radioFont = "#cacc8e";
        radioFontActive = "#000000";
    }
    $('#rsvNo').css('color', radioFont);
    $('#rsvYes').css('color', radioFontActive);
}

function delPush(id){
    document.forms[0].delId.value=id
    document.forms[0].submit();
    return false;
}

function chMemoYes(theme){
    var radioFont = "#7d7d7d";
    var radioFontActive = "#ffffff";
    if (theme == 'a') {
        radioFont = "#75a0bf";
        radioFontActive = "#ffffff";
    } else if (theme == 'b') {
        radioClass = "ui-btn-active_black";
        radioFont = "#7d7d7d";
        radioFontActive = "#ffffff";
    } else if (theme == 'c') {
        radioClass = "ui-btn-active_gray";
        radioFont = "#d3c5c5";
        radioFontActive = "#000000";
    } else if (theme == 'd') {
        radioClass = "ui-btn-active_white";
        radioFont = "#d3c5c5";
        radioFontActive = "#000000";
    } else if (theme == 'e') {
        radioClass = "ui-btn-active_orange";
        radioFont = "#cacc8e";
        radioFontActive = "#000000";
    }

    $('#memoYes').css('color', radioFont);
    $('#memoNo').css('color', radioFontActive);
    $('#memoAddArea')[0].style.display="none";
}

function chMemoNo(theme){
    var radioFont = "#7d7d7d";
    var radioFontActive = "#ffffff";
    if (theme == 'a') {
        radioFont = "#75a0bf";
        radioFontActive = "#ffffff";
    } else if (theme == 'b') {
        radioClass = "ui-btn-active_black";
        radioFont = "#7d7d7d";
        radioFontActive = "#ffffff";
    } else if (theme == 'c') {
        radioClass = "ui-btn-active_gray";
        radioFont = "#d3c5c5";
        radioFontActive = "#000000";
    } else if (theme == 'd') {
        radioClass = "ui-btn-active_white";
        radioFont = "#d3c5c5";
        radioFontActive = "#000000";
    } else if (theme == 'e') {
        radioClass = "ui-btn-active_orange";
        radioFont = "#cacc8e";
        radioFontActive = "#000000";
    }
    $('#memoNo').css('color', radioFont);
    $('#memoYes').css('color', radioFontActive);
    $('#memoAddArea')[0].style.display="block";
}

function delToUserPush(id){
    document.forms[0].CMD.value='to_user_del';
    document.forms[0].sml040DelUsrId.value=id
    document.forms[0].submit();
    return false;
}

function delCcUserPush(id){
    document.forms[0].CMD.value='cc_user_del';
    document.forms[0].sml040DelUsrId.value=id
    document.forms[0].submit();
    return false;
}

function delBccUserPush(id){
    document.forms[0].CMD.value='bcc_user_del';
    document.forms[0].sml040DelUsrId.value=id
    document.forms[0].submit();
    return false;
}

function schAttendPopup(){

    if ($('#mailAttend')[0].style.display == 'none') {
      $('#mailAttend')[0].style.display="block";
    } else {
      $('#mailAttend')[0].style.display="none";
    }
}