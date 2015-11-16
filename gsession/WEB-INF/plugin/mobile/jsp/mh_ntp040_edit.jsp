<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

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

            <br>
            ■<span class="text_bb1">タイトル</span><span class="titleArea<%= idx + 1 %> text_r2" style="display:none;">※</span>


              <logic:equal name="mbhNtp040Form" property="cmd" value="kakunin">
              <div class="titleArea<%= idx + 1 %>">
                 <span class="dsp_title_<%= idx + 1 %>"><bean:write name="dataMdl" property="title" /></span>
              </div>
              </logic:equal>

              <logic:equal name="mbhNtp040Form" property="cmd" value="edit">
              <div class="titleArea<%= idx + 1 %>">

                <input name="ntp040Title" maxlength="100" size="40" value="<bean:write name="dataMdl" property="title" />" id="ntpTitleTextBox" class="text_base" type="text">

                <br>

                <logic:equal name="dataMdl" property="bgcolor" value="1">
                  <span class="sc_block_color_1"><input name="ntp040Bgcolor" value="1" checked="checked" id="bg_color1_<%= idx + 1 %>" type="radio"></span>
                  <label for="bg_color1_<%= idx + 1 %>" class="text_base"></label><%= colormsg1 %>
                </logic:equal>
                <logic:notEqual name="dataMdl" property="bgcolor" value="1">
                  <span class="sc_block_color_1"><input name="ntp040Bgcolor" value="1" id="bg_color1_<%= idx + 1 %>" type="radio"></span>
                  <label for="bg_color1_<%= idx + 1 %>" class="text_base"></label><%= colormsg1 %>
                </logic:notEqual>

                <logic:equal name="dataMdl" property="bgcolor" value="2">
                  <span class="sc_block_color_2"><input name="ntp040Bgcolor" value="2" checked="checked" id="bg_color2_<%= idx + 1 %>" type="radio"></span>
                  <label for="bg_color2_<%= idx + 1 %>" class="text_base"></label><%= colormsg2 %>
                </logic:equal>
                <logic:notEqual name="dataMdl" property="bgcolor" value="2">
                  <span class="sc_block_color_2"><input name="ntp040Bgcolor" value="2" id="bg_color2_<%= idx + 1 %>" type="radio"></span>
                  <label for="bg_color2_<%= idx + 1 %>" class="text_base"></label><%= colormsg2 %>
                </logic:notEqual>

                <logic:equal name="dataMdl" property="bgcolor" value="3">
                  <span class="sc_block_color_3"><input name="ntp040Bgcolor" value="3" checked="checked" id="bg_color3_<%= idx + 1 %>" type="radio"></span>
                  <label for="bg_color3_<%= idx + 1 %>" class="text_base"></label><%= colormsg3 %>
                </logic:equal>
                <logic:notEqual name="dataMdl" property="bgcolor" value="3">
                  <span class="sc_block_color_3"><input name="ntp040Bgcolor" value="3" id="bg_color3_<%= idx + 1 %>" type="radio"></span>
                  <label for="bg_color3_<%= idx + 1 %>" class="text_base"></label><%= colormsg3 %>
                </logic:notEqual>

                <logic:equal name="dataMdl" property="bgcolor" value="4">
                  <span class="sc_block_color_4"><input name="ntp040Bgcolor" value="4" checked="checked" id="bg_color4_<%= idx + 1 %>" type="radio"></span>
                  <label for="bg_color4_<%= idx + 1 %>" class="text_base"></label><%= colormsg4 %>
                </logic:equal>
                <logic:notEqual name="dataMdl" property="bgcolor" value="4">
                  <span class="sc_block_color_4"><input name="ntp040Bgcolor" value="4" id="bg_color4_<%= idx + 1 %>" type="radio"></span>
                  <label for="bg_color4_<%= idx + 1 %>" class="text_base"></label><%= colormsg4 %>
                </logic:notEqual>

                <logic:equal name="dataMdl" property="bgcolor" value="5">
                  <span class="sc_block_color_5"><input name="ntp040Bgcolor" value="5" checked="checked" id="bg_color5_<%= idx + 1 %>" type="radio"></span>
                  <label for="bg_color5_<%= idx + 1 %>" class="text_base"></label><%= colormsg5 %>
                </logic:equal>
                <logic:notEqual name="dataMdl" property="bgcolor" value="5">
                  <span class="sc_block_color_5"><input name="ntp040Bgcolor" value="5" id="bg_color5_<%= idx + 1 %>" type="radio"></span>
                  <label for="bg_color5_<%= idx + 1 %>" class="text_base"></label><%= colormsg5 %>
                </logic:notEqual>

              </div>
              </logic:equal>

              <logic:equal name="mbhNtp040Form" property="cmd" value="edit">


            <br>
            ■<span class="text_bb1"><gsmsg:write key="ntp.35" /></span><span class="ntpTimeArea<%= idx + 1 %> text_r2">※</span>
            <br>

             <% selYearIdStr  = "selYear"  + String.valueOf(idx + 1); %>
             <% selMonthIdStr = "selMonth" + String.valueOf(idx + 1); %>
             <% selDayIdStr   = "selDay"   + String.valueOf(idx + 1); %>

             <bean:define id="dataYear" name="dataMdl" property="ntpYear" type="java.lang.Integer"/>
             <bean:define id="dataMonth" name="dataMdl" property="ntpMonth" type="java.lang.Integer"/>
             <bean:define id="dataDay" name="dataMdl" property="ntpDay" type="java.lang.Integer"/>

             <select name="ntp040HoukokuYear" id="<%= selYearIdStr.toString() %>">
               <logic:iterate id="yearLv" name="mbhNtp040Form" property="ntp040YearLavel">
                 <logic:equal name="yearLv" property="value" value="<%= dataYear.toString() %>">
                   <option value="<bean:write name="yearLv" property="value" />" selected="selected"><bean:write name="yearLv" property="label" /></option>
                 </logic:equal>
                 <logic:notEqual name="yearLv" property="value" value="<%= dataYear.toString() %>">
                   <option value="<bean:write name="yearLv" property="value" />"><bean:write name="yearLv" property="label" /></option>
                 </logic:notEqual>
               </logic:iterate>
             </select>

             <select name="ntp040HoukokuMonth" id="<%= selMonthIdStr %>">
               <logic:iterate id="monthLv" name="mbhNtp040Form" property="ntp040MonthLavel">
                 <logic:equal name="monthLv" property="value" value="<%= dataMonth.toString() %>">
                   <option value="<bean:write name="monthLv" property="value" />" selected="selected"><bean:write name="monthLv" property="label" /></option>
                 </logic:equal>
                 <logic:notEqual name="monthLv" property="value" value="<%= dataMonth.toString() %>">
                   <option value="<bean:write name="monthLv" property="value" />"><bean:write name="monthLv" property="label" /></option>
                 </logic:notEqual>
               </logic:iterate>
             </select>

             <select name="ntp040HoukokuDay" id="<%= selDayIdStr %>">
               <logic:iterate id="dayLv" name="mbhNtp040Form" property="ntp040DayLavel">
                 <logic:equal name="dayLv" property="value" value="<%= dataDay.toString() %>">
                   <option value="<bean:write name="dayLv" property="value" />" selected="selected"><bean:write name="dayLv" property="label" /></option>
                 </logic:equal>
                 <logic:notEqual name="dayLv" property="value" value="<%= dataDay.toString() %>">
                   <option value="<bean:write name="dayLv" property="value" />"><bean:write name="dayLv" property="label" /></option>
                 </logic:notEqual>
               </logic:iterate>
             </select>

             <%--
             <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#<%= selYearIdStr %>')[0], $('#<%= selMonthIdStr %>')[0], $('#<%= selDayIdStr %>')[0], 1)">
             <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#<%= selYearIdStr %>')[0], $('#<%= selMonthIdStr %>')[0], $('#<%= selDayIdStr %>')[0], 2)">
             <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#<%= selYearIdStr %>')[0], $('#<%= selMonthIdStr %>')[0], $('#<%= selDayIdStr %>')[0], 3)">
             <input type="button" value="Cal" onclick="wrtCalendarByBtn(this.form.selDay<%= idx + 1 %>, this.form.selMonth<%= idx + 1 %>, this.form.selYear<%= idx + 1 %>, 'ntp040FrCalBtn<%= idx + 1 %>')" class="calendar_btn" id="ntp040FrCalBtn<%= idx + 1 %>">
             --%>
             <br>
           </logic:equal>

           <br>

            ■<span class="text_bb1">時間</span>

               <logic:equal name="mbhNtp040Form" property="cmd" value="edit">

               <span class="ntpTimeArea<%= idx + 1 %> text_r2">※</span>

               </logic:equal>


             <logic:equal name="mbhNtp040Form" property="cmd" value="kakunin">


              <div class="ntpTimeArea<%= idx + 1 %>">

               <span class="dsp_frhour_<%= idx + 1 %>"><bean:write name="dataMdl" property="ntp040DspFrHour" /></span>
               <gsmsg:write key="cmn.hour.input" />
               <span class="dsp_frminute_<%= idx + 1 %>"><bean:write name="dataMdl" property="ntp040DspFrMinute"/></span>
               <gsmsg:write key="cmn.minute.input" />
               -
               <span class="dsp_tohour_<%= idx + 1 %>"><bean:write name="dataMdl" property="ntp040DspToHour"/></span>
               <gsmsg:write key="cmn.hour.input" />
               <span class="dsp_tominute_<%= idx + 1 %>"><bean:write name="dataMdl" property="ntp040DspToMinute"/></span>
               <gsmsg:write key="cmn.minute.input" />
                 &nbsp;&nbsp;&nbsp;&nbsp;<span id="betWeenDays" class="text_base"></span>

              </div>

              </logic:equal>

              <logic:equal name="mbhNtp040Form" property="cmd" value="edit">

              <div class="ntpTimeArea<%= idx + 1 %>">

                 <% String ntp040FrHour = "ntp040FrHour_" + (idx + 1); %>
                 <html:select property="ntp040FrHour" value="<%= String.valueOf(datafrhourval) %>" onchange="setToDay();">
                    <html:optionsCollection name="mbhNtp040Form" property="ntp040HourLavel" value="value" label="label" />
                 </html:select>
                 <gsmsg:write key="cmn.hour.input" />
                 <% String ntp040FrMin = "ntp040FrMin_" + (idx + 1); %>
                 <html:select property="ntp040FrMin" value="<%= String.valueOf(datafrminval) %>" onchange="setToDay();">
                    <html:optionsCollection name="mbhNtp040Form" property="ntp040MinuteLavel" value="value" label="label" />
                 </html:select>
                 <gsmsg:write key="cmn.minute.input" />
                 -
                 <% String ntp040ToHour = "ntp040ToHour_" + (idx + 1); %>
             <br><html:select property="ntp040ToHour" value="<%= String.valueOf(datatohourval) %>" onchange="setToDay();">
                    <html:optionsCollection name="mbhNtp040Form" property="ntp040HourLavel" value="value" label="label" />
                 </html:select>
                 <gsmsg:write key="cmn.hour.input" />
                 <% String ntp040ToMin = "ntp040ToMin_" + (idx + 1); %>
                 <html:select property="ntp040ToMin" value="<%= String.valueOf(datatominval) %>" onchange="setToDay();">
                    <html:optionsCollection name="mbhNtp040Form" property="ntp040MinuteLavel" value="value" label="label" />
                 </html:select>
                 <gsmsg:write key="cmn.minute.input" />
                   &nbsp;&nbsp;&nbsp;&nbsp;<span id="betWeenDays" class="text_base"></span>

              </div>


              </logic:equal>

            <br>

            <logic:equal name="mbhNtp040Form" property="ntp040AnkenCompanyUse" value="0">
              ■<span class="text_bb1"><gsmsg:write key="ntp.11" /></span><span class="text_r2"></span>

              <logic:equal name="mbhNtp040Form" property="cmd" value="kakunin">

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

                </logic:equal>

                <logic:equal name="mbhNtp040Form" property="cmd" value="edit">

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
                      <font size="-2"><input type="submit" name="ntp040ankendel" class="" value="<gsmsg:write key="cmn.delete" />" /></font>
                    </logic:notEmpty>
                  </div>

               </logic:equal>




              ■<span class="text_bb1"><gsmsg:write key="ntp.15" />・<gsmsg:write key="ntp.16" /></span><span class="text_r2"></span>


                <logic:equal name="mbhNtp040Form" property="cmd" value="kakunin">

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

               </logic:equal>


               <logic:equal name="mbhNtp040Form" property="cmd" value="edit">

               <div class="kigyouDataArea<%= idx + 1 %>">

                  <input type="submit" class="" value="<gsmsg:write key="addressbook" />" name="ntp040adrbook" />
                  <input type="submit" class="" value="<gsmsg:write key="ntp.17" />" name="ntp040rireki" /><br>
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
                        <font size="-2"><input type="submit" name="ntp040compdel" class="" value="<gsmsg:write key="cmn.delete" />" /></font>
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
              <span class="text_bb1"><gsmsg:write key="ntp.11" /></span><span class="text_r2"></span>


                <logic:equal name="mbhNtp040Form" property="cmd" value="kakunin">

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

                </logic:equal>

                <logic:equal name="mbhNtp040Form" property="cmd" value="edit">

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
                      <font size="-2"><input type="submit" name="ntp040ankendel" class="" value="<gsmsg:write key="cmn.delete" />" /></font>
                    </logic:notEmpty>
                  </div>

                </div>

                </logic:equal>

            </logic:equal>


            <logic:equal name="mbhNtp040Form" property="ntp040AnkenCompanyUse" value="2">
              <span class="text_bb1"><gsmsg:write key="ntp.15" />・<gsmsg:write key="ntp.16" /></span><span class="text_r2"></span>


                <logic:equal name="mbhNtp040Form" property="cmd" value="kakunin">

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

                 </logic:equal>

                 <logic:equal name="mbhNtp040Form" property="cmd" value="edit">


               <div class="kigyouDataArea<%= idx + 1 %>">

                  <input type="submit" class="" value="<gsmsg:write key="addressbook" />" name="ntp040adrbook" />
                  <input type="submit" class="" value="<gsmsg:write key="ntp.17" />" name="ntp040rireki" /><br>

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
                        <font size="-2"><input type="submit" name="ntp040compdel" class="" value="<gsmsg:write key="cmn.delete" />" /></font>
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

            <br>

            ■<span class="text_bb1">内　容<a id="naiyou" name="naiyou"></a></span>

            <logic:equal name="mbhNtp040Form" property="cmd" value="kakunin">

              <div class="naiyouArea<%= idx + 1 %>">
                <span class="dsp_naiyou_<%= idx + 1 %>"><bean:write name="dataMdl" property="ntp040DspValueStr" filter="false"/></span>
              </div>

            </logic:equal>

            <logic:equal name="mbhNtp040Form" property="cmd" value="edit">

              <div class="naiyouArea<%= idx + 1 %>">
                <textarea id="inputstr_<%= idx + 1 %>" name="ntp040Value" cols="50" rows="5" onkeyup="showLengthStr(value, 1000, 'inputlength<%= idx + 1 %>');"><bean:write name="dataMdl" property="valueStr" /></textarea>
                <br>
              </div>

            </logic:equal>


            <logic:equal name="mbhNtp040Form" property="ntp040KtBriHhuUse" value="0">

              <br>

              ■<span class="text_bb1"><gsmsg:write key="ntp.3" />/<gsmsg:write key="ntp.31" /></span>


                <bean:define id="ktbunruival" name="dataMdl" property="ktbunruiSid"/>
                <bean:define id="ktbouhouval" name="dataMdl" property="kthouhouSid"/>

                <logic:equal name="mbhNtp040Form" property="cmd" value="kakunin">

                <div class="ktBunruiArea<%= idx + 1 %>">
                 <span class="dsp_ktbunrui_<%= idx + 1 %>"><bean:write name="dataMdl" property="ntp040DspKtbunrui"/></span>&nbsp;
                 <span class="dsp_kthouhou_<%= idx + 1 %>"><bean:write name="dataMdl" property="ntp040DspKthouhou"/></span>
                </div>

                </logic:equal>

                <logic:equal name="mbhNtp040Form" property="cmd" value="edit">

                 <div class="ktBunruiArea<%= idx + 1 %>">
                   <% String ntp040Ktbunrui = "ntp040Ktbunrui_" + (idx + 1); %>
                   <logic:notEmpty name="mbhNtp040Form" property="ntp040KtbunruiLavel">
                     <html:select property="ntp040Ktbunrui" value="<%= String.valueOf(ktbunruival) %>">
                        <html:optionsCollection name="mbhNtp040Form" property="ntp040KtbunruiLavel" value="value" label="label" />
                     </html:select>
                   </logic:notEmpty>

                   <logic:notEmpty name="mbhNtp040Form" property="ntp040KthouhouLavel">
                     <% String ntp040Kthouhou = "ntp040Kthouhou_" + (idx + 1); %>
                     <html:select property="ntp040Kthouhou" value="<%= String.valueOf(ktbouhouval) %>">
                        <html:optionsCollection name="mbhNtp040Form" property="ntp040KthouhouLavel" value="value" label="label" />
                     </html:select>
                   </logic:notEmpty>
                 </div>

                 </logic:equal>

            </logic:equal>


            <logic:equal name="mbhNtp040Form" property="ntp040MikomidoUse" value="0">

              <br>

              ■<span class="text_bb1"><gsmsg:write key="ntp.32" /></span><span class="text_r2"></span>


                <logic:equal name="mbhNtp040Form" property="cmd" value="kakunin">

                <div class="mikomidoArea<%= idx + 1 %>">
                  <span class="text_base">
                    <span class="dsp_mikomido_<%= idx + 1 %>"><bean:write name="dataMdl" property="ntp040DspMikomido"/></span>％
                  </span>
                </div>

                </logic:equal>

                <logic:equal name="mbhNtp040Form" property="cmd" value="edit">

                <div class="mikomidoArea<%= idx + 1 %>">
                  <span class="text_base">
                    <logic:equal name="dataMdl" property="mikomido" value="0">
                      <input name="ntp040Mikomido" value="0" checked="checked" id="ntp040Mikomido0_<%= idx + 1 %>" type="radio"><label for="ntp040Mikomido0_<%= idx + 1 %>">10%</label>
                    </logic:equal>
                    <logic:notEqual name="dataMdl" property="mikomido" value="0">
                      <input name="ntp040Mikomido" value="0" id="ntp040Mikomido0_<%= idx + 1 %>" type="radio"><label for="ntp040Mikomido0_<%= idx + 1 %>">10%</label>
                    </logic:notEqual>

                    <logic:equal name="dataMdl" property="mikomido" value="1">
                      <input name="ntp040Mikomido" value="1" checked="checked" id="ntp040Mikomido1_<%= idx + 1 %>" type="radio"><label for="ntp040Mikomido1_<%= idx + 1 %>">30%</label>
                    </logic:equal>
                    <logic:notEqual name="dataMdl" property="mikomido" value="1">
                      <input name="ntp040Mikomido" value="1" id="ntp040Mikomido1_<%= idx + 1 %>" type="radio"><label for="ntp040Mikomido1_<%= idx + 1 %>">30%</label>
                    </logic:notEqual>

                    <logic:equal name="dataMdl" property="mikomido" value="2">
                      <input name="ntp040Mikomido" value="2" checked="checked" id="ntp040Mikomido2_<%= idx + 1 %>" type="radio"><label for="ntp040Mikomido2_<%= idx + 1 %>">50%</label>
                    </logic:equal>
                    <logic:notEqual name="dataMdl" property="mikomido" value="2">
                      <input name="ntp040Mikomido" value="2" id="ntp040Mikomido2_<%= idx + 1 %>" type="radio"><label for="ntp040Mikomido2_<%= idx + 1 %>">50%</label>
                    </logic:notEqual>

                    <logic:equal name="dataMdl" property="mikomido" value="3">
                      <input name="ntp040Mikomido" value="3" checked="checked" id="ntp040Mikomido3_<%= idx + 1 %>" type="radio"><label for="ntp040Mikomido3_<%= idx + 1 %>">70%</label>
                    </logic:equal>
                    <logic:notEqual name="dataMdl" property="mikomido" value="3">
                      <input name="ntp040Mikomido" value="3" id="ntp040Mikomido3_<%= idx + 1 %>" type="radio"><label for="ntp040Mikomido3_<%= idx + 1 %>">70%</label>
                    </logic:notEqual>

                    <logic:equal name="dataMdl" property="mikomido" value="4">
                      <input name="ntp040Mikomido" value="4" checked="checked" id="ntp040Mikomido4_<%= idx + 1 %>" type="radio"><label for="ntp040Mikomido4_<%= idx + 1 %>">100%</label>
                    </logic:equal>
                    <logic:notEqual name="dataMdl" property="mikomido" value="4">
                      <input name="ntp040Mikomido" value="4" id="ntp040Mikomido4_<%= idx + 1 %>" type="radio"><label for="ntp040Mikomido4_<%= idx + 1 %>">100%</label>
                    </logic:notEqual>

                  </span>
                </div>

                </logic:equal>

            </logic:equal>


            <logic:equal name="mbhNtp040Form" property="ntp040TmpFileUse" value="0">



              <logic:equal name="mbhNtp040Form" property="cmd" value="kakunin">
              <br>
              ■<span class="text_bb1"><gsmsg:write key="cmn.attached" /><a id="naiyou" name="naiyou"></a></span>

                <div class="tempFileArea<%= idx + 1 %> dsp_tmp_file_area_<%= idx + 1 %>">
                  <logic:notEmpty name="dataMdl" property="ntp040FileList">
                    <logic:iterate id="tempMdl" name="dataMdl" property="ntp040FileList">
                      <span class="text_link_min"><bean:write name="tempMdl" property="binFileName"/><bean:write name="tempMdl" property="binFileSizeDsp" /></span><br>
                    </logic:iterate>
                  </logic:notEmpty>
                </div>

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

            <logic:equal name="mbhNtp040Form" property="ntp040NextActionUse" value="0">

              <br>

              ■<span class="text_bb1"><gsmsg:write key="ntp.96" /><a id="nextAction" name="nextAction"></a></span>


              <logic:equal name="mbhNtp040Form" property="cmd" value="kakunin">

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

                </logic:equal>

                <logic:equal name="mbhNtp040Form" property="cmd" value="edit">

                <div class="nextActionArea<%= idx + 1 %>">


                   <%--
                   <span style="color:#000000;font-size:12px;font-weight:bold;">&nbsp;<gsmsg:write key="ntp.34" />：</span>


                   <logic:equal name="dataMdl" property="actDateKbn" value="1">
                     <input name="ntp040ActDateKbn_<%= idx + 1 %>" value="1" checked="checked" onchange="toggleActionArea('nxtActDateArea_<%= idx + 1 %>');" id="actDate1_<%= idx + 1 %>" type="radio">
                   </logic:equal>
                   <logic:notEqual name="dataMdl" property="actDateKbn" value="1">
                     <input name="ntp040ActDateKbn_<%= idx + 1 %>" value="1" onchange="toggleActionArea('nxtActDateArea_<%= idx + 1 %>');" id="actDate1_<%= idx + 1 %>" type="radio">
                   </logic:notEqual>

                   <label for="actDate1_<%= idx + 1 %>" class="text_base" style="color:#000000;font-size:12px;">する</label>

                   <logic:equal name="dataMdl" property="actDateKbn" value="0">
                     <input name="ntp040ActDateKbn_<%= idx + 1 %>" value="0" checked="checked" onchange="toggleActionArea('nxtActDateArea_<%= idx + 1 %>');" id="actDate0_<%= idx + 1 %>" type="radio">
                   </logic:equal>
                   <logic:notEqual name="dataMdl" property="actDateKbn" value="0">
                     <input name="ntp040ActDateKbn_<%= idx + 1 %>" value="0" onchange="toggleActionArea('nxtActDateArea_<%= idx + 1 %>');" id="actDate0_<%= idx + 1 %>" type="radio">
                   </logic:notEqual>

                   <label for="actDate0_<%= idx + 1 %>" class="text_base" style="color:#000000;font-size:12px;">しない</label>

                   <br>

                   <logic:equal name="dataMdl" property="actDateKbn" value="0">
                     <div id="nxtActDateArea_<%= idx + 1 %>" style="display:none;">
                   </logic:equal>

                   <logic:notEqual name="dataMdl" property="actDateKbn" value="0">
                     <div id="nxtActDateArea_<%= idx + 1 %>">
                   </logic:notEqual>

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


                     <select name="<%= selActionYearIdStr %>" id="<%= selActionYearIdStr.toString() %>">
                       <logic:iterate id="yearLv" name="mbhNtp040Form" property="ntp040YearLavel">
                         <logic:equal name="yearLv" property="value" value="<%= dataActionYear.toString() %>">
                           <option value="<bean:write name="yearLv" property="value" />" selected="selected"><bean:write name="yearLv" property="label" /></option>
                         </logic:equal>
                         <logic:notEqual name="yearLv" property="value" value="<%= dataActionYear.toString() %>">
                           <option value="<bean:write name="yearLv" property="value" />"><bean:write name="yearLv" property="label" /></option>
                         </logic:notEqual>
                       </logic:iterate>
                     </select>

                     <select name="<%= selActionMonthIdStr %>" id="<%= selActionMonthIdStr %>">
                       <logic:iterate id="monthLv" name="mbhNtp040Form" property="ntp040MonthLavel">
                         <logic:equal name="monthLv" property="value" value="<%= dataActionMonth.toString() %>">
                           <option value="<bean:write name="monthLv" property="value" />" selected="selected"><bean:write name="monthLv" property="label" /></option>
                         </logic:equal>
                         <logic:notEqual name="monthLv" property="value" value="<%= dataActionMonth.toString() %>">
                           <option value="<bean:write name="monthLv" property="value" />"><bean:write name="monthLv" property="label" /></option>
                         </logic:notEqual>
                       </logic:iterate>
                     </select>

                     <select name="<%= selActionDayIdStr %>" id="<%= selActionDayIdStr %>">
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

                    <%--
                    <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#<%= selActionYearIdStr.toString() %>')[0], $('#<%= selActionMonthIdStr.toString() %>')[0], $('#<%= selActionDayIdStr.toString() %>')[0], 1)">
                    <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#<%= selActionYearIdStr.toString() %>')[0], $('#<%= selActionMonthIdStr.toString() %>')[0], $('#<%= selActionDayIdStr.toString() %>')[0], 2)">
                    <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#<%= selActionYearIdStr.toString() %>')[0], $('#<%= selActionMonthIdStr.toString() %>')[0], $('#<%= selActionDayIdStr.toString() %>')[0], 3)">
                    <input type="button" value="Cal" onclick="wrtCalendarByBtn(this.form.<%= selActionDayIdStr.toString() %>, this.form.<%= selActionMonthIdStr.toString() %>, this.form.<%= selActionYearIdStr.toString() %>, 'ntp040ActionCalBtn<%= idx + 1 %>')" class="calendar_btn" id="ntp040ActionCalBtn<%= idx + 1 %>">
                    --%>


                  <logic:equal name="mbhNtp040Form" property="ntp040ActDateKbn" value="0">
                  <input type="submit" name="ntp040actDayAdd" class="" value="<gsmsg:write key="ntp.34" />する"/>
                  <div id="nxtActDateArea">
                  </logic:equal>

                  <logic:equal name="mbhNtp040Form" property="ntp040ActDateKbn" value="1">
                  <input type="submit" name="ntp040actDayNotAdd" class="" value="<gsmsg:write key="ntp.34" />しない"/>
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

                  </div>
                  </logic:equal>



                    <br>

                  </div>

                  <textarea id="actionstr_<%= idx + 1 %>" name="ntp040NextAction" cols="50" rows="2" onkeyup="showLengthStr(value, 1000, 'actionlength<%= idx + 1 %>');"><bean:write name="dataMdl" property="actionStr" /></textarea>
                  <br>
                  </div>

                  </logic:equal>

            </logic:equal>

            <logic:equal name="mbhNtp040Form" property="cmd" value="kakunin">
            <br>
            ■<span class="text_bb1"><gsmsg:write key="ntp.22" />!</span><br>


              <span id="goodBtnArea_<bean:write name="dataMdl" property="ntp040NtpSid" />">
                <logic:equal name="dataMdl" property="ntp040GoodFlg" value="0">
                  <input name="ntp040good" id="<bean:write name="dataMdl" property="ntp040NtpSid" />" style="" class="" value="<gsmsg:write key="ntp.22" />!" type="submit">
                </logic:equal>
                <logic:notEqual name="dataMdl" property="ntp040GoodFlg" value="0">
                  <span class="text_already_good">いいね!しています</span>
                </logic:notEqual>
              </span>
              <span class="text_good" id="<bean:write name="dataMdl" property="ntp040NtpSid" />">&nbsp;&nbsp;<span id="goodCntArea_<bean:write name="dataMdl" property="ntp040NtpSid" />"><bean:write name="dataMdl" property="ntp040GoodCnt" /></span>&nbsp;&nbsp;
              </span>

            <br><br>
            ■<span class="text_bb1">コメント</span><br>

            <logic:notEmpty name="dataMdl" property="ntp040CommentList">



                  <span class="commentDspArea<%= idx + 1 %>">
                  <logic:iterate id="npcMdl" name="dataMdl" property="ntp040CommentList">
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
                          <logic:equal name="npcMdl" property="ntp040CommentDelFlg" value="1">
                          &nbsp;&nbsp;<input name="<%= jp.groupsession.v3.mbh.ntp040.MbhNtp040Form.PARAM_COMMENT_DEL %><bean:write name="ntpCmtMdl" property="npcSid" />" style="" class="" value="<gsmsg:write key="cmn.delete" />" type="submit">
                          </logic:equal><br><span style="font-size:13px;color:#333333;"><bean:write name="ntpCmtMdl" property="npcComment" filter="false" /></span>

                    <br>

                  </logic:iterate>
                  </span>



            </logic:notEmpty>


              <br>


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
                    <input type="submit" name="ntp040AddComment" value="投稿" />



              </logic:notEmpty>

           </logic:equal>


           <logic:equal name="mbhNtp040Form" property="ntp040AnkenCompanyUse" value="3">

            </logic:equal>



        </logic:iterate>

        <input type="hidden" id="editLastRowNum" value="<%= lastRowNumber %>" />

      </logic:notEmpty>