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
<% pluginName = "rsvTop"; %>
<% thisForm = "mbhRsv010Form"; %>
</head>

<body>
<% jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage(); %>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form method="post" action="/mobile/sp_rsv010">
<html:hidden property="mobileType" value="1"/>
<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<img src="../mobile/sp/imgages/rsv_menu_icon_single.gif" class="tl img_border"/>
  <h1>
      <gsmsg:write key="cmn.reserve" /><br>
      <gsmsg:write key="cmn.weeks" />
  </h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">
<input type="hidden" name="CMD" value="">
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsvSelectedSisetuSid" />
<html:hidden property="rsvSelectedYoyakuSid" />
<html:hidden property="rsvSelectedDate" />
<html:hidden property="rsv100InitFlg" />
<html:hidden property="rsv100SearchFlg" />
<html:hidden property="rsv100SortKey" />
<html:hidden property="rsv100OrderKey" />
<html:hidden property="rsv100selectedFromYear" />
<html:hidden property="rsv100selectedFromMonth" />
<html:hidden property="rsv100selectedFromDay" />
<html:hidden property="rsv100selectedToYear" />
<html:hidden property="rsv100selectedToMonth" />
<html:hidden property="rsv100selectedToDay" />
<html:hidden property="rsv010ClearTargetKey" />
<html:hidden property="rsvGrpFlg" />

