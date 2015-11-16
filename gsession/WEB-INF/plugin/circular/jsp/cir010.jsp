<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<% String jusin = String.valueOf(jp.groupsession.v2.cir.GSConstCircular.MODE_JUSIN); %>
<% String sosin = String.valueOf(jp.groupsession.v2.cir.GSConstCircular.MODE_SOUSIN); %>
<% String gomi = String.valueOf(jp.groupsession.v2.cir.GSConstCircular.MODE_GOMI); %>

<% String unopen = String.valueOf(jp.groupsession.v2.cir.GSConstCircular.CONF_UNOPEN); %>

<%
  int order_desc = jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC;
  int order_asc = jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC;
%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<logic:notEqual name="cir010Form" property="cir010AccountTheme" value="0">
<link rel=stylesheet href="../common/css/theme<bean:write name="cir010Form" property="cir010AccountTheme" />/theme.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
</logic:notEqual>
<logic:equal name="cir010Form" property="cir010AccountTheme" value="0">
<theme:css filename="theme.css"/>
</logic:equal>

<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../circular/css/circular.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="cir.5" /></title>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/check.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../circular/js/cir010.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript">
<!--
  //自動リロード
  <logic:notEqual name="cir010Form" property="cir010Reload" value="0">
    var reloadinterval = <bean:write name="cir010Form" property="cir010Reload" />;
    setTimeout("buttonPush('reload')",reloadinterval);
  </logic:notEqual>
-->
</script>
</head>

<body class="body_03">

<html:form action="/circular/cir010">

<input type="hidden" name="helpPrm" value="<bean:write name="cir010Form" property="cir010cmdMode" />">

<input type="hidden" name="CMD" value="search">
<input type="hidden" name="cir010selectInfSid">
<input type="hidden" name="cir010sojuKbn">
<html:hidden property="cirAccountSid" />
<html:hidden property="cir010cmdMode" />
<html:hidden property="cir010orderKey" />
<html:hidden property="cir010sortKey" />
<logic:notEmpty name="cir010Form" property="cir010saveList" scope="request">
<logic:iterate id="chks" name="cir010Form" property="cir010saveList" scope="request">
  <bean:define id="chkSid" name="chks" type="java.lang.String" />
  <html:hidden property="cir010delInfSid" value="<%= chkSid %>" />
</logic:iterate>
</logic:notEmpty>
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<bean:define id="orderKey" name="cir010Form" property="cir010orderKey" />
<bean:define id="sortKbn" name="cir010Form" property="cir010sortKey" />
<% int iOrderKey = ((Integer) orderKey).intValue(); %>
<% int iSortKbn = ((Integer) sortKbn).intValue(); %>

