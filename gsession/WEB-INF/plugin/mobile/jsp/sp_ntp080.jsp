<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>

<html:html>
<head>
<title>[Group Session] <gsmsg:write key="ntp.1" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "ntp"; %>
<% thisForm = "mbhNtp080Form"; %>; %>

</head>

<body class="body_03" onunload="calWindowClose();">

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_ntp080">
<input type="hidden" name="companyId"/>
<input type="hidden" name="CMD" value="">
<input type="hidden" name="mobileType" value="1">
<html:hidden property="ntp080pageNum" />
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


<logic:notEmpty name="mbhNtp080Form"  property="ntp040FileList">
<logic:iterate id="tempmdl" name="mbhNtp080Form"  property="ntp040FileList">
<html:hidden name="tempmdl" property="binFileName" />
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

<logic:notEmpty name="mbhNtp080Form"  property="ntp040targetPrmList">
<logic:iterate id="targetPrmStr" name="mbhNtp080Form"  property="ntp040targetPrmList">
<input type="hidden" name="ntp040targetPrmList" value="<bean:write name="targetPrmStr"/>">
</logic:iterate>
</logic:notEmpty>

<div data-role="header" data-nobackbtn="true" align="center" data-theme="<%= usrTheme %>">
  <a href="#" onClick="buttonPush('sch050back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
    <h1><gsmsg:write key="ntp.1" /><br><gsmsg:write key="ntp.17" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">






  <logic:notEmpty name="mbhNtp080Form" property="ntp080AdrList">



      <logic:greaterThan name="mbhNtp080Form" property="ntp080maxPageNum" value="1">
        <div align="center" class="font_xsmall">
          <div data-role="controlgroup" data-type="horizontal" align="center">
            <input name="ntp080prev" value="前頁" type="submit" data-inline="true" data-role="button" data-icon="arrow-l"/>
            <input name="ntp080next" value="次頁" type="submit" data-inline="true" data-role="button" data-icon="arrow-r"  data-iconpos="right" />
          </div>
        </div>
      </logic:greaterThan>





    <logic:notEmpty name="mbhNtp080Form" property="ntp080AdrList">
    <bean:define id="tdColor" value="" />

    <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">

    <logic:greaterThan name="mbhNtp080Form" property="ntp080maxPageNum" value="1">
      <li data-role="list-divider" style="background:#ffffff;">
      <div align="center">
          <bean:write name="mbhNtp080Form" property="ntp080pageNum" />/
          <bean:write name="mbhNtp080Form" property="ntp080maxPageNum" />
      </div>
      </li>
    </logic:greaterThan>

    <logic:iterate id="compMdl" name="mbhNtp080Form" property="ntp080AdrList" indexId="idx">

      <li>

      <logic:empty name="compMdl" property="companyBaseName">
        <a href="#" onclick="clickComp('<%= jp.groupsession.v3.mbh.ntp040.MbhNtp040Form.PARAM_SELECTCMP_AD %><bean:write name="compMdl" property="companySid" />')">
          <bean:write name="compMdl" property="companyName" />
        </a>
      </logic:empty>

      <logic:notEmpty name="compMdl" property="companyBaseName">
        <a href="#" onclick="clickComp('<%= jp.groupsession.v3.mbh.ntp040.MbhNtp040Form.PARAM_SELECTCMPBA_AD %><bean:write name="compMdl" property="companySid" />_<bean:write name="compMdl" property="companyBaseSid" />')">
          <bean:write name="compMdl" property="companyName" />&nbsp
          <bean:write name="compMdl" property="companyBaseName" />
        </a>
      </logic:notEmpty>

      </li>

    </logic:iterate>

    </ul>

    </logic:notEmpty>

  </logic:notEmpty>

<div align="center">
<input name="ntp080back" value="<gsmsg:write key="cmn.back" />" type="submit" type="submit" data-inline="true" data-role="button" data-icon="back">
</div>

</div><!-- /content -->


</html:form>

</div><!-- /page -->

</body>
</html:html>