<html:hidden property="rsv100SearchCondition" />
<html:hidden property="rsv100TargetMok" />
<html:hidden property="rsv100TargetNiyo" />
<logic:notEmpty name="mbhRsv010Form" property="rsv100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="mbhRsv010Form" property="rsv100CsvOutField" scope="request">
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

  <logic:messagesPresent message="false">
  <html:errors />
  </logic:messagesPresent>

  <logic:empty name="mbhRsv010Form" property="rsv010SisetuList" scope="request">
    <bean:define id="daylyMoveFlg" value="0" />
  </logic:empty>
  <logic:notEmpty name="mbhRsv010Form" property="rsv010SisetuList" scope="request">
    <bean:define id="daylyMoveFlg" value="1" />
  </logic:notEmpty>

  <bean:define id="sigSid" name="mbhRsv010Form" property="rsvSelectedGrpSid" type="java.lang.Integer"/>

  <!-- 施設グループ週間 -->
  <div class="font_small" align="center">
    <html:select property="rsvSelectedGrpSid" styleClass="select01" onchange="changeCombo();">
      <html:optionsCollection name="mbhRsv010Form" property="rsvGrpLabelList" value="value" label="label" />
    </html:select>
  </div>

  <logic:notEqual name="mbhRsv010Form" property="rsvSelectedGrpSid" value="-1">

  <logic:notEmpty name="mbhRsv010Form" property="rsv010SisetuList" scope="request">

  <bean:define id="frDate" name="mbhRsv010Form" property="rsv010FrDispDate" type="java.lang.String"/>
  <bean:define id="toDate" name="mbhRsv010Form" property="rsv010ToDispDate" type="java.lang.String"/>

  <% if (frDate.length() >= 8) { %>
  <%    frDate = frDate.substring(0, 4) + gsMsg.getMessage(request, "cmn.year") + frDate.substring(4, 6) + gsMsg.getMessage(request, "cmn.month") + frDate.substring(6) + gsMsg.getMessage(request, "cmn.day");%>
  <% } %>
  <% if (toDate.length() >= 8) { %>
  <%    toDate = toDate.substring(0, 4) + gsMsg.getMessage(request, "cmn.year") + toDate.substring(4, 6) + gsMsg.getMessage(request, "cmn.month") + toDate.substring(6) + gsMsg.getMessage(request, "cmn.day");%>
  <% } %>

  <div width="100%" style="text-align:center;border:1px solid #a5a1a1;background:#ffffff;padding-right:0px!imporatnt;padding-left:1%;padding-top:3px;padding-bottom:5px;" class="header_both_shadow" align="center">
  <table cellspacing="0" cellpadding="0" style="padding-right:0px!imporatnt;" align="center" width="100%">
    <tr>
      <td colspan="2" height="25px">
        <div align="center" class="font_small"><%= frDate %> ～ <%= toDate %></div>
      </td>
    </tr>
    <tr>
      <td style="background:white;" width="20%">

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
        <logic:notEmpty name="mbhRsv010Form" property="rsv010CalendarList" scope="request">
          <logic:iterate id="calBean" name="mbhRsv010Form" property="rsv010CalendarList" scope="request">

             <logic:equal name="calBean" property="weekKbn" value="1">
               <div class="ui-block-e grid_block">
                 <div class="ui-bar ui-bar-d grid_position_head">
                   <div class="font_xsmall grid_text_position_head" style="color:red;"><bean:write name="calBean" property="dspDayString" /></div>
                 </div>
               </div>
             </logic:equal>

             <logic:equal name="calBean" property="weekKbn" value="7">
               <div class="ui-block-e grid_block">
                 <div class="ui-bar ui-bar-d grid_position_head">
                   <div class="font_xsmall grid_text_position_head" style="color:blue;"><bean:write name="calBean" property="dspDayString" /></div>
                 </div>
               </div>
             </logic:equal>

             <logic:notEqual name="calBean" property="weekKbn" value="1">
               <logic:notEqual name="calBean" property="weekKbn" value="7">
                 <div class="ui-block-e grid_block">
                   <div class="ui-bar ui-bar-d grid_position_head">

                    <logic:equal name="calBean" property="holidayKbn" value="1">
                      <div class="font_xsmall grid_text_position_head" style="color:red;"><bean:write name="calBean" property="dspDayString" /></div>
                    </logic:equal>

                    <logic:notEqual name="calBean" property="holidayKbn" value="1">
                     <div class="font_xsmall grid_text_position_head"><bean:write name="calBean" property="dspDayString" /></div>
                    </logic:notEqual>

                   </div>
                 </div>
               </logic:notEqual>
             </logic:notEqual>

           </logic:iterate>
        </logic:notEmpty>
        </div>

      </td>
    </tr>

  <logic:notEmpty name="mbhRsv010Form" property="rsv010SisetuList" scope="request">
    <logic:iterate id="sisetu" name="mbhRsv010Form" property="rsv010SisetuList" scope="request" indexId="cnt">

    <tr>

      <td>


        <% String sisName = null; %>
        <bean:define id="sisNm" name="sisetu" property="rsdName" type="java.lang.String"/>
        <% if (sisNm.length() > 8) { %>
        <% sisName = sisNm.substring(0, 8); %>
        <% } %>
        <% if (sisNm.length() > 4) { %>
        <% sisName = sisNm.substring(0, 4) + "<wbr>" + sisNm.substring(4); %>
        <% } else {%>
        <% sisName = sisNm; %>
        <% } %>


        <div class="ui-grid-a">
          <div class="ui-block-e" style="padding:0px!important;width:100%!important;">
            <div class="ui-bar ui-bar-c grid_position_title">
              <div class="font_small grid_text_position_title" style="text-shadow: 1px 1px 0 #ddd;"><%= sisName %></div>
            </div>
          </div>
        </div>
      </td>

      <logic:notEmpty name="sisetu" property="rsvWeekList">
        <logic:iterate id="week" name="sisetu" property="rsvWeekList">

        <td>
        <div class="ui-grid-a">

          <logic:notEmpty name="week" property="yoyakuDayList">
            <logic:iterate id="day" name="week" property="yoyakuDayList">

              <logic:notEmpty name="day" property="yoyakuList">

                <div class="ui-block-e grid_block">
                  <a href="#" onClick="popupRsvData('rsv_<bean:write name="sisetu" property="rsdSid" />_<bean:write name="day" property="yrkDateStr" />');">
                    <div class="ui-bar ui-bar-c grid_position">
                      <div class="font_small grid_text_position_naiyo"><img src="../mobile/sp/imgages/icon_check.gif"></img></div>
                    </div>
                  </a>
                </div>

              </logic:notEmpty>

              <logic:empty name="day" property="yoyakuList">


                  <div class="ui-block-e grid_block">
                  <logic:equal name="sisetu" property="racAuth" value="1">
                    <a href="#" onclick="moveSisetuAdd('<bean:write name="day" property="yrkDateStr" />', '<bean:write name="sisetu" property="rsdSid" />');">
                      <div class="ui-bar ui-bar-c grid_position">
                        <div class="font_small grid_text_position_naiyo"><img src="../mobile/sp/imgages/icon_add_data.gif"></div>
                      </div>
                    </a>
                  </logic:equal>
                  <logic:notEqual name="sisetu" property="racAuth" value="1">
                      <div class="ui-bar ui-bar-c grid_position">
                        <div class="font_small grid_text_position_naiyo"></div>
                      </div>
                  </logic:notEqual>
                  </div>


              </logic:empty>

            </logic:iterate>
          </logic:notEmpty>
        </div>
        </td>

        </logic:iterate>
      </logic:notEmpty>

    </tr>

    </logic:iterate>
  </logic:notEmpty>

  </table>
  </div>

  </logic:notEmpty>



  <div data-role="controlgroup" data-type="horizontal" align="center">
    <a href="#" onclick="buttonPush('zenzitu_ido');" data-inline="true" data-role="button"><div class="font_small"><gsmsg:write key="cmn.previous.day" /></div></a>
    <a href="#" onclick="buttonPush('kyo');" data-inline="true" data-role="button"><div class="font_small"><gsmsg:write key="cmn.today" /></div></a>
    <a href="#" onclick="buttonPush('yokuzitu_ido');" data-inline="true" data-role="button"><div class="font_small"><gsmsg:write key="cmn.nextday" /></div></a>
  </div>

  <div data-role="controlgroup" data-type="horizontal" align="center">
    <a href="#" onclick="buttonPush('zensyu_ido');" data-inline="true" data-role="button"><div class="font_small"><gsmsg:write key="mobile.24" /></div></a>
    <a href="#" onclick="buttonPush('yokusyu_ido');" data-inline="true" data-role="button"><div class="font_small"><gsmsg:write key="mobile.25" /></div></a>
  </div>

