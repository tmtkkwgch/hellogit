<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@page import="jp.groupsession.v2.cmn.GSConstCommon"%>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/freeze.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/freeze.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/cmn110_upload.js?<%= GSConst.VERSION_PARAM %>"></script>


<title>[Group Session] <gsmsg:write key="cmn.cmn110.1" /></title>
</head>

<body class="body_03" onload="checkStatus();">

<div id="FreezePane">
<div id="InnerFreezePane"> </div>


<html:form action="common/cmn110" enctype="multipart/form-data">

<input type="hidden" name="CMD">
<html:hidden property="cmn110parentListName" />
<html:hidden property="cmn110pluginId" />
<html:hidden property="cmn110fileLimit" />
<html:hidden property="cmn110Decision" />
<html:hidden property="cmn110Mode" />
<html:hidden property="cmn110TempDirPlus" />
<html:hidden property="splitStr" />

<input type="hidden" name="cmn110uploadType" value="0">
<input type="hidden" name="cmn110freezeText" value="<gsmsg:write key="cmn.cmn110.4" />">

<span id="cmn110fileDataArea">
<logic:notEmpty name="cmn110Form" property="cmn110tempName" scope="request">
  <logic:iterate id="fileName" name="cmn110Form" property="cmn110tempName" scope="request">
    <input type="hidden" name="cmn110tempName" value="<bean:write name="fileName"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="cmn110Form" property="cmn110tempSaveName" scope="request">
  <logic:iterate id="fileName" name="cmn110Form" property="cmn110tempSaveName" scope="request">
    <input type="hidden" name="cmn110tempSaveName" value="<bean:write name="fileName"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="cmn110Form" property="fileList" scope="request">
  <logic:iterate id="item" name="cmn110Form" property="fileList" scope="request">
    <input type="hidden" name="fileList" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>
</span>

<html:hidden property="cmn110PrjUseFlg" />

<!-- プロジェクトプラグイン使用時 -->
<logic:equal name="cmn110Form" property="cmn110PrjUseFlg" value="1">
  <html:hidden property="cmn110PrjCmdMode" />
  <html:hidden property="cmn110PrjSid" />
</logic:equal>

<!--　BODY -->
<table width="545px" align="center">
  <logic:messagesPresent message="false">
  <tr>
  <td align="left"><span class="TXT02" id="cmn110ErrArea"><html:errors/></span></td>
  </tr>
  </logic:messagesPresent>
  <tr><td id="cmn110drpErrArea"></td></tr>
  <tr>
  <td align="right" class="table_bg_A5B4E1">

    <table width="100%" cellpadding="0" cellspacing="0" border="0">
      <tr>
      <td align="left" width="100%">
        <logic:notEqual name="cmn110Form" property="strMaxSize" value="0">
        <span class="text_tlw2"><gsmsg:write key="cmn.cmn110.2" /></span>
        </logic:notEqual>
        <logic:equal name="cmn110Form" property="strMaxSize" value="0">
        <span class="text_tlw"><gsmsg:write key="cmn.cmn110.3" /></span>
        </logic:equal>
      </td>
      </tr>
    </table>

  </td>
  </tr>

  <logic:notEqual name="cmn110Form" property="strMaxSize" value="0">
  <tr id="cmn110NormalArea">
  <td align="right">

    <table width="100%" cellpadding="0" cellspacing="0" border="0">
      <tr>
      <td align="left" width="100%">
        <html:file property="cmn110file[0]" size="40" maxlength="50" />
      </td>
      <td>
        <input type="button" name="btn_prjadd" class="btn_base1_6" value="<gsmsg:write key="cmn.attached" />" onClick="confirmButtonPush();freezeScreenThis('<gsmsg:write key="cmn.cmn110.4" />');">
      </td>
      </tr>
    </table>

  </td>
  </tr>

  <tr id="cmn110DragArea">
  <td>
    <div id="uploadArea" draggable="true" class="fileDropArea" style="height: 120px;">
    <br>
    <br>
    <gsmsg:write key="cmn.file.droparea.message" />
    </div>
  </td>
  </tr>


  <bean:define id="filMaxSize" name="cmn110Form" property="strMaxSize" type="java.lang.String" />
  <tr>
  <td align="left"><span class="text_r1"><gsmsg:write key="cmn.cmn110.5" arg0="<%= filMaxSize %>" /></span>
  </td>
  </tr>
  </logic:notEqual>

  <tr>
  <td  align="center">&nbsp;</td>
  </tr>
  <tr>
  <td  align="center">
    <input type="button" name="btn_prjadd" class="btn_close_n2" value="<gsmsg:write key="cmn.cmn110.7" />" onClick="tempClose();">
  </td>
  </tr>


</table>

</html:form>
</div>
</body>
</html:html>