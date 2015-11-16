<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<div data-role="footer" data-theme="<%= usrTheme %>" align="center">
  <div data-role="navbar">
    <ul>
      <li>
        <a href="../mobile/sp_man001.do?mobileType=1" data-icon="home"><div class="font_xxsmall"><gsmsg:write key="cmn.main" /></div></a>
      </li>

      <% if (pluginName != null && pluginName.toLowerCase().indexOf("top") < 0 && !pluginName.equals("sch") && !pluginName.equals("ntp")) { %>
      <li>
        <% if (pluginName.equals("sml")) { %>
               <a href="../mobile/sp_sml010.do?mobileType=1" data-icon="arrow-u"><div class="font_xxsmall"><gsmsg:write key="cmn.shortmail" /><gsmsg:write key="mobile.15" /></div></a>
        <% } else if (pluginName.equals("wml")) { %>
               <a href="../mobile/sp_wml010.do?mobileType=1" data-icon="arrow-u"><div class="font_xxsmall"><gsmsg:write key="wml.wml010.25" /><gsmsg:write key="mobile.15" /></div></a>
        <% } else if (pluginName.equals("adr")) { %>
               <a href="../mobile/sp_adr010.do?mobileType=1" data-icon="arrow-u"><div class="font_xxsmall"><gsmsg:write key="addressbook" /><gsmsg:write key="mobile.15" /></div></a>
        <% } else if (pluginName.equals("usr")) { %>
               <a href="../mobile/sp_usr010.do?mobileType=1" data-icon="arrow-u"><div class="font_xxsmall"><gsmsg:write key="cmn.shain.info" /><gsmsg:write key="mobile.15" /></div></a>
        <% } else if (pluginName.equals("bbs")) { %>
               <a href="../mobile/sp_bbs010.do?mobileType=1" data-icon="arrow-u"><div class="font_xxsmall"><gsmsg:write key="cmn.bulletin" /><gsmsg:write key="mobile.15" /></div></a>
        <% } else if (pluginName.equals("rng")) { %>
               <a href="../mobile/sp_rng010.do?mobileType=1" data-icon="arrow-u"><div class="font_xxsmall"><gsmsg:write key="rng.62" /><gsmsg:write key="mobile.15" /></div></a>
        <% } else if (pluginName.equals("rsv")) { %>
               <a href="../mobile/sp_rsv010.do?mobileType=1" data-icon="arrow-u"><div class="font_xxsmall"><gsmsg:write key="cmn.reserve" /><gsmsg:write key="mobile.15" /></div></a>
        <% } else if (pluginName.equals("cir")) { %>
               <a href="../mobile/sp_cir010.do?mobileType=1" data-icon="arrow-u"><div class="font_xxsmall"><gsmsg:write key="cir.5" /><gsmsg:write key="mobile.15" /></div></a>
        <% } %>
      </li>
      <% } %>

      <li>
        <a href="../mobile/sp_cmn001.do?mobileType=1&CMD=logout" data-icon="back" data-iconpos="right"><div class="font_xxsmall"><gsmsg:write key="mobile.11" /></div></a>
      </li>
    </ul>
  </div>
</div>

<% int plugCnt = 0; %>
<% int liCnt = 0; %>
<% String ulClass = "li_width_max"; %>

<logic:equal name="<%= thisForm %>" property="scheduleUseOk" value="0">
  <% plugCnt = plugCnt + 1; %>
</logic:equal>
<logic:equal name="<%= thisForm %>" property="webMailUseOk" value="0">
  <% plugCnt = plugCnt + 1; %>
</logic:equal>
<logic:equal name="<%= thisForm %>" property="smailUseOk" value="0">
  <% plugCnt = plugCnt + 1; %>
</logic:equal>
<logic:equal name="<%= thisForm %>" property="addressUseOk" value="0">
  <% plugCnt = plugCnt + 1; %>
</logic:equal>
<logic:equal name="<%= thisForm %>" property="userUseOk" value="0">
  <% plugCnt = plugCnt + 1; %>
