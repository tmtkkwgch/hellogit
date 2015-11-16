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

        <table cellpadding="3" cellspacing="0" width="99%" class="user_search2_syosai">
        <tr>
        <td width="100%" colspan="2">
           <span class="text_r1"><gsmsg:write key="main.sel.items.export.output" /></span>
        </td>
        </tr>
        <tr>
        <td class="detail_tbl" align="left" width="15%" nowrap><span class="text_bb1"><gsmsg:write key="reserve.output.item" /></span><span class="text_r2">※</span></td>
        <td class="user_search2_syosai_td_1" align="left" width="85%">
          <table cellpadding="0" cellspacing="0" width="100%">
          <tr>
          <td width="85%">
            <span style="white-space: nowrap"><html:multibox styleId="userId" name="man330Form" property="man330CsvOutField" value="1" /><label for="userId" class="text_base"><gsmsg:write key="cmn.user.id" /></label></span><wbr>
            <span style="white-space: nowrap"><html:multibox styleId="userName" name="man330Form" property="man330CsvOutField" value="2" /><label for="userName" class="text_base"><gsmsg:write key="cmn.name" /></label></span><wbr>
            <span style="white-space: nowrap"><html:multibox styleId="userNameKn" name="man330Form" property="man330CsvOutField" value="3" /><label for="userNameKn" class="text_base"><gsmsg:write key="cmn.name.kana" /></label></span><wbr>
            <span style="white-space: nowrap"><html:multibox styleId="groupId" name="man330Form" property="man330CsvOutField" value="4" /><label for="groupId" class="text_base"><gsmsg:write key="cmn.group.id" /></label></span><wbr>
            <span style="white-space: nowrap"><html:multibox styleId="groupName" name="man330Form" property="man330CsvOutField" value="5" /><label for="groupName" class="text_base"><gsmsg:write key="cmn.group.name" /></label></span><wbr>
            <span style="white-space: nowrap"><html:multibox styleId="groupNameKn" name="man330Form" property="man330CsvOutField" value="6" /><label for="groupNameKn" class="text_base"><gsmsg:write key="user.14" /></label></span><wbr>
          </td>
          <td width="15%" align="left">
            <input type="button" value="<gsmsg:write key="cmn.export" />" class="btn_csv_n2" onclick="buttonPush('export_exe');">
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
