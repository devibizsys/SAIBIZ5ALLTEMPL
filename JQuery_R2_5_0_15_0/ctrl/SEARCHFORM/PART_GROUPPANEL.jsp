<div id='<%=strCId%>${ctrl.name}_${item.name}' class="${item.getColCssClass()} <#if (item.getPSSysCss()??)>${item.getPSSysCss().getCssName()}</#if>">
<#if item.isShowCaption()>
<fieldset style="border: 1px solid #e5e5e5;">
<div class="note note-success" ><h5><#if item.getCapPSLanguageRes()??><ibiz5:message code="${item.getCapPSLanguageRes().getLanResTag()}" text="${item.caption}"></ibiz5:message><#else>${item.caption}</#if></h5></div>
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