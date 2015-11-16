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
<script language="JavaScript" src="../common/js/cmn220.js?<%= GSConst.VERSION_PARAM %>"></script>
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
    width: 100%;
    height: 195px;
    overflow: hidden;
}

.fakeContainer2 {
    margin: 0 0 0px;
    width: 600px;
    height: 195px;
    overflow: hidden;
}

.sData{
    overflow-x:hidden!important;
}

</style>
</head>

<body class="body_03" style="width:100%">
<html:form action="/common/cmn220">

<logic:equal name="cmn220Form" property="cmn220TabMode" value="0">
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
<html:hidden property="submitCmd" />
<html:hidden property="parentFormName" />
<html:hidden property="paramBtn" />
<html:hidden property="selBoxName" />
<html:hidden property="moveUsers" />
<html:hidden property="moveGroups" />
<html:hidden property="moveGroupSid" />
<html:hidden property="admGpFlg" />
<html:hidden property="splitFlg" />
<html:hidden property="cmdKbn" />
<html:hidden property="selGrpFlg" />
<html:hidden property="cmn220SearchFlg" />
<html:hidden property="cmn220groupSid" />
<html:hidden property="cmn220SortTopKey" />
<html:hidden property="cmn220SortTopKbn" />
<html:hidden property="cmn220SortBottomKey" />
<html:hidden property="cmn220SortBottomKbn" />
<html:hidden property="cmn220SortTopKeyGp" />
<html:hidden property="cmn220SortTopKbnGp" />
<html:hidden property="cmn220SortBottomKeyGp" />
<html:hidden property="cmn220SortBottomKbnGp" />
<html:hidden property="cmn220TabFlg" />
<html:hidden property="cmn220TabMode" />
<html:hidden property="cmn220banUserSid" />
<logic:iterate id="sid" name="cmn220Form" property="cmn220banUserSid" scope="request">
    <input type="hidden" name="cmn220banUserSid" value="<bean:write name="sid" />">
</logic:iterate>
<logic:iterate id="sid" name="cmn220Form" property="cmn220banGroupSid" scope="request">
    <input type="hidden" name="cmn220banGroupSid" value="<bean:write name="sid" />">
</logic:iterate>
<logic:iterate id="sid" name="cmn220Form" property="cmn220disableGroupSid" scope="request">
    <input type="hidden" name="cmn220disableGroupSid" value="<bean:write name="sid" />">
</logic:iterate>


<logic:notEmpty name="cmn220Form" property="cmn220userSid" scope="request">
<logic:iterate id="ulBean" name="cmn220Form" property="cmn220userSid" scope="request">
<input type="hidden" name="cmn220userSid" value="<bean:write name="ulBean" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="cmn220Form" property="cmn220groupSidadd" scope="request">
<logic:iterate id="ulBean2" name="cmn220Form" property="cmn220groupSidadd" scope="request">
<input type="hidden" name="cmn220groupSidadd" value="<bean:write name="ulBean2" />">
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



<logic:equal name="cmn220Form" property="cmn220TabFlg" value="1">

  <logic:equal name="cmn220Form" property="cmn220TabMode" value="0">
    <table class="tab_table" width="100%" border="0" cellpadding="0" cellspacing="0" height="34px">
    <tbody>
      <tr>
        <td class="tab_bg_image_1_on" width="100px" align="left" nowrap="nowrap">
          <div class="tab_text_area">
            <a href="javascript:changeTab('220_tabUser');"><gsmsg:write key="cmn.user" /></a>
          </div>
        </td>
        <td class="tab_bg_image_1_off" width="107px" align="left" nowrap="nowrap">
          <div class="tab_text_area_right">
            <a href="javascript:changeTab('220_tabGroup');"><gsmsg:write key="cmn.group" /></a>
          </div>
        </td>
        <td class="tab_bg_underbar" width="100%" nowrap="nowrap">&nbsp;</td>
      </tr>
    </tbody>
    </table>

   </logic:equal>

   <logic:equal name="cmn220Form" property="cmn220TabMode" value="1">
      <table class="tab_table" width="100%" border="0" cellpadding="0" cellspacing="0" height="34px">
      <tbody>
        <tr>
          <td class="tab_bg_image_1_off" width="100px" align="left" nowrap="nowrap">
            <div class="tab_text_area_right">
              <a href="javascript:changeTab('220_tabUser');"><gsmsg:write key="cmn.user" /></a>
            </div>
          </td>
          <td class="tab_bg_image_1_on" width="107px" align="left" nowrap="nowrap">
            <div class="tab_text_area">
              <a href="javascript:changeTab('220_tabGroup');"><gsmsg:write key="cmn.group" /></a>
            </div>
          </td>
          <td class="tab_bg_underbar" valign="top" width="100%" align="right" nowrap="nowrap"></td>
        </tr>
      </tbody>
      </table>
   </logic:equal>
   <img src="../common/images/damy.gif" alt="" width="10" border="0" height="10">
