<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

    <!-- 取込みユーザ名 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="user.usr032kn.4" /></span>
    </td>
    <td valign="middle" align="left" class="td_nrlb" width="100%">
      <logic:notEmpty name="usr032knForm" property="usr032knImpList" scope="request">
        <logic:iterate id="uinf" name="usr032knForm" property="usr032knImpList" scope="request">
          <span class="text_base">
          <logic:equal name="uinf" property="usiHenkou" value="1">
            <span class="text_r2">※</span>
          </logic:equal>
          <logic:equal name="uinf" property="usiHenkou" value="0">
            <span class="text_r2">　&nbsp;</span>
          </logic:equal>
          &nbsp;<bean:write name="uinf" property="usigpSid" />&nbsp;&nbsp;<bean:write name="uinf" property="usigpNm" />&nbsp;/<bean:write name="uinf" property="usiSyainNo" />&nbsp;&nbsp;<bean:write name="uinf" property="usiSei" />&nbsp;&nbsp;<bean:write name="uinf" property="usiMei" /></span><br>
        </logic:iterate>
        <logic:equal name="usr032knForm" property="usr032knGrpflg" value="1">
          <br>
          <span class="text_r2">※</span><span class="text_base"><gsmsg:write key="user.usr032kn.5" /></span>
        </logic:equal>
      </logic:notEmpty>
    </td>
    </tr>

        <!-- 役職 -->
    <logic:notEmpty name="usr032knForm" property="posList" scope="request">
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.post" /></span>
    </td>
    <td valign="middle" align="left" class="td_nrlb" width="100%">
      <span class="text_r1">
        <gsmsg:write key="user.18" /><br>
        <gsmsg:write key="user.20" />
      </span>
      <br>
      <span class="text_base">
        <logic:iterate id="posName" name="usr032knForm" property="posList" scope="request">
          <bean:write name="posName" /><br>
        </logic:iterate>
      </span>
    </td>
    </tr>
    </logic:notEmpty>

    <tr>
    <td valign="middle" align="left" class="td_type5" nowrap colspan="2">
      <span class="text_tl2"><gsmsg:write key="user.86" /></span><span class="text_r2"></span>
    </td>
    </tr>

    <logic:equal name="usr032knForm" property="changePassword" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.CHANGEPASSWORD_PARMIT) %>">
    <!-- パスワード変更 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.change.password" /></span>
    </td>
    <td valign="middle" align="left" class="td_nrlb" width="100%">
    <logic:equal name="usr032knForm" property="usr032PswdKbn" value="1">
    <span class="text_base"><gsmsg:write key="user.2" /></span>
    </logic:equal>
    </td>
    </tr>
    </logic:equal>

    <!-- 上書き -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.overwrite" /></span>
    </td>
    <td valign="middle" align="left" class="td_nrlb" width="100%">
    <logic:equal name="usr032knForm" property="usr032updateFlg" value="1">
    <span class="text_base"><gsmsg:write key="user.1" /></span>
      <logic:equal name="usr032knForm" property="usr032updatePassFlg" value="1">
        <br><span class="text_base"><gsmsg:write key="user.no.pass.override" /></span>
      </logic:equal>
    </logic:equal>
    </td>
    </tr>

    <!-- グループ作成 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
    <span class="text_bb1"><gsmsg:write key="user.usr032.1" /></span>
    </td>
    <td valign="middle" align="left" class="td_nrlb" width="100%">
    <logic:equal name="usr032knForm" property="usr032createFlg" value="1">
    <span class="text_base"><gsmsg:write key="user.usr032.2" /></span>
    </logic:equal>
    </td>
    </tr>