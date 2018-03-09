<#assign dritem=item.getPSDEDRItem()>
new IBizFormDRPanel({id:this.getCId2()+'${item.uniqueId}',name:'${item.name}'
<#if item.parentLayoutMode=='TABLE'>
<#if (item.contentWidth>1)>
,width:${item.contentWidth}
<#else>
,columnWidth: ${item.contentWidth}
</#if></#if>,height:${item.getContentHeight()},width:'100%',form:form,dritem:{
id:'${dritem.id}',embedviewid:'${item.getEmbedViewId()}',parentmode:{srfparenttype:'${dritem.itemType}'<#if (dritem.itemType=='DER1N')>,srfder1nid:'${dritem.getPSDER1NName()}'</#if>}
}})