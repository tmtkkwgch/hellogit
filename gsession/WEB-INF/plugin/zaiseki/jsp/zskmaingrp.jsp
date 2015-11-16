<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.zaiseki.management" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script language="JavaScript" src="../zaiseki/js/zskmaingrp.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/userpopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmnPic.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>


<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>

<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../zaiseki/css/zaiseki.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/css/schedule.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

</head>

<body class="body_03" onload="initPicture2('userImage', 130, 150);">
<div id="zaiseki_zskmain_div">
<html:form action="/zaiseki/zskmaingrp">
<div id="tooltips_zsk">
<input type="hidden" name="CMD" value="">
<html:hidden property="zskSelectUsrSid" />
<html:hidden property="zskSelectSchSid" />

<logic:equal name="zskmaingrpForm" property="zskGrpDspFlg" value="<%= String.valueOf(jp.groupsession.v2.zsk.GSConstZaiseki.MAINGRP_DSP) %>">

<table width="100%" class="tl0" cellspacing="0" cellpadding="0">
<tr style="border: solid 1px #333333;">
<td class="double_header_7D91BD_left">
  <img src="../zaiseki/images/menu_icon_single.gif" class="img_bottom img_border img_menu_icon_single" alt="<gsmsg:write key="cmn.zaiseki.management" />"><a href="<bean:write name="zskmaingrpForm" property="zskTopUrl" />"><gsmsg:write key="zsk.zskmaingrep.06" /></a>
</td>
<td align="right" class="double_header_7D91BD_right">
  <input type="button" onClick="zskPriConf('mainDspSetting');" style="border:0px;color:#40a06b;font-size:10px;font-weight:bold;width:60px;height:17px;" class="btn_small_setting" value="<gsmsg:write key="cmn.setting" />">
</td>
</tr>

<!-- グループコンボ -->
<logic:notEmpty name="zskmaingrpForm" property="zaiGrpLabelList">
<tr>

<input type="button" class="display_hide" name="fakeButton" onClick="return updateZskGrp('changeGrpCmb');">
<td align="left" class="td_type3" colspan="2">
<span class="text_base"><gsmsg:write key="cmn.show.group" /></span>
<html:select name="zskmaingrpForm" property="zaiGrpSid" onchange="updateZskGrp('changeGrpCmb');">
  <logic:iterate id="gpBean" name="zskmaingrpForm" property="zaiGrpLabelList" scope="request">
    <bean:define id="gpValue" name="gpBean" property="value" type="java.lang.String" />
    <logic:equal name="gpBean" property="styleClass" value="0">
      <html:option value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
    </logic:equal>
    <logic:notEqual name="gpBean" property="styleClass" value="0">
      <html:option styleClass="select03" value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
    </logic:notEqual>
  </logic:iterate>
</html:select>
<input type="button" onclick="openGroupWindow(this.form.zaiGrpSid, 'zaiGrpSid', '1', 'changeGrpCmb', '1', 'fakeButton')" class="group_btn" value="&nbsp;&nbsp;" id="zskmaingrpGroupBtn">
</td>
</tr>
</logic:notEmpty>


<logic:notEmpty name="zskmaingrpForm" property="userList" scope="request">
<logic:iterate id="userMdl" name="zskmaingrpForm" property="userList" scope="request" indexId="idx">
  <!-- 在席カラー設定 -->
  <!-- 在席 -->
  <logic:equal name="userMdl" property="uioStatus" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.UIOSTS_IN) %>">
  <bean:define id="zskColor" value="td_type1" />
  </logic:equal>
  <!-- 不在 -->
  <logic:equal name="userMdl" property="uioStatus" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.UIOSTS_LEAVE) %>">
  <bean:define id="zskColor" value="td_type_gaisyutu" />
  </logic:equal>
  <!-- その他 -->
  <logic:equal name="userMdl" property="uioStatus" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.UIOSTS_ETC) %>">
  <bean:define id="zskColor" value="td_type_kekkin" />
  </logic:equal>

