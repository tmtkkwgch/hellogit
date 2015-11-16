<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

  <bean:define id="sortKey" name="adr010Form" property="adr010sortKey" />
  <bean:define id="orderKey" name="adr010Form" property="adr010orderKey" />
  <% int iSortKey = ((Integer) sortKey).intValue();                                       %>
  <% int iOrderKey = ((Integer) orderKey).intValue();                                     %>
  <% int[] sortKeyList = Adr010Const.SORTKEY_NORMAL; %>
  <% String[] title_width = new String[] { "19%", "22%", "22%", "10%"};                   %>
  <% String[] titleList = new String[] {gsMsg.getMessage(request, "cmn.name"), gsMsg.getMessage(request, "cmn.company.name"), gsMsg.getMessage(request, "address.10"), gsMsg.getMessage(request, "cmn.post")};  %>

  <% if (cmdMode.intValue() == Adr010Const.CMDMODE_CONTACT) { %>
  <%   sortKeyList = Adr010Const.SORTKEY_CONTACT; %>
  <%   title_width = new String[] { "15%", "15%", "15%", "15%", "10%", "20%", "10%"};     %>
  <%   titleList = new String[] {gsMsg.getMessage(request, "cmn.date"), gsMsg.getMessage(request, "cmn.name"), gsMsg.getMessage(request, "cmn.company.name"), gsMsg.getMessage(request, "address.10"), gsMsg.getMessage(request, "cmn.type"), gsMsg.getMessage(request, "address.6") + " " + gsMsg.getMessage(request, "cmn.title"), gsMsg.getMessage(request, "cmn.registant")};  %>
  <% } else if (cmdMode.intValue() == Adr010Const.CMDMODE_PROJECT) {%>
  <%   sortKeyList = Adr010Const.SORTKEY_PROJECT; %>
  <%   title_width = new String[] { "30%", "30%", "40%"};     %>
  <%   titleList = new String[] {gsMsg.getMessage(request, "cmn.name"), gsMsg.getMessage(request, "cmn.company.name"), "E-MAIL・" + gsMsg.getMessage(request, "cmn.tel")};  %>
  <% }%>

  <div class="fakeContainer" style="border:solid 0px;">
  <table id="demoTable" class="demoTable tl0 table_td_border2" width="100%" cellpadding="0" cellspacing="0">
  <thead>
  <tr class="table_bg_7D91BD">
  <th width="0%" class="detail_tbl sort_select_area"><input type="checkbox" name="adr010webAllCheck" onClick="chgCheckAll('adr010webAllCheck', 'adr010selectSid');"></th>

  <!--   プロジェクト -->
  <% if (cmdMode.intValue() == Adr010Const.CMDMODE_PROJECT) {%>

    <% String[] header_width = new String[] { "25%", "25%", "25%", "25%"}; %>
    <th width="<%= header_width[0] %>" class="detail_tbl sort_select_area">
       <!-- 氏名 -->
       <a href="#" onClick="return sort(<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_UNAME) %>);">
       <span class="cel_adr_data">
       <logic:equal name="adr010Form" property="adr010sortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_UNAME) %>">
          <logic:equal name="adr010Form" property="adr010orderKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.ORDERKEY_ASC) %>" >
               <gsmsg:write key="cmn.name" />▲
          </logic:equal>
          <logic:notEqual name="adr010Form" property="adr010orderKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.ORDERKEY_ASC) %>" >
               <gsmsg:write key="cmn.name" />▼
          </logic:notEqual>
       </logic:equal>
       <logic:notEqual name="adr010Form" property="adr010sortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_UNAME) %>">
            <gsmsg:write key="cmn.name" />
       </logic:notEqual>
       </span>
       </a>
    </th>

    <!-- 会社名 -->
    <th width="<%= header_width[1] %>" class="detail_tbl sort_select_area">
       <a href="#" onClick="return sort(<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_CONAME) %>);">
       <span class="cel_adr_data">
       <logic:equal name="adr010Form" property="adr010sortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_CONAME) %>">
          <logic:equal name="adr010Form" property="adr010orderKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.ORDERKEY_ASC) %>" >
              <gsmsg:write key="cmn.company.name" />▲
          </logic:equal>
          <logic:notEqual name="adr010Form" property="adr010orderKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.ORDERKEY_ASC) %>" >
              <gsmsg:write key="cmn.company.name" />▼
          </logic:notEqual>
       </logic:equal>
       <logic:notEqual name="adr010Form" property="adr010sortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_CONAME) %>">
        <gsmsg:write key="cmn.company.name" />
       </logic:notEqual>
       </span>
       </a>
    </th>

    <!--      電話番号 -->
    <th width="<%= header_width[2] %>" class="detail_tbl sort_select_area" nowrap><span class="cel_adr_data"><gsmsg:write key="cmn.tel" /></span></th>
    <!--     E-MAIL -->
    <th width="<%= header_width[3] %>" class="detail_tbl sort_select_area" nowrap><span class="cel_adr_data">E-MAIL</span></th>

  <% } else {%>
    <% String[] header_width = new String[] { "20%", "25%", "20%", "35%"}; %>
    <th width="<%= header_width[0] %>" class="detail_tbl sort_select_area">
       <!-- 氏名 -->
       <a href="#" onClick="return sort(<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_UNAME) %>);">
       <span class="cel_adr_data">
       <logic:equal name="adr010Form" property="adr010sortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_UNAME) %>">
          <logic:equal name="adr010Form" property="adr010orderKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.ORDERKEY_ASC) %>" >
               <gsmsg:write key="cmn.name" />▲
          </logic:equal>
          <logic:notEqual name="adr010Form" property="adr010orderKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.ORDERKEY_ASC) %>" >
               <gsmsg:write key="cmn.name" />▼
          </logic:notEqual>
       </logic:equal>
       <logic:notEqual name="adr010Form" property="adr010sortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_UNAME) %>">
            <gsmsg:write key="cmn.name" />
       </logic:notEqual>
       </span>
       </a>

       <span class="cel_adr_data">／</span>
   <!-- 役職 -->
       <a href="#" onClick="return sort(<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_POSITION) %>);">
       <span class="cel_adr_data">
       <logic:equal name="adr010Form" property="adr010sortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_POSITION) %>">
          <logic:equal name="adr010Form" property="adr010orderKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.ORDERKEY_ASC) %>" >
               <gsmsg:write key="cmn.post" />▲
          </logic:equal>
          <logic:notEqual name="adr010Form" property="adr010orderKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.ORDERKEY_ASC) %>" >
               <gsmsg:write key="cmn.post" />▼
          </logic:notEqual>
       </logic:equal>
       <logic:notEqual name="adr010Form" property="adr010sortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_POSITION) %>">
            <gsmsg:write key="cmn.post" />
       </logic:notEqual>
       </span>
       </a>
    </th>

        <th width="<%= header_width[1] %>" class="detail_tbl sort_select_area">
<!-- 会社名 -->
    <a href="#" onClick="return sort(<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_CONAME) %>);">
    <span class="cel_adr_data">
    <logic:equal name="adr010Form" property="adr010sortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_CONAME) %>">
       <logic:equal name="adr010Form" property="adr010orderKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.ORDERKEY_ASC) %>" >
           <gsmsg:write key="cmn.company.name" />▲
       </logic:equal>
       <logic:notEqual name="adr010Form" property="adr010orderKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.ORDERKEY_ASC) %>" >
           <gsmsg:write key="cmn.company.name" />▼
       </logic:notEqual>
    </logic:equal>
    <logic:notEqual name="adr010Form" property="adr010sortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_CONAME) %>">
        <gsmsg:write key="cmn.company.name" />
    </logic:notEqual>
    </span>
    </a>

    <span class="cel_adr_data">／</span>
<!-- 拠点 -->
    <a href="#" onClick="return sort(<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_COBASENAME) %>);">
    <span class="cel_adr_data">
    <logic:equal name="adr010Form" property="adr010sortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_COBASENAME) %>">
       <logic:equal name="adr010Form" property="adr010orderKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.ORDERKEY_ASC) %>" >
           <gsmsg:write key="address.10" />▲
       </logic:equal>
       <logic:notEqual name="adr010Form" property="adr010orderKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.ORDERKEY_ASC) %>" >
           <gsmsg:write key="address.10" />▼
       </logic:notEqual>
    </logic:equal>
    <logic:notEqual name="adr010Form" property="adr010sortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_COBASENAME) %>">
        <gsmsg:write key="address.10" />
    </logic:notEqual>
    </span>
    </a>
    </th>

    <!-- 電話番号 -->
    <th width="<%= header_width[2] %>" class="detail_tbl sort_select_area" nowrap><span class="cel_adr_data"><gsmsg:write key="cmn.tel" /></span></th>
    <!-- E-MAIL -->
    <th width="<%= header_width[3] %>" class="detail_tbl sort_select_area" nowrap><span class="cel_adr_data">E-MAIL</span></th>

  <% }%>

  </tr>
  </thead>

  <tbody>

  <logic:iterate id="detailMdl" name="adr010Form" property="detailList">

  <tr class="td_type1">
  <td>
    <% boolean existMail = false; %>
    <logic:notEmpty name="detailMdl" property="mail1"><% existMail = true; %></logic:notEmpty>
    <logic:notEmpty name="detailMdl" property="mail2"><% existMail = true; %></logic:notEmpty>
    <logic:notEmpty name="detailMdl" property="mail3"><% existMail = true; %></logic:notEmpty>
    <% if (existMail) { %>
      <html:multibox name="adr010Form" property="adr010selectSid"><bean:write name="detailMdl"  property="adrSid" /></html:multibox>
    <% } %>
  </td>

  <td>
     <% if (cmdMode.intValue() != Adr010Const.CMDMODE_PROJECT) { %>
         <logic:notEmpty name="detailMdl" property="positionName" >
            <span style="font-size:85%;"><bean:write name="detailMdl" property="positionName" /><br></span>
         </logic:notEmpty>
     <% } %>

     <%
         int dspCnt = 0;
         String mail = null;
         try {
            for(int i = 1; i <= 3; i++) {
              mail = "mail" + i;
              if (dspCnt == 0) {
     %>
          <logic:notEmpty name="detailMdl" property="<%=mail%>">
            <a href="javaScript:void(0);" onClick="addAddress(0, <bean:write name="detailMdl"  property="adrSid" />, 'addUsrAtesaki'); return false;"><span class="text_link"><bean:write name="detailMdl" property="userName" /></span></a>
            <% dspCnt++;%>
          </logic:notEmpty>
      <%
              }
            }
          } catch(Exception e) {
          }
          if (dspCnt == 0) {
      %>
          <span style="font-weight: bold;"><bean:write name="detailMdl" property="userName" /></span>
      <%
          }
      %>

     <logic:notEmpty name="detailMdl" property="viewLabelName">
        <br>
        <span class="label_style"><bean:write name="detailMdl" property="viewLabelName" /></span>
     </logic:notEmpty>
     </td>

