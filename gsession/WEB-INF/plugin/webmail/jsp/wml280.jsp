<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ page import="jp.groupsession.v2.wml.wml280.Wml280Form" %>
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

<html:form action="/webmail/wml280">
<logic:equal name="wml280Form" property="wmlCmdMode" value="<%= cmdModeAdd %>">
  <input type="hidden" name="helpPrm" value="0">
</logic:equal>
<logic:equal name="wml280Form" property="wmlCmdMode" value="<%= cmdModeEdit %>">
 <input type="hidden" name="helpPrm" value="2">
</logic:equal>

<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<%@ include file="/WEB-INF/plugin/webmail/jsp/wml010_hiddenParams.jsp" %>
<html:hidden property="wmlCmdMode" />
<bean:define id="wmlCmdMode" name="wml280Form" property="wmlCmdMode" type="java.lang.Integer" />
<bean:define id="acctMode" name="wml280Form" property="wmlAccountMode" type="java.lang.Integer" />
<% int cmdMode = wmlCmdMode.intValue(); %>
<% int accountMode = acctMode.intValue(); %>

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

<span id="wml280destUserArea">
<logic:notEmpty name="wml280Form" property="wml280destUser">
<logic:iterate id="destUser" name="wml280Form" property="wml280destUser">
  <input type="hidden" name="wml280destUser" value="<bean:write name="destUser" />">
</logic:iterate>
</logic:notEmpty>
</span>

<span id="wml280destAddressArea">
<logic:notEmpty name="wml280Form" property="wml280destAddress">
<logic:iterate id="destAddress" name="wml280Form" property="wml280destAddress">
  <input type="hidden" name="wml280destAddress" value="<bean:write name="destAddress" />">
</logic:iterate>
</logic:notEmpty>
</span>

<logic:notEmpty name="wml280Form" property="wml280accessFull">
<logic:iterate id="selectDestList" name="wml280Form" property="wml280accessFull">
  <input type="hidden" name="wml280accessFull" value="<bean:write name="selectDestList" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="wml280Form" property="wml280accessRead">
<logic:iterate id="selectDestList" name="wml280Form" property="wml280accessRead">
  <input type="hidden" name="wml280accessRead" value="<bean:write name="selectDestList" />">
</logic:iterate>
</logic:notEmpty>

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
        <% if (cmdMode == 0) { %>
          <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="wml.wml280.01" /> ]</td>
        <% } else if (cmdMode == 1) { %>
          <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="wml.wml280.02" /> ]</td>
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
          <logic:equal name="wml280Form" property="wmlCmdMode" value="1">
            <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('deleteDestList');">
          </logic:equal>
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
        <td class="table_bg_A5B4E1" width="200" nowrap><span class="text_bb1"><gsmsg:write key="wml.263" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
        <td align="left" class="td_wt" width="800">
          <html:text name="wml280Form" property="wml280name" maxlength="100" style="width:273px;"/>
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.memo" /></span></td>
        <td align="left" class="webmail_td1">
          <html:textarea name="wml280Form" property="wml280biko"  rows="10" style="width:572px;" />
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="anp.send.dest" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
        <td align="left" class="webmail_td1">
          <input type="button" name="btn_add" class="btn_add_n1" value="<gsmsg:write key="cmn.add" />" onClick="openSyainPlus('wml280user');">
          <input type="button" name="btn_delete" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onClick="buttonPush('deleteDestUser');">

          <table width="99%">
            <tr>
              <td class="table_bg_A5B4E1" align="center" width="1%"></td>
              <td class="table_bg_A5B4E1" align="center" width="44%"><span class="text_bb1"><gsmsg:write key="cmn.name" /></span></td>
              <td class="table_bg_A5B4E1" align="center" width="55%"><span class="text_bb1"><gsmsg:write key="cmn.mailaddress" /></span></td>
            </tr>

            <logic:notEmpty name="wml280Form" property="destUserList">
            <logic:iterate id="destUserData" name="wml280Form" property="destUserList">
            <tr>
              <td class="td_type1">
                <html:multibox name="wml280Form" property="wml280destUserSelect">
                  <bean:write name="destUserData" property="destId" />
                </html:multibox>
              </td>
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
              <td valign="td_type1">
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
          <table width="100%" border="0">
          <tr>
          <td width="40%" class="td_sub_title3" align="center"><span class="text_bb1"><gsmsg:write key="cmn.add.edit.delete" /></span><span class="text_r2">※</span></td>
          <td width="20%" align="center" style="border: 0px;">&nbsp;</td>

          <td width="40%" align="left" style="border: 0px;">
