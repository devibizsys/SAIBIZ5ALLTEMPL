var ${view.codeName}ControllerBase = WFEditView3ControllerBase.extend({
        <#SRFINC(CONTROLLER_BASE)>
,getDefaultCfg:function(cfg){
    var me=this;
    var config={backendurl:cfg.appctx+'/${view.getPSAppModule().getCodeName()}/${view.codeName}.do?'+'srfcid='+cfg.containerid+'&'
                ,viewurl:'/${view.getPSAppModule().getCodeName()?lower_case}/${view.codeName?lower_case}.jsp?'+'srfcid='+cfg.containerid+'&'
               ,ctrls:{form:{}}};
    return  $.extend({},config,cfg);
}  
<#SRFINC(EDITVIEWCONTROLLER_BASE)>
<#SRFINC(EDITVIEW2CONTROLLER_BASE)>
});