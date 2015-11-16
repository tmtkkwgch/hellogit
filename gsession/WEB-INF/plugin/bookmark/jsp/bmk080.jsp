<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.entry.rankings" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../bookmark/js/bmk080.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../bookmark/css/bookmark.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">

<html:form action="/bookmark/bmk080">
<input type="hidden" name="CMD" value="">

<logic:notEmpty name="bmk080Form" property="bmk010delInfSid" scope="request">
<logic:iterate id="item" name="bmk080Form" property="bmk010delInfSid" scope="request">
  <input type="hidden" name="bmk010delInfSid" value="<bean:write name="item"/>">
</logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/bookmark/jsp/bmk010_hiddenParams.jsp" %>

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
        <td width="0%"><img src="../bookmark/images/header_link01.gif" border="0" alt="<gsmsg:write key="bmk.43" />"></td>
        <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="bmk.43" /></span></td>
        <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="cmn.entry.rankings" /> ]</td>
        <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="bmk.43" />"></td>
      </tr>
    </table>

    <table cellpadding="5" cellspacing="0" width="100%">
      <tr>
        <td align="right">
          <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backPage');"></span>
        </td>
      </tr>
    </table>

    <logic:messagesPresent message="false">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr><td align="left"><span class="TXT02"><html:errors/></span></td></tr>
    </table>
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
    </logic:messagesPresent>

    <logic:notEmpty name="bmk080Form" property="bmk080ResultList">
    <bean:size id="count1" name="bmk080Form" property="bmk080PageLabelList" scope="request" />
    <logic:greaterThan name="count1" value="1">
    <table width="100%" cellpadding="5" cellspacing="0">
      <td align="right">
        <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom"height="20" width="20" onClick="buttonPush('prevPage');">
        <html:select property="bmk080PageTop" onchange="changePage(0);" styleClass="text_i">
          <html:optionsCollection name="bmk080Form" property="bmk080PageLabelList" value="value" label="label" />
        </html:select>
        <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom"height="20" width="20" onClick="buttonPush('nextPage');"></td>
      </tr>
    </table>
    </logic:greaterThan>
    </logic:notEmpty>

    <table width="100%" cellpadding="3" cellspacing="0" border="0" align="center">

      <tr>
      <th width="100%" class="table_bg_7D91BD" colspan="4"><span class="text_tlw"><gsmsg:write key="cmn.entry.rankings" /></span></th>

      <logic:notEmpty name="bmk080Form" property="bmk080ResultList">
      <logic:iterate id="resultMdl" name="bmk080Form" property="bmk080ResultList" indexId="idx">
      <bean:define id="tdColor" value="" />
      <% String[] tdColors = new String[] {"bmk_td1", "bmk_td2"}; %>
      <% tdColor = tdColors[(idx.intValue() % 2)]; %>

      <tr>
      <!-- ランキング順位 -->
      <td width="5%" align="right" class="<%= tdColor %>" nowrap>
      <span class="bmk_ranking_text"><bean:write name="resultMdl" property="ranking" /></span>
      </td>

      <!-- タイトル・URL -->
      <td width="60%" valign="middle" class="<%= tdColor %>">
        <table width="100%" cellpadding="0" cellspacing="3" border="0">
        <tr>
        <td align="left">
        <a href="<bean:write name="resultMdl" property="bmuUrl" />" target="_blank">
        <span class="text_link">
        <bean:write name="resultMdl" property="bmuTitle" />
        </span>
        </a>
        </td>
        </tr>
        <tr>
        <td class="text_base2">
        <span class="bmk_description">
          <logic:notEmpty name="resultMdl" property="bmkUrlDspList">
          <logic:iterate id="urlDsp" name="resultMdl" property="bmkUrlDspList" indexId="idx3">
          <% if (idx3 > 0) { %> <br> <% } %>
          <bean:write name="urlDsp"/>
          </logic:iterate>
          </logic:notEmpty>
        </span>
        </td>
        </tr>
        </table>
      </td>

      <!-- 登録人数 -->
      <td width="20%" align="right" valign="middle" class="<%= tdColor %>" nowrap>
        <bean:define id="addCount" name="resultMdl" property="userCount" type="java.lang.Integer" />
        <% String addCntMsg = "<b>" + String.valueOf(addCount.intValue()) + "</b>"; %>
        <a href="#" onClick="selPerCount(<bean:write name="resultMdl" property="bmuSid" />);" style="color:#ff0000;background-color:#ffcccc;"><font size="-1"><gsmsg:write key="bmk.22" arg0="<%= addCntMsg %>" /></a>
      </td>

      <!-- 追加ボタン・追加済み -->
      <td class="<%= tdColor %>" width="15%" align="center" nowrap>
      <logic:equal name="resultMdl" property="tourokuCount" value="0">
        <input type="button" value="<gsmsg:write key="cmn.add" />" onClick="bmkAdd(<bean:write name="resultMdl" property="bmuSid" />);" class="btn_add_n1">
      </logic:equal>

      <logic:greaterThan name="resultMdl" property="tourokuCount" value="0">
        <span class="text_tsuikazumi"><gsmsg:write key="cmn.add" /><br><gsmsg:write key="cmn.pre" /></span>
      </logic:greaterThan>
      </td>
      </tr>

      </logic:iterate>
      </logic:notEmpty>
      </td>
      </tr>

    </table>

    <logic:notEmpty name="bmk080Form" property="bmk080ResultList">
    <bean:size id="count2" name="bmk080Form" property="bmk080PageLabelList" scope="request" />
    <logic:greaterThan name="count2" value="1">
    <table width="100%" cellpadding="5" cellspacing="0">
      <td align="right">
        <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('prevPage');">
        <html:select property="bmk080PageBottom" onchange="changePage(1);" styleClass="text_i">
          <html:optionsCollection name="bmk080Form" property="bmk080PageLabelList" value="value" label="label" />
        </html:select>
        <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('nextPage');"></td>
      </tr>
    </table>
    </logic:greaterThan>
    </logic:notEmpty>

    <table cellpadding="5" cellspacing="0" width="100%">
      <tr>
        <td align="right">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backPage');"></span>
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