<!-- BODY -->
<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../circular/images/header_circular.gif" border="0" alt="<gsmsg:write key="cir.5" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cir.5" /></span></td>
  <logic:equal name="cir010Form" property="cir010cmdMode" value="<%= jusin %>">
    <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="cmn.receive2" /> ]</td>
  </logic:equal>

  <logic:equal name="cir010Form" property="cir010cmdMode" value="<%= sosin %>">
    <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="cir.cir010.1" /> ]</td>
  </logic:equal>

  <logic:equal name="cir010Form" property="cir010cmdMode" value="<%= gomi %>">
    <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="cmn.trash2" /> ]</td>
  </logic:equal>

    <td width="100%" class="header_white_bg">
      <input type="button" value="<gsmsg:write key="cmn.reload" />" class="btn_reload_n1" onClick="buttonPush('reload');">
      <input type="button" name="btn_account" class="btn_base1" value="<gsmsg:write key="wml.100" />" onClick="return buttonPush('accountConf');">
      <logic:equal name="cir010Form" property="adminFlg" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.USER_ADMIN) %>">
        <input type="button" name="settingAdmin" class="btn_setting_admin_n1" value="<gsmsg:write key="cmn.admin.setting" />" onClick="buttonPush('admConf');">
      </logic:equal>
      <input type="button" name="btn_prjadd" class="btn_setting_n1" value="<gsmsg:write key="cmn.preferences2" />" onClick="buttonPush('pset');">
    <td width="0%">
      <img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cir.5" />"></td>
    </tr>
    </table>

    <table width="100%" cellpadding="5" cellspacing="0">

    <tr>

    <td align="left">
      <span class="attent1"><gsmsg:write key="wml.102" />：</span>
      <html:select property="cirViewAccount" styleId="account_comb_box" styleClass="text_base account_select">
        <logic:notEmpty name="cir010Form" property="cir010AccountList">
          <logic:iterate id="accountMdl" name="cir010Form" property="cir010AccountList">
            <bean:define id="accoutVal" name="accountMdl" property="accountSid" type="java.lang.Integer" />
            <html:option value="<%= String.valueOf(accoutVal) %>"><bean:write name="accountMdl" property="accountName" /></html:option>
          </logic:iterate>
        </logic:notEmpty>
      </html:select>
    </td>

    <td align="right">

      <logic:equal name="cir010Form" property="cir010cmdMode" value="<%= gomi %>">

      <input type="button" onclick="buttonPush('gomibakoClear');" class="btn_gomibako_clear" value="<gsmsg:write key="cmn.empty.trash" />" name="trashdel">

      </logic:equal>

      <input type="button" value="<gsmsg:write key="cir.cir010.3" />" class="btn_add_n2" onClick="buttonPush('add');">
      <input type="button" value="<gsmsg:write key="cmn.delete2" />" class="btn_dell_n1" onClick="buttonPush('delete');">

    <logic:equal name="cir010Form" property="cir010cmdMode" value="<%= gomi %>">
      <input type="button" value="<gsmsg:write key="cmn.undo" />" class="btn_modosu_n1" onClick="buttonPush('comeback');">
    </logic:equal>
    <logic:notEqual name="cir010Form" property="cir010cmdMode" value="<%= gomi %>">
        <img src="../smail/images/spacer.gif" width="100px" height="1px" border="0">
    </logic:notEqual>
    </td>
    </tr>
    </table>


    <img src="../circular/images/spacer.gif" width="1px" height="10px" border="0">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <!-- エラーメッセージ -->
    <logic:messagesPresent message="false">
    <tr>
    <td align="left"><html:errors/><br></td>
    </tr>
    </logic:messagesPresent>

    <tr>

    <td width="100%" nowrap>

    <logic:equal name="cir010Form" property="cir010cmdMode" value="<%= jusin %>">
        <table class="tab_table" width="100%" height="34px" border="0" cellpadding="0" cellspacing="0">
        <tr>

        <td class="tab_bg_image_1_on" nowrap>
            <div class="tab_text_area">
                <a href="javascript:changeTab('jusin');"><gsmsg:write key="cmn.receive2" /></a>
            </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
                <a href="javascript:changeTab('sousin');"><gsmsg:write key="cir.8" /></a>
            </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
                <a href="javascript:changeTab('gomi');"><gsmsg:write key="cmn.trash" /></a>
            </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
           <td class="tab_bg_underbar" width="100%" align="right" valign="top" nowrap>
               <div align="right">
                  <img src="../common/images/search.gif" alt="<gsmsg:write key="cmn.search" />" class="img_bottom">
                  <html:text property="cir010searchWord" styleClass="text_base" maxlength="100" style="width:187px;"/>
                  <input type="submit" value="<gsmsg:write key="cmn.search" />" class="btn_base0">
                  <img src="../smail/images/spacer.gif" width="3px"border="0">
           </div>
         </td>

        <td class="tab_bg_underbar" width="100%" align="right" valign="top" nowrap>
               <div align="right">
                  <!-- ページング -->
                <bean:size id="count1" name="cir010Form" property="cir010PageLabel" scope="request" />
                <logic:greaterThan name="count1" value="1">
                  <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" align ="img_bottom" height="20" width="20" onClick="buttonPush('prev');">
                  <html:select property="cir010pageNum1" onchange="changePage(1);" styleClass="text_i">
                    <html:optionsCollection name="cir010Form" property="cir010PageLabel" value="value" label="label" />
                  </html:select>
                  <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" align ="img_bottom" width="20" height="20" onClick="buttonPush('next');">
                </logic:greaterThan>
            </div>
         </td>
         </tr>
        </table>
    </logic:equal>

    <logic:equal name="cir010Form" property="cir010cmdMode" value="<%= sosin %>">
        <table class="tab_table" width="100%" height="34px" border="0" cellpadding="0" cellspacing="0">
        <tr>

        <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
                <a href="javascript:changeTab('jusin');"><gsmsg:write key="cmn.receive2" /></a>
            </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_1_on" nowrap>
            <div class="tab_text_area">
                <a href="javascript:changeTab('sousin');"><gsmsg:write key="cir.8" /></a>
            </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
                <a href="javascript:changeTab('gomi');"><gsmsg:write key="cmn.trash" /></a>
            </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
           <td class="tab_bg_underbar" width="100%" align="right" valign="top" nowrap>
               <div align="right">
                  <img src="../common/images/search.gif" alt="<gsmsg:write key="cmn.search" />" class="img_bottom">
                  <html:text property="cir010searchWord" styleClass="text_base" maxlength="100" style="width:187px;"/>
                  <input type="submit" value="<gsmsg:write key="cmn.search" />" class="btn_base0">
                  <img src="../smail/images/spacer.gif" width="3px"border="0">
           </div>
         </td>

        <td class="tab_bg_underbar" width="100%" align="right" valign="top" nowrap>
               <div align="right">
                  <!-- ページング -->
                <bean:size id="count1" name="cir010Form" property="cir010PageLabel" scope="request" />
                <logic:greaterThan name="count1" value="1">
                  <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" align="top" class="text_i" height="20" width="20" onClick="buttonPush('prev');">
                  <html:select property="cir010pageNum1" onchange="changePage(1);" styleClass="text_i">
                    <html:optionsCollection name="cir010Form" property="cir010PageLabel" value="value" label="label" />
                  </html:select>
                  <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" align="top" class="text_i" height="20" width="20" onClick="buttonPush('next');">
                </logic:greaterThan>
            </div>
         </td>
         </tr>
        </table>
    </logic:equal>

    <logic:equal name="cir010Form" property="cir010cmdMode" value="<%= gomi %>">
        <table class="tab_table" width="100%" height="34px" border="0" cellpadding="0" cellspacing="0">
        <tr>

        <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
                <a href="javascript:changeTab('jusin');"><gsmsg:write key="cmn.receive2" /></a>
            </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
                <a href="javascript:changeTab('sousin');"><gsmsg:write key="cir.8" /></a>
            </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_1_on" nowrap>
            <div class="tab_text_area">
                <a href="javascript:changeTab('gomi');"><gsmsg:write key="cmn.trash" /></a>
            </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
           <td class="tab_bg_underbar" width="100%" align="right" valign="top" nowrap>
               <div align="right">
                  <img src="../common/images/search.gif" alt="<gsmsg:write key="cmn.search" />" class="img_bottom">
                  <html:text property="cir010searchWord" styleClass="text_base" maxlength="100" style="width:187px;"/>
                  <input type="submit" value="<gsmsg:write key="cmn.search" />" class="btn_base0">
                  <img src="../smail/images/spacer.gif" width="3px"border="0">
           </div>
         </td>

        <td class="tab_bg_underbar" width="100%" align="right" valign="top" nowrap>
               <div align="right">
                  <!-- ページング -->
                <bean:size id="count1" name="cir010Form" property="cir010PageLabel" scope="request" />
                <logic:greaterThan name="count1" value="1">
                  <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" align="top" class="text_i" height="20" width="20" onClick="buttonPush('prev');">
                  <html:select property="cir010pageNum1" onchange="changePage(1);" styleClass="text_i">
                    <html:optionsCollection name="cir010Form" property="cir010PageLabel" value="value" label="label" />
                  </html:select>
                  <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" align="top" class="text_i" height="20" width="20" onClick="buttonPush('next');">
                </logic:greaterThan>
            </div>
         </td>
         </tr>
        </table>
    </logic:equal>
    </td>

    <td width="0%" class="tab_head_end"><img src="../common/images/damy.gif" width="10" height="10" border="0" alt=""></td>
    </tr>
    </table>

    <table class="tl0 table_td_border" width="100%" cellpadding="0" cellspacing="0">

    <logic:equal name="cir010Form" property="cir010cmdMode" value="<%= jusin %>">
      <%@ include file="/WEB-INF/plugin/circular/jsp/cir010_sub01.jsp" %>
    </logic:equal>

    <logic:equal name="cir010Form" property="cir010cmdMode" value="<%= sosin %>">
      <%@ include file="/WEB-INF/plugin/circular/jsp/cir010_sub02.jsp" %>
    </logic:equal>

    <logic:equal name="cir010Form" property="cir010cmdMode" value="<%= gomi %>">
      <%@ include file="/WEB-INF/plugin/circular/jsp/cir010_sub03.jsp" %>
    </logic:equal>

    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%" class="tab_bottom_left"><img src="../common/images/damy.gif" width="10" height="10" border="0" alt=""></td>
    <td class="tab_bottom_mid" width="100%" align="right" valign="bottom">
      <!-- ページング -->
    <bean:size id="count2" name="cir010Form" property="cir010PageLabel" scope="request" />
    <logic:greaterThan name="count2" value="1">
      <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" align="top" class="text_i" height="20" width="20" onClick="buttonPush('prev');">
      <html:select property="cir010pageNum2" onchange="changePage(2);" styleClass="text_i">
        <html:optionsCollection name="cir010Form" property="cir010PageLabel" value="value" label="label" />
      </html:select>
      <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" align="top" class="text_i" height="20" width="20" onClick="buttonPush('next');">
    </logic:greaterThan>
    </td>
    <td width="0%" class="tab_bottom_right"><img src="../common/images/damy.gif" width="10" height="34" border="0" alt=""></td>
    </tr>
    </table>

  </td>
  </tr>
  </table>
</td>
</tr>
</table>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</html:form>

</body>
</html:html>