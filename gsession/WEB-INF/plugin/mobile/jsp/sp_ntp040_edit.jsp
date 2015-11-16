<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

      <html:hidden property="ntp040FrYear" />
      <html:hidden property="ntp040FrMonth" />
      <html:hidden property="ntp040FrDay" />
      <% String selYearIdStr = ""; %>
      <% String selMonthIdStr = ""; %>
      <% String selDayIdStr = ""; %>
      <% String selActionYearIdStr = ""; %>
      <% String selActionMonthIdStr = ""; %>
      <% String selActionDayIdStr = ""; %>
      <% Integer lastRowNumber = 0; %>

      <bean:define id="colormsg1" value=""/>
      <bean:define id="colormsg2" value=""/>
      <bean:define id="colormsg3" value=""/>
      <bean:define id="colormsg4" value=""/>
      <bean:define id="colormsg5" value=""/>
      <logic:iterate id="mstr" name="mbhNtp040Form" property="ntp040ColorMsgList" indexId="mId" type="java.lang.String">
      <logic:equal name="mId" value="0">
      <% colormsg1 = mstr; %>
      </logic:equal>
      <logic:equal name="mId" value="1">
      <% colormsg2 = mstr; %>
      </logic:equal>
      <logic:equal name="mId" value="2">
      <% colormsg3 = mstr; %>
      </logic:equal>
      <logic:equal name="mId" value="3">
      <% colormsg4 = mstr; %>
      </logic:equal>
      <logic:equal name="mId" value="4">
      <% colormsg5 = mstr; %>
      </logic:equal>
      </logic:iterate>

      <input type="hidden" id="msgCol1" value="<%= colormsg1 %>" />
      <input type="hidden" id="msgCol2" value="<%= colormsg2 %>" />
      <input type="hidden" id="msgCol3" value="<%= colormsg3 %>" />
      <input type="hidden" id="msgCol4" value="<%= colormsg4 %>" />
      <input type="hidden" id="msgCol5" value="<%= colormsg5 %>" />

      <logic:notEmpty name="mbhNtp040Form" property="ntp040DataModelList">
        <logic:iterate id="dataMdl" name="mbhNtp040Form" property="ntp040DataModelList"  indexId="idx">

            <% lastRowNumber =  idx + 1; %>

            <bean:define id="datafrhourval" name="dataMdl" property="frHour" />
            <bean:define id="datafrminval" name="dataMdl" property="frMin"/>
            <bean:define id="datatohourval" name="dataMdl" property="toHour"/>
            <bean:define id="datatominval" name="dataMdl" property="toMin"/>


                <%--


                <logic:equal name="dataMdl" property="ntp040SelectFlg" value="1">
                <div id="initSelect"></div>
                </logic:equal>
                <div id="pos<%= idx + 1 %>">
                </div>NO,<%= idx + 1 %>




              <span class="editButtonArea<%= idx + 1 %>">
                <input class="btn_copy_n2" name="ntpCopyBtn" id="<bean:write name="dataMdl" property="ntp040NtpSid" />" value="複写して登録" type="button">
              </span>

              <logic:equal name="mbhNtp040Form" property="authAddEditKbn" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.AUTH_ADD_EDIT) %>">

                <span class="editButtonArea<%= idx + 1 %>">
                  <input class="btn_edit_n4" name="ntpEditBtn" id="<%= idx + 1 %>" value="<gsmsg:write key="cmn.edit" />" type="button">
                  <input class="close_btn1" id="ntpDellBtn" value="<gsmsg:write key="cmn.delete" />" type="button">
                </span>

                <span class="editButtonArea<%= idx + 1 %>" style="display:none;">
                  <input class="btn_edit_n4" id="<%= idx + 1 %>" name="ntpEditKakuteiBtn" value="確定" type="button">
                  <input class="close_btn1" id="<%= idx + 1 %>" name="ntpEditCancelBtn" value="ｷｬﾝｾﾙ" type="button">
                </span>

              </logic:equal>

              --%>


            <logic:equal name="mbhNtp040Form" property="cmd" value="kakunin">
            <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
            </logic:equal>


            <logic:equal name="mbhNtp040Form" property="cmd" value="edit">
            <font size="-2">■<span class="text_bb1">タイトル</span><span class="titleArea<%= idx + 1 %> text_r2" style="display:none;">※</span></font>
            </logic:equal>

              <logic:equal name="mbhNtp040Form" property="cmd" value="kakunin">
              <li>
              <font size="-2">■<span class="text_bb1">タイトル</span><span class="titleArea<%= idx + 1 %> text_r2" style="display:none;">※</span></font>


              <div class="titleArea<%= idx + 1 %>">
                 <span class="dsp_title_<%= idx + 1 %>"><bean:write name="dataMdl" property="title" /></span>
              </div>
              </li>
              </logic:equal>

              <logic:equal name="mbhNtp040Form" property="cmd" value="edit">
              <div class="titleArea<%= idx + 1 %>">

                <input name="ntp040Title" maxlength="100" size="50" value="<bean:write name="dataMdl" property="title" />" id="ntpTitleTextBox" class="text_base" type="text">

              </div>


              <div data-role="navbar" align="center">
                  <ul>
                    <li style="background-color:#0000FF;">
                      <span style="background-color:#0000FF;"><html:radio name="mbhNtp040Form" property="ntp040Bgcolor" value="1" /></span>
                      <label for="bg_color1" class="text_base">&nbsp;&nbsp;</label><br><span style="color:white;"><div class="font_small"><b><%= colormsg1 %></b></div></span>
                    </li>
                    <li style="background-color:#FF0000;">
                      <span style="background-color:#FF0000;"><html:radio name="mbhNtp040Form" property="ntp040Bgcolor" value="2" /></span>
                      <label for="bg_color2" class="text_base">&nbsp;&nbsp;</label><br><span style="color:white;"><div class="font_small"><b><%= colormsg2 %></b></div></span>
                    </li>
                    <li style="background-color:#009900;">
                      <span style="background-color:#009900;"><html:radio name="mbhNtp040Form" property="ntp040Bgcolor" value="3" /></span>
                      <label for="bg_color3" class="text_base">&nbsp;&nbsp;</label><br><span style="color:white;"><div class="font_small"><b><%= colormsg3 %></b></div></span>
                    </li>
                    <li style="background-color:#ff9900;">
                      <span style="background-color:#ff9900;"><html:radio name="mbhNtp040Form" property="ntp040Bgcolor" value="4" /></span>
                      <label for="bg_color4" class="text_base">&nbsp;&nbsp;</label><br><span style="color:white;"><div class="font_small"><b><%= colormsg4 %></b></div></span>
                    </li>
                    <li style="background-color:#333333;">
                      <span style="background-color:#333333;"><html:radio name="mbhNtp040Form" property="ntp040Bgcolor" value="5" /></span>
                      <label for="bg_color5" class="text_base">&nbsp;&nbsp;</label><br><span style="color:white;"><div class="font_small"><b><%= colormsg5 %></b></div></span>
                  </li>
                </ul>
              </div>


              </logic:equal>

              <logic:equal name="mbhNtp040Form" property="cmd" value="edit">


            <br>
            <font size="-2">■<span class="text_bb1"><gsmsg:write key="ntp.35" /></span><span class="ntpTimeArea<%= idx + 1 %> text_r2">※</span></font>
            <br>

             <% selYearIdStr  = "selYear"  + String.valueOf(idx + 1); %>
             <% selMonthIdStr = "selMonth" + String.valueOf(idx + 1); %>
             <% selDayIdStr   = "selDay"   + String.valueOf(idx + 1); %>

             <bean:define id="dataYear" name="dataMdl" property="ntpYear" type="java.lang.Integer"/>
             <bean:define id="dataMonth" name="dataMdl" property="ntpMonth" type="java.lang.Integer"/>
             <bean:define id="dataDay" name="dataMdl" property="ntpDay" type="java.lang.Integer"/>
