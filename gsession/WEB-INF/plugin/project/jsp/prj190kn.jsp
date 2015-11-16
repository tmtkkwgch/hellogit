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
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<title>[Group Session] <gsmsg:write key="project.107" /> <gsmsg:write key="project.prj190kn.1" /></title>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
</head>

<body class="body_03">

<html:form action="/project/prj190kn">
<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="prj010cmdMode" />
<html:hidden property="prj010sort" />
<html:hidden property="prj010order" />
<html:hidden property="prj010page1" />
<html:hidden property="prj010page2" />
<html:hidden property="prj010Init" />
<html:hidden property="selectingProject" />
<html:hidden property="selectingTodoDay" />
<html:hidden property="selectingTodoPrj" />
<html:hidden property="selectingTodoSts" />
<html:hidden property="prj190TodoDay" />
<html:hidden property="prj190TodoPrj" />
<html:hidden property="prj190TodoSts" />
<html:hidden property="prj190Project" />
<html:hidden property="prj190DefDsp" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="60%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="project.prj190kn.1" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_setting_n1" onClick="return buttonPush('update');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="return buttonPush('backPrj190');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <logic:messagesPresent message="false">
    <table width="100%">
    <tr><td width="100%" align="left"><html:errors/></td></tr>
    </table>
    </logic:messagesPresent>

    <table width="100%" class="tl0" border="0" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.initial.display" /></span></td>
    <td class="td_type1">
      <span class="text_bb1"><bean:write name="prj190knForm" property="prj190knDefDsp" /></span>
    </td>

    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1">TODO[<gsmsg:write key="project.prj010.2" />]</span></td>
    <td align="left" class="td_wt" width="100%">

      <table>
      <tr>
      <td align="right" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.date2" />：</span>
      <td align="left" width="100%">
        <span class="text_base"><bean:write name="prj190knForm" property="prj190knTodoDateStr" /></span>
      </td>
      </tr>
      <tr>
      <td align="right" nowrap><span class="text_bb1"><gsmsg:write key="cmn.project" />：</span>
      <td align="left">
        <span class="text_base"><bean:write name="prj190knForm" property="prj190knTodoProjectStr" /></span>
      </td>
      </tr>
      <tr>
      <td align="right" nowrap><span class="text_bb1"><gsmsg:write key="cmn.status" />：</span>
      <td align="left">
        <span class="text_base"><bean:write name="prj190knForm" property="prj190knTodoStatusStr" /></span>
      </td>
      </tr>
      </table>

    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.project" />[<gsmsg:write key="project.prj010.2" />]</span></td>
    <td align="left" class="td_wt" width="100%">

      <table>
      <tr>
      <td align="right" nowrap><span class="text_bb1"><gsmsg:write key="cmn.project" />：</span>
      <td align="left">
        <span class="text_base"><bean:write name="prj190knForm" property="prj190knProjectStr" /></span>
      </td>
      </tr>
      </table>

    </td>
    </tr>
    </table>

    <IMG SRC="./images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellpadding="5" border="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_setting_n1" onClick="return buttonPush('update');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="return buttonPush('backPrj190');">
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