<#SRFINC(JSPPART_HEADER)>
<#SRFINC(JSPPART_CONTENT)>
<#if view.hasPSControl("drtab")>
<#assign drtab=view.getPSControl("drtab")>
<% if(strCtrlId.compareTo("drtab_header")==0){ %>
<ul class="nav nav-tabs" id="<%=strCId%>drtab" >
<#list drtab.getRootItem().getAllItems() as dritem>
<#if (dritem_index == 0)>
<li class="active"><a href="#<%=strCId%>drtab_form" data-toggle="tab">${dritem.text}</a></li>
<#elseif ((dritem.getDRViewId()??) && (dritem.getDRViewId()?length gt 0))>
<li id="<%=strCId%>drtab_h_${dritem.getDRViewId()}" ><a href="#<%=strCId%>drtab_${dritem.getDRViewId()}" data-toggle="tab">${dritem.text}</a></li>
</#if>								
</#list>  								
</ul>
<% return;} %>
<% if(strCtrlId.compareTo("drtab_content")==0){ %>
<#list drtab.getRootItem().getAllItems() as dritem>
<#if ((dritem.getDRViewId()??) && (dritem.getDRViewId()?length gt 0))>
<div class="tab-pane " id="<%=strCId%>drtab_${dritem.getDRViewId()}"></div>
</#if>								
</#list> 
<% return;} %>
</#if>