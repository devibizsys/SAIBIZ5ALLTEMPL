checkFieldValueRangeRule("${item.getDEFName()?upper_case}", et, bTempMode,<#if item.getMinValue()??>new Double(${item.getMinValue()?c})<#else>null</#if>,<#if item.isIncludeMinValue()>true<#else>false</#if>,<#if item.getMaxValue()??>new Double(${item.getMaxValue()?c})<#else>null</#if>,<#if item.isIncludeMaxValue()>true<#else>false</#if>,"${item.ruleInfo}",<#if item.isKeyCond()>false<#else>true</#if>)