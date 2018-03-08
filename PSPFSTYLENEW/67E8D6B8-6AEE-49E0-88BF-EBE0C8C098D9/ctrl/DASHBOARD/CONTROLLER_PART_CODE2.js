<#if item.getPortletType()=='CHART'>
var ${view.codeName}_${item.name}Base = IBizChartPortlet.extend({
	construct: function(config) {
		arguments.callee.$.construct.call(this,config);
	}
});
</#if>
<#if item.getPortletType()=='LIST'>
var ${view.codeName}_${item.name}Base = IBizListPortlet.extend({
	construct: function(config) {
		arguments.callee.$.construct.call(this,config);
	}
});
</#if>
<#comment>自定义插件</#comment>
<#if item.getPortletType()=='CUSTOM'>
<#assign customCode=''>
<#if item.getPSSysPortlet().getPSSysPFPlugin()??>
<#assign customCode= item.getPSSysPortlet().getPSSysPFPlugin().getCode("CODE2",pf.getId(),pfstyle.getId(),view,ctrl,item)> 
</#if>
<#if customCode?length gt 0>
var ${view.codeName}_${item.name}Base = ${customCode}
<#else>
var ${view.codeName}_${item.name}Base =  IBizCustomPortlet.extend({
	construct: function(config) {
		arguments.callee.$.construct.call(this,config);
	}
});
</#if>
</#if>
<#comment>HTML插件</#comment>
<#if item.getPortletType()=='HTML'>
var ${view.codeName}_${item.name}Base = IBizHtmlPortlet.extend({
	construct: function(config) {
		arguments.callee.$.construct.call(this,config);
	}
});
</#if>
<#if item.getPortletType()=='VIEW'>
var ${view.codeName}_${item.name}Base = IBizViewPortlet.extend({
	construct: function(config) {
		arguments.callee.$.construct.call(this,config);
	}
});
</#if>