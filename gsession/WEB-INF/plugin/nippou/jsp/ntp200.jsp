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
<title>[Group Session] <gsmsg:write key="ntp.1" /></title>
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/glayer.js"></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../nippou/js/ntp200.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../nippou/css/nippou.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href='../common/css/glayer.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

</head>

<body class="body_03" onunload="calWindowClose();">

<html:form action="/nippou/ntp200">

<input type="hidden" name="CMD" value="">
<html:hidden property="ntp200NanSid" />
<html:hidden property="ntp200ProcMode" />
<html:hidden property="ntp200InitFlg" />
<html:hidden property="ntp200parentPageId" />
<html:hidden property="ntp200RowNumber" />
<html:hidden property="ntp200SortKey1" />
<html:hidden property="ntp200OrderKey1" />

<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="99%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../nippou/images/header_anken_01.gif" border="0" alt="<gsmsg:write key="ntp.1" />"></td>
    <td width="0%" class="header_white_bg_text" nowrap><gsmsg:write key="ntp.11" />[ <gsmsg:write key="ntp.11" /><gsmsg:write key="cmn.search" /> ]</td>
    <td width="100%" class="header_white_bg">
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="ntp.1" />"></td>
    </tr>
    </table>


  </td>
  </tr>

  <tr>
  <td><img src="../common/images/spacer.gif" style="width:100px; height:3px;" border="0" alt=""></td>
  </tr>

  <logic:messagesPresent message="false">
  <tr><td align="left"><span class="TXT02"><html:errors/></span></td></tr>
  <tr><td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td></tr>
  </logic:messagesPresent>

  <tr>
  <td align="right">
    <input type="button" value="<gsmsg:write key="ntp.11" /><gsmsg:write key="cmn.add" />" class="btn_add_n1" onClick="buttonPush('add');">
  </td>
  </tr>

  <tr>
  <td><img src="../common/images/spacer.gif" style="width:100px; height:3px;" border="0" alt=""></td>
  </tr>

  <tr>
  <td align="left">

    <table width="100%" class="tl0" border="0" cellpadding="5">

    <tr>
      <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="ntp.29" /></span></td>
      <td align="left" class="td_type20" width="0%"><html:text name="ntp200Form" property="ntp200NanCode" maxlength="8" style="width:131px;" /></td>
      <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="ntp.11" />名</span></td>
      <td align="left" class="td_type20" width="0%" ><html:text name="ntp200Form" property="ntp200NanName" maxlength="50" style="width:335px;" /></td>
    </tr>

    <tr>
      <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="address.7" /></span></td>
      <td align="left" class="td_type20" width="0%"><html:text name="ntp200Form" property="ntp200AcoCode" maxlength="8" style="width:131px;" /></td>
      <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="cmn.company.name" /></span></td>
      <td align="left" class="td_type20" width="0%"><html:text name="ntp200Form" property="ntp200AcoName" maxlength="50" style="width:335px;" /></td>
    </tr>

    <tr>
      <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="address.9" /></span></td>
      <td align="left" class="td_type20" width="0%"><html:text name="ntp200Form" property="ntp200AcoNameKana" maxlength="100" style="width:215px;" /></td>
      <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="ntp.150" /></span></td>
      <td align="left" class="td_type20" width="0%"><html:text name="ntp200Form" property="ntp200AbaName" maxlength="50" style="width:335px;" /></td>
    </tr>


    <tr>
    <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="ntp.154" /><gsmsg:write key="cmn.category" /></span></td>
    <td align="left" class="td_type20" width="0%">
      <html:select name="ntp200Form" property="ntp200CatSid" styleClass="select01" style="width: 220px;">
        <logic:notEmpty name="ntp200Form" property="ntp200CategoryList">
        <html:optionsCollection name="ntp200Form" property="ntp200CategoryList" value="value" label="label" />
        </logic:notEmpty>
      </html:select>
    </td>
    <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="ntp.154" /></span></td>
    <td align="left" class="td_type20" width="85%" colspan="3"><html:text name="ntp200Form" property="ntp200ShohinName" maxlength="50" style="width:335px;" /></td>
    </tr>

    <tr>
      <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="ntp.71" /></span></td>
      <td align="left" class="td_type20" width="0%">
        <logic:notEmpty name="ntp200Form" property="ntp200StateList">
        <logic:iterate id="stateMdl" name="ntp200Form" property="ntp200StateList" indexId="stateidx">
        <bean:define  id="stateVal" name="stateMdl" property="value" />
        <% String idForState = "ntp200State" + String.valueOf(stateidx); %>
        <html:radio name="ntp200Form" property="ntp200State" styleId="<%= idForState %>"  value="<%= String.valueOf(stateVal) %>" /><span class="text_base7"><label for="<%= idForState %>"><bean:write  name="stateMdl" property="label" /></label></span>&nbsp;</wbr>
        </logic:iterate>
        </logic:notEmpty>
      </td>
      <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="ntp.64" /></span></td>
      <td align="left" class="td_type20" width="0%">
        <logic:notEmpty name="ntp200Form" property="ntp200AnkenStateList">
        <logic:iterate id="anstateMdl" name="ntp200Form" property="ntp200AnkenStateList" indexId="anstateidx">
        <bean:define  id="anstateVal" name="anstateMdl" property="value" />
        <% String idForAnState = "ntp200AnkenState" + String.valueOf(anstateidx); %>
        <html:radio name="ntp200Form" property="ntp200AnkenState" styleId="<%= idForAnState %>"  value="<%= String.valueOf(anstateVal) %>" /><span class="text_base7"><label for="<%= idForAnState %>"><bean:write  name="anstateMdl" property="label" /></label></span>&nbsp;</wbr>
        </logic:iterate>
        </logic:notEmpty>
      </td>
    </tr>

