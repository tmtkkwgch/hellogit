<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


  <table class="tl0 table_td_border2" width="100%" cellpadding="0" cellspacing="0">

  <tr>
  <th width="0%" class="detail_tbl sort_select_area"><input type="checkbox" name="allCheck" onClick="changeChk();"></th>

<!-- コンタクト履歴 -->
  <% if (cmdMode.intValue() == Adr010Const.CMDMODE_CONTACT) { %>
      <% String[] header_width = new String[] { "15%", "15%", "15%", "15%", "25%", "15%"}; %>

       <!-- 日時 -->
       <th width="<%= header_width[0] %>" class="detail_tbl sort_select_area">
       <a href="#" onClick="return sort(<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_CONTACT_DATE) %>);">
       <span class="cel_adr_data">
       <logic:equal name="adr010Form" property="adr010sortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_CONTACT_DATE) %>">
          <logic:equal name="adr010Form" property="adr010orderKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.ORDERKEY_ASC) %>" >
               <gsmsg:write key="cmn.date" />▲
          </logic:equal>
          <logic:notEqual name="adr010Form" property="adr010orderKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.ORDERKEY_ASC) %>" >
               <gsmsg:write key="cmn.date" />▼
          </logic:notEqual>
       </logic:equal>
       <logic:notEqual name="adr010Form" property="adr010sortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_CONTACT_DATE) %>">
            <gsmsg:write key="cmn.date" />
       </logic:notEqual>
       </span>
       </a>
    </th>

    <!-- 氏名 -->
    <th width="<%= header_width[1] %>" class="detail_tbl sort_select_area">
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

    <!-- 会社名／拠点 -->
    <th width="<%= header_width[2] %>" class="detail_tbl sort_select_area">
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

    <!-- 種別 -->
    <th width="<%= header_width[3] %>" class="detail_tbl sort_select_area">
       <a href="#" onClick="return sort(<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_CONTACT_TYPE) %>);">
       <span class="cel_adr_data">
       <logic:equal name="adr010Form" property="adr010sortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_CONTACT_TYPE) %>">
          <logic:equal name="adr010Form" property="adr010orderKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.ORDERKEY_ASC) %>" >
               <gsmsg:write key="cmn.type" />▲
          </logic:equal>
          <logic:notEqual name="adr010Form" property="adr010orderKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.ORDERKEY_ASC) %>" >
               <gsmsg:write key="cmn.type" />▼
          </logic:notEqual>
       </logic:equal>
       <logic:notEqual name="adr010Form" property="adr010sortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_CONTACT_TYPE) %>">
            <gsmsg:write key="cmn.type" />
       </logic:notEqual>
       </span>
       </a>
    </th>

    <!-- コンタクト履歴 タイトル -->
    <th width="<%= header_width[4] %>" class="detail_tbl sort_select_area">
       <a href="#" onClick="return sort(<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_CONTACT_TITLE) %>);">
       <span class="cel_adr_data">
       <logic:equal name="adr010Form" property="adr010sortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_CONTACT_TITLE) %>">
          <logic:equal name="adr010Form" property="adr010orderKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.ORDERKEY_ASC) %>" >
               <gsmsg:write key="address.6" />&nbsp;<gsmsg:write key="cmn.title" />▲
          </logic:equal>
          <logic:notEqual name="adr010Form" property="adr010orderKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.ORDERKEY_ASC) %>" >
               <gsmsg:write key="address.6" />&nbsp;<gsmsg:write key="cmn.title" />▼
          </logic:notEqual>
       </logic:equal>
       <logic:notEqual name="adr010Form" property="adr010sortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_CONTACT_TITLE) %>">
            <gsmsg:write key="address.6" />&nbsp;<gsmsg:write key="cmn.title" />
       </logic:notEqual>
       </span>
       </a>
    </th>

    <!-- 登録者 -->
    <th width="<%= header_width[5] %>" class="detail_tbl sort_select_area">
       <a href="#" onClick="return sort(<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_CONTACT_ENTRYUSER) %>);">
       <span class="cel_adr_data">
       <logic:equal name="adr010Form" property="adr010sortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_CONTACT_ENTRYUSER) %>">
          <logic:equal name="adr010Form" property="adr010orderKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.ORDERKEY_ASC) %>" >
               <gsmsg:write key="cmn.registant" />▲
          </logic:equal>
          <logic:notEqual name="adr010Form" property="adr010orderKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.ORDERKEY_ASC) %>" >
               <gsmsg:write key="cmn.registant" />▼
          </logic:notEqual>
       </logic:equal>
       <logic:notEqual name="adr010Form" property="adr010sortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_CONTACT_ENTRYUSER) %>">
            <gsmsg:write key="cmn.registant" />
       </logic:notEqual>
       </span>
       </a>
    </th>


<!--   プロジェクト -->
  <% } else if (cmdMode.intValue() == Adr010Const.CMDMODE_PROJECT) {%>
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
    </th>
    <!--      電話番号 -->
    <th width="<%= header_width[2] %>" class="detail_tbl sort_select_area" nowrap><span class="cel_adr_data"><gsmsg:write key="cmn.tel" /></span></th>
    <!--     E-MAIL -->
    <th width="<%= header_width[3] %>" class="detail_tbl sort_select_area" nowrap><span class="cel_adr_data">E-MAIL</span></th>


  <% } else { %>
    <% String[] header_width = new String[] { "20%", "25%", "20%", "26%", "9%"}; %>
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
    <!-- コンタクト履歴 -->
    <th width="<%= header_width[4] %>" class="detail_tbl sort_select_area" nowrap><span class="cel_adr_data"><gsmsg:write key="address.6" /></span></th>

  <% }%>
  </tr>


  <logic:iterate id="detailMdl" name="adr010Form" property="detailList">
  <tr class="td_type1">
  <td><html:multibox name="adr010Form" property="adr010selectSid"><bean:write name="detailMdl"  property="adrSid" /></html:multibox></td>
