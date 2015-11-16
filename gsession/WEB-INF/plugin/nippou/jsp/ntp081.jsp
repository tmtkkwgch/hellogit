<%@ page pageEncoding="Windows-31J" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[Group Session] <gsmsg:write key="ntp.1" /><gsmsg:write key="anp.anp080.01" /></title>
<script language="JavaScript" src="../common/js/cmd.js"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css" type="text/css">
<link rel=stylesheet href="../nippou/css/nippou.css" type="text/css">
</head>

<% String maxLengthMikomido = String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.MAX_LENGTH_MIKOMIDO); %>

<body class="body_03" onload="showLengthId($('#inputstr0')[0], <%= maxLengthMikomido %>, 'inputlength0');showLengthId($('#inputstr1')[0], <%= maxLengthMikomido %>, 'inputlength1');showLengthId($('#inputstr2')[0], <%= maxLengthMikomido %>, 'inputlength2');showLengthId($('#inputstr3')[0], <%= maxLengthMikomido %>, 'inputlength3');showLengthId($('#inputstr4')[0], <%= maxLengthMikomido %>, 'inputlength4');">



<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<html:form action="/nippou/ntp081">

<input type="hidden" name="CMD" value="">
<html:hidden property="ntp010DspDate"/>
<html:hidden property="ntp010SelectUsrSid"/>
<html:hidden property="ntp010SelectUsrKbn"/>
<html:hidden property="ntp010SelectDate" />
<html:hidden property="ntp010NipSid" />
<html:hidden property="ntp010DspGpSid"/>
<html:hidden property="ntp020SelectUsrSid"/>
<html:hidden property="ntp030SelectUsrSid"/>
<html:hidden property="dspMod" />
<html:hidden property="listMod" />
<html:hidden property="backScreen" />

