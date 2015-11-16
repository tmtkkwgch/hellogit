<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../portal/css/portal.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-ui-1.8.16/jquery-1.6.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../common/js/jquery-ui-1.8.16/jquery-ui-1.8.16.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.core.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.widget.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.mouse.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.sortable.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../portal/js/ptl040.js?<%= GSConst.VERSION_PARAM %>"></script>

<title>[Group Session] <gsmsg:write key="ptl.ptl040.1" /></title>
</head>


<!-- body -->
<body class="body_03" onload="initChgArea();" onunload="closePortalPreview();portalPopupClose();">
<html:form action="/portal/ptl040">

<input type="hidden" name="CMD" value="">
<html:hidden property="ptlPortalSid" />
<html:hidden property="ptl030sortPortal" />
<html:hidden property="ptl040PortalItemId" />
<html:hidden property="ptlCmdMode" />

<html:hidden property="ptl040areaTop" />
<html:hidden property="ptl040areaBottom" />
<html:hidden property="ptl040areaLeft" />
<html:hidden property="ptl040areaCenter" />
<html:hidden property="ptl040areaRight" />

<%@ include file="/WEB-INF/plugin/portal/jsp/ptl_hiddenParams.jsp" %>
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">

<tr>

<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">

  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text">[ <gsmsg:write key="ptl.ptl040.1" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.edit" />" class="btn_edit_n1" onClick="buttonPush2('ptl040editPortal');">
      <input type="button" value="<gsmsg:write key="ptl.5" />" class="btn_layout_n1" onClick="buttonPush2('ptl040editLayout');">
      <input type="button" value="<gsmsg:write key="ptl.6" />" class="btn_preview_n1" onClick="openPortalPreview('<bean:write name="ptl040Form" property="ptlPortalSid" />')">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush2('ptl040back');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

  </td>
  </tr>
  </table>



  <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

  <table width="100%" cellpadding="5" cellspacing="0" class="tl0">
  <tr>
  <td width="10%" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="ptl.4" /></span></td>
  <td class="td_type1"><bean:write name="ptl040Form" property="ptl040portalName" /></td>
  </tr>
  </table>

  <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

  <div align="right" valign="top">
    <input type="button" value="<gsmsg:write key="ptl.3" />" class="btn_portlet_n1" onClick="openPortalPopup(1);">
    <input type="button" value="<gsmsg:write key="cmn.plugin" />" class="btn_gs_n1" onClick="openPortalPopup(0);">
    <br><img src="../common/images/spacer.gif" width="1px" height="7px" border="0">
  </div>

  <!-- 各プラグイン情報レイアウト -->

  <% String[] tdColors = new String[] {"tl4 portlet_area2", "tl4 portlet_area2_closed"}; %>
  <bean:define id="tdColor" value="" />

  <table width="100%" cellspacing="0" cellpadding="0">
  <!-- 上 -->
  <logic:equal name="ptl040Form" property="ptl040areaTop" value="0">
  <tr>
  <td width="100%" height="100" id="mainScreenListTop" class="portal_area portal_area_top">

    <logic:notEmpty name="ptl040Form" property="ptl040topList">
    <logic:iterate id="topModel" name="ptl040Form" property="ptl040topList" indexId="idxtop">

      <logic:equal name="topModel" property="ptpView" value="0"><% tdColor = tdColors[0]; %></logic:equal>
      <logic:equal name="topModel" property="ptpView" value="1"><% tdColor = tdColors[1]; %></logic:equal>


    <div class="portlet" id="<bean:write name='topModel' property='ptpItemid' />">
      <table class="tl0" width="100%" cellspacing="0" cellpadding="0">
      <tr><td align="left" class="header_7D91BD_right">
        <a href="javascript:void(0);"><bean:write name="topModel" property="dspName" /></a>
      </td></tr>
      <tr>
      <td class="<%= tdColor %>" id="portlet_area<bean:write name='topModel' property='ptpItemid' />">
        <logic:equal name="topModel" property="ptpView" value="0">
        <input type="radio" name="openKbn<bean:write name='topModel' property='num' />" value="0" id="openKbn0_<bean:write name='topModel' property='num' />" onClick="changeOpenKbn(<bean:write name='topModel' property='ptpItemid'/>, 0);" checked="checked"><label for="openKbn0_<bean:write name='topModel' property='num' />" class="text_base2"><gsmsg:write key="cmn.show" /></label>
        <input type="radio" name="openKbn<bean:write name='topModel' property='num' />" value="1" id="openKbn1_<bean:write name='topModel' property='num' />" onClick="changeOpenKbn(<bean:write name='topModel' property='ptpItemid'/>, 1);"><label for="openKbn1_<bean:write name='topModel' property='num' />" class="text_base2"><gsmsg:write key="cmn.hide" /></label>
        </logic:equal>
        <logic:equal name="topModel" property="ptpView" value="1">
        <input type="radio" name="openKbn<bean:write name='topModel' property='num' />" value="0" id="openKbn0_<bean:write name='topModel' property='num' />" onClick="changeOpenKbn(<bean:write name='topModel' property='ptpItemid'/>, 0);"><label for="openKbn0_<bean:write name='topModel' property='num' />" class="text_base2"><gsmsg:write key="cmn.show" /></label>
        <input type="radio" name="openKbn<bean:write name='topModel' property='num' />" value="1" id="openKbn1_<bean:write name='topModel' property='num' />" onClick="changeOpenKbn(<bean:write name='topModel' property='ptpItemid'/>, 1);" checked="checked"><label for="openKbn1_<bean:write name='topModel' property='num' />" class="text_base2"><gsmsg:write key="cmn.hide" /></label>
        </logic:equal>

        <logic:notEqual name="topModel" property="ptpType" value="3">
        <br>&nbsp;<img src="../common/images/delete.gif" width="12" height="12">&nbsp;<a href="javascript:void(0);" onClick="return delPortlet('<bean:write name='topModel' property='ptpItemid' />', '<bean:write name='topModel' property='dspName' />');"><span class="text_link2"><gsmsg:write key="cmn.delete" /></span></a>
        </logic:notEqual>

      </td>
      </tr>
      </table>
    </div>

    </logic:iterate>
    </logic:notEmpty>

  </td>
  </tr>
  </logic:equal>
  <tr>
  <td height="20" align="center" id="portal_space_Top">
  </td>
  </tr>

  <tr>
  <td>
    <table width="100%">
    <tr>

    <!-- 左-->
    <logic:equal name="ptl040Form" property="ptl040areaLeft" value="0">
    <td width="33%" valign="top" id="mainScreenListLeft" class="portal_area portal_area_left">

    <logic:notEmpty name="ptl040Form" property="ptl040leftList">
    <logic:iterate id="leftModel" name="ptl040Form" property="ptl040leftList" indexId="idxleft">

      <logic:equal name="leftModel" property="ptpView" value="0"><% tdColor = tdColors[0]; %></logic:equal>
      <logic:equal name="leftModel" property="ptpView" value="1"><% tdColor = tdColors[1]; %></logic:equal>

      <div class="portlet" id="<bean:write name='leftModel' property='ptpItemid' />">
        <table class="tl0" width="100%" cellspacing="0" cellpadding="0">
        <tr><td align="left" class="header_7D91BD_right">
          <a href="javascript:void(0);"><bean:write name="leftModel" property="dspName" /></a>
        </td></tr>
        <tr>
        <td class="<%= tdColor %>" id="portlet_area<bean:write name='leftModel' property='ptpItemid' />">
          <logic:equal name="leftModel" property="ptpView" value="0">
          <input type="radio" name="openKbn<bean:write name='leftModel' property='num' />" value="0" id="openKbn0_<bean:write name='leftModel' property='num' />" onClick="changeOpenKbn(<bean:write name='leftModel' property='ptpItemid'/>, 0);" checked="checked"><label for="openKbn0_<bean:write name='leftModel' property='num' />" class="text_base2"><gsmsg:write key="cmn.show" /></label>
          <input type="radio" name="openKbn<bean:write name='leftModel' property='num' />" value="1" id="openKbn1_<bean:write name='leftModel' property='num' />" onClick="changeOpenKbn(<bean:write name='leftModel' property='ptpItemid'/>, 1);"><label for="openKbn1_<bean:write name='leftModel' property='num' />" class="text_base2"><gsmsg:write key="cmn.hide" /></label>
          </logic:equal>
          <logic:equal name="leftModel" property="ptpView" value="1">
          <input type="radio" name="openKbn<bean:write name='leftModel' property='num' />" value="0" id="openKbn0_<bean:write name='leftModel' property='num' />" onClick="changeOpenKbn(<bean:write name='leftModel' property='ptpItemid'/>, 0);"><label for="openKbn0_<bean:write name='leftModel' property='num' />" class="text_base2"><gsmsg:write key="cmn.show" /></label>
          <input type="radio" name="openKbn<bean:write name='leftModel' property='num' />" value="1" id="openKbn1_<bean:write name='leftModel' property='num' />" onClick="changeOpenKbn(<bean:write name='leftModel' property='ptpItemid'/>, 1);" checked="checked"><label for="openKbn1_<bean:write name='leftModel' property='num' />" class="text_base2"><gsmsg:write key="cmn.hide" /></label>
          </logic:equal>
          <logic:notEqual name="leftModel" property="ptpType" value="3">
          <br>&nbsp;<img src="../common/images/delete.gif" width="12" height="12">&nbsp;<a href="javascript:void(0);" onClick="return delPortlet('<bean:write name='leftModel' property='ptpItemid' />', '<bean:write name='leftModel' property='dspName' />');"><span class="text_link2"><gsmsg:write key="cmn.delete" /></span></a>
          </logic:notEqual>
        </td>
        </tr>
        </table>
        <img src="../common/images/spacer.gif" width="1px" height="5px" border="0">
      </div>

      </logic:iterate>
      </logic:notEmpty>

    </td>
    </logic:equal>

    <td width="0%" id="left_space">
      <img src="../common/images/spacer.gif" width="10px" height="1px" border="0">
    </td>

    <!-- 中央 -->
    <logic:equal name="ptl040Form" property="ptl040areaCenter" value="0">
    <td width="33%" valign="top" id="mainScreenListCenter" class="portal_area portal_area_center">

    <logic:notEmpty name="ptl040Form" property="ptl040centerList">
    <logic:iterate id="centerModel" name="ptl040Form" property="ptl040centerList" indexId="idxcenter">

      <logic:equal name="centerModel" property="ptpView" value="0"><% tdColor = tdColors[0]; %></logic:equal>
      <logic:equal name="centerModel" property="ptpView" value="1"><% tdColor = tdColors[1]; %></logic:equal>

      <div class="portlet" id="<bean:write name='centerModel' property='ptpItemid' />">
        <table class="tl0" width="100%" cellspacing="0" cellpadding="0">
        <tr><td align="left" class="header_7D91BD_right">
          <a href="javascript:void(0);"><bean:write name="centerModel" property="dspName" /></a>
        </td></tr>
        <tr>
        <td class="<%= tdColor %>" id="portlet_area<bean:write name='centerModel' property='ptpItemid' />">
          <logic:equal name="centerModel" property="ptpView" value="0">
          <input type="radio" name="openKbn<bean:write name='centerModel' property='num' />" value="0" id="openKbn0_<bean:write name='centerModel' property='num' />" onClick="changeOpenKbn(<bean:write name='centerModel' property='ptpItemid'/>, 0);" checked="checked"><label for="openKbn0_<bean:write name='centerModel' property='num' />" class="text_base2"><gsmsg:write key="cmn.show" /></label>
          <input type="radio" name="openKbn<bean:write name='centerModel' property='num' />" value="1" id="openKbn1_<bean:write name='centerModel' property='num' />" onClick="changeOpenKbn(<bean:write name='centerModel' property='ptpItemid'/>, 1);"><label for="openKbn1_<bean:write name='centerModel' property='num' />" class="text_base2"><gsmsg:write key="cmn.hide" /></label>
          </logic:equal>
          <logic:equal name="centerModel" property="ptpView" value="1">
          <input type="radio" name="openKbn<bean:write name='centerModel' property='num' />" value="0" id="openKbn0_<bean:write name='centerModel' property='num' />" onClick="changeOpenKbn(<bean:write name='centerModel' property='ptpItemid'/>, 0);"><label for="openKbn0_<bean:write name='centerModel' property='num' />" class="text_base2"><gsmsg:write key="cmn.show" /></label>
          <input type="radio" name="openKbn<bean:write name='centerModel' property='num' />" value="1" id="openKbn1_<bean:write name='centerModel' property='num' />" onClick="changeOpenKbn(<bean:write name='centerModel' property='ptpItemid'/>, 1);" checked="checked"><label for="openKbn1_<bean:write name='centerModel' property='num' />" class="text_base2"><gsmsg:write key="cmn.hide" /></label>
          </logic:equal>
          <logic:notEqual name="centerModel" property="ptpType" value="3">
          <br>&nbsp;<img src="../common/images/delete.gif" width="12" height="12">&nbsp;<a href="javascript:void(0);" onClick="return delPortlet('<bean:write name='centerModel' property='ptpItemid' />', '<bean:write name='centerModel' property='dspName' />');"><span class="text_link2"><gsmsg:write key="cmn.delete" /></span></a>
          </logic:notEqual>
        </td>
        </tr>
        </table>
        <img src="../common/images/spacer.gif" width="1px" height="5px" border="0">
      </div>

    </logic:iterate>
    </logic:notEmpty>

    </td>
    </logic:equal>

    <td width="0%" id="right_space">
      <img src="../common/images/spacer.gif" width="10px" height="1px" border="0">
    </td>

    <!-- 右 -->
    <logic:equal name="ptl040Form" property="ptl040areaRight" value="0">
    <td width="33%" valign="top" id="mainScreenListRight" class="portal_area portal_area_right">

      <logic:notEmpty name="ptl040Form" property="ptl040rightList">
      <logic:iterate id="rightModel" name="ptl040Form" property="ptl040rightList" indexId="idxright">

        <logic:equal name="rightModel" property="ptpView" value="0"><% tdColor = tdColors[0]; %></logic:equal>
        <logic:equal name="rightModel" property="ptpView" value="1"><% tdColor = tdColors[1]; %></logic:equal>

      <div class="portlet" id="<bean:write name='rightModel' property='ptpItemid' />">
        <table class="tl0" width="100%" cellspacing="0" cellpadding="0">
        <tr><td align="left" class="header_7D91BD_right">
          <a href="javascript:void(0);"><bean:write name="rightModel" property="dspName" /></a>
        </td></tr>
        <tr>
        <td class="<%= tdColor %>" id="portlet_area<bean:write name='rightModel' property='ptpItemid' />">
          <logic:equal name="rightModel" property="ptpView" value="0">
          <input type="radio" name="openKbn<bean:write name='rightModel' property='num' />" value="0" id="openKbn0_<bean:write name='rightModel' property='num' />" onClick="changeOpenKbn(<bean:write name='rightModel' property='ptpItemid'/>, 0);" checked="checked"><label for="openKbn0_<bean:write name='rightModel' property='num' />" class="text_base2"><gsmsg:write key="cmn.show" /></label>
          <input type="radio" name="openKbn<bean:write name='rightModel' property='num' />" value="1" id="openKbn1_<bean:write name='rightModel' property='num' />" onClick="changeOpenKbn(<bean:write name='rightModel' property='ptpItemid'/>, 1);"><label for="openKbn1_<bean:write name='rightModel' property='num' />" class="text_base2"><gsmsg:write key="cmn.hide" /></label>
          </logic:equal>
          <logic:equal name="rightModel" property="ptpView" value="1">
          <input type="radio" name="openKbn<bean:write name='rightModel' property='num' />" value="0" id="openKbn0_<bean:write name='rightModel' property='num' />" onClick="changeOpenKbn(<bean:write name='rightModel' property='ptpItemid'/>, 0);"><label for="openKbn0_<bean:write name='rightModel' property='num' />" class="text_base2"><gsmsg:write key="cmn.show" /></label>
          <input type="radio" name="openKbn<bean:write name='rightModel' property='num' />" value="1" id="openKbn1_<bean:write name='rightModel' property='num' />" onClick="changeOpenKbn(<bean:write name='rightModel' property='ptpItemid'/>, 1);" checked="checked"><label for="openKbn1_<bean:write name='rightModel' property='num' />" class="text_base2"><gsmsg:write key="cmn.hide" /></label>
          </logic:equal>
          <logic:notEqual name="rightModel" property="ptpType" value="3">
          <br>&nbsp;<img src="../common/images/delete.gif" width="12" height="12">&nbsp;<a href="javascript:void(0);" onClick="return delPortlet('<bean:write name='rightModel' property='ptpItemid' />', '<bean:write name='rightModel' property='dspName' />');"><span class="text_link2"><gsmsg:write key="cmn.delete" /></span></a>
          </logic:notEqual>
        </td>
        </tr>
        </table>
        <img src="../common/images/spacer.gif" width="1px" height="5px" border="0">
      </div>

      </logic:iterate>
      </logic:notEmpty>

    </td>
    </logic:equal>
    </tr>
    </table>

  </td>
  </tr>

  <!-- 下 -->
  <tr>
  <td height="20" align="center" id="portal_space_Bottom">
    <img src="../common/images/spacer.gif" width="1px" height="20px" border="0">
  </td>
  </tr>

  <logic:equal name="ptl040Form" property="ptl040areaBottom" value="0">
  <tr>
  <td width="100%" height="100" align="center" valign="top" id="mainScreenListBottom" class="portal_area portal_area_bottom">

    <logic:notEmpty name="ptl040Form" property="ptl040bottomList">
    <logic:iterate id="bottomModel" name="ptl040Form" property="ptl040bottomList" indexId="idxbottom">

      <logic:equal name="bottomModel" property="ptpView" value="0"><% tdColor = tdColors[0]; %></logic:equal>
      <logic:equal name="bottomModel" property="ptpView" value="1"><% tdColor = tdColors[1]; %></logic:equal>

    <div class="portlet" id="<bean:write name='bottomModel' property='ptpItemid' />">
      <table class="tl0" width="100%" cellspacing="0" cellpadding="0">
      <tr><td align="left" class="header_7D91BD_right">
        <a href="javascript:void(0);"><bean:write name="bottomModel" property="dspName" /></a>
      </td></tr>
      <tr>
      <td class="<%= tdColor %>" id="portlet_area<bean:write name='bottomModel' property='ptpItemid' />">
          <logic:equal name="bottomModel" property="ptpView" value="0">
          <input type="radio" name="openKbn<bean:write name='bottomModel' property='num' />" value="0" id="openKbn0_<bean:write name='bottomModel' property='num' />" onClick="changeOpenKbn(<bean:write name='bottomModel' property='ptpItemid'/>, 0);" checked="checked"><label for="openKbn0_<bean:write name='bottomModel' property='num' />" class="text_base2"><gsmsg:write key="cmn.show" /></label>
          <input type="radio" name="openKbn<bean:write name='bottomModel' property='num' />" value="1" id="openKbn1_<bean:write name='bottomModel' property='num' />" onClick="changeOpenKbn(<bean:write name='bottomModel' property='ptpItemid'/>, 1);"><label for="openKbn1_<bean:write name='bottomModel' property='num' />" class="text_base2"><gsmsg:write key="cmn.hide" /></label>
          </logic:equal>
          <logic:equal name="bottomModel" property="ptpView" value="1">
          <input type="radio" name="openKbn<bean:write name='bottomModel' property='num' />" value="0" id="openKbn0_<bean:write name='bottomModel' property='num' />" onClick="changeOpenKbn(<bean:write name='bottomModel' property='ptpItemid'/>, 0);"><label for="openKbn0_<bean:write name='bottomModel' property='num' />" class="text_base2"><gsmsg:write key="cmn.show" /></label>
          <input type="radio" name="openKbn<bean:write name='bottomModel' property='num' />" value="1" id="openKbn1_<bean:write name='bottomModel' property='num' />" onClick="changeOpenKbn(<bean:write name='bottomModel' property='ptpItemid'/>, 1);" checked="checked"><label for="openKbn1_<bean:write name='bottomModel' property='num' />" class="text_base2"><gsmsg:write key="cmn.hide" /></label>
          </logic:equal>
        <logic:notEqual name="bottomModel" property="ptpType" value="3">
        <br>&nbsp;<img src="../common/images/delete.gif" width="12" height="12">&nbsp;<a href="javascript:void(0);" onClick="return delPortlet('<bean:write name='bottomModel' property='ptpItemid' />', '<bean:write name='bottomModel' property='dspName' />');"><span class="text_link2"><gsmsg:write key="cmn.delete" /></span></a>
        </logic:notEqual>
      </td>
      </tr>
      </table>
    </div>

    </logic:iterate>
    </logic:notEmpty>

  </td>
  </tr>
  </logic:equal>

  </table>

</td>
</tr>

</table>

</html:form>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</body>

</html:html>
