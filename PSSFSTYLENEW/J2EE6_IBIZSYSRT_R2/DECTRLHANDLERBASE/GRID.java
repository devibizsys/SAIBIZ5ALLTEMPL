import net.ibizsys.paas.core.DEDataSetFetchContext;
import net.ibizsys.paas.ctrlmodel.IGridModel;
import net.ibizsys.paas.db.DBFetchResult;
import net.ibizsys.paas.web.AjaxActionResult;
import net.ibizsys.paas.web.MDAjaxActionResult;
import net.ibizsys.paas.entity.IEntity;
import net.ibizsys.paas.sysmodel.ISystemRuntime;
import net.ibizsys.paas.ctrlhandler.IGridEditItemHandler;
import net.ibizsys.paas.ctrlhandler.IGridEditItemUpdateHandler;

/**
 * 应用视图[${appview.codeName}]表格[${item.name}]后台处理对象基类
 */
<#if item.getPSAjaxControlHandler()??>
public class ${appview.codeName}${srfclassname('${item.name}')}Handler extends ${item.getHandler()}{
<#else>
<#if item.view.isEnableWF()>
public class ${appview.codeName}${srfclassname('${item.name}')}Handler extends ${pub.getBaseClassPKGCodeName()}.pswf.ctrlhandler.WF${srfclassname('${item.getControlType()}')}HandlerBase{
<#else>
public class ${appview.codeName}${srfclassname('${item.name}')}Handler extends ${pub.getBaseClassPKGCodeName()}.paas.ctrlhandler.${srfclassname('${item.getControlType()}')}HandlerBase{
</#if>
</#if>

    protected ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model gridModel = null;
    public ${appview.codeName}${srfclassname('${item.name}')}Handler()  {
        super();
    }