<html:hidden property="ntp100PageNum" />
<html:hidden property="ntp100Slt_page1" />
<html:hidden property="ntp100Slt_page2" />
<html:hidden property="ntp100OrderKey1" />
<html:hidden property="ntp100SortKey1" />
<html:hidden property="ntp100OrderKey2" />
<html:hidden property="ntp100SortKey2" />
<html:hidden property="ntp100SvSltGroup" />
<html:hidden property="ntp100SvSltUser" />
<html:hidden property="ntp100SvSltYearFr" />
<html:hidden property="ntp100SvSltMonthFr" />
<html:hidden property="ntp100SvSltDayFr" />
<html:hidden property="ntp100SvSltYearTo" />
<html:hidden property="ntp100SvSltMonthTo" />
<html:hidden property="ntp100SvSltDayTo" />
<html:hidden property="ntp100SvKeyWordkbn" />
<html:hidden property="ntp100SvKeyValue" />
<html:hidden property="ntp100SvOrderKey1" />
<html:hidden property="ntp100SvSortKey1" />
<html:hidden property="ntp100SvOrderKey2" />
<html:hidden property="ntp100SvSortKey2" />
<html:hidden property="ntp100SltGroup" />
<html:hidden property="ntp100SltUser" />
<html:hidden property="ntp100SltYearFr" />
<html:hidden property="ntp100SltMonthFr" />
<html:hidden property="ntp100SltDayFr" />
<html:hidden property="ntp100SltYearTo" />
<html:hidden property="ntp100SltMonthTo" />
<html:hidden property="ntp100SltDayTo" />
<html:hidden property="ntp100KeyWordkbn" />
<!--　BODY -->
<table align="center" cellpadding="0" cellspacing="5" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img alt="<gsmsg:write key="cmn.admin.setting" />" src="../common/images/header_ktool_01.gif" border="0"></td>
    <td width="100%" class="header_ktool_bg_text">[ <gsmsg:write key="ntp.1" /><gsmsg:write key="anp.anp080.01" /> ]</td>
    <td width="0%"><img alt="<gsmsg:write key="cmn.admin.setting" />" src="../common/images/header_ktool_05.gif" border="0"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img alt="" src="../common/images/header_glay_1.gif" border="0"></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" class="btn_ok1" value="OK" onClick="return buttonPush('ntp081Ok');">
    <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="return buttonPush('ntp081Back');"></td>
    <td width="0%"><img alt="" src="../common/images/header_glay_3.gif" border="0"></td>
    </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td><img style="WIDTH: 100px; HEIGHT: 10px" alt="" src="../common/images/spacer.gif" border="0" ></td>
  </tr>

  <tr>
  <td>
    <!-- エラーメッセージ -->
    <logic:messagesPresent message="false">
      <span class="TXT02"><html:errors/></span><br>
    </logic:messagesPresent>

    <table class="tl0" width="100%" cellpadding="5">

    <!-- 共有範囲 -->
    <tr>
    <td class="table_bg_A5B4E1" width="15%"><span class="text_bb1"><gsmsg:write key="schedule.123" /></span><span class="text_r2">※</span></td>
    <td class="td_type1">
    <span class="text_base"><gsmsg:write key="ntp.81" /></span><br><br>
    <html:radio name="ntp081Form" property="ntp081KyoyuFlg" styleId="ntp081KyoyuFlg0" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.CRANGE_SHARE_ALL) %>" /><label for="ntp081KyoyuFlg0"><gsmsg:write key="schedule.125" /></label>&nbsp;
    <html:radio name="ntp081Form" property="ntp081KyoyuFlg" styleId="ntp081KyoyuFlg1" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.CRANGE_SHARE_GROUP) %>" /><label for="ntp081KyoyuFlg1"><gsmsg:write key="schedule.src.2" /></label>
    </td>
    </tr>
    <!-- 時間単位 -->
    <tr>
    <td class="table_bg_A5B4E1" width="15%"><span class="text_bb1"><gsmsg:write key="cmn.time" /><gsmsg:write key="ntp.102" /></span><span class="text_r2">※</span></td>
    <td class="td_type1">
    <span class="text_base"><gsmsg:write key="ntp.82" /></span><br><br>
    <html:radio name="ntp081Form" property="ntp081HourDivision" styleId="ntp081HourDivision05" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.HOUR_DIVISION5) %>" /><label for="ntp081HourDivision05">５<gsmsg:write key="cmn.minute" /></label>&nbsp;
    <html:radio name="ntp081Form" property="ntp081HourDivision" styleId="ntp081HourDivision10" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.DF_HOUR_DIVISION) %>" /><label for="ntp081HourDivision10">１０<gsmsg:write key="cmn.minute" /></label>&nbsp;
    <html:radio name="ntp081Form" property="ntp081HourDivision" styleId="ntp081HourDivision15" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.HOUR_DIVISION15) %>" /><label for="ntp081HourDivision15">１５<gsmsg:write key="cmn.minute" /></label>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="15%"><span class="text_bb1"><gsmsg:write key="cmn.title" /><gsmsg:write key="schedule.src.16" /></span></td>
    <td class="td_type1">
    <span class="text_base"><gsmsg:write key="ntp.83" /></span><br><br>
    <span class="sc_block_color_1">　　</span>&nbsp;<span class="text_base2"><html:text maxlength="25" property="ntp081ColCmt1" style="width:185px;" /></span><br>
    <span class="sc_block_color_2">　　</span>&nbsp;<span class="text_base2"><html:text maxlength="25" property="ntp081ColCmt2" style="width:185px;" /></span><br>
    <span class="sc_block_color_3">　　</span>&nbsp;<span class="text_base2"><html:text maxlength="25" property="ntp081ColCmt3" style="width:185px;" /></span><br>
    <span class="sc_block_color_4">　　</span>&nbsp;<span class="text_base2"><html:text maxlength="25" property="ntp081ColCmt4" style="width:185px;" /></span><br>
    <span class="sc_block_color_5">　　</span>&nbsp;<span class="text_base2"><html:text maxlength="25" property="ntp081ColCmt5" style="width:185px;" /></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="15%"><span class="text_bb1"><gsmsg:write key="ntp.84" /></span></td>
    <td class="td_type1">
    <span class="text_base"><gsmsg:write key="ntp.85" /></span><br><br>
    <span>■10%</span>
    <div style="padding-left:20px;">
    <span class="text_base2"><textarea name="ntp081MikomidoCmt1" style="width:373;"rows="3" styleClass="text_base" onkeyup="showLengthStr(value, <%= maxLengthMikomido %>, 'inputlength0');" id="inputstr0"><bean:write name="ntp081Form"  property="ntp081MikomidoCmt1" /></textarea></span>
    <br>
    <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength0" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthMikomido %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </div>

    <br>

    <span>■30%</span>
    <div style="padding-left:20px;">
    <span class="text_base2"><textarea name="ntp081MikomidoCmt2" style="width:373;" rows="3" styleClass="text_base" onkeyup="showLengthStr(value, <%= maxLengthMikomido %>, 'inputlength1');" id="inputstr1"><bean:write name="ntp081Form"  property="ntp081MikomidoCmt2" /></textarea></span>
    <br>
    <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength1" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthMikomido %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </div>

    <br>

    <span>■50%</span>
    <div style="padding-left:20px;">
    <span class="text_base2"><textarea name="ntp081MikomidoCmt3" style="width:373;" rows="3" styleClass="text_base" onkeyup="showLengthStr(value, <%= maxLengthMikomido %>, 'inputlength2');" id="inputstr2"><bean:write name="ntp081Form"  property="ntp081MikomidoCmt3" /></textarea></span>
    <br>
    <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength2" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthMikomido %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </div>

    <br>

    <span>■70%</span>
    <div style="padding-left:20px;">
    <span class="text_base2"><textarea name="ntp081MikomidoCmt4" style="width:373;" rows="3" styleClass="text_base" onkeyup="showLengthStr(value, <%= maxLengthMikomido %>, 'inputlength3');" id="inputstr3"><bean:write name="ntp081Form" property="ntp081MikomidoCmt4" /></textarea></span>
    <br>
    <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength3" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthMikomido %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </div>

    <br>

    <span>■100%</span>
    <div style="padding-left:20px;">
    <span class="text_base2"><textarea name="ntp081MikomidoCmt5" style="width:373;" rows="3" styleClass="text_base" onkeyup="showLengthStr(value, <%= maxLengthMikomido %>, 'inputlength4');" id="inputstr4"><bean:write name="ntp081Form" property="ntp081MikomidoCmt5" /></textarea></span>
    <br>
    <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength4" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthMikomido %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </div>

    </td>
    </tr>


    <%--
    <tr>
    <td class="table_bg_A5B4E1" width="15%"><span class="text_bb1">日報確認者必須設定</span><span class="text_r2">※</span></td>
    <td class="td_type1">
    <html:radio name="ntp081Form" property="ntp081KakuteiFlg" styleId="ntp081KakuteiFlg0" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.KAKUTEI_INPUT_REQUIRED) %>" /><label for="ntp081KakuteiFlg0">確認者を入力必須</label>&nbsp;
    <html:radio name="ntp081Form" property="ntp081KakuteiFlg" styleId="ntp081KakuteiFlg1" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.KAKUTEI_INPUT_FREE) %>" /><label for="ntp081KakuteiFlg1">確認者を任意選択</label>
    </td>
    </tr>
    --%>
    </table>

  </td>
  </tr>

  <tr>
  <td>
  <img height="10" src="../common/images/spacer.gif" width="1" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
    <input type="button" class="btn_ok1" value="OK" onClick="return buttonPush('ntp081Ok');">
    <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="return buttonPush('ntp081Back');">
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