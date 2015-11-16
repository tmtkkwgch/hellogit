<%@ page pageEncoding="Windows-31J" contentType="text/html; charset=UTF-8"%>
<input type="hidden" name="helpPrm" value="shohin">

<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">


  <%-- タブ --%>
  <table class="" width="70%" border="0" cellpadding="0" cellspacing="0" height="15px">
  <tbody>

    <tr>
    <td align="left" colspan="4">

      <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td width="0%"><img src="../nippou/images/header_nippou_02.gif" border="0" alt="<gsmsg:write key="ntp.1" />"></td>
      <td width="0%" class="header_white_bg_text" nowrap>[ <gsmsg:write key="ntp.58" /><gsmsg:write key="cmn.list" /> ]</td>
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
      <td class="bunseki_tab_sel" width="100px" align="left" nowrap="nowrap">
        <div style="text-align:center;">
          <a style="" href="javascript:changeMode(0);"><gsmsg:write key="ntp.58" /><gsmsg:write key="cmn.list" /></a>
        </div>
      </td>
      <td class="bunseki_tab_not_sel" width="107px" align="left" nowrap="nowrap">
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

    <input type="button" class="btn_csv_n1" value="<gsmsg:write key="cmn.import" />" onClick="buttonPush2('import');">
    <input type="button" class="btn_csv_n2" value="<gsmsg:write key="cmn.export" />" onClick="buttonPush2('export');">
    <input type="button" class="btn_add_n1" value="<gsmsg:write key="cmn.add" />" onClick="return buttonSubmit('add','-1');">


    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush2('backNtp130');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>
  </td>
  </tr>

  <tr>
  <td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td>
  </tr>

  <logic:messagesPresent message="false">
  <tr><td align="left"><span class="TXT02"><html:errors/></span></td></tr>
  <tr><td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td></tr>
  </logic:messagesPresent>

  <tr>
  <td align="left">

    <table width="100%" class="tl0" border="0" cellpadding="5">

    <tr>
    <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="cmn.category" /></span></td>
    <td align="left" class="td_type20" width="85%" colspan="3">
    <html:select name="ntp130Form" property="ntp130CatSid" styleClass="select01" style="width:200px;">
    <logic:notEmpty name="ntp130Form" property="ntp130CategoryList">
    <html:optionsCollection name="ntp130Form" property="ntp130CategoryList" value="value" label="label" />
    </logic:notEmpty>
    </html:select>
    </td>
    </tr>

    <tr>
    <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="ntp.122" /></span></td>
    <td align="left" class="td_type20" width="85%" colspan="3"><html:text name="ntp130Form" property="ntp130NhnCode" maxlength="13" style="width:131px;" /></td>
    </tr>

    <tr>
    <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="ntp.154" /></span></td>
    <td align="left" class="td_type20" width="85%" colspan="3"><html:text name="ntp130Form" property="ntp130NhnName" maxlength="50" style="width:515px;" /></td>
    </tr>

    <tr>
    <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="ntp.76" /></span></td>
    <td align="left" class="td_type20" width="85%" nowrap><html:text name="ntp130Form" property="ntp130NhnPriceSale" maxlength="12" style="text-align:right; width:113px;" />&nbsp;<span class="text_base8"><gsmsg:write key="project.103" /></span>
    <html:radio name="ntp130Form" property="ntp130NhnPriceSaleKbn" styleId="ntp130NhnPriceSaleKbn1" value="<%= String.valueOf(jp.groupsession.v2.ntp.ntp130.Ntp130Biz.PRICE_MORE) %>" /><span class="text_base8"><label for="ntp130NhnPriceSaleKbn1"><gsmsg:write key="ntp.66" /></label></span>&nbsp;
    <html:radio name="ntp130Form" property="ntp130NhnPriceSaleKbn" styleId="ntp130NhnPriceSaleKbn2" value="<%= String.valueOf(jp.groupsession.v2.ntp.ntp130.Ntp130Biz.PRICE_LESS) %>" /><span class="text_base8"><label for="ntp130NhnPriceSaleKbn2"><gsmsg:write key="ntp.67" /></label></span>&nbsp;
    </td>

    <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="ntp.77" /></span></td>
    <td align="left" class="td_type20" width="85%" nowrap><html:text name="ntp130Form" property="ntp130NhnPriceCost" maxlength="12" style="text-align:right; width:113px;" />&nbsp;<span class="text_base8"><gsmsg:write key="project.103" /></span>
    <html:radio name="ntp130Form" property="ntp130NhnPriceCostKbn" styleId="ntp130NhnPriceCostKbn1" value="<%= String.valueOf(jp.groupsession.v2.ntp.ntp130.Ntp130Biz.PRICE_MORE) %>" /><span class="text_base8"><label for="ntp130NhnPriceCostKbn1"><gsmsg:write key="ntp.66" /></label></span>&nbsp;
    <html:radio name="ntp130Form" property="ntp130NhnPriceCostKbn" styleId="ntp130NhnPriceCostKbn2" value="<%= String.valueOf(jp.groupsession.v2.ntp.ntp130.Ntp130Biz.PRICE_LESS) %>" /><span class="text_base8"><label for="ntp130NhnPriceCostKbn2"><gsmsg:write key="ntp.67" /></label></span>&nbsp;
    </td>
    </tr>

    <tr>
    <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="cmn.sortkey" />１</span></td>
    <td align="left" class="td_type20" width="35%" nowrap>
    <html:select name="ntp130Form" property="ntp130SortKey1" styleClass="select01" style="width: 100px;">
    <logic:notEmpty name="ntp130Form" property="ntp130SortList">
    <html:optionsCollection name="ntp130Form" property="ntp130SortList" value="value" label="label" />
    </logic:notEmpty>
    </html:select>
    <html:radio name="ntp130Form" property="ntp130OrderKey1" styleId="ntp130OrderKey11" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.ORDER_KEY_ASC) %>" /><span class="text_base8"><label for="ntp130OrderKey11"><gsmsg:write key="cmn.order.asc" /></label></span>&nbsp;
    <html:radio name="ntp130Form" property="ntp130OrderKey1" styleId="ntp130OrderKey12" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.ORDER_KEY_DESC) %>" /><span class="text_base8"><label for="ntp130OrderKey12"><gsmsg:write key="cmn.order.desc" /></label></span>&nbsp;
    </td>

    <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="cmn.sortkey" />２</span></td>
    <td align="left" class="td_type20" width="35%" nowrap>
    <html:select name="ntp130Form" property="ntp130SortKey2" styleClass="select01" style="width: 100px;">
    <logic:notEmpty name="ntp130Form" property="ntp130SortList">
    <html:optionsCollection name="ntp130Form" property="ntp130SortList" value="value" label="label" />
    </logic:notEmpty>
    </html:select>
    <html:radio name="ntp130Form" property="ntp130OrderKey2" styleId="ntp130OrderKey21" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.ORDER_KEY_ASC) %>" /><span class="text_base8"><label for="ntp130OrderKey21"><gsmsg:write key="cmn.order.asc" /></label></span>&nbsp;
    <html:radio name="ntp130Form" property="ntp130OrderKey2" styleId="ntp130OrderKey22" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.ORDER_KEY_DESC) %>" /><span class="text_base8"><label for="ntp130OrderKey22"><gsmsg:write key="cmn.order.desc" /></label></span>&nbsp;
    </td>
    </tr>
    </table>

  </td>
  </tr>
  <tr>
  <td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td>
  </tr>
  <tr>
  <td align="center">
  <input type="button" name="btn_search" class="btn_search_n1" value="<gsmsg:write key="cmn.search" />" onClick="return buttonPush2('search');">
  </td>
  </tr>
  <tr>
  <td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td>
  </tr>

  <logic:notEmpty name="ntp130Form" property="ntp130ShohinList">
  <tr>
  <td align="right">
    <bean:size id="count1" name="ntp130Form" property="ntp130PageCmbList" scope="request" />
    <logic:greaterThan name="count1" value="1">
    <table width="100%" cellpadding="5" cellspacing="0">
      <td align="right">
        <img src="../common/images/arrow_w2.gif" alt="<gsmsg:write key="cmn.previous.page" />" width="13" height="13" onClick="buttonPush2('prevPage');">
        <html:select property="ntp130PageTop" onchange="changePage(0);">
          <html:optionsCollection name="ntp130Form" property="ntp130PageCmbList" value="value" label="label" />
        </html:select>
        <img src="../common/images/arrow_w.gif" alt="<gsmsg:write key="project.48" />" width="13" height="13" onClick="buttonPush2('nextPage');"></td>
      </tr>
    </table>
    </logic:greaterThan>
  </td>
  </tr>
  </logic:notEmpty>

  <tr>
  <td align="left">
    <table class="tl0 table_td_border2" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>

    <th align="center" class="table_bg_7D91BD" width="15%" nowrap><span style="font-size:12px;" class="text_tlw"><gsmsg:write key="cmn.category" /><br><gsmsg:write key="ntp.122" /></span></th>
    <th align="center" class="table_bg_7D91BD" width="30%" nowrap><span class="text_tlw"><gsmsg:write key="ntp.154" /></span></th>
    <th align="center" class="table_bg_7D91BD" width="10%" nowrap><span class="text_tlw"><gsmsg:write key="ntp.76" /></span></th>
    <th align="center" class="table_bg_7D91BD" width="10%" nowrap><span class="text_tlw"><gsmsg:write key="ntp.77" /></span></th>
    <th align="center" class="table_bg_7D91BD" width="12%" nowrap><span class="text_tlw"><gsmsg:write key="bmk.15" /></span></th>
    <th align="center" class="table_bg_7D91BD" width="12%" nowrap><span class="text_tlw"><gsmsg:write key="ntp.155" /></span></th>
    </tr>
    <logic:notEmpty name="ntp130Form" property="ntp130ShohinList">
    <bean:define id="tdColor" value="" />

    <% String[] tdColors = new String[] {"td_type1", "td_type_usr"}; %>

    <logic:iterate id="syohinMdl" name="ntp130Form" property="ntp130ShohinList" indexId="idx">
    <% tdColor = tdColors[(idx.intValue() % 2)]; %>

    <tr align="center" class="<%= tdColor %>">

    <!-- チェックボックス -->
    <logic:equal name="ntp130Form" property="ntp130DspMode" value="check">
    <td align="center">
    <bean:define id="sid" name="syohinMdl" property="nhnSid" type="java.lang.Integer" />
    <html:multibox property="ntp130ChkShohinSidList" value="<%= Integer.toString(sid.intValue()) %>"/>
    </td>
    </logic:equal>

    <!-- 商品コード -->
    <td align="left">
    <table><tr><td class="shohin_category">
    <div><bean:write name="syohinMdl" property="nscName" /></div>
    </td></tr></table>
    <a href="#" onclick="return buttonSubmit('edit','<bean:write name="syohinMdl" property="nhnSid" />') ">
    <span class="text_link"><bean:write name="syohinMdl" property="nhnCode" /></span>
    </a>
    </td>

    <!-- 商品名 -->
    <td align="left">
    <a href="#" onclick="return buttonSubmit('edit','<bean:write name="syohinMdl" property="nhnSid" />') ">
    <span class="text_link"><bean:write name="syohinMdl" property="nhnName" /></span>
    </a>
    </td>

    <!-- 販売金額 -->
    <td align="right">
    <span class="text_base8">￥<bean:write name="syohinMdl" property="ntp130PriceSale" /></span>
    </td>

    <!-- 原価金額 -->
    <td align="right">
    <span class="text_base8">￥<bean:write name="syohinMdl" property="ntp130PriceCost" /></span>
    </td>

    <!-- 登録日 -->
    <td align="right">
    <span class="text_base8"><bean:write name="syohinMdl" property="ntp130ADate" /></span>
    </td>

    <!-- 更新日 -->
    <td align="right">
    <span class="text_base8"><bean:write name="syohinMdl" property="ntp130EDate" /></span>
    </td>


    </tr>

    </logic:iterate>
    </logic:notEmpty>
    </table>
  </td>
  </tr>

  <logic:notEmpty name="ntp130Form" property="ntp130ShohinList">
  <tr>
  <td align="right">
    <bean:size id="count1" name="ntp130Form" property="ntp130PageCmbList" scope="request" />
    <logic:greaterThan name="count1" value="1">
    <table width="100%" cellpadding="5" cellspacing="0">
      <td align="right">
        <img src="../common/images/arrow_w2.gif" alt="<gsmsg:write key="cmn.previous.page" />" width="13" height="13" onClick="buttonPush2('prevPage');">
        <html:select property="ntp130PageBottom" onchange="changePage(1);">
          <html:optionsCollection name="ntp130Form" property="ntp130PageCmbList" value="value" label="label" />
        </html:select>
        <img src="../common/images/arrow_w.gif" alt="<gsmsg:write key="project.48" />" width="13" height="13" onClick="buttonPush2('nextPage');"></td>
      </tr>
    </table>
    </logic:greaterThan>
  </td>
  </tr>
  </logic:notEmpty>

  </table>

</td>
</tr>
</table>