${delogicnodeheader.code}

<#if item.getPSDELogicNodeParams()??>
<#list item.getPSDELogicNodeParams() as nodeparam>
    //${nodeparam.name}
<#if nodeparam.getLogicNodeParamType()=='SETPARAMVALUE'>
<#if nodeparam.getSrcValueType()=='WEBCONTEXT'>
    ${srfparamname('${nodeparam.getDstPSDELogicParam().codeName}')}.set("${nodeparam.getDstFieldName()}",WebContext.getCurrent().getPostValue("${nodeparam.getSrcFieldName()}"));
</#if>
<#if nodeparam.getSrcValueType()=='SRCDLPARAM'>
    ${srfparamname('${nodeparam.getDstPSDELogicParam().codeName}')}.set("${nodeparam.getDstFieldName()}",${srfparamname('${nodeparam.getSrcPSDELogicParam().codeName}')}.get("${nodeparam.getSrcFieldName()}"));
</#if>
<#if nodeparam.getSrcValueType()=='APPDATA'>
    ${srfparamname('${nodeparam.getDstPSDELogicParam().codeName}')}.set("${nodeparam.getDstFieldName()}",WebContext.getCurrent().getAppDataValue("${nodeparam.getSrcFieldName()}"));
</#if>
<#if nodeparam.getSrcValueType()=='VIEWPARAM'>
    ${srfparamname('${nodeparam.getDstPSDELogicParam().codeName}')}.set("${nodeparam.getDstFieldName()}",WebContext.getCurrent().getViewParamValue("${nodeparam.getSrcFieldName()}"));
</#if>
<#if nodeparam.getSrcValueType()=='APPLICATION'>
    ${srfparamname('${nodeparam.getDstPSDELogicParam().codeName}')}.set("${nodeparam.getDstFieldName()}",WebContext.getCurrent().getGlobalValue("${nodeparam.getSrcFieldName()}"));
</#if>
<#if nodeparam.getSrcValueType()=='SESSION'>
    ${srfparamname('${nodeparam.getDstPSDELogicParam().codeName}')}.set("${nodeparam.getDstFieldName()}",WebContext.getCurrent().getSessionValue("${nodeparam.getSrcFieldName()}"));
</#if>      
<#if nodeparam.getSrcValueType()=='DATACONTEXT'>
    ${srfparamname('${nodeparam.getDstPSDELogicParam().codeName}')}.set("${nodeparam.getDstFieldName()}",net.ibizsys.paas.util.freemarker.DataContextMethod.getValue("${nodeparam.getSrcFieldName()}",sessionFactory));
</#if>
<#if nodeparam.getSrcValueType()=='NONEVALUE'>
    ${srfparamname('${nodeparam.getDstPSDELogicParam().codeName}')}.remove("${nodeparam.getDstFieldName()}");
</#if>  
<#if nodeparam.getSrcValueType()=='NULLVALUE'>
    ${srfparamname('${nodeparam.getDstPSDELogicParam().codeName}')}.set("${nodeparam.getDstFieldName()}",null);
</#if>   
<#if nodeparam.getSrcValueType()=='SRCVALUE'>
    ${srfparamname('${nodeparam.getDstPSDELogicParam().codeName}')}.set("${nodeparam.getDstFieldName()}","${srfjavastring('${nodeparam.getSrcValue()}')}");
</#if> 
</#if> 
<#if nodeparam.getLogicNodeParamType()=='RESETPARAM'>
    ${srfparamname('${nodeparam.getDstPSDELogicParam().codeName}')}.reset();
</#if> 
<#if nodeparam.getLogicNodeParamType()=='COPYPARAM'>
    ${srfparamname('${nodeparam.getSrcPSDELogicParam().codeName}')}.copyTo(${srfparamname('${nodeparam.getDstPSDELogicParam().codeName}')},false);
</#if> 
</#list>
</#if>    

${delogicnodebottom.code}