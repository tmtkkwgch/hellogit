function buttonPush(cmd) {
    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}

function setBackGroundColor(id) {
    var idname = id + 'Inp';
    if (document.getElementById(id).checked==true) {
        document.getElementById(idname).className='td_type_selected';    
    } else {
        document.getElementById(idname).className='td_type1';
    }
}

function initSettingBackGroundColor() {
    var prop1 = document.getElementById('rsv200Prop1Inp');
    if (prop1 != null) {
        setBackGroundColor('rsv200Prop1')
    }
    var prop2 = document.getElementById('rsv200Prop2Inp');
    if (prop2 != null) {
        setBackGroundColor('rsv200Prop2')
    }
    var prop3 = document.getElementById('rsv200Prop3Inp');
    if (prop3 != null) {
        setBackGroundColor('rsv200Prop3')
    }
    var prop4 = document.getElementById('rsv200Prop4Inp');
    if (prop4 != null) {
        setBackGroundColor('rsv200Prop4')
    }
    var prop5 = document.getElementById('rsv200Prop5Inp');
    if (prop5 != null) {
        setBackGroundColor('rsv200Prop5')
    }
    var prop6 = document.getElementById('rsv200Prop6Inp');
    if (prop6 != null) {
        setBackGroundColor('rsv200Prop6')
    }
    var prop7 = document.getElementById('rsv200Prop7Inp');
    if (prop7 != null) {
        setBackGroundColor('rsv200Prop7')
    }
    var biko = document.getElementById('rsv200BikoInp');
    if (biko != null) {
        setBackGroundColor('rsv200Biko')
    }
}

function changeChk(){

   var chkFlg;
   if (document.forms[0].allCheck.checked) {
       chkFlg = true;
   } else {
       chkFlg = false;
   }

   targetArray = document.getElementsByName("rsv200TargetSisetu");
   for(i = 0; i < targetArray.length; i++) {
       targetArray[i].checked = chkFlg;
   }
}