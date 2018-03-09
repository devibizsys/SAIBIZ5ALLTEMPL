import net.ibizsys.paas.control.grid.IGridColumn;
import net.ibizsys.paas.control.grid.IGridDataItem;
import net.ibizsys.paas.control.grid.IGridEditItem;
import net.ibizsys.paas.ctrlmodel.GridColumnModel;
import net.ibizsys.paas.ctrlmodel.GridDataItemModel;
import net.ibizsys.paas.ctrlmodel.GridEditItemModel;

/**
 * 实体[${de.logicName}]数据表格[${item.name}]部件模型
 */
public class  ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model extends ${pub.getBaseClassPKGCodeName()}.paas.ctrlmodel.${srfclassname('${item.getControlType()}')}ModelBase{
   
    public ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model()  {
        super();
        this.setName("${item.name}");
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
         iGridColumn = createGridColumn("${gridcolumn.getName()?lower_case}");
         if(iGridColumn==null)
         {
        	 GridColumnModel gridColumnModel = new GridColumnModel();
        	 gridColumnModel.setName("${gridcolumn.getName()?lower_case}");
        	 gridColumnModel.setDataItemName("${gridcolumn.getDataItemName()}");
        	 gridColumnModel.setCaption("${gridcolumn.caption}");
                 <#if (gridcolumn.getPSCodeList()??)>
		 gridColumnModel.setCodeListId("${gridcolumn.getPSCodeList().getClassOrPkgName('CODELIST',pub)}");
		 </#if>

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
<#if griddataitem.isDataAccessAction()>
                         gridDataItemModel.setDataAccessAction(true);
</#if>
<#if ((griddataitem.getPrivilegeId()??)&&(griddataitem.getPrivilegeId()?length gt 0))>
			gridDataItemModel.setPrivilegeId("${griddataitem.getPrivilegeId()}");
			</#if>
<#if griddataitem.getDataItemParams()?? >
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
</#if>
 			gridDataItemModel.init(this);
 			iGridDataItem = gridDataItemModel;
		 }
        this.registerGridDataItem(iGridDataItem);
</#list>
	}
 
    /**
     * 准备表格编辑项模型
     * @throws Exception
     */
    protected void prepareGridEditItemModels()throws Exception
    {
    	IGridEditItem iGridEditItem = null;
<#list item.getGridEditItems() as gridedititem>
        // ${gridedititem.name} 
		iGridEditItem = this.createGridEditItem("${gridedititem.name?lower_case}");
		if(iGridEditItem==null)
		{
			GridEditItemModel gridEditItem = new GridEditItemModel();
			gridEditItem.setGrid(this);
			gridEditItem.setName("${gridedititem.name?lower_case}");
			gridEditItem.setDEFName("${gridedititem.getDEFName()}");
			<#if gridedititem.getEnableCond()!=3>
			gridEditItem.setEnableCond(${gridedititem.getEnableCond()?c});
			</#if>
			<#if gridedititem.getIgnoreInput()!=0>
			gridEditItem.setIgnoreInput(${gridedititem.getIgnoreInput()?c});
			</#if>
			<#if (gridedititem.getCreateDVT()??) && (gridedititem.getCreateDVT()?length>0)>
			gridEditItem.setCreateDVT("${gridedititem.getCreateDVT()}");
			</#if>
			<#if (gridedititem.getCreateDV()??) && (gridedititem.getCreateDV()?length>0)>
			gridEditItem.setCreateDV("${gridedititem.getCreateDV()}");
			</#if>
			<#if (gridedititem.getUpdateDVT()??) && (gridedititem.getUpdateDVT()?length>0)>
			gridEditItem.setUpdateDVT("${gridedititem.getUpdateDVT()}");
			</#if>
			<#if (gridedititem.getUpdateDV()??) && (gridedititem.getUpdateDV()?length>0)>
			gridEditItem.setUpdateDV("${gridedititem.getUpdateDV()}");
			</#if>
			<#if (gridedititem.getCodeList()??)>
			gridEditItem.setCodeListId("${gridedititem.getCodeList().getClassOrPkgName('CODELIST',pub)}");
			</#if>
                        <#if (gridedititem.getUserDictCatId()?length gt 0)>
			gridEditItem.setUserDictCatId("${gridedititem.getUserDictCatId()}");
			</#if>
			<#if (gridedititem.getCaption()??) && (gridedititem.getCaption()?length>0)>
			gridEditItem.setCaption("${gridedititem.getCaption()}");
			</#if>
			<#if (!gridedititem.isAllowEmpty())>
			gridEditItem.setAllowEmpty(false);
			</#if>
			<#if (gridedititem.isNeedCodeListConfig())>
			gridEditItem.setOutputCodeListConfig(true);
				<#if (gridedititem.getOutputCodeListConfigMode() gt 0)>
			gridEditItem.setOutputCodeListConfigMode(${gridedititem.getOutputCodeListConfigMode()?c});
				</#if>
			</#if>
			<#if ((gridedititem.getValueTranslator()??)&&(gridedititem.getValueTranslator()?length gt 0))>
			gridEditItem.setValueTranslator("${gridedititem.getValueTranslator()}");
			</#if>
			<#if ((gridedititem.getPrivilegeId()??)&&(gridedititem.getPrivilegeId()?length gt 0))>
			gridEditItem.setPrivilegeId("${gridedititem.getPrivilegeId()}");
			</#if>

			//设置数据项参数
			<#if gridedititem.getDataItem()??>
			<#assign dataitem=gridedititem.getDataItem()>
			DataItemModel dataItem = new DataItemModel(); 
			dataItem.setName("${gridedititem.name}");
	        <#if gridedititem.getDEField()??>
	        dataItem.setDataType(${gridedititem.getDEField().getStdDataType()});
	        </#if>
	        dataItem.setFormat("${srfxmlvalue('${gridedititem.getDataItem().format}')}");
                <#if (dataitem.getCodeListId()??)>
			dataItem.setCodeListId("${gridedititem.getCodeList().getClassOrPkgName('CODELIST',pub)}");
		</#if>
	        <#if dataitem.getDataItemParams()??> 
	        //注册参数
	        <#list dataitem.getDataItemParams() as dataitemparam>
	        DataItemParamModel dataItemParam${dataitemparam_index} = new DataItemParamModel();
	        dataItemParam${dataitemparam_index}.setName("${dataitemparam.name}");
	        dataItemParam${dataitemparam_index}.setFormat("${dataitemparam.format}");
	        dataItem.addDataItemParam(dataItemParam${dataitemparam_index});
	        </#list>
	        </#if>
			gridEditItem.setDataItem(dataItem);
			</#if>
			gridEditItem.init();
			iGridEditItem = gridEditItem;
		}
		this.registerGridEditItem(iGridEditItem);
		</#list> 
        	
    }


}