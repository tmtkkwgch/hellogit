<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<% String maxLengthUrl  = String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.MAXLENGTH_BASE_URL); %>
<% String maxLengthMail = String.valueOf(jp.groupsession.v2.usr.GSConstUser.MAX_LENGTH_MAIL); %>
<% String maxLengthHost = String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.MAXLENGTH_SEND_HOST); %>
<% String maxLengthPort = String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.MAXLENGTH_SEND_PORT); %>
<% String maxLengthUser = String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.MAXLENGTH_SEND_USER); %>
<% String maxLengthPass = String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.MAXLENGTH_SEND_PASSWORD); %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html;" charset="Shift_JIS">
<title>[Group Session] <gsmsg:write key="cmn.admin.setting.menu" /></title>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<script type="text/javascript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script type="text/javascript" src="../common/js/jquery.selection-min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../anpi/js/anp080.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>

</head>
<body class="body_03">
<html:form action="/anpi/anp080">
<!-- BODY -->
<input type="hidden" name="CMD">
<html:hidden property="backScreen" />
<html:hidden property="anp080BackScreen" />

<html:hidden property="anp080SvBaseUrlAuto" />

<logic:notEmpty name="anp080Form" property="anp080AdmUserList" scope="request">
    <logic:iterate id="ulBean" name="anp080Form" property="anp080AdmUserList" scope="request">
    <input type="hidden" name="anp080AdmUserList" value="<bean:write name="ulBean" />">
    </logic:iterate>
</logic:notEmpty>

