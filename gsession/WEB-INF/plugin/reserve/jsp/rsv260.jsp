<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.reserve" /> [<gsmsg:write key="reserve.rsv260.1" />]</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../reserve/js/rsv260.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../reserve/js/sisetuPopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../reserve/css/reserve.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03" onload="bodyLoad();" onunload="windowClose();">
<html:form action="/reserve/rsv260">
<input type="hidden" name="CMD" value="sisetu_toroku_kakunin">
<html:hidden property="rsvBackPgId" />
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsvSelectedGrpSid" />
<html:hidden property="rsvSelectedSisetuSid" />
<html:hidden property="rsv050SortRadio" />

<%@ include file="/WEB-INF/plugin/reserve/jsp/rsvHidden.jsp" %>

<logic:notEmpty name="rsv260Form" property="rsv100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="rsv260Form" property="rsv100CsvOutField" scope="request">
    <input type="hidden" name="rsv100CsvOutField" value="<bean:write name="csvOutField"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv260Form" property="saveUser" scope="request">
  <logic:iterate id="usrSid" name="rsv260Form" property="saveUser" scope="request">
    <input type="hidden" name="saveUser" value="<bean:write name="usrSid" />">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv260Form" property="rsvIkkatuTorokuKey" scope="request">
  <logic:iterate id="key" name="rsv260Form" property="rsvIkkatuTorokuKey" scope="request">
    <input type="hidden" name="rsvIkkatuTorokuKey" value="<bean:write name="key"/>">
  </logic:iterate>
</logic:notEmpty>

