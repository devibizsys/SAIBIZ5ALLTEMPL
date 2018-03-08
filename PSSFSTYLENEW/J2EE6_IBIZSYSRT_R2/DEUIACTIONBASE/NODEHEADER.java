/*
* 执行操作[${item.name}]
*/
protected void execute${item.codeName}(HashMap<String,IEntity> paramMap) throws Exception
{
<#list item.getPSDELogic().getPSDELogicParams() as logicparam>
<#if logicparam.getParamPSDataEntity()??>
<#assign paramde=logicparam.getParamPSDataEntity()>
    ${paramde.codeName} ${srfparamname('${logicparam.codeName}')} = (${paramde.codeName})paramMap.get("${logicparam.codeName}");
<#else>
    SimpleEntity ${srfparamname('${logicparam.codeName}')} = (SimpleEntity )paramMap.get("${logicparam.codeName}");
</#if>
</#list>