<%--
             <select name="ntp040HoiukokuYear" id="<%= selYearIdStr.toString() %>">
               <logic:iterate id="yearLv" name="mbhNtp040Form" property="ntp040YearLavel">
                 <logic:equal name="yearLv" property="value" value="<%= dataYear.toString() %>">
                   <option value="<bean:write name="yearLv" property="value" />" selected="selected"><bean:write name="yearLv" property="label" /></option>
                 </logic:equal>
                 <logic:notEqual name="yearLv" property="value" value="<%= dataYear.toString() %>">
                   <option value="<bean:write name="yearLv" property="value" />"><bean:write name="yearLv" property="label" /></option>
                 </logic:notEqual>
               </logic:iterate>
             </select>

             <select name="ntp040HoiukokuMonth" id="<%= selMonthIdStr %>">
               <logic:iterate id="monthLv" name="mbhNtp040Form" property="ntp040MonthLavel">
                 <logic:equal name="monthLv" property="value" value="<%= dataMonth.toString() %>">
                   <option value="<bean:write name="monthLv" property="value" />" selected="selected"><bean:write name="monthLv" property="label" /></option>
                 </logic:equal>
                 <logic:notEqual name="monthLv" property="value" value="<%= dataMonth.toString() %>">
                   <option value="<bean:write name="monthLv" property="value" />"><bean:write name="monthLv" property="label" /></option>
                 </logic:notEqual>
               </logic:iterate>
             </select>

             <select name="ntp040HoiukokuDay" id="<%= selDayIdStr %>">
               <logic:iterate id="dayLv" name="mbhNtp040Form" property="ntp040DayLavel">
                 <logic:equal name="dayLv" property="value" value="<%= dataDay.toString() %>">
                   <option value="<bean:write name="dayLv" property="value" />" selected="selected"><bean:write name="dayLv" property="label" /></option>
                 </logic:equal>
                 <logic:notEqual name="dayLv" property="value" value="<%= dataDay.toString() %>">
                   <option value="<bean:write name="dayLv" property="value" />"><bean:write name="dayLv" property="label" /></option>
                 </logic:notEqual>
               </logic:iterate>
             </select>
--%>
             <%--
             <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#<%= selYearIdStr %>')[0], $('#<%= selMonthIdStr %>')[0], $('#<%= selDayIdStr %>')[0], 1)">
             <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#<%= selYearIdStr %>')[0], $('#<%= selMonthIdStr %>')[0], $('#<%= selDayIdStr %>')[0], 2)">
             <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#<%= selYearIdStr %>')[0], $('#<%= selMonthIdStr %>')[0], $('#<%= selDayIdStr %>')[0], 3)">
             <input type="button" value="Cal" onclick="wrtCalendarByBtn(this.form.selDay<%= idx + 1 %>, this.form.selMonth<%= idx + 1 %>, this.form.selYear<%= idx + 1 %>, 'ntp040FrCalBtn<%= idx + 1 %>')" class="calendar_btn" id="ntp040FrCalBtn<%= idx + 1 %>">
             --%>
             <jquery:jqtext id="date" name="mbhNtp040Form" property="ntp040HoukokuDate" readonly="true"/>
             <br>
           </logic:equal>



              <logic:equal name="mbhNtp040Form" property="cmd" value="edit">
            <font size="-2">■<span class="text_bb1">時間</span>

               <span class="ntpTimeArea<%= idx + 1 %> text_r2">※</span></font>

               </logic:equal>


             <logic:equal name="mbhNtp040Form" property="cmd" value="kakunin">
              <li>
              <font size="-2">■<span class="text_bb1">時間</span></font>

              <div class="ntpTimeArea<%= idx + 1 %>">

               <span class="dsp_frhour_<%= idx + 1 %>"><bean:write name="dataMdl" property="ntp040DspFrHour" /></span>
               <gsmsg:write key="cmn.hour.input" />
               <span class="dsp_frminute_<%= idx + 1 %>"><bean:write name="dataMdl" property="ntp040DspFrMinute"/></span>
               <gsmsg:write key="cmn.minute.input" />
               ～
               <span class="dsp_tohour_<%= idx + 1 %>"><bean:write name="dataMdl" property="ntp040DspToHour"/></span>
               <gsmsg:write key="cmn.hour.input" />
               <span class="dsp_tominute_<%= idx + 1 %>"><bean:write name="dataMdl" property="ntp040DspToMinute"/></span>
               <gsmsg:write key="cmn.minute.input" />
                 &nbsp;&nbsp;&nbsp;&nbsp;<span id="betWeenDays" class="text_base"></span>

              </div>
              </li>
              </logic:equal>

              <logic:equal name="mbhNtp040Form" property="cmd" value="edit">

              <div class="ntpTimeArea<%= idx + 1 %>">
