<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.filekanri" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/file.css?<%= GSConst.VERSION_PARAM %>" type="text/css">

<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/file.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/filptl030.js?<%= GSConst.VERSION_PARAM %>"></script>

</head>

<body class="body_03">
<logic:notEmpty name="filptl030Form" property="cabinetList">
  <html:form action="/file/filptl030">
  <input type="hidden" name="CMD" value="">
  <input type="hidden" name="backDsp" value="">
  <input type="hidden" name="fil010SelectCabinet" value="">
  <input type="hidden" name="fil010SelectDirSid" value="">

  <table class="tl0" width="100%" cellpadding="0" cellspacing="0">
      <tr>
      <th class="header_7D91BD_left" colspan="2" width="100%" nowrap>
      <img src="../file/images/menu_icon_single.gif" class="img_border img_bottom img_menu_icon_single" alt="<gsmsg:write key="fil.1" />">
      <a href="../file/fil010.do"><gsmsg:write key="mainscreeninfo.file.filptl030" /></a>
      </th>
      </tr>
      <tr class="text_base2">
      <th class="td_type30" width="0">&nbsp;</th>
      <th class="td_type30" width="100%" scope="col" nowrap><gsmsg:write key="fil.23" /></th>
      </tr>

      <% String[] tdClassList = {"td_type1", "td_type25_2"}; %>
      <logic:iterate id="cabBean" name="filptl030Form" property="cabinetList" indexId="index">

      <% String tdClass = tdClassList[index.intValue() % 2]; %>

      <tr>
        <td class="<%= tdClass %>">
          <logic:equal name="cabBean" property="fcbMark" value="0">
            <img src="../file/images/cabinet.gif" alt="">
          </logic:equal>
          <logic:notEqual name="cabBean" property="fcbMark" value="0">
            <img name="iconImage" width="30" src="../file/fil010.do?CMD=tempview&fil010SelectCabinet=<bean:write name="cabBean" property="fcbSid" />&fil010binSid=<bean:write name="cabBean" property="fcbMark" />" name="pictImage<bean:write name="cabBean" property="fcbMark" />" >
          </logic:notEqual>
        </td>

        <td class="<%= tdClass %>">
          <a href="#" onclick="MoveToRootFolderListForPtl030(<bean:write name="cabBean" property="fcbSid" />, <bean:write name="cabBean" property="rootDirSid" />);"><span class="text_link"><bean:write name="cabBean" property="dspfcbName" filter="false" /></span>

          <logic:notEqual name="cabBean" property="accessIconKbn" value="1">
            <img src="../file/images/icon_stop.gif" alt="">
          </logic:notEqual>

          <logic:equal name="cabBean" property="callIconKbn" value="1">
            <img src="../file/images/icon_call.gif" alt="">
          </logic:equal>
          <logic:notEqual name="cabBean" property="callIconKbn" value="1">
            &nbsp;
          </logic:notEqual>
          </a>

          <logic:notEmpty name="cabBean" property="dspBikoString">
            <br><span style="font-size:70%;"><bean:write name="cabBean" property="dspBikoString" filter="false"/></span>
          </logic:notEmpty>
          <logic:empty name="cabBean" property="dspBikoString">
            &nbsp;
          </logic:empty>

      </td>
      </tr>

      </logic:iterate>
      </table>

  </html:form>
</logic:notEmpty>


</body>
</html:html>
