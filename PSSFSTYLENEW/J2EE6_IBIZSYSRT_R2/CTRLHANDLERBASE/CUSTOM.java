<#if item.getPSAjaxControlHandler()??>
public class ${appview.codeName}${srfclassname('${item.name}')}Handler extends ${item.getHandler()}{
<#else>
public class ${appview.codeName}${srfclassname('${item.name}')}Handler extends ${pub.getBaseClassPKGCodeName()}.paas.ctrlhandler.CustomHandlerBase{
</#if>
   
    public ${appview.codeName}${srfclassname('${item.name}')}Handler()  {
        super();
    }

    @Override
    protected void onInit() throws Exception
    {
<#if  item.getCustomTag()?? && item.getCustomTag()?length gt 0>
        this.setCustomTag("${item.getCustomTag()}");
</#if>
<#if  item.getCustomTag2()?? && item.getCustomTag2()?length gt 0>
        this.setCustomTag2("${item.getCustomTag2()}");
</#if>
<#if  item.getPSDataEntity()?? >
        this.setDEName("${item.getPSDataEntity().name}");
</#if>
<#if  item.getPSDEDataSet()?? >
        this.setDEDataSetName("${item.getPSDEDataSet().name}");
</#if>
<#if  item.getPSDEAction()?? >
        this.setDEActionName("${item.getPSDEAction().name}");
</#if>

        super.onInit();
    }

   

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