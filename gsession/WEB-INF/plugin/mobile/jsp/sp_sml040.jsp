<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String markNone = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_NONE); %>
<% String markTel = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_TEL); %>
<% String markImp = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_INP); %>
<% String markSmaily    = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_SMAILY);  %>
<% String markWorry     = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_WORRY);   %>
<% String markAngry     = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_ANGRY);   %>
<% String markSadly     = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_SADRY);   %>
<% String markBeer      = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_BEER);    %>
<% String markHart      = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_HART);    %>
<% String markZasetsu   = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_ZASETSU); %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>

<html:html>
<head>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "sml"; %>
<% thisForm = "mbhSml040Form"; %>
<script language="JavaScript" src="../mobile/sp/js/sp_mobile.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../mobile/sp/js/sp_file_upload.js?453<%= GSConst.VERSION_PARAM %>"></script>

</head>


<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form method="POST" action="/mobile/sp_sml040">
<input type="hidden" name="CMD" value="">
<html:hidden property="smlViewAccount" />
<html:hidden property="smlViewAccountName" />
<html:hidden property="mobileType" value="1"/>
<html:hidden property="sml010ProcMode" />
<html:hidden property="sml040ProcMode" />
<html:hidden property="sml040InitFlg" />
<html:hidden property="sml040DelUsrId" />
<html:hidden property="sml020PageNum" />
<html:hidden property="sml020SelectedSid" />

<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<a href="#" onClick="buttonPush('sml040back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
<img src="../mobile/sp/imgages/sml_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="cmn.shortmail" /><br><gsmsg:write key="cmn.create.new" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<logic:notEmpty name="mbhSml040Form" property="sml040userSid">
<logic:iterate id="usid" name="mbhSml040Form" property="sml040userSid">
  <input type="hidden" name="sml040userSid" value="<bean:write name="usid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhSml040Form" property="sml040userSidCc">
<logic:iterate id="usidcc" name="mbhSml040Form" property="sml040userSidCc">
  <input type="hidden" name="sml040userSidCc" value="<bean:write name="usidcc" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhSml040Form" property="sml040userSidBcc">
<logic:iterate id="usidbcc" name="mbhSml040Form" property="sml040userSidBcc">
  <input type="hidden" name="sml040userSidBcc" value="<bean:write name="usidbcc" />">
</logic:iterate>
</logic:notEmpty>

<logic:messagesPresent message="false">
<span style="color: red"><html:errors/></span>
</logic:messagesPresent>
<logic:notEmpty name="mbhSml040Form" property="sml030AtesakiDeletedMessage">
<span style="color: red"><bean:write name="mbhSml040Form" property="sml030AtesakiDeletedMessage" filter="false"/></span>
<br />
<br />
</logic:notEmpty>

<div class="font_small">■<gsmsg:write key="cmn.sender" />：<bean:write name="mbhSml040Form"  property="smlViewAccountName" /></div>

<div class="font_small">■<gsmsg:write key="cmn.from" /></div>

<logic:notEmpty name="mbhSml040Form" property="sml040SelectUserList">
  <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
    <logic:iterate id="usrLabel" name="mbhSml040Form" property="sml040SelectUserList" indexId="idx">
      <li data-icon="delete">
        <a href="#" onClick="delToUserPush('<bean:write name="usrLabel" property="value" />');" ><bean:write name="usrLabel" property="label" /></a>
      </li>
    </logic:iterate>
  </ul>
</logic:notEmpty>

<div align="center" class="font_small">
  <input name="sml040Select" value="<gsmsg:write key="sml.sml020.05" />" type="submit" data-inline="true" data-role="button" data-icon="arrow-r" data-iconpos="right"/>
</div>

<div class="font_small">■CC</div>

<logic:notEmpty name="mbhSml040Form" property="sml040CcSelectUserList">
  <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
    <logic:iterate id="usrCcLabel" name="mbhSml040Form" property="sml040CcSelectUserList" indexId="idx">
      <li data-icon="delete">
        <a href="#" onClick="delCcUserPush('<bean:write name="usrCcLabel" property="value" />');" ><bean:write name="usrCcLabel" property="label" /></a>
      </li>
    </logic:iterate>
  </ul>
</logic:notEmpty>

