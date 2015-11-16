<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>


<%  String order_desc = String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC);
    String order_asc = String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC);
    String sortkey_id = String.valueOf(jp.groupsession.v2.prj.GSConstProject.SORT_PRJECT_ID);
    String sortkey_name = String.valueOf(jp.groupsession.v2.prj.GSConstProject.SORT_PROJECT_NAME);
    String sortkey_start = String.valueOf(jp.groupsession.v2.prj.GSConstProject.SORT_PRJECT_START);
    String sortkey_end = String.valueOf(jp.groupsession.v2.prj.GSConstProject.SORT_PRJECT_END);  %>


<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../project/css/project.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<title>[Group Session] <gsmsg:write key="cmn.select.company" /></title>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../project/js/prj210.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/imageView.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
</head>

<body class="body_03">

<html:form action="/project/prj210">
<input type="hidden" name="CMD" value="">

<html:hidden property="prj210KoukaiKbn" />
<html:hidden property="prj210Progress" />
<html:hidden property="prj210page" />
<html:hidden property="prj210parentPageId" />
<html:hidden property="prj210ProjectSid" />
<html:hidden property="prj210ProjectName" />
<html:hidden property="prj210sort" />
<html:hidden property="prj210order" />

<table width="100%">
<tr>
<td width="100%" align="center">

  <table width="800">
  <tr>
  <td align="left">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../project/images/header_project_01.gif" border="0" alt="<gsmsg:write key="cmn.project" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.project" /></span></td>
    <td width="0%" class="header_white_bg_text">&nbsp;</td>
    <td width="100%" class="header_white_bg">&nbsp;</td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.project" />"></td>
    </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

<div id="prj_select_index">

    <table width="0%" class="tl0" border="0" cellpadding="0" align="center">
    <tr class="prj_select_tr">

    <logic:equal name="prj210Form" property="prj210KoukaiKbn" value="-1">
    <td class="prj_back_select_index"><span class="prj_select_font_blue_bbt_all"><gsmsg:write key="cmn.all" /></span></td>
    </logic:equal>
    <logic:notEqual name="prj210Form" property="prj210KoukaiKbn" value="-1">
    <td class="prj_back_index">
    <a href="javascript:void(0);" onClick="return selectKoukaiKbn('-1');">
    <span class="prj_select_font_black"><gsmsg:write key="cmn.all" /></span>
    </a>
    </td>
    </logic:notEqual>

    <logic:equal name="prj210Form" property="prj210KoukaiKbn" value="0">
    <td class="prj_back_select_index"><span class="prj_select_font_blue_bbt"><gsmsg:write key="cmn.join.project" /></span></td>
    </logic:equal>
    <logic:notEqual name="prj210Form" property="prj210KoukaiKbn" value="0">
    <td class="prj_back_index">
    <a href="javascript:void(0);" onClick="return selectKoukaiKbn('0');">
    <span class="prj_select_font_black"><gsmsg:write key="cmn.join.project" /></span>
    </a>
    </td>
    </logic:notEqual>

    <logic:equal name="prj210Form" property="prj210KoukaiKbn" value="1">
    <td class="prj_back_select_index"><span class="prj_select_font_blue_bbt"><gsmsg:write key="project.prj210.4" /></span></td>
    </logic:equal>
    <logic:notEqual name="prj210Form" property="prj210KoukaiKbn" value="1">
    <td class="prj_back_index">
    <a href="javascript:void(0);" onClick="return selectKoukaiKbn('1');">
    <span class="prj_select_font_black"><gsmsg:write key="project.prj210.4" /></span>
    </a>
    </td>
    </logic:notEqual>
    </tr>
    </table>
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="0" class="tl0" border="0" cellpadding="0" align="center">
    <tr class="prj_select_tr">

    <logic:equal name="prj210Form" property="prj210Progress" value="-1">
    <td class="prj_back_select_index"><span class="prj_select_font_blue_bbt_all"><gsmsg:write key="cmn.all" /></span></td>
    </logic:equal>
    <logic:notEqual name="prj210Form" property="prj210Progress" value="-1">
    <td class="prj_back_index">
    <a href="javascript:void(0);" onClick="return selectProgress('-1');">
    <span class="prj_select_font_black"><gsmsg:write key="cmn.all" /></span>
    </a>
    </td>
    </logic:notEqual>

    <logic:equal name="prj210Form" property="prj210Progress" value="0">
    <td class="prj_back_select_index"><span class="prj_select_font_blue_bbt"><gsmsg:write key="project.prj210.5" /></span></td>
    </logic:equal>
    <logic:notEqual name="prj210Form" property="prj210Progress" value="0">
    <td class="prj_back_index">
    <a href="javascript:void(0);" onClick="return selectProgress('0');">
    <span class="prj_select_font_black"><gsmsg:write key="project.prj210.5" /></span>
    </a>
    </td>
    </logic:notEqual>

    <logic:equal name="prj210Form" property="prj210Progress" value="1">
    <td class="prj_back_select_index"><span class="prj_select_font_blue_bbt"><gsmsg:write key="project.prj210.6" /></span></td>
    </logic:equal>
    <logic:notEqual name="prj210Form" property="prj210Progress" value="1">
    <td class="prj_back_index">
    <a href="javascript:void(0);" onClick="return selectProgress('1');">
    <span class="prj_select_font_black"><gsmsg:write key="project.prj210.6" /></span>
    </a>
    </td>
    </logic:notEqual>

    </tr>
    </table>