<%--

    <tr>
    <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="ntp.58" /></span></td>
    <td align="left" class="td_type20" width="85%" colspan="3"><html:text name="ntp200Form" property="ntp200ShohinName" size="50" maxlength="50" /></td>
    </tr>

    <tr>
    <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="ntp.6" /></span></td>
    <td align="left" class="td_type20" width="85%" colspan="3">

    <!-- 開始年コンボ -->
    <html:select name="ntp200Form" property="ntp200FrYear" styleId="selYear">
    <logic:notEmpty name="ntp200Form" property="ntp200YearList">
    <html:optionsCollection name="ntp200Form" property="ntp200YearList" label="value" label="label" />
    </logic:notEmpty>
    </html:select>

    <!-- 開始月コンボ -->
    <html:select name="ntp200Form" property="ntp200FrMonth" styleId="selMonth">
    <logic:notEmpty name="ntp200Form" property="ntp200MonthList">
    <html:optionsCollection name="ntp200Form" property="ntp200MonthList" label="value" label="label" />
    </logic:notEmpty>
    </html:select>
    <!-- 開始日コンボ -->
    <html:select name="ntp200Form" property="ntp200FrDay" styleId="selDay">
    <logic:notEmpty name="ntp200Form" property="ntp200DayList">
    <html:optionsCollection name="ntp200Form" property="ntp200DayList" label="value" label="label" />
    </logic:notEmpty>
    </html:select>
    <input type="button" value="Cal" onclick="wrtCalendar(this.form.selDay, this.form.selMonth, this.form.selYear)" class="calendar_btn">
    &nbsp;<span class="text_base7">～</span>&nbsp;
    <!-- 終了年コンボ -->
    <html:select name="ntp200Form" property="ntp200ToYear" styleId="seleYear">
    <logic:notEmpty name="ntp200Form" property="ntp200YearList">
    <html:optionsCollection name="ntp200Form" property="ntp200YearList" label="value" label="label" />
    </logic:notEmpty>
    </html:select>
    <!-- 終了月コンボ -->
    <html:select name="ntp200Form" property="ntp200ToMonth" styleId="seleMonth">
    <logic:notEmpty name="ntp200Form" property="ntp200MonthList">
    <html:optionsCollection name="ntp200Form" property="ntp200MonthList" label="value" label="label" />
    </logic:notEmpty>
    </html:select>
    <!-- 終了日コンボ -->
    <html:select name="ntp200Form" property="ntp200ToDay" styleId="seleDay">
    <logic:notEmpty name="ntp200Form" property="ntp200DayList">
    <html:optionsCollection name="ntp200Form" property="ntp200DayList" label="value" label="label" />
    </logic:notEmpty>
    </html:select>
    <input type="button" value="Cal" onclick="wrtCalendar(this.form.seleDay, this.form.seleMonth, this.form.seleYear)" class="calendar_btn">
    </td>
    </tr>

    <tr>
    <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="ntp.61" />／<gsmsg:write key="ntp.62" /></span></td>
    <td align="left" class="td_type20" width="35%">

    <!-- 業務コンボ -->
    <html:select name="ntp200Form" property="ntp200NgySid" styleClass="select01" onchange="return buttonPush('changeGyomu');" style="width: 150px;">
    <logic:notEmpty name="ntp200Form" property="ntp200GyomuList">
    <html:optionsCollection name="ntp200Form" property="ntp200GyomuList" label="value" label="label" />
    </logic:notEmpty>
    </html:select>
    &nbsp;
    <!-- プロセスコンボ -->
    <html:select name="ntp200Form" property="ntp200NgpSid" styleClass="select01" style="width: 150px;">
    <logic:notEmpty name="ntp200Form" property="ntp200ProcessList">
    <html:optionsCollection name="ntp200Form" property="ntp200ProcessList" label="value" label="label" />
    </logic:notEmpty>
    </html:select>

    </td>
    <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="ntp.63" /></span></td>
    <td align="left" class="td_type20" width="35%">
    <html:multibox name="ntp200Form" property="ntp200Mikomi" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.MIKOMI_DAI) %>" />&nbsp;<span class="text_base7">大</span>&nbsp;
    <html:multibox name="ntp200Form" property="ntp200Mikomi" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.MIKOMI_CHU) %>" />&nbsp;<span class="text_base7">中</span>&nbsp;
    <html:multibox name="ntp200Form" property="ntp200Mikomi" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.MIKOMI_SYO) %>" />&nbsp;<span class="text_base7">小</span>
    </td>
    </tr>
    <tr>
    <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="ntp.53" /></span></td>
    <td align="left" class="td_type20" width="35%">
    <html:text name="ntp200Form" property="ntp200NanKinMitumori" size="13" maxlength="12" style="text-align:right" />&nbsp;<span class="text_base7"><gsmsg:write key="project.103" /></span>
    <html:radio name="ntp200Form" property="ntp200NanKinMitumoriKbn" styleId="ntp200NanKinMitumoriKbn1" value="<%= String.valueOf(jp.groupsession.v2.ntp.ntp200.Ntp200Biz.PRICE_MORE) %>" /><span class="text_base7"><label for="ntp200NanKinMitumoriKbn1"><gsmsg:write key="ntp.66" /></label></span>&nbsp;
    <html:radio name="ntp200Form" property="ntp200NanKinMitumoriKbn" styleId="ntp200NanKinMitumoriKbn2" value="<%= String.valueOf(jp.groupsession.v2.ntp.ntp200.Ntp200Biz.PRICE_LESS) %>" /><span class="text_base7"><label for="ntp200NanKinMitumoriKbn2"><gsmsg:write key="ntp.67" /></label></span>&nbsp;
    </td>
    <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="ntp.54" /></span></td>
    <td align="left" class="td_type20" width="35%">
    <html:text name="ntp200Form" property="ntp200NanKinJutyu" size="13" maxlength="12" style="text-align:right" />&nbsp;<span class="text_base7"><gsmsg:write key="project.103" /></span>
    <html:radio name="ntp200Form" property="ntp200NanKinJutyuKbn" styleId="ntp200NanKinJutyuKbn1" value="<%= String.valueOf(jp.groupsession.v2.ntp.ntp200.Ntp200Biz.PRICE_MORE) %>" /><span class="text_base7"><label for="ntp200NanKinJutyuKbn1"><gsmsg:write key="ntp.66" /></label></span>&nbsp;
    <html:radio name="ntp200Form" property="ntp200NanKinJutyuKbn" styleId="ntp200NanKinJutyuKbn2" value="<%= String.valueOf(jp.groupsession.v2.ntp.ntp200.Ntp200Biz.PRICE_LESS) %>" /><span class="text_base7"><label for="ntp200NanKinJutyuKbn2"><gsmsg:write key="ntp.67" /></label></span>&nbsp;
    </td>
    </tr>
    <tr>
    <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="ntp.64" /></span></td>
    <td align="left" class="td_type20" width="35%">
    <html:multibox name="ntp200Form" property="ntp200Syodan" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.SYODAN_CHU) %>" />&nbsp;<span class="text_base7"><gsmsg:write key="ntp.68" /></span>&nbsp;
    <html:multibox name="ntp200Form" property="ntp200Syodan" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.SYODAN_JYUCHU) %>" />&nbsp;<span class="text_base7"><gsmsg:write key="ntp.69" /></span>&nbsp;
    <html:multibox name="ntp200Form" property="ntp200Syodan" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.SYODAN_SICHU) %>" />&nbsp;<span class="text_base7"><gsmsg:write key="ntp.7" /></span>
    </td>
    <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="ntp.65" /></span></td>
    <td align="left" class="td_type20" width="35%">
    <!-- 顧客源泉コンボ -->
    <html:select name="ntp200Form" property="ntp200NcnSid" styleClass="select01" style="width: 220px;">
    <logic:notEmpty name="ntp200Form" property="ntp200ContactList">
    <html:optionsCollection name="ntp200Form" property="ntp200ContactList" label="value" label="label" />
    </logic:notEmpty>
    </html:select>
    </td>
    </tr>

    <tr>
    <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="cmn.sortkey" />１</span></td>
    <td align="left" class="td_type20" width="35%" nowrap>
    <!-- ソート１コンボ -->
    <html:select name="ntp200Form" property="ntp200SortKey1" styleClass="select01" style="width: 220px;">
    <logic:notEmpty name="ntp200Form" property="ntp200SortList">
    <html:optionsCollection name="ntp200Form" property="ntp200SortList" label="value" label="label" />
    </logic:notEmpty>
    </html:select>
    <html:radio name="ntp200Form" property="ntp200OrderKey1" styleId="ntp200OrderKey11" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.ORDER_KEY_ASC) %>" /><span class="text_base7"><label for="ntp200OrderKey11"><gsmsg:write key="cmn.order.asc" /></label></span>&nbsp;
    <html:radio name="ntp200Form" property="ntp200OrderKey1" styleId="ntp200OrderKey12" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.ORDER_KEY_DESC) %>" /><span class="text_base7"><label for="ntp200OrderKey12"><gsmsg:write key="cmn.order.desc" /></label></span>&nbsp;
    </td>

    <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="cmn.sortkey" />２</span></td>
    <td align="left" class="td_type20" width="35%" nowrap>
    <!-- ソート２コンボ -->
    <html:select name="ntp200Form" property="ntp200SortKey2" styleClass="select01" style="width: 220px;">
    <logic:notEmpty name="ntp200Form" property="ntp200SortList">
    <html:optionsCollection name="ntp200Form" property="ntp200SortList" label="value" label="label" />
    </logic:notEmpty>
    </html:select>
    <html:radio name="ntp200Form" property="ntp200OrderKey2" styleId="ntp200OrderKey21" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.ORDER_KEY_ASC) %>" /><span class="text_base7"><label for="ntp200OrderKey21"><gsmsg:write key="cmn.order.asc" /></label></span>&nbsp;
    <html:radio name="ntp200Form" property="ntp200OrderKey2" styleId="ntp200OrderKey22" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.ORDER_KEY_DESC) %>" /><span class="text_base7"><label for="ntp200OrderKey22"><gsmsg:write key="cmn.order.desc" /></label></span>&nbsp;
    </td>
    </tr>

