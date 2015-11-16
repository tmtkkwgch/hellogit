<%@ page pageEncoding="Windows-31J" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[Group Session]</title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <link rel="stylesheet" href="../webmail/css/webmail.css?<%= GSConst.VERSION_PARAM %>" type="text/css">

  <logic:notEmpty name="wml012Form" property="wml010theme">
    <bean:define id="themeName" name="wml012Form" property="wml010theme" type="java.lang.String" />
    <link rel="stylesheet" type="text/css" href="../webmail/css/theme/<%= themeName %>.css?<%= GSConst.VERSION_PARAM %>" />
  </logic:notEmpty>

  <script language="JavaScript" src="../webmail/js/wml012.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03">

    <bean:define id="wml012checkAddress" name="wml012Form" property="wml010checkAddress" type="java.lang.Integer" />
    <bean:define id="wml012checkFile" name="wml012Form" property="wml010checkFile" type="java.lang.Integer" />
    <% boolean chkAdrFlg = (wml012checkAddress == 1); %>
    <% boolean chkFileFlg = (wml012checkFile == 1); %>

    <div class='bd' style="text-align: left; background-color: #ffffff;">
      <span class="text_base4">宛先、添付ファイルを確認(チェック)の上、送信ボタンを押してください。</span>

      <form method='POST' action='../webmail/wml012.do'>
         <table width="99%">

         <% String[] adrTypeArray = {"To", "Cc", "Bcc"}; %>
         <% for (int adressTypeIdx = 0; adressTypeIdx < adrTypeArray.length; adressTypeIdx++) {%>

           <tr>
           <td width="120" class="table_bg_A5B4E1" nowrap>
           <% if (adressTypeIdx == 0) {%><span class="text_base3"><gsmsg:write key="cmn.from" />:</span>
           <% } else if (adressTypeIdx == 1) {%><span class="text_base3">CC:</span></td>
           <% } else if (adressTypeIdx == 2) {%><span class="text_base3">BCC:</span></td>
           <% } %>
           </td>

           <% String adrType = adrTypeArray[adressTypeIdx];  %>
           <% String adrListName = "wml012" + adrType + "List"; %>
           <% String adrListSizeName = "wml012" + adrType + "ListSize"; %>
           <td width="380"  class="td_type20" id="check<%= adrType %>Area" style="padding: 2px; 5px;">
              <logic:notEmpty name="wml012Form" property="<%= adrListName %>">

              <% if (chkAdrFlg) { %>
              <logic:greaterThan name="wml012Form" property="<%= adrListSizeName %>" value="0">
              <div style="padding: 2px 0px 5px 3px;">
              <a href="#" onClick="return wml012checkAll('check<%= adrType %>');" class="wml_link"><gsmsg:write key="cmn.check.all" /></a>
              </div>
              </logic:greaterThan>
              <% } %>

              <logic:iterate id="sendAddress" name="wml012Form" property="<%= adrListName %>" indexId="adrIdx">
                <% if (adrIdx.intValue() > 0 ) %><br>
                <% String checkName = "check" + adrType + adrIdx.toString(); %>
                <% if (chkAdrFlg) { %><input type="checkbox" name="check<%= adrType %>" id="<%= checkName %>">&nbsp;<% } %>
                <label for="<%= checkName %>" id="<%= checkName %>_label">
                  <logic:empty name="sendAddress" property="domain"><bean:write name="sendAddress" property="address" /></logic:empty>
                  <logic:notEmpty name="sendAddress" property="domain">
                  <bean:write name="sendAddress" property="user" />@<span class="text_wml_domain_<bean:write name="sendAddress" property="domainType" />"><bean:write name="sendAddress" property="domain" /></span><bean:write name="sendAddress" property="domainEnd" />
                  </logic:notEmpty>
                </label>
               </logic:iterate>
               </logic:notEmpty>
           </td>
           </tr>
           <% } %>

           <tr>
           <td class="table_bg_A5B4E1"><span class="text_base3"><gsmsg:write key="cmn.subject" />:&nbsp;</span></td>
           <td id="wmlConfirmTitle" class="td_type20" style="padding: 2px; 5px;">
             <bean:write name="wml012Form" property="wml010sendSubject" />
           </td>
           </tr>
           <tr>
           <td class="table_bg_A5B4E1"><span class="text_base3"><gsmsg:write key="wml.211" />:&nbsp;</span></td>
           <td id="wmlSendPlanDate" class="td_type20" style="padding: 2px; 5px;">
<!--
             このメールは送信ボタンクリック後、すぐに送信されますので、ご注意ください。
-->
             <bean:write name="wml012Form" property="wml012SendPlanDate" />
           </td>
           </tr>
           <tr>
           <td class="table_bg_A5B4E1"><span class="text_base3"><gsmsg:write key="cmn.attach.file" />:&nbsp;</span></td>
           <td id="checkFileArea" class="td_type20" style="padding: 2px; 8px;">
              <logic:notEmpty name="wml012Form" property="wml012TempfileList">

              <% if (chkFileFlg) { %>
              <logic:greaterThan name="wml012Form" property="wml012TempfileList" value="0">
              <div style="padding: 2px 0px 5px 3px;">
              <a href="#" onClick="return wml012checkAll('checkFile');" class="wml_link"><gsmsg:write key="cmn.check.all" /></a>
              </div>
              </logic:greaterThan>
              <% } %>

              <logic:iterate id="fileData" name="wml012Form" property="wml012TempfileList" indexId="fileIdx">
                <% if (fileIdx.intValue() > 0 ) %><br>
                <% String checkName = "checkFile" + fileIdx.toString(); %>
                <% if (chkFileFlg) { %><input type="checkbox" name="checkFile" id="<%= checkName %>">&nbsp;<% } %>
                <label for="<%= checkName %>" id="<%= checkName %>_label"><bean:write name="fileData" property="fileName"/>&nbsp<bean:write name="fileData" property="fileSizeDsp"/></label>
               </logic:iterate>
               </logic:notEmpty>
             </td>
           </tr>
           <tr>
           <td colspan="2" class="td_type20" style="padding: 2px; 5px;">
           <pre id="wmlConfirmBodyArea" class="mail_body2"><bean:write name="wml012Form" property="wml012ViewBody" filter="false"/></pre>
           </td>
           </tr>
         </table>
      </form>
    </div>

</body>
</html:html>
