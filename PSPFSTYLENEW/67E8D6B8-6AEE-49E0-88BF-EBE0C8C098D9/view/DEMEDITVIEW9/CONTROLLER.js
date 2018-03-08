var ${view.codeName}ControllerBase = MEditView9ControllerBase.extend({
<#SRFINC(CONTROLLER_BASE)>
,getDefaultCfg:function(cfg){
    var me=this;   
    var config={backendurl:cfg.appctx+'/${view.getPSAppModule().getCodeName()}/${view.codeName}.do?'+'srfcid='+cfg.containerid+'&'
                ,viewurl:'/${view.getPSAppModule().getCodeName()?lower_case}/${view.codeName?lower_case}.jsp?'+'srfcid='+cfg.containerid+'&'
                ,ctrls:{meditviewpanel:${meditviewpanel.code2}}};
    return  $.extend({},config,cfg);
}
<#if view.hasPSControl('meditviewpanel')>
,createEditViewController:function(arg){
    return new ${view.getPSControl('meditviewpanel').getPSAppDEView().codeName}Controller($.extend({},arg,{pcontroller:this,appctx:this.getAppCtx()}));
}
</#if>  

});