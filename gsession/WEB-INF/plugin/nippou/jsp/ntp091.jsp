<%@ page pageEncoding="Windows-31J" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.preferences.menu" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css' type='text/css'>
<link rel=stylesheet href='../nippou/css/nippou.css' type='text/css'>

</head>

<body class="body_03">
<html:form action="/nippou/ntp091">
<input type="hidden" name="CMD" value="">
<html:hidden property="dspMod" />
<html:hidden property="listMod" />
<html:hidden property="backScreen" />
<html:hidden property="ntp010DspDate"/>
<html:hidden property="ntp010SelectUsrSid"/>
<html:hidden property="ntp010SelectUsrKbn"/>
<html:hidden property="ntp010SelectDate" />
<html:hidden property="ntp010NipSid" />
<html:hidden property="ntp010DspGpSid"/>
<html:hidden property="ntp020SelectUsrSid"/>
<html:hidden property="ntp030SelectUsrSid"/>

<html:hidden property="ntp100PageNum" />
<html:hidden property="ntp100Slt_page1" />
<html:hidden property="ntp100Slt_page2" />
<html:hidden property="ntp100OrderKey1" />
<html:hidden property="ntp100SortKey1" />
<html:hidden property="ntp100OrderKey2" />
<html:hidden property="ntp100SortKey2" />

<html:hidden property="ntp100SvSltGroup" />
<html:hidden property="ntp100SvSltUser" />
<html:hidden property="ntp100SvSltYearFr" />
<html:hidden property="ntp100SvSltMonthFr" />
<html:hidden property="ntp100SvSltDayFr" />
<html:hidden property="ntp100SvSltYearTo" />
<html:hidden property="ntp100SvSltMonthTo" />
<html:hidden property="ntp100SvSltDayTo" />
<html:hidden property="ntp100SvKeyWordkbn" />
<html:hidden property="ntp100SvKeyValue" />
<html:hidden property="ntp100SvOrderKey1" />
<html:hidden property="ntp100SvSortKey1" />
<html:hidden property="ntp100SvOrderKey2" />
<html:hidden property="ntp100SvSortKey2" />

<html:hidden property="ntp100SltGroup" />
<html:hidden property="ntp100SltUser" />
<html:hidden property="ntp100SltYearFr" />
<html:hidden property="ntp100SltMonthFr" />
<html:hidden property="ntp100SltDayFr" />
<html:hidden property="ntp100SltYearTo" />
<html:hidden property="ntp100SltMonthTo" />
<html:hidden property="ntp100SltDayTo" />
<html:hidden property="ntp100KeyWordkbn" />

<logic:notEmpty name="ntp091Form" property="ntp091userSid">
<logic:iterate id="usid" name="ntp091Form" property="ntp091userSid">
  <input type="hidden" name="ntp091userSid" value="<bean:write name="usid" />">
</logic:iterate>
</logic:notEmpty>


