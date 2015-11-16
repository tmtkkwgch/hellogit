<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cir.5" /><gsmsg:write key="cmn.list" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "cir"; %>
<% thisForm = "mbhCir020Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">
<html:form method="post" action="/mobile/sp_cir020">

<bean:define id="orderKey" name="mbhCir020Form" property="cir020orderKey" />
<bean:define id="sortKbn" name="mbhCir020Form" property="cir020sortKey" />
<% int iOrderKey = ((Integer) orderKey).intValue(); %>
<% int iSortKbn = ((Integer) sortKbn).intValue(); %>

<% String jusin = String.valueOf(jp.groupsession.v2.cir.GSConstCircular.MODE_JUSIN); %>
<% String sosin = String.valueOf(jp.groupsession.v2.cir.GSConstCircular.MODE_SOUSIN); %>
<% String gomi = String.valueOf(jp.groupsession.v2.cir.GSConstCircular.MODE_GOMI); %>

<% String unopen = String.valueOf(jp.groupsession.v2.cir.GSConstCircular.CONF_UNOPEN); %>


<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
  <h1><gsmsg:write key="cir.5" /><br>
    <logic:equal name="mbhCir020Form" property="cir020cmdMode" value="<%= jusin %>">
      <gsmsg:write key="cmn.receive2" />
    </logic:equal>
    <logic:equal name="mbhCir020Form" property="cir020cmdMode" value="<%= sosin %>">
      <gsmsg:write key="cir.8" />
    </logic:equal>
    <logic:equal name="mbhCir020Form" property="cir020cmdMode" value="<%= gomi %>">
      <gsmsg:write key="cmn.trash" />
    </logic:equal>
    <a href="#" onClick="buttonPush('cir020Back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
    <img src="../mobile/sp/imgages/cir_menu_icon_single.gif" class="tl img_border"/>
  </h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<input type="hidden" name="helpPrm" value="<bean:write name="mbhCir020Form" property="cir020cmdMode" />">
<html:hidden property="mobileType" value="1"/>
<input type="hidden" name="CMD" value="search">
<input type="hidden" name="cir020selectInfSid">
<input type="hidden" name="cir020sojuKbn">
<html:hidden property="cirViewAccount" />
<html:hidden property="cirViewAccountName" />
<html:hidden property="cir020cmdMode" />
<html:hidden property="cir020orderKey" />
<html:hidden property="cir020sortKey" />
<html:hidden property="cir020pageNum1" />
<logic:notEmpty name="mbhCir020Form" property="cir020saveList" scope="request">
<logic:iterate id="chks" name="mbhCir020Form" property="cir020saveList" scope="request">
  <bean:define id="chkSid" name="chks" type="java.lang.String" />
  <html:hidden property="cir020delInfSid" value="<%= chkSid %>" />
</logic:iterate>
</logic:notEmpty>
<bean:define id="maxPage" name="mbhCir020Form" property="cir020maxCout" />

<!-- BODY -->

<div data-role="controlgroup" data-type="horizontal" align="center" class="font_xsmall">
  <logic:notEmpty name="mbhCir020Form" property="cir020CircularList">
  <logic:greaterThan name="maxPage" value="1">
    <logic:notEqual name="mbhCir020Form" property="cir020pageNum1" value="1">
    <logic:notEqual name="mbhCir020Form" property="cir020pageNum1" value="<%= maxPage.toString() %>">
      <input type="submit" name="cir020prev" value="<gsmsg:write key="cmn.previous"/>" data-icon="arrow-l"/><input type="submit" name="cir020next" value="<gsmsg:write key="cmn.next"/>" data-icon="arrow-r" data-iconpos="right"/>
    </logic:notEqual>
    </logic:notEqual>
    <logic:equal name="mbhCir020Form" property="cir020pageNum1" value="1">
      <input type="submit" name="cir020next" value="<gsmsg:write key="cmn.next"/>" data-icon="arrow-r" data-iconpos="right"/>
    </logic:equal>
    <logic:equal name="mbhCir020Form" property="cir020pageNum1" value="<%= maxPage.toString()%>">
      <input type="submit" name="cir020prev" value="<gsmsg:write key="cmn.previous"/>" data-icon="arrow-l"/>
    </logic:equal>
  </logic:greaterThan>
  </logic:notEmpty>
</div>

<ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
  <li data-role="list-divider">
    <div align="center">
      <b>
        <bean:write name="mbhCir020Form" property="cirViewAccountName" /><br>
        <logic:equal name="mbhCir020Form" property="cir020cmdMode" value="<%= jusin %>">
          <gsmsg:write key="cmn.receive2" />
        </logic:equal>
        <logic:equal name="mbhCir020Form" property="cir020cmdMode" value="<%= sosin %>">
          <gsmsg:write key="cir.8" />
        </logic:equal>
        <logic:equal name="mbhCir020Form" property="cir020cmdMode" value="<%= gomi %>">
          <gsmsg:write key="cmn.trash" />
        </logic:equal>
      </b>
      <logic:greaterThan name="mbhCir020Form" property="cir020maxCout" value="1"><div class="font_small">(<bean:write name="mbhCir020Form" property="cir020pageNum1" />/<bean:write name="mbhCir020Form" property="cir020maxCout" />)</div></logic:greaterThan>
    </div>
  </li>


<logic:equal name="mbhCir020Form" property="cir020cmdMode" value="<%= jusin %>">
  <logic:notEmpty name="mbhCir020Form" property="cir020CircularList" scope="request">
    <bean:define id="mod" value="0" />
    <logic:iterate id="cirMdl" name="mbhCir020Form" property="cir020CircularList" scope="request" indexId="idx">

    <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
      <bean:define id="liColor" value="c" />
    </logic:equal>
    <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
      <bean:define id="liColor" value="d" />
    </logic:notEqual>

    <li data-theme=<bean:write name="liColor" />>
      <a href="./sp_cir030.do?mobileType=1&CMD=view&cirViewAccount=<bean:write name="mbhCir020Form" property="cirViewAccount" />&cir020selectInfSid=<bean:write name="cirMdl" property="cifSid" />&cir020cmdMode=<bean:write name="mbhCir020Form" property="cir020cmdMode" />&cir020pageNum1=<bean:write name="mbhCir020Form" property="cir020pageNum1" />">
        <!-- 日付 -->
        <bean:write name="cirMdl" property="dspCifAdate" /><br>

        <!-- 発信者 -->
        <logic:equal name="cirMdl" property="cacJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_TOROKU) %>">
          ■<gsmsg:write key="cir.2" />：<bean:write name="cirMdl" property="cacName" />
        </logic:equal>
        <logic:notEqual name="cirMdl" property="cacJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_TOROKU) %>">
          ■<gsmsg:write key="cir.2" />：<del><bean:write name="cirMdl" property="cacName" /></del>
        </logic:notEqual>

        <!-- タイトル -->
        <br>
        ■<gsmsg:write key="cmn.title" />：
        <logic:equal name="cirMdl" property="cvwConf" value="<%= unopen %>">
           <b>
        </logic:equal>
        <logic:notEqual name="cirMdl" property="cvwConf" value="<%= unopen %>">
           <span style="color:#990099">
        </logic:notEqual>
         <bean:write name="cirMdl" property="cifTitle" />
        <logic:equal name="cirMdl" property="cvwConf" value="<%= unopen %>">
           </b>
        </logic:equal>
        <logic:notEqual name="cirMdl" property="cvwConf" value="<%= unopen %>">
           </span>
        </logic:notEqual>
      </a>
    </li>

    </logic:iterate>
  </logic:notEmpty>
</logic:equal>


<logic:equal name="mbhCir020Form" property="cir020cmdMode" value="<%= sosin %>">

<!-- 表BODY -->
<logic:notEmpty name="mbhCir020Form" property="cir020CircularList" scope="request">
  <bean:define id="mod" value="0" />
  <logic:iterate id="cirMdl" name="mbhCir020Form" property="cir020CircularList" scope="request" indexId="idx">

    <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
      <bean:define id="liColor" value="c" />
    </logic:equal>
    <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
      <bean:define id="liColor" value="d" />
    </logic:notEqual>

    <li data-theme=<bean:write name="liColor" />>
      <a href="./sp_cir040.do?mobileType=1&CMD=view&cirViewAccount=<bean:write name="mbhCir020Form" property="cirViewAccount" />&cir020selectInfSid=<bean:write name="cirMdl" property="cifSid" />&cir020cmdMode=<bean:write name="mbhCir020Form" property="cir020cmdMode" />&cir020cmdMode=<bean:write name="mbhCir020Form" property="cir020cmdMode" />&cir020pageNum1=<bean:write name="mbhCir020Form" property="cir020pageNum1" />">
        <!-- 日付 -->
        <bean:write name="cirMdl" property="dspCifAdate" /><br>

        <!-- 発信者 -->
        <logic:equal name="cirMdl" property="cacJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_TOROKU) %>">
          ■<gsmsg:write key="cir.2" />：<span class="text_p"><bean:write name="cirMdl" property="cacName" /></span>
        </logic:equal>
        <logic:notEqual name="cirMdl" property="cacJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_TOROKU) %>">
          ■<gsmsg:write key="cir.2" />：<del><span class="text_p"><bean:write name="cirMdl" property="cacName" /></span></del>
        </logic:notEqual>

        <!-- 確認 -->
        <br>
        ■<gsmsg:write key="cmn.check" />：<span class="text_p"><bean:write name="cirMdl" property="openCount" />/<bean:write name="cirMdl" property="allCount" /></span>

        <!-- タイトル -->
        <br>
        ■<gsmsg:write key="cmn.title" />：<span class="text_p"><bean:write name="cirMdl" property="cifTitle" /></span>
    </a>
  </li>

  </logic:iterate>
