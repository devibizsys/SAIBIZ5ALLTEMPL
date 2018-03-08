import net.ibizsys.paas.core.DEDataSetFetchContext;
import net.ibizsys.paas.ctrlmodel.IListModel;
import net.ibizsys.paas.db.DBFetchResult;
import net.ibizsys.paas.web.AjaxActionResult;
import net.ibizsys.paas.web.MDAjaxActionResult;
import net.ibizsys.paas.entity.IEntity;

/**
 * 列表控件后台处理基类
 */
<#if item.getPSAjaxControlHandler()??>
public class ${appview.codeName}${srfclassname('${item.name}')}Handler extends ${item.getHandler()}{
<#else>
<#if item.view.isEnableWF()>
public class ${appview.codeName}${srfclassname('${item.name}')}Handler extends ${pub.getBaseClassPKGCodeName()}.pswf.ctrlhandler.WFListHandlerBase{
<#else>
public class ${appview.codeName}${srfclassname('${item.name}')}Handler extends ${pub.getBaseClassPKGCodeName()}.paas.ctrlhandler.ListHandlerBase{
</#if>
</#if>
    protected ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model listModel = null;
    public ${appview.codeName}${srfclassname('${item.name}')}Handler()  {
        super();
    }

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
        listModel = (${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model)this.getViewController().getCtrlModel("${item.name}");
    	super.onInit();
     }

       @Override
	protected IListModel getListModel()
	{
		return this.getRealListModel();
	}
    
    protected ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model getRealListModel(){
    	return this.listModel;
    }

       protected ${de.codeName}Service getRealService(){
            return (${de.codeName}Service)this.getService();
       }


      @Override
	protected DBFetchResult fetchDEDataSet(DEDataSetFetchContext deDataSetFetchContext) throws Exception
	{
              return  this.getRealService().fetch${item.getPSDEDataSet().codeName}(deDataSetFetchContext);
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

}