<div data-role="navbar" align="center">
  <ul>
    <li>
                 <% String ntp040FrHour = "ntp040FrHour_" + (idx + 1); %>
                 <html:select property="ntp040FrHour" value="<%= String.valueOf(datafrhourval) %>" onchange="setToDay();">
                    <html:optionsCollection name="mbhNtp040Form" property="ntp040HourLavel" value="value" label="label" />
                 </html:select>
                 <gsmsg:write key="cmn.hour.input" />
    </li>
    <li>
                 <% String ntp040FrMin = "ntp040FrMin_" + (idx + 1); %>
                 <html:select property="ntp040FrMin" value="<%= String.valueOf(datafrminval) %>" onchange="setToDay();">
                    <html:optionsCollection name="mbhNtp040Form" property="ntp040MinuteLavel" value="value" label="label" />
                 </html:select>
                 <gsmsg:write key="cmn.minute.input" />
   </li>
  </ul>
</div>
                 ～
<div data-role="navbar" align="center">
  <ul>
    <li>
                 <% String ntp040ToHour = "ntp040ToHour_" + (idx + 1); %>
                 <html:select property="ntp040ToHour" value="<%= String.valueOf(datatohourval) %>" onchange="setToDay();">
                    <html:optionsCollection name="mbhNtp040Form" property="ntp040HourLavel" value="value" label="label" />
                 </html:select>
                 <gsmsg:write key="cmn.hour.input" />
    </li>
    <li>
                 <% String ntp040ToMin = "ntp040ToMin_" + (idx + 1); %>
                 <html:select property="ntp040ToMin" value="<%= String.valueOf(datatominval) %>" onchange="setToDay();">
                    <html:optionsCollection name="mbhNtp040Form" property="ntp040MinuteLavel" value="value" label="label" />
                 </html:select>
                 <gsmsg:write key="cmn.minute.input" />
   </li>
  </ul>
