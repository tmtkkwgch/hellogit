<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ include file="./anpheader_mb000.jsp" %>

<html:html>
<head>
<title>[Group Session] <gsmsg:write key="anp.plugin"/></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html;" charset="Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
</head>

<body>
<html:form method="post" action="/anpi/anp021kn">
<!-- BODY -->
<input type="hidden" name="CMD">
<html:hidden property="anp021JokyoFlg" />
<html:hidden property="anp021PlaceFlg" />
<html:hidden property="anp021SyusyaFlg" />
<html:hidden property="anp021Comment" />
<html:hidden property="anp021KnrenFlg" />
<html:hidden property="anp021initFlg" />


<%@ include file="./anpheader_mb001.jsp" %>

<b><gsmsg:write key="anp.plugin"/><br><gsmsg:write key="anp.anp020kn.01"/></b>
<hr>

<div style="text-align:left; color:red">
  <html:errors/><br><br>
</div>

<logic:equal name="anp021knForm" property="anp021KnrenFlg" value="1">
<div style="text-align:left; color:red">
━━━ <gsmsg:write key="anp.knmode"/> ━━━<br><br>
</div>
</logic:equal>

■<gsmsg:write key="anp.jokyo"/><br>
   <logic:equal name="anp021knForm" property="anp021JokyoFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.JOKYO_FLG_UNSET) %>" ><gsmsg:write key="cmn.notset"/></logic:equal>
   <logic:equal name="anp021knForm" property="anp021JokyoFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.JOKYO_FLG_GOOD) %>" ><gsmsg:write key="anp.jokyo.good"/></logic:equal>
   <logic:equal name="anp021knForm" property="anp021JokyoFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.JOKYO_FLG_KEISYO) %>" ><gsmsg:write key="anp.jokyo.keisyo"/></logic:equal>
   <logic:equal name="anp021knForm" property="anp021JokyoFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.JOKYO_FLG_JUSYO) %>" ><gsmsg:write key="anp.jokyo.jusyo"/></logic:equal>
<br><br>

■<gsmsg:write key="anp.place"/><br>
   <logic:equal name="anp021knForm" property="anp021PlaceFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.PLACE_FLG_UNSET) %>" ><gsmsg:write key="cmn.notset"/></logic:equal>
   <logic:equal name="anp021knForm" property="anp021PlaceFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.PLACE_FLG_HOUSE) %>" ><gsmsg:write key="anp.place.house"/></logic:equal>
   <logic:equal name="anp021knForm" property="anp021PlaceFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.PLACE_FLG_OFFICE) %>" ><gsmsg:write key="anp.place.office"/></logic:equal>
   <logic:equal name="anp021knForm" property="anp021PlaceFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.PLACE_FLG_OUT) %>" ><gsmsg:write key="anp.place.out"/></logic:equal>
<br><br>

■<gsmsg:write key="anp.syusya.state"/><br>
   <logic:equal name="anp021knForm" property="anp021SyusyaFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SYUSYA_FLG_UNSET) %>" ><gsmsg:write key="cmn.notset"/></logic:equal>
   <logic:equal name="anp021knForm" property="anp021SyusyaFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SYUSYA_FLG_NO) %>" ><gsmsg:write key="anp.syusya.no"/></logic:equal>
   <logic:equal name="anp021knForm" property="anp021SyusyaFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SYUSYA_FLG_OK) %>" ><gsmsg:write key="anp.syusya.ok"/></logic:equal>
   <logic:equal name="anp021knForm" property="anp021SyusyaFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SYUSYA_FLG_OKD) %>" ><gsmsg:write key="anp.syusya.okd"/></logic:equal>
<br><br>

■<gsmsg:write key="anp.comment"/><br>
<bean:write name="anp021knForm" property="anp021knDspComment" filter="false" />
<br><br>

<div align="center">
  <br><input name="anp021knexcute" value="OK" type="submit" style="width: 90px; height: 30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="anp021knback" value="<gsmsg:write key="cmn.back" />" type="submit" style="width: 90px; height: 30px">
</div>

</html:form>
<!-- footer -->
<%@ include file="./anpfooter_mb.jsp" %>
</body>
</html:html>