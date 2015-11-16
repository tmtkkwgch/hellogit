<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<% String maxLengthMail = String.valueOf(jp.groupsession.v2.usr.GSConstUser.MAX_LENGTH_MAIL); %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<script type="text/javascript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script type="text/javascript" src="../common/js/jquery.selection-min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../anpi/js/anp150.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>


<title>[GroupSession] <gsmsg:write key="cmn.admin.setting.menu" /></title>
</head>

<body class="body_03">
<html:form action="/anpi/anp150">
<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />

<logic:notEmpty name="anp150Form" property="anp150TargetList" scope="request">
    <logic:iterate id="ulBean" name="anp150Form" property="anp150TargetList" scope="request">
    <input type="hidden" name="anp150TargetList" value="<bean:write name="ulBean" />">
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
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="anp.plugin"/> <gsmsg:write key="anp.anp150.01"/> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('anp150excute');">
      <input type="button" value="<gsmsg:write key="cmn.back.admin.setting" />" class="btn_back_n3" onClick="buttonPush('anp150back');">
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
        <span class="text_base">
        <html:radio styleId="anp150Obj_0" name="anp150Form" property="anp150TargetKbn" value="<%= String.valueOf(jp.groupsession.v2.anp.anp150.Anp150Form.TAISYO_ALL) %>" onclick="changeUrlKbn();" /><label for="anp150Obj_0"><gsmsg:write key="cmn.alluser" /></label>&nbsp;&nbsp;
        <html:radio styleId="anp150Obj_1" name="anp150Form" property="anp150TargetKbn" value="<%= String.valueOf(jp.groupsession.v2.anp.anp150.Anp150Form.TAISYO_SELECT) %>" onclick="changeUrlKbn();" /><label for="anp150Obj_1"><gsmsg:write key="anp.anp150.02"/></label>
        </span>
      <br>
      <html:checkbox name="anp150Form" property="anp150PassKbn" value="1" styleId="anp150PassKbn" /><span class="text_base2"><label for="anp150PassKbn"><gsmsg:write key="sml.sml180.07" /></label></span>
      </td>
      </tr>
      </table>

      <div id="selectTarget" style="padding-top:10px;">
        <table width="0%" border="0">
        <tr>
        <td width="40%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="anp.anp150.02"/></span></td>
        <td width="20%" align="center">&nbsp;</td>

        <td width="40%" align="left">
          <logic:notEqual name="anp150Form" property="anp150SelectGroupSid" value="-9">
          <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup(this.form.anp150SelectGroupSid, 'anp150SelectGroupSid', '<bean:write name="anp150Form" property="anp150SelectGroupSid" />', '1   ', 'anp150group', 'anp150TargetList', '-1', '1')" type="button"><br>
          </logic:notEqual>

          <!-- グループコンボボックス -->
          <logic:notEmpty name="anp150Form" property="anp150GroupLabel" scope="request">
          <html:select property="anp150SelectGroupSid" onchange="buttonPush('anp080group');" styleClass="select05">

          <logic:notEmpty name="anp150Form" property="anp150GroupLabel" scope="request">
          <logic:iterate id="gpBean" name="anp150Form" property="anp150GroupLabel" scope="request">

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
          <span class="text_base8">
          <input type="button" onclick="openGroupWindow(this.form.anp150SelectGroupSid, 'anp150SelectGroupSid', '1', 'anp080group')" class="group_btn2" value="&nbsp;" id="groupBtn">
          <input type="checkbox" value="1" id="select_user" onclick="selectUsersList(this, 'anp150SelectNoTargetSid');" />
          <label for="select_user"><gsmsg:write key="cmn.select" /></label></span>
          </logic:notEmpty>
        </td>
        </tr>

        <tr>
        <!-- 管理者ユーザリスト -->
        <td align="center">
          <select size="10" multiple name="anp150SelectTargetSid" class="select01">
          <logic:notEmpty name="anp150Form" property="anp150TargetLabel" scope="request">
          <logic:iterate id="laveValueBean" name="anp150Form" property="anp150TargetLabel" scope="request">
            <option value="<bean:write name="laveValueBean" property="value"/>"><bean:write name="laveValueBean" property="label"/></option>
          </logic:iterate>
          </logic:notEmpty>
          <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
          </select>
        </td>

        <td align="center">
          <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('anp150TargetAdd');"><br><br>
          <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('anp150TargetDel');">
        </td>

        <!-- グループ所属ユーザリスト -->
        <td valign="top" rowspan="3">
          <select size="10" multiple name="anp150SelectNoTargetSid" class="select01">
          <logic:notEmpty name="anp150Form" property="anp150NoTargetLabel" scope="request">
          <logic:iterate id="laveValueBean" name="anp150Form" property="anp150NoTargetLabel" scope="request">
            <option value="<bean:write name="laveValueBean" property="value" scope="page"/>"><bean:write name="laveValueBean" property="label" scope="page"/></option>
          </logic:iterate>
          </logic:notEmpty>
          <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
          </select>
        </td>
        </tr>
        </table>
    </div>
    </td>
    </tr>

<!-- メール転送設定 -->
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="anp.anp150.03"/></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <table>
      <tr>
      <td width="100%" align="left"><span class="text_base"><gsmsg:write key="anp.anp150.04"/><br></span>&nbsp;</td>
      </tr>

      <tr>
      <th width="100%" align="left" nowrap><span class="text_base"><gsmsg:write key="anp.anp150.05"/></span></th>
      </tr>
      <tr>
      <td align="left" width="100%">
        <logic:notEmpty name="anp150Form" property="anp150MailLabel">
        <html:select name="anp150Form" property="anp150SelectMail" onchange="changeEnableDisable();">
          <html:optionsCollection name="anp150Form" property="anp150MailLabel" value="value" label="label" />
        </html:select>
        </logic:notEmpty>
      <html:text name="anp150Form" maxlength="50" property="anp150OtherMail" styleClass="text_base" style="width:337px;" /><br>
      </td>
      </tr>

      </table>
    </td>
    </tr>

    <!-- 上書き -->
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.overwrite" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <html:checkbox name="anp150Form" property="anp150UpdateFlg" value="1" styleId="updateKbn" /><span class="text_base2"><label for="updateKbn"><gsmsg:write key="anp.anp150.06"/></label></span>
    </td>
    </tr>

    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellpadding="5" border="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('anp150excute');">
      <input type="button" value="<gsmsg:write key="cmn.back.admin.setting" />" class="btn_back_n3" onClick="buttonPush('anp150back');">
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