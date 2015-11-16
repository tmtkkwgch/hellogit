function fil080ShowOrHide(){
  if (document.forms[0].fil080AccessKbn.length) {
      if (document.forms[0].fil080AccessKbn[0].checked == true) {
          fil080HideText(0);
      } else {
          fil080ShowText(0);
      }
  }
}
function fil080ShowText(index){
    var item1 = $('#show' + index);
    var item2 = $('#hide' + index);
    item1.removeClass('display_none');
    item2.addClass('display_none');
}

function fil080HideText(index){
    var item1 = $('#show' + index);
    var item2 = $('#hide' + index);
    item1.addClass('display_none');
    item2.removeClass('display_none');
}

function parentAccessShowOrHide(flg){
	if (flg == 1) {
		showClassText(0);
    } else {
    	hideClassText(0);
    }
	document.forms[0].fil080ParentAccessAll.value = flg;
}

function showClassText(index){
    var item1 = $('.show' + index);
    var item2 = $('.hide' + index);
    item1.removeClass('display_none');
    item2.addClass('display_none');
}

function hideClassText(index){
    var item1 = $('.show' + index);
    var item2 = $('.hide' + index);
    item1.addClass('display_none');
    item2.removeClass('display_none');
}

function selectAccessEditList(){

    var flg = true;

   if (document.forms[0].fil080AcEditAllSlt.checked) {

       flg = true;

   } else {

       flg = false;

   }

   oElements = document.getElementsByName("fil080AcEditRight");

   var defUserAry = document.forms[0].fil080AcEditRight.options;

   var defLength = defUserAry.length;

   for (i = defLength - 1; i >= 0; i--) {

       if (defUserAry[i].value != -1) {

           defUserAry[i].selected = flg;

       }

   }

}
function selectAccessReadList(){

    var flg = true;

   if (document.forms[0].fil080AcReadAllSlt.checked) {

       flg = true;

   } else {

       flg = false;

   }

   oElements = document.getElementsByName("fil080AcReadRight");

   var defUserAry = document.forms[0].fil080AcReadRight.options;

   var defLength = defUserAry.length;

   for (i = defLength - 1; i >= 0; i--) {

       if (defUserAry[i].value != -1) {

           defUserAry[i].selected = flg;

       }

   }
}
