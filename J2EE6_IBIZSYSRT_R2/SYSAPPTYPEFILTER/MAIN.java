package ${pub.getPKGCodeName()}.${item.getAppFolder()?lower_case};

import org.springframework.stereotype.Component;
import net.ibizsys.paas.appmodel.AppModelGlobal;
import net.ibizsys.paas.core.ISystem;
import net.ibizsys.paas.sysmodel.SysModelGlobal;
import net.ibizsys.paas.service.IService;
import net.ibizsys.paas.service.ServiceGlobal;
import net.ibizsys.paas.appmodel.AppViewModel;
import net.ibizsys.paas.appmodel.AppDEViewModel;
import ${pub.getPKGCodeName()}.srv.${sys.codeName}SysModel;

/**
 * 应用程序[${item.name}]重写类型过滤器
 */
public class ${item.getPKGCodeName()}AppTypeFilter extends ${pub.getBaseClassPKGCodeName()}.paas.util.spring.OverrideTypeFilterBase {
	
        @Override
	protected String getPackagePath(){return "${pub.getPKGCodeName()}.${item.getAppFolder()?lower_case}";} 

}