</logic:equal>




<logic:equal name="cmn220Form" property="cmn220TabMode" value="0">

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

          <logic:notEmpty name="cmn220Form" property="myGroupList">
            <logic:iterate id="mgl" name="cmn220Form" property="myGroupList" indexId="idx">
                <li>
                  <a href="javascript:void(0);" onClick="return changeGroup('<bean:write name="mgl" property="value" />');">
                    <bean:write name="mgl" property="label" />
                  </a>
                </li>
            </logic:iterate>
          </logic:notEmpty>

          <logic:iterate id="mdl" name="cmn220Form" property="groupList" indexId="idx">
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

  <td width="80%" valign="top">


    <div class="textfield">
      <label for="field_id"><gsmsg:write key="cmn.search.item" /></label>

      <html:text name="cmn220Form" style="width:395px;" maxlength="50" property="cmn220SearchStr" styleClass="text_base" styleId="field_id" />
      <input value="<gsmsg:write key="cmn.search" />" class="btn_base1" onclick="searchUser('searchUsr');" type="button">
    </div>
    <table class="tl0" width="100%" border="0" cellpadding="5">
    <tbody>
    <tr>
      <td>
      <table width="100%">
      <tr>
      <td  class="table_bg_A5B4E1" align="center">
        <span class="text_bb1"><bean:write name="cmn220Form" property="cmn220DspGrpName"/></span>
      </td>
      </tr>
      </table>
      </td>
    </tr>

    <tr>
    <td>

     <div class="fakeContainer" style="border:solid 1px; border-color:#cccccc;">
     <table id="demoTable" class="demoTable table_td_border2" width="100%" cellpadding="0" cellspacing="0">

      <thead>
      <tr class="table_bg_7D91BD">

        <!-- チェックボックス -->
        <th width="3%" align="center" nowrap="nowrap">
         <span class="text_tlw"><input type="checkbox" name="cmn220AllCheckTop" value="1" onClick="chgCheckAll('cmn220AllCheckTop', 'cmn220userSidTop');chgCheckAllChange('cmn220AllCheckTop', 'cmn220userSidTop');"></span>
        </th>

        <!-- 社員/職員番号 -->
        <th width="40%" nowrap="nowrap">
          <logic:equal name="cmn220Form" property="cmn220SortTopKey" value="0">
            <logic:equal name="cmn220Form" property="cmn220SortTopKbn" value="1">
              <a href="#" onclick="clickSortTitle(0);"><span class="text_tlw"><gsmsg:write key="cmn.employee.staff.number" />▲</span></a>
            </logic:equal>
            <logic:notEqual name="cmn220Form" property="cmn220SortTopKbn" value="1">
              <a href="#" onclick="clickSortTitle(0);"><span class="text_tlw"><gsmsg:write key="cmn.employee.staff.number" />▼</span></a>
            </logic:notEqual>
          </logic:equal>
          <logic:notEqual name="cmn220Form" property="cmn220SortTopKey" value="0">
            <a href="#" onclick="clickSortTitle(0);"><span class="text_tlw"><gsmsg:write key="cmn.employee.staff.number" /></span></a>
          </logic:notEqual>
          <span class="text_tlw">/</span>
        <!-- 氏名 -->
          <logic:equal name="cmn220Form" property="cmn220SortTopKey" value="1">
            <logic:equal name="cmn220Form" property="cmn220SortTopKbn" value="1">
              <a href="#" onclick="clickSortTitle(1);"><span class="text_tlw"><gsmsg:write key="cmn.name" />▲</span></a>
            </logic:equal>
            <logic:notEqual name="cmn220Form" property="cmn220SortTopKbn" value="1">
              <a href="#" onclick="clickSortTitle(1);"><span class="text_tlw"><gsmsg:write key="cmn.name" />▼</span></a>
            </logic:notEqual>
          </logic:equal>
          <logic:notEqual name="cmn220Form" property="cmn220SortTopKey" value="1">
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

      <logic:notEmpty name="cmn220Form" property="cmn220TopUserList">
      <logic:iterate id="userMdl" name="cmn220Form" property="cmn220TopUserList" indexId="idx">

        <bean:define id="backclass" value="td_line_color" />
        <bean:define id="backpat" value="<%= String.valueOf((idx.intValue() % 2) + 1) %>" />
        <bean:define id="back" value="<%= String.valueOf(backclass) + String.valueOf(backpat) %>" />

        <tr class="td_type1 <%= String.valueOf(back) %>" style="font-size:90%;" id="<bean:write name="userMdl" property="usrSid" />">
          <!-- チェックボックス -->
          <td>
            <% if (Integer.valueOf(backpat) == 1) { %>
              <html:multibox name="cmn220Form" property="cmn220userSidTop" onclick="backGroundSetting(this, '1');">
                <bean:write name="userMdl" property="usrSid" />
              </html:multibox>
            <% } else { %>
              <html:multibox name="cmn220Form" property="cmn220userSidTop" onclick="backGroundSetting(this, '2');">
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
            <a href="javaScript:void(0);" onclick="moveUserButton('220_belowarrow', '<bean:write name="userMdl" property="usrSid" />');">
              <span class="normal_link"><bean:write name="userMdl" property="usiSei" />&nbsp;<bean:write name="userMdl" property="usiMei" /></span>
            </a>
          <!-- 役職 -->
          <logic:notEmpty name="userMdl" property="usiYakusyoku">
          <br><span style="font-size:12px;"><bean:write name="userMdl" property="usiYakusyoku" /></span>
          </logic:notEmpty>
          </td>
          <!-- 所属グループ -->
          <td style="border-right:0px">
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
      <logic:empty name="cmn220Form" property="cmn220TopUserList">
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
          <input type="button" class="btn_base2add" value="<gsmsg:write key="cmn.add" />" name="adduserBtn" onClick="moveUserAddButton('220_abovearrow_button');">
          <input type="button" class="btn_base2del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="moveUserDelButton('220_belowarrow_button');">
        </div>
      </td>
    </tr>

    <tr>
      <td>
      <table width="100%">
      <tr>
      <td class="table_bg_A5B4E1" align="center">
        <span class="text_bb1"><gsmsg:write key="cmn.sel.user" /></span>
      </td>
      </tr>
      </table>
      </td>
    </tr>

    <tr>
    <td>

    <div class="fakeContainer" style="border:solid 1px; border-color:#9a9a9a;">
    <table id="demoTable2" class="demoTable table_td_border2" width="100%" cellpadding="0" cellspacing="0">
      <thead>
      <tr class="table_bg_7D91BD">

        <!-- チェックボックス -->
        <th width="3%" align="center" nowrap="nowrap">
         <span class="text_tlw">
           <input type="checkbox" name="cmn220AllCheckBottom" value="1" onClick="chgCheckAll('cmn220AllCheckBottom', 'cmn220userSidBottom');chgCheckAllChange2('cmn220AllCheckBottom', 'cmn220userSidBottom');">
        </th>

        <!-- 社員/職員番号 -->
        <th width="40%" nowrap="nowrap">
          <logic:equal name="cmn220Form" property="cmn220SortBottomKey" value="0">
            <logic:equal name="cmn220Form" property="cmn220SortBottomKbn" value="1">
              <a href="#" onclick="clickSortTitle2(0);"><span class="text_tlw"><gsmsg:write key="cmn.employee.staff.number" />▲</span></a>
            </logic:equal>
            <logic:notEqual name="cmn220Form" property="cmn220SortBottomKbn" value="1">
              <a href="#" onclick="clickSortTitle2(0);"><span class="text_tlw"><gsmsg:write key="cmn.employee.staff.number" />▼</span></a>
            </logic:notEqual>
          </logic:equal>
          <logic:notEqual name="cmn220Form" property="cmn220SortBottomKey" value="0">
            <a href="#" onclick="clickSortTitle2(0);"><span class="text_tlw"><gsmsg:write key="cmn.employee.staff.number" /></span></a>
          </logic:notEqual>
          <span class="text_tlw">/</span>
        <!-- 氏名 -->
          <logic:equal name="cmn220Form" property="cmn220SortBottomKey" value="1">
            <logic:equal name="cmn220Form" property="cmn220SortBottomKbn" value="1">
              <a href="#" onclick="clickSortTitle2(1);"><span class="text_tlw"><gsmsg:write key="cmn.name" />▲</span></a>
            </logic:equal>
            <logic:notEqual name="cmn220Form" property="cmn220SortBottomKbn" value="1">
              <a href="#" onclick="clickSortTitle2(1);"><span class="text_tlw"><gsmsg:write key="cmn.name" />▼</span></a>
            </logic:notEqual>
          </logic:equal>
          <logic:notEqual name="cmn220Form" property="cmn220SortBottomKey" value="1">
            <a href="#" onclick="clickSortTitle2(1);"><span class="text_tlw"><gsmsg:write key="cmn.name" /></span></a>
          </logic:notEqual>

          <span class="text_tlw">/</span>
        <!-- 役職 -->
        <span class="text_tlw"><gsmsg:write key="cmn.post" /></span>
        </th>


        <!-- 所属グループ -->
        <th width="55%" nowrap="nowrap" style="border-right:0px">
          <span class="text_tlw"><gsmsg:write key="cmn.affiliation.group" /></span>
        </th>

        <th width="2%" nowrap="nowrap" style="width:5px; border-left:0px" >&nbsp;</th>

      </tr>
      </thead>

      <tbody>

      <logic:notEmpty name="cmn220Form" property="cmn220BottomUserList">
      <logic:iterate id="selUserMdl" name="cmn220Form" property="cmn220BottomUserList" indexId="idx">

        <bean:define id="backclass" value="td_line_color" />
        <bean:define id="backpat" value="<%= String.valueOf((idx.intValue() % 2) + 3) %>" />
        <bean:define id="back" value="<%= String.valueOf(backclass) + String.valueOf(backpat) %>" />

        <tr class="td_type1 <%= String.valueOf(back) %>" style="font-size:90%;" id="<bean:write name="selUserMdl" property="usrSid" />">
          <!-- チェックボックス -->
          <td>
            <% if (Integer.valueOf(backpat) == 3) { %>
              <html:multibox name="cmn220Form" property="cmn220userSidBottom" onclick="backGroundSetting2(this, '3');">
                <bean:write name="selUserMdl" property="usrSid" />
              </html:multibox>
            <% } else { %>
              <html:multibox name="cmn220Form" property="cmn220userSidBottom" onclick="backGroundSetting2(this, '4');">
                <bean:write name="selUserMdl" property="usrSid" />
              </html:multibox>
            <% } %>
          </td>
          <td>
          <!-- 社員/職員番号 -->
          <logic:notEmpty name="selUserMdl" property="usiSyainNo">
          <span style="font-size:12px;"><bean:write name="selUserMdl" property="usiSyainNo" /></span><br>
          </logic:notEmpty>
          <!-- 氏名 -->
            <a href="#" onclick="moveUserButton('220_abovearrow', '<bean:write name="selUserMdl" property="usrSid" />');">
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
                <div><bean:write name="selGrpMdl" property="groupName" /></div>
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

    <table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tbody><tr>
    <td align="right">
      <logic:equal name="cmn220Form" property="paramBtn" value="">
        <input value="<gsmsg:write key="cmn.apply" />" class="btn_base1" onclick="application('<bean:write name="cmn220Form" property="svDomName" />', '<bean:write name="cmn220Form" property="submitCmd" />', '<bean:write name="cmn220Form" property="selBoxName" />', '<bean:write name="cmn220Form" property="cmn220groupSid" />', '<bean:write name="cmn220Form" property="splitFlg" />', '<bean:write name="cmn220Form" property="cmdKbn" />');" type="button">
      </logic:equal>
      <logic:notEqual name="cmn220Form" property="paramBtn" value="">
        <input value="<gsmsg:write key="cmn.apply" />" class="btn_base1" onclick="application_no_submit('<bean:write name="cmn220Form" property="svDomName" />', '<bean:write name="cmn220Form" property="submitCmd" />', '<bean:write name="cmn220Form" property="selBoxName" />', '<bean:write name="cmn220Form" property="cmn220groupSid" />', '<bean:write name="cmn220Form" property="splitFlg" />', '<bean:write name="cmn220Form" property="cmdKbn" />', '<bean:write name="cmn220Form" property="parentFormName" />', '<bean:write name="cmn220Form" property="paramBtn" />');" type="button">
      </logic:notEqual>
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


<logic:equal name="cmn220Form" property="cmn220TabMode" value="1">

    <table class="tl0" width="100%" border="0" cellpadding="5">
    <tbody>
    <tr>
      <td class="table_bg_A5B4E1" align="center">
        <span class="text_bb1"><gsmsg:write key="cmn.grouplist" /></span>
      </td>
    </tr>

    <tr>
    <td>

     <div class="fakeContainer" style="border:solid 1px; border-color:#9a9a9a;">
     <table id="demoTable3" class="demoTable table_td_border2" width="100%" cellpadding="0" cellspacing="0">

      <thead>
      <tr class="table_bg_7D91BD">

        <!-- チェックボックス -->
        <th width="3%" align="center" nowrap="nowrap">
         <span class="text_tlw"><input type="checkbox" name="cmn220AllCheckTop" value="1" onClick="chgCheckAll('cmn220AllCheckTop', 'cmn220groupSidTop');chgCheckAllChange('cmn220AllCheckTop', 'cmn220groupSidTop');"></span>
        </th>

        <!-- グループID -->
        <th width="10%" nowrap="nowrap">
          <logic:equal name="cmn220Form" property="cmn220SortTopKeyGp" value="0">
            <logic:equal name="cmn220Form" property="cmn220SortTopKbnGp" value="1">
              <a href="#" onclick="clickSortTitle3(0);"><span class="text_tlw"><gsmsg:write key="cmn.group.id" />▲</span></a>
            </logic:equal>
            <logic:notEqual name="cmn220Form" property="cmn220SortTopKbnGp" value="1">
              <a href="#" onclick="clickSortTitle3(0);"><span class="text_tlw"><gsmsg:write key="cmn.group.id" />▼</span></a>
            </logic:notEqual>
          </logic:equal>
          <logic:notEqual name="cmn220Form" property="cmn220SortTopKeyGp" value="0">
            <a href="#" onclick="clickSortTitle3(0);"><span class="text_tlw"><gsmsg:write key="cmn.group.id" /></span></a>
          </logic:notEqual>
        </th>

        <!-- グループ名 -->
        <th width="0%" nowrap="nowrap" style="border-right:0px;">
          <logic:equal name="cmn220Form" property="cmn220SortTopKeyGp" value="1">
            <logic:equal name="cmn220Form" property="cmn220SortTopKbnGp" value="1">
              <a href="#" onclick="clickSortTitle3(1);"><span class="text_tlw"><gsmsg:write key="cmn.group.name" />▲</span></a>
            </logic:equal>
            <logic:notEqual name="cmn220Form" property="cmn220SortTopKbnGp" value="1">
              <a href="#" onclick="clickSortTitle3(1);"><span class="text_tlw"><gsmsg:write key="cmn.group.name" />▼</span></a>
            </logic:notEqual>
          </logic:equal>
          <logic:notEqual name="cmn220Form" property="cmn220SortTopKeyGp" value="1">
            <a href="#" onclick="clickSortTitle3(1);"><span class="text_tlw"><gsmsg:write key="cmn.group.name" /></span></a>
          </logic:notEqual>
        </th>


        <th width="5%" nowrap="nowrap" style="border-right:0px; border-left:0px;"></th>
        <th  nowrap="nowrap" style="width:5px; border-left:0px;">&nbsp;</th>

      </tr>
      </thead>

      <tbody>

      <logic:notEmpty name="cmn220Form" property="cmn220TopGroupList">
      <logic:iterate id="grpMdl" name="cmn220Form" property="cmn220TopGroupList" indexId="idx">

        <bean:define id="backclass" value="td_line_color" />
        <bean:define id="backpat" value="<%= String.valueOf((idx.intValue() % 2) + 1) %>" />
        <bean:define id="back" value="<%= String.valueOf(backclass) + String.valueOf(backpat) %>" />

        <tr class="td_type1 <%= String.valueOf(back) %>" style="font-size:90%;" id="<bean:write name="grpMdl" property="groupSid" />">
          <!-- チェックボックス -->
          <td>
            <% if (Integer.valueOf(backpat) == 1) { %>
              <html:multibox name="cmn220Form" property="cmn220groupSidTop" onclick="backGroundSetting(this, '1');">
                <bean:write name="grpMdl" property="groupSid" />
              </html:multibox>
            <% } else { %>
              <html:multibox name="cmn220Form" property="cmn220groupSidTop" onclick="backGroundSetting(this, '2');">
                <bean:write name="grpMdl" property="groupSid" />
              </html:multibox>
            <% } %>
          </td>

          <!-- グループID -->
          <td nowrap="nowrap"><bean:write name="grpMdl" property="groupId" /></td>
          <!-- グループ名 -->
          <td style="border-right:0px">
            <a href="javaScript:void(0);" onclick="moveGroupButton('220_belowarrowGp', '<bean:write name="grpMdl" property="groupSid" />');">
              <span class="normal_link"><bean:write name="grpMdl" property="groupName" /></span>
            </a>
          </td>

          <td nowrap="nowrap" style="border-right:0px; border-left:0px"></td>
          <td style="border-left:0px"></td>
        </tr>

      </logic:iterate>
      </logic:notEmpty>
      <logic:empty name="cmn220Form" property="cmn220TopGroupList">
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
          <input type="button" class="btn_base2add" value="<gsmsg:write key="cmn.add" />" name="adduserBtn" onClick="moveGroupAddButton('220_abovearrowGp_button');">
          <input type="button" class="btn_base2del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="moveGroupDelButton('220_belowarrowGp_button');">
        </div>
      </td>
    </tr>

    <tr>
      <td class="table_bg_A5B4E1" align="center">
        <span class="text_bb1"><gsmsg:write key="cmn.sel.group" /></span>
      </td>
    </tr>

    <tr>
    <td>

    <div class="fakeContainer" style="border:solid 1px; border-color:#9a9a9a;">
    <table id="demoTable4" class="demoTable table_td_border2" width="100%" cellpadding="0" cellspacing="0">
      <thead>
      <tr class="table_bg_7D91BD">

        <!-- チェックボックス -->
        <th width="3%" align="center" nowrap="nowrap">
         <span class="text_tlw">
           <input type="checkbox" name="cmn220AllCheckBottom" value="1" onClick="chgCheckAll('cmn220AllCheckBottom', 'cmn220groupSidBottom');chgCheckAllChange2('cmn220AllCheckBottom', 'cmn220groupSidBottom');">
        </th>

        <!-- グループID -->
        <th width="10%" nowrap="nowrap">
          <logic:equal name="cmn220Form" property="cmn220SortBottomKeyGp" value="0">
            <logic:equal name="cmn220Form" property="cmn220SortBottomKbnGp" value="1">
              <a href="#" onclick="clickSortTitle4(0);"><span class="text_tlw"><gsmsg:write key="cmn.group.id" />▲</span></a>
            </logic:equal>
            <logic:notEqual name="cmn220Form" property="cmn220SortBottomKbnGp" value="1">
              <a href="#" onclick="clickSortTitle4(0);"><span class="text_tlw"><gsmsg:write key="cmn.group.id" />▼</span></a>
            </logic:notEqual>
          </logic:equal>
          <logic:notEqual name="cmn220Form" property="cmn220SortBottomKeyGp" value="0">
            <a href="#" onclick="clickSortTitle4(0);"><span class="text_tlw"><gsmsg:write key="cmn.group.id" /></span></a>
          </logic:notEqual>
        </th>

        <!-- グループ名 -->
        <th width="0%" nowrap="nowrap" style="border-right:0px;">
          <logic:equal name="cmn220Form" property="cmn220SortBottomKeyGp" value="1">
            <logic:equal name="cmn220Form" property="cmn220SortBottomKbnGp" value="1">
              <a href="#" onclick="clickSortTitle4(1);"><span class="text_tlw"><gsmsg:write key="cmn.group.name" />▲</span></a>
            </logic:equal>
            <logic:notEqual name="cmn220Form" property="cmn220SortBottomKbnGp" value="1">
              <a href="#" onclick="clickSortTitle4(1);"><span class="text_tlw"><gsmsg:write key="cmn.group.name" />▼</span></a>
            </logic:notEqual>
          </logic:equal>
          <logic:notEqual name="cmn220Form" property="cmn220SortBottomKeyGp" value="1">
            <a href="#" onclick="clickSortTitle4(1);"><span class="text_tlw"><gsmsg:write key="cmn.group.name" /></span></a>
          </logic:notEqual>
        </th>

        <th width="5%" nowrap="nowrap" style="border-right:0px; border-left:0px;"></th>
        <th  nowrap="nowrap" style="width:5px; border-left:0px;">&nbsp;</th>

      </tr>
      </thead>

      <tbody>

      <logic:notEmpty name="cmn220Form" property="cmn220BottomGroupList">
      <logic:iterate id="selGrpMdl" name="cmn220Form" property="cmn220BottomGroupList" indexId="idx">

        <bean:define id="backclass" value="td_line_color" />
        <bean:define id="backpat" value="<%= String.valueOf((idx.intValue() % 2) + 3) %>" />
        <bean:define id="back" value="<%= String.valueOf(backclass) + String.valueOf(backpat) %>" />

        <tr class="td_type1 <%= String.valueOf(back) %>" style="font-size:90%;" id="<bean:write name="selGrpMdl" property="groupSid" />">
          <!-- チェックボックス -->
          <td>
            <% if (Integer.valueOf(backpat) == 3) { %>
              <html:multibox name="cmn220Form" property="cmn220groupSidBottom" onclick="backGroundSetting2(this, '3');">
                <bean:write name="selGrpMdl" property="groupSid" />
              </html:multibox>
            <% } else { %>
              <html:multibox name="cmn220Form" property="cmn220groupSidBottom" onclick="backGroundSetting2(this, '4');">
                <bean:write name="selGrpMdl" property="groupSid" />
              </html:multibox>
            <% } %>
          </td>
          <!-- グループID -->
          <td nowrap="nowrap"><bean:write name="selGrpMdl" property="groupId" /></td>
          <!-- グループ名 -->
          <td style="border-right:0px">
            <a href="#" onclick="moveGroupButton('220_abovearrowGp', '<bean:write name="selGrpMdl" property="groupSid" />');">
              <span class="normal_link"><bean:write name="selGrpMdl" property="groupName" /></span>
            </a>
          </td>

          <td nowrap="nowrap" style="border-right:0px; border-left:0px"></td>
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

    <table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tbody><tr>
    <td align="right">
      <input value="<gsmsg:write key="cmn.apply" />" class="btn_base1" onclick="application('<bean:write name="cmn220Form" property="svDomName" />', '<bean:write name="cmn220Form" property="submitCmd" />', '<bean:write name="cmn220Form" property="selBoxName" />', '<bean:write name="cmn220Form" property="cmn220groupSid" />', '<bean:write name="cmn220Form" property="splitFlg" />', '<bean:write name="cmn220Form" property="cmdKbn" />');" type="button">
      <input value="<gsmsg:write key="cmn.cancel" />" class="btn_base1" onClick="window.close();" type="button">
    </td>
    </tr>

    </tbody></table>

</logic:equal>

<script language="JavaScript" src="../common/js/jquery.scrollTable.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript">
(function() {
    <logic:notEmpty name="cmn220Form" property="cmn220TopUserList">
      var mySt = new superTable("demoTable", {
          fixedCols : 0,
          headerRows : 1
      });
    </logic:notEmpty>
    <logic:notEmpty name="cmn220Form" property="cmn220BottomUserList">
      var mySt = new superTable("demoTable2", {
          fixedCols : 0,
         headerRows : 1
      });
    </logic:notEmpty>

    <logic:notEmpty name="cmn220Form" property="cmn220TopGroupList">
      var mySt = new superTable("demoTable3", {
          fixedCols : 0,
          headerRows : 1
       });
    </logic:notEmpty>
    <logic:notEmpty name="cmn220Form" property="cmn220BottomGroupList">
       var mySt = new superTable("demoTable4", {
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