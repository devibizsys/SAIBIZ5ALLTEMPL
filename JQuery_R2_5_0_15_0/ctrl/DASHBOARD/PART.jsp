<div id="<%=strCId%>${ctrl.name}">
<#assign newrow=0>
<#list parts as part>
<#if (part.obj.getPSAjaxControlParam().isNewRowMode())>
<#if (newrow==1)>
</div>
</#if>
<div class="row">
<#assign newrow=1>
</#if>
${part.code}
</#list>
<#if (newrow==1)>
</div>
</#if>
</div>