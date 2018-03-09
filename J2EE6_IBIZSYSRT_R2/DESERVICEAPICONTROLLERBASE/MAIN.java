package ${pub.getPKGCodeName()}.api.${api.codeName?lower_case}.controller;

import java.util.ArrayList;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;

import net.ibizsys.paas.api.IRestServiceWork;
import net.ibizsys.paas.api.RestCallResult;
import net.ibizsys.paas.core.DEDataSetFetchContext;
import net.ibizsys.paas.core.Errors;
import net.ibizsys.paas.core.IDEDataSetFetchContext;
import net.ibizsys.paas.data.DataObject;
import net.ibizsys.paas.db.DBFetchResult;
import net.ibizsys.paas.db.IDataRow;
import net.ibizsys.paas.db.ISelectContext;
import net.ibizsys.paas.db.SelectContext;
import net.ibizsys.paas.demodel.DEModelGlobal;
import net.ibizsys.paas.demodel.IDataEntityModel;
import net.ibizsys.paas.exception.ErrorException;
import net.ibizsys.paas.service.IService;
import net.ibizsys.paas.service.ServiceGlobal;
import net.ibizsys.paas.sysmodel.ISystemModel;
import net.ibizsys.paas.sysmodel.SysModelGlobal;
import net.sf.json.JSONObject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ${pub.getPKGCodeName()}.srv.${sys.codeName}SysModel;

<#if item.getDataEntity()??>
<#assign de=item.getDataEntity()>
<#assign entityname=de.getClassOrPkgName('ENTITY',pub)>
</#if>

/**
 * 实体服务API[${item.name}]控制类基类
 *
 * !! 不要对此代码进行修改
 */
@RestController
@RequestMapping("/api/${api.codeName?lower_case}/v${api.getAPIVersion()?c}/${item.codeName?lower_case}")
public class ${item.codeName}RestController extends ${pub.getBaseClassPKGCodeName()}.paas.api.DERestControllerBase{

     public ${item.codeName}RestController() throws Exception{
	 super();
         this.setId("${item.id}");
     }
     
