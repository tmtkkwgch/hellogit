<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/submit.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/popup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/cmn999.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[GroupSession] <gsmsg:write key="cmn.message" /></title>
<script language="JavaScript">
<!--
  //ボタン
  function onForward(){

      document.forms[0].target=document.forms[0].wtarget.value;
      var forwardUrl;
      forwardUrl = "<bean:write name="cmn998Form" property="urlOK" filter="false" />";
      document.forms[0].directURL.value = forwardUrl;

      document.forms[0].submit();
      return onControlSubmit();
  }

  var subWindow;

  function openErrReportWindow(url) {
    var winWidth = 500;
    var winHeight = 300;
    var winx = getCenterX(winWidth);
    var winy = getCenterY(winHeight);

    var newWinOpt = "width=" + winWidth + ", height=" + winHeight + ", toolbar=no ,scrollbars=yes, resizable=yes, left=" + winx + ", top=" + winy;

    if (!flagSubWindow || (flagSubWindow && subWindow.closed)) {
        subWindow = window.open(url, 'exceptionWindow', newWinOpt);
        flagSubWindow = true;
    } else {
        subWindow.location.href=url;
        subWindow.focus();
    }

    document.forms['errLogForm'].submit();
    return false;
  }

  function getCenterX(winWidth) {
    var x = (screen.width - winWidth) / 2;
    return x;
  }

  function getCenterY(winHeight) {
    var y = (screen.height - winHeight) / 2;
    return y;
  }
// -->
</script>
</head>

<body>

<html:form action="/common/cmn998" target="_self">

<html:hidden property="CMD" value="" />
<html:hidden property="directURL" />
<html:hidden property="tokenUrl" />
<html:hidden property="tokenFlg" />
<html:hidden property="wtarget" />
<logic:notEmpty name="cmn998Form" property="hiddenList">
  <logic:iterate id="item" name="cmn998Form" property="hiddenList" scope="request">
  <input type="hidden" name="<bean:write name="item" property="key"/>" value="<bean:write name="item" property="value"/>">
  </logic:iterate>
</logic:notEmpty>

<div id="contair">
  <img height="30" src="../common/images/damy.gif" width="1" alt="">
  <div id="information_body">

    <div id="information_top">
      <span>
      <gsmsg:write key="cmn.warning" />
      </span>
    </div>
    <div class="information_middle_div">
      <table class="tl0">
      <tr>
      <td class="information_middle_left2">
      <img alt="<gsmsg:write key="cmn.warning" />" src="../common/images/warn.gif">
      </td>
      <td class="information_middle_right2">
      <span class="text_base_b"><bean:write name="cmn998Form" property="message" filter="false"/></span>
      </td>
      </tr>
      </table>
    </div>

    <div class="information_middle_div">

    <div class="information_middle_button">
      <input type="button" value="<bean:write name="cmn998Form" property="okBtnValue"/>" class="btn_ok1" onClick="return onForward();">
    </div>

    </html:form>

  </div>
  <div id="information_bottom"></div>
</div>

</form>
<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</body>
</html:html>