<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ page import="jp.groupsession.v2.rsv.rsv310.Rsv310Form" %>

<% String maxLengthNaiyo = String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.MAX_LENGTH_NAIYO); %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.reserve" /> [ <gsmsg:write key="reserve.rsv210.1" /> ] </title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../reserve/js/rsv210.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../reserve/js/rsvschedule.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../reserve/js/sisetuPopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/reservepopup.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../reserve/css/reserve.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03" onunload="windowClose();" onload="showLengthId($('#inputstr')[0], <%= maxLengthNaiyo %>, 'inputlength');rsvSchChange();">
<html:form action="/reserve/rsv210">
<input type="hidden" name="CMD" value="ikkatu_toroku">
<html:hidden property="rsvBackPgId" />
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsvSelectedGrpSid" />
<html:hidden property="rsvSelectedSisetuSid" />
<html:hidden property="rsv210YrkFrom" />

<%@ include file="/WEB-INF/plugin/reserve/jsp/rsvHidden.jsp" %>

<logic:notEmpty name="rsv210Form" property="rsv100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="rsv210Form" property="rsv100CsvOutField" scope="request">
    <input type="hidden" name="rsv100CsvOutField" value="<bean:write name="csvOutField"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="rsv210InitFlg" />
<logic:notEmpty name="rsv210Form" property="rsvIkkatuTorokuKey" scope="request">
  <logic:iterate id="key" name="rsv210Form" property="rsvIkkatuTorokuKey" scope="request">
    <input type="hidden" name="rsvIkkatuTorokuKey" value="<bean:write name="key"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv210Form" property="sv_users" scope="request">
  <logic:iterate id="ulBean" name="rsv210Form" property="sv_users" scope="request">
    <input type="hidden" name="sv_users" value="<bean:write name="ulBean" />">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv210Form" property="rsv210SchNotAccessGroupList" scope="request">
  <logic:iterate id="notAccessGroup" name="rsv210Form" property="rsv210SchNotAccessGroupList">
    <input type="hidden" name="rsvSchNotAccessGroup" value="<bean:write name="notAccessGroup" />">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv210Form" property="rsv210SchNotAccessUserList" scope="request">
  <logic:iterate id="notAccessUser" name="rsv210Form" property="rsv210SchNotAccessUserList">
    <input type="hidden" name="rsvSchNotAccessUser" value="<bean:write name="notAccessUser" />">
  </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%">
      <img src="../reserve/images/header_reserve_01.gif" border="0" alt="<gsmsg:write key="cmn.reserve" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.reserve" /></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="reserve.rsv210.1" /> ]</td>
    <td width="0%" class="header_white_bg">
      <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../reserve/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="submit" value="OK" class="btn_ok1">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('back_to_menu');">
    </td>
    <td width="0%"><img src="../reserve/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

  </td>
  </tr>
  <tr>
  <td>
    <img src="../common/images/spacer.gif" style="width:1px; height:2px;" border="0" alt="">
    <logic:messagesPresent message="false"><html:errors /></logic:messagesPresent>
    <img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt="">
  </td></tr>
  <tr>
  <td>
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="reserve.122" /></span></td>
    <td align="left" class="td_type1" width="80%">

      <table width="100%">

      <logic:iterate id="day" name="rsv210Form" property="rsvIkkatuTorokuHiddenList" scope="request">

        <tr>
        <td class="text_hid_day"><bean:write name="day" property="hidDayStr" /></td>
        </tr>

        <logic:iterate id="grp" name="day" property="grpList">
        <tr>
        <td class="text_hid_grp_lpad">■<bean:write name="grp" property="rsgName" /></td>
        </tr>

          <logic:iterate id="sisetu" name="grp" property="sisetuList">
          <tr>
          <td width="100%" class="td_sisetu_lpad">
            <a href="#" onclick="openSisetuSyosai(<bean:write name="sisetu" property="rsdSid" />);"><span class="rsv_sc_link_1"><bean:write name="sisetu" property="rsdName" /></span></a>
          </td>
          </tr>
          </logic:iterate>

        <tr>
        <td><img src="../common/images/spacer.gif" width="1" height="3"></td>
        </tr>

        </logic:iterate>

      </logic:iterate>

      </table>

    </td>
    </tr>
    </table>
  </td>
  </tr>
  <tr>
  <td><br></td>
  </tr>

  <tr>
  <td>

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.registant" /></span></td>
    <td align="left" class="td_type1" width="80%"><span class="text_base_rsv"><bean:write name="rsv210Form" property="rsv210Torokusya" /></span></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="reserve.72" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_type1">
      <html:text name="rsv210Form" property="rsv210Mokuteki" style="width:333px;" maxlength="50" styleClass="text_base_rsv" />
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.time" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_wt">

      <span class="text_base"><gsmsg:write key="cmn.start" />：</span>
      <html:select property="rsv210SelectedHourFr">
        <logic:notEmpty name="rsv210Form" property="rsv210HourComboList">
          <html:optionsCollection name="rsv210Form" property="rsv210HourComboList" value="value" label="label" />
        </logic:notEmpty>
      </html:select>
      <gsmsg:write key="cmn.hour.input" />

      <html:select property="rsv210SelectedMinuteFr">
        <logic:notEmpty name="rsv210Form" property="rsv210MinuteComboList">
          <html:optionsCollection name="rsv210Form" property="rsv210MinuteComboList" value="value" label="label" />
        </logic:notEmpty>
      </html:select>
      <gsmsg:write key="cmn.minute.input" />

      <br>

      <span class="text_base"><gsmsg:write key="cmn.end" />：</span>
      <html:select property="rsv210SelectedHourTo">
        <logic:notEmpty name="rsv210Form" property="rsv210HourComboList">
          <html:optionsCollection name="rsv210Form" property="rsv210HourComboList" value="value" label="label" />
        </logic:notEmpty>
      </html:select>
      <gsmsg:write key="cmn.hour.input" />

      <html:select property="rsv210SelectedMinuteTo">
        <logic:notEmpty name="rsv210Form" property="rsv210MinuteComboList">
          <html:optionsCollection name="rsv210Form" property="rsv210MinuteComboList" value="value" label="label" />
        </logic:notEmpty>
      </html:select>
      <gsmsg:write key="cmn.minute.input" />

    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.content" /></span></td>
    <td align="left" class="td_type1">
      <textarea styleClass="text_base_rsv" name="rsv210Naiyo" style="width:489px;" rows="6" onkeyup="showLengthStr(value, <%= maxLengthNaiyo %>, 'inputlength');" id="inputstr"><bean:write name="rsv210Form" property="rsv210Naiyo" /></textarea>
      <br>
      <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthNaiyo %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.edit.permissions" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_type1">
      <span class="text_base_rsv"><gsmsg:write key="cmn.comments" /><gsmsg:write key="reserve.89" /><br>
      ※<gsmsg:write key="reserve.90" /><br>
      &nbsp;&nbsp;<gsmsg:write key="reserve.91" /></span>
      <br><br>
      <html:radio styleId="lvl1" name="rsv210Form" property="rsv210RsyEdit" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.EDIT_AUTH_NONE) %>" /><label for="lvl1"><gsmsg:write key="cmn.nolimit" /></label>&nbsp;
      <html:radio styleId="lvl2" name="rsv210Form" property="rsv210RsyEdit" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.EDIT_AUTH_PER_AND_ADU) %>" /><label for="lvl2"><gsmsg:write key="cmn.only.principal.or.registant" /></label>&nbsp;
      <html:radio styleId="lvl3" name="rsv210Form" property="rsv210RsyEdit" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.EDIT_AUTH_GRP_AND_ADU) %>" /><label for="lvl3"><gsmsg:write key="cmn.only.affiliation.group.membership" /></label>
    </td>
    </tr>


    <logic:equal name="rsv210Form" property="schedulePluginKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.PLUGIN_USE) %>">

      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="schedule.3" /></span></td>
      <td align="left" class="td_type1">

        <html:radio property="rsv210SchKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSV_SCHKBN_USER) %>" styleId="rsvSchKbn0" onclick="rsvSchChange();" /><label for="rsvSchKbn0"><span class="text_base"><gsmsg:write key="cmn.user" /></span></label>
        <html:radio property="rsv210SchKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSV_SCHKBN_GROUP) %>" styleId="rsvSchKbn1" onclick="rsvSchChange();" /><label for="rsvSchKbn1"><span class="text_base"><gsmsg:write key="cmn.group" /></span></label>
        <br>

        <span id="rsvSchGroup">
          <span class="text_r1">[<gsmsg:write key="reserve.167" />]</span><br>
           <html:select property="rsv210SchGroupSid">
          <logic:notEmpty name="rsv210Form" property="rsv210SchGroupLabel" scope="request">

            <logic:iterate id="exSchGpBean" name="rsv210Form" property="rsv210SchGroupLabel" scope="request">
              <% boolean schGpDisabled = false; %>
              <logic:equal name="exSchGpBean" property="viewKbn" value="false">
                <% schGpDisabled = true; %>
              </logic:equal>
              <bean:define id="gpValue" name="exSchGpBean" property="value" type="java.lang.String" />
              <logic:equal name="exSchGpBean" property="styleClass" value="0">
                <html:option value="<%= gpValue %>" disabled="<%= schGpDisabled %>"><bean:write name="exSchGpBean" property="label" /></html:option>
              </logic:equal>
              <logic:notEqual name="exSchGpBean" property="styleClass" value="0">
                <html:option value="<%= gpValue %>" disabled="<%= schGpDisabled %>"><bean:write name="exSchGpBean" property="label" /></html:option>
              </logic:notEqual>

            </logic:iterate>


