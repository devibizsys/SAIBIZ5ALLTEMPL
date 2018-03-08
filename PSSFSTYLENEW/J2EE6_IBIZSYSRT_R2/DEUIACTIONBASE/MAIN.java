package ${pub.getPKGCodeName()}.srv.${de.getPSSystemModule().codeName?lower_case}.demodel;


import java.util.List;
import java.util.HashMap;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import net.ibizsys.paas.entity.IEntity;
import net.ibizsys.paas.entity.SimpleEntity;
import net.ibizsys.paas.web.WebContext;
import net.ibizsys.paas.service.ServiceGlobal;
import net.ibizsys.paas.service.IService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ${pub.getPKGCodeName()}.srv.${de.getPSSystemModule().codeName?lower_case}.entity.${de.codeName};
import ${pub.getPKGCodeName()}.srv.${de.getPSSystemModule().codeName?lower_case}.service.${de.codeName}Service;


public class ${de.codeName}${item.codeName}UIActionModel extends ${pub.getBaseClassPKGCodeName()}.paas.demodel.DEUIActionModelBase<${de.codeName}>{
  
   private static final Log log = LogFactory.getLog(${de.codeName}${item.codeName}UIActionModel.class);

   public ${de.codeName}${item.codeName}UIActionModel(){
        super();

        this.setId("${srfxmlvalue('${item.id}')}");
        this.setName("${srfxmlvalue('${item.getUIActionTag()}')}");
        this.setActionTarget("${srfxmlvalue('${item.actionTarget}')}");
        this.setDEActionName("${srfxmlvalue('${item.getPSDEAction().name}')}");
<#if item.isReloadData()>
        this.setReloadData(true);
</#if>
<#if item.isCloseEditView()>
        this.setCloseEditView(true);
</#if>
<#if item.getDataAccessAction()??>
        this.setDataAccessAction("${item.getDataAccessAction()}");
</#if>
<#if item.getSuccessMsg()??>
        this.setSuccessMsg("${srfxmlvalue('${item.getSuccessMsg()}')}");
</#if>
        <#comment>
        this.initAnnotation(${de.codeName}${item.codeName}UIActionModelBase.class);
        </#comment>
   }
 
}