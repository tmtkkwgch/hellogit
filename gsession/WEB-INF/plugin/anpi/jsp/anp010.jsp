<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>


<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[Group Session] <gsmsg:write key="anp.anp010.01" /></title>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href='../anpi/css/anpi.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script type="text/javascript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script type="text/javascript" src="../common/js/jquery.selection-min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../anpi/js/anp010.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>

</head>
<body class="body_03">
<html:form action="/anpi/anp010">
<!-- BODY -->
<input type="hidden" name="CMD">
<html:hidden property="backScreen" />
<html:hidden property="anpiSid" />
<html:hidden property="userSid" />
<html:hidden property="anp010NowPage" />
<html:hidden property="anp010SortKeyIndex" />
<html:hidden property="anp010OrderKey" />

<html:hidden property="anp010SearchKbn" />

<html:hidden property="anp010SvSearchSndKbn" />
<html:hidden property="anp010SvSearchAnsKbn" />
<html:hidden property="anp010SvSearchAnpKbn" />
<html:hidden property="anp010SvSearchPlcKbn" />
<html:hidden property="anp010SvSearchSyuKbn" />


<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td>

    <!-- タイトル -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tr>
        <td width="0%"><img src="../anpi/images/header_anpi_01.gif" border="0" alt=""></td>
        <td width="0%" class="header_white_bg_text"><gsmsg:write key="anp.plugin"/> [ <gsmsg:write key="anp.anp010.01"/> ]</td>
        <td width="100%" class="header_white_bg">
          <input type="button" value="<gsmsg:write key="cmn.reload" />" class="btn_reload_n1" onClick="buttonPush('reload');">
            <logic:equal name="anp010Form" property="anp010KanriFlg" value="1">
                <input type="button" name="btn_haisin" class="btn_haisin" value="<gsmsg:write key="anp.send.new"/>" onClick="buttonPush('anp010haisin');">
                <input type="button" name="btn_admin" class="btn_setting_admin_n1" value="<gsmsg:write key="cmn.admin.setting" />" onClick="buttonPush('anp010admtool');">
            </logic:equal>
            <input type="button" name="btn_kojn" class="btn_setting_n1" value="<gsmsg:write key="cmn.preferences2" />" onClick="buttonPush('anp010pritool');">
        </td>
        <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="anp.plugin"/>"></td>
        </tr>
    </table>

    <!-- 進行中データなし -->
    <logic:empty name="anp010Form" property="anpiSid" scope="request">
        <gsmsg:write key="anp.anp010.02"/>
    </logic:empty>

    <!-- 安否情報エリア START -->
    <logic:notEmpty name="anp010Form" property="anpiSid" scope="request">

    <!-- 訓練モード バー -->
    <logic:equal name="anp010Form" property="anp010KnrenFlg" value="1">
      <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
      <table cellspacing="0" border="0" width="100%" class="tl_u2" id="knren_top">
        <tr>
          <td valign="middle" class="table_bg_FFC1C1" align="center">
            <span class="text_r2"><gsmsg:write key="anp.knmode"/></span>
          </td>
        </tr>
      </table>
    </logic:equal>

    <table cellpadding="5" cellspacing="0" width="100%" border="0">
    <tr>
        <td valign="middle" align="right">
