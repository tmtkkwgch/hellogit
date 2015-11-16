<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<html:hidden property="selectingTodoDay" />
<html:hidden property="selectingTodoPrj" />
<html:hidden property="selectingTodoSts" />

<gsmsg:define id="projectName" msgkey="project.40" />
<gsmsg:define id="projectYosan" msgkey="project.10" />
<gsmsg:define id="projectStatus" msgkey="cmn.status" />
<gsmsg:define id="todoWeight" msgkey="project.prj050.4" />
<gsmsg:define id="todoStatus" msgkey="cmn.status" />
<gsmsg:define id="projectStart" msgkey="main.src.man250.29" />
<gsmsg:define id="projectEnd" msgkey="main.src.man250.30" />

<%
  int[] sortKeyList01 = new int[] {
                       jp.groupsession.v2.prj.GSConstProject.SORT_PRJECT_ID,
                       jp.groupsession.v2.prj.GSConstProject.SORT_PROJECT_NAME,
                       jp.groupsession.v2.prj.GSConstProject.SORT_PRJECT_YOSAN,
                       jp.groupsession.v2.prj.GSConstProject.SORT_PRJECT_STATUS,
                       jp.groupsession.v2.prj.GSConstProject.SORT_PRJECT_START,
                       jp.groupsession.v2.prj.GSConstProject.SORT_PRJECT_END
                       };
  String[] title_width01 = new String[] { "12", "44", "11", "9", "11", "11"};
  String[] titleList01 = new String[] {
                        jp.groupsession.v2.prj.GSConstProject.SORT_STR_PRJECT_ID,
                        projectName,
                        projectYosan,
                        projectStatus,
                        projectStart,
                        projectEnd
                       };

  String jkbnToroku = String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_TOROKU);
%>

<%-- includeされるプロジェクトタブモード時の明細 --%>
    <tr>
    <td class="prj_header_left" align="left" valign="middle" nowrap>
      &nbsp;
      <span class="text_todo_head"><gsmsg:write key="cmn.project" /></span>
      <logic:notEmpty name="prj010Form" property="targetProjectLabel">
        <html:select property="selectingProject" onchange="return buttonPush('changeCombo');">
          <html:optionsCollection name="prj010Form" property="targetProjectLabel" value="value" label="label" />
        </html:select>
      </logic:notEmpty>&nbsp;
    </td>
    <td class="prj_header_right" align="right" valign="middle" nowrap>
      <input type="button" name="btn_prjadd" class="btn_search_n1" value="<gsmsg:write key="cmn.advanced.search" />" onclick="buttonPush('<%= prjSearch %>');">
      <logic:equal name="prj010Form" property="prjAdd" value="true"><input type="button" name="btn_prjadd" class="btn_add_n1" value="<gsmsg:write key="cmn.new.registration" />" onclick="buttonPush('<%= prjAdd %>');"></logic:equal>&nbsp;
    </td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td width="0%" class="tab_bottom_left"><img src="../common/images/damy.gif" width="10" height="10" border="0" alt=""></td>
      <td class="tab_bottom_mid" width="100%">&nbsp;</td>
      <td width="0%" class="tab_bottom_right"><img src="../common/images/damy.gif" width="10" height="34" border="0" alt=""></td>
      </tr>
    </table>

    <table class="tl0 prj_tbl_base_2" width="100%" cellpadding="0" cellspacing="0">

  <logic:notEmpty name="prj010Form" property="projectList" scope="request">
    <tr>
