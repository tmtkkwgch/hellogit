function lmtEnableDisable() {
    var ctext = $('#lmtinput')[0];
    if (document.forms[0].usr082DefoDspKbn[1].checked) {
        changeStyle(ctext, 'usr_description_text_dsp');
    } else {
        changeStyle(ctext, 'usr_description_text_notdsp');
    }
}