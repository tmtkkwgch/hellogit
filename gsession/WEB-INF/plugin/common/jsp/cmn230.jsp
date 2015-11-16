<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-groupTree.tld" prefix="groupTree" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.grouplist" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src='../common/js/jquery-ui-1.8.16/jquery-1.6.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../common/js/jquery-ui-1.8.16/ui/jquery-ui-1.8.16.custom.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/check.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn230.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery.cookie.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery.treeview.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery.infieldlabel.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel="stylesheet" href="../common/css/jquery.treeview.css?<%= GSConst.VERSION_PARAM %>" />
<link rel="stylesheet" href="../common/css/jquery.scrolltable.css?<%= GSConst.VERSION_PARAM %>" />
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<style type="text/css">
.textfield { position: relative; margin: 0 0 10px 0;}
.textfield label { position: absolute; top: 4; left: 3;}
.textfield br {display: none;}

.fakeContainer {
    margin: 0 0 0px;
    width:  100%;
    height: 195px;
    overflow: hidden;
}
.fakeContainer2 {
    margin: 0 0 0px;
    width:  350px;
    height: 195px;
    overflow: hidden;
}
.fakeContainer3 {
    margin: 0 0 0px;
    width:  100%;
    height: 195px;
    overflow: hidden;
}
.fakeContainer4 {
    margin: 0 0 0px;
    width:  495px;
    height: 195px;
    overflow: hidden;
}
.sData{
  overflow-x:hidden!important;
}

</style>
</head>

<body class="body_03" style="width:100%">
<html:form action="/common/cmn230">

<logic:equal name="cmn230Form" property="cmn230TabMode" value="0">
<script type="text/javascript">
$(function() {
    $("#tree").treeview({
        collapsed: true,
        animated: "fast",
        control:"#sidetreecontrol",
        persist: "cookie"
    });
    $("label").inFieldLabels();
})
</script>
</logic:equal>

<input type="hidden" name="CMD" value="">
<html:hidden property="selGroupSv" />
<html:hidden property="myGroupFlg" />
<html:hidden property="moveUserSid" />
<html:hidden property="selUserSid" />
<html:hidden property="svDomName" />
<html:hidden property="svDomName2" />
<html:hidden property="plginId" />
<html:hidden property="submitCmd" />
<html:hidden property="selBoxName" />
<html:hidden property="moveUsers" />
<html:hidden property="moveGroups" />
<html:hidden property="moveGroupSid" />
<html:hidden property="admGpFlg" />
<html:hidden property="splitFlg" />
<html:hidden property="cmdKbn" />
<html:hidden property="cmn230SearchFlg" />
<html:hidden property="cmn230groupSid" />
<html:hidden property="cmn230SortTopKey" />
<html:hidden property="cmn230SortTopKbn" />
<html:hidden property="cmn230SortBottomKey" />
<html:hidden property="cmn230SortBottomKbn" />
<html:hidden property="cmn230SortBottomKey2" />
<html:hidden property="cmn230SortBottomKbn2" />
<html:hidden property="cmn230SortTopKeyGp" />
<html:hidden property="cmn230SortTopKbnGp" />
<html:hidden property="cmn230SortBottomKeyGp" />
<html:hidden property="cmn230SortBottomKbnGp" />
<html:hidden property="cmn230SortBottomKeyGp2" />
<html:hidden property="cmn230SortBottomKbnGp2" />
<html:hidden property="cmn230TabFlg" />
<html:hidden property="cmn230TabMode" />

<logic:notEmpty name="cmn230Form" property="cmn230userSid" scope="request">
<logic:iterate id="ulBean" name="cmn230Form" property="cmn230userSid" scope="request">
<input type="hidden" name="cmn230userSid" value="<bean:write name="ulBean" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="cmn230Form" property="cmn230userSid2" scope="request">
<logic:iterate id="ulBean2" name="cmn230Form" property="cmn230userSid2" scope="request">
<input type="hidden" name="cmn230userSid2" value="<bean:write name="ulBean2" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="cmn230Form" property="cmn230groupSidadd" scope="request">
<logic:iterate id="ulBean3" name="cmn230Form" property="cmn230groupSidadd" scope="request">
<input type="hidden" name="cmn230groupSidadd" value="<bean:write name="ulBean3" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="cmn230Form" property="cmn230groupSidadd2" scope="request">
<logic:iterate id="ulBean4" name="cmn230Form" property="cmn230groupSidadd2" scope="request">
<input type="hidden" name="cmn230groupSidadd2" value="<bean:write name="ulBean4" />">
</logic:iterate>
</logic:notEmpty>

<table width="100%">
<tr>
<td width="100%" align="center">
  <table width="100%">
  <tr>
  <td align="left">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../user/images/header_useradd_01.gif" border="0" alt="<gsmsg:write key="cmn.group" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.sel.all.group" /></span></td>
    <td width="0%" class="header_white_bg_text">&nbsp;</td>
    <td width="100%" class="header_white_bg">
      <input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_close_n1" onClick="window.close();">
    </td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
  </td>
  </tr>
  </table>
</td>
</tr>
</table>

<logic:equal name="cmn230Form" property="cmn230TabFlg" value="1">

  <logic:equal name="cmn230Form" property="cmn230TabMode" value="0">
    <table class="tab_table" width="100%" border="0" cellpadding="0" cellspacing="0" height="34px">
    <tbody>
      <tr>
        <td class="tab_bg_image_1_on" width="100px" align="left" nowrap="nowrap">
          <div class="tab_text_area">
            <a href="javascript:changeTab('230_tabUser');"><gsmsg:write key="cmn.user" /></a>
          </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_1_off" width="107px" align="left" nowrap="nowrap">
          <div class="tab_text_area_right">
            <a href="javascript:changeTab('230_tabGroup');"><gsmsg:write key="cmn.group" /></a>
          </div>
        </td>
        <td class="tab_bg_underbar" width="100%" nowrap="nowrap">&nbsp;</td>
      </tr>
    </tbody>
    </table>

   </logic:equal>

   <logic:equal name="cmn230Form" property="cmn230TabMode" value="1">
      <table class="tab_table" width="100%" border="0" cellpadding="0" cellspacing="0" height="34px">
      <tbody>
        <tr>
          <td class="tab_bg_image_1_off" width="100px" align="left" nowrap="nowrap">
            <div class="tab_text_area_right">
              <a href="javascript:changeTab('230_tabUser');"><gsmsg:write key="cmn.user" /></a>
            </div>
          </td>
          <td class="tab_space" nowrap>&nbsp;</td>
          <td class="tab_bg_image_1_on" width="107px" align="left" nowrap="nowrap">
            <div class="tab_text_area">
              <a href="javascript:changeTab('230_tabGroup');"><gsmsg:write key="cmn.group" /></a>
            </div>
          </td>
          <td class="tab_bg_underbar" valign="top" width="100%" align="right" nowrap="nowrap"></td>
        </tr>
      </tbody>
      </table>
   </logic:equal>
   <img src="../common/images/damy.gif" alt="" width="10" border="0" height="10">
</logic:equal>

