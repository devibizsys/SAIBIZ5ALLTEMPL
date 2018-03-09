package ${pub.getPKGCodeName()}.srv;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 系统[${item.codeName}]流程引擎对象基类
 */
public abstract class ${item.codeName}SysWFServiceBase extends  ${pub.getBaseClassPKGCodeName()}.pswf.core.WFServiceBase
{
    private static final Log log = LogFactory.getLog(${item.codeName}SysWFServiceBase.class);
    public ${item.codeName}SysWFServiceBase() throws Exception{
       
        super();
    }


    
}