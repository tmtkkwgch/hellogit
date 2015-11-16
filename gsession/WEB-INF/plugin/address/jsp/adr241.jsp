<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<logic:notEmpty name="adr241Form" property="addressList">
<% String[] tdClass = {"td_type1", "td_type_usr"}; %>
<table width="100%" class="tl0" border="0" cellpadding="0">
<logic:iterate id="addressModel" name="adr241Form" property="addressList" indexId="idx">
<tr>
  <td width="5%" class="<%= tdClass[idx.intValue() % 2] %>" align="right">
  <input type="checkbox" name="adr240Address" value="<bean:write name="addressModel" property="addressSid" />">
  </td>
  <td width="95%" class="<%= tdClass[idx.intValue() % 2] %>" onclick="clickAddressName('<bean:write name="addressModel" property="addressSid" />');">
  <input type="hidden" id="adrName_<bean:write name="addressModel" property="addressSid" />" value="<bean:write name="addressModel" property="addressName" />">
  <bean:write name="addressModel" property="addressName" />
  </td>
</tr>
</logic:iterate>
</table>
</logic:notEmpty>
