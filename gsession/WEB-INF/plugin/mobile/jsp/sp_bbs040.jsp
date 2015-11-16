<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-jqText.tld" prefix="jquery" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="cmn.bulletin" /><gsmsg:write key="bbs.bbs060.1" /></title>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>

<link rel="stylesheet" href="../mobile/sp/css/jquery.ui.datepicker.css?<%= GSConst.VERSION_PARAM %>" />
<link rel="stylesheet" href="../mobile/sp/css/jquery.ui.datepicker.mobile.css?<%= GSConst.VERSION_PARAM %>" />
<script src="../mobile/sp/js/jQuery.ui.datepicker.js?<%= GSConst.VERSION_PARAM %>"　type="text/javascript"　charset="utf-8"></script>
<script src="../mobile/sp/js/jquery.ui.datepicker.mobile.js?<%= GSConst.VERSION_PARAM %>"　type="text/javascript"　charset="utf-8"></script>
<logic:equal name="mbhBbs040Form" property="mobileLang" value="0">
<script src="../mobile/sp/js/jquery.ui.datepicker-ja.js?<%= GSConst.VERSION_PARAM %>"　type="text/javascript"　charset="utf-8"></script>
</logic:equal>
<script language="JavaScript" src="../mobile/sp/js/sp_file_upload.js?453<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript"　charset="utf-8">
$(document).ready(function(){
  $("#date").datepicker();
});
</script>
<% pluginName = "bbs"; %>
<% thisForm = "mbhBbs040Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_bbs040">
<html:hidden property="mobileType" value="1"/>
<input type="hidden" name="CMD">
<input type="hidden" name="mobileType" value="1">
<html:hidden name="mbhBbs040Form" property="bbs010forumSid" />
<html:hidden name="mbhBbs040Form" property="bbs040limit" />
<html:hidden name="mbhBbs040Form" property="bbs040limitYear" />
<html:hidden name="mbhBbs040Form" property="bbs040limitMonth" />
<html:hidden name="mbhBbs040Form" property="bbs040limitDay" />
<html:hidden property="bbs040InitFlg" />

<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<a href="#" onClick="buttonPush('bbs040back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
<img src="../mobile/sp/imgages/bbs_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="cmn.bulletin" /><br><gsmsg:write key="bbs.bbs060.1" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<logic:messagesPresent message="false">
<span style="color:red;"><html:errors/></span><br>
</logic:messagesPresent>

<div class="title_1" align="center">
  <b>
    <div class="font_small">
      <bean:write name="mbhBbs040Form" property="bbs040forumName" />
    </div>
  </b>
</div>
<br>
<div class="font_small">■<gsmsg:write key="cmn.contributor" /></div>
<html:select property="bbs040contributor">
  <html:optionsCollection name="mbhBbs040Form" property="bbs040contributorList" value="value" label="label" />
</html:select>
<br>
<br>
<div class="font_small">■<gsmsg:write key="cmn.title" /></div>
<br>
<html:text name="mbhBbs040Form" property="bbs040title" size="27" maxlength="70" />
<br>
<div class="font_small">■<gsmsg:write key="cmn.content" /></div>
<br>
<textarea name="bbs040value" cols="16" rows="3"><bean:write name="mbhBbs040Form" property="bbs040value" /></textarea>
<br>

  <!-- ファイル添付 -->
<div class="font_small">■<gsmsg:write key="cmn.attach.file"/></div>
<span id="tmp_file_area">

  <logic:notEmpty name="mbhBbs040Form" property="bbs040FileLabelList">
    <logic:iterate id="file" name="mbhBbs040Form" property="bbs040FileLabelList" indexId="idx" scope="request">
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
<div class="font_small">■<gsmsg:write key="bbs.12" /></div>
<br>
<jquery:jqtext id="date" name="mbhBbs040Form" property="bbs040limitDate" readonly="true" onchange="changeDate('changeDate');"/>
<gsmsg:define id="bbsNoLimit" msgkey="main.man200.9" />
<logic:notEqual name="mbhBbs040Form" property="bbs040limitDate" value="<%= bbsNoLimit %>">
  <div align="center">
    <input name="bbs040noLimit" value="<gsmsg:write key="mobile.16" />" type="submit" data-inline="true" data-icon="refresh" />
  </div>
</logic:notEqual>

<br>
<br>

<div class="font_small">
<div data-role="controlgroup" data-type="horizontal" align="center">
  <input name="bbs040ok" value="OK" type="submit" data-inline="true" data-icon="check" />
  <input name="bbs040back" value="<gsmsg:write key="cmn.back" />" type="submit" data-inline="true" data-icon="back" data-iconpos="right" />
</div>
</div>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

<!-- 添付ファイル -->
<input type="hidden" id="tmp_file_obj_name" value="bbs040file" />
<input type="file" id="file_use_check" style="visibility:hidden;"/>
<div id="tmp_pop" style="display:none;">

  <div class="tmppopupmenu">

    <div style="text-align:right;">
      <a href="#" onclick="tmpPopupMenu();" data-role="button" data-icon="delete" data-iconpos="notext">Close</a>
    </div>

    <div id="error_msg_area" align="center"></div>

    <div align="center">
      <div id="fileUpArea" class="fieldcontain">
        <input type="file" id="bbs040file" name="bbs040file" data-clear-btn="true"/>
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