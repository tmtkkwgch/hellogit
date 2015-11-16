<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhCir030Form"; %>
<%@page import="jp.groupsession.v2.cir.GSConstCircular"%>
<%@page import="jp.groupsession.v2.cmn.GSConst"%>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cir.cir020.1" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>

</head>

<body>

<html:form method="post" action="/mobile/mh_cir030">

<% String maxLengthMemo = String.valueOf(jp.groupsession.v2.cir.GSConstCircular.MAX_LENGTH_MEMO); %>
<% String jusin = String.valueOf(jp.groupsession.v2.cir.GSConstCircular.MODE_JUSIN); %>
<% String main = String.valueOf(jp.groupsession.v2.cir.GSConstCircular.MODE_JUSIN_MAIN); %>
<% String gomi = String.valueOf(jp.groupsession.v2.cir.GSConstCircular.MODE_GOMI); %>


<gsmsg:define id="simei" msgkey="cmn.name" />
<gsmsg:define id="kakunin" msgkey="cmn.check" />
<gsmsg:define id="memo" msgkey="cir.11" />
<gsmsg:define id="koushin" msgkey="cmn.last.modified" />

<%

  int[] sortKeyList = new int[] {
                       jp.groupsession.v2.cir.GSConstCircular.SAKI_SORT_NAME,
                       jp.groupsession.v2.cir.GSConstCircular.SAKI_SORT_KAKU,
                       jp.groupsession.v2.cir.GSConstCircular.SAKI_SORT_MEMO,
                       jp.groupsession.v2.cir.GSConstCircular.SAKI_SORT_SAISYU
                       };
  String[] title_width = new String[] { "0", "5", "90", "5"};
  String[] titleList = new String[] {
                        simei,
                        kakunin,
                        memo,
                        koushin
                       };
  int order_desc = jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC;
  int order_asc = jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC;
%>

<logic:empty name="mbhCir030Form" property="cir060dspId" scope="request">
<bean:define id="cmdMode" name="mbhCir030Form" property="cir020cmdMode" />
</logic:empty>
<logic:notEmpty name="mbhCir030Form" property="cir060dspId" scope="request">
<bean:define id="cmdMode" name="mbhCir030Form" property="cir060svSyubetsu" />
</logic:notEmpty>




<bean:define id="dspMdl" name="mbhCir030Form" property="cir030dspMdl" />
<bean:define id="orderKey" name="mbhCir030Form" property="cir030orderKey" />
<bean:define id="sortKbn" name="mbhCir030Form" property="cir030sortKey" />
<% int iOrderKey = ((Integer) orderKey).intValue(); %>
<% int iSortKbn = ((Integer) sortKbn).intValue(); %>



<input type="hidden" name="CMD" value="conf">
<input type="hidden" name="cmdMode" value="<bean:write name="cmdMode" />">
<input type="hidden" name="cir030cvwConf" value="<bean:write name="dspMdl" property="cvwConf" />">
<input type="hidden" name="cir030downLoadFlg" value="">
<input type="hidden" name="cir030memoEdit" value="<bean:write name="mbhCir030Form" property="memoFlg" />">

<html:hidden property="cirViewAccount" />
<html:hidden property="cirViewAccountName" />
<html:hidden property="cir030binSid" />
<html:hidden property="cir020cmdMode" />
<html:hidden property="cir020orderKey" />
<html:hidden property="cir020sortKey" />
<html:hidden property="cir020pageNum1" />
<html:hidden property="cir020pageNum2" />
<html:hidden property="cir020selectInfSid" />
<html:hidden property="mikakkuCount" />
<html:hidden property="schWeekDate" />
<html:hidden property="schDailyDate" />
<html:hidden property="cir030orderKey" />
<html:hidden property="cir030sortKey" />
<html:hidden property="cirDelFlg" />

<logic:notEmpty name="mbhCir030Form" property="cir020delInfSid" scope="request">
<logic:iterate id="item" name="mbhCir030Form" property="cir020delInfSid" scope="request">
  <input type="hidden" name="cir020delInfSid" value="<bean:write name="item"/>">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="cir060searchFlg" />
<html:hidden property="cir010svSearchWord" />
<html:hidden property="cir060svSyubetsu" />
<html:hidden property="cir060svGroupSid" />
<html:hidden property="cir060svUserSid" />
<html:hidden property="cir060svWordKbn" />
<html:hidden property="cir060svSort1" />
<html:hidden property="cir060svOrder1" />
<html:hidden property="cir060svSort2" />
<html:hidden property="cir060svOrder2" />

