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
<script language="JavaScript" src='../common/js/jquery.bgiframe.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/jquery.selection-min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery.exfixed.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../nippou/js/ntp087.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery.scrollTable.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/popup.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../nippou/css/nippou.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel="stylesheet" href="../common/css/jquery.scrolltable.css?<%= GSConst.VERSION_PARAM %>" />

</head>

<%
   String maxLengthContent = String.valueOf(1000);
%>

<body class="body_03" onload="showLengthId($('#inputstr')[0], <%= maxLengthContent %>, 'inputlength');" onunload="windowClose();">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<html:form action="/nippou/ntp087">

<input type="hidden" name="CMD" value="">
<input type="hidden" name="helpPrm" value="<bean:write name="ntp087Form" property="ntp087ProcMode" />">
<html:hidden property="ntp086NttSid"/>
<html:hidden property="ntp087initDspFlg" />
<html:hidden property="ntp087InitFlg" />
<html:hidden property="ntp087ProcMode" />
<html:hidden property="ntp010DspDate"/>
<html:hidden property="ntp010SelectUsrSid"/>
<html:hidden property="ntp010SelectUsrKbn"/>
<html:hidden property="ntp010SelectDate" />
<html:hidden property="ntp010NipSid" />
<html:hidden property="ntp010DspGpSid"/>
<html:hidden property="ntp020SelectUsrSid"/>
<html:hidden property="ntp030SelectUsrSid"/>
<html:hidden property="dspMod" />
<html:hidden property="listMod" />
<html:hidden property="backScreen" />

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

<logic:notEmpty name="ntp087Form" property="ntp087memberSid">
<logic:iterate id="usid" name="ntp087Form" property="ntp087memberSid">
  <input type="hidden" name="ntp087memberSid" value="<bean:write name="usid" />">
