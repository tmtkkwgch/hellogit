<%@ page pageEncoding="Windows-31J" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="ntp.1" /> [<gsmsg:write key="ntp.1" /><gsmsg:write key="cmn.import" />]</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../nippou/js/ntp110.js"></script>
<script language="JavaScript" src="../common/js/cmn110.js"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css' type='text/css'>
<link rel=stylesheet href='../nippou/css/nippou.css' type='text/css'>
</head>

<body class="body_03" onunload="windowClose();">
<html:form action="/nippou/ntp110">

<input type="hidden" name="CMD" value="">
<html:hidden property="dspMod" />
<html:hidden property="ntp010SelectUsrSid" />
<html:hidden property="ntp010SelectUsrKbn" />
<html:hidden property="ntp010SelectDate" />
<html:hidden property="ntp030SessionSid" />
<html:hidden property="ntp030SelectUsrSid" />
<html:hidden property="ntp030Offset" />
<html:hidden property="ntp010NipSid" />
<html:hidden property="ntp030LabelDate" />
<html:hidden property="ntp010DspDate" />
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table width="100%">
<tr>
<td width="100%" align="center">

  <table width="70%" class="tl0">
  <tr>
  <td align="left">

    <!-- É^ÉCÉgÉã -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../nippou/images/header_nippou_02.gif" border="0" alt="<gsmsg:write key="ntp.1" />"></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="ntp.1" /><gsmsg:write key="cmn.import" /> ]</td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="ntp.1" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.import" />" class="btn_csv_n1" onclick="buttonPush('110_import');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('back');"></td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
    <logic:messagesPresent message="false"><html:errors /></logic:messagesPresent>
    <img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt="">
  </td>
  </tr>

  <tr>
  <td>
    <table width="100%" cellpadding="5" cellspacing="0" class="tl0">

    <tr>
    <%--
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1">ìoò^ëŒè€</span><span class="text_r2">Å¶</span></td>
    <td align="left" class="td_wt" width="80%" nowrap>
      <html:select property="ntp110SltGroup" styleClass="select01" onchange="changeGroupCombo();">

      <logic:notEmpty name="ntp110Form" property="ntp110GroupLabel" scope="request">
      <logic:iterate id="gpBean" name="ntp110Form" property="ntp110GroupLabel" scope="request">

      <bean:define id="gpValue" name="gpBean" property="value" type="java.lang.String" />
      <logic:equal name="gpBean" property="styleClass" value="0">
      <html:option value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
      </logic:equal>

      <logic:notEqual name="gpBean" property="styleClass" value="0">
      <html:option styleClass="select03" value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
      </logic:notEqual>

      </logic:iterate>
      </logic:notEmpty>

      </html:select>

      <html:select property="ntp110SltUser" styleClass="select01">
      <html:optionsCollection name="ntp110Form" property="ntp110UserLabel" value="value" label="label" />
      </html:select>

    </td>
    --%>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="main.src.34" /></span><span class="text_r2">Å¶</span></td>
    <td align="left" class="td_wt" width="80%" nowrap>
      <a href="javascript:void(0);" onclick="opnTemp('ntp110SelectFiles', '<%= jp.groupsession.v2.ntp.GSConstNippou.PLUGIN_ID_NIPPOU %>', '1');">
      <img alt="<gsmsg:write key="cmn.attached" />" height="25" src="../common/images/btn_attach.gif" width="60" border="0"></a>
      &nbsp;<img src="../common/images/btn_dell.gif" alt="<gsmsg:write key="cmn.delete" />" height="25" width="60" onclick="buttonPush('delete');"><br>

      <html:select property="ntp110SelectFiles" styleClass="select01" multiple="false" size="1">
        <html:optionsCollection name="ntp110Form" property="ntp110FileLabelList" value="value" label="label" />
      </html:select>

      &nbsp;
      <span class="text_base">
          *<a href="../nippou/ntp110.do?CMD=ntp110_sample"><gsmsg:write key="reserve.111" /></a>
      </span>
      <br>
    </td>
    </tr>
    </table>
  </td>
  </tr>

  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" width="100%">
    <tr>
    <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">
      <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td width="100%" align="right">
      <input type="button" value="<gsmsg:write key="cmn.import" />" class="btn_csv_n1" onclick="buttonPush('110_import');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('back');"></td>
      </td>
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
</body>
</html:html>