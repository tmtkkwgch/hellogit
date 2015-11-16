<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="cmn.bulletin" /><gsmsg:write key="bbs.bbs090.1" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<script language="JavaScript" src="../mobile/sp/js/sp_file_upload.js?453<%= GSConst.VERSION_PARAM %>"></script>
<% pluginName = "bbs"; %>
<% thisForm = "mbhBbs070Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_bbs070">
<html:hidden property="mobileType" value="1"/>
<input type="hidden" name="CMD" value="">
<html:hidden name="mbhBbs070Form" property="bbs010page1" />
<html:hidden name="mbhBbs070Form" property="bbs010forumSid" />
<html:hidden name="mbhBbs070Form" property="bbs020page1" />
<html:hidden name="mbhBbs070Form" property="searchDspID" />
<html:hidden name="mbhBbs070Form" property="threadSid" />
<html:hidden name="mbhBbs070Form" property="bbs030page1" />
<html:hidden name="mbhBbs070Form" property="bbs030writeSid" />
<html:hidden name="mbhBbs070Form" property="bbs030orderKey" />

<html:hidden name="mbhBbs070Form" property="bbs070InitFlg" />


<logic:messagesPresent message="false">
<html:errors/>
</logic:messagesPresent>

<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<a href="#" onClick="buttonPush('bbs070back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
<img src="../mobile/sp/imgages/bbs_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="cmn.bulletin" /><br><gsmsg:write key="bbs.bbs090.1" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
  <li data-role="list-divider">
    <div align="center">
      <b><div class="font_small"><bean:write name="mbhBbs070Form" property="bbs070forumName" /></div></b>
      <hr>
      <b><bean:write name="mbhBbs070Form" property="bbs070threTitle" /></b>
    </div>
  </li>
</ul>

<div class="font_small">■<gsmsg:write key="cmn.contributor" /></div>
<html:select property="bbs070contributor">
  <html:optionsCollection name="mbhBbs070Form" property="bbs070contributorList" value="value" label="label" />
</html:select>
<br>

<div class="font_small">■<gsmsg:write key="cmn.content" /></div>
<br>
<textarea name="bbs070value" cols="16" rows="3"><bean:write name="mbhBbs070Form" property="bbs070value" /></textarea>
<br>

  <!-- ファイル添付 -->
<div class="font_small">■<gsmsg:write key="cmn.attach.file"/></div>
<span id="tmp_file_area">

  <logic:notEmpty name="mbhBbs070Form" property="bbs070FileLabelList">
    <logic:iterate id="file" name="mbhBbs070Form" property="bbs070FileLabelList" indexId="idx" scope="request">
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

<div data-role="controlgroup" data-type="horizontal" align="center">
  <input name="bbs070ok" value="OK" type="submit" data-inline="true" data-icon="check" />
  <input name="bbs070back" value="<gsmsg:write key="cmn.back" />" type="submit" data-inline="true" data-icon="back" data-iconpos="right" />
</div>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

<!-- 添付ファイル -->
<input type="hidden" id="tmp_file_obj_name" value="bbs070file" />
<input type="file" id="file_use_check" style="visibility:hidden;"/>
<div id="tmp_pop" style="display:none;">

  <div class="tmppopupmenu">

    <div style="text-align:right;">
      <a href="#" onclick="tmpPopupMenu();" data-role="button" data-icon="delete" data-iconpos="notext">Close</a>
    </div>

    <div id="error_msg_area" align="center"></div>

    <div align="center">
      <div id="fileUpArea" class="fieldcontain">
        <input type="file" id="bbs070file" name="bbs070file" data-clear-btn="true"/>
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