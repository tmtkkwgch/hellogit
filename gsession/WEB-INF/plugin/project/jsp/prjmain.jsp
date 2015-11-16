<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>


<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="project.prjmain.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href="../project/css/project.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<script language="JavaScript" src="../common/js/imageView.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
</head>


<body class="body_03">
<html:form action="/project/prjmain">
<html:hidden property="prjMainSort" />
<html:hidden property="prjMainOrder" />
<bean:define id="orderKey" name="prjmainForm" property="prjMainOrder" />
<bean:define id="sortKbn" name="prjmainForm" property="prjMainSort" />
<% int iOrderKey = ((Integer) orderKey).intValue(); %>
<% int iSortKbn = ((Integer) sortKbn).intValue(); %>

<%-- ソートオーダー --%>
<%  int order_desc = jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC;
    int order_asc = jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC;  %>
<gsmsg:define id="projectTitle" msgkey="cmn.project" />
<gsmsg:define id="title" msgkey="cmn.title" />
<gsmsg:define id="todoNum" msgkey="project.prj050.5" />
<gsmsg:define id="todoTanto" msgkey="cmn.staff" />
<gsmsg:define id="todoWeight" msgkey="project.prj050.4" />
<gsmsg:define id="todoStatus" msgkey="cmn.status" />
<gsmsg:define id="todoStartPlan" msgkey="project.100" />
<gsmsg:define id="todoLimitPlan" msgkey="project.src.66" />
<%
  int[] sortKeyList01 = new int[] {
                       jp.groupsession.v2.prj.GSConstProject.SORT_PROJECT_TITLE,
                       jp.groupsession.v2.prj.GSConstProject.SORT_TODO_TITLE,
                       jp.groupsession.v2.prj.GSConstProject.SORT_TODO_STATUS,
                       jp.groupsession.v2.prj.GSConstProject.SORT_TODO_START_PLAN,
                       jp.groupsession.v2.prj.GSConstProject.SORT_TODO_LIMIT_PLAN
                       };
  String[] title_width01 = new String[] { "25", "40", "9", "13", "13"};

  String[] titleList01 = new String[] {
                        projectTitle,
                        title,
                        todoStatus,
                        todoStartPlan,
                        todoLimitPlan
                       };
%>


<% String[] tdClassList = {"td_type1", "td_type25_2"}; %>
<logic:notEmpty name="prjmainForm" property="projectList" scope="request">

<table width="100%" class="tl0" cellspacing="0" cellpadding="0">
<tr>
<td align="left" class="header_7D91BD_left" colspan="5">
  <table width="100%" class="tl0"><tr>
  <td align="left" width="50%"><img src="../project/images/menu_icon_single.gif" class="img_bottom img_border img_menu_icon_single" alt="<gsmsg:write key="cmn.project" />"><a href="<bean:write name="prjmainForm" property="prjTopUrl" />"><gsmsg:write key="cmn.project" /></a></td>
  <td align="right" width="50%" class="td_main_button"><input type="button" name="btn_addTodo" class="btn_add_n1" value="<gsmsg:write key="project.32" />" onClick="location.href='../project/prj050.do?CMD=addTodo'" ></td>
  </tr>
  </table>
<tr class="text_base2">
<%
    for (int i = 0; i < sortKeyList01.length; i++) {
%>
        <th width="<%= title_width01[i] %>%" class="td_type30"<% if (i == 0) {%> scope="col"<% } %>>
<%
      if (iSortKbn == sortKeyList01[i]) {
        if (iOrderKey == order_desc) {
%>
<a href="javascript:void(0);" onClick="return updatePrj(<%= sortKeyList01[i] %>, <%= order_asc %>);"><span class="text_bb2"><%= titleList01[i] %>▼</span></a></th>
<%
        } else {
%>
<a href="javascript:void(0);" onClick="return updatePrj(<%= sortKeyList01[i] %>, <%= order_desc %>);"><span class="text_bb2"><%= titleList01[i] %>▲</span></a></th>
<%
        }
      } else {
%>
<a href="javascript:void(0);" onClick="return updatePrj(<%= sortKeyList01[i] %>, <%= order_asc %>);"><span class="text_bb2"><%= titleList01[i] %></span></a></th>
<%
      }
    }
