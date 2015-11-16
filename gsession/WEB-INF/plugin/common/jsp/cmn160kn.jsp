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
<title>[Group Session] <gsmsg:write key="cmn.cmn160kn.1" /></title>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
</head>

<body class="body_03">

<html:form action="/common/cmn160kn">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<input type="hidden" name="CMD" value="">

<html:hidden property="cmn160InitFlg" />
<html:hidden property="cmn160ComName" />
<html:hidden property="cmn160ComNamekn" />
<html:hidden property="cmn160Url" />
<html:hidden property="cmn160Kisyu" />
<html:hidden property="cmn160Biko" />
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
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.cmn160kn.1" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" class="btn_ok1" value="<gsmsg:write key="cmn.final" />" onClick="buttonPush('cmn160knDecision');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('cmn160knBack');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
  </td>
  </tr>
  <tr>
  <td>

    <table cellpadding="5" cellspacing="0" border="0" width="100%" class="tl_u2">
    <!--  会社名 -->
    <tr>
    <td width="20%" valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.company.name" /></span>
    </td>
    <td width="80%" valign="middle" align="left" class="td_wt">
      <span class="text_base"><bean:write name="cmn160knForm" property="cmn160ComName" /></span>
    </td>
    </tr>

    <!--  会社名かな -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.cmn160.1" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <span class="text_base"><bean:write name="cmn160knForm" property="cmn160ComNamekn" /></span>
    </td>
    </tr>

    <!-- ロゴ ログイン画面 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.login" /><gsmsg:write key="cmn.display" /> <gsmsg:write key="cmn.logo" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <logic:empty name="cmn160knForm" property="cmn160LogoName">
        <img src="../common/images/login/login_gslogo2.gif" name="pitctImage" alt="<gsmsg:write key="cmn.logo" />" border="1">
      </logic:empty>
      <logic:notEmpty name="cmn160knForm" property="cmn160LogoName">
        <logic:equal name="cmn160Form" property="cmn160DspLogoKbn" value="0">
          <img src="../common/images/login/login_gslogo2.gif" name="pitctImage" alt="<gsmsg:write key="cmn.logo" />" border="1">
        </logic:equal>
        <logic:equal name="cmn160Form" property="cmn160DspLogoKbn" value="1">
          <img src="../common/cmn160.do?CMD=getImageFile" alt="<gsmsg:write key="cmn.original.logo" />" border="1">
        </logic:equal>
      </logic:notEmpty>
    </td>
    </tr>

    <!-- ロゴ メニュー -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="man.menu" /> <gsmsg:write key="cmn.logo" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <logic:empty name="cmn160knForm" property="cmn160MenuLogoName">
        <img src="../<bean:write name="cmn160Form" property="cmn160ThemePath" />/images/menu_logo1.png" name="pitctMenuImage" alt="<gsmsg:write key="cmn.logo" />" border="1">
      </logic:empty>
      <logic:notEmpty name="cmn160knForm" property="cmn160MenuLogoName">
        <logic:equal name="cmn160Form" property="cmn160MenuDspLogoKbn" value="0">
          <img src="../<bean:write name="cmn160Form" property="cmn160ThemePath" />/images/menu_logo1.png" name="pitctMenuImage" alt="<gsmsg:write key="cmn.logo" />" border="1">
        </logic:equal>
        <logic:equal name="cmn160Form" property="cmn160MenuDspLogoKbn" value="1">
          <img src="../common/cmn160.do?CMD=getMenuImageFile" alt="<gsmsg:write key="cmn.original.logo" />" border="1">
        </logic:equal>
      </logic:notEmpty>
    </td>
    </tr>

    <!-- URL -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1">URL</span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <span class="text_base"><bean:write name="cmn160knForm" property="cmn160Url" /></span>
    </td>
    </tr>

    <!-- 期首月 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.cmn160.3" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <span class="text_base"><bean:write name="cmn160knForm" property="cmn160Kisyu" /><gsmsg:write key="cmn.month" /></span>
    </td>
    </tr>

    <!-- 備考 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.memo" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <span class="text_base"><bean:write name="cmn160knForm" property="cmn160knBiko" filter="false" /></span>
    </td>
    </tr>

    </table>
  </td>
  </tr>

  <tr>
  <td>
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
      <input type="button" class="btn_ok1" value="<gsmsg:write key="cmn.final" />" onClick="buttonPush('cmn160knDecision');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('cmn160knBack');">
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