<!--         再送・完了ボタン -->
            <input type="button" name="btn_prjadd" class="btn_search_n1" value="<gsmsg:write key="cmn.advanced.search"/>" onClick="dispSearch();">
        <logic:equal name="anp010Form" property="anp010KanriFlg" value="1">
            <logic:equal name="anp010Form" property="anp010AllDeleteFlg" value="0">
            <input type="button" value="<gsmsg:write key="anp.send.re"/>" class="btn_resend" onClick="buttonPush('anp010saiso');">
            </logic:equal>
            <input type="button" value="<gsmsg:write key="anp.end"/>" class="btn_base1" onClick="buttonPush('anp010finish');">
        </logic:equal>
        </td>
    </tr>
    </table>

    <table id="haisin" class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
        <tr>
        <td class="t_top" width="260px">

          <table class="tl0" width="100%" cellpadding="5" cellspacing="0" border="0">
          <tr>
          <td align="right" >
            <input type="button" name="btn_info" class="btn_info" value="配信内容" onClick="buttonPush('anp010InfoConf');">
          </td>
          </tr>
          </table>

            <!-- あなたの回答状況 -->
            <logic:equal name="anp010Form" property="anp010SendFlg" value="true">
            <table class="tl0 table_td_border" width="100%" cellpadding="5" cellspacing="0">
                <tr class="table_bg_7D91BD">
                    <th colspan="3" align="center" nowrap><span class="text_tlw"><gsmsg:write key="anp.anp010.03"/></span></th>
                </tr>

                <logic:equal name="anp010Form" property="anp010AnsKbn" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.ANP_ANS_NO) %>">
                <tr class="td_type1">
                    <td colspan="3" align="center" nowrap><gsmsg:write key="anp.anp010.04"/>
                    <br><input type="button" class="btn_ans" onclick="selectSyosai(<bean:write name="anp010Form" property="anp010SessionUserInfo.usrSid" />);" value="<gsmsg:write key="anp.answer"/>"/>
                    </td>
                </tr>
                </logic:equal>
                <logic:equal name="anp010Form" property="anp010AnsKbn" value ="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.ANP_ANS_YES) %>">
                <tr class="td_type1">
                    <td colspan="3" align="center" nowrap><gsmsg:write key="anp.anp010.05"/><br><bean:write name="anp010Form" property="anp010SessionUserInfo.replyDate" />
                    <br><input type="button" class="btn_ans" onclick="selectSyosai(<bean:write name="anp010Form" property="anp010SessionUserInfo.usrSid" />);" value="<gsmsg:write key="cmn.edit"/>"/>
                    </td>
                </tr>

                <tr class="td_type1">
                    <th class="td_type3" width="65px" nowrap><gsmsg:write key="anp.jokyo"/></th>
                    <td colspan="2" nowrap>
                    <logic:equal name="anp010Form" property="anp010SessionUserInfo.jokyoflg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.JOKYO_FLG_UNSET) %>"><gsmsg:write key="cmn.notset"/></logic:equal>
                    <logic:equal name="anp010Form" property="anp010SessionUserInfo.jokyoflg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.JOKYO_FLG_GOOD) %>"><span class="text_jokyoGood"><gsmsg:write key="anp.jokyo.good"/></span></logic:equal>
                    <logic:equal name="anp010Form" property="anp010SessionUserInfo.jokyoflg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.JOKYO_FLG_KEISYO) %>"><span class="text_jokyoKeisyo"><gsmsg:write key="anp.jokyo.keisyo"/></span></logic:equal>
                    <logic:equal name="anp010Form" property="anp010SessionUserInfo.jokyoflg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.JOKYO_FLG_JUSYO) %>"><span class="text_jokyoJyusyo"><gsmsg:write key="anp.jokyo.jusyo"/></span></logic:equal>
                    </td>
                </tr>
                <tr class="td_type1">
                    <th class="td_type3" nowrap><gsmsg:write key="anp.place"/></th>
                    <td colspan="2" nowrap>
                    <logic:equal name="anp010Form" property="anp010SessionUserInfo.placeflg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.PLACE_FLG_UNSET) %>"><gsmsg:write key="cmn.notset"/></logic:equal>
                    <logic:equal name="anp010Form" property="anp010SessionUserInfo.placeflg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.PLACE_FLG_HOUSE) %>"><gsmsg:write key="anp.place.house"/></logic:equal>
                    <logic:equal name="anp010Form" property="anp010SessionUserInfo.placeflg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.PLACE_FLG_OFFICE) %>"><gsmsg:write key="anp.place.office"/></logic:equal>
                    <logic:equal name="anp010Form" property="anp010SessionUserInfo.placeflg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.PLACE_FLG_OUT) %>"><gsmsg:write key="anp.place.out"/></logic:equal>
                    </td>
                </tr>
                <tr class="td_type1">
                    <th class="td_type3" nowrap><gsmsg:write key="anp.syusya.state"/></th>
                    <td colspan="2" nowrap>
                    <logic:equal name="anp010Form" property="anp010SessionUserInfo.syusyaflg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SYUSYA_FLG_UNSET) %>"><gsmsg:write key="cmn.notset"/></logic:equal>
                    <logic:equal name="anp010Form" property="anp010SessionUserInfo.syusyaflg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SYUSYA_FLG_NO) %>"><span class="text_jokyoSyusyaNo"><gsmsg:write key="anp.syusya.no"/></span></logic:equal>
                    <logic:equal name="anp010Form" property="anp010SessionUserInfo.syusyaflg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SYUSYA_FLG_OK) %>"><gsmsg:write key="anp.syusya.ok"/></logic:equal>
                    <logic:equal name="anp010Form" property="anp010SessionUserInfo.syusyaflg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SYUSYA_FLG_OKD) %>"><gsmsg:write key="anp.syusya.okd"/></logic:equal>
                    </td>
                </tr>
                <tr class="td_type1">
                    <th class="td_type3" nowrap><gsmsg:write key="anp.comment"/></th>
                    <td colspan="2" width="200px"><bean:write name="anp010Form" property="anp010SessionUserInfo.comment" filter="false"/></td>
                </tr>
                </logic:equal>
            </table>

            <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0"><br>
            </logic:equal>

            <logic:notEmpty name="anp010Form" property="anp010State">
            <bean:define id="anpState" name="anp010Form" property="anp010State" />

            <!-- 現在の状況 -->
            <table class="tl0 table_td_border" width="100%" cellpadding="5" cellspacing="0">
                <tr class="table_bg_7D91BD">
                    <th colspan="3" align="center" nowrap><span class="text_tlw"><gsmsg:write key="anp.anp010.06"/></span></th>
                </tr>
                <tr class="td_type1">
                    <th class="td_type3" width="65px" nowrap><gsmsg:write key="anp.date.send"/></th>
                    <td colspan="2" nowrap><bean:write name="anpState" property="haisinDate" /></td>
                </tr>
                <tr class="td_type1">
                    <th class="td_type3" nowrap><gsmsg:write key="anp.date.resend"/></th>
                    <td colspan="2" nowrap><bean:write name="anpState" property="resendDate" /></td>
                </tr>
                <!-- 最終回答 -->
                <tr class="td_type1">
                    <th class="td_type3" nowrap><gsmsg:write key="anp.date.end"/></th>
                    <td colspan="2" nowrap><bean:write name="anpState" property="lastDate" /></td>
                </tr>
                <tr class="td_type1">
                    <th class="td_type3" nowrap><gsmsg:write key="anp.ans.state"/></th>
                    <td colspan="2" nowrap><bean:write name="anpState" property="replyState" /></td>
                </tr>
                <tr class="td_type1">
                    <th width="0%" class="td_type3" rowspan="3" nowrap><gsmsg:write key="anp.state"/></th>
                    <td width="0%" class="td_type3" nowrap><gsmsg:write key="anp.jokyo.good"/></td>
                    <td width="20%" align="right" nowrap><bean:write name="anpState" property="jokyoGood" /></td>
                </tr>
                <tr class="td_type1">
                    <td class="td_type3" nowrap><gsmsg:write key="anp.jokyo.keisyo"/></td>
                    <td align="right" nowrap><bean:write name="anpState" property="jokyoKeisyo" /></td>
                </tr>
                <tr class="td_type1">
                    <td class="td_type3" nowrap><gsmsg:write key="anp.jokyo.jusyo"/></td>
                    <td align="right" nowrap><bean:write name="anpState" property="jokyoJusyo" /></td>
                </tr>
                <tr class="td_type1">
                    <th class="td_type3" rowspan="2" nowrap><gsmsg:write key="anp.syusya"/></th>
                    <td class="td_type3" nowrap><gsmsg:write key="anp.syusya.ok2"/></td>
                    <td align="right" nowrap><bean:write name="anpState" property="syusyaOk" /></td>
                </tr>
                <tr class="td_type1">
                    <td class="td_type3" nowrap><gsmsg:write key="anp.syusya.no"/></td>
                    <td align="right" nowrap><bean:write name="anpState" property="syusyaNo" /></td>
                </tr>
            </table>
            </logic:notEmpty>

            <!-- 安否確認管理者一覧 -->
            <logic:notEmpty name="anp010Form" property="anp010AdmUsrList">
            <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0"><br>
             <table class="tl0 table_td_border" width="100%" cellpadding="5" cellspacing="0">
                <tr class="table_bg_7D91BD">
                    <th align="center" nowrap><span class="text_tlw"><gsmsg:write key="anp.anp080.13"/></span></th>
                </tr>
                <tr class="td_type1">
                    <td align="left" nowrap>
                    <logic:iterate id="adminUsr" name="anp010Form" property="anp010AdmUsrList">
                    <bean:write name="adminUsr"/><br>
                    </logic:iterate>
                    </td>
                </tr>
             </table>
            </logic:notEmpty>


            <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0"><br>
            <!-- アイコンの説明 -->
            <img src="../anpi/images/okmail.gif" border="0" alt="配信済" style="vertical-align : middle;">
                <span class="text_base_mini">：<gsmsg:write key="anp.icon.ok"/></span><br>
            <img src="../anpi/images/waitmail.gif" border="0" alt="配信中" style="vertical-align : middle;">
                <span class="text_base_mini">：<gsmsg:write key="anp.icon.wait"/></span><br>
            <img src="../anpi/images/nomail.gif" border="0" alt="配信エラー" style="vertical-align : middle;">
                <span class="text_base_mini">：<gsmsg:write key="anp.icon.error"/></span><br>
            <img src="../anpi/images/reply.gif" border="0" alt="回答有" style="vertical-align : middle;">
                <span class="text_base_mini">：<gsmsg:write key="anp.icon.answer"/></span><br>
        </td>

        <td class="t_top">
            <!-- 検索条件 -->
            <div id="top_Search">
            <table class="table_td_border searchCondition" cellpadding="0" cellspacing="0" width="100%">
            <tr>
                <td class="table_bg_A5B4E1" align="center" width="10%" nowrap>
                <span class="text_bb2"><gsmsg:write key="anp.send.state"/></span>
                </td>
                <td class="td_type20" width="90%" nowrap>
                <html:radio name="anp010Form" property="anp010SearchSndKbn" styleId="searchSndKbn0" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SEARCH_SENDKBN_ALL) %>" /><label for="searchSndKbn0"><gsmsg:write key="cmn.all"/></label>
                &nbsp;<html:radio name="anp010Form" property="anp010SearchSndKbn" styleId="searchSndKbn1" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SEARCH_SENDKBN_NO) %>" /><label for="searchSndKbn1"><gsmsg:write key="anp.send.notyet"/></label>
                &nbsp;<html:radio name="anp010Form" property="anp010SearchSndKbn" styleId="searchSndKbn2" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SEARCH_SENDKBN_OK) %>" /><label for="searchSndKbn2"><gsmsg:write key="anp.send.ok"/></label>
                </td>
            </tr>

            <tr>
                <td class="table_bg_A5B4E1" align="center" width="10%" nowrap>
                <span class="text_bb2"><gsmsg:write key="anp.ans.state"/></span>
                </td>
                <td class="td_type20" width="90%" nowrap>
                <html:radio name="anp010Form" property="anp010SearchAnsKbn" styleId="searchAnsKbn0" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SEARCH_ANSKBN_ALL) %>" onclick="changeDispRadio();" /><label for="searchAnsKbn0"><gsmsg:write key="cmn.all"/></label>
                &nbsp;<html:radio name="anp010Form" property="anp010SearchAnsKbn" styleId="searchAnsKbn1" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SEARCH_ANSKBN_NO) %>" onclick="changeDispRadio();"/><label for="searchAnsKbn1"><gsmsg:write key="anp.ans.notyet"/></label>
                &nbsp;<html:radio name="anp010Form" property="anp010SearchAnsKbn" styleId="searchAnsKbn2" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SEARCH_ANSKBN_OK) %>" onclick="changeDispRadio();" /><label for="searchAnsKbn2"><gsmsg:write key="anp.ans.ok"/></label>
                </td>
            </tr>

            <tr class="top_Search_anp">
                <td class="table_bg_A5B4E1" align="center" width="10%" nowrap>
                <span class="text_bb2"><gsmsg:write key="anp.jokyo"/></span>
                </td>
                <td class="td_type20" width="90%" nowrap>
                <html:radio name="anp010Form" property="anp010SearchAnpKbn" styleId="searchAnpKbn0" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SEARCH_ANPKBN_ALL) %>" /><label for="searchAnpKbn0"><gsmsg:write key="cmn.all"/></label>
                &nbsp;<html:radio name="anp010Form" property="anp010SearchAnpKbn" styleId="searchAnpKbn1" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SEARCH_ANPKBN_GOOD) %>" /><label for="searchAnpKbn1"><gsmsg:write key="anp.jokyo.good"/></label>
                &nbsp;<html:radio name="anp010Form" property="anp010SearchAnpKbn" styleId="searchAnpKbn2" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SEARCH_ANPKBN_KEISYO) %>" /><label for="searchAnpKbn2"><gsmsg:write key="anp.jokyo.keisyo"/></label>
                &nbsp;<html:radio name="anp010Form" property="anp010SearchAnpKbn" styleId="searchAnpKbn3" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SEARCH_ANPKBN_JUSYO) %>" /><label for="searchAnpKbn3"><gsmsg:write key="anp.jokyo.jusyo"/></label>
                </td>
            </tr>

            <tr class="top_Search_plc">
                <td class="table_bg_A5B4E1" align="center" width="10%" nowrap>
                <span class="text_bb2"><gsmsg:write key="anp.place"/></span>
                </td>
                <td class="td_type20" width="90%" nowrap>
                <html:radio name="anp010Form" property="anp010SearchPlcKbn" styleId="searchPlcKbn0" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SEARCH_PLACEKBN_ALL) %>" /><label for="searchPlcKbn0"><gsmsg:write key="cmn.all"/></label>
                &nbsp;<html:radio name="anp010Form" property="anp010SearchPlcKbn" styleId="searchPlcKbn1" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SEARCH_PLACEKBN_HOUSE) %>" /><label for="searchPlcKbn1"><gsmsg:write key="anp.place.house"/></label>
                &nbsp;<html:radio name="anp010Form" property="anp010SearchPlcKbn" styleId="searchPlcKbn2" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SEARCH_PLACEKBN_OFFICE) %>" /><label for="searchPlcKbn2"><gsmsg:write key="anp.place.office"/></label>
                &nbsp;<html:radio name="anp010Form" property="anp010SearchPlcKbn" styleId="searchPlcKbn3" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SEARCH_PLACEKBN_OUT) %>" /><label for="searchPlcKbn3"><gsmsg:write key="anp.place.out"/></label>
                </td>
            </tr>

            <tr class="top_Search_syu">
                <td class="table_bg_A5B4E1" align="center" width="10%" nowrap>
                <span class="text_bb2"><gsmsg:write key="anp.syusya.state"/></span>
                </td>
                <td class="td_type20" width="90%" nowrap>
                <html:radio name="anp010Form" property="anp010SearchSyuKbn" styleId="searchSyuKbn0" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SEARCH_SYUSYAKBN_ALL) %>" /><label for="searchSyuKbn0"><gsmsg:write key="cmn.all"/></label>
                &nbsp;<html:radio name="anp010Form" property="anp010SearchSyuKbn" styleId="searchSyuKbn1" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SEARCH_SYUSYAKBN_NO) %>" /><label for="searchSyuKbn1"><gsmsg:write key="anp.syusya.no"/></label>
                &nbsp;<html:radio name="anp010Form" property="anp010SearchSyuKbn" styleId="searchSyuKbn2" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SEARCH_SYUSYAKBN_OK) %>" /><label for="searchSyuKbn2"><gsmsg:write key="anp.syusya.ok"/></label>
                &nbsp;<html:radio name="anp010Form" property="anp010SearchSyuKbn" styleId="searchSyuKbn3" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SEARCH_SYUSYAKBN_OKD) %>" /><label for="searchSyuKbn3"><gsmsg:write key="anp.syusya.okd"/></label>
                </td>
            </tr>

            <tr>
                <td class="td_type20" colspan="2" align="center" width="100%" nowrap>
                <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0"><br>
                <input type="button" name="btn_prjadd" class="btn_search_n1" value="<gsmsg:write key="cmn.search"/>" onClick="buttonPush('detailSearch');">
                <input type="button" class="btn_base1" value="<gsmsg:write key="cmn.cancel"/>" onClick="dispSearch();"><br>
                <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
                </td>
            </tr>

            </table>
            <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
            </div>

            <table width="100%" cellpadding="5" cellspacing="0">
              <tr>
              <!-- グループコンボボックス -->
              <td width="0%" align="left" nowrap>
              <logic:notEmpty name="anp010Form" property="anp010GroupLabel" scope="request">
                <span class="text_tlw_black"><gsmsg:write key="cmn.show.group" /></span>
                <html:select property="anp010SelectGroupSid" onchange="buttonPush('anp010group');" styleClass="select01">
                <logic:notEmpty name="anp010Form" property="anp010GroupLabel" scope="request">
                <logic:iterate id="gpBean" name="anp010Form" property="anp010GroupLabel" scope="request">

                <bean:define id="gpValue" name="gpBean" property="value" type="java.lang.String" />
                <logic:equal name="gpBean" property="styleClass" value="0">
                <html:option value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
                </logic:equal>

                <logic:notEqual name="gpBean" property="styleClass" value="0">
                <html:option styleClass="select03" value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
                </logic:notEqual>

                </logic:iterate>
                </logic:notEmpty>

                </html:select>
                <input type="button" onclick="openGroupWindow(this.form.anp010SelectGroupSid, 'anp010SelectGroupSid', '1', 'anp010group')" class="group_btn" value="&nbsp;" id="groupBtn">
              </logic:notEmpty>
              </td>

              <!-- ページング -->
              <td width="0%" align="right" nowrap>
              <bean:size id="pageCount" name="anp010Form" property="anp010PageLabel" scope="request" />
              <logic:greaterThan name="pageCount" value="1">
                <img alt="<gsmsg:write key="cmn.previous.page" />" height="20" src="../common/images/arrow2_l.gif" class="text_i" width="20" border="0" onClick="buttonPush('anp010pageLast');">
                <logic:notEmpty name="anp010Form" property="anp010PageLabel" scope="request">
                <html:select property="anp010DspPage1" onchange="changePage(this);" styleClass="text_i">
                <html:optionsCollection name="anp010Form" property="anp010PageLabel" value="value" label="label" />
                </html:select>
                </logic:notEmpty>
                <img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="text_i" width="20" height="20" border="0" onClick="buttonPush('anp010pageNext');">
              </logic:greaterThan>
              </td>
              </tr>
            </table>

            <!-- 安否状況ユーザ一覧 -->
            <table class="tl0 table_td_border" width="100%" cellpadding="0" cellspacing="0">
              <tr class="table_bg_7D91BD">
              <bean:define id="sortKeyIndex" name="anp010Form" property="anp010SortKeyIndex" />
              <bean:define id="orderKey" name="anp010Form" property="anp010OrderKey" />
              <gsmsg:define id="h_send" msgkey="anp.send"/>
              <gsmsg:define id="h_answer" msgkey="anp.answer"/>
              <gsmsg:define id="h_name" msgkey="cmn.name"/>
              <gsmsg:define id="h_state" msgkey="anp.state"/>
              <gsmsg:define id="h_place" msgkey="anp.place"/>
              <gsmsg:define id="h_syusya" msgkey="anp.syusya"/>
              <gsmsg:define id="h_comment" msgkey="anp.comment"/>
              <% int iSortKeyIndex = ((Integer) sortKeyIndex).intValue();   %>
              <% int iOrderKey = ((Integer) orderKey).intValue();     %>
              <% String[] colTitle = new String[] {h_send, h_answer, h_name, h_state, h_place, h_syusya, h_comment}; %>
              <% String[] colWidth = new String[] {"5%", "10%", "20%", "0%", "0%", "0%", "50%"}; %>
              <% Integer[] colOrder = new Integer[] {1, 1, 1, 1, 1, 1, 0}; %>
              <% for (int i = 0; i < colTitle.length; i++) { %>
                  <%   String title = colTitle[i];                    %>
                  <%   Integer order = -1;                            %>
                  <%   if (iSortKeyIndex == i) {                      %>
                  <%     if (iOrderKey == GSConst.ORDER_KEY_ASC) {    %>
                  <%       title = title + "▲";                      %>
                  <%     } else {                                     %>
                  <%       title = title + "▼";                      %>
                  <%     }                                            %>
                  <%     order = iOrderKey;                           %>
                  <%   }                                              %>
                  <th width="<%= colWidth[i] %>" class="table_bg_7D91BD" nowrap>
                  <% if (colOrder[i] == 1) { %><a href="#" onClick="return sortList(<%= i %>, <%= order %>);"><% } %>
                  <span class="text_tlw"><%= title %></span><% if (colOrder[i] == 1) { %></a><% } %></th>
              <% } %>
              </tr>

              <logic:notEmpty name="anp010Form" property="anp010List">
              <logic:iterate id="detailModel" name="anp010Form" property="anp010List" indexId="idx">
                <% String[] typeClass = new String[] {"td_type1", "td_type_usr"}; %>
                <% String tdType = typeClass[(idx.intValue() % 2)]; %>
                <tr class="<%= tdType %>">
                  <td align="center" nowrap>
                      <logic:equal name="detailModel" property="haisinflg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.HAISIN_FLG_OK) %>">
                           <img src="../anpi/images/okmail.gif" border="0" alt="配信済">
                      </logic:equal>
                      <logic:equal name="detailModel" property="haisinflg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.HAISIN_FLG_UNSET) %>">
                           <img src="../anpi/images/waitmail.gif" border="0" alt="配信中">
                      </logic:equal>
                      <logic:equal name="detailModel" property="haisinflg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.HAISIN_FLG_ERROR) %>">
                           <img src="../anpi/images/nomail.gif" border="0" alt="配信エラー">
                      </logic:equal>
                  </td>
                  <td align="center" nowrap>
                      <logic:notEmpty name="detailModel" property="replyDate">
                      <img src="../anpi/images/reply.gif" border="0" alt="回答有">&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="detailModel" property="replyDate"/></logic:notEmpty>
                  </td>
                  <td align="left" nowrap>
                  <logic:equal name="detailModel" property="usrJkbn" value="<%= String.valueOf(GSConst.JTKBN_TOROKU) %>">
                  <a href="#" onclick="selectSyosai(<bean:write name="detailModel" property="usrSid" />);"><bean:write name="detailModel" property="name" /></a>
                  </logic:equal>
                  <logic:notEqual name="detailModel" property="usrJkbn" value="<%= String.valueOf(GSConst.JTKBN_TOROKU) %>">
                  <del><bean:write name="detailModel" property="name" /></del>
                  </logic:notEqual>
                 </td>
                  <td align="center" nowrap>
                      <logic:equal name="detailModel" property="jokyoflg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.JOKYO_FLG_UNSET) %>">-</logic:equal>
                      <logic:equal name="detailModel" property="jokyoflg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.JOKYO_FLG_GOOD) %>"><span class="text_jokyoGood"><gsmsg:write key="anp.jokyo.good"/></span></logic:equal>
                      <logic:equal name="detailModel" property="jokyoflg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.JOKYO_FLG_KEISYO) %>"><span class="text_jokyoKeisyo"><gsmsg:write key="anp.jokyo.keisyo"/></span></logic:equal>
                      <logic:equal name="detailModel" property="jokyoflg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.JOKYO_FLG_JUSYO) %>"><span class="text_jokyoJyusyo"><gsmsg:write key="anp.jokyo.jusyo"/></span></logic:equal>
                  </td>
                  <td align="center" nowrap>
                      <logic:equal name="detailModel" property="placeflg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.PLACE_FLG_UNSET) %>">-</logic:equal>
                      <logic:equal name="detailModel" property="placeflg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.PLACE_FLG_HOUSE) %>"><gsmsg:write key="anp.place.house"/></logic:equal>
                      <logic:equal name="detailModel" property="placeflg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.PLACE_FLG_OFFICE) %>"><gsmsg:write key="anp.place.office"/></logic:equal>
                      <logic:equal name="detailModel" property="placeflg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.PLACE_FLG_OUT) %>"><gsmsg:write key="anp.place.out"/></logic:equal>
                  </td>
                  <td align="center" nowrap>
                      <logic:equal name="detailModel" property="syusyaflg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SYUSYA_FLG_UNSET) %>">-</logic:equal>
                      <logic:equal name="detailModel" property="syusyaflg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SYUSYA_FLG_NO) %>"><span class="text_jokyoSyusyaNo"><gsmsg:write key="anp.syusya.no"/></span></logic:equal>
                      <logic:equal name="detailModel" property="syusyaflg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SYUSYA_FLG_OK) %>"><gsmsg:write key="anp.syusya.ok"/></logic:equal>
                      <logic:equal name="detailModel" property="syusyaflg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SYUSYA_FLG_OKD) %>"><gsmsg:write key="anp.syusya.okd"/></logic:equal>
                  </td>
                  <td align="left"><bean:write name="detailModel" property="comment" filter="false" /></td>
                </tr>
              </logic:iterate>
              </logic:notEmpty>
            </table>

            <table width="100%" cellpadding="5" cellspacing="0">
                <tr>
                <!-- ページング -->
                <td width="0%" align="right" nowrap>
                <bean:size id="pageCount" name="anp010Form" property="anp010PageLabel" scope="request" />
                <logic:greaterThan name="pageCount" value="1">
                    <img alt="<gsmsg:write key="cmn.previous.page" />" height="20" src="../common/images/arrow2_l.gif" class="text_i" width="20" border="0" onClick="buttonPush('anp010pageLast');">
                    <logic:notEmpty name="anp010Form" property="anp010PageLabel" scope="request">
                    <html:select property="anp010DspPage2" onchange="changePage(this);" styleClass="text_i">
                    <html:optionsCollection name="anp010Form" property="anp010PageLabel" value="value" label="label" />
                    </html:select>
                    </logic:notEmpty>
                    <img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="text_i" width="20" height="20" border="0" onClick="buttonPush('anp010pageNext');">
                </logic:greaterThan>
                </td>
                </tr>
            </table>
        </td>
        </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="5px" border="0">
    </logic:notEmpty>   <!-- 安否情報エリア END -->

    </td>
    </tr>
    </table>

</td>
</tr>
</table>

</html:form>
<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>