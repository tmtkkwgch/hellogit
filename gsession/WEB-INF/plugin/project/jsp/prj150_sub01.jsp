<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

    <bean:define id="prj150MovedDspId" name="prj150Form" property="movedDspId" />
    <% boolean movePrjFlg = prj150MovedDspId != null && prj150MovedDspId.equals(jp.groupsession.v2.prj.GSConstProject.SCR_ID_PRJ030); %>

    <tr>
    <% if (movePrjFlg) { %>
      <html:hidden property="prj150naibuInitFlg" />
      <td align="left" colspan="3">
      <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.up" />" name="btn_upper" onClick="return buttonPush('prj150up');">
      <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.down" />" name="btn_downer" onClick="return buttonPush('prj150down');">
      </td>
      <td align="right" colspan="2" align="right">
        <input type="button" class="btn_add_n1" value="<gsmsg:write key="cmn.add" />" onclick="buttonPush('<%= jp.groupsession.v2.prj.prj150.Prj150Action.CMD_ADD_CLICK %>');">
        <logic:notEmpty name="prj150Form" property="member">
          <input type="button" name="btn_tododel"class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onClick="buttonPush('<%= jp.groupsession.v2.prj.prj150.Prj150Action.CMD_DEL_CLICK %>');">
        </logic:notEmpty>
      </td>
    <% } else { %>
      <td colspan="3">&nbsp;</td>
    <% } %>
    </tr>

    <logic:notEmpty name="prj150Form" property="member">
      <tr>

      <%
        String memIdWidth = "55";
        if (movePrjFlg) {
          memIdWidth = "50";
      %>
      <th class="prj_title td_type3" align="center" width="5%"><input type="checkbox" name="prj150naibuAllCheck" value="1" onClick="chgCheckAll('prj150naibuAllCheck', 'prj150naibuSelectMemberSid');"></th>
      <th class="prj_title td_type3" align="center" width="5%"></th>
      <% } %>

      <th class="prj_title td_type3" align="right" width="5%" nowrap><span class="text_prjtodo_list_head">Ｎｏ</span></th>
      <th class="prj_title td_type3" align="center" width="35%"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.name" /></span></th>
      <th class="prj_title td_type3" align="center" width="<%= memIdWidth %>%" nowrap><span class="text_prjtodo_list_head"><gsmsg:write key="project.3" /></span></th>
      </tr>

      <logic:iterate name="prj150Form" property="member" scope="request" id="member" indexId="index">

        <% String tblColor = (index.intValue() % 2 == 0)?"td_type1":"td_type_blue"; %>

        <%
          String idx = index.toString();
          String usr = "usr" + idx;
        %>

        <html:hidden name="member" property="usrSid" indexed="true" styleId="<%= usr %>" />
        <html:hidden name="member" property="rowNumber" indexed="true" />

        <tr>
        <% if (movePrjFlg) { %>
        <td class="<%= tblColor %>" align="center">
          <html:multibox name="prj150Form" property="prj150naibuSelectMemberSid">
            <bean:write name="member" property="usrSid" />
          </html:multibox>
        </td>

        <bean:define id="atiValue" name="member" property="sort" />
        <td class="<%= tblColor %>" align="center">
          <html:radio property="prj150SortRadio" value="<%= String.valueOf(atiValue) %>" />
        </td>
        <% } %>
        </td>
        <td class="<%= tblColor %>" align="right">
          <bean:write name="member" property="rowNumber" />
        </td>
        <td class="<%= tblColor %>" align="left">
          <a href="javaScript:void(0);" onClick="return openUserInfoWindow('<bean:write name="member" property="usrSid" />');"><bean:write name="member" property="usrName" /></a>
        </td>
        <td class="<%= tblColor %>" align="left">

          <%
              String keyId = "key" + idx;
              String keyIdSv = "keySv" + idx;
          %>

          <html:text name="member" property="projectMemberKey" maxlength="20" style="width:231px;" styleClass="text_base_prj" indexed="true" styleId="<%= keyId %>" />
          <html:hidden name="member" property="projectMemberKeySv" indexed="true" styleId="<%= keyIdSv %>" />

        </td>
        </tr>

      </logic:iterate>
    </logic:notEmpty>


