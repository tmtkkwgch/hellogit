YAHOO.namespace("subbox");
YAHOO.namespace("labeladdbox");
YAHOO.namespace("labelbox");

function init() {
  YAHOO.subbox.subPanel = new YAHOO.widget.Panel("subPanel", { width:"400px", fixedcenter:true, visible:false, constraintoviewport:true, close:false } );
  YAHOO.subbox.subPanel.render();

  YAHOO.labeladdbox.labelAddPanel = new YAHOO.widget.Panel("labelAddPanel", { width:"500px", fixedcenter:true, visible:false, constraintoviewport:true, close:false } );
  YAHOO.labeladdbox.labelAddPanel.render();

  YAHOO.labelbox.labelPanel = new YAHOO.widget.Panel("labelPanel", { width:"400px", height:"300px", fixedcenter:true, visible:false, constraintoviewport:true, close:false } );
  YAHOO.labelbox.labelPanel.render();
}

YAHOO.util.Event.addListener(window, "load", init);

function openlabel(procMode){

  var mode;
  var i;

  //ブックマーク区分取得
  if (procMode == 1) {
      mode = document.forms[0].bmk030mode.value;
  } else {
      for(i = 0; i < document.forms[0].bmk030mode.length; i ++){
          if(document.forms[0].bmk030mode[i].checked){
              mode = i;
          }
      }
  }

  //グループSID取得
  var groupSid = document.forms[0].bmk030groupSid.value;
  
  lab.location='../bookmark/bmk040.do?bmk040parentLabelName=bmk040label'
               + '&bmk040mode=' + mode
               + '&bmk040groupSid=' + groupSid

  YAHOO.labelbox.labelPanel.show();
  return false;
}
