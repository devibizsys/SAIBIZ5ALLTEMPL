import net.ibizsys.paas.core.DEDataSetFetchContext;
import net.ibizsys.paas.ctrlmodel.IGridModel;
import net.ibizsys.paas.db.DBFetchResult;
import net.ibizsys.paas.web.AjaxActionResult;
import net.ibizsys.paas.web.MDAjaxActionResult;
import net.ibizsys.paas.entity.IEntity;
import net.ibizsys.pswf.ctrlmodel.IWFExpBarModel;

public class ${appview.codeName}${srfclassname('${item.name}')}Handler extends ${pub.getBaseClassPKGCodeName()}.pswf.ctrlhandler.${srfclassname('${item.getControlType()}')}HandlerBase{
   
    protected ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model wfExpBarModel = null;
    public ${appview.codeName}${srfclassname('${item.name}')}Handler()  {
        super();
    }

     @Override
    protected void onInit() throws Exception
    {
        wfExpBarModel = (${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model)this.getViewController().getCtrlModel("${item.name}");
    	super.onInit();
     }

       @Override
	protected IWFExpBarModel getWFExpBarModel()
	{
		return this.getRealWFExpBarModel();
	}
    
    protected ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model getRealWFExpBarModel(){
    	return this.wfExpBarModel;
    }



}