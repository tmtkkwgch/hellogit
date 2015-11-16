function changeEnableDisable() {

    var jbatchKbn = 0;
    var jbatchKbnVal = '';

    for (i = 0; i < document.forms[0].sml150JdelKbn.length; i++) {
        if (document.forms[0].sml150JdelKbn[i].checked == true) {
            jbatchKbn = i;
        }
    }
    jbatchKbnVal = document.forms[0].sml150JdelKbn[jbatchKbn].value;

    if (jbatchKbnVal == 0) {
        document.forms[0].sml150JYear.disabled = true;
        document.forms[0].sml150JYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].sml150JMonth.disabled = true;
        document.forms[0].sml150JMonth.style.backgroundColor = '#e0e0e0';
    } else {
        document.forms[0].sml150JYear.disabled = false;
        document.forms[0].sml150JYear.style.backgroundColor = '#ffffff';
        document.forms[0].sml150JMonth.disabled = false;
        document.forms[0].sml150JMonth.style.backgroundColor = '#ffffff';
    }

    var sbatchKbn = 0;
    var sbatchKbnVal = '';

    for (i = 0; i < document.forms[0].sml150SdelKbn.length; i++) {
        if (document.forms[0].sml150SdelKbn[i].checked == true) {
            sbatchKbn = i;
        }
    }
    sbatchKbnVal = document.forms[0].sml150SdelKbn[sbatchKbn].value;

    if (sbatchKbnVal == 0) {
        document.forms[0].sml150SYear.disabled = true;
        document.forms[0].sml150SYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].sml150SMonth.disabled = true;
        document.forms[0].sml150SMonth.style.backgroundColor = '#e0e0e0';
    } else {
        document.forms[0].sml150SYear.disabled = false;
        document.forms[0].sml150SYear.style.backgroundColor = '#ffffff';
        document.forms[0].sml150SMonth.disabled = false;
        document.forms[0].sml150SMonth.style.backgroundColor = '#ffffff';
    }

    var wbatchKbn = 0;
    var wbatchKbnVal = '';

    for (i = 0; i < document.forms[0].sml150WdelKbn.length; i++) {
        if (document.forms[0].sml150WdelKbn[i].checked == true) {
            wbatchKbn = i;
        }
    }
    wbatchKbnVal = document.forms[0].sml150WdelKbn[wbatchKbn].value;

    if (wbatchKbnVal == 0) {
        document.forms[0].sml150WYear.disabled = true;
        document.forms[0].sml150WYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].sml150WMonth.disabled = true;
        document.forms[0].sml150WMonth.style.backgroundColor = '#e0e0e0';
    } else {
        document.forms[0].sml150WYear.disabled = false;
        document.forms[0].sml150WYear.style.backgroundColor = '#ffffff';
        document.forms[0].sml150WMonth.disabled = false;
        document.forms[0].sml150WMonth.style.backgroundColor = '#ffffff';
    }

    var dbatchKbn = 0;
    var dbatchKbnVal = '';

    for (i = 0; i < document.forms[0].sml150DdelKbn.length; i++) {
        if (document.forms[0].sml150DdelKbn[i].checked == true) {
            dbatchKbn = i;
        }
    }
    dbatchKbnVal = document.forms[0].sml150DdelKbn[dbatchKbn].value;

    if (dbatchKbnVal == 0) {
        document.forms[0].sml150DYear.disabled = true;
        document.forms[0].sml150DYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].sml150DMonth.disabled = true;
        document.forms[0].sml150DMonth.style.backgroundColor = '#e0e0e0';
    } else {
        document.forms[0].sml150DYear.disabled = false;
        document.forms[0].sml150DYear.style.backgroundColor = '#ffffff';
        document.forms[0].sml150DMonth.disabled = false;
        document.forms[0].sml150DMonth.style.backgroundColor = '#ffffff';
    }

    changeDelKbn();
}

