<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%-- 定数値 --%>
<bean:define id="schEditMode" name="sch240Form" property="sch230editMode" type="java.lang.Integer" />
<%
  int editMode = schEditMode.intValue();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[Group Session] <gsmsg:write key="schedule.sch240.01" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <link rel="stylesheet" href="../webmail/css/webmail.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
  <script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../schedule/js/sch240.js?<%= GSConst.VERSION_PARAM %>"></script>

</head>

<html:form action="/schedule/sch240">
<input type="hidden" name="CMD" value="">

<%@ include file="/WEB-INF/plugin/schedule/jsp/sch080_hiddenParams.jsp" %>

<logic:notEmpty name="sch240Form" property="sch100SvSearchTarget" scope="request">
  <logic:iterate id="svTarget" name="sch240Form" property="sch100SvSearchTarget" scope="request">
    <input type="hidden" name="sch100SvSearchTarget" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sch240Form" property="sch100SearchTarget" scope="request">
  <logic:iterate id="target" name="sch240Form" property="sch100SearchTarget" scope="request">
    <input type="hidden" name="sch100SearchTarget" value="<bean:write name="target"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sch240Form" property="sch100SvBgcolor" scope="request">
  <logic:iterate id="svBgcolor" name="sch240Form" property="sch100SvBgcolor" scope="request">
    <input type="hidden" name="sch100SvBgcolor" value="<bean:write name="svBgcolor"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sch240Form" property="sch100Bgcolor" scope="request">
  <logic:iterate id="bgcolor" name="sch240Form" property="sch100Bgcolor" scope="request">
    <input type="hidden" name="sch100Bgcolor" value="<bean:write name="bgcolor"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sch240Form" property="sch100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="sch240Form" property="sch100CsvOutField" scope="request">
    <input type="hidden" name="sch100CsvOutField" value="<bean:write name="csvOutField"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="sch230keyword" />
<html:hidden property="sch230pageTop" />
<html:hidden property="sch230pageBottom" />
<html:hidden property="sch230pageDspFlg" />
<html:hidden property="sch230svKeyword" />
<html:hidden property="sch230sortKey" />
<html:hidden property="sch230order" />
<html:hidden property="sch230editData" />
<html:hidden property="sch230searchFlg" />
<html:hidden property="sch230editMode" />

<html:hidden property="sch240initFlg" />

<logic:equal name="sch240Form" property="sch230editMode" value="0">
  <input type="hidden" name="helpPrm" value="0">
</logic:equal>
<logic:notEqual name="sch240Form" property="sch230editMode" value="0">
  <input type="hidden" name="helpPrm" value="1">
</logic:notEqual>