<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <!-- タイトル -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    <td width="100%" class="header_ktool_bg_text">[ <gsmsg:write key="ntp.1" /><gsmsg:write key="ntp.10" /><gsmsg:write key="cmn.setting" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('ntp091kakunin');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('ntp091back');"></td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <!-- エラーメッセージ -->
    <logic:messagesPresent message="false">
      <span class="TXT02"><html:errors/></span>
    </logic:messagesPresent>

    <table cellpadding="10" cellspacing="0" border="0" width="100%" class="tl_u2">
    <tr>
    <td width="10%" valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.time" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">
      <span class="text_bb1"><gsmsg:write key="cmn.starttime" />：</span>
      <!-- 時 -->
      <html:select property="ntp091DefFrH">
        <html:optionsCollection name="ntp091Form" property="ntp091HourLabel" value="value" label="label" />
      </html:select>
      <!-- 分 -->
      <html:select property="ntp091DefFrM">
        <html:optionsCollection name="ntp091Form" property="ntp091MinuteLabel" value="value" label="label" />
      </html:select>

    <br>
      <span class="text_bb1"><gsmsg:write key="cmn.endtime" />：</span>
      <!-- 時 -->
      <html:select property="ntp091DefToH">
        <html:optionsCollection name="ntp091Form" property="ntp091HourLabel" value="value" label="label" />
      </html:select>
      <!-- 分 -->
      <html:select property="ntp091DefToM">
        <html:optionsCollection name="ntp091Form" property="ntp091MinuteLabel" value="value" label="label" />
      </html:select>

    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
      <span class="text_bb1"><gsmsg:write key="schedule.10" /></span><span class="text_r2">※</span>
    </td>

    <td valign="middle" align="left" class="td_wt">


    <bean:define id="colorMsg1" value=""/>
    <bean:define id="colorMsg2" value=""/>
    <bean:define id="colorMsg3" value=""/>
    <bean:define id="colorMsg4" value=""/>
    <bean:define id="colorMsg5" value=""/>
    <logic:iterate id="msgStr" name="ntp091Form" property="ntp091ColorMsgList" indexId="msgId" type="java.lang.String">
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

    <span class="sc_block_color_1"><html:radio name="ntp091Form" property="ntp091Fcolor" styleId="ntp091Fcolor1" value="1" /></span><span class="text_base"><label for="ntp091Fcolor1" style="cursor:pointer;"><%= colorMsg1 %></label></span>
    <span class="sc_block_color_2"><html:radio name="ntp091Form" property="ntp091Fcolor" styleId="ntp091Fcolor2" value="2" /></span><span class="text_base"><label for="ntp091Fcolor2" style="cursor:pointer;"><%= colorMsg2 %></label></span>
    <span class="sc_block_color_3"><html:radio name="ntp091Form" property="ntp091Fcolor" styleId="ntp091Fcolor3" value="3" /></span><span class="text_base"><label for="ntp091Fcolor3" style="cursor:pointer;"><%= colorMsg3 %></label></span>
    <span class="sc_block_color_4"><html:radio name="ntp091Form" property="ntp091Fcolor" styleId="ntp091Fcolor4" value="4" /></span><span class="text_base"><label for="ntp091Fcolor4" style="cursor:pointer;"><%= colorMsg4 %></label></span>
    <span class="sc_block_color_5"><html:radio name="ntp091Form" property="ntp091Fcolor" styleId="ntp091Fcolor5" value="5" /></span><span class="text_base"><label for="ntp091Fcolor5" style="cursor:pointer;"><%= colorMsg5 %></label></span>
    </td>
    </tr>
<%--
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
      <span class="text_bb1">公開</span><span class="text_r2">※</span>
    </td>

    <td valign="middle" align="left" class="td_wt">
      <html:radio name="ntp091Form" property="ntp091PubFlg" styleId="ntp091PubFlg0" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.DSP_PUBLIC) %>" /><span class="text_base"><label for="ntp091PubFlg0">公開</label></span>&nbsp;
      <html:radio name="ntp091Form" property="ntp091PubFlg" styleId="ntp091PubFlg1" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.DSP_NOT_PUBLIC) %>" /><span class="text_base"><label for="ntp091PubFlg1">非公開</label>&nbsp;</span>
      <html:radio name="ntp091Form" property="ntp091PubFlg" styleId="ntp091PubFlg2" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.DSP_YOTEIARI) %>" /><span class="text_base"><label for="ntp091PubFlg2">予定有り（詳細は非表示）</label>&nbsp;</span>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
      <span class="text_bb1">編集権限</span><span class="text_r2">※</span>
    </td>

    <td valign="middle" align="left" class="td_wt">
      <html:radio name="ntp091Form" property="ntp091Edit" styleId="ntp091Edit0" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.EDIT_CONF_NONE) %>" /><span class="text_base"><label for="ntp091Edit0">制限無し</label></span>&nbsp;
      <html:radio name="ntp091Form" property="ntp091Edit" styleId="ntp091Edit1" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.EDIT_CONF_OWN) %>" /><span class="text_base"><label for="ntp091Edit1">本人・登録者のみ</label>&nbsp;</span>
      <html:radio name="ntp091Form" property="ntp091Edit" styleId="ntp091Edit2" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.EDIT_CONF_GROUP) %>" /><span class="text_base"><label for="ntp091Edit2">所属グループ・登録者のみ</label>&nbsp;</span>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
      <span class="text_bb1">業務内容</span><span class="text_r2">※</span>
    </td>

    <td valign="middle" align="left" class="td_wt">
      <logic:notEmpty name="ntp091Form" property="ntp091GyomuLabel">
      <html:select property="ntp091DefGyomu">
        <html:optionsCollection name="ntp091Form" property="ntp091GyomuLabel" label="value" label="label" />
      </html:select>
      </logic:notEmpty>
    </td>
    </tr>

    <tr>
    <td width="10%" valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
    <span class="text_bb1">日報確認管理者</span>
    </td>

    <td valign="middle" align="left" class="td_wt" >
      <table width="0%" border="0">
      <tbody>
      <tr>
      <td width="40%" class="table_bg_A5B4E1" align="center"><span class="text_bb1">メンバー</span></td>
      <td width="20%" align="center">&nbsp;</td>
      <td width="40%" class="table_bg_A5B4E1" align="left">
        <html:select name="ntp091Form" property="ntp091groupSid" onchange="buttonPush('changeGrp');">
          <logic:notEmpty name="ntp091Form" property="ntp091GroupList">
            <html:optionsCollection name="ntp091Form" property="ntp091GroupList" label="value" label="label" />
          </logic:notEmpty>
        </html:select>
        <input type="button" onclick="openGroupWindow(this.form.ntp091groupSid, 'ntp091groupSid', '1')" class="group_btn" value="&nbsp;&nbsp;" id="ntp091GroupBtn">
      </td>
      </tr>

      <tr>
      <td align="center">
        <html:select name="ntp091Form" property="ntp091SelectRightUser" size="6" multiple="true" styleClass="select01">
          <logic:notEmpty name="ntp091Form" property="ntp091RightUserList">
            <html:optionsCollection name="ntp091Form" property="ntp091RightUserList" label="value" label="label" />
          </logic:notEmpty>
          <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
        </html:select>
      </td>
      <td align="center">
      <a href="javascript:buttonPush('addUser');"><img src="../common/images/arrow_btn_add.gif" alt="<gsmsg:write key="cmn.add" />" border="0"></a><br><br>
      <a href="javascript:buttonPush('removeUser');"><img src="../common/images/arrow_btn_delete.gif" alt="<gsmsg:write key="cmn.delete" />" border="0"></a>
      </td>
      <td>
        <html:select name="ntp091Form" property="ntp091SelectLeftUser" size="6" multiple="true" styleClass="select01">
          <logic:notEmpty name="ntp091Form" property="ntp091LeftUserList">
            <html:optionsCollection name="ntp091Form" property="ntp091LeftUserList" label="value" label="label" />
          </logic:notEmpty>
          <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
        </html:select>
      </td>
      </tr>
      </tbody>
      </table>
    </td>
    </tr>
--%>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('ntp091kakunin');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('ntp091back');">
    </td>
    </tr>
    </table>


  </td>
  </tr>
  </table>

</td>
</tr>
</table>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</html:form>
</body>
</html:html>