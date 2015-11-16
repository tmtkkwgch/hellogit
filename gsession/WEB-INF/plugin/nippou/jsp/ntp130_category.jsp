<%@ page pageEncoding="Windows-31J" contentType="text/html; charset=UTF-8"%>
<input type="hidden" name="helpPrm" value="category">

<html:hidden property="ntp130ShohinPageNum" />
<html:hidden property="ntp130SelCategorySid" />

<logic:notEmpty name="ntp130Form" property="ntp130CatList" scope="request">
  <logic:iterate id="sort" name="ntp130Form" property="ntp130CatList" scope="request">
    <input type="hidden" name="ntp130KeyList" value="<bean:write name="sort" property="nscValue" />">
  </logic:iterate>
</logic:notEmpty>

<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">


  <%-- ƒ^ƒu --%>
  <table class="" width="70%" border="0" cellpadding="0" cellspacing="0" height="15px">
  <tbody>

    <tr>
    <td align="left" colspan="4">

      <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td width="0%"><img src="../nippou/images/header_nippou_02.gif" border="0" alt="<gsmsg:write key="ntp.1" />"></td>
      <td width="0%" class="header_white_bg_text" nowrap>[ <gsmsg:write key="ntp.59" /> ]</td>
      <td width="100%" class="header_white_bg">
      <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="ntp.1" />"></td>
      </tr>
      </table>

    </td>
    </tr>

    <tr>
    <td align="left" colspan="4">
      <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    </td>
    </tr>

    <tr>
      <td style="border-bottom:1px solid #939393;" width="2%" nowrap="nowrap">&nbsp;</td>
      <td class="bunseki_tab_not_sel" width="100px" align="left" nowrap="nowrap">
        <div style="text-align:center;">
          <a style="" href="javascript:changeMode(0);"><gsmsg:write key="ntp.58" /><gsmsg:write key="cmn.list" /></a>
        </div>
      </td>
      <td class="bunseki_tab_sel" width="107px" align="left" nowrap="nowrap">
        <div style="text-align:center;">
          <a href="javascript:changeMode(1);"><gsmsg:write key="ntp.59" /></a>
        </div>
      </td>
      <td style="border-bottom:1px solid #939393;" width="99%" nowrap="nowrap">&nbsp;</td>
    </tr>
  </tbody>
  </table>


  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="user.59" />" class="btn_add_n1" onClick="buttonSubmitCatagory('addCategory', 'add' , '-1');">&nbsp;
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush2('backNtp130');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td>
    <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.up" />" name="btn_upper" onClick="return buttonPush('ntp130up');">
    <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.down" />" name="btn_downer" onClick="return buttonPush('ntp130down');">&nbsp;&nbsp;
    </td>
    </tr>
    </table>

    <table class="tl0 table_td_border2" cellpadding="5" cellspacing="0" border="0" width="100%">

    <tr>
      <th align="center" class="table_bg_7D91BD" width="5%"><span class="text_tlw">&nbsp;</span></th>
      <th align="center" class="table_bg_7D91BD" width="28%"><span class="text_tlw"><gsmsg:write key="cmn.category.name" /></span></th>
      <th align="center" class="table_bg_7D91BD" width="51%"><span class="text_tlw"><gsmsg:write key="cmn.memo" /></span></th>
      <th align="center" class="table_bg_7D91BD" width="16%" colspan="2"><span class="text_tlw"><gsmsg:write key="ntp.58" /></span></th>
    </tr>

    <logic:notEmpty name="ntp130Form" property="ntp130CatList">
    <bean:define id="tdColor" value=""/>
    <% String[] tdColors = new String[] {"td_type1", "td_type_usr"}; %>
    <logic:iterate id="catMdl" name="ntp130Form" property="ntp130CatList" indexId="idx">
    <% tdColor = tdColors[(idx.intValue() % 2)]; %>

    <bean:define id="nscValue" name="catMdl" property="nscValue" />

    <tr align="center" class="<%= tdColor %>">
    <!-- <gsmsg:write key="adr.radio.button" /> -->
    <td align="center" nowrap>
    <html:radio property="ntp130SortRadio" value="<%= String.valueOf(nscValue) %>" />
    </td>
    <td align="left" nowrap>

    <logic:equal name="catMdl" property="nscSid" value="1">
    <bean:write name="catMdl" property="nscName" />
    </logic:equal>

    <logic:notEqual name="catMdl" property="nscSid" value="1">
    <a href="#" onclick="return buttonSubmitCatagory('categoryEdit', 'edit', '<bean:write name="catMdl" property="nscSid" />')"><bean:write name="catMdl" property="nscName" /></a>
    </logic:notEqual>

    </td>
    <!-- <gsmsg:write key="cmn.memo" /> -->
    <td align="left">
    <bean:write name="catMdl" property="nscBiko" filter="false" />
    </td>
    <td align="center">
    <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.add" />" onClick="return buttonCatagoryAdd('add','<bean:write name="catMdl" property="nscSid" />');">
    <input type="button" value="<gsmsg:write key="cmn.list" />" id="<bean:write name="catMdl" property="nscSid" />" class="shohin_list_btn btn_base0">
    </td>
    </tr>

    </logic:iterate>
    </logic:notEmpty>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellpadding="0" border="0" width="100%">
    <tr>
    <td align="right" valign="middle">
      <input type="button" value="<gsmsg:write key="user.59" />" class="btn_add_n1" onClick="buttonSubmitCatagory('addCategory', 'add' , '-1');">&nbsp;
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush2('backNtp130');">
    </td>
    </tr>
    </table>

  </td>
  </tr>
  </table>

</td>
</tr>
</table>

<div id="shohinPop" title="<gsmsg:write key="ntp.58" />" style="display:none;">
  <p>
    <div id="tmpShohinArea">
    </div>
  </p>
</div>