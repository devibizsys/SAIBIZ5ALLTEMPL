<div class="${item.getColCssClass()} "  >
<#if item.isShowCaption()>
<fieldset style="border: 1px solid #e5e5e5;">
<div class="note note-success" x><h5>${item.caption}</h5></div>
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