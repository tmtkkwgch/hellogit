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

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script type="text/javascript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src='../common/js/jquery-ui-1.8.16/jquery-1.6.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script type="text/javascript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/userpopup.js?<%= GSConst.VERSION_PARAM %>"></script>

<!--[if IE]><script type="text/javascript" src="../common/js/graph_circle_1_0_2/excanvas/excanvas.js"></script><![endif]-->
<script type="text/javascript" src="../common/js/jplot/jquery.jqplot.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/jplot/jqplot.barRenderer.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/jplot/jqplot.pieRenderer.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/jplot/jqplot.highlighter.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/jplot/jqplot.categoryAxisRenderer.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/jplot/jqplot.canvasAxisTickRenderer.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/jplot/jqplot.canvasTextRenderer.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/jplot/jqplot.pointLabels.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/jplot/jqplot.cursor.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src='../common/js/jquery-ui-1.8.16/ui/jquery-ui-1.8.16.custom.js?<%= GSConst.VERSION_PARAM %>'></script>
<script type="text/javascript" src='../common/js/jquery-ui-1.8.16/ui/jquery.ui.datepicker.js?<%= GSConst.VERSION_PARAM %>'></script>
<script type="text/javascript" src='../common/js/jquery-ui-1.8.16/ui/i18n/jquery.ui.datepicker-ja.js?<%= GSConst.VERSION_PARAM %>'></script>

<script type="text/javascript" src="../enquete/js/enq330.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/glayer.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../enquete/css/enquete.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<link rel=stylesheet href='../common/js/jplot/css/jquery.jqplot.min.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<title>[GroupSession] <gsmsg:write key="enq.plugin" /></title>
</head>
<body class="body_03">
<html:form action="/enquete/enq330">
<input type="hidden" name="CMD" value="">
<input type="hidden" name="enq330graphBf" value="<bean:write name="enq330Form" property="enq330graph" />">

<html:hidden property="ansEnqSid" />
<html:hidden property="enq310selectQue" />
<html:hidden property="enq310selectQueSub" />
<html:hidden property="enq330queKbn" />
<html:hidden property="enq330sortKey" />
<html:hidden property="enq330order" />
<html:hidden property="enq330viewDetailFlg" />
<html:hidden property="enq330scrollQuestonFlg" />
<html:hidden property="enq330svGroup" />
<html:hidden property="enq330svUser" />
<html:hidden property="enq330svAnsText" />
<html:hidden property="enq330svAnsNumKbn" />
<html:hidden property="enq330svAnsNumFrom" />
<html:hidden property="enq330svAnsNumTo" />
<html:hidden property="enq330svAnsDateFromYear" />
<html:hidden property="enq330svAnsDateFromMonth" />
<html:hidden property="enq330svAnsDateFromDay" />
<html:hidden property="enq330svAnsDateToYear" />
<html:hidden property="enq330svAnsDateToMonth" />
<html:hidden property="enq330svAnsDateToDay" />
<logic:notEmpty name="enq330Form" property="enq330svAns">
<logic:iterate id="svAns" name="enq330Form" property="enq330svAns">
  <input type="hidden" name="enq330svAns" value="<bean:write name="svAns" />">
</logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/enquete/jsp/enq010_hiddenParams.jsp" %>
<input type="hidden" name="helpPrm" value='<bean:write name="enq330Form" property="enq330queKbn" /><bean:write name="enq330Form" property="enq310anony" />'>

<logic:notEmpty name="enq330Form" property="enq010priority">
<logic:iterate id="svPriority" name="enq330Form" property="enq010priority">
  <input type="hidden" name="enq010priority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq330Form" property="enq010status">
<logic:iterate id="svStatus" name="enq330Form" property="enq010status">
  <input type="hidden" name="enq010status" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq330Form" property="enq010svPriority">
<logic:iterate id="svPriority" name="enq330Form" property="enq010svPriority">
  <input type="hidden" name="enq010svPriority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq330Form" property="enq010svStatus">
<logic:iterate id="svStatus" name="enq330Form" property="enq010svStatus">
  <input type="hidden" name="enq010svStatus" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq330Form" property="enq010statusAnsOver">
