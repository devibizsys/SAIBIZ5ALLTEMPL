<#SRFINC(JSPPART_HEADER)>
<% if(strCtrlId.compareTo("dashboard")==0){ %>
 ${dashboard.code}
<% return;} %>
<#if dashboard.getParam("parts")??>
<#assign parts= dashboard.getParam("parts")>
<#list parts as part>
<% if(strCtrlId.compareTo("${part.obj.name}")==0){ %>
 ${part.code}
<% return;} %>
</#list>
</#if>