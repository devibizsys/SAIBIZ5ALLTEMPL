/**
	 * 获取数据集合[${item.logicName}]
	 * @param iDEDataSetFetchContext
	 * @return
	 * @throws Exception
	 */
public DBFetchResult fetch${item.codeName}(IDEDataSetFetchContext iDEDataSetFetchContext) throws Exception{

    DBFetchResult dbFetchResult =  doServiceFetchWork(iDEDataSetFetchContext,DATASET_${item.name?upper_case},false);
   // dbFetchResult.getDataSet().cacheDataRow();
   // session.close();
    return dbFetchResult;
}

<#if de.isEnableTempData()>
        /**
	 * 获取数据集合[${item.logicName}]（临时数据模型）
	 * @param iDEDataSetFetchContext
	 * @return
	 * @throws Exception
	 */
public DBFetchResult fetchTemp${item.codeName}(IDEDataSetFetchContext iDEDataSetFetchContext) throws Exception{

    DBFetchResult dbFetchResult =  doServiceFetchWork(iDEDataSetFetchContext,DATASET_${item.name?upper_case},true);
<#comment>
   // dbFetchResult.getDataSet().cacheDataRow();
   // session.close();
</#comment>
    return dbFetchResult;
}
</#if>