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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[Group Session] <gsmsg:write key="ntp.1" /></title>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../nippou/js/ntp231.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/popup.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../nippou/css/nippou.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<% String maxLengthContent = String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.MAX_LENGTH_VALUE); %>

<body class="body_03" onload="showLengthId($('#inputstr')[0], <%= maxLengthContent %>, 'inputlength');">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<html:form action="/nippou/ntp231">
<input type="hidden" name="helpPrm" value="<bean:write name="ntp231Form" property="ntp230ProcMode" />">
<input type="hidden" name="CMD" value="">
<html:hidden property="ntp230NtgSid" />
<html:hidden property="ntp230ProcMode" />
<html:hidden property="ntp231initDspFlg" />
<html:hidden property="ntp231InitFlg" />

<!--　BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../nippou/images/header_nippou_02.gif" border="0" alt="<gsmsg:write key="ntp.1" />"></td>
    <td width="0%" class="header_white_bg_text" nowrap>[ <gsmsg:write key="ntp.12" /><gsmsg:write key="cmn.entry" /> ]</td>
    <td width="100%" class="header_white_bg">
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="ntp.1" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" class="btn_ok1" value="ＯＫ" onClick="return buttonPush2('ok');">
    <logic:equal name="ntp231Form" property="ntp230ProcMode" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.CMD_EDIT) %>">
    <input type="button" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onClick="return buttonPush2('del');">
    </logic:equal>
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush2('backNtp231');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>
  </td>
  </tr>

  <tr>
  <td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td>
  </tr>

  <logic:messagesPresent message="false">
  <tr><td align="left"><span class="TXT02"><html:errors/></span></td></tr>
  <tr><td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td></tr>
  </logic:messagesPresent>

  <tr>
  <td align="left">

    <table width="100%" class="tl0" border="0" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><gsmsg:write key="ntp.101" /></span><span class="text_r2">※</span></td>
    <td align="left" class="td_type20" width="90%">
    <html:text name="ntp231Form" property="ntp231TargetName" maxlength="50" style="width:335px;" />
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><gsmsg:write key="ntp.102" /></span><span class="text_r2">※</span></td>
    <td align="left" class="td_type20" width="90%">
    <html:text name="ntp231Form" property="ntp231TargetUnit" maxlength="15" style="width:95px;" />
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><gsmsg:write key="ntp.10" /></span><span class="text_r2">※</span></td>
    <td align="left" class="td_type20" width="90%">
    <html:text name="ntp231Form" property="ntp231TargetDef" style="text-align:right; width:125px;" maxlength="15" />/月
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.content" /></span></td>
    <td align="left" class="td_type20" width="90%">
      <% String onKeyUp = "showLengthStr(value, " + String.valueOf(maxLengthContent) + ", 'inputlength');"; %>
      <html:textarea name="ntp231Form" property="ntp231TargetDetail" styleClass="text_base" style="width:488px" rows="10" onkeyup="<%= onKeyUp %>" styleId="inputstr" />
      <br>
      <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthContent %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
    </tr>

      <tr>
        <td class="table_bg_A5B4E1" align="left" nowrap valign="middle" width="0%">
          <span class="text_bb1"><gsmsg:write key="cmn.template" /></span>
        </td>

        <td class="td_type20" id="ntpNoticeUsrArea" align="left" width="100%">
          <span class="text_r1">  ※<gsmsg:write key="ntp.135" /></span><br>
          <input class="btn_template_n1" value="<gsmsg:write key="ntp.136" />" id="templatePopBtn" type="button"><br>

          <div id="templateArea">
            <div>
              <logic:notEmpty name="ntp231Form" property="ntp231DspTemplate" scope="request">
                <logic:iterate id="nttSid" name="ntp231Form" property="ntp231DspTemplate" scope="request">
                  <input type="hidden" name="ntp231DspTemplate" value="<bean:write name="nttSid" />" />
                </logic:iterate>
              </logic:notEmpty>
            </div>
          </div>

        </td>
      </tr>

    </table>
  </td>
  </tr>

  <tr>
  <td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td>
  </tr>

  <tr>
  <td align="left">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
    <input type="button" class="btn_ok1" value="ＯＫ" onClick="return buttonPush2('ok');">
    <logic:equal name="ntp231Form" property="ntp230ProcMode" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.CMD_EDIT) %>">
    <input type="button" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onClick="return buttonPush2('del');">
    </logic:equal>
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush2('backNtp231');">
    </td>
    </tr>
    </table>
  </td>
  </tr>
  </table>

</td>
</tr>
</table>






<div id="templatePop" title="<gsmsg:write key="ntp.136" />" style="display:none;">
  <p>
    <div style="border:solid 1px;border-color:#9a9a9a;height:300px;overflow-y: scroll">
    <table class="table_td_border2" width="100%" cellpadding="0" cellspacing="0">
      <thead>
      <tr class="table_bg_7D91BD">

        <!-- チェックボックス -->
        <th width="7%" align="center" nowrap="nowrap">
         <span class="text_tlw">
         <%--
           <input type="checkbox" name="cmn220AllCheckBottom" value="1" onClick="chgCheckAll('cmn220AllCheckBottom', 'cmn220userSidBottom');chgCheckAllChange2('cmn220AllCheckBottom', 'cmn220userSidBottom');">
        --%>
        </th>

        <!-- テンプレート名 -->
        <th width="83%" nowrap="nowrap">
          <span class="text_tlw"><gsmsg:write key="ntp.92" /></span>
        </th>


      </tr>
      </thead>


      <tbody>
        <logic:notEmpty name="ntp231Form" property="ntp231TemplateList">
          <logic:iterate id="tmpMdl" name="ntp231Form" property="ntp231TemplateList">
            <bean:define id="nttsid" name="tmpMdl" property="nttSid" />
            <tr class="td_type1" style="font-size:90%;cursor:pointer;" id="tr_<%= nttsid.toString() %>" onclick="clickTemplateName(3, <%= nttsid.toString() %>);">

              <!-- チェックボックス -->
              <td width="7%">
                <%-- html:multibox name="ntp087Form" property="ntp087DspTarget" value="<%= ntgsid.toString() %>" onclick="clickMulti();"/ --%>
                <input type="checkbox" name="poptemplate" value="<%= nttsid.toString() %>" onclick="clickMulti();">
              </td>

              <!-- テンプレート名 -->
              <td width="83%" id="td_name_<%= nttsid.toString() %>" nowrap="nowrap"><bean:write name="tmpMdl" property="nttName" /></td>

            </tr>
          </logic:iterate>
        </logic:notEmpty>


      </tbody>
    </table>

    <logic:empty name="ntp231Form" property="ntp231TemplateList">
      <span style="padding-top:220px;height:100%;width:100%;font-weight:bold;text-align:center;"><gsmsg:write key="ntp.137" /></span>
    </logic:empty>

    </div>

  </p>
</div>





</html:form>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>