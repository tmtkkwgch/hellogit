<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%
    String limitNo = String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.THREAD_LIMIT_NO);
    String limitYes = String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.THREAD_LIMIT_YES);
%>

<html:html>
<head>
<logic:equal name="bbs070knForm" property="bbs070cmdMode" value="0">
<title>[GroupSession] <gsmsg:write key="bbs.bbs070kn.1" /></title>
</logic:equal>
<logic:notEqual name="bbs070knForm" property="bbs070cmdMode" value="0">
<title>[GroupSession] <gsmsg:write key="bbs.bbs070kn.2" /></title>
</logic:notEqual>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../bulletin/js/bbs070kn.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../bulletin/css/bulletin.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">

<html:form action="/bulletin/bbs070kn">

<input type="hidden" name="helpPrm" value="<bean:write name="bbs070knForm" property="bbs070cmdMode" />">

<input type="hidden" name="CMD" value="">
<html:hidden name="bbs070knForm" property="s_key" />
<html:hidden name="bbs070knForm" property="bbs010page1" />
<html:hidden name="bbs070knForm" property="bbs010forumSid" />
<html:hidden name="bbs070knForm" property="bbs060page1" />
<html:hidden name="bbs070knForm" property="searchDspID" />
<html:hidden name="bbs070knForm" property="bbs040forumSid" />
<html:hidden name="bbs070knForm" property="bbs040keyKbn" />
<html:hidden name="bbs070knForm" property="bbs040taisyouThread" />
<html:hidden name="bbs070knForm" property="bbs040taisyouNaiyou" />
<html:hidden name="bbs070knForm" property="bbs040userName" />
<html:hidden name="bbs070knForm" property="bbs040readKbn" />
<html:hidden name="bbs070knForm" property="bbs040dateNoKbn" />
<html:hidden name="bbs070knForm" property="bbs040fromYear" />
<html:hidden name="bbs070knForm" property="bbs040fromMonth" />
<html:hidden name="bbs070knForm" property="bbs040fromDay" />
<html:hidden name="bbs070knForm" property="bbs040toYear" />
<html:hidden name="bbs070knForm" property="bbs040toMonth" />
<html:hidden name="bbs070knForm" property="bbs040toDay" />
<html:hidden name="bbs070knForm" property="bbs041page1" />
<html:hidden name="bbs070knForm" property="bbs080page1" />
<html:hidden name="bbs070knForm" property="bbs080writeSid" />
<html:hidden name="bbs070knForm" property="bbs080orderKey" />

<html:hidden name="bbs070knForm" property="threadSid" />
<html:hidden name="bbs070knForm" property="bbs070cmdMode" />
<html:hidden name="bbs070knForm" property="bbs070forumName" />
<html:hidden name="bbs070knForm" property="bbs070contributor" />
<html:hidden name="bbs070knForm" property="bbs070title" />
<html:hidden name="bbs070knForm" property="bbs070value" />
<html:hidden name="bbs070knForm" property="bbs070limit" />
<html:hidden name="bbs070knForm" property="bbs070limitFrYear" />
<html:hidden name="bbs070knForm" property="bbs070limitFrMonth" />
<html:hidden name="bbs070knForm" property="bbs070limitFrDay" />
<html:hidden name="bbs070knForm" property="bbs070limitYear" />
<html:hidden name="bbs070knForm" property="bbs070limitMonth" />
<html:hidden name="bbs070knForm" property="bbs070limitDay" />
<html:hidden name="bbs070knForm" property="bbs070limitDisable" />
<html:hidden name="bbs070knForm" property="bbs070limitException" />

<html:hidden name="bbs070knForm" property="bbs070knTmpFileId" />

<html:hidden name="bbs070knForm" property="bbs070BackFlg" />


