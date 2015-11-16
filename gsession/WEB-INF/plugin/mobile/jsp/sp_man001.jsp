<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%  String key = jp.groupsession.v2.cmn.GSConst.SESSION_KEY; %>
<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>
<% String serverurl = jp.groupsession.v3.mbh.GSConstMobileHtml.SP_SERVERURL_AREA; %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="cmn.main" /></title>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% thisForm = "mbhMan001Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<img src="../mobile/sp/imgages/main_menu_icon_single.gif" class="tl img_border"/>
  <h1><b><gsmsg:write key="cmn.main" /></b><br></h1>
</div><!-- /header -->

<div data-role="content">

<logic:notEmpty name="<%= key %>" scope="session">
  <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
    <li data-role="list-divider" style="background:#ffffff;">
      <div align="center" class="font_xsmall">
        <bean:write name="<%= key %>" scope="session" property="usisei" />&nbsp;&nbsp;<bean:write name="<%= key %>" scope="session" property="usimei" />
      </div>
    </li>
  </ul>
</logic:notEmpty>

<html:form action="/mobile/sp_cmn001">
<html:hidden property="mobileType" value="1"/>

<logic:notEmpty name="mbhMan001Form" property="infoMsgs">
<div data-role="collapsible" data-collapsed="false">
  <h2>
    <div align="center"><gsmsg:write key="cmn.information" /></div>
  </h2>
  <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
    <bean:define id="mod" value="0" />
    <logic:iterate id="msg" name="mbhMan001Form" property="infoMsgs" scope="request" indexId="idx">

      <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
        <bean:define id="liColor" value="d" />
      </logic:equal>
      <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
        <bean:define id="liColor" value="c" />
      </logic:notEqual>

      <li data-theme=<bean:write name="liColor" />>
        <a class="ui-link-inherit" href="..<bean:write name="msg" property="linkUrl" />">

          <table width="240px;">
            <tr>
              <td>
                  <img src="<bean:write name="msg" property="icon" />" class="ui-li-icon ui-li-thumb">
              </td>
              <td style="padding-top:3px;">
                  &nbsp;&nbsp;&nbsp;&nbsp;<b><span class="font_small" style="color:blue;"><bean:write name="msg" property="message" filter="false"/></span></b>
              </td>
            </tr>
          </table>

        </a>
      </li>
     </logic:iterate>
  </ul>
  <hr>
</div>
</logic:notEmpty>

<br>

<logic:equal name="mbhMan001Form" property="scheduleUseOk" value="0">
<div class="header">
  <table>
    <tr>
      <td>
        <img src="../mobile/sp/imgages/sch_menu_icon2_single.gif">
      </td>
      <td>
        <b><gsmsg:write key="schedule.108" /></b>
      </td>
    </tr>
  </table>
</div>
<div data-role="footer" data-theme="c" align="center" class="header_bottom_shadow">
  <div data-role="navbar">
  <ul>
    <li><div class="font_small"><a class="footer_bottom_left" href="../mobile/sp_sch030.do?mobileType=1" data-theme="d"><gsmsg:write key="mobile.18" /></a></div></li>
    <li><div class="font_small"><a href="../mobile/sp_sch010.do?mobileType=1" data-theme="d"><gsmsg:write key="schedule.19" /></a></div></li>
    <li><div class="font_small"><a class="footer_bottom_right" href="../mobile/sp_sch020.do?mobileType=1" data-theme="d"><gsmsg:write key="cmn.group.days" /></a></div></li>
  </ul>
  </div>
</div>

</logic:equal>

