<div id="${ctrl.name}"  class="form-horizontal">
       <div class="form-body" >
<#list hiddens as hidden>${hidden.code}</#list>
<#if ctrl.isNoTabHeader()>
<#list formpages as formpage>  
  ${formpage.code}  
</#list>
<#else>
<ul class="nav nav-tabs" id="${ctrl.name}_tab">
<#list formpages as formpage>
   <li class="<#if (formpage_index ==0)>active</#if>"><a href="#${ctrl.name}_${formpage.obj.codeName}_tab" data-toggle="tab">${formpage.obj.caption}</a></li>
</#list>
</ul>
<div class="tab-content">
<#list formpages as formpage>
  <div class="tab-pane <#if (formpage_index ==0)>active</#if> row" id="${ctrl.name}_${formpage.obj.codeName}_tab">${formpage.code}</div>
</#list>
</div>
</#if>        
       </div>
</div>