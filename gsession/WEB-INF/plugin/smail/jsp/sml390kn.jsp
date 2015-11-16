<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">



<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../smail/js/smlaccountsel.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[GroupSession]
<logic:equal name="sml390knForm" property="sml380EditBan" value="0"><gsmsg:write key="sml.sml390.01" /></logic:equal>
<logic:notEqual name="sml390knForm" property="sml380EditBan" value="0"><gsmsg:write key="sml.sml390.02" /></logic:notEqual>
</title>
</head>

<body class="body_03">
<html:form action="/smail/sml390kn">
<input type="hidden" name="CMD" value="">

<html:hidden property="smlViewAccount" />
<html:hidden property="smlAccountSid" />
<html:hidden property="sml380EditBan" />
<html:hidden property="sml390sbcName" />
<html:hidden property="sml390biko" />
<html:hidden property="sml390initFlg" />

<logic:notEmpty name="sml390knForm" property="sml390sbpTarget" scope="request">
  <logic:iterate id="sid" name="sml390knForm" property="sml390sbpTarget" scope="request">
    <input type="hidden" name="sml390sbpTarget" value="<bean:write name="sid"/>">
  </logic:iterate>
</logic:notEmpty>
<html:hidden property="sml390post" />
<html:hidden property="sml390banGroup" />
<html:hidden property="sml390ableGroup" />

<html:hidden property="backScreen" />
<html:hidden property="sml010ProcMode" />
<html:hidden property="sml010Sort_key" />
<html:hidden property="sml010Order_key" />
<html:hidden property="sml010PageNum" />
<html:hidden property="sml010SelectedSid" />


<logic:notEmpty name="sml390knForm" property="sml010DelSid" scope="request">
  <logic:iterate id="del" name="sml390knForm" property="sml010DelSid" scope="request">
    <input type="hidden" name="sml010DelSid" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="sml380keyword" />
<html:hidden property="sml380svKeyword" />
<html:hidden property="sml380sortKey" />
<html:hidden property="sml380order" />
<html:hidden property="sml380searchFlg" />
<html:hidden property="sml380pageTop" />

<logic:equal name="sml390knForm" property="sml380EditBan" value="0">
  <input type="hidden" name="helpPrm" value="0">
</logic:equal>
<logic:notEqual name="sml390knForm" property="sml380EditBan" value="0">
  <input type="hidden" name="helpPrm" value="1">
</logic:notEqual>


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
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">
        <logic:equal name="sml390knForm" property="sml380EditBan" value="0">
            [ <gsmsg:write key="sml.sml390kn.01" /> ]
        </logic:equal>
        <logic:notEqual name="sml390knForm" property="sml380EditBan" value="0">
            [ <gsmsg:write key="sml.sml390kn.02" /> ]
        </logic:notEqual>

        </td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt="<gsmsg:write key='cmn.function.btn' />"></td>
        <td class="header_glay_bg" width="50%">
             <input type="button" name="btn_add" class="btn_base1" value="<gsmsg:write key="cmn.final" />" onClick="buttonPush('decision');">
             <input type="button" name="btn_facilities_mnt" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backInput');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt="<gsmsg:write key='cmn.function.btn' />"></td>
      </tr>
    </table>

    <img src="../common/images/spacer.gif" alt="<gsmsg:write key="cmn.spacer" />" height="10px" width="10px">
    <html:errors />
    <img src="../common/images/spacer.gif" alt="<gsmsg:write key="cmn.spacer" />" height="10px" width="10px">

    <table width="100%" class="tl0" border="0" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><gsmsg:write key="sml.sml380.03" /></span></td>
    <td align="left" width="90%" class="webmail_td1">
        <bean:write name="sml390knForm" property="sml390sbcName" />
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="sml.sml380.04" /></span></td>
    <td align="left" class="webmail_td1">
            <logic:iterate id="banAccountData" name="sml390knForm" property="sml390sbdTargetSelectCombo">
            <div class="atesaki_to_area">
                <input type="hidden" name="sml390sbdTarget" value="<bean:write name="banAccountData" property="value" />">
                <bean:write name="banAccountData" property="label" />
            </div>
            </logic:iterate>
            <logic:iterate id="banAccountData" name="sml390knForm" property="sml390sbdTargetAccSelectCombo">
            <div class="atesaki_to_area">
                <input type="hidden" name="sml390sbdTargetAcc" value="<bean:write name="banAccountData" property="value" />">
                <bean:write name="banAccountData" property="label" />
            </div>
            </logic:iterate>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="sml.sml390.03" /></span></td>
    <td align="left" class="webmail_td1">
         <gsmsg:write key="cmn.post" />:<bean:write name="sml390knForm" property="sml390knDspPosition" /><p>
         <logic:iterate id="sml390sbpTarget" name="sml390knForm" property="sml390sbpTargetSelectCombo">
             <bean:write name="sml390sbpTarget" property="label" />
             <br>
         </logic:iterate>

    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.memo" /></span></td>
    <td align="left" class="webmail_td1">
        <bean:write name="sml390knForm" property="sml390knDspBiko" filter="false"/>
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
             <input type="button" name="btn_add" class="btn_base1" value="<gsmsg:write key="cmn.final" />" onClick="buttonPush('decision');">
             <input type="button" name="btn_facilities_mnt" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backInput');">
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