<logic:equal name="mbhMan001Form" property="webMailUseOk" value="0">
<logic:notEmpty name="mbhMan001Form" property="man001WmlAccountList">
  <ul class=" ui-listview "data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
    <li data-role="list-divider" style="background:#ffffff;">
      <div class="font_xsmall">
        <table>
          <tr>
            <td>
              <img src="../mobile/sp/imgages/wml_menu_icon2_single.gif" class="ui-li-icon ui-li-thumb">
            </td>
            <td style="padding-top:4px">
              <b>&nbsp;&nbsp;&nbsp;&nbsp;<gsmsg:write key="wml.wml010.25" /></b>
            </td>
          </tr>
        </table>
      </div>
    </li>
    <bean:define id="accountCnt" name="mbhMan001Form" property="man001WmlAccountCount" type="java.lang.Integer" />
    <logic:iterate id="accountData" name="mbhMan001Form" property="man001WmlAccountList" indexId="accountIdx">
      <li>
        <bean:define id="noReadCnt" name="accountData" property="notReadCount" type="java.lang.Long" />
        <% boolean noRead = noReadCnt.longValue() > 0; %>
        <% if (noRead) { %><span class="ui-li-count"><gsmsg:write key="cmn.receive" />：<%= String.valueOf(noReadCnt.longValue()) %></span><% } %><a href="./sp_wml010.do?mobileType=1&wmlAccount=<bean:write name="accountData" property="wacSid" />" accessKey="<%= String.valueOf(accountIdx.intValue() + 6) %>"><span class="font_small"><bean:write name="accountData" property="wacName" /></span></a><% if (noRead) { %><a href="./sp_wml020.do?mobileType=1&wmlAccount=<bean:write name="accountData" property="wacSid" />&wmlDirectory=<bean:write name="accountData" property="receiveWdrSid" />"><%= "(" + String.valueOf(noReadCnt.longValue()) + ")" %><% } %></a><% if (noRead) { %><% } %>
      </li>
    </logic:iterate>
  </ul>
</logic:notEmpty>
</logic:equal>

<logic:notEqual name="mbhMan001Form" property="webMailUseOk" value="0">

<br>
</logic:notEqual>


<logic:equal name="mbhMan001Form" property="bullutinUseOk" value="0">
  <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
    <li>
      <a href="../mobile/sp_bbs010.do?mobileType=1">
        <table>
          <tr>
            <td>
              <img src="../mobile/sp/imgages/bbs_menu_icon2_single.gif" class="ui-li-icon ui-li-thumb">
            </td>
            <td>
              <b>&nbsp;&nbsp;&nbsp;&nbsp;<gsmsg:write key="cmn.bulletin" /></b>
            </td>
          </tr>
        </table>
        <logic:greaterThan name="mbhMan001Form" property="man001BbsCnt" value="0">
          <div class="font_xxsmall">
            <span class="ui-li-count"><gsmsg:write key="bbs.16" />：<bean:write name="mbhMan001Form" property="man001BbsCnt" /></span>
          </div>
        </logic:greaterThan>
      </a>
    </li>
  </ul>
</logic:equal>



<logic:equal name="mbhMan001Form" property="nippouUseOk" value="0">
<div class="header">
  <table>
    <tr>
      <td style="padding-top:3px;">
        <img src="../mobile/sp/imgages/ntp_menu_icon2_single.gif">
      </td>
      <td>
        <b>日報</b>
      </td>
    </tr>
  </table>
</div>
<div data-role="footer" data-theme="c" align="center" class="header_bottom_shadow">
  <div data-role="navbar">
  <ul>
    <li><div class="font_small"><a class="footer_bottom_left" href="../mobile/sp_ntp030.do?mobileType=1" data-theme="d"><gsmsg:write key="mobile.18" /></a></div></li>
    <li><div class="font_small"><a href="../mobile/sp_ntp010.do?mobileType=1" data-theme="d"><gsmsg:write key="schedule.19" /></a></div></li>
    <li><div class="font_small"><a class="footer_bottom_right" href="../mobile/sp_ntp020.do?mobileType=1" data-theme="d"><gsmsg:write key="cmn.group.days" /></a></div></li>
  </ul>
  </div>
</div>

</logic:equal>

<br>

<% String topHeaderClass = null; %>
<% String topListClass = null;%>
<% String smlAtagClass = null; %>
<% String bbsAtagClass = null; %>
<% String rsvAtagClass = null; %>

<logic:notEqual name="mbhMan001Form" property="man001BottomMenuKbn" value="4">
  <% topHeaderClass = "header_top_shadow"; %>
  <logic:equal name="mbhMan001Form" property="man001TopMenuKbn" value="0">
    <% smlAtagClass = "footer_top_left"; %>
    <% rsvAtagClass = "footer_top_right"; %>
  </logic:equal>
  <logic:equal name="mbhMan001Form" property="man001TopMenuKbn" value="1">
    <% smlAtagClass = "footer_top_left"; %>
    <% bbsAtagClass = "footer_top_right"; %>
    <% rsvAtagClass = "footer_top_right"; %>
  </logic:equal>
  <logic:equal name="mbhMan001Form" property="man001TopMenuKbn" value="2">
    <% bbsAtagClass = "footer_top_left"; %>
    <% rsvAtagClass = "footer_top_right"; %>

  </logic:equal>
  <logic:equal name="mbhMan001Form" property="man001TopMenuKbn" value="3">
    <% smlAtagClass = "footer_top_both"; %>
    <% bbsAtagClass = "footer_top_both"; %>
    <% rsvAtagClass = "footer_top_both"; %>
    <% topListClass = "li_width_max"; %>
  </logic:equal>
