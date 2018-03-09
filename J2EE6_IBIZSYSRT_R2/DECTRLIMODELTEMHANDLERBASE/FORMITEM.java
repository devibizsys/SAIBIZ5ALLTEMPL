<#if item.getItemPSAjaxHandler()??>
public class ${de.codeName}${ctrl.codeName}${srfclassname('${ctrl.getControlType()}')}${srfclassname('${item.name}')}Handler extends ${item.getItemPSAjaxHandler().getHandlerObj()}{
<#else>
public class ${de.codeName}${ctrl.codeName}${srfclassname('${ctrl.getControlType()}')}${srfclassname('${item.name}')}Handler extends ${pub.getBaseClassPKGCodeName()}.paas.ctrlhandler.${item.getItemHandlerType()}FormItemHandlerBase{
</#if>   
   
    public ${de.codeName}${ctrl.codeName}${srfclassname('${ctrl.getControlType()}')}${srfclassname('${item.name}')}Handler()  {
        super();
    }


<#if item.getItemHandlerType()=='PickupText' || item.getItemHandlerType()=='AC'>

<#if (item.getPSDEFFormItem()??) && (item.getPSDEFFormItem().getRefPSDataEntity()??)>
<#assign refde=item.getPSDEFFormItem().getRefPSDataEntity()>
        /* (non-Javadoc)
	 * @see net.ibizsys.paas.ctrlhandler.PickupTextFormItemHandlerBase#getPickupDEService()
	 */
	@Override
	protected IService getPickupDEService() throws Exception
	{
		return (IService)ServiceGlobal.getService("${refde.getClassOrPkgName('SERVICE',pub)}",this.getViewController().getSessionFactory());
	}
<#else>
        /* (non-Javadoc)
	 * @see net.ibizsys.paas.ctrlhandler.PickupTextFormItemHandlerBase#getPickupDEService()
	 */
	@Override
	protected IService getPickupDEService() throws Exception
	{
		throw new Exception("没有实现");
	}  
 
        @Override
	protected AjaxActionResult onItemFetch() throws Exception
	{
		MDAjaxActionResult mdAjaxActionResult = new MDAjaxActionResult();
		fillFetchResult(mdAjaxActionResult);
		return mdAjaxActionResult ;
	}
	
	/**
	 * 填充数据获取结果
	 * @param fetchResult
	 * @param dt
	 * @throws Exception
	 */
	protected void fillFetchResult(MDAjaxActionResult fetchResult)throws Exception
	{
		
	}
</#if>
         
       /**
	 * 获取拾取的实体数据集合名称
	 * @return
	 */
        @Override
	protected String getPickupDEDataSetName()
	{
<#if item.getRefPSDEDataSet()??>
			//${item.getRefPSDEDataSet().logicName}
            return "${item.getRefPSDEDataSet().name?upper_case}";
<#else>
            return "DEFAULT";
</#if>
	}

<#if (item.getPSDEFFormItem()??) && (item.getPSDEFFormItem().getRefPSDEACModeName()??) && (item.getPSDEFFormItem().getRefPSDEACModeName()?length>0) >
        /**
	 * 获取拾取的实体自填模式名称
	 * @return
	 */
	protected String getPickupDEACModeName()
	{
               //"${item.getPSDEFFormItem().getRefPSDEACModeName()}";
               return "${item.getPSDEFFormItem().getRefPSDEACModeId()}";
	}
</#if>     

<#if (item.getPSDEFFormItem()??) >
<#assign defformitem=item.getPSDEFFormItem()>
<#if defformitem.isRefTempData()>
      @Override
	protected boolean isEnableTempData()
	{
		return true;
	}
</#if>
</#if>
</#if>

<#if item.getItemHandlerType()=='CodeList'>
<#if (item.getPSCodeList()??) >
<#assign codelist=item.getPSCodeList()>
       /* (non-Javadoc)
	 * @see net.ibizsys.paas.ctrlhandler.CodeListFormItemHandlerBase#getCodeList()
	 */
	@Override
	protected ICodeList getCodeList() throws Exception
	{
             
		return (ICodeList)CodeListGlobal.getCodeList("${codelist.getClassOrPkgName('CODELIST',pub)}");
	}
</#if>
</#if>
}