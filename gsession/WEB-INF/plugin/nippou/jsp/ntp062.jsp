<%@ page pageEncoding="Windows-31J" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<%
   String maxLengthSyosai = String.valueOf(1000);
%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[Group Session] <gsmsg:write key="ntp.1" /></title>
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../common/js/jquery.bgiframe.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../address/js/adr240.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../nippou/js/ntp061.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/glayer.js"></script>
<script language="JavaScript" src="../common/js/cmn110.js"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css" type="text/css">
<link rel=stylesheet href="../nippou/css/nippou.css" type="text/css">
<link rel=stylesheet href='../common/css/glayer.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href="../address/css/address.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03" onload="showLengthId($('#inputstr')[0], <%= maxLengthSyosai %>, 'inputlength');" onunload="calWindowClose();companyWindowClose();">

<logic:equal name="ntp062Form" property="ntp061PopKbn" value="0">
  <%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
</logic:equal>


<html:form action="/nippou/ntp062">

<div id="ntp061CompanyIdArea">
<html:hidden property="ntp061CompanySid" />
</div>

<div id="ntp061CompanyBaseIdArea">
<html:hidden property="ntp061CompanyBaseSid" />
</div>


<input type="hidden" name="CMD" value="">

<%@ include file="/WEB-INF/plugin/nippou/jsp/ntp060_hiddenParams.jsp" %>

<logic:notEmpty name="ntp062Form" property="ntp060Mikomi" scope="request">
<logic:iterate id="mikomi" name="ntp062Form" property="ntp060Mikomi" scope="request">
  <input type="hidden" name="ntp060Mikomi" value="<bean:write name="mikomi"/>">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="ntp062Form" property="ntp060Syodan" scope="request">
<logic:iterate id="syodan" name="ntp062Form" property="ntp060Syodan" scope="request">
  <input type="hidden" name="ntp060Syodan" value="<bean:write name="syodan"/>">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="ntp060ProcMode" />
<html:hidden property="ntp060NanSid" />
<html:hidden property="ntp061ReturnPage" />
<html:hidden property="ntp062InitFlg" />
<html:hidden property="ntp061TourokuName" />
<html:hidden property="ntp061PopKbn" />
<html:hidden property="ntp061AddCompFlg" />
<html:hidden property="ntp061Date" />
<html:hidden property="ntp061AnkenSid" />
<html:hidden property="ntp061SvCompanySid" />
<html:hidden property="ntp061SvCompanyCode" />
<html:hidden property="ntp061SvCompanyName" />
<html:hidden property="ntp061SvCompanyBaseSid" />
<html:hidden property="ntp061SvCompanyBaseName" />

<html:hidden property="ntp200NanSid" />
<html:hidden property="ntp200ProcMode" />
<html:hidden property="ntp200InitFlg" />
<html:hidden property="ntp200parentPageId" />
<html:hidden property="ntp200RowNumber" />

<logic:notEmpty name="ntp062Form" property="ntp061ChkShohinSidList" scope="request">
<logic:iterate id="item" name="ntp062Form" property="ntp061ChkShohinSidList" scope="request">
  <input type="hidden" name="ntp061ChkShohinSidList" value="<bean:write name="item"/>">
</logic:iterate>
</logic:notEmpty>


<!--　BODY -->
<logic:notEmpty name="ntp062Form" property="sv_users" scope="request">
<logic:iterate id="ulBean" name="ntp062Form" property="sv_users" scope="request">
<input type="hidden" name="sv_users" value="<bean:write name="ulBean" />">
</logic:iterate>
</logic:notEmpty>