--%>

    </table>

  </td>
  </tr>
  <tr>
  <td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td>
  </tr>
  <tr>
  <td align="center">
      <table cellpadding="0" cellspacing="0" width="100%">
      <tr>
      <td align="center" width="100%">
      <input type="button" name="btn_search" class="btn_search_n1" value="<gsmsg:write key="cmn.search" />" onClick="return buttonPush('search');">
      </td>

      </tr>
      </table>
  </td>
  </tr>
  <tr>
  <td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td>
  </tr>

  <logic:notEmpty name="ntp200Form" property="ntp200AnkenList">
  <tr>
  <td align="right">
    <bean:size id="count1" name="ntp200Form" property="ntp200PageCmbList" scope="request" />
    <logic:greaterThan name="count1" value="1">
    <table width="100%" cellpadding="5" cellspacing="0">
      <td align="right">
        <img src="../common/images/arrow_w2.gif" alt="<gsmsg:write key="cmn.previous.page" />" width="13" height="13" onClick="buttonPush('prevPage');">
        <html:select property="ntp200PageTop" onchange="changePage(0);">
          <html:optionsCollection name="ntp200Form" property="ntp200PageCmbList" value="value" label="label" />
        </html:select>
        <img src="../common/images/arrow_w.gif" alt="<gsmsg:write key="project.48" />" width="13" height="13" onClick="buttonPush('nextPage');"></td>
      </tr>
    </table>
    </logic:greaterThan>
  </td>
  </tr>
  </logic:notEmpty>


  <% String anken_sort_class = "anken_sort_sel_desc"; %>
  <% String anken_anken_class = ""; %>
  <% String anken_company_class = ""; %>
  <% String anken_koushin_class = ""; %>
  <% String anken_syodan_class = ""; %>

  <logic:equal name="ntp200Form" property="ntp200OrderKey1" value="0">
    <% anken_sort_class = "anken_sort_sel_asc"; %>
  </logic:equal>

  <logic:equal name="ntp200Form" property="ntp200SortKey1" value="0">
    <% anken_anken_class = anken_sort_class; %>
  </logic:equal>

  <logic:equal name="ntp200Form" property="ntp200SortKey1" value="1">
    <% anken_company_class = anken_sort_class; %>
  </logic:equal>

  <logic:equal name="ntp200Form" property="ntp200SortKey1" value="2">
    <% anken_koushin_class = anken_sort_class; %>
  </logic:equal>

  <logic:equal name="ntp200Form" property="ntp200SortKey1" value="3">
    <% anken_syodan_class = anken_sort_class; %>
  </logic:equal>

  <tr>
  <td align="left">
    <table class="tl0" cellpadding="0" cellspacing="0" width="100%">
    <tr>

    <logic:notEmpty name="ntp200Form" property="ntp200ReturnPage">
    <th align="center" class="table_bg_7D91BD_search" width="5%" nowrap><span class="text_ntp_tlw2">&nbsp;</span></th>
    </logic:notEmpty>

    <th align="center" class="table_bg_7D91BD_search" width="35%" nowrap><span class="text_ntp_tlw2"><span class="text_ntp_tlw2 anken_sort_link <%= anken_anken_class %>" id="0"><gsmsg:write key="ntp.11" /></span></span></th>

    <th align="center" class="table_bg_7D91BD_search" width="25%" nowrap>
      <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tr>
        <td align="center" valign="middle" width="50%" class="text_ntp_tlw2" nowrap><span class="text_ntp_tlw2 anken_sort_link <%= anken_company_class %>" id="1"><gsmsg:write key="ntp.15" />/拠点</span></td>
        </tr>
      </table>
    </th>
