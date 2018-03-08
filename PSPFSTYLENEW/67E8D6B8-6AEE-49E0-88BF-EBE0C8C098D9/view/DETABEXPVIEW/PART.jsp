<#SRFINC(JSPPART_HEADER)>
<% if(strCtrlId.compareTo("exptab")==0){ %>
<ul class="nav nav-tabs" id="<%=strCId%>exptab" >
<#assign tabviewpanels=view.getPSControls("tabviewpanel",10)>
<#list tabviewpanels as expitem>
<li><a  href="#<%=strCId%>exptab_${expitem.name?replace(':','_')}" data-toggle="tab"><#if expitem.getCapPSLanguageRes()??><ibiz5:message code="${expitem.getCapPSLanguageRes().getLanResTag()}" text="${expitem.caption}"></ibiz5:message><#else>${expitem.caption}</#if></a></li>	
</#list>  								
</ul>
<div class="tab-content" style="margin:10px">
<#list tabviewpanels as expitem>
<div class="tab-pane " id="<%=strCId%>exptab_${expitem.name?replace(':','_')}"></div>								
</#list> 
</div>
<% return;} %>