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
<title>[Group Session] <gsmsg:write key="main.man028.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>


<body class="body_03"　onunload="windowClose();">
<html:form action="/main/man028">
<input type="hidden" name="CMD" value="">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="50%" class="header_ktool_bg_text2">[ <gsmsg:write key="main.holiday.setting" /> <gsmsg:write key="cmn.import" /> ]</td>
    <td width="50%" class="header_ktool_bg" align="right"></td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.import" />" class="btn_csv_n1" onClick="buttonPush('import');">
      <input type="button" value="<gsmsg:write key="main.man028.2" />" class="btn_back_n3" onClick="buttonPush('backHolSet');">
   <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

  <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

  <logic:messagesPresent message="false">
    <span class="TXT02"><html:errors/></span>
  </logic:messagesPresent>

    <table width="100%" cellpadding="5" cellspacing="0" class="tl0">
    <tr>
      <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.capture.file" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
      <td align="left" class="td_wt" width="80%" nowrap>
        <input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTemp('man028SelectFiles', '<%= jp.groupsession.v2.cmn.GSConst.PLUGINID_MAIN %>', '1');">
        &nbsp;<input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="buttonPush('delete');"><br>

      <html:select property="man028SelectFiles" styleClass="select01" multiple="false" size="1">
        <html:optionsCollection name="man028Form" property="man028FileLabelList" value="value" label="label" />
      </html:select>

        &nbsp;
        <span class="text_base">
        <% jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage(); %>
        <% String csvFileMsg = "<a href=\"../main/man028.do?CMD=man028_sample\">" + gsMsg.getMessage(request, "cmn.capture.csvfile") + "</a>"; %>
          *<gsmsg:write key="cmn.plz.specify2" arg0="<%= csvFileMsg %>" />
        </span><br>
      </td>
    </tr>
    <tr>
      <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.overwrite" /></span></td>
      <td align="left" class="td_wt" width="80%" nowrap>
      <html:checkbox name="man028Form" property="man028updateFlg" value="1" styleId="updateFlg" /><label for="updateFlg" class="text_base"><gsmsg:write key="cmn.holidays.same.date.override" /></label>      </td>
    </tr>
    </table>
  </td>
  </tr>
  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">
      <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td width="100%" align="right">
      <input type="button" value="<gsmsg:write key="cmn.import" />" class="btn_csv_n1" onclick="buttonPush('import');">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="main.man028.2" />" onClick="buttonPush('backHolSet');"></td>
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