import net.ibizsys.paas.control.drbar.DRBarItem;
import net.ibizsys.paas.control.drbar.DRBarRootItem;

public class  ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model extends ${pub.getBaseClassPKGCodeName()}.paas.ctrlmodel.${srfclassname('${item.getControlType()}')}ModelBase{
   
    public ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model()  {
        super();
    }
  
    @Override
    protected void onInit() throws Exception
    {
       super.onInit();
    }

    /**
     * 准备数据关系根节点
     * @param drBarRootItem
     * @throws Exception
     */
    @Override
    protected void onPrepareRootItem(DRBarRootItem drBarRootItem) throws Exception{

<#list item.getRootItem().getAllItems() as dritem> 
        //添加 ${dritem.text}
        DRBarItem drBarItem${dritem_index?c} = drBarRootItem.addItem("${dritem.id}","${dritem.getPId()}");
        drBarItem${dritem_index?c}.setText("${dritem.text}");
        drBarItem${dritem_index?c}.setDRViewId("${dritem.getDRViewId()}");
<#if dritem.isExpanded()>
        drBarItem${dritem_index?c}.setExpanded(true);
</#if>
<#list dritem.getViewParamNames() as viewparam>
        drBarItem${dritem_index?c}.setViewParam("${viewparam}","${dritem.getViewParam('${viewparam}')}");
</#list>
</#list>	
    }
}