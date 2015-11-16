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
      <logic:iterate id="mstr" name="ntp040Form" property="ntp040ColorMsgList" indexId="mId" type="java.lang.String">
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

      <logic:notEmpty name="ntp040Form" property="ntp040DataModelList">
        <logic:iterate id="dataMdl" name="ntp040Form" property="ntp040DataModelList"  indexId="idx">

            <% lastRowNumber =  idx + 1; %>

            <bean:define id="datafrhourval" name="dataMdl" property="frHour" />
            <bean:define id="datafrminval" name="dataMdl" property="frMin"/>
            <bean:define id="datatohourval" name="dataMdl" property="toHour"/>
            <bean:define id="datatominval" name="dataMdl" property="toMin"/>

            <tr id="nippou_data_<%= idx + 1 %>">
            <td colspan="6">
            <table width="100%"><tr><td>
            <tr>
            <td colspan="6" height="25px"></td>
            </tr>
            <tr id="<%= idx + 1 %>">
              <td colspan="3" class="nippou_info_bg_left" id="<%= idx + 1 %>" class="tr_nippou">
                <logic:equal name="dataMdl" property="ntp040SelectFlg" value="1">
                <div id="initSelect"></div>
                </logic:equal>
                <div id="pos<%= idx + 1 %>">
                </div>NO,<%= idx + 1 %>
              </td>
              <td id="<bean:write name="dataMdl" property="ntp040NtpSid" />" colspan="2" align="right" class="nippou_info_bg">



              <span class="editButtonArea<%= idx + 1 %>">
                <input class="btn_copy_n2" name="ntpCopyBtn" id="<bean:write name="dataMdl" property="ntp040NtpSid" />" value="複写して登録" type="button">
              </span>

              <logic:equal name="ntp040Form" property="authAddEditKbn" value="0">

                <span class="editButtonArea<%= idx + 1 %>">
                  <input class="btn_edit_n4" name="ntpEditBtn" id="<%= idx + 1 %>" value="編集" type="button">
                  <input class="close_btn1" id="ntpDellBtn" value="削除" type="button">
                </span>

                <span class="editButtonArea<%= idx + 1 %>" style="display:none;">
                  <input class="btn_edit_n4" id="<%= idx + 1 %>" name="ntpEditKakuteiBtn" value="確定" type="button">
                  <input class="close_btn1" id="<%= idx + 1 %>" name="ntpEditCancelBtn" value="ｷｬﾝｾﾙ" type="button">
                </span>

              </logic:equal>
              </td>
              <td colspan="6" height="25px"></td>
            </tr>

            <tr class="usrInfArea<%= idx + 1 %>">
              <td class="<%= tdColor %>" style="background-color:#fafafa;" colspan="5">
                <table>
                  <tr>
                    <td>
                      <logic:equal name="ntp040Form" property="ntp040UsrBinSid" value="0">
                        <img  class="comment_Img" src="../user/images/photo.gif" name="userImage" alt="<gsmsg:write key="cmn.photo" />" border="1" width="50px" />
                      </logic:equal>

                      <logic:notEqual name="ntp040Form" property="ntp040UsrBinSid" value="0">
                        <logic:equal name="ntp040Form" property="ntp040UsrPctKbn" value="1">
                          <div align="center" class="photo_hikokai2"><span style="color:#fc2929;"><gsmsg:write key="cmn.private" /></span></div>
                        </logic:equal>
                        <logic:notEqual name="ntp040Form" property="ntp040UsrPctKbn" value="1">
                          <img class="comment_Img" src="../common/cmn100.do?CMD=getImageFile&cmn100binSid=<bean:write name="ntp040Form" property="ntp040UsrBinSid" />" alt="<gsmsg:write key="cmn.photo" />" width="50px" />
                        </logic:notEqual>
                      </logic:notEqual>

                    </td>
                    <td style="padding-left:10px;">
                      <span style="font-size:12px;font-weight:bold;"><bean:write name="ntp040Form" property="ntp040UsrName" /></span>
                      <div>

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


                      <div>
                         <span style="font-weight:bold;" class="dsp_title_<%= idx + 1 %>"><bean:write name="dataMdl" property="title" /></span>
                      </div>


                    </td>
                  </tr>
                </table>
              </td>
            </tr>

            <tr class="titleArea<%= idx + 1 %>" style="display:none;">
            <td class="table_bg_A5B4E1" width="10%" nowrap="nowrap"><span class="text_bb1">タイトル</span><span class="titleArea<%= idx + 1 %> text_r2" style="display:none;">※</span></td>
            <td class="<%= tdColor %> ntp_add_td" width="90%" colspan="3" align="left">
<%--
              <div class="titleArea<%= idx + 1 %>">
                 <span class="dsp_title_<%= idx + 1 %>"><bean:write name="dataMdl" property="title" /></span>
              </div>
