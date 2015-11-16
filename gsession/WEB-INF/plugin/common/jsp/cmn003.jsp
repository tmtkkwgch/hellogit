<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String key = jp.groupsession.v2.cmn.GSConst.SESSION_KEY; %>
<% String gsurl = "../main/man001.do"; %>
<logic:equal name="cmn003Form" property="cmn003SysAdminFlg" value="true">
  <% gsurl = "../main/man002.do"; %>
</logic:equal>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.cmn003.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../common/js/jquery.jcarousel.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/cmn003.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/search.js?<%= GSConst.VERSION_PARAM %>"></script>

<link rel=stylesheet href="../main/css/skins/jts/skin.css?<%= GSConst.VERSION_PARAM %>" type='text/css'>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type='text/css'>
<theme:css filename="theme.css"/>
</head>

<body class="body_02">


  <!-- ログインユーザ名 -->
<div id="menu_username">
  <span class="login_user">&nbsp;<img src="../common/images/user_icon_s.gif" alt="<gsmsg:write key="cmn.user" />" styleClass="img_bottom" height="12px">&nbsp;
  <logic:notEmpty name="<%= key %>" scope="session">
    <bean:write name="<%= key %>" scope="session" property="usisei" />&nbsp;&nbsp;
    <bean:write name="<%= key %>" scope="session" property="usimei" /> | <img src="../common/images/logout_s.gif" alt="<gsmsg:write key="cmn.user" />" styleClass="img_bottom" height="12px">&nbsp;
    <a href="./cmn001.do?CMD=logout" target="_top"><gsmsg:write key="mobile.11" /></a>
      </logic:notEmpty>
  <logic:empty name="<%= key %>" scope="session">
  </logic:empty>
  </span>
</div>

<span>
  <logic:equal name="cmn003Form" property="cmn003LogoBinSid" value="0">
    <div><a href="<%= gsurl %>" target="body" border="0"  class="gs-logo">GroupSession</a></div>
    <div><a href="<%= gsurl %>" target="body" border="0"  class="gs-logo2">GroupSession</a></div>
  </logic:equal>
  <logic:notEqual name="cmn003Form" property="cmn003LogoBinSid" value="0">
    <div><a href="<%= gsurl %>" target="body" border="0"  style="background-image:url(../common/cmn003.do?CMD=getLogoImageFile&cmn003LogoBinSid=<bean:write name="cmn003Form" property="cmn003LogoBinSid" />);" class="gs_user_logo">GroupSession</a></div>
    <div><a href="<%= gsurl %>" target="body" border="0"  class="gs-logo2">GroupSession</a></div>
  </logic:notEqual>
</span>

<table width="100%" border="0" cellpadding="0" cellspacing="0" valign="middle">
<tr>
<td rowspan="3"><img height="1" src="../common/images/damy.gif" width="1" alt="<gsmsg:write key="cmn.space" />"></td>
<td rowspan="3" id="td-container" width="100%" align="center">
<bean:define id="kusr" name="<%= key %>" scope="session" />
<bean:define id="cmnMaxPage" name="cmn003Form" property="cmn003maxPage" />
<% int menuMaxPage = ((Integer) cmnMaxPage).intValue(); %>

<table>
<tr>
<td>

