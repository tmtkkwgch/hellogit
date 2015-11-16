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
<title>[GroupSession] <gsmsg:write key="bbs.9" /> <gsmsg:write key="portal.portal" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../bulletin/js/bbsptl010.js?<%= GSConst.VERSION_PARAM %>"></script>

<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../bulletin/css/bulletin.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<bean:define id="bbsBfiSid" name="bbsptl010Form" property="bbsPtlBfiSid" type="java.lang.Integer" />
<% String bbsFormId = "bbsptl010Form" + String.valueOf(bbsBfiSid.intValue()); %>

<bean:define id="bbsPageNum" name="bbsptl010Form" property="bbsPtl010page1" type="java.lang.Integer" />
<% String bbsPage = String.valueOf(bbsPageNum.intValue()); %>

</head>

<body class="body_03">
<html:form action="/bulletin/bbsptl010" styleId="<%= bbsFormId %>">
<input type="hidden" name="CMD" value="">
<input type="hidden" name="bbsptl010Form" proeprty="bbsPtl010page1">

<html:hidden property="bbsPtlBfiSid" />
<html:hidden property="bbsPtl010ItemId" />

    <!--掲示板-->
<logic:notEmpty name="bbsptl010Form" property="threadList">

    <table width="100%" class="tl0" cellspacing="0" cellpadding="0">
    <tr>
    <td align="left" class="header_7D91BD_left" colspan="4">
      <img src="../bulletin/images/menu_icon_single.gif" class="img_bottom img_border img_menu_icon_single" alt="<gsmsg:write key="cmn.bulletin" />"><a href="<bean:write name="bbsptl010Form" property="bbsTopUrl" />"><gsmsg:write key="cmn.bulletin" /></a>
      <span class="text_base3"> [ <bean:write name="bbsptl010Form" property="bbsPtlBfiName" /> ]</span>
    </td>
    </tr>

    <tr class="text_base2">
    <th class="td_type30" width="60%" scope="col" nowrap><gsmsg:write key="bbs.bbsMain.4" /></th>
    <th class="td_type30" width="15%" scope="col" nowrap><gsmsg:write key="cmn.contributor" /></th>
    <th class="td_type30" width="25%" scope="col" nowrap><gsmsg:write key="bbs.bbs060.3" /></th>
    </tr>

    <% String[] tdClassList = {"td_type1", "td_type25_2"}; %>

    <logic:iterate id="thdMdl" name="bbsptl010Form" property="threadList" indexId="index">

    <% String tdClass = tdClassList[index.intValue() % 2]; %>
    <% String titleClass = "text_link"; %>
    <logic:equal name="thdMdl" property="readFlg" value="1">
      <% titleClass = "text_title_main"; %>
    </logic:equal>
    <bean:define id="userJtkbn" name="thdMdl" property="userJkbn" />

    <tr class="text_base2">

    <td class="<%= tdClass %>"><a href="../bulletin/bbs080.do?bbs010forumSid=<bean:write name="bbsptl010Form" property="bbsPtlBfiSid" />&threadSid=<bean:write name="thdMdl" property="btiSid" />&bbsmainFlg=1"><span class="<%= titleClass %>"><bean:write name="thdMdl" property="btiTitle" /></span></a></td>
    <td class="<%= tdClass %>">
    <logic:equal name="thdMdl" property="userJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
      <s><bean:write name="thdMdl" property="userName" /></s>
    </logic:equal>
    <logic:notEqual name="thdMdl" property="userJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
      <bean:write name="thdMdl" property="userName" />
    </logic:notEqual>

    </td>
    <td class="<%= tdClass %>" align="center"><bean:write name="thdMdl" property="strWriteDate" /></td>
    </tr>
    </logic:iterate>

    <bean:size id="count1" name="bbsptl010Form" property="bbsPageLabel" scope="request" />
    <logic:greaterThan name="count1" value="1">
    <tr>
      <td align="right" valign="bottom" nowrap  colspan="4" class="td_type30">
          <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="bbsPtl010buttonPush('prevPage', '<%= bbsFormId %>');">

            <select size="1" name="bbsPtl010page1" onchange="bbsPtl010changePage(0, '<%= bbsFormId %>');" class="text_i">
              <logic:iterate id="labelBean" name="bbsptl010Form" property="bbsPageLabel" scope="request">

                <logic:equal name="labelBean" property="value" value="<%= bbsPage %>">
                  <option value="<bean:write name="labelBean" property="value" />" selected="selected"><bean:write name="labelBean" property="label" /></option>
                </logic:equal>

                <logic:notEqual name="labelBean" property="value" value="<%= bbsPage %>">
                  <option value="<bean:write name="labelBean" property="value" />"><bean:write name="labelBean" property="label" /></option>
                </logic:notEqual>

              </logic:iterate>
            </select>

          <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="bbsPtl010buttonPush('nextPage', '<%= bbsFormId %>');"></td>
      </td>
    </tr>
    </logic:greaterThan>

    </table>

</logic:notEmpty>

</html:form>


</body>
</html:html>