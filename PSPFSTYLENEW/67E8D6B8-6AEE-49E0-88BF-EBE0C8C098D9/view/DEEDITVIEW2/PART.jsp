<#SRFINC(JSPPART_HEADER)>
<#SRFINC(JSPPART_CONTENT)>

<#if view.hasPSControl("drbar")>
<#assign drbar=view.getPSControl("drbar")>
<% if(strCtrlId.compareTo("drbar_header")==0){ %>
<ul class="nav nav-tabs" id="<%=strCId%>drtab" style="display:none;">
<li class="active"><a href="#<%=strCId%>drtab_form" data-toggle="tab"></a></li>
<#list drbar.getRootItem().getAllItems() as dritem>
<#if ((dritem.getDRViewId()??) && (dritem.getDRViewId()?length gt 0))>
<li><a href="#<%=strCId%>drtab_${dritem.getDRViewId()}" data-toggle="tab"></a></li>
</#if>								
</#list>  								
</ul>
<% return;} %>
<% if(strCtrlId.compareTo("drbar_content")==0){ %>
<#list drbar.getRootItem().getAllItems() as dritem>
<#if ((dritem.getDRViewId()??) && (dritem.getDRViewId()?length gt 0))>
<div class="tab-pane " id="<%=strCId%>drtab_${dritem.getDRViewId()}"></div>
</#if>								
</#list> 
<% return;} %>
</#if>