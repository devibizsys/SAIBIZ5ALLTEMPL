<#assign title=ctrl.getPSDEChartTitle()>
<#assign xaxislist=ctrl.getPSDEChartAxesesByPos('x')>
<#assign yaxislist=ctrl.getPSDEChartAxesesByPos('y')>
<#assign customCode=''>
<#if ctrl.getPSSysPFPlugin()??>
<#assign customCode= ctrl.getPSSysPFPlugin().getCode("CODE2",pf.getId(),pfstyle.getId(),view,ctrl,null)> 
</#if>
{baseopt:{
title:{show:<#if title.isShowTitle()>true<#else>false</#if><#if title.getTitle()??>,text:'${title.getTitle()}'</#if><#if title.getSubTitle()??>,subtext:'${title.getSubTitle()}'</#if>}
,legend:{}
,toolbox:{ show : true,  feature : { dataView : {show: true, readOnly: true,title:$IGM('IBIZCHART.TOOLBAR.DATAVIEW','数据视图')}, saveAsImage : {show: true,title:$IGM('IBIZCHART.TOOLBAR.SAVEASIMAGE','保存为图片')} }}
}<#if customCode?length gt 0>,render:function(rows,ret,chart){${customCode}}</#if>}