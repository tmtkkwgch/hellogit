<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>


    <logic:equal name="man050Form" property="man050adminFlg" value="true">

      <table width="100%" cellpadding="0" cellspacing="0">
      <tr>
      <td width="100%" class="tbl_gray2">
        <table width="100%" cellpadding="0" cellspacing="0">
        <tr>
        <td width="50%" valign="top">

          <table cellpadding="3" cellspacing="0" width="99%" class="user_search2_syosai">
          <tr>
          <td class="detail_tbl" align="left" width="20%" nowrap><gsmsg:write key="cmn.affiliation.group" /></td>
          <td class="user_search2_syosai_td_1" align="left" width="80%">
            <html:select name="man050Form" property="man050grpSid" styleClass="select01" onchange="changeGrp();">
            <logic:notEmpty name="man050Form" property="man050GroupList">
              <html:optionsCollection name="man050Form" property="man050GroupList" value="value" label="label" />
            </logic:notEmpty>
             </html:select>
             <input type="button" onclick="openGroupWindow(this.form.man050grpSid, 'man050grpSid', '0')" class="group_btn" value="&nbsp;&nbsp;" id="man050GroupBtn">
          </td>
          </tr>
          </table>
        </td>
        <td width="50%" valign="middle" align="right">
          <input type="button" name="btn_usrimp" class="btn_csv_n2" value="<gsmsg:write key="cmn.export" />" onClick="buttonPush('man050export');">
        </td>
        </tr>
        </table>
      </td>
      </tr>
      </table>

      <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td width="0%" class="tab_bottom_left"><img src="../common/images/spacer.gif" width="10" height="10" border="0" alt=""></td>
        <td class="tab_bottom_mid" width="100%" align="right" valign="bottom"></td>
        <td width="0%" class="tab_bottom_right"><img src="../common/images/spacer.gif" width="10" height="10" border="0" alt=""></td>
      </tr>
      </table>
    </logic:equal>

    <logic:notEqual name="man050Form" property="man050adminFlg" value="true">
      <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td width="70%" valign="bottom">
        <span class="text_tlw_black"><gsmsg:write key="cmn.show.group" /></span>
        <html:select name="man050Form" property="man050grpSid" styleClass="select01" onchange="changeGrp();">
        <logic:notEmpty name="man050Form" property="man050GroupList">
          <html:optionsCollection name="man050Form" property="man050GroupList" value="value" label="label" />
        </logic:notEmpty>
         </html:select>
      </td>
      </tr>
      </table>
    </logic:notEqual>

      <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td class="td_type0">
        <table class="lastlogintd1" width="100%" border="0" cellspacing="3" cellpadding="1">
        <tr>
        <td class="lastlogin_tdBgColor1" nowrap><gsmsg:write key="cmn.today" /></td>
        <td class="lastlogin_tdBgColor2" nowrap><gsmsg:write key="man.yesterday" /></td>
        <td class="lastlogin_tdBgColor3" nowrap><gsmsg:write key="man.days.ago" arg0="2" /></td>
        <td class="lastlogin_tdBgColor4" nowrap><gsmsg:write key="man.days.ago" arg0="3" /></td>
        <td class="lastlogin_tdBgColor5" nowrap><gsmsg:write key="man.days.ago" arg0="4" /></td>
        <td class="lastlogin_tdBgColor6" nowrap><gsmsg:write key="man.days.ago" arg0="5" /></td>
        <td class="lastlogin_tdBgColor7" nowrap><gsmsg:write key="man.days.ago" arg0="10" /></td>
        <td class="lastlogin_tdBgColor8" nowrap><gsmsg:write key="main.man050.1" /></td>
        </tr>
        </table>
      </td>
      </tr>
      </table>

      <!-- <gsmsg:write key="cmn.title" /> -->
      <table class="tl0 lastlogintd2" width="100%" border="0" cellspacing="0" cellpadding="3">
      <tr>

      <logic:equal name="man050Form" property="man050SortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_SNO) %>">
      <logic:equal name="man050Form" property="man050OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>">
        <th width="0%" align="center" nowrap class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_SNO) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.employee.staff.number" />▲</span></a></th>
      </logic:equal>
      <logic:equal name="man050Form" property="man050OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>">
        <th width="0%" align="center" nowrap class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_SNO) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.employee.staff.number" />▼</span></a></th>
      </logic:equal>
      </logic:equal>
      <logic:notEqual name="man050Form" property="man050SortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_SNO) %>">
        <th width="0%" align="center" nowrap class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_SNO) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.employee.staff.number" /></span></a></th>
      </logic:notEqual>

      <logic:equal name="man050Form" property="man050SortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_NAME) %>">
      <logic:equal name="man050Form" property="man050OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>">
        <th width="50%" align="center" nowrap class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_NAME) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.name" />▲</span></a></th>
      </logic:equal>
      <logic:equal name="man050Form" property="man050OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>">
        <th width="50%" align="center" nowrap class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_NAME) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.name" />▼</span></a></th>
      </logic:equal>
      </logic:equal>
      <logic:notEqual name="man050Form" property="man050SortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_NAME) %>">
        <th width="50%" align="center" nowrap class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_NAME) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.name" /></span></a></th>
      </logic:notEqual>

      <logic:equal name="man050Form" property="man050SortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_YKSK) %>">
      <logic:equal name="man050Form" property="man050OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>">
        <th width="0%" align="center" nowrap class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_YKSK) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.post" />▲</span></a></th>
      </logic:equal>
      <logic:equal name="man050Form" property="man050OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>">
        <th width="0%" align="center" nowrap class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_YKSK) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.post" />▼</span></a></th>
      </logic:equal>
      </logic:equal>
      <logic:notEqual name="man050Form" property="man050SortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_YKSK) %>">
        <th width="0%" align="center" nowrap class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_YKSK) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.post" /></span></a></th>
      </logic:notEqual>

      <logic:equal name="man050Form" property="man050SortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_LALG) %>">
      <logic:equal name="man050Form" property="man050OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>">
        <th width="50%" align="center" nowrap class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_LALG) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>')"><span class="text_tlw"><gsmsg:write key="user.usr090.2" />▲</span></a></th>
      </logic:equal>
      <logic:equal name="man050Form" property="man050OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>">
        <th width="50%" align="center" nowrap class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_LALG) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="user.usr090.2" />▼</span></a></th>
      </logic:equal>
      </logic:equal>
      <logic:notEqual name="man050Form" property="man050SortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_LALG) %>">
        <th width="50%" align="center" nowrap class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_LALG) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="user.usr090.2" /></span></a></th>
      </logic:notEqual>

      <logic:equal name="man050Form" property="man050adminFlg" value="true">
      <th width="0%" align="center" nowrap class="table_bg_7D91BD">&nbsp;</th>
      </logic:equal>

      </tr>

      <logic:iterate id="group" name="man050Form" property="man050UserList" scope="request" indexId="idx">

      <bean:define id="tblColor" value="lastlogin_tdBgColor1" />
      <bean:define id="mod" name="group" property="lgintimeFg" />
      <logic:equal name="mod" value="1">
        <% tblColor = "lastlogin_tdBgColor1"; %>
      </logic:equal>

      <logic:equal name="mod" value="2">
        <% tblColor = "lastlogin_tdBgColor2"; %>
      </logic:equal>

      <logic:equal name="mod" value="3">
        <% tblColor = "lastlogin_tdBgColor3"; %>
      </logic:equal>

      <logic:equal name="mod" value="4">
        <% tblColor = "lastlogin_tdBgColor4"; %>
      </logic:equal>

      <logic:equal name="mod" value="5">
        <% tblColor = "lastlogin_tdBgColor5"; %>
      </logic:equal>

      <logic:equal name="mod" value="6">
        <% tblColor = "lastlogin_tdBgColor6"; %>
      </logic:equal>

      <logic:equal name="mod" value="7">
        <% tblColor = "lastlogin_tdBgColor7"; %>
      </logic:equal>

      <logic:equal name="mod" value="8">
        <% tblColor = "lastlogin_tdBgColor8"; %>
      </logic:equal>
      <tr class="<%= tblColor %>">

      <td align="left"   nowrap>
        <bean:write name="group" property="syainno" />
      </td>

      <td align="left"   nowrap>
        <a href="javaScript:void(0);" onClick="openUserInfoWindow(<bean:write name="group" property="usrsid" />);"><span class="normal_link"><bean:write name="group" property="fullName" /></span></a>
      </td>

      <td align="left"   nowrap>
        <bean:write name="group" property="yakusyoku" />
      </td>

      <td align="center" nowrap>
        <bean:write name="group" property="strLgintime" />
      </td>

      <logic:equal name="man050Form" property="man050adminFlg" value="true">
      <td align="center" nowrap>
        <input type="button" class="btn_base1" value="<gsmsg:write key="user.usr031.18" />" onclick="return moveDetail(<bean:write name="group" property="usrsid" />)">
      </td>
      </logic:equal>

      </tr>
      </logic:iterate>
      </table>
