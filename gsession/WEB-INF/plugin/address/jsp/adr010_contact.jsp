<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<%
  String notSbt   = String.valueOf(jp.groupsession.v2.adr.GSConstAddress.NOT_SYUBETU);
%>
<%
  String targetTitle   = String.valueOf(jp.groupsession.v2.adr.GSConstAddress.SEARCH_TARGET_TITLE);
  String targetBiko    = String.valueOf(jp.groupsession.v2.adr.GSConstAddress.SEARCH_TARGET_BIKO);
%>
<%
  String keyWordAnd    = String.valueOf(jp.groupsession.v2.adr.GSConstAddress.KEY_WORD_KBN_AND);
  String keyWordOr     = String.valueOf(jp.groupsession.v2.adr.GSConstAddress.KEY_WORD_KBN_OR);
%>

  <table width="100%" cellpadding="0" cellspacing="0">
  <tr>
  <td width="100%" class="adr_search_area">

    <table width="100%" cellpadding="3" cellspacing="0">
    <tr valign="top">
    <td width="80%" align="center">

      <table width="100%" cellpadding="0" cellspacing="0">
      <tr>
      <td width="40%" valign="top" align="left">

        <table cellpadding="3" cellspacing="0" border="1" width="100%" class="user_search2">
        <tr>
        <td class="detail_tbl" align="left" width="20%" nowrap><gsmsg:write key="cmn.name" /></td>
        <td class="user_search2_td_1" align="left" nowrap>
          <gsmsg:write key="cmn.lastname" />&nbsp;<html:text property="adr010unameSeiContact" maxlength="10" style="width:131px;" />
          &nbsp;&nbsp;
          <gsmsg:write key="cmn.name3" />&nbsp;<html:text property="adr010unameMeiContact" maxlength="10" style="width:131px;" />
        </td>
        </tr>
        <tr>
        <td class="detail_tbl" align="left" width="20%" nowrap><gsmsg:write key="cmn.company.name" /></td>
        <td class="user_search2_td_1" align="left" width="80%">
          <html:text property="adr010CoNameContact" maxlength="50" style="width:275px;" />
        </td>
        </tr>
        <tr>
        <td class="detail_tbl" align="left" width="20%" nowrap><gsmsg:write key="address.10" /></td>
        <td class="user_search2_td_1" align="left" width="80%">
          <html:text property="adr010CoBaseNameContact" maxlength="50" style="width:275px;" />
        </td>
        </tr>
        </table>

      </td>
      <td width="60%" valign="top" align="right">

        <table cellpadding="3" cellspacing="0" border="1" width="95%" class="user_search2">
        <tr>
        <td class="detail_tbl" align="left" width="20%" nowrap><gsmsg:write key="cmn.staff" /></td>
        <td class="user_search2_td_1" align="left" width="80%" nowrap>
          <html:select name="adr010Form" property="adr010tantoGroupContact" onchange="buttonPush('grpChange');" styleClass="select02">
            <logic:iterate id="gpBean" name="adr010Form" property="groupCmbList" scope="request">
              <bean:define id="gpValue" name="gpBean" property="value" type="java.lang.String" />
              <% boolean mygrpFlg = gpValue.indexOf("M") == -1; %>
              <% if (mygrpFlg) { %>
                <html:option value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
              <% } else { %>
                <html:option styleClass="select03" value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
              <% } %>
            </logic:iterate>
          </html:select>
          <input type="button" onclick="openGroupWindow(this.form.adr010tantoGroupContact, 'adr010tantoGroupContact', '0', 'grpChange')" class="group_btn" value="&nbsp;&nbsp;" id="adr010ContactGroupBtn2">
          <br>
          <html:select name="adr010Form" property="adr010tantoUserContact" styleClass="select02">
            <html:optionsCollection name="adr010Form" property="userCmbList" value="value" label="label" />
          </html:select>
        </td>
        </tr>
        <tr>
        <td class="detail_tbl" align="left" width="20%" nowrap><gsmsg:write key="cmn.project" /></td>
        <td class="user_search2_td_1" align="left" width="80%">
          <html:select name="adr010Form" styleClass="select02" property="adr010ProjectContact">
            <html:optionsCollection name="adr010Form" property="projectCmbList" value="value" label="label" />
          </html:select>
        </td>
        </tr>
        <tr>
        <td class="detail_tbl" align="left" width="20%" nowrap><gsmsg:write key="cmn.attached" /></td>
        <td class="user_search2_td_1" align="left" width="80%">
          <html:radio name="adr010Form" property="adr010TempFilekbnContact" value="0" styleId="tempFreeCont"/><label for="tempFreeCont"><span class="text_base2"><gsmsg:write key="cmn.specified.no" /></span></label>&nbsp;
          <html:radio name="adr010Form" property="adr010TempFilekbnContact" value="1" styleId="tempExistCont"/><label for="tempExistCont"><span class="text_base2"><gsmsg:write key="address.adr010.contact.5" /></span></label>&nbsp;
          <html:radio name="adr010Form" property="adr010TempFilekbnContact" value="2" styleId="tempNotExistCont"/><label for="tempNotExistCont"><span class="text_base2"><gsmsg:write key="cmn.no" /></span></label>
        </td>
        </tr>
        </table>

      </td>
      </tr>

      <tr>
      <td width="100%" valign="top" align="center" colspan="2">

        <table cellpadding="3" cellspacing="0" border="1" width="100%" class="user_search2">
        <tr>
        <td class="detail_tbl" align="left" width="5%" nowrap><gsmsg:write key="cmn.date" /></td>
        <td class="user_search2_td_1" align="left" width="95%" colspan="3">
          <span class="text_base2"><html:checkbox name="adr010Form" styleId="adr010dateNoKbn" property="adr010dateNoKbn" value="1" onclick="adr010DateKbn();" /><label for="adr010dateNoKbn"><gsmsg:write key="cmn.without.specifying" /></label></span>

          <html:select property="adr010SltYearFrContact" styleId="selYearFr">
            <html:optionsCollection name="adr010Form" property="adr010YearLabel" value="value" label="label" />
          </html:select>
          <html:select property="adr010SltMonthFrContact" styleId="selMonthFr">
            <html:optionsCollection name="adr010Form" property="adr010MonthLabel" value="value" label="label" />
          </html:select>
          <html:select property="adr010SltDayFrContact" styleId="selDayFr">
            <html:optionsCollection name="adr010Form" property="adr010DayLabel" value="value" label="label" />
          </html:select>
        <input type="button" value="Cal" onclick="wrtCalendarByBtn(this.form.selDayFr, this.form.selMonthFr, this.form.selYearFr, 'adr010FrCalBtn')" class="calendar_btn" id="adr010FrCalBtn">
        ～
          <html:select property="adr010SltYearToContact" styleId="selYearTo">
            <html:optionsCollection name="adr010Form" property="adr010YearLabel" value="value" label="label" />
          </html:select>
          <html:select property="adr010SltMonthToContact" styleId="selMonthTo">
            <html:optionsCollection name="adr010Form" property="adr010MonthLabel" value="value" label="label" />
          </html:select>
          <html:select property="adr010SltDayToContact" styleId="selDayTo">
            <html:optionsCollection name="adr010Form" property="adr010DayLabel" value="value" label="label" />
          </html:select>
        <input type="button" value="Cal" onclick="wrtCalendarByBtn(this.form.selDayTo, this.form.selMonthTo, this.form.selYearTo, 'adr010ToCalBtn')" class="calendar_btn" id="adr010ToCalBtn">

        </td>
        </tr>
        <tr>
        <td class="detail_tbl" align="left" width="9%" nowrap><gsmsg:write key="cmn.type" /></td>
        <td class="user_search2_td_1" align="left" width="91%" colspan="3">
          <html:radio styleId="adr010Mark_m1" name="adr010Form" property="adr010SyubetsuContact" value="<%= notSbt %>" />&nbsp;<label for="adr010Mark_m1"><gsmsg:write key="cmn.without.specifying" /></label>&nbsp;
          <html:radio styleId="adr010Mark_1" name="adr010Form" property="adr010SyubetsuContact" value="<%= markTel %>" />&nbsp;<label for="adr010Mark_1"><%= (String) imgMap.get(markTel) %>&nbsp;<%= (String) imgTextMap.get(markTel) %></label>&nbsp;
          <html:radio styleId="adr010Mark_2" name="adr010Form" property="adr010SyubetsuContact" value="<%= markMail %>" />&nbsp;<label for="adr010Mark_2"><%= (String) imgMap.get(markMail) %>&nbsp;<%= (String) imgTextMap.get(markMail) %></label>&nbsp;
          <html:radio styleId="adr010Mark_3" name="adr010Form" property="adr010SyubetsuContact" value="<%= markWeb %>" />&nbsp;<label for="adr010Mark_3"><%= (String) imgMap.get(markWeb) %>&nbsp;<%= (String) imgTextMap.get(markWeb) %></label>&nbsp;
          <html:radio styleId="adr010Mark_4" name="adr010Form" property="adr010SyubetsuContact" value="<%= markMeeting %>" />&nbsp;<label for="adr010Mark_4"><%= (String) imgMap.get(markMeeting) %>&nbsp;<%= (String) imgTextMap.get(markMeeting) %></label>&nbsp;
          <html:radio styleId="adr010Mark_0" name="adr010Form" property="adr010SyubetsuContact" value="<%= markOther %>" /><label for="adr010Mark_0">&nbsp;<gsmsg:write key="cmn.other" /></label>&nbsp;
        </td>
        </tr>
        <tr>
        <td class="detail_tbl" align="left" width="9%" nowrap><gsmsg:write key="cmn.keyword" /></td>
        <td class="user_search2_td_1" align="left" width="41%">
          <html:text name="adr010Form" maxlength="50" property="adr010SearchWordContact" style="width:335px;"/>
          <div class="text_base2">
            <html:radio name="adr010Form" property="adr010KeyWordkbnContact" value="<%= keyWordAnd %>" styleId="keyKbn_01" /><label for="keyKbn_01"><gsmsg:write key="cmn.contains.all" />(AND)</label>&nbsp;
            <html:radio name="adr010Form" property="adr010KeyWordkbnContact" value="<%= keyWordOr %>" styleId="keyKbn_02" /><label for="keyKbn_02"><gsmsg:write key="cmn.containing.either" />(OR)</label>
          </div>
        </td>
        <td class="detail_tbl" align="left" width="9%" nowrap><gsmsg:write key="cmn.search2" /></td>
        <td class="user_search2_td_1" align="left" width="41%">
          <html:multibox styleId="search_scope_01" name="adr010Form" property="adr010SearchTargetContact" value="<%= targetTitle %>" /><label for="search_scope_01"><gsmsg:write key="cmn.title" /></label>
          <html:multibox styleId="search_scope_02" name="adr010Form" property="adr010SearchTargetContact" value="<%= targetBiko %>" /><label for="search_scope_02"><gsmsg:write key="cmn.memo" /></label>
        </td>
        </tr>
        </table>

      </td>
      </tr>

      <tr>
      <td align="center" colspan ="2">
        <br><br>
        <input type="button" name="btn_search" class="btn_search_n1" value="<gsmsg:write key="cmn.search" />" onClick='buttonPush("search");'>
      </td>
      </tr>
      </table>

    </td>
    <td width="20%" align="center">

    </td>
    </tr>
    </table>

  </td>
  </tr>
  </table>
