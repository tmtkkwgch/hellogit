<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% thisForm = "mbhSml020Form"; %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% String jusin = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.TAB_DSP_MODE_JUSIN); %>
<% String sosin = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.TAB_DSP_MODE_SOSIN); %>
<% String soko = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.TAB_DSP_MODE_SOKO); %>
<% String gomi = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.TAB_DSP_MODE_GOMIBAKO); %>

<% String unopend = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.OPKBN_UNOPENED); %>
<% String opend = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.OPKBN_OPENED); %>
<% String toroku = String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_TOROKU); %>
<% String delete = String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE); %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cmn.shortmail" /><gsmsg:write key="cmn.list" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>

<html:form method="post" action="/mobile/mh_sml020">
<html:hidden property="smlViewAccount" />
<html:hidden property="smlViewAccountName" />
<html:hidden property="sml010ProcMode" />
<html:hidden property="sml010ProcMode" />
<html:hidden property="sml020PageNum" />
<html:hidden property="sml020SelectedSid" />



<b><%= emojiMail.toString() %><gsmsg:write key="cmn.shortmail" /><br>
<bean:write name="mbhSml020Form"  property="smlViewAccountName" /><br>

<logic:equal name="mbhSml020Form" property="sml010ProcMode" value="0"><gsmsg:write key="cmn.receive" /><gsmsg:write key="cmn.list" /></logic:equal>
<logic:equal name="mbhSml020Form" property="sml010ProcMode" value="1"><gsmsg:write key="cmn.sent" /><gsmsg:write key="cmn.list" /></logic:equal>
<logic:equal name="mbhSml020Form" property="sml010ProcMode" value="2"><gsmsg:write key="cmn.draft" /><gsmsg:write key="cmn.list" /></logic:equal>
<logic:equal name="mbhSml020Form" property="sml010ProcMode" value="4"><gsmsg:write key="cmn.trash" /></logic:equal>
</b>

<hr>

<div align="center">
<!--
  <a href="./mh_sml020.do<%= jsessionId.toString() %>?dispParam=recept&smlViewAccount=<bean:write name="mbhSml020Form" property="smlViewAccount" />"><gsmsg:write key="cmn.previous" /></a>(<bean:write name="mbhSml020Form" property="sml020PageStr" />)<a href="#"><gsmsg:write key="cmn.next" /></a>&nbsp;&nbsp;<a href="./mh_sml010<%= jsessionId.toString() %>.do"><input name="add_joint" value="<gsmsg:write key="cmn.back" />" type="button"></a>
-->
  <input type="submit" name="sml020Mae" value="<gsmsg:write key="cmn.previous" />" />(<bean:write name="mbhSml020Form" property="sml020PageStr" />)<input type="submit" name="sml020Tugi" value="<gsmsg:write key="cmn.next" />" /></a>

</div>