--%>
              <div>

                <input name="ntp040Title_<%= idx + 1 %>" maxlength="100" value="<bean:write name="dataMdl" property="title" />" id="ntpTitleTextBox" class="text_base" type="text" style="width:337px;" >

                <logic:equal name="dataMdl" property="bgcolor" value="1">
                  <span class="sc_block_color_1"><input name="ntp040Bgcolor_<%= idx + 1 %>" value="1" checked="checked" id="bg_color1_<%= idx + 1 %>" type="radio"></span>
                  <label for="bg_color1_<%= idx + 1 %>" class="text_base"></label><%= colormsg1 %>
                </logic:equal>
                <logic:notEqual name="dataMdl" property="bgcolor" value="1">
                  <span class="sc_block_color_1"><input name="ntp040Bgcolor_<%= idx + 1 %>" value="1" id="bg_color1_<%= idx + 1 %>" type="radio"></span>
                  <label for="bg_color1_<%= idx + 1 %>" class="text_base"></label><%= colormsg1 %>
                </logic:notEqual>

                <logic:equal name="dataMdl" property="bgcolor" value="2">
                  <span class="sc_block_color_2"><input name="ntp040Bgcolor_<%= idx + 1 %>" value="2" checked="checked" id="bg_color2_<%= idx + 1 %>" type="radio"></span>
                  <label for="bg_color2_<%= idx + 1 %>" class="text_base"></label><%= colormsg2 %>
                </logic:equal>
                <logic:notEqual name="dataMdl" property="bgcolor" value="2">
                  <span class="sc_block_color_2"><input name="ntp040Bgcolor_<%= idx + 1 %>" value="2" id="bg_color2_<%= idx + 1 %>" type="radio"></span>
                  <label for="bg_color2_<%= idx + 1 %>" class="text_base"></label><%= colormsg2 %>
                </logic:notEqual>

                <logic:equal name="dataMdl" property="bgcolor" value="3">
                  <span class="sc_block_color_3"><input name="ntp040Bgcolor_<%= idx + 1 %>" value="3" checked="checked" id="bg_color3_<%= idx + 1 %>" type="radio"></span>
                  <label for="bg_color3_<%= idx + 1 %>" class="text_base"></label><%= colormsg3 %>
                </logic:equal>
                <logic:notEqual name="dataMdl" property="bgcolor" value="3">
                  <span class="sc_block_color_3"><input name="ntp040Bgcolor_<%= idx + 1 %>" value="3" id="bg_color3_<%= idx + 1 %>" type="radio"></span>
                  <label for="bg_color3_<%= idx + 1 %>" class="text_base"></label><%= colormsg3 %>
                </logic:notEqual>

                <logic:equal name="dataMdl" property="bgcolor" value="4">
                  <span class="sc_block_color_4"><input name="ntp040Bgcolor_<%= idx + 1 %>" value="4" checked="checked" id="bg_color4_<%= idx + 1 %>" type="radio"></span>
                  <label for="bg_color4_<%= idx + 1 %>" class="text_base"></label><%= colormsg4 %>
                </logic:equal>
                <logic:notEqual name="dataMdl" property="bgcolor" value="4">
                  <span class="sc_block_color_4"><input name="ntp040Bgcolor_<%= idx + 1 %>" value="4" id="bg_color4_<%= idx + 1 %>" type="radio"></span>
                  <label for="bg_color4_<%= idx + 1 %>" class="text_base"></label><%= colormsg4 %>
                </logic:notEqual>

                <logic:equal name="dataMdl" property="bgcolor" value="5">
                  <span class="sc_block_color_5"><input name="ntp040Bgcolor_<%= idx + 1 %>" value="5" checked="checked" id="bg_color5_<%= idx + 1 %>" type="radio"></span>
                  <label for="bg_color5_<%= idx + 1 %>" class="text_base"></label><%= colormsg5 %>
                </logic:equal>
                <logic:notEqual name="dataMdl" property="bgcolor" value="5">
                  <span class="sc_block_color_5"><input name="ntp040Bgcolor_<%= idx + 1 %>" value="5" id="bg_color5_<%= idx + 1 %>" type="radio"></span>
                  <label for="bg_color5_<%= idx + 1 %>" class="text_base"></label><%= colormsg5 %>
                </logic:notEqual>

              </div>

            </td>
            </tr>

            <tr style="display:none;" class="ntpDateAreaTr<%= idx + 1 %>">
            <td class="table_bg_A5B4E1" nowrap><span class="text_bb1">報告日付</span><span class="ntpTimeArea<%= idx + 1 %> text_r2" style="display:none;">※</span></td>
            <td align="left" class="<%= tdColor %> ntp_add_td" colspan="3">

             <% selYearIdStr  = "selYear"  + String.valueOf(idx + 1); %>
             <% selMonthIdStr = "selMonth" + String.valueOf(idx + 1); %>
             <% selDayIdStr   = "selDay"   + String.valueOf(idx + 1); %>

             <bean:define id="dataYear" name="dataMdl" property="ntpYear" type="java.lang.Integer"/>
             <bean:define id="dataMonth" name="dataMdl" property="ntpMonth" type="java.lang.Integer"/>
             <bean:define id="dataDay" name="dataMdl" property="ntpDay" type="java.lang.Integer"/>

             <select name="<%= selYearIdStr %>" id="<%= selYearIdStr.toString() %>">
               <logic:iterate id="yearLv" name="ntp040Form" property="ntp040YearLavel">
                 <logic:equal name="yearLv" property="value" value="<%= dataYear.toString() %>">
                   <option value="<bean:write name="yearLv" property="value" />" selected="selected"><bean:write name="yearLv" property="label" /></option>
                 </logic:equal>
                 <logic:notEqual name="yearLv" property="value" value="<%= dataYear.toString() %>">
                   <option value="<bean:write name="yearLv" property="value" />"><bean:write name="yearLv" property="label" /></option>
                 </logic:notEqual>
               </logic:iterate>
             </select>

             <select name="<%= selMonthIdStr %>" id="<%= selMonthIdStr %>">
               <logic:iterate id="monthLv" name="ntp040Form" property="ntp040MonthLavel">
                 <logic:equal name="monthLv" property="value" value="<%= dataMonth.toString() %>">
                   <option value="<bean:write name="monthLv" property="value" />" selected="selected"><bean:write name="monthLv" property="label" /></option>
                 </logic:equal>
                 <logic:notEqual name="monthLv" property="value" value="<%= dataMonth.toString() %>">
                   <option value="<bean:write name="monthLv" property="value" />"><bean:write name="monthLv" property="label" /></option>
                 </logic:notEqual>
               </logic:iterate>
             </select>

             <select name="<%= selDayIdStr %>" id="<%= selDayIdStr %>">
               <logic:iterate id="dayLv" name="ntp040Form" property="ntp040DayLavel">
                 <logic:equal name="dayLv" property="value" value="<%= dataDay.toString() %>">
                   <option value="<bean:write name="dayLv" property="value" />" selected="selected"><bean:write name="dayLv" property="label" /></option>
                 </logic:equal>
                 <logic:notEqual name="dayLv" property="value" value="<%= dataDay.toString() %>">
                   <option value="<bean:write name="dayLv" property="value" />"><bean:write name="dayLv" property="label" /></option>
                 </logic:notEqual>
               </logic:iterate>
             </select>


             <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#<%= selYearIdStr %>')[0], $('#<%= selMonthIdStr %>')[0], $('#<%= selDayIdStr %>')[0], 1)">
             <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#<%= selYearIdStr %>')[0], $('#<%= selMonthIdStr %>')[0], $('#<%= selDayIdStr %>')[0], 2)">
             <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#<%= selYearIdStr %>')[0], $('#<%= selMonthIdStr %>')[0], $('#<%= selDayIdStr %>')[0], 3)">
             <input type="button" value="Cal" onclick="wrtCalendarByBtn(this.form.selDay<%= idx + 1 %>, this.form.selMonth<%= idx + 1 %>, this.form.selYear<%= idx + 1 %>, 'ntp040FrCalBtn<%= idx + 1 %>')" class="calendar_btn" id="ntp040FrCalBtn<%= idx + 1 %>">


            </td>
            </tr>

            <tr class="ntpTimeArea<%= idx + 1 %>" style="display:none;">
            <td class="table_bg_A5B4E1" nowrap><span class="text_bb1">時間</span><span class="ntpTimeArea<%= idx + 1 %> text_r2" style="display:none;">※</span></td>
            <td align="left" class="<%= tdColor %> ntp_add_td" colspan="3">


