<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

      <table width="100%" cellpadding="0" cellspacing="0">
      <tr valign="top">
      <td width="80%" align="center" >
        <table cellpadding="0" cellspacing="0" border="1" width="100%" class="user_search2">
        <tr>
        <!-- キーワード -->
        <td class="detail_tbl" align="left" width="10%" nowrap><gsmsg:write key="cmn.keyword"/></td>
        <td class="user_search2_syosai_td_1" align="left" width="40%" nowrap><html:text property="usr040Keyword" style="width:255px;" maxlength="60" />
        <br>
        <html:checkbox name="usr040Form" property="usr040KeyKbnShainno" value="1" styleId="KbnSNo"/><label for="KbnSNo"><gsmsg:write key="cmn.employee.staff.number" /></label>&nbsp;
        <html:checkbox name="usr040Form" property="usr040KeyKbnName" value="1" styleId="KbnName"/><label for="KbnName"><gsmsg:write key="cmn.name" /></label>&nbsp;
        <html:checkbox name="usr040Form" property="usr040KeyKbnNameKn" value="1" styleId="KbnNameKn"/><label for="KbnNameKn"><gsmsg:write key="cmn.name.kana" /></label>&nbsp;
        <html:checkbox name="usr040Form" property="usr040KeyKbnMail" value="1" styleId="KbnMail"/><label for="KbnMail">E-MAIL</label>
        </td>
        <!-- 年齢 -->
        <td width="10%" class="detail_tbl" align="left" nowrap><gsmsg:write key="user.3" /></td>
        <td width="40%" class="user_search2_syosai_td_1" align="left">
          <html:text property="usr040agefrom" style="width:51px;" maxlength="2" />&nbsp;<gsmsg:write key="user.98" />&nbsp;
          ～&nbsp;<html:text property="usr040ageto" style="width:51px;" maxlength="2" />&nbsp;<gsmsg:write key="user.98" />
        </td>
        </tr>

        <tr>
        <!-- 所属グループ -->
        <td class="detail_tbl" align="left" nowrap><gsmsg:write key="cmn.affiliation.group" /></td>
        <td class="user_search2_syosai_td_1" align="left">
          <html:select property="selectgsid" styleClass="select02">
            <html:optionsCollection name="usr040Form" property="grpLabelList" value="value" label="label" />
          </html:select>
          <logic:notEqual name="usr040Form" property="usr040webmail" value="1">
            <input type="button" onclick="openGroupWindow(this.form.selectgsid, 'selectgsid', '0', '', 1)" class="group_btn" value="&nbsp;&nbsp;" id="usr040GroupBtn">
          </logic:notEqual>
        </td>
        <!-- 性別 -->
        <td class="detail_tbl" align="left" nowrap><gsmsg:write key="user.123" /></td>
        <td class="user_search2_syosai_td_1" align="left" nowrap>
          <logic:notEmpty name="usr040Form" property="seibetuLabelList">
            <bean:define id="seibetuVal" name="usr040Form" property="usr040seibetu" type="java.lang.String" />
            <logic:iterate id="seibetuMdl" name="usr040Form" property="seibetuLabelList" indexId="idxSeibetu">
              <logic:equal name="seibetuMdl" property="value" value="<%= seibetuVal %>">
                <input type="radio" name="usr040seibetu" id="usr040seibetu_<%= idxSeibetu %>" value="<bean:write name="seibetuMdl" property="value" />" checked /><label for="usr040seibetu_<%= idxSeibetu %>"><bean:write name="seibetuMdl" property="label" /></label>
              </logic:equal>
              <logic:notEqual name="seibetuMdl" property="value" value="<%= seibetuVal %>">
                <input type="radio" name="usr040seibetu" id="usr040seibetu_<%= idxSeibetu %>" value="<bean:write name="seibetuMdl" property="value" />" /><label for="usr040seibetu_<%= idxSeibetu %>"><bean:write name="seibetuMdl" property="label" /></label>
              </logic:notEqual>
            </logic:iterate>
          </logic:notEmpty>
        </td>
        </tr>

        <tr>
        <!-- 役職 -->
        <td class="detail_tbl" align="left" nowrap><gsmsg:write key="cmn.post" /></td>
        <td class="user_search2_syosai_td_1" align="left">
           <html:select property="usr040yakushoku">
            <html:optionsCollection name="usr040Form" property="posLabelList" value="value" label="label" />
          </html:select></td>
        <!-- 都道府県 -->
        <td class="detail_tbl" align="left" nowrap><gsmsg:write key="cmn.prefectures" /></td>
        <td class="user_search2_syosai_td_1" align="left">
          <html:select property="usr040tdfkCd">
            <html:optionsCollection name="usr040Form" property="tdfkLabelList" value="value" label="label" />
          </html:select>
        </td>

        </tr>

        <tr>
        <!-- 入社年月日 -->
        <td class="detail_tbl" align="left" nowrap><gsmsg:write key="user.156" /></td>
        <td colspan="3" class="user_search2_syosai_td_1">


          <span style="line-height:30px;" class="text_base">From:</span>
          <html:select property="usr040entranceYearFr" styleId="selYearsf">
          <html:optionsCollection name="usr040Form" property="usr040entranceYearFrLabel" value="value" label="label" />
          </html:select>
          <html:select property="usr040entranceMonthFr" styleId="selMonthsf">
          <html:optionsCollection name="usr040Form" property="usr040entranceMonthFrLabel" value="value" label="label" />
          </html:select>
          <html:select property="usr040entranceDayFr" styleId="selDaysf">
          <html:optionsCollection name="usr040Form" property="usr040entranceDayFrLabel" value="value" label="label" />
          </html:select>
          <input type="button" value="Cal" onclick="wrtCalendarByBtn(this.form.selDaysf, this.form.selMonthsf, this.form.selYearsf, 'usr040entranceFrCalBtn')" class="calendar_btn" id="usr040entranceFrCalBtn">
          <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#selYearsf')[0], $('#selMonthsf')[0], $('#selDaysf')[0], 1)">
          <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#selYearsf')[0], $('#selMonthsf')[0], $('#selDaysf')[0], 2)">
          <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#selYearsf')[0], $('#selMonthsf')[0], $('#selDaysf')[0], 3)">
          <input type="button" value="<gsmsg:write key="cmn.specified.no" />" class="btn_base0" onclick="return clearDate($('#selYearsf'), $('#selMonthsf'), $('#selDaysf'))">
          <br>
          <span style="line-height:30px;" class="text_base">To&nbsp;&nbsp;&nbsp;&nbsp;:</span>
          <html:select property="usr040entranceYearTo" styleId="selYearst">
          <html:optionsCollection name="usr040Form" property="usr040entranceYearFrLabel" value="value" label="label" />
          </html:select>
          <html:select property="usr040entranceMonthTo" styleId="selMonthst">
          <html:optionsCollection name="usr040Form" property="usr040entranceMonthFrLabel" value="value" label="label" />
          </html:select>
          <html:select property="usr040entranceDayTo" styleId="selDayst">
          <html:optionsCollection name="usr040Form" property="usr040entranceDayFrLabel" value="value" label="label" />
          </html:select>
          <input type="button" value="Cal" onclick="wrtCalendarByBtn(this.form.selDayst, this.form.selMonthst, this.form.selYearst, 'usr040entranceToCalBtn')" class="calendar_btn" id="usr040entranceToCalBtn">
          <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#selYearst')[0], $('#selMonthst')[0], $('#selDayst')[0], 1)">
          <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#selYearst')[0], $('#selMonthst')[0], $('#selDayst')[0], 2)">
          <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#selYearst')[0], $('#selMonthst')[0], $('#selDayst')[0], 3)">
          <input type="button" value="<gsmsg:write key="cmn.specified.no" />" class="btn_base0" onclick="return clearDate($('#selYearst'), $('#selMonthst'), $('#selDayst'))">

        </td>
        </tr>
        </table>
      </td>
      <td width="20%" align="center"></td>
      </tr>
    </table>
      <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" cellpadding="0" cellspacing="0">
    <tr>
    <td width="11%" class="detail_tbl usr_sort_text" nowrap><gsmsg:write key="cmn.sort.order" /></td>
    <td class="user_search2_syosai_td_2 usr_sort_text">
    <span class="text_bb2"><gsmsg:write key="cmn.first.key" /></span>
      <logic:notEqual name="usr040Form" property="sortKeyLabelList" value="">
        <html:select property="usr040sortKey" onchange="changeSortCombo();">
          <html:optionsCollection name="usr040Form" property="sortKeyLabelList" value="value" label="label" />
        </html:select>
      </logic:notEqual>
      <html:radio name="usr040Form" property="usr040orderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>" styleId="sort1_up" /><label for="sort1_up"><gsmsg:write key="cmn.order.asc" /></label>
      <html:radio name="usr040Form" property="usr040orderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>" styleId="sort1_dw" /><label for="sort1_dw"><gsmsg:write key="cmn.order.desc" /></label>
      &nbsp;&nbsp;&nbsp;&nbsp;

    <span class="text_bb2"><gsmsg:write key="cmn.second.key" /></span>
      <logic:notEqual name="usr040Form" property="sortKeyLabelList" value="">
        <html:select property="usr040sortKey2" onchange="changeSortCombo();">
          <html:optionsCollection name="usr040Form" property="sortKeyLabelList" value="value" label="label" />
        </html:select>
      </logic:notEqual>
      <html:radio name="usr040Form" property="usr040orderKey2" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>" styleId="sort2_up" /><label for="sort2_up"><gsmsg:write key="cmn.order.asc" /></label>
      <html:radio name="usr040Form" property="usr040orderKey2" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>" styleId="sort2_dw" /><label for="sort2_dw"><gsmsg:write key="cmn.order.desc" /></label>

    </td>
    </tr>
    </table>