<logic:notEmpty name="mbhSml020Form" property="sml020SmlList" scope="request">
<logic:iterate id="msg" name="mbhSml020Form" property="sml020SmlList" indexId="idx" scope="request">


  <logic:equal name="mbhSml020Form" property="sml010ProcMode" value="<%= jusin %>">
    <logic:equal name="msg" property="smjOpkbn" value="<%= unopend %>"><b></logic:equal>
    <logic:notEqual name="msg" property="smjOpkbn" value="<%= unopend %>"><span style="color:#990099"></logic:notEqual>
    <bean:write name="msg" property="strSdate" />
    <br>
    ■<gsmsg:write key="cmn.sender" />：<logic:equal name="msg" property="usrJkbn" value="<%= toroku %>"><bean:write name="msg" property="usiSei" /><bean:write name="msg" property="usiMei" /></logic:equal><logic:equal name="msg" property="usrJkbn" value="<%= delete %>"><del><bean:write name="msg" property="usiSei" /><bean:write name="msg" property="usiMei" /></del></logic:equal>

    <br>
    ■<gsmsg:write key="cmn.subject" />：
    <logic:notEqual name="msg" property="fwKbn" value="0">
      <img alt="Re" src="../mobile/images/img_forward.gif">
    </logic:notEqual>
    <logic:notEqual name="msg" property="returnKbn" value="0">
      <img alt="Re" src="../mobile/images/img_henshin.gif">
    </logic:notEqual>
    <a href="./mh_sml030.do<%= jsessionId.toString() %>?sml010ProcMode=<%= jusin %>&smlViewAccount=<bean:write name="mbhSml020Form" property="smlViewAccount" />&sml020SelectedSid=<bean:write name="msg" property="smlSid" />&sml020PageNum=<bean:write name="mbhSml020Form" property="sml020PageNum" />">
    <logic:notEqual name="msg" property="smjOpkbn" value="<%= unopend %>"><span style="color:#990099"></logic:notEqual>
    <bean:write name="msg" property="smsTitle" filter="false" />
    <logic:notEqual name="msg" property="smjOpkbn" value="<%= unopend %>"></span></logic:notEqual>
    </a>
    <logic:equal name="msg" property="smjOpkbn" value="<%= unopend %>"></b></logic:equal>
    <logic:notEqual name="msg" property="smjOpkbn" value="<%= unopend %>"></span></logic:notEqual>
    <hr>
  </logic:equal>


  <logic:equal name="mbhSml020Form" property="sml010ProcMode" value="<%= sosin %>">
    <bean:write name="msg" property="strSdate" />
    <br>
    ■<gsmsg:write key="cmn.subject" />：<a href="./mh_sml030.do<%= jsessionId.toString() %>?sml010ProcMode=<%= sosin %>&smlViewAccount=<bean:write name="mbhSml020Form" property="smlViewAccount" />&sml020SelectedSid=<bean:write name="msg" property="smlSid" />&sml020PageNum=<bean:write name="mbhSml020Form" property="sml020PageNum" />"><bean:write name="msg" property="smsTitle" filter="false" /></a>
    <br>
    ■<gsmsg:write key="cmn.from" />
    <br>
    <logic:notEmpty name="msg" property="atesakiList" scope="page">
    <logic:iterate id="atesaki" name="msg" property="atesakiList" indexId="idx_at" scope="page">

      <logic:notEqual name="atesaki" property="usrSid" value="0">

      <logic:equal name="atesaki" property="usrJkbn" value="<%= toroku %>">
      <bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" />
      </logic:equal>

      <logic:equal name="atesaki" property="usrJkbn" value="<%= delete %>">
      <del><bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" /></del>
      </logic:equal>

      </logic:notEqual>

      <logic:equal name="atesaki" property="usrSid" value="0">

      <logic:equal name="atesaki" property="accountJkbn" value="<%= toroku %>">
      <bean:write name="atesaki" property="accountName" />
      </logic:equal>

      <logic:equal name="atesaki" property="accountJkbn" value="1">
      <del><bean:write name="atesaki" property="accountName" /></del>
      </logic:equal>

      </logic:equal>

    <br>
    </logic:iterate>
    </logic:notEmpty>
    <hr>
  </logic:equal>
  <logic:equal name="mbhSml020Form" property="sml010ProcMode" value="<%= soko %>">
    <bean:write name="msg" property="strSdate" />
    <br>
    ■<gsmsg:write key="cmn.subject" />：<a href="./mh_sml040.do<%= jsessionId.toString() %>?sml010ProcMode=<%= soko %>&smlViewAccount=<bean:write name="mbhSml020Form" property="smlViewAccount" />&smlViewAccount=<bean:write name="mbhSml020Form" property="smlViewAccount" />&sml020SelectedSid=<bean:write name="msg" property="smlSid" />&sml020PageNum=<bean:write name="mbhSml020Form" property="sml020PageNum" />"><bean:write name="msg" property="smsTitle" filter="false" /></a>
    <br>
    ■<gsmsg:write key="cmn.from" />
    <br>
    <logic:notEmpty name="msg" property="atesakiList" scope="page">
    <logic:iterate id="atesaki" name="msg" property="atesakiList" indexId="idx_at" scope="page">
      <logic:notEqual name="atesaki" property="usrSid" value="0">
      <logic:equal name="atesaki" property="usrJkbn" value="<%= toroku %>">
      <bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" />
      </logic:equal>

      <logic:equal name="atesaki" property="usrJkbn" value="<%= delete %>">
      <del><bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" /></del>
      </logic:equal>
      </logic:notEqual>
      <logic:equal name="atesaki" property="usrSid" value="0">

      <logic:equal name="atesaki" property="accountJkbn" value="<%= toroku %>">
      <bean:write name="atesaki" property="accountName" />
      </logic:equal>

      <logic:equal name="atesaki" property="accountJkbn" value="1">
      <del><bean:write name="atesaki" property="accountName" /></del>
      </logic:equal>
      </logic:equal>

    <br>
    </logic:iterate>
    </logic:notEmpty>
    <hr>
  </logic:equal>

  <logic:equal name="mbhSml020Form" property="sml010ProcMode" value="<%= gomi %>">
    <bean:write name="msg" property="strSdate" />
    <br>
    ■
    <logic:equal name="msg" property="mailKbn" value="<%= jusin %>">[ <gsmsg:write key="cmn.receive2" /> ]</logic:equal>
    <logic:equal name="msg" property="mailKbn" value="<%= sosin %>">[ <gsmsg:write key="cmn.sent2" /> ]</logic:equal>
    <logic:equal name="msg" property="mailKbn" value="<%= soko %>">[ <gsmsg:write key="cmn.draft3" /> ]</logic:equal>
    <br>
        <gsmsg:write key="cmn.subject" />：
    <a href="./mh_sml030.do<%= jsessionId.toString() %>?sml010ProcMode=<%= gomi %>&smlViewAccount=<bean:write name="mbhSml020Form" property="smlViewAccount" />&sml020SelectedSid=<bean:write name="msg" property="smlSid" />&sml020PageNum=<bean:write name="mbhSml020Form" property="sml020PageNum" />&sml020SelectedMailKbn=<bean:write name="msg" property="mailKbn" />">

        <logic:equal name="msg" property="mailKbn" value="0">
        <logic:notEqual name="msg" property="smjOpkbn" value="<%= unopend %>"><span style="color:#990099"></logic:notEqual>
        </logic:equal>

        <logic:notEqual name="msg" property="mailKbn" value="0">
        <span style="color:#990099">
        </logic:notEqual>

        <bean:write name="msg" property="smsTitle" filter="false" />

        <logic:equal name="msg" property="mailKbn" value="0">
        <logic:notEqual name="msg" property="smjOpkbn" value="<%= unopend %>"></span></logic:notEqual>
        <logic:equal name="msg" property="smjOpkbn" value="<%= unopend %>"></b></logic:equal>
        <logic:notEqual name="msg" property="smjOpkbn" value="<%= unopend %>"></span></logic:notEqual>
        </logic:equal>

        <logic:notEqual name="msg" property="mailKbn" value="0">
        </span>
        </logic:notEqual>

    </a>
    <br>
    ■<gsmsg:write key="cmn.sender" />：<logic:equal name="msg" property="usrJkbn" value="<%= toroku %>"><bean:write name="msg" property="usiSei" /><bean:write name="msg" property="usiMei" /></logic:equal><logic:equal name="msg" property="usrJkbn" value="<%= delete %>"><del><bean:write name="msg" property="usiSei" /><bean:write name="msg" property="usiMei" /></del></logic:equal>
    <br>
    <hr>
  </logic:equal>
</logic:iterate>
</logic:notEmpty>

<div align="center">
<!--
  <a href="./mh_sml020.do<%= jsessionId.toString() %>?dispParam=recept&smlViewAccount=<bean:write name="mbhSml020Form" property="smlViewAccount" />"><gsmsg:write key="cmn.previous" /></a>(<bean:write name="mbhSml020Form" property="sml020PageStr" />)<a href="#"><gsmsg:write key="cmn.next" /></a>&nbsp;&nbsp;<a href="./mh_sml010<%= jsessionId.toString() %>.do"><input name="add_joint" value="<gsmsg:write key="cmn.back" />" type="button"></a>
-->
  <input type="submit" name="sml020Mae" value="<gsmsg:write key="cmn.previous" />" />(<bean:write name="mbhSml020Form" property="sml020PageStr" />)<input type="submit" name="sml020Tugi" value="<gsmsg:write key="cmn.next" />" /></a>&nbsp;&nbsp;<input name="sml020Back" value="<gsmsg:write key="cmn.back" />" type="submit">

</div>

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>