</logic:notEmpty>

</logic:equal>


<logic:equal name="mbhCir020Form" property="cir020cmdMode" value="<%= gomi %>">

<%
  String font = "";
  String titleFont = "";
  String sojuStr = "";
%>

  <!-- 表BODY -->
  <logic:notEmpty name="mbhCir020Form" property="cir020CircularList" scope="request">
    <bean:define id="mod" value="0" />
    <logic:iterate id="cirMdl" name="mbhCir020Form" property="cir020CircularList" scope="request" indexId="idx">

    <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
      <bean:define id="liColor" value="c" />
    </logic:equal>
    <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
      <bean:define id="liColor" value="d" />
    </logic:notEqual>

    <li data-theme=<bean:write name="liColor" />>
      <a href="./sp_cir020.do?mobileType=1&CMD=view&cirViewAccount=<bean:write name="mbhCir020Form" property="cirViewAccount" />&cir020selectInfSid=<bean:write name="cirMdl" property="cifSid" />&cir020sojuKbn=<bean:write name="cirMdl" property="jsFlg" />&cir020cmdMode=<bean:write name="mbhCir020Form" property="cir020cmdMode" />&cir020pageNum1=<bean:write name="mbhCir020Form" property="cir020pageNum1" />">

        <logic:equal name="cirMdl" property="jsFlg" value="<%= jusin %>">
          <gsmsg:define id="jusinStr" msgkey="cmn.receive2" />
      <%
        sojuStr = "[ " + jusinStr + " ]";
      %>
        </logic:equal>
        <logic:equal name="cirMdl" property="jsFlg" value="<%= sosin %>">
          <gsmsg:define id="sosinStr" msgkey="cmn.sent2" />
      <%
      sojuStr = "[ " + sosinStr + " ]";
      %>
        </logic:equal>

          <%= String.valueOf(sojuStr) %><br>
          <!-- 日付 -->
          <bean:write name="cirMdl" property="dspCifAdate" /><br>

          <!-- 発信者 -->
          <logic:equal name="cirMdl" property="cacJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_TOROKU) %>">
            ■<gsmsg:write key="cir.2" />：<bean:write name="cirMdl" property="cacName" />
          </logic:equal>
          <logic:notEqual name="cirMdl" property="cacJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_TOROKU) %>">
            ■<gsmsg:write key="cir.2" />：<del><bean:write name="cirMdl" property="cacName" /></del>
          </logic:notEqual>

          <!-- タイトル -->
          <br>
          ■<gsmsg:write key="cmn.check" />：<bean:write name="cirMdl" property="cifTitle" />

      </a>
    </li>

    </logic:iterate>
  </logic:notEmpty>

</logic:equal>

</ul>

<div data-role="controlgroup" data-type="horizontal" align="center" class="font_xsmall">
  <logic:notEmpty name="mbhCir020Form" property="cir020CircularList">
  <logic:greaterThan name="maxPage" value="1">
    <logic:notEqual name="mbhCir020Form" property="cir020pageNum1" value="1">
    <logic:notEqual name="mbhCir020Form" property="cir020pageNum1" value="<%= maxPage.toString() %>">
      <input type="submit" name="cir020prev" value="<gsmsg:write key="cmn.previous"/>" data-icon="arrow-l"/><input type="submit" name="cir020next" value="<gsmsg:write key="cmn.next"/>" data-icon="arrow-r" data-iconpos="right"/>
    </logic:notEqual>
    </logic:notEqual>
    <logic:equal name="mbhCir020Form" property="cir020pageNum1" value="1">
      <input type="submit" name="cir020next" value="<gsmsg:write key="cmn.next"/>" data-icon="arrow-r" data-iconpos="right"/>
    </logic:equal>
    <logic:equal name="mbhCir020Form" property="cir020pageNum1" value="<%= maxPage.toString()%>">
      <input type="submit" name="cir020prev" value="<gsmsg:write key="cmn.previous"/>" data-icon="arrow-l"/>
    </logic:equal>
  </logic:greaterThan>
  </logic:notEmpty>
</div>

<div align="center">
  <input name="cir020Back" value="<gsmsg:write key="cmn.back" />" type="submit" data-inline="true" data-role="button" data-icon="back">
</div>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>
</div><!-- /page -->

</body>
</html:html>