<div id="<%=strCId%>${item.uniqueId}" name="${item.name}" class="ibiz-form-${item.getDetailType()?lower_case} ${item.getColCssClass()}<#if (item.getPSSysCss()??)> ${item.getPSSysCss().getCssName()}</#if>">
${item.getRawContent()}
</div>