<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

    <!--  グループ作成 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
      <span class="text_bb1"><gsmsg:write key="user.usr032.1" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" colspan="2">
    <html:checkbox name="usr032Form" property="usr032createFlg" value="1" styleId="createFlg" /><label for="createFlg" class="text_base"><gsmsg:write key="user.usr032.2" /></label>
    </td>
    </tr>