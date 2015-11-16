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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../user/css/user.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="main.man030.8" /></title>
<script language="JavaScript" src="../common/js/cmn130.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03">
<html:form action="/common/cmn130">

<input type="hidden" name="CMD" value="">
<input type="hidden" name="cmn130cmdMode" value="">
<input type="hidden" name="cmn130selectGroupSid" value="">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!-- BODY -->
<table width="100%">
<tr>
<td width="100%" align="center" cellpadding="5" cellspacing="0">
  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="main.man030.8" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="35%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="65%">
      <input type="button" class="btn_add_n1" name="new" value="<gsmsg:write key="cmn.add" />" onClick="buttonPush('groupAdd', '');">
      <input type="button" class="btn_dell_n1" name="dell" value="<gsmsg:write key="cmn.delete" />" onClick="buttonPush('groupDelete', '');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backToMenu', '');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <html:errors />
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tl0 table_td_border">
    <tr class="td_type24">
    <td width="0%" nowrap>&nbsp;</td>
    <td width="0%" nowrap><gsmsg:write key="cmn.cmn130.1" /></td>
    <td width="0%" nowrap><gsmsg:write key="cmn.member" /></td>
    <td width="100%" nowrap><gsmsg:write key="cir.11" /></td>
    </tr>

    <logic:notEmpty name="cmn130Form" property="cmn130GroupList" scope="request">
      <logic:iterate id="grpMdl" name="cmn130Form" property="cmn130GroupList" scope="request" indexId="idx">

      <bean:define id="mod" value="0" />
      <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
        <bean:define id="tblColor" value="td_type1" />
      </logic:equal>
      <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
        <bean:define id="tblColor" value="td_type_usr" />
      </logic:notEqual>


        <tr class="<bean:write name="tblColor" />">
        <td nowrap>
          <html:multibox name="cmn130Form" property="cmn130delGroupSid">
            <bean:write name="grpMdl" property="mgpSid" />
          </html:multibox>
        </td>
        <td nowrap><a href="javascript:void(0)" onClick="return buttonPush('groupEdit', '<bean:write name="grpMdl" property="mgpSid" />');"><span class="sc_ttl_sat"><bean:write name="grpMdl" property="mgpName" /></span></a></td>
        <td align="right"><bean:define id="memSize" name="grpMdl" property="memberCnt" />
           　<a href="javascript:void(0)" onClick="return opnDetail('<bean:write name="grpMdl" property="mgpSid" />', '0');"><span class="sc_ttl_sat"><gsmsg:write key="bmk.23" arg0="<%= String.valueOf(memSize) %>"/></span></a></td>
        </td>
        <td nowrap><bean:write name="grpMdl" property="mgpMemo" filter="false" /></td>
        </tr>
      </logic:iterate>
    </logic:notEmpty>
    </table>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" class="btn_add_n1" name="new" value="<gsmsg:write key="cmn.add" />" onClick="buttonPush('groupAdd', '');">
      <input type="button" class="btn_dell_n1" name="dell" value="<gsmsg:write key="cmn.delete" />" onClick="buttonPush('groupDelete', '');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backToMenu', '');">
    </td>
    </tr>
    </table>
    <img src="../common/images/spacer.gif" width="1px" height="50px" border="0">
    <span class="text_bb1"><gsmsg:write key="cmn.cmn130.2" /></span>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tl0 table_td_border" >
    <tr class="td_type24">
    <td width="0%" nowrap>&nbsp;</td>
    <td width="0%" nowrap><gsmsg:write key="cmn.cmn130.1" /></td>
    <td width="0%" nowrap><gsmsg:write key="cmn.registant" /></td>
    <td width="0%" nowrap><gsmsg:write key="cmn.member" /></td>
    <td width="100%" nowrap><gsmsg:write key="cir.11" /></td>
    </tr>
    <logic:notEmpty name="cmn130Form" property="cmn130SharedGroupList" scope="request">
      <logic:iterate id="grpMdl" name="cmn130Form" property="cmn130SharedGroupList" scope="request" indexId="idx">

      <bean:define id="mod" value="0" />
      <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
        <bean:define id="tblColor" value="td_type1" />
      </logic:equal>
      <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
        <bean:define id="tblColor" value="td_type_usr" />
      </logic:notEqual>


        <tr class="<bean:write name="tblColor" />">
        <td nowrap>
            <html:multibox name="cmn130Form" property="cmn130delGroupSid" style="visibility:hidden"  value="false"></html:multibox>
        </td>
        <td nowrap><a href="javascript:void(0)" onClick="return opnDetail('<bean:write name="grpMdl" property="mgpSid" />', '1');"><span class="sc_ttl_sat"><bean:write name="grpMdl" property="mgpName" /></span></a></td>
        <td nowrap><a href="javascript:void(0)" onClick="return opnDetail('<bean:write name="grpMdl" property="mgpSid" />', '1');"><span class="sc_ttl_sat"><bean:write name="grpMdl" property="owner.usiSei" />&nbsp<bean:write name="grpMdl" property="owner.usiMei" /></span></a></td>
        <td align="right"><bean:define id="memSize" name="grpMdl" property="memberCnt" />
           　<a href="javascript:void(0)" onClick="return opnDetail('<bean:write name="grpMdl" property="mgpSid" />', '1');"><span class="sc_ttl_sat"><gsmsg:write key="bmk.23" arg0="<%= String.valueOf(memSize) %>"/></span></a></td>
        </td>
        <td nowrap><bean:write name="grpMdl" property="mgpMemo" filter="false" /></td>
        </tr>
      </logic:iterate>
    </logic:notEmpty>



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