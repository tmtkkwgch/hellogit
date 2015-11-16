<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

  <table width="100%" cellpadding="0" cellspacing="0">
  <tr valign="top">
  <td width="100%" class="adr_search_area">

    <table width="100%" cellpadding="3" cellspacing="0">
    <tr valign="top">
    <td width="75%" align="center">

      <table width="100%" cellpadding="0" cellspacing="0">
      <tr>
      <td width="100%" valign="top" align="center">

        <table cellpadding="3" cellspacing="0" border="1" width="90%" class="user_search2">
        <tr>
        <td class="detail_tbl" align="left" width="20%" nowrap><gsmsg:write key="cmn.staff" /></td>
        <td class="user_search2_td_1" align="left" width="80%">
          <html:select name="adr010Form" property="adr010tantoGroup" onchange="buttonPush('grpChange');" styleClass="select01">
            <logic:notEmpty name="adr010Form" property="groupCmbList" scope="request">
            <logic:iterate id="gpBean" name="adr010Form" property="groupCmbList" scope="request">
              <bean:define id="gpValue" name="gpBean" property="value" type="java.lang.String" />
              <% boolean mygrpFlg = gpValue.indexOf("M") == -1; %>
              <% if (mygrpFlg) { %>
                <html:option value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
              <% } else { %>
                <html:option styleClass="select03" value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
              <% } %>
            </logic:iterate>
            </logic:notEmpty>
          </html:select>
          <logic:notEqual name="adr010Form" property="adr010webmail" value="1">
            <input type="button" onclick="openGroupWindow(this.form.adr010tantoGroup, 'adr010tantoGroup', '0', 'grpChange')" class="group_btn" value="&nbsp;&nbsp;" id="adr010TantoGroupBtn">
          </logic:notEqual>
          <html:select name="adr010Form" property="adr010tantoUser">
            <html:optionsCollection name="adr010Form" property="userCmbList" value="value" label="label" />
          </html:select>
        </td>
        </tr>
        </table>

      </td>
      </tr>

      <tr>
      <td align="center">
        <br><br><br><br>
        <input type="button" name="btn_search" class="btn_search_n1" value="<gsmsg:write key="cmn.search" />" onClick='buttonPush("search");'>
      </td>
      </tr>
      </table>

    </td>
    <td width="25%" align="center">
    </td>
    </tr>
    </table>

  </td>
  </tr>
  </table>