<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<%

    String sts_sonota      = String.valueOf(jp.groupsession.v2.cmn.GSConst.UIOSTS_ETC);
    String sts_zaiseki     = String.valueOf(jp.groupsession.v2.cmn.GSConst.UIOSTS_IN);
    String sts_huzai       = String.valueOf(jp.groupsession.v2.cmn.GSConst.UIOSTS_LEAVE);

%>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.zaiseki.management" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script language="JavaScript" src="../zaiseki/js/zskmain.js?<%= GSConst.VERSION_PARAM %>"></script>

<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../zaiseki/css/zaiseki.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">
<html:form action="/zaiseki/zskmain">

<input type="hidden" name="CMD" value="zskEdit">
<input type="hidden" name="SONOTA" value="<%= sts_sonota %>">
<input type="hidden" name="ZAISEKI" value="<%= sts_zaiseki %>">
<input type="hidden" name="HUZAI" value="<%= sts_huzai %>">


<!--  html:hidden property="sml010SelectedSid" / -->
<!--  html:hidden property="sml010ProcMode" / -->

    <table class="tl0" width="100%" cellspacing="0" cellpadding="0">
    <tr>
    <td align="left" class="header_7D91BD_right">
      <img src="../zaiseki/images/menu_icon_single.gif" class="img_bottom img_border img_menu_icon_single" alt="<gsmsg:write key="cmn.zaiseki.management" />"><a href="<bean:write name="zskmainForm" property="zskTopUrl" />"><gsmsg:write key="zsk.zskmain.01" /></a>
    </td>
    </tr>

    <tr>
    <logic:equal name="zskmainForm" property="zskUioStatus" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.UIOSTS_IN) %>">
      <bean:define id="zskColor" value="td_type0" />
    </logic:equal>
    <logic:equal name="zskmainForm" property="zskUioStatus" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.UIOSTS_LEAVE) %>">
      <bean:define id="zskColor" value="td_type_gaisyutu2" />
    </logic:equal>
    <logic:equal name="zskmainForm" property="zskUioStatus" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.UIOSTS_ETC) %>">
      <bean:define id="zskColor" value="td_type_kekkin2" />
    </logic:equal>

    <td>
      <table class="tl4" width="100%">
      <tr>
      <td class="<bean:write name="zskColor" />" align="left" nowrap>
      <span class="block_color_in"><html:radio name="zskmainForm" property="zskUioStatus" styleId="sts_zaiseki" value="<%= sts_zaiseki %>" /><span class="text_base2"><label for="sts_zaiseki" class="text_base2"><gsmsg:write key="cmn.zaiseki" /></label></span></span>
      <span class="block_color_leave"><html:radio name="zskmainForm" property="zskUioStatus" styleId="sts_huzai" value="<%= sts_huzai %>" /><span class="text_base2"><label for="sts_huzai" class="text_base2"><gsmsg:write key="cmn.absence" /></label></span></span>
      <span class="block_color_etc"><html:radio name="zskmainForm" property="zskUioStatus" styleId="sts_sonota" value="<%= sts_sonota %>"/><span class="text_base2"><label for="sts_sonota" class="text_base2"><gsmsg:write key="cmn.other" /></label></span></span>
      </td>
      </tr>
      <tr>
      <td class="<bean:write name="zskColor" />" align="left" nowrap>
      <html:text name="zskmainForm" property="zskUioBiko"  maxlength="30" style="width:243px;" />&nbsp;&nbsp;<input type="button" value="<gsmsg:write key="cmn.change" />" class="btn_base0_1" onClick="updateZsk('zskEdit');">
      </td>
      </tr>
      </table>
    </td>
    </tr>
    </table>

</html:form>

</body>
</html:html>