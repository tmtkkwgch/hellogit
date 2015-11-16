function changeEnableDisable() {
  if (document.forms[0].rsv280EditKbn[0].checked) {
//     $('#rsvEditArea1').show();
     document.getElementById('rsvEditArea1').rowspan=2;
     $('#rsvEditArea2').show();
  } else {
//     $('#rsvEditArea1').hide();
     document.getElementById('rsvEditArea1').rowspan=1;
     $('#rsvEditArea2').hide();
  }
}
