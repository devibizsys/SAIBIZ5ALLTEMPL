<#assign baseclassname='Portlet'>
<#if item.getPortletType()=='CHART'>
<#assign baseclassname='ChartPortlet'>
</#if>
<#if item.getPortletType()=='LIST'>
<#assign baseclassname='ListPortlet'>
</#if>
public class  ${app.getPKGCodeName()}${item.codeName}${srfclassname('${item.getControlType()}')}Model extends  ${item.getClassOrPkgName('MODEL',pub)}{
   
    public ${app.getPKGCodeName()}${item.codeName}${srfclassname('${item.getControlType()}')}Model()  {
        super();
    }
  
    @Override
    protected void onInit() throws Exception
    {
       super.onInit();
    }
   
}