new IBizFormIFrame({id:this.getCId2()+'${item.uniqueId}',name:'${item.name}'<#if item.getRefreshItems()??>,refreshitems:'${item.getRefreshItems()}'</#if>
<#if item.parentLayoutMode=='TABLE'>
<#if (item.contentWidth>1)>
,width:${item.contentWidth}?c
<#else>
,columnWidth: ${item.contentWidth}?c
</#if></#if>,height:${item.getContentHeight()?c},width:'100%',form:form,url:'${item.getIFrameUrl()}'
})