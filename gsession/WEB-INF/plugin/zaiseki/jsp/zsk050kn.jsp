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
<script language="JavaScript" src="../common/js/userpopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../reserve/js/sisetuPopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../zaiseki/js/zsk010.js?<%= GSConst.VERSION_PARAM %>"></script>

<script language="JavaScript" src='../common/js/jquery-ui-1.8.16/jquery-1.6.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../common/js/jquery-ui-1.8.16/jquery-ui-1.8.16.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.core.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.widget.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.mouse.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.draggable.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.droppable.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/yui/yahoo/yahoo.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/yui/dom/dom-min.js?<%= GSConst.VERSION_PARAM %>"></script>
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
  var pars = 'editZifSid=<bean:write name="zsk050knForm" property="editZifSid" />';
  imgX = $('#imgDiv').offset().left;
  imgY = $('#imgDiv').offset().top;

  var url = "../zaiseki/zsk050kn.do?CMD=getElmInfo";
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

    if (dsplinkKbn == 0) {

      htmlStr = '<div id="' +
      dsplinkKey +
      '" style="left:' + dspeleX + '; top:' + dspeleY + '; ' +
      'width:0px; height:0px; border-width:0px; z-index:2000;">' +
      '<a href="javascript:void(0);" onClick="return openUserInfoWindow(' +
      dsplinkSid +
      ')">' +
      '<span class="zsk_uio_in">' +
      dspname +
      '</span></a></div>';
    } else if (dsplinkKbn == 1) {

      htmlStr = '<div id="' +
      dsplinkKey +
      '" style="left:' + dspeleX + '; top:' + dspeleY + '; ' +
      'width:0px; height:0px; border-width:0px; z-index:2000;">' +
      '<a href="javascript:void(0);" onClick="return openSisetuSyosai(' + dsplinkSid + ')">' +
      '<span class="zsk_rsv" >' +
      dspname +
      '</span></a></div>';
    } else if (dsplinkKbn == 2) {

      htmlStr = '<div id="' +
      dsplinkKey +
      '" style="left:' + dspeleX + '; top:' + dspeleY + '; ' +
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
        disabled: true
      });
    }
}

function doMoveElement(element, xIndex, yIndex) {

  pars = 'editZifSid=<bean:write name="zsk050Form" property="editZifSid" />' +
         '&elKey=' +
          element.id +
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
-->
</script>
</head>

<body class="body_03">
<html:form action="/zaiseki/zsk050kn">
<input type="hidden" name="CMD">
<html:hidden name="zsk050knForm" property="backScreen" />
<html:hidden name="zsk050knForm" property="initFlg" />
<html:hidden name="zsk050knForm" property="editZifSid" />
<html:hidden name="zsk050knForm" property="zasekiMapName" />
<html:hidden name="zsk050knForm" property="zasekiSortNum" />

<html:hidden name="zsk050knForm" property="selectZifSid" />
<html:hidden name="zsk050knForm" property="uioStatus" />
<html:hidden name="zsk050knForm" property="uioStatusBiko" />
<html:hidden name="zsk050knForm" property="sortKey" />
<html:hidden name="zsk050knForm" property="orderKey" />

<html:hidden name="zsk050knForm" property="selectGroup" />
<html:hidden name="zsk050knForm" property="selectRsvGroup" />
<html:hidden name="zsk050knForm" property="commentValue" />
<div id="key"></div>
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>


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
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.check" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.zaiseki.management" />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
        <input type="button" name="btn_shinsei" class="btn_base1" value="<gsmsg:write key="cmn.final" />" onClick="buttonPush('zsk050knCommit');">
        <input type="button" name="btn_shinsei" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('zsk050');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td colspan="3">
    <span class="text_bb1"><gsmsg:write key="zsk.29" /><gsmsg:write key="wml.215" /> <bean:write name="zsk050knForm" property="zasekiMapName" /></span>&nbsp;
    <span class="text_bb1"><gsmsg:write key="cmn.sort" /><gsmsg:write key="wml.215" /> <bean:write name="zsk050knForm" property="zasekiSortNum" /></span>
    <hr style="border-color:#cccccc;">
    </td>
    </tr>

    <tr>
      <td align="left" valign="top" style="position:relative;" nowrap>
        <div id="imgDiv" style="position:relative; z-index:0; left:20px;">
        <logic:notEmpty name="zsk050knForm" property="imageFileName">
        <img src="../zaiseki/zsk050kn.do?CMD=imageDownLord&imageFileName=<bean:write name="zsk050knForm" property="imageFileName" />" name="userPhoto" alt="" style="border-style: solid; border-color: #7D91BD">
        </logic:notEmpty>
        </div>
      </td>
      <td width="100%">&nbsp;</td>
      </td>
    </tr>
  </table>
</td>
</tr>
</table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <table width="100%" cellspacing="0" cellpadding="0" border="0">
    <tr>
    <td align="right">
        <input type="button" name="btn_shinsei" class="btn_base1" value="<gsmsg:write key="cmn.final" />" onClick="buttonPush('zsk050knCommit');">
        <input type="button" name="btn_shinsei" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('zsk050');">
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
</html:html>