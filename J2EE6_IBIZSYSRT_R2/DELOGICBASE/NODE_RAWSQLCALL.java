${delogicnodeheader.code}

String strSQL = "${srfsqlcode('${item.getParam("PARAM4","")}')}";
//准备参数
SqlParamList sqlParamList = new SqlParamList();

<#if item.getPSDELogicNodeParams()??>
<#list item.getPSDELogicNodeParams() as nodeparam>
    //${nodeparam.name}
<#if nodeparam.getLogicNodeParamType()=='SQLPARAM'>
<#if nodeparam.getSrcValueType()=='WEBCONTEXT'>
    sqlParamList.addObject(WebContext.getCurrent().getPostValue("${nodeparam.getSrcFieldName()}"));
</#if>
<#if nodeparam.getSrcValueType()=='SRCDLPARAM'>
    sqlParamList.addObject(${srfparamname('${nodeparam.getSrcPSDELogicParam().codeName}')}.get("${nodeparam.getSrcFieldName()}"));
</#if>
<#if nodeparam.getSrcValueType()=='APPDATA'>
    sqlParamList.addObject(WebContext.getCurrent().getAppDataValue("${nodeparam.getSrcFieldName()}"));
</#if>
<#if nodeparam.getSrcValueType()=='APPLICATION'>
    sqlParamList.addObject(WebContext.getCurrent().getGlobalValue("${nodeparam.getSrcFieldName()}"));
</#if>
<#if nodeparam.getSrcValueType()=='SESSION'>
    sqlParamList.addObject(WebContext.getCurrent().getSessionValue("${nodeparam.getSrcFieldName()}"));
</#if>      
<#if nodeparam.getSrcValueType()=='NONEVALUE'>
    sqlParamList.addObject(null);
</#if>  
<#if nodeparam.getSrcValueType()=='NULLVALUE'>
    sqlParamList.addObject(null);
</#if>   
<#if nodeparam.getSrcValueType()=='SRCVALUE'>
    sqlParamList.addObject("${srfjavastring('${nodeparam.getSrcValue()}')}");
</#if> 
</#if> 
</#list>
</#if>    

    DBCallResult dbFetchResult= getDAO(iActionContext).executeRawSql(null, strSQL , sqlParamList);
<#if (item.getParam("PARAM9",0)==1)>
    if (dbFetchResult.getDataSet() == null || (dbFetchResult.getDataSet().getDataTableCount() == 0))
    {
       throw new ErrorException(Errors.INVALIDDATA);
    }

    dbFetchResult.getDataSet().cacheDataRow();
    IDataTable iDataTable = dbFetchResult.getDataSet().getDataTable(0);
    if (iDataTable.getCachedRowCount() == 0)
    {
	throw new ErrorException(Errors.INVALIDDATA);
     }
<#assign dstparam=item.getDstPSDELogicParam()>
     //填充对象
      IDataRow iDataRow = iDataTable.getCachedRow(0);
<#if (item.getParam("PARAM7",1)==0)>
      DataObject.fromDataRow(${srfparamname('${dstparam.codeName}')}, iDataRow,true);
<#else>
      DataObject.fromDataRow(${srfparamname('${dstparam.codeName}')}, iDataRow,false);
</#if>
</#if>

${delogicnodebottom.code}