<logic:equal name="cmn230Form" property="cmn230TabMode" value="0">

  <!-- ページコンテンツ start -->
  <table cellpadding="5" cellspacing="0" width="100%">
  <tr>
  <td width="20%" valign="top">

    <table class="tl0 prj_tbl_base4" width="100%" cellpadding="0" cellspacing="0"  style="border:solid 1px; border-color:#cccccc;">
    <tr>
    <th class="prj_mokuteki" nowrap><gsmsg:write key="wml.wml040.04" /></th>
    </tr>

    <tr>
    <td class="td_line_color2 cabinet_combo" align="center">
    </td>
    </tr>

    <tr>
    <td class="f_tree prj_tbl_base4_fontsize">

      <div style="padding-left:7px;width:250px;height:500px;overflow:scroll;">
          <div id="sidetree" style="margin-top:0px!important;margin-left:10px!important;">
          <div id="sidetreecontrol"><a href="?#"><gsmsg:write key="cmn.all.close" /></a> | <a href="?#"><gsmsg:write key="cmn.all.open" /></a></div>

          <% int pastVal = 0; %>

          <ul id="tree">

          <logic:notEmpty name="cmn230Form" property="myGroupList">
            <logic:iterate id="mgl" name="cmn230Form" property="myGroupList" indexId="idx">
                <li>
                  <a href="javascript:void(0);" onClick="return changeGroup('<bean:write name="mgl" property="value" />');">
                    <bean:write name="mgl" property="label" />
                  </a>
                </li>
            </logic:iterate>
          </logic:notEmpty>

          <logic:iterate id="mdl" name="cmn230Form" property="groupList" indexId="idx">

            <logic:equal name="mdl" property="classLevel" value="1">
              <% if (pastVal == 2) { %>
                </ul>
              </li>
              <% } else if (pastVal == 3) { %>
                    </ul>
                  </li>
                </ul>
              </li>
              <% } else if (pastVal == 4) { %>
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </li>
                  </ul>
                </li>
              <% } else if (pastVal == 5) { %>
                              </li>
                            </ul>
                          </li>
                        </ul>
                      </li>
                    </ul>
                  </li>
                </ul>
              </li>
              <% } else if (pastVal == 6) { %>
                                  </li>
                                </ul>
                              </li>
                            </ul>
                          </li>
                        </ul>
                      </li>
                    </ul>
                  </li>
                </ul>
              </li>
              <% } else if (pastVal == 7) { %>
                                      </li>
                                    </ul>
                                  </li>
                                </ul>
                              </li>
                            </ul>
                          </li>
                        </ul>
                      </li>
                    </ul>
                  </li>
                </ul>
              </li>
              <% } else if (pastVal == 8) { %>
                                          </li>
                                        </ul>
                                      </li>
                                    </ul>
                                  </li>
                                </ul>
                              </li>
                            </ul>
                          </li>
                        </ul>
                      </li>
                    </ul>
                  </li>
                </ul>
              </li>
              <% } else if (pastVal == 9) { %>
                                              </li>
                                            </ul>
                                          </li>
                                        </ul>
                                      </li>
                                    </ul>
                                  </li>
                                </ul>
                              </li>
                            </ul>
                          </li>
                        </ul>
                      </li>
                    </ul>
                  </li>
                </ul>
              </li>
              <% } else if (pastVal == 10) { %>
                                                  </li>
                                                </ul>
                                              </li>
                                            </ul>
                                          </li>
                                        </ul>
                                      </li>
                                    </ul>
                                  </li>
                                </ul>
                              </li>
                            </ul>
                          </li>
                        </ul>
                      </li>
                    </ul>
                  </li>
                </ul>
              </li>
              <% } else if (idx != 0) { %>
              </li>
              <% } %>
              <li>
                <a href="javascript:void(0);" onClick="return changeGroup('<bean:write name="mdl" property="groupSid" />');">
                  <bean:write name="mdl" property="groupName" />
                </a>
            </logic:equal>


            <logic:equal name="mdl" property="classLevel" value="2">
              <% if (pastVal == 1) { %>
                <ul>
              <% } else if (pastVal == 2) { %>
                  </li>
              <% } else if (pastVal == 3) { %>
                      </li>
                    </ul>
                  </li>
              <% } else if (pastVal == 4) { %>
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </li>
              <% } else if (pastVal == 5) { %>
                              </li>
                            </ul>
                          </li>
                        </ul>
                      </li>
                    </ul>
                  <li>
              <% } else if (pastVal == 6) { %>
                                </li>
                              </ul>
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </li>
                  </ul>
                </li>
              <% } else if (pastVal == 7) { %>
                                    </li>
                                  </ul>
                                </li>
                              </ul>
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </li>
                  </ul>
                </li>
              <% } else if (pastVal == 8) { %>
                                        </li>
                                      </ul>
                                    </li>
                                  </ul>
                                </li>
                              </ul>
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </li>
                  </ul>
                </li>
              <% } else if (pastVal == 9) { %>
                                            </li>
                                          </ul>
                                        </li>
                                      </ul>
                                    </li>
                                  </ul>
                                </li>
                              </ul>
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </li>
                  </ul>
                </li>
              <% } else if (pastVal == 10) { %>
                                                </li>
                                              </ul>
                                            </li>
                                          </ul>
                                        </li>
                                      </ul>
                                    </li>
                                  </ul>
                                </li>
                              </ul>
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </li>
                  </ul>
                </li>
              <% } %>
                  <li>
                    <a href="javascript:void(0);" onClick="return changeGroup('<bean:write name="mdl" property="groupSid" />');">
                      <bean:write name="mdl" property="groupName" />
                    </a>
            </logic:equal>


            <logic:equal name="mdl" property="classLevel" value="3">
              <% if (pastVal == 2) { %>
                  <ul>
              <% } else if (pastVal == 3) { %>
                    </li>
              <% } else if (pastVal == 4) { %>
                        </li>
                      </ul>
                    </li>
              <% } else if (pastVal == 5) { %>
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </li>
              <% } else if (pastVal == 6) { %>
                              </li>
                            </ul>
                          </li>
                        </ul>
                      </li>
                    </ul>
                  </li>
              <% } else if (pastVal == 7) { %>
                                  </li>
                                </ul>
                              </li>
                            </ul>
                          </li>
                        </ul>
                      </li>
                    </ul>
                  </li>
              <% } else if (pastVal == 8) { %>
                                      </li>
                                    </ul>
                                  </li>
                                </ul>
                              </li>
                            </ul>
                          </li>
                        </ul>
                      </li>
                    </ul>
                  </li>
              <% } else if (pastVal == 9) { %>
                                          </li>
                                        </ul>
                                      </li>
                                    </ul>
                                  </li>
                                </ul>
                              </li>
                            </ul>
                          </li>
                        </ul>
                      </li>
                    </ul>
                  </li>
              <% } else if (pastVal == 10) { %>
                                              </li>
                                            </ul>
                                          </li>
                                        </ul>
                                      </li>
                                    </ul>
                                  </li>
                                </ul>
                              </li>
                            </ul>
                          </li>
                        </ul>
                      </li>
                    </ul>
                  </li>
              <% } %>
                    <li>
                      <a href="javascript:void(0);" onClick="return changeGroup('<bean:write name="mdl" property="groupSid" />');">
                        <bean:write name="mdl" property="groupName" />
                      </a>
            </logic:equal>


            <logic:equal name="mdl" property="classLevel" value="4">
              <% if (pastVal == 3) { %>
                    <ul>
              <% }  else if (pastVal == 4) { %>
                      </li>
              <% }  else if (pastVal == 5) { %>
                          </li>
                        </ul>
                      </li>
              <% } else if (pastVal == 6) { %>
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </li>
              <% } else if (pastVal == 7) { %>
                                </li>
                              </ul>
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </li>
              <% } else if (pastVal == 8) { %>
                                    </li>
                                  </ul>
                                </li>
                              </ul>
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </li>
              <% } else if (pastVal == 9) { %>
                                        </li>
                                      </ul>
                                    </li>
                                  </ul>
                                </li>
                              </ul>
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </li>
              <% } else if (pastVal == 10) { %>
                                            </li>
                                          </ul>
                                        </li>
                                      </ul>
                                    </li>
                                  </ul>
                                </li>
                              </ul>
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </li>
              <% } %>
                      <li>
                        <a href="javascript:void(0);" onClick="return changeGroup('<bean:write name="mdl" property="groupSid" />');">
                          <bean:write name="mdl" property="groupName" />
                        </a>
            </logic:equal>


            <logic:equal name="mdl" property="classLevel" value="5">
              <% if (pastVal == 4) { %>
                    <ul>
              <% }  else if (pastVal == 5) { %>
                      </li>
              <% } else if (pastVal == 6) { %>
                          </li>
                        </ul>
                      </li>
              <% } else if (pastVal == 7) { %>
                              </li>
                            </ul>
                          </li>
                        </ul>
                      </li>
              <% } else if (pastVal == 8) { %>
                                  </li>
                                </ul>
                              </li>
                            </ul>
                          </li>
                        </ul>
                      </li>
              <% } else if (pastVal == 9) { %>
                                      </li>
                                    </ul>
                                  </li>
                                </ul>
                              </li>
                            </ul>
                          </li>
                        </ul>
                      </li>
              <% } else if (pastVal == 10) { %>
                                          </li>
                                        </ul>
                                      </li>
                                    </ul>
                                  </li>
                                </ul>
                              </li>
                            </ul>
                          </li>
                        </ul>
                      </li>
              <% } %>
                      <li>
                        <a href="javascript:void(0);" onClick="return changeGroup('<bean:write name="mdl" property="groupSid" />');">
                          <bean:write name="mdl" property="groupName" />
                        </a>
            </logic:equal>

            <logic:equal name="mdl" property="classLevel" value="6">
              <% if (pastVal == 5) { %>
                    <ul>
              <% }  else if (pastVal == 6) { %>
                      </li>
              <% } else if (pastVal == 7) { %>
                          </li>
                        </ul>
                      </li>
              <% } else if (pastVal == 8) { %>
                              </li>
                            </ul>
                          </li>
                        </ul>
                      </li>
              <% } else if (pastVal == 9) { %>
                                  </li>
                                </ul>
                              </li>
                            </ul>
                          </li>
                        </ul>
                      </li>
              <% } else if (pastVal == 10) { %>
                                      </li>
                                    </ul>
                                  </li>
                                </ul>
                              </li>
                            </ul>
                          </li>
                        </ul>
                      </li>
              <% } %>
                      <li>
                        <a href="javascript:void(0);" onClick="return changeGroup('<bean:write name="mdl" property="groupSid" />');">
                          <bean:write name="mdl" property="groupName" />
                        </a>
            </logic:equal>

            <logic:equal name="mdl" property="classLevel" value="7">
              <% if (pastVal == 6) { %>
                    <ul>
              <% }  else if (pastVal == 7) { %>
                      </li>
              <% } else if (pastVal == 8) { %>
                          </li>
                        </ul>
                      </li>
              <% } else if (pastVal == 9) { %>
                              </li>
                            </ul>
                          </li>
                        </ul>
                      </li>
              <% } else if (pastVal == 10) { %>
                                  </li>
                                </ul>
                              </li>
                            </ul>
                          </li>
                        </ul>
                      </li>
              <% } %>
                      <li>
                        <a href="javascript:void(0);" onClick="return changeGroup('<bean:write name="mdl" property="groupSid" />');">
                          <bean:write name="mdl" property="groupName" />
                        </a>
            </logic:equal>

            <logic:equal name="mdl" property="classLevel" value="8">
              <% if (pastVal == 7) { %>
                    <ul>
              <% }  else if (pastVal == 8) { %>
                      </li>
              <% } else if (pastVal == 9) { %>
                          </li>
                        </ul>
                      </li>
              <% } else if (pastVal == 10) { %>
                              </li>
                            </ul>
                          </li>
                        </ul>
                      </li>
              <% } %>
                      <li>
                        <a href="javascript:void(0);" onClick="return changeGroup('<bean:write name="mdl" property="groupSid" />');">
                          <bean:write name="mdl" property="groupName" />
                        </a>
            </logic:equal>

            <logic:equal name="mdl" property="classLevel" value="9">
              <% if (pastVal == 8) { %>
                    <ul>
              <% }  else if (pastVal == 9) { %>
                      </li>
              <% } else if (pastVal == 10) { %>
                          </li>
                        </ul>
                      </li>
              <% } %>
                      <li>
                        <a href="javascript:void(0);" onClick="return changeGroup('<bean:write name="mdl" property="groupSid" />');">
                          <bean:write name="mdl" property="groupName" />
                        </a>
            </logic:equal>

            <logic:equal name="mdl" property="classLevel" value="10">
              <% if (pastVal == 9) { %>
                    <ul>
              <% }  else if (pastVal == 10) { %>
                      </li>
              <% } %>
                      <li>
                        <a href="javascript:void(0);" onClick="return changeGroup('<bean:write name="mdl" property="groupSid" />');">
                          <bean:write name="mdl" property="groupName" />
                        </a>
            </logic:equal>

            <bean:define id="pastval" name="mdl" property="classLevel" />
            <% pastVal = ((Integer) pastval).intValue(); %>

          </logic:iterate>

          </ul>
          </div>
      </div>
    </td>
    </tr>
    </table>
  </td>

  <td width="80%" valign="top" style="padding-left: 0px;padding-right: 0px;">


    <div class="textfield">
      <label for="field_id"><gsmsg:write key="cmn.search.item" /></label>

      <html:text name="cmn230Form" style="width:395px;" maxlength="50" property="cmn230SearchStr" styleClass="text_base" styleId="field_id" />
      <input value="<gsmsg:write key="cmn.search" />" class="btn_base1" onclick="searchUser('searchUsr');" type="button">
    </div>
    <table class="tl0" width="100%" border="0" cellpadding="5">
    <tbody>

    <tr>
      <td  colspan="3">
      <table width="100%">
      <tr>
      <td class="table_bg_A5B4E1" align="center">
      <span class="text_bb1"><bean:write name="cmn230Form" property="cmn230DspGrpName"/></span>
      </td>
      </tr>
      </table>

      </td>
    </tr>

    <tr>
    <td colspan="3">

     <div class="fakeContainer" style="border:solid 1px; border-color:#cccccc;">
     <table id="demoTable" class="demoTable table_td_border2" width="100%" cellpadding="0" cellspacing="0">

      <thead>
      <tr class="table_bg_7D91BD">

        <!-- チェックボックス -->
        <th width="3%" align="center" nowrap="nowrap">
         <span class="text_tlw"><input type="checkbox" name="cmn230AllCheckTop" value="1" onClick="chgCheckAll('cmn230AllCheckTop', 'cmn230userSidTop');chgCheckAllChange('cmn230AllCheckTop', 'cmn230userSidTop');"></span>
        </th>

        <!-- 社員/職員番号 -->
        <th width="40%" nowrap="nowrap">
          <logic:equal name="cmn230Form" property="cmn230SortTopKey" value="0">
            <logic:equal name="cmn230Form" property="cmn230SortTopKbn" value="1">
              <a href="#" onclick="clickSortTitle(0);"><span class="text_tlw"><gsmsg:write key="cmn.employee.staff.number" />▲</span></a>
            </logic:equal>
            <logic:notEqual name="cmn230Form" property="cmn230SortTopKbn" value="1">
              <a href="#" onclick="clickSortTitle(0);"><span class="text_tlw"><gsmsg:write key="cmn.employee.staff.number" />▼</span></a>
            </logic:notEqual>
          </logic:equal>
          <logic:notEqual name="cmn230Form" property="cmn230SortTopKey" value="0">
            <a href="#" onclick="clickSortTitle(0);"><span class="text_tlw"><gsmsg:write key="cmn.employee.staff.number" /></span></a>
          </logic:notEqual>

          <span class="text_tlw">/</span>
        <!-- 氏名 -->
          <logic:equal name="cmn230Form" property="cmn230SortTopKey" value="1">
            <logic:equal name="cmn230Form" property="cmn230SortTopKbn" value="1">
              <a href="#" onclick="clickSortTitle(1);"><span class="text_tlw"><gsmsg:write key="cmn.name" />▲</span></a>
            </logic:equal>
            <logic:notEqual name="cmn230Form" property="cmn230SortTopKbn" value="1">
              <a href="#" onclick="clickSortTitle(1);"><span class="text_tlw"><gsmsg:write key="cmn.name" />▼</span></a>
            </logic:notEqual>
          </logic:equal>
          <logic:notEqual name="cmn230Form" property="cmn230SortTopKey" value="1">
            <a href="#" onclick="clickSortTitle(1);"><span class="text_tlw"><gsmsg:write key="cmn.name" /></span></a>
          </logic:notEqual>
          <span class="text_tlw">/</span>
        <!-- 役職 -->
        <span class="text_tlw"><gsmsg:write key="cmn.post" /></span>
        </th>


        <!-- 所属グループ -->
        <th width="55%" nowrap="nowrap" style="border-right:0px">
          <span class="text_tlw"><gsmsg:write key="cmn.affiliation.group" /></span>
        </th>

       <th width="2%" nowrap="nowrap" style="width:5px; border-left:0px;">&nbsp;</th>

      </tr>
      </thead>

      <tbody>

      <logic:notEmpty name="cmn230Form" property="cmn230TopUserList">
      <logic:iterate id="userMdl" name="cmn230Form" property="cmn230TopUserList" indexId="idx">

        <bean:define id="backclass" value="td_line_color" />
        <bean:define id="backpat" value="<%= String.valueOf((idx.intValue() % 2) + 1) %>" />
        <bean:define id="back" value="<%= String.valueOf(backclass) + String.valueOf(backpat) %>" />

        <tr class="td_type1 <%= String.valueOf(back) %>" style="font-size:90%;" id="<bean:write name="userMdl" property="usrSid" />">
          <!-- チェックボックス -->
          <td>
            <% if (Integer.valueOf(backpat) == 1) { %>
              <html:multibox name="cmn230Form" property="cmn230userSidTop" onclick="backGroundSetting(this, '1');">
                <bean:write name="userMdl" property="usrSid" />
              </html:multibox>
            <% } else { %>
              <html:multibox name="cmn230Form" property="cmn230userSidTop" onclick="backGroundSetting(this, '2');">
                <bean:write name="userMdl" property="usrSid" />
              </html:multibox>
            <% } %>
          </td>

          <td>
          <!-- 社員/職員番号 -->
          <logic:notEmpty name="userMdl" property="usiSyainNo" >
          <span style="font-size:12px;"><bean:write name="userMdl" property="usiSyainNo" /></span><br>
          </logic:notEmpty>
          <!-- 氏名 -->
            <a href="javaScript:void(0);" onclick="moveUserButton('230_belowarrow', '<bean:write name="userMdl" property="usrSid" />');">
              <span class="normal_link"><bean:write name="userMdl" property="usiSei" />&nbsp;<bean:write name="userMdl" property="usiMei" /></span>
            </a>
          <!-- 役職 -->
          <logic:notEmpty name="userMdl" property="usiYakusyoku">
          <br><span style="font-size:12px;"><bean:write name="userMdl" property="usiYakusyoku" /></span>
          </logic:notEmpty>
          </td>

          <!-- 所属グループ -->
          <td nowrap="nowrap" style="border-right:0px">
          <logic:notEmpty name="userMdl" property="belongGrpList">
          <logic:iterate id="grpMdl" name="userMdl" property="belongGrpList" indexId="idx">
                <div><bean:write name="grpMdl" property="groupName" /></div>
          </logic:iterate>
          </logic:notEmpty>
          </td>

          <td style="border-left:0px"></td>
        </tr>

      </logic:iterate>
      </logic:notEmpty>
      <logic:empty name="cmn230Form" property="cmn230TopUserList">
        <tr>
        <td colspan="5">
          <div align="center"><gsmsg:write key="cmn.not.exist.user" /></div>
        </td>
        </tr>
      </logic:empty>

    </tbody>
    </table>
    </div>

    </td>
    </tr>

    <tr>
      <td>
        <div align="center">
          <input type="button" class="btn_base2add" value="<gsmsg:write key="cmn.add" />" name="adduserBtn" onClick="moveUserAddButton('230_abovearrow_button');">
          <input type="button" class="btn_base2del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="moveUserDelButton('230_belowarrow_button');">
        </div>
      </td>
      <td width="1px"></td>
      <td>
        <div align="center">
          <input type="button" class="btn_base2add" value="<gsmsg:write key="cmn.add" />" name="adduserBtn" onClick="moveUserAddButton('230_abovearrow_button2');">
          <input type="button" class="btn_base2del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="moveUserDelButton2('230_belowarrow_button2');">
        </div>
      </td>
    </tr>

    <tr>
      <td>
      <table width="100%">
      <tr>
      <td class="table_bg_A5B4E1" align="center">
      <span class="text_bb1"><bean:write name="cmn230Form" property="cmn230LeftTitleName" /></span>
      </td>
      </tr>
      </table>

      </td>
      <td width="1px"></td>
      <td>
      <table width="100%">
      <tr>
      <td class="table_bg_A5B4E1" align="center">
        <span class="text_bb1"><bean:write name="cmn230Form" property="cmn230RightTitleName" /></span>
      </td>
      </tr>
      </table>
      </td>
    </tr>

    <tr>
    <td>

    <div class="fakeContainer2" style="border:solid 1px; border-color:#cccccc;">
    <table id="demoTable2" class="demoTable table_td_border2" width="100%" cellpadding="0" cellspacing="0">
      <thead>
      <tr class="table_bg_7D91BD">

        <!-- チェックボックス -->
        <th width="3%" align="center" nowrap="nowrap">
         <span class="text_tlw" >
           <input type="checkbox" name="cmn230AllCheckBottom" value="1" onClick="chgCheckAll('cmn230AllCheckBottom', 'cmn230userSidBottom');chgCheckAllChange2('cmn230AllCheckBottom', 'cmn230userSidBottom');">
        </th>

        <!-- 社員/職員番号 -->
        <th width="40%" nowrap="nowrap">
          <logic:equal name="cmn230Form" property="cmn230SortBottomKey" value="0">
            <logic:equal name="cmn230Form" property="cmn230SortBottomKbn" value="1">
            <a href="#" onclick="clickSortTitle2(0);"><span class="text_tlw" style="font-size:75%"><gsmsg:write key="cmn.employee.staff.number" />▲</span></a>
            </logic:equal>
            <logic:notEqual name="cmn230Form" property="cmn230SortBottomKbn" value="1">
              <a href="#" onclick="clickSortTitle2(0);"><span class="text_tlw" style="font-size:75%"><gsmsg:write key="cmn.employee.staff.number" />▼</span></a>
            </logic:notEqual>
          </logic:equal>
          <logic:notEqual name="cmn230Form" property="cmn230SortBottomKey" value="0">
            <a href="#" onclick="clickSortTitle2(0);"><span class="text_tlw" style="font-size:75%"><gsmsg:write key="cmn.employee.staff.number" /></span></a>
          </logic:notEqual>
          <span class="text_tlw" style="font-size:50%">/</span>
        <!-- 氏名 -->
          <logic:equal name="cmn230Form" property="cmn230SortBottomKey" value="1">
            <logic:equal name="cmn230Form" property="cmn230SortBottomKbn" value="1">
              <a href="#" onclick="clickSortTitle2(1);"><span class="text_tlw" style="font-size:75%"><gsmsg:write key="cmn.name" />▲</span></a>
            </logic:equal>
            <logic:notEqual name="cmn230Form" property="cmn230SortBottomKbn" value="1">
              <a href="#" onclick="clickSortTitle2(1);"><span class="text_tlw" style="font-size:75%"><gsmsg:write key="cmn.name" />▼</span></a>
            </logic:notEqual>
          </logic:equal>
          <logic:notEqual name="cmn230Form" property="cmn230SortBottomKey" value="1">
            <a href="#" onclick="clickSortTitle2(1);"><span class="text_tlw" style="font-size:75%"><gsmsg:write key="cmn.name" /></span></a>
          </logic:notEqual>
          <span class="text_tlw" style="font-size:50%">/</span>
          <!-- 役職 -->
          <span class="text_tlw" style="font-size:75%"><gsmsg:write key="cmn.post" /></span>
        </th>


        <!-- 所属グループ -->
        <th width="55%" nowrap="nowrap" style="border-right:0px">
          <span class="text_tlw" style="font-size:75%"><gsmsg:write key="cmn.affiliation.group" /></span>
        </th>
        <th width="2%" nowrap="nowrap" style="width:5px; border-left:0px" >&nbsp;</th>

      </tr>
      </thead>

      <tbody>

      <logic:notEmpty name="cmn230Form" property="cmn230BottomUserList">
      <logic:iterate id="selUserMdl" name="cmn230Form" property="cmn230BottomUserList" indexId="idx">

        <bean:define id="backclass" value="td_line_color" />
        <bean:define id="backpat" value="<%= String.valueOf((idx.intValue() % 2) + 3) %>" />
        <bean:define id="back" value="<%= String.valueOf(backclass) + String.valueOf(backpat) %>" />

        <tr class="td_type1 <%= String.valueOf(back) %>" style="font-size:90%;" id="<bean:write name="selUserMdl" property="usrSid" />">
          <!-- チェックボックス -->
          <td>
            <% if (Integer.valueOf(backpat) == 3) { %>
              <html:multibox name="cmn230Form" property="cmn230userSidBottom" onclick="backGroundSetting2(this, '3');">
                <bean:write name="selUserMdl" property="usrSid" />
              </html:multibox>
            <% } else { %>
              <html:multibox name="cmn230Form" property="cmn230userSidBottom" onclick="backGroundSetting2(this, '4');">
                <bean:write name="selUserMdl" property="usrSid" />
              </html:multibox>
            <% } %>
          </td>

          <!-- 社員/職員番号 -->
          <td>
          <logic:notEmpty name="selUserMdl" property="usiSyainNo">
          <span style="font-size:12px;"><bean:write name="selUserMdl" property="usiSyainNo" /></span><br>
          </logic:notEmpty>
          <!-- 氏名 -->
            <a href="#" onclick="moveUserButton('230_abovearrow', '<bean:write name="selUserMdl" property="usrSid" />');">
              <span class="normal_link"><bean:write name="selUserMdl" property="usiSei" />&nbsp;<bean:write name="selUserMdl" property="usiMei" /></span>
            </a>
            <!-- 役職 -->
            <logic:notEmpty name="selUserMdl" property="usiYakusyoku">
            <br><span style="font-size:12px;"><bean:write name="selUserMdl" property="usiYakusyoku" /></span>
            </logic:notEmpty>
          </td>
          <!-- 所属グループ -->
          <td style="border-right:0px">
          <logic:notEmpty name="selUserMdl" property="belongGrpList">
          <logic:iterate id="selGrpMdl" name="selUserMdl" property="belongGrpList" indexId="idx">
                <div style="font-size:12px;"><bean:write name="selGrpMdl" property="groupName" /></div>
          </logic:iterate>
          </logic:notEmpty>
          </td>
          <td style="border-left:0px"></td>
        </tr>
      </logic:iterate>
      </logic:notEmpty>

      </tbody>
    </table>
    </div>

    </td>


   <td width="0%"></td>


    <td>

    <div class="fakeContainer2" style="border:solid 1px; border-color:#cccccc;">
    <table id="demoTable3" class="demoTable table_td_border2" width="100%" cellpadding="0" cellspacing="0">
      <thead>
      <tr class="table_bg_7D91BD">

        <!-- チェックボックス -->
        <th width="3%" align="center" nowrap="nowrap">
         <span class="text_tlw">
           <input type="checkbox" name="cmn230AllCheckBottom2" value="1" onClick="chgCheckAll('cmn230AllCheckBottom2', 'cmn230userSidBottom2');chgCheckAllChange3('cmn230AllCheckBottom2', 'cmn230userSidBottom2');">
        </th>

        <!-- 社員/職員番号 -->
        <th width="40%" nowrap="nowrap">
          <logic:equal name="cmn230Form" property="cmn230SortBottomKey2" value="0">
            <logic:equal name="cmn230Form" property="cmn230SortBottomKbn2" value="1">
              <a href="#" onclick="clickSortTitle3(0);"><span class="text_tlw" style="font-size:75%"><gsmsg:write key="cmn.employee.staff.number" />▲</span></a>
            </logic:equal>
            <logic:notEqual name="cmn230Form" property="cmn230SortBottomKbn2" value="1">
              <a href="#" onclick="clickSortTitle3(0);"><span class="text_tlw" style="font-size:75%"><gsmsg:write key="cmn.employee.staff.number" />▼</span></a>
            </logic:notEqual>
          </logic:equal>
          <logic:notEqual name="cmn230Form" property="cmn230SortBottomKey2" value="0">
            <a href="#" onclick="clickSortTitle3(0);"><span class="text_tlw" style="font-size:75%"><gsmsg:write key="cmn.employee.staff.number" /></span></a>
          </logic:notEqual>
          <span class="text_tlw" style="font-size:50%">/</span>

        <!-- 氏名 -->
          <logic:equal name="cmn230Form" property="cmn230SortBottomKey2" value="1">
            <logic:equal name="cmn230Form" property="cmn230SortBottomKbn2" value="1">
              <a href="#" onclick="clickSortTitle3(1);"><span class="text_tlw" style="font-size:75%"><gsmsg:write key="cmn.name" />▲</span></a>
            </logic:equal>
            <logic:notEqual name="cmn230Form" property="cmn230SortBottomKbn2" value="1">
              <a href="#" onclick="clickSortTitle3(1);"><span class="text_tlw" style="font-size:75%"><gsmsg:write key="cmn.name" />▼</span></a>
            </logic:notEqual>
          </logic:equal>
          <logic:notEqual name="cmn230Form" property="cmn230SortBottomKey2" value="1">
            <a href="#" onclick="clickSortTitle3(1);"><span class="text_tlw" style="font-size:75%"><gsmsg:write key="cmn.name" /></span></a>
          </logic:notEqual>
          <span class="text_tlw" style="font-size:50%">/</span>
        <!-- 役職 -->
          <span class="text_tlw" style="font-size:75%"><gsmsg:write key="cmn.post" /></span>
        </th>

        <!-- 所属グループ -->
        <th width="55%" nowrap="nowrap" style="border-right:0px">
        <span class="text_tlw" style="font-size:75%"><gsmsg:write key="cmn.affiliation.group" /></span>
        </th>
        <th width="2%" nowrap="nowrap" style="width:5px; border-left:0px" >&nbsp;</th>
      </tr>
      </thead>

      <tbody>

      <logic:notEmpty name="cmn230Form" property="cmn230BottomUserList2">
      <logic:iterate id="selUserMdl2" name="cmn230Form" property="cmn230BottomUserList2" indexId="idx">

        <bean:define id="backclass" value="td_line_color" />
        <bean:define id="backpat" value="<%= String.valueOf((idx.intValue() % 2) + 5) %>" />
        <bean:define id="back" value="<%= String.valueOf(backclass) + String.valueOf(backpat) %>" />

        <tr class="td_type1 <%= String.valueOf(back) %>" style="font-size:90%;" id="<bean:write name="selUserMdl2" property="usrSid" />">
          <!-- チェックボックス -->
          <td>
            <% if (Integer.valueOf(backpat) == 5) { %>
              <html:multibox name="cmn230Form" property="cmn230userSidBottom2" onclick="backGroundSetting3(this, '5');">
                <bean:write name="selUserMdl2" property="usrSid" />
              </html:multibox>
            <% } else { %>
              <html:multibox name="cmn230Form" property="cmn230userSidBottom2" onclick="backGroundSetting3(this, '6');">
                <bean:write name="selUserMdl2" property="usrSid" />
              </html:multibox>
            <% } %>
          </td>
          <!-- 社員/職員番号 -->
          <td>
          <logic:notEmpty name="selUserMdl2" property="usiSyainNo">
          <span style="font-size:12px;"><bean:write name="selUserMdl2" property="usiSyainNo" /></span><br>
          </logic:notEmpty>
          <!-- 氏名 -->
            <a href="#" onclick="moveUserButton('230_abovearrow2', '<bean:write name="selUserMdl2" property="usrSid" />');">
              <span class="normal_link"><bean:write name="selUserMdl2" property="usiSei" />&nbsp;<bean:write name="selUserMdl2" property="usiMei" /></span>
            </a>
            <!-- 役職 -->
            <logic:notEmpty name="selUserMdl2" property="usiYakusyoku">
            <br><span style="font-size:12px;"><bean:write name="selUserMdl2" property="usiYakusyoku" /></span>
            </logic:notEmpty>
          </td>
          <!-- 所属グループ -->
          <td style="border-right:0px">
          <logic:notEmpty name="selUserMdl2" property="belongGrpList">
          <logic:iterate id="selGrpMdl2" name="selUserMdl2" property="belongGrpList" indexId="idx">
                <div style="font-size:12px;"><bean:write name="selGrpMdl2" property="groupName" /></div>
          </logic:iterate>
          </logic:notEmpty>
          </td>
          <td style="border-left:0px"></td>
        </tr>
      </logic:iterate>
      </logic:notEmpty>

      </tbody>
    </table>
    </div>

    </td>

    </tr>

    </tbody></table>

    <img src="../common/images/spacer.gif" alt="" width="10px" height="10px">

    <table width="99%" border="0" cellpadding="0" cellspacing="0">
    <tbody><tr>
    <td align="right">
      <input value="<gsmsg:write key="cmn.apply" />" class="btn_base1" onclick="application('<bean:write name="cmn230Form" property="svDomName" />', '<bean:write name="cmn230Form" property="svDomName2" />', '<bean:write name="cmn230Form" property="submitCmd" />', '<bean:write name="cmn230Form" property="selBoxName" />', '<bean:write name="cmn230Form" property="cmn230groupSid" />', '<bean:write name="cmn230Form" property="splitFlg" />', '<bean:write name="cmn230Form" property="cmdKbn" />');" type="button">
      <input value="<gsmsg:write key="cmn.cancel" />" class="btn_base1" onClick="window.close();" type="button">

    </td>
    </tr>

    </tbody></table>

  </td>
  </tr>

  </table>

