package ${pub.getPKGCodeName()}.srv.api;


import net.ibizsys.paas.api.RestServiceAPIActionModel;




/**
 * 子系统服务API客户端[${item.name}]基类
 *
 * !! 不要对此代码进行修改
 */
<#if item.getAPIType() == 'RESTFUL'>
public class ${item.codeName}APIClientModel extends ${pub.getBaseClassPKGCodeName()}.paas.api.RestServiceAPIClientModelBase {
</#if>

     public ${item.codeName}APIClientModel() throws Exception{
	 super();
         this.setId("${item.id}");
         this.setName("${item.name}");
         this.setUniqueTag("${item.codeName}");
         
         prepareAPIActions();
     }
     
<#if item.getDENames()?? && item.getPSSubSysServiceAPIMethods()??>     
     protected void prepareAPIActions()throws Exception{
    	 <#list item.getDENames() as dename>
    	 <#if dename?? && dename?length gt 0 >
    	 prepareAPIActions_${dename}();
    	 <#else>
    	 prepareAPIActionsUnknown();
    	 </#if>
    	 </#list>
	}
	<#list item.getDENames() as dename>
	<#if dename?? && dename?length gt 0 >
	<#assign unknownflag=0>
	protected void prepareAPIActions_${dename}()throws Exception{
	<#else>
	<#assign unknownflag=1>
	protected void prepareAPIActionsUnknown()throws Exception{
   	 </#if>	
     	<#list item.getPSSubSysServiceAPIMethods() as action>
     	<#assign outputflag=0>
   		<#if action.getDEName()?? && action.getDEName()?length gt 0>
   		<#if unknownflag==0 && action.getDEName() == dename>
   		<#assign outputflag=1>
   		</#if>
   		<#elseif unknownflag==1>
   		<#assign outputflag=1>
   		</#if>
   		<#if outputflag==1>
		RestServiceAPIActionModel action${action_index?c} = new RestServiceAPIActionModel();
		action${action_index?c}.setId("${action.id}");
<#if action.name ?? && action.name?length gt 0>              
		action${action_index?c}.setName("${action.name}");
</#if> 		
		action${action_index?c}.setUniqueTag("${action.getUniqueTag()}");
		action${action_index?c}.setActionType(RestServiceAPIActionModel.ACTIONTYPE_${action.getActionType()});
<#if action.getPSRESTfulAPI().getRequestMethod()??>
    action${action_index?c}.setRequestMethod(RestServiceAPIActionModel.REQUESTMETHOD_${action.getPSRESTfulAPI().getRequestMethod()});
</#if>
<#if action.getPSRESTfulAPI().getRequestPath()??>
		action${action_index?c}.setActionPath("${action.getPSRESTfulAPI().getRequestPath()}");
</#if>
<#if action.getKeyField()?? && action.getKeyField()?length gt 0>
	action${action_index?c}.setKeyField("${action.getKeyField()}");
</#if>
	this.registerServiceAPIAction(action${action_index?c});
	</#if>
</#list>
	}
	</#list>
</#if>     

}