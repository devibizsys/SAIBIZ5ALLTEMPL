<div id="<%=strCId%>${ctrl.name}" class="form-horizontal">
   <div class="col-md-12  form-body">
<#list hiddens as hidden>${hidden.code}</#list>
<#if ctrl.isNoTabHeader()>
<#list formpages as formpage>  
  ${formpage.code}  
</#list>
<#else>
<ul class="nav nav-tabs" id="<%=strCId%>${ctrl.name}_tab">
<#list formpages as formpage>
   <li class="<#if (formpage_index ==0)>active</#if>"><a href="#<%=strCId%>${ctrl.name}_${formpage.obj.codeName}_tab" data-toggle="tab"><#if formpage.obj.getCapPSLanguageRes()??><ibiz5:message code="${formpage.obj.getCapPSLanguageRes().getLanResTag()}" text="${formpage.obj.caption}"></ibiz5:message><#else>${formpage.obj.caption}</#if></a></li>
</#list>
</ul>
<div class="tab-content ">
<#list formpages as formpage>
  <div class="tab-pane <#if (formpage_index ==0)>active</#if> row" id="<%=strCId%>${ctrl.name}_${formpage.obj.codeName}_tab" >${formpage.code}</div>
</#list>
</div>
</#if>        
</div>
</div>