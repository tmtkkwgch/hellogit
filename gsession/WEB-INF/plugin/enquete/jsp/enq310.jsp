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
<script type="text/javascript" src="../enquete/js/enq310.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/glayer.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../enquete/css/enquete.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<title>[GroupSession] <gsmsg:write key="enq.plugin" /></title>
</head>
<body class="body_03">
<html:form action="/enquete/enq310">
<input type="hidden" name="CMD" value="">
<input type="hidden" name="enq310selectQue" value="">
<input type="hidden" name="enq310selectQueSub" value="">
<input type="hidden" name="enq320viewMode" value="">
<input type="hidden" name="enq330ans" value="">
<input type="hidden" name="enq330svAns" value="">

<html:hidden property="ansEnqSid" />

<%@ include file="/WEB-INF/plugin/enquete/jsp/enq010_hiddenParams.jsp" %>
<logic:notEmpty name="enq310Form" property="enq010priority">
<logic:iterate id="svPriority" name="enq310Form" property="enq010priority">
  <input type="hidden" name="enq010priority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq310Form" property="enq010status">
<logic:iterate id="svStatus" name="enq310Form" property="enq010status">
  <input type="hidden" name="enq010status" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq310Form" property="enq010svPriority">
<logic:iterate id="svPriority" name="enq310Form" property="enq010svPriority">
  <input type="hidden" name="enq010svPriority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq310Form" property="enq010svStatus">
<logic:iterate id="svStatus" name="enq310Form" property="enq010svStatus">
  <input type="hidden" name="enq010svStatus" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq310Form" property="enq010statusAnsOver">
<logic:iterate id="svStatusAnsOver" name="enq310Form" property="enq010statusAnsOver">
  <input type="hidden" name="enq010statusAnsOver" value="<bean:write name="svStatusAnsOver" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq310Form" property="enq010svStatusAnsOver">
<logic:iterate id="svStatusAnsOver" name="enq310Form" property="enq010svStatusAnsOver">
  <input type="hidden" name="enq010svStatusAnsOver" value="<bean:write name="svStatusAnsOver" />">
</logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="70%">
<tr>
<td width="100%" align="center" colspan="2">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%">
      <img src="../enquete/images/header_enquete_01.gif" border="0" alt="<gsmsg:write key="enq.plugin" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="enq.plugin" /></span></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap>[ <gsmsg:write key="enq.47" /> ]</td>
    <td width="100%" class="header_white_bg">
    </td>
    <td width="0%">
    <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tbody><tr>
    <td width="50%">
    </td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key='cmn.csv' />" class="btn_csv_n1" onclick="buttonPush('export');">
      <input type="button" value="<gsmsg:write key='cmn.pdf' />" class="btn_pdf_n1" onclick="buttonPush('pdf')">
      <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onclick="buttonPush('enq310backTo020')">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt="<gsmsg:write key='cmn.spacer' />"></td>
    </tr>
    </tbody></table>
  </td>
  </tr>
  </table>
</td>
</tr>

<!-- BODY -->
<tr>
  <td width="99%">
    <table width="100%">
      <tr>
        <td width="1%"><img src="../enquete/images/enquete_title.gif" /></td>
        <td width="99%" class="text_bb4 enq_title" style="padding-left: 10px;"><bean:write name="enq310Form" property="enq310enqTitle" /></td>
      </tr>
    </table>
  </td>
</tr>

