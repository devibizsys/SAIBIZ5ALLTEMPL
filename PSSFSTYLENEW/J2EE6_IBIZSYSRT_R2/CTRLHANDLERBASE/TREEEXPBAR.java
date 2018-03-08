import net.ibizsys.paas.core.DEDataSetFetchContext;
import net.ibizsys.paas.ctrlmodel.IGridModel;
import net.ibizsys.paas.db.DBFetchResult;
import net.ibizsys.paas.web.AjaxActionResult;
import net.ibizsys.paas.web.MDAjaxActionResult;
import net.ibizsys.paas.entity.IEntity;

public class ${appview.codeName}${srfclassname('${item.name}')}Handler extends ${pub.getBaseClassPKGCodeName()}.paas.ctrlhandler.${srfclassname('${item.getControlType()}')}HandlerBase{

    protected ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model treeExpBarModel = null;
    public ${appview.codeName}${srfclassname('${item.name}')}Handler()  {
        super();
    }

     @Override
    protected void onInit() throws Exception
    {
    	super.onInit();
    	
    	gridModel = (${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model)this.getViewController().getCtrlModel("${item.name}");
    }
    
       @Override
	protected ITreeExpBarModel getTreeExpBarModel()
	{
		return this.getRealTreeExpBarModel();
	}
    
    protected ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model getRealTreeExpBarModel(){
    	return this.treeExpBarModel ;
    }

       protected ${de.codeName}Service getRealService(){
            return (${de.codeName}Service)this.getViewController().getService();
       }

}