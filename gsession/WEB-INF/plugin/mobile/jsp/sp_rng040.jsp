<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>
<% int tmodeAll = jp.groupsession.v2.rng.RngConst.RNG_TEMPLATE_ALL; %>
<% String maxLengthContent = String.valueOf(jp.groupsession.v2.rng.RngConst.MAX_LENGTH_CONTENT); %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="rng.62" />(<gsmsg:write key="rng.rng010.02" />)</title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "rng"; %>
<% thisForm = "mbhRng040Form"; %>
<script language="JavaScript" src="../mobile/sp/js/sp_file_upload.js?453<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_rng040">
<html:hidden property="mobileType" value="1"/>
<input type="hidden" name="CMD" value="">
<html:hidden property="rngSid" />
<html:hidden property="rngProcMode" />
<html:hidden property="rngCmdMode" />
<html:hidden property="rng020sortKey" />
<html:hidden property="rng020orderKey" />
<html:hidden property="rng020pageTop" />
<html:hidden property="rng040InitFlg" />


<logic:notEmpty name="mbhRng040Form" property="rng040apprUser">
<logic:iterate id="apprUser" name="mbhRng040Form" property="rng040apprUser">
  <input type="hidden" name="rng040apprUser" value="<bean:write name='apprUser' />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhRng040Form" property="rng040confirmUser">
<logic:iterate id="confirmUser" name="mbhRng040Form" property="rng040confirmUser">
  <input type="hidden" name="rng040confirmUser" value="<bean:write name='confirmUser' />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhRng040Form" property="rng040files">
<logic:iterate id="files" name="mbhRng040Form" property="rng040files">
  <input type="hidden" name="rng040files" value="<bean:write name='files' />">
</logic:iterate>
</logic:notEmpty>

<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<a href="#" onClick="buttonPush('rng040back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
<img src="../mobile/sp/imgages/rng_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="rng.62" /><br><gsmsg:write key="rng.rng010.02" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<logic:messagesPresent message="false">
<span style="color:red;"><html:errors/></span>
</logic:messagesPresent>

<div class="font_small">■<gsmsg:write key="cmn.title" /></div>
<br>
<html:text name="mbhRng040Form" property="rng040Title" size="27" />
<br>
<%--
<div class="font_small">■<gsmsg:write key="rng.47" />：</div><bean:write name="mbhRng040Form" property="rng040requestUser" />
<br>
--%>
<br>
<div class="font_small">■<gsmsg:write key="cmn.content" /></div>
<br><html:textarea name="mbhRng040Form" property="rng040Content" cols="16" rows="3" ></html:textarea>

 <!-- ファイル添付 -->
<br>
<div class="font_small">■<gsmsg:write key="cmn.attach.file"/></div>
<span id="tmp_file_area">

  <logic:notEmpty name="mbhRng040Form" property="rng040fileList">
    <logic:iterate id="file" name="mbhRng040Form" property="rng040fileList" indexId="idx" scope="request">
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
<div class="font_small">■<gsmsg:write key="rng.24" /><gsmsg:write key="cmn.template" /> </div>
<br>
<div align="center">
  <div class="font_small">
    <html:select property="rng040rctSid" styleClass="select01">
      <html:optionsCollection name="mbhRng040Form" property="rng040rctList" value="value" label="label" />
    </html:select>
    <input type="button" value="<gsmsg:write key="rng.rng020.02" />" onClick="buttonPush('setChannel');" data-inline="true" data-icon="plus" data-iconpos="right"/>
  </div>
</div>
<br>

<div class="font_small">■<gsmsg:write key="rng.24" /></div>
<logic:notEmpty name="mbhRng040Form" property="rng040apprUserList">
  <div data-role="controlgroup" align="center">
    <logic:iterate id="urBean" name="mbhRng040Form" property="rng040apprUserList" indexId="idx">
      <bean:define id="usrId" name="urBean" property="value" />
      <bean:define id="usrName" name="urBean" property="label" />
      <html:radio name="mbhRng040Form" property="rng040DelUsrId" styleId="<%= idx.toString() %>" value="<%= usrId.toString() %>"/><label for="<%= idx.toString() %>"><%= usrName.toString() %></label>
    </logic:iterate>
  </div>
  <div data-role="controlgroup" align="center" data-type="horizontal">
      <input type="button" value="▲" onClick="buttonPush('upAppr');" data-inline="true"/>
      <span class="font_middle3"><input type="button" value="<gsmsg:write key="cmn.delete" />" onClick="buttonPush('delAppr');" data-inline="true"/></span>
      <input type="button" value="▼" onClick="buttonPush('downAppr');" data-inline="true"/>
  </div>
</logic:notEmpty>

<div align="center">
  <div class="font_small">
    <input name="rng040Select" value="<gsmsg:write key="mobile.21" />" type="submit" data-inline="true" data-icon="plus" data-iconpos="right"/>
  </div>
</div>

<hr>
<br>

<div class="font_small">■<gsmsg:write key="rng.35" /></div>
<logic:notEmpty name="mbhRng040Form" property="rng040confirmUserList">
  <div data-role="controlgroup" align="center">
    <logic:iterate id="urBean" name="mbhRng040Form" property="rng040confirmUserList">
      <input name="<bean:write name="urBean" property="value" />" value="<bean:write name="urBean" property="label" />" type="submit" data-icon="delete"/>
    </logic:iterate>
  </div>
</logic:notEmpty>

<div align="center">
  <div class="font_small">
    <input name="rng040KakuninSelect" value="<gsmsg:write key="rng.35" /><gsmsg:write key="cmn.select" />" type="submit" data-inline="true" data-icon="plus" data-iconpos="right"/>
  </div>
</div>

<hr>

<logic:notEmpty name="mbhRng040Form" property="rng040fileList">
  <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
    <li data-role="list-divider"><div align="center"><gsmsg:write key="cmn.attached" /></div></li>
    <logic:iterate id="tmp" name="mbhRng040Form" property="rng040fileList">
      <li>
      <bean:write name="tmp" property="label" />
      </li>
    </logic:iterate>
  </ul>
</logic:notEmpty>

<div data-role="controlgroup" data-type="horizontal" align="center">
  <input name="rng040Ok" value="<gsmsg:write key="rng.46" />" type="submit" data-inline="true" data-icon="forward" /><input name="rng040Hozon" value="<gsmsg:write key="cmn.draft" /><gsmsg:write key="mobile.20" />" type="submit" data-inline="true" data-icon="grid" data-iconpos="right"/>
</div>

<div align="center">
  <input name="rng040Back" value="<gsmsg:write key="cmn.back" />" type="submit" data-inline="true" data-icon="back" />
</div>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

<!-- 添付ファイル -->
<input type="hidden" id="tmp_file_obj_name" value="rng040file" />
<input type="file" id="file_use_check" style="visibility:hidden;"/>
<div id="tmp_pop" style="display:none;">

  <div class="tmppopupmenu">

    <div style="text-align:right;">
      <a href="#" onclick="tmpPopupMenu();" data-role="button" data-icon="delete" data-iconpos="notext">Close</a>
    </div>

    <div id="error_msg_area" align="center"></div>

    <div align="center">
      <div id="fileUpArea" class="fieldcontain">
        <input type="file" id="rng040file" name="rng040file" data-clear-btn="true"/>
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