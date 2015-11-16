<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="bmk.bmk070.07" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/userpopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/imageView.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../bookmark/js/bmk070.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../bookmark/css/bookmark.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">

<html:form action="/bookmark/bmk070">
<input type="hidden" name="CMD" value="">

<html:hidden property="procMode" />
<html:hidden property="editBmuSid" />
<html:hidden property="editBmkSid" />
<html:hidden property="returnPage" />

<html:hidden property="bmk010mode" />

<span id="userBookmark">
<html:hidden property="bmk010groupSid" />
<html:hidden property="bmk010orderKey" />
<html:hidden property="bmk010sortKey" />
<html:hidden property="bmk010searchLabel" />
<logic:notEmpty name="bmk070Form" property="bmk010delInfSid" scope="request">
<logic:iterate id="item" name="bmk070Form" property="bmk010delInfSid" scope="request">
  <input type="hidden" name="bmk010delInfSid" value="<bean:write name="item"/>">
</logic:iterate>
</logic:notEmpty>
</span>

<html:hidden property="bmk010userSid" />
<html:hidden property="bmk010page" />
<html:hidden property="bmk070SortKey" />
<html:hidden property="bmk070OrderKey" />
<html:hidden property="bmk070ReturnPage" />
<html:hidden property="bmk080Page" />
<html:hidden property="bmk080PageTop" />
<html:hidden property="bmk080PageBottom" />

