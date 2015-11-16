<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String markNone = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_NONE); %>
<% String markTel = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_TEL); %>
<% String markImp = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_INP); %>
<% String markSmaily    = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_SMAILY);  %>
<% String markWorry     = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_WORRY);   %>
<% String markAngry     = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_ANGRY);   %>
<% String markSadly     = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_SADRY);   %>
<% String markBeer      = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_BEER);    %>
<% String markHart      = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_HART);    %>
<% String markZasetsu   = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_ZASETSU); %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhSml040Form"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cmn.shortmail" />(<gsmsg:write key="cmn.create.new" />)</title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>

<html:form method="POST" action="/mobile/mh_sml040">
<html:hidden property="smlViewAccount" />
<html:hidden property="smlViewAccountName" />
<html:hidden property="sml010ProcMode" />
<html:hidden property="sml040ProcMode" />
<html:hidden property="sml020PageNum" />
<html:hidden property="sml020SelectedSid" />
<html:hidden property="sml040InitFlg" />
<html:hidden property="sml040SelectMode" />

<logic:notEmpty name="mbhSml040Form" property="sml040userSid">
<logic:iterate id="usid" name="mbhSml040Form" property="sml040userSid">
  <input type="hidden" name="sml040userSid" value="<bean:write name="usid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhSml040Form" property="sml040userSidCc">
<logic:iterate id="usidcc" name="mbhSml040Form" property="sml040userSidCc">
  <input type="hidden" name="sml040userSidCc" value="<bean:write name="usidcc" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhSml040Form" property="sml040userSidBcc">
<logic:iterate id="usidbcc" name="mbhSml040Form" property="sml040userSidBcc">
  <input type="hidden" name="sml040userSidBcc" value="<bean:write name="usidbcc" />">
</logic:iterate>
</logic:notEmpty>

<b><%= emojiMail.toString() %><gsmsg:write key="cmn.shortmail" /><br><gsmsg:write key="cmn.create.new" /></b>

<hr>

<logic:messagesPresent message="false">
<span style="color: red"><html:errors/></span>
</logic:messagesPresent>
<logic:notEmpty name="mbhSml040Form" property="sml030AtesakiDeletedMessage">
<span style="color: red"><bean:write name="mbhSml040Form" property="sml030AtesakiDeletedMessage" filter="false"/></span>
<br />
<br />
</logic:notEmpty>

■<gsmsg:write key="cmn.sender" />：<bean:write name="mbhSml040Form"  property="smlViewAccountName" />
<br>
■<gsmsg:write key="sml.sml020.05" /><input name="sml040Select" value="<gsmsg:write key="sml.sml020.05" />" type="submit"/>
<br>
<logic:notEmpty name="mbhSml040Form" property="sml040SelectUserList">
  <logic:iterate id="usrLabel" name="mbhSml040Form" property="sml040SelectUserList" indexId="idx">
    <input name="TO_<bean:write name="usrLabel" property="value" />" value="<gsmsg:write key="cmn.delete" />" type="submit">
    <bean:write name="usrLabel" property="label" /><br>
  </logic:iterate>
</logic:notEmpty>

■CC<input name="sml040CcSelect" value="<gsmsg:write key="sml.sml020.02" />" type="submit"/>
<br>
<logic:notEmpty name="mbhSml040Form" property="sml040CcSelectUserList">
  <logic:iterate id="usrCcLabel" name="mbhSml040Form" property="sml040CcSelectUserList" indexId="idx">
    <input name="CC_<bean:write name="usrCcLabel" property="value" />" value="<gsmsg:write key="cmn.delete" />" type="submit">
    <bean:write name="usrCcLabel" property="label" /><br>
  </logic:iterate>
</logic:notEmpty>

■BCC<input name="sml040BccSelect" value="<gsmsg:write key="sml.sml020.01" />" type="submit"/>
<br>
<logic:notEmpty name="mbhSml040Form" property="sml040BccSelectUserList">
  <logic:iterate id="usrBccLabel" name="mbhSml040Form" property="sml040BccSelectUserList" indexId="idx">
    <input name="BCC_<bean:write name="usrBccLabel" property="value" />" value="<gsmsg:write key="cmn.delete" />" type="submit">
    <bean:write name="usrBccLabel" property="label" /><br>
  </logic:iterate>
</logic:notEmpty>


■<gsmsg:write key="cmn.subject" />
<br>
<html:text name="mbhSml040Form" property="sml040Title" size="27"  maxlength="70" />
<br>


■<gsmsg:write key="cmn.mark" />
<br>

<html:select name="mbhSml040Form" property="sml040Mark">
<logic:notEmpty name="mbhSml040Form" property="sml040MarkLabel">
  <html:optionsCollection name="mbhSml040Form" property="sml040MarkLabel" value="value" label="label" />
</logic:notEmpty>
</html:select>

<br>
■<gsmsg:write key="cmn.body" />
<br>
<html:textarea name="mbhSml040Form" property="sml040Body" cols="16" rows="3" ></html:textarea>
<br>
<div align="center">
  <br><input name="sml040Ok" value="OK" type="submit"><input name="sml040Hozon" value="<gsmsg:write key="cmn.draft" /><gsmsg:write key="mobile.20" />" type="submit"><input name="sml040Back" value="<gsmsg:write key="cmn.back" />" type="submit">
</div>

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>