function changeCombo(){
    document.forms[0].CMD.value='';
    document.forms[0].submit();
    return false;
}

function createUserElement(userSid) {
      id = 'elKey-' + getElementCount(1);
      setElementNameHtml(0, userSid, id);
      return false;
}

function createRsvElement(rsvSid) {
      id = 'elKey-' + getElementCount(1);
      setElementNameHtml(1, rsvSid, id);
      return false;
}

function createTxtElement() {
      msg = document.forms[0].commentValue.value;

      if (trim(msg).length < 1) {
        alert('コメントを入力してください。');
      } else {
        id = 'elKey-' + getElementCount(1);
        htmlStr = '<div id="' +
        id +
        '" class="can_drop" style="' +
        'width:0px; height:0px; border-width:0px; z-index:2000;">' +
        '<span class="zsk_msg">' +
        msg +
        '</span></div>';
        setElementHtml(2, 0, id, htmlStr, msg);
        document.forms[0].commentValue.value = "";
      }
      return false;
}

function getElementCount(plus) {
    var objList = YAHOO.util.Dom.getElementsByClassName("can_drop","div");
    var ret = 0;
    var keyValue = "0";
    for (i=0; i < objList.length; i++) {
      keyValue = objList[i].id;
      subStr = keyValue.substring(6, keyValue.length);
      if (ret < parseInt(subStr)) {
        ret = parseInt(subStr);
      }
    }
    return parseInt(ret) + parseInt(plus);
}

function trim(argValue){
    return String(argValue).replace(/^[ 　]*/gim, "").replace(/[ 　]*$/gim, "");

}


