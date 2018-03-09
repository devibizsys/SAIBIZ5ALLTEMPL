<#assign baseclass='ReportView'>
<#SRFINC(CONTROLLER_REALCLASS)>
var ${view.codeName}ControllerBase = ${baseclass}ControllerBase.extend({
<#SRFINC(CONTROLLER_BASE)>
<#SRFINC(REPVIEWCONTROLLER_BASE)>
,getDefaultCfg:function(cfg){
     var me=this;   
    var config={backendurl:cfg.appctx+'/${view.getPSAppModule().getCodeName()}/${view.codeName}.do?'+'srfcid='+cfg.containerid+'&'
                ,viewurl:'/${view.getPSAppModule().getCodeName()?lower_case}/${view.codeName?lower_case}.jsp?'+'srfcid='+cfg.containerid+'&'
               ,ctrls:{reportpanel:${reportpanel.code}}};
    return  $.extend({},config,cfg);
}  
});