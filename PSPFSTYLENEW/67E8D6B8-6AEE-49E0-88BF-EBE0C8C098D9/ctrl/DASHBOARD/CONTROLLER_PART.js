<#if item.getPortletType()=='CHART'>
this.${item.name}= new ${view.codeName}_${item.name}({ctrler:this,id:this.getCId2()+'${item.name}',name:'${item.name}',url:this.getBackendUrl()+'srfctrlid=${item.name}&',chartcfg:{}});
</#if>
<#if item.getPortletType()=='LIST'>
this.${item.name}= new ${view.codeName}_${item.name}({ctrler:this,id:this.getCId2()+'${item.name}',refname:'${item.name}',url:this.getBackendUrl()+'srfctrlid=${item.name}&'<#if item.getTimer() gt 0>,timer:${item.getTimer()?c}</#if>,listcfg:{id:this.getCId2()+'${item.name}_list',refname:'${item.name}_list',url:this.getBackendUrl()+'srfctrlid=${item.name}_list&',${content.code}}});
</#if>
<#if item.getPortletType()=='CUSTOM'>
this.${item.name}= new ${view.codeName}_${item.name}({ctrler:this,id:this.getCId2()+'${item.name}',name:'${item.name}',url:this.getBackendUrl()+'srfctrlid=${item.name}&'});
</#if>
<#if item.getPortletType()=='VIEW'>
this.${item.name}= new ${view.codeName}_${item.name}({ctrler:this,id:this.getCId2()+'${item.name}',name:'${item.name}',url:this.getBackendUrl()+'srfctrlid=${item.name}&'});
</#if>
<#if item.getPortletType()=='HTML'>
this.${item.name}= new ${view.codeName}_${item.name}({ctrler:this,id:this.getCId2()+'${item.name}',name:'${item.name}',url:this.getBackendUrl()+'srfctrlid=${item.name}&'});
</#if>