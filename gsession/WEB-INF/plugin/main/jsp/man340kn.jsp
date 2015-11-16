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
<script language="JavaScript" src="../common/js/imageView.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../main/css/main.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">
<html:form action="/main/man340kn">

<input type="hidden" name="CMD" value="">
<html:hidden property="man340initFlg" />
<html:hidden property="man340cmdMode" />
<html:hidden property="man340file" />
<html:hidden property="man340SaveFile" />
<html:hidden property="man340funcId" />
<html:hidden property="man340title" />
<html:hidden property="man340url" />
<html:hidden property="man340openKbn" />

<html:hidden property="man340paramKbn" />
<html:hidden property="man340sendKbn" />

<logic:notEmpty name="man340knForm" property="man340urlParamListToList">
<logic:iterate id="paramData" name="man340knForm" property="man340urlParamListToList" indexId="idx">
  <input type="hidden" name="man340urlParamList[<%= String.valueOf(idx.intValue()) %>].paramName" value="<bean:write name="paramData" property="paramName" />">
  <input type="hidden" name="man340urlParamList[<%= String.valueOf(idx.intValue()) %>].paramValue" value="<bean:write name="paramData" property="paramValue" />">
</logic:iterate>
</logic:notEmpty>

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
        <td width="100%" class="header_ktool_bg_text">[ <gsmsg:write key="cmn.plugin" /><gsmsg:write key="cmn.add" /><gsmsg:write key="cmn.check" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('340kn_ok');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('man340');">
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
      <span class="text_base"><bean:write name="man340knForm" property="man340title" /></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb1">URL</span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_wt" width="80%">
      <span class="text_base"><bean:write name="man340knForm" property="man340url" /></span>
    </td>
    </tr>

    <!-- param -->
    <tr>
    <td class="table_bg_A5B4E1" width="15%" rowspan="2"><span class="text_bb1"><gsmsg:write key="main.man340.5" /></span></td>
    <td class="td_type1">
      <logic:equal name="man340knForm" property="man340paramKbn" value="0">
        <gsmsg:write key="cmn.noset" />
      </logic:equal>
      <logic:notEqual name="man340knForm" property="man340paramKbn" value="0">
        <gsmsg:write key="cmn.setting.do" />
      </logic:notEqual>
    </td>
    </tr>

    <tr>
      <logic:notEqual name="man340knForm" property="man340paramKbn" value="0">
      <td valign="middle" align="left" class="td_type1" style="border-collapse: collapse;">
        <logic:equal name="man340knForm" property="man340sendKbn" value="0">
          <span class="text_bb1"><gsmsg:write key="main.man340.6" />：&nbsp;</span><gsmsg:write key="main.man340.7" />
        </logic:equal>
        <logic:notEqual name="man340knForm" property="man340sendKbn" value="0">
          <span class="text_bb1"><gsmsg:write key="main.man340.6" />：&nbsp;</span><gsmsg:write key="main.man340.8" />
        </logic:notEqual>

        <br><br>

        <table width="80%" border="0">
        <tr>
        <td width="40%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="main.man340.9" /></span></td>
        <td width="20%" align="center">&nbsp;</td>
        <td width="40%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="main.man340.10" /></span></td>
        </tr>

        <logic:notEmpty name="man340knForm" property="man340urlParamListToList">
        <logic:iterate name="man340knForm" property="man340urlParamListToList" id="urlParamMdl">
          <tr>
          <td class="td_type1" align="left">
            <bean:write name="urlParamMdl" property="paramName" />
          </td>
          <td align="center">
            =
          </td>
          <td class="td_type1" align="left">
            <bean:write name="urlParamMdl" property="paramValue" />
          </td>
          </tr>
        </logic:iterate>
        </logic:notEmpty>

        </table>

      </td>
      </logic:notEqual>
    </tr>


    <tr>
    <td class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb1"><gsmsg:write key="main.man340.1" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_wt" width="80%">


      <logic:equal name="man340knForm" property="man340openKbn" value="0">
        <gsmsg:write key="main.man340.2" />
      </logic:equal>
      <logic:notEqual name="man340knForm" property="man340openKbn" value="0">
        <gsmsg:write key="main.man340.3" />
      </logic:notEqual>


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

        <logic:equal name="man340knForm" property="man340file" value="">
          <img src="../common/images/plugin_default.gif" name="pitctImage" width="25" height="25" alt="<gsmsg:write key="cmn.icon" />"><br>
        </logic:equal>
        <logic:notEqual name="man340knForm" property="man340file" value="">
          <img src="../main/man340.do?CMD=getImageFile" name="pitctImage" width="25" height="25" alt="<gsmsg:write key="cmn.icon" />" onload="initImageView('pitctImage');">
        </logic:notEqual>
      </td>
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
          <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('340kn_ok');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('man340');">
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