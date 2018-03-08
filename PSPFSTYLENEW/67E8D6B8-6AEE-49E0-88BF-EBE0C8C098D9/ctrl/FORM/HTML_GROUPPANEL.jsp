<div class="${item.getColCssClass()} <#if (!item.isShowCaption())>row form-group</#if> " style="<#if (item.getColWidth() gt 0)>width:${item.getColWidth()?c}px</#if>" id="${item.uniqueId}" name="${item.name}" >
<#if item.isShowCaption()>
<fieldset style="border: 1px solid #e5e5e5;margin-bottom:10px;">
<div class="note note-success" style="height:40px;"><h5>${item.caption}</h5></div>
<#list items as rowitem>
${rowitem.code}
</#list>
</fieldset>
<#else>
<#list items as rowitem>
${rowitem.code}
</#list>
</#if>
</div>