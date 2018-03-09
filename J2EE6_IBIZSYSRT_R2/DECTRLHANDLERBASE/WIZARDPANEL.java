import net.ibizsys.paas.ctrlmodel.IWizardPanelModel;
import net.ibizsys.paas.entity.IEntity;

/**
 * 向导面板控件后台处理基类
 */
<#if item.getPSAjaxControlHandler()??>
public class ${appview.codeName}${srfclassname('${item.name}')}Handler extends ${item.getHandler()}{
<#else>
public class ${appview.codeName}${srfclassname('${item.name}')}Handler extends ${pub.getBaseClassPKGCodeName()}.paas.ctrlhandler.${srfclassname('${item.getControlType()}')}HandlerBase{
</#if>

    protected ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model wizardPanelModel = null;
    public ${appview.codeName}${srfclassname('${item.name}')}Handler()  {
        super();
    }

     @Override
    protected void onInit() throws Exception
    {
        wizardPanelModel = (${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model)this.getViewController().getCtrlModel("${item.name}");
    	super.onInit();
    }

       @Override
	protected IWizardPanelModel getWizardPanelModel()
	{
		return this.getRealWizardPanelModel();
	}
    
    protected ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model getRealWizardPanelModel(){
    	return this.wizardPanelModel;
    }

       protected ${de.codeName}Service getRealService(){
            return (${de.codeName}Service)this.getService();
       }

    <#-- 初始化操作 -->
    <#if item.getPSDEWizard().getInitPSDEAction()??>
    <#assign deactionname =item.getPSDEWizard().getInitPSDEAction().getName()>
    <#if (deactionname?length>0)>
    @Override
    protected IEntity initWizard()throws Exception
    {
        ${de.codeName} entity = new ${de.codeName}();
        <#if deactionname?upper_case == "GET" || deactionname?upper_case == "UPDATE">
        String strKeyValue = WebContext.getKey(WebContext.getCurrent());
        if(StringHelper.isNullOrEmpty(strKeyValue))
        	strKeyValue = WebContext.getKeys(WebContext.getCurrent());
        entity.set("${de.getKeyPSDEField().getName()}", strKeyValue);
        </#if>
        this.getRealService().executeAction(${de.codeName}Service.ACTION_${deactionname?upper_case},entity);
        return entity ;
    }
    </#if>
    </#if>
    
    <#-- 完成操作 -->
    <#if item.getPSDEWizard().getFinishPSDEAction()??>
    <#assign deactionname =item.getPSDEWizard().getFinishPSDEAction().getName()>
    <#if (deactionname?length>0)>
    @Override
    protected IEntity finishWizard(IEntity iEntity)throws Exception
    {
        this.getRealService().executeAction(${de.codeName}Service.ACTION_${deactionname?upper_case},iEntity);
        return iEntity;
    }
    </#if>
    </#if>


}