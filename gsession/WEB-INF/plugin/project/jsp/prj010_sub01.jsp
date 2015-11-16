<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<% String cmdDelTo = jp.groupsession.v2.prj.prj010.Prj010Action.CMD_DEL_TODO; %>

<html:hidden property="selectingProject" />
<gsmsg:define id="projectTitle" msgkey="cmn.project" />
<gsmsg:define id="title" msgkey="cmn.title" />
<gsmsg:define id="todoNum" msgkey="project.prj050.5" />
<gsmsg:define id="todoWeight" msgkey="project.prj050.4" />
<gsmsg:define id="todoStatus" msgkey="cmn.status" />
<gsmsg:define id="todoStartPlan" msgkey="project.100" />
<gsmsg:define id="todoLimitPlan" msgkey="project.src.66" />

<%
  int[] sortKeyList01 = new int[] {
                       jp.groupsession.v2.prj.GSConstProject.SORT_PROJECT_TITLE,
                       jp.groupsession.v2.prj.GSConstProject.SORT_TODO_TITLE,
                       jp.groupsession.v2.prj.GSConstProject.SORT_TODO_NO,
                       jp.groupsession.v2.prj.GSConstProject.SORT_TODO_WEIGHT,
                       jp.groupsession.v2.prj.GSConstProject.SORT_TODO_STATUS,
                       jp.groupsession.v2.prj.GSConstProject.SORT_TODO_START_PLAN,
                       jp.groupsession.v2.prj.GSConstProject.SORT_TODO_LIMIT_PLAN
                       };
  String[] title_width01 = new String[] { "22", "32", "0", "10", "8", "12", "12"};
  String[] titleList01 = new String[] {
                        projectTitle,
                        title,
                        todoNum,
                        todoWeight,
                        todoStatus,
                        todoStartPlan,
                        todoLimitPlan
                       };
%>

    <tr>
    <td class="prj_header_left" align="left" valign="middle" nowrap>
      &nbsp;
      <span class="text_todo_head"><gsmsg:write key="cmn.date2" /></span>
      <logic:notEmpty name="prj010Form" property="targetTodoDayLabel">
        <html:select property="selectingTodoDay" onchange="return buttonPush('changeCombo');">
          <html:optionsCollection name="prj010Form" property="targetTodoDayLabel" value="value" label="label" />
        </html:select>
      </logic:notEmpty>&nbsp;

      <span class="text_todo_head"><gsmsg:write key="cmn.project" /></span>
      <logic:notEmpty name="prj010Form" property="targetTodoProjectLabel">
        <html:select property="selectingTodoPrj" onchange="return changeTodoPrj();">
          <html:optionsCollection name="prj010Form" property="targetTodoProjectLabel" value="value" label="label" />
        </html:select>
      </logic:notEmpty>&nbsp;

      <span class="text_todo_head"><gsmsg:write key="cmn.status" /></span>
      <logic:notEmpty name="prj010Form" property="targetTodoStsLabel">
        <html:select property="selectingTodoSts" onchange="return buttonPush('changeCombo')">
          <html:optionsCollection name="prj010Form" property="targetTodoStsLabel" value="value" label="label" />
        </html:select>
      </logic:notEmpty>

    </td>
    <td class="prj_header_right" align="right" valign="middle" nowrap>
      <input type="button" name="btn_prjadd" class="btn_search_n1" value="<gsmsg:write key="cmn.advanced.search" />" onclick="buttonPush('<%= todoSearch %>');">&nbsp;
    </td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td width="0%" class="tab_bottom_left"><img src="../common/images/damy.gif" width="10" height="15" border="0" alt=""></td>
      <td class="tab_bottom_mid" width="100%"></td>
      <td width="0%" class="tab_bottom_right"><img src="../common/images/damy.gif" width="10" height="15" border="0" alt=""></td>
      </tr>
    </table>

    <table width="100%" cellpadding="0" cellspacing="0" class="tl0 prj_tbl_base_2">
    <tr>
    <td colspan="7">
      <table width="100%" cellpadding="2">
      <tr>
      <td width="98%" align="right" valign="bottom">
        <logic:notEmpty name="prj010Form" property="projectList" scope="request">
        <a href="javascript:void(0)" onClick="return doOpenDialog();"><span class="text_todo_head">▼<gsmsg:write key="project.date.change" /></span></a>
          &nbsp;&nbsp;<span class="text_todo_head"><gsmsg:write key="project.20" />:</span>&nbsp;
          <html:select property="prj010selectEditStatus" styleClass="text_i">
            <html:optionsCollection name="prj010Form" property="editStatusLabel" value="value" label="label" />
          </html:select>
          <input type="button" value="<gsmsg:write key="cmn.change" />" class="btn_base0" onclick="buttonPush('<%= jp.groupsession.v2.prj.prj010.Prj010Action.CMD_EDIT_STATUS %>');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </logic:notEmpty>
        <input type="button" name="btn_prjadd" class="btn_add_n1" value="<gsmsg:write key="cmn.new.registration" />" onclick="buttonPushAdd('<%= todoAdd %>', <%= mode_add %>);">&nbsp;
        <logic:notEmpty name="prj010Form" property="projectList" scope="request">
          <input type="button" name="btn_tododel"class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onClick="buttonPush('<%= cmdDelTo %>');">
        </logic:notEmpty>
      </td>

      <!-- ページング -->
      <bean:size id="count1" name="prj010Form" property="pageLabel" scope="request" />
      <logic:greaterThan name="count1" value="1">
        <td width="2%" align="right" nowrap>
        <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_pageBtn" onClick="buttonPush('prev');">
        <html:select property="prj010page1" onchange="changePage(1);" styleClass="text_i">
          <html:optionsCollection name="prj010Form" property="pageLabel" value="value" label="label" />
        </html:select>
        <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_pageBtn" onClick="buttonPush('next');">
        </td>
      </logic:greaterThan>
      </tr>
      </table>
    </td>
    </tr>

  <logic:notEmpty name="prj010Form" property="projectList" scope="request">
    <tr>
    <th class="prj_title td_type3" width="4%"><input type="checkbox" name="prj010AllCheck" value="1" onClick="chgCheckAll('prj010AllCheck', 'prj010selectTodo');chgCheckAllChange('prj010AllCheck', 'prj010selectTodo');"></th>