</logic:notEqual>
<br>

<ul data-role="listview" data-theme="d" data-dividertheme="c">
  <li><a href="../mobile/sp_rsv020.do?mobileType=1"><gsmsg:write key="cmn.days" /></a></li>
  <li><a href="../mobile/sp_rsv010.do?mobileType=1"><gsmsg:write key="cmn.weekly" /></a></li>
</ul>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

  <logic:notEqual name="mbhRsv010Form" property="rsvSelectedGrpSid" value="-1">

  <logic:notEmpty name="mbhRsv010Form" property="rsv010SisetuList" scope="request">
    <logic:iterate id="sisetu" name="mbhRsv010Form" property="rsv010SisetuList" scope="request" indexId="cnt">

      <logic:notEmpty name="sisetu" property="rsvWeekList">
        <logic:iterate id="week" name="sisetu" property="rsvWeekList">

          <logic:notEmpty name="week" property="yoyakuDayList">
            <logic:iterate id="day" name="week" property="yoyakuDayList">

              <logic:notEmpty name="day" property="yoyakuList">

                <div id="rsv_<bean:write name="sisetu" property="rsdSid" />_<bean:write name="day" property="yrkDateStr" />" style="position:absolute;bottom:0;display:none;z-index:10;display:none;width:100%;height:100%;background:rgba(255, 255, 255, 0.7);">
                  <div class="rsv_popupmenu">
                    <div style="text-align:right;">
                      <a href="#" onclick="popupRsvData('rsv_<bean:write name="sisetu" property="rsdSid" />_<bean:write name="day" property="yrkDateStr" />');" data-role="button" data-icon="delete" data-iconpos="notext">Close</a>
                    </div>

                    <div align="center">
                      <ul style="width:80%;" data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">

                        <bean:define id="yrDate" name="day" property="yrkDateStr" type="java.lang.String"/>
                        <% if (yrDate.length() >= 8) { %>
                        <%    yrDate = yrDate.substring(0, 4) + gsMsg.getMessage(request, "cmn.year") + yrDate.substring(4, 6) + gsMsg.getMessage(request, "cmn.month") + yrDate.substring(6) + gsMsg.getMessage(request, "cmn.day");%>
                        <% } %>

                        <li data-role="list-divider" style="background:#ffffff;">
                          <div class="font_small" align="center"><b><bean:write name="sisetu" property="rsdName" /></b><hr><%= yrDate %></div>
                        </li>

                          <logic:iterate id="yrk" name="day" property="yoyakuList" indexId="idx">
                            <bean:define id="s_rsdSid" name="sisetu" property="rsdSid" type="java.lang.Integer" />
                            <bean:define id="s_rsySid" name="yrk" property="rsySid" type="java.lang.Integer" />
                            <bean:define id="s_date" name="day" property="yrkDateStr"  type="java.lang.String" />

                            <logic:notEmpty name="yrk" property="rsyNaiyo">
                            <bean:define id="scnaiyou" name="yrk" property="rsyNaiyo" />
                            <%
                              String tmpText = (String)pageContext.getAttribute("scnaiyou",PageContext.PAGE_SCOPE);
                              String tmpText2 = jp.co.sjts.util.StringUtilHtml.transToHTml(tmpText);
                            %>

                            <li>

                            <a href="#" onclick="return moveSisetuEdit('<bean:write name="yrk" property="rsySid" />');" title="<gsmsg:write key="cmn.content" />:<%= tmpText2 %>">
                            </logic:notEmpty>
                            <logic:empty name="yrk" property="rsyNaiyo">

                            <li>
                            <a href="#" title="<bean:write name="yrk" property="rsyMok" />" onclick="return moveSisetuEdit('<bean:write name="yrk" property="rsySid" />');">
                            </logic:empty>

                              <logic:notEmpty name="yrk" property="yrkRiyoDateStr">
                                <div class="font_xsmall">
                                  <span style="color:#ff0000"><bean:write name="yrk" property="yrkRiyoDateStr" /></span>
                                </div>
                              </logic:notEmpty>
                              <span class="font_small" style="color:blue;">
                                <logic:notEmpty name="yrk" property="rsyMok"><bean:write name="yrk" property="rsyMok" /></logic:notEmpty>
                                <logic:notEmpty name="yrk" property="rsyMok">
                                  <logic:notEmpty name="yrk" property="yrkName">&nbsp;/&nbsp;</logic:notEmpty>
                                </logic:notEmpty>
                                <logic:notEmpty name="yrk" property="yrkName"><bean:write name="yrk" property="yrkName" /></logic:notEmpty>
                              </span>
                            </a>

                          </li>

                        </logic:iterate>

                      </ul>
                      <logic:equal name="sisetu" property="racAuth" value="1">
                        <a href="#" onclick="moveSisetuAdd('<bean:write name="day" property="yrkDateStr" />', '<bean:write name="sisetu" property="rsdSid" />');" data-inline="true" data-role="button" data-icon="plus"><div class="font_small"><gsmsg:write key="cmn.new" /><gsmsg:write key="cmn.entry" /></div></a>
                      </logic:equal>
                    </div>

                  </div>
                </div>

              </logic:notEmpty>

            </logic:iterate>
          </logic:notEmpty>

        </logic:iterate>
      </logic:notEmpty>

    </logic:iterate>
  </logic:notEmpty>

  </logic:notEqual>

</html:form>
</div><!-- /page -->

</body>
</html:html>