</logic:notEqual>

<logic:equal name="mbhMan001Form" property="man001BottomMenuKbn" value="4">
  <% topHeaderClass = "header_both_shadow"; %>
  <logic:equal name="mbhMan001Form" property="man001TopMenuKbn" value="0">
    <% smlAtagClass = "footer_left"; %>
    <% rsvAtagClass = "footer_right"; %>
  </logic:equal>
  <logic:equal name="mbhMan001Form" property="man001TopMenuKbn" value="1">
    <% smlAtagClass = "footer_left"; %>
    <% bbsAtagClass = "footer_right"; %>
    <% rsvAtagClass = "footer_right"; %>
  </logic:equal>
  <logic:equal name="mbhMan001Form" property="man001TopMenuKbn" value="2">
    <% bbsAtagClass = "footer_left"; %>
    <% rsvAtagClass = "footer_right"; %>
  </logic:equal>
  <logic:equal name="mbhMan001Form" property="man001TopMenuKbn" value="3">
    <% smlAtagClass = "footer_both"; %>
    <% bbsAtagClass = "footer_both"; %>
    <% rsvAtagClass = "footer_both"; %>
    <% topListClass = "li_width_max"; %>
  </logic:equal>
</logic:equal>

<logic:notEqual name="mbhMan001Form" property="man001TopMenuKbn" value="4">
  <div data-role="footer" data-theme="c" align="center" class="<%= topHeaderClass %>">
    <div data-role="navbar">
      <ul>
        <logic:equal name="mbhMan001Form" property="smailUseOk" value="0">
          <li class="<%= topListClass %>">
            <a class="<%= smlAtagClass %>" style="height:73px" href="../mobile/sp_sml010.do?mobileType=1" data-theme="d" data-role="button">
              <img src="../mobile/sp/imgages/sml_menu_icon2_single.gif" width="22px" width="22px" style="margin:  1px 0px;">
              <div class="font_small"><gsmsg:write key="cmn.shortmail" /></div>
              <logic:greaterThan name="mbhMan001Form" property="man001SmlCnt" value="0">
                <div class="font_xxsmall">
                  <span class="title_2" style="color:#000000;"><gsmsg:write key="cmn.receive" />：<bean:write name="mbhMan001Form" property="man001SmlCnt" /></span>
                </div>
              </logic:greaterThan>
              <logic:lessEqual name="mbhMan001Form" property="man001SmlCnt" value="0">
                <div class="font_xxsmall">&nbsp;</div>
              </logic:lessEqual>
            </a>
          </li>
        </logic:equal>

        <logic:equal name="mbhMan001Form" property="circularUseOk" value="0">
          <li class="<%= topListClass %>">
            <a class="<%= bbsAtagClass %>" style="height:73px" href="../mobile/sp_cir010.do?mobileType=1" data-theme="d" data-role="button">
              <img src="../mobile/sp/imgages/cir_menu_icon2_single.gif">
              <div class="font_small"><gsmsg:write key="cir.5" /></div>
              <logic:greaterThan name="mbhMan001Form" property="man001CirCnt" value="0">
                <div class="font_xxsmall">
                  <span class="title_2" style="color:#000000;"><gsmsg:write key="cmn.receive" />：<bean:write name="mbhMan001Form" property="man001CirCnt" /></span>
                </div>
              </logic:greaterThan>
              <logic:lessEqual name="mbhMan001Form" property="man001CirCnt" value="0">
                <div class="font_xxsmall">&nbsp;</div>
              </logic:lessEqual>
            </a>
          </li>
        </logic:equal>

        <logic:equal name="mbhMan001Form" property="rsvUseOk" value="0">
          <li class="<%= topListClass %>">
            <a class="<%= rsvAtagClass %>" style="height:73px" href="../mobile/sp_rsv010.do?mobileType=1" data-theme="d" data-role="button">
              <img src="../mobile/sp/imgages/rsv_menu_icon2_single.gif" width="22px" height="22px">
              <div class="font_small"><gsmsg:write key="cmn.reserve" /></div>
              <div class="font_xxsmall">&nbsp;</div>
            </a>
          </li>
        </logic:equal>

      </ul>
    </div>
  </div>
