function file250TreeClick(cmd, sid) {
    document.forms[0].CMD.value=cmd;
    document.forms[0].fil250DirSid.value=sid;
    document.forms[0].submit();
    return false;
}


function allSelectUser() {

    var flg = true;
   if (document.forms[0].fil250callUserAllSlt.checked) {
       flg = true;
   } else {
       flg = false;
   }

   var defUserAry = document.forms[0].fil250callUserRight.options;
   var defLength = defUserAry.length;
   for (i = defLength - 1; i >= 0; i--) {
       if (defUserAry[i].value != -1) {
           defUserAry[i].selected = flg;
       }
   }
}
