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
<title>[GroupSession] <gsmsg:write key="main.man002.19" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../main/js/man340.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/imageView.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../main/css/main.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">
<html:form action="/main/man340">

<input type="hidden" name="CMD" value="">
<html:hidden property="man340initFlg" />
<html:hidden property="man120pluginId" />
<html:hidden property="man340cmdMode" />
<html:hidden property="man340file" />
<html:hidden property="man340SaveFile" />
<html:hidden property="man340funcId" />


<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->
<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text">[ <gsmsg:write key="cmn.plugin" /><gsmsg:write key="cmn.add" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="submit" value="OK" class="btn_ok1" onClick="buttonPush('man340kn');">

          <logic:equal name="man340Form" property="man340cmdMode" value="1">
            <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('man340_del');">
          </logic:equal>

          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('man120');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

    <logic:messagesPresent message="false">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tl0">
    <tr>
    <td align="left"><html:errors/><br></td>
    </tr>
    </table>
    </logic:messagesPresent>

  </td></tr>
  <tr><td>
    <img src="../common/images/spacer.gif" width="10" height="10" border="0" alt="">
  </td>
  </tr>

  <tr>
  <td>

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">

    <tr>
    <td class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.plugin" /><gsmsg:write key="cmn.name3" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_wt" width="80%">
      <html:text style="width:275px;" maxlength="10" property="man340title" styleClass="text_base" />
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb1">URL</span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_wt" width="80%">
      <html:text style="width:550px;" maxlength="1000" property="man340url" styleClass="text_base" />
    </td>
    </tr>

    <!-- param -->
    <tr>
    <td class="table_bg_A5B4E1" width="15%" rowspan="2"><span class="text_bb1"><gsmsg:write key="main.man340.5" /></span></td>
    <td class="td_type1">
      <html:radio name="man340Form" property="man340paramKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PARAM_KBN_NO) %>" styleId="man340paramKbn0"/><label for="man340paramKbn0"><gsmsg:write key="cmn.noset" /></label>&nbsp;
      <html:radio name="man340Form" property="man340paramKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PARAM_KBN_YES) %>" styleId="man340paramKbn1"/><label for="man340paramKbn1"><gsmsg:write key="cmn.setting.do" /></label>&nbsp;
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="td_type1" style="border-collapse: collapse;" id="paramSetArea">
      <span class="text_bb1"><gsmsg:write key="main.man340.6" />：&nbsp;</span>
      <html:radio name="man340Form" styleId="sendKbn_01" property="man340sendKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.SEND_KBN_POST) %>" /><label for="sendKbn_01"><gsmsg:write key="main.man340.7" /></label>&nbsp;
      <html:radio name="man340Form" styleId="sendKbn_02" property="man340sendKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.SEND_KBN_GET) %>" /><label for="sendKbn_02"><gsmsg:write key="main.man340.8" /></label>

      <br><br>

      <table width="80%" border="0">
      <tr>
      <td width="40%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="main.man340.9" /></span></td>
      <td width="20%" align="center">&nbsp;</td>
      <td width="40%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="main.man340.10" /></span></td>
      </tr>

      <logic:notEmpty name="man340Form" property="man340urlParamList">

      <% String[] lineParamName = {"paramName", "paramValue"}; %>
      <logic:iterate name="man340Form" property="man340urlParamList" id="urlParamMdl" indexId="idx">

        <% String lineNo = String.valueOf(idx.intValue()); %>
        <% String lineFrmName = "man340urlParamList[" + lineNo + "]."; %>

        <tr>
        <td align="center">
          <html:text maxlength="100" property="<%= lineFrmName + lineParamName[0] %>" styleClass="text_base" style="width:100%;" />
        </td>
        <td align="center">
          =
        </td>
        <td align="center">
          <html:text maxlength="1000" property="<%= lineFrmName + lineParamName[1] %>" styleClass="text_base" style="width: 100%;"/>
        </td>
        </tr>
      </logic:iterate>
      </logic:notEmpty>

      </table>

      <br>
      <gsmsg:write key="main.man340.11" />

    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="15%"><span class="text_bb1"><gsmsg:write key="main.man340.1" /></span></td>
    <td class="td_type1">
      <html:radio name="man340Form" property="man340openKbn" value="0" styleId="man340opnKbn0"/><span><label for="man340opnKbn0"><gsmsg:write key="main.man340.2" /></label></span>&nbsp;
      <html:radio name="man340Form" property="man340openKbn" value="1" styleId="man340opnKbn1"/><span><label for="man340opnKbn1"><gsmsg:write key="main.man340.3" /></label>&nbsp;</span>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.icon" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <table width="100%" border="0">
      <tr>
      <td width="0%">

        <logic:equal name="man340Form" property="man340file" value="">
          <img src="../common/images/plugin_default.gif" name="pitctImage" width="25" height="25" alt="<gsmsg:write key="cmn.icon" />"><br>
        </logic:equal>
        <logic:notEqual name="man340Form" property="man340file" value="">
          <img src="../main/man340.do?CMD=getImageFile" name="pitctImage" width="25" height="25" alt="<gsmsg:write key="cmn.icon" />" onload="initImageView('pitctImage');">
        </logic:notEqual>
      </td>
      </tr>
      <tr>
      <td><span class="text_base">(<gsmsg:write key="main.man340.4" />)</span><br>
        <input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="buttonPush('delete');">
        &nbsp;<input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTempPlus('man340SelectTempFilesMark', '<bean:write name="man340Form" property="man340pluginId" />', '1', '1', 'main');">
      </td>
      <td>&nbsp;</td>
      </tr>
      </table>
    </td>
    </tr>

    </table>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="<gsmsg:write key="cmn.spacer" />">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="100%" align="right">
          <input type="submit" value="OK" class="btn_ok1" onClick="buttonPush('man340kn');">

          <logic:equal name="man340Form" property="man340cmdMode" value="1">
            <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('man340_del');">
          </logic:equal>

          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('man120');">
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