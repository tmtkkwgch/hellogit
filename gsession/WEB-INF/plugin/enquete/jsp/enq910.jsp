<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ page import="jp.groupsession.v2.enq.GSConstEnquete" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META http-equiv="Content-Script-Type" content="text/javascript">
<META http-equiv="Content-Style-Type" content="text/css">
<title>[Group Session] <gsmsg:write key="cmn.admin.setting.menu" /></title>
<script type="text/javascript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script type="text/javascript" src="../common/js/jquery.selection-min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../enquete/js/enquete.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../enquete/js/enq910.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../enquete/css/enquete.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
</head>

<body class="body_03" onload="enq910Init(<bean:write name='enq910Form' property='enq910TaisyoKbn' />);">
<html:form action="/enquete/enq910">
<!-- BODY -->
<input type="hidden" name="CMD">
<html:hidden property="cmd" />
<html:hidden property="backScreen" />
<html:hidden property="enq910initFlg" />

<!-- 検索条件hidden -->
<%@ include file="/WEB-INF/plugin/enquete/jsp/enq010_hiddenParams.jsp" %>

<logic:notEmpty name="enq910Form" property="enq010priority">
<logic:iterate id="svPriority" name="enq910Form" property="enq010priority">
  <input type="hidden" name="enq010priority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq910Form" property="enq010status">
<logic:iterate id="svStatus" name="enq910Form" property="enq010status">
  <input type="hidden" name="enq010status" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq910Form" property="enq010svPriority">
<logic:iterate id="svPriority" name="enq910Form" property="enq010svPriority">
  <input type="hidden" name="enq010svPriority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq910Form" property="enq010svStatus">
<logic:iterate id="svStatus" name="enq910Form" property="enq010svStatus">
  <input type="hidden" name="enq010svStatus" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq910Form" property="enq010statusAnsOver">
<logic:iterate id="svStatusAnsOver" name="enq910Form" property="enq010statusAnsOver">
  <input type="hidden" name="enq010statusAnsOver" value="<bean:write name="svStatusAnsOver" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq910Form" property="enq010svStatusAnsOver">
<logic:iterate id="svStatusAnsOver" name="enq910Form" property="enq010svStatusAnsOver">
  <input type="hidden" name="enq010svStatusAnsOver" value="<bean:write name="svStatusAnsOver" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="enq910Form" property="enq910MakeSenderList" scope="request">
    <logic:iterate id="ulBean" name="enq910Form" property="enq910MakeSenderList" scope="request">
    <input type="hidden" name="enq910MakeSenderList" value="<bean:write name="ulBean" />">
    </logic:iterate>
</logic:notEmpty>

