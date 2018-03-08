${delogicnodeheader.code}
java.util.ArrayList<Object> args = new java.util.ArrayList<Object>();
<#if item.getPSDELogicNodeParams()??>
<#list item.getPSDELogicNodeParams() as nodeparam>
    //${nodeparam.name}
<#if nodeparam.getLogicNodeParamType()=='SFPLUGINPARAM'>
<#if nodeparam.getSrcValueType()=='WEBCONTEXT'>
    args.add(WebContext.getCurrent().getPostValue("${nodeparam.getSrcFieldName()}"));
</#if>
<#if nodeparam.getSrcValueType()=='SRCDLPARAM'>
    args.add(${srfparamname('${nodeparam.getSrcPSDELogicParam().codeName}')});
</#if>
<#if nodeparam.getSrcValueType()=='LOGICPARAM'>
    args.add(${srfparamname('${nodeparam.getSrcPSDELogicParam().codeName}')});
</#if>
<#if nodeparam.getSrcValueType()=='LOGICPARAMFIELD'>
    args.add(${srfparamname('${nodeparam.getSrcPSDELogicParam().codeName}')}.get("${nodeparam.getSrcFieldName()}"));
</#if>
<#if nodeparam.getSrcValueType()=='APPDATA'>
    args.add(WebContext.getCurrent().getAppDataValue("${nodeparam.getSrcFieldName()}"));
</#if>
<#if nodeparam.getSrcValueType()=='VIEWPARAM'>
    args.add(WebContext.getCurrent().getViewParamValue("${nodeparam.getSrcFieldName()}"));
</#if>
<#if nodeparam.getSrcValueType()=='APPLICATION'>
    args.add(WebContext.getCurrent().getGlobalValue("${nodeparam.getSrcFieldName()}"));
</#if>
<#if nodeparam.getSrcValueType()=='SESSION'>
    args.add(WebContext.getCurrent().getSessionValue("${nodeparam.getSrcFieldName()}"));
</#if>      
<#if nodeparam.getSrcValueType()=='DATACONTEXT'>
    args.add(net.ibizsys.paas.util.freemarker.DataContextMethod.getValue("${nodeparam.getSrcFieldName()}",sessionFactory));
</#if>
<#if nodeparam.getSrcValueType()=='NULLVALUE'>
    args.add(null);
</#if>   
<#if nodeparam.getSrcValueType()=='SRCVALUE'>
    args.add("${srfjavastring('${nodeparam.getSrcValue()}')}");
</#if> 
</#if> 
</#list>
</#if>    
<#if item.getPSSysSFPlugin()??>
    //调用后台服务插件[${item.getPSSysSFPlugin().getName()}]
    ${item.getPSSysSFPlugin().getCode("CODE")}
<#else>
    throw new java.lang.Exception("没有定义后台服务插件");
</#if>
${delogicnodebottom.code}