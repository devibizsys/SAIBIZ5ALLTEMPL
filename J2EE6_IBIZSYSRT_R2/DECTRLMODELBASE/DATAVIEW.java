<#comment>
@DataView(name="${item.name}",type="${item.controlType}",dataitems={ 
<#list item.getDataViewDataItems() as dataviewdataitem>
<#if (dataviewdataitem_index>0)>,</#if> @DataItem(name="${dataviewdataitem.name}",dataitemparams={ 
<#list dataviewdataitem.getDataItemParams() as dataitemparam>
<#if (dataitemparam_index>0)>,</#if> @DataItemParam(name="${dataitemparam.name}",format="${dataitemparam.format}")
</#list>})
</#list>}
)
</#comment>

import net.ibizsys.paas.control.dataview.IDataViewDataItem;
import net.ibizsys.paas.ctrlmodel.DataViewDataItemModel;

/**
 * 实体[${de.logicName}]数据视图[${item.name}]部件模型
 */
public class  ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model extends ${pub.getBaseClassPKGCodeName()}.paas.ctrlmodel.${srfclassname('${item.getControlType()}')}ModelBase{
   
    public ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model()  {
        super();
        this.setName("${item.getName()}");      
        <#comment>
        this.initAnnotation(${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}ModelBase.class); 
        </#comment>
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
     * 准备数据视图项模型
     * @throws Exception
     */
    protected void prepareDataViewDataItems()throws Exception
    {
    	super.prepareDataViewDataItems();
    	IDataViewDataItem iDataViewDataItem = null;
    	<#list item.getDataViewDataItems() as dataviewdataitem>
        // ${dataviewdataitem.name} 
    	iDataViewDataItem = this.createDataViewDataItem("${dataviewdataitem.name}");
		if(iDataViewDataItem==null)
		{
			DataViewDataItemModel dataViewDataItem = new DataViewDataItemModel();
			dataViewDataItem.setDataView(this);
			dataViewDataItem.setName("${dataviewdataitem.name}");
                        dataViewDataItem.setDataType(${dataviewdataitem.getDataType()});
<#if dataviewdataitem.format??>
                        dataViewDataItem.setFormat("${srfxmlvalue('${dataviewdataitem.format}')}");
</#if>
	        //注册参数
			<#if dataviewdataitem.getDataItemParams()??>
	        <#list dataviewdataitem.getDataItemParams() as dataitemparam>
	        DataItemParamModel dataItemParam${dataitemparam_index} = new DataItemParamModel();
	        dataItemParam${dataitemparam_index}.setName("${dataitemparam.name}");
<#if (dataitemparam.format??) && (dataitemparam.format?length gt 0)>
	        dataItemParam${dataitemparam_index}.setFormat("${dataitemparam.format}");
</#if>
	        dataViewDataItem.addDataItemParam(dataItemParam${dataitemparam_index});
	        </#list>
	        </#if>
			dataViewDataItem.init();
			iDataViewDataItem = dataViewDataItem;
		}
		this.registerDataViewDataItem(iDataViewDataItem);
		</#list> 
    }
 
}