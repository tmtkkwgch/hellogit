<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="bmk.43" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../bookmark/css/bookmark.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="bmk.43" /></title>
</head>

<body class="body_03">

<html:form action="/bookmark/bmk060">

<input type="hidden" name="CMD" value="">
<input type="hidden" name="helpPrm" value="<bean:write name="bmk060Form" property="bmk050ProcMode" />">

<logic:notEmpty name="bmk060Form" property="bmk050DelSidList" scope="request">
  <logic:iterate id="delSid" name="bmk060Form" property="bmk050DelSidList" scope="request">
    <input type="hidden" name="bmk050DelSidList" value="<bean:write name="delSid"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="bmk060Form" property="bmk060LabelList">
<logic:iterate id="label" name="bmk060Form" property="bmk060LabelList">
  <input type="hidden" name="bmk060LabelList" value="<bean:write name="label" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden name="bmk060Form" property="bmk050LblSid" />
<html:hidden name="bmk060Form" property="bmk050ProcMode" />

<logic:notEmpty name="bmk060Form" property="bmk010delInfSid" scope="request">
<logic:iterate id="item" name="bmk060Form" property="bmk010delInfSid" scope="request">
  <input type="hidden" name="bmk010delInfSid" value="<bean:write name="item"/>">
</logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/bookmark/jsp/bmk010_hiddenParams.jsp" %>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!-- BODY -->
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
        <logic:equal name="bmk060Form" property="bmk010mode" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KOJIN) %>">
        <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="cmn.entry.label" />(<gsmsg:write key="bmk.30" />) ]</td>
        </logic:equal>
        <logic:equal name="bmk060Form" property="bmk010mode" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_GROUP) %>">
        <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="cmn.entry.label" />(<gsmsg:write key="bmk.51" />) ]</td>
        </logic:equal>
        <logic:equal name="bmk060Form" property="bmk010mode" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KYOYU) %>">
        <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="cmn.entry.label" />(<gsmsg:write key="bmk.34" />) ]</td>
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
      <input type="button" value="<gsmsg:write key="cmn.ok" />" class="btn_ok1" onClick="buttonPush('bmk060ok');">
      <logic:equal name="bmk060Form" property="bmk050ProcMode" value="1">
      <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="return buttonPush('bmk060del');">
      </logic:equal>
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('bmk060back');">
      </td>
      <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

    <logic:messagesPresent message="false">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr><td align="left"><span class="TXT02"><html:errors/></span></td></tr>
    </table>
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
    </logic:messagesPresent>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td>
    <% jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage(); %>
    <% String targetLabel = ""; %>
    <logic:equal name="bmk060Form" property="bmk010mode" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KOJIN) %>">
    <% targetLabel = "<span class=\"attent1\">" + gsMsg.getMessage(request, "bmk.30") + "</span>"; %>
    <gsmsg:write key="bmk.45" arg0="<%= targetLabel %>" />
    </logic:equal>
    <logic:equal name="bmk060Form" property="bmk010mode" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_GROUP) %>">
    <bean:define id="grpNameStr" name="bmk060Form" property="bmk050GrpName" type="java.lang.String" />
    <% targetLabel = "<span class=\"attent1\">" + gsMsg.getMessage(request, "cmn.group") + gsMsg.getMessage(request, "wml.215") + grpNameStr + "</span>"; %>
    <gsmsg:write key="bmk.45" arg0="<%= targetLabel %>" />
    </logic:equal>
    <logic:equal name="bmk060Form" property="bmk010mode" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KYOYU) %>">
    <% targetLabel = "<span class=\"attent1\">" + gsMsg.getMessage(request, "bmk.34") + "</span>"; %>
    <gsmsg:write key="bmk.45" arg0="<%= targetLabel %>" />
    </logic:equal>
    </td>
    </tr>
    </table>
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <table width="100%" class="tl0" border="0" cellpadding="5">
      <tr>
      <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.label" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
      <td align="left" class="td_type20" width="90%">

        <table width="0%" border="0">
        <tr>
        <td colspan="3">
          <html:text name="bmk060Form" property="bmk060LblName" maxlength="20" style="width:335px;"/><br>
        </td>
        </tr>
        <tr>
        <td colspan="3" style="padding-top: 4px; padding-bottom: 4px;">
          <html:radio name="bmk060Form" property="bmk060LblKbn" styleId="bmk060LblKbn0" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.LABEL_TOGO_NO) %>" onclick="buttonPush('redraw');" /><span class="text_base"><label for="bmk060LblKbn0"><gsmsg:write key="bmk.bmk060.01" /></label></span>&nbsp;
          <html:radio name="bmk060Form" property="bmk060LblKbn" styleId="bmk060LblKbn1" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.LABEL_TOGO_YES) %>" onclick="buttonPush('redraw');" /><span class="text_base"><label for="bmk060LblKbn1"><gsmsg:write key="bmk.bmk060.02" /></label></span>&nbsp;<br>
        </td>
        </tr>

        <logic:equal name="bmk060Form" property="bmk060LblKbn" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.LABEL_TOGO_YES) %>">
          <tr>
          <td width="40%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="bmk.14" /></span></td>
          <td width="20%" align="center">&nbsp;</td>
          <td width="40%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="cmn.label" /></span></td>
          </tr>
          <tr>
          <td>
            <html:select name="bmk060Form" property="bmk060SelectLeftLabel" size="6" multiple="true" styleClass="select01">
              <logic:notEmpty name="bmk060Form" property="bmk060LeftLabelList">
                <html:optionsCollection name="bmk060Form" property="bmk060LeftLabelList" value="value" label="label" />
              </logic:notEmpty>
              <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
            </html:select>
          </td>
          <td align="center">
            <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('addLabel');"><br><br>
            <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('removeLabel');">
          </td>
          <td align="center">
            <html:select name="bmk060Form" property="bmk060SelectRightLabel" size="6" multiple="true" styleClass="select01">
              <logic:notEmpty name="bmk060Form" property="bmk060RightLabelList">
                <html:optionsCollection name="bmk060Form" property="bmk060RightLabelList" value="value" label="label" />
              </logic:notEmpty>
              <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
            </html:select>
          </td>
          </tr>
        </logic:equal>
        </table>

      </td>
      </tr>

    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" width="100%">
      <tr>
      <td align="right">
      <input type="button" value="<gsmsg:write key="cmn.ok" />" class="btn_ok1" onClick="buttonPush('bmk060ok');">
      <logic:equal name="bmk060Form" property="bmk050ProcMode" value="1">
      <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="return buttonPush('bmk060del');">
      </logic:equal>
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('bmk060back');">
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