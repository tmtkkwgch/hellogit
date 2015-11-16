<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%-- 手動削除区分 --%>
<%
  String manuDelNo = String.valueOf(jp.groupsession.v2.man.GSConstMain.LMD_DELKBN_NO);
  String manuDelOk = String.valueOf(jp.groupsession.v2.man.GSConstMain.LMD_DELKBN_OK);
%>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[Group Session] <gsmsg:write key="cmn.statistics.manual.delete" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>

<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script type="text/javascript" src="../main/js/man410.js?<%= GSConst.VERSION_PARAM %>"></script>

</head>

<body class="body_03" onload="changeEnableDisable();">

<html:form action="/main/man410">

<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="backPlugin" />
<html:hidden property="man410CtrlFlgWml"/>
<html:hidden property="man410CtrlFlgSml"/>
<html:hidden property="man410CtrlFlgCir"/>
<html:hidden property="man410CtrlFlgFil"/>
<html:hidden property="man410CtrlFlgBbs"/>

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
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.statistics.manual.delete" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key='cmn.admin.setting' />"></td>
      </tr>
    </table>

    <table summary="" cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="100%"> </td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt="<gsmsg:write key='cmn.function.btn' />"></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('confirm');">
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
      <logic:equal name="man410Form" property="man410CtrlFlgWml" value="true">
      <tr>
      <th><gsmsg:write key="wml.wml010.25" /></th>
      <td>
        <html:radio name="man410Form" property="man410WmlKbn" value="<%= manuDelNo %>" styleId="delKbnWmlNo" onclick="setDispState(this.form.man410WmlKbn, this.form.man410WmlYear, this.form.man410WmlMonth)" />
        <label for="delKbnWmlNo"><gsmsg:write key="cmn.dont.delete" /></label><br>
        <html:radio name="man410Form" property="man410WmlKbn" value="<%= manuDelOk %>" styleId="manuDelWmlOk" onclick="setDispState(this.form.man410WmlKbn, this.form.man410WmlYear, this.form.man410WmlMonth)" />
        <label for="manuDelWmlOk"><gsmsg:write key="wml.60" /></label>
        <div>
          <logic:notEmpty name="man410Form" property="yearLabelList">
            <html:select property="man410WmlYear" styleId="wmlYear">
              <html:optionsCollection name="man410Form" property="yearLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="man410Form" property="monthLabelList">
            <html:select property="man410WmlMonth" styleId="wmlMonth">
              <html:optionsCollection name="man410Form" property="monthLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>
        <gsmsg:write key="wml.73" />
        </div>
      </td>
      </tr>
      </logic:equal>

      <!-- ショートメール -->
      <logic:equal name="man410Form" property="man410CtrlFlgSml" value="true">
      <tr>
        <th><gsmsg:write key="cmn.shortmail" /></th>
      <td>
        <html:radio name="man410Form" property="man410SmlKbn" value="<%= manuDelNo %>" styleId="delKbnSmlNo" onclick="setDispState(this.form.man410SmlKbn, this.form.man410SmlYear, this.form.man410SmlMonth)" />
        <label for="delKbnSmlNo"><gsmsg:write key="cmn.dont.delete" /></label><br>
        <html:radio name="man410Form" property="man410SmlKbn" value="<%= manuDelOk %>" styleId="manuDelSmlOk" onclick="setDispState(this.form.man410SmlKbn, this.form.man410SmlYear, this.form.man410SmlMonth)" />
        <label for="manuDelSmlOk"><gsmsg:write key="wml.60" /></label>
        <div>
          <logic:notEmpty name="man410Form" property="yearLabelList">
            <html:select property="man410SmlYear" styleId="smlYear">
              <html:optionsCollection name="man410Form" property="yearLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="man410Form" property="monthLabelList">
            <html:select property="man410SmlMonth" styleId="smlMonth">
              <html:optionsCollection name="man410Form" property="monthLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>
        <gsmsg:write key="wml.73" />
        </div>
      </td>
      </tr>
      </logic:equal>

      <!-- 回覧板 -->
      <logic:equal name="man410Form" property="man410CtrlFlgCir" value="true">
      <tr>
        <th><gsmsg:write key="cir.5" /></th>
      <td>
        <html:radio name="man410Form" property="man410CirKbn" value="<%= manuDelNo %>" styleId="delKbnCirNo" onclick="setDispState(this.form.man410CirKbn, this.form.man410CirYear, this.form.man410CirMonth)" />
        <label for="delKbnCirNo"><gsmsg:write key="cmn.dont.delete" /></label><br>
        <html:radio name="man410Form" property="man410CirKbn" value="<%= manuDelOk %>" styleId="manuDelCirOk" onclick="setDispState(this.form.man410CirKbn, this.form.man410CirYear, this.form.man410CirMonth)" />
        <label for="manuDelCirOk"><gsmsg:write key="wml.60" /></label>
        <div>
          <logic:notEmpty name="man410Form" property="yearLabelList">
            <html:select property="man410CirYear" styleId="cirYear">
              <html:optionsCollection name="man410Form" property="yearLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="man410Form" property="monthLabelList">
            <html:select property="man410CirMonth" styleId="cirMonth">
              <html:optionsCollection name="man410Form" property="monthLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>
        <gsmsg:write key="wml.73" />
        </div>
      </td>
      </tr>
      </logic:equal>

      <!-- ファイル管理 -->
      <logic:equal name="man410Form" property="man410CtrlFlgFil" value="true">
      <tr>
        <th><gsmsg:write key="cmn.filekanri" /></th>
      <td>
        <html:radio name="man410Form" property="man410FilKbn" value="<%= manuDelNo %>" styleId="delKbnFilNo" onclick="setDispState(this.form.man410FilKbn, this.form.man410FilYear, this.form.man410FilMonth)" />
        <label for="delKbnFilNo"><gsmsg:write key="cmn.dont.delete" /></label><br>
        <html:radio name="man410Form" property="man410FilKbn" value="<%= manuDelOk %>" styleId="manuDelFilOk" onclick="setDispState(this.form.man410FilKbn, this.form.man410FilYear, this.form.man410FilMonth)" />
        <label for="manuDelFilOk"><gsmsg:write key="wml.60" /></label>
        <div>
          <logic:notEmpty name="man410Form" property="yearLabelList">
            <html:select property="man410FilYear" styleId="filYear">
              <html:optionsCollection name="man410Form" property="yearLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="man410Form" property="monthLabelList">
            <html:select property="man410FilMonth" styleId="filMonth">
              <html:optionsCollection name="man410Form" property="monthLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>
        <gsmsg:write key="wml.73" />
        </div>
      </td>
      </tr>
      </logic:equal>

      <!-- 掲示板 -->
      <logic:equal name="man410Form" property="man410CtrlFlgBbs" value="true">
      <tr>
        <th><gsmsg:write key="cmn.bulletin" /></th>
      <td>
        <html:radio name="man410Form" property="man410BbsKbn" value="<%= manuDelNo %>" styleId="delKbnBbsNo" onclick="setDispState(this.form.man410BbsKbn, this.form.man410BbsYear, this.form.man410BbsMonth)" />
        <label for="delKbnBbsNo"><gsmsg:write key="cmn.dont.delete" /></label><br>
        <html:radio name="man410Form" property="man410BbsKbn" value="<%= manuDelOk %>" styleId="manuDelBbsOk" onclick="setDispState(this.form.man410BbsKbn, this.form.man410BbsYear, this.form.man410BbsMonth)" />
        <label for="manuDelBbsOk"><gsmsg:write key="wml.60" /></label>
        <div>
          <logic:notEmpty name="man410Form" property="yearLabelList">
            <html:select property="man410BbsYear" styleId="bbsYear">
              <html:optionsCollection name="man410Form" property="yearLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="man410Form" property="monthLabelList">
            <html:select property="man410BbsMonth" styleId="bbsMonth">
              <html:optionsCollection name="man410Form" property="monthLabelList" value="value" label="label" />
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
          <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('confirm');">
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