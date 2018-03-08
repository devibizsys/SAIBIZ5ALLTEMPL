<#SRFINC(JSPPART_HEADER)>
<% if(strCtrlId.compareTo("exptab")==0){ %>
<ul class="nav nav-tabs" id="<%=strCId%>exptab" style="display:none;">
<#assign treeexpbar=view.getPSControl("treeexpbar")>
<#assign tree=treeexpbar.getPSDETree()>
<#list tree.getPSDETreeNodes() as treeNode>
<#if treeNode.getNavPSAppView()??>
<li><a  href="#<%=strCId%>exptab_${treeNode.getNodeType()}" data-toggle="tab"></a></li>	
</#if>
</#list>  								
</ul>
<div class="tab-content" style="margin:10px">
<#list tree.getPSDETreeNodes() as treeNode>
<#if treeNode.getNavPSAppView()??>
<div class="tab-pane " id="<%=strCId%>exptab_${treeNode.getNodeType()}"></div>		
</#if>						
</#list> 
</div>
<% return;} %>