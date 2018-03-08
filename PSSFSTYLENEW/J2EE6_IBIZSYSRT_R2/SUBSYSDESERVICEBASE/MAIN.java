package ${pub.getPKGCodeName()}.srv.subsys.service;


import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import net.ibizsys.paas.exception.ErrorException;
import net.ibizsys.paas.core.IDataEntity;
import net.ibizsys.paas.core.IDEDataSetFetchContext;
import net.ibizsys.paas.db.DBCallResult;
import net.ibizsys.paas.db.DBFetchResult;
import net.ibizsys.paas.db.IProcParam;
import net.ibizsys.paas.util.StringHelper;
import net.ibizsys.paas.util.StringBuilderEx;
import net.ibizsys.paas.core.Errors;
import net.ibizsys.paas.core.ActionContext;
import net.ibizsys.paas.data.DataObject;
import net.ibizsys.paas.service.ServiceGlobal;
import net.ibizsys.paas.entity.IEntity;
import net.ibizsys.paas.demodel.IDataEntityModel;
import net.ibizsys.paas.demodel.DEModelGlobal;
import net.ibizsys.paas.demodel.IDELogicModel;
import net.ibizsys.paas.dao.DAOGlobal;
import net.ibizsys.paas.web.WebContext;
import net.ibizsys.paas.service.IService;
import net.ibizsys.paas.util.DataTypeHelper;
import net.ibizsys.paas.util.KeyValueHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import net.ibizsys.paas.db.SelectCond;
import net.ibizsys.paas.service.IServiceWork;
import net.ibizsys.paas.service.ITransaction;
import net.ibizsys.paas.dao.IDAO;
import net.ibizsys.paas.service.CloneSession;
import net.ibizsys.paas.service.ServiceBase;
import net.ibizsys.paas.entity.EntityFieldError;
import net.ibizsys.paas.entity.EntityError;
import java.sql.Timestamp;
import net.ibizsys.paas.util.DefaultValueHelper;
import javax.annotation.PostConstruct;
import net.ibizsys.paas.service.IDataContextParam;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;
import net.ibizsys.paas.service.ServiceGlobal;



import ${de.getClassOrPkgName('DEMODEL',pub)};
import ${de.getClassOrPkgName('DAO',pub)};
import ${de.getClassOrPkgName('ENTITY',pub)};

<#assign entityname=item.codeName>
<#assign preparelastforupdate=0>
/**
 * ${item.logicName} 服务对象基类
 * 注意，此文件不能修改
 * 
 */
@Component
public class ${item.codeName}Service extends ${de.getClassOrPkgName('SUBSYS_SERVICE',pub)}{

<#list item.getAllPSDEDataSets() as dedataset>
<#if dedataset.getExtendMode()==2>
   public final static String DATASET_${dedataset.name?upper_case} = "${dedataset.name}";
</#if>
</#list>


   public ${item.codeName}Service (){
        super();
       
   }
   
    /**
     * 获取实体[${item.codeName}]服务对象
     * @return
     * @throws Exception
     */   
    public static ${item.codeName}Service getInstance() throws Exception{
    	return getInstance(null);
    }
    
    /**
     * 获取实体[${item.codeName}]服务对象
     * @param sessionFactory
     * @return
     * @throws Exception
     */
    public static ${item.codeName}Service getInstance(SessionFactory sessionFactory) throws Exception{
    	return (${item.codeName}Service)ServiceGlobal.getService(${item.codeName}Service.class, sessionFactory);
    }
   

   @PostConstruct  
   public void postConstruct() throws Exception{    
	   super.postConstruct();
	   ServiceGlobal.registerService("${de.getClassOrPkgName('SERVICE',pub)}", this);
           <#comment>  
	   ServiceGlobal.registerService("${de.getClassOrPkgName('SERVICE',pub)}",IDataEntity.DSLINK_DEFAULT,this);
           </#comment>
   }     


               
    /**
	 * 获取数据集合
	 * @param strDataSetName
	 * @param iDEDataSetFetchContext
	 * @return
	 * @throws Exception
	 */
        @Override
	protected DBFetchResult onfetchDataSet(String strDataSetName,IDEDataSetFetchContext iDEDataSetFetchContext) throws Exception
	{
<#list item.getAllPSDEDataSets() as dedataset>
<#if dedataset.getExtendMode()==2>
              if(StringHelper.compare(strDataSetName,DATASET_${dedataset.name?upper_case},true)==0){
                   return this.fetch${dedataset.codeName}(iDEDataSetFetchContext);
              }
</#if>
</#list>
              return super.onfetchDataSet(strDataSetName,iDEDataSetFetchContext);
	}

<#if de.isEnableTempData()>
    /**
     * 获取数据集合
   	 * @param strDataSetName
   	 * @param iDEDataSetFetchContext
   	 * @return
   	 * @throws Exception
   	 */
    @Override
   	protected DBFetchResult onfetchDataSetTemp(String strDataSetName,IDEDataSetFetchContext iDEDataSetFetchContext) throws Exception
   	{
   <#list item.getAllPSDEDataSets() as dedataset>
   <#if dedataset.getExtendMode()==2>
         if(StringHelper.compare(strDataSetName,DATASET_${dedataset.name?upper_case},true)==0){
                return this.fetchTemp${dedataset.codeName}(iDEDataSetFetchContext);
         }
   </#if>
   </#list>
         return super.onfetchDataSetTemp(strDataSetName,iDEDataSetFetchContext);
   	}
</#if>
	

<#list fetchdedatasets as fetchdedataset>
    ${fetchdedataset.code}

</#list>   


}