</td>
</tr>

</table>

</logic:equal>

<logic:equal name="cmn230Form" property="cmn230TabMode" value="1">




    <table class="tl0" width="100%" border="0" cellpadding="5">
    <tbody>

    <tr>
      <td colspan="3" align="center">
      <table width="100%">
      <tr>
      <td class="table_bg_A5B4E1" align="center">
        <span class="text_bb1"><gsmsg:write key="cmn.grouplist" /></span>
      </td>
      </tr>
      </table>
      </td>
    </tr>

    <tr>
    <td colspan="3">

     <div class="fakeContainer3" style="margin-left:0px; margin-right:0px; border:solid 1px; border-color:#cccccc;">
     <table id="demoTable4" class="demoTable table_td_border2" width="100%" cellpadding="0" cellspacing="0">

      <thead>
      <tr class="table_bg_7D91BD">

        <!-- チェックボックス -->
        <th width="3%" align="center" nowrap="nowrap">
         <span class="text_tlw"><input type="checkbox" name="cmn230AllCheckTop" value="1" onClick="chgCheckAll('cmn230AllCheckTop', 'cmn230groupSidTop');chgCheckAllChange('cmn230AllCheckTop', 'cmn230groupSidTop');"></span>
        </th>

        <!-- グループID -->
        <th width="10%" nowrap="nowrap">
          <logic:equal name="cmn230Form" property="cmn230SortTopKeyGp" value="0">
            <logic:equal name="cmn230Form" property="cmn230SortTopKbnGp" value="1">
              <a href="#" onclick="clickSortTitle4(0);"><span class="text_tlw"><gsmsg:write key="cmn.group.id" />▲</span></a>
            </logic:equal>
            <logic:notEqual name="cmn230Form" property="cmn230SortTopKbnGp" value="1">
              <a href="#" onclick="clickSortTitle4(0);"><span class="text_tlw"><gsmsg:write key="cmn.group.id" />▼</span></a>
            </logic:notEqual>
          </logic:equal>
          <logic:notEqual name="cmn230Form" property="cmn230SortTopKeyGp" value="0">
            <a href="#" onclick="clickSortTitle4(0);"><span class="text_tlw"><gsmsg:write key="cmn.group.id" /></span></a>
          </logic:notEqual>
        </th>

        <!-- グループ名 -->
        <th width="0%" nowrap="nowrap" style="border-right:0px;">
          <logic:equal name="cmn230Form" property="cmn230SortTopKeyGp" value="1">
            <logic:equal name="cmn230Form" property="cmn230SortTopKbnGp" value="1">
              <a href="#" onclick="clickSortTitle4(1);"><span class="text_tlw"><gsmsg:write key="cmn.group.name" />▲</span></a>
            </logic:equal>
            <logic:notEqual name="cmn230Form" property="cmn230SortTopKbnGp" value="1">
              <a href="#" onclick="clickSortTitle4(1);"><span class="text_tlw"><gsmsg:write key="cmn.group.name" />▼</span></a>
            </logic:notEqual>
          </logic:equal>
          <logic:notEqual name="cmn230Form" property="cmn230SortTopKeyGp" value="1">
            <a href="#" onclick="clickSortTitle4(1);"><span class="text_tlw"><gsmsg:write key="cmn.group.name" /></span></a>
          </logic:notEqual>
        </th>

        <th width="5%" nowrap="nowrap" style="border-right:0px; border-left:0px">
        </th>
       <th  nowrap="nowrap" style="width:5px; border-left:0px;">&nbsp;</th>

      </tr>
      </thead>

      <tbody>

      <logic:notEmpty name="cmn230Form" property="cmn230TopGroupList">
      <logic:iterate id="groupMdl" name="cmn230Form" property="cmn230TopGroupList" indexId="idx">

        <bean:define id="backclass" value="td_line_color" />
        <bean:define id="backpat" value="<%= String.valueOf((idx.intValue() % 2) + 1) %>" />
        <bean:define id="back" value="<%= String.valueOf(backclass) + String.valueOf(backpat) %>" />

        <tr class="td_type1 <%= String.valueOf(back) %>" style="font-size:90%;" id="<bean:write name="groupMdl" property="groupSid" />">
          <!-- チェックボックス -->
          <td>
            <% if (Integer.valueOf(backpat) == 1) { %>
              <html:multibox name="cmn230Form" property="cmn230groupSidTop" onclick="backGroundSetting(this, '1');">
                <bean:write name="groupMdl" property="groupSid" />
              </html:multibox>
            <% } else { %>
              <html:multibox name="cmn230Form" property="cmn230groupSidTop" onclick="backGroundSetting(this, '2');">
                <bean:write name="groupMdl" property="groupSid" />
              </html:multibox>
            <% } %>
          </td>

          <!-- グループID -->
          <td nowrap="nowrap"><bean:write name="groupMdl" property="groupId" /></td>
          <!-- グループ名 -->
          <td style="border-right:0px;">
            <a href="javaScript:void(0);" onclick="moveGroupButton('230_belowarrowGp', '<bean:write name="groupMdl" property="groupSid" />');">
              <span class="normal_link"><bean:write name="groupMdl" property="groupName" /></span>
            </a>
          </td>

          <td nowrap="nowrap" style="border-right:0px; border-left:0px;"></td>
          <td style="border-left:0px"></td>
        </tr>

      </logic:iterate>
      </logic:notEmpty>
      <logic:empty name="cmn230Form" property="cmn230TopGroupList">
        <tr>
        <td colspan="5">
          <div align="center"><gsmsg:write key="cmn.nothing.group" /></div>
        </td>
        </tr>
      </logic:empty>

    </tbody>
    </table>
    </div>

    </td>
    </tr>

    <tr>
      <td>
        <div align="center">
          <input type="button" class="btn_base2add" value="<gsmsg:write key="cmn.add" />" name="adduserBtn" onClick="moveGroupAddButton('230_abovearrowGp_button');">
          <input type="button" class="btn_base2del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="moveGroupDelButton('230_belowarrowGp_button');">
        </div>
      </td>
      <td width="1px"></td>
      <td>
        <div align="center">
          <input type="button" class="btn_base2add" value="<gsmsg:write key="cmn.add" />" name="adduserBtn" onClick="moveGroupAddButton('230_abovearrowGp_button2');">
          <input type="button" class="btn_base2del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="moveGroupDelButton2('230_belowarrowGp_button2');">
        </div>
      </td>
    </tr>

    <tr>
      <td  align="center">
      <div class="table_bg_A5B4E1">
          <span class="text_bb1"><bean:write name="cmn230Form" property="cmn230LeftTitleName" /></span>
        </div>
      </td>
      <td width="1px"></td>
      <td align="center">
          <div class="table_bg_A5B4E1">
          <span class="text_bb1"><bean:write name="cmn230Form" property="cmn230RightTitleName" /></span>
        </div>
      </td>
    </tr>

    <tr>
    <td>

    <div class="fakeContainer4" style="border:solid 1px; border-color:#cccccc;">
    <table id="demoTable5" class="demoTable table_td_border2" width="100%" cellpadding="0" cellspacing="0">
      <thead>
      <tr class="table_bg_7D91BD">

        <!-- チェックボックス -->
        <th width="3%" align="center" nowrap="nowrap">
         <span class="text_tlw">
           <input type="checkbox" name="cmn230AllCheckBottom" value="1" onClick="chgCheckAll('cmn230AllCheckBottom', 'cmn230groupSidBottom');chgCheckAllChange2('cmn230AllCheckBottom', 'cmn230groupSidBottom');">
        </th>

        <!-- グループID -->
        <th width="10%" nowrap="nowrap">
          <logic:equal name="cmn230Form" property="cmn230SortBottomKeyGp" value="0">
            <logic:equal name="cmn230Form" property="cmn230SortBottomKbnGp" value="1">
              <a href="#" onclick="clickSortTitle5(0);"><span class="text_tlw"><gsmsg:write key="cmn.group.id" />▲</span></a>
            </logic:equal>
            <logic:notEqual name="cmn230Form" property="cmn230SortBottomKbnGp" value="1">
              <a href="#" onclick="clickSortTitle5(0);"><span class="text_tlw"><gsmsg:write key="cmn.group.id" />▼</span></a>
            </logic:notEqual>
          </logic:equal>
          <logic:notEqual name="cmn230Form" property="cmn230SortBottomKeyGp" value="0">
            <a href="#" onclick="clickSortTitle5(0);"><span class="text_tlw"><gsmsg:write key="cmn.group.id" /></span></a>
          </logic:notEqual>
        </th>

        <!-- グループ名 -->
        <th width="0%" nowrap="nowrap" style="border-right:0px;">
          <logic:equal name="cmn230Form" property="cmn230SortBottomKeyGp" value="1">
            <logic:equal name="cmn230Form" property="cmn230SortBottomKbnGp" value="1">
              <a href="#" onclick="clickSortTitle5(1);"><span class="text_tlw"><gsmsg:write key="cmn.group.name" />▲</span></a>
            </logic:equal>
            <logic:notEqual name="cmn230Form" property="cmn230SortBottomKbnGp" value="1">
              <a href="#" onclick="clickSortTitle5(1);"><span class="text_tlw"><gsmsg:write key="cmn.group.name" />▼</span></a>
            </logic:notEqual>
          </logic:equal>
          <logic:notEqual name="cmn230Form" property="cmn230SortBottomKeyGp" value="1">
            <a href="#" onclick="clickSortTitle5(1);"><span class="text_tlw"><gsmsg:write key="cmn.group.name" /></span></a>
          </logic:notEqual>
        </th>

        <th width="5%" nowrap="nowrap" style="border-right:0px; border-left:0px;">
        </th>
        <th  nowrap="nowrap" style="width:5px; border-left:0px" >&nbsp;</th>

      </tr>
      </thead>

      <tbody>

      <logic:notEmpty name="cmn230Form" property="cmn230BottomGroupList">
      <logic:iterate id="selGroupMdl" name="cmn230Form" property="cmn230BottomGroupList" indexId="idx">

        <bean:define id="backclass" value="td_line_color" />
        <bean:define id="backpat" value="<%= String.valueOf((idx.intValue() % 2) + 3) %>" />
        <bean:define id="back" value="<%= String.valueOf(backclass) + String.valueOf(backpat) %>" />

        <tr class="td_type1 <%= String.valueOf(back) %>" style="font-size:90%;" id="<bean:write name="selGroupMdl" property="groupSid" />">
          <!-- チェックボックス -->
          <td>
            <% if (Integer.valueOf(backpat) == 3) { %>
              <html:multibox name="cmn230Form" property="cmn230groupSidBottom" onclick="backGroundSetting2(this, '3');">
                <bean:write name="selGroupMdl" property="groupSid" />
              </html:multibox>
            <% } else { %>
              <html:multibox name="cmn230Form" property="cmn230groupSidBottom" onclick="backGroundSetting2(this, '4');">
                <bean:write name="selGroupMdl" property="groupSid" />
              </html:multibox>
            <% } %>
          </td>
          <!-- グループID -->
          <td nowrap="nowrap"><bean:write name="selGroupMdl" property="groupId" /></td>
          <!-- グループ名 -->
          <td style="border-right:0px;">
            <a href="#" onclick="moveGroupButton('230_abovearrowGp', '<bean:write name="selGroupMdl" property="groupSid" />');">
              <span class="normal_link"><bean:write name="selGroupMdl" property="groupName" /></span>
            </a>
          </td>

          <td nowrap="nowrap" style="border-right:0px; border-left:0px;"></td>
          <td style="border-left:0px"></td>
        </tr>
      </logic:iterate>
      </logic:notEmpty>

      </tbody>
    </table>
    </div>

    </td>


   <td width="0%"></td>


    <td>

    <div class="fakeContainer4" style="border:solid 1px; border-color:#cccccc;">
    <table id="demoTable6" class="demoTable table_td_border2" width="100%" cellpadding="0" cellspacing="0">
      <thead>
      <tr class="table_bg_7D91BD">

        <!-- チェックボックス -->
        <th width="3%" align="center" nowrap="nowrap">
         <span class="text_tlw">
           <input type="checkbox" name="cmn230AllCheckBottom2" value="1" onClick="chgCheckAll('cmn230AllCheckBottom2', 'cmn230groupSidBottom2');chgCheckAllChange3('cmn230AllCheckBottom2', 'cmn230groupSidBottom2');">
        </th>

        <!-- グループID -->
        <th width="10%" nowrap="nowrap">
          <logic:equal name="cmn230Form" property="cmn230SortBottomKeyGp2" value="0">
            <logic:equal name="cmn230Form" property="cmn230SortBottomKbnGp2" value="1">
              <a href="#" onclick="clickSortTitle6(0);"><span class="text_tlw"><gsmsg:write key="cmn.group.id" />▲</span></a>
            </logic:equal>
            <logic:notEqual name="cmn230Form" property="cmn230SortBottomKbnGp2" value="1">
              <a href="#" onclick="clickSortTitle6(0);"><span class="text_tlw"><gsmsg:write key="cmn.group.id" />▼</span></a>
            </logic:notEqual>
          </logic:equal>
          <logic:notEqual name="cmn230Form" property="cmn230SortBottomKeyGp2" value="0">
            <a href="#" onclick="clickSortTitle6(0);"><span class="text_tlw"><gsmsg:write key="cmn.group.id" /></span></a>
          </logic:notEqual>
        </th>

        <!-- グループ名 -->
        <th width="0%" nowrap="nowrap" style="border-right:0px;">
          <logic:equal name="cmn230Form" property="cmn230SortBottomKeyGp2" value="1">
            <logic:equal name="cmn230Form" property="cmn230SortBottomKbnGp2" value="1">
              <a href="#" onclick="clickSortTitle6(1);"><span class="text_tlw"><gsmsg:write key="cmn.group.name" />▲</span></a>
            </logic:equal>
            <logic:notEqual name="cmn230Form" property="cmn230SortBottomKbnGp2" value="1">
              <a href="#" onclick="clickSortTitle6(1);"><span class="text_tlw"><gsmsg:write key="cmn.group.name" />▼</span></a>
            </logic:notEqual>
          </logic:equal>
          <logic:notEqual name="cmn230Form" property="cmn230SortBottomKeyGp2" value="1">
            <a href="#" onclick="clickSortTitle6(1);"><span class="text_tlw"><gsmsg:write key="cmn.group.name" /></span></a>
          </logic:notEqual>
        </th>

        <th width="5%" nowrap="nowrap" style="border-right:0px; border-left:0px;">
        </th>
        <th  nowrap="nowrap" style="width:5px; border-left:0px" >&nbsp;</th>

      </tr>
      </thead>

      <tbody>

      <logic:notEmpty name="cmn230Form" property="cmn230BottomGroupList2">
      <logic:iterate id="selGroupMdl2" name="cmn230Form" property="cmn230BottomGroupList2" indexId="idx">

        <bean:define id="backclass" value="td_line_color" />
        <bean:define id="backpat" value="<%= String.valueOf((idx.intValue() % 2) + 5) %>" />
        <bean:define id="back" value="<%= String.valueOf(backclass) + String.valueOf(backpat) %>" />

        <tr class="td_type1 <%= String.valueOf(back) %>" style="font-size:90%;" id="<bean:write name="selGroupMdl2" property="groupSid" />">
          <!-- チェックボックス -->
          <td>
            <% if (Integer.valueOf(backpat) == 5) { %>
              <html:multibox name="cmn230Form" property="cmn230groupSidBottom2" onclick="backGroundSetting3(this, '5');">
                <bean:write name="selGroupMdl2" property="groupSid" />
              </html:multibox>
            <% } else { %>
              <html:multibox name="cmn230Form" property="cmn230groupSidBottom2" onclick="backGroundSetting3(this, '6');">
                <bean:write name="selGroupMdl2" property="groupSid" />
              </html:multibox>
            <% } %>
          </td>
          <!-- グループID-->
          <td nowrap="nowrap"><bean:write name="selGroupMdl2" property="groupId" /></td>
          <!-- グループ名 -->
          <td style="border-right:0px;">
            <a href="#" onclick="moveGroupButton('230_abovearrowGp2', '<bean:write name="selGroupMdl2" property="groupSid" />');">
              <span class="normal_link"><bean:write name="selGroupMdl2" property="groupName" /></span>
            </a>
          </td>

          <td nowrap="nowrap" style="border-right:0px; border-left:0px;"></td>
          <td style="border-left:0px"></td>
        </tr>
      </logic:iterate>
      </logic:notEmpty>

      </tbody>
    </table>
    </div>

    </td>

    </tr>

    </tbody></table>

    <img src="../common/images/spacer.gif" alt="" width="10px" height="10px">

    <table width="99%" border="0" cellpadding="0" cellspacing="0">
    <tbody><tr>
    <td align="right">
      <input value="<gsmsg:write key="cmn.apply" />" class="btn_base1" onclick="application('<bean:write name="cmn230Form" property="svDomName" />', '<bean:write name="cmn230Form" property="svDomName2" />', '<bean:write name="cmn230Form" property="submitCmd" />', '<bean:write name="cmn230Form" property="selBoxName" />', '<bean:write name="cmn230Form" property="cmn230groupSid" />', '<bean:write name="cmn230Form" property="splitFlg" />', '<bean:write name="cmn230Form" property="cmdKbn" />');" type="button">
      <input value="<gsmsg:write key="cmn.cancel" />" class="btn_base1" onClick="window.close();" type="button">

    </td>
    </tr>
    </tbody>
    </table>

