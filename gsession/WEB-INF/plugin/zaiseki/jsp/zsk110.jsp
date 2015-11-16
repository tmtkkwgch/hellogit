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
<script language="JavaScript" src="../zaiseki/js/zsk110.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../zaiseki/css/zaiseki.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[GroupSession] <gsmsg:write key="cmn.zaiseki.management" /></title>
</head>

<body class="body_03" onload="enableDisable();">
<html:form action="/zaiseki/zsk110">
<input type="hidden" name="CMD" value="">
<html:hidden name="zsk110Form" property="backScreen" />
<html:hidden name="zsk110Form" property="selectZifSid" />
<html:hidden name="zsk110Form" property="uioStatus" />
<html:hidden name="zsk110Form" property="uioStatusBiko" />
<html:hidden name="zsk110Form" property="sortKey" />
<html:hidden name="zsk110Form" property="orderKey" />
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>


<!-- BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">
  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="zsk.11" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" class="btn_ok1" value="OK" onClick="return buttonPush('zsk110Ok');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('zsk110back');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <logic:messagesPresent message="false">
      <table width="100%">
      <tr>
        <td align="left"><span class="TXT02"><html:errors/></span></td>
      </tr>
      </table>
    </logic:messagesPresent>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="10" cellspacing="0" border="0" width="100%" class="tl_u2">

    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="zsk.11" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td  valign="middle" align="left" class="td_wt" width="80%">
      <span class="text_r1"><gsmsg:write key="zsk.zsk110.01" /></span><br>
      <html:radio name="zsk110Form" property="zsk110UpdateKbn" styleId="zsk110UpdateKbn0" value="<%= String.valueOf(jp.groupsession.v2.zsk.GSConstZaiseki.FIXED_UPDATE_ON) %>" onchange="enableDisable();" /><span class="text_base"><label for="zsk110UpdateKbn0"><gsmsg:write key="cmn.setting.do" /></label></span>&nbsp;
      <html:radio name="zsk110Form" property="zsk110UpdateKbn" styleId="zsk110UpdateKbn1" value="<%= String.valueOf(jp.groupsession.v2.zsk.GSConstZaiseki.FIXED_UPDATE_OFF) %>" onchange="enableDisable();" /><span class="text_base"><label for="zsk110UpdateKbn1"><gsmsg:write key="cmn.noset" /></label>&nbsp;</span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="20%"><span class="text_bb1"><gsmsg:write key="cmn.starttime" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td class="td_type1" width="80%">

      <html:select name="zsk110Form" property="zsk110StartTime">
        <logic:notEmpty name="zsk110Form" property="zsk110TimeList">
        <html:optionsCollection name="zsk110Form" property="zsk110TimeList" value="value" label="label" />
        </logic:notEmpty>
      </html:select>

    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="20%"><span class="text_bb1"><gsmsg:write key="zsk.20" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td  valign="middle" align="left" class="td_wt" width="80%">
      <table class="tl0">
      <tr>
      <td colspan="5"><span class="text_r1"><gsmsg:write key="zsk.zsk110.02" /></span></td>
      </tr>
      <td class="td_type1" nowrap>
      <html:radio name="zsk110Form" property="zsk110Status" styleId="zsk110Status1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.UIOSTS_IN) %>" /><span class="text_base2"><label for="zsk110Status1"><gsmsg:write key="cmn.zaiseki" /></label></span>&nbsp;
      </td>
      <td>&nbsp;</td>
      <td class="td_type_gaisyutu" nowrap>
      <html:radio name="zsk110Form" property="zsk110Status" styleId="zsk110Status2" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.UIOSTS_LEAVE) %>" /><span class="text_base2"><label for="zsk110Status2"><gsmsg:write key="cmn.absence" /></label>&nbsp;</span>
      </td>
      <td>&nbsp;</td>
      <td class="td_type_kekkin" nowrap>
      <html:radio name="zsk110Form" property="zsk110Status" styleId="zsk110Status0" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.UIOSTS_ETC) %>" /><span class="text_base2"><label for="zsk110Status0"><gsmsg:write key="cmn.other" /></label>&nbsp;</span>
      </td>
      </tr>
      </table>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="zsk.23" /></span></td>
    <td  valign="middle" align="left" class="td_wt" width="80%">
      <span class="text_r1"><gsmsg:write key="zsk.zsk110.03" /></span><br>
      <html:text name="zsk110Form" property="zsk110Msg" maxlength="30" style="width:153px;"/>
    </td>
    </tr>

    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
    <td width="100%" align="right">
      <input type="button" class="btn_ok1" value="OK" onClick="return buttonPush('zsk110Ok');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('zsk110back');">
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