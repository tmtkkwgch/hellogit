function lmtEnableDisable() {
    var ctext = $('#lmtinput')[0];
    if (document.forms[0].sch085MemDspKbn[1].checked) {
        changeStyle(ctext, 'sch_description_text_dsp');
    } else {
        changeStyle(ctext, 'sch_description_text_notdsp');
    }
}