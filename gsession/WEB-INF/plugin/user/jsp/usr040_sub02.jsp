<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

    <html:hidden property="selectgsid" />
    <html:hidden property="scrollPosition" />
      <table cellpadding="0" cellspacing="0" width="100%">
      <tr valign="top">
      <td width="50%">
        <iframe src="../user/usr022.do?grpId=<bean:write name="usr040Form" property="usr040GrpSearchGIdSave"/>&grpName=<bean:write name="usr040Form" property="usr040GrpSearchGNameSave"/>" class="iframe_01" name="ctgFrame" width="100%" height="100%">
        <gsmsg:write key="user.32" />
        </iframe>
      </td>
      <td width="5%"></td>
      <td width="45%" valign="middle" align="left">
        <table class="tl0 table_td_border2" width="50%" cellpadding="0" cellspacing="0">
        <tr>
        <td width="11%" class="detail_tbl usr_sort_text" nowrap><gsmsg:write key="cmn.group.id" /></td><td class="user_search2_syosai_td_2"><html:text name="usr040Form" property="usr040GrpSearchGId" style="width:153px;"/></td>
        </tr>
        <tr>
        <td width="11%" class="detail_tbl usr_sort_text" nowrap><gsmsg:write key="cmn.group.name" /></td><td class="user_search2_syosai_td_2"><html:text name="usr040Form" property="usr040GrpSearchGName" style="width:153px;"/></td>
        </tr>
        <tr>
        <td colspan="2" style="border:none;padding-top:15px;">
          <div width="50%" style="text-align:center;"><input type="button" name="grpSearch" class="btn_search_n1" value="絞込み" onClick="buttonPush('grpSearch');"></div>
        </td>
        </tr>
        </table>
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
