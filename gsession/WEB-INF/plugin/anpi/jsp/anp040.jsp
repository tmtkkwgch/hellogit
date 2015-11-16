<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html;" charset="Shift_JIS">
<title>[Group Session] <gsmsg:write key="cmn.preferences.menu" /></title>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<script type="text/javascript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>

</head>
<body class="body_03">
<html:form action="/anpi/anp040">
<!-- BODY -->
<input type="hidden" name="CMD">

<html:hidden property="backScreen" />
<html:hidden property="anp040UserSid" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">

  <table  cellpadding="0" cellspacing="0"width="70%">
  <tr>
  <td align="center">

    <!-- タイトル -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="anp.plugin"/> <gsmsg:write key="cmn.display.settings"/> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
            <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('anp040excute');">
            <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('anp040back');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
        </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="10" cellspacing="0" border="0" width="100%" class="tl_u2">

        <tr>
            <td width="20%" valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
            <span class="text_bb1"><gsmsg:write key="cmn.main.view2"/><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></span>
            </td>
            <td width="80%" valign="middle" align="left" class="td_wt" >
              <html:radio name="anp040Form" property="anp040MainDispFlg" value="0" styleId="anp040MainDispFlg_01"><label for="anp040MainDispFlg_01"><span class="text_base"><gsmsg:write key="cmn.dont.show"/></span></label></html:radio>&nbsp;
              <html:radio name="anp040Form" property="anp040MainDispFlg" value="1" styleId="anp040MainDispFlg_02"><label for="anp040MainDispFlg_02"><span class="text_base"><gsmsg:write key="cmn.display.ok"/></span></label></html:radio>
            </td>
        </tr>

        <tr>
            <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
            <span class="text_bb1"><gsmsg:write key="anp.number.display"/></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>
            </td>
            <td valign="middle" align="left" class="td_wt" width="100%">
            <html:select name="anp040Form" property="anp040SelectDispCnt">
              <html:optionsCollection name="anp040Form" property="anp040DsipCntList" value="value" label="label" />
            </html:select>
            <span class="text_base"><gsmsg:write key="cmn.number" />　<gsmsg:write key="anp.anp040.01"/></span>
            </td>
        </tr>

        <tr>
            <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
            <span class="text_bb1"><gsmsg:write key="anp.anp040.02"/></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>
            <td valign="middle" align="left" class="td_wt" width="100%">
              <logic:notEmpty name="anp040Form" property="anp040GroupLabel" scope="request">
                <html:select name="anp040Form" property="anp040SelectGroupSid" styleClass="select05">

                <logic:notEmpty name="anp040Form" property="anp040GroupLabel" scope="request">
                	<logic:iterate id="gpBean" name="anp040Form" property="anp040GroupLabel" scope="request">

                	<bean:define id="gpValue" name="gpBean" property="value" type="java.lang.String" />
                    <logic:equal name="gpBean" property="styleClass" value="0">
                    <html:option value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
                    </logic:equal>

                    <logic:notEqual name="gpBean" property="styleClass" value="0">
                    <html:option styleClass="select03" value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
                    </logic:notEqual>

                    </logic:iterate>
                </logic:notEmpty>

                </html:select>

                <!-- グループ選択ボタン-->
                <input type="button" onclick="openGroupWindow(this.form.anp040SelectGroupSid, 'anp040SelectGroupSid', '1')" class="group_btn2" value="&nbsp;" id="groupBtn">
                <span class="text_base">　<gsmsg:write key="anp.anp040.03"/></span>
              </logic:notEmpty>
           </td>
        </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" width="100%">
        <tr>
            <td align="right">
                <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('anp040excute');">
                <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('anp040back');">
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