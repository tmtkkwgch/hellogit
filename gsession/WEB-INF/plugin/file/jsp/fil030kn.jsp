<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<% String sinki = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_CREATE_MODE_NEW); %>
<% String hensin = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_CREATE_MODE_HENSIN); %>
<% String zenhen = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_CREATE_MODE_ZENHENSIN); %>
<% String tenso = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_CREATE_MODE_TENSO); %>
<% String soko = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_CREATE_MODE_SOKO); %>
<% String nikkan = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_CREATE_MODE_SC_NIKKAN); %>
<% String syukan = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_CREATE_MODE_SC_SYUKAN); %>
<% String zaiseki = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_CREATE_MODE_ZAISEKI); %>
<% String main = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_CREATE_MODE_MAIN); %>
<% String toroku = String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_TOROKU); %>
<% String delete = String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE); %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.filekanri" /> <gsmsg:write key="fil.52" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/file.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/dtree.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/fil030kn.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/file.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03">

<html:form action="/file/fil030kn">
<input type="hidden" name="CMD" value="">
<input type="hidden" name="fileSid" value="">
<html:hidden property="cmnMode" />
<html:hidden property="backDsp" />
<html:hidden property="backScreen" />
<html:hidden property="filSearchWd" />
<html:hidden property="admVerKbn" />
<html:hidden property="fil010SelectCabinet" />
<html:hidden property="fil010SelectDirSid" />
<html:hidden property="fil030SelectCabinet" />
<html:hidden property="fil030CabinetName" />
<html:hidden property="fil030AccessKbn" />
<html:hidden property="fil030CapaKbn" />
<html:hidden property="fil030CapaSize" />
<html:hidden property="fil030CapaWarn" />
<html:hidden property="fil030VerKbn" />
<html:hidden property="fil030VerAllKbn" />
<html:hidden property="fil030Biko" />
<html:hidden property="fil030ImageName" />
<html:hidden property="fil030ImageSaveName" />
<html:hidden property="fil030InitFlg" />
<html:hidden property="file030AdaptIncFile" />

<logic:notEmpty name="fil030knForm" property="fil220sltCheck" scope="request">
<logic:iterate id="select" name="fil030knForm" property="fil220sltCheck" scope="request">
  <input type="hidden" name="fil220sltCheck" value="<bean:write name="select" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil030knForm" property="fil030SvAcFull">
<logic:iterate id="afid" name="fil030knForm" property="fil030SvAcFull">
  <input type="hidden" name="fil030SvAcFull" value="<bean:write name="afid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil030knForm" property="fil030SvAcRead">
<logic:iterate id="arid" name="fil030knForm" property="fil030SvAcRead">
  <input type="hidden" name="fil030SvAcRead" value="<bean:write name="arid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil030knForm" property="fil030SvAdm">
<logic:iterate id="admid" name="fil030knForm" property="fil030SvAdm">
  <input type="hidden" name="fil030SvAdm" value="<bean:write name="admid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil030knForm" property="fil010SelectDelLink" scope="request">
<logic:iterate id="item" name="fil030knForm" property="fil010SelectDelLink" scope="request">
  <input type="hidden" name="fil010SelectDelLink" value="<bean:write name="item"/>">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil030knForm" property="fil040SelectDel" scope="request">
  <logic:iterate id="del" name="fil030knForm" property="fil040SelectDel" scope="request">
    <input type="hidden" name="fil040SelectDel" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>



<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<input type="hidden" name="helpPrm" value="<bean:write name="fil030knForm" property="cmnMode" />">

