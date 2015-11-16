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
<% thisForm = "mbhCir040Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form method="post" action="/mobile/sp_cir040">

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

<logic:empty name="mbhCir040Form" property="cir060dspId" scope="request">
<bean:define id="cmdMode" name="mbhCir040Form" property="cir020cmdMode" />
</logic:empty>
<logic:notEmpty name="mbhCir040Form" property="cir060dspId" scope="request">
<bean:define id="cmdMode" name="mbhCir040Form" property="cir060svSyubetsu" />
</logic:notEmpty>

<bean:define id="dspMdl" name="mbhCir040Form" property="cir030dspMdl" />
<bean:define id="orderKey" name="mbhCir040Form" property="cir030orderKey" />
<bean:define id="sortKbn" name="mbhCir040Form" property="cir030sortKey" />
<% int iOrderKey = ((Integer) orderKey).intValue(); %>
<% int iSortKbn = ((Integer) sortKbn).intValue(); %>

<input type="hidden" name="helpPrm" value="<bean:write name="cmdMode" />">

<input type="hidden" name="CMD" value="download">
<input type="hidden" name="cmdMode" value="<bean:write name="cmdMode" />">
<input type="hidden" name="cirEntryMode" value="<%= String.valueOf(jp.groupsession.v2.cir.GSConstCircular.CIR_ENTRYMODE_COPY) %>">
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
<html:hidden property="cir030orderKey" />
<html:hidden property="cir030sortKey" />
<html:hidden property="cir020orderKey" />
<html:hidden property="cir020sortKey" />

<logic:notEmpty name="mbhCir040Form" property="cir020delInfSid" scope="request">
<logic:iterate id="item" name="mbhCir040Form" property="cir020delInfSid" scope="request">
  <input type="hidden" name="cir020delInfSid" value="<bean:write name="item"/>">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="cir030binSid" />
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



<logic:notEmpty name="mbhCir040Form" property="cmn120userSid" scope="request">
  <logic:iterate id="item" name="mbhCir040Form" property="cmn120userSid" scope="request">
    <input type="hidden" name="cmn120userSid" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhCir040Form" property="cmn120svUserSid" scope="request">
  <logic:iterate id="item" name="mbhCir040Form" property="cmn120svUserSid" scope="request">
    <input type="hidden" name="cmn120svUserSid" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhCir040Form" property="cir060searchTarget" scope="request">
  <logic:iterate id="item" name="mbhCir040Form" property="cir060searchTarget" scope="request">
    <input type="hidden" name="cir060searchTarget" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhCir040Form" property="cir060svSearchTarget" scope="request">
  <logic:iterate id="item" name="mbhCir040Form" property="cir060svSearchTarget" scope="request">
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
    <!-- 前へ -->
    <logic:equal name="mbhCir040Form" property="cir030PrevBound" value="false">
      <input type="button" value="<gsmsg:write key="cmn.previous" />" class="btn_base1_dis" disabled="true" data-icon="arrow-l">
    </logic:equal>
    <logic:equal name="mbhCir040Form" property="cir030PrevBound" value="true">
      <input name="prev040" value="<gsmsg:write key="cmn.previous" />" class="btn_previous_n1" type="submit" data-icon="arrow-l">
    </logic:equal>

    <!-- 次へ -->
    <logic:equal name="mbhCir040Form" property="cir030NextBound" value="false">
      <input type="button" value="<gsmsg:write key="cmn.next" />" class="btn_base1_dis" disabled="true" data-icon="arrow-r" data-iconpos="right">
    </logic:equal>
    <logic:equal name="mbhCir040Form" property="cir030NextBound" value="true">
      <input name="next040" value="<gsmsg:write key="cmn.next" />" class="btn_next_n1" type="submit" data-icon="arrow-r" data-iconpos="right">
    </logic:equal>
  </span>
</div>

<div data-role="controlgroup" data-type="horizontal" align="center">
  <span class="font_small">
    <logic:notEqual name="mbhCir040Form" property="cir020cmdMode"  value="<%= gomi %>">
      <input name="copyButton" value="<gsmsg:write key="cmn.register.copy.new" />" class="btn_dell_n1" type="submit">
    </logic:notEqual>

    <!-- 削除 -->
    <input name="deleteButton" value="<gsmsg:write key="cmn.delete2" />" class="btn_dell_n1" type="submit">

    <logic:equal name="cmdMode" value="<%= gomi %>">
      <%-- input type="button" value="<gsmsg:write key="cmn.undo" />" class="btn_modosu_n1" onClick="buttonPush('comeback');" --%>
    </logic:equal>

    <!-- 戻る -->
    <input name="cir040Back" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" type="submit">
  </span>
</div>


<ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">

  <li>
    <span class="font_xsmall">■<gsmsg:write key="cir.2" /></span><br>
    <span class="text_base"><bean:write name="mbhCir040Form" property="cirViewAccountName" /></span>
  </li>

  <li>
    <span class="font_xsmall">■<gsmsg:write key="cir.13" /></span><br>
    <span class="text_base"><bean:write name="dspMdl" property="dspCifAdate" /></span>
  </li>

  <li>
    <span class="font_xsmall">■<gsmsg:write key="cmn.title" /></span><br>
    <span class="text_base"><bean:write name="dspMdl" property="cifTitle" /></span>
  </li>

  <li>
    <span class="font_xsmall">■<gsmsg:write key="cmn.content" /></span><br>
    <span class="text_cir_base"><bean:write name="dspMdl" property="cifValue" filter="false" /></span>
  </li>

  <li>
  <span class="font_xsmall">■<gsmsg:write key="cir.cir030.3" /></span><br>
    <logic:equal name="dspMdl" property="cifShow" value="0"><gsmsg:write key="cmn.public" /></logic:equal>
    <logic:notEqual name="dspMdl" property="cifShow" value="0"><gsmsg:write key="cmn.private" /></logic:notEqual>
  </li>
