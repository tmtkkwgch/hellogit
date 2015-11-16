<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ page import="jp.groupsession.v2.enq.GSConstEnquete" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html lang="true">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META http-equiv="Content-Script-Type" content="text/javascript">
<META http-equiv="Content-Style-Type" content="text/css">
<title>[Group Session] <gsmsg:write key="cmn.admin.setting.menu" /></title>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script type="text/javascript" src="../enquete/js/enquete.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../enquete/css/enquete.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
</head>

<body class="body_03">
<html:form action="/enquete/enq920kn">
<!-- BODY -->
<input type="hidden" name="CMD">
<html:hidden property="cmd" />
<html:hidden property="backScreen" />

<!-- 検索条件hidden -->
<%@ include file="/WEB-INF/plugin/enquete/jsp/enq010_hiddenParams.jsp" %>

<logic:notEmpty name="enq920knForm" property="enq010priority">
<logic:iterate id="svPriority" name="enq920knForm" property="enq010priority">
  <input type="hidden" name="enq010priority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq920knForm" property="enq010status">
<logic:iterate id="svStatus" name="enq920knForm" property="enq010status">
  <input type="hidden" name="enq010status" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq920knForm" property="enq010svPriority">
<logic:iterate id="svPriority" name="enq920knForm" property="enq010svPriority">
  <input type="hidden" name="enq010svPriority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq920knForm" property="enq010svStatus">
<logic:iterate id="svStatus" name="enq920knForm" property="enq010svStatus">
  <input type="hidden" name="enq010svStatus" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq920knForm" property="enq010statusAnsOver">
<logic:iterate id="svStatusAnsOver" name="enq920knForm" property="enq010statusAnsOver">
  <input type="hidden" name="enq010statusAnsOver" value="<bean:write name="svStatusAnsOver" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq920knForm" property="enq010svStatusAnsOver">
<logic:iterate id="svStatusAnsOver" name="enq920knForm" property="enq010svStatusAnsOver">
  <input type="hidden" name="enq010svStatusAnsOver" value="<bean:write name="svStatusAnsOver" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="enq920knForm" property="enq920TypeListToList">
  <logic:iterate id="typeData" name="enq920knForm" property="enq920TypeListToList" indexId="idx">
    <input type="hidden" name="enq920TypeList[<%= String.valueOf(idx.intValue()) %>].etpSid" value='<bean:write name="typeData" property="etpSid"/>' >
    <input type="hidden" name="enq920TypeList[<%= String.valueOf(idx.intValue()) %>].etpDspSeq" value='<bean:write name="typeData" property="etpDspSeq"/>' >
    <input type="hidden" name="enq920TypeList[<%= String.valueOf(idx.intValue()) %>].etpName" value='<bean:write name="typeData" property="etpName"/>' >
    <input type="hidden" name="enq920TypeList[<%= String.valueOf(idx.intValue()) %>].emnCnt" value='<bean:write name="typeData" property="emnCnt"/>' >
    <input type="hidden" name="enq920TypeList[<%= String.valueOf(idx.intValue()) %>].emnResEnd" value='<bean:write name="typeData" property="emnResEnd"/>' >
    <input type="hidden" name="enq920TypeList[<%= String.valueOf(idx.intValue()) %>].emnOpenEnd" value='<bean:write name="typeData" property="emnOpenEnd"/>' >
  </logic:iterate>
</logic:notEmpty>

<div id="saveTbl">
  <logic:notEmpty name="enq920knForm" property="enq920DelListToList">
    <logic:iterate id="delList" name="enq920knForm" property="enq920DelListToList" indexId="idx">
      <div class="save">
        <input type="hidden" name="enq920DelList[<%= String.valueOf(idx.intValue()) %>].etpSid" value='<bean:write name="delList" property="etpSid"/>'>
      </div>
    </logic:iterate>
  </logic:notEmpty>
</div>

<!-- header -->
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!-- content -->
<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" width="70%">
  <tr>
  <td align="center">

    <!-- タイトル -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key='cmn.admin.setting' />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="enq.enq920kn.01"/> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key='cmn.admin.setting' />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt="<gsmsg:write key='cmn.function.btn' />"></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="<gsmsg:write key='cmn.final' />" class="btn_base1" onClick="buttonPush('enq920kncommit');">
          <input type="button" value="<gsmsg:write key='cmn.back' />" class="btn_back_n1" onClick="buttonPush('enq920knback');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt="<gsmsg:write key='cmn.function.btn' />"></td>
      </tr>
    </table>

    <!-- エラーメッセージ -->
    <div style="text-align:left">
    <html:errors/>
    </div>

    <table cellpadding="10" cellspacing="0" border="0" width="100%" class="t10" style="margin-top:15px;">
    <tbody id="typeTbl">

      <!-- アンケート種類 -->
      <tr>
        <td width="20%" valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
          <span class="text_bb1"><gsmsg:write key="enq.enq920.01"/></span>
        </td>
        <td width="80%" valign="middle" align="left" class="td_wt">
          <span class="text_base">
            <logic:notEmpty name="enq920knForm" property="enq920knTypeList" scope="request">
              <logic:iterate id="list" name="enq920knForm" property="enq920knTypeList" scope="request">
                <bean:write name="list" property="etpName" /><br>
              </logic:iterate>
            </logic:notEmpty>
          </span>
        </td>
      </tr>

    </tbody>
    </table>

    <img src="../common/images/spacer.gif" width="1" height="10" alt="<gsmsg:write key='cmn.spacer' />" border="0">

    <table cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td align="right">
          <input type="button" value="<gsmsg:write key='cmn.final' />" class="btn_base1" onClick="buttonPush('enq920kncommit');">
          <input type="button" value="<gsmsg:write key='cmn.back' />" class="btn_back_n1" onClick="buttonPush('enq920knback');">
        </td>
      </tr>
    </table>

  </td>
  </tr>
  </table>

</td>
</tr>
</table>


</html:form>
<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>