<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!-- BODY -->
<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%">
        <img src="../bulletin/images/header_bulletin_01.gif" border="0" alt="<gsmsg:write key="cmn.bulletin" />"></td>
        <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.bulletin" /></span></td>
        <logic:equal name="bbs070knForm" property="bbs070cmdMode" value="0">
        <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="bbs.bbs070kn.1" /> ]</td>
        </logic:equal>
        <logic:notEqual name="bbs070knForm" property="bbs070cmdMode" value="0">
        <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="bbs.bbs070kn.2" /> ]</td>
        </logic:notEqual>
        <td width="0%">
        <img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.bulletin" />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('decision');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backToInput');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" class="tl0" border="0" cellpadding="5">

      <tr>
      <td width="20%" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.contributor" /></span></td>
      <td width="80%" class="td_type20">
        <logic:equal name="bbs070knForm" property="bbs070contributorJKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>" >
          <span class="text_base2"><del><bean:write name="bbs070knForm" property="bbs070knviewContributor" /></del></span>
        </logic:equal>
        <logic:notEqual name="bbs070knForm" property="bbs070contributorJKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>" >
          <span class="text_base2"><bean:write name="bbs070knForm" property="bbs070knviewContributor" /></span>
        </logic:notEqual>
      </td>      
      </tr>

      <tr>
      <td width="20%" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="bbs.3" /></span></td>
      <td width="80%" class="td_type20"><span class="text_base2"><bean:write name="bbs070knForm" property="bbs070forumName" /></span></td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.title" /></span></td>
      <td align="left" class="td_type20"><bean:write name="bbs070knForm" property="bbs070title" /></td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.content" /></span></td>
      <td align="left" class="td_type20"><bean:write name="bbs070knForm" property="bbs070knviewvalue" filter="false" /></td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.attached" /></span></td>
      <td align="left" class="td_type20" valign="middle">

        <logic:empty name="bbs070knForm" property="bbs070FileLabelList" scope="request">&nbsp;</logic:empty>

        <logic:notEmpty name="bbs070knForm" property="bbs070FileLabelList" scope="request">
        <table cellpadding="0" cellpadding="0" border="0">

        <logic:iterate id="fileMdl" name="bbs070knForm" property="bbs070FileLabelList" scope="request">
          <tr>
          <td><img src="../common/images/file_icon.gif" alt="<gsmsg:write key="cmn.file" />"></td>
          <td class="menu_bun"><a href="javascript:void(0);" onClick="return fileLinkClick2('<bean:write name="fileMdl" property="value" />');"><span class="text_link"><bean:write name="fileMdl" property="label" /></span></a></td>
          </tr>

        </logic:iterate>

        </table>
        </logic:notEmpty>

      </td>
      </tr>


      <logic:equal name="bbs070knForm" property="bbs070limitDisable" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.THREAD_ENABLE) %>">
      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="bbs.12" /></span></td>
      <td align="left" class="td_type20">
        <logic:equal name="bbs070knForm" property="bbs070limit" value="<%= limitYes %>">
        <bean:define id="frYr" name="bbs070knForm" property="bbs070limitFrYear" type="java.lang.Integer" />
          <bean:define id="yr" name="bbs070knForm" property="bbs070limitYear" type="java.lang.Integer" />

          <div>
          掲示開始日&nbsp;<gsmsg:write key="cmn.year" arg0="<%= String.valueOf(frYr) %>" /><bean:write name="bbs070knForm" property="bbs070limitFrMonth" /><gsmsg:write key="cmn.month" /><bean:write name="bbs070knForm" property="bbs070limitFrDay" /><gsmsg:write key="cmn.day" />
          </div>
          <div>
          掲示終了日&nbsp;<gsmsg:write key="cmn.year" arg0="<%= String.valueOf(yr) %>" /><bean:write name="bbs070knForm" property="bbs070limitMonth" /><gsmsg:write key="cmn.month" /><bean:write name="bbs070knForm" property="bbs070limitDay" /><gsmsg:write key="cmn.day" />
          </div>
        </logic:equal>
        <logic:notEqual name="bbs070knForm" property="bbs070limit" value="<%= limitYes %>">
          <gsmsg:write key="cmn.unlimited" />
        </logic:notEqual>
      </td>
      </tr>
      </logic:equal>
      <logic:notEqual name="bbs070knForm" property="bbs070limitDisable" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.THREAD_ENABLE) %>">
      <logic:equal name="bbs070knForm" property="bbs070limitException" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.THREAD_EXCEPTION) %>">
      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="bbs.12" /></span></td>
      <td align="left" class="td_type20">
        <logic:equal name="bbs070knForm" property="bbs070limit" value="<%= limitYes %>">
        <bean:define id="frYr" name="bbs070knForm" property="bbs070limitFrYear" type="java.lang.Integer" />
          <bean:define id="yr" name="bbs070knForm" property="bbs070limitYear" type="java.lang.Integer" />

          <div>
          掲示開始日&nbsp;<gsmsg:write key="cmn.year" arg0="<%= String.valueOf(frYr) %>" /><bean:write name="bbs070knForm" property="bbs070limitFrMonth" /><gsmsg:write key="cmn.month" /><bean:write name="bbs070knForm" property="bbs070limitFrDay" /><gsmsg:write key="cmn.day" />
          </div>
          <div>
          掲示終了日&nbsp;<gsmsg:write key="cmn.year" arg0="<%= String.valueOf(yr) %>" /><bean:write name="bbs070knForm" property="bbs070limitMonth" /><gsmsg:write key="cmn.month" /><bean:write name="bbs070knForm" property="bbs070limitDay" /><gsmsg:write key="cmn.day" />
          </div>
        </logic:equal>
        <logic:notEqual name="bbs070knForm" property="bbs070limit" value="<%= limitYes %>">
          <gsmsg:write key="cmn.unlimited" />
        </logic:notEqual>
      </td>
      </tr>
      </logic:equal>
      </logic:notEqual>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td align="right">
          <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('decision');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backToInput');">
        </td>
      </tr>
    </table>

  </td>
  </tr>
  </table>

</td>
</tr>
</table>

<IFRAME type="hidden" src="../common/html/damy.html" style="display: none" name="navframe"></IFRAME>

</html:form>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>