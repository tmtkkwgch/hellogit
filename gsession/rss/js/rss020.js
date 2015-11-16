/* Discription disp and hide */
function dispDescription() {
  var ctext = $('#basicAuthDsp')[0];
  changeStyle(ctext, 'rss_description_text_dsp2');
}
function notDispDescription() {
  var ctext = $('#basicAuthDsp')[0];
  changeStyle(ctext, 'rss_description_text_notdsp');
}

function initDispDescription(basicAuthFlg) {
  var ctext = $('#basicAuthDsp')[0];
  if (basicAuthFlg == 0) {
    changeStyle(ctext, 'rss_description_text_notdsp');
  } else {
    changeStyle(ctext, 'rss_description_text_dsp2');
  }
}
