<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="bbs.bbs040.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../bulletin/js/bbs040.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../bulletin/css/bulletin.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03" onload="bbs040DateKbn();" onunload="calWindowClose();">

<html:form action="/bulletin/bbs040">
<input type="hidden" name="CMD" value="">
<html:hidden name="bbs040Form" property="bbs010page1" />
<html:hidden name="bbs040Form" property="bbs010forumSid" />
<html:hidden name="bbs040Form" property="bbs060page1" />
<html:hidden name="bbs040Form" property="searchDspID" />
<html:hidden name="bbs040Form" property="threadSid" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!-- BODY -->
<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%">
        <img src="../bulletin/images/header_bulletin_01.gif" border="0" alt="<gsmsg:write key="cmn.bulletin" />"></td>
        <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.bulletin" /></span></td>
        <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="cmn.advanced.search" /> ]</td>
        <td width="0%">
        <img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.bulletin" />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" name="btn_prjadd" class="btn_search_n1" value="<gsmsg:write key="cmn.search" />" onClick="buttonPush('dtlSearch');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backToList');"></span>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <logic:messagesPresent message="false">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
      <td align="left"><span class="TXT02"><html:errors/></span></td>
    </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    </logic:messagesPresent>

    <table class="tl0" align="center" width="100%">

      <tr>
      <td align="center" class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="bbs.3" /></span></td>
      <td class="td_type20">
        <html:select property="bbs040forumSid" styleClass="select_forum">
          <logic:notEmpty name="bbs040Form" property="bbs040forumList">
          <html:optionsCollection name="bbs040Form" property="bbs040forumList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
      </td>
      </tr>

      <tr>
      <td align="center" class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="cmn.keyword" /></span></td>
      <td class="td_type20" nowrap>
        <html:text name="bbs040Form" property="s_key" maxlength="50" style="width:275px;"/>
        <br>
        <span class="text_base2"><html:radio name="bbs040Form" styleId="bbs040keyKbn_01" property="bbs040keyKbn" value="0" /><label for="bbs040keyKbn_01"><gsmsg:write key="cmn.contains.all" />(AND)</label>&nbsp;<html:radio name="bbs040Form" styleId="bbs040keyKbn_02" property="bbs040keyKbn" value="1" /><label for="bbs040keyKbn_02"><gsmsg:write key="cmn.containing.either" />(OR)</label></span>
      </td>
      </tr>

      <tr>
      <td align="center" class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="cmn.search2" /></span></td>
      <td class="td_type20"><span class="text_base2"><html:checkbox name="bbs040Form" styleId="bbs040taisyouThread" property="bbs040taisyouThread" value="1" /><label for="bbs040taisyouThread"><gsmsg:write key="bbs.bbs040.2" /></label>&nbsp;<html:checkbox name="bbs040Form" styleId="bbs040taisyouNaiyou" property="bbs040taisyouNaiyou" value="1" /><label for="bbs040taisyouNaiyou"><gsmsg:write key="cmn.content" /></label></span></td>
      </tr>

      <tr>
      <td align="center" class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="cmn.contributor.name" /></span></td>
      <td class="td_type20"><span class="text_base2"><html:text name="bbs040Form" property="bbs040userName" maxlength="20" style="width:185px;"/></span></td>
      </tr>

      <tr>
      <td align="center" class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="bbs.8" /></span></td>
      <td class="td_type20"><span class="text_base2"><html:radio name="bbs040Form" styleId="bbs040readKbn_01" property="bbs040readKbn" value="0" /><label for="bbs040readKbn_01"><gsmsg:write key="cmn.without.specifying" /></label>&nbsp;<html:radio name="bbs040Form" styleId="bbs040readKbn_02" property="bbs040readKbn" value="1" /><label for="bbs040readKbn_02"><gsmsg:write key="cmn.read.yet" /></label>&nbsp;<html:radio name="bbs040Form" styleId="bbs040readKbn_03" property="bbs040readKbn" value="2" /><label for="bbs040readKbn_03"><gsmsg:write key="cmn.read.already" /></label></span></td>
      </tr>

      <tr>
      <td align="center" class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="cmn.posted" /></span></td>
      <td class="td_type20">

        <span class="text_base2"><html:checkbox name="bbs040Form" styleId="bbs040dateNoKbn" property="bbs040dateNoKbn" value="1" onclick="bbs040DateKbn();" /><label for="bbs040dateNoKbn"><gsmsg:write key="cmn.without.specifying" /></label></span>

          <html:select property="bbs040fromYear">
            <html:optionsCollection name="bbs040Form" property="bbs040yearList" value="value" label="label" />
          </html:select>
          <html:select property="bbs040fromMonth">
            <html:optionsCollection name="bbs040Form" property="bbs040monthList" value="value" label="label" />
          </html:select>
          <html:select property="bbs040fromDay">
            <html:optionsCollection name="bbs040Form" property="bbs040dayList" value="value" label="label" />
          </html:select>
          <input type="button" value="Cal" name="fromCalendarBtn" onclick="wrtCalendar(this.form.bbs040fromDay, this.form.bbs040fromMonth, this.form.bbs040fromYear);" class="calendar_btn">
        ～
          <html:select property="bbs040toYear">
            <html:optionsCollection name="bbs040Form" property="bbs040yearList" value="value" label="label" />
          </html:select>
          <html:select property="bbs040toMonth">
            <html:optionsCollection name="bbs040Form" property="bbs040monthList" value="value" label="label" />
          </html:select>
          <html:select property="bbs040toDay">
            <html:optionsCollection name="bbs040Form" property="bbs040dayList" value="value" label="label" />
          </html:select>
          <input type="button" value="Cal" name="toCalendarBtn" onclick="wrtCalendar(this.form.bbs040toDay, this.form.bbs040toMonth, this.form.bbs040toYear);" class="calendar_btn">

      </td>
      </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td align="right">
          <input type="button" name="btn_prjadd" class="btn_search_n1" value="<gsmsg:write key="cmn.search" />" onClick="buttonPush('dtlSearch');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backToList');"></span>
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