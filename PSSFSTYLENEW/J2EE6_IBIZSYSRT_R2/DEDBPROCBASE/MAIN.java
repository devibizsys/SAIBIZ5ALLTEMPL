package ${pub.getPKGCodeName()}.srv.${de.getPSSystemModule().codeName?lower_case}.demodel.${de.codeName?lower_case}.dbproc;


import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.ibizsys.paas.core.IDataEntity;
import net.ibizsys.paas.core.DEDBProc;
import net.ibizsys.paas.core.DBProcParams;
import net.ibizsys.paas.core.DBProcParam;
import net.ibizsys.paas.core.DEDBProcDialect;

@DEDBProc(id="${srfxmlvalue('${item.id}')}",name="${srfxmlvalue('${item.name}')}",procname="${srfxmlvalue('${item.getDBProcName()}')}",timeout=${item.getTimeOut()})
@DEDBProcDialect({
<#list sys.supportDBTypes as dbtype>
<#if (dbtype_index>0)>,</#if>@DBProcParams(dbtype="${dbtype}",value={
<#list de.getPSDEDBSysProc(item.getPSDEDBSysProcId()).getPSDEDBSysProcCode(dbtype).getPSDBProcParams() as procparam> 
<#if (procparam_index>0)>,</#if>@DBProcParam(name="${procparam.name}",dir=${procparam.getDirection()},datatype=${procparam.getDataType()})
</#list>
})
</#list>
})


public abstract class ${de.codeName}${item.codeName}DBProcModelBase extends ${pub.getBaseClassPKGCodeName()}.paas.demodel.DEDBProcModelBase{

   public ${de.codeName}${item.codeName}DBProcModelBase(){
        super();

        this.initAnnotation(${de.codeName}${item.codeName}DBProcModelBase.class);
   }

}