<input type="hidden" name="helpPrm" value="">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table width="100%">
<tr>
<td width="100%" align="center">

  <table width="70%" class="tl0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%">
      <img src="../reserve/images/header_reserve_01.gif" border="0" alt="<gsmsg:write key="cmn.reserve" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.reserve" /></span></td>
    <td width="100%" class="header_white_bg_text">
      [ <gsmsg:write key="reserve.rsv260.1" /> ]
    </td>
    <td width="0%" class="header_white_bg">
      <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../reserve/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" class="btn_csv_n2" value="<gsmsg:write key="cmn.import" />" onclick="buttonPush('move_import');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('back_to_sisetu_group_settei');">
    </td>
    <td width="0%"><img src="../reserve/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
    <logic:messagesPresent message="false"><html:errors /></logic:messagesPresent>
    <img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt="">
  </td>
  </tr>

  <tr>
  <td>
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="reserve.47" /></span></td>
    <td align="left" class="td_type1" width="100%">
      <html:select property="rsv260SelectedSisetuKbn" styleClass="select01" onchange="changeShisetsuCombo();">
        <html:optionsCollection name="rsv260Form" property="rsv260SisetuLabelList" value="value" label="label" />
      </html:select>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="reserve.110" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_wt" width="80%" nowrap>
      <input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTemp('rsv260SelectFiles', '<%= jp.groupsession.v2.rsv.GSConstReserve.PLUGIN_ID_RESERVE %>', '1');">
      &nbsp;<input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="buttonPush('delete');"><br>

      <html:select property="rsv260SelectFiles" styleClass="select01" multiple="false" size="1">
        <html:optionsCollection name="rsv260Form" property="rsv260FileLabelList" value="value" label="label" />
      </html:select>

      &nbsp;
      <span class="text_base">
      <% jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage(); %>
      <% String csvFileMsg = ""; %>
        <logic:equal name="rsv260Form" property="rsv260RskSid" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_HEYA) %>">
          <% csvFileMsg = "<a href=\"../reserve/rsv260.do?CMD=rsv260_sample&sample=1&kbn=1\">【" + gsMsg.getMessage(request, "reserve.112") + "】" + gsMsg.getMessage(request, "cmn.capture.csvfile") + "</a>"; %>
          *<gsmsg:write key="cmn.plz.specify2" arg0="<%= csvFileMsg %>" />
        </logic:equal>
        <logic:equal name="rsv260Form" property="rsv260RskSid" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_BUPPIN) %>">
          <% csvFileMsg = "<a href=\"../reserve/rsv260.do?CMD=rsv260_sample&sample=1&kbn=2\">【" + gsMsg.getMessage(request, "reserve.113") + "】" + gsMsg.getMessage(request, "cmn.capture.csvfile") + "</a>"; %>
          *<gsmsg:write key="cmn.plz.specify2" arg0="<%= csvFileMsg %>" />
        </logic:equal>
        <logic:equal name="rsv260Form" property="rsv260RskSid" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_CAR) %>">
          <% csvFileMsg = "<a href=\"../reserve/rsv260.do?CMD=rsv260_sample&sample=1&kbn=3\">【" + gsMsg.getMessage(request, "reserve.114") + "】" + gsMsg.getMessage(request, "cmn.capture.csvfile") + "</a>"; %>
          *<gsmsg:write key="cmn.plz.specify2" arg0="<%= csvFileMsg %>" />
        </logic:equal>
        <logic:equal name="rsv260Form" property="rsv260RskSid" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_BOOK) %>">
          <% csvFileMsg = "<a href=\"../reserve/rsv260.do?CMD=rsv260_sample&sample=1&kbn=4\">【" + gsMsg.getMessage(request, "reserve.115") + "】" + gsMsg.getMessage(request, "cmn.capture.csvfile") + "</a>"; %>
          *<gsmsg:write key="cmn.plz.specify2" arg0="<%= csvFileMsg %>" />
        </logic:equal>
        <logic:equal name="rsv260Form" property="rsv260RskSid" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_OTHER) %>">
          <% csvFileMsg = "<a href=\"../reserve/rsv260.do?CMD=rsv260_sample&sample=1&kbn=5\">【" + gsMsg.getMessage(request, "cmn.other") + "】" + gsMsg.getMessage(request, "cmn.capture.csvfile") + "</a>"; %>
          *<gsmsg:write key="cmn.plz.specify2" arg0="<%= csvFileMsg %>" />
        </logic:equal>
      </span>
      <br>
    </td>
    </tr>


    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.group.admin" /></span>
    <td align="left" class="td_wt">
      <span class="text_base"><gsmsg:write key="reserve.49" /></span><br><br>
      <table>
      <tr>
      <td>
        <bean:define id="admOk" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSG_ADM_KBN_OK) %>" />
        <bean:define id="admNo" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSG_ADM_KBN_NO) %>" />
        <html:radio name="rsv260Form" styleId="doSet" property="rsv260GrpAdmKbn" value="<%= admOk %>" onclick="admChange(0);" /><span class="text_gray"><label for="doSet"><gsmsg:write key="reserve.50" /></label></span>
        <html:radio name="rsv260Form" styleId="notSet" property="rsv260GrpAdmKbn" value="<%= admNo %>" onclick="admChange(1);" /><span class="text_gray"><label for="notSet"><gsmsg:write key="reserve.51" /></label></span>
      </td>
      </tr>
      <tr>
      <td>
        <table width="100%" border="0">
        <tr>
        <td width="35%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="reserve.52" /></span></td>
        <td width="2%" align="center">&nbsp;</td>
        <td width="16%" align="center">&nbsp;</td>
        <td width="2%" align="center">&nbsp;</td>
        <td width="35%" align="left">
        <input class="btn_group_n1" onclick="return openAllGroup(this.form.rsv260SelectedGrpComboSid, 'rsv260SelectedGrpComboSid', '-1', '0', 'rsv260_redsp', 'saveUser', '-1')" value="<gsmsg:write key="cmn.sel.all.group" />"  type="button"><br>
          <html:select property="rsv260SelectedGrpComboSid" styleClass="select01" onchange="changeGroupCombo();">
            <html:optionsCollection name="rsv260Form" property="rsv260GrpLabelList" value="value" label="label" />
          </html:select>
        </td>
        <td width="10%" align="center" valign="bottom">
          <input type="button" onclick="openGroupWindow(this.form.rsv260SelectedGrpComboSid, 'rsv260SelectedGrpComboSid', '0', 'rsv260_redsp')" class="group_btn2" value="&nbsp;&nbsp;" id="rsv260GroupBtn">
        </td>

        </tr>

        <tr>
        <td align="center">
          <html:select property="rsv260SelectedLeft" size="5" styleClass="select01" multiple="true">
            <logic:notEmpty name="rsv260Form" property="rsv260AdmUser" scope="request">
                <html:optionsCollection name="rsv260Form" property="rsv260AdmUser" value="value" label="label" />
            </logic:notEmpty>
          </html:select>

        </td>
        <td>&nbsp;</td>
        <td align="center">
          <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('user_tuika');"><br><br>
          <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('user_sakuzyo');">
        </td>
        <td>&nbsp;</td>

        <td align="center">
          <html:select property="rsv260SelectedRight" size="5" styleClass="select01" multiple="true">
            <logic:notEmpty name="rsv260Form" property="rsv260NotAdmUser" scope="request">
                <html:optionsCollection name="rsv260Form" property="rsv260NotAdmUser" value="value" label="label" />
            </logic:notEmpty>
          </html:select>
        </td>
        </tr>
        </table>

      </td>
      </tr>
      </table>

    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.overwrite" /></span></td>
    <td align="left" class="td_type1">
      <html:checkbox name="rsv260Form" property="rsv260updateFlg" value="1" styleId="updateFlg" /><label for="updateFlg" class="text_base"><gsmsg:write key="reserve.125" /></label>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="reserve.126" /></span></td>
    <td align="left" class="td_type1">
      <html:checkbox name="rsv260Form" property="rsv260createGrpFlg" value="1" styleId="createGrpFlg" /><label for="createGrpFlg" class="text_base"><gsmsg:write key="reserve.127" /></label>
    </td>
    </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="100%" align="right">
      <input type="button" class="btn_csv_n2" value="<gsmsg:write key="cmn.import" />" onclick="buttonPush('move_import');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('back_to_sisetu_group_settei');">
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