package ${pub.getPKGCodeName()}.${app.getAppFolder()?lower_case}.${item.getPSAppModule().codeName?lower_case}.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import net.ibizsys.paas.appmodel.AppModelGlobal;
import net.ibizsys.paas.appmodel.IApplicationModel;
import net.ibizsys.paas.demodel.DEModelGlobal;
import net.ibizsys.paas.demodel.IDataEntityModel;
import net.ibizsys.paas.service.IService;
import net.ibizsys.paas.service.ServiceGlobal;
import net.ibizsys.paas.sysmodel.ISystemModel;
import net.ibizsys.paas.sysmodel.SysModelGlobal;
import net.ibizsys.paas.controller.ViewControllerGlobal;
import net.ibizsys.paas.ctrlmodel.ICtrlModel;
import net.ibizsys.paas.ctrlhandler.ICtrlHandler;


import ${pub.getPKGCodeName()}.srv.${sys.codeName}SysModel;
import ${pub.getPKGCodeName()}.${app.getAppFolder()?lower_case}.${app.getPKGCodeName()}AppModel;

<#if item.getDataEntity()??>
<#assign de=item.getDataEntity()>
</#if>

/**
 * 视图[${item.name}]控制类基类
 *
 * !! 不要对此代码进行修改
 */
@Controller
@RequestMapping(value = "/${app.getAppFolder()}/${item.getPSAppModule().codeName}/${item.codeName}.do")
<#if item.isEnableWF()>
public class ${item.codeName}Controller extends ${pub.getBaseClassPKGCodeName()}.pswf.controller.${item.getPSViewType().codeName}ControllerBase{
<#else>
public class ${item.codeName}Controller extends ${pub.getBaseClassPKGCodeName()}.paas.controller.${item.getPSViewType().codeName}ControllerBase{
</#if>
     public ${item.codeName}Controller() throws Exception{
	 super();
         this.setId("${item.id}");
<#if item.getCaption()??>
         this.setCaption("${item.caption}");
</#if>
<#if item.getSubCaption()??>
         this.setSubCaption("${item.getSubCaption()}");
</#if>
<#if item.getTitle()??>
         this.setTitle("${item.title}");
</#if>    
<#if item.getCapPSLanguageRes()??>
		this.setCapLanResTag("${item.getCapPSLanguageRes().getLanResTag()}");
</#if>
<#if item.getSubCapPSLanguageRes()??>
		this.setSubCapLanResTag("${item.getSubCapPSLanguageRes().getLanResTag()}");
</#if>
<#if item.getTitlePSLanguageRes()??>
		this.setTitleLanResTag("${item.getTitlePSLanguageRes().getLanResTag()}");
</#if> 
<#if item.getAccUserMode() gt 0>
         this.setAccessUserMode(${item.getAccUserMode()?c});
</#if>       
<#if item.getAccessKey()??>
         this.setAccessKey("${item.getAccessKey()}");
</#if>   
<#if item.getViewWizardGroup()??>
         this.setViewWizardGroupId("${item.getViewWizardGroup().id}");
</#if> 
<#if item.getPSViewMsgGroup()??>
         this.setViewMsgGroupId("${item.getPSViewMsgGroup().id}");
</#if> 
<#if item.getPSAppViewParams()??>
<#list item.getPSAppViewParams() as viewparam>
<#if viewparam.getDesc()??>
          //${viewparam.getDesc()}
</#if>
          this.setAttribute("${viewparam.key}","${viewparam.value}");
</#list>
</#if>

         ViewControllerGlobal.registerViewController("/${app.getAppFolder()}/${item.getPSAppModule().codeName}/${item.codeName}.do",this);
         ViewControllerGlobal.registerViewController("${pub.getPKGCodeName()}.${app.getAppFolder()?lower_case}.${item.getPSAppModule().codeName?lower_case}.controller.${item.codeName}Controller",this);
     }
  
     
     @Override
    protected void prepareViewParam() throws Exception
    {
    	super.prepareViewParam();
<#if item.isEnableWF()>
        this.setWFModel(this.getSystemModel().getWFModel("${item.getPSWorkflow().id}"));
<#if item.isWFIAMode()>  
        this.setWFIAMode(true);
        this.setWFStepValue("${item.getWFStepValue()}");
</#if>
<#if item.getPSDEWF()??>
        this.setDEWF(this.getDEModel().getDEWF("${item.getPSDEWF().id}"));    
</#if>        
</#if>
      
<#if item.isRedirectView() && item.isPSDEView() >
          this.setEnableWorkflow(<#if item.isEnableWorkflow()>true<#else>false</#if>);
</#if>   

    }

    private ${sys.codeName}SysModel ${srfparamname('${sys.codeName}')}SysModel;
    
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

       @Override
       public  ISystemModel getSystemModel() {
        return this.get${sys.codeName}SysModel();
    }

       
       private ${app.getPKGCodeName()}AppModel ${srfparamname('${app.getPKGCodeName()}')}AppModel;
       public  ${app.getPKGCodeName()}AppModel get${app.getPKGCodeName()}AppModel() {
            if(this.${srfparamname('${app.getPKGCodeName()}')}AppModel==null)
            {
                try
                 {
                       this.${srfparamname('${app.getPKGCodeName()}')}AppModel = (${app.getPKGCodeName()}AppModel)AppModelGlobal.getApplication("${pub.getPKGCodeName()}.${app.getAppFolder()?lower_case}.${app.getPKGCodeName()}AppModel");
                 }
                 catch(Exception ex)
                 {
                 }
            }       
            return this.${srfparamname('${app.getPKGCodeName()}')}AppModel;
       }

        @Override
        public  IApplicationModel  getAppModel() {
         return this.get${app.getPKGCodeName()}AppModel();
     }

     


<#if item.getDataEntity()??>
<#assign de=item.getDataEntity()>
     
      private ${de.getClassOrPkgName('DEMODEL',pub)} ${srfparamname('${de.codeName}')}DEModel;

      public  ${de.getClassOrPkgName('DEMODEL',pub)} get${de.codeName}DEModel() {
            if(this.${srfparamname('${de.codeName}')}DEModel==null){
                try
                {
                     this.${srfparamname('${de.codeName}')}DEModel = (${de.getClassOrPkgName('DEMODEL',pub)})DEModelGlobal.getDEModel("${de.getClassOrPkgName('DEMODEL',pub)}");
                }
                catch(Exception ex)
                {
                }
            }
           return this.${srfparamname('${de.codeName}')}DEModel;
      }

       public  IDataEntityModel getDEModel() {
          return this.get${de.codeName}DEModel();
      }
    
    public  ${de.getClassOrPkgName('SERVICE',pub)} get${de.codeName}Service() {
    	  try
          {
               return (${de.getClassOrPkgName('SERVICE',pub)})ServiceGlobal.getService("${de.getClassOrPkgName('SERVICE',pub)}",this.getSessionFactory());
          }
          catch(Exception ex)
          {
        	  return null;
          }
    }

     /* (non-Javadoc)
  	 * @see net.ibizsys.paas.controller.IViewController#getService()
  	 */
  	@Override
  	public IService getService()
  	{
  		return get${de.codeName}Service();
  	}


     
</#if>


     /**
      * 准备部件模型
      * @throws Exception
      */
     @Override
     protected void prepareCtrlModels()throws Exception{
<#list item.getAllPSAjaxControls() as ctrl>
<#if ctrl.hasCtrlModel()>
          //注册 ${ctrl.name}
<#if ctrl.getModelScope()=='DE'>
<#assign ctrlde=ctrl.getDataEntity()>
          ICtrlModel ${srfparamname('${ctrl.name}')}=(ICtrlModel)get${sys.codeName}SysModel().createObject("${pub.getPKGCodeName()}.${app.getAppFolder()?lower_case}.srv.${ctrlde.getPSSystemModule().codeName?lower_case}.ctrlmodel.${ctrlde.codeName}${ctrl.codeName}${srfclassname('${ctrl.getControlType()}')}Model");
</#if>
<#if ctrl.getModelScope()=='VIEW'>
          ICtrlModel ${srfparamname('${ctrl.name}')}=(ICtrlModel)get${sys.codeName}SysModel().createObject("${pub.getPKGCodeName()}.${app.getAppFolder()?lower_case}.${appview.getPSAppModule().codeName?lower_case}.ctrlmodel.${appview.codeName}${srfclassname('${ctrl.name}')}Model");
</#if>
<#if ctrl.getModelScope()=='APP'>
          ICtrlModel ${srfparamname('${ctrl.name}')}=(ICtrlModel)get${sys.codeName}SysModel().createObject("${pub.getPKGCodeName()}.${app.getAppFolder()?lower_case}.srv.sys.ctrlmodel.${app.getPKGCodeName()}${ctrl.codeName}${srfclassname('${ctrl.getControlType()}')}Model");
</#if>
<#if ctrl.getPSControlParam()??>
<#if ctrl.getPSControlParam().getCtrlParamNames()??>
<#list ctrl.getPSControlParam().getCtrlParamNames() as paramname>
          ${srfparamname('${ctrl.name}')}.setCtrlParam("${paramname}","${ctrl.getPSControlParam().getCtrlParam('${paramname}','')}");
</#list>
</#if>
</#if>
          ${srfparamname('${ctrl.name}')}.init(this);
          this.registerCtrlModel("${ctrl.name}",${srfparamname('${ctrl.name}')});
</#if>
</#list>
     }
	
     /**
      * 准备部件处理对象
      * @throws Exception
      */
     @Override
     protected void prepareCtrlHandlers()throws Exception{
<#list item.getAllPSAjaxControls() as ctrl>
          //注册 ${ctrl.name}
          ICtrlHandler ${srfparamname('${ctrl.name}')} = (ICtrlHandler)get${sys.codeName}SysModel().createObject("${pub.getPKGCodeName()}.${app.getAppFolder()?lower_case}.${appview.getPSAppModule().codeName?lower_case}.ctrlhandler.${appview.codeName}${srfclassname('${ctrl.name}')}Handler");
          ${srfparamname('${ctrl.name}')}.init(this);
          this.registerCtrlHandler("${ctrl.name}",${srfparamname('${ctrl.name}')});
</#list>	
<#list item.getPSUpdatePanels() as ctrl>
         //注册 ${ctrl.name}
          net.ibizsys.paas.ctrlhandler.UpdatePanelHandler ${srfparamname('${ctrl.name}')} = new net.ibizsys.paas.ctrlhandler.UpdatePanelHandler();
          <#if ctrl.getPSSysMsgTempl()??>
          //设置消息模板 ${ctrl.getPSSysMsgTempl().name}
          ${srfparamname('${ctrl.name}')}.setSysMsgTemplId("${ctrl.getPSSysMsgTempl().id}");
          </#if>
          <#if ctrl.getPSDataEntity()??>
          ${srfparamname('${ctrl.name}')}.setDEName("${ctrl.getPSDataEntity().name}");
          </#if>
          <#if ctrl.getPSDEAction()??>
          ${srfparamname('${ctrl.name}')}.setDEActionName("${ctrl.getPSDEAction().name}");
          </#if>
          ${srfparamname('${ctrl.name}')}.init(this);
          this.registerCtrlHandler("${ctrl.name}",${srfparamname('${ctrl.name}')});
</#list>
     }

 
     /**
      * 注册界面行为
      * @throws Exception
      */
     @Override
     protected void prepareUIActions()throws Exception{
<#if item.getPSUIActions()??>
<#list item.getPSUIActions() as uiaction>
<#if (uiaction.getUIActionMode() == 'BACKEND')>
          this.registerUIAction("${uiaction.codeName}");
</#if>
<#if (uiaction.getUIActionType() == 'DEUIACTION') && (uiaction.getDataAccessAction()??) && (uiaction.getDataAccessAction()?length gt 0)>
<#if uiaction.getPSDataEntity()??>
          //注册行为[${uiaction.name}]访问操作
          this.registerDEDataAccessAction("${uiaction.getDataEntity().name}","${uiaction.getDataAccessAction()}");  
<#elseif item.getDataEntity()??>
            //注册行为[${uiaction.name}]访问操作
           this.registerDEDataAccessAction("${item.getDataEntity().name}","${uiaction.getDataAccessAction()}");    
</#if>
</#if>
</#list>
</#if>
	
     }
}