<%
    for (int i = 0; i < sortKeyList01.length; i++) {
      if (i != 2) {
%>
    <th width="<%= title_width01[i] %>%" class="prj_title td_type3">
<%    } else { %>
&nbsp;/&nbsp;
<%
      }
      if (iSortKbn == sortKeyList01[i]) {
        if (iOrderKey == order_desc) {
%>
        <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= sortKeyList01[i] %>, <%= order_asc %>);"><span class="text_bb3"><%= titleList01[i] %>▼</span></a>
<%
        } else {
%>
        <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= sortKeyList01[i] %>, <%= order_desc %>);"><span class="text_bb3"><%= titleList01[i] %>▲</span></a>
<%
        }
      } else {
%>
        <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= sortKeyList01[i] %>, <%= order_asc %>);"><span class="text_bb3"><%= titleList01[i] %></span></a>
<%
      }

      if (i != 1) {
%>
        </th>
<%
      }
    }
%>
    </tr>

  <logic:iterate id="prjMdl" name="prj010Form" property="projectList" scope="request" indexId="idx">
  <bean:define id="backclass" value="td_line_color" />
  <bean:define id="backclass_no_edit" value="td_line_no_edit_color" />
  <bean:define id="backpat" value="<%= String.valueOf((idx.intValue() % 2) + 1) %>" />
  <bean:define id="back" value="<%= String.valueOf(backclass) + String.valueOf(backpat) %>" />
  <bean:define id="back_no_edit" value="<%= String.valueOf(backclass_no_edit) + String.valueOf(backpat) %>" />

    <logic:equal name="prjMdl" property="prjTodoEdit" value="true">
    <tr class="<%= String.valueOf(back) %>" id="<bean:write name="prjMdl" property="projectSid" />:<bean:write name="prjMdl" property="todoSid" />">
    </logic:equal>
    <logic:notEqual name="prjMdl" property="prjTodoEdit" value="true">
    <tr class="<%= String.valueOf(back_no_edit) %>">
    </logic:notEqual>
    <td class="prj_td" align="center">
      <logic:equal name="prjMdl" property="prjTodoEdit" value="true">
        <% if (Integer.valueOf(backpat) == 1) { %>
          <html:multibox name="prj010Form" property="prj010selectTodo" onclick="backGroundSetting(this, '1');">
            <bean:write name="prjMdl" property="projectSid" />:<bean:write name="prjMdl" property="todoSid" />
          </html:multibox>
        <% } else { %>
          <html:multibox name="prj010Form" property="prj010selectTodo" onclick="backGroundSetting(this, '2');">
            <bean:write name="prjMdl" property="projectSid" />:<bean:write name="prjMdl" property="todoSid" />
          </html:multibox>
        <% } %>
      </logic:equal>
    </td>
    <td class="prj_td" align="left">
      <logic:equal name="prjMdl" property="prjBinSid" value="0">
        <img src="../project/images/prj_icon.gif" name="pitctImage" width="30" height="30" alt="<gsmsg:write key="cmn.icon" />" border="1" class="prj_img_ico">
      </logic:equal>
      <logic:notEqual name="prjMdl" property="prjBinSid" value="0">
        <img src="../project/prj010.do?CMD=getImageFile&prj010PrjSid=<bean:write name="prjMdl" property="projectSid" />&prj010PrjBinSid=<bean:write name="prjMdl" property="prjBinSid" />" name="pitctImage" width="30" height="30" alt="<gsmsg:write key="cmn.icon" />" border="1" onload="initImageView('pitctImage');" class="prj_img_ico">
      </logic:notEqual>
      <a href="javascript:void(0)" onClick="return viewPoject('<%= prjMain %>', '<bean:write name="prjMdl" property="projectSid" />');"><span class="text_blue"><bean:write name="prjMdl" property="projectName" /></span></a>
    </td>
    <td class="prj_td" align="left">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
      <td width="99%">
        <logic:equal name="prjMdl" property="keikoku" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.KEIKOKU_ARI) %>">
          <logic:notEqual name="prjMdl" property="rate" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.RATE_MAX) %>">
            <img src="../common/images/keikoku.gif" alt="<gsmsg:write key="cmn.warning" />" class="prj_img_warn">&nbsp;
          </logic:notEqual>
        </logic:equal>
        <a href="javascript:void(0)" onClick="return viewTodo('<%= todoRef %>', '<bean:write name="prjMdl" property="projectSid" />', '<bean:write name="prjMdl" property="todoSid" />');"><span class="text_blue"><bean:write name="prjMdl" property="todoTitle" filter="false"/></span></a>
        <logic:equal name="prjMdl" property="prjTodoEdit" value="true">
        <a href="javascript:void(0)" onClick="return editTodo('<%= todoEdit %>', '<%= mode_edit %>', '<bean:write name="prjMdl" property="projectSid" />', '<bean:write name="prjMdl" property="todoSid" />');"><img src="../project/images/prj_todo_add.gif" alt="<gsmsg:write key="project.56" />"></a>
        </logic:equal>
      </td>
      <td width="1%" align="right" valign="middle" nowrap>
        <logic:notEqual name="prjMdl" property="prjTodoCommentCnt" value="0">
          <a href="javascript:void(0)" onClick="return viewTodoComment('<%= todoRef %>', '<bean:write name="prjMdl" property="projectSid" />', '<bean:write name="prjMdl" property="todoSid" />');">
          <img src="../project/images/todo_ref_comment_s.gif" alt="<gsmsg:write key="cmn.comment" />" class="prj_img_comment_s">
          <span class="text_base"><bean:write name="prjMdl" property="prjTodoCommentCnt" /></span>
          </a>
        </logic:notEqual>
      </td>
      </tr>
      <tr><td colspan="2"><span class="text_base"><bean:write name="prjMdl" property="strKanriNo" /></span></td></tr>
      </table>

    </td>

    <%-- <td class="prj_td" align="center"><span class="text_blue"><bean:write name="prjMdl" property="strJuyo" /></span></td> --%>
    <td class="prj_td" align="center">
      <logic:equal name="prjMdl" property="juyo" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.WEIGHT_KBN_LOW) %>">
        <img src="../project/images/star_b.gif" border="0" alt="<gsmsg:write key="project.58" />"><img src="../project/images/star_g.gif" border="0" alt="<gsmsg:write key="project.58" />"><img src="../project/images/star_g.gif" border="0" alt="<gsmsg:write key="project.58" />">
      </logic:equal>
      <logic:equal name="prjMdl" property="juyo" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.WEIGHT_KBN_MIDDLE) %>">
        <img src="../project/images/star_y.gif" border="0" alt="<gsmsg:write key="project.59" />"><img src="../project/images/star_y.gif" border="0" alt="<gsmsg:write key="project.59" />"><img src="../project/images/star_g.gif" border="0" alt="<gsmsg:write key="project.59" />">
      </logic:equal>
      <logic:equal name="prjMdl" property="juyo" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.WEIGHT_KBN_HIGH) %>">
        <img src="../project/images/star_o.gif" border="0" alt="<gsmsg:write key="project.60" />"><img src="../project/images/star_o.gif" border="0" alt="<gsmsg:write key="project.60" />"><img src="../project/images/star_o.gif" border="0" alt="<gsmsg:write key="project.60" />">
      </logic:equal>
    </td>
    <td class="prj_td" align="center"><span class="text_base"><bean:write name="prjMdl" property="rate" />%<br>(<bean:write name="prjMdl" property="statusName" />)</span></td>
    <td class="prj_td" align="center" nowrap><span class="text_base"><bean:write name="prjMdl" property="strStartDate" /></span></td>
    <td class="prj_td" align="center" nowrap><span class="text_base"><bean:write name="prjMdl" property="strEndDate" /></span></td>
    </tr>

  </logic:iterate>
  </logic:notEmpty>

