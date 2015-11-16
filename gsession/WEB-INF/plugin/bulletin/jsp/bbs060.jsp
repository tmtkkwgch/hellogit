<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%

  int[] sortKeyList = new int[] {
                       jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_THRED,
                       jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_TOUKOU,
                       jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_ETSURAN,
                       jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_USER,
                       jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_SAISHIN,
                       jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_SIZE
                       };
  String[] title_width = new String[] { "50", "7", "7", "13", "18", "5"};
  int order_desc = jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC;
  int order_asc = jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC;
%>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="bbs.9" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../bulletin/js/bbs060.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../bulletin/js/bbsMemPopUp.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../bulletin/css/bulletin.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03" onunload="windowClose();">

<html:form action="/bulletin/bbs060">
<input type="hidden" name="CMD" value="searchThre">
<html:hidden name="bbs060Form" property="bbs010page1" />
<html:hidden name="bbs060Form" property="bbs010forumSid" />
<html:hidden name="bbs060Form" property="threadSid" />
<html:hidden name="bbs060Form" property="bbsmainFlg" />
<html:hidden name="bbs060Form" property="bbs060sortKey" />
<html:hidden name="bbs060Form" property="bbs060orderKey" />

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
        <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="bbs.9" /> ]</td>
        <td width="0%">
        <img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.bulletin" />"></td>
      </tr>
    </table>

    <table cellpadding="5" cellspacing="0" width="100%">
      <tr>
        <td align="right">
          <img src="../common/images/search.gif" alt="<gsmsg:write key="cmn.search" />" class="img_bottom">
          <html:text name="bbs060Form" property="s_key" maxlength="50" style="width:185px;"/>
          <input type="submit" name="btn_prjadd" class="btn_base1s" value="<gsmsg:write key="cmn.search" />" onClick="buttonPush('searchThre');">
          <input type="button" name="btn_prjadd" class="btn_search_n1" value="<gsmsg:write key="cmn.advanced.search" />" onClick="buttonPush('searchThreDtl');">
          <logic:equal name="bbs060Form" property="bbs060createDspFlg" value="true">
            <input type="button" value="<gsmsg:write key="bbs.bbs060.1" />" class="btn_add_n2" onClick="buttonPush('addThre');">
          </logic:equal>
          <logic:equal name="bbs060Form" property="bbs060createDspFlg" value="false">
            <img src="../common/images/damy.gif" width="100" height="5">
          </logic:equal>
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

    <logic:greaterThan name="bbs060Form" property="bbs060forumWarnDisk" value="0">
    <tr>
    <td colspan="3" width="99%">
      <div style="text-align:center; border: solid 2px; border-color: #CC3333!important; width: 98%; padding: 10px 0px; margin: 10px 0px 5px 5px;">
      <table align="center">
      <tr>
      <td><img src="../common/images/warn2.gif" border="0" alt="<gsmsg:write key="cmn.warning" />"></td>
      <td class="text_error">
        <gsmsg:write key="wml.251" /><bean:write name="bbs060Form" property="bbs060forumWarnDisk" /><gsmsg:write key="wml.252" />
      </td>
      </tr>
      </table>
      </div>
    </td>
    </tr>
    </logic:greaterThan>

    <tr>
    <td width="250" valign="top" align="left" class="tl0">

      <logic:notEmpty name="bbs060Form" property="forumList">
        <!-- フォーラム一覧 -->
        <table width="250" border="1" class="tl0">
        <tr>
        <td width="100%" valign="top" align="left" class="tl0" style="border: solid 1px #cccccc;">

            <table width="100%" class="tl0 bbs_forum_header_left">
            <tr>
            <td width="0%"><img src="../bulletin/images/cate_icon01_bdr.gif" alt="<gsmsg:write key="bbs.1" />"></td>
            <td width="100%" align="left" nowrap><span class="text_base3"><gsmsg:write key="bbs.1" /></span></td>
            </tr>
            </table>

            <logic:iterate id="forumMdl" name="bbs060Form" property="forumList" indexId="index">
            <table width="100%" class="tl0 shintyaku_tbl2">
              <tr>
              <td width="100%">

                <% String titleClass = "text_link"; %>
                <logic:equal name="forumMdl" property="readFlg" value="1">
                <% titleClass = "text_title"; %>
                </logic:equal>

                  <table class="tl0" width="100%">
                    <tr>
                    <td width="35px">
                    <logic:equal name="forumMdl" property="imgBinSid" value="0">
                      <img width="30" height="30" src="../bulletin/images/cate_icon01.gif" alt="<gsmsg:write key="bbs.3" />">
                    </logic:equal>

                    <logic:notEqual name="forumMdl" property="imgBinSid" value="0">
                      <img width="30" height="30" src="../bulletin/bbs060.do?CMD=getImageFile&bbs010BinSid=<bean:write name="forumMdl" property="imgBinSid" />&bbs010forumSid=<bean:write name="forumMdl" property="bfiSid" />" alt="<gsmsg:write key="bbs.3" />">
                    </logic:notEqual>
                    </td>
                    <td align="left">
                    <a href="../bulletin/bbs060.do?bbs010forumSid=<bean:write name="forumMdl" property="bfiSid" />"><span class="<%= titleClass %>"><font size="-1"><bean:write name="forumMdl" property="bfiName" filter="false" /></font></span></a>
                    <logic:equal name="forumMdl" property="newFlg" value="1">
                      <img src="../bulletin/images/icon_new.gif" width="30" height="15" alt="new<gsmsg:write key="cmn.icon" />">
                    </logic:equal>
                    </td>
                    </tr>
                </table>
              </td>
              </tr>
            </table>

            </logic:iterate>

        </td>
        </tr>
        </table>
      </logic:notEmpty>

      <!-- 未読スレッド一覧 START-->
      <logic:notEmpty name="bbs060Form" property="notReadThreadList">
        <table width="250px" style="margin-top: 15px;" class="tl0">
        <tr>
          <td width="100%" valign="top" align="left" class="tl0" style="border: solid 1px #cccccc;">
            <table width="100%" class="tl0 bbs_forum_header_left">
            <tr>
              <td width="0%"><img src="../bulletin/images/cate_icon02_bdr.gif" alt="<gsmsg:write key="bbs.2" />"></td>
              <td width="100%" align="left" ><span class="text_base3"><gsmsg:write key="bbs.10" /></span></td>
            </tr>
            </table>
            <% java.util.Map sidMap = new java.util.HashMap(); %>
              <table width="100%" class="tl0 shintyaku_tbl2">
            <logic:iterate id="forumMdl" name="bbs060Form" property="notReadThreadList" indexId="index">
              <% String forumName = new String(((jp.groupsession.v2.bbs.model.BulletinDspModel) forumMdl).getBfiName()); %>
              <% Integer forumSid = new Integer(((jp.groupsession.v2.bbs.model.BulletinDspModel) forumMdl).getBfiSid()); %>
              <% if (!sidMap.containsKey(forumSid)) { %>
              </table>
              <table width="100%" class="tl0 shintyaku_tbl2">
              <tr class="smail_td2">
                <td align="left" width="0%">
                  <logic:equal name="forumMdl" property="binSid" value="0">
                    <img width="20" height="20" src="../bulletin/images/cate_icon01.gif" alt="<gsmsg:write key="bbs.3" />">
                  </logic:equal>
                  <logic:notEqual name="forumMdl" property="binSid" value="0">
                    <img width="20" height="20" src="../bulletin/bbs060.do?CMD=getImageFile&bbs010BinSid=<bean:write name="forumMdl" property="binSid" />&bbs010forumSid=<bean:write name="forumMdl" property="bfiSid" />" alt="<gsmsg:write key="bbs.3" />">
                  </logic:notEqual>
                </td>
                <td align="left" width="100%"><span class="text_base11"><bean:write name="forumMdl" property="bfiName" filter="false" /></span></td>
              </tr>
              <%     sidMap.put(forumSid, forumName); %>
              <% }%>
                <% String titleClass = "text_link"; %>
                <tr class="text_base2">
                  <td colspan="2">&nbsp;・
                    <a href="../bulletin/bbs080.do?bbs010forumSid=<bean:write name="forumMdl" property="bfiSid" />&threadSid=<bean:write name="forumMdl" property="btiSid" />">
                    <span class="<%= titleClass %>"><font size="-1"><bean:write name="forumMdl" property="btiTitle" filter="false" /></font></span></a>
                    <br><span class="text_base10"><bean:write name="forumMdl" property="strWriteDate" filter="false" />
                    </span>
                  </td>
                </tr>
            </logic:iterate>
            </table>
          </td>
        </tr>
        </table>
      </logic:notEmpty>
        <!-- 未読スレッド一覧 END-->

      <logic:equal name="bbs060Form" property="bbs060mreadFlg" value="true">
      <logic:notEqual name="bbs060Form" property="bbs060unreadCnt" value="0">
        <table width="250px" style="margin-top: 15px;" class="tl0">
        <tr>
        <td valign="top" align="left" class="tl0" style="border: solid 1px #cccccc;">
          <table width="100%" class="tl0 bbs_forum_header_left">
            <tr>
              <logic:equal name="bbs060Form" property="bbs060BinSid" value="0">
                <td width="0%"><img width="30" height="30" src="../bulletin/images/cate_icon01.gif" alt="<gsmsg:write key="bbs.3" />"></td>
              </logic:equal>
              <logic:notEqual name="bbs060Form" property="bbs060BinSid" value="0">
                <td width="0%"><img width="30" height="30" src="../bulletin/bbs060.do?CMD=getImageFile&bbs010BinSid=<bean:write name="bbs060Form" property="bbs060BinSid" />&bbs010forumSid=<bean:write name="bbs060Form" property="bbs010forumSid" />" alt="<gsmsg:write key="bbs.3" />"></td>
              </logic:notEqual>
              <td width="100%"><span class="text_base3"><bean:write name="bbs060Form" property="bbs060forumName" /></span></td>
            </tr>
          </table>

          <table width="100%" class="tl0 shintyaku_tbl2">
          <tr>
          <td align="center" style="padding: 10px;">
            <span class="text_base2"><gsmsg:write key="bbs.36" />:&nbsp;<bean:write name="bbs060Form" property="bbs060unreadCnt" /></span>
            <div style="width:100%; text-align:center; margin-top: 15px;">
              <input type="button" name="btn_allread" class="btn_base1w" value="<gsmsg:write key="cmn.all.read" />" onClick="buttonPush('bbs060allRead');">
            </div>
          </td>
          </tr>
          </table>
        </td>
        </tr>
        </table>
      </logic:notEqual>
      </logic:equal>

    </td>
   <td width="0%"><img src="../common/images/spacer.gif" width="8" height="20" border="0" alt=""></td>

   <td valign="top" width="100%">

      <table width="100%">
      <tr>
      <td align="left" width="100%">

          <table width="100%">
            <tr>
            <td width="0%" nowrap>

              <table cellpadding="0" cellspacing="0">
                <tr>
                <logic:equal name="bbs060Form" property="bbs060BinSid" value="0">
                  <td><img width="30" height="30" src="../bulletin/images/cate_icon01.gif" alt="<gsmsg:write key="bbs.3" />"></td>
                </logic:equal>
                <logic:notEqual name="bbs060Form" property="bbs060BinSid" value="0">
                  <td><img width="30" height="30" src="../bulletin/bbs060.do?CMD=getImageFile&bbs010BinSid=<bean:write name="bbs060Form" property="bbs060BinSid" />&bbs010forumSid=<bean:write name="bbs060Form" property="bbs010forumSid" />" alt="<gsmsg:write key="bbs.3" />"></td>
                </logic:notEqual>
                </tr>
              </table>

            </td>
            <td align="left" width="100%" nowrap><span class="text_bbs2"><bean:write name="bbs060Form" property="bbs060forumName" /></span></td>
            </tr>
          </table>

      </td>

      <!-- page combo  -->
      <td align="right" valign="bottom" width="20%" nowrap>
        <logic:notEmpty name="bbs060Form" property="threadList">
        <bean:size id="count1" name="bbs060Form" property="bbsPageLabel" scope="request" />
        <logic:greaterThan name="count1" value="1">
          <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('prevPage');">
          <html:select property="bbs060page1" onchange="changePage(0);" styleClass="text_i">
            <html:optionsCollection name="bbs060Form" property="bbsPageLabel" value="value" label="label" />
          </html:select>
          <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('nextPage');"></td>
        </logic:greaterThan>
        </logic:notEmpty>

      </td>

      </tr>
      </table>





      <table class="tl0" width="100%" cellpadding="0" cellspacing="0">

        <tr>
        <th width="50%" class="header_7D91BD_center" nowrap>
          <logic:equal name="bbs060Form" property="bbs060sortKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_THRED) %>">
            <logic:equal name="bbs060Form" property="bbs060orderKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_ASC) %>">
              <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_THRED) %>,<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_DESC) %>);"><span class="text_base3"><gsmsg:write key="bbs.2" />▲</span></a>
            </logic:equal>
            <logic:equal name="bbs060Form" property="bbs060orderKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_DESC) %>">
              <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_THRED) %>,<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_ASC) %>);"><span class="text_base3"><gsmsg:write key="bbs.2" />▼</span></a>
            </logic:equal>
          </logic:equal>
          <logic:notEqual name="bbs060Form" property="bbs060sortKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_THRED) %>">
            <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_THRED) %>,<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_ASC) %>);"><span class="text_base3"><gsmsg:write key="bbs.2" /></span></a>
          </logic:notEqual>
        </th>

        <th width="7%" class="header_7D91BD_center" nowrap>
          <logic:equal name="bbs060Form" property="bbs060sortKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_TOUKOU) %>">
            <logic:equal name="bbs060Form" property="bbs060orderKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_ASC) %>">
              <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_TOUKOU) %>,<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_DESC) %>);"><span class="text_base3"><gsmsg:write key="bbs.5" />▲</span></a>
            </logic:equal>
            <logic:equal name="bbs060Form" property="bbs060orderKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_DESC) %>">
              <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_TOUKOU) %>,<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_ASC) %>);"><span class="text_base3"><gsmsg:write key="bbs.5" />▼</span></a>
            </logic:equal>
          </logic:equal>
          <logic:notEqual name="bbs060Form" property="bbs060sortKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_TOUKOU) %>">
            <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_TOUKOU) %>,<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_ASC) %>);"><span class="text_base3"><gsmsg:write key="bbs.5" /></span></a>
          </logic:notEqual>
        </th>

        <th width="7%" class="header_7D91BD_center" nowrap>
          <logic:equal name="bbs060Form" property="bbs060sortKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_ETSURAN) %>">
            <logic:equal name="bbs060Form" property="bbs060orderKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_ASC) %>">
              <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_ETSURAN) %>,<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_DESC) %>);"><span class="text_base3"><gsmsg:write key="bbs.11" />▲</span></a>
            </logic:equal>
            <logic:equal name="bbs060Form" property="bbs060orderKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_DESC) %>">
              <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_ETSURAN) %>,<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_ASC) %>);"><span class="text_base3"><gsmsg:write key="bbs.11" />▼</span></a>
            </logic:equal>
          </logic:equal>
          <logic:notEqual name="bbs060Form" property="bbs060sortKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_ETSURAN) %>">
            <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_ETSURAN) %>,<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_ASC) %>);"><span class="text_base3"><gsmsg:write key="bbs.11" /></span></a>
          </logic:notEqual>
        </th>

        <th width="13%" class="header_7D91BD_center" nowrap>
          <logic:equal name="bbs060Form" property="bbs060sortKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_USER) %>">
            <logic:equal name="bbs060Form" property="bbs060orderKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_ASC) %>">
              <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_USER) %>,<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_DESC) %>);"><span class="text_base3"><gsmsg:write key="cmn.contributor" />▲</span></a>
            </logic:equal>
            <logic:equal name="bbs060Form" property="bbs060orderKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_DESC) %>">
              <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_USER) %>,<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_ASC) %>);"><span class="text_base3"><gsmsg:write key="cmn.contributor" />▼</span></a>
            </logic:equal>
          </logic:equal>
          <logic:notEqual name="bbs060Form" property="bbs060sortKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_USER) %>">
            <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_USER) %>,<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_ASC) %>);"><span class="text_base3"><gsmsg:write key="cmn.contributor" /></span></a>
          </logic:notEqual>
        </th>

        <th width="18%" class="header_7D91BD_center" nowrap>
          <logic:equal name="bbs060Form" property="bbs060sortKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_SAISHIN) %>">
            <logic:equal name="bbs060Form" property="bbs060orderKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_ASC) %>">
              <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_SAISHIN) %>,<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_DESC) %>);"><span class="text_base3"><gsmsg:write key="bbs.bbs060.3" />▼</span></a>
            </logic:equal>
            <logic:equal name="bbs060Form" property="bbs060orderKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_DESC) %>">
              <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_SAISHIN) %>,<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_ASC) %>);"><span class="text_base3"><gsmsg:write key="bbs.bbs060.3" />▲</span></a>
            </logic:equal>
          </logic:equal>
          <logic:notEqual name="bbs060Form" property="bbs060sortKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_SAISHIN) %>">
            <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_SAISHIN) %>,<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_ASC) %>);"><span class="text_base3"><gsmsg:write key="bbs.bbs060.3" /></span></a>
          </logic:notEqual>
        </th>

        <th width="5%" class="header_7D91BD_center" nowrap>
          <logic:equal name="bbs060Form" property="bbs060sortKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_SIZE) %>">
            <logic:equal name="bbs060Form" property="bbs060orderKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_ASC) %>">
              <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_SIZE) %>,<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_DESC) %>);"><span class="text_base3"><gsmsg:write key="cmn.size" />▲</span></a>
            </logic:equal>
            <logic:equal name="bbs060Form" property="bbs060orderKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_DESC) %>">
              <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_SIZE) %>,<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_ASC) %>);"><span class="text_base3"><gsmsg:write key="cmn.size" />▼</span></a>
            </logic:equal>
          </logic:equal>
          <logic:notEqual name="bbs060Form" property="bbs060sortKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_SIZE) %>">
            <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_SIZE) %>,<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.ORDER_KEY_ASC) %>);"><span class="text_base3"><gsmsg:write key="cmn.size" /></span></a>
          </logic:notEqual>
        </th>

        <logic:notEmpty name="bbs060Form" property="threadList">
        <logic:iterate id="threadMdl" name="bbs060Form" property="threadList" indexId="idx">
        <bean:define id="tdColor" value="" />
        <% String[] tdColors = new String[] {"td_type20", "td_type25_2"}; %>
        <% tdColor = tdColors[(idx.intValue() % 2)]; %>

        <% String titleClass = "text_link"; %>
        <% String valueClass = "sc_ttl_sat"; %>
        <logic:equal name="threadMdl" property="readFlg" value="1">
          <% titleClass = "text_title_kidoku"; %>
          <% valueClass = "text_p"; %>
        </logic:equal>
        <logic:notEqual name="threadMdl" property="readFlg" value="1">
          <% titleClass = "text_title_midoku"; %>
        </logic:notEqual>


        <tr>

        <td width="50%" class="<%= tdColor %>" valign="middle">

          <table cellpadding="0" cellpadding="0">
            <tr>
              <td><img src="../bulletin/images/cate_icon02.gif" alt="<gsmsg:write key="bbs.2" />"></td>
              <td class="menu_bun">&nbsp;<a href="javascript:clickThread(<bean:write name="threadMdl" property="btiSid" />);"><span class="<%= titleClass %>"><bean:write name="threadMdl" property="btiTitle" /></span></a>
              <logic:equal name="threadMdl" property="newFlg" value="1">
                <img src="../bulletin/images/icon_new.gif" width="30" height="15" alt="new<gsmsg:write key="cmn.icon" />" border="1">
              </logic:equal>
              </td>

            </tr>
          </table>

        </td>


        <td class="<%= tdColor %>" align="right"><span class="<%= valueClass %>"><bean:write name="threadMdl" property="writeCnt" /></span></td>
        <td class="<%= tdColor %>" align="right">
        <span class="<%= valueClass %>"><a href="javascript:openComWindow(<bean:write name="bbs060Form" property="bbs010forumSid" />, <bean:write name="threadMdl" property="btiSid" />);"><span class="<%= valueClass %>"><bean:write name="threadMdl" property="readedCnt" />&nbsp;/&nbsp;<bean:write name="bbs060Form" property="forumMemberCount" /></a></span>
        </td>

        <td class="<%= tdColor %>" align="left">
          <bean:define id="cbGrpSid" name="threadMdl" property="grpSid" type="java.lang.Integer" />
          <% if (cbGrpSid.intValue() > 0) { %>
          <logic:equal name="threadMdl" property="grpJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
            <s><span class="<%= valueClass %>"><bean:write name="threadMdl" property="grpName" /></span></s>
          </logic:equal>
          <logic:notEqual name="threadMdl" property="grpJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
            <span class="<%= valueClass %>"><bean:write name="threadMdl" property="grpName" /></span>
          </logic:notEqual>
         <% } else { %>
            <logic:equal name="threadMdl" property="userJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
              <s><span class="<%= valueClass %>"><bean:write name="threadMdl" property="userName" /></span></s>
            </logic:equal>
            <logic:notEqual name="threadMdl" property="userJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
              <span class="<%= valueClass %>"><bean:write name="threadMdl" property="userName" /></span>
            </logic:notEqual>
          <% } %>
        </td>
        <td class="<%= tdColor %>" align="center"><span class="<%= valueClass %>"><bean:write name="threadMdl" property="strWriteDate" /></span></td>
        <td class="<%= tdColor %>" align="right"><span class="<%= valueClass %>"><bean:write name="threadMdl" property="viewBtsSize" /></span></td>
        </tr>

        </logic:iterate>
        </logic:notEmpty>
      </table>

      <logic:notEmpty name="bbs060Form" property="threadList">
      <bean:size id="count1" name="bbs060Form" property="bbsPageLabel" scope="request" />
      <logic:greaterThan name="count1" value="1">
      <table width="100%" cellpadding="5" cellspacing="0">
        <tr>
        <td align="right">
          <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('prevPage');">
          <html:select property="bbs060page2" onchange="changePage(1);" styleClass="text_i">
            <html:optionsCollection name="bbs060Form" property="bbsPageLabel" value="value" label="label" />
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