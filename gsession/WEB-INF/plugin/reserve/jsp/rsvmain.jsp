<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<%
  String procMode       = String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_EDIT);
%>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="reserve.rsvmain.4" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../reserve/js/rsvmain.js?<%= GSConst.VERSION_PARAM %>"></script>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../reserve/css/reserve.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">

<html:form action="/reserve/rsvmain">
<input type="hidden" name="CMD" value="">
<html:hidden property="dspDate" />
<html:hidden property="rsvmainSselectedYoyakuSid" />
<div id="tooltips_rsv">
<logic:notEmpty name="rsvmainForm" property="reservList" scope="request">
    <table width="100%" cellspacing="0" cellpadding="0" class="tl0">
    <tr>
    <td align="left" class="header_7D91BD_left" colspan="5">
      <img src="../reserve/images/menu_icon_single.gif" class="img_bottom img_border img_menu_icon_single" alt="<gsmsg:write key="reserve.rsvmain.1" />"><a href="<bean:write name="rsvmainForm" property="rsvTopUrl" />"><gsmsg:write key="reserve.rsvmain.1" /></a>
    </td>
    </tr>
    <tr class="text_base2">
    <th class="td_type30" width="20%" scope="col" nowrap><gsmsg:write key="reserve.rsvmain.2" /></th>
    <th class="td_type30" width="35%" scope="col" nowrap><gsmsg:write key="reserve.rsvmain.3" /></th>
    <th class="td_type30" width="15%" scope="col" nowrap><gsmsg:write key="cmn.start" /></th>
    <th class="td_type30" width="15%" scope="col" nowrap><gsmsg:write key="cmn.end" /></th>
    <th class="td_type30" width="15%" scope="col" nowrap><gsmsg:write key="reserve.73" /></th>
    </tr>

    <% String[] tdClassList = {"td_type1", "td_type25_2"}; %>
    <logic:iterate id="grpMdl" name="rsvmainForm" property="reservList" scope="request" indexId="idx">

<%
      String font = "sc_ttl_sat";
      String titleFont = "text_link";
%>

    <logic:notEmpty name="grpMdl" property="sisetuList">
    <tr class="text_base2">
    <th class="th_sisetu1" align="left" colspan="5" scope="col" nowrap><span class="text_tlw2"><bean:write name="grpMdl" property="rsgName" /></span></th>
    </tr>
    <logic:iterate id="rsvMdl" name="grpMdl" property="sisetuList" indexId="idy">
    <% String tdClass = tdClassList[idy.intValue() % 2]; %>

    <bean:define id="s_rsdSid" name="idy" type="java.lang.Integer" />
    <bean:define id="s_rsySid" name="rsvMdl" property="rsySisetuSid" type="java.lang.Integer" />

    <tr>
    <td class="<%= tdClass %>">
      <bean:write name="rsvMdl" property="rsySisetu" />
    </td>
    <td class="<%= tdClass %>" align="left">
      <logic:notEmpty name="rsvMdl" property="rsyBiko">
        <bean:define id="rsybico" name="rsvMdl" property="rsyBiko" />
        <%
          String tmpText = (String)pageContext.getAttribute("rsybico",PageContext.PAGE_SCOPE);
          String tmpText2 = jp.co.sjts.util.StringUtilHtml.transToHTml(tmpText);
        %>
        <a href="#" onClick="editReserve(<bean:write name="rsvMdl" property="rsySisetuSid" />);" title=""><span class="tooltips"><gsmsg:write key="cmn.content" />:<%= tmpText2 %></span><span class="<%= String.valueOf(titleFont) %>"><bean:write name="rsvMdl" property="rsyContent" /></span></a>
      </logic:notEmpty>
      <logic:empty name="rsvMdl" property="rsyBiko">
        <a href="#" onClick="editReserve(<bean:write name="rsvMdl" property="rsySisetuSid" />);"><span class="<%= String.valueOf(titleFont) %>"><bean:write name="rsvMdl" property="rsyContent" /></span></a>
      </logic:empty>
    </td>

    <td class="<%= tdClass %>" align="center"><bean:write name="rsvMdl" property="rsyFrom" /></td>
    <td class="<%= tdClass %>" align="center"><bean:write name="rsvMdl" property="rsyTo" /></td>
    <td class="<%= tdClass %>">
      <bean:write name="rsvMdl" property="rsySeiMei" />
    </td>

    </logic:iterate>
    </tr>
    </logic:notEmpty>

    </logic:iterate>
    </table>
    </logic:notEmpty>
</div>
</html:form>


</body>
</html:html>