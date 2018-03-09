@DataView(name="${item.name}",type="${item.controlType}",dataitems={ 
<#list item.getDataViewDataItems() as dataviewdataitem>
<#if (dataviewdataitem_index>0)>,</#if> @DataItem(name="${dataviewdataitem.name}",dataitemparams={ 
<#list dataviewdataitem.getDataItemParams() as dataitemparam>
<#if (dataitemparam_index>0)>,</#if> @DataItemParam(name="${dataitemparam.name}",format="${dataitemparam.format}")
</#list>})
</#list>}
)

public class  ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model extends ${pub.getBaseClassPKGCodeName()}.paas.ctrlmodel.${srfclassname('${item.getControlType()}')}ModelBase{
   
    public ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model()  {
        super();
        
        this.initAnnotation(${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model.class); 
    }
 
}