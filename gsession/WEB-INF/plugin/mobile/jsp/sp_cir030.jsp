<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>
<%@page import="jp.groupsession.v2.cir.GSConstCircular"%>
<%@page import="jp.groupsession.v2.cmn.GSConst"%>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cir.cir020.1" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "cir"; %>
<% thisForm = "mbhCir030Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form method="post" action="/mobile/sp_cir030">

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
<html:hidden property="mobileType" value="1"/>
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
<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<a href="#" onClick="buttonPush('back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
<img src="../mobile/sp/imgages/cir_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="cir.5" /><br>
    <gsmsg:write key="cir.cir030.2" />
  </h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<div data-role="controlgroup" data-type="horizontal" align="center">
  <span class="font_xsmall">
    <logic:equal name="mbhCir030Form" property="cir030PrevBound" value="false">
      <input type="button" value="<gsmsg:write key="cmn.previous" />" class="btn_base1_dis_previous" disabled="true" data-icon="arrow-l">
    </logic:equal>
    <logic:equal name="mbhCir030Form" property="cir030PrevBound" value="true">
      <input name="prev030" value="<gsmsg:write key="cmn.previous" />" class="btn_previous_n1" type="submit" data-icon="arrow-l">
    </logic:equal>

    <logic:equal name="mbhCir030Form" property="cir030NextBound" value="false">
      <input type="button" value="<gsmsg:write key="cmn.next" />" class="btn_base1_dis_next" disabled="true" data-icon="arrow-r" data-iconpos="right">
    </logic:equal>
    <logic:equal name="mbhCir030Form" property="cir030NextBound" value="true">
      <input name="next030" value="<gsmsg:write key="cmn.next" />" class="btn_next_n1" type="submit" data-icon="arrow-r" data-iconpos="right">
    </logic:equal>
  </span>
</div>

  <logic:equal name="cmdMode" value="<%= gomi %>">
    <div data-role="controlgroup" data-type="horizontal" align="center">
      <span class="font_small">
        <input name="deleteButton" value="<gsmsg:write key="cmn.delete2" />" class="btn_dell_n1" type="submit">
        <%-- input type="button" value="<gsmsg:write key="cmn.undo" />" class="btn_modosu_n1" onClick="buttonPush('comeback');"--%>
        <input name="cir030Back" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" type="submit">
      </span>
    </div>
  </logic:equal>

  <logic:notEqual name="cmdMode" value="<%= gomi %>">
  <logic:equal name="dspMdl" property="cvwConf" value="<%= String.valueOf(GSConstCircular.CONF_UNOPEN) %>">
    <div data-role="controlgroup" data-type="horizontal" align="center">
      <span class="font_small">
        <input name="deleteButton" value="<gsmsg:write key="cmn.delete2" />" class="btn_dell_n1" type="submit">
        <input type="submit" value="<gsmsg:write key="cir.cir020.2" />" class="btn_base1" onClick="confClick();">
        <input name="cir030Back" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" type="submit">
      </span>
    </div>
  </logic:equal>

<logic:notEqual name="dspMdl" property="cvwConf" value="<%= String.valueOf(GSConstCircular.CONF_UNOPEN) %>">
    <div data-role="controlgroup" data-type="horizontal" align="center">
      <span class="font_small">
        <input name="deleteButton" value="<gsmsg:write key="cmn.delete2" />" class="btn_dell_n1" type="submit">
        <logic:equal name="mbhCir030Form" property="memoFlg" value="0">
          <input name="editButton" value="<gsmsg:write key="cmn.edit2" />" class="btn_base1" type="submit">
        </logic:equal>
        <input name="cir030Back" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" type="submit">
      </span>
    </div>
  </logic:notEqual>
</logic:notEqual>

<logic:messagesPresent message="false">
  <html:errors/><br>
</logic:messagesPresent>

