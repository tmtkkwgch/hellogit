<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<% String maxLengthBiko = String.valueOf(jp.groupsession.v2.ip.IpkConst.MAX_LENGTH_SPECM_BIKO); %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../ipkanri/js/ipkanri.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../ipkanri/css/ip.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[GroupSession] <gsmsg:write key="ipk.ipk100.1" /></title>
</head>

<body class="body_03" onload="scr(<bean:write name='ipk100Form' property='ipk100scroll' />);showLengthId($('#inputstr')[0], <%= maxLengthBiko %>, 'inputlength');">
<html:form action="/ipkanri/ipk100">
<html:hidden property="cmd" />
<html:hidden property="backScreen" />
<html:hidden property="editMode" />
<html:hidden property="specKbn" />
<html:hidden property="ismSid" />
<html:hidden property="ipk100scroll" />
<html:hidden property="existFlg" />
<html:hidden property="ipk100svSpecLevel" />
<html:hidden property="ipk100helpMode" />
<input type="hidden" name="helpPrm" value="<bean:write name='ipk100Form' property='ipk100helpMode' />">
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%" align="center">

  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%">
    <img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.ipkanri" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="ipk.10" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.ipkanri" />" /></td>
    </tr>
    </table>
  </td>
  </tr>

  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="./images/header_glay_1.gif" border="0" alt="<gsmsg:write key="cmn.ipkanri" />"></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" name="ok" value="OK" onClick="ipk100ok();" class="btn_ok1">
    <logic:equal name="ipk100Form" property="editMode" value="2">
    <input type="button" name="delete" value="<gsmsg:write key="cmn.delete" />" onClick="buttonPush2('ipk100Delete');" class="btn_dell_n1">
    </logic:equal>
    <input type="button" name="cancel" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush2('ipk100Return');" class="btn_back_n1">
  </td>
    <td width="0%"><img src="./images/header_glay_3.gif" border="0" alt="<gsmsg:write key="cmn.ipkanri" />"></td>
    </tr>
    </table>
  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="10" height="10" border="0" alt="">
    <span class="text_error"><html:errors/></span>
  </td>
  </tr>

  <tr>
  <td>
    <table class="table_padding2" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <th align="left" class="td_type32" width="100%" colspan="2"><span class="font_white">
      <logic:equal name="ipk100Form" property="specKbn" value="1"><gsmsg:write key="ipk.ipk100.4" /></logic:equal>
      <logic:equal name="ipk100Form" property="specKbn" value="2"><gsmsg:write key="ipk.ipk100.2" /></logic:equal>
      <logic:equal name="ipk100Form" property="specKbn" value="3"><gsmsg:write key="ipk.ipk100.3" /></logic:equal>
    </span></th>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="font_black2"><gsmsg:write key="cmn.name4" /></span><span class="font_red2">※</span></td>
    <td align="left" class="td_type20_1" width="80%">
    <input type="text" name="ipk100name" maxlength="50" style="width:393px;" value="<bean:write name='ipk100Form' property='ipk100name' />">
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="font_black2"><gsmsg:write key="cmn.sort" /></span><span class="font_red2">※</span></td>
    <td>
      <div class="scroll" id="Layer1">
      <table class="table_scr">
      <logic:notEmpty name="ipk100Form" property="ipk100specMstModelList">
      <tr>
      <logic:equal name="ipk100Form" property="ipk100specLevel" value="0">
      <td nowrap class="td_line5" id="0">
        <span class="radio_position">
        <input type="radio" name="ipk100specLevel" value="0" onclick="dispDescription('0');" checked>
        </span>
      </logic:equal>

      <logic:notEqual name="ipk100Form" property="ipk100specLevel" value="0">
      <td nowrap class="td_line4" id="0">
        <span class="radio_position">
        <input type="radio" name="ipk100specLevel" value="0" onclick="dispDescription('0');">
        </span>
      </logic:notEqual>
      </td>
      </tr>

      <logic:iterate id="specModel" name="ipk100Form" property="ipk100specMstModelList" indexId="idx">
      <bean:define id="radioValue" name="specModel" property="ismSid" type="java.lang.Integer" />
      <tr>

      <logic:equal name="ipk100Form" property="ipk100specLevel" value="<%= String.valueOf(radioValue.intValue()) %>">
      <td nowrap class="td_line2" id="<bean:write name='specModel' property='ismSid' />">
        <span class="radio_position">
        <input type="radio" name="ipk100specLevel" value="<%= String.valueOf(radioValue.intValue()) %>" onclick="dispDescription('<bean:write name='specModel' property='ismSid' />');" checked>
        </span>
      </logic:equal>

      <logic:notEqual name="ipk100Form" property="ipk100specLevel" value="<%= String.valueOf(radioValue.intValue()) %>">
      <td nowrap class="td_line3" id="<bean:write name='specModel' property='ismSid' />">
        <span class="radio_position">
        <input type="radio" name="ipk100specLevel" value="<%= String.valueOf(radioValue.intValue()) %>" onclick="dispDescription('<bean:write name="specModel" property="ismSid" />');" >
        </span>
      </logic:notEqual>

      <span class="name_position"><bean:write name="specModel" property="ipk100name" filter="false"/></span>
      </td>
      </tr>
      </logic:iterate>
      </logic:notEmpty>

      </table>
      </div>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="font_black2"><gsmsg:write key="cmn.memo" /></span></td>
    <td align="left" class="td_type20_1">
    <textarea name="ipk100biko" style="width:593px;" rows="5" class="textarea" onkeyup="showLengthStr(value, <%= maxLengthBiko %>, 'inputlength');" id="inputstr"><bean:write name="ipk100Form" property="ipk100biko" /></textarea>
    <br>
    <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthBiko %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
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
    <table width="100%" cellspacing="0" cellpadding="0" align="center">
    <tr>
    <td colspan="2" align="right">
    <input type="button" name="ok" value="OK" onClick="ipk100ok();" class="btn_ok1">
    <logic:equal name="ipk100Form" property="editMode" value="2">
    <input type="button" name="delete" value="<gsmsg:write key="cmn.delete" />" onClick="buttonPush2('ipk100Delete');" class="btn_dell_n1">
    </logic:equal>
    <input type="button" name="cancel" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush2('ipk100Return');" class="btn_back_n1">
    </td>
    </tr>
    </table>
  </td>
  </tr>
    <tr>
  <td>
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