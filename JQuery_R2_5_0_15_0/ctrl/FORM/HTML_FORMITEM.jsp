<div id='<%=strCId%>${item.uniqueId}__fi' style="<#if (item.getColWidth() gt 0)>width:${item.getColWidth()?c}px</#if>" class="ibiz-form-${item.getDetailType()?lower_case} ${item.getColCssClass()} form-group<#if (item.getPSSysCss()??)> ${item.getPSSysCss().getCssName()}</#if>" data-ibiz-allowblank="<#if item.isAllowEmpty()>1<#else>0</#if>">
	<#if (item.isShowCaption())>
	<div class='ibiz-formitem-label-left ' style="position: absolute;">
		<label id='<%=strCId%>${item.uniqueId}__lb' class="control-label" style="<#if (item.getLabelWidth() gt 0)>width:${item.getLabelWidth()?c}px;</#if>"  ><#if !(item.isEmptyCaption())>${item.caption}</#if></label>
	</div>
	</#if>
	<div style="margin-left:<#if (item.getLabelWidth() gt 0)>${(item.getLabelWidth()+10)?c}<#else>10</#if>px;margin-right:10px;" >
		${editor.code}
	</div>
</div>