<!-- コンタクト履歴 -->
  <% if (cmdMode.intValue() == Adr010Const.CMDMODE_CONTACT) { %>
     <td align="center"><bean:write name="detailMdl" property="contactDate" /></td>

     <td>
     <a href="#" onClick="return editAddress('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.PROCMODE_EDIT) %>', '<bean:write name="detailMdl" property="adrSid" />')"><span class="text_link"><bean:write name="detailMdl" property="userName" /></span></a>
     <logic:notEmpty name="detailMdl" property="viewLabelName">
     <br>
     <span class="label_style"><bean:write name="detailMdl" property="viewLabelName" /></span>
     </logic:notEmpty>
     </td>
     <td><a href="#" onClick="return viewCompany('<bean:write name="detailMdl" property="acoSid" />');"><span class="text_link"><bean:write name="detailMdl" property="companyName" /></span></a>
     <logic:notEmpty name="detailMdl" property="companyBaseName" >
        <br>&nbsp;&nbsp;<bean:write name="detailMdl" property="companyBaseName" />
    </logic:notEmpty>
     </td>

     <td align="center" nowrap>
       <bean:define id="intImgMark" name="detailMdl" property="contactType" type="java.lang.Integer" />
       <% String imgMark = String.valueOf(intImgMark.intValue()); %>
       <% String key = "none";  if (imgMap.containsKey(imgMark)) { key = imgMark; } %> <%= (String) imgMap.get(key) %>
       <% String txtkey = "none";  if (imgTextMap.containsKey(imgMark)) { txtkey = imgMark; } %> <%= (String) imgTextMap.get(txtkey) %>
     </td>
     <td><a href="#" onClick="return viewContact('<bean:write name="detailMdl" property="adrSid" />');"><span class="text_link"><bean:write name="detailMdl" property="contactTitle" /></span></a></td>
     <td><bean:write name="detailMdl" property="contactEntryUser" /></td>

  <% } else { %>
    <td>
     <% if (cmdMode.intValue() != Adr010Const.CMDMODE_PROJECT) { %>
        <logic:notEmpty name="detailMdl" property="positionName" >
           <span style="font-size:85%;"><bean:write name="detailMdl" property="positionName" /><br></span>
        </logic:notEmpty>
     <% } %>
     <a href="#" onClick="return editAddress('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.PROCMODE_EDIT) %>', '<bean:write name="detailMdl" property="adrSid" />')"><span class="text_link"><bean:write name="detailMdl" property="userName" /></span></a>
     <logic:notEmpty name="detailMdl" property="viewLabelName">
        <br>
        <span class="label_style"><bean:write name="detailMdl" property="viewLabelName" /></span>
     </logic:notEmpty>

     </td>
<!-- 会社名／拠点 -->
     <td><a href="#" onClick="return viewCompany('<bean:write name="detailMdl" property="acoSid" />');"><span class="text_link"><bean:write name="detailMdl" property="companyName" /></span></a>

     <% if (cmdMode.intValue() != Adr010Const.CMDMODE_PROJECT) { %>
        <logic:notEmpty name="detailMdl" property="companyBaseName">
        <br>&nbsp;&nbsp;<bean:write name="detailMdl" property="companyBaseName" />
        </logic:notEmpty>
     <% } %>
     </td>

     <!-- 電話番号 -->
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
       <logic:notEmpty name="detailMdl" property="telCmt1">&nbsp;<span style="font-size: 80%;">(<bean:write name="detailMdl" property="telCmt3" />)</span></logic:notEmpty>
       </span>
     </logic:notEmpty>
     </td>

     <!-- E-MAIL -->
     <td>
     <logic:notEmpty name="detailMdl" property="mail1">
       <a href="mailto:<bean:write name="detailMdl" property="mail1" />"><span class="normal_link"><bean:write name="detailMdl" property="mail1" /></span></a>
       <logic:notEmpty name="detailMdl" property="mailCmt1">&nbsp;<span style="font-size: 80%;">(<bean:write name="detailMdl" property="mailCmt1" />)</span></logic:notEmpty>
       <br>
     </logic:notEmpty>
     <logic:notEmpty name="detailMdl" property="mail2">
       <a href="mailto:<bean:write name="detailMdl" property="mail2" />"><span class="normal_link"><bean:write name="detailMdl" property="mail2" /></span></a>
       <logic:notEmpty name="detailMdl" property="mailCmt2">&nbsp;<span style="font-size: 80%;">(<bean:write name="detailMdl" property="mailCmt2" />)</span></logic:notEmpty>
       <br>
     </logic:notEmpty>
     <logic:notEmpty name="detailMdl" property="mail3">
       <a href="mailto:<bean:write name="detailMdl" property="mail3" />"><span class="normal_link"><bean:write name="detailMdl" property="mail3" /></span></a>
       <logic:notEmpty name="detailMdl" property="mailCmt3">&nbsp;<span style="font-size: 80%;">(<bean:write name="detailMdl" property="mailCmt3" />)</span></logic:notEmpty>
       <br>
     </logic:notEmpty>
     </td>

     <% if (cmdMode.intValue() != Adr010Const.CMDMODE_PROJECT) { %>
        <!-- コンタクト履歴 -->
        <td align="center"><input type="button" value="<gsmsg:write key="address.6" />" class="btn_base1" onClick="viewContact('<bean:write name="detailMdl" property="adrSid" />')"></td>
     <% } %>
  <% } %>

  </tr>
  </logic:iterate>

  </table>