<logic:equal name="cmdMode" value="<%= gomi %>">
  <span class="text_bb1"><gsmsg:write key="cir.cir030.3" /></span>
  <span class="text_base"><bean:write name="mbhCir030Form" property="kakuninStr" /></span>
</logic:equal>

<ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">

  <li>
    <span class="font_xsmall">■<gsmsg:write key="cir.13" /></span><br>
    <bean:write name="dspMdl" property="dspCifAdate" />
  </li>

  <li>
    <span class="font_xsmall">■<gsmsg:write key="cmn.title" /></span><br>
    <span class="text_base"><bean:write name="dspMdl" property="cifTitle" /></span>
  </li>

  <li>
  <span class="font_xsmall">■<gsmsg:write key="cmn.receive" /><gsmsg:write key="wml.102" /></span><br>
  <span class="text_base"><bean:write name="mbhCir030Form" property="cirViewAccountName" /></span>
  </li>


  <li>
    <span class="font_xsmall">■<gsmsg:write key="cir.2" /></span><br>
    <span class="text_base">
      <logic:equal name="dspMdl" property="cacJkbn" value="<%= String.valueOf(GSConst.JTKBN_TOROKU) %>">
        <bean:write name="dspMdl" property="cacName" />
      </logic:equal>
      <logic:notEqual name="dspMdl" property="cacJkbn" value="<%= String.valueOf(GSConst.JTKBN_TOROKU) %>">
        <del><bean:write name="dspMdl" property="cacName" /></del>
      </logic:notEqual>
    </span>
  </li>

  <li>
    <span class="font_xsmall">■<gsmsg:write key="cmn.content" /></span><br>
    <span class="text_cir_base"><bean:write name="dspMdl" property="cifValue" filter="false" /></span>
  </li>

</ul>


  <logic:notEmpty name="mbhCir030Form" property="cir030fileList" scope="request">

    <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
      <li data-role="list-divider" style="background:#ffffff;">
        <div class="font_xsmall" align="center"><gsmsg:write key="cmn.attached" /></div>
      </li>

      <logic:iterate id="fileMdl" name="mbhCir030Form" property="cir030fileList" scope="request">
        <li>
          <a href="../mobile/sp_cir030.do?CMD=conf&mobileType=1&cir020selectInfSid=<bean:write name="mbhCir030Form" property="cir020selectInfSid" />&cirViewAccount=<bean:write name="mbhCir030Form" property="cirViewAccount"/>&cir030downLoadFlg=1&cir030binSid=<bean:write name="fileMdl" property="binSid" />">
            <span class="color_blue"><bean:write name="fileMdl" property="binFileName" /><bean:write name="fileMdl" property="binFileSizeDsp" /></span>
          </a>
        </li>
      </logic:iterate>

    </ul>
  </logic:notEmpty>



<logic:notEqual name="mbhCir030Form" property="cir020cmdMode" value="3">
  ■<span class="font_xsmall"><gsmsg:write key="cir.11" /></span><br>

  <logic:equal name="dspMdl" property="cifMemoFlg" value="1">
    <span class="font_xxsmall"><gsmsg:write key="cir.53" />
    <bean:write name="dspMdl" property="dspCifMemoDate" /></span><br>
  </logic:equal>

<textarea class="text_cir_base" name="cir030memo" id="inputstr"><bean:write name="mbhCir030Form" property="cir030memo" filter="false"/></textarea>
</logic:notEqual>
<br>
<br>
<logic:equal name="dspMdl" property="cifShow" value="0">
  <span class="font_xsmall">■<gsmsg:write key="cir.14" /></span><br>
  <div class="font_small" align="center">
    <html:select name="mbhCir030Form" property="cirMemListGroup" styleClass="select01" onchange="changeCombo2('changeGroup');">
      <html:optionsCollection name="mbhCir030Form" property="cirMemListGroupCombo" value="value" label="label" />
    </html:select>
  </div>

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
  <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
    <logic:iterate id="memMdl" name="mbhCir030Form" property="cir030memList" scope="request">
      <li>
        <span class="text_base">
        <logic:equal name="memMdl" property="cacJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_TOROKU) %>">
          <bean:write name="memMdl" property="cacName" />
        </logic:equal>
        <logic:notEqual name="memMdl" property="cacJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_TOROKU) %>">
          <del><bean:write name="memMdl" property="cacName" /></del>
        </logic:notEqual>
        </span>

        <logic:equal name="memMdl" property="cvwConf" value="<%= String.valueOf(jp.groupsession.v2.cir.GSConstCircular.CONF_OPEN) %>">
          <img alt="<gsmsg:write key="cmn.confirmed" />" height="16" src="../common/images/check.gif" width="16" class="ui-li-icon ui-li-thumb">
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
      </li>
    </logic:iterate>
  </ul>
