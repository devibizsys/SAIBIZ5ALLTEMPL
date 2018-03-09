${delogicnodeheader.code}

<#if item.getPSDELogicNodeParams()??>
<#list item.getPSDELogicNodeParams() as nodeparam>
<#if nodeparam.getLogicNodeParamType()=='SETPARAMVALUE'>
<#if nodeparam.getSrcValueType()=='WEBCONTEXT'>
    ${srfparamname('${nodeparam.getDstPSDELogicParam().codeName}')}.set("${nodeparam.getDstFieldName()}",WebContext.getCurrent().getPostValue("${nodeparam.getSrcFieldName()}"));
</#if>
<#if nodeparam.getSrcValueType()=='SRCDLPARAM'>
    ${srfparamname('${nodeparam.getDstPSDELogicParam().codeName}')}.set("${nodeparam.getDstFieldName()}",${srfparamname('${nodeparam.getSrcPSDELogicParam().codeName}')}.get("${nodeparam.getSrcFieldName()}"));
</#if>
</#if>       
</#list>
</#if>    

${delogicnodebottom.code}