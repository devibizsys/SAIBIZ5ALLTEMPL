import net.ibizsys.paas.ctrlmodel.IEditFormModel;
import net.ibizsys.paas.entity.IEntity;


<#if item.view.isEnableWF()>
public class ${appview.codeName}${srfclassname('${item.name}')}Handler extends ${pub.getBaseClassPKGCodeName()}.pswf.ctrlhandler.WF${srfclassname('${item.getControlType()}')}HandlerBase{
<#else>
public class ${appview.codeName}${srfclassname('${item.name}')}Handler extends ${pub.getBaseClassPKGCodeName()}.paas.ctrlhandler.${srfclassname('${item.getControlType()}')}HandlerBase{
</#if>
   
    protected ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model editformModel = null;
    public ${appview.codeName}${srfclassname('${item.name}')}Handler()  {
        super();
    }

    @Override
    protected void onInit() throws Exception
    {
        super.onInit();
    	editformModel  = (${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model)this.getViewController().getCtrlModel("${item.name}");
    }
    
       @Override
	protected IEditFormModel getEditFormModel()
	{
		return this.getRealEditFormModel();
	}
    
    protected ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model getRealEditFormModel(){
    	return this.editformModel ;
    }

       protected ${de.codeName}Service getRealService(){
            return (${de.codeName}Service)this.getViewController().getService();
       }

       /**
	 * 准备部件成员处理对象
	 * @throws Exception
	 */
        @Override
	protected void prepareCtrlItemHandlers()throws Exception
	{
             super.prepareCtrlItemHandlers();

<#list item.getPSDEFormItems() as formitem>
<#if (formitem.getItemHandlerType()??) && (formitem.getItemHandlerType()?length>0)>
             //注册 '${formitem.name}'
             ${appview.codeName}${srfclassname('${item.name}')}${srfclassname('${formitem.name}')}Handler ${srfparamname('${formitem.name}')}Handler = new ${appview.codeName}${srfclassname('${item.name}')}${srfclassname('${formitem.name}')}Handler();
             ${srfparamname('${formitem.name}')}Handler.init(this.getEditFormModel(),this);
             this.registerCtrlItemHandler(ITEMACTIONTYPE_FORMITEM+"${formitem.codeName}",${srfparamname('${formitem.name}')}Handler);
	
</#if>
</#list>

<#if item.getPSDEFormItemUpdates()??>
<#list item.getPSDEFormItemUpdates() as fiupdate>   
              //注册表单项更新 '${fiupdate.codeName}'
             ${appview.codeName}${srfclassname('${item.name}')}${srfclassname('${fiupdate.codeName}')}Handler ${srfparamname('${fiupdate.codeName}')}Handler = new ${appview.codeName}${srfclassname('${item.name}')}${srfclassname('${fiupdate.codeName}')}Handler();
             ${srfparamname('${fiupdate.codeName}')}Handler.init(this.getEditFormModel(),this);
             this.registerCtrlItemHandler(ITEMACTIONTYPE_FORMITEMUPDATE+"${fiupdate.codeName}",${srfparamname('${fiupdate.codeName}')}Handler);
</#list>
</#if>
	}

<#-- 加载草稿操作 -->
<#if item.getPSAjaxControlHandler().getDEActionName('loaddraft')??>
<#assign deactionname =item.getPSAjaxControlHandler().getDEActionName('loaddraft')>
<#if (deactionname?length>0)>
      @Override
    protected IEntity getDraftEntity()throws Exception
    {
    	${de.codeName} entity = new ${de.codeName}();
        fillDefaultValues(entity ,false);
        this.getRealService().executeAction(${de.codeName}Service.ACTION_${deactionname?upper_case},entity);
        return entity;
    }
</#if>
</#if>

<#-- 加载草稿（从源数据）操作 -->                    
<#if item.getPSAjaxControlHandler().getDEActionName('loaddraftfrom')??>
<#assign deactionname =item.getPSAjaxControlHandler().getDEActionName('loaddraftfrom')>
<#if (deactionname?length>0)>
      @Override
     protected IEntity getDraftEntityFrom(Object objKeyValue)throws Exception
    {
    	${de.codeName} entity = new ${de.codeName}();
        entity.set(${de.codeName}.FIELD_${de.getKeyDEField().codeName?upper_case},objKeyValue);
        this.getRealService().executeAction(${de.codeName}Service.ACTION_${deactionname?upper_case},entity);
        return entity;
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
}