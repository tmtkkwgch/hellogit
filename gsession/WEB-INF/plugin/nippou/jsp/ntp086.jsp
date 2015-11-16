<%@ page pageEncoding="Windows-31J" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-dailyScheduleRow.tld" prefix="dailyScheduleRow" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ page import="jp.groupsession.v2.ntp.GSConstNippou" %>
<html:html>

<head>
<title>[GroupSession] <gsmsg:write key="cmn.admin.setting.menu" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../nippou/js/ntp086.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery.bgiframe.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/jquery.selection-min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery.exfixed.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/userpopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css' type='text/css'>
<link rel=stylesheet href='../nippou/css/nippou.css' type='text/css'>
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

</head>

<body class="body_03">
<html:form action="/nippou/ntp086">
<input type="hidden" name="CMD" value="">
<html:hidden property="ntp010DspDate"/>
<html:hidden property="ntp010SelectUsrSid"/>
<html:hidden property="ntp010SelectUsrKbn"/>
<html:hidden property="ntp010SelectDate" />
<html:hidden property="ntp010NipSid" />
<html:hidden property="ntp010DspGpSid"/>
<html:hidden property="ntp020SelectUsrSid"/>
<html:hidden property="ntp030SelectUsrSid"/>
<html:hidden property="ntp086NttSid"/>
<html:hidden property="ntp086pageNum"/>
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

<logic:notEmpty name="ntp086Form" property="ntp086TemplateList" scope="request">
  <logic:iterate id="sort" name="ntp086Form" property="ntp086TemplateList" scope="request">
    <input type="hidden" name="ntp086sortLabel" value="<bean:write name="sort" property="templateValue" />">
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
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="100%" class="header_ktool_bg_text">[ <gsmsg:write key="ntp.1" /> <gsmsg:write key="cmn.template" /><gsmsg:write key="cmn.list" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.add" />" class="btn_add_n1" onClick="buttonPush('ntp086add');">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('ntp086back');"></td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="10" cellspacing="0" border="0" width="100%" class="tl_u2">


      <tr>
      <td align="right">


      </td>
      </tr>

      <tr>
      <td align="left">

        <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
        <tr>
          <td>
            <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.up" />" name="btn_upper" onClick="buttonPush2('upTemplateData');">
            <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.down" />" name="btn_downer" onClick="buttonPush2('downTemplateData');">
          </td>
        </tr>
        </table>
        <div class="text_r1">※<gsmsg:write key="ntp.91" /></div>
        <table class="tl0 table_td_border2" cellpadding="5" cellspacing="0" border="0" width="100%">

        <tr>
        <th align="center" class="table_bg_7D91BD" width="5%" nowrap><span class="text_tlw"></span></th>
        <th align="center" class="table_bg_7D91BD" width="85%" nowrap><span class="text_tlw"><gsmsg:write key="ntp.92" /></span></th>
        <th class="table_bg_7D91BD" align="center" nowrap width="10%"><span class="text_tlw"><gsmsg:write key="ntp.93" /></span></th>
        </tr>
        <!-- bmk050 -->

        <logic:notEmpty name="ntp086Form" property="ntp086TemplateList">
        <bean:define id="tdColor" value="" />

        <% String[] tdColors = new String[] {"td_type1", "td_type_usr"}; %>

        <logic:iterate id="templateMdl" name="ntp086Form" property="ntp086TemplateList" indexId="idx">
        <bean:define id="radiovalue" name="templateMdl" property="templateValue" />
        <% tdColor = tdColors[(idx.intValue() % 2)]; %>

        <tr align="center" class="<%= tdColor %>">
        <!-- ラジオボタン -->
        <td align="center">
        <html:radio property="ntp086sortTemplate" value="<%= String.valueOf(radiovalue) %>"/>
        </td>
        <!-- テンプレート名 -->
        <td align="left">
        <a href="#" onclick="return buttonSubmit('edit','<bean:write name="templateMdl" property="templateSid" />') ">
        <span class="text_link"><bean:write name="templateMdl" property="templateName" /></span>
        </a>
        </td>

        <td align="center">
          <input id="<bean:write name="templateMdl" property="templateSid" />" class="apcUserBtn btn_base0" value="<gsmsg:write key="ntp.94" />" type="button">
        </td>

        </tr>

        </logic:iterate>
        </logic:notEmpty>

        </table>
      </td>
      </tr>

    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
    <td width="100%" align="right">
      <input type="button" value="<gsmsg:write key="cmn.add" />" class="btn_add_n1" onClick="buttonPush('ntp086add');">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('ntp086back');"></td>
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

<div id="apcUserPop" title="<gsmsg:write key="ntp.93" />" style="display:none;">
  <p>
    <div id="tmpUsrArea">
    </div>
  </p>
</div>

</html:form>
</body>
</html:html>