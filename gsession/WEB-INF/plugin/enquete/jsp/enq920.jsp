<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ page import="jp.groupsession.v2.enq.GSConstEnquete" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html lang="true">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META http-equiv="Content-Script-Type" content="text/javascript">
<META http-equiv="Content-Style-Type" content="text/css">
<title>[Group Session] <gsmsg:write key="cmn.admin.setting.menu" /></title>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../common/js/jquery.bgiframe.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script type="text/javascript" src="../enquete/js/enquete.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../enquete/js/enq920.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../enquete/css/enquete.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
</head>

<body class="body_03">
<html:form action="/enquete/enq920">
<!-- BODY -->
<input type="hidden" name="CMD">
<html:hidden property="cmd" />
<html:hidden property="backScreen" />

<!-- 検索条件hidden -->
<%@ include file="/WEB-INF/plugin/enquete/jsp/enq010_hiddenParams.jsp" %>

<logic:notEmpty name="enq920Form" property="enq010priority">
<logic:iterate id="svPriority" name="enq920Form" property="enq010priority">
  <input type="hidden" name="enq010priority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq920Form" property="enq010status">
<logic:iterate id="svStatus" name="enq920Form" property="enq010status">
  <input type="hidden" name="enq010status" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq920Form" property="enq010svPriority">
<logic:iterate id="svPriority" name="enq920Form" property="enq010svPriority">
  <input type="hidden" name="enq010svPriority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq920Form" property="enq010svStatus">
<logic:iterate id="svStatus" name="enq920Form" property="enq010svStatus">
  <input type="hidden" name="enq010svStatus" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq920Form" property="enq010statusAnsOver">
<logic:iterate id="svStatusAnsOver" name="enq920Form" property="enq010statusAnsOver">
  <input type="hidden" name="enq010statusAnsOver" value="<bean:write name="svStatusAnsOver" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq920Form" property="enq010svStatusAnsOver">
<logic:iterate id="svStatusAnsOver" name="enq920Form" property="enq010svStatusAnsOver">
  <input type="hidden" name="enq010svStatusAnsOver" value="<bean:write name="svStatusAnsOver" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="enq920Form" property="enq920TypeListToList">
  <logic:iterate id="typeData" name="enq920Form" property="enq920TypeListToList" indexId="idx">
    <input type="hidden" name="enq920TypeList[<%= String.valueOf(idx.intValue()) %>].emnCnt" value='<bean:write name="typeData" property="emnCnt"/>'>
    <input type="hidden" name="enq920TypeList[<%= String.valueOf(idx.intValue()) %>].emnResEnd" value='<bean:write name="typeData" property="emnResEnd"/>' >
    <input type="hidden" name="enq920TypeList[<%= String.valueOf(idx.intValue()) %>].emnOpenEnd" value='<bean:write name="typeData" property="emnOpenEnd"/>' >
  </logic:iterate>
</logic:notEmpty>

<div id="saveTbl">
  <logic:notEmpty name="enq920Form" property="enq920DelListToList">
    <logic:iterate id="delList" name="enq920Form" property="enq920DelListToList" indexId="idx">
      <div class="save">
        <input type="hidden" name="enq920DelList[<%= String.valueOf(idx.intValue()) %>].etpSid" value='<bean:write name="delList" property="etpSid"/>'>
      </div>
    </logic:iterate>
  </logic:notEmpty>
</div>

