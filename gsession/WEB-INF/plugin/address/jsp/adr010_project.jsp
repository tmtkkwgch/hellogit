<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:hidden property="projectCmbsize" />

  <table width="100%" cellpadding="0" cellspacing="0">
  <tr>
  <td class="adr_search_area">

    <table width="100%" cellpadding="3" cellspacing="0">
    <tr valign="top">
    <td width="75%" align="center">

      <table width="100%" cellpadding="0" cellspacing="0">
      <tr valign="top">
      <td width="50%" valign="left">

        <table cellpadding="3" cellspacing="0" border="1" width="99%" class="user_search2">
        <tr>
        <td class="detail_tbl" align="left" width="20%" nowrap><gsmsg:write key="address.adr010.project.1" /></td>
        <td class="user_search2_td_1" align="left" width="80%" nowrap>
        <html:radio name="adr010Form" property="projectKbn" value="0" styleId="prjAdd" onclick="buttonPush('chgPrjDspKbn');"/><label for="prjAdd"><span class="text_base2"><gsmsg:write key="cmn.join.project" /></span></label>&nbsp;
        <html:radio name="adr010Form" property="projectKbn" value="1" styleId="prjAll" onclick="buttonPush('chgPrjDspKbn');"/><label for="prjAll"><span class="text_base2"><gsmsg:write key="cmn.all" /></span></label>
        </td>
        </tr>

      <tr>
      <td class="detail_tbl" align="left" width="20%" nowrap><gsmsg:write key="cmn.status" /></td>
      <td class="user_search2_td_1" align="left" width="80%" nowrap>
        <html:radio name="adr010Form" property="statusKbn" value="0" styleId="prjNoStat" onclick="buttonPush('chgPrjDspKbn');"/><label for="prjNoStat"><span class="text_base2"><gsmsg:write key="address.adr010.project.4" /></span></label>&nbsp;
        <html:radio name="adr010Form" property="statusKbn" value="1" styleId="prjCompStat" onclick="buttonPush('chgPrjDspKbn');"/><label for="prjCompStat"><span class="text_base2"><gsmsg:write key="cmn.complete" /></span></label>&nbsp;
        <html:radio name="adr010Form" property="statusKbn" value="2" styleId="prjAllStat" onclick="buttonPush('chgPrjDspKbn');"/><label for="prjAllStat"><span class="text_base2"><gsmsg:write key="cmn.all" /></span></label>
      </td>
      </tr>
      </table>
    </td>

    <td width="50%" valign="right">
      <table cellpadding="3" cellspacing="0" border="1" width="99%" class="user_search2">
      <tr>
      <td class="detail_tbl" align="left" width="20%" nowrap><gsmsg:write key="address.adr010.project.6" /></td>
      <td class="user_search2_td_1" align="left" width="80%">
      <logic:notEmpty name="adr010Form" property="projectCmbList">
        <html:select property="selectingProject">
          <html:optionsCollection name="adr010Form" property="projectCmbList" value="value" label="label" />
        </html:select>
      </logic:notEmpty>
      </td>
      </tr>
      </table>
    </td>
    </tr>

    <tr>
    <td colspan="2" align="center">
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
