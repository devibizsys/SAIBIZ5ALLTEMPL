package ${pub.getPKGCodeName()}.srv.${item.getPSSystemModule().codeName?lower_case}.service;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.annotation.PostConstruct;
import net.ibizsys.paas.service.ServiceGlobal;
import net.ibizsys.paas.util.StringHelper;
import net.ibizsys.paas.core.IDataEntity;
import net.ibizsys.paas.data.DataObject;
import net.sf.json.JSONObject;
import net.ibizsys.paas.service.IService;
import net.ibizsys.paas.service.IInheritDEServiceProxy;
import org.springframework.stereotype.Component;

import ${pub.getPKGCodeName()}.srv.${item.getPSSystemModule().codeName?lower_case}.entity.${item.codeName};
import ${pub.getPKGCodeName()}.srv.${item.getPSSystemModule().codeName?lower_case}.dao.${item.codeName}DAO;

<#list  item.getPSDERs(true) as der >
<#if der.getDERType()=='DERINHERIT'>
<#assign minorde=der.getMinorPSDataEntity()>
import ${pub.getPKGCodeName()}.srv.${minorde.getPSSystemModule().codeName?lower_case}.service.${minorde.codeName}Service;
import ${pub.getPKGCodeName()}.srv.${minorde.getPSSystemModule().codeName?lower_case}.entity.${minorde.codeName};
</#if>
</#list>
/**
 * 继承实体[${item.name}]服务操作对象
 */
@Component
public class ${item.codeName}ServiceProxy extends ${item.codeName}Service<${item.codeName}> implements IInheritDEServiceProxy<${item.codeName}>{

  private static final Log log = LogFactory.getLog(${item.codeName}ServiceProxy.class);
   public ${item.codeName}ServiceProxy(){
        super();
        
   }
   	/**
  	 * Spring注册后执行构造处理
	 * @throws Exception
	 */
   @PostConstruct  
   public void postConstruct() throws Exception{    
	   	ServiceGlobal.registerService(getServiceId(), this);
        ServiceGlobal.registerService(getServiceId()+"Proxy", this);
        <#comment>
    	ServiceGlobal.registerService(getServiceId(),IDataEntity.DSLINK_DEFAULT, this);
        ServiceGlobal.registerService(getServiceId()+"Proxy",IDataEntity.DSLINK_DEFAULT, this);
        <#if item.isEnableMultiDS()>
 	   if(true)
 	   {
 		   ${item.codeName}ServiceBase service = (${item.codeName}ServiceBase)this.getClass().newInstance();
 		   service.setDSLink(IDataEntity.DSLINK_DB2);
 		   ServiceGlobal.registerService(getServiceId(),IDataEntity.DSLINK_DB2, service);
 		   ServiceGlobal.registerService(getServiceId()+"Proxy",IDataEntity.DSLINK_DB2, service);
 	   }
 	   if(true)
 	   {
 		   ${item.codeName}ServiceBase service = (${item.codeName}ServiceBase)this.getClass().newInstance();
 		   service.setDSLink(IDataEntity.DSLINK_DB3);
 		   ServiceGlobal.registerService(getServiceId(),IDataEntity.DSLINK_DB3, service);
 		   ServiceGlobal.registerService(getServiceId()+"Proxy",IDataEntity.DSLINK_DB3, service);
 	   }
 	   if(true)
 	   {
 		   ${item.codeName}ServiceBase service = (${item.codeName}ServiceBase)this.getClass().newInstance();
 		   service.setDSLink(IDataEntity.DSLINK_DB4);
 		   ServiceGlobal.registerService(getServiceId(),IDataEntity.DSLINK_DB4, service);
 		   ServiceGlobal.registerService(getServiceId()+"Proxy",IDataEntity.DSLINK_DB4, service);
 	   }
 	   </#if>
 	  </#comment>
   } 
  
    /* (non-Javadoc)
	 * @see net.ibizsys.paas.service.ServiceBase#getServiceId()
	 */
	@Override
   protected String getServiceId()
   {
	   return "${pub.getPKGCodeName()}.srv.${item.getPSSystemModule().codeName?lower_case}.service.${item.codeName}Service";
   }

