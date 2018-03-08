package ${pub.getPKGCodeName()}.srv;


/**
 * 系统[${item.name}]继承类型过滤器
 */
public class ${item.codeName}SysTypeFilter extends ${pub.getBaseClassPKGCodeName()}.paas.util.spring.OverrideTypeFilterBase {
	
        @Override
	protected String getPackagePath(){return "${pub.getPKGCodeName()}.srv";} 

}