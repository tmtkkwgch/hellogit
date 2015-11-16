<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<%-- <title>[Group Session] <bean:write name="cmn131knForm" property="cmn131dspTitle" /><gsmsg:write key="cmn.cmn131kn.1" /></title> --%>
<title>[Group Session] 
      <logic:equal name="cmn132Form" property="cmn132MyGroupKbn" value="<%= String.valueOf(1) %>">
        <gsmsg:write key="cmn.cmn130.2" /><gsmsg:write key="cmn.check" />
      </logic:equal>
      <logic:equal name="cmn132Form" property="cmn132MyGroupKbn" value="<%= String.valueOf(0) %>">
        <gsmsg:write key="cmn.mygroup" /><gsmsg:write key="cmn.check" />
      </logic:equal>
</title>
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/submit.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="FreezePane">
<html:form action="/common/cmn132" onsubmit="return onControlSubmit();">

<input type="hidden" name="CMD" value="touroku">


<!--　BODY -->
<table width="100%">
<tr>
<td width="100%" align="center" cellpadding="5" cellspacing="0">

  <table width="95%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">
  
<!--     <table cellpadding="0" cellspacing="0" border="0" width="100%"> -->
<!--     <tr> -->
<%--     <td width="0%" class="header_white_bg_text" align="left" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.shain.info" /></span></td> --%>
<!--     <td width="0%" class="header_white_bg_text"> -->
<%--       <logic:equal name="cmn132Form" property="cmn132MyGroupKbn" value="<%= String.valueOf(1) %>"> --%>
<%--         [ <gsmsg:write key="cmn.cmn130.2" /><gsmsg:write key="cmn.check" /> ] --%>
<%--       </logic:equal> --%>
<%--       <logic:equal name="cmn132Form" property="cmn132MyGroupKbn" value="<%= String.valueOf(0) %>"> --%>
<%--         [ <gsmsg:write key="cmn.mygroup" /><gsmsg:write key="cmn.check" /> ] --%>
<%--       </logic:equal> --%>
<!--     </td> -->
<!--     <td width="100%" class="header_white_bg"> -->
<%--       <input type="button" name="btn_close" class="btn_close_n1" value="<gsmsg:write key="cmn.close" />" onClick="window.close();"></td> --%>
<!--     <td width="0%"> -->
<%--       <img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.shain.info" />"></td> --%>
<!--     </tr> -->
<!--   </table> -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">
      <logic:equal name="cmn132Form" property="cmn132MyGroupKbn" value="<%= String.valueOf(1) %>">
        [ <gsmsg:write key="cmn.cmn130.2" /><gsmsg:write key="cmn.check" /> ]
      </logic:equal>
      <logic:equal name="cmn132Form" property="cmn132MyGroupKbn" value="<%= String.valueOf(0) %>">
        [ <gsmsg:write key="cmn.mygroup" /><gsmsg:write key="cmn.check" /> ]
      </logic:equal>
    </td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="40%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="60%">
      <input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_close_n1" onClick="window.close();">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>
  
  
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <logic:messagesPresent message="false"><html:errors /></logic:messagesPresent>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">


    <table width="100%" class="tl_u2" border="0" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.cmn130.1" /></span></td>
    <td align="left" class="td_wt" width="100%"><span class="text_base"><bean:write name="cmn132Form" property="cmn131name" /></span></td>
    </tr>
    <logic:equal name="cmn132Form" property="cmn132MyGroupKbn" value="<%= String.valueOf(1) %>">
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.registant" /></span></td>
    <td align="left" class="td_wt" width="100%"><span class="text_base"><bean:write name="cmn132Form" property="cmn132owner.usiSei" />&nbsp<bean:write name="cmn132Form" property="cmn132owner.usiMei" /></span></td>
    </tr>
    </logic:equal>
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cir.11" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <span class="text_base"><bean:write name="cmn132Form" property="cmn131memo" filter="false"/></span>
    </td>
    </tr>
    </table>
    
      <span class="text_base">
        <img src="../common/images/spacer.gif" width="1px" height="30px" border="0">
        <logic:notEmpty name="cmn132Form" property="cmn131knMemberList" scope="request">
          <span class="text_bb1"> <gsmsg:write key="cmn.member" /> </span>
          <table class="tl0" boreder="0" width="100%">
            <tr>
              <td class="table_bg_A5B4E1" align="center" ><span class="text_bb1"><gsmsg:write key="cmn.name" /></span></td>
              <td class="table_bg_A5B4E1" align="center" ><span class="text_bb1"><gsmsg:write key="cmn.post" /></span></td>
              <td class="table_bg_A5B4E1" align="center" ><span class="text_bb1"><gsmsg:write key="cmn.affiliation.group" /></span></td>
             </tr>
    
            <logic:iterate id="memMdl" name="cmn132Form" property="cmn131knMemberList" scope="request">
              <tr>
                <td class="td_type1">
                  <span class="text_base">
                    <bean:write name="memMdl" property="usiSei" />&nbsp;<bean:write name="memMdl" property="usiMei" />  
                  </span>
                </td>
    
                <td class="td_type1">
                  <span class="text_base">
                    <bean:write name="memMdl" property="usiYakusyoku" />
                  </span>
                </td>
                <td class="td_type1">               
                    <logic:notEmpty name="memMdl" property="belongGrpList">
                      <logic:iterate id="grp" name="memMdl" property="belongGrpList">
                        <div class="text_base"><bean:write name="grp" property="groupName"/></div>
                      </logic:iterate>
                    </logic:notEmpty>
                </td>
                
              </tr>
              </logic:iterate>
            </table>
        </logic:notEmpty>
        
        
      </span>
    

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
 
    <table cellpadding="0" cellpadding="0" border="0" width="100%">
    
    
    
    
    <tr>
    <td align="right" valign="middle">
      <input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_close_n1" onClick="window.close();">
    </td>
    </tr>
    </table>

  </td>
  </tr>
  </table>

</td>
</tr>
</table>

<%-- <%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %> --%>

</html:form>
</body>
</html:html>