<!--
          <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup2(this.form.wml280accessGroup, 'wml280accessGroup', '<bean:write name="wml280Form" property="wml280accessGroup" />', '0', 'changeGrp', 'wml280accessFullSelect', 'wml280accessReadSelect', 'webmail', '-1', '1')" type="button"><br>
-->
          <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup2(this.form.wml280accessGroup, 'wml280accessGroup', '<bean:write name="wml280Form" property="wml280accessGroup" />', '0', 'changeGrp', 'wml280accessFull', 'wml280accessRead', 'webmail', '-1', '1')" type="button"><br>
          <html:select name="wml280Form" property="wml280accessGroup" styleClass="select01" onchange="return buttonPush('changeGrp');" style="width: 150px;">
          <logic:notEmpty name="wml280Form" property="groupCombo">
          <html:optionsCollection name="wml280Form" property="groupCombo" value="value" label="label" />
          </logic:notEmpty>
          </html:select>
          <input type="button" onclick="openGroupWindow(this.form.wml280accessGroup, 'wml280accessGroup', '0', 'changeGrp')" class="group_btn2" value="&nbsp;&nbsp;" id="fil030GroupBtn">
          </td>
          </tr>

          <tr>
          <td align="center">
          <html:select name="wml280Form" property="wml280accessFullSelect" size="5" multiple="true" styleClass="select01" style="width:220px;">
          <logic:notEmpty name="wml280Form" property="wml280accessFullSelectCombo">
          <html:optionsCollection name="wml280Form" property="wml280accessFullSelectCombo" value="value" label="label" />
          </logic:notEmpty>
          <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
          </html:select>
          </td>

          <td align="center" style="border: 0px;">
          <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('addAuthEdit');">
          <br><br>
          <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('deleteAuthEdit');">
          </td>

          <td valign="top" rowspan="3" style="border: 0px;">
          <html:select name="wml280Form" property="wml280accessNoSelect" size="13" multiple="true" styleClass="select01" style="width:220px;">
          <logic:notEmpty name="wml280Form" property="wml280accessNoSelectCombo">
          <html:optionsCollection name="wml280Form" property="wml280accessNoSelectCombo" value="value" label="label" />
          </logic:notEmpty>
          <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
          </html:select>
          </td>
          </tr>

          <tr>
          <td width="40%" class="td_sub_title3" align="center"><span class="text_bb1"><gsmsg:write key="cmn.reading" /></span></td>
          <td width="20%" align="center" style="border: 0px;">&nbsp;</td>
          </tr>

          <tr>
          <td align="center" valign="top">
          <html:select name="wml280Form" property="wml280accessReadSelect" size="5" multiple="true" styleClass="select01" style="width:220px;">
          <logic:notEmpty name="wml280Form" property="wml280accessReadSelectCombo">
          <html:optionsCollection name="wml280Form" property="wml280accessReadSelectCombo" value="value" label="label" />
          </logic:notEmpty>
          <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
          </html:select>
          </td>

          <td align="center" style="border: 0px;">
          <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('addAuthRead');"><br><br>
          <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('deleteAuthRead');">
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
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="<gsmsg:write key="cmn.spacer" />">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="100%" align="right">
          <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('confirm');">
          <logic:equal name="wml280Form" property="wmlCmdMode" value="1">
            <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('deleteDestList');">
          </logic:equal>
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