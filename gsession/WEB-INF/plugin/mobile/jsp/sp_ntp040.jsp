<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-jqText.tld" prefix="jquery" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>

<html:html>

<head>
<logic:equal name="mbhNtp040Form" property="cmd" value="add">
<title>[GroupSession] <gsmsg:write key="ntp.1" /> <gsmsg:write key="cmn.entry" /></title>
</logic:equal>
<logic:equal name="mbhNtp040Form" property="cmd" value="edit">
<title>[GroupSession] <gsmsg:write key="ntp.1" /> <gsmsg:write key="cmn.check" /></title>
</logic:equal>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<link rel="stylesheet" href="../mobile/sp/css/jquery.ui.datepicker.css?<%= GSConst.VERSION_PARAM %>" />
<link rel="stylesheet" href="../mobile/sp/css/jquery.ui.datepicker.mobile.css?<%= GSConst.VERSION_PARAM %>" />
<script src="../mobile/sp/js/jQuery.ui.datepicker.js?<%= GSConst.VERSION_PARAM %>" type="text/javascript"></script>
<script src="../mobile/sp/js/jquery.ui.datepicker.mobile.js?<%= GSConst.VERSION_PARAM %>" type="text/javascript"></script>

<logic:equal name="mbhNtp040Form" property="mobileLang" value="0">
<script src="../mobile/sp/js/jquery.ui.datepicker-ja.js?<%= GSConst.VERSION_PARAM %>" type="text/javascript"></script>
</logic:equal>
<script language="JavaScript" src="../mobile/sp/js/sp_file_upload.js?453<%= GSConst.VERSION_PARAM %>"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<% String selectionEvent = ""; %>
<% boolean selectionFlg = false; %>
<% String valueFocusEvent = ""; %>
<% String bikoFocusEvent = ""; %>

<logic:equal name="mbhNtp040Form" property="searchPluginKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.PLUGIN_USE) %>">
  <% selectionFlg = true; %>
</logic:equal>


<logic:equal name="mbhNtp040Form" property="addressPluginKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.PLUGIN_USE) %>">


</logic:equal>






<script type="text/javascript">
$(document).ready(function(){

  $("#date").datepicker();

});
</script>
<script type="text/javascript">
$(document).ready(function(){
  $("#date2").datepicker();
});
</script>
<% pluginName = "ntp"; %>
<% thisForm = "mbhNtp040Form"; %>


</head>

<logic:notEmpty name="mbhNtp040Form" property="ntp040SelectUsrLavel" scope="request">
<logic:equal name="mbhNtp040Form" property="cmd" value="edit">
<body class="body_03" onload="">
</logic:equal>
<logic:equal name="mbhNtp040Form" property="cmd" value="add">
<body class="body_03" onload="">
</logic:equal>
</logic:notEmpty>
<logic:empty name="mbhNtp040Form" property="ntp040SelectUsrLavel" scope="request">
<body class="body_03" onload="">
</logic:empty>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_ntp040">
<input type="hidden" name="mobileType" value="1">
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
<html:hidden property="ntp040DspTitle" />
<html:hidden property="ntp040InitYear" />
<html:hidden property="ntp040InitMonth" />
<html:hidden property="ntp040InitDay" />

<html:hidden property="ntp040schUrl" />

<html:hidden property="ntp040ActDateKbn" />

<html:hidden property="ntp040NxtActYear" />
<html:hidden property="ntp040NxtActMonth" />
<html:hidden property="ntp040NxtActDay" />
<html:hidden property="ntp040FrYear" />
<html:hidden property="ntp040FrMonth" />
<html:hidden property="ntp040FrDay" />
<html:hidden property="ntp040HoukokuYear" />
<html:hidden property="ntp040HoukokuMonth" />
<html:hidden property="ntp040HoukokuDay" />

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
<bean:define id="sptm" name="mbhNtp040Form" property="spTheme"/>

<logic:notEmpty name="mbhNtp040Form"  property="ntp040FileList">
<logic:iterate id="tempmdl" name="mbhNtp040Form"  property="ntp040FileList">
<html:hidden name="tempmdl" property="binFileName" />
</logic:iterate>
</logic:notEmpty>

<input type="hidden" name="ntp040delCompanyId" value="">
<input type="hidden" name="ntp040delCompanyBaseId" value="">

<html:hidden property="addressPluginKbn" />
<html:hidden property="searchPluginKbn" />


