<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<% String maxLengthMemo = String.valueOf(jp.groupsession.v2.cmn.GSConstCommon.MAX_LENGTH_MEMO); %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <bean:write name="cmn131Form" property="cmn131dspTitle" /><gsmsg:write key="cmn.display" /></title>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03" onload="showLengthId($('#inputstr')[0], <%= maxLengthMemo %>, 'inputlength');">
<html:form action="/common/cmn131">

<input type="hidden" name="CMD" value="ok">
<html:hidden property="cmn130cmdMode" />
<html:hidden property="cmn130selectGroupSid" />
<html:hidden property="cmn131initFlg" />
<input type="hidden" name="helpPrm" value="<bean:write name="cmn131Form" property="cmn130cmdMode" />">

<logic:notEmpty name="cmn131Form" property="cmn131userSid" scope="request">
<logic:iterate id="users" name="cmn131Form" property="cmn131userSid" indexId="idx" scope="request">
  <bean:define id="userSid" name="users" type="java.lang.String" />
  <html:hidden property="cmn131userSid" value="<%= userSid %>" />
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="cmn131Form" property="cmn131refUserSid" scope="request">
<logic:iterate id="refUsers" name="cmn131Form" property="cmn131refUserSid" indexId="idx" scope="request">
  <bean:define id="refUserSid" name="refUsers" type="java.lang.String" />
  <html:hidden property="cmn131refUserSid" value="<%= refUserSid %>" />
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
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <bean:write name="cmn131Form" property="cmn131dspTitle" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="40%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="60%">
      <input type="submit" value="OK" class="btn_ok1">
      <logic:equal name="cmn131Form" property="cmn130cmdMode" value="1">
        <input type="button" class="btn_dell_n1" name="dell" value="<gsmsg:write key="cmn.delete" />" onClick="buttonPush('groupDelete');">
      </logic:equal>
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backToList');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <html:errors />
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" class="tl0" border="0" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.cmn130.1" /></span><span class="text_r2">※</span></td>
    <td align="left" class="td_wt" width="100%"><html:text style="width:316px;" maxlength="20" property="cmn131name" styleClass="text_base" /></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.member" /></span></td>
    <td align="left" class="td_wt">
      <table width="0%" border="0">
      <tr>
      <td width="35%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="cmn.member" /></span></td>
      <td width="20%" align="center">&nbsp;</td>
      <td width="35%" align="left">
      <input class="btn_group_n1" onclick="return openAllGroup(this.form.cmn131groupSid, 'cmn131groupSid', '-1', '0', 'changeGroup', 'cmn131userSid', '-1')" value="<gsmsg:write key="cmn.sel.all.group" />"  type="button"><br>
        <html:select property="cmn131groupSid" styleClass="select01" onchange="buttonPush('changeGroup');">
          <html:optionsCollection name="cmn131Form" property="cmn131GpLabelList" value="value" label="label" />
          <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
        </html:select>
      </td>
      <td width="10%" align="left" valign="bottom">
        <input type="button" onclick="openGroupWindow(this.form.cmn131groupSid, 'cmn131groupSid', '0', 'changeGrp')" class="group_btn2" value="&nbsp;&nbsp;" id="cmn131GroupBtn">
      </td>
      </tr>

      <tr>
      <td>
        <html:select property="cmn131selectUserSid" size="6" multiple="true" styleClass="select01">
          <html:optionsCollection name="cmn131Form" property="cmn131MbLabelList" value="value" label="label" />
          <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
        </html:select>
      </td>
      <td align="center">
        <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('addMnb');"><br><br>
        <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('deleteMnb');">
        <br>
      </td>
      <td align="center">
        <html:select property="cmn131addUserSid" size="6" multiple="true" styleClass="select01">
          <html:optionsCollection name="cmn131Form" property="cmn131AdLabelList" value="value" label="label" />
          <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
        </html:select>
      </td>
      </tr>
      </table>

    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cir.11" /></span></td>
    <td align="left" class="td_wt" width="100%"><textarea class="text_base" name="cmn131memo" style="width:512px;" rows="10" onkeyup="showLengthStr(value, <%= maxLengthMemo %>, 'inputlength');" id="inputstr"><bean:write name="cmn131Form" property="cmn131memo" /></textarea>
    <br>
    <span class="font_string_count"><gsmsg:write key="wml.js.15" /></span><span id="inputlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthMemo %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
    </tr>


    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.share" /></span></td>
    <td align="left" class="td_wt">
      <table width="0%" border="0">
      <tr>
      <!-- 追加 -->
      <td width="35%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="cmn.add" /></span></td>
      <!-- ギャップ -->
      <td width="20%" align="center">&nbsp;</td>
      <!-- 全グループボタンとセレクトボックス -->
      <td width="35%" align="left">
      <input class="btn_group_n1" onclick="return openAllGroup(this.form.cmn131refGroupSid, 'cmn131refGroupSid', '-1', '0', 'changeGroup', 'cmn131refUserSid', '-1', '1')" value="<gsmsg:write key="cmn.sel.all.group" />"  type="button"><br>
        <html:select property="cmn131refGroupSid" styleClass="select01" onchange="buttonPush('changeGroup');">
          <html:optionsCollection name="cmn131Form" property="cmn131refGpLabelList" value="value" label="label" />
          <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
        </html:select>
      </td>
      <!-- グループアイコン -->
      <td width="10%" align="left" valign="bottom">
        <input type="button" onclick="openGroupWindow(this.form.cmn131refGroupSid, 'cmn131refGroupSid', '0', 'changeGrp')" class="group_btn2" value="&nbsp;&nbsp;" id="">
      </td>
      </tr>

      <tr>
      <!-- 追加参照権限一覧 -->
      <td>
        <html:select property="cmn131refSelectUserSid" size="6" multiple="true" styleClass="select01">
          <html:optionsCollection name="cmn131Form" property="cmn131refMbLabelList" value="value" label="label" />
          <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
        </html:select>
      </td>
      <!-- 追加・削除ボタン -->
      <td align="center">
        <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('addRefMnb');"><br><br>
        <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('deleteRefMnb');">
        <br>
      </td>
      <!-- グループ・ユーザ一覧 -->
      <td align="center">
        <html:select property="cmn131refAddUserSid" size="6" multiple="true" styleClass="select01">
          <html:optionsCollection name="cmn131Form" property="cmn131refAdLabelList" value="value" label="label" />
          <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
        </html:select>
      </td>
      </tr>
      </table>
    </td>
    </tr>

    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellpadding="0" border="0" width="100%">
    <tr>
    <td align="right" valign="middle">
      <input type="submit" value="OK" class="btn_ok1">
      <logic:equal name="cmn131Form" property="cmn130cmdMode" value="1">
        <input type="button" class="btn_dell_n1" name="dell" value="<gsmsg:write key="cmn.delete" />" onClick="buttonPush('groupDelete');">
      </logic:equal>
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backToList');">
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