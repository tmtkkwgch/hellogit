<%@ page pageEncoding="Windows-31J" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[Group Session] <gsmsg:write key="ntp.1" /></title>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../nippou/js/ntp.js?<%= GSConst.VERSION_PARAM %>'></script>


<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../nippou/css/nippou.css?<%= GSConst.VERSION_PARAM %>" type="text/css">

</head>

<body class="body_03">



<html:form action="/nippou/ntp120">

<input type="hidden" name="CMD" value="">
<html:hidden property="dspMod" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--@BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td width="0%"><img src="../nippou/images/header_nippou_02.gif" border="0" alt="<gsmsg:write key="ntp.1" />"></td>
  <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="ntp.1" /></span></td>
  <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="ntp.14" /> ]</td>
  <td width="100%" class="header_white_bg">

  <%-- input type="button" value="<gsmsg:write key="ntp.11" /><gsmsg:write key="cmn.search" />" class="btn_search_n1" onClick="buttonPush2('anken');" --%>

    <logic:equal name="ntp120Form" property="adminKbn" value="1">
      <input type="button" name="btn_admin_tool" class="btn_setting_admin_n1" value="<gsmsg:write key="cmn.admin.setting" />" onClick="buttonPush2('ktool');">
    </logic:equal>
    <input type="button" name="btn_user_tool" class="btn_setting_n1" value="<gsmsg:write key="cmn.preferences2" />" onClick="buttonPush2('pset');">

  </td>
  <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="ntp.1" />"></td>
  </tr>
  </table>

<img src="../schedule/images/spacer.gif" width="1px" height="5px" border="0">


