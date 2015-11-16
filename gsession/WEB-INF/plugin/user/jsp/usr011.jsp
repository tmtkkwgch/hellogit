<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<% String maxLengthCmt = String.valueOf(jp.groupsession.v2.usr.GSConstUser.MAX_LENGTH_GROUPCOMMENT); %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="user.44" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../user/js/usr011.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../user/css/user.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03" onload="showLengthId($('#inputstr')[0], <%= maxLengthCmt %>, 'inputlength');">

<html:form action="/user/usr011">
<input type="hidden" name="CMD" value="">
<html:hidden property="usr010grpmode" />
<html:hidden property="usr010grpSid" />
<html:hidden property="usr011grpsid" />
<html:hidden property="usr011grpOnce" />
<html:hidden property="usr011DelButton" />
<html:hidden property="usr011DelKbn" />
<html:hidden property="selectgroup" />
<html:hidden property="disabledGroups"/>
<logic:equal name="usr011Form" property="usr010grpmode" scope="request" value="add">
  <input type="hidden" name="helpPrm" value="0">
</logic:equal>
<logic:equal name="usr011Form" property="usr010grpmode" scope="request" value="edit">
  <input type="hidden" name="helpPrm" value="1">
</logic:equal>

<logic:notEmpty name="usr011Form" property="sv_users" scope="request">
  <logic:iterate id="ulBean" name="usr011Form" property="sv_users" scope="request">
    <input type="hidden" name="sv_users" value="<bean:write name="ulBean" />">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="usr011Form" property="sv_Bfusers" scope="request">
  <logic:iterate id="ulBeanBf" name="usr011Form" property="sv_Bfusers" scope="request">
    <input type="hidden" name="sv_Bfusers" value="<bean:write name="ulBeanBf" />">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="usr011Form" property="sv_usersKr" scope="request">
  <logic:iterate id="ulkrBean" name="usr011Form" property="sv_usersKr" scope="request">
    <input type="hidden" name="sv_usersKr" value="<bean:write name="ulkrBean" />">
  </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!-- BODY -->
<table width="100%">
<tr>
<td width="100%" align="center" cellpadding="5" cellspacing="0">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">
      <logic:equal name="usr011Form" property="usr010grpmode" scope="request" value="add">[ <gsmsg:write key="user.usr011.1" /> ]</logic:equal>
      <logic:equal name="usr011Form" property="usr010grpmode" scope="request" value="edit">[ <gsmsg:write key="user.usr011.2" /> ]</logic:equal>
    </td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" class="btn_ok1" value="OK" onClick="getChgctg(), buttonPush('kakunin');">
      <logic:equal name="usr011Form" property="usr010grpmode"  scope="request" value="edit">
        <logic:equal name="usr011Form" property="usr011DelButton" value="enable">
          <input type="button" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onClick="pushDell('del'), getChgctg(), buttonPush('del');">
        </logic:equal>
      </logic:equal>
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('back');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <logic:messagesPresent message="false"><html:errors /></logic:messagesPresent>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <!-- グループID -->
    <table cellpadding="5" cellspacing="0" border="0" width="100%" class="tl2">
    <tr>
    <th valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.group.id" /></span><span class="text_r2">※</span></th>
    <td valign="middle" align="left" class="td_type1" width="100%"><html:text property="usr011gpId" style="width:273px;" maxlength="15" /></td>
    </tr>

    <!-- グループ名 -->
    <tr>
    <th valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.group.name" /></span><span class="text_r2">※</span></th>
    <td valign="middle" align="left" class="td_type1" width="100%"><html:text property="usr011gpname" style="width:541px;" maxlength="50" /></td>
    </tr>

    <!-- グループ名カナ -->
    <tr>
    <th valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="user.14" /></span></th>
    <td valign="middle" align="left" class="td_type1" width="100%"><html:text property="usr011gpnameKana" style="width:541px;" maxlength="75" /></td>
    </tr>

    <!-- コメント -->
    <tr>
    <th valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.comment" /></span></th>
    <td valign="middle" align="left" class="td_type1" width="100%">
      <textarea style="width:541;" name="usr011com"  rows="5" wrap="hard" onkeyup="showLengthStr(value, <%= maxLengthCmt %>, 'inputlength');" id="inputstr"><bean:write name="usr011Form" property="usr011com" /></textarea>
      <br>
      <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthCmt %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
    </tr>

    <!-- 利用者設定 -->
    <tr>
    <th valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="user.75" /></span></th>
    <td valign="middle" align="left" class="td_type1" width="100%">
      <table cellpadding="3" cellspacing="0" border="0" width="100%">
      <tr>
      <td align="center" class="td_type3" width="50%"><span class="text_base3"><gsmsg:write key="user.75" /></span></td>
      <td width="0%"><img alt="<gsmsg:write key="cmn.space" />" height="1" src="../common/images/damy.gif" width="25"></td>
      <td align="center" class="td_type3" width="50%"><span class="text_base3"><gsmsg:write key="user.15" /></span></td>
      </tr>
      <tr>
      <td align="center" class="td_body01" valign="top">
        <!-- 所属 -->
        <select size="17" multiple name ="users_l" class="select01">
          <logic:notEmpty name="usr011Form" property="usr011tarBelongingUser" scope="request">
            <logic:iterate id="ulBean" name="usr011Form" property="usr011tarBelongingUser" scope="request">
              <option value="<bean:write name="ulBean" property="usrsid" scope="page"/>"><bean:write name="ulBean" property="fullName" scope="page"/></option>
            </logic:iterate>
          </logic:notEmpty>
          <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
        </select>
      </td>


      <td valign="middle" align="center">
        <!--html:image src="../common/images/arrow_btn_add.gif" property="leftarrow_btn" alt="<gsmsg:write key="cmn.add" />" border="0" /-->
        <input type="image" src="../common/images/arrow_btn_add.gif" name="leftarrow_btn" alt="<gsmsg:write key="cmn.add" />" border="0" onClick="getChgctg()">
        <br><br><br>
        <!--html:image src="../common/images/arrow_btn_delete.gif" property="rightarrow_btn" alt="<gsmsg:write key="cmn.delete" />" border="0" /-->
        <input type="image" src="../common/images/arrow_btn_delete.gif" name="rightarrow_btn" alt="<gsmsg:write key="cmn.delete" />" border="0" onClick="getChgctg()">
      </td>