</logic:iterate>
</logic:notEmpty>

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
    <td width="0%" class="header_white_bg_text" nowrap>[ <gsmsg:write key="cmn.template" /><gsmsg:write key="cmn.entry" /> ]</td>
    <td width="100%" class="header_white_bg">
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="ntp.1" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" class="btn_ok1" value="ＯＫ" onClick="return buttonPush2('ntp087ok');">
    <logic:equal name="ntp087Form" property="ntp087ProcMode" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.CMD_EDIT) %>">
    <input type="button" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onClick="return buttonPush2('del');">
    </logic:equal>
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush2('backNtp087');">
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
  <td>

    <img src="../common/images/spacer.gif" border="0" height="10px" width="1px">

    <table class="tl_u2" border="0" cellpadding="10" cellspacing="0" width="100%">


      <tbody>


      <tr>
        <td class="table_bg_A5B4E1" align="left" nowrap valign="middle" width="0%">
          <span class="text_bb1"><gsmsg:write key="ntp.92" /></span><span class="text_r2">※</span>
        </td>

        <td class="td_type20" id="ntpNoticeUsrArea" align="left" width="100%">
          <html:text name="ntp087Form" property="ntp087TemplateName" maxlength="50" style="width:335px;" />
        </td>
      </tr>




      <tr>
        <td class="table_bg_A5B4E1" align="left" nowrap="" valign="middle" width="0%">
          <span class="text_bb1"><gsmsg:write key="reserve.100" /></span>
        </td>

        <td class="td_type20" id="ntpNoticeUsrArea" align="left" width="100%">
          <div class="text_r1">※<gsmsg:write key="ntp.95" /></div>
          <span class="text_base">
            <html:multibox name="ntp087Form" property="ntp087DspItem" styleId="ntp087DspItem0" value="0" />&nbsp;<label for="ntp087DspItem0"><span><gsmsg:write key="ntp.11" />&nbsp;&nbsp;&nbsp;&nbsp;</span></label>&nbsp;
            <html:multibox name="ntp087Form" property="ntp087DspItem" styleId="ntp087DspItem1" value="1" />&nbsp;<label for="ntp087DspItem1"><span><gsmsg:write key="ntp.15" />・<gsmsg:write key="ntp.16" />&nbsp;&nbsp;&nbsp;&nbsp;</span></label>&nbsp;
            <html:multibox name="ntp087Form" property="ntp087DspItem" styleId="ntp087DspItem2" value="2" />&nbsp;<label for="ntp087DspItem2"><span><gsmsg:write key="ntp.3" />/<gsmsg:write key="ntp.31" />&nbsp;&nbsp;&nbsp;&nbsp;</span></label>
            <html:multibox name="ntp087Form" property="ntp087DspItem" styleId="ntp087DspItem3" value="3" />&nbsp;<label for="ntp087DspItem3"><span><gsmsg:write key="ntp.32" />&nbsp;&nbsp;&nbsp;&nbsp;</span></label>&nbsp;
            <html:multibox name="ntp087Form" property="ntp087DspItem" styleId="ntp087DspItem5" value="5" />&nbsp;<label for="ntp087DspItem5"><span><gsmsg:write key="ntp.96" />&nbsp;&nbsp;&nbsp;&nbsp;</span></label>&nbsp;<br>
            <html:multibox name="ntp087Form" property="ntp087DspItem" styleId="ntp087DspItem4" value="4" />&nbsp;<label for="ntp087DspItem4"><span>添付ファイル&nbsp;&nbsp;&nbsp;&nbsp;</span></label>
          </span>
        </td>
      </tr>


      <tr>
        <td class="table_bg_A5B4E1" align="left" nowrap valign="middle" width="0%">
          <span class="text_bb1"><gsmsg:write key="ntp.94" /></span>
        </td>

        <td class="td_type20" id="ntpNoticeUsrArea" align="left" width="100%">
          <input class="btn_target_n1" value="<gsmsg:write key="ntp.12" /><gsmsg:write key="cmn.select" />" id="targetPopBtn" type="button"><br>

          <div id="targetArea">
            <div>
              <logic:notEmpty name="ntp087Form" property="ntp087DspTarget" scope="request">
                <logic:iterate id="trgSid" name="ntp087Form" property="ntp087DspTarget" scope="request">
                  <input type="hidden" name="ntp087DspTarget" value="<bean:write name="trgSid" />" />
                </logic:iterate>
              </logic:notEmpty>
            </div>
          </div>
          <%--
          <span><gsmsg:write key="ntp.12" />1</span>&nbsp;&nbsp;&nbsp;<span>目標2</span>&nbsp;&nbsp;&nbsp;<span><gsmsg:write key="ntp.12" />3</span>&nbsp;&nbsp;
          --%>


        </td>
      </tr>


      <tr>

        <td class="table_bg_A5B4E1" align="left" nowrap="" valign="middle" width="0%">
          <span class="text_bb1"><gsmsg:write key="schedule.sch100.4" /></span>
        </td>


        <td align="left" class="td_type20" width="100%" id="ntpNoticeUsrArea">

          <span class="text_r1">  ※<gsmsg:write key="ntp.99" /></span>
          <br>
          <table width="0%" border="0">
          <tr>
          <td width="35%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="ntp.149" /></span></td>
          <td width="20%" align="center">&nbsp;</td>
          <td width="35%" align="left">
            <logic:notEqual name="ntp087Form" property="ntp087groupSid" value="-9">

                <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup(this.form.ntp087groupSid, 'ntp087groupSid', '<bean:write name="ntp087Form" property="ntp087groupSid" />', '1', '', 'ntp087memberSid', '-1', '1')" type="button"></br>

            </logic:notEqual>
            <html:select property="ntp087groupSid" styleClass="select01" onchange="selectGroup();">

    <%--
              <logic:notEmpty name="ntp087Form" property="ntp087GroupList">
              <html:optionsCollection name="ntp087Form" property="ntp087GroupList" value="value" label="label" />
              </logic:notEmpty>
    --%>

              <logic:notEmpty name="ntp087Form" property="ntp087GroupList" scope="request">
              <logic:iterate id="gpBean" name="ntp087Form" property="ntp087GroupList" scope="request">

                <bean:define id="gpValue" name="gpBean" property="value" type="java.lang.String" />
                <logic:equal name="gpBean" property="styleClass" value="0">
                <html:option value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
                </logic:equal>

                <logic:notEqual name="gpBean" property="styleClass" value="0">
                <html:option styleClass="select03" value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
                </logic:notEqual>

              </logic:iterate>
              </logic:notEmpty>

            </html:select>
          </td>
          <td width="10%" align="left" valign="bottom">

            <input type="button" onclick="openGroupWindow(this.form.ntp087groupSid, 'ntp087groupSid', '0', 'changeGrp')" class="group_btn2" value="&nbsp;&nbsp;" id="ntp087GroupBtn">

          </td>
          </tr>

          <tr>
          <td align="center">
            <html:select property="ntp087SelectLeft" size="7" styleClass="select01" multiple="true">

              <logic:notEmpty name="ntp087Form" property="ntp087LeftList" scope="request">
              <logic:iterate id="gplBean" name="ntp087Form" property="ntp087LeftList" scope="request">

                <bean:define id="gplValue" name="gplBean" property="value" type="java.lang.String" />
                <logic:equal name="gplBean" property="styleClass" value="0">
                <html:option value="<%= gplValue %>"><bean:write name="gplBean" property="label" /></html:option>
                </logic:equal>

                <logic:notEqual name="gplBean" property="styleClass" value="0">
                <html:option styleClass="select03" value="<%= gplValue %>"><bean:write name="gplBean" property="label" /></html:option>
                </logic:notEqual>

              </logic:iterate>
              </logic:notEmpty>

            </html:select>
          </td>
          <td align="center">
            <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('add');"><br><br>
            <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('remove');">
          </td>
          <td>
            <html:select property="ntp087SelectRight" size="7" styleClass="select01" multiple="true">

              <logic:notEmpty name="ntp087Form" property="ntp087RightList" scope="request">
              <logic:iterate id="gprBean" name="ntp087Form" property="ntp087RightList" scope="request">

                <bean:define id="gprValue" name="gprBean" property="value" type="java.lang.String" />
                <logic:equal name="gprBean" property="styleClass" value="0">
                <html:option value="<%= gprValue %>"><bean:write name="gprBean" property="label" /></html:option>
                </logic:equal>

                <logic:notEqual name="gprBean" property="styleClass" value="0">
                <html:option styleClass="select03" value="<%= gprValue %>"><bean:write name="gprBean" property="label" /></html:option>
                </logic:notEqual>

              </logic:iterate>
              </logic:notEmpty>

            </html:select>
          </td>
          </tr>
          </table>



        </td>
      </tr>


      <tr>
        <td class="table_bg_A5B4E1" align="left" nowrap valign="middle" width="0%">
          <span class="text_bb1"><gsmsg:write key="cmn.content" />(<gsmsg:write key="ntp.10" />)</span>
        </td>

        <td align="left" class="td_type20" width="90%">
          <% String onKeyUp = "showLengthStr(value, " + String.valueOf(maxLengthContent) + ", 'inputlength');"; %>
          <html:textarea name="ntp087Form" property="ntp087Detail" styleClass="text_base" style="width:431px;" rows="10" onkeyup="<%= onKeyUp %>" styleId="inputstr" />
          <br>
          <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthContent %>&nbsp;<gsmsg:write key="cmn.character" /></span>
        </td>

      </tr>

    </tbody></table>

    <img src="../common/images/spacer.gif" border="0" height="10px" width="1px">


  </td>
  </tr>
























  <tr>
  <td align="left">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
    <input type="button" class="btn_ok1" value="ＯＫ" onClick="return buttonPush2('ntp087ok');">
    <logic:equal name="ntp087Form" property="ntp087ProcMode" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.CMD_EDIT) %>">
    <input type="button" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onClick="return buttonPush2('del');">
    </logic:equal>
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush2('backNtp087');">
    </td>
    </tr>
    </table>
  </td>
  </tr>
  </table>