<%-------------------------------- 案件登録画面 --------------------------------%>
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../nippou/images/header_anken_01.gif" border="0" alt="<gsmsg:write key="ntp.1" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="ntp.11" /><gsmsg:write key="cmn.import" /></span></td>
    <td width="100%" class="header_white_bg"></td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="ntp.1" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%">
    </td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.import" />" class="btn_csv_n1" onclick="buttonPush('062_import');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backNtp062');">
    </td>
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
      <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="main.src.34" /></span><span class="text_r2">※</span></td>
      <td align="left" class="td_wt" width="80%" nowrap>
        <a href="javascript:void(0);" onclick="opnTemp('ntp062SelectFiles', '<%= jp.groupsession.v2.ntp.GSConstNippou.PLUGIN_ID_NIPPOU %>', '1');">
        <img alt="<gsmsg:write key="cmn.attached" />" height="25" src="../common/images/btn_attach.gif" width="60" border="0"></a>
        &nbsp;<img src="../common/images/btn_dell.gif" alt="<gsmsg:write key="cmn.delete" />" height="25" width="60" onclick="buttonPush('delete');"><br>

        <html:select property="ntp062SelectFiles" styleClass="select01" multiple="false" size="1">
          <html:optionsCollection name="ntp062Form" property="ntp062FileLabelList" value="value" label="label" />
        </html:select>

        &nbsp;
        <span class="text_base">
            *<a href="../nippou/ntp062.do?CMD=ntp062_sample"><gsmsg:write key="reserve.111" /></a>
        </span>
        <br>
      </td>
      </tr>




    <tr>
    <td class="table_bg_A5B4E1" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.staff" /><br><br></span>
    </td>
    <td align="left" class="td_type20">

      <table width="0%" border="0">
      <tr>
      <td width="40%">

      </td>
      <td width="20%">&nbsp;</td>
      <td width="40%">
        <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup(this.form.ntp061GroupSid, 'ntp061GroupSid', '<bean:write name="ntp062Form" property="ntp061GroupSid" />', '1', '061_group', 'sv_users', '0', '0')" type="button">
      </td>
      </tr>
      <tr>
      <td width="40%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="cmn.staff" /></span></td>
      <td width="20%" align="center">&nbsp;</td>
      <td width="40%" align="left">

      <logic:notEmpty name="ntp062Form" property="ntp061GroupLavel" scope="request">
      <html:select property="ntp061GroupSid" onchange="changeGroupCombo('061_group');" styleClass="select05">

      <logic:notEmpty name="ntp062Form" property="ntp061GroupLavel" scope="request">
      <logic:iterate id="gpBean" name="ntp062Form" property="ntp061GroupLavel" scope="request">

      <bean:define id="gpValue" name="gpBean" property="value" type="java.lang.String" />
      <logic:equal name="gpBean" property="styleClass" value="0">
      <html:option value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
      </logic:equal>

      <logic:notEqual name="gpBean" property="styleClass" value="0">
      <html:option styleClass="select03" value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
      </logic:notEqual>

      </logic:iterate>
      </logic:notEmpty>

        </html:select>
        </logic:notEmpty>
        <span class="text_base8">
          <input type="button" onclick="openGroupWindow(this.form.ntp061GroupSid, 'ntp061GroupSid', '1', '061_group')" class="group_btn2" value="&nbsp;" id="ntp061GroupBtn">
          <%--
          <input type="checkbox" name="ntp061SelectUsersKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SELECT_ON) %>" id="select_user" onclick="return selectUsersList();" />
          <label for="select_user"><gsmsg:write key="cmn.select" /></label>
          --%>
       </span>
      </td>
      </tr>

      <tr>
      <td align="center">
         <!-- 登録先 -->
        <select size="5" multiple name="users_r" class="select01">
        <logic:notEmpty name="ntp062Form" property="ntp061SelectUsrLavel" scope="request">
        <logic:iterate id="urBean" name="ntp062Form" property="ntp061SelectUsrLavel" scope="request">
           <option value="<bean:write name="urBean" property="usrSid" scope="page"/>"><bean:write name="urBean" property="usiSei" scope="page"/>　<bean:write name="urBean" property="usiMei" scope="page"/></option>
          </logic:iterate>
         </logic:notEmpty>
        <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
        </select>
      </td>

      <td align="center">

        <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="moveUser('061_rightarrow');"><br><br>
        <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="moveUser('061_leftarrow');">

      </td>
      <td>
         <!-- グループ -->
         <select size="5" multiple name="users_l" class="select01">
         <logic:notEmpty name="ntp062Form" property="ntp061BelongLavel" scope="request">
          <logic:iterate id="urBean" name="ntp062Form" property="ntp061BelongLavel" scope="request">
            <option value="<bean:write name="urBean" property="usrSid" scope="page"/>"><bean:write name="urBean" property="usiSei" scope="page"/>　<bean:write name="urBean" property="usiMei" scope="page"/></option>
          </logic:iterate>
         </logic:notEmpty>
        <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
        </select>
      </td>
      </tr>
      </table>
    </td>
    </tr>


    <tr>
    <td align="left" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb1"><gsmsg:write key="ntp.58" /></span></td>
    <td align="left" valign="middle" class="td_type20" width="85%">
