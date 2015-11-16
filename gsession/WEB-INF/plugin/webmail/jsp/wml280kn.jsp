<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ page import="jp.groupsession.v2.wml.wml280kn.Wml280knForm" %>
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
<title>[Group Session] <gsmsg:write key="wml.wml280.01" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <link rel="stylesheet" href="../webmail/css/webmail.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
  <script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../webmail/js/assets/editorSubWindow.js?<%= GSConst.VERSION_PARAM %>"></script>

</head>

<html:form action="/webmail/wml280kn">
<logic:equal name="wml280knForm" property="wmlCmdMode" value="<%= cmdModeAdd %>">
  <input type="hidden" name="helpPrm" value="0">
</logic:equal>
<logic:equal name="wml280knForm" property="wmlCmdMode" value="<%= cmdModeEdit %>">
 <input type="hidden" name="helpPrm" value="2">
</logic:equal>

<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<%@ include file="/WEB-INF/plugin/webmail/jsp/wml010_hiddenParams.jsp" %>
<html:hidden property="wmlCmdMode" />

<html:hidden property="wmlViewAccount" />
<html:hidden property="wmlAccountMode" />
<html:hidden property="wmlAccountSid" />
<html:hidden property="wmlEditDestList" />
<html:hidden property="wml270keyword" />
<html:hidden property="wml270pageTop" />
<html:hidden property="wml270pageBottom" />
<html:hidden property="wml270pageDspFlg" />
<html:hidden property="wml270svKeyword" />
<html:hidden property="wml270sortKey" />
<html:hidden property="wml270order" />
<html:hidden property="wml270searchFlg" />
<html:hidden property="wml280initFlg" />
<html:hidden property="wml280name" />
<html:hidden property="wml280biko" />
<html:hidden property="wml280knMode" />

<logic:notEmpty name="wml280knForm" property="wml280destUser">
<logic:iterate id="destUser" name="wml280knForm" property="wml280destUser">
  <input type="hidden" name="wml280destUser" value="<bean:write name="destUser" />">
</logic:iterate>
</logic:notEmpty>
</span>

<logic:notEmpty name="wml280knForm" property="wml280destAddress">
<logic:iterate id="destAddress" name="wml280knForm" property="wml280destAddress">
  <input type="hidden" name="wml280destAddress" value="<bean:write name="destAddress" />">
</logic:iterate>
</logic:notEmpty>

<logic:iterate id="selectDestList" name="wml280knForm" property="wml280accessFull">
  <input type="hidden" name="wml280accessFull" value="<bean:write name="selectDestList" />">
</logic:iterate>

<logic:notEmpty name="wml280knForm" property="wml280accessRead">
<logic:iterate id="selectDestList" name="wml280knForm" property="wml280accessRead">
  <input type="hidden" name="wml280accessRead" value="<bean:write name="selectDestList" />">
</logic:iterate>
</logic:notEmpty>

<bean:define id="wmlCmdMode" name="wml280knForm" property="wmlCmdMode" type="java.lang.Integer" />
<bean:define id="acctMode" name="wml280knForm" property="wmlAccountMode" type="java.lang.Integer" />
<bean:define id="wml280knViewMode" name="wml280knForm" property="wml280knMode" type="java.lang.Integer" />
<% int cmdMode = wmlCmdMode.intValue(); %>
<% int accountMode = acctMode.intValue(); %>
<% int viewMode = wml280knViewMode.intValue(); %>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="80%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <% if (accountMode == 1) { %>
          <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt=""></td>
          <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
        <% } else { %>
          <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt=""></td>
          <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <% } %>
        <% if (viewMode == 1) { %>
          <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="wml.wml280kn.03" /> ]</td>
        <% } else if (cmdMode == 0) { %>
          <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="wml.wml280kn.01" /> ]</td>
        <% } else if (cmdMode == 1) { %>
          <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="wml.wml280kn.02" /> ]</td>
        <% } %>
          <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <logic:notEqual name="wml280knForm" property="wml280knMode" value="1">
            <input type="button" value="<gsmsg:write key="man.final" />" class="btn_base1" onClick="buttonPush('decision');">
          </logic:notEqual>
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
        <td class="table_bg_A5B4E1" width="200" nowrap><span class="text_bb1"><gsmsg:write key="wml.263" /></span></td>
        <td align="left" class="webmail_td1" width="800">
          <bean:write name="wml280knForm" property="wml280name" />
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.memo" /></span></td>
        <td align="left" class="webmail_td1">
          <bean:write name="wml280knForm" property="wml280knBiko" filter="false" />
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="anp.send.dest" /></span></td>
        <td align="left" class="webmail_td1">
          <table class="tl0" boreder="0" width="99%">
            <tr>
              <td class="table_bg_A5B4E1" align="center" width="45%"><span class="text_bb1"><gsmsg:write key="project.68" /></span></td>
              <td class="table_bg_A5B4E1" align="center" width="55%"><span class="text_bb1"><gsmsg:write key="cmn.mailaddress" /></span></td>
            </tr>

            <logic:notEmpty name="wml280knForm" property="destUserList">
            <logic:iterate id="destUserData" name="wml280knForm" property="destUserList">
            <tr>
              <td class="td_type1">
                <span class="text_base"><bean:write name="destUserData" property="name" /></span>
                <logic:notEmpty name="destUserData" property="acoName">
                  <br><br>
                  <span class="text_base">
                    &nbsp;<gsmsg:write key="address.139" />: <bean:write name="destUserData" property="acoName" />
                    <logic:notEmpty name="destUserData" property="abaName">
                      <br>&nbsp;<gsmsg:write key="address.10" />: <bean:write name="destUserData" property="abaName" />
                    </logic:notEmpty>
                  </span>
                </logic:notEmpty>
              </td>
              <td valign="middle">
                <span class="text_base"><bean:write name="destUserData" property="mailAddress" /></span>
              </td>
            </tr>
            </logic:iterate>
            </logic:notEmpty>
          </table>
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="fil.102" /></span></td>
        <td align="left" class="webmail_td1">

          <table width="60%" cellpadding="0" cellspacing="5" border="0" class="tl_u2">
          <logic:notEmpty name="wml280knForm" property="wml280accessFullSelectCombo">
          <tr>
          <td width="40%" class="td_sub_title3" align="center"><span class="text_bb1"><gsmsg:write key="cmn.add.edit.delete" /></span></td>
          </tr>
          <tr>
          <td align="left" class="td_wt">
          <logic:iterate id="accessFull" name="wml280knForm" property="wml280accessFullSelectCombo" scope="request">
          <span class="text_base"><bean:write name="accessFull" property="label" /></span><br>
          </logic:iterate>
          </td>
          </tr>
          </logic:notEmpty>

          <logic:notEmpty name="wml280knForm" property="wml280accessReadSelectCombo" scope="request">
          <tr>
          <td width="40%" class="td_sub_title3" align="center"><span class="text_bb1"><gsmsg:write key="cmn.reading" /></span></td>
          </tr>
          <tr>
          <td align="left" class="td_wt">
          <logic:iterate id="accessRead" name="wml280knForm" property="wml280accessReadSelectCombo" scope="request">
          <span class="text_base"><bean:write name="accessRead" property="label" /></span><br>
          </logic:iterate>
          </td>
          </tr>
          </logic:notEmpty>

          </table>

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
          <logic:notEqual name="wml280knForm" property="wml280knMode" value="1">
            <input type="button" value="<gsmsg:write key="man.final" />" class="btn_base1" onClick="buttonPush('decision');">
          </logic:notEqual>
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