</div>
                 <span id="betWeenDays" class="text_base"></span>

              </div>


              </logic:equal>



            <logic:equal name="mbhNtp040Form" property="ntp040AnkenCompanyUse" value="0">

              <logic:equal name="mbhNtp040Form" property="cmd" value="kakunin">

              <li>

              <font size="-2">■<span class="text_bb1"><gsmsg:write key="ntp.11" /></span><span class="text_r2"></span></font>

                <div class="ankenDataArea<%= idx + 1 %>">

                  <img src="../schedule/images/spacer.gif" width="1px" border="0" height="10px">

                  <logic:notEmpty name="dataMdl" property="ankenSid">
                    <span class="text_anken_code"><gsmsg:write key="ntp.29" />：<span class="anken_code_name_<%= idx + 1 %>"><bean:write name="dataMdl" property="ankenCode" /></span></span><br>
                  </logic:notEmpty>

                  <logic:notEmpty name="dataMdl" property="ankenSid">
                    <span class="text_anken">
                      <a id="<bean:write name="dataMdl" property="ankenSid" />" class="sc_link anken_click">
                        <span class="anken_name_<%= idx + 1 %>"><bean:write name="dataMdl" property="ankenName" /></span>
                      </a>
                    </span>
                  </logic:notEmpty>

                </div>

                </li>

                </logic:equal>

                <logic:equal name="mbhNtp040Form" property="cmd" value="edit">
                <br>
                <font size="-2">■<span class="text_bb1"><gsmsg:write key="ntp.11" /></span><span class="text_r2"></span></font>



                  <input type="submit" class="" value="<gsmsg:write key="ntp.11" /><gsmsg:write key="cmn.search" />" name="ntp040adr" /><br>
                  <img src="../schedule/images/spacer.gif" width="1px" border="0" height="10px">
                  <div id="ntp040AnkenIdArea_<%= idx + 1 %>">
                    <logic:notEmpty name="dataMdl" property="ankenSid">
                      <input name="ntp040AnkenSid" value="<bean:write name="dataMdl" property="ankenSid" />" type="hidden">
                    </logic:notEmpty>
                  </div>
                  <div id="ntp040AnkenCodeArea_<%= idx + 1 %>">
                    <logic:notEmpty name="dataMdl" property="ankenCode">
                      <span class="text_anken_code"><gsmsg:write key="ntp.29" />：<span class="anken_code_name_<%= idx + 1 %>"><bean:write name="dataMdl" property="ankenCode" /></span></span>
                    </logic:notEmpty>
                  </div>
                  <div id="ntp040AnkenNameArea_<%= idx + 1 %>">
                    <logic:notEmpty name="dataMdl" property="ankenName">
                      <span class="text_anken">
                        <a id="<bean:write name="dataMdl" property="ankenSid" />" class="sc_link anken_click">
                          <span class="anken_name_<%= idx + 1 %>"><bean:write name="dataMdl" property="ankenName" /></span>
                        </a>
                      </span>
                      <div align="center">
                      <font size="-2"><input type="submit" name="ntp040ankendel" class="" value="<gsmsg:write key="cmn.delete" />" data-icon="delete" data-inline="true" /></font>
                      </div>
                    </logic:notEmpty>
                  </div>

               </logic:equal>


                <logic:equal name="mbhNtp040Form" property="cmd" value="kakunin">

                <li>

                <font size="-2">■<span class="text_bb1"><gsmsg:write key="ntp.15" />・<gsmsg:write key="ntp.16" /></span><span class="text_r2"></span></font>

                <div class="kigyouDataArea<%= idx + 1 %>">

                  <img src="../schedule/images/spacer.gif" width="1px" border="0" height="10px">

                    <logic:notEmpty name="dataMdl" property="companySid">
                      <span class="text_anken_code"><gsmsg:write key="address.7" />：<span class="comp_code_name_<%= idx + 1 %>"><bean:write name="dataMdl" property="companyCode" /></span></span><br>
                    </logic:notEmpty>

                    <logic:notEmpty name="dataMdl" property="companySid">
                      <span class="text_company">
                        <a id="<bean:write name="dataMdl" property="companySid" />" class="sc_link comp_click comp_name_link_<%= idx + 1 %>">
                          <span class="comp_name_<%= idx + 1 %>"><bean:write name="dataMdl" property="companyName" />
                            <logic:notEmpty name="dataMdl" property="companySid">
                              <bean:write name="dataMdl" property="companyBaseName" />
                            </logic:notEmpty>
                          </span>
                        </a>
                      </span>
                    </logic:notEmpty>

                 </div>

                 </li>

               </logic:equal>


               <logic:equal name="mbhNtp040Form" property="cmd" value="edit">
               <br>
               <font size="-2">■<span class="text_bb1"><gsmsg:write key="ntp.15" />・<gsmsg:write key="ntp.16" /></span><span class="text_r2"></span></font>

               <div class="kigyouDataArea<%= idx + 1 %>">

               <div data-role="controlgroup" data-type="horizontal" align="center">

                  <input type="submit" class="" value="<gsmsg:write key="addressbook" />" name="ntp040adrbook" data-inline="true" />
                  <input type="submit" class="" value="<gsmsg:write key="ntp.17" />" name="ntp040rireki" data-inline="true" />

               </div>

                  <img src="../schedule/images/spacer.gif" width="1px" border="0" height="10px">
                  <div id="ntp040CompanyIdArea_<%= idx + 1 %>">
                    <logic:notEmpty name="dataMdl" property="companySid">
                      <input name="ntp040CompanySid" value="<bean:write name="dataMdl" property="companySid" />" type="hidden">
                    </logic:notEmpty>
                  </div>
                  <div id="ntp040CompanyBaseIdArea_<%= idx + 1 %>">
                    <logic:notEmpty name="dataMdl" property="companyBaseSid">
                      <input name="ntp040CompanyBaseSid" value="<bean:write name="dataMdl" property="companyBaseSid" />" type="hidden">
                    </logic:notEmpty>
                  </div>
                  <div id="ntp040CompanyCodeArea_<%= idx + 1 %>">
                    <logic:notEmpty name="dataMdl" property="companyCode">
                      <span class="text_anken_code"><gsmsg:write key="address.7" />：<span class="comp_code_name_<%= idx + 1 %>"><bean:write name="dataMdl" property="companyCode" /></span></span>
                    </logic:notEmpty>
                  </div>
                  <div id="ntp040CompNameArea_<%= idx + 1 %>">
                    <logic:notEmpty name="dataMdl" property="companyName">
                      <span class="text_company">
                        <a id="<bean:write name="dataMdl" property="companySid" />" class="sc_link comp_click">
                          <span class="comp_name_<%= idx + 1 %>"><bean:write name="dataMdl" property="companyName" />
                          <logic:notEmpty name="dataMdl" property="companyName">
                            <bean:write name="dataMdl" property="companyBaseName" />
                          </logic:notEmpty>
                          </span>
                        </a>
                        <div align="center">
                        <font size="-2"><input type="submit" name="ntp040compdel" class="" value="<gsmsg:write key="cmn.delete" />" data-icon="delete" data-inline="true" /></font>
                        </div>
                      </span>
                    </logic:notEmpty>
                  </div>
                  <div id="ntp040AddressIdArea_<%= idx + 1 %>">
                  </div>
                  <div id="ntp040AddressNameArea_<%= idx + 1 %>">
                  </div>

                </div>

                </logic:equal>

            </logic:equal>


            <logic:equal name="mbhNtp040Form" property="ntp040AnkenCompanyUse" value="1">



                <logic:equal name="mbhNtp040Form" property="cmd" value="kakunin">

                <li>
                <span class="text_bb1">■<gsmsg:write key="ntp.11" /></span><span class="text_r2"></span>

                <div class="ankenDataArea<%= idx + 1 %>">

                  <img src="../schedule/images/spacer.gif" width="1px" border="0" height="10px">

                  <logic:notEmpty name="dataMdl" property="ankenSid">
                    <span class="text_anken_code"><gsmsg:write key="ntp.29" />：<span class="anken_code_name_<%= idx + 1 %>"><bean:write name="dataMdl" property="ankenCode" /></span></span><br>
                  </logic:notEmpty>

                  <logic:notEmpty name="dataMdl" property="ankenSid">
                    <span class="text_anken">
                      <a id="<bean:write name="dataMdl" property="ankenSid" />" class="sc_link anken_click">
                        <span class="anken_name_<%= idx + 1 %>"><bean:write name="dataMdl" property="ankenName" /></span>
                      </a>
                    </span>
                  </logic:notEmpty>

                </div>

                </li>

                </logic:equal>

                <logic:equal name="mbhNtp040Form" property="cmd" value="edit">
                <br>
                <span class="text_bb1">■<gsmsg:write key="ntp.11" /></span><span class="text_r2"></span>

                <div class="ankenDataArea<%= idx + 1 %>">

                  <input type="submit" class="" value="<gsmsg:write key="ntp.11" /><gsmsg:write key="cmn.search" />" name="ntp040adr" /><br>
                  <img src="../schedule/images/spacer.gif" width="1px" border="0" height="10px">
                  <div id="ntp040AnkenIdArea_<%= idx + 1 %>">
                    <logic:notEmpty name="dataMdl" property="ankenSid">
                      <input name="ntp040AnkenSid" value="<bean:write name="dataMdl" property="ankenSid" />" type="hidden">
                    </logic:notEmpty>
                  </div>
                  <div id="ntp040AnkenCodeArea_<%= idx + 1 %>">
                    <logic:notEmpty name="dataMdl" property="ankenCode">
                      <span class="text_anken_code"><gsmsg:write key="ntp.29" />：<span class="anken_code_name_<%= idx + 1 %>"><bean:write name="dataMdl" property="ankenCode" /></span></span>
                    </logic:notEmpty>
                  </div>
                  <div id="ntp040AnkenNameArea_<%= idx + 1 %>">
                    <logic:notEmpty name="dataMdl" property="ankenName">
                      <span class="text_anken">
                        <a id="<bean:write name="dataMdl" property="ankenSid" />" class="sc_link anken_click">
                          <span class="anken_name_<%= idx + 1 %>"><bean:write name="dataMdl" property="ankenName" /></span>
                        </a>
                      </span>
                      <div align="center">
                      <font size="-2"><input type="submit" name="ntp040ankendel" class="" value="<gsmsg:write key="cmn.delete" />" data-icon="delete" data-inline="true" /></font>
                      </div>
                    </logic:notEmpty>
                  </div>

                </div>

                </logic:equal>

            </logic:equal>


            <logic:equal name="mbhNtp040Form" property="ntp040AnkenCompanyUse" value="2">

                <logic:equal name="mbhNtp040Form" property="cmd" value="kakunin">

                <li>

                <span class="text_bb1">■<gsmsg:write key="ntp.15" />・<gsmsg:write key="ntp.16" /></span><span class="text_r2"></span>

                <div class="kigyouDataArea<%= idx + 1 %>">

                  <img src="../schedule/images/spacer.gif" width="1px" border="0" height="10px">

                    <logic:notEmpty name="dataMdl" property="companySid">
                      <span class="text_anken_code"><gsmsg:write key="address.7" />：<span class="comp_code_name_<%= idx + 1 %>"><bean:write name="dataMdl" property="companyCode" /></span></span><br>
                    </logic:notEmpty>

                    <logic:notEmpty name="dataMdl" property="companySid">
                      <span class="text_company">
                        <a id="<bean:write name="dataMdl" property="companySid" />" class="sc_link comp_click comp_name_link_<%= idx + 1 %>">
                          <span class="comp_name_<%= idx + 1 %>"><bean:write name="dataMdl" property="companyName" />
                            <logic:notEmpty name="dataMdl" property="companySid">
                              <bean:write name="dataMdl" property="companyBaseName" />
                            </logic:notEmpty>
                          </span>
                        </a>
                      </span>
                    </logic:notEmpty>

                 </div>

                 </li>

                 </logic:equal>

                 <logic:equal name="mbhNtp040Form" property="cmd" value="edit">
                 <br>
                 <span class="text_bb1">■<gsmsg:write key="ntp.15" />・<gsmsg:write key="ntp.16" /></span><span class="text_r2"></span>


               <div class="kigyouDataArea<%= idx + 1 %>">

               <div data-role="controlgroup" data-type="horizontal" align="center">

                  <input type="submit" class="" value="<gsmsg:write key="addressbook" />" name="ntp040adrbook" data-inline="true" />
                  <input type="submit" class="" value="<gsmsg:write key="ntp.17" />" name="ntp040rireki" data-inline="true" />

               </div>

                  <img src="../schedule/images/spacer.gif" width="1px" border="0" height="10px">
                  <div id="ntp040CompanyIdArea_<%= idx + 1 %>">
                    <logic:notEmpty name="dataMdl" property="companySid">
                      <input name="ntp040CompanySid" value="<bean:write name="dataMdl" property="companySid" />" type="hidden">
                    </logic:notEmpty>
                  </div>
                  <div id="ntp040CompanyBaseIdArea_<%= idx + 1 %>">
                    <logic:notEmpty name="dataMdl" property="companyBaseSid">
                      <input name="ntp040CompanyBaseSid" value="<bean:write name="dataMdl" property="companyBaseSid" />" type="hidden">
                    </logic:notEmpty>
                  </div>
                  <div id="ntp040CompanyCodeArea_<%= idx + 1 %>">
                    <logic:notEmpty name="dataMdl" property="companyCode">
                      <span class="text_anken_code"><gsmsg:write key="address.7" />：<span class="comp_code_name_<%= idx + 1 %>"><bean:write name="dataMdl" property="companyCode" /></span></span>
                    </logic:notEmpty>
                  </div>
                  <div id="ntp040CompNameArea_<%= idx + 1 %>">
                    <logic:notEmpty name="dataMdl" property="companyName">
                      <span class="text_company">
                        <a id="<bean:write name="dataMdl" property="companySid" />" class="sc_link comp_click">
                          <span class="comp_name_<%= idx + 1 %>"><bean:write name="dataMdl" property="companyName" />
                          <logic:notEmpty name="dataMdl" property="companyName">
                            <bean:write name="dataMdl" property="companyBaseName" />
                          </logic:notEmpty>
                          </span>
                        </a>

                        <div align="center">
                        <font size="-2"><input type="submit" name="ntp040compdel" class="" value="<gsmsg:write key="cmn.delete" />" data-icon="delete" data-inline="true" /></font>
                        </div>
                      </span>
                    </logic:notEmpty>
                  </div>
                  <div id="ntp040AddressIdArea_<%= idx + 1 %>">
                  </div>
                  <div id="ntp040AddressNameArea_<%= idx + 1 %>">
                  </div>

                </div>

                </logic:equal>

            </logic:equal>


            <logic:equal name="mbhNtp040Form" property="cmd" value="kakunin">

            <li>
            <font size="-2">■<span class="text_bb1">内　容<a id="naiyou" name="naiyou"></a></span></font>

              <div class="naiyouArea<%= idx + 1 %>">
                <span class="dsp_naiyou_<%= idx + 1 %>"><bean:write name="dataMdl" property="ntp040DspValueStr" filter="false"/></span>
              </div>

              </li>

            </logic:equal>

            <logic:equal name="mbhNtp040Form" property="cmd" value="edit">
            <br>
            <font size="-2">■<span class="text_bb1">内　容<a id="naiyou" name="naiyou"></a></span></font>

              <div class="naiyouArea<%= idx + 1 %>">
                <textarea id="inputstr_<%= idx + 1 %>" name="ntp040Value" cols="50" rows="5" onkeyup="showLengthStr(value, 1000, 'inputlength<%= idx + 1 %>');"><bean:write name="dataMdl" property="valueStr" /></textarea>
                <br>
              </div>

            </logic:equal>


            <logic:equal name="mbhNtp040Form" property="ntp040KtBriHhuUse" value="0">




                <bean:define id="ktbunruival" name="dataMdl" property="ktbunruiSid"/>
                <bean:define id="ktbouhouval" name="dataMdl" property="kthouhouSid"/>

                <logic:equal name="mbhNtp040Form" property="cmd" value="kakunin">

                <li>
                <font size="-2">■<span class="text_bb1"><gsmsg:write key="ntp.3" />/<gsmsg:write key="ntp.31" /></span></font>

                <div class="ktBunruiArea<%= idx + 1 %>">
                 <span class="dsp_ktbunrui_<%= idx + 1 %>"><bean:write name="dataMdl" property="ntp040DspKtbunrui"/></span>&nbsp;
                 <span class="dsp_kthouhou_<%= idx + 1 %>"><bean:write name="dataMdl" property="ntp040DspKthouhou"/></span>
                </div>

                </li>

                </logic:equal>

                <logic:equal name="mbhNtp040Form" property="cmd" value="edit">
                <br>
                <font size="-2">■<span class="text_bb1"><gsmsg:write key="ntp.3" />/<gsmsg:write key="ntp.31" /></span></font>

                 <div class="ktBunruiArea<%= idx + 1 %>">
                   <% String ntp040Ktbunrui = "ntp040Ktbunrui_" + (idx + 1); %>
                   <div data-role="navbar" align="center">
                   <ul>
                   <li>
                   <logic:notEmpty name="mbhNtp040Form" property="ntp040KtbunruiLavel">
                     <html:select property="ntp040Ktbunrui" value="<%= String.valueOf(ktbunruival) %>">
                        <html:optionsCollection name="mbhNtp040Form" property="ntp040KtbunruiLavel" value="value" label="label" />
                     </html:select>
                   </logic:notEmpty>
                    </li>
                    <li>
                   <logic:notEmpty name="mbhNtp040Form" property="ntp040KthouhouLavel">
                     <% String ntp040Kthouhou = "ntp040Kthouhou_" + (idx + 1); %>
                     <html:select property="ntp040Kthouhou" value="<%= String.valueOf(ktbouhouval) %>">
                        <html:optionsCollection name="mbhNtp040Form" property="ntp040KthouhouLavel" value="value" label="label" />
                     </html:select>
                   </logic:notEmpty>
                    </li>
                </ul>
                </div>
                 </div>

                 </logic:equal>

            </logic:equal>


            <logic:equal name="mbhNtp040Form" property="ntp040MikomidoUse" value="0">


                <logic:equal name="mbhNtp040Form" property="cmd" value="kakunin">

                <li>

                <font size="-2">■<span class="text_bb1"><gsmsg:write key="ntp.32" /></span><span class="text_r2"></span></font>

                <div class="mikomidoArea<%= idx + 1 %>">
                  <span class="text_base">
                    <span class="dsp_mikomido_<%= idx + 1 %>"><bean:write name="dataMdl" property="ntp040DspMikomido"/></span>％
                  </span>
                </div>

                </li>

                </logic:equal>

                <logic:equal name="mbhNtp040Form" property="cmd" value="edit">
                <br>
                <font size="-2">■<span class="text_bb1"><gsmsg:write key="ntp.32" /></span><span class="text_r2"></span></font>

                <div class="mikomidoArea<%= idx + 1 %>">
                <fieldset data-role="controlgroup" data-mini="true">
                  <span class="text_base">
                    <logic:equal name="dataMdl" property="mikomido" value="0">
                      <input name="ntp040Mikomido" value="0" checked="checked" id="ntp040Mikomido0_<%= idx + 1 %>" type="radio"><label style="font-size:10px;" for="ntp040Mikomido0_<%= idx + 1 %>">10%</label>
                    </logic:equal>
                    <logic:notEqual name="dataMdl" property="mikomido" value="0">
                      <input name="ntp040Mikomido" value="0" id="ntp040Mikomido0_<%= idx + 1 %>" type="radio"><label style="font-size:10px;" for="ntp040Mikomido0_<%= idx + 1 %>">10%</label>
                    </logic:notEqual>

                    <logic:equal name="dataMdl" property="mikomido" value="1">
                      <input name="ntp040Mikomido" value="1" checked="checked" id="ntp040Mikomido1_<%= idx + 1 %>" type="radio"><label for="ntp040Mikomido1_<%= idx + 1 %>">30%</label>
                    </logic:equal>
                    <logic:notEqual name="dataMdl" property="mikomido" value="1">
                      <input name="ntp040Mikomido" value="1" id="ntp040Mikomido1_<%= idx + 1 %>" type="radio"><label style="font-size:10px;" for="ntp040Mikomido1_<%= idx + 1 %>">30%</label>
                    </logic:notEqual>

                    <logic:equal name="dataMdl" property="mikomido" value="2">
                      <input name="ntp040Mikomido" value="2" checked="checked" id="ntp040Mikomido2_<%= idx + 1 %>" type="radio"><label for="ntp040Mikomido2_<%= idx + 1 %>">50%</label>
                    </logic:equal>
                    <logic:notEqual name="dataMdl" property="mikomido" value="2">
                      <input name="ntp040Mikomido" value="2" id="ntp040Mikomido2_<%= idx + 1 %>" type="radio"><label style="font-size:10px;" for="ntp040Mikomido2_<%= idx + 1 %>">50%</label>
                    </logic:notEqual>

                    <logic:equal name="dataMdl" property="mikomido" value="3">
                      <input name="ntp040Mikomido" value="3" checked="checked" id="ntp040Mikomido3_<%= idx + 1 %>" type="radio"><label for="ntp040Mikomido3_<%= idx + 1 %>">70%</label>
                    </logic:equal>
                    <logic:notEqual name="dataMdl" property="mikomido" value="3">
                      <input name="ntp040Mikomido" value="3" id="ntp040Mikomido3_<%= idx + 1 %>" type="radio"><label style="font-size:10px;" for="ntp040Mikomido3_<%= idx + 1 %>">70%</label>
                    </logic:notEqual>

                    <logic:equal name="dataMdl" property="mikomido" value="4">
                      <input name="ntp040Mikomido" value="4" checked="checked" id="ntp040Mikomido4_<%= idx + 1 %>" type="radio"><label for="ntp040Mikomido4_<%= idx + 1 %>">100%</label>
                    </logic:equal>
                    <logic:notEqual name="dataMdl" property="mikomido" value="4">
                      <input name="ntp040Mikomido" value="4" id="ntp040Mikomido4_<%= idx + 1 %>" type="radio"><label style="font-size:10px;" for="ntp040Mikomido4_<%= idx + 1 %>">100%</label>
                    </logic:notEqual>

                  </span>
                  </fieldset>
                </div>

                </logic:equal>

            </logic:equal>

