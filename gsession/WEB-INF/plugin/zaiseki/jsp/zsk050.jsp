<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../zaiseki/css/zaiseki.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../zaiseki/js/zsk050.js?<%= GSConst.VERSION_PARAM %>"></script>

<script language="JavaScript" src='../common/js/jquery-ui-1.8.16/jquery-1.6.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../common/js/jquery-ui-1.8.16/jquery-ui-1.8.16.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.core.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.widget.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.mouse.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.draggable.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.droppable.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/yui/yahoo/yahoo.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/yui/dom/dom-min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>

<title>[Group Session] <gsmsg:write key="cmn.zaiseki.management" /></title>
<script type="text/javascript">
<!--
var zaseki;
var eleX = 0;
var eleY = 0;
var htmlStr = "";
var elementKey = '';
var imgX = 0;
var imgY = 0;
var msg1 = '<gsmsg:write key="zsk.10" />';
var msg2 = '<gsmsg:write key="zsk.09" />';

$(function() {
  var pars = 'editZifSid=<bean:write name="zsk050Form" property="editZifSid" />';
  imgX = $('#imgDiv').offset().left;
  imgY = $('#imgDiv').offset().top;

  var url = "../zaiseki/zsk050.do?CMD=getElmInfo";
  var a = $.ajax({
      async: false,
      url: url,
      type: "POST",
      data: pars,
      success: function(res){
          readjson(res);
      },
      complete: function(res){
         createHtml();

         doDraggable();
      },
      error: function(res){
          alert(msg2);
      }
   });
});


function readjson(httpObject) {
  data = eval("(" + httpObject + ")");
  var json = data.zasekielement;
  zaseki = json
}

function createHtml() {
  dsplinkKey = "";
  dsplinkKbn = "";
  dsplinkSid = "";
  dspname = "";
  dspeleX = 0;
  dspeleY = 0;
  for (j = 0; j < zaseki.length; j++) {
    dsplinkKey = zaseki[j].linkKey;
    dsplinkKbn = zaseki[j].linkKbn;
    dsplinkSid = zaseki[j].linkSid;
    dspname = zaseki[j].linkName;
    dspeleX = zaseki[j].linkX;
    dspeleY = zaseki[j].linkY;

    dspeleX = parseInt(imgX) + parseInt(dspeleX);
    dspeleY = parseInt(imgY) + parseInt(dspeleY);

    htmlStr = "";
    if (dsplinkKbn == 0) {
      htmlStr = '<div id="' +
      dsplinkKey +
      '" class="can_drop" style="left:' + dspeleX + '; top:' + dspeleY + '; ' +
      'width:0px; height:0px; border-width:0px; z-index:2000;">' +
      '<span class="zsk_uio_in">' +
      dspname +
      '</span></div>';

    } else if (dsplinkKbn == 1) {
      htmlStr = '<div id="' +
      dsplinkKey +
      '" class="can_drop" style="left:' + dspeleX + '; top:' + dspeleY + '; ' +
      'width:0px; height:0px; border-width:0px; z-index:2000;">' +
      '<span class="zsk_rsv">' +
      dspname +
      '</span></div>';

    } else if (dsplinkKbn == 2) {
      htmlStr = '<div id="' +
      dsplinkKey +
      '" class="can_drop" style="left:' + dspeleX + '; top:' + dspeleY + '; ' +
      'width:0px; height:0px; border-width:0px; z-index:2000;">' +
      '<span class="zsk_msg">' +
      dspname +
      '</span></div>';
    }
    document.getElementById("key").innerHTML += htmlStr;
  }
}

