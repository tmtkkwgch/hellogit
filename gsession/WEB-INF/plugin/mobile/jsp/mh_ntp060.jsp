<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhNtp060Form"; %>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[Group Session] <gsmsg:write key="ntp.1" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/glayer.js"></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../nippou/js/ntp060.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../nippou/css/nippou.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href='../common/css/glayer.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

</head>

<body class="body_03" onunload="calWindowClose();">

<html:form action="/mobile/mh_ntp060">

<input type="hidden" name="CMD" value="">
<html:hidden property="ntp060NanSid" />
<html:hidden property="ntp060ProcMode" />
<html:hidden property="ntp060InitFlg" />
<html:hidden property="ntp060parentPageId" />
<html:hidden property="ntp060RowNumber" />
<html:hidden property="ntp060PageTop" />
<html:hidden property="cmd" />
<html:hidden property="dspMod" />
<html:hidden property="listMod" />
<html:hidden property="ntp040InitFlg" />
<html:hidden property="ntp040CopyFlg" />
<html:hidden property="ntp040ScrollFlg" />
<html:hidden property="ntp040BgcolorInit" />
<html:hidden property="ntp040DspMoveFlg" />

<html:hidden property="ntp040AnkenUse" />
<html:hidden property="ntp040CompanyUse" />
<html:hidden property="ntp040AnkenCompanyUse" />
<html:hidden property="ntp040KtBriHhuUse" />
<html:hidden property="ntp040MikomidoUse" />
<html:hidden property="ntp040TmpFileUse" />
<html:hidden property="ntp040NextActionUse" />
<html:hidden property="ntp040AdrHistoryPageNum" />
<html:hidden property="ntp040DefaultValue" />
<html:hidden property="ntp040DefaultValue2" />

<html:hidden property="ntp040InitYear" />
<html:hidden property="ntp040InitMonth" />
<html:hidden property="ntp040InitDay" />
<html:hidden property="ntp040ActYear" />
<html:hidden property="ntp040ActMonth" />
<html:hidden property="ntp040ActDay" />

<html:hidden property="ntp040HoukokuYear" />
<html:hidden property="ntp040HoukokuMonth" />
<html:hidden property="ntp040HoukokuDay" />

<html:hidden property="ntp040schUrl" />

<html:hidden property="ntp010DspDate" />
<html:hidden property="ntp010SelectUsrSid" />
<html:hidden property="ntp010SelectUsrAreaSid" />
<html:hidden property="ntp010SelectUsrKbn" />
<html:hidden property="ntp010SelectDate" />
<html:hidden property="ntp010NipSid" />
<html:hidden property="ntp010DspGpSid" />
<html:hidden property="ntp010searchWord" />
<html:hidden property="ntp020SelectUsrSid" />
<html:hidden property="ntp030SelectUsrSid" />


<logic:notEmpty name="mbhNtp060Form"  property="ntp040FileList">
<logic:iterate id="tempmdl" name="mbhNtp060Form"  property="ntp040FileList">
<html:hidden name="tempmdl" property="binFileName" />
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhNtp060Form"  property="ntp040targetPrmList">
<logic:iterate id="targetPrmStr" name="mbhNtp060Form"  property="ntp040targetPrmList">
<input type="hidden" name="ntp040targetPrmList" value="<bean:write name="targetPrmStr"/>">
</logic:iterate>
</logic:notEmpty>

<input type="hidden" name="ntp040delCompanyId" value="">
<input type="hidden" name="ntp040delCompanyBaseId" value="">

<html:hidden property="addressPluginKbn" />
<html:hidden property="searchPluginKbn" />


<html:hidden property="ntp040FrYear" />
<html:hidden property="ntp040FrMonth" />
<html:hidden property="ntp040FrDay" />
<html:hidden property="ntp040FrHour" />
<html:hidden property="ntp040FrMin" />
<html:hidden property="ntp040ToHour" />
<html:hidden property="ntp040ToMin" />
<html:hidden property="ntp040Ktbunrui" />
<html:hidden property="ntp040Kthouhou" />
<html:hidden property="ntp040UsrName" />
<html:hidden property="ntp040AnkenSid" />
<html:hidden property="ntp040CompanySid" />
<html:hidden property="ntp040CompanyBaseSid" />
<html:hidden property="ntp040Mikomido" />
<html:hidden property="ntp040Bgcolor" />
<html:hidden property="ntp040Title" />
<html:hidden property="ntp040Value" />
<html:hidden property="ntp040ActDateKbn" />
<html:hidden property="ntp040NxtActYear" />
<html:hidden property="ntp040NxtActMonth" />
<html:hidden property="ntp040NxtActDay" />
<html:hidden property="ntp040NextAction" />
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>



<b><%= emojiTokei.toString() %><gsmsg:write key="ntp.1" /><br><gsmsg:write key="ntp.11" /><gsmsg:write key="cmn.search" /></b>
<hr>