<logic:iterate id="svStatusAnsOver" name="enq330Form" property="enq010statusAnsOver">
  <input type="hidden" name="enq010statusAnsOver" value="<bean:write name="svStatusAnsOver" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq330Form" property="enq010svStatusAnsOver">
<logic:iterate id="svStatusAnsOver" name="enq330Form" property="enq010svStatusAnsOver">
  <input type="hidden" name="enq010svStatusAnsOver" value="<bean:write name="svStatusAnsOver" />">
</logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="70%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%">
      <img src="../enquete/images/header_enquete_01.gif" border="0" alt="<gsmsg:write key="enq.plugin" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="enq.plugin" /></span></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap>[ <gsmsg:write key="enq.57" /> ]</td>
    <td width="100%" class="header_white_bg">
    </td>
    <td width="0%">
    <img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key='cmn.header' />"></td>
    </tr>
    </table>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tbody><tr>
    <td width="50%">
    </td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt="<gsmsg:write key='cmn.header' />"></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onclick="buttonPush('enq330back');" >
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt="<gsmsg:write key='cmn.header' />"></td>
    </tr>
    </tbody></table>

    <!-- エラーメッセージ -->
    <div style="text-align:left">
    <html:errors/>
    </div>

  </td>
  </tr>
  </table>
</td>
</tr>

<tr id="enq330detailArea1">
<td width="100%">
  <table width="100%">
  <tr>
    <td width="13%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="cmn.title"/></td>
    <td width="87%" class="td_type20">
    <table width="99%">
      <tr>
        <td width="99%"><span class="text_base2"><bean:write name="enq330Form" property="enq310enqTitle" />&nbsp;&nbsp;<bean:write name="enq330Form" property="enq330question" /></span></td>
        <td width="1%">
          <input type="button" value="<gsmsg:write key='cmn.detail' />" class="btn_base1s_3" onClick="enq330changeBasicDetail(1);" id="enq330detailBtn0">
        </td>
      </tr>
    </table>
    </td>
  </tr>
  </table>
</td>
</tr>

<tr>
<td width="100%" align="center" class="wrap_table">

