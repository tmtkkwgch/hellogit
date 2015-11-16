<%@page import="jp.groupsession.v2.enq.enq010.Enq010Const"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

      <table width="100%">
      <tr>
        <td align="right">
        <logic:notEmpty name="enq010Form" property="pageList">
          <img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_l.gif" border="0" onclick="buttonPush('prevPage');">
          <html:select name="enq010Form" property="enq010pageTop" styleClass="text_i" onchange="enq010changePage('0');">
            <html:optionsCollection name="enq010Form" property="pageList" value="value" label="label" />
          </html:select>
          <img src="../common/images/arrow2_r.gif" name="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" border="0" onclick="buttonPush('nextPage');">
        </logic:notEmpty>
        </td>
      </tr>
      </table>

      <img src="../common/images/spacer.gif" width="1px" height="5px" border="0" alt="">

      <table class="tl0 table_td_border2" width="100%" cellpadding="5" cellspacing="0">
      <tbody>
        <tr>
        <% if (enqCrtUser) { %>
          <th width="0%" class="detail_tbl" nowrap><input type="checkbox" name="enq010allCheck" value="1" onclick="chgCheckAll('enq010allCheck', 'enq010selectEnqSid');"></th>
        <% } %>
          <th width="4%" class="detail_tbl" nowrap></span><span class="cel_enq_head"><gsmsg:write key="cmn.status" /></span>
          <p class="min_width_img"><img src="../common/images/spacer.gif" width="26px" height="1px" border="0" alt="" ></p>
          </th>
          <%
            jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage();
            String[] minWidthPxList = {"48", "75", "90", "75", "75", "105"};
            String[] widthList = {"0", "13", "30", "13", "13", "17"};
            String[] titleList = {gsMsg.getMessage(request, "enq.24"), "&nbsp;" + gsMsg.getMessage(request, "enq.25"), gsMsg.getMessage(request, "cmn.title"), gsMsg.getMessage(request, "enq.53"), "&nbsp;" + gsMsg.getMessage(request, "enq.19"), gsMsg.getMessage(request, "enq.enq210.11")};
            int[] sortKeyList = {Enq010Const.SORTKEY_PRIORITY,  Enq010Const.SORTKEY_SENDER,  Enq010Const.SORTKEY_TITLE,  Enq010Const.SORTKEY_OPEN,  Enq010Const.SORTKEY_ANSLIMIT,  Enq010Const.SORTKEY_ANS_OPEN};
            for (int titleIdx = 0; titleIdx < titleList.length; titleIdx++) {
              if (enqSortKey.intValue() == sortKeyList[titleIdx]) {
                if (enqOrder.intValue() == 1) { sortSign="▼"; nextOrder = "0"; } else { sortSign="▲"; nextOrder = "1"; }
              } else { nextOrder = "0"; sortSign = ""; }
          %>
          <th width="<%= widthList[titleIdx] %>%" class="detail_tbl">
          <a href="#" onClick="enq010ClickTitle(<%= String.valueOf(sortKeyList[titleIdx]) %>, <%= nextOrder %>);" class="cel_enq_head"><%= titleList[titleIdx] %><%= sortSign %></a>
          <% if (titleIdx == 1 && enqSortKey.intValue() == Enq010Const.SORTKEY_MAKEDATE) { if (enqOrder.intValue() == 1) { sortSign="▼"; nextOrder = "0"; } else { sortSign="▲"; nextOrder = "1"; }%><br><a href="#" onClick="enq010ClickTitle(<%= String.valueOf(Enq010Const.SORTKEY_MAKEDATE) %>, <%= nextOrder %>);" class="cel_enq_head"><gsmsg:write key="man.creation.date" /><%= sortSign %></a>
          <% } else if (titleIdx == 1) {  { nextOrder = "0"; sortSign = ""; } %><br><a href="#" onClick="enq010ClickTitle(<%= String.valueOf(Enq010Const.SORTKEY_MAKEDATE) %>, <%= nextOrder %>);" class="cel_enq_head"><gsmsg:write key="man.creation.date" /><%= sortSign %></a>
          <% } else if (titleIdx == 4) { %><br><span class="cel_enq_head"><gsmsg:write key="cmn.number.of.candidates" /></span><% } %>
          <p class="min_width_img"><img src="../common/images/spacer.gif" width="<%=minWidthPxList[titleIdx] %>" height="1px" border="0" alt="" ></p>
          </th>
          <% } %>

        <% if (enqCrtUser) { %>
          <th width="0%" class="detail_tbl" nowrap><span class="cel_enq_head">&nbsp;</span></th>
        <% } %>
        </tr>
        <logic:notEmpty name="enq010Form" property="enq010EnqueteList">
        <logic:iterate id="enqData" name="enq010Form" property="enq010EnqueteList" indexId="idx">
        <tr>
        <% if (enqCrtUser) { %>
          <td class="td_type1" align="center" valign="middle" nowrap>
            <html:multibox name="enq010Form" property="enq010selectEnqSid">
              <bean:write name="enqData"  property="enqSid" />
            </html:multibox>
          </td>
        <% } %>
          <td class="td_type1" align="center" valign="middle" >
            <logic:equal name="enqData" property="publicKbn" value="<%= String.valueOf(Enq010Const.PUBLIC_YES) %>">
              <span class="text_base2"><gsmsg:write key="enq.77" /></span>
            </logic:equal>
            <logic:equal name="enqData" property="publicKbn" value="<%= String.valueOf(Enq010Const.PUBLIC_END) %>">
              <span class="text_base2"><gsmsg:write key="enq.17" /></span>
            </logic:equal>
            <logic:equal name="enqData" property="publicKbn" value="<%= String.valueOf(Enq010Const.PUBLIC_NO) %>">
              <span class="text_base2"><gsmsg:write key="enq.15" /></span>
            </logic:equal>
            <logic:equal name="enqData" property="publicKbn" value="<%= String.valueOf(Enq010Const.PUBLIC_ANSED) %>">
              <span class="text_base2"><gsmsg:write key="enq.16" /></span>
            </logic:equal>
          </td>
          <td class="td_type1" align="center" valign="middle" nowrap>
           <bean:define id="enqPriority" name="enqData" property="priority" type="java.lang.Integer" />
           <% if (enqPriority.intValue() == GSConstEnquete.JUUYOU_0) { %>
             <img src="../enquete/images/star_blue_16.png" class="star4" border="0" alt="<gsmsg:write key="project.58" />">
             <img src="../enquete/images/star_white_16.png" class="star4" border="0" alt="<gsmsg:write key="project.58" />">
             <img src="../enquete/images/star_white_16.png" class="star4" border="0" alt="<gsmsg:write key="project.58" />">
           <% } else if (enqPriority.intValue() == GSConstEnquete.JUUYOU_1) { %>
            <img src="../enquete/images/star_gold_16.png" class="star4" border="0" alt="<gsmsg:write key="project.59" />">
            <img src="../enquete/images/star_gold_16.png" class="star4" border="0" alt="<gsmsg:write key="project.59" />">
            <img src="../enquete/images/star_white_16.png" class="star4" border="0" alt="<gsmsg:write key="project.59" />">
           <% } else if (enqPriority.intValue() == GSConstEnquete.JUUYOU_2) { %>
            <img src="../enquete/images/star_red_16.png" class="star4" border="0" alt="<gsmsg:write key="project.60" />">
            <img src="../enquete/images/star_red_16.png" class="star4" border="0" alt="<gsmsg:write key="project.60" />">
            <img src="../enquete/images/star_red_16.png" class="star4" border="0" alt="<gsmsg:write key="project.60" />">
           <% } %>
          </td>
          <td class="td_type1" align="left" valign="top">
            <bean:define id="sdFlg" name="enqData" property="senderDelFlg" type="java.lang.Boolean" />
            <span class="text_base2<% if (sdFlg) { %> text_deluser_enq<% } %>">
              <bean:write name="enqData" property="sender" />
            </span>
            <br>
            <span class="text_base2">
              <bean:write name="enqData" property="makeDate" />
            </span>
          </td>
          <td class="td_type1" align="left" valign="top">
            <logic:notEmpty name="enqData" property="typeName">
              <span class="label_style"><bean:write name="enqData" property="typeName" /></span>
            </logic:notEmpty>
            <span class="text_base2"><bean:write name="enqData" property="title" /></span>
          </td>
          <td class="td_type1" align="center" valign="middle">
          <span class="text_base2">
          <bean:write name="enqData" property="pubStartDateStr" filter='false'/>
          </span>
          </td>
          <td class="td_type1" align="center" valign="top">
            <span class="text_base2">
            <bean:write name="enqData" property="ansLimitDate" />
            <logic:notEqual name="enqData" property="publicKbn" value="<%= String.valueOf(Enq010Const.PUBLIC_NO) %>">
              <bean:define id="publicKbn" name="enqData" property="publicKbn" type="java.lang.Integer" />
              <% if (publicKbn == Enq010Const.PUBLIC_YES || publicKbn == Enq010Const.PUBLIC_NO) { %>
              <br><a href="#" onclick="enqueteResult(<bean:write name="enqData" property="enqSid" />);" class="text_link_enq" id="<bean:write name="enqData" property="enqSid" />"><bean:write name="enqData" property="subjects" /><gsmsg:write key="cmn.persons" /></a>
              <% } else { %>
              <br><a href="#" onclick="enqueteResult(<bean:write name="enqData" property="enqSid" />);" class="text_link_enq enq_link_already" id="<bean:write name="enqData" property="enqSid" />"><bean:write name="enqData" property="subjects" /><gsmsg:write key="cmn.persons" /></a>
              <% } %>
            </logic:notEqual>
            </span>
          </td>
            <td class="td_type1" align="center" valign="top" >
              <span class="text_base2">
              <logic:equal name="enqData" property="ansOpen" value="<%= String.valueOf(GSConstEnquete.KOUKAI_ON) %>">
              <bean:write name="enqData" property="ansPubStartDate" />
              <logic:empty name="enqData" property="pubEndDateStr">
              &nbsp;～<br>
              <gsmsg:write key="main.man200.9" />
              </logic:empty>
              <logic:notEmpty name="enqData" property="pubEndDateStr">
              &nbsp;～<br>
              <bean:write name="enqData" property="pubEndDateStr" />
              </logic:notEmpty>
              </logic:equal>
              <logic:notEqual name="enqData" property="ansOpen" value="<%= String.valueOf(GSConstEnquete.KOUKAI_ON) %>">
              <gsmsg:write key="cmn.private" />
              </logic:notEqual>
              </span>
            </td>
        <% if (enqCrtUser) { %>
          <td class="td_type1" align="center" valign="middle">
            <input type="button" onclick="editEnquete(<bean:write name="enqData" property="enqSid" />);" class="btn_edit_sub" value="<gsmsg:write key="cmn.edit" />">
            <logic:equal name="enqData" property="publicKbn" value="<%= String.valueOf(Enq010Const.PUBLIC_YES) %>">
            <logic:equal name="enq010Form" property="enq010pluginSmailUse" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.PLUGIN_USE) %>">
              <div style="margin-top: 5px;">
                <input type="button" class="btn_smail_sub" value="<gsmsg:write key="main.man002.63" />" name="smlNotice" id="<bean:write name="enqData" property="enqSid" />">
              </div>
            </logic:equal>
            </logic:equal>
          </td>
        <% } %>
        </tr>

        </logic:iterate>
        </logic:notEmpty>
        </tbody>
        </table>

      <img src="../common/images/spacer.gif" width="1px" height="5px" border="0" alt="">
      <table width="100%">
      <tr>
        <td align="left">&nbsp;</td>
        <td align="right">
        <logic:notEmpty name="enq010Form" property="pageList">
          <img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_l.gif" border="0" onclick="buttonPush('prevPage');">
          <html:select name="enq010Form" property="enq010pageBottom" styleClass="text_i" onchange="enq010changePage('1');">
            <html:optionsCollection name="enq010Form" property="pageList" value="value" label="label" />
          </html:select>
          <img src="../common/images/arrow2_r.gif" name="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" border="0" onclick="buttonPush('nextPage');">
        </logic:notEmpty>
        </td>
      </tr>
      </table>