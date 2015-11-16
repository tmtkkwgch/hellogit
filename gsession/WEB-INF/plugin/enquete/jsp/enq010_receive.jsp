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

      <img src="../common/images/spacer.gif" width="1" height="5" border="0" alt="<gsmsg:write key='cmn.spacer' />">

      <!-- 一覧 -->

      <table class="tl0 table_td_border2" width="100%" cellpadding="5" cellspacing="0">
      <tbody>
        <tr>
          <%
            jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage();
            String[] minWidthPxList = {"1", "1", "75", "90", "1", "1"};
            String[] widthList = {"0", "0", "30", "70", "0", "0"};
            String[] titleList = {gsMsg.getMessage(request, "cmn.status"), gsMsg.getMessage(request, "enq.24"), gsMsg.getMessage(request, "enq.25"), gsMsg.getMessage(request, "cmn.title"), gsMsg.getMessage(request, "enq.19"), gsMsg.getMessage(request, "enq.enq210.11")};
            int[] sortKeyList = {Enq010Const.SORTKEY_STATUS,  Enq010Const.SORTKEY_PRIORITY,  Enq010Const.SORTKEY_SENDER,  Enq010Const.SORTKEY_TITLE,  Enq010Const.SORTKEY_ANSLIMIT,  Enq010Const.SORTKEY_OPEN};
            for (int titleIdx = 0; titleIdx < titleList.length; titleIdx++) {
              if (enqSortKey.intValue() == sortKeyList[titleIdx]) {
                if (enqOrder.intValue() == 1) { sortSign="▼"; nextOrder = "0"; } else { sortSign="▲"; nextOrder = "1"; }
                } else { nextOrder = "0"; sortSign = ""; }
          %>
          <th width="<%= widthList[titleIdx] %>%" class="detail_tbl"><a href="#" onClick="enq010ClickTitle(<%= String.valueOf(sortKeyList[titleIdx]) %>, <%= nextOrder %>);" class="cel_enq_head"><%= titleList[titleIdx] %><%= sortSign %></a>
          <p class="min_width_img"><img src="../common/images/spacer.gif" width="<%=minWidthPxList[titleIdx] %>" height="1px" border="0" alt="" ></p></th>
          <% } %>
        </tr>
        <logic:notEmpty name="enq010Form" property="enq010EnqueteList">
        <logic:iterate id="enqData" name="enq010Form" property="enq010EnqueteList" indexId="idx">
          <tr>
            <td class="td_type1" align="center" valign="middle" nowrap>
              <logic:equal name="enqData" property="status" value="<%= String.valueOf(GSConstEnquete.EAM_STS_KBN_YES) %>">
                <span class="text_base2"><gsmsg:write key="enq.14" /></span>
              </logic:equal>
              <logic:notEqual name="enqData" property="status" value="<%= String.valueOf(GSConstEnquete.EAM_STS_KBN_YES) %>">
                <span class="text_base2">
                  <gsmsg:write key="enq.21" />
                <logic:notEqual name="enqData" property="publicKbn" value="<%= String.valueOf(Enq010Const.PUBLIC_YES) %>">
                  <br><gsmsg:write key="cmn.expiration" />
                </logic:notEqual>
                </span>
              </logic:notEqual>
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
            <td class="td_type1" align="left" valign="middle">
              <bean:define id="sdFlg" name="enqData" property="senderDelFlg" type="java.lang.Boolean" />
              <span class="text_base2<% if (sdFlg) { %> text_deluser_enq<% } %>">
              <bean:write name="enqData" property="sender" />
              </span>
            </td>
            <td class="td_type1" align="left" valign="top">
              <logic:notEmpty name="enqData" property="typeName">
                <span class="label_style"><bean:write name="enqData" property="typeName" /></span>
              </logic:notEmpty>
              <span class="text_base2">
              <logic:equal name="enqData" property="status" value="<%= String.valueOf(GSConstEnquete.EAM_STS_KBN_YES) %>">
                <a href="#" onClick="ansEnquete(<bean:write name="enqData" property="enqSid" />);" class="text_link_enq enq_link_already"><bean:write name="enqData" property="title" /></a>
              </logic:equal>
              <logic:notEqual name="enqData" property="status" value="<%= String.valueOf(GSConstEnquete.EAM_STS_KBN_YES) %>">
                <logic:equal name="enqData" property="canAnsFlg" value="<%= String.valueOf(Enq010Const.PUBLIC_ANSFLG_NG) %>">
                  <bean:write name="enqData" property="title" />
                </logic:equal>
                <logic:notEqual name="enqData" property="canAnsFlg" value="<%= String.valueOf(Enq010Const.PUBLIC_ANSFLG_NG) %>">
                  <a href="#" onClick="ansEnquete(<bean:write name="enqData" property="enqSid" />);" class="text_link_enq"><bean:write name="enqData" property="title" /></a>
                </logic:notEqual>
              </logic:notEqual>
              <logic:equal name="enqData" property="annoy" value="<%= String.valueOf(GSConstEnquete.ANONYMUS_ON) %>">
              <br><gsmsg:write key="cmn.asterisk" /><gsmsg:write key="enq.06" />
              </logic:equal>
              </span>
            </td>
            <td class="td_type1" align="center" valign="top" nowrap>
              <span class="text_base2">
                <bean:write name="enqData" property="ansLimitDate" />
              </span>
            </td>
            <td class="td_type1" align="center" valign="top" nowrap>
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
                <logic:equal name="enqData" property="enqPublic" value="<%= String.valueOf(Enq010Const.PUBLIC_YES) %>">
                  <logic:equal name="enqData" property="ansOpen" value="<%= String.valueOf(GSConstEnquete.KOUKAI_ON) %>">
                   <logic:equal name="enqData" property="disClosureFlg" value="<%= String.valueOf(Enq010Const.RESULT_DISCLOSURE) %>">
                      <logic:equal name="enqData" property="status" value="<%= String.valueOf(GSConstEnquete.EAM_STS_KBN_YES) %>">
                      <logic:equal name="enqData" property="ansPubDateFlg" value="true">
                      <br><a href="#" onclick="enqueteResult(<bean:write name="enqData" property="enqSid" />);" class="text_link_enq enq_link_already"><gsmsg:write key="enq.logmsg.enq310" /></a>
                      </logic:equal>
                      </logic:equal>
                    </logic:equal>
                    <logic:equal name="enqData" property="disClosureFlg" value="<%= String.valueOf(Enq010Const.RESULT_DISCLOSURE) %>">
                    <logic:notEqual name="enqData" property="status" value="<%= String.valueOf(GSConstEnquete.EAM_STS_KBN_YES) %>">
                    <logic:equal name="enqData" property="ansPubDateFlg" value="true">
                    <br><a href="#" onclick="enqueteResult(<bean:write name="enqData" property="enqSid" />);" class="text_link_enq"><gsmsg:write key="enq.logmsg.enq310" /></a>
                    </logic:equal>
                    </logic:notEqual>
                    </logic:equal>
                  </logic:equal>
                </logic:equal>
              </span>
            </td>
          </tr>
        </logic:iterate>
        </logic:notEmpty>
      </tbody>
      </table>

      <img src="../common/images/spacer.gif" width="1px" height="5px" border="0" alt="<gsmsg:write key='cmn.spacer' />">

      <table width="100%">
        <tr>
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