<html:hidden property="cir060syubetsu" />
<html:hidden property="cir060groupSid" />
<html:hidden property="cir060userSid" />
<html:hidden property="cir060wordKbn" />
<html:hidden property="cir060sort1" />
<html:hidden property="cir060order1" />
<html:hidden property="cir060sort2" />
<html:hidden property="cir060order2" />
<html:hidden property="cir060pageNum1" />
<html:hidden property="cir060pageNum2" />
<html:hidden property="cir060dspId" />

<logic:notEmpty name="mbhCir030Form" property="cmn120userSid" scope="request">
  <logic:iterate id="item" name="mbhCir030Form" property="cmn120userSid" scope="request">
    <input type="hidden" name="cmn120userSid" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhCir030Form" property="cmn120svUserSid" scope="request">
  <logic:iterate id="item" name="mbhCir030Form" property="cmn120svUserSid" scope="request">
    <input type="hidden" name="cmn120svUserSid" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhCir030Form" property="cir060searchTarget" scope="request">
  <logic:iterate id="item" name="mbhCir030Form" property="cir060searchTarget" scope="request">
    <input type="hidden" name="cir060searchTarget" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhCir030Form" property="cir060svSearchTarget" scope="request">
  <logic:iterate id="item" name="mbhCir030Form" property="cir060svSearchTarget" scope="request">
    <input type="hidden" name="cir060svSearchTarget" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<!-- BODY -->
<b><%= emojiMemo.toString() %><gsmsg:write key="cir.5" /><br>
<gsmsg:write key="cir.cir030.2" />

<hr>

<logic:equal name="mbhCir030Form" property="cir030PrevBound" value="true">
  <input name="prev030" value="<gsmsg:write key="cmn.previous" />" class="btn_previous_n1" type="submit">
</logic:equal>

<logic:equal name="mbhCir030Form" property="cir030NextBound" value="true">
  <input name="next030" value="<gsmsg:write key="cmn.next" />" class="btn_next_n1" type="submit">
</logic:equal>

<br>

<logic:equal name="cmdMode" value="<%= gomi %>">
  <input name="deleteButton" value="<gsmsg:write key="cmn.delete2" />" class="btn_dell_n1" type="submit">
  <%-- input type="button" value="<gsmsg:write key="cmn.undo" />" class="btn_modosu_n1" onClick="buttonPush('comeback');"--%>
  <input name="cir030Back" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" type="submit">
</logic:equal>

<logic:notEqual name="cmdMode" value="<%= gomi %>">
<logic:equal name="dspMdl" property="cvwConf" value="<%= String.valueOf(GSConstCircular.CONF_UNOPEN) %>">
  <input type="submit" value="<gsmsg:write key="cir.cir020.2" />" class="btn_base1">
  <input name="deleteButton" value="<gsmsg:write key="cmn.delete2" />" class="btn_dell_n1" type="submit">
  <input name="cir030Back" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" type="submit">
</logic:equal>

<logic:notEqual name="dspMdl" property="cvwConf" value="<%= String.valueOf(GSConstCircular.CONF_UNOPEN) %>">


<logic:equal name="mbhCir030Form" property="memoFlg" value="0">
  <input name="editButton" value="<gsmsg:write key="cmn.edit2" />" class="btn_base1" type="submit">
</logic:equal>


  <input name="deleteButton" value="<gsmsg:write key="cmn.delete2" />" class="btn_dell_n1" type="submit">
  <input name="cir030Back" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" type="submit">
  </logic:notEqual>
</logic:notEqual>

<hr>

<logic:messagesPresent message="false">
  <html:errors/><br>
</logic:messagesPresent>

<logic:equal name="cmdMode" value="<%= gomi %>">
  <span class="text_bb1"><gsmsg:write key="cir.cir030.3" /></span>
  <span class="text_base"><bean:write name="mbhCir030Form" property="kakuninStr" /></span>
</logic:equal>

<bean:write name="dspMdl" property="dspCifAdate" />

<br>

<span class="text_bb1">■<gsmsg:write key="cmn.title" />:</span>
<span class="text_base"><bean:write name="dspMdl" property="cifTitle" /></span>

<br>

<span class="font_xsmall">■<gsmsg:write key="cmn.receive" /><gsmsg:write key="wml.102" />:</span>
<span class="text_base"><bean:write name="mbhCir030Form" property="cirViewAccountName" /></span>

<br>

