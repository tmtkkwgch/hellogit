<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>

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
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="cmn.shortmail" /><gsmsg:write key="cmn.list" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "sml"; %>
<% thisForm = "mbhSml020Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form method="post" action="/mobile/sp_sml020">

<input type="hidden" name="CMD" value="">
<html:hidden property="smlViewAccount" />
<html:hidden property="smlViewAccountName" />
<html:hidden property="mobileType" value="1"/>
<html:hidden property="sml010ProcMode" />
<html:hidden property="sml020PageNum" />
<html:hidden property="sml020SelectedSid" />
<bean:define id="maxPage" name="mbhSml020Form" property="sml020MaxPageNum" />

<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<a href="#" onClick="buttonPush('sml020back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
<img src="../mobile/sp/imgages/sml_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="cmn.shortmail" /><br>
    <logic:equal name="mbhSml020Form" property="sml010ProcMode" value="0"><gsmsg:write key="cmn.receive" /><gsmsg:write key="cmn.list" /></logic:equal>
    <logic:equal name="mbhSml020Form" property="sml010ProcMode" value="1"><gsmsg:write key="cmn.sent" /><gsmsg:write key="cmn.list" /></logic:equal>
    <logic:equal name="mbhSml020Form" property="sml010ProcMode" value="2"><gsmsg:write key="cmn.draft" /><gsmsg:write key="cmn.list" /></logic:equal>
    <logic:equal name="mbhSml020Form" property="sml010ProcMode" value="4"><gsmsg:write key="cmn.trash" /></logic:equal>
  </h1>
  <a href="../mobile/sp_man001.do?mobileType=1&smlViewAccount=<bean:write name="mbhSml020Form" property="smlViewAccount" />" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">


<div align="center">
<!--
  <a href="./sp_sml020.do?mobileType=1&dispParam=recept"><gsmsg:write key="cmn.previous" /></a>(<bean:write name="mbhSml020Form" property="sml020PageStr" />)<a href="#"><gsmsg:write key="cmn.next" /></a>&nbsp;&nbsp;<a href="./sp_sml010.do?mobileType=1"><input name="add_joint" value="<gsmsg:write key="cmn.back" />" type="button"></a>
-->

<div data-role="controlgroup" data-type="horizontal" align="center">
<logic:notEmpty name="mbhSml020Form" property="sml020SmlList">
<logic:greaterThan name="maxPage" value="1">
  <logic:notEqual name="mbhSml020Form" property="sml020PageNum" value="1">
  <logic:notEqual name="mbhSml020Form" property="sml020PageNum" value="<%= maxPage.toString() %>">
    <input type="submit" name="sml020Mae" value="<gsmsg:write key="cmn.previous" />" data-icon="arrow-l"/><input type="submit" name="sml020Tugi" value="<gsmsg:write key="cmn.next" />" data-icon="arrow-r" data-iconpos="right"/>
  </logic:notEqual>
  </logic:notEqual>
  <logic:equal name="mbhSml020Form" property="sml020PageNum" value="1">
    <input type="submit" name="sml020Tugi" value="<gsmsg:write key="cmn.next" />" data-icon="arrow-r" data-iconpos="right"/>
  </logic:equal>
  <logic:equal name="mbhSml020Form" property="sml020PageNum" value="<%= maxPage.toString()%>">
    <input type="submit" name="sml020Mae" value="<gsmsg:write key="cmn.previous" />" data-icon="arrow-l"/>
  </logic:equal>
</logic:greaterThan>
</logic:notEmpty>
</div>


</div>


<logic:notEmpty name="mbhSml020Form" property="sml020SmlList" scope="request">

<ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">

  <li data-role="list-divider">
    <div align="center">
      <bean:write name="mbhSml020Form"  property="smlViewAccountName" /><br>
      <b>
        <logic:equal name="mbhSml020Form" property="sml010ProcMode" value="0"><gsmsg:write key="cmn.receive" /><gsmsg:write key="cmn.list" /></logic:equal>
        <logic:equal name="mbhSml020Form" property="sml010ProcMode" value="1"><gsmsg:write key="cmn.sent" /><gsmsg:write key="cmn.list" /></logic:equal>
        <logic:equal name="mbhSml020Form" property="sml010ProcMode" value="2"><gsmsg:write key="cmn.draft" /><gsmsg:write key="cmn.list" /></logic:equal>
        <logic:equal name="mbhSml020Form" property="sml010ProcMode" value="4"><gsmsg:write key="cmn.trash" /></logic:equal>
      </b>
      <logic:greaterThan name="maxPage" value="1"><div class="font_small">(<bean:write name="mbhSml020Form" property="sml020PageNum" />/<bean:write name="mbhSml020Form" property="sml020MaxPageNum" />)</div></logic:greaterThan>
    </div>
  </li>

  <bean:define id="mod" value="0" />
  <logic:iterate id="msg" name="mbhSml020Form" property="sml020SmlList" indexId="idx" scope="request">

    <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
      <bean:define id="liColor" value="c" />
    </logic:equal>
    <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
      <bean:define id="liColor" value="d" />
    </logic:notEqual>

    <li data-theme=<bean:write name="liColor" />>

      <logic:equal name="mbhSml020Form" property="sml010ProcMode" value="<%= jusin %>">
        <a href="./sp_sml030.do?mobileType=1&sml010ProcMode=<%= jusin %>&smlViewAccount=<bean:write name="mbhSml020Form" property="smlViewAccount" />&sml020SelectedSid=<bean:write name="msg" property="smlSid" />&sml020PageNum=<bean:write name="mbhSml020Form" property="sml020PageNum" />">
        <logic:equal name="msg" property="smjOpkbn" value="<%= unopend %>"><b></logic:equal>
        <logic:notEqual name="msg" property="smjOpkbn" value="<%= unopend %>"><span style="color:#990099"></logic:notEqual>
        <div class="font_small"><bean:write name="msg" property="strSdate" /></div>
        <div class="font_small">■<gsmsg:write key="cmn.sender" />：<logic:equal name="msg" property="usrJkbn" value="<%= toroku %>"><bean:write name="msg" property="usiSei" /><bean:write name="msg" property="usiMei" /></logic:equal><logic:equal name="msg" property="usrJkbn" value="<%= delete %>"><del><bean:write name="msg" property="usiSei" /><bean:write name="msg" property="usiMei" /></del></logic:equal></div>
        <div class="font_small">■<gsmsg:write key="cmn.subject" /></div>
        <logic:notEqual name="msg" property="fwKbn" value="0">
          <img alt="Fw" src="../mobile/images/img_forward.gif">
        </logic:notEqual>
        <logic:notEqual name="msg" property="returnKbn" value="0">
          <img alt="Re" src="../mobile/images/img_henshin.gif">
        </logic:notEqual>
        <logic:notEqual name="msg" property="smjOpkbn" value="<%= unopend %>"><span style="color:#990099"></logic:notEqual>
        <bean:write name="msg" property="smsTitle" filter="false" />
        <logic:notEqual name="msg" property="smjOpkbn" value="<%= unopend %>"></span></logic:notEqual>
        <logic:equal name="msg" property="smjOpkbn" value="<%= unopend %>"></b></logic:equal>
        <logic:notEqual name="msg" property="smjOpkbn" value="<%= unopend %>"></span></logic:notEqual>
        </a>
      </logic:equal>


      <logic:equal name="mbhSml020Form" property="sml010ProcMode" value="<%= sosin %>">
        <a href="./sp_sml030.do?mobileType=1&sml010ProcMode=<%= sosin %>&smlViewAccount=<bean:write name="mbhSml020Form" property="smlViewAccount" />&sml020SelectedSid=<bean:write name="msg" property="smlSid" />&sml020PageNum=<bean:write name="mbhSml020Form" property="sml020PageNum" />">
        <div class="font_small"><bean:write name="msg" property="strSdate" /></div>
        <div class="font_small">■<gsmsg:write key="cmn.subject" />：</div><bean:write name="msg" property="smsTitle" filter="false" />
        <div class="font_small">■<gsmsg:write key="cmn.from" /><br>
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
        </div>
        </a>
      </logic:equal>

      <logic:equal name="mbhSml020Form" property="sml010ProcMode" value="<%= soko %>">
        <a href="./sp_sml040.do?mobileType=1&sml010ProcMode=<%= soko %>&smlViewAccount=<bean:write name="mbhSml020Form" property="smlViewAccount" />&sml020SelectedSid=<bean:write name="msg" property="smlSid" />&sml020PageNum=<bean:write name="mbhSml020Form" property="sml020PageNum" />">
        <div class="font_small"><bean:write name="msg" property="strSdate" /></div>
        <div class="font_small">■<gsmsg:write key="cmn.subject" />：</div><bean:write name="msg" property="smsTitle" filter="false"/>
        <div class="font_small">■<gsmsg:write key="cmn.from" />
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
        </div>
        </a>
      </logic:equal>

      <logic:equal name="mbhSml020Form" property="sml010ProcMode" value="<%= gomi %>">
        <a href="./sp_sml030.do?mobileType=1&sml010ProcMode=<%= gomi %>&sml020SelectedSid=<bean:write name="msg" property="smlSid" />&sml020PageNum=<bean:write name="mbhSml020Form" property="sml020PageNum" />&sml020SelectedMailKbn=<bean:write name="msg" property="mailKbn" />">
        <div class="font_small"><bean:write name="msg" property="strSdate" /></div>
        <div class="font_small">■
        <logic:equal name="msg" property="mailKbn" value="<%= jusin %>">[ <gsmsg:write key="cmn.receive2" /> ]</logic:equal>
        <logic:equal name="msg" property="mailKbn" value="<%= sosin %>">[ <gsmsg:write key="cmn.sent2" /> ]</logic:equal>
        <logic:equal name="msg" property="mailKbn" value="<%= soko %>">[ <gsmsg:write key="cmn.draft3" /> ]</logic:equal>
        </div>
        <div class="font_small">■<gsmsg:write key="cmn.sender" />：<logic:equal name="msg" property="usrJkbn" value="<%= toroku %>"><bean:write name="msg" property="usiSei" /><bean:write name="msg" property="usiMei" /></logic:equal><logic:equal name="msg" property="usrJkbn" value="<%= delete %>"><del><bean:write name="msg" property="usiSei" /><bean:write name="msg" property="usiMei" /></del></logic:equal>
        <br>
        ■<gsmsg:write key="cmn.subject" /></div>

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
      </logic:equal>

    </li>

  </logic:iterate>

