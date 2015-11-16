
function showLengthId(id, maxLength, dspId) {
   var str = $(id).val();
   if (str != null) {
       showLengthStr(str, maxLength, dspId);
   }
   return null;
}


function showLengthStr(str, maxLength, dspId) {

  if (str != null) {
      var repStr = str.replace(/(\r\n)/g, "12");
      repStr = repStr.replace(/(\n|\r)/g, "34");
      document.getElementById(dspId).innerHTML = repStr.length;
      var dspIdstr = "#" + dspId;
      var str2 = $(dspIdstr)[0];
      if (repStr.length > maxLength) {
          changeStyle(str2, 'font_string_count_red');
      } else {
          changeStyle(str2, 'font_string_count');
      }
  }

  return null;
}

function showCountStr(str, dspId) {

  if (str != null) {
      var repStr = str.replace(/(\r\n)/g, "12");
      repStr = repStr.replace(/(\n|\r)/g, "34");
      document.getElementById(dspId).innerHTML = repStr.length;

      var str2 = $(dspId);
      changeStyle(str2, 'font_string_count');
  }
}