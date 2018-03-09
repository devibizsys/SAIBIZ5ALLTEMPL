import net.ibizsys.paas.control.form.FormError;
import net.ibizsys.paas.control.form.IFormItem;
import net.ibizsys.paas.control.form.FormItemError;
import net.ibizsys.paas.ctrlmodel.EditFormItemModel;
import net.ibizsys.paas.data.IDataObject;
import net.ibizsys.paas.entity.EntityError;
import net.ibizsys.paas.entity.IEntity;
import net.ibizsys.paas.service.IService;

/**
 * 实体[${de.logicName}]编辑表单[${item.name}]部件模型
 */
public class ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model extends ${pub.getBaseClassPKGCodeName()}.paas.ctrlmodel.${srfclassname('${item.getControlType()}')}ModelBase{
   
    public ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model()  {
        super();
        this.setName("${item.getName()}");      
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
     * 准备表单项模型
     * @throws Exception
     */
    protected void prepareFormItems()throws Exception
    {
    	super.prepareFormItems();
    	IFormItem iFormItem = null;
<#list item.getFormItems() as formitem>
        // ${formitem.name} 
		iFormItem = this.createFormItem("${formitem.name}");
		if(iFormItem==null)
		{
			EditFormItemModel formItem = new EditFormItemModel();
			formItem.setForm(this);
			formItem.setName("${formitem.name}");
			formItem.setDEFName("${formitem.getDEFName()}");
			<#if formitem.getEnableCond()!=3>
			formItem.setEnableCond(${formitem.getEnableCond()?c});
			</#if>
			<#if formitem.getIgnoreInput()!=0>
			formItem.setIgnoreInput(${formitem.getIgnoreInput()?c});
			</#if>
			<#if formitem.getWriteBackDEFMode()!=0>
			formItem.setWriteBackDEFMode(${formitem.getWriteBackDEFMode()?c});
			</#if>
			<#if (formitem.getCreateDVT()??) && (formitem.getCreateDVT()?length>0)>
			formItem.setCreateDVT("${srfjavastring('${formitem.getCreateDVT()}')}");
			</#if>
			<#if (formitem.getCreateDV()??) && (formitem.getCreateDV()?length>0)>
			formItem.setCreateDV("${srfjavastring('${formitem.getCreateDV()}')}");
			</#if>
			<#if (formitem.getUpdateDVT()??) && (formitem.getUpdateDVT()?length>0)>
			formItem.setUpdateDVT("${srfjavastring('${formitem.getUpdateDVT()}')}");
			</#if>
			<#if (formitem.getUpdateDV()??) && (formitem.getUpdateDV()?length>0)>
			formItem.setUpdateDV("${srfjavastring('${formitem.getUpdateDV()}')}");
			</#if>
			<#if (formitem.getCodeList()??)>
			formItem.setCodeListId("${formitem.getCodeList().getClassOrPkgName('CODELIST',pub)}");
			</#if>
			<#if (formitem.getUserDictCatId()?length gt 0)>
			formItem.setUserDictCatId("${formitem.getUserDictCatId()}");
			</#if>
			<#if (formitem.getCaption()??) && (formitem.getCaption()?length>0)>
			formItem.setCaption("${formitem.getCaption()}");
			</#if>
			<#if formitem.getCapPSLanguageRes()??>
			formItem.setCapLanResTag("${formitem.getCapPSLanguageRes().getLanResTag()}");
			</#if>
			<#if formitem.getInputTipSetId()??>
			formItem.setInputTipSetId("${srfjavastring('${formitem.getInputTipSetId()}')}");
				<#if formitem.getInputTipUniqueTag()??>
			formItem.setInputTipUniqueTag("${srfjavastring('${formitem.getInputTipUniqueTag()}')}");
				</#if> 
			<#else>
				<#if formitem.getInputTip()??>
			formItem.setInputTip("${srfjavastring('${formitem.getInputTip()}')}");
				</#if> 
				<#if formitem.getInputTipLanResTag()??>
			formItem.setInputTipLanResTag("${formitem.getInputTipLanResTag()}");
				</#if>
			</#if>
			<#if (!formitem.isAllowEmpty())>
			formItem.setAllowEmpty(false);
			</#if>
			<#if (formitem.isNeedCodeListConfig())>
			formItem.setOutputCodeListConfig(true);
				<#if (formitem.getOutputCodeListConfigMode() gt 0)>
			formItem.setOutputCodeListConfigMode(${formitem.getOutputCodeListConfigMode()?c});
				</#if>
			</#if>
			<#if ((formitem.getValueTranslator()??)&&(formitem.getValueTranslator()?length gt 0))>
			formItem.setValueTranslator("${formitem.getValueTranslator()}");
			</#if>
			<#if ((formitem.getPrivilegeId()??)&&(formitem.getPrivilegeId()?length gt 0))>
			formItem.setPrivilegeId("${formitem.getPrivilegeId()}");
			</#if>

			//设置数据项参数
			<#if formitem.getDataItem()??>
			<#assign dataitem=formitem.getDataItem()>
			DataItemModel dataItem = new DataItemModel(); 
			dataItem.setName("${formitem.name}");
	        <#if formitem.getDEField()??>
	        dataItem.setDataType(${formitem.getDEField().getStdDataType()});
	        </#if>
	        dataItem.setFormat("${srfxmlvalue('${formitem.getDataItem().format}')}");
			<#if (dataitem.getCodeListId()??)>
			dataItem.setCodeListId("${formitem.getCodeList().getClassOrPkgName('CODELIST',pub)}");
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
			formItem.setDataItem(dataItem);
			</#if>
			formItem.init();
			iFormItem = formItem;
		}
		this.registerFormItem(iFormItem);
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
<#if item.getPSDEFormItemVRs()??>   
    @Override
    protected void onTestValueRule(IService iService, IDataObject iDataObject, boolean bUpdate,EntityError entityError) throws Exception
	{
		EntityFieldError entityFieldError = null;
<#list item.getPSDEFormItemVRs() as formitemvr>
<#assign defvr=formitemvr.getPSDEFValueRule()>
          //检查表单项 ${formitemvr.getPSDEFormItemName()}
          entityFieldError = iService.testValueRule("${defvr.getPSDEField().name?upper_case}","${defvr.codeName?upper_case}",(IEntity) iDataObject, "${formitemvr.getPSDEFormItemName()?lower_case}", !bUpdate);
          if(entityFieldError!=null){entityError.register(entityFieldError);}
</#list>    	
		super.onTestValueRule( iService,  iDataObject,  bUpdate, entityError) ;
	}
</#if> 

	@Override
	protected Boolean onTestFormItemEnabled(IFormItem iFormItem, IDataObject iDataObject, boolean bUpdate) throws Exception {
		<#list form_fdlogics2 as fdlogic>
        ${fdlogic}
        </#list>
		return super.onTestFormItemEnabled(iFormItem, iDataObject, bUpdate);
	}

}