<%--
    <th align="center" class="table_bg_7D91BD" width="15%" nowrap>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td colspan="2" align="left" valign="middle" width="100%" ><span id="lt" class="text_tlw">商　品</span></td>
      </tr>
      <tr>
      <td align="left" valign="middle" width="50%" nowrap><span id="lt" class="text_tlw"><gsmsg:write key="ntp.53" /></span></td>
      <td align="left" valign="middle" width="50%" nowrap><span id="lt" class="text_tlw"><gsmsg:write key="ntp.54" /></span></td>
      </tr>
    </table>
    </th>


    <th align="center" class="table_bg_7D91BD" width="30%" nowrap>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td align="left" valign="middle" width="50%" nowrap><span id="lt" class="text_tlw"><gsmsg:write key="ntp.61" /></span></td>
      <td align="left" valign="middle" width="50%" nowrap><span id="lt" class="text_tlw">現行プロセス</span></td>
      </tr>
      <tr>
      <td align="left" valign="middle" width="50%" nowrap><span id="lt" class="text_tlw"><gsmsg:write key="ntp.63" /></span></td>
      <td align="left" valign="middle" width="50%" nowrap><span id="lt" class="text_tlw"><gsmsg:write key="ntp.65" /></span></td>
      </tr>
    </table>
    </th>