<%--
    <logic:equal name="ntp062Form" property="ntp061PopKbn" value="0">
      <img alt="<gsmsg:write key="cmn.add" />" src="../nippou/images/arrow_btn_add2.gif" border="0" onClick="buttonPush('searchShohin');">
    </logic:equal>
    <logic:notEqual name="ntp062Form" property="ntp061PopKbn" value="0">
--%>
      <img alt="<gsmsg:write key="cmn.add" />" src="../nippou/images/arrow_btn_add2.gif" border="0" id="itmAddBtn">
<%--
    </logic:notEqual>
--%>
    <img alt="<gsmsg:write key="cmn.delete" />" src="../nippou/images/arrow_btn_delete2.gif" border="0" onClick="buttonPush('delShohin');"><br>

    <html:select name="ntp062Form" property="ntp061SelectShohin" size="6" multiple="true" styleClass="select01" style="width: 400px;">
      <logic:notEmpty name="ntp062Form" property="ntp061ShohinList">
        <html:optionsCollection name="ntp062Form" property="ntp061ShohinList" value="value" label="label" />
      </logic:notEmpty>
      <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
    </html:select>
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
    <td width="100%" align="right">
      <input type="button" value="<gsmsg:write key="cmn.import" />" class="btn_csv_n1" onclick="buttonPush('062_import');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backNtp062');">
    </td>
    </tr>
    </table>
  </td>
  </tr>

  </table>

</td>
</tr>
</table>




<%-------------------------------- 商品選択 --------------------------------%>
<logic:equal name="ntp062Form" property="ntp061ItmKbn" value="0">
<div id="itmArea" style="position:absolute;bottom:0;display:none;z-index:100;width:100%;height:100%;">
</logic:equal>
<logic:notEqual name="ntp062Form" property="ntp061ItmKbn" value="0">
<div id="itmArea" style="position:absolute;bottom:0;display:block;z-index:100;width:100%;height:100%;">
</logic:notEqual>

<logic:equal name="ntp062Form" property="ntp061PopKbn" value="0">
<div class="itmSelArea" align="center" style="background-color:#cccccc;padding:3px;">
</logic:equal>

<logic:notEqual name="ntp062Form" property="ntp061PopKbn" value="0">
<div class="itmSelArea2" align="center" style="background-color:#cccccc;padding:3px;">
</logic:notEqual>

<logic:notEmpty name="ntp062Form" property="ntp061ItmSvChkShohinSidList" scope="request">
<logic:iterate id="item" name="ntp062Form" property="ntp061ItmSvChkShohinSidList" scope="request">
  <input type="hidden" name="ntp061ItmSvChkShohinSidList" value="<bean:write name="item"/>">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="ntp062Form" property="ntp061ItmSelectedSid" scope="request">
<logic:iterate id="item" name="ntp062Form" property="ntp061ItmSelectedSid" scope="request">
  <input type="hidden" name="ntp061ItmChkShohinSidList" value="<bean:write name="item"/>">
</logic:iterate>
</logic:notEmpty>


<html:hidden property="ntp061ItmNhnSid" />
<html:hidden property="ntp061ItmProcMode" />
<html:hidden property="ntp061ItmReturnPage" />
<html:hidden property="ntp061ItmDspMode" />
<html:hidden property="ntp061ItmInitFlg" />

