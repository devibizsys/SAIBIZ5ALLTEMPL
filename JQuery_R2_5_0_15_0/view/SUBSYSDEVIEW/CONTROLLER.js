${dashboard.code2}


var ${view.codeName}ControllerBase = PortalViewControllerBase.extend({
        <#SRFINC(CONTROLLER_BASE)>
      ,getDefaultCfg:function(cfg){
         var me=this;   
         var config={backendurl:cfg.appctx+'/${view.getPSAppModule().getCodeName()}/${view.codeName}.do?'+'srfcid='+cfg.containerid+'&'
                     ,viewurl:'/${view.getPSAppModule().getCodeName()?lower_case}/${view.codeName?lower_case}.jsp?'+'srfcid='+cfg.containerid+'&'
               ,ctrls:{}};
       return  $.extend({},config,cfg);
      }
      ,onInit:function(){
		arguments.callee.$.onInit.call(this);
		${dashboard.code}
      }
});