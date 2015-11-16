<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.reserve" />
  <logic:equal name="rsv060Form" property="rsv060ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_SINKI) %>"> [<gsmsg:write key="reserve.rsv060.1" />]</logic:equal>
  <logic:equal name="rsv060Form" property="rsv060ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_EDIT) %>"> [<gsmsg:write key="reserve.rsv060.2" />]</logic:equal>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../reserve/js/rsv060.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../reserve/js/sisetuPopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../reserve/css/reserve.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03" onload="bodyLoad(); showOrHide(); showOrHideText(<bean:write name="rsv060Form" property="rsv060AccessKbn" />);" onunload="windowClose();">
<html:form action="/reserve/rsv060">
<input type="hidden" name="CMD" value="sisetu_toroku_kakunin">
<html:hidden property="rsvBackPgId" />
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsvSelectedGrpSid" />
<html:hidden property="rsvSelectedSisetuSid" />
<html:hidden property="rsv050SortRadio" />
<html:hidden property="rsv060InitFlg" />
<html:hidden property="rsv060ProcMode" />
<html:hidden property="rsv060EditGrpSid" />
<html:hidden property="rsv060AccessDspFlg" />

<%@ include file="/WEB-INF/plugin/reserve/jsp/rsvHidden.jsp" %>

<logic:notEmpty name="rsv060Form" property="rsv100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="rsv060Form" property="rsv100CsvOutField" scope="request">
    <input type="hidden" name="rsv100CsvOutField" value="<bean:write name="csvOutField"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv060Form" property="saveUser" scope="request">
  <logic:iterate id="usrSid" name="rsv060Form" property="saveUser" scope="request">
    <input type="hidden" name="saveUser" value="<bean:write name="usrSid" />">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv060Form" property="rsvIkkatuTorokuKey" scope="request">
  <logic:iterate id="key" name="rsv060Form" property="rsvIkkatuTorokuKey" scope="request">
    <input type="hidden" name="rsvIkkatuTorokuKey" value="<bean:write name="key"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv060Form" property="rsv060memberSid">
<logic:iterate id="usid" name="rsv060Form" property="rsv060memberSid">
  <input type="hidden" name="rsv060memberSid" value="<bean:write name="usid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv060Form" property="rsv060memberSidRead">
<logic:iterate id="usid" name="rsv060Form" property="rsv060memberSidRead">
  <input type="hidden" name="rsv060memberSidRead" value="<bean:write name="usid" />">
</logic:iterate>
</logic:notEmpty>

