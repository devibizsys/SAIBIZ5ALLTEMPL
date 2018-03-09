import net.ibizsys.paas.core.DEDataSetFetchContext;
import net.ibizsys.paas.ctrlmodel.IDashboardModel;
import net.ibizsys.paas.db.DBFetchResult;
import net.ibizsys.paas.web.AjaxActionResult;
import net.ibizsys.paas.web.MDAjaxActionResult;
import net.ibizsys.paas.entity.IEntity;

/**
 * 应用视图[${appview.title}]数据看板${item.name}部件后台处理对象
 */
public class ${appview.codeName}${srfclassname('${item.name}')}Handler extends ${pub.getBaseClassPKGCodeName()}.paas.ctrlhandler.${srfclassname('${item.getControlType()}')}HandlerBase{
   
    protected ${appview.codeName}${srfclassname('${item.name}')}Model dashboardModel = null;
    public ${appview.codeName}${srfclassname('${item.name}')}Handler()  {
        super();
    }

     @Override
    protected void onInit() throws Exception
    {
    	super.onInit();
    	
    	dashboardModel  = (${appview.codeName}${srfclassname('${item.name}')}Model)this.getViewController().getCtrlModel("${item.name}");
    }
    
       @Override
	protected IDashboardModel getDashboardModel()
	{
		return this.getRealDashboardModel();
	}
    
    protected ${appview.codeName}${srfclassname('${item.name}')}Model getRealDashboardModel(){
    	return this.dashboardModel;
    }



}