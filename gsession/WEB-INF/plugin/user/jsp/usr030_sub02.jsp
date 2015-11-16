<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

      <table width="100%" cellpadding="0" cellspacing="0">
      <tr>
      <td width="50%" valign="top">

        <table cellpadding="3" cellspacing="0" border="1" width="95%" class="user_search2">

        <tr>
        <td class="detail_tbl" align="left" width="20%" nowrap><gsmsg:write key="cmn.user.id" /></td>
        <td class="user_search2_syosai_td_1" align="left" width="80%"><html:text property="usr030userId" style="width:273px;" maxlength="20" /></td>

        <td width="20%" class="detail_tbl" align="left" nowrap><gsmsg:write key="cmn.affiliation.group" /></td>
        <td width="80%" class="user_search2_syosai_td_1" align="left">
          <html:select property="selectgsid" styleClass="select01">
            <html:optionsCollection name="usr030Form" property="grpLabelList" value="value" label="label" />
          </html:select>
          <input type="button" onclick="openGroupWindow(this.form.selectgsid, 'selectgsid', '0', '', 1)" class="group_btn2" value="&nbsp;&nbsp;" id="usr030GroupBtn">
        </td>

        </tr>

        <tr>
        <td class="detail_tbl" align="left" width="20%" nowrap><gsmsg:write key="cmn.employee.staff.number" /></td>
        <td class="user_search2_syosai_td_1" align="left" width="80%"><html:text property="usr030shainno" style="width:273px;" maxlength="20" /></td>

        <td class="detail_tbl" align="left" nowrap><gsmsg:write key="cmn.post" /></td>
        <td class="user_search2_syosai_td_1" align="left">
          <html:select property="usr030yakushoku">
            <html:optionsCollection name="usr030Form" property="posLabelList" value="value" label="label" />
          </html:select>
        </td>

        </tr>

        <tr>
        <td class="detail_tbl" align="left" nowrap><gsmsg:write key="cmn.name" /></td>

        <td class="user_search2_syosai_td_1" align="left">

          <gsmsg:write key="cmn.lastname" />&nbsp;<html:text property="usr030sei" style="width:93px;" maxlength="<%= String.valueOf(GSConstUser.MAX_LENGTH_USER_NAME_SEI) %>" />&nbsp;&nbsp;
          <gsmsg:write key="cmn.name3" />&nbsp;<html:text property="usr030mei" style="width:93px;" maxlength="<%= String.valueOf(GSConstUser.MAX_LENGTH_USER_NAME_MEI) %>" /></td>

        <td class="detail_tbl" align="left" nowrap><gsmsg:write key="user.123" /></td>
        <td class="user_search2_syosai_td_1" align="left">
          <logic:notEmpty name="usr030Form" property="seibetuLabelList">
            <bean:define id="seibetuVal" name="usr030Form" property="usr030seibetu" type="java.lang.String" />
            <logic:iterate id="seibetuMdl" name="usr030Form" property="seibetuLabelList" indexId="idxSeibetu">
              <logic:equal name="seibetuMdl" property="value" value="<%= seibetuVal %>">
                <input type="radio" name="usr030seibetu" id="usr030seibetu_<%= idxSeibetu %>" value="<bean:write name="seibetuMdl" property="value" />" checked /><label for="usr030seibetu_<%= idxSeibetu %>"><bean:write name="seibetuMdl" property="label" /></label>
              </logic:equal>
              <logic:notEqual name="seibetuMdl" property="value" value="<%= seibetuVal %>">
                <input type="radio" name="usr030seibetu" id="usr030seibetu_<%= idxSeibetu %>" value="<bean:write name="seibetuMdl" property="value" />" /><label for="usr030seibetu_<%= idxSeibetu %>"><bean:write name="seibetuMdl" property="label" /></label>
              </logic:notEqual>
            </logic:iterate>
          </logic:notEmpty>
        </td>

        </tr>

        <tr>
        <td class="detail_tbl" align="left" nowrap><gsmsg:write key="cmn.name.kana" /></td>
        <td class="user_search2_syosai_td_1" align="left" nowrap>
          <gsmsg:write key="user.149" />&nbsp;<html:text property="usr030seikn" style="width:93px;" maxlength="<%= String.valueOf(GSConstUser.MAX_LENGTH_USER_NAME_SEI_KN) %>" />&nbsp;&nbsp;
          <gsmsg:write key="user.150" />&nbsp;<html:text property="usr030meikn" style="width:93px;" maxlength="<%= String.valueOf(GSConstUser.MAX_LENGTH_USER_NAME_MEI_KN) %>" /></td>

        <td class="detail_tbl" align="left" nowrap>E-MAIL</td>
        <td class="user_search2_syosai_td_1" align="left">
          <html:text property="usr030mail" style="width:273px;" maxlength="50" /></td>

        </tr>

        <tr>
        <td class="detail_tbl" align="left" nowrap><gsmsg:write key="user.3" /></td>
        <td class="user_search2_syosai_td_1" align="left">
          <html:text property="usr030agefrom" style="width:51px;" maxlength="2" />&nbsp;<gsmsg:write key="user.98" />&nbsp;
          ～&nbsp;<html:text property="usr030ageto" style="width:51px;" maxlength="2" />&nbsp;<gsmsg:write key="user.98" /></td>

        <td class="detail_tbl" align="left" nowrap><gsmsg:write key="cmn.prefectures" /></td>
        <td class="user_search2_syosai_td_1" align="left">
          <html:select property="usr030tdfkCd">
            <html:optionsCollection name="usr030Form" property="tdfkLabelList" value="value" label="label" />
          </html:select>
        </td>

        </tr>




      <tr>
        <td class="detail_tbl" align="left" nowrap><gsmsg:write key="user.122" /></td>
        <td colspan="3" class="user_search2_syosai_td_1">



          <span style="line-height:30px;" class="text_base">From:</span>
          <html:select property="usr030entranceYearFr" styleId="selYearsf">
          <html:optionsCollection name="usr030Form" property="usr030entranceYearFrLabel" value="value" label="label" />
          </html:select>
          <html:select property="usr030entranceMonthFr" styleId="selMonthsf">
          <html:optionsCollection name="usr030Form" property="usr030entranceMonthFrLabel" value="value" label="label" />
          </html:select>
          <html:select property="usr030entranceDayFr" styleId="selDaysf">
          <html:optionsCollection name="usr030Form" property="usr030entranceDayFrLabel" value="value" label="label" />
          </html:select>
          <input type="button" value="Cal" onclick="wrtCalendarByBtn(this.form.selDaysf, this.form.selMonthsf, this.form.selYearsf, 'usr030entranceFrCalBtn')" class="calendar_btn" id="usr030entranceFrCalBtn">
          <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#selYearsf')[0], $('#selMonthsf')[0], $('#selDaysf')[0], 1)">
          <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#selYearsf')[0], $('#selMonthsf')[0], $('#selDaysf')[0], 2)">
          <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#selYearsf')[0], $('#selMonthsf')[0], $('#selDaysf')[0], 3)">
          <input type="button" value="<gsmsg:write key="cmn.specified.no" />" class="btn_base0" onclick="return clearDate($('#selYearsf'), $('#selMonthsf'), $('#selDaysf'))">
          <br>
          <span style="line-height:30px;" class="text_base">To&nbsp;&nbsp;&nbsp;&nbsp;:</span>
          <html:select property="usr030entranceYearTo" styleId="selYearst">
          <html:optionsCollection name="usr030Form" property="usr030entranceYearFrLabel" value="value" label="label" />
          </html:select>
          <html:select property="usr030entranceMonthTo" styleId="selMonthst">
          <html:optionsCollection name="usr030Form" property="usr030entranceMonthFrLabel" value="value" label="label" />
          </html:select>
          <html:select property="usr030entranceDayTo" styleId="selDayst">
          <html:optionsCollection name="usr030Form" property="usr030entranceDayFrLabel" value="value" label="label" />
          </html:select>
          <input type="button" value="Cal" onclick="wrtCalendarByBtn(this.form.selDayst, this.form.selMonthst, this.form.selYearst, 'usr030entranceToCalBtn')" class="calendar_btn" id="usr030entranceToCalBtn">
          <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#selYearst')[0], $('#selMonthst')[0], $('#selDayst')[0], 1)">
          <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#selYearst')[0], $('#selMonthst')[0], $('#selDayst')[0], 2)">
          <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#selYearst')[0], $('#selMonthst')[0], $('#selDayst')[0], 3)">
          <input type="button" value="<gsmsg:write key="cmn.specified.no" />" class="btn_base0" onclick="return clearDate($('#selYearst'), $('#selMonthst'), $('#selDayst'))">



        </td>

      </tr>




        </table>

      </td>

      </tr>

      <tr>
        <td>
          <img src="../common/images/spacer.gif" width="1px" height="5px" border="0">
        </td>
      </tr>

      <tr>
      <td align="center"><input type="button" name="btn_search" class="btn_search_n1" value="<gsmsg:write key="cmn.search" />" onClick="buttonPush('searchSyosai');"></td>

      </tr>
      </table>
