{multiSelect:<#if ctrl.isSingleSelect()>false<#else>true</#if><#if ctrl.getWidth() gt 0>,width:${ctrl.getWidth()?c}</#if><#if ctrl.getHeight() gt 0>,height:${ctrl.getHeight()?c}</#if><#if ctrl.isHideHeader()>,hideHeader:true</#if>
,enablePaging:<#if ctrl.isEnablePagingBar()>true,pageSize:${ctrl.getPagingSize()?c}<#else>false</#if>,forceFit:<#if ctrl.isForceFit()>true<#else>false</#if>
,columns:[<#list ctrl.getPSDEGridColumns() as column>
<#if column_index gt 0>,</#if>{ "title" : <#if column.getCapPSLanguageRes()??>$IGM('${column.getCapPSLanguageRes().getLanResTag()}','${column.caption}')<#else>'${column.caption}'</#if><#if column.getAlign()!='LEFT'>,"sClass":'dt-body-${column.getAlign()?lower_case}'</#if><#if column.getWidthUnit()!='STAR'>,"width":${column.width}</#if>,"data" :'${column.getDataItemName()}'<#if column.isEnableSort()>,"orderable":true<#else>,"orderable":false</#if><#if ((column.getPSCodeList()??)&&(column.getPSCodeList().getCodeListType()=='STATIC')||(column.getRenderPSSysPFPlugin()??)||(column.getColumnType()=='UAGRIDCOLUMN'))>,'render':function(data,type,row){return me.render${ctrl.name}_${column.name?lower_case}(data,type,row,me);}</#if>}
</#list>
]
}