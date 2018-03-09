import net.ibizsys.paas.core.DEDataSetFetchContext;
import net.ibizsys.paas.ctrlmodel.ITreeModel;
import net.ibizsys.paas.db.DBFetchResult;
import net.ibizsys.paas.web.AjaxActionResult;
import net.ibizsys.paas.web.MDAjaxActionResult;
import net.ibizsys.paas.entity.IEntity;
import net.ibizsys.paas.ctrlmodel.ITreeExpBarModel;
import net.ibizsys.paas.ctrlhandler.ITreeHandler;
import net.ibizsys.paas.ctrlmodel.ITreeModel;

/**
 * 树导航视图控件后台处理基类
 */
public class ${appview.codeName}${srfclassname('${item.name}')}Handler extends ${pub.getBaseClassPKGCodeName()}.paas.ctrlhandler.${srfclassname('${item.getControlType()}')}HandlerBase{


    protected ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model treeExpBarModel = null;
    public ${appview.codeName}${srfclassname('${item.name}')}Handler()  {
        super();
    }

     @Override
    protected void onInit() throws Exception
    {
        treeExpBarModel  = (${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model)this.getViewController().getCtrlModel("${item.name}");
    	super.onInit();
     }

      @Override
	protected ITreeExpBarModel getTreeExpBarModel()
	{
		return this.getRealTreeExpBarModel();
	}
    
    protected ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model getRealTreeExpBarModel(){
    	return this.treeExpBarModel;
    }

    
<#if item.getPSDETree()??>
     @Override
     protected ITreeHandler getTreeHandler() throws Exception{
    	return (ITreeHandler )this.getViewController().getCtrlHandler("${item.name}_tree");
    }
    
     @Override
     protected ITreeModel getTreeModel() throws Exception{
    	return (ITreeModel)this.getViewController().getCtrlModel("${item.name}_tree");
    }

</#if>

}