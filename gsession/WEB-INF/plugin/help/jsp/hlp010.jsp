<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<meta http-equiv="Content-Style-Type" content="text/css">
<title>[GroupSession] <gsmsg:write key="cmn.help" /><gsmsg:write key="cmn.main" /></title>
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css" type="text/css">
<link rel=stylesheet href="../help/css/help.css" type="text/css">
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js'></script>
<script language="JavaScript" src="../common/js/cmd.js"></script>
<script type="text/javascript" src="../help/js/help.js"></script>
</head>

<title>[Group Session] <gsmsg:write key="cmn.help" /><gsmsg:write key="cmn.main" /><gsmsg:write key="cmn.display" /></title>
</head>

<%-- ここを呼び出された状況に応じて切り替える start --%>

<logic:empty name="hlp010Form" property="hlp010HelpUrl">
  <body onLoad="ajaxGetContents('help-left-plugin-list', '../help/hlp000.do'); ajaxGetContents('right-content', '../help/hlp000top.do');">

  <div id="container"><a name="top"></a>
    <div id="help-left">
    <div class="help-content-tr">
      <div class="help-content-tl" >
        <table width="100%">
          <tr>
            <td class="help-content-tl-td">
              <span class="help-header-text2"><gsmsg:write key="cmn.plugin.list" /></span>
            </td>
          </tr>
        </table>
      </div>
    </div>
    <div class="help-content-mr"><div class="help-content-ml">
        <div id="help-left-plugin-list"></div>

    </div></div>
    <div class="help-content-br"><div class="help-content-bl"><img src="../help/images/spacer.gif" width="1" height="15" alt=""></div></div>
  </div>
    <!-- コンテンツ -->
  <div id="right-content">
  <!-- ここにAJAX.UPDATERの結果を挿入 -->
  </div>
  </div>

</logic:empty>

<logic:notEmpty name="hlp010Form" property="hlp010HelpUrl">
  <body onLoad="location.href='<bean:write name="hlp010Form" property="hlp010HelpUrl" />'">
</logic:notEmpty>

<%-- ここを呼び出された状況に応じて切り替える end --%>


<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>

</html:html>