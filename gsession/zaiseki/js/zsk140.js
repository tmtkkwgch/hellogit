function sortEnableDisable() {
    var ctext = $('#lmtText')[0];
    if (document.forms[0].zsk140SortKbn[1].checked) {
        changeStyle(ctext, 'text_description_dsp');
    } else {
        changeStyle(ctext, 'text_description_notdsp');
    }
}