<div align="center" class="font_small">
  <input name="sml040CcSelect" value="CC<gsmsg:write key="cmn.select" />" type="submit" data-inline="true" data-role="button" data-icon="arrow-r" data-iconpos="right"/>
</div>

<div class="font_small">■BCC</div>

<logic:notEmpty name="mbhSml040Form" property="sml040BccSelectUserList">
  <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
    <logic:iterate id="usrBccLabel" name="mbhSml040Form" property="sml040BccSelectUserList" indexId="idx">
      <li data-icon="delete">
        <a href="#" onClick="delBccUserPush('<bean:write name="usrBccLabel" property="value" />');" ><bean:write name="usrBccLabel" property="label" /></a>
      </li>
    </logic:iterate>
  </ul>
</logic:notEmpty>

<div align="center" class="font_small">
  <input name="sml040BccSelect" value="BCC<gsmsg:write key="cmn.select" />" type="submit" data-inline="true" data-role="button" data-icon="arrow-r" data-iconpos="right"/>
</div>

<div class="font_small">■<gsmsg:write key="cmn.subject" /></div>
<br>
<html:text name="mbhSml040Form" property="sml040Title" size="27"  maxlength="70" />
<br>


<div class="font_small">■<gsmsg:write key="cmn.mark" /></div>

<div align="center">
  <html:select name="mbhSml040Form" property="sml040Mark">
  <logic:notEmpty name="mbhSml040Form" property="sml040MarkLabel">
    <html:optionsCollection name="mbhSml040Form" property="sml040MarkLabel" value="value" label="label" />
  </logic:notEmpty>
  </html:select>
</div>


<div class="font_small">■添付ファイル</div>


<span id="tmp_file_area">

  <%--
  <logic:notEmpty name="mbhSml040Form" property="sml040FileLabelList">
    <div class="font_small">■<gsmsg:write key="cmn.attach.file" /></div>
    <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
      <logic:iterate id="file" name="mbhSml040Form" property="sml040FileLabelList" indexId="idx" scope="request">
        <li><bean:write name="file" property="label" /><bean:write name="file" property="value" /></li>
      </logic:iterate>
    </ul>
  </logic:notEmpty>
  --%>

  <logic:notEmpty name="mbhSml040Form" property="sml040FileLabelList">
    <logic:iterate id="file" name="mbhSml040Form" property="sml040FileLabelList" indexId="idx" scope="request">
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
<div class="font_small">■<gsmsg:write key="cmn.body" /></div>
<br>
<html:textarea name="mbhSml040Form" property="sml040Body" cols="16" rows="3" ></html:textarea>
<br>

<%--
<logic:notEmpty name="mbhSml040Form" property="sml040FileLabelList">
  <div class="font_small">■<gsmsg:write key="cmn.attach.file" /></div>
  <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
    <logic:iterate id="file" name="mbhSml040Form" property="sml040FileLabelList" indexId="idx" scope="request">
      <li><bean:write name="file" property="label" /><bean:write name="file" property="value" /></li>
    </logic:iterate>
  </ul>
</logic:notEmpty>
--%>

<div data-role="controlgroup" data-type="horizontal" align="center">
  <input name="sml040Ok" value="ＯＫ" type="submit" data-icon="check" /><input name="sml040Hozon" value="<gsmsg:write key="cmn.draft" /><gsmsg:write key="mobile.20" />" type="submit" data-icon="grid" data-iconpos="right"/>
</div>
<div align="center">
  <input name="sml040Back" value="<gsmsg:write key="cmn.back" />" type="submit" data-inline="true" data-role="button" data-icon="back" />
</div>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>


<!-- 添付ファイル -->
<input type="hidden" id="tmp_file_obj_name" value="sml040file" />
<input type="file" id="file_use_check" style="visibility:hidden;"/>
<div id="tmp_pop" style="display:none;">

  <div class="tmppopupmenu">

    <div style="text-align:right;">
      <a href="#" onclick="tmpPopupMenu();" data-role="button" data-icon="delete" data-iconpos="notext">Close</a>
    </div>

    <div id="error_msg_area" align="center"></div>

    <div align="center">
      <div id="fileUpArea" class="fieldcontain">
        <%--
        <input type="file" id="sml040file" name="sml040file[]" data-clear-btn="true" multiple />
        --%>
        <input type="file" id="sml040file" name="sml040file" data-clear-btn="true"/>
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