<!-- header -->
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!-- content -->
<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" width="70%">
  <tr>
  <td align="center">

    <!-- タイトル -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="enq.enq900.01"/> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt="<gsmsg:write key='cmn.function.btn' />"></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('enq910ok');">
          <input type="button" value="<gsmsg:write key='cmn.back.admin.setting' />" class="btn_back_n3" onClick="buttonPush('enq910back');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt="<gsmsg:write key='cmn.function.btn' />"></td>
      </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1" height="5" border="0">

    <!-- エラーメッセージ -->
    <div style="text-align:left">
    <html:errors/>
    </div>


    <table cellpadding="10" cellspacing="0" border="0" width="100%" class="tl_u2">
    <thead>
      <!-- 対象者区分 -->
      <tr>
        <td valign="middle" align="left" class="table_bg_A5B4E1" width="20%" nowrap>
          <span class="text_bb1"><gsmsg:write key="enq.enq910.01"/></span>
        </td>
        <td align="left" class="td_wt" width="80%">
          <span class="text_base">
            <html:radio styleId="taisyo0" styleClass="taisyoKbn" name="enq910Form" property="enq910TaisyoKbn" value="<%= String.valueOf(GSConstEnquete.CONF_TAISYO_LIMIT) %>" onclick="changeTaisyoKbn(0);" />
            <label for="taisyo0"><gsmsg:write key="enq.enq910.02"/></label>&nbsp;
            <html:radio styleId="taisyo1" styleClass="taisyoKbn" name="enq910Form" property="enq910TaisyoKbn" value="<%= String.valueOf(GSConstEnquete.CONF_TAISYO_ALL) %>" onclick="changeTaisyoKbn(1);" />
            <label for="taisyo1"><gsmsg:write key="enq.enq910.03"/></label>&nbsp;
          </span>
        </td>
      </tr>
    </thead>

    <tbody id="taisyoArea">

      <!-- アンケート発信対象者-->
      <tr>
        <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
          <span class="text_bb1"><gsmsg:write key="enq.70"/></span>
        </td>
        <td valign="middle" align="left" class="td_wt">
          <table width="100%" border="0">
            <tr>
              <td width="40%" class="table_bg_A5B4E1 td_comboBox" align="center" nowrap><span class="text_bb1"><gsmsg:write key="enq.70"/></span></td>
              <td width="20%" align="center">&nbsp;</td>
              <td width="40%" align="left" class="td_comboBox">

                <!-- 全グループから選択 -->
                <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup(this.form.enq910GroupSid, 'enq910GroupSid', '<bean:write name="enq910Form" property="enq910GroupSid" />', '0', 'enq910ChangeGroup', 'enq910MakeSenderList', '-1', '1')" type="button"><br>

                <!-- グループコンボボックス -->
                <logic:notEmpty name="enq910Form" property="enq910GroupLabel" scope="request">

                  <html:select property="enq910GroupSid" onchange="buttonPush('enq910ChangeGroup');" styleClass="select05">
                    <logic:notEmpty name="enq910Form" property="enq910GroupLabel" scope="request">
                      <logic:iterate id="gpBean" name="enq910Form" property="enq910GroupLabel" scope="request">
                        <bean:define id="gpValue" name="gpBean" property="value" type="java.lang.String" />
                        <html:option value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
                      </logic:iterate>
                    </logic:notEmpty>
                  </html:select>

                </logic:notEmpty>
                <input type="button" onclick="openGroupWindow(this.form.enq910GroupSid, 'enq910GroupSid', '0', 'enq910ChangeGroup')" class="group_btn2" value="&nbsp;" id="groupBtn">
              </td>
            </tr>

            <tr>
              <!-- アンケート発信対象者リスト -->
              <td align="center" valign="top">
                <select size="10" name="enq910SelectAddSenderSid" class="select01" style="width:100%;" multiple>
                  <logic:notEmpty name="enq910Form" property="enq910AddSenderLabel" scope="request">
                    <logic:iterate id="labelValueBean" name="enq910Form" property="enq910AddSenderLabel" scope="request">
                      <option value="<bean:write name="labelValueBean" property="value"/>"><bean:write name="labelValueBean" property="label"/></option>
                    </logic:iterate>
                  </logic:notEmpty>
                </select>
              </td>

              <td align="center">
                <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="addSenderBtn" onClick="buttonPush('enq910AddSender');"><br><br>
                <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="removeSenderBtn" onClick="buttonPush('enq910RemoveSender');">
              </td>

              <!-- グループ所属ユーザリスト -->
              <td align="center" valign="top" rowspan="3">
                <select size="10" name="enq910SelectBelongSenderSid" class="select01" style="width:100%;" multiple>
                  <logic:notEmpty name="enq910Form" property="enq910BelongSenderLabel" scope="request">
                    <logic:iterate id="labelValueBean" name="enq910Form" property="enq910BelongSenderLabel" scope="request">
                      <option value="<bean:write name="labelValueBean" property="value" scope="page"/>"><bean:write name="labelValueBean" property="label" scope="page"/></option>
                    </logic:iterate>
                  </logic:notEmpty>
                </select>
              </td>
            </tr>
          </table>
        </td>
      </tr>

    </tbody>
    </table>

    <img src="../common/images/spacer.gif" width="1" height="10" border="0">

    <table cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td align="right">
          <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('enq910ok');">
          <input type="button" value="<gsmsg:write key='cmn.back.admin.setting' />" class="btn_back_n3" onClick="buttonPush('enq910back');">
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