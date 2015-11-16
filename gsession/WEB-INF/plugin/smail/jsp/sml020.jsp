<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<% String sinki = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_CREATE_MODE_NEW); %>
<% String hensin = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_CREATE_MODE_HENSIN); %>
<% String zenhen = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_CREATE_MODE_ZENHENSIN); %>
<% String tenso = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_CREATE_MODE_TENSO); %>
<% String soko = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_CREATE_MODE_SOKO); %>
<% String nikkan = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_CREATE_MODE_SC_NIKKAN); %>
<% String syukan = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_CREATE_MODE_SC_SYUKAN); %>
<% String ntpnikkan = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_CREATE_MODE_NTP_NIKKAN); %>
<% String ntpsyukan = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_CREATE_MODE_NTP_SYUKAN); %>
<% String zaiseki = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_CREATE_MODE_ZAISEKI); %>
<% String main = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_CREATE_MODE_MAIN); %>
<% String copy = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_CREATE_MODE_COPY); %>
<% String toroku = String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_TOROKU); %>
<% String delete = String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE); %>

<% String markNone = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_NONE); %>
<% String markTel = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_TEL); %>
<% String markImp = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_INP); %>
<% String markSmaily    = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_SMAILY);  %>
<% String markWorry     = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_WORRY);   %>
<% String markAngry     = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_ANGRY);   %>
<% String markSadly     = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_SADRY);   %>
<% String markBeer      = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_BEER);    %>
<% String markHart      = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_HART);    %>
<% String markZasetsu   = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_ZASETSU); %>

<% String maxLengthText = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MAX_LENGTH_TEXT); %>

<%-- マーク画像定義 --%>
<%
  java.util.HashMap imgMap = new java.util.HashMap();
  jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage();
        String phone = gsMsg.getMessage(request, "cmn.phone");
        String important = gsMsg.getMessage(request, "sml.61");
        String smile = gsMsg.getMessage(request, "sml.11");
        String worry = gsMsg.getMessage(request, "sml.86");
        String angry = gsMsg.getMessage(request, "sml.83");
        String sad = gsMsg.getMessage(request, "sml.87");
        String beer = gsMsg.getMessage(request, "sml.15");
        String hart = gsMsg.getMessage(request, "sml.13");
        String tired = gsMsg.getMessage(request, "sml.88");

  imgMap.put(markTel, "<img src=\"../smail/images/call.gif\" alt=\"" + phone + "\" border=\"0\" class=\"img_bottom\">");
  imgMap.put(markImp, "<img src=\"../smail/images/zyuu.gif\" alt=\"" + important + "\" border=\"0\" class=\"img_bottom\">");
  imgMap.put(markSmaily, "<img src=\"../smail/images/icon_face01.gif\" alt=\"" + smile + "\" border=\"0\" class=\"img_bottom\">");
  imgMap.put(markWorry, "<img src=\"../smail/images/icon_face02.gif\" alt=\"" + worry + "\" border=\"0\" class=\"img_bottom\">");
  imgMap.put(markAngry, "<img src=\"../smail/images/icon_face03.gif\" alt=\"" + angry + "\" border=\"0\" class=\"img_bottom\">");
  imgMap.put(markSadly, "<img src=\"../smail/images/icon_face04.gif\" alt=\"" + sad + "\" border=\"0\" class=\"img_bottom\">");
  imgMap.put(markBeer, "<img src=\"../smail/images/icon_beer.gif\" alt=\"" + beer + "\" border=\"0\" class=\"img_bottom\">");
  imgMap.put(markHart, "<img src=\"../smail/images/icon_hart.gif\" alt=\"" + hart + "\" border=\"0\" class=\"img_bottom\">");
  imgMap.put(markZasetsu, "<img src=\"../smail/images/icon_zasetsu.gif\" alt=\"" + tired + "\" border=\"0\" class=\"img_bottom\">");

  imgMap.put("none", "&nbsp;");
%>


<%
  java.util.HashMap imgTextMap = new java.util.HashMap();
  imgTextMap.put(markTel, phone);
  imgTextMap.put(markImp, important);
  imgTextMap.put(markSmaily, smile);
  imgTextMap.put(markWorry, worry);
  imgTextMap.put(markAngry, angry);
  imgTextMap.put(markSadly, sad);
  imgTextMap.put(markBeer, beer);
  imgTextMap.put(markHart, hart);
  imgTextMap.put(markZasetsu, tired);

  imgTextMap.put("none", "&nbsp;");
%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../smail/js/sml020.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/submit.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>

