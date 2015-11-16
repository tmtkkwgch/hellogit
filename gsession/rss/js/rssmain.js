function rssEdit(rssSid) {
    document.forms[0].CMD.value='rssEdit';
    document.forms[0].rssSid.value=rssSid;
    document.forms[0].rssCmdMode.value=1;
    document.forms[0].submit();
    return false;
}

function rssUpdate() {
    $.get("../rss/rss010.do", {"CMD":rssUpdate} );
    return false;
}

  /* Discription disp and hide */
  function dispDescription(fdid) {
    var ctext = $('#' + fdid)[0];
    if (ctext.className == 'rss_description_text_dsp') {
      changeStyle(ctext, 'rss_description_text_notdsp');
    } else {
      changeStyle(ctext, 'rss_description_text_dsp');
    }
  }

  /** rss title search */
  function rssSearch(rsstitle) {
      return webSearch(rsstitle);
  }

