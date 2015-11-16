function buttonPush(cmd) {

    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}

function bbs040DateKbn() {
    if (document.forms[0].bbs040dateNoKbn.checked == true) {
        document.forms[0].bbs040fromYear.disabled=true;
        document.forms[0].bbs040fromMonth.disabled=true;
        document.forms[0].bbs040fromDay.disabled=true;
        document.forms[0].fromCalendarBtn.disabled=true;
        document.forms[0].bbs040toYear.disabled=true;
        document.forms[0].bbs040toMonth.disabled=true;
        document.forms[0].bbs040toDay.disabled=true;
        document.forms[0].toCalendarBtn.disabled=true;
    } else {
        document.forms[0].bbs040fromYear.disabled=false;
        document.forms[0].bbs040fromMonth.disabled=false;
        document.forms[0].bbs040fromDay.disabled=false;
        document.forms[0].fromCalendarBtn.disabled=false;
        document.forms[0].bbs040toYear.disabled=false;
        document.forms[0].bbs040toMonth.disabled=false;
        document.forms[0].bbs040toDay.disabled=false;
        document.forms[0].toCalendarBtn.disabled=false;
    }
}