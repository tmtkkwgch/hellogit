<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="rss.rss090.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../rss/js/rss090.js?<%= GSConst.VERSION_PARAM %>"></script>

<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../rss/css/rss.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<theme:css filename="theme.css"/>
</head>

<body class="body_03">

<html:form action="/rss/rss090">
<input type="hidden" name="CMD" value="">
<html:hidden name="rss090Form" property="rssSid" />

<!-- BODY -->
<table cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>

<td width="100%" align="center">

  <table width="100%" cellpadding="0" border="0" cellspacing="0">
  <tr>
  <td align="left">

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" class="tl5">

    <logic:notEmpty name="rss090Form" property="userDataList">
    <bean:size id="count1" name="rss090Form" property="pageLabelList" scope="request" />
    <logic:greaterThan name="count1" value="1">
    <tr>
    <td align="right" width="0%">
      <table width="0%" cellpadding="0" cellspacing="0" border="0">
      <td align="right">
        <img alt="<gsmsg:write key="cmn.previous.page" />" height="20" src="../common/images/arrow2_l.gif" class="img_bottom" width="20" border="0" onClick="buttonPush('arrorw_left');">&nbsp;</td>
      <td align="right">
        <html:select property="rss090page1" onchange="changePage(1);" styleClass="text_i">
          <span class="text_base"><html:optionsCollection name="rss090Form" property="pageLabelList" value="value" label="label" /></span>
        </html:select>
      </td>
      <td align="right">
        &nbsp;<img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" border="0" onClick="buttonPush('arrorw_right');">
      </td>
      </tr>
      </table>
    </td>
    </tr>
    </logic:greaterThan>
    </logic:notEmpty>

    </table>

    <table class="tl0 table_td_border2" width="100%" cellpadding="0" cellspacing="0">
    <tr class="table_bg_7D91BD">

    <!-- 氏名 -->
    <th nowrap>
        <span class="text_tlw"><gsmsg:write key="rss.rss090.2" /></span></a></th>
    </th>

    <logic:notEmpty name="rss090Form" property="userDataList">
    <logic:iterate id="user" name="rss090Form" property="userDataList" scope="request" indexId="idx">
      <bean:define id="mod" value="0" />
      <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
        <bean:define id="tblColor" value="td_type1" />
      </logic:equal>
      <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
        <bean:define id="tblColor" value="td_type25_2" />
      </logic:notEqual>

    <tr class="<bean:write name="tblColor" />">
    <!-- 氏名 -->
    <td nowrap><span class="normal_link"><bean:write name="user" property="registSei" />&nbsp;<bean:write name="user" property="registMei" /></span></td>
    </tr>
    </logic:iterate>
    </logic:notEmpty>

    </table>

    <logic:notEmpty name="rss090Form" property="userDataList">
    <bean:size id="count2" name="rss090Form" property="pageLabelList" scope="request" />
    <logic:greaterThan name="count2" value="1">
    <table width="100%" class="tl5">
    <tr>
    <td width="100%">&nbsp;</td>
    <td align="right">
      <table width="0%" cellpadding="0" cellspacing="0" border="0">
      <td align="right">
        <img alt="<gsmsg:write key="cmn.previous.page" />" height="20" src="../common/images/arrow2_l.gif" class="img_bottom" width="20" border="0" onClick="buttonPush('arrorw_left');">&nbsp;
      </td>
      <td align="right">
        <html:select property="rss090page2" onchange="changePage(2);" styleClass="text_i">
          <span class="text_base"><html:optionsCollection name="rss090Form" property="pageLabelList" value="value" label="label" /></span>
        </html:select>
      </td>
      <td align="right">
        &nbsp;<img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" border="0" onClick="buttonPush('arrorw_right');">
      </td>
      </tr>
      </table>

    </td>
    </tr>
    </table>
    </logic:greaterThan>
    </logic:notEmpty>


  </td>
  </tr>

  <tr>
  <td align="center">
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
  </td>
  </tr>

  <tr>
  <td align="center">
    <input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_close_n1" onClick="window.close();">
  </td>
  </tr>

  </table>

</td>
</tr>
</table>

</html:form>

</body>
</html:html>