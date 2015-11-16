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
<title>[GroupSession] <gsmsg:write key="bbs.9" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../bulletin/js/bbs170.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../bulletin/js/bbsMemPopUp.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../bulletin/css/bulletin.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03" onunload="windowClose();">

<html:form action="/bulletin/bbs170">
<input type="hidden" name="CMD" value="searchThre">
<html:hidden name="bbs170Form" property="bbs010page1" />
<html:hidden name="bbs170Form" property="bbs010forumSid" />
<html:hidden name="bbs170Form" property="threadSid" />
<html:hidden name="bbs170Form" property="bbs170sortKey" />
<html:hidden name="bbs170Form" property="bbs170orderKey" />


<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!-- BODY -->
<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%">
        <img src="../bulletin/images/header_bulletin_01.gif" border="0" alt="<gsmsg:write key="cmn.bulletin" />"></td>
        <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.bulletin" /></span></td>
        <td width="100%" class="header_white_bg_text">[ 掲示予定一覧 ]</td>
        <td width="0%">
        <img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.bulletin" />"></td>
      </tr>
    </table>

    <table cellpadding="5" cellspacing="0" width="100%">
      <tr>
        <td align="right">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backBBSList');">
        </td>
      </tr>
    </table>

    <logic:messagesPresent message="false">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
         <td align="left"><html:errors/><br></td>
      </tr>
    </table>
    </logic:messagesPresent>

    <table width="100%">

    <logic:greaterThan name="bbs170Form" property="bbs170forumWarnDisk" value="0">
    <tr>
    <td colspan="3" width="99%">
      <div style="text-align:center; border: solid 2px; border-color: #CC3333!important; width: 98%; padding: 10px 0px; margin: 10px 0px 5px 5px;">
      <table align="center">
      <tr>
      <td><img src="../common/images/warn2.gif" border="0" alt="<gsmsg:write key="cmn.warning" />"></td>
      <td class="text_error">
        <gsmsg:write key="wml.251" /><bean:write name="bbs170Form" property="bbs170forumWarnDisk" /><gsmsg:write key="wml.252" />
      </td>
      </tr>
      </table>
      </div>
    </td>
    </tr>
    </logic:greaterThan>

    <tr>

    <td valign="top" width="100%">

      <table width="100%">
      <tr>
      <td align="left" width="100%">

          <table width="100%">
            <tr>
            <td width="0%" nowrap>

              <table cellpadding="0" cellspacing="0">
                <tr>
                <logic:equal name="bbs170Form" property="bbs170BinSid" value="0">
                  <td><img width="30" height="30" src="../bulletin/images/cate_icon01.gif" alt="<gsmsg:write key="bbs.3" />"></td>
                </logic:equal>
                <logic:notEqual name="bbs170Form" property="bbs170BinSid" value="0">
                  <td><img width="30" height="30" src="../bulletin/bbs170.do?CMD=getImageFile&bbs010BinSid=<bean:write name="bbs170Form" property="bbs170BinSid" />&bbs010forumSid=<bean:write name="bbs170Form" property="bbs010forumSid" />" alt="<gsmsg:write key="bbs.3" />"></td>
                </logic:notEqual>
                </tr>
              </table>

            </td>
            <td align="left" width="100%" nowrap><span class="text_bbs2"><bean:write name="bbs170Form" property="bbs170forumName" /></span></td>
            </tr>
          </table>

      </td>

      <!-- page combo  -->
      <td align="right" valign="bottom" width="20%" nowrap>
        <logic:notEmpty name="bbs170Form" property="threadList">
        <bean:size id="count1" name="bbs170Form" property="bbsPageLabel" scope="request" />
        <logic:greaterThan name="count1" value="1">
          <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('prevPage');">
          <html:select property="bbs170page1" onchange="changePage(0);" styleClass="text_i">
            <html:optionsCollection name="bbs170Form" property="bbsPageLabel" value="value" label="label" />
          </html:select>
          <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('nextPage');"></td>
        </logic:greaterThan>
        </logic:notEmpty>

      </td>

      </tr>
      </table>


      <table class="tl0" width="100%" cellpadding="0" cellspacing="0">

        <tr>
        <th width="42%" class="header_7D91BD_center" nowrap>
          <logic:equal name="bbs170Form" property="bbs170sortKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_THRED) %>">
            <logic:equal name="bbs170Form" property="bbs170orderKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_ASC) %>">
              <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_THRED) %>,<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_DESC) %>);"><span class="text_base3"><gsmsg:write key="bbs.2" />▲</span></a>
            </logic:equal>
            <logic:equal name="bbs170Form" property="bbs170orderKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_DESC) %>">
              <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_THRED) %>,<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_ASC) %>);"><span class="text_base3"><gsmsg:write key="bbs.2" />▼</span></a>
            </logic:equal>
          </logic:equal>
          <logic:notEqual name="bbs170Form" property="bbs170sortKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_THRED) %>">
            <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_THRED) %>,<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_ASC) %>);"><span class="text_base3"><gsmsg:write key="bbs.2" /></span></a>
          </logic:notEqual>
        </th>

        <th width="13%" class="header_7D91BD_center" nowrap>
          <logic:equal name="bbs170Form" property="bbs170sortKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_USER) %>">
            <logic:equal name="bbs170Form" property="bbs170orderKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_ASC) %>">
              <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_USER) %>,<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_DESC) %>);"><span class="text_base3"><gsmsg:write key="cmn.contributor" />▲</span></a>
            </logic:equal>
            <logic:equal name="bbs170Form" property="bbs170orderKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_DESC) %>">
              <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_USER) %>,<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_ASC) %>);"><span class="text_base3"><gsmsg:write key="cmn.contributor" />▼</span></a>
            </logic:equal>
          </logic:equal>
          <logic:notEqual name="bbs170Form" property="bbs170sortKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_USER) %>">
            <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_USER) %>,<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_ASC) %>);"><span class="text_base3"><gsmsg:write key="cmn.contributor" /></span></a>
          </logic:notEqual>
        </th>

        <th width="16%" class="header_7D91BD_center" nowrap>
          <logic:equal name="bbs170Form" property="bbs170sortKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_LIMIT_FR) %>">
            <logic:equal name="bbs170Form" property="bbs170orderKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_ASC) %>">
              <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_LIMIT_FR) %>,<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_DESC) %>);"><span class="text_base3"><gsmsg:write key="bbs.bbs070.5" />▼</span></a>
            </logic:equal>
            <logic:equal name="bbs170Form" property="bbs170orderKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_DESC) %>">
              <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_LIMIT_FR) %>,<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_ASC) %>);"><span class="text_base3"><gsmsg:write key="bbs.bbs070.5" />▲</span></a>
            </logic:equal>
          </logic:equal>
          <logic:notEqual name="bbs170Form" property="bbs170sortKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_LIMIT_FR) %>">
            <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_LIMIT_FR) %>,<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_ASC) %>);"><span class="text_base3"><gsmsg:write key="bbs.bbs070.5" /></span></a>
          </logic:notEqual>
        </th>

        <th width="16%" class="header_7D91BD_center" nowrap>
          <logic:equal name="bbs170Form" property="bbs170sortKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_LIMIT_TO) %>">
            <logic:equal name="bbs170Form" property="bbs170orderKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_ASC) %>">
              <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_LIMIT_TO) %>,<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_DESC) %>);"><span class="text_base3"><gsmsg:write key="bbs.bbs070.6" />▼</span></a>
            </logic:equal>
            <logic:equal name="bbs170Form" property="bbs170orderKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_DESC) %>">
              <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_LIMIT_TO) %>,<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_ASC) %>);"><span class="text_base3"><gsmsg:write key="bbs.bbs070.6" />▲</span></a>
            </logic:equal>
          </logic:equal>
          <logic:notEqual name="bbs170Form" property="bbs170sortKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_LIMIT_TO) %>">
            <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_LIMIT_TO) %>,<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_ASC) %>);"><span class="text_base3"><gsmsg:write key="bbs.bbs070.6" /></span></a>
          </logic:notEqual>
        </th>

        <th width="5%" class="header_7D91BD_center" nowrap>
          <logic:equal name="bbs170Form" property="bbs170sortKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_SIZE) %>">
            <logic:equal name="bbs170Form" property="bbs170orderKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_ASC) %>">
              <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_SIZE) %>,<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_DESC) %>);"><span class="text_base3"><gsmsg:write key="cmn.size" />▲</span></a>
            </logic:equal>
            <logic:equal name="bbs170Form" property="bbs170orderKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_DESC) %>">
              <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_SIZE) %>,<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_ASC) %>);"><span class="text_base3"><gsmsg:write key="cmn.size" />▼</span></a>
            </logic:equal>
          </logic:equal>
          <logic:notEqual name="bbs170Form" property="bbs170sortKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_SIZE) %>">
            <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_SIZE) %>,<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_ASC) %>);"><span class="text_base3"><gsmsg:write key="cmn.size" /></span></a>
          </logic:notEqual>
        </th>

        <th width="8%" class="header_7D91BD_center" nowrap>
        <span class="text_base3"><gsmsg:write key="bbs.bbs080.1" /></span>
        </th>
        </tr>

        <logic:notEmpty name="bbs170Form" property="threadList">
        <logic:iterate id="threadMdl" name="bbs170Form" property="threadList" indexId="idx">
        <bean:define id="tdColor" value="" />
        <% String[] tdColors = new String[] {"td_type20", "td_type25_2"}; %>
        <% tdColor = tdColors[(idx.intValue() % 2)]; %>

        <tr>

        <td class="<%= tdColor %>" valign="middle">

          <table cellpadding="0" cellpadding="0">
            <tr>
              <td><img src="../bulletin/images/cate_icon02.gif" alt="<gsmsg:write key="bbs.2" />"></td>
              <td class="menu_bun">&nbsp;<a href="javascript:buttnPushWrite('editThread',<bean:write name="threadMdl" property="btiSid" />);"><span class="text_title_midoku"><bean:write name="threadMdl" property="btiTitle" /></span></a>
              </td>

            </tr>
          </table>

        </td>

        <td class="<%= tdColor %>" align="left">
          <bean:define id="cbGrpSid" name="threadMdl" property="grpSid" type="java.lang.Integer" />
          <% if (cbGrpSid.intValue() > 0) { %>
          <logic:equal name="threadMdl" property="grpJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
            <s><span class="text_base2"><bean:write name="threadMdl" property="grpName" /></span></s>
          </logic:equal>
          <logic:notEqual name="threadMdl" property="grpJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
            <span class="text_base2"><bean:write name="threadMdl" property="grpName" /></span>
          </logic:notEqual>
         <% } else { %>
            <logic:equal name="threadMdl" property="userJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
              <s><span class="text_base2"><bean:write name="threadMdl" property="userName" /></span></s>
            </logic:equal>
            <logic:notEqual name="threadMdl" property="userJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
              <span class="text_base2"><bean:write name="threadMdl" property="userName" /></span>
            </logic:notEqual>
          <% } %>
        </td>
        <td class="<%= tdColor %>" align="center"><span class="text_base2"><bean:write name="threadMdl" property="strBtiLimitFrDate" /></span></td>
        <td class="<%= tdColor %>" align="center"><span class="text_base2"><bean:write name="threadMdl" property="strBtiLimitDate" /></span></td>
        <td class="<%= tdColor %>" align="right"><span class="text_base2"><bean:write name="threadMdl" property="viewBtsSize" /></span></td>
        <td class="<%= tdColor %>" align="center"><input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n3" onClick="buttonPushDelete('<bean:write name="threadMdl" property="btiSid" />');"></td>
        </tr>

        </logic:iterate>
        </logic:notEmpty>
      </table>

      <logic:notEmpty name="bbs170Form" property="threadList">
      <bean:size id="count1" name="bbs170Form" property="bbsPageLabel" scope="request" />
      <logic:greaterThan name="count1" value="1">
      <table width="100%" cellpadding="5" cellspacing="0">
        <tr>
        <td align="right">
          <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('prevPage');">
          <html:select property="bbs170page2" onchange="changePage(1);" styleClass="text_i">
            <html:optionsCollection name="bbs170Form" property="bbsPageLabel" value="value" label="label" />
          </html:select>
          <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('nextPage');">
        </td>
        </tr>
      </table>
      </logic:greaterThan>
      </logic:notEmpty>

      <!-- width set  -->
      <img src="../common/images/spacer.gif" width="600" height="20" border="0" alt="">

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