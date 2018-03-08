<#assign dritem=item.getPSDEDRItem()>
<#assign drpitem=item.getParentPSDEFormDetail()>
new IBizFormDRPanel({id:this.getCId2()+'${item.uniqueId}'<#if drpitem??>,parentName:'${drpitem.name}'</#if>,name:'${item.name}'<#if item.getRefreshItems()??>,refreshitems:'${item.getRefreshItems()}'</#if>
<#if item.parentLayoutMode=='TABLE'>
<#if (item.contentWidth>1)>
,width:${item.contentWidth}
<#else>
,columnWidth: ${item.contentWidth}
</#if></#if>,height:${item.getContentHeight()},width:'100%',form:form,dritem:{
id:'${dritem.id}',embedviewid:'${item.getEmbedViewId()}',parentmode:{srfparenttype:'${dritem.itemType}'<#if ((dritem.itemType=='DER1N') || (dritem.itemType=='SYSDER1N'))>,srfder1nid:'${dritem.getPSDER1NName()}'</#if>}
}})