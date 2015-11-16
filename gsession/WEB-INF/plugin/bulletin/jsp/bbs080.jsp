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
<title>[GroupSession] <gsmsg:write key="bbs.32" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmnPic.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../bulletin/js/bbs080.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../bulletin/js/bbsMemPopUp.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/userpopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/imageView.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../bulletin/css/bulletin.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<link rel=stylesheet href='../common/css/selection.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/jquery.selection-min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/selectionSearch.js?<%= GSConst.VERSION_PARAM %>"></script>

</head>

<body class="body_03" onload="" onunload="windowClose();">

<html:form action="/bulletin/bbs080">
<input type="hidden" name="CMD" value="">
<html:hidden name="bbs080Form" property="bbsmainFlg" />
<html:hidden name="bbs080Form" property="s_key" />
<html:hidden name="bbs080Form" property="bbs010page1" />
<html:hidden name="bbs080Form" property="bbs010forumSid" />
<html:hidden name="bbs080Form" property="bbs060page1" />
<html:hidden name="bbs080Form" property="searchDspID" />
<html:hidden name="bbs080Form" property="bbs040forumSid" />
<html:hidden name="bbs080Form" property="bbs040keyKbn" />
<html:hidden name="bbs080Form" property="bbs040taisyouThread" />
<html:hidden name="bbs080Form" property="bbs040taisyouNaiyou" />
<html:hidden name="bbs080Form" property="bbs040userName" />
<html:hidden name="bbs080Form" property="bbs040readKbn" />
<html:hidden name="bbs080Form" property="bbs040dateNoKbn" />
<html:hidden name="bbs080Form" property="bbs040fromYear" />
<html:hidden name="bbs080Form" property="bbs040fromMonth" />
<html:hidden name="bbs080Form" property="bbs040fromDay" />
<html:hidden name="bbs080Form" property="bbs040toYear" />
<html:hidden name="bbs080Form" property="bbs040toMonth" />
<html:hidden name="bbs080Form" property="bbs040toDay" />
<html:hidden name="bbs080Form" property="threadSid" />

<html:hidden name="bbs080Form" property="bbs080writeSid" />
<html:hidden name="bbs080Form" property="bbs080binSid" />
<html:hidden name="bbs080Form" property="bbs080reply" />
<html:hidden name="bbs080Form" property="bbs041page1" />

