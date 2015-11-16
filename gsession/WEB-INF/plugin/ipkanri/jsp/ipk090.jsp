<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../ipkanri/js/ipkanri.js?<%= GSConst.VERSION_PARAM %>"></script>
<link rel=stylesheet href='../ipkanri/css/ip.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<theme:css filename="theme.css"/>
<title>[GroupSession] <gsmsg:write key="ipk.ipk090.1" /></title>
</head>
<body class="body_03">
<html:form action="/ipkanri/ipk090">
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<html:hidden property="cmd" />
<html:hidden property="backScreen" />
<html:hidden property="editMode" />
<html:hidden property="specKbn" />
<html:hidden property="ismSid" />
<input type="hidden" name="helpPrm" value="<bean:write name='ipk090Form' property='ipk090helpMode' />">

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">

<tr>
<td width="100%" align="center">
  <table cellpadding="0" cellspacing="0" width="70%" align="center">

  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" width="100%">
    <tr>
    <td width="0%">
    <img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.ipkanri" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="ipk.10" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.ipkanri" />" /></td>
    </tr>
    </table>
  </td>
  </tr>

  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../ipkanri/images/header_glay_1.gif" border="0" alt="<gsmsg:write key="cmn.ipkanri" />"></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" name="add" value="<gsmsg:write key="cmn.add" />" onClick="ipk090ButtonPush('specMstEdit', '1', '');" class="btn_add_n1">
    <input type="button" name="cancel" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush2('ipk090Return');" class="btn_back_n1"></td>
    <td width="0%"><img src="../ipkanri/images/header_glay_3.gif" border="0" alt="<gsmsg:write key="cmn.ipkanri" />"></td>
    </tr>
    </table>
  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="10" height="10" border="0" alt="">
  </td>
  </tr>

  <tr>
  <td class="teble_ip">
    <table cellpadding="0" cellspacing="0" width="100%" border="0">
    <tr>

    <td width="100%" nowrap >

    <logic:equal name="ipk090Form" property="specKbn" value="1">
        <table class="tab_table" width="100%" height="34px" border="0" cellpadding="0" cellspacing="0">
        <tr>

        <td class="tab_bg_image_1_on" nowrap>
            <div class="tab_text_area">
                <a href="javascript:changeMode('1');">CPU</a>
            </div>
        </td>

        <td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
                <a href="javascript:changeMode('2');"><gsmsg:write key="cmn.memory" /></a>
            </div>
        </td>

        <td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
                <a href="javascript:changeMode('3');">H　D</a>
            </div>
        </td>

        <td class="tab_bg_underbar" width="100%" align="right" valign="top" nowrap>&nbsp;</td>
        </tr>
        </table>
    </logic:equal>

    <logic:equal name="ipk090Form" property="specKbn" value="2">
        <table class="tab_table" width="100%" height="34px" border="0" cellpadding="0" cellspacing="0">
        <tr>

        <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
                <a href="javascript:changeMode('1');">CPU</a>
            </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_1_on" nowrap>
            <div class="tab_text_area">
                <a href="javascript:changeMode('2');"><gsmsg:write key="cmn.memory" /></a>
            </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
                <a href="javascript:changeMode('3');">H　D</a>
            </div>
        </td>

        <td class="tab_bg_underbar" width="100%" align="right" valign="top" nowrap>&nbsp;</td>
        </tr>
        </table>
    </logic:equal>

    <logic:equal name="ipk090Form" property="specKbn" value="3">
        <table class="tab_table" width="100%" height="34px" border="0" cellpadding="0" cellspacing="0">
        <tr>

        <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
                <a href="javascript:changeMode('1');">CPU</a>
            </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
                <a href="javascript:changeMode('2');"><gsmsg:write key="cmn.memory" /></a>
            </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_1_on" nowrap>
            <div class="tab_text_area">
                <a href="javascript:changeMode('3');">H　D</a>
            </div>
        </td>

        <td class="tab_bg_underbar" width="100%" align="right" valign="top" nowrap>&nbsp;</td>
        </tr>
        </table>
    </logic:equal>

    </td>
    <td width="0%" class="tab_head_end"><img src="../common/images/spacer.gif" width="10" height="10" border="0" alt=""></td>
    </tr>
    </table>
  </td>
  </tr>

  <tr>
  <td>
    <table class="table_kotei2" align="center">
    <tr>
    <td width="35%" align="center" class="td_type_ipk" nowrap><span class="text_base3"><gsmsg:write key="cmn.name4" /></span></td>
    <td width="50%" align="center" class="td_type_ipk" nowrap><span class="text_base3"><gsmsg:write key="cmn.memo" /></span></td>
    </tr>

    <logic:iterate id="param" name="ipk090Form" property="specInfList" indexId="idx">

    <bean:define id="backclass" value="td_type20_" />
    <bean:define id="backlinkclass" value="text_link3_" />
    <bean:define id="backpat" value="<%= String.valueOf((idx.intValue() % 2) + 1) %>" />
    <bean:define id="back" value="<%= String.valueOf(backclass) + String.valueOf(backpat) %>" />
    <bean:define id="backlink" value="<%= String.valueOf(backlinkclass) + String.valueOf(backpat) %>" />

    <tr>
    <td class="<%= String.valueOf(backlink) %>" align="left" nowrap>
    <a href="#" onClick="ipk090ButtonPush('specMstEdit', '2', '<bean:write name="param" property="ismSid" />');">
    <bean:write name="param" property="ipk100name" filter="false" /></a></td>
    <td class="<%= String.valueOf(back) %>"><bean:write name="param" property="ipk100biko" filter="false" /></td>
    </tr>

    </logic:iterate>

    </table>
  </td>
  </tr>

  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%" class="tab_bottom_left"><img src="../common/images/spacer.gif" width="10" height="10" border="0" alt=""></td>
    <td class="tab_bottom_mid" width="100%" align="right" valign="bottom"></td>
    <td width="0%" class="tab_bottom_right"><img src="../common/images/spacer.gif" width="10" height="10" border="0" alt=""></td>
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