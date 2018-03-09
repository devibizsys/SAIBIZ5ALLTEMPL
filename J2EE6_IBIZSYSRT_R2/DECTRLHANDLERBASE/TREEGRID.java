import net.ibizsys.paas.core.DEDataSetFetchContext;
import net.ibizsys.paas.ctrlmodel.IGridModel;
import net.ibizsys.paas.db.DBFetchResult;
import net.ibizsys.paas.web.AjaxActionResult;
import net.ibizsys.paas.web.MDAjaxActionResult;
import net.ibizsys.paas.entity.IEntity;


public class ${appview.codeName}${srfclassname('${item.name}')}Handler extends ${pub.getBaseClassPKGCodeName()}.paas.ctrlhandler.${srfclassname('${item.getControlType()}')}HandlerBase{

    protected ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model gridModel = null;
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
        gridModel = (${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model)this.getViewController().getCtrlModel("${item.name}");
    	super.onInit();
    	
    	
    }

       @Override
	protected IGridModel getGridModel()
	{
		return this.getRealGridModel();
	}
    
    protected ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model getRealGridModel(){
    	return this.gridModel;
    }

       protected ${de.codeName}Service getRealService(){
            return (${de.codeName}Service)this.getViewController().getService();
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
        
<#-- 加载草稿操作 -->
<#if item.getPSAjaxControlHandler().getDEActionName('loaddraft')??>
<#assign deactionname =item.getPSAjaxControlHandler().getDEActionName('loaddraft')>
<#if (deactionname?length>0)>
    @Override
    protected IEntity getDraftEntity()throws Exception
    {
    	${de.codeName} entity = new ${de.codeName}();
        getDraftEntity(entity);
        return entity;
    }

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
      @Override
    protected void removeEntity(Object objKeyValue)throws Exception
    {
    	${de.codeName} entity = new ${de.codeName}();
        entity.set(${de.codeName}.FIELD_${de.getKeyDEField().codeName?upper_case},objKeyValue);
        this.getRealService().executeAction(${de.codeName}Service.ACTION_${deactionname?upper_case},entity);
    }
</#if>
</#if>

<#if (appview.isEnableBatchAdd())>
<#assign dernn=de.getPSDERNN()>
     /**
	 * 获取草稿实体数据
	 * @return
	 * @throws Exception
	 */
        @Override
	protected IEntity getDraftEntity(String strParentType,String strTypeParam,String strParentKey,String strParentKey2) throws Exception
	{
            ${de.codeName} entity = new ${de.codeName}();
<#assign der1n=dernn.getFirstPSDER1N()>
<#assign der1n2=dernn.getSecondPSDER1N()>
           if ((StringHelper.compare(strParentType,"${der1n.getDERType()}",false)==0) && (StringHelper.compare(strTypeParam,"${der1n.name}",true)==0))
           {
                 entity.set(${de.codeName}.FIELD_${de.getPSDEField('${der1n2.getPickupDEFName()}').codeName?upper_case},strParentKey2);
                 this.getDraftEntity(entity);
                 return entity;
           }
<#assign der1n2=dernn.getFirstPSDER1N()>
<#assign der1n=dernn.getSecondPSDER1N()>
           if ((StringHelper.compare(strParentType,"${der1n.getDERType()}",false)==0) && (StringHelper.compare(strTypeParam,"${der1n.name}",true)==0))
           {
                 entity.set(${de.codeName}.FIELD_${de.getPSDEField('${der1n2.getPickupDEFName()}').codeName?upper_case},strParentKey2);
                  this.getDraftEntity(entity);
                 return entity;
           }

           throw new Exception("无法填充关系数据对象");
        }       

</#if>

<#if (item.getPSAjaxControlHandler().getTempMode()==2)>
        @Override
	public int getTempMode()
	{
	     return TEMPMODE_MINOR;
	}
</#if>

<#if (item.getPSAjaxControlHandler().getTempMode()==1)>
        @Override
	public int getTempMode()
	{
	     return TEMPMODE_MAJOR;
	}
</#if>
</#if>      




}