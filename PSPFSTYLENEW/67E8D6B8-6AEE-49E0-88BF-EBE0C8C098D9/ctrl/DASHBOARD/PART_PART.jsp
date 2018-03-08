<div class="${item.getColCssClass()}" id="<%=strCId%>${item.name}">
<#assign sysportlet=item.getPSSysPortlet()>
    <!-- BEGIN PORTLET-->
<#if item.getPortletType()=='CUSTOM'>
  <#if sysportlet.getPSSysPFPlugin()??>
    ${sysportlet.getPSSysPFPlugin().getCode("CODE",pf.getId(),pfstyle.getId(),view,ctrl,item)}
  <#else>
    没有指定绘制器
  </#if>
<#else>
   <div class="portlet light ">
   <#if sysportlet.isShowTitleBar()>
      <#if sysportlet.getTitlePSSysPFPlugin()??>
           ${sysportlet.getTitlePSSysPFPlugin().getCode("CODE",pf.getId(),pfstyle.getId(),view,ctrl,item)}
      <#else>
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-bar-chart font-green-sharp hide"></i>
				<span class="caption-subject font-green-sharp bold uppercase">${item.title}</span>
				<span class="caption-helper"></span>
			</div>
			<div class="actions">
			        <div class="btn-group btn-group-devided" data-toggle="buttons">
				       <label class="btn btn-transparent grey-salsa btn-circle btn-sm active">
				        <input type="radio" name="options" class="toggle" id="option1">New</label>
				      <label class="btn btn-transparent grey-salsa btn-circle btn-sm">
				     <input type="radio" name="options" class="toggle" id="option2">Returning</label>
			       </div>
		        </div>
	         </div>
     </#if>
   </#if>
	<div class="portlet-body">
		<div id="<%=strCId%>${item.name}_loading">
		</div>
		<div id="<%=strCId%>${item.name}_content" >
<#if item.getPortletType()=='CHART'>
			<div id="<%=strCId%>${item.name}_chart" class="chart">
			</div>
</#if>
<#if item.getPortletType()=='LIST'>
			<div id="<%=strCId%>${item.name}_list" class="scroller" <#if item.getHeight() gt 0>style="height: ${item.getHeight()?c}px;"</#if> data-always-visible="1" data-rail-visible="0">
			</div>
</#if>
		</div>
	</div>
	</div>
</#if>
	<!-- END PORTLET-->
</div>