<#SRFINC(JSPPART_HEADER)>
<#if ctrls??>
<#list ctrls as ctrlitem>
<% if(strCtrlId.compareTo("${ctrlitem.obj.name}")==0){ %>
 ${ctrlitem.code}
<% return;} %>
</#list>
</#if>