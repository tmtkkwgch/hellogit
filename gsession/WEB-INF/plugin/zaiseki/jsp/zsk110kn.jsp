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
<link rel=stylesheet href='../zaiseki/css/zaiseki.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[GroupSession] <gsmsg:write key="cmn.zaiseki.management" /></title>
</head>

<body class="body_03">
<html:form action="/zaiseki/zsk110kn">
<input type="hidden" name="CMD" value="">
<html:hidden name="zsk110knForm" property="backScreen" />
<html:hidden name="zsk110knForm" property="selectZifSid" />
<html:hidden name="zsk110knForm" property="uioStatus" />
<html:hidden name="zsk110knForm" property="uioStatusBiko" />
<html:hidden name="zsk110knForm" property="sortKey" />
<html:hidden name="zsk110knForm" property="orderKey" />
<html:hidden name="zsk110knForm" property="zsk110UpdateKbn" />
<html:hidden name="zsk110knForm" property="zsk110StartTime" />
<html:hidden name="zsk110knForm" property="zsk110Status" />
<html:hidden name="zsk110knForm" property="zsk110Msg" />
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
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="zsk.zsk110kn.02" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" class="btn_ok1" value="OK" onClick="return buttonPush('zsk110knOk');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('zsk110knBack');">
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
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="zsk.11" /></span></td>
    <td  valign="middle" align="left" class="td_wt" width="80%">
      <logic:equal name="zsk110knForm" property="zsk110UpdateKbn" value="<%= String.valueOf(jp.groupsession.v2.zsk.GSConstZaiseki.FIXED_UPDATE_ON) %>">
      <span class="text_base2"><gsmsg:write key="cmn.setting.do" /></span>
      </logic:equal>
      <logic:equal name="zsk110knForm" property="zsk110UpdateKbn" value="<%= String.valueOf(jp.groupsession.v2.zsk.GSConstZaiseki.FIXED_UPDATE_OFF) %>">
      <span class="text_base2"><gsmsg:write key="cmn.noset" /></span>
      </logic:equal>
    </td>
    </tr>

   <logic:equal name="zsk110knForm" property="zsk110UpdateKbn" value="<%= String.valueOf(jp.groupsession.v2.zsk.GSConstZaiseki.FIXED_UPDATE_ON) %>">
   <bean:define id="zskSTime" name="zsk110knForm" property="zsk110StartTime" type="java.lang.Integer" />
   <tr>
    <td class="table_bg_A5B4E1" width="20%"><span class="text_bb1"><gsmsg:write key="cmn.starttime" /></span></td>
    <td class="td_wt" width="80%"><span class="text_base2"><gsmsg:write key="cmn.hour.only" arg0="<%= String.valueOf(zskSTime.intValue()) %>"/></span></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="20%"><span class="text_bb1"><gsmsg:write key="zsk.20" /></span></td>
    <td  valign="middle" align="left" class="td_wt" width="80%">
      <table class="tl0">
      <tr>
      <logic:equal name="zsk110knForm" property="zsk110Status" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.UIOSTS_IN) %>">
      <td class="td_type1" nowrap><span class="text_base2"><gsmsg:write key="cmn.zaiseki" /></span></td>
      </logic:equal>
      <logic:equal name="zsk110knForm" property="zsk110Status" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.UIOSTS_LEAVE) %>">
      <td class="td_type_gaisyutu" nowrap><span class="text_base2"><gsmsg:write key="cmn.absence" /></span></td>
      </logic:equal>
      <logic:equal name="zsk110knForm" property="zsk110Status" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.UIOSTS_ETC) %>">
      <td class="td_type_kekkin" nowrap><span class="text_base2"><gsmsg:write key="cmn.other" /></span></td>
      </logic:equal>
      </span>
      </td>
      </tr>
      </table>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="zsk.23" /></span></td>
    <td  valign="middle" align="left" class="td_wt" width="80%">
      <span class="text_base2"><bean:write name="zsk110knForm" property="zsk110Msg" /></span>
    </td>
    </tr>
    </logic:equal>

    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
    <td width="100%" align="right">
      <input type="button" class="btn_ok1" value="OK" onClick="return buttonPush('zsk110knOk');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('zsk110knback');">
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