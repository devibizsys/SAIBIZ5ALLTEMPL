package ${pub.getPKGCodeName()}.srv;

import net.ibizsys.paas.entity.IEntity;

/**
 * 系统[${item.codeName}]服务对象基类
 */
public abstract class ${item.codeName}SysServiceBase<ET extends IEntity> extends ${pub.getBaseClassPKGCodeName()}.paas.service.ServiceBase<ET>{

   public ${item.codeName}SysServiceBase(){
        super();

   }
    
}