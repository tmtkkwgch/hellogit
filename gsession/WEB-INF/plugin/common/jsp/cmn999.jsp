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
  function onForward(buttonType){

      document.forms[0].target=document.forms[0].wtarget.value;

      var forwardUrl;
      if (buttonType == 0) {
          forwardUrl = "<bean:write name="cmn999Form" property="urlOK" filter="false" />";
          document.forms[0].directURL.value = forwardUrl;
      } else if (buttonType == 1) {
          forwardUrl = "<bean:write name="cmn999Form" property="urlCancel"/>";
          document.forms[0].directURL.value = forwardUrl;
      }

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

<html:form action="/common/cmn999" target="_self">

<html:hidden property="CMD" value="" />
<html:hidden property="directURL" />
<html:hidden property="tokenUrl" />
<html:hidden property="tokenFlg" />
<html:hidden property="wtarget" />
<logic:notEmpty name="cmn999Form" property="hiddenList">
  <logic:iterate id="item" name="cmn999Form" property="hiddenList" scope="request">
  <input type="hidden" name="<bean:write name="item" property="key"/>" value="<bean:write name="item" property="value"/>">
  </logic:iterate>
</logic:notEmpty>

<div id="contair">
  <img height="30" src="../common/images/damy.gif" width="1" alt="">
  <div id="information_body">

    <div id="information_top">
      <span>
      <logic:equal name="cmn999Form" property="icon" value="0"><gsmsg:write key="cmn.warning" /></logic:equal>
      <logic:equal name="cmn999Form" property="icon" value="1"><gsmsg:write key="cmn.information" /></logic:equal>
      </span>
    </div>
    <div class="information_middle_div">
      <table class="tl0">
      <tr>
      <td class="information_middle_left2">
      <logic:equal name="cmn999Form" property="icon" value="0">
      <img alt="<gsmsg:write key="cmn.warning" />" src="../common/images/warn.gif">
      </logic:equal>
      <logic:equal name="cmn999Form" property="icon" value="1">
      <img alt="<gsmsg:write key="cmn.information" />" src="../common/images/info.gif">
      </logic:equal>
      </td>
      <td class="information_middle_right2">
      <span class="text_base_b"><bean:write name="cmn999Form" property="message" filter="false"/></span>
      </td>
      </tr>
      </table>
    </div>

    <div class="information_middle_div">
    <!-- エラーログ -->
    <logic:notEmpty name="cmn999Form" property="errorLog">
      <div id="information_errorlog">
        <div align="left"><gsmsg:write key="cmn.cmn999.2" /></div>
        <textarea name="elog" id="exceptionLog" style="width:383px" rows="5" class="text_base" readonly wrap="off"><bean:write name="cmn999Form" property="errorLogOnly"/></textarea>
      </div>
      <br>
    </logic:notEmpty>
    <br>
    <!-- 動作環境 -->
    <logic:notEmpty name="cmn999Form" property="detailInfo">
      <div id="information_machine">
        <div align="left"><gsmsg:write key="cmn.cmn999.3" /></div>
        <textarea name="minfo" id="exceptionLog" style="width:383px" rows="2" class="text_base" readonly wrap="off"><bean:write name="cmn999Form" property="detailInfo" /></textarea>
      </div>
      <br>
    </logic:notEmpty>

    <logic:equal name="cmn999Form" property="type_popup" value="0">
      <div class="information_middle_button">
        <logic:equal name="cmn999Form" property="type" value="0">
        <input type="button" value="<bean:write name="cmn999Form" property="okBtnValue"/>" class="btn_ok1" onClick="return onForward(0);">
        </logic:equal>
        <logic:equal name="cmn999Form" property="type" value="1">
        <input type="button" value="<bean:write name="cmn999Form" property="okBtnValue"/>" class="btn_ok1" onClick="return onForward(0);">&nbsp;&nbsp;
        <input type="button" value="<gsmsg:write key="cmn.cancel" />" class="btn_base1" onClick="return onForward(1);">
        </logic:equal>
        <logic:equal name="cmn999Form" property="type" value="2">
        <input type="button" value="<bean:write name="cmn999Form" property="okBtnValue"/>" class="btn_ok1" onClick="return onForward(0);">&nbsp;&nbsp;
        <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_base1" onClick="location.href='<bean:write name="cmn999Form" property="urlBack"/>'">
        </logic:equal>
      </div>
    </logic:equal>

    <logic:equal name="cmn999Form" property="type_popup" value="1">
      <div class="information_middle_button">
        <logic:empty name="cmn999Form" property="closeScript">
          <input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_base1" onClick="window.open('about:blank','_self').close()">
        </logic:empty>
        <logic:notEmpty name="cmn999Form" property="closeScript">
          <input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_base1" onClick="<bean:write name="cmn999Form" property="closeScript" />">
        </logic:notEmpty>
      </div>
    </logic:equal>

    <logic:equal name="cmn999Form" property="outOfMemory" value="1">
      <div id="information_machine">
        <div align="left"><gsmsg:write key="cmn.cmn999.4" /></div>
        <a href="<bean:write name="cmn999Form" property="urlGsSetting" />" target="_blank"><gsmsg:write key="cmn.cmn999.5" /></a>
      </div>
    </logic:equal>

    </html:form>

    <logic:equal name="cmn999Form" property="type" value="3">
      <div>
        <div class="information_middle_button">
          <logic:notEmpty name="cmn999Form" property="errorLog">
          <form action="<bean:write name="cmn999Form" property="urlReport"/>" method="post" name="errLogForm" target="exceptionWindow">
          <input type="hidden" name="exception" value="<bean:write name="cmn999Form" property="errorLog"/>">
          <input type="hidden" name="version" value="<bean:write name="cmn999Form" property="gsVersion"/>">
          <div id="error_report">
            <div align="left"><gsmsg:write key="cmn.cmn999.6" /></div>
            <div class="td_type20" width="100%">
                    <gsmsg:write key="cmn.cmn999.7" />
              <input type="button" value="<gsmsg:write key="cmn.reports" />" class="btn_base1" onClick="openErrReportWindow('<bean:write name="cmn999Form" property="urlReport"/>')">
            </div>
            <br>
            <input type="button" value="<bean:write name="cmn999Form" property="okBtnValue"/>" class="btn_ok1" onClick="return onForward(0);">
          </div>
          </form>
          </logic:notEmpty>
          <logic:empty name="cmn999Form" property="errorLog">
            <input type="button" value="<bean:write name="cmn999Form" property="okBtnValue"/>" class="btn_ok1" onClick="return onForward(0);">
          </logic:empty>
        </div>
      </div>
    </logic:equal>

  </div>
  <div id="information_bottom"></div>
</div>

</form>
<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</body>
</html:html>