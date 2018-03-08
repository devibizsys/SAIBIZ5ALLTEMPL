package ${pub.getPKGCodeName()}.api.${item.codeName?lower_case};

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.stereotype.Component;

/**
 * 系统服务API[${item.name}]模型对象基类
 */
@Component
<#if item.getAPIType() == 'RESTFUL'>
public class ${item.codeName}APIModel extends ${pub.getBaseClassPKGCodeName()}.paas.api.RestServiceAPIModelBase {
</#if>	
	 private static final Log log = LogFactory.getLog(${item.codeName}APIModel.class);

   public ${item.codeName}APIModel() throws Exception{
        super();
        this.setId("${item.id}");
        this.setName("${item.name}");
    }   
}