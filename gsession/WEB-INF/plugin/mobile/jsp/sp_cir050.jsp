<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jqText.tld" prefix="jquery" %>
<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>

<%@page import="jp.groupsession.v2.cir.GSConstCircular"%>
<%@page import="jp.groupsession.v2.cmn.GSConst"%>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cir.5" /><gsmsg:write key="cmn.create.new" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>

<link rel="stylesheet" href="../mobile/sp/css/jquery.ui.datepicker.css?<%= GSConst.VERSION_PARAM %>" />
<link rel="stylesheet" href="../mobile/sp/css/jquery.ui.datepicker.mobile.css?<%= GSConst.VERSION_PARAM %>" />
<script src="../mobile/sp/js/jQuery.ui.datepicker.js?<%= GSConst.VERSION_PARAM %>"　type="text/javascript"　charset="utf-8"></script>
<script src="../mobile/sp/js/jquery.ui.datepicker.mobile.js?<%= GSConst.VERSION_PARAM %>"　type="text/javascript"　charset="utf-8"></script>
<logic:equal name="mbhCir050Form" property="mobileLang" value="0">
<script src="../mobile/sp/js/jquery.ui.datepicker-ja.js?<%= GSConst.VERSION_PARAM %>"　type="text/javascript"　charset="utf-8"></script>
</logic:equal>
<script language="JavaScript" src="../mobile/sp/js/sp_file_upload.js?453<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript"　charset="utf-8">
$(document).ready(function(){
  $("#date").datepicker();

  <logic:equal name="mbhCir050Form" property="cir050memoKbn" value="0">
  chMemoYes('b');
  </logic:equal>

  <logic:equal name="mbhCir050Form" property="cir050memoKbn" value="1">
  chMemoNo('b');
  </logic:equal>

});
</script>

<% pluginName = "cir"; %>
<% thisForm = "mbhCir050Form"; %>
<bean:define id="sptm" name="mbhCir050Form" property="spTheme"/>
</head>

<!--　BODY -->
<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form method="post" action="/mobile/sp_cir050">

<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<a href="#" onClick="buttonPush('back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
<img src="../mobile/sp/imgages/cir_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="cir.5" /><br><gsmsg:write key="cmn.create.new" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<input type="hidden" name="delId" value="">
<input type="hidden" name="CMD" value="">
<html:hidden property="mobileType" value="1"/>
<html:hidden property="cirViewAccount" />
<html:hidden property="cirViewAccountName" />
<html:hidden property="cir020cmdMode" />
<html:hidden property="cir020orderKey" />
<html:hidden property="cir020sortKey" />
<html:hidden property="cir020pageNum1" />
<html:hidden property="cir020pageNum2" />
<html:hidden property="cir050pluginId" />
<html:hidden property="cir050memoPeriod" />
<html:hidden property="cir050memoPeriodYear" />
<html:hidden property="cir050memoPeriodMonth" />
<html:hidden property="cir050memoPeriodDay" />
<html:hidden property="cir050InitFlg" />
<html:hidden property="cir060dspId" />
<html:hidden property="cir020selectInfSid" />

<logic:notEmpty name="mbhCir050Form" property="cir050userSid" scope="request">
<logic:iterate id="users" name="mbhCir050Form" property="cir050userSid" indexId="idx" scope="request">
  <bean:define id="userSid" name="users" type="java.lang.String" />
  <html:hidden property="cir050userSid" value="<%= userSid %>" />
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhCir050Form" property="cir020delInfSid" scope="request">
<logic:iterate id="item" name="mbhCir050Form" property="cir020delInfSid" scope="request">
  <input type="hidden" name="cir020delInfSid" value="<bean:write name="item"/>">
</logic:iterate>
</logic:notEmpty>

<logic:messagesPresent message="false">
  <html:errors/><br>
</logic:messagesPresent>