<%--
      <td valign="middle" align="center">
        <!--html:image src="../common/images/arrow_btn_add.gif" property="leftarrow_btn" alt="<gsmsg:write key="cmn.add" />" border="0" /-->
        <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="getChgctg();">
        <br><br><br>
        <!--html:image src="../common/images/arrow_btn_delete.gif" property="rightarrow_btn" alt="<gsmsg:write key="cmn.delete" />" border="0" /-->
        <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="getChgctg();">
      </td>
--%>


      <td align="center" class="td_body01" valign="top">
        <!-- 未所属 -->
        <table>
        <tr>
        <td colspan="2">
          <input class="btn_group_n1" onclick="getBfsids();openAllGroup(this.form.slt_group, 'slt_group', '<bean:write name="usr011Form" property="slt_group" />', '0', 'usrCheck', 'sv_users', '-1', '0');" value="<gsmsg:write key="cmn.sel.all.group" />"  type="button">
        </td>
        </tr>
        <tr>
        <td>
          <logic:notEmpty name="usr011Form" property="groupCombo" scope="request">
            <html:select name="usr011Form" property="slt_group" styleClass="select01" onchange="getChgctg(), usr011ChahgeCbx()">
              <html:optionsCollection name="usr011Form" property="groupCombo" value="value" label="label" />
            </html:select>
          </logic:notEmpty>
        </td>
        <td>
          <input type="button" onclick="openGroupWindowForUsr011(this.form.slt_group, 'slt_group', '0', 'change')" class="group_btn2" value="&nbsp;&nbsp;" id="usr011GroupBtn">
        </td>
        </tr>
        <tr>
        <td colspan="2">
        <select size="14" multiple name="users_r" class="select01">
          <logic:notEmpty name="usr011Form" property="usr011tarUnbelongingUser" scope="request">
            <logic:iterate id="urBean" name="usr011Form" property="usr011tarUnbelongingUser" scope="request">
              <option value="<bean:write name="urBean" property="usrsid" scope="page"/>"><bean:write name="urBean" property="fullName" scope="page"/></option>
            </logic:iterate>
          </logic:notEmpty>
          <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
        </select>
        </td>
        </tr>
        </table>
      </td>
      </tr>
      </table>
    </td>
    </tr>

    <!-- グループ管理者設定 -->
    <tr>
    <th valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.group.admin" /></span></th>
    <td valign="middle" align="left" class="td_type1" width="100%">
      <table cellpadding="3" cellspacing="0" border="0" width="100%">
      <tr>
      <td align="center" class="td_type3" width="50%"><span class="text_base3"><gsmsg:write key="cmn.admin" /></span></td>
      <td width="0%"><img alt="<gsmsg:write key="cmn.space" />" height="1" src="../common/images/damy.gif" width="25"></td>
      <td align="center" class="td_type3" width="50%"><span class="text_base3"><gsmsg:write key="user.75" /></span></td>
      </tr>
      <tr>
      <td align="center" class="td_body01" valign="top">
        <!-- 所属 -->
        <select size="15" multiple name ="usersKr_l" class="select01">
          <logic:notEmpty name="usr011Form" property="usr011BelongingUserKr" scope="request">
            <logic:iterate id="ulkrBean" name="usr011Form" property="usr011BelongingUserKr" scope="request">
              <option value="<bean:write name="ulkrBean" property="usrsid" scope="page"/>"><bean:write name="ulkrBean" property="fullName" scope="page"/></option>
            </logic:iterate>
          </logic:notEmpty>
          <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
        </select>
      </td>




      <td valign="middle" align="center">
        <!--html:image src="../common/images/arrow_btn_add.gif" property="leftarrow2_btn" alt="<gsmsg:write key="cmn.add" />" border="0" /-->
        <input type="image" src="../common/images/arrow_btn_add.gif" name="leftarrow2_btn" alt="<gsmsg:write key="cmn.add" />" border="0" onClick="getChgctg()">
        <br><br><br>
        <!--html:image src="../common/images/arrow_btn_delete.gif" property="rightarrow2_btn" alt="<gsmsg:write key="cmn.delete" />" border="0" /-->
        <input type="image" src="../common/images/arrow_btn_delete.gif" name="rightarrow2_btn" alt="<gsmsg:write key="cmn.delete" />" border="0" onClick="getChgctg()">
      </td>


