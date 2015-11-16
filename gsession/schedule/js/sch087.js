function changeEnableDisable() {
  if (document.forms[0].sch087RepeatKbnType[0].checked) {
     document.getElementById('schRepertArea1').rowspan=2;
     $('#schRepertArea2').show();
  } else {
     document.getElementById('schRepertArea1').rowspan=1;
     $('#schRepertArea2').hide();
  }
}


function showOrHide() {
  if (document.forms[0].sch087RepeatKbn.length) {
      if (document.forms[0].sch087RepeatKbn[1].checked) {
          showText();
      } else {
          hideText();
      }
  }
}
function showText(){
    $('#repertMyKbnArea').show();
}

function hideText(){
    $('#repertMyKbnArea').hide();
}