<html:hidden property="bmk150PageNum" />
<html:hidden property="bmk070ToBmk150DspFlg" />

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
        <td width="0%"><img src="../bookmark/images/header_link01.gif" border="0" alt="<gsmsg:write key="bmk.43" />"></td>
        <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="bmk.43" /></span></td>
        <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="bmk.bmk070.07" /> ]</td>
        <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="bmk.43" />"></td>
      </tr>
    </table>

    <table cellpadding="5" cellspacing="0" width="100%">
      <tr>
        <td align="right">
          <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('bmk070back');"></span>
        </td>
      </tr>
    </table>

    <logic:messagesPresent message="false">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr><td align="left"><html:errors/></td></tr>
    </table>
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
    </logic:messagesPresent>

    <logic:notEmpty name="bmk070Form" property="bmk070ResultUrl">
    <table class="bookmarklist" cellpadding="0" cellspacing="0" border="0" width="100%">

        <tr><th>
        <bean:define id="resultUrl" name="bmk070Form" property="bmk070ResultUrl" />

        <table class="comment" cellpadding="0" cellspacing="0" border="0" width="100%">
        <tr>

        <td width="100%">
        <a href="<bean:write name="resultUrl" property="bmuUrl" />" target="_blank">
        <span class="text_link">
          <bean:write name="resultUrl" property="bmuTitle" />
        </span>
        </a><br>

        <div nowrap>
        <small>
          <logic:notEmpty name="resultUrl" property="bmkUrlDspList">
          <logic:iterate id="urlDsp" name="resultUrl" property="bmkUrlDspList" indexId="idx3">
          <% if (idx3 > 0) { %> <br> <% } %>
          <bean:write name="urlDsp"/>
          </logic:iterate>
          </logic:notEmpty>
        </small>
        </div>
        </td>

        <bean:define id="userCnt" name="resultUrl" property="userCount" type="java.lang.Integer" />
        <td class="small" align="center" width="0%" nowrap><gsmsg:write key="bmk.bmk070.05" /> <gsmsg:write key="bmk.23" arg0="<%= String.valueOf(userCnt.intValue()) %>" /></td>

        <logic:equal name="bmk070Form" property="bmk070NotViewBmk" value="0">
            <td class="small" align="center" width="0%" nowrap>
            <gsmsg:write key="bmk.bmk070.01" />
            <logic:equal name="resultUrl" property="scoreAvg" value="1"><img src="../bookmark/images/icon_star1.gif" class="img_bottom" alt="<gsmsg:write key="bmk.11" />"></logic:equal>
            <logic:equal name="resultUrl" property="scoreAvg" value="2"><img src="../bookmark/images/icon_star2.gif" class="img_bottom" alt="<gsmsg:write key="bmk.10" />"></logic:equal>
            <logic:equal name="resultUrl" property="scoreAvg" value="3"><img src="../bookmark/images/icon_star3.gif" class="img_bottom" alt="<gsmsg:write key="bmk.09" />"></logic:equal>
            <logic:equal name="resultUrl" property="scoreAvg" value="4"><img src="../bookmark/images/icon_star4.gif" class="img_bottom" alt="<gsmsg:write key="bmk.08" />"></logic:equal>
            <logic:equal name="resultUrl" property="scoreAvg" value="5"><img src="../bookmark/images/icon_star5.gif" class="img_bottom" alt="<gsmsg:write key="bmk.07" />"></logic:equal>
            </td>
        </logic:equal>

        <td align="center" width="0%">

        <logic:equal name="bmk070Form" property="editBmkSid" value="-1">
          <logic:empty name="bmk070Form" property="bmk070ReturnPage">
            <input type="button" name="add" style="border:0px;font-size:10px;color:#4e4e4e;width:26px;height:26px;padding-right:0px;padding-left:0px;padding-top:12px;font-weight:bold;" class="bmk_add_btn" value="<gsmsg:write key="cmn.add" />" onClick="return bmkAdd(<bean:write name="resultUrl" property="bmuSid" />, 0);">
          </logic:empty>
          <logic:equal name="bmk070Form" property="bmk070ReturnPage" value="bmk070">
            <input type="button" name="add" style="border:0px;font-size:10px;color:#4e4e4e;width:26px;height:26px;padding-right:0px;padding-left:0px;padding-top:12px;font-weight:bold;" class="bmk_add_btn" value="<gsmsg:write key="cmn.add" />" onClick="return bmkAdd(<bean:write name="resultUrl" property="bmuSid" />, 0);">
          </logic:equal>
          <logic:equal name="bmk070Form" property="bmk070ReturnPage" value="bmk150">
            <input type="button" name="add" style="border:0px;font-size:10px;color:#4e4e4e;width:26px;height:26px;padding-right:0px;padding-left:0px;padding-top:12px;font-weight:bold;" class="bmk_add_btn" value="<gsmsg:write key="cmn.add" />" onClick="return bmkAdd(<bean:write name="resultUrl" property="bmuSid" />, 1);">
          </logic:equal>
        </logic:equal>
        <logic:notEqual name="bmk070Form" property="editBmkSid" value="-1">
          <logic:empty name="bmk070Form" property="bmk070ReturnPage">
            <input type="button" name="add" style="border:0px;width:26px;height:26px;" class="bmk_edit_btn" value="" onClick="return bmkEdit(<bean:write name="bmk070Form" property="editBmkSid" />, 0);">
          </logic:empty>
          <logic:equal name="bmk070Form" property="bmk070ReturnPage" value="bmk070">
            <input type="button" name="add" style="border:0px;width:26px;height:26px;" class="bmk_edit_btn" value="" onClick="return bmkEdit(<bean:write name="bmk070Form" property="editBmkSid" />, 0);">
          </logic:equal>
          <logic:equal name="bmk070Form" property="bmk070ReturnPage" value="bmk150">
            <input type="button" name="add" style="border:0px;width:26px;height:26px;" class="bmk_edit_btn" value="" onClick="return bmkEdit(<bean:write name="bmk070Form" property="editBmkSid" />, 1);">
          </logic:equal>
        </logic:notEqual>

        </tr>
        </table>

        </th></tr>
    </table>

    </logic:notEmpty>

    <logic:notEmpty name="bmk070Form" property="bmk070ResultList">
    <bean:size id="count1" name="bmk070Form" property="bmk070PageLabelList" scope="request" />
    <logic:greaterThan name="count1" value="1">
    <table width="100%" cellpadding="5" cellspacing="0">
      <td align="right">
        <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" width="20" height="20" onClick="buttonPush('prevPage');">
        <html:select property="bmk070PageTop" onchange="changePage(0);" styleClass="text_i">
          <html:optionsCollection name="bmk070Form" property="bmk070PageLabelList" value="value" label="label" />
        </html:select>
        <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" onClick="buttonPush('nextPage');"></td>
      </tr>
    </table>
    </logic:greaterThan>
    </logic:notEmpty>

    <table class="tl1" width="100%" align="center" border="0" cellpadding="5">
      <tr>
      <td class="td_type3" width="0%" nowrap><span class="text_base3"><gsmsg:write key="cmn.contributor" /></span></td>
      <td align="left" class="td_type3" width="100%">
      <span class="text_base3" style="float:left;"><gsmsg:write key="cmn.content" /></span>
      <span style="float:right;" class="sort_navi_text">

      <bean:define id="sortKey" name="bmk070Form" property="bmk070SortKey" type="java.lang.Integer" />
      <bean:define id="orderKey" name="bmk070Form" property="bmk070OrderKey" type="java.lang.Integer" />

      <!-- 登録順 -->
      <% if (sortKey.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.SORTKEY_ADATE && orderKey.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.ORDERKEY_ASC) { %>
          <strong>
          <a href="#" onClick="return sortChange(<%= jp.groupsession.v2.bmk.GSConstBookmark.SORTKEY_ADATE %>, <%= jp.groupsession.v2.bmk.GSConstBookmark.ORDERKEY_DESC %>);" style="color:black;font-size:13px;">
          ▲<gsmsg:write key="bmk.17" />
          </a>
          </strong>
      <% } else if (sortKey.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.SORTKEY_ADATE && orderKey.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.ORDERKEY_DESC) { %>
          <strong>
          <a href="#" onClick="return sortChange(<%= jp.groupsession.v2.bmk.GSConstBookmark.SORTKEY_ADATE %>, <%= jp.groupsession.v2.bmk.GSConstBookmark.ORDERKEY_ASC %>);" style="color:black;font-size:13px;">
          ▼<gsmsg:write key="bmk.17" />
          </a>
          </strong>
      <% } else { %>
          <a href="#" onClick="return sortChange(<%= jp.groupsession.v2.bmk.GSConstBookmark.SORTKEY_ADATE %>, <%= jp.groupsession.v2.bmk.GSConstBookmark.ORDERKEY_ASC %>);" style="color:#0000ff;font-size:80%;">
          <gsmsg:write key="bmk.17" />
          </a>
      <% } %>
      ／
      <!-- 評価順 -->
      <% if (sortKey.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.SORTKEY_SCORE && orderKey.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.ORDERKEY_ASC) { %>
          <strong>
          <a href="#" onClick="return sortChange(<%= jp.groupsession.v2.bmk.GSConstBookmark.SORTKEY_SCORE %>, <%= jp.groupsession.v2.bmk.GSConstBookmark.ORDERKEY_DESC %>);" style="color:black;font-size:13px;">
          ▲<gsmsg:write key="bmk.06" />
          </a>
          </strong>
      <% } else if (sortKey.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.SORTKEY_SCORE && orderKey.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.ORDERKEY_DESC) { %>
          <strong>
          <a href="#" onClick="return sortChange(<%= jp.groupsession.v2.bmk.GSConstBookmark.SORTKEY_SCORE %>, <%= jp.groupsession.v2.bmk.GSConstBookmark.ORDERKEY_ASC %>);" style="color:black;font-size:13px;">
          ▼<gsmsg:write key="bmk.06" />
          </a>
          </strong>
      <% } else { %>
          <a href="#" onClick="return sortChange(<%= jp.groupsession.v2.bmk.GSConstBookmark.SORTKEY_SCORE %>, <%= jp.groupsession.v2.bmk.GSConstBookmark.ORDERKEY_ASC %>);" style="color:#0000ff;font-size:80%;">
          <gsmsg:write key="bmk.06" />
          </a>
      <% } %>
      ／
      <!-- 投稿者順 -->
      <% if (sortKey.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.SORTKEY_AUID && orderKey.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.ORDERKEY_ASC) { %>
          <strong>
          ▲<a href="#" onClick="return sortChange(<%= jp.groupsession.v2.bmk.GSConstBookmark.SORTKEY_AUID %>, <%= jp.groupsession.v2.bmk.GSConstBookmark.ORDERKEY_DESC %>);" style="color:black;font-size:13px;">
          <gsmsg:write key="cmn.contributor.order" />
          </a>
          </strong>
      <% } else if (sortKey.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.SORTKEY_AUID && orderKey.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.ORDERKEY_DESC) { %>
          <strong>
          <a href="#" onClick="return sortChange(<%= jp.groupsession.v2.bmk.GSConstBookmark.SORTKEY_AUID %>, <%= jp.groupsession.v2.bmk.GSConstBookmark.ORDERKEY_ASC %>);" style="color:black;font-size:13px;">
          ▼<gsmsg:write key="cmn.contributor.order" />
          </a>
          </strong>
      <% } else { %>
          <a href="#" onClick="return sortChange(<%= jp.groupsession.v2.bmk.GSConstBookmark.SORTKEY_AUID %>, <%= jp.groupsession.v2.bmk.GSConstBookmark.ORDERKEY_ASC %>);" style="color:#0000ff;font-size:80%;">
          <gsmsg:write key="cmn.contributor.order" />
          </a>
      <% } %>
      </span>
      </td>
      </tr>

      <!-- コメント・評価リスト一覧 -->
      <logic:notEmpty name="bmk070Form" property="bmk070ResultList">
      <logic:iterate id="resultMdl" name="bmk070Form" property="bmk070ResultList" indexId="idx">
          <bean:define id="tdColor" value="" />
          <% String[] tdColors = new String[] {"td_type20", "td_type25_3"}; %>
          <% tdColor = tdColors[(idx.intValue() % 2)]; %>
          <tr valign="top">
          <td class="<%= tdColor %>" width="0%" nowrap>
            <table cellpadding="0" cellspacing="0" class="tl1">
            <tr>
                <td class="td_type20" valign="top" align="center"><a href="javaScript:void(0);" onClick="openUserInfoWindow(<bean:write name="resultMdl" property="usrSid" />);"><bean:write name="resultMdl" property="usrName" /></a>&nbsp;</td>
            </tr>
            <tr>
                <logic:equal name="resultMdl" property="userPictKf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>">
                    <td class="td_type_bbs" width="130" align="center" valign="bottom" nowrap><span class="text_bbs_gray2"><gsmsg:write key="cmn.private" /></span></td>
                </logic:equal>

                <logic:notEqual name="resultMdl" property="userPictKf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>">
                    <logic:notEmpty name="resultMdl" property="photoFileName">
                        <td class="td_type20">
                        <a href="javaScript:void(0);" onClick="openUserInfoWindow(<bean:write name="resultMdl" property="usrSid" />);">
                        <img src="../common/cmn100.do?CMD=getImageFile&cmn100binSid=<bean:write name="resultMdl" property="photoFileSid" />" name="userPhoto" alt="<gsmsg:write key="cmn.photo" />" border="0" width="130" onload="initImageView130('userPhoto');"></a></td>
                    </logic:notEmpty>
                    <logic:empty name="resultMdl" property="photoFileName">
                        <td class="td_type20" width="130">
                        <a href="javaScript:void(0);" onClick="openUserInfoWindow(<bean:write name="resultMdl" property="usrSid" />);">
                        <img src="../user/images/photo.gif" width="130" height="150" alt="<gsmsg:write key="cmn.photo" />" border="0"></a></td>
                    </logic:empty>
                </logic:notEqual>
            </tr>

            <tr>
            <td style="padding:5px 0px 0px 0px;" align="center">
            <input type="button" value="<gsmsg:write key="bmk.30" />" class="btn_base1" onClick="return buttonPushUser(<bean:write name="resultMdl" property="usrSid" />);" style="WIDTH: 112px;">
            </td>
            </tr>
            </table>
          </td>
          <td align="left" valign="top" class="<%= tdColor %>" width="100%">
          <logic:equal name="resultMdl" property="bmkScore" value="1"><img src="../bookmark/images/icon_star1.gif" class="img_bottom" alt="<gsmsg:write key="bmk.11" />"><br><br></logic:equal>
          <logic:equal name="resultMdl" property="bmkScore" value="2"><img src="../bookmark/images/icon_star2.gif" class="img_bottom" alt="<gsmsg:write key="bmk.10" />"><br><br></logic:equal>
          <logic:equal name="resultMdl" property="bmkScore" value="3"><img src="../bookmark/images/icon_star3.gif" class="img_bottom" alt="<gsmsg:write key="bmk.09" />"><br><br></logic:equal>
          <logic:equal name="resultMdl" property="bmkScore" value="4"><img src="../bookmark/images/icon_star4.gif" class="img_bottom" alt="<gsmsg:write key="bmk.08" />"><br><br></logic:equal>
          <logic:equal name="resultMdl" property="bmkScore" value="5"><img src="../bookmark/images/icon_star5.gif" class="img_bottom" alt="<gsmsg:write key="bmk.07" />"><br><br></logic:equal>
          <!-- コメント -->
          <span><bean:write name="resultMdl" property="bmkCmt" filter="false"/></span>
          <p class="bookmark_tag">
          <!-- ラベル -->
          <span><gsmsg:write key="cmn.label" /><gsmsg:write key="wml.215" /><bean:write name="resultMdl" property="labelName" /></span><br><br>
          <!-- 登録日 -->
          <span><gsmsg:write key="bmk.15" /><gsmsg:write key="wml.215" /><bean:write name="resultMdl" property="strBmkAdate" /></span>
          </p>
          </td>
          </tr>
          <tr><td>&nbsp;</td></tr>
      </logic:iterate>
      </logic:notEmpty>

    </table>

    <logic:notEmpty name="bmk070Form" property="bmk070ResultList">
    <bean:size id="count2" name="bmk070Form" property="bmk070PageLabelList" scope="request" />
    <logic:greaterThan name="count2" value="1">
    <table width="100%" cellpadding="5" cellspacing="0">
      <td align="right">
        <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('prevPage');">
        <html:select property="bmk070PageBottom" onchange="changePage(1);" styleClass="text_i">
          <html:optionsCollection name="bmk070Form" property="bmk070PageLabelList" value="value" label="label" />
        </html:select>
        <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('nextPage');"></td>
      </tr>
    </table>
    </logic:greaterThan>
    </logic:notEmpty>

    <table cellpadding="5" cellspacing="0" width="100%">
      <tr>
        <td align="right">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('bmk070back');"></span>
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