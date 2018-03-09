import net.ibizsys.paas.ctrlmodel.SearchFormItemModel;
import net.ibizsys.paas.ctrlmodel.DataItemModel;
import net.ibizsys.paas.control.form.FormError;
import net.ibizsys.paas.control.form.IFormItem;
import net.ibizsys.paas.control.form.FormItemError;

public class  ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model extends ${pub.getBaseClassPKGCodeName()}.paas.ctrlmodel.${srfclassname('${item.getControlType()}')}ModelBase{
   
    public ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model() {
        super();
        this.setName("${item.name}");
    }
 
    /**
      * 准备表单项模型
      * @throws Exception
      */
     protected void prepareFormItemModels()throws Exception
     {
	 super.prepareFormItemModels();
<#list item.getFormItems() as formitem>
         // ${formitem.name} 
         SearchFormItemModel formItem${formitem_index?c} = new SearchFormItemModel();
         formItem${formitem_index?c}.setName("${formitem.name}");
         formItem${formitem_index?c}.setDEFName("${formitem.getDEFName()}");
<#if formitem.getDataItem()??>
<#assign dataitem=formitem.getDataItem()>
         DataItemModel dataItem${formitem_index?c} = new DataItemModel(); 
<#if dataitem.name??>
         dataItem${formitem_index?c}.setName("${dataitem.name}");
</#if>
         dataItem${formitem_index?c}.setDataType(${dataitem.getDataType()});
<#if dataitem.format??>
         dataItem${formitem_index?c}.setFormat("${srfxmlvalue('${dataitem.format}')}");
</#if>
         formItem${formitem_index?c}.setDataItem(dataItem${formitem_index?c});
</#if>
         formItem${formitem_index?c}.init(this);
         this.registerFormItem(formItem${formitem_index?c});
</#list>	
     }

         /**
     * 填充表单值
     * @param iDataObject
     * @param bUpdate
     * @param bIgnoreEmpty
     * @param formError
     * @throws Exception
     */
    @Override
    protected void onFillInputValues(IDataObject iDataObject,boolean bUpdate,boolean bIgnoreEmpty,FormError formError)throws Exception
    {
    	  super.onFillInputValues(iDataObject,bUpdate,bIgnoreEmpty,formError);
          if(formError.hasError())
              return;

<#list form_fdlogics as fdlogic>
          ${fdlogic}
</#list>
 
    }
 
}