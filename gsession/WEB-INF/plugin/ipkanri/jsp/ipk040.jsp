<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../ipkanri/js/ipkanri.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/check.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../ipkanri/css/ip.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<title>[GroupSession] <gsmsg:write key="ipk.15" /></title>
</head>

<body class="body_03" onload="showOrHide();">
<html:form action="/ipkanri/ipk040">
<html:hidden property="netSid" />
<html:hidden property="iadSid" />
<input type="hidden" name="cmd" value="search" >
<html:hidden property="iadPageNum" />
<html:hidden property="iadCount" />
<html:hidden property="iadCountUse" />
<html:hidden property="iadCountNotUse" />
<html:hidden property="maxPageNum" />
<html:hidden property="sortKey" />
<html:hidden property="orderKey" />
<html:hidden property="binSid" />
<html:hidden property="returnCmd" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<table align="center" cellpadding="5" cellspacing="0" border="0" width="85%">
<tr>
<td width="100%" align="center">
  <table class="table_padding" cellpadding="0">
  <tr>
  <td width="0%"><img src="../ipkanri/images/header_ipkanri_icon_01.gif" border="0" alt="<gsmsg:write key="cmn.ipkanri" />"></td>
  <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.ipkanri" /></span></td>
  <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="ipk.15" /> ]</td>
  <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.ipkanri" />"></td>
  </tr>
  </table>
</td>
</tr>
<tr>
<td>
  <table width="98%" align="center">
  <tr>
  <td align="right">
  <logic:equal name="ipk040Form" property="checkBoxDspFlg" value="true">
  <input type="button" name="delete" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush2('selectDelete');">
  </logic:equal>
  <logic:equal name="ipk040Form" property="iadNetAdmFlg" value="true">
  <input type="button" name="add" value="<gsmsg:write key="cmn.add" />" class="btn_add_n1" onClick="ipk040ButtonPush('ipAdd', '' , '0');">
  </logic:equal>
  <logic:equal name="ipk040Form" property="iadNetAdmFlg" value="true">
  <input type="button" name="export" value="<gsmsg:write key="cmn.import" />" class="btn_csv_n1" onClick="buttonPush2('import');">
  </logic:equal>
  <logic:notEqual name="ipk040Form" property="maxCount" value="0">
  <input type="button" name="export" value="<gsmsg:write key="cmn.export" />" class="btn_csv_n2" onClick="buttonPush2('export');">
  </logic:notEqual>
  <input type="button" name="back" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('return', '');" class="btn_back_n1">
  </td>
  </tr>
  </table>
</td>
</tr>
<tr>
<td>
  <div id="hide">
  <table class="table_ip" width="100%">
  <tr>
  <td class="table_bg_7D91BD" width="20%" nowrap>
    <table cellpadding="0" cellspacing="0">
    <tr>
    <td><img src="../ipkanri/images/ipkanri_icon.gif" width="30" border="0" class="header_icon" ></td>
    <td nowrap>&nbsp;<span class="font_white"><gsmsg:write key="ipk.4" /></span></td>
    </tr>
    </table>
  </td>
  <td align="left" width="80%" class="td_type20_1">
    <table width="100%">
      <tr>
      <td nowrap>
      <span class="font_black2">
      <bean:write name="ipk040Form" property="netName" /></span></td>
      <td align="right" width="100%" class="font_black3" nowrap><gsmsg:write key="ipk.5" />：<bean:write name="ipk040Form" property="iadCount" />
     （<gsmsg:write key="cmn.in.use" />: <bean:write name="ipk040Form" property="iadCountUse" />
      <gsmsg:write key="cmn.unused" />: <bean:write name="ipk040Form" property="iadCountNotUse" />) &nbsp;
      </td>
      <td align="right" width="0%" nowrap>
      <input type="button" value="<gsmsg:write key="cmn.show" />" class="btn_base1s" onClick="showText()">&nbsp;
      </td>
      </tr>
    </table>
  </td>
  </tr>
  </table>
  </div>

  <div id="show">
  <table align="center" class="table_padding" cellpadding="0">
  <tr align="center" border="0">
  <logic:equal name="ipk040Form" property="tempExist" value="true">
  <logic:equal name="ipk040Form" property="iadNetAdmFlg" value="true">
  <td align="left" class="td_border" colspan="4">
  </logic:equal>
  <logic:equal name="ipk040Form" property="iadNetAdmFlg" value="false">
  <logic:notEmpty name="ipk040Form" property="koukaiBinFileInfList">
  <td align="left" class="td_border" colspan="4">
  </logic:notEmpty>
  <logic:empty name="ipk040Form" property="koukaiBinFileInfList">
  <td align="left" class="td_border" colspan="3">
  </logic:empty>
  </logic:equal>
  </logic:equal>
  <logic:equal name="ipk040Form" property="tempExist" value="false">
  <td align="left" class="td_border" colspan="3">
  </logic:equal>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr class="table_bg_7D91BD">
    <td align="left" nowrap border="0" width="0%">
    <img src="../ipkanri/images/ipkanri_icon.gif" width="30" border="0" class="header_icon" >
    </td>
    <td align="left" width="0%" nowrap>
    <span class="font_white">&nbsp;<bean:write name="ipk040Form" property="netName" /></span>
    </td>
    <td align="right" width="90%" nowrap>
    <span class="font_white2">　<gsmsg:write key="ipk.5" />：<bean:write name="ipk040Form" property="iadCount" />