</div>
    <logic:notEmpty name="prj210Form" property="prj210ProjectList">
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <% String[] tdClass = {"td_type1", "td_type_usr"}; %>

    <logic:notEmpty  name="prj210Form" property="pageCmbList">
    <table width="100%" cellpadding="5" cellspacing="0">
    <td align="right">
      <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="return buttonPush('prevPage');" style="cursor:pointer;">
      <html:select name="prj210Form" property="prj210pageTop" styleClass="text_i" onchange="buttonPush('changePageTop');">
        <html:optionsCollection name="prj210Form" property="pageCmbList" value="value" label="label" />
      </html:select>
      <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="return buttonPush('nextPage');" style="cursor:pointer;">
    </td>
    </tr>
    </table>
    </logic:notEmpty>

    <table width="100%" class="tl0" border="0" cellpadding="0">
    <tr>
    <th width="0%" class="table_bg_A5B4E1" nowrap></th>

    <logic:equal name="prj210Form" property="prj210sort" value="<%= sortkey_name %>">
      <logic:equal name="prj210Form" property="prj210order" value="<%= order_desc %>">
        <th width="80%" class="table_bg_A5B4E1" nowrap><a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= sortkey_name %>, <%= order_asc %>);"><span class="text_bb2"><gsmsg:write key="project.40" />▼</span></a></th>
      </logic:equal>
      <logic:equal name="prj210Form" property="prj210order" value="<%= order_asc %>">
        <th width="80%" class="table_bg_A5B4E1" nowrap><a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= sortkey_name %>, <%= order_desc %>);"><span class="text_bb2"><gsmsg:write key="project.40" />▲</span></a></th>
      </logic:equal>
    </logic:equal>
    <logic:notEqual name="prj210Form" property="prj210sort" value="<%= sortkey_name %>">
      <th width="80%" class="table_bg_A5B4E1" nowrap><a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= sortkey_name %>, <%= order_asc %>);"><span class="text_bb2"><gsmsg:write key="project.40" /></span></a></th>
    </logic:notEqual>

    <logic:equal name="prj210Form" property="prj210sort" value="<%= sortkey_id %>">
      <logic:equal name="prj210Form" property="prj210order" value="<%= order_desc %>">
        <th width="20%" class="table_bg_A5B4E1" nowrap><a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= sortkey_id %>, <%= order_asc %>);"><span class="text_bb2"><gsmsg:write key="cmn.id" />▼</span></a></th>
      </logic:equal>
      <logic:equal name="prj210Form" property="prj210order" value="<%= order_asc %>">
        <th width="20%" class="table_bg_A5B4E1" nowrap><a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= sortkey_id %>, <%= order_desc %>);"><span class="text_bb2"><gsmsg:write key="cmn.id" />▲</span></a></th>
      </logic:equal>
    </logic:equal>
    <logic:notEqual name="prj210Form" property="prj210sort" value="<%= sortkey_id %>">
      <th width="20%" class="table_bg_A5B4E1" nowrap><a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= sortkey_id %>, <%= order_asc %>);"><span class="text_bb2"><gsmsg:write key="cmn.id" /></span></a></th>
    </logic:notEqual>

    <logic:equal name="prj210Form" property="prj210sort" value="<%= sortkey_start %>">
      <logic:equal name="prj210Form" property="prj210order" value="<%= order_desc %>">
        <th width="0%" class="table_bg_A5B4E1" nowrap><a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= sortkey_start %>, <%= order_asc %>);"><span class="text_bb2"><gsmsg:write key="cmn.start" />▼</span></a></th>
      </logic:equal>
      <logic:equal name="prj210Form" property="prj210order" value="<%= order_asc %>">
        <th width="0%" class="table_bg_A5B4E1" nowrap><a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= sortkey_start %>, <%= order_desc %>);"><span class="text_bb2"><gsmsg:write key="cmn.start" />▲</span></a></th>
      </logic:equal>
    </logic:equal>
    <logic:notEqual name="prj210Form" property="prj210sort" value="<%= sortkey_start %>">
      <th width="0%" class="table_bg_A5B4E1" nowrap><a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= sortkey_start %>, <%= order_asc %>);"><span class="text_bb2"><gsmsg:write key="cmn.start" /></span></a></th>
    </logic:notEqual>

    <logic:equal name="prj210Form" property="prj210sort" value="<%= sortkey_end %>">
      <logic:equal name="prj210Form" property="prj210order" value="<%= order_desc %>">
        <th width="0%" class="table_bg_A5B4E1" nowrap><a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= sortkey_end %>, <%= order_asc %>);"><span class="text_bb2"><gsmsg:write key="cmn.end" />▼</span></a></th>
      </logic:equal>
      <logic:equal name="prj210Form" property="prj210order" value="<%= order_asc %>">
        <th width="0%" class="table_bg_A5B4E1" nowrap><a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= sortkey_end %>, <%= order_desc %>);"><span class="text_bb2"><gsmsg:write key="cmn.end" />▲</span></a></th>
      </logic:equal>
    </logic:equal>
    <logic:notEqual name="prj210Form" property="prj210sort" value="<%= sortkey_end %>">
      <th width="0%" class="table_bg_A5B4E1" nowrap><a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= sortkey_end %>, <%= order_asc %>);"><span class="text_bb2"><gsmsg:write key="cmn.end" /></span></a></th>
    </logic:notEqual>

    </tr>


    <logic:iterate id="projectModel" name="prj210Form" property="prj210ProjectList" indexId="idx">
    <bean:define id="prjData" name="projectModel" type="jp.groupsession.v2.prj.model.ProjectItemModel" />
    <% String projectId = String.valueOf(prjData.getProjectSid()); %>

    <tr>
    <td class="<%= tdClass[idx.intValue() % 2] %>">
    <html:multibox name="prj210Form" property="prj210selectProject" styleId="<%= projectId %>"><%= projectId %></html:multibox>
    </td>
    <td class="<%= tdClass[idx.intValue() % 2] %>">
    <logic:equal name="prjData" property="prjBinSid" value="0">
      <img src="../project/images/prj_icon.gif" name="pitctImage" width="20" height="20" alt="<gsmsg:write key="cmn.icon" />" border="1" class="prj_img_ico">
    </logic:equal>
    <logic:notEqual name="prjData" property="prjBinSid" value="0">
      <img src="../project/prj210.do?CMD=getImageFile&prj010PrjSid=<bean:write name="prjData" property="projectSid" />&prj010PrjBinSid=<bean:write name="prjData" property="prjBinSid" />" name="pitctImage" width="20" height="20" alt="<gsmsg:write key="cmn.icon" />" border="1" onload="initImageView('pitctImage');" class="prj_img_ico">
    </logic:notEqual>
    <label for="<%= projectId %>"><bean:write name="projectModel" property="projectName" />
    </td>
    <td class="<%= tdClass[idx.intValue() % 2] %>">
    <bean:write name="projectModel" property="projectId" />
    </td>
    <td class="<%= tdClass[idx.intValue() % 2] %>">
    <bean:write name="projectModel" property="strStartDate" />
    </td>
    <td class="<%= tdClass[idx.intValue() % 2] %>">
    <bean:write name="projectModel" property="strEndDate" />
    </td>
    </tr>
    </logic:iterate>

    </table>
    </logic:notEmpty>

    <logic:notEmpty  name="prj210Form" property="pageCmbList">
    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
    <td align="right">
      <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('prevPage');" style="cursor:pointer;">
      <html:select name="prj210Form" property="prj210pageBottom" styleClass="text_i" onchange="buttonPush('changePageBottom');">
        <html:optionsCollection name="prj210Form" property="pageCmbList" value="value" label="label" />
      </html:select>
      <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('nextPage');" style="cursor:pointer;">
    </td>
    </tr>
    </table>
    </logic:notEmpty>


    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" class="tl0" border="0" cellpadding="0">
    <tr>
      <td align="center">
      <input type="button" value="<gsmsg:write key="cmn.select" />" class="btn_base1" onClick="return parentReload();">
      &nbsp;&nbsp;
      <input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_close_n1" onClick="return window.close();">
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