<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="cmn.reserve" /><gsmsg:write key="mobile.15" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "rngTop"; %>
<% thisForm = "mbhRsv020Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>
<% jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage(); %>
<div data-role="page" data-theme="<%= usrTheme %>">

<html:form method="post" action="/mobile/sp_rsv020">
<html:hidden property="mobileType" value="1"/>
<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<img src="../mobile/sp/imgages/rsv_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="cmn.reserve" /><br><gsmsg:write key="cmn.days2" /></h1>

  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<input type="hidden" name="CMD" value="">
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsv020FromHour" />
<html:hidden property="rsv020ToHour" />
<html:hidden property="rsv020ColSpan" />
<html:hidden property="rsvSelectedHourFr" />
<html:hidden property="rsvSelectedHourTo" />
<html:hidden property="rsvSelectedSisetuSid" />
<html:hidden property="rsvSelectedYoyakuSid" />
<html:hidden property="rsvSelectedDate" />
<html:hidden property="rsv100InitFlg" />
<html:hidden property="rsv100SearchFlg" />
<html:hidden property="rsv100SortKey" />
<html:hidden property="rsv100OrderKey" />
<html:hidden property="rsv100PageTop" />
<html:hidden property="rsv100PageBottom" />
<html:hidden property="rsv100selectedFromYear" />
<html:hidden property="rsv100selectedFromMonth" />
<html:hidden property="rsv100selectedFromDay" />
<html:hidden property="rsv100selectedToYear" />
<html:hidden property="rsv100selectedToMonth" />
<html:hidden property="rsv100selectedToDay" />
<html:hidden property="rsv020ClearTargetKey" />

<html:hidden property="rsv100SearchCondition" />
<html:hidden property="rsv100TargetMok" />
<html:hidden property="rsv100TargetNiyo" />
<logic:notEmpty name="mbhRsv020Form" property="rsv100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="mbhRsv020Form" property="rsv100CsvOutField" scope="request">
    <input type="hidden" name="rsv100CsvOutField" value="<bean:write name="csvOutField"/>">
  </logic:iterate>
</logic:notEmpty>
<html:hidden property="rsv100SelectedKey1" />
<html:hidden property="rsv100SelectedKey2" />
<html:hidden property="rsv100SelectedKey1Sort" />
<html:hidden property="rsv100SelectedKey2Sort" />
<html:hidden property="rsv100svFromYear" />
<html:hidden property="rsv100svFromMonth" />
<html:hidden property="rsv100svFromDay" />
<html:hidden property="rsv100svToYear" />
<html:hidden property="rsv100svToMonth" />
<html:hidden property="rsv100svToDay" />
<html:hidden property="rsv100svGrp1" />
<html:hidden property="rsv100svGrp2" />
<html:hidden property="rsv100svKeyWord" />
<html:hidden property="rsv100svSearchCondition" />
<html:hidden property="rsv100svTargetMok" />
<html:hidden property="rsv100svTargetNiyo" />
<html:hidden property="rsv100svSelectedKey1" />
<html:hidden property="rsv100svSelectedKey2" />
<html:hidden property="rsv100svSelectedKey1Sort" />
<html:hidden property="rsv100svSelectedKey2Sort" />
<html:hidden property="rsv100SearchSvFlg" />
<html:hidden property="rsvGrpFlg" />

<logic:notEmpty name="mbhRsv020Form" property="rsvSelectedIkkatuTorokuKey" scope="request">
  <logic:iterate id="key" name="mbhRsv020Form" property="rsvSelectedIkkatuTorokuKey" scope="request">
    <input type="hidden" name="rsvIkkatuTorokuKey" value="<bean:write name="key"/>">
  </logic:iterate>
</logic:notEmpty>

<bean:define id="fromHour" name="mbhRsv020Form" property="rsv020FromHour" scope="request"/>
<bean:define id="toHour" name="mbhRsv020Form" property="rsv020ToHour" scope="request"/>
<bean:define id="rsvDspFrom" name="mbhRsv020Form" property="rsvDspFrom" scope="request"/>
<bean:define id="colSpan" name="mbhRsv020Form" property="rsv020ColSpan" scope="request"/>
<bean:define id="hourDivCount" name="mbhRsv020Form" property="rsv020HourDivCount" scope="request"/>

