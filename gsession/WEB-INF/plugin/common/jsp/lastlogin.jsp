<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<!--前回ログイン時間-->
<table width="100%" class="tl0" cellspacing="0" cellpadding="0">
<tr>
<td align="left" class="header_7D91BD_right">
  <img src="../main/images/menu_icon_single_login.gif" class="img_bottom" alt="<gsmsg:write key="cmn.last.1" />"><a href="../main/man050.do?man050Backurl=1"><gsmsg:write key="cmn.last.1" /></a>
</td>
</tr>

<tr>
<td class="td_type1" align="center">
  <logic:notEmpty name="man001Form" property="man001lstLogintime">
      <span class="text_base3"><bean:write name="man001Form" property="man001lstLogintime" /></span>
    &nbsp;&nbsp;<br>
  </logic:notEmpty>
  <logic:empty name="man001Form" property="man001lstLogintime">
   &nbsp;
  </logic:empty>&nbsp;
  <input type="button" name="confirm_last_login" value="<gsmsg:write key="cmn.last.2" />"" class="btn_base0" onClick="location.href='../main/man050.do?man050Backurl=1'">
</td>
</tr>

<tr>
<td>
  <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
</td>
</tr>
</table>
