<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

  <IMG SRC="../common/images/spacer.gif" width="1px" height="5px" alt="">

  <table class="tl0" width="100%" cellpadding="0" cellspacing="0">
  <tr>
  <td width="30%" align="left">

   <logic:equal name="usr040Form" property="usr040webmailType" value="1">
     <div id="send_title"><gsmsg:write key="anp.send.dest" /></div>
   </logic:equal>
   <logic:notEqual name="usr040Form" property="usr040webmailType" value="1">
     <div id="send_title"><gsmsg:write key="cmn.from" /></div>
   </logic:notEqual>
   <div id="send_address">
   <table width="100%" class="tl0 table_td_border" width="100%" cellpadding="0" cellspacing="0">

   <% String addressKbn = "1"; %>
   <logic:notEmpty name="usr040Form" property="usr040AtskList">
   <logic:iterate id="atskMdl" name="usr040Form" property="usr040AtskList">

   <tr class="td_type1">
   <td nowrap>
     <span class="text_base">
       <bean:write name="atskMdl" property="usiSei" />&nbsp;&nbsp;<bean:write name="atskMdl" property="usiMei" />
     </span>
     <br>&lt;
     <a href="#">
       <span class="text_link">
       <logic:notEmpty name="atskMdl" property="usiMail1">
         <bean:write name="atskMdl" property="usiMail1" />
         <% addressKbn = "1"; %>
       </logic:notEmpty>
       <logic:notEmpty name="atskMdl" property="usiMail2">
         <bean:write name="atskMdl" property="usiMail2" />
         <% addressKbn = "2"; %>
       </logic:notEmpty>
       <logic:notEmpty name="atskMdl" property="usiMail3">
         <bean:write name="atskMdl" property="usiMail3" />
         <% addressKbn = "3"; %>
       </logic:notEmpty>
       </span>
     </a>&gt;
   &nbsp;[<a href="javascript:void(0);" onClick="return deleteSend(0, <bean:write name="atskMdl" property="usrSid" />, <%= addressKbn %>);"><span class="text_link3"><gsmsg:write key="cmn.delete" /></span></a>]
   </td>
   </tr>
   </logic:iterate>
   </logic:notEmpty>

   </table>
   </div>
  </td>

  <logic:notEmpty name="usr040Form" property="usr040CcList">
  <td width="30%" align="left">

   <div id="send_title">CC</div>
   <div id="send_address">

   <table width="100%" class="tl0 table_td_border" width="100%" cellpadding="0" cellspacing="0">

   <logic:iterate id="ccMdl" name="usr040Form" property="usr040CcList">

   <tr class="td_type1">
   <td nowrap><span class="text_base"><bean:write name="ccMdl" property="usiSei" />&nbsp;&nbsp;<bean:write name="ccMdl" property="usiMei" /></span><br>
   &lt;<a href="#"><span class="text_link">
   <% int mailDspFlg = 0; %>
   <logic:notEmpty name="ccMdl" property="usiMail1">
   <logic:equal name="ccMdl" property="usiMail1Kf" value="0">
     <bean:write name="ccMdl" property="usiMail1" />
     <% mailDspFlg = 1; %>
     <% addressKbn = "1"; %>
   </logic:equal>
   </logic:notEmpty>
   <logic:notEmpty name="ccMdl" property="usiMail2">
   <logic:equal name="ccMdl" property="usiMail2Kf" value="0">
   <% if(mailDspFlg == 0) { %>
     <bean:write name="ccMdl" property="usiMail2" />
     <% mailDspFlg = 1; %>
     <% addressKbn = "2"; %>
   <% } %>
   </logic:equal>
   </logic:notEmpty>
   <logic:notEmpty name="ccMdl" property="usiMail3">
   <logic:equal name="ccMdl" property="usiMail3Kf" value="0">
   <% if(mailDspFlg == 0) { %>
     <bean:write name="ccMdl" property="usiMail3" />
     <% addressKbn = "3"; %>
   <% } %>
   </logic:equal>
   </logic:notEmpty>
   </span></a>&gt;
   &nbsp;[<a href="javascript:void(0);" onClick="return deleteSend(1, <bean:write name="ccMdl" property="usrSid" />, <%= addressKbn %>);"><span class="text_link3"><gsmsg:write key="cmn.delete" /></span></a>]
   </td>
   </tr>

   </logic:iterate>

   </table>
   </div>

  </td>
  </logic:notEmpty>

  <logic:notEmpty name="usr040Form" property="usr040BccList">
  <td width="30%" align="left">

   <div id="send_title">BCC</div>
   <div id="send_address">

   <table width="100%" class="tl0 table_td_border" width="100%" cellpadding="0" cellspacing="0">

   <logic:iterate id="bccMdl" name="usr040Form" property="usr040BccList">

   <tr class="td_type1">
   <td nowrap><span class="text_base"><bean:write name="bccMdl" property="usiSei" />&nbsp;&nbsp;<bean:write name="bccMdl" property="usiMei" /></span>
   <br>&lt;<a href="#"><span class="text_link">
   <% int mailDspFlg = 0; %>
   <logic:notEmpty name="bccMdl" property="usiMail1">
   <logic:equal name="bccMdl" property="usiMail1Kf" value="0">
     <bean:write name="bccMdl" property="usiMail1" />
     <% mailDspFlg = 1; %>
     <% addressKbn = "1"; %>
   </logic:equal>
   </logic:notEmpty>
   <logic:notEmpty name="bccMdl" property="usiMail2">
   <logic:equal name="bccMdl" property="usiMail2Kf" value="0">
   <% if(mailDspFlg == 0) { %>
     <bean:write name="bccMdl" property="usiMail2" />
     <% mailDspFlg = 1; %>
     <% addressKbn = "2"; %>
   <% } %>
   </logic:equal>
   </logic:notEmpty>
   <logic:notEmpty name="bccMdl" property="usiMail3">
   <logic:equal name="bccMdl" property="usiMail3Kf" value="0">
   <% if(mailDspFlg == 0) { %>
     <bean:write name="bccMdl" property="usiMail3" />
     <% addressKbn = "3"; %>
   <% } %>
   </logic:equal>
   </logic:notEmpty>
   </span> </a>&gt;
   &nbsp;[<a href="javascript:void(0);" onClick="return deleteSend(2, <bean:write name="bccMdl" property="usrSid" />, <%= addressKbn %>);"><span class="text_link3"><gsmsg:write key="cmn.delete" /></span></a>]
   </td>
   </tr>

   </logic:iterate>

   </table>
   </div>

  </td>
  </logic:notEmpty>

  </tr>
  </table>