%>
</tr>

<logic:iterate id="prjMdl" name="prjmainForm" property="projectList" scope="request" indexId="idx">
<% String back = tdClassList[idx.intValue() % 2]; %>

<tr class="text_base2">
<td class="<%= back %>"><a href="../project/prj030.do?prj030scrId=main&prj030prjSid=<bean:write name="prjMdl" property="projectSid" />">
  <logic:equal name="prjMdl" property="prjBinSid" value="0">
    <img src="../project/images/prj_icon.gif" name="pitctImage" width="30" height="30" alt="<gsmsg:write key="cmn.icon" />" border="1">
  </logic:equal>
  <logic:notEqual name="prjMdl" property="prjBinSid" value="0">
    <img src="../project/prjmain.do?CMD=getImageFile&prjMainPrjSid=<bean:write name="prjMdl" property="projectSid" />&prjMainPrjBinSid=<bean:write name="prjMdl" property="prjBinSid" />" name="pitctImage" width="30" height="30" alt="<gsmsg:write key="cmn.icon" />" border="1" onload="initImageView('pitctImage');">
  </logic:notEqual>
  <span class="text_link"><bean:write name="prjMdl" property="projectName" /></span></a>
</td>
<td class="<%= back %>">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr class="text_base2">
    <td width="99%">

      <logic:equal name="prjMdl" property="keikoku" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.KEIKOKU_ARI) %>">
        <img src="../common/images/keikoku.gif" alt="<gsmsg:write key="cmn.warning" />" class="prj_img_warn">&nbsp;
      </logic:equal>
      <a href="../project/prj060.do?prj060scrId=main&prj060prjSid=<bean:write name="prjMdl" property="projectSid" />&prj060todoSid=<bean:write name="prjMdl" property="todoSid" />"><span class="text_link"><bean:write name="prjMdl" property="todoTitle" filter="false" /></span></a>
      <logic:equal name="prjMdl" property="prjTodoEdit" value="true">
      <a href="../project/prj050.do?prj050scrId=main&prj050prjSid=<bean:write name="prjMdl" property="projectSid" />&prj050todoSid=<bean:write name="prjMdl" property="todoSid" />&prj050cmdMode=1"><img src="../project/images/prj_todo_add.gif" class="prj_img_todo_edit" alt="<gsmsg:write key="project.56" />"></a>
      </logic:equal>
    </td>

    <td width="1%" align="right" valign="middle" nowrap>
      <logic:notEqual name="prjMdl" property="prjTodoCommentCnt" value="0">
        <a href="../project/prj060.do?prj060scrId=main&prj060prjSid=<bean:write name="prjMdl" property="projectSid" />&prj060todoSid=<bean:write name="prjMdl" property="todoSid" />&prjmvComment=1">
        <img src="../project/images/todo_ref_comment_s.gif" alt="<gsmsg:write key="cmn.comment" />" class="prj_img_comment_s">
        <span class="text_base"><bean:write name="prjMdl" property="prjTodoCommentCnt" /></span>
        </a>
      </logic:notEqual>
    </td>
    </tr>
  </table>
</td>
<td class="<%= back %>" align="center"><bean:write name="prjMdl" property="rate" />%<br>(<bean:write name="prjMdl" property="statusName" />)</td>
<td class="<%= back %>" align="center"><bean:write name="prjMdl" property="strStartDate" /></td>
<td class="<%= back %>" align="center"><bean:write name="prjMdl" property="strEndDate" /></td>
</tr>

</logic:iterate>

</table>

</logic:notEmpty>

</html:form>

</body>
</html:html>