<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%-- 定数値 --%>
<%
  String  acModeNormal    = String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.ACCOUNTMODE_NORMAL);
  String  acModePsn       = String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.ACCOUNTMODE_PSNLSETTING);
  String  acModeCommon    = String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.ACCOUNTMODE_COMMON);
  String  cmdModeAdd      = String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.CMDMODE_ADD);
  String  cmdModeEdit     = String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.CMDMODE_EDIT);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[Group Session] <gsmsg:write key="schedule.sch240kn.01" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <link rel="stylesheet" href="../webmail/css/webmail.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
  <script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>

</head>

<html:form action="/schedule/sch240kn">
<input type="hidden" name="CMD" value="">

<%@ include file="/WEB-INF/plugin/schedule/jsp/sch080_hiddenParams.jsp" %>

<logic:notEmpty name="sch240knForm" property="sch100SvSearchTarget" scope="request">
  <logic:iterate id="svTarget" name="sch240knForm" property="sch100SvSearchTarget" scope="request">
    <input type="hidden" name="sch100SvSearchTarget" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sch240knForm" property="sch100SearchTarget" scope="request">
  <logic:iterate id="target" name="sch240knForm" property="sch100SearchTarget" scope="request">
    <input type="hidden" name="sch100SearchTarget" value="<bean:write name="target"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sch240knForm" property="sch100SvBgcolor" scope="request">
  <logic:iterate id="svBgcolor" name="sch240knForm" property="sch100SvBgcolor" scope="request">
    <input type="hidden" name="sch100SvBgcolor" value="<bean:write name="svBgcolor"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sch240knForm" property="sch100Bgcolor" scope="request">
  <logic:iterate id="bgcolor" name="sch240knForm" property="sch100Bgcolor" scope="request">
    <input type="hidden" name="sch100Bgcolor" value="<bean:write name="bgcolor"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sch240knForm" property="sch100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="sch240knForm" property="sch100CsvOutField" scope="request">
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
<html:hidden property="sch240name" />
<html:hidden property="sch240biko" />
<html:hidden property="sch240position" />
<html:hidden property="sch240positionAuth" />
<html:hidden property="sch240accessGroup" />

<logic:equal name="sch240knForm" property="sch230editMode" value="0">
  <input type="hidden" name="helpPrm" value="0">
</logic:equal>
<logic:notEqual name="sch240knForm" property="sch230editMode" value="0">
  <input type="hidden" name="helpPrm" value="1">
</logic:notEqual>

<logic:notEmpty name="sch240knForm" property="sch240subject">
  <logic:iterate id="subject" name="sch240knForm" property="sch240subject">
    <input type="hidden" name="sch240subject" value="<bean:write name="subject"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sch240knForm" property="sch240editUser">
  <logic:iterate id="editUser" name="sch240knForm" property="sch240editUser">
    <input type="hidden" name="sch240editUser" value="<bean:write name="editUser"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sch240knForm" property="sch240accessUser">
  <logic:iterate id="accessUser" name="sch240knForm" property="sch240accessUser">
    <input type="hidden" name="sch240accessUser" value="<bean:write name="accessUser"/>">
  </logic:iterate>
</logic:notEmpty>

<bean:define id="schEditMode" name="sch240knForm" property="sch230editMode" type="java.lang.Integer" />
<%
  int editMode = schEditMode.intValue();
%>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="80%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
          <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt=""></td>
          <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <% if (editMode == 0) { %>
          <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="schedule.sch240kn.01" /> ]</td>
        <% } else if (editMode == 1) { %>
          <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="schedule.sch240kn.02" /> ]</td>
        <% } %>
          <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="<gsmsg:write key="man.final" />" class="btn_base1" onClick="buttonPush('decision');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backInput');">
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
        <td class="table_bg_A5B4E1" width="150" nowrap><span class="text_bb1"><gsmsg:write key="schedule.sch240.04" /></span></td>
        <td align="left" class="webmail_td1" width="850">
          <bean:write name="sch240knForm" property="sch240name" />
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="schedule.sch240.05" /></span></td>
        <td align="left" class="webmail_td1">
          <logic:notEmpty name="sch240knForm" property="sch240subjectSelectCombo">
          <logic:iterate id="subject" name="sch240knForm" property="sch240subjectSelectCombo" scope="request">
          <bean:write name="subject" property="label" /><br>
          </logic:iterate>
          </logic:notEmpty>
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.post" /></span></td>
        <td align="left" class="webmail_td1">
          <bean:write name="sch240knForm" property="sch240knpositionName" />
          <logic:greaterThan name="sch240knForm" property="sch240position" value="0">
            <br>
            <logic:equal name="sch240knForm" property="sch240positionAuth" value="0">
            <gsmsg:write key="cmn.add.edit.delete" />
            </logic:equal>
            <logic:equal name="sch240knForm" property="sch240positionAuth" value="1">
            <gsmsg:write key="cmn.reading" />
            </logic:equal>
          </td>
          </logic:greaterThan>
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="schedule.sch240.03" /></span></td>
        <td align="left" class="webmail_td1">

          <table width="60%" cellpadding="0" cellspacing="5" border="0" class="tl_u2">
          <tr>
          <td width="40%" class="td_sub_title3" align="center"><span class="text_bb1"><gsmsg:write key="cmn.add.edit.delete" /></span></td>
          </tr>
          <tr>
          <td align="left" class="td_type1">
            <logic:notEmpty name="sch240knForm" property="sch240editUserSelectCombo">
            <logic:iterate id="editUser" name="sch240knForm" property="sch240editUserSelectCombo" scope="request">
            <span class="text_base"><bean:write name="editUser" property="label" /></span><br>
            </logic:iterate>
            </logic:notEmpty>
            <logic:empty name="sch240knForm" property="sch240editUserSelectCombo">&nbsp;</logic:empty>
          </td>
          </tr>

          <tr>
          <td width="40%" class="td_sub_title3" align="center"><span class="text_bb1"><gsmsg:write key="cmn.reading" /></span></td>
          </tr>
          <tr>
          <td align="left" class="td_type1">
            <logic:notEmpty name="sch240knForm" property="sch240accessSelectCombo">
            <logic:iterate id="accessUser" name="sch240knForm" property="sch240accessSelectCombo" scope="request">
            <span class="text_base"><bean:write name="accessUser" property="label" /></span><br>
            </logic:iterate>
            </logic:notEmpty>
            <logic:empty name="sch240knForm" property="sch240accessSelectCombo">&nbsp;</logic:empty>
          </td>
          </tr>
          </table>

        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.memo" /></span></td>
        <td align="left" class="webmail_td1">
          <bean:write name="sch240knForm" property="sch240knBiko" filter="false" />
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
          <input type="button" value="<gsmsg:write key="man.final" />" class="btn_base1" onClick="buttonPush('decision');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backInput');">
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