--%>

    <th align="center" class="table_bg_7D91BD_search" width="20%" nowrap>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td align="center" valign="middle" width="50%" class="text_ntp_tlw2" nowrap><span class="text_ntp_tlw2 anken_sort_link <%= anken_koushin_class %>" id="2"><gsmsg:write key="ntp.155" /></span></td>
      </tr>
    </table>
    </th>

    <th align="center" class="table_bg_7D91BD_search" width="0%" nowrap>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td align="center" valign="middle" width="50%" class="text_ntp_tlw2" nowrap><span class="text_ntp_tlw2 anken_sort_link <%= anken_syodan_class %>" id="3"><gsmsg:write key="ntp.64" /></span></td>
      </tr>
    </table>
    </th>

    <th align="center" class="table_bg_7D91BD_search" width="0%" nowrap>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td align="center" valign="middle" width="50%" class="text_ntp_tlw2" nowrap><span class="text_ntp_tlw2" style="font-size:14px;"><gsmsg:write key="ntp.58" /></span></td>
      </tr>
    </table>
    </th>

    <%--
    <logic:empty name="ntp200Form" property="ntp200ReturnPage">
    <th align="center" class="table_bg_7D91BD" width="0%" nowrap><span class="text_tlw"><gsmsg:write key="cmn.edit" /></span></th>
    </logic:empty>
    --%>
    </tr>

    <logic:notEmpty name="ntp200Form" property="ntp200AnkenList">
    <bean:define id="tdColor" value="" />

    <logic:iterate id="ankenMdl" name="ntp200Form" property="ntp200AnkenList" indexId="idx">

    <bean:define id="mod" value="0" />
    <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
      <bean:define id="tblColor" value="anken_select_area" />
    </logic:equal>
    <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
      <bean:define id="tblColor" value="anken_select_area" />
    </logic:notEqual>

    <tr align="center" class="cursor_pointer" onclick="return setParent(<bean:write name="ankenMdl" property="nanSid" />);">

    <td align="center" id="<bean:write name="ankenMdl" property="nanSid" />" class="td_anken_<bean:write name="ankenMdl" property="nanSid" /> <bean:write name="tblColor"/> anken_link_area<%= String.valueOf(idx.intValue() % 2) %>">
    <table cellpadding="0" cellspacing="0" width="100%">
      <tr>
      <td align="left" valign="middle">
      <input type="hidden" id="ankenCode_<bean:write name="ankenMdl" property="nanSid" />" value="<bean:write name="ankenMdl" property="nanCode" />">
      <span class="text_base2"><bean:write name="ankenMdl" property="nanCode" />&nbsp;</span>
      </td>
      </tr>
      <tr>
      <td align="left" valign="middle">
      <input type="hidden" id="ankenName_<bean:write name="ankenMdl" property="nanSid" />" value="<bean:write name="ankenMdl" property="nanName" />">
      <input type="hidden" id="ankenMikomido_<bean:write name="ankenMdl" property="nanSid" />" value="<bean:write name="ankenMdl" property="nanMikomi" />">
      <span class="text_link cursor_pointer"><bean:write name="ankenMdl" property="nanName" />&nbsp;</span>
      </td>
      </tr>
    </table>
    </td>

    <td align="center" id="<bean:write name="ankenMdl" property="nanSid" />" class="td_anken_<bean:write name="ankenMdl" property="nanSid" /> <bean:write name="tblColor"/> anken_link_area<%= String.valueOf(idx.intValue() % 2) %>">
    <table cellpadding="0" cellspacing="0" width="100%">
      <tr>
      <td align="left" valign="middle">
      <input type="hidden" id="ankenCompanySid_<bean:write name="ankenMdl" property="nanSid" />" value="<bean:write name="ankenMdl" property="acoSid" />">
      <input type="hidden" id="ankenCompanyCode_<bean:write name="ankenMdl" property="nanSid" />" value="<bean:write name="ankenMdl" property="ntp200CompanyCode" />">
      <input type="hidden" id="ankenCompanyName_<bean:write name="ankenMdl" property="nanSid" />" value="<bean:write name="ankenMdl" property="ntp200CompanyName" />">
      <span class="text_base2"><bean:write name="ankenMdl" property="ntp200CompanyName" />&nbsp;</span>
      </td>
      </tr>
      <tr>
      <td align="left" valign="middle">
      <input type="hidden" id="ankenBaseSid_<bean:write name="ankenMdl" property="nanSid" />" value="<bean:write name="ankenMdl" property="abaSid" />">
      <input type="hidden" id="ankenBaseName_<bean:write name="ankenMdl" property="nanSid" />" value="<bean:write name="ankenMdl" property="ntp200BaseName" />">
      <span class="text_base2"><bean:write name="ankenMdl" property="ntp200BaseName" />&nbsp;</span></td>
      </tr>
    </table>
    </td>
