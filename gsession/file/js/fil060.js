function showOrHide(){
  if (document.forms[0].fil060AccessKbn.length) {
      if (document.forms[0].fil060AccessKbn[0].checked == true) {
          hideText(0);
      } else {
          showText(0);
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

function parentAccessShowOrHide(flg){
	if (flg == 1) {
		showClassText(0);
    } else {
    	hideClassText(0);
    }
	document.forms[0].fil060ParentAccessAll.value = flg;
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

   if (document.forms[0].fil060AcEditAllSlt.checked) {

       flg = true;

   } else {

       flg = false;

   }

   oElements = document.getElementsByName("fil060AcEditRight");

   var defUserAry = document.forms[0].fil060AcEditRight.options;

   var defLength = defUserAry.length;

   for (i = defLength - 1; i >= 0; i--) {

       if (defUserAry[i].value != -1) {

           defUserAry[i].selected = flg;

       }

   }

}
function selectAccessReadList(){

    var flg = true;

   if (document.forms[0].fil060AcReadAllSlt.checked) {

       flg = true;

   } else {

       flg = false;

   }

   oElements = document.getElementsByName("fil060AcReadRight");

   var defUserAry = document.forms[0].fil060AcReadRight.options;

   var defLength = defUserAry.length;

   for (i = defLength - 1; i >= 0; i--) {

       if (defUserAry[i].value != -1) {

           defUserAry[i].selected = flg;

       }

   }
}