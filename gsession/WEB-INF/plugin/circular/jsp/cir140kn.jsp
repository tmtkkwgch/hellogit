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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../circular/css/circular.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<title>[GroupSession] <gsmsg:write key="cir.31" /></title>
</head>

<body class="body_03">
<html:form action="/circular/cir140kn">
<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="cirViewAccount" />
<html:hidden property="cirAccountMode" />
<html:hidden property="cirAccountSid" />
<html:hidden property="cir140KenKbn" />
<html:hidden property="cir140memoKbn" />
<html:hidden property="cir140memoPeriod" />
<html:hidden property="cir140show" />

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
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt=""></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cir.32" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('backToKtool');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('back_init_change');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <logic:messagesPresent message="false">
      <table width="100%">
      <tr>
        <td align="left"><span class="TXT02"><html:errors/></span></td>
      </tr>
      </table>
    </logic:messagesPresent>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" class="tl0" border="0" cellpadding="5">
    <tr>
    <td>
      <table cellpadding="10" cellspacing="0" border="0" width="100%" class="tl_u2">

      <tr>
      <td valign="middle" align="left" class="table_bg_A5B4E1" width="20%" nowrap id="cirEditArea1">
        <span class="text_bb1"><gsmsg:write key="cmn.edit.permissions" /></span>
      </td>
      <td valign="middle" align="left" class="td_wt">

      <!-- メモ欄の修正権限区分 -->
      <logic:equal name="cir140knForm" property="cir140KenKbn" value="<%= String.valueOf(jp.groupsession.v2.cir.GSConstCircular.CIR_INIEDIT_STYPE_ADM) %>">
        <span class="text_base6_2"><gsmsg:write key="cmn.set.the.admin" /></span>
      </logic:equal>
      <logic:equal name="cir140knForm" property="cir140KenKbn" value="<%= String.valueOf(jp.groupsession.v2.cir.GSConstCircular.CIR_INIEDIT_STYPE_USER) %>">
        <span class="text_base6_2"><gsmsg:write key="cmn.set.eachaccount" /></span>
      </logic:equal>
      </td>
      </tr>

      <tr>
      <td valign="middle" align="left" class="table_bg_A5B4E1" width="20%" nowrap id="cirEditArea1">
        <span class="text_bb1"><gsmsg:write key="cir.cir040.2" /></span>
      </td>
      <logic:equal name="cir140knForm" property="cir140memoKbn" value="0">
        <!-- メモ欄修正区分 -->
        <td align="left" class="td_wt" nowrap><span class="text_base"><gsmsg:write key="cir.cir040.3" />&nbsp;&nbsp;</span>
          <span class="text_base"><gsmsg:write key="cmn.not" /></span>
        </td>
      </logic:equal>

      <logic:equal name="cir140knForm" property="cir140memoKbn" value="1">
        <!-- メモ欄修正区分 -->
        <td align="left" class="td_wt" nowrap><span class="text_base"><gsmsg:write key="cir.cir040.3" />&nbsp;&nbsp;</span>
          <span class="text_base"><gsmsg:write key="cmn.accepted" /></span>
        <!-- メモ欄修正期限設定 -->
        <br>
        <br>
        <span class="text_base"><gsmsg:write key="cir.54" /></span>
        <br>

        <logic:equal name="cir140knForm" property="cir140memoPeriod" value="1">
          <span class="text_base"><gsmsg:write key="cmn.today" /></span>
        </logic:equal>
        <logic:equal name="cir140knForm" property="cir140memoPeriod" value="0">
          <span class="text_base">1<gsmsg:write key="cmn.weeks" /></span>
        </logic:equal>
        <logic:equal name="cir140knForm" property="cir140memoPeriod" value="2">
          <span class="text_base">2<gsmsg:write key="cmn.weeks" /></span>
        </logic:equal>
        <logic:equal name="cir140knForm" property="cir140memoPeriod" value="3">
          <span class="text_base"><gsmsg:write key="cmn.months" arg0="1" /></span>
        </logic:equal>
        </td>
      </logic:equal>
      </tr>

      <!-- 回覧先確認状況区分 -->
      <tr>
      <td valign="middle" align="left" class="table_bg_A5B4E1" width="20%" nowrap id="cirEditArea1" rowspan="2">
        <span class="text_bb1"><gsmsg:write key="cir.cir030.3" /></span>
      </td>
      <td align="left" class="td_wt" nowrap>
        <logic:equal name="cir140knForm" property="cir140show" value="<%= String.valueOf(jp.groupsession.v2.cir.GSConstCircular.CIR_INIT_SAKI_PUBLIC) %>">
          <span class="text_base"><gsmsg:write key="cmn.public" /></span>
        </logic:equal>
        <logic:equal name="cir140knForm" property="cir140show" value="<%= String.valueOf(jp.groupsession.v2.cir.GSConstCircular.CIR_INIT_SAKI_PRIVATE) %>">
            <span class="text_base"><gsmsg:write key="cmn.private" /></span>
        </logic:equal>
      </td>
      </tr>
      </table>

      <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

      <table cellpadding="0" cellpadding="5" border="0" width="100%">
      <tr>
      <td align="right">
        <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('backToKtool');">
        <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('back_init_change');">
      </td>
      </tr>
      </table>

    </td>
    </tr>
    </table>
  </td>
  </tr>
  </table>
</td>
</tr>
</table>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</html:form>
</body>
</html:html>