<%--
    <td align="center" class="td_type1_ntp">
    <table cellpadding="0" cellspacing="0" width="100%">
      <tr>
      <td colspan="2" align="left" valign="middle" width="100%" >
      <span class="text_base2">
        <logic:notEmpty  name="ankenMdl" property="ntp200ShohinList">
          <logic:iterate id="shMdl" name="ankenMdl" property="ntp200ShohinList">
            <br><span class="text_shohin"><bean:write name="shMdl" property="nhnName" /></span>
          </logic:iterate>
        </logic:notEmpty>
      </span></td>
      </tr>
      <tr>
      <td align="left" valign="middle" width="50%"><span class="text_base2">￥<bean:write name="ankenMdl" property="ntp200KinMitumori" />&nbsp;</span></td>
      <td align="left" valign="middle" width="50%"><span class="text_base2">￥<bean:write name="ankenMdl" property="ntp200KinJutyu" />&nbsp;</span></td>
      </tr>
    </table>
    </td>

    <td align="center" class="td_type1_ntp">
    <table cellpadding="0" cellspacing="0" width="100%">
      <tr>
      <td align="left" valign="middle" width="50%"><span class="text_base2"><bean:write name="ankenMdl" property="ntp200GyomuName" />&nbsp;</span></td>
      <td align="left" valign="middle" width="50%"><span class="text_base2"><bean:write name="ankenMdl" property="ntp200ProcessName" />&nbsp;</span></td>
      </tr>
      <tr>
      <td align="left" valign="middle">
      <span class="text_base2">
      <logic:equal name="ankenMdl" property="nanMikomi" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.MIKOMI_DAI) %>">大</logic:equal>
      <logic:equal name="ankenMdl" property="nanMikomi" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.MIKOMI_CHU) %>">中</logic:equal>
      <logic:equal name="ankenMdl" property="nanMikomi" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.MIKOMI_SYO) %>">小</logic:equal>
      </span>
      </td>
      <td align="left" valign="middle"><span class="text_base2"><bean:write name="ankenMdl" property="ntp200ContactName" />&nbsp;</span></td>
      </tr>
    </table>
    </td>