     <#list item.getPSDEServiceAPIMethods() as action>
     <#if action.getActionType()??>
     <#if action.getActionType() == 'DEACTION'>
     <#assign restapi=action.getPSRESTfulAPI()>
     @RequestMapping(<#if restapi.getRequestParamType()=='FIELD'><#if restapi.getRequestPath()??>value = "${restapi.getRequestPath()}/{srfkey}"<#else>value = "/{srfkey}"</#if>, <#else><#if restapi.getRequestPath()??>value = "${restapi.getRequestPath()}", </#if></#if>method = RequestMethod.${restapi.getRequestMethod()} , produces = { "application/json" })
  	 @ResponseBody
  	 String <#if action.codeName??>${srfmethodname('${action.codeName}')}<#else>${srfmethodname('${action.getPSDEAction().codeName}')}</#if> (<#if restapi.getRequestParamType()=='FIELD'>@PathVariable("srfkey") final String strKey,<#elseif restapi.getRequestParamType()=='ENTITY'>@RequestBody final String strBody,</#if>HttpServletRequest req,Principal principal) {
  		return this.doRestServiceWork(new IRestServiceWork() {
  			@Override
  			public void execute(RestCallResult callResult) throws Exception {
  				${entityname} ${srfparamname('${de.codeName}')} = new ${entityname}();
  				<#if restapi.getRequestParamType()=='FIELD'>
  	    		${srfparamname('${de.codeName}')}.set("${restapi.getRequestField()}",strKey);
  	    		<#elseif restapi.getRequestParamType()=='ENTITY'>
  	    		JSONObject jo = JSONObject.fromString(strBody);
	    		DataObject.fromJSONObject(${srfparamname('${de.codeName}')}, jo);
  	    		</#if>
  	    		get${de.codeName}Service().${srfmethodname('${action.getPSDEAction().codeName}')}(${srfparamname('${de.codeName}')});
  	    		${srfparamname('${de.codeName}')}.fillJSONObject(callResult.getItem(true), false);
  			}
  		},req,principal);
  	}
    </#if>
    </#if>
    </#list>
     
<#comment>     
     
<#list item.getPSDEServiceAPIMethods() as action>
<#if action.getActionType()??>
<#if action.getActionType() == 'DEACTION'>
<#assign restapi=action.getPSRESTfulAPI()>
<#if restapi.getRequestMethod() == 'GET'>
	@RequestMapping(<#if restapi.getRequestPath()??>value = "${restapi.getRequestPath()}/{srfkey}"<#else>value = "/{srfkey}"</#if> , method = RequestMethod.GET , produces = { "application/json" })
	@ResponseBody
	String <#if action.codeName??>${srfmethodname('${action.codeName}')}<#else>${srfmethodname('${action.getPSDEAction().codeName}')}</#if> (@PathVariable("srfkey") final String strKey,HttpServletRequest req,Principal principal) {
		return this.doRestServiceWork(new IRestServiceWork() {
			@Override
			public void execute(RestCallResult callResult) throws Exception {
	    		${entityname} ${srfparamname('${de.codeName}')} = new ${entityname}();
	    		${srfparamname('${de.codeName}')}.set("${de.getKeyPSDEField().getName()?upper_case}",strKey);
	    		get${de.codeName}Service().${srfmethodname('${action.getPSDEAction().codeName}')}(${srfparamname('${de.codeName}')});
	    		if(callResult.getItem(false)==null)
	    			${srfparamname('${de.codeName}')}.fillJSONObject(callResult.getItem(true), false);
			}
		},req,principal);
	}
</#if>
</#if>
</#if>
</#list>
     
<#list item.getPSDEServiceAPIMethods() as action>
<#if action.getActionType()??>
<#if action.getActionType() == 'DEACTION'>
<#assign restapi=action.getPSRESTfulAPI()>
<#if restapi.getRequestMethod() == 'POST'>
	@RequestMapping(<#if restapi.getRequestPath()??>value = "${restapi.getRequestPath()}" , </#if>method = RequestMethod.POST , produces = { "application/json" })
	@ResponseBody
	String <#if action.codeName??>${srfmethodname('${action.codeName}')}<#else>${srfmethodname('${action.getPSDEAction().codeName}')}</#if> (@RequestBody final String strBody,HttpServletRequest req,Principal principal) {
		return this.doRestServiceWork(new IRestServiceWork() {
			@Override
			public void execute(RestCallResult callResult) throws Exception {
	    		JSONObject jo = JSONObject.fromString(strBody);
	    		${entityname} ${srfparamname('${de.codeName}')} = new ${entityname}();
	    		DataObject.fromJSONObject(${srfparamname('${de.codeName}')}, jo);
	    		get${de.codeName}Service().${srfmethodname('${action.getPSDEAction().codeName}')}(${srfparamname('${de.codeName}')});
	    		if(callResult.getItem(false)==null)
	    			${srfparamname('${de.codeName}')}.fillJSONObject(callResult.getItem(true), false);
			}
		},req,principal);
	}
</#if>
</#if>
</#if>
</#list>

<#list item.getPSDEServiceAPIMethods() as action>
<#if action.getActionType()??>
<#if action.getActionType() == 'DEACTION'>
<#assign restapi=action.getPSRESTfulAPI()>
<#if restapi.getRequestMethod() == 'PUT'>
	@RequestMapping(<#if restapi.getRequestPath()??>value = "${restapi.getRequestPath()}" , </#if>method = RequestMethod.PUT , produces = { "application/json" })
	@ResponseBody
	String <#if action.codeName??>${srfmethodname('${action.codeName}')}<#else>${srfmethodname('${action.getPSDEAction().codeName}')}</#if> (@RequestBody final String strBody,HttpServletRequest req,Principal principal) {
		return this.doRestServiceWork(new IRestServiceWork() {
			@Override
			public void execute(RestCallResult callResult) throws Exception {
	    		JSONObject jo = JSONObject.fromString(strBody);
	    		${entityname} ${srfparamname('${de.codeName}')} = new ${entityname}();
	    		DataObject.fromJSONObject(${srfparamname('${de.codeName}')}, jo);
	    		get${de.codeName}Service().${srfmethodname('${action.getPSDEAction().codeName}')}(${srfparamname('${de.codeName}')});
	    		if(callResult.getItem(false)==null)
	    			${srfparamname('${de.codeName}')}.fillJSONObject(callResult.getItem(true), false);
			}
		},req,principal);
	}
</#if>
</#if>
</#if>
</#list>

<#list item.getPSDEServiceAPIMethods() as action>
<#if action.getActionType()??>
<#if action.getActionType() == 'DEACTION'>
<#assign restapi=action.getPSRESTfulAPI()>
<#if restapi.getRequestMethod() == 'DELETE'>
	@RequestMapping(<#if restapi.getRequestPath()??>value = "${restapi.getRequestPath()}/{srfkey}"<#else>value = "/{srfkey}"</#if> , method = RequestMethod.DELETE , produces = { "application/json" })
	@ResponseBody
	String <#if action.codeName??>${srfmethodname('${action.codeName}')}<#else>${srfmethodname('${action.getPSDEAction().codeName}')}</#if> (@PathVariable("srfkey") final String strKey,HttpServletRequest req,Principal principal) {
		return this.doRestServiceWork(new IRestServiceWork() {
			@Override
			public void execute(RestCallResult callResult) throws Exception {
	    		${entityname} ${srfparamname('${de.codeName}')} = new ${entityname}();
	    		${srfparamname('${de.codeName}')}.set("${de.getKeyPSDEField().getName()?upper_case}",strKey);
	    		get${de.codeName}Service().${srfmethodname('${action.getPSDEAction().codeName}')}(${srfparamname('${de.codeName}')});
			}
		},req,principal);
	}
</#if>
</#if>
</#if>
</#list>
</#comment>     

<#list item.getPSDEServiceAPIMethods() as action>
<#if action.getActionType()??>
<#if action.getActionType() == 'SELECT'>
<#assign restapi=action.getPSRESTfulAPI()>
@RequestMapping(<#if restapi.getRequestPath()??>value = "${restapi.getRequestPath()}" , </#if>method = RequestMethod.${restapi.getRequestMethod()} , produces = { "application/json" })
@ResponseBody
String ${srfmethodname('${action.codeName}')} (@RequestBody final String strBody,HttpServletRequest req,Principal principal) {
	return this.doRestServiceWork(new IRestServiceWork() {
		@Override
		public void execute(RestCallResult callResult) throws Exception {
			JSONObject jo = JSONObject.fromString(strBody);
			ISelectContext iSelectConext = SelectContext.fromJSONObject(jo);
			ArrayList<${entityname}> list = get${de.codeName}Service().select(iSelectConext);
			if(callResult.getItems(false)==null){
				for(${entityname} item:list){
					callResult.getItems(true).add(DataObject.toJSONObject(item, false));
				}
			}
		}
	},req,principal);
}
</#if>
</#if>
</#list>
  
<#list item.getPSDEServiceAPIMethods() as action>
<#if action.getActionType()??>
<#if action.getActionType() == 'FETCH'>
<#assign restapi=action.getPSRESTfulAPI()>
@RequestMapping(<#if restapi.getRequestPath()??>value = "${restapi.getRequestPath()}" , </#if>method = RequestMethod.${restapi.getRequestMethod()} , produces = { "application/json" })
@ResponseBody
String ${srfmethodname('${action.codeName}')} (@RequestBody final String strBody,HttpServletRequest req,Principal principal) {
	return this.doRestServiceWork(new IRestServiceWork() {
		@Override
		public void execute(RestCallResult callResult) throws Exception {
			JSONObject jo = JSONObject.fromString(strBody);
			IDEDataSetFetchContext iDEDataSetFetchContext = DEDataSetFetchContext.fromJSONObject(jo);
			DBFetchResult dbFetchResult = get${de.codeName}Service().fetch${action.getPSDEDataSet().codeName}(iDEDataSetFetchContext);
			if(dbFetchResult.isError()){
				throw new ErrorException(dbFetchResult.getRetCode(),dbFetchResult.getErrorInfo());
			}
			if(!iDEDataSetFetchContext.isCacheDataSet()){
				dbFetchResult.getDataSet().cacheDataRow();
			}
			if(dbFetchResult.getDataSet().getDataTableCount() == 0){
				throw new ErrorException(Errors.INVALIDDATA);
			}
			callResult.setTotalRow(dbFetchResult.getTotalRow());
			if(callResult.getItems(false)==null){
				int nRowCount = dbFetchResult.getDataSet().getDataTable(0).getCachedRowCount();
				for(int i=0;i< nRowCount;i++ ){
					IDataRow iDataRow =  dbFetchResult.getDataSet().getDataTable(0).getCachedRow(i);
					DataObject dataObject = new DataObject();
					DataObject.fromDataRow(dataObject, iDataRow);
					callResult.getItems(true).add(DataObject.toJSONObject(dataObject, false));
				}
			}
		}
	},req,principal);
}
</#if>
</#if>
</#list>
    private ${sys.codeName}SysModel ${srfparamname('${sys.codeName}')}SysModel;
    
    public  ${sys.codeName}SysModel get${sys.codeName}SysModel() {
           if(this.${srfparamname('${sys.codeName}')}SysModel==null)
           {
               try
                {
                      this.${srfparamname('${sys.codeName}')}SysModel = (${sys.codeName}SysModel)SysModelGlobal.getSystem("${pub.getPKGCodeName()}.srv.${sys.codeName}SysModel");
                }
                catch(Exception ex)
                {
                }
           }       
           return this.${srfparamname('${sys.codeName}')}SysModel;
      }

       @Override
       public  ISystemModel getSystemModel() {
        return this.get${sys.codeName}SysModel();
    }

       
     


<#if item.getDataEntity()??>
<#assign de=item.getDataEntity()>
     
      private ${de.getClassOrPkgName('DEMODEL',pub)} ${srfparamname('${de.codeName}')}DEModel;

      public  ${de.getClassOrPkgName('DEMODEL',pub)} get${de.codeName}DEModel() {
            if(this.${srfparamname('${de.codeName}')}DEModel==null){
                try
                {
                     this.${srfparamname('${de.codeName}')}DEModel = (${de.getClassOrPkgName('DEMODEL',pub)})DEModelGlobal.getDEModel("${de.getClassOrPkgName('DEMODEL',pub)}");
                }
                catch(Exception ex)
                {
                }
            }
           return this.${srfparamname('${de.codeName}')}DEModel;
      }

       public  IDataEntityModel getDEModel() {
          return this.get${de.codeName}DEModel();
      }
    
    public  ${de.getClassOrPkgName('SERVICE',pub)} get${de.codeName}Service() {
    	  try
          {
               return (${de.getClassOrPkgName('SERVICE',pub)})ServiceGlobal.getService("${de.getClassOrPkgName('SERVICE',pub)}",this.getSessionFactory());
          }
          catch(Exception ex)
          {
        	  return null;
          }
    }

      	@Override
  	public IService getService()
  	{
  		return get${de.codeName}Service();
  	}


     
</#if>

}