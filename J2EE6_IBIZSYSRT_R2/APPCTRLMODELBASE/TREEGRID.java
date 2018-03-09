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

public abstract class  ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}ModelBase extends ${pub.getBaseClassPKGCodeName()}.paas.ctrlmodel.${srfclassname('${item.getControlType()}')}ModelBase{
   
    public ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}ModelBase()  {
        super();
        
        this.initAnnotation(${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}ModelBase.class); 
    }
  
    @Override
    protected void onInit() throws Exception
    {
        //设置父数据属性
        this.setParentDEField("${item.getTreePPSDEF().name}");
	super.onInit();
    }
 
}