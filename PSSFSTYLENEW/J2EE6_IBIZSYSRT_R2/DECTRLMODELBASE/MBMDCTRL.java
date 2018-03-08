import net.ibizsys.paas.ctrlmodel.ListDataItemModel;

/**
 * 列表控件模型基类
 */
public class  ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model extends ${pub.getBaseClassPKGCodeName()}.paas.ctrlmodel.ListModelBase{
   
    public ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model()  {
        super();
<#if item.getPagingSize() gt 0>
        this.setPageSize(${item.getPagingSize()?c});
</#if>        
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
      * 准备列表数据项模型
      * @throws Exception
      */
     protected void prepareListDataItemModels()throws Exception
     {
	 super.prepareListDataItemModels();
<#list item.getListDataItems() as listDataItem>
         // ${listDataItem.name} 
         ListDataItemModel listDataItem${listDataItem_index?c} = new ListDataItemModel();
<#if listDataItem.name??>
         listDataItem${listDataItem_index?c}.setName("${listDataItem.name}");
</#if>
         listDataItem${listDataItem_index?c}.setDataType(${listDataItem.getDataType()});
<#if listDataItem.format??>
         listDataItem${listDataItem_index?c}.setFormat("${srfxmlvalue('${listDataItem.format}')}");
</#if>

<#if (listDataItem.getPSCodeList()??)>
         //设置代码表 ${listDataItem.getPSCodeList().name}
        listDataItem${listDataItem_index?c}.setCodeListId("${listDataItem.getPSCodeList().id}");
</#if>
<#if listDataItem.getDataItemParams()?? >
       if(true){
<#list listDataItem.getDataItemParams() as dataitemparam>
        DataItemParamModel  dataItemParam${dataitemparam_index?c} = new DataItemParamModel();
<#if dataitemparam.name??>
        dataItemParam${dataitemparam_index?c}.setName("${dataitemparam.name}");
</#if>
<#if (dataitemparam.format??) && (dataitemparam.format?length gt 0)>
	dataItemParam${dataitemparam_index?c}.setFormat("${srfxmlvalue('${dataitemparam.format}')}");
</#if>
	dataItemParam${dataitemparam_index?c}.setDataItem(listDataItem${listDataItem_index?c});
        listDataItem${listDataItem_index?c}.addDataItemParam(dataItemParam${dataitemparam_index?c});
</#list>
        }
</#if>

         listDataItem${listDataItem_index?c}.init(this);
         this.registerListDataItem(listDataItem${listDataItem_index?c});
</#list>	
     }
 
}