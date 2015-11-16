<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


  <table class="tl0" width="100%" cellpadding="0" cellspacing="0">
  <tr>
  <td width="30%" align="left">

   <div id="send_title"><gsmsg:write key="cmn.from" /></div>
   <div id="send_address">
   <table class="tl0 table_td_border" width="100%" cellpadding="0" cellspacing="0">

   <% String addressKbn = "1"; %>
   <% String[] detailClass = {"td_type1", "td_type_usr"}; %>
   <logic:notEmpty name="adr010Form" property="adr010AtskList">
   <logic:iterate id="atskMdl" name="adr010Form" property="adr010AtskList" indexId="idx">

   <tr class="<%= detailClass[idx.intValue() % 2] %>">
   <td nowrap><span class="text_base"><bean:write name="atskMdl" property="adrSei" />&nbsp;&nbsp;<bean:write name="atskMdl" property="adrMei" /></span>
   <br>&lt;<a href="#">
     <span class="text_link">
     <logic:notEmpty name="atskMdl" property="adrMail1">
       <bean:write name="atskMdl" property="adrMail1" />
       <% addressKbn = "1"; %>
     </logic:notEmpty>
     <logic:notEmpty name="atskMdl" property="adrMail2">
       <bean:write name="atskMdl" property="adrMail2" />
       <% addressKbn = "2"; %>
     </logic:notEmpty>
     <logic:notEmpty name="atskMdl" property="adrMail3">
       <bean:write name="atskMdl" property="adrMail3" />
       <% addressKbn = "3"; %>
     </logic:notEmpty>

     </span></a>&gt;
   &nbsp;[<a href="javascript:void(0);" onClick="return deleteSend(0, <bean:write name="atskMdl" property="adrSid" />, <%= addressKbn %>);"><span class="text_link3"><gsmsg:write key="cmn.delete" /></span></a>]
   </td>
   </tr>
   </logic:iterate>
   </logic:notEmpty>

   </table>
   </div>
  </td>

  <logic:notEmpty name="adr010Form" property="adr010CcList">
  <td width="30%" align="left">

   <div id="send_title">CC</div>
   <div id="send_address">

   <table class="tl0 table_td_border" width="100%" cellpadding="0" cellspacing="0">
   <logic:iterate id="ccMdl" name="adr010Form" property="adr010CcList" indexId="idx2">
   <tr class="<%= detailClass[idx2.intValue() % 2] %>">
   <td style="border-right:0px" nowrap><span class="text_base"><bean:write name="ccMdl" property="adrSei" />&nbsp;&nbsp;<bean:write name="ccMdl" property="adrMei" /></span>
   <br>&lt;<a href="#"><span class="text_link">
     <logic:notEmpty name="ccMdl" property="adrMail1">
       <bean:write name="ccMdl" property="adrMail1" />
       <% addressKbn = "1"; %>
     </logic:notEmpty>
     <logic:notEmpty name="ccMdl" property="adrMail2">
       <bean:write name="ccMdl" property="adrMail2" />
       <% addressKbn = "2"; %>
     </logic:notEmpty>
     <logic:notEmpty name="ccMdl" property="adrMail3">
       <bean:write name="ccMdl" property="adrMail3" />
       <% addressKbn = "3"; %>
     </logic:notEmpty>
   </span></a>&gt;
   &nbsp;[<a href="javascript:void(0);" onClick="return deleteSend(1, <bean:write name="ccMdl" property="adrSid" />, <%= addressKbn %>);"><span class="text_link3"><gsmsg:write key="cmn.delete" /></span></a>]
   </td>
   </tr>

   </logic:iterate>

   </table>
   </div>

  </td>
  </logic:notEmpty>

  <logic:notEmpty name="adr010Form" property="adr010BccList">
  <td width="30%" align="left">

   <div id="send_title">BCC</div>
   <div id="send_address">

   <table class="tl0 table_td_border" width="100%" cellpadding="0" cellspacing="0">
   <logic:iterate id="bccMdl" name="adr010Form" property="adr010BccList" indexId="idx3">
   <tr class="<%= detailClass[idx3.intValue() % 2] %>">
   <td style="border-right:0px" nowrap><span class="text_base"><bean:write name="bccMdl" property="adrSei" />&nbsp;&nbsp;<bean:write name="bccMdl" property="adrMei" /></span>
   <br>&lt;<a href="#"><span class="text_link">
     <logic:notEmpty name="bccMdl" property="adrMail1">
       <bean:write name="bccMdl" property="adrMail1" />
       <% addressKbn = "1"; %>
     </logic:notEmpty>
     <logic:notEmpty name="bccMdl" property="adrMail2">
       <bean:write name="bccMdl" property="adrMail2" />
       <% addressKbn = "2"; %>
     </logic:notEmpty>
     <logic:notEmpty name="bccMdl" property="adrMail3">
       <bean:write name="bccMdl" property="adrMail3" />
       <% addressKbn = "3"; %>
     </logic:notEmpty>
   </span></a>&gt;
   &nbsp;[<a href="javascript:void(0);" onClick="return deleteSend(2, <bean:write name="bccMdl" property="adrSid" />, <%= addressKbn %>);"><span class="text_link3"><gsmsg:write key="cmn.delete" /></span></a>]
   </td>
     <td style="border-left:0px"></td>
   </tr>

   </logic:iterate>

   </table>
   </div>

  </td>
  </logic:notEmpty>

  </tr>
  </table>
