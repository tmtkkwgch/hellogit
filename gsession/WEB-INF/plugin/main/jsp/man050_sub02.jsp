<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>

    <table width="100%" cellpadding="0" cellspacing="0">
    <tr>
    <td width="100%" class="tbl_gray2">
      <table width="100%" cellpadding="0" cellspacing="0">
      <tr>
      <td width="50%" valign="top">

        <table cellpadding="3" cellspacing="0" width="99%" class="user_search2_syosai" border="1">
        <tr>
        <td class="detail_tbl" colspan="2" align="left" width="20%" nowrap><gsmsg:write key="cmn.affiliation.group" /></td>

        <td class="user_search2_syosai_td_1" align="left" width="80%">
          <html:select name="man050Form" property="man050grpSid" styleClass="select01" onchange="changeGrp2()">
          <logic:notEmpty name="man050Form" property="man050GroupList">
            <html:optionsCollection name="man050Form" property="man050GroupList" value="value" label="label" />
          </logic:notEmpty>
           </html:select>
          <input type="button" onclick="openGroupWindow(this.form.man050grpSid, 'man050grpSid', '0')" class="group_btn" value="&nbsp;&nbsp;" id="man050GroupBtn">
        </td>
        </tr>
        <tr>
        <td width="20%" colspan="2" class="detail_tbl" align="left" nowrap><gsmsg:write key="user.75" /></td>
        <td width="80%" class="user_search2_syosai_td_1" align="left">
          <html:select name="man050Form" property="man050usrSid" styleClass="select01" onchange="buttonPush('search')">
          <logic:notEmpty name="man050Form" property="man050BelongUserList">
            <html:optionsCollection name="man050Form" property="man050BelongUserList" value="value" label="label" />
          </logic:notEmpty>
          </html:select>
        </td>

        </tr>
        <tr>
        <td rowspan="2" class="detail_tbl" align="left" nowrap><gsmsg:write key="main.man050.2" /></td>
        <td class="detail_tbl" align="left" nowrap><gsmsg:write key="cmn.start" /></td>
        <td class="user_search2_syosai_td_1" align="left" valign="middle" nowrap>
          <html:select property="man050FrYear" styleId="fromYear" onchange="buttonPush('search')">
          <html:optionsCollection name="man050Form" property="man050YearList" value="value" label="label" />
          </html:select>
          <html:select property="man050FrMonth" styleId="fromMonth" onchange="buttonPush('search')">
          <html:optionsCollection name="man050Form" property="man050MonthList" value="value" label="label" />
          </html:select>
          <html:select property="man050FrDay" styleId="fromDay" onchange="buttonPush('search')">
          <html:optionsCollection name="man050Form" property="man050DayList" value="value" label="label" />
          </html:select>
          <input type="button" value="Cal" name="frCalendarBtn" onclick="wrtCalendarWithReload(this.form.fromDay, this.form.fromMonth, this.form.fromYear)" class="calendar_btn">

          <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="moveDay(1, 1)">
          <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="moveDay(1, 2)">
          <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="moveDay(1, 3)">

        </td>
        </tr>
        <tr>
        <td class="detail_tbl" align="left" nowrap><gsmsg:write key="cmn.end" /></td>
        <td class="user_search2_syosai_td_1" align="left" nowrap>
          <html:select name="man050Form" property="man050ToYear" styleId="toYear" onchange="buttonPush('search')">
          <html:optionsCollection name="man050Form" property="man050YearList" value="value" label="label" />
          </html:select>
          <html:select name="man050Form" property="man050ToMonth" styleId="toMonth" onchange="buttonPush('search')">
          <html:optionsCollection name="man050Form" property="man050MonthList" value="value" label="label" />
          </html:select>
          <html:select name="man050Form" property="man050ToDay" styleId="toDay" onchange="buttonPush('search')">
          <html:optionsCollection name="man050Form" property="man050DayList" value="value" label="label" />
          </html:select>
          <input type="button" value="Cal" name="toCalendarBtn" onclick="wrtCalendarWithReload(this.form.toDay, this.form.toMonth, this.form.toYear);" class="calendar_btn">

          <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="moveDay(2, 1)">
          <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="moveDay(2, 2)">
          <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="moveDay(2, 3)">

        </td>
        </tr>

        </table>

      </td>
      <td width="50%" valign="top" align="right">

        <table cellpadding="3" cellspacing="0" width="97%" class="user_search2_syosai">
        <tr>
        <td class="detail_tbl" align="left" nowrap><gsmsg:write key="main.man050.3" /></td>
        <td class="user_search2_syosai_td_1" align="left">
            <html:radio property="man050Terminal" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstCommon.TERMINAL_KBN_ALL) %>" styleId="terminal0" onclick="return tarminalChange(0)" /><label for="terminal0"><gsmsg:write key="cmn.all" /></label>
            <html:radio property="man050Terminal" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstCommon.TERMINAL_KBN_PC) %>" styleId="terminal1" onclick="return tarminalChange(1)" /><label for="terminal1"><%= jp.groupsession.v2.cmn.GSConstCommon.TERMINAL_KBN_PC_TEXT %></label>
            <html:radio property="man050Terminal" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstCommon.TERMINAL_KBN_MOBILE) %>" styleId="terminal2" onclick="return tarminalChange(2)" /><label for="terminal2"><gsmsg:write key="main.man120.4" /></label>
        </td>
        </tr>
        <tr>
        <td class="detail_tbl" align="left" nowrap><gsmsg:write key="main.man050.4" /></td>
        <td class="user_search2_syosai_td_1" align="left">
          <html:radio property="man050Car" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstCommon.CAR_KBN_PC_ALL) %>" styleId="car0"  onclick="return tarminalChange(3)" /><label for="car0"><gsmsg:write key="cmn.all" /></label>
          <html:radio property="man050Car" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstCommon.CAR_KBN_DOCOMO) %>" styleId="car2"  onclick="return tarminalChange(3)" /><label for="car2"><%= jp.groupsession.v2.cmn.GSConstCommon.CAR_KBN_DOCOMO_TEXT %></label>
          <html:radio property="man050Car" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstCommon.CAR_KBN_KDDI) %>" styleId="car3"  onclick="return tarminalChange(3)" /><label for="car3"><%= jp.groupsession.v2.cmn.GSConstCommon.CAR_KBN_KDDI_TEXT %></label>
          <html:radio property="man050Car" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstCommon.CAR_KBN_SOFTBANK) %>" styleId="car4"  onclick="return tarminalChange(3)" /><label for="car4"><%= jp.groupsession.v2.cmn.GSConstCommon.CAR_KBN_SOFTBANK_TEXT %></label>
        </td>
        </tr>
        </table>

      </td>
      </tr>

      <tr>
      <td colspan="2" align="center"><input type="button" name="btn_search" class="btn_search_n1" value="<gsmsg:write key="cmn.search" />" onClick="buttonPush('search');"></td>

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

    <table cellpadding="0" cellspacing="0" border="0" width="100%" style="padding-bottom:5px;" >
    <tr><td><img src="../common/images/spacer.gif" width="10" height="10" border="0" alt=""></td></tr>

    <tr>
    <td align="right">
      <input type="button" name="btn_usrimp" class="btn_csv_n2" value="<gsmsg:write key="cmn.export" />" onClick="buttonPush('man050export');">
    </td>
    </tr>

    <tr><td><img src="../common/images/spacer.gif" width="10" height="10" border="0" alt=""></td></tr>

    <tr>
    <td align="right">
    <!-- <gsmsg:write key="cmn.pageing" /> -->
    <logic:notEmpty name="man050Form" property="man050PageList">
    <bean:size id="count2" name="man050Form" property="man050PageList" scope="request" />
    <logic:greaterThan name="count2" value="1">
      <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('pageleft');">
      <html:select property="man050PageTop" onchange="changePage('0');" styleClass="text_i">
        <html:optionsCollection name="man050Form" property="man050PageList" value="value" label="label" />
      </html:select>
      <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('pageright');">
    </logic:greaterThan>
    </logic:notEmpty>
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
        <th width="25%" align="center" nowrap class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_NAME) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.name" />▲</span></a></th>
      </logic:equal>
      <logic:equal name="man050Form" property="man050OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>">
        <th width="25%" align="center" nowrap class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_NAME) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.name" />▼</span></a></th>
      </logic:equal>
    </logic:equal>
    <logic:notEqual name="man050Form" property="man050SortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_NAME) %>">
      <th width="25%" align="center" nowrap class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_NAME) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.name" /></span></a></th>
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
        <th width="25%" align="center" nowrap class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_LALG) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>')"><span class="text_tlw"><gsmsg:write key="main.man050.5" />▲</span></a></th>
      </logic:equal>
      <logic:equal name="man050Form" property="man050OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>">
        <th width="25%" align="center" nowrap class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_LALG) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="main.man050.5" />▼</span></a></th>
      </logic:equal>
    </logic:equal>
    <logic:notEqual name="man050Form" property="man050SortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_LALG) %>">
      <th width="25%" align="center" nowrap class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_LALG) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="main.man050.5" /></span></a></th>
    </logic:notEqual>

    <logic:equal name="man050Form" property="man050SortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_TERMINAL) %>">
      <logic:equal name="man050Form" property="man050OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>">
        <th width="15%" align="center" nowrap class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_TERMINAL) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>')"><span class="text_tlw"><gsmsg:write key="main.man050.6" />▲</span></a></th>
      </logic:equal>
      <logic:equal name="man050Form" property="man050OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>">
        <th width="15%" align="center" nowrap class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_TERMINAL) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="main.man050.6" />▼</span></a></th>
      </logic:equal>
    </logic:equal>
    <logic:notEqual name="man050Form" property="man050SortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_TERMINAL) %>">
      <th width="15%" align="center" nowrap class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_TERMINAL) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="main.man050.6" /></span></a></th>
    </logic:notEqual>

    <logic:equal name="man050Form" property="man050SortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_CAR) %>">
      <logic:equal name="man050Form" property="man050OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>">
        <th width="15%" align="center" nowrap class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_CAR) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.careers" />▲</span></a></th>
      </logic:equal>
      <logic:equal name="man050Form" property="man050OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>">
        <th width="15%" align="center" nowrap class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_CAR) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.careers" />▼</span></a></th>
      </logic:equal>
    </logic:equal>
    <logic:notEqual name="man050Form" property="man050SortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_CAR) %>">
      <th width="15%" align="center" nowrap class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_CAR) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.careers" /></span></a></th>
    </logic:notEqual>

    <logic:equal name="man050Form" property="man050SortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_UID) %>">
      <logic:equal name="man050Form" property="man050OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>">
        <th width="20%" align="center" nowrap class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_UID) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.identification.number" />▲</span></a></th>
      </logic:equal>
      <logic:equal name="man050Form" property="man050OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>">
        <th width="20%" align="center" nowrap class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_UID) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.identification.number" />▼</span></a></th>
      </logic:equal>
    </logic:equal>
    <logic:notEqual name="man050Form" property="man050SortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_UID) %>">
      <th width="20%" align="center" nowrap class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_UID) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.identification.number" /></span></a></th>
    </logic:notEqual>

    </tr>

    <logic:notEmpty name="man050Form" property="man050UserList">
    <logic:iterate id="group" name="man050Form" property="man050UserList" scope="request" indexId="idx">

    <bean:define id="mod" value="0" />
    <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
      <bean:define id="tblColor" value="td_lastlogin1" />
    </logic:equal>
    <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
      <bean:define id="tblColor" value="td_lastlogin2" />
    </logic:notEqual>
    <tr class="<bean:write name="tblColor" />">

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

    <td align="left"   nowrap>
      <bean:write name="group" property="terminalName" />
    </td>

    <td align="left"   nowrap>
      <bean:write name="group" property="carName" />
    </td>

    <td align="left"   nowrap>
      <bean:write name="group" property="clhUid" />
    </td>

    </tr>
    </logic:iterate>
    </logic:notEmpty>

    </table>
    <logic:notEmpty name="man050Form" property="man050PageList">
    <table cellpadding="0" cellspacing="0" border="0" width="100%" style="padding-top:5px;" >
    <tr>
    <td align="right">
    <!-- <gsmsg:write key="cmn.pageing" /> -->
    <bean:size id="count2" name="man050Form" property="man050PageList" scope="request" />
    <logic:greaterThan name="count2" value="1">
      <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('pageleft');">
      <html:select property="man050PageBottom" onchange="changePage('1');" styleClass="text_i">
        <html:optionsCollection name="man050Form" property="man050PageList" value="value" label="label" />
      </html:select>
      <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('pageright');">
    </logic:greaterThan>
    </td>
    </tr>
    </table>
    </logic:notEmpty>