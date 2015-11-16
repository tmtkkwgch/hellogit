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
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../ipkanri/css/ip.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<title>[GroupSession] <gsmsg:write key="ipk.16" /></title>
</head>

<body class="body_03" onload="showOrHide();">
<html:form action="/ipkanri/ipk060">
<html:hidden property="cmd" />
<html:hidden property="netSid" />
<html:hidden property="binSid" />
<html:hidden property="sortKey" />
<html:hidden property="orderKey" />
<html:hidden property="usedKbn" />
<html:hidden property="iadLimit" />
<html:hidden property="deleteAllCheck" />
<logic:notEmpty name="ipk060Form" property="deleteCheck">
<logic:iterate id="param" name="ipk060Form" property="deleteCheck">
  <input type="hidden" name="deleteCheck" value="<bean:write name="param" />">
</logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!-- BODY -->
<table width="100%">
<tr>
<td width="100%" align="center" cellpadding="5" cellspacing="0">
  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">
    <table class="table_padding" cellpadding="0">

    <tr>
    <td width="0%"><img src="../ipkanri/images/header_ipkanri_icon_01.gif" border="0" alt="<gsmsg:write key="cmn.ipkanri" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.ipkanri" /></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="ipk.16" /> ]</td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.ipkanri" />"></td>
    </tr>

    </table>
  </td>
  </tr>
  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">

    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" name="btn_add" class="btn_csv_n1" value="<gsmsg:write key="cmn.import" />" onClick="buttonPush2('iadImp');">
    <input type="button" name="btn_back" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush2('ipk060Return');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>

    </table>
  </td>
  </tr>
  <tr>
  <td>
  <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
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
      <td><img src="../ipkanri/images/ipkanri_icon.gif" width="30" height="30" border="0" class="header_icon" ></td>
      <td nowrap>&nbsp;<span class="font_white"><gsmsg:write key="ipk.4" /></span></td>
      </tr>
      </table>
    </td>
    <td align="left" width="80%" class="td_type20_1">
      <table width="100%">

      <tr>
      <td nowrap>
      <span class="font_black2">
      <bean:write name="ipk060Form" property="netName" /></span></td>
      <td align="right" width="100%" class="font_black3" nowrap><gsmsg:write key="ipk.5" />：<bean:write name="ipk060Form" property="iadCount" />
     （<gsmsg:write key="cmn.in.use" />: <bean:write name="ipk060Form" property="iadCountUse" />
      <gsmsg:write key="cmn.unused" />: <bean:write name="ipk060Form" property="iadCountNotUse" />) &nbsp;
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
    <table align="center" class="table_padding" cellpadding="0">
    <tr align="center" border="0">
    <logic:equal name="ipk060Form" property="tempExist" value="true">
    <td align="left" class="td_border" colspan="4">
    </logic:equal>
    <logic:equal name="ipk060Form" property="tempExist" value="false">
    <td align="left" class="td_border" colspan="3">
    </logic:equal>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">

      <tr class="table_bg_7D91BD">
      <td align="left" nowrap border="0" width="0%">
      <img src="../ipkanri/images/ipkanri_icon.gif" width="30" height="30" border="0" class="header_icon">
      </td>
      <td align="left" width="0%" nowrap>
      <span class="font_white">&nbsp;<bean:write name="ipk060Form" property="netName" /></span>
      </td>
      <td align="right" width="90%" nowrap>
      <span class="font_white2">　<gsmsg:write key="ipk.5" />：<bean:write name="ipk060Form" property="iadCount" />
