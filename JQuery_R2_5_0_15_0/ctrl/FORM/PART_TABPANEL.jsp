<div id='<%=strCId%>${ctrl.name}_${item.name}' class="ibiz-form-${item.getDetailType()?lower_case} ${item.getColCssClass()}<#if (item.getPSSysCss()??)> ${item.getPSSysCss().getCssName()}</#if>">
<ul class="nav nav-tabs" id="<%=strCId%>${ctrl.name}_${item.name}_tab">
<#list tabpages as tabpage>
   <li class="<#if (tabpage_index ==0)>active</#if>"><a href="#<%=strCId%>${ctrl.name}_${tabpage.obj.codeName}_tab"  id="<%=strCId%>${tabpage.obj.uniqueId}" name="${tabpage.obj.name}" data-toggle="tab"><#if tabpage.obj.getCapPSLanguageRes()??><ibiz5:message code="${tabpage.obj.getCapPSLanguageRes().getLanResTag()}" text="${tabpage.obj.caption}"></ibiz5:message><#else>${tabpage.obj.caption}</#if></a></li>
</#list>
</ul>
<div class="tab-content">
<#list tabpages as tabpage>
  <div class="tab-pane <#if (tabpage_index ==0)>active</#if>" id="<%=strCId%>${ctrl.name}_${tabpage.obj.codeName}_tab">${tabpage.code}</div>
</#list>
</div>
</div>