</logic:equal>
<logic:equal name="<%= thisForm %>" property="bullutinUseOk" value="0">
  <% plugCnt = plugCnt + 1; %>
</logic:equal>
<logic:equal name="<%= thisForm %>" property="ringiUseOk" value="0">
  <% plugCnt = plugCnt + 1; %>
</logic:equal>
<logic:equal name="<%= thisForm %>" property="circularUseOk" value="0">
  <% plugCnt = plugCnt + 1; %>
</logic:equal>

<% String liClass = null; %>
<% if (plugCnt == 1) { %>
<%     liClass = "li_width_cnt2"; %>
<% } else if (plugCnt == 2) { %>
<%     liClass = "li_width_cnt3"; %>
<%     ulClass = "li_width_max2"; %>
<% } else if (plugCnt == 3) { %>
<%     liClass = "li_width_cnt4"; %>
<% } else if (plugCnt == 4) { %>
<%     liClass = "li_width_cnt5"; %>
<% } else if (plugCnt == 5 || plugCnt >= 6) { %>
<%     if (pluginName.toLowerCase().indexOf("top") > 0 ) { %>
<%       ulClass = "li_width_max2"; %>
<%     } %>
<%     liClass = "li_width_cnt6"; %>
<% } %>

<div data-role="footer" data-theme="<%= usrTheme %>" align="center">
  <div data-role="navbar" data-theme="<%= usrTheme %>">
    <ul class="<%= ulClass %>">
      <% if (!pluginName.equals("sch")) { %>
        <logic:equal name="<%= thisForm %>" property="scheduleUseOk" value="0">
          <li class="<%= liClass %>"><a style="height:50px" href="../mobile/sp_sch030.do?mobileType=1"><img style="margin:5px" src="../mobile/sp/imgages/sch_menu_icon2_single.gif" alt="<gsmsg:write key="schedule.108" />" /></a></li>
      <% liCnt = liCnt +1; %>
        </logic:equal>
      <% } %>

      <% if (!pluginName.equals("wml") && !pluginName.equals("wmlTop")) { %>
        <logic:equal name="<%= thisForm %>" property="webMailUseOk" value="0">
          <li class="<%= liClass %>"><a style="height:50px" href="../mobile/sp_wml010.do?mobileType=1"><img style="margin:5px" src="../mobile/sp/imgages/wml_menu_icon2_single.gif" alt="<gsmsg:write key="cmn.shortmail" />" /></a></li>
      <% liCnt = liCnt +1; %>
        </logic:equal>
      <% } %>

      <% if (!pluginName.equals("sml") && !pluginName.equals("smlTop")) { %>
        <logic:equal name="<%= thisForm %>" property="smailUseOk" value="0">
          <li class="<%= liClass %>"><a style="height:50px" href="../mobile/sp_sml010.do?mobileType=1"><img style="margin:5px" src="../mobile/sp/imgages/sml_menu_icon2_single.gif" alt="<gsmsg:write key="wml.wml010.25" />" /></a></li>
      <% liCnt = liCnt +1; %>
        </logic:equal>
      <% } %>

      <% if (!pluginName.equals("adr") && !pluginName.equals("adrTop")) { %>
        <logic:equal name="<%= thisForm %>" property="addressUseOk" value="0">
          <li class="<%= liClass %>"><a style="height:50px" href="../mobile/sp_adr010.do?mobileType=1"><img style="margin:5px" src="../mobile/sp/imgages/adr_menu_icon2_single.gif" alt="<gsmsg:write key="addressbook" />" /></a></li>
     <% liCnt = liCnt +1; %>
        </logic:equal>
      <% } %>

      <% if (!pluginName.equals("usr") && !pluginName.equals("usrTop") && liCnt <= 4) { %>
        <logic:equal name="<%= thisForm %>" property="userUseOk" value="0">
          <li class="<%= liClass %>"><a style="height:50px" style="height:50px" href="../mobile/sp_usr010.do?mobileType=1"><img style="margin:5px" src="../mobile/sp/imgages/usr_menu_icon2_single.gif" alt="<gsmsg:write key="cmn.shain.info" />" /></a></li>
      <% liCnt = liCnt +1; %>
        </logic:equal>
      <% } %>

      <% if (!pluginName.equals("bbs") && !pluginName.equals("bbsTop") && liCnt <= 4) { %>
        <logic:equal name="<%= thisForm %>" property="bullutinUseOk" value="0">
          <li class="<%= liClass %>"><a style="height:50px" href="../mobile/sp_bbs010.do?mobileType=1"><img style="margin:5px" src="../mobile/sp/imgages/bbs_menu_icon2_single.gif" alt="<gsmsg:write key="cmn.bulletin" />" /></a></li>
      <% liCnt = liCnt +1; %>
        </logic:equal>
      <% } %>

      <% if (!pluginName.equals("cir") && !pluginName.equals("cirTop") && liCnt <= 4) { %>
        <logic:equal name="<%= thisForm %>" property="circularUseOk" value="0">
          <li class="<%= liClass %>"><a style="height:50px" href="../mobile/sp_cir010.do?mobileType=1"><img style="margin:5px" src="../mobile/sp/imgages/cir_menu_icon2_single.gif" alt="<gsmsg:write key="cmn.bulletin" />" /></a></li>
      <% liCnt = liCnt +1; %>
        </logic:equal>
      <% } %>

      <% if (!pluginName.equals("rng") && !pluginName.equals("rngTop") && liCnt <= 4) { %>
        <logic:equal name="<%= thisForm %>" property="ringiUseOk" value="0">
          <li class="<%= liClass %>"><a style="height:50px" href="../mobile/sp_rng010.do?mobileType=1"><img style="margin:5px" src="../mobile/sp/imgages/rng_menu_icon2_single.gif" alt="<gsmsg:write key="rng.62" />" /></a></li>
      <% liCnt = liCnt +1; %>
        </logic:equal>
      <% } %>

      <% if (!pluginName.equals("rsv") && !pluginName.equals("rsvTop") && liCnt <= 4) { %>
        <logic:equal name="<%= thisForm %>" property="rsvUseOk" value="0">
          <li class="<%= liClass %>"><a style="height:50px" href="../mobile/sp_rsv010.do?mobileType=1"><img style="margin:5px" src="../mobile/sp/imgages/rsv_menu_icon2_single.gif" alt="<gsmsg:write key="cmn.reserve" />" /></a></li>
        </logic:equal>
      <% } %>

      <li class="<%= liClass %>" style="margin:0;"><a href="#" onClick="popupMenu();" style="margin:0;"><img src="../mobile/sp/imgages/menu_icon_grid.gif" alt="<gsmsg:write key="rng.62" />" /></a></li>

    </ul>
  </div>
