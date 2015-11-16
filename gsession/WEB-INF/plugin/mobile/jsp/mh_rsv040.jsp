<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhRsv040Form"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cmn.reserve" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>

<html:form action="/mobile/mh_rsv040">
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsvBackPgId" />
<html:hidden property="rsvSelectedGrpSid" />
<html:hidden property="rsvGrpFlg" />
<html:hidden property="rsv010SisetuId" />
<html:hidden property="rsv010DspMode" />
<html:hidden property="rsv020SisetuId" />
<html:hidden property="rsv020DspMode" />
<html:hidden property="rsv040DspMode" />

<b><%= emojiTokei.toString() %><gsmsg:write key="cmn.reserve" /><br>

<logic:equal name="mbhRsv040Form" property="rsvGrpFlg" value="0">
  <gsmsg:write key="cmn.facility" /><gsmsg:write key="cmn.change" />
</logic:equal>
<logic:equal name="mbhRsv040Form" property="rsvGrpFlg" value="1">
    <logic:equal name="mbhRsv040Form" property="rsv040DspMode" value="1">
     <gsmsg:write key="cmn.days2" />
    </logic:equal>
    <logic:equal name="mbhRsv040Form" property="rsv040DspMode" value="2">
      <gsmsg:write key="cmn.weeks" />
    </logic:equal>
    <logic:equal name="mbhRsv040Form" property="rsv040DspMode" value="0">
      <logic:equal name="mbhRsv040Form" property="rsvBackPgId" value="mbh_rsv010">
        <gsmsg:write key="cmn.days2" />
      </logic:equal>
      <logic:equal name="mbhRsv040Form" property="rsvBackPgId" value="mbh_rsv020">
        <gsmsg:write key="cmn.weeks" />
      </logic:equal>
    </logic:equal>
</logic:equal>
</b>

<hr>
<logic:equal name="mbhRsv040Form" property="rsvGrpFlg" value="1">
  <div align="center">
    <font size="-2">[施設を選択してください。]</font>
  </div>
</logic:equal>

<%= emojiPen.toString() %><span class="text_bb1"><gsmsg:write key="cmn.facility.group" /></span>
<br>

<html:select property="rsv040GrpSid" styleClass="select01">
  <html:optionsCollection name="mbhRsv040Form" property="rsvGrpLabelList" value="value" label="label" />
</html:select>


<input name="sch050Search" value="<gsmsg:write key="cmn.search" />" type="submit">

<br>

<logic:iterate id="sisetu" name="mbhRsv040Form" property="rsv040ReserveBelongLavel">

  <logic:equal name="mbhRsv040Form" property="rsvGrpFlg" value="0">
    <logic:equal name="mbhRsv040Form" property="rsvBackPgId" value="mbh_rsv010">
      <a href="../mobile/mh_rsv010.do<%= jsessionId.toString() %>?rsvDspFrom=<bean:write name="mbhRsv040Form" property="rsvDspFrom" />&rsvSelectedGrpSid=<bean:write name="mbhRsv040Form" property="rsv040GrpSid" />&rsv010SisetuId=<bean:write name="sisetu" property="rsdSid" />&rsv010DspMode=<bean:write name="mbhRsv040Form" property="rsv010DspMode" />"><bean:write name="sisetu" property="rsdName" /></a>
    </logic:equal>
    <logic:equal name="mbhRsv040Form" property="rsvBackPgId" value="mbh_rsv020">
      <a href="../mobile/mh_rsv020.do<%= jsessionId.toString() %>?rsvDspFrom=<bean:write name="mbhRsv040Form" property="rsvDspFrom" />&rsvSelectedGrpSid=<bean:write name="mbhRsv040Form" property="rsv040GrpSid" />&rsv020SisetuId=<bean:write name="sisetu" property="rsdSid" />&rsv020DspMode=<bean:write name="mbhRsv040Form" property="rsv020DspMode" />"><bean:write name="sisetu" property="rsdName" /></a>
    </logic:equal>
    <br>
  </logic:equal>


  <logic:equal name="mbhRsv040Form" property="rsvGrpFlg" value="1">
    <logic:equal name="mbhRsv040Form" property="rsv040DspMode" value="1">
      <a href="../mobile/mh_rsv020.do<%= jsessionId.toString() %>?rsvGrpFlg=1&rsvSelectedGrpSid=<bean:write name="mbhRsv040Form" property="rsv040GrpSid" />&rsv020SisetuId=<bean:write name="sisetu" property="rsdSid" />&rsv020DspMode=1"><bean:write name="sisetu" property="rsdName" /></a>
      <br>
    </logic:equal>

    <logic:equal name="mbhRsv040Form" property="rsv040DspMode" value="2">
      <a href="../mobile/mh_rsv010.do<%= jsessionId.toString() %>?rsvGrpFlg=1&rsvSelectedGrpSid=<bean:write name="mbhRsv040Form" property="rsv040GrpSid" />&rsv010SisetuId=<bean:write name="sisetu" property="rsdSid" />&rsv010DspMode=1"><bean:write name="sisetu" property="rsdName" /></a>
      <br>
    </logic:equal>

    <logic:equal name="mbhRsv040Form" property="rsv040DspMode" value="0">
      <logic:equal name="mbhRsv040Form" property="rsvBackPgId" value="mbh_rsv010">
        <a href="../mobile/mh_rsv010.do<%= jsessionId.toString() %>?rsvGrpFlg=1&rsvSelectedGrpSid=<bean:write name="mbhRsv040Form" property="rsv040GrpSid" />&rsv010SisetuId=<bean:write name="sisetu" property="rsdSid" />&rsv010DspMode=1"><bean:write name="sisetu" property="rsdName" /></a>
      </logic:equal>
      <logic:equal name="mbhRsv040Form" property="rsvBackPgId" value="mbh_rsv020">
        <a href="../mobile/mh_rsv020.do<%= jsessionId.toString() %>?rsvGrpFlg=1&rsvSelectedGrpSid=<bean:write name="mbhRsv040Form" property="rsv040GrpSid" />&rsv020SisetuId=<bean:write name="sisetu" property="rsdSid" />&rsv020DspMode=1"><bean:write name="sisetu" property="rsdName" /></a>
      </logic:equal>
      <br>
    </logic:equal>
  </logic:equal>

</logic:iterate>

<logic:equal name="mbhRsv040Form" property="rsvGrpFlg" value="0">
  <input name="rsv040back" value="戻る" type="submit">
</logic:equal>
</div>

<hr>

<logic:equal name="mbhRsv040Form" property="rsvGrpFlg" value="1">
  <a href="../mobile/mh_rsv010.do<%= jsessionId.toString() %>"><gsmsg:write key="cmn.group" /><gsmsg:write key="cmn.weeks" /></a>
  <br>
  <a href="../mobile/mh_rsv020.do<%= jsessionId.toString() %>"><gsmsg:write key="cmn.group" /><gsmsg:write key="cmn.days2" /></a>
  <br>
  <a href="../mobile/mh_rsv040.do<%= jsessionId.toString() %>?rsvGrpFlg=1&rsv040DspMode=2"><gsmsg:write key="cmn.facility" /><gsmsg:write key="cmn.weeks" /></a>
  <br>
  <a href="../mobile/mh_rsv040.do<%= jsessionId.toString() %>?rsvGrpFlg=1&rsv040DspMode=1"><gsmsg:write key="cmn.facility" /><gsmsg:write key="cmn.days2" /></a>


  <%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</logic:equal>

</html:form>

</body>
</html:html>