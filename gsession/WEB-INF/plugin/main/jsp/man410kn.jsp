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
<title>[Group Session] <gsmsg:write key="cmn.statistics.manual.delete.kn" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>

</head>

<body class="body_03">

<html:form action="/main/man410kn">

<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="backPlugin" />
<html:hidden property="man410CtrlFlgWml"/>
<html:hidden property="man410CtrlFlgSml"/>
<html:hidden property="man410CtrlFlgCir"/>
<html:hidden property="man410CtrlFlgFil"/>
<html:hidden property="man410CtrlFlgBbs"/>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<html:hidden name="man410knForm" property="man410WmlKbn" />
<html:hidden name="man410knForm" property="man410WmlYear" />
<html:hidden name="man410knForm" property="man410WmlMonth" />
<html:hidden name="man410knForm" property="man410SmlKbn" />
<html:hidden name="man410knForm" property="man410SmlYear" />
<html:hidden name="man410knForm" property="man410SmlMonth" />
<html:hidden name="man410knForm" property="man410CirKbn" />
<html:hidden name="man410knForm" property="man410CirYear" />
<html:hidden name="man410knForm" property="man410CirMonth" />
<html:hidden name="man410knForm" property="man410FilKbn" />
<html:hidden name="man410knForm" property="man410FilYear" />
<html:hidden name="man410knForm" property="man410FilMonth" />
<html:hidden name="man410knForm" property="man410BbsKbn" />
<html:hidden name="man410knForm" property="man410BbsYear" />
<html:hidden name="man410knForm" property="man410BbsMonth" />

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
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.statistics.manual.delete.kn" /> ]</td>
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
        <logic:equal name="man410knForm" property="man410CtrlFlgWml" value="true">
        <tr>
        <th><gsmsg:write key="wml.wml010.25" /></th>
        <td>
          <bean:write name="man410knForm" property="man410knWmlKbn" />
          <logic:equal name="man410knForm" property="man410WmlKbn" value="1">
            <bean:define id="wmlYear" name="man410knForm" property="man410knWmlYear" type="java.lang.String" />
            &nbsp;<gsmsg:write key="cmn.year" arg0="<%= wmlYear %>" />
            <bean:define id="wmlMonth" name="man410knForm" property="man410knWmlMonth" type="java.lang.String" />
            <gsmsg:write key="cmn.months" arg0="<%= wmlMonth %>" />
            <gsmsg:write key="wml.73" />
          </logic:equal>
        </td>
        </tr>
        </logic:equal>

        <!-- ショートメール -->
        <logic:equal name="man410knForm" property="man410CtrlFlgSml" value="true">
        <tr>
        <th><gsmsg:write key="cmn.shortmail" /></th>
        <td>
          <bean:write name="man410knForm" property="man410knSmlKbn" />
          <logic:equal name="man410knForm" property="man410SmlKbn" value="1">
            <bean:define id="smlYear" name="man410knForm" property="man410knSmlYear" type="java.lang.String" />
            &nbsp;<gsmsg:write key="cmn.year" arg0="<%= smlYear %>" />
            <bean:define id="smlMonth" name="man410knForm" property="man410knSmlMonth" type="java.lang.String" />
            <gsmsg:write key="cmn.months" arg0="<%= smlMonth %>" />
            <gsmsg:write key="wml.73" />
          </logic:equal>
        </td>
        </tr>
        </logic:equal>

        <!-- 回覧板 -->
        <logic:equal name="man410knForm" property="man410CtrlFlgCir" value="true">
        <tr>
        <th><gsmsg:write key="cir.5" /></th>
        <td>
          <bean:write name="man410knForm" property="man410knCirKbn" />
          <logic:equal name="man410knForm" property="man410CirKbn" value="1">
            <bean:define id="cirYear" name="man410knForm" property="man410knCirYear" type="java.lang.String" />
            &nbsp;<gsmsg:write key="cmn.year" arg0="<%= cirYear %>" />
            <bean:define id="cirMonth" name="man410knForm" property="man410knCirMonth" type="java.lang.String" />
            <gsmsg:write key="cmn.months" arg0="<%= cirMonth %>" />
            <gsmsg:write key="wml.73" />
          </logic:equal>
        </td>
        </tr>
        </logic:equal>

        <!-- ファイル管理 -->
        <logic:equal name="man410knForm" property="man410CtrlFlgFil" value="true">
        <tr>
        <th><gsmsg:write key="cmn.filekanri" /></th>
        <td>
          <bean:write name="man410knForm" property="man410knFilKbn" />
          <logic:equal name="man410knForm" property="man410FilKbn" value="1">
            <bean:define id="filYear" name="man410knForm" property="man410knFilYear" type="java.lang.String" />
            &nbsp;<gsmsg:write key="cmn.year" arg0="<%= filYear %>" />
            <bean:define id="filMonth" name="man410knForm" property="man410knFilMonth" type="java.lang.String" />
            <gsmsg:write key="cmn.months" arg0="<%= filMonth %>" />
            <gsmsg:write key="wml.73" />
          </logic:equal>
        </td>
        </tr>
        </logic:equal>

        <!-- 掲示板 -->
        <logic:equal name="man410knForm" property="man410CtrlFlgBbs" value="true">
        <tr>
        <th><gsmsg:write key="cmn.bulletin" /></th>
        <td>
          <bean:write name="man410knForm" property="man410knBbsKbn" />
          <logic:equal name="man410knForm" property="man410BbsKbn" value="1">
            <bean:define id="bbsYear" name="man410knForm" property="man410knBbsYear" type="java.lang.String" />
            &nbsp;<gsmsg:write key="cmn.year" arg0="<%= bbsYear %>" />
            <bean:define id="bbsMonth" name="man410knForm" property="man410knBbsMonth" type="java.lang.String" />
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