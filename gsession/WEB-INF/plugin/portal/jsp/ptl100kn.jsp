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
<script language="JavaScript" src='../common/js/jquery-ui-1.8.16/jquery-1.6.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../common/js/jquery-ui-1.8.16/jquery-ui-1.8.16.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.core.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.widget.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.mouse.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.draggable.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.droppable.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../portal/js/ptl100kn.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../portal/css/portal.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<title>[Group Session] <gsmsg:write key="ptl.ptl100kn.1" /></title>
</head>

<!-- body -->
<body class="body_03" onload="changeContentKn(0);">
<html:form action="/portal/ptl100kn">
<input type="hidden" name="CMD" value="init">
<html:hidden property="ptlPortletSid" />
<html:hidden property="ptlCmdMode" />

<html:hidden property="ptl090category" />
<html:hidden property="ptl090svCategory" />
<html:hidden property="ptl090sortPortlet" />

<html:hidden property="ptl100contentType" />
<html:hidden property="ptl100contentHtml" />
<html:hidden property="ptl100contentUrl" />
<html:hidden property="ptl100content" />
<html:hidden property="ptl100border" />
<html:hidden property="ptl100name" />
<html:hidden property="ptl100description" />
<html:hidden property="ptl100category" />
<html:hidden property="ptl100init" />

<html:hidden property="ptl100knContentUrl" />

<%@ include file="/WEB-INF/plugin/portal/jsp/ptl_hiddenParams.jsp" %>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>


<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">

<tr>

<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text">[ <gsmsg:write key="ptl.ptl100kn.1" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%">&nbsp;</td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('ptl100knOk')">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('ptl100knBack')">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="10" height="10" border="0" alt="<gsmsg:write key="cmn.space" />">
  </td>
  </tr>

  <tr>
  <td>

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
        <td class="table_bg_A5B4E1" width="250" nowrap><span class="text_bb1"><gsmsg:write key="ptl.17" /></span></td>
        <td align="left" class="td_wt" width="750">
          <span class="text_base"><bean:write name="ptl100knForm" property="ptl100name" /></span>
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.category" /></span></td>
        <td align="left" class="td_wt">
          <span class="text_base"><bean:write name="ptl100knForm" property="ptl100knCategoryName" /></span>
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="ptl.16" /></span></td>
        <td align="left" class="td_wt">
        <logic:equal name="ptl100knForm" property="ptl100border" value="0">
          <span class="text_base"><gsmsg:write key="address.adr010.contact.5" /></span>
        </logic:equal>
        <logic:equal name="ptl100knForm" property="ptl100border" value="1">
          <span class="text_base"><gsmsg:write key="cmn.no" /></span>
        </logic:equal>
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap>
        <span class="text_bb1"><gsmsg:write key="cmn.content" /></span>
        </td>
        <td class="td_wt" style="padding:5px 0 0 0; margin:5px 0 0 0;">
        <table class="tl_u2" cellpadding="0" cellspacing="0" border="0" width="100%">
        <tr id="forcusDsp" align="left">
          <td class="plt_tab_td2" width="5px">&nbsp;</td>
          <td class="now_forcus plt_tab_td2" nowrap><gsmsg:write key="cmn.show" /></td>
          <td class="none_forcus plt_tab_td2" nowrap><a href="#" onclick="changeContentKn(1);">HTML</a></td>
          <td class="plt_tab_td2" width="100%">&nbsp;</td>
        </tr>

        <tr id="forcusHTML" align="left">
          <td class="plt_tab_td2" width="5px">&nbsp;</td>
          <td class="none_forcus plt_tab_td2" nowrap><a href="#" onclick="changeContentKn(0);"><gsmsg:write key="cmn.show" /></a></td>
          <td class="now_forcus plt_tab_td2" nowrap>HTML</td>
          <td class="plt_tab_td2" width="100%">&nbsp;</td>
        </tr>

        <tr>

        <td class="plt_td tl_u2" colspan="5">
          <table id="contentSrcArea">
            <tr><td>
              <logic:equal name="ptl100knForm" property="ptl100contentType" value="0">
                <span class="text_base"><bean:write name="ptl100knForm" property="ptl100content" />
                </span>
              </logic:equal>
              <logic:equal name="ptl100knForm" property="ptl100contentType" value="1">
                <span class="text_base"><bean:write name="ptl100knForm" property="ptl100knContentUrl" />
                </span>
              </logic:equal>
              <logic:equal name="ptl100knForm" property="ptl100contentType" value="2">
                <span class="text_base"><bean:write name="ptl100knForm" property="ptl100contentHtml" />
                </span>
              </logic:equal>
            </div>
          </td></tr></table>
          <logic:equal name="ptl100knForm" property="ptl100border" value="0">
          <table width="100%" id="contentAreaTitle">
          <tr>
            <td class="header_7D91BD_left plt_title"><bean:write name="ptl100knForm" property="ptl100name" /></td>
          </tr>
          </table>
          <table class="portlet_border portlet_style" id="contentArea" width="100%">
          <tr><td>
          </logic:equal>
          <logic:notEqual name="ptl100knForm" property="ptl100border" value="0">
          <table class="portlet portlet_style" id="contentArea" width="100%">
          <tr><td>
          </logic:notEqual>

          <logic:equal name="ptl100knForm" property="ptl100contentType" value="0">
            <bean:write name="ptl100knForm" property="ptl100knContent" filter="false" />
          </logic:equal>

          <logic:equal name="ptl100knForm" property="ptl100contentType" value="1">
            <bean:write name="ptl100knForm" property="ptl100knContentUrl" filter="false" />
          </logic:equal>

          <logic:equal name="ptl100knForm" property="ptl100contentType" value="2">
            <bean:write name="ptl100knForm" property="ptl100knContentHtml" filter="false" />
          </logic:equal>

          </td></tr>
          </table>
          </td>
        </tr>
        </table>

        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="ptl.8" /></span></td>
        <td align="left" class="td_wt">
          <span class="text_base"><bean:write name="ptl100knForm" property="ptl100knDescription" filter="false"/>
          </span>
        </td>
      </tr>

    </table>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="<gsmsg:write key="cmn.spacer" />">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="100%" align="right">
          <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('ptl100knOk');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('ptl100knBack');">
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

<!-- Footer -->
<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>


</body>

</html:html>