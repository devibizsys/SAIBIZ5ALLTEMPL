package ${pub.getPKGCodeName()}.${app.getAppFolder()?lower_case}.${appview.getPSAppModule().codeName?lower_case}.ctrlhandler;


import java.util.ArrayList;
import java.util.List;
import net.ibizsys.paas.util.StringHelper;
import net.ibizsys.paas.web.WebContext;
import net.ibizsys.paas.demodel.DEModelGlobal;
import net.ibizsys.paas.demodel.IDataEntityModel;
import net.ibizsys.paas.service.IService;
import net.ibizsys.paas.service.ServiceGlobal;

import ${pub.getPKGCodeName()}.${app.getAppFolder()?lower_case}.srv.${de.getPSSystemModule().codeName?lower_case}.ctrlmodel.${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model;
import ${de.getClassOrPkgName('DEMODEL',pub)};
import ${de.getClassOrPkgName('SERVICE',pub)};
import ${de.getClassOrPkgName('DAO',pub)};
import ${de.getClassOrPkgName('ENTITY',pub)};

${ctrlcode.code}