（<gsmsg:write key="cmn.in.use" />: <bean:write name="ipk040Form" property="iadCountUse" />
 <gsmsg:write key="cmn.unused" />: <bean:write name="ipk040Form" property="iadCountNotUse" />) &nbsp; </span>
    </td>
    <td align="right" width="10%">
    <input type="button" value="<gsmsg:write key="cmn.hide" />" class="btn_base1s" onClick="hideText()">&nbsp;&nbsp;
    </td>
    </tr>
    </table>
  </td>
  </tr>
  <logic:equal name="ipk040Form" property="tempExist" value="true">
  <logic:equal name="ipk040Form" property="iadNetAdmFlg" value="true">
  <tr>
  <td class="td_type3" width="0%" nowrap><gsmsg:write key="ipk.2" /></td>
  <td class="td_type3" width="0%" nowrap><gsmsg:write key="ipk.3" /></td>
  <td class="td_type3" width="100%" nowrap colspan="2"><gsmsg:write key="cmn.comment" /></td>
  </tr>

  <logic:notEmpty name="ipk040Form" property="koukaiBinFileInfList">
  <logic:notEmpty name="ipk040Form" property="hikoukaiBinFileInfList">
  <tr>
  <td class="td_type20_1" align="center" rowspan="3">
  <span class="font_000000"><bean:write name="ipk040Form" property="netNetad" /></span>
  </td>
  <td class="td_type20_1" align="center" rowspan="3">
  <span class="font_000000"><bean:write name="ipk040Form" property="netSabnet" /></span>
  </td>
  <td align="left" class="td_type20_1" valign="top" colspan="2">
  <span class="font_000000"><bean:write name="ipk040Form" property="netMsg" filter="false" /></span>
  </td>
  </tr>
  <tr>
  <td class="td_type3" width="20%" nowrap><gsmsg:write key="cmn.attach.file" />(<gsmsg:write key="cmn.public" />)</td>
  <td width="80%" class="td_type20_1">
  <logic:iterate id="koukaiFileMdl" name="ipk040Form" property="koukaiBinFileInfList">
  <img src="../common/images/file_icon.gif" alt="<gsmsg:write key="cmn.file" />">
  <a href="javascript:fileLinkClick(<bean:write name="koukaiFileMdl" property="binSid" />);">
  <span class="text_link"><bean:write name="koukaiFileMdl" property="binFileName" /><bean:write name="koukaiFileMdl" property="binFileSizeDsp" /></a><br>
  </logic:iterate>
  </td>
  </tr>
  <tr>
  <td class="td_type3" width="20%" nowrap><gsmsg:write key="cmn.attach.file" />(<gsmsg:write key="cmn.private" />)</td>
  <td width="80%" class="td_type20_1">
  <logic:iterate id="hikoukaiFileMdl" name="ipk040Form" property="hikoukaiBinFileInfList">
  <img src="../common/images/file_icon.gif" alt="<gsmsg:write key="cmn.file" />">
  <a href="javascript:fileLinkClick(<bean:write name="hikoukaiFileMdl" property="binSid" />);">
  <span class="text_link"><bean:write name="hikoukaiFileMdl" property="binFileName" /><bean:write name="hikoukaiFileMdl" property="binFileSizeDsp" /></span></a></br>
  </logic:iterate>
  </td>
  </tr>
  </logic:notEmpty>
  </logic:notEmpty>


  <logic:empty name="ipk040Form" property="koukaiBinFileInfList">
  <tr>
  <td class="td_type20_1" align="center" rowspan="2">
  <span class="font_000000"><bean:write name="ipk040Form" property="netNetad" /></span>
  </td>
  <td class="td_type20_1" align="center" rowspan="2">
  <span class="font_000000"><bean:write name="ipk040Form" property="netSabnet" /></span>
  </td>
  <td align="left" class="td_type20_1" valign="top" colspan="2">
  <span class="font_000000"><bean:write name="ipk040Form" property="netMsg" filter="false" /></span>
  </td>
  </tr>
  <tr>
  <td class="td_type3" width="20%" nowrap><gsmsg:write key="cmn.attach.file" />(<gsmsg:write key="cmn.private" />)</td>
  <td width="80%" class="td_type20_1">
  <logic:iterate id="hikoukaiFileMdl" name="ipk040Form" property="hikoukaiBinFileInfList">
  <img src="../common/images/file_icon.gif" alt="<gsmsg:write key="cmn.file" />">
  <a href="javascript:fileLinkClick(<bean:write name="hikoukaiFileMdl" property="binSid" />);">
  <span class="text_link"><bean:write name="hikoukaiFileMdl" property="binFileName" /><bean:write name="hikoukaiFileMdl" property="binFileSizeDsp" /></span></a></br>
  </logic:iterate>
  </td>
  </tr>
  </logic:empty>

  <logic:empty name="ipk040Form" property="hikoukaiBinFileInfList">
  <tr>
  <td class="td_type20_1" align="center" rowspan="2">
  <span class="font_000000"><bean:write name="ipk040Form" property="netNetad" /></span>
  </td>
  <td class="td_type20_1" align="center" rowspan="2">
  <span class="font_000000"><bean:write name="ipk040Form" property="netSabnet" /></span>
  </td>
  <td align="left" class="td_type20_1" valign="top" colspan="2">
  <span class="font_000000"><bean:write name="ipk040Form" property="netMsg" filter="false" /></span>
  </td>
  </tr>
  <tr>
  <td class="td_type3" width="20%" nowrap><gsmsg:write key="cmn.attach.file" />(<gsmsg:write key="cmn.public" />)</td>
  <td width="80%" class="td_type20_1">
  <logic:iterate id="koukaiFileMdl" name="ipk040Form" property="koukaiBinFileInfList">
  <img src="../common/images/file_icon.gif" alt="<gsmsg:write key="cmn.file" />">
  <a href="javascript:fileLinkClick(<bean:write name="koukaiFileMdl" property="binSid" />);">
  <span class="text_link"><bean:write name="koukaiFileMdl" property="binFileName" /><bean:write name="koukaiFileMdl" property="binFileSizeDsp" /></span></a></br>
  </logic:iterate>
  </td>
  </tr>
  </logic:empty>
  </logic:equal>

  <logic:equal name="ipk040Form" property="iadNetAdmFlg" value="false">
  <logic:notEmpty name="ipk040Form" property="koukaiBinFileInfList">
  <tr>
  <td class="td_type3" width="0%" nowrap><gsmsg:write key="ipk.2" /></td>
  <td class="td_type3" width="0%" nowrap><gsmsg:write key="ipk.3" /></td>
  <td class="td_type3" width="100%" nowrap colspan="2"><gsmsg:write key="cmn.comment" /></td>
  </tr>
  <tr>
  <td class="td_type20_1" align="center" rowspan="2">
  <span class="font_000000"><bean:write name="ipk040Form" property="netNetad" /></span>
  </td>
  <td class="td_type20_1" align="center" rowspan="2">
  <span class="font_000000"><bean:write name="ipk040Form" property="netSabnet" /></span>
  </td>
  <td align="left" class="td_type20_1" valign="top" colspan="2">
  <span class="font_000000"><bean:write name="ipk040Form" property="netMsg" filter="false" /></span>
  </td>
  </tr>
  <tr>
  <td class="td_type3"><gsmsg:write key="cmn.attach.file" />(<gsmsg:write key="cmn.public" />)</td>
  <td class="td_type20_1" align="center">
    <table cellpadding="0" cellpadding="0" border="0">
    <logic:iterate id="koukaiFileMdl" name="ipk040Form" property="koukaiBinFileInfList">
    <tr>
    <td><img src="../common/images/file_icon.gif" alt="<gsmsg:write key="cmn.file" />"></td>
    <td class="menu_bun"><a href="javascript:fileLinkClick(<bean:write name="koukaiFileMdl" property="binSid" />);">
    <span class="text_link"><bean:write name="koukaiFileMdl" property="binFileName" /><bean:write name="koukaiFileMdl" property="binFileSizeDsp" /></span></a></td>
    </tr>
    </logic:iterate>
    </table>
  </td>
  </tr>
  </logic:notEmpty>
  <logic:empty name="ipk040Form" property="koukaiBinFileInfList">
  <tr>
  <td class="td_type3" width="30%" nowrap><gsmsg:write key="ipk.2" /></td>
  <td class="td_type3 width="30%" nowrap><gsmsg:write key="ipk.3" /></td>
  <td class="td_type3" width="40%" nowrap><gsmsg:write key="cmn.comment" /></td>
  </tr>
  <tr>
  <td class="td_type20_1" align="center">
  <span class="font_000000"><bean:write name="ipk040Form" property="netNetad" /></span>
  </td>
  <td class="td_type20_1" align="center">
  <span class="font_000000"><bean:write name="ipk040Form" property="netSabnet" /></span>
  </td>
  <td align="left" class="td_type20_1" valign="top">
  <span class="font_000000"><bean:write name="ipk040Form" property="netMsg" filter="false" /></span>
  </td>
  </tr>
  </logic:empty>
  </logic:equal>
  </logic:equal>

  <logic:equal name="ipk040Form" property="tempExist" value="false">
  <tr>
  <td class="td_type3" width="30%" nowrap><gsmsg:write key="ipk.2" /></td>
  <td class="td_type3 width="30%" nowrap"><gsmsg:write key="ipk.3" /></td>
  <td class="td_type3" width="40%" nowrap><gsmsg:write key="cmn.comment" /></td>
  </tr>

  <tr>
  <td class="td_type20_1" align="center">
  <span class="font_000000"><bean:write name="ipk040Form" property="netNetad" /></span>
  </td>
  <td class="td_type20_1" align="center">
  <span class="font_000000"><bean:write name="ipk040Form" property="netSabnet" /></span>
  </td>
  <td align="left" class="td_type20_1" valign="top">
  <span class="font_000000"><bean:write name="ipk040Form" property="netMsg" filter="false" /></span>
  </td>
  </tr>
  </logic:equal>
  </table>
  </div>
