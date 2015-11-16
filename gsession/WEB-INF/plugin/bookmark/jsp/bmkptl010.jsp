<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="bmk.43" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="../bookmark/css/bookmark.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<theme:css filename="theme.css"/>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../bookmark/js/bmkptl010.js?<%= GSConst.VERSION_PARAM %>"></script>

<bean:define id="grpId" name="bmkptl010Form" property="bmkGrpSid" type="java.lang.Integer" />
<% String bmkFormId = "group" + String.valueOf(grpId.intValue()); %>

<bean:define id="pageNum" name="bmkptl010Form" property="bmkptl010page" type="java.lang.Integer" />
<% String bmkPage = String.valueOf(pageNum.intValue()); %>


</head>

<body class="body_03">
<html:form action="/bookmark/bmkptl010" styleId="<%= bmkFormId %>">
<input type="hidden" name="CMD" value="">
<html:hidden property="bmkptl010ItemId" />
<html:hidden property="bmkGrpSid" />

<div id="tooltips_bmk">

  <!-- グループブックマーク -->
  <logic:notEmpty name="bmkptl010Form" property="bmkPtl010List">
    <logic:notEqual name="bmkptl010Form" property="dspFlg" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.DSP_NO) %>">
    <table width="100%" class="tl0" cellspacing="0" cellpadding="0">
    <td class="header_7D91BD_left" >
    <img src="../bookmark/images/menu_icon_single.gif" class="img_border img_bottom img_menu_icon_single" alt="<gsmsg:write key="bmk.43" />"><a href="<bean:write name="bmkptl010Form" property="bmkTopUrl" />"><gsmsg:write key="bmk.51" /></a>
    <span class="text_base3">&nbsp;[ <bean:write name="bmkptl010Form" property="groupName" /> ]</span></td>
    </td>
    </tr>



    <!-- ページング処理 -->
    <bean:size id="count1" name="bmkptl010Form" property="bmkptl010pageCmb" scope="request" />
    <logic:greaterThan name="count1" value="1">
    <tr>
      <td align="right" valign="bottom" width="20%" nowrap  colspan="4" class="td_type30">
          <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="bmkptlClickArrow('prevPage', '<%= bmkFormId %>');">

            <select size="1" name="bmkptl010page" onchange="bmkptlChangePage('<%= bmkFormId %>');" class="text_i">
              <logic:iterate id="labelBean" name="bmkptl010Form" property="bmkptl010pageCmb" scope="request">

                <logic:equal name="labelBean" property="value" value="<%= bmkPage %>">
                  <option value="<bean:write name="labelBean" property="value" />" selected="selected"><bean:write name="labelBean" property="label" /></option>
                </logic:equal>

                <logic:notEqual name="labelBean" property="value" value="<%= bmkPage %>">
                  <option value="<bean:write name="labelBean" property="value" />"><bean:write name="labelBean" property="label" /></option>
                </logic:notEqual>

              </logic:iterate>
            </select>

          <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="bmkptlClickArrow('nextPage', '<%= bmkFormId %>');"></td>
      </td>
    </tr>
    </logic:greaterThan>



    <bean:define id="tdColor" value="" />
    <% String[] tdColors = new String[] {"td_type1", "td_type25_2"}; %>

    <logic:iterate id="bmkMdl" name="bmkptl010Form" property="bmkPtl010List" indexId="idx">
    <% tdColor = tdColors[(idx.intValue() % 2)]; %>
    <tr>
    <td colspan="2" class="<%= tdColor %>">

    <a href="<bean:write name="bmkMdl" property="bmuUrl" />" target="_blank">
    <span class="text_link"><font size="-1"><bean:write name="bmkMdl" property="bmkTitle" /></font></span>
    </a>
    </td>
    </tr>

    </logic:iterate>
    </table>
  </logic:notEqual>
  </logic:notEmpty>
</div>
</html:form>
</body>
</html:html>