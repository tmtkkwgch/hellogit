<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>


      <br>



      <div class="font_small">■<span class="text_bb1">時間</span><span class="text_r2">※</span></div><br>


<div data-role="navbar" align="center">
  <ul>
    <li>
       <html:select property="ntp040FrHour" value="<%= frhourval %>">
          <html:optionsCollection name="mbhNtp040Form" property="ntp040HourLavel" value="value" label="label" />
       </html:select>
       <gsmsg:write key="cmn.hour.input" />
    </li>
    <li>
       <html:select property="ntp040FrMin" value="<%= frminval %>">
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
       <html:select property="ntp040ToHour" value="<%= tohourval %>">
          <html:optionsCollection name="mbhNtp040Form" property="ntp040HourLavel" value="value" label="label" />
       </html:select>
       <gsmsg:write key="cmn.hour.input" />
    </li>
    <li>
       <html:select property="ntp040ToMin" value="<%= tominval %>">
          <html:optionsCollection name="mbhNtp040Form" property="ntp040MinuteLavel" value="value" label="label" />
       </html:select>
       <gsmsg:write key="cmn.minute.input" />
   </li>
  </ul>
</div>
         &nbsp;&nbsp;&nbsp;&nbsp;<span id="betWeenDays" class="text_base"></span>


      <logic:equal name="mbhNtp040Form" property="ntp040AnkenCompanyUse" value="0">
        <br>
        <div class="font_small">■<span class="text_bb1"><gsmsg:write key="ntp.11" /></span></div><span class="text_r2"></span>
        <br>

        <input type="submit" class="" value="<gsmsg:write key="ntp.11" /><gsmsg:write key="cmn.search" />" name="ntp040adr" /><br>

          <img src="../schedule/images/spacer.gif" width="1px" border="0" height="10px">
          <logic:equal name="mbhNtp040Form" property="ntp040AnkenSid" value="-1">
            <div id="ntp040AnkenIdArea"></div>
            <div id="ntp040AnkenCodeArea"></div>
            <div id="ntp040AnkenNameArea"></div>
          </logic:equal>

          <logic:equal name="mbhNtp040Form" property="ntp040AnkenSid" value="0">
            <div id="ntp040AnkenIdArea"></div>
            <div id="ntp040AnkenCodeArea"></div>
            <div id="ntp040AnkenNameArea"></div>
          </logic:equal>

          <logic:notEqual name="mbhNtp040Form" property="ntp040AnkenSid" value="-1">
            <logic:notEqual name="mbhNtp040Form" property="ntp040AnkenSid" value="0">
              <div id="ntp040AnkenIdArea">
                <input name="ntp040AnkenSid" value="<bean:write name="mbhNtp040Form" property="ntp040AnkenSid" />" type="hidden">
              </div>

              <div id="ntp040AnkenCodeArea">
                <span class="text_anken_code"><gsmsg:write key="ntp.29" />：<span class="anken_code_name"><bean:write name="mbhNtp040Form" property="ntp040AnkenCode" /></span></span>
              </div>

              <div id="ntp040AnkenNameArea">
                <span class="text_anken">
                  <a id="<bean:write name="mbhNtp040Form" property="ntp040AnkenSid" />" class="sc_link anken_click">
                    <span class="anken_name"><bean:write name="mbhNtp040Form" property="ntp040AnkenName" /></span>
                  </a>
                </span>

                <div align="center">
                  <font size="-2"><input type="submit" name="ntp040ankendel" class="" value="<gsmsg:write key="cmn.delete" />" data-icon="delete" data-inline="true" /></font>
                </div>

              </div>
            </logic:notEqual>
          </logic:notEqual>


        <br>
        <div class="font_small">■<span class="text_bb1"><gsmsg:write key="ntp.15" />・<gsmsg:write key="ntp.16" /></span></div><span class="text_r2"></span>
        <br>
        <div data-role="controlgroup" data-type="horizontal" align="center">
          <input type="submit" class="" value="<gsmsg:write key="addressbook" />" name="ntp040adrbook" data-inline="true" />
          <input type="submit" class="" value="<gsmsg:write key="ntp.17" />" name="ntp040rireki" data-inline="true" />
        </div>
          <img src="../schedule/images/spacer.gif" width="1px" border="0" height="10px">
          <logic:equal name="mbhNtp040Form" property="ntp040CompanySid" value="-1">
            <div id="ntp040CompanyIdArea"></div>
            <div id="ntp040CompanyBaseIdArea"></div>
            <div id="ntp040CompanyCodeArea"></div>
            <div id="ntp040CompNameArea"></div>
          </logic:equal>

          <logic:equal name="mbhNtp040Form" property="ntp040CompanySid" value="0">
            <div id="ntp040CompanyIdArea"></div>
            <div id="ntp040CompanyBaseIdArea"></div>
            <div id="ntp040CompanyCodeArea"></div>
            <div id="ntp040CompNameArea"></div>
          </logic:equal>


          <logic:notEqual name="mbhNtp040Form" property="ntp040CompanySid" value="-1">
          <logic:notEqual name="mbhNtp040Form" property="ntp040CompanySid" value="0">
            <div id="ntp040CompanyIdArea">
              <input name="ntp040CompanySid" value="<bean:write name="mbhNtp040Form" property="ntp040CompanySid" />" type="hidden">
            </div>

            <div id="ntp040CompanyBaseIdArea">
              <logic:notEqual name="mbhNtp040Form" property="ntp040CompanyBaseSid" value="-1">
              <logic:notEqual name="mbhNtp040Form" property="ntp040CompanyBaseSid" value="0">
                <input name="ntp040CompanyBaseSid" value="<bean:write name="mbhNtp040Form" property="ntp040CompanyBaseSid" />" type="hidden">
              </logic:notEqual>
              </logic:notEqual>
            </div>

            <div id="ntp040CompanyCodeArea">
              <span class="text_anken_code"><gsmsg:write key="address.7" />：<span class="comp_code_name"><bean:write name="mbhNtp040Form" property="ntp040CompanyCode" /></span></span>
            </div>

            <div id="ntp040CompNameArea">
              <span class="text_company">
                <a id="<bean:write name="mbhNtp040Form" property="ntp040CompanySid" />" class="sc_link comp_click">
                  <span class="comp_name"><bean:write name="mbhNtp040Form" property="ntp040CompanyName" />&nbsp;
                    <logic:notEqual name="mbhNtp040Form" property="ntp040CompanyBaseSid" value="-1">
                    <logic:notEqual name="mbhNtp040Form" property="ntp040CompanyBaseSid" value="0">
                      <bean:write name="mbhNtp040Form" property="ntp040CompanyBaseName" />
                    </logic:notEqual>
                    </logic:notEqual>
                  </span>
                </a>
              </span>
              <div align="center">
                <font size="-2"><input type="submit" name="ntp040compdel" class="" value="<gsmsg:write key="cmn.delete" />" data-icon="delete" data-inline="true" /></font>
              </div>
            </div>
          </logic:notEqual>
          </logic:notEqual>

          <div id="ntp040AddressIdArea"></div>
          <div id="ntp040AddressNameArea"></div>

      </logic:equal>


      <logic:equal name="mbhNtp040Form" property="ntp040AnkenCompanyUse" value="1">
       <br>
        <div class="font_small">■<span class="text_bb1"><gsmsg:write key="ntp.11" /></span></div><span class="text_r2"></span>
       <br>

        <input type="button" class="" value="<gsmsg:write key="ntp.11" /><gsmsg:write key="cmn.search" />" onclick="return openAnkenWindow('ntp040','')" /><br>
          <img src="../schedule/images/spacer.gif" width="1px" border="0" height="10px">

          <logic:equal name="mbhNtp040Form" property="ntp040AnkenSid" value="-1">
            <div id="ntp040AnkenIdArea"></div>
            <div id="ntp040AnkenCodeArea"></div>
            <div id="ntp040AnkenNameArea"></div>
          </logic:equal>

         <logic:equal name="mbhNtp040Form" property="ntp040AnkenSid" value="0">
            <div id="ntp040AnkenIdArea"></div>
            <div id="ntp040AnkenCodeArea"></div>
            <div id="ntp040AnkenNameArea"></div>
          </logic:equal>

          <logic:notEqual name="mbhNtp040Form" property="ntp040AnkenSid" value="-1">
            <logic:notEqual name="mbhNtp040Form" property="ntp040AnkenSid" value="0">
              <div id="ntp040AnkenIdArea">
                <input name="ntp040AnkenSid" value="<bean:write name="mbhNtp040Form" property="ntp040AnkenSid" />" type="hidden">
              </div>

              <div id="ntp040AnkenCodeArea">
                <span class="text_anken_code"><gsmsg:write key="ntp.29" />：<span class="anken_code_name"><bean:write name="mbhNtp040Form" property="ntp040AnkenCode" /></span></span>
              </div>

              <div id="ntp040AnkenNameArea">
                <span class="text_anken">
                  <a id="<bean:write name="mbhNtp040Form" property="ntp040AnkenSid" />" class="sc_link anken_click">
                    <span class="anken_name"><bean:write name="mbhNtp040Form" property="ntp040AnkenName" /></span>
                  </a>
                </span>

                <div align="center">
                  <font size="-2"><input type="submit" name="<gsmsg:write key="cmn.delete" />" class="" value="<gsmsg:write key="cmn.delete" />" data-icon="delete" data-inline="true" /></font>
                </div>

              </div>
            </logic:notEqual>
          </logic:notEqual>

      </logic:equal>

      <logic:equal name="mbhNtp040Form" property="ntp040AnkenCompanyUse" value="2">
        <br>
        <div class="font_small">■<span class="text_bb1"><gsmsg:write key="ntp.15" />・<gsmsg:write key="ntp.16" /></span></div><span class="text_r2"></span>
        <br>
        <div data-role="controlgroup" data-type="horizontal" align="center" data-inline="true">
          <input type="submit" class="" value="<gsmsg:write key="addressbook" />" name="ntp040adrbook" data-inline="true" />
          <input type="submit" class="" value="<gsmsg:write key="ntp.17" />" name="ntp040rireki" />
        </div>
          <img src="../schedule/images/spacer.gif" width="1px" border="0" height="10px">



          <logic:equal name="mbhNtp040Form" property="ntp040CompanySid" value="-1">
            <div id="ntp040CompanyIdArea"></div>
            <div id="ntp040CompanyBaseIdArea"></div>
            <div id="ntp040CompanyCodeArea"></div>
            <div id="ntp040CompNameArea"></div>
          </logic:equal>


          <logic:equal name="mbhNtp040Form" property="ntp040CompanySid" value="0">
            <div id="ntp040CompanyIdArea"></div>
            <div id="ntp040CompanyBaseIdArea"></div>
            <div id="ntp040CompanyCodeArea"></div>
            <div id="ntp040CompNameArea"></div>
          </logic:equal>



          <logic:notEqual name="mbhNtp040Form" property="ntp040CompanySid" value="-1">
          <logic:notEqual name="mbhNtp040Form" property="ntp040CompanySid" value="0">
            <div id="ntp040CompanyIdArea">
              <input name="ntp040CompanySid" value="<bean:write name="mbhNtp040Form" property="ntp040CompanySid" />" type="hidden">
            </div>

            <div id="ntp040CompanyBaseIdArea">
              <logic:notEqual name="mbhNtp040Form" property="ntp040CompanyBaseSid" value="-1">
              <logic:notEqual name="mbhNtp040Form" property="ntp040CompanyBaseSid" value="0">
                <input name="ntp040CompanyBaseSid" value="<bean:write name="mbhNtp040Form" property="ntp040CompanyBaseSid" />" type="hidden">
              </logic:notEqual>
              </logic:notEqual>
            </div>

            <div id="ntp040CompanyCodeArea">
              <span class="text_anken_code"><gsmsg:write key="address.7" />：<span class="comp_code_name"><bean:write name="mbhNtp040Form" property="ntp040CompanyCode" /></span></span>
            </div>

            <div id="ntp040CompNameArea">
              <span class="text_company">
                <a id="<bean:write name="mbhNtp040Form" property="ntp040CompanySid" />" class="sc_link comp_click">
                  <span class="comp_name"><bean:write name="mbhNtp040Form" property="ntp040CompanyName" />&nbsp;
                    <logic:notEqual name="mbhNtp040Form" property="ntp040CompanyBaseSid" value="-1">
                    <logic:notEqual name="mbhNtp040Form" property="ntp040CompanyBaseSid" value="0">
                      <bean:write name="mbhNtp040Form" property="ntp040CompanyBaseName" />
                    </logic:notEqual>
                    </logic:notEqual>
                  </span>
                </a>
              </span>

              <div align="center">
                <font size="-2"><input type="submit" name="ntp040compdel" class="" value="<gsmsg:write key="cmn.delete" />"  data-icon="delete" data-inline="true" /></font>
              </div>

            </div>
          </logic:notEqual>
          </logic:notEqual>


          <div id="ntp040AddressIdArea"></div>
          <div id="ntp040AddressNameArea"></div>

      </logic:equal>


      <logic:equal name="mbhNtp040Form" property="ntp040KtBriHhuUse" value="0">
        <br>
        <div class="font_small">■<span class="text_bb1"><gsmsg:write key="ntp.3" />/<gsmsg:write key="ntp.31" /></span></div>
        <div data-role="navbar" align="center">
          <ul>
            <li>
                 <logic:notEmpty name="mbhNtp040Form" property="ntp040KtbunruiLavel">
                   <html:select property="ntp040Ktbunrui">
                      <html:optionsCollection name="mbhNtp040Form" property="ntp040KtbunruiLavel" value="value" label="label" />
                   </html:select>
                 </logic:notEmpty>
            </li>
            <li>
                 <logic:notEmpty name="mbhNtp040Form" property="ntp040KthouhouLavel">
                   <html:select property="ntp040Kthouhou">
                      <html:optionsCollection name="mbhNtp040Form" property="ntp040KthouhouLavel" value="value" label="label" />
                   </html:select>
                 </logic:notEmpty>
            </li>
        </ul>
        </div>
      </logic:equal>


      <logic:equal name="mbhNtp040Form" property="ntp040MikomidoUse" value="0">
        <br>
        <div class="font_small">■<span class="text_bb1"><gsmsg:write key="ntp.32" /></span></div><span class="text_r2"></span>
        <br>
        <div align="center">
          <span class="text_base">
          <fieldset data-role="controlgroup" data-mini="true">
            <html:radio name="mbhNtp040Form" property="ntp040Mikomido" styleId="ntp040Mikomido0" value="0" /><span class="text_base2"><label style="font-size:10px;" for="ntp040Mikomido0">10%</label></span>
            <html:radio name="mbhNtp040Form" property="ntp040Mikomido" styleId="ntp040Mikomido1" value="1" /><span class="text_base2"><label style="font-size:10px;" for="ntp040Mikomido1">30%</label></span>
            <html:radio name="mbhNtp040Form" property="ntp040Mikomido" styleId="ntp040Mikomido2" value="2" /><span class="text_base2"><label style="font-size:10px;" for="ntp040Mikomido2">50%</label></span>
            <html:radio name="mbhNtp040Form" property="ntp040Mikomido" styleId="ntp040Mikomido3" value="3" /><span class="text_base2"><label style="font-size:10px;" for="ntp040Mikomido3">70%</label></span>
            <html:radio name="mbhNtp040Form" property="ntp040Mikomido" styleId="ntp040Mikomido4" value="4" /><span class="text_base2"><label style="font-size:10px;" for="ntp040Mikomido4">100%</label></span>
          </fieldset>
          </span>
        </div>
        <br>
      </logic:equal>

      <br>
      <div class="font_small">■<span class="text_bb1"><gsmsg:write key="cmn.title" /></span><span class="text_r2">※</span></div>
      <br>

      <html:text name="mbhNtp040Form" size="50" maxlength="100" property="ntp040Title" styleId="ntpTitleTextBox" styleClass="text_base" />

      <bean:define id="colorMsg1" value=""/>
      <bean:define id="colorMsg2" value=""/>
      <bean:define id="colorMsg3" value=""/>
      <bean:define id="colorMsg4" value=""/>
      <bean:define id="colorMsg5" value=""/>
      <logic:iterate id="msgStr" name="mbhNtp040Form" property="ntp040ColorMsgList" indexId="msgId" type="java.lang.String">
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

      <br>
