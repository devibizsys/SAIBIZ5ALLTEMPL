package ${pub.getPKGCodeName()}.srv.${item.getPSSystemModule().codeName?lower_case}.dao;

<#comment>
import java.util.List;
import java.util.HashMap;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
</#comment>
import net.ibizsys.paas.db.IDBDialect;
import net.ibizsys.paas.core.IDataEntity;
import net.ibizsys.paas.util.StringHelper;
import net.ibizsys.paas.core.IDEDBCallContext;
import net.ibizsys.paas.db.DBCallResult;
import net.ibizsys.paas.demodel.IDataEntityModel;
import net.ibizsys.paas.demodel.DEModelGlobal;
import net.ibizsys.paas.dao.DAOGlobal;
import net.ibizsys.paas.dao.IDAO;
import net.ibizsys.paas.entity.IEntity;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import ${pub.getPKGCodeName()}.srv.${item.getPSSystemModule().codeName?lower_case}.demodel.${item.codeName}DEModel;
import ${pub.getPKGCodeName()}.srv.${item.getPSSystemModule().codeName?lower_case}.entity.${item.codeName};
<#if item.getInheritPSDataEntity()??>
<#assign inheritde=item.getInheritPSDataEntity()>
import ${pub.getPKGCodeName()}.srv.${inheritde.getPSSystemModule().codeName?lower_case}.dao.${inheritde.codeName}DAO;
import ${pub.getPKGCodeName()}.srv.${inheritde.getPSSystemModule().codeName?lower_case}.entity.${inheritde.codeName};
</#if>
/**
 * 实体[${item.codeName}] DAO对象
 */
@Repository
public class ${item.codeName}DAO extends ${pub.getPKGCodeName()}.srv.${item.getPSSystem().codeName}SysDAOBase<${item.codeName}> {

   private static final long serialVersionUID = -1L;

<#list item.getAllPSDEDataQueries() as dedataquery>
   public static final String DATAQUERY_${dedataquery.name?upper_case} = "${dedataquery.name}";
</#list>

   public ${item.codeName}DAO(){
        super();
       
    }
   
   @PostConstruct  
   public void postConstruct() throws Exception{    
	   DAOGlobal.registerDAO(getDAOId(), this);
<#comment>
	   DAOGlobal.registerDAO(getDAOId(),IDataEntity.DSLINK_DEFAULT,this);
	   <#if item.isEnableMultiDS()>
	   if(true)
	   {
		   ${item.codeName}DAOBase dao = (${item.codeName}DAOBase)this.getClass().newInstance();
		   dao.setDSLink(IDataEntity.DSLINK_DB2);
		   DAOGlobal.registerDAO(getDAOId(),IDataEntity.DSLINK_DB2, dao);
	   }
	   if(true)
	   {
		   ${item.codeName}DAOBase dao = (${item.codeName}DAOBase)this.getClass().newInstance();
		   dao.setDSLink(IDataEntity.DSLINK_DB3);
		   DAOGlobal.registerDAO(getDAOId(),IDataEntity.DSLINK_DB3, dao);
	   }
	   if(true)
	   {
		   ${item.codeName}DAOBase dao = (${item.codeName}DAOBase)this.getClass().newInstance();
		   dao.setDSLink(IDataEntity.DSLINK_DB4);
		   DAOGlobal.registerDAO(getDAOId(),IDataEntity.DSLINK_DB4, dao);
	   }
	   </#if>
</#comment>
   }    

   	/* (non-Javadoc)
	 * @see net.ibizsys.paas.dao.DAOBase#getDAOId()
	 */
	@Override
	 protected String getDAOId()
	 {
		 return "${pub.getPKGCodeName()}.srv.${item.getPSSystemModule().codeName?lower_case}.dao.${item.codeName}DAO";
	 }
     
      private ${item.codeName}DEModel ${srfparamname('${item.codeName}')}DEModel;

      /**
  	 	* 获取实体[${item.codeName}]模型对象
  	 	* @return
  	 	*/
      public  ${item.codeName}DEModel get${item.codeName}DEModel() {
            if(this.${srfparamname('${item.codeName}')}DEModel==null){
                try
                {
                     this.${srfparamname('${item.codeName}')}DEModel = (${item.codeName}DEModel)DEModelGlobal.getDEModel("${pub.getPKGCodeName()}.srv.${item.getPSSystemModule().codeName?lower_case}.demodel.${item.codeName}DEModel");
                }
                catch(Exception ex)
                {
                }
            }
           return this.${srfparamname('${item.codeName}')}DEModel;
      }

      	/*
      	 * (non-Javadoc)
      	 * @see net.ibizsys.paas.dao.DAOBase#getDEModel()
      	 */
  		@Override
       public  IDataEntityModel getDEModel() {
          return this.get${item.codeName}DEModel();
      }
 

<#comment>

    @Autowired
   	private IDBDialect dbDialect;

       public void setDBDialect(IDBDialect dbDialect) {
           this.dbDialect = dbDialect;
       }
       public IDBDialect getDBDialect() {
           return dbDialect;
       }


   @Autowired
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    
    	/**
	 * 获取当前会话
	 * 
	 * @return
	 */
        @Override
	public Session getCurrentSession(){
            return this.getSessionFactory().getCurrentSession();
        }
</#comment>
<#if item.getInheritPSDataEntity()??>
<#assign inheritde=item.getInheritPSDataEntity()>
     private ${inheritde.codeName}DAO ${srfparamname('${inheritde.codeName}')}DAO;
     
     /* (non-Javadoc)
      * @see net.ibizsys.paas.dao.DAOBase#getInheritDEDAO()
      */
     @Override
     protected  IDAO  getInheritDEDAO() {
        if(this.${srfparamname('${inheritde.codeName}')}DAO==null){
                try
                {
                     this.${srfparamname('${inheritde.codeName}')}DAO= (${inheritde.codeName}DAO)DAOGlobal.getDAO("${pub.getPKGCodeName()}.srv.${inheritde.getPSSystemModule().codeName?lower_case}.dao.${inheritde.codeName}DAO",this.getSessionFactory());
                }
                catch(Exception ex)
                {
                }
            }
           return this.${srfparamname('${inheritde.codeName}')}DAO;
    }

    /**
     * 填充继承实体数据信息
     * @param et
     * @throws Exception
     */
    @Override
    protected void fillInheritEntity(${item.codeName} et)throws Exception
    {
        super.fillInheritEntity( et);

        ${inheritde.codeName} ${srfparamname('${inheritde.codeName}')} =  (${inheritde.codeName})et;
        ${srfparamname('${inheritde.codeName}')}.set${inheritde.getKeyPSDEField().codeName}(et.get${item.getKeyPSDEField().codeName}());
        if(et.is${item.getMajorPSDEField().codeName}Dirty())
             ${srfparamname('${inheritde.codeName}')}.set${inheritde.getMajorPSDEField().codeName}(et.get${item.getMajorPSDEField().codeName}());
	    ${srfparamname('${inheritde.codeName}')}.set(${inheritde.codeName}.FIELD_${inheritde.getIndexTypePSDEField().codeName?upper_case},"${item.getPSDERInherit().getTypeValue()}");
    }

</#if> 
<#list deactions as deaction>
    ${deaction.code}
</#list>    
  
}