<input type="hidden" name="helpPrm" value="<bean:write name="rsv060Form" property="rsv060ProcMode" />">

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
      <logic:equal name="rsv060Form" property="rsv060ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_SINKI) %>">[ <gsmsg:write key="reserve.rsv060.1" /> ]</logic:equal>
      <logic:equal name="rsv060Form" property="rsv060ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_EDIT) %>">[ <gsmsg:write key="reserve.rsv060.2" /> ]</logic:equal>
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
      <input type="submit" value="OK" class="btn_ok1">
      <logic:equal name="rsv060Form" property="rsv060ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_EDIT) %>">
        <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onclick="buttonPush('sisetu_sakuzyo_kakunin');">
      </logic:equal>
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
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.group.name" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_wt" width="80%">
      <html:text name="rsv060Form" styleClass="text_base" property="rsv060GrpName" maxlength="20" style="width:395px;" />
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.group.id" /></span></td>
    <td align="left" class="td_wt" width="80%">
      <span class="text_base7"><gsmsg:write key="reserve.rsv060.3" /></span><br>
      <html:text name="rsv060Form" styleClass="text_base" property="rsv060GrpId" maxlength="15" style="width:275px;" />
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="reserve.47" /></span></td>
    <td align="left" class="td_type1">
      <html:select property="rsv060SelectedSisetuKbn" styleClass="select01">
        <html:optionsCollection name="rsv060Form" property="rsv060SisetuLabelList" value="value" label="label" />
      </html:select>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="reserve.162" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_type1">
        <bean:define id="admOk" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSG_ADM_KBN_OK) %>" />
        <bean:define id="admNo" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSG_ADM_KBN_NO) %>" />
        <html:radio name="rsv060Form" styleId="doSet" property="rsv060GrpAdmKbn" value="<%= admOk %>" onclick="admChange(0);" /><span class="text_gray"><label for="doSet"><gsmsg:write key="reserve.163" /></label></span>
        <html:radio name="rsv060Form" styleId="notSet" property="rsv060GrpAdmKbn" value="<%= admNo %>" onclick="admChange(1);" /><span class="text_gray"><label for="notSet"><gsmsg:write key="reserve.164" /></label></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.group.admin" /></span>
    <td align="left" class="td_wt">
      <table width="100%">
      <tr>
      <td width="100%">
        <table width="" border="0">
        <tr>
        <td width="35%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="reserve.52" /></span></td>
        <td width="20%" align="center">&nbsp;</td>
        <td width="35%" align="left">
          <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup(this.form.rsv060SelectedGrpComboSid, 'rsv060SelectedGrpComboSid', '<bean:write name="rsv060Form" property="rsv060SelectedGrpComboSid" />', '0', '', 'saveUser', '-1', '0')" type="button"><br>
          <html:select property="rsv060SelectedGrpComboSid" styleClass="select01" onchange="changeGroupCombo();">
            <html:optionsCollection name="rsv060Form" property="rsv060GrpLabelList" value="value" label="label" />
          </html:select>
        </td>
        <td width="10%" align="left" valign="bottom">
          <input type="button" onclick="openGroupWindow(this.form.rsv060SelectedGrpComboSid, 'rsv060SelectedGrpComboSid', '0')" class="group_btn2" value="&nbsp;&nbsp;" id="rsv060GroupBtn">
        </td>
        </tr>

        <tr>
        <td align="center">
        <html:select property="rsv060SelectedLeft" size="5" styleClass="select01" multiple="true">
          <logic:notEmpty name="rsv060Form" property="rsv060AdmUser">
          <html:optionsCollection name="rsv060Form" property="rsv060AdmUser" value="value" label="label" />
          </logic:notEmpty>
        </html:select>

        </td>
        <td align="center">
          <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('user_tuika');"><br><br>
          <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('user_sakuzyo');">
        </td>

        <td align="center">
        <html:select property="rsv060SelectedRight" size="5" styleClass="select01" multiple="true">
          <logic:notEmpty name="rsv060Form" property="rsv060NotAdmUser">
          <html:optionsCollection name="rsv060Form" property="rsv060NotAdmUser" value="value" label="label" />
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
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="reserve.appr.set.title" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_type1">
      <bean:define id="apprKbn_appr" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSG_APPR_KBN_APPR) %>" />
      <bean:define id="apprKbn_sisetsu" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSG_APPR_KBN_SISETSU) %>" />
      <html:radio name="rsv060Form" styleId="apprKbn1" property="rsv060apprKbn" value="<%= apprKbn_appr %>" /><span class="text_gray"><label for="apprKbn1"><gsmsg:write key="reserve.appr.set.kbn1" /></label></span>
      <html:radio name="rsv060Form" styleId="apprKbn2" property="rsv060apprKbn" value="<%= apprKbn_sisetsu %>" /><span class="text_gray"><label for="apprKbn2"><gsmsg:write key="reserve.appr.set.kbn3" /></label></span>
    </td>
    </tr>

    <tr>
    <td colspan="2">
    <span id="text1">
    <a href="#" onClick="changeShowOrHide()"><span class="text_link" ><gsmsg:write key="cmn.not.setting.access.conf" /></span></a>
    </span>

    <span id="text2">
    <a href="#" onClick="changeShowOrHide()"><span class="text_link" ><gsmsg:write key="cmn.setting.access.conf" /></span></a>
    </span>

    </td>
    </tr>

    </table>


    <div id="show0">
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.access.auth" /></span>
    <td align="left" class="td_wt" width="80%">

      <table>
      <tr>
      <td colspan="4">
        <span class="text_bb1"><gsmsg:write key="cmn.howto.limit" /></span><br>
        <span class="">
          <bean:define id="textPermit" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSV_ACCESS_MODE_PERMIT) %>" />
          <bean:define id="textLimit" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSV_ACCESS_MODE_LIMIT) %>" />
        <html:radio name="rsv060Form" styleId="permit" property="rsv060AccessKbn" value="<%= textPermit %>" onclick="changeShowOrHideText(0);" /><span class="text_gray"><label for="permit"><gsmsg:write key="cmn.access.permit" /></label></span><br>
        <html:radio name="rsv060Form" styleId="limit" property="rsv060AccessKbn" value="<%= textLimit %>" onclick="changeShowOrHideText(1);" /><span class="text_gray"><label for="limit"><gsmsg:write key="cmn.access.limit" /></label></span>
        </span>
        <br><br>
      </td>
      </tr>

      <tr>
      <td width="35%" class="table_bg_A5B4E1" align="center">
      <span class="text_bb1">
        <span id="showText0"><gsmsg:write key="reserve.161" /></span>
        <span id="showText1"><gsmsg:write key="reserve.165" /></span>
      </span>
      </td>
      <td width="20%" align="center">&nbsp;</td>
      <td width="35%" align="left">
          <input id="showBtn0" class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup2(this.form.rsv060groupSid, 'rsv060groupSid', '<bean:write name="rsv060Form" property="rsv060groupSid" />', '0', 'init', 'rsv060memberSid', 'rsv060memberSidRead', 'reserve', '-1', '1')" type="button">
          <input id="showBtn1" class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup2(this.form.rsv060groupSid, 'rsv060groupSid', '<bean:write name="rsv060Form" property="rsv060groupSid" />', '0', 'init', 'rsv060memberSid', 'rsv060memberSidRead', 'reserve_2', '-1', '1')" type="button">
        <html:select property="rsv060groupSid" styleClass="select01" onchange="changeGroupCombo();">
          <logic:notEmpty name="rsv060Form" property="rsv060GroupList">
          <html:optionsCollection name="rsv060Form" property="rsv060GroupList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
      </td>
      <td width="10%" align="left" valign="bottom">
        <input type="button" onclick="openGroupWindow(this.form.rsv060groupSid, 'rsv060groupSid', '0')" class="group_btn2" value="&nbsp;&nbsp;" id="rsv060GroupBtn2">
      </td>
      </tr>

      <tr>
      <td align="center">
        <html:select property="rsv060SelectLeftUser" size="5" styleClass="select01" multiple="true">
          <logic:notEmpty name="rsv060Form" property="rsv060LeftUserList">
          <html:optionsCollection name="rsv060Form" property="rsv060LeftUserList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
      </td>
      <td align="center">
        <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('addMember');"><br><br>
        <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('removeMember');">
      </td>
      <td rowspan="3">
        <html:select property="rsv060SelectRightUser" size="13" styleClass="select01" multiple="true">
          <logic:notEmpty name="rsv060Form" property="rsv060RightUserList">
          <html:optionsCollection name="rsv060Form" property="rsv060RightUserList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
      </td>
      </tr>


      <tr>
      <td width="40%" class="table_bg_A5B4E1" align="center">
      <span class="text_bb1">
        <span id="showText2"><gsmsg:write key="cmn.reading.ok" /></span>
        <span id="showText3"><gsmsg:write key="cmn.reading.ng" /></span>
      </span>
      </td>
      <td width="20%" align="center">&nbsp;</td>
      </tr>

      <tr>
      <td align="center">
        <html:select property="rsv060SelectLeftUserRead" size="5" styleClass="select01" multiple="true">
          <logic:notEmpty name="rsv060Form" property="rsv060LeftUserListRead">
          <html:optionsCollection name="rsv060Form" property="rsv060LeftUserListRead" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
      </td>

      <td align="center">
        <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('addMemberRead');"><br><br>
        <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('removeMemberRead');">
      </td>
      </tr>

      </table>
    </td>
    </tr>
    </table>
    </div>

    <logic:equal name="rsv060Form" property="rsv060ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_EDIT) %>">
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td>&nbsp;</td>
    </tr>

    <tr>
    <th align="center" class="table_bg_7D91BD" colspan="2"><span class="text_tlw"><gsmsg:write key="reserve.rsv060.4" /></span></th>
    </tr>

    <bean:define id="mod" value="0" />

    <logic:notEmpty name="rsv060Form" property="rsv060SyozokuList" scope="request">
      <logic:iterate id="syozoku" name="rsv060Form" property="rsv060SyozokuList" scope="request" indexId="idx">

      <tr>
      <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
        <bean:define id="tblColor" value="td_type25_2" />
      </logic:notEqual>
      <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
        <bean:define id="tblColor" value="td_type1" />
      </logic:equal>

      <td align="left" class="<bean:write name="tblColor" />" colspan="2"><a href="javaScript:void(0);" onclick="openSisetuSyosai(<bean:write name="syozoku" property="rsdSid" />);"><bean:write name="syozoku" property="rsdName" /><a></td>
      </tr>

      </logic:iterate>
    </logic:notEmpty>

    </table>
    </logic:equal>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="100%" align="right">
      <input type="submit" value="OK" class="btn_ok1">
      <logic:equal name="rsv060Form" property="rsv060ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_EDIT) %>">
        <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onclick="buttonPush('sisetu_sakuzyo_kakunin');">
      </logic:equal>
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