<script type="text/javascript" src="../common/js/yui/yahoo-dom-event/yahoo-dom-event.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/yui/element/element-beta-min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/yui-accordion/accordionview/utilities.js?<%= GSConst.VERSION_PARAM %>"></script>
<link rel="stylesheet" type="text/css" href="../smail/css/reset-fontssmail.css?<%= GSConst.VERSION_PARAM %>">
<link rel="stylesheet" type="text/css" href="../common/js/yui-accordion/accordionview/assets/skins/sam/accordionview.css?<%= GSConst.VERSION_PARAM %>">
<script src="../common/js/yui/animation/animation-min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script src="../common/js/yui/container/container-min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<logic:equal name="sml020Form" property="sml020ProcMode" value="<%= sinki %>"><gsmsg:define id="title" msgkey="cmn.create.new" /></logic:equal>
<logic:equal name="sml020Form" property="sml020ProcMode" value="<%= hensin %>"><gsmsg:define id="title" msgkey="cmn.reply" /></logic:equal>
<logic:equal name="sml020Form" property="sml020ProcMode" value="<%= zenhen %>"><gsmsg:define id="title" msgkey="sml.67" /></logic:equal>
<logic:equal name="sml020Form" property="sml020ProcMode" value="<%= tenso %>"><gsmsg:define id="title" msgkey="cmn.forward" /></logic:equal>
<logic:equal name="sml020Form" property="sml020ProcMode" value="<%= soko %>"><gsmsg:define id="title" msgkey="cmn.create.new" /></logic:equal>
<logic:equal name="sml020Form" property="sml020ProcMode" value="<%= nikkan %>"><gsmsg:define id="title" msgkey="cmn.create.new" /></logic:equal>
<logic:equal name="sml020Form" property="sml020ProcMode" value="<%= syukan %>"><gsmsg:define id="title" msgkey="cmn.create.new" /></logic:equal>
<logic:equal name="sml020Form" property="sml020ProcMode" value="<%= ntpnikkan %>"><gsmsg:define id="title" msgkey="cmn.create.new" /></logic:equal>
<logic:equal name="sml020Form" property="sml020ProcMode" value="<%= ntpsyukan %>"><gsmsg:define id="title" msgkey="cmn.create.new" /></logic:equal>
<logic:equal name="sml020Form" property="sml020ProcMode" value="<%= zaiseki %>"><gsmsg:define id="title" msgkey="cmn.create.new" /></logic:equal>
<logic:equal name="sml020Form" property="sml020ProcMode" value="<%= main %>"><gsmsg:define id="title" msgkey="cmn.create.new" /></logic:equal>
<logic:equal name="sml020Form" property="sml020ProcMode" value="<%= copy %>"><gsmsg:define id="title" msgkey="cmn.create.new" /></logic:equal>

<title>[Group Session] <gsmsg:write key="cmn.shortmail" /><bean:write name="title" /></title>

<style type="text/css"><!--


.yui-tt {
    visibility:hidden;
    position:absolute;
    color:#333333;
    background-color:#eeeeee;
    padding:2px;
    border:1px solid #cccccc;
    width:auto;
    height: auto;
    z-index:1000;
}

--></style>

</head>



<body class="body_03 yui-skin-sam" onunload="windowClose();" onload="showLengthId($('#inputstr')[0], <%= maxLengthText %>, 'inputlength');">
<html:form action="/smail/sml020">
<input type="hidden" name="CMD" value="sendkn">
<html:hidden property="sml010ProcMode" />
<html:hidden property="sml010Sort_key" />
<html:hidden property="sml010Order_key" />
<html:hidden property="sml010PageNum" />
<html:hidden property="sml010SelectedSid" />
<html:hidden property="sml010usrSid" />

<html:hidden property="sml020ProcMode" />
<html:hidden property="sml020SendKbn" />
<html:hidden property="sml020SelectHinaId" />
<html:hidden property="sml020DelUsrSid" />
<html:hidden property="sml020DelSendKbn" />


<logic:notEmpty name="sml020Form" property="sml010DelSid" scope="request">
  <logic:iterate id="del" name="sml020Form" property="sml010DelSid" scope="request">
    <input type="hidden" name="sml010DelSid" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sml020Form" property="sml020userSid" scope="request">
  <logic:iterate id="smlUsrSid" name="sml020Form" property="sml020userSid" scope="request">
    <input type="hidden" name="sml020userSid" value="<bean:write name="smlUsrSid"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sml020Form" property="sml020userSidCc" scope="request">
  <logic:iterate id="smlUsrSidCc" name="sml020Form" property="sml020userSidCc" scope="request">
    <input type="hidden" name="sml020userSidCc" value="<bean:write name="smlUsrSidCc"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sml020Form" property="sml020userSidBcc" scope="request">
  <logic:iterate id="smlUsrSidBcc" name="sml020Form" property="sml020userSidBcc" scope="request">
    <input type="hidden" name="sml020userSidBcc" value="<bean:write name="smlUsrSidBcc"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="sml030SelectedRowNum" />
<html:hidden property="sch010DspDate" />
<html:hidden property="sch010DspGpSid" />
<html:hidden property="sch030FromHour" />
<html:hidden property="schWeekDate" />
<html:hidden property="schDailyDate" />
<html:hidden property="ntp010DspDate" />
<html:hidden property="ntp010DspGpSid" />
<html:hidden property="ntp030FromHour" />

