function changeEnableDisable() {
    var ctext = $('#lmtText')[0];
    if (document.forms[0].rsv290DateKbn[1].checked) {
        changeStyle(ctext, 'text_description_dsp');
    } else {
        changeStyle(ctext, 'text_description_notdsp');
    }
}
