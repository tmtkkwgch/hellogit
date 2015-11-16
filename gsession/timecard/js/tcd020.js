function clearValue(hour, min){
    document.getElementsByName(hour).item(0).value = '-1';
    document.getElementsByName(min).item(0).value = '-1';
}

function init() {
    holRadioChange();
}


function holRadioChange(){
    flgTmp = 0;
    for (i = 0; i < document.forms[0].tcd020HolKbn.length; i++) {
        if (document.forms[0].tcd020HolKbn[i].checked == true) {
            flgTmp = document.forms[0].tcd020HolKbn[i].value;
        }
    }
    if (flgTmp == 0) {
        setStyle('tcd020HolValue', 0);
        setStyle('tcd020HolDays', 0);
    } else if (flgTmp == 5) {
        setStyle('tcd020HolValue', 1);
        setStyle('tcd020HolDays', 1);
    } else {
        setStyle('tcd020HolValue', 0);
        setStyle('tcd020HolDays', 1);
    }
}

function setStyle(fname, flg){

    chkAry = document.getElementsByName(fname);

    if (flg == 1) {

        for(i = 0; i < chkAry.length; i++) {
            chkAry[i].disabled=false;

            if (chkAry[i].type == 'text' || chkAry[i].type == 'textarea') {
                chkAry[i].style.backgroundColor = '#ffffff';
            }
        }

    } else if (flg == 0) {

        for(i = 0; i < chkAry.length; i++) {
            chkAry[i].disabled=true;

            if (chkAry[i].type == 'text' || chkAry[i].type == 'textarea') {
                chkAry[i].style.backgroundColor = '#e0e0e0';
                chkAry[i].value="";
            } else if (chkAry[i].type == 'select-one') {
                chkAry[i].value=0;
            } else {
                if (chkAry[i].value == 0) {
                    chkAry[i].checked = true;
                }
            }
        }
    }
}