<!-- BODY -->

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" width="70%" class="tl0">
  <tr>
  <td align="center">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>

    <td width="0%">
      <img src="../file/images/header_project_01.gif" border="0" alt="<gsmsg:write key="cmn.filekanri" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.filekanri" /></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="fil.52" /> ]</td>
    <td width="0%" class="header_white_bg">
      <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" class="btn_ok1" value="OK" onclick="buttonPush('fil030knok');">
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('fil030knback');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <logic:messagesPresent message="false">
      <table width="100%">
      <tr>
        <td align="left"><span class="TXT02"><html:errors/></span></td>
      </tr>
      </table>
    </logic:messagesPresent>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <table width="100%" class="tl0 prj_tbl_base" border="0" cellpadding="5">
    <tr>
    <td class="td_sub_title" nowrap>
    <span class="text_bb1"><gsmsg:write key="fil.13" /><span class="text_r2">※</span></span>
    </td>
    <td align="left" class="td_sub_detail">
    <span class="text_base_prj"><bean:write name="fil030knForm" property="fil030CabinetName" /></span>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="td_sub_title" width="0%" nowrap>
    <span class="text_bb1"><gsmsg:write key="fil.102" /></span><span class="text_r2">※</span>
    </td>


    <logic:equal name="fil030knForm" property="fil030AccessKbn" value="0">
    <td align="left" class="td_sub_detail">
    <span class="text_base_prj"><gsmsg:write key="cmn.not.limit" /></span>
    </td>
    </logic:equal>

    <logic:notEqual name="fil030knForm" property="fil030AccessKbn" value="0">
    <td valign="middle" align="left" class="td_wt" width="100%">
      <table width="60%" cellpadding="0" cellspacing="5" border="0" class="tl_u2">
      <logic:notEmpty name="fil030knForm" property="fil030AcFullLavel" scope="request">
      <tr>
      <td width="40%" class="td_sub_title3" align="center"><span class="text_bb1"><gsmsg:write key="cmn.add.edit.delete" /></span></td>
      </tr>
      <tr>
      <td align="left" class="td_wt">
      <logic:iterate id="fullBean" name="fil030knForm" property="fil030AcFullLavel" scope="request">
      <span class="text_base"><bean:write name="fullBean" property="label" /></span><br>
      </logic:iterate>
      </td>
      </tr>
      </logic:notEmpty>

      <logic:notEmpty name="fil030knForm" property="fil030AcReadLavel" scope="request">
      <tr>
      <td width="40%" class="td_sub_title3" align="center"><span class="text_bb1"><gsmsg:write key="cmn.reading" /></span></td>
      </tr>
      <tr>
      <td align="left" class="td_wt">
      <logic:iterate id="readBean" name="fil030knForm" property="fil030AcReadLavel" scope="request">
      <span class="text_base"><bean:write name="readBean" property="label" /></span><br>
      </logic:iterate>
      </td>
      </tr>
      </logic:notEmpty>

      </table>
      <br>
      <span class="text_base"><gsmsg:write key="fil.127" />：<b>
      <logic:notEqual name="fil030knForm" property="file030AdaptIncFile" value="1"><gsmsg:write key="fil.129" /></logic:notEqual>
      <logic:equal name="fil030knForm" property="file030AdaptIncFile" value="1"><gsmsg:write key="fil.128" /></logic:equal></b></span>
    </td>
    </logic:notEqual>


    </tr>

    <tr>
    <td valign="middle" align="left" class="td_sub_title" width="0%" nowrap>
    <span class="text_bb1"><gsmsg:write key="fil.14" /></span><span class="text_r2">※</span>
    </td>
    <td class="td_sub_detail" align="left" width="80%">
    <logic:notEmpty name="fil030knForm" property="fil030AdmLavel" scope="request">
    <logic:iterate id="admBean" name="fil030knForm" property="fil030AdmLavel" scope="request">
      <span class="text_base"><bean:write name="admBean" property="label" /></span><br>
      </logic:iterate>
    </logic:notEmpty>
    </td>
    </tr>

    <tr>
    <td class="td_sub_title" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="fil.3" /></span><span class="text_r2">※</span></td>
    <td class="td_sub_detail" align="left" width="80%">
    <logic:equal name="fil030knForm" property="fil030CapaKbn" value="0">
    <span class="text_base_prj"><gsmsg:write key="cmn.noset" /></span>
    </logic:equal>

    <logic:notEqual name="fil030knForm" property="fil030CapaKbn" value="0">
    <span class="text_base"><gsmsg:write key="fil.4" />：<b><bean:write name="fil030knForm" property="fil030knDspCapaSize" /></b></span>
    <logic:notEqual name="fil030knForm" property="fil030CapaWarn" value="0">
    <br><span class="text_base"><gsmsg:write key="fil.fil030kn.1" />：<b><bean:write name="fil030knForm" property="fil030CapaWarn" />%</b></span>
    </logic:notEqual>
    </logic:notEqual>
    </td>
    </tr>

    <logic:equal name="fil030knForm" property="admVerKbn" value="1" >
    <tr>
    <td class="td_sub_title" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="fil.5" /></span><span class="text_r2">※</span></td>
    <td class="td_sub_detail" align="left" width="80%">

    <logic:equal name="fil030knForm" property="fil030VerAllKbn" value="1">
    <span class="text_base">&nbsp;<gsmsg:write key="fil.15" />&nbsp;&nbsp;<gsmsg:write key="fil.fil030.3" />：<bean:write name="fil030knForm" property="fil030VerKbn" /> </span>
    </logic:equal>
    <logic:notEqual name="fil030knForm" property="fil030VerAllKbn" value="1">
    <span class="text_base">&nbsp;<gsmsg:write key="fil.fil030kn.5" /></span>
    </logic:notEqual>

    </td>
    </tr>
    </logic:equal>

    <logic:notEqual name="fil030knForm" property="cmnMode" value="2">
    <tr>
    <td class="td_sub_title" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.icon" /></span><span class="text_r2">※</span></td>
    <td class="td_sub_detail" align="left" width="80%">

      <table width="100%" border="0">
      <tr>
      <td width="0%">
        <logic:empty name="fil030knForm" property="fil030ImageName">
        <img src="../file/images/cabinet.gif" alt="">
        </logic:empty>
        <logic:notEmpty name="fil030knForm" property="fil030ImageName">
        <logic:equal name="fil030knForm" property="cmnMode" value="0">
          <img width="30" src="../file/fil030kn.do?CMD=getImageFile" name="pitctImage" alt="<gsmsg:write key="cmn.photo" />" border="1" onload="">
        </logic:equal>
        <logic:equal name="fil030knForm" property="cmnMode" value="1">
          <img width="30" src="../file/fil030kn.do?CMD=getImageFile&cmnMode=<bean:write name="fil030knForm" property="cmnMode" />&fil030SelectCabinet=<bean:write name="fil030knForm" property="fil030SelectCabinet" />" name="pitctImage" alt="<gsmsg:write key="cmn.photo" />" border="1" onload="">
        </logic:equal>
        </logic:notEmpty>
      </td>
      </tr>

      </table>
    </td>
    </tr>
    </logic:notEqual>

    <tr>
    <td class="td_sub_title" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.memo" /></span></td>
    <td class="td_sub_detail" align="left" width="80%">
    <span class="text_base"><bean:write name="fil030knForm" property="fil030knDspBiko" filter="false"/></span>
    </td>
    </tr>
