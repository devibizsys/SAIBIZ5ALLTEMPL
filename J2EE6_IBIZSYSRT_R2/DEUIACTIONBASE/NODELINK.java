/**/
if(${linkcond}){
    execute${item.getDstPSDELogicNode().codeName}(paramMap);
<#if item.getSrcPSDELogicNode().isParallelOutput()>
<#else>
    return;
</#if>
}