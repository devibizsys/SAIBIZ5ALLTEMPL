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
    if (dbFetchResult.getDataSet() == null || (dbFetchResult.getDataSet().getDataTableCount() == 0))
    {
       throw new ErrorException(Errors.INVALIDDATA);
    }
    IDataTable iDataTable = dbFetchResult.getDataSet().getDataTable(0);
<#assign dstde=item.getDstPSDataEntity()>
<#assign dstaction=item.getDstPSDEAction()>
     IService service = ServiceGlobal.getService(${dstde.getClassOrPkgName('SERVICE',pub)}.class,sessionFactory );

    while(true)
    {
        int nCount = iDataTable.cacheRows(20);
        for(int i=0;i<nCount;i++)
        {
            IDataRow iDataRow = iDataTable.getCachedRow(i);
            SimpleEntity simpleEntity = new SimpleEntity ();

<#if item.getSrcPSDELogicParam()??>
            //复制源参数
            ${srfparamname('${item.getSrcPSDELogicParam().codeName}')}.copyTo(simpleEntity,false);
</#if> 
            DataObject.fromDataRow(simpleEntity, iDataRow,false);
            //执行操作
            service.executeAction(${dstde.getClassOrPkgName('SERVICE',pub)}.ACTION_${dstaction.name?upper_case},simpleEntity );
        }
        if(nCount<20)
            break;
    }

${delogicnodebottom.code}