    /* (non-Javadoc)
     * @see net.ibizsys.paas.ctrlhandler.CtrlHandlerBase#onInit()
     */ 
     @Override
    protected void onInit() throws Exception
    {
<#if item.isNoSort()> 
         this.setEnableUserSort(false);
</#if>
<#if item.getMinorSortPSDEF()??>
         this.setMinorSortField("${item.getMinorSortPSDEF().name}");
    	 this.setMinorSortDir("${item.getMinorSortDir()}");
</#if>
<#if item.isEnableRowEdit()> 
         this.setEnableRowEdit(true);
</#if>
<#if item.isEnableItemPrivilege()>
         this.setEnableItemPriv(true);
</#if>
<#if item.getPSAjaxControlHandler()??>
<#assign achandler=item.getPSAjaxControlHandler()>
<#if achandler.isEnableOrgDR()>
         this.setEnableOrgDR(true);
         this.setOrgDR(${achandler.getOrgDR()?c});
</#if>
<#if achandler.isEnableSecDR()>
         this.setEnableSecDR(true);
         this.setSecDR(${achandler.getSecDR()?c});
</#if>
<#if achandler.isEnableSecBC()>
         this.setEnableSecBC(true);
         this.setSecBC("${achandler.getSecBC()}");
</#if>
<#if achandler.isEnableUserDR()>
         this.setEnableUserDR(true);
</#if>
<#if achandler.getPSSysUserDR()??>
<#assign userDR=achandler.getPSSysUserDR()>
<#if userDR.getCustomMode()??>
         this.setCustomDRMode("${userDR.getCustomMode()}");
</#if>
</#if>
<#if achandler.getPSSysUserDR2()??>
<#assign userDR=achandler.getPSSysUserDR2()>
<#if userDR.getCustomMode()??>
         this.setCustomDRMode2("${userDR.getCustomMode()}");
</#if>
</#if>

<#if achandler.getPSDEDataExport()??>
        //设置数据导出处理[${achandler.getPSDEDataExport().name}]
        this.setDEDataExportId("${achandler.getPSDEDataExport().id}");
</#if>
<#if achandler.isEnableCache()>
        this.setEnableCache(true);
        this.setCacheScope(${achandler.getCacheScope()?c});
<#if achandler.getCacheTimeout() gt 0>
        this.setCacheTimeout(${achandler.getCacheTimeout()?c});
</#if>
</#if>
<#if achandler.getPSSysUniState()??>
        this.setUniStateId("${achandler.getPSSysUniState().id}");
</#if>
<#if achandler.getUniStateKeyValue()?? && (achandler.getUniStateKeyValue()?length gt 0)>
        this.setUniStateKeyValue("${achandler.getUniStateKeyValue()}");
</#if>
<#if achandler.getUniStateField()?? && (achandler.getUniStateField()?length gt 0)>
        this.setUniStateField("${achandler.getUniStateField()}");
</#if>
</#if>
        gridModel = (${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model)this.getViewController().getCtrlModel("${item.name}");
    	super.onInit();
    	

    }
       /* (non-Javadoc)
        * @see net.ibizsys.paas.ctrlhandler.GridHandlerBase#getGridModel()
        */
       @Override
	protected IGridModel getGridModel()
	{
		return this.getRealGridModel();
	}
    
    /**
     * 获取实际的表格模型对象
     * @return
     */  
    protected ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model getRealGridModel(){
    	return this.gridModel;
    }

    /**
     * 获取实际的实体服务对象
     * @return
     */
       protected ${de.codeName}Service getRealService(){
            return (${de.codeName}Service)this.getService();
       }

       /**
	 * 准备部件操作数据访问能力
	 * @throws Exception
	 */
        @Override
	protected void prepareDataAccessActions()throws Exception
	{
              super.prepareDataAccessActions();
<#if item.getPSAjaxControlHandler()??>
<#list item.getPSAjaxControlHandler().getAjaxActions() as ajaxAction>
             this.registerDataAccessAction("${ajaxAction}","${item.getPSAjaxControlHandler().getDataAccessAction('${ajaxAction}')}");
</#list>              
</#if> 
         }

    /* (non-Javadoc)
     * @see net.ibizsys.paas.ctrlhandler.MDCtrlHandlerBase#fetchDEDataSet(net.ibizsys.paas.core.DEDataSetFetchContext)
     */

      @Override
	protected DBFetchResult fetchDEDataSet(DEDataSetFetchContext deDataSetFetchContext) throws Exception
	{
<#if !appview.isPickupMode() || !de.isEnableTempData()>	
<#if (item.getPSAjaxControlHandler().getTempMode()>0)>
             return  this.getRealService().fetchTemp${item.getPSAjaxControlHandler().getPSDEDataSet().codeName}(deDataSetFetchContext);
<#else>
	     return  this.getRealService().fetch${item.getPSAjaxControlHandler().getPSDEDataSet().codeName}(deDataSetFetchContext);
</#if>
<#else>
             if(WebContext.isTempMode(this.getWebContext()))
                   return  this.getRealService().fetchTemp${item.getPSAjaxControlHandler().getPSDEDataSet().codeName}(deDataSetFetchContext);
             else
                   return  this.getRealService().fetch${item.getPSAjaxControlHandler().getPSDEDataSet().codeName}(deDataSetFetchContext);
</#if>
	}

<#if !appview.isPickupMode()>	
<#comment>
	@Override
	protected void removeEntities(String[] keys) throws Exception
	{
		ArrayList<${de.codeName}> list = new ArrayList<${de.codeName}>();
		for (String strKey : keys)
		{
			${de.codeName} entity = new ${de.codeName}();
			entity .set(${de.codeName}.FIELD_${de.getKeyPSDEField().codeName?upper_case}, strKey);
			list.add(entity);
		}
<#if (item.getPSAjaxControlHandler().getTempMode()>0)>
                this.getRealService().removeTemp(list);
<#else>
		this.getRealService().remove(list);
</#if>
	}
</#comment>
<#if item.isEnableRowEdit()>
	/**
	* 准备部件成员处理对象
	* @throws Exception
	*/
	 @Override
	protected void prepareCtrlItemHandlers()throws Exception
	{
	      super.prepareCtrlItemHandlers();
          ISystemRuntime iSystemRuntime = (ISystemRuntime)this.getSystemModel();	
	<#list item.getPSDEGridEditItems() as gridedititem>
	<#if (gridedititem.getItemHandlerType()??) && (gridedititem.getItemHandlerType()?length>0)>
	      //注册 '${gridedititem.name}'
	      IGridEditItemHandler  ${srfparamname('${gridedititem.name}')}Handler = (IGridEditItemHandler)iSystemRuntime.createObject("${pub.getPKGCodeName()}.${app.getAppFolder()?lower_case}.srv.${de.getPSSystemModule().codeName?lower_case}.ctrlhandler.${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}${srfclassname('${gridedititem.name}')}Handler");
	      ${srfparamname('${gridedititem.name}')}Handler.init(this.getGridModel(),this);
	      this.registerCtrlItemHandler(ITEMACTIONTYPE_GRIDEDITITEM+"${gridedititem.codeName}",${srfparamname('${gridedititem.name}')}Handler);
	
	</#if>
	</#list>
	
	<#if item.getPSDEGridEditItemUpdates()??>
	<#list item.getPSDEGridEditItemUpdates() as geiupdate>   
	       //注册表格编辑项更新 '${geiupdate.codeName}'
	      IGridEditItemUpdateHandler ${srfparamname('${geiupdate.codeName}')}Handler = (IGridEditItemUpdateHandler)iSystemRuntime.createObject("${pub.getPKGCodeName()}.${app.getAppFolder()?lower_case}.srv.${de.getPSSystemModule().codeName?lower_case}.ctrlhandler.${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}${srfclassname('${geiupdate.codeName}')}Handler");
	      ${srfparamname('${geiupdate.codeName}')}Handler.init(this.getGridModel(),this);
	      this.registerCtrlItemHandler(ITEMACTIONTYPE_GRIDEDITITEMUPDATE+"${geiupdate.codeName}",${srfparamname('${geiupdate.codeName}')}Handler);
	</#list>
	</#if>
	}
 
</#if>        
<#-- 加载草稿操作 -->
<#if item.getPSAjaxControlHandler().getDEActionName('loaddraft')??>
<#assign deactionname =item.getPSAjaxControlHandler().getDEActionName('loaddraft')>
<#if (deactionname?length>0)>
    /* (non-Javadoc)
     * @see net.ibizsys.paas.ctrlhandler.MDCtrlHandlerBase#getDraftEntity()
     */ 
    @Override
    protected IEntity getDraftEntity()throws Exception
    {
    	${de.codeName} entity = new ${de.codeName}();
        getDraftEntity(entity);
        return entity;
    }

    /**
     * 获取操作数据对象
     * @param entity
     * @throws Exception
     */ 
    protected void getDraftEntity(${de.codeName} entity)throws Exception
    {
    	this.getRealService().executeAction(${de.codeName}Service.ACTION_${deactionname?upper_case},entity);
    }
</#if>
</#if>

<#-- 加载操作 -->
<#if item.getPSAjaxControlHandler().getDEActionName('load')??>
<#assign deactionname =item.getPSAjaxControlHandler().getDEActionName('load')>
<#if (deactionname?length>0)>
    /* (non-Javadoc)
     * @see net.ibizsys.paas.ctrlhandler.MDCtrlHandlerBase#getEntity(java.lang.Object)
     */
    @Override
    protected IEntity getEntity(Object objKeyValue)throws Exception
    {
    	${de.codeName} entity = new ${de.codeName}();
        entity.set(${de.codeName}.FIELD_${de.getKeyDEField().codeName?upper_case},objKeyValue);
        this.getRealService().executeAction(${de.codeName}Service.ACTION_${deactionname?upper_case},entity);
        return entity;
    }
</#if>
</#if>


<#-- 建立操作 -->
<#if item.getPSAjaxControlHandler().getDEActionName('create')??>
<#assign deactionname =item.getPSAjaxControlHandler().getDEActionName('create')>
<#if (deactionname?length>0)>
    /* (non-Javadoc)
     * @see net.ibizsys.paas.ctrlhandler.MDCtrlHandlerBase#createEntity(net.ibizsys.paas.entity.IEntity)
     */
    @Override
    protected IEntity createEntity(IEntity iEntity)throws Exception
    {
        this.getRealService().executeAction(${de.codeName}Service.ACTION_${deactionname?upper_case},iEntity);
        return iEntity;
    }
</#if>
</#if>

<#-- 更新操作 -->
<#if item.getPSAjaxControlHandler().getDEActionName('update')??>
<#assign deactionname =item.getPSAjaxControlHandler().getDEActionName('update')>
<#if (deactionname?length>0)>
    /* (non-Javadoc)
     * @see net.ibizsys.paas.ctrlhandler.MDCtrlHandlerBase#updateEntity(net.ibizsys.paas.entity.IEntity)
     */
    @Override
    protected IEntity updateEntity(IEntity iEntity)throws Exception
    {
        this.getRealService().executeAction(${de.codeName}Service.ACTION_${deactionname?upper_case},iEntity);
        return iEntity;
    }
</#if>
</#if>

<#-- 删除操作 -->
<#if item.getPSAjaxControlHandler().getDEActionName('remove')??>
<#assign deactionname =item.getPSAjaxControlHandler().getDEActionName('remove')>
<#if (deactionname?length>0)>
    /* (non-Javadoc)
     * @see net.ibizsys.paas.ctrlhandler.MDCtrlHandlerBase#removeEntity(java.lang.Object)
     */
    @Override
    protected void removeEntity(Object objKeyValue)throws Exception
    {
    	${de.codeName} entity = new ${de.codeName}();
        entity.set(${de.codeName}.FIELD_${de.getKeyDEField().codeName?upper_case},objKeyValue);
        this.getRealService().executeAction(${de.codeName}Service.ACTION_${deactionname?upper_case},entity);
    }
</#if>
</#if>

<#if de.getDEType() == 3>
<#if (appview.isEnableBatchAdd())>
<#assign dernn=de.getPSDERNN()>
     /* (non-Javadoc)
      * @see net.ibizsys.paas.ctrlhandler.MDCtrlHandlerBase#getDraftEntity(java.lang.String,java.lang.String,java.lang.String,java.lang.String)
      */
      @Override
	protected IEntity getDraftEntity(String strParentType,String strTypeParam,String strParentKey,String strParentKey2) throws Exception
	{
            ${de.codeName} entity = new ${de.codeName}();
<#assign der1n=dernn.getFirstPSDER1N()>
<#assign der1n2=dernn.getSecondPSDER1N()>
           if ( (StringHelper.compare(strTypeParam,"${der1n.name}",true)==0))
           {
                 entity.set(${de.codeName}.FIELD_${de.getPSDEField('${der1n2.getPickupDEFName()}').codeName?upper_case},strParentKey2);
                 this.getDraftEntity(entity);
                 return entity;
           }
<#assign der1n2=dernn.getFirstPSDER1N()>
<#assign der1n=dernn.getSecondPSDER1N()>
           if ((StringHelper.compare(strTypeParam,"${der1n.name}",true)==0))
           {
                 entity.set(${de.codeName}.FIELD_${de.getPSDEField('${der1n2.getPickupDEFName()}').codeName?upper_case},strParentKey2);
                  this.getDraftEntity(entity);
                 return entity;
           }

           throw new Exception("无法填充关系数据对象");
        }       

</#if>
</#if>

<#if (item.getPSAjaxControlHandler().getTempMode()==2)>
        /* (non-Javadoc)
         * @see net.ibizsys.paas.ctrlhandler.MDCtrlHandlerBase#getTempMode()
         */
        @Override
	public int getTempMode()
	{
             //临时数据从模式
	     return TEMPMODE_MINOR;
	}
</#if>

<#if (item.getPSAjaxControlHandler().getTempMode()==1)>
        /* (non-Javadoc)
         * @see net.ibizsys.paas.ctrlhandler.MDCtrlHandlerBase#getTempMode()
         */
        @Override
	public int getTempMode()
	{
             //临时数据从模式
	     return TEMPMODE_MAJOR;
	}
</#if>
</#if>      
}