<%--
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
--%>


              <div>

                 <% String ntp040FrHour = "ntp040FrHour_" + (idx + 1); %>
                 <html:select property="<%= ntp040FrHour %>" value="<%= String.valueOf(datafrhourval) %>" onchange="setToDay();">
                    <html:optionsCollection name="ntp040Form" property="ntp040HourLavel" value="value" label="label" />
                 </html:select>
                 <gsmsg:write key="cmn.hour.input" />
                 <% String ntp040FrMin = "ntp040FrMin_" + (idx + 1); %>
                 <html:select property="<%= ntp040FrMin %>" value="<%= String.valueOf(datafrminval) %>" onchange="setToDay();">
                    <html:optionsCollection name="ntp040Form" property="ntp040MinuteLavel" value="value" label="label" />
                 </html:select>
                 <gsmsg:write key="cmn.minute.input" />
                 ～
                 <% String ntp040ToHour = "ntp040ToHour_" + (idx + 1); %>
                 <html:select property="<%= ntp040ToHour %>" value="<%= String.valueOf(datatohourval) %>" onchange="setToDay();">
                    <html:optionsCollection name="ntp040Form" property="ntp040HourLavel" value="value" label="label" />
                 </html:select>
                 <gsmsg:write key="cmn.hour.input" />
                 <% String ntp040ToMin = "ntp040ToMin_" + (idx + 1); %>
                 <html:select property="<%= ntp040ToMin %>" value="<%= String.valueOf(datatominval) %>" onchange="setToDay();">
                    <html:optionsCollection name="ntp040Form" property="ntp040MinuteLavel" value="value" label="label" />
                 </html:select>
                 <gsmsg:write key="cmn.minute.input" />
                   &nbsp;&nbsp;&nbsp;&nbsp;<span id="betWeenDays" class="text_base"></span>

              </div>

            </td>
            </tr>


            <logic:equal name="ntp040Form" property="ntp040AnkenCompanyUse" value="0">
              <tr>
              <td class="table_bg_A5B4E1" nowrap><span class="text_bb1">案件</span><span class="text_r2"></span></td>
              <td width="30%" align="left" class="<%= tdColor %> ntp_add_td" nowrap>

                <div class="ankenDataArea<%= idx + 1 %>">

                  <img src="../schedule/images/spacer.gif" width="1px" border="0" height="10px">

                  <logic:notEmpty name="dataMdl" property="ankenSid">
                    <div class="text_anken_code">案件コード：<span class="anken_code_name_<%= idx + 1 %>"><bean:write name="dataMdl" property="ankenCode" /></span></div>
                  </logic:notEmpty>

                  <logic:notEmpty name="dataMdl" property="ankenSid">
                    <div class="text_anken">
                      <a id="<bean:write name="dataMdl" property="ankenSid" />" class="sc_link anken_click">
                        <span class="anken_name_<%= idx + 1 %>"><bean:write name="dataMdl" property="ankenName" /></span>
                      </a>
                    </div>
                  </logic:notEmpty>

                </div>

                <div class="ankenDataArea<%= idx + 1 %>" style="display:none;">

                  <input type="button" class="btn_search_n1" value="案件検索" onclick="return openAnkenWindow('ntp040','<%= idx + 1 %>')" />
                  <input type="button" class="ankenHistoryPop btn_history" id="<%= idx + 1 %>" value="履歴" /><br>
                  <img src="../schedule/images/spacer.gif" width="1px" border="0" height="10px">
                  <div id="ntp040AnkenIdArea_<%= idx + 1 %>">
                    <logic:notEmpty name="dataMdl" property="ankenSid">
                      <input name="ntp040AnkenSid_<%= idx + 1 %>" value="<bean:write name="dataMdl" property="ankenSid" />" type="hidden">
                    </logic:notEmpty>
                  </div>
                  <div id="ntp040AnkenCodeArea_<%= idx + 1 %>">
                    <logic:notEmpty name="dataMdl" property="ankenCode">
                      <span class="text_anken_code">案件コード：<span class="anken_code_name_<%= idx + 1 %>"><bean:write name="dataMdl" property="ankenCode" /></span></span>
                    </logic:notEmpty>
                  </div>
                  <div id="ntp040AnkenNameArea_<%= idx + 1 %>">
                    <logic:notEmpty name="dataMdl" property="ankenName">
                      <div class="text_anken">
                        <a id="<bean:write name="dataMdl" property="ankenSid" />" class="sc_link anken_click">
                          <span class="anken_name_<%= idx + 1 %>"><bean:write name="dataMdl" property="ankenName" /></span>
                        </a>
                        <a href="javascript:void(0);" onclick="delAnken('ntp040','_<%= idx + 1 %>');"><img src="../common/images/delete.gif" class="img_bottom" alt="" width="15"></a>
                      </div>

                    </logic:notEmpty>
                  </div>

                </div>


              </td>
              <td class="table_bg_A5B4E1" nowrap><span class="text_bb1">企業・顧客</span><span class="text_r2"></span></td>
              <td align="left" class="<%= tdColor %> ntp_add_td" >

                <div class="kigyouDataArea<%= idx + 1 %>">

                  <img src="../schedule/images/spacer.gif" width="1px" border="0" height="10px">

                    <logic:notEmpty name="dataMdl" property="companySid">
                      <span class="text_anken_code">企業コード：<span class="comp_code_name_<%= idx + 1 %>"><bean:write name="dataMdl" property="companyCode" /></span></span>
                    </logic:notEmpty>

                    <logic:notEmpty name="dataMdl" property="companyName">
                      <div class="text_company">
                        <a id="<bean:write name="dataMdl" property="companySid" />" class="sc_link comp_click comp_name_link_<%= idx + 1 %>">
                          <span class="comp_name_<%= idx + 1 %>"><bean:write name="dataMdl" property="companyName" />
                            <logic:notEmpty name="dataMdl" property="companySid">
                              <bean:write name="dataMdl" property="companyBaseName" />
                            </logic:notEmpty>
                          </span>
                        </a>
                      </div>
                    </logic:notEmpty>

                 </div>


               <div class="kigyouDataArea<%= idx + 1 %>" style="display:none;">

                  <input type="button" class="btn_address_n2" value="アドレス帳" onclick="return openCompanyWindow2('ntp040',<%= idx + 1 %>)" />
                  <input type="button" class="adrHistoryPop btn_history" id="<%= idx + 1 %>" value="履歴" /><br>
                  <img src="../schedule/images/spacer.gif" width="1px" border="0" height="10px">
                  <div id="ntp040CompanyIdArea_<%= idx + 1 %>">
                    <logic:notEmpty name="dataMdl" property="companySid">
                      <input name="ntp040CompanySid_<%= idx + 1 %>" value="<bean:write name="dataMdl" property="companySid" />" type="hidden">
                    </logic:notEmpty>
                  </div>
                  <div id="ntp040CompanyBaseIdArea_<%= idx + 1 %>">
                    <logic:notEmpty name="dataMdl" property="companyBaseSid">
                      <input name="ntp040CompanyBaseSid_<%= idx + 1 %>" value="<bean:write name="dataMdl" property="companyBaseSid" />" type="hidden">
                    </logic:notEmpty>
                  </div>
                  <div id="ntp040CompanyCodeArea_<%= idx + 1 %>">
                    <logic:notEmpty name="dataMdl" property="companyCode">
                      <span class="text_anken_code">企業コード：<span class="comp_code_name_<%= idx + 1 %>"><bean:write name="dataMdl" property="companyCode" /></span></span>
                    </logic:notEmpty>
                  </div>
                  <div id="ntp040CompNameArea_<%= idx + 1 %>">
                    <logic:notEmpty name="dataMdl" property="companyName">
                      <div class="text_company">
                        <a id="<bean:write name="dataMdl" property="companySid" />" class="sc_link comp_click">
                          <span class="comp_name_<%= idx + 1 %>"><bean:write name="dataMdl" property="companyName" />
                          <logic:notEmpty name="dataMdl" property="companyName">
                            <bean:write name="dataMdl" property="companyBaseName" />
                          </logic:notEmpty>
                          </span>
                        </a>
                        <a href="javascript:void(0);" onclick="delCompany('ntp040','_<%= idx + 1 %>');"><img src="../common/images/delete.gif" class="img_bottom" alt="" width="15"></a>
                      </div>
                    </logic:notEmpty>
                  </div>
                  <div id="ntp040AddressIdArea_<%= idx + 1 %>">
                  </div>
                  <div id="ntp040AddressNameArea_<%= idx + 1 %>">
                  </div>

                </div>


              </td>
              </tr>
            </logic:equal>


            <logic:equal name="ntp040Form" property="ntp040AnkenCompanyUse" value="1">
              <tr>
              <td class="table_bg_A5B4E1" nowrap><span class="text_bb1">案件</span><span class="text_r2"></span></td>
              <td class="td_ntp_wt2 ntp_add_td" align="left" nowrap>

                <div class="ankenDataArea<%= idx + 1 %>">

                  <img src="../schedule/images/spacer.gif" width="1px" border="0" height="10px">

                  <logic:notEmpty name="dataMdl" property="ankenSid">
                    <span class="text_anken_code">案件コード：<span class="anken_code_name_<%= idx + 1 %>"><bean:write name="dataMdl" property="ankenCode" /></span></span>
                  </logic:notEmpty>

                  <logic:notEmpty name="dataMdl" property="ankenSid">
                    <div class="text_anken">
                      <a id="<bean:write name="dataMdl" property="ankenSid" />" class="sc_link anken_click">
                        <span class="anken_name_<%= idx + 1 %>"><bean:write name="dataMdl" property="ankenName" /></span>
                      </a>
                    </div>
                  </logic:notEmpty>

                </div>

                <div class="ankenDataArea<%= idx + 1 %>" style="display:none;">

                  <input type="button" class="btn_search_n1" value="案件検索" onclick="return openAnkenWindow('ntp040','<%= idx + 1 %>')" />
                  <input type="button" class="ankenHistoryPop btn_history" id="<%= idx + 1 %>" value="履歴" /><br>
                  <img src="../schedule/images/spacer.gif" width="1px" border="0" height="10px">
                  <div id="ntp040AnkenIdArea_<%= idx + 1 %>">
                    <logic:notEmpty name="dataMdl" property="ankenSid">
                      <input name="ntp040AnkenSid_<%= idx + 1 %>" value="<bean:write name="dataMdl" property="ankenSid" />" type="hidden">
                    </logic:notEmpty>
                  </div>
                  <div id="ntp040AnkenCodeArea_<%= idx + 1 %>">
                    <logic:notEmpty name="dataMdl" property="ankenCode">
                      <span class="text_anken_code">案件コード：<span class="anken_code_name_<%= idx + 1 %>"><bean:write name="dataMdl" property="ankenCode" /></span></span>
                    </logic:notEmpty>
                  </div>
                  <div id="ntp040AnkenNameArea_<%= idx + 1 %>">
                    <logic:notEmpty name="dataMdl" property="ankenName">
                      <div class="text_anken">
                        <a id="<bean:write name="dataMdl" property="ankenSid" />" class="sc_link anken_click">
                          <span class="anken_name_<%= idx + 1 %>"><bean:write name="dataMdl" property="ankenName" /></span>
                        </a>
                        <a href="javascript:void(0);" onclick="delAnken('ntp040','_<%= idx + 1 %>');"><img src="../common/images/delete.gif" class="img_bottom" alt="" width="15"></a>
                      </div>
                    </logic:notEmpty>
                  </div>

                </div>


              </td>
              <td class="td_ntp_wt4"></td><td align="left" class="td_ntp_wt3" ></td>
              </tr>
            </logic:equal>


            <logic:equal name="ntp040Form" property="ntp040AnkenCompanyUse" value="2">
              <tr>

              <td class="table_bg_A5B4E1" nowrap><span class="text_bb1">企業・顧客</span><span class="text_r2"></span></td>
              <td class="td_ntp_wt2 ntp_add_td" align="left">

                <div class="kigyouDataArea<%= idx + 1 %>">

                  <img src="../schedule/images/spacer.gif" width="1px" border="0" height="10px">

                    <logic:notEmpty name="dataMdl" property="companySid">
                      <span class="text_anken_code">企業コード：<span class="comp_code_name_<%= idx + 1 %>"><bean:write name="dataMdl" property="companyCode" /></span></span>
                    </logic:notEmpty>

                    <logic:notEmpty name="dataMdl" property="companyName">
                      <div class="text_company">
                        <a id="<bean:write name="dataMdl" property="companySid" />" class="sc_link comp_click comp_name_link_<%= idx + 1 %>">
                          <span class="comp_name_<%= idx + 1 %>"><bean:write name="dataMdl" property="companyName" />
                            <logic:notEmpty name="dataMdl" property="companySid">
                              <bean:write name="dataMdl" property="companyBaseName" />
                            </logic:notEmpty>
                          </span>
                        </a>
                      </div>
                    </logic:notEmpty>

                 </div>


               <div class="kigyouDataArea<%= idx + 1 %>" style="display:none;">

                  <input type="button" class="btn_address_n2" value="アドレス帳" onclick="return openCompanyWindow2('ntp040',<%= idx + 1 %>)" />
                  <input type="button" class="adrHistoryPop btn_history" id="<%= idx + 1 %>" value="履歴" /><br>

                  <img src="../schedule/images/spacer.gif" width="1px" border="0" height="10px">
                  <div id="ntp040CompanyIdArea_<%= idx + 1 %>">
                    <logic:notEmpty name="dataMdl" property="companySid">
                      <input name="ntp040CompanySid_<%= idx + 1 %>" value="<bean:write name="dataMdl" property="companySid" />" type="hidden">
                    </logic:notEmpty>
                  </div>
                  <div id="ntp040CompanyBaseIdArea_<%= idx + 1 %>">
                    <logic:notEmpty name="dataMdl" property="companyBaseSid">
                      <input name="ntp040CompanyBaseSid_<%= idx + 1 %>" value="<bean:write name="dataMdl" property="companyBaseSid" />" type="hidden">
                    </logic:notEmpty>
                  </div>
                  <div id="ntp040CompanyCodeArea_<%= idx + 1 %>">
                    <logic:notEmpty name="dataMdl" property="companyCode">
                      <span class="text_anken_code">企業コード：<span class="comp_code_name_<%= idx + 1 %>"><bean:write name="dataMdl" property="companyCode" /></span></span>
                    </logic:notEmpty>
                  </div>
                  <div id="ntp040CompNameArea_<%= idx + 1 %>">
                    <logic:notEmpty name="dataMdl" property="companyName">
                      <div class="text_company">
                        <a id="<bean:write name="dataMdl" property="companySid" />" class="sc_link comp_click">
                          <span class="comp_name_<%= idx + 1 %>"><bean:write name="dataMdl" property="companyName" />
                          <logic:notEmpty name="dataMdl" property="companyName">
                            <bean:write name="dataMdl" property="companyBaseName" />
                          </logic:notEmpty>
                          </span>
                        </a>
                        <a href="javascript:void(0);" onclick="delCompany('ntp040','_<%= idx + 1 %>');"><img src="../common/images/delete.gif" class="img_bottom" alt="" width="15"></a>
                      </div>
                    </logic:notEmpty>
                  </div>
                  <div id="ntp040AddressIdArea_<%= idx + 1 %>">
                  </div>
                  <div id="ntp040AddressNameArea_<%= idx + 1 %>">
                  </div>

                </div>


              </td>
              <td class="td_ntp_wt4"></td><td align="left" class="td_ntp_wt3" ></td>
              </tr>
            </logic:equal>

            <tr>
            <td class="table_bg_A5B4E1" nowrap="nowrap"><span class="text_bb1">内　容<a id="naiyou" name="naiyou"></a></span></td>
            <td class="<%= tdColor %> ntp_add_td" colspan="3" align="left">
              <div class="naiyouArea<%= idx + 1 %>">
              <span class="text_base">
                <span class="dsp_naiyou_<%= idx + 1 %>"><bean:write name="dataMdl" property="ntp040DspValueStr" filter="false"/></span>
              </span>
              </div>

              <div class="naiyouArea<%= idx + 1 %>" style="display:none;">
                <textarea id="inputstr_<%= idx + 1 %>" name="ntp040Value_<%= idx + 1 %>" style="width:431px;" rows="5" onkeyup="showLengthStr(value, 1000, 'inputlength<%= idx + 1 %>');"><bean:write name="dataMdl" property="valueStr" /></textarea>
                <br>
                <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength<%= idx + 1 %>" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthContent %>&nbsp;<gsmsg:write key="cmn.character" /></span>
              </div>
            </td>
            </tr>

            <logic:equal name="ntp040Form" property="ntp040KtBriHhuUse" value="0">
              <tr>
              <td class="table_bg_A5B4E1" nowrap><span class="text_bb1">活動分類/方法</span></td>
              <td align="left" colspan="3" class="<%= tdColor %> ntp_add_td">

                <bean:define id="ktbunruival" name="dataMdl" property="ktbunruiSid"/>
                <bean:define id="ktbouhouval" name="dataMdl" property="kthouhouSid"/>

                <div class="ktBunruiArea<%= idx + 1 %>">
                <span class="text_base">
                 <span class="dsp_ktbunrui_<%= idx + 1 %>"><bean:write name="dataMdl" property="ntp040DspKtbunrui"/></span>&nbsp;
                 <span class="dsp_kthouhou_<%= idx + 1 %>"><bean:write name="dataMdl" property="ntp040DspKthouhou"/></span>
                </span>
                </div>

                 <div class="ktBunruiArea<%= idx + 1 %>" style="display:none;">
                   <% String ntp040Ktbunrui = "ntp040Ktbunrui_" + (idx + 1); %>
                   <logic:notEmpty name="ntp040Form" property="ntp040KtbunruiLavel">
                     <html:select property="<%= ntp040Ktbunrui %>" value="<%= String.valueOf(ktbunruival) %>">
                        <html:optionsCollection name="ntp040Form" property="ntp040KtbunruiLavel" value="value" label="label" />
                     </html:select>
                   </logic:notEmpty>

                   <logic:notEmpty name="ntp040Form" property="ntp040KthouhouLavel">
                     <% String ntp040Kthouhou = "ntp040Kthouhou_" + (idx + 1); %>
                     <html:select property="<%= ntp040Kthouhou %>" value="<%= String.valueOf(ktbouhouval) %>">
                        <html:optionsCollection name="ntp040Form" property="ntp040KthouhouLavel" value="value" label="label" />
                     </html:select>
                   </logic:notEmpty>
                 </div>

               </td>
              </tr>
            </logic:equal>


            <logic:equal name="ntp040Form" property="ntp040MikomidoUse" value="0">
              <tr>
              <td class="table_bg_A5B4E1" nowrap="nowrap"><span class="text_bb1">見込み度</span>
              </td>
              <td class="<%= tdColor %> ntp_add_td" colspan="3" align="left">

                <div class="mikomidoArea<%= idx + 1 %>">
                  <span class="text_base">
                    <span class="dsp_mikomido_<%= idx + 1 %>"><bean:write name="dataMdl" property="ntp040DspMikomido"/></span>％
                  </span>
                </div>

                <div class="mikomidoArea<%= idx + 1 %>" style="display:none;">
                  <span class="text_base">
                    <logic:equal name="dataMdl" property="mikomido" value="0">
                      <input name="ntp040Mikomido_<%= idx + 1 %>" value="0" checked="checked" id="ntp040Mikomido0_<%= idx + 1 %>" type="radio"><label for="ntp040Mikomido0_<%= idx + 1 %>">10%</label>
                    </logic:equal>
                    <logic:notEqual name="dataMdl" property="mikomido" value="0">
                      <input name="ntp040Mikomido_<%= idx + 1 %>" value="0" id="ntp040Mikomido0_<%= idx + 1 %>" type="radio"><label for="ntp040Mikomido0_<%= idx + 1 %>">10%</label>
                    </logic:notEqual>

                    <logic:equal name="dataMdl" property="mikomido" value="1">
                      <input name="ntp040Mikomido_<%= idx + 1 %>" value="1" checked="checked" id="ntp040Mikomido1_<%= idx + 1 %>" type="radio"><label for="ntp040Mikomido1_<%= idx + 1 %>">30%</label>
                    </logic:equal>
                    <logic:notEqual name="dataMdl" property="mikomido" value="1">
                      <input name="ntp040Mikomido_<%= idx + 1 %>" value="1" id="ntp040Mikomido1_<%= idx + 1 %>" type="radio"><label for="ntp040Mikomido1_<%= idx + 1 %>">30%</label>
                    </logic:notEqual>

                    <logic:equal name="dataMdl" property="mikomido" value="2">
                      <input name="ntp040Mikomido_<%= idx + 1 %>" value="2" checked="checked" id="ntp040Mikomido2_<%= idx + 1 %>" type="radio"><label for="ntp040Mikomido2_<%= idx + 1 %>">50%</label>
                    </logic:equal>
                    <logic:notEqual name="dataMdl" property="mikomido" value="2">
                      <input name="ntp040Mikomido_<%= idx + 1 %>" value="2" id="ntp040Mikomido2_<%= idx + 1 %>" type="radio"><label for="ntp040Mikomido2_<%= idx + 1 %>">50%</label>
                    </logic:notEqual>

                    <logic:equal name="dataMdl" property="mikomido" value="3">
                      <input name="ntp040Mikomido_<%= idx + 1 %>" value="3" checked="checked" id="ntp040Mikomido3_<%= idx + 1 %>" type="radio"><label for="ntp040Mikomido3_<%= idx + 1 %>">70%</label>
                    </logic:equal>
                    <logic:notEqual name="dataMdl" property="mikomido" value="3">
                      <input name="ntp040Mikomido_<%= idx + 1 %>" value="3" id="ntp040Mikomido3_<%= idx + 1 %>" type="radio"><label for="ntp040Mikomido3_<%= idx + 1 %>">70%</label>
                    </logic:notEqual>

                    <logic:equal name="dataMdl" property="mikomido" value="4">
                      <input name="ntp040Mikomido_<%= idx + 1 %>" value="4" checked="checked" id="ntp040Mikomido4_<%= idx + 1 %>" type="radio"><label for="ntp040Mikomido4_<%= idx + 1 %>">100%</label>
                    </logic:equal>
                    <logic:notEqual name="dataMdl" property="mikomido" value="4">
                      <input name="ntp040Mikomido_<%= idx + 1 %>" value="4" id="ntp040Mikomido4_<%= idx + 1 %>" type="radio"><label for="ntp040Mikomido4_<%= idx + 1 %>">100%</label>
                    </logic:notEqual>

                  </span>

                  <logic:notEqual name="ntp040Form" property="ntp040MikomidoFlg" value="0">
                    <br><input class="mikomido_btn mikomido_back" type="button" value="基 準" />
                  </logic:notEqual>

                </div>

              </td>
              </tr>
            </logic:equal>


            <logic:equal name="ntp040Form" property="ntp040TmpFileUse" value="0">
              <tr>
              <td class="table_bg_A5B4E1" nowrap><span class="text_bb1">添付<a id="naiyou" name="naiyou"></a></span></td>
              <td align="left"  class="<%= tdColor %> ntp_add_td" colspan="3">

                <div class="tempFileArea<%= idx + 1 %> dsp_tmp_file_area_<%= idx + 1 %>">
                  <logic:notEmpty name="dataMdl" property="ntp040FileList">
                    <logic:iterate id="tempMdl" name="dataMdl" property="ntp040FileList">
                      <a href="javascript:void(0);" onClick="return fileLinkClick(<bean:write name="dataMdl" property="ntp040NtpSid" />,<bean:write name="tempMdl" property="binSid"/>);"><span class="text_link_min"><bean:write name="tempMdl" property="binFileName"/><bean:write name="tempMdl" property="binFileSizeDsp" /></span></a><br>
                    </logic:iterate>
                  </logic:notEmpty>
                </div>

                <div class="tempFileArea<%= idx + 1 %>" style="display:none;">
                  <select id="ntp040selFile<%= idx + 1 %>" name="ntp040selectFiles<%= idx + 1 %>" multiple="multiple" size="3" class="select01">

                  </select>
                  <input type="button" class="btn_attach" value="添付" name="attacheBtn" onClick="opnTempPlus('ntp040selectFiles<%= idx + 1 %>', 'nippou', '0', '0', 'row<%= idx + 1 %>');">
                  &nbsp;
                  <input type="button" class="btn_delete" name="tempDelBtn" id="<%= idx + 1 %>" value="削除">
                </div>

              </td>
              </tr>
            </logic:equal>

            <logic:equal name="ntp040Form" property="ntp040NextActionUse" value="0">
              <tr>
              <td class="table_bg_A5B4E1" nowrap="nowrap"><span class="text_bb1">次のアクション<a id="nextAction" name="nextAction"></a></span></td>
              <td class="<%= tdColor %> ntp_add_td" colspan="3" align="left">

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

                  <span class="text_base">
                  <span class="dsp_nextaction_<%= idx + 1 %>"><bean:write name="dataMdl" property="ntp040DspActionStr" filter="false"/></span>
                  </span>
                </div>

                <div class="nextActionArea<%= idx + 1 %>" style="display:none;">

                   <span style="color:#000000;font-size:12px;font-weight:bold;">&nbsp;日付指定：</span>


                   <logic:equal name="dataMdl" property="actDateKbn" value="1">
                     <input name="ntp040ActDateKbn_<%= idx + 1 %>" value="1" checked="checked" onclick="toggleActionAreaShow('nxtActDateArea_<%= idx + 1 %>');" id="actDate1_<%= idx + 1 %>" type="radio">
                   </logic:equal>
                   <logic:notEqual name="dataMdl" property="actDateKbn" value="1">
                     <input name="ntp040ActDateKbn_<%= idx + 1 %>" value="1" onclick="toggleActionAreaShow('nxtActDateArea_<%= idx + 1 %>');" id="actDate1_<%= idx + 1 %>" type="radio">
                   </logic:notEqual>

                   <label for="actDate1_<%= idx + 1 %>" class="text_base" style="color:#000000;font-size:12px;">する</label>

                   <logic:equal name="dataMdl" property="actDateKbn" value="0">
                     <input name="ntp040ActDateKbn_<%= idx + 1 %>" value="0" checked="checked" onclick="toggleActionAreaHide('nxtActDateArea_<%= idx + 1 %>');" id="actDate0_<%= idx + 1 %>" type="radio">
                   </logic:equal>
                   <logic:notEqual name="dataMdl" property="actDateKbn" value="0">
                     <input name="ntp040ActDateKbn_<%= idx + 1 %>" value="0" onclick="toggleActionAreaHide('nxtActDateArea_<%= idx + 1 %>');" id="actDate0_<%= idx + 1 %>" type="radio">
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
                       <bean:define id="actionInitYear" name="ntp040Form" property="ntp040InitYear" type="java.lang.String"/>
                       <bean:define id="actionInitMonth" name="ntp040Form" property="ntp040InitMonth" type="java.lang.String"/>
                       <bean:define id="actionInitDay" name="ntp040Form" property="ntp040InitDay" type="java.lang.String"/>
                       <% dataActionYear  =  Integer.parseInt(actionInitYear); %>
                       <% dataActionMonth =  Integer.parseInt(actionInitMonth); %>
                       <% dataActionDay   =  Integer.parseInt(actionInitDay); %>
                     </logic:equal>


                     <select name="<%= selActionYearIdStr %>" id="<%= selActionYearIdStr.toString() %>">
                       <logic:iterate id="yearLv" name="ntp040Form" property="ntp040YearLavel">
                         <logic:equal name="yearLv" property="value" value="<%= dataActionYear.toString() %>">
                           <option value="<bean:write name="yearLv" property="value" />" selected="selected"><bean:write name="yearLv" property="label" /></option>
                         </logic:equal>
                         <logic:notEqual name="yearLv" property="value" value="<%= dataActionYear.toString() %>">
                           <option value="<bean:write name="yearLv" property="value" />"><bean:write name="yearLv" property="label" /></option>
                         </logic:notEqual>
                       </logic:iterate>
                     </select>

                     <select name="<%= selActionMonthIdStr %>" id="<%= selActionMonthIdStr %>">
                       <logic:iterate id="monthLv" name="ntp040Form" property="ntp040MonthLavel">
                         <logic:equal name="monthLv" property="value" value="<%= dataActionMonth.toString() %>">
                           <option value="<bean:write name="monthLv" property="value" />" selected="selected"><bean:write name="monthLv" property="label" /></option>
                         </logic:equal>
                         <logic:notEqual name="monthLv" property="value" value="<%= dataActionMonth.toString() %>">
                           <option value="<bean:write name="monthLv" property="value" />"><bean:write name="monthLv" property="label" /></option>
                         </logic:notEqual>
                       </logic:iterate>
                     </select>

                     <select name="<%= selActionDayIdStr %>" id="<%= selActionDayIdStr %>">
                       <logic:iterate id="dayLv" name="ntp040Form" property="ntp040DayLavel">
                         <logic:equal name="dayLv" property="value" value="<%= dataActionDay.toString() %>">
                           <option value="<bean:write name="dayLv" property="value" />" selected="selected"><bean:write name="dayLv" property="label" /></option>
                         </logic:equal>
                         <logic:notEqual name="dayLv" property="value" value="<%= dataActionDay.toString() %>">
                           <option value="<bean:write name="dayLv" property="value" />"><bean:write name="dayLv" property="label" /></option>
                         </logic:notEqual>
                       </logic:iterate>
                     </select>

                    <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#<%= selActionYearIdStr.toString() %>')[0], $('#<%= selActionMonthIdStr.toString() %>')[0], $('#<%= selActionDayIdStr.toString() %>')[0], 1)">
                    <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#<%= selActionYearIdStr.toString() %>')[0], $('#<%= selActionMonthIdStr.toString() %>')[0], $('#<%= selActionDayIdStr.toString() %>')[0], 2)">
                    <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#<%= selActionYearIdStr.toString() %>')[0], $('#<%= selActionMonthIdStr.toString() %>')[0], $('#<%= selActionDayIdStr.toString() %>')[0], 3)">
                    <input type="button" value="Cal" onclick="wrtCalendarByBtn(this.form.<%= selActionDayIdStr.toString() %>, this.form.<%= selActionMonthIdStr.toString() %>, this.form.<%= selActionYearIdStr.toString() %>, 'ntp040ActionCalBtn<%= idx + 1 %>')" class="calendar_btn" id="ntp040ActionCalBtn<%= idx + 1 %>">

                    <br>

                  </div>

                  <textarea id="actionstr_<%= idx + 1 %>" name="ntp040NextAction_<%= idx + 1 %>" style="width:431px;" rows="2" onkeyup="showLengthStr(value, 1000, 'actionlength<%= idx + 1 %>');"><bean:write name="dataMdl" property="actionStr" /></textarea>
                  <br>
                  <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="actionlength<%= idx + 1 %>" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthContent %>&nbsp;<gsmsg:write key="cmn.character" /></span>
                </div>
              </td>
              </tr>
            </logic:equal>

            <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.registant" /></span></td>
            <td align="left"  class="<%= tdColor %> ntp_add_td" colspan="3">
            <table class="tl0" width="100%">
            <tr>
            <td width="60%" nowrap>
            <span class="text_base">
            <logic:notEqual name="ntp040Form" property="ntp040AddUsrJkbn" value="9">
            <span class="addUserName_<%= idx + 1 %>"><bean:write name="dataMdl" property="ntp040NtpAddUsrName" /></span>
            </logic:notEqual>
            <logic:equal name="ntp040Form" property="ntp040AddUsrJkbn" value="9">
            <del>
            <span class="addUserName_<%= idx + 1 %>"><bean:write name="dataMdl" property="ntp040NtpAddUsrName" /></span>
            </del>
            </logic:equal>
            </span>
            </td>
            <td width="40%" align="left" nowrap>
            <span class="text_base">
            <span class="addDate_<%= idx + 1 %>"><bean:write name="dataMdl" property="ntp040NtpDate" /></span>
            </span>
            </td>
            </tr>
            </table>
            </td>
            </tr>

            <tr>
            <td class="td_wt" colspan="4" style="padding-left:350px;background-color:#f6f6f6;">
            <div style="float:left;"><span id="goodBtnArea_<bean:write name="dataMdl" property="ntp040NtpSid" />"><logic:equal name="dataMdl" property="ntp040GoodFlg" value="0"><input id="<bean:write name="dataMdl" property="ntp040NtpSid" />" style="border:0px;color:#000066;font-size:10px;font-weight:bold;width:60px;height:17px;" class="ntp_good_btn goodLink" value="いいね!" type="button"></logic:equal><logic:notEqual name="dataMdl" property="ntp040GoodFlg" value="0"><span class="text_already_good">いいね!しています</span></logic:notEqual></span></div>
            <div style="float:left;padding-left:4px;padding-top:2px;">
              <table>
                <tr>
                  <td align="center" class="text_good" id="<bean:write name="dataMdl" property="ntp040NtpSid" />">
                    <span id="goodCntArea_<bean:write name="dataMdl" property="ntp040NtpSid" />"><bean:write name="dataMdl" property="ntp040GoodCnt" /></span>
                  </td>
                </tr>
              </table>
            </div>

            </td>
            </tr>



            <logic:notEmpty name="dataMdl" property="ntp040CommentList">

              <tr class="ntp040DspComment_tr_<%= idx + 1 %>">
                <td id="ntp040DspComment_<%= idx + 1 %>" class="<%= tdColor %>" colspan="4">

                  <span class="commentDspArea<%= idx + 1 %>">
                  <logic:iterate id="npcMdl" name="dataMdl" property="ntp040CommentList">
                    <bean:define id="usrInfMdl" name="npcMdl" property="ntp040UsrInfMdl"/>
                    <bean:define id="ntpCmtMdl" name="npcMdl" property="ntp040CommentMdl"/>

                    <table class="commentDspAreaTable_<%= idx + 1 %>_<bean:write name="ntpCmtMdl" property="npcSid" />">
                      <tr>
                        <td>

                          <logic:equal name="usrInfMdl" property="binSid" value="0">
                            <img  class="comment_Img" src="../user/images/photo.gif" name="userImage" alt="<gsmsg:write key="cmn.photo" />" border="1" width="50px" />
                          </logic:equal>

                          <logic:notEqual name="usrInfMdl" property="binSid" value="0">
                            <logic:equal name="usrInfMdl" property="usiPictKf" value="1">
                              <div align="center" class="photo_hikokai2"><span style="color:#fc2929;"><gsmsg:write key="cmn.private" /></span></div>
                            </logic:equal>
                            <logic:notEqual name="usrInfMdl" property="usiPictKf" value="1">
                              <img class="comment_Img" src="../common/cmn100.do?CMD=getImageFile&cmn100binSid=<bean:write name="usrInfMdl" property="binSid" />" alt="<gsmsg:write key="cmn.photo" />" width="50px" />
                            </logic:notEqual>
                          </logic:notEqual>
                        </td>
                        <td valign="top" style="padding-left:10px;" id="commentDspAreaTable_<%= idx + 1 %>_<bean:write name="ntpCmtMdl" property="npcSid" />">
                          <logic:notEqual name="usrInfMdl" property="usrJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
	                          <span style="font-size:12px;color:#333333;"><b><bean:write name="usrInfMdl" property="usiSei"/>&nbsp;<bean:write name="usrInfMdl" property="usiMei"/></b></span>
	                          &nbsp;<span style="font-size:12px;color:#333333;"><bean:write name="npcMdl" property="ntp040CommentDate" filter="false"/></span>
                          </logic:notEqual>
                          <logic:equal name="usrInfMdl" property="usrJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
	                          <del><span style="font-size:12px;color:#333333;"><b><bean:write name="usrInfMdl" property="usiSei"/>&nbsp;<bean:write name="usrInfMdl" property="usiMei"/></b></span></del>
	                          &nbsp;<span style="font-size:12px;color:#333333;"><bean:write name="npcMdl" property="ntp040CommentDate" filter="false"/></span>
                          </logic:equal>
                          <logic:equal name="npcMdl" property="ntp040CommentDelFlg" value="1">
                          &nbsp;&nbsp;<span class="commentDel" id="<bean:write name="ntpCmtMdl" property="npcSid" />"><img src="../nippou/images/delete_icon2.gif" alt="削除" /></span></logic:equal><br><span style="font-size:13px;color:#333333;"><bean:write name="ntpCmtMdl" property="npcComment" filter="false" /></span>
                        </td>
                      </tr>
                    </table>

                    <hr class="commentDspAreaTable_<%= idx + 1 %>_<bean:write name="ntpCmtMdl" property="npcSid" />" align="center" width="90%" style="color:#e9e9e9;">

                  </logic:iterate>
                  </span>

                </td>
              </tr>

            </logic:notEmpty>

            <logic:empty name="dataMdl" property="ntp040CommentList">
              <tr class="ntp040DspComment_tr_<%= idx + 1 %> ntp040DspComment_tr_none">
                <td id="ntp040DspComment_<%= idx + 1 %>" class="<%= tdColor %>" colspan="4"></td>
              </tr>
            </logic:empty>


            <tr class="commentArea<%= idx + 1 %>">
            <td class="<%= tdColor %> ntp_add_td" colspan="4">
              <logic:notEmpty name="ntp040Form" property="ntp040UsrInfMdl">
              <bean:define id="usrInf" name="ntp040Form" property="ntp040UsrInfMdl"/>
              <table>
                <tr>
                  <td>

                    <logic:equal name="usrInf" property="binSid" value="0">
                      <img  class="comment_Img" src="../user/images/photo.gif" name="userImage" alt="<gsmsg:write key="cmn.photo" />" border="1" width="50px" />
                    </logic:equal>

                    <logic:notEqual name="usrInf" property="binSid" value="0">
                      <logic:equal name="usrInf" property="usiPictKf" value="1">
                        <div align="center" class="photo_hikokai2"><span style="color:#fc2929;"><gsmsg:write key="cmn.private" /></span></div>
                      </logic:equal>
                      <logic:notEqual name="usrInf" property="usiPictKf" value="1">
                        <img class="comment_Img" src="../common/cmn100.do?CMD=getImageFile&cmn100binSid=<bean:write name="usrInf" property="binSid" />" alt="<gsmsg:write key="cmn.photo" />" width="50px" />
                      </logic:notEqual>
                    </logic:notEqual>

                  </td>
                  <td valign="middle" style="padding-left:10px;">
                    <div class="textfield">
                      <label class="label_area" style="opacity:1;font-size:12px;color:a3a3a3;" for="field_id<%= idx + 1 %>">コメントする</label>
                      <textarea name="ntp040Comment_<%= idx + 1 %>" rows="3" style="height:50px;width:431px;" id="field_id<%= idx + 1 %>"></textarea>
                    </div>
                  </td>
                  <td valign="middle" id="<%= idx + 1 %>"><input type="button" class="btn_base_toukou" id="<bean:write name="dataMdl" property="ntp040NtpSid" />" name="commentBtn" value="投稿" /></td>
                </tr>
              </table>
              </logic:notEmpty>
            </td>
            </tr>

           <logic:equal name="ntp040Form" property="ntp040AnkenCompanyUse" value="3">
              <tr><td></td><td></td><td></td><td></td></tr>
            </logic:equal>

            <tr class="commentArea<%= idx + 1 %>" style="display:none;"></tr>

            </td></tr></table>
            </td>
            </tr>

        </logic:iterate>

      </logic:notEmpty>

      <logic:empty name="ntp040Form" property="ntp040DataModelList">
        <tr id="ntpEmptyArea" class="ntpEmptyArea" align="center">
          <td>
            <span style="font-size:14px;color:#ff0000;font-weight:bold;">該当する日報がありません。</span>
          </td>
        </tr>
      </logic:empty>

      <input type="hidden" id="editLastRowNum" value="<%= lastRowNumber %>" />