</td>
</tr>
<tr>
<td>
  <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
</td>
</tr>

<tr>
<td>
  <table width="98%" border="0" align="center" cellpadding="0">
  <tr>
  <td width="10%" align="left" valign="bottom" colspan="2" nowrap><span class="font_black2"><gsmsg:write key="cmn.number.display" /> </span>
  <html:select name="ipk040Form" property="iadLimit" onchange="pageSelect();">
  <html:option value="10"><gsmsg:write key="ipk.ipk040.2" arg0="１０" /></html:option>
  <html:option value="20"><gsmsg:write key="ipk.ipk040.2" arg0="２０" /></html:option>
  <html:option value="30"><gsmsg:write key="ipk.ipk040.2" arg0="３０" /></html:option>
  <html:option value="50"><gsmsg:write key="ipk.ipk040.2" arg0="５０" /></html:option>
  <html:option value="80"><gsmsg:write key="ipk.ipk040.2" arg0="８０" /></html:option>
  <html:option value="100"><gsmsg:write key="ipk.ipk040.2" arg0="１００" /></html:option>
  <html:option value="0"><gsmsg:write key="cmn.showing.all" /></html:option>
  </html:select>
  </td>
  <td align="left" nowrap><span class="font_black2">　<gsmsg:write key="ipk.ipk040.1" /></span>
  <html:radio property="usedKbn" value="2" onclick="pageSelect();" styleId="sort_all" /><label for="sort_all" class="font_black4"><gsmsg:write key="cmn.all" /></label>
  <html:radio property="usedKbn" value="1" onclick="pageSelect();" styleId="sort_use" /><label for="sort_use" class="font_black4"><gsmsg:write key="cmn.in.use" /></label>
  <html:radio property="usedKbn" value="0" onclick="pageSelect();" styleId="sort_notuse" /><label for="sort_notuse" class="font_black4"><gsmsg:write key="cmn.unused" /></label>
  </td>
  <td align="right" nowrap>
  <img src="../common/images/search.gif" alt="<gsmsg:write key="cmn.search" />" class="img_bottom">
  <html:text property="ipk070KeyWord" style="width:183px;" maxlength="50" />
  <input type="submit" name="btn_search" class="btn_base1s" value="<gsmsg:write key="cmn.search" />" onClick="buttonPush2('search');">
  </td>
  </tr>
  </table>
