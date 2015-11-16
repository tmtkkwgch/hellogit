<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhRsv020Form"; %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cmn.reserve" /><gsmsg:write key="cmn.days2" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body class="body_03">
<html:form action="/mobile/mh_rsv020">
<input type="hidden" name="CMD" value="">
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsv020FromHour" />
<html:hidden property="rsv020ToHour" />
<html:hidden property="rsv020ColSpan" />
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


<b><%= emojiTokei.toString() %><gsmsg:write key="cmn.reserve" /><br>
  <logic:equal name="mbhRsv020Form" property="rsv020DspMode" value="0">
    <gsmsg:write key="cmn.group" />
  </logic:equal>
  <gsmsg:write key="cmn.days2" />
</b>
<hr>

<bean:define id="sigSid" name="mbhRsv020Form" property="rsvSelectedGrpSid" type="java.lang.Integer"/>
<% if (sigSid == -1) { %>
  <logic:iterate id="sisetuGp" name="mbhRsv020Form" property="rsvGrpLabelList">
    <logic:notEqual name="sisetuGp" property="value" value="0">
    <logic:notEqual name="sisetuGp" property="value" value="-1">
      <a href="../mobile/mh_rsv020.do<%= jsessionId.toString() %>?rsvSelectedGrpSid=<bean:write name="sisetuGp" property="value" />"><bean:write name="sisetuGp" property="label" /></a><br>
      <bean:define id="sisetuGpSid" name="sisetuGp" property="value" />
    </logic:notEqual>
    </logic:notEqual>
  </logic:iterate>

<% } else { %>


  <!-- グループ日間  -->
<logic:equal name="mbhRsv020Form" property="rsv020DspMode" value="0">
  <logic:notEmpty name="mbhRsv020Form" property="rsv020TimeChartList" scope="request">


  <span class="text_bb1"><gsmsg:write key="cmn.group" />：</span>
  <html:select property="rsvSelectedGrpSid" styleClass="select01" onchange="changeGroupCombo();">
    <html:optionsCollection name="mbhRsv020Form" property="rsvGrpLabelList" value="value" label="label" />
  </html:select><br>
  <input name="groupButton" value="選択" type="submit">
  <hr>

  <logic:messagesPresent message="false">
  <html:errors />
  </logic:messagesPresent>

  <bean:write name="mbhRsv020Form" property="rsvDispYm" />
  <br>
  <br>


  <table>
    <tr>
    <td>名前</td>
    <td>9･･0･･3･･6･</td>
    </tr>

    <logic:notEmpty name="mbhRsv020Form" property="rsv020DaylyList" scope="request">
      <logic:iterate id="sisetu" name="mbhRsv020Form" property="rsv020DaylyList" scope="request" indexId="cnt">


      <% String sisName = null; %>
      <% String sisParam = null; %>
      <bean:define id="sisNm" name="sisetu" property="rsdName" type="java.lang.String"/>
      <% sisName = sisNm.substring(0, 2); %>

      <tr>
      <td><a href="../mobile/mh_rsv020.do<%= jsessionId.toString() %>?rsvDspFrom=<bean:write name="mbhRsv020Form" property="rsvDspFrom" />&rsvSelectedGrpSid=<bean:write name="mbhRsv020Form" property="rsvSelectedGrpSid" />&rsv020SisetuId=<bean:write name="sisetu" property="rsdSid" />&rsv020DspMode=1"><%= sisName %></a></td>

      <logic:notEmpty name="sisetu" property="rsvWeekList">
        <logic:iterate id="week" name="sisetu" property="rsvWeekList">

          <logic:notEmpty name="week" property="yoyakuDayList">
            <logic:iterate id="day" name="week" property="yoyakuDayList">

              <% String spanColor = null; %>
              <% String dayName = null; %>

              <logic:notEmpty name="day" property="holName"><span id="rt"><font size="-2" color="#ff0000"><bean:write name="day" property="holName" /></font></span></logic:notEmpty>
              <td><bean:write name="day" property="rsyMbTimeStr" /></td>

            </logic:iterate>
          </logic:notEmpty>

        </logic:iterate>
      </logic:notEmpty>

      </tr>

      </logic:iterate>
    </logic:notEmpty>
    <tr>
    <td>名前</td>
    <td>9･･0･･3･･6･</td>
    </tr>
    </table>
    <hr>
    <font size="-1">予約有：*</font>
  </logic:notEmpty>
</logic:equal>



  <!-- 施設日間 -->
<logic:equal name="mbhRsv020Form" property="rsv020DspMode" value="1">
  <html:hidden property="rsvSelectedGrpSid" />
  <logic:notEmpty  name="mbhRsv020Form" property="rsv020SisetuId" scope="request">
  <logic:notEmpty name="mbhRsv020Form" property="rsv020TimeChartList" scope="request">

    <logic:notEmpty name="mbhRsv020Form" property="rsv020DaylyList" scope="request">
       <logic:iterate id="sisetu" name="mbhRsv020Form" property="rsv020DaylyList" scope="request" indexId="cnt">

      <bean:define id="sisId" name="mbhRsv020Form" property="rsv020SisetuId" type="java.lang.String"/>
      <logic:equal name="sisetu" property="rsdSid" value="<%= sisId %>">

      [<bean:write name="sisetu" property="rsdName" />]
      <a href="../mobile/mh_rsv040.do<%= jsessionId.toString() %>?rsvGrpFlg=<bean:write name="mbhRsv020Form" property="rsvGrpFlg" />&rsvBackPgId=mbh_rsv020&rsvDspFrom=<bean:write name="mbhRsv020Form" property="rsvDspFrom" />&rsv040GrpSid=<bean:write name="mbhRsv020Form" property="rsvSelectedGrpSid" />&rsvSelectedGrpSid=<bean:write name="mbhRsv020Form" property="rsvSelectedGrpSid" />&rsv020SisetuId=<bean:write name="mbhRsv020Form" property="rsv020SisetuId" />&rsv020DspMode=<bean:write name="mbhRsv020Form" property="rsv020DspMode" />">(施設変更)</a>
      <br>
      <logic:equal name="sisetu" property="racAuth" value="1">
      <a href="../mobile/mh_rsv030.do<%= jsessionId.toString() %>?rsv020DspMode=<bean:write name="mbhRsv020Form" property="rsv020DspMode" />&rsv010SisetuId=<bean:write name="mbhRsv020Form" property="rsv020SisetuId" />&rsvBackPgId=mbh_rsv020&rsv030SinkiDefaultDate=<bean:write name="mbhRsv020Form" property="rsvDspFrom" />&rsvSelectedGrpSid=<bean:write name="mbhRsv020Form" property="rsvSelectedGrpSid" />&rsvGrpFlg=<bean:write name="mbhRsv020Form" property="rsvGrpFlg" />&rsv030RsdSid=<bean:write name="mbhRsv020Form" property="rsv020SisetuId" />&rsvDspFrom=<bean:write name="mbhRsv020Form" property="rsvDspFrom" />&rsv020SisetuId=<bean:write name="mbhRsv020Form" property="rsv020SisetuId" />"><gsmsg:write key="cmn.entry" /></a>
      </logic:equal>

      <hr>

      <logic:notEmpty name="sisetu" property="rsvWeekList">
        <logic:iterate id="week" name="sisetu" property="rsvWeekList">

          <logic:notEmpty name="week" property="yoyakuDayList">
            <logic:iterate id="day" name="week" property="yoyakuDayList">

              <bean:write name="mbhRsv020Form" property="rsvDispYm" />
              <br>

              <% String spanColor = null; %>
              <% String dayName = null; %>

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
                  ┗<a href="../mobile/mh_rsv030.do<%= jsessionId.toString() %>?rsv030ProcMode=1&rsv030RsySid=<bean:write name="yrk" property="rsySid" />&rsv020DspMode=<bean:write name="mbhRsv020Form" property="rsv020DspMode" />&rsv010SisetuId=<bean:write name="mbhRsv020Form" property="rsv020SisetuId" />&rsvBackPgId=mbh_rsv020&rsv030SinkiDefaultDate=<bean:write name="mbhRsv020Form" property="rsvDspFrom" />&rsvSelectedGrpSid=<bean:write name="mbhRsv020Form" property="rsvSelectedGrpSid" />&rsvGrpFlg=<bean:write name="mbhRsv020Form" property="rsvGrpFlg" />&rsv030RsdSid=<bean:write name="mbhRsv020Form" property="rsv020SisetuId" />&rsvDspFrom=<bean:write name="mbhRsv020Form" property="rsvDspFrom" />&rsv020SisetuId=<bean:write name="mbhRsv020Form" property="rsv020SisetuId" />">
                  </logic:notEmpty>
                  <logic:empty name="yrk" property="rsyNaiyo">
                  <logic:notEmpty name="yrk" property="yrkRiyoDateStr"><font size="-2" color="#ff0000"><%= emojiTokei.toString() %><bean:write name="yrk" property="yrkRiyoDateStr" /></font><br></logic:notEmpty>
                  ┗<a href="../mobile/mh_rsv030.do<%= jsessionId.toString() %>?rsv030ProcMode=1&rsv030RsySid=<bean:write name="yrk" property="rsySid" />&rsv020DspMode=<bean:write name="mbhRsv020Form" property="rsv020DspMode" />&rsv010SisetuId=<bean:write name="mbhRsv020Form" property="rsv020SisetuId" />&rsvBackPgId=mbh_rsv020&rsv030SinkiDefaultDate=<bean:write name="mbhRsv020Form" property="rsvDspFrom" />&rsvSelectedGrpSid=<bean:write name="mbhRsv020Form" property="rsvSelectedGrpSid" />&rsvGrpFlg=<bean:write name="mbhRsv020Form" property="rsvGrpFlg" />&rsv030RsdSid=<bean:write name="mbhRsv020Form" property="rsv020SisetuId" />&rsvDspFrom=<bean:write name="mbhRsv020Form" property="rsvDspFrom" />&rsv020SisetuId=<bean:write name="mbhRsv020Form" property="rsv020SisetuId" />">
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

  </logic:notEmpty>
  </logic:notEmpty>

  <logic:equal name="mbhRsv020Form" property="rsvGrpFlg" value="0">
    <div align="center">
      <input name="mh_rsv020back" value="<gsmsg:write key="cmn.back" />" type="submit">
    </div>
  </logic:equal>
</logic:equal>

<hr>

<div align="center">
  <a href="../mobile/mh_rsv020.do<%= jsessionId.toString() %>?CMD=zenzitu_ido&rsvGrpFlg=<bean:write name="mbhRsv020Form" property="rsvGrpFlg" />&rsvDspFrom=<bean:write name="mbhRsv020Form" property="rsvDspFrom" />&rsvSelectedGrpSid=<bean:write name="mbhRsv020Form" property="rsvSelectedGrpSid" />&rsv020SisetuId=<bean:write name="mbhRsv020Form" property="rsv020SisetuId" />&rsv020DspMode=<bean:write name="mbhRsv020Form" property="rsv020DspMode" />"><gsmsg:write key="cmn.previous.day" /></a>/<a href="../mobile/mh_rsv020.do<%= jsessionId.toString() %>?CMD=kyo&rsvGrpFlg=<bean:write name="mbhRsv020Form" property="rsvGrpFlg" />&rsvDspFrom=<bean:write name="mbhRsv020Form" property="rsvDspFrom" />&rsvSelectedGrpSid=<bean:write name="mbhRsv020Form" property="rsvSelectedGrpSid" />&rsv020SisetuId=<bean:write name="mbhRsv020Form" property="rsv020SisetuId" />&rsv020DspMode=<bean:write name="mbhRsv020Form" property="rsv020DspMode" />"><gsmsg:write key="cmn.today" /></a>/<a href="../mobile/mh_rsv020.do<%= jsessionId.toString() %>?CMD=yokuzitu_ido&rsvGrpFlg=<bean:write name="mbhRsv020Form" property="rsvGrpFlg" />&rsvDspFrom=<bean:write name="mbhRsv020Form" property="rsvDspFrom" />&rsvSelectedGrpSid=<bean:write name="mbhRsv020Form" property="rsvSelectedGrpSid" />&rsv020SisetuId=<bean:write name="mbhRsv020Form" property="rsv020SisetuId" />&rsv020DspMode=<bean:write name="mbhRsv020Form" property="rsv020DspMode" />"><gsmsg:write key="cmn.nextday" /></a>
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