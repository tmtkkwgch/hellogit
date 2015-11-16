function changeInitArea() {

  var initKbn = document.getElementsByName('ptl150ptlInitKbn')[0].checked;

  if (initKbn == true) {

     $('#ptlInitTypeArea0').show();

     $('#ptlInitTypeArea1').show();

  } else {

     $('#ptlInitTypeArea0').hide();

     $('#ptlInitTypeArea1').hide();

  }

}