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
<title>[Group Session] <gsmsg:write key="wml.wml050kn.01" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>

</head>

<body class="body_03">

<html:form action="/main/man400kn">

<html:hidden property="backScreen" />
<html:hidden property="backPlugin" />
<html:hidden property="man400CtrlFlgWml"/>
<html:hidden property="man400CtrlFlgSml"/>
<html:hidden property="man400CtrlFlgCir"/>
<html:hidden property="man400CtrlFlgFil"/>
<html:hidden property="man400CtrlFlgBbs"/>

<html:hidden name="man400knForm" property="man400WmlKbn" />
<html:hidden name="man400knForm" property="man400WmlYear" />
<html:hidden name="man400knForm" property="man400WmlMonth" />
<html:hidden name="man400knForm" property="man400SmlKbn" />
<html:hidden name="man400knForm" property="man400SmlYear" />
<html:hidden name="man400knForm" property="man400SmlMonth" />
<html:hidden name="man400knForm" property="man400CirKbn" />
<html:hidden name="man400knForm" property="man400CirYear" />
<html:hidden name="man400knForm" property="man400CirMonth" />
<html:hidden name="man400knForm" property="man400FilKbn" />
<html:hidden name="man400knForm" property="man400FilYear" />
<html:hidden name="man400knForm" property="man400FilMonth" />
<html:hidden name="man400knForm" property="man400BbsKbn" />
<html:hidden name="man400knForm" property="man400BbsYear" />
<html:hidden name="man400knForm" property="man400BbsMonth" />

<html:hidden name="man400knForm" property="man400InitFlg" />

<input type="hidden" name="CMD" value="">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table summary="" align="center" cellpadding="0" cellspacing="5" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table summary="" cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>

    <table summary="" cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key='cmn.admin.setting' />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="wml.wml050kn.01" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key='cmn.admin.setting' />"></td>
      </tr>
    </table>

    <table summary="" cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="100%"> </td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt="<gsmsg:write key='cmn.function.btn' />"></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('decision');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backInput');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt="<gsmsg:write key='cmn.function.btn' />"></td>
      </tr>
    </table>

  </td></tr>
  <tr><td>
    <img src="../common/images/spacer.gif" width="10" height="10" border="0" alt="">
  </td>
  </tr>

  <tr>
  <td>

    <table summary="" id="wml_settings">

        <!-- WEBメール -->
        <logic:equal name="man400knForm" property="man400CtrlFlgWml" value="true">
        <tr>
        <th><gsmsg:write key="wml.wml010.25" /></th>
        <td>
          <bean:write name="man400knForm" property="man400knWmlKbn" />
          <logic:equal name="man400knForm" property="man400WmlKbn" value="1">
            <bean:define id="wmlYear" name="man400knForm" property="man400knWmlYear" type="java.lang.String" />
            &nbsp;<gsmsg:write key="cmn.year" arg0="<%= wmlYear %>" />
            <bean:define id="wmlMonth" name="man400knForm" property="man400knWmlMonth" type="java.lang.String" />
            <gsmsg:write key="cmn.months" arg0="<%= wmlMonth %>" />
            <gsmsg:write key="wml.73" />
          </logic:equal>
        </td>
        </tr>
        </logic:equal>

        <!-- ショートメール -->
        <logic:equal name="man400knForm" property="man400CtrlFlgSml" value="true">
        <tr>
        <th><gsmsg:write key="cmn.shortmail" /></th>
        <td>
          <bean:write name="man400knForm" property="man400knSmlKbn" />
          <logic:equal name="man400knForm" property="man400SmlKbn" value="1">
            <bean:define id="smlYear" name="man400knForm" property="man400knSmlYear" type="java.lang.String" />
            &nbsp;<gsmsg:write key="cmn.year" arg0="<%= smlYear %>" />
            <bean:define id="smlMonth" name="man400knForm" property="man400knSmlMonth" type="java.lang.String" />
            <gsmsg:write key="cmn.months" arg0="<%= smlMonth %>" />
            <gsmsg:write key="wml.73" />
          </logic:equal>
        </td>
        </tr>
        </logic:equal>

        <!-- 回覧板 -->
        <logic:equal name="man400knForm" property="man400CtrlFlgCir" value="true">
        <tr>
        <th><gsmsg:write key="cir.5" /></th>
        <td>
          <bean:write name="man400knForm" property="man400knCirKbn" />
          <logic:equal name="man400knForm" property="man400CirKbn" value="1">
            <bean:define id="cirYear" name="man400knForm" property="man400knCirYear" type="java.lang.String" />
            &nbsp;<gsmsg:write key="cmn.year" arg0="<%= cirYear %>" />
            <bean:define id="cirMonth" name="man400knForm" property="man400knCirMonth" type="java.lang.String" />
            <gsmsg:write key="cmn.months" arg0="<%= cirMonth %>" />
            <gsmsg:write key="wml.73" />
          </logic:equal>
        </td>
        </tr>
        </logic:equal>

        <!-- ファイル管理 -->
        <logic:equal name="man400knForm" property="man400CtrlFlgFil" value="true">
        <tr>
        <th><gsmsg:write key="cmn.filekanri" /></th>
        <td>
          <bean:write name="man400knForm" property="man400knFilKbn" />
          <logic:equal name="man400knForm" property="man400FilKbn" value="1">
            <bean:define id="filYear" name="man400knForm" property="man400knFilYear" type="java.lang.String" />
            &nbsp;<gsmsg:write key="cmn.year" arg0="<%= filYear %>" />
            <bean:define id="filMonth" name="man400knForm" property="man400knFilMonth" type="java.lang.String" />
            <gsmsg:write key="cmn.months" arg0="<%= filMonth %>" />
            <gsmsg:write key="wml.73" />
          </logic:equal>
        </td>
        </tr>
        </logic:equal>

        <!-- 掲示板 -->
        <logic:equal name="man400knForm" property="man400CtrlFlgBbs" value="true">
        <tr>
        <th><gsmsg:write key="cmn.bulletin" /></th>
        <td>
          <bean:write name="man400knForm" property="man400knBbsKbn" />
          <logic:equal name="man400knForm" property="man400BbsKbn" value="1">
            <bean:define id="bbsYear" name="man400knForm" property="man400knBbsYear" type="java.lang.String" />
            &nbsp;<gsmsg:write key="cmn.year" arg0="<%= bbsYear %>" />
            <bean:define id="bbsMonth" name="man400knForm" property="man400knBbsMonth" type="java.lang.String" />
            <gsmsg:write key="cmn.months" arg0="<%= bbsMonth %>" />
            <gsmsg:write key="wml.73" />
          </logic:equal>
        </td>
        </tr>
        </logic:equal>

    </table>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1" height="10" border="0" alt="">
    <table summary="" cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="100%" align="right">
          <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('decision');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backInput');">
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