<%--
<logic:equal name="mbhNtp040Form" property="cmd" value="edit">
  <div class="fix_content" style="width:150px;">
    <table class="tl0" width="30px">

      <tr>
        <td class="table_bg_A5B4E1 text_bb1" style="font-size:12px;" align="center" nowrap>
         <bean:write name="mbhNtp040Form" property="ntp040FrYear" />年
         <bean:write name="mbhNtp040Form" property="ntp040FrMonth" />月
         <bean:write name="mbhNtp040Form" property="ntp040FrDay" />日
        </td>
      </tr>

      <tr>
        <td align="center" width="20%" style="font-size:14px;" class="td_wt" nowrap><span class="text_base"><bean:write name="mbhNtp040Form" property="ntp040UsrName" /></span></td>
      </tr>

      <logic:notEmpty name="mbhNtp040Form" property="ntp040DspTargetMdl">
        <tr class="targetSelBtnArea">
          <td align="center" width="20%" style="font-size:14px;" class="td_wt cursor_pointer target_link_area" onclick="return scrollTarger();" nowrap>
            <span class="text_base"><b><gsmsg:write key="ntp.12" /></b></span>
          </td>
        </tr>
        <tr class="targetSelBtnArea" style="display:none;">
          <td align="center" width="20%" style="font-size:14px;" class="td_wt cursor_pointer target_link_area" onclick="return scrollInit();" nowrap>
            <span class="text_base"><b>▼</b></span>
          </td>
        </tr>
      </logic:notEmpty>

      <tr>
        <td align="center">
        <logic:equal name="mbhNtp040Form" property="cmd" value="edit">
        <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="backButtonPush('040_back', '<bean:write name="mbhNtp040Form" property="cmd" />');">
        </logic:equal>
        </td>
      </tr>

    </table>
  </div>
</logic:equal>
--%>

<input type="hidden" name="helpPrm" value="<bean:write name="mbhNtp040Form" property="ntp010SelectUsrKbn" /><bean:write name="mbhNtp040Form" property="cmd" />">

<!--　BODY -->
<bean:write name="mbhNtp040Form" property="ntp040YearLavelStr" filter="false" />
<bean:write name="mbhNtp040Form" property="ntp040MonthLavelStr" filter="false" />
<bean:write name="mbhNtp040Form" property="ntp040DayLavelStr" filter="false" />
<bean:write name="mbhNtp040Form" property="ntp040HourLavelStr" filter="false" />
<bean:write name="mbhNtp040Form" property="ntp040MinuteLavelStr" filter="false" />
<bean:write name="mbhNtp040Form" property="ntp040KtbunruiLavelStr" filter="false" />
<bean:write name="mbhNtp040Form" property="ntp040KthouhouLavelStr" filter="false" />


<input type="hidden" id="frhourhide" value="<bean:write name="mbhNtp040Form" property="ntp040FrHour" />">
<input type="hidden" id="frminhide" value="<bean:write name="mbhNtp040Form" property="ntp040FrMin" />">
<input type="hidden" id="tohourhide" value="<bean:write name="mbhNtp040Form" property="ntp040ToHour" />">
<input type="hidden" id="tominhide" value="<bean:write name="mbhNtp040Form" property="ntp040ToMin" />">


<logic:equal name="mbhNtp040Form" property="ntp040KtBriHhuUse" value="0">
<input type="hidden" id="ktbunruihide" value="<bean:write name="mbhNtp040Form" property="ntp040Ktbunrui" />">
<input type="hidden" id="kthouhouhide" value="<bean:write name="mbhNtp040Form" property="ntp040Kthouhou" />">
</logic:equal>

<logic:notEqual name="mbhNtp040Form" property="ntp040KtBriHhuUse" value="0">
<input type="hidden" name="ntp040Ktbunrui" value="-1">
<input type="hidden" name="ntp040Kthouhou" value="-1">
</logic:notEqual>

<logic:notEqual name="mbhNtp040Form" property="ntp040MikomidoUse" value="0">
<input type="hidden" name="ntp040Mikomido" value="0">
</logic:notEqual>

<bean:define id="frhourval" name="mbhNtp040Form" property="ntp040FrHour" type="java.lang.String"/>
<bean:define id="frminval" name="mbhNtp040Form" property="ntp040FrMin" type="java.lang.String"/>
<bean:define id="tohourval" name="mbhNtp040Form" property="ntp040ToHour" type="java.lang.String"/>
<bean:define id="tominval" name="mbhNtp040Form" property="ntp040ToMin" type="java.lang.String"/>


<logic:equal name="mbhNtp040Form" property="ntp010SelectUsrKbn" value="1">
<html:hidden property="ntp040GroupSid" />
</logic:equal>





