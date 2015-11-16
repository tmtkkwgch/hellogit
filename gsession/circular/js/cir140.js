function lmtEnableDisable() {
    var ctext = $('#lmtinput')[0];
    if (document.forms[0].cir140KenKbn[1].checked) {
        changeStyle(ctext, 'cir_description_text_dsp');
    } else {
        changeStyle(ctext, 'cir_description_text_notdsp');
    }
}