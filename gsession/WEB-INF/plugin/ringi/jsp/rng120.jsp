<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="rng.rng120.08" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../ringi/css/ringi.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">

<html:form action="/ringi/rng120">

<input type="hidden" name="CMD" value="">

<html:hidden property="backScreen" />
<html:hidden property="rng010orderKey" />
<html:hidden property="rng010sortKey" />
<html:hidden property="rng010pageTop" />
<html:hidden property="rng010pageBottom" />
<html:hidden property="rngProcMode" />
<html:hidden property="rngTemplateMode" />

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
        <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="rng.rng120.08" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="<gsmsg:write key="cmn.setting" />" class="btn_setting_n1" onClick="buttonPush('conf');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backConfMenu');">
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" class="tl0" border="0" cellpadding="5">

    <logic:equal name="rng120Form" property="canUseSml" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.PLUGIN_USE) %>">
	    <logic:equal name="rng120Form" property="rng120AdmSmlNtf" value="<%= String.valueOf(jp.groupsession.v2.rng.RngConst.RAR_SML_NTF_USER) %>">
	      <tr>
	      <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="shortmail.notification" /></span></td>
	      <td align="left" class="td_type20" width="100%">
	
	        <table width="100%" cellpadding="0" cellspacing="0" border="0">
	        <tr>
	        <td align="left" width="100%">
	          <html:radio styleId="info" name="rng120Form" property="rng120smlNtf" value="<%= String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_SMAIL_TSUUCHI) %>" /><span class="text_base6"><label for="info"><gsmsg:write key="cmn.notify" /></label></span>&nbsp;
	          <html:radio styleId="notinfo" name="rng120Form" property="rng120smlNtf" value="<%= String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_SMAIL_NOT_TSUUCHI) %>" /><span class="text_base6"><label for="notinfo"><gsmsg:write key="cmn.dont.notify" /></label></span>
	          <div class="text_base7">
	            <gsmsg:write key="rng.rng120.07" />
	          </div>
	        </td>
	        </tr>
	        </table>
	
	      </td>
	      </tr>
	    </logic:equal>
	    <logic:notEqual name="rng120Form" property="rng120AdmSmlNtf" value="<%= String.valueOf(jp.groupsession.v2.rng.RngConst.RAR_SML_NTF_USER) %>">
	        <html:hidden property="rng120smlNtf"/>
	    </logic:notEqual>
	  </logic:equal>
      <logic:notEqual name="rng120Form" property="canUseSml" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.PLUGIN_USE) %>">
            <html:hidden property="rng120smlNtf"/>      
      </logic:notEqual>

      <tr>
      <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="rng.rng120.09" /></span></td>
      <td align="left" class="td_type20" width="100%">

        <table width="100%" cellpadding="0" cellspacing="0" border="0">
          <tr>
          <td align="left" width="100%">
            <html:select property="rng120ringiCnt">
              <html:optionsCollection name="rng120Form" property="rng120ringiCntLabel" value="value" label="label" />
            </html:select>
            <span class="text_base6"><gsmsg:write key="cmn.number" /></span>
            <br><span class="text_base7"><gsmsg:write key="rng.rng120.02" /></span>
          </td>
          </tr>
        </table>

      </td>
      </tr>


    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td align="right">
          <input type="button" value="<gsmsg:write key="cmn.setting" />" class="btn_setting_n1" onClick="buttonPush('conf');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backConfMenu');">
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