<#if item.getRenderPSSysPFPlugin()??>
${item.getRenderPSSysPFPlugin().getCode("CODE2",pf.getId(),pfstyle.getId(),view,ctrl,item)}
<#else>
表单自定义部件没有定义插件
</#if>