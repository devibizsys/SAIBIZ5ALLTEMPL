var ${view.codeName}ControllerBase = IndexViewControllerBase.extend({
        <#SRFINC(CONTROLLER_BASE)>
      ,getDefaultCfg:function(cfg){
         var me=this;   
         var config={backendurl:cfg.appctx+'/${view.getPSAppModule().getCodeName()}/${view.codeName}.do?'+'srfcid='+cfg.containerid+'&'
                     ,viewurl:'/${view.getPSAppModule().getCodeName()?lower_case}/${view.codeName?lower_case}.jsp?'+'srfcid='+cfg.containerid+'&'<#if view.getPortalPSSysCounterRef()??>,portalcountertag:'${view.getPortalPSSysCounterRef().tag}'</#if>
               ,menualign:'${view.getMainMenuAlign()?lower_case}',ctrls:{}<#if view.getDefPSAppView()??><#assign _appview= view.getDefPSAppView()>,defviewurl:'/${_appview.getPSAppModule().codeName?lower_case}/${_appview.codeName?lower_case}.jsp'</#if>};
       return  $.extend({},config,cfg);
      } 
        ,getAppFunc:function(arg){
        var me = this;
        var appfuncid = arg.appfuncid;
<#if view.getPSAppMenu()??>
<#list view.getPSAppMenu().getRootItem().getAllItems() as menuitem >
<#if menuitem.getPSAppFunc()??>
<#assign  appfunc=menuitem.getPSAppFunc()>
           if(appfuncid=='${appfunc.id}'){
                var ret={funcsn:'${appfunc.funcSN}',functype:'${appfunc.appFuncType}',openmode:'${appfunc.openMode}'<#if appfunc.getPSAppView()??><#assign _appview=appfunc.getPSAppView()>,viewurl:'/${_appview.getPSAppModule().codeName?lower_case}/${_appview.codeName?lower_case}.jsp'</#if><#if appfunc.getPSSubAppView()??><#assign _appview=appfunc.getPSSubAppView()>,subapp:'${appfunc.getPSSubAppRef().folderName}',viewurl:'${_appview.getPageUrl()}'</#if><#if appfunc.getUserData()??>,userData:'${appfunc.getUserData()}'</#if><#if appfunc.getUserData2()??>,userData2:'${appfunc.getUserData2()}'</#if><#if appfunc.getPSPDTAppFuncId()??>,pdtAppFuncId:'${appfunc.getPSPDTAppFuncId()}'</#if><#if appfunc.getJSCode()??>,jsCode:'${srfjsstring('${appfunc.getJSCode()}')}'</#if><#if appfunc.getHtmlPageUrl()??>,htmlPageUrl:'${appfunc.getHtmlPageUrl()}'</#if><#if menuitem.isDisableClose()>,closable:false<#else>,closable:true</#if><#if menuitem.isHideSideBar()>,hidesidebar:true</#if><#if (appfunc.getViewWidth() gt 0)>,viewwidth:${appfunc.getViewWidth()?c}</#if><#if (appfunc.getViewHeight() gt 0)>,viewheight:${appfunc.getViewHeight()?c}</#if>,viewtitle:'${appfunc.getViewTitle()}',openviewparam:${appfunc.getOpenViewParam().toString()}};
             return $.extend({},ret,arg);
           }
</#if>
</#list>
</#if>
           return null;
        }

});