<tr>
<td width="100%" align="center" class="wrap_table" colspan="2">

  <table class="clear_table" width="100%">
  <tr>

    <td class="content_area">

    <table cellpadding="5" width="100%" class="tl0">
    <tbody>

    <!-- 基本情報 重要度 -->
    <tr>
    <td width="13%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="project.prj050.4" /></td>
    <td width="25%" class="td_type20" nowrap>
    <span class="text_base2">
       <logic:equal name="enq310Form" property="enq310priority" value="0">
         <img src="../enquete/images/star_blue_16.png" class="star" border="0" alt="<gsmsg:write key="project.58" />">
         <img src="../enquete/images/star_white_16.png" class="star" border="0" alt="<gsmsg:write key="project.58" />">
         <img src="../enquete/images/star_white_16.png" class="star" border="0" alt="<gsmsg:write key="project.58" />">
       </logic:equal>
       <logic:equal name="enq310Form" property="enq310priority" value="1">
         <img src="../enquete/images/star_gold_16.png" class="star" border="0" alt="<gsmsg:write key="project.59" />">
         <img src="../enquete/images/star_gold_16.png" class="star" border="0" alt="<gsmsg:write key="project.59" />">
         <img src="../enquete/images/star_white_16.png" class="star" border="0" alt="<gsmsg:write key="project.59" />">
       </logic:equal>
       <logic:equal name="enq310Form" property="enq310priority" value="2">
         <img src="../enquete/images/star_red_16.png" class="star" border="0" alt="<gsmsg:write key="project.60" />">
         <img src="../enquete/images/star_red_16.png" class="star" border="0" alt="<gsmsg:write key="project.60" />">
         <img src="../enquete/images/star_red_16.png" class="star" border="0" alt="<gsmsg:write key="project.60" />">
       </logic:equal>
    </span>
    </td>
    <!-- 基本情報 発信者 -->
    <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.25" /></td>
    <td colspan="3" width="52%" class="td_type20" nowrap>
      <bean:define id="sdFlg" name="enq310Form" property="enq310senderDelFlg" type="java.lang.Boolean" />
      <span class="text_base2<% if (sdFlg) { %> text_deluser_enq<% } %>">
        <bean:write name="enq310Form" property="enq310sender" />
      </span>
    </td>
    </tr>

    <!-- 基本情報 アンケート内容 -->
    <tr>
    <td align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.18" /></td>
    <td colspan="3" class="td_type20">
    <span class="text_base2">
      <bean:write name="enq310Form" property="enq310enqContent" filter="false" />&nbsp;
    </span>
    </td>
    </tr>
    <!-- 基本情報 回答期限 -->
    <tr>
    <td align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.19" /></td>
    <td class="td_type20" nowrap>
     <bean:write name="enq310Form" property="enq310ansLimitDate" />
    </td>
    <!-- 基本情報 結果公開期限 -->
    <td align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.enq210.11" /></td>
    <td class="td_type20" nowrap>
    <bean:define id="ansOpen" name="enq310Form" property="enq310ansOpen" type="java.lang.Integer" />
    <% if (ansOpen == GSConstEnquete.EMN_ANS_OPEN_PUBLIC) {%>
      <bean:write name="enq310Form" property="enq310ansPubFrDate" />
      <logic:empty name="enq310Form" property="enq310pubLimitDate">
              ～&nbsp;<gsmsg:write key="main.man200.9" />
      </logic:empty>
      <logic:notEmpty name="enq310Form" property="enq310pubLimitDate">
      &nbsp;～
      <br>
      <bean:write name="enq310Form" property="enq310pubLimitDate" />
      </logic:notEmpty>
    <% } else { %>
      <gsmsg:write key="cmn.private" />
    <% } %>
    </td>
    </tr>

    <tr>
      <!-- 対象人数 -->
      <td align="center" valign="middle" class="td_gray text_header" nowrap><gsmsg:write key="cmn.number.of.candidates" /></td>
      <td align="left" valign="top" class="td_type20" nowrap>
        <table width="100%" cellpadding="2" cellspacing="0" border="0">
        <tbody>
          <tr>
            <td width="80px" class="text_header2" align="left" valign="top" nowrap><gsmsg:write key="cmn.whole" /></td>
            <td width="100px" class="text_base2" align="left" valign="top" nowrap>
              <span class="answered"><a href="#" onclick="enq310Taisyo('enq310list', 0);return false;" class="answered"><bean:write name="enq310Form" property="enq310answerCountAll" /><gsmsg:write key="cmn.persons" /></a></span>
            </td>
          </tr>
          <tr>
            <td class="text_header2" align="left" valign="top" nowrap><gsmsg:write key="enq.44" /></td>
            <td class="text_base2" align="left" valign="top" nowrap>
              <span class="answered"><a href="#" onclick="enq310Taisyo('enq310list', 1);return false;" class="answered"><bean:write name="enq310Form" property="enq310answerCountAr" /><gsmsg:write key="cmn.persons" /></a></span>　<span style="font-size: 80%">[<bean:write name="enq310Form" property="enq310answerCountArPer" />%]</span>
            </td>
          </tr>
          <tr>
            <td class="text_header2" align="left" valign="top" nowrap><gsmsg:write key="enq.45" /></td>
            <td class="text_base2" align="left" valign="top" nowrap>
              <span class="answered"><a href="#" onclick="enq310Taisyo('enq310list', 2);return false;" class="answered"><bean:write name="enq310Form" property="enq310answerCountUn" /><gsmsg:write key="cmn.persons" /></a></span>　<span style="font-size: 80%">[<bean:write name="enq310Form" property="enq310answerCountUnPer" />%]</span>
            </td>
          </tr>
        </tbody>
        </table>
      </td>
      <!-- 基本情報 注意事項 -->
      <td class="td_gray text_header" align="center" valign="middle" nowrap><gsmsg:write key="enq.27" /></td>
      <td class="td_type20" align="left" valign="middle">
        <span class="text_base2">
          <bean:define id="anony" name="enq310Form" property="enq310anony" type="java.lang.Integer" />
          <bean:define id="ansOpen" name="enq310Form" property="enq310ansOpen" type="java.lang.Integer" />
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


  <!-- 回答情報 -->
  <div width="100%" style="margin-top: 20px;" class="text_info_title">
    <gsmsg:write key="enq.46" />
  </div>

  <!-- 結果一覧 -->
  <table class="tl0" width="100%" cellpadding="5" cellspacing="0">
  <tbody>
    <tr>
      <th width="75%" class="table_bg_7D91BD" nowrap><span class="text_tlw_10pt"><gsmsg:write key="enq.48" /></span></th>
      <th width="25%" class="table_bg_7D91BD" nowrap><span class="text_tlw_10pt"><gsmsg:write key="enq.49" /></span></th>
    </tr>

    <logic:notEmpty name="enq310Form" property="queList">
    <logic:iterate id="queData" name="enq310Form" property="queList">
    <tr>
      <!-- 設問内容 -->
      <td class="td_type1" align="left" valign="top">
        <table width="90%" cellpadding="5" cellspacin="0" border="0">
        <tbody>
          <tr>
            <td width="20%" class="text_base2" align="left" valign="top">
              <div style="width: 110px!important">
              <gsmsg:write key="enq.12" /> <bean:write name="queData" property="no" />
              <br><span class="text_base2" style="font-size:80%; font-weight: normal;">[<bean:write name="queData" property="queKbnName" />]　</span>
              <logic:equal name="queData" property="require" value="1">
              <br><span style="color:red; font-weight:normal;"><gsmsg:write key="enq.28" /></span>
              </logic:equal>
              </div>
            </td>
            <td width="80%" align="left" valign="top">
              <table width="99%" cellpadding="5" cellspacin="0" border="0">
              <tbody>
                <tr><td colspan="4" class="text_base2" style="padding-bottom: 20px;"><bean:write name="queData" property="question" /></td></tr>
                <logic:notEmpty name="queData" property="subList">
                <logic:iterate id="subQueData" name="queData" property="subList">
                <tr>
                  <td width="2%" class="text_question" align="left" valign="top" nowrap>
                  <logic:equal name="subQueData" property="queSubSeq" value="-1">
                    <gsmsg:write key="cmn.other" />
                  </logic:equal>
                  <logic:notEqual name="subQueData" property="queSubSeq" value="-1">
                    <bean:write name="subQueData" property="dspName" />
                  </logic:notEqual>
                  </td>
                  <td width="2%" class="text_base2 answered" style="text-align: right; padding-left: 10px;" nowrap><a href="#" onclick="enq310DetailSelect(<bean:write name="queData" property="queSeq" />, <bean:write name="subQueData" property="queSubSeq" />);" class="answered"><bean:write name="subQueData" property="answer" /><gsmsg:write key="cmn.persons" /></a></td>
                  <td width="2%" class="text_base2" style="text-align: right; padding-left: 10px;" nowrap><bean:write name="subQueData" property="answerAllPer" />%</td>
                  <td width="96%">&nbsp;</td>
                </tr>
                </logic:iterate>
                </logic:notEmpty>
              </tbody>
              </table>
            </td>
          </tr>
        </tbody>
        </table>
      </td>

      <td class="td_type1" align="left" valign="top">
        <table width="90%" cellpadding="5" cellspacin="0" border="0">
          <tbody>
          <tr>
          <td width="1%" class="text_base2" align="left" valign="top" nowrap><gsmsg:write key="enq.44" /></td>
          <td width="1%" class="text_base2 answered" style="text-align: right; vertical-align: top; padding-left: 20px;" nowrap><a href="#" onclick="enq310Detail(<bean:write name="queData" property="queSeq" />);return false;" class="answered"><bean:write name="queData" property="answerCountAr" /><gsmsg:write key="cmn.persons" /></a></td>
          <td width="1%" class="text_base2" style="text-align: right; vertical-align: top; padding-left: 20px;" nowrap><bean:write name="queData" property="answerCountArPer" />%</td>
          <td width="90%" rowspan="2" align="right">　</td>
          </tr>

          <tr>
          <td class="text_base2" align="left" valign="top" nowrap><gsmsg:write key="enq.45" /></td>
          <td class="text_base2" style="text-align: right; vertical-align: top; padding-left: 10px;" nowrap><bean:write name="queData" property="answerCountUn" /><gsmsg:write key="cmn.persons" /></td>
          <td class="text_base2" style="text-align: right; vertical-align: top; padding-left: 10px;" nowrap><bean:write name="queData" property="answerCountUnPer" />%</td>
          </tr>

          </tbody>
        </table>
      </td>
    </tr>
    </logic:iterate>
    </logic:notEmpty>
    </tbody></table>

  </tr>
  <tr>
    <td width="50%" align="right" style="padding-top:10px;">
      <input type="button" value="<gsmsg:write key='cmn.csv' />" class="btn_csv_n1" onclick="buttonPush('export');">
      <input type="button" value="<gsmsg:write key='cmn.pdf' />" class="btn_pdf_n1" onclick="buttonPush('pdf')">
      <input type="button" value="<gsmsg:write key='cmn.back2' />" class="btn_back_n1" onclick="buttonPush('enq310backTo020')">
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