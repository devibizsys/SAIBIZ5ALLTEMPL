<DIV id='<%=strCId%>${item.uniqueId}__fi' style="<#if (item.getColWidth() gt 0)>width:${item.getColWidth()?c}px</#if>" class="${item.getColCssClass()} form-group" data-ibiz-allowblank="<#if item.isAllowEmpty()>1<#else>0</#if>">
<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr>
<#if (item.isShowCaption())>
<td <#if (item.getLabelWidth() gt 0)>width="${item.getLabelWidth()?c}"</#if> valign="top">
<label id='<%=strCId%>${item.uniqueId}__lb' class="control-label" style="<#if (item.getLabelWidth() gt 0)>width:${item.getLabelWidth()?c}px;</#if>"  ><#if !(item.isEmptyCaption())><#if item.getCapPSLanguageRes()??><ibiz5:message code="${item.getCapPSLanguageRes().getLanResTag()}" text="${item.caption}"></ibiz5:message><#else>${item.caption}</#if></#if></label>
</td>
</#if>
<td>
<div class="col-md-12" >
	${editor.code}
</div>
</td>
</tr>
</table>
</DIV>