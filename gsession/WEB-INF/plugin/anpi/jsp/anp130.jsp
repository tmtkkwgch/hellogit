<%@page import="jp.groupsession.v2.cmn.GSConstCommon"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html;" charset="Shift_JIS">
<title>[Group Session] <gsmsg:write key="cmn.admin.setting.menu" /></title>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<script type="text/javascript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../anpi/js/anp130.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>

</head>
<body class="body_03">
<html:form action="/anpi/anp130">
<!-- BODY -->
<input type="hidden" name="CMD">

<html:hidden property="backScreen" />
<html:hidden property="anp130SelectAphSid" />
<html:hidden property="anp130NowPage" />
<html:hidden property="anp130DspPage1" />
<html:hidden property="anp130DspPage2" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>


<table align="center" cellpadding="0" cellspacing="5" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>

    <!-- タイトル -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="anp.plugin"/> <gsmsg:write key="anp.anp070.08"/> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
            <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('anp130delete');">
            <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('anp130back');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
        </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
    <!-- エラーメッセージ -->
    <div style="text-align:left">
    <html:errors/>
    </div>

    <img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt="">
  </td>
  </tr>

  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
        <td>
        <table cellspacing="5" width="100%" border="0">
        <tr>
            <!-- ページコンボ -->
            <td width="0%" align="right" nowrap>
            <bean:size id="pageCount" name="anp130Form" property="anp130PageLabel" scope="request" />
            <logic:greaterThan name="pageCount" value="1">
                <img alt="<gsmsg:write key="cmn.previous.page" />" src="../common/images/arrow2_l.gif" class="text_i" width="20" height="20" border="0" onClick="buttonPush('anp130pageLast');">
                <html:select property="anp130DspPage1" onchange="changePage(this);" styleClass="text_i">
                <html:optionsCollection name="anp130Form" property="anp130PageLabel" value="value" label="label"/>
                </html:select>
                <img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="text_i" width="20" height="20" border="0" onClick="buttonPush('anp130pageNext');">
            </logic:greaterThan>
            </td>
        </tr>
        </table>

        <table class="tl0 table_td_border" width="100%" cellpadding="0" cellspacing="0">

          <tr class="table_bg_7D91BD">
          <!-- ヘッダ部分 -->
          <th width="0%"  nowrap><html:checkbox name="anp130Form" property="anp130allCheck" value="1" onclick="changeCheckList();" /></th>
          <th width="35%" nowrap><span class="text_tlw"><gsmsg:write key="cmn.subject"/></span></th>
          <th width="10%" nowrap><span class="text_tlw"><gsmsg:write key="anp.knmode"/></span></th>
          <th width="15%" nowrap><span class="text_tlw"><gsmsg:write key="anp.sender"/></span></th>
          <th width="0%"  nowrap><span class="text_tlw"><gsmsg:write key="anp.date.send"/></span></th>
          <th width="0%"  nowrap><span class="text_tlw"><gsmsg:write key="anp.date.finish"/></span></th>
          </tr>

          <logic:notEmpty name="anp130Form" property="anp130HaisinList">
          <logic:iterate id="haisin" name="anp130Form" property="anp130HaisinList" indexId="idx">
              <% String[] typeClass = new String[] {"td_type1", "td_type_usr"}; %>
              <% String tdType = typeClass[(idx.intValue() % 2)]; %>
              <tr class="<%= tdType %>">
                <!-- 削除チェック -->
                <td align="center"><html:multibox name="anp130Form" property="anp130DelSidList">
                   <bean:write name="haisin" property="anpiSid" /></html:multibox></td>
                <!-- 件名 -->
                <td align="left"   nowrap><a href="#" onclick="selectData(<bean:write name="haisin" property="anpiSid" />);">
                    <bean:write name="haisin" property="subject" /></a></td>
                <!-- 訓練モード -->
                <td align="center"   nowrap>
                    <logic:equal name="haisin" property="knrenFlg" value="1">
                    ○
                    </logic:equal>
                </td>
                <!-- 氏名 -->
                <td align="left"   nowrap>
                    <logic:equal name="haisin" property="jyotaiKbn" value="<%= String.valueOf(GSConst.JTKBN_TOROKU) %>">
                    <bean:write name="haisin" property="name" />
                    </logic:equal>
                    <logic:notEqual name="haisin" property="jyotaiKbn" value="<%= String.valueOf(GSConst.JTKBN_TOROKU) %>">
                    <del><bean:write name="haisin" property="name" /></del>
                    </logic:notEqual>
                </td>
                <!-- 配信日時 -->
                <td align="center" nowrap><bean:write name="haisin" property="haisinDate" /></td>
                <!-- 完了日時 -->
                <td align="center" nowrap><bean:write name="haisin" property="kanryoDate" /></td>
              </tr>
          </logic:iterate>
          </logic:notEmpty>

        </table>

        <table width="100%" cellpadding="5" cellspacing="0">
            <tr>
            <!-- ページコンボ -->
            <td align="right">
            <bean:size id="pageCount" name="anp130Form" property="anp130PageLabel" scope="request" />
            <logic:greaterThan name="pageCount" value="1">
                <img alt="<gsmsg:write key="cmn.previous.page" />" src="../common/images/arrow2_l.gif" class="text_i" width="20" height="20" border="0" onClick="buttonPush('anp130pageLast');">
                <html:select property="anp130DspPage2" onchange="changePage(this);" styleClass="text_i">
                <html:optionsCollection name="anp130Form" property="anp130PageLabel" value="value" label="label"/>
                </html:select>
                <img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="text_i" width="20" height="20" border="0" onClick="buttonPush('anp130pageNext');">
            </logic:greaterThan>
            </tr>
        </table>
        </td>
        </tr>

    </table>
    </td>
    </tr>

	  <tr>
	  <td>

	   <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

	    <table width="100%">
	        <tr>
	        <td width="100%" align="right" cellpadding="5" cellspacing="0">
	            <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('anp130delete');">
                <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('anp130back');">
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