<bean:define id="sigSid" name="mbhRsv020Form" property="rsvSelectedGrpSid" type="java.lang.Integer"/>



  <!-- グループ日間  -->
<logic:equal name="mbhRsv020Form" property="rsv020DspMode" value="0">
  <logic:notEmpty name="mbhRsv020Form" property="rsv020TimeChartList" scope="request">

  <div class="font_small" align="center">
    <html:select property="rsvSelectedGrpSid" styleClass="select01" onchange="changeCombo();">
      <html:optionsCollection name="mbhRsv020Form" property="rsvGrpLabelList" value="value" label="label" />
    </html:select>
  </div>

  <logic:messagesPresent message="false">
  <html:errors />
  </logic:messagesPresent>

  <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
    <li data-role="list-divider" style="background:#ffffff;"><div align="center"><bean:write name="mbhRsv020Form" property="rsvDispYm" /></li>
  </ul>
    <div data-role="controlgroup" data-type="horizontal" align="center" class="font_xxsmall">
      <a href="#" onclick="buttonPush('zenzitu_ido');" data-inline="true" data-role="button"><div class="font_small"><gsmsg:write key="cmn.previous.day" /></div></a>
      <a href="#" onclick="buttonPush('kyo');" data-inline="true" data-role="button"><div class="font_small"><gsmsg:write key="cmn.today" /></div></a>
      <a href="#" onclick="buttonPush('yokuzitu_ido');" data-inline="true" data-role="button"><div class="font_small"><gsmsg:write key="cmn.nextday" /></div></a>
    </div>

    <table width="100%" class="top_left_radius top_right_radius " align="center" style="text-align:center;border:1px solid #a5a1a1;border-bottom:0px!important;background:#ffffff;">
      <tr style="height:30px">
        <td width="70px"><div class=""><a href="#" onclick="timeShiftLeft('<bean:write name="mbhRsv020Form" property="rsv020FromHour" />');"><img src="../mobile/sp/imgages/img_arrow_left.gif"></div></a></td>

        <logic:iterate id="tmc" name="mbhRsv020Form" property="rsv020TimeChartList" scope="request" indexId="idx">
          <% if (idx < 8) { %>
             <td width="16px" class="<bean:write name="tmc" />">
          <% } else { %>
             <td width="16px" class="<bean:write name="tmc" />" style="display:none;">
          <% } %>
          <div class="font_xsmall"><bean:write name="tmc" /></div></td>
        </logic:iterate>

        <td width="70px" ><div class=""><a href="#" onclick="timeShiftRight('<bean:write name="mbhRsv020Form" property="rsv020ToHour" />');"><img src="../mobile/sp/imgages/img_arrow_right.gif"></div></a></td>
      </tr>
    </table>
  <table cellspacing="0" cellpadding="0" align="center" width="100%">
    <tr>
      <td width="20%">

        <div class="ui-grid-a">
          <div class="ui-block-e" style="padding:0px!important;width:100%!important;">
            <div class="ui-bar ui-bar-d grid_position_head">
              <div class="font_xsmall grid_text_position"><gsmsg:write key="cmn.institution" /></div>
            </div>
          </div>
        </div>

      </td>
      <td width="80%">

        <div class="ui-grid-a">
          <logic:iterate id="tmc" name="mbhRsv020Form" property="rsv020TimeChartList" scope="request" indexId="cnt">
            <% if (cnt < 8) { %>
               <div class="ui-block-e grid_block2 <bean:write name="tmc" />">
            <% } else { %>
               <div class="ui-block-e grid_block2 <bean:write name="tmc" />" style="display:none;">
            <% } %>
                 <div class="ui-bar ui-bar-d grid_position_head">
                   <div class="font_xsmall grid_text_position_head"><bean:write name="tmc" /></div>
                 </div>
               </div>
          </logic:iterate>
        </div>

      </td>
    </tr>

    <logic:notEmpty name="mbhRsv020Form" property="rsv020DaylyList" scope="request">
      <logic:iterate id="sisetu" name="mbhRsv020Form" property="rsv020DaylyList" scope="request" indexId="cnt">

      <% String sisName = null; %>
      <bean:define id="sisNm" name="sisetu" property="rsdName" type="java.lang.String"/>
      <% if (sisNm.length() > 8) { %>
      <% sisName = sisNm.substring(0, 8); %>
      <% } %>
      <% if (sisNm.length() > 4) { %>
      <% sisName = sisNm.substring(0, 4) + "<br>" + sisNm.substring(4); %>
      <% } else {%>
      <% sisName = sisNm; %>
      <% } %>

      <tr>
      <td>

        <div class="ui-grid-a">
          <div class="ui-block-e" style="padding:0px!important;width:100%!important;">
            <div class="ui-bar ui-bar-c grid_position_title">
              <div class="font_small grid_text_position_title" style="text-shadow: 1px 1px 0 #ddd;"><%= sisName %></div>
            </div>
          </div>
        </div>

      </td>
      <td>
      <logic:notEmpty name="sisetu" property="rsvWeekList">
        <logic:iterate id="week" name="sisetu" property="rsvWeekList">

        <logic:notEmpty name="mbhRsv020Form" property="rsv020TimeChartList" scope="request">
        <logic:iterate id="tmcHour" name="mbhRsv020Form" property="rsv020TimeChartList" scope="request" indexId="tmcCnt">

          <logic:notEmpty name="week" property="yoyakuDayList">
            <logic:iterate id="day" name="week" property="yoyakuDayList">

              <logic:notEmpty name="day" property="rsyMbTimeList">
                <logic:iterate id="timeString" name="day" property="rsyMbTimeList" indexId="timeStrIdx">

                  <% if (timeStrIdx.equals(tmcCnt)) { %>
                  <bean:define id="timeVal" name="timeString" type="java.lang.String" />

                  <% if (tmcCnt > 7) { %>
                      <div class="ui-block-e grid_block2  <bean:write name="tmcHour" />" style="display:none;">
                  <% } else { %>
                      <div class="ui-block-e grid_block2  <bean:write name="tmcHour" />" >
                  <% } %>

                  <logic:equal name="timeString" value="1">
                    <a href="#" onClick="popupRsvData('rsv_<bean:write name="sisetu" property="rsdSid" />_<bean:write name="tmcHour" />');">
                      <div class="ui-bar ui-bar-c grid_position">
                        <div class="font_small grid_text_position_naiyo"><img src="../mobile/sp/imgages/icon_check.gif"></div>
                      </div>
                    </a>
                  </logic:equal>
                  <logic:notEqual name="timeString" value="1">
                    <logic:equal name="sisetu" property="racAuth" value="1">
                      <a href="#" onclick="moveSisetuAdd2('<bean:write name="day" property="yrkDateStr" />', '<bean:write name="sisetu" property="rsdSid" />', '<bean:write name="tmcHour" />');">
                    </logic:equal>
                      <div class="ui-bar ui-bar-c grid_position">
                        <div class="font_small grid_text_position_naiyo">
                          <logic:equal name="sisetu" property="racAuth" value="1">
                            <img src="../mobile/sp/imgages/icon_add_data2.gif">
                          </logic:equal>
                        </div>
                      </div>
                    </a>
                  </logic:notEqual>

                  </div>
                  <% } %>

                </logic:iterate>
              </logic:notEmpty>

            </logic:iterate>
          </logic:notEmpty>

        </logic:iterate>
        </logic:notEmpty>

        </logic:iterate>
      </logic:notEmpty>
      </td>
      </tr>

      </logic:iterate>
    </logic:notEmpty>
    <tr>
    </tr>
    </table>
    <table width="100%" class="bottom_left_radius bottom_right_radius" align="center" style="text-align:center;border:1px solid #a5a1a1;background:#ffffff;">
      <tr style="height:30px">
        <td width="70px"><div class=""><a href="#" onclick="timeShiftLeft('<bean:write name="mbhRsv020Form" property="rsv020FromHour" />');"><img src="../mobile/sp/imgages/img_arrow_left.gif"></div></a></td>

        <logic:iterate id="tmc" name="mbhRsv020Form" property="rsv020TimeChartList" scope="request" indexId="idx">
          <% if (idx < 8) { %>
             <td width="16px" class="<bean:write name="tmc" />">
          <% } else { %>
             <td width="16px" class="<bean:write name="tmc" />" style="display:none;">
          <% } %>
          <div class="font_xsmall"><bean:write name="tmc" /></div></td>
        </logic:iterate>

        <td width="70px" ><div class=""><a href="#" onclick="timeShiftRight('<bean:write name="mbhRsv020Form" property="rsv020ToHour" />');"><img src="../mobile/sp/imgages/img_arrow_right.gif"></div></a></td>
      </tr>
    </table>

