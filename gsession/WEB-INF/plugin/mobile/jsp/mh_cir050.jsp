<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhCir050Form"; %>
<%@page import="jp.groupsession.v2.cir.GSConstCircular"%>
<%@page import="jp.groupsession.v2.cmn.GSConst"%>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cir.5" /><gsmsg:write key="cmn.create.new" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>

</head>

<!--　BODY -->
<body>

<html:form method="post" action="/mobile/mh_cir050">

<html:hidden property="cirViewAccount" />
<html:hidden property="cirViewAccountName" />
<html:hidden property="cir020cmdMode" />
<html:hidden property="cir020orderKey" />
<html:hidden property="cir020sortKey" />
<html:hidden property="cir020pageNum1" />
<html:hidden property="cir020pageNum2" />
<html:hidden property="cir050pluginId" />
<html:hidden property="cir050memoKbn" />
<html:hidden property="cir050memoPeriod" />
<html:hidden property="cir050memoPeriodYear" />
<html:hidden property="cir050memoPeriodMonth" />
<html:hidden property="cir050memoPeriodDay" />
<html:hidden property="cir050InitFlg" />
<html:hidden property="cir060dspId" />
<html:hidden property="cir020selectInfSid" />

<logic:notEmpty name="mbhCir050Form" property="cir050userSid" scope="request">
<logic:iterate id="users" name="mbhCir050Form" property="cir050userSid" indexId="idx" scope="request">
  <bean:define id="userSid" name="users" type="java.lang.String" />
  <html:hidden property="cir050userSid" value="<%= userSid %>" />
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhCir050Form" property="cir020delInfSid" scope="request">
<logic:iterate id="item" name="mbhCir050Form" property="cir020delInfSid" scope="request">
  <input type="hidden" name="cir020delInfSid" value="<bean:write name="item"/>">
</logic:iterate>
</logic:notEmpty>

<b><%= emojiMemo.toString() %><gsmsg:write key="cir.5" /><br><gsmsg:write key="cmn.create.new" /></b>

<hr>

<logic:messagesPresent message="false">
  <html:errors/><br>
</logic:messagesPresent>

■<gsmsg:write key="cir.2" />:<bean:write name="mbhCir050Form" property="cirViewAccountName" /><br>

■<gsmsg:write key="cir.20" />
<input type="submit" value="<gsmsg:write key="sml.sml020.05" />" class="btn_user_n1" name="seluser">

<logic:notEmpty name="mbhCir050Form" property="cir050MemberList" scope="request">
<br>
      <logic:iterate id="memMdl" name="mbhCir050Form" property="cir050MemberList" scope="request">
        <span class="text_base">

        <logic:greaterThan name="memMdl" property="cacSid" value="0">


          <logic:greaterThan name="memMdl" property="usrSid" value="0">
            <input name="<bean:write name="memMdl" property="usrSid" />" value="<gsmsg:write key="cmn.delete" />" type="submit">
            <bean:write name="memMdl" property="cacName" />
          </logic:greaterThan>

          <logic:lessThan name="memMdl" property="usrSid" value="1">
            <input name="cac<bean:write name="memMdl" property="cacSid" />" value="<gsmsg:write key="cmn.delete" />" type="submit">
            <bean:write name="memMdl" property="cacName" />
          </logic:lessThan>

        </logic:greaterThan>

        </span><br>

      </logic:iterate>

</logic:notEmpty>

<logic:empty name="mbhCir050Form" property="cir050MemberList" scope="request">

</logic:empty>







<br>

<!-- タイトル -->
■<gsmsg:write key="cmn.title" /><br>
<html:text size="27" property="cir050title" styleClass="text_base"  maxlength="70"/>

<br>

<!-- 内容 -->
■<gsmsg:write key="cmn.content" />
<br>
<textarea class="text_base" name="cir050value" cols="16" rows="3" id="inputstr"><bean:write name="mbhCir050Form" property="cir050value" /></textarea>
<br>

<br>

<logic:equal name="mbhCir050Form" property="cir050memoKbn" value="0">
<!-- メモ欄修正区分 -->
<span class="text_bb1">■<gsmsg:write key="cir.cir040.2" /></span>
<input name="setpriod" value="<gsmsg:write key="cmn.setting.do" />" type="submit"><br>
<span class="text_base"><gsmsg:write key="cmn.not" /></span>
</logic:equal>

<logic:equal name="mbhCir050Form" property="cir050memoKbn" value="1">
<!-- メモ欄修正区分 -->
<span class="text_bb1">■<gsmsg:write key="cir.cir040.2" /></span><input name="noset" value="<gsmsg:write key="cmn.noset" />" type="submit"><br>

<!-- 日付指定の場合 -->
<bean:write name="mbhCir050Form" property="cir050memoPeriodYear" /><gsmsg:write key="cmn.year2" />
<bean:write name="mbhCir050Form" property="cir050memoPeriodMonth" /><gsmsg:write key="cmn.month" />
<bean:write name="mbhCir050Form" property="cir050memoPeriodDay" /><gsmsg:write key="cmn.day" />
<gsmsg:write key="cir.56" arg0="" /><br>
<input name="cir070change" value="<gsmsg:write key="cmn.change" />" type="submit">

</logic:equal>

<%--
<!-- 添付 -->
<span class="text_bb1"><gsmsg:write key="cmn.attached" /></span>
<img src="../common/images/btn_dell.gif" alt="<gsmsg:write key="cmn.delete" />" height="25" width="60" onClick="buttonPush('delete');">&nbsp;<a href="javascript:void(0);" onClick="opnTemp('cir040selectFiles', '<bean:write name="mbhCir050Form" property="cir050pluginId" />', '0', '0');"><img alt="<gsmsg:write key="cmn.attached" />" height="25" src="../common/images/btn_attach.gif" width="60" border="0"></a>
<br>
<html:select property="cir050selectFiles" styleClass="select01" multiple="true">
<html:optionsCollection name="mbhCir050Form" property="cir050FileLabelList" value="value" label="label" />
</html:select>
--%>

<br>
<br>

<!-- 公開／非公開   -->
■<gsmsg:write key="cir.cir030.3" /><br>

<html:radio name="mbhCir050Form" property="cir050show" styleId="kokai"   value="0" /><label for="kokai"  ><span class="text_base"><gsmsg:write key="cmn.public" /></span>&nbsp;&nbsp;</label>
<html:radio name="mbhCir050Form" property="cir050show" styleId="hikokai" value="1" /><label for="hikokai"><span class="text_base"><gsmsg:write key="cmn.private" /></span></label>

<br>
<br>

<input type="submit" name="okbtn" value="OK" class="btn_ok1">
<input type="submit" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" name="cir050Back">

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>