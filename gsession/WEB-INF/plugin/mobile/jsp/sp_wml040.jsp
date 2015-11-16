<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="wml.wml010.25" />(<gsmsg:write key="cmn.create.new" />)</title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<script language="JavaScript" src="../mobile/sp/js/sp_file_upload.js?453<%= GSConst.VERSION_PARAM %>"></script>
<% pluginName = "wml"; %>
<% thisForm = "mbhWml040Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_wml040">
<input type="hidden" name="CMD" value="">
<html:hidden property="mobileType" value="1"/>
<html:hidden property="wmlAccount" />
<html:hidden property="wmlDirectory" />
<html:hidden property="wmlLabel" />
<html:hidden property="wmlMailNum" />
<html:hidden property="wmlSendMode" />
<html:hidden property="wml020SearchFlg" />
<html:hidden property="wml020selectPage" />
<html:hidden property="wml020MaxPage" />
<html:hidden property="wml020svSearchFrom" />
<html:hidden property="wml020svSearchTo" />
<html:hidden property="wml020svSearchDateType" />
<html:hidden property="wml020svSearchDateYearFr" />
<html:hidden property="wml020svSearchDateMonthFr" />
<html:hidden property="wml020svSearchDateDayFr" />
<html:hidden property="wml020svSearchDateYearTo" />
<html:hidden property="wml020svSearchDateMonthTo" />
<html:hidden property="wml020svSearchDateDayTo" />
<html:hidden property="wml020svKeyword" />
<html:hidden property="wml020svSearchKeyword" />
<html:hidden property="wml020svSearchKeywordKbn" />
<html:hidden property="wml020svSearchReadKbn" />
<html:hidden property="wml020svSearchTempFile" />
<html:hidden property="wml020DetaileSrhFlg" />
<html:hidden property="wml020FrDate" />
<html:hidden property="wml020ToDate" />
<html:hidden property="wml020FrDate" />
<html:hidden property="wml020ToDate" />
<html:hidden property="wml040initFlg" />
<html:hidden property="wml040svAccount" />


<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<a href="#" onClick="buttonPush('wml040back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
<img src="../mobile/sp/imgages/wml_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="wml.wml010.25" /><br><gsmsg:write key="cmn.create.new" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<div class="font_xsmall">
●<gsmsg:write key="wml.102" />
</div>
<div class="font_small" align="center">
<html:select property="wml040Account" onchange="changeCombo();">
  <html:optionsCollection name="mbhWml040Form" property="accountCombo" value="value" label="label" />
</html:select>
</div>
<hr>

<logic:messagesPresent message="false">
<span style="color: red"><html:errors/></span>
</logic:messagesPresent>

<div class="font_xsmall">
■<gsmsg:write key="cmn.from" />
</div>

<div align="center" class="font_xxsmall">
<div data-role="controlgroup" data-type="horizontal" align="center">
<logic:equal name="mbhWml040Form" property="addressUseOk" value="0">
  <input name="wml040selectAdTo" value="<gsmsg:write key="addressbook" />" type="submit" data-inline="true" data-icon="arrow-l"/>
</logic:equal>
<logic:equal name="mbhWml040Form" property="userUseOk" value="0">
  <input name="wml040selectTo" value="<gsmsg:write key="cmn.shain.info" />" type="submit" data-inline="true" data-icon="arrow-r" data-iconpos="right"/>
</logic:equal>
</div>
<html:text name="mbhWml040Form" property="wml040To" size="27" />
</div>

<br>

<div class="font_xsmall">
■CC
</div>
<div align="center" class="font_xxsmall">
<div data-role="controlgroup" data-type="horizontal" align="center">
<logic:equal name="mbhWml040Form" property="addressUseOk" value="0">
  <input name="wml040selectAdCc" value="<gsmsg:write key="addressbook" />" type="submit" data-inline="true" data-icon="arrow-l"/>
</logic:equal>
<logic:equal name="mbhWml040Form" property="userUseOk" value="0">
  <input name="wml040selectCc" value="<gsmsg:write key="cmn.shain.info" />" type="submit" data-inline="true" data-icon="arrow-r" data-iconpos="right"/>
</logic:equal>
</div>
<html:text name="mbhWml040Form" property="wml040Cc" size="27" />
</div>

<br>

<div class="font_xxsmall">
■BCC
</div>
<div align="center" class="font_xxsmall">
<div data-role="controlgroup" data-type="horizontal" align="center">
<logic:equal name="mbhWml040Form" property="addressUseOk" value="0">
  <input name="wml040selectAdBcc" value="<gsmsg:write key="addressbook" />" type="submit" data-inline="true" data-icon="arrow-l"/>
</logic:equal>
<logic:equal name="mbhWml040Form" property="userUseOk" value="0">
  <input name="wml040selectBcc" value="<gsmsg:write key="cmn.shain.info" />" type="submit" data-inline="true" data-icon="arrow-r" data-iconpos="right"/>
</logic:equal>
</div>
<html:text name="mbhWml040Form" property="wml040Bcc" size="27" />
</div>

<br>

<div class="font_xsmall">
■<gsmsg:write key="cmn.subject" />
</div>
<div align="center">
  <html:text name="mbhWml040Form" property="wml040Subject" size="27" />
</div>
<br>

<!-- 添付ファイル -->
<div class="font_xsmall">
■<gsmsg:write key="cmn.attach.file"/>
</div>
   <span id="tmp_file_area">
     <logic:notEmpty name="mbhWml040Form" property="wml040FileLabelList">
       <logic:iterate id="file" name="mbhWml040Form" property="wml040FileLabelList" indexId="idx" scope="request">
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

<div class="font_xsmall">
■<gsmsg:write key="cmn.body" />
</div>
<div align="center">
  <html:textarea name="mbhWml040Form" property="wml040Body" cols="20" rows="4" />
</div>

<div class="font_small">
<div data-role="controlgroup" data-type="horizontal" align="center">
  <br><input name="wml040Send" value="<gsmsg:write key="cmn.sent" />" type="submit" data-icon="forward" /><input name="wml040Draft" value="<gsmsg:write key="cmn.save.draft" />" type="submit" data-icon="grid" data-iconpos="right"/>
</div>
<div align="center">
  <input name="wml040Back" value="<gsmsg:write key="cmn.back" />" type="submit" data-inline="true" data-role="button" data-icon="back" />
</div>
</div>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>


<!-- 添付ファイル -->
<input type="hidden" id="tmp_file_obj_name" value="wml040file" />
<input type="file" id="file_use_check" style="visibility:hidden;"/>
<div id="tmp_pop" style="display:none;">

  <div class="tmppopupmenu">

    <div style="text-align:right;">
      <a href="#" onclick="tmpPopupMenu();" data-role="button" data-icon="delete" data-iconpos="notext">Close</a>
    </div>

    <div id="error_msg_area" align="center"></div>

    <div align="center">
      <div id="fileUpArea" class="fieldcontain">
        <input type="file" id="wml040file" name="wml040file" data-clear-btn="true"/>
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