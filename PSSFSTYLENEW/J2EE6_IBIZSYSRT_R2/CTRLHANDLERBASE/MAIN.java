package ${pub.getPKGCodeName()}.${app.getAppFolder()?lower_case}.${appview.getPSAppModule().codeName?lower_case}.ctrlhandler;


import java.util.ArrayList;
import java.util.List;
import net.ibizsys.paas.util.StringHelper;
import net.ibizsys.paas.web.WebContext;
<#if item.hasCtrlModel()>
import ${pub.getPKGCodeName()}.${app.getAppFolder()?lower_case}.${appview.getPSAppModule().codeName?lower_case}.ctrlmodel.${appview.codeName}${srfclassname('${item.name}')}Model;
</#if>

${ctrlcode.code}