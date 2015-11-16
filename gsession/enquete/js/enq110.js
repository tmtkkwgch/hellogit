$(function(){
    setFormatDesc('enq210Desc', 'enq210DescText');
    setCheckEnq('enq110commit');
});

function setFormatDesc(descParam, formatParam) {
    document.getElementsByName(formatParam)[0].value
      = formatDesc(document.getElementsByName(descParam)[0].value, false);
}

function formatDesc(txtVal, type) {
    var lines;
    if (txtVal.indexOf('\n') < 0) {
        lines = [txtVal];
    } else {
        lines = txtVal.split('\n');
    }

    var formatTxt = '';
    for (idx = 0; idx < lines.length; idx++) {
      if (idx >= 1) {
          if (type) {
              formatTxt += '<br />';
          } else {
              formatTxt += '\n';
          }
      }
      if (type) {
          formatTxt += $('<div/>').text(lines[idx]).html();
      } else {
          formatTxt += $('<div/>').html(lines[idx]).text();
      }
    }

    return formatTxt;
  }