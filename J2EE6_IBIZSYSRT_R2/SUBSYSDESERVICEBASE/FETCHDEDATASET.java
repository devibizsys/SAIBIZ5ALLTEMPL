<#if item.getExtendMode()==2>
/*
 * 获取数据集合[${item.codeName}]
 */
public DBFetchResult fetch${item.codeName}(IDEDataSetFetchContext iDEDataSetFetchContext) throws Exception{

    DBFetchResult dbFetchResult =  doServiceFetchWork(iDEDataSetFetchContext,DATASET_${item.name?upper_case},false);
   // dbFetchResult.getDataSet().cacheDataRow();
   // session.close();
    return dbFetchResult;
}

<#if de.isEnableTempData()>
/*
 * 获取数据集合[${item.codeName}]
 */
public DBFetchResult fetchTemp${item.codeName}(IDEDataSetFetchContext iDEDataSetFetchContext) throws Exception{

    DBFetchResult dbFetchResult =  doServiceFetchWork(iDEDataSetFetchContext,DATASET_${item.name?upper_case},true);
   // dbFetchResult.getDataSet().cacheDataRow();
   // session.close();
    return dbFetchResult;
}
</#if>
</#if>