<div id="startNum" style="display:none"><bean:write name="mbhRsv020Form" property="rsv020FromHour" /></div>

  </logic:notEmpty>
</logic:equal>

<img SRC="../common/images/spacer.gif" width="1px" height="10px" border="0" style="visibility:hidden">

<div data-role="controlgroup" data-type="horizontal" align="center" class="font_xxsmall">
  <a href="#" onclick="buttonPush('zenzitu_ido');" data-inline="true" data-role="button"><div class="font_small"><gsmsg:write key="cmn.previous.day" /></div></a>
  <a href="#" onclick="buttonPush('kyo');" data-inline="true" data-role="button"><div class="font_small"><gsmsg:write key="cmn.today" /></div></a>
  <a href="#" onclick="buttonPush('yokuzitu_ido');" data-inline="true" data-role="button"><div class="font_small"><gsmsg:write key="cmn.nextday" /></div></a>
</div>

<br>

<ul data-role="listview" data-theme="d" data-dividertheme="c">
  <li><a href="../mobile/sp_rsv020.do?mobileType=1"><gsmsg:write key="cmn.days" /></a></li>
  <li><a href="../mobile/sp_rsv010.do?mobileType=1"><gsmsg:write key="cmn.weekly" /></a></li>
</ul>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>


<logic:notEmpty name="mbhRsv020Form" property="rsv020DaylyList" scope="request">

  <logic:iterate id="sisetu" name="mbhRsv020Form" property="rsv020DaylyList" scope="request" indexId="cnt">

  <logic:notEmpty name="mbhRsv020Form" property="rsv020TimeChartList" scope="request">
  <logic:iterate id="tmcHour" name="mbhRsv020Form" property="rsv020TimeChartList" scope="request">

  <logic:notEmpty name="sisetu" property="rsvWeekList">
    <logic:iterate id="week" name="sisetu" property="rsvWeekList">

      <logic:notEmpty name="week" property="yoyakuDayList">
        <logic:iterate id="day" name="week" property="yoyakuDayList">
           <logic:notEmpty name="day" property="yoyakuList">
            <logic:iterate id="yrk" name="day" property="yoyakuList" indexId="idx">
                <div id="rsv_<bean:write name="sisetu" property="rsdSid" />_<bean:write name="tmcHour" />" style="position:absolute;bottom:0;display:none;z-index:10;display:none;width:100%;height:100%;background:rgba(255, 255, 255, 0.7);">
                  <div class="rsv_popupmenu">
                    <div style="text-align:right;">
                      <a href="#" onclick="popupRsvData('rsv_<bean:write name="sisetu" property="rsdSid" />_<bean:write name="tmcHour" />');" data-role="button" data-icon="delete" data-iconpos="notext">Close</a>
                    </div>

                    <div align="center">
                      <ul style="width:80%;" data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">

                        <bean:define id="yrDate" name="day" property="yrkDateStr" type="java.lang.String"/>
                        <% if (yrDate.length() >= 8) { %>
                        <%    yrDate = yrDate.substring(0, 4) + gsMsg.getMessage(request, "cmn.year") + yrDate.substring(4, 6) + gsMsg.getMessage(request, "cmn.month") + yrDate.substring(6) + gsMsg.getMessage(request, "cmn.day");%>
                        <% } %>

                        <bean:define id="tmcFr" name="tmcHour" type="java.lang.String"/>
                        <% String tmcString = tmcFr + gsMsg.getMessage(request, "tcd.running.time") + "～" + (Integer.valueOf(tmcFr) + 1) + gsMsg.getMessage(request, "tcd.running.time"); %>

                        <li data-role="list-divider" style="background:#ffffff;">
                          <div class="font_small" align="center"><b><bean:write name="sisetu" property="rsdName" /></b><hr><%= yrDate %>&nbsp;&nbsp;&nbsp;&nbsp;<%= tmcString %></div>
                        </li>

                          <logic:iterate id="yrkTime" name="day" property="yoyakuList" indexId="idx2">
                            <bean:define id="tmcNm" name="tmcHour" type="java.lang.String" />
                            <bean:define id="frHnm" name="yrkTime" property="rsyFrHour" type="java.lang.Integer" />
                            <bean:define id="toHnm" name="yrkTime" property="rsyToHour" type="java.lang.Integer" />
                            <bean:define id="toMnm" name="yrkTime" property="rsyToMinute" type="java.lang.Integer" />

                            <% boolean dspFlg = false; %>
                            <% if (Integer.valueOf(tmcNm) >= Integer.valueOf(frHnm) && Integer.valueOf(tmcNm) < Integer.valueOf(toHnm)) { %>
                            <%    dspFlg = true; %>
                            <% } else if (Integer.valueOf(tmcNm) == Integer.valueOf(toHnm) && Integer.valueOf(toMnm) > 0) { %>
                            <%    dspFlg = true; %>
                            <% } %>


                            <% if (dspFlg) { %>


                            <bean:define id="s_rsdSid" name="sisetu" property="rsdSid" type="java.lang.Integer" />
                            <bean:define id="s_rsySid" name="yrkTime" property="rsySid" type="java.lang.Integer" />
                            <bean:define id="s_date" name="day" property="yrkDateStr"  type="java.lang.String" />

                            <logic:notEmpty name="yrkTime" property="rsyNaiyo">
                            <bean:define id="scnaiyou" name="yrkTime" property="rsyNaiyo" />
                            <%
                              String tmpText = (String)pageContext.getAttribute("scnaiyou",PageContext.PAGE_SCOPE);
                              String tmpText2 = jp.co.sjts.util.StringUtilHtml.transToHTml(tmpText);
                            %>

                            <li>

                            <a href="#" onclick="return moveSisetuEdit('<bean:write name="yrkTime" property="rsySid" />');" title="<gsmsg:write key="cmn.content" />:<%= tmpText2 %>">
                            </logic:notEmpty>
                            <logic:empty name="yrkTime" property="rsyNaiyo">

                            <li>

                            <a href="#" title="<bean:write name="yrkTime" property="rsyMok" />" onclick="return moveSisetuEdit('<bean:write name="yrkTime" property="rsySid" />');">
                            </logic:empty>

                              <logic:notEmpty name="yrkTime" property="yrkRiyoDateStr">
                                <div class="font_xsmall">
                                  <span style="color:#ff0000"><bean:write name="yrkTime" property="yrkRiyoDateStr" /></span>
                                </div>
                              </logic:notEmpty>
                              <span class="font_small" style="color:blue;">
                                <logic:notEmpty name="yrkTime" property="rsyMok"><bean:write name="yrk" property="rsyMok" /></logic:notEmpty>
                                <logic:notEmpty name="yrkTime" property="rsyMok">
                                  <logic:notEmpty name="yrkTime" property="yrkName">&nbsp;/&nbsp;</logic:notEmpty>
                                </logic:notEmpty>
                                <logic:notEmpty name="yrkTime" property="yrkName"><bean:write name="yrkTime" property="yrkName" /></logic:notEmpty>
                              </span>
                            </a>

                          </li>
                          <% } %>


                        </logic:iterate>

                      </ul>
                      <logic:equal name="sisetu" property="racAuth" value="1">
                        <a href="#" onclick="moveSisetuAdd2('<bean:write name="day" property="yrkDateStr" />', '<bean:write name="sisetu" property="rsdSid" />', '<bean:write name="tmcHour" />');" data-inline="true" data-role="button" data-icon="plus"><div class="font_small"><gsmsg:write key="cmn.new" /><gsmsg:write key="cmn.entry" /></div></a>
                      </logic:equal>
                    </div>

                  </div>
                </div>

            </logic:iterate>
          </logic:notEmpty>

        </logic:iterate>
      </logic:notEmpty>

    </logic:iterate>
  </logic:notEmpty>

  </logic:iterate>
  </logic:notEmpty>

  </logic:iterate>
</logic:notEmpty>

</html:form>
</div><!-- /page -->

</body>
</html:html>