<div id="dialog-form" title="<gsmsg:write key="project.date.change" />" style="display:none; height: 100px!important;">
    <fieldset>

        <div class="dialog_div" style="float:left;">
         <input type="radio" name="plusMinus" id="radioPl" value="plus" checked><label for="radioPl"><b>＋</b></label><br>
         <input type="radio" name="plusMinus" id="radioMi" value="minus"><label for="radioMi"><b>－</b></label>
        </div>
        <div class="dialog_div" style="float:right; width:80%; vertical-align:
         display: inline-block; _display: inline; margin-top: 0.7em; text-align: left;">
        <b><gsmsg:write key="project.move.days" />:</b>
        <select name="prj010SelMonth" id="selMonth">
          <option value="0" selected="selected"><gsmsg:write key="cmn.months" arg0="0" /></option>
          <option value="1"><gsmsg:write key="cmn.months" arg0="1" /></option>
          <option value="2"><gsmsg:write key="cmn.months" arg0="2" /></option>
          <option value="3"><gsmsg:write key="cmn.months" arg0="3" /></option>
          <option value="4"><gsmsg:write key="cmn.months" arg0="4" /></option>
          <option value="5"><gsmsg:write key="cmn.months" arg0="5" /></option>
          <option value="6"><gsmsg:write key="cmn.months" arg0="6" /></option>
          <option value="7"><gsmsg:write key="cmn.months" arg0="7" /></option>
          <option value="8"><gsmsg:write key="cmn.months" arg0="8" /></option>
          <option value="9"><gsmsg:write key="cmn.months" arg0="9" /></option>
          <option value="10"><gsmsg:write key="cmn.months" arg0="10" /></option>
          <option value="11"><gsmsg:write key="cmn.months" arg0="11" /></option>
          <option value="12"><gsmsg:write key="cmn.months" arg0="12" /></option>
        </select>
        <select name="prj010SelDay" id="selDay">
          <option value="1">0<gsmsg:write key="cmn.day" /></option>
          <option value="1" selected="selected">1<gsmsg:write key="cmn.day" /></option>
          <option value="2">2<gsmsg:write key="cmn.day" /></option>
          <option value="3">3<gsmsg:write key="cmn.day" /></option>
          <option value="4">4<gsmsg:write key="cmn.day" /></option>
          <option value="5">5<gsmsg:write key="cmn.day" /></option>
          <option value="6">6<gsmsg:write key="cmn.day" /></option>
          <option value="7">7<gsmsg:write key="cmn.day" /></option>
          <option value="8">8<gsmsg:write key="cmn.day" /></option>
          <option value="9">9<gsmsg:write key="cmn.day" /></option>
          <option value="10">10<gsmsg:write key="cmn.day" /></option>
          <option value="11">11<gsmsg:write key="cmn.day" /></option>
          <option value="12">12<gsmsg:write key="cmn.day" /></option>
          <option value="13">13<gsmsg:write key="cmn.day" /></option>
          <option value="14">14<gsmsg:write key="cmn.day" /></option>
          <option value="15">15<gsmsg:write key="cmn.day" /></option>
          <option value="16">16<gsmsg:write key="cmn.day" /></option>
          <option value="17">17<gsmsg:write key="cmn.day" /></option>
          <option value="18">18<gsmsg:write key="cmn.day" /></option>
          <option value="19">19<gsmsg:write key="cmn.day" /></option>
          <option value="20">20<gsmsg:write key="cmn.day" /></option>
          <option value="21">21<gsmsg:write key="cmn.day" /></option>
          <option value="22">22<gsmsg:write key="cmn.day" /></option>
          <option value="23">23<gsmsg:write key="cmn.day" /></option>
          <option value="24">24<gsmsg:write key="cmn.day" /></option>
          <option value="25">25<gsmsg:write key="cmn.day" /></option>
          <option value="26">26<gsmsg:write key="cmn.day" /></option>
          <option value="27">27<gsmsg:write key="cmn.day" /></option>
          <option value="28">28<gsmsg:write key="cmn.day" /></option>
          <option value="29">29<gsmsg:write key="cmn.day" /></option>
          <option value="30">30<gsmsg:write key="cmn.day" /></option>
          <option value="31">31<gsmsg:write key="cmn.day" /></option>
        </select>

        </div>

        <br>
        <br clear="all">
        <hr>
        <input type="checkbox" name="holSetCheck" value="<gsmsg:write key="main.holiday.setting" />" id="holSet" checked><label for="holSet"><b><gsmsg:write key="project.lef.holiday" /></b></label>


    </fieldset>
</div>

<div id="dialog-error" title="<gsmsg:write key="cmn.warning" />" style="display:none">
 <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
   <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<gsmsg:write key="cmn.select.3" arg0="TODO" />
 </p>
</div>