<logic:notEmpty name="sch240Form" property="sch240subject">
  <logic:iterate id="subject" name="sch240Form" property="sch240subject">
    <input type="hidden" name="sch240subject" value="<bean:write name="subject"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sch240Form" property="sch240editUser">
  <logic:iterate id="editUser" name="sch240Form" property="sch240editUser">
    <input type="hidden" name="sch240editUser" value="<bean:write name="editUser"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sch240Form" property="sch240accessUser">
  <logic:iterate id="accessUser" name="sch240Form" property="sch240accessUser">
    <input type="hidden" name="sch240accessUser" value="<bean:write name="accessUser"/>">
  </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="75%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
          <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt=""></td>
          <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <% if (editMode == 0) { %>
          <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="schedule.sch240.01" /> ]</td>
        <% } else if (editMode == 1) { %>
          <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="schedule.sch240.02" /> ]</td>
        <% } %>
          <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('confirm');">
          <% if (editMode == 1) { %>
            <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('deleteAccess');">
          <% } %>
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('beforePage');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

  </td>
  </tr>

  <logic:messagesPresent message="false">
    <tr>
    <td>
    <table width="100%">
      <tr><td align="left"><html:errors/></td></tr>
    </table>
    </td>
    </tr>
  </logic:messagesPresent>

  <tr>
  <td>
    <span id="errorArea"></span>
    <img src="../common/images/spacer.gif" width="10" height="10" border="0" alt="">
  </td>
  </tr>

  <tr>
  <td>

    <table id="wml_settings" class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
        <td class="table_bg_A5B4E1" width="150" nowrap><span class="text_bb1"><gsmsg:write key="schedule.sch240.04" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
        <td align="left" class="td_wt" width="850">
          <html:text name="sch240Form" property="sch240name" maxlength="50" style="width:273px;"/>
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="schedule.sch240.05" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
        <td align="left">
          <table width="100%" border="0" padding="0" margin="0">
          <tr>
          <td width="40%" class="td_sub_title3" align="center">
            <span class="text_bb1"><gsmsg:write key="schedule.sch240.06" /></span>
          </td>
          <td width="20%" align="center" style="border: 0px;">&nbsp;</td>

          <td width="40%" align="left" style="border: 0px;">
          <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup(this.form.sch240subjectGroup, 'sch240subjectGroup', '<bean:write name="sch240Form" property="sch240subjectGroup" />', '0', 'init', 'sch240subject', '-1', '0');" type="button">
          <html:select name="sch240Form" property="sch240subjectGroup" styleClass="select01" onchange="return buttonPush('changeGrp');" style="width: 150px;">
          <logic:notEmpty name="sch240Form" property="groupCombo">
          <html:optionsCollection name="sch240Form" property="groupCombo" value="value" label="label" />
          </logic:notEmpty>
          </html:select>
          <input type="button" onclick="openGroupWindow(this.form.sch240subjectGroup, 'sch240subjectGroup', '0', 'changeGrp')" class="group_btn2" value="&nbsp;&nbsp;" id="sch240subjectGroupBtn">
          </td>
          </tr>

          <tr>
          <td align="center">
          <html:select name="sch240Form" property="sch240subjectSelect" size="10" multiple="true" styleClass="select01" style="width:220px;">
          <logic:notEmpty name="sch240Form" property="sch240subjectSelectCombo">
          <html:optionsCollection name="sch240Form" property="sch240subjectSelectCombo" value="value" label="label" />
          </logic:notEmpty>
          </html:select>
          </td>

          <td align="center" style="border: 0px;">
          <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('addSubject');">
          <br><br>
          <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('deleteSubject');">
          </td>

          <td valign="top" style="border: 0px;">
          <html:select name="sch240Form" property="sch240subjectNoSelect" size="10" multiple="true" styleClass="select01" style="width:220px;">
          <logic:notEmpty name="sch240Form" property="sch240subjectNoSelectCombo">
          <html:optionsCollection name="sch240Form" property="sch240subjectNoSelectCombo" value="value" label="label" />
          </logic:notEmpty>
          </html:select>
          </td>
          </tr>

          </table>
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" rowspan="2" nowrap><span class="text_bb1"><gsmsg:write key="schedule.sch240.07" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
        <td align="left" class="td_type20">
          <table>
          <tr>
          <td rowspan="2" class="text_bb1" style="border: 0px!important; padding: 0px 5px 0px 0px; margin: 0px;">
          <gsmsg:write key="cmn.post" />:
          </td>
          <td style="border: 0px!important; padding: 0px; margin: 0px;">
          <html:select property="sch240position">
            <logic:notEmpty name="sch240Form" property="sch240positionCombo">
            <html:optionsCollection name="sch240Form" property="sch240positionCombo" value="value" label="label" />
            </logic:notEmpty>
          </html:select>
          </td>
          </tr>
          <tr id="positionAuthArea">
          <td class="text_bold" style="font-size: 75%; border: 0px!important; padding: 5px 0px 0px 0px; margin: 0px;">
          <html:radio property="sch240positionAuth" value="0" styleId="positionAuth0" />&nbsp;<label for="positionAuth0"><gsmsg:write key="cmn.add.edit.delete" /></label>
          &nbsp;&nbsp;<html:radio property="sch240positionAuth" value="1" styleId="positionAuth1" />&nbsp;<label for="positionAuth1"><gsmsg:write key="cmn.reading" /></label>
          </td>
          </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td>
          <table width="0%" border="0" padding="0" margin="0"  style="margin-top: 15px;">
          <tr>
          <td width="40%" class="td_sub_title3" align="center"><span class="text_bb1"><gsmsg:write key="cmn.add.edit.delete" /></span></td>
          <td width="20%" align="center" style="border: 0px;">&nbsp;</td>
          <td width="40%" align="left" style="border: 0px;">
            <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup2(this.form.sch240accessGroup, 'sch240accessGroup', '<bean:write name="sch240Form" property="sch240accessGroup" />', '0', 'init', 'sch240editUser', 'sch240accessUser', 'schedule', '-1', '1');" type="button">
            <html:select name="sch240Form" property="sch240accessGroup" styleClass="select01" onchange="return buttonPush('changeGrp');" style="width: 150px;">
            <logic:notEmpty name="sch240Form" property="groupCombo">
            <html:optionsCollection name="sch240Form" property="groupCombo" value="value" label="label" />
            </logic:notEmpty>
            </html:select>
            <input type="button" onclick="openGroupWindow(this.form.sch240accessGroup, 'sch240accessGroup', '0', 'changeGrp')" class="group_btn2" value="&nbsp;&nbsp;" id="sch240GroupBtn">
          </td>
          </tr>

          <tr>
          <td align="center">
            <html:select name="sch240Form" property="sch240editUserSelect" size="10" multiple="true" styleClass="select01" style="width:220px;">
            <logic:notEmpty name="sch240Form" property="sch240editUserSelectCombo">
            <html:optionsCollection name="sch240Form" property="sch240editUserSelectCombo" value="value" label="label" />
            </logic:notEmpty>
            </html:select>
          </td>
          <td align="center" style="border: 0px;">
            <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('addEditUser');"><br><br>
            <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('deleteEditUser');">
          </td>
          <td rowspan="3" align="left" style="border: 0px;">
            <html:select name="sch240Form" property="sch240accessUserNoSelect" size="24" multiple="true" styleClass="select01" style="width:220px;">
            <logic:notEmpty name="sch240Form" property="sch240accessNoSelectCombo">
            <html:optionsCollection name="sch240Form" property="sch240accessNoSelectCombo" value="value" label="label" />
            </logic:notEmpty>
            </html:select>
          </td>
          </tr>

          <tr>
          <td width="40%" class="td_sub_title3" align="center"><span class="text_bb1"><gsmsg:write key="cmn.reading" /></span></td>
          <td width="20%" align="center" style="border: 0px;">&nbsp;</td>
          </tr>

          <tr>
          <td align="center">
            <html:select name="sch240Form" property="sch240accessUserSelect" size="10" multiple="true" styleClass="select01" style="width:220px;">
            <logic:notEmpty name="sch240Form" property="sch240accessSelectCombo">
            <html:optionsCollection name="sch240Form" property="sch240accessSelectCombo" value="value" label="label" />
            </logic:notEmpty>
            </html:select>
          </td>

          <td align="center" style="border: 0px;">
            <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('addAccessUser');"><br><br>
            <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('deleteAccessUser');">
          </td>
          </tr>

          </table>

        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.memo" /></span></td>
        <td align="left" class="webmail_td1">
          <html:textarea name="sch240Form" property="sch240biko"  rows="10" style="width:572px;" />
        </td>
      </tr>

    </table>
  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="<gsmsg:write key="cmn.spacer" />">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="100%" align="right">
          <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('confirm');">
          <% if (editMode == 1) { %>
            <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('deleteAccess');">
          <% } %>
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('beforePage');">
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