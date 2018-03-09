<div id="${ctrl.name}" action="#" class="form-horizontal ">
<!-- 表单区:开始-->
   <div class="col-md-10 form-group row">
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
  <div class="tab-pane <#if (formpage_index ==0)>active</#if>" id="${ctrl.name}_${formpage.obj.codeName}_tab">${formpage.code}</div>
</#list>
</div>
</#if>        
      </div>
<!-- 表单区:结束-->
<!-- 按钮区:开始-->
 <div class="col-md-2 form-group row" style="float:right;">
    <button id="${ctrl.name}_resetbutton" title="重置"  class=" btn " style="float:right;margin-right:0px;">
     <span >重置</span>
   </button>

    <button id="${ctrl.name}_searchbutton" title="搜索"  class=" btn green " style="float:right;margin-right:8px;">
     <span >搜索</span>
</button>

</div>
<!-- 按钮区:结束-->
</div>