<%
    for (int i = 0; i < sortKeyList01.length; i++) {

      if (i == 3) {
%>
    <th class="prj_title td_type3" width="13%"><span class="text_bb3"><gsmsg:write key="cmn.admin" /></span></th>
<%
      }


      if (iSortKbn == sortKeyList01[i]) {
        if (iOrderKey == order_desc) {
%>
        <th width="<%= title_width01[i] %>%" class="prj_title td_type3"><a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= sortKeyList01[i] %>, <%= order_asc %>);"><span class="text_bb3"><%= titleList01[i] %>▼</span></a></th>
<%
        } else {
%>
        <th width="<%= title_width01[i] %>%" class="prj_title td_type3"><a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= sortKeyList01[i] %>, <%= order_desc %>);"><span class="text_bb3"><%= titleList01[i] %>▲</span></a></th>
<%
        }
      } else {
%>
        <th width="<%= title_width01[i] %>%" class="prj_title td_type3"><a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= sortKeyList01[i] %>, <%= order_asc %>);"><span class="text_bb3"><%= titleList01[i] %></span></a></th>
<%
      }
    }
%>
    </tr>

  <logic:iterate id="prjMdl" name="prj010Form" property="projectList" scope="request" indexId="idx">
  <bean:define id="backclass" value="td_line_color" />
  <bean:define id="backpat" value="<%= String.valueOf((idx.intValue() % 2) + 1) %>" />
  <bean:define id="back" value="<%= String.valueOf(backclass) + String.valueOf(backpat) %>" />

      <tr class="<%= String.valueOf(back) %>">
        <td class="prj_td"><span class="text_blue"><bean:write name="prjMdl" property="projectId" /></span></td>
        <td class="prj_td" align="left">
        <logic:equal name="prjMdl" property="prjBinSid" value="0">
          <img src="../project/images/prj_icon.gif" name="pitctImage" width="30" height="30" alt="<gsmsg:write key="cmn.icon" />" border="1" class="prj_img_ico">
        </logic:equal>
        <logic:notEqual name="prjMdl" property="prjBinSid" value="0">
          <img src="../project/prj010.do?CMD=getImageFile&prj010PrjSid=<bean:write name="prjMdl" property="projectSid" />&prj010PrjBinSid=<bean:write name="prjMdl" property="prjBinSid" />" name="pitctImage" width="30" height="30" alt="<gsmsg:write key="cmn.icon" />" border="1" onload="initImageView('pitctImage');" class="prj_img_ico">
        </logic:notEqual>
          <a href="javascript:void(0)" onClick="return viewPoject('<%= prjMain %>', '<bean:write name="prjMdl" property="projectSid" />');"><span class="text_blue"><bean:write name="prjMdl" property="projectName" /></span></a>
        </td>
        <td class="prj_td" align="right"><span class="text_blue"><bean:write name="prjMdl" property="strYosan" /></span></td>
        <td class="prj_td" align="center">
          <span class="text_blue">
          <logic:notEmpty name="prjMdl" property="memberList">
          <logic:iterate id="userMdl" name="prjMdl" property="memberList">
            <logic:equal name="userMdl" property="status" value="<%= jkbnToroku %>">
            <bean:write name="userMdl" property="sei" />&nbsp;<bean:write name="userMdl" property="mei" />
            </logic:equal>
            <logic:notEqual name="userMdl" property="status" value="<%= jkbnToroku %>">
            <del><bean:write name="userMdl" property="sei" />&nbsp;<bean:write name="userMdl" property="mei" /></del>
            </logic:notEqual>
          <br>
          </logic:iterate>
          </logic:notEmpty>
          </span>
        </td>
        <td class="prj_td" align="center">
        <logic:equal name="prjMdl" property="prjMyKbn" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.KBN_MY_PRJ_DEF) %>">
          <span class="text_blue"><bean:write name="prjMdl" property="rate" />%<br>(<bean:write name="prjMdl" property="statusName" />)
        </span>
        </logic:equal>
        </td>
        <td class="prj_td" nowrap align="center"><span class="text_blue"><bean:write name="prjMdl" property="strStartDate" /></span></td>
        <td class="prj_td" nowrap align="center"><span class="text_blue"><bean:write name="prjMdl" property="strEndDate" /></span></td>
      </tr>

  </logic:iterate>
  </logic:notEmpty>
