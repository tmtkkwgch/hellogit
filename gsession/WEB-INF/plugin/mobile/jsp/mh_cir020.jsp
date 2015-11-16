<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% thisForm = "mbhCir020Form"; %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhCir020Form"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cir.5" /><gsmsg:write key="cmn.list" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>

<html:form method="post" action="/mobile/mh_cir020">

<input type="hidden" name="helpPrm" value="<bean:write name="mbhCir020Form" property="cir020cmdMode" />">

<input type="hidden" name="CMD" value="search">
<html:hidden property="cirViewAccount" />
<html:hidden property="cirViewAccountName" />
<input type="hidden" name="cir020selectInfSid">
<input type="hidden" name="cir020sojuKbn">
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

<bean:define id="orderKey" name="mbhCir020Form" property="cir020orderKey" />
<bean:define id="sortKbn" name="mbhCir020Form" property="cir020sortKey" />
<% int iOrderKey = ((Integer) orderKey).intValue(); %>
<% int iSortKbn = ((Integer) sortKbn).intValue(); %>

<% String jusin = String.valueOf(jp.groupsession.v2.cir.GSConstCircular.MODE_JUSIN); %>
<% String sosin = String.valueOf(jp.groupsession.v2.cir.GSConstCircular.MODE_SOUSIN); %>
<% String gomi = String.valueOf(jp.groupsession.v2.cir.GSConstCircular.MODE_GOMI); %>

<% String unopen = String.valueOf(jp.groupsession.v2.cir.GSConstCircular.CONF_UNOPEN); %>

<%
  int order_desc = jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC;
  int order_asc = jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC;
%>

<!-- BODY -->
<b><%= emojiMemo.toString() %><gsmsg:write key="cir.5" /><br>
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
<hr>
<logic:greaterThan name="mbhCir020Form" property="cir020maxCout" value="1">
  <input type="submit" name="cir020prev" value="<gsmsg:write key="cmn.previous" />" />(<bean:write name="mbhCir020Form" property="cir020pageNum1" />/<bean:write name="mbhCir020Form" property="cir020maxCout" />)<input type="submit" name="cir020next" value="<gsmsg:write key="cmn.next" />" />
</logic:greaterThan>
&nbsp;&nbsp;<input name="cir020Back" value="<gsmsg:write key="cmn.back" />" type="submit">

<br>

<logic:equal name="mbhCir020Form" property="cir020cmdMode" value="<%= jusin %>">
  <logic:notEmpty name="mbhCir020Form" property="cir020CircularList" scope="request">
    <logic:iterate id="cirMdl" name="mbhCir020Form" property="cir020CircularList" scope="request" indexId="idx">
      <hr>
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
      ■<gsmsg:write key="cmn.title" />：<a href="./mh_cir030.do<%= jsessionId.toString() %>?CMD=view&cirViewAccount=<bean:write name="mbhCir020Form" property="cirViewAccount" />&cir020selectInfSid=<bean:write name="cirMdl" property="cifSid" />&cir020cmdMode=<bean:write name="mbhCir020Form" property="cir020cmdMode" />&cir020pageNum1=<bean:write name="mbhCir020Form" property="cir020pageNum1" />">
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

    </logic:iterate>
  </logic:notEmpty>
</logic:equal>


<logic:equal name="mbhCir020Form" property="cir020cmdMode" value="<%= sosin %>">

<!-- 表BODY -->
<logic:notEmpty name="mbhCir020Form" property="cir020CircularList" scope="request">
  <logic:iterate id="cirMdl" name="mbhCir020Form" property="cir020CircularList" scope="request" indexId="idx">
    <hr>
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
    ■<gsmsg:write key="cmn.title" />：<a href="./mh_cir040.do<%= jsessionId.toString() %>?CMD=view&cirViewAccount=<bean:write name="mbhCir020Form" property="cirViewAccount" />&cir020selectInfSid=<bean:write name="cirMdl" property="cifSid" />&cir020cmdMode=<bean:write name="mbhCir020Form" property="cir020cmdMode" />&cir020cmdMode=<bean:write name="mbhCir020Form" property="cir020cmdMode" />&cir020pageNum1=<bean:write name="mbhCir020Form" property="cir020pageNum1" />"><span class="text_p"><bean:write name="cirMdl" property="cifTitle" /></span></a>

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
    <logic:iterate id="cirMdl" name="mbhCir020Form" property="cir020CircularList" scope="request" indexId="idx">

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

    <hr>
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
    ■<gsmsg:write key="cmn.check" />：<a href="./mh_cir020.do<%= jsessionId.toString() %>?CMD=view&cirViewAccount=<bean:write name="mbhCir020Form" property="cirViewAccount" />&cir020selectInfSid=<bean:write name="cirMdl" property="cifSid" />&cir020sojuKbn=<bean:write name="cirMdl" property="jsFlg" />&cir020cmdMode=<bean:write name="mbhCir020Form" property="cir020cmdMode" />&cir020pageNum1=<bean:write name="mbhCir020Form" property="cir020pageNum1" />"><bean:write name="cirMdl" property="cifTitle" /></a>

    </logic:iterate>
  </logic:notEmpty>

</logic:equal>

<br>
<br>
<hr>
<logic:greaterThan name="mbhCir020Form" property="cir020maxCout" value="1">
  <input type="submit" name="cir020prev" value="<gsmsg:write key="cmn.previous" />" />(<bean:write name="mbhCir020Form" property="cir020pageNum1" />/<bean:write name="mbhCir020Form" property="cir020maxCout" />)<input type="submit" name="cir020next" value="<gsmsg:write key="cmn.next" />" />
</logic:greaterThan>
&nbsp;&nbsp;<input name="cir020Back" value="<gsmsg:write key="cmn.back" />" type="submit">

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>