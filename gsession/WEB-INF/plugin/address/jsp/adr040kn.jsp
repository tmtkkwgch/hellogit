<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../address/css/address.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="cmn.addressbook" /></title>
</head>

<body class="body_03">
<html:form action="/address/adr040kn">
<input type="hidden" name="CMD" value="">

<html:hidden name="adr040knForm" property="backScreen" />
<html:hidden name="adr040knForm" property="adr040Pow1" />
<html:hidden name="adr040knForm" property="adr040Pow2" />
<html:hidden name="adr040knForm" property="adr040Pow3" />
<html:hidden name="adr040knForm" property="adr040Pow4" />
<html:hidden name="adr040knForm" property="adr040Pow5" />

<logic:notEmpty name="adr040knForm" property="adr010SearchTargetContact" scope="request">
  <logic:iterate id="target" name="adr040knForm" property="adr010SearchTargetContact" scope="request">
    <input type="hidden" name="adr010SearchTargetContact" value="<bean:write name="target"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="adr040knForm" property="adr010svSearchTargetContact" scope="request">
  <logic:iterate id="svTarget" name="adr040knForm" property="adr010svSearchTargetContact" scope="request">
    <input type="hidden" name="adr010svSearchTargetContact" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr040knForm" property="adr010selectSid">
<logic:iterate id="adrSid" name="adr040knForm" property="adr010selectSid">
  <input type="hidden" name="adr010selectSid" value="<bean:write name="adrSid" />">
</logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/address/jsp/adr010_hiddenParams.jsp" %>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.setting.permissions.kn" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.setting.permissions.kn" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.setting.permissions.kn" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" class="btn_base1" value="<gsmsg:write key="cmn.final" />" onClick="return buttonPush('adr040knkakutei');">
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('adr040knback');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td>
  </tr>

  <tr>
  <td>

    <table class="tl0" width="100%" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="20%"><span class="text_bb1"><gsmsg:write key="address.85" /></span></a></td>
    <td class="td_type1" width="82%">
    <logic:equal name="adr040knForm" property="adr040Pow5" value="1"><gsmsg:write key="cmn.only.admin.editable" /></logic:equal>
    <logic:equal name="adr040knForm" property="adr040Pow5" value="0"><gsmsg:write key="address.87" /></logic:equal>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="20%"><span class="text_bb1"><gsmsg:write key="address.88" /></span></a></td>
    <td class="td_type1" width="82%">
    <logic:equal name="adr040knForm" property="adr040Pow1" value="1"><gsmsg:write key="cmn.only.admin.editable" /></logic:equal>
    <logic:equal name="adr040knForm" property="adr040Pow1" value="0"><gsmsg:write key="address.89" /></logic:equal>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="address.90" /></span></a></td>
    <td class="td_type1">
    <logic:equal name="adr040knForm" property="adr040Pow2" value="1"><gsmsg:write key="cmn.only.admin.editable" /></logic:equal>
    <logic:equal name="adr040knForm" property="adr040Pow2" value="0"><gsmsg:write key="address.91" /></logic:equal>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="cmn.edit.permissions.label" /></span></a></td>
    <td class="td_type1">
    <logic:equal name="adr040knForm" property="adr040Pow3" value="1"><gsmsg:write key="cmn.only.admin.editable" /></logic:equal>
    <logic:equal name="adr040knForm" property="adr040Pow3" value="0"><gsmsg:write key="cmn.noset.edit.permissions.label" /></logic:equal>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="address.94" /></span></a></td>
    <td class="td_type1">
    <logic:equal name="adr040knForm" property="adr040Pow4" value="1"><gsmsg:write key="address.140" /></logic:equal>
    <logic:equal name="adr040knForm" property="adr040Pow4" value="0"><gsmsg:write key="address.95" /></logic:equal>
    </td>
    </tr>

    </table>

  </td>
  </tr>

  <tr>
  <td>

  <img src="../common/images/spacer.gif" style="width:1px; height:10px;" border="0" alt="">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
    <input type="button" class="btn_base1" value="<gsmsg:write key="cmn.final" />" onClick="return buttonPush('adr040knkakutei');">
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('adr040knback');">
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