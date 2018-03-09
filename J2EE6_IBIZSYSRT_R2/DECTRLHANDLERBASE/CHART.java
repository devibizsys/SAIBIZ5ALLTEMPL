import net.ibizsys.paas.core.DEDataSetFetchContext;
import net.ibizsys.paas.ctrlmodel.IChartModel;
import net.ibizsys.paas.db.DBFetchResult;
import net.ibizsys.paas.web.AjaxActionResult;
import net.ibizsys.paas.web.MDAjaxActionResult;
import net.ibizsys.paas.entity.IEntity;

/**
 * 图表控件后台处理基类
 */
<#if item.getPSAjaxControlHandler()??>
public class ${appview.codeName}${srfclassname('${item.name}')}Handler extends ${item.getHandler()}{
<#else>
public class ${appview.codeName}${srfclassname('${item.name}')}Handler extends ${pub.getBaseClassPKGCodeName()}.paas.ctrlhandler.${srfclassname('${item.getControlType()}')}HandlerBase{
</#if>

    protected ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model chartModel = null;
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
        chartModel = (${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model)this.getViewController().getCtrlModel("${item.name}");
    	super.onInit();
    }

       @Override
	protected IChartModel getChartModel()
	{
		return this.getRealChartModel();
	}
    
    protected ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model getRealChartModel(){
    	return this.chartModel;
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