<%----------------------------------- 案件検索 -----------------------------------%>
<%--
<span class="text_bb2"><gsmsg:write key="ntp.29" /></span>
<html:text name="mbhNtp060Form" property="ntp060NanCode" size="16" maxlength="8" />

<br>
--%>
<span class="text_bb2"><gsmsg:write key="ntp.57" /></span>:
<html:text name="mbhNtp060Form" property="ntp060NanName" size="25" maxlength="50" />

<br>
<%--
<span class="text_bb2"><gsmsg:write key="address.7" /></span>
<html:text name="mbhNtp060Form" property="ntp060AcoCode" size="16" maxlength="8" />

<br>

<span class="text_bb2"><gsmsg:write key="cmn.company.name" /></span>
<html:text name="mbhNtp060Form" property="ntp060AcoName" size="50" maxlength="50" />

<br>

<span class="text_bb2"><gsmsg:write key="address.9" /></span>
<html:text name="mbhNtp060Form" property="ntp060AcoNameKana" size="30" maxlength="50" />

<br>

<span class="text_bb2"><gsmsg:write key="ntp.150" /></span>
<html:text name="mbhNtp060Form" property="ntp060AbaName" size="50" maxlength="50" />

<br>
--%>
<span class="text_bb2"><gsmsg:write key="ntp.71" /></span>:
<html:select name="mbhNtp060Form" property="ntp060State" styleClass="select01" style="width: 220px;">
  <logic:notEmpty name="mbhNtp060Form" property="ntp060StateList">
  <html:optionsCollection name="mbhNtp060Form" property="ntp060StateList" value="value" label="label" />
  </logic:notEmpty>
</html:select>

<br><br>

<div align="center">
  <input type="submit" name="ntp060search" value="<gsmsg:write key="cmn.search" />" />

  <logic:notEmpty name="mbhNtp060Form" property="ntp060AnkenList">

    <br><br>

    <bean:size id="count1" name="mbhNtp060Form" property="ntp060PageCmbList" scope="request" />
    <logic:greaterThan name="count1" value="1">



      <input name="ntp060prev" value="前頁" type="submit" />
        <bean:write name="mbhNtp060Form" property="ntp060PageTop" />/
        <bean:write name="mbhNtp060Form" property="ntp060MaxPage" />
      <input name="ntp060next" value="次頁" type="submit" />

      <br>

    </logic:greaterThan>

  </logic:notEmpty>
</div>


    <logic:notEmpty name="mbhNtp060Form" property="ntp060AnkenList">
    <bean:define id="tdColor" value="" />

    <logic:iterate id="ankenMdl" name="mbhNtp060Form" property="ntp060AnkenList" indexId="idx">

    <br>

    <bean:define id="mod" value="0" />
    <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
      <bean:define id="tblColor" value="anken_select_area" />
    </logic:equal>
    <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
      <bean:define id="tblColor" value="anken_select_area" />
    </logic:notEqual>


      <input name="<%= jp.groupsession.v3.mbh.ntp040.MbhNtp040Form.PARAM_ANKEN_SELECTADR %><bean:write name="ankenMdl" property="nanSid" />" value="<gsmsg:write key="cmn.select" />" type="submit">


      <input type="hidden" id="ankenCode_<bean:write name="ankenMdl" property="nanSid" />" value="<bean:write name="ankenMdl" property="nanCode" />">
      <span class="text_base2"><bean:write name="ankenMdl" property="nanCode" />&nbsp;</span>

      <input type="hidden" id="ankenName_<bean:write name="ankenMdl" property="nanSid" />" value="<bean:write name="ankenMdl" property="nanName" />">
      <bean:write name="ankenMdl" property="nanName" />&nbsp;



      <input type="hidden" id="ankenCompanySid_<bean:write name="ankenMdl" property="nanSid" />" value="<bean:write name="ankenMdl" property="acoSid" />">
      <input type="hidden" id="ankenCompanyCode_<bean:write name="ankenMdl" property="nanSid" />" value="<bean:write name="ankenMdl" property="ntp200CompanyCode" />">
      <input type="hidden" id="ankenCompanyName_<bean:write name="ankenMdl" property="nanSid" />" value="<bean:write name="ankenMdl" property="ntp200CompanyName" />">
      <span class="text_base2"><bean:write name="ankenMdl" property="ntp200CompanyName" />&nbsp;</span>

      <input type="hidden" id="ankenBaseSid_<bean:write name="ankenMdl" property="nanSid" />" value="<bean:write name="ankenMdl" property="abaSid" />">
      <input type="hidden" id="ankenBaseName_<bean:write name="ankenMdl" property="nanSid" />" value="<bean:write name="ankenMdl" property="ntp200BaseName" />">
      <span class="text_base2"><bean:write name="ankenMdl" property="ntp200BaseName" />&nbsp;</span></td>


    </logic:iterate>
    </logic:notEmpty>






<div align="center">
<input name="ntp060back" value="<gsmsg:write key="cmn.back" />" type="submit">
</div>


</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>