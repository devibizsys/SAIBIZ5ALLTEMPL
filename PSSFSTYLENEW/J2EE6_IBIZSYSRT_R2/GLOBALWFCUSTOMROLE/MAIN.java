package ${pub.getPKGCodeName()}.srv;

import net.ibizsys.paas.entity.IEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 系统 ${item.codeName} 工作流自定义角色对象基类
 * 
 * @param <ET>
 */
public abstract class ${item.codeName}SysWFCustomRoleModelBase extends ${pub.getBaseClassPKGCodeName()}.pswf.core.WFCustomRoleModelBase  
{
	private static final Log log = LogFactory.getLog(${item.codeName}SysWFCustomRoleModelBase.class);
   public ${item.codeName}SysWFCustomRoleModelBase(){
        super();

   }

    
}