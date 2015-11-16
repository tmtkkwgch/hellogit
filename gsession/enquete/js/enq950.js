/**
 * ページロード時の初期処理
 */
function enq950Init() {

    changeDspState(document.forms[0].enq950SendDelKbn,
                   document.forms[0].enq950SelectSendYear,
                   document.forms[0].enq950SelectSendMonth);

    changeDspState(document.forms[0].enq950DraftDelKbn,
                   document.forms[0].enq950SelectDraftYear,
                   document.forms[0].enq950SelectDraftMonth);
}

/**
 * 年月コンボの無効区分変更処理
 * @param kbnElem 削除区分
 * @param yearElem 年リスト
 * @param monthElem 月リスト
 * @returns {}
 */
function changeDspState(kbnElem, yearElem, monthElem) {

    var selKbn;
    var selKbnVal;

    for (i = 0; i < kbnElem.length; i++) {
        if (kbnElem[i].checked == true) {
            selKbn = i;
        }
    }
    selKbnVal = kbnElem[selKbn].value;

    if (selKbnVal == 0) {
        yearElem.disabled = true;
        yearElem.style.backgroundColor = '#E0E0E0';
        monthElem.disabled = true;
        monthElem.style.backgroundColor = '#E0E0E0';
    } else {
        yearElem.disabled = false;
        yearElem.style.backgroundColor = '#FFFFFF';
        monthElem.disabled = false;
        monthElem.style.backgroundColor = '#FFFFFF';
    }
}

/**
 * submit時に、年月コンボのdisableをfalseに設定する
 * @returns {}
 */
function submitStyleChange() {

    document.forms[0].enq950SelectSendYear.disabled=false;
    document.forms[0].enq950SelectSendMonth.disabled=false;
    document.forms[0].enq950SelectDraftYear.disabled=false;
    document.forms[0].enq950SelectDraftMonth.disabled=false;
}