<div class="font_small">■<gsmsg:write key="cir.2" /></div>
<span class="text_base"><bean:write name="mbhCir050Form" property="cirViewAccountName" /></span><br><br>

<div class="font_small">■<gsmsg:write key="cir.20" /></div>
<div align="center"><input type="submit" value="<gsmsg:write key="sml.sml020.05" />" class="btn_user_n1" name="seluser" data-inline="true" data-role="button" data-icon="arrow-r" data-iconpos="right"></div>

<logic:notEmpty name="mbhCir050Form" property="cir050MemberList" scope="request">
  <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
      <logic:iterate id="memMdl" name="mbhCir050Form" property="cir050MemberList" scope="request">

        <logic:greaterThan name="memMdl" property="cacSid" value="0">


          <logic:greaterThan name="memMdl" property="usrSid" value="0">
            <li data-icon="delete">
              <a href="#" onClick="delPush('<bean:write name="memMdl" property="usrSid" />');" ><bean:write name="memMdl" property="cacName" /></a>
            </li>
          </logic:greaterThan>

          <logic:lessThan name="memMdl" property="usrSid" value="1">
            <li data-icon="delete">
              <a href="#" onClick="delPush('cac<bean:write name="memMdl" property="cacSid" />');" ><bean:write name="memMdl" property="cacName" /></a>
            </li>
          </logic:lessThan>

        </logic:greaterThan>



      </logic:iterate>
  </ul>
</logic:notEmpty>



<logic:empty name="mbhCir050Form" property="cir050MemberList" scope="request">

</logic:empty>

<br>

<!-- タイトル -->
<div class="font_small">■<gsmsg:write key="cmn.title" /></div>
<html:text size="27" property="cir050title" styleClass="text_base"  maxlength="70"/>

<br>
<br>

<!-- 内容 -->
<div class="font_small">■<gsmsg:write key="cmn.content" /></div>

<textarea class="text_base" name="cir050value" id="inputstr"><bean:write name="mbhCir050Form" property="cir050value" /></textarea>
<br>
<br>


<% String grpColor2 = radioFont; %>
<% String nameColor2 = radioFont; %>
<logic:equal name="mbhCir050Form" property="cir050memoKbn" value="0">
 <% grpColor2 = radioFontActive; %>
</logic:equal>
<logic:equal name="mbhCir050Form" property="cir050memoKbn" value="1">
 <% nameColor2 = radioFontActive; %>

</logic:equal>
<!-- メモ欄修正区分 -->
<div class="font_small">■<gsmsg:write key="cir.cir040.3" /></div>
<fieldset data-role="controlgroup" data-type="horizontal" align="center">
  <html:radio value="0" property="cir050memoKbn" styleId="memoNg" onchange="chMemoYes('<%= sptm %>');" /><label for="memoNg" id="memoNo" class="<%= radioClass %>" style="color:<%= grpColor2 %>"><gsmsg:write key="cmn.not" /></label>
  <html:radio value="1" property="cir050memoKbn" styleId="memoOk" onchange="chMemoNo('<%= sptm %>');" /><label for="memoOk"  id="memoYes" class="<%= radioClass %>" style="color:<%= nameColor2 %>">&nbsp;&nbsp;<gsmsg:write key="cmn.accepted" />&nbsp;&nbsp;</label>
</fieldset>

