<%@ page pageEncoding="Windows-31J" contentType="text/html; charset=UTF-8"%>
<input type="hidden" name="helpPrm" value="shukei">


          <div style="padding-top:5px;padding-right:5px;padding-bottom:0px;padding-left:5px;">

            <%-- タブ --%>
            <table class="" width="100%" border="0" cellpadding="0" cellspacing="0" height="15px">
            <tbody>
              <tr>
                <td style="border-bottom:1px solid #939393;" width="1%" nowrap="nowrap">&nbsp;</td>
                <td class="bunseki_tab_sel" width="100px" align="left" nowrap="nowrap">
                  <div style="text-align:center;">
                    <a style="" href="javascript:changeMode(0);"><gsmsg:write key="ntp.130" /></a>
                  </div>
                </td>
                <td class="bunseki_tab_not_sel" width="107px" align="left" nowrap="nowrap">
                  <div style="text-align:center;">
                    <a href="javascript:changeMode(1);"><gsmsg:write key="ntp.131" /></a>
                  </div>
                </td>
                <td style="border-bottom:1px solid #939393;" width="99%" nowrap="nowrap">&nbsp;</td>
              </tr>
            </tbody>
            </table>


            <table width="100%" style="margin-top:5px;">
              <%-- 日付指定 --%>
              <tr>
                <td valign="top" style="padding-bottom:10px;" width="100%">
                  <table style="padding:0px;margin:0px;">
                    <tr>
                      <td valign="top" style="padding:0px;margin:0px;" nowrap>
                        <p id="jquery-ui-datepicker-wrap"><span style="font-size:12px;font-weight:bold;padding-left:12px;" class="text_base"><gsmsg:write key="ntp.132" />:</span>
                          <input readonly="readonly" style="cursor:pointer; width:155px;" value="<bean:write name="ntp220Form" property="ntp220DateFrStr" />" type="text" id="jquery-ui-datepicker-from" name="jquery-ui-datepicker-from"/>
                          <label for="jquery-ui-datepicker-from"><span class="text_base">～</span></label>
                          <input readonly="readonly" style="cursor:pointer; width:155px;" value="<bean:write name="ntp220Form" property="ntp220DateToStr" />" type="text" id="jquery-ui-datepicker-to" name="jquery-ui-datepicker-to"/>
                        </p>
                      </td>
                      <td valign="middle" style="padding:0px;margin:0px;">
                      </td>
                      <td valign="middle" style="padding:0px;margin:0px;"  nowrap>
                        <div class="category_sel_area" style="padding-top:0px;padding-right:0px;padding-bottom:0px;padding-left:10px;margin:0px;">
                            <span style="font-size:12px;font-weight:bold;padding-left:12px;" class="text_base"><gsmsg:write key="ntp.59" />:</span>
                            <html:select style="width:100px;" name="ntp220Form" property="ntp220CatSid" styleClass="select01" onchange="changeCategoryCombo();">
                            <logic:notEmpty name="ntp220Form" property="ntp220CategoryList">
                            <html:optionsCollection name="ntp220Form" property="ntp220CategoryList" value="value" label="label" />
                            </logic:notEmpty>
                            </html:select>
                        </div>
                      </td>
                    </tr>
                    <tr>
                    <tr>
                      <td valign="top" style="padding:0px;margin:0px;" colspan="3" nowrap>
                        <div style="padding-top:0px;padding-right:0px;padding-bottom:0px;padding-left:0px;margin:0px;">
                            <span style="font-size:12px;font-weight:bold;padding-left:12px;padding-right:5px;" class="text_base"><gsmsg:write key="cmn.group" />&nbsp;:</span>
                            <html:select style="width:150px;" property="ntp220GroupSid" styleClass="select01" onchange="changeKojinGroupCombo();">
                              <logic:notEmpty name="ntp220Form" property="ntp220GroupLavel" scope="request">
                              <logic:iterate id="gpBean" name="ntp220Form" property="ntp220GroupLavel" scope="request">

                              <bean:define id="gpValue" name="gpBean" property="value" type="java.lang.String" />
                              <logic:equal name="gpBean" property="styleClass" value="0">
                              <html:option value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
                              </logic:equal>

                              <logic:notEqual name="gpBean" property="styleClass" value="0">
                              <html:option styleClass="select03" value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
                              </logic:notEqual>

                              </logic:iterate>
                              </logic:notEmpty>
                            </html:select>
                            <input name="ntp220SelectUsrSid" type="hidden" value="" />
                        </div>
                      </td>
                    </tr>
                  </table>
                </td>
              </tr>

              <%-- コンテンツ--%>
              <tr>
                <td style="padding:0px;margin:0px;" width="100%">
                  <table  width="100%" style="padding:0px;margin:0px;">
                    <tr>
                      <%-- メニュー--%>
                      <td width="180px" valign="top" id="sel_menu_wrapper" class="sel_menu_wrapper">
                        <div style="margin:0px;padding-top:0px;padding-right:0px;padding-bottom:0px;padding-left:0px;">
                          <table width="180px" style="margin-top:0px;margin-right:0px;margin-bottom:3px;margin-left:0px;">
                            <tr>
                              <td class="sel_menu_area">
                                <%-- <gsmsg:write key="ntp.15" />・<gsmsg:write key="ntp.16" /> --%>
                                <div class="sel_menu_title" id="kigyou">
                                  <span><gsmsg:write key="ntp.15" />・<gsmsg:write key="ntp.16" /></span>
                                  <input id="kigyou_offset" type="hidden" value="1" />
                                  <input id="kigyou_already_sel" type="hidden" value="" />
                                </div>
                                <div class="sel_menu_content display_none" id="kigyou_sel_menu_content">
                                </div>
                              </td>
                            </tr>
                            <%--
                            <tr>
                              <td class="sel_menu_area">
                            --%>
                                <%-- 商品 --%>
                            <%--
                                <div class="sel_menu_title" id="shohin">
                                  <span><gsmsg:write key="ntp.58" /></span>
                                  <input id="shohin_offset" type="hidden" value="1" />
                                  <input id="shohin_already_sel" type="hidden" value="" />
                                </div>
                                <div class="sel_menu_content display_none" id="shohin_sel_menu_content">
                                  <span class="sel_menu_content_text"><gsmsg:write key="ntp.58" />A</span><br>
                                  <span class="sel_menu_content_text"><gsmsg:write key="ntp.58" />B</span><br>
                                  <span class="sel_menu_content_text"><gsmsg:write key="ntp.58" />C</span><br>
                                  <span class="sel_menu_content_text"><gsmsg:write key="ntp.58" />D</span><br>
                                </div>
                              </td>
                            </tr>
                            --%>
                            <%--
                            <tr>
                              <td class="sel_menu_area">
                             --%>
                                <%-- 業種 --%>
                                <%--
                                <div class="sel_menu_title" id="gyoushu">
                                  <span><gsmsg:write key="ntp.61" /></span>
                                  <input id="gyoushu_offset" type="hidden" value="1" />
                                  <input id="gyoushu_already_sel" type="hidden" value="" />
                                </div>
                                <div class="sel_menu_content display_none" id="gyoushu_sel_menu_content">
                                  <span class="sel_menu_content_text">○×</span><br>
                                  <span class="sel_menu_content_text">△□</span><br>
                                  <span class="sel_menu_content_text">×○</span><br>
                                  <span class="sel_menu_content_text">□×</span><br>
                                </div>
                              </td>
                            </tr>
                            --%>
                            <tr>
                              <td class="sel_menu_area">
                                <%-- 業種・プロセス --%>
                                <div class="sel_menu_title sel_menu_title_process_area" id="process">
                                  <span><gsmsg:write key="ntp.61" /> / <gsmsg:write key="ntp.62" /></span>
                                  <input id="process_offset" type="hidden" value="1" />
                                  <input id="process_already_sel" type="hidden" value="" />
                                </div>

                               <div class="sel_menu_content_gyoushu display_none">
                                  <logic:notEmpty name="ntp220Form" property="ntp220GyoushuList">
                                    <html:select name="ntp220Form" property="ntp220GyoushuSid" onchange="changeGyoushuCombo();">
                                      <html:optionsCollection name="ntp220Form" property="ntp220GyoushuList" value="value" label="label" />
                                    </html:select>
                                  </logic:notEmpty>
                                </div>

                                <div class="sel_menu_content display_none" id="process_sel_menu_content">
                                </div>

                              </td>
                            </tr>
                            <tr>
                              <td class="sel_menu_area">
                                <%-- 見込み度 --%>
                                <div class="sel_menu_title" id="mikomido">
                                  <span><gsmsg:write key="ntp.32" /></span>
                                  <input id="mikomido_offset" type="hidden" value="1" />
                                  <input id="mikomido_already_sel" type="hidden" value="" />
                                </div>
                                <div class="sel_menu_content display_none" id="mikomido_sel_menu_content">
                                  <span class="sel_menu_content_text">○×</span><br>
                                  <span class="sel_menu_content_text">△□</span><br>
                                  <span class="sel_menu_content_text">×○</span><br>
                                  <span class="sel_menu_content_text">□×</span><br>
                                </div>
                              </td>
                            </tr>
                            <tr>
                              <td class="sel_menu_area">
                                <%-- 顧客源泉 --%>
                                <div class="sel_menu_title" id="kokyakugensen">
                                  <span><gsmsg:write key="ntp.65" /></span>
                                  <input id="kokyakugensen_offset" type="hidden" value="1" />
                                  <input id="kokyakugensen_already_sel" type="hidden" value="" />
                                </div>
                                <div class="sel_menu_content display_none" id="kokyakugensen_sel_menu_content">
                                </div>
                              </td>
                            </tr>
                            <tr>
                              <td class="sel_menu_area">
                                <%-- 稼働時間 --%>
                                <div class="sel_menu_title" id="kadou">
                                  <span><gsmsg:write key="ntp.170" /></span>
                                  <input id="kadou_offset" type="hidden" value="1" />
                                  <input id="kadou_already_sel" type="hidden" value="" />
                                </div>
                                <div class="sel_menu_content display_none" id="kadoou_sel_menu_content">
                                </div>
                              </td>
                            </tr>
                          </table>
                        </div>
                      </td>

                      <%-- メニュー格納用縦線 --%>
                      <td width="3px" id="menu_length_area" class="menu_length_border" style="padding:0px;margin:0px;">&nbsp;</td>

                      <%-- グラフ --%>
                      <td valign="top" style="width:100%;padding:0px;margin:0px;">

                        <table style="padding:0px;margin:0px;" width="100%">
                          <tr id="anken_state_menu_area">
                            <td align="left" colspan="2" class="graph_anken_state" width="100%">
                              <div id="-1" class="anken_state_check"><span><gsmsg:write key="ntp.166" /></span></div>
                              <div id="0" class="anken_state_check anken_state_notcheck"><span><gsmsg:write key="ntp.74" /></span></div>
                              <div id="1" class="anken_state_check anken_state_notcheck"><span><gsmsg:write key="ntp.75" /></span></div>
                              <%--
                              <div style="float:left;" id="no_sel"><span>&nbsp;</span></div>
                              <div style="float:left" id="category_sel"></div>
                              --%>
                            </td>
                          </tr>

                          <%-- デフォルトグラフ --%>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            <%-- <gsmsg:write key="ntp.11" /> --%>
                          <tr>
                            <td colspan="2" class="def_graph" width="100%">
                              <table style="padding:0px;margin:0px;">
                                <tr>
                                  <td nowrap style="padding-left:5px;padding-right:0px;padding-top:5px;padding-bottom:0px;margin:0px;">
                                    <div class="state_label_title state_label_title_def_graph def_graph_title" id="0"><gsmsg:write key="ntp.167" /></div>
                                    <div class="state_label_title2">|</div>
                                    <div class="state_label_title state_label_title_def_graph state_label_title_def_graph_not_sel def_graph_title" id="1"><gsmsg:write key="ntp.64" /></div>
                                  </td>
                                  <td nowrap style="padding-left:20px;padding-top:3px;" align="center">
                                    <div id="-1" class="state_label_sel_text sel_label_sel_all def_graph_state_label"><gsmsg:write key="ntp.166" /></div>
                                    <div id="1" class="state_label_sel_text state_label_sel_text2 state_label_notsel_text sel_label_sel_jutyu def_graph_state_label"><gsmsg:write key="ntp.69" /></div>
                                    <div id="2" class="state_label_sel_text state_label_sel_text2 state_label_notsel_text sel_label_sel_sityu def_graph_state_label"><gsmsg:write key="ntp.7" /></div>
                                    <div id="0" class="state_label_sel_text state_label_sel_text2 state_label_notsel_text sel_label_sel_shodan def_graph_state_label"><gsmsg:write key="ntp.68" /></div>
                                  </td>
                              </table>
                              <div id="ankenGraphArea">
                                <div id="ankenGraph" style="text-align:center;padding-top:110px;padding-bottom:110px;"><img src="../nippou/images/ajax-loader.gif" /></div>
                                <div id="shodanStateGraph"></div>
                              </div>
                              <input type="hidden" value="0" id="def_graph_val" name="def_graph_val" />
                            </td>
                          </tr>

                          <%-- 稼働時間グラフ --%>
                          <tr id="kadouArea">
                            <td colspan="2" class="anything_kadou_area kadou_graph display_none" width="100%">
                              <div style="width:100%;text-align:center;">
                                <table width="100%"><tr><td nowrap width="100%" class="kdou_times_back">
                                <span class="state_label_title1"><gsmsg:write key="ntp.175" />:</span><span id="sum_kadou_days" class="state_label_title3"><span></span></span><span class="state_label_title4"><gsmsg:write key="cmn.day" /></span>
                                <span class="state_label_title1"><gsmsg:write key="ntp.171" />:</span><span id="sum_kadou_time" class="state_label_title3"><span></span></span><span class="state_label_title4"><gsmsg:write key="cmn.time" /></span>
                                <span class="state_label_title1"><gsmsg:write key="ntp.176" />:</span><span id="sum_kadou_time_average" class="state_label_title3"><span></span></span><span class="state_label_title4"><gsmsg:write key="cmn.time" />/<gsmsg:write key="cmn.day" /></span>
                                </td></tr></table>
                              </div>
                              <div id="kadouGraphArea">
                                <div id="kadouGraph" style="text-align:center;padding-top:110px;padding-bottom:110px;"><img src="../nippou/images/ajax-loader.gif" /></div>
                              </div>
                            </td>
                          </tr>

                          <%-- 可変エリア  親要素 --%>
                          <tr>
                            <td valign="top" width="50%" class="anything_area display_none">

                             <table width="100%">
                                <tr>
                                  <td nowrap style="width:80%;height:45px;font-size:10px;color:#333333;padding-left:10px;padding-top:9px;">
                                    <div class="state_label_title"><gsmsg:write key="ntp.167" /></div>
                                    <div id="-1" class="state_label_sel_text sel_label_sel_all"><gsmsg:write key="ntp.166" /></div>
                                    <div id="1" class="state_label_sel_text state_label_sel_text2 state_label_notsel_text sel_label_sel_jutyu"><gsmsg:write key="ntp.69" /></div>
                                    <div id="2" class="state_label_sel_text state_label_sel_text2 state_label_notsel_text sel_label_sel_sityu"><gsmsg:write key="ntp.7" /></div>
                                    <div id="0" class="state_label_sel_text state_label_sel_text2 state_label_notsel_text sel_label_sel_shodan"><gsmsg:write key="ntp.68" /></div>
                                  </td>
                                  <td align="right" style="padding-left:5px;width:20%;font-size:10px;color:#333333;padding-right:12px;padding-top:9px;">
                                    <input id="anythingGraph1Val" type="hidden" value="0" />
                                    <table style="border:1px solid #dddddd;">
                                      <tr>
                                        <td valign="middle">
                                          <table>
                                          <tr>
                                          <td>
                                          </td>
                                          </tr>
                                          </table>
                                          <div style="background-color:#eaa228;width:10px;height:10px;line-height:10px;margin:3px;border:1px solid #dddddd;">&nbsp;</div>
                                        </td>
                                        <td valign="middle" nowrap style="height:10px;"><span style="line-height:10px;font-size:10px;color:#333333;font-family:'ＭＳ Ｐゴシック',sans-serif!important;"><gsmsg:write key="ntp.54" /></span></td>
                                        </tr>
                                        <tr>
                                        <td valign="middle">
                                          <div style="background-color:#4bb2c5;width:10px;height:10px;line-height:10px;margin:3px;border:1px solid #dddddd;">&nbsp;</div>
                                        </td>
                                        <td valign="middle" nowrap><span style="line-height:10px;font-size:10px;color:#333333;font-family:'ＭＳ Ｐゴシック',sans-serif!important;"><gsmsg:write key="ntp.53" /></span></td>
                                      </tr>
                                    </table>
                                    <%-- <img src="../nippou/images/btn_graph_sel.gif" alt="" /> --%>
                                  </td>
                                </tr>
                              </table>

                              <div id="anythingGraph1Area" style="clear:both;">
                                <div id="anythingGraph1" style=""></div>
                                <div class="tooltip_back" id="tooltipAnything"></div>
                              </div>
                              <div style="width:100%;text-align:center;"><span id="anything1_more" class="sel_anything1_more display_none">▼<gsmsg:write key="ntp.133" /></span></div>
                              <input type="hidden" name="anything1page" value="1" />
                              <input type="hidden" name="anything1NowCount" value="0" />
                            </td>

                            <td valign="top" width="50%" class="anything_area display_none">

                              <table width="100%">
                                <tr>
                                  <td style="width:80%;height:45px;font-size:10px;color:#333333;padding-left:10px;padding-top:9px;">
                                    <div class="state_label_title"><gsmsg:write key="ntp.11" /><gsmsg:write key="ntp.71" /></div>
                                    <span class="state_label_area display_none">
                                    <div id="-1" class="state_label_sel_text state_label_notsel_text sel_label_sel_all"><gsmsg:write key="ntp.166" /></div>
                                    <div id="1" class="state_label_sel_text state_label_sel_text2 state_label_notsel_text sel_label_sel_jutyu"><gsmsg:write key="ntp.69" /></div>
                                    <div id="2" class="state_label_sel_text state_label_sel_text2 state_label_notsel_text sel_label_sel_sityu"><gsmsg:write key="ntp.7" /></div>
                                    <div id="0" class="state_label_sel_text state_label_sel_text2 state_label_notsel_text sel_label_sel_shodan"><gsmsg:write key="ntp.68" /></div>
                                    </span>
                                    <%--
                                    <div class="state_label_sel_text"><gsmsg:write key="ntp.53" /></div>
                                    <div class="state_label_sel_text state_label_sel_text2 state_label_notsel_text"><gsmsg:write key="ntp.54" /></div>
                                    --%>
                                  </td>
                                  <td id="anythingGraph2Btn" align="right" style="width:20%;cursor:pointer;font-size:10px;color:#333333;padding-right:12px;padding-top:9px;">
                                    <input id="anythingGraph2Val" type="hidden" value="0" />
                                    <img src="../nippou/images/btn_graph_sel.gif" alt="" />
                                  </td>
                                </tr>
                              </table>

                              <div id="anythingGraph2Area" style="clear:both;">
                                <div id="anythingGraph2"></div>
                                <div class="tooltip_back" id="tooltipAnythingMoney"></div>
                              </div>
                            </td>
                          </tr>

                          <%-- 可変エリア(稼働時間)  親要素 --%>
                          <tr>
                            <td valign="top" width="50%" class="anything_kadou_area display_none">

                              <table width="100%">
                                <tr>
                                  <td nowrap style="width:90%;height:45px;font-size:10px;color:#333333;padding-left:10px;padding-top:9px;">
                                    <div class="state_label_title"><gsmsg:write key="ntp.11" />-<gsmsg:write key="ntp.170" /></div>
                                  </td>

                                  <td id="anythingKadou1GraphBtn" align="right" style="width:20%;cursor:pointer;font-size:10px;color:#333333;padding-right:12px;padding-top:9px;">
                                    <input id="anythingKadou1GraphVal" type="hidden" value="0" />
                                    <img src="../nippou/images/btn_graph_sel.gif" alt="" />
                                  </td>
                                </tr>
                              </table>

                              <div id="anythingKadouGraph1Area" style="clear:both;">
                                <div id="anythingKadouGraph1" style=""></div>
                                <div class="tooltip_back" id="tooltipAnythingKadou1"></div>
                              </div>
                              <div style="width:100%;text-align:center;"><span id="anythingKadou1_more" class="sel_anythingKadou1_more display_none">▼<gsmsg:write key="ntp.133" /></span></div>
                              <input type="hidden" name="anythingKadou1page" value="1" />
                              <input type="hidden" name="anythingKadou1NowCount" value="0" />
                            </td>

                            <td valign="top" width="50%" class="anything_kadou_area display_none">

                              <table width="100%">
                                <tr>
                                  <td nowrap style="width:90%;height:45px;font-size:10px;color:#333333;padding-left:10px;padding-top:9px;">
                                    <div class="state_label_title"><gsmsg:write key="ntp.15" />-<gsmsg:write key="ntp.170" /></div>
                                  </td>

                                  <td id="anythingKadou2GraphBtn" align="right" style="width:20%;cursor:pointer;font-size:10px;color:#333333;padding-right:12px;padding-top:9px;">
                                    <input id="anythingKadou2GraphVal" type="hidden" value="0" />
                                    <img src="../nippou/images/btn_graph_sel.gif" alt="" />
                                  </td>
                                </tr>
                              </table>

                              <div id="anythingKadouGraph2Area" style="clear:both;">
                                <div id="anythingKadouGraph2"></div>
                                <div class="tooltip_back" id="tooltipAnythingKadou2"></div>
                              </div>
                              <div style="width:100%;text-align:center;"><span id="anythingKadou2_more" class="sel_anythingKadou2_more display_none">▼<gsmsg:write key="ntp.133" /></span></div>
                              <input type="hidden" name="anythingKadou2page" value="1" />
                              <input type="hidden" name="anythingKadou2NowCount" value="0" />
                            </td>
                          </tr>

                          <tr>
                            <td valign="top" width="50%" class="anything_kadou_area display_none">

                              <table width="100%">
                                <tr>
                                  <td nowrap style="width:90%;height:45px;font-size:10px;color:#333333;padding-left:10px;padding-top:9px;">
                                    <div class="state_label_title"><gsmsg:write key="ntp.3" />-<gsmsg:write key="ntp.170" /></div>
                                  </td>

                                  <td id="anythingKadou3GraphBtn" align="right" style="width:20%;cursor:pointer;font-size:10px;color:#333333;padding-right:12px;padding-top:9px;">
                                    <input id="anythingKadou3GraphVal" type="hidden" value="0" />
                                    <img src="../nippou/images/btn_graph_sel.gif" alt="" />
                                  </td>
                                </tr>
                              </table>

                              <div id="anythingKadouGraph3Area" style="clear:both;">
                                <div id="anythingKadouGraph3" style=""></div>
                                <div class="tooltip_back" id="tooltipAnythingKadou3"></div>
                              </div>
                              <div style="width:100%;text-align:center;"><span id="anythingKadou3_more" class="sel_anythingKadou3_more display_none">▼<gsmsg:write key="ntp.133" /></span></div>
                              <input type="hidden" name="anythingKadou3page" value="1" />
                              <input type="hidden" name="anythingKadou3NowCount" value="0" />
                            </td>

                            <td valign="top" width="50%" class="anything_kadou_area display_none">

                              <table width="100%">
                                <tr>
                                  <td nowrap style="width:90%;height:45px;font-size:10px;color:#333333;padding-left:10px;padding-top:9px;">
                                    <div class="state_label_title"><gsmsg:write key="ntp.121" />-<gsmsg:write key="ntp.170" /></div>
                                  </td>

                                  <td id="anythingKadou4GraphBtn" align="right" style="width:20%;cursor:pointer;font-size:10px;color:#333333;padding-right:12px;padding-top:9px;">
                                    <input id="anythingKadou4GraphVal" type="hidden" value="0" />
                                    <img src="../nippou/images/btn_graph_sel.gif" alt="" />
                                  </td>
                                </tr>
                              </table>

                              <div id="anythingKadouGraph4Area" style="clear:both;">
                                <div id="anythingKadouGraph4"></div>
                                <div class="tooltip_back" id="tooltipAnythingKadou4"></div>
                              </div>
                              <div style="width:100%;text-align:center;"><span id="anythingKadou4_more" class="sel_anythingKadou4_more display_none">▼<gsmsg:write key="ntp.133" /></span></div>
                              <input type="hidden" name="anythingKadou4page" value="1" />
                              <input type="hidden" name="anythingKadou4NowCount" value="0" />
                            </td>
                          </tr>

                          <%-- 可変エリア 子要素 --%>
                          <tr>
                            <td valign="top" colspan="2" class="anything_child_area display_none" style="border:1px solid #939393;">
                             <div class="state_label_head_title"><span class="child_sel_content_head_title"></span></div>
                             <table width="100%">
                                <tr>
                                  <td style="width:80%;font-size:10px;color:#333333;padding-left:10px;padding-top:9px;">
                                    <div class="state_label_title"><gsmsg:write key="ntp.167" /></div>
                                    <div id="-1" class="state_label_sel_text sel_label_sel_all"><gsmsg:write key="ntp.166" /></div>
                                    <div id="1" class="state_label_sel_text state_label_sel_text2 state_label_notsel_text sel_label_sel_jutyu"><gsmsg:write key="ntp.69" /></div>
                                    <div id="2" class="state_label_sel_text state_label_sel_text2 state_label_notsel_text sel_label_sel_sityu"><gsmsg:write key="ntp.7" /></div>
                                    <div id="0" class="state_label_sel_text state_label_sel_text2 state_label_notsel_text sel_label_sel_shodan"><gsmsg:write key="ntp.68" /></div>
                                  </td>
                                  <td id="anythingChild0GraphBtn" align="right" style="width:20%;cursor:pointer;font-size:10px;color:#333333;padding-right:12px;padding-top:9px;">
                                    <input id="anythingChild0GraphVal" type="hidden" value="1" />
                                    <table style="border:1px solid #dddddd;">
                                      <tr>
                                       <%--
                                        <td valign="middle">
                                          <table>
                                          <tr>
                                          <td>
                                          </td>
                                          </tr>
                                          </table>
                                          <div style="background-color:#eaa228;width:10px;height:10px;margin:3px;border:1px solid #dddddd;">&nbsp;</div>
                                        </td>
                                        <td valign="middle" nowrap><span style="font-size:10px;color:#333333;"><gsmsg:write key="ntp.53" /></span></td>
                                        <td valign="middle">
                                          <div style="background-color:#4bb2c5;width:10px;height:10px;margin:3px;border:1px solid #dddddd;">&nbsp;</div>
                                        </td>
                                        <td valign="middle" nowrap><span style="padding-right:2px;font-size:10px;color:#333333;"><gsmsg:write key="ntp.54" /></span></td>
                                        --%>
                                      </tr>
                                    </table>
                                    <%-- <img src="../nippou/images/btn_graph_sel.gif" alt="" /> --%>
                                  </td>
                                </tr>
                              </table>

                              <div id="anythingChild0GraphArea">
                                <div id="anythingChild0Graph" style=""></div>
                                <div class="tooltip_back" id="tooltipAnything"></div>
                              </div>
                            </td>
                          </tr>

                          <tr>
                            <td class="anything_child_area display_none" valign="top" width="50%" style="border:1px solid #939393;">

                              <table width="100%">
                                <tr>
                                  <td style="width:80%;font-size:10px;color:#333333;padding-left:10px;padding-top:9px;"><div class="shoudan_rsult_text"><span style="font-size:14px;font-weight:bold;"><span style="font-size:14px;font-weight:bold;" class="child_sel_content_title"><gsmsg:write key="ntp.64" /></span></span>&nbsp;&nbsp;</div></td>
                                  <td id="anythingChild1GraphBtn" align="right" style="width:20%;cursor:pointer;font-size:10px;color:#333333;padding-right:12px;padding-top:9px;">
                                    <input id="anythingChild1GraphVal" type="hidden" value="0" />
                                    <img src="../nippou/images/btn_graph_sel.gif" alt="" />
                                  </td>
                                </tr>
                              </table>

                              <div id="anythingChild1GraphArea" style="text-valign:top;clear:both;">
                                <div id="anythingChild1Graph" ></div>
                                <div class="tooltip_back" id="tooltipAnythingChildResult"></div>
                              </div>
                            </td>

                            <td class="anything_child_area display_none" valign="top" width="50%" style="border:1px solid #939393;">

                              <table width="100%">
                                <tr>
                                  <td style="width:80%;font-size:10px;color:#333333;padding-left:10px;padding-top:9px;" nowrap>
                                    <div class="state_label_title"><span class="child_sel_content_title"><gsmsg:write key="ntp.134" /></span></div>
                                    <%--
                                    <div class="state_label_sel_text"><gsmsg:write key="ntp.53" /></div>
                                    <div class="state_label_sel_text state_label_sel_text2 state_label_notsel_text"><gsmsg:write key="ntp.54" /></div>
                                    --%>
                                    <div id="-1" class="state_label_sel_text sel_label_sel_all"><gsmsg:write key="ntp.166" /></div>
                                    <div id="1" class="state_label_sel_text state_label_sel_text2 state_label_notsel_text sel_label_sel_jutyu"><gsmsg:write key="ntp.69" /></div>
                                    <div id="2" class="state_label_sel_text state_label_sel_text2 state_label_notsel_text sel_label_sel_sityu"><gsmsg:write key="ntp.7" /></div>
                                    <div id="0" class="state_label_sel_text state_label_sel_text2 state_label_notsel_text sel_label_sel_shodan"><gsmsg:write key="ntp.68" /></div>
                                  </td>
                                  <td id="anythingChild2GraphBtn" align="right" style="width:20%;cursor:pointer;font-size:10px;color:#333333;padding-right:12px;padding-top:9px;">
                                    <input id="anythingChild2GraphVal" type="hidden" value="0" />
                                    <img src="../nippou/images/btn_graph_sel.gif" alt="" />
                                  </td>
                                </tr>
                              </table>


                              <div id="anythingChild2GraphArea" style="clear:both;">
                                <div id="anythingChild2Graph" ></div>
                                <div class="tooltip_back" id="tooltipAnythingChild2"></div>
                              </div>
                            </td>
                          </tr>




                          <%-- 可変エリア(稼働時間) 子要素 --%>
                          <tr>

                            <td valign="top" colspan="2" class="anything_kadou_child_area display_none" style="border:1px solid #939393;">
                              <div class="state_label_head_title"><span class="child_sel_content_head_title"></span></div>
                              <table width="100%">
                                <tr>
                                  <td nowrap class="kdou_times_back3" style="width:95%;font-size:10px;color:#333333;padding-left:10px;">
                                    <span class="state_label_title5"><gsmsg:write key="ntp.175" />:</span><span id="sum_kadou_days_child" class="state_label_title6"><span></span></span><span class="state_label_title7"><gsmsg:write key="cmn.day" /></span>
                                    <span class="state_label_title5"><gsmsg:write key="ntp.170" /><gsmsg:write key="ntp.172" />:</span><span id="sum_kadou_time_child" class="state_label_title6"><span></span></span><span class="state_label_title7"><gsmsg:write key="cmn.time" /></span>
                                    <span class="state_label_title5"><gsmsg:write key="ntp.176" />:</span><span id="sum_kadou_time_average_child" class="state_label_title6"><span></span></span><span class="state_label_title7"><gsmsg:write key="cmn.time" />/<gsmsg:write key="cmn.day" /></span><br>
                                  </td>

                                  <td  class="kdou_times_back2" id="anythingKadouChild0GraphBtn" align="right" style="width:5%;cursor:pointer;font-size:10px;color:#333333;padding-right:12px;">
                                    <input id="anythingKadouChild0GraphVal" type="hidden" value="0" />
                                    <img src="../nippou/images/btn_graph_sel.gif" alt="" />
                                  </td>
                                </tr>
                              </table>

                              <div id="anythingKadouChild0GraphArea" style="clear:both;">
                                <div id="anythingKadouChild0Graph" style=""></div>
                                <div class="tooltip_back" id="tooltipAnythingKadouChild0"></div>
                              </div>
                              <div style="width:100%;text-align:center;"><span id="anythingKadouChild0_more" class="sel_anythingKadouChild0_more display_none">▼<gsmsg:write key="ntp.133" /></span></div>
                              <input type="hidden" name="anythingKadouChild0page" value="1" />
                              <input type="hidden" name="anythingKadouChild0NowCount" value="0" />
                            </td>

                          </tr>



                          <%-- 結果・金額 --%>
                          <tr>
                            <td class="def_graph" valign="top" width="50%" height="200px" style="border:1px solid #939393;">

                              <table width="100%">
                                <tr>
                                  <td style="width:80%;font-size:10px;color:#333333;padding-left:10px;padding-top:9px;"><div class="shoudan_rsult_text"><gsmsg:write key="ntp.64" /></div></td>
                                  <td id="resultGraphBtn" align="right" style="width:20%;cursor:pointer;font-size:10px;color:#333333;padding-right:12px;padding-top:9px;">
                                    <input id="resultGraphVal" type="hidden" value="1" />
                                    <img src="../nippou/images/btn_graph_sel.gif" alt="" />
                                  </td>
                                </tr>
                              </table>

                              <div id="resultGraphArea" style="clear:both;">
                                <div id="resultGraph" ></div>
                                <div class="tooltip_back" id="tooltipResult"></div>
                              </div>
                            </td>
                            <td class="def_graph" height="200px" valign="top" style="border:1px solid #939393;">

                              <table width="100%">
                                <tr>
                                  <td style="width:80%;font-size:10px;color:#333333;padding-left:10px;padding-top:9px;" nowrap>
                                    <div class="shoudan_rsult_text"><gsmsg:write key="ntp.58" />&nbsp;&nbsp;</div>
                                    <div id="-1" class="state_label_sel_text sel_label_sel_all"><gsmsg:write key="ntp.166" /></div>
                                    <div id="1" class="state_label_sel_text state_label_sel_text2 state_label_notsel_text sel_label_sel_jutyu"><gsmsg:write key="ntp.69" /></div>
                                    <div id="2" class="state_label_sel_text state_label_sel_text2 state_label_notsel_text sel_label_sel_sityu"><gsmsg:write key="ntp.7" /></div>
                                    <div id="0" class="state_label_sel_text state_label_sel_text2 state_label_notsel_text sel_label_sel_shodan"><gsmsg:write key="ntp.68" /></div>
                                  </td>
                                  <td id="shohinGraphBtn" align="right" style="width:20%;cursor:pointer;font-size:10px;color:#333333;padding-right:12px;padding-top:9px;">
                                    <input id="shohinGraphVal" type="hidden" value="0" />
                                    <img src="../nippou/images/btn_graph_sel.gif" alt="" />
                                  </td>
                                </tr>
                              </table>


                              <div id="shohinGraphArea" style="clear:both;">
                                <div id="shohinGraph"></div>
                                <div class="tooltip_back" id="tooltipShohin"></div>
                              </div>
                            </td>
                          </tr>

                          <%-- 案件詳細 --%>
                          <tr id="anken_detail_area">
                            <td width="100%" colspan="2" style="padding:5px;">

                              <table width="100%">

                                <tr>

                                  <td valign="top" colspan="6">
                                    <table width="100%" class="anken_val_table" id="anken_val_table">

                                      <tr class="anken_val_title" id="anken_val_title">
                                        <td width="5%" class="anken_val_title">
                                        </td>
                                        <td width="30%"><gsmsg:write key="ntp.173" />
                                        </td>
                                        <td width="10%">

                                          <select style="font-size:12px;" name="ankenListState" id="ankenListState">
                                            <option value="-1" selected><gsmsg:write key="ntp.166" /></option>
                                            <option value="1"><gsmsg:write key="ntp.69" /></option>
                                            <option value="2"><gsmsg:write key="ntp.7" /></option>
                                            <option value="0"><gsmsg:write key="ntp.68" /></option>
                                          </select>
                                          <input name="ankenListState" type="hidden" value="-1" />

                                        </td>
                                        <td width="15%">

                                          <select style="font-size:12px;" id="ankenListMoney">
                                            <option value="1" selected><gsmsg:write key="ntp.54" /></option>
                                            <option value="0"><gsmsg:write key="ntp.53" /></option>
                                          </select>
                                          <input name="ankenListMoney" type="hidden" value="1" />

                                        </td>
                                        <td width="20%">

                                          <select style="font-size:12px;" id="ankenListOther">
                                            <option value="0"><gsmsg:write key="ntp.15" />・<gsmsg:write key="ntp.16" /></option>
                                            <option value="1"><gsmsg:write key="ntp.58" /></option>
                                            <option value="2"><gsmsg:write key="ntp.61" /></option>
                                            <option value="3"><gsmsg:write key="ntp.62" /></option>
                                            <option value="4"><gsmsg:write key="ntp.32" /></option>
                                            <option value="5"><gsmsg:write key="ntp.65" /></option>
                                            <option value="6"><gsmsg:write key="cmn.staff" /></option>
                                          </select>
                                          <input name="ankenListOther" type="hidden" value="0" />
                                        </td>

                                      </tr>

                                    </table>

                                    <div class="anken_list_more_area display_none">
                                      <input name="ankenDataPageNum" type="hidden" value="1" />
                                      <span id="ankenDataMore" class="sel_menu_content_text_more2">▼<gsmsg:write key="ntp.133" /></span>
                                    </div>

                                  </td>

                                  <td width="0">
                                  <%--
                                    <div id="compGraphArea">
                                      <div id="compGraph"></div>
                                      <div class="tooltip_back_mini" id="tooltipComp"></div>
                                    </div>
                                  --%>
                                  </td>

                                </tr>

                              </table>
                            </td>
                          </tr>





                          <%-- 案件詳細 (稼働時間)--%>
                          <tr id="anken_kadou_detail_area" class="display_none">
                            <td width="100%" colspan="2" style="padding:5px;">

                              <table width="100%">

                                <tr>

                                  <td valign="top" colspan="6">
                                    <table width="100%" class="anken_kadou_val_table" id="anken_kadou_val_table">

                                      <tr class="anken_kadou_val_title" id="anken_kadou_val_title">
                                        <td width="5%" class="anken_kadou_val_title">
                                        </td>

                                        <td width="20%"><gsmsg:write key="ntp.173" /></td>

                                        <td width="15%"><gsmsg:write key="ntp.170" />(h)</td>

                                        <td width="5%"><gsmsg:write key="ntp.174" /></td>

                                        <td width="10%"><gsmsg:write key="ntp.71" />
                                          <input name="ankenKadouListState" type="hidden" value="-1" />
                                        </td>
                                        <td width="15%">

                                          <select style="font-size:12px;" id="ankenKadouListMoney">
                                            <option value="1" selected><gsmsg:write key="ntp.54" /></option>
                                            <option value="0"><gsmsg:write key="ntp.53" /></option>
                                          </select>
                                          <input name="ankenKadouListMoney" type="hidden" value="1" />

                                        </td>
                                        <td width="10%">

                                          <select style="font-size:12px;" id="ankenKadouListOther">
                                            <option value="0"><gsmsg:write key="ntp.15" />・<gsmsg:write key="ntp.16" /></option>
                                            <option value="1"><gsmsg:write key="ntp.58" /></option>
                                            <option value="2"><gsmsg:write key="ntp.61" /></option>
                                            <option value="3"><gsmsg:write key="ntp.62" /></option>
                                            <option value="4"><gsmsg:write key="ntp.32" /></option>
                                            <option value="5"><gsmsg:write key="ntp.65" /></option>
                                            <option value="6"><gsmsg:write key="cmn.staff" /></option>
                                          </select>
                                          <input name="ankenKadouListOther" type="hidden" value="0" />
                                        </td>

                                      </tr>

                                    </table>

                                    <div class="anken_kadou_list_more_area display_none">
                                      <input name="ankenKadouDataPageNum" type="hidden" value="1" />
                                      <span id="ankenKadouDataMore" class="sel_menu_content_text_more2">▼<gsmsg:write key="ntp.133" /></span>
                                    </div>

                                  </td>

                                  <td width="0"></td>

                                </tr>

                              </table>
                            </td>
                          </tr>









                          <%-- <gsmsg:write key="ntp.15" />・<gsmsg:write key="ntp.16" />詳細 (<gsmsg:write key="ntp.170" />)--%>
                          <tr id="kigyou_kadou_detail_area" class="display_none">
                            <td width="100%" colspan="2" style="padding:5px;">

                              <table width="100%">

                                <tr>

                                  <td valign="top" colspan="6">
                                    <table width="100%" class="kigyou_kadou_val_table" id="kigyou_kadou_val_table">

                                      <tr class="kigyou_kadou_val_title" id="kigyou_kadou_val_title">
                                        <td width="5%" class="kigyou_kadou_val_title">
                                        </td>

                                        <td width="50%"><gsmsg:write key="ntp.15" />・<gsmsg:write key="ntp.16" /></td>

                                        <td width="30%"><gsmsg:write key="ntp.170" />(h)</td>

                                        <td width="20%"><gsmsg:write key="ntp.174" /></td>

                                      </tr>

                                    </table>

                                    <div class="kigyou_kadou_list_more_area display_none">
                                      <input name="kigyouKadouDataPageNum" type="hidden" value="1" />
                                      <span id="kigyouKadouDataMore" class="sel_menu_content_text_more2">▼<gsmsg:write key="ntp.133" /></span>
                                    </div>

                                  </td>

                                  <td width="0"></td>

                                </tr>

                              </table>
                            </td>
                          </tr>






                          <%--<gsmsg:write key="ntp.3" /> (<gsmsg:write key="ntp.170" />)--%>
                          <tr id="kbunrui_kadou_detail_area" class="display_none">
                            <td width="100%" colspan="2" style="padding:5px;">

                              <table width="100%">

                                <tr>

                                  <td valign="top" colspan="6">
                                    <table width="100%" class="kbunrui_kadou_val_table" id="kbunrui_kadou_val_table">

                                      <tr class="kbunrui_kadou_val_title" id="kbunrui_kadou_val_title">
                                        <td width="5%" class="kbunrui_kadou_val_title">
                                        </td>

                                        <td width="20%"><gsmsg:write key="ntp.3" /></td>

                                        <td width="30%"><gsmsg:write key="ntp.170" />(h)</td>

                                        <td width="20%"><gsmsg:write key="ntp.174" /></td>

                                      </tr>

                                    </table>

                                    <div class="kbunrui_kadou_list_more_area display_none">
                                      <input name="kbunruiKadouDataPageNum" type="hidden" value="1" />
                                      <span id="kbunruiKadouDataMore" class="sel_menu_content_text_more2">▼<gsmsg:write key="ntp.133" /></span>
                                    </div>

                                  </td>

                                  <td width="0"></td>

                                </tr>

                              </table>
                            </td>
                          </tr>





                          <%--<gsmsg:write key="ntp.121" /> (<gsmsg:write key="ntp.170" />)--%>
                          <tr id="khouhou_kadou_detail_area" class="display_none">
                            <td width="100%" colspan="2" style="padding:5px;">

                              <table width="100%">

                                <tr>

                                  <td valign="top" colspan="6">
                                    <table width="100%" class="khouhou_kadou_val_table" id="khouhou_kadou_val_table">

                                      <tr class="khouhou_kadou_val_title" id="khouhou_kadou_val_title">
                                        <td width="5%" class="kbunrui_kadou_val_title">
                                        </td>

                                        <td width="20%"><gsmsg:write key="ntp.121" /></td>

                                        <td width="30%"><gsmsg:write key="ntp.170" />(h)</td>

                                        <td width="20%"><gsmsg:write key="ntp.174" /></td>

                                      </tr>

                                    </table>

                                    <div class="khouhou_kadou_list_more_area display_none">
                                      <input name="khouhouKadouDataPageNum" type="hidden" value="1" />
                                      <span id="khouhouKadouDataMore" class="sel_menu_content_text_more2">▼<gsmsg:write key="ntp.133" /></span>
                                    </div>

                                  </td>

                                  <td width="0"></td>

                                </tr>

                              </table>
                            </td>
                          </tr>






                        </table>

                      </td>
                    </tr>
                  </table>
                </td>
              </tr>

            </table>
          </div>