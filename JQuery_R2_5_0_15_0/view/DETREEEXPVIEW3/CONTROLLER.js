var ${view.codeName}ControllerBase = TreeExpView3ControllerBase.extend({
<#SRFINC(CONTROLLER_BASE)>
<#assign expbarctrl=view.getPSControl('treeexpbar')>
,getDefaultCfg:function(cfg){
    var me=this;
    var config={backendurl:cfg.appctx+'/${view.getPSAppModule().getCodeName()}/${view.codeName}.do?'+'srfcid='+cfg.containerid+'&'
                ,viewurl:'/${view.getPSAppModule().getCodeName()?lower_case}/${view.codeName?lower_case}.jsp?'+'srfcid='+cfg.containerid+'&'
               ,ctrls:{expbar:{countertag:'<#if expbarctrl.getPSSysCounterRef()??>${expbarctrl.getPSSysCounterRef().tag}</#if>'}}};
    return  $.extend({},config,cfg);
}  
<#SRFINC(EXPVIEWCONTROLLER_BASE)>
});