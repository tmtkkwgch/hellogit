<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

    <html:hidden property="usr030SearchKana" />
     <bean:define id="nrow" value="1" type="java.lang.String" />
      <table width="100%" cellpadding="0" cellspacing="0">
      <tr align="center">
      <td width="100%">
        <table width="95%" class="user_index_tl">
        <tr align="center">
        <logic:iterate id="existskn" name="usr030Form" property="usr030ekanas" scope="request">
          <bean:define id="row" name="existskn" property="row" type="java.lang.String" />
          <logic:notEqual name="row" value="<%= nrow %>" >
            </tr>
            <tr align="center">
            <% nrow=row; %>
          </logic:notEqual>
          <logic:equal name="row" value="<%= nrow %>" >
            <td>
              <logic:equal name="existskn" property="exists" value="true">
                <a href="#" onClick="return searchKn('<bean:write name="existskn" property="kana" />');"><span class="user_index_text"><bean:write name="existskn" property="kana" /></span></a>
              </logic:equal>
              <logic:notEqual name="existskn" property="exists" value="true">
                <span class="user_index_text_nolink"><bean:write name="existskn" property="kana" /></span>
              </logic:notEqual>
            </td>
          </logic:equal>
        </logic:iterate>
        </tr>
        </table>

      </td>
      </tr>
      </table>
