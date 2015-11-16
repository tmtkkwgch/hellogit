<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/container.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/freeze.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../zaiseki/css/zaiseki.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/glayer.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src='../common/js/jquery-ui-1.8.16/jquery-1.6.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../common/js/jquery-ui-1.8.16/jquery-ui-1.8.16.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.core.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.widget.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.mouse.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.draggable.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.droppable.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/yui/yahoo/yahoo.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/yui/dom/dom-min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/userpopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../reserve/js/sisetuPopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../zaiseki/js/zsk010.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript">
<!--
  //自動リロード
  <logic:notEqual name="zsk010Form" property="zsk010Reload" value="0">
    var reloadinterval = <bean:write name="zsk010Form" property="zsk010Reload" />;
    setTimeout("buttonPush('reload')",reloadinterval);
  </logic:notEqual>
-->
</script>

<script type="text/javascript">
<!--

var zaseki;
var eleX = 0;
var eleY = 0;
var htmlStr = "";
var elementKey = '';
var imgX = 0;
var imgY = 0;
function init() {
  var pars = '?CMD=getElmInfo&selectZifSid=<bean:write name="zsk010Form" property="selectZifSid" />';
  imgX = $('#imgDiv').offset().left;
  imgY = $('#imgDiv').offset().top;

  var url = "../zaiseki/zsk010.do";
  url= url + pars;
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
      }
   });
}

function readjson(httpObject) {
    data = eval("(" + httpObject + ")");
    var json = data.zasekielement;
    zaseki = json
}

function createHtml() {

  dsplinkKey = "";
  dsplinkKbn = "";
  dsplinkSid = "";
  dsplinkUio = "";
  dspname = "";
  dspeleX = 0;
  dspeleY = 0;
  for (j = 0; j < zaseki.length; j++) {
    dsplinkKey = zaseki[j].linkKey;
    dsplinkKbn = zaseki[j].linkKbn;
    dsplinkSid = zaseki[j].linkSid;
    dsplinkUio = zaseki[j].linkUio;
    dspname = zaseki[j].linkName;
    dspeleX = zaseki[j].linkX;
    dspeleY = zaseki[j].linkY;
    dspeleX = parseInt(imgX) + parseInt(dspeleX);
    dspeleY = parseInt(imgY) + parseInt(dspeleY);
    htmlStr = "";
    if (dsplinkKbn == 0) {
        htmlStr = '<div id="' +
        dsplinkKey +
        '" style="' +
        'width:0px; height:0px; border-width:0px; ">' +
        '<span id="' + dsplinkSid + '" class="zsk_uio_etc">' +
        '<a href="javascript:void(0);" onClick="return openUserIoWindow(' +
        dsplinkSid +
        ',' +
        dsplinkUio +
        ')">' +
        dspname +
        '</a>' +
        '</span>' +

        '</div>';
      if (dsplinkUio == 1) {
        htmlStr = '<div id="' +
        dsplinkKey +
        '" style="' +
        'width:0px; height:0px; border-width:0px; ">' +
        '<span id="' + dsplinkSid + '" class="zsk_uio_in">' +
        '<a href="javascript:void(0);" onClick="return openUserIoWindow(' +
        dsplinkSid +
        ',' +
        dsplinkUio +
        ')">' +
        dspname +
        '</a>' +
        '</span>' +

        '</div>';
      } else if (dsplinkUio == 2) {
      month="month";
        htmlStr = '<div id="' +
        dsplinkKey +
        '" style="' +
        'width:0px; height:0px; border-width:0px; ">' +
        '<span id="' + dsplinkSid + '" class="zsk_uio_leave">' +
        '<a href="javascript:void(0);" onClick="return openUserIoWindow(' +
        dsplinkSid +
        ',' +
        dsplinkUio +
        ')">' +
        dspname +
        '</a>' +
        '</span>' +
        '</div>';

      }

    } else if (dsplinkKbn == 1) {

      if (dsplinkUio == 0) {
        htmlStr = '<div id="' +
        dsplinkKey +
        '" style="' +
        'width:0px; height:0px; border-width:0px; ">' +
        '<a href="javascript:void(0);" onClick="return openSisetuSyosai(' + dsplinkSid + ', 1)">' +
        '<span class="zsk_rsv">' +
        dspname +
        '</span></a></div>';
      } else {
        htmlStr = '<div id="' +
        dsplinkKey +
        '" style="' +
        'width:0px; height:0px; border-width:0px; ">' +
        '<a href="javascript:void(0);" onClick="return openSisetuSyosai(' + dsplinkSid + ', 1)">' +
        '<span class="zsk_rsv_used">' +
        dspname +
        '</span></a></div>';
      }


    } else if (dsplinkKbn == 2) {
      htmlStr = '<div id="' +
      dsplinkKey +
      '" style="' +
      'width:0px; height:0px; border-width:0px; ">' +
      '<span class="zsk_msg">' +
      dspname +
      '</span></div>';
    }
    document.getElementById("key").innerHTML += htmlStr;
    YAHOO.util.Dom.setXY(dsplinkKey, [dspeleX,dspeleY], true);
  }
}
-->
</script>