</td>
</tr>

<tr>
<td>
<!--- IPList --->
  <table cellspacing="0" cellpadding="0" align="center" class="table_kotei2">
  <tr>
  <logic:equal name="ipk040Form" property="iadNetAdmFlg" value="true">
  <td colspan="9" width="100%" align="right" valign="top" nowrap>
  </logic:equal>
  <logic:equal name="ipk040Form" property="iadNetAdmFlg" value="false">
  <logic:greaterThan name="ipk040Form" property="dspFlg" value="0">
  <td colspan="9" width="100%" align="right" valign="top" nowrap>
  </logic:greaterThan>
  <logic:equal name="ipk040Form" property="dspFlg" value="0">
  <td colspan="8" width="100%" align="right" valign="top" nowrap>
  </logic:equal>
  </logic:equal>
  <logic:greaterThan name="ipk040Form" property="maxPageNum" value="1">
  <img alt="<gsmsg:write key="cmn.previous.page" />" height="20" class="img_bottom" src="../ipkanri/images/arrow2_l.gif" width="20" border="0" onClick="buttonPush2('arrorw_left');">
  <logic:notEmpty name="ipk040Form" property="iadPageLabel">
  <html:select property="iadPage1" onchange="changePage1();" styleClass="text_i">
  <html:optionsCollection name="ipk040Form" property="iadPageLabel" value="value" label="label" />
  </html:select>
  </logic:notEmpty>
  <img src="../ipkanri/images/arrow2_r.gif" class="img_bottom" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" width="20" height="20" border="0" onClick="buttonPush2('arrorw_right');">
  </logic:greaterThan>
  </td>
  </tr>

  <tr class="tr_bg_7D91BD">
  <logic:equal name="ipk040Form" property="checkBoxDspFlg" value="true">
  <td align="center" class="td_typeip4" width="1%" rowspan="2">
  <html:checkbox name="ipk040Form" property="deleteAllCheck" value="check" onclick="changeChk();" />
  </td>
  </logic:equal>
  <logic:equal name="ipk040Form" property="sortKey" value="0">
  <logic:equal name="ipk040Form" property="orderKey" value="0">
  <td class="td_typeip4" width="15%" align="center" rowspan="2" nowrap>
  <a href="javascript:void(0)" onclick="return sortOrderKey(0,1);" style="text-decoration: none;">
  <span class="text_tlw"><gsmsg:write key="ipk.6" />▲</span></a></td>
  </logic:equal>
  <logic:equal name="ipk040Form" property="orderKey" value="1">
  <td class="td_typeip4" width="15%" align="center" rowspan="2" nowrap>
  <a href="javascript:void(0)" onclick="return sortOrderKey(0,0);" style="text-decoration: none;">
  <span class="text_tlw"><gsmsg:write key="ipk.6" />▼</span></a></td>
  </logic:equal>
  </logic:equal>
  <logic:notEqual name="ipk040Form" property="sortKey" value="0">
  <td class="td_typeip4" width="15%" align="center" rowspan="2" nowrap>
  <a href="javascript:void(0)" onclick="return sortOrderKey(0,0);" style="text-decoration: none;">
  <span class="text_tlw">&nbsp; <gsmsg:write key="ipk.6" /> &nbsp;</span></a></td>
  </logic:notEqual>
  <logic:equal name="ipk040Form" property="sortKey" value="1">
  <logic:equal name="ipk040Form" property="orderKey" value="0">
  <td class="td_typeip4" width="15%" align="center" rowspan="2" nowrap>
  <a href="javascript:void(0)" onclick="return sortOrderKey(1,1);" style="text-decoration: none;">
  <span class="text_tlw"><gsmsg:write key="ipk.7" />▲</span></a></td>
  </logic:equal>
  <logic:equal name="ipk040Form" property="orderKey" value="1">
  <td class="td_typeip4" width="15%" align="center" rowspan="2" nowrap>
  <a href="javascript:void(0)" onclick="return sortOrderKey(1,0);" style="text-decoration: none;">
  <span class="text_tlw"><gsmsg:write key="ipk.7" />▼</span></a></td>
  </logic:equal>
  </logic:equal>
  <logic:notEqual name="ipk040Form" property="sortKey" value="1">
  <td class="td_typeip4" width="15%" align="center" rowspan="2" nowrap>
  <a href="javascript:void(0)" onclick="return sortOrderKey(1,0);" style="text-decoration: none;">
  <span class="text_tlw">&nbsp; <gsmsg:write key="ipk.7" /> &nbsp;</span></a></td>
  </logic:notEqual>
  <logic:equal name="ipk040Form" property="sortKey" value="2">
  <logic:equal name="ipk040Form" property="orderKey" value="0">
  <td class="td_typeip4" width="8%" align="center" rowspan="2" nowrap>
  <a href="javascript:void(0)" onclick="return sortOrderKey(2,1);" style="text-decoration: none;">
  <span class="text_tlw"><gsmsg:write key="ipk.21" />▲</span></a></td>
  </logic:equal>
  <logic:equal name="ipk040Form" property="orderKey" value="1">
  <td class="td_typeip4" width="8%" align="center" rowspan="2" nowrap>
  <a href="javascript:void(0)" onclick="return sortOrderKey(2,0);" style="text-decoration: none;">
  <span class="text_tlw"><gsmsg:write key="ipk.21" />▼</span></a></td>
  </logic:equal>
  </logic:equal>

  <logic:notEqual name="ipk040Form" property="sortKey" value="2">
  <td class="td_typeip4" width="8%" align="center" rowspan="2" nowrap>
  <a href="javascript:void(0)" onclick="return sortOrderKey(2,0);" style="text-decoration: none;">
  <span class="text_tlw"> <gsmsg:write key="ipk.21" /> </span></a></td>
  </logic:notEqual>
  <td class="td_typeip4" width="11%" align="center" rowspan="2" nowrap>
  <span class="text_tlw"><gsmsg:write key="cmn.employer" /></span></td>

  <logic:equal name="ipk040Form" property="sortKey" value="6">
  <logic:equal name="ipk040Form" property="orderKey" value="1">
  <td class="td_typeip4" width="15%" align="center" nowrap>
  <a href="javascript:void(0)" onclick="return sortOrderKey(6,0);" style="text-decoration: none;">
  <span class="text_tlw">CPU▲</span></a></td>
  </logic:equal>
  <logic:equal name="ipk040Form" property="orderKey" value="0">
  <td class="td_typeip4" width="15%" align="center" nowrap>
  <a href="javascript:void(0)" onclick="return sortOrderKey(6,1);" style="text-decoration: none;">
  <span class="text_tlw">CPU▼</span></a></td>
  </logic:equal>
  </logic:equal>
  <logic:notEqual name="ipk040Form" property="sortKey" value="6">
  <td class="td_typeip4" width="15%" align="center" nowrap>
  <a href="javascript:void(0)" onclick="return sortOrderKey(6,1);" style="text-decoration: none;">
  <span class="text_tlw">&nbsp; CPU &nbsp;</span></a></td>
  </logic:notEqual>

  <logic:equal name="ipk040Form" property="sortKey" value="7">
  <logic:equal name="ipk040Form" property="orderKey" value="1">
  <td class="td_typeip4" width="15%" align="center" nowrap>
  <a href="javascript:void(0)" onclick="return sortOrderKey(7,0);" style="text-decoration: none;">
  <span class="text_tlw"><gsmsg:write key="cmn.memory" />▲</span></a></td>
  </logic:equal>
  <logic:equal name="ipk040Form" property="orderKey" value="0">
  <td class="td_typeip4" width="15%" align="center" nowrap>
  <a href="javascript:void(0)" onclick="return sortOrderKey(7,1);" style="text-decoration: none;">
  <span class="text_tlw"><gsmsg:write key="cmn.memory" />▼</span></a></td>
  </logic:equal>
  </logic:equal>
  <logic:notEqual name="ipk040Form" property="sortKey" value="7">
  <td class="td_typeip4" width="15%" align="center" nowrap>
  <a href="javascript:void(0)" onclick="return sortOrderKey(7,1);" style="text-decoration: none;">
  <span class="text_tlw">&nbsp; <gsmsg:write key="cmn.memory" /> &nbsp;</span></a></td>
  </logic:notEqual>

  <logic:equal name="ipk040Form" property="sortKey" value="8">
  <logic:equal name="ipk040Form" property="orderKey" value="1">
  <td class="td_typeip4" width="15%" align="center" nowrap>
  <a href="javascript:void(0)" onclick="return sortOrderKey(8,0);" style="text-decoration: none;">
  <span class="text_tlw">HD▲</span></a></td>
  </logic:equal>
  <logic:equal name="ipk040Form" property="orderKey" value="0">
  <td class="td_typeip4" width="15%" align="center" nowrap>
  <a href="javascript:void(0)" onclick="return sortOrderKey(8,1);" style="text-decoration: none;">
  <span class="text_tlw">HD▼</span></a></td>
  </logic:equal>
  </logic:equal>
  <logic:notEqual name="ipk040Form" property="sortKey" value="8">
  <td class="td_typeip4" width="15%" align="center" nowrap>
  <a href="javascript:void(0)" onclick="return sortOrderKey(8,1);" style="text-decoration: none;">
  <span class="text_tlw">&nbsp; HD &nbsp;</span></a></td>
  </logic:notEqual>
  <td class="td_typeip4" width="0%" align="center" rowspan="2" nowrap>
  <span class="text_tlw"><gsmsg:write key="cmn.detail" /></span></td>
  </tr>

  <tr class="tr_bg_7D91BD">
  <logic:equal name="ipk040Form" property="sortKey" value="3">
  <logic:equal name="ipk040Form" property="orderKey" value="0">
  <td class="td_typeip4" width="100%" align="center" colspan="3" nowrap>
  <a href="javascript:void(0)" onclick="return sortOrderKey(3,1);" style="text-decoration: none;">
  <span class="text_tlw"><gsmsg:write key="cmn.comment" />▲</span></a></td>
  </logic:equal>
  <logic:equal name="ipk040Form" property="orderKey" value="1">
  <td class="td_typeip4" width="100%" align="center" colspan="3" nowrap>
  <a href="javascript:void(0)" onclick="return sortOrderKey(3,0);" style="text-decoration: none;">
  <span class="text_tlw"><gsmsg:write key="cmn.comment" />▼</span></a></td>
  </logic:equal>
  </logic:equal>
  <logic:notEqual name="ipk040Form" property="sortKey" value="3">
  <td class="td_typeip4" width="100%" align="center" colspan="3" nowrap>
  <a href="javascript:void(0)" onclick="return sortOrderKey(3,0);" style="text-decoration: none;">
  <span class="text_tlw">&nbsp; <gsmsg:write key="cmn.comment" /> &nbsp;</span></a></td>
  </logic:notEqual>
  </tr>

  <logic:notEmpty name="ipk040Form" property="ipkAddList">
  <logic:iterate id="param" name="ipk040Form" property="ipkAddList" indexId="idx">
  <bean:define id="backclass" value="td_type20_" />
  <bean:define id="backpat" value="<%= String.valueOf((idx.intValue() % 2) + 1) %>" />
  <bean:define id="back" value="<%= String.valueOf(backclass) + String.valueOf(backpat) %>" />

  <tr>
  <logic:equal name="ipk040Form" property="checkBoxDspFlg" value="true">
  <td class="<%= String.valueOf(back) %>" align="center" rowspan="2">
  <logic:equal name="param" property="iadAdmFlg" value="true">
  <bean:define id="delCheck" name="param" property="iadSid" type="java.lang.Integer" />
  <html:multibox property="deleteCheck" value="<%= Integer.toString(delCheck.intValue()) %>"/>
  </logic:equal>
  </td>
  </logic:equal>

  <td class="<%= String.valueOf(back) %>" rowspan="2"><bean:write name="param" property="iadIpadDsp" /></td>
  <td class="<%= String.valueOf(back) %>" rowspan="2" nowrap><bean:write name="param" property="iadName" /></td>
  <td class="<%= String.valueOf(back) %>" rowspan="2" nowrap align="center">
  <logic:equal name="param" property="iadUseKbn" value="0"><span class="font_ff5500"> <gsmsg:write key="cmn.unused" /> </span></logic:equal>
  <logic:equal name="param" property="iadUseKbn" value="1"> <gsmsg:write key="cmn.in.use" /> </logic:equal>
  </td>
  <td class="<%= String.valueOf(back) %>" rowspan="2" nowrap>

  <logic:notEmpty name="param" property="userSeiMei">
    <logic:iterate id="addAdm" name="param" property="userSeiMei">
      <logic:equal name="addAdm" property="usrJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_TOROKU) %>">
        <bean:write name="addAdm" property="usiSei" />&nbsp;<bean:write name="addAdm" property="usiMei" /><br>
      </logic:equal>
      <logic:equal name="addAdm" property="usrJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
        <strike><bean:write name="addAdm" property="usiSei" />&nbsp;<bean:write name="addAdm" property="usiMei" /></strike><br>
      </logic:equal>
    </logic:iterate>
  </logic:notEmpty>

  </td>
  <td class="<%= String.valueOf(back) %>" nowrap><bean:write name="param" property="iadCpuName" filter="false"/></td>
  <td class="<%= String.valueOf(back) %>" nowrap><bean:write name="param" property="iadMemoryName" filter="false"/></td>
  <td class="<%= String.valueOf(back) %>" nowrap><bean:write name="param" property="iadHdName" filter="false"/></td>
  <td class="<%= String.valueOf(back) %>" align="center" rowspan="2">
  <input type="button" class="btn_base1s" name="edit" value="<gsmsg:write key="cmn.detail" />"
   onClick="ipk040ButtonPush('ipEdit', '<bean:write name="param" property="iadSid" />', '0');">
  </td>
  </tr>
  <tr>
  <td class="<%= String.valueOf(back) %>" valign="top" colspan="3"><bean:write name="param" property="iadMsg" /></td>
  </tr>

  </logic:iterate>
  </logic:notEmpty>

  <tr>
  <logic:equal name="ipk040Form" property="iadNetAdmFlg" value="true">
  <td colspan="9" width="100%" align="right" valign="top" nowrap>
  </logic:equal>
  <logic:equal name="ipk040Form" property="iadNetAdmFlg" value="false">
  <logic:greaterThan name="ipk040Form" property="dspFlg" value="0">
  <td colspan="9" width="100%" align="right" valign="top" nowrap>
  </logic:greaterThan>
  <logic:equal name="ipk040Form" property="dspFlg" value="0">
  <td colspan="8" width="100%" align="right" valign="top" nowrap>
  </logic:equal>
  </logic:equal>
  <logic:greaterThan name="ipk040Form" property="maxPageNum" value="1">
  <img alt="<gsmsg:write key="cmn.previous.page" />" height="20" src="../ipkanri/images/arrow2_l.gif" class="img_bottom" width="20" border="0" onClick="buttonPush2('arrorw_left');">
  <logic:notEmpty name="ipk040Form" property="iadPageLabel">
  <html:select property="iadPage2" onchange="changePage2();" styleClass="text_i">
  <html:optionsCollection name="ipk040Form" property="iadPageLabel" value="value" label="label" />
  </html:select>
  </logic:notEmpty>
  <img src="../ipkanri/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" border="0" onClick="buttonPush2('arrorw_right');">
  </logic:greaterThan>
  </td>
  </tr>

  </table>
</td>
</tr>

<tr>
<td>
  <table align="center" border="0" cellSpacing="0" width="98%">
  <tr>
  <td align="right" nowrap>
  <logic:equal name="ipk040Form" property="checkBoxDspFlg" value="true">
  <input type="button" name="delete" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush2('selectDelete');">
  </logic:equal>
  <logic:equal name="ipk040Form" property="iadNetAdmFlg" value="true">
  <input type="button" name="add" value="<gsmsg:write key="cmn.add" />" class="btn_add_n1"
  onClick="ipk040ButtonPush('ipAdd', '' , '0');">
  </logic:equal>
  <logic:equal name="ipk040Form" property="iadNetAdmFlg" value="true">
  <input type="button" name="export" value="<gsmsg:write key="cmn.import" />" class="btn_csv_n1" onClick="buttonPush2('import');">
  </logic:equal>
  <logic:notEqual name="ipk040Form" property="maxCount" value="0">
  <input type="button" name="export" value="<gsmsg:write key="cmn.export" />" class="btn_csv_n2" onClick="buttonPush2('export');">
  </logic:notEqual>
  <input type="button" name="back" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('return', '');" class="btn_back_n1">
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