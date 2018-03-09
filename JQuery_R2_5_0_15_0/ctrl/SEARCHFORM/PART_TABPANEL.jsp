<div id='<%=strCId%>${ctrl.name}_${item.name}' class="${item.getColCssClass()}">
<ul class="nav nav-tabs" id="<%=strCId%>${ctrl.name}_${item.name}_tab">
<#list tabpages as tabpage>
   <li class="<#if (tabpage_index ==0)>active</#if>"><a href="#<%=strCId%>${ctrl.name}_${tabpage.obj.codeName}_tab" data-toggle="tab">${tabpage.obj.caption}</a></li>
</#list>
</ul>
<div class="tab-content">
<#list tabpages as tabpage>
  <div class="tab-pane <#if (tabpage_index ==0)>active</#if>" id="<%=strCId%>${ctrl.name}_${tabpage.obj.codeName}_tab">${tabpage.code}</div>
</#list>
</div>
</div>