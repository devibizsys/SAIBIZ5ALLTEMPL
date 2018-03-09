package ${pub.getPKGCodeName()}.srv.${de.getPSSystemModule().codeName?lower_case}.demodel;


import java.util.List;
import java.util.HashMap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import net.ibizsys.paas.core.Errors;
import net.ibizsys.paas.core.IActionContext;
import net.ibizsys.paas.data.DataObject;
import net.ibizsys.paas.db.DBCallResult;
import net.ibizsys.paas.db.DBFetchResult;
import net.ibizsys.paas.db.IDataRow;
import net.ibizsys.paas.db.IDataTable;
import net.ibizsys.paas.db.SqlParamList;
import net.ibizsys.paas.entity.IEntity;
import net.ibizsys.paas.entity.SimpleEntity;
import net.ibizsys.paas.exception.ErrorException;
import net.ibizsys.paas.web.WebContext;
import net.ibizsys.paas.service.ServiceGlobal;
import net.ibizsys.paas.service.IService;
import net.ibizsys.pswf.core.IWFModel;
import net.ibizsys.paas.sysmodel.ISystemModel;
import net.ibizsys.pswf.core.IWFService;
import net.ibizsys.pswf.core.WFActionParam;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ${de.getClassOrPkgName('ENTITY',pub)};
import ${de.getClassOrPkgName('SERVICE',pub)};

/**
 * 实体[${de.name}]逻辑处理[${item.name}]模型对象
 */
public class ${de.codeName}${item.codeName}LogicModel extends ${pub.getBaseClassPKGCodeName()}.paas.demodel.DELogicModelBase<${de.codeName}>{
  
   private static final Log log = LogFactory.getLog(${de.codeName}${item.codeName}LogicModel.class);

   public ${de.codeName}${item.codeName}LogicModel(){
        super();
        //设置逻辑基本信息
        this.setId("${srfxmlvalue('${item.id}')}");
        this.setName("${srfxmlvalue('${item.codeName}')}");
        //设置默认参数名称
        this.setDefaultParamName("${srfxmlvalue('${item.getDefaultParamName()}')}");  
    }

   /* (non-Javadoc)
    * @see net.ibizsys.paas.demodel.DELogicModelBase#onExecute(net.ibizsys.paas.core.IActionContext)
    */
   @Override
   protected void onExecute(IActionContext iActionContext) throws Exception
   {
<#list item.getPSDELogicParams() as logicparam>
<#if (!logicparam.isDefault())>
		//初始化逻辑变量[${logicparam.name}]
<#if logicparam.isSessionParam()>
        IEntity ${srfparamname('${logicparam.codeName}')} = (IEntity) net.ibizsys.paas.service.ActionSessionManager.getCurrentSession(true).getActionParam("${logicparam.codeName}");
        if(${srfparamname('${logicparam.codeName}')} == null){
        	 <#if logicparam.getParamPSDataEntity()??>     
        	 <#assign paramde=logicparam.getParamPSDataEntity()>
        	 	${srfparamname('${logicparam.codeName}')} = new ${paramde.getClassOrPkgName('ENTITY',pub)}();
        	 <#else>
        	    ${srfparamname('${logicparam.codeName}')} = new SimpleEntity();
        	 </#if>
        	 net.ibizsys.paas.service.ActionSessionManager.getCurrentSession(true).setActionParam("${logicparam.codeName}", ${srfparamname('${logicparam.codeName}')});
        }
        iActionContext.setParam("${logicparam.codeName}",${srfparamname('${logicparam.codeName}')});
<#elseif logicparam.isEnvParam()>
        IEntity ${srfparamname('${logicparam.codeName}')} = (IEntity) net.ibizsys.paas.service.ActionSessionManager.getCurrentSession(true).getEnvEntity(true);
        iActionContext.setParam("${logicparam.codeName}",${srfparamname('${logicparam.codeName}')});
<#else>
<#if logicparam.getParamPSDataEntity()??>
<#assign paramde=logicparam.getParamPSDataEntity()>
   		iActionContext.setParam("${logicparam.codeName}",new ${paramde.getClassOrPkgName('ENTITY',pub)}());
<#else>
		iActionContext.setParam("${logicparam.codeName}",new SimpleEntity());
</#if>
</#if>
</#if>
</#list>	
		execute${item.getStartPSDELogicNode().codeName}(iActionContext);
   }

<#list delogicnodes as delogicnode>
   ${delogicnode.code} 
</#list>

}