<div id="memoAddArea" align="center" class="font_xsmall" style="display:none;">
  <!-- メモ欄修正期限設定 -->
  <span class="text_base"><gsmsg:write key="cir.54" /></span>&nbsp;&nbsp;&nbsp;&nbsp;

  <!-- 期間指定の場合 -->
  <%--
  <a href="javascript:void(0);" onClick="return clickPeriod('clickPeriod', '1');"><span class="text_link2"><gsmsg:write key="cmn.today" /></span></a>&nbsp;&nbsp;
  <a href="javascript:void(0);" onClick="return clickPeriod('clickPeriod', '0');"><span class="text_link2">1<gsmsg:write key="cmn.weeks" /></span></a>&nbsp;&nbsp;
  <a href="javascript:void(0);" onClick="return clickPeriod('clickPeriod', '2');"><span class="text_link2">2<gsmsg:write key="cmn.weeks" /></span></a>&nbsp;&nbsp;
  <a href="javascript:void(0);" onClick="return clickPeriod('clickPeriod', '3');"><span class="text_link2">1<gsmsg:write key="cmn.months" /></span></a>&nbsp;&nbsp;
  <br>
  --%>

  <!-- 日付指定の場合 -->
  <span class="text_r1" style="color:red;"><gsmsg:write key="cir.cir040.4" /></span><br>
  <jquery:jqtext id="date" name="mbhCir050Form" property="cir050limitDay" readonly="true" />
</div>

<br>

  <!-- ファイル添付 -->
<div class="font_small">■<gsmsg:write key="cmn.attach.file"/></div>
<span id="tmp_file_area">

  <logic:notEmpty name="mbhCir050Form" property="cir050FileLabelList">
    <logic:iterate id="file" name="mbhCir050Form" property="cir050FileLabelList" indexId="idx" scope="request">
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

<!-- 公開／非公開   -->
 <% String grpColor = radioFont; %>
 <% String nameColor = radioFont; %>
 <logic:equal name="mbhCir050Form" property="cir050show" value="0">
   <% grpColor = radioFontActive; %>
 </logic:equal>
 <logic:equal name="mbhCir050Form" property="cir050show" value="1">
   <% nameColor = radioFontActive; %>
 </logic:equal>

<div class="font_small">■<gsmsg:write key="cir.cir030.3" /></div>
<fieldset data-role="controlgroup" data-type="horizontal" align="center">
  <html:radio name="mbhCir050Form" property="cir050show" styleId="kokai"   value="0" onchange="chRsvYes('<%= sptm %>');" /><label for="kokai" id="rsvNo" class="<%= radioClass %>" style="color:<%= grpColor %>"><span class="font_middle"><gsmsg:write key="cmn.public" /></span>&nbsp;&nbsp;</label>
  <html:radio name="mbhCir050Form" property="cir050show" styleId="hikokai" value="1" onchange="chRsvNo('<%= sptm %>');" /><label for="hikokai" id="rsvYes" class="<%= radioClass %>" style="color:<%= nameColor %>"><span class="font_middle"><gsmsg:write key="cmn.private" /></span></label>
</fieldset>

<br>

<div data-role="controlgroup" data-type="horizontal" align="center">
  <input type="submit" name="okbtn" value="ＯＫ" class="btn_ok1" data-icon="check">
  <input type="submit" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" name="cir050Back" data-inline="true" data-role="button" data-icon="back" data-iconpos="right">
</div>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>


<!-- 添付ファイル -->
<input type="hidden" id="tmp_file_obj_name" value="cir050file" />
<input type="file" id="file_use_check" style="visibility:hidden;"/>
<div id="tmp_pop" style="display:none;">

  <div class="tmppopupmenu">

    <div style="text-align:right;">
      <a href="#" onclick="tmpPopupMenu();" data-role="button" data-icon="delete" data-iconpos="notext">Close</a>
    </div>

    <div id="error_msg_area" align="center"></div>

    <div align="center">
      <div id="fileUpArea" class="fieldcontain">
        <input type="file" id="cir050file" name="cir050file" data-clear-btn="true"/>
      </div>
    </div>

    <div style="padding-top:20px;"></div>

    <div id="progress_bar_wrapper" align="center">
      <div id="progress_bar"></div>
    </div>

    <div id="progress_text_wrapper">
      <span id="progress_text"></span>
    </div>

    <div align="center">
      <a href="#" id="uploadBtn" onClick="performAjaxSubmit();" data-role="button" data-icon="plus" data-inline="true"/>添付する</a>
    </div>

  </div>

</div>

</html:form>
</div><!-- /page -->

</body>
</html:html>