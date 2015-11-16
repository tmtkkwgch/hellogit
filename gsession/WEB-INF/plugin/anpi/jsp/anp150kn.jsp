<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<script type="text/javascript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script type="text/javascript" src="../common/js/jquery.selection-min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>


<title>[GroupSession] <gsmsg:write key="cmn.admin.setting.menu" /></title>
</head>

<body class="body_03">
<html:form action="/anpi/anp150kn">
<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />

<html:hidden property="anp150PassKbn" />
<html:hidden property="anp150TargetKbn" />
<html:hidden property="anp150SelectGroupSid" />

<html:hidden property="anp150SelectMail" />
<html:hidden property="anp150OtherMail" />
<html:hidden property="anp150UpdateFlg" />

<logic:notEmpty name="anp150knForm" property="anp150TargetList" scope="request">
    <logic:iterate id="ulBean" name="anp150knForm" property="anp150TargetList" scope="request">
      <bean:define id="userSid" name="ulBean" type="java.lang.String" />
    <html:hidden property="anp150TargetList" value="<%= userSid %>" />
    </logic:iterate>
</logic:notEmpty>

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
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt=""></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="anp.plugin"/> <gsmsg:write key="anp.anp150kn.01"/> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('anp150knexcute');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('anp150knback');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>
    <logic:messagesPresent message="false">
      <table width="100%">
      <tr>
        <td align="left"><span class="TXT02"><html:errors/></span></td>
      </tr>
      </table>
    </logic:messagesPresent>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" class="tl0" border="0" cellpadding="5">


<!-- 対象 -->
    <tr>
    <td class="table_bg_A5B4E1" width="20%"><span class="text_bb1"><gsmsg:write key="cmn.target" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td class="td_type1" width="80%">
      <table width="100%" border="0">
      <tr>
      <td width="100%">
      <span class="text_r1"><gsmsg:write key="anp.anp150kn.02"/></span>
        <logic:equal name="anp150knForm" property="anp150TargetKbn" value="<%= String.valueOf(jp.groupsession.v2.anp.anp150.Anp150Form.TAISYO_ALL) %>">
        <br>
        <span class="text_base"><gsmsg:write key="cmn.alluser" /></span>
        </logic:equal>

        <logic:notEqual name="anp150knForm" property="anp150TargetKbn" value="<%= String.valueOf(jp.groupsession.v2.anp.anp150.Anp150Form.TAISYO_ALL) %>">
        <logic:notEmpty name="anp150knForm" property="anp150knOkUsrList">
          <logic:iterate id="usrName" name="anp150knForm" property="anp150knOkUsrList">
          <br>
          <span class="text_base"><bean:write name="usrName" /></span>
          </logic:iterate>
        </logic:notEmpty>
        </logic:notEqual>
        <br>
        <span class="text_base">(<bean:write name="anp150knForm" property="anp150knOkUsrNum" />&nbsp;<gsmsg:write key="cmn.user" />)</span>
        <logic:notEmpty name="anp150knForm" property="anp150knCutUsrList">
          <br>
          <br>
          <span class="text_r1"><gsmsg:write key="anp.anp150kn.03"/></span>
          <logic:iterate id="cutUsrName" name="anp150knForm" property="anp150knCutUsrList">
            <br>
            <span class="text_base"><bean:write name="cutUsrName" /></span>
          </logic:iterate>
          <br>
          <span class="text_base">(<bean:write name="anp150knForm" property="anp150knCutUsrNum" />&nbsp;<gsmsg:write key="cmn.user" />)</span>
        </logic:notEmpty>

      </td>
      </tr>
      </table>
      <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    </td>
    </tr>

<!-- メール転送設定 -->
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="anp.anp150.03"/></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <table>

      <tr>
      <th width="100%" align="left" nowrap><span class="text_base"><gsmsg:write key="anp.anp150.05"/></span></th>
      </tr>
      <tr>
      <td align="left" width="100%">
      <logic:equal name="anp150knForm" property="anp150SelectMail" value="0">
        <span class="text_base"><gsmsg:write key="anp.anp150.07"/>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="anp150knForm" property="anp150OtherMail" /></span>
      </logic:equal>
      <logic:equal name="anp150knForm" property="anp150SelectMail" value="1">
      <span class="text_base"><gsmsg:write key="cmn.mailaddress1" /></span>
      </logic:equal>
      <logic:equal name="anp150knForm" property="anp150SelectMail" value="2">
      <span class="text_base"><gsmsg:write key="cmn.mailaddress2" /></span>
      </logic:equal>
      <logic:equal name="anp150knForm" property="anp150SelectMail" value="3">
      <span class="text_base"><gsmsg:write key="cmn.mailaddress3" /></span>
      </logic:equal>
      </td>
      </tr>

      </table>
    </td>
    </tr>

    <!-- 上書き -->
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.overwrite" /></span></td>
    <td align="left" class="td_wt" width="100%">
    <logic:equal name="anp150knForm" property="anp150UpdateFlg" value="1">
    <span class="text_base"><gsmsg:write key="anp.anp150kn.04"/></span>
    </logic:equal>
    <logic:notEqual name="anp150knForm" property="anp150UpdateFlg" value="1">
    <span class="text_base"><gsmsg:write key="anp.anp150kn.05"/></span>
    </logic:notEqual>
    </td>
    </tr>

    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellpadding="5" border="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('anp150knexcute');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('anp150knback');">
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