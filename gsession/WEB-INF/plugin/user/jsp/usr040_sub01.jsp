<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

    <html:hidden property="usr040SearchKana" />
    <table width="100%" cellpadding="0">
    <tr valign="top"><td width="75%" align="center">
    <bean:define id="nrow" value="1" type="java.lang.String" />
      <table width="100%" class="user_search">
      <tr align="center">
      <logic:iterate id="existskn" name="usr040Form" property="usr040ekanas" scope="request">
        <bean:define id="row" name="existskn" property="row" type="java.lang.String" />
          <logic:notEqual name="row" value="<%= nrow %>" >
           </tr>
           <tr align="center">
           <% nrow=row; %>
          </logic:notEqual>
        <logic:equal name="row" value="<%= nrow %>" >
          <td>
            <logic:equal name="existskn" property="exists" value="true">
              <a href="#" onClick="return searchKn('<bean:write name="existskn" property="kana" />');"><span class="user_sakuin_text"><bean:write name="existskn" property="kana" /></span></a>
            </logic:equal>
            <logic:notEqual name="existskn" property="exists" value="true">
              <span class="user_base_text2"><bean:write name="existskn" property="kana" /></span>
            </logic:notEqual>
          </td>
        </logic:equal>
      </logic:iterate>
      </tr>
      </table>
      </td>
      <td width="25%" align="center">
      </td>
      </tr>
      </table>
      <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

      <table width="100%" cellpadding="0" cellspacing="0">
      <tr>
      <td width="11%" class="detail_tbl usr_sort_text" nowrap><gsmsg:write key="cmn.sort.order" /></td>
      <td class="user_search2_syosai_td_2 usr_sort_text">
      <span class="text_bb2"><gsmsg:write key="cmn.first.key" /></span>
      <logic:notEqual name="usr040Form" property="sortKeyLabelList" value="">
        <html:select property="usr040sortKey" onchange="changeSortCombo();">
          <html:optionsCollection name="usr040Form" property="sortKeyLabelList" value="value" label="label" />
        </html:select>
      </logic:notEqual>
      <html:radio name="usr040Form" property="usr040orderKey" onclick="return changeSortCombo();" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>" styleId="sort1_up" /><label for="sort1_up"><gsmsg:write key="cmn.order.asc" /></label>
      <html:radio name="usr040Form" property="usr040orderKey" onclick="return changeSortCombo();" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>" styleId="sort1_dw" /><label for="sort1_dw"><gsmsg:write key="cmn.order.desc" /></label>

      &nbsp;&nbsp;&nbsp;&nbsp;

      <span class="text_bb2"><gsmsg:write key="cmn.second.key" /></span>
      <logic:notEqual name="usr040Form" property="sortKeyLabelList" value="">
        <html:select property="usr040sortKey2" onchange="changeSortCombo();">
          <html:optionsCollection name="usr040Form" property="sortKeyLabelList" value="value" label="label" />
        </html:select>
      </logic:notEqual>
      <html:radio name="usr040Form" property="usr040orderKey2" onclick="return changeSortCombo();" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>" styleId="sort2_up" /><label for="sort2_up"><gsmsg:write key="cmn.order.asc" /></label>
      <html:radio name="usr040Form" property="usr040orderKey2" onclick="return changeSortCombo();" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>" styleId="sort2_dw" /><label for="sort2_dw"><gsmsg:write key="cmn.order.desc" /></label>

      </td>
      </tr>
      </table>