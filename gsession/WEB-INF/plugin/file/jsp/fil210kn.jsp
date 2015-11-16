<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<%
   String crtKbnAdmin = String.valueOf(jp.groupsession.v2.fil.GSConstFile.CREATE_CABINET_PERMIT_ADMIN);
   String crtKbnGroup = String.valueOf(jp.groupsession.v2.fil.GSConstFile.CREATE_CABINET_PERMIT_GROUP);
   String crtKbnUser = String.valueOf(jp.groupsession.v2.fil.GSConstFile.CREATE_CABINET_PERMIT_USER);
   String crtKbnNo = String.valueOf(jp.groupsession.v2.fil.GSConstFile.CREATE_CABINET_PERMIT_NO);
   String lockKbnOff = String.valueOf(jp.groupsession.v2.fil.GSConstFile.LOCK_KBN_OFF);
   String lockKbnOn = String.valueOf(jp.groupsession.v2.fil.GSConstFile.LOCK_KBN_ON);
   String verKbnOff = String.valueOf(jp.groupsession.v2.fil.GSConstFile.VERSION_KBN_OFF);
   String verKbnOn = String.valueOf(jp.groupsession.v2.fil.GSConstFile.VERSION_KBN_ON);
%>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.filekanri" /> <gsmsg:write key="cmn.preferences.kn" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/file.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/dtree.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
</head>

<body class="body_03">

<html:form action="/file/fil210kn">
<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="backDsp" />
<html:hidden property="fil210CrtKbn" />
<html:hidden property="fil210SltGroup" />
<html:hidden property="fil210AllSelect" />
<html:hidden property="fil210FileSize" />
<html:hidden property="fil210SaveDays" />
<html:hidden property="fil210LockKbn" />
<html:hidden property="fil210VerKbn" />
<html:hidden property="filSearchWd" />
<html:hidden property="fil010SelectCabinet" />
<html:hidden property="fil010SelectDirSid" />

<logic:notEmpty name="fil210knForm" property="fil040SelectDel" scope="request">
  <logic:iterate id="del" name="fil210knForm" property="fil040SelectDel" scope="request">
    <input type="hidden" name="fil040SelectDel" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil210knForm" property="fil210SvUsers">
<logic:iterate id="param" name="fil210knForm" property="fil210SvUsers">
  <input type="hidden" name="fil210SvUsers" value="<bean:write name="param" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil210knForm" property="fil210SvGroups">
<logic:iterate id="param" name="fil210knForm" property="fil210SvGroups">
  <input type="hidden" name="fil210SvGroups" value="<bean:write name="param" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil210knForm" property="fil010SelectDelLink" scope="request">
  <logic:iterate id="del" name="fil210knForm" property="fil010SelectDelLink" scope="request">
    <input type="hidden" name="fil010SelectDelLink" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!-- BODY -->

<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">

  <table  cellpadding="0" cellspacing="0"width="70%">
  <tr>
  <td align="center">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.filekanri" />　<gsmsg:write key="cmn.preferences.kn" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" class="btn_ok1" value="OK" onclick="buttonPush('fil210knok');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('fil210knback');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <logic:messagesPresent message="false">
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr><td width="100%"><html:errors /></td></tr>
    </table>
    </logic:messagesPresent>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="10" cellspacing="0" border="0" width="100%" class="tl_u2">

    <tr>
    <td valign="middle" align="left" class="td_sub_title3" nowrap>
      <span class="text_bb1"><gsmsg:write key="fil.28" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt" >

      <logic:equal name="fil210knForm" property="fil210CrtKbn" value="<%= crtKbnAdmin %>">
      <span class="text_base7"><gsmsg:write key="fil.29" /></span>
      </logic:equal>

      <logic:equal name="fil210knForm" property="fil210CrtKbn" value="<%= crtKbnGroup %>">
        <span class="text_base7"><gsmsg:write key="fil.fil210kn.1" /><br>
　　　　　　　<logic:notEmpty name="fil210knForm" property="fil210knGroupNameList">
  　　　　　　<logic:iterate id="groupName" name="fil210knForm" property="fil210knGroupNameList">
          <br>・<bean:write name="groupName" />
        </logic:iterate>
        </logic:notEmpty>
        </span>
      </logic:equal>

      <logic:equal name="fil210knForm" property="fil210CrtKbn" value="<%= crtKbnUser %>">
        <span class="text_base7"><gsmsg:write key="fil.fil210kn.2" /><br>
　　　　　　　<logic:notEmpty name="fil210knForm" property="fil210knUserNameList">
  　　　　　　<logic:iterate id="userName" name="fil210knForm" property="fil210knUserNameList">
          <br>・<bean:write name="userName" />
        </logic:iterate>
        </logic:notEmpty>
        </span>
      </logic:equal>

      <logic:equal name="fil210knForm" property="fil210CrtKbn" value="<%= crtKbnNo %>">
      <span class="text_base7"><gsmsg:write key="fil.30" /> </span>
      </logic:equal>

    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="td_sub_title3" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="fil.31" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">
      <span class="text_base"><bean:write name="fil210knForm" property="fil210knFileSize" /></span><br>
      <br>
      <span class="text_base">※<gsmsg:write key="fil.32" /></span>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="td_sub_title3" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="fil.fil210kn.3" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">
      <span class="text_base"><bean:write name="fil210knForm" property="fil210knSaveDays" /></span><br>
      <br>
      <span class="text_base">※<gsmsg:write key="fil.33" /></span><br>
      <span class="text_base">※<gsmsg:write key="fil.fil210.5" /></span><br>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="td_sub_title3" nowrap>
      <span class="text_bb1"><gsmsg:write key="fil.34" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt" >
      <span class="text_base7">
      <logic:equal name="fil210knForm" property="fil210LockKbn" value="<%= lockKbnOff %>"><gsmsg:write key="fil.107" />。</logic:equal>
      <logic:equal name="fil210knForm" property="fil210LockKbn" value="<%= lockKbnOn %>"><gsmsg:write key="fil.108" /></logic:equal>
      </span>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="td_sub_title3" nowrap>
      <span class="text_bb1"><gsmsg:write key="fil.69" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt" >
      <span class="text_base7">
      <logic:equal name="fil210knForm" property="fil210VerKbn" value="<%= verKbnOff %>"><gsmsg:write key="fil.107" /></logic:equal>
      <logic:equal name="fil210knForm" property="fil210VerKbn" value="<%= verKbnOn %>"><gsmsg:write key="fil.108" /></logic:equal>
      </span>
    </td>
    </tr>

    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" class="btn_ok1" value="OK" onclick="buttonPush('fil210knok');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('fil210knback');">
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