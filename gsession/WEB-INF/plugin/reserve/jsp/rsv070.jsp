<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ page import="jp.groupsession.v2.rsv.GSConstReserve" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.reserve" /> [ <gsmsg:write key="reserve.rsv070.1" /> ]</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../reserve/css/reserve.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<script type="text/javascript">
function initPictureRsv() {
    jQuery(".img_hoge").each(function(){
      if ($(".img_hoge").width() > 130) {
        $(".img_hoge").width(130);
      }
    });
}
</script>

</head>

<body class="body_03" onload="initPictureRsv();">
<html:form action="/reserve/rsv070">
<input type="hidden" name="CMD" value="">

<table width="100%">
<tr>
<td width="100%" align="center">

  <table width="100%" class="tl0">
  <tr>
  <td align="left">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../reserve/images/header_reserve_01.gif" border="0" alt="<gsmsg:write key="cmn.reserve" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.reserve" /></span></td>
    <td width="100%" class="header_white_bg_text" nowrap>
      <table width="100%">
      <tr>
      <td align="left" class="text_header">[ <gsmsg:write key="reserve.rsv070.1" /> ]</td>
      <td align="right"><input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_close_n1" onclick="window.close();"></td>
      </tr>
      </table>
    </td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>
  </td>
  </tr>
  <tr><td>
    <img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt="">
  </td></tr>

  <tr>
  <td>
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.facility.group" /></span></td>
    <td align="left" class="td_wt" width="80%">
      <span class="text_base"><bean:write name="rsv070Form" property="rsv070GrpName" /></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="reserve.47" /></span></td>
    <td align="left" class="td_wt"><span class="text_base"><bean:write name="rsv070Form" property="rsv070SisetuKbnName" /></span></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="reserve.55" /></span></td>
    <td align="left" class="td_wt"><span class="text_base"><bean:write name="rsv070Form" property="rsv070SisetuId" /></span></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.facility.name" /></span></td>
    <td align="left" class="td_wt"><span class="text_base"><bean:write name="rsv070Form" property="rsv070SisetuName" /></span></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.asset.register.num" /></span></td>
    <td align="left" class="td_wt"><span class="text_base"><bean:write name="rsv070Form" property="rsv070SisanKanri" /></span></td>
    </tr>

    <logic:notEmpty name="rsv070Form" property="rsv070PropHeaderName4">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv070Form" property="rsv070PropHeaderName4" /></span></td>
    <td align="left" class="td_type1"><span class="text_base_rsv"><bean:write name="rsv070Form" property="rsv070Prop4Value" /></span></td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv070Form" property="rsv070PropHeaderName5">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv070Form" property="rsv070PropHeaderName5" /></span></td>
    <td align="left" class="td_type1"><span class="text_base_rsv"><bean:write name="rsv070Form" property="rsv070Prop5Value" /></span></td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv070Form" property="rsv070PropHeaderName1">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv070Form" property="rsv070PropHeaderName1" /></span></td>
    <td align="left" class="td_type1"><span class="text_base_rsv"><bean:write name="rsv070Form" property="rsv070Prop1Value" /></span></td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv070Form" property="rsv070PropHeaderName2">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv070Form" property="rsv070PropHeaderName2" /></span></td>
    <td align="left" class="td_type1">
      <span class="text_base_rsv">
        <logic:equal name="rsv070Form" property="rsv070Prop2Value" value="<%= String.valueOf(GSConstReserve.PROP_KBN_KA) %>"><gsmsg:write key="cmn.accepted" /></logic:equal>
        <logic:equal name="rsv070Form" property="rsv070Prop2Value" value="<%= String.valueOf(GSConstReserve.PROP_KBN_HUKA) %>"><gsmsg:write key="cmn.not" /></logic:equal>
      </span>
    </td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv070Form" property="rsv070PropHeaderName3">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv070Form" property="rsv070PropHeaderName3" /></span></td>
    <td align="left" class="td_type1">
      <span class="text_base_rsv">
        <logic:equal name="rsv070Form" property="rsv070Prop3Value" value="<%= String.valueOf(GSConstReserve.PROP_KBN_KA) %>"><gsmsg:write key="cmn.accepted" /></logic:equal>
        <logic:equal name="rsv070Form" property="rsv070Prop3Value" value="<%= String.valueOf(GSConstReserve.PROP_KBN_HUKA) %>"><gsmsg:write key="cmn.not" /></logic:equal>
      </span>
    </td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv070Form" property="rsv070PropHeaderName7">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv070Form" property="rsv070PropHeaderName7" /></span></td>
    <td align="left" class="td_type1">
      <span class="text_base_rsv">
        <logic:equal name="rsv070Form" property="rsv070Prop7Value" value="<%= String.valueOf(GSConstReserve.PROP_KBN_KA) %>"><gsmsg:write key="cmn.accepted" /></logic:equal>
        <logic:equal name="rsv070Form" property="rsv070Prop7Value" value="<%= String.valueOf(GSConstReserve.PROP_KBN_HUKA) %>"><gsmsg:write key="cmn.not" /></logic:equal>
      </span>
    </td>
    </tr>
    </logic:notEmpty>

    <logic:equal name="rsv070Form" property="rsv070apprDspFlg" value="true">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="reserve.appr.set.title" /></span></td>
    <td align="left" class="td_type1">
      <span class="text_base_rsv">
        <logic:equal name="rsv070Form" property="rsv070apprKbnFlg" value="true"><gsmsg:write key="reserve.appr.set.kbn1" /></logic:equal>
        <logic:notEqual name="rsv070Form" property="rsv070apprKbnFlg" value="true"><gsmsg:write key="reserve.appr.set.kbn2" /></logic:notEqual>
      </span>
    </td>
    </tr>
    </logic:equal>

    <logic:notEmpty name="rsv070Form" property="rsv070PropHeaderName6">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv070Form" property="rsv070PropHeaderName6" /></span></td>
    <td align="left" class="td_type1">
      <span class="text_base_rsv">
      <logic:notEmpty name="rsv070Form" property="rsv070Prop6Value">
        <bean:write name="rsv070Form" property="rsv070Prop6Value" /><gsmsg:write key="cmn.days.after" />
      </logic:notEmpty>
      </span>
    </td>
    </tr>
    </logic:notEmpty>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.memo" /></span></td>
    <td align="left" class="td_wt"><span class="text_base">
      <bean:write name="rsv070Form" property="rsv070Biko" filter="false" /></span></td>
    </tr>

    <logic:notEmpty name="rsv070Form" property="rsv070SisetuBinDataList" scope="request">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="reserve.59" /></span></td>
    <td align="center" class="td_wt">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
      <logic:equal name="rsv070Form" property="rsv070AdmFlg" value="true">
      <td width="40%" nowrap class="td_type_rsv_pop" align="center">
        <span class="text_base_rsv3"><gsmsg:write key="cmn.image" /></span>
      </td>
      <td width="60%" nowrap class="td_type_rsv_pop" align="center">
        <span class="text_base_rsv3"><gsmsg:write key="fil.9" /></span>
      </td>
      </logic:equal>
      <logic:equal name="rsv070Form" property="rsv070AdmFlg" value="false">
      <td width="100%" nowrap class="td_type_rsv_pop" align="center">
        <span class="text_base_rsv3"><gsmsg:write key="cmn.image" /></span>
      </td>
      </logic:equal>

      </tr>
      <logic:iterate id="sisetuBinMdl" name="rsv070Form" property="rsv070SisetuBinDataList" scope="request">
      <tr>
      <td class="td_type1" nowrap align="center">
        <img src="../reserve/rsv010.do?CMD=getImageFileSisetu&rsvSelectedSisetuSid=<bean:write name="rsv070Form" property="rsv070RsdSid" />&rsv010BinSid=<bean:write name="sisetuBinMdl" property="binSid" />" alt="<gsmsg:write key="reserve.17" />" border="1" class="img_hoge">
      </td>
      <logic:equal name="rsv070Form" property="rsv070AdmFlg" value="true">
      <td class="td_type1" nowrap align="center">
        <span class="text_base2"><bean:write name="sisetuBinMdl" property="binFileName" /></span>
      </td>
      </logic:equal>
      </tr>
      </logic:iterate>
    </table>
    </td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv070Form" property="rsv070PlaceBinDataList" scope="request">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="reserve.location.comments" /></span></td>
    <td align="left" class="td_wt"><span class="text_base">
      <bean:write name="rsv070Form" property="rsv070PlaComment" /></span></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="reserve.60" /></span></td>
    <td align="center" class="td_wt">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
      <td width="30%" nowrap class="td_type_rsv_pop" align="center">
        <span class="text_base_rsv3"><gsmsg:write key="cmn.image" /></span>
      </td>
      <logic:equal name="rsv070Form" property="rsv070AdmFlg" value="true">
      <td width="20%" nowrap class="td_type_rsv_pop" align="center">
        <span class="text_base_rsv3"><gsmsg:write key="fil.9" /></span>
      </td>
      </logic:equal>
      <logic:equal name="rsv070Form" property="rsv070AdmFlg" value="true">
      <td width="50%" nowrap class="td_type_rsv_pop" align="center">
      </logic:equal>
      <logic:equal name="rsv070Form" property="rsv070AdmFlg" value="false">
      <td width="70%" nowrap class="td_type_rsv_pop" align="center">
      </logic:equal>
        <span class="text_base_rsv3"><gsmsg:write key="cmn.comment" /></span>
      </td>
      </tr>
      <logic:iterate id="placeBinMdl" name="rsv070Form" property="rsv070PlaceBinDataList" scope="request" indexId="idx">
      <tr>
      <td class="td_type1" nowrap align="center">
      <a href="../reserve/rsv010.do?CMD=getImageFileSisetu&rsvSelectedSisetuSid=<bean:write name="rsv070Form" property="rsv070RsdSid" />&rsv010BinSid=<bean:write name="placeBinMdl" property="binSid" />" target="_blank">
        <img src="../reserve/rsv010.do?CMD=getImageFileSisetu&rsvSelectedSisetuSid=<bean:write name="rsv070Form" property="rsv070RsdSid" />&rsv010BinSid=<bean:write name="placeBinMdl" property="binSid" />" alt="<gsmsg:write key="reserve.17" />" border="1" class="img_hoge">
      </a>
      </td>
      <logic:equal name="rsv070Form" property="rsv070AdmFlg" value="true">
      <td class="td_type1" nowrap align="center">
      <a href="../reserve/rsv010.do?CMD=getImageFileSisetu&rsvSelectedSisetuSid=<bean:write name="rsv070Form" property="rsv070RsdSid" />&rsv010BinSid=<bean:write name="placeBinMdl" property="binSid" />" target="_blank">
        <span class="text_base2"><bean:write name="placeBinMdl" property="binFileName" /></span>
      </a>
      </td>
      </logic:equal>
      <td class="td_type1" nowrap align="center">
      <% if (idx.intValue() == 0) { %>
        <span class="text_base2"><bean:write name="rsv070Form" property="rsv070PlaceFileComment1" /></span>
      <% } else if (idx.intValue() == 1) { %>
        <span class="text_base2"><bean:write name="rsv070Form" property="rsv070PlaceFileComment2" /></span>
      <% } else if (idx.intValue() == 2) { %>
        <span class="text_base2"><bean:write name="rsv070Form" property="rsv070PlaceFileComment3" /></span>
      <% } else if (idx.intValue() == 3) { %>
        <span class="text_base2"><bean:write name="rsv070Form" property="rsv070PlaceFileComment4" /></span>
      <% } else if (idx.intValue() == 4) { %>
        <span class="text_base2"><bean:write name="rsv070Form" property="rsv070PlaceFileComment5" /></span>
      <% } else if (idx.intValue() == 5) { %>
        <span class="text_base2"><bean:write name="rsv070Form" property="rsv070PlaceFileComment6" /></span>
      <% } else if (idx.intValue() == 6) { %>
        <span class="text_base2"><bean:write name="rsv070Form" property="rsv070PlaceFileComment7" /></span>
      <% } else if (idx.intValue() == 7) { %>
        <span class="text_base2"><bean:write name="rsv070Form" property="rsv070PlaceFileComment8" /></span>
      <% } else if (idx.intValue() == 8) { %>
        <span class="text_base2"><bean:write name="rsv070Form" property="rsv070PlaceFileComment9" /></span>
      <% } else if (idx.intValue() == 9) { %>
        <span class="text_base2"><bean:write name="rsv070Form" property="rsv070PlaceFileComment10" /></span>
      <% } %>
      </td>
      </tr>
      </logic:iterate>
    </table>
    </td>
    </tr>
    </logic:notEmpty>

    </table>

  </td>
  </tr>

  <logic:notEmpty name="rsv070Form" property="rsv070RsvList">
  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">
  </td>
  </tr>
  <bean:size id="row" name="rsv070Form" property="rsv070RsvList"/>
  <tr>
  <td>
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td class="table_bg_A5B4E1" width="20%" rowspan="<%= row %>" nowrap><span class="text_bb1"><gsmsg:write key="reserve.rsv070.2" /></span></td>

    <logic:iterate id="rsvStr" name="rsv070Form" property="rsv070RsvList" indexId="cnt">
    <logic:greaterThan name="cnt" value="0">
    <tr>
    </logic:greaterThan>
    <td align="left" class="td_wt" width="80%">
      <span class="text_base"><bean:write name="rsvStr" /></span>
    </td>
    </logic:iterate>

    </tr>
    </table>
  </td>
  </tr>
  </logic:notEmpty>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">
  </td>
  </tr>
  </table>

</td>
</tr>
</table>

</html:form>
</body>
</html:html>