   /* (non-Javadoc)
    * @see net.ibizsys.paas.service.ServiceBase#remove(net.ibizsys.paas.entity.IEntity)
    */
    @Override
    public void remove(${item.codeName} entity) throws Exception
    {
		if(entity.get${item.getIndexTypePSDEField().codeName}()==null)
		{
		     //重新获取
		     this.get(entity);
		}
	 
<#list  item.getPSDERs(true) as der >
<#if der.getDERType()=='DERINHERIT'>
<#assign minorde=der.getMinorPSDataEntity()>
<#if srfjavatype(item.getIndexTypePSDEField().stdDataType)=='String'>
        if(StringHelper.compare(entity.get${item.getIndexTypePSDEField().codeName}(),"${minorde.getPSDERInherit().typeValue}",true)==0){
<#else>
        if(entity.get${item.getIndexTypePSDEField().codeName}()==${minorde.getPSDERInherit().typeValue}){
</#if>
             ${minorde.codeName}Service realService= (${minorde.codeName}Service)ServiceGlobal.getService(${minorde.codeName}Service.class,this.getSessionFactory());
             ${minorde.codeName} realEntity = new  ${minorde.codeName}();
             realEntity.set${minorde.getKeyPSDEField().codeName}(entity.get${item.getKeyPSDEField().codeName}());
             realService.remove(realEntity );
             return;
        }
</#if>
</#list>
  
	   throw new Exception(StringHelper.format("无法识别的继承类型[%1$s]",entity.get${item.getIndexTypePSDEField().codeName}()));
    }
        
    /* (non-Javadoc)
     * @see net.ibizsys.paas.service.IInheritDEServiceProxy#getReal(net.ibizsys.paas.entity.IEntity, boolean)
     */
    @Override
    public ${item.codeName} getReal(${item.codeName} entity,boolean bTryMode) throws Exception
    {
		if(entity.get${item.getIndexTypePSDEField().codeName}()==null)
		{
		     //重新获取
		     if(!this.get(entity,bTryMode))
		    	 return null;
		}
	 
<#list  item.getPSDERs(true) as der >
<#if der.getDERType()=='DERINHERIT'>
<#assign minorde=der.getMinorPSDataEntity()>
<#if srfjavatype(item.getIndexTypePSDEField().stdDataType)=='String'>
        if(StringHelper.compare(entity.get${item.getIndexTypePSDEField().codeName}(),"${minorde.getPSDERInherit().typeValue}",true)==0){
<#else>
        if(entity.get${item.getIndexTypePSDEField().codeName}()==${minorde.getPSDERInherit().typeValue}){
</#if>
             ${minorde.codeName}Service realService= (${minorde.codeName}Service)ServiceGlobal.getService(${minorde.codeName}Service.class,this.getSessionFactory());
             ${minorde.codeName} realEntity = new  ${minorde.codeName}();
             realEntity.set${minorde.getKeyPSDEField().codeName}(entity.get${item.getKeyPSDEField().codeName}());
             if(! realService.get(realEntity,bTryMode ))
            	 return null;
             return realEntity;
        }
</#if>
</#list>
  
	   throw new Exception(StringHelper.format("无法识别的继承类型[%1$s]",entity.get${item.getIndexTypePSDEField().codeName}()));
    }
        
    /* (non-Javadoc)
     * @see net.ibizsys.paas.service.IInheritDEServiceProxy#getRealService(net.ibizsys.paas.entity.IEntity)
     */
    @Override
    public IService getRealService(${item.codeName} entity) throws Exception
    {
		if(entity.get${item.getIndexTypePSDEField().codeName}()==null)
		{
		     //重新获取
		     this.get(entity);
		}
	 
<#list  item.getPSDERs(true) as der >
<#if der.getDERType()=='DERINHERIT'>
<#assign minorde=der.getMinorPSDataEntity()>
<#if srfjavatype(item.getIndexTypePSDEField().stdDataType)=='String'>
        if(StringHelper.compare(entity.get${item.getIndexTypePSDEField().codeName}(),"${minorde.getPSDERInherit().typeValue}",true)==0){
<#else>
        if(entity.get${item.getIndexTypePSDEField().codeName}()==${minorde.getPSDERInherit().typeValue}){
</#if>
             ${minorde.codeName}Service realService= (${minorde.codeName}Service)ServiceGlobal.getService(${minorde.codeName}Service.class,this.getSessionFactory());
             return realService;
        }
</#if>
</#list>
  
	   throw new Exception(StringHelper.format("无法识别的继承类型[%1$s]",entity.get${item.getIndexTypePSDEField().codeName}()));
    }
        
	/* (non-Javadoc)
	 * @see net.ibizsys.paas.service.ServiceBase#onExportCurModel(net.ibizsys.paas.entity.IEntity, java.util.ArrayList)
	 */
    @Override
	protected void onExportCurModel(${item.codeName} entity,  ArrayList<JSONObject> list)throws Exception
	{
<#list  item.getPSDERs(true) as der >
<#if der.getDERType()=='DERINHERIT'>
<#assign minorde=der.getMinorPSDataEntity()>
<#if srfjavatype(item.getIndexTypePSDEField().stdDataType)=='String'>
        if(StringHelper.compare(entity.get${item.getIndexTypePSDEField().codeName}(),"${minorde.getPSDERInherit().typeValue}",true)==0){
<#else>
        if(entity.get${item.getIndexTypePSDEField().codeName}()==${minorde.getPSDERInherit().typeValue}){
</#if>
             ${minorde.codeName}Service realService= (${minorde.codeName}Service)ServiceGlobal.getService(${minorde.codeName}Service.class,this.getSessionFactory());
             ${minorde.codeName} realEntity = new  ${minorde.codeName}();
             realEntity.set${minorde.getKeyPSDEField().codeName}(entity.get${item.getKeyPSDEField().codeName}());
             realService.exportModel(realEntity,list );
             return;
        }
</#if>
</#list>
    	throw new Exception(StringHelper.format("无法识别的继承类型[%1$s]",entity.get${item.getIndexTypePSDEField().codeName}()));
	}

}