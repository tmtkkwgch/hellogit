<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

    <bean:define id="prj150MovedDspId" name="prj150Form" property="movedDspId" />
    <% boolean movePrjFlg = prj150MovedDspId != null && prj150MovedDspId.equals(jp.groupsession.v2.prj.GSConstProject.SCR_ID_PRJ030); %>

    <logic:equal name="prj150Form" property="addressPluginKbn" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.PLUGIN_USE) %>">
    <tr>
    <% if (movePrjFlg) { %>
      <html:hidden property="prj150gaibuInitFlg" />
      <td align="left" colspan="5">
        <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.up" />" name="btn_upper" onClick="return buttonPush('prj150gaibuUp');">
        <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.down" />" name="btn_downer" onClick="return buttonPush('prj150gaibuDown');">
      </td>
      <td align="right" colspan="2" align="right">
        <input type="button" class="btn_address_n1" value="<gsmsg:write key="cmn.add" />" onclick="return openCompanyWindow('prj150')"/>
        <logic:notEmpty name="prj150Form" property="prj150DspList">
          <input type="button" name="btn_tododel"class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onClick="buttonPush('<%= jp.groupsession.v2.prj.prj150.Prj150Action.CMD_DEL_OUTER_CLICK %>');">
        </logic:notEmpty>
      </td>
    <% } else { %>
      <td colspan="4">&nbsp;</td>
    <% } %>
    </tr>
    </logic:equal>

    <logic:notEmpty name="prj150Form" property="prj150DspList">

      <tr>
      <% if (movePrjFlg) { %>
        <th class="prj_title td_type3" align="center" width="5%"><input type="checkbox" name="prj150gaibuAllCheck" value="1" onClick="chgCheckAll('prj150gaibuAllCheck', 'prj150gaibuSelectMemberSid');"></th>
        <th class="prj_title td_type3" align="center" width="5%"></th>
      <% } %>

      <th class="prj_title td_type3" align="right" width="5%" nowrap><span class="text_prjtodo_list_head">Ｎｏ</span></th>
      <th class="prj_title td_type3" align="center" width="30%" nowrap><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.company.name" /></span></th>
      <th class="prj_title td_type3" align="center" width="30%" nowrap><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.name" /></span></th>
      <th class="prj_title td_type3" align="center" width="25%" nowrap><span class="text_prjtodo_list_head"><gsmsg:write key="project.prj150.7" /></span></th>
      <% if (movePrjFlg) { %>
        <th class="prj_title td_type3" align="center" width="10%" nowrap></th>
      <% } %>
      </tr>

      <bean:define id="mod" value="0" />
      <logic:iterate name="prj150Form" property="prj150DspList" scope="request" id="prj150DspList" indexId="index">

        <logic:equal name="mod" value="<%= String.valueOf(index.intValue() % 2) %>">
          <bean:define id="tblColor" value="td_type1" />
        </logic:equal>
        <logic:notEqual name="mod" value="<%= String.valueOf(index.intValue() % 2) %>">
          <bean:define id="tblColor" value="td_type_blue" />
        </logic:notEqual>

        <%
          String idx = index.toString();
          String adr = "adr" + idx;
        %>

        <html:hidden name="prj150DspList" property="adrSid" indexed="true" styleId="<%= adr %>" />
        <html:hidden name="prj150DspList" property="gaibuRowNumber" indexed="true" />
        <html:hidden name="prj150DspList" property="gaibuSort" indexed="true" />
        <html:hidden name="prj150DspList" property="companySid" indexed="true" />
        <html:hidden name="prj150DspList" property="companyName" indexed="true" />
        <html:hidden name="prj150DspList" property="adrName" indexed="true" />
        <html:hidden name="prj150DspList" property="adrMail" indexed="true" />
        <html:hidden name="prj150DspList" property="adrTel" indexed="true" />

        <tr>
        <% if (movePrjFlg) { %>
          <td class="<bean:write name="tblColor" />" align="center">
            <html:multibox name="prj150Form" property="prj150gaibuSelectMemberSid">
              <bean:write name="prj150DspList" property="companySid" />:<bean:write name="prj150DspList" property="companyBaseSid" />:<bean:write name="prj150DspList" property="adrSid" />
            </html:multibox>
          </td>
          <bean:define id="atiValue" name="prj150DspList" property="gaibuSort" />
          <td class="<bean:write name="tblColor" />" align="center">
            <html:radio property="prj150SortGaibuRadio" value="<%= String.valueOf(atiValue) %>" />
          </td>
        <% } %>
        <td class="<bean:write name="tblColor" />" align="right">
          <bean:write name="prj150DspList" property="gaibuRowNumber" />
        </td>
        <td class="<bean:write name="tblColor" />" align="left">
          <bean:write name="prj150DspList" property="companyName" />
        </td>
        <td class="<bean:write name="tblColor" />" align="left">
          <span class="text_tantou">
            <bean:write name="prj150DspList" property="adrName" />&nbsp;
          </span>
          <br>
        </td>

        <td class="<bean:write name="tblColor" />" align="left">
          <a href="mailto:<bean:write name="prj150DspList" property="adrMail" />"><bean:write name="prj150DspList" property="adrMail" /></a><br>
          <bean:write name="prj150DspList" property="adrTel" /><br>
        </td>
          <%
              String keyId      = "key" + idx;
              String keyIdSv    = "keySv" + idx;
              String keyId2     = "key2" + idx;
              String keyId2Sv   = "key2Sv" + idx;
          %>

          <html:hidden name="prj150DspList" property="companySid" indexed="true" styleId="<%= keyId %>" />
          <html:hidden name="prj150DspList" property="companySid" indexed="true" styleId="<%= keyIdSv %>" />

          <html:hidden name="prj150DspList" property="companyBaseSid" indexed="true" styleId="<%= keyId2 %>" />
          <html:hidden name="prj150DspList" property="companyBaseSid" indexed="true" styleId="<%= keyId2Sv %>" />

        <% if (movePrjFlg) { %>
          <td class="<bean:write name="tblColor" />" align="center">
            <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n3" onClick="deleteCompany(<bean:write name="prj150DspList" property="companySid" />, <bean:write name="prj150DspList" property="companyBaseSid" />, <bean:write name="prj150DspList" property="adrSid" />);">
          </td>
        <% } %>
        </tr>


      </logic:iterate>
    </logic:notEmpty>


