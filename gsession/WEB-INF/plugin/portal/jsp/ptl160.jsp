<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<%@page import="jp.groupsession.v2.cmn.GSConstCommon"%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/freeze.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../portal/js/ptl160.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/freeze.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="cmn.cmn110.1" /></title>
</head>

<body class="body_03" onload="checkStatus();">

<div id="FreezePane">
<div id="InnerFreezePane"> </div>


<html:form action="portal/ptl160" enctype="multipart/form-data">

<input type="hidden" name="CMD">

<html:hidden property="ptlCmdMode" />
<html:hidden property="ptlPortletSid" />
<html:hidden property="ptlPortletImageSid" />

<html:hidden property="ptl160tempName" />
<html:hidden property="ptl160tempSaveName" />
<html:hidden property="ptl160Decision" />
<html:hidden property="splitStr" />

<!--　BODY -->
<table width="100%">
  <logic:messagesPresent message="false">
  <tr>
  <td align="left"><span class="TXT02"><html:errors/></span></td>
  </tr>
  </logic:messagesPresent>
  <tr>
  <td align="right" class="table_bg_A5B4E1">

    <table width="100%" cellpadding="0" cellspacing="0" border="0">
      <tr>
      <td align="left" width="100%">
        <logic:notEqual name="ptl160Form" property="strMaxSize" value="0">
        <span class="text_tlw2"><gsmsg:write key="cmn.cmn110.2" /></span>
        </logic:notEqual>
        <logic:equal name="ptl160Form" property="strMaxSize" value="0">
        <span class="text_tlw"><gsmsg:write key="cmn.cmn110.3" /></span>
        </logic:equal>
      </td>
      </tr>
    </table>

  </td>
  </tr>

  <logic:notEqual name="ptl160Form" property="strMaxSize" value="0">
  <tr>
  <td align="right">

    <table width="100%" cellpadding="0" cellspacing="0" border="0">
      <tr>
      <td align="left" width="100%">
        <html:file property="ptl160file" size="40" maxlength="50" />
      </td>
      <td>
        <input type="button" name="btn_ptlImageAdd" class="btn_base1_6" value="<gsmsg:write key="cmn.attached" />" onClick="ptlImageEntry();freezeScreenThis('<gsmsg:write key="cmn.cmn110.4" />');">
      </td>
      </tr>
    </table>

  </td>
  </tr>

  <bean:define id="filMaxSize" name="ptl160Form" property="strMaxSize" type="java.lang.String" />
  <tr><td align="left"><span class="text_r1"><gsmsg:write key="cmn.cmn110.5" arg0="<%= filMaxSize %>" /></span>
  </td>
  </tr>
  </logic:notEqual>

  <tr>
  <td  align="center">&nbsp;</td>
  </tr>
  <tr>
  <td  align="center">
    <input type="button" name="btn_ptlImageClose" class="btn_close_n2" value="<gsmsg:write key="cmn.cmn110.7" />" onClick="ptlImagePopupClose();">
  </td>
  </tr>


</table>

</html:form>
</div>
</body>
</html:html>