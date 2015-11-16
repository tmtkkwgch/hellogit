<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%
    String maxLengthBiko = String.valueOf(jp.groupsession.v2.cmn.GSConstCommon.MAX_LENGTH_ENT_BIKO);
%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[Group Session] <gsmsg:write key="main.man002.21" /></title>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn160.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">

</head>

<body class="body_03" onload="showLengthId($('#inputstr')[0], <%= maxLengthBiko %>, 'inputlength');">

<html:form action="/common/cmn160">
<html:hidden property="cmn160InitFlg" />
<html:hidden property="cmn160LogoName" />
<html:hidden property="cmn160LogoSaveName" />
<html:hidden property="cmn160TempSetFlg" />
<html:hidden property="cmn160TourokuKbn" />
<html:hidden property="cmn160DspLogoKbn" />
<html:hidden property="cmn160DbLogoKbn" />

<html:hidden property="cmn160MenuLogoName" />
<html:hidden property="cmn160MenuLogoSaveName" />
<html:hidden property="cmn160MenuTempSetFlg" />

<html:hidden property="cmn160MenuDspLogoKbn" />
<html:hidden property="cmn160MenuDbLogoKbn" />

<html:hidden property="cmn160ThemePath" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<input type="hidden" name="CMD" value="">

<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt=""></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="main.man002.21" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>


    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" class="btn_ok1" value="OK" onClick="buttonPush('confirmEnterprise');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backAdmMenu');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <logic:messagesPresent message="false">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr><td width="100%"><html:errors /></td></tr>
    </table>
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
    </logic:messagesPresent>

    <table cellpadding="5" cellspacing="0" border="0" width="100%" class="tl_u2">

    <!--  会社名 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.company.name" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <html:text property="cmn160ComName" style="width:453px;" maxlength="50" />
    </td>
    </tr>

    <!--  会社名かな -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.cmn160.1" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <html:text property="cmn160ComNamekn" style="width:453px;" maxlength="100" />
      <span class="text_base"><gsmsg:write key="cmn.cmn160.2" /></span><br>
    </td>
    </tr>

    <!-- ロゴ ログイン画面 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.login" /><gsmsg:write key="cmn.display" /> <gsmsg:write key="cmn.logo" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt">
    <table width="100%">
    <tr>
    <td colspan="3">
      <logic:empty name="cmn160Form" property="cmn160LogoName">
        <img src="../common/images/login/login_gslogo2.gif" name="pitctImage" alt="<gsmsg:write key="cmn.logo" />" border="1">
      </logic:empty>

      <logic:notEmpty name="cmn160Form" property="cmn160LogoName">
      <logic:equal name="cmn160Form" property="cmn160DspLogoKbn" value="0">
        <img src="../common/images/login/login_gslogo2.gif" name="pitctImage" alt="<gsmsg:write key="cmn.logo" />" border="1">
      </logic:equal>
      <logic:equal name="cmn160Form" property="cmn160DspLogoKbn" value="1">
        <img src="../common/cmn160.do?CMD=getImageFile" alt="<gsmsg:write key="cmn.original.logo" />" border="1">
      </logic:equal>
      </logic:notEmpty>
    </td>
    </tr>
    <tr>
    <td align="left" colspan="3">
    <span class="text_base"><gsmsg:write key="cmn.change.logo" /></span><br>
    <span class="text_base">&nbsp;<gsmsg:write key="cmn.cmn160.4" /></span>
    </td>
    </tr>
    <tr>
    <td width="100%" align="left">
    <input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="buttonPush('cmn160tempdeleteMark');">
    <input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTempPlus('cmn160SelectFiles', '<%= jp.groupsession.v2.usr.GSConstUser.PLUGIN_ID_USER %>', '1', '1', 'cmn160Logo');">
    <input type="button" class=btn_base0 value="<gsmsg:write key="cmn.reset" />" onclick="buttonPush('defaultLogo');">
    </td>
    </tr>
    </table>
    </td>
    </tr>

    <!-- ロゴ メニュー -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="man.menu" /> <gsmsg:write key="cmn.logo" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt">
    <table width="100%">
    <tr>
    <td colspan="3">
      <logic:empty name="cmn160Form" property="cmn160MenuLogoName">
        <img src="../<bean:write name="cmn160Form" property="cmn160ThemePath" />/images/menu_logo1.png" name="pitctMenuImage" alt="<gsmsg:write key="cmn.logo" />" border="1">
      </logic:empty>

      <logic:notEmpty name="cmn160Form" property="cmn160MenuLogoName">
      <logic:equal name="cmn160Form" property="cmn160MenuDspLogoKbn" value="0">
        <img src="../<bean:write name="cmn160Form" property="cmn160ThemePath" />/images/menu_logo1.png" name="pitctMenuImage" alt="<gsmsg:write key="cmn.logo" />" border="1">
      </logic:equal>
      <logic:equal name="cmn160Form" property="cmn160MenuDspLogoKbn" value="1">
        <img src="../common/cmn160.do?CMD=getMenuImageFile" alt="<gsmsg:write key="cmn.original.logo" />" border="1">
      </logic:equal>
      </logic:notEmpty>
    </td>
    </tr>
    <tr>
    <td align="left" colspan="3">
    <span class="text_base"><gsmsg:write key="cmn.change.menu.logo" /></span><br>
    <span class="text_base">&nbsp;<gsmsg:write key="cmn.cmn160.5" /></span>
    </td>
    </tr>
    <tr>
    <td width="100%" align="left">
    <input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="buttonPush('cmn160tempdeleteMenuMark');">
    <input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTempPlus('cmn160SelectMenuFiles', '<%= jp.groupsession.v2.usr.GSConstUser.PLUGIN_ID_USER %>', '1', '1', 'cmn160MenuLogo');">
    <input type="button" class=btn_base0 value="<gsmsg:write key="cmn.reset" />" onclick="buttonPush('defaultMenuLogo');">
    </td>
    </tr>
    </table>
    </td>
    </tr>

    <!-- URL -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1">URL</span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <html:text property="cmn160Url" style="width:633px;" maxlength="100" /><br>
      <span class="text_base"><gsmsg:write key="cmn.change.logo.link" /></span>
    </td>
    </tr>

    <!-- 期首月 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.cmn160.3" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <logic:notEmpty name="cmn160Form" property="cmn160MonthList">
        <html:select name="cmn160Form" property="cmn160Kisyu">
          <html:optionsCollection name="cmn160Form" property="cmn160MonthList" value="value" label="label" />
        </html:select>
      </logic:notEmpty>
    </td>
    </tr>

    <!-- 備考 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.memo" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <textarea name="cmn160Biko" style="width:541" rows="5" styleClass="text_gray" onkeyup="showLengthStr(value, <%= maxLengthBiko %>, 'inputlength');" id="inputstr" /><bean:write name="cmn160Form" property="cmn160Biko" /></textarea>
      <br>
      <span class="font_string_count"><gsmsg:write key="wml.js.15" /></span><span id="inputlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthBiko %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
    </tr>

    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
  </td>
  </tr>

  <td>
  <tr>
    <table width="70%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
      <input type="button" class="btn_ok1" value="OK" onClick="buttonPush('confirmEnterprise');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backAdmMenu');">
    </td>
    </tr>
    </table>

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