<!-- 会社名／拠点 -->
     <td><bean:write name="detailMdl" property="companyName" />

     <% if (cmdMode.intValue() != Adr010Const.CMDMODE_PROJECT) { %>
        <logic:notEmpty name="detailMdl" property="companyBaseName">
        <br>&nbsp;&nbsp;<bean:write name="detailMdl" property="companyBaseName" />
        </logic:notEmpty>
     <% } %>
     </td>

     <td>
     <logic:notEmpty name="detailMdl" property="tel1">
       <span style="white-space: nowrap">
       <bean:write name="detailMdl" property="tel1" />
       <logic:notEmpty name="detailMdl" property="telCmt1">&nbsp;<span style="font-size: 80%;">(<bean:write name="detailMdl" property="telCmt1" />)</span></logic:notEmpty>
       </span><br>
     </logic:notEmpty>
     <logic:notEmpty name="detailMdl" property="tel2">
       <span style="white-space: nowrap">
       <bean:write name="detailMdl" property="tel2" />
       <logic:notEmpty name="detailMdl" property="telCmt2">&nbsp;<span style="font-size: 80%;">(<bean:write name="detailMdl" property="telCmt2" />)</span></logic:notEmpty>
       </span><br>
     </logic:notEmpty>
     <logic:notEmpty name="detailMdl" property="tel3">
       <span style="white-space: nowrap">
       <bean:write name="detailMdl" property="tel3" />
       <logic:notEmpty name="detailMdl" property="telCmt3">&nbsp;<span style="font-size: 80%;">(<bean:write name="detailMdl" property="telCmt3" />)</span></logic:notEmpty>
       </span>
     </logic:notEmpty>
     </td>

     <td>
     <logic:notEmpty name="detailMdl" property="mail1">
       <a href="javaScript:void(0);" onClick="addAddressExt(0, <bean:write name="detailMdl" property="adrSid" />, 'addUsrAtesaki', 1); return false;"><span class="normal_link"><bean:write name="detailMdl" property="mail1" /></span></a>
       <logic:notEmpty name="detailMdl" property="mailCmt1">&nbsp;<span style="font-size: 80%;">(<bean:write name="detailMdl" property="mailCmt1" />)</span></logic:notEmpty>
       <br>
     </logic:notEmpty>
     <logic:notEmpty name="detailMdl" property="mail2">
       <a href="javaScript:void(0);" onClick="addAddressExt(0, <bean:write name="detailMdl" property="adrSid" />, 'addUsrAtesaki', 2); return false;"><span class="normal_link"><bean:write name="detailMdl" property="mail2" /></span></a>
       <logic:notEmpty name="detailMdl" property="mailCmt2">&nbsp;<span style="font-size: 80%;">(<bean:write name="detailMdl" property="mailCmt2" />)</span></logic:notEmpty>
       <br>
     </logic:notEmpty>
     <logic:notEmpty name="detailMdl" property="mail3">
       <a href="javaScript:void(0);" onClick="addAddressExt(0, <bean:write name="detailMdl" property="adrSid" />, 'addUsrAtesaki', 3); return false;"><span class="normal_link"><bean:write name="detailMdl" property="mail3" /></span></a>
       <logic:notEmpty name="detailMdl" property="mailCmt3">&nbsp;<span style="font-size: 80%;">(<bean:write name="detailMdl" property="mailCmt3" />)</span></logic:notEmpty>
       <br>
     </logic:notEmpty>
     </td>

  </tr>
  </logic:iterate>

  </tbody>
  </table>
  </div>
