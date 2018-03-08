@Grid(name="${item.name}",type="${item.controlType}",gridcolumns={ 
<#list item.getGridColumns() as gridcolumn>
<#if (gridcolumn_index>0)>,</#if> @GridColumn(caption="${gridcolumn.caption}",dataitemname="${gridcolumn.getDataItemName()}")
</#list>},griddataitems={ 
<#list item.getGridDataItems() as griddataitem>
<#if (griddataitem_index>0)>,</#if> @DataItem(name="${griddataitem.name}",dataitemparams={ 
<#list griddataitem.getDataItemParams() as dataitemparam>
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