<!--       添付ファイル -->
            <logic:equal name="mbhNtp040Form" property="cmd" value="edit">
                <div class="font_small">■<gsmsg:write key="cmn.attach.file"/></div>
                <span id="tmp_file_area">
                  <logic:notEmpty name="mbhNtp040Form" property="ntp040FileLabelList">
                    <logic:iterate id="file" name="mbhNtp040Form" property="ntp040FileLabelList" indexId="idx" scope="request">
                       <div style="width:100%;" id="file_<bean:write name="file" property="value" />">
                        <div class="del_file_txt"><bean:write name="file" property="label" /></div>
                        <div id="<bean:write name="file" property="value" />" class="del_file_div">&nbsp;&nbsp;</div>
                      </div>
                      <div style="clear:both;padding-top:10px;"></div>
                    </logic:iterate>
                  </logic:notEmpty>
                </span>

                <div align="center" style="clear:both;">
                  <div id="tmp_button_area" style="display:block;"><input type="button" id="tmp_button" value="添付" data-inline="true" data-role="button" data-icon="grid" data-iconpos="left"/></div>
                </div>
                <br>
            </logic:equal>


            <logic:equal name="mbhNtp040Form" property="ntp040TmpFileUse" value="0">





            <logic:equal name="mbhNtp040Form" property="ntp040NextActionUse" value="0">


              <logic:equal name="mbhNtp040Form" property="cmd" value="kakunin">

              <li>
              <font size="-2">■<span class="text_bb1"><gsmsg:write key="ntp.96" /><a id="nextAction" name="nextAction"></a></span></font>

                <div class="nextActionArea<%= idx + 1 %>">

                  <span id="actionSelDateArea_<%= idx + 1 %>" style="color:#000000;font-size:12px;font-weight:bold;">
                    <logic:equal name="dataMdl" property="actDateKbn" value="1">
                      &nbsp;日付：
                      <span class="dsp_actionyear_<%= idx + 1 %>"><bean:write name="dataMdl" property="actionYear"/></span>年
                      <span class="dsp_actionmonth_<%= idx + 1 %>"><bean:write name="dataMdl" property="actionMonth"/></span>月
                      <span class="dsp_actionday_<%= idx + 1 %>"><bean:write name="dataMdl" property="actionDay"/></span>日
                      <br>
                    </logic:equal>
                  </span>

                  <span class="dsp_nextaction_<%= idx + 1 %>"><bean:write name="dataMdl" property="ntp040DspActionStr" filter="false"/></span>
                </div>

                </li>

                </logic:equal>

                <logic:equal name="mbhNtp040Form" property="cmd" value="edit">
                <br>
                <font size="-2">■<span class="text_bb1"><gsmsg:write key="ntp.96" /><a id="nextAction" name="nextAction"></a></span></font>

                <div class="nextActionArea<%= idx + 1 %>">

                  <logic:equal name="mbhNtp040Form" property="ntp040ActDateKbn" value="0">
                  <div data-role="controlgroup" data-type="horizontal" align="center" style="font-size:10px;">
                  <input type="submit" name="ntp040actDayAdd" class="" value="<gsmsg:write key="ntp.34" />する" data-inline="true"/>
                  </div>
                  <div id="nxtActDateArea">
                  </logic:equal>

                  <logic:equal name="mbhNtp040Form" property="ntp040ActDateKbn" value="1">
                  <div data-role="controlgroup" data-type="horizontal" align="center" style="font-size:10px;">
                  <input type="submit" name="ntp040actDayNotAdd" class="" value="<gsmsg:write key="ntp.34" />しない" data-inline="true"/>
                  </div>
                  <div id="nxtActDateArea">


                     <% selActionYearIdStr  = "selActionYear"  + String.valueOf(idx + 1); %>
                     <% selActionMonthIdStr = "selActionMonth" + String.valueOf(idx + 1); %>
                     <% selActionDayIdStr   = "selActionDay"   + String.valueOf(idx + 1); %>

                     <bean:define id="dataActionYear" name="dataMdl" property="actionYear" type="java.lang.Integer"/>
                     <bean:define id="dataActionMonth" name="dataMdl" property="actionMonth" type="java.lang.Integer"/>
                     <bean:define id="dataActionDay" name="dataMdl" property="actionDay" type="java.lang.Integer"/>

                     <logic:equal name="dataMdl" property="actDateKbn" value="0">
                       <bean:define id="actionInitYear" name="mbhNtp040Form" property="ntp040InitYear" type="java.lang.String"/>
                       <bean:define id="actionInitMonth" name="mbhNtp040Form" property="ntp040InitMonth" type="java.lang.String"/>
                       <bean:define id="actionInitDay" name="mbhNtp040Form" property="ntp040InitDay" type="java.lang.String"/>
                       <% dataActionYear  =  Integer.parseInt(actionInitYear); %>
                       <% dataActionMonth =  Integer.parseInt(actionInitMonth); %>
                       <% dataActionDay   =  Integer.parseInt(actionInitDay); %>
                     </logic:equal>

