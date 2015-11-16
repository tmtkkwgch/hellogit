function showOrHide(){
  if (document.forms[0].fil030AccessKbn.length) {
      if (document.forms[0].fil030AccessKbn[0].checked == true) {
          hideText(0);
      } else {
          showText(0);
      }
  }
  if (document.forms[0].fil030CapaKbn.length) {
    if (document.forms[0].fil030CapaKbn[0].checked == true) {
        hideText(1);
    } else {
        showText(1);
    }
  }
}
function showText(index){
    var item1 = $('#show' + index);
    var item2 = $('#hide' + index);
    item1.removeClass('display_none');
    item2.addClass('display_none');
}

function hideText(index){
    var item1 = $('#show' + index);
    var item2 = $('#hide' + index);
    item1.addClass('display_none');
    item2.removeClass('display_none');
}

function selectAccessList(){

    var flg = true;

   if (document.forms[0].fil030AcAllSlt.checked) {

       flg = true;

   } else {

       flg = false;

   }

   oElements = document.getElementsByName("fil030AcRight");

   var defUserAry = document.forms[0].fil030AcRight.options;

   var defLength = defUserAry.length;

   for (i = defLength - 1; i >= 0; i--) {

       if (defUserAry[i].value != -1) {

           defUserAry[i].selected = flg;

       }

   }

}
function selectAdminList(){

    var flg = true;

   if (document.forms[0].fil030AdmAllSlt.checked) {

       flg = true;

   } else {

       flg = false;

   }

   oElements = document.getElementsByName("fil030AdmRight");

   var defUserAry = document.forms[0].fil030AdmRight.options;

   var defLength = defUserAry.length;

   for (i = defLength - 1; i >= 0; i--) {

       if (defUserAry[i].value != -1) {

           defUserAry[i].selected = flg;

       }

   }
}

function setVersionComboStatus() {

        if (document.forms[0].fil030VerAllKbn.checked) {
            document.forms[0].fil030VerKbn.disabled=false;
       } else {
            document.forms[0].fil030VerKbn.disabled=true;
       }
}