<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
  <img src="../mobile/sp/imgages/ntp_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="ntp.1" /><br>
    <logic:equal name="mbhNtp040Form" property="cmd" value="add">
    <gsmsg:write key="cmn.entry" />
    </logic:equal>
    <logic:equal name="mbhNtp040Form" property="cmd" value="kakunin">
    <gsmsg:write key="cmn.check" />
    </logic:equal>
    <logic:equal name="mbhNtp040Form" property="cmd" value="edit">
    <gsmsg:write key="cmn.edit" />
    </logic:equal>
  </h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">


<logic:messagesPresent message="false">
<span style="color: red"><html:errors/></span>
</logic:messagesPresent>





    <logic:equal name="mbhNtp040Form" property="cmd" value="add">

      <div class="font_small">■<span class="text_bb1"><gsmsg:write key="schedule.4" /></span></div><br>

      <logic:notEqual name="mbhNtp040Form" property="ntp010SelectUsrKbn" value="0">
      <span id="lt"><img src="../common/images/groupicon.gif" alt="<gsmsg:write key="cmn.group" />" border="0"></span>
      </logic:notEqual>
      <span class="text_base"><bean:write name="mbhNtp040Form" property="ntp040UsrName" /></span>

      <br>
      <br>
      <div class="font_small">■<span class="text_bb1"><gsmsg:write key="ntp.35" /></span><span class="text_r2">※</span></div>

      <br>
<%--
        <html:select property="ntp040FrYear" styleId="selYear">
          <html:optionsCollection name="mbhNtp040Form" property="ntp040YearLavel" value="value" label="label" />
        </html:select>

        <html:select property="ntp040FrMonth" styleId="selMonth">
          <html:optionsCollection name="mbhNtp040Form" property="ntp040MonthLavel" value="value" label="label" />
        </html:select>

        <html:select property="ntp040FrDay" styleId="selDay">
          <html:optionsCollection name="mbhNtp040Form" property="ntp040DayLavel" value="value" label="label" />
        </html:select>