<table class="clear_table" width="100%">
  <tr>

    <td class="content_area">
    <table cellpadding="5" width="100%" class="tl0" style="margin-bottom: 25px;" id="enq330detailArea">
    <tbody>
    <tr>
    <td width="13%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="cmn.title"/></td>
    <td width="87%" class="td_type20" colspan="3">
      <table width="99%">
      <tr>
      <td width="99%"><span class="text_base2"><bean:write name="enq330Form" property="enq310enqTitle" />&nbsp;&nbsp;<bean:write name="enq330Form" property="enq330question" /></span></td>
      <td width="1%">
        <input type="button" value="<gsmsg:write key='cmn.close' />" class="btn_base1s_3" onClick="enq330changeBasicDetail(0);" id="enq330detailBtn1">
      </td>
      </tr>
      </table>
    </td>
    </tr>
    <!-- 基本情報 重要度 -->
    <tr>
    <td width="13%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.24" /></td>
    <td width="25%" class="td_type20" nowrap>
    <span class="text_base2">
       <logic:equal name="enq330Form" property="enq310priority" value="0">
         <img src="../enquete/images/star_blue_16.png" class="star" border="0" alt="<gsmsg:write key="enq.33" />">
         <img src="../enquete/images/star_white_16.png" class="star" border="0" alt="<gsmsg:write key="enq.33" />">
         <img src="../enquete/images/star_white_16.png" class="star" border="0" alt="<gsmsg:write key="enq.33" />">
       </logic:equal>
       <logic:equal name="enq330Form" property="enq310priority" value="1">
         <img src="../enquete/images/star_gold_16.png" class="star" border="0" alt="<gsmsg:write key="enq.34" />">
         <img src="../enquete/images/star_gold_16.png" class="star" border="0" alt="<gsmsg:write key="enq.34" />">
         <img src="../enquete/images/star_white_16.png" class="star" border="0" alt="<gsmsg:write key="enq.34" />">
       </logic:equal>
       <logic:equal name="enq330Form" property="enq310priority" value="2">
         <img src="../enquete/images/star_red_16.png" class="star" border="0" alt="<gsmsg:write key="enq.35" />">
         <img src="../enquete/images/star_red_16.png" class="star" border="0" alt="<gsmsg:write key="enq.35" />">
         <img src="../enquete/images/star_red_16.png" class="star" border="0" alt="<gsmsg:write key="enq.35" />">
       </logic:equal>
    </span>
    </td>
    <!-- 基本情報 発信者 -->
    <td align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.25" /></td>
    <td class="td_type20" nowrap>
      <bean:define id="sdFlg" name="enq330Form" property="enq310senderDelFlg" type="java.lang.Boolean" />
      <span class="text_base2<% if (sdFlg) { %> text_deluser_enq<% } %>">
        <bean:write name="enq330Form" property="enq310sender" />
      </span>
    </td>
    </tr>

    <!-- 基本情報 アンケート内容 -->
    <tr>
    <td align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.26" /></td>
    <td colspan="3" class="td_type20">
    <span class="text_base2">
      <bean:write name="enq330Form" property="enq310enqContent" filter="false" />
    </span>
    </td>
    </tr>

    <tr>
    <!-- 基本情報 回答期限 -->
      <td align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.19" /></td>
      <td class="td_type20" nowrap>
        <bean:write name="enq330Form" property="enq310ansLimitDate" />
      </td>
      <!-- 基本情報 結果公開期限 -->
      <td align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.enq210.11" /></td>
      <td class="td_type20" nowrap>
      <bean:define id="ansOpen" name="enq330Form" property="enq310ansOpen" type="java.lang.Integer" />
      <% if (ansOpen == GSConstEnquete.EMN_ANS_OPEN_PUBLIC) {%>
        <bean:write name="enq330Form" property="enq310ansPubFrDate" />
        <logic:empty name="enq330Form" property="enq310pubLimitDate">
              ～&nbsp;<gsmsg:write key="main.man200.9" />
        </logic:empty>
        <logic:notEmpty name="enq330Form" property="enq310pubLimitDate">
        &nbsp;～
        <br>
        <bean:write name="enq330Form" property="enq310pubLimitDate" />
        </logic:notEmpty>
      <% } else {%>
        <gsmsg:write key="cmn.private" />
      <% } %>
      </td>
    </tr>
    <tr>
      <!-- 対象人数 -->
      <td align="center" valign="middle" class="td_gray text_header" nowrap><gsmsg:write key="enq.58" /></td>
      <td align="left" valign="top" class="td_type20" nowrap>
        <table width="100%" cellpadding="0" cellspacing="0" border="0">
        <tbody>
          <tr>
            <td width="80px" class="text_header2" align="left" valign="top" nowrap><gsmsg:write key="cmn.whole" /></td>
            <td width="100px" class="text_base2" align="left" valign="top" nowrap>
              <bean:write name="enq330Form" property="enq310answerCountAll" /><gsmsg:write key="cmn.persons" />
            </td>
          </tr>
          <tr>
            <td class="text_header2" align="left" valign="top" nowrap><gsmsg:write key="enq.44" /></td>
            <td class="text_base2" align="left" valign="top" nowrap>
              <bean:write name="enq330Form" property="enq310answerCountAr" /><gsmsg:write key="cmn.persons" />　<span style="font-size: 80%">[<bean:write name="enq330Form" property="enq310answerCountArPer" />%]</span>
            </td>
          </tr>
          <tr>
            <td class="text_header2" align="left" valign="top" nowrap><gsmsg:write key="enq.45" /></td>
            <td class="text_base2" align="left" valign="top" nowrap>
              <bean:write name="enq330Form" property="enq310answerCountUn" /><gsmsg:write key="cmn.persons" />　<span style="font-size: 80%">[<bean:write name="enq330Form" property="enq310answerCountUnPer" />%]</span>
              <input type="hidden" name="enq330graphLabelNoAns" value="<gsmsg:write key="anp.ans.notyet" />">
              <input type="hidden" name="enq330graphValueNoAns" value="<bean:write name="enq330Form" property="enq310answerCountUnNum" />">
            </td>
          </tr>
        </tbody>
        </table>
      </td>
      <!-- 基本情報 注意事項 -->
      <td class="td_gray text_header" align="center" valign="middle" nowrap><gsmsg:write key="cmn.hints" /></td>
      <td class="td_type20" align="left" valign="middle">
        <span class="text_base2">
          <bean:define id="anony" name="enq330Form" property="enq310anony" type="java.lang.Integer" />
          <bean:define id="ansOpen" name="enq330Form" property="enq310ansOpen" type="java.lang.Integer" />
          <% if (anony == GSConstEnquete.ANONYMUS_ON && ansOpen == GSConstEnquete.KOUKAI_ON) { %>
            <gsmsg:write key="enq.69" />
          <% } else if (anony == GSConstEnquete.ANONYMUS_ON) { %>
            <gsmsg:write key="enq.31" />
          <% } else if (ansOpen == GSConstEnquete.KOUKAI_ON) { %>
            <gsmsg:write key="enq.32" />
          <% } %>
        </span>
      </td>
    </tr>
    </tbody>
    </table>

    <table cellpadding="5" width="100%" class="tl0 table_ans">
    <tbody>
    <!-- 設問情報 -->
    <tr>
      <td width="100%" class="td_type1_enq">

        <!-- 上部 -->
        <div class="text_bg_index">
          <table class="area_question" cellpadding="5" cellspacing="0" width="95%" border="0">
          <tbody>
            <tr>
              <td width="40" align="left" valign="top" class="text_question" nowrap>
                <gsmsg:write key="enq.37" />
              </td>
              <td width="80" align="left" valign="top" class="text_question" nowrap>
                <bean:write name="enq330Form" property="enq330queNo" />
              </td>
              <td rowspan="3" align="left" valign="top" class="text_question">
                <bean:write name="enq330Form" property="enq330question" />
              </td>
            </tr>
            <tr>
              <td colspan="2" align="left" valign="top" class="text_question" nowrap>
                <logic:equal name="enq330Form" property="enq330queRequire" value="1">
                  <span style="color: red;"><gsmsg:write key="cmn.asterisk" /><gsmsg:write key="cmn.required" /></span><br>
                </logic:equal>
              </td>
            </tr>
            <tr>
              <td colspan="2" align="left" valign="top" class="text_question2" nowrap>
                <span style="font-size: 80%;">[<bean:write name="enq330Form" property="enq330queKbnName" />]</span>
              </td>
            </tr>
          </tbody>
          </table>
        </div>

        <!-- 下部 -->
        <div class="area_question">
          <table width="100%" cellpadding="5" cellspacin="0" border="0">
          <tbody>
            <tr>
              <td width="99%" align="left" valign="top" style="padding-left:10px;">
                <bean:define id="enqQueKbn" name="enq330Form" property="enq330queKbn" type="java.lang.Integer" />
                <logic:notEmpty name="enq330Form" property="queSubList">
                <table width="280" class="table_setsumon" cellpadding="5" cellspacing="0">
                <tbody>
                  <tr>
                    <td width="100" class="text_base2" align="left">&nbsp;</td>
                    <td width="60" class="text_question2" align="right" nowrap><gsmsg:write key="reserve.use.num" /></td>
                    <td width="60" class="text_question2" align="right" valign="top" nowrap><gsmsg:write key="cmn.whole" /></td>
                    <td width="60" class="text_question2" align="right" nowrap><gsmsg:write key="enq.22" /></td>
                  </tr>
                <logic:iterate id="queSubData" name="enq330Form" property="queSubList">
                  <tr>
                    <td class="text_question2" align="left" valign="top" nowrap>
                      <bean:write name="queSubData" property="dspName" />
                      <input type="hidden" name="enq330graphLabel" value="<bean:write name="queSubData" property="ansDspName" />">
                      <input type="hidden" name="enq330graphValue" value="<bean:write name="queSubData" property="answerNum" />">
                    </td>
                    <td class="text_base2" align="right" nowrap><bean:write name="queSubData" property="answer" /><gsmsg:write key="cmn.persons" /></td>
                    <td class="text_base2" align="right" nowrap><bean:write name="queSubData" property="answerAllPer" />%</td>
                    <td class="text_base2" align="right" nowrap><bean:write name="queSubData" property="answerArPer" />%</td>
                  </tr>
                </logic:iterate>
                </tbody>
                </table>
                </logic:notEmpty>
                <% if  (enqQueKbn == GSConstEnquete.SYURUI_INTEGER || enqQueKbn == GSConstEnquete.SYURUI_DAY) {%>
                <table width="90%" class="table_setsumon" cellpadding="50" cellspacing="10">
                <tbody>
                  <tr>
                    <td class="text_base2" align="left" colspan="4">
                      <% if  (enqQueKbn == GSConstEnquete.SYURUI_DAY) {%>
                      <gsmsg:write key="enq.29" />：<bean:write name="enq330Form" property="enq330answerMinValue" />　　<gsmsg:write key="enq.30" />：<bean:write name="enq330Form" property="enq330answerMaxValue" />
                      <% } else { %>
                      <gsmsg:write key="enq.29" />：<bean:write name="enq330Form" property="enq330answerMinValue" />　　<gsmsg:write key="enq.30" />：<bean:write name="enq330Form" property="enq330answerMaxValue" />　　<gsmsg:write key="enq.59" />：<bean:write name="enq330Form" property="enq330answerAvgValue" />
                      <% } %>
                    </td>
                  </tr>
                </tbody>
                </table>
                <% } %>
              </td>
            </tr>

            <% if  (enqQueKbn == GSConstEnquete.SYURUI_SINGLE || enqQueKbn == GSConstEnquete.SYURUI_MULTIPLE) {%>
            <tr id="enq330graphArea">
              <td align="left" valign="top" style="padding-top: 25px; padding-left:25px;">
                <div id="image3">
                  <div id="enq330GraphArea" style="height: 350px; width: 500px;"></div>
                </div>
              </td>
            </tr>
            <tr>
              <td align="center" valign="top">
                <br>
                <html:radio name="enq330Form" property="enq330graph" styleId="graph0" value="0" onclick="enq330changeGraph();" /><label for="graph0"><span style="font-size: 80%"><gsmsg:write key="cmn.bar.graph" /></span></label>
                <html:radio name="enq330Form" property="enq330graph" styleId="graph1" value="1" onclick="enq330changeGraph();" /><label for="graph1"><span style="font-size: 80%"><gsmsg:write key="cmn.bar.graph" />(<gsmsg:write key="enq.61" />）</span></label>
                <%   if  (enqQueKbn == GSConstEnquete.SYURUI_SINGLE) {%>
                <br>
                <html:radio name="enq330Form" property="enq330graph" styleId="graph2" value="2" onclick="enq330changeGraph();" /><label for="graph2"><span style="font-size: 80%"><gsmsg:write key="cmn.pie.graph" /></span></label>
                <html:radio name="enq330Form" property="enq330graph" styleId="graph3" value="3" onclick="enq330changeGraph();" /><label for="graph3"><span style="font-size: 80%"><gsmsg:write key="cmn.pie.graph" />(<gsmsg:write key="enq.61" />）</span></label><br>
                <% } else { %>&nbsp;<% } %>
              </td>
            </tr>
            <% } %>
          </tbody>
          </table>

          <img src="../common/images/spacer.gif" width="1" height="10" border="0" alt="<gsmsg:write key='cmn.spacer' />">
          <table width="210" cellpadding="0" cellspacing="0">
            <tbody>
              <tr>
                <td width="90" class="text_header2" align="left" valign="top" style="padding-left:10px;" nowrap><gsmsg:write key="enq.44" /></td>
                <td width="100" class="text_base2" align="left" nowrap><bean:write name="enq330Form" property="enq330answerCountAr" /><gsmsg:write key="cmn.persons" /></td>
              </tr>
              <tr>
                <td class="text_header2" align="left" valign="top" style="padding-left:10px;" nowrap><gsmsg:write key="enq.45" /></td>
                <td class="text_base2" nowrap><bean:write name="enq330Form" property="enq330answerCountUn" /><gsmsg:write key="cmn.persons" /></td>
              </tr>
            </tbody>
          </table>

        </div>

      </td>
    </tr>
  </tbody>
  </table>

  <!-- 検索条件 -->
  <table width="100%" cellpadding="0" cellspacing="0" style="margin-top: 25px;">
  <tbody>
    <tr>
      <td width="100%" class="enq_search_area tbl_search">

        <table cellpadding="5" width="100%" class="tl0">
        <tbody>
          <logic:equal name="enq330Form" property="enq310anony" value="<%= String.valueOf(GSConstEnquete.EMN_ANONNY_NON) %>" >
          <tr>
            <td width="12%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="cmn.target" /></td>
            <td width="88%" align="left" class="td_type20" nowrap>
              <span style="font-size: 80%;"><gsmsg:write key="cmn.group" /></span>
              <html:select property="enq330group" onchange="buttonPush('init');">
                <html:optionsCollection name="enq330Form" property="groupCombo" value="value" label="label" />
              </html:select>
              &nbsp;&nbsp;
              <span style="font-size: 80%;"><gsmsg:write key="cmn.user" /></span>
              <html:select property="enq330user">
                <html:optionsCollection name="enq330Form" property="userCombo" value="value" label="label" />
              </html:select>
            </td>
          </tr>
          </logic:equal>

          <tr>
            <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.22" /></td>
            <td class="td_type20">

              <% if  (enqQueKbn == GSConstEnquete.SYURUI_TEXT || enqQueKbn == GSConstEnquete.SYURUI_TEXTAREA) {%>
              <html:text name="enq330Form" property="enq330ansText" maxlength="100" styleClass="que_text_init"/>
              <% } %>

              <% if  (enqQueKbn == GSConstEnquete.SYURUI_INTEGER || enqQueKbn == GSConstEnquete.SYURUI_DAY) {%>
                <html:radio name="enq330Form" property="enq330ansNumKbn" value="0" styleId="ansNumKbn0" onclick="changeSearchArea(0);" /><label for="ansNumKbn0" style="font-size:90%;"><gsmsg:write key="cmn.single" /></label>
                &nbsp;<html:radio name="enq330Form" property="enq330ansNumKbn" value="1" styleId="ansNumKbn1" onclick="changeSearchArea(0);" /><label for="ansNumKbn1" style="font-size:90%;"><gsmsg:write key="cmn.range" /></label>&nbsp;

                <% if  (enqQueKbn == GSConstEnquete.SYURUI_INTEGER) {%>
                  <html:text name="enq330Form" property="enq330ansNumFrom" maxlength="10" styleClass="ans_text_num2" />
                  <span id="enq330SearchArea">
                  <gsmsg:write key="tcd.142" />
                  <html:text name="enq330Form" property="enq330ansNumTo" maxlength="10" styleClass="ans_text_num2" />
                  </div>
                <% } %>
                <% if  (enqQueKbn == GSConstEnquete.SYURUI_DAY) {%>
                  <div nowrap>
                  <html:select property="enq330ansDateFromYear" styleId="enq330ansDateFromYear" style="vertical-align:middle;">
                  <html:optionsCollection name="enq330Form" property="yearCombo" value="value" label="label" />
                  </html:select>&nbsp;
                  <html:select property="enq330ansDateFromMonth" styleId="enq330ansDateFromMonth" style="vertical-align:middle;">
                  <html:optionsCollection name="enq330Form" property="monthCombo" value="value" label="label" />
                  </html:select>&nbsp;
                  <html:select property="enq330ansDateFromDay" styleId="enq330ansDateFromDay" style="vertical-align:middle;">
                  <html:optionsCollection name="enq330Form" property="dayCombo" value="value" label="label" />
                  </html:select>
                  <input type="button" value="Cal" name="ansDateFrom" onclick="wrtCalendar(this.form.enq330ansDateFromDay, this.form.enq330ansDateFromMonth, this.form.enq330ansDateFromYear);" class="calendar_btn">
                  <span id="enq330SearchArea">
                  <gsmsg:write key="tcd.142" />
                  <html:select property="enq330ansDateToYear" styleId="enq330ansDateToYear" style="vertical-align:middle;">
                  <html:optionsCollection name="enq330Form" property="yearCombo" value="value" label="label" />
                  </html:select>&nbsp;
                  <html:select property="enq330ansDateToMonth" styleId="enq330ansDateToMonth" style="vertical-align:middle;">
                  <html:optionsCollection name="enq330Form" property="monthCombo" value="value" label="label" />
                  </html:select>&nbsp;
                  <html:select property="enq330ansDateToDay" styleId="enq330ansDateToDay" style="vertical-align:middle;">
                  <html:optionsCollection name="enq330Form" property="dayCombo" value="value" label="label" />
                  </html:select>
                  <input type="button" value="Cal" name="ansDateTo" onclick="wrtCalendar(this.form.enq330ansDateToDay, this.form.enq330ansDateToMonth, this.form.enq330ansDateToYear);" class="calendar_btn">
                  </div>
                  </div>
                <% } %>

              <% } %>

              <% if  (enqQueKbn == GSConstEnquete.SYURUI_SINGLE || enqQueKbn == GSConstEnquete.SYURUI_MULTIPLE) { %>
              <logic:notEmpty name="enq330Form" property="queSubList">
                <span class="text_base2">
                <logic:iterate id="queSubData" name="enq330Form" property="queSubList" indexId="idx">
                <% String selAnsId = "answer" + String.valueOf(idx.intValue()); %>
                <html:multibox name="enq330Form" property="enq330ans" styleId="<%= selAnsId %>">
                  <bean:write name="queSubData"  property="queSubSeq" />
                </html:multibox>
                <label for="<%= selAnsId %>">
                  <bean:write name="queSubData"  property="dspName" />
                </label>
              </logic:iterate>
                </span>
              </logic:notEmpty>
              <% } %>

            </td>
          </tr>

          <tr>
            <td colspan="2" align="center" width="100%" nowrap>
              <img src="../common/images/spacer.gif" width="1" height="25" border="0" alt="<gsmsg:write key='cmn.spacer' />">
              <input type="button" class="btn_search_n1" value="<gsmsg:write key="cmn.search" />" onClick="buttonPush('enq330search');">
            </td>
          </tr>
        </tbody>
        </table>

      </td>
    </tr>
  </tbody>
  </table>

  <img src="../common/images/spacer.gif" width="1" height="20" border="0" alt="<gsmsg:write key='cmn.spacer' />">

  <!-- 結果一覧 -->
  <table class="tl0 clear_table" width="100%" cellpadding="5" cellspacing="0" id="enq330ansListArea">
  <tbody>
    <tr>
      <td colspan="2" width="50%" class="td_type1" align="left" valign="middle" style="border: 0px;">
        <gsmsg:write key="bbs.bbs041.2" />：<bean:write name="enq330Form" property="enq330searchCount" /><gsmsg:write key="cmn.number" />
      </td>
      <td colspan="2" width="50%" align="right">
        <logic:notEmpty name="enq330Form" property="pageList">
          <img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_l.gif" border="0" onclick="buttonPush('prevPage');">
          <html:select name="enq330Form" property="enq330pageTop" styleClass="text_i" onchange="enq330changePage(0);">
            <html:optionsCollection name="enq330Form" property="pageList" value="value" label="label" />
          </html:select>
          <img src="../common/images/arrow2_r.gif" name="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" border="0" onclick="buttonPush('nextPage');">
        </logic:notEmpty>
      </td>
    </tr>

    </tr>
    <bean:define id="enqSortKey" name="enq330Form" property="enq330sortKey" type="java.lang.Integer" />
    <bean:define id="enqOrder" name="enq330Form" property="enq330order" type="java.lang.Integer" />
    <% String sortSign = ""; %>
    <% String nextOrder = ""; %>
    <tr>
      <th width="20%" class="table_bg_7D91BD" nowrap>
      <% int titleSortKey = 1; %>
      <logic:equal name="enq330Form" property="enq310anony" value="1">
      <span class="text_tlw_10pt"><gsmsg:write key="cmn.group" /></span>
      </logic:equal>
      <logic:notEqual name="enq330Form" property="enq310anony" value="1">
      <% if (enqSortKey.intValue() == titleSortKey) { %>
      <%   if (enqOrder.intValue() == 1) { sortSign="▼"; nextOrder = "0"; } else { sortSign="▲"; nextOrder = "1"; } %>
      <% } else { nextOrder = "0"; sortSign = ""; } %>
          <a href="#" onClick="enqClickTitle(<%= String.valueOf(titleSortKey) %>, <%= nextOrder %>);" class="text_tlw_10pt"><gsmsg:write key="cmn.group" /><%= sortSign %></a>
      </logic:notEqual>
      </th>
      <th width="20%" class="table_bg_7D91BD" nowrap>
      <% titleSortKey = 2; %>
      <logic:equal name="enq330Form" property="enq310anony" value="1">
      <span class="text_tlw_10pt"><gsmsg:write key="cmn.user" /></span>
      </logic:equal>
      <logic:notEqual name="enq330Form" property="enq310anony" value="1">
      <% if (enqSortKey.intValue() == titleSortKey) { %>
      <%   if (enqOrder.intValue() == 1) { sortSign="▼"; nextOrder = "0"; } else { sortSign="▲"; nextOrder = "1"; } %>
      <% } else { nextOrder = "0"; sortSign = ""; } %>
          <a href="#" onClick="enqClickTitle(<%= String.valueOf(titleSortKey) %>, <%= nextOrder %>);" class="text_tlw_10pt"><gsmsg:write key="cmn.user" /><%= sortSign %></a>
      </logic:notEqual>
      </th>
      <th width="20%" class="table_bg_7D91BD" nowrap>
      <% titleSortKey = 0; %>
      <% if (enqSortKey.intValue() == titleSortKey) { %>
      <%   if (enqOrder.intValue() == 1) { sortSign="▼"; nextOrder = "0"; } else { sortSign="▲"; nextOrder = "1"; } %>
      <% } else { nextOrder = "0"; sortSign = ""; } %>
          <a href="#" onClick="enqClickTitle(<%= String.valueOf(titleSortKey) %>, <%= nextOrder %>);" class="text_tlw_10pt"><gsmsg:write key="enq.51" /><%= sortSign %></a>
      </th>
      <th width="40%" class="table_bg_7D91BD" nowrap>
      <span class="text_tlw_10pt"><gsmsg:write key="enq.60" /></span>
      </th>
    </tr>

    <logic:notEmpty name="enq330Form" property="ansList">
    <logic:iterate id="ansData" name="enq330Form" property="ansList">
    <tr>
      <!-- グループ -->
      <td class="td_type1" align="left" valign="top">
        <bean:write name="ansData" property="group" />
      </td>
      <!-- ユーザ -->
      <td class="td_type1" align="left" valign="top">
        <bean:define id="sdFlg" name="ansData" property="userDelFlg" type="java.lang.Boolean" />
        <bean:define id="enq330anony" name="ansData" property="anony" type="java.lang.Boolean" />
        <% boolean userLinkFlg = (!sdFlg && !enq330anony); %>
        <% if (sdFlg) { %><span class="text_deluser_enq">
        <% } else if (!enq330anony) { %><a href="javaScript:void(0);" onClick="openUserInfoWindow(<bean:write name="ansData" property="userSid" />);" class="answer">
        <% } %>
        <bean:write name="ansData" property="user" />
        <% if (sdFlg) { %></span>
        <% } else if (!enq330anony) { %></a>
        <% } %>
      </td>
      <!-- 回答日 -->
      <td class="td_type1" align="left" valign="top">
        <bean:write name="ansData" property="ansDate" />
      </td>
      <!-- 回答値 -->
      <td class="td_type1" align="left" valign="top">
        <bean:write name="ansData" property="ansValue" />
      </td>
    </tr>
    </logic:iterate>
    </logic:notEmpty>

    <logic:notEmpty name="enq330Form" property="pageList">
    <tr>
      <td colspan="4" width="100%" align="right" style="padding-bottom: 15px; padding-top:10px;">
          <img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_l.gif" border="0" onclick="buttonPush('prevPage');">
          <html:select name="enq330Form" property="enq330pageBottom" styleClass="text_i" onchange="enq330changePage(1);">
            <html:optionsCollection name="enq330Form" property="pageList" value="value" label="label" />
          </html:select>
          <img src="../common/images/arrow2_r.gif" name="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" border="0" onclick="buttonPush('nextPage');">
      </td>
    </tr>
    </logic:notEmpty>

    </tbody>
    </table>
  </td>
  </tr>
  <tr>
    <td width="99%" align="right" style="padding-top: 10px;">
      <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onclick="buttonPush('enq330back');" >
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