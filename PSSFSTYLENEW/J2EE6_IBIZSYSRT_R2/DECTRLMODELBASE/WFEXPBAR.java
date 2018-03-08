import net.ibizsys.paas.control.expbar.ExpBarItem;
import net.ibizsys.paas.control.expbar.ExpBarRootItem;

/**
 * 实体[${de.logicName}]流程导航栏[${item.name}]部件模型
 */
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
<#if ((expitem.getCounterId()??) && (expitem.getCounterId()?length gt 0))>
        expBarItem${expitem_index?c}.setCounterId("${expitem.getCounterId()}");
        expBarItem${expitem_index?c}.setCounterMode(${expitem.getCounterMode()?c});
</#if>
<#if (expitem.getTextLanResTag()??)>
        expBarItem${expitem_index?c}.setTextLanResTag("${expitem.getTextLanResTag()}");
</#if>
<#list expitem.getViewParamNames() as viewparam>
        expBarItem${expitem_index?c}.setViewParam("${viewparam}","${expitem.getViewParam('${viewparam}')}");
</#list>
</#list>	
    }
}