<title>[Group Session] <gsmsg:write key="cmn.zaiseki.management" /></title>
</head>

<body class="body_03" onunload="windowClose();" onload="init();">
<div id="key"></div>
<html:form action="/zaiseki/zsk010">


<input type="hidden" name="CMD">
<html:hidden property="cmd" />
<html:hidden name="zsk010Form" property="sortKey" />
<html:hidden name="zsk010Form" property="orderKey" />
<html:hidden name="zsk010Form" property="smailUseOk" />


<input type="hidden" name="uioUpdateUsrSid">
<input type="hidden" name="uioUpdateStatus">

<input type="hidden" name="sch010SelectUsrSid">
<input type="hidden" name="sch010SelectUsrKbn">

<logic:notEmpty name="zsk010Form" property="elementKeyList" scope="request">
  <logic:iterate id="key" name="zsk010Form" property="elementKeyList" scope="request">
    <input type="hidden" name="elementKey" value="<bean:write name="key"/>">
  </logic:iterate>
</logic:notEmpty>

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
    <td width="0%"><img src="./images/header_zaiseki_01.gif" border="0" alt="<gsmsg:write key="cmn.zaiseki.management" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl" style="font-size: 20px!important"><gsmsg:write key="cmn.zaiseki.management" /></span></td>
    <td width="100%" class="header_white_bg_text"><span style="font-size: 15px!important">[ <gsmsg:write key="zsk.20" /> ]</span></td>
    <td width="100%" class="header_white_bg">
    <input type="button" name="btn_reload" class="btn_reload_n1" value="<gsmsg:write key="cmn.reload" />" onClick="buttonPush('reload');">
    <logic:equal name="zsk010Form" property="adminKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.USER_ADMIN) %>">
    <input type="button" name="btn_ktool" class="btn_setting_admin_n1" value="<gsmsg:write key="cmn.admin.setting" />" onClick="buttonPush('zsk020');">
    </logic:equal>
    <input type="button" name="btn_pset" class="btn_setting_n1" value="<gsmsg:write key="cmn.preferences2" />" onClick="buttonPush('zsk070');"></td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.zaiseki.management" />"></td>
    </tr>
    </table>
  </td>
  </tr>

  <tr>
  <td align="center">
  <img src="../common/images/spacer.gif" width="1px" height="3px" border="0">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td colspan="1" align="left">
      <table width="100%">
      <tr valign="bottom">
      <td width="0%" nowrap>
        <table border="0" cellspacing="0" cellpadding="0">
        <tr>
        <td nowrap><span class="text_bb1"><gsmsg:write key="cmn.user" /><gsmsg:write key="wml.215" /></span></td>
        <td class="td_type1" nowrap><span class="text_base2"><gsmsg:write key="cmn.zaiseki2" /></span></td>
        <td>&nbsp;</td>
        <td class="td_type_gaisyutu" nowrap><span class="text_base2"><gsmsg:write key="cmn.absence2" /></span></td>
        <td>&nbsp;</td>
        <td class="td_type_kekkin" nowrap><span class="text_base2"><gsmsg:write key="cmn.other" /></span></td>
        <td>&nbsp;</td>
        <td nowrap><span class="text_bb1"><gsmsg:write key="cmn.facility" /><gsmsg:write key="wml.215" /></span></td>
        <td class="td_type1" nowrap><span class="text_base2"><gsmsg:write key="cmn.unused" /></span></td>
        <td>&nbsp;</td>
        <td class="td_type_used" nowrap><span class="text_base2"><gsmsg:write key="cmn.in.use" /></span></td>
        <td>&nbsp;</td>
        </tr>
        </table>
      </td>
      <td width="100%">
      <span class="text_bb1"><gsmsg:write key="zsk.29" /></span>
      <html:select property="selectZifSid" styleClass="select02" onchange="changeZasekiCombo();">
      <html:optionsCollection name="zsk010Form" property="zifLabelList" value="value" label="label" />
      </html:select>
      </td>
      </tr>
      </table>
    <hr style="border-color:#cccccc;">
    </td>
    </tr>
    <tr>
    <td colspan="1" align="left">
      <table width="0%">
      <tr>
      <td width="0%" valign="top" align="left">
        <table width="0%">
        <tr>
        <td align="left">
        <div id="imgDiv" style="position:static; z-index:0;left:20px;">
        <logic:notEmpty name="zsk010Form" property="zsk010binSid">
        <logic:notEqual name="zsk010Form" property="selectZifSid" value="-1">
        <img src="../zaiseki/zsk010.do?CMD=imageDownLord&zsk010binSid=<bean:write name='zsk010Form' property='zsk010binSid' />" name="zasekiImage" alt="" style="border-style: solid; border-color: #999999">
        </logic:notEqual>
        </logic:notEmpty>
        </div>
        </td>
        </tr>
        </table>
      </td>

      <td width="0%"><img src="../common/images/spacer.gif" width="1px" height="10px" border="0"></td>
      <td width="0%" valign="top" align="left">
        <table class="tl0" width="300" cellpadding="5">
        <logic:empty name="zsk010Form" property="userList" scope="request">
        <tr>
        <td width="60%" align="center" class="table_bg_7D91BD" nowrap><span class="text_tlw"><gsmsg:write key="cmn.user.name" /></span></td>
        <td width="20%" align="center" class="table_bg_7D91BD" nowrap><span class="text_tlw"><gsmsg:write key="zsk.20" /></span></td>
        </tr>
        <tr>
        <td colspan="2" width="80%" align="center" class="td_type1" nowrap><gsmsg:write key="zsk.zsk010.06" /></td>
        </tr>
        </logic:empty>

        <logic:notEmpty name="zsk010Form" property="userList" scope="request">
        <tr>
        <!-- 氏名 -->
        <th colspan="2" class="table_bg_7D91BD" width="20%" nowrap><span class="text_tlw"><gsmsg:write key="cmn.name" /></span></th>

        <!-- 在席状況 -->
        <th class="table_bg_7D91BD" width="0%" nowrap><span class="text_tlw"><gsmsg:write key="zsk.20" /></span></th>

        <!-- 在席コメント -->
        <th class="table_bg_7D91BD" width="0%" nowrap><span class="text_tlw"><gsmsg:write key="zsk.23" /></span></th>
        </tr>

        <logic:iterate id="userMdl" name="zsk010Form" property="userList" scope="request" indexId="idx">
        <tr>
        <input type="hidden" name="smlAble[<bean:write name="userMdl" property="usrSid" />]" value="<bean:write name="userMdl" property="smlAble"/>"/>
        <logic:equal name="userMdl" property="uioStatus" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.UIOSTS_IN) %>">
        <bean:define id="zskColor" value="td_type1" />
        <td class="<bean:write name="zskColor" />" nowrap>
        <a href="javascript:void(0);" onClick="return openUserInfoWindow(<bean:write name="userMdl" property="usrSid" />)">
        <span class="text_link">
        <bean:write name="userMdl" property="usiSei" />&nbsp;&nbsp;<bean:write name="userMdl" property="usiMei" />
        </span>
        </a>
        </td>
        <td class="<bean:write name="zskColor" />" nowrap>
        <span id="rt">
        <input type="button" class="btn_change" name="btnChange" value="<gsmsg:write key="cmn.change" />" onClick="openUserIoWindow(<bean:write name="userMdl" property="usrSid"/>, <%= String.valueOf(jp.groupsession.v2.cmn.GSConst.UIOSTS_IN) %>);"><br>
        </span>
        </td>
        <td align="center" class="<bean:write name="zskColor" />"><gsmsg:write key="cmn.zaiseki2" /></td>
        <td align="left" class="<bean:write name="zskColor" />"><bean:write name="userMdl" property="uioComment" /></td>
        </logic:equal>

        <logic:equal name="userMdl" property="uioStatus" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.UIOSTS_LEAVE) %>">
        <bean:define id="zskColor" value="td_type_gaisyutu" />
        <td class="<bean:write name="zskColor" />" nowrap>
        <a href="javascript:void(0);" onClick="return openUserInfoWindow(<bean:write name="userMdl" property="usrSid" />)">
        <span class="text_link">
        <bean:write name="userMdl" property="usiSei" />&nbsp;&nbsp;<bean:write name="userMdl" property="usiMei" />
        </span>
        </a>
        </td>
        <td class="<bean:write name="zskColor" />" nowrap>
        <span id="rt">
        <input type="button" class="btn_change" name="btnChange" value="<gsmsg:write key="cmn.change" />" onClick="openUserIoWindow(<bean:write name="userMdl" property="usrSid"/>, <%= String.valueOf(jp.groupsession.v2.cmn.GSConst.UIOSTS_LEAVE) %>);"><br>
        </span>
        </td>
        <td align="center" class="<bean:write name="zskColor" />"><gsmsg:write key="cmn.absence2" /></td>
        <td align="left" class="<bean:write name="zskColor" />"><bean:write name="userMdl" property="uioComment" /></td>
        </logic:equal>

        <logic:equal name="userMdl" property="uioStatus" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.UIOSTS_ETC) %>">
        <bean:define id="zskColor" value="td_type_kekkin" />
        <td class="<bean:write name="zskColor" />" nowrap>
        <a href="javascript:void(0);" onClick="return openUserInfoWindow(<bean:write name="userMdl" property="usrSid" />)">
        <span class="text_link">
        <bean:write name="userMdl" property="usiSei" />&nbsp;&nbsp;<bean:write name="userMdl" property="usiMei" />
        </span>
        </a>
        </td>
        <td class="<bean:write name="zskColor" />" nowrap>
        <input type="button" class="btn_change" name="btnChange" value="<gsmsg:write key="cmn.change" />" onClick="openUserIoWindow(<bean:write name="userMdl" property="usrSid"/>, <%= String.valueOf(jp.groupsession.v2.cmn.GSConst.UIOSTS_ETC) %>);"><br>
        </td>
        <td align="center" class="<bean:write name="zskColor" />"><gsmsg:write key="cmn.other" /></td>
        <td align="left" class="<bean:write name="zskColor" />"><bean:write name="userMdl" property="uioComment" /></td>
        </logic:equal>

        </tr>

        <logic:equal name="idx" value="0">
        <tr>
        <td colspan="4" class="table_border_bg_type1"><img src="./images/damy.gif" width="1" height="3" alt="<gsmsg:write key="cmn.space" />"/></td>
        </tr>

        </logic:equal>

        </logic:iterate>
        </logic:notEmpty>
        </table>
      </td>
      </tr>
      </table>
    </td>
    </tr>
    </table>
  </td>
  </tr>

  <tr>
  <td class="td_type_tab">
    <table width="100%" class="tl5">
    <tr>
    <td width="100%">&nbsp;</td>
    </tr>
    </table>
  </td>
  </tr>
  </table>
</td>
</tr>

</table>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</html:form>

<div id="smlPop" title="" style="display:none">
  <div id="smlCreateArea" width="100%" height="100%"></div>
</div>

</body>
</html:html>