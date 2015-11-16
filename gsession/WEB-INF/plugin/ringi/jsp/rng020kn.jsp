<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<% int tmodeAll = jp.groupsession.v2.rng.RngConst.RNG_TEMPLATE_ALL; %>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../ringi/css/ringi.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../ringi/js/rng020.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../ringi/js/pageutil.js?<%= GSConst.VERSION_PARAM %>"></script>
<title>[Group Session] <gsmsg:write key="rng.rng020kn.02" /></title>
</head>

<body class="body_03" onunload="windowClose();">

<html:form action="ringi/rng020kn">
<input type="hidden" name="CMD" value="">

<html:hidden property="rngSid" />
<html:hidden property="rngProcMode" />
<html:hidden property="rngCmdMode" />
<html:hidden property="rngTemplateMode" />

<html:hidden property="rng010orderKey" />
<html:hidden property="rng010sortKey" />
<html:hidden property="rng010pageTop" />
<html:hidden property="rng010pageBottom" />

<html:hidden property="rngKeyword" />
<html:hidden property="rng130Type" />
<html:hidden property="sltGroupSid" />
<html:hidden property="sltUserSid" />
<html:hidden property="rng130keyKbn" />
<html:hidden property="rng130searchSubject1" />
<html:hidden property="rng130searchSubject2" />
<html:hidden property="sltSortKey1" />
<html:hidden property="rng130orderKey1" />
<html:hidden property="sltSortKey2" />
<html:hidden property="rng130orderKey2" />
<html:hidden property="sltApplYearFr" />
<html:hidden property="sltApplMonthFr" />
<html:hidden property="sltApplDayFr" />
<html:hidden property="sltApplYearTo" />
<html:hidden property="sltApplMonthTo" />
<html:hidden property="sltApplDayTo" />
<html:hidden property="sltLastManageYearFr" />
<html:hidden property="sltLastManageMonthFr" />
<html:hidden property="sltLastManageDayFr" />
<html:hidden property="sltLastManageYearTo" />
<html:hidden property="sltLastManageMonthTo" />
<html:hidden property="sltLastManageDayTo" />
<html:hidden property="rng130pageTop" />
<html:hidden property="rng130pageBottom" />

<html:hidden property="svRngKeyword" />
<html:hidden property="svRng130Type" />
<html:hidden property="svGroupSid" />
<html:hidden property="svUserSid" />
<html:hidden property="svRng130keyKbn" />
<html:hidden property="svRng130searchSubject1" />
<html:hidden property="svRng130searchSubject2" />
<html:hidden property="svSortKey1" />
<html:hidden property="svRng130orderKey1" />
<html:hidden property="svSortKey2" />
<html:hidden property="svRng130orderKey2" />
<html:hidden property="svApplYearFr" />
<html:hidden property="svApplMonthFr" />
<html:hidden property="svApplDayFr" />
<html:hidden property="svApplYearTo" />
<html:hidden property="svApplMonthTo" />
<html:hidden property="svApplDayTo" />
<html:hidden property="svLastManageYearFr" />
<html:hidden property="svLastManageMonthFr" />
<html:hidden property="svLastManageDayFr" />
<html:hidden property="svLastManageYearTo" />
<html:hidden property="svLastManageMonthTo" />
<html:hidden property="svLastManageDayTo" />

<html:hidden property="rng130searchFlg" />

<html:hidden property="rng020Title" />
<html:hidden property="rng020requestUser" />
<html:hidden property="rng020Content" />
<html:hidden property="rng020BinSid" />

<logic:notEmpty name="rng020knForm" property="rng020apprUser">
<logic:iterate id="apprUser" name="rng020knForm" property="rng020apprUser">
  <input type="hidden" name="rng020apprUser" value="<bean:write name='apprUser' />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rng020knForm" property="rng020confirmUser">
<logic:iterate id="confirmUser" name="rng020knForm" property="rng020confirmUser">
  <input type="hidden" name="rng020confirmUser" value="<bean:write name='confirmUser' />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="rng020copyApply" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!-- body -->
