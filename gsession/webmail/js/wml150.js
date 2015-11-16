function more(checkValue){
    if (checkValue == 0) {
        //基本設定
        $('#normal_settings').show();
        $('#detail_settings').hide();
        $('#normal').show();
        $('#detail').hide();

    } else if (checkValue == 1) {
        //詳細設定
        $('#normal_settings').hide();
        $('#detail_settings').show();
        $('#normal').hide();
        $('#detail').show();
    }

    document.forms[0].wml150elementKbn.value = checkValue;

}

function changeAcntMakeKbn(acntMakeKbn) {
  if(acntMakeKbn == 1){
    document.getElementById("settingServerArea").style.display="";
  } else {
    document.getElementById("settingServerArea").style.display="none";
  }
}

function changeInputDiskSize(diskHnt) {
  var ctext = $('#lmtinput1')[0];
  if(diskHnt == 1){
    document.getElementById("inputDiskSize").style.display="";
    if (!document.forms[0].wml150permitKbn[1].checked) {
      document.getElementById("inputDiskSize2").style.display="none";
      changeStyle(ctext, 'wml_description_text_notdsp');
    } else {
      document.getElementById("inputDiskSize2").style.display="";
      changeStyle(ctext, 'wml_description_text_dsp');
      changeDefLabel();
    }
  } else if(diskHnt == 0) {
    document.getElementById("inputDiskSize").style.display="none";
    document.getElementById("inputDiskSize2").style.display="none";
    if (document.forms[0].wml150permitKbn[1].checked) {
        changeStyle(ctext, 'wml_description_text_dsp');
    }
  }
}

function changeAutoRsvTime(autoTimeKbn) {
  if(autoTimeKbn == 1){
    document.getElementById("autoRsvTime").style.display="";
  } else if(autoTimeKbn == 0) {
    document.getElementById("autoRsvTime").style.display="none";
  }
}

function lmtEnableDisable() {
    var impInputSize = 10;

    if (document.forms[0].wml150permitKbn[1].checked) {
        for (cnt = 1; cnt <= impInputSize; cnt++) {
          changeStyle($('#lmtinput' + cnt)[0], 'wml_description_text_dsp');
        }

        if ($('#disk2').is(':checked')) {
          document.getElementById("inputDiskSize2").style.display="";
          changeDefLabel();
        }
    } else {
        for (cnt = 1; cnt <= impInputSize; cnt++) {
          changeStyle($('#lmtinput' + cnt)[0], 'wml_description_text_notdsp');
        }

        document.getElementById("inputDiskSize2").style.display="none";
    }
}

function changeDefLabel() {
    var ctext = $('#lmtinput1')[0];

    if (!document.forms[0].wml150permitKbn[1].checked) {
        changeStyle(ctext, 'wml_description_text_notdsp');
    } else {
        if ($('#sizeComp').is(':checked')) {
            changeStyle(ctext, 'wml_description_text_notdsp');
        } else {
            changeStyle(ctext, 'wml_description_text_dsp');
        }
    }
}

function changeSendMaxSize(hnt) {
  if (hnt == 1){
    $('#sendMaxSize').show();
  } else {
    $('#sendMaxSize').hide();
  }
}

function changeFwLimit(fwLimitKbn) {
  if (fwLimitKbn == 1){
    $('#fwLimitArea').show();
    $('#fwLimitDeleteArea').hide();
  } else if (fwLimitKbn == 2){
    $('#fwLimitArea').hide();
    $('#fwLimitDeleteArea').show();
  } else {
    $('#fwLimitArea').hide();
    $('#fwLimitDeleteArea').hide();
  }
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

function wml150changeBcc(bcc) {
  if (bcc == 1){
    $('#bccThresholdArea').show();
  } else {
    $('#bccThresholdArea').hide();
  }
}

function wml150changeWarnDisk(warnDisk) {
  if (warnDisk == 1){
    $('#warnDiskThresholdArea').show();
  } else {
    $('#warnDiskThresholdArea').hide();
  }
}

function wml150Init(acntMakeKbn, moreKbn, inputDiskKbn, rsvTimeKbn, maxSizeSendKbn, fwLimitKbn, fwLimitCheck, bcc, warnDisk) {
  lmtEnableDisable();
  changeAcntMakeKbn(acntMakeKbn);
  more(moreKbn);
  changeInputDiskSize(inputDiskKbn);
  changeAutoRsvTime(rsvTimeKbn);
  changeDefLabel();
  changeSendMaxSize(maxSizeSendKbn);
  wml150changeBcc(bcc);
  wml150changeWarnDisk(warnDisk);
  changeFwLimit(fwLimitKbn);
  if (fwLimitCheck == 1) {
    var targetOffset = $('#fwLimitLine').offset().top;
    $('html,body').animate({scrollTop: targetOffset},50);
  }
}
