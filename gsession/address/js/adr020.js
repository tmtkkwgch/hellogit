function editCompany() {
    document.forms['adr020Form'].adr110editAcoSid.value = document.forms['adr020Form'].adr020selectCompany.value;
    document.forms['adr020Form'].adr110ProcMode.value = 1;
    buttonPush('editCompany');
}

function deleteLabel(albSid) {
    document.forms['adr020Form'].adr020deleteLabel.value = albSid;
    buttonPush('deleteLabel');
}

function viewchange() {
    var viewgroup=document.all && document.all('viewgroup') || document.getElementById && document.getElementById('viewgroup');
    var viewuser=document.all && document.all('viewuser') || document.getElementById && document.getElementById('viewuser');
    var editgroup=document.all && document.all('editgroup') || document.getElementById && document.getElementById('editgroup');
    var edituser=document.all && document.all('edituser') || document.getElementById && document.getElementById('edituser');

    if ($('#view0')[0].checked) {
        viewgroup.style.display="none";
        viewuser.style.display="none";
        editselect.style.display="none";
        editselectstr.style.display="block";
        $('#editselectstr')[0].innerHTML = "<span class=\"text_base2\">担当者のみ</span>";

        $('#edit0')[0].checked = true;

    } else if ($('#view1')[0].checked) {
        viewgroup.style.display="block";
        viewuser.style.display="none";
        editselect.style.display="none";
        editselectstr.style.display="block";
        $('#editselectstr')[0].innerHTML = "<span class=\"text_base2\">グループ指定</span>";

        $('#edit1')[0].checked = true;

    } else if ($('#view2')[0].checked) {
        viewgroup.style.display="none";
        viewuser.style.display="block";
        editselect.style.display="none";
        editselectstr.style.display="block";
        $('#editselectstr')[0].innerHTML = "<span class=\"text_base2\">ユーザ指定</span>";

        $('#edit2')[0].checked = true;

    } else if ($('#view3')[0].checked) {
        viewgroup.style.display="none";
        viewuser.style.display="none";
        editselect.style.display="block";
        editselectstr.style.display="none";

    }

    editchange();
}

function editchange() {

    var editgroup=document.all && document.all('editgroup') || document.getElementById && document.getElementById('editgroup');
    var edituser=document.all && document.all('edituser') || document.getElementById && document.getElementById('edituser');

    if ($('#edit0')[0].checked) {
        editgroup.style.display="none";
        edituser.style.display="none";

    } else if ($('#edit1')[0].checked) {
        editgroup.style.display="block";
        edituser.style.display="none";

    } else if ($('#edit2')[0].checked) {
        editgroup.style.display="none";
        edituser.style.display="block";

    } else if ($('#edit3')[0].checked) {
        editgroup.style.display="none";
        edituser.style.display="none";
    }
}

YAHOO.namespace("subbox");
YAHOO.namespace("labeladdbox");
YAHOO.namespace("labelbox");

function init() {
  YAHOO.subbox.subPanel = new YAHOO.widget.Panel("subPanel", { width:"400px", fixedcenter:true, visible:false, constraintoviewport:true, close:false } );
  YAHOO.subbox.subPanel.render();

  YAHOO.labeladdbox.labelAddPanel = new YAHOO.widget.Panel("labelAddPanel", { width:"500px", fixedcenter:true, visible:false, constraintoviewport:true, close:false } );
  YAHOO.labeladdbox.labelAddPanel.render();

  YAHOO.labelbox.labelPanel = new YAHOO.widget.Panel("labelPanel", { width:"400px", fixedcenter:true, visible:false, constraintoviewport:true, close:false } );
  YAHOO.labelbox.labelPanel.render();
}

YAHOO.util.Event.addListener(window, "load", init);

function openpos() {
  pos.location='../address/adr180.do';
  YAHOO.subbox.subPanel.show();
  return false;
}

function openlabeladd(){
  labadd.location='../address/adr200.do';
  YAHOO.labeladdbox.labelAddPanel.show();
  return false;
}

function openlabel(){
  lab.location='../address/adr190.do?adr190parentLabelName=adr020label';
  YAHOO.labelbox.labelPanel.show();
  return false;
}

function addressSearch() {
  var address = document.getElementsByName('adr020address1')[0].value;
  var address2 = document.getElementsByName('adr020address2')[0].value;

  if (address == null) {
      address = address2;
  } else if (address2 != null) {
      address += address2;
  }

  searchGoogleMap(address);
  return false;
}

function buttonCopy(procMode) {
    document.forms[0].adr020ProcMode.value = procMode;
    document.forms[0].adrCopyFlg.value = 1;
    buttonPush('addAdrData');
}