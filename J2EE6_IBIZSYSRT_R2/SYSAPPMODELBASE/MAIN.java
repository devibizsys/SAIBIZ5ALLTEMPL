package ${pub.getPKGCodeName()}.${item.getAppFolder()?lower_case};

import org.springframework.stereotype.Component;
import net.ibizsys.paas.appmodel.AppModelGlobal;
import net.ibizsys.paas.core.ISystem;
import net.ibizsys.paas.sysmodel.SysModelGlobal;
import net.ibizsys.paas.service.IService;
import net.ibizsys.paas.service.ServiceGlobal;
import net.ibizsys.paas.appmodel.AppViewModel;
import net.ibizsys.paas.appmodel.AppDEViewModel;
import ${pub.getPKGCodeName()}.srv.${sys.codeName}SysModel;

/**
 * 应用程序[${item.name}]模型对象
 */
@Component
public class ${item.getPKGCodeName()}AppModel extends ${pub.getBaseClassPKGCodeName()}.paas.appmodel.AppModelBase  {

   public ${item.getPKGCodeName()}AppModel() throws Exception{
        super();
        //设置应用基本信息
         this.setId("${item.getId()}");      
        this.setName("${item.getPKGCodeName()}");      
        this.setPFType("${item.getPFType()}");      
        //注册到全局应用程序模型对象
        AppModelGlobal.registerApplication("${pub.getPKGCodeName()}.${item.getAppFolder()?lower_case}.${item.getPKGCodeName()}AppModel",this);
        //准备应用用户模式菜单
        this.prepareAppUserModeMenus();
        //准备应用视图
        this.prepareAppViews();
    }

     private ${sys.codeName}SysModel ${srfparamname('${sys.codeName}')}SysModel;
     
     /**
      * 获取当前系统模型对象
      * @return
      */
      public  ${sys.codeName}SysModel get${sys.codeName}SysModel() {
           if(this.${srfparamname('${sys.codeName}')}SysModel==null)
           {
               try
                {
                      this.${srfparamname('${sys.codeName}')}SysModel = (${sys.codeName}SysModel)SysModelGlobal.getSystem("${pub.getPKGCodeName()}.srv.${sys.codeName}SysModel");
                }
                catch(Exception ex)
                {
                }
           }       
           return this.${srfparamname('${sys.codeName}')}SysModel;
      }

      /* (non-Javadoc)
       * @see net.ibizsys.paas.core.IApplication#getSystem()
       */
       @Override
       public  ISystem getSystem() {
        return this.get${sys.codeName}SysModel();
    }

       /* (non-Javadoc)
        * @see net.ibizsys.paas.appmodel.AppModelBase#setPFType(java.lang.String)
        */
    @Override
    protected void setPFType(String strPFType)
    {
        super.setPFType(strPFType);
<#if item.getAllPSAppUtilPages()??>
<#list item.getAllPSAppUtilPages() as utilpage>
         this.registerUtilPageUrl("${utilpage.name}", "${utilpage.getPageUrl()}");
</#list>
</#if>
    }
    
    /**
   	 * 准备应用用户模式菜单
   	 * @throws Exception
   	 */
   	protected void prepareAppUserModeMenus()throws Exception
   	{
<#list item.getAllPSAppUserModes() as appusermode>
		//注册应用用户模式[${appusermode.name}]
		<#if appusermode.getPSSysUserMode()??>
		this.registerUserModeMenu("${srfjavastring('${appusermode.getPSSysUserMode().name}')}","${srfjavastring('${appusermode.getPSAppMenuModel().id}')}");
		<#else>
		this.registerUserModeMenu("","${srfjavastring('${appusermode.getPSAppMenuModel().id}')}");
		</#if>
</#list>
   	}
    
