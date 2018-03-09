<div id="<%=strCId%>${item.uniqueId}" name="${item.name}" style="<#if (item.getColWidth() gt 0)>width:${item.getColWidth()?c}px</#if>" class="<#if (!item.isShowCaption())>ibiz-form-formgroup-nocap row form-group<#else>ibiz-form-formgroup</#if> ${item.getColCssClass()} <#if (item.getPSSysCss()??)>${item.getPSSysCss().getCssName()}</#if>">
<#if item.isShowCaption()>
<fieldset style="border: 1px solid #e5e5e5;margin-bottom:10px;">
<#if item.getTitleBarCloseMode() == 0>
<div class="note note-success" style="height:40px;" ><h5><#if item.getCapPSLanguageRes()??><ibiz5:message code="${item.getCapPSLanguageRes().getLanResTag()}" text="${item.caption}"></ibiz5:message><#else>${item.caption}</#if></h5></div>
<#else>
<div class="note note-success" style="height:40px;" ><h5><#if item.getCapPSLanguageRes()??><ibiz5:message code="${item.getCapPSLanguageRes().getLanResTag()}" text="${item.caption}"></ibiz5:message><#else>${item.caption}</#if></h5><a class="<%=strCId%>${item.uniqueId}_ddl form-grouppanel-link"><i id="<%=strCId%>${item.uniqueId}_ddi" class="fa fa-angle-double-down" aria-hidden="true"></i></a></div>
<div id="<%=strCId%>${item.uniqueId}_ddt" class="<%=strCId%>${item.uniqueId}_ddl form-group form-grouppanel-tip"><span>. . . . . .</span></div>
</#if>
<div id="<%=strCId%>${item.uniqueId}_gd" >
<#list items as rowitem>
${rowitem.code}
</#list>
</div>
</fieldset>
<#else>
<#list items as rowitem>
${rowitem.code}
</#list>
</#if>
</div>