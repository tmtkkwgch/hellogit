<%@ page pageEncoding="Windows-31J" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<html:html>
<head>

<title>[GroupSession] <gsmsg:write key="cmn.preferences2" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../nippou/js/ntp095.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css' type='text/css'>
<link rel=stylesheet href='../nippou/css/nippou.css' type='text/css'>

</head>

<body class="body_03">
<html:form action="/nippou/ntp095">

<input type="hidden" name="CMD" value="">
<html:hidden property="dspMod" />
<html:hidden property="listMod" />
<html:hidden property="backScreen" />
<logic:notEmpty name="ntp095Form" property="ntp095memberSid">
<logic:iterate id="usid" name="ntp095Form" property="ntp095memberSid">
  <input type="hidden" name="ntp095memberSid" value="<bean:write name="usid" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="ntp095InitFlg" />
<html:hidden property="ntp095NtpDspKbn" />
<html:hidden property="ntp095CmtDspKbn" />
<html:hidden property="ntp095GoodDspKbn" />
<html:hidden property="ntp010DspDate"/>
<html:hidden property="ntp010SelectUsrSid"/>
<html:hidden property="ntp010SelectUsrKbn"/>
<html:hidden property="ntp010SelectDate" />
<html:hidden property="ntp010NipSid" />
<html:hidden property="ntp010DspGpSid"/>
<html:hidden property="ntp020SelectUsrSid"/>
<html:hidden property="ntp030SelectUsrSid"/>

<html:hidden property="ntp100PageNum" />
<html:hidden property="ntp100Slt_page1" />
<html:hidden property="ntp100Slt_page2" />
<html:hidden property="ntp100OrderKey1" />
<html:hidden property="ntp100SortKey1" />
<html:hidden property="ntp100OrderKey2" />
<html:hidden property="ntp100SortKey2" />

<html:hidden property="ntp100SvSltGroup" />
<html:hidden property="ntp100SvSltUser" />
<html:hidden property="ntp100SvSltYearFr" />
<html:hidden property="ntp100SvSltMonthFr" />
<html:hidden property="ntp100SvSltDayFr" />
<html:hidden property="ntp100SvSltYearTo" />
<html:hidden property="ntp100SvSltMonthTo" />
<html:hidden property="ntp100SvSltDayTo" />
<html:hidden property="ntp100SvKeyWordkbn" />
<html:hidden property="ntp100SvKeyValue" />
<html:hidden property="ntp100SvOrderKey1" />
<html:hidden property="ntp100SvSortKey1" />
<html:hidden property="ntp100SvOrderKey2" />
<html:hidden property="ntp100SvSortKey2" />

<html:hidden property="ntp100SltGroup" />
<html:hidden property="ntp100SltUser" />
<html:hidden property="ntp100SltYearFr" />
<html:hidden property="ntp100SltMonthFr" />
<html:hidden property="ntp100SltDayFr" />
<html:hidden property="ntp100SltYearTo" />
<html:hidden property="ntp100SltMonthTo" />
<html:hidden property="ntp100SltDayTo" />
<html:hidden property="ntp100KeyWordkbn" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>


