import net.ibizsys.paas.control.drctrl.DRCtrlItem;
import net.ibizsys.paas.control.drctrl.DRCtrlRootItem;

/**
 * 实体[${de.logicName}]数据关系栏[${item.name}]部件模型
 */
public class  ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model extends ${pub.getBaseClassPKGCodeName()}.paas.ctrlmodel.${srfclassname('${item.getControlType()}')}ModelBase{
   
    public ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model(){
        super();
    }
  
    @Override
    protected void onInit() throws Exception
    {
       super.onInit();
    }

<#if item.getDataEntity()??>
<#assign de=item.getDataEntity()>
      private ${de.getClassOrPkgName('DEMODEL',pub)} ${srfparamname('${de.codeName}')}DEModel;
     protected  ${de.getClassOrPkgName('DEMODEL',pub)} get${de.codeName}DEModel() {
            if(this.${srfparamname('${de.codeName}')}DEModel==null){
                try
                {
                     this.${srfparamname('${de.codeName}')}DEModel = (${de.getClassOrPkgName('DEMODEL',pub)})DEModelGlobal.getDEModel("${de.getClassOrPkgName('DEMODEL',pub)}");
                }
                catch(Exception ex)
                {
                }
            }
           return this.${srfparamname('${de.codeName}')}DEModel;
      }

      @Override
      public IDataEntityModel getDEModel() {
          return this.get${de.codeName}DEModel();
      }
</#if>

    /**
     * 准备数据关系根节点
     * @param drCtrlRootItem
     * @throws Exception
     */
    @Override
    protected void onPrepareRootItem(DRCtrlRootItem drCtrlRootItem) throws Exception{

<#list item.getRootItem().getAllItems() as dritem> 
        //添加 ${dritem.text}
        DRCtrlItem drCtrlItem${dritem_index?c} = drCtrlRootItem.addItem("${dritem.id}","${dritem.getPId()}");
        drCtrlItem${dritem_index?c}.setText("${dritem.text}");
        drCtrlItem${dritem_index?c}.setDRViewId("${dritem.getDRViewId()}");
<#if dritem.isExpanded()>
        drCtrlItem${dritem_index?c}.setExpanded(true);
</#if>
<#if (dritem.getTextLanResTag()??)>
        drCtrlItem${dritem_index?c}.setTextLanResTag("${dritem.getTextLanResTag()}");
</#if>
<#if dritem.getIconPath()??>
        drCtrlItem${dritem_index?c}.setIconPath("${dritem.getIconPath()}");
</#if>
<#if dritem.getIconCls()??>
        drCtrlItem${dritem_index?c}.setIconCls("${dritem.getIconCls()}");
</#if>
<#if (dritem.getCounterId()??) && (dritem.getCounterId()?length gt 0)>
        drCtrlItem${dritem_index?c}.setCounterId("${dritem.getCounterId()}");
</#if>
<#if dritem.getEnableMode()??>
        drCtrlItem${dritem_index?c}.setEnableMode("${dritem.getEnableMode()}");
</#if>
<#if dritem.getTestEnableDEActionName()??>
        drCtrlItem${dritem_index?c}.setTestEnableDEActionName("${dritem.getTestEnableDEActionName()}");
</#if>
<#if dritem.getTestEnableDEOPPriv()??>
        drCtrlItem${dritem_index?c}.setTestEnableDEOPPriv("${dritem.getTestEnableDEOPPriv()}");
</#if>
<#list dritem.getViewParamNames() as viewparam>
        drCtrlItem${dritem_index?c}.setViewParam("${viewparam}","${dritem.getViewParam('${viewparam}')}");
</#list>
</#list>	
    }
}