<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="fil.47" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/file.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/dtree.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/dtree.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/file.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03">

<html:form action="/file/fil090kn">
<input type="hidden" name="CMD" value="">
<html:hidden property="backDsp" />
<html:hidden property="selectDir" />
<html:hidden property="sepKey" />
<html:hidden property="fileSid" />
<html:hidden property="moveToDir" />

<html:hidden property="fil010SelectCabinet" />
<html:hidden property="fil010SelectDirSid" />
<html:hidden property="fil090SltDirPath" />

<html:hidden property="fil090Mode" />
<html:hidden property="fil090DirSid" />
<html:hidden property="fil090SelectPluralKbn" />
<html:hidden property="fil090EditId" />

<logic:notEmpty name="fil090knForm" property="treeFormLv0" scope="request">
  <logic:iterate id="lv0" name="fil090knForm" property="treeFormLv0" scope="request">
    <input type="hidden" name="treeFormLv0" value="<bean:write name="lv0"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil090knForm" property="treeFormLv1" scope="request">
  <logic:iterate id="lv1" name="fil090knForm" property="treeFormLv1" scope="request">
    <input type="hidden" name="treeFormLv1" value="<bean:write name="lv1"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil090knForm" property="treeFormLv2" scope="request">
  <logic:iterate id="lv2" name="fil090knForm" property="treeFormLv2" scope="request">
    <input type="hidden" name="treeFormLv2" value="<bean:write name="lv2"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil090knForm" property="treeFormLv3" scope="request">
  <logic:iterate id="lv3" name="fil090knForm" property="treeFormLv3" scope="request">
    <input type="hidden" name="treeFormLv3" value="<bean:write name="lv3"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil090knForm" property="treeFormLv4" scope="request">
  <logic:iterate id="lv4" name="fil090knForm" property="treeFormLv4" scope="request">
    <input type="hidden" name="treeFormLv4" value="<bean:write name="lv4"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil090knForm" property="treeFormLv5" scope="request">
  <logic:iterate id="lv5" name="fil090knForm" property="treeFormLv5" scope="request">
    <input type="hidden" name="treeFormLv5" value="<bean:write name="lv5"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil090knForm" property="treeFormLv6" scope="request">
  <logic:iterate id="lv6" name="fil090knForm" property="treeFormLv6" scope="request">
    <input type="hidden" name="treeFormLv6" value="<bean:write name="lv6"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil090knForm" property="treeFormLv7" scope="request">
  <logic:iterate id="lv7" name="fil090knForm" property="treeFormLv7" scope="request">
    <input type="hidden" name="treeFormLv7" value="<bean:write name="lv7"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil090knForm" property="treeFormLv8" scope="request">
  <logic:iterate id="lv8" name="fil090knForm" property="treeFormLv8" scope="request">
    <input type="hidden" name="treeFormLv8" value="<bean:write name="lv8"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil090knForm" property="treeFormLv9" scope="request">
  <logic:iterate id="lv9" name="fil090knForm" property="treeFormLv9" scope="request">
    <input type="hidden" name="treeFormLv9" value="<bean:write name="lv9"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil090knForm" property="treeFormLv10" scope="request">
  <logic:iterate id="lv10" name="fil090knForm" property="treeFormLv10" scope="request">
    <input type="hidden" name="treeFormLv10" value="<bean:write name="lv10"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil090knForm" property="fil040SelectDel" scope="request">
  <logic:iterate id="del" name="fil090knForm" property="fil040SelectDel" scope="request">
    <input type="hidden" name="fil040SelectDel" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil090knForm" property="fil040SelectDel" scope="request">
  <logic:iterate id="del" name="fil090Form" property="fil040SelectDel" scope="request">
    <input type="hidden" name="fil040SelectDel" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<input type="hidden" name="helpPrm" value="<bean:write name="fil090knForm" property="fil090Mode" />">
<!-- BODY -->

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
    <logic:equal name="fil090knForm" property="fil090Mode" value="0">
      [ <gsmsg:write key="fil.fil090kn.1" /> ]
    </logic:equal>
    <logic:equal name="fil090knForm" property="fil090Mode" value="1">
      [ <gsmsg:write key="fil.97" /> ]
    </logic:equal>
    <logic:equal name="fil090knForm" property="fil090Mode" value="2">
      [ <gsmsg:write key="fil.117" /> ]
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
    <input type="button" class="btn_ok1" value="OK" onclick="buttonPush('fil090knok');">
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('fil090knback');">
    </td>

    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
<html:messages id="msg" message="false" >
    <p style="text-align:left;"><span class="text_error"><bean:write name="msg" ignore="true" /></span></p>
