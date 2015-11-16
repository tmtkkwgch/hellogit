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
<title>[GroupSession] <gsmsg:write key="bbs.9" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../bulletin/css/bulletin.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">
<html:form action="/bulletin/bbsmain">
<input type="hidden" name="CMD" value="">

    <!--掲示板-->

<logic:notEmpty name="bbsmainForm" property="threadList">

    <table width="100%" class="tl0" cellspacing="0" cellpadding="0">
    <tr>
    <td align="left" class="header_7D91BD_left" colspan="4">
      <img src="../bulletin/images/menu_icon_single.gif" class="img_border img_bottom img_menu_icon_single" alt="<gsmsg:write key="bbs.bbsMain.5" />"><a href="<bean:write name="bbsmainForm" property="bbsTopUrl" />"><gsmsg:write key="cmn.bulletin" /></a>
    </td>
    </tr>

    <tr class="text_base2">
    <th class="td_type30" width="30%" scope="col" nowrap><gsmsg:write key="bbs.3" /></th>
    <th class="td_type30" width="30%" scope="col" nowrap><gsmsg:write key="bbs.bbsMain.4" /></th>
    <th class="td_type30" width="25%" scope="col" nowrap><gsmsg:write key="bbs.bbsMain.2" /></th>
    <th class="td_type30" width="15%" scope="col" nowrap><gsmsg:write key="bbs.bbsMain.3" /></th>
    </tr>

    <% String[] tdClassList = {"td_type1", "td_type25_2"}; %>

    <logic:iterate id="thdMdl" name="bbsmainForm" property="threadList" indexId="index">

    <% String tdClass = tdClassList[index.intValue() % 2]; %>
    <% String titleClass = "text_link"; %>
    <logic:equal name="thdMdl" property="readFlg" value="1">
      <% titleClass = "text_title_main"; %>
    </logic:equal>
    <bean:define id="userJtkbn" name="thdMdl" property="userJkbn" />

    <tr class="text_base2">

    <td class="<%= tdClass %>">
    <logic:equal name="thdMdl" property="newFlg" value="1">
    <img src="../bulletin/images/icon_new.gif" class="img_bottom" alt="new">&nbsp;
    </logic:equal>
    <a href="../bulletin/bbs060.do?bbs010forumSid=<bean:write name="thdMdl" property="bfiSid" />&bbsmainFlg=1"><span class="<%= titleClass %>"><bean:write name="thdMdl" property="bfiName" /></span></a>
    </td>

    <td class="<%= tdClass %>"><a href="../bulletin/bbs080.do?bbs010forumSid=<bean:write name="thdMdl" property="bfiSid" />&threadSid=<bean:write name="thdMdl" property="btiSid" />&bbsmainFlg=1"><span class="<%= titleClass %>"><bean:write name="thdMdl" property="btiTitle" /></span></a></td>
    <td class="<%= tdClass %>" align="center"><bean:write name="thdMdl" property="strWriteDate" /></td>
    <td class="<%= tdClass %>">
    <logic:equal name="thdMdl" property="userJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
      <s><bean:write name="thdMdl" property="userName" /></s>
    </logic:equal>
    <logic:notEqual name="thdMdl" property="userJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
      <bean:write name="thdMdl" property="userName" />
    </logic:notEqual>

    </td>
    </tr>
    </logic:iterate>


    </table>

</logic:notEmpty>

</html:form>


</body>
</html:html>