<!--　BODY -->
<table width="100%" cellpadding="5" cellspacing="0" style="background-color:#ffffff;">
<tr>
<td width="100%" align="center">

  <table width="99%" cellpadding="0" cellspacing="0">
  <!-- <table width="70%" cellpadding="0" cellspacing="0"> -->
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../nippou/images/header_nippou_02.gif" border="0" alt="<gsmsg:write key="ntp.1" />"></td>
    <td width="0%" class="header_white_bg_text" nowrap>[ <gsmsg:write key="ntp.58" /><gsmsg:write key="cmn.list" /> ]</td>
    <td width="100%" class="header_white_bg">
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="ntp.1" />"></td>
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
    <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="cmn.category" /></span></td>
    <td align="left" class="td_type20" width="85%" colspan="3">
    <html:select name="ntp062Form" property="ntp061CatSid" styleClass="select01" style="width:200px;">
    <logic:notEmpty name="ntp062Form" property="ntp061CategoryList">
    <html:optionsCollection name="ntp062Form" property="ntp061CategoryList" value="value" label="label" />
    </logic:notEmpty>
    </html:select>
    </td>
    </tr>

    <tr>
    <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="ntp.122" /></span></td>
    <td align="left" class="td_type20" width="85%" colspan="3">
    <html:text name="ntp062Form" property="ntp061ItmNhnCode" maxlength="13" style="width:131px;" />
    </td>
    </tr>

    <tr>
    <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="ntp.154" /></span></td>
    <td align="left" class="td_type20" width="85%" colspan="3"><html:text name="ntp062Form" property="ntp061ItmNhnName" maxlength="100" style="width:515px;" /></td>
    </tr>

    <tr>
    <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="ntp.76" /></span></td>
    <td align="left" class="td_type20"><html:text name="ntp062Form" property="ntp061ItmNhnPriceSale" maxlength="12" style="text-align:right; width:113px;" />&nbsp;<span class="text_base7"><gsmsg:write key="project.103" /></span>
    <html:radio name="ntp062Form" property="ntp061ItmNhnPriceSaleKbn" styleId="ntp061ItmNhnPriceSaleKbn1" value="<%= String.valueOf(jp.groupsession.v2.ntp.ntp130.Ntp130Biz.PRICE_MORE) %>" /><span class="text_base7"><label for="ntp061ItmNhnPriceSaleKbn1"><gsmsg:write key="ntp.66" /></label></span>&nbsp;
    <html:radio name="ntp062Form" property="ntp061ItmNhnPriceSaleKbn" styleId="ntp061ItmNhnPriceSaleKbn2" value="<%= String.valueOf(jp.groupsession.v2.ntp.ntp130.Ntp130Biz.PRICE_LESS) %>" /><span class="text_base7"><label for="ntp061ItmNhnPriceSaleKbn2"><gsmsg:write key="ntp.67" /></label></span>&nbsp;
    </td>

    <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="ntp.77" /></span></td>
    <td align="left" class="td_type20" nowrap><html:text name="ntp062Form" property="ntp061ItmNhnPriceCost" maxlength="12" style="text-align:right; width:113px;" />&nbsp;<span class="text_base7"><gsmsg:write key="project.103" /></span>
    <html:radio name="ntp062Form" property="ntp061ItmNhnPriceCostKbn" styleId="ntp061ItmNhnPriceCostKbn1" value="<%= String.valueOf(jp.groupsession.v2.ntp.ntp130.Ntp130Biz.PRICE_MORE) %>" /><span class="text_base7"><label for="ntp061ItmNhnPriceCostKbn1"><gsmsg:write key="ntp.66" /></label></span>&nbsp;
    <html:radio name="ntp062Form" property="ntp061ItmNhnPriceCostKbn" styleId="ntp061ItmNhnPriceCostKbn2" value="<%= String.valueOf(jp.groupsession.v2.ntp.ntp130.Ntp130Biz.PRICE_LESS) %>" /><span class="text_base7"><label for="ntp061ItmNhnPriceCostKbn2"><gsmsg:write key="ntp.67" /></label></span>&nbsp;
    </td>
    </tr>

    <tr>
    <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="cmn.sortkey" />１</span></td>
    <td align="left" class="td_type20" width="35%" nowrap>
    <html:select name="ntp062Form" property="ntp061ItmSortKey1" styleClass="select01" style="width: 100px;">
    <logic:notEmpty name="ntp062Form" property="ntp061ItmSortList">
    <html:optionsCollection name="ntp062Form" property="ntp061ItmSortList" value="value" label="label" />
    </logic:notEmpty>
    </html:select>
    <html:radio name="ntp062Form" property="ntp061ItmOrderKey1" styleId="ntp061ItmOrderKey11" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.ORDER_KEY_ASC) %>" /><span class="text_base7"><label for="ntp061ItmOrderKey11"><gsmsg:write key="cmn.order.asc" /></label></span>&nbsp;
    <html:radio name="ntp062Form" property="ntp061ItmOrderKey1" styleId="ntp061ItmOrderKey12" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.ORDER_KEY_DESC) %>" /><span class="text_base7"><label for="ntp061ItmOrderKey12"><gsmsg:write key="cmn.order.desc" /></label></span>&nbsp;
    </td>

    <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="cmn.sortkey" />２</span></td>
    <td align="left" class="td_type20" width="35%" nowrap>
    <html:select name="ntp062Form" property="ntp061ItmSortKey2" styleClass="select01" style="width: 100px;">
    <logic:notEmpty name="ntp062Form" property="ntp061ItmSortList">
    <html:optionsCollection name="ntp062Form" property="ntp061ItmSortList" value="value" label="label" />
    </logic:notEmpty>
    </html:select>
    <html:radio name="ntp062Form" property="ntp061ItmOrderKey2" styleId="ntp061ItmOrderKey21" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.ORDER_KEY_ASC) %>" /><span class="text_base7"><label for="ntp061ItmOrderKey21"><gsmsg:write key="cmn.order.asc" /></label></span>&nbsp;
    <html:radio name="ntp062Form" property="ntp061ItmOrderKey2" styleId="ntp061ItmOrderKey22" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.ORDER_KEY_DESC) %>" /><span class="text_base7"><label for="ntp061ItmOrderKey22"><gsmsg:write key="cmn.order.desc" /></label></span>&nbsp;
    </td>
    </tr>
    </table>

  </td>
  </tr>
  <tr>
  <td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td>
  </tr>
  <tr>
  <td align="center">
  <input type="button" name="btn_search" class="btn_search_n1" value="<gsmsg:write key="cmn.search" />" onClick="return itemSearchPush('itmsearch');">
  </td>
  </tr>
  <tr>
  <td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td>
  </tr>

  <logic:notEmpty name="ntp062Form" property="ntp061ItmShohinList">
  <tr>
  <td align="right">
    <bean:size id="count1" name="ntp062Form" property="ntp061ItmPageCmbList" scope="request" />
    <logic:greaterThan name="count1" value="1">
    <table width="100%" cellpadding="5" cellspacing="0">
      <td align="right">
        <img src="../common/images/arrow_w2.gif" alt="<gsmsg:write key="cmn.previous.page" />" width="13" height="13" onClick="buttonPush('itmprevPage');">
        <html:select property="ntp061ItmPageTop" onchange="itmChangePage(0);" styleClass="itmselectbox">
          <html:optionsCollection name="ntp062Form" property="ntp061ItmPageCmbList" value="value" label="label" />
        </html:select>
        <img src="../common/images/arrow_w.gif" alt="<gsmsg:write key="project.48" />" width="13" height="13" onClick="buttonPush('itmnextPage');"></td>
      </tr>
    </table>
    </logic:greaterThan>
  </td>
  </tr>
  </logic:notEmpty>

  <tr>
  <td align="left">
    <table class="tl0 table_td_border2" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>

    <logic:notEqual name="ntp062Form" property="ntp061ItmDspMode" value="search">
    <th align="center" class="table_bg_7D91BD" width="5%" nowrap><span class="text_tlw"></span></th>
    </logic:notEqual>

    <th align="center" class="table_bg_7D91BD" width="10%" nowrap><span class="text_tlw"><gsmsg:write key="ntp.122" /></span></th>
    <th align="center" class="table_bg_7D91BD" width="25%" nowrap><span class="text_tlw"><gsmsg:write key="ntp.154" /></span></th>
    <th align="center" class="table_bg_7D91BD" width="10%" nowrap><span class="text_tlw"><gsmsg:write key="ntp.76" /></span></th>
    <th align="center" class="table_bg_7D91BD" width="10%" nowrap><span class="text_tlw"><gsmsg:write key="ntp.77" /></span></th>
    <th align="center" class="table_bg_7D91BD" width="10%" nowrap><span class="text_tlw"><gsmsg:write key="bmk.15" /></span></th>
    <th align="center" class="table_bg_7D91BD" width="10%" nowrap><span class="text_tlw"><gsmsg:write key="ntp.155" /></span></th>
    </tr>
    <logic:notEmpty name="ntp062Form" property="ntp061ItmShohinList">
    <bean:define id="tdColor" value="" />

    <% String[] tdColors = new String[] {"td_type1", "td_type_usr"}; %>

    <logic:iterate id="syohinMdl" name="ntp062Form" property="ntp061ItmShohinList" indexId="idx">
    <% tdColor = tdColors[(idx.intValue() % 2)]; %>


    <bean:define id="backclass" value="td_line_color" />
    <bean:define id="backclass_no_edit" value="td_line_no_edit_color" />
    <bean:define id="backpat" value="<%= String.valueOf((idx.intValue() % 2) + 1) %>" />
    <bean:define id="back" value="<%= String.valueOf(backclass) + String.valueOf(backpat) %>" />
    <bean:define id="back_no_edit" value="<%= String.valueOf(backclass_no_edit) + String.valueOf(backpat) %>" />
    <bean:define id="sid" name="syohinMdl" property="nhnSid" type="java.lang.Integer" />

    <% if (Integer.valueOf(backpat) == 1) { %>
    <tr align="center" style="cursor:pointer;" class="<%= String.valueOf(back) %>" id="tr_<%= Integer.toString(sid.intValue()) %>" onclick="clickShohinName('1', <%= Integer.toString(sid.intValue()) %>);">
    <% } else { %>
    <tr align="center" style="cursor:pointer;" class="<%= String.valueOf(back) %>" id="tr_<%= Integer.toString(sid.intValue()) %>" onclick="clickShohinName('2', <%= Integer.toString(sid.intValue()) %>);">
    <% } %>


    <!-- チェックボックス -->

    <td align="center">

    <% if (Integer.valueOf(backpat) == 1) { %>
      <html:multibox property="ntp061ItmChkShohinSidList" value="<%= Integer.toString(sid.intValue()) %>" styleId="1" onclick="clickMulti();"/>
    <% } else { %>
      <html:multibox property="ntp061ItmChkShohinSidList" value="<%= Integer.toString(sid.intValue()) %>" styleId="2" onclick="clickMulti();"/>
    <% } %>

    </td>

    <!-- 商品コード -->
    <td align="left">
    <table><tr><td class="shohin_category">
    <div><bean:write name="syohinMdl" property="nscName" /></div>
    </td></tr></table>
    <logic:equal name="ntp062Form" property="ntp061ItmDspMode" value="search">
    <a href="#" onclick="return buttonSubmit('edit','<bean:write name="syohinMdl" property="nhnSid" />') ">
    <span class="text_link"><bean:write name="syohinMdl" property="nhnCode" /></span>
    </a>
    </logic:equal>
    <logic:notEqual name="ntp062Form" property="ntp061ItmDspMode" value="search">
    <span class="text_base7"><bean:write name="syohinMdl" property="nhnCode" /></span>
    </logic:notEqual>
    </td>

    <!-- 商品名 -->
    <td align="left">
    <logic:equal name="ntp062Form" property="ntp061ItmDspMode" value="search">
    <a href="#" onclick="return buttonSubmit('edit','<bean:write name="syohinMdl" property="nhnSid" />') ">
    <span class="text_link"><bean:write name="syohinMdl" property="nhnName" /></span>
    </a>
    </logic:equal>
    <logic:notEqual name="ntp062Form" property="ntp061ItmDspMode" value="search">
    <span class="text_base7"><bean:write name="syohinMdl" property="nhnName" /></span>
    </logic:notEqual>
    </td>

    <!-- 販売金額 -->
    <td align="right">
    <span class="text_base7">￥<bean:write name="syohinMdl" property="ntp130PriceSale" /></span>
    </td>

    <!-- 原価金額 -->
    <td align="right">
    <span class="text_base7">￥<bean:write name="syohinMdl" property="ntp130PriceCost" /></span>
    </td>

    <!-- 登録日 -->
    <td align="right">
    <span class="text_base7"><bean:write name="syohinMdl" property="ntp130ADate" /></span>
    </td>

    <!-- 更新日 -->
    <td align="right">
    <span class="text_base7"><bean:write name="syohinMdl" property="ntp130EDate" /></span>
    </td>


    </tr>

    </logic:iterate>
    </logic:notEmpty>
    </table>
  </td>
  </tr>

  <logic:notEmpty name="ntp062Form" property="ntp061ItmShohinList">
  <tr>
  <td align="right">
    <bean:size id="count1" name="ntp062Form" property="ntp061ItmPageCmbList" scope="request" />
    <logic:greaterThan name="count1" value="1">
      <table width="100%" cellpadding="5" cellspacing="0">
        <td align="right">
          <img src="../common/images/arrow_w2.gif" alt="<gsmsg:write key="cmn.previous.page" />" width="13" height="13" onClick="buttonPush('itmprevPage');">
          <html:select property="ntp061ItmPageBottom" onchange="itmChangePage(1);" styleClass="itmselectbox">
            <html:optionsCollection name="ntp062Form" property="ntp061ItmPageCmbList" value="value" label="label" />
          </html:select>
          <img src="../common/images/arrow_w.gif" alt="<gsmsg:write key="project.48" />" width="13" height="13" onClick="buttonPush('itmnextPage');"></td>
        </tr>
      </table>
    </logic:greaterThan>
  </td>
  </tr>
  </logic:notEmpty>

  </table>

  <input type="button" class="btn_base1" value="選択" onClick="return itemSelectPush('itmok');">
  <input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_close_n1" id="itmClose">

