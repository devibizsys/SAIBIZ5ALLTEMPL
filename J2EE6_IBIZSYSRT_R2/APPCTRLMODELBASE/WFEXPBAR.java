import net.ibizsys.paas.control.expbar.ExpBarItem;
import net.ibizsys.paas.control.expbar.ExpBarRootItem;

public class  ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model extends ${pub.getBaseClassPKGCodeName()}.pswf.ctrlmodel.${srfclassname('${item.getControlType()}')}ModelBase{
   
    public ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model()  {
        super();
    }
  
    @Override
    protected void onInit() throws Exception
    {
        this.setWFModel(this.getViewController().getSystemModel().getWFModel("${item.getPSWorkflow().id}"));
  	super.onInit();
    }

    /**
     * 准备导航栏根节点
     * @param expBarRootItem
     * @throws Exception
     */
    @Override
    protected void onPrepareRootItem(ExpBarRootItem expBarRootItem) throws Exception{
<#list item.getRootItem().getAllItems() as expitem> 
        //添加 ${expitem.text}
        ExpBarItem expBarItem${expitem_index?c} = expBarRootItem.addItem("${expitem.id}","${expitem.getPId()}");
        expBarItem${expitem_index?c}.setText("${expitem.text}");
        expBarItem${expitem_index?c}.setExpViewId("${expitem.getExpViewId()}");
<#list expitem.getViewParamNames() as viewparam>
        expBarItem${expitem_index?c}.setViewParam("${viewparam}","${expitem.getViewParam('${viewparam}')}");
</#list>
</#list>	
    }
}