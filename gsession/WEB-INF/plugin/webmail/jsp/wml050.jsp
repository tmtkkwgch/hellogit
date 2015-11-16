<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%-- ゴミ箱自動削除区分 --%>
<%
  String wadDustNo        = String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.WAD_DUST_NO);
  String wadDustLogout    = String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.WAD_DUST_LOGOUT);
  String wadDustAuto      = String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.WAD_DUST_AUTO);
%>

<%-- 送信済み自動削除区分 --%>
<%
  String wadSendNo        = String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.WAD_SEND_NO);
  String wadSendLogout    = String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.WAD_SEND_LOGOUT);
  String wadSendAuto      = String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.WAD_SEND_AUTO);
%>

<%-- 草稿自動削除区分 --%>
<%
  String wadDraftNo        = String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.WAD_DRAFT_NO);
  String wadDraftLogout    = String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.WAD_DRAFT_LOGOUT);
  String wadDraftAuto      = String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.WAD_DRAFT_AUTO);
%>

<%-- 受信箱自動削除区分 --%>
<%
  String wadResvNo        = String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.WAD_RESV_NO);
  String wadResvLogout    = String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.WAD_RESV_LOGOUT);
  String wadResvAuto      = String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.WAD_RESV_AUTO);
%>

<%-- 保管自動削除区分 --%>
<%
  String wadKeepNo        = String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.WAD_KEEP_NO);
  String wadKeepLogout    = String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.WAD_KEEP_LOGOUT);
  String wadKeepAuto      = String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.WAD_KEEP_AUTO);
%>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[Group Session] <gsmsg:write key="cmn.autodelete.setting" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../webmail/js/wml050.js?<%= GSConst.VERSION_PARAM %>"></script>

</head>

<body class="body_03" onload="changeEnableDisable();">

<html:form action="/webmail/wml050">

<%@ include file="/WEB-INF/plugin/webmail/jsp/wml010_hiddenParams.jsp" %>

