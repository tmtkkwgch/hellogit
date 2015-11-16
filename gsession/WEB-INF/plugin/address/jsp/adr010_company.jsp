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
      <tr>
      <td width="55%" valign="top">

        <% java.util.List rowList = new java.util.ArrayList(); %>
        <% rowList.add(new String[]{gsMsg.getMessage(request, "cmn.kana.a"), gsMsg.getMessage(request, "cmn.kana.ka"), gsMsg.getMessage(request, "cmn.kana.sa"), gsMsg.getMessage(request, "cmn.kana.ta"), gsMsg.getMessage(request, "cmn.kana.na"), gsMsg.getMessage(request, "cmn.kana.ha"), gsMsg.getMessage(request, "cmn.kana.ma"), gsMsg.getMessage(request, "cmn.kana.ya"), gsMsg.getMessage(request, "cmn.kana.ra"), gsMsg.getMessage(request, "cmn.kana.wa")}); %>
        <% rowList.add(new String[]{gsMsg.getMessage(request, "cmn.kana.i"), gsMsg.getMessage(request, "cmn.kana.ki"), gsMsg.getMessage(request, "cmn.kana.shi"), gsMsg.getMessage(request, "cmn.kana.chi"), gsMsg.getMessage(request, "cmn.kana.ni"), gsMsg.getMessage(request, "cmn.kana.hi"), gsMsg.getMessage(request, "cmn.kana.mi"), "", gsMsg.getMessage(request, "cmn.kana.ri"), gsMsg.getMessage(request, "cmn.kana.wo")}); %>
        <% rowList.add(new String[]{gsMsg.getMessage(request, "cmn.kana.u"), gsMsg.getMessage(request, "cmn.kana.ku"), gsMsg.getMessage(request, "cmn.kana.su"), gsMsg.getMessage(request, "cmn.kana.tsu"), gsMsg.getMessage(request, "cmn.kana.nu"), gsMsg.getMessage(request, "cmn.kana.fu"), gsMsg.getMessage(request, "cmn.kana.mu"), gsMsg.getMessage(request, "cmn.kana.yu"), gsMsg.getMessage(request, "cmn.kana.ru"), gsMsg.getMessage(request, "cmn.kana.n")}); %>
        <% rowList.add(new String[]{gsMsg.getMessage(request, "cmn.kana.e"), gsMsg.getMessage(request, "cmn.kana.ke"), gsMsg.getMessage(request, "cmn.kana.se"), gsMsg.getMessage(request, "cmn.kana.te"), gsMsg.getMessage(request, "cmn.kana.ne"), gsMsg.getMessage(request, "cmn.kana.he"), gsMsg.getMessage(request, "cmn.kana.me"), "", gsMsg.getMessage(request, "cmn.kana.re"), ""}); %>
        <% rowList.add(new String[]{gsMsg.getMessage(request, "cmn.kana.o"), gsMsg.getMessage(request, "cmn.kana.ko"), gsMsg.getMessage(request, "cmn.kana.so"), gsMsg.getMessage(request, "cmn.kana.to"), gsMsg.getMessage(request, "cmn.kana.no"), gsMsg.getMessage(request, "cmn.kana.ho"), gsMsg.getMessage(request, "cmn.kana.mo"), gsMsg.getMessage(request, "cmn.kana.yo"), gsMsg.getMessage(request, "cmn.kana.ro"), ""}); %>
        <bean:define id="extKanaList" name="adr010Form" property="adr010cnameKanaList" type="java.util.List" />

        <table cellpadding="3" cellspacing="0" border="1" width="95%" class="user_search">

        <% for (int rowIndex = 0; rowIndex < rowList.size(); rowIndex++) { %>
        <%     String[] kanaArray = (String[]) rowList.get(rowIndex); %>
        <tr align="center" class="company_initial_height">
        <%     for (int kanaIndex = 0; kanaIndex < kanaArray.length; kanaIndex++) { %>
        <%         String kana = kanaArray[kanaIndex]; %>
        <%         if (extKanaList.contains(kana)) { %>
        <td><a href="#" onClick="return searchToComKana('<%= kana %>');"><span class="company_sakuin_text"><%= kana %></span></a></td>
        <%         } else { %>
        <td><span class="company_base_text2"><%= kana %></span></td>
        <%         } %>
        <%     } %>
        </tr>
        <% } %>
        </table>

      </td>
      <td width="45%" valign="top">

        <table cellpadding="3" cellspacing="0" border="1" width="95%" class="user_search2">

        <tr>
        <td class="detail_tbl" align="left" nowrap><gsmsg:write key="address.7" /></td>
        <td class="user_search2_td_1" align="left">
          <html:text property="adr010code" maxlength="20" style="width:155px;" />
        </td>
        </tr>

        <tr>
        <td class="detail_tbl" align="left" nowrap><gsmsg:write key="cmn.company.name" /></td>
        <td class="user_search2_td_1" align="left">
          <html:text property="adr010coName" maxlength="50" style="width:275px;" />
        </td>
        </tr>

        <tr>
        <td class="detail_tbl" align="left" nowrap><gsmsg:write key="address.9" /></td>
        <td class="user_search2_td_1" align="left">
          <html:text property="adr010coNameKn" maxlength="100" style="width:275px;" />
        </td>
        </tr>

        <tr>
        <td class="detail_tbl" align="left" nowrap><gsmsg:write key="address.10" /></td>
        <td class="user_search2_td_1" align="left">
          <html:text property="adr010coBaseName" maxlength="50" style="width:275px;" />
        </td>
        </tr>

        <tr>
        <td class="detail_tbl" align="left" width="20%" nowrap><gsmsg:write key="address.11" /></td>
        <td class="user_search2_td_1" align="left" width="80%">
          <html:select name="adr010Form" property="adr010atiSid">
            <html:optionsCollection name="adr010Form" property="atiCmbList" value="value" label="label" />
          </html:select>
        </td>
        </tr>

        <tr>
        <td class="detail_tbl" align="left" nowrap><gsmsg:write key="cmn.prefectures" /></td>
        <td class="user_search2_td_1" align="left">
          <html:select name="adr010Form" property="adr010tdfk">
            <html:optionsCollection name="adr010Form" property="tdfkCmbList" value="value" label="label" />
          </html:select>
        </td>
        </tr>

        <tr>
        <td class="detail_tbl" align="left" nowrap><gsmsg:write key="cmn.memo" /></td>
        <td class="user_search2_td_1" align="left">
          <html:text property="adr010biko" maxlength="1000" style="width:275px;" />
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