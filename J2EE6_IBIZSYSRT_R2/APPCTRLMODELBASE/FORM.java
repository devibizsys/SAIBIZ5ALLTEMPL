import net.ibizsys.paas.control.form.FormError;
import net.ibizsys.paas.control.form.IFormItem;
import net.ibizsys.paas.control.form.FormItemError;

@EditForm(name="${item.name}",type="${item.controlType}",formitems={ 
<#list item.getFormItems() as formitem>
<#if (formitem_index>0)>,</#if> @FormItem(name="${formitem.name}",defname="${formitem.getDEFName()}"<#if formitem.getEnableCond()!=3>,enablecond=${formitem.getEnableCond()}</#if><#if (formitem.getCreateDVT()??) && (formitem.getCreateDVT()?length>0)>,createdvt="${formitem.getCreateDVT()}"</#if><#if (formitem.getCreateDV()??) && (formitem.getCreateDV()?length>0)>,createdv="${formitem.getCreateDV()}"</#if><#if (formitem.getUpdateDVT()??) && (formitem.getUpdateDVT()?length>0)>,updatedvt="${formitem.getUpdateDVT()}"</#if><#if (formitem.getUpdateDV()??) && (formitem.getUpdateDV()?length>0)>,updatedv="${formitem.getUpdateDV()}"</#if><#if (formitem.getCodeList()??)>,codelistcls="${pub.getPKGCodeName()}.srv.codelist.${formitem.getCodeList().codeName}CodeListModel"</#if>,caption="${formitem.getCaption()}"<#if (!formitem.isAllowEmpty())>,allowemtpy=false</#if>
<#if formitem.getDataItem()??>
,dataitem=@DataItem(dataitemparams={
<#if  formitem.getDataItem().getDataItemParams()??> 
<#list formitem.getDataItem().getDataItemParams() as dataitemparam>
<#if (dataitemparam_index>0)>,</#if> @DataItemParam(name="${dataitemparam.name}",format="${dataitemparam.format}")
</#list></#if>},name="${formitem.name}"<#if formitem.getDEField()??>,datatype=${formitem.getDEField().getStdDataType()}</#if>,format="${srfxmlvalue('${formitem.getDataItem().format}')}")
</#if>
)
</#list>
})

public class ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model extends ${pub.getBaseClassPKGCodeName()}.paas.ctrlmodel.${srfclassname('${item.getControlType()}')}ModelBase{
   
    public ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model()  {
        super();
        
        this.initAnnotation(${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model.class); 
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