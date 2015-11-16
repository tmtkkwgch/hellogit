<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhRsv010Form"; %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cmn.reserve" /><gsmsg:write key="cmn.weeks" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body class="body_03">
<html:form action="/mobile/mh_rsv010">
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


<b><%= emojiTokei.toString() %><gsmsg:write key="cmn.reserve" /><br>
<gsmsg:write key="cmn.weeks" />
</b>
<hr>


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

  <% if (sigSid == -1) { %>
    <logic:iterate id="sisetuGp" name="mbhRsv010Form" property="rsvGrpLabelList">
      <logic:notEqual name="sisetuGp" property="value" value="0">
      <logic:notEqual name="sisetuGp" property="value" value="-1">
        <a href="../mobile/mh_rsv010.do<%= jsessionId.toString() %>?rsvSelectedGrpSid=<bean:write name="sisetuGp" property="value" />"><bean:write name="sisetuGp" property="label" /></a><br>
        <bean:define id="sisetuGpSid" name="sisetuGp" property="value" />
      </logic:notEqual>
      </logic:notEqual>
    </logic:iterate>

  <% } else { %>


  <!-- 施設グループ週間 -->
  <logic:equal name="mbhRsv010Form" property="rsv010DspMode" value="0">

  <span class="text_bb1"><gsmsg:write key="cmn.group" />：</span>
  <html:select property="rsvSelectedGrpSid" styleClass="select01">
    <html:optionsCollection name="mbhRsv010Form" property="rsvGrpLabelList" value="value" label="label" />
  </html:select><br>
  <input name="groupButton" value="選択" type="submit">
  <hr>

  <logic:notEmpty name="mbhRsv010Form" property="rsv010SisetuList" scope="request">


  <bean:define id="frDate" name="mbhRsv010Form" property="rsv010FrDispDate" type="java.lang.String"/>
  <bean:define id="toDate" name="mbhRsv010Form" property="rsv010ToDispDate" type="java.lang.String"/>
  <% if (frDate.length() >= 8) { %>
  <%    frDate = frDate.substring(4, 6) + "月" + frDate.substring(6) + "日";%>
  <% } %>
  <% if (toDate.length() >= 8) { %>
  <%    toDate = toDate.substring(4, 6) + "月" + toDate.substring(6) + "日";%>
  <% } %>
  <%= frDate %>-<%= toDate %>
  <br>
  <br>

  <table>
    <tr>
      <td>名前</td>
      <%--
      <td><bean:write name="mbhRsv010Form" property="rsv010WeekHeadStr" filter="false" /></td>
       --%>
      <td>


        <logic:notEmpty name="mbhRsv010Form" property="rsv010CalendarList2" scope="request">
          <logic:iterate id="calBean" name="mbhRsv010Form" property="rsv010CalendarList2" scope="request">

             <logic:equal name="calBean" property="weekKbn" value="1">
               <span style="font-size:14px;color:red;letter-spacing:0px;"><bean:write name="calBean" property="dspDayString" /></span>
             </logic:equal>

             <logic:equal name="calBean" property="weekKbn" value="7">
               <span style="font-size:14px;color:blue;letter-spacing:0px;"><bean:write name="calBean" property="dspDayString" /></span>
             </logic:equal>

             <logic:notEqual name="calBean" property="weekKbn" value="1">
               <logic:notEqual name="calBean" property="weekKbn" value="7">
                  <logic:equal name="calBean" property="holidayKbn" value="1">
                    <span style="font-size:14px;color:red;letter-spacing:0px;"><bean:write name="calBean" property="dspDayString" /></span>
                  </logic:equal>

                  <logic:notEqual name="calBean" property="holidayKbn" value="1">
                   <span style="font-size:14px;letter-spacing:0px;"><bean:write name="calBean" property="dspDayString" /></span>
                  </logic:notEqual>
               </logic:notEqual>
             </logic:notEqual>

           </logic:iterate>
        </logic:notEmpty>


      </td>
    </tr>

    <logic:iterate id="sisetu" name="mbhRsv010Form" property="rsv010SisetuList" scope="request" indexId="cnt">

    <% String sisName = null; %>
    <bean:define id="sisNm" name="sisetu" property="rsdName" type="java.lang.String"/>
    <% sisName = sisNm.substring(0, 2); %>

    <tr>
      <td><a href="../mobile/mh_rsv010.do<%= jsessionId.toString() %>?rsvSelectedGrpSid=<bean:write name="mbhRsv010Form" property="rsvSelectedGrpSid" />&rsv010SisetuId=<bean:write name="sisetu" property="rsdSid" />&rsvDspFrom=<bean:write name="mbhRsv010Form" property="rsvDspFrom" />&rsv010DspMode=1"><%= sisName %></a></td>
      <td><bean:write name="sisetu" property="rsyMbWeekStr" /></td>
    </tr>

    </logic:iterate>
  </logic:notEmpty>
    <tr>
      <td>名前</td>
      <td>


        <logic:notEmpty name="mbhRsv010Form" property="rsv010CalendarList2" scope="request">
          <logic:iterate id="calBean" name="mbhRsv010Form" property="rsv010CalendarList2" scope="request">

             <logic:equal name="calBean" property="weekKbn" value="1">
               <span style="font-size:14px;color:red;letter-spacing:0px;"><bean:write name="calBean" property="dspDayString" /></span>
             </logic:equal>

             <logic:equal name="calBean" property="weekKbn" value="7">
               <span style="font-size:14px;color:blue;letter-spacing:0px;"><bean:write name="calBean" property="dspDayString" /></span>
             </logic:equal>

             <logic:notEqual name="calBean" property="weekKbn" value="1">
               <logic:notEqual name="calBean" property="weekKbn" value="7">
                  <logic:equal name="calBean" property="holidayKbn" value="1">
                    <span style="font-size:14px;color:red;letter-spacing:0px;"><bean:write name="calBean" property="dspDayString" /></span>
                  </logic:equal>

                  <logic:notEqual name="calBean" property="holidayKbn" value="1">
                   <span style="font-size:14px;letter-spacing:0px;"><bean:write name="calBean" property="dspDayString" /></span>
                  </logic:notEqual>
               </logic:notEqual>
             </logic:notEqual>

           </logic:iterate>
        </logic:notEmpty>


      </td>
    </tr>
  </table>
  <hr>
  <font size="-1">予約有：*</font>
  </logic:equal>


  <!-- 施設週間 -->
  <logic:equal name="mbhRsv010Form" property="rsv010DspMode" value="1">
  <html:hidden property="rsvSelectedGrpSid" />
  <logic:notEmpty name="mbhRsv010Form" property="rsv010SisetuList" scope="request">
    <logic:iterate id="sisetu" name="mbhRsv010Form" property="rsv010SisetuList" scope="request" indexId="cnt">


    <bean:define id="siId" name="mbhRsv010Form" property="rsv010SisetuId" type="java.lang.String"/>
    <logic:equal name="sisetu" property="rsdSid" value="<%= siId %>">

      <bean:write name="sisetu" property="rsdName" /><a href="../mobile/mh_rsv040.do<%= jsessionId.toString() %>?rsvGrpFlg=<bean:write name="mbhRsv010Form" property="rsvGrpFlg" />&rsvBackPgId=mbh_rsv010&rsvDspFrom=<bean:write name="mbhRsv010Form" property="rsvDspFrom" />&rsv040GrpSid=<bean:write name="mbhRsv010Form" property="rsvSelectedGrpSid" />&rsvSelectedGrpSid=<bean:write name="mbhRsv010Form" property="rsvSelectedGrpSid" />&rsv010SisetuId=<bean:write name="mbhRsv010Form" property="rsv010SisetuId" />&rsv010DspMode=<bean:write name="mbhRsv010Form" property="rsv010DspMode" />">(施設変更)</a><br>

      <hr>

      <logic:notEmpty name="sisetu" property="rsvWeekList">
        <logic:iterate id="week" name="sisetu" property="rsvWeekList">

          <logic:notEmpty name="week" property="yoyakuDayList">
            <logic:iterate id="day" name="week" property="yoyakuDayList">

              <% String spanColor = null; %>
              <% String dayName = null; %>


              <bean:define id="strDate" name="day" property="yrkDateStr" type="java.lang.String"/>
              <% if (strDate.length() >= 8) { %>
              <%    strDate = strDate.substring(4, 6) + "月" + strDate.substring(6) + "日";%>
              <% } %>

              <logic:equal name="day" property="yrkYobi" value="1">
                <% spanColor = "red"; %>
                <% dayName = "日"; %>
              </logic:equal>
              <logic:equal name="day" property="yrkYobi" value="2">
                <% spanColor = "#000000"; %>
                <% dayName = "月"; %>
              </logic:equal>
              <logic:equal name="day" property="yrkYobi" value="3">
                <% spanColor = "#000000"; %>
                <% dayName = "火"; %>
              </logic:equal>
              <logic:equal name="day" property="yrkYobi" value="4">
                <% spanColor = "#000000"; %>
                <% dayName = "水"; %>
              </logic:equal>
              <logic:equal name="day" property="yrkYobi" value="5">
                <% spanColor = "#000000"; %>
                <% dayName = "木"; %>
              </logic:equal>
              <logic:equal name="day" property="yrkYobi" value="6">
                <% spanColor = "#000000"; %>
                <% dayName = "金"; %>
              </logic:equal>
              <logic:equal name="day" property="yrkYobi" value="7">
                <% spanColor = "blue"; %>
                <% dayName = "土"; %>
              </logic:equal>

              <logic:notEmpty name="day" property="holName">
                <% spanColor = "#ff0000"; %>
              </logic:notEmpty>

             <span style="color:<%= spanColor %>"><%= strDate %>(<%= dayName %>)</span>
             <logic:equal name="sisetu" property="racAuth" value="1">
             <a href="../mobile/mh_rsv030.do<%= jsessionId.toString() %>?rsv010DspMode=<bean:write name="mbhRsv010Form" property="rsv010DspMode" />&rsv010SisetuId=<bean:write name="mbhRsv010Form" property="rsv010SisetuId" />&rsvBackPgId=mbh_rsv010&rsv030SinkiDefaultDate=<bean:write name="day" property="yrkDateStr" />&rsvSelectedGrpSid=<bean:write name="mbhRsv010Form" property="rsvSelectedGrpSid" />&rsvDspFrom=<bean:write name="mbhRsv010Form" property="rsvDspFrom" />&rsvBackPgId=mbh_rsv010&rsvGrpFlg=<bean:write name="mbhRsv010Form" property="rsvGrpFlg" />&rsv030RsdSid=<bean:write name="sisetu" property="rsdSid" />"><font size="-1"><gsmsg:write key="cmn.entry" /></font></a>
             </logic:equal>
             <br>

              <logic:notEmpty name="day" property="holName"><span id="rt"><font size="-2" color="#ff0000"><bean:write name="day" property="holName" /></font></span></logic:notEmpty>


              <logic:notEmpty name="day" property="yoyakuList">
                <logic:iterate id="yrk" name="day" property="yoyakuList" indexId="idx">
                  <bean:define id="index" value="<%= String.valueOf(((Integer) idx).intValue()) %>" />
                  <logic:notEqual name="index" value="0"><br><img src="../common/images/spacer.gif" width="1px" height="10px" border="0"><br></logic:notEqual>

                  <bean:define id="s_rsdSid" name="sisetu" property="rsdSid" type="java.lang.Integer" />
                  <bean:define id="s_rsySid" name="yrk" property="rsySid" type="java.lang.Integer" />
                  <bean:define id="s_date" name="day" property="yrkDateStr"  type="java.lang.String" />

                  <logic:notEmpty name="yrk" property="rsyNaiyo">
                  <bean:define id="scnaiyou" name="yrk" property="rsyNaiyo" />
                  <%
                    String tmpText = (String)pageContext.getAttribute("scnaiyou",PageContext.PAGE_SCOPE);
                    String tmpText2 = jp.co.sjts.util.StringUtilHtml.transToHTml(tmpText);
                  %>
                  <logic:notEmpty name="yrk" property="yrkRiyoDateStr"><font size="-2" color="#ff0000"><%= emojiTokei.toString() %><bean:write name="yrk" property="yrkRiyoDateStr" /></font><br></logic:notEmpty>
                  ┗<a href="../mobile/mh_rsv030.do<%= jsessionId.toString() %>?rsv030ProcMode=1&rsv030RsySid=<bean:write name="yrk" property="rsySid" />&rsv010DspMode=<bean:write name="mbhRsv010Form" property="rsv010DspMode" />&rsv010SisetuId=<bean:write name="mbhRsv010Form" property="rsv010SisetuId" />&rsvBackPgId=mbh_rsv010&rsv030SinkiDefaultDate=<bean:write name="day" property="yrkDateStr" />&rsvSelectedGrpSid=<bean:write name="mbhRsv010Form" property="rsvSelectedGrpSid" />&rsvDspFrom=<bean:write name="mbhRsv010Form" property="rsvDspFrom" />&rsvBackPgId=mbh_rsv010&rsvGrpFlg=<bean:write name="mbhRsv010Form" property="rsvGrpFlg" />&rsv030RsdSid=<bean:write name="sisetu" property="rsdSid" />" title="<gsmsg:write key="cmn.content" />:<%= tmpText2 %>">
                  </logic:notEmpty>
                  <logic:empty name="yrk" property="rsyNaiyo">
                  <logic:notEmpty name="yrk" property="yrkRiyoDateStr"><font size="-2" color="#ff0000"><%= emojiTokei.toString() %><bean:write name="yrk" property="yrkRiyoDateStr" /></font><br></logic:notEmpty>
                  ┗<a href="../mobile/mh_rsv030.do<%= jsessionId.toString() %>?rsv030ProcMode=1&rsv030RsySid=<bean:write name="yrk" property="rsySid" />&rsv010DspMode=<bean:write name="mbhRsv010Form" property="rsv010DspMode" />&rsv010SisetuId=<bean:write name="mbhRsv010Form" property="rsv010SisetuId" />&rsvBackPgId=mbh_rsv010&rsv030SinkiDefaultDate=<bean:write name="day" property="yrkDateStr" />&rsvSelectedGrpSid=<bean:write name="mbhRsv010Form" property="rsvSelectedGrpSid" />&rsvDspFrom=<bean:write name="mbhRsv010Form" property="rsvDspFrom" />&rsvBackPgId=mbh_rsv010&rsvGrpFlg=<bean:write name="mbhRsv010Form" property="rsvGrpFlg" />&rsv030RsdSid=<bean:write name="sisetu" property="rsdSid" />" title="<bean:write name="yrk" property="rsyMok" />">
                  </logic:empty>


                    <span class="sc_link_1">
                      <logic:notEmpty name="yrk" property="rsyMok"><bean:write name="yrk" property="rsyMok" /></logic:notEmpty>
                      <logic:notEmpty name="yrk" property="rsyMok">
                        <logic:notEmpty name="yrk" property="yrkName">/</logic:notEmpty>
                      </logic:notEmpty>
                      <logic:notEmpty name="yrk" property="yrkName"><bean:write name="yrk" property="yrkName" /></logic:notEmpty>
                    </span>
                  </a>

                </logic:iterate>
              </logic:notEmpty>

            <br>
            </logic:iterate>
          </logic:notEmpty>

        </logic:iterate>
      </logic:notEmpty>

      </logic:equal>

    </logic:iterate>
  </logic:notEmpty>

  <logic:equal name="mbhRsv010Form" property="rsvGrpFlg" value="0">
    <div align="center">
      <input name="mh_rsv010back" value="<gsmsg:write key="cmn.back" />" type="submit">
    </div>
  </logic:equal>

  </logic:equal>


  <hr>
  <div align="center">
    <a href="../mobile/mh_rsv010.do<%= jsessionId.toString() %>?CMD=zensyu_ido&rsvGrpFlg=<bean:write name="mbhRsv010Form" property="rsvGrpFlg" />&rsvDspFrom=<bean:write name="mbhRsv010Form" property="rsvDspFrom" />&rsvSelectedGrpSid=<bean:write name="mbhRsv010Form" property="rsvSelectedGrpSid" />&rsv010SisetuId=<bean:write name="mbhRsv010Form" property="rsv010SisetuId" />&rsv010DspMode=<bean:write name="mbhRsv010Form" property="rsv010DspMode" />"><gsmsg:write key="mobile.24" /></a>/<a href="../mobile/mh_rsv010.do<%= jsessionId.toString() %>?CMD=zenzitu_ido&rsvGrpFlg=<bean:write name="mbhRsv010Form" property="rsvGrpFlg" />&rsvDspFrom=<bean:write name="mbhRsv010Form" property="rsvDspFrom" />&rsvSelectedGrpSid=<bean:write name="mbhRsv010Form" property="rsvSelectedGrpSid" />&rsv010SisetuId=<bean:write name="mbhRsv010Form" property="rsv010SisetuId" />&rsv010DspMode=<bean:write name="mbhRsv010Form" property="rsv010DspMode" />"><gsmsg:write key="cmn.previous.day" /></a>/<a href="../mobile/mh_rsv010.do<%= jsessionId.toString() %>?CMD=kyo&rsvGrpFlg=<bean:write name="mbhRsv010Form" property="rsvGrpFlg" />&rsvDspFrom=<bean:write name="mbhRsv010Form" property="rsvDspFrom" />&rsvSelectedGrpSid=<bean:write name="mbhRsv010Form" property="rsvSelectedGrpSid" />&rsv010SisetuId=<bean:write name="mbhRsv010Form" property="rsv010SisetuId" />&rsv010DspMode=<bean:write name="mbhRsv010Form" property="rsv010DspMode" />"><gsmsg:write key="cmn.today" /></a>/<a href="../mobile/mh_rsv010.do<%= jsessionId.toString() %>?CMD=yokuzitu_ido&rsvGrpFlg=<bean:write name="mbhRsv010Form" property="rsvGrpFlg" />&rsvDspFrom=<bean:write name="mbhRsv010Form" property="rsvDspFrom" />&rsvSelectedGrpSid=<bean:write name="mbhRsv010Form" property="rsvSelectedGrpSid" />&rsv010SisetuId=<bean:write name="mbhRsv010Form" property="rsv010SisetuId" />&rsv010DspMode=<bean:write name="mbhRsv010Form" property="rsv010DspMode" />"><gsmsg:write key="cmn.nextday" /></a>/<a href="../mobile/mh_rsv010.do<%= jsessionId.toString() %>?CMD=yokusyu_ido&rsvGrpFlg=<bean:write name="mbhRsv010Form" property="rsvGrpFlg" />&rsvDspFrom=<bean:write name="mbhRsv010Form" property="rsvDspFrom" />&rsvSelectedGrpSid=<bean:write name="mbhRsv010Form" property="rsvSelectedGrpSid" />&rsv010SisetuId=<bean:write name="mbhRsv010Form" property="rsv010SisetuId" />&rsv010DspMode=<bean:write name="mbhRsv010Form" property="rsv010DspMode" />"><gsmsg:write key="mobile.25" /></a>
  </div>

  <hr>

  <a href="../mobile/mh_rsv010.do<%= jsessionId.toString() %>"><gsmsg:write key="cmn.group" /><gsmsg:write key="cmn.weeks" /></a>
  <br>
  <a href="../mobile/mh_rsv020.do<%= jsessionId.toString() %>"><gsmsg:write key="cmn.group" /><gsmsg:write key="cmn.days2" /></a>
  <br>
  <a href="../mobile/mh_rsv040.do<%= jsessionId.toString() %>?rsvGrpFlg=1&rsv040DspMode=2"><gsmsg:write key="cmn.facility" /><gsmsg:write key="cmn.weeks" /></a>
  <br>
  <a href="../mobile/mh_rsv040.do<%= jsessionId.toString() %>?rsvGrpFlg=1&rsv040DspMode=1"><gsmsg:write key="cmn.facility" /><gsmsg:write key="cmn.days2" /></a>

  <% } %>

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>