（<gsmsg:write key="cmn.in.use" />: <bean:write name="ipk060Form" property="iadCountUse" />
 <gsmsg:write key="cmn.unused" />: <bean:write name="ipk060Form" property="iadCountNotUse" />) &nbsp; </span></td>
      <td align="left" width="10%" nowrap>
      <input type="button" value="<gsmsg:write key="cmn.hide" />" class="btn_base1s" onClick="hideText()">&nbsp;
      </td>
      </tr>

      </table>
    </td>
    </tr>

    <logic:equal name="ipk060Form" property="tempExist" value="true">
    <tr>
    <td class="td_type3" width="0%" nowrap><gsmsg:write key="ipk.2" /></td>
    <td class="td_type3" width="0%" nowrap><gsmsg:write key="ipk.3" /></td>
    <td class="td_type3" width="100%" nowrap colspan="2"><gsmsg:write key="cmn.comment" /></td>
    </tr>

    <logic:notEmpty name="ipk060Form" property="koukaiBinFileInfList">
    <logic:notEmpty name="ipk060Form" property="hikoukaiBinFileInfList">
    <tr>
    <td class="td_type20_1" align="center" rowspan="3">
    <span class="font_000000"><bean:write name="ipk060Form" property="netNetad" /></span>
    </td>
    <td class="td_type20_1" align="center" rowspan="3">
    <span class="font_000000"><bean:write name="ipk060Form" property="netSabnet" /></span>
    </td>
    <td align="left" class="td_type20_1" valign="top" colspan="2">
    <span class="font_000000"><bean:write name="ipk060Form" property="netMsg" filter="false" /></span>
    </td>
    </tr>

    <tr>
    <td class="td_type3" width="20%" nowrap><gsmsg:write key="cmn.attach.file" />(<gsmsg:write key="cmn.public" />)</td>
    <td width="80%" class="td_type20_1">
    <logic:iterate id="koukaiFileMdl" name="ipk060Form" property="koukaiBinFileInfList">
    <img src="../common/images/file_icon.gif" alt="<gsmsg:write key="cmn.file" />">
    <a href="javascript:fileLinkClick(<bean:write name="koukaiFileMdl" property="binSid" />);">
    <span class="text_link"><bean:write name="koukaiFileMdl" property="binFileName" /></a><br>
    </logic:iterate>
    </td>
    </tr>

    <tr>
    <td class="td_type3" width="20%" nowrap><gsmsg:write key="cmn.attach.file" />(<gsmsg:write key="cmn.private" />)</td>
    <td width="80%" class="td_type20_1">
    <logic:iterate id="hikoukaiFileMdl" name="ipk060Form" property="hikoukaiBinFileInfList">
    <img src="../common/images/file_icon.gif" alt="<gsmsg:write key="cmn.file" />">
    <a href="javascript:fileLinkClick(<bean:write name="hikoukaiFileMdl" property="binSid" />);">
    <span class="text_link"><bean:write name="hikoukaiFileMdl" property="binFileName" /></span></a></br>
    </logic:iterate>
    </td>
    </tr>
    </logic:notEmpty>
    </logic:notEmpty>

    <logic:empty name="ipk060Form" property="koukaiBinFileInfList">
    <tr>
    <td class="td_type20_1" align="center" rowspan="2">
    <span class="font_000000"><bean:write name="ipk060Form" property="netNetad" /></span>
    </td>
    <td class="td_type20_1" align="center" rowspan="2">
    <span class="font_000000"><bean:write name="ipk060Form" property="netSabnet" /></span>
    </td>
    <td align="left" class="td_type20_1" valign="top" colspan="2">
    <span class="font_000000"><bean:write name="ipk060Form" property="netMsg" filter="false" /></span>
    </td>
    </tr>

    <tr>
    <td class="td_type3" width="20%" nowrap><gsmsg:write key="cmn.attach.file" />(<gsmsg:write key="cmn.private" />)</td>
    <td width="80%" class="td_type20_1">
    <logic:iterate id="hikoukaiFileMdl" name="ipk060Form" property="hikoukaiBinFileInfList">
    <img src="../common/images/file_icon.gif" alt="<gsmsg:write key="cmn.file" />">
    <a href="javascript:fileLinkClick(<bean:write name="hikoukaiFileMdl" property="binSid" />);">
    <span class="text_link"><bean:write name="hikoukaiFileMdl" property="binFileName" /></span></a></br>
    </logic:iterate>
    </td>
    </tr>
    </logic:empty>

    <logic:empty name="ipk060Form" property="hikoukaiBinFileInfList">
    <tr>
    <td class="td_type20_1" align="center" rowspan="2">
    <span class="font_000000"><bean:write name="ipk060Form" property="netNetad" /></span>
    </td>
    <td class="td_type20_1" align="center" rowspan="2">
    <span class="font_000000"><bean:write name="ipk060Form" property="netSabnet" /></span>
    </td>
    <td align="left" class="td_type20_1" valign="top" colspan="2">
    <span class="font_000000"><bean:write name="ipk060Form" property="netMsg" filter="false" /></span>
    </td>
    </tr>

    <tr>
    <td class="td_type3" width="20%" nowrap><gsmsg:write key="cmn.attach.file" />(<gsmsg:write key="cmn.public" />)</td>
    <td width="80%" class="td_type20_1">
    <logic:iterate id="koukaiFileMdl" name="ipk060Form" property="koukaiBinFileInfList">
    <img src="../common/images/file_icon.gif" alt="<gsmsg:write key="cmn.file" />">
    <a href="javascript:fileLinkClick(<bean:write name="koukaiFileMdl" property="binSid" />);">
    <span class="text_link"><bean:write name="koukaiFileMdl" property="binFileName" /></span></a></br>
    </logic:iterate>
    </td>
    </tr>
    </logic:empty>
    </logic:equal>

    <logic:equal name="ipk060Form" property="tempExist" value="false">
    <tr>
    <td class="td_type3" width="30%" nowrap><gsmsg:write key="ipk.2" /></td>
    <td class="td_type3 width="30%" nowrap"><gsmsg:write key="ipk.3" /></td>
    <td class="td_type3" width="40%" nowrap><gsmsg:write key="cmn.comment" /></td>
    </tr>
    <tr>
    <td class="td_type20_1" align="center">
    <span class="font_000000"><bean:write name="ipk060Form" property="netNetad" /></span>
    </td>
    <td class="td_type20_1" align="center">
    <span class="font_000000"><bean:write name="ipk060Form" property="netSabnet" /></span>
    </td>
    <td align="left" class="td_type20_1" valign="top">
    <span class="font_000000"><bean:write name="ipk060Form" property="netMsg" filter="false" /></span>
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
  <html:errors />
  <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
  </td>
  </tr>

  <tr>
  <td>
    <!-- 取込みファイル -->
    <table cellpadding="5" cellspacing="0" border="0" width="100%" class="table_collapse">

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="30%" nowrap>
    <span class="font_black"><gsmsg:write key="cmn.capture.file" /></span><span class="font_red">※</span>
    </td>
    <td valign="middle" align="left" class="td_type20_1" width="70%" colspan="1" nowrap>
    <input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="buttonPush2('delFile');">
    &nbsp;
    <input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTemp('ipk060Files', 'ipkanri', '1', '0');">
    <br>
    <html:select name="ipk060Form" property="ipk060Files" styleClass="select01" multiple="true" size="1">
    <logic:notEmpty name="ipk060Form" property="ipk060FileLabelList">
    <html:optionsCollection name="ipk060Form" property="ipk060FileLabelList" value="value" label="label" />
    </logic:notEmpty>
    </html:select>&nbsp;&nbsp;
    <span class="text_base">
    <% jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage(); %>
    <% String csvFileMsg = "<a href=\"../ipkanri/ipk060.do?CMD=ipk060_sample&sample=1\">" + gsMsg.getMessage(request, "cmn.capture.csvfile") + "</a>"; %>
    *<gsmsg:write key="cmn.plz.specify2" arg0="<%= csvFileMsg %>" />
    </span>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="30%" nowrap>
    <span class="font_black"><gsmsg:write key="ipk.ipk060.2" /></span><span class="font_red">※</span>
    </td>
    <td valign="middle" align="left" class="td_type20_1" width="70%" colspan="1">
    <html:radio property="importMode" value="0" styleId="import_tuika" /><label for="import_tuika"><gsmsg:write key="cmn.add" /></label>
    <html:radio property="importMode" value="1" styleId="import_uwagaki" /><label for="import_uwagaki"><gsmsg:write key="cmn.overwrite" /></label>
    </td>
    </tr>

    </table>
  </td>
  </tr>

  <tr>
  <td>
    <table width="100%" cellpadding="5" cellspacing="0">

    <tr>
    <td width="100%" align="right">
    <input type="button" name="btn_add" class="btn_csv_n1" value="<gsmsg:write key="cmn.import" />" onClick="buttonPush2('iadImp');">
    <input type="button" name="btn_back" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush2('ipk060Return');">
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