--%>
        <jquery:jqtext id="date" name="mbhNtp040Form" property="ntp040HoukokuDate" readonly="true"/>

   </logic:equal>




  <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

  <logic:notEmpty name="mbhNtp040Form" property="ntp040DspTargetMdl">
  <bean:define id="trgDspMdl" name="mbhNtp040Form" property="ntp040DspTargetMdl" />

  <logic:notEmpty name="trgDspMdl" property="ntgList">

    <input type="hidden" id="hideYear" value="<bean:write name="trgDspMdl" property="year" />" />
    <input type="hidden" id="hideMonth" value="<bean:write name="trgDspMdl" property="month" />" />
    <input type="hidden" id="hideUsrSid" value="<bean:write name="trgDspMdl" property="usrSid" />" />
    <input type="hidden" id="hideNttSid" value="<bean:write name="trgDspMdl" property="nttSid" />" />

    <span id="pos-1"></span>
    <div id="trgSetArea">
      <div id="trgDataSetArea">


        <logic:equal name="mbhNtp040Form" property="cmd" value="add">



              <bean:define id="trgList" name="trgDspMdl" property="ntgList" />
              <bean:size id="listSize" name="trgList" />

                <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">

                <li data-role="list-divider" style="background:#ffffff;">
                  <div align="center">
                      <div class="font_small"><span class="text_bb1"><gsmsg:write key="ntp.12" /></span></div>
                  </div>
                </li>

                <logic:iterate id="ntgMdl" name="trgDspMdl" property="ntgList" indexId="trgIdx">
                    <li>
                    <input type="hidden" name="ntp040TargetSids" value="<bean:write name="ntgMdl" property="ntgSid" />_<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="trgDspMdl" property="usrSid" />_<bean:write name="ntgMdl" property="npgTarget" />"/>

                    <span class="text_bb1"><bean:write name="ntgMdl" property="npgTargetName" /></span>

                      <span class="text_base">
                        <div align="center">
                          <input name="ntp040targetStr_<bean:write name="ntgMdl" property="ntgSid" />" style="text-align:right;" maxlength="15" size="10" value="<bean:write name="ntgMdl" property="npgRecord" />" id="val_<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="trgDspMdl" property="usrSid" />_<bean:write name="ntgMdl" property="ntgSid" />" class="text_base" type="text">/<span id="valTrg_<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="trgDspMdl" property="usrSid" />_<bean:write name="ntgMdl" property="ntgSid" />"><bean:write name="ntgMdl" property="npgTarget" /><bean:write name="ntgMdl" property="npgTargetUnit" /></span>
                        </div>
                      </span>
                    </li>

                </logic:iterate>
                </ul>

        </logic:equal>

        <logic:equal name="mbhNtp040Form" property="cmd" value="kakunin">


              <bean:define id="trgList" name="trgDspMdl" property="ntgList" />
              <bean:size id="listSize" name="trgList" />

              <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">

                <li data-role="list-divider" style="background:#ffffff;">
                  <div align="center" class="font_small"><span class="text_bb1"><gsmsg:write key="ntp.12" /></span></div>
                </li>

                <logic:iterate id="ntgMdl" name="trgDspMdl" property="ntgList" indexId="trgIdx">

                    <li>

                    <% String recordColor = ""; %>

                    <logic:equal name="ntgMdl" property="npgTargetKbn" value="1">
                    <% recordColor = "record_comp"; %>
                    </logic:equal>

                    <span class="text_bb1">■<bean:write name="ntgMdl" property="npgTargetName" /><br></span>



                            <span class="text_base">
                              <span class="recordArea_<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="trgDspMdl" property="usrSid" />_<bean:write name="ntgMdl" property="ntgSid" />">
                                <span class="<%= recordColor %>" id="recordAreaText_<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="trgDspMdl" property="usrSid" />_<bean:write name="ntgMdl" property="ntgSid" />">
                                  <bean:write name="ntgMdl" property="npgRecord" />
                                </span>
                              </span>

                              /<span id="valTrg_<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="trgDspMdl" property="usrSid" />_<bean:write name="ntgMdl" property="ntgSid" />"><bean:write name="ntgMdl" property="npgTarget" /></span>
                            </span>
                            <bean:write name="ntgMdl" property="npgTargetUnit" />&nbsp;&nbsp;

                            <logic:equal name="mbhNtp040Form" property="ntp040TargetAdmKbn" value="0">
                            <%--
                              <div class="trgBtnArea_<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="trgDspMdl" property="usrSid" />_<bean:write name="ntgMdl" property="ntgSid" />" style="display:block;">
                                <input class="target_settei_btn changeTrgBtn" id="<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="trgDspMdl" property="usrSid" />_<bean:write name="ntgMdl" property="ntgSid" />" value="変更" type="button">
                              </div>
                            --%>

                            </logic:equal>

                    </li>
                </logic:iterate>
                </ul>

        </logic:equal>


        <logic:equal name="mbhNtp040Form" property="cmd" value="edit">


              <bean:define id="trgList" name="trgDspMdl" property="ntgList" />
              <bean:size id="listSize" name="trgList" />

              <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">

                <li data-role="list-divider" style="background:#ffffff;">
                  <div class="font_small" align="center"><span class="text_bb1"><gsmsg:write key="ntp.12" /></span></div>
                </li>

                <logic:iterate id="ntgMdl" name="trgDspMdl" property="ntgList" indexId="trgIdx">

                    <li>

                    <input type="hidden" name="ntp040TargetSids" value="<bean:write name="ntgMdl" property="ntgSid" />_<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="trgDspMdl" property="usrSid" />_<bean:write name="ntgMdl" property="npgTarget" />"/>

                    <% String recordColor = ""; %>

                    <logic:equal name="ntgMdl" property="npgTargetKbn" value="1">
                    <% recordColor = "record_comp"; %>
                    </logic:equal>

                    <span class="text_bb1"><bean:write name="ntgMdl" property="npgTargetName" /></span>



                            <span class="text_base">
                          <div class="font_small" align="center">
                              <span class="recordArea_<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="trgDspMdl" property="usrSid" />_<bean:write name="ntgMdl" property="ntgSid" />">
                                <input name="ntp040targetStr_<bean:write name="ntgMdl" property="ntgSid" />" style="text-align:right;" maxlength="15" size="15" value="<bean:write name="ntgMdl" property="npgRecord" />" id="trgRecord_<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="trgDspMdl" property="usrSid" />_<bean:write name="ntgMdl" property="ntgSid" />" class="text_base" type="text">
                              </span>
                              /<span id="valTrg_<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="trgDspMdl" property="usrSid" />_<bean:write name="ntgMdl" property="ntgSid" />"><bean:write name="ntgMdl" property="npgTarget" /></span>
                            </span>
                            <bean:write name="ntgMdl" property="npgTargetUnit" />&nbsp;&nbsp;

                            <%--
                            <logic:equal name="mbhNtp040Form" property="ntp040TargetAdmKbn" value="0">

                              <div class="trgBtnArea_<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="trgDspMdl" property="usrSid" />_<bean:write name="ntgMdl" property="ntgSid" />">

                                      <input class="target_kakutei_btn" id="<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="trgDspMdl" property="usrSid" />_<bean:write name="ntgMdl" property="ntgSid" />" value="確定" type="button">

                                      <input class="target_cansel_btn" id="<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="trgDspMdl" property="usrSid" />_<bean:write name="ntgMdl" property="ntgSid" />" value="キャンセル" type="button">

                              </div>
                            </logic:equal>
                            --%>

                            </div>

                     </li>

                </logic:iterate>


        </logic:equal>


      </div>
    </div>

  </logic:notEmpty>
  </logic:notEmpty>




    <logic:equal name="mbhNtp040Form" property="cmd" value="add">
      <%@ include file="/WEB-INF/plugin/mobile/jsp/sp_ntp040_add.jsp" %>
    </logic:equal>

    <logic:notEqual name="mbhNtp040Form" property="cmd" value="add">
      <%@ include file="/WEB-INF/plugin/mobile/jsp/sp_ntp040_edit.jsp" %>
    </logic:notEqual>




    <img src="../schedule/images/spacer.gif" width="1px" border="0" height="10px">


    <img src="../schedule/images/spacer.gif" width="1px" border="0" height="10px">



      <logic:equal name="mbhNtp040Form" property="cmd" value="add">
        <div data-role="controlgroup" data-type="horizontal" align="center">
          <input name="ntp040add" value="<gsmsg:write key="cmn.entry.2" />" type="submit" data-icon="plus" data-inline="true">
          <input name="ntp040back" value="<gsmsg:write key="cmn.back" />" type="submit" data-icon="back" data-inline="true" data-iconpos="right">
        </div>
      </logic:equal>

      <logic:equal name="mbhNtp040Form" property="cmd" value="kakunin">


       <div data-role="controlgroup" data-type="horizontal" align="center">

        <logic:equal name="mbhNtp040Form" property="authAddEditKbn" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.AUTH_ADD_EDIT) %>">



        <input class="" name="ntp040edit" value="<gsmsg:write key="cmn.edit" />" type="submit"  data-inline="true" data-icon="arrow-l">
        <input type="submit" class="" name="ntp040del" value="<gsmsg:write key="cmn.delete" />" type="button"  data-inline="true" data-icon="delete">

        </logic:equal>

        <input name="ntp040back" value="<gsmsg:write key="cmn.back" />" type="submit" data-icon="back" data-inline="true" data-iconpos="right">


        </div>


      </logic:equal>

      <logic:equal name="mbhNtp040Form" property="cmd" value="edit">
        <div data-role="controlgroup" data-type="horizontal" align="center">
        <input name="ntp040add" value="<gsmsg:write key="cmn.entry.2" />" type="submit" data-icon="back" data-inline="true">
        <input name="ntp040back" value="<gsmsg:write key="cmn.back" />" type="submit" data-icon="back" data-inline="true">
        </div>
      </logic:equal>

        <br>


