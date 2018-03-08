<#assign customCode=''>
<#if ctrl.getPSSysPFPlugin()??>
<#assign customCode= ctrl.getPSSysPFPlugin().getCode("CODE2",pf.getId(),pfstyle.getId(),view,ctrl,null)> 
</#if>
render:function(rows,ret,list){<#if customCode?length gt 0>${customCode}<#else>return '没有定义列表绘制器';</#if>}