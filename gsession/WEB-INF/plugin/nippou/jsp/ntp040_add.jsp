<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

      <tr>
        <td colspan="6" align="right">
          <logic:equal name="ntp040Form" property="scheduleUseOk" value="0">
          <input value="スケジュールデータ取込" class="sch_data_btn schDataGetBtn" type="button">
          </logic:equal>
          <logic:equal name="ntp040Form" property="projectUseOk" value="0">
          <input value="プロジェクトデータ取込" class="prj_data_btn prjDataGetBtn" type="button">
          </logic:equal>
          <br>
          <img src="../schedule/images/spacer.gif" width="1px" border="0" height="10px">
        </td>
      </tr>

      <tr class="tr_nippou" id="">
      <td colspan="2" width="0%" class="nippou_info_bg_left"><div id="pos1"></div>NO,1</td>
      <td colspan="3" align="right" class="nippou_info_bg">
      </td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1">時間</span><span class="text_r2">※</span></td>
      <td align="left" width="90%" class="<%= tdColor %> ntp_add_td" colspan="3">


       <html:select property="ntp040FrHour" value="<%= frhourval %>">
          <html:optionsCollection name="ntp040Form" property="ntp040HourLavel" value="value" label="label" />
       </html:select>
       <gsmsg:write key="cmn.hour.input" />
       <html:select property="ntp040FrMin" value="<%= frminval %>">
          <html:optionsCollection name="ntp040Form" property="ntp040MinuteLavel" value="value" label="label" />
       </html:select>
       <gsmsg:write key="cmn.minute.input" />
       ～
       <html:select property="ntp040ToHour" value="<%= tohourval %>">
          <html:optionsCollection name="ntp040Form" property="ntp040HourLavel" value="value" label="label" />
       </html:select>
       <gsmsg:write key="cmn.hour.input" />
       <html:select property="ntp040ToMin" value="<%= tominval %>">
          <html:optionsCollection name="ntp040Form" property="ntp040MinuteLavel" value="value" label="label" />
       </html:select>
       <gsmsg:write key="cmn.minute.input" />
         &nbsp;&nbsp;&nbsp;&nbsp;<span id="betWeenDays" class="text_base"></span>
      </td>
      </tr>

      <logic:equal name="ntp040Form" property="ntp040AnkenCompanyUse" value="0">
        <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1">案件</span><span class="text_r2"></span></td>
        <td width="30%" align="left" class="<%= tdColor %>  ntp_add_td" nowrap>
        <input type="button" class="btn_search_n1" value="案件検索" onclick="return openAnkenWindow('ntp040','')" />
        <input type="button" class="ankenHistoryPop btn_history" id="" value="履歴" /><br>
          <img src="../schedule/images/spacer.gif" width="1px" border="0" height="10px">
          <logic:equal name="ntp040Form" property="ntp040AnkenSid" value="-1">
            <div id="ntp040AnkenIdArea"></div>
            <div id="ntp040AnkenCodeArea"></div>
            <div id="ntp040AnkenNameArea"></div>
          </logic:equal>

          <logic:equal name="ntp040Form" property="ntp040AnkenSid" value="0">
            <div id="ntp040AnkenIdArea"></div>
            <div id="ntp040AnkenCodeArea"></div>
            <div id="ntp040AnkenNameArea"></div>
          </logic:equal>

          <logic:notEqual name="ntp040Form" property="ntp040AnkenSid" value="-1">
            <logic:notEqual name="ntp040Form" property="ntp040AnkenSid" value="0">
              <div id="ntp040AnkenIdArea">
                <input name="ntp040AnkenSid" value="<bean:write name="ntp040Form" property="ntp040AnkenSid" />" type="hidden">
              </div>

              <div id="ntp040AnkenCodeArea">
                <span class="text_anken_code">案件コード：<span class="anken_code_name"><bean:write name="ntp040Form" property="ntp040AnkenCode" /></span></span>
              </div>

              <div id="ntp040AnkenNameArea">
                <div class="text_anken">
                  <a id="<bean:write name="ntp040Form" property="ntp040AnkenSid" />" class="sc_link anken_click">
                    <span class="anken_name"><bean:write name="ntp040Form" property="ntp040AnkenName" /></span>
                  </a>
                </div>

                <a href="javascript:void(0);" onclick="delAnken('ntp040','');">
                  <img src="../common/images/delete.gif" class="img_bottom" alt="" width="15">
                </a>
              </div>
            </logic:notEqual>
          </logic:notEqual>
        </td>

        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1">企業・顧客</span><span class="text_r2"></span></td>
        <td align="left" class="<%= tdColor %>  ntp_add_td" >
          <input type="button" class="btn_address_n2" value="<gsmsg:write key="addressbook" />" onclick="return openCompanyWindow2('ntp040','')" />
          <input type="button" class="adrHistoryPop btn_history" id="" value="履歴" /><br>
          <img src="../schedule/images/spacer.gif" width="1px" border="0" height="10px">
          <logic:equal name="ntp040Form" property="ntp040CompanySid" value="-1">
            <div id="ntp040CompanyIdArea"></div>
            <div id="ntp040CompanyBaseIdArea"></div>
            <div id="ntp040CompanyCodeArea"></div>
            <div id="ntp040CompNameArea"></div>
          </logic:equal>

          <logic:equal name="ntp040Form" property="ntp040CompanySid" value="0">
            <div id="ntp040CompanyIdArea"></div>
            <div id="ntp040CompanyBaseIdArea"></div>
            <div id="ntp040CompanyCodeArea"></div>
            <div id="ntp040CompNameArea"></div>
          </logic:equal>


          <logic:notEqual name="ntp040Form" property="ntp040CompanySid" value="-1">
          <logic:notEqual name="ntp040Form" property="ntp040CompanySid" value="0">
            <div id="ntp040CompanyIdArea">
              <input name="ntp040CompanySid" value="<bean:write name="ntp040Form" property="ntp040CompanySid" />" type="hidden">
            </div>

            <div id="ntp040CompanyBaseIdArea">
              <logic:notEqual name="ntp040Form" property="ntp040CompanyBaseSid" value="-1">
              <logic:notEqual name="ntp040Form" property="ntp040CompanyBaseSid" value="0">
                <input name="ntp040CompanyBaseSid" value="<bean:write name="ntp040Form" property="ntp040CompanyBaseSid" />" type="hidden">
              </logic:notEqual>
              </logic:notEqual>
            </div>

            <div id="ntp040CompanyCodeArea">
              <span class="text_anken_code">企業コード：<span class="comp_code_name"><bean:write name="ntp040Form" property="ntp040CompanyCode" /></span></span>
            </div>

            <div id="ntp040CompNameArea">
              <div class="text_company">
                <a id="<bean:write name="ntp040Form" property="ntp040CompanySid" />" class="sc_link comp_click">
                  <span class="comp_name"><bean:write name="ntp040Form" property="ntp040CompanyName" />&nbsp;
                    <logic:notEqual name="ntp040Form" property="ntp040CompanyBaseSid" value="-1">
                    <logic:notEqual name="ntp040Form" property="ntp040CompanyBaseSid" value="0">
                      <bean:write name="ntp040Form" property="ntp040CompanyBaseName" />
                    </logic:notEqual>
                    </logic:notEqual>
                  </span>
                </a>
              </div>
              <a href="javascript:void(0);" onclick="delCompany('ntp040','');">
                <img src="../common/images/delete.gif" class="img_bottom" alt="" width="15">
              </a>
            </div>
          </logic:notEqual>
          </logic:notEqual>

          <div id="ntp040AddressIdArea"></div>
          <div id="ntp040AddressNameArea"></div>
        </td>
        </tr>
      </logic:equal>


      <logic:equal name="ntp040Form" property="ntp040AnkenCompanyUse" value="1">
       <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1">案件</span><span class="text_r2"></span></td>
        <td align="left" colspan="3" class="<%= tdColor %> ntp_add_td" nowrap>
        <input type="button" class="btn_search_n1" value="案件検索" onclick="return openAnkenWindow('ntp040','')" />
        <input type="button" class="ankenHistoryPop btn_history" id="" value="履歴" /><br>
          <img src="../schedule/images/spacer.gif" width="1px" border="0" height="10px">

          <logic:equal name="ntp040Form" property="ntp040AnkenSid" value="-1">
            <div id="ntp040AnkenIdArea"></div>
            <div id="ntp040AnkenCodeArea"></div>
            <div id="ntp040AnkenNameArea"></div>
          </logic:equal>

         <logic:equal name="ntp040Form" property="ntp040AnkenSid" value="0">
            <div id="ntp040AnkenIdArea"></div>
            <div id="ntp040AnkenCodeArea"></div>
            <div id="ntp040AnkenNameArea"></div>
          </logic:equal>

          <logic:notEqual name="ntp040Form" property="ntp040AnkenSid" value="-1">
            <logic:notEqual name="ntp040Form" property="ntp040AnkenSid" value="0">
              <div id="ntp040AnkenIdArea">
                <input name="ntp040AnkenSid" value="<bean:write name="ntp040Form" property="ntp040AnkenSid" />" type="hidden">
              </div>

              <div id="ntp040AnkenCodeArea">
                <span class="text_anken_code">案件コード：<span class="anken_code_name"><bean:write name="ntp040Form" property="ntp040AnkenCode" /></span></span>
              </div>

              <div id="ntp040AnkenNameArea">
                <div class="text_anken">
                  <a id="<bean:write name="ntp040Form" property="ntp040AnkenSid" />" class="sc_link anken_click">
                    <span class="anken_name"><bean:write name="ntp040Form" property="ntp040AnkenName" /></span>
                  </a>
                </div>

                <a href="javascript:void(0);" onclick="delAnken('ntp040','');">
                  <img src="../common/images/delete.gif" class="img_bottom" alt="" width="15">
                </a>
              </div>
            </logic:notEqual>
          </logic:notEqual>


        </td>
        </tr>
      </logic:equal>

      <logic:equal name="ntp040Form" property="ntp040AnkenCompanyUse" value="2">
        <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1">企業・顧客</span><span class="text_r2"></span></td>
        <td align="left" colspan="3" class="<%= tdColor %> ntp_add_td">
          <input type="button" class="btn_address_n2" value="<gsmsg:write key="addressbook" />" onclick="return openCompanyWindow2('ntp040','')" />
          <input type="button" class="adrHistoryPop btn_history" id="" value="履歴" /><br>
          <img src="../schedule/images/spacer.gif" width="1px" border="0" height="10px">



          <logic:equal name="ntp040Form" property="ntp040CompanySid" value="-1">
            <div id="ntp040CompanyIdArea"></div>
            <div id="ntp040CompanyBaseIdArea"></div>
            <div id="ntp040CompanyCodeArea"></div>
            <div id="ntp040CompNameArea"></div>
          </logic:equal>


          <logic:equal name="ntp040Form" property="ntp040CompanySid" value="0">
            <div id="ntp040CompanyIdArea"></div>
            <div id="ntp040CompanyBaseIdArea"></div>
            <div id="ntp040CompanyCodeArea"></div>
            <div id="ntp040CompNameArea"></div>
          </logic:equal>



          <logic:notEqual name="ntp040Form" property="ntp040CompanySid" value="-1">
          <logic:notEqual name="ntp040Form" property="ntp040CompanySid" value="0">
            <div id="ntp040CompanyIdArea">
              <input name="ntp040CompanySid" value="<bean:write name="ntp040Form" property="ntp040CompanySid" />" type="hidden">
            </div>

            <div id="ntp040CompanyBaseIdArea">
              <logic:notEqual name="ntp040Form" property="ntp040CompanyBaseSid" value="-1">
              <logic:notEqual name="ntp040Form" property="ntp040CompanyBaseSid" value="0">
                <input name="ntp040CompanyBaseSid" value="<bean:write name="ntp040Form" property="ntp040CompanyBaseSid" />" type="hidden">
              </logic:notEqual>
              </logic:notEqual>
            </div>

            <div id="ntp040CompanyCodeArea">
              <span class="text_anken_code">企業コード：<span class="comp_code_name"><bean:write name="ntp040Form" property="ntp040CompanyCode" /></span></span>
            </div>

            <div id="ntp040CompNameArea">
              <div class="text_company">
                <a id="<bean:write name="ntp040Form" property="ntp040CompanySid" />" class="sc_link comp_click">
                  <span class="comp_name"><bean:write name="ntp040Form" property="ntp040CompanyName" />&nbsp;
                    <logic:notEqual name="ntp040Form" property="ntp040CompanyBaseSid" value="-1">
                    <logic:notEqual name="ntp040Form" property="ntp040CompanyBaseSid" value="0">
                      <bean:write name="ntp040Form" property="ntp040CompanyBaseName" />
                    </logic:notEqual>
                    </logic:notEqual>
                  </span>
                </a>
              </div>
              <a href="javascript:void(0);" onclick="delCompany('ntp040','');">
                <img src="../common/images/delete.gif" class="img_bottom" alt="" width="15">
              </a>
            </div>
          </logic:notEqual>
          </logic:notEqual>


          <div id="ntp040AddressIdArea"></div>
          <div id="ntp040AddressNameArea"></div>

        </td>
        </tr>
      </logic:equal>


      <logic:equal name="ntp040Form" property="ntp040KtBriHhuUse" value="0">
        <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1">活動分類/方法</span></td>
        <td align="left" colspan="3" class="<%= tdColor %> ntp_add_td">
         <logic:notEmpty name="ntp040Form" property="ntp040KtbunruiLavel">
           <html:select property="ntp040Ktbunrui">
              <html:optionsCollection name="ntp040Form" property="ntp040KtbunruiLavel" value="value" label="label" />
           </html:select>
         </logic:notEmpty>

         <logic:notEmpty name="ntp040Form" property="ntp040KthouhouLavel">
           <html:select property="ntp040Kthouhou">
              <html:optionsCollection name="ntp040Form" property="ntp040KthouhouLavel" value="value" label="label" />
           </html:select>
         </logic:notEmpty>
         </td>
        </tr>
      </logic:equal>


      <logic:equal name="ntp040Form" property="ntp040MikomidoUse" value="0">
        <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1">見込み度</span>
        </td>
        <td align="left" colspan="3" class="<%= tdColor %> ntp_add_td">
          <span class="text_base">
            <html:radio name="ntp040Form" property="ntp040Mikomido" styleId="ntp040Mikomido0" value="0" /><span class="text_base2"><label for="ntp040Mikomido0">10%</label></span>
            <html:radio name="ntp040Form" property="ntp040Mikomido" styleId="ntp040Mikomido1" value="1" /><span class="text_base2"><label for="ntp040Mikomido1">30%</label></span>
            <html:radio name="ntp040Form" property="ntp040Mikomido" styleId="ntp040Mikomido2" value="2" /><span class="text_base2"><label for="ntp040Mikomido2">50%</label></span>
            <html:radio name="ntp040Form" property="ntp040Mikomido" styleId="ntp040Mikomido3" value="3" /><span class="text_base2"><label for="ntp040Mikomido3">70%</label></span>
            <html:radio name="ntp040Form" property="ntp040Mikomido" styleId="ntp040Mikomido4" value="4" /><span class="text_base2"><label for="ntp040Mikomido4">100%</label></span>
          </span>
          <logic:notEqual name="ntp040Form" property="ntp040MikomidoFlg" value="0">
            <br>&nbsp;<input class="mikomido_btn mikomido_back" type="button" value="基 準" />
          </logic:notEqual>
        </td>
        </tr>
      </logic:equal>

      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.title" /></span><span class="text_r2">※</span></td>
      <td align="left" class="<%= tdColor %> ntp_add_td" colspan="3">
      <html:text name="ntp040Form" maxlength="100" property="ntp040Title" styleId="ntpTitleTextBox" styleClass="text_base" style="width:337px;" />

      <bean:define id="colorMsg1" value=""/>
      <bean:define id="colorMsg2" value=""/>
      <bean:define id="colorMsg3" value=""/>
      <bean:define id="colorMsg4" value=""/>
      <bean:define id="colorMsg5" value=""/>
      <logic:iterate id="msgStr" name="ntp040Form" property="ntp040ColorMsgList" indexId="msgId" type="java.lang.String">
      <logic:equal name="msgId" value="0">
      <% colorMsg1 = msgStr; %>
      </logic:equal>
      <logic:equal name="msgId" value="1">
      <% colorMsg2 = msgStr; %>
      </logic:equal>
      <logic:equal name="msgId" value="2">
      <% colorMsg3 = msgStr; %>
      </logic:equal>
      <logic:equal name="msgId" value="3">
      <% colorMsg4 = msgStr; %>
      </logic:equal>
      <logic:equal name="msgId" value="4">
      <% colorMsg5 = msgStr; %>

      </logic:equal>
      </logic:iterate>

      <input type="hidden" id="msgCol1" value="<%= colorMsg1 %>" />
      <input type="hidden" id="msgCol2" value="<%= colorMsg2 %>" />
      <input type="hidden" id="msgCol3" value="<%= colorMsg3 %>" />
      <input type="hidden" id="msgCol4" value="<%= colorMsg4 %>" />
      <input type="hidden" id="msgCol5" value="<%= colorMsg5 %>" />

      <span class="sc_block_color_1"><html:radio name="ntp040Form" property="ntp040Bgcolor" value="1" styleId="bg_color1"/></span>
      <label for="bg_color1" class="text_base"><%= colorMsg1 %></label>
      <span class="sc_block_color_2"><html:radio name="ntp040Form" property="ntp040Bgcolor" value="2" styleId="bg_color2" /></span>
      <label for="bg_color2" class="text_base"><%= colorMsg2 %></label>
      <span class="sc_block_color_3"><html:radio name="ntp040Form" property="ntp040Bgcolor" value="3" styleId="bg_color3" /></span>
      <label for="bg_color3" class="text_base"><%= colorMsg3 %></label>
      <span class="sc_block_color_4"><html:radio name="ntp040Form" property="ntp040Bgcolor" value="4" styleId="bg_color4" /></span>
      <label for="bg_color4" class="text_base"><%= colorMsg4 %></label>
      <span class="sc_block_color_5"><html:radio name="ntp040Form" property="ntp040Bgcolor" value="5" styleId="bg_color5" /></span>
      <label for="bg_color5" class="text_base"><%= colorMsg5 %></label>

      </td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.content2" /><a id="naiyou" name="naiyou"></a></span></td>
      <td align="left" class="<%= tdColor %> ntp_add_td" colspan="3">
      <textarea name="ntp040Value" style="width:431px;" rows="5" class="text_base" onkeyup="showLengthStr(value, <%= maxLengthContent %>, 'inputlength');" id="inputstr" <%= valueFocusEvent %>><bean:write name="ntp040Form" property="ntp040Value" /></textarea>
      <br>
      <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthContent %>&nbsp;<gsmsg:write key="cmn.character" /></span>
      </td>
      </tr>

      <logic:equal name="ntp040Form" property="ntp040TmpFileUse" value="0">
        <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1">添付<a id="naiyou" name="naiyou"></a></span></td>
        <td align="left" class="<%= tdColor %> ntp_add_td" colspan="3">
          <select name="ntp040selectFiles1" multiple="multiple" size="3" class="select01"></select>
          <input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTempPlus('ntp040selectFiles1', 'nippou', '0', '0', 'row1');">&nbsp;
          <input type="button" class="btn_delete" name="tempDelBtn" id="1" value="削除">
        </td>
        </tr>
      </logic:equal>

      <logic:equal name="ntp040Form" property="ntp040NextActionUse" value="0">
      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1">次のアクション<a id="nextaction" name="nextaction"></a></span></td>
      <td align="left" class="<%= tdColor %> ntp_add_td" colspan="3">
      <span style="color:#000000;font-size:12px;font-weight:bold;">&nbsp;日付指定：</span>
      <html:radio name="ntp040Form" property="ntp040ActDateKbn" value="1" styleId="actDate1" onclick="toggleActionAreaShow('nxtActDateArea');"/>
      <label for="actDate1" class="text_base" style="color:#000000;font-size:12px;">する</label>
      <html:radio name="ntp040Form" property="ntp040ActDateKbn" value="0" styleId="actDate0" onclick="toggleActionAreaHide('nxtActDateArea');"/>
      <label for="actDate0" class="text_base" style="color:#000000;font-size:12px;">しない</label>

      <logic:equal name="ntp040Form" property="ntp040ActDateKbn" value="0">
      <div id="nxtActDateArea" style="display:none;">
      </logic:equal>

      <logic:equal name="ntp040Form" property="ntp040ActDateKbn" value="1">
      <div id="nxtActDateArea">
      </logic:equal>

        <html:select property="ntp040NxtActYear" styleId="selActionYear">
          <html:optionsCollection name="ntp040Form" property="ntp040YearLavel" value="value" label="label" />
        </html:select>

        <html:select property="ntp040NxtActMonth" styleId="selActionMonth">
          <html:optionsCollection name="ntp040Form" property="ntp040MonthLavel" value="value" label="label" />
        </html:select>

        <html:select property="ntp040NxtActDay" styleId="selActionDay">
          <html:optionsCollection name="ntp040Form" property="ntp040DayLavel" value="value" label="label" />
        </html:select>

        <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#selActionYear')[0], $('#selActionMonth')[0], $('#selActionDay')[0], 1)">
        <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#selActionYear')[0], $('#selActionMonth')[0], $('#selActionDay')[0], 2)">
        <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#selActionYear')[0], $('#selActionMonth')[0], $('#selActionDay')[0], 3)">
        <input type="button" value="Cal" onclick="wrtCalendarByBtn(this.form.selActionDay, this.form.selActionMonth, this.form.selActionYear, 'ntp040ActionCalBtn')" class="calendar_btn" id="ntp040ActionCalBtn">

      </div>

      <div>
        <textarea name="ntp040NextAction" style="width:431px;" rows="2" class="text_base" onkeyup="showLengthStr(value, <%= maxLengthContent %>, 'actionlength');" id="actionstr" <%= valueFocusEvent %>><bean:write name="ntp040Form" property="ntp040NextAction" /></textarea>
        <br>
        <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="actionlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthContent %>&nbsp;<gsmsg:write key="cmn.character" /></span>
      </div>

      </td>
      </tr>
      </logic:equal>