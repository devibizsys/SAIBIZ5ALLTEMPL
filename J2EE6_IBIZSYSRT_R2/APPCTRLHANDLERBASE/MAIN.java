package ${pub.getPKGCodeName()}.${app.getAppFolder()?lower_case}.${appview.getPSAppModule().codeName?lower_case}.ctrlhandler;


import java.util.ArrayList;
import java.util.List;
import net.ibizsys.paas.util.StringHelper;
import net.ibizsys.paas.web.WebContext;
import net.ibizsys.paas.demodel.DEModelGlobal;
import net.ibizsys.paas.demodel.IDataEntityModel;
import ${pub.getPKGCodeName()}.${app.getAppFolder()?lower_case}.srv.sys.ctrlmodel.${app.getPKGCodeName()}${item.codeName}${srfclassname('${item.getControlType()}')}Model;
import ${pub.getPKGCodeName()}.srv.${sys.codeName}SysModel;
<#if item.getDataEntity()??>
<#assign de=item.getDataEntity()>
import ${pub.getPKGCodeName()}.srv.${de.getPSSystemModule().codeName?lower_case}.service.${de.codeName}Service;
import ${pub.getPKGCodeName()}.srv.${de.getPSSystemModule().codeName?lower_case}.demodel.${de.codeName}DEModel;
</#if>


${ctrlcode.code}