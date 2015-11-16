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
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn210.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../user/js/group2.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery.cookie.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery.treeview.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript">
  $(function() {
      $("#tree").treeview({
          collapsed: true,
          animated: "fast",
          control:"#sidetreecontrol",
          persist: "location"
      });
  })
</script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel="stylesheet" href="../common/css/jquery.treeview.css?<%= GSConst.VERSION_PARAM %>">
</head>

<body class="body_03" onload="checkedGroup();">
<html:form action="/common/cmn210">

<table width="100%">
<tr>
<td width="100%" align="center">

  <table width="45%">
  <tr>
  <td align="left">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../user/images/header_group_01.gif" border="0" alt="<gsmsg:write key="cmn.group" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="wml.wml040.04" /></span></td>
    <td width="0%" class="header_white_bg_text">&nbsp;</td>
    <td width="100%" class="header_white_bg">
      <input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_close_n1" onClick="window.close();">
    </td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="530" height="" cellpadding="0" cellspacing="0">
    <tr>
    <td width="100%" class="tbl_gray">
        <table width="100%" class="user_search" height="400px">
        <tr align="left">
        <td style="vertical-align: top!important;">

          <div id="sidetree" style="margin-top:0px!important;margin-left:30px!important;">
          <div id="sidetreecontrol"><a href="?#"><gsmsg:write key="cmn.all.close" /></a> | <a href="?#"><gsmsg:write key="cmn.all.open" /></a></div>

          <% int pastVal = 0; %>

          <ul id="tree">

          <logic:notEmpty name="cmn210Form" property="myGroupList">
            <logic:iterate id="mgl" name="cmn210Form" property="myGroupList" indexId="idx">
                <li>
                  <a href="<bean:write name="cmn210Form" property="scriptStr" />.value=('<bean:write name="mgl" property="value" />');<bean:write name="cmn210Form" property="scriptStr2" />">
                    <bean:write name="mgl" property="label" />
                  </a>
                </li>
            </logic:iterate>
          </logic:notEmpty>

          <logic:iterate id="mdl" name="cmn210Form" property="groupList" indexId="idx">
            <logic:iterate id="disabled" name="cmn210Form" property="groupDisabledKbnList" offset="idx" length="1">

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
                <logic:equal name="disabled" value="1">
                    <bean:write name="mdl" property="groupName" />
                </logic:equal>
                <logic:notEqual name="disabled" value="1">
                <a href="<bean:write name="cmn210Form" property="scriptStr" />.value=('<bean:write name="mdl" property="groupSid" />');<bean:write name="cmn210Form" property="scriptStr2" />">
                  <bean:write name="mdl" property="groupName" />
                </a>
                </logic:notEqual>
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
                <logic:equal name="disabled" value="1">
                    <bean:write name="mdl" property="groupName" />
                </logic:equal>
                <logic:notEqual name="disabled" value="1">
                    <a href="<bean:write name="cmn210Form" property="scriptStr" />.value=('<bean:write name="mdl" property="groupSid" />');<bean:write name="cmn210Form" property="scriptStr2" />">
                      <bean:write name="mdl" property="groupName" />
                    </a>
                </logic:notEqual>

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
                <logic:equal name="disabled" value="1">
                    <bean:write name="mdl" property="groupName" />
                </logic:equal>
                <logic:notEqual name="disabled" value="1">
                      <a href="<bean:write name="cmn210Form" property="scriptStr" />.value=('<bean:write name="mdl" property="groupSid" />');<bean:write name="cmn210Form" property="scriptStr2" />">
                        <bean:write name="mdl" property="groupName" />
                      </a>
                </logic:notEqual>
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
                <logic:equal name="disabled" value="1">
                    <bean:write name="mdl" property="groupName" />
                </logic:equal>
                <logic:notEqual name="disabled" value="1">
                        <a href="<bean:write name="cmn210Form" property="scriptStr" />.value=('<bean:write name="mdl" property="groupSid" />');<bean:write name="cmn210Form" property="scriptStr2" />">
                          <bean:write name="mdl" property="groupName" />
                        </a>
                </logic:notEqual>
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
                <logic:equal name="disabled" value="1">
                    <bean:write name="mdl" property="groupName" />
                </logic:equal>
                <logic:notEqual name="disabled" value="1">
                        <a href="<bean:write name="cmn210Form" property="scriptStr" />.value=('<bean:write name="mdl" property="groupSid" />');<bean:write name="cmn210Form" property="scriptStr2" />">
                          <bean:write name="mdl" property="groupName" />
                        </a>
                </logic:notEqual>
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
                <logic:equal name="disabled" value="1">
                    <bean:write name="mdl" property="groupName" />
                </logic:equal>
                <logic:notEqual name="disabled" value="1">
                        <a href="<bean:write name="cmn210Form" property="scriptStr" />.value=('<bean:write name="mdl" property="groupSid" />');<bean:write name="cmn210Form" property="scriptStr2" />">
                          <bean:write name="mdl" property="groupName" />
                        </a>
                </logic:notEqual>
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
                <logic:equal name="disabled" value="1">
                    <bean:write name="mdl" property="groupName" />
                </logic:equal>
                <logic:notEqual name="disabled" value="1">
                        <a href="<bean:write name="cmn210Form" property="scriptStr" />.value=('<bean:write name="mdl" property="groupSid" />');<bean:write name="cmn210Form" property="scriptStr2" />">
                          <bean:write name="mdl" property="groupName" />
                        </a>
                </logic:notEqual>
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
                <logic:equal name="disabled" value="1">
                    <bean:write name="mdl" property="groupName" />
                </logic:equal>
                <logic:notEqual name="disabled" value="1">
                        <a href="<bean:write name="cmn210Form" property="scriptStr" />.value=('<bean:write name="mdl" property="groupSid" />');<bean:write name="cmn210Form" property="scriptStr2" />">
                          <bean:write name="mdl" property="groupName" />
                        </a>
                </logic:notEqual>
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
                <logic:equal name="disabled" value="1">
                    <bean:write name="mdl" property="groupName" />
                </logic:equal>
                <logic:notEqual name="disabled" value="1">
                        <a href="<bean:write name="cmn210Form" property="scriptStr" />.value=('<bean:write name="mdl" property="groupSid" />');<bean:write name="cmn210Form" property="scriptStr2" />">
                          <bean:write name="mdl" property="groupName" />
                        </a>
                </logic:notEqual>
            </logic:equal>

            <logic:equal name="mdl" property="classLevel" value="10">
              <% if (pastVal == 9) { %>
                    <ul>
              <% }  else if (pastVal == 10) { %>
                      </li>
              <% } %>
                      <li>
                <logic:equal name="disabled" value="1">
                    <bean:write name="mdl" property="groupName" />
                </logic:equal>
                <logic:notEqual name="disabled" value="1">
                        <a href="<bean:write name="cmn210Form" property="scriptStr" />.value=('<bean:write name="mdl" property="groupSid" />');<bean:write name="cmn210Form" property="scriptStr2" />">
                          <bean:write name="mdl" property="groupName" />
                        </a>
                </logic:notEqual>
            </logic:equal>

            <bean:define id="pastval" name="mdl" property="classLevel" />
            <% pastVal = ((Integer) pastval).intValue(); %>
            </logic:iterate>
          </logic:iterate>
          </li>
          </ul>
          </div>

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

</html:form>
</body>

</html:html>