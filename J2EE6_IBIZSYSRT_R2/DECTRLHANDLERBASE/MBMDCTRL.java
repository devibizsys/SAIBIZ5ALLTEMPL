import net.ibizsys.paas.core.DEDataSetFetchContext;
import net.ibizsys.paas.ctrlmodel.IListModel;
import net.ibizsys.paas.db.DBFetchResult;
import net.ibizsys.paas.web.AjaxActionResult;
import net.ibizsys.paas.web.MDAjaxActionResult;
import net.ibizsys.paas.entity.IEntity;

/**
 * 列表控件后台处理基类
 */
public class ${appview.codeName}${srfclassname('${item.name}')}Handler extends ${pub.getBaseClassPKGCodeName()}.paas.ctrlhandler.ListHandlerBase{

    protected ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model listModel = null;
    public ${appview.codeName}${srfclassname('${item.name}')}Handler()  {
        super();
    }

     @Override
    protected void onInit() throws Exception
    {
<#if item.isNoSort()> 
         this.setEnableUserSort(false);
</#if>
<#if item.getMinorSortPSDEF()??>
         this.setMinorSortField("${item.getMinorSortPSDEF().name}");
    	 this.setMinorSortDir("${item.getMinorSortDir()}");
</#if>
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
        listModel = (${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model)this.getViewController().getCtrlModel("${item.name}");
    	super.onInit();
     }

       @Override
	protected IListModel getListModel()
	{
		return this.getRealListModel();
	}
    
    protected ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model getRealListModel(){
    	return this.listModel;
    }

       protected ${de.codeName}Service getRealService(){
            return (${de.codeName}Service)this.getService();
       }


      @Override
	protected DBFetchResult fetchDEDataSet(DEDataSetFetchContext deDataSetFetchContext) throws Exception
	{
              return  this.getRealService().fetch${item.getPSDEDataSet().codeName}(deDataSetFetchContext);
	}



}