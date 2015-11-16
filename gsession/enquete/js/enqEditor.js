$(function() {
    tinyMCE.init({
        // General options
        mode : "exact",
        elements : "enqDescArea",
        theme : "advanced",
        plugins : "safari,style,layer,table,save,advhr,advimage,advlink,inlinepopups,insertdatetime,preview,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras",
        language : "ja",
        width:"100%",
        height:document.getElementsByName('enqEditorSize')[0].value + "px",
        // Theme options
        theme_advanced_buttons1 : "bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,formatselect,fontselect,fontsizeselect,|,bullist,numlist,|,outdent,indent",
        theme_advanced_buttons2 : "undo,redo,|,link,unlink,|,preview,|,forecolor,backcolor,tablecontrols",
        theme_advanced_buttons3 : "",
        theme_advanced_toolbar_location : "top",
        theme_advanced_toolbar_align : "left",
        theme_advanced_statusbar_location : "bottom",
        theme_advanced_resizing : false
    });
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
