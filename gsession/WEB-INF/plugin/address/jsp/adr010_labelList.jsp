<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

    <table width="100%" class="tl0 table_td_border2" style="margin-top:20px;margin-right:0px;margin-bottom:3px;margin-left:0px;">
    <tr>
    <td colspan="2" class="detail_tbl" nowrap>
    <!-- ラベル -->
      <span class="sel_label_header"><gsmsg:write key="cmn.select.label" /></span>
      <!--     ラベル編集ボタン -->
      <logic:notEqual name="adr010Form" property="adr010webmail" value="1">
      <logic:equal name="adr010Form" property="adr010viewLabelBtn" value="1">
         <div align="right" style="float:right;">
             <input type="button" onClick="buttonPush('setupLabel');" style="border:0px;color:#40a06b;font-size:10px;font-weight:bold;width:60px;height:17px;" class="btn_small_setting" value="<gsmsg:write key="cmn.label"/>">
         </div>
      </logic:equal>
      </logic:notEqual>
    </td>
    </tr>

    <tr>
    <td style="padding:0!important">
    <logic:empty name="adr010Form" property="adr010CaegoryLabelList">
      <div style="text-align:center;font-size:90%;"><gsmsg:write key="cmn.label.no" /></div>
    </logic:empty>

    <logic:notEmpty name="adr010Form" property="adr010CaegoryLabelList">
    <logic:iterate id="categoryLabelData" name="adr010Form" property="adr010CaegoryLabelList" indexId="idx">

       <%
           String category_head_id = "category_head_" + idx.toString();
           String category_data_id = "category_data_" + idx.toString();
       %>

       <div class="menu_head_sel" id="<%= category_head_id.toString() %>">
         &nbsp;<a href="javascript:changeDspCategory('<%= idx.toString() %>');" class="sel_category_header" >&nbsp;&nbsp;&nbsp;<bean:write name="categoryLabelData" property="categoryName" /></a>
       </div>

       <div class="label_name" id = "<%= category_data_id.toString() %>">

       <logic:notEmpty name="categoryLabelData" property="labelList">
       <logic:iterate id="labelData" name="categoryLabelData" property="labelList" indexId="idx2">

          <%
              String label_id = "label_" + idx.toString() + "_" + idx2.toString();
          %>

          <div style="margin-left:15px;">
          <!-- チェックボックス -->
          <html:multibox name="adr010Form" property="adr010searchLabel" styleId="<%= label_id.toString() %>">
            <bean:write name="labelData" property="albSid" />
          </html:multibox>

          <label for="<%= label_id.toString() %>"><a href="#" onClick='labelCheck(<bean:write name="labelData" property="albSid" />)'><bean:write name="labelData" property="albName" /></a></label>
          </div>
       </logic:iterate>
       </logic:notEmpty>

       </div>

    </logic:iterate>
    </logic:notEmpty>

    </td>
    </tr>
    </table>