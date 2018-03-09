import net.ibizsys.paas.control.grid.IGridColumn;
import net.ibizsys.paas.control.grid.IGridDataItem;
import net.ibizsys.paas.ctrlmodel.GridColumnModel;
import net.ibizsys.paas.ctrlmodel.GridDataItemModel;
/**
 * 实体[${de.logicName}]树表格[${item.name}]部件模型
 */
public class  ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model extends ${pub.getBaseClassPKGCodeName()}.paas.ctrlmodel.${srfclassname('${item.getControlType()}')}ModelBase{
   
    public ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model()  {
        super();
        this.setName("${item.name}");
        //设置父数据属性
        this.setParentDEField("${item.getTreePPSDEF().name}");
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
	 * 准备表格列模型
	 * @throws Exception
	 */
        @Override
	protected void prepareGridColumnModels()throws Exception
	{
            super.prepareGridColumnModels();
            IGridColumn iGridColumn = null;
<#list item.getGridColumns() as gridcolumn>
         //建立表格列 ${gridcolumn.caption} 
         iGridColumn = createGridColumn("${gridcolumn.getDataItemName()}");
         if(iGridColumn==null)
         {
        	 GridColumnModel gridColumnModel = new GridColumnModel();
        	 gridColumnModel.setName("${gridcolumn.getDataItemName()}");
        	 gridColumnModel.setDataItemName("${gridcolumn.getDataItemName()}");
        	 gridColumnModel.setCaption("${gridcolumn.caption}");
        	 gridColumnModel.init(this);
        	 iGridColumn = gridColumnModel;
         }
         this.registerGridColumn(iGridColumn);
</#list>	
	}
	
	/**
	 * 准备表格数据项模型
	 * @throws Exception
	 */
        @Override
	protected void prepareGridDataItemModels()throws Exception
	{
        	 super.prepareGridDataItemModels();
                 IGridDataItem iGridDataItem = null;
<#list item.getGridDataItems() as griddataitem>
         //建立数据项 ${griddataitem.name} 
		 iGridDataItem = createGridDataItem("${griddataitem.name}");
		 if(iGridDataItem == null)
		 {
			 GridDataItemModel gridDataItemModel = new GridDataItemModel();
			 gridDataItemModel.setName("${griddataitem.name}");
<#comment>
		dataItem${griddataitem_index?c}.setDataType(${dataitem.getDataType()});
</#comment>
<#list griddataitem.getDataItemParams() as dataitemparam>
        	DataItemParamModel  dataItemParam${dataitemparam_index?c} = new DataItemParamModel();
<#if dataitemparam.name??>
        	dataItemParam${dataitemparam_index?c}.setName("${dataitemparam.name}");
</#if>
<#if dataitemparam.format??>
			dataItemParam${dataitemparam_index?c}.setFormat("${srfxmlvalue('${dataitemparam.format}')}");
</#if>
			dataItemParam${dataitemparam_index?c}.setDataItem(gridDataItemModel);
<#if (dataitemparam.getPSCodeList()??)&&(dataitemparam.getPSCodeList().getCodeListType()=='DYNAMIC')>
                        //设置代码表 ${dataitemparam.getPSCodeList().name}
                        dataItemParam${dataitemparam_index?c}.setCodeListId("${dataitemparam.getPSCodeList().id}");
</#if>
			gridDataItemModel.addDataItemParam(dataItemParam${dataitemparam_index?c});
 </#list>
 			gridDataItemModel.init(this);
 			iGridDataItem = gridDataItemModel;
		 }
        this.registerGridDataItem(iGridDataItem);
</#list>
	}
 
}