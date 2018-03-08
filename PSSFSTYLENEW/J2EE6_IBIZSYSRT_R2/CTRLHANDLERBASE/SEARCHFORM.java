import net.ibizsys.paas.ctrlmodel.ISearchFormModel;
import net.ibizsys.paas.data.DataObject;
import net.ibizsys.paas.data.IDataObject;
import net.ibizsys.paas.web.AjaxActionResult;
import net.ibizsys.paas.web.SDAjaxActionResult;

public class ${appview.codeName}${srfclassname('${item.name}')}Handler extends ${pub.getBaseClassPKGCodeName()}.paas.ctrlhandler.${srfclassname('${item.getControlType()}')}HandlerBase{
   
    protected ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model searchformModel = null;
    public ${appview.codeName}${srfclassname('${item.name}')}Handler()  {
        super();
    }

     @Override
    protected void onInit() throws Exception
    {
    	super.onInit();
    	
    	searchformModel  = (${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model)this.getViewController().getCtrlModel("${item.name}");
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

<#list item.getPSDEFormItems() as formitem>
<#if (formitem.getItemHandlerType()??) && (formitem.getItemHandlerType()?length>0)>
             //注册 '${formitem.name}'
             ${appview.codeName}${srfclassname('${item.name}')}${srfclassname('${formitem.name}')}Handler ${srfparamname('${formitem.name}')}Handler = new ${appview.codeName}${srfclassname('${item.name}')}${srfclassname('${formitem.name}')}Handler();
             ${srfparamname('${formitem.name}')}Handler.init(this.getSearchFormModel(),this);
             this.registerCtrlItemHandler(ITEMACTIONTYPE_FORMITEM+"${formitem.codeName}",${srfparamname('${formitem.name}')}Handler);
	
</#if>
</#list>

<#if item.getPSDEFormItemUpdates()??>
<#list item.getPSDEFormItemUpdates() as fiupdate>   
              //注册表单项更新 '${fiupdate.codeName}'
             ${appview.codeName}${srfclassname('${item.name}')}${srfclassname('${fiupdate.codeName}')}Handler ${srfparamname('${fiupdate.codeName}')}Handler = new ${appview.codeName}${srfclassname('${item.name}')}${srfclassname('${fiupdate.codeName}')}Handler();
             ${srfparamname('${fiupdate.codeName}')}Handler.init(this.getEditFormModel(),this);
             this.registerCtrlItemHandler(ITEMACTIONTYPE_FORMITEMUPDATE+"${fiupdate.codeName}",${srfparamname('${fiupdate.codeName}')}Handler);
</#list>
</#if>
	}

         	
	/* 
	 * 
	 */
        @Override
	protected AjaxActionResult onSearch() throws Exception
	{
		SDAjaxActionResult sdAjaxActionResult = new SDAjaxActionResult();
		this.getWebContext().setCurAjaxActionResult(sdAjaxActionResult);
		
		IDataObject iDataObject = new DataObject();
		this.fillInputValues(iDataObject, true);
		
		this.fillOutputDatas(iDataObject,sdAjaxActionResult.getData(true));
		
		return sdAjaxActionResult;
		
		
	}


 
}