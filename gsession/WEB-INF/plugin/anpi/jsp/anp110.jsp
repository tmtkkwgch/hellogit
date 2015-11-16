<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html;" charset="Shift_JIS">
<title>[Group Session] <gsmsg:write key="cmn.admin.setting.menu" /></title>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<script type="text/javascript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../anpi/js/anp110.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>

</head>
<body class="body_03">
<html:form action="/anpi/anp110">
<!-- BODY -->
<input type="hidden" name="CMD">

<html:hidden property="backScreen" />
<html:hidden property="anp110SelectUserSid" />
<html:hidden property="anp110SelectUserNm" />
<html:hidden property="anp110SortKeyIndex" />
<html:hidden property="anp110OrderKey" />
<html:hidden property="anp110NowPage" />
<html:hidden property="anp110DspPage1" />
<html:hidden property="anp110DspPage2" />


<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>


<table align="center" cellpadding="0" cellspacing="5" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>

    <!-- タイトル -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="anp.plugin"/> <gsmsg:write key="anp.anp110.01"/> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
            <input type="button" value="<gsmsg:write key="cmn.import" />" class="btn_csv_n1" onClick="buttonPush('anp110import');">
            <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('anp110back');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
        </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt="">
  </td>
  </tr>

  <tr>
  <td>
      <table cellspacing="5" width="100%" border="0">
         <tr>
         <td width="0%" align="left" nowrap>
         <span class="text_tlw_black"><gsmsg:write key="cmn.mailaddress"/>：</span>
         <span class="text_base">
         <html:radio styleId="mailAll" name="anp110Form" property="anp110SelectMailFilter" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.FILTER_FLG_ALL) %>" onclick="buttonPush('anp110filter');" />
         <label for="mailAll"><gsmsg:write key="cmn.all"/></label>&nbsp;
         <html:radio styleId="mailReg" name="anp110Form" property="anp110SelectMailFilter" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.FILTER_FLG_REG) %>" onclick="buttonPush('anp110filter');" />
         <label for="mailReg"><gsmsg:write key="anp.anp110.02"/></label>&nbsp;
         <html:radio styleId="mailNone" name="anp110Form" property="anp110SelectMailFilter" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.FILTER_FLG_NONE) %>" onclick="buttonPush('anp110filter');" />
         <label for="mailNone"><gsmsg:write key="anp.anp110.03"/></label>&nbsp;
         </span>
         <br>
         <span class="text_tlw_black"><gsmsg:write key="cmn.tel"/>　　　：</span>
         <span class="text_base">
         <html:radio styleId="tellAll" name="anp110Form" property="anp110SelectTellFilter" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.FILTER_FLG_ALL) %>" onclick="buttonPush('anp110filter');" />
         <label for="tellAll"><gsmsg:write key="cmn.all"/></label>&nbsp;
         <html:radio styleId="tellReg" name="anp110Form" property="anp110SelectTellFilter" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.FILTER_FLG_REG) %>" onclick="buttonPush('anp110filter');" />
         <label for="tellReg"><gsmsg:write key="anp.anp110.02"/></label>&nbsp;
         <html:radio styleId="tellNone" name="anp110Form" property="anp110SelectTellFilter" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.FILTER_FLG_NONE) %>" onclick="buttonPush('anp110filter');" />
         <label for="tellNone"><gsmsg:write key="anp.anp110.03"/></label>&nbsp;
         </span>
         </td>
         </tr>

         <tr>
            <!-- グループコンボ -->
            <td width="0%" align="left" nowrap>
            <span class="text_tlw_black"><gsmsg:write key="cmn.show.group" /></span>
            <logic:notEmpty name="anp110Form" property="anp110GroupLabel" scope="request">
                <html:select property="anp110SelectGroupSid" onchange="buttonPush('anp110group');" styleClass="select05">

                  <logic:iterate id="gpBean" name="anp110Form" property="anp110GroupLabel" scope="request">
                  <bean:define id="gpValue" name="gpBean" property="value" type="java.lang.String" />
                  <logic:equal name="gpBean" property="styleClass" value="0">
                  <html:option value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
                  </logic:equal>

                  <logic:notEqual name="gpBean" property="styleClass" value="0">
                  <html:option styleClass="select03" value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
                  </logic:notEqual>
                  </logic:iterate>

                </html:select>
                <input type="button" onclick="openGroupWindow(this.form.anp110SelectGroupSid, 'anp110SelectGroupSid', '1', 'anp110group')" class="group_btn2" value="&nbsp;" id="groupBtn">
              </logic:notEmpty>
            </td>

            <!-- ページコンボ -->
            <td width="0%" align="right" nowrap>
            <bean:size id="pageCount" name="anp110Form" property="anp110PageLabel" scope="request" />
            <logic:greaterThan name="pageCount" value="1">
                  <logic:notEmpty name="anp110Form" property="anp110PageLabel" scope="request">
                  <img alt="<gsmsg:write key="cmn.previous.page" />" src="../common/images/arrow2_l.gif" class="text_i" width="20" height="20" border="0" onClick="buttonPush('anp110pageLast');">
                  <html:select property="anp110DspPage1" onchange="changePage(this);" styleClass="text_i">
                  <html:optionsCollection name="anp110Form" property="anp110PageLabel" value="value" label="label"/>
                  </html:select>
                  <img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="text_i" width="20" height="20" border="0" onClick="buttonPush('anp110pageNext');">
                  </logic:notEmpty>
            </logic:greaterThan>
            </td>
         </tr>
      </table>

        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tl0 table_td_border">
            <tr class="table_bg_7D91BD">
            <!-- 氏名列オーダーキー -->
            <bean:define id="sortKeyIndex" name="anp110Form" property="anp110SortKeyIndex" />
            <bean:define id="orderKey" name="anp110Form" property="anp110OrderKey" />
            <gsmsg:define id="h_num" msgkey="cmn.employee.staff.number"/>
            <gsmsg:define id="h_name" msgkey="cmn.name"/>
            <gsmsg:define id="h_post" msgkey="cmn.post"/>
            <gsmsg:define id="h_mail" msgkey="cmn.mailaddress"/>
            <gsmsg:define id="h_tel" msgkey="cmn.tel"/>


              <% int iSortKeyIndex = ((Integer) sortKeyIndex).intValue();   %>
              <% int iOrderKey = ((Integer) orderKey).intValue();     %>
              <% String[] colTitle = new String[] {h_num, h_name, h_post, h_mail, h_tel}; %>
              <% String[] colWidth = new String[] {"15%", "20%", "10%", "35%", "20%"}; %>
              <% Integer[] colOrder = new Integer[] {1, 1, 1, 1, 1}; %>
              <% for (int i = 0; i < colTitle.length; i++) {      %>
                  <%   String title = colTitle[i];                   %>
                  <%   Integer order = -1;                           %>
                  <%   if (iSortKeyIndex == i) {                     %>
                  <%     if (iOrderKey == GSConst.ORDER_KEY_ASC) {   %>
                  <%       title = title + "▲";                      %>
                  <%     } else {                                    %>
                  <%       title = title + "▼";                      %>
                  <%     }                                           %>
                  <%     order = iOrderKey;                          %>
                  <%   }                                             %>
                  <th width="<%= colWidth[i] %>" class="table_bg_7D91BD" nowrap>
                  <% if (colOrder[i] == 1) { %><a href="#" onClick="return sortList(<%= i %>, <%= order %>);"><% } %>
                  <span class="text_tlw"><%= title %></span><% if (colOrder[i] == 1) { %></a><% } %></th>
              <% } %>
            </tr>

          <logic:notEmpty name="anp110Form" property="anp110List">
              <logic:iterate id="detailModel" name="anp110Form" property="anp110List" indexId="idx">
                <% String[] typeClass = new String[] {"td_type1", "td_type_usr"}; %>
                <% String tdType = typeClass[(idx.intValue() % 2)]; %>
                <tr class="<%= tdType %>">
                  <td align="left"   nowrap><bean:write name="detailModel" property="syainNo" /></td>
                  <td align="left"   nowrap><a href="#" onclick="selectUser(<bean:write name="detailModel" property="usrSid" />,'<bean:write name="detailModel" property="name" />');"><bean:write name="detailModel" property="name" /></a></td>
                  <td align="left" nowrap><bean:write name="detailModel" property="post" /></td>
                  <td align="left" nowrap><bean:write name="detailModel" property="mailAdr" /></td>
                  <td align="left" nowrap><bean:write name="detailModel" property="telNo" /></td>
                </tr>
              </logic:iterate>
            </logic:notEmpty>
        </table>

        <!-- ページコンボ -->
        <table width="100%" cellpadding="5" cellspacing="0">
            <tr>
            <td align="right">
            <bean:size id="pageCount" name="anp110Form" property="anp110PageLabel" scope="request" />
            <logic:greaterThan name="pageCount" value="1">
                  <logic:notEmpty name="anp110Form" property="anp110PageLabel" scope="request">
                  <img alt="<gsmsg:write key="cmn.previous.page" />" src="../common/images/arrow2_l.gif" class="text_i" width="20" height="20" border="0" onClick="buttonPush('anp110pageLast');">
                  <html:select property="anp110DspPage2" onchange="changePage(this);" styleClass="text_i">
                  <html:optionsCollection name="anp110Form" property="anp110PageLabel" value="value" label="label"/>
                  </html:select>
                  <img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="text_i" width="20" height="20" border="0" onClick="buttonPush('anp110pageNext');">
                  </logic:notEmpty>
            </logic:greaterThan>
            </td>
            </tr>
        </table>
  </td>
  </tr>

  <tr>
  <td>

   <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
        <tr>
        <td width="100%" align="right" cellpadding="5" cellspacing="0">
            <input type="button" value="<gsmsg:write key="cmn.import" />" class="btn_csv_n1" onClick="buttonPush('anp110import');">
            <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('anp110back');">
        </td>
        </tr>
    </table>

  </td>
  </tr>
  </table>

</td>
</tr>
</table>


</html:form>
<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>