</td>
</tr>
</table>

</div>
</div>

</html:form>

<logic:equal name="ntp062Form" property="ntp061PopKbn" value="0">
  <%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</logic:equal>



<%-------------------------------- 商品ダイアログ内遷移時 --------------------------------%>
<logic:notEqual name="ntp062Form" property="ntp061ItmKbn" value="0">
<script type="text/javascript">
Glayer.show();
$(".itmselectbox").css('visibility','visible');
</script>
</logic:notEqual>

<%-------------------------------- 登録確認ダイアログ --------------------------------%>
<logic:notEqual name="ntp062Form" property="ntp061PopKbn" value="0">
<logic:notEqual name="ntp062Form" property="ntp061AddFlg" value="0">
<div id="dialogAddOk" title="<gsmsg:write key="cmn.entry" /><gsmsg:write key="cmn.check" />" style="display:none">
    <p>
      <span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><gsmsg:write key="ntp.78" /></b>
    </p>
</div>
<script type="text/javascript">
addOkOpen();
</script>
</logic:notEqual>
</logic:notEqual>

<%-------------------------------- 登録完了ダイアログ --------------------------------%>
<logic:notEqual name="ntp062Form" property="ntp061PopKbn" value="0">
<logic:notEqual name="ntp062Form" property="ntp061AddCompFlg" value="0">
<div id="dialogAddComp" title="<gsmsg:write key="cmn.entry" /><gsmsg:write key="ntp.75" />" style="display:none">
    <p>
      <span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><gsmsg:write key="ntp.79" /></b>
    </p>
