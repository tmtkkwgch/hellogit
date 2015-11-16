$(function(){
    var juuyou=$("input:radio[name=enq210Juuyou]:checked").val();

    document.getElementById('star_1').style.display = "none";
    document.getElementById('star_2').style.display = "none";
    document.getElementById('star_3').style.display = "none";

    if (juuyou == 1) {
        document.getElementById('star_2').style.display = "block";
    } else if (juuyou == 2) {
        document.getElementById('star_3').style.display = "block";
    } else {
        document.getElementById('star_1').style.display = "block";
    }

    enq210checkSmail();


    var editMode=$("input:hidden[name=enq210editMode]").val();

    if (editMode != 2) {
        checkEnq210AnsOpen();
    }

});

function changeAttached(attachedType) {
    $('#enq210attachPosition').hide();
    $('#enq210attachFile').hide();
    $('#enq210attachUrl').hide();
    $('#enq210attachDspName').hide();

    if (attachedType == 1) {
        $('#enq210attachPosition').show();
        $('#enq210attachFile').show();
    } else if (attachedType == 2) {
        $('#enq210attachPosition').show();
        $('#enq210attachFile').show();
        $('#enq210attachDspName').show();
    } else if (attachedType == 3) {
        $('#enq210attachPosition').show();
        $('#enq210attachUrl').show();
        $('#enq210attachDspName').show();
    }
}

function changeSeqType() {
    if (!$('#enq210qnoAuto_0')) {
        return;
    }

    var seqType = $('input[name="enq210queSeqType"]:checked').val();
    var queListSize = $('input[name="ebaListSize"]').val();

    for (idx = 0; idx < queListSize; idx++) {
        if (seqType == 1) {
            $('#enq210qnoAuto_' + idx).show();
            $('#enq210qnoMan_' + idx).hide();
        } else {
            $('#enq210qnoAuto_' + idx).hide();
            $('#enq210qnoMan_' + idx).show();
        }
    }
}

function enq210Entry() {
    return buttonPush('enq210entry');
}

function enq210Draft() {
    setFormatDesc('enq210DescText', 'enq210DescText');
    return buttonPush('enq210draft');
}

function addQuestion(type) {
    document.forms[0].enq210queType.value = type;
    document.forms[0].enq220editMode.value = '0';
    return buttonPush('enq210addQuestion');
}

function editQuestion(queIndex) {
    document.forms[0].enq220editMode.value = '1';
    document.forms[0].enq210editQueIndex.value = queIndex;
    return buttonPush('enq210editQuestion');
}

function deleteQuestion(queIndex) {
    document.forms[0].enq210editQueIndex.value = queIndex;
    return buttonPush('enq210deleteQuestion');
}

function sortQuestion(cmd) {
    return buttonPush(cmd);
}

$(function(){
    if (document.forms[0].enq210scrollQuestonFlg.value == '1') {
        window.location.hash='enq210question';
        document.forms[0].enq210scrollQuestonFlg.value = '';
    }
});

//hiddenパラメータの表示順を再設定する
function changeDspSec() {
    var hid = document.getElementsByClassName("enqDspSec");
    for (i = 0; i < hid.length; i++) {
        hid[i].value = i + 1;
    }
}

function enq210selectTemplate(templateId) {
    document.forms[0].enq210templateId.value = templateId;
    return buttonPush('selectTemplate');
}

function enq210DspTemplate() {
    $('#enq210_template').dialog({
        autoOpen: true,  // hide dialog
        bgiframe: true,   // for IE6
        resizable: false,
        height: 400,
        width: 500,
        modal: true,
        title: "テンプレート選択",
        overlay: {
          backgroundColor: '#000000',
          opacity: 0.5
        },
        buttons: {
            閉じる: function() {
            $(this).dialog('close');
            }
          }
    });
}

function enq210opnTemp(fileDir) {
    opnTemp('enq210file', fileDir, '1', '0');
}

function enq210selectAnswersList() {

  var ansCheck = document.forms['enq210Form'].enq210selectAnswersKbn.checked;
  var answerArray = document.forms['enq210Form'].enq210NoSelectAnswerList.options;
  var ansLength = answerArray.length;

  for (i = ansLength - 1; i >= 0; i--) {
    if (answerArray[i].value != -1) {
      answerArray[i].selected = ansCheck;
    }
  }
}

function enq210checkSmail() {
  if (!document.forms['enq210Form'].enq210smailInfo) {
    return;
  }

  var openFrDate = enq210createDate('enq210Fr');
  var ansLimitDate = enq210createDate('enq210Ans');
  if (openFrDate == null || ansLimitDate == null) {
      enq210changeSmail(false);
      return;
  }
  var nowDate = new Date();
  nowDate = new Date(nowDate.getFullYear(), nowDate.getMonth(), nowDate.getDate());
  if (nowDate.getTime() < openFrDate.getTime() || nowDate.getTime() > ansLimitDate.getTime()) {
      enq210changeSmail(false);
  } else {
      enq210changeSmail(true);
  }
}

function enq210changeSmail(smlFlg) {
    if (smlFlg) {
        document.forms['enq210Form'].enq210smailInfo.disabled = false;
    } else {
        document.forms['enq210Form'].enq210smailInfo.checked = false;
        document.forms['enq210Form'].enq210smailInfo.disabled = true;
    }
}

function enq210createDate(paramNameHead) {
    var year = enq210selectValue(paramNameHead + 'Year');
    var month = enq210selectValue(paramNameHead + 'Month');
    var day = enq210selectValue(paramNameHead + 'Day');
    var newDate = new Date(year, month - 1, day);
    if (newDate.getFullYear() != year || newDate.getMonth() + 1 != month || newDate.getDate() != day) {
        newDate = null;
    }
    return newDate;
}

function enq210selectValue(paramName) {
  return $("select[name='" + paramName + "']").val();
}

function enq210moveDay(year, month, day, kbn) {
  var rtnVal = moveDay(year, month, day, kbn);
  enq210checkSmail();
  return rtnVal;
}

function enq210WrtCalendar(day, month, year) {
    setCalDateClickFnc('enq210checkSmail()');
    wrtCalendar(day, month, year);
}

function enq210ToDateKbn() {
    var toDateDisabled = document.forms[0].enq210ToKbn.checked;

    var toDateElements = ['enq210ToYear', 'enq210ToMonth', 'enq210ToDay',
                                       'enq210ToDateCal', 'enq210ToDateForward', 'enq210ToDateToday', 'enq210ToDateNext'];

    for (elIdx = 0; elIdx < toDateElements.length; elIdx++) {
        document.getElementsByName(toDateElements[elIdx])[0].disabled = toDateDisabled;
    }
}
function checkEnq210AnsOpen() {
    var ansOpen = !document.forms[0].enq210AnsOpen.checked;

    var toDateElements = ['enq210AnsPubFrYear', 'enq210AnsPubFrMonth', 'enq210AnsPubFrDay',
                                       'enq210AnsPubFrCal', 'enq210AnsPubFrForward', 'enq210AnsPubFrToday', 'enq210AnsPubFrNext',
                                       'enq210ToYear', 'enq210ToMonth', 'enq210ToDay',
                                       'enq210ToDateCal', 'enq210ToDateForward', 'enq210ToDateToday', 'enq210ToDateNext',
                                       'enq210ToKbn'];

    for (elIdx = 0; elIdx < toDateElements.length; elIdx++) {
        document.getElementsByName(toDateElements[elIdx])[0].disabled = ansOpen;

    }
    if (!ansOpen) {
        enq210ToDateKbn();
    }
}
