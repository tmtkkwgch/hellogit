<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<gsmsg:define id="title" msgkey="cmn.title" />
<gsmsg:define id="kakunin" msgkey="cmn.check" />
<gsmsg:define id="nitiji" msgkey="cmn.date" />
<gsmsg:define id="hassinsya" msgkey="cir.2" />

<%
  int[] sortKeyList02 = new int[] {
                       jp.groupsession.v2.cir.GSConstCircular.SORT_TITLE,
                       0,
                       jp.groupsession.v2.cir.GSConstCircular.SORT_DATE,
                       jp.groupsession.v2.cir.GSConstCircular.SORT_USER
                       };
  String[] title_width02 = new String[] { "56", "5", "18", "18"};
  String[] titleList02 = new String[] {
          title,
          kakunin,
          nitiji,
          hassinsya
                       };
%>

    <tr>

    <!-- 表タイトル -->
    <!-- 全選択・全解除チェックボックス -->
    <td width="3%" class="td_cir_header2"><input type="checkbox" name="allChk" onClick="changeChk();"></td>

<%
    for (int i = 0; i < sortKeyList02.length; i++) {

      if (i == 1) {
%>
        <th width="<%= title_width02[i] %>%" class="td_cir_header" nowrap><span class="text_base3"><gsmsg:write key="cmn.check" /></span></th>
<%
        continue;
      }

      if (iSortKbn == sortKeyList02[i]) {
        if (iOrderKey == order_desc) {
%>
        <th width="<%= title_width02[i] %>%" class="td_cir_header"><a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= sortKeyList02[i] %>, <%= order_asc %>);"><span class="text_base3"><%= titleList02[i] %>▼</span></a></th>
<%
        } else {
%>
        <th width="<%= title_width02[i] %>%" class="td_cir_header"><a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= sortKeyList02[i] %>, <%= order_desc %>);"><span class="text_base3"><%= titleList02[i] %>▲</span></a></th>
<%
        }
      } else {
%>
        <th width="<%= title_width02[i] %>%" class="td_cir_header"><a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= sortKeyList02[i] %>, <%= order_asc %>);"><span class="text_base3"><%= titleList02[i] %></span></a></th>
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
  <tr>

  <!-- チェックボックス -->
  <td class="<%= String.valueOf(back) %>" align="center">
    <html:multibox name="cir010Form" property="cir010delInfSid">
       <bean:write name="cirMdl" property="cifSid" />-<bean:write name="cirMdl" property="jsFlg" />
    </html:multibox>
  </td>

  <!-- タイトル -->
  <td class="<%= String.valueOf(back) %>"><a href="javascript:void(0)" onClick="return buttonPush('view', '<bean:write name="cirMdl" property="cifSid" />');"><span class="text_p"><bean:write name="cirMdl" property="cifTitle" /></span></a></td>

  <td class="<%= String.valueOf(back) %>" align="center"><span class="text_p"><bean:write name="cirMdl" property="openCount" />/<bean:write name="cirMdl" property="allCount" /></span></td>

  <!-- 日付 -->
  <td class="<%= String.valueOf(back) %>" align="center"><span class="text_p"><bean:write name="cirMdl" property="dspCifAdate" /></span></td>

  <!-- 発信者 -->
  <td class="<%= String.valueOf(back) %>">
    <logic:equal name="cirMdl" property="cacJkbn" value="<%= String.valueOf(jp.groupsession.v2.cir.GSConstCircular.CAC_JKBN_NORMAL) %>">
    <span class="text_p"><bean:write name="cirMdl" property="cacName" /></span>
    </logic:equal>
    <logic:notEqual name="cirMdl" property="cacJkbn" value="<%= String.valueOf(jp.groupsession.v2.cir.GSConstCircular.CAC_JKBN_NORMAL) %>">
    <del><span class="text_p"><bean:write name="cirMdl" property="cacName" /></span></del>
    </logic:notEqual>
  </td>

  </tr>

  </logic:iterate>
  </logic:notEmpty>