function doDraggable() {
  for (j = 0; j < zaseki.length; j++) {
    elementKey = zaseki[j].linkKey;
    $('#' + elementKey).draggable({
       scroll: false
    });
  }

  $("#imgDiv").droppable({
      accept: ".can_drop",
      drop: function(event, ui) {
          x = $(ui.draggable).offset().left;
          y = $(ui.draggable).offset().top;
          xIndex = parseInt($(ui.draggable).offset().left) - parseInt($('#imgDiv').offset().left)
          yIndex = parseInt($(ui.draggable).offset().top) - parseInt($('#imgDiv').offset().top)
          doMoveElement(ui.draggable, xIndex, yIndex);
      }
  });
  $("#garbage_box").droppable({
      accept: ".can_drop",
      hoverClass: "cart-active",
      drop: function(event, ui) {
          delElement($(ui.draggable).attr("id"));
      }
  });

  $("#allDiv").droppable({
      accept: ".can_drop",
      drop: function(event, ui) {
          x = parseInt($(ui.draggable).offset().left);
          y = parseInt($(ui.draggable).offset().top);

          setUndoIndex($(ui.draggable).attr("id"))
      }
  });
}

function doMoveElement(element, xIndex, yIndex) {

    pars = 'editZifSid=<bean:write name="zsk050Form" property="editZifSid" />' +
           '&elKey=' +
            $(element).attr("id") +
            '&indexx=' +
            xIndex +
            '&indexy=' +
            yIndex;
    url = "../zaiseki/zsk050.do?CMD=setElmInfo";
    var a = $.ajax({
        url: url,
        type: "POST",
        data: pars,
        success: function(res){

        },
        complete: function(res){

        },
        error: function(res){
            alert(msg2);
        }
   });
}

function delElement(eid) {

    pars = 'editZifSid=<bean:write name="zsk050Form" property="editZifSid" />' +
           '&elKey=' +
            eid
            ;
    url = "../zaiseki/zsk050.do?CMD=delElmInfo";
    var a = $.ajax({
        url: url,
        type: "POST",
        data: pars,
        success: function(res){

        },
        complete: function(res){
          $('#' + eid).remove();
        },
        error: function(res){
            alert(msg2);
        }
   });
}

function setUndoIndex(key) {
    name = "";
    linkKey = "";
    eleX = 0;
    eleY = 0;
    pars = 'editZifSid=<bean:write name="zsk050Form" property="editZifSid" />' +
            '&elKey=' +
            key
            ;
    url = "../zaiseki/zsk050.do?CMD=getElmIndex";
    var a = $.ajax({
        url: url,
        type: "POST",
        data: pars,
        success: function(res){
            readjson(res);
        },
        complete: function(res){
            for (j = 0; j < zaseki.length; j++) {
              linkKey = zaseki[j].linkKey;
              eleX = zaseki[j].linkX;
              eleY = zaseki[j].linkY;
            }
            eleX = parseInt($('#imgDiv').offset().left) + parseInt(eleX);
            eleY = parseInt($('#imgDiv').offset().top) + parseInt(eleY);
            $('#' + linkKey)[0].style.left = eleX;
            $('#' + linkKey)[0].style.top = eleY;
        },
        error: function(res){
            alert(msg2);
        }
   });
}

function setElementNameHtml(kbn, sid, id) {
    var addname = "";
    pars = 'editZifSid=<bean:write name="zsk050Form" property="editZifSid" />' +
            '&addElKbn=' +
            kbn +
            '&addElSid=' +
            sid
            ;
    url = "../zaiseki/zsk050.do?CMD=getElmName";
    var a = $.ajax({
        url: url,
        type: "POST",
        data: pars,
        success: function(res){
            readjson(res);
        },
        complete: function(res){
            addname = getNameFromJson();
            if (kbn == 0) {
              htmlStr = '<div id="' +
              id +
              '" class="can_drop" style="' +
              'width:0px; height:0px; border-width:0px; z-index:2000;">' +
              '<span class="zsk_uio_in">' +
              name +
              '</span></div>';

              setElementHtml(0, sid, id, htmlStr, '');
            } else if(kbn == 1) {
              htmlStr = '<div id="' +
              id +
              '" class="can_drop" style="' +
              'width:0px; height:0px; border-width:0px; z-index:2000;">' +
              '<span class="zsk_rsv">' +
              addname +
              '</span></div>';
              setElementHtml(1, sid, id, htmlStr, '');
            }
        },
        error: function(res){
            alert(msg2);
        }
   });
}

