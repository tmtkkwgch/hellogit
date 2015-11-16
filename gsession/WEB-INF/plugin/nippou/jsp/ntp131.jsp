<%@ page pageEncoding="Windows-31J" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<%
   String maxLengthHosoku = String.valueOf(1000);
%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[Group Session] <gsmsg:write key="ntp.1" /></title>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../nippou/js/ntp131.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../nippou/css/nippou.css?<%= GSConst.VERSION_PARAM %>" type="text/css">

</head>

<body class="body_03" onload="showLengthId($('inputstr'), <%= maxLengthHosoku %>, 'inputlength');">
<input type="hidden" name="helpPrm" value="<bean:write name="ntp131Form" property="ntp130ProcMode" />">
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<html:form action="/nippou/ntp131">

<input type="hidden" name="CMD" value="">
<%@ include file="/WEB-INF/plugin/nippou/jsp/ntp130_hiddenParams.jsp" %>
<html:hidden property="ntp130DspKbn" />
<html:hidden property="ntp130EditSid" />

<!--Å@BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../nippou/images/header_nippou_02.gif" border="0" alt="<gsmsg:write key="ntp.1" />"></td>
    <td width="0%" class="header_white_bg_text" nowrap>[ <gsmsg:write key="ntp.58" /><gsmsg:write key="cmn.entry" /> ]</td>
    <td width="100%" class="header_white_bg">
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="ntp.1" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%">
      <logic:equal name="ntp131Form" property="ntp130ProcMode" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.CMD_EDIT) %>">
        <input type="button" value="<gsmsg:write key="cmn.register.copy" />" class="btn_base1" onClick="buttonCopy('131_copy', 'add');">
      </logic:equal>
    </td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" class="btn_ok1" value="ÇnÇj" onClick="return buttonPush2('ok');">
    <logic:equal name="ntp131Form" property="ntp130ProcMode" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.CMD_EDIT) %>">
    <input type="button" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onClick="return buttonPush2('del');">
    </logic:equal>
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush2('backNtp131');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>
  </td>
  </tr>

  <tr>
  <td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td>
  </tr>

  <logic:messagesPresent message="false">
  <tr><td align="left"><span class="TXT02"><html:errors/></span></td></tr>
  <tr><td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td></tr>
  </logic:messagesPresent>

  <tr>
  <td align="left">

    <table width="100%" class="tl0" border="0" cellpadding="5">


    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.category" /></span><span class="text_r2">Å¶</span></td>
    <td align="left" class="td_type20" width="90%">
    <html:select name="ntp131Form" property="ntp130SelCategorySid" styleClass="select01" style="width:200px;">
    <logic:notEmpty name="ntp131Form" property="ntp130CategoryList">
    <html:optionsCollection name="ntp131Form" property="ntp130CategoryList" value="value" label="label" />
    </logic:notEmpty>
    </html:select>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><gsmsg:write key="ntp.122" /></span><span class="text_r2">Å¶</span></td>
    <td align="left" class="td_type20" width="90%">
    <html:text name="ntp131Form" property="ntp131ShohinCode" maxlength="13" style="width:131px;" />
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><gsmsg:write key="ntp.154" /></span><span class="text_r2">Å¶</span></td>
    <td align="left" class="td_type20" width="90%">
    <html:text name="ntp131Form" property="ntp131ShohinName" maxlength="50" style="width:515px;" />
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1">îÃîÑâøäi</span></td>
    <td align="left" class="td_type20" width="90%">
    <html:text name="ntp131Form" property="ntp131PriceSale" maxlength="9" style="text-align:right; width:113px;" />&nbsp;<span class="text_base7"><gsmsg:write key="project.103" /></span>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1">å¥âøâøäi</span></td>
    <td align="left" class="td_type20" width="90%">
    <html:text name="ntp131Form" property="ntp131PriceCost" maxlength="9" style="text-align:right; width:113px;" />&nbsp;<span class="text_base7"><gsmsg:write key="project.103" /></span>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1">ï‚ë´éñçÄ</span></td>
    <td align="left" class="td_type20" width="90%">
    <textarea name="ntp131Hosoku"  style="width:488px" rows="10" styleClass="text_gray" onkeyup="showLengthStr(value, <%= maxLengthHosoku %>, 'inputlength');" id="inputstr"><bean:write name="ntp131Form" property="ntp131Hosoku" /></textarea>
    <br>
    <span class="font_string_count"><gsmsg:write key="wml.js.15" /></span><span id="inputlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthHosoku %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
    </tr>
    </table>
  </td>
  </tr>

  <tr>
  <td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td>
  </tr>

  <tr>
  <td align="left">

    <table width="100%">
    <tr>
    <td width="50%" align="left" cellpadding="5" cellspacing="0">
      <logic:equal name="ntp131Form" property="ntp130ProcMode" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.CMD_EDIT) %>">
        <input type="button" value="<gsmsg:write key="cmn.register.copy" />" class="btn_base1" onClick="buttonCopy('131_copy', 'add');">
      </logic:equal>
    </td>
    <td width="50%" align="right" cellpadding="5" cellspacing="0">
    <input type="button" class="btn_ok1" value="ÇnÇj" onClick="return buttonPush2('ok');">
    <logic:equal name="ntp131Form" property="ntp130ProcMode" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.CMD_EDIT) %>">
    <input type="button" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onClick="return buttonPush2('del');">
    </logic:equal>
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush2('backNtp131');">
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