<%-- sml090詳細検索パラメータ start --%>
<html:hidden property="sml090ProcModeSave" />
<html:hidden property="sml090BackParm" />

<html:hidden property="searchFlg" />

<html:hidden property="sml090page1" />
<html:hidden property="sml090page2" />
<html:hidden property="sml090SltGroup" />
<html:hidden property="sml090SltUser" />
<html:hidden property="sml090MailSyubetsu" />
<html:hidden property="sml090MailMark" />
<html:hidden property="sml090KeyWord" />
<html:hidden property="sml090KeyWordkbn" />
<logic:notEmpty name="sml020Form" property="sml090SearchTarget" scope="request">
  <logic:iterate id="target" name="sml020Form" property="sml090SearchTarget" scope="request">
    <input type="hidden" name="sml090SearchTarget" value="<bean:write name="target"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="sml090SearchSortKey1" />
<html:hidden property="sml090SearchOrderKey1" />
<html:hidden property="sml090SearchSortKey2" />
<html:hidden property="sml090SearchOrderKey2" />

<html:hidden property="sml020webmail" />


<%-- 検索SVパラメータ start -------------------------------------------------------------------------- --%>
<logic:notEmpty name="sml020Form" property="cmn120SvuserSid" scope="request">
  <logic:iterate id="svuser" name="sml020Form" property="cmn120SvuserSid" scope="request">
    <input type="hidden" name="cmn120SvuserSid" value="<bean:write name="svuser"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sml020Form" property="sml090userSid" scope="request">
  <logic:iterate id="user" name="sml020Form" property="sml090userSid" scope="request">
    <input type="hidden" name="sml090userSid" value="<bean:write name="user"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="sml090SvSltGroup" />
<html:hidden property="sml090SvSltUser" />

<logic:notEmpty name="sml020Form" property="sml090SvAtesaki" scope="request">
  <logic:iterate id="svAtesaki" name="sml020Form" property="sml090SvAtesaki" scope="request">
    <input type="hidden" name="sml090SvAtesaki" value="<bean:write name="svAtesaki"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="sml090SvMailSyubetsu" />
<html:hidden property="sml090SvMailMark" />
<html:hidden property="sml090SvKeyWord" />
<html:hidden property="sml090SvKeyWordkbn" />

<logic:notEmpty name="sml020Form" property="sml090SvSearchTarget" scope="request">
  <logic:iterate id="svTarget" name="sml020Form" property="sml090SvSearchTarget" scope="request">
    <input type="hidden" name="sml090SvSearchTarget" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="sml090SvSearchOrderKey1" />
<html:hidden property="sml090SvSearchSortKey1" />
<html:hidden property="sml090SvSearchOrderKey2" />
<html:hidden property="sml090SvSearchSortKey2" />

<html:hidden property="sml020AllUserReFlg" />

<%-- 検索SVパラメータ end ---------------------------------------------------------------------------- --%>

<%-- sml090詳細検索パラメータ end   --%>


<% boolean callWebmail = true; %>
<logic:notEqual name="sml020Form" property="sml020webmail" value="1">
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<% callWebmail = false; %>
</logic:notEqual>

