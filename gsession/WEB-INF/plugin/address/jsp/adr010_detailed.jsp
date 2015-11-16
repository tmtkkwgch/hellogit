<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


  <table width="100%" cellpadding="0" cellspacing="0">
  <tr>
  <td class="adr_search_area">

    <table width="100%" cellpadding="3" cellspacing="0">
    <tr valign="top">
    <td width="75%" align="center">

      <table width="100%" cellpadding="0" cellspacing="0">
      <tr valign="top">
      <td width="50%" align="left">

        <table cellpadding="3" cellspacing="0" border="1" width="99%" class="user_search2">
        <tr>
        <td class="detail_tbl" align="left" nowrap><gsmsg:write key="cmn.name" /></td>
        <td class="user_search2_td_1" align="left" nowrap>
        <gsmsg:write key="cmn.lastname" />&nbsp;<html:text property="adr010unameSei" maxlength="<%= String.valueOf(GSConstAddress.MAX_LENGTH_NAME_SEI) %>" style="width:129px;" />
        &nbsp;&nbsp;
        <gsmsg:write key="cmn.name3" />&nbsp;<html:text property="adr010unameMei" maxlength="<%= String.valueOf(GSConstAddress.MAX_LENGTH_NAME_MEI) %>" style="width:129px;" />
        </td>
        </tr>

        <tr>
        <td class="detail_tbl" align="left" nowrap><gsmsg:write key="cmn.name.kana" /></td>
        <td class="user_search2_td_1" align="left" nowrap>
        <gsmsg:write key="cmn.lastname" />&nbsp;<html:text property="adr010unameSeiKn" maxlength="<%= String.valueOf(GSConstAddress.MAX_LENGTH_NAME_SEI_KN) %>" style="width:129px;" />
        &nbsp;&nbsp;
        <gsmsg:write key="cmn.name3" />&nbsp;<html:text property="adr010unameMeiKn" maxlength="<%= String.valueOf(GSConstAddress.MAX_LENGTH_NAME_MEI_KN) %>" style="width:129px;" />
        </td>
        </tr>

        <tr>
        <td class="detail_tbl" align="left" nowrap><gsmsg:write key="cmn.company.name" /></td>
        <td class="user_search2_td_1" align="left"><html:text property="adr010detailCoName" maxlength="50" style="width:305px;" /></td>
        </tr>

        <tr>
        <td class="detail_tbl" align="left" nowrap><gsmsg:write key="cmn.affiliation" /></td>
        <td class="user_search2_td_1" align="left"><html:text property="adr010syozoku" maxlength="60" style="width:305px;" /></td>
        </tr>

        <tr>
        <td class="detail_tbl" align="left" nowrap><gsmsg:write key="cmn.post" /></td>
        <td class="user_search2_td_1" align="left">
          <html:select name="adr010Form" property="adr010position">
            <html:optionsCollection name="adr010Form" property="positionCmbList" value="value" label="label" />
          </html:select>
        </td>
        </tr>
        </table>

      </td>
      <td width="50%" align="right">

        <table cellpadding="3" cellspacing="0" border="1" width="99%" class="user_search2">
        <tr>
        <td class="detail_tbl" align="left" nowrap>E-MAIL</td>
        <td class="user_search2_td_1" align="left"><html:text property="adr010mail" maxlength="50" style="width:275px;" /></td>
        </tr>

        <tr>
        <td class="detail_tbl" align="left" nowrap><gsmsg:write key="cmn.staff" /></td>
        <td class="user_search2_td_1" align="left">
          <html:select name="adr010Form" property="adr010detailTantoGroup" onchange="buttonPush('init');">
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
            <input type="button" onclick="openGroupWindow(this.form.adr010detailTantoGroup, 'adr010detailTantoGroup', '0', 'init')" class="group_btn" value="&nbsp;&nbsp;" id="adr010DetailedGroupBtn">
          </logic:notEqual>
          <html:select name="adr010Form" property="adr010detailTantoUser">
            <html:optionsCollection name="adr010Form" property="userCmbList" value="value" label="label" />
          </html:select>
        </td>
        </tr>

        <tr>
        <td class="detail_tbl" align="left" width="20%" nowrap><gsmsg:write key="address.11" /></td>
        <td class="user_search2_td_1" align="left" width="80%">
          <html:select name="adr010Form" property="adr010detailAtiSid">
            <html:optionsCollection name="adr010Form" property="atiCmbList" value="value" label="label" />
          </html:select>
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
    <td width="25%" align="center">

    </td>
    </tr>
    </table>

  </td>
  </tr>
  </table>