<%--
      <span class="sc_block_color_1"><html:radio name="mbhNtp040Form" property="ntp040Bgcolor" value="1" styleId="bg_color1"/></span>
      <label for="bg_color1" class="text_base"><%= colorMsg1 %></label>
      <span class="sc_block_color_2"><html:radio name="mbhNtp040Form" property="ntp040Bgcolor" value="2" styleId="bg_color2" /></span>
      <label for="bg_color2" class="text_base"><%= colorMsg2 %></label>
      <span class="sc_block_color_3"><html:radio name="mbhNtp040Form" property="ntp040Bgcolor" value="3" styleId="bg_color3" /></span>
      <label for="bg_color3" class="text_base"><%= colorMsg3 %></label>
      <span class="sc_block_color_4"><html:radio name="mbhNtp040Form" property="ntp040Bgcolor" value="4" styleId="bg_color4" /></span>
      <label for="bg_color4" class="text_base"><%= colorMsg4 %></label>
      <span class="sc_block_color_5"><html:radio name="mbhNtp040Form" property="ntp040Bgcolor" value="5" styleId="bg_color5" /></span>
      <label for="bg_color5" class="text_base"><%= colorMsg5 %></label>
--%>
<div data-role="navbar" align="center">
    <ul>
      <li style="background-color:#0000FF;">
        <span style="background-color:#0000FF;"><html:radio name="mbhNtp040Form" property="ntp040Bgcolor" value="1" /></span>
        <label for="bg_color1" class="text_base">&nbsp;&nbsp;</label><br><span style="color:white;"><div class="font_small"><b><%= colorMsg1 %></b></div></span>
      </li>
      <li style="background-color:#FF0000;">
        <span style="background-color:#FF0000;"><html:radio name="mbhNtp040Form" property="ntp040Bgcolor" value="2" /></span>
        <label for="bg_color2" class="text_base">&nbsp;&nbsp;</label><br><span style="color:white;"><div class="font_small"><b><%= colorMsg2 %></b></div></span>
      </li>
      <li style="background-color:#009900;">
        <span style="background-color:#009900;"><html:radio name="mbhNtp040Form" property="ntp040Bgcolor" value="3" /></span>
        <label for="bg_color3" class="text_base">&nbsp;&nbsp;</label><br><span style="color:white;"><div class="font_small"><b><%= colorMsg3 %></b></div></span>
      </li>
      <li style="background-color:#ff9900;">
        <span style="background-color:#ff9900;"><html:radio name="mbhNtp040Form" property="ntp040Bgcolor" value="4" /></span>
        <label for="bg_color4" class="text_base">&nbsp;&nbsp;</label><br><span style="color:white;"><div class="font_small"><b><%= colorMsg4 %></b></div></span>
      </li>
      <li style="background-color:#333333;">
        <span style="background-color:#333333;"><html:radio name="mbhNtp040Form" property="ntp040Bgcolor" value="5" /></span>
        <label for="bg_color5" class="text_base">&nbsp;&nbsp;</label><br><span style="color:white;"><div class="font_small"><b><%= colorMsg5 %></b></div></span>
    </li>
  </ul>
