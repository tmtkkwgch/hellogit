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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[GroupSession] <gsmsg:write key="cmn.setting.main.view2" /></title>
</head>

<body class="body_03">
<html:form action="/common/cmn150">
<input type="hidden" name="CMD" value="">

<logic:notEmpty name="cmn150Form" property="cmn150Dsp4Area">
<logic:iterate id="areaId" name="cmn150Form" property="cmn150Dsp4Area">
<input type="hidden" name="cmn150Dsp4Area" value="<bean:write name="areaId" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="cmn150backPage" />


<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!-- BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">
  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.setting.main.view2" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" class="btn_ok1" value="OK" onClick="return buttonPush('cmn150kakunin');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('cmn150back');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <logic:messagesPresent message="false"><html:errors /></logic:messagesPresent>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="5" cellspacing="0" border="0" width="100%" class="tl_u2">
    <!-- 時計表示 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.cmn150.1" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%" colspan="5">
      <html:radio name="cmn150Form" styleId="cmn150Dsp1_01" property="cmn150Dsp1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstCommon.MAIN_DSP) %>" /><label for="cmn150Dsp1_01"><span class="text_base"><gsmsg:write key="cmn.show" /></span></label>&nbsp;
      <html:radio name="cmn150Form" styleId="cmn150Dsp1_02" property="cmn150Dsp1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstCommon.MAIN_NOT_DSP) %>" /><label for="cmn150Dsp1_02"><span class="text_base"><gsmsg:write key="cmn.hide" />&nbsp;</span></label>
    </td>
    </tr>
    <!-- 最終ログイン時間 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="user.usr090.2" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%" colspan="5">
      <html:radio name="cmn150Form" styleId="cmn150Dsp2_01" property="cmn150Dsp2" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstCommon.MAIN_DSP) %>" /><label for="cmn150Dsp2_01"><span class="text_base"><gsmsg:write key="cmn.show" /></span></label>&nbsp;
      <html:radio name="cmn150Form" styleId="cmn150Dsp2_02" property="cmn150Dsp2" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstCommon.MAIN_NOT_DSP) %>" /><label for="cmn150Dsp2_02"><span class="text_base"><gsmsg:write key="cmn.hide" />&nbsp;</span></label>
    </td>
    </tr>
    <!-- 自動リロード時間 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.auto.reload.time" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%" colspan="5">
      <html:select name="cmn150Form" property="cmn150Dsp3">
        <html:optionsCollection name="cmn150Form" property="cmn150DspLabelList" value="value" label="label" />
      </html:select>
    </td>
    </tr>

    <logic:equal name="cmn150Form" property="cmn150MainStatus" value="0">

      <!-- 天気予報 -->
      <tr>
      <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
        <span class="text_bb1"><gsmsg:write key="main.src.man001.1" /></span><span class="text_r2">※</span>
      </td>
      <td valign="middle" align="left" class="td_wt" width="100%" colspan="5">
        <html:radio name="cmn150Form" styleId="cmn150Dsp4_01" property="cmn150Dsp4" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstCommon.MAIN_DSP) %>" /><label for="cmn150Dsp4_01"><span class="text_base"><gsmsg:write key="cmn.show" /></span></label>&nbsp;
        <html:radio name="cmn150Form" styleId="cmn150Dsp4_02" property="cmn150Dsp4" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstCommon.MAIN_NOT_DSP) %>" /><label for="cmn150Dsp4_02"><span class="text_base"><gsmsg:write key="cmn.hide" />&nbsp;</span></label>
      </td>
      </tr>

      <tr>
      <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.cmn150.2" /></span><span class="text_r2">※</span>
      </td>
      <td valign="middle" align="left" class="td_wt" width="100%" colspan="5">
        <table width="0%" border="0">
        <tr>
          <td width="40%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="cmn.cmn150.3" /></span></td>
          <td width="20%" align="center">&nbsp;</td>
          <td class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="cmn.cmn150.4" /></span></td>
        </tr>

        <tr>
          <td align="center" valign="top">
            <table cellspacing="0" class="tl0" height="100%">
              <tr>
              <td valign="top" class="table_bg_A5B4E1" height="100%">
                <table height="100%">
                <tr>
                  <td height="50%" valign="top"><a href="javascript:void(0)" onClick="return buttonPush('upArea');"><img src="../common/images/arrow2_t.gif" alt="<gsmsg:write key="cmn.up" />" border="0"></a></td>
                </tr>
                <tr>
                  <td height="50%" valign="bottom"><a href="javascript:void(0)" onClick="return buttonPush('downArea');"><img src="../common/images/arrow2_b.gif" alt="<gsmsg:write key="cmn.down" />" border="0"></a></td>
                </tr>
                </table>
              </td>

              <td width="100%" rowspan="2" align="left">
                <html:select property="cmn150Dsp4AreaLeft" size="10" styleClass="select02" multiple="true">
                  <html:optionsCollection name="cmn150Form" property="selectWeatherAreaCombo" value="value" label="label" />
                </html:select>
              </td>

              </tr>
            </table>
          </td>
          <td align="center">
            <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('addArea');"><br><br>
            <br><br>
            <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('delArea');">
            <br>
          </td>
          <td valign="top"  rowspan="3">
            <table width="100%" cellpadding="0" cellspacing="0">
              <tr>
              <td>
                <html:select property="cmn150Dsp4AreaRight" size="10" styleClass="select02" multiple="true">
                  <html:optionsCollection name="cmn150Form" property="noSelectWeatherAreaCombo" value="value" label="label" />
                </html:select>
              </td>
              </tr>
            </table>

          </td>
        </tr>
        </table>
      </td>
      </tr>

      <!-- 今日は何の日？ -->
      <tr>
      <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
        <span class="text_bb1"><gsmsg:write key="main.src.man001.2" /></span><span class="text_r2">※</span>
      </td>
      <td valign="middle" align="left" class="td_wt" width="100%" colspan="5">
        <html:radio name="cmn150Form" styleId="cmn150Dsp5_01" property="cmn150Dsp5" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstCommon.MAIN_DSP) %>" /><label for="cmn150Dsp5_01"><span class="text_base"><gsmsg:write key="cmn.show" /></span></label>&nbsp;
        <html:radio name="cmn150Form" styleId="cmn150Dsp5_02" property="cmn150Dsp5" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstCommon.MAIN_NOT_DSP) %>" /><label for="cmn150Dsp5_02"><span class="text_base"><gsmsg:write key="cmn.hide" />&nbsp;</span></label>
      </td>
      </tr>

      <!-- ニュース -->
      <tr>
      <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
        <span class="text_bb1"><gsmsg:write key="cmn.news" /></span><span class="text_r2">※</span>
      </td>
      <td valign="middle" align="left" class="td_wt" width="100%" colspan="5">
        <html:radio name="cmn150Form" styleId="cmn150Dsp6_01" property="cmn150Dsp6" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstCommon.MAIN_DSP) %>" /><label for="cmn150Dsp6_01"><span class="text_base"><gsmsg:write key="cmn.show" /></span></label>&nbsp;
        <html:radio name="cmn150Form" styleId="cmn150Dsp6_02" property="cmn150Dsp6" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstCommon.MAIN_NOT_DSP) %>" /><label for="cmn150Dsp6_02"><span class="text_base"><gsmsg:write key="cmn.hide" />&nbsp;</span></label>
      </td>
      </tr>

    </logic:equal>


    <logic:notEqual name="cmn150Form" property="cmn150MainStatus" value="0">
      <html:hidden name="cmn150Form" property="cmn150Dsp4" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstCommon.MAIN_DSP) %>" />
      <html:hidden name="cmn150Form" property="cmn150Dsp5" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstCommon.MAIN_DSP) %>" />
      <html:hidden name="cmn150Form" property="cmn150Dsp6" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstCommon.MAIN_DSP) %>" />
    </logic:notEqual>


    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
    <td width="60%" align="left" class="text_base7">
      <logic:equal name="cmn150Form" property="cmn150MainStatus" value="0">
        <span class="text_red">※</span><gsmsg:write key="cmn.cmn150.5" />
      </logic:equal>
    </td>
    <td width="40%" align="right">
      <input type="button" class="btn_ok1" value="OK" onClick="return buttonPush('cmn150kakunin');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('cmn150back');">
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