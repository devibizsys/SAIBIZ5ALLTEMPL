<button id="${item.uniqueId}" title="${item.caption}"  class="${item.getColCssClass()}  btn <#if item.isShowCaption()><#else>btn-icon-only</#if> <#if item.getPSSysCss()??>${item.getPSSysCss().getCssName()}</#if> ">
        <#if item.getPSSysImage()??><#assign img=item.getPSSysImage()><#if img.getCssClass()?? && (img.getCssClass()?length gt 0)><i class="${img.getCssClass()}"></i></#if></#if>   
        <#if item.isShowCaption()><span >${item.caption}</span></#if>
</button>