<!-- header -->
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!-- content -->
<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" width="70%">
  <tr>
  <td align="center">

    <!-- タイトル -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key='cmn.admin.setting' />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="enq.enq900.03"/> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key='cmn.admin.setting' />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt="<gsmsg:write key='cmn.function.btn' />"></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('enq920ok');">
          <input type="button" value="<gsmsg:write key='cmn.back.admin.setting' />" class="btn_back_n3" onClick="buttonPush('enq920back');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt="<gsmsg:write key='cmn.function.btn' />"></td>
      </tr>
    </table>

    <!-- エラーメッセージ -->
    <div style="text-align:left">
    <html:errors/>
    </div>

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%" style="margin-top:15px;">
    <tbody>
      <tr>
        <td align="left" style="white-space:nowrap;">
          <input type="button" class="btn_base0" id="upList" value="<gsmsg:write key='cmn.up' />" name="btn_upper">
          <input type="button" class="btn_base0" id="downList" value="<gsmsg:write key='cmn.down' />" name="btn_downer">
        </td>
        <td align="right" style="white-space:nowrap;">
          <input type="button" class="btn_add_row" value="<gsmsg:write key='enq.02' />" onclick="addRow();">
        </td>
      </tr>
    </tbody>
    </table>

    <table cellpadding="10" cellspacing="0" border="0" width="100%" class="t10">
    <thead>
      <tr>
        <th width="7%" class="table_bg_7D91BD" nowrap></th>
        <th width="44%" class="table_bg_7D91BD" nowrap><span class="text_tlw_10pt"><gsmsg:write key="enq.enq920.01" /></span></th>
        <th width="8%" class="table_bg_7D91BD" nowrap><span class="text_tlw_10pt"><gsmsg:write key="enq.enq920.02" /></span></th>
        <th width="15%" class="table_bg_7D91BD" nowrap><span class="text_tlw_10pt"><gsmsg:write key="enq.enq920.03" /></span></th>
        <th width="15%" class="table_bg_7D91BD" nowrap><span class="text_tlw_10pt"><gsmsg:write key="enq.enq920.04" /></span></th>
        <th width="11%" class="table_bg_7D91BD" nowrap><span class="text_tlw_10pt"><gsmsg:write key="cmn.delete" /></span></th>
      </tr>
    </thead>

    <!-- アンケート種類 -->
    <tbody id="typeTbl">
      <logic:notEmpty name="enq920Form" property="enq920TypeList">
      <% String[] paramName = {"lineNo", "etpSid", "etpDspSeq", "etpName", "emnCnt", "emnResEnd", "emnOpenEnd"}; %>
      <logic:iterate id="subForm" name="enq920Form" property="enq920TypeList" indexId="lineIdx">
      <% String lineNo = String.valueOf(lineIdx.intValue()); %>
      <% String lineFrmName = "enq920TypeList[" + lineNo + "]."; %>
      <% String trNo = "typeRow_" + lineNo; %>
      <% String radioNo = "radioNo_" + lineNo; %>
      <tr id="<%= trNo %>">
        <input type="hidden" name="rowIdx" value="<%= lineNo %>">
        <html:hidden property="<%= lineFrmName + paramName[1] %>" />
        <html:hidden property="<%= lineFrmName + paramName[2] %>" styleClass="etpDspSec" />
        <td class="td_type1 text_mod_10pt rbnChk" align="center" valign="middle" nowrap>
          <label for="<%= radioNo %>" width="100%" height="100%">
          <input type="radio" name="nowRow" id="<%= radioNo %>" value="<%= lineNo %>">
        </td>
        <td class="td_type1 text_mod_10pt" align="left" valign="middle" nowrap>
          <html:text property="<%= lineFrmName + paramName[3] %>" maxlength="100" style="width:100%;" />
        </td>
        <td class="td_type1 text_mod_10pt" align="right" valign="middle" nowrap>
          <logic:greaterEqual name="subForm" property="emnCnt" value="0">
            <bean:write name="subForm" property="emnCnt" /> <gsmsg:write key="cmn.number" />
          </logic:greaterEqual>
        </td>
        <td class="td_type1 text_mod_10pt" align="center" valign="middle" nowrap>
          <logic:notEmpty name="subForm" property="emnResEnd">
            <bean:write name="subForm" property="emnResEnd" /> <gsmsg:write key="enq.03" />
          </logic:notEmpty>

        </td>
        <td class="td_type1 text_mod_10pt" align="center" valign="middle" nowrap>
          <logic:notEmpty name="subForm" property="emnOpenEnd">
            <bean:write name="subForm" property="emnOpenEnd" /> <gsmsg:write key="enq.03" />
          </logic:notEmpty>
        </td>
        <td class="td_type1 text_mod_10pt" align="center" valign="middle" nowrap>
          <logic:lessEqual name="subForm" property="emnCnt" value="0">
            <input type="button" class="btn_dell_n3" name="enqTypeDelBtn" value="<gsmsg:write key='cmn.delete' />" title="<gsmsg:write key='cmn.delete' />" id='<%= lineNo %>:<bean:write name="subForm" property="etpSid"/>'>
          </logic:lessEqual>
        </td>
      </tr>
      </logic:iterate>
      </logic:notEmpty>
    </tbody>

    </table>

    <img src="../common/images/spacer.gif" width="1" height="10" alt="<gsmsg:write key='cmn.spacer' />" border="0">

    <table cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td align="right">
          <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('enq920ok');">
          <input type="button" value="<gsmsg:write key='cmn.back.admin.setting' />" class="btn_back_n3" onClick="buttonPush('enq920back');">
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

<!-- アンケート種類削除ダイアログ -->
<div id="dialogDeleteOk" title="アンケート種類名削除確認" style="display:none;">
  <p>
    <div>
      <span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>
      <b>種類名を削除します。よろしいですか？</b>
    </div>
  </p>
</div>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>