<%--
                     <select name="ntp040NxtActYear" id="<%= selActionYearIdStr.toString() %>">
                       <logic:iterate id="yearLv" name="mbhNtp040Form" property="ntp040YearLavel">
                         <logic:equal name="yearLv" property="value" value="<%= dataActionYear.toString() %>">
                           <option value="<bean:write name="yearLv" property="value" />" selected="selected"><bean:write name="yearLv" property="label" /></option>
                         </logic:equal>
                         <logic:notEqual name="yearLv" property="value" value="<%= dataActionYear.toString() %>">
                           <option value="<bean:write name="yearLv" property="value" />"><bean:write name="yearLv" property="label" /></option>
                         </logic:notEqual>
                       </logic:iterate>
                     </select>

                     <select name="ntp040NxtActMonth" id="<%= selActionMonthIdStr %>">
                       <logic:iterate id="monthLv" name="mbhNtp040Form" property="ntp040MonthLavel">
                         <logic:equal name="monthLv" property="value" value="<%= dataActionMonth.toString() %>">
                           <option value="<bean:write name="monthLv" property="value" />" selected="selected"><bean:write name="monthLv" property="label" /></option>
                         </logic:equal>
                         <logic:notEqual name="monthLv" property="value" value="<%= dataActionMonth.toString() %>">
                           <option value="<bean:write name="monthLv" property="value" />"><bean:write name="monthLv" property="label" /></option>
                         </logic:notEqual>
                       </logic:iterate>
                     </select>

                     <select name="ntp040NxtActDay" id="<%= selActionDayIdStr %>">
                       <logic:iterate id="dayLv" name="mbhNtp040Form" property="ntp040DayLavel">
                         <logic:equal name="dayLv" property="value" value="<%= dataActionDay.toString() %>">
                           <option value="<bean:write name="dayLv" property="value" />" selected="selected"><bean:write name="dayLv" property="label" /></option>
                         </logic:equal>
                         <logic:notEqual name="dayLv" property="value" value="<%= dataActionDay.toString() %>">
                           <option value="<bean:write name="dayLv" property="value" />"><bean:write name="dayLv" property="label" /></option>
                         </logic:notEqual>
                       </logic:iterate>
                     </select>
