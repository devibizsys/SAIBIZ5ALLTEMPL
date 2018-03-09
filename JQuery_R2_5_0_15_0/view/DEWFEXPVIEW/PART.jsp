<#SRFINC(JSPPART_HEADER)>
<% if(strCtrlId.compareTo("exptab")==0){ %>
<ul class="nav nav-tabs" id="<%=strCId%>exptab" style="display:none;">
<#assign expbar=view.getPSControl("expbar")>
<#list expbar.getRootItem().getAllItems() as expitem>
<#if ((expitem.getExpViewId()??) && (expitem.getExpViewId()?length gt 0))>
	<li><a href="#<%=strCId%>exptab_${expitem.getExpViewId()?replace(':','_')}" data-toggle="tab"></a></li>
</#if>								
</#list>  								
</ul>
<div class="tab-content" style="margin:10px">
<#list expbar.getRootItem().getAllItems() as expitem>
<#if ((expitem.getExpViewId()??) && (expitem.getExpViewId()?length gt 0))>
	<div class="tab-pane " id="<%=strCId%>exptab_${expitem.getExpViewId()?replace(':','_')}"></div>
</#if>								
</#list> 
</div>
<% return;} %>