</logic:notEqual>

<% String buttomHeaderClass = null; %>
<% String buttomListClass = null;%>
<% String adrAtagClass = null; %>
<% String usrAtagClass = null; %>
<% String rngAtagClass = null; %>

<logic:notEqual name="mbhMan001Form" property="man001TopMenuKbn" value="4">
  <% buttomHeaderClass = "header_bottom_shadow"; %>
  <logic:equal name="mbhMan001Form" property="man001BottomMenuKbn" value="0">
    <% adrAtagClass = "footer_bottom_left"; %>
    <% rngAtagClass = "footer_bottom_right"; %>
  </logic:equal>
  <logic:equal name="mbhMan001Form" property="man001BottomMenuKbn" value="1">
    <% adrAtagClass = "footer_bottom_left"; %>
    <% usrAtagClass = "footer_bottom_right"; %>
    <% rngAtagClass = "footer_bottom_right"; %>
  </logic:equal>
  <logic:equal name="mbhMan001Form" property="man001BottomMenuKbn" value="2">
    <% usrAtagClass = "footer_bottom_left"; %>
    <% rngAtagClass = "footer_bottom_right"; %>
  </logic:equal>
  <logic:equal name="mbhMan001Form" property="man001BottomMenuKbn" value="3">
    <% adrAtagClass = "footer_bottom_both"; %>
    <% usrAtagClass = "footer_bottom_both"; %>
    <% rngAtagClass = "footer_bottom_both"; %>
    <% buttomListClass = "li_width_max"; %>
  </logic:equal>
</logic:notEqual>

<logic:equal name="mbhMan001Form" property="man001TopMenuKbn" value="4">
  <% buttomHeaderClass = "header_both_shadow"; %>
  <logic:equal name="mbhMan001Form" property="man001BottomMenuKbn" value="0">
    <% adrAtagClass = "footer_left"; %>
    <% rngAtagClass = "footer_right"; %>
  </logic:equal>
  <logic:equal name="mbhMan001Form" property="man001BottomMenuKbn" value="1">
    <% adrAtagClass = "footer_left"; %>
    <% usrAtagClass = "footer_right"; %>
    <% rngAtagClass = "footer_right"; %>
  </logic:equal>
  <logic:equal name="mbhMan001Form" property="man001BottomMenuKbn" value="2">
    <% usrAtagClass = "footer_left"; %>
    <% rngAtagClass = "footer_right"; %>
  </logic:equal>
  <logic:equal name="mbhMan001Form" property="man001BottomMenuKbn" value="3">
    <% adrAtagClass = "footer_both"; %>
    <% usrAtagClass = "footer_both"; %>
    <% rngAtagClass = "footer_both"; %>
    <% buttomListClass = "li_width_max"; %>
  </logic:equal>
</logic:equal>

