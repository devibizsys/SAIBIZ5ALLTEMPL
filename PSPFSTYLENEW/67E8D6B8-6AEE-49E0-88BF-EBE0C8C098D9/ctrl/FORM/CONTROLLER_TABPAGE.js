<#assign drpitem=item.getParentPSDEFormDetail()>
new IBizFormTabPage({id:this.getCId2()+'${item.uniqueId}'<#if drpitem??>,parentName:'${drpitem.name}'</#if>,name:'${item.name}',form:form})