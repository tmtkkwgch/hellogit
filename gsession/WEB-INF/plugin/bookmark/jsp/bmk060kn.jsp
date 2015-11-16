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
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../bookmark/css/bookmark.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="bmk.43" /></title>
</head>

<body class="body_03">
<html:form action="/bookmark/bmk060kn">
<input type="hidden" name="CMD" value="">
<input type="hidden" name="helpPrm" value="<bean:write name="bmk060knForm" property="bmk050ProcMode" />">

<logic:notEmpty name="bmk060knForm" property="bmk050DelSidList" scope="request">
  <logic:iterate id="delSid" name="bmk060knForm" property="bmk050DelSidList" scope="request">
    <input type="hidden" name="bmk050DelSidList" value="<bean:write name="delSid"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="bmk060knForm" property="bmk060LabelList">
<logic:iterate id="label" name="bmk060knForm" property="bmk060LabelList">
  <input type="hidden" name="bmk060LabelList" value="<bean:write name="label" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden name="bmk060knForm" property="bmk050LblSid" />
<html:hidden name="bmk060knForm" property="bmk050ProcMode" />
<html:hidden name="bmk060knForm" property="bmk060LblName" />
<html:hidden name="bmk060knForm" property="bmk060LblKbn" />

<logic:notEmpty name="bmk060knForm" property="bmk010delInfSid" scope="request">
<logic:iterate id="item" name="bmk060knForm" property="bmk010delInfSid" scope="request">
  <input type="hidden" name="bmk010delInfSid" value="<bean:write name="item"/>">
</logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/bookmark/jsp/bmk010_hiddenParams.jsp" %>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../bookmark/images/header_link01.gif" border="0" alt="<gsmsg:write key="bmk.43" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="bmk.43" /></span></td>
    <logic:equal name="bmk060knForm" property="bmk010mode" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KOJIN) %>">
    <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="cmn.entry.label.kn" />(<gsmsg:write key="bmk.30" />) ]</td>
    </logic:equal>
    <logic:equal name="bmk060knForm" property="bmk010mode" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_GROUP) %>">
    <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="cmn.entry.label.kn" />(<gsmsg:write key="bmk.51" />) ]</td>
    </logic:equal>
    <logic:equal name="bmk060knForm" property="bmk010mode" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KYOYU) %>">
    <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="cmn.entry.label.kn" />(<gsmsg:write key="bmk.34" />) ]</td>
    </logic:equal>

    <td width="100%" class="header_white_bg">
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="bmk.43" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" class="btn_base1" value="<gsmsg:write key="cmn.final" />" onClick="return buttonPush('bmk060knkakutei');">
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('bmk060knback');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

  </td>
  </tr>

  <logic:messagesPresent message="false">
  <tr><td width="100%"><html:errors /></td></tr>
  <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
  </logic:messagesPresent>
  <tr>
  <td>
      <% jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage(); %>
      <% String targetLabel = ""; %>

      <logic:equal name="bmk060knForm" property="bmk010mode" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KOJIN) %>">
      <% targetLabel = "<span class=\"attent1\">" + gsMsg.getMessage(request, "bmk.30") + "</span>"; %>
      <gsmsg:write key="bmk.45" arg0="<%= targetLabel %>" />
      </logic:equal>
      <logic:equal name="bmk060knForm" property="bmk010mode" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_GROUP) %>">
      <bean:define id="grpNameStr" name="bmk060knForm" property="bmk050GrpName" type="java.lang.String" />
      <% targetLabel = "<span class=\"attent1\">" + gsMsg.getMessage(request, "cmn.group") + gsMsg.getMessage(request, "wml.215") + grpNameStr + "</span>"; %>
      <gsmsg:write key="bmk.45" arg0="<%= targetLabel %>" />
      </logic:equal>
      <logic:equal name="bmk060knForm" property="bmk010mode" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KYOYU) %>">
      <% targetLabel = "<span class=\"attent1\">" + gsMsg.getMessage(request, "bmk.34") + "</span>"; %>
      <gsmsg:write key="bmk.45" arg0="<%= targetLabel %>" />
      </logic:equal>
  </td>
  </tr>
  <tr>
  <td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td>
  </tr>

  <tr>
  <td>
    <table class="tl0" width="100%" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.label" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td class="td_type1" width="80%"  nowrap>
    <span style="padding-left:0px"><bean:write name="bmk060knForm" property="bmk060LblName" /></span><br>
    <logic:notEmpty name="bmk060knForm" property="bmk060knLabelNameList">
      <span class="attent1" style="padding-top:10px; padding-left:10px"><gsmsg:write key="bmk.14" /><gsmsg:write key="wml.215" /></span><br>
      <logic:iterate id="labelName" name="bmk060knForm" property="bmk060knLabelNameList">
      <span style="padding-left:10px"><bean:write name="labelName" /></span><br>
      </logic:iterate>
    </logic:notEmpty>
    </td>
    </tr>
    </table>
  </td>
  </tr>
  <tr>
  <td>

  <img src="../common/images/spacer.gif" style="width:1px; height:10px;" border="0" alt="">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
    <input type="button" class="btn_base1" value="<gsmsg:write key="cmn.final" />" onClick="return buttonPush('bmk060knkakutei');">
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('bmk060knback');">
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