function getNameFromJson() {
    name = "";
    if (zaseki.length > 0) {
      name = zaseki[0].linkName;
    }
    return name;
}

function setElementHtml(kbn, sid, id, htmlStr, msg) {

    x = $('#imgDiv').offset().left;
    y = $('#imgDiv').offset().top;
    setX = parseInt(x) + 10;
    setY = parseInt(y) + 10;

    document.getElementById("key").innerHTML += htmlStr;
    $('#' + id)[0].style.left = setX;
    $('#' + id)[0].style.top = setY;
    var keys = getElementsByClassName('can_drop', null, 'div');

    for (i = 0; i < keys.length; i++) {
      $('#' + keys[i].id).draggable({
          scroll: false
       });
    }

    addElement(id, 10, 10, kbn, sid, msg);
}




function getElementsByClassName(searchClass, domNode, tagName) {
    if (domNode == null) domNode = document;
    if (tagName == null) tagName = '*';
    var el = new Array();
    var tags = domNode.getElementsByTagName(tagName);
    var tcl = " "+searchClass+" ";
    for(i=0,j=0; i<tags.length; i++) {
        var test = " " + tags[i].className + " ";
        if (test.indexOf(tcl) != -1)
        el[j++] = tags[i];
    }
    return el;
}






function addElement(eid, xIndex, yIndex, kbn, sid, addElMsg) {

    pars = 'editZifSid=<bean:write name="zsk050Form" property="editZifSid" />' +
           '&elKey=' +
            eid +
            '&indexx=' +
            xIndex +
            '&indexy=' +
            yIndex +
            '&addElKbn=' +
            kbn +
            '&addElSid=' +
            sid +
            '&addElMsg=' +
            addElMsg
            ;
    url = "../zaiseki/zsk050.do?CMD=addElmInfo";
    var a = $.ajax({
        url: url,
        type: "POST",
        data: pars,
        success: function(res){

        },
        complete: function(res){
           changeCombo();
        },
        error: function(res){
            alert(msg2);
        }
   });
}

-->
</script>

</head>
<div id="allDiv">
<body class="body_03">

<div id="key"></div>
<html:form action="/zaiseki/zsk050">
<input type="hidden" name="CMD">
<html:hidden name="zsk050Form" property="backScreen" />
<html:hidden name="zsk050Form" property="initFlg" />
<html:hidden name="zsk050Form" property="editZifSid" />

<html:hidden name="zsk050Form" property="indexx" />
<html:hidden name="zsk050Form" property="indexy" />
<html:hidden name="zsk050Form" property="elKey" />
<html:hidden name="zsk050Form" property="addElKbn" />
<html:hidden name="zsk050Form" property="addElSid" />
<html:hidden name="zsk050Form" property="addElMsg" />


<html:hidden name="zsk050Form" property="selectZifSid" />
<html:hidden name="zsk050Form" property="uioStatus" />
<html:hidden name="zsk050Form" property="uioStatusBiko" />
<html:hidden name="zsk050Form" property="sortKey" />
<html:hidden name="zsk050Form" property="orderKey" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!-- body -->
<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.zaiseki.management" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="zsk.zsk050.04" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.zaiseki.management" />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
        <input type="button" name="btn_shinsei" class="btn_ok1" value="OK" onClick="buttonPush('zsk050kn');">
        <input type="button" name="btn_shinsei" class="btn_dell_n2" value="<gsmsg:write key="zsk.zsk050.05" />" onClick="buttonPush('zsk050delete');">
        <input type="button" name="btn_shinsei" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('zsk030');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <logic:messagesPresent message="false">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
         <td align="left"><html:errors/><br></td>
      </tr>
    </table>
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
    </logic:messagesPresent>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td colspan="3">
    <span class="text_bb1"><gsmsg:write key="zsk.29" /><gsmsg:write key="wml.215" /> </span>
    <html:text name="zsk050Form" property="zasekiMapName" styleClass="text_base" style="width:155px;" maxlength="20"/>
    <span class="text_bb1"><gsmsg:write key="cmn.sort" /><gsmsg:write key="wml.215" /> </span>
    <html:text name="zsk050Form" property="zasekiSortNum" styleClass="text_number" style="width:57px;" maxlength="3"/>
    <hr style="border-color:#cccccc;">
    </td>
    </tr>

    <tr>
      <td width="20%" valign="top">