    /**
	 * 准备应用视图
	 * @throws Exception
	 */
	protected void prepareAppViews()throws Exception
	{
<#list item.getAllPSAppModules() as appmodule>
		//注册应用模块[${appmodule.name}]视图
		prepareAppViews_${appmodule.codeName}();
</#list>
	}
	
<#list item.getAllPSAppModules() as appmodule>	
	/**
	 * 准备应用视图[${appmodule.name}]
	 * @throws Exception
	 */
	protected void prepareAppViews_${appmodule.codeName}()throws Exception
	{
<#if item.getAllPSAppViews()??>
<#assign viewindex=0>
<#list item.getAllPSAppViews() as appview>	
<#if appview.getPSAppModule().id == appmodule.id>
              registerAppView("${srfjavastring('${appview.id}')}","${srfjavastring('${appview.codeName}')}",<#if appview.isPSDEView()>"${srfjavastring('${appview.getPSDEViewId()}')}"<#else>null</#if>,<#if appview.getTitle()??>"${srfjavastring('${appview.getTitle()}')}"<#else>null</#if>,<#if appview.getPSAppModule()??>"${srfjavastring('${appview.getPSAppModule().codeName}')}"<#else>null</#if>,<#if (appview.getOpenMode()??) && (appview.getOpenMode()?length gt 0)>"${srfjavastring('${appview.getOpenMode()}')}"<#else>null</#if>,<#if appview.getWidth() gt 0>${appview.getWidth()?c}<#else>-1</#if>,<#if appview.getHeight() gt 0>${appview.getHeight()?c}<#else>-1</#if>);
</#if>
<#comment>		
<#if appview.getPSAppModule().id == appmodule.id>
		//注册视图 ${appview.name}
<#if appview.isPSDEView()>
			AppDEViewModel m${viewindex?c} = new AppDEViewModel();
			m${viewindex?c}.setDEViewId("${appview.getPSDEViewId()}");
<#else>
			AppViewModel m${viewindex?c} = new AppViewModel();
</#if>
			m${viewindex?c}.setId("${appview.id}");
			m${viewindex?c}.setName("${appview.codeName}");
			<#if appview.getTitle()??>
			m${viewindex?c}.setTitle("${appview.getTitle()}");
			</#if>
			<#if appview.getPSAppModule()??>
			m${viewindex?c}.setModuleName("${appview.getPSAppModule().codeName}");
			</#if>
			<#if (appview.getOpenMode()??) && (appview.getOpenMode()?length gt 0)>
			m${viewindex?c}.setOpenMode("${appview.getOpenMode()}");
			</#if>
			<#if appview.getHeight() gt 0>
			m${viewindex?c}.setHeight(${appview.getHeight()?c});
			</#if>
			<#if appview.getWidth() gt 0>
			m${viewindex?c}.setWidth(${appview.getWidth()?c});
			</#if>
			this.registerAppView(m${viewindex?c});
<#assign viewindex=viewindex+1>

</#if>	
</#comment>	
</#list>
</#if>
	}
</#list>
	
	/* (non-Javadoc)
	 * @see net.ibizsys.paas.appmodel.AppModelBase#onInstallRTDatas()
	 */
    @Override
   	protected void onInstallRTDatas() throws Exception
   	{
   		super.onInstallRTDatas();
   		
   		//登记所有的门户界面
 <#if item.getAllPSAppViews()??>
 		net.ibizsys.psrt.srv.common.service.PortalPageService portalPageService  = (net.ibizsys.psrt.srv.common.service.PortalPageService)ServiceGlobal.getService(net.ibizsys.psrt.srv.common.service.PortalPageService.class);
 		net.ibizsys.psrt.srv.common.service.PVPartService pvPartService  = (net.ibizsys.psrt.srv.common.service.PVPartService)ServiceGlobal.getService(net.ibizsys.psrt.srv.common.service.PVPartService.class);
 <#list item.getAllPSAppViews() as appview>
 <#if appview.getPSViewType().id == 'APPPORTALVIEW'>
 		//创建门户视图 ${appview.name}
 		if(true)
 		{
 			net.ibizsys.psrt.srv.common.entity.PortalPage portalPage = new net.ibizsys.psrt.srv.common.entity.PortalPage();
 			portalPage.setPortalPageId("${appview.id}");
 			portalPage.setPortalPageName("${appview.getTitle()}");
 			if(portalPageService.checkKey(portalPage)==IService.CHECKKEYSTATE_OK)
 			{
 				portalPageService.create(portalPage,false);
 			}
 			else
 			{
 				pvPartService.removeByPortalPage(portalPage);
 			}
<#assign dbctrl=appview.getPSControl("dashboard")> 			
<#list dbctrl.getPSPortlets() as portlet>
				net.ibizsys.psrt.srv.common.entity.PVPart ${portlet.name} = new net.ibizsys.psrt.srv.common.entity.PVPart();
				${portlet.name}.setPortalPageId(portalPage.getPortalPageId());
				${portlet.name}.setPortalPageName(portalPage.getPortalPageName());
				${portlet.name}.setPVPartName("${portlet.getTitle()}");
				${portlet.name}.setPVPartType("${portlet.getPortletType()}");
				${portlet.name}.setCtrlId("${portlet.name}");
				pvPartService.create(${portlet.name},false);
</#list>
 		}
 </#if>
 </#list>
 </#if>
   	}
}