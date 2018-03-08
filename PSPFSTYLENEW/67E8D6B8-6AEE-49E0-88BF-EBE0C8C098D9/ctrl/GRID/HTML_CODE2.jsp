[<#list ctrl.getPSDEGridColumns() as column>
<#if column_index gt 0>,</#if>{"title" : "${column.caption}","align" : 'center',"valign" : 'middle',"data" : '${column.getDataItemName()}'
<#if column.isEnableSort()>,"orderable":true<#else>,"orderable":false</#if>}
</#list>
]