<ul data-role="listview" data-theme="d" data-dividertheme="c">
  <li><a href="../mobile/sp_ntp030.do?mobileType=1"><gsmsg:write key="mobile.18" /></a></li>
  <li><a href="../mobile/sp_ntp010.do?mobileType=1"><gsmsg:write key="schedule.19" /></a></li>
  <li><a href="../mobile/sp_ntp020.do?mobileType=1"><gsmsg:write key="mobile.19" /></a></li>
</ul>

</div><!-- /content -->


<!-- 添付ファイル -->
<input type="hidden" id="tmp_file_obj_name" value="ntp040file" />
<input type="file" id="file_use_check" style="visibility:hidden;"/>
<div id="tmp_pop" style="display:none;">

  <div class="tmppopupmenu">

    <div style="text-align:right;">
      <a href="#" onclick="tmpPopupMenu();" data-role="button" data-icon="delete" data-iconpos="notext">Close</a>
    </div>

    <div id="error_msg_area" align="center"></div>

    <div align="center">
      <div id="fileUpArea" class="fieldcontain">
        <input type="file" id="ntp040file" name="ntp040file" data-clear-btn="true"/>
      </div>
    </div>

    <div style="padding-top:20px;"></div>

    <div id="progress_bar_wrapper" align="center">
      <div id="progress_bar"></div>
    </div>

    <div id="progress_text_wrapper">
      <span id="progress_text"></span>
    </div>

    <div align="center">
      <a href="#" id="uploadBtn" onClick="performAjaxSubmit();" data-role="button" data-icon="plus" data-inline="true"/>添付する</a>
    </div>

  </div>

</div>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>
</div><!-- /page -->

</body>
</html:html>