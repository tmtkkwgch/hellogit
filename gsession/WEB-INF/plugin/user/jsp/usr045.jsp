<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%

String maxLengthBiko = String.valueOf(jp.groupsession.v2.adr.GSConstAddress.MAX_LENGTH_ADR2_BIKO);
String kbnFlg = String.valueOf(jp.groupsession.v2.usr.GSConstUser.PROCMODE_EDIT);

%>

<html:html>
<head>
<title>[Group Session] <gsmsg:write key="cmn.category.entry" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03" onload="showLengthId($('#inputstr')[0], <%= maxLengthBiko %>, 'inputlength');">
<html:form action="/user/usr045">

<input type="hidden" name="CMD" value="ok">
<input type="hidden" name="helpPrm" value="<bean:write name="usr045Form" property="usr043ProcMode" />">

<html:hidden property="usr043EditSid" />
<html:hidden property="usr043ProcMode" />

<html:hidden property="backScreen" />
<html:hidden property="usr040cmdMode" />
<html:hidden property="usr040orderKey" />
<html:hidden property="usr040sortKey" />
<html:hidden property="usr040orderKey2" />
<html:hidden property="usr040sortKey2" />
<html:hidden property="usr040pageNum1" />
<html:hidden property="usr040pageNum2" />
<html:hidden property="usr040SearchKana" />
<html:hidden property="selectgsid" />

<html:hidden property="usr040Keyword" />
<html:hidden property="usr040KeyKbnShainno" />
<html:hidden property="usr040KeyKbnName" />
<html:hidden property="usr040KeyKbnNameKn" />
<html:hidden property="usr040KeyKbnMail" />
<html:hidden property="usr040agefrom" />
<html:hidden property="usr040ageto" />
<html:hidden property="usr040yakushoku" />
<html:hidden property="usr040tdfkCd" />
<html:hidden property="usr040entranceYearFr" />
<html:hidden property="usr040entranceMonthFr" />
<html:hidden property="usr040entranceDayFr" />
<html:hidden property="usr040entranceYearTo" />
<html:hidden property="usr040entranceMonthTo" />
<html:hidden property="usr040entranceDayTo" />
<html:hidden property="usr040seibetu" />

<logic:notEmpty name="usr045Form" property="usr040labSid">
  <logic:iterate id="labSidArray" name="usr045Form" property="usr040labSid" indexId="idx">
    <input type="hidden" name="usr040labSid" value="<bean:write name="labSidArray" />">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="selectgsidSave" />
<html:hidden property="usr040SearchKanaSave" />

<html:hidden property="usr040KeywordSave" />
<html:hidden property="usr040KeyKbnShainnoSave" />
<html:hidden property="usr040KeyKbnNameSave" />
<html:hidden property="usr040KeyKbnNameKnSave" />
<html:hidden property="usr040KeyKbnMailSave" />
<html:hidden property="usr040agefromSave" />
<html:hidden property="usr040agetoSave" />
<html:hidden property="usr040yakushokuSave" />
<html:hidden property="usr040tdfkCdSave" />
<html:hidden property="usr040entranceYearFrSave" />
<html:hidden property="usr040entranceMonthFrSave" />
<html:hidden property="usr040entranceDayFrSave" />
<html:hidden property="usr040entranceYearToSave" />
<html:hidden property="usr040entranceMonthToSave" />
<html:hidden property="usr040entranceDayToSave" />
<html:hidden property="usr040seibetuSave" />

<logic:notEmpty name="usr045Form" property="usr040labSidSave">
  <logic:iterate id="labSidArraySave" name="usr045Form" property="usr040labSidSave" indexId="idx">
    <input type="hidden" name="usr040labSidSave" value="<bean:write name="labSidArraySave" />">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="usr040SearchFlg" />
<html:hidden property="usr040DspFlg" />


<html:hidden property="usr040CategorySetInitFlg" />

<logic:notEmpty name="usr045Form" property="usr040CategoryOpenFlg">
<logic:iterate id="openFlg" name="usr045Form" property="usr040CategoryOpenFlg">
  <bean:define id="flg" name="openFlg" type="java.lang.String" />
  <html:hidden property="usr040CategoryOpenFlg" value="<%= flg %>" />
</logic:iterate>
</logic:notEmpty>

<html:hidden property="usr040GrpSearchGId"/>
<html:hidden property="usr040GrpSearchGName"/>
<html:hidden property="usr040GrpSearchGIdSave"/>
<html:hidden property="usr040GrpSearchGNameSave"/>

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
    <td width="0%"><img src="../user/images/header_user_01.gif" border="0" alt="<gsmsg:write key="cmn.shain.info" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.shain.info" /></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="cmn.category.entry" /> ]</td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.shain.info" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('ok');">
  <logic:equal name="usr045Form" property="usr043ProcMode" value="<%= kbnFlg %>">
    <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('delete');">
  </logic:equal>
    <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('usr043back');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>
  </td>
  </tr>
  <tr>
  <td>
  <img src="../common/images/spacer.gif" width="10" height="10" border="0" alt="">
  </td>
  </tr>
  <tr>
  <td>
  <logic:messagesPresent message="false">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tl0">
    <tr>
    <td align="left"><html:errors/></td>
    </tr>
    </table>
  </logic:messagesPresent>

  </td>
  </tr>


  <tr>
  <td>

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.category.name" /></span><span class="text_r2">※</span></td>
    <td align="left" class="td_wt" width="80%">
    <html:text style="width:275px;" maxlength="20" property="usr045CategoryName" styleClass="text_base" />
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.memo" /></span></td>
    <td align="left" class="td_type1">
    <textarea name="usr045bikou" style="width:566px" rows="5" styleClass="text_gray" onkeyup="showLengthStr(value, <%= maxLengthBiko %>, 'inputlength');" id="inputstr"><bean:write name="usr045Form" property="usr045bikou" /></textarea>
    <br>
    <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthBiko %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
    </tr>
    </table>

  </td> 
  </tr>

  <tr>
  <td>
  <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="<gsmsg:write key="cmn.spacer" />">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="100%" align="right">
    <input type="submit" value="OK" class="btn_ok1">

  <logic:equal name="usr045Form" property="usr043ProcMode" value="<%= kbnFlg %>">
    <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('delete');">
  </logic:equal>

    <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('usr043back');">
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