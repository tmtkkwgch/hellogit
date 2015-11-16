<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[Group Session] <gsmsg:write key="cmn.filekanri" />  <gsmsg:write key="fil.fil240.1" /></title>
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/file.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/dtree.css?<%= GSConst.VERSION_PARAM %>" type="text/css">

<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/dtree.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/file.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/fil240.js?<%= GSConst.VERSION_PARAM %>"></script>

</head>
<body class="body_03">
<html:form action="/file/fil240">
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!-- BODY -->
<input type="hidden" name="CMD" value="">
<input type="hidden" name="cmnMode" value="">
<input type="hidden" name="backDsp" value="">
<input type="hidden" name="backDspLow" value="">
<input type="hidden" name="fil010SelectCabinet" value="">
<input type="hidden" name="fil010SelectDirSid" value="">
<input type="hidden" name="fil070DirSid" value="">
<html:hidden name="fil240Form" property="fil240PageNum" />
<html:hidden name="fil240Form" property="backDspCall" />

<html:hidden property="fileSid" />
<table align="center"  cellpadding="5" cellspacing="0" border="0" width="80%">
<tr>
<td width="100%" align="center">
  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td width="0%"><img src="../file/images/header_project_01.gif" border="0" alt="<gsmsg:write key="cmn.filekanri" />"></td>
  <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.filekanri" /></span></td>
  <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="fil.fil240.1" /> ]</td>
  <td width="100%" class="header_white_bg"></td>
  <td width="0%" class="header_white_bg"><img src="../common/images/header_white_end.gif" border="0" alt=""></td>
  </tr>
  </table>

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td width="50%"></td>
  <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
  <td class="header_glay_bg" width="50%">
  <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('fil240back');">
  </td>
  <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
  </tr>
  </table>

  <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
  <bean:size id="count1" name="fil240Form" property="fil240PageLabel" scope="request" />
  <logic:greaterThan name="count1" value="1">
  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td colspan="7" width="100%" align="right" valign="top" nowrap>
    <img alt="<gsmsg:write key="cmn.previous.page" />" height="20" src="../common/images/arrow2_l.gif" class="img_bottom" width="20" border="0" onClick="buttonPush('arrorw_left');">
    <logic:notEmpty name="fil240Form" property="fil240PageLabel">
      <html:select property="fil240Slt_page1" onchange="changePage1();" styleClass="text_i">
        <html:optionsCollection name="fil240Form" property="fil240PageLabel" value="value" label="label" />
      </html:select>
    </logic:notEmpty>
    <img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" border="0" onClick="buttonPush('arrorw_right');">
  </td>
  </tr>
  </table>
  </logic:greaterThan>

  <logic:notEmpty name="fil240Form" property="callList" scope="request">
  <table id="file-list-table">
  <bean:define id="tdColor" value="" />
  <% String[] tdColors = new String[] {"td_type_file1", "td_type_file2"}; %>
  <tr class="row-heading">
  <th></th>
  <th><gsmsg:write key="fil.98" /></span></th>
  <th><gsmsg:write key="fil.11" /></span></th>
  <th><gsmsg:write key="cmn.update.user" /></span></th>
  <th></th>
  </tr>

  <logic:iterate id="callBean" name="fil240Form" property="callList" scope="request" indexId="idx">
  <% tdColor = tdColors[(idx.intValue() % 2)]; %>
  <tr class="<%= tdColor %>">
  <td width="0%" align="left">

    <logic:notEqual name="callBean" property="fcbMark" value="0">
    <img name="iconImage" width="30" src="../file/fil010.do?CMD=tempview&fil010SelectCabinet=<bean:write name="callBean" property="cabinetSid" />&fil010binSid=<bean:write name="callBean" property="fcbMark" />" name="pctImage<bean:write name="callBean" property="fcbMark" />" >
    </logic:notEqual>

  <logic:notEqual name="callBean" property="directoryKbn" value="0">
    <logic:equal name="callBean" property="fcbMark" value="0">
    <img src="../file/images/cate_icon02_anime.gif" border="0" alt="" style="vertical-align:bottom;">
    </logic:equal>
  </td>
  <td width="70%" align="left">
  <a href="javascript:void(0);" onClick="return fileDl('fileDownload', <bean:write name="callBean" property="binSid" />);" id="fdrsid<bean:write name="callBean" property="directorySid" />">
  <bean:write name="callBean" property="directoryFullPathName" />
  </a>
  </logic:notEqual>

  <br>
  <gsmsg:write key="cmn.update.day.hour" />：<bean:write name="callBean" property="directoryUpdateStr" />
  </td>

  <td width="30%" align="left">
  <bean:write name="callBean" property="ffrUpCmt" filter="false"/>
  </td>

  <td width="0%" align="left" nowrap>
  <logic:equal name="callBean" property="editUsrJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
    <s><bean:write name="callBean" property="editUsrName" /></s>
  </logic:equal>
  <logic:notEqual name="callBean" property="editUsrJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
    <bean:write name="callBean" property="editUsrName" />
  </logic:notEqual>
  </td>

  <td width="0%" align="center">
  <input type="button" value="<gsmsg:write key="fil.18" />" class="btn_base1s" onClick="MoveToFileDetail(<bean:write name="callBean" property="directorySid" />);">
  </td>

  </tr>
  </logic:iterate>
  </table>
  </logic:notEmpty>

  <bean:size id="count2" name="fil240Form" property="fil240PageLabel" scope="request" />
  <logic:greaterThan name="count2" value="1">
  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td colspan="7" width="100%" align="right" valign="top" nowrap>
  <img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" src="../common/images/arrow2_l.gif" width="20" border="0" onClick="buttonPush('arrorw_left');">
  <logic:notEmpty name="fil240Form" property="fil240PageLabel">
  <html:select property="fil240Slt_page2" onchange="changePage2();" styleClass="text_i">
    <html:optionsCollection name="fil240Form" property="fil240PageLabel" value="value" label="label" />
  </html:select>
  </logic:notEmpty>
  <img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" border="0" onClick="buttonPush('arrorw_right');">
  </td>
  </tr>
  </table>
  </logic:greaterThan>

</td>
</tr>
  <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
<tr>
<td width="100%">
  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td width="100%" align="right" valign="top" nowrap>
  <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('fil240back');">
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