<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<% String okiniDspOff = String.valueOf(jp.groupsession.v2.fil.GSConstFile.MAIN_OKINI_DSP_OFF);
   String okiniDspOn = String.valueOf(jp.groupsession.v2.fil.GSConstFile.MAIN_OKINI_DSP_ON);
%>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.filekanri" />　<gsmsg:write key="cmn.display.settings" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/file.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/dtree.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
</head>

<body class="body_03">

<html:form action="/file/fil120">
<input type="hidden" name="CMD" value="">

<html:hidden property="backDsp" />
<html:hidden property="backScreen" />
<html:hidden property="fil010SelectCabinet" />
<html:hidden property="fil010SelectDirSid" />
<html:hidden property="filSearchWd" />
<html:hidden property="backMainFlg" />

<logic:notEmpty name="fil120Form" property="fil040SelectDel" scope="request">
  <logic:iterate id="del" name="fil120Form" property="fil040SelectDel" scope="request">
    <input type="hidden" name="fil040SelectDel" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil120Form" property="fil010SelectDelLink" scope="request">
  <logic:iterate id="del" name="fil120Form" property="fil010SelectDelLink" scope="request">
    <input type="hidden" name="fil010SelectDelLink" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">

  <table  cellpadding="0" cellspacing="0"width="70%">
  <tr>
  <td align="center">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.filekanri" />　<gsmsg:write key="cmn.display.settings" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" name="btn_add" class="btn_ok1" value="OK" onClick="buttonPush('fil120ok');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('fil120back');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <logic:messagesPresent message="false">
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr><td width="100%"><html:errors /></td></tr>
    </table>
    </logic:messagesPresent>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="10" cellspacing="0" border="0" width="100%" class="tl_u2">

    <tr>
    <td valign="middle" align="left" class="td_sub_title3" nowrap>
    <span class="text_bb1"><gsmsg:write key="cmn.main.view2" /><span class="text_r2">※</span><br><gsmsg:write key="fil.2" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" >
      <html:radio name="fil120Form" styleId="okini_01" property="fil120MainSort" value="<%= okiniDspOff %>" /><label for="okini_01"><span class="text_base7"><gsmsg:write key="cmn.dont.show" /></span></label>&nbsp;
      <html:radio name="fil120Form" styleId="okini_02" property="fil120MainSort" value="<%= okiniDspOn %>" /><label for="okini_02"><span class="text_base7"><gsmsg:write key="cmn.display.ok" /></span></label>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="td_sub_title3" nowrap>
    <span class="text_bb1"><gsmsg:write key="cmn.main.view2" /><span class="text_r2">※</span><br><gsmsg:write key="fil.1" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">
    <logic:notEmpty name="fil120Form" property="fil120CallLblList">
      <html:select property="fil120MainCall">
      <html:optionsCollection name="fil120Form" property="fil120CallLblList" value="value" label="label" />
      </html:select>
    </logic:notEmpty>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="td_sub_title3" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="fil.26" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">
    <logic:notEmpty name="fil120Form" property="fil120RirekiCntLblList">
      <html:select property="fil120RirekiCnt">
      <html:optionsCollection name="fil120Form" property="fil120RirekiCntLblList" value="value" label="label" />
      </html:select>
    </logic:notEmpty>
    <span class="text_base">※<gsmsg:write key="fil.fil120.1" /></span>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="td_sub_title3" nowrap>
    <span class="text_bb1"><gsmsg:write key="fil.24" /><span class="text_r2">※</span><br></span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">
    <logic:notEmpty name="fil120Form" property="fil120CallLblList">
      <html:select property="fil120Call">
      <html:optionsCollection name="fil120Form" property="fil120CallLblList" value="value" label="label" />
      </html:select>
    </logic:notEmpty>
    </td>
    </tr>

    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" name="btn_add" class="btn_ok1" value="OK" onClick="buttonPush('fil120ok');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('fil120back');">
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