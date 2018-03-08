{text: "${item.caption}", width: ${item.width}, dataIndex: '${item.dataItemName}' <#if !item.isEnableSort()>,sortable:false</#if>
<#if (item.getPSCodeList()??)&&(item.getPSCodeList().getCodeListType()=='STATIC')>,renderer:'render${ctrl.name}_${item.dataItemName}'</#if>
}