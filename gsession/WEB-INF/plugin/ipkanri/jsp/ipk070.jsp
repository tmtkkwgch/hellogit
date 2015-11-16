<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../ipkanri/js/ipkanri.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/check.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../ipkanri/css/ip.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<title>[GroupSession] <gsmsg:write key="ipk.ipk070.1" /></title>
</head>

<body class="body_03">
<html:form action="/ipkanri/ipk070">
<html:hidden property="cmd" />
<html:hidden property="netSid" />
<html:hidden property="iadSid" />
<html:hidden property="binSid" />
<html:hidden property="usedKbn" />
<html:hidden property="iadLimit" />
<html:hidden property="deleteAllCheck" />
<html:hidden property="sortKey" />
<html:hidden property="orderKey" />
<html:hidden property="ipk070PageNow" />
<html:hidden property="ipk070MaxPageNum" />
<html:hidden property="ipk070SvNetSid" />
<html:hidden property="ipk070SvSltNet" />
<html:hidden property="ipk070SvGrpSid" />
<html:hidden property="ipk070SvUsrSid" />
<html:hidden property="ipk070SvSearchSortKey1" />
<html:hidden property="ipk070SvSearchOrderKey1" />
<html:hidden property="ipk070SvSearchSortKey2" />
<html:hidden property="ipk070SvSearchOrderKey2" />
<html:hidden property="ipk070SvKeyWord" />
<html:hidden property="ipk070SvKeyWordkbn" />
<html:hidden property="ipk050NetSid" />
<html:hidden property="returnCmd" />


<logic:notEmpty name="ipk070Form" property="ipk070SvSearchTarget">
<logic:iterate id="prm" name="ipk070Form" property="ipk070SvSearchTarget">
  <input type="hidden" name="ipk070SvSearchTarget" value="<bean:write name="prm" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="ipk070Form" property="deleteCheck">
<logic:iterate id="param" name="ipk070Form" property="deleteCheck">
  <input type="hidden" name="deleteCheck" value="<bean:write name="param" />">
</logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!-- BODY -->
<table width="100%">
<tr>
<td width="100%" align="center" cellpadding="5" cellspacing="0">
  <table width="85%" cellpadding="0" cellspacing="0">

  <tr>
  <td align="left">
    <table class="table_padding" cellpadding="0">
    <tr>
    <td width="0%"><img src="../ipkanri/images/header_ipkanri_icon_01.gif" border="0" alt="<gsmsg:write key="cmn.ipkanri" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.ipkanri" /></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="ipk.ipk070.1" /> ]</td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.ipkanri" />"></td>
    </tr>
    </table>
  </td>
  </tr>

  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" name="btn_back" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush2('ipk070Return');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>
  </td>
  </tr>

  <tr>
  <td>
  <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <html:errors />
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
  </td>
  </tr>

  <tr>
  <td>
    <table width="100%" class="table_padding">

    <tr>
    <td width="100%" height="30px" colspan="4" class="table_bg_7D91BD_search">
      <img src="../common/images/spacer.gif" width="1" height="20" align="left">
      <img src="../common/images/search_icon.gif" class="img_bottom" alt="<gsmsg:write key="cmn.advanced.search" />"><span class="text_tlw3"><gsmsg:write key="cmn.advanced.search" /></span>
    </td>
    </tr>

    <tr width="100%">
    <th width="20%" class="td_type3" nowrap><gsmsg:write key="ipk.1" /></th>
    <td class="td_type20_1" colspan="3">
    <html:select property="ipk070SltNet" styleClass="select01">
    <logic:notEmpty name="ipk070Form" property="ipk070NetNameLabel">
    <html:optionsCollection name="ipk070Form" property="ipk070NetNameLabel" value="value" label="label" />
    </logic:notEmpty>
    </html:select>
    </td>
    </tr>

    <tr width="100%">

    <th width="20%" class="td_type3" nowrap><gsmsg:write key="cmn.employer" /></th>
    <td width="100%" class="td_type20_1" colspan="3">
      <table width="100%">

      <tr>
      <td>
      <html:select property="ipk070SltGroup" styleClass="select01" onchange="buttonPush2('changeGroup');">
      <logic:notEmpty name="ipk070Form" property="ipk070GroupLabel">
      <html:optionsCollection name="ipk070Form" property="ipk070GroupLabel" value="value" label="label"/>
      </logic:notEmpty>
      </html:select>
      <input type="button" onclick="openGroupWindowForIpkanri(this.form.ipk070SltGroup, 'ipk070SltGroup', '0', 'changeGrp')" class="group_btn" value="&nbsp;&nbsp;" id="ipk070GroupBtn">
      </td>
      <td>
      <html:select property="ipk070SltUser" styleClass="select01">
      <logic:notEmpty name="ipk070Form" property="ipk070UserLabel">
      <html:optionsCollection name="ipk070Form" property="ipk070UserLabel" value="value" label="label" />
      </logic:notEmpty>
      </html:select>
      </td>
      </tr>

      </table>
    </td>
    </tr>

    <tr>
    <th width="20%" class="td_type3" nowrap><gsmsg:write key="cmn.keyword" /></th>
    <td class="td_type20_1" nowrap>
    <html:text name="ipk070Form" property="ipk070KeyWord" maxlength="100" style="width:213px;" />
    <div class="font_black4">
    <html:radio name="ipk070Form" property="ipk070KeyWordkbn" value="0" styleId="keyKbn_01" /><label for="keyKbn_01"><gsmsg:write key="cmn.contains.all" />(AND)</label>&nbsp;
    <html:radio name="ipk070Form" property="ipk070KeyWordkbn" value="1" styleId="keyKbn_02" /><label for="keyKbn_02"><gsmsg:write key="cmn.containing.either" />(OR)</label>
    </div>
    </td>
    <th width="20%" class="td_type3" nowrap><gsmsg:write key="cmn.search2" /></th>
    <td width="40%" class="td_type20_1" nowrap>
      <table>

      <tr>
      <td nowrap class="font_black4">
      <html:multibox styleId="search_scope_01" name="ipk070Form" property="ipk070SearchTarget" value="1" /><label for="search_scope_01"><gsmsg:write key="ipk.6" /></label>
      </td>
      </tr>

      <tr>
      <td nowrap class="font_black4">
      <html:multibox styleId="search_scope_02" name="ipk070Form" property="ipk070SearchTarget" value="2" /><label for="search_scope_02"><gsmsg:write key="ipk.7" /></label>
      </td>
      </tr>

      <tr>
      <td nowrap class="font_black4">
      <html:multibox styleId="search_scope_03" name="ipk070Form" property="ipk070SearchTarget" value="3" /><label for="search_scope_03"><gsmsg:write key="cmn.comment" /></label>
      </td>
      </tr>

      </table>
    </td>
    </tr>

    <tr>
    <th width="10%" class="td_type3" nowrap><gsmsg:write key="cmn.sort.order" /></th>
    <td width="100%" class="td_type20_1" colspan="3" nowrap>
    <span class="text_bb2"><gsmsg:write key="cmn.first.key" /></span>
    <html:select property="ipk070SearchSortKey1">
    <logic:notEmpty name="ipk070Form" property="ipk070SortKeyLabelList">
    <html:optionsCollection name="ipk070Form" property="ipk070SortKeyLabelList" value="value" label="label" />
    </logic:notEmpty>
    </html:select>

    <html:radio name="ipk070Form" property="ipk070SearchOrderKey1" value="0" styleId="sort1_up" /><label for="sort1_up"><gsmsg:write key="cmn.order.asc" /></label>
    <html:radio name="ipk070Form" property="ipk070SearchOrderKey1" value="1" styleId="sort1_dw" /><label for="sort1_dw"><gsmsg:write key="cmn.order.desc" /></label>
    &nbsp;&nbsp;&nbsp;&nbsp;
    <span class="text_bb2"><gsmsg:write key="cmn.second.key" /></span>
    <html:select property="ipk070SearchSortKey2">
    <logic:notEmpty name="ipk070Form" property="ipk070SortKeyLabelList">
    <html:optionsCollection name="ipk070Form" property="ipk070SortKeyLabelList" value="value" label="label" />
    </logic:notEmpty>
    </html:select>
    <html:radio name="ipk070Form" property="ipk070SearchOrderKey2" value="0" styleId="sort2_up" /><label for="sort2_up"><gsmsg:write key="cmn.order.asc" /></label>
    <html:radio name="ipk070Form" property="ipk070SearchOrderKey2" value="1" styleId="sort2_dw" /><label for="sort2_dw"><gsmsg:write key="cmn.order.desc" /></label>
    </td>
    </tr>

    </table>

  <div><img src="../common/images/spacer.gif" width="1" height="10" class="img_bottom"></div>
  <div align="center"><input type="button" value="<gsmsg:write key="cmn.search" />" class="btn_search_n1" onclick="buttonPush2('doSearch');"></div>

  </td>
  </tr>

  <tr>
  <td>
  <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
  </td>
  </tr>

  <tr>
  <td align="right">
    <logic:notEmpty name="ipk070Form" property="searchModelList">
    <logic:greaterThan name="ipk070Form" property="ipk070MaxPageNum" value="1">
    <img alt="<gsmsg:write key="cmn.previous.page" />" height="20" class="img_bottom" src="../ipkanri/images/arrow2_l.gif" width="20" border="0" onClick="buttonPush2('arrow_left');">
    <logic:notEmpty name="ipk070Form" property="ipk070PageLabel">
    <html:select property="ipk070Page1" onchange="ipk070ChangePage1();" styleClass="text_i">
    <html:optionsCollection name="ipk070Form" property="ipk070PageLabel" value="value" label="label" />
    </html:select>
    </logic:notEmpty>
    <img src="../ipkanri/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" border="0" onClick="buttonPush2('arrow_right');">
    </logic:greaterThan>
  </td>
  </tr>

  <tr>
  <td>
    <table cellspacing="0" cellpadding="0" align="center" class="table_kotei2">

    <tr class="tr_bg_7D91BD">
    <td class="td_typeip4" width="13%" align="center" rowspan="2" nowrap>
    <a href="javascript:void(0)" onclick="return clickSortTitle(4);" style="text-decoration: none;">
    <span class="text_tlw"><gsmsg:write key="ipk.19" /></span></a></td>

    <td class="td_typeip4" width="13%" align="center" rowspan="2" nowrap>
    <a href="javascript:void(0)" onclick="return clickSortTitle(5);" style="text-decoration: none;">
    <span class="text_tlw"><gsmsg:write key="ipk.20" /></span></a></td>

    <td class="td_typeip4" width="15%" align="center" rowspan="2" nowrap>
    <a href="javascript:void(0)" onclick="return clickSortTitle(0);" style="text-decoration: none;">
    <span class="text_tlw"><gsmsg:write key="ipk.6" /></span></a></td>

    <td class="td_typeip4" width="20%" align="center" rowspan="2" nowrap>
    <a href="javascript:void(0)" onclick="return clickSortTitle(1);" style="text-decoration: none;">
    <span class="text_tlw"><gsmsg:write key="ipk.7" /></span></a></td>

    <td class="td_typeip4" width="0%" align="center" rowspan="2" nowrap>
    <a href="javascript:void(0)" onclick="return clickSortTitle(2);" style="text-decoration: none;">
    <span class="text_tlw"><gsmsg:write key="ipk.21" /></span></a></td>

    <td class="td_typeip4" width="6%" align="center" rowspan="2" nowrap>
    <span class="text_tlw"><gsmsg:write key="cmn.employer" /></span></td>

    <td class="td_typeip4" width="11%" align="center" nowrap>
    <a href="javascript:void(0)" onclick="return clickSortTitle(6);" style="text-decoration: none;">
    <span class="text_tlw">CPU</span></a></td>

    <td class="td_typeip4" width="11%" align="center" nowrap>
    <a href="javascript:void(0)" onclick="return clickSortTitle(7);" style="text-decoration: none;">
    <span class="text_tlw"><gsmsg:write key="cmn.memory" /></span></a></td>

    <td class="td_typeip4" width="11%" align="center" nowrap>
    <a href="javascript:void(0)" onclick="return clickSortTitle(8);" style="text-decoration: none;">
    <span class="text_tlw">HD</span></a></td>

    <td class="td_typeip4" width="0%" align="center" rowspan="2" nowrap>
    <span class="text_tlw"><gsmsg:write key="cmn.detail" /></span></td>

    </tr>

    <tr class="table_bg_7D91BD">
    <td class="td_typeip4" width="100%" align="center" colspan="3" nowrap>
    <a href="javascript:void(0)" onclick="return clickSortTitle(3);" style="text-decoration: none;">
    <span class="text_tlw">&nbsp; <gsmsg:write key="cmn.comment" /> &nbsp;</span></a></td>
    </tr>

    <logic:iterate id="searchModel" name="ipk070Form" property="searchModelList" indexId="idx">
    <bean:define id="backclass" value="td_type20_" />
    <bean:define id="backpat" value="<%= String.valueOf((idx.intValue() % 2) + 1) %>" />
    <bean:define id="back" value="<%= String.valueOf(backclass) + String.valueOf(backpat) %>" />
    <tr>
    <td class="<%= String.valueOf(back) %>" rowspan="2"><bean:write name="searchModel" property="netad" /></td>
    <td class="<%= String.valueOf(back) %>" rowspan="2"><bean:write name="searchModel" property="subnet" /></td>
    <td class="<%= String.valueOf(back) %>" rowspan="2"><bean:write name="searchModel" property="ipad" /></td>
    <td class="<%= String.valueOf(back) %>" rowspan="2"><bean:write name="searchModel" property="iadName" /></td>
    <td class="<%= String.valueOf(back) %>" nowrap align="center" rowspan="2">
    <logic:equal name="searchModel" property="useKbn" value="0"><span class="font_ff5500"> <gsmsg:write key="cmn.unused" /> </span></logic:equal>
    <logic:equal name="searchModel" property="useKbn" value="1"> <gsmsg:write key="cmn.in.use" /> </logic:equal>
    </td>
    <td class="<%= String.valueOf(back) %>" nowrap rowspan="2">

    <logic:notEmpty name="searchModel" property="iadAdmList">
      <logic:iterate id="addAdm" name="searchModel" property="iadAdmList">
        <logic:equal name="addAdm" property="usrJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_TOROKU) %>">
          <bean:write name="addAdm" property="usiSei" />&nbsp;<bean:write name="addAdm" property="usiMei" /><br>
        </logic:equal>
        <logic:equal name="addAdm" property="usrJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
          <strike><bean:write name="addAdm" property="usiSei" />&nbsp;<bean:write name="addAdm" property="usiMei" /></strike><br>
        </logic:equal>
      </logic:iterate>
    </logic:notEmpty>

    </td>
    <td class="<%= String.valueOf(back) %>" nowrap><bean:write name="searchModel" property="cpuName" /></td>
    <td class="<%= String.valueOf(back) %>" nowrap><bean:write name="searchModel" property="memoryName" /></td>
    <td class="<%= String.valueOf(back) %>" nowrap><bean:write name="searchModel" property="hdName" /></td>
    <td class="<%= String.valueOf(back) %>" rowspan="2">
    <input type="button" class="btn_base1s" name="detail" value="<gsmsg:write key="cmn.detail" />"
      onClick="ipk070ButtonPush('ipk070detail', '<bean:write name="searchModel" property="netSid" />', '<bean:write name="searchModel" property="iadSid" />', '1');">
    </td>
    </tr>

    <tr>
    <td class="<%= String.valueOf(back) %>" colspan="3" nowrap><bean:write name="searchModel" property="iadMsg" /></td>
    </tr>

    </logic:iterate>

    <tr>
    <td colspan="10" align="right">
    <logic:greaterThan name="ipk070Form" property="ipk070MaxPageNum" value="1">
    <img alt="<gsmsg:write key="cmn.previous.page" />" height="20" src="../ipkanri/images/arrow2_l.gif" class="img_bottom" width="20" border="0" onClick="buttonPush2('arrow_left');">
    <logic:notEmpty name="ipk070Form" property="ipk070PageLabel">
    <html:select property="ipk070Page2" onchange="ipk070ChangePage2();" styleClass="text_i">
    <html:optionsCollection name="ipk070Form" property="ipk070PageLabel" value="value" label="label" />
    </html:select>
    </logic:notEmpty>
    <img src="../ipkanri/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" border="0" onClick="buttonPush2('arrow_right');">
    </logic:greaterThan>
    </td>
    </tr>
    </table>
    </logic:notEmpty>
  </td>
  </tr>

  <tr>
  <td>
    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
    <td width="100%" align="right">
    <input type="button" name="btn_back" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush2('ipk070Return');">
    </td>
    </tr>
    </table>
  </td>
  </tr>

  </table>
</td>
</tr>

</table>
</html:form>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>