<span class="text_bb1">■<gsmsg:write key="cir.2" />:</span>

<span class="text_base">
  <logic:equal name="dspMdl" property="cacJkbn" value="<%= String.valueOf(GSConst.JTKBN_TOROKU) %>">
    <bean:write name="dspMdl" property="cacName" />
  </logic:equal>
  <logic:notEqual name="dspMdl" property="cacJkbn" value="<%= String.valueOf(GSConst.JTKBN_TOROKU) %>">
    <del><bean:write name="dspMdl" property="cacName" /></del>
  </logic:notEqual>
</span>

<br>

■<gsmsg:write key="cmn.content" />:<br>
<span class="text_cir_base"><bean:write name="dspMdl" property="cifValue" filter="false" /></span>

<br>
<br>

<logic:notEmpty name="mbhCir030Form" property="cir030fileList" scope="request">
  <span class="text_bb1">■<gsmsg:write key="cmn.attached" />:</span><br>
  <logic:iterate id="fileMdl" name="mbhCir030Form" property="cir030fileList" scope="request">

  <logic:notEmpty name="fileMdl" property="binFileExtension">
  <bean:define id="fext" name="fileMdl" property="binFileExtension"  type="java.lang.String" />
  <%
  String dext = ((String)pageContext.getAttribute("fext",PageContext.PAGE_SCOPE));
  if (dext != null) {
      dext = dext.toLowerCase();
      if (jp.groupsession.v2.cmn.biz.CommonBiz.isViewFile(dext)) {
  %>
  <img src="../circular/cir030.do<%= jsessionId.toString() %>?CMD=tempview&cir010selectInfSid=<bean:write name="mbhCir030Form" property="cir020selectInfSid" />&cirViewAccount=<bean:write name="mbhCir030Form" property="cirViewAccount"/>&cir020binSid=<bean:write name="fileMdl" property="binSid" />" name="pictImage<bean:write name="fileMdl" property="binSid" />" onload="initImageView('pictImage<bean:write name="fileMdl" property="binSid" />');">
  <br>
  <%
      }
  }
  %>
  </logic:notEmpty>

  <span class="text_link_min"><bean:write name="fileMdl" property="binFileName" /><bean:write name="fileMdl" property="binFileSizeDsp" /></span>
  <br>
  </logic:iterate>
</logic:notEmpty>

<logic:notEqual name="mbhCir030Form" property="cir020cmdMode" value="3">
<br>
■<span class="text_bb1"><gsmsg:write key="cir.11" /></span><br>
<logic:equal name="dspMdl" property="cifMemoFlg" value="1">
  <span class="font_string_count">
  <bean:write name="dspMdl" property="dspCifMemoDate" /></span><br>
</logic:equal>
<textarea class="text_cir_base" name="cir030memo"  cols="16" rows="2" id="inputstr"><bean:write name="mbhCir030Form" property="cir030memo" filter="false"/></textarea>
</logic:notEqual>
<br>


<logic:equal name="dspMdl" property="cifShow" value="0">
<span class="text_bb1">■<gsmsg:write key="cir.14" /></span><br>
  <html:select name="mbhCir030Form" property="cirMemListGroup" styleClass="select01">
    <html:optionsCollection name="mbhCir030Form" property="cirMemListGroupCombo" value="value" label="label" />
  </html:select>
 <input name="groupButton" value="選択" type="submit">
<br>


<%--
<%
      int titlecol = 1;
      for (int i = 0; i < sortKeyList.length; i++) {
        if (jp.groupsession.v2.cir.GSConstCircular.SAKI_SORT_KAKU == sortKeyList[i]) {
            titlecol = 2;
        } else {
            titlecol = 1;
        }
        if (iSortKbn == sortKeyList[i]) {
          if (iOrderKey == order_desc) {
%>
            <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= sortKeyList[i] %>, <%= order_asc %>);"><span class="text_tlw"><%= titleList[i] %>▼</span></a>
<%
          } else {
%>
            <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= sortKeyList[i] %>, <%= order_desc %>);"><span class="text_tlw"><%= titleList[i] %>▲</span></a>
<%
          }
        } else {
%>
            <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= sortKeyList[i] %>, <%= order_asc %>);"><span class="text_tlw"><%= titleList[i] %></span></a>
<%
        }
      }
%>

<br>
--%>

