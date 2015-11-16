<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html;" charset="Shift_JIS">
<title>[Group Session] <gsmsg:write key="cmn.admin.setting.menu" /></title>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<script type="text/javascript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../anpi/js/anp090.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>

</head>
<body class="body_03">
<html:form action="/anpi/anp090">
<!-- BODY -->
<input type="hidden" name="CMD">
<html:hidden property="backScreen" />
<html:hidden property="anp090SelectSid" />

<logic:notEmpty name="anp090Form" property="anp090TempList" scope="request">
  <logic:iterate id="sort" name="anp090Form" property="anp090TempList" scope="request">
    <input type="hidden" name="anp090SortLabel" value="<bean:write name="sort" property="templateValue" />">
  </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>


<table align="center" cellpadding="0" cellspacing="5" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>

    <!-- タイトル -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="anp.plugin"/> <gsmsg:write key="anp.anp090.01"/> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
            <input type="button" value="<gsmsg:write key="cmn.add" />" class="btn_add_n1" onClick="editDetail('');">
            <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('anp090back');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
        </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt="">
  </td>
  </tr>

  <tr>
  <td>

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
        <td>
        <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.up" />" name="btn_upper" onClick="buttonPush('upTemplateData');">
        <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.down" />" name="btn_downer" onClick="buttonPush('downTemplateData');">
        </td>
      </tr>
    </table>
    <div class="text_r1"><gsmsg:write key="anp.anp090.02"/></div>

    <table class="tl0 table_td_border" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr class="table_bg_7D91BD">
    <th align="center" class="table_bg_7D91BD" width="5%" nowrap><span class="text_tlw"></span></th>
    <th align="center" width="95%"><span class="text_tlw"><gsmsg:write key="anp.anp090.03"/></span></th>
    </tr>

    <logic:notEmpty name="anp090Form" property="anp090TempList">

      <% String[] typeClass = new String[] {"td_type1", "td_type_usr"}; %>

    <logic:iterate id="templateMdl" name="anp090Form" property="anp090TempList" indexId="idx">
    <bean:define id="radiovalue" name="templateMdl" property="templateValue" />
      <% String tdType = typeClass[(idx.intValue() % 2)]; %>

      <tr class="<%= tdType %>">
      <!-- ラジオボタン -->
      <td align="center">
      <html:radio property="anp090SortTemplate" value="<%= String.valueOf(radiovalue) %>"/>
      </td>
      <!-- テンプレート名 -->
      <td align="left">
      <a href="#" onclick="editDetail('<bean:write name="templateMdl" property="templateSid" />');">
      <bean:write name="templateMdl" property="templateName" />
      </a>
      </td>

      </tr>

    </logic:iterate>
    </logic:notEmpty>
    </table>
  </td>
  </tr>

  <tr>
  <td>

   <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
        <tr>
        <td width="100%" align="right" cellpadding="5" cellspacing="0">
            <input type="button" value="<gsmsg:write key="cmn.add" />" class="btn_add_n1" onClick="editDetail('');">
            <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('anp090back');">
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