<%--              <html:optionsCollection name="rsv210Form" property="rsv210SchGroupLabel" value="value" label="label" /> --%>
          </logic:notEmpty>
          </html:select>
<%--           <html:select property="rsv210SchGroupSid"> --%>
<%--           <logic:notEmpty name="rsv210Form" property="rsv210SchGroupLabel" scope="request"> --%>
<%--              <html:optionsCollection name="rsv210Form" property="rsv210SchGroupLabel" value="value" label="label" /> --%>
<%--           </logic:notEmpty> --%>
<%--           </html:select> --%>
        </span>

        <table cellpadding="0" cellspacing="0" border="0" width="100%" id="rsvSchUser">
        <tr>
        <td width="100%" align="left" class="tbl_in_tbl">
          <span class="text_r1">[<gsmsg:write key="reserve.166" />]</span>

          <table width="0%" border="0">
          <tr>
          <td width="40%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="cmn.from" /></span></td>
          <td width="20%" align="center">&nbsp;</td>
          <td width="40%" align="left">

            <logic:notEmpty name="rsv210Form" property="rsv210GroupLabel" scope="request">
              <logic:equal name="rsv210Form" property="rsv210SchCrangeKbn" value="0">
              <input id="test" class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup_setDisable(this.form.rsv210GroupSid, 'rsv210GroupSid', '<bean:write name="rsv210Form" property="rsv210GroupSid" />', '1', '110_group', 'sv_users', '-1', '0', 0, 0, 0, 'rsvSchNotAccessUser', null, 'rsvSchNotAccessGroup')" type="button">
              </logic:equal>
              <html:select property="rsv210GroupSid" onchange="buttonPush('110_group');">
              <logic:notEmpty name="rsv210Form" property="rsv210GroupLabel" scope="request">
                <logic:iterate id="gpBean" name="rsv210Form" property="rsv210GroupLabel" scope="request">
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
            </logic:notEmpty>

            <span class="text_base">
            <input type="checkbox" name="rsv210SelectUsersKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SELECT_ON) %>" id="select_user" onclick="return selectUsersList();" />
            <label for="select_user"><gsmsg:write key="cmn.select" /></label></span>

          </td>
          </tr>

          <tr>
          <td align="center">
            <!-- 同時登録先 -->
            <select size="5" multiple name="users_r" class="select01">
            <logic:notEmpty name="rsv210Form" property="rsv210SelectUsrLabel" scope="request">
              <logic:iterate id="urBean" name="rsv210Form" property="rsv210SelectUsrLabel" scope="request">
                <option value="<bean:write name="urBean" property="usrSid" scope="page"/>"><bean:write name="urBean" property="usiSei" scope="page"/>　<bean:write name="urBean" property="usiMei" scope="page"/></option>
              </logic:iterate>
            </logic:notEmpty>
            <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
            </select>
          </td>

          <td align="center">
            <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('210_rightarrow');"><br><br>
            <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('210_leftarrow');">
          </td>
          <td>
            <select size="5" multiple name="users_l" class="select01">
            <logic:notEmpty name="rsv210Form" property="rsv210BelongLabel" scope="request">
              <logic:iterate id="urBean" name="rsv210Form" property="rsv210BelongLabel" scope="request">
                <option value="<bean:write name="urBean" property="usrSid" scope="page"/>"><bean:write name="urBean" property="usiSei" scope="page"/>　<bean:write name="urBean" property="usiMei" scope="page"/></option>
              </logic:iterate>
            </logic:notEmpty>
            <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
            </select>
          </td>
          </tr>
          </table>

        </td>
        </tr>
        </table>
      </td>
      </tr>
    </logic:equal>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="schedule.18" /></span></td>
    <td align="left" class="td_type1">
      <table class="tl0" width="100%">
      <tr>
      <td width="50%" nowrap>      <span class="text_base">※<gsmsg:write key="schedule.35" /></span>
      </span>
      </td>
      <td width="50%" align="left" nowrap>
      <input type="button" value="<gsmsg:write key="schedule.17" />" class="btn_base1" onClick="openScheduleReserveWindowForReserve(<%= String.valueOf(Rsv310Form.POP_DSP_MODE_RSV210) %>);">
      </td>
      </tr>
      </table>
    </td>
    </tr>

    </table>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../reserve/images/spacer.gif" width="1px" height="10px" border="0" alt="">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="100%" align="right">
      <input type="submit" value="OK" class="btn_ok1">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('back_to_menu');">
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