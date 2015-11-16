<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../portal/js/ptl030.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../portal/css/portal.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="ptl.2" /></title>
</head>

<body class="body_03">

<html:form action="/portal/ptl030">
<input type="hidden" name="CMD" value="init">
<html:hidden property="ptlPortalSid" />
<%@ include file="/WEB-INF/plugin/portal/jsp/ptl_hiddenParams.jsp" %>
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>

        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text">[ <gsmsg:write key="ptl.2" /> ]</td>
        <td width="0%">
        <img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt="<gsmsg:write key="ptl.1" />"></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" name="btn_portlet" class="btn_portlet_n2" value="<gsmsg:write key="ptl.9" />" onClick="buttonPush2('portletManager');">
          <input type="button" name="btn_facilities_mnt" class="btn_add_n1" value="<gsmsg:write key="cmn.add" />" onClick="addPortal();">
          <input type="button" name="btn_back_ktool" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush2('confMenu');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt="<gsmsg:write key="ptl.1" />"></td>
      </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt="">
  </td>
  </tr>

  <logic:messagesPresent message="false">
  <tr>
    <td align="left"><span class="TXT02"><html:errors/></span></td>
  </tr>
  </logic:messagesPresent>

  <tr>
  <td align="left">
    <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.up" />" name="btn_upper" onClick="buttonPush2('ptl030up');">
    <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.down" />" name="btn_downer" onClick="buttonPush2('ptl030down');">
    &nbsp;&nbsp;
    <gsmsg:write key="ptl.ptl030.1" />
  </td>
  </tr>

  <tr><td><img src="../common/images/spacer.gif" style="width:0px; height:2px;" border="0" alt=""></td></tr>

  <tr>
  <td>

    <table class="tl0 table_td_border2" cellpadding="5" cellspacing="0" border="0" width="100%">

    <tr>
    <th class="table_bg_7D91BD" width="6%" nowrap>&nbsp;</th>
    <th align="center" class="table_bg_7D91BD" width="35%"><span class="text_tlw"><gsmsg:write key="ptl.4" /></span></th>
    <th align="center" class="table_bg_7D91BD" width="10%"><span class="text_tlw"><gsmsg:write key="cmn.public" /></span></th>
    <th align="center" class="table_bg_7D91BD" width="49%"><span class="text_tlw"><gsmsg:write key="cmn.memo" /></span></th>
    </tr>

    <bean:define id="trColor" value="" />

    <logic:notEmpty name="ptl030Form" property="ptl030portalList">
    <logic:iterate id="ptlMdl" name="ptl030Form" property="ptl030portalList" indexId="idx">

    <bean:define id="sortValue" name="ptlMdl" property="ptlSid" />

    <% String[] trColors = new String[] {"td_type1", "td_type_usr"}; %>
    <% trColor = trColors[(idx.intValue() % 2)]; %>

    <tr align="center" class="<%= trColor %>">
    <td align="center" nowrap>
    <html:radio property="ptl030sortPortal" value="<%= String.valueOf(sortValue) %>" />
    </td>
    <td align="left">

    <logic:equal name="ptlMdl" property="ptlSid" value="0">
      <span class="text_base"><bean:write name="ptlMdl" property="ptlName" /></span>
    </logic:equal>
    <logic:notEqual name="ptlMdl" property="ptlSid" value="0">
      <a href="javascript:void(0);" onClick="return editPortal(<bean:write name="ptlMdl" property="ptlSid" />);">
      <span class="text_link"><bean:write name="ptlMdl" property="ptlName" /></span>
      </a>
    </logic:notEqual>

    </td>
    <td align="center">
      <logic:equal name="ptlMdl" property="ptlOpen" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstPortal.PTL_OPENKBN_OK) %>"><gsmsg:write key="cmn.show" /></logic:equal>
      <logic:equal name="ptlMdl" property="ptlOpen" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstPortal.PTL_OPENKBN_NG) %>"><gsmsg:write key="cmn.hide" /></logic:equal>
    </td>
    <td align="left">
      <span class="text_base"><bean:write name="ptlMdl" property="ptlDescriptionView" filter="false"/></span>
    </td>
    </tr>


    </logic:iterate>
    </logic:notEmpty>

    </table>

  </td>
  </tr>

  <tr>
  <td>

    <img src="../common/images/spacer.gif" width="1" height="10" border="0" alt="">

    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
      <td width="100%" align="right">
          <input type="button" name="btn_portlet" class="btn_portlet_n2" value="<gsmsg:write key="ptl.9" />" onClick="buttonPush2('portletManager');">
          <input type="button" name="btn_facilities_mnt" class="btn_add_n1" value="<gsmsg:write key="cmn.add" />" onClick="addPortal();">
          <input type="button" name="btn_back_ktool" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush2('confMenu');">
      </td>
    </tr>
    </table>

  </td>
  </tr>
  </table>

</td>
</tr>
</table>

</table>

</html:form>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</body>
</html:html>