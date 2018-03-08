/**
  * 执行逻辑处理[${item.name}]
  * @Param iActionContext 处理上下文参数
  */
protected void execute${item.codeName}(IActionContext iActionContext) throws Exception
{
<#list item.getPSDELogic().getPSDELogicParams() as logicparam>
<#if logicparam.getParamPSDataEntity()??>
<#assign paramde=logicparam.getParamPSDataEntity()>
    ${paramde.getClassOrPkgName('ENTITY',pub)} ${srfparamname('${logicparam.codeName}')} = (${paramde.getClassOrPkgName('ENTITY',pub)})iActionContext.getParam("${logicparam.codeName}");
<#else>
    SimpleEntity ${srfparamname('${logicparam.codeName}')} = (SimpleEntity )iActionContext.getParam("${logicparam.codeName}");
</#if>
</#list>
    SessionFactory sessionFactory = iActionContext.getSessionFactory();