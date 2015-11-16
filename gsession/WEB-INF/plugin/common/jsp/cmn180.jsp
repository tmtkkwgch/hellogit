<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:form action="/common/cmn180">
<input type="hidden" name="CMD" value="setting">
<input type="hidden" name="cmn150backPage" value="<%= String.valueOf(jp.groupsession.v2.cmn.cmn150.Cmn150Form.BACKPAGE_MAIN) %>">
<logic:notEmpty name="cmn180Form" property="areaList">
<logic:iterate id="weatherData" name="cmn180Form" property="areaList">
  <table width="100%" cellpadding="0" cellspacing="0">
  <tbody>
  <tr style="border: solid 1px #333333;">
  <td class="double_header_7D91BD_left">
    <img src="../common/images/menu_icon_single_tenki.gif" class="img_bottom img_border img_menu_icon_single">&nbsp;<a href="#" onclick="window.open('<bean:write name="cmn180Form" property="cmn180WeekUrl" /><bean:write name="weatherData" property="areaId" />.html?date=<bean:write name="cmn180Form" property="cmn180Date" />');return false;">
    <a href="#" onclick="window.open('<bean:write name="cmn180Form" property="cmn180WeekUrl" /><bean:write name="weatherData" property="areaId" />.html?date=<bean:write name="cmn180Form" property="cmn180Date" />');return false;"><bean:write name="weatherData" property="areaName" /></a>
  </td>
  <td align="right" class="double_header_7D91BD_right">
    <input type="button" onClick="document.forms['cmn180Form'].submit();return false;" style="border:0px;color:#40a06b;font-size:10px;font-weight:bold;width:60px;height:17px;" class="btn_small_setting" value="<gsmsg:write key="cmn.setting" />">
  </td>
  </tr>
  <tr><td colspan="2" width="100%" class="td_type1" style="margin:0px!important; padding:0px;!important" style="display:inline;"><iframe hspace="0" vspace="0" style="margin:0px; padding:0px; width:100%; height:200px; display:inline!important;" frameborder="no" src="<bean:write name="cmn180Form" property="cmn180Url" /><bean:write name="weatherData" property="areaId" />.html?date=<bean:write name="cmn180Form" property="cmn180Date" />"></iframe></td></tr>
  </tbody>
  </table>
</logic:iterate>
<br>
</logic:notEmpty>
</html:form>