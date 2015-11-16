<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ page import="jp.groupsession.v2.enq.GSConstEnquete" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META http-equiv="Content-Script-Type" content="text/javascript">
<META http-equiv="Content-Style-Type" content="text/css">
<title>[Group Session] <gsmsg:write key="cmn.admin.setting.menu" /></title>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<script type="text/javascript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script type="text/javascript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../enquete/js/enquete.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
</head>

<body class="body_03">
<html:form action="/enquete/enq910kn">
<!-- BODY -->
<input type="hidden" name="CMD">
<html:hidden property="cmd" />
<html:hidden property="backScreen" />
<html:hidden property="enq910initFlg" />
<html:hidden property="enq910TaisyoKbn" />

<!-- 検索条件hidden -->
<%@ include file="/WEB-INF/plugin/enquete/jsp/enq010_hiddenParams.jsp" %>

<logic:notEmpty name="enq910knForm" property="enq010priority">
<logic:iterate id="svPriority" name="enq910knForm" property="enq010priority">
  <input type="hidden" name="enq010priority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq910knForm" property="enq010status">
<logic:iterate id="svStatus" name="enq910knForm" property="enq010status">
  <input type="hidden" name="enq010status" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq910knForm" property="enq010svPriority">
<logic:iterate id="svPriority" name="enq910knForm" property="enq010svPriority">
  <input type="hidden" name="enq010svPriority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq910knForm" property="enq010svStatus">
<logic:iterate id="svStatus" name="enq910knForm" property="enq010svStatus">
  <input type="hidden" name="enq010svStatus" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq910knForm" property="enq010statusAnsOver">
<logic:iterate id="svStatusAnsOver" name="enq910knForm" property="enq010statusAnsOver">
  <input type="hidden" name="enq010statusAnsOver" value="<bean:write name="svStatusAnsOver" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq910knForm" property="enq010svStatusAnsOver">
<logic:iterate id="svStatusAnsOver" name="enq910knForm" property="enq010svStatusAnsOver">
  <input type="hidden" name="enq010svStatusAnsOver" value="<bean:write name="svStatusAnsOver" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="enq910knForm" property="enq910MakeSenderList" scope="request">
    <logic:iterate id="ulBean" name="enq910knForm" property="enq910MakeSenderList" scope="request">
    <input type="hidden" name="enq910MakeSenderList" value="<bean:write name="ulBean" />">
    </logic:iterate>
</logic:notEmpty>

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
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="enq.enq910kn.01"/> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt="<gsmsg:write key='cmn.function.btn' />"></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="<gsmsg:write key='cmn.final' />" class="btn_base1" onClick="buttonPush('enq910kncommit');">
          <input type="button" value="<gsmsg:write key='cmn.back' />" class="btn_back_n1" onClick="buttonPush('enq910knback');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt="<gsmsg:write key='cmn.function.btn' />"></td>
      </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1" height="5" border="0" alt="<gsmsg:write key='cmn.spacer' />">

    <!-- エラーメッセージ -->
    <div style="text-align:left">
    <html:errors/>
    </div>

    <table cellpadding="10" cellspacing="0" border="0" width="100%" class="tl_u2">
    <tbody>

      <!-- 対象者区分 -->
      <tr>
        <td valign="middle" align="left" class="table_bg_A5B4E1" width="20%" nowrap>
          <span class="text_bb1"><gsmsg:write key="enq.enq910.01"/></span>
        </td>
        <td align="left" class="td_wt" width="80%">
          <span class="text_base">
            <logic:equal name="enq910knForm" property="enq910knTaisyoKbn" value="<%= String.valueOf(GSConstEnquete.CONF_TAISYO_LIMIT) %>" >
              <gsmsg:write key="enq.enq910.02" />
            </logic:equal>
            <logic:notEqual name="enq910knForm" property="enq910knTaisyoKbn" value="<%= String.valueOf(GSConstEnquete.CONF_TAISYO_LIMIT) %>" >
              <gsmsg:write key="enq.enq910.03" />
            </logic:notEqual>
          </span>
        </td>
      </tr>

    <logic:equal name="enq910knForm" property="enq910knTaisyoKbn" value="<%= String.valueOf(GSConstEnquete.CONF_TAISYO_LIMIT) %>" >
      <!-- アンケート発信対象者 -->
      <tr>
        <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
          <span class="text_bb1"><gsmsg:write key="enq.70"/></span>
        </td>
        <td valign="middle" align="left" class="td_wt">
          <span class="text_base">
            <logic:notEmpty name="enq910knForm" property="enq910knAddSenderLabel" scope="request">
              <logic:iterate id="labelValueBean" name="enq910knForm" property="enq910knAddSenderLabel" scope="request">
                <bean:write name="labelValueBean" property="label" /><br>
              </logic:iterate>
            </logic:notEmpty>
          </span>
        </td>
      </tr>
    </logic:equal>

    </tbody>
    </table>

    <img src="../common/images/spacer.gif" width="1" height="10" border="0" alt="<gsmsg:write key='cmn.spacer' />">

    <table cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td align="right">
          <input type="button" value="<gsmsg:write key='cmn.final' />" class="btn_base1" onClick="buttonPush('enq910kncommit');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('enq910knback');">
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