</logic:equal>


<script language="JavaScript" src="../common/js/jquery.scrollTable.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript">
(function() {
    <logic:notEmpty name="cmn230Form" property="cmn230TopUserList">
      var mySt = new superTable("demoTable", {
          fixedCols : 0,
          headerRows : 1
      });
    </logic:notEmpty>
    <logic:notEmpty name="cmn230Form" property="cmn230BottomUserList">
      var mySt = new superTable("demoTable2", {
          fixedCols : 0,
         headerRows : 1
      });
    </logic:notEmpty>
    <logic:notEmpty name="cmn230Form" property="cmn230BottomUserList2">
      var mySt = new superTable("demoTable3", {
        fixedCols : 0,
        headerRows : 1
      });
   </logic:notEmpty>

   <logic:notEmpty name="cmn230Form" property="cmn230TopGroupList">
    var mySt = new superTable("demoTable4", {
      fixedCols : 0,
      headerRows : 1
    });
   </logic:notEmpty>
   <logic:notEmpty name="cmn230Form" property="cmn230BottomGroupList">
    var mySt = new superTable("demoTable5", {
      fixedCols : 0,
      headerRows : 1
    });
   </logic:notEmpty>
   <logic:notEmpty name="cmn230Form" property="cmn230BottomGroupList2">
    var mySt = new superTable("demoTable6", {
      fixedCols : 0,
      headerRows : 1
    });
   </logic:notEmpty>
})();
</script>

<div id="dialog-error" title="<gsmsg:write key="cmn.warning" />" style="display:none">
 <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
   <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<gsmsg:write key="cmn.sel.add.user" />
 </p>
</div>

<div id="dialog-error2" title="<gsmsg:write key="cmn.warning" />" style="display:none">
 <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
   <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<gsmsg:write key="cmn.sel.del.user" />
 </p>
</div>

<div id="dialog-error3" title="<gsmsg:write key="cmn.warning" />" style="display:none">
 <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
   <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<gsmsg:write key="cmn.search.condition" />
 </p>
</div>

<div id="dialog-error4" title="<gsmsg:write key="cmn.warning" />" style="display:none">
 <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
   <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<gsmsg:write key="cmn.sel.add.group" />
 </p>
</div>

<div id="dialog-error5" title="<gsmsg:write key="cmn.warning" />" style="display:none">
 <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
   <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<gsmsg:write key="cmn.sel.del.group" />
 </p>
</div>

</html:form>

</body>

</html:html>