<table align="center"  cellpadding="0" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center" class="table_pad">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%">
      <img src="../smail/images/header_smail03_7_01.gif" border="0" alt="<gsmsg:write key="cmn.shortmail" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.shortmail" /></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="cmn.create.new" /> ]</td>
    <td width="0%">
      <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="submit" value="OK" class="btn_ok1" onClick="return onControlSubmit();">
      <input type="button" value="<gsmsg:write key="sml.sml020.07" />" class="btn_base1" onClick="smlButtonPush('soko');">
      <logic:equal name="sml020Form" property="sml020ProcMode" value="<%= soko %>">
      <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="smlButtonPush('deleteKn');">
      </logic:equal>
      <% if (callWebmail) { %>
        <input type="button" name="btn_close" class="btn_close_n1" value="<gsmsg:write key="cmn.close" />" onClick="window.close();">
      <% } else { %>
        <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="replaceAtesakiParm();">
      <% } %>
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>
  </td>
  </tr>
  <tr>
  <td>
    <img src="../common/images/spacer.gif" style="width:1px; height:10px;" border="0" alt="">
    <logic:messagesPresent message="false">
    <br><html:errors /><br>
    <img src="../common/images/spacer.gif" style="width:1px; height:1px;" border="0" alt="">
    </logic:messagesPresent>
  </td>
  </tr>
  <tr>
  <td>


    <table cellpadding="0" cellspacing="0" align="center" width="100%">
    <tr>

    <td width="195px" align="left" valign="top">

          <ul id="leftmenu1">
          <li>
            <div class="text_bbw1"><img src="../common/images/user_icon_n1.gif" class="img_bottom">&nbsp;<gsmsg:write key="cmn.userlist" /></div>

            <div align="left">

              <table width="193px">
              <tr>
              <td width="193px" nowrap>
                <div class="smail_td2" style="border-bottom: solid 1px #cccccc">
                  <html:select name="sml020Form" property="sml010groupSid" onchange="sml020ChangeGrp();" styleClass="text_base" style="width:165px;">
                    <logic:notEmpty name="sml020Form" property="sml020groupList">
                    <logic:iterate id="gpBean" name="sml020Form" property="sml020groupList" scope="request">
                      <bean:define id="gpValue" name="gpBean" property="value" type="java.lang.String" />
                      <logic:equal name="gpBean" property="styleClass" value="0">
                      <html:option value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
                      </logic:equal>
                      <logic:notEqual name="gpBean" property="styleClass" value="0">
                        <html:option styleClass="select03" value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
                      </logic:notEqual>
                    </logic:iterate>
                    </logic:notEmpty>
                  </html:select>
                  <input type="button" onclick="openGroupWindow(this.form.sml010groupSid, 'sml010groupSid', '1', 'changeGrp')" class="group_btn2" value="&nbsp;&nbsp;" id="sml020GroupBtn">

                  <div class="table_pad">
                  <input type="button" name="new" value="<gsmsg:write key="cmn.from" />" class="btn_base1s_1" onclick="return smlButtonPush('sml020addUsrAtesaki');"/>
                  <input type="button" name="new" value="CC" class="btn_base1s_1" onclick="return smlButtonPush('sml020addUsrCc');"/>
                  <input type="button" name="new" value="BCC" class="btn_base1s_1" onclick="return smlButtonPush('sml020addUsrBcc');"/><br>
                  </div>
                  <input type="checkbox" name="usrAllCheck" onClick="changeChkAdd();" id="all" /> <label for="all"><gsmsg:write key="sml.66" /></label>
                </div>
              </td>
              </tr>

              <tr>
              <td class="body_05">
                <logic:notEmpty name="sml020Form" property="sml020userList" >
                <logic:iterate id="usrList" name="sml020Form" property="sml020userList" >
                  <div class="usrList">
                  <input type="checkbox" name="sml010usrSids" value="<bean:write name="usrList" property="usrSid" />" />
                  &nbsp;<a href="javascript:void(0);" onclick="return usrNameClick('<bean:write name="usrList" property="usrSid" />');"><bean:write name="usrList" property="usiSei" />&nbsp;<bean:write name="usrList" property="usiMei" /></a>
                  </div>
                </logic:iterate>
                </logic:notEmpty>
              </td>
              </tr>
              </table>

            </div>
          </li>
          </ul>

          <ul id="leftmenu2" style="z-index:11">
          <li>
            <div class="text_bbw1"><img src="../smail/images/icon_hina.gif" class="img_bottom"><gsmsg:write key="sml.sml020.03" /></div>
            <div>

            <logic:notEmpty name="sml020Form" property="sml020HinaList">
            <div class="table_pad">
            <table>
            <bean:define id="mod" value="0" />
            <logic:iterate id="hinaModel" name="sml020Form" property="sml020HinaList" indexId="hinaidx">
              <tr>
              <td width="0%" align="center" valign="middle" class="table_pad">
                <%-- マーク  --%>
                <bean:define id="imgMark"><bean:write name="hinaModel" property="shnMark" /></bean:define>
                <% java.lang.String key = "none";  if (imgMap.containsKey(imgMark)) { key = imgMark; } %> <%= (java.lang.String) imgMap.get(key) %>
              </td>
              <td width="100%" class="table_pad">


                <logic:notEmpty name="hinaModel" property="shnBody">
                <bean:define id="hinabody" name="hinaModel" property="shnBody" />
                </logic:notEmpty>

                <p>
                <a href="javascript:void(0);" onclick="return hinaNameClick('<bean:write name="hinaModel" property="shnSid" />');" id="linkcmn<bean:write name="hinaModel" property="shnSid" />">
                <bean:write name="hinaModel" property="shnHname" />
                </a>
                </p>

              </td>
              </tr>


            </logic:iterate>
            </table>
            </div>
            </logic:notEmpty>


          </div>
          </li>
          </ul>


          <ul id="leftmenu3">
          <li>

          <div class="text_bbw1"><img src="../smail/images/icon_hina.gif" class="img_bottom"><gsmsg:write key="sml.sml020.04" /></div>
          <div>
            <logic:notEmpty name="sml020Form" property="sml020HinaListKjn">
            <div class="table_pad">
            <table>
            <bean:define id="mod" value="0" />
            <logic:iterate id="hinaModelKjn" name="sml020Form" property="sml020HinaListKjn" indexId="hinaidxkjn">
              <tr>
              <td width="0%" align="center" valign="middle" class="table_pad">

                <%-- マーク  --%>
                <bean:define id="imgMark"><bean:write name="hinaModelKjn" property="shnMark" /></bean:define>
                <% java.lang.String key = "none";  if (imgMap.containsKey(imgMark)) { key = imgMark; } %> <%= (java.lang.String) imgMap.get(key) %>
              </td>
              <td width="100%" class="table_pad">

                <logic:notEmpty name="hinaModelKjn" property="shnBody">
                <bean:define id="hinabody" name="hinaModelKjn" property="shnBody" />
                </logic:notEmpty>
                <p><a id="linkkjn<bean:write name="hinaModelKjn" property="shnSid" />"  href="javascript:void(0);" onclick="return hinaNameClick('<bean:write name="hinaModelKjn" property="shnSid" />');">
                <bean:write name="hinaModelKjn" property="shnHname" />
                </a>
                </p>

              </td>
              </tr>

            </logic:iterate>
            </table>
            </div>
            </logic:notEmpty>

          </div>
          </li>

          </ul>




    </td>

    <td width="10">&nbsp</td>

    <td valign="top" width="100%">

      <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">

      <tr>
      <td class="table_bg_A5B4E1" nowrap width="10%"><span class="text_bb1"><gsmsg:write key="cmn.from" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
      <td align="left" class="td_wt table_pad" width="90%">

        <table cellspacing="0" cellpadding="0" border="0">
        <tr>

        <logic:notEmpty name="sml020Form" property="sml020Atesaki" scope="request">
        <td>
        <bean:define id="detail" name="sml020Form" property="sml020Atesaki" type="jp.groupsession.v2.sml.model.SmailModel" />
        <logic:iterate id="atesaki" name="detail" property="atesakiList" indexId="idx" scope="page">
          <span class="text_base">
          <logic:equal name="atesaki" property="usrJkbn" value="<%= toroku %>"><bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" /><logic:notEqual name="detail" property="listSize" value="<%= String.valueOf(idx.intValue()) %>"></logic:notEqual></logic:equal>
          <logic:equal name="atesaki" property="usrJkbn" value="<%= delete %>"><del><bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" /></del><logic:notEqual name="detail" property="listSize" value="<%= String.valueOf(idx.intValue()) %>"></logic:notEqual></logic:equal>
          &nbsp;[<a href="javascript:void(0);" onClick="return deleteAtesaki(<bean:write name="atesaki" property="usrSid" />, 0);"><gsmsg:write key="cmn.delete" /></a>]
          </span><br>
        </logic:iterate>
        <logic:empty name="sml020Form" property="sml020Atesaki" scope="request">&nbsp;</logic:empty>
        </td>
        </logic:notEmpty>
        <td align="left" width="10%"><input type="button" value="<gsmsg:write key="sml.sml020.05" />" class="btn_user_n1" onClick="return replaceAtesakiPrm(0);"></td>
        </tr>
        </table>
      </td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" nowrap width="10%"><span class="text_bb1">CC</span></td>
      <td align="left" class="td_wt table_pad" width="90%">
        <table cellspacing="0" cellpadding="0" border="0">
        <tr>
        <logic:notEmpty name="sml020Form" property="sml020AtesakiCc" scope="request">
        <td>
          <bean:define id="detailCc" name="sml020Form" property="sml020AtesakiCc" type="jp.groupsession.v2.sml.model.SmailModel" />
          <logic:iterate id="atesakiCc" name="detailCc" property="atesakiList" indexId="ccIdx" scope="page">
            <span class="text_base">
              <logic:equal name="atesakiCc" property="usrJkbn" value="<%= toroku %>"><bean:write name="atesakiCc" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesakiCc" property="usiMei" /><logic:notEqual name="detailCc" property="listSize" value="<%= String.valueOf(ccIdx.intValue()) %>"></logic:notEqual></logic:equal>
              <logic:equal name="atesakiCc" property="usrJkbn" value="<%= delete %>"><del><bean:write name="atesakiCc" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesakiCc" property="usiMei" /></del><logic:notEqual name="detailCc" property="listSize" value="<%= String.valueOf(ccIdx.intValue()) %>"></logic:notEqual></logic:equal>
            &nbsp;[<a href="javascript:void(0);" onClick="return deleteAtesaki(<bean:write name="atesakiCc" property="usrSid" />, 1);"><gsmsg:write key="cmn.delete" /></a>]
            </span><br>
          </logic:iterate>
          <logic:empty name="sml020Form" property="sml020AtesakiCc" scope="request">&nbsp;</logic:empty>
        </td>
        </logic:notEmpty>
        <td align="left" width="10%"><input type="button" value="<gsmsg:write key="sml.sml020.02" />" class="btn_user_n1" onClick="return replaceAtesakiPrm(1);"></td>
        </tr>
        </table>
      </td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" nowrap width="10%"><span class="text_bb1">BCC</span></td>
      <td align="left" class="td_wt table_pad" width="90%">
        <table cellspacing="0" cellpadding="0" border="0">
        <tr>
        <logic:notEmpty name="sml020Form" property="sml020AtesakiBcc" scope="request">
        <td>
          <bean:define id="detailBcc" name="sml020Form" property="sml020AtesakiBcc" type="jp.groupsession.v2.sml.model.SmailModel" />
          <logic:iterate id="atesakiBcc" name="detailBcc" property="atesakiList" indexId="bccIdx" scope="page">
            <span class="text_base">
              <logic:equal name="atesakiBcc" property="usrJkbn" value="<%= toroku %>"><bean:write name="atesakiBcc" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesakiBcc" property="usiMei" /><logic:notEqual name="detailBcc" property="listSize" value="<%= String.valueOf(bccIdx.intValue()) %>"></logic:notEqual></logic:equal>
              <logic:equal name="atesakiBcc" property="usrJkbn" value="<%= delete %>"><del><bean:write name="atesakiBcc" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesakiBcc" property="usiMei" /></del><logic:notEqual name="detailBcc" property="listSize" value="<%= String.valueOf(bccIdx.intValue()) %>"></logic:notEqual></logic:equal>
              &nbsp;[<a href="javascript:void(0);" onClick="return deleteAtesaki(<bean:write name="atesakiBcc" property="usrSid" />, 2);"><gsmsg:write key="cmn.delete" /></a>]
            </span><br>
          </logic:iterate>
          <logic:empty name="sml020Form" property="sml020AtesakiBcc" scope="request">&nbsp;</logic:empty>
        </td>
        </logic:notEmpty>
        <td align="left" width="10%"><input type="button" value="<gsmsg:write key="sml.sml020.01" />" class="btn_user_n1" onClick="return replaceAtesakiPrm(2);"></td>
        </tr>
        </table>
      </td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.subject" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
      <td align="left" class="td_wt table_pad">
        <html:text styleClass="text_base" name="sml020Form" property="sml020Title" size="50" maxlength="50" />
      </td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.mark" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
      <td align="left" class="td_wt table_pad">
        <span class="text_base">
        <html:radio styleId="sml020Mark_0" name="sml020Form" property="sml020Mark" value="<%= markNone %>" /><label for="sml020Mark_0"><gsmsg:write key="cmn.no3" /></label>&nbsp;
        <html:radio styleId="sml020Mark_1" name="sml020Form" property="sml020Mark" value="<%= markTel %>" />&nbsp;<label for="sml020Mark_1"><%= (String) imgMap.get(markTel) %><%= (String) imgTextMap.get(markTel) %></label>&nbsp;
        <html:radio styleId="sml020Mark_2" name="sml020Form" property="sml020Mark" value="<%= markImp %>" />&nbsp;<label for="sml020Mark_2"><%= (String) imgMap.get(markImp) %><%= (String) imgTextMap.get(markImp) %></label>&nbsp;
        <html:radio styleId="sml020Mark_3" name="sml020Form" property="sml020Mark" value="<%= markSmaily %>" />&nbsp;<label for="sml020Mark_3"><%= (String) imgMap.get(markSmaily) %><%= (String) imgTextMap.get(markSmaily) %></label>&nbsp;
        <html:radio styleId="sml020Mark_4" name="sml020Form" property="sml020Mark" value="<%= markWorry %>" />&nbsp;<label for="sml020Mark_4"><%= (String) imgMap.get(markWorry) %><%= (String) imgTextMap.get(markWorry) %></label>&nbsp;
        <html:radio styleId="sml020Mark_5" name="sml020Form" property="sml020Mark" value="<%= markAngry %>" />&nbsp;<label for="sml020Mark_5"><%= (String) imgMap.get(markAngry) %><%= (String) imgTextMap.get(markAngry) %></label>&nbsp;
        <html:radio styleId="sml020Mark_6" name="sml020Form" property="sml020Mark" value="<%= markSadly %>" />&nbsp;<label for="sml020Mark_6"><%= (String) imgMap.get(markSadly) %><%= (String) imgTextMap.get(markSadly) %></label>&nbsp;
        <html:radio styleId="sml020Mark_7" name="sml020Form" property="sml020Mark" value="<%= markBeer %>" />&nbsp;<label for="sml020Mark_7"><%= (String) imgMap.get(markBeer) %><%= (String) imgTextMap.get(markBeer) %></label>&nbsp;
        <html:radio styleId="sml020Mark_8" name="sml020Form" property="sml020Mark" value="<%= markHart %>" />&nbsp;<label for="sml020Mark_8"><%= (String) imgMap.get(markHart) %><%= (String) imgTextMap.get(markHart) %></label>&nbsp;
        <html:radio styleId="sml020Mark_9" name="sml020Form" property="sml020Mark" value="<%= markZasetsu %>" />&nbsp;<label for="sml020Mark_9"><%= (String) imgMap.get(markZasetsu) %><%= (String) imgTextMap.get(markZasetsu) %></label>&nbsp;
        </span>
      </td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.attached" /></span></td>
      <td align="left" class="td_wt table_pad">
        <table border="0" cellpadding="0" cellspacing="0">
        <tr>
        <td width="0%" align="left" valign="top" nowrap>
          <html:select property="sml020selectFiles" styleClass="select01" multiple="true" size="3">
            <html:optionsCollection name="sml020Form" property="sml020FileLabelList" value="value" label="label" />
          </html:select>
        </td>

        <td width="0%" align="left" valign="top" nowrap><img src="../common/images/spacer.gif" width="10px" height="1px" border="0"></td>
        <td width="100%" align="left" valign="middle">
          <input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTemp('sml020selectFiles', '<bean:write name="sml020Form" property="sml020pluginId" />', '0', '0');">