<input type="hidden" name="cmd" value="">
<input type="hidden" name="sch010SelectUsrSid" value="">
<input type="hidden" name="sch010SelectUsrKbn" value="">

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
        <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="bbs.32" /> ]</td>
        <td width="0%">
        <img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.bulletin" />"></td>
      </tr>
    </table>

    <table cellpadding="5" cellspacing="0" width="100%">
      <tr>
        <td align="right">
        <input type="button" value="<gsmsg:write key="cmn.pdf" />" class="btn_pdf_n1" onClick="buttonPush('pdf');">
        <logic:equal name="bbs080Form" property="bbs080ShowThreBtn" value="1">
        <input type="button" class="btn_dell_n1" value="<gsmsg:write key="bbs.bbs080.1" />" onClick="buttonPush('delThread');">
        </logic:equal>
        <logic:equal name="bbs080Form" property="bbs080reply" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_THRE_REPLY_YES) %>">
        <input type="button" class="btn_henshin_n1" value="<gsmsg:write key="cmn.reply" />" onClick="buttonPush('addWrite');">
        </logic:equal>
        <logic:equal name="bbs080Form" property="bbs080reply" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_THRE_REPLY_NO) %>">
        <img src="../common/images/damy.gif" width="100" height="5">
        </logic:equal>
        <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backThreadList');">&nbsp;
        </td>
      </tr>
    </table>

    <table width="100%" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="250px" valign="top" align="left">


      <!-- フォーラム一覧・未読スレッドSTART -->

      <logic:notEmpty name="bbs080Form" property="forumList">

        <!-- フォーラム一覧 START-->
        <table width="250" border="1" class="tl0">
        <tr>
          <td width="100%" valign="top" align="left" class="tl0" style="border: solid 1px #cccccc;">
            <table width="100%" class="tl0 bbs_forum_header_left">
            <tr>
              <td width="0%"><img src="../bulletin/images/cate_icon01_bdr.gif" alt="<gsmsg:write key="bbs.1" />"></td>
              <td width="100%" align="left" nowrap><span class="text_base3"><gsmsg:write key="bbs.1" /></span></td>
            </tr>
            </table>
            <logic:iterate id="forumMdl" name="bbs080Form" property="forumList" indexId="index">
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
                    <a href="../bulletin/bbs060.do?bbs010forumSid=<bean:write name="forumMdl" property="bfiSid" />"><span class="<%= titleClass %>"><font size="-1">&shy;<bean:write name="forumMdl" property="bfiName" filter="false"/></font></span></a>
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
        <!-- フォーラム一覧END -->
      </logic:notEmpty>


        <!-- 未読スレッド一覧 START-->
      <logic:notEmpty name="bbs080Form" property="notReadThreadList">
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
              <table width="250" class="tl0 shintyaku_tbl2">
            <logic:iterate id="forumMdl" name="bbs080Form" property="notReadThreadList" indexId="index">
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
                  <td colspan="2" width="250px">&nbsp;・
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


      <!-- フォーラム一覧・未読スレッドEND -->


    </td>

    </td>
   <td width="0%"><img src="../common/images/spacer.gif" width="8" height="20" border="0" alt=""></td>

    <td width="100%" align="left" valign="top" nowrap>

        <table width="100%" cellpadding="3" cellspacing="0" class="tl0">
          <tr>
          <td width="0%" nowrap class="bbs_toukou_head_td">

            <table cellpadding="0" cellspacing="0">
              <tr>
              <logic:equal name="bbs080Form" property="bbs060BinSid" value="0">
                <td><img width="30" height="30" src="../bulletin/images/cate_icon01.gif" alt="<gsmsg:write key="bbs.3" />"></td>
              </logic:equal>
              <logic:notEqual name="bbs080Form" property="bbs060BinSid" value="0">
                <td><img width="30" height="30" src="../bulletin/bbs060.do?CMD=getImageFile&bbs010BinSid=<bean:write name="bbs080Form" property="bbs060BinSid" />&bbs010forumSid=<bean:write name="bbs080Form" property="bbs010forumSid" />" alt="<gsmsg:write key="bbs.3" />"></td>
              </logic:notEqual>
              <td nowrap></td>
              </tr>
            </table>

          </td>
          <td align="left" width="100%" class="bbs_toukou_head_td"><a href="../bulletin/bbs060.do?bbs010forumSid=<bean:write name="bbs080Form" property="bbs010forumSid" />"><span class="text_bbs1"><bean:write name="bbs080Form" property="bbs080forumName" /></span></a></td>
          </tr>

          <tr id="selectionSearchArea">
          <td width="0%" nowrap class="bbs_toukou_head_td">

            <table cellpadding="0" cellspacing="0">
              <tr>
              <td><img src="../bulletin/images/cate_icon02.gif" alt="<gsmsg:write key="bbs.2" />"></td>
              <td nowrap></td>
              </tr>
            </table>

          </td>
          <td align="left" width="100%" class="bbs_toukou_head_td"><span class="text_bbs2"><bean:write name="bbs080Form" property="bbs080threadTitle" /></span></td>
          </tr>

          <tr>
          <td width="0%" nowrap></td>
          <td align="left" width="100%">

            <table width="100%">
            <tr>
            <td>
              <span class="text_base"><gsmsg:write key="bbs.bbs080.2" /></span>&nbsp;
              <html:radio name="bbs080Form" styleId="wrtOrder_01" property="bbs080orderKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_WRTLIST_ORDER_ASC) %>" onclick="changeOrder();" /><label for="wrtOrder_01"><span class="text_base"><gsmsg:write key="cmn.order.asc" /></span></label>&nbsp;
              <html:radio name="bbs080Form" styleId="wrtOrder_02" property="bbs080orderKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_WRTLIST_ORDER_DESC) %>" onclick="changeOrder();" /><label for="wrtOrder_02"><span class="text_base"><gsmsg:write key="cmn.order.desc" /></span></label>
              &nbsp;&nbsp;<span class="text_base"><gsmsg:write key="bbs.11" /></span>&nbsp;
                <span class="text_p"><a href="javascript:openComWindow(<bean:write name="bbs080Form" property="bbs010forumSid" />, <bean:write name="bbs080Form" property="threadSid" />);"><bean:write name="bbs080Form" property="readedCnt" />&nbsp;/&nbsp;<bean:write name="bbs080Form" property="forumMemberCount" /></a></span>
            </td>
            <logic:notEmpty name="bbs080Form" property="bbsPageLabel">
            <td nowrap>
              <table class="tl1" width="100%" align="center" border="0" cellpadding="5">
              <tr>
                <td align="right" colspan="2">
                  <table width="100%" cellpadding="5" cellspacing="0">
                    <tr>
                    <td align="right">
                    <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('prevPage');">
                    <html:select property="bbs080page1" onchange="changePage(0);" styleClass="text_i">
                      <html:optionsCollection name="bbs080Form" property="bbsPageLabel" value="value" label="label" />
                    </html:select>
                    <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('nextPage');">
                    </td>
                    </tr>
                  </table>
                </td>
              </tr>
              </table>
            </td>
            </logic:notEmpty>
            </tr>
            </table>
          </td>
          </tr>
        </table>

        <logic:notEmpty name="bbs080Form" property="bbs080limitDate">
          <span class="text_base4"><gsmsg:write key="bbs.12" />:&nbsp;<bean:write name="bbs080Form" property="bbs080limitDate" /></span>
        </logic:notEmpty>


        <table width="100%">
        <tr>
        <td width="100%" valign="top" id="selectionSearchArea2">
          <table class="tl1" width="100%" align="center" border="0" cellpadding="">

          <logic:notEmpty name="bbs080Form" property="writeList">
          <logic:iterate id="writeMdl" name="bbs080Form" property="writeList" indexId="idx">
          <bean:define id="tdColor" value="" />
          <% String[] tdColors = new String[] {"td_type20", "td_type25_3"}; %>
          <% tdColor = tdColors[(idx.intValue() % 2)]; %>

          <tr>
          <td class="header_7D91BD_left" width="0%" nowrap><span class="text_base3"><gsmsg:write key="cmn.contributor" /></span></td>
          <td align="left" class="header_7D91BD_left" width="100%"><span class="text_left text_base3"><gsmsg:write key="cmn.content" /></span><span class="text_right text_base3"><gsmsg:write key="cmn.posted" />：<bean:write name="writeMdl" property="strBwiAdate" /></span></td>
          </tr>

          <tr valign="top">
          <td class="<%= tdColor %>" width="0%" nowrap>