</div>
<script type="text/javascript">
addCompOpen();
</script>
</logic:notEqual>
</logic:notEqual>



<%-------------------------------- 商品検索時エラーダイアログ --------------------------------%>
<div id="dialog_error" class="error_dialog" title="" style="display:none">
    <p>
      <table>
        <tr>
          <td class="ui-icon ui-icon-alert" valign="middle"></td>
          <td id="error_msg" valign="middle" style="padding-left:20px;" class="shohinErrorStr"></td>
        </tr>
      </table>
    </p>
</div>

<div id="mikomidoPop" title="<gsmsg:write key="ntp.84" />" style="display:none;">
  <p>
    <div style="border:solid 1px;border-color:#9a9a9a;height:300px;overflow-y: scroll">
      <table class="table_td_border2" width="100%" cellpadding="0" cellspacing="0">
        <logic:notEmpty name="ntp062Form" property="ntp061MikomidoMsgList">
          <logic:iterate id="mmdMdl" name="ntp062Form" property="ntp061MikomidoMsgList">
            <tr>
              <td class="table_bg_A5B4E1" width="20%" align="center">
                <span style="font-size:16px;font-weight:bold;color:#333333;"><bean:write name="mmdMdl" property="nmmName" /></span>
              </td>

              <td width="80%">
                <span style="font-size:14px;font-weight:bold;color:#333333;"><bean:write name="mmdMdl" property="nmmMsg" filter="false" /></span>
              </td>
            </tr>
          </logic:iterate>
        </logic:notEmpty>
      </table>
    </div>
  </p>
</div>

</body>
</html:html>