</logic:notEmpty>


</logic:equal>

  <logic:equal name="cmdMode" value="<%= gomi %>">
    <div data-role="controlgroup" data-type="horizontal" align="center">
      <span class="font_small">
        <input name="deleteButton" value="<gsmsg:write key="cmn.delete2" />" class="btn_dell_n1" type="submit">
        <%-- input type="button" value="<gsmsg:write key="cmn.undo" />" class="btn_modosu_n1" onClick="buttonPush('comeback');"--%>
        <input name="cir030Back" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" type="submit">
      </span>
    </div>
  </logic:equal>

  <logic:notEqual name="cmdMode" value="<%= gomi %>">
  <logic:equal name="dspMdl" property="cvwConf" value="<%= String.valueOf(GSConstCircular.CONF_UNOPEN) %>">
    <div data-role="controlgroup" data-type="horizontal" align="center">
      <span class="font_small">
        <input name="deleteButton" value="<gsmsg:write key="cmn.delete2" />" class="btn_dell_n1" type="submit">
        <input type="submit" value="<gsmsg:write key="cir.cir020.2" />" class="btn_base1" onClick="confClick();">
        <input name="cir030Back" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" type="submit">
      </span>
    </div>
  </logic:equal>

<logic:notEqual name="dspMdl" property="cvwConf" value="<%= String.valueOf(GSConstCircular.CONF_UNOPEN) %>">

    <div data-role="controlgroup" data-type="horizontal" align="center">
      <span class="font_small">
        <input name="deleteButton" value="<gsmsg:write key="cmn.delete2" />" class="btn_dell_n1" type="submit">
        <logic:equal name="mbhCir030Form" property="memoFlg" value="0">
          <input name="editButton" value="<gsmsg:write key="cmn.edit2" />" class="btn_base1" type="submit">
        </logic:equal>
        <input name="cir030Back" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" type="submit">
      </span>
    </div>
  </logic:notEqual>
</logic:notEqual>

<div data-role="controlgroup" data-type="horizontal" align="center">
  <span class="font_xsmall">
    <logic:equal name="mbhCir030Form" property="cir030PrevBound" value="false">
      <input type="button" value="<gsmsg:write key="cmn.previous" />" class="btn_base1_dis_previous" disabled="true" data-icon="arrow-l">
    </logic:equal>
    <logic:equal name="mbhCir030Form" property="cir030PrevBound" value="true">
      <input name="prev030" value="<gsmsg:write key="cmn.previous" />" class="btn_previous_n1" type="submit" data-icon="arrow-l">
    </logic:equal>

    <logic:equal name="mbhCir030Form" property="cir030NextBound" value="false">
      <input type="button" value="<gsmsg:write key="cmn.next" />" class="btn_base1_dis_next" disabled="true" data-icon="arrow-r" data-iconpos="right">
    </logic:equal>
    <logic:equal name="mbhCir030Form" property="cir030NextBound" value="true">
      <input name="next030" value="<gsmsg:write key="cmn.next" />" class="btn_next_n1" type="submit" data-icon="arrow-r" data-iconpos="right">
    </logic:equal>
  </span>
</div>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>
</div><!-- /page -->

</body>
</html:html>