<bean:define id="photoFileDsp" name="bbs080Form" property="photoFileDsp" type="java.lang.Integer" />
<bean:define id="registGrpSid" name="writeMdl" property="grpSid" type="java.lang.Integer" />
<% if (photoFileDsp.intValue() == jp.groupsession.v2.bbs.GSConstBulletin.BBS_IMAGE_DSP && registGrpSid.intValue() <= 0) { %>
            <table cellpadding="0" cellspacing="0" class="tl1 bbs_syashin" border="0">
              <tr>
                <logic:equal name="writeMdl" property="userJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
                  <td class="<%= tdColor %>" valign="top" align="center"><del><bean:write name="writeMdl" property="userName" /></del>&nbsp;</td>
                </logic:equal>
                <logic:notEqual name="writeMdl" property="userJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
                  <td class="<%= tdColor %>" valign="top" align="center"><a href="javaScript:void(0);" onClick="openUserInfoWindow(<bean:write name="writeMdl" property="userSid" />);"><bean:write name="writeMdl" property="userName" /></a>&nbsp;</td>
                </logic:notEqual>
              </tr>
              <tr>
                <logic:equal name="writeMdl" property="userPictKf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>">
                  <td class="td_type_bbs" width="130" align="center" valign="bottom" nowrap><span class="text_bbs_gray2"><gsmsg:write key="cmn.private" /></span></td>
                </logic:equal>
                <logic:notEqual name="writeMdl" property="userPictKf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>">
                  <logic:notEmpty name="writeMdl" property="photoFileName">
                    <td >
                      <a href="javaScript:void(0);" onClick="openUserInfoWindow(<bean:write name="writeMdl" property="userSid" />);">
                      <img src="../common/cmn100.do?CMD=getImageFile&cmn100binSid=<bean:write name="writeMdl" property="photoFileSid" />" name="userPhoto" alt="<gsmsg:write key="cmn.photo" />" border="0" width="130" onload="initImageView130('userPhoto');"></a></td>
                  </logic:notEmpty>
                  <logic:empty name="writeMdl" property="photoFileName">
                    <td width="130">
                      <a href="javaScript:void(0);" onClick="openUserInfoWindow(<bean:write name="writeMdl" property="userSid" />);">
                      <img src="../user/images/photo.gif" width="130" height="150" alt="<gsmsg:write key="cmn.photo" />" border="0"></a></td>
                  </logic:empty>
                </logic:notEqual>
              </tr>
            </table>
<% } else { %>
                <logic:equal name="writeMdl" property="userJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
                  <del><bean:write name="writeMdl" property="userName" /></del>&nbsp;
                </logic:equal>
                <logic:notEqual name="writeMdl" property="userJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
                  <% if (registGrpSid.intValue() > 0) { %>
                  <span class="text_bb1"><bean:write name="writeMdl" property="grpName" /></span>
                  <% } else { %>
                  <a href="javaScript:void(0);" onClick="openUserInfoWindow(<bean:write name="writeMdl" property="userSid" />);"><bean:write name="writeMdl" property="userName" /></a>&nbsp;
                  <% } %>
                </logic:notEqual>
<% } %>


          </td>
          <td align="left" valign="top" class="<%= tdColor %>" width="100%">
          <logic:equal name="writeMdl" property="newFlg" value="1">
          <img src="../bulletin/images/icon_new.gif" class="img_bottom" alt="new">&nbsp;
            <logic:notEqual name="writeMdl" property="writeUpdateFlg" value="1">
              <br><br>
            </logic:notEqual>
          </logic:equal>
          <logic:equal name="writeMdl" property="writeUpdateFlg" value="1">
          <bean:define id="edate" name="writeMdl" property="strBwiEdate" type="java.lang.String" />
          <span class="text_base4"><gsmsg:write key="bbs.bbs080.4" arg0="<%= edate %>" /></span>
          <br><br>
          </logic:equal>
          <bean:write name="writeMdl" property="bwiValueView" filter="false" />
          </td>
          </tr>

          <tr>
          <td class="<%= tdColor %>" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.attached" /></span></td>
          <td align="left" class="<%= tdColor %>" width="100%">
            <logic:empty name="writeMdl" property="tmpFileList">&nbsp;</logic:empty>
            <logic:notEmpty name="writeMdl" property="tmpFileList">
            <table cellpadding="0" cellpadding="0" border="0">

              <bean:define id="bbs080BwiSid" name="writeMdl" property="bwiSid"  type="java.lang.Integer" />

              <logic:iterate id="fileMdl" name="writeMdl" property="tmpFileList">

              <logic:equal name="bbs080Form" property="tempImageFileDsp" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_IMAGE_TEMP_DSP) %>">
                <logic:notEmpty name="fileMdl" property="binFileExtension">
                   <bean:define id="fext" name="fileMdl" property="binFileExtension"  type="java.lang.String" />
                   <%
                    String dext = ((String)pageContext.getAttribute("fext",PageContext.PAGE_SCOPE));
                    if (dext != null) {
                        dext = dext.toLowerCase();
                        if (jp.groupsession.v2.cmn.biz.CommonBiz.isViewFile(dext)) {
                    %>
                    <tr>
                    <td colspan="2">
                   <img src="../bulletin/bbs080.do?CMD=tempview&bbs010forumSid=<bean:write name="bbs080Form" property="bbs010forumSid" />&threadSid=<bean:write name="bbs080Form" property="threadSid" />&bbs080binSid=<bean:write name="fileMdl" property="binSid" />&bbs080writeSid=<bean:write name="bbs080BwiSid" />" name="pictImage<bean:write name="fileMdl" property="binSid" />" onload="initImageView('pictImage<bean:write name="fileMdl" property="binSid" />');">
                   </td>
                   </tr>
                    <%
                        }
                    }
                    %>
                </logic:notEmpty>
                </logic:equal>


              <tr>
              <td><img src="../common/images/file_icon.gif" alt="<gsmsg:write key="cmn.file" />"></td>
              <td class="menu_bun"><a href="javascript:fileLinkClick(<bean:write name="bbs080Form" property="bbs010forumSid" />, <bean:write name="bbs080Form" property="threadSid" />, <bean:write name="fileMdl" property="binSid" />, <bean:write name="bbs080BwiSid" />);"><span class="text_link"><bean:write name="fileMdl" property="binFileName" /><bean:write name="fileMdl" property="binFileSizeDsp" /></span></a></td>
              </tr>
              <tr>
              <td colspan="2">&nbsp;<br><br></td>
              </tr>
              </logic:iterate>

            </table>
            </logic:notEmpty>

          </td>
          </tr>

          <logic:equal name="bbs080Form" property="bbs080btnDspFlg" value="true">
          <tr>
          <td colspan="2" align="right" class="<%= tdColor %>" width="100%">

            <table width="0%">
              <tr>
              <td width="100%">&nbsp;</td>
              <logic:equal name="writeMdl" property="showBtnFlg" value="1">
                <logic:equal name="writeMdl" property="thdWriteFlg" value="1">
                  <logic:equal name="bbs080Form" property="bbs080ShowThreBtn" value="1">
                  <td><input type="button" class="btn_edit_n1" value="<gsmsg:write key="cmn.edit" />" onclick="buttnPushWrite('editThread', <bean:write name="writeMdl" property="bwiSid" />);"></td>
                  </logic:equal>
                </logic:equal>
                <logic:notEqual name="writeMdl" property="thdWriteFlg" value="1">
                  <td><input type="button" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onclick="buttnPushWrite('delWrite', <bean:write name='writeMdl' property='bwiSid' />);"></td>
                  <td>
                  <logic:equal name="bbs080Form" property="bbs080reply" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_THRE_REPLY_YES) %>">
                  <input type="button" class="btn_edit_n1" value="<gsmsg:write key="cmn.edit" />" onclick="buttnPushWrite('editWrite', <bean:write name='writeMdl' property='bwiSid' />);">
                  </logic:equal>
                  <logic:equal name="bbs080Form" property="bbs080reply" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_THRE_REPLY_NO) %>">
                  <img src="../common/images/damy.gif" width="100" height="5">
                  </logic:equal>
                  </td>
                </logic:notEqual>
              </logic:equal>
              <td>
              <logic:equal name="bbs080Form" property="bbs080reply" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_THRE_REPLY_YES) %>">
              <input type="button" class="btn_henshin_n1" value="<gsmsg:write key="bbs.bbs080.5" />" onclick="buttnPushWrite('inyouWrite', <bean:write name='writeMdl' property='bwiSid' />);">
              </logic:equal>
              <logic:equal name="bbs080Form" property="bbs080reply" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_THRE_REPLY_NO) %>">
              <img src="../common/images/damy.gif" width="100" height="5">
              </logic:equal>
              </td>
              </tr>
            </table>

          </td>
          </tr>
          </logic:equal>





          <tr>
          <td colspan="2" width="100%" align="left"><img src="../common/images/damy.gif" width="1" height="5"></td>
          </tr>

          </logic:iterate>
    <table cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td align="left">
          <logic:notEmpty name="bbs080Form" property="threadUrl">
            <table cellpadding="0" cellspacing="0" width="100%">
              <tr align="left">
                <td>
                  <span class="text_base"><font size="-1"><gsmsg:write key="bbs.2" />URL:</font></span><input type="text" value="<bean:write name="bbs080Form" property="threadUrl" />" class="text_theadurl" readOnly="true" style="width:515px;"/>
                </td>
              </tr>
            </table>
          </logic:notEmpty>
        </td>
      </tr>
      <tr>
        <td align="right" nowrap>
        <input type="button" value="<gsmsg:write key="cmn.pdf" />" class="btn_pdf_n1" onClick="buttonPush('pdf');">
        <logic:equal name="bbs080Form" property="bbs080ShowThreBtn" value="1">
        <input type="button" class="btn_dell_n1" value="<gsmsg:write key="bbs.bbs080.1" />" onClick="buttonPush('delThread');">
        </logic:equal>
        <logic:equal name="bbs080Form" property="bbs080reply" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_THRE_REPLY_YES) %>">
        <input type="button" class="btn_henshin_n1" value="<gsmsg:write key="cmn.reply" />" onClick="buttonPush('addWrite');">
        </logic:equal>
        <logic:equal name="bbs080Form" property="bbs080reply" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_THRE_REPLY_NO) %>">
        <img src="../common/images/damy.gif" width="100" height="5">
        </logic:equal>
        <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backThreadList');">&nbsp;
        </td>
      </tr>
    </table>
      </logic:notEmpty>

          <tr>
          <td colspan="2" align="right">
            <logic:empty name="bbs080Form" property="bbsPageLabel">&nbsp</logic:empty>
            <logic:notEmpty name="bbs080Form" property="bbsPageLabel">

            <table width="100%" cellpadding="5" cellspacing="0">
              <tr>
              <td align="right">
                <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('prevPage');">
                <html:select property="bbs080page2" onchange="changePage(1);" styleClass="text_i">
                  <html:optionsCollection name="bbs080Form" property="bbsPageLabel" value="value" label="label" />
                </html:select>
                <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('nextPage');">
              </td>
              </tr>
            </table>

            </logic:notEmpty>
          </td>
          </tr>

          </table>

        </td>

      </td>
      </tr>
      </table>
    </td>

    </tr>
    </table>

  </td>
  </tr>
  </table>

  <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">


    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">


  </td>
  </tr>
  </table>

</td>
</tr>
</table>

<IFRAME type="hidden" src="../common/html/damy.html" style="display: none" name="navframe"></IFRAME>

</html:form>
<logic:equal name="bbs080Form" property="bbs080searchUse" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.PLUGIN_USE) %>">
<span id="tooltip_search" class="tooltip_search"></span>
<span id="tooltip_search2" class="tooltip_search"></span>
</logic:equal>
<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>