<%--
    <tr>
    <td class="td_sub_title" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.attach.file" /></span></td>

    <td class="td_sub_detail" align="left" width="80%">

        <logic:empty name="fil030knForm" property="fil030FileLabelList" scope="request">&nbsp;</logic:empty>

        <logic:notEmpty name="fil030knForm" property="fil030FileLabelList" scope="request">
        <table cellpadding="0" cellpadding="0" border="0">

        <logic:iterate id="fileMdl" name="fil030knForm" property="fil030FileLabelList" scope="request">
          <tr>
          <td><img src="../common/images/file_icon.gif" alt="<gsmsg:write key="cmn.file" />"></td>
          <td class="menu_bun"><a href="javascript:void(0);" onClick="fileDl('fileDownload', '<bean:write name="fileMdl" property="value" />');"><span class="text_link"><bean:write name="fileMdl" property="label" /></span></a></td>
          </tr>

        </logic:iterate>

        </table>
        </logic:notEmpty>
    </td>
    </tr>
--%>
    </table>

  </td>
  </tr>
  <tr>
  <td><img src="../common/images/spacer.gif" width="1px" height="10px" border="0"></td>
  </tr>

  <tr>
  <td align="right">
    <input type="button" class="btn_ok1" value="OK" onclick="buttonPush('fil030knok');">
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('fil030knback');">
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