&nbsp;          <input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="smlButtonPush('delete');">
        </td>
        </tr>
        </table>

      </td>
      </tr>

      <tr>
      <td colspan="2" class="table_bg_A5B4E1" align="left" nowrap><span class="text_bb1"><gsmsg:write key="wml.210" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
      </tr>
      <tr>
      <td colspan="2" align="center" class="td_wt table_pad">
        <textarea class="sml_textarea" name="sml020Body" rows="20" wrap="soft" onkeyup="showLengthStr(value, <%= maxLengthText %>, 'inputlength');" id="inputstr"><bean:write name="sml020Form" property="sml020Body" /></textarea>
        <br>
        <div align="right">
        <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthText %>&nbsp;<gsmsg:write key="cmn.character" /></span>
        </div>
      </td>
      </tr>

      </table>

      <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">
      <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td width="100%" align="right">
<script language="JavaScript">
<!--
function replaceAtesakiParm() {
  $('#atesaki-part')[0].innerHTML = '<logic:notEmpty name="sml020Form" property="sml090userSid" scope="request"><logic:iterate id="user090" name="sml020Form" property="sml090userSid" scope="request"><input type="hidden" name="cmn120userSid" value="<bean:write name="user090"/>"></logic:iterate></logic:notEmpty>';
  smlButtonPush('backFromSmailCreate');
}

