<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[Group Session] <gsmsg:write key="cmn.filekanri" /> <gsmsg:write key="cmn.edit.folder.kn" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/file.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/dtree.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/file.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03">
<html:form action="/file/fil060kn">
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<input type="hidden" name="helpPrm" value="<bean:write name="fil060knForm" property="fil060CmdMode" />">
<input type="hidden" name="CMD" value="">
<html:hidden property="backDsp" />
<html:hidden property="backDspLow" />
<html:hidden property="filSearchWd" />

<html:hidden property="fil010SelectCabinet" />
<html:hidden property="fil010SelectDirSid" />

<html:hidden property="fil050DirSid" />
<html:hidden property="fil050ParentDirSid" />
<html:hidden property="fil060CmdMode" />
<html:hidden property="fil060DirName" />
<html:hidden property="fil060Biko" />
<html:hidden property="fil060PluginId" />
<html:hidden property="fil060EditId" />
<html:hidden property="fileSid" />
<html:hidden property="fil060ParentAccessAll" />
<html:hidden property="fil060AccessKbn" />
<html:hidden property="file060AdaptIncFile" />

<html:hidden name="fil060knForm" property="fil100SltCabinetSid" />
<html:hidden name="fil060knForm" property="fil100ChkTrgFolder" />
<html:hidden name="fil060knForm" property="fil100ChkTrgFile" />
<html:hidden name="fil060knForm" property="fil100SearchMode" />
<html:hidden name="fil060knForm" property="fil100ChkWdTrgName" />
<html:hidden name="fil060knForm" property="fil100ChkWdTrgBiko" />
<html:hidden name="fil060knForm" property="fil100ChkWdTrgText" />
<html:hidden name="fil060knForm" property="fileSearchfromYear" />
<html:hidden name="fil060knForm" property="fileSearchfromMonth" />
<html:hidden name="fil060knForm" property="fileSearchfromDay" />
<html:hidden name="fil060knForm" property="fileSearchtoYear" />
<html:hidden name="fil060knForm" property="fileSearchtoMonth" />
<html:hidden name="fil060knForm" property="fileSearchtoDay" />
<html:hidden name="fil060knForm" property="fil100ChkOnOff" />

<html:hidden name="fil060knForm" property="fil100SvSltCabinetSid" />
<html:hidden name="fil060knForm" property="fil100SvChkTrgFolder" />
<html:hidden name="fil060knForm" property="fil100SvChkTrgFile" />
<html:hidden name="fil060knForm" property="fil100SvSearchMode" />
<html:hidden name="fil060knForm" property="fil100SvChkWdTrgName" />
<html:hidden name="fil060knForm" property="fil100SvChkWdTrgBiko" />
<html:hidden name="fil060knForm" property="fil100SvChkWdTrgText" />
<html:hidden name="fil060knForm" property="fil100SvChkWdKeyWord" />
<html:hidden name="fil060knForm" property="fileSvSearchfromYear" />
<html:hidden name="fil060knForm" property="fileSvSearchfromMonth" />
<html:hidden name="fil060knForm" property="fileSvSearchfromDay" />
<html:hidden name="fil060knForm" property="fileSvSearchtoYear" />
<html:hidden name="fil060knForm" property="fileSvSearchtoMonth" />
<html:hidden name="fil060knForm" property="fileSvSearchtoDay" />
<html:hidden name="fil060knForm" property="fil100SvChkOnOff" />
<html:hidden name="fil060knForm" property="fil100sortKey" />
<html:hidden name="fil060knForm" property="fil100orderKey" />
<html:hidden name="fil060knForm" property="fil100pageNum1" />
<html:hidden name="fil060knForm" property="fil100pageNum2" />

<logic:notEmpty name="fil060knForm" property="fil060SvAcFull">
<logic:iterate id="afid" name="fil060knForm" property="fil060SvAcFull">
  <input type="hidden" name="fil060SvAcFull" value="<bean:write name="afid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil060knForm" property="fil060SvAcRead">
<logic:iterate id="arid" name="fil060knForm" property="fil060SvAcRead">
  <input type="hidden" name="fil060SvAcRead" value="<bean:write name="arid" />">
