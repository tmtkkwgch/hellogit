<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>

    <table width="100%" cellpadding="0" cellspacing="0">
    <tr>
    <td width="100%" class="tbl_gray2">
      <table width="100%" cellpadding="0" cellspacing="0">
      <tr>
      <td width="50%" valign="top">

        <table cellpadding="3" cellspacing="0" width="99%" class="user_search2_syosai" border="1">
        <tr>
        <td class="detail_tbl" align="left" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.capture.file" /></span><span class="text_r2">※</span></td>
        <td class="user_search2_syosai_td_1" align="left" width="80%">

          <table cellpadding="0" cellspacing="0" width="100%">
          <tr>
          <td width="70%">
          <input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTemp('man330SelectFiles', '<%= jp.groupsession.v2.usr.GSConstUser.PLUGIN_ID_USER %>', '1');">
          &nbsp;<input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="buttonPush('delete');"><br>

          <html:select property="man330SelectFiles" styleClass="select01" multiple="false" size="1">
            <html:optionsCollection name="man330Form" property="man330FileLabelList" value="value" label="label" />
          </html:select>
          <br>
          <span class="text_base">
          <% jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage(); %>
          <% String csvFileMsg = "<a href=\"../main/man330.do?CMD=man330_sample\">" + gsMsg.getMessage(request, "cmn.capture.csvfile") + "</a>"; %>
              *<gsmsg:write key="cmn.plz.specify2" arg0="<%= csvFileMsg %>" />
          </span>
          <td width="30%" align="left">
            <input type="button" value="<gsmsg:write key="cmn.import" />" class="btn_csv_n1" onclick="buttonPush('import_exe');">
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

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%" class="tab_bottom_left"><img src="../common/images/spacer.gif" width="10" height="10" border="0" alt=""></td>
      <td class="tab_bottom_mid" width="100%" align="right" valign="bottom"></td>
      <td width="0%" class="tab_bottom_right"><img src="../common/images/spacer.gif" width="10" height="10" border="0" alt=""></td>
    </tr>
    </table>