<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%
    String maxLengthValue        = String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.MAX_LENGTH_WRITEVALUE);
%>

<html:html>
<head>
<logic:notEqual name="bbs090Form" property="bbs090cmdMode" value="1">
<title>[GroupSession] <gsmsg:write key="bbs.bbs090.1" /></title>
</logic:notEqual>
<logic:equal name="bbs090Form" property="bbs090cmdMode" value="1">
<title>[GroupSession] <gsmsg:write key="bbs.bbs090.2" /></title>
</logic:equal>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../bulletin/js/bbs090.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../bulletin/css/bulletin.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03" onunload="windowClose();" onload="showLengthId($('#inputstr')[0], <%= maxLengthValue %>, 'inputlength');">

<html:form action="/bulletin/bbs090">

<logic:equal name="bbs090Form" property="bbs090cmdMode" value="2">
<input type="hidden" name="helpPrm" value="0">
</logic:equal>
<logic:notEqual name="bbs090Form" property="bbs090cmdMode" value="2">
<input type="hidden" name="helpPrm" value="<bean:write name="bbs090Form" property="bbs090cmdMode" />">
</logic:notEqual>

<input type="hidden" name="CMD" value="">
<html:hidden name="bbs090Form" property="s_key" />
<html:hidden name="bbs090Form" property="bbs010page1" />
<html:hidden name="bbs090Form" property="bbs010forumSid" />
<html:hidden name="bbs090Form" property="bbs060page1" />
<html:hidden name="bbs090Form" property="searchDspID" />
<html:hidden name="bbs090Form" property="bbs040forumSid" />
<html:hidden name="bbs090Form" property="bbs040keyKbn" />
<html:hidden name="bbs090Form" property="bbs040taisyouThread" />
<html:hidden name="bbs090Form" property="bbs040taisyouNaiyou" />
<html:hidden name="bbs090Form" property="bbs040userName" />
<html:hidden name="bbs090Form" property="bbs040readKbn" />
<html:hidden name="bbs090Form" property="bbs040dateNoKbn" />
<html:hidden name="bbs090Form" property="bbs040fromYear" />
<html:hidden name="bbs090Form" property="bbs040fromMonth" />
<html:hidden name="bbs090Form" property="bbs040fromDay" />
<html:hidden name="bbs090Form" property="bbs040toYear" />
<html:hidden name="bbs090Form" property="bbs040toMonth" />
<html:hidden name="bbs090Form" property="bbs040toDay" />
<html:hidden name="bbs090Form" property="bbs041page1" />
<html:hidden name="bbs090Form" property="threadSid" />
<html:hidden name="bbs090Form" property="bbs080page1" />
<html:hidden name="bbs090Form" property="bbs080writeSid" />
<html:hidden name="bbs090Form" property="bbs080orderKey" />

<html:hidden name="bbs090Form" property="bbs090cmdMode" />

<html:hidden name="bbs090Form" property="bbsmainFlg" />

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
        <logic:notEqual name="bbs090Form" property="bbs090cmdMode" value="1">
        <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="bbs.bbs090.1" /> ]</td>
        </logic:notEqual>
        <logic:equal name="bbs090Form" property="bbs090cmdMode" value="1">
        <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="bbs.bbs090.2" /> ]</td>
        </logic:equal>
        <td width="0%">
        <img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.bulletin" />"></td>
      </tr>
    </table>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
        <input type="button" value="ＯＫ" class="btn_ok1" onClick="buttonPush('moveWriteConfirm');">
        <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backWriteList');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <logic:messagesPresent message="false">

    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
         <td align="left"><html:errors/><br></td>
      </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    </logic:messagesPresent>

    <table width="100%" class="tl0" border="0" cellpadding="5">

      <tr>
      <td width="20%" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.contributor" /></span><span class="text_r3">※</span></td>
      <td width="80%" class="td_type20">
        <logic:equal name="bbs090Form" property="bbs090contributorEditKbn" value="0">
          <logic:iterate id="labelMdl" name="bbs090Form" property="bbs090contributorList">
          <html:hidden name="bbs090Form" property="bbs090contributor" />
          <logic:equal name="bbs090Form" property="bbs090contributorJKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>" >
            <span class="text_base2"><del><bean:write name="labelMdl" property="label" /></del></span>
          </logic:equal>
          <logic:notEqual name="bbs090Form" property="bbs090contributorJKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>" >
            <span class="text_base2"><bean:write name="labelMdl" property="label" /></span>
          </logic:notEqual>
          </logic:iterate>
        </logic:equal>
        <logic:equal name="bbs090Form" property="bbs090contributorEditKbn" value="1">
          <html:select property="bbs090contributor">
            <html:optionsCollection name="bbs090Form" property="bbs090contributorList" value="value" label="label" />
          </html:select>
        </logic:equal>
      </td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="bbs.3" /></span></td>
      <td class="td_type20"><span class="text_base2"><bean:write name="bbs090Form" property="bbs090forumName" /></span></td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.title" /></span></td>
      <td align="left" class="td_type20"><span class="text_base2"><bean:write name="bbs090Form" property="bbs090threTitle" /></span></td>
      </tr>
      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.content" /></span><span class="text_r3">※</span></td>
      <td align="left" class="td_type20">
        <textarea name="bbs090value" style="width:898px;" rows="10" onkeyup="showLengthStr(value, <%= maxLengthValue %>, 'inputlength');" id="inputstr"><bean:write name="bbs090Form" property="bbs090value" /></textarea>
        <br>
        <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;/&nbsp;<span class="font_string_count_max"><%= maxLengthValue %>&nbsp;<gsmsg:write key="cmn.character" /></span>
      </td>
      </tr>
      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.attached" /></span></td>
      <td align="left" class="td_type20">
        <input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="buttonPush('delTemp');">
        &nbsp;<input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTemp('bbs090files', 'bulletin', '0', '0');">
        <br>
        <html:select property="bbs090files" styleClass="select01" multiple="true">
          <html:optionsCollection name="bbs090Form" property="bbs090FileLabelList" value="value" label="label" />
        </html:select>
      </td>
      </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td align="right">
        <input type="button" value="ＯＫ" class="btn_ok1" onClick="buttonPush('moveWriteConfirm');">
        <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backWriteList');">
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