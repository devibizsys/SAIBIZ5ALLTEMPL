package ${pub.getPKGCodeName()}.srv.ba.${item.getPSSysBDScheme().getShortCodeName()?lower_case};

import net.ibizsys.paas.db.IDBDialect;
import net.ibizsys.paas.core.IDataEntity;
import net.ibizsys.paas.util.StringHelper;
import net.ibizsys.paas.core.IDEDBCallContext;
import net.ibizsys.paas.db.DBCallResult;
import net.ibizsys.psba.dao.BADAOGlobal;
import net.ibizsys.paas.dao.IDAO;
import net.ibizsys.paas.entity.IEntity;
import net.ibizsys.psba.core.IBATableModel;
import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import ${pub.getPKGCodeName()}.srv.ba.${item.getPSSysBDScheme().getShortCodeName()?lower_case}.${item.codeName};

/**
 * 大数据表[${item.name}]DAO对象基类
 */
@Repository
public class ${item.codeName}BADAO extends ${pub.getPKGCodeName()}.srv.ba.${item.getPSSysBDScheme().getShortCodeName()}BADAOBase<${item.codeName}> {

   private static final long serialVersionUID = -1L;

   public ${item.codeName}BADAO(){
        super();
       
    }
   
   @PostConstruct  
   public void postConstruct() throws Exception{    
	   BADAOGlobal.registerBADAO(getBADAOId(), this);
           BADAOGlobal.registerBADAO("${item.id}", this);
  }    

   	/* (non-Javadoc)
	 * @see net.ibizsys.psba.dao.BADAOBase#getBADAOId()
	 */
	@Override
	 protected String getBADAOId()
	 {
		 return "${pub.getPKGCodeName()?lower_case}.srv.ba.${item.getPSSysBDScheme().getShortCodeName()?lower_case}.${item.codeName}BADAO";
	 }
  
       private IBATableModel iBATableModel;

     /*
      	 * (non-Javadoc)
      	 * @see net.ibizsys.psba.dao.IBADAO#getBATableModel()
      	 */
  		@Override
      public IBATableModel getBATableModel() {
            if(this.iBATableModel==null){
                try
                {
                     this.iBATableModel = (IBATableModel) this.getBASchemeModel().getBATable("${item.name}",false);
                }
                catch(Exception ex)
                {
                }
            }
           return this.iBATableModel;
      }
}