<html:form action="/common/cmn002" target="_parent">
<input type="hidden" name="url" value="">
<html:hidden name="cmn003Form" property="menuPage" />
  <ul id="mycarousel" class="jcarousel-skin-jts">

    <!-- ICON -->
    <logic:equal name="kusr" property="usrsid" value="0">
    <!-- 管理者(初期作成管理者)メニュー -->
    <li>
    <table valign="middle" border="0" cellpadding="0" cellspacing="0">
      <tr>
      <td>
        <html:link action="/main/man002" target="body">
          <div class="menu_bg">
            <table>
              <tbody><tr>
                <td><img name="mainMenuImg" alt="<gsmsg:write key="cmn.main" />" class="menu_img" src="../main/images/menu_icon_single.gif" width="25" border="0" height="25"></td>
                <td class="menu_text menu_text_position3"><b><gsmsg:write key="cmn.main" /></b></td>
              </tr>
            </tbody></table>
          </div>
        </html:link>
      </td>

      <td><div class="menu_no"></div></td>
      <td><div class="menu_no"></div></td>
      <td><div class="menu_no"></div></td>
      <td><div class="menu_no"></div></td>
      <td><div class="menu_no"></div></td>
      <td><div class="menu_no"></div></td>
      <td><div class="menu_no"></div></td>
      </tr>

      <tr>
      <td><div class="menu_no"></div></td>
      <td><div class="menu_no"></div></td>
      <td><div class="menu_no"></div></td>
      <td><div class="menu_no"></div></td>
      <td><div class="menu_no"></div></td>
      <td><div class="menu_no"></div></td>
      <td><div class="menu_no"></div></td>
      <td><div class="menu_no"></div></td>
      </tr>
    </table>
    </li>
    </ul>
    </logic:equal>

    <logic:notEqual name="kusr" property="usrsid" value="0">
    <!-- ユーザメニュー -->

      <bean:define id="menuInfoList" name="cmn003Form" property="menuInfoList" />
      <% java.util.List menuList = (java.util.List) menuInfoList; %>
      <% int menuSize = menuList.size(); %>
      <% jp.groupsession.v2.cmn.cmn003.MenuInfo menuInfo = null; %>

      <% int pageNum = 1; %>
      <% int sixTeenCnt = 1; %>
      <% int eightCnt = 1; %>
      <% for (int menuCount = 1; menuCount <= menuMaxPage * 16; menuCount++) { %>

      <% if (menuCount == 1 || sixTeenCnt == 1) { %>
      <li align="left" >
      <table valign="middle" border="0" cellpadding="0" cellspacing="0">
      <tr>
      <% } %>

      <% if (eightCnt == 1 && sixTeenCnt != 1) { %>
      <tr>
      <% } %>

      <% if (menuCount <= menuSize) { %>
      <% menuInfo = (jp.groupsession.v2.cmn.cmn003.MenuInfo) menuList.get(menuCount - 1); %>
      <% String pluginId = menuInfo.getPluginId(); %>
      <% String name = menuInfo.getName(); %>
      <% String menuUrl = menuInfo.getUrl(); %>
      <% Integer menuTarget = menuInfo.getTarget(); %>
      <% int pluginKbn = menuInfo.getPluginKbn(); %>
      <% Long binSid = menuInfo.getBinSid(); %>

      <% int paramKbn = menuInfo.getParamKbn(); %>
      <% int sendKbn = menuInfo.getSendKbn(); %>

      <% int strLen = menuInfo.getName().length(); %>
      <% if (strLen == 7) { %>
      <%    name = name.substring(0, 4) + "<br/>" + name.substring(4); %>
      <% } else if (strLen >= 8) { %>
      <%    strLen = 8; %>
      <%    name = name.substring(0, 5) + "<br/>" + name.substring(5); %>
      <% } %>

      <td width="100px" height="30px">
        <% if (menuTarget == 0) { %>
            <% if (pluginKbn != 0) { %>
            <a id="<%= pluginId %>" class="user_plugin_link" href="../common/cmn002.do?url=<%=menuUrl%>" target="body" onclick="clickMenuTarFrame('<%= pluginId %>','<%= paramKbn %>','<%= sendKbn %>');">
            <% } else { %>
            <a href="../common/cmn002.do?url=<%=menuUrl%>" target="body" onclick="clickMenuGs('<%= menuUrl %>');">
            <% } %>
        <% } else { %>
            <a id="<%= pluginId %>" class="user_plugin_link" href="<%=menuUrl%>" onclick="clickMenuTarWindow('<%= pluginId %>','<%= paramKbn %>','<%= sendKbn %>'); return false;">
        <% } %>
        <div class="menu_bg">
          <table>
            <tr>
              <% if (pluginKbn != 0) { %>
                <% if (binSid != 0) { %>
                <td><img name="<%= pluginId %>MenuImg" alt="<%= name %>" class="menu_img" height="25" width="25" src="../common/cmn003.do?CMD=getImageFile&cmn003PluginId=<%= pluginId %>&cmn003BinSid=<%= binSid %>" border="0" /></td>
                <% } else { %>
                <td><img name="<%= pluginId %>MenuImg" alt="<%= name %>" class="menu_img" height="25" width="25" src="../common/images/plugin_default.gif" border="0" /></td>
                <% } %>
              <% } else { %>
              <td><img name="<%= pluginId %>MenuImg" alt="<%= name %>" class="menu_img" height="25" width="25" src="../<%= pluginId %>/images/menu_icon_single.gif" border="0" /></td>
              <% } %>
              <% if (strLen == 7) { %>
              <td><table><tr><td class="str_7width"></td><td class="menu_text menu_text_position<%= strLen %>"><b><%= name %></b></td></tr></table></td>
              <% } else { %>
              <td class="menu_text menu_text_position<%= strLen %>"><b><%= name %></b></td>
              <% } %>
            </tr>
          </table>
        </div>
        </a>
      </td>
      <% } else { %>
        <td><div class="menu_no"></div></td>
      <% } %>

      <% if (sixTeenCnt == 16) { %>
      <% sixTeenCnt = 0; %>
      <% eightCnt = 0; %>
      </tr>
      </table>
      </li>
      <% } %>

      <% if (eightCnt == 8 && sixTeenCnt != 0) { %>
      <% eightCnt = 0; %>
      </tr>
      <% } %>

      <% sixTeenCnt = sixTeenCnt + 1; %>
      <% eightCnt = eightCnt + 1; %>
      <% } %>

  </ul>