<table cellpadding="1px" width="100%" height="100%">
<tr>
<td height="100%">

    <table cellpadding="0" cellspacing="0" class="menu_table" height="100%">

      <tr height="100%">

        <td valign="top" width="16%" height="100%" class="menu_space_area_both">
          <table width="100%" height="100%" cellpadding="0" cellspacing="0">

            <tr class="menu_not_select_tr" class="" onClick="buttonPush2('nippou');">
              <td class="menu_not_select_icon">
                <img src="../nippou/images/menu_icon_single.gif" alt="<gsmsg:write key="ntp.1" />" />
              </td>
              <td class="menu_not_select_text" colspan="2" width="82%" nowrap>
                <span class="timeline_menu"><gsmsg:write key="ntp.1" /></span>
              </td>
            </tr>

            <tr class="menu_not_select_tr" onClick="buttonPush2('anken');">
              <td class="menu_not_select_icon">
                <img src="../nippou/images/menu_icon_anken.gif" alt="<gsmsg:write key="ntp.11" />" />
              </td>
              <td class="menu_not_select_text" colspan="2" width="82%" nowrap>
                <span class="timeline_menu"><gsmsg:write key="ntp.11" /></span>
              </td>
            </tr>

            <tr class="menu_not_select_tr" onClick="buttonPush('target');">
              <td class="menu_not_select_icon">
                <img src="../nippou/images/menu_icon_target.gif" alt="<gsmsg:write key="ntp.12" />" />
              </td>
              <td class="menu_not_select_text" colspan="2" width="82%" nowrap>
                <span class="timeline_menu"><gsmsg:write key="ntp.12" /></span>
              </td>
            </tr>

            <tr class="menu_not_select_tr" onClick="buttonPush('bunseki');">
              <td class="menu_not_select_icon">
                <img src="../nippou/images/menu_icon_bunseki.gif" alt="<gsmsg:write key="ntp.13" />" />
              </td>
              <td class="menu_not_select_text" colspan="2" width="82%" nowrap>
                <span class="timeline_menu"><gsmsg:write key="ntp.13" /></span>
              </td>
            </tr>

            <tr class="menu_select_tr">
              <td class="menu_select_icon">
                <img src="../nippou/images/menu_icon_mente.gif" alt="<gsmsg:write key="ntp.14" />" />
              </td>
              <td class="menu_select_text" valign="middle" nowrap>
                <span class="timeline_menu2" style=""><gsmsg:write key="ntp.14" /></span>
              </td>
              <td class="menu_select_text" valign="middle" nowrap>
                <div class="menu_select_bg" style="position:relative;left:2px;height:40px">&nbsp;</div>
              </td>
            </tr>

            <tr style="clear:both;">
              <td class="menu_space_area_left"></td>
              <td class="menu_space_area_right" colspan="2" style="height:100%;"></td>
            </tr>

          </table>
        </td>

        <td valign="top" width="84%">

          <div style="padding-left:100px;">
              <img src="../schedule/images/spacer.gif" width="1px" height="10px" border="0">

                <br>

                <table cellpadding="5" cellspacing="0" border="0" width="70%">
                  <tr>
                    <td align="left" class="text_shohin_icon" nowrap height="40px" width="30%"></td>
                    <td align="left" style="border-bottom:1px solid #dddddd;vertical-align:bottom;">
                      <a href="#" onClick="return buttonPush2('ntp130');"><span class="text_link">&nbsp;<gsmsg:write key="ntp.58" /></span></a>
                    </td>
                    <td align="center" style="border-bottom:1px solid #dddddd;vertical-align:bottom;" width="50%">
                      <span class="text_kensu"><bean:write name="ntp120Form" property="ntp120ShohinCnt" /><gsmsg:write key="cmn.number" /></span>
                    </td>
                    <td align="right" nowrap style="border-bottom:1px solid #dddddd;vertical-align:bottom;" width="20%">
                      <span class="text_koshinbi"><bean:write name="ntp120Form" property="ntp120ShohinDay" /> <gsmsg:write key="cmn.update" /></span>
                    </td>
                  </tr>
                </table>

                <br>

                <table cellpadding="5" cellspacing="0" border="0" width="70%">
                <tr>
                  <td align="left" class="text_gyoushu_icon" height="40px"></td>
                  <td align="left" style="border-bottom:1px solid #dddddd;vertical-align:bottom;">
                    <a href="#" onClick="return buttonPush2('ntp140');"><span class="text_link">&nbsp;<gsmsg:write key="ntp.61" /></span></a>
                  </td>
                  <td align="center" style="border-bottom:1px solid #dddddd;vertical-align:bottom;" width="50%">
                   <span class="text_kensu"><bean:write name="ntp120Form" property="ntp120GyoushuCnt" /><gsmsg:write key="cmn.number" /></span>
                  </td>
                  <td align="right" nowrap style="border-bottom:1px solid #dddddd;vertical-align:bottom;" width="20%">
                    <span class="text_koshinbi"><bean:write name="ntp120Form" property="ntp120GyoushuDay" /> <gsmsg:write key="cmn.update" /></span>
                  </td>
                </tr>
                </table>

                <br>

                <table cellpadding="5" cellspacing="0" border="0" width="70%">
                <tr>
                  <td align="left" class="text_process_icon" height="40px"></td>
                  <td align="left" style="border-bottom:1px solid #dddddd;vertical-align:bottom;">
                    <a href="#" onClick="return buttonPush2('ntp150');"><span class="text_link">&nbsp;<gsmsg:write key="ntp.62" /></span></a>
                  </td>
                   <td align="center" style="border-bottom:1px solid #dddddd;vertical-align:bottom;" width="50%">
                   <span class="text_kensu"><bean:write name="ntp120Form" property="ntp120ProcessCnt" /><gsmsg:write key="cmn.number" /></span>
                  </td>
                  <td align="right" nowrap style="border-bottom:1px solid #dddddd;vertical-align:bottom;" width="20%">
                    <span class="text_koshinbi"><bean:write name="ntp120Form" property="ntp120ProcessDay" /> <gsmsg:write key="cmn.update" /></span>
                  </td>
                </tr>
                </table>

                <br>



                <table cellpadding="5" cellspacing="0" border="0" width="70%">
                <tr>
                  <td align="left" class="text_bunrui_icon" height="40px"></td>
                  <td align="left" style="border-bottom:1px solid #dddddd;vertical-align:bottom;">
                     <a href="#" onClick="return buttonPush2('ntp170');"><span class="text_link">&nbsp;<gsmsg:write key="ntp.3" /></span></a>
                  </td>
                  <td align="center" style="border-bottom:1px solid #dddddd;vertical-align:bottom;" width="50%">
                   <span class="text_kensu"><bean:write name="ntp120Form" property="ntp120KtBunruiCnt" /><gsmsg:write key="cmn.number" /></span>
                  </td>
                  <td align="right" nowrap style="border-bottom:1px solid #dddddd;vertical-align:bottom;" width="20%">
                    <span class="text_koshinbi"><bean:write name="ntp120Form" property="ntp120KtBunruiDay" /> <gsmsg:write key="cmn.update" /></span>
                  </td>
                </tr>
                </table>

                <br>

                <table cellpadding="5" cellspacing="0" border="0" width="70%">
                <tr>
                  <td align="left" class="text_houhou_icon" height="40px"></td>
                  <td align="left" style="border-bottom:1px solid #dddddd;vertical-align:bottom;">
                     <a href="#" onClick="return buttonPush2('ntp180');"><span class="text_link">&nbsp;<gsmsg:write key="ntp.121" /></span></a>
                  </td>
                  <td align="center" style="border-bottom:1px solid #dddddd;vertical-align:bottom;" width="50%">
                   <span class="text_kensu"><bean:write name="ntp120Form" property="ntp120KtHouhouCnt" /><gsmsg:write key="cmn.number" /></span>
                  </td>
                  <td align="right" nowrap style="border-bottom:1px solid #dddddd;vertical-align:bottom;" width="20%">
                    <span class="text_koshinbi"><bean:write name="ntp120Form" property="ntp120KtHouhouDay" /> <gsmsg:write key="cmn.update" /></span>
                  </td>
                </tr>
                </table>

                <br>

                <table cellpadding="5" cellspacing="0" border="0" width="70%">
                <tr>
                  <td align="left" class="text_contact_icon" height="40px" width="30%"></td>
                  <td align="left" style="border-bottom:1px solid #dddddd;vertical-align:bottom;">
                     <a href="#" onClick="return buttonPush2('ntp190');"><span class="text_link">&nbsp;<gsmsg:write key="ntp.65" /></span></a>
                  </td>
                   <td align="center" style="border-bottom:1px solid #dddddd;vertical-align:bottom;" width="50%">
                   <span class="text_kensu"><bean:write name="ntp120Form" property="ntp120ContactCnt" /><gsmsg:write key="cmn.number" /></span>
                  </td>
                  <td align="right" nowrap style="border-bottom:1px solid #dddddd;vertical-align:bottom;" width="20%">
                    <span class="text_koshinbi"><bean:write name="ntp120Form" property="ntp120ContactDay" /> <gsmsg:write key="cmn.update" /></span>
                  </td>
                </tr>
                </table>

                <br>

                <table cellpadding="5" cellspacing="0" border="0" width="70%">
                <tr>
                  <td align="left" class="text_target_icon" height="40px" width="30%"></td>
                  <td align="left" style="border-bottom:1px solid #dddddd;vertical-align:bottom;">
                     <a href="#" onClick="return buttonPush2('ntp230');"><span class="text_link">&nbsp;<gsmsg:write key="ntp.12" /></span></a>
                  </td>
                   <td align="center" style="border-bottom:1px solid #dddddd;vertical-align:bottom;" width="50%">
                   <span class="text_kensu"><bean:write name="ntp120Form" property="ntp120TargetCnt" /><gsmsg:write key="cmn.number" /></span>
                  </td>
                  <td align="right" nowrap style="border-bottom:1px solid #dddddd;vertical-align:bottom;" width="20%">
                    <span class="text_koshinbi"><bean:write name="ntp120Form" property="ntp120TargetDay" /> <gsmsg:write key="cmn.update" /></span>
                  </td>
                </tr>
                </table>

                <br>

          </div>

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