--%>
                   <jquery:jqtext id="date2" name="mbhNtp040Form" property="ntp040ActionDate" readonly="true"/>

                  </logic:equal>

                   </div>

                    <br>

                  </div>

                  <textarea id="actionstr_<%= idx + 1 %>" name="ntp040NextAction" cols="50" rows="2" onkeyup="showLengthStr(value, 1000, 'actionlength<%= idx + 1 %>');"><bean:write name="dataMdl" property="actionStr" /></textarea>


                  </logic:equal>

            </logic:equal>

           <logic:equal name="mbhNtp040Form" property="cmd" value="kakunin">

            <li>
            <font size="-2">■<span class="text_bb1"><gsmsg:write key="ntp.22" />!</span></font><br>

              <span id="goodBtnArea_<bean:write name="dataMdl" property="ntp040NtpSid" />">
                <logic:equal name="dataMdl" property="ntp040GoodFlg" value="0">
                  <input name="ntp040good" data-icon="plus" data-inline="true" id="<bean:write name="dataMdl" property="ntp040NtpSid" />" style="" class="" value="<gsmsg:write key="ntp.22" />!する" type="submit">
                </logic:equal>
                <logic:notEqual name="dataMdl" property="ntp040GoodFlg" value="0">
                  <span class="text_already_good">いいね!しています</span>
                </logic:notEqual>
              </span>
              <span class="text_good" id="<bean:write name="dataMdl" property="ntp040NtpSid" />">&nbsp;&nbsp;<span id="goodCntArea_<bean:write name="dataMdl" property="ntp040NtpSid" />"><bean:write name="dataMdl" property="ntp040GoodCnt" />件</span>&nbsp;&nbsp;
              </span>
            </li>


            </logic:equal>




              <logic:equal name="mbhNtp040Form" property="cmd" value="kakunin">

              <logic:notEmpty name="dataMdl" property="ntp040FileList">
              <li>

              <div class="font_small" align="center"><span class="text_bb1"><font size="-2"><gsmsg:write key="cmn.attached" /></font></span></div>

              </li>

                <logic:iterate id="tempMdl" name="dataMdl" property="ntp040FileList">
                <li>
                  <a href="../mobile/sp_ntp040.do?mobileType=1&CMD=fileDownload&ntp010NipSid=<bean:write name="dataMdl" property="ntp040NtpSid" />&ntp040BinSid=<bean:write name="tempMdl" property="binSid"/>"><span class="text_link_min"><bean:write name="tempMdl" property="binFileName"/>
                  <bean:write name="tempMdl" property="binFileSizeDsp" /></span>
                  </a>
                </li>
                </logic:iterate>
              </logic:notEmpty>



                </logic:equal>

                <%--
                <logic:equal name="mbhNtp040Form" property="cmd" value="edit">

                <div class="tempFileArea<%= idx + 1 %>">
                  <select id="ntp040selFile<%= idx + 1 %>" name="ntp040selectFiles<%= idx + 1 %>" multiple="multiple" size="3" class="select01">

                  </select>
                  <input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTempPlus('ntp040selectFiles<%= idx + 1 %>', 'nippou', '0', '0', 'row<%= idx + 1 %>');">
                  &nbsp;
                  <input type="button" class="btn_delete" name="tempDelBtn" id="<%= idx + 1 %>" value="<gsmsg:write key="cmn.delete" />">
                </div>

                </logic:equal>
                --%>

            </logic:equal>







            <logic:equal name="mbhNtp040Form" property="cmd" value="kakunin">

           </ul>

           <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">


            <li data-role="list-divider" style="background:#ffffff;">
            <div class="font_small" align="center"><span class="text_bb1">コメント</span></div>
            </li>

            <logic:notEmpty name="dataMdl" property="ntp040CommentList">




                  <logic:iterate id="npcMdl" name="dataMdl" property="ntp040CommentList">

                  <li style="padding-right:3px;">

                    <bean:define id="usrInfMdl" name="npcMdl" property="ntp040UsrInfMdl"/>
                    <bean:define id="ntpCmtMdl" name="npcMdl" property="ntp040CommentMdl"/>

                    <%--

                          <logic:equal name="usrInfMdl" property="binSid" value="0">
                            <img  class="comment_Img" src="../user/images/photo.gif" name="userImage" alt="<gsmsg:write key="cmn.photo" />" border="1" width="50px" />
                          </logic:equal>

                          <logic:notEqual name="usrInfMdl" property="binSid" value="0">
                            <logic:equal name="usrInfMdl" property="usiPictKf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>">
                              <div align="center" class="photo_hikokai2"><span style="color:#fc2929;"><gsmsg:write key="cmn.private" /></span></div>
                            </logic:equal>
                            <logic:notEqual name="usrInfMdl" property="usiPictKf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>">
                              <img class="comment_Img" src="../nippou/ntp040.do?CMD=getPhotoFile&photoFileSid=<bean:write name="usrInfMdl" property="binSid" />" alt="<gsmsg:write key="cmn.photo" />" width="50px" />
                            </logic:notEqual>
                          </logic:notEqual>

                     --%>


                          <span style="font-size:12px;color:#333333;"><b><bean:write name="usrInfMdl" property="usiSei" />&nbsp;<bean:write name="usrInfMdl" property="usiMei" /></b></span>
                          &nbsp;<span style="font-size:12px;color:#333333;"><bean:write name="npcMdl" property="ntp040CommentDate" filter="false"/></span>
                          <br><span style="font-size:13px;color:#333333;"><bean:write name="ntpCmtMdl" property="npcComment" filter="false" /></span>
                          <logic:equal name="npcMdl" property="ntp040CommentDelFlg" value="1">
                          <br><font size="-2"><input name="<%= jp.groupsession.v3.mbh.ntp040.MbhNtp040Form.PARAM_COMMENT_DEL %><bean:write name="ntpCmtMdl" property="npcSid" />" style="" class="" value="<gsmsg:write key="cmn.delete" />" type="submit"  type="submit" data-icon="delete" data-inline="true"></font>
                          </logic:equal>
                  </li>

                  </logic:iterate>







            </logic:notEmpty>



            <li>

              <logic:notEmpty name="mbhNtp040Form" property="ntp040UsrInfMdl">
              <bean:define id="usrInf" name="mbhNtp040Form" property="ntp040UsrInfMdl"/>




                <%--

                    <logic:equal name="usrInf" property="binSid" value="0">
                      <img  class="comment_Img" src="../user/images/photo.gif" name="userImage" alt="<gsmsg:write key="cmn.photo" />" border="1" width="50px" />
                    </logic:equal>

                    <logic:notEqual name="usrInf" property="binSid" value="0">
                      <logic:equal name="usrInf" property="usiPictKf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>">
                        <div align="center" class="photo_hikokai2"><span style="color:#fc2929;"><gsmsg:write key="cmn.private" /></span></div>
                      </logic:equal>
                      <logic:notEqual name="usrInf" property="usiPictKf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>">
                        <img class="comment_Img" src="../nippou/ntp040.do?CMD=getPhotoFile&photoFileSid=<bean:write name="usrInf" property="binSid" />" alt="<gsmsg:write key="cmn.photo" />" width="50px" />
                      </logic:notEqual>
                    </logic:notEqual>

                 --%>

                    <div class="textfield">
                      <textarea name="ntp040Comment" cols="50" rows="3" style="height:50px;" id="field_id<%= idx + 1 %>"></textarea>
                    </div>
                    <font size="-2">
                    <input type="submit" name="ntp040AddComment" value="投稿" type="submit" data-icon="plus" data-inline="true"/>
                    </font>

                </li>

              </ul>

              </logic:notEmpty>



           </logic:equal>


        </logic:iterate>

        <input type="hidden" id="editLastRowNum" value="<%= lastRowNumber %>" />

      </logic:notEmpty>