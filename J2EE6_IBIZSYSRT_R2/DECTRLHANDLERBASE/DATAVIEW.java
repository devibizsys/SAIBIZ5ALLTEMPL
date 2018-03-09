import net.ibizsys.paas.core.DEDataSetFetchContext;
import net.ibizsys.paas.ctrlmodel.IDataViewModel;
import net.ibizsys.paas.db.DBFetchResult;
import net.ibizsys.paas.web.AjaxActionResult;
import net.ibizsys.paas.web.MDAjaxActionResult;
/**
 * 应用视图[${appview.title}]数据看板${item.name}部件后台处理对象基类
 */
<#if item.getPSAjaxControlHandler()??>
public class ${appview.codeName}${srfclassname('${item.name}')}Handler extends ${item.getHandler()}{
<#else>
public class ${appview.codeName}${srfclassname('${item.name}')}Handler extends ${pub.getBaseClassPKGCodeName()}.paas.ctrlhandler.${srfclassname('${item.getControlType()}')}HandlerBase{
</#if>   
    protected ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model dataViewModel = null;
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
        dataViewModel = (${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model)this.getViewController().getCtrlModel("${item.name}");
    	super.onInit();
    }

       @Override
	protected IDataViewModel getDataViewModel()
	{
		return this.getRealDataViewModel();
	}
    
    protected ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model getRealDataViewModel(){
    	return this.dataViewModel;
    }

       protected ${de.codeName}Service getRealService(){
            return (${de.codeName}Service)this.getService();
       }

       /**
	 * 准备部件操作数据访问能力
	 * @throws Exception
	 */
        @Override
	protected void prepareDataAccessActions()throws Exception
	{
              super.prepareDataAccessActions();
<#if item.getPSAjaxControlHandler()??>
<#list item.getPSAjaxControlHandler().getAjaxActions() as ajaxAction>
             this.registerDataAccessAction("${ajaxAction}","${item.getPSAjaxControlHandler().getDataAccessAction('${ajaxAction}')}");
</#list>              
</#if> 
         }

        @Override
	protected DBFetchResult fetchDEDataSet(DEDataSetFetchContext deDataSetFetchContext) throws Exception
	{
		return  this.getRealService().fetch${item.getPSAjaxControlHandler().getPSDEDataSet().codeName}(deDataSetFetchContext);
	}


<#if (item.getPSAjaxControlHandler().getTempMode()==2)>
        @Override
	public int getTempMode()
	{
	     return TEMPMODE_MINOR;
	}
</#if>

<#if (item.getPSAjaxControlHandler().getTempMode()==1)>
        @Override
	public int getTempMode()
	{
	     return TEMPMODE_MAJOR;
	}
</#if>

}