</logic:iterate>
</logic:notEmpty>
<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" width="70%" class="tl0">
  <tr>
  <td align="center">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>

    <td width="0%">
      <img src="../file/images/header_project_01.gif" border="0" alt="<gsmsg:write key="cmn.filekanri" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.filekanri" /></span></td>
    <td width="100%" class="header_white_bg_text">
    <logic:equal name="fil060knForm" property="fil060CmdMode" value="0">
        [ <gsmsg:write key="fil.fil060kn.2" /> ]
    </logic:equal>
    <logic:equal name="fil060knForm" property="fil060CmdMode" value="1">
        [ <gsmsg:write key="cmn.edit.folder.kn" /> ]
    </logic:equal>

    </td>
    <td width="0%" class="header_white_bg">
      <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" class="btn_ok1" value="OK" onclick="buttonPush('fil060knok');">
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('fil060knback');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <logic:messagesPresent message="false">
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr><td width="100%"><html:errors /></td></tr>
    </table>
    </logic:messagesPresent>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <table width="100%" class="tl0 prj_tbl_base" border="0" cellpadding="5">

    <tr>
    <td class="td_sub_title" nowrap>
    <span class="text_bb1"><gsmsg:write key="cmn.update.user" /></span>
    </td>
    <td align="left" class="td_sub_detail">
    <span class="text_base_prj"><bean:write name="fil060knForm" property="fil060knEditName" /></span>
    </td>
    </tr>

    <tr>
    <td class="td_sub_title" nowrap>
    <span class="text_bb1"><gsmsg:write key="fil.21" /><span class="text_r2">※</span></span>
    </td>
    <td align="left" class="td_sub_detail">
    <span class="text_base_prj"><bean:write name="fil060knForm" property="fil060DirName" /></span>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="td_sub_title" width="0%" nowrap>
    <span class="text_bb1"><gsmsg:write key="fil.102" /></span><span class="text_r2">※</span>
    </td>


    <logic:equal name="fil060knForm" property="fil060AccessKbn" value="0">
    <td align="left" class="td_sub_detail">
    <span class="text_base_prj"><gsmsg:write key="cmn.not.limit" /></span>
    </td>
    </logic:equal>

    <logic:notEqual name="fil060knForm" property="fil060AccessKbn" value="0">
    <td valign="middle" align="left" class="td_wt" width="100%">
      <table width="60%" cellpadding="0" cellspacing="5" border="0" class="tl_u2">
      <logic:notEmpty name="fil060knForm" property="fil060AcFullLavel" scope="request">
      <tr>
      <td width="40%" class="td_sub_title3" align="center"><span class="text_bb1"><gsmsg:write key="cmn.add.edit.delete" /></span></td>
      </tr>
      <tr>
      <td align="left" class="td_wt">
      <logic:iterate id="fullBean" name="fil060knForm" property="fil060AcFullLavel" scope="request">
      <span class="text_base"><bean:write name="fullBean" property="label" /></span><br>
      </logic:iterate>
    </td>
      </tr>
      </logic:notEmpty>

      <logic:notEmpty name="fil060knForm" property="fil060AcReadLavel" scope="request">
      <tr>
      <td width="40%" class="td_sub_title3" align="center"><span class="text_bb1"><gsmsg:write key="cmn.reading" /></span></td>
      </tr>
      <tr>
      <td align="left" class="td_wt">
      <logic:iterate id="readBean" name="fil060knForm" property="fil060AcReadLavel" scope="request">
      <span class="text_base"><bean:write name="readBean" property="label" /></span><br>
      </logic:iterate>
      </td>
      </tr>
      </logic:notEmpty>

      </table>
      <br>
      <span class="text_base"><gsmsg:write key="fil.127" />：<b>
      <logic:notEqual name="fil060knForm" property="file060AdaptIncFile" value="1"><gsmsg:write key="fil.129" /></logic:notEqual>
      <logic:equal name="fil060knForm" property="file060AdaptIncFile" value="1"><gsmsg:write key="fil.128" /></logic:equal></b></span>
    </td>
    </logic:notEqual>
    </tr>

    <tr>
    <td class="td_sub_title" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.memo" /></span></td>
    <td class="td_sub_detail" align="left" width="80%">
    <span class="text_base"><bean:write name="fil060knForm" property="fil060knBiko" filter="false"/></span>
    </td>
    </tr>
<%--
    <tr>
    <td class="td_sub_title" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.attach.file" /></span></td>

    <td class="td_sub_detail" align="left" width="80%">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
      <td width="0%" align="left" valign="top" nowrap>

      <logic:notEmpty name="fil060knForm" property="fil060FileLabelList">
      <logic:iterate id="file" name="fil060knForm" property="fil060FileLabelList">

        <a href="#" onclick="fileDl('fileDownload', '<bean:write name="file" property="value" />');">
        <span class="text_link2"><bean:write name="file" property="label" /></span>
        </a>
        <br>

      </logic:iterate>
      </logic:notEmpty>

      </td>
      </tr>
      </table>
    </td>
    </tr>
--%>
    </table>

  </td>
  </tr>
  <tr>
  <td><img src="../project/images/spacer.gif" width="1px" height="10px" border="0"></td>
  </tr>

  <tr>
  <td align="right">
    <input type="button" class="btn_ok1" value="OK" onclick="buttonPush('fil060knok');">
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('fil060knback');">
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