</ul>

  <logic:notEmpty name="mbhCir040Form" property="cir030fileList" scope="request">

    <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
      <li data-role="list-divider" style="background:#ffffff;">
        <div class="font_xsmall" align="center"><gsmsg:write key="cmn.attached" /></div>
      </li>

      <logic:iterate id="fileMdl" name="mbhCir040Form" property="cir030fileList" scope="request">
        <li>
          <a href="../mobile/sp_cir040.do?CMD=download&mobileType=1&cir020selectInfSid=<bean:write name="mbhCir040Form" property="cir020selectInfSid" />&cirViewAccount=<bean:write name="mbhCir040Form" property="cirViewAccount"/>&cir030downLoadFlg=1&cir030binSid=<bean:write name="fileMdl" property="binSid" />">
          <logic:notEmpty name="fileMdl" property="binFileExtension">
             <bean:define id="fext" name="fileMdl" property="binFileExtension"  type="java.lang.String" />
             <%
              String dext = ((String)pageContext.getAttribute("fext",PageContext.PAGE_SCOPE));
              if (dext != null) {
                  dext = dext.toLowerCase();
                  if (jp.groupsession.v2.cmn.biz.CommonBiz.isViewFile(dext)) {
              %>
             <img src="../circular/cir030.do?CMD=tempview&cir010selectInfSid=<bean:write name="mbhCir040Form" property="cir020selectInfSid" />&cirViewAccount=<bean:write name="mbhCir040Form" property="cirViewAccount"/>&cir020binSid=<bean:write name="fileMdl" property="binSid" />" name="pictImage<bean:write name="fileMdl" property="binSid" />" onload="initImageView('pictImage<bean:write name="fileMdl" property="binSid" />');">
             <br>
              <%
                  }
              }
              %>
          </logic:notEmpty>

          <span class="text_link_min"><bean:write name="fileMdl" property="binFileName" /><bean:write name="fileMdl" property="binFileSizeDsp" /></span></a>
        </li>
      </logic:iterate>

    </ul>
  </logic:notEmpty>

<span class="font_xsmall">■<gsmsg:write key="cir.14" /></span><br>
<div class="font_xsmall" align="center">
  <html:select name="mbhCir040Form" property="cirMemListGroup" styleClass="select01" onchange="changeCombo2('changeGroup');">
    <html:optionsCollection name="mbhCir040Form" property="cirMemListGroupCombo" value="value" label="label" />
  </html:select>
</div>

<logic:notEmpty name="mbhCir040Form" property="cir030memList" scope="request">
  <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
    <logic:iterate id="memMdl" name="mbhCir040Form" property="cir030memList" scope="request">
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

        <br>

        <span class="text_base"><bean:write name="memMdl" property="cvwMemo" filter="false" /></span>
        <logic:equal name="memMdl" property="circularUse" value="false">
          <logic:notEmpty name="memMdl" property="cvwMemo"><br></logic:notEmpty>
          <span class="text_error">※<gsmsg:write key="cir.15" /></span>
        </logic:equal>
      </li>
    </logic:iterate>
  </ul>
</logic:notEmpty>

<div data-role="controlgroup" data-type="horizontal" align="center">
  <span class="font_small">
    <logic:notEqual name="mbhCir040Form" property="cir020cmdMode"  value="<%= gomi %>">
      <input name="copyButton" value="<gsmsg:write key="cmn.register.copy.new" />" class="btn_dell_n1" type="submit">
    </logic:notEqual>

    <!-- 削除 -->
    <input name="deleteButton" value="<gsmsg:write key="cmn.delete2" />" class="btn_dell_n1" type="submit">

    <logic:equal name="cmdMode" value="<%= gomi %>">
      <%-- input type="button" value="<gsmsg:write key="cmn.undo" />" class="btn_modosu_n1" onClick="buttonPush('comeback');" --%>
    </logic:equal>

    <!-- 戻る -->
    <input name="cir040Back" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" type="submit">
  </span>
</div>

<div data-role="controlgroup" data-type="horizontal" align="center">
  <span class="font_xsmall">
    <!-- 前へ -->
    <logic:equal name="mbhCir040Form" property="cir030PrevBound" value="false">
      <input type="button" value="<gsmsg:write key="cmn.previous" />" class="btn_base1_dis" disabled="true" data-icon="arrow-l">
    </logic:equal>
    <logic:equal name="mbhCir040Form" property="cir030PrevBound" value="true">
      <input name="prev040" value="<gsmsg:write key="cmn.previous" />" class="btn_previous_n1" type="submit" data-icon="arrow-l">
    </logic:equal>

    <!-- 次へ -->
    <logic:equal name="mbhCir040Form" property="cir030NextBound" value="false">
      <input type="button" value="<gsmsg:write key="cmn.next" />" class="btn_base1_dis" disabled="true" data-icon="arrow-r" data-iconpos="right">
    </logic:equal>
    <logic:equal name="mbhCir040Form" property="cir030NextBound" value="true">
      <input name="next040" value="<gsmsg:write key="cmn.next" />" class="btn_next_n1" type="submit" data-icon="arrow-r" data-iconpos="right">
    </logic:equal>
  </span>
</div>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>
</div><!-- /page -->

</body>
</html:html>