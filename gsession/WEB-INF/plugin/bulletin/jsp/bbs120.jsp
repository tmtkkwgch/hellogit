<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.bulletin" />-<gsmsg:write key="cmn.autodelete.setting" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../bulletin/js/bbs120.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../bulletin/css/bulletin.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

</head>

<body class="body_03" onload="initSetting();">
<html:form action="/bulletin/bbs120">
<input type="hidden" name="CMD" value="">
<html:hidden name="bbs120Form" property="backScreen" />
<html:hidden name="bbs120Form" property="s_key" />
<html:hidden name="bbs120Form" property="bbs010page1" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <!-- タイトル -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.bulletin" /> <gsmsg:write key="cmn.automatic.delete.setting" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('bbs120kakunin');">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('confMenu');"></td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <!-- エラーメッセージ -->
    <logic:messagesPresent message="false">
      <span class="TXT02"><html:errors/></span>
    </logic:messagesPresent>

    <table class="tl0" width="100%" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="15%"><span class="text_bb1"><gsmsg:write key="cmn.autodelete" /></span><span class="text_r2">※</span></td>
    <td class="td_type1">
      <span class="text_base"><gsmsg:write key="bbs.bbs120.1" /></span>

      <logic:notEmpty name="bbs120Form" property="batchTime">
      <br>
      <bean:define id="btime" name="bbs120Form" property="batchTime" type="java.lang.String" />
      <span class="text_r1_2"><gsmsg:write key="bbs.bbs120.2" arg0="<%= btime %>" /></span>
      </logic:notEmpty>

      <br><br>
      <html:radio name="bbs120Form" styleId="bbs120AtdelFlg_01" property="bbs120AtdelFlg" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.AUTO_DELETE_OFF) %>" onclick="changeDisable();" /><label for="bbs120AtdelFlg_01"><span class="text_base7"><gsmsg:write key="cmn.noset" /></span></label>&nbsp;
      <html:radio name="bbs120Form" styleId="bbs120AtdelFlg_02" property="bbs120AtdelFlg" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.AUTO_DELETE_ON) %>" onclick="changeEnable();" /><label for="bbs120AtdelFlg_02"><span class="text_base7"><gsmsg:write key="cmn.automatically.delete" />&nbsp;</span></label>
      <span class="text_base7"><gsmsg:write key="cmn.after.data.head" /></span>
      <!-- 年 -->
      <html:select property="bbs120AtdelYear">
        <html:optionsCollection name="bbs120Form" property="bbs120AtdelYearLabel" value="value" label="label" />
      </html:select>

      <!-- 月 -->
      <html:select property="bbs120AtdelMonth">
        <html:optionsCollection name="bbs120Form" property="bbs120AtdelMonthLabel" value="value" label="label" />
      </html:select>
      <span class="text_base7"><gsmsg:write key="cmn.after.data" /></span>

    </td>
    </tr>
    </table>


    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
    <td width="100%" align="right">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('bbs120kakunin');">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('confMenu');"></td>
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
</body></html:html>