<logic:notEmpty name="mbhCir030Form" property="cir030memList" scope="request">
  <logic:iterate id="memMdl" name="mbhCir030Form" property="cir030memList" scope="request">
  <hr>
  <span class="text_base">
  <logic:equal name="memMdl" property="cacJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_TOROKU) %>">
    <bean:write name="memMdl" property="cacName" />
  </logic:equal>
  <logic:notEqual name="memMdl" property="cacJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_TOROKU) %>">
    <del><bean:write name="memMdl" property="cacName" /></del>
  </logic:notEqual>
  </span>

  <logic:equal name="memMdl" property="cvwConf" value="<%= String.valueOf(jp.groupsession.v2.cir.GSConstCircular.CONF_OPEN) %>">
    <img alt="<gsmsg:write key="cmn.confirmed" />" height="16" src="../common/images/check.gif" width="16">
  </logic:equal>
  <logic:notEqual name="memMdl" property="cvwConf" value="<%= String.valueOf(jp.groupsession.v2.cir.GSConstCircular.CONF_OPEN) %>">
  &nbsp;
  </logic:notEqual>

  <%--
  <logic:equal name="memMdl" property="cvwConf" value="<%= String.valueOf(jp.groupsession.v2.cir.GSConstCircular.CONF_OPEN) %>">
    <span class="text_base"><bean:write name="memMdl" property="openTime" /></span>
  </logic:equal>
  <logic:notEqual name="memMdl" property="cvwConf" value="<%= String.valueOf(jp.groupsession.v2.cir.GSConstCircular.CONF_OPEN) %>">
    &nbsp;
  </logic:notEqual>
  --%>
  <br>

  <span class="text_base"><bean:write name="memMdl" property="cvwMemo" filter="false" /></span>
  <logic:equal name="memMdl" property="circularUse" value="false">
  <logic:notEmpty name="memMdl" property="cvwMemo"><br></logic:notEmpty>
    <span class="text_error">※<gsmsg:write key="cir.15" /></span>
  </logic:equal>


  <%--
  <logic:equal name="memMdl" property="cvwConf" value="<%= String.valueOf(jp.groupsession.v2.cir.GSConstCircular.CONF_OPEN) %>">
    <span class="text_base"><bean:write name="memMdl" property="dspCvwEdate" /></span>
  </logic:equal>
  <logic:notEqual name="memMdl" property="cvwConf" value="<%= String.valueOf(jp.groupsession.v2.cir.GSConstCircular.CONF_OPEN) %>">
    &nbsp;
  </logic:notEqual>
  --%>

  </logic:iterate>
</logic:notEmpty>


</logic:equal>

<br>

<hr>

  <logic:equal name="cmdMode" value="<%= gomi %>">
    <input name="deleteButton" value="<gsmsg:write key="cmn.delete2" />" class="btn_dell_n1" type="submit">
    <%-- input type="button" value="<gsmsg:write key="cmn.undo" />" class="btn_modosu_n1" onClick="buttonPush('comeback');"--%>
    <input name="cir030Back" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" type="submit">
  </logic:equal>

  <logic:notEqual name="cmdMode" value="<%= gomi %>">
  <logic:equal name="dspMdl" property="cvwConf" value="<%= String.valueOf(GSConstCircular.CONF_UNOPEN) %>">

    <input type="submit" value="<gsmsg:write key="cir.cir020.2" />" class="btn_base1" onClick="confClick();">
    <input name="deleteButton" value="<gsmsg:write key="cmn.delete2" />" class="btn_dell_n1" type="submit">
    <input name="cir030Back" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" type="submit">
  </logic:equal>

  <logic:notEqual name="dspMdl" property="cvwConf" value="<%= String.valueOf(GSConstCircular.CONF_UNOPEN) %>">

    <logic:equal name="mbhCir030Form" property="memoFlg" value="0">
      <input name="editButton" value="<gsmsg:write key="cmn.edit2" />" class="btn_base1" type="submit">
    </logic:equal>

    <input name="deleteButton" value="<gsmsg:write key="cmn.delete2" />" class="btn_dell_n1" type="submit">
    <input name="cir030Back" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" type="submit">
  </logic:notEqual>
</logic:notEqual>

<br>

  <logic:equal name="mbhCir030Form" property="cir030PrevBound" value="true">
    <input name="prev030" value="<gsmsg:write key="cmn.previous" />" class="btn_previous_n1" type="submit">
  </logic:equal>

  <logic:equal name="mbhCir030Form" property="cir030NextBound" value="true">
    <input name="next030" value="<gsmsg:write key="cmn.next" />" class="btn_next_n1" type="submit">
  </logic:equal>

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>