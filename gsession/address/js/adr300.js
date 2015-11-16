function lmtEnableDisable() {
    var ctext = $('#lmtinput')[0];
    if (document.forms[0].adr300MemDspKbn[1].checked) {
        changeStyle(ctext, 'add_description_text_dsp');
    } else {
        changeStyle(ctext, 'add_description_text_notdsp');
    }
}

function lmtEnableDisable2(selnum) {
    var ctext1 = document.getElementById("lmtinput1");
    var ctext2 = document.getElementById("lmtinput2");
    var ctext3 = document.getElementById("lmtinput3");
    var ctext4 = document.getElementById("lmtinput4");
    if (selnum == 0) {
        changeStyle(ctext1, 'add_description_text_dsp2');
        changeStyle(ctext2, 'add_description_text_notdsp');
        changeStyle(ctext3, 'add_description_text_notdsp');
        changeStyle(ctext4, 'add_description_text_notdsp');
    } else if (selnum == 1) {
        changeStyle(ctext1, 'add_description_text_notdsp');
        changeStyle(ctext2, 'add_description_text_dsp2');
        changeStyle(ctext3, 'add_description_text_notdsp');
        changeStyle(ctext4, 'add_description_text_notdsp');
    } else if (selnum == 2) {
        changeStyle(ctext1, 'add_description_text_notdsp');
        changeStyle(ctext2, 'add_description_text_notdsp');
        changeStyle(ctext3, 'add_description_text_dsp2');
        changeStyle(ctext4, 'add_description_text_notdsp');
    } else if (selnum == 3) {
        changeStyle(ctext1, 'add_description_text_notdsp');
        changeStyle(ctext2, 'add_description_text_notdsp');
        changeStyle(ctext3, 'add_description_text_notdsp');
        changeStyle(ctext4, 'add_description_text_dsp2');
    }
}