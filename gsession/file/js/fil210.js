function viewchange() {

    var viewadmin=document.all && document.all('viewadmin') || document.getElementById && document.getElementById('viewadmin');
    var viewgroup=document.all && document.all('viewgroup') || document.getElementById && document.getElementById('viewgroup');
    var viewuser=document.all && document.all('viewuser') || document.getElementById && document.getElementById('viewuser');
    var viewall=document.all && document.all('viewall') || document.getElementById && document.getElementById('viewall');

    if ($('#view0')[0].checked) {
        viewadmin.style.display="block";
        viewgroup.style.display="none";
        viewuser.style.display="none";
        viewall.style.display="none";

    } else if ($('#view1')[0].checked) {
        viewadmin.style.display="none";
        viewgroup.style.display="block";
        viewuser.style.display="none";
        viewall.style.display="none";

    } else if ($('#view2')[0].checked) {
        viewadmin.style.display="none";
        viewgroup.style.display="none";
        viewuser.style.display="block";
        viewall.style.display="none";

    } else if ($('#view3')[0].checked) {
        viewadmin.style.display="none";
        viewgroup.style.display="none";
        viewuser.style.display="none";
        viewall.style.display="block";

    }
}

function allSelect() {

    var flg = true;
   if (document.forms[0].fil210AllSelect.checked) {
       flg = true;
   } else {
       flg = false;
   }
   oElements = document.getElementsByName("all_select");
   var defUsrAry = document.forms[0].fil210RightUsers.options;
   var defLength = defUsrAry.length;
   for (i = defLength - 1; i >= 0; i--) {
       if (defUsrAry[i].value != -1) {
           defUsrAry[i].selected = flg;
       }
   }
}