</td>
</tr>
</table>


<div id="targetPop" title="<gsmsg:write key="ntp.12" /><gsmsg:write key="cmn.select" />" style="display:none;">
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

        <!-- 目標名 -->
        <th width="83%" nowrap="nowrap">
          <span class="text_tlw"><gsmsg:write key="ntp.101" /></span>
        </th>

        <!-- 目標値 -->
        <%--
        <th width="25%" nowrap="nowrap" style="border-right:0px">
          <span class="text_tlw">目標値/月</span>
        </th>
        --%>


        <!-- 単位 -->
        <th width="10%" nowrap="nowrap" style="border-right:0px">
          <span class="text_tlw"><gsmsg:write key="ntp.102" /></span>
        </th>
        <th  nowrap="nowrap" style="width:5px; border-left:0px" >&nbsp;</th>

      </tr>
      </thead>


      <tbody>
        <logic:notEmpty name="ntp087Form" property="ntp087TargetList">
          <logic:iterate id="trgMdl" name="ntp087Form" property="ntp087TargetList">
            <bean:define id="ntgsid" name="trgMdl" property="ntgSid" />
            <tr class="td_type1" style="font-size:90%;cursor:pointer;" id="tr_<%= ntgsid.toString() %>" onclick="clickTargetName(3, <%= ntgsid.toString() %>);">

              <!-- チェックボックス -->
              <td width="7%">
                <%-- html:multibox name="ntp087Form" property="ntp087DspTarget" value="<%= ntgsid.toString() %>" onclick="clickMulti();"/ --%>
                <input type="checkbox" name="poptarget" value="<%= ntgsid.toString() %>" onclick="clickMulti();">
              </td>

              <!-- 目標名 -->
              <td width="83%" id="td_name_<%= ntgsid.toString() %>" nowrap="nowrap"><bean:write name="trgMdl" property="ntgName" /></td>

              <!-- 単位 -->
              <td width="10%" align="center" nowrap="nowrap" style="border-right:0px"><bean:write name="trgMdl" property="ntgUnit" /></td>
              <td style="width:5px;border-left:0px"></td>

            </tr>
          </logic:iterate>
        </logic:notEmpty>





      </tbody>
    </table>

    <logic:empty name="ntp087Form" property="ntp087TargetList">
      <span style="padding-top:220px;height:100%;width:100%;font-weight:bold;text-align:center;"><gsmsg:write key="ntp.103" /></span>
    </logic:empty>

    </div>





















  </p>
</div>


</html:form>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>