</div>

<div data-role="footer" data-theme="<%= usrTheme %>" data-theme="<%= usrTheme %>" align="center">
  <br>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_footer.jsp" %>
</div><!-- /footer -->


<div id="popupmenu" style="position:absolute;bottom:0;display:none;z-index:10;display:none;width:100%;height:100%;background:rgba(255, 255, 255, 0.7);">
  <div class="popupmenu">
    <div style="text-align:right;">
      <a href="#" onclick="popupMenu();" data-role="button" data-icon="delete" data-iconpos="notext">Close</a>
    </div>

    <% int tdCnt = 0; %>
    <div style="text-align:center;">
      <table width="100%">
      <tr>
        <logic:equal name="<%= thisForm %>" property="scheduleUseOk" value="0">
        <td>
          <a href="../mobile/sp_sch010.do?mobileType=1">
            <div class="iconmenu">
              <img src="../mobile/sp/imgages/sch_pop_menu_icon.gif" class="iconpotision">
            </div>
          </a>
        </td>
        <% tdCnt = tdCnt + 1; %>
        </logic:equal>

        <logic:equal name="<%= thisForm %>" property="webMailUseOk" value="0">
        <td>
          <a href="../mobile/sp_wml010.do?mobileType=1">
            <div class="iconmenu">
              <img src="../mobile/sp/imgages/wml_pop_menu_icon.gif" class="iconpotision">
            </div>
          </a>
        </td>
        <% tdCnt = tdCnt + 1; %>
        </logic:equal>

        <logic:equal name="<%= thisForm %>" property="smailUseOk" value="0">
        <td>
          <a href="../mobile/sp_sml010.do?mobileType=1">
            <div class="iconmenu">
              <img src="../mobile/sp/imgages/sml_pop_menu_icon.gif" class="iconpotision">
            </div>
          </a>
        </td>
        <% tdCnt = tdCnt + 1; %>
       </logic:equal>

        <logic:equal name="<%= thisForm %>" property="bullutinUseOk" value="0">
        <td>
          <a href="../mobile/sp_bbs010.do?mobileType=1">
            <div class="iconmenu">
              <img src="../mobile/sp/imgages/bbs_pop_menu_icon.gif" class="iconpotision">
            </div>
          </a>
        </td>
        <% tdCnt = tdCnt + 1; %>
       </logic:equal>


       <% if (tdCnt >= 4) { %>
       </tr>
       <tr>
      <% tdCnt = 0; %>
       <% } %>

        <logic:equal name="<%= thisForm %>" property="rsvUseOk" value="0">
        <td>
          <a href="../mobile/sp_rsv010.do?mobileType=1">
            <div class="iconmenu">
              <img src="../mobile/sp/imgages/rsv_pop_menu_icon.gif" class="iconpotision">
            </div>
          </a>
        </td>
        <% tdCnt = tdCnt + 1; %>
        </logic:equal>



       <% if (tdCnt >= 4) { %>
       </tr>
       <tr>
       <% tdCnt = 0; %>
       <% } %>

        <logic:equal name="<%= thisForm %>" property="circularUseOk" value="0">
        <td>
          <a href="../mobile/sp_cir010.do?mobileType=1">
            <div class="iconmenu">
              <img src="../mobile/sp/imgages/cir_pop_menu_icon.gif" class="iconpotision">
            </div>
          </a>
        </td>
        <% tdCnt = tdCnt + 1; %>
        </logic:equal>


       <% if (tdCnt >= 4) { %>
       </tr>
       <tr>
       <% tdCnt = 0; %>
       <% } %>

        <logic:equal name="<%= thisForm %>" property="addressUseOk" value="0">
        <td>
          <a href="../mobile/sp_adr010.do?mobileType=1">
            <div class="iconmenu">
              <img src="../mobile/sp/imgages/adr_pop_menu_icon.gif" class="iconpotision">
            </div>
          </a>
        </td>
        <% tdCnt = tdCnt + 1; %>
        </logic:equal>

       <% if (tdCnt >= 4) { %>
       </tr>
       <tr>
       <% tdCnt = 0; %>
       <% } %>

        <logic:equal name="<%= thisForm %>" property="userUseOk" value="0">
        <td>
          <a href="../mobile/sp_usr010.do?mobileType=1">
            <div class="iconmenu">
              <img src="../mobile/sp/imgages/usr_pop_menu_icon.gif" class="iconpotision">
            </div>
          </a>
        </td>
        <% tdCnt = tdCnt + 1; %>
        </logic:equal>

       <% if (tdCnt >= 4) { %>
       </tr>
       <tr>
       <% tdCnt = 0; %>
       <% } %>

        <logic:equal name="<%= thisForm %>" property="ringiUseOk" value="0">
        <td>
          <a href="../mobile/sp_rng010.do?mobileType=1">
            <div class="iconmenu">
              <img src="../mobile/sp/imgages/rng_pop_menu_icon.gif" class="iconpotision">
            </div>
          </a>
        </td>
        </logic:equal>

       <% if (tdCnt >= 4) { %>
       </tr>
       <tr>
       <% tdCnt = 0; %>
       <% } %>

        <logic:equal name="<%= thisForm %>" property="nippouUseOk" value="0">
        <td>
          <a href="../mobile/sp_ntp010.do?mobileType=1">
            <div class="iconmenu">
              <img src="../mobile/sp/imgages/ntp_pop_menu_icon.gif" class="iconpotision">
            </div>
          </a>
        </td>
        </logic:equal>

      </tr>
      </table>
    </div>
  </div>
</div>