<%--
      <td valign="middle" align="center">
        <!--html:image src="../common/images/arrow_btn_add.gif" property="leftarrow2_btn" alt="<gsmsg:write key="cmn.add" />" border="0" /-->
        <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="getChgctg();">
        <br><br><br>
        <!--html:image src="../common/images/arrow_btn_delete.gif" property="rightarrow2_btn" alt="<gsmsg:write key="cmn.delete" />" border="0" /-->
        <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="getChgctg();">
      </td>
--%>


      <td align="center" class="td_body01" valign="top">
        <!-- 未所属 -->
        <select size="15" multiple name ="usersKr_r" class="select01">
          <logic:notEmpty name="usr011Form" property="usr011UnBelongingUserKr" scope="request">
            <logic:iterate id="uripBean" name="usr011Form" property="usr011UnBelongingUserKr" scope="request">
              <option value="<bean:write name="uripBean" property="usrsid" scope="page"/>"><bean:write name="uripBean" property="fullName" scope="page"/></option>
            </logic:iterate>
          </logic:notEmpty>
          <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
        </select>
      </td>
      </tr>
      </table>
    </td>
    </tr>

    <!-- グループ設定 -->
    <tr>
    <th valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="user.21" /></span></th>
    <td valign="middle" align="left" class="td_type1" width="100%">
    <span class="text_base"><br><gsmsg:write key="user.usr011.3" /></span><br>
      <iframe src="../user/usr020.do?dspRoot=1&selectLevel=<bean:write name="usr011Form" property="selectLevel" scope="request"/>&checkGroup=<bean:write name="usr011Form" property="selectgroup" scope="request"/>" class="iframe_01" name="ctgFrame" root="1" height="300" width="100%">
      <gsmsg:write key="user.32" />
      </iframe>
    </td>
    </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
      <input type="button" class="btn_ok1" value="OK" onClick="getChgctg(), buttonPush('kakunin');">
      <logic:equal name="usr011Form" property="usr010grpmode"  scope="request" value="edit">
        <logic:equal name="usr011Form" property="usr011DelButton" value="enable">
          <input type="button" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onClick="pushDell('del'), getChgctg(), buttonPush('del');">
        </logic:equal>
      </logic:equal>
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('back');">
    </td>
    </tr>
    </table>
  </tr>
  </td>
  </table>
</td>
</tr>
</table>

</html:form>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>