<#assign deuiaction=item.getPSUIAction()>
<button title="${item.tooltip}"  data-ibiz-tag="${deuiaction.getUIActionTag()}" data-ibiz-target="${deuiaction.getActionTarget()}" class="btn <#if item.isShowCaption()><#else>btn-icon-only</#if> <#if item.getPSSysCss()??>${item.getPSSysCss().getCssName()}</#if>  ibiz-toolbar-item"
     data-ibiz-uiaction='{"type":"${deuiaction.getUIActionType()}","tag":"${deuiaction.getUIActionTag()}"<#if (deuiaction.getActionTarget()!="")>,"actiontarget":"${deuiaction.getActionTarget()}"</#if>
<#if item.getPSDEUIAction()??>
<#if deuiaction.getUIActionMode()!="SYS">
,"actionmode":"${deuiaction.getUIActionMode()}"
<#if deuiaction.getUIActionMode()=="BACKEND">
,"timeout":${deuiaction.getTimeout()?c}<#if deuiaction.getConfirmMsg()??>,"confirmmsg":"${deuiaction.getConfirmMsg()}"</#if>
</#if>
<#comment><!--开始：向导界面--></#comment>
<#if deuiaction.getFrontProcessType()=="WIZARD">
,"fronttype":"${deuiaction.frontProcessType}"
<#if item.getFrontPSAppView()??>
<#assign frontview=item.getFrontPSAppView()>,"frontview":{view:"${app.getPKGCodeName()}.view.${frontview.fullCodeName}","width":${frontview.getWidth()?c},"height":${frontview.getHeight()?c},"title":"${frontview.title}"}
</#if>                  
</#if>
<#comment><!--结束：向导界面--></#comment>
<#comment><!--开始：打开网页--></#comment>
<#if deuiaction.getFrontProcessType()=="OPENHTMLPAGE">
,"fronttype":"${deuiaction.frontProcessType}","htmlpageurl":"${deuiaction.getHtmlPageUrl()}"
</#if>
<#comment><!--结束：向导界面--></#comment>
</#if>
</#if>
<#if item.getPSWFUIAction()??>
,"actionmode":"${deuiaction.getUIActionMode()}"
</#if>
}'  class="btn btn-icon-only green ibiz-toolbar-item" >
        <#if item.isShowIcon() && item.getPSSysImage()??><#assign img=item.getPSSysImage()><#if img.getCssClass()?? && (img.getCssClass()?length gt 0)><i class="${img.getCssClass()}"></i></#if></#if>   
        <#if item.isShowCaption()><span >${item.caption}</span></#if>
</button>