<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden name="wml050Form" property="wmlCmdMode" />
<html:hidden name="wml050Form" property="wmlViewAccount" />
<html:hidden name="wml050Form" property="wmlAccountMode" />
<html:hidden name="wml050Form" property="wmlAccountSid" />
<html:hidden name="wml050Form" property="wml050initFlg" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table summary="" align="center" cellpadding="0" cellspacing="5" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table summary="" cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>

    <table summary="" cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt=""></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.autodelete.setting" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table summary="" cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="100%"> </td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="<gsmsg:write key="cmn.setting" />" class="btn_setting_n1" onClick="buttonPush('confirm');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('admTool');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
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

        <tr>
        <th><gsmsg:write key="cmn.trash" /></th>
        <td>
          <html:radio name="wml050Form" property="wml050dustKbn" value="<%= wadDustNo %>" styleId="wadDustNo" onclick="setDispState(this.form.wml050dustKbn, this.form.wml050dustYear, this.form.wml050dustMonth, this.form.wml050dustDay)" /><label for="wadDustNo"><gsmsg:write key="cmn.noset" /></label><br>
          <html:radio name="wml050Form" property="wml050dustKbn" value="<%= wadDustLogout %>" styleId="wadDustLogout" onclick="setDispState(this.form.wml050dustKbn, this.form.wml050dustYear, this.form.wml050dustMonth, this.form.wml050dustDay)" /><label for="wadDustLogout"><gsmsg:write key="wml.wml050.02" /></label><br>
          <html:radio name="wml050Form" property="wml050dustKbn" value="<%= wadDustAuto %>" styleId="wadDustAuto" onclick="setDispState(this.form.wml050dustKbn, this.form.wml050dustYear, this.form.wml050dustMonth, this.form.wml050dustDay)" /><label for="wadDustAuto"><gsmsg:write key="cmn.autodelete" /></label>

          <div>
          <logic:notEmpty name="wml050Form" property="yearLabelList">
            <html:select property="wml050dustYear">
              <html:optionsCollection name="wml050Form" property="yearLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="wml050Form" property="monthLabelList">
            <html:select property="wml050dustMonth">
              <html:optionsCollection name="wml050Form" property="monthLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="wml050Form" property="dayLabelList">
            <html:select property="wml050dustDay">
              <html:optionsCollection name="wml050Form" property="dayLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>
          <gsmsg:write key="wml.73" />
          </div>
        </td>
        </tr>

        <tr>
        <th><gsmsg:write key="wml.19" /></th>
        <td>
          <html:radio name="wml050Form" property="wml050sendKbn" value="<%= wadSendNo %>" styleId="wadSendNo" onclick="setDispState(this.form.wml050sendKbn, this.form.wml050sendYear, this.form.wml050sendMonth, this.form.wml050sendDay)" /><label for="wadSendNo"><gsmsg:write key="cmn.noset" /></label><br>
          <html:radio name="wml050Form" property="wml050sendKbn" value="<%= wadSendLogout %>" styleId="wadSendLogout" onclick="setDispState(this.form.wml050sendKbn, this.form.wml050sendYear, this.form.wml050sendMonth, this.form.wml050sendDay)" /><label for="wadSendLogout"><gsmsg:write key="wml.wml050.02" /></label><br>
          <html:radio name="wml050Form" property="wml050sendKbn" value="<%= wadSendAuto %>" styleId="wadSendAuto" onclick="setDispState(this.form.wml050sendKbn, this.form.wml050sendYear, this.form.wml050sendMonth, this.form.wml050sendDay)" /><label for="wadSendAuto"><gsmsg:write key="cmn.autodelete" /></label>
          <div>
          <logic:notEmpty name="wml050Form" property="yearLabelList">
            <html:select property="wml050sendYear">
              <html:optionsCollection name="wml050Form" property="yearLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="wml050Form" property="monthLabelList">
            <html:select property="wml050sendMonth">
              <html:optionsCollection name="wml050Form" property="monthLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="wml050Form" property="dayLabelList">
            <html:select property="wml050sendDay">
              <html:optionsCollection name="wml050Form" property="dayLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>
          <gsmsg:write key="wml.73" />
          </div>
        </td>
        </tr>

        <tr>
        <th><gsmsg:write key="cmn.draft" /></th>
        <td>
          <html:radio name="wml050Form" property="wml050draftKbn" value="<%= wadDraftNo %>" styleId="wadDraftNo" onclick="setDispState(this.form.wml050draftKbn, this.form.wml050draftYear, this.form.wml050draftMonth, this.form.wml050draftDay)" /><label for="wadDraftNo"><gsmsg:write key="cmn.noset" /></label><br>
          <html:radio name="wml050Form" property="wml050draftKbn" value="<%= wadDraftLogout %>" styleId="wadDraftLogout" onclick="setDispState(this.form.wml050draftKbn, this.form.wml050draftYear, this.form.wml050draftMonth, this.form.wml050draftDay)" /><label for="wadDraftLogout"><gsmsg:write key="wml.wml050.02" /></label><br>
          <html:radio name="wml050Form" property="wml050draftKbn" value="<%= wadDraftAuto %>" styleId="wadDraftAuto" onclick="setDispState(this.form.wml050draftKbn, this.form.wml050draftYear, this.form.wml050draftMonth, this.form.wml050draftDay)" /><label for="wadDraftAuto"><gsmsg:write key="cmn.autodelete" /></label>
          <div>
          <logic:notEmpty name="wml050Form" property="yearLabelList">
            <html:select property="wml050draftYear">
              <html:optionsCollection name="wml050Form" property="yearLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="wml050Form" property="monthLabelList">
            <html:select property="wml050draftMonth">
              <html:optionsCollection name="wml050Form" property="monthLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="wml050Form" property="dayLabelList">
            <html:select property="wml050draftDay">
              <html:optionsCollection name="wml050Form" property="dayLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>
          <gsmsg:write key="wml.73" />
          </div>
        </td>
        </tr>

        <tr>
        <th><gsmsg:write key="wml.37" /></th>
        <td>
          <html:radio name="wml050Form" property="wml050resvKbn" value="<%= wadResvNo %>" styleId="wadResvNo" onclick="setDispState(this.form.wml050resvKbn, this.form.wml050resvYear, this.form.wml050resvMonth, this.form.wml050resvDay)" /><label for="wadResvNo"><gsmsg:write key="cmn.noset" /></label><br>
          <html:radio name="wml050Form" property="wml050resvKbn" value="<%= wadResvLogout %>" styleId="wadResvLogout" onclick="setDispState(this.form.wml050resvKbn, this.form.wml050resvYear, this.form.wml050resvMonth, this.form.wml050resvDay)" /><label for="wadResvLogout"><gsmsg:write key="wml.wml050.02" /></label><br>
          <html:radio name="wml050Form" property="wml050resvKbn" value="<%= wadResvAuto %>" styleId="wadResvAuto" onclick="setDispState(this.form.wml050resvKbn, this.form.wml050resvYear, this.form.wml050resvMonth, this.form.wml050resvDay)" /><label for="wadResvAuto"><gsmsg:write key="cmn.autodelete" /></label>
          <div>
          <logic:notEmpty name="wml050Form" property="yearLabelList">
            <html:select property="wml050resvYear">
              <html:optionsCollection name="wml050Form" property="yearLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="wml050Form" property="monthLabelList">
            <html:select property="wml050resvMonth">
              <html:optionsCollection name="wml050Form" property="monthLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="wml050Form" property="dayLabelList">
            <html:select property="wml050resvDay">
              <html:optionsCollection name="wml050Form" property="dayLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>
          <gsmsg:write key="wml.73" />
          </div>
        </td>
        </tr>

        <tr>
        <th><gsmsg:write key="cmn.strage" /></th>
        <td>
          <html:radio name="wml050Form" property="wml050keepKbn" value="<%= wadKeepNo %>" styleId="wadKeepNo" onclick="setDispState(this.form.wml050keepKbn, this.form.wml050keepYear, this.form.wml050keepMonth, this.form.wml050keepDay)" /><label for="wadKeepNo"><gsmsg:write key="cmn.noset" /></label><br>
          <html:radio name="wml050Form" property="wml050keepKbn" value="<%= wadKeepLogout %>" styleId="wadKeepLogout" onclick="setDispState(this.form.wml050keepKbn, this.form.wml050keepYear, this.form.wml050keepMonth, this.form.wml050keepDay)" /><label for="wadKeepLogout"><gsmsg:write key="wml.wml050.02" /></label><br>
          <html:radio name="wml050Form" property="wml050keepKbn" value="<%= wadKeepAuto %>" styleId="wadKeepAuto" onclick="setDispState(this.form.wml050keepKbn, this.form.wml050keepYear, this.form.wml050keepMonth, this.form.wml050keepDay)" /><label for="wadKeepAuto"><gsmsg:write key="cmn.autodelete" /></label>
          <div>
          <logic:notEmpty name="wml050Form" property="yearLabelList">
            <html:select property="wml050keepYear">
              <html:optionsCollection name="wml050Form" property="yearLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="wml050Form" property="monthLabelList">
            <html:select property="wml050keepMonth">
              <html:optionsCollection name="wml050Form" property="monthLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="wml050Form" property="dayLabelList">
            <html:select property="wml050keepDay">
              <html:optionsCollection name="wml050Form" property="dayLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>
          <gsmsg:write key="wml.73" />
          </div>
        </td>
        </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1" height="10" border="0" alt="">
    <table summary="" cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="100%" align="right">
          <input type="button" value="<gsmsg:write key="cmn.setting" />" class="btn_setting_n1" onClick="buttonPush('confirm');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('admTool');">
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