function replaceAtesakiPrm(kbn) {
  if (kbn == 0) {
     $('#atesaki-part')[0].innerHTML = '<logic:notEmpty name="sml020Form" property="sml020userSid" scope="request"><logic:iterate id="user020" name="sml020Form" property="sml020userSid" scope="request"><input type="hidden" name="cmn120userSid" value="<bean:write name="user020"/>"></logic:iterate></logic:notEmpty>';
  } else if (kbn == 1) {
     $('#atesaki-part')[0].innerHTML = '<logic:notEmpty name="sml020Form" property="sml020userSidCc" scope="request"><logic:iterate id="user020Cc" name="sml020Form" property="sml020userSidCc" scope="request"><input type="hidden" name="cmn120userSid" value="<bean:write name="user020Cc"/>"></logic:iterate></logic:notEmpty>';
  } else if (kbn == 2) {
     $('#atesaki-part')[0].innerHTML = '<logic:notEmpty name="sml020Form" property="sml020userSidBcc" scope="request"><logic:iterate id="user020Bcc" name="sml020Form" property="sml020userSidBcc" scope="request"><input type="hidden" name="cmn120userSid" value="<bean:write name="user020Bcc"/>"></logic:iterate></logic:notEmpty>';
  }
  smlAtesakiSelectPush('fromselect', kbn);
}


