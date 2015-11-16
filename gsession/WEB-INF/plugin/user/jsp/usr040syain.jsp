<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

    <table class="tl0 table_td_border2" width="100%" cellpadding="0" cellspacing="0">

    <tr>
    <th width="3%" align="center" class="detail_tbl" nowrap>
    <input type="checkbox" name="allCheck" onclick="changeChk();">
    </th>

    <!--  社員・職員番号／氏名／役職 -->
    <th width="22%" align="center" class="detail_tbl sort_select_area" nowrap>
    <a href="#" onClick="clickSortTitle(<%= jp.groupsession.v2.usr.GSConstUser.USER_SORT_SNO %>);">
    <span class="cel_user_data">
    <logic:equal name="usr040Form" property="usr040sortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_SNO) %>">
      <logic:equal name="usr040Form" property="usr040orderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>"><gsmsg:write key="cmn.employee.staff.number2" />▲</logic:equal>
      <logic:notEqual name="usr040Form" property="usr040orderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>"><gsmsg:write key="cmn.employee.staff.number2" />▼</logic:notEqual>
    </logic:equal>
    <logic:notEqual name="usr040Form" property="usr040sortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_SNO) %>">
    <gsmsg:write key="cmn.employee.staff.number2" />
    </logic:notEqual>
    </span>
    </a>
    <span class="cel_user_data">／</span>
    <a href="#" onClick="clickSortTitle(<%= jp.groupsession.v2.usr.GSConstUser.USER_SORT_NAME %>);">
    <span class="cel_user_data">
    <logic:equal name="usr040Form" property="usr040sortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_NAME) %>">
      <logic:equal name="usr040Form" property="usr040orderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>"><gsmsg:write key="cmn.name" />▲</logic:equal>
      <logic:notEqual name="usr040Form" property="usr040orderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>"><gsmsg:write key="cmn.name" />▼</logic:notEqual>
    </logic:equal>
    <logic:notEqual name="usr040Form" property="usr040sortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_NAME) %>">
    <gsmsg:write key="cmn.name" />
    </logic:notEqual>
    </span></a>
    <span class="cel_user_data">／</span>
    <a href="#" onClick="clickSortTitle(<%= jp.groupsession.v2.usr.GSConstUser.USER_SORT_YKSK %>);">
    <span class="cel_user_data">
    <logic:equal name="usr040Form" property="usr040sortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_YKSK) %>">
      <logic:equal name="usr040Form" property="usr040orderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>"><gsmsg:write key="cmn.post" />▲</logic:equal>
      <logic:notEqual name="usr040Form" property="usr040orderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>"><gsmsg:write key="cmn.post" />▼</logic:notEqual>
    </logic:equal>
    <logic:notEqual name="usr040Form" property="usr040sortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_YKSK) %>">
    <gsmsg:write key="cmn.post" />
    </logic:notEqual>
    </span></a>

    </th>
    <th width="20%" align="center" class="detail_tbl" nowrap>
    <span class="cel_user_data">
    <gsmsg:write key="cmn.affiliation.group" />
    </span>
    </th>
    <th width="20%" align="center" class="detail_tbl" nowrap>
    <span class="cel_user_data">
    <gsmsg:write key="user.8" />
    </span>
    </th>
    <th width="20%" align="center" class="detail_tbl" nowrap>
    <span class="cel_user_data">
    <gsmsg:write key="cmn.mailaddress" />
    </span>
    </th>
    <th width="15%" align="center" class="detail_tbl" nowrap>
    <span class="cel_user_data">
    <gsmsg:write key="cmn.label" />
    </span>
    </th>
    </tr>

    <logic:notEmpty name="usr040Form" property="usr040users">
    <logic:iterate id="user" name="usr040Form" property="usr040users" scope="request" indexId="idx">
    <tr>
    <!-- チェックボックス -->
    <td align="center"><html:multibox name="usr040Form" property="usr040selectUser"><bean:write name="user"  property="usrSid" /></html:multibox></td>
    <!-- 写真 -->
    <td>
      <table class="clear_table" width="100%">
      <tr>
      <td rowspan="3" width="50px">
      <div style="padding-left:5px;padding-right:10px;">
        <logic:equal name="user" property="binSid" value="0">
          <img  class="comment_Img" src="../user/images/photo.gif" name="userImage" alt="<gsmsg:write key="cmn.photo" />" border="1" width="45px" />
        </logic:equal>

        <logic:notEqual name="user" property="binSid" value="0">
        <logic:equal name="user" property="usiPictKf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>">
          <div align="center" class="photo_hikokai2"><span style="color:#fc2929;"><gsmsg:write key="cmn.private" /></span></div>
        </logic:equal>
        <logic:notEqual name="user" property="usiPictKf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>">
          <img class="comment_Img" src="../common/cmn100.do?CMD=getImageFile&cmn100binSid=<bean:write name='user' property='binSid' />" alt="<gsmsg:write key="cmn.photo" />" width="45px" />
        </logic:notEqual>
        </logic:notEqual>
      </div>
      </td>
      <!-- 社員/職員番号 -->
      <td align="left" nowrap>
       <span style="font-color:#eeeeee;font-size:12px;">
       <bean:write name="user" property="usiSyainNo" />&nbsp;
       </span>
      </td>
      </tr>
    <!-- 名前 -->
    <tr>
      <td align="left" nowrap>
       <a href="javaScript:void(0);" onClick="openUserInfoWindow(<bean:write name="user" property="usrSid" />);">
       <span class="text_link2">
       <bean:write name="user" property="usiSei" />&nbsp;<bean:write name="user" property="usiMei" />
       </span>
       </a>
      </td>
    </tr>
    <!-- 役職 -->
    <tr>
      <td align="left" nowrap>
       <span class="cel_user_data">
       <bean:write name="user" property="usiYakusyoku" />&nbsp;
       </span>
      </td>
      </tr>
      </table>
    </td>

    <!-- 所属グループ -->
    <td>
        <logic:notEmpty name="user" property="belongGrpList">
          <logic:iterate id="grp" name="user" property="belongGrpList">
            <div class="cel_user_data"><bean:write name="grp" property="groupName"/></div>
          </logic:iterate>
        </logic:notEmpty>
    </td>

    <!-- 電話・内線 -->
    <%
      String strUsiTel1 = "";
      String strUsiTelNai1 = "";
      String strUsiTel2 = "";
      String strUsiTelNai2 = "";
      String strUsiTel3 = "";
      String strUsiTelNai3 = "";
    %>
    <logic:notEmpty name="user" property="usiTel1">
      <bean:define id="u_usiTel1" name="user" property="usiTel1" type="java.lang.String" />
      <% strUsiTel1 = jp.co.sjts.util.NullDefault.getString(((String)pageContext.getAttribute("u_usiTel1",PageContext.PAGE_SCOPE)), ""); %>
    </logic:notEmpty>
    <logic:notEmpty name="user" property="usiTelNai1">
      <bean:define id="u_usiTelNai1" name="user" property="usiTelNai1" type="java.lang.String" />
      <% strUsiTelNai1 = jp.co.sjts.util.NullDefault.getString(((String)pageContext.getAttribute("u_usiTelNai1",PageContext.PAGE_SCOPE)), ""); %>
    </logic:notEmpty>
    <logic:notEmpty name="user" property="usiTel2">
      <bean:define id="u_usiTel2" name="user" property="usiTel2" type="java.lang.String" />
      <% strUsiTel2 = jp.co.sjts.util.NullDefault.getString(((String)pageContext.getAttribute("u_usiTel2",PageContext.PAGE_SCOPE)), ""); %>
    </logic:notEmpty>
    <logic:notEmpty name="user" property="usiTelNai2">
      <bean:define id="u_usiTelNai2" name="user" property="usiTelNai2" type="java.lang.String" />
      <% strUsiTelNai2 = jp.co.sjts.util.NullDefault.getString(((String)pageContext.getAttribute("u_usiTelNai2",PageContext.PAGE_SCOPE)), ""); %>
    </logic:notEmpty>
    <logic:notEmpty name="user" property="usiTel3">
      <bean:define id="u_usiTel3" name="user" property="usiTel3" type="java.lang.String" />
      <% strUsiTel3 = jp.co.sjts.util.NullDefault.getString(((String)pageContext.getAttribute("u_usiTel3",PageContext.PAGE_SCOPE)), ""); %>
    </logic:notEmpty>
    <logic:notEmpty name="user" property="usiTelNai3">
      <bean:define id="u_usiTelNai3" name="user" property="usiTelNai3" type="java.lang.String" />
      <% strUsiTelNai3 = jp.co.sjts.util.NullDefault.getString(((String)pageContext.getAttribute("u_usiTelNai3",PageContext.PAGE_SCOPE)), ""); %>
    </logic:notEmpty>

    <td>
      <% if (strUsiTel1.length() > 0 || strUsiTelNai1.length() > 0) { %>
      <span class="cel_user_data">
      <logic:notEmpty name="user" property="usiTel1"><bean:write name="user" property="usiTel1" /></logic:notEmpty>
      <logic:notEmpty name="user" property="usiTelNai1"><bean:write name="user" property="usiTelNai1" /></logic:notEmpty>
      <logic:notEmpty name="user" property="usiTelCmt1">&nbsp;<span style="font-size: 80%;">(<bean:write name="user" property="usiTelCmt1" />)</span></logic:notEmpty>
      </span><br>
      <% } %>
      <% if (strUsiTel2.length() > 0 || strUsiTelNai2.length() > 0) { %>
      <span class="cel_user_data">
      <logic:notEmpty name="user" property="usiTel2"><bean:write name="user" property="usiTel2" /></logic:notEmpty>
      <logic:notEmpty name="user" property="usiTelNai2"><bean:write name="user" property="usiTelNai2" /></logic:notEmpty>
      <logic:notEmpty name="user" property="usiTelCmt2">&nbsp;<span style="font-size: 80%;">(<bean:write name="user" property="usiTelCmt2" />)</span></logic:notEmpty>
      </span><br>
      <% } %>
      <% if (strUsiTel3.length() > 0 || strUsiTelNai3.length() > 0) { %>
      <span class="cel_user_data">
      <logic:notEmpty name="user" property="usiTel3"><bean:write name="user" property="usiTel3" /></logic:notEmpty>
      <logic:notEmpty name="user" property="usiTelNai3"><bean:write name="user" property="usiTelNai3" /></logic:notEmpty>
      <logic:notEmpty name="user" property="usiTelCmt3">&nbsp;<span style="font-size: 80%;">(<bean:write name="user" property="usiTelCmt3" />)</span></logic:notEmpty>
      </span>
      <% } %>
    </td>
    <!-- メールアドレス -->
    <td>

      <logic:notEmpty name="user" property="usiMail1">
      <span class="cel_user_data">
      <a href="mailto:<bean:write name="user" property="usiMail1" />"><bean:write name="user" property="mailAddress1" filter="false" /></a>
      <logic:notEmpty name="user" property="usiMailCmt1">&nbsp;<span style="font-size: 80%;">(<bean:write name="user" property="usiMailCmt1" />)</span></logic:notEmpty>
      </span>
      <br>
      </logic:notEmpty>
      <logic:notEmpty name="user" property="usiMail2">
      <span class="cel_user_data">
      <a href="mailto:<bean:write name="user" property="usiMail2" />">
      <bean:write name="user" property="mailAddress2" filter="false" /></a>
      <logic:notEmpty name="user" property="usiMailCmt2">&nbsp;<span style="font-size: 80%;">(<bean:write name="user" property="usiMailCmt2" />)</span></logic:notEmpty>
      </span>
      <br>
      </logic:notEmpty>
      <logic:notEmpty name="user" property="usiMail3">
      <span class="cel_user_data">
      <a href="mailto:<bean:write name="user" property="usiMail3" />">
      <bean:write name="user" property="mailAddress3" filter="false" /></a>
      <logic:notEmpty name="user" property="usiMailCmt3">&nbsp;<span style="font-size: 80%;">(<bean:write name="user" property="usiMailCmt3" />)</span></logic:notEmpty>
      </span>
      </logic:notEmpty>

    </td>
    <!-- ラベル -->
    <td>
        <logic:notEmpty name="user" property="viewLabelName">
        <span class="label_style"><bean:write name="user" property="viewLabelName"/></span>
        </logic:notEmpty>
    </td>
    </tr>

    </logic:iterate>
    </logic:notEmpty>

    </table>