<logic:notEqual name="mbhMan001Form" property="man001BottomMenuKbn" value="4">
  <div data-role="footer" data-theme="c" align="center" class="<%= buttomHeaderClass %>">
    <div data-role="navbar">
      <ul>
        <logic:equal name="mbhMan001Form" property="addressUseOk" value="0">
          <li class="<%= buttomListClass %>">
            <a class="<%= adrAtagClass %>" class="footer_bottom_left" href="../mobile/sp_adr010.do?mobileType=1" data-theme="d" data-inline="true" data-role="button">
              <img src="../mobile/sp/imgages/adr_menu_icon2_single.gif">
              <div class="font_small"><gsmsg:write key="addressbook" /></div>
              <div class="font_xxsmall">&nbsp;</div>
            </a>
          </li>
        </logic:equal>

        <logic:equal name="mbhMan001Form" property="userUseOk" value="0">
          <li class="<%= buttomListClass %>">
            <a class="<%= usrAtagClass %>" href="../mobile/sp_usr010.do?mobileType=1" data-theme="d" data-inline="true" data-role="button">
              <img src="../mobile/sp/imgages/usr_menu_icon2_single.gif">
              <div class="font_small"><gsmsg:write key="cmn.shain.info" /></div>
              <div class="font_xxsmall">&nbsp;</div>
            </a>
          </li>
        </logic:equal>

        <logic:equal name="mbhMan001Form" property="ringiUseOk" value="0">
          <li class="<%= buttomListClass %>">
            <a class="<%= rngAtagClass %>" class="footer_bottom_right" href="../mobile/sp_rng010.do?mobileType=1" data-theme="d" data-inline="true" data-role="button">
              <img src="../mobile/sp/imgages/rng_menu_icon2_single.gif">
              <div class="font_small"><gsmsg:write key="rng.62" /></div>
              <logic:greaterThan name="mbhMan001Form" property="man001RngCnt" value="0">
                <div class="font_xxsmall">
                  <span class="title_2" style="color:#000000;"><gsmsg:write key="cmn.receive" />：<bean:write name="mbhMan001Form" property="man001RngCnt" /></span>
                </div>
              </logic:greaterThan>
              <logic:lessEqual name="mbhMan001Form" property="man001RngCnt" value="0">
                <div class="font_xxsmall">&nbsp;</div>
              </logic:lessEqual>
            </a>
          </li>
        </logic:equal>
      </ul>
    </div>
  </div>
</logic:notEqual>

<br>

<logic:notEmpty name="mbhMan001Form" property="areaList">
  <div class="header" align="center">
    <b><div class="font_small"><gsmsg:write key="main.src.man001.1" /></div></b>
  </div>
  <div data-role="footer" data-theme="c" align="center" class="header_bottom_shadow">
    <div data-role="navbar">
    <ul>
    <bean:define id="areaList" name="mbhMan001Form" property="areaList" />
    <% java.util.List arList = (java.util.List) areaList; %>
    <% int menuSize = arList.size(); %>
    <% jp.groupsession.v2.cmn.cmn180.Cmn180AreaModel wData = null; %>
    <% String li_width = "li_width_cnt5"; %>
    <% String atagLeft = null; %>
    <% String atagRight = null; %>
    <% boolean rowFlg = false; %>
    <% long row = Math.round(Math.ceil((double)arList.size()/5)); %>
    <% long lastItem = row*5; %>
    <% if (arList.size() <= 5) { %>
    <%    li_width = "li_width_cnt" + String.valueOf(arList.size()); %>
    <%    lastItem = arList.size(); %>
    <% } %>

    <% for (int menuCount = 0; menuCount < menuSize; menuCount++) { %>
      <% wData = (jp.groupsession.v2.cmn.cmn180.Cmn180AreaModel) arList.get(menuCount); %>
      <% String areaId = String.valueOf(wData.getAreaId()); %>
      <% String areaName = wData.getAreaName(); %>
      <% if (Math.round(Math.ceil((double)(menuCount + 1)/5)) == row) { %>
      <%     if (!rowFlg) { %>
      <%     atagLeft = "footer_bottom_left"; %>
      <%     rowFlg = true; %>
      <%   } %>
      <% } %>
      <% if (menuCount + 1 == lastItem) { %>
      <%     atagRight = "footer_bottom_right"; %>
      <% } %>
      <li class="<%= li_width %>">
        <a class="ui-link-inherit <%= atagLeft %> <%= atagRight %>" target="_blank" href="<%= serverurl %><%= areaId %>.html">
          <span class="font_xsmall" style="color:blue;"><%= areaName %></span>
        </a>
      </li>
      <% atagLeft = null; %>
    <% } %>
    </ul>
    </div>
  </div>
</logic:notEmpty>

</div><!-- /content -->

<div data-role="footer" data-theme="<%= usrTheme %>" data-theme="<%= usrTheme %>">
  <a href="../mobile/sp_cmn001.do?mobileType=1&CMD=logout" data-icon="back" class="ui-btn-right"><div class="font_xsmall"><gsmsg:write key="mobile.11" /></div></a>
  <a href="../mobile/sp_cmn002.do?mobileType=1" class="footer_theme_button" data-icon="gear" class="ui-btn-right"><div class="font_xsmall"><gsmsg:write key="cmn.setting" /></div></a>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_footer.jsp" %>
</div><!-- /footer -->

</html:form>

</div><!-- /page -->

</body>
</html:html>