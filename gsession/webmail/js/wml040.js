function change(usrKbn, accountMode) {
    if (accountMode == 2) {
        if(usrKbn == 0){
            $('#permissionGroup')[0].style.display="block";
            $('#permissionUser')[0].style.display="none";
        }else if(usrKbn == 1){
            $('#permissionGroup')[0].style.display="none";
            $('#permissionUser')[0].style.display="block";
        }
    }
}

function more(checkValue){
    if (checkValue == 0) {
        //基本情報
        $('#wml_settings').show();
        $('#signSettings').hide();
        $('#moreSettings').hide();
        $('#otherSettings').hide();
        $('#normal').show();
        $('#sign').hide();
        $('#receive').hide();
        $('#other').hide();

    } else if (checkValue == 1) {
        //署名情報
        $('#wml_settings').hide();
        $('#signSettings').show();
        $('#moreSettings').hide();
        $('#otherSettings').hide();
        $('#normal').hide();
        $('#sign').show();
        $('#receive').hide();
        $('#other').hide();

    } else if (checkValue == 2) {
        //送受信情報
        $('#wml_settings').hide();
        $('#signSettings').hide();
        $('#moreSettings').show();
        $('#otherSettings').hide();
        $('#normal').hide();
        $('#sign').hide();
        $('#receive').show();
        $('#other').hide();

    } else {
        //その他
        $('#wml_settings').hide();
        $('#signSettings').hide();
        $('#moreSettings').hide();
        $('#otherSettings').show();
        $('#normal').hide();
        $('#sign').hide();
        $('#receive').hide();
        $('#other').show();
    }

    document.forms[0].wml040elementKbn.value = checkValue;

}

function changeInputDiskSize(diskHnt) {
  if(diskHnt == 1){
    document.getElementById("inputDiskSize").style.display="";
  } else if(diskHnt == 0) {
    document.getElementById("inputDiskSize").style.display="none";
  }
}

function changeAutoRsvTime(autoTimeKbn) {
  if(autoTimeKbn == 1){
    document.getElementById("autoRsvTime").style.display="";
  } else if(autoTimeKbn == 0) {
    document.getElementById("autoRsvTime").style.display="none";
  }
}

function changeSendServerAuth(auth) {
  $('#wml040sendServerUser')[0].disabled = (auth != 1);
  $('#wml040sendServerPassword')[0].disabled = (auth != 1);
}

function changeCompressFile(value) {
  if (value == 2) {
    $('#compressDefArea').show();
  } else {
    $('#compressDefArea').hide();
  }
}

function changeTimesent(value) {
  if (value == 2) {
    $('#timeSentDefArea').show();
  } else {
    $('#timeSentDefArea').hide();
  }
}

function initDiskArea(adminUserFlg) {
    var permitKbn = document.getElementsByName('wml040PermitKbn')[0].value;
    var diskSizeComp = document.getElementsByName('wml040diskSizeComp')[0].value;
    if (permitKbn == 0 || diskSizeComp == 1) {
        if (adminUserFlg) {
            changeDiskSps();
        } else {
            setDiskLimitArea(0);
        }
    } else {
        setDiskLimitArea(1);
    }
}

function changeDiskSps() {
    if (document.getElementsByName('wml040diskSps')[0].checked) {
        setDiskLimitArea(1);
    } else {
        setDiskLimitArea(0);
    }
}

function setDiskLimitArea(diskKbn) {
    if (diskKbn == 1) {
        $('#diskSizeInputArea').show();
        $('#diskSizeViewArea').hide();
    } else {
        $('#diskSizeInputArea').hide();
        $('#diskSizeViewArea').show();
    }
}
