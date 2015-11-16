<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<% String maxLengthBiko = String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.MAX_LENGTH_BIKO); %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.reserve" /> [ <gsmsg:write key="reserve.68" /> ]</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../reserve/css/reserve.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">
<html:form action="/reserve/rsv092">
<input type="hidden" name="CMD" value="">

<html:hidden property="rsvBackPgId" />
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsvSelectedGrpSid" />
<html:hidden property="rsvSelectedSisetuSid" />
<html:hidden property="rsv050SortRadio" />
<html:hidden property="rsv080EditGrpSid" />
<html:hidden property="rsv080SortRadio" />
<html:hidden property="rsv090InitFlg" />
<html:hidden property="rsv090ProcMode" />
<html:hidden property="rsv090EditGrpSid" />
<html:hidden property="rsv090EditSisetuSid" />
<html:hidden property="rsv090SisetuId" />
<html:hidden property="rsv090SisetuName" />
<html:hidden property="rsv090SisanKanri" />
<html:hidden property="rsv090Prop1Value" />
<html:hidden property="rsv090Prop2Value" />
<html:hidden property="rsv090Prop3Value" />
<html:hidden property="rsv090Prop4Value" />
<html:hidden property="rsv090Prop5Value" />
<html:hidden property="rsv090Prop6Value" />
<html:hidden property="rsv090Prop7Value" />
<html:hidden property="rsv090Biko" />
<html:hidden property="rsv090SisetuImgDefoValue" />
<html:hidden property="rsv090PlaceComment" />
<html:hidden property="rsv090apprKbn" />

<html:hidden property="rsv090SisetuIdDspKbn" />
<html:hidden property="rsv090SisanKanriDspKbn" />
<html:hidden property="rsv090Prop1ValueDspKbn" />
<html:hidden property="rsv090Prop2ValueDspKbn" />
<html:hidden property="rsv090Prop3ValueDspKbn" />
<html:hidden property="rsv090Prop4ValueDspKbn" />
<html:hidden property="rsv090Prop5ValueDspKbn" />
<html:hidden property="rsv090Prop6ValueDspKbn" />
<html:hidden property="rsv090Prop7ValueDspKbn" />
<html:hidden property="rsv090BikoDspKbn" />
<html:hidden property="rsv090PlaceCommentDspKbn" />
<html:hidden property="rsv090SisetuImgDefoDspKbn" />
<html:hidden property="rsv090apprKbnDspKbn" />

<html:hidden property="rsv092DelExeFlg" />

<%@ include file="/WEB-INF/plugin/reserve/jsp/rsvHidden.jsp" %>

<logic:notEmpty name="rsv092Form" property="rsv100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="rsv092Form" property="rsv100CsvOutField" scope="request">
    <input type="hidden" name="rsv100CsvOutField" value="<bean:write name="csvOutField"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv092Form" property="rsvIkkatuTorokuKey" scope="request">
  <logic:iterate id="key" name="rsv092Form" property="rsvIkkatuTorokuKey" scope="request">
    <input type="hidden" name="rsvIkkatuTorokuKey" value="<bean:write name="key"/>">
  </logic:iterate>
</logic:notEmpty>

<input type="hidden" name="helpPrm" value="">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table width="100%">
<tr>
<td width="100%" align="center">

  <table width="600px" class="tl0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%">
      <img src="../reserve/images/header_reserve_01.gif" border="0" alt="<gsmsg:write key="cmn.reserve" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.reserve" /></span></td>
    <td width="100%" class="header_white_bg_text"> [ <gsmsg:write key="reserve.68" /> ]</td>
    <td width="0%" class="header_white_bg">
      <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../reserve/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.setting2" />" class="btn_base1" onclick="buttonPush('sisetu_img_touroku');">
      <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onclick="buttonPush('back_to_sisetu_touroku');">
    </td>
    <td width="0%"><img src="../reserve/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
    <logic:messagesPresent message="false"><html:errors /></logic:messagesPresent>
    <img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt="">
  </td></tr>
  <tr>
  <td>

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.image" /></span></td>
      <td align="left" class="td_wt table_pad">
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
        <td width="0%" align="left" valign="top" nowrap>
          <html:select property="rsv092selectFiles" styleClass="select01" multiple="true" size="5">
            <html:optionsCollection name="rsv092Form" property="rsv092FileLabelList" value="value" label="label" />
          </html:select>
        </td>
        <td width="0%" align="left" valign="top" nowrap><img src="../common/images/spacer.gif" width="10px" height="1px" border="0"></td>
        <td width="100%" align="left" valign="middle">
          <input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTempPlus('rsv092selectFiles', '<bean:write name="rsv092Form" property="rsv010pluginId" />', '0', '6', 'upPlaceImgRsv092');">
          &nbsp;
          <input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="buttonPush('delete');">
        </td>
        </tr>
        </table>
      </td>
      </tr>

      <tr>
      <td>
        <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">
      </td>
      </tr>
    </table>
  </td>
  </tr>

  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="100%" align="right">
      <input type="button" value="<gsmsg:write key="cmn.setting2" />" class="btn_base1" onclick="buttonPush('sisetu_img_touroku');">
      <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onclick="buttonPush('back_to_sisetu_touroku');">
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