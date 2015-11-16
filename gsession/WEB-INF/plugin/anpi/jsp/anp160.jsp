<%@ page pageEncoding="Windows-31J" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>



<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[Group Session] <gsmsg:write key="anp.plugin"/></title>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href='../anpi/css/anpi.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script type="text/javascript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../anpi/js/anp160.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>

</head>

<body class="body_03" onload="" onunload="">

<html:form action="/anpi/anp160">

<input type="hidden" name="CMD" value="">
<html:hidden property="anpiSid"/>
<html:hidden property="anp160ProcMode"/>
<html:hidden property="anp160GrpSid"/>
<html:hidden property="anp160NowPage" />
<html:hidden property="anp160DspPage1" />
<html:hidden property="anp160DspPage2" />

<!--  BODY -->

<table width="100%" cellpadding="5" cellspacing="0">

<tr>
<td width="100%" align="center">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="center">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../anpi/images/header_anpi_01.gif" border="0" alt="<gsmsg:write key="anp.plugin"/>"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="anp.plugin"/></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="anp.anp060kn.01"/> <gsmsg:write key="anp.send.dest"/> ]</td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="anp.plugin"/>"></td>
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
  <td align="center">

    <table width="100%" class="tl0" border="0" cellpadding="5">

      <tr>
        <td align="left" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.group"/></span></td>
        <td align="left" class="td_type20" width="85%"><bean:write name="anp160Form" property="anp160DispGrpName" /></td>
      </tr>

      <!-- ページコンボ -->
      <tr height="25px">
      <td colspan="2" width="0%" align="right" valign="bottom" nowrap>
      <bean:size id="pageCount" name="anp160Form" property="anp160PageLabel" scope="request" />
      <logic:greaterThan name="pageCount" value="1">
        <logic:notEmpty name="anp160Form" property="anp160PageLabel" scope="request">
        <img alt="<gsmsg:write key="cmn.previous.page" />" src="../common/images/arrow2_l.gif" class="text_i" width="20" height="20" border="0" onClick="buttonPush('anp160pageLast');">
        <html:select property="anp160DspPage1" onchange="changePage(this);" styleClass="text_i">
        <html:optionsCollection name="anp160Form" property="anp160PageLabel" value="value" label="label"/>
        </html:select>
        <img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="text_i" width="20" height="20" border="0" onClick="buttonPush('anp160pageNext');">
        </logic:notEmpty>
      </logic:greaterThan>
      </td>
      </tr>

        <tr height="400px">
            <!-- 送信先 -->
            <td valign="top" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
                <span class="text_bb1"><gsmsg:write key="anp.send.dest"/><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
            </td>
            <td valign="top" align="left" class="td_wt table_pad" width="100%">
                <logic:notEmpty name="anp160Form" property="anp160DspSenderList">
                  <table cellpadding="0" cellspacing="0" border="0" class="tl0"  width="98%" >
                  <logic:iterate id="urBean" name="anp160Form" property="anp160DspSenderList" indexId="idx">
                     <tr>
                     <td width="0%" nowrap><span class="text_base">
                     <bean:write name="urBean" property="anp160Name" />
                     &nbsp;&nbsp;</span></td>
                     <td width="0%"><logic:notEqual name="urBean" property="anp160MailFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.anp160.Anp160Form.MAIL_SET_YES) %>">
                            <img src="../anpi/images/nomail.gif" border="0" alt="!"></logic:notEqual>
                     </td>
                     </tr>
                  </logic:iterate>

                  <logic:equal name="anp160Form" property="anp160NosetMailFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.anp160.Anp160Form.MAIL_NOSET_USER_YES) %>">
                     <tr>
                     <td align="right" colspan="2" width="0%" nowrap>
                       <br><span class="text_base_mini"><img src="../anpi/images/nomail.gif" border="0" alt="!" style="vertical-align : middle;">：<gsmsg:write key="anp.anp060kn.08"/></span>
                     </td>
                     </tr>
                   </logic:equal>

                  </table>
                </logic:notEmpty>
            </td>
        </tr>

        <tr>
          <td align="right" colspan="2">
          <bean:size id="pageCount" name="anp160Form" property="anp160PageLabel" scope="request" />
          <logic:greaterThan name="pageCount" value="1">
            <logic:notEmpty name="anp160Form" property="anp160PageLabel" scope="request">
            <img alt="<gsmsg:write key="cmn.previous.page" />" src="../common/images/arrow2_l.gif" class="text_i" width="20" height="20" border="0" onClick="buttonPush('anp160pageLast');">
            <html:select property="anp160DspPage2" onchange="changePage(this);" styleClass="text_i">
            <html:optionsCollection name="anp160Form" property="anp160PageLabel" value="value" label="label"/>
            </html:select>
            <img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="text_i" width="20" height="20" border="0" onClick="buttonPush('anp160pageNext');">
            </logic:notEmpty>
          </logic:greaterThan>
          </td>
        </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="center" cellpadding="5" cellspacing="0">
      <input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_close_n1" onClick="window.close();">
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


</body>
</html:html>