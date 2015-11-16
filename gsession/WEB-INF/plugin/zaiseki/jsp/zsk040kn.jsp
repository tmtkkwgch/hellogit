<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=shift_jis">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../zaiseki/js/zsk040kn.js?<%= GSConst.VERSION_PARAM %>"></script>
<title>[Group Session] <gsmsg:write key="cmn.zaiseki.management" /></title>
</head>

<body class="body_03">

<html:form action="/zaiseki/zsk040kn">
<input type="hidden" name="CMD">
<html:hidden name="zsk040Form" property="backScreen" />
<html:hidden name="zsk040Form" property="selectZifSid" />
<html:hidden name="zsk040Form" property="uioStatus" />
<html:hidden name="zsk040Form" property="uioStatusBiko" />
<html:hidden name="zsk040Form" property="sortKey" />
<html:hidden name="zsk040Form" property="orderKey" />

<html:hidden name="zsk040knForm" property="zsk040knTmpFileId" />
<html:hidden name="zsk040knForm" property="zsk040name" />
<html:hidden name="zsk040knForm" property="zasekiSortNum" />


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
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.zaiseki.management" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="zsk.zsk040kn.01" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.zaiseki.management" />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%">&nbsp;</td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('cmn999');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('zsk040');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" class="tl0" border="0" cellpadding="5">
      <tr>
        <td class="table_bg_A5B4E1" nowrap width="15%"><span class="text_bb1"><gsmsg:write key="zsk.08" /></span></td>
        <td align="left" class="td_wt" ><bean:write name="zsk040knForm" property="zsk040name" /></td>
      </tr>
      <tr>
        <td class="table_bg_A5B4E1" nowrap width="15%"><span class="text_bb1"><gsmsg:write key="cmn.sort" /></span></td>
        <td align="left" class="td_wt" ><bean:write name="zsk040knForm" property="zasekiSortNum" /></td>
      </tr>
      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="zsk.27" /></span></td>
        <td align="left" class="td_wt">

        <logic:empty name="zsk040knForm" property="zsk040FileLabelList" scope="request">&nbsp;</logic:empty>

        <logic:notEmpty name="zsk040knForm" property="zsk040FileLabelList" scope="request">
        <table cellpadding="0" cellpadding="0" border="0">

        <logic:iterate id="fileMdl" name="zsk040knForm" property="zsk040FileLabelList" scope="request">
          <tr>
          <td><img src="../common/images/file_icon.gif" alt="<gsmsg:write key="cmn.file" />"></td>
          <td class="menu_bun">
          <a href="javascript:void(0);" onClick="return fileLinkClick('<bean:write name="fileMdl" property="value" />');">
          <span class="text_link"><bean:write name="fileMdl" property="label" /></span>
          </a>
          </td>
          </tr>
        </logic:iterate>

        </table>
        </logic:notEmpty>

        </td>
      </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" cellspacing="0" cellpadding="0" border="0">
    <tr>
    <td align="right">
          <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('cmn999');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('zsk040');">
    </td>
    </tr>
    </table>

  </td>
  </tr>
  </table>

</td>
</tr>
</table>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
<IFRAME type="hidden" src="../common/html/damy.html" style="display: none" name="navframe"></IFRAME>
</html:form>
</body>
</html:html>