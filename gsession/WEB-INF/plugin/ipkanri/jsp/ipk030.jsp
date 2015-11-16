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
<script language="JavaScript" src="../ipkanri/js/ipkanri.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../ipkanri/css/ip.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<title>[GroupSession] <gsmsg:write key="ipk.14" /></title>
</head>

<body class="body_03">
<html:form action="/ipkanri/ipk030">
<input type="hidden" name="cmd" value="submit" />
<logic:notEmpty name="ipk030Form" property="adminSidList">
<logic:iterate id="param" name="ipk030Form" property="adminSidList">
<input type="hidden" name="adminSidList" value="<bean:write name="param" />">
</logic:iterate>
</logic:notEmpty>
<html:hidden property="backScreen" />
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">

<tr>
<td width="100%" align="center">
  <table cellpadding="0" cellspacing="0" width="70%" align="center">

  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" width="100%">
    <tr>
    <td width="0%">
    <img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.ipkanri" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="ipk.14" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.ipkanri" />" /></td>
    </tr>
    </table>
  </td>
  </tr>

  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../ipkanri/images/header_glay_1.gif" border="0" alt="<gsmsg:write key="cmn.ipkanri" />"></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" name="edit" value="<gsmsg:write key="cmn.entry" />" onClick="buttonPush2('networkAdminEdit');" class="btn_base1">
    <input type="button" name="cancel" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush2('ipk030Return');" class="btn_back_n1"></td>
    <td width="0%"><img src="../ipkanri/images/header_glay_3.gif" border="0" alt="<gsmsg:write key="cmn.ipkanri" />"></td>
    </tr>
    </table>
  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="10" height="10" border="0" alt="">
  </td>
  </tr>

  <tr>
  <td>
  <span class="text_error"><html:errors/></span>
  </td>
  </tr>

  <tr>
  <td>
    <table class="table_padding" cellpadding="0">

    <tr>
    <td align="left" width="150" rowspan="1" class="table_bg_A5B4E1" nowrap><span class="font_black2"><gsmsg:write key="ipk.ipk030.2" /></span></td>
    <td class="td_type1" cellspacing="0" cellpadding="0">
      <table class="table_padding" cellpadding="0" algin="center">

      <tr>
      <td align="left">
        <table width="100%" border="0">

        <tr>
        <td width="35%" class="table_bg_A5B4E1" align="center"><span class="font_black2"><gsmsg:write key="ipk.ipk030.1" /></span></td>
        <td width="20%" align="center"> &nbsp; </td>
        <td width="35%" align="left">
        <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup(this.form.groupSelect, 'groupSelect', '<bean:write name="ipk030Form" property="groupSelect" />', '0', 'changeGrp', 'adminSidList', '-1', '0', '0', '0', '1')" type="button">
        <html:select name="ipk030Form" property="groupSelect" styleClass="select01" onchange="changeGrp();">
        <logic:notEmpty name="ipk030Form" property="groupList">
        <html:optionsCollection name="ipk030Form" property="groupList" value="value" label="label" />
        </logic:notEmpty>
        </html:select>
        </td>
        <td width="10%" align="left" valign="bottom">
        <input type="button" onclick="openGroupWindowForIpkanri(this.form.groupSelect, 'groupSelect', '0', 'changeGrp')" class="group_btn2" value="&nbsp;&nbsp;" id="ipk030GroupBtn">
        </td>
        </tr>

        <tr>
        <td>
        <html:select name="ipk030Form" property="selectLeftUser" size="5" multiple="true" styleClass="select01">
        <logic:notEmpty name="ipk030Form" property="leftUserList">
        <html:optionsCollection name="ipk030Form" property="leftUserList" value="value" label="label" />
        </logic:notEmpty>
        <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
        </html:select>
        </td>
        <td align="center">
        <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush2('userAdd');"><br><br>
        <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush2('userDelete');">
        </td>
        <td align="center">
        <html:select name="ipk030Form" property="selectRightUser" size="5" multiple="true" styleClass="select01">
        <logic:notEmpty name="ipk030Form" property="rightUserList">
        <html:optionsCollection name="ipk030Form" property="rightUserList" value="value" label="label" filter="false" />
        </logic:notEmpty>
        <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
        </html:select>
        </td>
        </tr>

        </table>
      </td>
      </tr>

      </table>
    </td>
    </tr>

    </table>
  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="10" height="10" border="0" alt="">
  </td>
  </tr>

  <tr>
  <td>
    <table td_wt cellpadding="0" align="center" class="table_kotei2">
    <tr>
    <td align="right">
    <input type="button" name="edit" value="<gsmsg:write key="cmn.entry" />" onClick="buttonPush2('networkAdminEdit');" class="btn_base1">
    <input type="button" name="cancel" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush2('ipk030Return');" class="btn_back_n1">
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