<!--　BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <!-- タイトル -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    <td width="100%" class="header_ktool_bg_text">[ <gsmsg:write key="ntp.1" />　<gsmsg:write key="cmn.sml.notification.setting" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('ntp095ok');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('ntp095back');"></td>
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

    <table cellpadding="10" cellspacing="0" border="0" width="100%" class="tl_u2">

    <logic:equal name="ntp095Form" property="ntp095NtpDspKbn" value="1">
      <tr>
      <td width="0%" valign="middle" align="left" class="table_bg_A5B4E1" rowspan="2" nowrap>
        <span class="text_bb1"><gsmsg:write key="ntp.88" /></span><span class="text_r2">※</span>
      </td>
      <td align="left" class="td_type20" width="100%">

        <table width="100%" cellpadding="0" cellspacing="0" border="0">
        <tr>
        <td align="left" width="100%">
        <html:radio name="ntp095Form" styleId="ntp095Smail_02" property="ntp095Smail" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.SMAIL_NOT_USE) %>" /><label for="ntp095Smail_02"><span class="text_base"><gsmsg:write key="cmn.dont.notify" /></span></label>&nbsp;
        <html:radio name="ntp095Form" styleId="ntp095Smail_01" property="ntp095Smail" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.SMAIL_USE) %>" /><label for="ntp095Smail_01"><span class="text_base"><gsmsg:write key="cmn.notify" /></span></label>
        </td>
        </tr>
        </table>
      </td>
      </tr>

      <tr>
        <td align="left" class="td_type20" width="100%" id="ntpNoticeUsrArea">

          <span class="text_r1">  ※<gsmsg:write key="ntp.114" /></span>
          <br>
          <table width="0%" border="0">
          <tr>
          <td width="35%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="ntp.162" /></span></td>
          <td width="20%" align="center">&nbsp;</td>
          <td width="35%" align="left">
            <logic:notEqual name="ntp095Form" property="ntp095groupSid" value="-9">
              <logic:equal name="ntp095Form" property="ntp095KyoyuKbn" value="0">
                <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup(this.form.ntp095groupSid, 'ntp095groupSid', '<bean:write name="ntp095Form" property="ntp095groupSid" />', '1', '', 'ntp095memberSid', '-1', '1')" type="button"></br>
              </logic:equal>
            </logic:notEqual>
            <html:select property="ntp095groupSid" styleClass="select01" onchange="selectGroup();">

    <%--
              <logic:notEmpty name="ntp095Form" property="ntp095GroupList">
              <html:optionsCollection name="ntp095Form" property="ntp095GroupList" value="value" label="label" />
              </logic:notEmpty>
    --%>

              <logic:notEmpty name="ntp095Form" property="ntp095GroupList" scope="request">
              <logic:iterate id="gpBean" name="ntp095Form" property="ntp095GroupList" scope="request">

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
          </td>
          <td width="10%" align="left" valign="bottom">
          <logic:equal name="ntp095Form" property="ntp095KyoyuKbn" value="0">
            <input type="button" onclick="openGroupWindow(this.form.ntp095groupSid, 'ntp095groupSid', '1', 'changeGrp')" class="group_btn2" value="&nbsp;&nbsp;" id="ntp095GroupBtn">
          </logic:equal>
          </td>
          </tr>

          <tr>
          <td align="center">
            <html:select property="ntp095SelectLeft" size="7" styleClass="select01" multiple="true">

              <logic:notEmpty name="ntp095Form" property="ntp095LeftList" scope="request">
              <logic:iterate id="gplBean" name="ntp095Form" property="ntp095LeftList" scope="request">

                <bean:define id="gplValue" name="gplBean" property="value" type="java.lang.String" />
                <logic:equal name="gplBean" property="styleClass" value="0">
                <html:option value="<%= gplValue %>"><bean:write name="gplBean" property="label" /></html:option>
                </logic:equal>

                <logic:notEqual name="gplBean" property="styleClass" value="0">
                <html:option styleClass="select03" value="<%= gplValue %>"><bean:write name="gplBean" property="label" /></html:option>
                </logic:notEqual>

              </logic:iterate>
              </logic:notEmpty>

            </html:select>
          </td>
          <td align="center">
            <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('add');"><br><br>
            <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('remove');">
          </td>
          <td>
            <html:select property="ntp095SelectRight" size="7" styleClass="select01" multiple="true">

              <logic:notEmpty name="ntp095Form" property="ntp095RightList" scope="request">
              <logic:iterate id="gprBean" name="ntp095Form" property="ntp095RightList" scope="request">

                <bean:define id="gprValue" name="gprBean" property="value" type="java.lang.String" />
                <logic:equal name="gprBean" property="styleClass" value="0">
                <html:option value="<%= gprValue %>"><bean:write name="gprBean" property="label" /></html:option>
                </logic:equal>

                <logic:notEqual name="gprBean" property="styleClass" value="0">
                <html:option styleClass="select03" value="<%= gprValue %>"><bean:write name="gprBean" property="label" /></html:option>
                </logic:notEqual>

              </logic:iterate>
              </logic:notEmpty>

            </html:select>
          </td>
          </tr>
          </table>

        </td>
      </tr>
    </logic:equal>

    <logic:equal name="ntp095Form" property="ntp095CmtDspKbn" value="1">
      <tr>
      <td width="0%" valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
        <span class="text_bb1"><gsmsg:write key="ntp.89" /></span><span class="text_r2">※</span>
      </td>
      <td align="left" class="td_type20" width="100%">

        <table width="100%" cellpadding="0" cellspacing="0" border="0">
        <tr>
        <td align="left" width="100%">
        <html:radio name="ntp095Form" styleId="ntp095CmtSmail_02" property="ntp095CmtSmail" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.SMAIL_NOT_USE) %>" /><label for="ntp095CmtSmail_02"><span class="text_base"><gsmsg:write key="cmn.dont.notify" /></span></label>&nbsp;
        <html:radio name="ntp095Form" styleId="ntp095CmtSmail_01" property="ntp095CmtSmail" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.SMAIL_USE) %>" /><label for="ntp095CmtSmail_01"><span class="text_base"><gsmsg:write key="cmn.notify" /></span></label>
        </td>
        </tr>
        </table>

      </td>
      </tr>
    </logic:equal>

    <logic:equal name="ntp095Form" property="ntp095GoodDspKbn" value="1">
      <tr>
      <td width="0%" valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
        <span class="text_bb1"><gsmsg:write key="ntp.9" /></span><span class="text_r2">※</span>
      </td>
      <td align="left" class="td_type20" width="100%">

        <table width="100%" cellpadding="0" cellspacing="0" border="0">
        <tr>
        <td align="left" width="100%">
        <html:radio name="ntp095Form" styleId="ntp095GoodSmail_02" property="ntp095GoodSmail" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.SMAIL_NOT_USE) %>" /><label for="ntp095GoodSmail_02"><span class="text_base"><gsmsg:write key="cmn.dont.notify" /></span></label>&nbsp;
        <html:radio name="ntp095Form" styleId="ntp095GoodSmail_01" property="ntp095GoodSmail" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.SMAIL_USE) %>" /><label for="ntp095GoodSmail_01"><span class="text_base"><gsmsg:write key="cmn.notify" /></span></label>
        </td>
        </tr>
        </table>

      </td>
      </tr>
    </logic:equal>

    </table>



    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('ntp095ok');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('ntp095back');">
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