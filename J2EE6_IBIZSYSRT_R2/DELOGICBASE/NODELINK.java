if(${linkcond}){
    execute${item.getDstPSDELogicNode().codeName}(iActionContext);
<#if item.getSrcPSDELogicNode().isParallelOutput()>
<#else>
    return;
</#if>
}