function changeDelKbn() {

    var delKbn = 0;
    var delVal = '';

    for (i = 0; i < document.forms[0].sml150DelKbn.length; i++) {
        if (document.forms[0].sml150DelKbn[i].checked == true) {
            delKbn = i;
        }
    }
    delVal = document.forms[0].sml150DelKbn[delKbn].value;

    if (delVal == 1) {

        document.forms[0].sml150JdelKbn[0].disabled = true;
        document.forms[0].sml150SdelKbn[0].disabled = true;
        document.forms[0].sml150WdelKbn[0].disabled = true;
        document.forms[0].sml150DdelKbn[0].disabled = true;
        document.forms[0].sml150JdelKbn[1].disabled = true;
        document.forms[0].sml150SdelKbn[1].disabled = true;
        document.forms[0].sml150WdelKbn[1].disabled = true;
        document.forms[0].sml150DdelKbn[1].disabled = true;

        document.forms[0].sml150JYear.disabled = true;
        document.forms[0].sml150JMonth.disabled = true;
        document.forms[0].sml150SYear.disabled = true;
        document.forms[0].sml150SMonth.disabled = true;
        document.forms[0].sml150WYear.disabled = true;
        document.forms[0].sml150WMonth.disabled = true;
        document.forms[0].sml150DYear.disabled = true;
        document.forms[0].sml150DMonth.disabled = true;

        document.forms[0].sml150JYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].sml150JMonth.style.backgroundColor = '#e0e0e0';
        document.forms[0].sml150SYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].sml150SMonth.style.backgroundColor = '#e0e0e0';
        document.forms[0].sml150WYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].sml150WMonth.style.backgroundColor = '#e0e0e0';
        document.forms[0].sml150DYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].sml150DMonth.style.backgroundColor = '#e0e0e0';

    } else if (delVal == 0) {

        document.forms[0].sml150JdelKbn[0].disabled = false;
        document.forms[0].sml150JdelKbn[1].disabled = false;

        if (document.forms[0].sml150JdelKbn[1].checked == true) {
            document.forms[0].sml150JYear.disabled = false;
            document.forms[0].sml150JMonth.disabled = false;
            document.forms[0].sml150JYear.style.backgroundColor = '#ffffff';
            document.forms[0].sml150JMonth.style.backgroundColor = '#ffffff';
        }

        document.forms[0].sml150SdelKbn[0].disabled = false;
        document.forms[0].sml150SdelKbn[1].disabled = false;

        if (document.forms[0].sml150SdelKbn[1].checked == true) {
            document.forms[0].sml150SYear.disabled = false;
            document.forms[0].sml150SMonth.disabled = false;
            document.forms[0].sml150SYear.style.backgroundColor = '#ffffff';
            document.forms[0].sml150SMonth.style.backgroundColor = '#ffffff';
        }

        document.forms[0].sml150WdelKbn[0].disabled = false;
        document.forms[0].sml150WdelKbn[1].disabled = false;

        if (document.forms[0].sml150WdelKbn[1].checked == true) {
            document.forms[0].sml150WYear.disabled = false;
            document.forms[0].sml150WMonth.disabled = false;
            document.forms[0].sml150WYear.style.backgroundColor = '#ffffff';
            document.forms[0].sml150WMonth.style.backgroundColor = '#ffffff';
        }

        document.forms[0].sml150DdelKbn[0].disabled = false;
        document.forms[0].sml150DdelKbn[1].disabled = false;

        if (document.forms[0].sml150DdelKbn[1].checked == true) {
            document.forms[0].sml150DYear.disabled = false;
            document.forms[0].sml150DMonth.disabled = false;
            document.forms[0].sml150DYear.style.backgroundColor = '#ffffff';
            document.forms[0].sml150DMonth.style.backgroundColor = '#ffffff';
        }
    }
}

function submitStyleChange() {
    document.forms[0].sml150JdelKbn[0].disabled = false;
    document.forms[0].sml150SdelKbn[0].disabled = false;
    document.forms[0].sml150WdelKbn[0].disabled = false;
    document.forms[0].sml150DdelKbn[0].disabled = false;
    document.forms[0].sml150JdelKbn[1].disabled = false;
    document.forms[0].sml150SdelKbn[1].disabled = false;
    document.forms[0].sml150WdelKbn[1].disabled = false;
    document.forms[0].sml150DdelKbn[1].disabled = false;
    document.forms[0].sml150JYear.disabled=false;
    document.forms[0].sml150JMonth.disabled=false;
    document.forms[0].sml150SYear.disabled=false;
    document.forms[0].sml150SMonth.disabled=false;
    document.forms[0].sml150WYear.disabled=false;
    document.forms[0].sml150WMonth.disabled=false;
    document.forms[0].sml150DYear.disabled=false;
    document.forms[0].sml150DMonth.disabled=false;
}

function setDispState(kbnElem, yearElem, monthElem) {

    for (i = 0; i < kbnElem.length; i++) {
        if (kbnElem[i].checked == true) {
            batchKbn = i;
        }
    }
    batchKbnVal = kbnElem[batchKbn].value;

    if (batchKbnVal == 0) {
        yearElem.disabled = true;
        yearElem.style.backgroundColor = '#e0e0e0';
        monthElem.disabled = true;
        monthElem.style.backgroundColor = '#e0e0e0';
    } else {
        yearElem.disabled = false;
        yearElem.style.backgroundColor = '#ffffff';
        monthElem.disabled = false;
        monthElem.style.backgroundColor = '#ffffff';
    }
}

$(function() {

    //アカウント選択ボタン
    $("#accountSelBtn").live("click", function(){

        /* アカウント選択ポップアップ */
        $('#accountSelPop').dialog({
            autoOpen: true,  // hide dialog
            bgiframe: true,   // for IE6
            resizable: false,
            height: 600,
            width: 800,
            modal: true,
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
    });

    //削除区分ラジオボタン
    $(".accountSelKbn").live("click", function(){
        $('#accountSelArea').toggle();
    });

});