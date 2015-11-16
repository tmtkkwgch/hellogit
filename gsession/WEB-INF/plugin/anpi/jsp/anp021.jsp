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
<html:form method="post" action="/anpi/anp021">
<!-- BODY -->
<input type="hidden" name="CMD">

<%@ include file="./anpheader_mb001.jsp" %>
<html:hidden property="anp021KnrenFlg" />
<html:hidden property="anp021initFlg" />


<b><gsmsg:write key="anp.plugin"/><br><gsmsg:write key="anp.anp020.02"/></b>
<hr>

<div style="text-align:left; color:red">
  <html:errors/><br><br>
</div>

<logic:equal name="anp021Form" property="anp021KnrenFlg" value="1">
<div style="text-align:left; color:red">
━━━ <gsmsg:write key="anp.knmode"/> ━━━<br><br>
</div>
</logic:equal>

■<gsmsg:write key="anp.jokyo"/><br>
<html:radio styleId="jokyoFlg1" name="anp021Form" property="anp021JokyoFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.JOKYO_FLG_UNSET) %>" />
      <label for="jokyoFlg1"><gsmsg:write key="cmn.notset"/></label><br>
<html:radio styleId="jokyoFlg2" name="anp021Form" property="anp021JokyoFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.JOKYO_FLG_GOOD) %>" />
      <label for="jokyoFlg2"><gsmsg:write key="anp.jokyo.good"/></label>&nbsp;
<html:radio styleId="jokyoFlg3" name="anp021Form" property="anp021JokyoFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.JOKYO_FLG_KEISYO) %>" />
      <label for="jokyoFlg3"><gsmsg:write key="anp.jokyo.keisyo"/></label>&nbsp;
<html:radio styleId="jokyoFlg4" name="anp021Form" property="anp021JokyoFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.JOKYO_FLG_JUSYO) %>" />
       <label for="jokyoFlg4"><gsmsg:write key="anp.jokyo.jusyo"/></label>
<br><br>

■<gsmsg:write key="anp.place"/><br>
<html:radio styleId="placeFlg1" name="anp021Form" property="anp021PlaceFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.PLACE_FLG_UNSET) %>" />
        <label for="placeFlg1"><gsmsg:write key="cmn.notset"/></label><br>
<html:radio styleId="placeFlg2" name="anp021Form" property="anp021PlaceFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.PLACE_FLG_HOUSE) %>" />
      <label for="placeFlg2"><gsmsg:write key="anp.place.house"/></label>&nbsp;
<html:radio styleId="placeFlg3" name="anp021Form" property="anp021PlaceFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.PLACE_FLG_OFFICE) %>" />
      <label for="placeFlg3"><gsmsg:write key="anp.place.office"/></label>&nbsp;
<html:radio styleId="placeFlg4" name="anp021Form" property="anp021PlaceFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.PLACE_FLG_OUT) %>" />
       <label for="placeFlg4"><gsmsg:write key="anp.place.out"/></label>
<br><br>

■<gsmsg:write key="anp.syusya.state"/><br>
<html:radio styleId="syusyaFlg1" name="anp021Form" property="anp021SyusyaFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SYUSYA_FLG_UNSET) %>" />
      <label for="syusyaFlg1"><gsmsg:write key="cmn.notset"/></label><br>
<html:radio styleId="syusyaFlg2" name="anp021Form" property="anp021SyusyaFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SYUSYA_FLG_NO) %>" />
      <label for="syusyaFlg2"><gsmsg:write key="anp.syusya.no"/></label>&nbsp;
<html:radio styleId="syusyaFlg3" name="anp021Form" property="anp021SyusyaFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SYUSYA_FLG_OK) %>" />
      <label for="syusyaFlg3"><gsmsg:write key="anp.syusya.ok"/></label>&nbsp;
<html:radio styleId="syusyaFlg4" name="anp021Form" property="anp021SyusyaFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SYUSYA_FLG_OKD) %>" />
       <label for="syusyaFlg4"><gsmsg:write key="anp.syusya.okd"/></label>
<br><br>

■<gsmsg:write key="anp.comment"/><br>
<html:textarea styleId="comment" name="anp021Form" property="anp021Comment" style="width:183px;" rows="2" styleClass="text_base" />
<br><br>
<div align="center">
  <br><input name="anp021excute" value="OK" type="submit" style="width: 90px; height: 30px">
</div>


</html:form>

<!-- footer -->
<%@ include file="./anpfooter_mb.jsp" %>
</body>
</html:html>