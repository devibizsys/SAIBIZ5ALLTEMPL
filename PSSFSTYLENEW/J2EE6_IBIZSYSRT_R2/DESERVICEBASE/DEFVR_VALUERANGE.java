checkFieldDataSetRule("${item.getDEFName()?upper_case}","${item.getMajorPSDataEntity().name}","${item.getMajorPSDEDataSet().name}", et, bTempMode,<#if item.getExtMajorPSDEField()??>"${item.getExtMajorPSDEField().name}"<#else>null</#if>,<#if item.getExtPSDEField()??>"${item.getExtPSDEField().name}"<#else>null</#if>,"${item.ruleInfo}",<#if item.isAlwaysCheck()>true<#else>false</#if>)