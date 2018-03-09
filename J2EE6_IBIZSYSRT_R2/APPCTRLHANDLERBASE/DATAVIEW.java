import net.ibizsys.paas.core.DEDataSetFetchContext;
import net.ibizsys.paas.ctrlmodel.IDataViewModel;
import net.ibizsys.paas.db.DBFetchResult;
import net.ibizsys.paas.web.AjaxActionResult;
import net.ibizsys.paas.web.MDAjaxActionResult;


public class ${appview.codeName}${srfclassname('${item.name}')}Handler extends ${pub.getBaseClassPKGCodeName()}.paas.ctrlhandler.${srfclassname('${item.getControlType()}')}HandlerBase{
   
    protected ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model dataViewModel = null;
    public ${appview.codeName}${srfclassname('${item.name}')}Handler()  {
        super();
    }

     @Override
    protected void onInit() throws Exception
    {
    	super.onInit();
    	
    	dataViewModel = (${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model)this.getViewController().getCtrlModel("${item.name}");
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
            return (${de.codeName}Service)this.getViewController().getService();
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