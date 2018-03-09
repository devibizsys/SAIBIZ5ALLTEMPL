package ${pub.getPKGCodeName()}.srv.${de.getPSSystemModule().codeName?lower_case}.demodel;

import net.ibizsys.paas.core.IDEDataExportItem;
import net.ibizsys.paas.demodel.DEDataExportItemModel;
import net.ibizsys.paas.datamodel.DataItemParamModel;

/**
 *  实体数据导出模型 [${item.name}]对象模型基类
 */ 
public class ${de.codeName}${item.codeName}DataExportModel extends ${pub.getBaseClassPKGCodeName()}.paas.demodel.DEDataExportModelBase{


   public ${de.codeName}${item.codeName}DataExportModel (){
        super();

        this.setId("${item.id}");
        this.setName("${item.codeName}");
   }

   @Override
   protected void prepareDEDataExportItemModels()throws Exception{
        IDEDataExportItem iDEDataExportItem = null;
<#if item.getPSDEDataExportItems()??>
<#list  item.getPSDEDataExportItems() as expitem>
        iDEDataExportItem = createDEDataExportItem("${expitem.name}");
        if(iDEDataExportItem==null){
             DEDataExportItemModel item = new DEDataExportItemModel();
             			 item.setName("${expitem.name}");
             			 item.setCaption("${srfjavastring('${expitem.caption}')}");
<#if expitem.getDataItemParams()?? >
<#list expitem.getDataItemParams() as dataitemparam>
        	DataItemParamModel  dataItemParam${dataitemparam_index?c} = new DataItemParamModel();
<#if dataitemparam.name??>
        	dataItemParam${dataitemparam_index?c}.setName("${dataitemparam.name}");
</#if>
<#if dataitemparam.format??>
			dataItemParam${dataitemparam_index?c}.setFormat("${srfxmlvalue('${dataitemparam.format}')}");
</#if>
			dataItemParam${dataitemparam_index?c}.setDataItem(item);
<#if (dataitemparam.getPSCodeList()??)>
                        //设置代码表 ${dataitemparam.getPSCodeList().name}
                        dataItemParam${dataitemparam_index?c}.setCodeListId("${dataitemparam.getPSCodeList().id}");
</#if>
			item.addDataItemParam(dataItemParam${dataitemparam_index?c});
</#list>
</#if>
 			item.init(this);
             iDEDataExportItem  = item ;
        }
        this.registerDEDataExportItem(iDEDataExportItem);
</#list>
</#if>

   }

}