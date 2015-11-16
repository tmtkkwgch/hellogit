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
<script language="JavaScript" src="../bookmark/js/bmk050.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../bookmark/css/bookmark.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="bmk.43" /></title>
</head>

<body class="body_03">
<html:form action="/bookmark/bmk050">

<input type="hidden" name="CMD" value="">

<html:hidden property="bmk050LblSid" />
<html:hidden property="bmk050ProcMode" />

<logic:notEmpty name="bmk050Form" property="bmk010delInfSid" scope="request">
<logic:iterate id="item" name="bmk050Form" property="bmk010delInfSid" scope="request">
  <input type="hidden" name="bmk010delInfSid" value="<bean:write name="item"/>">
</logic:iterate>
</logic:notEmpty>


<%@ include file="/WEB-INF/plugin/bookmark/jsp/bmk010_hiddenParams.jsp" %>
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
    <td width="0%"><img src="../bookmark/images/header_link01.gif" border="0" alt="<gsmsg:write key="bmk.43" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="bmk.43" /></span></td>
    <logic:equal name="bmk050Form" property="bmk010mode" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KOJIN) %>">
    <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="cmn.label.management" />(<gsmsg:write key="bmk.30" />) ]</td>
    </logic:equal>
    <logic:equal name="bmk050Form" property="bmk010mode" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_GROUP) %>">
    <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="cmn.label.management" />(<gsmsg:write key="bmk.51" />) ]</td>
    </logic:equal>
    <logic:equal name="bmk050Form" property="bmk010mode" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KYOYU) %>">
    <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="cmn.label.management" />(<gsmsg:write key="bmk.34" />) ]</td>
    </logic:equal>

    <td width="100%" class="header_white_bg">
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="bmk.43" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" class="btn_add_n1" value="<gsmsg:write key="cmn.add" />" onClick="return buttonSubmit('bmk050add','-1', '0');">
    <input type="button" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onClick="return buttonPush('bmk050del');">
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('bmk050back');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    </td>
    </tr>
    <logic:messagesPresent message="false">
    <tr><td width="100%">
    <html:errors />
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
    </td></tr>
    </logic:messagesPresent>
    <tr>
    <td>
    <% jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage(); %>
    <% String targetLabel = ""; %>
    <logic:equal name="bmk050Form" property="bmk010mode" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KOJIN) %>">
    <% targetLabel = "<span class=\"attent1\">" + gsMsg.getMessage(request, "bmk.30") + "</span>"; %>
    <gsmsg:write key="bmk.bmk050.03" arg0="<%= targetLabel %>" />
    </logic:equal>
    <logic:equal name="bmk050Form" property="bmk010mode" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_GROUP) %>">
    <bean:define id="grpNameStr" name="bmk050Form" property="bmk050GrpName" type="java.lang.String" />
    <% targetLabel = "<span class=\"attent1\">" + gsMsg.getMessage(request, "cmn.group") + gsMsg.getMessage(request, "wml.215") + grpNameStr + "</span>"; %>
    <gsmsg:write key="bmk.bmk050.03" arg0="<%= targetLabel %>" />
    </logic:equal>
    <logic:equal name="bmk050Form" property="bmk010mode" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KYOYU) %>">
    <% targetLabel = "<span class=\"attent1\">" + gsMsg.getMessage(request, "bmk.34") + "</span>"; %>
    <gsmsg:write key="bmk.bmk050.03" arg0="<%= targetLabel %>" />
    </logic:equal>
    </td>
    </tr>
    <tr>
    <td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td>
    </tr>
    <tr>
    <td>
    </td>
    </tr>
    <tr>
    <td>

    <table class="tl0 table_td_border2" cellpadding="5" cellspacing="0" border="0" width="100%">

    <tr>
    <th align="center" class="table_bg_7D91BD" width="80%"><span class="text_tlw"><gsmsg:write key="cmn.label" /></span></th>
    <th align="center" class="table_bg_7D91BD" width="10%"><span class="text_tlw"><gsmsg:write key="bmk.bmk050.01" /></span></th>
    <th class="table_bg_7D91BD" width="10%" nowrap><span class="text_tlw"><gsmsg:write key="cmn.delete" /></span></th>
    </tr>

    <logic:notEmpty name="bmk050Form" property="bmk050LabelList">
    <bean:define id="tdColor" value="" />

    <% String[] tdColors = new String[] {"td_type1", "td_type_usr"}; %>

    <logic:iterate id="labelMdl" name="bmk050Form" property="bmk050LabelList" indexId="idx">
    <% tdColor = tdColors[(idx.intValue() % 2)]; %>



    <tr align="center" class="<%= tdColor %>">

        <!-- ラベル名 -->
        <td align="left">
        <a href="#" onclick="return buttonSubmit('bmk050edit','<bean:write name="labelMdl" property="blbSid" />', '1') ">
        <span class="text_link"><bean:write name="labelMdl" property="blbName" /></span>
        </a>
        </td>
        <!-- 数 -->
        <td align="center"><bean:write name="labelMdl" property="blbCnt" filter="false" /></td>
        <!-- チェックボックス -->
        <td align="center" nowrap>
        <bean:define id="sid" name="labelMdl" property="blbSid" type="java.lang.Integer" />
        <html:multibox property="bmk050DelSidList" value="<%= Integer.toString(sid.intValue()) %>"/>
        </td>

    </tr>

    </logic:iterate>

    </logic:notEmpty>
    </table>

  </td>
  </tr>
  <tr>
  <td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td>
  </tr>
  <tr>
  <td>
    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
    <input type="button" class="btn_add_n1" value="<gsmsg:write key="cmn.add" />" onClick="return buttonSubmit('bmk050add','-1', '0');">
    <input type="button" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onClick="return buttonPush('bmk050del');">
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('bmk050back');">
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