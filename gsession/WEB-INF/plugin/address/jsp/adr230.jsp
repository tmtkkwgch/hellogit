<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.select.company" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../address/js/adr230.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">
<html:form action="/address/adr230">
<input type="hidden" name="CMD" value="">
<html:hidden property="adr230SortKey" />
<html:hidden property="adr230OrderKey" />

<table width="100%">
<tr>
<td width="100%" align="center">

  <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
  <tr>
  <td align="right">
    <input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_close_n1" onClick="window.close();">
  </td>
  </tr>

  <tr>
  <td align="right" width="100%">
    <img alt="<gsmsg:write key="cmn.previous.page" />" height="20" src="../common/images/arrow2_l.gif" class="img_bottom" width="20" border="0" onClick="buttonPush('pageleft');" style="cursor:pointer;">
    <logic:notEmpty name="adr230Form" property="adr230PageList">
      <html:select property="adr230PageTop" onchange="return changePage('0');" styleClass="text_i">
        <html:optionsCollection name="adr230Form" property="adr230PageList" value="value" label="label" />
      </html:select>
    </logic:notEmpty>
    <logic:empty name="adr230Form" property="adr230PageList">
      <html:select property="adr230PageTop">
        <option value="1" class="text_i">1 / 1</option>
      </html:select>
    </logic:empty>
    <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" border="0" onClick="buttonPush('pageright');" style="cursor:pointer;">
  </td>
  </tr>
  </table>

  <table summary="" class="tl0 table_td_border2" width="100%" cellpadding="0" cellspacing="0">
  <tr class="table_bg_7D91BD">

  <!-- <gsmsg:write key="address.7" /> -->
  <logic:equal name="adr230Form" property="adr230SortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.COMPANY_SORT_CODE) %>">

    <logic:equal name="adr230Form" property="adr230OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>">
      <th width="20%" nowrap><a href="#" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.COMPANY_SORT_CODE) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>')"><span class="text_tlw"><gsmsg:write key="address.7" />▲</span></a></th>
    </logic:equal>

    <logic:equal name="adr230Form" property="adr230OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>">
      <th width="20%" nowrap><a href="#" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.COMPANY_SORT_CODE) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="address.7" />▼</span></a></th>
    </logic:equal>

  </logic:equal>

  <logic:notEqual name="adr230Form" property="adr230SortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.COMPANY_SORT_CODE) %>">
    <th width="20%" nowrap><a href="#" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.COMPANY_SORT_CODE) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="address.7" /></span></a></th>
  </logic:notEqual>

  <!-- <gsmsg:write key="cmn.company.name" /> -->
  <logic:equal name="adr230Form" property="adr230SortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.COMPANY_SORT_NAME) %>">

    <logic:equal name="adr230Form" property="adr230OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>">
      <th width="20%" nowrap><a href="#" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.COMPANY_SORT_NAME) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.company.name" />▲</span></a></th>
    </logic:equal>

    <logic:equal name="adr230Form" property="adr230OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>">
      <th width="20%" nowrap><a href="#" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.COMPANY_SORT_NAME) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.company.name" />▼</span></a></th>
    </logic:equal>

  </logic:equal>

  <logic:notEqual name="adr230Form" property="adr230SortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.COMPANY_SORT_NAME) %>">
    <th width="20%" nowrap><a href="#" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.COMPANY_SORT_NAME) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.company.name" /></span></a></th>
  </logic:notEqual>

  </tr>

  <bean:define id="mod" value="0" />
  <logic:notEmpty name="adr230Form" property="adr230CompanyList">
    <logic:iterate id="comList" name="adr230Form" property="adr230CompanyList" indexId="idx">

    <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
      <bean:define id="tblColor" value="td_type1" />
    </logic:equal>
    <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
      <bean:define id="tblColor" value="td_type_usr" />
    </logic:notEqual>

    <tr>
    <td align="left" class="<bean:write name="tblColor" />" nowrap>
      <span class="normal_link">
        <a href="#" onclick="return selectCompany('<bean:write name="comList" property="acoCode" />')"><bean:write name="comList" property="acoCode" /></a>
      </span>
    </td>
    <td align="left" class="<bean:write name="tblColor" />" nowrap>
      <span class="text_base"><bean:write name="comList" property="acoName" /></span>
    </td>
    </tr>

    </logic:iterate>
  </logic:notEmpty>

  </table>

  <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
  <tr>
  <td align="right" width="100%">
    <img alt="<gsmsg:write key="cmn.previous.page" />" height="20" src="../common/images/arrow2_l.gif" class="img_bottom" width="20" border="0" onClick="buttonPush('pageleft');" style="cursor:pointer;">
    <logic:notEmpty name="adr230Form" property="adr230PageList">
      <html:select property="adr230PageBottom" onchange="return changePage('1');" styleClass="text_i">
        <html:optionsCollection name="adr230Form" property="adr230PageList" value="value" label="label" />
      </html:select>
    </logic:notEmpty>
    <logic:empty name="adr230Form" property="adr230PageList">
      <html:select property="adr230PageBottom">
        <option value="1" class="text_i">1 / 1</option>
      </html:select>
    </logic:empty>
    <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" border="0" onClick="buttonPush('pageright');" style="cursor:pointer;">
  </td>
  </tr>
  </table>

</td>
</tr>
</table>

</html:form>
</body>
</html:html>