</td>

<% if (menuMaxPage > 1) { %>
    <td><IMG SRC="../common/images/spacer.gif" width="10px" height="10px" border="0" style="visibility:hidden"></td>
    <td>
      <table border="0" cellpadding="0" cellspacing="0" style="position:absolute;top:35px;padding-left:10px;">
        <tr>
        <td align="left" nowrap><span class="menu_bun"><span id="pageCount"><bean:write name="cmn003Form" property="menuPage" /></span>/<%= String.valueOf(menuMaxPage) %></span></td>
        </tr>
      </table>
    </td>
    <td><IMG SRC="../common/images/spacer.gif" width="30px" height="30px" border="0" style="visibility:hidden"></td>
<% } %>

</logic:notEqual>

</html:form>

</tr>
</table>


<!-- ここから -->
<div id="right-part">
<table border="0" cellpadding="0" cellspacing="0">
<tr>
<td>

  <logic:equal name="kusr" property="usrsid" value="0">
  <table border="0" cellpadding="0" cellspacing="0"><tr><td><img height="30" width="1" src="../common/images/damy.gif" alt="<gsmsg:write key="cmn.space" />"></td></tr></table>
  </logic:equal>
</td>
</tr>

<tr>
<td align="left" nowrap margin="50px">

</td>
<td><img height="1" src="../common/images/damy.gif" width="5" alt="<gsmsg:write key="cmn.space" />"></td>
</tr>
</table>

</div>

<!-- ここまで -->

</td>
</tr>

</table>

</body>


<script type="text/javascript">
  var animate = false;
  $(function(){
    var maxCnt = <bean:write name="cmn003Form" property="cmn003maxPage" />;
    $("#mycarousel").jcarousel({
      start:<bean:write name="cmn003Form" property="menuPage" />,
      size :<bean:write name="cmn003Form" property="cmn003maxPage" />,
      scroll:1,
      <logic:equal name="cmn003Form" property="cmn003buttonFlg" value="1">
      buttonNextHTML:'',
      buttonPrevHTML:'',
      </logic:equal>
      initCallback: mycarousel_initCallback,
      itemVisibleInCallback: {
          onBeforeAnimation: callback1,
          onAfterAnimation: callback2
      }
    });

    $('.jcarousel-next-horizontal').hover(
       function(){
              var cnt = eval($("#pageCount").text());
              if (cnt < maxCnt) {
                $('.jcarousel-next-horizontal').removeClass("jcarousel-hover2");
                $('.jcarousel-next-horizontal').addClass("jcarousel-hover1");
              }
         },function(){
              var cnt = eval($("#pageCount").text());
              if (cnt < maxCnt) {
                $('.jcarousel-next-horizontal').removeClass("jcarousel-hover1");
                $('.jcarousel-next-horizontal').addClass("jcarousel-hover2");
              }
         });

      $('.jcarousel-prev-horizontal').hover(
        function(){
            var cnt = eval($("#pageCount").text());
            if ((cnt - 1) > 0) {
              $('.jcarousel-prev-horizontal').removeClass("jcarousel-hover2");
              $('.jcarousel-prev-horizontal').addClass("jcarousel-hover1");
            }
        },function(){
            var cnt = eval($("#pageCount").text());
            if ((cnt - 1) > 0) {
              $('.jcarousel-prev-horizontal').removeClass("jcarousel-hover1");
              $('.jcarousel-prev-horizontal').addClass("jcarousel-hover2");
            }
        });
      $('.menu_bg').hover(
        function(){
            $(this).addClass("menu_hover");
        },function(){
            $(this).removeClass("menu_hover");
        });
  });

  function callback1() {
     animate = true;
  }

  function callback2() {
       animate = false;
  }

  function mycarousel_initCallback(carousel) {
    var maxCnt = <bean:write name="cmn003Form" property="cmn003maxPage" />;

      carousel.buttonNext.bind('click', function() {
          if (!animate) {
            var cnt = eval($("#pageCount").text());
            if (cnt < maxCnt) {
               $("#pageCount").text(cnt + 1);
               $("input[name='menuPage']").attr("value",cnt + 1);
               if (cnt +1 == maxCnt) {
                 $('.jcarousel-next-horizontal').addClass("jcarousel-hover2");
               }
            }
          }
      });
      carousel.buttonPrev.bind('click', function() {
          if (!animate) {
            var cnt = eval($("#pageCount").text());
            if ((cnt - 1) > 0) {
                $("#pageCount").text(cnt - 1);
                $("input[name='menuPage']").attr("value",cnt - 1);
                if ((cnt - 1) == 1) {
                  $('.jcarousel-prev-horizontal').addClass("jcarousel-hover2");
                }
            }
          }
      });
  };
</script>


</html:html>