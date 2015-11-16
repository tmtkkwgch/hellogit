<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="bbs.bbs050.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../bulletin/js/bbs050.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../bulletin/css/bulletin.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">

<html:form action="/bulletin/bbs050">
<input type="hidden" name="CMD" value="">
<html:hidden name="bbs050Form" property="backScreen" />
<html:hidden name="bbs050Form" property="s_key" />
<html:hidden name="bbs050Form" property="bbs010page1" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!-- BODY -->
<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="bbs.bbs050.21" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="<gsmsg:write key="cmn.setting" />" class="btn_setting_n1" onClick="buttonPush('bbsPsConf');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backBBSList');">
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" class="tl0" border="0" cellpadding="5">

      <tr>
      <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="bbs.bbs050.2" /></span></td>
      <td align="left" class="td_type20" width="100%">

        <table width="100%" cellpadding="0" cellspacing="0" border="0">
          <tr>
          <td align="left" width="100%">
            <html:select property="bbs050forumCnt">
              <html:optionsCollection name="bbs050Form" property="bbs050forumCntLabel" value="value" label="label" />
            </html:select>
            <span class="text_base6"><gsmsg:write key="cmn.number" /></span>
            <br><span class="text_base7"><gsmsg:write key="bbs.bbs050.3" /></span>
          </td>
          </tr>
        </table>

      </td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="bbs.bbs050.4" /></span></td>
      <td align="left" class="td_type20" width="100%">

        <table width="100%" cellpadding="0" cellspacing="0" border="0">
          <tr>
          <td align="left" width="100%">
            <html:select property="bbs050threCnt">
              <html:optionsCollection name="bbs050Form" property="bbs050threCntLabel" value="value" label="label" />
            </html:select>
            <span class="text_base6"><gsmsg:write key="cmn.number" /></span>
            <br><span class="text_base7"><gsmsg:write key="bbs.bbs050.5" /></span>
          </td>
          </tr>
        </table>

      </td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="bbs.bbs050.6" /></span></td>
      <td align="left" class="td_type20" width="100%">

        <table width="100%" cellpadding="0" cellspacing="0" border="0">
          <tr>
          <td align="left" width="100%">
            <html:select property="bbs050writeCnt">
              <html:optionsCollection name="bbs050Form" property="bbs050writeCntLabel" value="value" label="label" />
            </html:select>
            <span class="text_base6"><gsmsg:write key="cmn.number" /></span>
            <br><span class="text_base7"><gsmsg:write key="bbs.bbs050.7" /></span>
          </td>
          </tr>
        </table>

      </td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="bbs.bbs050.8" /></span></td>
      <td align="left" class="td_type20" width="100%">

        <table width="100%" cellpadding="0" cellspacing="0" border="0">
          <tr>
          <td align="left" width="100%">
            <html:select property="bbs050newCnt">
              <html:optionsCollection name="bbs050Form" property="bbs050newCntLabel" value="value" label="label" />
            </html:select>
            <br>
            <div class="text_base7">
              <gsmsg:write key="bbs.bbs050.9" /><br>
              <gsmsg:write key="bbs.bbs050.11" />
            </div>
          </td>
          </tr>
        </table>

      </td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="bbs.bbs050.17" /></span></td>
      <td align="left" class="td_type20" width="100%">

        <table width="100%" cellpadding="0" cellspacing="0" border="0">
        <tr>
        <td align="left" width="100%">
          <html:radio name="bbs050Form" styleId="bbs050wrtOrder_01" property="bbs050wrtOrder" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_WRTLIST_ORDER_ASC) %>" /><label for="bbs050wrtOrder_01"><span class="text_base6"><gsmsg:write key="cmn.order.asc" /></span></label>&nbsp;
          <html:radio name="bbs050Form" styleId="bbs050wrtOrder_02" property="bbs050wrtOrder" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_WRTLIST_ORDER_DESC) %>" /><label for="bbs050wrtOrder_02"><span class="text_base6"><gsmsg:write key="cmn.order.desc" /></span></label>
          <div class="text_base7">
          <gsmsg:write key="bbs.bbs050.18" />
          </div>
        </td>
        </tr>
        </table>

      </td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="bbs.bbs050.19" /></span></td>
      <td align="left" class="td_type20" width="100%">

        <table width="100%" cellpadding="0" cellspacing="0" border="0">
        <tr>
        <td align="left" width="100%">
          <html:radio name="bbs050Form" styleId="bbs050threImage_01" property="bbs050threImage" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_IMAGE_DSP) %>" /><label for="bbs050threImage_01"><span class="text_base6"><gsmsg:write key="cmn.show" /></span></label>&nbsp;
          <html:radio name="bbs050Form" styleId="bbs050threImage_02" property="bbs050threImage" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_IMAGE_NOT_DSP) %>" /><label for="bbs050threImage_02"><span class="text_base6"><gsmsg:write key="cmn.hide" /></span></label>
          <div class="text_base7">
          <gsmsg:write key="bbs.bbs050.20" />
          </div>
        </td>
        </tr>
        </table>

      </td>
      </tr>
      <!-- 添付ファイル -->
      <tr>
      <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="bbs.bbs050.26" /></span></td>
      <td align="left" class="td_type20" width="100%">

        <table width="100%" cellpadding="0" cellspacing="0" border="0">
        <tr>
        <td align="left" width="100%">
          <html:radio name="bbs050Form" styleId="bbs050tempImage_01" property="bbs050tempImage" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_IMAGE_TEMP_DSP) %>" /><label for="bbs050tempImage_01"><span class="text_base6"><gsmsg:write key="cmn.show" /></span></label>&nbsp;
          <html:radio name="bbs050Form" styleId="bbs050tempImage_02" property="bbs050tempImage" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_IMAGE_TEMP_NOT_DSP) %>" /><label for="bbs050tempImage_02"><span class="text_base6"><gsmsg:write key="cmn.hide" /></span></label>
          <div class="text_base7">
          <gsmsg:write key="bbs.bbs050.27" />
          </div>
        </td>
        </tr>
        </table>

      </td>
      </tr>

      <!-- [サブコンテンツ] 新着スレッド一覧表示設定 -->
      <tr>
      <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="bbs.bbs050.25" /><br><gsmsg:write key="bbs.bbs010.2" /></span></td>
      <td align="left" class="td_type20" width="100%">

        <table width="100%" cellpadding="0" cellspacing="0" border="0">
        <tr>
        <td align="left" width="100%">
          <html:radio name="bbs050Form" styleId="bbs050threadFlg_01" property="bbs050threadFlg" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_THRED_DSP) %>" /><label for="bbs050threadFlg_01"><span class="text_base6"><gsmsg:write key="cmn.show" /></span></label>&nbsp;
          <html:radio name="bbs050Form" styleId="bbs050threadFlg_02" property="bbs050threadFlg" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_THRED_NOT_DSP) %>" /><label for="bbs050threadFlg_02"><span class="text_base6"><gsmsg:write key="cmn.hide" /></span></label>
          <div class="text_base7">
          <gsmsg:write key="bbs.bbs050.22" />
          </div>
        </td>
        </tr>
        </table>

      </td>
      </tr>

      <!-- [サブコンテンツ] フォーラム一覧表示設定 -->
      <tr>
      <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="bbs.bbs050.25" /><br><gsmsg:write key="bbs.1" /></span></td>
      <td align="left" class="td_type20" width="100%">

        <table width="100%" cellpadding="0" cellspacing="0" border="0">
        <tr>
        <td align="left" width="100%">
          <html:radio name="bbs050Form" styleId="bbs050forumFlg_01" property="bbs050forumFlg" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_FORUM_DSP) %>" /><label for="bbs050forumFlg_01"><span class="text_base6"><gsmsg:write key="cmn.show" /></span></label>&nbsp;
          <html:radio name="bbs050Form" styleId="bbs050forumFlg_02" property="bbs050forumFlg" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_FORUM_NOT_DSP) %>" /><label for="bbs050forumFlg_02"><span class="text_base6"><gsmsg:write key="cmn.hide" /></span></label>
          <div class="text_base7">
          <gsmsg:write key="bbs.bbs050.23" />
          </div>
        </td>
        </tr>
        </table>

      </td>
      </tr>

      <!-- [サブコンテンツ] 未読スレッド一覧表示設定 -->
      <tr>
      <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="bbs.bbs050.25" /><br><gsmsg:write key="bbs.10" /></span></td>
      <td align="left" class="td_type20" width="100%">

        <table width="100%" cellpadding="0" cellspacing="0" border="0">
        <tr>
        <td align="left" width="100%">
          <html:radio name="bbs050Form" styleId="bbs050midokuTrdFlg_01" property="bbs050midokuTrdFlg" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_MIDOKU_TRD_DSP) %>" /><label for="bbs050midokuTrdFlg_01"><span class="text_base6"><gsmsg:write key="cmn.show" /></span></label>&nbsp;
          <html:radio name="bbs050Form" styleId="bbs050midokuTrdFlg_02" property="bbs050midokuTrdFlg" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_MIDOKU_TRD_NOT_DSP) %>" /><label for="bbs050midokuTrdFlg_02"><span class="text_base6"><gsmsg:write key="cmn.hide" /></span></label>
          <div class="text_base7">
          <gsmsg:write key="bbs.bbs050.24" />
          </div>
        </td>
        </tr>
        </table>

      </td>
      </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td align="right">
          <input type="button" value="<gsmsg:write key="cmn.setting" />" class="btn_setting_n1" onClick="buttonPush('bbsPsConf');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backBBSList');">
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