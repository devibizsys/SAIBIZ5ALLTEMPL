<#assign db=view.getPSControl('dashboard')>
<#list db.getPSPortlets() as portlet>
var ${view.codeName}_${portlet.name} = ${view.codeName}_${portlet.name}Base.extend({});
</#list>

var ${view.codeName}Controller = ${view.codeName}ControllerBase.extend({});