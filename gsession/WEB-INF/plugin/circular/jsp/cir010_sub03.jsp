<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<gsmsg:define id="title" msgkey="cmn.title" />
<gsmsg:define id="nitiji" msgkey="cmn.date" />
<gsmsg:define id="hassinsya" msgkey="cir.2" />

<%
  int[] sortKeyList03 = new int[] {
                       jp.groupsession.v2.cir.GSConstCircular.SORT_TITLE,
                       jp.groupsession.v2.cir.GSConstCircular.SORT_DATE,
                       jp.groupsession.v2.cir.GSConstCircular.SORT_USER
                       };
  String[] title_width03 = new String[] { "61", "18", "18"};
  String[] titleList03 = new String[] {
          title,
          nitiji,
          hassinsya
                       };
%>

    <tr>

    <!-- 表タイトル -->
    <!-- 全選択・全解除チェックボックス -->
    <td width="3%" class="td_cir_header2"><input type="checkbox" name="allChk" onClick="changeChk();"></td>

<%
    for (int i = 0; i < sortKeyList03.length; i++) {
      if (iSortKbn == sortKeyList03[i]) {
        if (iOrderKey == order_desc) {
%>
        <th width="<%= title_width03[i] %>%" class="td_cir_header"><a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= sortKeyList03[i] %>, <%= order_asc %>);"><span class="text_base3"><%= titleList03[i] %>▼</span></a></th>
<%
        } else {
%>
        <th width="<%= title_width03[i] %>%" class="td_cir_header"><a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= sortKeyList03[i] %>, <%= order_desc %>);"><span class="text_base3"><%= titleList03[i] %>▲</span></a></th>
<%
        }
      } else {
%>
        <th width="<%= title_width03[i] %>%" class="td_cir_header"><a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= sortKeyList03[i] %>, <%= order_asc %>);"><span class="text_base3"><%= titleList03[i] %></span></a></th>
<%
      }
    }
%>
    </tr>


  <!-- 表BODY -->
  <logic:notEmpty name="cir010Form" property="cir010CircularList" scope="request">
  <logic:iterate id="cirMdl" name="cir010Form" property="cir010CircularList" scope="request" indexId="idx">
  <bean:define id="backclass" value="smail_td" />
  <bean:define id="backpat" value="<%= String.valueOf((idx.intValue() % 2) + 1) %>" />
  <bean:define id="back" value="<%= String.valueOf(backclass) + String.valueOf(backpat) %>" />

<%
  String font = "";
  String titleFont = "";
  String sojuStr = "";
%>

  <logic:equal name="cirMdl" property="cvwConf" value="<%= unopen %>">
<%
  font = "sc_ttl_sat";
  titleFont = "text_link";
%>
  </logic:equal>

  <logic:notEqual name="cirMdl" property="cvwConf" value="<%= unopen %>">
<%
  font = "text_p";
  titleFont = "text_p";
%>
  </logic:notEqual>

  <logic:equal name="cirMdl" property="jsFlg" value="<%= jusin %>">
    <gsmsg:define id="jusinStr" msgkey="cmn.receive2" />
<%
  sojuStr = "[ " + jusinStr + " ]";
%>
  </logic:equal>
  <logic:equal name="cirMdl" property="jsFlg" value="<%= sosin %>">
    <gsmsg:define id="sosinStr" msgkey="cmn.sent2" />
<%
sojuStr = "[ " + sosinStr + " ]";
%>
  </logic:equal>

  <tr>

  <!-- チェックボックス -->
  <td class="<%= String.valueOf(back) %>" align="center">
    <html:multibox name="cir010Form" property="cir010delInfSid">
       <bean:write name="cirMdl" property="cifSid" />-<bean:write name="cirMdl" property="jsFlg" />
    </html:multibox>
  </td>

  <!-- タイトル -->
  <td class="<%= String.valueOf(back) %>">
    <a href="javascript:void(0)" onClick="return gomiView('view', '<bean:write name="cirMdl" property="cifSid" />', '<bean:write name="cirMdl" property="jsFlg" />');"><span class="<%= String.valueOf(titleFont) %>"><%= String.valueOf(sojuStr) %>&nbsp;<bean:write name="cirMdl" property="cifTitle" /></span></a>
  </td>

  <!-- 日付 -->
  <td class="<%= String.valueOf(back) %>" align="center"><span class="<%= String.valueOf(font) %>"><bean:write name="cirMdl" property="dspCifAdate" /></span></td>

  <!-- 発信者 -->
  <td class="<%= String.valueOf(back) %>">
    <logic:equal name="cirMdl" property="cacJkbn" value="<%= String.valueOf(jp.groupsession.v2.cir.GSConstCircular.CAC_JKBN_NORMAL) %>">
    <span class="<%= String.valueOf(font) %>"><bean:write name="cirMdl" property="cacName" /></span>
    </logic:equal>
    <logic:notEqual name="cirMdl" property="cacJkbn" value="<%= String.valueOf(jp.groupsession.v2.cir.GSConstCircular.CAC_JKBN_NORMAL) %>">
    <del><span class="<%= String.valueOf(font) %>"><bean:write name="cirMdl" property="cacName" /></span></del>
    </logic:notEqual>
  </td>
  </tr>

  </logic:iterate>
  </logic:notEmpty>