</div>

      <br>
      <br>
      <div class="font_small">■<span class="text_bb1"><gsmsg:write key="cmn.content2" /><a id="naiyou" name="naiyou"></a></span></div>
      <br>

      <textarea name="ntp040Value" cols="50" rows="5" class="text_base" id="inputstr" <%= valueFocusEvent %>><bean:write name="mbhNtp040Form" property="ntp040Value" /></textarea>
      <br>

<!--       添付ファイル -->
      <div class="font_small">■<gsmsg:write key="cmn.attach.file"/></div>
<!--       <span id="tmp_file_area"> -->
<!--         <logic:notEmpty name="mbhNtp040Form" property="ntp040FileList"> -->
<!--           <logic:iterate id="file" name="mbhNtp040Form" property="ntp040FileList" indexId="idx" scope="request"> -->
<%--              <div style="width:100%;" id="file_<bean:write name="file" property="binSid" />"> --%>
<!--               <div class="del_file_txt"><bean:write name="file" property="binFileName" /></div> -->
<%--               <div id="<bean:write name="file" property="binSid" />" class="del_file_div">&nbsp;&nbsp;</div> --%>
<!--             </div> -->
<!--             <div style="clear:both;padding-top:10px;"></div> -->
<!--           </logic:iterate> -->
<!--         </logic:notEmpty> -->
<!--       </span> -->
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



      <logic:equal name="mbhNtp040Form" property="ntp040NextActionUse" value="0">
      <br>
      <div class="font_small">■<span class="text_bb1"><gsmsg:write key="ntp.96" /><a id="nextaction" name="nextaction"></a></span></div>
      <br>


      <logic:equal name="mbhNtp040Form" property="ntp040ActDateKbn" value="0">
      <div data-role="controlgroup" data-type="horizontal" align="center" style="font-size:10px;">
      <input type="submit" name="ntp040actDayAdd" class="" value="<gsmsg:write key="ntp.34" />する" data-inline="true"/>
      </div>
      </logic:equal>

      <logic:equal name="mbhNtp040Form" property="ntp040ActDateKbn" value="1">
      <div data-role="controlgroup" data-type="horizontal" align="center" style="font-size:10px;">
      <input type="submit" name="ntp040actDayNotAdd" class="" value="<gsmsg:write key="ntp.34" />しない" data-inline="true"/>
      </div>


<%--
        <html:select property="ntp040NxtActYear" styleId="selActionYear">
          <html:optionsCollection name="mbhNtp040Form" property="ntp040YearLavel" value="value" label="label" />
        </html:select>

        <html:select property="ntp040NxtActMonth" styleId="selActionMonth">
          <html:optionsCollection name="mbhNtp040Form" property="ntp040MonthLavel" value="value" label="label" />
        </html:select>

        <html:select property="ntp040NxtActDay" styleId="selActionDay">
          <html:optionsCollection name="mbhNtp040Form" property="ntp040DayLavel" value="value" label="label" />
        </html:select>
--%>
        <jquery:jqtext id="date2" name="mbhNtp040Form" property="ntp040ActionDate" readonly="true"/>


      </logic:equal>

      <div>
        <textarea name="ntp040NextAction" cols="50" rows="2" class="text_base" id="actionstr"><bean:write name="mbhNtp040Form" property="ntp040NextAction" /></textarea>
        <br>
      </div>

      </logic:equal>