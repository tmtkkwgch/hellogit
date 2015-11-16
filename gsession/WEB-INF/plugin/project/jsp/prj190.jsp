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
<title>[Group Session] <gsmsg:write key="project.107" /> <gsmsg:write key="project.11" /></title>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
</head>

<body class="body_03">

<html:form action="/project/prj190">
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
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="project.11" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onClick="return buttonPush('edit');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="return buttonPush('backKmenu');">
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
      <html:radio name="prj190Form" styleId="prj190Dsp_01" property="prj190DefDsp" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.DSP_TODO) %>" /><label for="prj190Dsp_01"><span class="text_bb1">TODO</span></span></label>&nbsp;
      <html:radio name="prj190Form" styleId="prj190Dsp_02" property="prj190DefDsp" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.DSP_PROJECT) %>" /><label for="prj190Dsp_02"><span class="text_bb1"><gsmsg:write key="cmn.project" /></span></label>&nbsp;
    </td>

    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1">TODO[<gsmsg:write key="project.prj010.2" />]</span></td>
    <td align="left" class="td_wt" width="100%">

      <table>
      <tr>
      <td align="right" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.date2" />：</span>
      <td align="left" width="100%">
        <logic:notEmpty name="prj190Form" property="prj190TodoDayList">
          <html:select name="prj190Form" property="prj190TodoDay" styleClass="select01" onchange="return buttonPush('changeList');">
            <html:optionsCollection name="prj190Form" property="prj190TodoDayList" value="value" label="label" />
          </html:select>
        </logic:notEmpty>
      </td>
      </tr>
      <tr>
      <td align="right" nowrap><span class="text_bb1"><gsmsg:write key="cmn.project" />：</span>
      <td align="left">
        <logic:notEmpty name="prj190Form" property="prj190TodoProjectList">
          <html:select name="prj190Form" property="prj190TodoPrj" styleClass="select01" onchange="return buttonPush('changeList');">
            <html:optionsCollection name="prj190Form" property="prj190TodoProjectList" value="value" label="label" />
          </html:select>
        </logic:notEmpty>
      </td>
      </tr>
      <tr>
      <td align="right" nowrap><span class="text_bb1"><gsmsg:write key="cmn.status" />：</span>
      <td align="left">
        <logic:notEmpty name="prj190Form" property="prj190TodoStsList">
          <html:select name="prj190Form" property="prj190TodoSts" styleClass="select01">
            <html:optionsCollection name="prj190Form" property="prj190TodoStsList" value="value" label="label" />
          </html:select>
        </logic:notEmpty>
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
        <logic:notEmpty name="prj190Form" property="prj190ProjectList">
          <html:select name="prj190Form" property="prj190Project" styleClass="select01">
            <html:optionsCollection name="prj190Form" property="prj190ProjectList" value="value" label="label" />
          </html:select>
        </logic:notEmpty>
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
      <input type="button" value="OK" class="btn_ok1" onClick="return buttonPush('edit');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="return buttonPush('backKmenu');">
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