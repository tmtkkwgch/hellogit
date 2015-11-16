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
<title>[Group Session] <gsmsg:write key="sml.sml400kn.01" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<theme:css filename="theme.css"/>
<link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel="stylesheet" href="../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%=GSConst.VERSION_PARAM%>'></script>
<script language="JavaScript" src="../smail/js/sml400kn.js?<%=GSConst.VERSION_PARAM%>"></script>
</head>

<body class="body_03" onload="setShowHide();">
<html:form action="/smail/sml400kn">
<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="smlAccountSid" />
<html:hidden property="smlViewAccount" />
<html:hidden property="sml010ProcMode" />
<html:hidden property="sml010Sort_key" />
<html:hidden property="sml010Order_key" />
<html:hidden property="sml010PageNum" />
<html:hidden property="sml010SelectedSid" />
<html:hidden property="sml400MaxDspStype" />
<html:hidden property="sml400ReloadTimeStype" />
<html:hidden property="sml400PhotoDspStype" />
<html:hidden property="sml400AttachImgDspStype" />
<html:hidden property="sml400MaxDsp" />
<html:hidden property="sml400ReloadTime" />
<html:hidden property="sml400PhotoDsp" />
<html:hidden property="sml400AttachImgDsp" />

<logic:notEmpty name="sml400knForm" property="sml010DelSid" scope="request">
  <logic:iterate id="del" name="sml400knForm" property="sml010DelSid" scope="request">
    <input type="hidden" name="sml010DelSid" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.display.settings.kn" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt="<gsmsg:write key='cmn.function.btn' />"></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" name="btn_add" class="btn_base1" value="<gsmsg:write key="cmn.final" />" onClick="buttonPush('decision');">
          <input type="button" name="btn_facilities_mnt" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backInput');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt="<gsmsg:write key='cmn.function.btn' />"></td>
      </tr>
    </table>

  </td>
  </tr>

  <logic:messagesPresent message="false">
    <tr>
      <td align="left"><span class="TXT02"><html:errors/></span></td>
    </tr>
  </logic:messagesPresent>

  <tr>
  <td>
    <br>

    <table id="normal_settings" class="tl0" width="100%" cellpadding="5">

    <!-- 表示件数 -->
    <tr>
    <td class="table_bg_A5B4E1" width="25%" nowrap id="smlMaxDspArea1"><span class="text_bb1"><gsmsg:write key="cmn.number.display" /></span></td>
    <!-- 表示件数 設定種別 -->
    <td class="td_wt">
      <span class="text_base">
      <logic:equal name="sml400knForm" property="sml400MaxDspStype" value="1">
        <gsmsg:write key="cmn.set.the.admin" />
      </logic:equal>
      <logic:equal name="sml400knForm" property="sml400MaxDspStype" value="0">
        <gsmsg:write key="cmn.set.eachuser" />
      </logic:equal>
      </span>
    </td>
    </tr>
    <tr>
    <!-- 表示件数 選択 -->
    <td valign="middle" align="left" class="td_wt" id="smlMaxDspArea2" nowrap>
      <span class="text_base">
      <bean:write name="sml400knForm" property="sml400knMaxDsp" />
      </span>
    </td>
    </tr>

    <!-- 自動リロード時間 -->
    <tr>
    <td class="table_bg_A5B4E1" width="25%" nowrap id="smlReloadTimeArea1"><span class="text_bb1"><gsmsg:write key="cmn.auto.reload.time" /></span></td>
    <!-- 自動リロード時間 設定種別 -->
    <td class="td_wt">
      <span class="text_base">
      <logic:equal name="sml400knForm" property="sml400ReloadTimeStype" value="1">
        <gsmsg:write key="cmn.set.the.admin" />
      </logic:equal>
      <logic:equal name="sml400knForm" property="sml400ReloadTimeStype" value="0">
        <gsmsg:write key="cmn.set.eachuser" />
      </logic:equal>
      </span>
    </td>
    </tr>
    <tr>
    <!-- 自動リロード時間 選択 -->
    <td valign="middle" align="left" class="td_wt" id="smlReloadTimeArea2" nowrap>
      <span class="text_base">
      <bean:write name="sml400knForm" property="sml400knReloadTime" />
      </span>
    </td>
    </tr>

    <!-- 写真表示設定 -->
    <tr>
    <td class="table_bg_A5B4E1" width="25%" nowrap id="smlPhotoDspArea1"><span class="text_bb1"><gsmsg:write key="sml.sml040.05" /></span></td>
    <!-- 写真表示設定 設定種別 -->
    <td class="td_wt">
      <span class="text_base">
      <logic:equal name="sml400knForm" property="sml400PhotoDspStype" value="1">
        <gsmsg:write key="cmn.set.the.admin" />
      </logic:equal>
      <logic:equal name="sml400knForm" property="sml400PhotoDspStype" value="0">
        <gsmsg:write key="cmn.set.eachuser" />
      </logic:equal>
      </span>
    </td>
    </tr>
    <tr>
    <!-- 写真表示設定 選択 -->
    <td valign="middle" align="left" class="td_wt" id="smlPhotoDspArea2" nowrap>
      <span class="text_base">
      <bean:write name="sml400knForm" property="sml400knPhotoDsp" />
      </span>
    </td>
    </tr>

    <!-- 添付画像表示設定 -->
    <tr>
    <td class="table_bg_A5B4E1" width="25%" nowrap id="smlAttachImgDspArea1"><span class="text_bb1"><gsmsg:write key="sml.sml040.07" /></span></td>
    <!-- 添付画像表示設定 設定種別 -->
    <td class="td_wt">
      <span class="text_base">
      <logic:equal name="sml400knForm" property="sml400AttachImgDspStype" value="1">
        <gsmsg:write key="cmn.set.the.admin" />
      </logic:equal>
      <logic:equal name="sml400knForm" property="sml400AttachImgDspStype" value="0">
        <gsmsg:write key="cmn.set.eachuser" />
      </logic:equal>
      </span>
    </td>
    </tr>
    <tr>
    <!-- 添付画像表示設定 選択 -->
    <td valign="middle" align="left" class="td_wt" id="smlAttachImgDspArea2" nowrap>
      <span class="text_base">
      <bean:write name="sml400knForm" property="sml400knAttachImgDsp" />
      </span>
    </td>
    </tr>

    </table>

  </td>
  </tr>

  <tr>
  <td><br>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="100%" align="right">
          <input type="button" name="btn_add" class="btn_base1" value="<gsmsg:write key="cmn.final" />" onClick="buttonPush('decision');">
          <input type="button" name="btn_facilities_mnt" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backInput');">
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