//-->
</script>

      <input type="submit" value="OK" class="btn_ok1" onClick="return onControlSubmit();">
      <input type="button" value="<gsmsg:write key="sml.sml020.07" />" class="btn_base1" onClick="smlButtonPush('soko');">
      <logic:equal name="sml020Form" property="sml020ProcMode" value="<%= soko %>">
      <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="smlButtonPush('deleteKn');">
      </logic:equal>
      <% if (callWebmail) { %>
        <input type="button" name="btn_close" class="btn_close_n1" value="<gsmsg:write key="cmn.close" />" onClick="window.close();">
      <% } else { %>
        <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="replaceAtesakiParm();">
      <% } %>

      <div id="atesaki-part">
        <logic:notEmpty name="sml020Form" property="cmn120userSid" scope="request">
        <logic:iterate id="user" name="sml020Form" property="cmn120userSid" scope="request">
          <input type="hidden" name="cmn120userSid" value="<bean:write name="user"/>">
        </logic:iterate>
        </logic:notEmpty>
      </div>

      </td>
      </tr>
      </table>


    </td>

    </tr>
    </table>
  </td>
  </tr>
  </table>
</td>
</tr>
</table>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</html:form>

<script type="text/javascript" src="../common/js/yui-accordion/accordionview/accordionview.js?<%= GSConst.VERSION_PARAM %>"></script>

