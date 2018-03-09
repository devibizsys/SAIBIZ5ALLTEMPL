/*
 * 执行操作[${item.codeName}]
 */
public void ${methodname}(${de.codeName} ${srfparamname('${de.codeName}')}) throws Exception{

     DBCallResult dbCallResult =  getDAO().executeDEDBProc(null,ACTION_${item.name},${srfparamname('${de.codeName}')});
     if(dbCallResult.isOk())
    {
    	dbCallResult.getDataSet().cacheDataRow();
    }
   
    if(dbCallResult.isError())
    {
    	throw new ErrorException(Errors.INTERNALERROR,dbCallResult.getErrorInfo());
    }

<#if (item.getPSDEDBSysProc().getPSDBSysProcType().isReturnResult())>
     
    if(dbCallResult.getDataSet().getDataTableCount()==0 || dbCallResult.getDataSet().getDataTable(0).getCachedRowCount()==0)
    {
    	throw new ErrorException(Errors.INVALIDDATA);
    }
    
    DataObject.fromDataRow(${srfparamname('${de.codeName}')}, dbCallResult.getDataSet().getDataTable(0).getCachedRow(0));  
  
</#if>
}