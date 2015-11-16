<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.affiliation.group" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt" colspan="2">
      <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td align="right">
        <input type="button" name="all_off" class="btn_base1w" onclick="javascript:onAllUnCheck();defaultGroup();" value="<gsmsg:write key="user.31" />">
      </td>
      </tr>
      <tr>
      <td width="100%"><img alt="<gsmsg:write key="user.116" />" src="../common/images/damy.gif" height="4" width="1"></td>
      </tr>
      </table>

      <iframe name="grpFrame" src="../user/usr021.do?parentName=usr032" class="iframe_01" height="300" width="100%">
      <gsmsg:write key="user.32" />
      </iframe>

    </td>
    </tr>
    
    <!-- デフォルトグループ -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
      <span class="text_bb1"><gsmsg:write key="user.35" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt" colspan="2">
      <logic:empty name="usr032Form" property="groupList">
        <html:select property="usr031defgroup" style="width:250px;">
          <option value="-1"><gsmsg:write key="cmn.select.plz" /></option>
        </html:select>
      </logic:empty>
      <logic:notEmpty name="usr032Form" property="groupList">
        <html:select property="usr031defgroup" style="width:250px;">
          <option value="-1"><gsmsg:write key="cmn.select.plz" /></option>
          <html:optionsCollection name="usr032Form" property="groupList" value="groupSid" label="groupName" />
        </html:select>
      </logic:notEmpty>
    </td>
    </tr>
