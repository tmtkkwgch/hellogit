<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>


<gsmsg:define id="siyou" msgkey="cmn.in.use" />
<gsmsg:define id="misiyou" msgkey="cmn.unused" />

<%

    String kbn_siyou     = String.valueOf(jp.groupsession.v2.ip.IpkConst.USEDKBN_SIYOU);
    String kbn_misiyou       = String.valueOf(jp.groupsession.v2.ip.IpkConst.USEDKBN_MISIYOU);
    String kbn_siyou_str = siyou;
    String kbn_misiyou_str   = misiyou;
    String maxLengthComment = String.valueOf(jp.groupsession.v2.ip.IpkConst.MAX_LENGTH_MSG);

%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../ipkanri/js/ipkanri.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../ipkanri/css/ip.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<title>[GroupSession] <gsmsg:write key="ipk.ipk050.1" /></title>
</head>

<body class="body_03" onload="showOrHide();showLengthId($('#inputstr')[0], <%= maxLengthComment %>, 'inputlength');scroll2();">
<html:form action="/ipkanri/ipk050">
<html:hidden property="netSid" />
<html:hidden property="iadSid" />
<html:hidden property="binSid" />
<html:hidden property="ipk050NetSid" />
<html:hidden property="cmd" />
<html:hidden property="iadCount" />
<html:hidden property="iadCountUse" />
<html:hidden property="iadCountNotUse" />
<html:hidden property="textNum" />
<html:hidden property="iadPageNum" />
<html:hidden property="maxPageNum" />
<html:hidden property="sortKey" />
<html:hidden property="orderKey" />
<html:hidden property="returnCmd" />
<html:hidden property="usedKbn" />
<html:hidden property="ipk050AdminFlg" />
<html:hidden property="ipk050NetAdminFlg" />
<html:hidden property="iadLimit" />
<html:hidden property="deleteAllCheck" />
<html:hidden property="ipk070PageNow" />
<html:hidden property="ipk070MaxPageNum" />
<html:hidden property="ipk070SltNet" />
<html:hidden property="ipk070SltUser" />
<html:hidden property="ipk070SltGroup" />
<html:hidden property="ipk070SearchSortKey1" />
<html:hidden property="ipk070SearchOrderKey1" />
<html:hidden property="ipk070SearchSortKey2" />
<html:hidden property="ipk070SearchOrderKey2" />
<html:hidden property="ipk070KeyWord" />
<html:hidden property="ipk070KeyWordkbn" />
<html:hidden property="ipk070SvNetSid" />
<html:hidden property="ipk070SvSltNet" />
<html:hidden property="ipk070SvGrpSid" />
<html:hidden property="ipk070SvUsrSid" />
<html:hidden property="ipk070SvSearchSortKey1" />
<html:hidden property="ipk070SvSearchOrderKey1" />
<html:hidden property="ipk070SvSearchSortKey2" />
<html:hidden property="ipk070SvSearchOrderKey2" />
<html:hidden property="ipk070SvKeyWord" />
<html:hidden property="ipk070SvKeyWordkbn" />

<logic:notEmpty name="ipk050Form" property="ipk070SearchTarget">
<logic:iterate id="sTarget" name="ipk050Form" property="ipk070SearchTarget">
  <input type="hidden" name="ipk070SearchTarget" value="<bean:write name="sTarget" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="ipk050Form" property="ipk070SvSearchTarget">
<logic:iterate id="svSTarget" name="ipk050Form" property="ipk070SvSearchTarget">
  <input type="hidden" name="ipk070SvSearchTarget" value="<bean:write name="svSTarget" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="ipk050Form" property="deleteCheck">
<logic:iterate id="param" name="ipk050Form" property="deleteCheck">
  <input type="hidden" name="deleteCheck" value="<bean:write name="param" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="ipk050Form" property="adminSidList">
<logic:iterate id="param" name="ipk050Form" property="adminSidList">
  <input type="hidden" name="adminSidList" value="<bean:write name="param" />">
</logic:iterate>
</logic:notEmpty>

<logic:equal name="ipk050Form" property="textNum" value="2">
  <html:hidden property="iadAddText1" />
</logic:equal>

<logic:equal name="ipk050Form" property="textNum" value="3">
  <html:hidden property="iadAddText1" />
  <html:hidden property="iadAddText2" />
</logic:equal>

<logic:equal name="ipk050Form" property="textNum" value="4">
  <html:hidden property="iadAddText1" />
  <html:hidden property="iadAddText2" />
  <html:hidden property="iadAddText3" />
</logic:equal>

<html:hidden name="ipk050Form" property="ipk050DspKbn" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<input type="hidden" name="helpPrm" value="<bean:write name='ipk050Form' property='ipk050DspKbn' />">

<html:hidden property="ipk050ScrollFlg" />