--%>
    <td align="center" id="<bean:write name="ankenMdl" property="nanSid" />" class="td_anken_<bean:write name="ankenMdl" property="nanSid" /> <bean:write name="tblColor"/> anken_link_area<%= String.valueOf(idx.intValue() % 2) %>">
    <table cellpadding="0" cellspacing="0" width="100%">
      <tr>
      <td align="center" valign="middle"><span class="text_base2"><bean:write name="ankenMdl" property="ntp200Date" />&nbsp;</span></td>
      </tr>
    </table>
    </td>

    <td align="center" id="<bean:write name="ankenMdl" property="nanSid" />" class="td_anken_<bean:write name="ankenMdl" property="nanSid" /> <bean:write name="tblColor"/> anken_link_area<%= String.valueOf(idx.intValue() % 2) %>">
    <table cellpadding="0" cellspacing="0" width="100%">
      <td align="center" valign="middle">
      <span class="text_base2">
      <logic:equal name="ankenMdl" property="nanSyodan" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.SYODAN_CHU) %>"><gsmsg:write key="ntp.68" /></logic:equal>
      <logic:equal name="ankenMdl" property="nanSyodan" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.SYODAN_JYUCHU) %>"><gsmsg:write key="ntp.69" /></logic:equal>
      <logic:equal name="ankenMdl" property="nanSyodan" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.SYODAN_SICHU) %>"><gsmsg:write key="ntp.7" /></logic:equal>
      </span>
      </td>
      </tr>
    </table>
    </td>

    <td align="center" nowrap id="<bean:write name="ankenMdl" property="nanSid" />" class="td_anken_<bean:write name="ankenMdl" property="nanSid" /> <bean:write name="tblColor"/> anken_link_area<%= String.valueOf(idx.intValue() % 2) %>">
      <table cellpadding="0" cellspacing="0" width="100%">
        <logic:notEmpty  name="ankenMdl" property="ntp200ShohinList">
          <logic:iterate id="shMdl" name="ankenMdl" property="ntp200ShohinList">
            <tr>
              <td align="center" valign="middle" nowrap>
                <span class="text_base2"><bean:write name="shMdl" property="nhnName" /></span>
              </td>
            </tr>
          </logic:iterate>
        </logic:notEmpty>
      </table>
    </td>

