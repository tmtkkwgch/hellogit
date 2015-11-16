<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%-- 統計自動削除区分 --%>
<%
  String delKbnNo = String.valueOf(jp.groupsession.v2.man.GSConstMain.LAD_DELKBN_NO);
  String delKbnAuto = String.valueOf(jp.groupsession.v2.man.GSConstMain.LAD_DELKBN_AUTO);
%>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[Group Session] <gsmsg:write key="cmn.statistics.automatic.delete.setting" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
  <script language="JavaScript" src="../main/js/man400.js?<%= GSConst.VERSION_PARAM %>"></script>

</head>

<body class="body_03" onload="changeEnableDisable();">

<html:form action="/main/man400">

<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="backPlugin" />
<html:hidden property="man400InitFlg" />
<html:hidden property="man400CtrlFlgWml"/>
<html:hidden property="man400CtrlFlgSml"/>
<html:hidden property="man400CtrlFlgCir"/>
<html:hidden property="man400CtrlFlgFil"/>
<html:hidden property="man400CtrlFlgBbs"/>

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
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.statistics.automatic.delete.setting" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key='cmn.admin.setting' />"></td>
      </tr>
    </table>

    <table summary="" cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="100%"> </td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt="<gsmsg:write key='cmn.function.btn' />"></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="<gsmsg:write key="cmn.setting" />" class="btn_setting_n1" onClick="buttonPush('confirm');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('admTool');">
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
        <logic:equal name="man400Form" property="man400CtrlFlgWml" value="true">
        <tr>
        <th><gsmsg:write key="wml.wml010.25" /></th>
        <td>
          <html:radio name="man400Form" property="man400WmlKbn" value="<%= delKbnNo %>" styleId="delKbnWmlNo" onclick="setDispState(this.form.man400WmlKbn, this.form.man400WmlYear, this.form.man400WmlMonth)" />
          <label for="delKbnWmlNo"><gsmsg:write key="cmn.noset" /></label><br>
          <html:radio name="man400Form" property="man400WmlKbn" value="<%= delKbnAuto %>" styleId="delKbnWmlAuto" onclick="setDispState(this.form.man400WmlKbn, this.form.man400WmlYear, this.form.man400WmlMonth)" />
          <label for="delKbnWmlAuto"><gsmsg:write key="cmn.autodelete" /></label>
          <div>
          <logic:notEmpty name="man400Form" property="yearLabelList">
            <html:select property="man400WmlYear">
              <html:optionsCollection name="man400Form" property="yearLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="man400Form" property="monthLabelList">
            <html:select property="man400WmlMonth">
              <html:optionsCollection name="man400Form" property="monthLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>
          <gsmsg:write key="wml.73" />
          </div>
        </td>
        </tr>
        </logic:equal>

        <!-- ショートメール -->
        <logic:equal name="man400Form" property="man400CtrlFlgSml" value="true">
        <tr>
        <th><gsmsg:write key="cmn.shortmail" /></th>
        <td>
          <html:radio name="man400Form" property="man400SmlKbn" value="<%= delKbnNo %>" styleId="delKbnSmlNo" onclick="setDispState(this.form.man400SmlKbn, this.form.man400SmlYear, this.form.man400SmlMonth)" />
          <label for="delKbnSmlNo"><gsmsg:write key="cmn.noset" /></label><br>
          <html:radio name="man400Form" property="man400SmlKbn" value="<%= delKbnAuto %>" styleId="delKbnSmlAuto" onclick="setDispState(this.form.man400SmlKbn, this.form.man400SmlYear, this.form.man400SmlMonth)" />
          <label for="delKbnSmlAuto"><gsmsg:write key="cmn.autodelete" /></label>
          <div>
          <logic:notEmpty name="man400Form" property="yearLabelList">
            <html:select property="man400SmlYear">
              <html:optionsCollection name="man400Form" property="yearLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="man400Form" property="monthLabelList">
            <html:select property="man400SmlMonth">
              <html:optionsCollection name="man400Form" property="monthLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>
          <gsmsg:write key="wml.73" />
          </div>
        </td>
        </tr>
        </logic:equal>

        <!-- 回覧板 -->
        <logic:equal name="man400Form" property="man400CtrlFlgCir" value="true">
        <tr>
        <th><gsmsg:write key="cir.5" /></th>
        <td>
          <html:radio name="man400Form" property="man400CirKbn" value="<%= delKbnNo %>" styleId="delKbnCirNo" onclick="setDispState(this.form.man400CirKbn, this.form.man400CirYear, this.form.man400CirMonth)" />
          <label for="delKbnCirNo"><gsmsg:write key="cmn.noset" /></label><br>
          <html:radio name="man400Form" property="man400CirKbn" value="<%= delKbnAuto %>" styleId="delKbnCirAuto" onclick="setDispState(this.form.man400CirKbn, this.form.man400CirYear, this.form.man400CirMonth)" />
          <label for="delKbnCirAuto"><gsmsg:write key="cmn.autodelete" /></label>
          <div>
          <logic:notEmpty name="man400Form" property="yearLabelList">
            <html:select property="man400CirYear">
              <html:optionsCollection name="man400Form" property="yearLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="man400Form" property="monthLabelList">
            <html:select property="man400CirMonth">
              <html:optionsCollection name="man400Form" property="monthLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>
          <gsmsg:write key="wml.73" />
          </div>
        </td>
        </tr>
        </logic:equal>

        <!-- ファイル管理 -->
        <logic:equal name="man400Form" property="man400CtrlFlgFil" value="true">
        <tr>
        <th><gsmsg:write key="cmn.filekanri" /></th>
        <td>
          <html:radio name="man400Form" property="man400FilKbn" value="<%= delKbnNo %>" styleId="delKbnFilNo" onclick="setDispState(this.form.man400FilKbn, this.form.man400FilYear, this.form.man400FilMonth)" />
          <label for="delKbnFilNo"><gsmsg:write key="cmn.noset" /></label><br>
          <html:radio name="man400Form" property="man400FilKbn" value="<%= delKbnAuto %>" styleId="delKbnFilAuto" onclick="setDispState(this.form.man400FilKbn, this.form.man400FilYear, this.form.man400FilMonth)" />
          <label for="delKbnFilAuto"><gsmsg:write key="cmn.autodelete" /></label>
          <div>
          <logic:notEmpty name="man400Form" property="yearLabelList">
            <html:select property="man400FilYear">
              <html:optionsCollection name="man400Form" property="yearLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="man400Form" property="monthLabelList">
            <html:select property="man400FilMonth">
              <html:optionsCollection name="man400Form" property="monthLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>
          <gsmsg:write key="wml.73" />
          </div>
        </td>
        </tr>
        </logic:equal>

        <!-- 掲示板 -->
        <logic:equal name="man400Form" property="man400CtrlFlgBbs" value="true">
        <tr>
        <th><gsmsg:write key="cmn.bulletin" /></th>
        <td>
          <html:radio name="man400Form" property="man400BbsKbn" value="<%= delKbnNo %>" styleId="delKbnBbsNo" onclick="setDispState(this.form.man400BbsKbn, this.form.man400BbsYear, this.form.man400BbsMonth)" />
          <label for="delKbnBbsNo"><gsmsg:write key="cmn.noset" /></label><br>
          <html:radio name="man400Form" property="man400BbsKbn" value="<%= delKbnAuto %>" styleId="delKbnBbsAuto" onclick="setDispState(this.form.man400BbsKbn, this.form.man400BbsYear, this.form.man400BbsMonth)" />
          <label for="delKbnBbsAuto"><gsmsg:write key="cmn.autodelete" /></label>
          <div>
          <logic:notEmpty name="man400Form" property="yearLabelList">
            <html:select property="man400BbsYear">
              <html:optionsCollection name="man400Form" property="yearLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="man400Form" property="monthLabelList">
            <html:select property="man400BbsMonth">
              <html:optionsCollection name="man400Form" property="monthLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>
          <gsmsg:write key="wml.73" />
          </div>
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