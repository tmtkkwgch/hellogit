<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String help = jp.groupsession.v2.cmn.GSConst.HELPURL; %>
<!-- HEADER -->
<logic:notEmpty name="<%= help %>">
  <div align="right">
  <!--a href="<bean:write name="<%= help %>" />" target="_blank"><img src="../common/images/q.gif" alt="<gsmsg:write key="cmn.help" />" border="0"></a></div -->
  <input type="button" class="q_bg" value="<gsmsg:write key="cmn.help" />" onclick="return openHelp();">
  </div>

<script language="JavaScript">
<!--
function openHelp(){

  var urlStr = '<bean:write name="<%= help %>" filter="false" />';
  helpPrm = document.getElementsByName("helpPrm");

  if (helpPrm.length > 0) {
    for (i = 0; i < helpPrm.length; i++) {
      urlStr = urlStr + helpPrm[i].value;
    }
  }

  window.open(urlStr, 'help', '');
  return false;
}

// -->
</script>


</logic:notEmpty>