<%--
    <logic:empty name="ntp200Form" property="ntp200ReturnPage">
    <td align="center" class="td_type1_ntp">
    <input type="button" value="選択" class="btn_base1s" onclick="return setParent(<bean:write name="ankenMdl" property="nanSid" />);">
    </td>
    </logic:empty>
--%>
    </tr>

    </logic:iterate>
    </logic:notEmpty>

    </table>
  </td>
  </tr>

  <logic:notEmpty name="ntp200Form" property="ntp200AnkenList">
  <tr>
  <td align="right">
    <bean:size id="count1" name="ntp200Form" property="ntp200PageCmbList" scope="request" />
    <logic:greaterThan name="count1" value="1">
    <table width="100%" cellpadding="5" cellspacing="0">
      <td align="right">
        <img src="../common/images/arrow_w2.gif" alt="<gsmsg:write key="cmn.previous.page" />" width="13" height="13" onClick="buttonPush('prevPage');">
        <html:select property="ntp200PageBottom" onchange="changePage(1);">
          <html:optionsCollection name="ntp200Form" property="ntp200PageCmbList" value="value" label="label" />
        </html:select>
        <img src="../common/images/arrow_w.gif" alt="<gsmsg:write key="project.48" />" width="13" height="13" onClick="buttonPush('nextPage');"></td>
      </tr>
    </table>
    </logic:greaterThan>
  </td>
  </tr>
  </logic:notEmpty>


  </table>

  <img src="../schedule/images/spacer.gif" width="1px" height="10px" border="0">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td align="center">
  <input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_close_n1" onClick="return window.close();">
  </td>
  </tr>
  </table>

</td>
</tr>
</table>

</html:form>

</body>
</html:html>