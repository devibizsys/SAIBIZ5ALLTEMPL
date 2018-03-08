import net.ibizsys.paas.ctrlmodel.ISearchFormModel;
import net.ibizsys.paas.data.DataObject;
import net.ibizsys.paas.data.IDataObject;
import net.ibizsys.paas.web.AjaxActionResult;
import net.ibizsys.paas.web.SDAjaxActionResult;
import net.ibizsys.paas.sysmodel.ISystemRuntime;
import net.ibizsys.paas.ctrlhandler.IFormItemHandler;
import net.ibizsys.paas.ctrlhandler.IFormItemUpdateHandler;

public class ${appview.codeName}${srfclassname('${item.name}')}Handler extends ${pub.getBaseClassPKGCodeName()}.paas.ctrlhandler.${srfclassname('${item.getControlType()}')}HandlerBase{
   
    protected ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model searchformModel = null;
    public ${appview.codeName}${srfclassname('${item.name}')}Handler()  {
        super();
    }

     @Override
    protected void onInit() throws Exception
    {
<#if item.getPSAjaxControlHandler()??>
<#assign achandler=item.getPSAjaxControlHandler()>
<#if achandler.isEnableCache()>
        this.setEnableCache(true);
        this.setCacheScope(${achandler.getCacheScope()?c});
<#if achandler.getCacheTimeout() gt 0>
        this.setCacheTimeout(${achandler.getCacheTimeout()?c});
</#if>
</#if>
<#if achandler.getPSSysUniState()??>
        this.setUniStateId("${achandler.getPSSysUniState().id}");
</#if>
<#if achandler.getUniStateKeyValue()?? && (achandler.getUniStateKeyValue()?length gt 0)>
        this.setUniStateKeyValue("${achandler.getUniStateKeyValue()}");
</#if>
<#if achandler.getUniStateField()?? && (achandler.getUniStateField()?length gt 0)>
        this.setUniStateField("${achandler.getUniStateField()}");
</#if>
</#if>
        searchformModel  = (${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model)this.getViewController().getCtrlModel("${item.name}");
    	super.onInit();
     }
    
       @Override
	protected ISearchFormModel getSearchFormModel()
	{
		return this.getRealSearchFormModel();
	}
    
    protected ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model getRealSearchFormModel(){
    	return this.searchformModel ;
    }

       protected ${de.codeName}Service getRealService(){
            return (${de.codeName}Service)this.getViewController().getService();
       }

              /**
	 * 准备部件成员处理对象
	 * @throws Exception
	 */
        @Override
	protected void prepareCtrlItemHandlers()throws Exception
	{
             super.prepareCtrlItemHandlers();

             ISystemRuntime iSystemRuntime = (ISystemRuntime)this.getSystemModel();

<#list item.getPSDEFormItems() as formitem>
<#if (formitem.getItemHandlerType()??) && (formitem.getItemHandlerType()?length>0)>
             //注册 '${formitem.name}'
             IFormItemHandler ${srfparamname('${formitem.name}')}Handler=(IFormItemHandler)iSystemRuntime.createObject("${pub.getPKGCodeName()}.${app.getAppFolder()?lower_case}.srv.${de.getPSSystemModule().codeName?lower_case}.ctrlhandler.${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}${srfclassname('${formitem.name}')}Handler");   
             ${srfparamname('${formitem.name}')}Handler.init(this.getSearchFormModel(),this);
             this.registerCtrlItemHandler(ITEMACTIONTYPE_FORMITEM+"${formitem.codeName}",${srfparamname('${formitem.name}')}Handler);
	
</#if>
</#list>

<#if item.getPSDEFormItemUpdates()??>
<#list item.getPSDEFormItemUpdates() as fiupdate>   
              //注册表单项更新 '${fiupdate.codeName}'
             IFormItemUpdateHandler ${srfparamname('${fiupdate.codeName}')}Handler = (IFormItemUpdateHandler)iSystemRuntime.createObject("${pub.getPKGCodeName()}.${app.getAppFolder()?lower_case}.srv.${de.getPSSystemModule().codeName?lower_case}.ctrlhandler.${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}${srfclassname('${fiupdate.codeName}')}Handler");  
             ${srfparamname('${fiupdate.codeName}')}Handler.init(this.getEditFormModel(),this);
             this.registerCtrlItemHandler(ITEMACTIONTYPE_FORMITEMUPDATE+"${fiupdate.codeName}",${srfparamname('${fiupdate.codeName}')}Handler);
</#list>
</#if>
	}

}