<script type="text/javascript">
  (function() {
    var menu1 = new YAHOO.widget.AccordionView('leftmenu1', {collapsible: true, expandable: true, expandItem: 0, width: '195px', animate: true, animationSpeed: '0.5'});
    var menu2 = new YAHOO.widget.AccordionView('leftmenu2', {collapsible: true, expandable: true, expandItem: 0, width: '195px', animate: true, animationSpeed: '0.5'});
    var menu3 = new YAHOO.widget.AccordionView('leftmenu3', {collapsible: true, expandable: true, expandItem: 0, width: '195px', animate: true, animationSpeed: '0.5'});

  })();
  YAHOO.namespace("ttBox");
</script>


<!-- ツールチップ -->
<logic:notEmpty name="sml020Form" property="sml020HinaList">
<logic:iterate id="hinaModel" name="sml020Form" property="sml020HinaList" indexId="hinaidx">
  <logic:notEmpty name="hinaModel" property="shnBody">
    <bean:define id="hinaTitle" name="hinaModel" property="shnTitle" />
    <bean:define id="hinabody" name="hinaModel" property="shnBody" />
    <bean:define id="ttTextCmn" value="" />
    <%

      String tmpTextT1 = (String)pageContext.getAttribute("hinaTitle",PageContext.PAGE_SCOPE);
      String tmpTextT2 = jp.co.sjts.util.StringUtilHtml.transToHTml(tmpTextT1);

      String tmpText1 = (String)pageContext.getAttribute("hinabody",PageContext.PAGE_SCOPE);
      String tmpText2 = jp.co.sjts.util.StringUtilHtml.transToHTml(tmpText1);

      jp.groupsession.v2.struts.msg.GsMessage gsMsg2 = new jp.groupsession.v2.struts.msg.GsMessage();
      String kenmei = gsMsg2.getMessage(request, "cmn.subject");
      String naiyo = gsMsg2.getMessage(request, "cmn.content");

      String tmpTextDsp = kenmei + ":";
      String tmpTextDsp1 = "<br>" + naiyo + ":";

      String tmpText = kenmei + ":" + tmpTextT2 + "<br>" + naiyo + ":" + tmpText2;

      pageContext.setAttribute("ttTextCmn", tmpText, PageContext.PAGE_SCOPE);

      %>
  </logic:notEmpty>
  <script type="text/javascript">
      YAHOO.ttBox.tt1 = new YAHOO.widget.Tooltip("ttcmn<bean:write name="hinaModel" property="shnSid" />",
      {
        context:"linkcmn<bean:write name="hinaModel" property="shnSid" />",
        text:"<bean:write name="ttTextCmn" filter="false"/>",
        showdelay:500,
        autodismissdelay:10000
      });
  </script>
</logic:iterate>
</logic:notEmpty>


<logic:notEmpty name="sml020Form" property="sml020HinaListKjn">
<logic:iterate id="hinaModelKjn" name="sml020Form" property="sml020HinaListKjn" indexId="hinaidxkjn">
  <logic:notEmpty name="hinaModelKjn" property="shnBody">
    <bean:define id="hinaTitle" name="hinaModelKjn" property="shnTitle" />
    <bean:define id="hinaBody" name="hinaModelKjn" property="shnBody" />
    <bean:define id="hinaSid" name="hinaModelKjn" property="shnSid" />
    <bean:define id="ttText" value="" />
    <%
      jp.groupsession.v2.struts.msg.GsMessage gsMsg2 = new jp.groupsession.v2.struts.msg.GsMessage();
      String kenmei = gsMsg2.getMessage(request, "cmn.subject");
      String naiyo = gsMsg2.getMessage(request, "cmn.content");

      String tmpTextT1 = (String)pageContext.getAttribute("hinaTitle",PageContext.PAGE_SCOPE);
      String tmpTextT2 = jp.co.sjts.util.StringUtilHtml.transToHTml(tmpTextT1);

      String tmpTextB1 = (String)pageContext.getAttribute("hinaBody",PageContext.PAGE_SCOPE);
      String tmpTextB2 = jp.co.sjts.util.StringUtilHtml.transToHTml(tmpTextB1);

      String tmpTextDsp = kenmei + ":" + tmpTextT2
                                  + "<br>"
                                  + naiyo + ":"
                                  + tmpTextB2;
      pageContext.setAttribute("ttText", tmpTextDsp, PageContext.PAGE_SCOPE);
    %>
  </logic:notEmpty>
  <script type="text/javascript">
    YAHOO.ttBox.tt2 = new YAHOO.widget.Tooltip("ttkjn<bean:write name="hinaModelKjn" property="shnSid" />",
    {
      context:"linkkjn<bean:write name="hinaModelKjn" property="shnSid" />",
      text:"<bean:write name="ttText" filter="false" />",
      showdelay:500,
      autodismissdelay:10000
    });
  </script>
</logic:iterate>
</logic:notEmpty>



</body>
</html:html>