<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">
  <table width="70%" cellpadding="0" cellspacing="0" border="0">

  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../ringi/images/header_ringi_01.gif" border="0" alt="<gsmsg:write key="rng.62" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="rng.62" /></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="rng.rng020kn.01" /> ]</td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="rng.62" />"></td>
    </tr>
    </table>
  </td>
  </tr>

  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt="<gsmsg:write key="rng.63" />"></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" value="<gsmsg:write key="rng.46" />" class="btn_base1" onClick="buttonPush('rng020knSinsei')">
    <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('rng020')"></td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt="<gsmsg:write key="rng.63" />"></td>
    </tr>
    </table>
  </td>
  </tr>

  <logic:messagesPresent message="false">
  <tr>
  <td>
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr><td align="left"><html:errors/><br></td></tr>
    </table>
  </td>
  </tr>
  </logic:messagesPresent>


  <tr>
  <td>
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
  </td>
  </tr>

  <tr>
  <td>
    <table width="100%" class="tl0" border="0" cellpadding="5">

<!-- タイトル --->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.title" /></span></td>
    <td valign="middle" align="left" class="td_wt" width="100%" colspan="2">
      <span class="text_base"><bean:write name="rng020knForm" property="rng020Title" /></span>
    </td>
    </tr>

<!-- 申請者 --->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="rng.47" /></span></td>
    <td valign="middle" align="left" class="td_wt" width="100%" colspan="2">
      <span class="text_base"><bean:write name="rng020knForm" property="rng020requestUser" /></span>
    </td>
    </tr>

<!-- 内容 --->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.content" /></span></td>
    <td valign="middle" align="left" class="td_wt" width="100%" colspan="2">
      <span class="text_base"><bean:write name="rng020knForm" property="rng020knContent" filter="false" /></span>
    </td>
    </tr>

<!-- 添付ファイル --->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.attached" /></span></td>
    <td valign="middle" align="left" class="td_wt" colspan="2" width="100%">

      <logic:notEmpty name="rng020knForm" property="rng020fileList" scope="request">
      <table width="100%"cellpadding="0" cellpadding="0" border="0">
      <logic:iterate id="fileMdl" name="rng020knForm" property="rng020fileList" scope="request">

      <tr>
      <td width="0%"><img src="../common/images/file_icon.gif" alt="<gsmsg:write key="cmn.file" />"></td>
      <td class="menu_bun" width="100%"><a href="javascript:void(0);" onClick="return fileLinkClick('<bean:write name="fileMdl" property="value" />');">
      <span class="text_link"><bean:write name="fileMdl" property="label" /></span></a>
      </td>
      </tr>

      </logic:iterate>
      </table>
      </logic:notEmpty>

    </td>
    </tr>

    <tr>
    <td　colspan="3">
      <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
    </td>
    </tr>

<!-- 経路情報 --->
    <tr>
    <td align="center" colspan="3" class="table_bg_7D91BD" nowrap><span class="text_tlw"><gsmsg:write key="rng.27" /></span></td>
    </tr>

    <logic:notEmpty name="rng020knForm" property="channelList">
    <logic:iterate id="channelModel" name="rng020knForm" property="channelList" indexId="idx">

      <tr>
      <logic:equal name="idx" value="0">
      <td align="center" class="td_wt" rowspan="<bean:write name='rng020knForm' property='rng020apprUserCnt' />"><img src="../common/images/arrow_south.gif" alt="<gsmsg:write key="rng.26" />"></td>
      </logic:equal>
      <td valign="middle" align="left" class="td_wt" width="35%" nowrap><span class="text_base" nowrap><bean:write name="channelModel" property="yakusyoku" /></span></td>
      <td valign="middle" align="left" class="td_wt" width="65%" nowrap><span class="text_base" nowrap><bean:write name="channelModel" property="userName" /></span></td>
      </tr>

    </logic:iterate>
    </logic:notEmpty>

<!-- 確認 --->
    <logic:notEmpty name="rng020knForm" property="confirmChannelList">

    <logic:iterate id="confirmChannelModel" name="rng020knForm" property="confirmChannelList" indexId="indx">
      <tr>
      <logic:equal name="indx" value="0">
      <td class="td_wt" rowspan="<bean:write name='rng020knForm' property='rng020confirmUserCnt' />" align="center" nowrasp><gsmsg:write key="cmn.check" /></td>
      </logic:equal>
      <td valign="middle" align="left" class="td_wt" width="35%" nowrap><span class="text_base" nowrap><bean:write name="confirmChannelModel" property="yakusyoku" /></span></td>
      <td valign="middle" align="left" class="td_wt" width="65%" nowrap><span class="text_base" nowrap><bean:write name="confirmChannelModel" property="userName" /></span></td>
      </tr>
      </logic:iterate>
    </logic:notEmpty>

    </table>
  </td>
  </tr>

  <tr>
  <td>
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
      <input type="button" value="<gsmsg:write key="rng.46" />" class="btn_base1" onClick="buttonPush('rng020knSinsei')">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('rng020')"></td>
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