</html:messages>

    <table width="100%" class="tl0 prj_tbl_base" border="0" cellpadding="5">

    <tr>
    <td class="td_sub_title" nowrap>
    <span class="text_bb1"><gsmsg:write key="cmn.update.user" /></span>
    </td>
    <td align="left" class="td_sub_detail">
    <span class="text_base_prj"><bean:write name="fil090knForm" property="fil090knEditName" /></span>
    </td>
    </tr>

    <logic:equal name="fil090knForm" property="fil090Mode" value="0">
    <tr>
    <td class="td_sub_title" nowrap>
    <span class="text_bb1"><gsmsg:write key="fil.21" /></span>
    </td>
    <td align="left" class="td_sub_detail">
    <span class="text_base_prj"><bean:write name="fil090knForm" property="fil090DirName" /></span>
    </td>
    </tr>

    <tr>
    <td class="td_sub_title" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.memo" /></span></td>
    <td class="td_sub_detail" align="left" width="80%">
    <span class="text_base_prj"><bean:write name="fil090knForm" property="fil090Biko" filter="false" /></span>
    </td>
    </tr>

    </logic:equal>

    <logic:equal name="fil090knForm" property="fil090Mode" value="1">
    <tr>
    <td class="td_sub_title" nowrap>
    <span class="text_bb1"><gsmsg:write key="fil.9" /></span>
    </td>
    <td align="left" class="td_sub_detail">

      <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
      <td width="0%" align="left" valign="top" nowrap>

      <logic:notEmpty name="fil090knForm" property="fil090FileLabelList">
      <logic:iterate id="file" name="fil090knForm" property="fil090FileLabelList">
        <a href="#" onclick="fileDl('fileDownload', '<bean:write name="file" property="value" />');">
        <span class="text_link"><bean:write name="file" property="label" /></span>
        </a>
        <br>
      </logic:iterate>
      </logic:notEmpty>

      </td>
      </tr>
      </table>

    </td>
    </tr>

    <tr>
    <td class="td_sub_title" nowrap>
    <span class="text_bb1"><gsmsg:write key="fil.5" /></span>
    </td>
    <td align="left" class="td_sub_detail">
    <span class="text_base_prj">
      <logic:equal name="fil090knForm" property="fil090VerKbn" value="0"><gsmsg:write key="fil.fil090kn.2" /></logic:equal>
      <logic:notEqual name="fil090knForm" property="fil090VerKbn" value="0">
      <bean:define id="ver" name="fil090knForm" property="fil090VerKbn" type="java.lang.String" />
      <gsmsg:write key="fil.generations" arg0="<%= ver %>" />
      </logic:notEqual>
    </span>
    </td>
    </tr>

    <tr>
    <td class="td_sub_title" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.memo" /></span></td>
    <td class="td_sub_detail" align="left" width="80%">
    <input type="hidden" name="prj180Biko" value="">
    <span class="text_base_prj"><bean:write name="fil090knForm" property="fil090Biko" filter="false" /></span>
    </td>
    </tr>

    </logic:equal>


    <logic:equal name="fil090knForm" property="fil090Mode" value="2">
    <tr>
    <td class="td_sub_title" nowrap>
    <span class="text_bb1"><gsmsg:write key="fil.119" /></span>
    </td>
    <td align="left" class="">
      <table width="60%" cellpadding="0" cellspacing="5" border="0" class="tl_u2">

      <logic:notEmpty name="fil090knForm" property="fil090FolderNameList">
      <tr>
      <td width="40%" class="td_sub_title" align="center"><span class="file_text_bb1"><gsmsg:write key="cmn.folder" /></span></td>
      </tr>
      <tr>
      <td align="left" class="td_type1">
        <logic:iterate id="folderName" name="fil090knForm" property="fil090FolderNameList">
          <span class="text_base_prj"><bean:write name="folderName" /></span>
          <br>
        </logic:iterate>
      </td>
      </tr>
      </logic:notEmpty>

      <logic:notEmpty name="fil090knForm" property="fil090FileLabelList">
      <tr>
      <td width="40%" class="td_sub_title" align="center"><span class="file_text_bb1"><gsmsg:write key="cmn.file" /></span></td>
      </tr>
      <tr>
      <td align="left" class="td_type1">
        <logic:iterate id="file" name="fil090knForm" property="fil090FileLabelList">
          <a href="#" onclick="fileDl('fileDownload', '<bean:write name="file" property="value" />');">
          <span class="text_link"><bean:write name="file" property="label" /></span>
          </a>
          <br>
        </logic:iterate>
      </td>
      </tr>
      </logic:notEmpty>
      </table>

    </td>
    </tr>
    </logic:equal>


    <tr>
    <td class="td_sub_title" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="fil.75" /></span></td>
    <td class="td_sub_detail" align="left" width="80%">
    <span class="text_base_prj">
    <div id="moveDirName">
    <img src="../file/images/icon_folder.gif" border="0" alt="" style="vertical-align:middle;">
    <bean:write name="fil090knForm" property="fil090SltDirPath" />
    </div>
    </span>
    </td>
    </tr>

    </table>

  </td>
  </tr>
  <tr>
  <td><img src="../project/images/spacer.gif" width="1px" height="10px" border="0"></td>
  </tr>

  <tr>
  <td align="right">
    <input type="button" class="btn_ok1" value="OK" onclick="buttonPush('fil090knok');">
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('fil090knback');">
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