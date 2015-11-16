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
<script language="JavaScript" src="../nippou/js/ntp085.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css' type='text/css'>
<link rel=stylesheet href='../nippou/css/nippou.css' type='text/css'>

</head>

<body class="body_03">
<html:form action="/nippou/ntp085">
<input type="hidden" name="CMD" value="">
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
    <td width="100%" class="header_ktool_bg_text">[ <gsmsg:write key="ntp.1" /> <gsmsg:write key="cmn.sml.notification.setting" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('ntp085ok');">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('ntp085back');"></td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">



    <table cellpadding="10" cellspacing="0" border="0" width="100%" class="tl_u2">

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="20%" nowrap id="schRepertArea1" rowspan="3">
      <span class="text_bb1"><gsmsg:write key="ntp.88" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <html:radio name="ntp085Form" styleId="ntp085NoticeKbn_00" property="ntp085NoticeKbn" value="<%= String.valueOf(GSConstNippou.SML_NOTICE_ADM) %>"/><label for="ntp085NoticeKbn_00"><span class="text_base6_2"><gsmsg:write key="cmn.set.the.admin" /></span></label>&nbsp;
      <html:radio name="ntp085Form" styleId="ntp085NoticeKbn_01" property="ntp085NoticeKbn" value="<%= String.valueOf(GSConstNippou.SML_NOTICE_USR) %>"/><label for="ntp085NoticeKbn_01"><span class="text_base6_2"><gsmsg:write key="cmn.set.eachuser" /></span></label>&nbsp;
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="td_wt" style="border-collapse: collapse;" id="smlNoticeKbnArea">
      <html:radio name="ntp085Form" styleId="ntp085SmlNoticeKbn_00" property="ntp085SmlNoticeKbn" value="<%= String.valueOf(GSConstNippou.SML_NOTICE_NO) %>" /><label for="ntp085SmlNoticeKbn_00"><span class="text_base"><gsmsg:write key="cmn.dont.notify" /></span></label>&nbsp;&nbsp;
      <html:radio name="ntp085Form" styleId="ntp085SmlNoticeKbn_01" property="ntp085SmlNoticeKbn" value="<%= String.valueOf(GSConstNippou.SML_NOTICE_YES) %>" /><label for="ntp085SmlNoticeKbn_01"><span class="text_base"><gsmsg:write key="cmn.notify" /></span></label>&nbsp;&nbsp;
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="td_wt" style="border-collapse: collapse;" id="smlNoticeKbnPlace">
      <html:radio name="ntp085Form" styleId="ntp085SmlNoticePlace_00" property="ntp085SmlNoticePlace" value="<%= String.valueOf(GSConstNippou.SML_NOTICE_GROUP) %>" /><label for="ntp085SmlNoticePlace_00"><span class="text_base"><gsmsg:write key="ntp.160" /></span></label>&nbsp;&nbsp;
      <html:radio name="ntp085Form" styleId="ntp085SmlNoticePlace_01" property="ntp085SmlNoticePlace" value="<%= String.valueOf(GSConstNippou.SML_NOTICE_GROUP_ADM) %>" /><label for="ntp085SmlNoticePlace_01"><span class="text_base"><gsmsg:write key="ntp.161" /></span></label>&nbsp;&nbsp;
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="20%" nowrap rowspan="2">
      <span class="text_bb1"><gsmsg:write key="ntp.89" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <html:radio name="ntp085Form" styleId="ntp085CmtNoticeKbn_00" property="ntp085CmtNoticeKbn" value="<%= String.valueOf(GSConstNippou.SML_NOTICE_ADM) %>"/><label for="ntp085CmtNoticeKbn_00"><span class="text_base6_2"><gsmsg:write key="cmn.set.the.admin" /></span></label>&nbsp;
      <html:radio name="ntp085Form" styleId="ntp085CmtNoticeKbn_01" property="ntp085CmtNoticeKbn" value="<%= String.valueOf(GSConstNippou.SML_NOTICE_USR) %>"/><label for="ntp085CmtNoticeKbn_01"><span class="text_base6_2"><gsmsg:write key="cmn.set.eachuser" /></span></label>&nbsp;
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="td_wt" style="border-collapse: collapse;" id="cmtSmlNoticeKbnArea">
      <html:radio name="ntp085Form" styleId="ntp085CmtSmlNoticeKbn_00" property="ntp085CmtSmlNoticeKbn" value="<%= String.valueOf(GSConstNippou.SML_NOTICE_NO) %>" /><label for="ntp085CmtSmlNoticeKbn_00"><span class="text_base"><gsmsg:write key="cmn.dont.notify" /></span></label>&nbsp;&nbsp;
      <html:radio name="ntp085Form" styleId="ntp085CmtSmlNoticeKbn_01" property="ntp085CmtSmlNoticeKbn" value="<%= String.valueOf(GSConstNippou.SML_NOTICE_YES) %>" /><label for="ntp085CmtSmlNoticeKbn_01"><span class="text_base"><gsmsg:write key="cmn.notify" /></span></label>&nbsp;&nbsp;
    </td>
    </tr>


    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="20%" nowrap rowspan="2">
      <span class="text_bb1"><gsmsg:write key="ntp.9" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <html:radio name="ntp085Form" styleId="ntp085GoodNoticeKbn_00" property="ntp085GoodNoticeKbn" value="<%= String.valueOf(GSConstNippou.SML_NOTICE_ADM) %>"/><label for="ntp085GoodNoticeKbn_00"><span class="text_base6_2"><gsmsg:write key="cmn.set.the.admin" /></span></label>&nbsp;
      <html:radio name="ntp085Form" styleId="ntp085GoodNoticeKbn_01" property="ntp085GoodNoticeKbn" value="<%= String.valueOf(GSConstNippou.SML_NOTICE_USR) %>"/><label for="ntp085GoodNoticeKbn_01"><span class="text_base6_2"><gsmsg:write key="cmn.set.eachuser" /></span></label>&nbsp;
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="td_wt" style="border-collapse: collapse;" id="goodSmlNoticeKbnArea">
      <html:radio name="ntp085Form" styleId="ntp085GoodSmlNoticeKbn_00" property="ntp085GoodSmlNoticeKbn" value="<%= String.valueOf(GSConstNippou.SML_NOTICE_NO) %>" /><label for="ntp085GoodSmlNoticeKbn_00"><span class="text_base"><gsmsg:write key="cmn.dont.notify" /></span></label>&nbsp;&nbsp;
      <html:radio name="ntp085Form" styleId="ntp085GoodSmlNoticeKbn_01" property="ntp085GoodSmlNoticeKbn" value="<%= String.valueOf(GSConstNippou.SML_NOTICE_YES) %>" /><label for="ntp085GoodSmlNoticeKbn_01"><span class="text_base"><gsmsg:write key="cmn.notify" /></span></label>&nbsp;&nbsp;
    </td>
    </tr>

    </table>



    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
    <td width="100%" align="right">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('ntp085ok');">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('ntp085back');"></td>
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

</html:form>
</body>
</html:html>