<!-- header -->
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!-- content -->
<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">

  <table  cellpadding="0" cellspacing="0"width="70%">
  <tr>
  <td align="center">

    <!-- タイトル -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="anp.plugin"/> <gsmsg:write key="anp.anp080.01"/> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
            <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('anp080excute');">
            <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('anp080back');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
        </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <!-- エラーメッセージ -->
    <div style="text-align:left">
    <html:errors/>
    </div>
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="10" cellspacing="0" border="0" width="100%" class="tl_u2">

        <tr>
            <!-- 返信基本URL -->
            <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
                <span class="text_bb1">返信基本URL<span class="text_r2">※</span></span>
            </td>
            <td valign="middle" align="left" class="td_wt" width="100%">
              <div id="baseUrlForm">
                <html:text styleId="baseUrl" name="anp080Form" property="anp080BaseUrl" maxlength="<%=maxLengthUrl %>" styleClass="text_base" style="width:385px;" />
              </div>
              <span class="text_base">
              <div id="baseUrlAuto">
                <bean:write name="anp080Form" property="anp080SvBaseUrlAuto" />
              </div>
              <br>
              <html:radio styleId="urlSetKbn1" name="anp080Form" property="anp080UrlSetKbn" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.URL_SETTING_AUTO) %>" onclick="changeUrlKbn();" />
              <label for="urlSetKbn1"><gsmsg:write key="anp.anp080.03"/></label>&nbsp;
              <html:radio styleId="urlSetKbn2" name="anp080Form" property="anp080UrlSetKbn" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.URL_SETTING_MANUAL) %>" onclick="changeUrlKbn();" />
              <label for="urlSetKbn2"><gsmsg:write key="anp.anp080.04"/></label>&nbsp;
              <br><gsmsg:write key="anp.anp080.05"/></span>
            </td>
        </tr>


        <tr>
            <!-- 送信メールアドレス -->
            <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
                <span class="text_bb1"><gsmsg:write key="anp.anp080.06"/></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>
            </td>
            <td valign="middle" align="left" class="td_wt" width="100%">
                <html:text name="anp080Form" property="anp080SendMail" maxlength="<%=maxLengthMail %>" styleClass="text_base" style="width:385px;" />
            </td>
        </tr>

        <tr>
            <!-- メール送信サーバ -->
            <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
                <span class="text_bb1"><gsmsg:write key="anp.smtp.server"/></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>
            </td>
            <td align="left" class="td_wt">
                <html:text name="anp080Form" property="anp080SendHost" maxlength="<%=maxLengthHost %>" styleClass="text_base" style="width:277px;" />
                <span class="text_base"><gsmsg:write key="cmn.port.number" /></span>
                <html:text name="anp080Form" property="anp080SendPort" maxlength="<%=maxLengthPort %>" styleClass="text_base" style="width:73px;" />
                <br>
                <html:checkbox styleId="sendSsl" name="anp080Form" property="anp080SendSsl" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SEND_SSL_USE) %>" />
                <label for="sendSsl"><span class="text_base"><gsmsg:write key="anp.anp080.07"/></span></label>
            </td>
        </tr>

        <tr>
            <!-- SMTP認証ON/OFF -->
            <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
                <span class="text_bb1"><gsmsg:write key="anp.anp080.08"/></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>
            </td>
            <td align="left" class="td_wt">
                <span class="text_base">
                <html:radio styleId="smtpAuth1" name="anp080Form" property="anp080SmtpAuth" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SMTP_AUTH_YES) %>" onclick="changeSendServerAuth(1);" />
                   <label for="smtpAuth1"><gsmsg:write key="anp.anp080.09"/></label>&nbsp;
                <html:radio styleId="smtpAuth2" name="anp080Form" property="anp080SmtpAuth" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SMTP_AUTH_NOT) %>" onclick="changeSendServerAuth(0);" />
                   <label for="smtpAuth2"><gsmsg:write key="anp.anp080.10"/></label>&nbsp;
                </span>
            </td>
        </tr>

        <tr>
            <!-- メール送信サーバ ユーザ名 -->
            <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
                <span class="text_bb1"><gsmsg:write key="anp.anp080.11"/></span>
            </td>
            <td valign="middle" align="left" class="td_wt" width="100%">
                <html:text styleId="sendUser" name="anp080Form" property="anp080SendUser" maxlength="<%=maxLengthUser %>" styleClass="text_base" style="width:277px;" />
            </td>
        </tr>

        <tr>
            <!-- メール送信サーバ パスワード -->
            <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
                <span class="text_bb1"><gsmsg:write key="anp.anp080.12"/></span>
            </td>
            <td valign="middle" align="left" class="td_wt" width="100%">
                <html:password styleId="sendPassword" name="anp080Form" property="anp080SendPass" style="width:275px" maxlength="<%=maxLengthPass %>" styleClass="text_base" />
            </td>
        </tr>

        <tr>
            <!-- 安否確認管理者 -->
            <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
                <span class="text_bb1"><gsmsg:write key="anp.anp080.13"/></span>
            </td>
            <td valign="middle" align="left" class="td_wt" width="100%">
                <table width="0%" border="0">
                  <tr>
                  <td width="40%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="cmn.admin"/></span></td>
                  <td width="20%" align="center">&nbsp;</td>

                  <td width="40%" align="left">
                  <logic:notEqual name="anp080Form" property="anp080SelectGroupSid" value="-9">
                  <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup(this.form.anp080SelectGroupSid, 'anp080SelectGroupSid', '<bean:write name="anp080Form" property="anp080SelectGroupSid" />', '1', 'anp080group', 'anp080AdmUserList', '-1', '1')" type="button"><br>
                  </logic:notEqual>

                    <!-- グループコンボボックス -->
                    <logic:notEmpty name="anp080Form" property="anp080GroupLabel" scope="request">
                      <html:select property="anp080SelectGroupSid" onchange="buttonPush('anp080group');" styleClass="select05">

                      <logic:notEmpty name="anp080Form" property="anp080GroupLabel" scope="request">
                      <logic:iterate id="gpBean" name="anp080Form" property="anp080GroupLabel" scope="request">

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
                      <span class="text_base8">
                      <input type="button" onclick="openGroupWindow(this.form.anp080SelectGroupSid, 'anp080SelectGroupSid', '1', 'anp080group')" class="group_btn2" value="&nbsp;" id="groupBtn">
                      <input type="checkbox" value="1" id="select_user" onclick="selectUsersList(this, 'anp080SelectBelongSid');" />
                      <label for="select_user"><gsmsg:write key="cmn.select" /></label></span>
                    </logic:notEmpty>
                  </td>
                  </tr>

                  <tr>
                  <!-- 管理者ユーザリスト -->
                  <td align="center">
                    <select size="10" multiple name="anp080SelectAdmUserSid" class="select01">
                      <logic:notEmpty name="anp080Form" property="anp080AdmUserLabel" scope="request">
                      <logic:iterate id="laveValueBean" name="anp080Form" property="anp080AdmUserLabel" scope="request">
                      <option value="<bean:write name="laveValueBean" property="value"/>"><bean:write name="laveValueBean" property="label"/></option>
                      </logic:iterate>
                      </logic:notEmpty>
                      <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
                    </select>
                  </td>

                  <td align="center">
                    <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('anp080admUserAdd');"><br><br>
                    <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('anp080admUserDel');">
                  </td>

                  <!-- グループ所属ユーザリスト -->
                  <td valign="top" rowspan="3">
                    <select size="10" multiple name="anp080SelectBelongSid" class="select01">
                      <logic:notEmpty name="anp080Form" property="anp080BelongLabel" scope="request">
                      <logic:iterate id="laveValueBean" name="anp080Form" property="anp080BelongLabel" scope="request">
                        <option value="<bean:write name="laveValueBean" property="value" scope="page"/>"><bean:write name="laveValueBean" property="label" scope="page"/></option>
                      </logic:iterate>
                      </logic:notEmpty>
                      <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
                    </select>
                  </td>
                  </tr>
                </table>
            </td>
        </tr>

    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" width="100%">
        <tr>
        <td align="right">
            <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('anp080excute');">
            <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('anp080back');">
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