<table align="center" cellpadding="0" class="table_kotei3" >
<tr>
<td>
  <table width="820" align="center">
  <tr>
  <td>
    <table cellpadding="0" class="table_kotei2">
    <tr>
    <td width="0%"><img src="../ipkanri/images/header_ipkanri_icon_01.gif" border="0" alt="<gsmsg:write key="cmn.ipkanri" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.ipkanri" /></span></td>
    <td width="100%" class="header_white_bg_text">
    <logic:equal name="ipk050Form" property="ipk050AdminFlg" value="true">
    <logic:empty name="ipk050Form" property="iadSid">[ <gsmsg:write key="ipk.ipk050.2" /> ]</logic:empty>
    <logic:notEmpty name="ipk050Form" property="iadSid">[ <gsmsg:write key="ipk.ipk050.1" /> ]</logic:notEmpty>
    </logic:equal>
    <logic:equal name="ipk050Form" property="ipk050AdminFlg" value="false">[ <gsmsg:write key="ipk.ipk050.3" /> ]
    </logic:equal>
    </td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.ipkanri" />"></td>
    </tr>
    </table>
    <br>
  </td>
  </tr>
<!--親ネットワーク情報-->
  <tr>
  <td>

    <div id="hide">
    <table class="table_ip" width="100%">
    <tr>
    <td class="table_bg_7D91BD" width="20%" nowrap>
      <table cellpadding="0" cellspacing="0">
      <tr>
      <td><img src="../ipkanri/images/ipkanri_icon.gif" width="25" height="25" border="0" class="header_icon" ></td>
      <td nowrap>&nbsp;<span class="font_white"><gsmsg:write key="ipk.4" /></span></td>
      </tr>
      </table>
    </td>
    <td align="left" width="80%" class="td_type20_1">
      <table width="100%">
      <tr>
      <td nowrap>
      <span class="font_black2">
      <bean:write name="ipk050Form" property="netName" /></span></td>
      <td align="right" width="100%" class="font_black3" nowrap><gsmsg:write key="ipk.5" />：<bean:write name="ipk050Form" property="iadCount" />
     （<gsmsg:write key="cmn.in.use" />: <bean:write name="ipk050Form" property="iadCountUse" />
      <gsmsg:write key="cmn.unused" />: <bean:write name="ipk050Form" property="iadCountNotUse" />) &nbsp;
      </td>
      <td align="right" width="0%">
      <input type="button" value="<gsmsg:write key="cmn.show" />" class="btn_base1s" onClick="showText()">
      </td>
      </tr>
      </table>
    </td>
    </tr>
    </table>
    </div>

    <div id="show">
    <table width="100%" align="center" cellpadding="0" cellspacing="0" class="td_typeip">
    <tr>
    <logic:equal name="ipk050Form" property="ipk050TempExist" value="true">
    <logic:equal name="ipk050Form" property="ipk050NetAdminFlg" value="true">
    <td align="left" class="td_border" colspan="4">
    </logic:equal>

    <logic:equal name="ipk050Form" property="ipk050NetAdminFlg" value="false">

    <logic:notEmpty name="ipk050Form" property="koukaiBinFileInfList">
    <td align="left" class="td_border" colspan="4">
    </logic:notEmpty>

    <logic:empty name="ipk050Form" property="koukaiBinFileInfList">
    <td align="left" class="td_border" colspan="3">
    </logic:empty>

    </logic:equal>
    </logic:equal>

    <logic:equal name="ipk050Form" property="ipk050TempExist" value="false">
    <td align="left" class="td_border" colspan="3">
    </logic:equal>

      <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr class="table_bg_7D91BD">
      <td align="left" nowrap border="0" width="0%">
      <img src="../ipkanri/images/ipkanri_icon.gif" width="30" height="30" border="0" class="header_icon">
      </td>
      <td align="left" width="0%" nowrap>
      <span class="font_white">&nbsp;<bean:write name="ipk050Form" property="netName" /></span>
      </td>
      <td align="right" width="90%" nowrap>
      <span class="font_white2">　<gsmsg:write key="ipk.5" />：<bean:write name="ipk050Form" property="iadCount" />
    （<gsmsg:write key="cmn.in.use" />: <bean:write name="ipk050Form" property="iadCountUse" />
     <gsmsg:write key="cmn.unused" />: <bean:write name="ipk050Form" property="iadCountNotUse" />) &nbsp; </span>
      </td>
      <td align="right" width="10%" nowrap>
      <input type="button" value="<gsmsg:write key="cmn.hide" />" class="btn_base1s_2" onClick="hideText()">&nbsp;
      </td>
      </tr>
      </table>
    </td>
    </tr>
    <logic:equal name="ipk050Form" property="ipk050TempExist" value="true">
    <logic:equal name="ipk050Form" property="ipk050NetAdminFlg" value="true">
    <tr>
    <td class="td_type3" width="0%" nowrap><gsmsg:write key="ipk.2" /></td>
    <td class="td_type3" width="0%" nowrap><gsmsg:write key="ipk.3" /></td>
    <td class="td_type3" width="100%" nowrap colspan="2"><gsmsg:write key="cmn.comment" /></td>
    </tr>

    <logic:notEmpty name="ipk050Form" property="koukaiBinFileInfList">
    <logic:notEmpty name="ipk050Form" property="hikoukaiBinFileInfList">
    <tr>
    <td class="td_type20_1" align="center" rowspan="3">
    <span class="font_000000"><bean:write name="ipk050Form" property="netNetad" /></span>
    </td>
    <td class="td_type20_1" align="center" rowspan="3">
    <span class="font_000000"><bean:write name="ipk050Form" property="netSabnet" /></span>
    </td>
    <td align="left" class="td_type20_1" valign="top" colspan="2">
    <span class="font_000000"><bean:write name="ipk050Form" property="netMsg" filter="false" /></span>
    </td>
    </tr>

    <tr>
    <td class="td_type3" width="20%" nowrap><gsmsg:write key="cmn.attach.file" />(<gsmsg:write key="cmn.public" />)</td>
    <td width="80%" class="td_type20_1">
    <logic:iterate id="koukaiFileMdl" name="ipk050Form" property="koukaiBinFileInfList">
    <img src="../common/images/file_icon.gif" alt="<gsmsg:write key="cmn.file" />">
    <a href="javascript:fileLinkClick(<bean:write name="koukaiFileMdl" property="binSid" />);">
    <span class="text_link"><bean:write name="koukaiFileMdl" property="binFileName" /><bean:write name="koukaiFileMdl" property="binFileSizeDsp" /></a><br>
    </logic:iterate>
    </td>
    </tr>

    <tr>
    <td class="td_type3" width="20%" nowrap><gsmsg:write key="cmn.attach.file" />(<gsmsg:write key="cmn.private" />)</td>
    <td width="80%" class="td_type20_1">
    <logic:iterate id="hikoukaiFileMdl" name="ipk050Form" property="hikoukaiBinFileInfList">
    <img src="../common/images/file_icon.gif" alt="<gsmsg:write key="cmn.file" />">
    <a href="javascript:fileLinkClick(<bean:write name="hikoukaiFileMdl" property="binSid" />);">
    <span class="text_link"><bean:write name="hikoukaiFileMdl" property="binFileName" /><bean:write name="hikoukaiFileMdl" property="binFileSizeDsp" /></span></a></br>
    </logic:iterate>
    </td>
    </tr>
    </logic:notEmpty>
    </logic:notEmpty>

    <logic:empty name="ipk050Form" property="koukaiBinFileInfList">
    <tr>
    <td class="td_type20_1" align="center" rowspan="2">
    <span class="font_000000"><bean:write name="ipk050Form" property="netNetad" /></span>
    </td>
    <td class="td_type20_1" align="center" rowspan="2">
    <span class="font_000000"><bean:write name="ipk050Form" property="netSabnet" /></span>
    </td>
    <td align="left" class="td_type20_1" valign="top" colspan="2">
    <span class="font_000000"><bean:write name="ipk050Form" property="netMsg" filter="false" /></span>
    </td>
    </tr>

    <tr>
    <td class="td_type3" width="20%" nowrap><gsmsg:write key="cmn.attach.file" />(<gsmsg:write key="cmn.private" />)</td>
    <td width="80%" class="td_type20_1">
    <logic:iterate id="hikoukaiFileMdl" name="ipk050Form" property="hikoukaiBinFileInfList">
    <img src="../common/images/file_icon.gif" alt="<gsmsg:write key="cmn.file" />">
    <a href="javascript:fileLinkClick(<bean:write name="hikoukaiFileMdl" property="binSid" />);">
    <span class="text_link"><bean:write name="hikoukaiFileMdl" property="binFileName" /><bean:write name="hikoukaiFileMdl" property="binFileSizeDsp" /></span></a></br>
    </logic:iterate>
    </td>
    </tr>
    </logic:empty>

    <logic:empty name="ipk050Form" property="hikoukaiBinFileInfList">
    <tr>
    <td class="td_type20_1" align="center" rowspan="2">
    <span class="font_000000"><bean:write name="ipk050Form" property="netNetad" /></span>
    </td>
    <td class="td_type20_1" align="center" rowspan="2">
    <span class="font_000000"><bean:write name="ipk050Form" property="netSabnet" /></span>
    </td>
    <td align="left" class="td_type20_1" valign="top" colspan="2">
    <span class="font_000000"><bean:write name="ipk050Form" property="netMsg" filter="false" /></span>
    </td>
    </tr>

    <tr>
    <td class="td_type3" width="20%" nowrap><gsmsg:write key="cmn.attach.file" />(<gsmsg:write key="cmn.public" />)</td>
    <td width="80%" class="td_type20_1">
    <logic:iterate id="koukaiFileMdl" name="ipk050Form" property="koukaiBinFileInfList">
    <img src="../common/images/file_icon.gif" alt="<gsmsg:write key="cmn.file" />">
    <a href="javascript:fileLinkClick(<bean:write name="koukaiFileMdl" property="binSid" />);">
    <span class="text_link"><bean:write name="koukaiFileMdl" property="binFileName" /><bean:write name="koukaiFileMdl" property="binFileSizeDsp" /></span></a></br>
    </logic:iterate>
    </td>
    </tr>
    </logic:empty>
    </logic:equal>

    <logic:equal name="ipk050Form" property="ipk050NetAdminFlg" value="false">
    <logic:notEmpty name="ipk050Form" property="koukaiBinFileInfList">
    <td class="td_type3"><gsmsg:write key="ipk.2" /></td>
    <td class="td_type3"><gsmsg:write key="ipk.3" /></td>
    <td class="td_type3" colspan="2"><gsmsg:write key="cmn.comment" /></td>
    </tr>

    <tr>
    <td class="td_type20_1" align="center" rowspan="2"><span class="000000"><bean:write name="ipk050Form" property="netNetad" /></span></td>
    <td class="td_type20_1" align="center" rowspan="2"><span class="000000"><bean:write name="ipk050Form" property="netSabnet" /></span></td>
    <td align="left" class="td_type20_1" colspan="2"><span class="000000"><bean:write name="ipk050Form" property="netMsg" filter="false"/></span></td>
    </tr>

    <tr>
    <td class="td_type3"><gsmsg:write key="cmn.attach.file" />(<gsmsg:write key="cmn.public" />)</td>
    <td class="td_type20_1" align="center">
      <table cellpadding="0" cellpadding="0" border="0">
      <logic:iterate id="koukaiFileMdl" name="ipk050Form" property="koukaiBinFileInfList">
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

    <logic:empty name="ipk050Form" property="koukaiBinFileInfList">
    <td class="td_type3"><gsmsg:write key="ipk.2" /></td>
    <td class="td_type3"><gsmsg:write key="ipk.3" /></td>
    <td class="td_type3"><gsmsg:write key="cmn.comment" /></td>
    </tr>

    <tr>
    <td class="td_type20_1" align="center"><span class="000000"><bean:write name="ipk050Form" property="netNetad" /></span></td>
    <td class="td_type20_1" align="center"><span class="000000"><bean:write name="ipk050Form" property="netSabnet" /></span></td>
    <td align="left" class="td_type20_1"><span class="000000"><bean:write name="ipk050Form" property="netMsg" filter="false"/></span></td>
    </tr>
    </logic:empty>
    </logic:equal>
    </logic:equal>

    <logic:equal name="ipk050Form" property="ipk050TempExist" value="false">
    <td class="td_type3"><gsmsg:write key="ipk.2" /></td>
    <td class="td_type3"><gsmsg:write key="ipk.3" /></td>
    <td class="td_type3"><gsmsg:write key="cmn.comment" /></td>
    </tr>

    <tr>
    <td class="td_type20_1" align="center"><span class="000000"><bean:write name="ipk050Form" property="netNetad" /></span></td>
    <td class="td_type20_1" align="center"><span class="000000"><bean:write name="ipk050Form" property="netSabnet" /></span></td>
    <td align="left" class="td_type20_1"><span class="000000"><bean:write name="ipk050Form" property="netMsg" filter="false"/></span></td>
    </tr>
    </logic:equal>
    </table></div><br><br>
  </td>
  </tr>

  <tr>
  <td>
  <span class="text_error"><html:errors/></span>
  </td>
  </tr>

  <tr>
  <td>
    <table class="table_padding" cellspacing="0" cellpadding="0">

    <tr>
    <td tclass="text_base">
      <table class="table_padding2" cellpadding="0" >

      <tr class="table_bg_7D91BD">
      <td><span class="text_tlw"> &nbsp;
      <logic:equal name="ipk050Form" property="ipk050AdminFlg" value="true">
      <logic:notEqual name="ipk050Form" property="cmd" value="ipEdit"><gsmsg:write key="ipk.ipk050.4" /></logic:notEqual>
      <logic:equal name="ipk050Form" property="cmd" value="ipEdit"><gsmsg:write key="ipk.ipk050.5" /></logic:equal>
      </logic:equal>
      <logic:equal name="ipk050Form" property="ipk050AdminFlg" value="false"><gsmsg:write key="cmn.detail" /></logic:equal>
      </span></td>
      <td align="right">
      <logic:equal name="ipk050Form" property="ipk050AdminFlg" value="true">
      <logic:notEmpty name="ipk050Form" property="iadSid">
      <input type="button" name="" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush2('ipadDelete');">
      </logic:notEmpty>
      <logic:empty name="ipk050Form" property="iadSid">
      <input type="button" name="add" value="<gsmsg:write key="cmn.entry" />" class="btn_base1" onClick="buttonPush2('iadAdd');">
      </logic:empty>
      <logic:notEmpty name="ipk050Form" property="iadSid">
      <input type="button" name="edit" value="<gsmsg:write key="cmn.edit" />" class="btn_edit_n1" onClick="buttonPush2('iadEdit');">
      </logic:notEmpty>
      </logic:equal>
      <input type="button" name="cancel" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush2('ipReturn');" class="btn_back_n1"> &nbsp;
      </td>
      </tr>

      <tr>
      <td width="140" align="left" class="table_bg_A5B4E1" nowrap><span class="font_black"><gsmsg:write key="ipk.6" /></span>
      <logic:equal name="ipk050Form" property="ipk050AdminFlg" value="true"><span class="font_red">※</span></logic:equal></td>
      <td class="td_type20_1">
      <logic:equal name="ipk050Form" property="ipk050AdminFlg" value="true">
      <logic:equal name="ipk050Form" property="textNum" value="1">
      <input type="text" name="iadAddText1" style="width:57px;" maxlength="3" value="<bean:write name="ipk050Form" property="iadAddText1" />" class="text_number">.
      <input type="text" name="iadAddText2" style="width:57px;" maxlength="3" value="<bean:write name="ipk050Form" property="iadAddText2" />" class="text_number">.
      <input type="text" name="iadAddText3" style="width:57px;" maxlength="3" value="<bean:write name="ipk050Form" property="iadAddText3" />" class="text_number">.
      <input type="text" name="iadAddText4" style="width:57px;" maxlength="3" value="<bean:write name="ipk050Form" property="iadAddText4" />" class="text_number">
      </logic:equal>

      <logic:equal name="ipk050Form" property="textNum" value="2">
      <bean:write name="ipk050Form" property="iadAddText1" />.
      <input type="text" name="iadAddText2" style="width:57px;" maxlength="3" value="<bean:write name="ipk050Form" property="iadAddText2" />" class="text_number">.
      <input type="text" name="iadAddText3" style="width:57px;" maxlength="3" value="<bean:write name="ipk050Form" property="iadAddText3" />" class="text_number">.
      <input type="text" name="iadAddText4" style="width:57px;" maxlength="3" value="<bean:write name="ipk050Form" property="iadAddText4" />" class="text_number">
      </logic:equal>

      <logic:equal name="ipk050Form" property="textNum" value="3">
      <bean:write name="ipk050Form" property="iadAddText1" />.
      <bean:write name="ipk050Form" property="iadAddText2" />.
      <input type="text" name="iadAddText3" style="width:57px;" maxlength="3" value="<bean:write name="ipk050Form" property="iadAddText3" />" class="text_number">.
      <input type="text" name="iadAddText4" style="width:57px;" maxlength="3" value="<bean:write name="ipk050Form" property="iadAddText4" />" class="text_number">
      </logic:equal>

      <logic:equal name="ipk050Form" property="textNum" value="4">
      <bean:write name="ipk050Form" property="iadAddText1" />.
      <bean:write name="ipk050Form" property="iadAddText2" />.
      <bean:write name="ipk050Form" property="iadAddText3" />.
      <input type="text" name="iadAddText4" style="width:57px;" maxlength="3" value="<bean:write name="ipk050Form" property="iadAddText4" />" class="text_number">
      </logic:equal>
      </logic:equal>
      <logic:equal name="ipk050Form" property="ipk050AdminFlg" value="false">
      <bean:write name="ipk050Form" property="iadAddText1" />.
      <bean:write name="ipk050Form" property="iadAddText2" />.
      <bean:write name="ipk050Form" property="iadAddText3" />.
      <bean:write name="ipk050Form" property="iadAddText4" />
      </logic:equal>
      </td>
      </tr>

      <tr>
      <td valign="top" align="left" class="table_bg_A5B4E1" nowrap><span class="font_black"><gsmsg:write key="ipk.7" /></span>
      <logic:equal name="ipk050Form" property="ipk050AdminFlg" value="true"><span class="font_red">※</span></logic:equal></td>
      <td class="td_type20_1">
      <logic:equal name="ipk050Form" property="ipk050AdminFlg" value="true">
      <input type="text" name="iadMachineName" style="width:213px;" maxlength="50" value="<bean:write name="ipk050Form" property="iadMachineName" />">
      </logic:equal>
      <logic:equal name="ipk050Form" property="ipk050AdminFlg" value="false"><bean:write name="ipk050Form" property="iadMachineName" />
      </logic:equal>
      </td>
      </tr>

      <tr>
      <td align="left" class="table_bg_A5B4E1" nowrap><span class="font_black"><gsmsg:write key="ipk.11" /></span></td>
      <td class="td_type20_1" cellspacing="0" cellpadding="0">
      <logic:equal name="ipk050Form" property="ipk050AdminFlg" value="true">
      <html:radio property="iadUse" styleId="kbn_siyou" value="<%= kbn_siyou %>" /><span class="text_base2"><label for="kbn_siyou" class="font_black4"><%= kbn_siyou_str %></label></span>&nbsp;
      <html:radio property="iadUse" styleId="kbn_misiyou" value="<%= kbn_misiyou %>"/><span class="text_base2"><label for="kbn_misiyou" class="font_black4"><%= kbn_misiyou_str %></label></span>
      </logic:equal>
      <logic:equal name="ipk050Form" property="ipk050AdminFlg" value="false">
      <logic:equal name="ipk050Form" property="iadUse" value="1"><gsmsg:write key="cmn.in.use" /></logic:equal>
      <logic:equal name="ipk050Form" property="iadUse" value="0"><gsmsg:write key="cmn.unused" /></logic:equal>
      </logic:equal>
      </td>
      </tr>

      <tr>
      <td align="left" valign="top" class="table_bg_A5B4E1" nowrap><span class="font_black"><gsmsg:write key="cmn.comment" /></span></td>
      <td class="td_type20_1">
      <logic:equal name="ipk050Form" property="ipk050AdminFlg" value="true">
      <textarea name="iadMsg" style="width:591px;" rows="7"  class="textarea" onkeyup="showLengthStr(value, <%= maxLengthComment %>, 'inputlength');" id="inputstr"><bean:write name="ipk050Form" property="iadMsg" /></textarea>
      <br>
      <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthComment %>&nbsp;<gsmsg:write key="cmn.character" /></span>
      </logic:equal>
      <logic:equal name="ipk050Form" property="ipk050AdminFlg" value="false">
      <bean:write name="ipk050Form" property="iadMsgHtml" filter="false"/>
      </logic:equal>
      </td>
      </tr>

      <tr>
      <td valign="top" align="left" class="table_bg_A5B4E1" nowrap><span class="font_black"><gsmsg:write key="cmn.asset.register.num" /></span></td>
      <td class="td_type20_1">
      <logic:equal name="ipk050Form" property="ipk050AdminFlg" value="true">
      <input type="text" name="iadPrtMngNum" style="width:213px;" maxlength="50" value="<bean:write name="ipk050Form" property="iadPrtMngNum" />">
      </logic:equal>
      <logic:equal name="ipk050Form" property="ipk050AdminFlg" value="false">
      <bean:write name="ipk050Form" property="iadPrtMngNum" />
      </logic:equal>
      </td>
      </tr>




      <tr>
      <td align="left" class="table_bg_A5B4E1" nowrap><span class="font_black">CPU</span></td>
      <td class="td_type20_1" cellspacing="0" cellpadding="0">
      <logic:equal name="ipk050Form" property="ipk050AdminFlg" value="true">
        <html:select name="ipk050Form" property="cpuSelect" styleClass="select01">
        <logic:notEmpty name="ipk050Form" property="ipk050cpuLabelList">
          <logic:iterate id="cpu" name="ipk050Form" property="ipk050cpuLabelList" indexId="idx">
            <bean:define id="backclass" value="bg_color" />
            <bean:define id="backpat" value="<%= String.valueOf((idx.intValue() % 2) + 1) %>" />
            <bean:define id="back" value="<%= String.valueOf(backclass) + String.valueOf(backpat) %>" />
            <bean:define id="cpuValue" name="cpu" property="value" />
            <html:option value="<%= String.valueOf(cpuValue) %>" styleClass="<%= String.valueOf(back) %>"><bean:write name="cpu" property="label" filter="false" /></html:option>
          </logic:iterate>
        </logic:notEmpty>
        </html:select>
      </logic:equal>

      <logic:equal name="ipk050Form" property="ipk050AdminFlg" value="false">
      <bean:write name="ipk050Form" property="cpuName" />
      </logic:equal>

      </td>
      </tr>

      <tr>
      <td align="left" class="table_bg_A5B4E1" nowrap><span class="font_black"><gsmsg:write key="cmn.memory" /></span></td>
      <td class="td_type20_1" cellspacing="0" cellpadding="0">
      <logic:equal name="ipk050Form" property="ipk050AdminFlg" value="true">
        <html:select name="ipk050Form" property="memorySelect" styleClass="select01">
        <logic:notEmpty name="ipk050Form" property="ipk050memoryLabelList">
          <logic:iterate id="memory" name="ipk050Form" property="ipk050memoryLabelList" indexId="idxmemory">
            <bean:define id="memoryBackclass" value="bg_color" />
            <bean:define id="memoryBackpat" value="<%= String.valueOf((idxmemory.intValue() % 2) + 1) %>" />
            <bean:define id="memoryBack" value="<%= String.valueOf(memoryBackclass) + String.valueOf(memoryBackpat) %>" />
            <bean:define id="memoryValue" name="memory" property="value" />
            <html:option value="<%= String.valueOf(memoryValue) %>" styleClass="<%= String.valueOf(memoryBack) %>"><bean:write name="memory" property="label" filter="false" /></html:option>
          </logic:iterate>
        </logic:notEmpty>
        </html:select>
      </logic:equal>
      <logic:equal name="ipk050Form" property="ipk050AdminFlg" value="false">
      <bean:write name="ipk050Form" property="memoryName" />
      </logic:equal>
      </td>
      </tr>

      <tr>
      <td align="left" class="table_bg_A5B4E1" nowrap><span class="font_black">HD</span></td>
      <td class="td_type20_1" cellspacing="0" cellpadding="0">
      <logic:equal name="ipk050Form" property="ipk050AdminFlg" value="true">
        <html:select name="ipk050Form" property="hdSelect" styleClass="select01">
        <logic:notEmpty name="ipk050Form" property="ipk050hdLabelList">
          <logic:iterate id="hd" name="ipk050Form" property="ipk050hdLabelList" indexId="idxhd">
            <bean:define id="hdBackclass" value="bg_color" />
            <bean:define id="hdBackpat" value="<%= String.valueOf((idxhd.intValue() % 2) + 1) %>" />
            <bean:define id="hdBack" value="<%= String.valueOf(hdBackclass) + String.valueOf(hdBackpat) %>" />
            <bean:define id="hdValue" name="hd" property="value" />
            <html:option value="<%= String.valueOf(hdValue) %>" styleClass="<%= String.valueOf(hdBack) %>"><bean:write name="hd" property="label" filter="false" /></html:option>
          </logic:iterate>
        </logic:notEmpty>
        </html:select>
      </logic:equal>
      <logic:equal name="ipk050Form" property="ipk050AdminFlg" value="false">
      <bean:write name="ipk050Form" property="hdName" />
      </logic:equal>
      <a id="add_user" name="add_user"></a>
      </td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="font_black"><gsmsg:write key="cmn.attached" />(<gsmsg:write key="cmn.public" />)</span></td>
      <td align="left" class="td_type20_1">
        <logic:equal name="ipk050Form" property="ipk050AdminFlg" value="true">
        <table class="table_kotei2">

        <tr>
        <td width="0%" class="td_button2">
        <input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="buttonPush2('delKoukaiTempFile');"></td>
        <td width="0%" class="td_button2">
        <input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTempPlus('ipk050KoukaiFiles', 'ipkanri', '0', '0', 'ipAddress/koukai');">
        </td>
        <td width="100%" class="td_button2">
        <input type="button" name="download" value="<gsmsg:write key="cmn.download" />" onClick="buttonPush2('koukaiTempDownload');" class="btn_base1_3">
        </td>
        </tr>

        <tr>
        <td colspan="3">
        <html:select property="ipk050KoukaiFiles" styleClass="select01" multiple="true">
        <logic:notEmpty name="ipk050Form" property="ipk050KoukaiFileLabelList">
        <html:optionsCollection name="ipk050Form" property="ipk050KoukaiFileLabelList" value="value" label="label" />
        </logic:notEmpty>
        </html:select>
        </td>
        </tr>

        </table>
        </logic:equal>

        <logic:equal name="ipk050Form" property="ipk050AdminFlg" value="false">
        <logic:notEmpty name="ipk050Form" property="ipk050KoukaiFileLabelList">
        <table class="table_kotei2">
        <tr>
        <td>
        <logic:iterate id="ipadKoukaiFileMdl" name="ipk050Form" property="ipadKoukaiBinFileInfList">
        <img src="../common/images/file_icon.gif" alt="<gsmsg:write key="cmn.file" />">
        <a href="javascript:ipAdrFileLinkClick(<bean:write name='ipadKoukaiFileMdl' property='binSid' />);">
        <span class="text_link"><bean:write name="ipadKoukaiFileMdl" property="binFileName" /><bean:write name="ipadKoukaiFileMdl" property="binFileSizeDsp" /></a><br>
        </logic:iterate>
        </td>
        </tr>
        </table>
        </logic:notEmpty>
        </logic:equal>
      </td>
      </tr>

      <logic:equal name="ipk050Form" property="ipk050AdminFlg" value="true">
      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="font_black"><gsmsg:write key="cmn.attached" />(<gsmsg:write key="cmn.private" />)</span></td>
      <td align="left" class="td_type20_1">
        <table class="table_kotei2">

        <tr>
        <td width="0%" class="td_button2" align="left">
        <input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="buttonPush2('delHikoukaiTempFile');"></td>
        <td width="0%" class="td_button2">
        <input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTempPlus('ipk050HikoukaiFiles', 'ipkanri', '0', '0', 'ipAddress/hikoukai');">
        </td>
        <td width="100%" class="td_button2">
        <input type="button" name="download" value="<gsmsg:write key="cmn.download" />" onClick="buttonPush2('hikoukaiTempDownload');" class="btn_base1_3">
        </td>
        </tr>

        <tr>
        <td colspan="3">
        <html:select property="ipk050HikoukaiFiles" styleClass="select01" multiple="true">
        <logic:notEmpty name="ipk050Form" property="ipk050HikoukaiFileLabelList">
        <html:optionsCollection name="ipk050Form" property="ipk050HikoukaiFileLabelList" value="value" label="label" />
        </logic:notEmpty>
        </html:select>
        </td>
        </tr>

        </table>
      </td>
      </tr>
      </logic:equal>

      <tr>
      <td align="left" class="table_bg_A5B4E1" nowrap><span class="font_black"><gsmsg:write key="cmn.employer" /></span></td>
      <td class="td_type20_1" cellspacing="0" cellpadding="0">
        <logic:equal name="ipk050Form" property="ipk050AdminFlg" value="true">
        <table class="table_kotei2">

        <tr>
        <td width="35%" class="table_bg_A5B4E1" align="center"><span class="font_black"><gsmsg:write key="cmn.employer.user" /></span></td>
        <td width="20%" align="center">&nbsp;</td>
        <td width="35%" align="left">
        <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup(this.form.groupSelect, 'groupSelect', '<bean:write name="ipk050Form" property="groupSelect" />', '0', 'changeGrp', 'adminSidList', '-1', '0', '0', '0', '1')" type="button">
        <html:select name="ipk050Form" property="groupSelect" styleClass="select01" onchange="changeGrp();">
        <logic:notEmpty name="ipk050Form" property="groupList">
        <html:optionsCollection name="ipk050Form" property="groupList" value="value" label="label" />
        </logic:notEmpty>
        </html:select>
        </td>
        <td width="10%" align="left" valign="bottom">
        <input type="button" onclick="openGroupWindowForIpkanri(this.form.groupSelect, 'groupSelect', '0', 'changeGrp')" class="group_btn2" value="&nbsp;&nbsp;" id="ipk050GroupBtn">
        </td>
        </tr>

        <tr>
        <td>
        <html:select name="ipk050Form" property="selectLeftUser" size="5" multiple="true" styleClass="select01">
        <logic:notEmpty name="ipk050Form" property="leftUserList">
        <html:optionsCollection name="ipk050Form" property="leftUserList" value="value" label="label" />
        </logic:notEmpty>
        <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
        </html:select>
        </td>
      <td align="center">
      <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush2('iadAddList');"><br><br>
      <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush2('iadDelete');">

      </td>
      <td align="center">
      <html:select name="ipk050Form" property="selectRightUser" size="5" multiple="true" styleClass="select01">
        <logic:notEmpty name="ipk050Form" property="rightUserList">
        <html:optionsCollection name="ipk050Form" property="rightUserList" value="value" label="label" filter="false"/>
        </logic:notEmpty>
        <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
        </html:select>
        </td>
      </tr>
      </table>
        </logic:equal>

        <logic:equal name="ipk050Form" property="ipk050AdminFlg" value="false">
        <logic:notEmpty name="ipk050Form" property="leftUserList">
        <logic:iterate id="labelValueBean" name="ipk050Form" property="leftUserList">
        <bean:write name="labelValueBean" property="label"/><br>
        </logic:iterate>
        </logic:notEmpty>
        </logic:equal>
    </td>
    </tr>

       <tr class="table_bg_7D91BD">
    <td align="right" colspan="2">
      <logic:equal name="ipk050Form" property="ipk050AdminFlg" value="true">
    <logic:notEmpty name="ipk050Form" property="iadSid">
      <input type="button" name="delete" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush2('ipadDelete');">
      </logic:notEmpty>
    <logic:empty name="ipk050Form" property="iadSid">
    <input type="button" name="add" value="<gsmsg:write key="cmn.entry" />" class="btn_base1" onClick="buttonPush2('iadAdd');">
    </logic:empty>
      <logic:notEmpty name="ipk050Form" property="iadSid" >
      <input type="button" name="edit" value="<gsmsg:write key="cmn.edit" />" class="btn_edit_n1" onClick="buttonPush2('iadEdit');">
      </logic:notEmpty>
      </logic:equal>
    <input type="button" name="cancel" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush2('ipReturn');" class="btn_back_n1"> &nbsp;
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