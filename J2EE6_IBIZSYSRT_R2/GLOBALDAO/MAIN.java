package ${pub.getPKGCodeName()}.srv;

import net.ibizsys.paas.entity.IEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 系统[${item.codeName}] DAO对象基类
 * 
 * @param <ET>
 */
public abstract class ${item.codeName}SysDAOBase<ET extends IEntity> extends ${pub.getBaseClassPKGCodeName()}.paas.dao.DAOBase<ET>
{
	private static final Log log = LogFactory.getLog(${item.codeName}SysDAOBase.class);
   public ${item.codeName}SysDAOBase(){
        super();

   }

    
}