</ul>

</logic:notEmpty>

<div align="center">
<!--
  <a href="./sp_sml020.do?mobileType=1&dispParam=recept"><gsmsg:write key="cmn.previous" /></a>(<bean:write name="mbhSml020Form" property="sml020PageStr" />)<a href="#"><gsmsg:write key="cmn.next" /></a>&nbsp;&nbsp;<a href="./sp_sml010.do?mobileType=1"><input name="add_joint" value="<gsmsg:write key="cmn.back" />" type="button"></a>
-->

<div data-role="controlgroup" data-type="horizontal" align="center">
<logic:notEmpty name="mbhSml020Form" property="sml020SmlList">
<logic:greaterThan name="maxPage" value="1">
  <logic:notEqual name="mbhSml020Form" property="sml020PageNum" value="1">
  <logic:notEqual name="mbhSml020Form" property="sml020PageNum" value="<%= maxPage.toString() %>">
    <input type="submit" name="sml020Mae" value="<gsmsg:write key="cmn.previous" />" data-icon="arrow-l"/><input type="submit" name="sml020Tugi" value="<gsmsg:write key="cmn.next" />" data-icon="arrow-r" data-iconpos="right"/>
  </logic:notEqual>
  </logic:notEqual>
  <logic:equal name="mbhSml020Form" property="sml020PageNum" value="1">
    <input type="submit" name="sml020Tugi" value="<gsmsg:write key="cmn.next" />" data-icon="arrow-r" data-iconpos="right"/>
  </logic:equal>
  <logic:equal name="mbhSml020Form" property="sml020PageNum" value="<%= maxPage.toString()%>">
    <input type="submit" name="sml020Mae" value="<gsmsg:write key="cmn.previous" />" data-icon="arrow-l"/>
  </logic:equal>
</logic:greaterThan>
</logic:notEmpty>
</div>

<div align="center">
  <input name="sml020Back" value="<gsmsg:write key="cmn.back" />" type="submit" data-inline="true" data-role="button" data-icon="back" />
</div>

</div>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>
</div><!-- /page -->

</body>
</html:html>