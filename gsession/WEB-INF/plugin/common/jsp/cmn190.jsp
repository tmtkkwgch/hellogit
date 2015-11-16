<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:form action="/common/cmn190">
<input type="hidden" name="CMD" value="setting">
<input type="hidden" name="cmn150backPage" value="<%= String.valueOf(jp.groupsession.v2.cmn.cmn150.Cmn150Form.BACKPAGE_MAIN) %>">

<table width="100%" cellpadding="0" cellspacing="0">
  <tbody>
  <tr style="border: solid 1px #333333;">
  <td class="double_header_7D91BD_left">
    <a href="#" onclick="window.open('<bean:write name="cmn190Form" property="cmn190dailyUrl" /><bean:write name="cmn190Form" property="cmn190dateStr" />.html');return false;"><gsmsg:write key="main.src.man001.2" /></a>
  </td>
  <td align="right" class="double_header_7D91BD_right">
    <input type="button" onClick="document.forms['cmn190Form'].submit();return false;" style="border:0px;color:#40a06b;font-size:10px;font-weight:bold;width:60px;height:17px;" class="btn_small_setting" value="<gsmsg:write key="cmn.setting" />">
  </td>
  </tr>
  <tr><td colspan="2" width="100%" class="td_type1" style="margin:0px!important; padding:0px;!important" style="display:inline;"><iframe hspace="0" vspace="0" style="margin:0px; padding:0px; width:100%; height:140px; display:inline!important;" frameborder="no" src="<bean:write name="cmn190Form" property="cmn190gadgetUrl" /><bean:write name="cmn190Form" property="cmn190dateStr" />.html"></iframe></td></tr>
  </tbody>
</table>
</html:form>