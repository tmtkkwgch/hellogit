<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>

    <!--<gsmsg:write key="cmn.information" />-->
    <logic:empty name="ptl070Form" property="infoMsgs">

      <table cellpadding="0" cellspacing="0" width="100%" class="tl0">
      <tr>
      <td align="left">
        <img src="../main/images/menu_icon_single_info.gif" class="img_bottom img_border img_menu_icon_single" alt="<gsmsg:write key="cmn.information" />">&nbsp;<a href="javascript:buttonPush2('infoSetting');" style="font-size: 90%; font-weight: bold; text-decoration: underline;"><gsmsg:write key="main.4" /></a>
      </td>
      </tr>
      </table>
      <div><img src="../common/images/damy.gif" height="5px" width="1px" border="0" alt="<gsmsg:write key="cmn.dummy" />"></div>

    </logic:empty>


    <logic:notEmpty name="ptl070Form" property="infoMsgs">
    <table width="100%" class="tl0" cellspacing="0" cellpadding="0">

    <tr>
    <td align="left" class="header_7D91BD_left" colspan="1">
      <table cellpadding="0" cellspacing="0" width="100%" class="tl0">
      <tr>
      <td align="left">
        <img src="../main/images/menu_icon_single_info.gif" class="img_bottom img_border img_menu_icon_single" alt="<gsmsg:write key="cmn.information" />"><a><gsmsg:write key="cmn.information" /></a>
      </td>
      <td align="right">
        <a href="#">
        <input type="button" onClick="buttonPush2('infoSetting');" style="border:0px;color:#40a06b;font-size:10px;font-weight:bold;width:60px;height:17px;" class="btn_small_setting" value="<gsmsg:write key="cmn.setting" />">
        </a>
        &nbsp;

      </td>
      </tr>
      </table>
    </td>
    </tr>

    <logic:iterate id="msg" name="ptl070Form" property="infoMsgs" scope="request" indexId="idx">
    <tr>
    <td class="td_type1">
      <logic:notEmpty name="msg" property="icon">
      <img src="<bean:write name="msg" property="icon" />" class="img_bottom" alt="<bean:write name="msg" property="message" />">
      </logic:notEmpty>

      <logic:equal name="msg" property="popupDsp" value="false">
      <logic:equal name="msg" property="htmlEscape" value="true">
      <a href="<bean:write name="msg" property="linkUrl" />"><span class="text_r2"><bean:write name="msg" property="message" /></span></a>
      </logic:equal>
      <logic:notEqual name="msg" property="htmlEscape" value="true">
      <a href="<bean:write name="msg" property="linkUrl" />"><span class="text_r2"><bean:write name="msg" property="message" filter="false" /></span></a>
      </logic:notEqual>
      </logic:equal>

      <logic:notEqual name="msg" property="popupDsp" value="false">
      <logic:equal name="msg" property="htmlEscape" value="true">
      <a href="javascript:void(0);" onClick="<bean:write name="msg" property="linkUrl" />"><span class="text_r2"><bean:write name="msg" property="message" /></span></a>
      </logic:equal>
      <logic:notEqual name="msg" property="htmlEscape" value="true">
      <a href="javascript:void(0);" onClick="<bean:write name="msg" property="linkUrl" />"><span class="text_r2"><bean:write name="msg" property="message" filter="false" /></span></a>
      </logic:notEqual>
      </logic:notEqual>

    </td>
    </tr>
    </logic:iterate>

    </table>
    <div><img src="../common/images/damy.gif" height="5px" width="1px" border="0" alt="<gsmsg:write key="cmn.dummy" />"></div>
    </logic:notEmpty>
