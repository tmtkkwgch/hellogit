function buttonPush(cmd){

    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}

function selectGroup() {
    document.forms[0].CMD.value = 'changeGrp';
    document.forms[0].submit();
    return false;
}

function changeInitArea() {

//  var initKbn = document.getElementsByName('bbs030templateKbn')[0].checked;

  var initKbn = Number($("input:radio[name=bbs030templateKbn]:checked").val());


  if (initKbn == 1) {
    $('#templateArea').show();
  } else {
    $('#templateArea').hide();
  }

}

function changeInputDiskSize(diskHnt) {
  if(diskHnt == 1){
    document.getElementById("inputDiskSize").style.display="";
    document.getElementById("warnDiskArea").style.display="";
  } else if(diskHnt == 0) {
    document.getElementById("inputDiskSize").style.display="none";
    document.getElementById("warnDiskArea").style.display="none";
  }
}

function changeWarnDisk(warnDisk) {
  if (warnDisk == 1){
    $('#warnDiskThresholdArea').show();
  } else {
    $('#warnDiskThresholdArea').hide();
  }
}

$(function() {

    //掲示期間 設定区分 変更時
    $("input:radio[name=bbs030Limit]").live('change', function(){
        if ($(this).val() == 1) {
            $('#inputLimitDate').show();
        } else {
            $('#inputLimitDate').hide();
        }
    });

    //スレッド保存期間設定 変更時
    $("input:radio[name=bbs030Keep]").live('change', function(){
        if ($(this).val() == 1) {
            $('#inputKeepDate').show();
        } else {
            $('#inputKeepDate').hide();
        }
    });

    //初期処理
    doInit();
})

function doInit() {

    var limitdisableKbn = Number($("input:radio[name=bbs030LimitDisable]:checked").val());
    if (limitdisableKbn == 1) {
        $('#inputLimitEnableLimit').show();
        $('#inputLimitEnableDate').show();
    } else {
        $('#inputLimitEnableLimit').hide();
        $('#inputLimitEnableDate').hide();
    }

    var limitKbn = Number($("input:radio[name=bbs030Limit]:checked").val());
    if (limitKbn == 1) {
        $('#inputLimitDate').show();
    } else {
        $('#inputLimitDate').hide();
    }

    var keepKbn = Number($("input:radio[name=bbs030Keep]:checked").val());
    if (keepKbn == 1) {
        $('#inputKeepDate').show();
    } else {
        $('#inputKeepDate').hide();
    }
}