<!-- 画像選択 -->
        <table width="100%" class="tl0">
        <tr>
        <td class="table_bg_7D91BD_zaiseki_l">
        <span class="text_tlw"><gsmsg:write key="zsk.zsk050.06" /></span>
        </td>
        <td class="table_bg_7D91BD_zaiseki_r" align="right">
        <input type="button" name="float_button" value="<gsmsg:write key="zsk.zsk050.07" />" class="btn_base0" onClick="buttonPush('changeImage');">
        </td>
        </tr>
        <tr>
        <td class="td_type25_2" colspan="2">
        <input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTemp('zsk050file', 'zaiseki', '1', '0');">
        <br>
        <html:select property="zsk050file" styleClass="select01" multiple="false">
        <html:optionsCollection name="zsk050Form" property="zsk050FileLabelList" value="value" label="label" />
        </html:select>
        <br><span class="text_base"><gsmsg:write key="zsk.42" /></span>
        <br><span class="text_base"><gsmsg:write key="zsk.41" /></span>
        <br><span class="text_base"><gsmsg:write key="zsk.43" /></span>
        </td>
        </tr>
        </table>

        <br>

        <table width="100%" class="tl0">
        <tr>
        <td class="table_bg_7D91BD"><span class="text_tlw"><gsmsg:write key="cmn.delete" /></span></td>
        </tr>
        <tr>
        <td class="td_type25_2">
        <span class="text_base"><gsmsg:write key="zsk.zsk050.10" />
        <div id="garbage_box" align="center">
        <img src="../zaiseki/images/icon_dust_box.gif" alt="<gsmsg:write key="cmn.trash" />">
        </div>
        </td>
        </tr>
        </table>

        <br>

        <table width="100%" class="tl0">
        <tr>
        <td class="table_bg_7D91BD"><span class="text_tlw"><gsmsg:write key="zsk.zsk050.08" /></span></td>
        </tr>
        <tr>
        <td class="td_type25_2">
          <span class="text_base"><b>[<gsmsg:write key="zsk.zsk050.03" />]</b></span>
            <br><gsmsg:write key="zsk.zsk050.18" />
            <br><gsmsg:write key="zsk.zsk050.15" />
            <br><gsmsg:write key="zsk.zsk050.13" />
            <br>&nbsp;&nbsp;&nbsp;<gsmsg:write key="zsk.zsk050.14" />
        </td>
        </tr>
        <tr>
          <td width="100%" class="table_bg_7D91BD" nowrap>
            <span class="text_tlw"><gsmsg:write key="cmn.group" /><gsmsg:write key="wml.215" /></span>
            <html:select property="selectGroup" styleClass="select02" onchange="changeCombo();">
            <html:optionsCollection name="zsk050Form" property="groupLabelList" value="value" label="label" />
            </html:select>
            <input type="button" onclick="openGroupWindow(this.form.selectGroup, 'selectGroup', '0')" class="group_btn" value="&nbsp;&nbsp;" id="zsk050GroupBtn">
           </td>
        </tr>
        <tr>
        <td id="test" class="td_type25_2" nowrap>
        <logic:empty name="zsk050Form" property="belongUserList" scope="request">&nbsp;</logic:empty>
        <logic:notEmpty name="zsk050Form" property="belongUserList" scope="request">
        <logic:iterate id="userMdl" name="zsk050Form" property="belongUserList" scope="request">
        <a href="javascript:void(0);" ondblclick="createUserElement(<bean:write name="userMdl" property="usrSid" />);">
        <span class="text_link">
        <bean:write name="userMdl" property="usiSei" />
        &nbsp;
        <bean:write name="userMdl" property="usiMei" />
        </span>
        </a><br>
        </logic:iterate>
        </logic:notEmpty>

        </td>
        </tr>

        <tr>
          <td width="100%" class="table_bg_7D91BD" nowrap>
            <span class="text_tlw"><gsmsg:write key="cmn.facility.group" /><gsmsg:write key="wml.215" /></span>
            <html:select property="selectRsvGroup" styleClass="select02" onchange="changeCombo();">
            <html:optionsCollection name="zsk050Form" property="rsvGroupLabelList" value="value" label="label" />
            </html:select>
          </td>
        </tr>
        <tr>
        <td id="test" class="td_type25_2" nowrap>
        <logic:empty name="zsk050Form" property="belongRsvList" scope="request">&nbsp;</logic:empty>
        <logic:notEmpty name="zsk050Form" property="belongRsvList" scope="request">
        <logic:iterate id="rsvMdl" name="zsk050Form" property="belongRsvList" scope="request">
        <a href="javascript:void(0);" ondblclick="createRsvElement(<bean:write name="rsvMdl" property="rsdSid" />)">
        <span class="text_link">
        <bean:write name="rsvMdl" property="rsdName" />
        </span>
        </a><br>
        </logic:iterate>
        </logic:notEmpty>

        </td>
        </tr>
        </table>

        <br>

        <table width="100%" class="tl0">
        <tr>
          <td width="100%" class="table_bg_7D91BD" nowrap>
            <span class="text_tlw"><gsmsg:write key="zsk.zsk050.09" /></span>
          </td>
        </tr>

        <tr>
          <td class="td_type25_2" nowrap>
            <span class="text_base"><b>[<gsmsg:write key="zsk.zsk050.03" />]</b></span>
            <br><gsmsg:write key="zsk.zsk050.17" />
            <br><gsmsg:write key="zsk.zsk050.16" />
            <br><gsmsg:write key="zsk.zsk050.13" />
            <br>&nbsp;&nbsp;&nbsp;<gsmsg:write key="zsk.zsk050.14" /><br>
            <html:text name="zsk050Form" property="commentValue" styleClass="text_base" style="width:131px;" maxlength="20"/>
            &nbsp;
            <input type="button" name="float_button" value="<gsmsg:write key="zsk.zsk050.01" />" class="btn_base0" onClick="createTxtElement();">
            <br><br><br>

          </td>
        </tr>

        </table>
      </td>

      <td align="left" valign="top" style="position:relative;" nowrap>
        <div id="imgDiv" style="position:relative; z-index:0; left:20px;">
        <logic:notEmpty name="zsk050Form" property="imageFileName">
        <img src="../zaiseki/zsk050.do?CMD=imageDownLord&imageFileName=<bean:write name="zsk050Form" property="imageFileName" />&<bean:write name="zsk050Form" property="zsk050RndNum" />" name="userPhoto" alt="" style="border-style: solid; border-color: #7D91BD">
        </logic:notEmpty>
        </div>
      </td>
      <td width="100%">&nbsp;</td>
  </table>


</td>
</tr>
</table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <table width="100%" cellspacing="0" cellpadding="0" border="0">
    <tr>
    <td align="right">
        <input type="button" name="btn_shinsei" class="btn_ok1" value="OK" onClick="buttonPush2('zsk050kn');">
        <input type="button" name="btn_shinsei" class="btn_dell_n2" value="<gsmsg:write key="zsk.zsk050.05" />" onClick="buttonPush('zsk050delete');">
        <input type="button" name="btn_shinsei" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('zsk030');">
    </td>
    </tr>
    </table>

<tr>
<td class="td_type_tab" colspan="3">
  <table width="100%" class="tl5">
  <tr>
  <td width="100%">&nbsp;</td>
  <td align="right">
  </td>
  </tr>
  </table>
</td>
</tr>
</table>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</html:form>

</body>
</div>
</html:html>