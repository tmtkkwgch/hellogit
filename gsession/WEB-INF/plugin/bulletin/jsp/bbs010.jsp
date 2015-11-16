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
<title>[GroupSession] <gsmsg:write key="bbs.bbs010.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../bulletin/js/bbs010.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/imageView.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../bulletin/css/bulletin.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">

<html:form action="/bulletin/bbs010">
<input type="hidden" name="CMD" value="search">
<html:hidden name="bbs010Form" property="bbs010forumSid" />

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
        <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="bbs.1" /> ]</td>
        <td width="100%" class="header_white_bg">
          <logic:equal name="bbs010Form" property="bbs010AdminFlg" value="1">
            <input type="button" name="btn_prjadd" class="btn_setting_admin_n1" value="<gsmsg:write key="cmn.admin.setting" />" onClick="buttonPush('confMenu');">
          </logic:equal>
          <input type="button" name="btn_prjadd" class="btn_setting_n1" value="<gsmsg:write key="cmn.preferences2" />" onClick="buttonPush('personal');">
        </td>
        <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.bulletin" />"></td>
      </tr>
    </table>

    <table cellpadding="5" cellspacing="0" width="100%">
      <tr>
        <td align="right">
          <img src="../common/images/search.gif" alt="<gsmsg:write key="cmn.search" />" class="img_bottom">
          <html:text name="bbs010Form" property="s_key" maxlength="50" style="width:185px;"/>
          <input type="submit" name="btn_prjadd" class="btn_base1s" value="<gsmsg:write key="cmn.search" />" onClick="buttonPush('search');">
          <input type="button" name="btn_prjadd" class="btn_search_n1" value="<gsmsg:write key="cmn.advanced.search" />" onClick="buttonPush('searchDtl');">
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

    <table width="100%" class="tl0">
    <tr>

      <logic:notEmpty name="bbs010Form" property="shinThredList">

    <td width="250" valign="top" class="tl0">
      <table class="tl0"  style="border: solid 1px #cccccc;">
      <tr>
      <td>
        <table class="tl0 bbs_forum_header_left" width="250">
        <tr>

        <td width="0%">
          <img src="../bulletin/images/cate_icon02_bdr.gif" alt="<gsmsg:write key="bbs.2" />">
        </td>

        <td width="100%" nowrap>
          <span class="text_base3"><gsmsg:write key="bbs.bbs010.2" /></span>
        </td>

        </tr>
        </table>


        <logic:iterate id="thredMdl" name="bbs010Form" property="shinThredList" indexId="idy">
        <table class="tl0 shintyaku_tbl2 body_05" width="250px">
          <tr>
            <td align="left" width="100%">
              <table width="100%" cellpadding="" cellspacing="">
                <tr>
                  <%-- 未読・既読のスタイル --%>
                    <bean:define id="tdColor2" value="" />
                    <% String[] tdColors2 = new String[] {"td_type20", "td_type25_2"}; %>
                    <% tdColor2 = tdColors2[(idy.intValue() % 2)]; %>

                    <% String fTitleClass = "forum_link"; %>
                    <% String tTitleClass = "thred_link"; %>
                    <logic:equal name="thredMdl" property="f_ReadFlg" value="1">
                      <% fTitleClass = "forum_link2"; %>
                    </logic:equal>
                    <logic:equal name="thredMdl" property="t_ReadFlg" value="1">
                      <% tTitleClass = "thred_link2"; %>
                    </logic:equal>

                  <%-- フォーラム画像default --%>
                  <logic:equal name="thredMdl" property="imgBinSid" value="0">
                    <td class="text_left forum_img_top" width="20"><img width="20" height="20" src="../bulletin/images/cate_icon01.gif" alt="<gsmsg:write key="bbs.3" />"></td>
                  </logic:equal>

                  <%-- フォーラム画像original --%>
                  <logic:notEqual name="thredMdl" property="imgBinSid" value="0">
                    <td class="text_left forum_img_top" width="20">
                    <img width="20" height="20" src="../bulletin/bbs010.do?CMD=getImageFile&bbs010BinSid=<bean:write name="thredMdl" property="imgBinSid" />&bbs010ForSid=<bean:write name="thredMdl" property="bfiSid" />" alt="<gsmsg:write key="bbs.3" />"></td>
                  </logic:notEqual>

                  <%-- フォーラム名 --%>
                  <td class="forum_img_top" width="100%">
                  <a href="javascript:clickForum(<bean:write name="thredMdl" property="bfiSid" />);">
                  <span class="<%= fTitleClass %>"><bean:write name="thredMdl" property="bfiName" filter="false" /></span></a>
                  </td>

                </tr>
                <tr><td colspan="2">
                <a href="../bulletin/bbs080.do?bbs010forumSid=<bean:write name="thredMdl" property="bfiSid" />&threadSid=<bean:write name="thredMdl" property="btiSid" />">
                <span class="<%= tTitleClass %>"><bean:write name="thredMdl" property="btiTitle" filter="false" /></span></a></td></tr>
                <tr>
                  <td colspan="2" style="padding: 0!important;">
                  <table width="100%">
                    <tr>
                    <td class="text_base9_left">
                    <bean:define id="cbGrpSid" name="thredMdl" property="grpSid" type="java.lang.Integer" />
                    <% if (cbGrpSid.intValue() > 0) { %>
                      <logic:equal name="thredMdl" property="grpJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
                        <s><bean:write name="thredMdl" property="grpName" /></s>
                      </logic:equal>
                      <logic:notEqual name="thredMdl" property="grpJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
                        <bean:write name="thredMdl" property="grpName" />
                      </logic:notEqual>
                    <% } else { %>
                      <logic:equal name="thredMdl" property="userJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
                        <s><bean:write name="thredMdl" property="userName" /></s>
                      </logic:equal>
                      <logic:notEqual name="thredMdl" property="userJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
                        <bean:write name="thredMdl" property="userName" />
                      </logic:notEqual>
                    <% } %>
                    </td>
                    <td class="text_base9"><bean:write name="thredMdl" property="strWriteDate" /></td>
                    </tr>
                  </table>
                </tr>
              </table></td></tr>
        </table>
        </logic:iterate>
        </td>
        </tr>
        </table>
      </td>
      </logic:notEmpty>
      <td width="0%"><img src="../common/images/spacer.gif" width="10" height="20" border="0" alt=""></td>

      <td width="100%" valign="top" align="right">


        <logic:notEmpty name="bbs010Form" property="forumList">
        <bean:size id="count1" name="bbs010Form" property="bbsPageLabel" scope="request" />
        <logic:greaterThan name="count1" value="1">
        <table width="100%" cellpadding="5" cellspacing="0">
          <tr>
          <td align="right">
            <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('prevPage');">
            <html:select property="bbs010page1" onchange="changePage(0);" styleClass="text_i">
              <html:optionsCollection name="bbs010Form" property="bbsPageLabel" value="value" label="label" />
            </html:select>
            <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('nextPage');">
          </td>
          </tr>
        </table>
        </logic:greaterThan>
        </logic:notEmpty>

        <table width="100%">
          <logic:notEmpty name="bbs010Form" property="forumList">
          <logic:iterate id="forumMdl" name="bbs010Form" property="forumList" indexId="idx">
            <bean:define id="tdColor" value="" />
            <% String[] tdColors = new String[] {"td_type20", "td_type25_2"}; %>
            <% tdColor = tdColors[(idx.intValue() % 2)]; %>

            <% String titleClass = "text_title4"; %>
            <% String valueClass = "sc_ttl_sat"; %>
            <logic:equal name="forumMdl" property="readFlg" value="1">
              <% titleClass = "text_title2"; %>
              <% valueClass = "text_p"; %>
            </logic:equal>

          <tr>
            <td class="bbs_title_back forum_img" width="30px">
              <%-- フォーラム画像default --%>
              <logic:equal name="forumMdl" property="imgBinSid" value="0">
              <a href="javascript:clickForum(<bean:write name="forumMdl" property="bfiSid" />);" class="text_left">
                <img width="30" height="30" src="../bulletin/images/cate_icon01.gif" alt="<gsmsg:write key="bbs.3" />">
              </a>
              </logic:equal>

              <%-- フォーラム画像original --%>
              <logic:notEqual name="forumMdl" property="imgBinSid" value="0">
              <a href="javascript:clickForum(<bean:write name="forumMdl" property="bfiSid" />);" class="text_left">
                <img width="30" height="30" src="../bulletin/bbs010.do?CMD=getImageFile&bbs010BinSid=<bean:write name="forumMdl" property="imgBinSid" />&bbs010ForSid=<bean:write name="forumMdl" property="bfiSid" />" alt="<gsmsg:write key="bbs.3" />">
              </a>
              </logic:notEqual>
            </td>
           <td class="bbs_title_back forum_title" width="100%">
               <%-- フォーラムタイトル --%>
                <div id="forum_tit_middle">
                <a href="javascript:clickForum(<bean:write name="forumMdl" property="bfiSid" />);">
                <span class="<%= titleClass %>"><bean:write name="forumMdl" property="bfiName" /></span></a>


              <%-- フォーラムnew画像 --%>
                <logic:equal name="forumMdl" property="newFlg" value="1">
                  <br><span class="forum_img_new"><img src="../bulletin/images/icon_new.gif" width="30" height="15" alt="new<gsmsg:write key="cmn.icon" />" border="1"></span>
                </logic:equal>
                </div>

            </td>
            <td class="bbs_title_back forum_title_right" nowrap>
            <div id="forum_tit_right">
              <div class="text_base8" align="right">
              <a href="javascript:clickForum(<bean:write name="forumMdl" property="bfiSid" />);">
              <span class="<%= titleClass %>">
              <font size="-1">
              <gsmsg:write key="bbs.2" />:<bean:write name="forumMdl" property="bfsThreCnt" />
              <gsmsg:write key="bbs.16" />:<bean:write name="forumMdl" property="writeCnt" />
              </font>
              </span>
              </a>
              </div>
              <div class="text_base8" align="right">
              <gsmsg:write key="cmn.member" />:<a href="#" onclick="clickMemBtn(<bean:write name="forumMdl" property="bfiSid"/>);"><img src="../common/images/groupicon.gif" alt="<gsmsg:write key="cmn.member" />" width="20" height="20" class="img_bottom"></a>
              </div>
            </div>

            </td>
          </tr>
          <tr>
            <td class="text_base8" align="left" colspan="3">
              <%-- フォーラムコメント --%>
              <bean:write name="forumMdl" property="bfiCmtView" filter="false" />
            </td>
          </tr>
          <tr>
            <td class="bbs_bottom_border" colspan="3">
              <div class="text_base8" style="float:left"><gsmsg:write key="bbs.bbs010.3" />:<bean:write name="forumMdl" property="strWriteDate" />
              </div>
              <div class="text_base8" style="float:right">
              <logic:greaterThan name="forumMdl" property="rsvThreCnt" value="0">
              <a href="javascript:clickRsvThread(<bean:write name="forumMdl" property="bfiSid" />);"><span class="thred_link">掲示予定のスレッドが<bean:write name="forumMdl" property="rsvThreCnt" />件あります。</span></a>
              </logic:greaterThan>
              </div>
          </td>
          </tr>
          <td width="0%"><img src="../common/images/spacer.gif" width="10" height="10" border="0" alt=""></td>
          </logic:iterate>
          </logic:notEmpty></td>
        </table>

      <logic:notEmpty name="bbs010Form" property="forumList">

    <bean:size id="count2" name="bbs010Form" property="bbsPageLabel" scope="request" />
    <logic:greaterThan name="count2" value="1">
    <table width="100%" cellpadding="5" cellspacing="0">
      <tr>
      <td align="right">
        <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('prevPage');">
        <html:select property="bbs010page2" onchange="changePage(1);" styleClass="text_i">
          <html:optionsCollection name="bbs010Form" property="bbsPageLabel" value="value" label="label" />
        </html:select>
        <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('nextPage');">
      </td>
      </tr>
    </table>
    </logic:greaterThan>
    </logic:notEmpty>
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