<tr>
<td class="<bean:write name="zskColor" />" colspan="2">

  <!-- ユーザ情報 -->
  <table class="tl0" width="100%">
  <tr>
  <td rowspan="2" width="0%">
  <!-- ユーザ画像 -->

  <logic:equal name="userMdl" property="usiPictKf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>">
    <a href="javascript:void(0);" onClick="return openUserInfoWindow(<bean:write name="userMdl" property="usrSid" />);"><div align="center" class="photo_hikokai2"><span style="color:#fc2929;"><gsmsg:write key="cmn.private" /></span></div></a>
  </logic:equal>

  <logic:equal name="userMdl" property="usiPictKf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_OPEN) %>">
    <logic:equal name="userMdl" property="binSid" value="<%= String.valueOf(jp.groupsession.v2.zsk.GSConstZaiseki.USR_IMAGE_NOT_DSP) %>">
      <a href="javascript:void(0);" onClick="return openUserInfoWindow(<bean:write name="userMdl" property="usrSid" />);"><img src="../user/images/photo.gif" name="userImage" width="35" alt="<gsmsg:write key="cmn.photo" />" border="1"></a>
    </logic:equal>
    <logic:notEqual name="userMdl" property="binSid" value="<%= String.valueOf(jp.groupsession.v2.zsk.GSConstZaiseki.USR_IMAGE_NOT_DSP) %>">
      <a href="javascript:void(0);" onClick="return openUserInfoWindow(<bean:write name="userMdl" property="usrSid" />);"><img src="../common/cmn100.do?CMD=getImageFile&cmn100binSid=<bean:write name="userMdl" property="binSid" />" name="userImage" width="35" alt="<gsmsg:write key="cmn.photo" />" border="1"></a>
    </logic:notEqual>
  </logic:equal>

  </td>
  <td width="100%" align="left" class="left_space" nowrap colspan="2">
  <!-- ユーザ名 -->
  <span class="text_link2">
  <a href="javascript:void(0);" onClick="return openUserInfoWindow(<bean:write name="userMdl" property="usrSid" />)">
  <bean:write name="userMdl" property="usiSei" />&nbsp;<bean:write name="userMdl" property="usiMei" /><br>
  </a>
  </span>
  </td>
  </tr>

  <tr>
  <td align="left" class="img_middle">
  <span class="img_middle">
  <!-- 在席状況表示 -->
  <logic:equal name="userMdl" property="uioStatus" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.UIOSTS_IN) %>">
    <span class="text_zsk_text2"><gsmsg:write key="cmn.zaiseki2" />:</span>
  </logic:equal>
  <logic:equal name="userMdl" property="uioStatus" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.UIOSTS_LEAVE) %>">
    <span class="text_zsk_text2"><gsmsg:write key="cmn.absence2" />:</span>
  </logic:equal>
  <logic:equal name="userMdl" property="uioStatus" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.UIOSTS_ETC) %>">
    <span class="text_zsk_text2"><gsmsg:write key="cmn.other" />:</span>
  </logic:equal>
  <!-- 在席コメント -->
  <span class="text_base"><bean:write name="userMdl" property="uioComment" /></span>
  </span>
  </td>

  <td align="right">
  <!-- 在席状況変更ボタン -->
  <logic:equal name="zskmaingrpForm" property="zaisekiUseOk" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.PLUGIN_USE) %>">
    <!-- 在席 -->
    <logic:equal name="userMdl" property="uioStatus" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.UIOSTS_IN) %>">
      <a href="javascript:void(0);" onClick="return openUserIoWindow(<bean:write name="userMdl" property="usrSid"/>, <%= String.valueOf(jp.groupsession.v2.cmn.GSConst.UIOSTS_IN) %>);"><img src="../zaiseki/images/btn_main_zaiseki.gif" alt="<gsmsg:write key="cmn.zaiseki.management" />" border="0"></a>
    </logic:equal>
    <!-- 不在 -->
    <logic:equal name="userMdl" property="uioStatus" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.UIOSTS_LEAVE) %>">
      <a href="javascript:void(0);" onClick="return openUserIoWindow(<bean:write name="userMdl" property="usrSid"/>, <%= String.valueOf(jp.groupsession.v2.cmn.GSConst.UIOSTS_LEAVE) %>);"><img src="../zaiseki/images/btn_main_zaiseki.gif" alt="<gsmsg:write key="cmn.zaiseki.management" />" border="0"></a>
    </logic:equal>
      <!-- その他 -->
    <logic:equal name="userMdl" property="uioStatus" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.UIOSTS_ETC) %>">
      <a href="javascript:void(0);" onClick="return openUserIoWindow(<bean:write name="userMdl" property="usrSid"/>, <%= String.valueOf(jp.groupsession.v2.cmn.GSConst.UIOSTS_ETC) %>);"><img src="../zaiseki/images/btn_main_zaiseki.gif" alt="<gsmsg:write key="cmn.zaiseki.management" />" border="0"></a>
    </logic:equal>
  </logic:equal>


  <!-- ショートメールボタン -->
  <logic:equal name="zskmaingrpForm" property="smailUseOk" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.PLUGIN_USE) %>">
    <logic:equal name="userMdl" property="smlAble" value="1">
    <a href="#" onClick="moveCreateMsg('msg', <bean:write name="userMdl" property="usrSid" />);"><img src="../zaiseki/images/btn_sml_send.gif" alt="<gsmsg:write key="cmn.shortmail" />" border="0"></a>
    </logic:equal>
  </logic:equal>

  <!-- スケジュール表示切替ボタン -->
  <logic:equal name="zskmaingrpForm" property="schUseOk" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.PLUGIN_USE) %>">

    <logic:equal name="userMdl" property="schAccessFlg" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SCH_ACCESS_YES) %>">
      <logic:equal name="zskmaingrpForm" property="zaiSchViewDf" value="<%= String.valueOf(jp.groupsession.v2.zsk.GSConstZaiseki.MAIN_SCH_DSP) %>">
        <a href="javaScript:void(0);" onClick="dispDescriptionZsk('ds<bean:write name="idx" />');"><img id="resesSwitchds<bean:write name='idx' />" src="../zaiseki/images/btn_sch_not_dsp.gif" alt="<gsmsg:write key="cmn.hide" />" border="0"></a>
      </logic:equal>

      <logic:equal name="zskmaingrpForm" property="zaiSchViewDf" value="<%= String.valueOf(jp.groupsession.v2.zsk.GSConstZaiseki.MAIN_SCH_NOT_DSP) %>">
        <a href="javaScript:void(0);" onClick="dispDescriptionZsk('ds<bean:write name="idx" />');"><img id="resesSwitchds<bean:write name='idx' />" src="../zaiseki/images/btn_sch_dsp.gif" alt="<gsmsg:write key="zsk.zskmaingrep.07" />" border="0"></a>
      </logic:equal>
    </logic:equal>

  </logic:equal>

  </td>
  </tr>

  <logic:equal name="zskmaingrpForm" property="schUseOk" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.PLUGIN_USE) %>">
  <logic:equal name="userMdl" property="schAccessFlg" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SCH_ACCESS_YES) %>">

  <tr>
  <td width="100%" colspan="3">
  <!-- スケジュール欄 -->
  <logic:equal name="zskmaingrpForm" property="zaiSchViewDf" value="<%= String.valueOf(jp.groupsession.v2.zsk.GSConstZaiseki.MAIN_SCH_DSP) %>">
    <div id="ds<bean:write name='idx' />" class="zsk_description_text_dsp">
  </logic:equal>

  <logic:equal name="zskmaingrpForm" property="zaiSchViewDf" value="<%= String.valueOf(jp.groupsession.v2.zsk.GSConstZaiseki.MAIN_SCH_NOT_DSP) %>">
    <div id="ds<bean:write name='idx' />" class="zsk_description_text_notdsp">
  </logic:equal>

    <table width="100%">
    <tr>
    <td class="zsk_line_style">

    <!-- スケジュール表示 -->
    <logic:notEmpty name="zskmaingrpForm" property="scheduleList" scope="request">
    <logic:iterate id="scdMdl" name="zskmaingrpForm" property="scheduleList" scope="request" indexId="idx2">
    <bean:define id="dspUser" name="userMdl" property="usrSid" />
    <bean:define id="selUser" name="scdMdl" property="scdUsrSid" />

    <bean:define id="u_usrsid" name="scdMdl" property="scdUsrSid" type="java.lang.Integer" />
    <bean:define id="u_schsid" name="scdMdl" property="scdSid" type="java.lang.Integer" />
    <%
      if (dspUser.equals(selUser)) {
    %>

    <logic:empty name="scdMdl" property="scdValue">
      <logic:notEqual name="zskmaingrpForm" property="dspUserSid" value="<%= selUser.toString() %>">
      <logic:notEqual name="scdMdl" property="scdPublic" value="2">
        <a href="#" title="" onClick="zskeditSchedule('schw_edit', <bean:write name="scdMdl" property="scdSid" />, <bean:write name="scdMdl" property="scdUsrSid" />);">
        <span class="tooltips"><bean:write name="scdMdl" property="scdTitle" /></span>
      </logic:notEqual>
      </logic:notEqual>
      <logic:equal name="zskmaingrpForm" property="dspUserSid" value="<%= selUser.toString() %>">
        <a href="#" title="" onClick="zskeditSchedule('schw_edit', <bean:write name="scdMdl" property="scdSid" />, <bean:write name="scdMdl" property="scdUsrSid" />);">
        <span class="tooltips"><bean:write name="scdMdl" property="scdTitle" /></span>
      </logic:equal>
    </logic:empty>

    <logic:notEmpty name="scdMdl" property="scdValue">
    <bean:define id="scnaiyou" name="scdMdl" property="scdValue" />
    <%
      String tmpText = (String)pageContext.getAttribute("scnaiyou",PageContext.PAGE_SCOPE);
      String tmpText2 = jp.co.sjts.util.StringUtilHtml.transToHTml(tmpText);
    %>

      <logic:notEqual name="zskmaingrpForm" property="dspUserSid" value="<%= selUser.toString() %>">
      <logic:notEqual name="scdMdl" property="scdPublic" value="2">
        <a href="#" onClick="zskeditSchedule('schw_edit', <bean:write name="scdMdl" property="scdSid" />, <bean:write name="scdMdl" property="scdUsrSid" />);" title="">
        <span class="tooltips"><gsmsg:write key="cmn.content" />:<br><%= tmpText2 %></span>
      </logic:notEqual>
      </logic:notEqual>
      <logic:equal name="zskmaingrpForm" property="dspUserSid" value="<%= selUser.toString() %>">
        <a href="#" onClick="zskeditSchedule('schw_edit', <bean:write name="scdMdl" property="scdSid" />, <bean:write name="scdMdl" property="scdUsrSid" />);" title="">
        <span class="tooltips"><gsmsg:write key="cmn.content" />:<br><%= tmpText2 %></span>
      </logic:equal>
    </logic:notEmpty>

    <!-- スケジュール　タイトルカラー設定 -->
    <logic:equal name="scdMdl" property="scdBgcolor" value="1">
      <span class="sc_link_1">
    </logic:equal>
    <logic:equal name="scdMdl" property="scdBgcolor" value="2">
      <span class="sc_link_2">
    </logic:equal>
    <logic:equal name="scdMdl" property="scdBgcolor" value="3">
      <span class="sc_link_3">
    </logic:equal>
    <logic:equal name="scdMdl" property="scdBgcolor" value="4">
      <span class="sc_link_4">
    </logic:equal>
    <logic:equal name="scdMdl" property="scdBgcolor" value="5">
      <span class="sc_link_5">
    </logic:equal>
    <logic:equal name="scdMdl" property="scdBgcolor" value="6">
      <span class="sc_link_6">
    </logic:equal>
    <logic:equal name="scdMdl" property="scdBgcolor" value="7">
      <span class="sc_link_7">
    </logic:equal>
    <logic:equal name="scdMdl" property="scdBgcolor" value="8">
      <span class="sc_link_8">
    </logic:equal>
    <logic:equal name="scdMdl" property="scdBgcolor" value="9">
      <span class="sc_link_9">
    </logic:equal>
    <logic:equal name="scdMdl" property="scdBgcolor" value="10">
      <span class="sc_link_10">
    </logic:equal>
    <!-- スケジュール　期間表示 -->
    <bean:write name="scdMdl" property="scdFrToDateDsp" />&nbsp;&nbsp;
    <!-- スケジュール　タイトル表示 -->
    <bean:write name="scdMdl" property="scdTitle" /><br>
    <% } %>
    </span>
    </a>

    </logic:iterate><!-- スケジュール繰返し終了 -->
    </logic:notEmpty>

    <logic:equal name="userMdl" property="schRegistFlg" value="true">
    <!-- スケジュール追加ボタン -->
    <a href="#" onClick="return zskaddSchedule('schw_add', <bean:write name="userMdl" property="usrSid" />);"><img src="../schedule/images/scadd.gif" alt="<gsmsg:write key="cmn.add" />" width="16" height="16" border="0" align="right" /></a>
    </logic:equal>
    </td>
    </tr>
    </table><!-- スケジュール表示テーブル -->

  </div>
  </td>
  </tr>
  </logic:equal>
  </logic:equal>

  </table><!--ユーザテーブル終了 -->
</td>
</tr>
</logic:iterate>
</logic:notEmpty>

</table>
</logic:equal>
</div>
</html:form>
</div>
</body>
</html:html>